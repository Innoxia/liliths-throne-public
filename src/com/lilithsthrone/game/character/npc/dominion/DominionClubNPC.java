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
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.OutfitType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.8
 * @version 0.3.1
 * @author Innoxia
 */
public class DominionClubNPC extends NPC {

	public DominionClubNPC() {
		this(Gender.F_V_B_FEMALE, Subspecies.DOG_MORPH, false);
	}
	
	public DominionClubNPC(boolean isImported) {
		this(Gender.F_V_B_FEMALE, Subspecies.DOG_MORPH, isImported);
	}
	
	public DominionClubNPC(Gender gender, Subspecies subspecies, boolean isImported) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3, gender, Subspecies.DOG_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
		
		if(!isImported) {
			this.setLocation(Main.game.getPlayer(), false);
			
			setLevel(Util.random.nextInt(5) + 5);
			
			// RACE & NAME:

			if(subspecies.getRace()==Race.HARPY) {
				setBody(gender, subspecies, RaceStage.LESSER);
				
			} else {
				if(gender.isFeminine()) {
					RaceStage stage = CharacterUtils.getRaceStageFromPreferences(Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(subspecies), gender, subspecies);
					setBody(gender, subspecies, stage);
					
				} else {
					RaceStage stage = CharacterUtils.getRaceStageFromPreferences(Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(subspecies), gender, subspecies);
					setBody(gender, subspecies, stage);
				}
			}
			
			setName(Name.getRandomTriplet(this.getRace()));
			this.setPlayerKnowsName(true);
			setDescription(UtilText.parse(this,
					"[npc.Name] is a resident of Dominion, who you met in one of Nightlife's clubs."));
			
			// PERSONALITY & BACKGROUND:
			
			CharacterUtils.setHistoryAndPersonality(this, false);
			
			// ADDING FETISHES:
			
			CharacterUtils.addFetishes(this);
			
			// BODY RANDOMISATION:
			
			CharacterUtils.randomiseBody(this, true);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			
			equipClothing(EquipClothingSetting.getAllClothingSettings());
			
			CharacterUtils.applyMakeup(this, true);
			
			// Set starting attributes based on the character's race
			initPerkTreeAndBackgroundPerks();

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
		CharacterUtils.equipClothingFromOutfitType(this, OutfitType.CLUBBING, settings);
//		super.equipClothing(settings);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public Vector2i getLocation() {
		if(this.getWorldLocation()==WorldType.NIGHTLIFE_CLUB
				&& Main.game.getPlayer().getWorldLocation()==WorldType.NIGHTLIFE_CLUB) {
			return Main.game.getPlayer().getLocation();
		}
		return location;
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
		return null;
	}
	
}