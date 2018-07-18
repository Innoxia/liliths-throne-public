package com.lilithsthrone.game.sex;

import java.lang.reflect.Field;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionPresets;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.SexActionUtility;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.BaseColour;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * Singleton enforced by Enum Call initialiseCombat() before using.
 * Then call startSex(), which returns the starting DialogueNode.
 * 
 * Lasciate ogni speranza, voi ch'entrate.
 *
 * @since 0.1.0
 * @version 0.2.7
 * @author Innoxia
 */
public enum Sex {
	SEX;

	/*
	 * How it works: Call initialiseSex(GameCharacter partner, boolean
	 * isDom(Main.game.getPlayer())) first. This sets starting arousal values based on player and
	 * partner composure. (0 composure starts you at 50 arousal)
	 *
	 * Then call startSex(), which returns the starting DialogueNode After that,
	 * the Sex class automatically generates responses and descriptions based on
	 * the player and their partner.
	 *
	 * Things to account for in sex:

		Masochist/Submissive/Exhibitionist
		Femininity
		Face
		Hair
		Wings
		Tail
		Horns
		Hip size
		Skin/fur colour
		Pregnancy
		Breasts
			Race
			Rows
			Size
			Lactation
			Capacity
			Creampied
		Ass
			Race
			Size
			Wetness
			Capacity
			Virgin
			Creampied
		Vagina
			Race
			Wetness
			Capacity
			Virgin
			Clit size
			Creampied
		Penis
			Race
			Size
			Testicle size
			Capacity
			Creampied

	 * TODO
	 * finishSex() method applies after sex stat changes, increment magical tally tattoos, etc.
	 */

	// Sex variables:

	private static boolean sexStarted = false;
	private static boolean consensual;
	private static boolean subHasEqualControl;
	private static boolean publicSex;

	private static Map<GameCharacter, SexPositionSlot> dominants;
	private static Map<GameCharacter, SexPositionSlot> submissives;
	private static List<GameCharacter> allParticipants;
	private static NPC activePartner;
	private static GameCharacter characterPerformingAction;
	private static SexManagerInterface sexManager;
	private static SexManagerInterface initialSexManager;
	private static String sexDescription, unequipClothingText, dyeClothingText, usingItemText;
	private static AbstractClothing clothingBeingRemoved;
	private static StringBuilder sexSB = new StringBuilder();
	private static List<SexActionInterface> availableSexActionsPlayer, miscActionsPlayer, selfActionsPlayer, sexActionsPlayer, positionActionsPlayer;
	private static List<SexActionInterface> repeatActionsPlayer;
	private static List<SexActionInterface> availableSexActionsPartner;
	
	private static Map<GameCharacter, SexPace> forceSexPaceMap;

	private static Map<GameCharacter, Set<SexAreaInterface>> initialPenetrations;
	
	// Actions that are currently available from all SexPositionSlots. First key is character whose actions they are, second key is target.
	private static Map<GameCharacter, Map<GameCharacter, Set<SexActionInterface>>> actionsAvailable;
	private static Map<GameCharacter, Map<GameCharacter, Set<SexActionInterface>>> orgasmActionsAvailable;
	
	private static DialogueNodeOld postSexDialogue;

	private static SexActionInterface lastUsedPlayerAction, lastUsedPartnerAction;
	
	// Tracking statuses:
	
	private static Map<GameCharacter, Map<SexAreaInterface, Set<LubricationType>>> wetAreas;
	private static Map<GameCharacter, Set<SexAreaOrifice>> areasCurrentlyStretching;
	private static Map<GameCharacter, Set<SexAreaOrifice>> areasStretched;
	private static Map<GameCharacter, Set<SexAreaOrifice>> areasTooLoose;
//	private static Map<GameCharacter, Set<OrificeType>> areasCummedIn;
	
	private static Map<GameCharacter, List<CoverableArea>> areasExposed;

	private static Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>>> ongoingActionsMap;
	
	private static Map<GameCharacter, Integer> orgasmCountMap;

	private static Map<GameCharacter, Map<GameCharacter, Integer>> cummedInsideMap;

	private static Map<GameCharacter, List<SexAreaInterface>> requestsBlocked;
	
	// Clothes:

	private static Map<GameCharacter, Map<AbstractClothing, List<DisplacementType>>> clothingPreSexMap;
	
	private static Set<GameCharacter> charactersAbleToRemoveSelfClothing, charactersAbleToRemoveOthersClothing;
	
	private static boolean sexFinished;
	private static boolean partnerAllowedToUseSelfActions;
	
	private static AbstractClothing selectedClothing;
	

	private Sex() {
	}

	public DialogueNodeOld initialiseSex(
			boolean consensual,
			boolean subHasEqualControl,
			SexManagerInterface sexManager,
			DialogueNodeOld postSexDialogue,
			String sexStartDescription) {

		SexFlags.reset();
		
		Sex.consensual = consensual;
		Sex.subHasEqualControl = subHasEqualControl;
		
//		Main.game.setActiveNPC(activePartner);

		actionsAvailable = new HashMap<>();
		orgasmActionsAvailable = new HashMap<>();
		initialPenetrations = new HashMap<>();

		availableSexActionsPlayer = new ArrayList<>();
		miscActionsPlayer = new ArrayList<>();
		selfActionsPlayer = new ArrayList<>();
		sexActionsPlayer = new ArrayList<>();
		positionActionsPlayer = new ArrayList<>();
		repeatActionsPlayer = new LinkedList<>();
		availableSexActionsPartner = new ArrayList<>();
		
		forceSexPaceMap = new HashMap<>();
		
		setSexManager(sexManager);
		initialSexManager = sexManager;
		characterPerformingAction = Main.game.getPlayer();
		
		publicSex = sexManager.isPublicSex();
		
		Sex.postSexDialogue = postSexDialogue;
		
		sexStarted = false;

		resetAllOngoingActions();
		
		for(GameCharacter character : Sex.getAllParticipants()) {
			for(GameCharacter character2 : Sex.getAllParticipants()) {
				character.addSexPartner(character2);
			}
		}
		
		lastUsedPlayerAction = SexActionUtility.PLAYER_NONE;
		lastUsedPartnerAction = SexActionUtility.PARTNER_NONE;

		sexFinished = false;
		partnerAllowedToUseSelfActions = true;
		orgasmCountMap = new HashMap<>();
		cummedInsideMap = new HashMap<>();
		
		charactersAbleToRemoveSelfClothing = new HashSet<>();
		charactersAbleToRemoveOthersClothing = new HashSet<>();
		for(GameCharacter character : Sex.getAllParticipants()) {
			if(sexManager.isAbleToRemoveSelfClothing(character)) {
				charactersAbleToRemoveSelfClothing.add(character);
			}
			if(sexManager.isAbleToRemoveOthersClothing(character)) {
				charactersAbleToRemoveOthersClothing.add(character);
			}
		}
		
		for(GameCharacter character : Sex.getAllParticipants()) {
			if(isConsensual()) {
				character.setSexConsensualCount(character.getSexConsensualCount()+1);
			}
			if(isDom(character)) {
				character.setSexAsDomCount(character.getSexAsDomCount()+1);
			} else {
				character.setSexAsSubCount(character.getSexAsSubCount()+1);
			}
		}

		// Populate exposed areas:
		areasExposed = new HashMap<>();
		requestsBlocked = new HashMap<>();
		for(GameCharacter character : Sex.getAllParticipants()) {
			areasExposed.put(character, new ArrayList<>());
			requestsBlocked.put(character, new ArrayList<>());
			
//			for (CoverableArea area : CoverableArea.values()) {
//				if (character.isAbleToAccessCoverableArea(area, false)) {
//					areasExposed.get(character).add(area);
//				}
//			}
		}

		areasStretched = new HashMap<>();
		for(GameCharacter character : Sex.getAllParticipants()) {
			areasStretched.put(character, new HashSet<>());
		}
		
		areasCurrentlyStretching = new HashMap<>();
		for(GameCharacter character : Sex.getAllParticipants()) {
			areasCurrentlyStretching.put(character, new HashSet<>());
		}
		
		areasTooLoose = new HashMap<>();
		for(GameCharacter character : Sex.getAllParticipants()) {
			areasTooLoose.put(character, new HashSet<>());
		}
		
		for(GameCharacter character : Sex.getAllParticipants()) {
			if(character instanceof NPC) {
				((NPC)character).generateSexChoices(Sex.getTargetedPartner(character), null);
			}
			
			// Default starting lust and arousal:
			Sex.getSexManager().initStartingLustAndArousal(character);
			
			if(character.isPlayer()) {
				forceSexPaceMap.put(character, Sex.isDom(character)?SexPace.DOM_NORMAL:SexPace.SUB_NORMAL);
			} else {
				if(Sex.isDom(character) && ((NPC)character).getSexPaceDomPreference()!=null) {
					forceSexPaceMap.put(character, ((NPC)character).getSexPaceDomPreference());
				}
				if(!Sex.isDom(character) && ((NPC)character).getSexPaceSubPreference(Sex.getTargetedPartner(character))!=null) {
					forceSexPaceMap.put(character, ((NPC)character).getSexPaceSubPreference(Sex.getTargetedPartner(character)));
				}
			}
			
			if(sexManager.getStartingSexPaceModifier(character)!=null) {
				if(character.isPlayer()) {
					forceSexPaceMap.put(character, sexManager.getStartingSexPaceModifier(character));
				}
				switch(sexManager.getStartingSexPaceModifier(character)) {
					case DOM_GENTLE:
						character.setLust(10);
						break;
					case DOM_NORMAL:
						character.setArousal(5);
						break;
					case DOM_ROUGH:
						character.setLust(85);
						character.setArousal(10);
						break;
					case SUB_EAGER:
						character.setLust(85);
						character.setArousal(10);
						break;
					case SUB_NORMAL:
						character.setArousal(5);
						break;
					case SUB_RESISTING:
						character.setLust(0);
						break;
				}
			}

			if(sexManager.getForcedSexPace(character)!=null) {
				forceSexPaceMap.put(character, sexManager.getForcedSexPace(character));
				switch(sexManager.getForcedSexPace(character)) {
					case DOM_GENTLE:
						character.setLust(10);
						break;
					case DOM_NORMAL:
						character.setArousal(5);
						break;
					case DOM_ROUGH:
						character.setLust(85);
						character.setArousal(10);
						break;
					case SUB_EAGER:
						character.setLust(85);
						character.setArousal(10);
						break;
					case SUB_NORMAL:
						character.setArousal(5);
						break;
					case SUB_RESISTING:
						character.setLust(0);
						break;
				}
			}
		}
		
		// Set starting wetness values:
		wetAreas = new HashMap<>();
		
		for(GameCharacter character : Sex.getAllParticipants()) {
			wetAreas.put(character, new HashMap<>());
			for(SexAreaPenetration pt : SexAreaPenetration.values()) {
				wetAreas.get(character).put(pt, new HashSet<>());
			}
			for(SexAreaOrifice pt : SexAreaOrifice.values()) {
				wetAreas.get(character).put(pt, new HashSet<>());
			}
		}
		
		// Starting text:
		sexSB = new StringBuilder(sexStartDescription);

		sexSB.append(sexManager.getStartSexDescription());
		
		if(Sex.isPublicSex()) {
			sexSB.append(Sex.getInitialSexManager().getPublicSexStartingDescription());
		}

		sexSB.append("<p style='text-align:center;'><b>Starting Position:</b> <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>"+sexManager.getPosition().getName()+"</b><br/>"
				+"<i><b>"+sexManager.getPosition().getDescription()+"</b></i></p>");
		
		
		// This method appends wet descriptions to the sexSB StringBuilder: TODO
		calculateWetAreas(true);

		for(Entry<GameCharacter, List<CoverableArea>> entry : sexManager.exposeAtStartOfSexMap().entrySet()) {
			for(CoverableArea ca : entry.getValue()) {
				entry.getKey().displaceClothingForAccess(ca);
			}
		}
		
		// Starting exposed:
		for(GameCharacter character : Sex.getAllParticipants()) {
			handleExposedDescriptions(character, true);
		}

		Main.game.setInSex(true);
		
		// Store status of all clothes for both partners (so they can be restored afterwards):
		clothingPreSexMap = new HashMap<>();
		
		for(GameCharacter character : Sex.getAllParticipants()) {
			clothingPreSexMap.put(character, new HashMap<>());
			for (AbstractClothing c : character.getClothingCurrentlyEquipped()) {
				clothingPreSexMap.get(character).put(c, new ArrayList<>(c.getDisplacedList()));
			}
		}

		List<AbstractClothing> clothingToStrip = new ArrayList<>();

		if(sexManager.isPlayerStartNaked()) {
			clothingToStrip.addAll(Main.game.getPlayer().getClothingCurrentlyEquipped());

			for (AbstractClothing c : clothingToStrip) {
				Main.game.getPlayer().unequipClothingOntoFloor(c, true, Main.game.getPlayer());
			}
		}

		clothingToStrip.clear();

		if(sexManager.isPartnerStartNaked()) {
			for(GameCharacter character : Sex.getAllParticipants()) {
				if(!character.isPlayer()) {
					clothingToStrip.addAll(character.getClothingCurrentlyEquipped());
		
					for (AbstractClothing c : clothingToStrip) {
						character.unequipClothingOntoFloor(c, true, character);
					}
				}
			}
		}
		
		sexDescription = sexSB.toString();

		// Populate available SexAction list:
		populatePlayerSexLists();

		return SEX_DIALOGUE;
	}

	private static void endSex() {
		Main.game.setInSex(false);
		
		// Restore clothes:
		for(Entry<GameCharacter, Map<AbstractClothing, List<DisplacementType>>> entry : clothingPreSexMap.entrySet()) {
			for (AbstractClothing c : entry.getValue().keySet()) {
				if(!c.getClothingType().isDiscardedOnUnequip()) {
					if (!entry.getKey().getClothingCurrentlyEquipped().contains(c)) {
						if(entry.getKey().getAllClothingInInventory().contains(c)) {
							entry.getKey().equipClothingFromInventory(c, true, entry.getKey(), entry.getKey());
						} else {
							entry.getKey().equipClothingFromGround(c, true, entry.getKey());
						}
					}
					c.getDisplacedList().clear();
					if(entry.getValue().get(c)!=null) {
						for(DisplacementType displacement : entry.getValue().get(c)) {
							entry.getKey().isAbleToBeDisplaced(c, displacement, true, true, entry.getKey());
						}
					}
				}
			}
		}
		
		if(SexFlags.playerGrewDemonicCock) {
			Main.game.getPlayer().setPenisType(PenisType.NONE);
		}
		
		for(GameCharacter participant : Sex.getAllParticipants()) {
			if(participant instanceof NPC) {
				((NPC) participant).setLastTimeHadSex(Main.game.getMinutesPassed(), Sex.getNumberOfOrgasms(participant)>0);
				((NPC)participant).endSex();
			}
		}
	}

	private static String endSexDescription;

	public static void applyEndSexEffects() {
		sexSB = new StringBuilder();

		boolean auraEventTriggered = false;
		for(GameCharacter participant : Sex.getAllParticipants()) {
			if(participant.isPlayer()) {
				// Stretching effects for each of the player's orifices:
				if (participant.getAssRawCapacityValue() != participant.getAssStretchedCapacity() && areasStretched.get(participant).contains(SexAreaOrifice.ANUS)) {
					if (participant.getAssPlasticity() == OrificePlasticity.ZERO_RUBBERY){

						participant.setAssStretchedCapacity(participant.getAssRawCapacityValue());

						sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Your asshole quickly recovers from its ordeal, and instantly returns to its original size!</b></p>");

					} else {

						// Increment core capacity by the Elasticity's capacityIncreaseModifier:
						participant.incrementAssCapacity(
								(participant.getAssStretchedCapacity()-participant.getAssRawCapacityValue())*participant.getAssPlasticity().getCapacityIncreaseModifier(),
								false);

						sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Your " + participant.getAssPlasticity().getDescriptor() + " asshole has been stretched from its ordeal, and is currently "
								+ Capacity.getCapacityFromValue(participant.getAssStretchedCapacity()).getDescriptor() + "!");

						if(participant.getAssPlasticity().getCapacityIncreaseModifier()>0) {
							sexSB.append(" It will recover some of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(participant.getAssRawCapacityValue()).getDescriptor() + "!</b></p>");
						} else {
							sexSB.append(" It will recover all of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(participant.getAssRawCapacityValue()).getDescriptor() + "!</b></p>");
						}
					}
				}
				
				if (participant.getVaginaRawCapacityValue() != participant.getVaginaStretchedCapacity() && areasStretched.get(participant).contains(SexAreaOrifice.VAGINA)) {
					if (participant.getVaginaPlasticity() == OrificePlasticity.ZERO_RUBBERY){

						participant.setVaginaStretchedCapacity(participant.getVaginaRawCapacityValue());

						sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Your vagina quickly recovers from its ordeal, and instantly returns to its original size!</b></p>");

					} else {
						// Increment core capacity by the Elasticity's capacityIncreaseModifier:
						participant.incrementVaginaCapacity(
								(participant.getVaginaStretchedCapacity()-participant.getVaginaRawCapacityValue())*participant.getVaginaPlasticity().getCapacityIncreaseModifier(),
								false);
						
						sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Your " + participant.getVaginaPlasticity().getDescriptor() + " pussy has been stretched from its ordeal, and is currently "
								+ Capacity.getCapacityFromValue(participant.getVaginaStretchedCapacity()).getDescriptor() + "!");

						if(participant.getVaginaPlasticity().getCapacityIncreaseModifier()>0) {
							sexSB.append(" It will recover some of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(participant.getVaginaRawCapacityValue()).getDescriptor() + "!</b></p>");
						} else {
							sexSB.append(" It will recover all of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(participant.getVaginaRawCapacityValue()).getDescriptor() + "!</b></p>");
						}
					}
				}
				
				if (participant.getNippleRawCapacityValue() != participant.getNippleStretchedCapacity() && areasStretched.get(participant).contains(SexAreaOrifice.NIPPLE)) {
					if (participant.getNipplePlasticity() == OrificePlasticity.ZERO_RUBBERY){

						participant.setNippleStretchedCapacity(participant.getNippleRawCapacityValue());

						sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Your [pc.nipples+] quickly recover from their ordeal, and instantly return to their original size!</b></p>");

					} else {
						// Increment core capacity by the Elasticity's capacityIncreaseModifier:
						participant.incrementNippleCapacity(
								(participant.getNippleStretchedCapacity()-participant.getNippleRawCapacityValue())*participant.getNipplePlasticity().getCapacityIncreaseModifier(),
								false);

						sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Your " + participant.getNipplePlasticity().getDescriptor() + " nipple-cunts have been stretched from their ordeal, and are currently "
								+ Capacity.getCapacityFromValue(participant.getNippleStretchedCapacity()).getDescriptor() + "!");

						if(participant.getNipplePlasticity().getCapacityIncreaseModifier()>0) {
							sexSB.append(" They will recover some of their original size, eventually tightening back to being " + Capacity.getCapacityFromValue(participant.getNippleRawCapacityValue()).getDescriptor() + "!</b></p>");
						} else {
							sexSB.append(" They will recover all of their original size, eventually tightening back to being " + Capacity.getCapacityFromValue(participant.getNippleRawCapacityValue()).getDescriptor() + "!</b></p>");
						}
					}
				}
				
				if (participant.getPenisRawCapacityValue() != participant.getPenisStretchedCapacity() && areasStretched.get(participant).contains(SexAreaOrifice.URETHRA_PENIS)) {
					if (participant.getUrethraPlasticity() == OrificePlasticity.ZERO_RUBBERY){

						participant.setPenisStretchedCapacity(participant.getPenisRawCapacityValue());

						sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Your cock's urethra quickly recovers from its ordeal, and instantly returns to its original size!</b></p>");

					} else {

						// Increment core capacity by the Elasticity's capacityIncreaseModifier:
						participant.incrementPenisCapacity(
								(participant.getPenisStretchedCapacity()-participant.getPenisRawCapacityValue())*participant.getUrethraPlasticity().getCapacityIncreaseModifier(),
								false);

						sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Your cock's " + participant.getUrethraPlasticity().getDescriptor() + " urethra has been stretched from its ordeal, and is currently "
								+ Capacity.getCapacityFromValue(participant.getPenisStretchedCapacity()).getDescriptor() + "!");

						if(participant.getUrethraPlasticity().getCapacityIncreaseModifier()>0) {
							sexSB.append(" It will recover some of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(participant.getPenisRawCapacityValue()).getDescriptor() + "!</b></p>");
						} else {
							sexSB.append(" It will recover all of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(participant.getPenisRawCapacityValue()).getDescriptor() + "!</b></p>");
						}
					}
				}
				
				if (participant.getVaginaUrethraRawCapacityValue() != participant.getVaginaUrethraStretchedCapacity() && areasStretched.get(participant).contains(SexAreaOrifice.URETHRA_VAGINA)) {
					if (participant.getVaginaUrethraPlasticity() == OrificePlasticity.ZERO_RUBBERY){

						participant.setVaginaUrethraStretchedCapacity(participant.getVaginaUrethraRawCapacityValue());

						sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Your pussy's urethra quickly recovers from its ordeal, and instantly returns to its original size!</b></p>");

					} else {

						// Increment core capacity by the Elasticity's capacityIncreaseModifier:
						participant.incrementVaginaUrethraCapacity(
								(participant.getVaginaUrethraStretchedCapacity()-participant.getVaginaUrethraRawCapacityValue())*participant.getVaginaUrethraPlasticity().getCapacityIncreaseModifier(),
								false);

						sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Your pussy's " + participant.getVaginaUrethraPlasticity().getDescriptor() + " urethra has been stretched from its ordeal, and is currently "
								+ Capacity.getCapacityFromValue(participant.getVaginaUrethraStretchedCapacity()).getDescriptor() + "!");

						if(participant.getVaginaUrethraPlasticity().getCapacityIncreaseModifier()>0) {
							sexSB.append(" It will recover some of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(participant.getVaginaUrethraRawCapacityValue()).getDescriptor() + "!</b></p>");
						} else {
							sexSB.append(" It will recover all of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(participant.getVaginaUrethraRawCapacityValue()).getDescriptor() + "!</b></p>");
						}
					}
				}
				
				if (participant.getFaceRawCapacityValue() != participant.getFaceStretchedCapacity() && areasStretched.get(participant).contains(SexAreaOrifice.MOUTH)) {
						// Increment core capacity by the Elasticity's capacityIncreaseModifier:
						participant.incrementFaceCapacity(
								(participant.getFaceStretchedCapacity()-participant.getFaceRawCapacityValue())*participant.getFacePlasticity().getCapacityIncreaseModifier(),
								false);
						// Special case for throat, as you aren't stretching it out, merely getting more experienced at sucking cock:
						participant.setFaceStretchedCapacity(participant.getFaceRawCapacityValue());

						sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>From your oral experience with " + activePartner.getName("the") + "'s " + activePartner.getPenisSize().getDescriptor()
								+ " cock, you are now experienced enough to comfortably suck " + PenisSize.getPenisSizeFromInt((int)participant.getFaceRawCapacityValue()).getDescriptor() + " cocks!</b></p>");
				}

				if(participant.getArousal() > ArousalLevel.THREE_HEATED.getMaximumValue() && getNumberOfOrgasms(participant) == 0) {
					participant.addStatusEffect(StatusEffect.FRUSTRATED_NO_ORGASM, 240+postSexDialogue.getMinutesPassed());
					sexSB.append("<p style='text-align:center'>[style.boldArcane(After stopping so close to the edge, you're left feeling frustrated and horny!)]</p>");
				}
				if(getNumberOfOrgasms(participant) > 0
						&& Main.game.isInNewWorld()) {
					participant.removeStatusEffect(StatusEffect.FRUSTRATED_NO_ORGASM);
					if(participant.hasStatusEffect(StatusEffect.RECOVERING_AURA)) {
						sexSB.append("<p style='text-align:center'><b>Your arcane aura is still strengthened from a previous sexual encounter, so</b> [style.boldArcane(you don't receive any arcane essences!)]</p>");
						
					} else {
						if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.essenceOrgasmDiscovered)) {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.essenceOrgasmDiscovered);
							if(!((PlayerCharacter) participant).isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
								if(activePartner!=null) {
									sexSB.append(
											UtilText.parse(activePartner,
											"<p>"
												+ "As you disentangle yourself from [npc.name], you suddenly become aware of a strange, shimmering pink glow that's started to materialise around your body,"
													+ " just like the one you saw in Lilaya's lab when she ran her tests on you."
												+ " Quickly realising that you're somehow able to see your own arcane aura, you watch, fascinated, as it rapidly increases in luminosity."
												+ " Just as you think that it can't get any brighter, your aura suddenly leaps back into your body, and you find yourself sharply inhaling as you feel it gaining strength."
											+ "</p>"
											+ "<p>"
												+ "Looking back over at [npc.name], you see that [npc.she] seems completely oblivious to what you've just witnessed."
												+ " You think that it would probably be best to go and ask Lilaya about what just happened..."
											+ "</p>"
											+(!((PlayerCharacter) participant).hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)
													?((PlayerCharacter) participant).startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)
													:"")));
								} else {
									sexSB.append("<p>"
												+ "As you stop masturbating, you suddenly become aware of a strange, shimmering pink glow that's started to materialise around your body,"
													+ " just like the one you saw in Lilaya's lab when she ran her tests on you."
												+ " Quickly realising that you're somehow able to see your own arcane aura, you watch, fascinated, as it rapidly increases in luminosity."
												+ " Just as you think that it can't get any brighter, your aura suddenly leaps back into your body, and you find yourself sharply inhaling as you feel it gaining strength."
											+ "</p>"
											+ "<p>"
												+ "You think that it would probably be best to go and ask Lilaya about what just happened..."
											+ "</p>"
											+(!((PlayerCharacter) participant).hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)
													?((PlayerCharacter) participant).startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)
													:""));
								}
								
							} else {
								if(activePartner!=null) {
									sexSB.append(
											UtilText.parse(activePartner,
											"<p>"
												+ "As you disentangle yourself from [npc.name], you suddenly become aware of a strange, shimmering pink glow that's started to materialise around your body,"
													+ " just like the one you saw in Lilaya's lab when she ran her tests on you."
												+ " Quickly realising that you're somehow able to see your own arcane aura, you watch, fascinated, as it rapidly increases in luminosity."
												+ " Just as you think that it can't get any brighter, your aura suddenly leaps back into your body, and you find yourself sharply inhaling as you feel it gaining strength."
											+ "</p>"
											+ "<p>"
												+ "Looking back over at [npc.name], you see that [npc.she] seems completely oblivious to what you've just witnessed."
												+ " You suddenly remember what Lilaya told you about absorbing essences, and how it's absolutely harmless for both parties involved, and breathe a sigh of relief..."
											+ "</p>"));
								} else {
									sexSB.append("<p>"
												+ "As you stop masturbating, you suddenly become aware of a strange, shimmering pink glow that's started to materialise around your body,"
													+ " just like the one you saw in Lilaya's lab when she ran her tests on you."
												+ " Quickly realising that you're somehow able to see your own arcane aura, you watch, fascinated, as it rapidly increases in luminosity."
												+ " Just as you think that it can't get any brighter, your aura suddenly leaps back into your body, and you find yourself sharply inhaling as you feel it gaining strength."
											+ "</p>"
											+ "<p>"
												+ "You suddenly remember what Lilaya told you about absorbing essences, and how it's absolutely harmless for both parties involved, and breathe a sigh of relief..."
											+ "</p>");
								}
							}
						}
						
						sexSB.append("<p style='text-align:center'>You feel your aura pulsating all around you as it draws strength from the sexual energy of your orgasm...</p>"
										+"<div class='container-full-width' style='text-align:center;'>"
											+ (participant.hasTrait(Perk.NYMPHOMANIAC, true)
													?participant.incrementEssenceCount(TFEssence.ARCANE, 4, true)
													:participant.incrementEssenceCount(TFEssence.ARCANE, 2, true))
										+ "</div>");
						
					}
					participant.addStatusEffect(StatusEffect.RECOVERING_AURA, 240);
					
					participant.setLust(0);
				}
				
				
			// Partner effects:
			} else {
				// Stretching effects:
				if (participant.getAssRawCapacityValue() != participant.getAssStretchedCapacity() && areasStretched.get(Sex.getActivePartner()).contains(SexAreaOrifice.ANUS)) {
					if (participant.getAssPlasticity() == OrificePlasticity.ZERO_RUBBERY){
		
						participant.setAssStretchedCapacity(participant.getAssRawCapacityValue());
		
						sexSB.append(UtilText.parse(participant,
								"<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Her] asshole quickly recovers from its ordeal, and instantly returns to its original size!</b></p>"));
		
					} else {
		
						// Increment core capacity by the Elasticity's capacityIncreaseModifier:
						participant.incrementAssCapacity(
								(participant.getAssStretchedCapacity()-participant.getAssRawCapacityValue())*participant.getAssPlasticity().getCapacityIncreaseModifier(),
								false);
		
						sexSB.append(UtilText.parse(participant,
										"<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Her] " + participant.getAssElasticity().getDescriptor() + " asshole has been stretched from its ordeal, and is currently "
								+ Capacity.getCapacityFromValue(participant.getAssStretchedCapacity()).getDescriptor() + "!"));
		
						if(participant.getAssPlasticity().getCapacityIncreaseModifier()>0) {
							sexSB.append(" It will recover some of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(participant.getAssRawCapacityValue()).getDescriptor() + "!</b></p>");
						} else {
							sexSB.append(" It will recover all of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(participant.getAssRawCapacityValue()).getDescriptor() + "!</b></p>");
						}
					}
				}
				
				if (participant.getVaginaRawCapacityValue() != participant.getVaginaStretchedCapacity() && areasStretched.get(Sex.getActivePartner()).contains(SexAreaOrifice.VAGINA)) {
					if (participant.getVaginaPlasticity() == OrificePlasticity.ZERO_RUBBERY){
		
						participant.setVaginaStretchedCapacity(participant.getVaginaRawCapacityValue());
		
		
						sexSB.append(UtilText.parse(participant,
								"<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Her] vagina quickly recovers from its ordeal, and instantly returns to its original size!</b></p>"));
		
					} else {
		
						// Increment core capacity by the Elasticity's capacityIncreaseModifier:
						participant.incrementVaginaCapacity(
								(participant.getVaginaStretchedCapacity()-participant.getVaginaRawCapacityValue())*participant.getVaginaPlasticity().getCapacityIncreaseModifier(),
								false);
		
						sexSB.append(UtilText.parse(participant,
								"<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Her] " + participant.getVaginaPlasticity().getDescriptor() + " pussy has been stretched from its ordeal, and is currently "
								+ Capacity.getCapacityFromValue(participant.getVaginaStretchedCapacity()).getDescriptor() + "!"));
		
						if(participant.getVaginaPlasticity().getCapacityIncreaseModifier()>0) {
							sexSB.append(" It will recover some of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(participant.getVaginaRawCapacityValue()).getDescriptor() + "!</b></p>");
						} else {
							sexSB.append(" It will recover all of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(participant.getVaginaRawCapacityValue()).getDescriptor() + "!</b></p>");
						}
					}
				}
				
				if (participant.getNippleRawCapacityValue() != participant.getNippleStretchedCapacity() && areasStretched.get(Sex.getActivePartner()).contains(SexAreaOrifice.NIPPLE)) {
					if (participant.getNipplePlasticity() == OrificePlasticity.ZERO_RUBBERY){
		
						participant.setNippleStretchedCapacity(participant.getNippleRawCapacityValue());
		
						sexSB.append(UtilText.parse(participant,
								"<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Her] [npc.nipples+] quickly recover from their ordeal, and instantly return to their original size!</b></p>"));
		
					} else {
		
						// Increment core capacity by the Elasticity's capacityIncreaseModifier:
						participant.incrementNippleCapacity(
								(participant.getNippleStretchedCapacity()-participant.getNippleRawCapacityValue())*participant.getNipplePlasticity().getCapacityIncreaseModifier(),
								false);
		
						sexSB.append(UtilText.parse(participant,
								"<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Her] " + participant.getNipplePlasticity().getDescriptor() + " nipple-cunts have been stretched from their ordeal, and are currently "
								+ Capacity.getCapacityFromValue(participant.getNippleStretchedCapacity()).getDescriptor() + "!"));
		
						if(participant.getNipplePlasticity().getCapacityIncreaseModifier()>0) {
							sexSB.append(" They will recover some of their original size, eventually tightening back to being " + Capacity.getCapacityFromValue(participant.getNippleRawCapacityValue()).getDescriptor() + "!</b></p>");
						} else {
							sexSB.append(" They will recover all of their original size, eventually tightening back to being " + Capacity.getCapacityFromValue(participant.getNippleRawCapacityValue()).getDescriptor() + "!</b></p>");
						}
					}
				}
				
				if (participant.getPenisRawCapacityValue() != participant.getPenisStretchedCapacity() && areasStretched.get(Sex.getActivePartner()).contains(SexAreaOrifice.URETHRA_PENIS)) {
					if (participant.getUrethraPlasticity() == OrificePlasticity.ZERO_RUBBERY){
		
						participant.setPenisStretchedCapacity(participant.getPenisRawCapacityValue());
		
						sexSB.append(UtilText.parse(participant,
								"<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Her] cock's urethra quickly recovers from its ordeal, and instantly returns to its original size!</b></p>"));
		
					} else {
		
						// Increment core capacity by the Elasticity's capacityIncreaseModifier:
						participant.incrementPenisCapacity(
								(participant.getPenisStretchedCapacity()-participant.getPenisRawCapacityValue())*participant.getUrethraPlasticity().getCapacityIncreaseModifier(),
								false);
		
						sexSB.append(UtilText.parse(participant,
								"<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Her] cock's " + participant.getUrethraPlasticity().getDescriptor() + " urethra has been stretched from its ordeal, and is currently "
								+ Capacity.getCapacityFromValue(participant.getPenisStretchedCapacity()).getDescriptor() + "!"));
		
						if(participant.getUrethraPlasticity().getCapacityIncreaseModifier()>0) {
							sexSB.append(" It will recover some of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(participant.getPenisRawCapacityValue()).getDescriptor() + "!</b></p>");
						} else {
							sexSB.append(" It will recover all of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(participant.getPenisRawCapacityValue()).getDescriptor() + "!</b></p>");
						}
					}
				}
				
				if (participant.getVaginaUrethraRawCapacityValue() != participant.getVaginaUrethraStretchedCapacity() && areasStretched.get(Sex.getActivePartner()).contains(SexAreaOrifice.URETHRA_VAGINA)) {
					if (participant.getVaginaUrethraPlasticity() == OrificePlasticity.ZERO_RUBBERY){
		
						participant.setVaginaUrethraStretchedCapacity(participant.getVaginaUrethraRawCapacityValue());
		
						sexSB.append(UtilText.parse(participant,
								"<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Her] vagina's urethra quickly recovers from its ordeal, and instantly returns to its original size!</b></p>"));
		
					} else {
		
						// Increment core capacity by the Elasticity's capacityIncreaseModifier:
						participant.incrementVaginaUrethraCapacity(
								(participant.getVaginaUrethraStretchedCapacity()-participant.getVaginaUrethraRawCapacityValue())*participant.getVaginaUrethraPlasticity().getCapacityIncreaseModifier(),
								false);
		
						sexSB.append(UtilText.parse(participant,
								"<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Her] vagina's " + participant.getVaginaUrethraPlasticity().getDescriptor() + " urethra has been stretched from its ordeal, and is currently "
								+ Capacity.getCapacityFromValue(participant.getVaginaUrethraStretchedCapacity()).getDescriptor() + "!"));
		
						if(participant.getVaginaUrethraPlasticity().getCapacityIncreaseModifier()>0) {
							sexSB.append(" It will recover some of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(participant.getVaginaUrethraRawCapacityValue()).getDescriptor() + "!</b></p>");
						} else {
							sexSB.append(" It will recover all of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(participant.getVaginaUrethraRawCapacityValue()).getDescriptor() + "!</b></p>");
						}
					}
				}
				
				if (participant.getFaceRawCapacityValue() != participant.getFaceStretchedCapacity() && areasStretched.get(Sex.getActivePartner()).contains(SexAreaOrifice.MOUTH)) {
					// Increment core capacity by the Elasticity's capacityIncreaseModifier:
					participant.incrementFaceCapacity(
							(participant.getFaceStretchedCapacity()-participant.getFaceRawCapacityValue())*participant.getFacePlasticity().getCapacityIncreaseModifier(),
							false);
					// Special case for throat, as you aren't stretching it out, merely getting more experienced at sucking cock:
					participant.setFaceStretchedCapacity(participant.getFaceRawCapacityValue());
		
					sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>From [npc.her] oral experience with your [pc.cockSize] cock, [npc.she] is now experienced enough to comfortably suck "
							+ PenisSize.getPenisSizeFromInt((int)Main.game.getPlayer().getFaceRawCapacityValue()).getDescriptor() + " cocks!</b></p>");
				}
				
				
				// Extra effects:
				if(participant.getArousal() > ArousalLevel.THREE_HEATED.getMaximumValue() && getNumberOfOrgasms(participant) == 0) {
					participant.addStatusEffect(StatusEffect.FRUSTRATED_NO_ORGASM, 240+postSexDialogue.getMinutesPassed());
					sexSB.append("<p style='text-align:center'>[style.boldArcane(After stopping so close to the edge, [npc.name] is left feeling frustrated and horny!)]</p>");
				}
				if(getNumberOfOrgasms(participant) > 0
						&& Main.game.isInNewWorld()) {
					participant.removeStatusEffect(StatusEffect.FRUSTRATED_NO_ORGASM);
					if(participant.hasStatusEffect(StatusEffect.RECOVERING_AURA)) {
						sexSB.append("<p style='text-align:center'><b>[npc.NamePos] arcane aura is still strengthened from a previous sexual encounter, so</b> [style.boldArcane(you don't receive any arcane essences!)]</p>");
						
					} else {
						
						if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.essenceOrgasmDiscovered)) {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.essenceOrgasmDiscovered);
							if(!auraEventTriggered) {
								if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
									sexSB.append(
											UtilText.parse(participant,
											"<p>"
												+ "As you disentangle yourself from [npc.name], you suddenly become aware of a strange, shimmering pink glow that's started to materialise around [npc.her] body,"
													+ " just like the one you saw in Lilaya's lab when she ran her tests on you."
												+ " Quickly realising that you're somehow able to see [npc.namePos] arcane aura, you watch, fascinated, as it rapidly increases in luminosity."
												+ " Just as you think that it can't get any brighter, [npc.her] aura suddenly leaps back into [npc.her] body, but as it does so, a single shard breaks off and flies towards you."
												+ " Unable to dodge in time, you find yourself sharply inhaling as the small piece of [npc.namePos] aura shoots into your chest."
											+ "</p>"
											+ "<p>"
												+ "Alarmed at what's just happened, you look back over at [npc.name], only to see that [npc.she] seems completely oblivious to what you've just witnessed."
												+ " You think that it would probably be best to go and ask Lilaya about what just happened..."
											+ "</p>"
											+(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)?Main.game.getPlayer().startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY):"")));
									
								} else {
									sexSB.append(
											UtilText.parse(participant,
											"<p>"
												+ "As you disentangle yourself from [npc.name], you suddenly become aware of a strange, shimmering pink glow that's started to materialise around [npc.her] body,"
													+ " just like the one you saw in Lilaya's lab when she ran her tests on you."
												+ " Quickly realising that you're somehow able to see [npc.namePos] arcane aura, you watch, fascinated, as it rapidly increases in luminosity."
												+ " Just as you think that it can't get any brighter, [npc.her] aura suddenly leaps back into [npc.her] body, but as it does so, a single shard breaks off and flies towards you."
												+ " Unable to dodge in time, you find yourself sharply inhaling as the small piece of [npc.namePos] aura shoots into your chest."
											+ "</p>"
											+ "<p>"
												+ "Alarmed at what's just happened, you look back over at [npc.name], only to see that [npc.she] seems completely oblivious to what you've just witnessed."
												+ " You suddenly remember what Lilaya told you about absorbing essences, and how it's absolutely harmless for both parties involved, and breathe a sigh of relief..."
											+ "</p>"));
								}
								auraEventTriggered = true;
							}
						}
						
						sexSB.append("<p style='text-align:center'>You feel your aura drawing strength from the sexual energy of [npc.namePos] orgasm...</p>"
								+"<div class='container-full-width' style='text-align:center;'>"
									+ (Main.game.getPlayer().hasTrait(Perk.NYMPHOMANIAC, true)
											?Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, 4, true)
											:Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, 2, true))
								+ "</div>");
						
					}
					participant.addStatusEffect(StatusEffect.RECOVERING_AURA, 240);

				}
				
				if(Sex.getAllParticipants().contains(Main.game.getPlayer())) {
					if((Sex.getSexPace(participant)!=SexPace.SUB_RESISTING || participant.hasFetish(Fetish.FETISH_NON_CON_SUB))) {
						if(Sex.getNumberOfOrgasms(participant)>0
								|| participant.hasFetish(Fetish.FETISH_DENIAL_SELF)
								|| (Sex.isDom(participant) && !Sex.isSubHasEqualControl())) {
							sexSB.append(participant.incrementAffection(Main.game.getPlayer(), 5));
						} else {
							sexSB.append(participant.incrementAffection(Main.game.getPlayer(), -2));
						}
					} else {
						sexSB.append(participant.incrementAffection(Main.game.getPlayer(), -50));
					}
				}
				
				if(getNumberOfOrgasms(participant) > 0) {
					participant.setLust(0);
				}
			}
		}
		
		sexSB.append(Sex.getSexManager().applyEndSexEffects());
		
		endSexDescription = sexSB.toString();
	}
	
	// Text formatting:
	
	private static String formatInitialPenetration(String description) {
		return "<p style='text-align:center;'><i style='color:" + BaseColour.PINK_DEEP.toWebHexString() + ";'>"+description+"</i></p>";
	}
	
	private static String formatPenetration(String rawInput){
		return "<p style='text-align:center; color:"+BaseColour.VIOLET.toWebHexString()+";'><i>"+rawInput+"</i></p>";
	}
	
	private static String formatStopPenetration(String rawInput){
		return "<p style='text-align:center; color:"+BaseColour.PINK.toWebHexString()+";'><i>"+rawInput+"</i></p>";
	}
	
	private static String formatCoverableAreaBecomingExposed(String description) {
		return "<p style='text-align:center;'><i style='color:" + BaseColour.PURPLE_LIGHT.toWebHexString() + ";'>"+description+"</i></p>";
	}
	
	private static String formatCoverableAreaGettingWet(String description) {
		return UtilText.parse(Sex.getActivePartner(),
				"<p style='text-align:center;'><i style='color:" + BaseColour.LILAC_LIGHT.toWebHexString() + ";'>"+description+"</i></p>");
	}

	
	public static SexActionCategory responseCategory = null;
	
	public static final DialogueNodeOld SEX_DIALOGUE = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed() {
			return 1;
		}
		
		@Override
		public String getLabel() {
			return (!Sex.isConsensual() && Main.getProperties().hasValue(PropertyValue.nonConContent)?"Non-consensual ":"")
					+(Sex.isPublicSex()?"Public ":"")
					+(Sex.getAllParticipants().size()>1?"Sex: ":"Masturbation: ")
					+getPosition().getName();
		}

		@Override
		public String getContent() {
			return sexDescription + (sexFinished ? endSexDescription : "");
		}

		@Override
		public String getResponseTabTitle(int index) {
			if (sexFinished
//					|| lastUsedPartnerAction == SexActionUtility.PARTNER_ORGASM_MUTUAL_WAIT
					|| Main.game.getPlayer().getArousal() >= ArousalLevel.FIVE_ORGASM_IMMINENT.getMaximumValue()
					|| (activePartner!=null && activePartner.getArousal() >= ArousalLevel.FIVE_ORGASM_IMMINENT.getMaximumValue())) {
				return null;
			}
			if(index==0) {
				return "Misc. Actions";
				
			} else if(index==1) {
				return "Self Actions";
				
			} else if(index==2) {
				return "Sex Actions";
				
			} else if(index==3) {
				return "Positioning";
				
			} else if(index==4) {
				return "Repeat Actions";
				
			} else {
				return null;
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			// Finished sex:
			if (sexFinished) {
				if (index == 1) {
					return new Response(postSexDialogue.getLabel(), postSexDialogue.getDescription(), postSexDialogue){
						@Override
						public void effects() {
							endSex();
						}
					};
				} else {
					return null;
				}
				
			// Orgasm actions:
			} else if(
//					lastUsedPartnerAction == SexActionUtility.PARTNER_ORGASM_MUTUAL_WAIT ||
					Main.game.getPlayer().getArousal() >= ArousalLevel.FIVE_ORGASM_IMMINENT.getMaximumValue()
						|| (activePartner!=null && activePartner.getArousal() >= ArousalLevel.FIVE_ORGASM_IMMINENT.getMaximumValue())) {
					
				if(index == 0){
					return null;

				} else if (index-1 < availableSexActionsPlayer.size() && availableSexActionsPlayer.get(index-1) != null){
					return availableSexActionsPlayer.get(index-1).toResponse();
					
				} else {
					return null;
				}
				
			// Normal sex actions:
			} else {
				
				if(responseTab==0) {
					if(index>=15) {
						if(index < miscActionsPlayer.size()) {
							return miscActionsPlayer.get(index).toResponse();
						}
					} else {
						if(index <= miscActionsPlayer.size()) {
							if(index==0) {
								if(miscActionsPlayer.size()>=15) {
									return miscActionsPlayer.get(14).toResponse();
								} else {
									return null;
								}
							} else {
								return miscActionsPlayer.get(index-1).toResponse();
							}
						}
					}
					
				} else if(responseTab==1) {
					if(index>=15) {
						if(index < selfActionsPlayer.size()) {
							return selfActionsPlayer.get(index).toResponse();
						}
					} else {
						if(index <= selfActionsPlayer.size()) {
							if(index==0) {
								if(selfActionsPlayer.size()>=15) {
									return selfActionsPlayer.get(14).toResponse();
								} else {
									return null;
								}
							} else {
								return selfActionsPlayer.get(index-1).toResponse();
							}
						}
					}
					
				} else if(responseTab==2) {
					if(index>=15) {
						if(index < sexActionsPlayer.size()) {
							return sexActionsPlayer.get(index).toResponse();
						}
					} else {
						if(index <= sexActionsPlayer.size()) {
							if(index==0) {
								if(sexActionsPlayer.size()>=15) {
									return sexActionsPlayer.get(14).toResponse();
								} else {
									return null;
								}
							} else {
								return sexActionsPlayer.get(index-1).toResponse();
							}
						}
					}
					
				} else if(responseTab==3) {
					if(index>=15) {
						if(index < positionActionsPlayer.size()) {
							return positionActionsPlayer.get(index).toResponse();
						}
					} else {
						if(index <= positionActionsPlayer.size()) {
							if(index==0) {
								if(positionActionsPlayer.size()>=15) {
									return positionActionsPlayer.get(14).toResponse();
								} else {
									return null;
								}
							} else {
								return positionActionsPlayer.get(index-1).toResponse();
							}
						}
					}
					
				} else if(responseTab==4) {
					List<SexActionInterface> availableRepeatActionsPlayer = new LinkedList<>();
					availableRepeatActionsPlayer.addAll(repeatActionsPlayer);
					availableRepeatActionsPlayer.removeIf(sa-> !sa.isAddedToAvailableSexActions());
					availableRepeatActionsPlayer.removeIf(sa-> !sa.isBaseRequirementsMet());
					availableRepeatActionsPlayer.removeIf(sa-> !sa.toResponse().isAvailable());
					Collections.reverse(availableRepeatActionsPlayer);
					if(index <= availableRepeatActionsPlayer.size()) {
						if(index==0) {
							if(availableRepeatActionsPlayer.size()>=15) {
								return availableRepeatActionsPlayer.get(14).toResponse();
							} else {
								return null;
							}
						} else {
							return availableRepeatActionsPlayer.get(index-1).toResponse();
						}
					}
					
				}
				
				return null;
			}
		}

		@Override
		public boolean isContinuesDialogue(){
			return sexStarted;
		}

		@Override
		public boolean isDisplaysActionTitleOnContinuesDialogue() {
			return true;
		}

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	
//	public static final DialogueNodeOld SEX_DIALOGUE_RETURN = new DialogueNodeOld("", "", true) {
//		private static final long serialVersionUID = 1L;
//
//		@Override
//		public String getLabel() {
//			return "Sex: "+getPosition().getName();
//		}
//
//		@Override
//		public String getContent() {
//			return (!sexStarted?SEX_DIALOGUE.getContent():"");
//		}
//
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			return SEX_DIALOGUE.getResponse(0, index);
//		}
//
//		@Override
//		public boolean isContinuesDialogue(){
//			return SEX_DIALOGUE.isContinuesDialogue();
//		}
//
//		@Override
//		public boolean isDispalysActionTitleOnContinuesDialogue() {
//			return false;
//		}
//
//		@Override
//		public boolean isInventoryDisabled() {
//			return SEX_DIALOGUE.isInventoryDisabled();
//		}
//	};
	
	/**
	 * Don't call this out of sex.
	 * @param sexActionPlayer The action that the player is taking this turn.
	 */
	public static void endSexTurn(SexActionInterface sexActionPlayer) {

		sexSB = new StringBuilder();

		sexSB.append("<p>" + sexActionPlayer.getDescription() + "</p>");
		
		String endString = sexActionPlayer.baseEffects();
		
		applyGenericDescriptionsAndEffects(Main.game.getPlayer(), sexActionPlayer);

		sexSB.append(endString);
		
		String s;
		if(sexActionPlayer.getLimitation()==null
				&& sexActionPlayer!=SexActionUtility.CLOTHING_REMOVAL
				&& sexActionPlayer!=SexActionUtility.CLOTHING_DYE) {
			s = UtilText.parse(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(sexActionPlayer), sexSB.toString());
		} else {
			s = UtilText.parse(Sex.getCharacterTargetedForSexAction(sexActionPlayer), sexSB.toString());
		}
		sexSB.setLength(0);
		sexSB.append(s);
		
		
		// End sex conditions:
		if (sexActionPlayer.endsSex()) {
			sexDescription = sexSB.toString();

			applyEndSexEffects();
			sexFinished = true;
			
		} else {
			// Partner action is done afterwards:
			// Update lists for the partner's action choice.
			if(!Sex.isMasturbation()) {
				GameCharacter active = Sex.getActivePartner();
				
				for(GameCharacter character : Sex.getAllParticipants()) {
					if(!character.isPlayer()) {
						Sex.setActivePartner((NPC) character);
						Sex.setCharacterPerformingAction(character);
						
						if(sexActionPlayer.getActionType()!=SexActionType.ORGASM && sexActionPlayer.getActionType()!=SexActionType.ORGASM_NO_AROUSAL_RESET) {
							calculateAvailableSexActionsPartner();
				
							SexActionInterface sexActionPartner = sexManager.getPartnerSexAction(sexActionPlayer);
							
							sexSB.append("<br/><p>" + sexActionPartner.getDescription() + "</p>");
				
							endString = sexActionPartner.baseEffects();
							lastUsedPartnerAction = sexActionPartner;
							
							applyGenericDescriptionsAndEffects(Sex.getActivePartner(), sexActionPartner);
	
							sexSB.append(endString);
							
							if(sexActionPartner.getLimitation()==null
									&& sexActionPartner!=SexActionUtility.CLOTHING_REMOVAL
									&& sexActionPartner!=SexActionUtility.CLOTHING_DYE) {
								s = UtilText.parse(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(sexActionPartner), sexSB.toString());
							} else {
								s = UtilText.parse(Sex.getCharacterPerformingAction(), sexSB.toString());
							}
							sexSB.setLength(0);
							sexSB.append(s);
							
							sexDescription = sexSB.toString();
							
							// End sex conditions:
							if (sexActionPartner.endsSex()) {
								applyEndSexEffects();
								sexFinished = true;
							}
						} else {
							sexDescription = sexSB.toString();
						}
					}
				}
				Sex.setActivePartner((NPC) active);
				Sex.setCharacterPerformingAction(Main.game.getPlayer());
			} else {
				sexDescription = sexSB.toString();
			}
			
			if(Sex.isPublicSex()) {
				sexSB.append(Sex.getInitialSexManager().getRandomPublicSexDescription());
				sexDescription = sexSB.toString();
			}
			
			// Re-populate lists for the player's next action choice.
			populatePlayerSexLists();
		}

		lastUsedPlayerAction = sexActionPlayer;
		if(sexActionPlayer.getActionType()!=SexActionType.PREPARE_FOR_PARTNER_ORGASM
				&& sexActionPlayer.getActionType()!=SexActionType.ORGASM
				&& sexActionPlayer.getActionType()!=SexActionType.ORGASM_NO_AROUSAL_RESET) {
			repeatActionsPlayer.remove(sexActionPlayer);
			repeatActionsPlayer.add(sexActionPlayer);
		}
		
		repeatActionsPlayer.remove(SexActionUtility.CLOTHING_DYE);
		repeatActionsPlayer.remove(SexActionUtility.CLOTHING_REMOVAL);
		repeatActionsPlayer.remove(SexActionUtility.PLAYER_USE_ITEM);
	}

	public static void recalculateSexActions() {
		populatePlayerSexLists();
	}
	
	private static void populatePlayerSexLists() {
		// Populate available SexActions from the current SexPosition.
		availableSexActionsPlayer.clear();

		if (Main.game.getPlayer().getArousal() >= ArousalLevel.FIVE_ORGASM_IMMINENT.getMaximumValue()) { // Add orgasm actions if player ready to orgasm:
			
			for (SexActionInterface sexAction : Sex.getOrgasmActionsPlayer()) {
				if (sexAction.isAddedToAvailableSexActions()) {
					availableSexActionsPlayer.add(sexAction);
				}
			}

		} else if (activePartner!=null && activePartner.getArousal() >= ArousalLevel.FIVE_ORGASM_IMMINENT.getMaximumValue()) { // Add orgasm reactions if partner is ready to orgasm:
			for (SexActionInterface sexAction : Sex.getActionsAvailablePlayer()) {
				if (sexAction.getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM && sexAction.isAddedToAvailableSexActions()) {
					availableSexActionsPlayer.add(sexAction);
				}
			}
			
		} else {
			// Can always do nothing:
			availableSexActionsPlayer.add(SexActionUtility.PLAYER_NONE);
			availableSexActionsPlayer.add(SexActionUtility.PLAYER_CALM_DOWN);

//			if(Main.game.getPlayer().hasFetish(Fetish.FETISH_DENIAL)) {
//				if(isConsensual() || isDom(Main.game.getPlayer()))
//					availableSexActionsPlayer.add(SexActionUtility.DENIAL_FETISH_DENY);
//			}

			// Add actions:
			for (SexActionInterface sexAction : Sex.getActionsAvailablePlayer()) {
				if (sexAction.isAddedToAvailableSexActions()){
					availableSexActionsPlayer.add(sexAction);
				}
			}

			availableSexActionsPlayer.sort((SexActionInterface s1, SexActionInterface s2) -> {return s1.getActionType().compareTo(s2.getActionType());});
		}
		
		miscActionsPlayer.clear();
		selfActionsPlayer.clear();
		sexActionsPlayer.clear();
		positionActionsPlayer.clear();
		for(SexActionInterface action : availableSexActionsPlayer) {
			if(action.toResponse()!=null) {
				switch(action.getCategory()) {
					case MISCELLANEOUS: case CHARACTER_SWITCH:
						miscActionsPlayer.add(action);
						break;
					case POSITIONING:
						positionActionsPlayer.add(action);
						break;
					case SELF:
						selfActionsPlayer.add(action);
						break;
					case SEX:
						sexActionsPlayer.add(action);
						break;
				}
			}
		}
		positionActionsPlayer.sort((a1, a2) -> a1.getActionTitle().compareTo(a2.getActionTitle()));
		
		if(Sex.getTotalParticipantCount()>2) {
			for(GameCharacter character : Sex.getDominantParticipants().keySet()) {
				if(!character.isPlayer() && !Sex.getTargetedPartner(Main.game.getPlayer()).equals(character)) {
					miscActionsPlayer.add(new SexAction(
												SexActionType.ONGOING,
												ArousalIncrease.ZERO_NONE,
												ArousalIncrease.ZERO_NONE,
												CorruptionLevel.ZERO_PURE,
												null,
												SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
													@Override
													public String getActionTitle() {
														return character.getName();
													}
											
													@Override
													public String getActionDescription() {
														return UtilText.parse(character, "Set [npc.name] as the active partner.");
													}
											
													@Override
													public String getDescription() {
														return "";
													}
													
													@Override
													public SexActionCategory getCategory() {
														return SexActionCategory.CHARACTER_SWITCH;
													}
													
													@Override
													public void applyEffects() {
														Sex.setActivePartner((NPC) character);
														Sex.recalculateSexActions();
													}
												});
				}
			}
			for(GameCharacter character : Sex.getSubmissiveParticipants().keySet()) {
				if(!character.isPlayer() && !Sex.getTargetedPartner(Main.game.getPlayer()).equals(character)) {
					miscActionsPlayer.add(new SexAction(
												SexActionType.ONGOING,
												ArousalIncrease.ZERO_NONE,
												ArousalIncrease.ZERO_NONE,
												CorruptionLevel.ZERO_PURE,
												null,
												SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
													@Override
													public String getActionTitle() {
														return character.getName();
													}
											
													@Override
													public String getActionDescription() {
														return UtilText.parse(character, "Set [npc.name] as the active partner.");
													}
											
													@Override
													public String getDescription() {
														return "";
													}
													
													@Override
													public SexActionCategory getCategory() {
														return SexActionCategory.CHARACTER_SWITCH;
													}
													
													@Override
													public void applyEffects() {
														Sex.setActivePartner((NPC) character);
														Sex.recalculateSexActions();
													}
												});
				}
			}
		}

	}

	/**
	 * For use in getPartnerSexAction() in SexManager classes.
	 */
	private static void calculateAvailableSexActionsPartner() {
		// Populate available SexActions from the current SexPosition.
		availableSexActionsPartner.clear();
		List<SexActionInterface> lowPriority = new ArrayList<>(), normalPriority = new ArrayList<>(), highPriority = new ArrayList<>(), uniqueMax  = new ArrayList<>();
		boolean standardActions = true;
		
		// Add orgasm actions if ready to orgasm:
		if (activePartner.getArousal() >= ArousalLevel.FIVE_ORGASM_IMMINENT.getMaximumValue()) {
			standardActions = false;

			if(SexFlags.playerPreparedForOrgasm) {
				for (SexActionInterface sexAction : Sex.getOrgasmActionsPartner()) {
					if (sexAction.isAddedToAvailableSexActions()) {
						boolean dislikedAction = false;
						if(sexAction.getFetishes(activePartner)!=null) {
							for(Fetish f : sexAction.getFetishes(activePartner)) {
								if(f!=Fetish.FETISH_EXHIBITIONIST // Do not include exhibitionist, as otherwise NPC will never do anything in public sex.
										&& (activePartner.getFetishDesire(f)==FetishDesire.ONE_DISLIKE
											|| activePartner.getFetishDesire(f)==FetishDesire.ZERO_HATE)) {
									lowPriority.add(sexAction);
									dislikedAction = true;
									break;
								}
							}
						}
						if(!dislikedAction) {
							switch(sexAction.getPriority()){
								case LOW:
									lowPriority.add(sexAction);
									break;
								case NORMAL:
									normalPriority.add(sexAction);
									break;
								case HIGH:
									highPriority.add(sexAction);
									break;
								case UNIQUE_MAX:
									uniqueMax.add(sexAction);
									break;
							}
						}
					}
				}
			} else {
				standardActions = true;
			}

			if(!uniqueMax.isEmpty()) {
				availableSexActionsPartner.addAll(uniqueMax);

			} else if(!highPriority.isEmpty()) {
				availableSexActionsPartner.addAll(highPriority);

			} else if(!normalPriority.isEmpty()) {
				availableSexActionsPartner.addAll(normalPriority);

			} else if(!lowPriority.isEmpty()) {
				availableSexActionsPartner.addAll(lowPriority);

			}

			// Backup just in case for some reason no orgasms were added:
			if (!availableSexActionsPartner.isEmpty()) {
				return;
			}
			
		}
		
		if (Main.game.getPlayer().getArousal() >= ArousalLevel.FIVE_ORGASM_IMMINENT.getMaximumValue()) { // Add orgasm reactions if ready to orgasm:
			for (SexActionInterface sexAction : Sex.getActionsAvailablePartner()) {
				if (sexAction.getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM) {
					if (sexAction.isAddedToAvailableSexActions()) {
						boolean dislikedAction = false;
						if(sexAction.getFetishes(activePartner)!=null) {
							for(Fetish f : sexAction.getFetishes(activePartner)) {
								if(f!=Fetish.FETISH_EXHIBITIONIST // Do not include exhibitionist, as otherwise NPC will never do anything in public sex.
										&& (activePartner.getFetishDesire(f)==FetishDesire.ONE_DISLIKE
											|| activePartner.getFetishDesire(f)==FetishDesire.ZERO_HATE)) {
									lowPriority.add(sexAction);
									dislikedAction = true;
									break;
								}
							}
						}
						if(!dislikedAction) {
							switch(sexAction.getPriority()){
								case LOW:
									lowPriority.add(sexAction);
									break;
								case NORMAL:
									normalPriority.add(sexAction);
									break;
								case HIGH:
									highPriority.add(sexAction);
									break;
								case UNIQUE_MAX:
									uniqueMax.add(sexAction);
									break;
							}
						}
					}
				}
			}
			
			if(!uniqueMax.isEmpty()) {
				availableSexActionsPartner.addAll(uniqueMax);

			} else if(!highPriority.isEmpty()) {
				availableSexActionsPartner.addAll(highPriority);

			} else if(!normalPriority.isEmpty()) {
				availableSexActionsPartner.addAll(normalPriority);

			} else if(!lowPriority.isEmpty()) {
				availableSexActionsPartner.addAll(lowPriority);

			}

			// Backup just in case for some reason no orgasms were added:
			if (!availableSexActionsPartner.isEmpty()) {
				return;
			}
			
		} else if(standardActions) {
			
			// Add actions:
			for (SexActionInterface sexAction : Sex.getActionsAvailablePartner()) {
				if (sexAction.isAddedToAvailableSexActions()
						&& (partnerAllowedToUseSelfActions || sexAction.getParticipantType()!=SexParticipantType.SELF)) {
					
					// Do not add action if the partner is resisting and this action is SUB_EAGER or SUB_NORMAL or is a self action
					// Do not add action if action does not correspond to the partner's preferred action pace
					if((Main.game.isNonConEnabled()
						&& getSexPace(activePartner)==SexPace.SUB_RESISTING
						&& ((sexAction.getSexPace()!=null && sexAction.getSexPace()!=SexPace.SUB_RESISTING) || sexAction.getParticipantType()==SexParticipantType.SELF))
							|| (sexAction.getSexPace()!=null && getSexPace(activePartner)!=sexAction.getSexPace())) {
						
					} else {
						// Add action as normal:
						boolean dislikedAction = false;
						if(sexAction.getFetishes(activePartner)!=null) {
							for(Fetish f : sexAction.getFetishes(activePartner)) {
								if(f!=Fetish.FETISH_NON_CON_SUB
										&& f!=Fetish.FETISH_EXHIBITIONIST // Do not include exhibitionist, as otherwise NPC will never do anything in public sex.
										&& (activePartner.getFetishDesire(f)==FetishDesire.ONE_DISLIKE
											|| activePartner.getFetishDesire(f)==FetishDesire.ZERO_HATE)) {
									dislikedAction = true;
									break;
								}
							}
						}
						if(!dislikedAction) {
							switch(sexAction.getPriority()){
								case LOW:
									lowPriority.add(sexAction);
									break;
								case NORMAL:
									normalPriority.add(sexAction);
									break;
								case HIGH:
									highPriority.add(sexAction);
									break;
								case UNIQUE_MAX:
									uniqueMax.add(sexAction);
									break;
							}
						}
					}
				}
			}
			
			if(!uniqueMax.isEmpty()) {
				availableSexActionsPartner.addAll(uniqueMax);

			} else if(!highPriority.isEmpty()) {
				availableSexActionsPartner.addAll(highPriority);

			} else if(!normalPriority.isEmpty()) {
				availableSexActionsPartner.addAll(normalPriority);

			} else if(!lowPriority.isEmpty()) {
				availableSexActionsPartner.addAll(lowPriority);
			}
			
			// Can do nothing if they have no other options:
			if (availableSexActionsPartner.isEmpty()) {
				availableSexActionsPartner.add(SexActionUtility.PARTNER_NONE);
			}
		}

	}

	public static List<SexActionInterface> getAvailableSexActionsPartner() {
		return availableSexActionsPartner;
	}

	/** Applies extra effects and generates extra description text. */
	private static void applyGenericDescriptionsAndEffects(GameCharacter activeCharacter, SexActionInterface sexAction) {

		// TODO for each character?
		GameCharacter targetCharacter = Sex.getTargetedPartner(activeCharacter);
		
		Map<GameCharacter, SexPace> initialPaces = new HashMap<>();
		for(GameCharacter participant : Sex.getAllParticipants()) {
			initialPaces.put(participant, Sex.getSexPace(participant));
		}
		
		
		// Increment arousal and lust:
		Map<GameCharacter, Float> arousalIncrements = new HashMap<>();
		Map<GameCharacter, Float> lustIncrements = new HashMap<>();
		
		arousalIncrements.put(activeCharacter, sexAction.getArousalGainSelf().getArousalIncreaseValue());
		if(!Sex.isMasturbation()) {
			arousalIncrements.put(targetCharacter, sexAction.getArousalGainTarget().getArousalIncreaseValue());
		}
		
		// Base lust gains are based on arousal gains:
		if(Sex.getSexPace(activeCharacter)==SexPace.SUB_RESISTING) {
			lustIncrements.put(activeCharacter, -2.5f);
		} else {
			lustIncrements.put(activeCharacter, Math.min(2.5f, Math.max(-2.5f, sexAction.getArousalGainSelf().getArousalIncreaseValue())));
		}
		
		if(Sex.getSexPace(targetCharacter)==SexPace.SUB_RESISTING) {
			lustIncrements.put(targetCharacter, -2.5f);
		} else {
			lustIncrements.put(targetCharacter, Math.min(2.5f, Math.max(-2.5f, sexAction.getArousalGainTarget().getArousalIncreaseValue())));
		}
		
		
		// Arousal increments for related status effects:
		for(StatusEffect se : activeCharacter.getStatusEffects()) {
			if(se.isSexEffect()) {
				arousalIncrements.put(activeCharacter, arousalIncrements.get(activeCharacter) + se.getArousalPerTurnSelf(activeCharacter));
				for(GameCharacter penetratingCharacter : Sex.getAllParticipants()) {
					if(!penetratingCharacter.equals(activeCharacter)) {
						arousalIncrements.putIfAbsent(penetratingCharacter, 0f);
						arousalIncrements.put(penetratingCharacter, arousalIncrements.get(penetratingCharacter) + se.getArousalPerTurnPartner(activeCharacter, penetratingCharacter));
					}
				}
			}
		}

		// Arousal increments for related fetishes:
		if(sexAction.getFetishes(activeCharacter)!=null && sexAction.getSexPace()!=SexPace.SUB_RESISTING) {
			for(Fetish f : sexAction.getFetishes(activeCharacter)) {
				if(activeCharacter.hasFetish(f)) {
					arousalIncrements.put(activeCharacter, arousalIncrements.get(activeCharacter) + activeCharacter.getFetishLevel(f).getBonusArousalIncrease());
					if(!Sex.isMasturbation()) {
						arousalIncrements.put(targetCharacter, arousalIncrements.get(targetCharacter) + activeCharacter.getFetishLevel(f).getBonusArousalIncreasePartner());
					}
					activeCharacter.incrementFetishExperience(f, f.getExperienceGainFromSexAction());
				}
				lustIncrements.put(activeCharacter, lustIncrements.get(activeCharacter) + activeCharacter.getFetishDesire(f).getLustIncrement());
			}
		}

		// Arousal increments for this target's related fetishes:
		if(sexAction.getFetishesForTargetedPartner(activeCharacter)!=null && Sex.getSexPace(targetCharacter)!=SexPace.SUB_RESISTING) {
			for(Fetish f : sexAction.getFetishesForTargetedPartner(activeCharacter)) {
				if(targetCharacter.hasFetish(f)) {
					arousalIncrements.put(targetCharacter, arousalIncrements.get(targetCharacter) + targetCharacter.getFetishLevel(f).getBonusArousalIncrease());
					if(!Sex.isMasturbation()) {
						arousalIncrements.put(activeCharacter, arousalIncrements.get(activeCharacter) + targetCharacter.getFetishLevel(f).getBonusArousalIncreasePartner());
					}
					targetCharacter.incrementFetishExperience(f, f.getExperienceGainFromSexAction());
				}
				lustIncrements.put(targetCharacter, lustIncrements.get(targetCharacter) + targetCharacter.getFetishDesire(f).getLustIncrement());
			}
		}
		
		// Increment lust:
		for(Entry<GameCharacter, Float> entry : lustIncrements.entrySet()) {
			if(sexAction.getActionType().isOrgasmOption() && entry.getKey().equals(activeCharacter)) { // Lose 50% lust when orgasm, making sure that lust doesn't fall into resisting range:
				float lustLoss = (entry.getKey().getLust()/2);
				if(Sex.getSexPace(activeCharacter)!=SexPace.SUB_RESISTING && !Sex.isDom(activeCharacter) && entry.getKey().getLust()-lustLoss < Math.min(entry.getKey().getLust(), LustLevel.ONE_HORNY.getMinimumValue()+5)) {
					entry.getKey().setLust(Math.min(entry.getKey().getLust(), LustLevel.ONE_HORNY.getMinimumValue()+5));
				} else {
					entry.getKey().incrementLust(-lustLoss);
				}
			} else {
				entry.getKey().incrementLust(entry.getValue());
			}
		}
		
		if(sexAction.getArousalGainSelf().getArousalIncreaseValue()<0) {
			arousalIncrements.put(activeCharacter, sexAction.getArousalGainSelf().getArousalIncreaseValue());
		}
		if(!Sex.isMasturbation() && sexAction.getArousalGainTarget().getArousalIncreaseValue()<0) {
			arousalIncrements.put(targetCharacter, sexAction.getArousalGainTarget().getArousalIncreaseValue());
		}
		
		// Modify arousal value based on lust:
		for(Entry<GameCharacter, Float> entry : arousalIncrements.entrySet()) {
			float arousal = entry.getValue();
			if(Sex.isMasturbation()) {
				arousal*=2;
			}
			entry.getKey().incrementArousal(Math.min(10, arousal * entry.getKey().getLustLevel().getArousalModifier()));
		}
		
		// Cummed in areas:
		
		// Add any areas that have been cummed in:
		// TODO Take into account condom being used on other penetrationTypes
		for(GameCharacter cumProvidor : Sex.getAllParticipants()) {
			for(GameCharacter cumTarget :Sex.getAllParticipants()) {
				if(cumProvidor.equals(activeCharacter) || cumTarget.equals(activeCharacter)) {
					if(cumProvidor.getPenisOrgasmCumQuantity() != CumProduction.ZERO_NONE) {
						if (sexAction.getAreasCummedIn(cumProvidor, cumTarget) != null) {
							if(!cumProvidor.isWearingCondom() || sexAction.ignoreCondom(cumProvidor)){
								Sex.incrementTimesCummedInside(cumProvidor, cumTarget, 1);
								
								for(SexAreaOrifice ot : sexAction.getAreasCummedIn(cumProvidor, cumTarget)) {
									cumTarget.incrementCumCount(new SexType(SexParticipantType.NORMAL, ot, SexAreaPenetration.PENIS));
									cumProvidor.incrementCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, ot));
									sexSB.append(cumTarget.ingestFluid(cumProvidor, cumProvidor.getCum().getType(), ot, cumProvidor.getPenisRawOrgasmCumQuantity(), cumProvidor.getCum().getFluidModifiers()));
									
								}
							}
						}
						
						if(sexAction.getAreasCummedOn(cumProvidor, cumTarget)!=null) {
							if(!cumProvidor.isWearingCondom() || sexAction.ignoreCondom(cumProvidor)){
								for(CoverableArea area : sexAction.getAreasCummedOn(cumProvidor, cumTarget)) {
									switch(area) {
										case NONE:
											break;
										case ASS:
											if (cumTarget.getHighestZLayerCoverableArea(CoverableArea.ASS)!=null) {
												cumTarget.getHighestZLayerCoverableArea(CoverableArea.ASS).setDirty(true);
											} else {
												cumTarget.addDirtySlot(InventorySlot.ANUS);
											}
											break;
										case ANUS:
											if (cumTarget.getHighestZLayerCoverableArea(CoverableArea.ANUS)!=null) {
												cumTarget.getHighestZLayerCoverableArea(CoverableArea.ANUS).setDirty(true);
											} else {
												cumTarget.addDirtySlot(InventorySlot.ANUS);
											}
											break;
										case BREASTS: case NIPPLES:
											if (cumTarget.getHighestZLayerCoverableArea(CoverableArea.BREASTS)!=null) {
												cumTarget.getHighestZLayerCoverableArea(CoverableArea.BREASTS).setDirty(true);
											} else {
												cumTarget.addDirtySlot(InventorySlot.CHEST);
											}
											break;
										case PENIS: case TESTICLES:
											if (cumTarget.getHighestZLayerCoverableArea(CoverableArea.PENIS)!=null) {
												cumTarget.getHighestZLayerCoverableArea(CoverableArea.PENIS).setDirty(true);
											} else {
												cumTarget.addDirtySlot(InventorySlot.GROIN);
											}
											break;
										 case VAGINA: case MOUND:
											if (cumTarget.getHighestZLayerCoverableArea(CoverableArea.VAGINA)!=null) {
												cumTarget.getHighestZLayerCoverableArea(CoverableArea.VAGINA).setDirty(true);
											} else {
												cumTarget.addDirtySlot(InventorySlot.GROIN);
											}
											break;
										case HAIR:
											if (cumTarget.getHighestZLayerCoverableArea(CoverableArea.HAIR)!=null) {
												cumTarget.getHighestZLayerCoverableArea(CoverableArea.HAIR).setDirty(true);
											} else {
												cumTarget.addDirtySlot(InventorySlot.HAIR);
											}
											break;
										case LEGS: case THIGHS:
											if (cumTarget.getHighestZLayerCoverableArea(CoverableArea.LEGS)!=null) {
												cumTarget.getHighestZLayerCoverableArea(CoverableArea.LEGS).setDirty(true);
											} else {
												cumTarget.addDirtySlot(InventorySlot.LEG);
											}
											break;
										case MOUTH:
	//										if (cumTarget.getHighestZLayerCoverableArea(CoverableArea.MOUTH)!=null) {
	//											cumTarget.getHighestZLayerCoverableArea(CoverableArea.MOUTH).setDirty(true);
	//										} else {
	//											cumTarget.addDirtySlot(InventorySlot.MOUTH);
	//										}
											break;
										case STOMACH:
											if (cumTarget.getHighestZLayerCoverableArea(CoverableArea.STOMACH)!=null) {
												cumTarget.getHighestZLayerCoverableArea(CoverableArea.STOMACH).setDirty(true);
											} else {
												cumTarget.addDirtySlot(InventorySlot.STOMACH);
											}
											break;
										case BACK:
											if (cumTarget.getHighestZLayerCoverableArea(CoverableArea.BACK)!=null) {
												cumTarget.getHighestZLayerCoverableArea(CoverableArea.BACK).setDirty(true);
											} else {
												cumTarget.addDirtySlot(InventorySlot.TORSO_OVER);
											}
											break;
									}
								}
							}
						}
					}
				}
			}
		}

		if(sexAction.getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM
				&& Sex.getCharacterPerformingAction().isPlayer()) {
			SexFlags.playerPreparedForOrgasm = true;
		}
		
		// Handle player orgasms:
		if(sexAction.getActionType()==SexActionType.ORGASM && Sex.getCharacterPerformingAction().isPlayer()) {
			// Condom removal:
			if(Main.game.getPlayer().isWearingCondom()){
				Main.game.getPlayer().getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()).setSealed(false);
				if(Main.game.getPlayer().getPenisRawOrgasmCumQuantity()>0) {
					sexSB.append(Main.game.getPlayer().addItem(
							AbstractItemType.generateFilledCondom(
									Main.game.getPlayer().getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()).getColour(), Main.game.getPlayer(), Main.game.getPlayer().getCum(), Main.game.getPlayer().getPenisRawOrgasmCumQuantity()),
							false, true));
				}
				Main.game.getPlayer().unequipClothingIntoVoid(Main.game.getPlayer().getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()), true, Main.game.getPlayer());
				
			}
			
			if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isVaginaSquirter()) {
				if(Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.VAGINA)!=null) {
					Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.VAGINA).setDirty(true);
					
				} else {
					Set<GameCharacter> charactersEatingOut = new HashSet<>(getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
					charactersEatingOut.addAll(getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaOrifice.VAGINA, SexAreaOrifice.MOUTH));
					
					for(GameCharacter character : charactersEatingOut) {
						sexSB.append(character.ingestFluid(
								Main.game.getPlayer(),
								Main.game.getPlayer().getGirlcumType(),
								SexAreaOrifice.MOUTH,
								5 * Main.game.getPlayer().getVaginaWetness().getValue(),
								Main.game.getPlayer().getGirlcum().getFluidModifiers()));
					}
				}
			}
			
			// Apply orgasm arousal resets:
			if(!Main.game.getPlayer().hasPenis() || Main.game.getPlayer().getPenisRawOrgasmCumQuantity()>0 || Main.game.getPlayer().hasVagina() || Sex.getNumberOfOrgasms(Main.game.getPlayer())>0) {
				incrementNumberOfOrgasms(Main.game.getPlayer(), 1);
				player().setArousal(0);
				Main.game.getPlayer().applyOrgasmCumEffect();
				
			} else {
				incrementNumberOfOrgasms(Main.game.getPlayer(), 1);
				Main.game.getPlayer().setArousal(20);
				Main.game.getPlayer().addStatusEffect(StatusEffect.FRUSTRATED_NO_ORGASM, 240+postSexDialogue.getMinutesPassed());
				sexSB.append("<p style='text-align:center'>Without producing any cum, your climax can't be counted as a real orgasm, and makes you feel [style.boldBad(frustrated and horny)]!</p>");
			}
			
			// Reset appropriate flags:
			SexFlags.partnerRequestedCreampie = false;
			SexFlags.partnerRequestedPullOut = false;
			
		}
		// Handle partner orgasms:
		if(sexAction.getActionType()==SexActionType.ORGASM  && !Sex.getCharacterPerformingAction().isPlayer()) {
			// Condom removal:
			if(activePartner.isWearingCondom()){
				activePartner.getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()).setSealed(false);
				if(activePartner.getPenisRawOrgasmCumQuantity()>0) {
					sexSB.append(Main.game.getPlayer().addItem(
							AbstractItemType.generateFilledCondom(
									activePartner.getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()).getColour(), activePartner, activePartner.getCum(), activePartner.getPenisRawOrgasmCumQuantity()),
							false, true));
				}
				activePartner.unequipClothingIntoVoid(activePartner.getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()), true, activePartner);
				
			}
			
			if(activePartner.hasVagina() && activePartner.isVaginaSquirter()) {
				if(activePartner.getLowestZLayerCoverableArea(CoverableArea.VAGINA)!=null) {
					activePartner.getLowestZLayerCoverableArea(CoverableArea.VAGINA).setDirty(true);
					
				} else {
					Set<GameCharacter> charactersEatingOut = new HashSet<>(getCharacterContactingSexArea(activePartner, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
					charactersEatingOut.addAll(getCharacterContactingSexArea(activePartner, SexAreaOrifice.VAGINA, SexAreaOrifice.MOUTH));
					
					for(GameCharacter character : charactersEatingOut) {
						sexSB.append(character.ingestFluid(
								activePartner,
								activePartner.getGirlcumType(),
								SexAreaOrifice.MOUTH,
								5 * activePartner.getVaginaWetness().getValue(),
								activePartner.getGirlcum().getFluidModifiers()));
					}
				}
			}
			
			// Apply orgasm arousal resets:
			if(!activePartner.hasPenis() || activePartner.getPenisRawOrgasmCumQuantity()>0 || activePartner.hasVagina() || Sex.getNumberOfOrgasms(activePartner)>0) {
				incrementNumberOfOrgasms(activePartner, 1);
				activePartner.setArousal(0);
				activePartner.applyOrgasmCumEffect();
				
			} else {
				incrementNumberOfOrgasms(activePartner, 1);
				activePartner.setArousal(20);
				activePartner.addStatusEffect(StatusEffect.FRUSTRATED_NO_ORGASM, 240+postSexDialogue.getMinutesPassed());
				sexSB.append(UtilText.parse(activePartner,
						"<p style='text-align:center'>Without producing any cum, [npc.namePos] climax can't be counted as a real orgasm, and makes [npc.name] feel [style.boldBad(frustrated and horny)]!</p>"));
			}

			// Reset appropriate flags:
			SexFlags.playerRequestedCreampie = false;
			SexFlags.playerRequestedPullOut = false;
			SexFlags.playerPreparedForOrgasm = false;
		}

		// Handle if parts have just become exposed:
		if (sexAction == SexActionUtility.CLOTHING_REMOVAL) {
			for(GameCharacter character : Sex.getAllParticipants()) {
				handleExposedDescriptions(character, false);
			}
		}
		
		// Only apply penetration effects if this action isn't an orgasm, and it isn't the end of sex. (Otherwise, ongoing descriptions get appended after the main description, which usually don't make sense.) TODO
		if (!Sex.getOrgasmActionsPlayer().contains(sexAction)
				&& (Sex.isMasturbation() || (!Sex.isMasturbation() && !Sex.getOrgasmActionsPartner().contains(sexAction)))
				&& sexAction.getActionType() != SexActionType.POSITIONING
				&& !sexAction.endsSex()) {

			for(GameCharacter character : Sex.getAllParticipants()) {
				for(Entry<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> entry : ongoingActionsMap.get(character).entrySet()) {
					for(GameCharacter characterTarget : Sex.getAllParticipants()) {
						if(entry.getValue().containsKey(characterTarget)) {
							for(SexAreaInterface sArea : entry.getValue().get(characterTarget)) {
								if(entry.getKey().isPenetration()) {//TODO
									applyPenetrationEffects(character, (SexAreaPenetration)entry.getKey(), characterTarget, sArea);
								}
								List<Fetish> selfFetishes = sexAction.getFetishesFromPenetrationAndOrificeTypes(character, entry.getKey(), characterTarget, sArea, true);
								List<Fetish> targetFetishes = sexAction.getFetishesFromPenetrationAndOrificeTypes(character, entry.getKey(), characterTarget, sArea, false);
								
								// Half lust and xp from ongoing:
								for(Fetish f : selfFetishes) {
									character.incrementLust(character.getFetishDesire(f).getLustIncrement()/2);
									character.incrementFetishExperience(f, f.getExperienceGainFromSexAction()/2);
								}
								for(Fetish f : targetFetishes) {
									characterTarget.incrementLust(characterTarget.getFetishDesire(f).getLustIncrement()/2);
									characterTarget.incrementFetishExperience(f, f.getExperienceGainFromSexAction()/2);
								}
							}
						}
					}
				}
			}
			
		}
		
		calculateWetAreas(false);
		
		for(Entry<GameCharacter, SexPace> entry : initialPaces.entrySet()) {
			SexPace finalPace = Sex.getSexPace(entry.getKey());
			if(entry.getValue() != finalPace && !entry.getKey().isPlayer()) {
				sexSB.append("<p style='text-align:center;'>"
						+ "<b style='color:"+finalPace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(finalPace.getName())+" Pace</b><br/>");
				switch(finalPace) {
					case DOM_GENTLE:
						sexSB.append(UtilText.parse(entry.getKey(),
								"[npc.Name] lets out a soft sigh as [npc.she] calms down a little..."));
						break;
					case DOM_NORMAL:
						sexSB.append(UtilText.parse(entry.getKey(),
								"[npc.Name] lets out "+(entry.getValue()==SexPace.DOM_GENTLE?"[npc.a_moan+] as [npc.she] starts getting more turned on":"a soft [npc.moan] as [npc.she] calms down a little")+"..."));
						break;
					case DOM_ROUGH:
						sexSB.append(UtilText.parse(entry.getKey(),
								"[npc.Name] lets out a wild [npc.moan] as [npc.she] finds [npc.herself] filled with lust..."));
						break;
					case SUB_EAGER:
						sexSB.append(UtilText.parse(entry.getKey(),
								"[npc.Name] lets out a desperate [npc.moan] as [npc.she] finds [npc.herself] filled with lust..."));
						break;
					case SUB_NORMAL:
						sexSB.append(UtilText.parse(entry.getKey(),
								"[npc.Name] lets out "+(entry.getValue()==SexPace.SUB_RESISTING?"[npc.a_moan+] as [npc.she] starts getting more turned on":"a soft [npc.moan] as [npc.she] calms down a little")+"..."));
						break;
					case SUB_RESISTING:
						sexSB.append(UtilText.parse(entry.getKey(),
								"[npc.Name] lets out an uncomfortable whine as [npc.she] suddenly finds [npc.herself] not happy with the current situation..."));
						break;
				}
				sexSB.append("</p>");
			}
		}
	}
	
	private static void handleExposedDescriptions(GameCharacter characterBeingExposed, boolean atStartOfSex) {
		// Player:
		if(characterBeingExposed.isPlayer()) {
			if (!areasExposed.get(Main.game.getPlayer()).contains(CoverableArea.ANUS)) {
				if (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, false)) {
					sexSB.append(
							formatCoverableAreaBecomingExposed(
									(atStartOfSex
											?"Your [pc.asshole+] was already exposed before starting sex!"
											:"Your [pc.asshole+] is now exposed!"))
							+ sexManager.getPlayerAssRevealReaction()
							+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, SexAreaOrifice.ANUS)));
					areasExposed.get(Main.game.getPlayer()).add(CoverableArea.ANUS);
				}
			}
			if (!areasExposed.get(Main.game.getPlayer()).contains(CoverableArea.PENIS)) {
				if (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, false)) {
					if (Main.game.getPlayer().hasPenis()) {
						sexSB.append(
							formatCoverableAreaBecomingExposed(
								(atStartOfSex
										?"Your [pc.cock+] was already exposed before starting sex!"
										:"Your [pc.cock+] is now exposed!"))
								+ sexManager.getPlayerPenisRevealReaction()
								+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, SexAreaPenetration.PENIS)));
					}
					areasExposed.get(Main.game.getPlayer()).add(CoverableArea.PENIS);
				}
			}
			if (!areasExposed.get(Main.game.getPlayer()).contains(CoverableArea.VAGINA)) {
				if (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, false)) {
					if (Main.game.getPlayer().hasVagina()) {
						sexSB.append(
							formatCoverableAreaBecomingExposed(
								(atStartOfSex
										?"Your [pc.pussy+] was already exposed before starting sex!"
										:"Your [pc.pussy+] is now exposed!"))
								+ sexManager.getPlayerVaginaRevealReaction()
								+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, SexAreaOrifice.VAGINA)));
	
					} else if (Main.game.getPlayer().getVaginaType() == VaginaType.NONE && Main.game.getPlayer().getPenisType() == PenisType.NONE) {
						sexSB.append(
								formatCoverableAreaBecomingExposed(
										(atStartOfSex
												?"Your doll-like mound was already exposed before starting sex!"
												:"Your doll-like mound is now exposed!"))
								+ sexManager.getPlayerMoundRevealReaction());
					}
					areasExposed.get(Main.game.getPlayer()).add(CoverableArea.VAGINA);
				}
			}
			if (!areasExposed.get(Main.game.getPlayer()).contains(CoverableArea.NIPPLES)) {
				if (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, false)) {
					sexSB.append(
							formatCoverableAreaBecomingExposed(
									(atStartOfSex
											?"Your [pc.nipples+] were already exposed before starting sex!"
											:"Your [pc.nipples+] are now exposed!"))
							+ sexManager.getPlayerBreastsRevealReaction()
							+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, SexAreaOrifice.NIPPLE)));
					areasExposed.get(Main.game.getPlayer()).add(CoverableArea.NIPPLES);
				}
			}
			
		} else {
			// Partner:
			if (!areasExposed.get(characterBeingExposed).contains(CoverableArea.ANUS)) {
				if (characterBeingExposed.isAbleToAccessCoverableArea(CoverableArea.ANUS, false)) {
					sexSB.append(UtilText.parse(characterBeingExposed,
							formatCoverableAreaBecomingExposed(
									(atStartOfSex
											?"[npc.NamePos] [npc.asshole+] was already exposed before starting sex!"
											:"[npc.NamePos] [npc.asshole+] is now exposed!")))
							+ sexManager.getPartnerAssRevealReaction()
							+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, SexAreaOrifice.ANUS)));
					areasExposed.get(characterBeingExposed).add(CoverableArea.ANUS);
				}
			}
			if (!areasExposed.get(characterBeingExposed).contains(CoverableArea.PENIS)) {
				if (characterBeingExposed.isAbleToAccessCoverableArea(CoverableArea.PENIS, false)) {
					if (characterBeingExposed.hasPenis()) {
						sexSB.append(UtilText.parse(characterBeingExposed,
								formatCoverableAreaBecomingExposed(
										(atStartOfSex
												?"[npc.NamePos] [npc.cock+] was already exposed before starting sex!"
												:"[npc.NamePos] [npc.cock+] is now exposed!")))
								+ sexManager.getPartnerPenisRevealReaction()
								+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, SexAreaPenetration.PENIS)));
					}
					areasExposed.get(characterBeingExposed).add(CoverableArea.PENIS);
				}
			}
			if (!areasExposed.get(characterBeingExposed).contains(CoverableArea.VAGINA)) {
				if (characterBeingExposed.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false)) {
					if (characterBeingExposed.hasVagina()) {
						sexSB.append(UtilText.parse(characterBeingExposed,
								formatCoverableAreaBecomingExposed(
										(atStartOfSex
												?"[npc.NamePos] [npc.pussy+] was already exposed before starting sex!"
												:"[npc.NamePos] [npc.pussy+] is now exposed!")))
								+ sexManager.getPartnerVaginaRevealReaction()
								+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, SexAreaOrifice.VAGINA)));
	
					} else if (characterBeingExposed.getVaginaType() == VaginaType.NONE && characterBeingExposed.getPenisType() == PenisType.NONE) {
						sexSB.append(UtilText.parse(characterBeingExposed,
								formatCoverableAreaBecomingExposed(
										(atStartOfSex
												?"[npc.NamePos] doll-like mound was already exposed before starting sex!"
												:"[npc.NamePos] doll-like mound is now exposed!")))
								+ sexManager.getPartnerMoundRevealReaction());
					}
					areasExposed.get(characterBeingExposed).add(CoverableArea.VAGINA);
				}
			}
			if (!areasExposed.get(characterBeingExposed).contains(CoverableArea.NIPPLES)) {
				if (characterBeingExposed.isAbleToAccessCoverableArea(CoverableArea.NIPPLES, false)) {
					sexSB.append(UtilText.parse(characterBeingExposed,
							formatCoverableAreaBecomingExposed(
									(atStartOfSex
											?"[npc.NamePos] [npc.nipples+] were already exposed before starting sex!"
											:"[npc.NamePos] [npc.nipples+] are now exposed!")))
								+ sexManager.getPartnerBreastsRevealReaction()
								+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, SexAreaOrifice.NIPPLE)));
					areasExposed.get(characterBeingExposed).add(CoverableArea.NIPPLES);
				}
			}
		}
	}

	
	private static void calculateWetAreas(boolean onSexInit) {

		// Add starting lube:
		addOrificeLubrication(Main.game.getPlayer(), SexAreaOrifice.MOUTH, LubricationType.PLAYER_SALIVA, !onSexInit);
		addPenetrationTypeLubrication(Main.game.getPlayer(), SexAreaPenetration.TONGUE, LubricationType.PLAYER_SALIVA, !onSexInit);
		
		
		// Add player lubrication from cum:
		if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_ANUS)) {
			addOrificeLubrication(Main.game.getPlayer(), SexAreaOrifice.ANUS, LubricationType.OTHER_CUM, !onSexInit);
		}
		if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_NIPPLES)) {
			addOrificeLubrication(Main.game.getPlayer(), SexAreaOrifice.NIPPLE, LubricationType.OTHER_CUM, !onSexInit);
		}
		if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)) {
			addOrificeLubrication(Main.game.getPlayer(), SexAreaOrifice.VAGINA, LubricationType.OTHER_CUM, !onSexInit);
		}

		// Add milk in nipples:
		if(Main.game.getPlayer().getBreastRawMilkStorageValue()>0) {
			addOrificeLubrication(Main.game.getPlayer(), SexAreaOrifice.NIPPLE, LubricationType.PLAYER_MILK, !onSexInit);
		}
		
		// Add player natural lubrications:
		if(Main.game.getPlayer().getArousal() >= Main.game.getPlayer().getAssWetness().getArousalNeededToGetVaginaWet()) {
			addOrificeLubrication(Main.game.getPlayer(), SexAreaOrifice.ANUS, LubricationType.PLAYER_ANAL_LUBE, !onSexInit);
		}
		if(Main.game.getPlayer().hasPenisIgnoreDildo()) {
			if(Main.game.getPlayer().getArousal() >= Main.game.getPlayer().getPenisCumStorage().getArousalNeededToStartPreCumming()) {
				addPenetrationTypeLubrication(Main.game.getPlayer(), SexAreaPenetration.PENIS, LubricationType.PLAYER_PRECUM, !onSexInit);
				addOrificeLubrication(Main.game.getPlayer(), SexAreaOrifice.URETHRA_PENIS, LubricationType.PLAYER_PRECUM, !onSexInit);
			}
		}
		if(Main.game.getPlayer().hasVagina()) {
			if(Main.game.getPlayer().getArousal() >= Main.game.getPlayer().getVaginaWetness().getArousalNeededToGetVaginaWet()) {
				addOrificeLubrication(Main.game.getPlayer(), SexAreaOrifice.VAGINA, LubricationType.PLAYER_GIRLCUM, !onSexInit);
			}
		}
		
		for(GameCharacter character : Sex.getAllParticipants()) {
			if(character.getBodyMaterial()==BodyMaterial.SLIME && onSexInit) {
				for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
					addOrificeLubrication(character, orifice, LubricationType.SLIME, !onSexInit);
				}
			}
			
			if(!character.isPlayer()) {
				addOrificeLubrication(character, SexAreaOrifice.MOUTH, LubricationType.PARTNER_SALIVA, !onSexInit);
				addPenetrationTypeLubrication(character, SexAreaPenetration.TONGUE, LubricationType.PARTNER_SALIVA, !onSexInit);
				
				if(character.getBreastRawMilkStorageValue()>0) {
					addOrificeLubrication(character, SexAreaOrifice.NIPPLE, LubricationType.PARTNER_MILK, !onSexInit);
				}

				// Add partner lubrication from cum:
				if(character.hasStatusEffect(StatusEffect.CREAMPIE_ANUS)) {
					addOrificeLubrication(character, SexAreaOrifice.ANUS, LubricationType.OTHER_CUM, !onSexInit);
				}
				if(character.hasStatusEffect(StatusEffect.CREAMPIE_NIPPLES)) {
					addOrificeLubrication(character, SexAreaOrifice.NIPPLE, LubricationType.OTHER_CUM, !onSexInit);
				}
				if(character.hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)) {
					addOrificeLubrication(character, SexAreaOrifice.VAGINA, LubricationType.OTHER_CUM, !onSexInit);
				}
				
				// Add partner natural lubrications:
				if(character.getArousal() >= character.getAssWetness().getArousalNeededToGetVaginaWet()) {
					addOrificeLubrication(character, SexAreaOrifice.ANUS, LubricationType.PARTNER_ANAL_LUBE, !onSexInit);
				}
				if(character.hasPenisIgnoreDildo()) {
					if(character.getArousal() >= character.getPenisCumStorage().getArousalNeededToStartPreCumming()) {
						addPenetrationTypeLubrication(character, SexAreaPenetration.PENIS, LubricationType.PARTNER_PRECUM);
						addOrificeLubrication(character, SexAreaOrifice.URETHRA_PENIS, LubricationType.PARTNER_PRECUM, !onSexInit);
					}
				}
				if(character.hasVagina()) {
					if(character.getArousal() >= character.getVaginaWetness().getArousalNeededToGetVaginaWet()) {
						addOrificeLubrication(character, SexAreaOrifice.VAGINA, LubricationType.PARTNER_GIRLCUM, !onSexInit);
					}
				}
			}
		}
		
		// Calculate lubrication transfers:
		for(GameCharacter characterPenetrating : Sex.getAllParticipants()) {
			for(GameCharacter characterPenetrated : Sex.getAllParticipants()) {
				for(Entry<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> entry : ongoingActionsMap.get(characterPenetrating).entrySet()) {
					if(entry.getValue().containsKey(characterPenetrated)) {
						for(SexAreaInterface sArea : entry.getValue().get(characterPenetrated)) {
							transferLubrication(characterPenetrating, entry.getKey(), characterPenetrated, sArea);
						}
					}
				}
			}
		}
	}
	
	public static void transferLubrication(GameCharacter character, SexAreaInterface characterArea, GameCharacter targetCharacter, SexAreaInterface targetArea) {
		List<String> lubricationTransferred = new ArrayList<>();
		boolean lastLubricationPlural = false;
		
		for(LubricationType lt : wetAreas.get(character).get(characterArea)) {
			if(!wetAreas.get(targetCharacter).get(targetArea).contains(lt)) {
				wetAreas.get(targetCharacter).get(targetArea).add(lt);
				lubricationTransferred.add(lt.getName());
				lastLubricationPlural = lt.isPlural();
			}
		}
		
		if(!lubricationTransferred.isEmpty()) {
			sexSB.append(formatCoverableAreaGettingWet(
					Util.capitaliseSentence(Util.stringsToStringList(lubricationTransferred, false))+" quickly lubricate"+(lastLubricationPlural?" ":"s ")
						+(targetCharacter.isPlayer()?"your ":targetCharacter.getName("the")+"'s ")+targetArea.getName(targetCharacter)+"."));
		}
		
		lubricationTransferred.clear();
		
		for(LubricationType lt : wetAreas.get(targetCharacter).get(targetArea)) {
			if(!wetAreas.get(character).get(characterArea).contains(lt)) {
				wetAreas.get(character).get(characterArea).add(lt);
				lubricationTransferred.add(lt.getName());
				lastLubricationPlural = lt.isPlural();
			}
		}
		
		if(!lubricationTransferred.isEmpty()) {
			sexSB.append(formatCoverableAreaGettingWet(
					Util.capitaliseSentence(Util.stringsToStringList(lubricationTransferred, false))+" quickly lubricate"+(lastLubricationPlural?" ":"s ")
						+(character.isPlayer()?"your ":character.getName("the")+"'s ")+characterArea.getName(character)+"."));
		}
	}
	
	private static String getLubricationDescription(GameCharacter character, SexAreaInterface area) {
		if(wetAreas.get(character).get(area).isEmpty()) {
			return "";
		}
		StringBuilder description = new StringBuilder((character.isPlayer() ?"Your " :"[npc.NamePos] ")+area.getName(character) +" "+(area.isPlural()?"are":"is")+" lubricated with ");
		List<String> lubes = new ArrayList<>();
		for(LubricationType lube : wetAreas.get(character).get(area)) {
			lubes.add(lube.getName());
		}
		description.append(Util.stringsToStringList(lubes, false)+".");
		return description.toString();
	}
	
	public static void addOrificeLubrication(GameCharacter character, SexAreaOrifice orifice, LubricationType lubrication) {
		addOrificeLubrication(character, orifice, lubrication, true);
	}
	
	public static void addOrificeLubrication(GameCharacter character, SexAreaOrifice orifice, LubricationType lubrication, boolean appendTextToSex) {
		boolean appendDescription =
				!(orifice==SexAreaOrifice.MOUTH && lubrication==LubricationType.PLAYER_SALIVA) // Don't give descriptions of saliva lubricating your own mouth.
				&& !(orifice==SexAreaOrifice.MOUTH && lubrication==LubricationType.PARTNER_SALIVA)
				&& (orifice==SexAreaOrifice.VAGINA?activePartner!=null && activePartner.isCoverableAreaExposed(CoverableArea.VAGINA):true)
				&& (orifice==SexAreaOrifice.ANUS?activePartner!=null && activePartner.isCoverableAreaExposed(CoverableArea.ANUS):true)
				&& (orifice==SexAreaOrifice.NIPPLE?activePartner!=null && activePartner.isCoverableAreaExposed(CoverableArea.NIPPLES):true);
		
		if(wetAreas.get(character).get(orifice).add(lubrication)){
			if(appendDescription && appendTextToSex) {
				sexSB.append(formatCoverableAreaGettingWet((character.isPlayer()?"Your ":"[npc.NamePos] ")+orifice.getName(character)+" "+(orifice.isPlural()?"are":"is")+" quickly lubricated by "+lubrication.getName()+"."));
			}
		}
	}

	public static void addPenetrationTypeLubrication(GameCharacter character, SexAreaPenetration penetrationType, LubricationType lubrication) {
		addPenetrationTypeLubrication(character, penetrationType, lubrication, true);
	}
	
	public static void addPenetrationTypeLubrication(GameCharacter character, SexAreaPenetration penetrationType, LubricationType lubrication, boolean appendTextToSex) {
		boolean appendDescription =
				!(penetrationType==SexAreaPenetration.TONGUE && (lubrication==LubricationType.PLAYER_SALIVA || lubrication==LubricationType.PARTNER_SALIVA)) // Don't give descriptions of saliva lubricating your own tongue.
				&& (penetrationType==SexAreaPenetration.PENIS ? (!character.isPlayer() ? activePartner.isCoverableAreaExposed(CoverableArea.PENIS):true) :true);
		
		if((penetrationType == SexAreaPenetration.PENIS
				&& (lubrication == LubricationType.PLAYER_PRECUM || lubrication == LubricationType.PLAYER_CUM || lubrication == LubricationType.PARTNER_PRECUM || lubrication == LubricationType.PARTNER_CUM)
					? !character.isWearingCondom()
					: true)) { // Can't lubricate if covered by condom
			if(wetAreas.get(character).get(penetrationType).add(lubrication)){
				if(appendDescription && appendTextToSex) {
					sexSB.append(formatCoverableAreaGettingWet((character.isPlayer()?"Your ":"[npc.NamePos] ")+penetrationType.getName(character)+" "+(penetrationType.isPlural()?"are":"is")+" quickly lubricated by "+lubrication.getName()+"."));
				}
			}
		}
	}


	
	public static void applyOngoingAction(GameCharacter characterPerformingAction, SexAreaInterface performerArea, GameCharacter characterTargeted, SexAreaInterface targetedArea) {
		
		// Free up orifice and penetrator:
		stopOngoingAction(characterPerformingAction, performerArea, characterTargeted, targetedArea);

		ongoingActionsMap.get(characterPerformingAction).get(performerArea).putIfAbsent(characterTargeted, new HashSet<>());
		ongoingActionsMap.get(characterPerformingAction).get(performerArea).get(characterTargeted).add(targetedArea);

		ongoingActionsMap.get(characterTargeted).get(targetedArea).putIfAbsent(characterPerformingAction, new HashSet<>());
		ongoingActionsMap.get(characterTargeted).get(targetedArea).get(characterPerformingAction).add(performerArea);
		
		initialPenetrations.putIfAbsent(characterTargeted, new HashSet<>());
		initialPenetrations.get(characterTargeted).add(targetedArea);

		initialPenetrations.putIfAbsent(characterPerformingAction, new HashSet<>());
		initialPenetrations.get(characterPerformingAction).add(performerArea);
		
		if(characterPerformingAction != null && characterTargeted != null) {
			SexType relatedSexTypePerformer = new SexType(SexParticipantType.NORMAL, performerArea, targetedArea);
			SexType relatedSexTypeTargeted = new SexType(SexParticipantType.NORMAL, targetedArea, performerArea);
			
			characterPerformingAction.incrementSexCount(relatedSexTypePerformer);
			characterTargeted.incrementSexCount(relatedSexTypeTargeted);

			characterPerformingAction.addSexPartner(characterTargeted, relatedSexTypePerformer);
			characterTargeted.addSexPartner(characterPerformingAction, relatedSexTypeTargeted);
			
		} else {
			System.err.println("Warning! Sex.applyPenetration() is finding 'characterPenetrated' or 'characterPenetrating' to be null!!!");
		}
	}
	
	public static void stopAllOngoingActions(GameCharacter characterPerformingAction, GameCharacter characterTargeted) {
		for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
			ongoingActionsMap.get(characterPerformingAction).get(orifice).remove(characterTargeted);
			ongoingActionsMap.get(characterTargeted).get(orifice).remove(characterPerformingAction);
		}
		for(SexAreaPenetration penetration : SexAreaPenetration.values()) {
			ongoingActionsMap.get(characterPerformingAction).get(penetration).remove(characterTargeted);
			ongoingActionsMap.get(characterTargeted).get(penetration).remove(characterPerformingAction);
		}
	}

	public static void stopOngoingAction(GameCharacter characterPerformingAction, SexAreaInterface performerArea, GameCharacter characterTargeted, SexAreaInterface targetedArea) {
		stopOngoingAction(characterPerformingAction, performerArea, characterTargeted, targetedArea, true);
	}
	
	public static void stopOngoingAction(GameCharacter characterPerformingAction, SexAreaInterface performerArea, GameCharacter characterTargeted, SexAreaInterface targetedArea, boolean appendRemovalText) {
		
		if(ongoingActionsMap.get(characterPerformingAction).get(performerArea).containsKey(characterTargeted)) {
			if(ongoingActionsMap.get(characterPerformingAction).get(performerArea).get(characterTargeted).remove(targetedArea)) {
				ongoingActionsMap.get(characterTargeted).get(targetedArea).get(characterPerformingAction).remove(performerArea);
				if(appendRemovalText && characterTargeted!=null && performerArea.isPenetration() && targetedArea.isOrifice()) {
					//TODO add variants for pen/pen and ori/ori
					sexSB.append(formatStopPenetration(characterTargeted.getStopPenetrationDescription(characterPerformingAction, (SexAreaPenetration)performerArea, characterTargeted, (SexAreaOrifice)targetedArea)));
				}
			}
			
			if(ongoingActionsMap.get(characterPerformingAction).get(performerArea).containsKey(characterTargeted)
					&& ongoingActionsMap.get(characterPerformingAction).get(performerArea).get(characterTargeted).isEmpty()) {
				ongoingActionsMap.get(characterPerformingAction).get(performerArea).remove(characterTargeted);
			}
			if(ongoingActionsMap.get(characterTargeted).get(targetedArea).containsKey(characterPerformingAction)
					&& ongoingActionsMap.get(characterTargeted).get(targetedArea).get(characterPerformingAction).isEmpty()) {
				ongoingActionsMap.get(characterTargeted).get(targetedArea).remove(characterPerformingAction);
			}
		}
		
	}

	private static void applyPenetrationEffects(GameCharacter characterPenetrating, SexAreaPenetration penetrationType, GameCharacter characterPenetrated, SexAreaInterface orifice) { //TODO formatting
		
		if(orifice.isOrifice()) {
			SexAreaOrifice actualOrifice = (SexAreaOrifice) orifice;
			
			SexType relatedSexTypeForCharacterPenetrating = new SexType(SexParticipantType.NORMAL, penetrationType, orifice);
			SexType relatedSexTypeForCharacterPenetrated = new SexType(SexParticipantType.NORMAL, orifice, penetrationType);
			
			String penileVirginityLoss = "";
			
			if (penetrationType == SexAreaPenetration.PENIS) {
				if(characterPenetrating.isPenisVirgin()
						&& characterPenetrating.hasPenisIgnoreDildo()
						&& actualOrifice.isTakesPenisVirginity()) {
					penileVirginityLoss = characterPenetrating.getVirginityLossPenetrationDescription(characterPenetrating, SexAreaPenetration.PENIS, characterPenetrated, actualOrifice);
					if(characterPenetrated.hasFetish(Fetish.FETISH_DEFLOWERING)) {
						characterPenetrated.incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(characterPenetrated), true);
						characterPenetrated.incrementFetishExperience(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_DEFLOWERING.getExperienceGainFromSexAction());
					}
					characterPenetrating.setVirginityLoss(relatedSexTypeForCharacterPenetrating, characterPenetrated.getName("a") + " " + characterPenetrated.getLostVirginityDescriptor());
					characterPenetrating.setPenisVirgin(false);
				}
				
			}
			
			if (actualOrifice == SexAreaOrifice.ASS) {
				if (initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.ASS)) {
					sexSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					
					initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.ASS);
					
				} else {
					sexSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
				}
					
			} else if (actualOrifice == SexAreaOrifice.BREAST) {
				if (initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.BREAST)) {
					sexSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					
					initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.BREAST);
					
				} else {
					sexSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
				}
					
			} else if (actualOrifice == SexAreaOrifice.ANUS) {
				if (initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.ANUS)) {
					sexSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					
					if (characterPenetrated.isAssVirgin()) {
						if (penetrationType.isTakesVirginity()) {
							sexSB.append(characterPenetrating.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, characterPenetrated, SexAreaOrifice.ANUS));
							if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
								characterPenetrating.incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating), true);
								characterPenetrating.incrementFetishExperience(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_DEFLOWERING.getExperienceGainFromSexAction());
							}
							characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating.getName("a") + " " + characterPenetrating.getLostVirginityDescriptor());
							characterPenetrated.setAssVirgin(false);
						}
					}
					initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.ANUS);
				} else {
					sexSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
				}
					
			} else if (actualOrifice == SexAreaOrifice.VAGINA) {
				if (initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.VAGINA)) {
					sexSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					
					if (characterPenetrated.isVaginaVirgin()) {
						if (penetrationType.isTakesVirginity()) {
							sexSB.append(characterPenetrating.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, characterPenetrated, SexAreaOrifice.VAGINA));
							if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
								characterPenetrating.incrementExperience(Fetish.getExperienceGainFromTakingVaginalVirginity(characterPenetrating), true);
								characterPenetrating.incrementFetishExperience(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_DEFLOWERING.getExperienceGainFromSexAction());
							}
							characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating.getName("a") + " " + characterPenetrating.getLostVirginityDescriptor());
							characterPenetrated.setVaginaVirgin(false);
						}
					}
					initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.VAGINA);
				} else {
					sexSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
				}
				
			} else if (actualOrifice == SexAreaOrifice.NIPPLE) {
				if (initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.NIPPLE)) {
						sexSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
						
						if (characterPenetrated.isNippleVirgin()) {
							if (penetrationType.isTakesVirginity()) {
								sexSB.append(characterPenetrating.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, characterPenetrated, SexAreaOrifice.NIPPLE));
								if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
									characterPenetrating.incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating), true);
									characterPenetrating.incrementFetishExperience(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_DEFLOWERING.getExperienceGainFromSexAction());
								}
								characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating.getName("a") + " " + characterPenetrating.getLostVirginityDescriptor());
								characterPenetrated.setNippleVirgin(false);
							}
						}
						initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.NIPPLE);
					} else {
						sexSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					}
					
			} else if (actualOrifice == SexAreaOrifice.URETHRA_PENIS) {
				if (initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.URETHRA_PENIS)) {
						sexSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
						
						if (characterPenetrated.isUrethraVirgin()) {
							if (penetrationType.isTakesVirginity()) {
								sexSB.append(characterPenetrating.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, characterPenetrated, SexAreaOrifice.URETHRA_PENIS));
								if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
									characterPenetrating.incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating), true);
									characterPenetrating.incrementFetishExperience(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_DEFLOWERING.getExperienceGainFromSexAction());
								}
								characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating.getName("a") + " " + characterPenetrating.getLostVirginityDescriptor());
								characterPenetrated.setUrethraVirgin(false);
							}
						}
						initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.URETHRA_PENIS);
					} else {
						sexSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					}
				
			} else if (actualOrifice == SexAreaOrifice.URETHRA_VAGINA) {
				if (initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.URETHRA_VAGINA)) {
					sexSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					
					if (characterPenetrated.isVaginaUrethraVirgin()) {
						if (penetrationType.isTakesVirginity()) {
							sexSB.append(characterPenetrating.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, characterPenetrated, SexAreaOrifice.URETHRA_VAGINA));
							if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
								characterPenetrating.incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating), true);
								characterPenetrating.incrementFetishExperience(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_DEFLOWERING.getExperienceGainFromSexAction());
							}
							characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating.getName("a") + " " + characterPenetrating.getLostVirginityDescriptor());
							characterPenetrated.setVaginaUrethraVirgin(false);
						}
					}
					initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.URETHRA_VAGINA);
				} else {
					sexSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
				}
			
			} else if (actualOrifice == SexAreaOrifice.MOUTH) {
				if (initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.MOUTH)) {
						sexSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
						
						if (characterPenetrated.isFaceVirgin()) {
							if (penetrationType.isTakesVirginity()) {
								sexSB.append(characterPenetrating.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, characterPenetrated, SexAreaOrifice.MOUTH));
								if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
									characterPenetrating.incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating), true);
									characterPenetrating.incrementFetishExperience(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_DEFLOWERING.getExperienceGainFromSexAction());
								}
								characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating.getName("a") + " " + characterPenetrating.getLostVirginityDescriptor());
								characterPenetrated.setFaceVirgin(false);
							}
						}
						initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.MOUTH);
					} else {
						sexSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					}
					
			}
			
			sexSB.append(penileVirginityLoss);
			
		} else {
			if (penetrationType == SexAreaPenetration.FINGER && orifice == SexAreaPenetration.PENIS) {
				if (initialPenetrations.get(characterPenetrated).contains(SexAreaPenetration.PENIS)) {
					sexSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, orifice)));
					
					initialPenetrations.get(characterPenetrated).remove(SexAreaPenetration.PENIS);
					
				} else {
					sexSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, orifice)));
				}
				
			}
		}
		
		
		// TODO apply masochism effects to stretching:

		// Stretching effects (will only stretch from penises):
		if (penetrationType == SexAreaPenetration.PENIS) {
			
			boolean lubed = !wetAreas.get(characterPenetrated).get(orifice).isEmpty();
			boolean twoPenisesInOrifice = false;

			areasCurrentlyStretching.get(characterPenetrated).clear();
			if (orifice == SexAreaOrifice.ANUS){
				if (Capacity.isPenisSizeTooBig((int)characterPenetrated.getAssStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
					sexSB.append(characterPenetrated.getStretchingDescription(characterPenetrating, penetrationType, SexAreaOrifice.ANUS));

					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementAssStretchedCapacity((((float)characterPenetrating.getPenisRawSizeValue())-characterPenetrated.getAssStretchedCapacity())*characterPenetrated.getAssElasticity().getStretchModifier());
					if(characterPenetrated.getAssStretchedCapacity()>characterPenetrating.getPenisRawSizeValue())
						characterPenetrated.setAssStretchedCapacity(characterPenetrating.getPenisRawSizeValue());

					areasCurrentlyStretching.get(characterPenetrated).add(SexAreaOrifice.ANUS);

					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenisSizeTooBig((int)characterPenetrated.getAssStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
						sexSB.append(characterPenetrated.getStretchingFinishedDescription(SexAreaOrifice.ANUS));
						areasCurrentlyStretching.get(characterPenetrated).remove(SexAreaOrifice.ANUS);
					}

					areasStretched.get(characterPenetrated).add(SexAreaOrifice.ANUS);

				} else if(Capacity.isPenisSizeTooSmall((int)characterPenetrated.getAssStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
					sexSB.append(characterPenetrated.getTooLooseDescription(SexAreaOrifice.ANUS));
					areasTooLoose.get(characterPenetrated).add(SexAreaOrifice.ANUS);
				}

			} else if (orifice == SexAreaOrifice.VAGINA){
				if (Capacity.isPenisSizeTooBig((int)characterPenetrated.getVaginaStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
					sexSB.append(characterPenetrated.getStretchingDescription(characterPenetrating, penetrationType, SexAreaOrifice.VAGINA));
					
					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementVaginaStretchedCapacity((((float)characterPenetrating.getPenisRawSizeValue())-characterPenetrated.getVaginaStretchedCapacity())*characterPenetrated.getVaginaElasticity().getStretchModifier());
					if(characterPenetrated.getVaginaStretchedCapacity()>characterPenetrating.getPenisRawSizeValue()) {
						characterPenetrated.setVaginaStretchedCapacity(characterPenetrating.getPenisRawSizeValue());
					}
					
					areasCurrentlyStretching.get(characterPenetrated).add(SexAreaOrifice.VAGINA);
					
					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenisSizeTooBig((int)characterPenetrated.getVaginaStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
						sexSB.append(characterPenetrated.getStretchingFinishedDescription(SexAreaOrifice.VAGINA));
						areasCurrentlyStretching.get(characterPenetrated).remove(SexAreaOrifice.VAGINA);
					}

					areasStretched.get(characterPenetrated).add(SexAreaOrifice.VAGINA);

				} else if(Capacity.isPenisSizeTooSmall((int)characterPenetrated.getVaginaStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
					sexSB.append(characterPenetrated.getTooLooseDescription(SexAreaOrifice.VAGINA));
					areasTooLoose.get(characterPenetrated).add(SexAreaOrifice.VAGINA);
				}

			}else if (orifice == SexAreaOrifice.NIPPLE){
				if (Capacity.isPenisSizeTooBig((int)characterPenetrated.getNippleStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
					sexSB.append(characterPenetrated.getStretchingDescription(characterPenetrating, penetrationType, SexAreaOrifice.NIPPLE));

					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementNippleStretchedCapacity((((float)characterPenetrating.getPenisRawSizeValue())-characterPenetrated.getNippleStretchedCapacity())*characterPenetrated.getNippleElasticity().getStretchModifier());
					if(characterPenetrated.getNippleStretchedCapacity()>characterPenetrating.getPenisRawSizeValue())
						characterPenetrated.setNippleStretchedCapacity(characterPenetrating.getPenisRawSizeValue());

					areasCurrentlyStretching.get(characterPenetrated).add(SexAreaOrifice.NIPPLE);

					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenisSizeTooBig((int)characterPenetrated.getNippleStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
						sexSB.append(characterPenetrated.getStretchingFinishedDescription(SexAreaOrifice.NIPPLE));
						areasCurrentlyStretching.get(characterPenetrated).remove(SexAreaOrifice.NIPPLE);
					}

					areasStretched.get(characterPenetrated).add(SexAreaOrifice.NIPPLE);

				}else if(Capacity.isPenisSizeTooSmall((int)characterPenetrated.getNippleStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
					sexSB.append(characterPenetrated.getTooLooseDescription(SexAreaOrifice.NIPPLE));
					areasTooLoose.get(characterPenetrated).add(SexAreaOrifice.NIPPLE);
				}

			} else if (orifice == SexAreaOrifice.URETHRA_PENIS){
				if (Capacity.isPenisSizeTooBig((int)characterPenetrated.getPenisStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
					sexSB.append(characterPenetrated.getStretchingDescription(characterPenetrating, penetrationType, SexAreaOrifice.URETHRA_PENIS));

					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementPenisStretchedCapacity((((float)characterPenetrating.getPenisRawSizeValue())-characterPenetrated.getPenisStretchedCapacity())*characterPenetrated.getUrethraElasticity().getStretchModifier());
					if(characterPenetrated.getPenisStretchedCapacity()>characterPenetrating.getPenisRawSizeValue())
						characterPenetrated.setPenisStretchedCapacity(characterPenetrating.getPenisRawSizeValue());

					areasCurrentlyStretching.get(characterPenetrated).add(SexAreaOrifice.URETHRA_PENIS);

					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenisSizeTooBig((int)characterPenetrated.getPenisStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
						sexSB.append(characterPenetrated.getStretchingFinishedDescription(SexAreaOrifice.URETHRA_PENIS));
						areasCurrentlyStretching.get(characterPenetrated).remove(SexAreaOrifice.URETHRA_PENIS);
					}

					areasStretched.get(characterPenetrated).add(SexAreaOrifice.URETHRA_PENIS);

				}else if(Capacity.isPenisSizeTooSmall((int)characterPenetrated.getPenisStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
					sexSB.append(characterPenetrated.getTooLooseDescription(SexAreaOrifice.URETHRA_PENIS));
					areasTooLoose.get(characterPenetrated).add(SexAreaOrifice.URETHRA_PENIS);
				}

			} else if (orifice == SexAreaOrifice.URETHRA_VAGINA){
				if (Capacity.isPenisSizeTooBig((int)characterPenetrated.getVaginaUrethraStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
					sexSB.append(characterPenetrated.getStretchingDescription(characterPenetrating, penetrationType, SexAreaOrifice.URETHRA_VAGINA));

					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementVaginaUrethraStretchedCapacity((((float)characterPenetrating.getPenisRawSizeValue())-characterPenetrated.getVaginaUrethraStretchedCapacity())*characterPenetrated.getVaginaUrethraElasticity().getStretchModifier());
					if(characterPenetrated.getVaginaUrethraStretchedCapacity()>characterPenetrating.getPenisRawSizeValue())
						characterPenetrated.setVaginaUrethraStretchedCapacity(characterPenetrating.getPenisRawSizeValue());

					areasCurrentlyStretching.get(characterPenetrated).add(SexAreaOrifice.URETHRA_VAGINA);

					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenisSizeTooBig((int)characterPenetrated.getVaginaUrethraStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
						sexSB.append(characterPenetrated.getStretchingFinishedDescription(SexAreaOrifice.URETHRA_VAGINA));
						areasCurrentlyStretching.get(characterPenetrated).remove(SexAreaOrifice.URETHRA_VAGINA);
					}

					areasStretched.get(characterPenetrated).add(SexAreaOrifice.URETHRA_VAGINA);

				}else if(Capacity.isPenisSizeTooSmall((int)characterPenetrated.getVaginaUrethraStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
					sexSB.append(characterPenetrated.getTooLooseDescription(SexAreaOrifice.URETHRA_VAGINA));
					areasTooLoose.get(characterPenetrated).add(SexAreaOrifice.URETHRA_VAGINA);
				}

			} else if (orifice == SexAreaOrifice.MOUTH){
				if (Capacity.isPenisSizeTooBig((int)characterPenetrated.getFaceStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
					sexSB.append(characterPenetrated.getStretchingDescription(characterPenetrating, penetrationType, SexAreaOrifice.MOUTH));

					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementFaceStretchedCapacity((((float)characterPenetrating.getPenisRawSizeValue())-characterPenetrated.getFaceStretchedCapacity())*characterPenetrated.getFaceElasticity().getStretchModifier());
					if(characterPenetrated.getFaceStretchedCapacity()>characterPenetrating.getPenisRawSizeValue())
						characterPenetrated.setFaceStretchedCapacity(characterPenetrating.getPenisRawSizeValue());

					areasCurrentlyStretching.get(characterPenetrated).add(SexAreaOrifice.MOUTH);

					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenisSizeTooBig((int)characterPenetrated.getFaceStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
						sexSB.append(characterPenetrated.getStretchingFinishedDescription(SexAreaOrifice.MOUTH));
						areasCurrentlyStretching.get(characterPenetrated).remove(SexAreaOrifice.MOUTH);
					}

					areasStretched.get(characterPenetrated).add(SexAreaOrifice.MOUTH);

				}else if(Capacity.isPenisSizeTooSmall((int)characterPenetrated.getFaceStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
					sexSB.append(characterPenetrated.getTooLooseDescription(SexAreaOrifice.MOUTH));
				}
			}
		}
	}

	/**
	 * Removes a piece of clothing.
	 *
	 * @return SexActionUtility.CLOTHING_REMOVAL
	 */
	public static SexActionInterface manageClothingToAccessCoverableArea(GameCharacter characterManagingClothing, GameCharacter targetForManagement, CoverableArea coverableArea) {
		
		SimpleEntry<AbstractClothing, DisplacementType> clothingRemoval = targetForManagement.getNextClothingToRemoveForCoverableAreaAccess(coverableArea);
		if (clothingRemoval.getKey() == null) {
			unequipClothingText = UtilText.parse(characterManagingClothing, targetForManagement, "[npc.Name] can't find a piece of [npc2.namePos] clothing to remove! (Please tell Innoxia. :3)");
			System.err.println("partnerManageClothingToAccessCoverableArea method can't get clothing! 1");
			return SexActionUtility.CLOTHING_REMOVAL;
		}
		
		clothingBeingRemoved = clothingRemoval.getKey();

		if (clothingRemoval.getValue() == DisplacementType.REMOVE_OR_EQUIP) {
			targetForManagement.unequipClothingOntoFloor(clothingBeingRemoved, false, characterManagingClothing);
			unequipClothingText = targetForManagement.getUnequipDescription();

		} else {
			targetForManagement.isAbleToBeDisplaced(clothingBeingRemoved, clothingRemoval.getValue(), true, false, characterManagingClothing);
			unequipClothingText = targetForManagement.getDisplaceDescription();
		}

		return SexActionUtility.CLOTHING_REMOVAL;
	}

	public static boolean isInForeplay() {
		return Sex.getActivePartner().getArousal()<ArousalLevel.ONE_TURNED_ON.getMaximumValue() && Sex.getNumberOfOrgasms(Sex.getActivePartner())==0 && Sex.getSexManager().isPartnerUsingForeplayActions();
	}
	
	// Getters & Setters:

	public static boolean isConsensual() {
		return consensual;
	}

	public static void setConsensual(boolean consensual) {
		Sex.consensual = consensual;
	}

	public static boolean isSubHasEqualControl() {
		return subHasEqualControl;
	}

	public static void setCanResist(boolean subHasEqualControl) {
		Sex.subHasEqualControl = subHasEqualControl;
	}
	
	public static boolean isPositionChangingAllowed(GameCharacter characterWantingToChangePosition) {
		if(characterWantingToChangePosition.isPlayer() && SexFlags.positioningBlockedPlayer) {
			return false;
		}
		if(!characterWantingToChangePosition.isPlayer() && SexFlags.positioningBlockedPartner) {
			return false;
		}
		return Sex.getInitialSexManager().isPositionChangingAllowed(characterWantingToChangePosition);
	}
	
	
	public static List<SexAreaInterface> getRequestsBlocked(GameCharacter character) {
		return requestsBlocked.get(character);
	}

	public static void addRequestsBlocked(GameCharacter character, SexAreaInterface area) {
		if(!requestsBlocked.get(character).contains(area)) {
			requestsBlocked.get(character).add(area);
		}
	}

	/**
	 * @param targeter The character whose target is to be found.
	 * @return The character that the 'targeter' is currently focusing on.
	 */
	public static GameCharacter getTargetedPartner(GameCharacter targeter) {
		if(targeter!=null
				&& targeter.isPlayer()
				&& Sex.allParticipants!=null
				&& Sex.allParticipants.size()>1) {
			return activePartner;
		} else {
			return Main.game.getPlayer();
		}
	}
	
	public static NPC getActivePartner() {
		return activePartner;
	}
	
	public static void setActivePartner(NPC character) {
		if(!Sex.getDominantParticipants().keySet().contains(character) && !Sex.getSubmissiveParticipants().keySet().contains(character)) {
			throw new IllegalArgumentException("This character ("+character.getName()+") is not in the sex scene!");
		}
		activePartner = character;
	}
	
	public static GameCharacter getCharacterPerformingAction() {
		return characterPerformingAction;
	}

	public static void setCharacterPerformingAction(GameCharacter characterPerformingAction) {
		Sex.characterPerformingAction = characterPerformingAction;
	}
	
	public static GameCharacter getCharacterTargetedForSexAction(SexActionInterface action) {
		if(action.getParticipantType()==SexParticipantType.SELF) {
			return getCharacterPerformingAction();
		}
		return Sex.getTargetedPartner(Sex.getCharacterPerformingAction());
	}

	public static int getTotalParticipantCount() {
		return dominants.size()+submissives.size();
	}
	
	public static boolean isMasturbation() {
		return Sex.allParticipants.size()==1;
	}
	
	public static List<GameCharacter> getAllParticipants() {
		return Sex.allParticipants;
	}
	
	public static Map<GameCharacter, SexPositionSlot> getDominantParticipants() {
		return dominants;
	}
	
	public static Map<GameCharacter, SexPositionSlot> getSubmissiveParticipants() {
		return submissives;
	}
	

	private static PlayerCharacter player() {
		return Main.game.getPlayer();
	}

	public static String getUnequipClothingText() {
		return unequipClothingText;
	}

	public static void setUnequipClothingText(String unequipClothingText) {
		Sex.unequipClothingText = unequipClothingText;
	}
	
	public static String getDyeClothingText() {
		return dyeClothingText;
	}
	
	public static void setDyeClothingText(String dyeClothingText) {
		Sex.dyeClothingText = dyeClothingText;
	}

	public static String getUsingItemText() {
		return usingItemText;
	}

	public static void setUsingItemText(String usingItemText) {
		Sex.usingItemText = usingItemText;
	}

	public static AbstractClothing getClothingBeingRemoved() {
		return clothingBeingRemoved;
	}
	
	public static boolean isAnyOngoingActionHappening() {
		for(GameCharacter character : Sex.getAllParticipants()) {
			if(isCharacterEngagedInOngoingAction(character)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isCharacterEngagedInOngoingAction(GameCharacter character) {
		if(!Sex.getAllParticipants().contains(character)) {
			System.err.println("isCharacterPenetrated("+character.getId()+"): This character is not in Sex!"); 
			return false;
		}

		for(Entry<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> entry : ongoingActionsMap.get(character).entrySet()) {
			for(GameCharacter penetrator : Sex.getAllParticipants()) {
				if(entry.getValue().containsKey(penetrator)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isAnyNonSelfOngoingActionHappening() {
		for(GameCharacter penetrator : Sex.getAllParticipants()) {
			for(GameCharacter penetrated : Sex.getAllParticipants()) {
				if(!penetrator.equals(penetrated)) {
					for(Entry<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> entry : ongoingActionsMap.get(penetrator).entrySet()) {
						if(entry.getValue().containsKey(penetrated)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean isCharacterSelfOngoingActionHappening(GameCharacter character) {
		for(Entry<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> entry : ongoingActionsMap.get(character).entrySet()) {
			if(entry.getValue().containsKey(character)) {
				return true;
			}
		}
		return false;
	}


	// Free area convenience methods:

	public static boolean isOrificeFree(GameCharacter character, SexAreaOrifice orifice) {
		return ongoingActionsMap.get(character).get(orifice).isEmpty();
	}
	
	public static boolean isOrificeNonSelfOngoingAction(GameCharacter characterOrifice, SexAreaOrifice orifice) {
		for(GameCharacter penetrator : Sex.allParticipants) {
			if(!penetrator.equals(characterOrifice)) {
				for(Entry<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> entry : ongoingActionsMap.get(penetrator).entrySet()) {
					if(entry.getValue().containsKey(characterOrifice)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	public static boolean isPenetrationTypeFree(GameCharacter penetrator, SexAreaPenetration penetrationType) {
		int penetrationTypesAvailable = 1;
		
		switch(penetrationType) {
			case FINGER:
				penetrationTypesAvailable = penetrator.getArmRows()*2;
				break;
			case PENIS:
				if(!penetrator.hasPenis()) {
					return true;
				}
				break;
			case TAIL:
				if(penetrator.getTailType()==TailType.NONE|| penetrator.getTailCount()==0) {
					return true;
				}
				penetrationTypesAvailable = penetrator.getTailCount();
				break;
			case TENTACLE:
				break;
			case TONGUE:
				break;
			case CLIT:
				if(!penetrator.hasVagina()) {
					return true;
				}
				break;
			case TOES:
				break;
		}
		
		int totalAreasUsed = 0;
		for(GameCharacter target : Sex.allParticipants) {
			if(ongoingActionsMap.get(penetrator).get(penetrationType).containsKey(target)) {
				totalAreasUsed += ongoingActionsMap.get(penetrator).get(penetrationType).get(target).size();
			}
		}
		return totalAreasUsed<penetrationTypesAvailable;
	}
	
	public static boolean isPenetrationNonSelfOngoingAction(GameCharacter penetrator, SexAreaPenetration penetrationType) {
		for(GameCharacter target : Sex.allParticipants) {
			if(!penetrator.equals(target)) {
				for(Entry<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> entry : ongoingActionsMap.get(penetrator).entrySet()) {
					if(entry.getValue().containsKey(penetrator)) {
						return true;
					}
				}
			}
		}
		return false;
	}


	// Orgasm convenience methods:

	public static boolean isPlayerReadyToOrgasm() {
		return Main.game.getPlayer().getArousal()>=ArousalLevel.FIVE_ORGASM_IMMINENT.getMinimumValue();
	}
	public static boolean isPartnerReadyToOrgasm() {
		return activePartner.getArousal()>=ArousalLevel.FIVE_ORGASM_IMMINENT.getMinimumValue();
	}
	public static boolean isPlayerAbleToMutualOrgasm() {
		return ArousalLevel.getArousalLevelFromValue(Main.game.getPlayer().getArousal()).isMutualOrgasm();
	}
	public static boolean isPartnerAbleToMutualOrgasm() {
		return ArousalLevel.getArousalLevelFromValue(Main.game.getPlayer().getArousal()).isMutualOrgasm();
	}
	
	public static Map<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> getOngoingActionsMap(GameCharacter characterPerformingPenetration) {
		return ongoingActionsMap.get(characterPerformingPenetration);
	}
	
	/**
	 * @return A map of characters, with a set of SexAreas holding SexAreaInterfaces which are in contact with the passed-in character's sexArea.
	 */
	public static Map<GameCharacter, Set<SexAreaInterface>> getContactingSexAreas(GameCharacter character, SexAreaInterface sexArea) {
		return ongoingActionsMap.get(character).get(sexArea);
	}
	
	/**
	 * Get all of the SexAreaInterfaces that this character's area is in contact with.
	 */
	public static List<SexAreaInterface> getAllContactingSexAreas(GameCharacter character, SexAreaInterface sexArea) {
		List<SexAreaInterface> returnList = new ArrayList<>();
		for(Set<SexAreaInterface> set : ongoingActionsMap.get(character).get(sexArea).values()) {
			returnList.addAll(set);
		}
		return returnList;
	}
	
	public static Set<SexAreaInterface> getContactingSexAreas(GameCharacter character, SexAreaInterface sexArea, GameCharacter characterInteractingWith) {
		if(ongoingActionsMap.get(character).get(sexArea).containsKey(characterInteractingWith)) {
			return ongoingActionsMap.get(character).get(sexArea).get(characterInteractingWith);
		} else {
			return new HashSet<>();
		}
	}
	
	public static SexAreaInterface getFirstContactingSexArea(GameCharacter character, SexAreaInterface sexArea) {
		for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(character, sexArea).entrySet()) {
			for(SexAreaInterface sArea : entry.getValue()) {
				return sArea;
			}
		}
		return null;
	}
	
	public static SexAreaPenetration getFirstContactingSexAreaPenetration(GameCharacter character, SexAreaInterface sexArea) {
		for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(character, sexArea).entrySet()) {
			for(SexAreaInterface sArea : entry.getValue()) {
				if(sArea.isPenetration()) {
					return (SexAreaPenetration) sArea;
				}
			}
		}
		return null;
	}
	
	public static SexAreaOrifice getFirstContactingSexAreaOrifice(GameCharacter character, SexAreaInterface sexArea) {
		for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(character, sexArea).entrySet()) {
			for(SexAreaInterface sArea : entry.getValue()) {
				if(sArea.isOrifice()) {
					return (SexAreaOrifice) sArea;
				}
			}
		}
		return null;
	}
	
	/**
	 * @return The characters who are currently using this character's sexArea.
	 */
	public static List<GameCharacter> getCharacterContactingSexArea(GameCharacter character, SexAreaInterface sexArea) {
		return new ArrayList<>(getContactingSexAreas(character, sexArea).keySet());
	}
	
	/**
	 * @return The characters who are currently using their sexAreaUsing to contact this character's sexAreaBeingUsed.
	 */
	public static Set<GameCharacter> getCharacterContactingSexArea(GameCharacter character, SexAreaInterface sexAreaBeingUsed, SexAreaInterface sexAreaUsing) {
		Set<GameCharacter> characters = new HashSet<>();
		
		for(Entry<GameCharacter, Set<SexAreaInterface>> entry : ongoingActionsMap.get(character).get(sexAreaBeingUsed).entrySet()) {
			if(entry.getValue().contains(sexAreaUsing)) {
				characters.add(entry.getKey());
			}
		}
		
		return characters;
	}
	
	/**
	 * Returns a list, not a single instance of GameCharacter, as the 'characterPenetrating' could be penetrating multiple characters with tentacles, tails, hands, etc.
	 */
	public static List<SexAreaOrifice> getOrificesBeingPenetratedBy(GameCharacter characterPenetrating, SexAreaPenetration penetration, GameCharacter characterPenetrated) {
		if(ongoingActionsMap.get(characterPenetrating).get(penetration).containsKey(characterPenetrated)) {
			List<SexAreaOrifice> returnList = new ArrayList<>();
			for(SexAreaInterface sArea : ongoingActionsMap.get(characterPenetrating).get(penetration).get(characterPenetrated)) {
				if(sArea.isOrifice()) {
					returnList.add((SexAreaOrifice)sArea);
				}
			}
			return returnList;
			
		} else {
			return new ArrayList<>();
		}
	}
	
	/**
	 * Returns a set, not a single instance of GameCharacter, as the 'characterPenetrating' could be penetrating multiple characters with tentacles, tails, hands, etc.
	 */
	public static List<GameCharacter> getCharactersHavingOngoingActionWith(GameCharacter characterPenetrating, SexAreaPenetration penetration) {
		return new ArrayList<>(ongoingActionsMap.get(characterPenetrating).get(penetration).keySet());
	}
	
	public static Map<SexAreaInterface, Set<LubricationType>> getWetAreas(GameCharacter character) {
		return wetAreas.get(character);
	}

	public AbstractClothing getSelectedClothing() {
		return selectedClothing;
	}

	public static void setSelectedClothing(AbstractClothing selectedClothing) {
		Sex.selectedClothing = selectedClothing;
	}

	public static SexManagerInterface getSexManager() {
		return sexManager;
	}

	public static SexManagerInterface getInitialSexManager() {
		return initialSexManager;
	}

	public static void setSexManager(SexManagerInterface sexManager) {
		Sex.allParticipants = new ArrayList<>(sexManager.getDominants().keySet());
		Sex.allParticipants.addAll(sexManager.getSubmissives().keySet());
		
		Sex.resetAllOngoingActions();
		
		actionsAvailable.clear();
		orgasmActionsAvailable.clear();
		for(GameCharacter character : Sex.allParticipants) {
			actionsAvailable.put(character, new HashMap<>());
			orgasmActionsAvailable.put(character, new HashMap<>());
			
			for(GameCharacter target : Sex.allParticipants) {
				if(!character.equals(target) || Sex.isMasturbation()) {
					actionsAvailable.get(character).put(target, new HashSet<>());
					orgasmActionsAvailable.get(character).put(target, new HashSet<>());
				}
			}
		}
		
		
		// Add dominants to map, with player as the first entry:
		List<GameCharacter> tempCharacterList = new ArrayList<>(sexManager.getDominants().keySet());
		Collections.sort(tempCharacterList, (p1, p2) -> p1.isPlayer()?-1:0);
		Sex.dominants = new LinkedHashMap<>();
		for(GameCharacter character : tempCharacterList) {
			Sex.dominants.put(character, sexManager.getDominants().get(character));
		}
		if(!Sex.isDom(Main.game.getPlayer())) {
			if(!tempCharacterList.isEmpty()) {
				activePartner = (NPC) tempCharacterList.get(0);
			} else {
				activePartner = null;
			}
		}

		// Add submissives to map, with player as the first entry:
		tempCharacterList = new ArrayList<>(sexManager.getSubmissives().keySet());
		Collections.sort(tempCharacterList, (p1, p2) -> p1.isPlayer()?-1:0);
		Sex.submissives = new LinkedHashMap<>();
		for(GameCharacter character : tempCharacterList) {
			Sex.submissives.put(character, sexManager.getSubmissives().get(character));
		}
		if(Sex.isDom(Main.game.getPlayer())) {
			if(!tempCharacterList.isEmpty()) {
				activePartner = (NPC) tempCharacterList.get(0);
			} else {
				activePartner = null;
			}
		}
		
		Sex.sexManager = sexManager;
		
		updateAvailableActions();
		
		for(GameCharacter character : Sex.getAllParticipants()) {
			if(actionsAvailable.get(Main.game.getPlayer()).containsKey(character)) {
				repeatActionsPlayer.removeIf((action) -> !actionsAvailable.get(Main.game.getPlayer()).get(character).contains(action));
			}
		}
		
		sexSB.append(
				"<p style='text-align:center;'><b>New position:</b> <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>"+Sex.sexManager.getPosition().getName()+"</b><br/>"
				+"<i><b>"+Sex.sexManager.getPosition().getDescription()+"</b></i></p>");
	}
	
	public static void resetAllOngoingActions() {
		ongoingActionsMap = new HashMap<>();
		for(GameCharacter character : Sex.getAllParticipants()) {
			ongoingActionsMap.put(character, new HashMap<>());
			for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
				ongoingActionsMap.get(character).put(orifice, new HashMap<>());
			}
			for(SexAreaPenetration penetration : SexAreaPenetration.values()) {
				ongoingActionsMap.get(character).put(penetration, new HashMap<>());
			}
		}
	}
	
	private static void updateAvailableActions() {

		for(GameCharacter character : Sex.allParticipants) {
			for(GameCharacter target : Sex.allParticipants) {
				if(!character.equals(target) || Sex.isMasturbation()) {
					actionsAvailable.get(character).get(target).clear();
					orgasmActionsAvailable.get(character).get(target).clear();
				}
			}
		}
					
		for(GameCharacter character : Sex.allParticipants) {
			for(GameCharacter target : Sex.allParticipants) {
				if(!character.equals(target) || Sex.isMasturbation()) {
					SexActionInteractions interactions = Sex.sexManager.getPosition().getSlotTargets().get(Sex.getSexPositionSlot(character)).get(Sex.getSexPositionSlot(target));
					
					if(Sex.sexManager.getPosition().isAddStandardActions()) {
						addActionsFromContainingClasses(character, target, interactions, SexActionPresets.allCommonActions);
					}
					if(Sex.sexManager.getPosition().isAddStandardPositioning()) {
						addActionsFromContainingClasses(character, target, interactions, SexActionPresets.positioningActions);
					}
					addActionsFromContainingClasses(character, target, interactions, Sex.sexManager.getPosition().getSpecialClasses());
					
					if(character instanceof NPC) {
						for(Class<?> sexClass : ((NPC)character).getUniqueSexClasses()) {
							Sex.addSexActionClass(character, target, interactions, sexClass);
						}
					}
				}
			}
		}
	}
	
	private static void addActionsFromContainingClasses(GameCharacter character, GameCharacter target, SexActionInteractions interactions, List<Class<?>> sexActionContainingClasses) {
		try {
			if (!sexActionContainingClasses.isEmpty()) {
				for(Class<?> container : sexActionContainingClasses) {
					addSexActionClass(character, target, interactions, container);
				}
			}
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
	private static void addSexActionClass(GameCharacter character, GameCharacter target, SexActionInteractions interactions, Class<?> classToAddSexActionsFrom) {
		try {
			if(classToAddSexActionsFrom!=null) {
				Field[] fields = classToAddSexActionsFrom.getFields();
				
				for(Field f : fields){
					if (SexAction.class.isAssignableFrom(f.getType())) {
						SexAction action = ((SexAction) f.get(null));
						
						boolean addedForCharacter = (SexActionPresets.miscActions.contains(classToAddSexActionsFrom)
								|| SexActionPresets.selfActions.contains(classToAddSexActionsFrom)
								|| action.getParticipantType()==SexParticipantType.SELF);
						
						boolean addedForTarget = (SexActionPresets.miscActions.contains(classToAddSexActionsFrom)
								|| SexActionPresets.selfActions.contains(classToAddSexActionsFrom)
								|| action.getParticipantType()==SexParticipantType.SELF);
						
						if(!addedForCharacter) {
							if(action.getSexAreaInteractions().isEmpty()) {
								addedForCharacter = true;
							} else {
								outer:
								for(SexAreaInterface area : action.getSexAreaInteractions().keySet()) {
									for(SexAreaInterface area2 : interactions.getInteractions().keySet()) {
										if(area == area2) {
											for(SexAreaInterface areaInner : action.getSexAreaInteractions().values()) {
												for(SexAreaInterface areaInner2 : interactions.getInteractions().get(area2)) {
													if(areaInner == areaInner2) {
														addedForCharacter = true;
														break outer;
													}
												}
											}
										}
									}
								}
							}
						}

						if(!addedForTarget) {
							if(action.getSexAreaInteractions().isEmpty()) {
								addedForTarget = true;
							} else {
								outer:
								for(SexAreaInterface area : action.getSexAreaInteractions().keySet()) {
									for(Entry<SexAreaInterface, List<SexAreaInterface>> entry : interactions.getInteractions().entrySet()) {
										for(SexAreaInterface areaInner2 : entry.getValue()) {
											if(area == areaInner2) {
												if(action.getSexAreaInteractions().get(area) == entry.getKey()) {
													addedForTarget = true;
													break outer;
												}
											}
										}
									}
								}
							}
						}
						
						if(!((action.getLimitation()==SexActionLimitation.PLAYER_ONLY && character.isPlayer())
								|| (action.getLimitation()==SexActionLimitation.NPC_ONLY && !character.isPlayer())
								|| action.getLimitation()==null)) {
							addedForCharacter = false;
						}
						
						if(!((action.getLimitation()==SexActionLimitation.PLAYER_ONLY && target.isPlayer())
								|| (action.getLimitation()==SexActionLimitation.NPC_ONLY && !target.isPlayer())
								|| action.getLimitation()==null)) {
							addedForTarget = false;
						}
						
						if(!Sex.isMasturbation() || action.getParticipantType()==SexParticipantType.SELF) {
							if (action.getActionType().isOrgasmOption()) {
								if(addedForCharacter) {
									orgasmActionsAvailable.get(character).get(target).add(action);
								}
								if(addedForTarget) {
									orgasmActionsAvailable.get(target).get(character).add(action);
								}
							} else {
								if(addedForCharacter) {
									actionsAvailable.get(character).get(target).add(action);
								}
								if(addedForTarget) {
									actionsAvailable.get(target).get(character).add(action);
								}
							}
						}
					}
				}
			}
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Set<SexActionInterface> getActionsAvailablePlayer() {
		return actionsAvailable.get(Main.game.getPlayer()).get(Sex.getTargetedPartner(Main.game.getPlayer()));
	}

	public static Set<SexActionInterface> getActionsAvailablePartner() {
		return actionsAvailable.get(activePartner).get(Sex.getTargetedPartner(activePartner));
	}

	public static Set<SexActionInterface> getOrgasmActionsPlayer() {
		return orgasmActionsAvailable.get(Main.game.getPlayer()).get(Sex.getTargetedPartner(Main.game.getPlayer()));
	}

	public static Set<SexActionInterface> getOrgasmActionsPartner() {
		return orgasmActionsAvailable.get(activePartner).get(Sex.getTargetedPartner(activePartner));
	}

//	public static Set<SexActionInterface> getMutualOrgasmActions() {
//		return mutualOrgasmActions;
//	}
	
	public static List<SexActionInterface> getAvailableSexActionsPlayer() {
		return availableSexActionsPlayer;
	}

	public static SexActionInterface getLastUsedPlayerAction() {
		return lastUsedPlayerAction;
	}

	public static SexActionInterface getLastUsedPartnerAction() {
		return lastUsedPartnerAction;
	}
	
	public static void setSexPace(GameCharacter character, SexPace sexPace) {
		forceSexPaceMap.put(character, sexPace);
	}
	
	public static SexPace getSexPace(GameCharacter character) {
		if(character==null) {
			return null;
		}
		
		if(forceSexPaceMap.containsKey(character)) {
			return forceSexPaceMap.get(character);
		}
		
		return LustLevel.getLustLevelFromValue(character.getLust()).getSexPace(Sex.isConsensual(), character);
	}
	
	public static GameCharacter getCharacterInPosition(SexPositionSlot position) {
		for(Entry<GameCharacter, SexPositionSlot> entry : Sex.dominants.entrySet()) {
			if(entry.getValue()==position) {
				return entry.getKey();
			}
		}
		for(Entry<GameCharacter, SexPositionSlot> entry : Sex.submissives.entrySet()) {
			if(entry.getValue()==position) {
				return entry.getKey();
			}
		}
		return null;
	}
	
	public static SexPositionSlot getSexPositionSlot(GameCharacter character) {
		if(Sex.dominants.keySet().contains(character)) {
			return Sex.dominants.get(character);
			
		} else if(Sex.submissives.keySet().contains(character)) {
			return Sex.submissives.get(character);
		}
		
		throw new IllegalArgumentException("The passed character in Sex.getSexPositionSlot(character) is not detected as a participant in this Sex scene!");
	}
	
	public static void swapSexPositionSlots(GameCharacter character1, GameCharacter character2) {
		SexPositionSlot characterSlot1 = Sex.getSexPositionSlot(character1);
		SexPositionSlot characterSlot2 = Sex.getSexPositionSlot(character2);
		
		if(Sex.dominants.keySet().contains(character1)) {
			Sex.dominants.put(character1, characterSlot2);
		}
		if(Sex.submissives.keySet().contains(character1)) {
			Sex.submissives.put(character1, characterSlot2);
		}
		
		if(Sex.dominants.keySet().contains(character2)) {
			Sex.dominants.put(character2, characterSlot1);
		}
		if(Sex.submissives.keySet().contains(character2)) {
			Sex.submissives.put(character2, characterSlot1);
		}
		
		Sex.resetAllOngoingActions();
		
		updateAvailableActions();
	}
	
	public static void setSexPositionSlot(GameCharacter character, SexPositionSlot slot) {
		// Check to see if character is in this sex scene:
		if(!Sex.dominants.keySet().contains(character) && !Sex.submissives.keySet().contains(character)) {
			throw new IllegalArgumentException("This character is not in this sex scene!");
		}
		
		// Check to see if this slot is available:
		if(getPosition().getSlotTargets().keySet().contains(slot)) {
			for(Entry<GameCharacter, SexPositionSlot> e : dominants.entrySet()) {
				if(!e.getKey().equals(character)) {
					if(getPosition().getSlotTargets().keySet().contains(e.getValue())) {
						throw new IllegalArgumentException("A dominant participant ("+character.getName()+") is already occupying this slot ("+slot+")!");
					}
				}
			}
			for(Entry<GameCharacter, SexPositionSlot> e : submissives.entrySet()) {
				if(!e.getKey().equals(character)) {
					if(getPosition().getSlotTargets().keySet().contains(e.getValue())) {
						throw new IllegalArgumentException("A submissive participant ("+character.getName()+") is already occupying this slot ("+slot+")!");
					}
				}
			}
		} else {
			throw new IllegalArgumentException("This slot ("+slot+") is not available in this position ("+getPosition()+")!");
		}
		
		if(Sex.dominants.keySet().contains(character)) {
			Sex.dominants.put(character, slot);
		}
		if(Sex.submissives.keySet().contains(character)) {
			Sex.submissives.put(character, slot);
		}
		
		updateAvailableActions();
	}
	
	public static boolean isDom(GameCharacter character) {
		return Sex.dominants.keySet().contains(character);
	}
	
	public static boolean isPublicSex() {
		return publicSex;
	}
	
	public static boolean isSexFinished() {
		return sexFinished;
	}

	public static void forceSexEnd() {
		sexFinished = true;
	}

	public static boolean isSexStarted() {
		return sexStarted;
	}

	public static void setSexStarted(boolean sexStarted) {
		Sex.sexStarted = sexStarted;
	}

	public static Set<SexAreaOrifice> getAreasCurrentlyStretching(GameCharacter character) {
		return areasCurrentlyStretching.get(character);
	}

	public static Set<SexAreaOrifice> getAreasTooLoose(GameCharacter character) {
		return areasTooLoose.get(character);
	}

	public static int getNumberOfOrgasms(GameCharacter character) {
		orgasmCountMap.putIfAbsent(character, 0);
		return orgasmCountMap.get(character);
	}
	
	public static void setNumberOfOrgasms(GameCharacter character, int count) {
		orgasmCountMap.put(character, count);
	}
	
	public static void incrementNumberOfOrgasms(GameCharacter character, int increment) {
		character.incrementDaysOrgasmCount(increment);
		character.incrementTotalOrgasmCount(increment);
		orgasmCountMap.putIfAbsent(character, 0);
		orgasmCountMap.put(character, orgasmCountMap.get(character)+increment);
	}
	
	public static int getTimesCummedInside(GameCharacter character, GameCharacter target) {
		cummedInsideMap.putIfAbsent(character, new HashMap<>());
		cummedInsideMap.get(character).putIfAbsent(target, 0);
		return cummedInsideMap.get(character).get(target);
	}
	
	public static void setTimesCummedInside(GameCharacter character, GameCharacter target, int count) {
		cummedInsideMap.putIfAbsent(character, new HashMap<>());
		cummedInsideMap.get(character).put(target, count);
	}
	
	public static void incrementTimesCummedInside(GameCharacter character, GameCharacter target, int increment) {
		cummedInsideMap.putIfAbsent(character, new HashMap<>());
		cummedInsideMap.get(character).putIfAbsent(target, 0);
		cummedInsideMap.get(character).put(target, cummedInsideMap.get(character).get(target)+increment);
	}
	
	public static SexPositionType getPosition() {
		return sexManager.getPosition();
	}

	public static boolean isPartnerAllowedToUseSelfActions() {
		return partnerAllowedToUseSelfActions;
	}

	public static void setPartnerAllowedToUseSelfActions(boolean partnerAllowedToUseSelfActions) {
		Sex.partnerAllowedToUseSelfActions = partnerAllowedToUseSelfActions;
	}

	public static boolean isCanRemoveSelfClothing(GameCharacter character) {
		return charactersAbleToRemoveSelfClothing.contains(character);
	}

	public static void setCanRemoveSelfClothing(GameCharacter character, boolean canRemoveSelfClothing) {
		if(canRemoveSelfClothing) {
			charactersAbleToRemoveSelfClothing.add(character);
		} else {
			charactersAbleToRemoveSelfClothing.remove(character);
		}
	}
	
	public static boolean isCanRemoveOthersClothing(GameCharacter character) {
		return charactersAbleToRemoveOthersClothing.contains(character);
	}

	public static void setCanRemoveOthersClothing(GameCharacter character, boolean canRemoveOthersClothing) {
		if(canRemoveOthersClothing) {
			charactersAbleToRemoveOthersClothing.add(character);
		} else {
			charactersAbleToRemoveOthersClothing.remove(character);
		}
	}

	public static String getSexDescription() {
		return sexDescription;
	}

	public static StringBuilder getSexSB() {
		return sexSB;
	}

	public static List<SexActionInterface> getSelfActionsPlayer() {
		return selfActionsPlayer;
	}

	public static List<SexActionInterface> getSexActionsPlayer() {
		return sexActionsPlayer;
	}

	public static List<SexActionInterface> getPositionActionsPlayer() {
		return positionActionsPlayer;
	}

	public static List<SexActionInterface> getRepeatActionsPlayer() {
		return repeatActionsPlayer;
	}

	public static DialogueNodeOld getPostSexDialogue() {
		return postSexDialogue;
	}

	public static SexActionCategory getResponseCategory() {
		return responseCategory;
	}

	public static DialogueNodeOld getSexDialogue() {
		return SEX_DIALOGUE;
	}
	
}
