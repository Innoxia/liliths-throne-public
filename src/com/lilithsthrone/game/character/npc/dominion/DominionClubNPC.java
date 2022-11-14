package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.8
 * @version 0.4
 * @author Innoxia
 */
public class DominionClubNPC extends NPC {

	public DominionClubNPC() {
		this(Gender.getGenderFromUserPreferences(false, false), Subspecies.DOG_MORPH, RaceStage.GREATER, false);
	}
	
	public DominionClubNPC(boolean isImported) {
		this(Gender.F_V_B_FEMALE, Subspecies.DOG_MORPH, RaceStage.GREATER, isImported);
	}
	
	public DominionClubNPC(Gender gender, AbstractSubspecies subspecies, RaceStage raceStage, boolean isImported) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3,
				null, null, null,
				new CharacterInventory(10), WorldType.EMPTY, PlaceType.GENERIC_CLUB_HOLDING_CELL, false);
		
		if(!isImported) {
			this.setLocation(Main.game.getPlayer(), false);
			
			setLevel(Util.random.nextInt(5) + 5);
			
			// RACE & NAME:

			setBody(gender, subspecies, raceStage, true);
//			if(subspecies.getRace()==Race.HARPY) {
//				setBody(gender, subspecies, RaceStage.LESSER, true);
//				
//			} else if(gender.isFeminine()) {
//				RaceStage stage = Main.game.getCharacterUtils().getRaceStageFromPreferences(Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(subspecies), gender, subspecies);
//				setBody(gender, subspecies, stage, true);
//				
//			} else {
//				RaceStage stage = Main.game.getCharacterUtils().getRaceStageFromPreferences(Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(subspecies), gender, subspecies);
//				setBody(gender, subspecies, stage, true);
//			}
			
			setName(Name.getRandomTriplet(this.getSubspecies()));
			this.setPlayerKnowsName(true);
			setDescription(UtilText.parse(this,
					"[npc.Name] is a resident of Dominion, who you met in one of the clubs in the city's Nightlife district."));
			
			// PERSONALITY & BACKGROUND:
			
			Main.game.getCharacterUtils().setHistoryAndPersonality(this, false);
			
			// ADDING FETISHES:
			
			Main.game.getCharacterUtils().addFetishes(this);
			
			// BODY RANDOMISATION:
			
			Main.game.getCharacterUtils().randomiseBody(this, true);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			
			equipClothing(EquipClothingSetting.getAllClothingSettings());
			
			Main.game.getCharacterUtils().applyMakeup(this, true);
			Main.game.getCharacterUtils().applyTattoos(this, true);
			
			// Set starting attributes based on the character's race
			initPerkTreeAndBackgroundPerks();
			this.setStartingCombatMoves();
			loadImages();

			initHealthAndManaToMax();
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(this.getHomeWorldLocation()==WorldType.DOMINION) {
			this.setHomeLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
		}
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Not needed
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.CLUBBING, settings);
//		super.equipClothing(settings);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
//	@Override
//	public Vector2i getLocation() {
//		if(this.getWorldLocation()==WorldType.NIGHTLIFE_CLUB
//				&& Main.game.getPlayer().getWorldLocation()==WorldType.NIGHTLIFE_CLUB) {
//			return Main.game.getPlayer().getLocation();
//		}
//		return location;
//	}
//	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
}