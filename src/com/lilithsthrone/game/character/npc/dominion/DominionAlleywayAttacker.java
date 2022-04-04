package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesSpawnRarity;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.AlleywayAttackerDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.AlleywayProstituteDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.StormStreetAttackerDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.66
 * @version 0.3.5.5
 * @author Innoxia
 */
public class DominionAlleywayAttacker extends NPC {

	public DominionAlleywayAttacker() {
		this(Gender.getGenderFromUserPreferences(false, false), false);
	}
	
	public DominionAlleywayAttacker(Gender gender) {
		this(gender, false);
	}
	
	public DominionAlleywayAttacker(boolean isImported) {
		this(Gender.F_V_B_FEMALE, isImported);
	}
	
	/**
	 * You must manually place this NPC in a location after creation!
	 */
	public DominionAlleywayAttacker(Gender gender, boolean isImported, NPCGenerationFlag... generationFlags) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3,
				null, null, null,
				new CharacterInventory(10), WorldType.DOMINION, PlaceType.DOMINION_BACK_ALLEYS, false,
				generationFlags);

		if(!isImported) {
			boolean canalSpecies = false;
			AbstractPlaceType pt = Main.game.getPlayerCell().getPlace().getPlaceType();
			if(pt.equals(PlaceType.DOMINION_ALLEYS_CANAL_CROSSING)
					|| pt.equals(PlaceType.DOMINION_CANAL)
					|| pt.equals(PlaceType.DOMINION_CANAL_END)) {
				canalSpecies = true;
			}
			
			// Set random level from 1 to 3:
			setLevel(Util.random.nextInt(3) + 1);
			
			// RACE & NAME:
			
			Map<AbstractSubspecies, Integer> availableRaces = new HashMap<>();
			for(AbstractSubspecies s : Subspecies.getAllSubspecies()) {
				if(s.getSubspeciesOverridePriority()>0) { // Do not spawn demonic races, elementals, or youko
					continue;
				}
				if(s==Subspecies.REINDEER_MORPH) {
					if(Main.game.getSeason()==Season.WINTER && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter)) {
						AbstractSubspecies.addToSubspeciesMap((int) ((canalSpecies?500:10000)* SubspeciesSpawnRarity.THREE_UNCOMMON.getChanceMultiplier()), gender, s, availableRaces);
					}
					
				} else {
					if(Subspecies.getWorldSpecies(WorldType.DOMINION, pt, false).containsKey(s)) {
						AbstractSubspecies.addToSubspeciesMap((int) ((canalSpecies?2500:10000) * Subspecies.getWorldSpecies(WorldType.DOMINION, pt, false).get(s).getChanceMultiplier()), gender, s, availableRaces);
					}
					if(canalSpecies && Subspecies.getWorldSpecies(WorldType.SUBMISSION, pt, false).containsKey(s)) {
//						System.out.println(s.getName(null));
						AbstractSubspecies.addToSubspeciesMap((int) (10000 * Subspecies.getWorldSpecies(WorldType.SUBMISSION, pt, false).get(s).getChanceMultiplier()), gender, s, availableRaces);
					}
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces, true, true);
			
			if(Main.game.getCurrentWeather()!=Weather.MAGIC_STORM || canalSpecies || pt==PlaceType.DOMINION_BACK_ALLEYS) {
				if(Math.random()<Main.getProperties().halfDemonSpawnRate/100f && this.getSubspecies()!=Subspecies.SLIME) { // Don't convert slimes, as their getFleshSubspecies() can be of any non-Dominion subspecies
					this.setBody(Main.game.getCharacterUtils().generateHalfDemonBody(this, gender, this.getBody().getFleshSubspecies(), true), true);
				}
			}
			
			if(Math.random()<Main.getProperties().taurSpawnRate/100f
					&& this.getLegConfiguration()!=LegConfiguration.QUADRUPEDAL) { // Do not reset this character's taur body if they spawned as a taur (as otherwise subspecies-specific settings get overridden by global taur settings)
				// Check for race's leg type as taur, otherwise NPCs which spawn with human legs won't be affected by taur conversion rate:
				if(this.getRace().getRacialBody().getLegType().isLegConfigurationAvailable(LegConfiguration.QUADRUPEDAL)) {
					this.setLegType(this.getRace().getRacialBody().getLegType());
					Main.game.getCharacterUtils().applyTaurConversion(this);
				}
			}
			
			setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(gender));
			
			setName(Name.getRandomTriplet(this.getRace()));
			this.setPlayerKnowsName(false);
			setDescription(UtilText.parse(this,
					"[npc.Name] is a resident of Dominion, who, for reasons of [npc.her] own, prowls the back alleys in search of victims to prey upon."));
			
			// PERSONALITY & BACKGROUND:
			
			Main.game.getCharacterUtils().setHistoryAndPersonality(this, true);
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				this.setHistory(Occupation.NPC_MUGGER);
				setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(gender));
			}
			
			// ADDING FETISHES:
			
			Main.game.getCharacterUtils().addFetishes(this);
			
			// BODY RANDOMISATION:
			
			Main.game.getCharacterUtils().randomiseBody(this, true);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			Main.game.getCharacterUtils().generateItemsInInventory(this);
			
			if(!Arrays.asList(generationFlags).contains(NPCGenerationFlag.NO_CLOTHING_EQUIP)) {
				this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			}
			Main.game.getCharacterUtils().applyMakeup(this, true);
			
			// Set starting perks based on the character's race
			initPerkTreeAndBackgroundPerks();
			this.setStartingCombatMoves();
			loadImages();

			initHealthAndManaToMax();
			
			if(this.isStormAttacker() && !this.isVulnerableToArcaneStorm()) { // NPCs spawned during a storm should be vulnerable to it.
				this.addSpecialPerk(Perk.SPECIAL_ARCANE_ALLERGY);
			}
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
		this.incrementMoney((int) (this.getInventory().getNonEquippedValue() * 0.5f));
		this.clearNonEquippedInventory(false);
		Main.game.getCharacterUtils().generateItemsInInventory(this);
		
		if(this.getHistory()==Occupation.NPC_PROSTITUTE) {
			Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.PROSTITUTE, settings);
		} else {
			Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.MUGGER, settings);
		}
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public void hourlyUpdate() {
		if(this.getHistory()==Occupation.NPC_PROSTITUTE
				&& Main.game.isLipstickMarkingEnabled()
				&& !this.isSlave()
				&& !Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())
				&& this.getLipstick().getPrimaryColour()!=PresetColour.COVERING_NONE) {
			this.addHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
		}
		if(this.getHistory()==Occupation.NPC_PROSTITUTE && this.getLocationPlace().getPlaceType().equals(PlaceType.ANGELS_KISS_BEDROOM)) { //TODO need to move this to Angel's Kiss
			// Remove client:
			List<NPC> charactersPresent = new ArrayList<>(Main.game.getCharactersPresent(this.getWorldLocation(), this.getLocation()));
			charactersPresent.removeAll(Main.game.getPlayer().getCompanions());
			if(charactersPresent.size()>1) {
				for(NPC npc : charactersPresent) {
					if(npc instanceof GenericSexualPartner) {
//						System.out.println("partner removed for "+this.getName());
//						System.out.println(npc.getId());
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
					Main.game.addNPC(partner, false, true);
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
			} else if(this.getLocationPlace().getPlaceType().equals(PlaceType.ANGELS_KISS_BEDROOM)){
				return (UtilText.parse(this,
						"You first found [npc.name] in the alleyways of Dominion, where [npc.she] was illegally selling [npc.her] body. You offered [npc.herHim] the chance to move and work out of Angel's Kiss; an offer which [npc.she] happily accepted."));
			} else {
				return (UtilText.parse(this,
						"[npc.Name] is a prostitute who whores [npc.herself] out in the backalleys of Dominion."));
			}
			
		} else {
			if(this.isSlave()) {
				return (UtilText.parse(this,
						"[npc.NamePos] days of prowling the back alleys of Dominion and mugging innocent travellers are now over. Having run afoul of the law, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property."));
			} else if(Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())){
				return (UtilText.parse(this,
						"[npc.NamePos] days of prowling the back alleys of Dominion and mugging innocent travellers are now over. Having befriended [npc.herHim], you invited [npc.name] to move in with you and helped [npc.herHim] to start a new life."));
			} else {
				return (UtilText.parse(this,
						"[npc.Name] is a resident of Dominion, who prowls the back alleys in search of innocent travellers to prey upon."));
			}
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
				return AlleywayAttackerDialogue.ALLEY_ATTACK;
			}
			
		} else {
			return StormStreetAttackerDialogue.STORM_ATTACK;
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
			
		} else if(isStormAttacker()) {
			if (victory) {
				return new Response("", "", StormStreetAttackerDialogue.AFTER_COMBAT_VICTORY);
			} else {
				return new Response ("", "", StormStreetAttackerDialogue.AFTER_COMBAT_DEFEAT);
			}
			
		} else {
			if (victory) {
				return new Response("", "", AlleywayAttackerDialogue.AFTER_COMBAT_VICTORY);
			} else {
				return new Response ("", "", AlleywayAttackerDialogue.AFTER_COMBAT_DEFEAT);
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
