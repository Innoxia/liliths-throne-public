package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Season;
import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.npcDialogue.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.AlleywayAttackerDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.AlleywayAttackerDialogueCompanions;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.AlleywayProstituteDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.66
 * @version 0.2.6
 * @author Innoxia
 */
public class DominionAlleywayAttacker extends NPC {

	public DominionAlleywayAttacker() {
		this(Gender.F_V_B_FEMALE, false);
	}
	
	public DominionAlleywayAttacker(Gender gender) {
		this(gender, false);
	}
	
	public DominionAlleywayAttacker(boolean isImported) {
		this(Gender.F_V_B_FEMALE, isImported);
	}
	
	public DominionAlleywayAttacker(Gender gender, boolean isImported) {
		super(isImported, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3, gender, Subspecies.DOG_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.DOMINION, PlaceType.DOMINION_BACK_ALLEYS, false);

		if(!isImported) {
	
			this.setWorldLocation(Main.game.getPlayer().getWorldLocation());
			this.setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()));
			
			boolean canalSpecies = false;
			PlaceType pt = Main.game.getActiveWorld().getCell(location).getPlace().getPlaceType();
			if(pt == PlaceType.DOMINION_ALLEYS_CANAL_CROSSING
					|| pt == PlaceType.DOMINION_CANAL
					|| pt == PlaceType.DOMINION_CANAL_END) {
				canalSpecies = true;
			}
			
			// Set random level from 1 to 3:
			setLevel(Util.random.nextInt(3) + 1);
			
			// RACE & NAME:
			
			Map<Subspecies, Integer> availableRaces = new HashMap<>();
			for(Subspecies s : Subspecies.values()) {
				switch(s) {
					// No spawn chance:
					case ANGEL:
					case BAT_MORPH:
					case DEMON:
					case HARPY:
					case HARPY_RAVEN:
					case HARPY_BALD_EAGLE:
					case HUMAN:
					case IMP:
					case IMP_ALPHA:
					case FOX_ASCENDANT:
					case FOX_ASCENDANT_FENNEC:
					case ELEMENTAL_AIR:
					case ELEMENTAL_ARCANE:
					case ELEMENTAL_EARTH:
					case ELEMENTAL_FIRE:
					case ELEMENTAL_WATER:
						break;
						
					// Canals spawn only:
					case ALLIGATOR_MORPH:
						addToSubspeciesMap(canalSpecies?20:0, gender, s, availableRaces);
						break;
					case SLIME:
						addToSubspeciesMap(canalSpecies?15:0, gender, s, availableRaces);
						break;
					case RAT_MORPH:
						addToSubspeciesMap(canalSpecies?10:0, gender, s, availableRaces);
						break;
						
					// Special spawns:
					case REINDEER_MORPH:
						if(Main.game.getSeason()==Season.WINTER && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter)) {
							addToSubspeciesMap(canalSpecies?1:10, gender, s, availableRaces);
						}
						break;
						
					// Regular spawns:
					case CAT_MORPH:
						addToSubspeciesMap(canalSpecies?5:20, gender, s, availableRaces);
						break;
					case CAT_MORPH_LYNX:
						addToSubspeciesMap(canalSpecies?2:5, gender, s, availableRaces);
						break;
					case CAT_MORPH_LEOPARD_SNOW:
						addToSubspeciesMap(canalSpecies?2:5, gender, s, availableRaces);
						break;
					case CAT_MORPH_LEOPARD:
						addToSubspeciesMap(canalSpecies?2:5, gender, s, availableRaces);
						break;
					case CAT_MORPH_LION:
						addToSubspeciesMap(canalSpecies?2:5, gender, s, availableRaces);
						break;
					case CAT_MORPH_TIGER:
						addToSubspeciesMap(canalSpecies?2:5, gender, s, availableRaces);
						break;
					case CAT_MORPH_CHEETAH:
						addToSubspeciesMap(canalSpecies?2:5, gender, s, availableRaces);
						break;
					case CAT_MORPH_CARACAL:
						addToSubspeciesMap(canalSpecies?2:5, gender, s, availableRaces);
						break;
					case COW_MORPH:
						addToSubspeciesMap(canalSpecies?1:10, gender, s, availableRaces);
						break;
					case DOG_MORPH:
						addToSubspeciesMap(canalSpecies?3:12, gender, s, availableRaces);
						break;
					case DOG_MORPH_DOBERMANN:
						addToSubspeciesMap(canalSpecies?1:4, gender, s, availableRaces);
						break;
					case DOG_MORPH_BORDER_COLLIE:
						addToSubspeciesMap(canalSpecies?1:4, gender, s, availableRaces);
						break;
					case FOX_MORPH:
						addToSubspeciesMap(canalSpecies?1:10, gender, s, availableRaces);
						break;
					case FOX_MORPH_FENNEC:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case HORSE_MORPH:
						addToSubspeciesMap(canalSpecies?4:16, gender, s, availableRaces);
						break;
					case HORSE_MORPH_ZEBRA:
						addToSubspeciesMap(canalSpecies?1:4, gender, s, availableRaces);
						break;
					case SQUIRREL_MORPH:
						addToSubspeciesMap(canalSpecies?1:10, gender, s, availableRaces);
						break;
					case WOLF_MORPH:
						addToSubspeciesMap(canalSpecies?5:20, gender, s, availableRaces);
						break;
					case RABBIT_MORPH:
						addToSubspeciesMap(canalSpecies?1:3, gender, s, availableRaces);
						break;
					case RABBIT_MORPH_LOP:
						addToSubspeciesMap(canalSpecies?1:3, gender, s, availableRaces);
						break;
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces);
			
			setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(gender));
	
			setName(Name.getRandomTriplet(this.getRace()));
			this.setPlayerKnowsName(false);
			setDescription(UtilText.parse(this,
					"[npc.Name] is a resident of Dominion, who, for reasons of [npc.her] own, prowls the back alleys in search of victims to prey upon."));
			
			// PERSONALITY & BACKGROUND:
			
			CharacterUtils.setHistoryAndPersonality(this, true);
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				this.setHistory(Occupation.NPC_MUGGER);
			}
			
			// ADDING FETISHES:
			
			CharacterUtils.addFetishes(this);
			
			// BODY RANDOMISATION:
			
			CharacterUtils.randomiseBody(this);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
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
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Not needed
	}

	@Override
	public void equipClothing(boolean replaceUnsuitableClothing, boolean addWeapons, boolean addScarsAndTattoos) {
		CharacterUtils.equipClothing(this, replaceUnsuitableClothing, false);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public void hourlyUpdate() {
		if(this.getHistory()==Occupation.NPC_PROSTITUTE && this.getLocationPlace().getPlaceType()==PlaceType.ANGELS_KISS_BEDROOM) {
			// Remove client:
			List<NPC> charactersPresent = Main.game.getCharactersPresent(this.getWorldLocation(), this.getLocation());
			if(charactersPresent.size()>1) {
				for(NPC npc : charactersPresent) {
					if(npc instanceof GenericSexualPartner) {
	//					System.out.println("partner removed for "+slave.getName());
						Main.game.banishNPC(npc);
					}
				}
				
			} else if(Math.random()<0.33f) { // Add client:
				GenericSexualPartner partner;
				
				if(Math.random()<0.25f) {
					partner = new GenericSexualPartner(Gender.F_P_V_B_FUTANARI, this.getWorldLocation(), this.getLocation(), false);
				} else {
					partner = new GenericSexualPartner(Gender.M_P_MALE, this.getWorldLocation(), this.getLocation(), false);
				}
				try {
					Main.game.addNPC(partner, false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
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
	public DialogueNodeOld getEncounterDialogue() {
		PlaceType pt = Main.game.getActiveWorld().getCell(location).getPlace().getPlaceType();
		
		if(pt == PlaceType.DOMINION_BACK_ALLEYS
				|| pt == PlaceType.DOMINION_CANAL
				|| pt == PlaceType.DOMINION_ALLEYS_CANAL_CROSSING
				|| pt == PlaceType.DOMINION_CANAL_END) {
			
			if(this.getHistory()==Occupation.NPC_PROSTITUTE) {
				this.setPlayerKnowsName(true);
				return AlleywayProstituteDialogue.ALLEY_PROSTITUTE;
				
			} else {
				if(Main.game.getPlayer().getCompanions().isEmpty()) {
					return AlleywayAttackerDialogue.ALLEY_ATTACK;
				} else {
					return AlleywayAttackerDialogueCompanions.ALLEY_ATTACK;
				}
			}
			
		} else {
			return AlleywayAttackerDialogue.STORM_ATTACK;
		}
	}

	// Combat:

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if(this.getHistory()==Occupation.NPC_PROSTITUTE) {
			if (victory) {
				return new Response("", "", AlleywayProstituteDialogue.AFTER_COMBAT_VICTORY);
			} else {
				return new Response ("", "", AlleywayProstituteDialogue.AFTER_COMBAT_DEFEAT);
			}
		} else {
			if(Main.game.getPlayer().getCompanions().isEmpty()) {
				if (victory) {
					return new Response("", "", AlleywayAttackerDialogue.AFTER_COMBAT_VICTORY);
				} else {
					return new Response ("", "", AlleywayAttackerDialogue.AFTER_COMBAT_DEFEAT);
				}
			} else {
				if (victory) {
					return new Response("", "", AlleywayAttackerDialogueCompanions.AFTER_COMBAT_VICTORY);
				} else {
					return new Response ("", "", AlleywayAttackerDialogueCompanions.AFTER_COMBAT_DEFEAT);
				}
			}
		}
	}
}
