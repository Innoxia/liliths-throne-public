package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePokerTable;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.OutfitType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.6
 * @version 0.3.1
 * @author Innoxia
 */
public class GamblingDenPatron extends NPC {
	
	private DicePokerTable table;
	
	public GamblingDenPatron() {
		this(Gender.F_V_B_FEMALE, DicePokerTable.COPPER, false);
	}
	
	public GamblingDenPatron(Gender gender) {
		this(gender, DicePokerTable.COPPER, false);
	}
	
	public GamblingDenPatron(boolean isImported) {
		this(Gender.F_V_B_FEMALE, DicePokerTable.COPPER, isImported);
	}
	
	public GamblingDenPatron(Gender gender, DicePokerTable table, boolean isImported) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3, gender, Subspecies.ALLIGATOR_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_GAMBLING, false);
		
		this.table = table;
		
		if(!isImported) {
			// Set random level from 5 to 8:
			setLevel(5 + Util.random.nextInt(4));
			
			// RACE & NAME:
			
			int slimeChance = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeQueenHelped) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_SLIME_QUEEN) ? 100 : 50;
			
			Map<Subspecies, Integer> availableRaces = new HashMap<>();
			for(Subspecies s : Subspecies.values()) {
				if(s==Subspecies.SLIME) {
					addToSubspeciesMap(slimeChance, gender, s, availableRaces);
					
				} else if(Subspecies.getWorldSpecies().get(WorldType.SUBMISSION).containsKey(s) && s!=Subspecies.IMP && s!=Subspecies.IMP_ALPHA) {
					addToSubspeciesMap((int) (100 * Subspecies.getWorldSpecies().get(WorldType.SUBMISSION).get(s).getChanceMultiplier()), gender, s, availableRaces);
					
				} else if(Subspecies.getWorldSpecies().get(WorldType.DOMINION).containsKey(s) && s!=Subspecies.IMP && s!=Subspecies.IMP_ALPHA) {
					addToSubspeciesMap((int) (25 * Subspecies.getWorldSpecies().get(WorldType.DOMINION).get(s).getChanceMultiplier()), gender, s, availableRaces);
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces);
			
			setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(gender));
	
			setName(Name.getRandomTriplet(this.getRace()));
			this.setPlayerKnowsName(true);
			
			// PERSONALITY & BACKGROUND:
			
			CharacterUtils.setHistoryAndPersonality(this, false);
			
			// ADDING FETISHES:
			
			CharacterUtils.addFetishes(this);
			this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.TWO_NEUTRAL);
			
			// BODY RANDOMISATION:
			
			CharacterUtils.randomiseBody(this, true);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(750 + Util.random.nextInt(750));
			CharacterUtils.generateItemsInInventory(this);
	
			equipClothing(EquipClothingSetting.getAllClothingSettings());
			CharacterUtils.applyMakeup(this, true);
			
			// Set starting attributes based on the character's race
			initPerkTreeAndBackgroundPerks();

			initHealthAndManaToMax();
		}
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element properties = super.saveAsXML(parentElement, doc);
		
		Element tableElement = doc.createElement("table");
		properties.appendChild(tableElement);
		
		CharacterUtils.addAttribute(doc, tableElement, "value", table.toString());
		
		return properties;
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		try {
			Element npcSpecificElement = (Element) parentElement.getElementsByTagName("table").item(0);
			this.setTable(DicePokerTable.valueOf(npcSpecificElement.getAttribute("value")));
		} catch(Exception ex) {
		}
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
	
	public DicePokerTable getTable() {
		return table;
	}

	public void setTable(DicePokerTable table) {
		this.table = table;
	}

	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getDescription() {
		switch (table) {
			case COPPER:
				return (UtilText.parse(this, "[npc.Name] is a relative novice at dice poker, and chooses to play in the 'copper' section of the Gambling Den's poker hall."));
			case SILVER:
				return (UtilText.parse(this, "[npc.Name] has quite a lot of experience at dice poker, and chooses to play in the 'silver' section of the Gambling Den's poker hall."));
			case GOLD:
				return (UtilText.parse(this, "[npc.Name] is an expert at dice poker, and chooses to play in the 'gold' section of the Gambling Den's poker hall."));
		}
		return "";
	}
	
	@Override
	public void endSex() {
		if(!isSlave()) {
			setPendingClothingDressing(true);
		}
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

	// Combat:
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		return null;
	}
	
}
