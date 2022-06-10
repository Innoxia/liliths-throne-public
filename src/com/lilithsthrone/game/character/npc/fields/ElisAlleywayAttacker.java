package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesSpawnRarity;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.4
 * @version 0.4.4
 * @author Innoxia
 */
public class ElisAlleywayAttacker extends NPC {

	public ElisAlleywayAttacker() {
		this(Gender.getGenderFromUserPreferences(false, false), false);
	}
	
	public ElisAlleywayAttacker(Gender gender) {
		this(gender, false);
	}
	
	public ElisAlleywayAttacker(boolean isImported) {
		this(Gender.F_V_B_FEMALE, isImported);
	}
	
	/**
	 * You must manually place this NPC in a location after creation!
	 */
	public ElisAlleywayAttacker(Gender gender, boolean isImported, NPCGenerationFlag... generationFlags) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3,
				null, null, null,
				new CharacterInventory(10),
				WorldType.getWorldTypeFromId("innoxia_fields_elis_town"),
				PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_alley"),
				false,
				generationFlags);

		if(!isImported) {
			// Set random level from 10 to 15:
			setLevel(Util.random.nextInt(6) + 10);
			
			// Race, name, personality/fetishes:
			
			Map<AbstractSubspecies, Integer> availableRaces = new HashMap<>();
			for(AbstractSubspecies s : Subspecies.getAllSubspecies()) {
				if(s.getSubspeciesOverridePriority()>0) { // Do not spawn demonic races, elementals, or youko
					continue;
				}
				Map<AbstractSubspecies, SubspeciesSpawnRarity> subMap = Subspecies.getWorldSpecies(WorldType.getWorldTypeFromId("innoxia_fields_elis_town"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_alley"), false);
				if(subMap.containsKey(s)) {
					AbstractSubspecies.addToSubspeciesMap((int) (10000 * subMap.get(s).getChanceMultiplier()), gender, s, availableRaces);
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces, true, true);
			
			if(Math.random()<Main.getProperties().halfDemonSpawnRate/100f && this.getSubspecies()!=Subspecies.SLIME) { // Don't convert slimes, as their getFleshSubspecies() can be of any subspecies
				this.setBody(Main.game.getCharacterUtils().generateHalfDemonBody(this, gender, this.getBody().getFleshSubspecies(), true), true);
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
			
//			Main.game.getCharacterUtils().setHistoryAndPersonality(this, true);
			
			this.setHistory(Occupation.NPC_MUGGER);
			if(Math.random()<0.25f) {
				this.addPersonalityTrait(PersonalityTrait.SLOVENLY);
			}
			
			Main.game.getCharacterUtils().addFetishes(this);
			
			Main.game.getCharacterUtils().randomiseBody(this, true);
			
			
			// Inventory:
			
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
		this.incrementMoney((int) (this.getInventory().getNonEquippedValue() * 1f));
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
	public void turnUpdate() {
		if(!this.isSlave()
				&& !Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())
				&& this.getCell()!=Main.game.getPlayerCell()
				&& this.getHomeCell().getPlace().getPlaceType()!=PlaceType.ANGELS_KISS_BEDROOM) {
			if(Main.game.isDayTime()) {
				this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE);
			} else {
				this.returnToHome();
			}
		}
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
						"[npc.NamePos] days of whoring [npc.herself] out in the back alleys of Elis are now over. Having run afoul of the law, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property."));
			} else if(this.getLocationPlace().getPlaceType().equals(PlaceType.ANGELS_KISS_BEDROOM)){
				return (UtilText.parse(this,
						"You first found [npc.name] in the alleyways of Elis, where [npc.she] was illegally selling [npc.her] body. You offered [npc.herHim] the chance to move and work out of Angel's Kiss; an offer which [npc.she] happily accepted."));
			} else {
				return (UtilText.parse(this,
						"[npc.Name] is a prostitute who whores [npc.herself] out in the backalleys of Elis."));
			}
			
		} else {
			if(this.isSlave()) {
				return (UtilText.parse(this,
						"[npc.NamePos] days of prowling the back alleys of Elis and mugging innocent travellers are now over. Having run afoul of the law, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property."));
			} else if(Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())){
				return (UtilText.parse(this,
						"[npc.NamePos] days of prowling the back alleys of Elis and mugging innocent travellers are now over. Having befriended [npc.herHim], you invited [npc.name] to move in with you and helped [npc.herHim] to start a new life."));
			} else {
				return (UtilText.parse(this,
						"[npc.Name] is a resident of Elis, who prowls the back alleys in search of innocent travellers to prey upon."));
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
		if(this.getHistory()==Occupation.NPC_PROSTITUTE) {
			this.setPlayerKnowsName(true);
			return DialogueManager.getDialogueFromId("innoxia_encounters_fields_elis_alleyway_prostitute_start");
			
		} else {
			return DialogueManager.getDialogueFromId("innoxia_encounters_fields_elis_alleyway_start");
		}
	}

	// Combat:

	@Override
	public void applyEscapeCombatEffects() {
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if(this.getHistory()==Occupation.NPC_PROSTITUTE) {
			if (victory) {
				return new Response("", "", DialogueManager.getDialogueFromId("innoxia_encounters_fields_elis_alleyway_prostitute_after_combat_victory"));
			} else {
				return new Response ("", "", DialogueManager.getDialogueFromId("innoxia_encounters_fields_elis_alleyway_prostitute_after_combat_defeat"));
			}
			
		} else {
			if (victory) {
				return new Response("", "", DialogueManager.getDialogueFromId("innoxia_encounters_fields_elis_alleyway_after_combat_victory"));
			} else {
				return new Response ("", "", DialogueManager.getDialogueFromId("innoxia_encounters_fields_elis_alleyway_after_combat_defeat"));
			}
		}
	}
	
	// Misc.:
	
	public void setAsProstitute() {
		this.removePersonalityTrait(PersonalityTrait.MUTE);
		this.setHistory(Occupation.NPC_PROSTITUTE);
		CharacterUtils.initProstitute(this);
		this.equipClothing();
	}
}
