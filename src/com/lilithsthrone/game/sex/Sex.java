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

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.QuestLine;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.Rarity;
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
import com.lilithsthrone.game.sex.sexActions.dominion.lilaya.SALilayaSpecials;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.BaseColour;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.SizedStack;
import com.lilithsthrone.utils.Util;

/**
 * Singleton enforced by Enum Call initialiseCombat() before using. Then call
 * startSex(), which returns the starting DialogueNode.
 *
 * @since 0.1.0
 * @version 0.1.88
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
	private static SizedStack<SexActionInterface> repeatActionsPlayer;
	private static List<SexActionInterface> availableSexActionsPartner;
	
	// Actions that are currently available from all SexPositionSlots TODO
//	private static Map<GameCharacter, Set<SexActionInterface>> actionsAvailable;
//	private static Map<GameCharacter, Set<SexActionInterface>> orgasmActionsAvailable;
//	private static Map<GameCharacter, Set<SexActionInterface>> actionsAvailable;
	
	private static Set<SexActionInterface> actionsAvailablePlayer;
	private static Set<SexActionInterface> actionsAvailablePartner;
	private static Set<SexActionInterface> orgasmActionsPlayer;
	private static Set<SexActionInterface> orgasmActionsPartner;
	private static Set<SexActionInterface> mutualOrgasmActions;

	private static DialogueNodeOld postSexDialogue;

	private static SexActionInterface lastUsedPlayerAction, lastUsedPartnerAction;
	
	// Tracking statuses:
	
	private static Map<GameCharacter, Map<PenetrationType, Set<LubricationType>>> wetPenetrationTypes;
	private static Map<GameCharacter, Map<OrificeType, Set<LubricationType>>> wetOrificeTypes;
	
	private static Map<GameCharacter, List<CoverableArea>> areasExposed;

	
	private static Map<GameCharacter, Map<GameCharacter, Map<PenetrationType, Set<OrificeType>>>> ongoingPenetrationMap;
	// Clothes:

	private static Map<GameCharacter, List<AbstractClothing>> clothingPreSexMap;
	
	private static boolean sexFinished, partnerAllowedToUseSelfActions, partnerCanRemoveOwnClothes, partnerCanRemovePlayersClothes;
	private static Map<GameCharacter, Integer> orgasmCountMap;
	
	
	//TODO

	
	private static Set<OrificeType> areasCummedInPlayer, areasCummedInPartner,
										areasStretchedPlayer, areasStretchedPartner,
										areasCurrentlyStretchingPlayer, areasCurrentlyStretchingPartner,
										areasTooLoosePlayer, areasTooLoosePartner,
										penetrationRequestsPlayer;


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
		
		actionsAvailablePlayer = new HashSet<>();
		actionsAvailablePartner = new HashSet<>();
		orgasmActionsPlayer = new HashSet<>();
		orgasmActionsPartner = new HashSet<>();
		mutualOrgasmActions = new HashSet<>();
		
		Sex.consensual = consensual;
		Sex.subHasEqualControl = subHasEqualControl;
		
//		Main.game.setActiveNPC(activePartner);
		
		setSexManager(sexManager);
		
		Sex.activePartner.generateSexChoices();
		Sex.postSexDialogue = postSexDialogue;
		
		sexStarted = false;

		activePartner.setLocation(Main.game.getPlayer().getLocation());

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
			}
		}
		
		lastUsedPlayerAction = SexActionUtility.PLAYER_NONE;
		lastUsedPartnerAction = SexActionUtility.PARTNER_NONE;

		sexFinished = false;
		partnerAllowedToUseSelfActions = true;
		partnerCanRemoveOwnClothes = sexManager.isPartnerCanRemoveOwnClothes();
		partnerCanRemovePlayersClothes = sexManager.isPartnerCanRemovePlayersClothes();
		orgasmCountMap = new HashMap<>();

		availableSexActionsPlayer = new ArrayList<>();
		miscActionsPlayer = new ArrayList<>();
		selfActionsPlayer = new ArrayList<>();
		sexActionsPlayer = new ArrayList<>();
		positionActionsPlayer = new ArrayList<>();
		repeatActionsPlayer = new SizedStack<>(15);
		availableSexActionsPartner = new ArrayList<>();

		// Populate exposed areas:
		areasExposed = new HashMap<>();
		for(GameCharacter character : Sex.getAllParticipants()) {
			areasExposed.put(character, new ArrayList<>());
		}

//		for (CoverableArea area : CoverableArea.values()) {
//			if (Main.game.getPlayer().isAbleToAccessCoverableArea(area, false))
//				areasExposed.get(Main.game.getPlayer()).add(area);
//			if (partner.isAbleToAccessCoverableArea(area, false))
//				areasExposedPartner.add(area);
//		}

		penetrationRequestsPlayer = new HashSet<>();

		areasStretchedPlayer = new HashSet<>();
		areasStretchedPartner = new HashSet<>();
		areasCurrentlyStretchingPlayer = new HashSet<>();
		areasCurrentlyStretchingPartner = new HashSet<>();
		areasTooLoosePlayer = new HashSet<>();
		areasTooLoosePartner = new HashSet<>();

		areasCummedInPlayer = new HashSet<>();
		areasCummedInPartner = new HashSet<>();


		
		
		for(GameCharacter character : Sex.getAllParticipants()) {
			character.setLust(50);
			character.setArousal(0);
			
			if(Main.getProperties().nonConContent) {
				if(!character.isPlayer()) {
					if(!((NPC) character).isAttractedTo(Main.game.getPlayer())) {
						character.setLust(0);
					}
				}
			}
			
			if(sexManager.getStartingSexPaceOverride(character)!=null) {
				switch(sexManager.getStartingSexPaceOverride(character)) {
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

		Main.mainController.openInventory();

		// Main.mainController.updateUI();

		// Store status of all clothes for both partners (so they can be restored afterwards):
		clothingPreSexMap = new HashMap<>();
		
		for(GameCharacter character : Sex.getAllParticipants()) {
			clothingPreSexMap.put(character, new ArrayList<>());
			for (AbstractClothing c : character.getClothingCurrentlyEquipped()) {
				clothingPreSexMap.get(character).add(c);
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
		for(Entry<GameCharacter, List<AbstractClothing>> entry : clothingPreSexMap.entrySet()) {
			for (AbstractClothing c : entry.getValue()) {
				if(!c.getClothingType().isDiscardedOnUnequip()) {
					if (!entry.getKey().getClothingCurrentlyEquipped().contains(c)) {
							if(entry.getKey().getAllClothingInInventory().contains(c)) {
								entry.getKey().equipClothingFromInventory(c, true, entry.getKey(), entry.getKey());
							} else {
								entry.getKey().equipClothingFromGround(c, true, entry.getKey());
							}
					} else {
						c.getDisplacedList().clear();
					}
				}
			}
		}
		
		activePartner.setLastTimeHadSex(Main.game.getMinutesPassed(), Sex.getNumberOfOrgasms(activePartner)>0);
		activePartner.endSex(true);
	}

	private static String endSexDescription;

	public static void applyEndSexEffects() {
		sexSB = new StringBuilder();

		// Stretching effects for each of the player's orifices:
		if (Main.game.getPlayer().getAssRawCapacityValue() != Main.game.getPlayer().getAssStretchedCapacity() && areasStretchedPlayer.contains(OrificeType.ANUS_PLAYER)) {
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
		
		if (Main.game.getPlayer().getVaginaRawCapacityValue() != Main.game.getPlayer().getVaginaStretchedCapacity() && areasStretchedPlayer.contains(OrificeType.VAGINA_PLAYER)) {
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
		
		if (Main.game.getPlayer().getNippleRawCapacityValue() != Main.game.getPlayer().getNippleStretchedCapacity() && areasStretchedPlayer.contains(OrificeType.NIPPLE_PLAYER)) {
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
		
		if (Main.game.getPlayer().getPenisRawCapacityValue() != Main.game.getPlayer().getPenisStretchedCapacity() && areasStretchedPlayer.contains(OrificeType.URETHRA_PLAYER)) {
			if (Main.game.getPlayer().getUrethraPlasticity() == OrificePlasticity.ZERO_RUBBERY){

				Main.game.getPlayer().setPenisStretchedCapacity(Main.game.getPlayer().getPenisRawCapacityValue());

				sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Your urethra quickly recovers from its ordeal, and instantly returns to its original size!</b></p>");

			} else {

				// Increment core capacity by the Elasticity's capacityIncreaseModifier:
				Main.game.getPlayer().incrementPenisCapacity(
						(Main.game.getPlayer().getPenisStretchedCapacity()-Main.game.getPlayer().getPenisRawCapacityValue())*Main.game.getPlayer().getUrethraPlasticity().getCapacityIncreaseModifier(),
						false);

				sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Your " + Main.game.getPlayer().getUrethraPlasticity().getDescriptor() + " urethra has been stretched from its ordeal, and is currently "
						+ Capacity.getCapacityFromValue(Main.game.getPlayer().getPenisStretchedCapacity()).getDescriptor() + "!");

				if(Main.game.getPlayer().getUrethraPlasticity().getCapacityIncreaseModifier()>0) {
					sexSB.append(" It will recover some of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(Main.game.getPlayer().getPenisRawCapacityValue()).getDescriptor() + "!</b></p>");
				} else {
					sexSB.append(" It will recover all of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(Main.game.getPlayer().getPenisRawCapacityValue()).getDescriptor() + "!</b></p>");
				}
			}
		}
		
		if (Main.game.getPlayer().getFaceRawCapacityValue() != Main.game.getPlayer().getFaceStretchedCapacity() && areasStretchedPlayer.contains(OrificeType.MOUTH_PLAYER)) {
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
		if (activePartner.getAssRawCapacityValue() != activePartner.getAssStretchedCapacity() && areasStretchedPartner.contains(OrificeType.ANUS_PARTNER)) {
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
		
		if (activePartner.getVaginaRawCapacityValue() != activePartner.getVaginaStretchedCapacity() && areasStretchedPartner.contains(OrificeType.VAGINA_PARTNER)) {
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
		
		if (activePartner.getNippleRawCapacityValue() != activePartner.getNippleStretchedCapacity() && areasStretchedPartner.contains(OrificeType.NIPPLE_PARTNER)) {
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
		
		if (activePartner.getPenisRawCapacityValue() != activePartner.getPenisStretchedCapacity() && areasStretchedPartner.contains(OrificeType.URETHRA_PARTNER)) {
			if (activePartner.getUrethraPlasticity() == OrificePlasticity.ZERO_RUBBERY){

				activePartner.setPenisStretchedCapacity(activePartner.getPenisRawCapacityValue());

				sexSB.append(UtilText.parse(activePartner,
						"<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Her] urethra quickly recovers from its ordeal, and instantly returns to its original size!</b></p>"));

			} else {

				// Increment core capacity by the Elasticity's capacityIncreaseModifier:
				activePartner.incrementPenisCapacity(
						(activePartner.getPenisStretchedCapacity()-activePartner.getPenisRawCapacityValue())*activePartner.getUrethraPlasticity().getCapacityIncreaseModifier(),
						false);

				sexSB.append(UtilText.parse(activePartner,
						"<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Her] " + activePartner.getUrethraPlasticity().getDescriptor() + " urethra has been stretched from its ordeal, and is currently "
						+ Capacity.getCapacityFromValue(activePartner.getPenisStretchedCapacity()).getDescriptor() + "!"));

				if(activePartner.getUrethraPlasticity().getCapacityIncreaseModifier()>0) {
					sexSB.append(" It will recover some of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(activePartner.getPenisRawCapacityValue()).getDescriptor() + "!</b></p>");
				} else {
					sexSB.append(" It will recover all of its original size, eventually tightening back to being " + Capacity.getCapacityFromValue(activePartner.getPenisRawCapacityValue()).getDescriptor() + "!</b></p>");
				}
			}
		}
		
		if (activePartner.getFaceRawCapacityValue() != activePartner.getFaceStretchedCapacity() && areasStretchedPartner.contains(OrificeType.MOUTH_PARTNER)) {
			// Increment core capacity by the Elasticity's capacityIncreaseModifier:
			activePartner.incrementFaceCapacity(
					(activePartner.getFaceStretchedCapacity()-activePartner.getFaceRawCapacityValue())*activePartner.getFacePlasticity().getCapacityIncreaseModifier(),
					false);
			// Special case for throat, as you aren't stretching it out, merely getting more experienced at sucking cock:
			activePartner.setFaceStretchedCapacity(activePartner.getFaceRawCapacityValue());

			sexSB.append("<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>From [npc.her] oral experience with your [pc.cockSize] cock, [npc.she] is now experienced enough to comfortably suck "
					+ PenisSize.getPenisSizeFromInt((int)Main.game.getPlayer().getFaceRawCapacityValue()).getDescriptor() + " cocks!</b></p>");
		}
		

		// Player pregnancy:
		if (!areasCummedInPlayer.isEmpty()) {
			if (areasCummedInPlayer.contains(OrificeType.VAGINA_PLAYER))
				sexSB.append(Main.game.getPlayer().rollForPregnancy(activePartner));
		}
		// Partner pregnancy:
		if (!areasCummedInPartner.isEmpty() && activePartner.isAbleToBeImpregnated()) {
			if (areasCummedInPartner.contains(OrificeType.VAGINA_PARTNER))
				sexSB.append(activePartner.rollForPregnancy(Main.game.getPlayer()));
		}
		
		if(getNumberOfOrgasms(Main.game.getPlayer())==0) {
			if(Sex.getSexPace(Main.game.getPlayer())!=SexPace.SUB_RESISTING) {
				Main.game.getPlayer().addStatusEffect(StatusEffect.FRUSTRATED_NO_ORGASM, 240+postSexDialogue.getMinutesPassed());
				sexSB.append("<p style='text-align:center'>[style.boldArcane(After finishing sex without orgasming once, you're left feeling frustrated and horny!)]</p>");
			}
			
		} else {
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
								+(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)?Main.game.getPlayer().incrementQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY):"")));
						
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
						+"<div style='text-align: center; display:block; margin:0 auto; height:48px; padding:8px 0 8px 0;'>"
						+ "<div class='item-inline"
							+ (TFEssence.ARCANE.getRarity() == Rarity.COMMON ? " common" : "")
							+ (TFEssence.ARCANE.getRarity() == Rarity.UNCOMMON ? " uncommon" : "")
							+ (TFEssence.ARCANE.getRarity() == Rarity.RARE ? " rare" : "")
							+ (TFEssence.ARCANE.getRarity() == Rarity.EPIC ? " epic" : "")
							+ (TFEssence.ARCANE.getRarity() == Rarity.LEGENDARY ? " legendary" : "")
							+ (TFEssence.ARCANE.getRarity() == Rarity.JINXED ? " jinxed" : "") + "'>"
							+ TFEssence.ARCANE.getSVGString()
						+ "</div>"
						+ " <div style='display:inline-block; height:20px; vertical-align: middle;'>"
							+ "<b>[style.boldArcane(You have earned "+(Main.game.getPlayer().hasPerk(Perk.NYMPHOMANIAC)?"four arcane essences":"two arcane essence")+"!)]</b>"
						+ "</div> "
						+ "<div class='item-inline"
							+ (TFEssence.ARCANE.getRarity() == Rarity.COMMON ? " common" : "")
							+ (TFEssence.ARCANE.getRarity() == Rarity.UNCOMMON ? " uncommon" : "")
							+ (TFEssence.ARCANE.getRarity() == Rarity.RARE ? " rare" : "")
							+ (TFEssence.ARCANE.getRarity() == Rarity.EPIC ? " epic" : "")
							+ (TFEssence.ARCANE.getRarity() == Rarity.LEGENDARY ? " legendary" : "")
							+ (TFEssence.ARCANE.getRarity() == Rarity.JINXED ? " jinxed" : "") + "'>"
							+ TFEssence.ARCANE.getSVGString()
						+ "</div>"
						+ "</div>");
				
				if(Main.game.getPlayer().hasPerk(Perk.NYMPHOMANIAC)) {
					Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, 4);
				} else {
					Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, 2);
				}
			}
			Main.game.getPlayer().addStatusEffect(StatusEffect.RECOVERING_AURA, 240);	
		}
		
		if(getNumberOfOrgasms(activePartner)==0) {
			if(Sex.getSexPace(activePartner)!=SexPace.SUB_RESISTING) {
				activePartner.addStatusEffect(StatusEffect.FRUSTRATED_NO_ORGASM, 240+postSexDialogue.getMinutesPassed());
				sexSB.append("<p style='text-align:center'>[style.boldArcane(After finishing sex without orgasming once, [npc.name] is left feeling frustrated and horny!)]</p>");
			}
			
		} else {
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
								+(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)?Main.game.getPlayer().incrementQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY):"")));
						
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
						+"<div style='text-align: center; display:block; margin:0 auto; height:48px; padding:8px 0 8px 0;'>"
						+ "<div class='item-inline"
							+ (TFEssence.ARCANE.getRarity() == Rarity.COMMON ? " common" : "")
							+ (TFEssence.ARCANE.getRarity() == Rarity.UNCOMMON ? " uncommon" : "")
							+ (TFEssence.ARCANE.getRarity() == Rarity.RARE ? " rare" : "")
							+ (TFEssence.ARCANE.getRarity() == Rarity.EPIC ? " epic" : "")
							+ (TFEssence.ARCANE.getRarity() == Rarity.LEGENDARY ? " legendary" : "")
							+ (TFEssence.ARCANE.getRarity() == Rarity.JINXED ? " jinxed" : "") + "'>"
							+ TFEssence.ARCANE.getSVGString()
						+ "</div>"
						+ " <div style='display:inline-block; height:20px; vertical-align: middle;'>"
						+ "<b>[style.boldArcane(You have earned "+(Main.game.getPlayer().hasPerk(Perk.NYMPHOMANIAC)?"four arcane essences":"two arcane essence")+"!)]</b>"
						+ "</div> "
						+ "<div class='item-inline"
							+ (TFEssence.ARCANE.getRarity() == Rarity.COMMON ? " common" : "")
							+ (TFEssence.ARCANE.getRarity() == Rarity.UNCOMMON ? " uncommon" : "")
							+ (TFEssence.ARCANE.getRarity() == Rarity.RARE ? " rare" : "")
							+ (TFEssence.ARCANE.getRarity() == Rarity.EPIC ? " epic" : "")
							+ (TFEssence.ARCANE.getRarity() == Rarity.LEGENDARY ? " legendary" : "")
							+ (TFEssence.ARCANE.getRarity() == Rarity.JINXED ? " jinxed" : "") + "'>"
							+ TFEssence.ARCANE.getSVGString()
						+ "</div>"
						+ "</div>");
				if(Main.game.getPlayer().hasPerk(Perk.NYMPHOMANIAC))
					Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, 4);
				else
					Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, 2);
				
			}
			activePartner.addStatusEffect(StatusEffect.RECOVERING_AURA, 240);
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
			return (Sex.isConsensual()?"Sex":"Non-consensual sex")+": "+getPosition().getName();
		}

		@Override
		public String getContent() {
			return sexDescription + (sexFinished ? endSexDescription : "");
		}

		@Override
		public String getResponseTabTitle(int index) {
			if (sexFinished
					|| lastUsedPartnerAction == SexActionUtility.PARTNER_ORGASM_MUTUAL_WAIT
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
			} else if(lastUsedPartnerAction == SexActionUtility.PARTNER_ORGASM_MUTUAL_WAIT
						|| Main.game.getPlayer().getArousal() >= ArousalLevel.FIVE_ORGASM_IMMINENT.getMaximumValue()
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
					
				} else if(responseTab==1) {
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
					
				} else if(responseTab==2) {
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
					
				} else if(responseTab==3) {
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
					
				} else if(responseTab==4) {
					if(index <= repeatActionsPlayer.size()) {
						if(index==0) {
							if(repeatActionsPlayer.size()>=15) {
								return repeatActionsPlayer.get(14).toResponse();
							} else {
								return null;
							}
						} else {
							return repeatActionsPlayer.get(index-1).toResponse();
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
		
		sexActionPlayer.baseEffects();
		
		applyGenericDescriptionsAndEffects(sexActionPlayer);
		
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
			
						sexActionPartner.baseEffects();
						lastUsedPartnerAction = sexActionPartner;
			
						applyGenericDescriptionsAndEffects(sexActionPartner);
						
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
			
			
			// Re-populate lists for the player's next action choice.
			populatePlayerSexLists();
		}

		lastUsedPlayerAction = sexActionPlayer;
		if(!repeatActionsPlayer.contains(sexActionPlayer)
				&& sexActionPlayer.getActionType()!=SexActionType.PLAYER_PREPARE_PARTNER_ORGASM
				&& sexActionPlayer.getActionType()!=SexActionType.PLAYER_ORGASM
				&& sexActionPlayer.getActionType()!=SexActionType.PLAYER_ORGASM_NO_AROUSAL_RESET) {
			repeatActionsPlayer.add(sexActionPlayer);
		}
		
		repeatActionsPlayer.removeIf(sa-> !sa.isAddedToAvailableSexActions());
		repeatActionsPlayer.removeIf(sa-> !sa.isBaseRequirementsMet());
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

		if(lastUsedPartnerAction == SexActionUtility.PARTNER_ORGASM_MUTUAL_WAIT) {
			for (SexActionInterface sexAction : Sex.getMutualOrgasmActions()) {
				if (sexAction.isAddedToAvailableSexActions()) {
					availableSexActionsPlayer.add(sexAction);
				}
			}

		} else if (Main.game.getPlayer().getArousal() >= ArousalLevel.FIVE_ORGASM_IMMINENT.getMaximumValue()) { // Add orgasm actions if player ready to orgasm:
			
			// If mutual orgasm threshold has been reached, use a mutual orgasm:
			boolean orgasmFound = false;

			if (ArousalLevel.getArousalLevelFromValue(activePartner.getArousal()).isMutualOrgasm()) {
				for (SexActionInterface sexAction : Sex.getMutualOrgasmActions()) {
					if (sexAction.isAddedToAvailableSexActions()) {
						availableSexActionsPlayer.add(sexAction);
						orgasmFound = true;
					}
				}
			}

			// If there were no mutual orgasm options available (or mutual orgasm threshold wasn't reached), use a standard one:
			if (!orgasmFound) {
				for (SexActionInterface sexAction : Sex.getOrgasmActionsPlayer()) {
					if (sexAction.isAddedToAvailableSexActions()) {
						availableSexActionsPlayer.add(sexAction);
					}
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
		
		if(Sex.getTotalParticipantCount()>2) {
			for(GameCharacter character : Sex.getDominantParticipants().keySet()) {
				if(!character.isPlayer()) {
					miscActionsPlayer.add(new SexAction(
												SexActionType.PLAYER,
												ArousalIncrease.ZERO_NONE,
												ArousalIncrease.ZERO_NONE,
												CorruptionLevel.ZERO_PURE,
												null,
												null) {
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
													}
												});
				}
			}
			for(GameCharacter character : Sex.getSubmissiveParticipants().keySet()) {
				if(!character.isPlayer()) {
					if(!character.isPlayer()) {
						miscActionsPlayer.add(new SexAction(
													SexActionType.PLAYER,
													ArousalIncrease.ZERO_NONE,
													ArousalIncrease.ZERO_NONE,
													CorruptionLevel.ZERO_PURE,
													null,
													null) {
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
														}
													});
					}
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

			// If mutual orgasm threshold has been reached, use a mutual orgasm:
			boolean orgasmFound = false;

			// If mutual orgasm threshold has been reached, use a mutual orgasm:
			if (ArousalLevel.getArousalLevelFromValue(Main.game.getPlayer().getArousal()).isMutualOrgasm()) {
				for (SexActionInterface sexAction : Sex.getMutualOrgasmActions()) {
					if (sexAction.isAddedToAvailableSexActions()) {
						availableSexActionsPartner.add(SexActionUtility.PARTNER_ORGASM_MUTUAL_WAIT);
						orgasmFound = true;
						break;
					}
				}
			}
			
			// If there were no mutual orgasm options available (or mutual orgasm threshold wasn't reached), use a standard one:
			if(!orgasmFound) {
				if(SexFlags.playerPreparedForOrgasm) {
					for (SexActionInterface sexAction : Sex.getOrgasmActionsPartner()) {
						if (sexAction.isAddedToAvailableSexActions()) {
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
				} else {
					standardActions = true;
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
			
		}
		
		if (Main.game.getPlayer().getArousal() >= ArousalLevel.FIVE_ORGASM_IMMINENT.getMaximumValue()) { // Add orgasm reactions if ready to orgasm:
			for (SexActionInterface sexAction : Sex.getActionsAvailablePartner()) {
				if (sexAction.getActionType()==SexActionType.PARTNER_PREPARE_PLAYER_ORGASM) {
					if (sexAction.isAddedToAvailableSexActions()) {
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
					
					if(Main.game.isNonConEnabled()
							&& getSexPace(activePartner)==SexPace.SUB_RESISTING
							&& ((sexAction.getSexPace(Sex.getActivePartner())!=null && sexAction.getSexPace(Sex.getActivePartner())!=SexPace.SUB_RESISTING) || sexAction.isPartnerSelfAction())) {
						// Do not add action if the partner is resisting and this action is SUB_EAGER or SUB_NORMAL or is a self action
						
					} else if(sexAction.getSexPace(Sex.getActivePartner())!=null && getSexPace(activePartner)!=sexAction.getSexPace(Sex.getActivePartner())) {
						// Do not add action if action does not correspond to the partner's preferred action pace
						
					} else {
						// Add action as normal:
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
	private static void applyGenericDescriptionsAndEffects(SexActionInterface sexAction) {
		
		//TODO Make specific for each character
		// Arousal increases, taking into account fetishes and sex effects:
		float bonusArousalIncreasePlayer = 0f, bonusArousalIncreasePartner = 0f;
		for(StatusEffect se : Main.game.getPlayer().getStatusEffects()) {
			if(se.isSexEffect()) {
				bonusArousalIncreasePlayer += se.getArousalPerTurnSelf(Main.game.getPlayer());
				bonusArousalIncreasePartner += se.getArousalPerTurnPartner(activePartner);
			}
		}
		for(StatusEffect se : Sex.getActivePartner().getStatusEffects()) {
			if(se.isSexEffect()) {
				bonusArousalIncreasePlayer += se.getArousalPerTurnPartner(Main.game.getPlayer());
				bonusArousalIncreasePartner += se.getArousalPerTurnSelf(activePartner);
			}
		}

		if(sexAction.getFetishes(Main.game.getPlayer())!=null) {
			for(Fetish f : sexAction.getFetishes(Main.game.getPlayer())) {
				if(Main.game.getPlayer().hasFetish(f)) {
					bonusArousalIncreasePlayer += 2f;
				}
			}
		}
		if(sexAction.getFetishes(activePartner)!=null) {
			for(Fetish f : sexAction.getFetishes(activePartner)) {
				if(activePartner.hasFetish(f)) {
					bonusArousalIncreasePartner += 2f;
				}
			}
		}
		
		if(bonusArousalIncreasePlayer>6) {
			bonusArousalIncreasePlayer = 6;
		}
		if(bonusArousalIncreasePartner>6) {
			bonusArousalIncreasePartner = 6;
		}
		
		float playerArousalIncrease = (sexAction.getArousalGainPlayer().getArousalIncreaseValue() + bonusArousalIncreasePlayer);
		if(getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) {
			if(Main.game.getPlayer().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
				playerArousalIncrease*=1.25f;
			} else {
				playerArousalIncrease*=0.5f;
			}
		}
		if(getSexPace(activePartner)==SexPace.SUB_RESISTING && Main.game.getPlayer().hasFetish(Fetish.FETISH_NON_CON_DOM)) {
			playerArousalIncrease*=1.25f;
		}
		Main.game.getPlayer().incrementArousal(playerArousalIncrease);
		
		float partnerArousalIncrease = (sexAction.getArousalGainPartner().getArousalIncreaseValue() + bonusArousalIncreasePartner);
		if(getSexPace(activePartner)==SexPace.SUB_RESISTING) {
			if(activePartner.hasFetish(Fetish.FETISH_NON_CON_SUB)) {
				partnerArousalIncrease*=1.25f;
			} else {
				partnerArousalIncrease*=0.5f;
			}
		}
		if(getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING && activePartner.hasFetish(Fetish.FETISH_NON_CON_DOM)) {
			partnerArousalIncrease*=1.25f;
		}
		activePartner.incrementArousal(partnerArousalIncrease);

		// Cummed in areas:

		// Add any areas that have been cummed in:
		// TODO take into account all penetrationType cum variants.
		// TODO Take into account condom being used on other penetrationTypes
		if (sexAction.getPlayerAreasCummedIn() != null && activePartner.getPenisCumProduction() != CumProduction.ZERO_NONE) {
			if(!activePartner.isWearingCondom() || (activePartner.isWearingCondom() && sexAction.ignorePartnerCondom())){
				for(OrificeType ot : sexAction.getPlayerAreasCummedIn()) {
					areasCummedInPlayer.add(ot);
					Main.game.getPlayer().incrementCumCount(new SexType(PenetrationType.PENIS_PARTNER, ot));
					activePartner.incrementCumCount(new SexType(PenetrationType.PENIS_PARTNER, ot));
					sexSB.append(Main.game.getPlayer().ingestFluid(activePartner, activePartner.getCum().getType(), ot, activePartner.getCum().hasFluidModifier(FluidModifier.ADDICTIVE)));
					
					if(ot==OrificeType.ANUS_PLAYER) {
						Main.game.getPlayer().addStatusEffect(StatusEffect.CREAMPIE_ANUS, 120+postSexDialogue.getMinutesPassed());
					} else if(ot==OrificeType.NIPPLE_PLAYER) {
						Main.game.getPlayer().addStatusEffect(StatusEffect.CREAMPIE_NIPPLES, 120+postSexDialogue.getMinutesPassed());
					} else if(ot==OrificeType.VAGINA_PLAYER) {
						Main.game.getPlayer().addStatusEffect(StatusEffect.CREAMPIE_VAGINA, 120+postSexDialogue.getMinutesPassed());
					} else if(ot==OrificeType.URETHRA_PLAYER) {
						Main.game.getPlayer().addStatusEffect(StatusEffect.CREAMPIE_PENIS, 120+postSexDialogue.getMinutesPassed());
					}
				}
			}
		}
		if (sexAction.getPartnerAreasCummedIn() != null && Main.game.getPlayer().getPenisCumProduction() != CumProduction.ZERO_NONE) {
			if(!Main.game.getPlayer().isWearingCondom() || (Main.game.getPlayer().isWearingCondom() && sexAction.ignorePartnerCondom())){
				for(OrificeType ot : sexAction.getPartnerAreasCummedIn()) {
					areasCummedInPartner.add(ot);
					Main.game.getPlayer().incrementCumCount(new SexType(PenetrationType.PENIS_PLAYER, ot));
					activePartner.incrementCumCount(new SexType(PenetrationType.PENIS_PLAYER, ot));
					sexSB.append(activePartner.ingestFluid(Main.game.getPlayer(), Main.game.getPlayer().getCum().getType(), ot, Main.game.getPlayer().getCum().hasFluidModifier(FluidModifier.ADDICTIVE)));
					
					if(ot==OrificeType.ANUS_PARTNER) {
						activePartner.addStatusEffect(StatusEffect.CREAMPIE_ANUS, 120+postSexDialogue.getMinutesPassed());
					} else if(ot==OrificeType.NIPPLE_PARTNER) {
						activePartner.addStatusEffect(StatusEffect.CREAMPIE_NIPPLES, 120+postSexDialogue.getMinutesPassed());
					} else if(ot==OrificeType.VAGINA_PARTNER) {
						activePartner.addStatusEffect(StatusEffect.CREAMPIE_VAGINA, 120+postSexDialogue.getMinutesPassed());
					} else if(ot==OrificeType.URETHRA_PARTNER) {
						activePartner.addStatusEffect(StatusEffect.CREAMPIE_PENIS, 120+postSexDialogue.getMinutesPassed());
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
					sexSB.append(Main.game.getPlayer().addItem(AbstractItemType.generateFilledCondom(Main.game.getPlayer().getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()).getColour(), Main.game.getPlayer(), Main.game.getPlayer().getCum()), false));
				}
				Main.game.getPlayer().unequipClothingIntoVoid(Main.game.getPlayer().getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()), true, Main.game.getPlayer());
				
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
					sexSB.append(Main.game.getPlayer().addItem(AbstractItemType.generateFilledCondom(activePartner.getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()).getColour(), activePartner, activePartner.getCum()), false));
				}
				activePartner.unequipClothingIntoVoid(activePartner.getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()), true, activePartner);
				
			}
			// Apply orgasm arousal resets:
			incrementNumberOfOrgasms(activePartner, 1);
			activePartner.setArousal(0);

			// Reset appropriate flags:
			SexFlags.playerRequestedCreampie = false;
			SexFlags.playerRequestedPullOut = false;
			SexFlags.playerPreparedForOrgasm = false;
		}
		// Handle mutual orgasms:
		if(sexAction.getActionType()==SexActionType.MUTUAL_ORGASM) {
			// Condom removal:
			if(Main.game.getPlayer().isWearingCondom()){
				Main.game.getPlayer().getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()).setSealed(false);
				if(Main.game.getPlayer().getPenisRawCumProductionValue()>0) {
					sexSB.append(Main.game.getPlayer().addItem(AbstractItemType.generateFilledCondom(Main.game.getPlayer().getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()).getColour(), Main.game.getPlayer(), Main.game.getPlayer().getCum()), false));
				}
				Main.game.getPlayer().unequipClothingIntoVoid(Main.game.getPlayer().getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()), true, Main.game.getPlayer());
				
			}
			if(activePartner.isWearingCondom()){
				activePartner.getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()).setSealed(false);
				if(activePartner.getPenisRawCumProductionValue()>0) {
					sexSB.append(Main.game.getPlayer().addItem(AbstractItemType.generateFilledCondom(activePartner.getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()).getColour(), activePartner, activePartner.getCum()), false));
				}
				activePartner.unequipClothingIntoVoid(activePartner.getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()), true, activePartner);
				
			}
			// Apply orgasm arousal resets:
			incrementNumberOfOrgasms(Main.game.getPlayer(), 1);
			player().setArousal(0);
			// Apply orgasm arousal resets:
			incrementNumberOfOrgasms(activePartner, 1);
			activePartner.setArousal(0);

			// Reset appropriate flags:
			SexFlags.partnerRequestedCreampie = false;
			SexFlags.partnerRequestedPullOut = false;
			SexFlags.playerRequestedCreampie = false;
			SexFlags.playerRequestedPullOut = false;
		}

		// Handle if parts have just become exposed:
		if (sexAction == SexActionUtility.CLOTHING_REMOVAL) {
			handleExposedDescriptions(Sex.getActivePartner(), false);
		}
		
		// Only apply penetration effects if this action isn't an orgasm, and it isn't the end of sex. (Otherwise, ongoing descriptions get appended after the main description, which usually don't make sense.) TODO
		if (!Sex.getOrgasmActionsPlayer().contains(sexAction)
				&& !Sex.getOrgasmActionsPartner().contains(sexAction)
				&& !Sex.getMutualOrgasmActions().contains(sexAction)
				&& sexAction.getActionType() != SexActionType.PARTNER_POSITIONING
				&& sexAction.getActionType() != SexActionType.PLAYER_POSITIONING
				&& !sexAction.endsSex()) {
			for(GameCharacter characterPenetrating : Sex.getAllParticipants()) {
				for(GameCharacter characterPenetrated : Sex.getAllParticipants()) {
					for(Entry<PenetrationType, Set<OrificeType>> entry : ongoingPenetrationMap.get(characterPenetrating).get(characterPenetrated).entrySet()) {
						for(OrificeType t : entry.getValue()) {
							applyPenetrationEffects(characterPenetrating, characterPenetrated, entry.getKey(), t);
						}
					}
				}
			}
		}
		
		calculateWetAreas(false);
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
							+ sexManager.getPlayerAssRevealReaction(isDom(Main.game.getPlayer()))
							+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, OrificeType.ANUS_PLAYER)));
					areasExposed.get(Main.game.getPlayer()).add(CoverableArea.ANUS);
				}
			}
			if (!areasExposed.get(Main.game.getPlayer()).contains(CoverableArea.PENIS)) {
				if (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, false)) {
					if (Main.game.getPlayer().getPenisType() != PenisType.NONE) {
						sexSB.append(
							formatCoverableAreaBecomingExposed(
								(atStartOfSex
										?"Your [pc.cock+] was already exposed before starting sex!"
										:"Your [pc.cock+] is now exposed!"))
								+ sexManager.getPlayerPenisRevealReaction(isDom(Main.game.getPlayer()))
								+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, PenetrationType.PENIS_PLAYER)));
					}
					areasExposed.get(Main.game.getPlayer()).add(CoverableArea.PENIS);
				}
			}
			if (!areasExposed.get(Main.game.getPlayer()).contains(CoverableArea.VAGINA)) {
				if (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, false)) {
					if (Main.game.getPlayer().getVaginaType() != VaginaType.NONE) {
						sexSB.append(
							formatCoverableAreaBecomingExposed(
								(atStartOfSex
										?"Your [pc.pussy+] was already exposed before starting sex!"
										:"Your [pc.pussy+] is now exposed!"))
								+ sexManager.getPlayerVaginaRevealReaction(isDom(Main.game.getPlayer()))
								+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, OrificeType.VAGINA_PLAYER)));
	
					} else if (Main.game.getPlayer().getVaginaType() == VaginaType.NONE && Main.game.getPlayer().getPenisType() == PenisType.NONE) {
						sexSB.append(
								formatCoverableAreaBecomingExposed(
										(atStartOfSex
												?"Your doll-like mound was already exposed before starting sex!"
												:"Your doll-like mound is now exposed!"))
								+ sexManager.getPlayerMoundRevealReaction(isDom(Main.game.getPlayer())));
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
							+ sexManager.getPlayerBreastsRevealReaction(isDom(Main.game.getPlayer()))
							+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, OrificeType.NIPPLE_PLAYER)));
					areasExposed.get(Main.game.getPlayer()).add(CoverableArea.NIPPLES);
				}
			}
		} else {
			// Partner:
			if (!areasExposed.get(characterBeingExposed).contains(CoverableArea.ANUS)) {
				if (activePartner.isAbleToAccessCoverableArea(CoverableArea.ANUS, false)) {
					sexSB.append(
							formatCoverableAreaBecomingExposed(
									(atStartOfSex
											?"[npc.Name]'s [npc.asshole+] was already exposed before starting sex!"
											:"[npc.Name]'s [npc.asshole+] is now exposed!"))
							+ sexManager.getPartnerAssRevealReaction(isDom(Main.game.getPlayer()))
							+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, OrificeType.ANUS_PARTNER)));
					areasExposed.get(characterBeingExposed).add(CoverableArea.ANUS);
				}
			}
			if (!areasExposed.get(characterBeingExposed).contains(CoverableArea.PENIS)) {
				if (activePartner.isAbleToAccessCoverableArea(CoverableArea.PENIS, false)) {
					if (activePartner.getPenisType() != PenisType.NONE) {
						sexSB.append(
								formatCoverableAreaBecomingExposed(
										(atStartOfSex
												?"[npc.Name]'s [npc.cock+] was already exposed before starting sex!"
												:"[npc.Name]'s [npc.cock+] is now exposed!"))
								+ sexManager.getPartnerPenisRevealReaction(isDom(Main.game.getPlayer()))
								+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, PenetrationType.PENIS_PARTNER)));
					}
					areasExposed.get(characterBeingExposed).add(CoverableArea.PENIS);
				}
			}
			if (!areasExposed.get(characterBeingExposed).contains(CoverableArea.VAGINA)) {
				if (activePartner.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false)) {
					if (activePartner.getVaginaType() != VaginaType.NONE) {
						sexSB.append(
								formatCoverableAreaBecomingExposed(
										(atStartOfSex
												?"[npc.Name]'s [npc.pussy+] was already exposed before starting sex!"
												:"[npc.Name]'s [npc.pussy+] is now exposed!"))
								+ sexManager.getPartnerVaginaRevealReaction(isDom(Main.game.getPlayer()))
								+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, OrificeType.VAGINA_PARTNER)));
	
					} else if (activePartner.getVaginaType() == VaginaType.NONE && activePartner.getPenisType() == PenisType.NONE) {
						sexSB.append(
								formatCoverableAreaBecomingExposed(
										(atStartOfSex
												?"[npc.Name]'s doll-like mound was already exposed before starting sex!"
												:"[npc.Name]'s doll-like mound is now exposed!"))
								+ sexManager.getPartnerMoundRevealReaction(isDom(Main.game.getPlayer())));
					}
					areasExposed.get(characterBeingExposed).add(CoverableArea.VAGINA);
				}
			}
			if (!areasExposed.get(characterBeingExposed).contains(CoverableArea.NIPPLES)) {
				if (activePartner.isAbleToAccessCoverableArea(CoverableArea.NIPPLES, false)) {
					sexSB.append(
							formatCoverableAreaBecomingExposed(
									(atStartOfSex
											?"[npc.Name]'s [npc.nipples+] were already exposed before starting sex!"
											:"[npc.Name]'s [npc.nipples+] are now exposed!"))
								+ sexManager.getPartnerBreastsRevealReaction(isDom(Main.game.getPlayer()))
								+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, OrificeType.NIPPLE_PARTNER)));
					areasExposed.get(characterBeingExposed).add(CoverableArea.NIPPLES);
				}
			}
		}
	}

	
	private static void calculateWetAreas(boolean onSexInit) {

		// Add starting lube:
		addOrificeLubrication(Main.game.getPlayer(), OrificeType.MOUTH_PLAYER, LubricationType.PLAYER_SALIVA);
		addPenetrationTypeLubrication(Main.game.getPlayer(), PenetrationType.TONGUE_PLAYER, LubricationType.PLAYER_SALIVA);

		
		// Add player lubrication from cum:
		if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_ANUS)) {
			addOrificeLubrication(Main.game.getPlayer(), OrificeType.ANUS_PLAYER, LubricationType.OTHER_CUM);
		}
		if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_NIPPLES)) {
			addOrificeLubrication(Main.game.getPlayer(), OrificeType.NIPPLE_PLAYER, LubricationType.OTHER_CUM);
		}
		if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)) {
			addOrificeLubrication(Main.game.getPlayer(), OrificeType.VAGINA_PLAYER, LubricationType.OTHER_CUM);
		}

		// Add milk in nipples:
		if(Main.game.getPlayer().getBreastRawLactationValue()>0) {
			addOrificeLubrication(Main.game.getPlayer(), OrificeType.NIPPLE_PLAYER, LubricationType.PLAYER_MILK);
		}
		
		// Add player natural lubrications:
		if(Main.game.getPlayer().getArousal() >= Main.game.getPlayer().getAssWetness().getArousalNeededToGetVaginaWet()) {
			addOrificeLubrication(Main.game.getPlayer(), OrificeType.ANUS_PLAYER, LubricationType.PLAYER_NATURAL_LUBRICATION);
		}
		if(Main.game.getPlayer().hasPenis()) {
			if(Main.game.getPlayer().getArousal() >= Main.game.getPlayer().getPenisCumProduction().getArousalNeededToStartPreCumming()) {
				addPenetrationTypeLubrication(Main.game.getPlayer(), PenetrationType.PENIS_PLAYER, LubricationType.PLAYER_PRECUM);
				addOrificeLubrication(Main.game.getPlayer(), OrificeType.URETHRA_PLAYER, LubricationType.PLAYER_PRECUM);
			}
		}
		if(Main.game.getPlayer().hasVagina()) {
			if(Main.game.getPlayer().getArousal() >= Main.game.getPlayer().getVaginaWetness().getArousalNeededToGetVaginaWet()) {
				addOrificeLubrication(Main.game.getPlayer(), OrificeType.VAGINA_PLAYER, LubricationType.PLAYER_NATURAL_LUBRICATION);
			}
		}
		
		for(GameCharacter character : Sex.getAllParticipants()) {
			if(!character.isPlayer()) {
				addOrificeLubrication(character, OrificeType.MOUTH_PARTNER, LubricationType.PARTNER_SALIVA);
				addPenetrationTypeLubrication(character, PenetrationType.TONGUE_PARTNER, LubricationType.PARTNER_SALIVA);
				
				if(activePartner.getBreastRawLactationValue()>0) {
					addOrificeLubrication(character, OrificeType.NIPPLE_PARTNER, LubricationType.PARTNER_MILK);
				}

				// Add partner lubrication from cum:
				if(activePartner.hasStatusEffect(StatusEffect.CREAMPIE_ANUS)) {
					addOrificeLubrication(character, OrificeType.ANUS_PARTNER, LubricationType.OTHER_CUM);
				}
				if(activePartner.hasStatusEffect(StatusEffect.CREAMPIE_NIPPLES)) {
					addOrificeLubrication(character, OrificeType.NIPPLE_PARTNER, LubricationType.OTHER_CUM);
				}
				if(activePartner.hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)) {
					addOrificeLubrication(character, OrificeType.VAGINA_PARTNER, LubricationType.OTHER_CUM);
				}
				
				// Add partner natural lubrications:
				if(activePartner.getArousal() >= activePartner.getAssWetness().getArousalNeededToGetVaginaWet()) {
					addOrificeLubrication(character, OrificeType.ANUS_PARTNER, LubricationType.PARTNER_NATURAL_LUBRICATION);
				}
				if(activePartner.hasPenis()) {
					if(activePartner.getArousal() >= activePartner.getPenisCumProduction().getArousalNeededToStartPreCumming()) {
						addPenetrationTypeLubrication(character, PenetrationType.PENIS_PARTNER, LubricationType.PARTNER_PRECUM);
						addOrificeLubrication(character, OrificeType.URETHRA_PARTNER, LubricationType.PARTNER_PRECUM);
					}
				}
				if(activePartner.hasVagina()) {
					if(activePartner.getArousal() >= activePartner.getVaginaWetness().getArousalNeededToGetVaginaWet()) {
						addOrificeLubrication(character, OrificeType.VAGINA_PARTNER, LubricationType.PARTNER_NATURAL_LUBRICATION);
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
						+(orificeType.isPlayer()?"your ":activePartner.getName("the")+"'s ")+orificeType.getName()+"."));
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
						+(penetrationType.isPlayer()?"your ":activePartner.getName("the")+"'s ")+penetrationType.getName()+"."));
		}
	}
	
	private static String getLubricationDescription(GameCharacter character, OrificeType orifice) {
		if(wetOrificeTypes.get(character).get(orifice).isEmpty()) {
			return "";
		}
		StringBuilder description = new StringBuilder((orifice.isPlayer() ?"Your " :"[npc.Name]'s ")+orifice.getName() +" "+(orifice.isPlural()?"are":"is")+" lubricated with ");
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
				(penetration.isPlayer()?"Your "+penetration.getName():"[npc.Name]'s "+penetration.getName()) +" "+(penetration.isPlural()?"are":"is")+" lubricated with ");
		List<String> lubes = new ArrayList<>();
		for(LubricationType lube : wetPenetrationTypes.get(character).get(penetration)) {
			lubes.add(lube.getName());
		}
		description.append(Util.stringsToStringList(lubes, false)+".");
		return description.toString();
	}
	
	public static void addOrificeLubrication(GameCharacter character, OrificeType orifice, LubricationType lubrication) {
		boolean appendDescription =
				orifice != OrificeType.URETHRA_PLAYER && orifice != OrificeType.URETHRA_PARTNER// Can't penetrate urethras for now, so skip that description.
				&& !(orifice==OrificeType.MOUTH_PLAYER && lubrication==LubricationType.PLAYER_SALIVA) // Don't give descriptions of saliva lubricating your own mouth.
				&& !(orifice==OrificeType.MOUTH_PARTNER && lubrication==LubricationType.PARTNER_SALIVA)
				&& (orifice==OrificeType.VAGINA_PARTNER?activePartner.isCoverableAreaExposed(CoverableArea.VAGINA):true)
				&& (orifice==OrificeType.ANUS_PARTNER?activePartner.isCoverableAreaExposed(CoverableArea.ANUS):true)
				&& (orifice==OrificeType.NIPPLE_PARTNER?activePartner.isCoverableAreaExposed(CoverableArea.NIPPLES):true);
		
		if(orifice.isPlayer()) {
			if(wetOrificeTypes.get(character).get(orifice).add(lubrication)){
				if(appendDescription) {
					sexSB.append(formatCoverableAreaGettingWet("Your "+orifice.getName()+" "+(orifice.isPlural()?"are":"is")+" quickly lubricated by "+lubrication.getName()+"."));
				}
			}
		} else {
			if(wetOrificeTypes.get(character).get(orifice).add(lubrication)){
				if(appendDescription) {
					sexSB.append(formatCoverableAreaGettingWet("[npc.Name]'s "+orifice.getName()+" "+(orifice.isPlural()?"are":"is")+" quickly lubricated by "+lubrication.getName()+"."));
				}
			}
		}
	}
	
	public static void addPenetrationTypeLubrication(GameCharacter character, PenetrationType penetrationType, LubricationType lubrication) {
		boolean appendDescription =
				!(penetrationType==PenetrationType.TONGUE_PLAYER && lubrication==LubricationType.PLAYER_SALIVA) // Don't give descriptions of saliva lubricating your own tongue.
				&& !(penetrationType==PenetrationType.TONGUE_PARTNER && lubrication==LubricationType.PARTNER_SALIVA)
				&& (penetrationType==PenetrationType.PENIS_PARTNER?activePartner.isCoverableAreaExposed(CoverableArea.PENIS):true);
		
		if(penetrationType.isPlayer()) {
			if((penetrationType == PenetrationType.PENIS_PLAYER && (lubrication == LubricationType.PLAYER_PRECUM || lubrication == LubricationType.PLAYER_CUM) ? !Main.game.getPlayer().isWearingCondom() : true)) { // Can't lubricate if covered by condom
				if(wetPenetrationTypes.get(character).get(penetrationType).add(lubrication)){
					if(appendDescription) {
						sexSB.append(formatCoverableAreaGettingWet("Your "+penetrationType.getName()+" "+(penetrationType.isPlural()?"are":"is")+" quickly lubricated by "+lubrication.getName()+"."));
					}
				}
			}
		} else {
			if((penetrationType == PenetrationType.PENIS_PARTNER && (lubrication == LubricationType.PARTNER_PRECUM || lubrication == LubricationType.PARTNER_CUM) ? !activePartner.isWearingCondom() : true)) { // Can't lubricate if covered by condom
				if(wetPenetrationTypes.get(character).get(penetrationType).add(lubrication)){
					if(appendDescription) {
						sexSB.append(formatCoverableAreaGettingWet("[npc.Name]'s "+penetrationType.getName()+" "+(penetrationType.isPlural()?"are":"is")+" quickly lubricated by "+lubrication.getName()+"."));
					}
				}
			}
		}
	}


	private static Set<OrificeType> initialPenetrations = new HashSet<>();
	
	public static void applyPenetration(GameCharacter characterPenetrating, GameCharacter characterPenetrated, PenetrationType penetration, OrificeType orifice) {
		
		SexType relatedSexType = new SexType(penetration, orifice);
		
		// Free up orifice and penetrator:
		removePenetration(characterPenetrating, characterPenetrated, penetration, orifice);
		
		ongoingPenetrationMap.get(characterPenetrating).get(characterPenetrated).putIfAbsent(penetration, new HashSet<>());
		ongoingPenetrationMap.get(characterPenetrating).get(characterPenetrated).get(penetration).add(orifice);
		
		initialPenetrations.add(orifice);
		
		if(characterPenetrated != null && characterPenetrating != null) {
			characterPenetrated.incrementSexCount(relatedSexType);
			characterPenetrating.incrementSexCount(relatedSexType);
			
			characterPenetrating.addSexPartner(characterPenetrated, relatedSexType);
			characterPenetrated.addSexPartner(characterPenetrating, relatedSexType);
			
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
					sexSB.append(formatStopPenetration(activePartner.getStopPenetrationDescription(penetrationType, orifice)));
				}
			}
			
			if(ongoingPenetrationMap.get(characterPenetrating).get(characterPenetrated).get(penetrationType).isEmpty()) {
				ongoingPenetrationMap.get(characterPenetrating).get(characterPenetrated).remove(penetrationType);
			}
		}
		
	}

	private static void applyPenetrationEffects(GameCharacter characterPenetrating, GameCharacter characterPenetrated, PenetrationType penetrationType, OrificeType orifice) { //TODO formatting
		
		SexType relatedSexType = new SexType(penetrationType, orifice);
		
		if (penetrationType == PenetrationType.PENIS_PLAYER) {
			if(Main.game.getPlayer().isPenisVirgin()) {
				// Do not need to append virginity loss description here, as it will be handled in the orifice virginity loss description.
				if(activePartner.hasFetish(Fetish.FETISH_DEFLOWERING)) {
					activePartner.incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(activePartner));
				}
				Main.game.getPlayer().setVirginityLoss(relatedSexType, activePartner.getName("a") + " " + activePartner.getLostVirginityDescriptor());
				Main.game.getPlayer().setPenisVirgin(false);
			}
			
		} else if (penetrationType == PenetrationType.PENIS_PARTNER) {
			if(activePartner.isPenisVirgin()) {
				// Do not need to append virginity loss description here, as it will be handled in the orifice virginity loss description.
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_DEFLOWERING)) {
					Main.game.getPlayer().incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(Main.game.getPlayer()));
				}
				activePartner.setPenisVirgin(false);
			}
		}
		
		// Append description based on what orifice is being penetrated and by whom:
		
		if (orifice == OrificeType.ANUS_PLAYER) {
			if (initialPenetrations.contains(OrificeType.ANUS_PLAYER)) {
				sexSB.append(formatInitialPenetration(activePartner.getPenetrationDescription(true, penetrationType, orifice)));
				
				if (Main.game.getPlayer().isAssVirgin()) {
					if (penetrationType.isTakesVirginity()) {
						sexSB.append(activePartner.getVirginityLossDescription(penetrationType, OrificeType.ANUS_PLAYER));
						if(activePartner.hasFetish(Fetish.FETISH_DEFLOWERING)) {
							activePartner.incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(activePartner));
						}
						Main.game.getPlayer().setVirginityLoss(relatedSexType, activePartner.getName("a") + " " + activePartner.getLostVirginityDescriptor());
						Main.game.getPlayer().setAssVirgin(false);
					}
				}
				initialPenetrations.remove(OrificeType.ANUS_PLAYER);
			} else {
				sexSB.append(formatPenetration(activePartner.getPenetrationDescription(false, penetrationType, orifice)));
			}
				
		} else if (orifice == OrificeType.ANUS_PARTNER) {
			if (initialPenetrations.contains(OrificeType.ANUS_PARTNER)) {
				sexSB.append(formatInitialPenetration(activePartner.getPenetrationDescription(true, penetrationType, orifice)));
				
				if (activePartner.isAssVirgin()) {
					if (penetrationType.isTakesVirginity()) {
						sexSB.append(activePartner.getVirginityLossDescription(penetrationType, OrificeType.ANUS_PARTNER));
						if(Main.game.getPlayer().hasFetish(Fetish.FETISH_DEFLOWERING)) {
							Main.game.getPlayer().incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(Main.game.getPlayer()));
						}
						activePartner.setAssVirgin(false);
					}
				}
				initialPenetrations.remove(OrificeType.ANUS_PARTNER);
			} else {
				sexSB.append(formatPenetration(activePartner.getPenetrationDescription(false, penetrationType, orifice)));
			}
			
		} else if (orifice == OrificeType.VAGINA_PLAYER) {
			if (initialPenetrations.contains(OrificeType.VAGINA_PLAYER)) {
				sexSB.append(formatInitialPenetration(activePartner.getPenetrationDescription(true, penetrationType, orifice)));
				
				if (Main.game.getPlayer().isVaginaVirgin()) {
						if (penetrationType.isTakesVirginity()) {
							sexSB.append(activePartner.getVirginityLossDescription(penetrationType, OrificeType.VAGINA_PLAYER));
							if(activePartner.hasFetish(Fetish.FETISH_DEFLOWERING)) {
								activePartner.incrementExperience(Fetish.getExperienceGainFromTakingVaginalVirginity(activePartner));
							}
							Main.game.getPlayer().setVirginityLoss(relatedSexType, activePartner.getName("a") + " " + activePartner.getLostVirginityDescriptor());
							Main.game.getPlayer().setVaginaVirgin(false);
						}
				}
				initialPenetrations.remove(OrificeType.VAGINA_PLAYER);
			} else {
				sexSB.append(formatPenetration(activePartner.getPenetrationDescription(false, penetrationType, orifice)));
			}
			
		} else if (orifice == OrificeType.VAGINA_PARTNER) {
			if (initialPenetrations.contains(OrificeType.VAGINA_PARTNER)) {
				sexSB.append(formatInitialPenetration(activePartner.getPenetrationDescription(true, penetrationType, orifice)));
				
				if (activePartner.isVaginaVirgin()) {
					if (penetrationType.isTakesVirginity()) {
						sexSB.append(activePartner.getVirginityLossDescription(penetrationType, OrificeType.VAGINA_PARTNER));
						if(Main.game.getPlayer().hasFetish(Fetish.FETISH_DEFLOWERING)) {
							Main.game.getPlayer().incrementExperience(Fetish.getExperienceGainFromTakingVaginalVirginity(Main.game.getPlayer()));
						}
						activePartner.setVaginaVirgin(false);
					}
				}
				initialPenetrations.remove(OrificeType.VAGINA_PARTNER);
			} else {
				sexSB.append(formatPenetration(activePartner.getPenetrationDescription(false, penetrationType, orifice)));
			}
			
		} else if (orifice == OrificeType.NIPPLE_PLAYER) {
			// Penetrating player's nipples:
			if (initialPenetrations.contains(OrificeType.NIPPLE_PLAYER)) {
					sexSB.append(formatInitialPenetration(activePartner.getPenetrationDescription(true, penetrationType, orifice)));
					
					if (Main.game.getPlayer().isNippleVirgin()) {
						if (penetrationType.isTakesVirginity()) {
							sexSB.append(activePartner.getVirginityLossDescription(penetrationType, OrificeType.NIPPLE_PLAYER));
							if(activePartner.hasFetish(Fetish.FETISH_DEFLOWERING)) {
								activePartner.incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(activePartner));
							}
							Main.game.getPlayer().setVirginityLoss(relatedSexType, activePartner.getName("a") + " " + activePartner.getLostVirginityDescriptor());
							Main.game.getPlayer().setNippleVirgin(false);
						}
					}
					initialPenetrations.remove(OrificeType.NIPPLE_PLAYER);
				} else {
					sexSB.append(formatPenetration(activePartner.getPenetrationDescription(false, penetrationType, orifice)));
				}
				
		} else if (orifice == OrificeType.NIPPLE_PARTNER) {
			if (initialPenetrations.contains(OrificeType.NIPPLE_PARTNER)) {
				sexSB.append(formatInitialPenetration(activePartner.getPenetrationDescription(true, penetrationType, orifice)));
				
				if (activePartner.isNippleVirgin()) {
					if (penetrationType.isTakesVirginity()) {
						sexSB.append(activePartner.getVirginityLossDescription(penetrationType, OrificeType.NIPPLE_PARTNER));
						if(Main.game.getPlayer().hasFetish(Fetish.FETISH_DEFLOWERING)) {
							Main.game.getPlayer().incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(Main.game.getPlayer()));
						}
						activePartner.setNippleVirgin(false);
					}
				}
				initialPenetrations.remove(OrificeType.NIPPLE_PARTNER);
			} else {
				sexSB.append(formatPenetration(activePartner.getPenetrationDescription(false, penetrationType, orifice)));
			}
		
		} else if (orifice == OrificeType.URETHRA_PLAYER) {
			// Penetrating player's urethra:
			if (initialPenetrations.contains(OrificeType.URETHRA_PLAYER)) {
					sexSB.append(formatInitialPenetration(activePartner.getPenetrationDescription(true, penetrationType, orifice)));
					
					if (Main.game.getPlayer().isUrethraVirgin()) {
						if (penetrationType.isTakesVirginity()) {
							sexSB.append(activePartner.getVirginityLossDescription(penetrationType, OrificeType.URETHRA_PLAYER));
							if(activePartner.hasFetish(Fetish.FETISH_DEFLOWERING)) {
								activePartner.incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(activePartner));
							}
							Main.game.getPlayer().setVirginityLoss(relatedSexType, activePartner.getName("a") + " " + activePartner.getLostVirginityDescriptor());
							Main.game.getPlayer().setUrethraVirgin(false);
						}
					}
					initialPenetrations.remove(OrificeType.URETHRA_PLAYER);
				} else {
					sexSB.append(formatPenetration(activePartner.getPenetrationDescription(false, penetrationType, orifice)));
				}
			
		} else if (orifice == OrificeType.URETHRA_PARTNER) {
			if (initialPenetrations.contains(OrificeType.URETHRA_PARTNER)) {
				sexSB.append(formatInitialPenetration(activePartner.getPenetrationDescription(true, penetrationType, orifice)));
				
				if (activePartner.isUrethraVirgin()) {
					if (penetrationType.isTakesVirginity()) {
						sexSB.append(activePartner.getVirginityLossDescription(penetrationType, OrificeType.URETHRA_PARTNER));
						if(Main.game.getPlayer().hasFetish(Fetish.FETISH_DEFLOWERING)) {
							Main.game.getPlayer().incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(Main.game.getPlayer()));
						}
						activePartner.setUrethraVirgin(false);
					}
				}
				initialPenetrations.remove(OrificeType.URETHRA_PARTNER);
			} else {
				sexSB.append(formatPenetration(activePartner.getPenetrationDescription(false, penetrationType, orifice)));
			}

		} else if (orifice == OrificeType.MOUTH_PLAYER) {
			// Penetrating player's mouth:
			if (initialPenetrations.contains(OrificeType.MOUTH_PLAYER)) {
					sexSB.append(formatInitialPenetration(activePartner.getPenetrationDescription(true, penetrationType, orifice)));
					
					if (Main.game.getPlayer().isFaceVirgin()) {
						if (penetrationType.isTakesVirginity()) {
							sexSB.append(activePartner.getVirginityLossDescription(penetrationType, OrificeType.MOUTH_PLAYER));
							if(activePartner.hasFetish(Fetish.FETISH_DEFLOWERING)) {
								activePartner.incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(activePartner));
							}
							Main.game.getPlayer().setVirginityLoss(relatedSexType, activePartner.getName("a") + " " + activePartner.getLostVirginityDescriptor());
							Main.game.getPlayer().setFaceVirgin(false);
						}
					}
					initialPenetrations.remove(OrificeType.MOUTH_PLAYER);
				} else {
					sexSB.append(formatPenetration(activePartner.getPenetrationDescription(false, penetrationType, orifice)));
				}
				
		} else if (orifice == OrificeType.MOUTH_PARTNER) {
			if (initialPenetrations.contains(OrificeType.MOUTH_PARTNER)) {
				sexSB.append(formatInitialPenetration(activePartner.getPenetrationDescription(true, penetrationType, orifice)));
				
				if (activePartner.isFaceVirgin()) {
					if (penetrationType.isTakesVirginity()) {
						sexSB.append(activePartner.getVirginityLossDescription(penetrationType, OrificeType.MOUTH_PARTNER));
						if(Main.game.getPlayer().hasFetish(Fetish.FETISH_DEFLOWERING)) {
							Main.game.getPlayer().incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(Main.game.getPlayer()));
						}
						activePartner.setFaceVirgin(false);
					}
				}
				initialPenetrations.remove(OrificeType.MOUTH_PARTNER);
			} else {
				sexSB.append(formatPenetration(activePartner.getPenetrationDescription(false, penetrationType, orifice)));
			}
			
		}

		// TODO apply masochism effects to stretching:

		// Stretching effects (will only stretch from penises):
		if (penetrationType.isPenis()) {
			
			GameCharacter personPenetrating = Main.game.getPlayer();
			if(!penetrationType.isPlayer()) {
				personPenetrating = activePartner;
			}
			
			boolean lubed = !wetOrificeTypes.get(orifice.isPlayer()?Main.game.getPlayer():Sex.getActivePartner()).get(orifice).isEmpty();
			boolean twoPenisesInOrifice = false;

			if (orifice.isPlayer()) {
				areasCurrentlyStretchingPlayer.clear();
				if (orifice == OrificeType.ANUS_PLAYER){
					if (Capacity.isPenisSizeTooBig((int)Main.game.getPlayer().getAssStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
						sexSB.append(activePartner.getPlayerAssStretchingDescription(penetrationType));

						// Stretch out the orifice by a factor of elasticity's modifier.
						Main.game.getPlayer().incrementAssStretchedCapacity((((float)personPenetrating.getPenisRawSizeValue())-Main.game.getPlayer().getAssStretchedCapacity())*Main.game.getPlayer().getAssElasticity().getStretchModifier());
						if(Main.game.getPlayer().getAssStretchedCapacity()>personPenetrating.getPenisRawSizeValue())
							Main.game.getPlayer().setAssStretchedCapacity(personPenetrating.getPenisRawSizeValue());

						areasCurrentlyStretchingPlayer.add(OrificeType.ANUS_PLAYER);

						// If just stretched out enough to be comfortable, append that description:
						if(!Capacity.isPenisSizeTooBig((int)Main.game.getPlayer().getAssStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
							sexSB.append(activePartner.getPlayerAssStretchingFinishedDescription());
							areasCurrentlyStretchingPlayer.remove(OrificeType.ANUS_PLAYER);
						}

						areasStretchedPlayer.add(OrificeType.ANUS_PLAYER);

					}else if(Capacity.isPenisSizeTooSmall((int)Main.game.getPlayer().getAssStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
						sexSB.append(activePartner.getPlayerAssTooLooseDescription());
						areasTooLoosePlayer.add(OrificeType.ANUS_PLAYER);
					}

				} else if (orifice == OrificeType.VAGINA_PLAYER){
					if (Capacity.isPenisSizeTooBig((int)Main.game.getPlayer().getVaginaStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
						sexSB.append(activePartner.getPlayerVaginaStretchingDescription(penetrationType));
						
						// Stretch out the orifice by a factor of elasticity's modifier.
						Main.game.getPlayer().incrementVaginaStretchedCapacity((((float)personPenetrating.getPenisRawSizeValue())-Main.game.getPlayer().getVaginaStretchedCapacity())*Main.game.getPlayer().getVaginaElasticity().getStretchModifier());
						if(Main.game.getPlayer().getVaginaStretchedCapacity()>personPenetrating.getPenisRawSizeValue()) {
							Main.game.getPlayer().setVaginaStretchedCapacity(personPenetrating.getPenisRawSizeValue());
						}
						
						areasCurrentlyStretchingPlayer.add(OrificeType.VAGINA_PLAYER);
						
						// If just stretched out enough to be comfortable, append that description:
						if(!Capacity.isPenisSizeTooBig((int)Main.game.getPlayer().getVaginaStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
							sexSB.append(activePartner.getPlayerVaginaStretchingFinishedDescription());
							areasCurrentlyStretchingPlayer.remove(OrificeType.VAGINA_PLAYER);
						}

						areasStretchedPlayer.add(OrificeType.VAGINA_PLAYER);

					} else if(Capacity.isPenisSizeTooSmall((int)Main.game.getPlayer().getVaginaStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
						sexSB.append(activePartner.getPlayerVaginaTooLooseDescription());
						areasTooLoosePlayer.add(OrificeType.VAGINA_PLAYER);
					}

				}else if (orifice == OrificeType.NIPPLE_PLAYER){
					if (Capacity.isPenisSizeTooBig((int)Main.game.getPlayer().getNippleStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
						sexSB.append(activePartner.getPlayerBreastsStretchingDescription(penetrationType));

						// Stretch out the orifice by a factor of elasticity's modifier.
						Main.game.getPlayer().incrementNippleStretchedCapacity((((float)personPenetrating.getPenisRawSizeValue())-Main.game.getPlayer().getNippleStretchedCapacity())*Main.game.getPlayer().getNippleElasticity().getStretchModifier());
						if(Main.game.getPlayer().getNippleStretchedCapacity()>personPenetrating.getPenisRawSizeValue())
							Main.game.getPlayer().setNippleStretchedCapacity(personPenetrating.getPenisRawSizeValue());

						areasCurrentlyStretchingPlayer.add(OrificeType.NIPPLE_PLAYER);

						// If just stretched out enough to be comfortable, append that description:
						if(!Capacity.isPenisSizeTooBig((int)Main.game.getPlayer().getNippleStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
							sexSB.append(activePartner.getPlayerBreastsStretchingFinishedDescription());
							areasCurrentlyStretchingPlayer.remove(OrificeType.NIPPLE_PLAYER);
						}

						areasStretchedPlayer.add(OrificeType.NIPPLE_PLAYER);

					}else if(Capacity.isPenisSizeTooSmall((int)Main.game.getPlayer().getNippleStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
						sexSB.append(activePartner.getPlayerBreastsTooLooseDescription());
						areasTooLoosePlayer.add(OrificeType.NIPPLE_PLAYER);
					}

				}else if (orifice == OrificeType.URETHRA_PLAYER){
					if (Capacity.isPenisSizeTooBig((int)Main.game.getPlayer().getPenisStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
						sexSB.append(activePartner.getPlayerPenisStretchingDescription(penetrationType));

						// Stretch out the orifice by a factor of elasticity's modifier.
						Main.game.getPlayer().incrementPenisStretchedCapacity((((float)personPenetrating.getPenisRawSizeValue())-Main.game.getPlayer().getPenisStretchedCapacity())*Main.game.getPlayer().getUrethraElasticity().getStretchModifier());
						if(Main.game.getPlayer().getPenisStretchedCapacity()>personPenetrating.getPenisRawSizeValue())
							Main.game.getPlayer().setPenisStretchedCapacity(personPenetrating.getPenisRawSizeValue());

						areasCurrentlyStretchingPlayer.add(OrificeType.URETHRA_PLAYER);

						// If just stretched out enough to be comfortable, append that description:
						if(!Capacity.isPenisSizeTooBig((int)Main.game.getPlayer().getPenisStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
							sexSB.append(activePartner.getPlayerPenisStretchingFinishedDescription());
							areasCurrentlyStretchingPlayer.remove(OrificeType.URETHRA_PLAYER);
						}

						areasStretchedPlayer.add(OrificeType.URETHRA_PLAYER);

					}else if(Capacity.isPenisSizeTooSmall((int)Main.game.getPlayer().getPenisStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
						sexSB.append(activePartner.getPlayerPenisTooLooseDescription());
						areasTooLoosePlayer.add(OrificeType.URETHRA_PLAYER);
					}

				}else if (orifice == OrificeType.MOUTH_PLAYER){
					if (Capacity.isPenisSizeTooBig((int)Main.game.getPlayer().getFaceStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
						sexSB.append(activePartner.getPlayerMouthStretchingDescription(penetrationType));

						// Stretch out the orifice by a factor of elasticity's modifier.
						Main.game.getPlayer().incrementFaceStretchedCapacity((((float)personPenetrating.getPenisRawSizeValue())-Main.game.getPlayer().getFaceStretchedCapacity())*Main.game.getPlayer().getFaceElasticity().getStretchModifier());
						if(Main.game.getPlayer().getFaceStretchedCapacity()>personPenetrating.getPenisRawSizeValue())
							Main.game.getPlayer().setFaceStretchedCapacity(personPenetrating.getPenisRawSizeValue());

						areasCurrentlyStretchingPlayer.add(OrificeType.MOUTH_PLAYER);

						// If just stretched out enough to be comfortable, append that description:
						if(!Capacity.isPenisSizeTooBig((int)Main.game.getPlayer().getFaceStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
							sexSB.append(activePartner.getPlayerMouthStretchingFinishedDescription());
							areasCurrentlyStretchingPlayer.remove(OrificeType.MOUTH_PLAYER);
						}

						areasStretchedPlayer.add(OrificeType.MOUTH_PLAYER);

					}else if(Capacity.isPenisSizeTooSmall((int)Main.game.getPlayer().getFaceStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
						sexSB.append(activePartner.getPlayerMouthTooLooseDescription());
					}
				}

			} else {
				areasCurrentlyStretchingPartner.clear();
				if (orifice == OrificeType.ANUS_PARTNER){
					if (Capacity.isPenisSizeTooBig((int)activePartner.getAssStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
						sexSB.append(activePartner.getPartnerAssStretchingDescription(penetrationType));

						// Stretch out the orifice by a factor of elasticity's modifier.
						activePartner.incrementAssStretchedCapacity((((float)personPenetrating.getPenisRawSizeValue())-activePartner.getAssStretchedCapacity())*activePartner.getAssElasticity().getStretchModifier());
						if(activePartner.getAssStretchedCapacity()>personPenetrating.getPenisRawSizeValue())
							activePartner.setAssStretchedCapacity(personPenetrating.getPenisRawSizeValue());

						areasCurrentlyStretchingPartner.add(OrificeType.ANUS_PARTNER);

						// If just stretched out enough to be comfortable, append that description:
						if(!Capacity.isPenisSizeTooBig((int)activePartner.getAssStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
							sexSB.append(activePartner.getPartnerAssStretchingFinishedDescription());
							areasCurrentlyStretchingPartner.remove(OrificeType.ANUS_PARTNER);
						}

						areasStretchedPartner.add(OrificeType.ANUS_PARTNER);

					}else if(Capacity.isPenisSizeTooSmall((int)activePartner.getAssStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
						sexSB.append(activePartner.getPartnerAssTooLooseDescription());
						areasTooLoosePartner.add(OrificeType.ANUS_PARTNER);
					}

				}else if (orifice == OrificeType.VAGINA_PARTNER){
					if (Capacity.isPenisSizeTooBig((int)activePartner.getVaginaStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
						sexSB.append(activePartner.getPartnerVaginaStretchingDescription(penetrationType));

						// Stretch out the orifice by a factor of elasticity's modifier.
						activePartner.incrementVaginaStretchedCapacity((((float)personPenetrating.getPenisRawSizeValue())-activePartner.getVaginaStretchedCapacity())*activePartner.getVaginaElasticity().getStretchModifier());
						if(activePartner.getVaginaStretchedCapacity()>personPenetrating.getPenisRawSizeValue())
							activePartner.setVaginaStretchedCapacity(personPenetrating.getPenisRawSizeValue());

						areasCurrentlyStretchingPartner.add(OrificeType.VAGINA_PARTNER);

						// If just stretched out enough to be comfortable, append that description:
						if(!Capacity.isPenisSizeTooBig((int)activePartner.getVaginaStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
							sexSB.append(activePartner.getPartnerVaginaStretchingFinishedDescription());
							areasCurrentlyStretchingPartner.remove(OrificeType.VAGINA_PARTNER);
						}

						areasStretchedPartner.add(OrificeType.VAGINA_PARTNER);

					}else if(Capacity.isPenisSizeTooSmall((int)activePartner.getVaginaStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
						sexSB.append(activePartner.getPartnerVaginaTooLooseDescription());
						areasTooLoosePartner.add(OrificeType.VAGINA_PARTNER);
					}

				}else if (orifice == OrificeType.NIPPLE_PARTNER){
					if (Capacity.isPenisSizeTooBig((int)activePartner.getNippleStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
						sexSB.append(activePartner.getPartnerBreastsStretchingDescription(penetrationType));

						// Stretch out the orifice by a factor of elasticity's modifier.
						activePartner.incrementNippleStretchedCapacity((((float)personPenetrating.getPenisRawSizeValue())-activePartner.getNippleStretchedCapacity())*activePartner.getNippleElasticity().getStretchModifier());
						if(activePartner.getNippleStretchedCapacity()>personPenetrating.getPenisRawSizeValue())
							activePartner.setNippleStretchedCapacity(personPenetrating.getPenisRawSizeValue());

						areasCurrentlyStretchingPartner.add(OrificeType.NIPPLE_PARTNER);

						// If just stretched out enough to be comfortable, append that description:
						if(!Capacity.isPenisSizeTooBig((int)activePartner.getNippleStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
							sexSB.append(activePartner.getPartnerBreastsStretchingFinishedDescription());
							areasCurrentlyStretchingPartner.remove(OrificeType.NIPPLE_PARTNER);
						}

						areasStretchedPartner.add(OrificeType.NIPPLE_PARTNER);

					}else if(Capacity.isPenisSizeTooSmall((int)activePartner.getNippleStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
						sexSB.append(activePartner.getPartnerBreastsTooLooseDescription());
						areasTooLoosePartner.add(OrificeType.NIPPLE_PARTNER);
					}

				}else if (orifice == OrificeType.URETHRA_PARTNER){
					if (Capacity.isPenisSizeTooBig((int)activePartner.getPenisStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
						sexSB.append(activePartner.getPartnerPenisStretchingDescription(penetrationType));

						// Stretch out the orifice by a factor of elasticity's modifier.
						activePartner.incrementPenisStretchedCapacity((((float)personPenetrating.getPenisRawSizeValue())-activePartner.getPenisStretchedCapacity())*activePartner.getUrethraElasticity().getStretchModifier());
						if(activePartner.getPenisStretchedCapacity()>personPenetrating.getPenisRawSizeValue())
							activePartner.setPenisStretchedCapacity(personPenetrating.getPenisRawSizeValue());

						areasCurrentlyStretchingPartner.add(OrificeType.URETHRA_PARTNER);

						// If just stretched out enough to be comfortable, append that description:
						if(!Capacity.isPenisSizeTooBig((int)activePartner.getPenisStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
							sexSB.append(activePartner.getPartnerPenisStretchingFinishedDescription());
							areasCurrentlyStretchingPartner.remove(OrificeType.URETHRA_PARTNER);
						}

						areasStretchedPartner.add(OrificeType.URETHRA_PARTNER);

					}else if(Capacity.isPenisSizeTooSmall((int)activePartner.getPenisStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
						sexSB.append(activePartner.getPartnerPenisTooLooseDescription());
						areasTooLoosePartner.add(OrificeType.URETHRA_PARTNER);
					}

				}else if (orifice == OrificeType.MOUTH_PARTNER){
					if (Capacity.isPenisSizeTooBig((int)activePartner.getFaceStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
						sexSB.append(activePartner.getPartnerMouthStretchingDescription(penetrationType));

						// Stretch out the orifice by a factor of elasticity's modifier.
						activePartner.incrementFaceStretchedCapacity((((float)personPenetrating.getPenisRawSizeValue())-activePartner.getFaceStretchedCapacity())*activePartner.getFaceElasticity().getStretchModifier());
						if(activePartner.getFaceStretchedCapacity()>personPenetrating.getPenisRawSizeValue())
							activePartner.setFaceStretchedCapacity(personPenetrating.getPenisRawSizeValue());

						areasCurrentlyStretchingPartner.add(OrificeType.MOUTH_PARTNER);

						// If just stretched out enough to be comfortable, append that description:
						if(!Capacity.isPenisSizeTooBig((int)activePartner.getFaceStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
							sexSB.append(activePartner.getPartnerMouthStretchingFinishedDescription());
							areasCurrentlyStretchingPartner.remove(OrificeType.MOUTH_PARTNER);
						}

						areasStretchedPartner.add(OrificeType.MOUTH_PARTNER);

					}else if(Capacity.isPenisSizeTooSmall((int)activePartner.getFaceStretchedCapacity(), personPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
						sexSB.append(activePartner.getPartnerMouthTooLooseDescription());
					}
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
				throw new NullPointerException("No clothing found to remove!");
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
			if (clothingRemoval.getKey() == null)
				throw new NullPointerException("No clothing found to remove!");

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
		return Sex.getActivePartner().getArousal()<ArousalLevel.ONE_TURNED_ON.getMaximumValue() && Sex.getNumberOfOrgasms(activePartner)==0;
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
			case FINGER_PARTNER: case FINGER_PLAYER:
				penetrationTypesAvailable = penetrator.getArmRows()*2;
				break;
			case PENIS_PARTNER: case PENIS_PLAYER:
				if(!penetrator.hasPenis()) {
					return true;
				}
				break;
			case TAIL_PARTNER: case TAIL_PLAYER:
				if(penetrator.getTailType()==TailType.NONE|| penetrator.getTailCount()==0) {
					return true;
				}
				penetrationTypesAvailable = penetrator.getTailCount();
				break;
			case TENTACLE_PARTNER: case TENTACLE_PLAYER:
				break;
			case TONGUE_PARTNER: case TONGUE_PLAYER:
				break;
		}
		
		for(GameCharacter target : Sex.allParticipants) {
			for(Entry<PenetrationType, Set<OrificeType>> entry : ongoingPenetrationMap.get(penetrator).get(target).entrySet()) {
				if(entry.getValue().size()>=penetrationTypesAvailable) {
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
		// Reset penetration map:
		if(Sex.ongoingPenetrationMap!=null) {
			ongoingPenetrationMap.clear();
			for(GameCharacter characterPenetrating : Sex.getAllParticipants()) {
				ongoingPenetrationMap.put(characterPenetrating, new HashMap<>());
				for(GameCharacter characterPenetrated : Sex.getAllParticipants()) {
					ongoingPenetrationMap.get(characterPenetrating).put(characterPenetrated, new HashMap<>());
				}
			}
		}
		
		Sex.allParticipants = new ArrayList<>(sexManager.getDominants().keySet());
		Sex.allParticipants.addAll(sexManager.getSubmissives().keySet());
		
		// Add dominants to map, with player as the first entry:
		List<GameCharacter> tempCharacterList = new ArrayList<>(sexManager.getDominants().keySet());
		Collections.sort(tempCharacterList, (p1, p2) -> p1.isPlayer()?-1:p1.getName().compareTo(p2.getName()));
		Sex.dominants = new LinkedHashMap<>();
		for(GameCharacter character : tempCharacterList) {
			Sex.dominants.put(character, sexManager.getDominants().get(character));
		}
		if(!Sex.isDom(Main.game.getPlayer())) {
			if(tempCharacterList.isEmpty()) {
				activePartner = null;
			} else {
				activePartner = (NPC) tempCharacterList.get(0);
			}
		}

		// Add submissives to map, with player as the first entry:
		tempCharacterList = new ArrayList<>(sexManager.getSubmissives().keySet());
		Collections.sort(tempCharacterList, (p1, p2) -> p1.isPlayer()?-1:p1.getName().compareTo(p2.getName()));
		Sex.submissives = new LinkedHashMap<>();
		for(GameCharacter character : tempCharacterList) {
			Sex.submissives.put(character, sexManager.getSubmissives().get(character));
		}
		if(Sex.isDom(Main.game.getPlayer())) {
			if(tempCharacterList.isEmpty()) {
				activePartner = null;
			} else {
				activePartner = (NPC) tempCharacterList.get(0);
			}
		}
		
		Sex.sexManager = sexManager;
		
		updateAvailableActions();
		
		sexSB.append(
				"<p style='text-align:center;'><b>New position:</b> <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>"+Sex.sexManager.getPosition().getName()+"</b></br>"
				+"<i><b>"+Sex.sexManager.getPosition().getDescription()+"</b></i></p>");
	}
	
	private static void updateAvailableActions() {
		actionsAvailablePlayer.clear();
		actionsAvailablePartner.clear();
		orgasmActionsPlayer.clear();
		orgasmActionsPartner.clear();
		mutualOrgasmActions.clear();
		
		for(Entry<GameCharacter, SexPositionSlot> entry: Sex.getDominantParticipants().entrySet()) {
			if(entry.getKey().isPlayer()) {
				actionsAvailablePlayer.addAll(entry.getValue().getPlayerSlotActionsAvailablePlayer());
				actionsAvailablePartner.addAll(entry.getValue().getPlayerSlotActionsAvailablePartner());
				orgasmActionsPlayer.addAll(entry.getValue().getPlayerSlotOrgasmActionsPlayer());
				orgasmActionsPartner.addAll(entry.getValue().getPlayerSlotOrgasmActionsPartner());
				mutualOrgasmActions.addAll(entry.getValue().getPlayerSlotMutualOrgasmActions());
			} else {
				actionsAvailablePlayer.addAll(entry.getValue().getPartnerSlotActionsAvailablePlayer());
				actionsAvailablePartner.addAll(entry.getValue().getPartnerSlotActionsAvailablePartner());
				orgasmActionsPlayer.addAll(entry.getValue().getPartnerSlotOrgasmActionsPlayer());
				orgasmActionsPartner.addAll(entry.getValue().getPartnerSlotOrgasmActionsPartner());
				mutualOrgasmActions.addAll(entry.getValue().getPartnerSlotMutualOrgasmActions());
			}
		}
		for(Entry<GameCharacter, SexPositionSlot> entry: Sex.getSubmissiveParticipants().entrySet()) {
			if(entry.getKey().isPlayer()) {
				actionsAvailablePlayer.addAll(entry.getValue().getPlayerSlotActionsAvailablePlayer());
				actionsAvailablePartner.addAll(entry.getValue().getPlayerSlotActionsAvailablePartner());
				orgasmActionsPlayer.addAll(entry.getValue().getPlayerSlotOrgasmActionsPlayer());
				orgasmActionsPartner.addAll(entry.getValue().getPlayerSlotOrgasmActionsPartner());
				mutualOrgasmActions.addAll(entry.getValue().getPlayerSlotMutualOrgasmActions());
			} else {
				actionsAvailablePlayer.addAll(entry.getValue().getPartnerSlotActionsAvailablePlayer());
				actionsAvailablePartner.addAll(entry.getValue().getPartnerSlotActionsAvailablePartner());
				orgasmActionsPlayer.addAll(entry.getValue().getPartnerSlotOrgasmActionsPlayer());
				orgasmActionsPartner.addAll(entry.getValue().getPartnerSlotOrgasmActionsPartner());
				mutualOrgasmActions.addAll(entry.getValue().getPartnerSlotMutualOrgasmActions());
			}
		}
		
		if(activePartner.equals(Main.game.getLilaya())) {// TODO move to somewhere logical
			Sex.addSexActionClass(SALilayaSpecials.class);
		}
	}
	
	private static void addSexActionClass(Class<?> classToAddSexActionsFrom) {
		try {
			if(classToAddSexActionsFrom!=null) {
				Field[] fields = classToAddSexActionsFrom.getFields();
				
				for(Field f : fields){
					
					if (SexAction.class.isAssignableFrom(f.getType())) {
						if (((SexAction) f.get(null)).getActionType().isOrgasmOption()) {
							if (((SexAction) f.get(null)).getActionType() == SexActionType.MUTUAL_ORGASM) {
								mutualOrgasmActions.add(((SexAction) f.get(null)));
								
							} else if (((SexAction) f.get(null)).getActionType().isPlayerAction()) {
								orgasmActionsPlayer.add(((SexAction) f.get(null)));
								
							} else {
								orgasmActionsPartner.add(((SexAction) f.get(null)));
							}
							
						} else {
							if (((SexAction) f.get(null)).getActionType().isPlayerAction()) {
								actionsAvailablePlayer.add(((SexAction) f.get(null)));
								
							} else {
								actionsAvailablePartner.add(((SexAction) f.get(null)));
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
		return actionsAvailablePlayer;
	}

	public static Set<SexActionInterface> getActionsAvailablePartner() {
		return actionsAvailablePartner;
	}

	public static Set<SexActionInterface> getOrgasmActionsPlayer() {
		return orgasmActionsPlayer;
	}

	public static Set<SexActionInterface> getOrgasmActionsPartner() {
		return orgasmActionsPartner;
	}

	public static Set<SexActionInterface> getMutualOrgasmActions() {
		return mutualOrgasmActions;
	}
	
	public static List<SexActionInterface> getAvailableSexActionsPlayer() {
		return availableSexActionsPlayer;
	}

	public static SexActionInterface getLastUsedPlayerAction() {
		return lastUsedPlayerAction;
	}

	public static SexActionInterface getLastUsedPartnerAction() {
		return lastUsedPartnerAction;
	}
	
	public static SexPace getSexPace(GameCharacter character) {
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
	
	public static void setSexPositionSlot(GameCharacter character, SexPositionSlot slot) {
		// Check to see if character is in this sex scene:
		if(!Sex.dominants.keySet().contains(character) && !Sex.submissives.keySet().contains(character)) {
			throw new IllegalArgumentException("This character is not in this sex scene!");
		}
		
		// Check to see if this slot is available:
		boolean found = false;
		for(List<SexPositionSlot> availableSlots : getPosition().getAvailableSlots()) {
			if(availableSlots.contains(slot)) {
				found = true;
				for(Entry<GameCharacter, SexPositionSlot> e : dominants.entrySet()) {
					if(!e.getKey().equals(character)) {
						if(availableSlots.contains(e.getValue())) {
							throw new IllegalArgumentException("A dominant participant ("+character.getName()+") is already occupying this slot ("+slot+")!");
						}
					}
				}
				for(Entry<GameCharacter, SexPositionSlot> e : submissives.entrySet()) {
					if(!e.getKey().equals(character)) {
						if(availableSlots.contains(e.getValue())) {
							throw new IllegalArgumentException("A submissive participant ("+character.getName()+") is already occupying this slot ("+slot+")!");
						}
					}
				}
				break;
			}
		}
		if(!found) {
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

	public static Set<OrificeType> getAreasCurrentlyStretchingPlayer() {
		return areasCurrentlyStretchingPlayer;
	}

	public static Set<OrificeType> getAreasCurrentlyStretchingPartner() {
		return areasCurrentlyStretchingPartner;
	}

	public static Set<OrificeType> getAreasTooLoosePlayer() {
		return areasTooLoosePlayer;
	}

	public static Set<OrificeType> getAreasTooLoosePartner() {
		return areasTooLoosePartner;
	}

	public static int getNumberOfOrgasms(GameCharacter character) {
		orgasmCountMap.putIfAbsent(character, 0);
		return orgasmCountMap.get(character);
	}
	
	public static void setNumberOfOrgasms(GameCharacter character, int count) {
		orgasmCountMap.put(character, count);
	}
	
	public static void incrementNumberOfOrgasms(GameCharacter character, int increment) {
		orgasmCountMap.putIfAbsent(character, 0);
		orgasmCountMap.put(character, orgasmCountMap.get(character)+increment);
	}
	
	public static SexPositionNew getPosition() {
		return sexManager.getPosition();
	}

	public static boolean isPartnerAllowedToUseSelfActions() {
		return partnerAllowedToUseSelfActions;
	}

	public static void setPartnerAllowedToUseSelfActions(boolean partnerAllowedToUseSelfActions) {
		Sex.partnerAllowedToUseSelfActions = partnerAllowedToUseSelfActions;
	}

	public static boolean isPartnerCanRemoveOwnClothes() {
		return partnerCanRemoveOwnClothes;
	}

	public static void setPartnerCanRemoveOwnClothes(boolean partnerCanRemoveOwnClothes) {
		Sex.partnerCanRemoveOwnClothes = partnerCanRemoveOwnClothes;
	}

	public static boolean isPartnerCanRemovePlayersClothes() {
		return partnerCanRemovePlayersClothes;
	}

	public static void setPartnerCanRemovePlayersClothes(boolean partnerCanRemovePlayersClothes) {
		Sex.partnerCanRemovePlayersClothes = partnerCanRemovePlayersClothes;
	}

	public static Map<GameCharacter, SexPositionSlot> getSubmissives() {
		return submissives;
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

	public static SizedStack<SexActionInterface> getRepeatActionsPlayer() {
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
