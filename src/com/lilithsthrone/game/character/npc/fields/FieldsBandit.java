package com.lilithsthrone.game.character.npc.fields;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.RandomNPC;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;

/**
 * @since 0.4.1
 * @version 0.4.1
 * @author Innoxia
 */
public class FieldsBandit extends RandomNPC {

	public FieldsBandit(NPCGenerationFlag... generationFlags) {
		this(false, generationFlags);
	}
	
	public FieldsBandit(boolean isImported, NPCGenerationFlag... generationFlags) {
		super(isImported, false, generationFlags);
		
		if (isImported) {
			return;
		}
		
		// Pre-setup
		this.setLevel(10 + Util.random.nextInt(11));
		
		Map<AbstractSubspecies, Integer> subspeciesMap = new HashMap<>();
		AbstractPlaceType placeType = Main.game.getPlayer().getLocationPlace().getPlaceType();
		for(AbstractSubspecies s : Subspecies.getAllSubspecies()) {
			if(s.getSubspeciesOverridePriority()>0) { // Do not spawn demonic races, elementals, or youko
				continue;
			}
			if(Subspecies.getWorldSpecies(WorldType.WORLD_MAP, placeType, false).containsKey(s)) {
				AbstractSubspecies.addToSubspeciesMap((int) (10000 * Subspecies.getWorldSpecies(WorldType.WORLD_MAP, placeType, false).get(s).getChanceMultiplier()), getGenderIdentity(), s, subspeciesMap);
			}
		}
		
		// Setup
		this.setupNPC(subspeciesMap,
				null,
				Occupation.NPC_MUGGER,
				true,
				true,
				true,
				true,
				true,
				true,
				true,
				generationFlags);
		
		// Post-setup
		// No post-setup
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.incrementMoney((int) (this.getInventory().getNonEquippedValue() * 2.5f));
		this.clearNonEquippedInventory(false);
		Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);
		String outfitId = "innoxia_genericMugger_dominion_masculine";
		if(this.isFeminine()) {
			outfitId = "innoxia_genericMugger_dominion_feminine";
		}
		Main.game.getCharacterUtils().equipClothingFromOutfitId(this, outfitId, settings);
	}
	
	@Override
	public String getDescription() {
		if(this.isSlave()) {
			return UtilText.parse(this,
					"[npc.NamePos] days of roaming the Foloi Fields and preying upon innocent travellers are now over. Having run afoul of the law, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property.");
			
		} else if(Main.game.getPlayer()!=null && Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())) {
			return UtilText.parse(this, "When you first met [npc.name], [npc.she] was a bandit who roamed the Foloi Fields looking for travellers to prey upon. After a period of being your slave, [npc.sheIs] now your trusted friend.");
			
		} else {
			return UtilText.parse(this, "[npc.Name] is a bandit who roams the Foloi Fields looking for travellers to prey upon.");
		}
	}

	// Combat:
	@Override
	public void applyEscapeCombatEffects() {
		Main.game.banishNPC(this);
	}
	
	// Misc.:
	public int getPaymentDemand() {
		return (Math.max(2500, Math.min(Main.game.getPlayer().getMoney()/10, 10000))/500) * 500; // Round to nearest 500
	}
}
