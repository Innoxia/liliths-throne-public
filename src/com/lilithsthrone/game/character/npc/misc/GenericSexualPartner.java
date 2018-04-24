package com.lilithsthrone.game.character.npc.misc;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Season;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.2
 * @version 0.2.2
 * @author Innoxia
 */
public class GenericSexualPartner extends NPC {

	private static final long serialVersionUID = 1L;

	public GenericSexualPartner() {
		this(Gender.F_V_B_FEMALE, WorldType.EMPTY, new Vector2i(0, 0), false);
	}
	
	public GenericSexualPartner(boolean isImported) {
		this(Gender.F_V_B_FEMALE, WorldType.EMPTY, new Vector2i(0, 0), isImported);
	}
	
	public GenericSexualPartner(Gender gender, WorldType worldLocation, Vector2i location, boolean isImported) {
		super(null, "", 3, gender, RacialBody.DOG_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.DOMINION, PlaceType.DOMINION_BACK_ALLEYS, false);

		if(!isImported) {
			this.setWorldLocation(worldLocation);
			this.setLocation(location);
			
			setLevel(Util.random.nextInt(5) + 5);
			
			// RACE & NAME:
			
			Subspecies species = Subspecies.DOG_MORPH;
			
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
			
			Map<Subspecies, Integer> availableRaces = new HashMap<>();
			for(Subspecies s : Subspecies.values()) {
				switch(s) {
					// No spawn chance:
					case ANGEL:
					case DEMON:
					case IMP:
					case IMP_ALPHA:
						break;
						
					// Low spawn chance:
					case ALLIGATOR_MORPH:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case SLIME:
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
					case SLIME_BAT:
					case SLIME_RAT:
					case SLIME_WOLF:
					case SLIME_RABBIT:
						addToSubspeciesMap(1, gender, s, availableRaces);
						break;
					case RAT_MORPH:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;

					case BAT_MORPH:
						addToSubspeciesMap(1, gender, s, availableRaces);
						break;
					case HARPY:
						addToSubspeciesMap(4, gender, s, availableRaces);
						break;
					case HARPY_RAVEN:
						addToSubspeciesMap(1, gender, s, availableRaces);
						break;
					case HUMAN:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
						
					// Special spawns:
					case REINDEER_MORPH:
						if(Main.game.getSeason()==Season.WINTER && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter)) {
							addToSubspeciesMap(10, gender, s, availableRaces);
						}
						break;
						
					// Regular spawns:
					case CAT_MORPH:
						addToSubspeciesMap(25, gender, s, availableRaces);
						break;
					case COW_MORPH:
						addToSubspeciesMap(15, gender, s, availableRaces);
						break;
					case DOG_MORPH:
						addToSubspeciesMap(15, gender, s, availableRaces);
						break;
					case DOG_MORPH_DOBERMANN:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case DOG_MORPH_BORDER_COLLIE:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case HORSE_MORPH:
						addToSubspeciesMap(25, gender, s, availableRaces);
						break;
					case SQUIRREL_MORPH:
						addToSubspeciesMap(10, gender, s, availableRaces);
						break;
					case WOLF_MORPH:
						addToSubspeciesMap(25, gender, s, availableRaces);
						break;
					case RABBIT_MORPH:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case RABBIT_MORPH_LOP:
						addToSubspeciesMap(5, gender, s, availableRaces);
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
			this.setPlayerKnowsName(false);
			setDescription(UtilText.parse(this,
					"[npc.Name] is a resident of Dominion, who's currently only interested in having sex."));
			
			// PERSONALITY & BACKGROUND:
			
			CharacterUtils.setHistoryAndPersonality(this);
			
			// ADDING FETISHES:
			
			CharacterUtils.addFetishes(this);
			
			// BODY RANDOMISATION:
			
			CharacterUtils.randomiseBody(this);
			
			// INVENTORY:
			
			resetInventory();
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
	
			CharacterUtils.equipClothing(this, true, false);
			CharacterUtils.applyMakeup(this, true);
			
			// Set starting attributes based on the character's race
			for (Attribute a : RacialBody.valueOfRace(species.getRace()).getAttributeModifiers().keySet()) {
				attributes.put(a, RacialBody.valueOfRace(species.getRace()).getAttributeModifiers().get(a).getMinimum() + RacialBody.valueOfRace(species.getRace()).getAttributeModifiers().get(a).getRandomVariance());
			}
			
			setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
			setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		}
	}
	
	private void addToSubspeciesMap(int weight, Gender gender, Subspecies subspecies, Map<Subspecies, Integer> map) {
		if(weight!=0) {
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
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		this.setName(new NameTriplet("unknown male"));
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}

	@Override
	public void endSex(boolean applyEffects) {
	}
	

}