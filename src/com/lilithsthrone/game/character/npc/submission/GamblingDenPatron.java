package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.attributes.Attribute;
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
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.npcDialogue.SlaveDialogue;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePokerTable;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.6
 * @version 0.2.6
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
		super(null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3, gender, RacialBody.ALLIGATOR_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_GAMBLING, false);
		
		this.table = table;
		
		if(!isImported) {
			// Set random level from 5 to 8:
			setLevel(5 + Util.random.nextInt(4));
			
			// RACE & NAME:
			
			int slimeChance = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeQueenHelped) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_SLIME_QUEEN) ? 50 : 20;
			int otherSlimeChance = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeQueenHelped) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_SLIME_QUEEN) ? 2 : 1;
			
			Map<Subspecies, Integer> availableRaces = new HashMap<>();
			for(Subspecies s : Subspecies.values()) {
				switch(s) {
					// No chance of spawn:
					case ANGEL:
					case IMP_ALPHA:
					case IMP:
					case ELEMENTAL_AIR:
					case ELEMENTAL_ARCANE:
					case ELEMENTAL_EARTH:
					case ELEMENTAL_FIRE:
					case ELEMENTAL_WATER:
					case FOX_ASCENDANT:
					case FOX_ASCENDANT_FENNEC:
						break;

					// Rare spawns:
					case CAT_MORPH:
					case COW_MORPH:
					case DEMON:
					case DOG_MORPH:
					case DOG_MORPH_DOBERMANN:
					case DOG_MORPH_BORDER_COLLIE:
					case HARPY:
					case HARPY_RAVEN:
					case HARPY_BALD_EAGLE:
					case HORSE_MORPH:
					case HORSE_MORPH_ZEBRA:
					case HUMAN:
					case REINDEER_MORPH:
					case SQUIRREL_MORPH:
					case WOLF_MORPH:
					case RABBIT_MORPH:
					case RABBIT_MORPH_LOP:
					case FOX_MORPH:
					case FOX_MORPH_FENNEC:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;

					case CAT_MORPH_CARACAL:
					case CAT_MORPH_CHEETAH:
					case CAT_MORPH_LEOPARD:
					case CAT_MORPH_LEOPARD_SNOW:
					case CAT_MORPH_LION:
					case CAT_MORPH_LYNX:
					case CAT_MORPH_TIGER:
						addToSubspeciesMap(1, gender, s, availableRaces);
						break;
						
					case BAT_MORPH:
						addToSubspeciesMap(10, gender, s, availableRaces);
						break;
						
					// Common spawns:
					case ALLIGATOR_MORPH:
						addToSubspeciesMap(30, gender, s, availableRaces);
						break;
					case RAT_MORPH:
						addToSubspeciesMap(40, gender, s, availableRaces);
						break;
					case SLIME:
						addToSubspeciesMap(slimeChance, gender, s, availableRaces);
						break;
					case SLIME_ALLIGATOR:
					case SLIME_ANGEL:
					case SLIME_CAT:
					case SLIME_COW:
					case SLIME_DEMON:
					case SLIME_DOG:
					case SLIME_DOG_DOBERMANN:
					case SLIME_DOG_BORDER_COLLIE:
					case SLIME_HARPY:
					case SLIME_HARPY_RAVEN:
					case SLIME_HARPY_BALD_EAGLE:
					case SLIME_HORSE:
					case SLIME_IMP:
					case SLIME_REINDEER:
					case SLIME_SQUIRREL:
					case SLIME_WOLF:
					case SLIME_RAT:
					case SLIME_BAT:
					case SLIME_RABBIT:
					case SLIME_FOX:
					case SLIME_FOX_FENNEC:
					case SLIME_CAT_CARACAL:
					case SLIME_CAT_CHEETAH:
					case SLIME_CAT_LEOPARD:
					case SLIME_CAT_LEOPARD_SNOW:
					case SLIME_CAT_LION:
					case SLIME_CAT_LYNX:
					case SLIME_CAT_TIGER:
						addToSubspeciesMap(otherSlimeChance, gender, s, availableRaces);
						break;
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces);
			
			setSexualOrientation(RacialBody.valueOfRace(getRace()).getSexualOrientation(gender));
	
			setName(Name.getRandomTriplet(this.getRace()));
			this.setPlayerKnowsName(true);
			
			// PERSONALITY & BACKGROUND:
			
			CharacterUtils.setHistoryAndPersonality(this, false);
			
			// ADDING FETISHES:
			
			CharacterUtils.addFetishes(this);
			this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.TWO_NEUTRAL);
			
			// BODY RANDOMISATION:
			
			CharacterUtils.randomiseBody(this);
			
			// INVENTORY:
			
			resetInventory();
			inventory.setMoney(750 + Util.random.nextInt(750));
			CharacterUtils.generateItemsInInventory(this);
	
			CharacterUtils.equipClothing(this, true, false);
			CharacterUtils.applyMakeup(this, true);
			
			// Set starting attributes based on the character's race
			for (Attribute a : RacialBody.valueOfRace(this.getRace()).getAttributeModifiers().keySet()) {
				attributes.put(a, RacialBody.valueOfRace(this.getRace()).getAttributeModifiers().get(a).getMinimum() + RacialBody.valueOfRace(this.getRace()).getAttributeModifiers().get(a).getRandomVariance());
			}
			
			setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
			setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		}

		this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE);
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
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}

	// Combat:
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		return null;
	}
	
}
