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
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * A randomly-generated Patrol Enforcer. Spawns with the 'patrol' outfit (ATHLETIC).
 * 
 * @since 0.3.6.2
 * @version 0.3.6.2
 * @author Innoxia
 */
public class EnforcerPatrol extends NPC {

	public EnforcerPatrol() {
		this(Occupation.NPC_ENFORCER_PATROL_CONSTABLE, Gender.F_V_B_FEMALE, WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CELLS_OFFICE, false);
	}
	
	public EnforcerPatrol(boolean isImported) {
		this(Occupation.NPC_ENFORCER_PATROL_CONSTABLE, Gender.F_V_B_FEMALE, WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CELLS_OFFICE, isImported);
	}
	
	public EnforcerPatrol(Occupation occupation, Gender gender, AbstractWorldType world, AbstractPlaceType place, boolean isImported, NPCGenerationFlag... generationFlags) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				5, gender, null, null,
				new CharacterInventory(10), world, place, false,
				generationFlags);

		if(!isImported) {
			setLevel(Util.random.nextInt(5)+3);
			
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
					case FOX_ASCENDANT_ARCTIC:
					case FOX_ASCENDANT_FENNEC:
					case ELEMENTAL_AIR:
					case ELEMENTAL_ARCANE:
					case ELEMENTAL_EARTH:
					case ELEMENTAL_FIRE:
					case ELEMENTAL_WATER:
					case HALF_DEMON:
					case ALLIGATOR_MORPH:
					case SLIME:
					case RAT_MORPH:
					case REINDEER_MORPH:
						break;
					// Regular spawns:
					default:
						if(Subspecies.getWorldSpecies(WorldType.DOMINION, false).containsKey(s)) {
							Subspecies.addToSubspeciesMap((int) (1000 * Subspecies.getWorldSpecies(WorldType.DOMINION, false).get(s).getChanceMultiplier()), gender, s, availableRaces);
						}
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces, true, false);
			
			if(Math.random()<0.05) { //5% chance for the NPC to be a half-demon
				this.setBody(CharacterUtils.generateHalfDemonBody(this, gender, Subspecies.getFleshSubspecies(this), true), true);
			}
			// Taur chance not included
			
			setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(gender));
			
			
			setName(Name.getRandomTriplet(this.getRace()));
			
			this.setPlayerKnowsName(false);
			
			this.setHistory(occupation);
			
			CharacterUtils.addFetishes(this, Fetish.FETISH_CROSS_DRESSER, Fetish.FETISH_EXHIBITIONIST); // Do not allow cross-dressing or exhibitionist, as otherwise it will mess with uniforms.
			
			CharacterUtils.randomiseBody(this, true);
			
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			CharacterUtils.generateItemsInInventory(this);
			
			if(!Arrays.asList(generationFlags).contains(NPCGenerationFlag.NO_CLOTHING_EQUIP)) {
				this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			}
			CharacterUtils.applyMakeup(this, true);
			
			initPerkTreeAndBackgroundPerks(); // Set starting perks based on the character's race
			
			this.setEssenceCount(TFEssence.ARCANE, 100);
			
			initHealthAndManaToMax();
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
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		
		CharacterUtils.equipClothingFromOutfitType(this, OutfitType.ATHLETIC, settings);
		
		// Do not wear gas masks:
		AbstractClothing mouthClothing = this.getClothingInSlot(InventorySlot.MOUTH);
		if(mouthClothing!=null && mouthClothing.getClothingType().equals(ClothingType.getClothingTypeFromId("dsg_eep_tacequipset_gmask"))) {
			this.unequipClothingIntoVoid(mouthClothing, true, this);
		}
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getDescription() {
		return UtilText.parse(this, "A member of the Enforcers' Frontline Patrol division, [npc.name] is expected to carry out a wide variety of day-to-day policing duties."); 
	}
	
	@Override
	public void endSex() {
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
		return null;
	}

	// Combat:
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		return null; //TODO
	}
}
