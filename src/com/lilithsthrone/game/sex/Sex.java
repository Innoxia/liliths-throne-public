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
import java.util.stream.Collectors;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.ParserTag;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.StandardSexActionInteractions;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.game.sex.sexActions.PositioningData;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionPresets;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.SexActionUtility;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericActions;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PartnerTalk;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.BaseColour;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * Singleton enforced by Enum Call initialiseCombat() before using.
 * Then call startSex(), which returns the starting DialogueNode.
 * 
 * Lasciate ogni speranza, voi ch'entrate.
 *
 * @since 0.1.0
 * @version 0.3.4
 * @author Innoxia
 */
public class Sex {

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
	 */

	// Sex variables:

	private static StringBuilder sexSB = new StringBuilder();
	private static int turn = 1;

	private static boolean sexInitFinished;
	private static boolean sexStarted = false;
	private static boolean consensual;
	private static boolean subHasEqualControl;
	private static boolean publicSex;
	private static boolean playerUniqueActions = false; // Set to true when the player's turn consists of unique actions.
	private static boolean overridePlayerArousalRestriction; // Set to true to prevent player's arousal locking at 99 during a turn of sex. Is reset to false after every turn.

	private static Map<GameCharacter, SexSlot> dominants;
	private static Map<GameCharacter, SexSlot> submissives;
	private static List<GameCharacter> allParticipants;
	private static List<GameCharacter> dominantSpectators;
	private static List<GameCharacter> submissiveSpectators;
	private static Map<GameCharacter, GameCharacter> targetedCharacters;
	private static NPC activePartner;
	private static GameCharacter characterPerformingAction;
	private static GameCharacter characterOrgasming;
	private static SexManagerInterface sexManager;
	private static SexManagerInterface initialSexManager;
	private static String sexDescription;
	private static String unequipClothingText;
	private static String dyeClothingText;
	private static String usingItemText;
	
	private static List<SexActionInterface> availableSexActionsPlayer;
	private static List<SexActionInterface> characterSwitchActionsPlayer;
	private static List<SexActionInterface> miscActionsPlayer;
	private static List<SexActionInterface> selfActionsPlayer;
	private static List<SexActionInterface> sexActionsPlayer;
	private static List<SexActionInterface> positionActionsPlayer;
	private static List<SexActionInterface> repeatActionsPlayer;
	private static List<SexActionInterface> availableSexActionsPartner;
	
	private static Map<GameCharacter, SexPace> forceSexPaceMap;

	private static Map<GameCharacter, Set<SexAreaInterface>> initialPenetrations;
	
	// Actions that are currently available from all SexPositionSlots. First key is character whose actions they are, second key is target.
	private static Map<GameCharacter, Map<GameCharacter, Set<SexActionInterface>>> actionsAvailable;
	private static Map<GameCharacter, Map<GameCharacter, Set<SexActionInterface>>> orgasmActionsAvailable;
	
	private static DialogueNode postSexDialogue;

	private static Map<GameCharacter, SexActionInterface> lastUsedSexAction;
	
	// Tracking statuses:
	
	/**Maps: character who is lubricated -> Map of areas -> Map of owner of lubrication -> lubrications*/
	private static Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> wetAreas;
	private static Map<GameCharacter, Set<SexAreaOrifice>> areasCurrentlyStretching;
	private static Map<GameCharacter, Set<SexAreaOrifice>> areasStretched;
	private static Map<GameCharacter, Set<SexAreaOrifice>> areasTooLoose;
	
	private static Map<GameCharacter, List<CoverableArea>> areasExposed;

	/**Maps: character -> character's areas -> Map of characters mapped to sets of sexAreas that are contacting character's sexArea*/
	private static Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>>> ongoingActionsMap;
	
	// Counting for stats:
	private static Map<GameCharacter, Integer> orgasmCountMap;
	/**Maps: Characters performing denials -> Map of characters they've denied mapped to the number of times they were denied. */
	private static Map<GameCharacter, Map<GameCharacter, Integer>> deniedOrgasmsCountMap;
	private static Map<GameCharacter, Map<GameCharacter, Map<SexType, Integer>>> sexCountMap;
	private static Map<GameCharacter, Map<GameCharacter, Integer>> cummedInsideMap;

	// Positioning, requests, tracking:
	private static Map<GameCharacter, List<SexType>> requestsBlocked;
	private static Map<GameCharacter, List<AbstractSexPosition>> positioningRequestsBlocked;
	private static PositioningData positionRequest;
	private static Set<GameCharacter> charactersRequestingCreampie;
	private static Set<GameCharacter> charactersRequestingPullout;
	private static Set<GameCharacter> charactersSealed;
	private static Set<GameCharacter> charactersBannedFromPositioning;
	private static Set<GameCharacter> charactersForbiddenByOthersFromPositioning;
	private static Set<GameCharacter> charactersSelfActionsBlocked;
	private static Set<GameCharacter> charactersDeniedOrgasm;
	private static Map<GameCharacter, SexControl> forcedSexControlMap;

	private static Set<GameCharacter> charactersGrewCock;

	private static Value<GameCharacter, Class<? extends BodyPartInterface>> creampieLockedBy;
	
	private static Set<GameCharacter> removeEndSexAffection;
	
	// Clothes:

	private static Set<GameCharacter> charactersBannedFromRemovingSelfClothing;
	private static Set<GameCharacter> charactersBannedFromRemovingOthersClothing;
	
	private static AbstractClothing clothingBeingRemoved;
	
	private static Map<GameCharacter, Map<InventorySlot, Map<AbstractClothing, List<DisplacementType>>>> clothingPreSexMap;
	
	private static boolean sexFinished;
	
	private static AbstractClothing selectedClothing;
	

	public Sex() {
	}

	public DialogueNode initialiseSex(
			boolean consensual,
			boolean subHasEqualControl,
			SexManagerInterface sexManager,
			DialogueNode postSexDialogue,
			String sexStartDescription) {
		return initialiseSex(consensual,
				subHasEqualControl,
				sexManager,
				new ArrayList<>(),
				new ArrayList<>(),
				postSexDialogue,
				sexStartDescription,
				new ArrayList<>());
	}
	public DialogueNode initialiseSex(
			boolean consensual,
			boolean subHasEqualControl,
			SexManagerInterface sexManager,
			List<GameCharacter> dominantSpectators,
			List<GameCharacter> submissiveSpectators,
			DialogueNode postSexDialogue,
			String sexStartDescription) {
		return initialiseSex(consensual,
				subHasEqualControl,
				sexManager,
				dominantSpectators,
				submissiveSpectators,
				postSexDialogue,
				sexStartDescription,
				new ArrayList<>());
	}
	
	public DialogueNode initialiseSex(
			boolean consensual,
			boolean subHasEqualControl,
			SexManagerInterface sexManager,
			List<GameCharacter> dominantSpectators,
			List<GameCharacter> submissiveSpectators,
			DialogueNode postSexDialogue,
			String sexStartDescription,
			List<InitialSexActionInformation> startingSexActions) {
		
		sexInitFinished = false;
		overridePlayerArousalRestriction = false;
		turn = 1;
		
		SexFlags.reset();
		
		Sex.consensual = consensual;
		Sex.subHasEqualControl = subHasEqualControl;
		
		// Remove spectators that are defined as participants:
		List<GameCharacter> trimmedDomSpectators = new ArrayList<>(dominantSpectators);
		trimmedDomSpectators.removeAll(sexManager.getDominants().keySet());
		trimmedDomSpectators.removeAll(sexManager.getSubmissives().keySet());
		Sex.dominantSpectators = trimmedDomSpectators;

		List<GameCharacter> trimmedSubSpectators = new ArrayList<>(submissiveSpectators);
		trimmedSubSpectators.removeAll(sexManager.getSubmissives().keySet());
		trimmedSubSpectators.removeAll(sexManager.getDominants().keySet());
		Sex.submissiveSpectators = trimmedSubSpectators;
		
		actionsAvailable = new HashMap<>();
		orgasmActionsAvailable = new HashMap<>();
		initialPenetrations = new HashMap<>();

		availableSexActionsPlayer = new ArrayList<>();
		characterSwitchActionsPlayer = new ArrayList<>();
		miscActionsPlayer = new ArrayList<>();
		selfActionsPlayer = new ArrayList<>();
		sexActionsPlayer = new ArrayList<>();
		positionActionsPlayer = new ArrayList<>();
		repeatActionsPlayer = new LinkedList<>();
		availableSexActionsPartner = new ArrayList<>();
		
		targetedCharacters = new HashMap<>();
		
		forceSexPaceMap = new HashMap<>();
		
		sexFinished = false;
		
		orgasmCountMap = new HashMap<>();
		deniedOrgasmsCountMap = new HashMap<>();
		sexCountMap = new HashMap<>();
		cummedInsideMap = new HashMap<>();
		creampieLockedBy = null;
		
		setSexManager(sexManager);
		initialSexManager = sexManager;
		characterPerformingAction = Main.game.getPlayer();
		characterOrgasming = null;
		
		publicSex = sexManager.isPublicSex();
		
		Sex.postSexDialogue = postSexDialogue;
		
		sexStarted = false;

		resetAllOngoingActions(true);

		
		lastUsedSexAction = new HashMap<>();
		for(GameCharacter character : Sex.getAllParticipants()) {
			sexCountMap.put(character, new HashMap<>());
			if(character.isPlayer()) {
				lastUsedSexAction.put(character, SexActionUtility.PLAYER_NONE);
			} else {
				lastUsedSexAction.put(character, SexActionUtility.PARTNER_NONE);
			}
		}
		
		for(GameCharacter character : Sex.getAllParticipants()) {
			for(GameCharacter character2 : Sex.getAllParticipants()) {
				character.addSexPartner(character2);
			}
		}
		
		for(GameCharacter character : Sex.getAllParticipants()) {
			for(GameCharacter partner : Sex.getAllParticipants()) {
				if(isConsensual()) {
					character.setSexConsensualCount(partner, character.getSexConsensualCount(partner)+1);
				}
				if(isDom(character)) {
					character.setSexAsDomCount(partner, character.getSexAsDomCount(partner)+1);
				} else {
					character.setSexAsSubCount(partner, character.getSexAsSubCount(partner)+1);
				}
			}
		}

		// Populate exposed areas:
		areasExposed = new HashMap<>();
		requestsBlocked = new HashMap<>();
		positioningRequestsBlocked = new HashMap<>();
		for(GameCharacter character : Sex.getAllParticipants()) {
			areasExposed.put(character, new ArrayList<>());
			requestsBlocked.put(character, new ArrayList<>());
			positioningRequestsBlocked.put(character, new ArrayList<>());
		}
		
		positionRequest = null;
		
		charactersRequestingCreampie = new HashSet<>();
		charactersRequestingPullout = new HashSet<>();
		
		charactersSealed = new HashSet<>(sexManager.getCharactersSealed());
		charactersBannedFromPositioning = new HashSet<>();
		
		charactersSelfActionsBlocked = new HashSet<>();
		charactersDeniedOrgasm = new HashSet<>();
		charactersGrewCock = new HashSet<>();
		
		removeEndSexAffection = new HashSet<>();
		forcedSexControlMap = new HashMap<>();
		for(GameCharacter character : Sex.getAllParticipants(true)) {
			if(!Sex.sexManager.isEndSexAffectionChangeEnabled(character)) {
				removeEndSexAffection.add(character);
			}
			forcedSexControlMap.put(character, null);
		}

		charactersForbiddenByOthersFromPositioning = new HashSet<>();
		for(GameCharacter character : Sex.getAllParticipants()) {
			if(!character.isPlayer() && (Sex.getSexControl(character)!=SexControl.FULL || !Sex.isDom(character))) {
				charactersForbiddenByOthersFromPositioning.add(character);
			}
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
				for(GameCharacter target : Sex.getAllParticipants()) {
					if(!target.equals(character) && Sex.getSexPositionSlot(target)!=SexSlotGeneric.MISC_WATCHING) {
						((NPC)character).generateSexChoices(false, target, null);
					}
				}
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
						character.setLustNoText(10);
						break;
					case DOM_NORMAL:
						character.setArousal(5);
						break;
					case DOM_ROUGH:
						character.setLustNoText(85);
						character.setArousal(10);
						break;
					case SUB_EAGER:
						character.setLustNoText(85);
						character.setArousal(10);
						break;
					case SUB_NORMAL:
						character.setLustNoText(Math.max(character.getLust(), 10));
						character.setArousal(5);
						break;
					case SUB_RESISTING:
						character.setLustNoText(0);
						break;
				}
			}

			if(sexManager.getForcedSexPace(character)!=null) {
				forceSexPaceMap.put(character, sexManager.getForcedSexPace(character));
				switch(sexManager.getForcedSexPace(character)) {
					case DOM_GENTLE:
						character.setLustNoText(10);
						break;
					case DOM_NORMAL:
						character.setArousal(5);
						break;
					case DOM_ROUGH:
						character.setLustNoText(85);
						character.setArousal(10);
						break;
					case SUB_EAGER:
						character.setLustNoText(85);
						character.setArousal(10);
						break;
					case SUB_NORMAL:
						character.setLustNoText(Math.max(character.getLust(), 10));
						character.setArousal(5);
						break;
					case SUB_RESISTING:
						character.setLustNoText(0);
						break;
				}
			}
		}
		
		// Set starting wetness values:
		wetAreas = new HashMap<>();
		
		for(GameCharacter character : Sex.getAllParticipants()) {
			wetAreas.putIfAbsent(character, new HashMap<>());
			
			for(GameCharacter characterLubricationOwner : Sex.getAllParticipants()) {
				for(SexAreaPenetration penetration : SexAreaPenetration.values()) {
					wetAreas.get(character).putIfAbsent(penetration, new HashMap<>());
					wetAreas.get(character).get(penetration).put(characterLubricationOwner, new HashSet<>());
				}
				for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
					wetAreas.get(character).putIfAbsent(orifice, new HashMap<>());
					wetAreas.get(character).get(orifice).put(characterLubricationOwner, new HashSet<>());
				}
			}
			for(SexAreaPenetration penetration : SexAreaPenetration.values()) {
				wetAreas.get(character).putIfAbsent(penetration, new HashMap<>());
				wetAreas.get(character).get(penetration).put(null, new HashSet<>());
			}
			for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
				wetAreas.get(character).putIfAbsent(orifice, new HashMap<>());
				wetAreas.get(character).get(orifice).put(null, new HashSet<>());
			}
		}
		
		// Starting text:
		sexSB = new StringBuilder(sexStartDescription);

		sexSB.append(sexManager.getStartSexDescription());
		
		if(Sex.isPublicSex()) {
			sexSB.append(Sex.getInitialSexManager().getPublicSexStartingDescription());
		}

		sexSB.append("<p style='text-align:center;'><b>Starting Position:</b> <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>"+sexManager.getPosition().getName()+"</b><br/>"
				+"<i><b>"+sexManager.getPosition().getDescription(Sex.getAllOccupiedSlots(false))+"</b></i></p>");
		
		sexSB.append(calculateWetAreas(true));
		
		charactersBannedFromRemovingSelfClothing = new HashSet<>();
		charactersBannedFromRemovingOthersClothing = new HashSet<>();
		for(GameCharacter character : Sex.getAllParticipants()) {
			if(Sex.getSexControl(character).getValue()<SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS.getValue()) {
				charactersBannedFromRemovingOthersClothing.add(character);
			}
		}
		
		
		// Store status of all clothes for both partners (so they can be restored afterwards):
		clothingPreSexMap = new HashMap<>();
		
		for(GameCharacter character : Sex.getAllParticipants()) {
			clothingPreSexMap.put(character, new HashMap<>());
			for (AbstractClothing c : character.getClothingCurrentlyEquipped()) {
				clothingPreSexMap.get(character).put(c.getSlotEquippedTo(), new HashMap<>());
				clothingPreSexMap.get(character).get(c.getSlotEquippedTo()).put(c, new ArrayList<>(c.getDisplacedList()));
			}
		}
		
		Map<GameCharacter, Map<AbstractClothing, DisplacementType>> displacedClothing = new HashMap<>();
		if(sexManager.exposeAtStartOfSexMapExtendedInformation().get(false)!=null) {
			for(Entry<GameCharacter, Map<CoverableArea, List<InventorySlot>>> entry : sexManager.exposeAtStartOfSexMapExtendedInformation().get(false).entrySet()) {
				displacedClothing.putIfAbsent(entry.getKey(), new HashMap<>());
				for(CoverableArea ca : entry.getValue().keySet()) {
					for(Entry<AbstractClothing, DisplacementType> e : entry.getKey().displaceClothingForAccess(ca, entry.getValue().get(ca), false).entrySet()) {
						displacedClothing.get(entry.getKey()).put(e.getKey(), e.getValue());
					}
				}
			}
		}
		if(sexManager.exposeAtStartOfSexMapExtendedInformation().get(true)!=null) {
			for(Entry<GameCharacter, Map<CoverableArea, List<InventorySlot>>> entry : sexManager.exposeAtStartOfSexMapExtendedInformation().get(true).entrySet()) {
				displacedClothing.putIfAbsent(entry.getKey(), new HashMap<>());
				for(CoverableArea ca : entry.getValue().keySet()) {
					for(Entry<AbstractClothing, DisplacementType> e : entry.getKey().displaceClothingForAccess(ca, entry.getValue().get(ca), true).entrySet()) {
						displacedClothing.get(entry.getKey()).put(e.getKey(), e.getValue());
					}
				}
			}
		}
		// Make sure all removed clothing is placed onto the floor:
		for(Entry<GameCharacter, Map<AbstractClothing, DisplacementType>> entry : displacedClothing.entrySet()) {
			for(Entry<AbstractClothing, DisplacementType> e : entry.getValue().entrySet()) {
				if(!entry.getKey().getClothingCurrentlyEquipped().contains(e.getKey())) {
					Main.game.getPlayerCell().getInventory().addClothing(e.getKey());
//					System.out.println(entry.getKey().getName()+" "+e.getKey().getName());
				}
			}
		}

//		Main.game.setInSex(true); Moved to setSexmanager()

		List<AbstractClothing> clothingToStrip = new ArrayList<>();


		for(GameCharacter character : Sex.getAllParticipants()) {
			if(sexManager.isCharacterStartNaked(character)) {
				clothingToStrip.clear();
				clothingToStrip.addAll(character.getClothingCurrentlyEquipped());
				for (AbstractClothing c : clothingToStrip) {
					character.unequipClothingOntoFloor(c, true, character);
				}
			}
		}
		
		// Starting exposed:
		for(GameCharacter character : Sex.getAllParticipants()) {
			if(Sex.getSexPositionSlot(character)!=SexSlotGeneric.MISC_WATCHING) {
				if(sexManager.isAppendStartingExposedDescriptions(character)) {
					sexSB.append(handleExposedDescriptions(character, true));
				} else {
					handleExposedDescriptions(character, true);
				}
			}
		}
		
		StringBuilder initialSexActionSB = new StringBuilder();
		for(InitialSexActionInformation sexAction : startingSexActions) {
			Sex.setCharacterPerformingAction(sexAction.getPerformer());
			Sex.setTargetedPartner(sexAction.getPerformer(), sexAction.getTarget());
			if(sexAction.getPerformer().isPlayer()) {//TODO sort out the active partner shit. Why is the player being treated as having a special partner?
				Sex.setActivePartner((NPC) sexAction.getTarget());
			}
			
			initialSexActionSB.setLength(0);
			if(sexAction.isAppendDescription()) {
				initialSexActionSB.append("<p>"
											+ sexAction.getSexAction().preDescriptionBaseEffects()
											+ sexAction.getSexAction().getDescription()
											+ sexAction.getSexAction().getFluidFlavourDescription(sexAction.getPerformer(), sexAction.getTarget())
										+ "</p>");
			}
			String endString = sexAction.getSexAction().baseEffects();
			if(sexAction.isAppendEffects()) {
				initialSexActionSB.append(applyGenericDescriptionsAndEffects(sexAction.getPerformer(), sexAction.getTarget(), sexAction.getSexAction()));
				initialSexActionSB.append(endString);
				sexAction.getSexAction().applyEndEffects();
			}
			if(initialSexActionSB.length()>0) {
				sexSB.append(UtilText.parse(sexAction.getPerformer(), sexAction.getTarget(), initialSexActionSB.toString(), ParserTag.SEX_DESCRIPTION));
			}
		}
		
		sexDescription = sexSB.toString();
		
		Sex.setCharacterPerformingAction(Main.game.getPlayer());

		// Populate available SexAction list:
		populatePlayerSexLists();

		sexInitFinished = true;
		
		return SEX_DIALOGUE;
	}

	private static void endSex() {
		Main.game.setInSex(false);
		
		// Restore clothes:
		for(Entry<GameCharacter, Map<InventorySlot, Map<AbstractClothing, List<DisplacementType>>>> entry : clothingPreSexMap.entrySet()) {
			GameCharacter character = entry.getKey();
			if(character.isUnique() && !character.isPlayer()) { // Backup for unique NPCs, as they shouldn't be able to have clothing put on them during sex:
				List<AbstractClothing> equippedClothing = new ArrayList<>(character.getClothingCurrentlyEquipped());
				for(AbstractClothing c : equippedClothing) {
					AbstractClothing clean = new AbstractClothing(c) {};
					clean.setDirty(null, false);
					if(!entry.getValue().get(c.getSlotEquippedTo()).keySet().contains(c) && !entry.getValue().get(c.getSlotEquippedTo()).keySet().contains(clean)) {
						character.forceUnequipClothingIntoVoid(character, c);
						character.getCell().getInventory().addClothing(c);
					}
				}
			}
			
			for (Entry<InventorySlot, Map<AbstractClothing, List<DisplacementType>>> entry2 : entry.getValue().entrySet()) {
				for (AbstractClothing c : entry2.getValue().keySet()) {
					if(!c.getClothingType().isDiscardedOnUnequip(entry2.getKey())) {
						AbstractClothing dirtyClone = new AbstractClothing(c) {};
						dirtyClone.setDirty(null, true);
						AbstractClothing clothingEquipped = character.getClothingInSlot(entry2.getKey());
						if (clothingEquipped==null) {
							// Only re-equip if that slot is empty, as some endSex methods force clothing on the player:
							if(character.getCell().getInventory().hasClothing(c) || character.hasClothing(c)) {
								character.equipClothingOverride(c, entry2.getKey(), false, true);
							} else if(character.getCell().getInventory().hasClothing(dirtyClone) || character.hasClothing(dirtyClone)) {
								character.equipClothingOverride(dirtyClone, entry2.getKey(), false, true);
							}
						}
						if(Main.getProperties().hasValue(PropertyValue.autoSexClothingManagement)) {
							for(AbstractClothing clothing : new ArrayList<>(character.getClothingCurrentlyEquipped())) {
								if(clothing.getClothingType().equals(c.getClothingType())) {
									clothing.getDisplacedList().clear();
									if(entry2.getValue().get(c)!=null) {
										for(DisplacementType displacement : entry2.getValue().get(c)) {
											character.isAbleToBeDisplaced(clothing, displacement, true, true, character);
										}
									}
								}
							}
							
						} else if(character.getCell().getInventory().hasClothing(c)) { // Try to pick up their clothing if it's on the floor:
							character.addClothing(c, true);
							
						} else if(character.getCell().getInventory().hasClothing(dirtyClone)) { // Try to pick up their clothing if it's on the floor:
							character.addClothing(dirtyClone, true);
						}
					}
				}
			}
		}

		for(GameCharacter participant : Sex.getAllParticipants()) {
			if(!Sex.isSpectator(participant) && !Sex.isMasturbation()) {
				boolean satisfiedPartners = false;
				if(Sex.isDom(participant)) {
					for(GameCharacter sub : Sex.getSubmissiveParticipants(false).keySet()) {
						if(Sex.getNumberOfOrgasms(sub)>=sub.getOrgasmsBeforeSatisfied()) {
							satisfiedPartners = true;
							break;
						}
					}
				} else {
					for(GameCharacter dom : Sex.getDominantParticipants(false).keySet()) {
						if(Sex.getNumberOfOrgasms(dom)>=dom.getOrgasmsBeforeSatisfied()) {
							satisfiedPartners = true;
							break;
						}
					}
				}
				if(satisfiedPartners) {
					// Pure virgin gains experience by having their virginities intact at the end of sex:
					if(participant.hasVagina() && participant.isVaginaVirgin()) {
						participant.incrementFetishExperience(Fetish.FETISH_PURE_VIRGIN, Fetish.FETISH_PURE_VIRGIN.getExperienceGainFromSexAction()/3);
					}
					if(participant.hasPenis() && participant.isPenisVirgin()) {
						participant.incrementFetishExperience(Fetish.FETISH_PURE_VIRGIN, Fetish.FETISH_PURE_VIRGIN.getExperienceGainFromSexAction()/3);
					}
					if(participant.isAssVirgin()) {
						participant.incrementFetishExperience(Fetish.FETISH_PURE_VIRGIN, Fetish.FETISH_PURE_VIRGIN.getExperienceGainFromSexAction()/6);
					}
					if(participant.isFaceVirgin()) {
						participant.incrementFetishExperience(Fetish.FETISH_PURE_VIRGIN, Fetish.FETISH_PURE_VIRGIN.getExperienceGainFromSexAction()/6);
					}

					// Lusty maiden gains experience from retaining vaginal virginity, while having had their anus or mouth penetrated, or performed hotdogging or paizuri/naizuri:
					if(participant.hasVagina() && participant.isVaginaVirgin()) {
						lusty:
						for(Map<SexType, Integer> entry : sexCountMap.get(participant).values()) {
							for(SexType st : entry.keySet()) {
								if(((st.getPerformingSexArea()==SexAreaOrifice.ANUS || st.getPerformingSexArea()==SexAreaOrifice.MOUTH) && st.getTargetedSexArea().isPenetration() && ((SexAreaPenetration)st.getTargetedSexArea()).isTakesVirginity())
										|| ((st.getPerformingSexArea()==SexAreaOrifice.ASS || st.getPerformingSexArea()==SexAreaOrifice.BREAST) && st.getTargetedSexArea()==SexAreaPenetration.PENIS)) {
									participant.incrementFetishExperience(Fetish.FETISH_LUSTY_MAIDEN, Fetish.FETISH_LUSTY_MAIDEN.getExperienceGainFromSexAction());
									break lusty;
								}
							}
						}
					}
				}
			}
			if(participant instanceof NPC) {
				((NPC) participant).setLastTimeHadSex(Main.game.getMinutesPassed(), Sex.getNumberOfOrgasms(participant)>0);
				((NPC)participant).endSex();
			}
			
			if(getNumberOfOrgasms(participant) > 0) {
				participant.setLustNoText(0);
			}
		}
		
		for(GameCharacter character : Sex.getCharactersGrewCock()) {
			character.setPenisType(PenisType.NONE);
		}
	}

	private static String endSexDescription;
	
	private static String getEndSexStretchingDescription(GameCharacter participant) {
		StringBuilder sb = new StringBuilder();

		// Asshole:
		if (participant.getAssRawCapacityValue() != participant.getAssStretchedCapacity() && areasStretched.get(participant).contains(SexAreaOrifice.ANUS)) {
			if (participant.getAssPlasticity() == OrificePlasticity.ZERO_RUBBERY){
				participant.setAssStretchedCapacity(participant.getAssRawCapacityValue());
				sb.append("<p style='text-align:center;'>[npc.NamePos] [style.italicsPlasticity(stretched)] [style.italicsAsshole(asshole)] quickly recovers from its ordeal, [style.italicsExcellent(instantly regaining all of its tightness)]!</p>");
				
			} else if (participant.getAssPlasticity().getCapacityIncreaseModifier()>=1){
				participant.setAssCapacity(participant.getAssStretchedCapacity(), true);
				sb.append("<p style='text-align:center;'><i>[npc.NamePos] [style.italicsAsshole(asshole)] has been"
								+ " [style.italicsTerrible(permanently)] [style.italicsPlasticity(stretched)] into being"
								+ " [style.italicsPlasticity("+ Capacity.getCapacityFromValue(participant.getAssRawCapacityValue()).getDescriptor() +")]!</i></p>");
				
			} else {
				// Increment core capacity by the Plasticity's capacityIncreaseModifier:
				participant.incrementAssCapacity((participant.getAssStretchedCapacity()-participant.getAssRawCapacityValue())*participant.getAssPlasticity().getCapacityIncreaseModifier(), false);

				sb.append("<p style='text-align:center;'><i>[npc.NamePos] "+ participant.getAssElasticity().getDescriptor()
						+" [style.italicsAsshole(asshole)] has been [style.italicsPlasticity(stretched)] from its ordeal, and is currently"
						+ " [style.italicsPlasticity("+ Capacity.getCapacityFromValue(participant.getAssStretchedCapacity()).getDescriptor() + ")]!");

				if(participant.getAssPlasticity().getCapacityIncreaseModifier()>0) {
					sb.append("<br/>It will recover [style.italicsMinorBad(only some)] of its original size, eventually tightening back to being"
							+ " [style.italicsPlasticity(" + Capacity.getCapacityFromValue(participant.getAssRawCapacityValue()).getDescriptor() + ")]!</i></p>");
				} else {
					sb.append("<br/>It will recover [style.italicsMinorGood(all)] of its original size, eventually tightening back to being"
							+ " [style.italicsPlasticity(" + Capacity.getCapacityFromValue(participant.getAssRawCapacityValue()).getDescriptor() + ")]!</i></p>");
				}
			}
		}
		// Vagina:
		if (participant.getVaginaRawCapacityValue() != participant.getVaginaStretchedCapacity() && areasStretched.get(participant).contains(SexAreaOrifice.VAGINA)) {
			if (participant.getVaginaPlasticity() == OrificePlasticity.ZERO_RUBBERY){
				participant.setVaginaStretchedCapacity(participant.getVaginaRawCapacityValue());
				sb.append("<p style='text-align:center;'>[npc.NamePos] [style.italicsPlasticity(stretched)] [style.italicsVagina(pussy)] quickly recovers from its ordeal, [style.italicsExcellent(instantly regaining all of its tightness)]!</p>");
				
			} else if (participant.getVaginaPlasticity().getCapacityIncreaseModifier()>=1){
				participant.setVaginaCapacity(participant.getVaginaStretchedCapacity(), true);
				sb.append("<p style='text-align:center;'><i>[npc.NamePos] [style.italicsVagina(pussy)] has been"
								+ " [style.italicsTerrible(permanently)] [style.italicsPlasticity(stretched)] into being"
								+ " [style.italicsPlasticity("+ Capacity.getCapacityFromValue(participant.getVaginaRawCapacityValue()).getDescriptor() +")]!</i></p>");
				
			} else {
				// Increment core capacity by the Plasticity's capacityIncreaseModifier:
				participant.incrementVaginaCapacity((participant.getVaginaStretchedCapacity()-participant.getVaginaRawCapacityValue())*participant.getVaginaPlasticity().getCapacityIncreaseModifier(), false);

				sb.append("<p style='text-align:center;'><i>[npc.NamePos] "+ participant.getVaginaElasticity().getDescriptor()
						+" [style.italicsVagina(pussy)] has been [style.italicsPlasticity(stretched)] from its ordeal, and is currently"
						+ " [style.italicsPlasticity("+ Capacity.getCapacityFromValue(participant.getVaginaStretchedCapacity()).getDescriptor() + ")]!");

				if(participant.getVaginaPlasticity().getCapacityIncreaseModifier()>0) {
					sb.append("<br/>It will recover [style.italicsMinorBad(only some)] of its original size, eventually tightening back to being"
							+ " [style.italicsPlasticity(" + Capacity.getCapacityFromValue(participant.getVaginaRawCapacityValue()).getDescriptor() + ")]!</i></p>");
				} else {
					sb.append("<br/>It will recover [style.italicsMinorGood(all)] of its original size, eventually tightening back to being"
							+ " [style.italicsPlasticity(" + Capacity.getCapacityFromValue(participant.getVaginaRawCapacityValue()).getDescriptor() + ")]!</i></p>");
				}
			}
		}
		// Nipples:
		if (participant.getNippleRawCapacityValue() != participant.getNippleStretchedCapacity() && areasStretched.get(participant).contains(SexAreaOrifice.NIPPLE)) {
			if (participant.getNipplePlasticity() == OrificePlasticity.ZERO_RUBBERY){
				participant.setNippleStretchedCapacity(participant.getNippleRawCapacityValue());
				sb.append("<p style='text-align:center;'>[npc.NamePos] [style.italicsPlasticity(stretched)] [style.italicsNipples(nipples)] quickly recovers from their ordeal, [style.italicsExcellent(instantly regaining all of its tightness)]!</p>");
				
			} else if (participant.getNipplePlasticity().getCapacityIncreaseModifier()>=1){
				participant.setNippleCapacity(participant.getNippleStretchedCapacity(), true);
				sb.append("<p style='text-align:center;'><i>[npc.NamePos] [style.italicsNipples(nipples)] have been"
								+ " [style.italicsTerrible(permanently)] [style.italicsPlasticity(stretched)] into being"
								+ " [style.italicsPlasticity("+ Capacity.getCapacityFromValue(participant.getNippleRawCapacityValue()).getDescriptor() +")]!</i></p>");
				
			} else {
				// Increment core capacity by the Plasticity's capacityIncreaseModifier:
				participant.incrementNippleCapacity((participant.getNippleStretchedCapacity()-participant.getNippleRawCapacityValue())*participant.getNipplePlasticity().getCapacityIncreaseModifier(), false);

				sb.append("<p style='text-align:center;'><i>[npc.NamePos] "+ participant.getNippleElasticity().getDescriptor()
						+" [style.italicsNipples(nipples)] have been [style.italicsPlasticity(stretched)] from their ordeal, and are currently"
						+ " [style.italicsPlasticity("+ Capacity.getCapacityFromValue(participant.getNippleStretchedCapacity()).getDescriptor() + ")]!");

				if(participant.getNipplePlasticity().getCapacityIncreaseModifier()>0) {
					sb.append("<br/>They will recover [style.italicsMinorBad(only some)] of their original size, eventually tightening back to being"
							+ " [style.italicsPlasticity(" + Capacity.getCapacityFromValue(participant.getNippleRawCapacityValue()).getDescriptor() + ")]!</i></p>");
				} else {
					sb.append("<br/>They will recover [style.italicsMinorGood(all)] of their original size, eventually tightening back to being"
							+ " [style.italicsPlasticity(" + Capacity.getCapacityFromValue(participant.getNippleRawCapacityValue()).getDescriptor() + ")]!</i></p>");
				}
			}
		}
		// Nipples:
		if (participant.getNippleCrotchRawCapacityValue() != participant.getNippleCrotchStretchedCapacity() && areasStretched.get(participant).contains(SexAreaOrifice.NIPPLE_CROTCH)) {
			if (participant.getNippleCrotchPlasticity() == OrificePlasticity.ZERO_RUBBERY){
				participant.setNippleCrotchStretchedCapacity(participant.getNippleCrotchRawCapacityValue());
				sb.append("<p style='text-align:center;'>[npc.NamePos] [style.italicsPlasticity(stretched)] [style.italicsNippleCrotch(crotch-nipples)] quickly recovers from their ordeal,"
						+ " [style.italicsExcellent(instantly regaining all of its tightness)]!</p>");
				
			} else if (participant.getNippleCrotchPlasticity().getCapacityIncreaseModifier()>=1){
				participant.setNippleCrotchCapacity(participant.getNippleCrotchStretchedCapacity(), true);
				sb.append("<p style='text-align:center;'><i>[npc.NamePos] [style.italicsNippleCrotch(crotch-nipples)] have been"
								+ " [style.italicsTerrible(permanently)] [style.italicsPlasticity(stretched)] into being"
								+ " [style.italicsPlasticity("+ Capacity.getCapacityFromValue(participant.getNippleCrotchRawCapacityValue()).getDescriptor() +")]!</i></p>");
				
			} else {
				// Increment core capacity by the Plasticity's capacityIncreaseModifier:
				participant.incrementNippleCrotchCapacity((participant.getNippleCrotchStretchedCapacity()-participant.getNippleCrotchRawCapacityValue())*participant.getNippleCrotchPlasticity().getCapacityIncreaseModifier(), false);

				sb.append("<p style='text-align:center;'><i>[npc.NamePos] "+ participant.getNippleCrotchElasticity().getDescriptor()
						+" [style.italicsNippleCrotch(crotch-nipples)] have been [style.italicsPlasticity(stretched)] from their ordeal, and are currently"
						+ " [style.italicsPlasticity("+ Capacity.getCapacityFromValue(participant.getNippleCrotchStretchedCapacity()).getDescriptor() + ")]!");

				if(participant.getNippleCrotchPlasticity().getCapacityIncreaseModifier()>0) {
					sb.append("<br/>They will recover [style.italicsMinorBad(only some)] of their original size, eventually tightening back to being"
							+ " [style.italicsPlasticity(" + Capacity.getCapacityFromValue(participant.getNippleCrotchRawCapacityValue()).getDescriptor() + ")]!</i></p>");
				} else {
					sb.append("<br/>They will recover [style.italicsMinorGood(all)] of their original size, eventually tightening back to being"
							+ " [style.italicsPlasticity(" + Capacity.getCapacityFromValue(participant.getNippleCrotchRawCapacityValue()).getDescriptor() + ")]!</i></p>");
				}
			}
		}
		// Penis urethra:
		if (participant.getPenisRawCapacityValue() != participant.getPenisStretchedCapacity() && areasStretched.get(participant).contains(SexAreaOrifice.URETHRA_PENIS)) {
			if (participant.getUrethraPlasticity() == OrificePlasticity.ZERO_RUBBERY){
				participant.setPenisStretchedCapacity(participant.getPenisRawCapacityValue());
				sb.append("<p style='text-align:center;'>[npc.NamePos] [style.italicsPlasticity(stretched)] [style.italicsPenisUrethra(penile urethra)] quickly recovers from its ordeal,"
						+ " [style.italicsExcellent(instantly regaining all of its tightness)]!</p>");
				
			} else if (participant.getUrethraPlasticity().getCapacityIncreaseModifier()>=1){
				participant.setPenisCapacity(participant.getPenisStretchedCapacity(), true);
				sb.append("<p style='text-align:center;'><i>[npc.NamePos] [style.italicsPenisUrethra(penile urethra)] has been"
								+ " [style.italicsTerrible(permanently)] [style.italicsPlasticity(stretched)] into being"
								+ " [style.italicsPlasticity("+ Capacity.getCapacityFromValue(participant.getPenisRawCapacityValue()).getDescriptor() +")]!</i></p>");
				
			} else {
				// Increment core capacity by the Plasticity's capacityIncreaseModifier:
				participant.incrementPenisCapacity((participant.getPenisStretchedCapacity()-participant.getPenisRawCapacityValue())*participant.getUrethraPlasticity().getCapacityIncreaseModifier(), false);

				sb.append("<p style='text-align:center;'><i>[npc.NamePos] "+ participant.getUrethraElasticity().getDescriptor()
						+" [style.italicsPenisUrethra(penile urethra)] has been [style.italicsPlasticity(stretched)] from its ordeal, and is currently"
						+ " [style.italicsPlasticity("+ Capacity.getCapacityFromValue(participant.getPenisStretchedCapacity()).getDescriptor() + ")]!");

				if(participant.getUrethraPlasticity().getCapacityIncreaseModifier()>0) {
					sb.append("<br/>It will recover [style.italicsMinorBad(only some)] of its original size, eventually tightening back to being"
							+ " [style.italicsPlasticity(" + Capacity.getCapacityFromValue(participant.getPenisRawCapacityValue()).getDescriptor() + ")]!</i></p>");
				} else {
					sb.append("<br/>It will recover [style.italicsMinorGood(all)] of its original size, eventually tightening back to being"
							+ " [style.italicsPlasticity(" + Capacity.getCapacityFromValue(participant.getPenisRawCapacityValue()).getDescriptor() + ")]!</i></p>");
				}
			}
		}
		// Vaginal urethra:
		if (participant.getVaginaUrethraRawCapacityValue() != participant.getVaginaUrethraStretchedCapacity() && areasStretched.get(participant).contains(SexAreaOrifice.URETHRA_VAGINA)) {
			if (participant.getVaginaUrethraPlasticity() == OrificePlasticity.ZERO_RUBBERY){
				participant.setVaginaUrethraStretchedCapacity(participant.getVaginaUrethraRawCapacityValue());
				sb.append("<p style='text-align:center;'>[npc.NamePos] [style.italicsPlasticity(stretched)] [style.italicsVaginaUrethra(vaginal urethra)] quickly recovers from its ordeal,"
						+ " [style.italicsExcellent(instantly regaining all of its tightness)]!</p>");
				
			} else if (participant.getVaginaUrethraPlasticity().getCapacityIncreaseModifier()>=1){
				participant.setVaginaUrethraCapacity(participant.getVaginaUrethraStretchedCapacity(), true);
				sb.append("<p style='text-align:center;'><i>[npc.NamePos] [style.italicsVaginaUrethra(vaginal urethra)] has been"
								+ " [style.italicsTerrible(permanently)] [style.italicsPlasticity(stretched)] into being"
								+ " [style.italicsPlasticity("+ Capacity.getCapacityFromValue(participant.getVaginaUrethraRawCapacityValue()).getDescriptor() +")]!</i></p>");
				
			} else {
				// Increment core capacity by the Plasticity's capacityIncreaseModifier:
				participant.incrementVaginaUrethraCapacity((participant.getVaginaUrethraStretchedCapacity()-participant.getVaginaUrethraRawCapacityValue())*participant.getVaginaUrethraPlasticity().getCapacityIncreaseModifier(), false);

				sb.append("<p style='text-align:center;'><i>[npc.NamePos] "+ participant.getVaginaUrethraElasticity().getDescriptor()
						+" [style.italicsVaginaUrethra(vaginal urethra)] has been [style.italicsPlasticity(stretched)] from its ordeal, and is currently"
						+ " [style.italicsPlasticity("+ Capacity.getCapacityFromValue(participant.getVaginaUrethraStretchedCapacity()).getDescriptor() + ")]!");

				if(participant.getVaginaUrethraPlasticity().getCapacityIncreaseModifier()>0) {
					sb.append("<br/>It will recover [style.italicsMinorBad(only some)] of its original size, eventually tightening back to being"
							+ " [style.italicsPlasticity(" + Capacity.getCapacityFromValue(participant.getVaginaUrethraRawCapacityValue()).getDescriptor() + ")]!</i></p>");
				} else {
					sb.append("<br/>It will recover [style.italicsMinorGood(all)] of its original size, eventually tightening back to being"
							+ " [style.italicsPlasticity(" + Capacity.getCapacityFromValue(participant.getVaginaUrethraRawCapacityValue()).getDescriptor() + ")]!</i></p>");
				}
			}
		}
		// Special case for throat, as you aren't stretching it out, merely getting more experienced at sucking cock:
		if (participant.getFaceRawCapacityValue() != participant.getFaceStretchedCapacity() && areasStretched.get(participant).contains(SexAreaOrifice.MOUTH)) {
			// Increment core capacity by the Plasticity's capacityIncreaseModifier:
			participant.incrementFaceCapacity(
					(participant.getFaceStretchedCapacity()-participant.getFaceRawCapacityValue())*participant.getFacePlasticity().getCapacityIncreaseModifier(),
					false);
			participant.setFaceStretchedCapacity(participant.getFaceRawCapacityValue());

			sb.append("<p style='text-align:center;'><i>From [npc.namePos] [style.italicsPlasticity(newfound oral experience)], [npc.sheIs] now experienced enough to comfortably suck "
					+ PenisSize.getPenisSizeFromInt((int)participant.getFaceRawCapacityValue()).getDescriptor() + " cocks!</i></p>");
		}
		
		return UtilText.parse(participant, sb.toString());
	}
	
	public static int getEssenceGeneration(GameCharacter character) {
		int orgN = Sex.getNumberOfOrgasms(character);
		return Math.min(8, orgN*2-Math.max(0, orgN-3)) * (Main.game.getPlayer().hasTrait(Perk.NYMPHOMANIAC, true)?2:1);
	}
	
	public static void applyEndSexEffects() {
		StringBuilder endSexSB = new StringBuilder();
		
		boolean auraEventTriggered = false;
		for(GameCharacter participant : Sex.getAllParticipants()) {
			if(participant.isPlayer()) {
//				endSexSB.append("<br/><p style='text-align:center;'>"
//						+ "<span style='color:"+participant.getFemininity().getColour().toWebHexString()+";'>[npc.Name]:</span>"
//								+ "</p>");
				// Stretching effects:
				endSexSB.append(getEndSexStretchingDescription(participant));

				if((participant.getArousal() > ArousalLevel.THREE_HEATED.getMaximumValue() || Sex.getNumberOfDeniedOrgasms(participant)>0) && getNumberOfOrgasms(participant) == 0) {
					participant.addStatusEffect(StatusEffect.FRUSTRATED_NO_ORGASM, (240*60)+(postSexDialogue.getSecondsPassed()));
					if(Sex.getNumberOfDeniedOrgasms(participant)>0) {
						endSexSB.append("<p style='text-align:center'>[style.boldArcane(After being denied your orgasm, you're left feeling frustrated and horny!)]</p>");
					} else {
						endSexSB.append("<p style='text-align:center'>[style.boldArcane(After stopping so close to the edge, you're left feeling frustrated and horny!)]</p>");
					}
				}
				if(getNumberOfOrgasms(participant) > 0
						&& Main.game.isInNewWorld()) {
					participant.removeStatusEffect(StatusEffect.FRUSTRATED_NO_ORGASM);
					if(participant.hasStatusEffect(StatusEffect.RECOVERING_AURA)) {
						int orgasmCount = Sex.getNumberOfOrgasms(participant);
						endSexSB.append("<div class='container-full-width' style='text-align:center;'>"
								+ "<i>Your arcane aura is still strengthened from a previous sexual encounter,"
									+ " so [style.italicsBad(you don't receive any essences)] from your [style.colourSex("+(orgasmCount==1?"single orgasm":Util.intToString(orgasmCount)+" orgasms")+")]!</i><br/>"
							+ "</div>");
						
					} else {
						if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.essenceOrgasmDiscovered)) {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.essenceOrgasmDiscovered);
							if(!((PlayerCharacter) participant).isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
								if(Sex.getActivePartner()!=null) {
									endSexSB.append(
											UtilText.parse(Sex.getActivePartner(),
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
									endSexSB.append("<p>"
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
								if(Sex.getActivePartner()!=null) {
									endSexSB.append(
											UtilText.parse(Sex.getActivePartner(),
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
									endSexSB.append("<p>"
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
						int orgasmCount = Sex.getNumberOfOrgasms(participant);
						int essences = getEssenceGeneration(participant);
						endSexSB.append("<div class='container-full-width' style='text-align:center;'>"
											+ "<i>Your aura absorbs the "+(essences==1?"arcane essence":"arcane essences")+" generated by your [style.colourSex("+(orgasmCount==1?"single orgasm":Util.intToString(orgasmCount)+" orgasms")+")]!</i><br/>"
											+ Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, essences, true)
										+ "</div>");
						
					}
					participant.addStatusEffect(StatusEffect.RECOVERING_AURA, (240*60)+(postSexDialogue.getSecondsPassed()));
				}
				
				endSexSB = new StringBuilder(UtilText.parse(participant, endSexSB.toString()));
				
			// Partner effects:
			} else {
//				endSexSB.append("<br/><p style='text-align:center;'>"
//						+ "<span style='color:"+participant.getFemininity().getColour().toWebHexString()+";'>[npc.Name]:</span>"
//								+ "</p>");
				// Stretching effects:
				endSexSB.append(getEndSexStretchingDescription(participant));
				
				// Extra effects:
				if((participant.getArousal() > ArousalLevel.THREE_HEATED.getMaximumValue() || Sex.getNumberOfDeniedOrgasms(participant)>0) && getNumberOfOrgasms(participant) == 0) {
					participant.addStatusEffect(StatusEffect.FRUSTRATED_NO_ORGASM, (240*60)+(postSexDialogue.getSecondsPassed()));if(Sex.getNumberOfDeniedOrgasms(participant)>0) {
						endSexSB.append("<p style='text-align:center'>[style.boldArcane(After being denied [npc.her] orgasm, [npc.name] is left feeling frustrated and horny!)]</p>");
					} else {
						endSexSB.append("<p style='text-align:center'>[style.boldArcane(After stopping so close to the edge, [npc.name] is left feeling frustrated and horny!)]</p>");
					}
				}
				if(getNumberOfOrgasms(participant) > 0
						&& Main.game.isInNewWorld()) {
					participant.removeStatusEffect(StatusEffect.FRUSTRATED_NO_ORGASM);
					if(participant.hasStatusEffect(StatusEffect.RECOVERING_AURA)) {

						int orgasmCount = Sex.getNumberOfOrgasms(participant);
						endSexSB.append("<div class='container-full-width' style='text-align:center;'>"
								+ "<i>[npc.NamePos] arcane aura is still strengthened from a previous sexual encounter,"
									+ " so [style.italicsBad(you don't receive any essences)] from [npc.her] [style.colourSex("+(orgasmCount==1?"single orgasm":Util.intToString(orgasmCount)+" orgasms")+")]!</i><br/>"
							+ "</div>");
						
					} else {
						
						if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.essenceOrgasmDiscovered)) {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.essenceOrgasmDiscovered);
							if(!auraEventTriggered) {
								if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
									endSexSB.append(
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
									endSexSB.append(
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

						int orgasmCount = Sex.getNumberOfOrgasms(participant);
						int essences = getEssenceGeneration(participant);
						endSexSB.append("<div class='container-full-width' style='text-align:center;'>"
											+ "<i>Your aura absorbs the "+(essences==1?"arcane essence":"arcane essences")
												+" generated by [npc.namePos] [style.colourSex("+(orgasmCount==1?"single orgasm":Util.intToString(orgasmCount)+" orgasms")+")]!</i><br/>"
											+ Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, essences, true)
										+ "</div>");
					}
					participant.addStatusEffect(StatusEffect.RECOVERING_AURA, (240*60)+(postSexDialogue.getSecondsPassed()));

				}

				if(Sex.getSexPositionSlot(participant)!=SexSlotGeneric.MISC_WATCHING) {
					if(Sex.getAllParticipants().contains(Main.game.getPlayer())) {
						if((Sex.getSexPace(participant)==SexPace.SUB_RESISTING && !participant.getFetishDesire(Fetish.FETISH_NON_CON_SUB).isPositive())) {
							for(GameCharacter domParticipant : Sex.getDominantParticipants(false).keySet()) {
								endSexSB.append(participant.incrementAffection(domParticipant, -200, "[npc.Name] now despises [npc2.name] for raping [npc.herHim]!"));
							}
							
						} else {
							if(!Sex.isRemoveEndSexAffection(participant)) {
								if(!participant.getFetishDesire(Fetish.FETISH_DENIAL_SELF).isPositive() && !Sex.isDom(participant)) {
									int orgasms = Sex.getNumberOfOrgasms(participant);
									if(Sex.getNumberOfOrgasms(participant)==0) {
										for(GameCharacter domParticipant : Sex.getDominantParticipants(false).keySet()) {
											endSexSB.append(participant.incrementAffection(domParticipant, -10f, "[npc.Name] is angry at [npc2.name] for finishing without bringing [npc.herHim] to orgasm."));
										}
									} else if(orgasms < participant.getOrgasmsBeforeSatisfied()){
										for(GameCharacter domParticipant : Sex.getDominantParticipants(false).keySet()) {
											endSexSB.append(participant.incrementAffection(domParticipant, -5f,
													"[npc.Name] is annoyed at [npc2.name] for only giving [npc.herHim] "+Util.intToString(orgasms)+" orgasm"+(orgasms==1?"":"s")
														+", when [npc.she] really wanted at least "+Util.intToString(participant.getOrgasmsBeforeSatisfied())+"."));
										}
									}
								}
							}
						}
					}
				}
				
				endSexSB = new StringBuilder(UtilText.parse(participant, endSexSB.toString()));
			}
		}
		
		endSexSB.append(Sex.getSexManager().applyEndSexEffects());
		
		String s = endSexSB.toString();
		if(!s.isEmpty()) {
			endSexDescription ="<p style='text-align:center;'>"
					+ "<b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>End of sex status</b>"
							+ "</p>"
					+s;
		} else {
			endSexDescription = s;
		}
	}
	
	// Text formatting:
	
	private static String formatInitialPenetration(String description) {
		return "<p style='text-align:center;'><i style='color:" + BaseColour.PINK_DEEP.toWebHexString() + "; padding:0; margin:0;'>"+description+"</i></p>";
	}
	
	public static String formatPenetration(String rawInput){
		return "<p style='text-align:center; color:"+BaseColour.VIOLET.toWebHexString()+"; padding:0; margin:0;'><i>"+rawInput+"</i></p>";
	}
	
	private static String formatStopPenetration(String rawInput){
		return "<p style='text-align:center; color:"+BaseColour.PINK.toWebHexString()+"; padding:0; margin:0;'><i>"+rawInput+"</i></p>";
	}
	
	private static String formatCoverableAreaBecomingExposed(String description) {
		return "<p style='text-align:center; padding:0; margin:0;'><i style='color:" + BaseColour.PURPLE_LIGHT.toWebHexString() + ";'>"+description+"</i></p>";
	}
	
	private static String formatCoverableAreaGettingWet(String description) {
		return UtilText.parse(Sex.getActivePartner(),
				"<p style='text-align:center; padding:0; margin:0;'><i style='color:" + BaseColour.LILAC_LIGHT.toWebHexString() + ";'>"+description+"</i></p>");
	}

	
	public static final DialogueNode SEX_DIALOGUE = new DialogueNode("", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		
		@Override
		public String getLabel() {
			return (!Sex.isConsensual() && Main.getProperties().hasValue(PropertyValue.nonConContent)?"Non-consensual ":"")
					+(Sex.isPublicSex()?"Public ":"")
					+(getPosition().getName().isEmpty()
							?(Sex.getAllParticipants(false).size()>1?"Sex":"Masturbation")
							:(Sex.getAllParticipants(false).size()>1?"Sex: ":"Masturbation: ")+getPosition().getName());
		}

		@Override
		public String getContent() {
			return sexDescription + (sexFinished ? endSexDescription : "");
		}

		@Override
		public String getResponseTabTitle(int index) {
			if (sexFinished
					|| isReadyToOrgasm(Main.game.getPlayer())
					|| Sex.isCharacterDeniedOrgasm(Main.game.getPlayer())
					|| (Sex.getActivePartner()!=null && isReadyToOrgasm(Sex.getActivePartner()))) {
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
			} else if(isReadyToOrgasm(Main.game.getPlayer()) || Sex.isCharacterDeniedOrgasm(Main.game.getPlayer()) || (Sex.getActivePartner()!=null && isReadyToOrgasm(Sex.getActivePartner()))) {
				if(index == 0){
					return null;

				} else if (index-1 < availableSexActionsPlayer.size() && availableSexActionsPlayer.get(index-1) != null){
					return availableSexActionsPlayer.get(index-1).toResponse();
					
				} else if (isReadyToOrgasm(Main.game.getPlayer())
						&& (index-1-availableSexActionsPlayer.size() < characterSwitchActionsPlayer.size() && characterSwitchActionsPlayer.get(index-1-availableSexActionsPlayer.size()) != null)){
					return characterSwitchActionsPlayer.get(index-1-availableSexActionsPlayer.size()).toResponse();
					
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
					
				} else if(responseTab==4 && !playerUniqueActions) { // Cannot use repeat actions when there are unique actions.
					List<SexActionInterface> availableRepeatActionsPlayer = new LinkedList<>();
					availableRepeatActionsPlayer.addAll(repeatActionsPlayer);
					availableRepeatActionsPlayer.removeIf(sa-> !miscActionsPlayer.contains(sa) && !selfActionsPlayer.contains(sa) && !sexActionsPlayer.contains(sa));
					availableRepeatActionsPlayer.removeIf(sa-> !sa.isAddedToAvailableSexActions());
					availableRepeatActionsPlayer.removeIf(sa-> !sa.isBaseRequirementsMet());
					availableRepeatActionsPlayer.removeIf(sa-> !sa.toResponse().isAvailable() && !sa.toResponse().isAbleToBypass());
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
		public boolean isInventoryDisabled() {
			if (sexFinished
					|| isReadyToOrgasm(Main.game.getPlayer())
					|| Sex.isCharacterDeniedOrgasm(Main.game.getPlayer())
					|| (Sex.getActivePartner()!=null && isReadyToOrgasm(Sex.getActivePartner()))) {
					return true;
			}
			return false;
		}
	};
	
	/**
	 * Don't call this out of sex.
	 * @param sexActionPlayer The action that the player is taking this turn.
	 */
	public static void endSexTurn(SexActionInterface sexActionPlayer) {

		sexSB = new StringBuilder();
		
//		startTurnPlayerArousal = Main.game.getPlayer().getArousal(); //TODO test
//		System.out.println("startTurnPlayerArousal: "+startTurnPlayerArousal);
		
		sexSB.append("<p>"
						+ sexActionPlayer.preDescriptionBaseEffects()
						+ sexActionPlayer.getDescription()
						+ sexActionPlayer.getFluidFlavourDescription(Main.game.getPlayer(), Sex.getTargetedPartner(Main.game.getPlayer()))
					+ "</p>");
		
		String endString = sexActionPlayer.baseEffects();
		
		sexSB.append(applyGenericDescriptionsAndEffects(Main.game.getPlayer(), Sex.getTargetedPartner(Main.game.getPlayer()), sexActionPlayer));
		
		boolean endsSex = sexActionPlayer.endsSex();
		
		sexActionPlayer.applyEndEffects();
		
		sexSB.append(endString);
		
		String s;
		if(sexActionPlayer.getLimitation()==null
				&& sexActionPlayer!=SexActionUtility.CLOTHING_REMOVAL
				&& sexActionPlayer!=SexActionUtility.CLOTHING_DYE) {
			s = UtilText.parse(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(sexActionPlayer), sexSB.toString(), ParserTag.SEX_DESCRIPTION);
			
		} else {
			s = UtilText.parse(Sex.getCharacterTargetedForSexAction(sexActionPlayer), sexSB.toString(), ParserTag.SEX_DESCRIPTION);
		}
		sexSB.setLength(0);
		sexSB.append(s);

		lastUsedSexAction.put(Main.game.getPlayer(), sexActionPlayer);
		if(sexActionPlayer.getActionType()!=SexActionType.PREPARE_FOR_PARTNER_ORGASM
				&& sexActionPlayer.getActionType()!=SexActionType.ORGASM
				&& sexActionPlayer.getActionType()!=SexActionType.ORGASM_DENIAL) {
			repeatActionsPlayer.remove(sexActionPlayer);
			repeatActionsPlayer.add(sexActionPlayer);
		}
		
		// End sex conditions:
		if (endsSex && !sexFinished) {
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
						
						if(sexActionPlayer.getActionType()!=SexActionType.ORGASM && sexActionPlayer.getActionType()!=SexActionType.ORGASM_DENIAL) {
							Sex.getSexManager().assignNPCTarget(character);
							
							calculateAvailableSexActionsPartner();
							
							SexActionInterface sexActionPartner = sexManager.getPartnerSexAction(sexActionPlayer);
							
							sexSB.append("<br/>"
									+ "<p>"
										+ "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>&gt; "+UtilText.parse(character, "[npc.Name]")+": "+(Util.capitaliseSentence(sexActionPartner.getActionTitle()))+"</span>"
										+ "</br>"
										+ sexActionPartner.preDescriptionBaseEffects()
										+ sexActionPartner.getDescription()
										+ sexActionPartner.getFluidFlavourDescription(character, Sex.getTargetedPartner(character))
									+ "</p>");
				
							endString = sexActionPartner.baseEffects();
							lastUsedSexAction.put(character, sexActionPartner);
							
							sexSB.append(applyGenericDescriptionsAndEffects(character, Sex.getTargetedPartner(character), sexActionPartner));
							
							endsSex = sexActionPartner.endsSex();
							
							sexActionPartner.applyEndEffects();
							
							sexSB.append(endString);
							
							if(sexActionPartner.getLimitation()==null
									&& sexActionPartner!=SexActionUtility.CLOTHING_REMOVAL
									&& sexActionPartner!=SexActionUtility.CLOTHING_DYE) {
								s = UtilText.parse(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(sexActionPartner), sexSB.toString(), ParserTag.SEX_DESCRIPTION);
							} else {
								s = UtilText.parse(Sex.getCharacterPerformingAction(), sexSB.toString(), ParserTag.SEX_DESCRIPTION);
							}
							sexSB.setLength(0);
							sexSB.append(s);
							
							sexDescription = sexSB.toString();
							
							// End sex conditions:
							if (endsSex && !sexFinished) {
								applyEndSexEffects();
								sexFinished = true;
								break;
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
			
			if(Sex.isPublicSex() && !sexFinished) {
				sexSB.append(Sex.getInitialSexManager().getRandomPublicSexDescription());
				sexDescription = sexSB.toString();
			}
			
			// Re-populate lists for the player's next action choice.
			populatePlayerSexLists();
		}

		repeatActionsPlayer.remove(SexActionUtility.POSITION_SELECTION);
		repeatActionsPlayer.remove(SexActionUtility.CLOTHING_DYE);
		repeatActionsPlayer.remove(SexActionUtility.CLOTHING_REMOVAL);
		repeatActionsPlayer.remove(SexActionUtility.PLAYER_USE_ITEM);
		
		setOverridePlayerArousalRestriction(false);
		
		turn++;
	}

	public static void recalculateSexActions() {
		populatePlayerSexLists();
	}
	
	private static void populatePlayerSexLists() {
		// Populate available SexActions from the current SexPosition.
		availableSexActionsPlayer.clear();

		List<SexActionInterface> uniqueActions = new ArrayList<>();
		List<SexActionInterface> normalActions = new ArrayList<>();
		
		if (isReadyToOrgasm(Main.game.getPlayer())) { // Add orgasm actions if player ready to orgasm:
			characterOrgasming = Main.game.getPlayer();
			
			// If there are unique maximum priority actions, only add those.
			for (SexActionInterface sexAction : Sex.getOrgasmActionsPlayer()) {
				if (sexAction.isAddedToAvailableSexActions()) {
					if(sexAction.getPriority()==SexActionPriority.UNIQUE_MAX) {
						uniqueActions.add(sexAction);
					} else {
						normalActions.add(sexAction);
					}
				}
			}
			
		} else {
			boolean partnerOrgasming = false;
			for(GameCharacter character : Sex.getAllParticipants(false)) {
				if(!character.isPlayer() && isReadyToOrgasm(character)) {
					characterOrgasming = character;
					Sex.setActivePartner((NPC) character);
					for (SexActionInterface sexAction : Sex.getActionsAvailablePlayer()) {
						if (sexAction.getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM && sexAction.isAddedToAvailableSexActions()) {
							if(sexAction.getPriority()==SexActionPriority.UNIQUE_MAX) {
								uniqueActions.add(sexAction);
							} else {
								normalActions.add(sexAction);
							}
						}
					}
					partnerOrgasming = true;
					break;
				}
			}
			
			if (!partnerOrgasming) {
				// Can always do nothing:
				normalActions.add(SexActionUtility.PLAYER_NONE);
				normalActions.add(SexActionUtility.PLAYER_CALM_DOWN);
				
				// Add actions:
				for (SexActionInterface sexAction : Sex.getActionsAvailablePlayer()) {
					if (sexAction.isAddedToAvailableSexActions()){
						if(sexAction.getPriority()==SexActionPriority.UNIQUE_MAX) {
							uniqueActions.add(sexAction);
						} else {
							normalActions.add(sexAction);
						}
					}
				}
			}
		}
		
		if(!uniqueActions.isEmpty()) {
			availableSexActionsPlayer.addAll(uniqueActions);
			playerUniqueActions = true;
		} else {
			availableSexActionsPlayer.addAll(normalActions);
			playerUniqueActions = false;
		}
		availableSexActionsPlayer.sort((SexActionInterface s1, SexActionInterface s2) -> {
			if(s1.getActionType()==s2.getActionType()) {
				if(s1.getSexPace()==s2.getSexPace()) {
					return 0;
				}
				return s1.getSexPace()==null
						?-1
						:s2.getSexPace()==null
							?1
							:s1.getSexPace().compareTo(s2.getSexPace());
			}
			return s1.getActionType().compareTo(s2.getActionType());
			});
		
		characterSwitchActionsPlayer.clear();
		miscActionsPlayer.clear();
		selfActionsPlayer.clear();
		sexActionsPlayer.clear();
		positionActionsPlayer.clear();
		for(SexActionInterface action : availableSexActionsPlayer) {
			if(action.toResponse()!=null) {
				switch(action.getCategory()) {
					case MISCELLANEOUS:
						miscActionsPlayer.add(action);
						break;
					case CHARACTER_SWITCH:
						characterSwitchActionsPlayer.add(action);
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
		
		positionActionsPlayer.sort((a1, a2) -> 
		a1.getActionType()==SexActionType.POSITIONING_MENU
			?a2.getActionType()==SexActionType.POSITIONING_MENU
				?a1.getActionTitle().compareTo(a2.getActionTitle())
				:-1
			:a2.getActionType()!=SexActionType.POSITIONING_MENU && a1.isPositionSwap()
				?-1
				:a1.getActionType()!=SexActionType.POSITIONING_MENU && a2.isPositionSwap()
					?1
					:a1.getActionTitle().compareTo(a2.getActionTitle()));
		
		if(Sex.getTotalParticipantCount(false)>2) {
			for(GameCharacter character : Sex.getAllParticipants(false)) {
				if(!character.isPlayer() && !Sex.getTargetedPartner(Main.game.getPlayer()).equals(character)) {
					characterSwitchActionsPlayer.add(new SexAction(
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
														return UtilText.parse(character, "[npc.Name]");
													}
											
													@Override
													public String getActionDescription() {
														return UtilText.parse(character, "Set [npc.name] as the active partner. (You can also do this by clicking on their name in the side bar.)");
													}
											
													@Override
													public String getDescription() {
														return "";
													}
											
													@Override
													public String getFluidFlavourDescription(GameCharacter performing, GameCharacter receiving) {
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
		
		miscActionsPlayer.addAll(characterSwitchActionsPlayer);
		
	}

	/**
	 * For use in getPartnerSexAction() in SexManager classes.
	 */
	private static void calculateAvailableSexActionsPartner() {
		// Populate available SexActions from the current SexPosition.
		availableSexActionsPartner.clear();
		List<SexActionInterface> lowPriority = new ArrayList<>();
		List<SexActionInterface> normalPriority = new ArrayList<>();
		List<SexActionInterface> highPriority = new ArrayList<>();
		List<SexActionInterface> uniqueMax  = new ArrayList<>();
		boolean standardActions = true;
		GameCharacter targetedCharacter = Sex.getTargetedPartner(Sex.getCharacterPerformingAction());
		
		// Add orgasm actions if ready to orgasm:
		if (isReadyToOrgasm(Sex.getCharacterPerformingAction())) {
			characterOrgasming = Sex.getCharacterPerformingAction();
			standardActions = false;

			if(SexFlags.playerPreparedForCharactersOrgasm.contains(Sex.getCharacterPerformingAction())) {
				for (SexActionInterface sexAction : Sex.getOrgasmActionsPartner(Sex.getCharacterPerformingAction(), targetedCharacter)) {
					if (sexAction.isAddedToAvailableSexActions()) {
						
						int weight = ((NPC)Sex.getCharacterPerformingAction()).calculateSexTypeWeighting(sexAction.getAsSexType(), targetedCharacter, null);
						
						if(weight>=0 || sexAction.getCategory()==SexActionCategory.POSITIONING) { // Positioning actions should always be available
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
		
		if (isReadyToOrgasm(Main.game.getPlayer()) && !Sex.isSpectator(Main.game.getPlayer())) { // Add orgasm reactions if ready to orgasm:
			characterOrgasming = Main.game.getPlayer();
			Sex.setTargetedPartner(Sex.getCharacterPerformingAction(), characterOrgasming);
			for (SexActionInterface sexAction : Sex.getActionsAvailablePartner(Sex.getCharacterPerformingAction(), characterOrgasming)) { //TODO manually set target as characterOrgasming. Test more
				if (sexAction.getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM) {
					if (sexAction.isAddedToAvailableSexActions()) {
						int weight = ((NPC)Sex.getCharacterPerformingAction()).calculateSexTypeWeighting(sexAction.getAsSexType(), characterOrgasming, null);
						
						if(weight>=0 || sexAction.getCategory()==SexActionCategory.POSITIONING) { // Positioning actions should always be available
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
			for (SexActionInterface sexAction : Sex.getActionsAvailablePartner(Sex.getCharacterPerformingAction(), targetedCharacter)) {
				if (sexAction.isAddedToAvailableSexActions() && (Sex.isCharacterAllowedToUseSelfActions(Sex.getCharacterPerformingAction()) || sexAction.getParticipantType()==SexParticipantType.NORMAL)) {
					
					// Do not add action if the partner is resisting and this action is SUB_EAGER or SUB_NORMAL or is a self action
					// Do not add action if action does not correspond to the partner's preferred action pace
					if((Main.game.isNonConEnabled()
						&& getSexPace(Sex.getCharacterPerformingAction())==SexPace.SUB_RESISTING
						&& ((sexAction.getSexPace()!=null && sexAction.getSexPace()!=SexPace.SUB_RESISTING)
								|| sexAction.getParticipantType()==SexParticipantType.SELF
								|| (sexAction.getSexPace()==null && sexAction!=PartnerTalk.PARTNER_DIRTY_TALK && !sexAction.equals(GenericActions.PARTNER_STOP_SEX_NOT_HAVING_FUN)))) // TODO This is a little terrible
							|| (sexAction.getSexPace()!=null && sexAction.getSexPace()!=getSexPace(Sex.getCharacterPerformingAction()))) {
//						System.out.println(Sex.getCharacterPerformingAction().getNameIgnoresPlayerKnowledge() +": "+ sexAction.getActionTitle());
						
					} else {
						// Add action as normal:
						int weight = ((NPC)Sex.getCharacterPerformingAction()).calculateSexTypeWeighting(sexAction.getAsSexType(), targetedCharacter, null);
						
						if(weight>=0 || sexAction.equals(GenericActions.PARTNER_STOP_SEX_NOT_HAVING_FUN) || sexAction.getCategory()==SexActionCategory.POSITIONING) { // Positioning actions should always be available
							switch(sexAction.getPriority()){
								case LOW:
									lowPriority.add(sexAction);
									break;
								case NORMAL:
									normalPriority.add(sexAction);
									break;
								case HIGH:
									// High priority positioning actions are added to normal priority so that when there is a favourite positioning action, it doesn't exclude all other normal actions.
									// High priority is checked in SexManagerDefault's getPartnerSexAction() method, under the section 'Priority 3'
									if(sexAction.getCategory()==SexActionCategory.POSITIONING) {
										normalPriority.add(sexAction);
									} else {
										highPriority.add(sexAction);
									}
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
	private static String applyGenericDescriptionsAndEffects(GameCharacter activeCharacter, GameCharacter targetCharacter, SexActionInterface sexAction) {

		// TODO Change targetPartner to a loop of all characters?
		StringBuilder stringBuilderForAppendingDescriptions = new StringBuilder();
		
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
		if(Sex.getSexPace(activeCharacter)==SexPace.SUB_RESISTING && !activeCharacter.getFetishDesire(Fetish.FETISH_NON_CON_SUB).isPositive()) {
			lustIncrements.put(activeCharacter, -2.5f);
		} else {
			lustIncrements.put(activeCharacter, Math.min(2.5f, Math.max(-2.5f, sexAction.getArousalGainSelf().getArousalIncreaseValue())));
		}
		lustIncrements.put(activeCharacter, lustIncrements.get(activeCharacter) + (activeCharacter.getAffection(targetCharacter)/40)); //+-2.5
		
		if(Sex.getSexPace(targetCharacter)==SexPace.SUB_RESISTING && !targetCharacter.getFetishDesire(Fetish.FETISH_NON_CON_SUB).isPositive()) {
			lustIncrements.put(targetCharacter, -2.5f);
		} else {
			lustIncrements.put(targetCharacter, Math.min(2.5f, Math.max(-2.5f, sexAction.getArousalGainTarget().getArousalIncreaseValue())));
		}
		lustIncrements.put(targetCharacter, lustIncrements.get(targetCharacter) + (targetCharacter.getAffection(activeCharacter)/40)); //+-2.5
		
		
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
		if(sexAction.getFetishes(activeCharacter)!=null) {// && sexAction.getSexPace()!=SexPace.SUB_RESISTING) {
			for(Fetish f : sexAction.getFetishes(activeCharacter)) {
				if(activeCharacter.hasFetish(f)) {
					arousalIncrements.put(activeCharacter, arousalIncrements.get(activeCharacter) + activeCharacter.getFetishLevel(f).getBonusArousalIncrease());
					if(!Sex.isMasturbation()) {
						arousalIncrements.put(targetCharacter, arousalIncrements.get(targetCharacter) + activeCharacter.getFetishLevel(f).getBonusArousalIncreasePartner());
					}
					activeCharacter.incrementFetishExperience(f, f.getExperienceGainFromSexAction());
				}
			}
		}
		if(sexAction.getCategory()!=SexActionCategory.POSITIONING) { // Positioning actions should not be affected by lust increments
			lustIncrements.put(activeCharacter, lustIncrements.get(activeCharacter) + (activeCharacter.calculateSexTypeWeighting(sexAction.getAsSexType(), targetCharacter, null, true)*0.25f));
		}
		
		if(sexAction.getParticipantType()!=SexParticipantType.SELF) {
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
				}
			}
			if(sexAction.getCategory()!=SexActionCategory.POSITIONING) { // Positioning actions should not be affected by lust increments
				lustIncrements.put(targetCharacter, lustIncrements.get(targetCharacter) + (targetCharacter.calculateSexTypeWeighting(sexAction.getAsSexType().getReversedSexType(), activeCharacter, null, true)*0.25f));
			}
		}
		
		// Increment lust:
		for(Entry<GameCharacter, Float> entry : lustIncrements.entrySet()) {
			if(Sex.getSexPositionSlot(activeCharacter)!=SexSlotGeneric.MISC_WATCHING || entry.getKey().equals(activeCharacter)) { // Spectators only influence themselves.
				if(sexAction.getActionType().isOrgasmOption() && entry.getKey().equals(activeCharacter)) { // Orgasm actions causes lust to drop:
					float lustLoss = 50;
					
					if(activeCharacter.hasStatusEffect(StatusEffect.WEATHER_STORM_VULNERABLE)) { // If vulnerable to storm, lust does not drop.
						lustLoss=0;
						
					} else {
						if(activeCharacter.isAttractedTo(targetCharacter)) { // -50% loss if attracted.
							lustLoss*=0.5f;
						}
						if(activeCharacter.hasStatusEffect(StatusEffect.PSYCHOACTIVE)) { // -75% loss if on drugs.
							lustLoss*=0.25f;
						}
						switch(activeCharacter.getAffectionLevelBasic(targetCharacter)) { // -50% loss if positive affection, +50% increase if negative affection.
							case DISLIKE:
								lustLoss*=1.5f;
								break;
							case LIKE:
								lustLoss*=0.5f;
								break;
							case NEUTRAL:
								break;
						}
					}
					entry.getKey().incrementLust(-lustLoss, false);
					
				} else {
					float lustValue = entry.getValue();
					
					entry.getKey().incrementLust(Math.max(-2.5f, Math.min(2.5f, lustValue)), false);
				}
			}
		}
		
		if(sexAction.getArousalGainSelf().getArousalIncreaseValue()<0) {
			arousalIncrements.put(activeCharacter, sexAction.getArousalGainSelf().getArousalIncreaseValue());
		}
		if(!Sex.isMasturbation() && sexAction.getArousalGainTarget().getArousalIncreaseValue()<0) {
			arousalIncrements.put(targetCharacter, sexAction.getArousalGainTarget().getArousalIncreaseValue());
		}

		float startArousal = Main.game.getPlayer().getArousal();
		
		// Increment arousal:
		for(Entry<GameCharacter, Float> entry : arousalIncrements.entrySet()) {
			if(Sex.getSexPositionSlot(activeCharacter)!=SexSlotGeneric.MISC_WATCHING || entry.getKey().equals(activeCharacter)) { // Spectators only influence themselves.
				boolean foreplay = Sex.isInForeplay(entry.getKey());
				float arousal = entry.getValue();
				float startingArousal = entry.getKey().getArousal();
				
				// Raises the cap for positive arousal increments:
				int arousalCapIncrease = Math.max(0,
						Math.min(10,
						entry.getKey().equals(activeCharacter)
							?activeCharacter.calculateSexTypeWeighting(sexAction.getAsSexType(), targetCharacter, null, true)
							:entry.getKey().calculateSexTypeWeighting(sexAction.getAsSexType().getReversedSexType(), activeCharacter, null, true)));

				if(sexAction.getCategory()==SexActionCategory.POSITIONING) { // Positioning actions should not affect arousal increments
					arousalCapIncrease = 0;
				}
				
				if(Sex.isMasturbation()) {
					arousal*=2;
				}
				
				if(entry.getKey().equals(activeCharacter) && sexAction.getArousalGainSelf().getArousalIncreaseValue()<0) { // For special negative arousal values.
					entry.getKey().incrementArousal(sexAction.getArousalGainSelf().getArousalIncreaseValue());
					
				} else if(entry.getKey().equals(targetCharacter) && sexAction.getArousalGainTarget().getArousalIncreaseValue()<0) { // For special negative arousal values.
					entry.getKey().incrementArousal(sexAction.getArousalGainTarget().getArousalIncreaseValue());
					
				} else {
					int sideDifference = Math.max(0, (Sex.isDom(entry.getKey())?-1:1)*Sex.getDominantParticipants(false).size()-Sex.getSubmissiveParticipants(false).size());
					
					float increment = Math.min(
							(5f+arousalCapIncrease)*(1f-(sideDifference/5f)),
							arousal * entry.getKey().getLustLevel().getArousalModifier()); // Modify arousal value based on lust

//					System.out.println(entry.getKey().getName()+": "+increment+" | "+(5f+arousalCapIncrease)+", "+(1f-(sideDifference/5f)));
					
					entry.getKey().incrementArousal(increment);
				}
				
				// Player arousal can only reach 99 during partner's turns, to give them all a chance to react:
				if(!isOverridePlayerArousalRestriction()
						&& entry.getKey().isPlayer()
						&& !activeCharacter.isPlayer()
						&& entry.getKey().getArousal()>=100
						&& (startArousal<100 || sexAction.getActionType().isOrgasmOption())) {
					entry.getKey().setArousal(startingArousal);
				}
				
				if(foreplay && !Sex.isInForeplay(entry.getKey())) { // Reset positioning blocked when moving from foreplay to main sex:
					Sex.removeCharacterBannedFromPositioning(entry.getKey());
				}
			}
		}
		
		
		// Cummed in areas:
		
		// Add any areas that have been cummed in:
		boolean condomBrokeAppended = false;
		for(GameCharacter cumProvider : Sex.getAllParticipants()) {
			for(GameCharacter cumTarget :Sex.getAllParticipants()) {
				if (!cumProvider.equals(activeCharacter) && !cumTarget.equals(activeCharacter)) {
					continue;
				}
				
				if (cumProvider.getPenisOrgasmCumQuantity() == CumProduction.ZERO_NONE) {
					continue;
				}
				
				if (cumProvider.isWearingCondom()
						&& cumProvider.equals(activeCharacter)
						&& (sexAction.getActionType()==SexActionType.ORGASM || sexAction.getActionType()==SexActionType.ORGASM_DENIAL)) {
					if(sexAction.getCondomFailure(cumProvider, cumTarget)==CondomFailure.NONE) {
						continue;
					} else if(!condomBrokeAppended) {
						stringBuilderForAppendingDescriptions.append(UtilText.parse(cumProvider, "<p style='text-align:center;'>[style.boldTerrible([npc.NamePos] condom broke as [npc.she] [npc.was] cumming!)]</p>"));
						condomBrokeAppended = true;
					}
				}

				List<SexAreaInterface> cummedInAreas = sexAction.getAreasCummedIn(cumProvider, cumTarget);
				if (cummedInAreas != null) {
					for (SexAreaInterface area : cummedInAreas) {
						cumTarget.incrementCumCount(
								new SexType(SexParticipantType.NORMAL, area, SexAreaPenetration.PENIS));
						cumProvider.incrementCumCount(
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, area));
						if (area.isOrifice() && ((SexAreaOrifice) area).isInternalOrifice()) {
							if(sexAction==GenericOrgasms.GENERIC_ORGASM_DOUBLE_CREAMPIE) {
								stringBuilderForAppendingDescriptions.append(cumTarget.ingestFluid(cumProvider, cumProvider.getCum(), (SexAreaOrifice) area, cumProvider.getPenisRawOrgasmCumQuantity()/2));
							} else {
								stringBuilderForAppendingDescriptions.append(cumTarget.ingestFluid(cumProvider, cumProvider.getCum(), (SexAreaOrifice) area, cumProvider.getPenisRawOrgasmCumQuantity()));
							}
						}
					}
					Sex.incrementTimesCummedInside(cumProvider, cumTarget, 1);
				}

				List<CoverableArea> cummedOnAreas = sexAction.getAreasCummedOn(cumProvider, cumTarget);
				if (cummedOnAreas == null) {
					continue;
				}
				for(CoverableArea area : cummedOnAreas) {
					for(InventorySlot slot : area.getAssociatedInventorySlots(cumTarget)) {
						List<AbstractClothing> dirtyClothing = new ArrayList<>(cumTarget.getVisibleClothingConcealingSlot(slot));
						if(!dirtyClothing.isEmpty()) {
							for(AbstractClothing c : dirtyClothing) {
								c.setDirty(cumTarget, true);
							}
						} else if(slot!=InventorySlot.TORSO_OVER) { // Do not dirty over-torso slot, as it doesn't really make much sense...
							cumTarget.addDirtySlot(slot);
						}
					}
				}
			}
		}

		if(sexAction.getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM && Sex.getCharacterPerformingAction().isPlayer()) {
			SexFlags.playerPreparedForCharactersOrgasm.add(Sex.getCharacterTargetedForSexAction(sexAction));
		}
		
		// Handle orgasms:
		if(sexAction.getActionType()==SexActionType.ORGASM) {
			// Condom removal:
			if(Sex.getCharacterPerformingAction().isWearingCondom()) {
				if(sexAction.getCondomFailure(activeCharacter, targetCharacter)==CondomFailure.NONE) {
					Sex.getCharacterPerformingAction().getClothingInSlot(InventorySlot.PENIS).setSealed(false);
					if(Sex.getCharacterPerformingAction().getPenisRawOrgasmCumQuantity()>0) {
						stringBuilderForAppendingDescriptions.append(Main.game.getPlayer().addItem(
								AbstractItemType.generateFilledCondom(
										Sex.getCharacterPerformingAction().getClothingInSlot(InventorySlot.PENIS).getColour(),
										Sex.getCharacterPerformingAction(), Sex.getCharacterPerformingAction().getCum(), Sex.getCharacterPerformingAction().getPenisRawOrgasmCumQuantity()),
								false, true));
					}
				}
				Sex.getCharacterPerformingAction().unequipClothingIntoVoid(Sex.getCharacterPerformingAction().getClothingInSlot(InventorySlot.PENIS), true, Sex.getCharacterPerformingAction());
			}
			
			if(Sex.getCharacterPerformingAction().hasVagina() && Sex.getCharacterPerformingAction().isVaginaSquirter()) {
				AbstractClothing vaginaClothing = Sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.VAGINA);
				if(vaginaClothing!=null
						&& !vaginaClothing.getItemTags().contains(ItemTag.PLUGS_VAGINA)
						&& !vaginaClothing.getItemTags().contains(ItemTag.SEALS_VAGINA)) {
					vaginaClothing.setDirty(Sex.getCharacterPerformingAction(), true);
					
				} else {
					Set<GameCharacter> charactersEatingOut = new HashSet<>(getOngoingCharactersUsingAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
					charactersEatingOut.addAll(getOngoingCharactersUsingAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaOrifice.MOUTH));
					
					for(GameCharacter character : charactersEatingOut) {
						List<InventorySlot> squirterSlots = Util.newArrayListOfValues(InventorySlot.MOUTH, InventorySlot.EYES, InventorySlot.HAIR);
						for(InventorySlot slot : squirterSlots) {
							List<AbstractClothing> dirtyClothing = new ArrayList<>(character.getVisibleClothingConcealingSlot(slot));
							if(!dirtyClothing.isEmpty()) {
								for(AbstractClothing c : dirtyClothing) {
									c.setDirty(character, true);
								}
							} else {
								character.addDirtySlot(slot);
							}
						}
						
						stringBuilderForAppendingDescriptions.append(character.ingestFluid(
								Sex.getCharacterPerformingAction(),
								Sex.getCharacterPerformingAction().getGirlcum(),
								SexAreaOrifice.MOUTH,
								5 * Sex.getCharacterPerformingAction().getVaginaWetness().getValue()));
					}
				}
			}
			
			// Apply orgasm arousal resets:
			if((Sex.getCharacterPerformingAction().hasPenis() && Sex.getCharacterPerformingAction().getPenisRawOrgasmCumQuantity()>0)
					|| Sex.getCharacterPerformingAction().hasVagina()
					|| Sex.getNumberOfOrgasms(Sex.getCharacterPerformingAction())>0) {
				incrementNumberOfOrgasms(Sex.getCharacterPerformingAction(), 1);
				Sex.getCharacterPerformingAction().setArousal(Sex.getCharacterPerformingAction().getLust()/4f);
				if(Sex.getCharacterPerformingAction().hasPenis()) {
					Sex.getCharacterPerformingAction().applyOrgasmCumEffect(sexAction==GenericOrgasms.GENERIC_ORGASM_DOUBLE_CREAMPIE?0.5f:1);
				}
				
			} else {
				incrementNumberOfOrgasms(Sex.getCharacterPerformingAction(), 1);
				Sex.getCharacterPerformingAction().setArousal(Sex.getCharacterPerformingAction().getLust()/2f);
				Sex.getCharacterPerformingAction().addStatusEffect(StatusEffect.FRUSTRATED_NO_ORGASM, (4*60*60)+(postSexDialogue.getSecondsPassed()));
				stringBuilderForAppendingDescriptions.append(UtilText.parse(Sex.getCharacterPerformingAction(),
						"<p style='text-align:center'>Without producing any cum, [npc.namePos] climax can't be counted as a real orgasm, and makes [npc.name] feel [style.boldBad(frustrated and horny)]!</p>"));
			}

			// Reset appropriate flags:
			removeCharacterBannedFromPositioning(getCharacterPerformingAction());
			charactersRequestingCreampie = new HashSet<>();
			charactersRequestingPullout = new HashSet<>();
			SexFlags.playerPreparedForCharactersOrgasm.remove(getCharacterPerformingAction());
		}

		// Handle if parts have just become exposed:
		if (sexAction == SexActionUtility.CLOTHING_REMOVAL) {
			for(GameCharacter character : Sex.getAllParticipants()) {
				if(Sex.getSexPositionSlot(character)!=SexSlotGeneric.MISC_WATCHING) {
					stringBuilderForAppendingDescriptions.append(handleExposedDescriptions(character, false));
				}
			}
		}
		
		// Only apply penetration effects if this action isn't an orgasm, and it isn't the end of sex. (Otherwise, ongoing descriptions get appended after the main description, which usually don't make sense.) TODO
		if (!Sex.getOrgasmActionsPlayer().contains(sexAction)
				&& (Sex.isMasturbation() || !Sex.getOrgasmActionsPartner(Sex.getCharacterPerformingAction(), Sex.getTargetedPartner(Sex.getCharacterPerformingAction())).contains(sexAction))
				&& sexAction.getCategory()!=SexActionCategory.POSITIONING
				&& !sexAction.endsSex()) {

			for(GameCharacter character : Sex.getAllParticipants()) {
				for(Entry<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> entry : ongoingActionsMap.get(character).entrySet()) {
					for(GameCharacter characterTarget : Sex.getAllParticipants()) {
						
						// Reset loose areas ready to be re-applied in applyPenetrationEffects()
						for(SexAreaOrifice o : SexAreaOrifice.values()) {
							if(!Sex.isOrificeNonSelfOngoingAction(characterTarget, o)) {
								areasTooLoose.get(characterTarget).remove(o);
							}
						}
						
						if(entry.getValue().containsKey(characterTarget)) {
							for(SexAreaInterface sArea : entry.getValue().get(characterTarget)) {
								if(entry.getKey().isPenetration()) {
									stringBuilderForAppendingDescriptions.append(applyPenetrationEffects(sexAction, character, (SexAreaPenetration)entry.getKey(), characterTarget, sArea));
								}
								
								SexType ongoingSexType = new SexType(SexParticipantType.NORMAL, entry.getKey(), sArea);
								
								int weight = character.calculateSexTypeWeighting(ongoingSexType, characterTarget, null, true);
								int targetWeight = characterTarget.calculateSexTypeWeighting(ongoingSexType.getReversedSexType(), character, null, true);

								character.incrementLust(Math.max(-2.5f, Math.min(2.5f, (weight*0.25f))), false);
								characterTarget.incrementLust(Math.max(-2.5f, Math.min(2.5f, (targetWeight*0.25f))), false);
								
								// Half xp from ongoing:
								List<Fetish> selfFetishes = sexAction.getFetishesFromPenetrationAndOrificeTypes(character, entry.getKey(), characterTarget, sArea, true);
								List<Fetish> targetFetishes = sexAction.getFetishesFromPenetrationAndOrificeTypes(character, entry.getKey(), characterTarget, sArea, false);
								
								for(Fetish f : selfFetishes) {
									character.incrementFetishExperience(f, f.getExperienceGainFromSexAction()/2);
								}
								for(Fetish f : targetFetishes) {
									characterTarget.incrementFetishExperience(f, f.getExperienceGainFromSexAction()/2);
								}
							}
						}
					}
				}
			}
			
		}
		
		stringBuilderForAppendingDescriptions.append(calculateWetAreas(false));
		
		for(Entry<GameCharacter, SexPace> entry : initialPaces.entrySet()) {
			SexPace finalPace = Sex.getSexPace(entry.getKey());
			if(entry.getValue() != finalPace && !entry.getKey().isPlayer()) {
				stringBuilderForAppendingDescriptions.append("<p style='text-align:center;'>"
						+ "<b style='color:"+finalPace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(finalPace.getName())+" Pace</b><br/>");
				switch(finalPace) {
					case DOM_GENTLE:
						stringBuilderForAppendingDescriptions.append(UtilText.parse(entry.getKey(),
								"[npc.Name] lets out a soft sigh as [npc.she] calms down a little..."));
						break;
					case DOM_NORMAL:
						stringBuilderForAppendingDescriptions.append(UtilText.parse(entry.getKey(),
								"[npc.Name] lets out "+(entry.getValue()==SexPace.DOM_GENTLE?"[npc.a_moan+] as [npc.she] starts getting more turned on":"a soft [npc.moan] as [npc.she] calms down a little")+"..."));
						break;
					case DOM_ROUGH:
						stringBuilderForAppendingDescriptions.append(UtilText.parse(entry.getKey(),
								"[npc.Name] lets out a wild [npc.moan] as [npc.she] finds [npc.herself] filled with lust..."));
						break;
					case SUB_EAGER:
						stringBuilderForAppendingDescriptions.append(UtilText.parse(entry.getKey(),
								"[npc.Name] lets out a desperate [npc.moan] as [npc.she] finds [npc.herself] filled with lust..."));
						break;
					case SUB_NORMAL:
						stringBuilderForAppendingDescriptions.append(UtilText.parse(entry.getKey(),
								"[npc.Name] lets out "+(entry.getValue()==SexPace.SUB_RESISTING?"[npc.a_moan+] as [npc.she] starts getting more turned on":"a soft [npc.moan] as [npc.she] calms down a little")+"..."));
						break;
					case SUB_RESISTING:
						stringBuilderForAppendingDescriptions.append(UtilText.parse(entry.getKey(),
								"[npc.Name] lets out an uncomfortable whine as [npc.she] suddenly finds [npc.herself] not happy with the current situation..."));
						break;
				}
				stringBuilderForAppendingDescriptions.append("</p>");
			}
		}
		
		return stringBuilderForAppendingDescriptions.toString();
	}
	
	private static String handleExposedDescriptions(GameCharacter characterBeingExposed, boolean atStartOfSex) {
		StringBuilder exposedSB = new StringBuilder();
		
		List<GameCharacter> charactersReacting = new ArrayList<>(Sex.getAllParticipants());
		charactersReacting.remove(characterBeingExposed);
		
		if (!areasExposed.get(characterBeingExposed).contains(CoverableArea.ANUS)) {
			if(characterBeingExposed.getGenitalArrangement()==GenitalArrangement.CLOACA) {
				if (characterBeingExposed.isCoverableAreaVisible(CoverableArea.ANUS)) {
					exposedSB.append(UtilText.parse(characterBeingExposed,
							formatCoverableAreaBecomingExposed(
									(atStartOfSex
											?"[npc.NamePos] ass was already exposed before starting sex!"
											:"[npc.NamePos] ass is now exposed!")))
							+ sexManager.getAssRevealReaction(characterBeingExposed, charactersReacting, true));
					areasExposed.get(characterBeingExposed).add(CoverableArea.ANUS);
				}
				
			} else {
				if (characterBeingExposed.isCoverableAreaVisible(CoverableArea.ANUS)) {
					exposedSB.append(UtilText.parse(characterBeingExposed,
							formatCoverableAreaBecomingExposed(
									(atStartOfSex
											?"[npc.NamePos] [npc.asshole+] was already exposed before starting sex!"
											:"[npc.NamePos] [npc.asshole+] is now exposed!")))
							+ sexManager.getAssRevealReaction(characterBeingExposed, charactersReacting, true)
							+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, SexAreaOrifice.ANUS)));
					areasExposed.get(characterBeingExposed).add(CoverableArea.ANUS);
				}
			}
		}
		//TODO test cloaca asshole description is working
		boolean cloacaToBeRevealed = false;
		if (!areasExposed.get(characterBeingExposed).contains(CoverableArea.PENIS)) {
			if (characterBeingExposed.isCoverableAreaVisible(CoverableArea.PENIS)) {
				if(characterBeingExposed.getGenitalArrangement()==GenitalArrangement.CLOACA && !areasExposed.get(characterBeingExposed).contains(CoverableArea.MOUND)) {
					cloacaToBeRevealed = true;
				}
				if (characterBeingExposed.hasPenis()) {
					exposedSB.append(UtilText.parse(characterBeingExposed,
							formatCoverableAreaBecomingExposed(
									(atStartOfSex
											?"[npc.NamePos] [npc.cock+] was already exposed before starting sex!"
											:"[npc.NamePos] [npc.cock+] is now exposed!")))
							+ sexManager.getPenisRevealReaction(characterBeingExposed, charactersReacting)
							+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, SexAreaPenetration.PENIS)));
				}
				areasExposed.get(characterBeingExposed).add(CoverableArea.PENIS);
			}
		}
		if (!areasExposed.get(characterBeingExposed).contains(CoverableArea.VAGINA)) {
			if (characterBeingExposed.isCoverableAreaVisible(CoverableArea.VAGINA)) {
				if(characterBeingExposed.getGenitalArrangement()==GenitalArrangement.CLOACA && !areasExposed.get(characterBeingExposed).contains(CoverableArea.MOUND)) {
					cloacaToBeRevealed = true;
				}
				if (characterBeingExposed.hasVagina()) {
					exposedSB.append(UtilText.parse(characterBeingExposed,
							formatCoverableAreaBecomingExposed(
									(atStartOfSex
											?"[npc.NamePos] [npc.pussy+] was already exposed before starting sex!"
											:"[npc.NamePos] [npc.pussy+] is now exposed!")))
							+ sexManager.getVaginaRevealReaction(characterBeingExposed, charactersReacting)
							+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, SexAreaOrifice.VAGINA))
							+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, SexAreaPenetration.CLIT)));

				} else if (characterBeingExposed.getVaginaType() == VaginaType.NONE && characterBeingExposed.getPenisType() == PenisType.NONE) {
					exposedSB.append(UtilText.parse(characterBeingExposed,
							formatCoverableAreaBecomingExposed(
									(atStartOfSex
											?"[npc.NamePos] doll-like mound was already exposed before starting sex!"
											:"[npc.NamePos] doll-like mound is now exposed!")))
							+ sexManager.getMoundRevealReaction(characterBeingExposed, charactersReacting));
				}
				areasExposed.get(characterBeingExposed).add(CoverableArea.VAGINA);
			}
		}
		if(cloacaToBeRevealed) {
			exposedSB.append(UtilText.parse(characterBeingExposed,
					formatCoverableAreaBecomingExposed(
							(atStartOfSex
									?"[npc.NamePos] [npc.asshole+] was already exposed before starting sex!"
									:"[npc.NamePos] [npc.asshole+] is now exposed!")))
					+ sexManager.getAssRevealReaction(characterBeingExposed, charactersReacting, false)
					+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, SexAreaOrifice.ANUS)));
			areasExposed.get(characterBeingExposed).add(CoverableArea.MOUND);
		}
		if (!areasExposed.get(characterBeingExposed).contains(CoverableArea.NIPPLES)) {
			if (characterBeingExposed.isCoverableAreaVisible(CoverableArea.NIPPLES)) {
				exposedSB.append(UtilText.parse(characterBeingExposed,
						formatCoverableAreaBecomingExposed(
								(atStartOfSex
										?"[npc.NamePos] [npc.nipples+] were already exposed before starting sex!"
										:"[npc.NamePos] [npc.nipples+] are now exposed!")))
							+ sexManager.getBreastsRevealReaction(characterBeingExposed, charactersReacting)
							+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, SexAreaOrifice.NIPPLE)));
				areasExposed.get(characterBeingExposed).add(CoverableArea.NIPPLES);
			}
		}
		if (!areasExposed.get(characterBeingExposed).contains(CoverableArea.BREASTS_CROTCH) && characterBeingExposed.hasBreastsCrotch()) {
			if (characterBeingExposed.isCoverableAreaVisible(CoverableArea.BREASTS_CROTCH)) {
				exposedSB.append(UtilText.parse(characterBeingExposed,
						formatCoverableAreaBecomingExposed(
								(atStartOfSex
										?"[npc.NamePos] [npc.crotchBoobs+] were already exposed before starting sex!"
										:"[npc.NamePos] [npc.crotchBoobs+] are now exposed!")))
							+ sexManager.getBreastsCrotchRevealReaction(characterBeingExposed, charactersReacting)
							+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, SexAreaOrifice.NIPPLE_CROTCH)));
				areasExposed.get(characterBeingExposed).add(CoverableArea.BREASTS_CROTCH);
			}
		}
		
		return exposedSB.toString();
	}

	
	private static String calculateWetAreas(boolean onSexInit) {
		StringBuilder wetSB = new StringBuilder();
		
		for(GameCharacter character : Sex.getAllParticipants()) {
			if(character.getBodyMaterial()==BodyMaterial.SLIME && onSexInit) {
				for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
					addLubricationNoAppend(character, orifice, character, LubricationType.SLIME);
				}
			}
			addLubricationNoAppend(character, SexAreaOrifice.MOUTH, character, LubricationType.SALIVA);
			addLubricationNoAppend(character, SexAreaPenetration.TONGUE, character, LubricationType.SALIVA);
			
			if(character.getBreastRawMilkStorageValue()>0) {
				wetSB.append(addLubricationNoAppend(character, SexAreaOrifice.NIPPLE, character, LubricationType.MILK));
			}

			// Add partner lubrication from cum:
			if(character.hasStatusEffect(StatusEffect.CREAMPIE_ANUS)) {
				wetSB.append(addLubricationNoAppend(character, SexAreaOrifice.ANUS, null, LubricationType.CUM));
			}
			if(character.hasStatusEffect(StatusEffect.CREAMPIE_NIPPLES)) {
				wetSB.append(addLubricationNoAppend(character, SexAreaOrifice.NIPPLE, null, LubricationType.CUM));
			}
			if(character.hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)) {
				wetSB.append(addLubricationNoAppend(character, SexAreaOrifice.VAGINA, null, LubricationType.CUM));
				wetSB.append(addLubricationNoAppend(character, SexAreaPenetration.CLIT, null, LubricationType.CUM));
			}
			
			// Add partner natural lubrications:
			if(character.getArousal() >= character.getAssWetness().getArousalNeededToGetAssWet()) {
				wetSB.append(addLubricationNoAppend(character, SexAreaOrifice.ANUS, character, LubricationType.ANAL_LUBE));
			}
			if(character.hasPenisIgnoreDildo() && character.getArousal() >= character.getPenisCumStorage().getArousalNeededToStartPreCumming()) {
				wetSB.append(addLubricationNoAppend(character, SexAreaPenetration.PENIS, character, LubricationType.PRECUM));
				addLubricationNoAppend(character, SexAreaOrifice.URETHRA_PENIS, character, LubricationType.PRECUM);
			}
			if(character.hasVagina() && character.getArousal() >= character.getVaginaWetness().getArousalNeededToGetVaginaWet()) {
				wetSB.append(addLubricationNoAppend(character, SexAreaOrifice.VAGINA, character, LubricationType.GIRLCUM));
				wetSB.append(addLubricationNoAppend(character, SexAreaPenetration.CLIT, character, LubricationType.GIRLCUM));
			}
		}
		
		// Calculate lubrication transfers:
		for(GameCharacter characterPenetrating : Sex.getAllParticipants()) {
			for(GameCharacter characterPenetrated : Sex.getAllParticipants()) {
				for(Entry<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> entry : ongoingActionsMap.get(characterPenetrating).entrySet()) {
					if(entry.getValue().containsKey(characterPenetrated)) {
						for(SexAreaInterface sArea : entry.getValue().get(characterPenetrated)) {
							wetSB.append(transferLubricationNoAppend(characterPenetrating, entry.getKey(), characterPenetrated, sArea));
						}
					}
				}
			}
		}
		if(onSexInit && !sexManager.isAppendStartingWetDescriptions()) {
			return "";
		}
		return wetSB.toString();
	}
	

	private static String transferLubricationNoAppend(GameCharacter character, SexAreaInterface characterArea, GameCharacter targetCharacter, SexAreaInterface targetArea) {
		List<String> lubricationTransferred = new ArrayList<>();
		boolean lastLubricationPlural = false;
		StringBuilder lubeSB = new StringBuilder();
		
		for(GameCharacter lubricantProvider : Sex.getAllParticipants()) {
			for(LubricationType lt : wetAreas.get(character).get(characterArea).get(lubricantProvider)) {
				if(!wetAreas.get(targetCharacter).get(targetArea).get(lubricantProvider).contains(lt)
						// Cannot lubricate with self cum or precum via penis through a condom:
						&& ((lt!=LubricationType.PRECUM && lt!=LubricationType.CUM) || characterArea!=SexAreaPenetration.PENIS || !character.isWearingCondom() || !lubricantProvider.equals(character))) {
					wetAreas.get(targetCharacter).get(targetArea).get(lubricantProvider).add(lt);
					lubricationTransferred.add((lubricantProvider==null?"":UtilText.parse(lubricantProvider, "[npc.namePos] "))+lt.getName(lubricantProvider));
					lastLubricationPlural = lt.isPlural();
				}
			}
		}
		
		if(!lubricationTransferred.isEmpty()) {
			lubeSB.append(formatCoverableAreaGettingWet(
					Util.capitaliseSentence(Util.stringsToStringList(lubricationTransferred, false))
						+(sexInitFinished
							?" quickly lubricate"+(lubricationTransferred.size()>1 || !lastLubricationPlural?"s ":" ")
							:" has already lubricated ")
						+(targetCharacter.isPlayer()?"your ":targetCharacter.getName("the")+"'s ")+targetArea.getName(targetCharacter)+"."));
		}
		
		lubricationTransferred.clear();
		
		for(GameCharacter lubricantProvider : Sex.getAllParticipants()) {
			for(LubricationType lt : wetAreas.get(targetCharacter).get(targetArea).get(lubricantProvider)) {
				if(!wetAreas.get(character).get(characterArea).get(lubricantProvider).contains(lt)
						// Cannot lubricate with self cum or precum via penis through a condom:
						&& ((lt!=LubricationType.PRECUM && lt!=LubricationType.CUM) || targetArea!=SexAreaPenetration.PENIS || !targetCharacter.isWearingCondom() || !lubricantProvider.equals(targetCharacter))) {
					wetAreas.get(character).get(characterArea).get(lubricantProvider).add(lt);
					lubricationTransferred.add((lubricantProvider==null?"":UtilText.parse(lubricantProvider, "[npc.namePos] "))+lt.getName(lubricantProvider));
					lastLubricationPlural = lt.isPlural();
				}
			}
		}
		
		if(!lubricationTransferred.isEmpty()) { //TODO check this.
			lubeSB.append(formatCoverableAreaGettingWet(
					Util.capitaliseSentence(Util.stringsToStringList(lubricationTransferred, false))
						+(sexInitFinished
							?" quickly lubricate"+(lubricationTransferred.size()>1 || !lastLubricationPlural?"s ":" ")
							:" has already lubricated ")
						+(character.isPlayer()?"your ":character.getName("the")+"'s ")+characterArea.getName(character)+"."));
		}
		
		return lubeSB.toString();
	}
	
	public static void transferLubrication(GameCharacter character, SexAreaInterface characterArea, GameCharacter targetCharacter, SexAreaInterface targetArea) {
		sexSB.append(transferLubricationNoAppend(character, characterArea, targetCharacter, targetArea));
	}
	
	private static String getLubricationDescription(GameCharacter character, SexAreaInterface area) {
		List<String> lubes = new ArrayList<>();
		for(GameCharacter lubricantProvider : Sex.getAllParticipants()) {
			for(LubricationType lube : wetAreas.get(character).get(area).get(lubricantProvider)) {
				lubes.add(UtilText.parse(lubricantProvider, character.equals(lubricantProvider)?"[npc.her] ":"[npc.namePos] ")+lube.getName(lubricantProvider));
			}
		}
		
		if(lubes.isEmpty()) {
			return "";
		}
		
		StringBuilder description = new StringBuilder(UtilText.parse(character, "[npc.NamePos] ")+area.getName(character) +" "+(area.isPlural()?"are":"is")+" lubricated with ");
		description.append(Util.stringsToStringList(lubes, false)+".");
		
		return description.toString();
	}
	
	public static void clearLubrication(GameCharacter character, SexAreaInterface sexArea) {
		for(Entry<GameCharacter, Set<LubricationType>> entry : wetAreas.get(character).get(sexArea).entrySet()) {
			try {
				entry.getValue().clear();
			} catch(Exception ex) {
			}
		}
	}
	
	private static String addLubricationNoAppend(GameCharacter characterGettingLubricated, SexAreaInterface sexArea, GameCharacter characterProvidingLubrication, LubricationType lubrication) {
		StringBuilder lubeSB = new StringBuilder();
		boolean appendDescription = true;
		
		if(appendDescription) {
			if(characterGettingLubricated.isPlayer()) {
				appendDescription =
						(lubrication!=LubricationType.SALIVA
							|| !characterGettingLubricated.equals(characterProvidingLubrication)
							|| (sexArea!=SexAreaOrifice.MOUTH && sexArea!=SexAreaPenetration.TONGUE))
						&& (sexArea != SexAreaPenetration.PENIS
							|| !characterGettingLubricated.isWearingCondom()
							|| !characterGettingLubricated.equals(characterProvidingLubrication)
							|| (lubrication != LubricationType.PRECUM && lubrication != LubricationType.CUM));
			} else {
				appendDescription = !(sexArea==SexAreaOrifice.MOUTH && lubrication==LubricationType.SALIVA)
									&& (sexArea!=SexAreaOrifice.VAGINA || (characterGettingLubricated.isCoverableAreaVisible(CoverableArea.VAGINA)))
									&& (sexArea!=SexAreaPenetration.CLIT || (characterGettingLubricated.isCoverableAreaVisible(CoverableArea.VAGINA)))
									&& (sexArea!=SexAreaOrifice.ANUS || (characterGettingLubricated.isCoverableAreaVisible(CoverableArea.ANUS)))
									&& (sexArea!=SexAreaOrifice.NIPPLE || (characterGettingLubricated.isCoverableAreaVisible(CoverableArea.NIPPLES)))
									&& (sexArea!=SexAreaPenetration.PENIS || (characterGettingLubricated.isCoverableAreaVisible(CoverableArea.PENIS)))
									&& (sexArea != SexAreaPenetration.PENIS
										|| !characterGettingLubricated.isWearingCondom()
										|| !characterGettingLubricated.equals(characterProvidingLubrication)
										|| (lubrication != LubricationType.PRECUM && lubrication != LubricationType.CUM));
			}
		}
		
		if(wetAreas.get(characterGettingLubricated).get(sexArea).get(characterProvidingLubrication).add(lubrication)) {
			if(appendDescription
					&& (sexArea!=SexAreaPenetration.TONGUE || lubrication!=LubricationType.SALIVA)) {
				if(characterProvidingLubrication==null) {
					lubeSB.append(formatCoverableAreaGettingWet(UtilText.parse(characterGettingLubricated,
							"[npc.NamePos] "+sexArea.getName(characterGettingLubricated)
							+(sexArea.isPlural()?" are":" is")
							+(sexInitFinished
									?" quickly lubricated by "
									:" already lubricated by ")
							+lubrication.getName(characterProvidingLubrication)+".")));
					
				} else {
					lubeSB.append(formatCoverableAreaGettingWet(UtilText.parse(characterGettingLubricated, characterProvidingLubrication,
							"[npc.NamePos] "+sexArea.getName(characterGettingLubricated)
							+(sexArea.isPlural()?" are":" is")
							+(sexInitFinished
									?" quickly lubricated by "
									:" already lubricated by ")
							+(characterProvidingLubrication.equals(characterGettingLubricated)?"[npc.her] ":"[npc2.namePos] ")+lubrication.getName(characterProvidingLubrication)+".")));
					
				}
			}
		}
		
		return lubeSB.toString();
	}
	
	public static void addLubrication(GameCharacter character, SexAreaInterface sexArea, GameCharacter characterProvidingLubrication, LubricationType lubrication) {
		addLubrication(character, sexArea, characterProvidingLubrication, lubrication, true);
	}
	
	public static void addLubrication(GameCharacter characterGettingLubricated, SexAreaInterface sexArea, GameCharacter characterProvidingLubrication, LubricationType lubrication, boolean appendTextToSex) {
		String description = addLubricationNoAppend(characterGettingLubricated, sexArea, characterProvidingLubrication, lubrication);
		if(appendTextToSex) {
			sexSB.append(description);
		}
	}
	
	public static void applyOngoingAction(GameCharacter characterPerformingAction, SexAreaInterface performerArea, GameCharacter characterTargeted, SexAreaInterface targetedArea, boolean freeUpSexAreas) {
		// Free up orifice and penetrator:
		if(freeUpSexAreas) {
			if(performerArea.isPenetration()) {
				if(Sex.getPenetrationTypeFreeCount(characterPerformingAction, (SexAreaPenetration)performerArea)==0) {
					Sex.stopFirstOngoingAction(characterPerformingAction, performerArea);
				}
			} else {
				Sex.stopFirstOngoingAction(characterPerformingAction, performerArea);
			}
			if(targetedArea.isPenetration()) {
				if(Sex.getPenetrationTypeFreeCount(characterTargeted, (SexAreaPenetration)targetedArea)==0) {
					Sex.stopFirstOngoingAction(characterTargeted, targetedArea);
				}
			} else {
				Sex.stopFirstOngoingAction(characterTargeted, targetedArea);
			}
		}

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
			
			incrementSexTypeCount(characterPerformingAction, characterTargeted, relatedSexTypePerformer);
			incrementSexTypeCount(characterTargeted, characterPerformingAction, relatedSexTypeTargeted);
			
			characterPerformingAction.incrementSexCount(relatedSexTypePerformer);
			characterTargeted.incrementSexCount(relatedSexTypeTargeted);

			characterPerformingAction.addSexPartner(characterTargeted, relatedSexTypePerformer);
			characterTargeted.addSexPartner(characterPerformingAction, relatedSexTypeTargeted);
			
		} else {
			System.err.println("Warning! Sex.applyPenetration() is finding 'characterPenetrated' or 'characterPenetrating' to be null!!!");
		}
	}
	
	public static void stopAllOngoingActions(GameCharacter characterPerformingAction, SexAreaInterface area) {
		for(Entry<GameCharacter, Set<SexAreaInterface>> e : new HashSet<>(Sex.ongoingActionsMap.get(characterPerformingAction).get(area).entrySet())) {
			for(SexAreaInterface sa : e.getValue()) {
				stopOngoingAction(characterPerformingAction, area, e.getKey(), sa, true);
			}
		}
		if(area==SexAreaPenetration.TONGUE) {
			for(Entry<GameCharacter, Set<SexAreaInterface>> e : new HashSet<>(Sex.ongoingActionsMap.get(characterPerformingAction).get(SexAreaOrifice.MOUTH).entrySet())) {
				for(SexAreaInterface sa : e.getValue()) {
					stopOngoingAction(characterPerformingAction, SexAreaOrifice.MOUTH, e.getKey(), sa, true);
				}
			}
		}
		if(area==SexAreaOrifice.MOUTH) {
			for(Entry<GameCharacter, Set<SexAreaInterface>> e : new HashSet<>(Sex.ongoingActionsMap.get(characterPerformingAction).get(SexAreaPenetration.TONGUE).entrySet())) {
				for(SexAreaInterface sa : e.getValue()) {
					stopOngoingAction(characterPerformingAction, SexAreaPenetration.TONGUE, e.getKey(), sa, true);
				}
			}
		}
	}

	public static void stopFirstOngoingAction(GameCharacter characterPerformingAction, SexAreaInterface area) {
		outer:
		for(Entry<GameCharacter, Set<SexAreaInterface>> e : new HashSet<>(Sex.ongoingActionsMap.get(characterPerformingAction).get(area).entrySet())) {
			for(SexAreaInterface sa : e.getValue()) {
				stopOngoingAction(characterPerformingAction, area, e.getKey(), sa, true);
				break outer;
			}
		}
		
		if(area==SexAreaPenetration.TONGUE) {
			for(Entry<GameCharacter, Set<SexAreaInterface>> e : new HashSet<>(Sex.ongoingActionsMap.get(characterPerformingAction).get(SexAreaOrifice.MOUTH).entrySet())) {
				for(SexAreaInterface sa : e.getValue()) {
					stopOngoingAction(characterPerformingAction, SexAreaOrifice.MOUTH, e.getKey(), sa, true);
				}
			}
		}
		if(area==SexAreaOrifice.MOUTH) {
			for(Entry<GameCharacter, Set<SexAreaInterface>> e : new HashSet<>(Sex.ongoingActionsMap.get(characterPerformingAction).get(SexAreaPenetration.TONGUE).entrySet())) {
				for(SexAreaInterface sa : e.getValue()) {
					stopOngoingAction(characterPerformingAction, SexAreaPenetration.TONGUE, e.getKey(), sa, true);
				}
			}
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
	
	public static void stopAllOngoingActions(GameCharacter characterPerformingAction, SexAreaInterface performerArea, GameCharacter characterTargeted, boolean appendRemovalText) {
		if(ongoingActionsMap.get(characterPerformingAction).get(performerArea).containsKey(characterTargeted)) {
			List<SexAreaInterface> areasToClear = new ArrayList<>(ongoingActionsMap.get(characterPerformingAction).get(performerArea).get(characterTargeted));
			
			for(SexAreaInterface targetedArea : areasToClear) {
				if(ongoingActionsMap.get(characterPerformingAction).get(performerArea).get(characterTargeted).remove(targetedArea)) {
					ongoingActionsMap.get(characterTargeted).get(targetedArea).get(characterPerformingAction).remove(performerArea);
					if(appendRemovalText && characterTargeted!=null) {
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
	}

	/**
	 * @return The description of this action being stopped.
	 */
	public static String stopOngoingAction(GameCharacter characterPerformingAction, SexAreaInterface performerArea, GameCharacter characterTargeted, SexAreaInterface targetedArea) {
		return stopOngoingAction(characterPerformingAction, performerArea, characterTargeted, targetedArea, true);
	}
	
	/**
	 * @return The description of this action being stopped.
	 */
	public static String stopOngoingAction(GameCharacter characterPerformingAction, SexAreaInterface performerArea, GameCharacter characterTargeted, SexAreaInterface targetedArea, boolean appendRemovalText) {
		String removalText = "";
		if(ongoingActionsMap.get(characterPerformingAction).get(performerArea).containsKey(characterTargeted)) {
			if(ongoingActionsMap.get(characterPerformingAction).get(performerArea).get(characterTargeted).remove(targetedArea)) {
				ongoingActionsMap.get(characterTargeted).get(targetedArea).get(characterPerformingAction).remove(performerArea);
				if(characterTargeted!=null) {
					removalText = formatStopPenetration(characterTargeted.getStopPenetrationDescription(characterPerformingAction, performerArea, characterTargeted, targetedArea));
					if(appendRemovalText) {
						sexSB.append(removalText);
					}
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
		return removalText;
	}

	private static boolean displayOngoingPenetrationEffects = false;
	
	private static String applyPenetrationEffects(SexActionInterface sexAction, GameCharacter characterPenetrating, SexAreaPenetration penetrationType, GameCharacter characterPenetrated, SexAreaInterface orifice) { //TODO formatting
		StringBuilder penetrationSB = new StringBuilder();
		
		if(orifice.isOrifice()) {
			SexAreaOrifice actualOrifice = (SexAreaOrifice) orifice;
			
			SexType relatedSexTypeForCharacterPenetrating = new SexType(SexParticipantType.NORMAL, penetrationType, orifice);
			SexType relatedSexTypeForCharacterPenetrated = new SexType(SexParticipantType.NORMAL, orifice, penetrationType);
			
			String penileVirginityLoss = "";
			
			if (penetrationType == SexAreaPenetration.PENIS) {
				if(characterPenetrating.isPenisVirgin()
						&& characterPenetrating.hasPenisIgnoreDildo()
						&& actualOrifice.isInternalOrifice()) {
					penileVirginityLoss = characterPenetrating.getVirginityLossPenetrationDescription(characterPenetrating, SexAreaPenetration.PENIS, characterPenetrated, actualOrifice);
					if(characterPenetrated.hasFetish(Fetish.FETISH_DEFLOWERING)) {
						characterPenetrated.incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(characterPenetrated), true);
					}
					characterPenetrated.incrementFetishExperience(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_DEFLOWERING.getExperienceGainFromSexAction());
					characterPenetrating.setVirginityLoss(relatedSexTypeForCharacterPenetrating, characterPenetrated, characterPenetrated.getLostVirginityDescriptor());
					characterPenetrating.setPenisVirgin(false);
				}
				
			}
			
			if (actualOrifice == SexAreaOrifice.ASS) {
				if (initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.ASS)) {
					penetrationSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					
					initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.ASS);
					
				} else if(displayOngoingPenetrationEffects) {
					penetrationSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
				}
					
			} else if (actualOrifice == SexAreaOrifice.BREAST) {
				if (initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.BREAST)) {
					penetrationSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					
					initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.BREAST);
					
				} else if(displayOngoingPenetrationEffects) {
					penetrationSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
				}
					
			} else if (actualOrifice == SexAreaOrifice.ANUS) {
				if (initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.ANUS)) {
					penetrationSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					
					if (characterPenetrated.isAssVirgin()) {
						if (penetrationType.isTakesVirginity()) {
							penetrationSB.append(characterPenetrating.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, characterPenetrated, SexAreaOrifice.ANUS));
							if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
								characterPenetrating.incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating), true);
							}
							characterPenetrating.incrementFetishExperience(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_DEFLOWERING.getExperienceGainFromSexAction());
							characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating, characterPenetrating.getLostVirginityDescriptor());
							characterPenetrated.setAssVirgin(false);
						}
					}
					initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.ANUS);
					
				} else if(displayOngoingPenetrationEffects) {
					penetrationSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
				}
					
			} else if (actualOrifice == SexAreaOrifice.VAGINA) {
				if (initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.VAGINA)) {
					penetrationSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					
					if (characterPenetrated.isVaginaVirgin()) {
						if (penetrationType.isTakesVirginity()) {
							penetrationSB.append(characterPenetrating.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, characterPenetrated, SexAreaOrifice.VAGINA));
							if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
								characterPenetrating.incrementExperience(Fetish.getExperienceGainFromTakingVaginalVirginity(characterPenetrating), true);
							}
							characterPenetrating.incrementFetishExperience(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_DEFLOWERING.getExperienceGainFromSexAction());
							characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating, characterPenetrating.getLostVirginityDescriptor());
							characterPenetrated.setVaginaVirgin(false);
						}
					}
					initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.VAGINA);
					
				} else if(displayOngoingPenetrationEffects) {
					penetrationSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
				}
				
			} else if (actualOrifice == SexAreaOrifice.NIPPLE) {
				if (initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.NIPPLE)) {
						penetrationSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
						
						if (characterPenetrated.isNippleVirgin()) {
							if (penetrationType.isTakesVirginity()) {
								penetrationSB.append(characterPenetrating.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, characterPenetrated, SexAreaOrifice.NIPPLE));
								if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
									characterPenetrating.incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating), true);
								}
								characterPenetrating.incrementFetishExperience(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_DEFLOWERING.getExperienceGainFromSexAction());
								characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating, characterPenetrating.getLostVirginityDescriptor());
								characterPenetrated.setNippleVirgin(false);
							}
						}
						initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.NIPPLE);
						
					} else if(displayOngoingPenetrationEffects) {
						penetrationSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					}
					
			} else if (actualOrifice == SexAreaOrifice.URETHRA_PENIS) {
				if (initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.URETHRA_PENIS)) {
						penetrationSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
						
						if (characterPenetrated.isUrethraVirgin()) {
							if (penetrationType.isTakesVirginity()) {
								penetrationSB.append(characterPenetrating.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, characterPenetrated, SexAreaOrifice.URETHRA_PENIS));
								if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
									characterPenetrating.incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating), true);
								}
								characterPenetrating.incrementFetishExperience(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_DEFLOWERING.getExperienceGainFromSexAction());
								characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating, characterPenetrating.getLostVirginityDescriptor());
								characterPenetrated.setUrethraVirgin(false);
							}
						}
						initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.URETHRA_PENIS);
						
					} else if(displayOngoingPenetrationEffects) {
						penetrationSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					}
				
			} else if (actualOrifice == SexAreaOrifice.URETHRA_VAGINA) {
				if (initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.URETHRA_VAGINA)) {
					penetrationSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					
					if (characterPenetrated.isVaginaUrethraVirgin()) {
						if (penetrationType.isTakesVirginity()) {
							penetrationSB.append(characterPenetrating.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, characterPenetrated, SexAreaOrifice.URETHRA_VAGINA));
							if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
								characterPenetrating.incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating), true);
							}
							characterPenetrating.incrementFetishExperience(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_DEFLOWERING.getExperienceGainFromSexAction());
							characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating, characterPenetrating.getLostVirginityDescriptor());
							characterPenetrated.setVaginaUrethraVirgin(false);
						}
					}
					initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.URETHRA_VAGINA);
					
				} else if(displayOngoingPenetrationEffects) {
					penetrationSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
				}
			
			} else if (actualOrifice == SexAreaOrifice.MOUTH) {
				if (initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.MOUTH)) {
						penetrationSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
						
						if (characterPenetrated.isFaceVirgin()) {
							if (penetrationType.isTakesVirginity()) {
								penetrationSB.append(characterPenetrating.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, characterPenetrated, SexAreaOrifice.MOUTH));
								if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
									characterPenetrating.incrementExperience(Fetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating), true);
								}
								characterPenetrating.incrementFetishExperience(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_DEFLOWERING.getExperienceGainFromSexAction());
								characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating, characterPenetrating.getLostVirginityDescriptor());
								characterPenetrated.setFaceVirgin(false);
							}
						}
						initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.MOUTH);
						
					} else if(displayOngoingPenetrationEffects) {
						penetrationSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					}
					
			}
			
			penetrationSB.append(penileVirginityLoss);
			
		} else { // Pen/pen stuff:
			if (penetrationType == SexAreaPenetration.FINGER && orifice == SexAreaPenetration.PENIS) {
				if (initialPenetrations.get(characterPenetrated).contains(SexAreaPenetration.PENIS)) {
					penetrationSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, orifice)));
					
					initialPenetrations.get(characterPenetrated).remove(SexAreaPenetration.PENIS);
					
				} else if(displayOngoingPenetrationEffects) {
					penetrationSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, orifice)));
				}
				
			} else if (penetrationType == SexAreaPenetration.FOOT && orifice == SexAreaPenetration.PENIS) {
				if (initialPenetrations.get(characterPenetrated).contains(SexAreaPenetration.PENIS)) {
					if(sexAction.getActionType()==SexActionType.START_ONGOING) {
						if(sexAction.getPerformingCharacterPenetrations().contains(SexAreaPenetration.PENIS)) {
							penetrationSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrated, SexAreaPenetration.PENIS, characterPenetrating, SexAreaPenetration.FOOT)));
						} else {
							penetrationSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, SexAreaPenetration.FOOT, characterPenetrated, SexAreaPenetration.PENIS)));
						}
					}
					
					initialPenetrations.get(characterPenetrated).remove(SexAreaPenetration.PENIS);
					initialPenetrations.get(characterPenetrated).remove(SexAreaPenetration.FOOT);

					initialPenetrations.get(characterPenetrating).remove(SexAreaPenetration.PENIS);
					initialPenetrations.get(characterPenetrating).remove(SexAreaPenetration.FOOT);
					
				} else if(displayOngoingPenetrationEffects) {
					penetrationSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, orifice)));
				}
			}
		}
		
		
		// TODO apply masochism effects to stretching:
		
		// Stretching effects (will only stretch from penises):
		if (penetrationType == SexAreaPenetration.PENIS) {
			
			boolean lubed = false;
			for(GameCharacter lubricantProvider : Sex.getAllParticipants()) {
				if(!wetAreas.get(characterPenetrated).get(orifice).get(lubricantProvider).isEmpty()) {
					lubed = true;
					break;
				}
			}
			boolean twoPenisesInOrifice = Sex.getOngoingCharactersUsingAreas(characterPenetrated, orifice, SexAreaPenetration.PENIS).size()>1;

			areasCurrentlyStretching.get(characterPenetrated).clear();
			if (orifice == SexAreaOrifice.ANUS){
				if (Capacity.isPenisSizeTooBig((int)characterPenetrated.getAssStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
					penetrationSB.append(characterPenetrated.getStretchingDescription(characterPenetrating, penetrationType, SexAreaOrifice.ANUS));

					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementAssStretchedCapacity((((float)characterPenetrating.getPenisRawSizeValue())-characterPenetrated.getAssStretchedCapacity())*characterPenetrated.getAssElasticity().getStretchModifier());
					if(characterPenetrated.getAssStretchedCapacity()>characterPenetrating.getPenisRawSizeValue())
						characterPenetrated.setAssStretchedCapacity(characterPenetrating.getPenisRawSizeValue());

					areasCurrentlyStretching.get(characterPenetrated).add(SexAreaOrifice.ANUS);

					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenisSizeTooBig((int)characterPenetrated.getAssStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
						penetrationSB.append(characterPenetrated.getStretchingFinishedDescription(SexAreaOrifice.ANUS));
						areasCurrentlyStretching.get(characterPenetrated).remove(SexAreaOrifice.ANUS);
					}

					areasStretched.get(characterPenetrated).add(SexAreaOrifice.ANUS);

				} else if(Capacity.isPenisSizeTooSmall((int)characterPenetrated.getAssStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
					penetrationSB.append(characterPenetrated.getTooLooseDescription(SexAreaOrifice.ANUS));
					areasTooLoose.get(characterPenetrated).add(SexAreaOrifice.ANUS);
				}

			} else if (orifice == SexAreaOrifice.VAGINA){
				if (Capacity.isPenisSizeTooBig((int)characterPenetrated.getVaginaStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
					penetrationSB.append(characterPenetrated.getStretchingDescription(characterPenetrating, penetrationType, SexAreaOrifice.VAGINA));
					
					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementVaginaStretchedCapacity((((float)characterPenetrating.getPenisRawSizeValue())-characterPenetrated.getVaginaStretchedCapacity())*characterPenetrated.getVaginaElasticity().getStretchModifier());
					if(characterPenetrated.getVaginaStretchedCapacity()>characterPenetrating.getPenisRawSizeValue()) {
						characterPenetrated.setVaginaStretchedCapacity(characterPenetrating.getPenisRawSizeValue());
					}
					
					areasCurrentlyStretching.get(characterPenetrated).add(SexAreaOrifice.VAGINA);
					
					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenisSizeTooBig((int)characterPenetrated.getVaginaStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
						penetrationSB.append(characterPenetrated.getStretchingFinishedDescription(SexAreaOrifice.VAGINA));
						areasCurrentlyStretching.get(characterPenetrated).remove(SexAreaOrifice.VAGINA);
					}

					areasStretched.get(characterPenetrated).add(SexAreaOrifice.VAGINA);

				} else if(Capacity.isPenisSizeTooSmall((int)characterPenetrated.getVaginaStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
					penetrationSB.append(characterPenetrated.getTooLooseDescription(SexAreaOrifice.VAGINA));
					areasTooLoose.get(characterPenetrated).add(SexAreaOrifice.VAGINA);
				}

			}else if (orifice == SexAreaOrifice.NIPPLE){
				if (Capacity.isPenisSizeTooBig((int)characterPenetrated.getNippleStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
					penetrationSB.append(characterPenetrated.getStretchingDescription(characterPenetrating, penetrationType, SexAreaOrifice.NIPPLE));

					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementNippleStretchedCapacity((((float)characterPenetrating.getPenisRawSizeValue())-characterPenetrated.getNippleStretchedCapacity())*characterPenetrated.getNippleElasticity().getStretchModifier());
					if(characterPenetrated.getNippleStretchedCapacity()>characterPenetrating.getPenisRawSizeValue())
						characterPenetrated.setNippleStretchedCapacity(characterPenetrating.getPenisRawSizeValue());

					areasCurrentlyStretching.get(characterPenetrated).add(SexAreaOrifice.NIPPLE);

					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenisSizeTooBig((int)characterPenetrated.getNippleStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
						penetrationSB.append(characterPenetrated.getStretchingFinishedDescription(SexAreaOrifice.NIPPLE));
						areasCurrentlyStretching.get(characterPenetrated).remove(SexAreaOrifice.NIPPLE);
					}

					areasStretched.get(characterPenetrated).add(SexAreaOrifice.NIPPLE);

				}else if(Capacity.isPenisSizeTooSmall((int)characterPenetrated.getNippleStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
					penetrationSB.append(characterPenetrated.getTooLooseDescription(SexAreaOrifice.NIPPLE));
					areasTooLoose.get(characterPenetrated).add(SexAreaOrifice.NIPPLE);
				}

			} else if (orifice == SexAreaOrifice.URETHRA_PENIS){
				if (Capacity.isPenisSizeTooBig((int)characterPenetrated.getPenisStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
					penetrationSB.append(characterPenetrated.getStretchingDescription(characterPenetrating, penetrationType, SexAreaOrifice.URETHRA_PENIS));

					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementPenisStretchedCapacity((((float)characterPenetrating.getPenisRawSizeValue())-characterPenetrated.getPenisStretchedCapacity())*characterPenetrated.getUrethraElasticity().getStretchModifier());
					if(characterPenetrated.getPenisStretchedCapacity()>characterPenetrating.getPenisRawSizeValue())
						characterPenetrated.setPenisStretchedCapacity(characterPenetrating.getPenisRawSizeValue());

					areasCurrentlyStretching.get(characterPenetrated).add(SexAreaOrifice.URETHRA_PENIS);

					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenisSizeTooBig((int)characterPenetrated.getPenisStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
						penetrationSB.append(characterPenetrated.getStretchingFinishedDescription(SexAreaOrifice.URETHRA_PENIS));
						areasCurrentlyStretching.get(characterPenetrated).remove(SexAreaOrifice.URETHRA_PENIS);
					}

					areasStretched.get(characterPenetrated).add(SexAreaOrifice.URETHRA_PENIS);

				}else if(Capacity.isPenisSizeTooSmall((int)characterPenetrated.getPenisStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
					penetrationSB.append(characterPenetrated.getTooLooseDescription(SexAreaOrifice.URETHRA_PENIS));
					areasTooLoose.get(characterPenetrated).add(SexAreaOrifice.URETHRA_PENIS);
				}

			} else if (orifice == SexAreaOrifice.URETHRA_VAGINA){
				if (Capacity.isPenisSizeTooBig((int)characterPenetrated.getVaginaUrethraStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
					penetrationSB.append(characterPenetrated.getStretchingDescription(characterPenetrating, penetrationType, SexAreaOrifice.URETHRA_VAGINA));

					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementVaginaUrethraStretchedCapacity((((float)characterPenetrating.getPenisRawSizeValue())-characterPenetrated.getVaginaUrethraStretchedCapacity())*characterPenetrated.getVaginaUrethraElasticity().getStretchModifier());
					if(characterPenetrated.getVaginaUrethraStretchedCapacity()>characterPenetrating.getPenisRawSizeValue())
						characterPenetrated.setVaginaUrethraStretchedCapacity(characterPenetrating.getPenisRawSizeValue());

					areasCurrentlyStretching.get(characterPenetrated).add(SexAreaOrifice.URETHRA_VAGINA);

					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenisSizeTooBig((int)characterPenetrated.getVaginaUrethraStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
						penetrationSB.append(characterPenetrated.getStretchingFinishedDescription(SexAreaOrifice.URETHRA_VAGINA));
						areasCurrentlyStretching.get(characterPenetrated).remove(SexAreaOrifice.URETHRA_VAGINA);
					}

					areasStretched.get(characterPenetrated).add(SexAreaOrifice.URETHRA_VAGINA);

				}else if(Capacity.isPenisSizeTooSmall((int)characterPenetrated.getVaginaUrethraStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
					penetrationSB.append(characterPenetrated.getTooLooseDescription(SexAreaOrifice.URETHRA_VAGINA));
					areasTooLoose.get(characterPenetrated).add(SexAreaOrifice.URETHRA_VAGINA);
				}

			} else if (orifice == SexAreaOrifice.MOUTH){
				if (Capacity.isPenisSizeTooBig((int)characterPenetrated.getFaceStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)){
					penetrationSB.append(characterPenetrated.getStretchingDescription(characterPenetrating, penetrationType, SexAreaOrifice.MOUTH));

					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementFaceStretchedCapacity((((float)characterPenetrating.getPenisRawSizeValue())-characterPenetrated.getFaceStretchedCapacity())*characterPenetrated.getFaceElasticity().getStretchModifier());
					if(characterPenetrated.getFaceStretchedCapacity()>characterPenetrating.getPenisRawSizeValue())
						characterPenetrated.setFaceStretchedCapacity(characterPenetrating.getPenisRawSizeValue());

					areasCurrentlyStretching.get(characterPenetrated).add(SexAreaOrifice.MOUTH);

					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenisSizeTooBig((int)characterPenetrated.getFaceStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), lubed, twoPenisesInOrifice)) {
						penetrationSB.append(characterPenetrated.getStretchingFinishedDescription(SexAreaOrifice.MOUTH));
						areasCurrentlyStretching.get(characterPenetrated).remove(SexAreaOrifice.MOUTH);
					}

					areasStretched.get(characterPenetrated).add(SexAreaOrifice.MOUTH);

				}else if(Capacity.isPenisSizeTooSmall((int)characterPenetrated.getFaceStretchedCapacity(), characterPenetrating.getPenisRawSizeValue(), twoPenisesInOrifice)){
					penetrationSB.append(characterPenetrated.getTooLooseDescription(SexAreaOrifice.MOUTH));
				}
			}
		}
		
		return penetrationSB.toString();
	}

	/**
	 * Removes a piece of clothing.
	 *
	 * @return SexActionUtility.CLOTHING_REMOVAL
	 */
	public static SexActionInterface manageClothingToAccessCoverableArea(GameCharacter characterManagingClothing, GameCharacter targetForManagement, CoverableArea coverableArea) {
		
		SimpleEntry<AbstractClothing, DisplacementType> clothingRemoval = targetForManagement.getNextClothingToRemoveForCoverableAreaAccess(coverableArea);
		if (clothingRemoval == null || clothingRemoval.getKey() == null) {
			Sex.setUnequipClothingText(null,
					UtilText.parse(characterManagingClothing, targetForManagement, "[npc.Name] can't find a piece of [npc2.namePos] clothing to remove in order to access the slot '"+coverableArea+"'. (This is a bug...)"));
			System.err.println("manageClothingToAccessCoverableArea() can't find clothing - CoverableArea."+coverableArea.toString());
			return SexActionUtility.CLOTHING_REMOVAL;
		}
		
		clothingBeingRemoved = clothingRemoval.getKey();

		if (clothingRemoval.getValue() == DisplacementType.REMOVE_OR_EQUIP) {
			targetForManagement.unequipClothingOntoFloor(clothingBeingRemoved, false, characterManagingClothing);
			Sex.setUnequipClothingText(clothingBeingRemoved, targetForManagement.getUnequipDescription());

		} else {
			targetForManagement.isAbleToBeDisplaced(clothingBeingRemoved, clothingRemoval.getValue(), true, false, characterManagingClothing);
			Sex.setDisplaceClothingText(clothingBeingRemoved, targetForManagement.getDisplaceDescription());
		}

		return SexActionUtility.CLOTHING_REMOVAL;
	}

	public static boolean isInForeplay(GameCharacter character) {
		return character.getArousal()<ArousalLevel.ONE_TURNED_ON.getMaximumValue() && Sex.getNumberOfOrgasms(character)==0 && Sex.getSexManager().isPartnerUsingForeplayActions();
	}
	
	public static boolean isInForeplay() {
		return isInForeplay(Sex.getActivePartner());
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

	public static void setSubHasEqualControl(boolean subHasEqualControl) {
		Sex.subHasEqualControl = subHasEqualControl;
	}

	public static void setForcedSexControl(GameCharacter character, SexControl sexControl) {
		forcedSexControlMap.put(character, sexControl);
	}
	
	public static SexControl getSexControl(GameCharacter character) {
		if(forcedSexControlMap.get(character)!=null) {
			return forcedSexControlMap.get(character);
		}
		return initialSexManager.getSexControl(character);
	}
	
	public static boolean isPositionChangingAllowed(GameCharacter characterWantingToChangePosition) {
		if(Sex.isMasturbation()) {
			return true;
		}
		if(isCharacterBannedFromPositioning(characterWantingToChangePosition)
				|| Sex.isCharacterForbiddenByOthersFromPositioning(characterWantingToChangePosition)
				|| Sex.isDom(characterWantingToChangePosition)==Sex.isDom(Sex.getTargetedPartner(characterWantingToChangePosition))) { // Don't allow position changing if the character/target are sub/sub or dom/dom as it can break positioning
			return false;
		}
		
		return Sex.getInitialSexManager().isPositionChangingAllowed(characterWantingToChangePosition);
	}

	public static boolean isPositionMenuChangingAllowed(GameCharacter characterWantingToChangePosition) {
		if(Sex.isMasturbation()) {
			return true;
		}
		if(isCharacterBannedFromPositioning(characterWantingToChangePosition)
				|| Sex.isCharacterForbiddenByOthersFromPositioning(characterWantingToChangePosition)) {
			return false;
		}
		
		return Sex.getInitialSexManager().isPositionChangingAllowed(characterWantingToChangePosition);
	}
	
	public static List<SexType> getRequestsBlocked(GameCharacter character) {
		return requestsBlocked.get(character);
	}

	public static void addRequestsBlocked(GameCharacter character, SexType sexTypeRequest) {
		if(!requestsBlocked.get(character).contains(sexTypeRequest)) {
			requestsBlocked.get(character).add(sexTypeRequest);
		}
	}
	
	public static List<AbstractSexPosition> getPositioningRequestsBlocked(GameCharacter character) {
		return positioningRequestsBlocked.get(character);
	}

	public static void addPositioningRequestsBlocked(GameCharacter character, AbstractSexPosition position) {
		if(!positioningRequestsBlocked.get(character).contains(position)) {
			positioningRequestsBlocked.get(character).add(position);
		}
	}
	
	public static boolean isPositioningRequestBlocked(GameCharacter character, AbstractSexPosition position) {
		return positioningRequestsBlocked.get(character).contains(position);
	}

	/**
	 * @param targeter The character whose target is to be found.
	 * @return The character that the 'targeter' is currently focusing on.
	 */
	public static GameCharacter getTargetedPartner(GameCharacter targeter) {
		if(targeter!=null
				&& targeter.isPlayer()
				&& Sex.allParticipants!=null
				&& Sex.getAllParticipants(false).size()>1) {
			return activePartner;
			
		} else {
			if(Sex.isMasturbation()) {
				return Main.game.getPlayer();
			}
			return targetedCharacters.get(targeter);
		}
	}
	
	public static boolean isAbleToTarget(GameCharacter character) {
		return Sex.getSexPositionSlot(character)!=SexSlotGeneric.MISC_WATCHING;
	}
	
	public static void setTargetedPartner(GameCharacter targeter, GameCharacter targetedCharacter) {
		if(Sex.getSexPositionSlot(targetedCharacter)==SexSlotGeneric.MISC_WATCHING) {
			System.err.println("Sex error: "+targeter.getName(true)+" attempted to target the spectator: "+targetedCharacter.getName(true));
			return; // Cannot target spectators.
		}
		targetedCharacters.put(targeter, targetedCharacter);
	}

	public static NPC getActivePartner() {
		return activePartner;
	}
	
	public static void setActivePartner(NPC character) {
		if(Sex.getSexPositionSlot(character)==SexSlotGeneric.MISC_WATCHING) {
//			System.err.println("Sex error: Player attempted to target the spectator: "+character.getName());
			return; // Cannot target spectators.
		}
		if(!Sex.getDominantParticipants(false).keySet().contains(character) && !Sex.getSubmissiveParticipants(false).keySet().contains(character)) {
			throw new IllegalArgumentException("This character ("+character.getName(true)+") is not in the sex scene!");
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
		GameCharacter target = Sex.getTargetedPartner(Sex.getCharacterPerformingAction());
		if(target==null) {
			return getCharacterPerformingAction();
		}
		return target;
	}

	public static int getTotalParticipantCount(boolean includeSpectators) {
		return getAllParticipants(includeSpectators).size();
	}
	
	public static boolean isMasturbation() {
		return Sex.getAllParticipants(false).size()==1;
	}
	
	public static List<GameCharacter> getAllParticipants(boolean includeSpectators) {
		List<GameCharacter> returnCharacters = new ArrayList<>();
		for(GameCharacter character : Sex.allParticipants) {
			if(includeSpectators || Sex.getSexPositionSlot(character)==null || Sex.getSexPositionSlot(character)!=SexSlotGeneric.MISC_WATCHING) {
				returnCharacters.add(character);
			}
		}
		return returnCharacters;
	}
	
	public static List<GameCharacter> getAllParticipants() {
		return getAllParticipants(true);
	}

	public static Map<GameCharacter, SexSlot> getAllOccupiedSlots(boolean includeSpectators) {
		Map<GameCharacter, SexSlot> allParticipantsMap = new HashMap<>();
		allParticipantsMap.putAll(getDominantParticipants(includeSpectators));
		allParticipantsMap.putAll(getSubmissiveParticipants(includeSpectators));
		return allParticipantsMap;
	}
	
	public static Map<GameCharacter, SexSlot> getDominantParticipants(boolean includeSpectators) {
		if(includeSpectators) {
			return dominants;
		} else {
			Map<GameCharacter, SexSlot> map = new HashMap<>(dominants);
			for(GameCharacter character : Sex.getDominantSpectators()) {
				map.remove(character);
			}
			return map;
		}
	}
	
	public static Map<GameCharacter, SexSlot> getSubmissiveParticipants(boolean includeSpectators) {
		if(includeSpectators) {
			return submissives;
		} else {
			Map<GameCharacter, SexSlot> map = new HashMap<>(submissives);
			for(GameCharacter character : Sex.getSubmissiveSpectators()) {
				map.remove(character);
			}
			return map;
		}
	}
	
	/**
	 * @return true if this character is the leader of either the submissives or dominants.
	 */
	public static boolean isSexLeader(GameCharacter character) {
		return (!dominants.isEmpty() && dominants.keySet().iterator().next().equals(character))
				|| (!submissives.isEmpty() && submissives.keySet().iterator().next().equals(character));
	}
	
	public static List<GameCharacter> getDominantSpectators() {
		return dominantSpectators;
	}
	
	public static List<GameCharacter> getSubmissiveSpectators() {
		return submissiveSpectators;
	}
	
	public static boolean isSpectator(GameCharacter character) {
		return dominantSpectators.contains(character) || submissiveSpectators.contains(character);
	}
	
	public static String getUnequipClothingText() {
		return unequipClothingText;
	}

	public static void setUnequipClothingText(AbstractClothing clothing, String unequipClothingText) {
		Sex.unequipClothingText =
						"<p style='text-align:center;'>"
								+ "<i style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Clothing removal</i>"+(clothing==null?"":": "+Util.capitaliseSentence(clothing.getName()))
								+"<br/>"
								+ unequipClothingText
						+ "</p>";
	}

	public static void setUnequipWeaponText(AbstractWeapon weapon, String unequipClothingText) {
		Sex.unequipClothingText = 
				"<p style='text-align:center;'>"
						+ "<i style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Weapon removal</i>"+(weapon==null?"":": "+Util.capitaliseSentence(weapon.getName()))
						+"<br/>"
						+ unequipClothingText
				+ "</p>";
	}
	
	public static void setDisplaceClothingText(AbstractClothing clothing, String unequipClothingText) {
		Sex.unequipClothingText = 
				"<p style='text-align:center;'>"
						+ "<i style='color:" + Colour.GENERIC_MINOR_BAD.toWebHexString() + ";'>Clothing displacement</i>"+(clothing==null?"":": "+Util.capitaliseSentence(clothing.getName()))
						+"<br/>"
						+ unequipClothingText
				+ "</p>";
	}
	
	public static void setEquipClothingText(AbstractClothing clothing, String unequipClothingText) {
		Sex.unequipClothingText =
				"<p style='text-align:center;'>"
						+ "<i style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Clothing equip</i>"+(clothing==null?"":": "+Util.capitaliseSentence(clothing.getName()))
						+"<br/>"
						+ unequipClothingText
				+ "</p>";
	}

	public static void setJinxRemovalClothingText(AbstractClothing clothing, String unequipClothingText) {
		Sex.unequipClothingText = 
				"<p style='text-align:center;'>"
						+ "<i style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Jinx removal</i>"+(clothing==null?"":": "+Util.capitaliseSentence(clothing.getName()))
						+"<br/>"
						+ unequipClothingText
				+ "</p>";
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
	
	public static boolean isCharacterEngagedInOngoingAction(GameCharacter character, GameCharacter target) {
		if(!Sex.getAllParticipants().contains(character)) {
			System.err.println("isCharacterEngagedInOngoingAction("+character.getId()+") 1: This character is not in Sex!"); 
			return false;
		}
		if(!Sex.getAllParticipants().contains(target)) {
			System.err.println("isCharacterEngagedInOngoingAction("+target.getId()+") 1: This character is not in Sex!"); 
			return false;
		}

		for(Entry<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> entry : ongoingActionsMap.get(character).entrySet()) {
			if(entry.getValue().containsKey(target)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isCharacterEngagedInOngoingAction(GameCharacter character) {
		if(!Sex.getAllParticipants().contains(character)) {
			System.err.println("isCharacterEngagedInOngoingAction("+character.getId()+") 2: This character is not in Sex!"); 
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
					if(entry.getValue().containsKey(characterOrifice) && entry.getValue().get(characterOrifice)!=null && entry.getValue().get(characterOrifice).contains(orifice)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	public static int getPenetrationTypeFreeCount(GameCharacter penetrator, SexAreaPenetration penetrationType) {
		int penetrationTypesAvailable = 1;
		
		switch(penetrationType) {
			case FINGER:
				penetrationTypesAvailable = penetrator.getArmRows()*2;
				break;
			case PENIS:
				if(!penetrator.hasPenis()) {
					return 0;
				}
				break;
			case TAIL:
				if(penetrator.getTailType()==TailType.NONE|| penetrator.getTailCount()==0) {
					return 0;
				}
				penetrationTypesAvailable = penetrator.getTailCount();
				break;
			case TENTACLE:
				if(Sex.getSexPositionSlot(penetrator).isStanding(penetrator)) {
					// Need at least three legs to remain upright (if more than 4):
					penetrationTypesAvailable = Math.max(1, penetrator.getLegConfiguration().getNumberOfTentacles()-3);
				} else {
					penetrationTypesAvailable = penetrator.getLegConfiguration().getNumberOfTentacles();
				}
				break;
			case TONGUE:
				break;
			case CLIT:
				if(!penetrator.hasVagina()) {
					return 0;
				}
				break;
			case FOOT:
				if(Sex.getSexPositionSlot(penetrator).isStanding(penetrator)) {
					// Need at least three legs to remain upright (if more than 4):
					penetrationTypesAvailable = Math.max(1, penetrator.getLegConfiguration().getNumberOfLegs()-3);
				} else {
					penetrationTypesAvailable = penetrator.getLegConfiguration().getNumberOfLegs();
				}
				break;
		}
		if(!Sex.isSpectator(penetrator)) {
			Map<SexAreaPenetration, Integer> restrictionMap = Sex.getPosition().getRestrictedPenetrationCounts(penetrator);
			if(restrictionMap!=null && restrictionMap.containsKey(penetrationType)) {
				penetrationTypesAvailable+=restrictionMap.get(penetrationType);
			}
		}
		int totalAreasUsed = 0;
		for(GameCharacter target : Sex.allParticipants) {
			if(ongoingActionsMap.get(penetrator).get(penetrationType).containsKey(target)) {
				totalAreasUsed += ongoingActionsMap.get(penetrator).get(penetrationType).get(target).size();
			}
		}
		return penetrationTypesAvailable-totalAreasUsed;
	}
	
	public static boolean isPenetrationTypeFree(GameCharacter penetrator, SexAreaPenetration penetrationType) {
		return getPenetrationTypeFreeCount(penetrator, penetrationType)>0;
	}
	
	/**
	 * @return true if the penetrator is using their penetrationType on another character.
	 */
	public static boolean isPenetrationNonSelfOngoingAction(GameCharacter penetrator, SexAreaPenetration penetrationType) {
		for(GameCharacter target : Sex.allParticipants) {
			if(!penetrator.equals(target)) {
				if(ongoingActionsMap.get(penetrator).containsKey(penetrationType)) {
					if(ongoingActionsMap.get(penetrator).get(penetrationType).containsKey(target)) {
						return !ongoingActionsMap.get(penetrator).get(penetrationType).get(target).isEmpty();
					}
				}
			}
		}
		return false;
	}


	// Orgasm convenience methods:

	public static boolean isAnyCharacterReadyToOrgasm(boolean includeSpectators) {
		for(GameCharacter character : Sex.getAllParticipants(includeSpectators)) {
			if(isReadyToOrgasm(character)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isReadyToOrgasm(GameCharacter character) {
		return character.getArousal()>=ArousalLevel.FIVE_ORGASM_IMMINENT.getMaximumValue();
	}
	
	/**
	 * @return The character who is orgasming this turn, or, if there is no such character, the character who last orgasmed. (Might return null.)
	 */
	public static GameCharacter getCharacterOrgasming() {
		return characterOrgasming;
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
	 * Makes the supplied character the primary character who is interacting with the targetedCharacter's targetsSexArea.
	 * The 'primary' character is not used much, other than for some sex descriptions.
	 * e.g. In multiple-character blowjobs, the 'primary' character is the one considered to currently have the target's cock in their mouth, while the others are simply kissing/licking the sides.
	 * @param character The character to make the primary interacting character.
	 * @param targetedCharacter The character who is being interacted with.
	 * @param targetsSexArea The area which is being interacted with.
	 */
	public static void setPrimaryOngoingCharacter(GameCharacter character, GameCharacter targetedCharacter, SexAreaInterface targetsSexArea) {
		Set<Entry<GameCharacter, Set<SexAreaInterface>>> entrySet = new HashSet<>(ongoingActionsMap.get(targetedCharacter).get(targetsSexArea).entrySet());
		
		Map<GameCharacter, Set<SexAreaInterface>> entries = new HashMap<>();
		for(Entry<GameCharacter, Set<SexAreaInterface>> e : entrySet) {
			entries.put(e.getKey(), e.getValue());
		}
		
		ongoingActionsMap.get(targetedCharacter).get(targetsSexArea).clear();
		
		ongoingActionsMap.get(targetedCharacter).get(targetsSexArea).put(character, entries.get(character));

		for(Entry<GameCharacter, Set<SexAreaInterface>> e : entrySet) {
			if(!e.getKey().equals(character)) {
				ongoingActionsMap.get(targetedCharacter).get(targetsSexArea).put(e.getKey(), e.getValue());
			}
		}
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
	
	public static List<SexAreaInterface> getContactingSexAreas(GameCharacter character, SexAreaInterface sexArea, GameCharacter characterInteractingWith) {
		if(ongoingActionsMap.get(character).get(sexArea).containsKey(characterInteractingWith)) {
			return new ArrayList<>(ongoingActionsMap.get(character).get(sexArea).get(characterInteractingWith));
		} else {
			return new ArrayList<>();
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
	 * @return The characters who are currently using this character's sexArea.
	 */
	public static List<GameCharacter> getCharactersUsingSexAreaOnCharacter(GameCharacter characterTargeted, SexAreaInterface sexAreaBeingUsed) {
		List<GameCharacter> returnList = new ArrayList<>();
		for(GameCharacter character : Sex.getAllParticipants()) {
			if(ongoingActionsMap.get(character).get(sexAreaBeingUsed).containsKey(characterTargeted)) {
				returnList.add(character);
			}
		}
		return returnList;
	}
	
	/**
	 * @return The characters who are currently using their sexAreaUsing to contact this character's sexAreaBeingUsed.
	 */
	public static Set<GameCharacter> getOngoingCharactersUsingAreas(GameCharacter character, SexAreaInterface sexAreaBeingUsed, SexAreaInterface sexAreaUsing) {
		Set<GameCharacter> characters = new HashSet<>();
		
		if(!ongoingActionsMap.get(character).containsKey(sexAreaBeingUsed)) {
			return characters;
		}
		
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

	public static List<GameCharacter> getCharactersHavingOngoingActionWith(GameCharacter character, SexAreaInterface area) {
		if(area.isOrifice()) {
			return getCharactersHavingOngoingActionWith(character, (SexAreaOrifice) area);
		} else {
			return getCharactersHavingOngoingActionWith(character, (SexAreaPenetration) area);
		}
	}
	
	/**
	 * Returns a set, not a single instance of GameCharacter, as the 'characterPenetrating' could be penetrating multiple characters with tentacles, tails, hands, etc.
	 */
	public static List<GameCharacter> getCharactersHavingOngoingActionWith(GameCharacter characterPenetrating, SexAreaPenetration penetration) {
		return new ArrayList<>(ongoingActionsMap.get(characterPenetrating).get(penetration).keySet());
	}
	
	/**
	 * Returns a set, not a single instance of GameCharacter, as the 'characterPenetrating' could be penetrating multiple characters with tentacles, tails, hands, etc.
	 */
	public static List<GameCharacter> getCharactersHavingOngoingActionWith(GameCharacter characterPenetrated, SexAreaOrifice orifice) {
		if(ongoingActionsMap==null || ongoingActionsMap.get(characterPenetrated)==null || ongoingActionsMap.get(characterPenetrated).get(orifice)==null) {
			return new ArrayList<>();
		}
		return new ArrayList<>(ongoingActionsMap.get(characterPenetrated).get(orifice).keySet());
	}
	
	public static Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>> getWetAreas(GameCharacter character) {
		return wetAreas.get(character);
	}
	
	public static boolean hasLubricationTypeFromAnyone(GameCharacter characterHavingLubrication, SexAreaInterface sexArea) {
		for(GameCharacter lubricantProvider : Sex.getAllParticipants()) {
			if(getWetAreas(characterHavingLubrication)!=null && !getWetAreas(characterHavingLubrication).get(sexArea).get(lubricantProvider).isEmpty()) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean hasLubricationTypeFromAnyone(GameCharacter characterHavingLubrication, SexAreaInterface sexArea, LubricationType lubrication) {
		for(GameCharacter lubricantProvider : Sex.getAllParticipants()) {
			if(getWetAreas(characterHavingLubrication).get(sexArea).get(lubricantProvider).contains(lubrication)) {
				return true;
			}
		}
		
		return false;
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
		setSexManager(sexManager, Sex.getDominantSpectators(), Sex.getSubmissiveSpectators());
	}
	
	public static void setSexManager(SexManagerInterface sexManager, List<GameCharacter> dominantSpectators, List<GameCharacter> submissiveSpectators) {
		Sex.allParticipants = new ArrayList<>(sexManager.getDominants().keySet());
		Sex.allParticipants.addAll(sexManager.getSubmissives().keySet());
		Sex.allParticipants.addAll(dominantSpectators);
		Sex.allParticipants.addAll(submissiveSpectators);
		Sex.dominantSpectators = new ArrayList<>(dominantSpectators);
		Sex.submissiveSpectators = new ArrayList<>(submissiveSpectators);
		
		Sex.resetAllOngoingActions(!sexInitFinished);
		
		// Add dominants to map, with the leader as the first entry:
		List<GameCharacter> tempCharacterList = new ArrayList<>(sexManager.getDominants().keySet());
		tempCharacterList.addAll(dominantSpectators);
		GameCharacter leader = tempCharacterList.contains(Main.game.getPlayer())?Main.game.getPlayer():tempCharacterList.get(0);
		Collections.sort(tempCharacterList, (p1, p2) -> p1.equals(leader)
				?-1
				:(p2.equals(leader)
					?1
					:(sexManager.getDominants().containsKey(p1)
						?sexManager.getDominants().containsKey(p2)
							?p1.getName(true).compareTo(p2.getName(true))
							:-1
						:sexManager.getDominants().containsKey(p2)
							?1
							:p1.getName(true).compareTo(p2.getName(true)))));
		
		Sex.dominants = new LinkedHashMap<>();
		for(GameCharacter character : tempCharacterList) {
			if(sexManager.getDominants().containsKey(character)) {
				Sex.dominants.put(character, sexManager.getDominants().get(character));
			} else {
				Sex.dominants.put(character, SexSlotGeneric.MISC_WATCHING);
			}
		}
		if(!Sex.isDom(Main.game.getPlayer())) {
			if(!tempCharacterList.isEmpty()) {
				activePartner = (NPC) tempCharacterList.get(0);
			} else {
				activePartner = null;
			}
		}

		// Add submissives to map, with the leader as the first entry:
		tempCharacterList = new ArrayList<>(sexManager.getSubmissives().keySet());
		tempCharacterList.addAll(submissiveSpectators);
		if(!tempCharacterList.isEmpty()) {
			GameCharacter leader2 = tempCharacterList.contains(Main.game.getPlayer())?Main.game.getPlayer():tempCharacterList.get(0);
			
			Collections.sort(tempCharacterList, (p1, p2) -> p1.equals(leader2)
					?-1
					:(p2.equals(leader2)
						?1
						:(sexManager.getSubmissives().containsKey(p1)
							?sexManager.getSubmissives().containsKey(p2)
								?p1.getName(true).compareTo(p2.getName(true))
								:-1
							:sexManager.getSubmissives().containsKey(p2)
								?1
								:p1.getName(true).compareTo(p2.getName(true)))));
		}
		Sex.submissives = new LinkedHashMap<>();
		for(GameCharacter character : tempCharacterList) {
			if(sexManager.getSubmissives().containsKey(character)) {
				Sex.submissives.put(character, sexManager.getSubmissives().get(character));
			} else {
				Sex.submissives.put(character, SexSlotGeneric.MISC_WATCHING);
			}
		}
		if(Sex.isDom(Main.game.getPlayer())) {
			if(!tempCharacterList.isEmpty()) {
				activePartner = (NPC) tempCharacterList.get(0);
			} else {
				activePartner = null;
			}
		}
		
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
		
		Sex.sexManager = sexManager;
		
		Main.game.setInSex(true);
		
		updateAvailableActions();
		
		for(GameCharacter character : Sex.getAllParticipants()) {
			if(!character.isPlayer()) {
				Sex.getSexManager().assignNPCTarget(character);
			}
			// Make sure subs switched to doms and vice versa have correct paces:
			if(Sex.getSexPace(character).isDom() != Sex.isDom(character)) {
				Sex.setSexPace(character, Sex.getSexPace(character).getOppositeDomEquivalent());
			}
			if(actionsAvailable.get(Main.game.getPlayer()).containsKey(character)) {
				repeatActionsPlayer.removeIf((action) -> !actionsAvailable.get(Main.game.getPlayer()).get(character).contains(action));
			}
		}
		
		sexSB.append(
				"<p style='text-align:center;'><b>New position:</b> <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>"+Sex.sexManager.getPosition().getName()+"</b><br/>"
				+"<i><b>"+Sex.sexManager.getPosition().getDescription(Sex.getAllOccupiedSlots(false))+"</b></i></p>");
	}
	
	public static void resetAllOngoingActions(boolean includeSpectators) {
		if(ongoingActionsMap==null) {
			ongoingActionsMap = new LinkedHashMap<>();
		}
		for(GameCharacter character : Sex.getAllParticipants()) {
			if(!Sex.isSpectator(character) || includeSpectators) {
				ongoingActionsMap.put(character, new LinkedHashMap<>());
				for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
					ongoingActionsMap.get(character).put(orifice, new LinkedHashMap<>());
				}
				for(SexAreaPenetration penetration : SexAreaPenetration.values()) {
					ongoingActionsMap.get(character).put(penetration, new LinkedHashMap<>());
				}
			}
		}
	}
	
	public static void updateAvailableActions() {
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
				if((!character.equals(target) || Sex.isMasturbation())
						&& (Sex.sexManager.getPosition().getAllAvailableSexPositions().contains(Sex.getSexPositionSlot(character)) || Sex.getSexPositionSlot(character)==SexSlotGeneric.MISC_WATCHING)) {
					
					//TODO Handle cloacas in here as well:
					if((character instanceof NPC) && ((NPC) character).getLimitedSexClasses()!=null) {
						for(SexActionInterface action : ((NPC)character).getLimitedSexClasses()) {
							Sex.addSexAction(
									character,
									target,
									Sex.sexManager.getPosition().getSexInteractions(Sex.getSexPositionSlot(character), Sex.getSexPositionSlot(target)),
									action,
									action.getParticipantType()==SexParticipantType.SELF,
									action.getParticipantType()==SexParticipantType.SELF);
//							try {
//								System.out.println("A: "+action.getActionTitle()+" "+actionsAvailable.get(target).get(character).contains(action));
//							} catch(Exception ex) {
//								System.out.println("A: null "+actionsAvailable.get(target).get(character).contains(action));
//							}
						}
						
					} else if((target instanceof NPC) && ((NPC) target).getLimitedSexClasses()!=null) {
						// Do nothing?
						
					} else {
						SexActionInteractions interactions;
						if(Sex.getSexPositionSlot(character)==SexSlotGeneric.MISC_WATCHING || Sex.getSexPositionSlot(target)==SexSlotGeneric.MISC_WATCHING) {
							interactions = StandardSexActionInteractions.spectator;
						} else {
							interactions = Sex.sexManager.getPosition().getSexInteractions(Sex.getSexPositionSlot(character), Sex.getSexPositionSlot(target));
						}
						
						if(interactions!=null) {
							// Cloaca reassignment (removing all anus interactions, as well as copying all vagina interactions and in those copies replacing vagina with anus):
							if(character.getGenitalArrangement()==GenitalArrangement.CLOACA || target.getGenitalArrangement()==GenitalArrangement.CLOACA) {
								interactions = getCloacaHandledInteractions(interactions, character, target);
							}
							
							if(Sex.sexManager.getPosition().isAddStandardActions()) {
								addActionsFromContainingClasses(character, target, interactions, SexActionPresets.allCommonActions);
							}
							if(Sex.sexManager.getPosition().getPositioningClasses()!=null) {
								addActionsFromContainingClasses(character, target, interactions, Sex.sexManager.getPosition().getPositioningClasses());
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
		}
	}
	
	private static SexActionInteractions getCloacaHandledInteractions(SexActionInteractions interactions, GameCharacter character, GameCharacter target) {
		SexActionInteractions interactionsCopy = new SexActionInteractions(null, interactions.getAvailableCumTargets(), interactions.getProvidedCumTargets());
		boolean characterCloaca = character.getGenitalArrangement()==GenitalArrangement.CLOACA;
		boolean targetCloaca = target.getGenitalArrangement()==GenitalArrangement.CLOACA;
		
		for(Entry<SexAreaInterface, List<SexAreaInterface>> entry : interactions.getInteractions().entrySet()) {
			if(entry.getKey()!=SexAreaOrifice.ANUS || !characterCloaca) {
				List<SexAreaInterface> targetedAreas = new ArrayList<>(entry.getValue());
				if(targetCloaca) {
					targetedAreas.stream().filter(area -> !area.equals(SexAreaOrifice.ANUS)).collect(Collectors.toList());
					for(SexAreaInterface area : entry.getValue()) {
						if(area.equals(SexAreaOrifice.VAGINA)) {
							targetedAreas.add(SexAreaOrifice.ANUS);
						}
					}
				}
				if(entry.getKey()==SexAreaOrifice.VAGINA && characterCloaca) {
					interactionsCopy.getInteractions().put(SexAreaOrifice.ANUS, new ArrayList<>(targetedAreas));
				}
				interactionsCopy.getInteractions().put(entry.getKey(), targetedAreas);
			}
		}
		
		return interactionsCopy;
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
						
						addSexAction(character, target, interactions, action, addedForCharacter, addedForTarget);
					}
				}
			}
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	private static void addSexAction(GameCharacter character, GameCharacter target, SexActionInteractions interactions, SexActionInterface action, boolean addedForCharacter, boolean addedForTarget) {
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
		
		if(Sex.isMasturbation()
				?action.getParticipantType()==SexParticipantType.SELF || action.getCategory()==SexActionCategory.POSITIONING
				:((Sex.getSexPositionSlot(character)!=SexSlotGeneric.MISC_WATCHING && Sex.getSexPositionSlot(target)!=SexSlotGeneric.MISC_WATCHING)
						|| action.getParticipantType()==SexParticipantType.SELF
						|| (Sex.isDom(character) && action.getCategory()==SexActionCategory.POSITIONING)
						|| action.getActionType().isOrgasmOption()
						|| action.getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)) {
			
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
	
	
	public static Set<SexActionInterface> getActionsAvailablePlayer() {
		return actionsAvailable.get(Main.game.getPlayer()).get(Sex.getTargetedPartner(Main.game.getPlayer()));
	}

	public static Set<SexActionInterface> getActionsAvailablePartner(GameCharacter characterPerformingAction, GameCharacter target) {
		return actionsAvailable.get(characterPerformingAction).get(target);
	}

	public static Set<SexActionInterface> getOrgasmActionsPlayer() {
		return orgasmActionsAvailable.get(Main.game.getPlayer()).get(Sex.getTargetedPartner(Main.game.getPlayer()));
	}

	public static Set<SexActionInterface> getOrgasmActionsPartner(GameCharacter characterPerformingAction, GameCharacter target) {
		return orgasmActionsAvailable.get(characterPerformingAction).get(target);
	}

//	public static Set<SexActionInterface> getMutualOrgasmActions() {
//		return mutualOrgasmActions;
//	}
	
	public static List<SexActionInterface> getAvailableSexActionsPlayer() {
		return availableSexActionsPlayer;
	}

	public static SexActionInterface getLastUsedPlayerAction() {
		return getLastUsedSexAction(Main.game.getPlayer());
	}

	public static SexActionInterface getLastUsedSexAction(GameCharacter character) {
		if(lastUsedSexAction!=null && lastUsedSexAction.containsKey(character)) {
			return lastUsedSexAction.get(character);
		} else {
			return null;
		}
	}

	public static SexType getForeplayPreference(NPC character, GameCharacter targetedCharacter) {
		return Sex.getSexManager().getForeplayPreference(character, targetedCharacter);
	}
	
	public static SexType getMainSexPreference(NPC character, GameCharacter targetedCharacter) {
		return Sex.getSexManager().getMainSexPreference(character, targetedCharacter);
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
	
	/**
	 * @param position The position in which the returned character is currently occupying.
	 * @return null if no character is in the slot.
	 */
	public static GameCharacter getCharacterInPosition(SexSlot position) {
//		if(!Sex.isSexStarted()) {
//			return Main.game.getPlayer(); // This is just a catch for when calculating maximum slot size before sex has started.
//		}
		
		for(Entry<GameCharacter, SexSlot> entry : Sex.dominants.entrySet()) {
			if(entry.getValue()==position) {
				return entry.getKey();
			}
		}
		for(Entry<GameCharacter, SexSlot> entry : Sex.submissives.entrySet()) {
			if(entry.getValue()==position) {
				return entry.getKey();
			}
		}
		return null;
	}
	
	public static SexSlot getSexPositionSlot(GameCharacter character) {
		if(Sex.dominants.keySet().contains(character)) {
			return Sex.dominants.get(character);
			
		} else if(Sex.submissives.keySet().contains(character)) {
			return Sex.submissives.get(character);
		}
		
		throw new IllegalArgumentException("The passed character: "+character.getName(true)+" in Sex.getSexPositionSlot(character) is not detected as a participant in this Sex scene!");
	}
	
	public static void swapSexPositionSlots(GameCharacter character1, GameCharacter character2) {
		SexSlot characterSlot1 = Sex.getSexPositionSlot(character1);
		SexSlot characterSlot2 = Sex.getSexPositionSlot(character2);
		
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
		
		Sex.resetAllOngoingActions(false);
		
		updateAvailableActions();
	}
	
	public static List<OrgasmCumTarget> getAvailableCumTargets(GameCharacter orgasmingCharacter) {
		if(Sex.getSexPositionSlot(orgasmingCharacter)==SexSlotGeneric.MISC_WATCHING || Sex.getSexPositionSlot(Sex.getTargetedPartner(orgasmingCharacter))==SexSlotGeneric.MISC_WATCHING) {
			return StandardSexActionInteractions.spectator.getAvailableCumTargets();
			
		} else {
			GameCharacter target = Sex.getTargetedPartner(orgasmingCharacter);
			
			return Util.mergeLists(
					Sex.sexManager.getPosition().getSexInteractions(Sex.getSexPositionSlot(orgasmingCharacter), Sex.getSexPositionSlot(target)).getAvailableCumTargets(),
					Sex.sexManager.getPosition().getSexInteractions(Sex.getSexPositionSlot(target), Sex.getSexPositionSlot(orgasmingCharacter)).getProvidedCumTargets());
		}
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
	
	public static Set<SexAreaOrifice> getAreasStretched(GameCharacter character) {
		return areasStretched.get(character);
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

	public static int getNumberOfDeniedOrgasms(GameCharacter characterDenied) {
		int denied = 0;
		for(Map<GameCharacter, Integer> value : deniedOrgasmsCountMap.values()) {
			if(value.containsKey(characterDenied)) {
				denied+=value.get(characterDenied);
			}
		}
		return denied;
	}
	
	public static int getNumberOfDeniedOrgasms(GameCharacter denier, GameCharacter target) {
		deniedOrgasmsCountMap.putIfAbsent(denier, new HashMap<>());
		deniedOrgasmsCountMap.get(denier).putIfAbsent(target, 0);
		return deniedOrgasmsCountMap.get(denier).get(target);
	}
	
	public static void setNumberOfDeniedOrgasms(GameCharacter denier, GameCharacter target, int count) {
		deniedOrgasmsCountMap.putIfAbsent(denier, new HashMap<>());
		deniedOrgasmsCountMap.get(denier).put(target, count);
	}
	
	public static void incrementNumberOfDeniedOrgasms(GameCharacter denier, GameCharacter target, int increment) {
		deniedOrgasmsCountMap.putIfAbsent(denier, new HashMap<>());
		deniedOrgasmsCountMap.get(denier).putIfAbsent(target, 0);
		deniedOrgasmsCountMap.get(denier).put(target, deniedOrgasmsCountMap.get(denier).get(target)+increment);
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
	
	public static int getSexTypeCount(GameCharacter performer, GameCharacter partner, SexType sexType) {
		if(sexCountMap.containsKey(performer)) {
			if(sexCountMap.get(performer).containsKey(partner)) {
				if(sexCountMap.get(performer).get(partner).containsKey(sexType)) {
					return sexCountMap.get(performer).get(partner).get(sexType);
				}
			}
		}
		return 0;
	}
	
	public static void incrementSexTypeCount(GameCharacter performer, GameCharacter partner, SexType sexType) {
		sexCountMap.putIfAbsent(performer, new HashMap<>());
		sexCountMap.get(performer).putIfAbsent(partner, new HashMap<>());
		sexCountMap.get(performer).get(partner).putIfAbsent(sexType, 0);
		
		sexCountMap.get(performer).get(partner).put(sexType, sexCountMap.get(performer).get(partner).get(sexType)+1);
	}
	
	public static AbstractSexPosition getPosition() {
		return sexManager.getPosition();
	}

	public static boolean isCharacterAllowedToUseSelfActions(GameCharacter character) {
		return !charactersSelfActionsBlocked.contains(character);
	}

	public static void setCharacterAllowedToUseSelfActions(GameCharacter character, boolean allowedToUseSelfActions) {
		if(allowedToUseSelfActions) {
			charactersSelfActionsBlocked.remove(character);
		} else {
			charactersSelfActionsBlocked.add(character);
		}
	}

	public static boolean isCanRemoveSelfClothing(GameCharacter character) {
		if(charactersBannedFromRemovingSelfClothing.contains(character)) {
			return false;
		}
		return initialSexManager.isAbleToRemoveSelfClothing(character);
	}

	public static void setCanRemoveSelfClothing(GameCharacter character, boolean canRemoveSelfClothing) {
		if(canRemoveSelfClothing) {
			charactersBannedFromRemovingSelfClothing.remove(character);
		} else {
			charactersBannedFromRemovingSelfClothing.add(character);
		}
	}
	
	public static boolean isCanRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
		if(charactersBannedFromRemovingOthersClothing.contains(character)) {
			return false;
		}
		return initialSexManager.isAbleToRemoveOthersClothing(character, clothing);
	}

	public static void setCanRemoveOthersClothing(GameCharacter character, boolean canRemoveOthersClothing) {
		if(canRemoveOthersClothing) {
			charactersBannedFromRemovingOthersClothing.remove(character);
		} else {
			charactersBannedFromRemovingOthersClothing.add(character);
		}
	}
	
	public static boolean isSelfTransformDisabled(GameCharacter character) {
		return initialSexManager.isSelfTransformDisabled(character);
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

	public static DialogueNode getPostSexDialogue() {
		return postSexDialogue;
	}

	public static DialogueNode getSexDialogue() {
		return SEX_DIALOGUE;
	}

	public static int getTurn() {
		return turn;
	}
	
	public static boolean isDoubleFootJob(GameCharacter charactersFeet) {
		for(AbstractClothing clothing : charactersFeet.getClothingCurrentlyEquipped()) {
			if(clothing.getClothingType().getItemTags(clothing.getSlotEquippedTo()).contains(ItemTag.SPREADS_FEET)) {
				return false;
			}
		}
		
		return !Sex.getSexPositionSlot(charactersFeet).isStanding(charactersFeet);
	}
	
	/**
	 * @return true if any character is <= 60% the size of another character.
	 */
	public static boolean isSizeDifference(List<GameCharacter> sexParticipants) {
		for(int i=0;i<sexParticipants.size();i++) {
			for(int j=i+1;j<sexParticipants.size();j++) {
				float h1 = sexParticipants.get(i).getHeightValue();
				float h2 = sexParticipants.get(j).getHeightValue();
				
				if(h2/h1<0.6f || h1/h2<0.6f) {
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * @return true if any character is <= 60% the size of another character.
	 */
	public static boolean isSizeDifference() {
		return isSizeDifference(Sex.getAllParticipants());
	}

	public static PositioningData getPositionRequest() {
		return positionRequest;
	}

	public static void setPositionRequest(PositioningData positionRequest) {
		Sex.positionRequest = positionRequest;
	}
	
	public static Set<GameCharacter> getCharactersSealed() {
		return charactersSealed;
	}
	
	public static boolean isCharacterSealed(GameCharacter character) {
		return getCharactersSealed().contains(character);
	}
	
	public static boolean addCharacterSealed(GameCharacter character) {
		return getCharactersSealed().add(character);
	}

	public static boolean removeCharacterSealed(GameCharacter character) {
		return getCharactersSealed().remove(character);
	}
	
	public static Set<GameCharacter> getCharactersBannedFromPositioning() {
		return charactersBannedFromPositioning;
	}
	
	public static boolean isCharacterBannedFromPositioning(GameCharacter character) {
		return getCharactersBannedFromPositioning().contains(character);
	}
	
	public static boolean addCharacterBannedFromPositioning(GameCharacter character) {
		return getCharactersBannedFromPositioning().add(character);
	}

	public static boolean removeCharacterBannedFromPositioning(GameCharacter character) {
		return getCharactersBannedFromPositioning().remove(character);
	}
	
	public static Set<GameCharacter> getCharactersForbiddenByOthersFromPositioning() {
		return charactersForbiddenByOthersFromPositioning;
	}
	
	public static boolean isCharacterForbiddenByOthersFromPositioning(GameCharacter character) {
		return getCharactersForbiddenByOthersFromPositioning().contains(character);
	}
	
	public static boolean addCharacterForbiddenByOthersFromPositioning(GameCharacter character) {
		return getCharactersForbiddenByOthersFromPositioning().add(character);
	}

	public static boolean removeCharacterForbiddenByOthersFromPositioning(GameCharacter character) {
		return getCharactersForbiddenByOthersFromPositioning().remove(character);
	}

	public static Value<GameCharacter, Class<? extends BodyPartInterface>> getCreampieLockedBy() {
		return creampieLockedBy;
	}

	public static void setCreampieLockedBy(Value<GameCharacter, Class<? extends BodyPartInterface>> creampieLockedBy) {
		Sex.creampieLockedBy = creampieLockedBy;
	}
	
	public static boolean isBipedSex() {
		for(GameCharacter character : Sex.getSubmissiveParticipants(false).keySet()) {
			if(character.getLegConfiguration()!=LegConfiguration.BIPEDAL) {
				return false;
			}
		}
		for(GameCharacter character : Sex.getDominantParticipants(false).keySet()) {
			if(character.getLegConfiguration()!=LegConfiguration.BIPEDAL) {
				return false;
			}
		}
		return true;
	}

	public static boolean isOverridePlayerArousalRestriction() {
		return overridePlayerArousalRestriction;
	}

	public static void setOverridePlayerArousalRestriction(boolean overridePlayerArousalRestriction) {
		Sex.overridePlayerArousalRestriction = overridePlayerArousalRestriction;
	}
	
	public static boolean isCharacterDeniedOrgasm(GameCharacter character) {
		return charactersDeniedOrgasm.contains(character);
	}
	
	public static boolean addCharacterDeniedOrgasm(GameCharacter character) {
		return charactersDeniedOrgasm.add(character);
	}
	
	public static boolean removeCharacterDeniedOrgasm(GameCharacter character) {
		return charactersDeniedOrgasm.remove(character);
	}

	public static Set<GameCharacter> getCharactersGrewCock() {
		return charactersGrewCock;
	}

	public static void setCharactersGrewCock(Set<GameCharacter> charactersGrewCock) {
		Sex.charactersGrewCock = charactersGrewCock;
	}
	
	public static boolean isRemoveEndSexAffection(GameCharacter character) {
		return removeEndSexAffection.contains(character);
	}

	public static void addRemoveEndSexAffection(GameCharacter character) {
		Sex.removeEndSexAffection.add(character);
	}

	public static Set<GameCharacter> getCharactersRequestingCreampie() {
		return charactersRequestingCreampie;
	}

	public static Set<GameCharacter> getCharactersRequestingPullout() {
		return charactersRequestingPullout;
	}
	
	public static boolean isCharacterObeyingTarget(GameCharacter character, GameCharacter target) {
		if(Sex.isMasturbation()
				|| (character.isSlave() && character.getObedienceValue()<ObedienceLevel.POSITIVE_ONE_AGREEABLE.getMinimumValue())) { // Unruly slaves do what they want
			return false;
		}
		
		return Sex.getSexControl(target).getValue()>=Sex.getSexControl(character).getValue()
				|| character.getFetishDesire(Fetish.FETISH_SUBMISSIVE).isPositive();
	}
	
	/**
	 * @param character The character who is thinking about pulling out.
	 * @return A positive value if they want to obey requests to pull out, or a negative value if they want to obey request to creampie. 0 is no requests.
	 */
	public static int getRequestedPulloutWeighting(GameCharacter character) {
		int weighting = 0;
		
		for(GameCharacter pulloutRequester : Sex.getCharactersRequestingPullout()) {
			if(isCharacterObeyingTarget(character, pulloutRequester)) {
				if(pulloutRequester.hasPerkAnywhereInTree(Perk.CONVINCING_REQUESTS)) {
					weighting+=20;
				} else {
					weighting+=5;
				}
			}
		}
		for(GameCharacter creampieRequester : Sex.getCharactersRequestingCreampie()) {
			if(isCharacterObeyingTarget(character, creampieRequester)) {
				if(creampieRequester.hasPerkAnywhereInTree(Perk.CONVINCING_REQUESTS)) {
					weighting-=20;
				} else {
					weighting-=5;
				}
			}
		}
		
		return weighting;
	}
	
	public static boolean isOngoingActionsBlockingSpeech(GameCharacter character) {
		try {
			for(Set<SexAreaInterface> saList : Sex.getOngoingActionsMap(character).get(SexAreaOrifice.MOUTH).values()) {
				for(SexAreaInterface sa : saList) {
					if(sa.isPenetration() && ((SexAreaPenetration)sa).isTakesVirginity()) {
						return true;
					}
				}
			}
		} catch(Exception ex) {}
		return false;
	}
}
