package com.lilithsthrone.game.character.npc.submission;

import java.util.List;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.RandomNPC;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.submission.BatCavernDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.3
 * @version 0.3.5.5
 * @author Innoxia
 */
public class BatCavernSlimeAttacker extends RandomNPC {

	public BatCavernSlimeAttacker() {
		this(false);
	}
	
	public BatCavernSlimeAttacker(boolean isImported, NPCGenerationFlag... generationFlags) {
		super(isImported, false, generationFlags);
		
		if (isImported) {
			return;
		}
		
		// Pre-setup
		this.setLevel(8 + Util.random.nextInt(5));
		
		// Setup
		this.setupNPC(Subspecies.SLIME,
				RaceStage.GREATER,
				Occupation.NPC_MUGGER,
				false,
				false,
				false,
				true,
				true,
				true,
				true,
				generationFlags);
		
		// Post-setup
		this.setBodyMaterial(BodyMaterial.SLIME);
		this.useItem(Main.game.getItemGen().generateItem(ItemType.MUSHROOM), this, false);
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.incrementMoney((int) (this.getInventory().getNonEquippedValue() * 0.5f));
		this.clearNonEquippedInventory(false);
		Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);

		Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.MUGGER, settings); //TODO need slime-specific?
	}
	
	@Override
	public void hourlyUpdate(int hour) {
		if(!this.isSlave() && !Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())) {
			this.useItem(Main.game.getItemGen().generateItem(ItemType.MUSHROOM), this, false);
		}
	}
	
	@Override
	public String getDescription() {
		if(this.isSlave()) {
			return (UtilText.parse(this,
					"[npc.NamePos] days of getting high on mushrooms and attacking innocent travellers in the Bat Caverns are now over."
							+ " Having been enslaved as punishment for [npc.her] lawless behaviour, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property."));
		} else if(Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())){
			return (UtilText.parse(this,
					"[npc.NamePos] days of getting high on mushrooms and attacking innocent travellers in the Bat Caverns are now over."
					+ " Having befriended [npc.herHim], you invited [npc.name] to move in with you and helped [npc.herHim] to start a new life."));
		} else {
			return (UtilText.parse(this,
					"[npc.Name] is a resident of the bat caverns, and loves nothing more than getting high on mushrooms, attacking innocent travellers, and having sex."));
		}
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return BatCavernDialogue.CAVERN_ATTACK;
	}

	// Combat:

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", BatCavernDialogue.AFTER_COMBAT_VICTORY);
		} else {
			return new Response ("", "", BatCavernDialogue.AFTER_COMBAT_DEFEAT);
		}
	}
}
