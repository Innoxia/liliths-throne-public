package com.lilithsthrone.game.character.npc.dominion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.RandomNPC;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesSpawnRarity;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.HarpyAttackerDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.75
 * @version 0.4.2.1
 * @author Innoxia
 */
public class HarpyNestsAttacker extends RandomNPC {

	public HarpyNestsAttacker(NPCGenerationFlag... generationFlags) {
		this(false, generationFlags);
	}
	
	public HarpyNestsAttacker(boolean isImported, NPCGenerationFlag... generationFlags) {
		super(isImported, false, generationFlags);
		
		if (isImported) {
			return;
		}
		
		// Pre-setup
		this.setLevel(Util.random.nextInt(4) + 2);
		this.setGenderIdentity(Gender.getGenderFromUserPreferences(Femininity.FEMININE));

		Map<AbstractSubspecies, Integer> subspeciesMap = new HashMap<>();
		for(Entry<AbstractSubspecies, SubspeciesSpawnRarity> entry : Subspecies.getWorldSpecies(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_WALKWAYS, false).entrySet()) {
			if(entry.getKey().getRace()==Race.HARPY) {
				AbstractSubspecies.addToSubspeciesMap((int) (1000*entry.getValue().getChanceMultiplier()), this.getGenderIdentity(), entry.getKey(), subspeciesMap);
			}
		}
		
		// Setup
		this.setupNPC(subspeciesMap,
				null,
				Occupation.NPC_HARPY_FLOCK_MEMBER,
				false,
				true,
				false,
				true,
				true,
				true,
				true,
				generationFlags);
		
		// Post-setup
		if (this.getRace() == Race.HUMAN) {
			this.setBody(getGenderIdentity(), Util.getRandomObjectFromWeightedMap(subspeciesMap), RaceStage.LESSER, true);
		}
	}
	
	
	@Override
	public String getDescription() {
		if(this.isSlave()) {
			return UtilText.parse(this, "Having run afoul of the law, [npc.nameIsFull] now a slave, and is no more than [npc.her] owner's property.");
			
		} else if(this.getAffectionLevel(Main.game.getPlayer()).isLessThan(AffectionLevel.POSITIVE_ONE_FRIENDLY)) {
			return UtilText.parse(this, "[npc.Name] is angry with the fact that you've walked into what [npc.she] considers to be '[npc.her]' territory. It seems as though [npc.sheIs] prepared to fight you in order to teach you a lesson...");
			
		} else {
			return UtilText.parse(this, "While your first encounter with [npc.name] was a hostile one, you've since managed to become friends with the aggressive [npc.race], and you're sure to always receive a warm welcome from [npc.herHim].");
		}
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.incrementMoney((int) (this.getInventory().getNonEquippedValue() * 0.5f));
		this.clearNonEquippedInventory(false);
		Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);
		
		Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.CASUAL, settings);
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return HarpyAttackerDialogue.HARPY_ATTACK;
	}

	// Combat:
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", HarpyAttackerDialogue.AFTER_COMBAT_VICTORY);
		} else {
			return new Response ("", "", HarpyAttackerDialogue.AFTER_COMBAT_DEFEAT);
		}
	}
}
