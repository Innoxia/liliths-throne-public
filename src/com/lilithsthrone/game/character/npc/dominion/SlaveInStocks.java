package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Season;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.SlaveJobSetting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.95
 * @version 0.2.6
 * @author Innoxia
 */
public class SlaveInStocks extends NPC {

	public SlaveInStocks() {
		this(Gender.F_V_B_FEMALE, false);
	}
	
	public SlaveInStocks(Gender gender) {
		this(gender, false);
	}
	
	public SlaveInStocks(boolean isImported) {
		this(Gender.F_V_B_FEMALE, isImported);
	}
	
	public SlaveInStocks(Gender gender, boolean isImported) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3, gender, Subspecies.DOG_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS, false);

		if(!isImported) {
			
			// Set random level from 1 to 3:
			setLevel(Util.random.nextInt(3) + 1);
			
			// RACE & NAME:
			
			Map<Subspecies, Integer> availableRaces = new HashMap<>();
			for(Subspecies s : Subspecies.values()) {
				switch(s) {
					// No spawn chance:
					case ANGEL:
					case DEMON:
					case HALF_DEMON:
					case LILIN:
					case ELDER_LILIN:
					case HARPY:
					case HARPY_RAVEN:
					case HARPY_BALD_EAGLE:
					case HUMAN:
					case IMP:
					case IMP_ALPHA:
					case FOX_ASCENDANT:
					case FOX_ASCENDANT_FENNEC:
					case SLIME:
					case ELEMENTAL_AIR:
					case ELEMENTAL_ARCANE:
					case ELEMENTAL_EARTH:
					case ELEMENTAL_FIRE:
					case ELEMENTAL_WATER:
						break;
					
					// Special spawns:
					case REINDEER_MORPH:
						if(Main.game.getSeason()==Season.WINTER && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter)) {
							addToSubspeciesMap(10, gender, s, availableRaces);
						}
						break;
						
					// Rare spawns:
					case ALLIGATOR_MORPH:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case BAT_MORPH:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case RAT_MORPH:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
						
					// Common spawns:
					case CAT_MORPH:
						addToSubspeciesMap(20, gender, s, availableRaces);
						break;
					case CAT_MORPH_LYNX:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case CAT_MORPH_LEOPARD_SNOW:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case CAT_MORPH_LEOPARD:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case CAT_MORPH_LION:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case CAT_MORPH_TIGER:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case CAT_MORPH_CHEETAH:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case CAT_MORPH_CARACAL:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case COW_MORPH:
						addToSubspeciesMap(10, gender, s, availableRaces);
						break;
					case DOG_MORPH:
						addToSubspeciesMap(20, gender, s, availableRaces);
						break;
					case DOG_MORPH_DOBERMANN:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case DOG_MORPH_BORDER_COLLIE:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case FOX_MORPH:
						addToSubspeciesMap(10, gender, s, availableRaces);
						break;
					case FOX_MORPH_FENNEC:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case HORSE_MORPH:
						addToSubspeciesMap(20, gender, s, availableRaces);
						break;
					case HORSE_MORPH_ZEBRA:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case SQUIRREL_MORPH:
						addToSubspeciesMap(10, gender, s, availableRaces);
						break;
					case WOLF_MORPH:
						addToSubspeciesMap(20, gender, s, availableRaces);
						break;
					case RABBIT_MORPH:
						addToSubspeciesMap(3, gender, s, availableRaces);
						break;
					case RABBIT_MORPH_LOP:
						addToSubspeciesMap(3, gender, s, availableRaces);
						break;
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces);
			
			setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(gender));
	
			setName(Name.getRandomTriplet(this.getRace()));
			this.setPlayerKnowsName(false);
			setDescription(UtilText.parse(this,
					"[npc.Name] is a slave, who, for one reason or another, has been locked into the stocks for public use."));
			
			// PERSONALITY & BACKGROUND:
			
			if(this.isFeminine()) {
				if(Math.random()>0.5f) {
					this.setHistory(Occupation.NPC_PROSTITUTE);
					setSexualOrientation(SexualOrientation.AMBIPHILIC);
					setName(Name.getRandomProstituteTriplet());
					useItem(AbstractItemType.generateItem(ItemType.PROMISCUITY_PILL), this, false);
				} else {
					this.setHistory(Occupation.NPC_MUGGER);
				}
				
			} else {
				this.setHistory(Occupation.NPC_MUGGER);
			}
			
			// ADDING FETISHES:
			
			CharacterUtils.addFetishes(this);
			
			// BODY RANDOMISATION:
			
			CharacterUtils.randomiseBody(this, true);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);

			equipClothing(true, true, true, true);
			
			CharacterUtils.applyMakeup(this, true);
			
			this.addSlaveJobSettings(SlaveJobSetting.SEX_ORAL);
			
			if(Math.random()>0.4f) {
				this.addSlaveJobSettings(SlaveJobSetting.SEX_ANAL);
			}
			
			if(!this.hasVagina()) {
				this.addSlaveJobSettings(SlaveJobSetting.SEX_ANAL);
			} else {
				if(Math.random()>0.4f) {
					this.addSlaveJobSettings(SlaveJobSetting.SEX_VAGINAL);
				}
			}
			
			this.setPlayerKnowsName(true);
			
			setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
			setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		}
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
	public void equipClothing(boolean replaceUnsuitableClothing, boolean addWeapons, boolean addScarsAndTattoos, boolean addAccessories) {
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NECK_SLAVE_COLLAR), true, this);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getDescription() {
		if(this.getHistory()==Occupation.NPC_PROSTITUTE) {
			if(this.isSlave()) {
				return (UtilText.parse(this,
						"[npc.NamePos] days of whoring [npc.herself] out in the back alleys of Dominion are now over. Having run afoul of the law, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property."));
			} else {
				return (UtilText.parse(this,
						"[npc.Name] is a prostitute who whores [npc.herself] out in the backalleys of Dominion."));
			}
			
		} else {
			if(this.isSlave()) {
				return (UtilText.parse(this,
						"[npc.NamePos] days of prowling the back alleys of Dominion and mugging innocent travellers are now over. Having run afoul of the law, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property."));
			} else {
				return (UtilText.parse(this,
						"[npc.Name] is a resident of Dominion, who prowls the back alleys in search of innocent travellers to mug and rape."));
			}
		}
	}

	@Override
	public boolean isClothingStealable() {
		return false;
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
		return null; //TODO
	}
}
