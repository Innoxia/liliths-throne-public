package com.lilithsthrone.game.sex;

import java.util.AbstractMap.SimpleEntry;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
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
 * @version 0.2.2
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

	private static Map<GameCharacter, SexPositionSlot> dominants;
	private static Map<GameCharacter, SexPositionSlot> submissives;
	private static List<GameCharacter> allParticipants;
	private static NPC activePartner;
	private static SexManagerInterface sexManager;
	private static String sexDescription, unequipClothingText, dyeClothingText, usingItemText;
	private static AbstractClothing clothingBeingRemoved;
	private static StringBuilder sexSB = new StringBuilder();
	private static List<SexActionInterface> availableSexActionsPlayer, miscActionsPlayer, selfActionsPlayer, sexActionsPlayer, positionActionsPlayer;
	private static List<SexActionInterface> repeatActionsPlayer;
	private static List<SexActionInterface> availableSexActionsPartner;
	
	private static Map<GameCharacter, SexPace> forceSexPaceMap;
	
	// Actions that are currently available from all SexPositionSlots. First key is character whose actions they are, second key is target.
	private static Map<GameCharacter, Map<GameCharacter, Set<SexActionInterface>>> actionsAvailable;
	private static Map<GameCharacter, Map<GameCharacter, Set<SexActionInterface>>> orgasmActionsAvailable;
	
	private static DialogueNodeOld postSexDialogue;

	private static SexActionInterface lastUsedPlayerAction, lastUsedPartnerAction;
	
	// Tracking statuses:
	
	private static Map<GameCharacter, Map<PenetrationType, Set<LubricationType>>> wetPenetrationTypes;
	private static Map<GameCharacter, Map<OrificeType, Set<LubricationType>>> wetOrificeTypes;
	private static Map<GameCharacter, Set<OrificeType>> areasCurrentlyStretching;
	private static Map<GameCharacter, Set<OrificeType>> areasStretched;
	private static Map<GameCharacter, Set<OrificeType>> areasTooLoose;
//	private static Map<GameCharacter, Set<OrificeType>> areasCummedIn;
	
	private static Map<GameCharacter, List<CoverableArea>> areasExposed;

	private static Map<GameCharacter, Map<GameCharacter, Map<PenetrationType, Set<OrificeType>>>> ongoingPenetrationMap;
	
	private static Map<GameCharacter, Integer> orgasmCountMap;
	
	private static Set<OrificeType> penetrationRequestsPlayer;
	
	// Clothes:

	private static Map<GameCharacter, Map<AbstractClothing, List<DisplacementType>>> clothingPreSexMap;
	
	private static Set<GameCharacter> charactersAbleToRemoveSelfClothing, charactersAbleToRemoveOthersClothing;
	
	private static boolean sexFinished, partnerAllowedToUseSelfActions;
	
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
		
		setSexManager(sexManager);
		
		Sex.postSexDialogue = postSexDialogue;
		
		sexStarted = false;

		if(isConsensual()) {
			activePartner.setSexConsensualCount(activePartner.getSexConsensualCount()+1);
		}
		if(isDom(Main.game.getPlayer())) {
			activePartner.setSexAsSubCount(activePartner.getSexAsSubCount()+1);
		} else {
			activePartner.setSexAsDomCount(activePartner.getSexAsDomCount()+1);
		}
		
		ongoingPenetrationMap = new HashMap<>();
		for(GameCharacter characterPenetrating : Sex.getAllParticipants()) {
			ongoingPenetrationMap.put(characterPenetrating, new HashMap<>());
			for(GameCharacter characterPenetrated : Sex.getAllParticipants()) {
				ongoingPenetrationMap.get(characterPenetrating).put(characterPenetrated, new HashMap<>());
				characterPenetrating.addSexPartner(characterPenetrated);
			}
		}
		
		lastUsedPlayerAction = SexActionUtility.PLAYER_NONE;
		lastUsedPartnerAction = SexActionUtility.PARTNER_NONE;

		sexFinished = false;
		partnerAllowedToUseSelfActions = true;
		orgasmCountMap = new HashMap<>();
		
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

		availableSexActionsPlayer = new ArrayList<>();
		miscActionsPlayer = new ArrayList<>();
		selfActionsPlayer = new ArrayList<>();
		sexActionsPlayer = new ArrayList<>();
		positionActionsPlayer = new ArrayList<>();
		repeatActionsPlayer = new ArrayList<>();
		availableSexActionsPartner = new ArrayList<>();

		// Populate exposed areas:
		areasExposed = new HashMap<>();
		for(GameCharacter character : Sex.getAllParticipants()) {
			areasExposed.put(character, new ArrayList<>());
			
			for (CoverableArea area : CoverableArea.values()) {
				if (character.isAbleToAccessCoverableArea(area, false)) {
					areasExposed.get(character).add(area);
				}
			}
		}

		

		penetrationRequestsPlayer = new HashSet<>();

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
		
//		areasCummedIn = new HashMap<>();
//		for(GameCharacter character : Sex.getAllParticipants()) {
//			areasCummedIn.put(character, new HashSet<>());
//		}
		
		forceSexPaceMap = new HashMap<>();
		
		for(GameCharacter character : Sex.getAllParticipants()) {
			if(character instanceof NPC) {
				((NPC)character).generateSexChoices(Sex.getTargetedPartner(character));
			}
			
			// Default starting lust and arousal:
			character.setLust(50);
			character.setArousal(0);
			if(Sex.isDom(character)) {
				if(character.hasFetish(Fetish.FETISH_DOMINANT)) {
					character.setLust(85);
					character.setArousal(10);
				} else if(character.hasFetish(Fetish.FETISH_SUBMISSIVE)) {
					character.setLust(10);
				}
			} else {
				if(character.hasFetish(Fetish.FETISH_SUBMISSIVE)) {
					character.setLust(85);
					character.setArousal(10);
				}
			}
			
			
			if(Main.getProperties().hasValue(PropertyValue.nonConContent)) {
				if(!character.isPlayer()) {
					if(!((NPC) character).isAttractedTo(Main.game.getPlayer())) {
						character.setLust(0);
					}
				}
			}
			
			if(character.isPlayer()) {
				forceSexPaceMap.put(character, Sex.isDom(character)?SexPace.DOM_NORMAL:SexPace.SUB_NORMAL);
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
		wetPenetrationTypes = new HashMap<>();
		wetOrificeTypes = new HashMap<>();
		
		for(GameCharacter character : Sex.getAllParticipants()) {
			wetPenetrationTypes.put(character, new HashMap<PenetrationType, Set<LubricationType>>());
			for(PenetrationType pt : PenetrationType.values()) {
				wetPenetrationTypes.get(character).put(pt, new HashSet<>());
			}
		}
		for(GameCharacter character : Sex.getAllParticipants()) {
			wetOrificeTypes.put(character, new HashMap<OrificeType, Set<LubricationType>>());
			for(OrificeType ot : OrificeType.values()) {
				wetOrificeTypes.get(character).put(ot, new HashSet<>());
			}
		}
		
		// Starting text:
		sexSB = new StringBuilder(sexStartDescription);

		sexSB.append(sexManager.getStartSexDescription());

		sexSB.append("<p style='text-align:center;'><b>Starting Position:</b> <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>"+sexManager.getPosition().getName()+"</b></br>"
				+"<i><b>"+sexManager.getPosition().getDescription()+"</b></i></p>");
		
		// Starting exposed:
		for(GameCharacter character : Sex.getAllParticipants()) {
			handleExposedDescriptions(character, true);
		}
		
		// This method appends wet descriptions to the sexSB StringBuilder: TODO
		calculateWetAreas(true);

		sexDescription = sexSB.toString();

		Main.game.setInSex(true);
		
		//TODO why was this here?
		// The reason has long been lost in time, like... tears in rain...
//		Main.mainController.openInventory();

		// Main.mainController.updateUI();

		// Store status of all clothes for both partners (so they can be restored afterwards):
		clothingPreSexMap = new HashMap<>();
		
		for(GameCharacter character : Sex.getAllParticipants()) {
			clothingPreSexMap.put(character, new HashMap<>());
			for (AbstractClothing c : character.getClothingCurrentlyEquipped()) {
				clothingPreSexMap.get(character).put(c, new ArrayList<>(c.getDisplacedList()));
//				System.out.println(c.getName()+": "+clothingPreSexMap.get(character).get(c));
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
		
		// This is to keep the applied creampies post-sex:
		for(GameCharacter participant : Sex.getAllParticipants()) {
			for(OrificeType ot : OrificeType.values()) {
				if(participant.getCummedInAreaMap().get(ot)>0) {
					participant.incrementCummedInArea(ot, postSexDialogue.getMinutesPassed() * ot.getCumLossPerMinute());
				}
			}
		}
		
		if(SexFlags.playerGrewDemonicCock) {
			Main.game.getPlayer().setPenisType(PenisType.NONE);
		}
		
		activePartner.setLastTimeHadSex(Main.game.getMinutesPassed(), Sex.getNumberOfOrgasms(activePartner)>0);
		for(GameCharacter participant : Sex.getAllParticipants()) {
			if(participant instanceof NPC) {
				((NPC)participant).endSex(true);
			}
		}
	}

	private static String endSexDescription;

	public static void applyEndSexEffects() {
		sexSB = new StringBuilder();

		// Stretching effects for each of the player's orifices:
		if (Main.game.getPlayer().getAssRawCapacityValue() != Main.game.getPlayer().getAssStretchedCapacity() && areasStretched.get(Main.game.getPlayer()).contains(OrificeType.ANUS)) {
			if (Main.game.getPlayer().getAssPlasticity() == OrificePlasticity.ZERO_RUBBERY){

				Main.game.getPlayer().setAssStretchedCapacity(Main.game.getPlayer().getAssRawCapacityValue());

				sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Your asshole quickly recovers from its ordeal, and instantly returns to its original size!</b></p>");

			} else {

				// Increment core capacity by the Elasticity's capacityIncreaseModifier:
				Main.game.getPlayer().incrementAssCapacity(
						(Main.game.getPlayer().getAssStretchedCapacity()-Main.game.getPlayer().getAssRawCapacityValue())*Main.game.getPlayer().getAssPlasticity().getCapacityIncreaseModifier(),
						false);

				sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Your " + Main.game.getPlayer().getAssPlasticity().getDescriptor() + " asshole has been stretched from its ordeal, and is currently "
						+ Capacity.getCapacityFromValue(Main.game.getPlayer().getAssStretchedCapacity()).getDescriptor() + "!");

				if(Main.game.getPlayer().getAssPlasticity().getCapacityIncreaseModifier()>0) {
					sexSB.append(" It will recover some of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(Main.game.getPlayer().getAssRawCapacityValue()).getDescriptor() + "!</b></p>");
				} else {
					sexSB.append(" It will recover all of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(Main.game.getPlayer().getAssRawCapacityValue()).getDescriptor() + "!</b></p>");
				}
			}
		}
		
		if (Main.game.getPlayer().getVaginaRawCapacityValue() != Main.game.getPlayer().getVaginaStretchedCapacity() && areasStretched.get(Main.game.getPlayer()).contains(OrificeType.VAGINA)) {
			if (Main.game.getPlayer().getVaginaPlasticity() == OrificePlasticity.ZERO_RUBBERY){

				Main.game.getPlayer().setVaginaStretchedCapacity(Main.game.getPlayer().getVaginaRawCapacityValue());

				sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Your vagina quickly recovers from its ordeal, and instantly returns to its original size!</b></p>");

			} else {
				// Increment core capacity by the Elasticity's capacityIncreaseModifier:
				Main.game.getPlayer().incrementVaginaCapacity(
						(Main.game.getPlayer().getVaginaStretchedCapacity()-Main.game.getPlayer().getVaginaRawCapacityValue())*Main.game.getPlayer().getVaginaPlasticity().getCapacityIncreaseModifier(),
						false);
				
				sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Your " + Main.game.getPlayer().getVaginaPlasticity().getDescriptor() + " pussy has been stretched from its ordeal, and is currently "
						+ Capacity.getCapacityFromValue(Main.game.getPlayer().getVaginaStretchedCapacity()).getDescriptor() + "!");

				if(Main.game.getPlayer().getVaginaPlasticity().getCapacityIncreaseModifier()>0) {
					sexSB.append(" It will recover some of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(Main.game.getPlayer().getVaginaRawCapacityValue()).getDescriptor() + "!</b></p>");
				} else {
					sexSB.append(" It will recover all of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(Main.game.getPlayer().getVaginaRawCapacityValue()).getDescriptor() + "!</b></p>");
				}
			}
		}
		
		if (Main.game.getPlayer().getNippleRawCapacityValue() != Main.game.getPlayer().getNippleStretchedCapacity() && areasStretched.get(Main.game.getPlayer()).contains(OrificeType.NIPPLE)) {
			if (Main.game.getPlayer().getNipplePlasticity() == OrificePlasticity.ZERO_RUBBERY){

				Main.game.getPlayer().setNippleStretchedCapacity(Main.game.getPlayer().getNippleRawCapacityValue());

				sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Your [pc.nipples+] quickly recover from their ordeal, and instantly return to their original size!</b></p>");

			} else {
				// Increment core capacity by the Elasticity's capacityIncreaseModifier:
				Main.game.getPlayer().incrementNippleCapacity(
						(Main.game.getPlayer().getNippleStretchedCapacity()-Main.game.getPlayer().getNippleRawCapacityValue())*Main.game.getPlayer().getNipplePlasticity().getCapacityIncreaseModifier(),
						false);

				sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Your " + Main.game.getPlayer().getNipplePlasticity().getDescriptor() + " nipple-cunts have been stretched from their ordeal, and are currently "
						+ Capacity.getCapacityFromValue(Main.game.getPlayer().getNippleStretchedCapacity()).getDescriptor() + "!");

				if(Main.game.getPlayer().getNipplePlasticity().getCapacityIncreaseModifier()>0) {
					sexSB.append(" They will recover some of their original size, eventually tightening back to being " + Capacity.getCapacityFromValue(Main.game.getPlayer().getNippleRawCapacityValue()).getDescriptor() + "!</b></p>");
				} else {
					sexSB.append(" They will recover all of their original size, eventually tightening back to being " + Capacity.getCapacityFromValue(Main.game.getPlayer().getNippleRawCapacityValue()).getDescriptor() + "!</b></p>");
				}
			}
		}
		
		if (Main.game.getPlayer().getPenisRawCapacityValue() != Main.game.getPlayer().getPenisStretchedCapacity() && areasStretched.get(Main.game.getPlayer()).contains(OrificeType.URETHRA_PENIS)) {
			if (Main.game.getPlayer().getUrethraPlasticity() == OrificePlasticity.ZERO_RUBBERY){

				Main.game.getPlayer().setPenisStretchedCapacity(Main.game.getPlayer().getPenisRawCapacityValue());

				sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Your cock's urethra quickly recovers from its ordeal, and instantly returns to its original size!</b></p>");

			} else {

				// Increment core capacity by the Elasticity's capacityIncreaseModifier:
				Main.game.getPlayer().incrementPenisCapacity(
						(Main.game.getPlayer().getPenisStretchedCapacity()-Main.game.getPlayer().getPenisRawCapacityValue())*Main.game.getPlayer().getUrethraPlasticity().getCapacityIncreaseModifier(),
						false);

				sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Your cock's " + Main.game.getPlayer().getUrethraPlasticity().getDescriptor() + " urethra has been stretched from its ordeal, and is currently "
						+ Capacity.getCapacityFromValue(Main.game.getPlayer().getPenisStretchedCapacity()).getDescriptor() + "!");

				if(Main.game.getPlayer().getUrethraPlasticity().getCapacityIncreaseModifier()>0) {
					sexSB.append(" It will recover some of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(Main.game.getPlayer().getPenisRawCapacityValue()).getDescriptor() + "!</b></p>");
				} else {
					sexSB.append(" It will recover all of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(Main.game.getPlayer().getPenisRawCapacityValue()).getDescriptor() + "!</b></p>");
				}
			}
		}
		
		if (Main.game.getPlayer().getVaginaUrethraRawCapacityValue() != Main.game.getPlayer().getVaginaUrethraStretchedCapacity() && areasStretched.get(Main.game.getPlayer()).contains(OrificeType.URETHRA_VAGINA)) {
			if (Main.game.getPlayer().getVaginaUrethraPlasticity() == OrificePlasticity.ZERO_RUBBERY){

				Main.game.getPlayer().setVaginaUrethraStretchedCapacity(Main.game.getPlayer().getVaginaUrethraRawCapacityValue());

				sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Your pussy's urethra quickly recovers from its ordeal, and instantly returns to its original size!</b></p>");

			} else {

				// Increment core capacity by the Elasticity's capacityIncreaseModifier:
				Main.game.getPlayer().incrementVaginaUrethraCapacity(
						(Main.game.getPlayer().getVaginaUrethraStretchedCapacity()-Main.game.getPlayer().getVaginaUrethraRawCapacityValue())*Main.game.getPlayer().getVaginaUrethraPlasticity().getCapacityIncreaseModifier(),
						false);

				sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Your pussy's " + Main.game.getPlayer().getVaginaUrethraPlasticity().getDescriptor() + " urethra has been stretched from its ordeal, and is currently "
						+ Capacity.getCapacityFromValue(Main.game.getPlayer().getVaginaUrethraStretchedCapacity()).getDescriptor() + "!");

				if(Main.game.getPlayer().getVaginaUrethraPlasticity().getCapacityIncreaseModifier()>0) {
					sexSB.append(" It will recover some of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(Main.game.getPlayer().getVaginaUrethraRawCapacityValue()).getDescriptor() + "!</b></p>");
				} else {
					sexSB.append(" It will recover all of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(Main.game.getPlayer().getVaginaUrethraRawCapacityValue()).getDescriptor() + "!</b></p>");
				}
			}
		}
		
		if (Main.game.getPlayer().getFaceRawCapacityValue() != Main.game.getPlayer().getFaceStretchedCapacity() && areasStretched.get(Main.game.getPlayer()).contains(OrificeType.MOUTH)) {
				// Increment core capacity by the Elasticity's capacityIncreaseModifier:
				Main.game.getPlayer().incrementFaceCapacity(
						(Main.game.getPlayer().getFaceStretchedCapacity()-Main.game.getPlayer().getFaceRawCapacityValue())*Main.game.getPlayer().getFacePlasticity().getCapacityIncreaseModifier(),
						false);
				// Special case for throat, as you aren't stretching it out, merely getting more experienced at sucking cock:
				Main.game.getPlayer().setFaceStretchedCapacity(Main.game.getPlayer().getFaceRawCapacityValue());

				sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>From your oral experience with " + activePartner.getName("the") + "'s " + activePartner.getPenisSize().getDescriptor()
						+ " cock, you are now experienced enough to comfortably suck " + PenisSize.getPenisSizeFromInt((int)Main.game.getPlayer().getFaceRawCapacityValue()).getDescriptor() + " cocks!</b></p>");
		}

		// Stretching effects for each of the partner's orifices:
		if (activePartner.getAssRawCapacityValue() != activePartner.getAssStretchedCapacity() && areasStretched.get(Sex.getActivePartner()).contains(OrificeType.ANUS)) {
			if (activePartner.getAssPlasticity() == OrificePlasticity.ZERO_RUBBERY){

				activePartner.setAssStretchedCapacity(activePartner.getAssRawCapacityValue());

				sexSB.append(UtilText.parse(activePartner,
						"<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Her] asshole quickly recovers from its ordeal, and instantly returns to its original size!</b></p>"));

			} else {

				// Increment core capacity by the Elasticity's capacityIncreaseModifier:
				activePartner.incrementAssCapacity(
						(activePartner.getAssStretchedCapacity()-activePartner.getAssRawCapacityValue())*activePartner.getAssPlasticity().getCapacityIncreaseModifier(),
						false);

				sexSB.append(UtilText.parse(activePartner,
								"<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Her] " + activePartner.getAssElasticity().getDescriptor() + " asshole has been stretched from its ordeal, and is currently "
						+ Capacity.getCapacityFromValue(activePartner.getAssStretchedCapacity()).getDescriptor() + "!"));

				if(activePartner.getAssPlasticity().getCapacityIncreaseModifier()>0) {
					sexSB.append(" It will recover some of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(activePartner.getAssRawCapacityValue()).getDescriptor() + "!</b></p>");
				} else {
					sexSB.append(" It will recover all of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(activePartner.getAssRawCapacityValue()).getDescriptor() + "!</b></p>");
				}
			}
		}
		
		if (activePartner.getVaginaRawCapacityValue() != activePartner.getVaginaStretchedCapacity() && areasStretched.get(Sex.getActivePartner()).contains(OrificeType.VAGINA)) {
			if (activePartner.getVaginaPlasticity() == OrificePlasticity.ZERO_RUBBERY){

				activePartner.setVaginaStretchedCapacity(activePartner.getVaginaRawCapacityValue());


				sexSB.append(UtilText.parse(activePartner,
						"<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Her] vagina quickly recovers from its ordeal, and instantly returns to its original size!</b></p>"));

			} else {

				// Increment core capacity by the Elasticity's capacityIncreaseModifier:
				activePartner.incrementVaginaCapacity(
						(activePartner.getVaginaStretchedCapacity()-activePartner.getVaginaRawCapacityValue())*activePartner.getVaginaPlasticity().getCapacityIncreaseModifier(),
						false);

				sexSB.append(UtilText.parse(activePartner,
						"<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Her] " + activePartner.getVaginaPlasticity().getDescriptor() + " pussy has been stretched from its ordeal, and is currently "
						+ Capacity.getCapacityFromValue(activePartner.getVaginaStretchedCapacity()).getDescriptor() + "!"));

				if(activePartner.getVaginaPlasticity().getCapacityIncreaseModifier()>0) {
					sexSB.append(" It will recover some of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(activePartner.getVaginaRawCapacityValue()).getDescriptor() + "!</b></p>");
				} else {
					sexSB.append(" It will recover all of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(activePartner.getVaginaRawCapacityValue()).getDescriptor() + "!</b></p>");
				}
			}
		}
		
		if (activePartner.getNippleRawCapacityValue() != activePartner.getNippleStretchedCapacity() && areasStretched.get(Sex.getActivePartner()).contains(OrificeType.NIPPLE)) {
			if (activePartner.getNipplePlasticity() == OrificePlasticity.ZERO_RUBBERY){

				activePartner.setNippleStretchedCapacity(activePartner.getNippleRawCapacityValue());

				sexSB.append(UtilText.parse(activePartner,
						"<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Her] [npc.nipples+] quickly recover from their ordeal, and instantly return to their original size!</b></p>"));

			} else {

				// Increment core capacity by the Elasticity's capacityIncreaseModifier:
				activePartner.incrementNippleCapacity(
						(activePartner.getNippleStretchedCapacity()-activePartner.getNippleRawCapacityValue())*activePartner.getNipplePlasticity().getCapacityIncreaseModifier(),
						false);

				sexSB.append(UtilText.parse(activePartner,
						"<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Her] " + activePartner.getNipplePlasticity().getDescriptor() + " nipple-cunts have been stretched from their ordeal, and are currently "
						+ Capacity.getCapacityFromValue(activePartner.getNippleStretchedCapacity()).getDescriptor() + "!"));

				if(activePartner.getNipplePlasticity().getCapacityIncreaseModifier()>0) {
					sexSB.append(" They will recover some of their original size, eventually tightening back to being " + Capacity.getCapacityFromValue(activePartner.getNippleRawCapacityValue()).getDescriptor() + "!</b></p>");
				} else {
					sexSB.append(" They will recover all of their original size, eventually tightening back to being " + Capacity.getCapacityFromValue(activePartner.getNippleRawCapacityValue()).getDescriptor() + "!</b></p>");
				}
			}
		}
		
		if (activePartner.getPenisRawCapacityValue() != activePartner.getPenisStretchedCapacity() && areasStretched.get(Sex.getActivePartner()).contains(OrificeType.URETHRA_PENIS)) {
			if (activePartner.getUrethraPlasticity() == OrificePlasticity.ZERO_RUBBERY){

				activePartner.setPenisStretchedCapacity(activePartner.getPenisRawCapacityValue());

				sexSB.append(UtilText.parse(activePartner,
						"<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Her] cock's urethra quickly recovers from its ordeal, and instantly returns to its original size!</b></p>"));

			} else {

				// Increment core capacity by the Elasticity's capacityIncreaseModifier:
				activePartner.incrementPenisCapacity(
						(activePartner.getPenisStretchedCapacity()-activePartner.getPenisRawCapacityValue())*activePartner.getUrethraPlasticity().getCapacityIncreaseModifier(),
						false);

				sexSB.append(UtilText.parse(activePartner,
						"<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Her] cock's " + activePartner.getUrethraPlasticity().getDescriptor() + " urethra has been stretched from its ordeal, and is currently "
						+ Capacity.getCapacityFromValue(activePartner.getPenisStretchedCapacity()).getDescriptor() + "!"));

				if(activePartner.getUrethraPlasticity().getCapacityIncreaseModifier()>0) {
					sexSB.append(" It will recover some of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(activePartner.getPenisRawCapacityValue()).getDescriptor() + "!</b></p>");
				} else {
					sexSB.append(" It will recover all of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(activePartner.getPenisRawCapacityValue()).getDescriptor() + "!</b></p>");
				}
			}
		}
		
		if (activePartner.getVaginaUrethraRawCapacityValue() != activePartner.getVaginaUrethraStretchedCapacity() && areasStretched.get(Sex.getActivePartner()).contains(OrificeType.URETHRA_VAGINA)) {
			if (activePartner.getVaginaUrethraPlasticity() == OrificePlasticity.ZERO_RUBBERY){

				activePartner.setVaginaUrethraStretchedCapacity(activePartner.getVaginaUrethraRawCapacityValue());

				sexSB.append(UtilText.parse(activePartner,
						"<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Her] vagina's urethra quickly recovers from its ordeal, and instantly returns to its original size!</b></p>"));

			} else {

				// Increment core capacity by the Elasticity's capacityIncreaseModifier:
				activePartner.incrementVaginaUrethraCapacity(
						(activePartner.getVaginaUrethraStretchedCapacity()-activePartner.getVaginaUrethraRawCapacityValue())*activePartner.getVaginaUrethraPlasticity().getCapacityIncreaseModifier(),
						false);

				sexSB.append(UtilText.parse(activePartner,
						"<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Her] vagina's " + activePartner.getVaginaUrethraPlasticity().getDescriptor() + " urethra has been stretched from its ordeal, and is currently "
						+ Capacity.getCapacityFromValue(activePartner.getVaginaUrethraStretchedCapacity()).getDescriptor() + "!"));

				if(activePartner.getVaginaUrethraPlasticity().getCapacityIncreaseModifier()>0) {
					sexSB.append(" It will recover some of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(activePartner.getVaginaUrethraRawCapacityValue()).getDescriptor() + "!</b></p>");
				} else {
					sexSB.append(" It will recover all of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(activePartner.getVaginaUrethraRawCapacityValue()).getDescriptor() + "!</b></p>");
				}
			}
		}
		
		if (activePartner.getFaceRawCapacityValue() != activePartner.getFaceStretchedCapacity() && areasStretched.get(Sex.getActivePartner()).contains(OrificeType.MOUTH)) {
			// Increment core capacity by the Elasticity's capacityIncreaseModifier:
			activePartner.incrementFaceCapacity(
					(activePartner.getFaceStretchedCapacity()-activePartner.getFaceRawCapacityValue())*activePartner.getFacePlasticity().getCapacityIncreaseModifier(),
					false);
			// Special case for throat, as you aren't stretching it out, merely getting more experienced at sucking cock:
			activePartner.setFaceStretchedCapacity(activePartner.getFaceRawCapacityValue());

			sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>From [npc.her] oral experience with your [pc.cockSize] cock, [npc.she] is now experienced enough to comfortably suck "
					+ PenisSize.getPenisSizeFromInt((int)Main.game.getPlayer().getFaceRawCapacityValue()).getDescriptor() + " cocks!</b></p>");
		}
		

//		// Player pregnancy:
//		if (!areasCummedIn.get(Main.game.getPlayer()).isEmpty()) {
//			if (areasCummedIn.get(Main.game.getPlayer()).contains(OrificeType.VAGINA))
//				sexSB.append(Main.game.getPlayer().rollForPregnancy(activePartner));
//		}
//		// Partner pregnancy:
//		if (!areasCummedIn.get(Sex.getActivePartner()).isEmpty() && activePartner.isAbleToBeImpregnated()) {
//			if (areasCummedIn.get(Sex.getActivePartner()).contains(OrificeType.VAGINA))
//				sexSB.append(activePartner.rollForPregnancy(Main.game.getPlayer()));
//		}
		
		if(Main.game.getPlayer().getArousal() > ArousalLevel.THREE_HEATED.getMaximumValue()) {
			Main.game.getPlayer().addStatusEffect(StatusEffect.FRUSTRATED_NO_ORGASM, 240+postSexDialogue.getMinutesPassed());
			sexSB.append("<p style='text-align:center'>[style.boldArcane(After stopping so close to the edge, you're left feeling frustrated and horny!)]</p>");
		}
		if(getNumberOfOrgasms(Main.game.getPlayer()) > 0
				&& Main.game.isInNewWorld()) {
			Main.game.getPlayer().removeStatusEffect(StatusEffect.FRUSTRATED_NO_ORGASM);
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.RECOVERING_AURA)) {
				sexSB.append("<p style='text-align:center'><b>Your arcane aura is still strengthened from a previous sexual encounter, so</b> [style.boldArcane(you don't receive any arcane essences!)]</p>");
				
			} else {
				if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.essenceOrgasmDiscovered)) {
					Main.game.getDialogueFlags().values.add(DialogueFlagValue.essenceOrgasmDiscovered);
					if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
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
								+(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)?Main.game.getPlayer().startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY):"")));
						
					} else {
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
					}
				}
				
				sexSB.append("<p style='text-align:center'>You feel your aura pulsating all around you as it draws strength from the sexual energy of your orgasm...</p>"
								+"<div class='container-full-width' style='text-align:center;'>"
									+ (Main.game.getPlayer().hasTrait(Perk.NYMPHOMANIAC, true)
											?Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, 4, true)
											:Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, 2, true))
								+ "</div>");
				
			}
			Main.game.getPlayer().addStatusEffect(StatusEffect.RECOVERING_AURA, 240);	
		}
		
		if(activePartner.getArousal() > ArousalLevel.THREE_HEATED.getMaximumValue()) {
			activePartner.addStatusEffect(StatusEffect.FRUSTRATED_NO_ORGASM, 240+postSexDialogue.getMinutesPassed());
			sexSB.append("<p style='text-align:center'>[style.boldArcane(After stopping so close to the edge, [npc.name] is left feeling frustrated and horny!)]</p>");
		}
		if(getNumberOfOrgasms(activePartner) > 0
				&& Main.game.isInNewWorld()) {
			activePartner.removeStatusEffect(StatusEffect.FRUSTRATED_NO_ORGASM);
			if(activePartner.hasStatusEffect(StatusEffect.RECOVERING_AURA)) {
				sexSB.append("<p style='text-align:center'><b>[npc.Name]'s arcane aura is still strengthened from a previous sexual encounter, so</b> [style.boldArcane(you don't receive any arcane essences!)]</p>");
				
			} else {
				
				if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.essenceOrgasmDiscovered)) {
					Main.game.getDialogueFlags().values.add(DialogueFlagValue.essenceOrgasmDiscovered);
					if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
						sexSB.append(
								UtilText.parse(activePartner,
								"<p>"
									+ "As you disentangle yourself from [npc.name], you suddenly become aware of a strange, shimmering pink glow that's started to materialise around [npc.her] body,"
										+ " just like the one you saw in Lilaya's lab when she ran her tests on you."
									+ " Quickly realising that you're somehow able to see [npc.name]'s arcane aura, you watch, fascinated, as it rapidly increases in luminosity."
									+ " Just as you think that it can't get any brighter, [npc.her] aura suddenly leaps back into [npc.her] body, but as it does so, a single shard breaks off and flies towards you."
									+ " Unable to dodge in time, you find yourself sharply inhaling as the small piece of [npc.name]'s aura shoots into your chest."
								+ "</p>"
								+ "<p>"
									+ "Alarmed at what's just happened, you look back over at [npc.name], only to see that [npc.she] seems completely oblivious to what you've just witnessed."
									+ " You think that it would probably be best to go and ask Lilaya about what just happened..."
								+ "</p>"
								+(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)?Main.game.getPlayer().startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY):"")));
						
					} else {
						sexSB.append(
								UtilText.parse(activePartner,
								"<p>"
									+ "As you disentangle yourself from [npc.name], you suddenly become aware of a strange, shimmering pink glow that's started to materialise around [npc.her] body,"
										+ " just like the one you saw in Lilaya's lab when she ran her tests on you."
									+ " Quickly realising that you're somehow able to see [npc.name]'s arcane aura, you watch, fascinated, as it rapidly increases in luminosity."
									+ " Just as you think that it can't get any brighter, [npc.her] aura suddenly leaps back into [npc.her] body, but as it does so, a single shard breaks off and flies towards you."
									+ " Unable to dodge in time, you find yourself sharply inhaling as the small piece of [npc.name]'s aura shoots into your chest."
								+ "</p>"
								+ "<p>"
									+ "Alarmed at what's just happened, you look back over at [npc.name], only to see that [npc.she] seems completely oblivious to what you've just witnessed."
									+ " You suddenly remember what Lilaya told you about absorbing essences, and how it's absolutely harmless for both parties involved, and breathe a sigh of relief..."
								+ "</p>"));
					}
				}
				
				sexSB.append("<p style='text-align:center'>You feel your aura drawing strength from the sexual energy of [npc.name]'s orgasm...</p>"
						+"<div class='container-full-width' style='text-align:center;'>"
							+ (Main.game.getPlayer().hasTrait(Perk.NYMPHOMANIAC, true)
									?Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, 4, true)
									:Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, 2, true))
						+ "</div>");
				
			}
			activePartner.addStatusEffect(StatusEffect.RECOVERING_AURA, 240);
		}
		
		if(Sex.getNumberOfOrgasms(Main.game.getPlayer())>0) {
			Main.game.getPlayer().setLust(0);
		}
		
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
		public String getLabel() {
			return (!Sex.isConsensual() && Main.getProperties().hasValue(PropertyValue.nonConContent)?"Non-consensual ":"")+(Sex.getSexManager().isPublicSex()?"Public ":"")+"Sex: "+getPosition().getName();
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
					|| activePartner.getArousal() >= ArousalLevel.FIVE_ORGASM_IMMINENT.getMaximumValue()) {
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
						|| activePartner.getArousal() >= ArousalLevel.FIVE_ORGASM_IMMINENT.getMaximumValue()) {
					
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
					List<SexActionInterface> availableRepeatActionsPlayer = new ArrayList<>();
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
	 * If you call this while not in sex, you're going to seriously f*** things up.
	 * @param sexActionPlayer The action that the player is taking this turn.
	 */
	public static void endSexTurn(SexActionInterface sexActionPlayer) {

		sexSB = new StringBuilder();

		sexSB.append("<p>" + sexActionPlayer.getDescription() + "</p>");
		
		String endString = sexActionPlayer.baseEffects();
		
		applyGenericDescriptionsAndEffects(Main.game.getPlayer(), sexActionPlayer);

		sexSB.append(endString);
		
		String s = UtilText.parse(Sex.getActivePartner(), sexSB.toString());
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
			
			GameCharacter active = Sex.getActivePartner();
			
			for(GameCharacter character : Sex.getAllParticipants()) {
				if(!character.isPlayer()) {
					Sex.setActivePartner((NPC) character);
					
					if(sexActionPlayer.getActionType()!=SexActionType.PLAYER_ORGASM && sexActionPlayer.getActionType()!=SexActionType.PLAYER_ORGASM_NO_AROUSAL_RESET) {
						calculateAvailableSexActionsPartner();
			
						SexActionInterface sexActionPartner = sexManager.getPartnerSexAction(sexActionPlayer);
						
						sexSB.append("</br><p>" + sexActionPartner.getDescription() + "</p>");
			
						endString = sexActionPartner.baseEffects();
						lastUsedPartnerAction = sexActionPartner;
						
						applyGenericDescriptionsAndEffects(Sex.getActivePartner(), sexActionPartner);

						sexSB.append(endString);
						
						s = UtilText.parse(character, sexSB.toString());
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
			
			if(Sex.getSexManager().isPublicSex()) {
				sexSB.append(Sex.getSexManager().getRandomPublicSexDescription());
				sexDescription = sexSB.toString();
			}
			
			// Re-populate lists for the player's next action choice.
			populatePlayerSexLists();
		}

		lastUsedPlayerAction = sexActionPlayer;
		if(sexActionPlayer.getActionType()!=SexActionType.PLAYER_PREPARE_PARTNER_ORGASM
				&& sexActionPlayer.getActionType()!=SexActionType.PLAYER_ORGASM
				&& sexActionPlayer.getActionType()!=SexActionType.PLAYER_ORGASM_NO_AROUSAL_RESET) {
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

		} else if (activePartner.getArousal() >= ArousalLevel.FIVE_ORGASM_IMMINENT.getMaximumValue()) { // Add orgasm reactions if partner is ready to orgasm:
			for (SexActionInterface sexAction : Sex.getActionsAvailablePlayer()) {
				if (sexAction.getActionType()==SexActionType.PLAYER_PREPARE_PARTNER_ORGASM && sexAction.isAddedToAvailableSexActions()) {
					availableSexActionsPlayer.add(sexAction);
				}
			}
			
		} else {
			// Can always do nothing:
			availableSexActionsPlayer.add(SexActionUtility.PLAYER_NONE);
			availableSexActionsPlayer.add(SexActionUtility.PLAYER_CALM_DOWN);

			if(Main.game.getPlayer().hasFetish(Fetish.FETISH_DENIAL)) {
				if(isConsensual() || isDom(Main.game.getPlayer()))
					availableSexActionsPlayer.add(SexActionUtility.DENIAL_FETISH_DENY);
			}

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
		positionActionsPlayer.sort((a1, a2) -> a1.getActionTitle().compareTo(a2.getActionTitle()));
		
		if(Sex.getTotalParticipantCount()>2) {
			for(GameCharacter character : Sex.getDominantParticipants().keySet()) {
				if(!character.isPlayer() && !Sex.getTargetedPartner(Main.game.getPlayer()).equals(character)) {
					miscActionsPlayer.add(new SexAction(
												SexActionType.PLAYER,
												ArousalIncrease.ZERO_NONE,
												ArousalIncrease.ZERO_NONE,
												CorruptionLevel.ZERO_PURE,
												null,
												null,
												SexParticipantType.MISC) {
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
												SexActionType.PLAYER,
												ArousalIncrease.ZERO_NONE,
												ArousalIncrease.ZERO_NONE,
												CorruptionLevel.ZERO_PURE,
												null,
												null,
												SexParticipantType.MISC) {
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
								if(activePartner.getFetishDesire(f)==FetishDesire.ONE_DISLIKE || activePartner.getFetishDesire(f)==FetishDesire.ZERO_HATE) {
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
				if (sexAction.getActionType()==SexActionType.PARTNER_PREPARE_PLAYER_ORGASM) {
					if (sexAction.isAddedToAvailableSexActions()) {
						boolean dislikedAction = false;
						if(sexAction.getFetishes(activePartner)!=null) {
							for(Fetish f : sexAction.getFetishes(activePartner)) {
								if(activePartner.getFetishDesire(f)==FetishDesire.ONE_DISLIKE || activePartner.getFetishDesire(f)==FetishDesire.ZERO_HATE) {
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
				if (sexAction.isAddedToAvailableSexActions() && (partnerAllowedToUseSelfActions || (!sexAction.isPartnerSelfPenetration()))) {
					
					if(// Do not add action if the partner is resisting and this action is SUB_EAGER or SUB_NORMAL or is a self action
						(Main.game.isNonConEnabled()
							&& getSexPace(activePartner)==SexPace.SUB_RESISTING
							&& ((sexAction.getSexPace(Sex.getActivePartner())!=null && sexAction.getSexPace(Sex.getActivePartner())!=SexPace.SUB_RESISTING) || sexAction.isPartnerSelfAction()))
						// Do not add action if action does not correspond to the partner's preferred action pace
						|| (sexAction.getSexPace(Sex.getActivePartner())!=null && getSexPace(activePartner)!=sexAction.getSexPace(Sex.getActivePartner()))) {
						
						
					} else {
						// Add action as normal:
						boolean dislikedAction = false;
						if(sexAction.getFetishes(activePartner)!=null) {
							for(Fetish f : sexAction.getFetishes(activePartner)) {
								if(activePartner.getFetishDesire(f)==FetishDesire.ONE_DISLIKE || activePartner.getFetishDesire(f)==FetishDesire.ZERO_HATE) {
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
		arousalIncrements.put(targetCharacter, sexAction.getArousalGainTarget().getArousalIncreaseValue());
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
		if(sexAction.getFetishes(activeCharacter)!=null && sexAction.getSexPace(activeCharacter)!=SexPace.SUB_RESISTING) {
			for(Fetish f : sexAction.getFetishes(activeCharacter)) {
				if(activeCharacter.hasFetish(f)) {
					arousalIncrements.put(activeCharacter, arousalIncrements.get(activeCharacter) + activeCharacter.getFetishLevel(f).getBonusArousalIncrease());
					arousalIncrements.put(targetCharacter, arousalIncrements.get(targetCharacter) + activeCharacter.getFetishLevel(f).getBonusArousalIncreasePartner());
					activeCharacter.incrementFetishExperience(f, f.getExperienceGainFromSexAction());
				}
				lustIncrements.put(activeCharacter, lustIncrements.get(activeCharacter) + activeCharacter.getFetishDesire(f).getLustIncrement());
			}
		}

		// Arousal increments for this target's related fetishes:
		if(sexAction.getFetishesForTargetedPartner(activeCharacter)!=null && sexAction.getSexPace(targetCharacter)!=SexPace.SUB_RESISTING) {
			for(Fetish f : sexAction.getFetishesForTargetedPartner(activeCharacter)) {
				if(targetCharacter.hasFetish(f)) {
					arousalIncrements.put(targetCharacter, arousalIncrements.get(targetCharacter) + targetCharacter.getFetishLevel(f).getBonusArousalIncrease());
					arousalIncrements.put(activeCharacter, arousalIncrements.get(activeCharacter) + targetCharacter.getFetishLevel(f).getBonusArousalIncreasePartner());
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
		
		// Modify arousal value based on lust:
		for(Entry<GameCharacter, Float> entry : arousalIncrements.entrySet()) {
			entry.getKey().incrementArousal(Math.min(10, entry.getValue() * entry.getKey().getLustLevel().getArousalModifier()));
		}
		
		// Cummed in areas:

		// Add any areas that have been cummed in:
		// TODO Take into account condom being used on other penetrationTypes
		for(GameCharacter cumProvidor : Sex.getAllParticipants()) {
			for(GameCharacter cumTarget :Sex.getAllParticipants()) {
				if(cumProvidor.equals(activeCharacter) || cumTarget.equals(activeCharacter)) {
					if(cumProvidor.getPenisCumProduction() != CumProduction.ZERO_NONE) {
						if (sexAction.getAreasCummedIn(cumProvidor, cumTarget) != null) {
							if(!cumProvidor.isWearingCondom() || sexAction.ignoreCondom(cumProvidor)){
								for(OrificeType ot : sexAction.getAreasCummedIn(cumProvidor, cumTarget)) {
									
									cumTarget.incrementCumCount(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, ot));
									cumProvidor.incrementCumCount(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, ot));
									sexSB.append(cumTarget.ingestFluid(cumProvidor, cumProvidor.getCum().getType(), ot, cumProvidor.getPenisRawCumProductionValue(), cumProvidor.getCum().getFluidModifiers()));
									
									cumTarget.incrementCummedInArea(ot, cumProvidor.getPenisRawCumProductionValue());
									
									if(cumTarget.getBodyMaterial()==BodyMaterial.SLIME) {
										sexSB.append(cumTarget.rollForPregnancy(cumProvidor));
										
									} else {
										if(ot == OrificeType.VAGINA) {
											sexSB.append(cumTarget.rollForPregnancy(cumProvidor));
										}
									}
								}
							}
						}
						
						if(sexAction.getAreasCummedOn(cumProvidor, cumTarget)!=null) {
							if(!cumProvidor.isWearingCondom() || sexAction.ignoreCondom(cumProvidor)){
								for(CoverableArea area : sexAction.getAreasCummedOn(cumProvidor, cumTarget)) {
									switch(area) {
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

		if(sexAction.getActionType()==SexActionType.PLAYER_PREPARE_PARTNER_ORGASM) {
			SexFlags.playerPreparedForOrgasm = true;
		}
		
		// Handle player orgasms:
		if(sexAction.getActionType()==SexActionType.PLAYER_ORGASM) {
			// Condom removal:
			if(Main.game.getPlayer().isWearingCondom()){
				Main.game.getPlayer().getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()).setSealed(false);
				if(Main.game.getPlayer().getPenisRawCumProductionValue()>0) {
					sexSB.append(Main.game.getPlayer().addItem(AbstractItemType.generateFilledCondom(Main.game.getPlayer().getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()).getColour(), Main.game.getPlayer(), Main.game.getPlayer().getCum()), false, true));
				}
				Main.game.getPlayer().unequipClothingIntoVoid(Main.game.getPlayer().getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()), true, Main.game.getPlayer());
				
			}
			
			if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isVaginaSquirter()) {
				if(Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.VAGINA)!=null) {
					Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.VAGINA).setDirty(true);
				} else {
					if(Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.VAGINA) == PenetrationType.TONGUE) {
						sexSB.append(Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.VAGINA).ingestFluid(
								Main.game.getPlayer(),
								Main.game.getPlayer().getGirlcumType(),
								OrificeType.MOUTH,
								5 * Main.game.getPlayer().getVaginaWetness().getValue(),
								Main.game.getPlayer().getGirlcum().getFluidModifiers()));
					}
				}
			}
			
			// Apply orgasm arousal resets:
			incrementNumberOfOrgasms(Main.game.getPlayer(), 1);
			player().setArousal(0);
			
			// Reset appropriate flags:
			SexFlags.partnerRequestedCreampie = false;
			SexFlags.partnerRequestedPullOut = false;
			
		}
		// Handle partner orgasms:
		if(sexAction.getActionType()==SexActionType.PARTNER_ORGASM) {
			// Condom removal:
			if(activePartner.isWearingCondom()){
				activePartner.getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()).setSealed(false);
				if(activePartner.getPenisRawCumProductionValue()>0) {
					sexSB.append(Main.game.getPlayer().addItem(AbstractItemType.generateFilledCondom(activePartner.getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()).getColour(), activePartner, activePartner.getCum()), false, true));
				}
				activePartner.unequipClothingIntoVoid(activePartner.getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()), true, activePartner);
				
			}
			
			if(activePartner.hasVagina() && activePartner.isVaginaSquirter()) {
				if(activePartner.getLowestZLayerCoverableArea(CoverableArea.VAGINA)!=null) {
					activePartner.getLowestZLayerCoverableArea(CoverableArea.VAGINA).setDirty(true);
				} else {
					if(Sex.getPenetrationTypeInOrifice(activePartner, OrificeType.VAGINA) == PenetrationType.TONGUE) {
						sexSB.append(Sex.getPenetratingCharacterUsingOrifice(activePartner, OrificeType.VAGINA).ingestFluid(
								activePartner,
								activePartner.getGirlcumType(),
								OrificeType.MOUTH,
								5 * activePartner.getVaginaWetness().getValue(),
								activePartner.getGirlcum().getFluidModifiers()));
					}
				}
			}
			
			// Apply orgasm arousal resets:
			incrementNumberOfOrgasms(activePartner, 1);
			activePartner.setArousal(0);

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
				&& !Sex.getOrgasmActionsPartner().contains(sexAction)
				&& sexAction.getActionType() != SexActionType.PARTNER_POSITIONING
				&& sexAction.getActionType() != SexActionType.PLAYER_POSITIONING
				&& !sexAction.endsSex()) {
			for(GameCharacter characterPenetrating : Sex.getAllParticipants()) {
				for(GameCharacter characterPenetrated : Sex.getAllParticipants()) {
					for(Entry<PenetrationType, Set<OrificeType>> entry : ongoingPenetrationMap.get(characterPenetrating).get(characterPenetrated).entrySet()) {
						for(OrificeType t : entry.getValue()) {
							applyPenetrationEffects(characterPenetrating, characterPenetrated, entry.getKey(), t);
							List<Fetish> penetratingFetishes = getFetishesFromPenetrationAndOrificeTypes(characterPenetrating, characterPenetrating, entry.getKey(), characterPenetrated, t);
							List<Fetish> penetratedFetishes = getFetishesFromPenetrationAndOrificeTypes(characterPenetrated, characterPenetrating, entry.getKey(), characterPenetrated, t);
							
							// Half lust and xp from ongoing:
							for(Fetish f : penetratingFetishes) {
								characterPenetrating.incrementLust(characterPenetrating.getFetishDesire(f).getLustIncrement()/2);
								characterPenetrating.incrementFetishExperience(f, f.getExperienceGainFromSexAction()/2);
							}
							for(Fetish f : penetratedFetishes) {
								characterPenetrated.incrementLust(characterPenetrated.getFetishDesire(f).getLustIncrement()/2);
								characterPenetrated.incrementFetishExperience(f, f.getExperienceGainFromSexAction()/2);
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
						+ "<b style='color:"+finalPace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(finalPace.getName())+" Pace</b></br>");
				switch(finalPace) {
					case DOM_GENTLE:
						sexSB.append("[npc.Name] lets out a soft sigh as [npc.she] calms down a little...");
						break;
					case DOM_NORMAL:
						sexSB.append("[npc.Name] lets out "+(entry.getValue()==SexPace.DOM_GENTLE?"[npc.a_moan+] as [npc.she] starts getting more turned on":"a soft [npc.moan] as [npc.she] calms down a little")+"...");
						break;
					case DOM_ROUGH:
						sexSB.append("[npc.Name] lets out a wild [npc.moan] as [npc.she] finds [npc.herself] filled with lust...");
						break;
					case SUB_EAGER:
						sexSB.append("[npc.Name] lets out a desperate [npc.moan] as [npc.she] finds [npc.herself] filled with lust...");
						break;
					case SUB_NORMAL:
						sexSB.append("[npc.Name] lets out "+(entry.getValue()==SexPace.SUB_RESISTING?"[npc.a_moan+] as [npc.she] starts getting more turned on":"a soft [npc.moan] as [npc.she] calms down a little")+"...");
						break;
					case SUB_RESISTING:
						sexSB.append("[npc.Name] lets out an uncomfortable whine as [npc.she] suddenly finds [npc.herself] not happy with the current situation...");
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
							+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, OrificeType.ANUS)));
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
								+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, PenetrationType.PENIS)));
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
								+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, OrificeType.VAGINA)));
	
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
							+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, OrificeType.NIPPLE)));
					areasExposed.get(Main.game.getPlayer()).add(CoverableArea.NIPPLES);
				}
			}
		} else {
			// Partner:
			if (!areasExposed.get(characterBeingExposed).contains(CoverableArea.ANUS)) {
				if (characterBeingExposed.isAbleToAccessCoverableArea(CoverableArea.ANUS, false)) {
					sexSB.append(
							formatCoverableAreaBecomingExposed(
									(atStartOfSex
											?"[npc.Name]'s [npc.asshole+] was already exposed before starting sex!"
											:"[npc.Name]'s [npc.asshole+] is now exposed!"))
							+ sexManager.getPartnerAssRevealReaction()
							+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, OrificeType.ANUS)));
					areasExposed.get(characterBeingExposed).add(CoverableArea.ANUS);
				}
			}
			if (!areasExposed.get(characterBeingExposed).contains(CoverableArea.PENIS)) {
				if (characterBeingExposed.isAbleToAccessCoverableArea(CoverableArea.PENIS, false)) {
					if (characterBeingExposed.hasPenis()) {
						sexSB.append(
								formatCoverableAreaBecomingExposed(
										(atStartOfSex
												?"[npc.Name]'s [npc.cock+] was already exposed before starting sex!"
												:"[npc.Name]'s [npc.cock+] is now exposed!"))
								+ sexManager.getPartnerPenisRevealReaction()
								+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, PenetrationType.PENIS)));
					}
					areasExposed.get(characterBeingExposed).add(CoverableArea.PENIS);
				}
			}
			if (!areasExposed.get(characterBeingExposed).contains(CoverableArea.VAGINA)) {
				if (characterBeingExposed.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false)) {
					if (characterBeingExposed.hasVagina()) {
						sexSB.append(
								formatCoverableAreaBecomingExposed(
										(atStartOfSex
												?"[npc.Name]'s [npc.pussy+] was already exposed before starting sex!"
												:"[npc.Name]'s [npc.pussy+] is now exposed!"))
								+ sexManager.getPartnerVaginaRevealReaction()
								+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, OrificeType.VAGINA)));
	
					} else if (characterBeingExposed.getVaginaType() == VaginaType.NONE && characterBeingExposed.getPenisType() == PenisType.NONE) {
						sexSB.append(
								formatCoverableAreaBecomingExposed(
										(atStartOfSex
												?"[npc.Name]'s doll-like mound was already exposed before starting sex!"
												:"[npc.Name]'s doll-like mound is now exposed!"))
								+ sexManager.getPartnerMoundRevealReaction());
					}
					areasExposed.get(characterBeingExposed).add(CoverableArea.VAGINA);
				}
			}
			if (!areasExposed.get(characterBeingExposed).contains(CoverableArea.NIPPLES)) {
				if (characterBeingExposed.isAbleToAccessCoverableArea(CoverableArea.NIPPLES, false)) {
					sexSB.append(
							formatCoverableAreaBecomingExposed(
									(atStartOfSex
											?"[npc.Name]'s [npc.nipples+] were already exposed before starting sex!"
											:"[npc.Name]'s [npc.nipples+] are now exposed!"))
								+ sexManager.getPartnerBreastsRevealReaction()
								+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, OrificeType.NIPPLE)));
					areasExposed.get(characterBeingExposed).add(CoverableArea.NIPPLES);
				}
			}
		}
	}

	
	private static void calculateWetAreas(boolean onSexInit) {

		// Add starting lube:
		addOrificeLubrication(Main.game.getPlayer(), OrificeType.MOUTH, LubricationType.PLAYER_SALIVA, !onSexInit);
		addPenetrationTypeLubrication(Main.game.getPlayer(), PenetrationType.TONGUE, LubricationType.PLAYER_SALIVA, !onSexInit);
		
		
		// Add player lubrication from cum:
		if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_ANUS)) {
			addOrificeLubrication(Main.game.getPlayer(), OrificeType.ANUS, LubricationType.OTHER_CUM, !onSexInit);
		}
		if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_NIPPLES)) {
			addOrificeLubrication(Main.game.getPlayer(), OrificeType.NIPPLE, LubricationType.OTHER_CUM, !onSexInit);
		}
		if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)) {
			addOrificeLubrication(Main.game.getPlayer(), OrificeType.VAGINA, LubricationType.OTHER_CUM, !onSexInit);
		}

		// Add milk in nipples:
		if(Main.game.getPlayer().getBreastRawMilkStorageValue()>0) {
			addOrificeLubrication(Main.game.getPlayer(), OrificeType.NIPPLE, LubricationType.PLAYER_MILK, !onSexInit);
		}
		
		// Add player natural lubrications:
		if(Main.game.getPlayer().getArousal() >= Main.game.getPlayer().getAssWetness().getArousalNeededToGetVaginaWet()) {
			addOrificeLubrication(Main.game.getPlayer(), OrificeType.ANUS, LubricationType.PLAYER_ANAL_LUBE, !onSexInit);
		}
		if(Main.game.getPlayer().hasPenis()) {
			if(Main.game.getPlayer().getArousal() >= Main.game.getPlayer().getPenisCumProduction().getArousalNeededToStartPreCumming()) {
				addPenetrationTypeLubrication(Main.game.getPlayer(), PenetrationType.PENIS, LubricationType.PLAYER_PRECUM, !onSexInit);
				addOrificeLubrication(Main.game.getPlayer(), OrificeType.URETHRA_PENIS, LubricationType.PLAYER_PRECUM, !onSexInit);
			}
		}
		if(Main.game.getPlayer().hasVagina()) {
			if(Main.game.getPlayer().getArousal() >= Main.game.getPlayer().getVaginaWetness().getArousalNeededToGetVaginaWet()) {
				addOrificeLubrication(Main.game.getPlayer(), OrificeType.VAGINA, LubricationType.PLAYER_GIRLCUM, !onSexInit);
			}
		}
		
		for(GameCharacter character : Sex.getAllParticipants()) {
			if(character.getBodyMaterial()==BodyMaterial.SLIME && onSexInit) {
				for(OrificeType orifice : OrificeType.values()) {
					addOrificeLubrication(character, orifice, LubricationType.SLIME, !onSexInit);
				}
			}
			
			if(!character.isPlayer()) {
				addOrificeLubrication(character, OrificeType.MOUTH, LubricationType.PARTNER_SALIVA, !onSexInit);
				addPenetrationTypeLubrication(character, PenetrationType.TONGUE, LubricationType.PARTNER_SALIVA, !onSexInit);
				
				if(character.getBreastRawMilkStorageValue()>0) {
					addOrificeLubrication(character, OrificeType.NIPPLE, LubricationType.PARTNER_MILK, !onSexInit);
				}

				// Add partner lubrication from cum:
				if(character.hasStatusEffect(StatusEffect.CREAMPIE_ANUS)) {
					addOrificeLubrication(character, OrificeType.ANUS, LubricationType.OTHER_CUM, !onSexInit);
				}
				if(character.hasStatusEffect(StatusEffect.CREAMPIE_NIPPLES)) {
					addOrificeLubrication(character, OrificeType.NIPPLE, LubricationType.OTHER_CUM, !onSexInit);
				}
				if(character.hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)) {
					addOrificeLubrication(character, OrificeType.VAGINA, LubricationType.OTHER_CUM, !onSexInit);
				}
				
				// Add partner natural lubrications:
				if(character.getArousal() >= character.getAssWetness().getArousalNeededToGetVaginaWet()) {
					addOrificeLubrication(character, OrificeType.ANUS, LubricationType.PARTNER_ANAL_LUBE, !onSexInit);
				}
				if(character.hasPenis()) {
					if(character.getArousal() >= character.getPenisCumProduction().getArousalNeededToStartPreCumming()) {
						addPenetrationTypeLubrication(character, PenetrationType.PENIS, LubricationType.PARTNER_PRECUM);
						addOrificeLubrication(character, OrificeType.URETHRA_PENIS, LubricationType.PARTNER_PRECUM, !onSexInit);
					}
				}
				if(character.hasVagina()) {
					if(character.getArousal() >= character.getVaginaWetness().getArousalNeededToGetVaginaWet()) {
						addOrificeLubrication(character, OrificeType.VAGINA, LubricationType.PARTNER_GIRLCUM, !onSexInit);
					}
				}
			}
		}
		
		// Calculate lubrication transfers:
		for(GameCharacter characterPenetrating : Sex.getAllParticipants()) {
			for(GameCharacter characterPenetrated : Sex.getAllParticipants()) {
				for(Entry<PenetrationType, Set<OrificeType>> entry : ongoingPenetrationMap.get(characterPenetrating).get(characterPenetrated).entrySet()) {
					for(OrificeType ot : entry.getValue()) {
						transferLubrication(characterPenetrating,
								characterPenetrated,
								entry.getKey(),
								ot);
					}
				}
			}
		}
	}
	
	public static void transferLubrication(GameCharacter penetrator, GameCharacter penetrated, PenetrationType penetrationType, OrificeType orificeType) {
		List<String> lubricationTransferred = new ArrayList<>();
		boolean lastLubricationPlural = false;
		
		for(LubricationType lt : wetPenetrationTypes.get(penetrator).get(penetrationType)) {
			if(!wetOrificeTypes.get(penetrated).get(orificeType).contains(lt)) {
				wetOrificeTypes.get(penetrated).get(orificeType).add(lt);
				lubricationTransferred.add(lt.getName());
				lastLubricationPlural = lt.isPlural();
			}
		}
		
		if(!lubricationTransferred.isEmpty()) {
			sexSB.append(formatCoverableAreaGettingWet(
					Util.capitaliseSentence(Util.stringsToStringList(lubricationTransferred, false))+" quickly lubricate"+(lastLubricationPlural?" ":"s ")
						+(penetrated.isPlayer()?"your ":penetrated.getName("the")+"'s ")+orificeType.getName(penetrated)+"."));
		}
		
		lubricationTransferred.clear();
		
		for(LubricationType lt : wetOrificeTypes.get(penetrated).get(orificeType)) {
			if(!wetPenetrationTypes.get(penetrator).get(penetrationType).contains(lt)) {
				wetPenetrationTypes.get(penetrator).get(penetrationType).add(lt);
				lubricationTransferred.add(lt.getName());
				lastLubricationPlural = lt.isPlural();
			}
		}
		
		if(!lubricationTransferred.isEmpty()) {
			sexSB.append(formatCoverableAreaGettingWet(
					Util.capitaliseSentence(Util.stringsToStringList(lubricationTransferred, false))+" quickly lubricate"+(lastLubricationPlural?" ":"s ")
						+(penetrator.isPlayer()?"your ":penetrator.getName("the")+"'s ")+penetrationType.getName(penetrator)+"."));
		}
	}
	
	public static void transferLubrication(GameCharacter penetrator1, PenetrationType penetrationType1, GameCharacter penetrator2, PenetrationType penetrationType2) {
		List<String> lubricationTransferred = new ArrayList<>();
		boolean lastLubricationPlural = false;
		
		for(LubricationType lt : wetPenetrationTypes.get(penetrator1).get(penetrationType1)) {
			if(!wetPenetrationTypes.get(penetrator2).get(penetrationType2).contains(lt)) {
				wetPenetrationTypes.get(penetrator2).get(penetrationType2).add(lt);
				lubricationTransferred.add(lt.getName());
				lastLubricationPlural = lt.isPlural();
			}
		}
		
		if(!lubricationTransferred.isEmpty()) {
			sexSB.append(formatCoverableAreaGettingWet(
					Util.capitaliseSentence(Util.stringsToStringList(lubricationTransferred, false))+" quickly lubricate"+(lastLubricationPlural?" ":"s ")
						+(penetrator2.isPlayer()?"your ":penetrator2.getName("the")+"'s ")+penetrationType2.getName(penetrator2)+"."));
		}
		
		lubricationTransferred.clear();
		
		for(LubricationType lt : wetPenetrationTypes.get(penetrator2).get(penetrationType2)) {
			if(!wetPenetrationTypes.get(penetrator1).get(penetrationType1).contains(lt)) {
				wetPenetrationTypes.get(penetrator1).get(penetrationType1).add(lt);
				lubricationTransferred.add(lt.getName());
				lastLubricationPlural = lt.isPlural();
			}
		}
		
		if(!lubricationTransferred.isEmpty()) {
			sexSB.append(formatCoverableAreaGettingWet(
					Util.capitaliseSentence(Util.stringsToStringList(lubricationTransferred, false))+" quickly lubricate"+(lastLubricationPlural?" ":"s ")
						+(penetrator1.isPlayer()?"your ":penetrator1.getName("the")+"'s ")+penetrationType1.getName(penetrator1)+"."));
		}
	}
	
	private static String getLubricationDescription(GameCharacter character, OrificeType orifice) {
		if(wetOrificeTypes.get(character).get(orifice).isEmpty()) {
			return "";
		}
		StringBuilder description = new StringBuilder((character.isPlayer() ?"Your " :"[npc.Name]'s ")+orifice.getName(character) +" "+(orifice.isPlural()?"are":"is")+" lubricated with ");
		List<String> lubes = new ArrayList<>();
		for(LubricationType lube : wetOrificeTypes.get(character).get(orifice)) {
			lubes.add(lube.getName());
		}
		description.append(Util.stringsToStringList(lubes, false)+".");
		return description.toString();
	}
	
	private static String getLubricationDescription(GameCharacter character, PenetrationType penetration) {
		if(wetPenetrationTypes.get(character).get(penetration).isEmpty()) {
			return "";
		}
		StringBuilder description = new StringBuilder(
				(character.isPlayer()?"Your ":"[npc.Name]'s ") + penetration.getName(character) +" "+(penetration.isPlural()?"are":"is")+" lubricated with ");
		List<String> lubes = new ArrayList<>();
		for(LubricationType lube : wetPenetrationTypes.get(character).get(penetration)) {
			lubes.add(lube.getName());
		}
		description.append(Util.stringsToStringList(lubes, false)+".");
		return description.toString();
	}
	

	public static void addOrificeLubrication(GameCharacter character, OrificeType orifice, LubricationType lubrication) {
		addOrificeLubrication(character, orifice, lubrication, true);
	}
	
	public static void addOrificeLubrication(GameCharacter character, OrificeType orifice, LubricationType lubrication, boolean appendTextToSex) {
		boolean appendDescription =
				!(orifice==OrificeType.MOUTH && lubrication==LubricationType.PLAYER_SALIVA) // Don't give descriptions of saliva lubricating your own mouth.
				&& !(orifice==OrificeType.MOUTH && lubrication==LubricationType.PARTNER_SALIVA)
				&& (orifice==OrificeType.VAGINA?activePartner.isCoverableAreaExposed(CoverableArea.VAGINA):true)
				&& (orifice==OrificeType.ANUS?activePartner.isCoverableAreaExposed(CoverableArea.ANUS):true)
				&& (orifice==OrificeType.NIPPLE?activePartner.isCoverableAreaExposed(CoverableArea.NIPPLES):true);
		
		if(wetOrificeTypes.get(character).get(orifice).add(lubrication)){
			if(appendDescription && appendTextToSex) {
				sexSB.append(formatCoverableAreaGettingWet((character.isPlayer()?"Your ":"[npc.Name]'s ")+orifice.getName(character)+" "+(orifice.isPlural()?"are":"is")+" quickly lubricated by "+lubrication.getName()+"."));
			}
		}
	}

	public static void addPenetrationTypeLubrication(GameCharacter character, PenetrationType penetrationType, LubricationType lubrication) {
		addPenetrationTypeLubrication(character, penetrationType, lubrication, true);
	}
	
	public static void addPenetrationTypeLubrication(GameCharacter character, PenetrationType penetrationType, LubricationType lubrication, boolean appendTextToSex) {
		boolean appendDescription =
				!(penetrationType==PenetrationType.TONGUE && (lubrication==LubricationType.PLAYER_SALIVA || lubrication==LubricationType.PARTNER_SALIVA)) // Don't give descriptions of saliva lubricating your own tongue.
				&& (penetrationType==PenetrationType.PENIS ? (!character.isPlayer() ? activePartner.isCoverableAreaExposed(CoverableArea.PENIS):true) :true);
		
		if((penetrationType == PenetrationType.PENIS
				&& (lubrication == LubricationType.PLAYER_PRECUM || lubrication == LubricationType.PLAYER_CUM || lubrication == LubricationType.PARTNER_PRECUM || lubrication == LubricationType.PARTNER_CUM)
					? !character.isWearingCondom()
					: true)) { // Can't lubricate if covered by condom
			if(wetPenetrationTypes.get(character).get(penetrationType).add(lubrication)){
				if(appendDescription && appendTextToSex) {
					sexSB.append(formatCoverableAreaGettingWet((character.isPlayer()?"Your ":"[npc.Name]'s ")+penetrationType.getName(character)+" "+(penetrationType.isPlural()?"are":"is")+" quickly lubricated by "+lubrication.getName()+"."));
				}
			}
		}
	}


	private static Map<GameCharacter, Set<OrificeType>> initialPenetrations = new HashMap<>();
	
	public static void applyPenetration(GameCharacter characterPenetrating, GameCharacter characterPenetrated, PenetrationType penetration, OrificeType orifice) {
		
		SexType relatedSexTypePenetrator = new SexType(SexParticipantType.PITCHER, penetration, orifice);
		SexType relatedSexTypePenetrated = new SexType(SexParticipantType.CATCHER, penetration, orifice);
		
		// Free up orifice and penetrator:
		removePenetration(characterPenetrating, characterPenetrated, penetration, orifice);
		
		ongoingPenetrationMap.get(characterPenetrating).get(characterPenetrated).putIfAbsent(penetration, new HashSet<>());
		ongoingPenetrationMap.get(characterPenetrating).get(characterPenetrated).get(penetration).add(orifice);
		
		initialPenetrations.putIfAbsent(characterPenetrated, new HashSet<>());
		initialPenetrations.get(characterPenetrated).add(orifice);
		
		if(characterPenetrated != null && characterPenetrating != null) {
			characterPenetrated.incrementSexCount(relatedSexTypePenetrated);
			characterPenetrating.incrementSexCount(relatedSexTypePenetrator);
			
			characterPenetrating.addSexPartner(characterPenetrated, relatedSexTypePenetrator);
			characterPenetrated.addSexPartner(characterPenetrating, relatedSexTypePenetrated);
			
		} else {
			System.err.println("Warning! Sex.applyPenetration() is finding 'characterPenetrated' or 'characterPenetrating' to be null!!!");
		}
	}

	public static void removePenetration(GameCharacter characterPenetrating, GameCharacter characterPenetrated, PenetrationType penetrationType, OrificeType orifice) {
		removePenetration(characterPenetrating, characterPenetrated, true, penetrationType, orifice);
	}
	
	public static void removePenetration(GameCharacter characterPenetrating, GameCharacter characterPenetrated, boolean appendRemovalText, PenetrationType penetrationType, OrificeType orifice) {
		if(ongoingPenetrationMap.get(characterPenetrating).get(characterPenetrated).containsKey(penetrationType)) {
			if(ongoingPenetrationMap.get(characterPenetrating).get(characterPenetrated).get(penetrationType).remove(orifice)) {
				if(appendRemovalText) {
					sexSB.append(formatStopPenetration(activePartner.getStopPenetrationDescription(characterPenetrating, penetrationType, characterPenetrated, orifice)));
				}
			}
			
			if(ongoingPenetrationMap.get(characterPenetrating).get(characterPenetrated).get(penetrationType).isEmpty()) {
				ongoingPenetrationMap.get(characterPenetrating).get(characterPenetrated).remove(penetrationType);
			}
		}
		
	}

	private static void applyPenetrationEffects(GameCharacter characterPenetrating, GameCharacter characterPenetrated, PenetrationType penetrationType, OrificeType orifice) { //TODO formatting

		SexType relatedSexTypeForCharacterPenetrating = new SexType(SexParticipantType.PITCHER, penetrationType, orifice);
		SexType relatedSexTypeForCharacterPenetrated = new SexType(SexParticipantType.CATCHER, penetrationType, orifice);
		
		String penileVirginityLoss = "";
		
		if (penetrationType == PenetrationType.PENIS) {
			if(characterPenetrating.isPenisVirgin()
					&& orifice.isTakesPenisVirginity()) {
				penileVirginityLoss = characterPenetrating.getVirginityLossPenetrationDescription(characterPenetrating, PenetrationType.PENIS, characterPenetrated, orifice);
				if(characterPenetrated.hasFetish(Fetish.FETISH_DEFLOWERING)) {
					characterPenetrated.incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(characterPenetrated), true);
				}
				characterPenetrating.setVirginityLoss(relatedSexTypeForCharacterPenetrating, characterPenetrated.getName("a") + " " + characterPenetrated.getLostVirginityDescriptor());
				characterPenetrating.setPenisVirgin(false);
			}
			
		}
		
		if (orifice == OrificeType.ANUS) {
			if (initialPenetrations.get(characterPenetrated).contains(OrificeType.ANUS)) {
				sexSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, orifice)));
				
				if (characterPenetrated.isAssVirgin()) {
					if (penetrationType.isTakesVirginity()) {
						sexSB.append(characterPenetrating.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, characterPenetrated, OrificeType.ANUS));
						if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
							characterPenetrating.incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating), true);
						}
						characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating.getName("a") + " " + characterPenetrating.getLostVirginityDescriptor());
						characterPenetrated.setAssVirgin(false);
					}
				}
				initialPenetrations.get(characterPenetrated).remove(OrificeType.ANUS);
			} else {
				sexSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, orifice)));
			}
				
		}  else if (orifice == OrificeType.VAGINA) {
			if (initialPenetrations.get(characterPenetrated).contains(OrificeType.VAGINA)) {
				sexSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, orifice)));
				
				if (characterPenetrated.isVaginaVirgin()) {
					if (penetrationType.isTakesVirginity()) {
						sexSB.append(characterPenetrating.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, characterPenetrated, OrificeType.VAGINA));
						if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
							characterPenetrating.incrementExperience(Fetish.getExperienceGainFromTakingVaginalVirginity(characterPenetrating), true);
						}
						characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating.getName("a") + " " + characterPenetrating.getLostVirginityDescriptor());
						characterPenetrated.setVaginaVirgin(false);
					}
				}
				initialPenetrations.get(characterPenetrated).remove(OrificeType.VAGINA);
			} else {
				sexSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, orifice)));
			}
			
		} else if (orifice == OrificeType.NIPPLE) {
			if (initialPenetrations.get(characterPenetrated).contains(OrificeType.NIPPLE)) {
					sexSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, orifice)));
					
					if (characterPenetrated.isNippleVirgin()) {
						if (penetrationType.isTakesVirginity()) {
							sexSB.append(characterPenetrating.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, characterPenetrated, OrificeType.NIPPLE));
							if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
								characterPenetrating.incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating), true);
							}
							characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating.getName("a") + " " + characterPenetrating.getLostVirginityDescriptor());
							characterPenetrated.setNippleVirgin(false);
						}
					}
					initialPenetrations.get(characterPenetrated).remove(OrificeType.NIPPLE);
				} else {
					sexSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, orifice)));
				}
				
		} else if (orifice == OrificeType.URETHRA_PENIS) {
			if (initialPenetrations.get(characterPenetrated).contains(OrificeType.URETHRA_PENIS)) {
					sexSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, orifice)));
					
					if (characterPenetrated.isUrethraVirgin()) {
						if (penetrationType.isTakesVirginity()) {
							sexSB.append(characterPenetrating.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, characterPenetrated, OrificeType.URETHRA_PENIS));
							if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
								characterPenetrating.incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating), true);
							}
							characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating.getName("a") + " " + characterPenetrating.getLostVirginityDescriptor());
							characterPenetrated.setUrethraVirgin(false);
						}
					}
					initialPenetrations.get(characterPenetrated).remove(OrificeType.URETHRA_PENIS);
				} else {
					sexSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, orifice)));
				}
			
		} else if (orifice == OrificeType.URETHRA_VAGINA) {
			if (initialPenetrations.get(characterPenetrated).contains(OrificeType.URETHRA_VAGINA)) {
				sexSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, orifice)));
				
				if (characterPenetrated.isVaginaUrethraVirgin()) {
					if (penetrationType.isTakesVirginity()) {
						sexSB.append(characterPenetrating.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, characterPenetrated, OrificeType.URETHRA_VAGINA));
						if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
							characterPenetrating.incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating), true);
						}
						characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating.getName("a") + " " + characterPenetrating.getLostVirginityDescriptor());
						characterPenetrated.setVaginaUrethraVirgin(false);
					}
				}
				initialPenetrations.get(characterPenetrated).remove(OrificeType.URETHRA_VAGINA);
			} else {
				sexSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, orifice)));
			}
		
		} else if (orifice == OrificeType.MOUTH) {
			if (initialPenetrations.get(characterPenetrated).contains(OrificeType.MOUTH)) {
					sexSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, orifice)));
					
					if (characterPenetrated.isFaceVirgin()) {
						if (penetrationType.isTakesVirginity()) {
							sexSB.append(characterPenetrating.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, characterPenetrated, OrificeType.MOUTH));
							if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
								characterPenetrating.incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating), true);
							}
							characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating.getName("a") + " " + characterPenetrating.getLostVirginityDescriptor());
							characterPenetrated.setFaceVirgin(false);
						}
					}
					initialPenetrations.get(characterPenetrated).remove(OrificeType.MOUTH);
				} else {
					sexSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, orifice)));
				}
				
		}
		
		sexSB.append(penileVirginityLoss);

		// TODO apply masochism effects to stretching:

		// Stretching effects (will only stretch from penises):
		if (penetrationType == PenetrationType.PENIS) {
			
			boolean lubed = !wetOrificeTypes.get(characterPenetrated).get(orifice).isEmpty();
			boolean twoPenisesInOrifice = false;

			areasCurrentlyStretching.get(characterPenetrated).clear();
			if (orifice == OrificeType.ANUS){
				if (Capacity.isPenisSizeTooBig((int)characterPenetrated.getAssStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
					sexSB.append(characterPenetrated.getStretchingDescription(characterPenetrating, penetrationType, OrificeType.ANUS));

					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementAssStretchedCapacity((((float)characterPenetrating.getPenisRawSizeValue())-characterPenetrated.getAssStretchedCapacity())*characterPenetrated.getAssElasticity().getStretchModifier());
					if(characterPenetrated.getAssStretchedCapacity()>characterPenetrating.getPenisRawSizeValue())
						characterPenetrated.setAssStretchedCapacity(characterPenetrating.getPenisRawSizeValue());

					areasCurrentlyStretching.get(characterPenetrated).add(OrificeType.ANUS);

					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenisSizeTooBig((int)characterPenetrated.getAssStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
						sexSB.append(characterPenetrated.getStretchingFinishedDescription(OrificeType.ANUS));
						areasCurrentlyStretching.get(characterPenetrated).remove(OrificeType.ANUS);
					}

					areasStretched.get(characterPenetrated).add(OrificeType.ANUS);

				} else if(Capacity.isPenisSizeTooSmall((int)characterPenetrated.getAssStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
					sexSB.append(characterPenetrated.getTooLooseDescription(OrificeType.ANUS));
					areasTooLoose.get(characterPenetrated).add(OrificeType.ANUS);
				}

			} else if (orifice == OrificeType.VAGINA){
				if (Capacity.isPenisSizeTooBig((int)characterPenetrated.getVaginaStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
					sexSB.append(characterPenetrated.getStretchingDescription(characterPenetrating, penetrationType, OrificeType.VAGINA));
					
					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementVaginaStretchedCapacity((((float)characterPenetrating.getPenisRawSizeValue())-characterPenetrated.getVaginaStretchedCapacity())*characterPenetrated.getVaginaElasticity().getStretchModifier());
					if(characterPenetrated.getVaginaStretchedCapacity()>characterPenetrating.getPenisRawSizeValue()) {
						characterPenetrated.setVaginaStretchedCapacity(characterPenetrating.getPenisRawSizeValue());
					}
					
					areasCurrentlyStretching.get(characterPenetrated).add(OrificeType.VAGINA);
					
					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenisSizeTooBig((int)characterPenetrated.getVaginaStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
						sexSB.append(characterPenetrated.getStretchingFinishedDescription(OrificeType.VAGINA));
						areasCurrentlyStretching.get(characterPenetrated).remove(OrificeType.VAGINA);
					}

					areasStretched.get(characterPenetrated).add(OrificeType.VAGINA);

				} else if(Capacity.isPenisSizeTooSmall((int)characterPenetrated.getVaginaStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
					sexSB.append(characterPenetrated.getTooLooseDescription(OrificeType.VAGINA));
					areasTooLoose.get(characterPenetrated).add(OrificeType.VAGINA);
				}

			}else if (orifice == OrificeType.NIPPLE){
				if (Capacity.isPenisSizeTooBig((int)characterPenetrated.getNippleStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
					sexSB.append(characterPenetrated.getStretchingDescription(characterPenetrating, penetrationType, OrificeType.NIPPLE));

					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementNippleStretchedCapacity((((float)characterPenetrating.getPenisRawSizeValue())-characterPenetrated.getNippleStretchedCapacity())*characterPenetrated.getNippleElasticity().getStretchModifier());
					if(characterPenetrated.getNippleStretchedCapacity()>characterPenetrating.getPenisRawSizeValue())
						characterPenetrated.setNippleStretchedCapacity(characterPenetrating.getPenisRawSizeValue());

					areasCurrentlyStretching.get(characterPenetrated).add(OrificeType.NIPPLE);

					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenisSizeTooBig((int)characterPenetrated.getNippleStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
						sexSB.append(characterPenetrated.getStretchingFinishedDescription(OrificeType.NIPPLE));
						areasCurrentlyStretching.get(characterPenetrated).remove(OrificeType.NIPPLE);
					}

					areasStretched.get(characterPenetrated).add(OrificeType.NIPPLE);

				}else if(Capacity.isPenisSizeTooSmall((int)characterPenetrated.getNippleStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
					sexSB.append(characterPenetrated.getTooLooseDescription(OrificeType.NIPPLE));
					areasTooLoose.get(characterPenetrated).add(OrificeType.NIPPLE);
				}

			} else if (orifice == OrificeType.URETHRA_PENIS){
				if (Capacity.isPenisSizeTooBig((int)characterPenetrated.getPenisStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
					sexSB.append(characterPenetrated.getStretchingDescription(characterPenetrating, penetrationType, OrificeType.URETHRA_PENIS));

					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementPenisStretchedCapacity((((float)characterPenetrating.getPenisRawSizeValue())-characterPenetrated.getPenisStretchedCapacity())*characterPenetrated.getUrethraElasticity().getStretchModifier());
					if(characterPenetrated.getPenisStretchedCapacity()>characterPenetrating.getPenisRawSizeValue())
						characterPenetrated.setPenisStretchedCapacity(characterPenetrating.getPenisRawSizeValue());

					areasCurrentlyStretching.get(characterPenetrated).add(OrificeType.URETHRA_PENIS);

					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenisSizeTooBig((int)characterPenetrated.getPenisStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
						sexSB.append(characterPenetrated.getStretchingFinishedDescription(OrificeType.URETHRA_PENIS));
						areasCurrentlyStretching.get(characterPenetrated).remove(OrificeType.URETHRA_PENIS);
					}

					areasStretched.get(characterPenetrated).add(OrificeType.URETHRA_PENIS);

				}else if(Capacity.isPenisSizeTooSmall((int)characterPenetrated.getPenisStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
					sexSB.append(characterPenetrated.getTooLooseDescription(OrificeType.URETHRA_PENIS));
					areasTooLoose.get(characterPenetrated).add(OrificeType.URETHRA_PENIS);
				}

			} else if (orifice == OrificeType.URETHRA_VAGINA){
				if (Capacity.isPenisSizeTooBig((int)characterPenetrated.getVaginaUrethraStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
					sexSB.append(characterPenetrated.getStretchingDescription(characterPenetrating, penetrationType, OrificeType.URETHRA_VAGINA));

					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementVaginaUrethraStretchedCapacity((((float)characterPenetrating.getPenisRawSizeValue())-characterPenetrated.getVaginaUrethraStretchedCapacity())*characterPenetrated.getVaginaUrethraElasticity().getStretchModifier());
					if(characterPenetrated.getVaginaUrethraStretchedCapacity()>characterPenetrating.getPenisRawSizeValue())
						characterPenetrated.setVaginaUrethraStretchedCapacity(characterPenetrating.getPenisRawSizeValue());

					areasCurrentlyStretching.get(characterPenetrated).add(OrificeType.URETHRA_VAGINA);

					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenisSizeTooBig((int)characterPenetrated.getVaginaUrethraStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
						sexSB.append(characterPenetrated.getStretchingFinishedDescription(OrificeType.URETHRA_VAGINA));
						areasCurrentlyStretching.get(characterPenetrated).remove(OrificeType.URETHRA_VAGINA);
					}

					areasStretched.get(characterPenetrated).add(OrificeType.URETHRA_VAGINA);

				}else if(Capacity.isPenisSizeTooSmall((int)characterPenetrated.getVaginaUrethraStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
					sexSB.append(characterPenetrated.getTooLooseDescription(OrificeType.URETHRA_VAGINA));
					areasTooLoose.get(characterPenetrated).add(OrificeType.URETHRA_VAGINA);
				}

			} else if (orifice == OrificeType.MOUTH){
				if (Capacity.isPenisSizeTooBig((int)characterPenetrated.getFaceStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
					sexSB.append(characterPenetrated.getStretchingDescription(characterPenetrating, penetrationType, OrificeType.MOUTH));

					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementFaceStretchedCapacity((((float)characterPenetrating.getPenisRawSizeValue())-characterPenetrated.getFaceStretchedCapacity())*characterPenetrated.getFaceElasticity().getStretchModifier());
					if(characterPenetrated.getFaceStretchedCapacity()>characterPenetrating.getPenisRawSizeValue())
						characterPenetrated.setFaceStretchedCapacity(characterPenetrating.getPenisRawSizeValue());

					areasCurrentlyStretching.get(characterPenetrated).add(OrificeType.MOUTH);

					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenisSizeTooBig((int)characterPenetrated.getFaceStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
						sexSB.append(characterPenetrated.getStretchingFinishedDescription(OrificeType.MOUTH));
						areasCurrentlyStretching.get(characterPenetrated).remove(OrificeType.MOUTH);
					}

					areasStretched.get(characterPenetrated).add(OrificeType.MOUTH);

				}else if(Capacity.isPenisSizeTooSmall((int)characterPenetrated.getFaceStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
					sexSB.append(characterPenetrated.getTooLooseDescription(OrificeType.MOUTH));
				}
			}
		}
	}

	/**
	 * Removes a piece of clothing.
	 *
	 * @return SexActionUtility.CLOTHING_REMOVAL
	 */
	public static SexActionInterface partnerManageClothingToAccessCoverableArea(boolean playerClothing, CoverableArea coverableArea) {
		
		if (playerClothing) {
			SimpleEntry<AbstractClothing, DisplacementType> clothingRemoval = Main.game.getPlayer().getNextClothingToRemoveForCoverableAreaAccess(coverableArea);
			if (clothingRemoval.getKey() == null) {
				unequipClothingText = "[npc.Name] can't find a piece of clothing to remove! (Please tell Innoxia. :3)";
				System.err.println("partnerManageClothingToAccessCoverableArea method can't get clothing! 1");
				return SexActionUtility.CLOTHING_REMOVAL;
			}
			
			clothingBeingRemoved = clothingRemoval.getKey();

			if (clothingRemoval.getValue() == DisplacementType.REMOVE_OR_EQUIP) {// || player().isAbleToUnequip(clothingRemoval.getKey(), false, partner)) {
				player().unequipClothingOntoFloor(clothingBeingRemoved, false, getActivePartner());
				unequipClothingText = Main.game.getPlayer().getUnequipDescription();

			} else {
				player().isAbleToBeDisplaced(clothingBeingRemoved, clothingRemoval.getValue(), true, false, getActivePartner());
				unequipClothingText = Main.game.getPlayer().getDisplaceDescription();
			}

		} else {
			SimpleEntry<AbstractClothing, DisplacementType> clothingRemoval = activePartner.getNextClothingToRemoveForCoverableAreaAccess(coverableArea);
			if (clothingRemoval.getKey() == null) {
				unequipClothingText = "[npc.Name] can't find a piece of clothing to remove! (Please tell Innoxia. :3)";
				System.err.println("partnerManageClothingToAccessCoverableArea method can't get clothing! 2");
				return SexActionUtility.CLOTHING_REMOVAL;
			}

			clothingBeingRemoved = clothingRemoval.getKey();

			if (clothingRemoval.getValue() == DisplacementType.REMOVE_OR_EQUIP) {//  || partner.isAbleToUnequip(clothingRemoval.getKey(), false, partner)) {
				activePartner.unequipClothingOntoFloor(clothingBeingRemoved, false, getActivePartner());
				unequipClothingText = activePartner.getUnequipDescription();

			} else {
				activePartner.isAbleToBeDisplaced(clothingBeingRemoved, clothingRemoval.getValue(), true, false, getActivePartner());
				unequipClothingText = activePartner.getDisplaceDescription();
			}
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
	
	/**
	 * @param targeter The character whose target is to be found.
	 * @return The character that the 'targeter' is currently focusing on.
	 */
	public static GameCharacter getTargetedPartner(GameCharacter targeter) {
		if(targeter.isPlayer()) {
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
	
	public static int getTotalParticipantCount() {
		return dominants.size()+submissives.size();
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
	
	public static boolean isAnyPenetrationHappening() {
		for(GameCharacter character : Sex.getAllParticipants()) {
			if(isCharacterPenetrated(character)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isCharacterPenetrated(GameCharacter character) {
		if(!Sex.getAllParticipants().contains(character)) {
			System.err.println("isCharacterPenetrated("+character.getId()+"): This character is not in Sex!"); 
			return false;
		}
		
		for(GameCharacter penetrator : Sex.getAllParticipants()) {
			for(Entry<PenetrationType, Set<OrificeType>> entry : ongoingPenetrationMap.get(penetrator).get(character).entrySet()) {
				if(!entry.getValue().isEmpty()) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isAnyNonSelfPenetrationHappening() {
		for(GameCharacter penetrator : Sex.getAllParticipants()) {
			for(GameCharacter penetrated : Sex.getAllParticipants()) {
				if(!penetrator.equals(penetrated)) {
					for(Entry<PenetrationType, Set<OrificeType>> entry : ongoingPenetrationMap.get(penetrator).get(penetrated).entrySet()) {
						if(!entry.getValue().isEmpty()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean isCharacterSelfPenetrationHappening(GameCharacter character) {
		for(Entry<PenetrationType, Set<OrificeType>> entry : ongoingPenetrationMap.get(character).get(character).entrySet()) {
			if(!entry.getValue().isEmpty()) {
				return true;
			}
		}
		return false;
	}


	// Free area convenience methods:

	public static boolean isOrificeFree(GameCharacter character, OrificeType orifice) {
		return getPenetrationTypeInOrifice(character, orifice)==null;
	}
	
	public static boolean isOrificeNonSelfPenetration(GameCharacter characterOrifice, OrificeType orifice) {
		for(GameCharacter penetrator : Sex.allParticipants) {
			if(!penetrator.equals(characterOrifice)) {
				for(Entry<PenetrationType, Set<OrificeType>> entry : ongoingPenetrationMap.get(penetrator).get(characterOrifice).entrySet()) {
					if(!entry.getValue().isEmpty()) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	public static boolean isPenetrationTypeFree(GameCharacter penetrator, PenetrationType penetrationType) {
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
		}
		
		for(GameCharacter target : Sex.allParticipants) {
			if(ongoingPenetrationMap.get(penetrator).get(target).containsKey(penetrationType)) {
				if(ongoingPenetrationMap.get(penetrator).get(target).get(penetrationType).size()>=penetrationTypesAvailable) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean isPenetrationTypeNonSelfPenetration(GameCharacter penetrator, PenetrationType penetrationType) {
		for(GameCharacter target : Sex.allParticipants) {
			if(!penetrator.equals(target)) {
				for(Entry<PenetrationType, Set<OrificeType>> entry : ongoingPenetrationMap.get(penetrator).get(target).entrySet()) {
					if(!entry.getValue().isEmpty()) {
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
	
	
	public static Map<GameCharacter, Map<PenetrationType, Set<OrificeType>>> getOngoingPenetrationMap(GameCharacter characterPerformingPenetration) {
		return ongoingPenetrationMap.get(characterPerformingPenetration);
	}
	
	/**
	 * @param characterOrifice The character who is being penetrated.
	 * @param orifice The orifice type.
	 * @return The PenetrationType which is currently penetrating this orifice. Returns null if  'characterOrifice''s 'orifice' is not currently being penetrated.
	 */
	public static PenetrationType getPenetrationTypeInOrifice(GameCharacter characterOrifice, OrificeType orifice) {
		for(GameCharacter penetrator : Sex.allParticipants) {
			for(Entry<PenetrationType, Set<OrificeType>> entry : ongoingPenetrationMap.get(penetrator).get(characterOrifice).entrySet()) {
				for(OrificeType ot : entry.getValue()) {
					if(ot==orifice) {
						return entry.getKey();
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * @param characterOrifice The character who is being penetrated.
	 * @param orifice The orifice type.
	 * @return The character who is currently penetrating this orifice. Returns null if no character is penetrating 'characterOrifice''s 'orifice'.
	 */
	public static GameCharacter getPenetratingCharacterUsingOrifice(GameCharacter characterOrifice, OrificeType orifice) {
		for(GameCharacter penetrator : Sex.allParticipants) {
			for(Entry<PenetrationType, Set<OrificeType>> entry : ongoingPenetrationMap.get(penetrator).get(characterOrifice).entrySet()) {
				for(OrificeType ot : entry.getValue()) {
					if(ot==orifice) {
						return penetrator;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Returns a list, not a single instance of GameCharacter, as the 'characterPenetrating' could be penetrating multiple characters with tentacles, tails, hands, etc.
	 * @param characterPenetrating The character who is doing the penetrating.
	 * @param penetration The penetration type.
	 * @param characterPenetrated The character who is being penetrated.
	 * @return A set of orifices, belonging to 'characterPenetrated', that are currently being penetrated by 'characterPenetrating', using 'penetration'.
	 */
	public static List<OrificeType> getOrificesBeingPenetratedBy(GameCharacter characterPenetrating, PenetrationType penetration, GameCharacter characterPenetrated) {
		if(ongoingPenetrationMap.get(characterPenetrating).get(characterPenetrated).containsKey(penetration)) {
			return new ArrayList<>(ongoingPenetrationMap.get(characterPenetrating).get(characterPenetrated).get(penetration));
		} else {
			return new ArrayList<>();
		}
	}
	
	/**
	 * Returns a list, not a single instance of GameCharacter, as the 'characterPenetrating' could be penetrating multiple characters with tentacles, tails, hands, etc.
	 * @param characterPenetrating The character who is doing the penetrating.
	 * @param penetration The penetration type.
	 * @return A list of characters that are currently being penetrated by 'characterPenetrating', using 'penetration'.
	 */
	public static List<GameCharacter> getCharactersBeingPenetratedBy(GameCharacter characterPenetrating, PenetrationType penetration) {
		List<GameCharacter> charactersPenetrated = new ArrayList<>();
		
		for(GameCharacter penetrated : Sex.allParticipants) {
			for(Entry<PenetrationType, Set<OrificeType>> entry : ongoingPenetrationMap.get(characterPenetrating).get(penetrated).entrySet()) {
				if(penetration == entry.getKey()) {
					charactersPenetrated.add(penetrated);
				}
			}
		}
		
		return charactersPenetrated;
	}
	
	public static Map<OrificeType, Set<LubricationType>> getWetOrificeTypes(GameCharacter character) {
		return wetOrificeTypes.get(character);
	}
	
	public static Map<PenetrationType, Set<LubricationType>> getWetPenetrationTypes(GameCharacter character) {
		return wetPenetrationTypes.get(character);
	}

	public static void clearPlayerPenetrationRequests() {
		penetrationRequestsPlayer.clear();
	}

	public static void addPlayerPenetrationRequest(OrificeType orifice) {
		penetrationRequestsPlayer.add(orifice);
	}

	public static void removePlayerPenetrationRequest(OrificeType orifice) {
		penetrationRequestsPlayer.remove(orifice);
	}

	public static Set<OrificeType> getPlayerPenetrationRequests() {
		return penetrationRequestsPlayer;
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

	public static void setSexManager(SexManagerInterface sexManager) {
		Sex.resetAllPenetrations();
		
		Sex.allParticipants = new ArrayList<>(sexManager.getDominants().keySet());
		Sex.allParticipants.addAll(sexManager.getSubmissives().keySet());
		
		actionsAvailable.clear();
		orgasmActionsAvailable.clear();
		for(GameCharacter character : Sex.allParticipants) {
			actionsAvailable.put(character, new HashMap<>());
			orgasmActionsAvailable.put(character, new HashMap<>());
			
			for(GameCharacter target : Sex.allParticipants) {
				if(!character.equals(target)) {
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
			}
		}
		
		Sex.sexManager = sexManager;
		
		updateAvailableActions();
		
		sexSB.append(
				"<p style='text-align:center;'><b>New position:</b> <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>"+Sex.sexManager.getPosition().getName()+"</b></br>"
				+"<i><b>"+Sex.sexManager.getPosition().getDescription()+"</b></i></p>");
	}
	
	public static void resetAllPenetrations() {
		if(Sex.ongoingPenetrationMap!=null) {
			ongoingPenetrationMap.clear();
			for(GameCharacter characterPenetrating : Sex.getAllParticipants()) {
				ongoingPenetrationMap.put(characterPenetrating, new HashMap<>());
				for(GameCharacter characterPenetrated : Sex.getAllParticipants()) {
					ongoingPenetrationMap.get(characterPenetrating).put(characterPenetrated, new HashMap<>());
				}
			}
		}
	}
	
	private static void updateAvailableActions() {

		for(GameCharacter character : Sex.allParticipants) {
			for(GameCharacter target : Sex.allParticipants) {
				if(!character.equals(target)) {
					actionsAvailable.get(character).get(target).clear();
					orgasmActionsAvailable.get(character).get(target).clear();
				}
			}
		}
					
		for(GameCharacter character : Sex.allParticipants) {
			for(GameCharacter target : Sex.allParticipants) {
				if(!character.equals(target)) {
					SexActionPresetPair pair = Sex.sexManager.getPosition().getSlotTargets().get(Sex.getSexPositionSlot(character)).get(Sex.getSexPositionSlot(target));
					if(character.isPlayer()) {
						addActionsFromContainingClasses(character, target, pair.getPlayerSexActionContainingClasses());
					} else {
						addActionsFromContainingClasses(character, target, pair.getPartnerSexActionContainingClasses());
					}
				}
			}
		}

		for(GameCharacter character : Sex.allParticipants) {
			if(character instanceof NPC) {
				for(Class<?> sexClass : ((NPC)character).getUniqueSexClasses()) {
					Sex.addSexActionClass(character, Sex.getTargetedPartner(character), sexClass);
				}
			}
		}
	}
	
	private static void addActionsFromContainingClasses(GameCharacter character, GameCharacter target, List<Class<?>> sexActionContainingClasses) {
		try {
			if (!sexActionContainingClasses.isEmpty()) {
				for(Class<?> container : sexActionContainingClasses) {
					addSexActionClass(character, target, container);
				}
			}
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
	private static void addSexActionClass(GameCharacter character, GameCharacter target, Class<?> classToAddSexActionsFrom) {
		try {
			if(classToAddSexActionsFrom!=null) {
				Field[] fields = classToAddSexActionsFrom.getFields();
				
				for(Field f : fields){
					if (SexAction.class.isAssignableFrom(f.getType())) {
						if (((SexAction) f.get(null)).getActionType().isOrgasmOption()) {
							if(character.isPlayer()) {
								if(((SexAction) f.get(null)).getActionType().isPlayerAction()) {
									orgasmActionsAvailable.get(character).get(target).add(((SexAction) f.get(null)));
								} else {
									orgasmActionsAvailable.get(target).get(character).add(((SexAction) f.get(null)));
								}
							} else {
								if(!((SexAction) f.get(null)).getActionType().isPlayerAction()) {
									orgasmActionsAvailable.get(character).get(target).add(((SexAction) f.get(null)));
								} else {
									orgasmActionsAvailable.get(Main.game.getPlayer()).get(character).add(((SexAction) f.get(null)));
								}
							}
						} else {
							if(character.isPlayer()) {
								if(((SexAction) f.get(null)).getActionType().isPlayerAction()) {
									actionsAvailable.get(character).get(target).add(((SexAction) f.get(null)));
								} else {
									actionsAvailable.get(target).get(character).add(((SexAction) f.get(null)));
								}
							} else {
								if(!((SexAction) f.get(null)).getActionType().isPlayerAction()) {
									actionsAvailable.get(character).get(target).add(((SexAction) f.get(null)));
								} else {
									actionsAvailable.get(Main.game.getPlayer()).get(character).add(((SexAction) f.get(null)));
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
		if(forceSexPaceMap.containsKey(character)) {
			return forceSexPaceMap.get(character);
		}
		return LustLevel.getLustLevelFromValue(character.getLust()).getSexPace(Sex.isConsensual(), character);
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
		
		Sex.resetAllPenetrations();
		
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

	public static Set<OrificeType> getAreasCurrentlyStretching(GameCharacter character) {
		return areasCurrentlyStretching.get(character);
	}

	public static Set<OrificeType> getAreasTooLoose(GameCharacter character) {
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
	
	private static List<Fetish> getFetishesFromPenetrationAndOrificeTypes(GameCharacter character, GameCharacter characterPenetrating, PenetrationType penetrationType, GameCharacter characterOrifice, OrificeType orificeBeingUsed) {
		List<Fetish> associatedFetishes = new ArrayList<>();
		
		if(Sex.getSexManager().isPublicSex()) {
			associatedFetishes.add(Fetish.FETISH_EXHIBITIONIST);
		}
		
		switch(penetrationType) {
			case FINGER:
				if(character.equals(characterOrifice)) {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_RECEIVING);
							break;
						case BREAST:
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							break;
						case MOUTH:
							associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							break;
						case NIPPLE:
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							break;
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							associatedFetishes.add(Fetish.FETISH_MASTURBATION);
							break;
						case VAGINA:
							associatedFetishes.add(Fetish.FETISH_MASTURBATION);
							associatedFetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
							break;
						case THIGHS:
							associatedFetishes.add(Fetish.FETISH_STRUTTER);
							break;
					}
				} else {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_GIVING);
							break;
						case BREAST:
							associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							break;
						case MOUTH:
							break;
						case NIPPLE:
							associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							break;
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							associatedFetishes.add(Fetish.FETISH_MASTURBATION);
							break;
						case VAGINA:
							associatedFetishes.add(Fetish.FETISH_VAGINAL_GIVING);
							associatedFetishes.add(Fetish.FETISH_MASTURBATION);
							break;
						case THIGHS:
							associatedFetishes.add(Fetish.FETISH_LEG_LOVER);
							break;
					}
				}
				break;
			case PENIS:
				if(character.equals(characterOrifice)) {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_RECEIVING);
							break;
						case BREAST:
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							break;
						case MOUTH:
							associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							if(characterPenetrating.equals(characterOrifice)) {
								associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							}
							break;
						case NIPPLE:
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							break;
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							associatedFetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
							break;
						case THIGHS:
							associatedFetishes.add(Fetish.FETISH_STRUTTER);
							break;
					}
				} else {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_GIVING);
							break;
						case BREAST:
							associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							break;
						case MOUTH:
							associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							break;
						case NIPPLE:
							associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							break;
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							associatedFetishes.add(Fetish.FETISH_VAGINAL_GIVING);
							break;
						case THIGHS:
							associatedFetishes.add(Fetish.FETISH_LEG_LOVER);
							break;
					}
				}
				break;
			case TAIL:
				if(character.equals(characterOrifice)) {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_RECEIVING);
							break;
						case BREAST:
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							break;
						case MOUTH:
							associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							if(characterPenetrating.equals(characterOrifice)) {
								associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							}
							break;
						case NIPPLE:
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							break;
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							associatedFetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
							break;
						case THIGHS:
							associatedFetishes.add(Fetish.FETISH_STRUTTER);
							break;
					}
				} else {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_GIVING);
							break;
						case BREAST:
							associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							break;
						case MOUTH:
							associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							break;
						case NIPPLE:
							associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							break;
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							associatedFetishes.add(Fetish.FETISH_VAGINAL_GIVING);
							break;
						case THIGHS:
							associatedFetishes.add(Fetish.FETISH_LEG_LOVER);
							break;
					}
				}
				break;
			case TENTACLE:
				if(character.equals(characterOrifice)) {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_RECEIVING);
							break;
						case BREAST:
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							break;
						case MOUTH:
							associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							if(characterPenetrating.equals(characterOrifice)) {
								associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							}
							break;
						case NIPPLE:
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							break;
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							associatedFetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
							break;
						case THIGHS:
							associatedFetishes.add(Fetish.FETISH_STRUTTER);
							break;
					}
				} else {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_GIVING);
							break;
						case BREAST:
							associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							break;
						case MOUTH:
							associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							break;
						case NIPPLE:
							associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							break;
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							associatedFetishes.add(Fetish.FETISH_VAGINAL_GIVING);
							break;
						case THIGHS:
							associatedFetishes.add(Fetish.FETISH_LEG_LOVER);
							break;
					}
				}
				break;
			case TONGUE:
				if(character.equals(characterOrifice)) {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_RECEIVING);
							associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							if(characterPenetrating.equals(characterOrifice)) {
								associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							}
							break;
						case BREAST:
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							if(characterPenetrating.equals(characterOrifice)) {
								associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							}
							break;
						case MOUTH:
							break;
						case NIPPLE:
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							if(characterPenetrating.equals(characterOrifice)) {
								associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							}
							break;
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							if(characterPenetrating.equals(characterOrifice)) {
								associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							}
							break;
						case VAGINA:
							associatedFetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
							associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							if(characterPenetrating.equals(characterOrifice)) {
								associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							}
							break;
						case THIGHS:
							associatedFetishes.add(Fetish.FETISH_STRUTTER);
							break;
					}
				} else {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_GIVING);
							associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							break;
						case BREAST:
							associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							break;
						case MOUTH:
							associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							break;
						case NIPPLE:
							associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							break;
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							break;
						case VAGINA:
							associatedFetishes.add(Fetish.FETISH_VAGINAL_GIVING);
							associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							break;
						case THIGHS:
							associatedFetishes.add(Fetish.FETISH_LEG_LOVER);
							break;
					}
				}
				break;
		}
		if(!associatedFetishes.contains(Fetish.FETISH_MASTURBATION) && characterPenetrating.equals(characterOrifice)) {
			associatedFetishes.add(Fetish.FETISH_MASTURBATION);
		}
		
		return associatedFetishes;
	}
}
