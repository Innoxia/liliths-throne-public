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
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.CombatBehaviour;
import com.lilithsthrone.game.combat.moves.CombatMove;
import com.lilithsthrone.game.combat.moves.CombatMoveType;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.EnforcerAlleywayDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.game.sex.sexActions.dominion.EnforcerPatrolSA;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * A randomly-generated Patrol Enforcer. Spawns with the 'patrol' outfit (ATHLETIC).
 * 
 * @since 0.3.6.2
 * @version 0.3.8.3
 * @author Innoxia
 */
public class EnforcerPatrol extends NPC {

	public EnforcerPatrol() {
		this(Occupation.NPC_ENFORCER_PATROL_CONSTABLE, Gender.F_V_B_FEMALE, false);
	}
	
	public EnforcerPatrol(boolean isImported) {
		this(Occupation.NPC_ENFORCER_PATROL_CONSTABLE, Gender.F_V_B_FEMALE, isImported);
	}

	public EnforcerPatrol(Occupation occupation, Gender gender) {
		this(occupation, gender, false);
	}
	
	public EnforcerPatrol(Occupation occupation, Gender gender, boolean isImported, NPCGenerationFlag... generationFlags) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				5, gender, null, null,
				new CharacterInventory(10), WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CELLS_OFFICE, false,
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
					case SLIME:
					case REINDEER_MORPH:
						break;
					// Regular spawns:
					default:
						if(Subspecies.getWorldSpecies(WorldType.DOMINION, false).containsKey(s)) {
							Subspecies.addToSubspeciesMap((int) (5 * Subspecies.getWorldSpecies(WorldType.DOMINION, false).get(s).getChanceMultiplier()), gender, s, availableRaces);
						} else if(Subspecies.getWorldSpecies(WorldType.SUBMISSION, false).containsKey(s)) {
							Subspecies.addToSubspeciesMap((int) (Subspecies.getWorldSpecies(WorldType.SUBMISSION, false).get(s).getChanceMultiplier()), gender, s, availableRaces);
						}
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces, true, false);
			
			if(Math.random()<Main.getProperties().halfDemonSpawnRate/100f) { // Half-demon spawn rate
				this.setBody(CharacterUtils.generateHalfDemonBody(this, gender, Subspecies.getFleshSubspecies(this), true), true);
			}
			if(Math.random()<Main.getProperties().taurSpawnRate/100f
					&& this.getLegConfiguration()!=LegConfiguration.TAUR // Do not reset this charatcer's taur body if they spawned as a taur (as otherwise subspecies-specific settings get overridden by global taur settings)
					&& this.isLegConfigurationAvailable(LegConfiguration.TAUR)) { // Taur spawn rate
				CharacterUtils.applyTaurConversion(this);
			}
			
			setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(gender));
			
			setName(Name.getRandomTriplet(this.getRace()));
			
			this.setPlayerKnowsName(false);
			
			this.setHistory(occupation);
			
			CharacterUtils.addFetishes(this, Fetish.FETISH_CROSS_DRESSER, Fetish.FETISH_EXHIBITIONIST); // Do not allow cross-dressing or exhibitionist, as otherwise it will mess with uniforms.
			
			List<Fetish> fetishesForNonNegative = Util.newArrayListOfValues(
					Fetish.FETISH_ANAL_GIVING,
					Fetish.FETISH_ORAL_RECEIVING,
					Fetish.FETISH_VAGINAL_GIVING,
					Fetish.FETISH_VAGINAL_RECEIVING,
					Fetish.FETISH_PENIS_GIVING,
					Fetish.FETISH_PENIS_RECEIVING,
					Fetish.FETISH_DOMINANT);
			for(Fetish fetish : fetishesForNonNegative) {
				if(this.getFetishDesire(fetish).isNegative()) {
					this.setFetishDesire(fetish, FetishDesire.TWO_NEUTRAL);
				}
			}
			
			CharacterUtils.randomiseBody(this, true);
			
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			CharacterUtils.generateItemsInInventory(this);
			this.addClothing(Main.game.getItemGen().generateClothing("innoxia_penis_condom", PresetColour.CLOTHING_PURPLE_DARK, false), 5, false, false);
			
			if(!Arrays.asList(generationFlags).contains(NPCGenerationFlag.NO_CLOTHING_EQUIP)) {
				this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			}
			CharacterUtils.applyMakeup(this, true);
			
			initPerkTreeAndBackgroundPerks(); // Set starting perks based on the character's race
			
			this.removePersonalityTrait(PersonalityTrait.MUTE);
			
			this.setEssenceCount(100);

			this.setLocation(Main.game.getPlayer(), false); // Move to player location
			
			for(CombatMove move : new ArrayList<>(this.getEquippedMoves())) {
				if(move.getType()==CombatMoveType.TEASE) {
					this.unequipMove(move.getIdentifier());
				}
			}
			
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
	
	/**
	 * Equips this Enforcer with a baton in the primary slot, and the weapon as defined by offhandWeaponID in the offhand slot.
	 */
	public void setWeapons(String offhandWeaponID) {
		this.unequipAllWeaponsIntoVoid(false);
		this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("dsg_eep_enbaton_enbaton"));
		this.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(offhandWeaponID));
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
	public String getGenericName() {
		if(this.getHistory()==Occupation.NPC_ENFORCER_PATROL_CONSTABLE) {
			return UtilText.parse(this, "Constable [npc.surname]");
		} else if(this.getHistory()==Occupation.NPC_ENFORCER_PATROL_SERGEANT) {
			return UtilText.parse(this, "Sergeant [npc.surname]");
		} else if(this.getHistory()==Occupation.NPC_ENFORCER_PATROL_INSPECTOR) {
			return UtilText.parse(this, "Inspector [npc.surname]");
		}
		return UtilText.parse(this, "Enforcer");
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
	public CombatBehaviour getCombatBehaviour() {
		return CombatBehaviour.ATTACK; // Enforcers just use attacks
	}
	
	@Override
	public void applyEscapeCombatEffects() {
		EnforcerAlleywayDialogue.banishEnforcers(false);
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", EnforcerAlleywayDialogue.AFTER_COMBAT_VICTORY);
		} else {
			return new Response ("", "", EnforcerAlleywayDialogue.AFTER_COMBAT_DEFEAT);
		}
	}
	
	// Sex:
	
	@Override
	public List<Class<?>> getUniqueSexClasses() {
		return Util.newArrayListOfValues(EnforcerPatrolSA.class);
	}
	
	@Override
	public Value<AbstractItem, String> getSexItemToUse(GameCharacter partner) {
		return null; // Do not want Enforcers using items during sex
	}
	
//	@Override
//	public Value<AbstractClothing, String> getSexClothingToSelfEquip(GameCharacter partner, boolean inQuickSex) {
//		if(Main.game.isInSex() && (inQuickSex || !Main.sex.getInitialSexManager().isPartnerWantingToStopSex(this))) {
//			if(this.hasPenisIgnoreDildo() && this.getClothingInSlot(InventorySlot.PENIS)==null) {
//				AbstractClothing condom = null;
//				for(AbstractClothing clothing : this.getAllClothingInInventory().keySet()) {
//					if(clothing.isCondom()) {
//						condom = clothing;
//						break;
//					}
//				}
//				if(condom!=null && this.isAbleToEquip(condom, inQuickSex, this)) {
//					return new Value<>(condom, UtilText.parse(this, "[npc.Name] grabs a "+condom.getName()+" from out of [npc.her] inventory..."));
//				}
//			}
//		}
//		return null;
//	}
}
