package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.HarpyAttackerDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.HarpyAttackerDialogueCompanions;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.OutfitType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.75
 * @version 0.2.11
 * @author Innoxia
 */
public class HarpyNestsAttacker extends NPC {

	public HarpyNestsAttacker() {
		this(Gender.F_V_B_FEMALE, false);
	}
	
	public HarpyNestsAttacker(Gender gender) {
		this(gender, false);
	}
	
	public HarpyNestsAttacker(boolean isImported) {
		this(Gender.F_V_B_FEMALE, isImported);
	}
	
	public HarpyNestsAttacker(Gender gender, boolean isImported) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				4, gender, Subspecies.HARPY, RaceStage.LESSER,
				new CharacterInventory(10), WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_WALKWAYS, false);

		if(!isImported) {
			this.setLocation(Main.game.getPlayer(), true);
			
			// Set random level from 2 to 5:
			setLevel(Util.random.nextInt(4) + 2);
			
			
			// RACE & NAME:
			if(this.hasPenis()) {
				if(this.hasBreasts()) {
					setBody(Gender.F_P_B_SHEMALE, Subspecies.HARPY, RaceStage.LESSER);
				} else {
					setBody(Gender.F_P_TRAP, Subspecies.HARPY, RaceStage.LESSER);
				}
			} else {
				if(this.hasBreasts()) {
					setBody(Gender.F_V_B_FEMALE, Subspecies.HARPY, RaceStage.LESSER);
				} else {
					setBody(Gender.F_V_FEMALE, Subspecies.HARPY, RaceStage.LESSER);
				}
			}

			if(Math.random()<0.05) { //5% chance for the NPC to be a half-demon
				this.setBody(CharacterUtils.generateHalfDemonBody(this, this.getGender(), Subspecies.getFleshSubspecies(this), true));
			}
			
			setName(Name.getRandomTriplet(Race.HARPY));
			this.setPlayerKnowsName(false);
			setDescription(UtilText.parse(this,
					"[npc.Name] is angry with the fact that you've walked into what [npc.she] considers to be '[npc.her]' territory. It seems as though [npc.sheIs] prepared to fight you in order to teach you a lesson..."));

			CharacterUtils.setHistoryAndPersonality(this, true);
			this.setHistory(Occupation.NPC_HARPY_FLOCK_MEMBER);
			
			// Add fetishes:
			CharacterUtils.addFetishes(this);
			
			// BODY RANDOMISATION:
			CharacterUtils.randomiseBody(this, true);
			
			// INVENTORY:
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			CharacterUtils.generateItemsInInventory(this);
			
			equipClothing(EquipClothingSetting.getAllClothingSettings());
			CharacterUtils.applyMakeup(this, true);

			initHealthAndManaToMax();
		}

		this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE, true);
		
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Not needed
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		CharacterUtils.equipClothingFromOutfitType(this, OutfitType.CASUAL, settings);
//		super.equipClothing(settings);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}

	@Override
	public void endSex() {
		if(!isSlave()) {
			setPendingClothingDressing(true);
		}
	}

	@Override
	public boolean isClothingStealable() {
		return true;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		if(Main.game.getPlayer().getCompanions().isEmpty()) {
			return HarpyAttackerDialogue.HARPY_ATTACKS;
		} else {
			return HarpyAttackerDialogueCompanions.HARPY_ATTACKS;
		}
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
