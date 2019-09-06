package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.SkinType;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.AlleywayAttackerDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.AlleywayAttackerDialogueCompanions;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.AlleywayProstituteDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.OutfitType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.66
 * @version 0.3.1
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
	
	public DominionAlleywayAttacker(Gender gender, boolean isImported, NPCGenerationFlag... generationFlags) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3, gender, Subspecies.DOG_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.DOMINION, PlaceType.DOMINION_BACK_ALLEYS, false,
				generationFlags);

		if(!isImported) {
			this.setLocation(Main.game.getPlayer(), true);
			
			boolean canalSpecies = false;
			AbstractPlaceType pt = Main.game.getActiveWorld().getCell(location).getPlace().getPlaceType();
			if(pt.equals(PlaceType.DOMINION_ALLEYS_CANAL_CROSSING)
					|| pt.equals(PlaceType.DOMINION_CANAL)
					|| pt.equals(PlaceType.DOMINION_CANAL_END)) {
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
					case ELEMENTAL_AIR:
					case ELEMENTAL_ARCANE:
					case ELEMENTAL_EARTH:
					case ELEMENTAL_FIRE:
					case ELEMENTAL_WATER:
					case HALF_DEMON:
						break;
						
					// Canals spawn only:
					case ALLIGATOR_MORPH:
						addToSubspeciesMap((canalSpecies?2000:0), gender, s, availableRaces);
						break;
					case SLIME:
						addToSubspeciesMap((canalSpecies?3000:0), gender, s, availableRaces);
						break;
					case RAT_MORPH:
						addToSubspeciesMap((canalSpecies?2500:0), gender, s, availableRaces);
						break;
						
					// Special spawns:
					case REINDEER_MORPH:
						if(Main.game.getSeason()==Season.WINTER && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter)) {
							addToSubspeciesMap((int) ((canalSpecies?50:1000)* Subspecies.getWorldSpecies().get(WorldType.DOMINION).get(s).getChanceMultiplier()), gender, s, availableRaces);
						}
						break;
						
					// Regular spawns:
					default:
						if(Subspecies.getWorldSpecies().get(WorldType.DOMINION).containsKey(s)) {
							addToSubspeciesMap((int) (canalSpecies?250:1000 * Subspecies.getWorldSpecies().get(WorldType.DOMINION).get(s).getChanceMultiplier()), gender, s, availableRaces);
						}
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces);
			
			if(Main.game.getCurrentWeather()!=Weather.MAGIC_STORM) {
				if(Math.random()<0.05) { //5% chance for the NPC to be a half-demon
					this.setBody(CharacterUtils.generateHalfDemonBody(this, gender, Subspecies.getFleshSubspecies(this), true));
				}
			}
			
			if(Math.random()<0.05 && this.isLegConfigurationAvailable(LegConfiguration.TAUR)) { //5% chance for the NPC to be a taur
				int taurLevel = Main.getProperties().taurFurryLevel;
				if(this.getRace()==Race.DEMON) {
					taurLevel = 3; // Demons should always be untouched
				}
				switch(taurLevel) {
					case 1:
						this.setLegConfiguration(LegConfiguration.TAUR, true);
						this.setAntennaType(AntennaType.NONE);
						this.setArmType(ArmType.HUMAN);
						this.setBreastType(BreastType.HUMAN);
						this.setEarType(EarType.HUMAN);
						this.setEyeType(EyeType.HUMAN);
						this.setFaceType(FaceType.HUMAN);
						this.setHairType(HairType.HUMAN);
						this.setHornType(HornType.NONE);
						this.setSkinType(SkinType.HUMAN);
						break;
					case 2:
						this.setLegConfiguration(LegConfiguration.TAUR, true);
						this.setAntennaType(Util.randomItemFrom(AntennaType.getAntennaTypes(this.getLegRace())));
						this.setArmType(ArmType.HUMAN);
						this.setBreastType(BreastType.HUMAN);
						this.setEarType(Util.randomItemFrom(EarType.getEarTypes(this.getLegRace())));
						this.setEyeType(Util.randomItemFrom(EyeType.getEyeTypes(this.getLegRace())));
						this.setFaceType(FaceType.HUMAN);
						this.setHairType(Util.randomItemFrom(HairType.getHairTypes(this.getLegRace())));
						this.setHornType(Util.randomItemFrom(HornType.getHornTypes(this.getLegRace())));
						this.setSkinType(SkinType.HUMAN);
						break;
					case 3:
						this.setLegConfiguration(LegConfiguration.TAUR, true);
						break;
					case 4:
						this.setLegConfiguration(LegConfiguration.TAUR, true);
						this.setAntennaType(Util.randomItemFrom(AntennaType.getAntennaTypes(this.getLegRace())));
						this.setArmType(Util.randomItemFrom(ArmType.getArmTypes(this.getLegRace())));
						this.setBreastType(Util.randomItemFrom(BreastType.getBreastTypes(this.getLegRace())));
						this.setEarType(Util.randomItemFrom(EarType.getEarTypes(this.getLegRace())));
						this.setEyeType(Util.randomItemFrom(EyeType.getEyeTypes(this.getLegRace())));
						this.setFaceType(Util.randomItemFrom(FaceType.getFaceTypes(this.getLegRace())));
						this.setHairType(Util.randomItemFrom(HairType.getHairTypes(this.getLegRace())));
						this.setHornType(Util.randomItemFrom(HornType.getHornTypes(this.getLegRace())));
						this.setSkinType(Util.randomItemFrom(SkinType.getSkinTypes(this.getLegRace())));
						break;
					default:
						break;
				}
			}
			
			setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(gender));
			
			setName(Name.getRandomTriplet(this.getRace()));
			this.setPlayerKnowsName(false);
			setDescription(UtilText.parse(this,
					"[npc.Name] is a resident of Dominion, who, for reasons of [npc.her] own, prowls the back alleys in search of victims to prey upon."));
			
			// PERSONALITY & BACKGROUND:
			
			CharacterUtils.setHistoryAndPersonality(this, true);
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				this.setHistory(Occupation.NPC_MUGGER);
				setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(gender));
			}
			
			// ADDING FETISHES:
			
			CharacterUtils.addFetishes(this);
			
			// BODY RANDOMISATION:
			
			CharacterUtils.randomiseBody(this, true);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			CharacterUtils.generateItemsInInventory(this);
			
			if(!Arrays.asList(generationFlags).contains(NPCGenerationFlag.NO_CLOTHING_EQUIP)) {
				this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			}
			CharacterUtils.applyMakeup(this, true);
			
			// Set starting perks based on the character's race
			initPerkTreeAndBackgroundPerks();

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
		if(this.getHistory()==Occupation.NPC_PROSTITUTE) {
			CharacterUtils.equipClothingFromOutfitType(this, OutfitType.PROSTITUTE, settings);
		} else {
			CharacterUtils.equipClothingFromOutfitType(this, OutfitType.MUGGER, settings);
		}
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public void hourlyUpdate() {
		if(this.getHistory()==Occupation.NPC_PROSTITUTE && this.getLocationPlace().getPlaceType().equals(PlaceType.ANGELS_KISS_BEDROOM)) {
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
//				System.out.println("partner generated for "+this.getNameIgnoresPlayerKnowledge()+" "+this.getLocation().toString()+", "+this.getLocationPlace().getPlaceType().getName());
				
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
	public DialogueNode getEncounterDialogue() {
		
		if(!isStormAttacker()) {
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
	public void applyEscapeCombatEffects() {
		if(isStormAttacker()) {
			Main.game.banishNPC(this);
		}
	}
	
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
	
	public boolean isStormAttacker() {
		AbstractPlaceType pt = this.getLocationPlace().getPlaceType();
		return this.getWorldLocation().equals(WorldType.DOMINION)
				&& !pt.equals(PlaceType.DOMINION_BACK_ALLEYS)
				&& !pt.equals(PlaceType.DOMINION_ALLEYS_CANAL_CROSSING)
				&& !pt.equals(PlaceType.DOMINION_CANAL)
				&& !pt.equals(PlaceType.DOMINION_CANAL_END)
				&& !Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())
				&& (!this.isSlave() || !this.getOwner().isPlayer());
	}
}
