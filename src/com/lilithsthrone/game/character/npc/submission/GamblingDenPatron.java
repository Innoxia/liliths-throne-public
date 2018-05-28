package com.lilithsthrone.game.character.npc.submission;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.npcDialogue.SlaveDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.1
 * @version 0.2.5
 * @author Innoxia
 */
public class GamblingDenPatron extends NPC {

	private static final long serialVersionUID = 1L;

	public GamblingDenPatron() {
		this(Gender.F_V_B_FEMALE, false);
	}
	
	public GamblingDenPatron(Gender gender) {
		this(gender, false);
	}
	
	public GamblingDenPatron(boolean isImported) {
		this(Gender.F_V_B_FEMALE, isImported);
	}
	
	public GamblingDenPatron(Gender gender, boolean isImported) {
		super(null, "", 3, gender, RacialBody.ALLIGATOR_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_GAMBLING, false);

		if(!isImported) {
			// Set random level from 5 to 8:
			setLevel(5 + Util.random.nextInt(4));
			
			// RACE & NAME:
			
			Subspecies species = Subspecies.ALLIGATOR_MORPH;
			
			double humanChance = 0;
			
			if(Main.getProperties().humanEncountersLevel==1) {
				humanChance = 0.05f;
				
			} else if(Main.getProperties().humanEncountersLevel==2) {
				humanChance = 0.25f;
				
			} else if(Main.getProperties().humanEncountersLevel==3) {
				humanChance = 0.5f;
				
			} else if(Main.getProperties().humanEncountersLevel==4) {
				humanChance = 0.75f;
			}
			
			int slimeChance = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeQueenHelped) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_SLIME_QUEEN) ? 50 : 20;
			int otherSlimeChance = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeQueenHelped) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_SLIME_QUEEN) ? 2 : 1;
			
			Map<Subspecies, Integer> availableRaces = new HashMap<>();
			for(Subspecies s : Subspecies.values()) {
				switch(s) {
					// No chance of spawn:
					case IMP_ALPHA:
					case IMP:
					case ELEMENTAL_AIR:
					case ELEMENTAL_ARCANE:
					case ELEMENTAL_EARTH:
					case ELEMENTAL_FIRE:
					case ELEMENTAL_WATER:
						break;

					// Rare spawns:
					case ANGEL:
					case CAT_MORPH:
					case COW_MORPH:
					case DEMON:
					case DOG_MORPH:
					case DOG_MORPH_DOBERMANN:
					case DOG_MORPH_BORDER_COLLIE:
					case HARPY:
					case HARPY_RAVEN:
					case HORSE_MORPH:
					case HUMAN:
					case REINDEER_MORPH:
					case SQUIRREL_MORPH:
					case WOLF_MORPH:
					case RABBIT_MORPH:
					case RABBIT_MORPH_LOP:
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
					case SLIME_HORSE:
					case SLIME_IMP:
					case SLIME_REINDEER:
					case SLIME_SQUIRREL:
					case SLIME_WOLF:
					case SLIME_RAT:
					case SLIME_BAT:
					case SLIME_RABBIT:
						addToSubspeciesMap(otherSlimeChance, gender, s, availableRaces);
						break;
				}
			}
			
			if(gender.isFeminine()) {
				for(Entry<Subspecies, FurryPreference> entry : Main.getProperties().subspeciesFeminineFurryPreferencesMap.entrySet()) {
					if(entry.getValue() == FurryPreference.HUMAN) {
						availableRaces.remove(entry.getKey());
					}
				}
			} else {
				for(Entry<Subspecies, FurryPreference> entry : Main.getProperties().subspeciesMasculineFurryPreferencesMap.entrySet()) {
					if(entry.getValue() == FurryPreference.HUMAN) {
						availableRaces.remove(entry.getKey());
					}
				}
			}
			
			if(availableRaces.isEmpty() || Math.random()<humanChance) {
				setBody(gender, RacialBody.HUMAN, RaceStage.HUMAN);
				
			} else {
				species = Util.getRandomObjectFromWeightedMap(availableRaces);
				
				if(gender.isFeminine()) {
					switch(Main.getProperties().subspeciesFeminineFurryPreferencesMap.get(species)) {
						case HUMAN:
							setBody(gender, RacialBody.HUMAN, RaceStage.HUMAN);
							break;
						case MINIMUM:
							setBodyFromPreferences(1, gender, species);
							break;
						case REDUCED:
							setBodyFromPreferences(2, gender, species);
							break;
						case NORMAL:
							setBodyFromPreferences(3, gender, species);
							break;
						case MAXIMUM:
							setBody(gender, species, RaceStage.GREATER);
							break;
					}
				} else {
					switch(Main.getProperties().subspeciesMasculineFurryPreferencesMap.get(species)) {
						case HUMAN:
							setBody(gender, RacialBody.HUMAN, RaceStage.HUMAN);
							break;
						case MINIMUM:
							setBodyFromPreferences(1, gender, species);
							break;
						case REDUCED:
							setBodyFromPreferences(2, gender, species);
							break;
						case NORMAL:
							setBodyFromPreferences(3, gender, species);
							break;
						case MAXIMUM:
							setBody(gender, species, RaceStage.GREATER);
							break;
					}
				}
			}
			
			setSexualOrientation(RacialBody.valueOfRace(getRace()).getSexualOrientation(gender));
	
			setName(Name.getRandomTriplet(species.getRace()));
			this.setPlayerKnowsName(true);
			
			// PERSONALITY & BACKGROUND:
			
			CharacterUtils.setHistoryAndPersonality(this);
			
			// ADDING FETISHES:
			
			CharacterUtils.addFetishes(this);
			if(this.getBodyMaterial()==BodyMaterial.SLIME) {
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_SLIME_QUEEN) == Quest.SLIME_QUEEN_ONE) {
					this.addFetish(Fetish.FETISH_TRANSFORMATION_GIVING);
					this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
				}
			}
			
			// BODY RANDOMISATION:
			
			CharacterUtils.randomiseBody(this);
			
			// INVENTORY:
			
			resetInventory();
			inventory.setMoney(750 + Util.random.nextInt(750));
			CharacterUtils.generateItemsInInventory(this);
	
			CharacterUtils.equipClothing(this, true, false);
			CharacterUtils.applyMakeup(this, true);
			
			// Set starting attributes based on the character's race
			for (Attribute a : RacialBody.valueOfRace(species.getRace()).getAttributeModifiers().keySet()) {
				attributes.put(a, RacialBody.valueOfRace(species.getRace()).getAttributeModifiers().get(a).getMinimum() + RacialBody.valueOfRace(species.getRace()).getAttributeModifiers().get(a).getRandomVariance());
			}
			
			setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
			setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		}

		this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE);
	}
	
	private void addToSubspeciesMap(int weight, Gender gender, Subspecies subspecies, Map<Subspecies, Integer> map) {
		if(gender.isFeminine()) {
			if(Main.getProperties().subspeciesFeminineFurryPreferencesMap!=FurryPreference.HUMAN && Main.getProperties().subspeciesFemininePreferencesMap.get(subspecies).getValue()>0) {
				map.put(subspecies, weight*Main.getProperties().subspeciesFemininePreferencesMap.get(subspecies).getValue());
			}
		} else {
			if(Main.getProperties().subspeciesMasculineFurryPreferencesMap!=FurryPreference.HUMAN && Main.getProperties().subspeciesMasculinePreferencesMap.get(subspecies).getValue()>0) {
				map.put(subspecies, weight*Main.getProperties().subspeciesMasculinePreferencesMap.get(subspecies).getValue());
			}
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	private void setBodyFromPreferences(int i, Gender gender, Subspecies species) {
		int choice = Util.random.nextInt(i)+1;
		RaceStage raceStage = RaceStage.PARTIAL;
		
		if (choice == 1) {
			raceStage = RaceStage.PARTIAL;
		} else if (choice == 2) {
			raceStage = RaceStage.LESSER;
		} else {
			raceStage = RaceStage.GREATER;
		}
		
		setBody(gender, species, raceStage);
	}
	
	@Override
	public String getDescription() {
		return (UtilText.parse(this,
				"[npc.Name]'s days of prowling the tunnels of Submission and mugging innocent travellers are now over. Having run afoul of the law, [npc.she]'s now a slave, and is no more than [npc.her] owner's property."));
	}
	
	@Override
	public void endSex(boolean applyEffects) {
		if(applyEffects) {
			if(!isSlave()) {
				setPendingClothingDressing(true);
			}
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
