package com.lilithsthrone.game.sex;

import java.lang.reflect.Field;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.FluidStored;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.pregnancy.FertilisationType;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.ParserTag;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.sex.managers.SexManagerExternal;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.game.sex.managers.SexManagerLoader;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.StandardSexActionInteractions;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.game.sex.sexActions.PositioningData;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionManager;
import com.lilithsthrone.game.sex.sexActions.SexActionPresets;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.SexActionUtility;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericActions;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.MiscActions;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.BaseColour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.utils.comparators.ClothingZLayerComparator;
import com.lilithsthrone.world.Cell;

/**
 * Singleton enforced by Enum Call initialiseSex() before using.
 * Then call startSex(), which returns the starting DialogueNode.
 * 
 * Lasciate ogni speranza, voi ch'entrate.
 *
 * @since 0.1.0
 * @version 0.4.2.1
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
	
	// Managers of external content:
	private SexManagerLoader sexManagerLoader;
	private SexActionManager sexActionManager;
	
	// Sex variables:

	private StringBuilder sexSB = new StringBuilder();
	private int turn = 1;

	private boolean sexInitFinished;
	private boolean sexStarted = false;
	private boolean consensual;
	private boolean subHasEqualControl;
	private boolean publicSex;
	private boolean playerUniqueActions = false; // Set to true when the player's turn consists of unique actions.
	private boolean overridePlayerArousalRestriction; // Set to true to prevent player's arousal locking at 99 during a turn of sex. Is reset to false after every turn.
	
	public boolean playerLevelDrain; // When set to true and player has 'orgasmic level drain' perk, orgasming partners lose a level.
	
	private Map<GameCharacter, SexSlot> dominants;
	private Map<GameCharacter, SexSlot> submissives;
	private List<GameCharacter> allParticipants;
	private List<GameCharacter> dominantSpectators;
	private List<GameCharacter> submissiveSpectators;
	private Map<GameCharacter, GameCharacter> targetedCharacters;
	private Value<Integer, GameCharacter> preOrgasmTargeting;
	private GameCharacter characterPerformingAction;
	private GameCharacter characterOrgasming;
	private GameCharacter characterLayingEggs; // Usually null, but if there is an ongoing egg-laying orgasm action, this variable is set to the character who is laying eggs
	
	private Value<GameCharacter, Value<GameCharacter, AbstractClothing>> clothingSelfEquipInformation; // The character self-equipping clothing, the character targeted, and the clothing being equipped.
	private Value<GameCharacter, Value<GameCharacter, AbstractClothing>> clothingEquipInformation; // The character equipping clothing, the character targeted, and the clothing being equipped.
	
	private Value<GameCharacter, Value<GameCharacter, AbstractItem>> itemUseInformation; // The character using an item, the character targeted, and the item being used.
	private Map<GameCharacter, Map<GameCharacter, List<AbstractItemType>>> itemUseDenials; // A map of item-using NPCs to a map of characters who they've tried, and failed, to use items on. This second map has the failed items in a List as its Value.
	
	private SexManagerInterface sexManager;
	private SexManagerInterface initialSexManager;
	private String sexDescription;
	private String unequipClothingText;
	private String dyeClothingText;
	private String usingItemText;

	private String endSexDescription;
	
	private List<SexActionInterface> availableSexActionsPlayer;
	private List<SexActionInterface> characterSwitchActionsPlayer;
	private List<SexActionInterface> miscActionsPlayer;
	private List<SexActionInterface> selfActionsPlayer;
	private List<SexActionInterface> sexActionsPlayer;
	private List<SexActionInterface> positionActionsPlayer;
	private List<SexActionInterface> repeatActionsPlayer;
	private List<SexActionInterface> availableSexActionsPartner;
	
	private Map<GameCharacter, SexPace> forceSexPaceMap;

	private Map<GameCharacter, Set<SexAreaInterface>> initialPenetrations;
	
	// Actions that are currently available from all SexPositionSlots. First key is character whose actions they are, second key is target.
	private Map<GameCharacter, Map<GameCharacter, Set<SexActionInterface>>> actionsAvailable;
	private Map<GameCharacter, Map<GameCharacter, Set<SexActionInterface>>> orgasmActionsAvailable;
	
	private DialogueNode postSexDialogue;

	private Map<GameCharacter, SexActionInterface> lastUsedSexAction;
	
	
	// Tracking statuses:
	
	/**Maps: character who is lubricated -> Map of areas -> Map of owner of lubrication -> lubrications*/
	private Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> wetAreas;
	private Map<GameCharacter, Set<SexAreaOrifice>> areasCurrentlyStretching;
	private Map<GameCharacter, Set<SexAreaOrifice>> areasStretched;
	private Map<GameCharacter, Set<SexAreaOrifice>> areasTooLoose;
	private Map<GameCharacter, List<CoverableArea>> areasExposed;

	/**Maps: character -> character's areas -> Map of characters mapped to sets of sexAreas that are contacting character's sexArea*/
	private Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>>> ongoingActionsMap;
	
	/** Maps: character who is doing the knotting -> character they are knotted to */
	private Map<GameCharacter, GameCharacter> charactersKnottedTogether;
	
	
	// Counting for stats:
	private Map<GameCharacter, Integer> orgasmCountMap;
	/**Maps: Characters performing denials -> Map of characters they've denied mapped to the number of times they were denied. */
	private Map<GameCharacter, Map<GameCharacter, Integer>> deniedOrgasmsCountMap;
	private Map<GameCharacter, Map<GameCharacter, Map<SexType, Integer>>> sexCountMap;
	private Map<GameCharacter, Map<GameCharacter, Map<SexAreaInterface, Integer>>> cummedInsideMap;

	
	// Positioning, requests, tracking:
	private Map<GameCharacter, List<SexType>> requestsBlocked;
	private Map<GameCharacter, List<AbstractSexPosition>> positioningRequestsBlocked;
	private PositioningData positionRequest;
	private Set<GameCharacter> charactersRequestingCreampie;
	private Set<GameCharacter> charactersRequestingKnot;
	/**Maps: Characters who are requesting a pull out -> The area which they want the orgasming character to cum on (null if no area requested). */
	private Map<GameCharacter, OrgasmCumTarget> charactersRequestingPullout;
	/**Maps: Immobilisation types -> Map of characters responsible for inflicting the immobilisation mapped to the targets who are immobilised. */
	private Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> charactersImmobilised;
	private Set<GameCharacter> charactersBannedFromPositioning;
	private Set<GameCharacter> charactersForbiddenByOthersFromPositioning;
	private Set<GameCharacter> charactersSelfActionsBlocked;
	private Set<GameCharacter> charactersDeniedOrgasm;
	private Map<GameCharacter, SexControl> forcedSexControlMap;
	private Set<GameCharacter> charactersBannedFromRapePlay;

	private Set<GameCharacter> charactersGrewCock;
	private Set<GameCharacter> heavyLipstickUsedCharacter; // For tracking which characters have their 'heavy' lipstick removed at the end of sex.
	
	/** Maps: character orgasming -> Value of: characters forcing creampie, area used to force creampie */
	private Map<GameCharacter, Value<GameCharacter, Class<? extends BodyPartInterface>>> creampieLockedBy;
	
	private Set<GameCharacter> removeEndSexAffection;
	
	// Clothes:

	private Set<GameCharacter> charactersBannedFromRemovingSelfClothing;
	private Set<GameCharacter> charactersBannedFromRemovingOthersClothing;
	
	private AbstractClothing clothingBeingRemoved;
	
	private Map<GameCharacter, Map<InventorySlot, Map<AbstractClothing, List<DisplacementType>>>> clothingPreSexMap;
	private Map<GameCharacter, Map<InventorySlot, AbstractWeapon>> weaponsPreSexMap;
	
	private Map<GameCharacter, Map<GameCharacter, List<AbstractClothing>>> clothingEquippedDuringSex; // Maps character who owned clothing -> character who had it equipped to them -> list of clothing
	
	private boolean sexFinished;
	
	private AbstractClothing selectedClothing;
	

	public Sex() {
		sexManagerLoader = new SexManagerLoader();
		sexActionManager = new SexActionManager();
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
		
		// Reset companion management nodes, so that inventory handles correctly if sex is started from within slave/companion management:
		CompanionManagement.initManagement(null, 0, null);
		
		sexInitFinished = false;
		overridePlayerArousalRestriction = false;
		playerLevelDrain = true;
		turn = 1;
		
		SexFlags.reset();
		
		Main.sex.consensual = consensual;
		Main.sex.subHasEqualControl = subHasEqualControl;
		
		// Remove spectators that are defined as participants:
		List<GameCharacter> trimmedDomSpectators = new ArrayList<>(dominantSpectators);
		trimmedDomSpectators.removeAll(sexManager.getDominants().keySet());
		trimmedDomSpectators.removeAll(sexManager.getSubmissives().keySet());
		Main.sex.dominantSpectators = trimmedDomSpectators;

		List<GameCharacter> trimmedSubSpectators = new ArrayList<>(submissiveSpectators);
		trimmedSubSpectators.removeAll(sexManager.getSubmissives().keySet());
		trimmedSubSpectators.removeAll(sexManager.getDominants().keySet());
		Main.sex.submissiveSpectators = trimmedSubSpectators;
		
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
		preOrgasmTargeting = null;
		
		forceSexPaceMap = new HashMap<>();
		
		sexFinished = false;
		
		orgasmCountMap = new HashMap<>();
		deniedOrgasmsCountMap = new HashMap<>();
		sexCountMap = new HashMap<>();
		cummedInsideMap = new HashMap<>();
		creampieLockedBy = null;
		
		charactersBannedFromRapePlay = new HashSet<>();
		if(!Main.getProperties().hasValue(PropertyValue.rapePlayAtSexStart)) {
			for(GameCharacter character : sexManager.getSubmissives().keySet()) { // All characters start banned from rape-play, as otherwise it's very jarring to start consensual sex and the partner is immediately resisting with no explanation
				if(sexManager.isRapePlayBannedAtStart(character)) {
					charactersBannedFromRapePlay.add(character);
				}
			}
		}
		
		initialSexManager = sexManager;
		setSexManager(sexManager);
		characterPerformingAction = Main.game.getPlayer();
		characterOrgasming = null;
		characterLayingEggs = null;
		
		itemUseInformation = null;
		itemUseDenials = new HashMap<>();
		
		publicSex = sexManager.isPublicSex();
		
		Main.sex.postSexDialogue = postSexDialogue;
		
		sexStarted = false;

		resetAllOngoingActions(true);

		for(GameCharacter character : Main.sex.getAllParticipants()) {
			if(!Main.game.getPlayer().isWantingToLevelDrain(character)) {
				playerLevelDrain = false;
				break;
			}
		}
		
		lastUsedSexAction = new HashMap<>();
		for(GameCharacter character : Main.sex.getAllParticipants()) {
			sexCountMap.put(character, new HashMap<>());
			if(character.isPlayer()) {
				lastUsedSexAction.put(character, SexActionUtility.PLAYER_NONE);
			} else {
				lastUsedSexAction.put(character, SexActionUtility.PARTNER_NONE);
			}
		}
		
//		for(GameCharacter character : Main.sex.getAllParticipants(false)) {
//			for(GameCharacter character2 : Main.sex.getAllParticipants(false)) {
//				character.addSexPartner(character2);
//			}
//		}
		
		for(GameCharacter character : Main.sex.getAllParticipants(false)) {
			for(GameCharacter partner : Main.sex.getAllParticipants(false)) {
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
		
		charactersKnottedTogether = new HashMap<>();
		
		// Populate exposed areas:
		areasExposed = new HashMap<>();
		requestsBlocked = new HashMap<>();
		positioningRequestsBlocked = new HashMap<>();
		for(GameCharacter character : Main.sex.getAllParticipants()) {
			areasExposed.put(character, new ArrayList<>());
			requestsBlocked.put(character, new ArrayList<>());
			positioningRequestsBlocked.put(character, new ArrayList<>());
		}
		
		positionRequest = null;
		
		charactersRequestingCreampie = new HashSet<>();
		charactersRequestingKnot = new HashSet<>();
		charactersRequestingPullout = new HashMap<>();
		
		charactersImmobilised = new HashMap<>(sexManager.getStartingCharactersImmobilised());
		charactersBannedFromPositioning = new HashSet<>();
		
		charactersSelfActionsBlocked = new HashSet<>();
		charactersDeniedOrgasm = new HashSet<>();
		charactersGrewCock = new HashSet<>();
		heavyLipstickUsedCharacter = new HashSet<>();
		
		creampieLockedBy = new HashMap<>();
		
		removeEndSexAffection = new HashSet<>();
		forcedSexControlMap = new HashMap<>();
		for(GameCharacter character : Main.sex.getAllParticipants(true)) {
			if(!Main.sex.sexManager.isEndSexAffectionChangeEnabled(character)) {
				removeEndSexAffection.add(character);
			}
			forcedSexControlMap.put(character, null);
		}

		charactersForbiddenByOthersFromPositioning = new HashSet<>();
		for(GameCharacter character : Main.sex.getAllParticipants()) {
			if(!character.isPlayer() && (Main.sex.getSexControl(character)!=SexControl.FULL || !Main.sex.isDom(character))) {
				charactersForbiddenByOthersFromPositioning.add(character);
			}
		}
		
		areasStretched = new HashMap<>();
		for(GameCharacter character : Main.sex.getAllParticipants()) {
			areasStretched.put(character, new HashSet<>());
		}
		
		areasCurrentlyStretching = new HashMap<>();
		for(GameCharacter character : Main.sex.getAllParticipants()) {
			areasCurrentlyStretching.put(character, new HashSet<>());
		}
		
		areasTooLoose = new HashMap<>();
		for(GameCharacter character : Main.sex.getAllParticipants()) {
			areasTooLoose.put(character, new HashSet<>());
		}
		
		for(GameCharacter character : Main.sex.getAllParticipants()) {
			for(GameCharacter target : Main.sex.getAllParticipants()) {
				if(!target.equals(character) && Main.sex.getSexPositionSlot(target)!=SexSlotGeneric.MISC_WATCHING) {
					character.generateSexChoices(false, target, null);
				}
			}
			
			// Default starting lust and arousal:
			Main.sex.getSexManager().initStartingLustAndArousal(character);
			
			if(character.isPlayer()) {
				forceSexPaceMap.put(character, Main.sex.isDom(character)?SexPace.DOM_NORMAL:SexPace.SUB_NORMAL);
			} else {
				if(Main.sex.isDom(character) && ((NPC)character).getSexPaceDomPreference()!=null) {
					forceSexPaceMap.put(character, ((NPC)character).getSexPaceDomPreference());
				}
				if(!Main.sex.isDom(character) && ((NPC)character).getSexPaceSubPreference(Main.sex.getTargetedPartner(character))!=null) {
					forceSexPaceMap.put(character, ((NPC)character).getSexPaceSubPreference(Main.sex.getTargetedPartner(character)));
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
		
		for(GameCharacter character : Main.sex.getAllParticipants()) {
			wetAreas.putIfAbsent(character, new HashMap<>());
			
			for(GameCharacter characterLubricationOwner : Main.sex.getAllParticipants()) {
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
		
		// Add all lubrication types that need to be applied at the start of this scene:
		if(sexManager.getStartingWetAreas()!=null) {
			for(Entry<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> entry : sexManager.getStartingWetAreas().entrySet()) {
				if(wetAreas.containsKey(entry.getKey())) { // Make sure that the character is actually in this sex scene
					for(SexAreaInterface area : entry.getValue().keySet()) {
						for(GameCharacter owner : sexManager.getStartingWetAreas().get(entry.getKey()).get(area).keySet()) {
							wetAreas.get(entry.getKey()).get(area).putIfAbsent(owner, new HashSet<>());
//							if(wetAreas.get(entry.getKey()).get(area).containsKey(owner)) {
								wetAreas.get(entry.getKey()).get(area).get(owner).addAll(entry.getValue().get(area).get(owner));
//							}
						}
					}
				}
			}
		}
		
		// Starting text:
		sexSB = new StringBuilder(sexStartDescription);

		sexSB.append(sexManager.getStartSexDescription());

		sexSB.append(Main.game.getTextEndStringBuilder()); // Append the textEndStringBuilder here, as appending it after all the starting-sex effects makes it a lot less visible.
		Main.game.clearTextEndStringBuilder();
		
		if(Main.sex.isPublicSex()) {
			sexSB.append(Main.sex.getInitialSexManager().getPublicSexStartingDescription());
		}

		sexSB.append("<p style='text-align:center;'><b>Starting Position:</b> <b style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>"+sexManager.getPosition().getName()+"</b><br/>"
				+"<i><b>"+sexManager.getPosition().getDescription(Main.sex.getAllOccupiedSlots(false))+"</b></i></p>");
		
		sexSB.append(calculateWetAreas(true));
		
		charactersBannedFromRemovingSelfClothing = new HashSet<>();
		charactersBannedFromRemovingOthersClothing = new HashSet<>();
		for(GameCharacter character : Main.sex.getAllParticipants()) {
			if(Main.sex.getSexControl(character).getValue()<SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS.getValue()) {
				charactersBannedFromRemovingOthersClothing.add(character);
			}
		}

		// Left out for now due to needing checks for size differences for every penetration type
//		for(GameCharacter character : Main.sex.getAllParticipants()) {
//			List<String> targetnames = new ArrayList<>();
//			boolean playerIncluded = character.isPlayer();
//			if(Main.sex.getCharacterPerformingAction().getHeight()==Height.NEGATIVE_THREE_MIMIMUM) {
//				for(GameCharacter targetCharacter : Main.sex.getAllParticipants()) {
//					if(!character.equals(targetCharacter) && !targetCharacter.getHeight().isShortStature()) {
//						if(!playerIncluded) {
//							playerIncluded = targetCharacter.isPlayer();
//						}
//						targetnames.add(UtilText.parse(targetCharacter, "[npc.name]"));
//					}
//				}
//				if(!targetnames.isEmpty()) {
//					sexSB.append("<p style='text-align:center;'><i>");
//						sexSB.append(UtilText.parse(character, "Due to "+(playerIncluded?"your":"their")+" extreme difference in size, [style.colourTerrible([npc.name] cannot be penetrated by "+Util.stringsToStringList(targetnames, false)+")]!"));
//					sexSB.append("</i></p>");
//				}
//			}
//		}
		
		
		// Store status of all clothes for both partners (so they can be restored afterwards):
		clothingPreSexMap = new HashMap<>();
		
		for(GameCharacter character : Main.sex.getAllParticipants()) {
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
		
		weaponsPreSexMap = new HashMap<>();
		for(GameCharacter character : Main.sex.getAllParticipants()) {
			weaponsPreSexMap.put(character, new HashMap<>());
			// Main weapons:
			AbstractWeapon w = character.getMainWeaponArray()[0];
			if(w!=null) {
				weaponsPreSexMap.get(character).put(InventorySlot.WEAPON_MAIN_1, w);
			}
			w = character.getMainWeaponArray()[1];
			if(w!=null) {
				weaponsPreSexMap.get(character).put(InventorySlot.WEAPON_MAIN_2, w);
			}
			w = character.getMainWeaponArray()[2];
			if(w!=null) {
				weaponsPreSexMap.get(character).put(InventorySlot.WEAPON_MAIN_3, w);
			}
			// offhand weapons:
			w = character.getOffhandWeaponArray()[0];
			if(w!=null) {
				weaponsPreSexMap.get(character).put(InventorySlot.WEAPON_OFFHAND_1, w);
			}
			w = character.getOffhandWeaponArray()[1];
			if(w!=null) {
				weaponsPreSexMap.get(character).put(InventorySlot.WEAPON_OFFHAND_2, w);
			}
			w = character.getOffhandWeaponArray()[2];
			if(w!=null) {
				weaponsPreSexMap.get(character).put(InventorySlot.WEAPON_OFFHAND_3, w);
			}
		}

		
		clothingEquippedDuringSex = new HashMap<>();
		for(GameCharacter character : Main.sex.getAllParticipants()) {
			clothingEquippedDuringSex.put(character, new HashMap<>());
		}

		// Add known areas, so NPCs don't react to already-naked characters as though they are only just seeing them:

		for(GameCharacter character : Main.sex.getAllParticipants()) {
			for(GameCharacter character2 : Main.sex.getAllParticipants()) {
				if(!character.equals(character2)) {
					for(CoverableArea ca : CoverableArea.values()) {
						boolean concealed = false;
						for(InventorySlot slot : ca.getAssociatedInventorySlots(character)) {
							if(Main.sex.getInitialSexManager().getSlotsConcealed(character, character2).contains(slot)) {
								concealed = true;
								break;
							}
						}
						if(!concealed && character.isCoverableAreaVisible(ca) && ca!=CoverableArea.MOUTH) {
							character.setAreaKnownByCharacter(ca, character2, true);
						}
					}
				}
			}
		}
		
		// Strip clothing:
		
		List<AbstractClothing> clothingToStrip = new ArrayList<>();

		for(GameCharacter character : Main.sex.getAllParticipants()) {
			if(sexManager.isCharacterStartNaked(character)
					|| (Main.getProperties().hasValue(PropertyValue.autoSexStrip) && character.isPlayer() && Main.sex.getInitialSexManager().isAbleToRemoveSelfClothing(character))) {
				clothingToStrip.clear();
				clothingToStrip.addAll(character.getClothingCurrentlyEquipped());
				clothingToStrip.removeIf(c -> c.getSlotEquippedTo().isJewellery() || c.isMilkingEquipment());
				for(AbstractClothing c : clothingToStrip) {
					character.unequipClothingIntoInventory(c, true, character);
				}
				// If any clothing was unable to be removed, displace it in every way possible:
				clothingToStrip.clear();
				clothingToStrip.addAll(character.getClothingCurrentlyEquipped());
				clothingToStrip.removeIf(c -> c.getSlotEquippedTo().isJewellery() || c.isMilkingEquipment());
				for(AbstractClothing c : clothingToStrip) {
					for(DisplacementType dt : c.getBlockedPartsKeysAsListWithoutNONE(character, c.getSlotEquippedTo())) {
						character.isAbleToBeDisplaced(c, dt, true, true, character);
					}
				}
				if(!sexManager.isCharacterStartNaked(character)) {
					sexSB.append("<p style='text-align:center;'>"
									+ "[style.italicsSex(You quickly strip off all of your clothes!)]"
								+ "</p>");
				}
				
			} else if(Main.getProperties().hasValue(PropertyValue.autoSexStrip) && !character.isPlayer() && !Main.sex.isSpectator(character)) {
				boolean anyClothingStripped = false;
				clothingToStrip.clear();
				clothingToStrip.addAll(character.getClothingCurrentlyEquipped());
				clothingToStrip.removeIf(c -> c.getSlotEquippedTo().isJewellery() || c.isMilkingEquipment());
				for(AbstractClothing c : clothingToStrip) {
					if(Main.sex.getInitialSexManager().isAbleToRemoveOthersClothing(Main.game.getPlayer(), c)) {
						character.unequipClothingIntoInventory(c, true, character);
						anyClothingStripped = true;
					}
				}
				// If any clothing was unable to be removed, displace it in every way possible:
				clothingToStrip.clear();
				clothingToStrip.addAll(character.getClothingCurrentlyEquipped());
				clothingToStrip.removeIf(c -> c.getSlotEquippedTo().isJewellery() || c.isMilkingEquipment());
				for(AbstractClothing c : clothingToStrip) {
					if(Main.sex.getInitialSexManager().isAbleToRemoveOthersClothing(Main.game.getPlayer(), c)) {
						for(DisplacementType dt : c.getBlockedPartsKeysAsListWithoutNONE(character, c.getSlotEquippedTo())) {
							character.isAbleToBeDisplaced(c, dt, true, true, character);
							anyClothingStripped = true;
						}
					}
				}
				if(anyClothingStripped) {
					sexSB.append("<p style='text-align:center;'>"
									+ UtilText.parse(character, "[style.italicsSex(You quickly strip off all of [npc.namePos] clothes!)]")
								+ "</p>");
				}
			}
		}
		
		// Starting exposed:
		for(GameCharacter character : Main.sex.getAllParticipants(false)) {
//			if(Main.sex.getSexPositionSlot(character)!=SexSlotGeneric.MISC_WATCHING) {
				if(sexManager.isAppendStartingExposedDescriptions(character)) {
					sexSB.append(handleExposedDescriptions(character, true));
				} else {
					handleExposedDescriptions(character, true);
				}
//			}
		}
		
		StringBuilder initialSexActionSB = new StringBuilder();
		for(InitialSexActionInformation sexAction : startingSexActions) {
			if(sexAction.isConditionalMet()) {
				Main.sex.setCharacterPerformingAction(sexAction.getPerformer());
				Main.sex.setTargetedPartner(sexAction.getPerformer(), sexAction.getTarget());
				float initArousalPerformer = sexAction.getPerformer().getArousal();
				float initArousalTarget = sexAction.getTarget().getArousal();
				
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
				}

				String effectApplication = sexAction.getSexAction().applyEndEffects();
				if(sexAction.isAppendEffects()) {
					initialSexActionSB.append(effectApplication);
				}
				
				// Revert arousal additions from action effects as otherwise initial actions pretty much completely skip foreplay:
				sexAction.getPerformer().setArousal(initArousalPerformer);
				sexAction.getTarget().setArousal(initArousalTarget);
				
				if(initialSexActionSB.length()>0) {
					sexSB.append(UtilText.parse(sexAction.getPerformer(), sexAction.getTarget(), initialSexActionSB.toString(), ParserTag.SEX_DESCRIPTION));
				}
			}
		}
		
		sexDescription = sexSB.toString();
		
		Main.sex.setCharacterPerformingAction(Main.game.getPlayer());
		
		// Populate available SexAction list:
		populatePlayerSexLists();
		
		sexInitFinished = true;
		
		return SEX_DIALOGUE;
	}
	
	/**
	 * Called after initialiseSex(). Handles any effects which are dependent upon SEX_DIALOGUE being the active DialogueNode.
	 */
	public void postSexInitSetup() {
		if(Main.sex.isMasturbation()) {
			Main.game.setResponseTab(1);
		} else {
			if(!sexActionsPlayer.isEmpty()) {
				Main.game.setResponseTab(2);
			} else if(!selfActionsPlayer.isEmpty()) {
				Main.game.setResponseTab(1);
			} else {
				Main.game.setResponseTab(0);
			}
		}
		Main.game.updateResponses();
	}

	private void endSex() {
		Main.game.setInSex(false);
		
		// If this is a washing scene, make sure that participants don't end sex while being dirty:
		if(this.getInitialSexManager().isWashingScene()) {
			for(GameCharacter participant : getAllParticipants(false)) {
				participant.cleanAllDirtySlots(true);
				participant.cleanAllClothing(false, true);
			}
		}
		
		// Restore clothes:
		for(Entry<GameCharacter, Map<InventorySlot, Map<AbstractClothing, List<DisplacementType>>>> entry : clothingPreSexMap.entrySet()) {
			GameCharacter character = entry.getKey();
			boolean preWornCondomFound = false;
			for (Entry<InventorySlot, Map<AbstractClothing, List<DisplacementType>>> entry2 : entry.getValue().entrySet()) {
				for (AbstractClothing c : entry2.getValue().keySet()) {
					if(!c.isDiscardedOnUnequip(entry2.getKey()) || c.isMilkingEquipment()) { // Special case for pumps, which are normally discarded on unequip
						AbstractClothing dirtyClone = new AbstractClothing(c) {};
						dirtyClone.setDirty(null, true);
						dirtyClone.setSlotEquippedTo(null);
						AbstractClothing clothingEquipped = character.getClothingInSlot(entry2.getKey());
						if(clothingEquipped==null) {
							// Only re-equip if that slot is empty, as some endSex methods force clothing on the player:
							if(character.getCell().getInventory().hasClothing(c) || character.hasClothing(c)) {
								// Have to remove clothing manually before setting the unlocked state of the clothing, as unlock state alters the hashCode of the clothing,
									// thereby making it so the clothing is not removed from the underlying inventory after its unlock state has changed
								if(character.getCell().getInventory().hasClothing(c)) {
									character.getCell().getInventory().removeClothing(c);
								} else {
									character.removeClothing(c);
								}
								c.setUnlocked(false); // Reset seal status
								character.equipClothingOverride(c, entry2.getKey(), false, false);
								
							} else if(character.getCell().getInventory().hasClothing(dirtyClone) || character.hasClothing(dirtyClone)) {
								// Have to remove clothing manually before setting the unlocked state of the clothing, as unlock state alters the hashCode of the clothing,
									// thereby making it so the clothing is not removed from the underlying inventory after its unlock state has changed
								if(character.getCell().getInventory().hasClothing(dirtyClone)) {
									character.getCell().getInventory().removeClothing(dirtyClone);
								} else {
									character.removeClothing(dirtyClone);
								}
								dirtyClone.setUnlocked(false); // Reset seal status
								character.equipClothingOverride(dirtyClone, entry2.getKey(), false, false);
								
							} else if(c.isMilkingEquipment()) {
								c.setUnlocked(false); // Reset seal status
								character.equipClothingOverride(c, entry2.getKey(), false, false);
							}
						}
						
						clothingEquipped = character.getClothingInSlot(entry2.getKey()); // check if now equipped
						if(Main.getProperties().hasValue(PropertyValue.autoSexClothingManagement)) {
							for(AbstractClothing clothing : new ArrayList<>(character.getClothingCurrentlyEquipped())) {
								if(clothing.getClothingType().equals(c.getClothingType()) && clothing.getSlotEquippedTo().equals(c.getSlotEquippedTo())) {
									clothing.getDisplacedList().clear();
									if(entry2.getValue().get(c)!=null) {
										for(DisplacementType displacement : entry2.getValue().get(c)) {
											character.isAbleToBeDisplaced(clothing, displacement, true, true, character);
										}
									}
								}
							}
							
						} else if(character.getCell().getInventory().hasClothing(c) && clothingEquipped==null) { // Try to pick up their clothing if it's still on the floor:
							character.addClothing(c, true);
							
						} else if(character.getCell().getInventory().hasClothing(dirtyClone) && clothingEquipped==null) { // Try to pick up their clothing if it's still on the floor:
							character.addClothing(dirtyClone, true);
						}
						
					} else {
						preWornCondomFound = c.isCondom();
					}
				}
			}
			if(!preWornCondomFound && character.getClothingInSlot(InventorySlot.PENIS)!=null && character.getClothingInSlot(InventorySlot.PENIS).isCondom()) { // Remove condom if this character was not wearing one when starting sex
				character.forceUnequipClothingIntoVoid(character, character.getClothingInSlot(InventorySlot.PENIS));
			}
		}

		// Restore weapons:
		for(Entry<GameCharacter, Map<InventorySlot, AbstractWeapon>> entry : weaponsPreSexMap.entrySet()) {
			GameCharacter character = entry.getKey();
			AbstractWeapon w = entry.getValue().get(InventorySlot.WEAPON_MAIN_1);
			if(w!=null && character.getMainWeaponArray()[0]!=w) {
				if(character.getCell().getInventory().hasWeapon(w)) {
					character.equipMainWeaponFromFloor(w);
				} else if(character.hasWeapon(w)) {
					character.equipMainWeaponFromInventory(w, character);
				}
			}
			w = entry.getValue().get(InventorySlot.WEAPON_MAIN_2);
			if(w!=null && character.getMainWeaponArray()[1]!=w) {
				if(character.getCell().getInventory().hasWeapon(w)) {
					character.equipMainWeaponFromFloor(w);
				} else if(character.hasWeapon(w)) {
					character.equipMainWeaponFromInventory(w, character);
				}
			}
			w = entry.getValue().get(InventorySlot.WEAPON_MAIN_3);
			if(w!=null && character.getMainWeaponArray()[2]!=w) {
				if(character.getCell().getInventory().hasWeapon(w)) {
					character.equipMainWeaponFromFloor(w);
				} else if(character.hasWeapon(w)) {
					character.equipMainWeaponFromInventory(w, character);
				}
			}
			
			w = entry.getValue().get(InventorySlot.WEAPON_OFFHAND_1);
			if(w!=null && character.getOffhandWeaponArray()[0]!=w) {
				if(character.getCell().getInventory().hasWeapon(w)) {
					character.equipOffhandWeaponFromFloor(w);
				} else if(character.hasWeapon(w)) {
					character.equipOffhandWeaponFromInventory(w, character);
				}
			}
			w = entry.getValue().get(InventorySlot.WEAPON_OFFHAND_2);
			if(w!=null && character.getOffhandWeaponArray()[1]!=w) {
				if(character.getCell().getInventory().hasWeapon(w)) {
					character.equipOffhandWeaponFromFloor(w);
				} else if(character.hasWeapon(w)) {
					character.equipOffhandWeaponFromInventory(w, character);
				}
			}
			w = entry.getValue().get(InventorySlot.WEAPON_OFFHAND_3);
			if(w!=null && character.getOffhandWeaponArray()[2]!=w) {
				if(character.getCell().getInventory().hasWeapon(w)) {
					character.equipOffhandWeaponFromFloor(w);
				} else if(character.hasWeapon(w)) {
					character.equipOffhandWeaponFromInventory(w, character);
				}
			}
		}
		
		
		for(GameCharacter participant : Main.sex.getAllParticipants()) {
			if(!Main.sex.isSpectator(participant) && !Main.sex.isMasturbation()) {
				boolean satisfiedPartners = false;
				if(Main.sex.isDom(participant)) {
					for(GameCharacter sub : Main.sex.getSubmissiveParticipants(false).keySet()) {
						if(Main.sex.getNumberOfOrgasms(sub)>=sub.getOrgasmsBeforeSatisfied()) {
							satisfiedPartners = true;
							break;
						}
					}
				} else {
					for(GameCharacter dom : Main.sex.getDominantParticipants(false).keySet()) {
						if(Main.sex.getNumberOfOrgasms(dom)>=dom.getOrgasmsBeforeSatisfied()) {
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
			participant.setLastTimeHadSex(Main.game.getMinutesPassed(), false); // Do not need to set orgasm time, as it is set in setNumberOfOrgasms() and incrementNumberOfOrgasms()
			if(participant instanceof NPC) {
				((NPC)participant).endSex();
			}
			
			if(getNumberOfOrgasms(participant) > 0) {
				participant.setLustNoText(0);
			}
		}
		
		for(GameCharacter character : Main.sex.getCharactersGrewCock()) {
			character.setPenisType(PenisType.NONE);
		}
	}

	private static String getStretchInstantRecoveryDescription(boolean plural, String name) {
		return "<p style='text-align:center;'><i>"
					+ "[npc.NamePos] [style.italicsPlasticity(stretched)] "+name+" quickly recover"+(plural?"":"s")+" from "+(plural?"their":"its")+" ordeal, [style.italicsExcellent(instantly regaining all of "+(plural?"their":"its")+" tightness)]!"
				+ "</i></p>";
	}
	
	private static String getStretchNoRecoveryDescription(boolean plural, String name, Capacity capacity) {
		return "<p style='text-align:center;'><i>"
					+ "[npc.NamePos] "+name+" "+(plural?"have":"has")+" been"
						+ " [style.italicsTerrible(permanently)] [style.italicsPlasticity(stretched)] into being"
						+ " <span style='color:"+capacity.getColour().toWebHexString()+";'>"+ capacity.getDescriptor() +"</span>!"
				+ "</i></p>";
	}
	
	private static String getStretchPartialRecoveryDescription(boolean plural, String name, OrificeElasticity elasticity, Capacity capacityRaw, Capacity capacityStretched) {
		return "<p style='text-align:center;'><i>"
					+ "[npc.NamePos] "+ elasticity.getDescriptor()+" [style.italicsAsshole("+name+")] "+(plural?"have":"has")+" been [style.italicsPlasticity(stretched)] from "+(plural?"their":"its")+" ordeal, and "+(plural?"are":"is")+" currently"
						+ " <span style='color:"+capacityStretched.getColour().toWebHexString()+";'>"+ capacityStretched.getDescriptor() + "</span>!"
					+ "<br/>"+(plural?"They":"It")+" will recover [style.italicsMinorBad(only some)] of "+(plural?"their":"its")+" original size, eventually tightening back to being"
						+ " <span style='color:"+capacityRaw.getColour().toWebHexString()+";'>" + capacityRaw.getDescriptor() + "</span>!"
				+ "</i></p>";
	}
	
	private static String getStretchFullRecoveryDescription(boolean plural, String name, OrificeElasticity elasticity, Capacity capacityRaw, Capacity capacityStretched) {
		return "<p style='text-align:center;'><i>"
				+ "[npc.NamePos] "+ elasticity.getDescriptor()+" [style.italicsAsshole("+name+")] "+(plural?"have":"has")+" been [style.italicsPlasticity(stretched)] from "+(plural?"their":"its")+" ordeal, and "+(plural?"are":"is")+" currently"
					+ " <span style='color:"+capacityStretched.getColour().toWebHexString()+";'>"+ capacityStretched.getDescriptor() + "</span>!"
				+ "<br/>"+(plural?"They":"It")+" will recover [style.italicsMinorGood(all)] of "+(plural?"their":"its")+" original size, eventually tightening back to being"
					+ " <span style='color:"+capacityRaw.getColour().toWebHexString()+";'>" + capacityRaw.getDescriptor() + "</span>!"
			+ "</i></p>";
	}
	
	private String getEndSexStretchingDescription(GameCharacter participant) {
		StringBuilder sb = new StringBuilder();

		// Asshole:
		if(participant.getAssRawCapacityValue() != participant.getAssStretchedCapacity() && areasStretched.get(participant).contains(SexAreaOrifice.ANUS)) {
			if(participant.getAssPlasticity() == OrificePlasticity.ZERO_RUBBERY){
				participant.setAssStretchedCapacity(participant.getAssRawCapacityValue());
				sb.append(getStretchInstantRecoveryDescription(false, "[style.italicsAsshole(asshole)]"));
				
			} else if(participant.getAssPlasticity().getCapacityIncreaseModifier()>=1){
				participant.setAssCapacity(participant.getAssStretchedCapacity(), true);
				sb.append(getStretchNoRecoveryDescription(false, "[style.italicsAsshole(asshole)]", Capacity.getCapacityFromValue(participant.getAssRawCapacityValue())));
				
			} else {
				// Increment core capacity by the Plasticity's capacityIncreaseModifier:
				participant.incrementAssCapacity((participant.getAssStretchedCapacity()-participant.getAssRawCapacityValue())*participant.getAssPlasticity().getCapacityIncreaseModifier(), false);

				if(participant.getAssPlasticity().getCapacityIncreaseModifier()>0) {
					sb.append(getStretchPartialRecoveryDescription(
							false, "[style.italicsAsshole(asshole)]", participant.getAssElasticity(), Capacity.getCapacityFromValue(participant.getAssRawCapacityValue()), Capacity.getCapacityFromValue(participant.getAssStretchedCapacity())));
					
				} else {
					sb.append(getStretchFullRecoveryDescription(
							false, "[style.italicsAsshole(asshole)]", participant.getAssElasticity(), Capacity.getCapacityFromValue(participant.getAssRawCapacityValue()), Capacity.getCapacityFromValue(participant.getAssStretchedCapacity())));
				}
			}
		}
		// Vagina:
		if(participant.getVaginaRawCapacityValue() != participant.getVaginaStretchedCapacity() && areasStretched.get(participant).contains(SexAreaOrifice.VAGINA)) {
			if(participant.getVaginaPlasticity() == OrificePlasticity.ZERO_RUBBERY){
				participant.setVaginaStretchedCapacity(participant.getVaginaRawCapacityValue());
				sb.append(getStretchInstantRecoveryDescription(false, "[style.italicsVagina(pussy)]"));
				
			} else if(participant.getVaginaPlasticity().getCapacityIncreaseModifier()>=1){
				participant.setVaginaCapacity(participant.getVaginaStretchedCapacity(), true);
				sb.append(getStretchNoRecoveryDescription(false, "[style.italicsVagina(pussy)]", Capacity.getCapacityFromValue(participant.getAssRawCapacityValue())));
				
			} else {
				// Increment core capacity by the Plasticity's capacityIncreaseModifier:
				participant.incrementVaginaCapacity((participant.getVaginaStretchedCapacity()-participant.getVaginaRawCapacityValue())*participant.getVaginaPlasticity().getCapacityIncreaseModifier(), false);

				if(participant.getVaginaPlasticity().getCapacityIncreaseModifier()>0) {
					sb.append(getStretchPartialRecoveryDescription(
							false, "[style.italicsVagina(pussy)]", participant.getVaginaElasticity(), Capacity.getCapacityFromValue(participant.getVaginaRawCapacityValue()), Capacity.getCapacityFromValue(participant.getVaginaStretchedCapacity())));
					
				} else {
					sb.append(getStretchFullRecoveryDescription(
							false, "[style.italicsVagina(pussy)]", participant.getVaginaElasticity(), Capacity.getCapacityFromValue(participant.getVaginaRawCapacityValue()), Capacity.getCapacityFromValue(participant.getVaginaStretchedCapacity())));
				}
			}
		}
		// Throat:
		if(participant.getFaceRawCapacityValue() != participant.getFaceStretchedCapacity() && areasStretched.get(participant).contains(SexAreaOrifice.MOUTH)) {
			if(participant.getFacePlasticity() == OrificePlasticity.ZERO_RUBBERY){
				participant.setFaceStretchedCapacity(participant.getFaceRawCapacityValue());
				sb.append(getStretchInstantRecoveryDescription(false, "[style.italicsMouth(throat)]"));
				
			} else if(participant.getFacePlasticity().getCapacityIncreaseModifier()>=1){
				participant.setFaceCapacity(participant.getFaceStretchedCapacity(), true);
				sb.append(getStretchNoRecoveryDescription(false, "[style.italicsMouth(throat)]", Capacity.getCapacityFromValue(participant.getAssRawCapacityValue())));
				
			} else {
				// Increment core capacity by the Plasticity's capacityIncreaseModifier:
				participant.incrementFaceCapacity((participant.getFaceStretchedCapacity()-participant.getFaceRawCapacityValue())*participant.getFacePlasticity().getCapacityIncreaseModifier(), false);

				if(participant.getFacePlasticity().getCapacityIncreaseModifier()>0) {
					sb.append(getStretchPartialRecoveryDescription(
							false, "[style.italicsMouth(throat)]", participant.getFaceElasticity(), Capacity.getCapacityFromValue(participant.getFaceRawCapacityValue()), Capacity.getCapacityFromValue(participant.getFaceStretchedCapacity())));
					
				} else {
					sb.append(getStretchFullRecoveryDescription(
							false, "[style.italicsMouth(throat)]", participant.getFaceElasticity(), Capacity.getCapacityFromValue(participant.getFaceRawCapacityValue()), Capacity.getCapacityFromValue(participant.getFaceStretchedCapacity())));
				}
			}
		}
		// Nipples:
		if(participant.getNippleRawCapacityValue() != participant.getNippleStretchedCapacity() && areasStretched.get(participant).contains(SexAreaOrifice.NIPPLE)) {
			if(participant.getNipplePlasticity() == OrificePlasticity.ZERO_RUBBERY){
				participant.setNippleStretchedCapacity(participant.getNippleRawCapacityValue());
				sb.append(getStretchInstantRecoveryDescription(true, "[style.italicsNipples(nipples)]"));
				
			} else if(participant.getNipplePlasticity().getCapacityIncreaseModifier()>=1){
				participant.setNippleCapacity(participant.getNippleStretchedCapacity(), true);
				sb.append(getStretchNoRecoveryDescription(true, "[style.italicsNipples(nipples)]", Capacity.getCapacityFromValue(participant.getNippleRawCapacityValue())));
				
			} else {
				// Increment core capacity by the Plasticity's capacityIncreaseModifier:
				participant.incrementNippleCapacity((participant.getNippleStretchedCapacity()-participant.getNippleRawCapacityValue())*participant.getNipplePlasticity().getCapacityIncreaseModifier(), false);

				if(participant.getNipplePlasticity().getCapacityIncreaseModifier()>0) {
					sb.append(getStretchPartialRecoveryDescription(
							true, "[style.italicsNipples(nipples)]", participant.getNippleElasticity(), Capacity.getCapacityFromValue(participant.getNippleRawCapacityValue()), Capacity.getCapacityFromValue(participant.getNippleStretchedCapacity())));
					
				} else {
					sb.append(getStretchFullRecoveryDescription(
							true, "[style.italicsNipples(nipples)]", participant.getNippleElasticity(), Capacity.getCapacityFromValue(participant.getNippleRawCapacityValue()), Capacity.getCapacityFromValue(participant.getNippleStretchedCapacity())));
				}
			}
		}
		// Crotch nipples:
		if(participant.getNippleCrotchRawCapacityValue() != participant.getNippleCrotchStretchedCapacity() && areasStretched.get(participant).contains(SexAreaOrifice.NIPPLE_CROTCH)) {
			if(participant.getNippleCrotchPlasticity() == OrificePlasticity.ZERO_RUBBERY){
				participant.setNippleCrotchStretchedCapacity(participant.getNippleCrotchRawCapacityValue());
				sb.append(getStretchInstantRecoveryDescription(true, "[style.italicsNippleCrotch(crotch-nipples)]"));
				
			} else if(participant.getNippleCrotchPlasticity().getCapacityIncreaseModifier()>=1){
				participant.setNippleCrotchCapacity(participant.getNippleCrotchStretchedCapacity(), true);
				sb.append(getStretchNoRecoveryDescription(true, "[style.italicsNippleCrotch(crotch-nipples)]", Capacity.getCapacityFromValue(participant.getNippleCrotchRawCapacityValue())));
				
			} else {
				// Increment core capacity by the Plasticity's capacityIncreaseModifier:
				participant.incrementNippleCrotchCapacity((participant.getNippleCrotchStretchedCapacity()-participant.getNippleCrotchRawCapacityValue())*participant.getNippleCrotchPlasticity().getCapacityIncreaseModifier(), false);

				if(participant.getNippleCrotchPlasticity().getCapacityIncreaseModifier()>0) {
					sb.append(getStretchPartialRecoveryDescription(
							true, "[style.italicsNippleCrotch(crotch-nipples)]", participant.getNippleCrotchElasticity(),
							Capacity.getCapacityFromValue(participant.getNippleCrotchRawCapacityValue()), Capacity.getCapacityFromValue(participant.getNippleCrotchStretchedCapacity())));
					
				} else {
					sb.append(getStretchFullRecoveryDescription(
							true, "[style.italicsNippleCrotch(crotch-nipples)]", participant.getNippleCrotchElasticity(),
							Capacity.getCapacityFromValue(participant.getNippleCrotchRawCapacityValue()), Capacity.getCapacityFromValue(participant.getNippleCrotchStretchedCapacity())));
				}
			}
		}
		// Penis urethra:
		if(participant.getPenisRawCapacityValue() != participant.getPenisStretchedCapacity() && areasStretched.get(participant).contains(SexAreaOrifice.URETHRA_PENIS)) {
			if(participant.getUrethraPlasticity() == OrificePlasticity.ZERO_RUBBERY){
				participant.setPenisStretchedCapacity(participant.getPenisRawCapacityValue());
				sb.append(getStretchInstantRecoveryDescription(false, "[style.italicsPenisUrethra(penile urethra)]"));
				
			} else if(participant.getUrethraPlasticity().getCapacityIncreaseModifier()>=1){
				participant.setPenisCapacity(participant.getPenisStretchedCapacity(), true);
				sb.append(getStretchNoRecoveryDescription(false, "[style.italicsPenisUrethra(penile urethra)]", Capacity.getCapacityFromValue(participant.getPenisRawCapacityValue())));
				
			} else {
				// Increment core capacity by the Plasticity's capacityIncreaseModifier:
				participant.incrementPenisCapacity((participant.getPenisStretchedCapacity()-participant.getPenisRawCapacityValue())*participant.getUrethraPlasticity().getCapacityIncreaseModifier(), false);

				if(participant.getUrethraPlasticity().getCapacityIncreaseModifier()>0) {
					sb.append(getStretchPartialRecoveryDescription(
							false, "[style.italicsPenisUrethra(penile urethra)]", participant.getUrethraElasticity(),
							Capacity.getCapacityFromValue(participant.getPenisRawCapacityValue()), Capacity.getCapacityFromValue(participant.getPenisStretchedCapacity())));
					
				} else {
					sb.append(getStretchFullRecoveryDescription(
							false, "[style.italicsPenisUrethra(penile urethra)]", participant.getUrethraElasticity(),
							Capacity.getCapacityFromValue(participant.getPenisRawCapacityValue()), Capacity.getCapacityFromValue(participant.getPenisStretchedCapacity())));
				}
			}
		}
		// Vaginal urethra:
		if(participant.getVaginaUrethraRawCapacityValue() != participant.getVaginaUrethraStretchedCapacity() && areasStretched.get(participant).contains(SexAreaOrifice.URETHRA_VAGINA)) {
			if(participant.getVaginaUrethraPlasticity() == OrificePlasticity.ZERO_RUBBERY){
				participant.setVaginaUrethraStretchedCapacity(participant.getVaginaUrethraRawCapacityValue());
				sb.append(getStretchInstantRecoveryDescription(false, "[style.italicsVaginaUrethra(vaginal urethra)]"));
				
			} else if(participant.getVaginaUrethraPlasticity().getCapacityIncreaseModifier()>=1){
				participant.setVaginaUrethraCapacity(participant.getVaginaUrethraStretchedCapacity(), true);
				sb.append(getStretchNoRecoveryDescription(false, "[style.italicsPenisUrethra(vaginal urethra)]", Capacity.getCapacityFromValue(participant.getVaginaUrethraRawCapacityValue())));
				
			} else {
				// Increment core capacity by the Plasticity's capacityIncreaseModifier:
				participant.incrementVaginaUrethraCapacity((participant.getVaginaUrethraStretchedCapacity()-participant.getVaginaUrethraRawCapacityValue())*participant.getVaginaUrethraPlasticity().getCapacityIncreaseModifier(), false);

				if(participant.getVaginaUrethraPlasticity().getCapacityIncreaseModifier()>0) {
					sb.append(getStretchPartialRecoveryDescription(
							false, "[style.italicsPenisUrethra(vaginal urethra)]", participant.getVaginaUrethraElasticity(),
							Capacity.getCapacityFromValue(participant.getVaginaUrethraRawCapacityValue()), Capacity.getCapacityFromValue(participant.getVaginaUrethraStretchedCapacity())));
					
				} else {
					sb.append(getStretchFullRecoveryDescription(
							false, "[style.italicsPenisUrethra(vaginal urethra)]", participant.getVaginaUrethraElasticity(),
							Capacity.getCapacityFromValue(participant.getVaginaUrethraRawCapacityValue()), Capacity.getCapacityFromValue(participant.getVaginaUrethraStretchedCapacity())));
				}
			}
		}
		
		return UtilText.parse(participant, sb.toString());
	}
	
	public int getEssenceGeneration(GameCharacter character) {
		int orgN = Main.sex.getNumberOfOrgasms(character);
		return Math.min(8, orgN*2-Math.max(0, orgN-3)) * (Main.game.getPlayer().hasTrait(Perk.NYMPHOMANIAC, true)?2:1);
	}
	
	public void applyEndSexEffects() {
		StringBuilder endSexSB = new StringBuilder();
		
		GameCharacter characterWhoEndedSex = Main.sex.getAllParticipants().get(0);
		for(GameCharacter character : Main.sex.getAllParticipants()) {
			if(Main.sex.getLastUsedSexAction(character).endsSex()) {
				characterWhoEndedSex = character;
//				System.out.println(UtilText.parse(characterWhoEndedSex, "[npc.Name] ended sex!"));
				break;
			}
		}
		
		boolean auraEventTriggered = false;
		for(GameCharacter participant : Main.sex.getAllParticipants()) {
			if(participant.isPlayer()) {
//				endSexSB.append("<br/><p style='text-align:center;'>"
//						+ "<span style='color:"+participant.getFemininity().getColour().toWebHexString()+";'>[npc.Name]:</span>"
//								+ "</p>");
				// Stretching effects:
				endSexSB.append(getEndSexStretchingDescription(participant));
				
				if(getHeavyLipstickUsedCharacter().contains(participant)) {
					if(participant.hasItemType(ItemType.MAKEUP_SET)) {
						endSexSB.append("<p style='text-align:center'><i>Your [style.italicsPinkDeep(heavy layer)] of lipstick has worn off, but you have "
								+ ItemType.MAKEUP_SET.getName(true, false)
								+ ", so you take a few moments to [style.italicsGood(re-apply)] your [style.italicsPinkDeep(heavy layer)] of lipstick.</i></p>");
					} else {
						participant.removeHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
						endSexSB.append("<p style='text-align:center'><i>Your [style.italicsPinkDeep(heavy layer)] of lipstick has [style.italicsBad(worn off)]!</i></p>");
					}
				}
				
				if((participant.getArousal() > ArousalLevel.THREE_HEATED.getMaximumValue() || Main.sex.getNumberOfDeniedOrgasms(participant)>0) && getNumberOfOrgasms(participant) == 0) {
					participant.addStatusEffect(StatusEffect.FRUSTRATED_NO_ORGASM, (240*60)+(postSexDialogue.getSecondsPassed()));
					if(Main.sex.getNumberOfDeniedOrgasms(participant)>0) {
						endSexSB.append("<p style='text-align:center'>[style.boldArcane(After being denied your orgasm, you're left feeling frustrated and horny!)]</p>");
					} else {
						endSexSB.append("<p style='text-align:center'>[style.boldArcane(After stopping so close to the edge, you're left feeling frustrated and horny!)]</p>");
					}
				}
				if(getNumberOfOrgasms(participant) > 0
						&& Main.game.isInNewWorld()) {
					if(participant.hasStatusEffect(StatusEffect.RECOVERING_AURA)) {
						int orgasmCount = Main.sex.getNumberOfOrgasms(participant);
						endSexSB.append("<div class='container-full-width' style='text-align:center;'>"
								+ "<i>Your arcane aura is still strengthened from a previous sexual encounter,"
									+ " so [style.italicsBad(you don't receive any essences)] from your [style.colourSex("+(orgasmCount==1?"single orgasm":Util.intToString(orgasmCount)+" orgasms")+")]!</i><br/>"
							+ "</div>");
						
					} else {
						if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.essenceOrgasmDiscovered)) {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.essenceOrgasmDiscovered);
							if(!((PlayerCharacter) participant).isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
								if(Main.sex.isMasturbation()) {
									endSexSB.append(
										"<p><i>"
											+ "As you stop masturbating, you suddenly become aware of a strange, shimmering pink glow that's started to materialise around your body,"
												+ " just like the one you saw in Lilaya's lab when she ran her tests on you the first time you met her."
											+ " Quickly realising that you're somehow able to see your own arcane aura, you watch, fascinated, as it rapidly increases in luminosity."
											+ " Just as you think that it can't get any brighter, your aura suddenly leaps back into your body, and you find yourself sharply inhaling as you feel it gaining strength."
										+ "</i></p>"
										+ "<p><i>"
											+ "You think that it would probably be best to go and ask Lilaya about what just happened..."
										+ "</i></p>"
										+(!((PlayerCharacter) participant).hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)
												?((PlayerCharacter) participant).startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)
												:""));
								} else if(Main.sex.isSpectator(Main.game.getPlayer())) {
									endSexSB.append(
											"<p><i>"
												+ "As you stop watching the sex scene, you suddenly become aware of a strange, shimmering pink glow that's started to materialise around your body,"
													+ " just like the one you saw in Lilaya's lab when she ran her tests on you the first time you met her."
												+ " Quickly realising that you're somehow able to see your own arcane aura, you watch, fascinated, as it rapidly increases in luminosity."
												+ " Just as you think that it can't get any brighter, your aura suddenly leaps back into your body, and you find yourself sharply inhaling as you feel it gaining strength."
											+ "</i></p>"
											+ "<p><i>"
												+ "You think that it would probably be best to go and ask Lilaya about what just happened..."
											+ "</i></p>"
											+(!((PlayerCharacter) participant).hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)
													?((PlayerCharacter) participant).startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)
													:""));
								} else {
									endSexSB.append(
											UtilText.parse(Main.sex.getTargetedPartner(Main.game.getPlayer()),
											"<p><i>"
												+ "As you disentangle yourself from [npc.name], you suddenly become aware of a strange, shimmering pink glow that's started to materialise around your body,"
													+ " just like the one you saw in Lilaya's lab when she ran her tests on you the first time you met her."
												+ " Quickly realising that you're somehow able to see your own arcane aura, you watch, fascinated, as it rapidly increases in luminosity."
												+ " Just as you think that it can't get any brighter, your aura suddenly leaps back into your body, and you find yourself sharply inhaling as you feel it gaining strength."
											+ "</i></p>"
											+ "<p><i>"
												+ "Looking back over at [npc.name], you see that [npc.she] seems completely oblivious to what you've just witnessed."
												+ " You think that it would probably be best to go and ask Lilaya about what just happened..."
											+ "</i></p>"
											+(!((PlayerCharacter) participant).hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)
													?((PlayerCharacter) participant).startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)
													:"")));
								}
								
							} else {
								if(Main.sex.isMasturbation()) {
									endSexSB.append("<p><i>"
											+ "As you stop masturbating, you suddenly become aware of a strange, shimmering pink glow that's started to materialise around your body,"
												+ " just like the one you saw in Lilaya's lab when she ran her tests on you the first time you met her."
											+ " Quickly realising that you're somehow able to see your own arcane aura, you watch, fascinated, as it rapidly increases in luminosity."
											+ " Just as you think that it can't get any brighter, your aura suddenly leaps back into your body, and you find yourself sharply inhaling as you feel it gaining strength."
										+ "</i></p>"
										+ "<p><i>"
											+ "You suddenly remember what Lilaya told you about absorbing essences, and how it's absolutely harmless for both parties involved, and breathe a sigh of relief..."
										+ "</i></p>");
								} else if(Main.sex.isSpectator(Main.game.getPlayer())) {
									endSexSB.append(
											"<p><i>"
												+ "As you stop watching the sex scene, you suddenly become aware of a strange, shimmering pink glow that's started to materialise around your body,"
													+ " just like the one you saw in Lilaya's lab when she ran her tests on you the first time you met her."
												+ " Quickly realising that you're somehow able to see your own arcane aura, you watch, fascinated, as it rapidly increases in luminosity."
												+ " Just as you think that it can't get any brighter, your aura suddenly leaps back into your body, and you find yourself sharply inhaling as you feel it gaining strength."
											+ "</i></p>"
											+ "<p><i>"
												+ "You suddenly remember what Lilaya told you about absorbing essences, and how it's absolutely harmless for both parties involved, and breathe a sigh of relief..."
											+ "</i></p>");
								} else {
									endSexSB.append(
											UtilText.parse(Main.sex.getTargetedPartner(Main.game.getPlayer()),
											"<p><i>"
												+ "As you disentangle yourself from [npc.name], you suddenly become aware of a strange, shimmering pink glow that's started to materialise around your body,"
													+ " just like the one you saw in Lilaya's lab when she ran her tests on you the first time you met her."
												+ " Quickly realising that you're somehow able to see your own arcane aura, you watch, fascinated, as it rapidly increases in luminosity."
												+ " Just as you think that it can't get any brighter, your aura suddenly leaps back into your body, and you find yourself sharply inhaling as you feel it gaining strength."
											+ "</i></p>"
											+ "<p><i>"
												+ "Looking back over at [npc.name], you see that [npc.she] seems completely oblivious to what you've just witnessed."
												+ " You suddenly remember what Lilaya told you about absorbing essences, and how it's absolutely harmless for both parties involved, and breathe a sigh of relief..."
											+ "</i></p>"));
								}
							}
						}
						int orgasmCount = Main.sex.getNumberOfOrgasms(participant);
						int essences = getEssenceGeneration(participant);
						endSexSB.append("<div class='container-full-width' style='text-align:center;'>"
											+ "<i>Your aura absorbs the "+(essences==1?"arcane essence":"arcane essences")+" generated by your [style.colourSex("+(orgasmCount==1?"single orgasm":Util.intToString(orgasmCount)+" orgasms")+")]!</i><br/>"
											+ Main.game.getPlayer().incrementEssenceCount(essences, true)
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

				if(getHeavyLipstickUsedCharacter().contains(participant)) {
					if(participant.hasItemType(ItemType.MAKEUP_SET)) {
						endSexSB.append("<p style='text-align:center'><i>[npc.NamePos] [style.italicsPinkDeep(heavy layer)] of lipstick has worn off, but [npc.she] [npc.has] "
								+ ItemType.MAKEUP_SET.getName(true, false)
								+ ", so [npc.she] [npc.verb(take)] a few moments to [style.italicsGood(reapply)] [npc.her] [style.italicsPinkDeep(heavy layer)] of lipstick.</i></p>");
					} else {
						participant.removeHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
						endSexSB.append("<p style='text-align:center'><i>[npc.NamePos] [style.italicsPinkDeep(heavy layer)] of lipstick has [style.italicsBad(worn off)]!</i></p>");
					}
				}
				
				// Extra effects:
				if((participant.getArousal() > ArousalLevel.THREE_HEATED.getMaximumValue() || Main.sex.getNumberOfDeniedOrgasms(participant)>0) && getNumberOfOrgasms(participant) == 0) {
					participant.addStatusEffect(StatusEffect.FRUSTRATED_NO_ORGASM, (240*60)+(postSexDialogue.getSecondsPassed()));
					if(Main.sex.getNumberOfDeniedOrgasms(participant)>0) {
						endSexSB.append("<p style='text-align:center'>[style.boldArcane(After being denied [npc.her] orgasm, [npc.name] is left feeling frustrated and horny!)]</p>");
					} else {
						endSexSB.append("<p style='text-align:center'>[style.boldArcane(After stopping so close to the edge, [npc.name] is left feeling frustrated and horny!)]</p>");
					}
				}
				if(getNumberOfOrgasms(participant) > 0
						&& Main.game.isInNewWorld()) {
					if(participant.hasStatusEffect(StatusEffect.RECOVERING_AURA)) {

						int orgasmCount = Main.sex.getNumberOfOrgasms(participant);
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
											"<p><i>"
												+ (Main.sex.isSpectator(Main.game.getPlayer())
														?"Just as you begin to look away from [npc.name],"
														:"As you disentangle yourself from [npc.name],")
													+ " you suddenly become aware of a strange, shimmering pink glow that's started to materialise around [npc.her] body,"
													+ " just like the one you saw in Lilaya's lab when she ran her tests on you the first time you met her."
												+ " Quickly realising that you're somehow able to see [npc.namePos] arcane aura, you watch, fascinated, as it rapidly increases in luminosity."
												+ " Just as you think that it can't get any brighter, [npc.her] aura suddenly leaps back into [npc.her] body, but as it does so, a single shard breaks off and flies towards you."
												+ " Unable to dodge in time, you find yourself sharply inhaling as the small piece of [npc.namePos] aura shoots into your chest."
											+ "</i></p>"
											+ "<p><i>"
												+ "Alarmed at what's just happened, you look back over at [npc.name], only to see that [npc.she] seems completely oblivious to what you've just witnessed."
												+ " You think that it would probably be best to go and ask Lilaya about what just happened..."
											+ "</i></p>"
											+(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)?Main.game.getPlayer().startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY):"")));
									
								} else {
									endSexSB.append(
											UtilText.parse(participant,
											"<p><i>"
												+ (Main.sex.isSpectator(Main.game.getPlayer())
														?"Just as you begin to look away from [npc.name],"
														:"As you disentangle yourself from [npc.name],")
												+" you suddenly become aware of a strange, shimmering pink glow that's started to materialise around [npc.her] body,"
													+ " just like the one you saw in Lilaya's lab when she ran her tests on you the first time you met her."
												+ " Quickly realising that you're somehow able to see [npc.namePos] arcane aura, you watch, fascinated, as it rapidly increases in luminosity."
												+ " Just as you think that it can't get any brighter, [npc.her] aura suddenly leaps back into [npc.her] body, but as it does so, a single shard breaks off and flies towards you."
												+ " Unable to dodge in time, you find yourself sharply inhaling as the small piece of [npc.namePos] aura shoots into your chest."
											+ "</i></p>"
											+ "<p><i>"
												+ "Alarmed at what's just happened, you look back over at [npc.name], only to see that [npc.she] seems completely oblivious to what you've just witnessed."
												+ " You suddenly remember what Lilaya told you about absorbing essences, and how it's absolutely harmless for both parties involved, and breathe a sigh of relief..."
											+ "</i></p>"));
								}
								auraEventTriggered = true;
							}
						}

						int orgasmCount = Main.sex.getNumberOfOrgasms(participant);
						int essences = getEssenceGeneration(participant);
						endSexSB.append("<div class='container-full-width' style='text-align:center;'>"
											+ "<i>Your aura absorbs the "+(essences==1?"arcane essence":"arcane essences")
												+" generated by [npc.namePos] [style.colourSex("+(orgasmCount==1?"single orgasm":Util.intToString(orgasmCount)+" orgasms")+")]!</i><br/>"
											+ Main.game.getPlayer().incrementEssenceCount(essences, true)
										+ "</div>");
					}
					participant.addStatusEffect(StatusEffect.RECOVERING_AURA, (240*60)+(postSexDialogue.getSecondsPassed()));

				}
				
				if(Main.sex.getSexPositionSlot(participant)!=SexSlotGeneric.MISC_WATCHING && !Main.sex.isRemoveEndSexAffection(participant)) {
					if(Main.sex.getAllParticipants().contains(Main.game.getPlayer())) {
						if((Main.sex.getSexPace(participant)==SexPace.SUB_RESISTING && !participant.getFetishDesire(Fetish.FETISH_NON_CON_SUB).isPositive())) {
							for(GameCharacter domParticipant : Main.sex.getDominantParticipants(false).keySet()) {
								endSexSB.append(participant.incrementAffection(domParticipant, -200, "[npc.Name] now despises [npc2.name] for raping [npc.herHim]!"));
							}
							
						} else if(characterWhoEndedSex!=participant) {
							// Affection loss only applies towards who ended sex early:
							if(participant.getFetishDesire(Fetish.FETISH_DENIAL_SELF).isPositive()) {
								if(participant.isAbleToOrgasm() && Main.sex.getNumberOfDeniedOrgasms(participant)==0 && Main.sex.getNumberOfOrgasms(participant)==0) {
									endSexSB.append(participant.incrementAffection(characterWhoEndedSex, -5f, "[npc.Name] is upset at [npc2.name] for failing to give or deny [npc.herHim] a single orgasm."));
								}
								
							} else {
								int orgasms = Main.sex.getNumberOfOrgasms(participant);
								if(Main.sex.getNumberOfOrgasms(participant)==0) {
									endSexSB.append(participant.incrementAffection(characterWhoEndedSex, -5f, "[npc.Name] is upset at [npc2.name] for failing to give [npc.herHim] a single orgasm."));
									
								} else if(orgasms < participant.getOrgasmsBeforeSatisfied()){
									endSexSB.append(participant.incrementAffection(characterWhoEndedSex, -2.5f,
											"[npc.Name] is annoyed at [npc2.name] for only giving [npc.herHim] "+Util.intToString(orgasms)+" orgasm"+(orgasms==1?"":"s")
												+", when [npc.she] really wanted at least "+Util.intToString(participant.getOrgasmsBeforeSatisfied())+"."));
								}
							}
						}
					}
				}
				
				endSexSB = new StringBuilder(UtilText.parse(participant, endSexSB.toString()));
			}
			for(AbstractStatusEffect se : new ArrayList<>(participant.getStatusEffects())) {
				if(se.isRemoveAtEndOfSex()) {
					participant.removeStatusEffect(se);
				}
			}
		}
		
		endSexSB.append(Main.sex.getSexManager().applyEndSexEffects());
		
		String s = endSexSB.toString();
		if(!s.isEmpty()) {
			endSexDescription ="<p style='text-align:center;'>"
					+ "<b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>End of sex status</b>"
							+ "</p>"
					+s;
		} else {
			endSexDescription = s;
		}
	}
	
	// Text formatting:
	
	private String formatInitialPenetration(String description) {
		return "<p style='text-align:center;'><i style='color:" + BaseColour.PINK_DEEP.toWebHexString() + "; margin-top:0; margin-bottom:0; padding-top:0; padding-bottom:0;'>"+description+"</i></p>";
	}
	
	public String formatPenetration(String rawInput){
		return "<p style='text-align:center; color:"+BaseColour.PINK_SALMON.toWebHexString()+"; margin-top:0; margin-bottom:0; padding-top:0; padding-bottom:0;'><i>"+rawInput+"</i></p>";
	}
	
	private String formatStopPenetration(String rawInput){
		return "<p style='text-align:center; color:"+BaseColour.PINK.toWebHexString()+"; margin-top:0; margin-bottom:0; padding-top:0; padding-bottom:0;'><i>"+rawInput+"</i></p>";
	}
	
	private String formatCoverableAreaBecomingExposed(String description) {
		return "<p style='text-align:center; margin-top:0; margin-bottom:0; padding-top:0; padding-bottom:0;'><i style='color:" + BaseColour.PURPLE_LIGHT.toWebHexString() + ";'>"+description+"</i></p>";
	}
	
	private String formatCoverableAreaGettingWet(String description) {
		return "<p style='text-align:center; margin-top:0; margin-bottom:0; padding-top:0; padding-bottom:0;'><i style='color:" + BaseColour.LILAC_LIGHT.toWebHexString() + ";'>"+description+"</i></p>";
	}

	
	public final DialogueNode SEX_DIALOGUE = new DialogueNode("", "", true) {
		
		@Override
		public int getSecondsPassed() {
			if(lastUsedSexAction.get(Main.game.getPlayer())==GenericActions.PLAYER_SKIP_SEX) {
				int seconds = 0;
				 // 3 minutes per character orgasm (always limited to 1 orgasm for unequal control subs) if 'Quick sex' is used:
				for(GameCharacter participant : getAllParticipants(false)) {
					if(Main.sex.isDom(participant) || Main.sex.isSubHasEqualControl()) {
						seconds+=Math.max(1, participant.getOrgasmsBeforeSatisfied()-Main.sex.getNumberOfOrgasms(participant))*3*60;
					} else {
						seconds+=3*60;
					}
				}
				return seconds;
			}
			return 20;
		}
		
		@Override
		public String getLabel() {
			return Main.sex.initialSexManager.getSexTitle();
		}

		@Override
		public String getContent() {
			return sexDescription + (sexFinished ? endSexDescription : "");
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(sexFinished
					|| (getItemUseInformation()!=null && getItemUseInformation().getValue().getKey().isPlayer() && !isForcingItemUse(getItemUseInformation().getKey(), Main.game.getPlayer()))
					|| isReadyToOrgasm(Main.game.getPlayer())
					|| Main.sex.isCharacterDeniedOrgasm(Main.game.getPlayer())
					|| (Main.sex.getTargetedPartner(Main.game.getPlayer())!=null && isReadyToOrgasm(Main.sex.getTargetedPartner(Main.game.getPlayer())))) {
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
			if(sexFinished) {
				if(index == 1) {
					return new Response(postSexDialogue.getLabel(), postSexDialogue.getDescription(), postSexDialogue){
						@Override
						public void effects() {
							endSex();
						}
					};
				} else {
					return null;
				}
				
			} else if(getItemUseInformation()!=null && getItemUseInformation().getValue().getKey().isPlayer() && !isForcingItemUse(getItemUseInformation().getKey(), Main.game.getPlayer())) {
				if(index==1) { // Refuse item (put in default 1 slot so if player is quickly clicking through sex, they don't accept something by accident):
					return SexActionUtility.PLAYER_REFUSE_ITEM_FROM_PARTNER.toResponse();
					
				} else if(index==2) { // Accept item:
					return SexActionUtility.PLAYER_ACCEPT_ITEM_FROM_PARTNER.toResponse();
					
				} else {
					return null;
				}
				
			// Orgasm actions:
			} else if(isReadyToOrgasm(Main.game.getPlayer())
					|| Main.sex.isCharacterDeniedOrgasm(Main.game.getPlayer())
					|| (Main.sex.getTargetedPartner(Main.game.getPlayer())!=null && isReadyToOrgasm(Main.sex.getTargetedPartner(Main.game.getPlayer())))) {
				if(index == 0){
					return null;

				} else if(index-1 < availableSexActionsPlayer.size() && availableSexActionsPlayer.get(index-1) != null){
					return availableSexActionsPlayer.get(index-1).toResponse();
					
				} else if(isReadyToOrgasm(Main.game.getPlayer())
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
						if(index==miscActionsPlayer.size() && !miscActionsPlayer.contains(GenericActions.PLAYER_SKIP_SEX)) {
							return new Response(GenericActions.PLAYER_SKIP_SEX.getActionTitle(), isMasturbation()?"You cannot use quick sex during masturbation scenes.":"As this is a special sex scene, you cannot skip it!", null);
						}
						
					} else {
						int i=index;
						if(index==0) {
							i=15;
						}
						if(i <= miscActionsPlayer.size()) {
							return miscActionsPlayer.get(i-1).toResponse();
						}
						if(i==miscActionsPlayer.size()+1 && !miscActionsPlayer.contains(GenericActions.PLAYER_SKIP_SEX)) {
							return new Response(GenericActions.PLAYER_SKIP_SEX.getActionTitle(), isMasturbation()?"You cannot use quick sex during masturbation scenes.":"As this is a special sex scene, you cannot skip it!", null);
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
			if(sexFinished
					|| isReadyToOrgasm(Main.game.getPlayer())
					|| Main.sex.isCharacterDeniedOrgasm(Main.game.getPlayer())
					|| (Main.sex.getTargetedPartner(Main.game.getPlayer())!=null && isReadyToOrgasm(Main.sex.getTargetedPartner(Main.game.getPlayer())))) {
					return true;
			}
			return false;
		}
	};
	
	/**
	 * Don't call this out of sex.
	 * @param sexActionPlayer The action that the player is taking this turn.
	 */
	public void endSexTurn(SexActionInterface sexActionPlayer) {

		sexSB = new StringBuilder();
		
		// Reset knotted map:
		getCharactersKnottedTogether().clear();
		
//		startTurnPlayerArousal = Main.game.getPlayer().getArousal(); //TODO test
//		System.out.println("startTurnPlayerArousal: "+startTurnPlayerArousal);
		
		// preDescriptionBaseEffects() and getFluidFlavourDescription() should already be formatted in p tags, so just enclose .getDescription() in them:
		sexSB.append(sexActionPlayer.preDescriptionBaseEffects()
					+"<p>"
						+ sexActionPlayer.getDescription()
					+ "</p>"
					+ sexActionPlayer.getFluidFlavourDescription(Main.game.getPlayer(), Main.sex.getTargetedPartner(Main.game.getPlayer())));
		
		String endString = sexActionPlayer.baseEffects();
		
		sexSB.append(applyGenericDescriptionsAndEffects(Main.game.getPlayer(), Main.sex.getTargetedPartner(Main.game.getPlayer()), sexActionPlayer));
		
		boolean endsSex = sexActionPlayer.endsSex();
		
		endString += sexActionPlayer.applyEndEffects();
		
		sexSB.append(endString);
		
		String s;
		if(sexActionPlayer.getLimitation()==null
				&& sexActionPlayer!=SexActionUtility.CLOTHING_REMOVAL
				&& sexActionPlayer!=SexActionUtility.CLOTHING_DYE) {
			s = UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(sexActionPlayer), sexSB.toString(), ParserTag.SEX_DESCRIPTION);
			
		} else {
			s = UtilText.parse(Main.sex.getCharacterTargetedForSexAction(sexActionPlayer), sexSB.toString(), ParserTag.SEX_DESCRIPTION);
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
		if(endsSex && !sexFinished) {
			sexDescription = sexSB.toString();

			applyEndSexEffects();
			sexFinished = true;
			
		} else {
			// Partner action is done afterwards:
			// Update lists for the partner's action choice.
			if(!Main.sex.isMasturbation()) {
				
				// If an NPC wants to use an item on the player, this should be the only action taken in this loop, so that the player can react to it without distraction or confusion from multiple item usages.
				itemUseInformation = null;
				Value<AbstractItem, String> sexItemValue = null;
				characterUsingItemLoop:
				for(GameCharacter character : Main.sex.getAllParticipants()) {
					if(!character.isPlayer()) {
						sexItemValue = ((NPC)character).getSexItemToUse(Main.game.getPlayer());
						if(sexItemValue!=null) {
							Main.sex.setItemUseInformation(character, Main.game.getPlayer(), sexItemValue.getKey());
							break characterUsingItemLoop;
						}
					}
				}
				
				for(GameCharacter character : Main.sex.getAllParticipants()) {
					if(!character.isPlayer()) {
						Main.sex.setCharacterPerformingAction(character);
						
						if(sexActionPlayer.getActionType()!=SexActionType.ORGASM && sexActionPlayer.getActionType()!=SexActionType.ORGASM_DENIAL) {
							Main.sex.getSexManager().assignNPCTarget(character);
							
							calculateAvailableSexActionsPartner();
							
							SexActionInterface sexActionPartner = sexManager.getPartnerSexAction((NPC) character, sexActionPlayer);
							
							if(itemUseInformation!=null) {
								sexActionPartner = SexActionUtility.PARTNER_USE_ITEM;
							}
							
							if(itemUseInformation==null || itemUseInformation.getKey().equals(character)) {
								sexSB.append("<br/>"
										+ "<p>"
											+ "<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>&gt; "+UtilText.parse(character, "[npc.Name]")+": "+(Util.capitaliseSentence(sexActionPartner.getActionTitle()))+"</span>"
											+ "</br>"
											+ sexActionPartner.preDescriptionBaseEffects()
											+ sexActionPartner.getDescription()
											+ sexActionPartner.getFluidFlavourDescription(character, Main.sex.getTargetedPartner(character))
										+ "</p>");
					
								endString = sexActionPartner.baseEffects();
								lastUsedSexAction.put(character, sexActionPartner);

								sexSB.append(applyGenericDescriptionsAndEffects(character, Main.sex.getTargetedPartner(character), sexActionPartner));
								
								endsSex = sexActionPartner.endsSex();
								
								endString += sexActionPartner.applyEndEffects();
								
								sexSB.append(endString);
								
								if(sexActionPartner.getLimitation()==null
										&& sexActionPartner!=SexActionUtility.CLOTHING_REMOVAL
										&& sexActionPartner!=SexActionUtility.CLOTHING_DYE) {
									s = UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(sexActionPartner), sexSB.toString(), ParserTag.SEX_DESCRIPTION);
									
								} else {
									s = UtilText.parse(character, sexSB.toString(), ParserTag.SEX_DESCRIPTION);
								}
								
								sexSB.setLength(0);
								sexSB.append(s);
								
								sexDescription = sexSB.toString();
							}
							
							// End sex conditions:
							if(endsSex && !sexFinished) {
								applyEndSexEffects();
								sexFinished = true;
								break;
							}
							
						} else {
							sexDescription = sexSB.toString();
						}
					}
				}
				Main.sex.setCharacterPerformingAction(Main.game.getPlayer());
				
			} else {
				sexDescription = sexSB.toString();
			}
			
			if(Main.sex.isPublicSex() && !sexFinished) {
				sexSB.append(Main.sex.getInitialSexManager().getRandomPublicSexDescription());
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
		
		if(SEX_DIALOGUE.getResponseTabTitle(1)!=null && preOrgasmTargeting!=null) {
			Main.game.setResponseTab(preOrgasmTargeting.getKey());
			preOrgasmTargeting = null;
		}
		
		turn++;
	}

	public void recalculateSexActions() {
		populatePlayerSexLists();
	}
	
	private void populatePlayerSexLists() {
		// Populate available SexActions from the current SexPosition.
		availableSexActionsPlayer.clear();

		List<SexActionInterface> uniqueActions = new ArrayList<>();
		List<SexActionInterface> normalActions = new ArrayList<>();

		boolean partnerOrgasming = false;
		
		if(isReadyToOrgasm(Main.game.getPlayer())) { // Add orgasm actions if player ready to orgasm:
			characterOrgasming = Main.game.getPlayer();
			if(preOrgasmTargeting==null) {
				preOrgasmTargeting = new Value<>(Main.game.getResponseTab(), Main.sex.getTargetedPartner(Main.game.getPlayer()));
			}
			
			// If there are unique maximum priority actions, only add those.
			for (SexActionInterface sexAction : Main.sex.getOrgasmActionsPlayer()) {
				if(sexAction.isAddedToAvailableSexActions()) {
					if(sexAction.getPriority()==SexActionPriority.UNIQUE_MAX) {
						uniqueActions.add(sexAction);
					} else {
						normalActions.add(sexAction);
					}
				}
			}
			
			
		} else {
			for(GameCharacter character : Main.sex.getAllParticipants(false)) {
				if(!character.isPlayer() && isReadyToOrgasm(character)) {
					characterOrgasming = character;
					if(preOrgasmTargeting==null) {
						preOrgasmTargeting = new Value<>(Main.game.getResponseTab(), Main.sex.getTargetedPartner(Main.game.getPlayer()));
					}
					Main.sex.setTargetedPartner(Main.game.getPlayer(), character);
					for (SexActionInterface sexAction : Main.sex.getActionsAvailablePlayer()) {
						if(sexAction.getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM && sexAction.isAddedToAvailableSexActions()) {
							if(sexAction.getPriority()==SexActionPriority.UNIQUE_MAX) {
								uniqueActions.add(sexAction);
							} else {
								normalActions.add(sexAction);
							}
						}
					}
					if(GenericActions.PLAYER_STOP_SEX.isAddedToAvailableSexActions()) {
						normalActions.add(GenericActions.PLAYER_STOP_SEX);
					}
					partnerOrgasming = true;
					break;
					
				} else {
					if(preOrgasmTargeting!=null) { //TODO
						Main.sex.setTargetedPartner(Main.game.getPlayer(), preOrgasmTargeting.getValue());
					}
				}
			}
			
			if(!partnerOrgasming) {
				// Can always do nothing:
				normalActions.add(SexActionUtility.PLAYER_NONE);
				normalActions.add(SexActionUtility.PLAYER_CALM_DOWN);
				
				// Add actions:
				for (SexActionInterface sexAction : Main.sex.getActionsAvailablePlayer()) {
					if(sexAction.isAddedToAvailableSexActions()){
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
				if(s1.getActionRenderingPriority()!=s2.getActionRenderingPriority()) {
					return s1.getActionRenderingPriority()>s2.getActionRenderingPriority()?-1:1;
				}
				if(s1.getActionType()==s2.getActionType()) {
					if(s1==GenericOrgasms.GENERIC_PREPARATION_DENIAL) {
						return 1;
					}
					if(s2==GenericOrgasms.GENERIC_PREPARATION_DENIAL) {
						return -1;
					}
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
		if(partnerOrgasming && Main.game.getPlayer().hasTrait(Perk.ORGASMIC_LEVEL_DRAIN, true)) {
			availableSexActionsPlayer.add(MiscActions.LEVEL_DRAIN_TOGGLE);
		}
		
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
		
		if(Main.sex.getTotalParticipantCount(false)>2) {
			for(GameCharacter character : Main.sex.getAllParticipants(false)) {
				if(!character.isPlayer() && !Main.sex.getTargetedPartner(Main.game.getPlayer()).equals(character)) {
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
														Main.sex.setTargetedPartner(Main.game.getPlayer(), character);
														Main.sex.recalculateSexActions();
													}
												});
				}
			}
		}
		
		miscActionsPlayer.addAll(characterSwitchActionsPlayer);
		if(miscActionsPlayer.contains(GenericActions.PLAYER_SKIP_SEX)) { // Put this action at the very end:
			miscActionsPlayer.remove(GenericActions.PLAYER_SKIP_SEX);
			miscActionsPlayer.add(GenericActions.PLAYER_SKIP_SEX);
		}
	}

	/**
	 * For use in getPartnerSexAction() in SexManager classes.
	 */
	private void calculateAvailableSexActionsPartner() {
		// Populate available SexActions from the current SexPosition.
		availableSexActionsPartner.clear();
		List<SexActionInterface> lowPriority = new ArrayList<>();
		List<SexActionInterface> normalPriority = new ArrayList<>();
		List<SexActionInterface> highPriority = new ArrayList<>();
		List<SexActionInterface> uniqueMax  = new ArrayList<>();
		boolean standardActions = true;
		GameCharacter targetedCharacter = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
		
		// Add orgasm actions if ready to orgasm:
		if(isReadyToOrgasm(Main.sex.getCharacterPerformingAction())) {
			characterOrgasming = Main.sex.getCharacterPerformingAction();
			standardActions = false;

			if(SexFlags.playerPreparedForCharactersOrgasm.contains(Main.sex.getCharacterPerformingAction())
					|| Main.sex.isSpectator(Main.sex.getCharacterPerformingAction())) {
				for (SexActionInterface sexAction : Main.sex.getOrgasmActionsPartner(Main.sex.getCharacterPerformingAction(), targetedCharacter)) {
					if(sexAction.isAddedToAvailableSexActions()) {
						int weight = ((NPC)Main.sex.getCharacterPerformingAction()).calculateSexTypeWeighting(sexAction.getAsSexType(), targetedCharacter, null);
						
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
			if(!availableSexActionsPartner.isEmpty()) {
				return;
			}
		}
		
		if(isReadyToOrgasm(Main.game.getPlayer()) && !Main.sex.isSpectator(Main.game.getPlayer())) { // Add orgasm reactions if ready to orgasm:
			characterOrgasming = Main.game.getPlayer();
			Main.sex.setTargetedPartner(Main.sex.getCharacterPerformingAction(), characterOrgasming);
			for (SexActionInterface sexAction : Main.sex.getActionsAvailablePartner(Main.sex.getCharacterPerformingAction(), characterOrgasming)) { //TODO manually set target as characterOrgasming. Test more
				if(sexAction.getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM) {
					if(sexAction.isAddedToAvailableSexActions()) {
						int weight = ((NPC)Main.sex.getCharacterPerformingAction()).calculateSexTypeWeighting(sexAction.getAsSexType(), characterOrgasming, null);
						
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
			if(!availableSexActionsPartner.isEmpty()) {
				return;
			}
			
		} else if(standardActions) {
			// Add actions:
			Set<SexActionInterface> actionsAvailableToPartner = Main.sex.getActionsAvailablePartner(Main.sex.getCharacterPerformingAction(), targetedCharacter);
			if(actionsAvailableToPartner!=null) {
				for (SexActionInterface sexAction : actionsAvailableToPartner) {
					if(sexAction.isAddedToAvailableSexActions() && (Main.sex.isCharacterAllowedToUseSelfActions(Main.sex.getCharacterPerformingAction()) || sexAction.getParticipantType()==SexParticipantType.NORMAL)) {
						
						// Do not add action if the partner is resisting and this action is SUB_EAGER or SUB_NORMAL or is a self action
						// Do not add action if action does not correspond to the partner's preferred action pace
						if((Main.game.isNonConEnabled()
							&& getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.SUB_RESISTING
							&& ((sexAction.getSexPace()!=null && sexAction.getSexPace()!=SexPace.SUB_RESISTING)
									|| sexAction.getParticipantType()==SexParticipantType.SELF
									|| (sexAction.getSexPace()==null && !sexAction.isOverrideAvailableDuringResisting())))
								|| (sexAction.getSexPace()!=null && sexAction.getSexPace()!=getSexPace(Main.sex.getCharacterPerformingAction()))) {
	//						System.out.println(Main.sex.getCharacterPerformingAction().getNameIgnoresPlayerKnowledge() +": "+ sexAction.getActionTitle());
							
						} else {
							// Add action as normal:
							int weight = ((NPC)Main.sex.getCharacterPerformingAction()).calculateSexTypeWeighting(sexAction.getAsSexType(), targetedCharacter, null);
							
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
				
			} else {
				System.err.println("Warning! Main.sex.getActionsAvailablePartner() is returning null for: "
						+(Main.sex.getCharacterPerformingAction()==null?"NULL":Main.sex.getCharacterPerformingAction().getId())
						+", "
						+(targetedCharacter==null?"NULL":targetedCharacter.getId()));
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
			if(availableSexActionsPartner.isEmpty()) {
				availableSexActionsPartner.add(SexActionUtility.PARTNER_NONE);
			}
		}

	}

	public List<SexActionInterface> getAvailableSexActionsPartner() {
		return availableSexActionsPartner;
	}

	/** Applies extra effects and generates extra description text. */
	private String applyGenericDescriptionsAndEffects(GameCharacter activeCharacter, GameCharacter targetCharacter, SexActionInterface sexAction) {
		// TODO Change targetPartner to a loop of all characters?
		StringBuilder stringBuilderForAppendingDescriptions = new StringBuilder();
		StringBuilder dirtiedSlotsSB = new StringBuilder();
		
		Map<GameCharacter, SexPace> initialPaces = new HashMap<>();
		for(GameCharacter participant : Main.sex.getAllParticipants()) {
			initialPaces.put(participant, Main.sex.getSexPace(participant));
		}
		
		// Increment arousal and lust:
		Map<GameCharacter, Float> arousalIncrements = new HashMap<>();
		Map<GameCharacter, Float> lustIncrements = new HashMap<>();
		
		arousalIncrements.put(activeCharacter, sexAction.getArousalGainSelf().getArousalIncreaseValue());
		if(!Main.sex.isMasturbation()) {
			arousalIncrements.put(targetCharacter, sexAction.getArousalGainTarget().getArousalIncreaseValue());
		}
		
		// Base lust gains are based on arousal gains:
		if(Main.sex.getSexPace(activeCharacter)==SexPace.SUB_RESISTING && !activeCharacter.getFetishDesire(Fetish.FETISH_NON_CON_SUB).isPositive()) {
			lustIncrements.put(activeCharacter, -2.5f);
		} else if(activeCharacter.getFetishDesire(Fetish.FETISH_NON_CON_DOM).isPositive() && Main.sex.getSexPace(targetCharacter) == SexPace.SUB_RESISTING) {
			lustIncrements.put(activeCharacter, 2.5f);
		} else {
			lustIncrements.put(activeCharacter, Math.min(2.5f, Math.max(-2.5f, sexAction.getArousalGainSelf().getArousalIncreaseValue())));
		}
		lustIncrements.put(activeCharacter, lustIncrements.get(activeCharacter) + (activeCharacter.getAffection(targetCharacter)/40)); //+-2.5
		
		if(Main.sex.getSexPace(targetCharacter)==SexPace.SUB_RESISTING && !targetCharacter.getFetishDesire(Fetish.FETISH_NON_CON_SUB).isPositive()) {
			lustIncrements.put(targetCharacter, -2.5f);
		} else if(targetCharacter.getFetishDesire(Fetish.FETISH_NON_CON_DOM).isPositive() && Main.sex.getSexPace(activeCharacter) == SexPace.SUB_RESISTING) {
			lustIncrements.put(targetCharacter, 2.5f);
		} else {
			lustIncrements.put(targetCharacter, Math.min(2.5f, Math.max(-2.5f, sexAction.getArousalGainTarget().getArousalIncreaseValue())));
		}
		lustIncrements.put(targetCharacter, lustIncrements.get(targetCharacter) + (targetCharacter.getAffection(activeCharacter)/40)); //+-2.5


		// Arousal increments for related status effects:
		for(AbstractStatusEffect se : activeCharacter.getStatusEffects()) {
			if(se.isSexEffect()) {
				arousalIncrements.put(activeCharacter, arousalIncrements.get(activeCharacter) + se.getArousalPerTurnSelf(activeCharacter));
				for(GameCharacter penetratingCharacter : Main.sex.getAllParticipants()) {
					if(!penetratingCharacter.equals(activeCharacter)) {
						arousalIncrements.putIfAbsent(penetratingCharacter, 0f);
						arousalIncrements.put(penetratingCharacter, arousalIncrements.get(penetratingCharacter) + se.getArousalPerTurnPartner(activeCharacter, penetratingCharacter));
					}
				}
			}
		}
		
		// Arousal increments for related fetishes:
		if(sexAction.getFetishes(activeCharacter)!=null) {// && sexAction.getSexPace()!=SexPace.SUB_RESISTING) {
			for(AbstractFetish f : sexAction.getFetishes(activeCharacter)) {
				if(activeCharacter.hasFetish(f)) {
					arousalIncrements.put(activeCharacter, arousalIncrements.get(activeCharacter) + activeCharacter.getFetishLevel(f).getBonusArousalIncrease());
					if(!Main.sex.isMasturbation()) {
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
			if(sexAction.getFetishesForTargetedPartner(activeCharacter)!=null && Main.sex.getSexPace(targetCharacter)!=SexPace.SUB_RESISTING) {
				for(AbstractFetish f : sexAction.getFetishesForTargetedPartner(activeCharacter)) {
					if(targetCharacter.hasFetish(f)) {
						arousalIncrements.put(targetCharacter, arousalIncrements.get(targetCharacter) + targetCharacter.getFetishLevel(f).getBonusArousalIncrease());
						if(!Main.sex.isMasturbation()) {
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
			if(Main.sex.getSexPositionSlot(activeCharacter)!=SexSlotGeneric.MISC_WATCHING || entry.getKey().equals(activeCharacter)) { // Spectators only influence themselves.
				if(sexAction.getActionType().isOrgasmOption() && entry.getKey().equals(activeCharacter)) { // Orgasm actions causes lust to drop:
					float lustLoss = activeCharacter.getLust()/2;
					
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
					if(activeCharacter.getLust()-lustLoss<15) {
						activeCharacter.setLustNoText(Math.min(activeCharacter.getLust(), 15));
					} else {
						activeCharacter.incrementLust(-lustLoss, false);
					}
					
				} else {
					float lustValue = entry.getValue();

					entry.getKey().incrementLust(Math.max(-2.5f, Math.min(2.5f, lustValue)), false);
				}
			}
		}
		
		if(sexAction.getArousalGainSelf().getArousalIncreaseValue()<0) {
			arousalIncrements.put(activeCharacter, sexAction.getArousalGainSelf().getArousalIncreaseValue());
		}
		if(!Main.sex.isMasturbation() && sexAction.getArousalGainTarget().getArousalIncreaseValue()<0) {
			arousalIncrements.put(targetCharacter, sexAction.getArousalGainTarget().getArousalIncreaseValue());
		}

		float startArousal = Main.game.getPlayer().getArousal();
		
		// Increment arousal:
		for(Entry<GameCharacter, Float> entry : arousalIncrements.entrySet()) {
			if(Main.sex.getSexPositionSlot(activeCharacter)!=SexSlotGeneric.MISC_WATCHING || entry.getKey().equals(activeCharacter)) { // Spectators only influence themselves.
				boolean foreplay = Main.sex.isInForeplay(entry.getKey());
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
				
				if(Main.sex.isMasturbation()) {
					arousal*=2;
				}
				
				if(entry.getKey().equals(activeCharacter) && sexAction.getArousalGainSelf().getArousalIncreaseValue()<0) { // For special negative arousal values.
					entry.getKey().incrementArousal(sexAction.getArousalGainSelf().getArousalIncreaseValue());
					
				} else if(entry.getKey().equals(targetCharacter) && sexAction.getArousalGainTarget().getArousalIncreaseValue()<0) { // For special negative arousal values.
					entry.getKey().incrementArousal(sexAction.getArousalGainTarget().getArousalIncreaseValue());
					
				} else {
					int sideDifference = Math.max(0, (Main.sex.isDom(entry.getKey())?-1:1)*Main.sex.getDominantParticipants(false).size()-Main.sex.getSubmissiveParticipants(false).size());
					
					float increment = Math.min(
							(5f+arousalCapIncrease)*(1f-(sideDifference/5f)),
							arousal * entry.getKey().getLustLevel().getArousalModifier()); // Modify arousal value based on lust

					if(Main.sex.isInForeplay(entry.getKey())) {
						increment/=2; // Halve arousal increases in foreplay, as otherwise foreplay gets skipped in 1 or 2 turns
					}
					 
					entry.getKey().incrementArousal(increment);
				}
				
				for(AbstractClothing c : activeCharacter.getClothingCurrentlyEquipped()) {
					if(c.isVibrator()) {
						switch(c.getVibratorIntensity()) {
							case MINOR_BOOST:
								activeCharacter.incrementArousal(0.5f);
								break;
							case BOOST:
								activeCharacter.incrementArousal(1);
								break;
							case MAJOR_BOOST:
								activeCharacter.incrementArousal(2);
								break;
							default:
								break;
						}
					}
				}
				
				// Player arousal can only reach 99 during partner's turns, to give them all a chance to react:
				if(!isOverridePlayerArousalRestriction()
						&& entry.getKey().isPlayer()
						&& !activeCharacter.isPlayer()
						&& entry.getKey().getArousal()>=100
						&& (startArousal<100 || sexAction.getActionType().isOrgasmOption())) {
					entry.getKey().setArousal(startingArousal);
				}
				
				if(foreplay && !Main.sex.isInForeplay(entry.getKey())) { // Reset positioning blocked when moving from foreplay to main sex:
					Main.sex.removeCharacterBannedFromPositioning(entry.getKey());
				}
			}
		}
		
		
		// Cummed in areas:
		
		// Add any areas that have been cummed in:
		boolean condomBrokeAppended = false;
		for(GameCharacter cumProvider : Main.sex.getAllParticipants()) {
			for(GameCharacter cumTarget :Main.sex.getAllParticipants()) {
				if(!cumProvider.equals(activeCharacter) && !cumTarget.equals(activeCharacter)) {
					continue;
				}
				
				if(cumProvider.getPenisOrgasmCumQuantity() == CumProduction.ZERO_NONE) {
					continue;
				}
				
				if(cumProvider.isWearingCondom()
						&& cumProvider.equals(activeCharacter)
						&& (sexAction.getActionType()==SexActionType.ORGASM || sexAction.getActionType()==SexActionType.ORGASM_DENIAL)) {
					CondomFailure condomFailure = sexAction.getCondomFailure(cumProvider, cumTarget);
					if(condomFailure==CondomFailure.NONE) {
						continue;
					} else if(!condomBrokeAppended) {
						stringBuilderForAppendingDescriptions.append(UtilText.parse(cumProvider,
								"<p style='text-align:center;'>[style.boldTerrible([npc.NamePos] condom broke as [npc.she] [npc.was] "+(condomFailure==CondomFailure.EGG_LAYING?"laying [npc.her] eggs":"cumming")+"!)]</p>"));
						condomBrokeAppended = true;
					}
				}
				
				List<SexAreaInterface> cummedInAreas = sexAction.getAreasCummedIn(cumProvider, cumTarget);
				if(cummedInAreas != null) {
					for (SexAreaInterface area : cummedInAreas) {
						cumTarget.incrementCumCount(cumProvider, new SexType(SexParticipantType.NORMAL, area, SexAreaPenetration.PENIS));
						cumProvider.incrementCumCount(cumTarget, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, area));
						if(area.isOrifice() && ((SexAreaOrifice) area).isInternalOrifice()) {
							if(sexAction==GenericOrgasms.GENERIC_ORGASM_DOUBLE_CREAMPIE) {
								stringBuilderForAppendingDescriptions.append(cumTarget.ingestFluid(cumProvider, cumProvider.getCum(), (SexAreaOrifice) area, cumProvider.getPenisRawOrgasmCumQuantity()/2));
							} else {
								stringBuilderForAppendingDescriptions.append(cumTarget.ingestFluid(cumProvider, cumProvider.getCum(), (SexAreaOrifice) area, cumProvider.getPenisRawOrgasmCumQuantity()));
							}
						}
						for(SexAreaInterface cummedArea : cummedInAreas) {
							Main.sex.incrementTimesCummedInside(cumProvider, cumTarget, cummedArea, 1);
						}
					}
				}
				
				List<CoverableArea> cummedOnAreas = sexAction.getAreasCummedOn(cumProvider, cumTarget);
				if(cummedOnAreas != null) {
					// The '<p style='text-align:center;'>[style.italicsCum(' section is appended before the 'getCharacterPerformingAction().applyOrgasmCumEffect' down below
						dirtiedSlotsSB.append("<br/>");
						dirtiedSlotsSB.append(applyCummedOnEffects(cummedOnAreas, cumProvider, cumTarget));
					dirtiedSlotsSB.append(")]</p>");
					
					CumProduction cumProduction = CumProduction.getCumProductionFromInt(cumProvider.getPenisRawOrgasmCumQuantity());
					int extraDirtySlots = cumProduction.getAdditionalSlotsDirtiedUponOrgasm();
					if(extraDirtySlots>0) {
						dirtiedSlotsSB.append("<p style='text-align:center;'>[style.boldSex(");
						dirtiedSlotsSB.append(UtilText.parse(cumProvider, cumTarget, "[npc.Name] [npc.verb(cum)] so much that [npc2.nameIsFull]"));
						switch(cumProduction) {
							case FOUR_LARGE:
							case FIVE_HUGE:
								dirtiedSlotsSB.append(" splattered all over by it!");
								break;
							case SIX_EXTREME:
								dirtiedSlotsSB.append(" almost completely coated by it!");
								break;
							case SEVEN_MONSTROUS:
								dirtiedSlotsSB.append(" absolutely drenched in it!");
								break;
							case THREE_AVERAGE:
							case TWO_SMALL_AMOUNT:
							case ONE_TRICKLE:
							case ZERO_NONE:
								break;
						}
						dirtiedSlotsSB.append(")]");
						// Apply extra slot dirtying effects:
						List<CoverableArea> extraCoverableAreasHit = new ArrayList<>(Arrays.asList(CoverableArea.values()));
						extraCoverableAreasHit.removeAll(cummedOnAreas);
						Collections.shuffle(extraCoverableAreasHit);
						extraCoverableAreasHit = extraCoverableAreasHit.subList(0, extraDirtySlots);
						dirtiedSlotsSB.append("<br/>[style.italicsCum(");
							dirtiedSlotsSB.append(applyCummedOnEffects(extraCoverableAreasHit, cumProvider, cumTarget));
						dirtiedSlotsSB.append(")]</p>");
					}
				}
			}
		}

		if(sexAction.getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM && Main.sex.getCharacterPerformingAction().isPlayer()) {
			SexFlags.playerPreparedForCharactersOrgasm.add(Main.sex.getCharacterTargetedForSexAction(sexAction));
		}
		
		// Handle orgasms:
		if(sexAction.getActionType()==SexActionType.ORGASM) {
			// Condom removal:
			if(Main.sex.getCharacterPerformingAction().isWearingCondom()) {
				if(sexAction.getCondomFailure(activeCharacter, targetCharacter)==CondomFailure.NONE) {
					Main.sex.getCharacterPerformingAction().getClothingInSlot(InventorySlot.PENIS).setSealed(false);
					if(Main.sex.getCharacterPerformingAction().getPenisRawOrgasmCumQuantity()>0) {
						stringBuilderForAppendingDescriptions.append(Main.game.getPlayer().addItem(
								Main.game.getItemGen().generateFilledCondom(
										Main.sex.getCharacterPerformingAction().getClothingInSlot(InventorySlot.PENIS).getClothingType().equals(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"))
											?ItemType.CONDOM_USED_WEBBING
											:ItemType.CONDOM_USED,
										Main.sex.getCharacterPerformingAction().getClothingInSlot(InventorySlot.PENIS).getColour(0),
										Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterPerformingAction().getCum(), Main.sex.getCharacterPerformingAction().getPenisRawOrgasmCumQuantity()),
								false, true));
					}
					Main.sex.stopAllOngoingActions(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
				}
				Main.sex.getCharacterPerformingAction().unequipClothingIntoVoid(Main.sex.getCharacterPerformingAction().getClothingInSlot(InventorySlot.PENIS), true, Main.sex.getCharacterPerformingAction());
			}
			
			if(Main.sex.getCharacterPerformingAction().hasVagina()) {
				int squirtAmount = Main.sex.getCharacterPerformingAction().getVaginaWetness().getValue();
				
				if(Main.sex.getCharacterPerformingAction().isVaginaSquirter()) {
					squirtAmount = Math.max(squirtAmount, 1);
					squirtAmount *= 5;
				}
				
				if(squirtAmount>0) {
					AbstractClothing vaginaClothing = Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.VAGINA);
					if(vaginaClothing!=null) {
						if(vaginaClothing.isMilkingEquipment()
								&& Main.sex.getCharacterPerformingAction().isSlave()
								&& Main.sex.getCharacterPerformingAction().getSlaveJob(Main.game.getHourOfDay())==SlaveJob.MILKING
								&& Main.sex.getCharacterPerformingAction().isAtWork()) {
							Cell c = MilkingRoom.getMilkingCell(Main.sex.getCharacterPerformingAction(), true);
							MilkingRoom room = Main.game.getOccupancyUtil().getMilkingRoom(c.getType(), c.getLocation());
							room.incrementFluidStored(new FluidStored(Main.sex.getCharacterPerformingAction().getId(), Main.sex.getCharacterPerformingAction().getGirlcum(), squirtAmount), squirtAmount);
							stringBuilderForAppendingDescriptions.append("<p style='text-align:center; padding:0; margin:0;'>"
										+ UtilText.parse(Main.sex.getCharacterPerformingAction(), "[style.italicsGirlCum("+Units.fluid(squirtAmount)+" of [npc.namePos] [npc.girlcum] is sucked down into the milking machine's storage vat!)]")
									+ "</p>");
									
						} else if(!vaginaClothing.getItemTags().contains(ItemTag.PLUGS_VAGINA)
								&& !vaginaClothing.getItemTags().contains(ItemTag.SEALS_VAGINA)) {
							vaginaClothing.setDirty(Main.sex.getCharacterPerformingAction(), true);
							stringBuilderForAppendingDescriptions.append("<p style='text-align:center;'>");
								stringBuilderForAppendingDescriptions.append("[style.italicsGirlCum(");
									stringBuilderForAppendingDescriptions.append("[npc.NamePos] "+vaginaClothing.getName()+" "+(vaginaClothing.getClothingType().isPlural()?"are":"is")+" dirtied from [npc.her] squirting!");
								stringBuilderForAppendingDescriptions.append(")]");
							stringBuilderForAppendingDescriptions.append("</p>");
						}
						
					} else {
						Set<GameCharacter> charactersEatingOut = new HashSet<>(getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
						charactersEatingOut.addAll(getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaOrifice.MOUTH));
						
						for(GameCharacter character : charactersEatingOut) {
							stringBuilderForAppendingDescriptions.append("<p style='text-align:center;'>");
								stringBuilderForAppendingDescriptions.append("[style.italicsGirlCum(");
									stringBuilderForAppendingDescriptions.append(applySquirtedOnEffects(Util.newArrayListOfValues(InventorySlot.MOUTH, InventorySlot.EYES, InventorySlot.HAIR), Main.sex.getCharacterPerformingAction(), character));
								stringBuilderForAppendingDescriptions.append(")]");
							stringBuilderForAppendingDescriptions.append("</p>");
							
							stringBuilderForAppendingDescriptions.append(character.ingestFluid(
									Main.sex.getCharacterPerformingAction(),
									Main.sex.getCharacterPerformingAction().getGirlcum(),
									SexAreaOrifice.MOUTH,
									squirtAmount));
						}
					}
				}

				// If under the effects of Amazonian's Secret, then this character can impregnate partners who are directly contacting their vagina:
				if(Main.sex.getCharacterPerformingAction().hasStatusEffect("innoxia_amazons_secret")) {
					Set<GameCharacter> charactersContactingVagina = new HashSet<>(getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT));
					charactersContactingVagina.addAll(getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA));
					charactersContactingVagina.addAll(getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaOrifice.VAGINA));
					charactersContactingVagina.addAll(getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT, SexAreaPenetration.CLIT));
					
					if(!charactersContactingVagina.isEmpty()) {
						stringBuilderForAppendingDescriptions.append("<p style='text-align:center; padding:0; margin:0;'>");
						stringBuilderForAppendingDescriptions.append(UtilText.parse(Main.sex.getCharacterPerformingAction(),
								"<i>[style.boldSex(Feminine Fertility:)]</i>"));
						for(GameCharacter character : charactersContactingVagina) {
							stringBuilderForAppendingDescriptions.append(UtilText.parse(Main.sex.getCharacterPerformingAction(), character,
									"<br/><i>[npc.NamePos] orgasm triggers a wave of hot arcane energy that rushes up deep into [npc2.namePos] [npc2.pussy+],"
									+ " causing [npc2.herHim] to [npc2.verb(let)] out an erotic moan as [npc2.she] [npc2.verb(realise)] that"
									+ (character.isVisiblyPregnant() || character.hasIncubationLitter(SexAreaOrifice.VAGINA)
											?" if it weren't for the fact that [npc2.her] womb is already occupied, [npc2.she] might have got pregnant from this!"
											:" [npc2.she] might get pregnant from this!")
									+ "</i>"));
							stringBuilderForAppendingDescriptions.append(character.rollForPregnancy(Main.sex.getCharacterPerformingAction(), 1, true, FertilisationType.TRIBBING, Attribute.FERTILITY));
						}
						stringBuilderForAppendingDescriptions.append("</p>");
					}
				}
			}
			
			
			// Apply orgasm arousal resets:
			if((Main.sex.getCharacterPerformingAction().hasPenis() && Main.sex.getCharacterPerformingAction().getPenisRawOrgasmCumQuantity()>0)
					|| Main.sex.getCharacterPerformingAction().hasVagina()
					|| Main.sex.getNumberOfOrgasms(Main.sex.getCharacterPerformingAction())>0) {
				incrementNumberOfOrgasms(Main.sex.getCharacterPerformingAction(), 1);
				Main.sex.getCharacterPerformingAction().setArousal(Main.sex.getCharacterPerformingAction().getLust()/4f);
				if((Main.sex.getCharacterPerformingAction().hasPenis() && Main.sex.getCharacterPerformingAction().getPenisRawOrgasmCumQuantity()>0)) {
					stringBuilderForAppendingDescriptions.append("<p style='text-align:center;'>[style.italicsCum(");
					stringBuilderForAppendingDescriptions.append(Main.sex.getCharacterPerformingAction().applyOrgasmCumEffect(1, false));
					if(dirtiedSlotsSB.length()>0) {
						stringBuilderForAppendingDescriptions.append(dirtiedSlotsSB.toString());
					} else {
						stringBuilderForAppendingDescriptions.append(")]</p>");
					}
				}
				
			} else {
				incrementNumberOfOrgasms(Main.sex.getCharacterPerformingAction(), 1);
				Main.sex.getCharacterPerformingAction().setArousal(Main.sex.getCharacterPerformingAction().getLust()/2f);
				Main.sex.getCharacterPerformingAction().addStatusEffect(StatusEffect.FRUSTRATED_NO_ORGASM, (4*60*60)+(postSexDialogue.getSecondsPassed()));
				stringBuilderForAppendingDescriptions.append(UtilText.parse(Main.sex.getCharacterPerformingAction(),
						"<p style='text-align:center'>Without producing any cum, [npc.namePos] climax can't be counted as a real orgasm, and makes [npc.name] feel [style.boldBad(frustrated and horny)]!</p>"));
			}
			
			// Draining levels:
			Set<GameCharacter> levelDrains = new HashSet<>();
			if(!Main.sex.getCharacterPerformingAction().isImmuneToLevelDrain() && !Main.sex.getInitialSexManager().isHidden(Main.sex.getCharacterPerformingAction())) {
				if(Main.sex.isDom(Main.sex.getCharacterPerformingAction())) {
					for(GameCharacter sub : Main.sex.getSubmissiveParticipants(true).keySet()) {
						if(sub.hasTrait(Perk.ORGASMIC_LEVEL_DRAIN, true) && ((!sub.isPlayer() && sub.isWantingToLevelDrain(Main.sex.getCharacterPerformingAction())) || Main.sex.playerLevelDrain)) {
							levelDrains.add(sub);
						}
					}
					
				} else {
					for(GameCharacter dom : Main.sex.getDominantParticipants(true).keySet()) {
						if(dom.hasTrait(Perk.ORGASMIC_LEVEL_DRAIN, true) && ((!dom.isPlayer() && dom.isWantingToLevelDrain(Main.sex.getCharacterPerformingAction())) || Main.sex.playerLevelDrain)) {
							levelDrains.add(dom);
						}
					}
				}
			}
			if(!Main.game.isBadEnd()) { // Do not drain levels during a bad end
				for(GameCharacter drainer : levelDrains) {
					if(drainer.isLevelDrainAvailableToUse()) {
						stringBuilderForAppendingDescriptions.append(drainer.applyLevelDrain(Main.sex.getCharacterPerformingAction()));
					}
				}
			}
			
			// Reset appropriate flags:
			removeCharacterBannedFromPositioning(getCharacterPerformingAction());
			charactersRequestingCreampie = new HashSet<>();
			charactersRequestingKnot = new HashSet<>();
			charactersRequestingPullout = new HashMap<>();
			SexFlags.playerPreparedForCharactersOrgasm.remove(getCharacterPerformingAction());
		}

		// Handle if parts have just become exposed:
		if(sexAction == SexActionUtility.CLOTHING_REMOVAL) {
			for(GameCharacter character : Main.sex.getAllParticipants()) {
				if(Main.sex.getSexPositionSlot(character)!=SexSlotGeneric.MISC_WATCHING) {
					stringBuilderForAppendingDescriptions.append(handleExposedDescriptions(character, false));
				}
			}
		}
		
		// Only apply penetration effects if this action isn't an orgasm, and it isn't the end of sex. (Otherwise, ongoing descriptions get appended after the main description, which usually don't make sense.) TODO
		if(
//				!Main.sex.getOrgasmActionsPlayer().contains(sexAction)
//				&& (Main.sex.isMasturbation() || !Main.sex.getOrgasmActionsPartner(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction())).contains(sexAction))
				sexAction.getCategory()!=SexActionCategory.POSITIONING
				&& !sexAction.endsSex()) {

//			GameCharacter character = activeCharacter;
			for(GameCharacter character : Main.sex.getAllParticipants()) {
				if(character.equals(activeCharacter) || character.equals(targetCharacter)) { //TODO For every character?
					for(Entry<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> entry : ongoingActionsMap.get(character).entrySet()) {
						for(GameCharacter characterTarget : Main.sex.getAllParticipants()) {
							
							// Reset loose areas ready to be re-applied in applyPenetrationEffects()
							for(SexAreaOrifice o : SexAreaOrifice.values()) {
								if(!Main.sex.isOrificeNonSelfOngoingAction(characterTarget, o)) {
									areasTooLoose.get(characterTarget).remove(o);
								}
							}
							
							if(entry.getValue().containsKey(characterTarget)) {
								for(SexAreaInterface sArea : entry.getValue().get(characterTarget)) {
									if(entry.getKey().isPenetration()) {
//										if(activeCharacter.equals(targetCharacter)) {
//											System.out.println(activeCharacter.getNameIgnoresPlayerKnowledge());
//										}
										stringBuilderForAppendingDescriptions.append(applyPenetrationEffects(sexAction, character, (SexAreaPenetration)entry.getKey(), characterTarget, sArea));
									}
									
									SexType ongoingSexType = new SexType(SexParticipantType.NORMAL, entry.getKey(), sArea);
									
									int weight = character.calculateSexTypeWeighting(ongoingSexType, characterTarget, null, true);
									int targetWeight = characterTarget.calculateSexTypeWeighting(ongoingSexType.getReversedSexType(), character, null, true);
									
									character.incrementLust(Math.max(-2.5f, Math.min(2.5f, (weight*0.25f))), false);
									characterTarget.incrementLust(Math.max(-2.5f, Math.min(2.5f, (targetWeight*0.25f))), false);
									
									// Half xp from ongoing:
									List<AbstractFetish> selfFetishes = sexAction.getFetishesFromPenetrationAndOrificeTypes(character, entry.getKey(), characterTarget, sArea, true);
									List<AbstractFetish> targetFetishes = sexAction.getFetishesFromPenetrationAndOrificeTypes(character, entry.getKey(), characterTarget, sArea, false);
									
									for(AbstractFetish f : selfFetishes) {
										character.incrementFetishExperience(f, f.getExperienceGainFromSexAction()/2);
									}
									for(AbstractFetish f : targetFetishes) {
										characterTarget.incrementFetishExperience(f, f.getExperienceGainFromSexAction()/2);
									}
								}
							}
						}
					}
				}
			}
			
		}
		
		stringBuilderForAppendingDescriptions.append(calculateWetAreas(false));
		
		for(Entry<GameCharacter, SexPace> entry : initialPaces.entrySet()) {
			SexPace finalPace = Main.sex.getSexPace(entry.getKey());
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
		
		if(this.getInitialSexManager().isWashingScene()) {
			if(!activeCharacter.getDirtySlots().isEmpty()) {
				activeCharacter.cleanAllDirtySlots(true);
				stringBuilderForAppendingDescriptions.append(UtilText.parse(activeCharacter,
						"<p style='text-align:center;'>"
							+ "[style.italicsAqua([npc.NamePos] body is quickly cleaned by the flowing water!)]"
						+ "</p>"));
			}
			if(activeCharacter.getClothingCurrentlyEquipped().stream().anyMatch(c->c.isDirty())) {
				stringBuilderForAppendingDescriptions.append(activeCharacter.cleanAllClothing(false, true));
			}
		}
		
		return stringBuilderForAppendingDescriptions.toString();
	}

	/**
	 * Applies dirtying effects to slots and clothing.
	 * @return A parsed, <b>but not formatted</b>, description of which slots and clothing were dirtied.
	 */
	public String applyCummedOnEffects(CoverableArea cummedOnArea, GameCharacter cumProvider, GameCharacter cumTarget, boolean applyFormatting) {
		return applyCummedOnEffects(Util.newArrayListOfValues(cummedOnArea), cumProvider, cumTarget, applyFormatting);
	}
	public String applyCummedOnEffects(CoverableArea cummedOnArea, GameCharacter cumProvider, GameCharacter cumTarget) {
		return applyCummedOnEffects(cummedOnArea, cumProvider, cumTarget, false);
	}
	
	/**
	 * Applies dirtying effects to slots and clothing.
	 * @return A parsed, <b>but not formatted</b>, description of which slots and clothing were dirtied.
	 */
	public String applyCummedOnEffects(List<CoverableArea> cummedOnAreas, GameCharacter cumProvider, GameCharacter cumTarget, boolean applyFormatting) {
		List<String> clothingDirtied = new ArrayList<>();
		List<String> slotsDirtied = new ArrayList<>();
		StringBuilder dirtiedSlotsSB = new StringBuilder();
		List<InventorySlot> slotsEncountered = new ArrayList<>();
		List<AbstractClothing> clothingEncountered = new ArrayList<>();
		boolean nonClothingAreaDirtied = false;
		
		if(applyFormatting) {
			dirtiedSlotsSB.append("<p style='text-align:center;'>[style.italicsCum(");
		}
		for(CoverableArea area : cummedOnAreas) {
			for(InventorySlot slot : area.getAssociatedInventorySlots(cumTarget)) {
				if(slot.isPhysicallyAvailable(cumTarget) && !slotsEncountered.contains(slot)) {
					List<AbstractClothing> dirtyClothing = new ArrayList<>(cumTarget.getVisibleClothingConcealingSlot(slot));
					AbstractClothing clothingInSlot = cumTarget.getClothingInSlot(slot);
					if(cumTarget.getClothingInSlot(slot)!=null && !dirtyClothing.contains(clothingInSlot)) {
						dirtyClothing.add(clothingInSlot);
						// Dirty the slot as well, as this clothing was only added if it wasn't concealing the slot, and so the slot should also be dirtied in this condition.
						// e.g. A necklace might be in the neck slot, but is not concealing the slot. As such, the neck slot should be dirtied as well as the necklace.
						cumTarget.addDirtySlot(slot);
						slotsDirtied.add(slot.getNameOfAssociatedPart(cumTarget));
					}
					if(!dirtyClothing.isEmpty()) {
						for(AbstractClothing c : dirtyClothing) {
							if(!clothingEncountered.contains(c)) {
								c.setDirty(cumTarget, true);
								clothingDirtied.add(c.getName());
								clothingEncountered.add(c);
							}
						}
						
					} else if(slot!=InventorySlot.TORSO_OVER) { // Do not dirty over-torso slot, as it doesn't really make much sense...
						cumTarget.addDirtySlot(slot);
						slotsDirtied.add(slot.getNameOfAssociatedPart(cumTarget));
						nonClothingAreaDirtied = true;
					}
					slotsEncountered.add(slot);
				}
			}
		}
		dirtiedSlotsSB.append(getDirtyingAreasString(cumProvider, cumTarget, slotsDirtied, clothingDirtied));
		if(Main.game.isMuskContentEnabled() && nonClothingAreaDirtied && cumProvider.hasCumModifier(FluidModifier.MUSKY)) {
			cumTarget.setMuskMarker(cumProvider.getId());
			dirtiedSlotsSB.append("<br/>[npc2.NameIsFull] marked by the musky scent of [npc.namePos] cum!");
		}
		if(applyFormatting) {
			dirtiedSlotsSB.append(")]</p>");
		}
		return UtilText.parse(cumProvider, cumTarget, dirtiedSlotsSB.toString());
	}
	public String applyCummedOnEffects(List<CoverableArea> cummedOnAreas, GameCharacter cumProvider, GameCharacter cumTarget) {
		return applyCummedOnEffects(cummedOnAreas, cumProvider, cumTarget, false);
	}

	/**
	 * Applies dirtying effects to slots and clothing.
	 * @return A parsed, <b>but not formatted</b>, description of which slots and clothing were dirtied.
	 */
	private String applySquirtedOnEffects(List<InventorySlot> squirterSlots, GameCharacter squirtProvider, GameCharacter squirtTarget) {
		List<String> clothingDirtied = new ArrayList<>();
		List<String> slotsDirtied = new ArrayList<>();
		StringBuilder dirtiedSlotsSB = new StringBuilder();
		List<InventorySlot> slotsEncountered = new ArrayList<>();
		List<AbstractClothing> clothingEncountered = new ArrayList<>();
		boolean nonClothingAreaDirtied = false;
		
		for(InventorySlot slot : squirterSlots) {
			if(slot.isPhysicallyAvailable(squirtTarget) && !slotsEncountered.contains(slot)) {
				List<AbstractClothing> dirtyClothing = new ArrayList<>(squirtTarget.getVisibleClothingConcealingSlot(slot));
				AbstractClothing clothingInSlot = squirtTarget.getClothingInSlot(slot);
				if(squirtTarget.getClothingInSlot(slot)!=null && !dirtyClothing.contains(clothingInSlot)) {
					dirtyClothing.add(clothingInSlot);
					// Dirty the slot as well, as this clothing was only added if it wasn't concealing the slot, and so the slot should also be dirtied in this condition.
					// e.g. A necklace might be in the neck slot, but is not concealing the slot. As such, the neck slot should be dirtied as well as the necklace.
					squirtTarget.addDirtySlot(slot);
					slotsDirtied.add(slot.getNameOfAssociatedPart(squirtTarget));
				}
				
				if(!dirtyClothing.isEmpty()) {
					for(AbstractClothing c : dirtyClothing) {
						if(!clothingEncountered.contains(c)) {
							c.setDirty(squirtTarget, true);
							clothingDirtied.add(c.getName());
							clothingEncountered.add(c);
						}
					}
					
				} else {
					squirtTarget.addDirtySlot(slot);
					slotsDirtied.add(slot.getNameOfAssociatedPart(squirtTarget));
					nonClothingAreaDirtied = true;
				}
				slotsEncountered.add(slot);
			}
		}
		dirtiedSlotsSB.append(getDirtyingAreasString(squirtProvider, squirtTarget, slotsDirtied, clothingDirtied));
		if(Main.game.isMuskContentEnabled() && nonClothingAreaDirtied && squirtProvider.hasCumModifier(FluidModifier.MUSKY)) {
			squirtTarget.setMuskMarker(squirtProvider.getId());
			dirtiedSlotsSB.append("<br/>[npc2.NameIsFull] marked by the musky scent of [npc.namePos] cum!");
		}
		return UtilText.parse(squirtProvider, squirtTarget, dirtiedSlotsSB.toString());
	}
	
	private static String getDirtyingAreasString(GameCharacter cumProvider, GameCharacter cumTarget, List<String> slotsDirtied, List<String> clothingDirtied) {
		StringBuilder dirtiedSlotsSB = new StringBuilder();
		if(!slotsDirtied.isEmpty() || !clothingDirtied.isEmpty()) {
			if(!slotsDirtied.isEmpty()) {
				dirtiedSlotsSB.append("[npc2.NamePos] "+Util.stringsToStringList(slotsDirtied, false));
				dirtiedSlotsSB.append(slotsDirtied.size()==1?" is":" are");
				dirtiedSlotsSB.append(" dirtied");
			}
			if(!clothingDirtied.isEmpty()) {
				if(!slotsDirtied.isEmpty()) {
					dirtiedSlotsSB.append(", as "+(clothingDirtied.size()==1?"is":"are")+" [npc2.her] ");
					dirtiedSlotsSB.append(Util.stringsToStringList(clothingDirtied, false));
					dirtiedSlotsSB.append("!");
				} else {
					dirtiedSlotsSB.append("[npc2.NamePos] ");
					dirtiedSlotsSB.append(Util.stringsToStringList(clothingDirtied, false));
					dirtiedSlotsSB.append((clothingDirtied.size()==1?" is":" are")+" dirtied!");
				}
			} else {
				dirtiedSlotsSB.append("!");
			}
		}
		return dirtiedSlotsSB.toString();
	}
	
	private static boolean isAnyCharacterAbleToSeeArea(GameCharacter target, List<GameCharacter> charactersReacting, InventorySlot area) {
		for(GameCharacter reacting : charactersReacting) {
			if(!Main.sex.initialSexManager.getSlotsConcealed(target, reacting).contains(area)) {
				return true;
			}
		}
		return false;
	}
	
	private String handleExposedDescriptions(GameCharacter characterBeingExposed, boolean atStartOfSex) {
		StringBuilder exposedSB = new StringBuilder();
		
		List<GameCharacter> charactersReacting = new ArrayList<>(Main.sex.getAllParticipants());
		charactersReacting.remove(characterBeingExposed);
		
		// Just have one character react, as otherwise the spam is too much:
		if(charactersReacting.contains(Main.game.getPlayer()) && !isSpectator(Main.game.getPlayer())) {
			charactersReacting.removeIf(c->!c.isPlayer());
		} else {
			if(isDom(characterBeingExposed)) {
				if(charactersReacting.stream().anyMatch(c->!Main.sex.isDom(c))) {
					charactersReacting.removeIf(c->Main.sex.isDom(c));
					charactersReacting = charactersReacting.subList(0, 1);
				}
			} else if(charactersReacting.stream().anyMatch(c->Main.sex.isDom(c))) {
				charactersReacting.removeIf(c->!Main.sex.isDom(c));
				charactersReacting = charactersReacting.subList(0, 1);
			}
		}
		
		// Asshole and genitals:
		
		if(!areasExposed.get(characterBeingExposed).contains(CoverableArea.ANUS) && isAnyCharacterAbleToSeeArea(characterBeingExposed, charactersReacting, InventorySlot.ANUS)) {
			if(characterBeingExposed.isCoverableAreaVisible(CoverableArea.ANUS)) {
				exposedSB.append(UtilText.parse(characterBeingExposed,
						formatCoverableAreaBecomingExposed(
								(atStartOfSex
										?"[npc.NamePos] [npc.asshole+] was already exposed before starting sex!"
										:"[npc.NamePos] [npc.asshole+] is now exposed!")))
						+ sexManager.getAssRevealReaction(characterBeingExposed, charactersReacting, false)
						+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, SexAreaOrifice.ANUS)));
				areasExposed.get(characterBeingExposed).add(CoverableArea.ANUS);
			}
		}
		if(!areasExposed.get(characterBeingExposed).contains(CoverableArea.PENIS) && isAnyCharacterAbleToSeeArea(characterBeingExposed, charactersReacting, InventorySlot.PENIS)) {
			if(characterBeingExposed.isCoverableAreaVisible(CoverableArea.PENIS)) {
				if(characterBeingExposed.hasPenis()) {
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
		if(!areasExposed.get(characterBeingExposed).contains(CoverableArea.VAGINA) && isAnyCharacterAbleToSeeArea(characterBeingExposed, charactersReacting, InventorySlot.VAGINA)) {
			if(characterBeingExposed.isCoverableAreaVisible(CoverableArea.VAGINA)) {
				if(characterBeingExposed.hasVagina()) {
					exposedSB.append(UtilText.parse(characterBeingExposed,
							formatCoverableAreaBecomingExposed(
									(atStartOfSex
											?"[npc.NamePos] [npc.pussy+] was already exposed before starting sex!"
											:"[npc.NamePos] [npc.pussy+] is now exposed!")))
							+ sexManager.getVaginaRevealReaction(characterBeingExposed, charactersReacting)
							+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, SexAreaOrifice.VAGINA))
							+ formatCoverableAreaGettingWet(getLubricationDescription(characterBeingExposed, SexAreaPenetration.CLIT)));

				} else if(characterBeingExposed.getVaginaType() == VaginaType.NONE && characterBeingExposed.getPenisType() == PenisType.NONE) {
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
		
		// Breasts & crotch-boobs:
		if(!areasExposed.get(characterBeingExposed).contains(CoverableArea.NIPPLES) && isAnyCharacterAbleToSeeArea(characterBeingExposed, charactersReacting, InventorySlot.NIPPLE)) {
			if(characterBeingExposed.isCoverableAreaVisible(CoverableArea.NIPPLES)
					&& (!characterBeingExposed.isFeral() || characterBeingExposed.getFeralAttributes().isBreastsPresent())) {
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
		if(!areasExposed.get(characterBeingExposed).contains(CoverableArea.BREASTS_CROTCH) && characterBeingExposed.hasBreastsCrotch() && isAnyCharacterAbleToSeeArea(characterBeingExposed, charactersReacting, InventorySlot.STOMACH)) {
			if(characterBeingExposed.isCoverableAreaVisible(CoverableArea.BREASTS_CROTCH)) {
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

	
	private String calculateWetAreas(boolean onSexInit) {
		StringBuilder wetSB = new StringBuilder();
		
		for(GameCharacter character : Main.sex.getAllParticipants()) {
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
		for(GameCharacter characterPenetrating : Main.sex.getAllParticipants()) {
			for(GameCharacter characterPenetrated : Main.sex.getAllParticipants()) {
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
	

	private String transferLubricationNoAppend(GameCharacter character, SexAreaInterface characterArea, GameCharacter targetCharacter, SexAreaInterface targetArea) {
		List<String> lubricationTransferred = new ArrayList<>();
		boolean lastLubricationPlural = false;
		StringBuilder lubeSB = new StringBuilder();
		List<GameCharacter> lubricationCharacters = Main.sex.getAllParticipants();
		lubricationCharacters.add(null);
		
		for(GameCharacter lubricantProvider : lubricationCharacters) {
			for(LubricationType lt : wetAreas.get(character).get(characterArea).get(lubricantProvider)) {
				if(!wetAreas.get(targetCharacter).get(targetArea).get(lubricantProvider).contains(lt)
						// Cannot lubricate with self cum or precum via penis through a condom:
						&& ((lt!=LubricationType.PRECUM && lt!=LubricationType.CUM) || characterArea!=SexAreaPenetration.PENIS || !character.isWearingCondom() || !character.equals(lubricantProvider))) {
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
		
		for(GameCharacter lubricantProvider : lubricationCharacters) {
			for(LubricationType lt : wetAreas.get(targetCharacter).get(targetArea).get(lubricantProvider)) {
				if(!wetAreas.get(character).get(characterArea).get(lubricantProvider).contains(lt)
						// Cannot lubricate with self cum or precum via penis through a condom:
						&& ((lt!=LubricationType.PRECUM && lt!=LubricationType.CUM) || targetArea!=SexAreaPenetration.PENIS || !targetCharacter.isWearingCondom() || !targetCharacter.equals(lubricantProvider))) {
					wetAreas.get(character).get(characterArea).get(lubricantProvider).add(lt);
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
						+(character.isPlayer()?"your ":character.getName("the")+"'s ")+characterArea.getName(character)+"."));
		}
		
		return lubeSB.toString();
	}
	
	public void transferLubrication(GameCharacter character, SexAreaInterface characterArea, GameCharacter targetCharacter, SexAreaInterface targetArea) {
		sexSB.append(transferLubricationNoAppend(character, characterArea, targetCharacter, targetArea));
	}
	
	private String getLubricationDescription(GameCharacter character, SexAreaInterface area) {
		List<String> lubes = new ArrayList<>();
		List<GameCharacter> lubricationCharacters = Main.sex.getAllParticipants();
		lubricationCharacters.add(null);
		
		for(GameCharacter lubricantProvider : lubricationCharacters) {
			for(LubricationType lube : wetAreas.get(character).get(area).get(lubricantProvider)) {
				lubes.add((lubricantProvider==null
							?""
							:UtilText.parse(lubricantProvider, character.equals(lubricantProvider)?"[npc.her] ":"[npc.namePos] "))
						+lube.getName(lubricantProvider));
			}
		}
		
		if(lubes.isEmpty()) {
			return "";
		}
		
		StringBuilder description = new StringBuilder(UtilText.parse(character, "[npc.NamePos] ")+area.getName(character) +" "+(area.isPlural()?"are":"is")+" lubricated with ");
		description.append(Util.stringsToStringList(lubes, false)+".");
		
		return description.toString();
	}
	
	public void clearLubrication(GameCharacter character, SexAreaInterface sexArea) {
		for(Entry<GameCharacter, Set<LubricationType>> entry : wetAreas.get(character).get(sexArea).entrySet()) {
			try {
				entry.getValue().clear();
			} catch(Exception ex) {
			}
		}
	}
	
	private String addLubricationNoAppend(GameCharacter characterGettingLubricated, SexAreaInterface sexArea, GameCharacter characterProvidingLubrication, LubricationType lubrication) {
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
	
	public void addLubrication(GameCharacter character, SexAreaInterface sexArea, GameCharacter characterProvidingLubrication, LubricationType lubrication) {
		addLubrication(character, sexArea, characterProvidingLubrication, lubrication, true);
	}
	
	public void addLubrication(GameCharacter characterGettingLubricated, SexAreaInterface sexArea, GameCharacter characterProvidingLubrication, LubricationType lubrication, boolean appendTextToSex) {
		String description = addLubricationNoAppend(characterGettingLubricated, sexArea, characterProvidingLubrication, lubrication);
		if(appendTextToSex) {
			sexSB.append(description);
		}
	}
	
	public void applyOngoingAction(GameCharacter characterPerformingAction, SexAreaInterface performerArea, GameCharacter characterTargeted, SexAreaInterface targetedArea, boolean freeUpSexAreas) {
		// Free up orifice and penetrator:
		if(freeUpSexAreas) {
			if(performerArea.isPenetration()) {
				if(Main.sex.getPenetrationTypeFreeCount(characterPerformingAction, (SexAreaPenetration)performerArea)==0) {
					Main.sex.stopFirstOngoingAction(characterPerformingAction, performerArea);
				}
				if(performerArea==SexAreaPenetration.TONGUE) {
					if(!Main.sex.isOrificeFree(characterPerformingAction, SexAreaOrifice.MOUTH)) {
						Main.sex.stopFirstOngoingAction(characterPerformingAction, performerArea);
					}
				}
			} else {
				Main.sex.stopFirstOngoingAction(characterPerformingAction, performerArea);
				if(performerArea==SexAreaOrifice.MOUTH) {
					if(Main.sex.getPenetrationTypeFreeCount(characterPerformingAction, SexAreaPenetration.TONGUE)==0) {
						Main.sex.stopFirstOngoingAction(characterPerformingAction, performerArea);
					}
				}
			}
			
			if(targetedArea.isPenetration()) {
				if(Main.sex.getPenetrationTypeFreeCount(characterTargeted, (SexAreaPenetration)targetedArea)==0) {
					Main.sex.stopFirstOngoingAction(characterTargeted, targetedArea);
				}
				if(targetedArea==SexAreaPenetration.TONGUE) {
					if(!Main.sex.isOrificeFree(characterTargeted, SexAreaOrifice.MOUTH)) {
						Main.sex.stopFirstOngoingAction(characterTargeted, targetedArea);
					}
				}
			} else {
				Main.sex.stopFirstOngoingAction(characterTargeted, targetedArea);
				if(targetedArea==SexAreaOrifice.MOUTH) {
					if(Main.sex.getPenetrationTypeFreeCount(characterTargeted, SexAreaPenetration.TONGUE)==0) {
						Main.sex.stopFirstOngoingAction(characterTargeted, targetedArea);
					}
				}
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
			
			characterPerformingAction.incrementSexCount(characterTargeted, relatedSexTypePerformer);
			characterTargeted.incrementSexCount(characterPerformingAction, relatedSexTypeTargeted);
			
			if(performerArea==SexAreaPenetration.TONGUE && targetedArea==SexAreaOrifice.MOUTH
					&& !Main.sex.getOngoingCharactersUsingAreas(characterTargeted, performerArea, targetedArea).contains(characterPerformingAction)) {
				applyOngoingAction(characterTargeted, performerArea, characterPerformingAction, targetedArea, false);
			}
			
		} else {
			System.err.println("Warning! Main.sex.applyPenetration() is finding 'characterPenetrated' or 'characterPenetrating' to be null!!!");
		}
	}
	
	public void stopAllOngoingActions(GameCharacter characterPerformingAction, SexAreaInterface area) {
		for(Entry<GameCharacter, Set<SexAreaInterface>> e : new HashSet<>(Main.sex.ongoingActionsMap.get(characterPerformingAction).get(area).entrySet())) {
			for(SexAreaInterface sa : e.getValue()) {
				stopOngoingAction(characterPerformingAction, area, e.getKey(), sa, true);
			}
		}
		if(area==SexAreaPenetration.TONGUE) {
			for(Entry<GameCharacter, Set<SexAreaInterface>> e : new HashSet<>(Main.sex.ongoingActionsMap.get(characterPerformingAction).get(SexAreaOrifice.MOUTH).entrySet())) {
				for(SexAreaInterface sa : e.getValue()) {
					stopOngoingAction(characterPerformingAction, SexAreaOrifice.MOUTH, e.getKey(), sa, true);
				}
			}
		}
		if(area==SexAreaOrifice.MOUTH) {
			for(Entry<GameCharacter, Set<SexAreaInterface>> e : new HashSet<>(Main.sex.ongoingActionsMap.get(characterPerformingAction).get(SexAreaPenetration.TONGUE).entrySet())) {
				for(SexAreaInterface sa : e.getValue()) {
					stopOngoingAction(characterPerformingAction, SexAreaPenetration.TONGUE, e.getKey(), sa, true);
				}
			}
		}
	}

	public void stopFirstOngoingAction(GameCharacter characterPerformingAction, SexAreaInterface area) {
		outer:
		for(Entry<GameCharacter, Set<SexAreaInterface>> e : new HashSet<>(Main.sex.ongoingActionsMap.get(characterPerformingAction).get(area).entrySet())) {
			for(SexAreaInterface sa : e.getValue()) {
				stopOngoingAction(characterPerformingAction, area, e.getKey(), sa, true);
				break outer;
			}
		}
		
		if(area==SexAreaPenetration.TONGUE) {
			for(Entry<GameCharacter, Set<SexAreaInterface>> e : new HashSet<>(Main.sex.ongoingActionsMap.get(characterPerformingAction).get(SexAreaOrifice.MOUTH).entrySet())) {
				for(SexAreaInterface sa : e.getValue()) {
					stopOngoingAction(characterPerformingAction, SexAreaOrifice.MOUTH, e.getKey(), sa, true);
				}
			}
		}
		if(area==SexAreaOrifice.MOUTH) {
			for(Entry<GameCharacter, Set<SexAreaInterface>> e : new HashSet<>(Main.sex.ongoingActionsMap.get(characterPerformingAction).get(SexAreaPenetration.TONGUE).entrySet())) {
				for(SexAreaInterface sa : e.getValue()) {
					stopOngoingAction(characterPerformingAction, SexAreaPenetration.TONGUE, e.getKey(), sa, true);
				}
			}
		}
	}
	
	public void stopAllOngoingActions(GameCharacter characterPerformingAction, GameCharacter characterTargeted) {
		for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
			ongoingActionsMap.get(characterPerformingAction).get(orifice).remove(characterTargeted);
			ongoingActionsMap.get(characterTargeted).get(orifice).remove(characterPerformingAction);
		}
		for(SexAreaPenetration penetration : SexAreaPenetration.values()) {
			ongoingActionsMap.get(characterPerformingAction).get(penetration).remove(characterTargeted);
			ongoingActionsMap.get(characterTargeted).get(penetration).remove(characterPerformingAction);
		}
	}
	
	public void stopAllOngoingActions(GameCharacter characterPerformingAction, SexAreaInterface performerArea, GameCharacter characterTargeted, boolean appendRemovalText) {
		if(ongoingActionsMap.get(characterPerformingAction).get(performerArea).containsKey(characterTargeted)) {
			List<SexAreaInterface> areasToClear = new ArrayList<>(ongoingActionsMap.get(characterPerformingAction).get(performerArea).get(characterTargeted));
			
			for(SexAreaInterface targetedArea : areasToClear) {
				if(ongoingActionsMap.get(characterPerformingAction).get(performerArea).get(characterTargeted).remove(targetedArea)) {
					ongoingActionsMap.get(characterTargeted).get(targetedArea).get(characterPerformingAction).remove(performerArea);
					if(appendRemovalText && characterTargeted!=null) {
						sexSB.append(formatStopPenetration(characterTargeted.getStopPenetrationDescription(characterPerformingAction, performerArea, characterTargeted, targetedArea)));
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
	public String stopOngoingAction(GameCharacter characterPerformingAction, SexAreaInterface performerArea, GameCharacter characterTargeted, SexAreaInterface targetedArea) {
		return stopOngoingAction(characterPerformingAction, performerArea, characterTargeted, targetedArea, true);
	}
	
	/**
	 * @return The description of this action being stopped.
	 */
	public String stopOngoingAction(GameCharacter characterPerformingAction, SexAreaInterface performerArea, GameCharacter characterTargeted, SexAreaInterface targetedArea, boolean appendRemovalText) {
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
		if(performerArea==SexAreaPenetration.TONGUE && targetedArea==SexAreaOrifice.MOUTH
				&& Main.sex.getOngoingCharactersUsingAreas(characterTargeted, performerArea, targetedArea).contains(characterPerformingAction)) {
			stopOngoingAction(characterTargeted, performerArea, characterPerformingAction, targetedArea, false);
		}
		return removalText;
	}

	private boolean displayOngoingPenetrationEffects = false;
	
	public String applyPenetrationEffects(SexActionInterface sexAction, GameCharacter characterPenetrating, SexAreaPenetration penetrationType, GameCharacter characterPenetrated, SexAreaInterface orifice) {
		StringBuilder penetrationSB = new StringBuilder();
//		System.out.println(Main.sex.getTurn()+" : "+characterPenetrating.getName());

		boolean initialPenetration = initialPenetrations.get(characterPenetrated).contains(orifice);
		boolean knotted = Objects.equals(Main.sex.getCharacterKnotting(characterPenetrating), characterPenetrated);
		
		if(orifice.isOrifice()) {
			SexAreaOrifice actualOrifice = (SexAreaOrifice) orifice;
			
			SexType relatedSexTypeForCharacterPenetrating = new SexType(SexParticipantType.NORMAL, penetrationType, orifice);
			SexType relatedSexTypeForCharacterPenetrated = new SexType(SexParticipantType.NORMAL, orifice, penetrationType);
			
			String penileVirginityLoss = "";
			
			if(penetrationType == SexAreaPenetration.PENIS) {
				if(characterPenetrating.isPenisVirgin()
						&& characterPenetrating.hasPenisIgnoreDildo()
						&& actualOrifice.isInternalOrifice()) {
					penileVirginityLoss = characterPenetrating.getVirginityLossPenetrationDescription(characterPenetrated, SexAreaPenetration.PENIS, actualOrifice);
					if(characterPenetrated.hasFetish(Fetish.FETISH_DEFLOWERING)) {
						characterPenetrated.incrementExperience(AbstractFetish.getExperienceGainFromTakingOtherVirginity(characterPenetrated), true);
					}
					characterPenetrated.incrementFetishExperience(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_DEFLOWERING.getExperienceGainFromSexAction());
					characterPenetrating.setVirginityLoss(relatedSexTypeForCharacterPenetrating, characterPenetrated, characterPenetrated.getLostVirginityDescriptor());
					characterPenetrating.setPenisVirgin(false);
				}
			}
			
			// Ingesting fluids in this orifice:
			if(penetrationType==SexAreaPenetration.TONGUE && actualOrifice!=SexAreaOrifice.MOUTH) {
				if(characterPenetrating==Main.sex.getCharacterPerformingAction()
					&& !characterPenetrated.getFluidsStoredInOrifice(actualOrifice).isEmpty()) {
					List<FluidStored> fluids = characterPenetrated.drainTotalFluidsStored(actualOrifice, 25);
					for(FluidStored fluid : fluids) {
						try {
							GameCharacter fluidCharacter = fluid.getFluidCharacter();
							penetrationSB.append(
									"<p style='text-align:center; margin-bottom:0; padding-bottom:0; color:"+PresetColour.BASE_ORANGE.toWebHexString()+";'><i>"
										+ UtilText.parse(characterPenetrating, characterPenetrated, "[npc.Name] [npc.verb(find)] [npc.herself] swallowing down some fluids previously deposited in [npc2.namePos] "+orifice.getName(characterPenetrated)+"!")
									+ "</i></p>"
									+ characterPenetrating.ingestFluid(
											fluidCharacter,
											fluid.getFluid(),
											SexAreaOrifice.MOUTH,
											fluid.getMillilitres()));
						} catch(Exception ex) {
							if(fluid.isCum()) {
								penetrationSB.append(
										"<p style='text-align:center; margin-bottom:0; padding-bottom:0; color:"+PresetColour.BASE_ORANGE.toWebHexString()+";'><i>"
											+ UtilText.parse(characterPenetrating, characterPenetrated, "[npc.Name] [npc.verb(find)] [npc.herself] swallowing down some fluids previously deposited in [npc2.namePos] "+orifice.getName(characterPenetrated)+"!")
										+ "</i></p>"
										+ characterPenetrating.ingestFluid(
												null,
												fluid.getCumSubspecies(),
												fluid.getCumHalfDemonSubspecies(),
												fluid.getFluid(),
												SexAreaOrifice.MOUTH,
												fluid.getMillilitres()));
							} else {
								penetrationSB.append(
										"<p style='text-align:center; margin-bottom:0; padding-bottom:0; color:"+PresetColour.BASE_ORANGE.toWebHexString()+";'><i>"
											+ UtilText.parse(characterPenetrating, characterPenetrated, "[npc.Name] [npc.verb(find)] [npc.herself] swallowing down some fluids previously deposited in [npc2.namePos] "+orifice.getName(characterPenetrated)+"!")
										+ "</i></p>"
										+ characterPenetrating.ingestFluid(
												fluid,
												SexAreaOrifice.MOUTH,
												fluid.getMillilitres()));
							}
						}
					}
				}
			}
			
			if(actualOrifice == SexAreaOrifice.ASS) {
				if(initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.ASS)) {
					penetrationSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					
					initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.ASS);
					
				} else if(displayOngoingPenetrationEffects) {
					penetrationSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
				}
					
			} else if(actualOrifice == SexAreaOrifice.BREAST) {
				if(initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.BREAST)) {
					penetrationSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					
					initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.BREAST);
					
				} else if(displayOngoingPenetrationEffects) {
					penetrationSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
				}
					
			} else if(actualOrifice == SexAreaOrifice.ANUS) {
				if(initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.ANUS)) {
					if(characterPenetrated.isAssVirgin() && penetrationType.isTakesVirginity()) {
						penetrationSB.append(characterPenetrated.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, SexAreaOrifice.ANUS));
						if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
							characterPenetrating.incrementExperience(AbstractFetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating), true);
						}
						characterPenetrating.incrementFetishExperience(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_DEFLOWERING.getExperienceGainFromSexAction());
						characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating, characterPenetrating.getLostVirginityDescriptor());
						characterPenetrated.setAssVirgin(false);
					}
					
					penetrationSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					penetrationSB.append(characterPenetrating.getPenetrationDepthDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice));
					
					initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.ANUS);
					
				} else {
					if(displayOngoingPenetrationEffects) {
						penetrationSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					}
					penetrationSB.append(characterPenetrating.getPenetrationDepthDescription(knotted, characterPenetrating, penetrationType, characterPenetrated, actualOrifice));
				}
					
			} else if(actualOrifice == SexAreaOrifice.VAGINA) {
				if(initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.VAGINA)) {
					if(penetrationType.isTakesVirginity()) {
						if(characterPenetrated.isVaginaVirgin()) {
							penetrationSB.append(characterPenetrated.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, SexAreaOrifice.VAGINA));
							if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
								characterPenetrating.incrementExperience(AbstractFetish.getExperienceGainFromTakingVaginalVirginity(characterPenetrating), true);
							}
							characterPenetrating.incrementFetishExperience(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_DEFLOWERING.getExperienceGainFromSexAction());
							characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating, characterPenetrating.getLostVirginityDescriptor());
							characterPenetrated.setVaginaVirgin(false);
							
						} else if(characterPenetrated.hasHymen()){
							characterPenetrated.setHymen(false);
							penetrationSB.append(UtilText.formatVirginityLoss(UtilText.parse(characterPenetrated, "[npc.NamePos] hymen was torn!")));
						}
					}
					
					penetrationSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					penetrationSB.append(characterPenetrating.getPenetrationDepthDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice));
					
					initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.VAGINA);
					
				} else {
					if(displayOngoingPenetrationEffects) {
						penetrationSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					}
					penetrationSB.append(characterPenetrating.getPenetrationDepthDescription(knotted, characterPenetrating, penetrationType, characterPenetrated, actualOrifice));
				}
				
				// Girlcum ingestion:
				if((characterPenetrating==Main.sex.getCharacterPerformingAction()
							|| (sexAction.getPerformingCharacterOrifices().contains(SexAreaOrifice.VAGINA)
									&& sexAction.getTargetedCharacterPenetrations().contains(SexAreaPenetration.TONGUE)
									&& characterPenetrating==Main.sex.getCharacterTargetedForSexAction(sexAction)))
						&& penetrationType==SexAreaPenetration.TONGUE
						&& characterPenetrated.getArousal() >= characterPenetrated.getVaginaWetness().getArousalNeededToGetVaginaWet()) {
					penetrationSB.append(
							characterPenetrating.ingestFluid(
								characterPenetrated,
								characterPenetrated.getGirlcum(),
								SexAreaOrifice.MOUTH,
								1 + characterPenetrated.getVaginaWetness().getValue()*2));
				}
				
			} else if(actualOrifice == SexAreaOrifice.NIPPLE) {
				if(initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.NIPPLE)) {
					if(characterPenetrated.isNippleVirgin() && penetrationType.isTakesVirginity()) {
						penetrationSB.append(characterPenetrated.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, SexAreaOrifice.NIPPLE));
						if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
							characterPenetrating.incrementExperience(AbstractFetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating), true);
						}
						characterPenetrating.incrementFetishExperience(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_DEFLOWERING.getExperienceGainFromSexAction());
						characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating, characterPenetrating.getLostVirginityDescriptor());
						characterPenetrated.setNippleVirgin(false);
					}
					
					penetrationSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					penetrationSB.append(characterPenetrating.getPenetrationDepthDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice));
					
					initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.NIPPLE);
					
				} else {
					if(displayOngoingPenetrationEffects) {
						penetrationSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					}
					penetrationSB.append(characterPenetrating.getPenetrationDepthDescription(knotted, characterPenetrating, penetrationType, characterPenetrated, actualOrifice));
				}
				if(penetrationType==SexAreaPenetration.TONGUE
						&& characterPenetrating.equals(Main.sex.getCharacterPerformingAction())
						&& (!sexAction.getPerformingCharacterAreas().contains(SexAreaPenetration.TONGUE) || !sexAction.getTargetedCharacterAreas().contains(SexAreaOrifice.NIPPLE))
						&& (!sexAction.getTargetedCharacterAreas().contains(SexAreaPenetration.TONGUE) || !sexAction.getPerformingCharacterAreas().contains(SexAreaOrifice.NIPPLE))
						&& characterPenetrated.getBreastRawStoredMilkValue()>0) {
					
					float suckleAmount = Math.max(5, Math.min(25, characterPenetrated.getBreastRawMilkStorageValue()/20));
					
					if(suckleAmount>characterPenetrated.getBreastRawStoredMilkValue()) {
						suckleAmount = characterPenetrated.getBreastRawStoredMilkValue();
					}
					
					penetrationSB.append(
						characterPenetrating.ingestFluid(
							characterPenetrated,
							characterPenetrated.getMilk(),
							SexAreaOrifice.MOUTH,
							suckleAmount));
					characterPenetrated.incrementBreastStoredMilk(-suckleAmount);
				}
					
			} else if(actualOrifice == SexAreaOrifice.NIPPLE_CROTCH) {
				if(initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.NIPPLE_CROTCH)) {
					if(characterPenetrated.isNippleCrotchVirgin() && penetrationType.isTakesVirginity()) {
						penetrationSB.append(characterPenetrated.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, SexAreaOrifice.NIPPLE_CROTCH));
						if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
							characterPenetrating.incrementExperience(AbstractFetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating), true);
						}
						characterPenetrating.incrementFetishExperience(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_DEFLOWERING.getExperienceGainFromSexAction());
						characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating, characterPenetrating.getLostVirginityDescriptor());
						characterPenetrated.setNippleCrotchVirgin(false);
					}
					
					penetrationSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					penetrationSB.append(characterPenetrating.getPenetrationDepthDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice));
					
					initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.NIPPLE_CROTCH);
					
				} else {
					if(displayOngoingPenetrationEffects) {
						penetrationSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					}
					penetrationSB.append(characterPenetrating.getPenetrationDepthDescription(knotted, characterPenetrating, penetrationType, characterPenetrated, actualOrifice));
				}
				
			} else if(actualOrifice == SexAreaOrifice.URETHRA_PENIS) {
				if(initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.URETHRA_PENIS)) {
					if(characterPenetrated.isUrethraVirgin() && penetrationType.isTakesVirginity()) {
						penetrationSB.append(characterPenetrated.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, SexAreaOrifice.URETHRA_PENIS));
						if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
							characterPenetrating.incrementExperience(AbstractFetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating), true);
						}
						characterPenetrating.incrementFetishExperience(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_DEFLOWERING.getExperienceGainFromSexAction());
						characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating, characterPenetrating.getLostVirginityDescriptor());
						characterPenetrated.setUrethraVirgin(false);
					}
					
					penetrationSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					penetrationSB.append(characterPenetrating.getPenetrationDepthDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice));
					
					initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.URETHRA_PENIS);
					
				} else {
					if(displayOngoingPenetrationEffects) {
						penetrationSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					}
					penetrationSB.append(characterPenetrating.getPenetrationDepthDescription(knotted, characterPenetrating, penetrationType, characterPenetrated, actualOrifice));
				}
				
			} else if(actualOrifice == SexAreaOrifice.URETHRA_VAGINA) {
				if(initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.URETHRA_VAGINA)) {
					if(characterPenetrated.isVaginaUrethraVirgin() && penetrationType.isTakesVirginity()) {
						penetrationSB.append(characterPenetrated.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, SexAreaOrifice.URETHRA_VAGINA));
						if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
							characterPenetrating.incrementExperience(AbstractFetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating), true);
						}
						characterPenetrating.incrementFetishExperience(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_DEFLOWERING.getExperienceGainFromSexAction());
						characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating, characterPenetrating.getLostVirginityDescriptor());
						characterPenetrated.setVaginaUrethraVirgin(false);
					}
					
					penetrationSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					penetrationSB.append(characterPenetrating.getPenetrationDepthDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice));
					
					initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.URETHRA_VAGINA);
					
				} else {
					if(displayOngoingPenetrationEffects) {
						penetrationSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					}
					penetrationSB.append(characterPenetrating.getPenetrationDepthDescription(knotted, characterPenetrating, penetrationType, characterPenetrated, actualOrifice));
				}
			
			} else if(actualOrifice == SexAreaOrifice.MOUTH) {
				if(initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.MOUTH)) {
					if(characterPenetrated.isFaceVirgin() && penetrationType.isTakesVirginity()) {
						penetrationSB.append(characterPenetrated.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, SexAreaOrifice.MOUTH));
						if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
							characterPenetrating.incrementExperience(AbstractFetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating), true);
						}
						characterPenetrating.incrementFetishExperience(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_DEFLOWERING.getExperienceGainFromSexAction());
						characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating, characterPenetrating.getLostVirginityDescriptor());
						characterPenetrated.setFaceVirgin(false);
					}
					
					penetrationSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					penetrationSB.append(characterPenetrating.getPenetrationDepthDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice));
					
					initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.MOUTH);
					
				} else {
					if(displayOngoingPenetrationEffects) {
						penetrationSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					}
					penetrationSB.append(characterPenetrating.getPenetrationDepthDescription(knotted, characterPenetrating, penetrationType, characterPenetrated, actualOrifice));
				}
				
			} else if(actualOrifice == SexAreaOrifice.SPINNERET) {
				if(initialPenetrations.get(characterPenetrated).contains(SexAreaOrifice.SPINNERET)) {
					if(characterPenetrated.isSpinneretVirgin() && penetrationType.isTakesVirginity()) {
						penetrationSB.append(characterPenetrated.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, SexAreaOrifice.SPINNERET));
						if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
							characterPenetrating.incrementExperience(AbstractFetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating), true);
						}
						characterPenetrating.incrementFetishExperience(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_DEFLOWERING.getExperienceGainFromSexAction());
						characterPenetrated.setVirginityLoss(relatedSexTypeForCharacterPenetrated, characterPenetrating, characterPenetrating.getLostVirginityDescriptor());
						characterPenetrated.setSpinneretVirgin(false);
					}
					
					penetrationSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					penetrationSB.append(characterPenetrating.getPenetrationDepthDescription(true, characterPenetrating, penetrationType, characterPenetrated, actualOrifice));
					
					initialPenetrations.get(characterPenetrated).remove(SexAreaOrifice.SPINNERET);
					
				} else {
					if(displayOngoingPenetrationEffects) {
						penetrationSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, actualOrifice)));
					}
					penetrationSB.append(characterPenetrating.getPenetrationDepthDescription(knotted, characterPenetrating, penetrationType, characterPenetrated, actualOrifice));
				}
			}
			
			penetrationSB.append(penileVirginityLoss);
			
		} else { // Pen/pen stuff:
			if(penetrationType == SexAreaPenetration.FINGER && orifice == SexAreaPenetration.PENIS) {
				if(initialPenetrations.get(characterPenetrated).contains(SexAreaPenetration.PENIS)) {
					penetrationSB.append(formatInitialPenetration(characterPenetrating.getPenetrationDescription(true, characterPenetrating, penetrationType, characterPenetrated, orifice)));
					
					initialPenetrations.get(characterPenetrated).remove(SexAreaPenetration.PENIS);
					
				} else if(displayOngoingPenetrationEffects) {
					penetrationSB.append(formatPenetration(characterPenetrating.getPenetrationDescription(false, characterPenetrating, penetrationType, characterPenetrated, orifice)));
				}
				
			} else if(penetrationType == SexAreaPenetration.FOOT && orifice == SexAreaPenetration.PENIS) {
				if(initialPenetrations.get(characterPenetrated).contains(SexAreaPenetration.PENIS)) {
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
		
		// Stretching effects (will only stretch from penises, tails, tentacles, and clits):
		if(penetrationType == SexAreaPenetration.PENIS
				 || penetrationType == SexAreaPenetration.TAIL
				 || penetrationType == SexAreaPenetration.TENTACLE) {
			boolean lubed = false;
			List<GameCharacter> lubricationCharacters = Main.sex.getAllParticipants();
			lubricationCharacters.add(null);
			for(GameCharacter lubricantProvider : lubricationCharacters) {
				if(!wetAreas.get(characterPenetrated).get(orifice).get(lubricantProvider).isEmpty()) {
					lubed = true;
					break;
				}
			}
			
			float minimumStretchPercentage = 0.05f;
			
			knotted = false;
			float totalPenetratingDiameter = 0;
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(characterPenetrated, orifice).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
							case FOOT:
								break;
							case CLIT:
								totalPenetratingDiameter += entry.getKey().getClitorisDiameter();
//								if(Objects.equals(Main.sex.getCharacterKnotting(entry.getKey()), characterPenetrated)) {
//									totalPenetratingDiameter += entry.getKey().getClitorisDiameter();
//									knotted = true;
//								}
								break;
							case TENTACLE:
								if(orifice.isOrifice()) {
									totalPenetratingDiameter += entry.getKey().getTentacleDiameter(
											entry.getKey().getTentacleLength(false) - entry.getKey().getPenetrationLengthInserted(SexAreaPenetration.TENTACLE, characterPenetrated, (SexAreaOrifice) orifice));
								} else {
									totalPenetratingDiameter += entry.getKey().getTentacleBaseDiameter();
								}
								break;
							case TONGUE:
								break;
							case PENIS:
								totalPenetratingDiameter += entry.getKey().getPenisDiameter();
								if(Objects.equals(Main.sex.getCharacterKnotting(entry.getKey()), characterPenetrated)) {
									totalPenetratingDiameter += entry.getKey().getPenisDiameter();
									knotted = true;
								}
								break;
							case TAIL:
								if(entry.getKey().getLegConfiguration()==LegConfiguration.TAIL_LONG) {
									if(orifice.isOrifice()) {
										totalPenetratingDiameter += entry.getKey().getLegTailDiameter(
												entry.getKey().getLegTailLength(false) - entry.getKey().getPenetrationLengthInserted(SexAreaPenetration.TAIL, characterPenetrated, (SexAreaOrifice) orifice));
									} else {
										totalPenetratingDiameter += entry.getKey().getLegTailBaseDiameter();
									}
									
								} else {
									if(orifice.isOrifice()) {
										totalPenetratingDiameter += entry.getKey().getTailDiameter(
												entry.getKey().getTailLength(false) - entry.getKey().getPenetrationLengthInserted(SexAreaPenetration.TAIL, characterPenetrated, (SexAreaOrifice) orifice));
									} else {
										totalPenetratingDiameter += entry.getKey().getTailBaseDiameter();
									}
								}
								break;
						}
					}
				}
			}
			
			areasCurrentlyStretching.get(characterPenetrated).clear();
			if(orifice == SexAreaOrifice.ANUS){
				if(Capacity.isPenetrationDiameterTooBig(
						characterPenetrated.getAssElasticity(), characterPenetrated.getAssStretchedCapacity(), totalPenetratingDiameter, lubed)){
					penetrationSB.append(UtilText.formatStretching(characterPenetrated.getStretchingDescription((initialPenetration || knotted), characterPenetrating, penetrationType, SexAreaOrifice.ANUS, false)));

					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementAssStretchedCapacity(
							Math.max(
									totalPenetratingDiameter*minimumStretchPercentage,
									(totalPenetratingDiameter-characterPenetrated.getAssStretchedCapacity())*characterPenetrated.getAssElasticity().getStretchModifier()));
					if(characterPenetrated.getAssStretchedCapacity()>totalPenetratingDiameter) {
						characterPenetrated.setAssStretchedCapacity(totalPenetratingDiameter);
					}
					
					areasCurrentlyStretching.get(characterPenetrated).add(SexAreaOrifice.ANUS);

					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenetrationDiameterTooBig(
							characterPenetrated.getAssElasticity(), characterPenetrated.getAssStretchedCapacity(), totalPenetratingDiameter, lubed)) {
						penetrationSB.append(UtilText.formatStretching(characterPenetrated.getStretchingFinishedDescription(SexAreaOrifice.ANUS)));
						areasCurrentlyStretching.get(characterPenetrated).remove(SexAreaOrifice.ANUS);
					}

					areasStretched.get(characterPenetrated).add(SexAreaOrifice.ANUS);

				} else if(Capacity.isPenetrationDiameterTooSmall(characterPenetrated.getAssOrificeModifiers(), characterPenetrated.getAssStretchedCapacity(), totalPenetratingDiameter)){
					penetrationSB.append(characterPenetrated.getTooLooseDescription(SexAreaOrifice.ANUS));
					areasTooLoose.get(characterPenetrated).add(SexAreaOrifice.ANUS);
				}

			} else if(orifice == SexAreaOrifice.VAGINA){
				if(Capacity.isPenetrationDiameterTooBig(
						characterPenetrated.getVaginaElasticity(), characterPenetrated.getVaginaStretchedCapacity(), totalPenetratingDiameter, lubed)){
					penetrationSB.append(UtilText.formatStretching(characterPenetrated.getStretchingDescription((initialPenetration || knotted), characterPenetrating, penetrationType, SexAreaOrifice.VAGINA, false)));
					
					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementVaginaStretchedCapacity(
							Math.max(
									totalPenetratingDiameter*minimumStretchPercentage,
									(totalPenetratingDiameter-characterPenetrated.getVaginaStretchedCapacity())*characterPenetrated.getVaginaElasticity().getStretchModifier()));
					
//					System.out.println(characterPenetrated.getName()+": "+characterPenetrated.getVaginaStretchedCapacity()+" | "+penisStretchSize);
					if(characterPenetrated.getVaginaStretchedCapacity()>totalPenetratingDiameter) {
						characterPenetrated.setVaginaStretchedCapacity(totalPenetratingDiameter);
					}
					
					areasCurrentlyStretching.get(characterPenetrated).add(SexAreaOrifice.VAGINA);
					
					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenetrationDiameterTooBig(
							characterPenetrated.getVaginaElasticity(), characterPenetrated.getVaginaStretchedCapacity(), totalPenetratingDiameter, lubed)) {
						penetrationSB.append(UtilText.formatStretching(characterPenetrated.getStretchingFinishedDescription(SexAreaOrifice.VAGINA)));
						areasCurrentlyStretching.get(characterPenetrated).remove(SexAreaOrifice.VAGINA);
					}

					areasStretched.get(characterPenetrated).add(SexAreaOrifice.VAGINA);

				} else if(Capacity.isPenetrationDiameterTooSmall(characterPenetrated.getVaginaOrificeModifiers(), characterPenetrated.getVaginaStretchedCapacity(), totalPenetratingDiameter)){
					penetrationSB.append(characterPenetrated.getTooLooseDescription(SexAreaOrifice.VAGINA));
					areasTooLoose.get(characterPenetrated).add(SexAreaOrifice.VAGINA);
				}

			} else if(orifice == SexAreaOrifice.NIPPLE){
				if(Capacity.isPenetrationDiameterTooBig(
						characterPenetrated.getNippleElasticity(), characterPenetrated.getNippleStretchedCapacity(), totalPenetratingDiameter, lubed)){
					penetrationSB.append(UtilText.formatStretching(characterPenetrated.getStretchingDescription((initialPenetration || knotted), characterPenetrating, penetrationType, SexAreaOrifice.NIPPLE, false)));

					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementNippleStretchedCapacity(
							Math.max(
									totalPenetratingDiameter*minimumStretchPercentage,
									(totalPenetratingDiameter-characterPenetrated.getNippleStretchedCapacity())*characterPenetrated.getNippleElasticity().getStretchModifier()));
					if(characterPenetrated.getNippleStretchedCapacity()>totalPenetratingDiameter) {
						characterPenetrated.setNippleStretchedCapacity(totalPenetratingDiameter);
					}
					
					areasCurrentlyStretching.get(characterPenetrated).add(SexAreaOrifice.NIPPLE);

					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenetrationDiameterTooBig(
							characterPenetrated.getNippleElasticity(), characterPenetrated.getNippleStretchedCapacity(), totalPenetratingDiameter, lubed)) {
						penetrationSB.append(UtilText.formatStretching(characterPenetrated.getStretchingFinishedDescription(SexAreaOrifice.NIPPLE)));
						areasCurrentlyStretching.get(characterPenetrated).remove(SexAreaOrifice.NIPPLE);
					}

					areasStretched.get(characterPenetrated).add(SexAreaOrifice.NIPPLE);

				} else if(Capacity.isPenetrationDiameterTooSmall(characterPenetrated.getNippleOrificeModifiers(), characterPenetrated.getNippleStretchedCapacity(), totalPenetratingDiameter)){
					penetrationSB.append(characterPenetrated.getTooLooseDescription(SexAreaOrifice.NIPPLE));
					areasTooLoose.get(characterPenetrated).add(SexAreaOrifice.NIPPLE);
				}

			} else if(orifice == SexAreaOrifice.NIPPLE_CROTCH){
				if(Capacity.isPenetrationDiameterTooBig(
						characterPenetrated.getNippleCrotchElasticity(), characterPenetrated.getNippleCrotchStretchedCapacity(), totalPenetratingDiameter, lubed)){
					penetrationSB.append(UtilText.formatStretching(characterPenetrated.getStretchingDescription((initialPenetration || knotted), characterPenetrating, penetrationType, SexAreaOrifice.NIPPLE_CROTCH, false)));

					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementNippleCrotchStretchedCapacity(
							Math.max(
									totalPenetratingDiameter*minimumStretchPercentage,
									(totalPenetratingDiameter-characterPenetrated.getNippleCrotchStretchedCapacity())*characterPenetrated.getNippleCrotchElasticity().getStretchModifier()));
					if(characterPenetrated.getNippleCrotchStretchedCapacity()>totalPenetratingDiameter) {
						characterPenetrated.setNippleCrotchStretchedCapacity(totalPenetratingDiameter);
					}
					
					areasCurrentlyStretching.get(characterPenetrated).add(SexAreaOrifice.NIPPLE_CROTCH);

					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenetrationDiameterTooBig(
							characterPenetrated.getNippleCrotchElasticity(), characterPenetrated.getNippleCrotchStretchedCapacity(), totalPenetratingDiameter, lubed)) {
						penetrationSB.append(UtilText.formatStretching(characterPenetrated.getStretchingFinishedDescription(SexAreaOrifice.NIPPLE_CROTCH)));
						areasCurrentlyStretching.get(characterPenetrated).remove(SexAreaOrifice.NIPPLE_CROTCH);
					}

					areasStretched.get(characterPenetrated).add(SexAreaOrifice.NIPPLE_CROTCH);

				} else if(Capacity.isPenetrationDiameterTooSmall(characterPenetrated.getNippleCrotchOrificeModifiers(), characterPenetrated.getNippleCrotchStretchedCapacity(), totalPenetratingDiameter)){
					penetrationSB.append(characterPenetrated.getTooLooseDescription(SexAreaOrifice.NIPPLE_CROTCH));
					areasTooLoose.get(characterPenetrated).add(SexAreaOrifice.NIPPLE_CROTCH);
				}

			} else if(orifice == SexAreaOrifice.URETHRA_PENIS){
				if(Capacity.isPenetrationDiameterTooBig(
						characterPenetrated.getUrethraElasticity(), characterPenetrated.getPenisStretchedCapacity(), totalPenetratingDiameter, lubed)){
					penetrationSB.append(UtilText.formatStretching(characterPenetrated.getStretchingDescription((initialPenetration || knotted), characterPenetrating, penetrationType, SexAreaOrifice.URETHRA_PENIS, false)));

					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementPenisStretchedCapacity(
							Math.max(
									totalPenetratingDiameter*minimumStretchPercentage,
									(totalPenetratingDiameter-characterPenetrated.getPenisStretchedCapacity())*characterPenetrated.getUrethraElasticity().getStretchModifier()));
					if(characterPenetrated.getPenisStretchedCapacity()>totalPenetratingDiameter) {
						characterPenetrated.setPenisStretchedCapacity(totalPenetratingDiameter);
					}
					
					areasCurrentlyStretching.get(characterPenetrated).add(SexAreaOrifice.URETHRA_PENIS);

					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenetrationDiameterTooBig(
							characterPenetrated.getUrethraElasticity(), characterPenetrated.getPenisStretchedCapacity(), totalPenetratingDiameter, lubed)) {
						penetrationSB.append(UtilText.formatStretching(characterPenetrated.getStretchingFinishedDescription(SexAreaOrifice.URETHRA_PENIS)));
						areasCurrentlyStretching.get(characterPenetrated).remove(SexAreaOrifice.URETHRA_PENIS);
					}

					areasStretched.get(characterPenetrated).add(SexAreaOrifice.URETHRA_PENIS);

				} else if(Capacity.isPenetrationDiameterTooSmall(characterPenetrated.getUrethraOrificeModifiers(), characterPenetrated.getPenisStretchedCapacity(), totalPenetratingDiameter)){
					penetrationSB.append(characterPenetrated.getTooLooseDescription(SexAreaOrifice.URETHRA_PENIS));
					areasTooLoose.get(characterPenetrated).add(SexAreaOrifice.URETHRA_PENIS);
				}

			} else if(orifice == SexAreaOrifice.URETHRA_VAGINA){
				if(Capacity.isPenetrationDiameterTooBig(
						characterPenetrated.getVaginaUrethraElasticity(), characterPenetrated.getVaginaUrethraStretchedCapacity(), totalPenetratingDiameter, lubed)){
					penetrationSB.append(UtilText.formatStretching(characterPenetrated.getStretchingDescription((initialPenetration || knotted), characterPenetrating, penetrationType, SexAreaOrifice.URETHRA_VAGINA, false)));

					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementVaginaUrethraStretchedCapacity(
							Math.max(
									totalPenetratingDiameter*minimumStretchPercentage,
									(totalPenetratingDiameter-characterPenetrated.getVaginaUrethraStretchedCapacity())*characterPenetrated.getVaginaUrethraElasticity().getStretchModifier()));
					if(characterPenetrated.getVaginaUrethraStretchedCapacity()>totalPenetratingDiameter) {
						characterPenetrated.setVaginaUrethraStretchedCapacity(totalPenetratingDiameter);
					}
					
					areasCurrentlyStretching.get(characterPenetrated).add(SexAreaOrifice.URETHRA_VAGINA);

					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenetrationDiameterTooBig(
							characterPenetrated.getVaginaUrethraElasticity(), characterPenetrated.getVaginaUrethraStretchedCapacity(), totalPenetratingDiameter, lubed)) {
						penetrationSB.append(UtilText.formatStretching(characterPenetrated.getStretchingFinishedDescription(SexAreaOrifice.URETHRA_VAGINA)));
						areasCurrentlyStretching.get(characterPenetrated).remove(SexAreaOrifice.URETHRA_VAGINA);
					}

					areasStretched.get(characterPenetrated).add(SexAreaOrifice.URETHRA_VAGINA);

				} else if(Capacity.isPenetrationDiameterTooSmall(characterPenetrated.getVaginaUrethraOrificeModifiers(), characterPenetrated.getVaginaUrethraStretchedCapacity(), totalPenetratingDiameter)){
					penetrationSB.append(characterPenetrated.getTooLooseDescription(SexAreaOrifice.URETHRA_VAGINA));
					areasTooLoose.get(characterPenetrated).add(SexAreaOrifice.URETHRA_VAGINA);
				}

			} else if(orifice == SexAreaOrifice.MOUTH){
				if(Capacity.isPenetrationDiameterTooBig(
						characterPenetrated.getFaceElasticity(), characterPenetrated.getFaceStretchedCapacity(), totalPenetratingDiameter, lubed)){
					penetrationSB.append(UtilText.formatStretching(characterPenetrated.getStretchingDescription((initialPenetration || knotted), characterPenetrating, penetrationType, SexAreaOrifice.MOUTH, false)));

					for(AbstractClothing clothing : new ArrayList<>(characterPenetrated.getClothingCurrentlyEquipped())) {
						if(clothing.getItemTags().contains(ItemTag.CHOKER_SNAP)) {
							if(clothing.isSealed()) {
								clothing.setSealed(false);
							}
							penetrationSB.append(UtilText.parse(characterPenetrated, characterPenetrating,
									"<p style='text-align:center;'>"
											+ "[style.italicsSex([npc2.NamePos] "+penetrationType.getName(characterPenetrating)+" bulges [npc.namePos] throat so much that [npc.her] [style.boldBad("+clothing.getName()+" snaps)]!)]"
											+ "<br/>"+characterPenetrated.addedItemToInventoryText(clothing, 1)
									+ "</p>"));
							characterPenetrated.unequipClothingIntoInventory(clothing, true, characterPenetrated);
						}
					}
					
					// Stretch out the orifice by a factor of elasticity's modifier.
					characterPenetrated.incrementFaceStretchedCapacity(
							Math.max(
									totalPenetratingDiameter*minimumStretchPercentage,
									(totalPenetratingDiameter-characterPenetrated.getFaceStretchedCapacity())*characterPenetrated.getFaceElasticity().getStretchModifier()));
					if(characterPenetrated.getFaceStretchedCapacity()>totalPenetratingDiameter) {
						characterPenetrated.setFaceStretchedCapacity(totalPenetratingDiameter);
					}
					
					areasCurrentlyStretching.get(characterPenetrated).add(SexAreaOrifice.MOUTH);

					// If just stretched out enough to be comfortable, append that description:
					if(!Capacity.isPenetrationDiameterTooBig(
							characterPenetrated.getFaceElasticity(), characterPenetrated.getFaceStretchedCapacity(), totalPenetratingDiameter, lubed)) {
						penetrationSB.append(UtilText.formatStretching(characterPenetrated.getStretchingFinishedDescription(SexAreaOrifice.MOUTH)));
						areasCurrentlyStretching.get(characterPenetrated).remove(SexAreaOrifice.MOUTH);
					}

					areasStretched.get(characterPenetrated).add(SexAreaOrifice.MOUTH);

				} else if(Capacity.isPenetrationDiameterTooSmall(characterPenetrated.getFaceOrificeModifiers(), (int)characterPenetrated.getFaceStretchedCapacity(), totalPenetratingDiameter)){
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
	public SexActionInterface manageClothingToAccessCoverableArea(GameCharacter characterManagingClothing, GameCharacter targetForManagement, CoverableArea coverableArea) {
		
		SimpleEntry<AbstractClothing, DisplacementType> clothingRemoval;
		if((coverableArea==CoverableArea.NIPPLES || coverableArea==CoverableArea.BREASTS)
				&& targetForManagement.getClothingInSlot(InventorySlot.CHEST)!=null) {
			List<AbstractClothing> zLayerSortedList = new ArrayList<>(targetForManagement.getClothingCurrentlyEquipped());
			zLayerSortedList.sort(new ClothingZLayerComparator());
			clothingRemoval = targetForManagement.getInventory().findNextClothingDisplacement(
					targetForManagement, coverableArea, targetForManagement.getClothingInSlot(InventorySlot.CHEST), DisplacementType.REMOVE_OR_EQUIP, zLayerSortedList, true);
			if(clothingRemoval.getKey().isSealed()) { // If the bra is sealed, just displace instead:
				clothingRemoval = targetForManagement.getNextClothingToRemoveForCoverableAreaAccess(coverableArea);
			}
			
		} else {
			clothingRemoval = targetForManagement.getNextClothingToRemoveForCoverableAreaAccess(coverableArea);
		}
		
		if(clothingRemoval == null || clothingRemoval.getKey() == null) {
			Main.sex.setUnequipClothingText(null,
					UtilText.parse(characterManagingClothing, targetForManagement, "[npc.Name] can't find a piece of [npc2.namePos] clothing to remove in order to access the slot '"+coverableArea+"'. (This is a bug...)"));
			System.err.println("manageClothingToAccessCoverableArea() can't find clothing - CoverableArea."+coverableArea.toString());
			return SexActionUtility.CLOTHING_REMOVAL;
		}
		
		clothingBeingRemoved = clothingRemoval.getKey();

		if(clothingRemoval.getValue() == DisplacementType.REMOVE_OR_EQUIP) {
			targetForManagement.unequipClothingIntoInventory(clothingBeingRemoved, false, characterManagingClothing);
			Main.sex.setUnequipClothingText(clothingBeingRemoved, targetForManagement.getUnequipDescription());

		} else {
			targetForManagement.isAbleToBeDisplaced(clothingBeingRemoved, clothingRemoval.getValue(), true, false, characterManagingClothing);
			Main.sex.setDisplaceClothingText(clothingBeingRemoved, targetForManagement.getDisplaceDescription());
		}

		return SexActionUtility.CLOTHING_REMOVAL;
	}

	public boolean isInForeplay(GameCharacter character) {
		return character.getArousal()<ArousalLevel.ONE_TURNED_ON.getMaximumValue() && Main.sex.getNumberOfOrgasms(character)==0
				&& Main.sex.getSexManager().isPartnerUsingForeplayActions(); //TODO remove this
	}
	
	// Getters & Setters:

	public boolean isConsensual() {
		return consensual;
	}

	public void setConsensual(boolean consensual) {
		Main.sex.consensual = consensual;
	}

	public boolean isSubHasEqualControl() {
		return subHasEqualControl;
	}

	public void setSubHasEqualControl(boolean subHasEqualControl) {
		Main.sex.subHasEqualControl = subHasEqualControl;
	}

	public void setForcedSexControl(GameCharacter character, SexControl sexControl) {
		forcedSexControlMap.put(character, sexControl);
	}
	
	public SexControl getSexControl(GameCharacter character) {
		if(forcedSexControlMap.get(character)!=null) {
			return forcedSexControlMap.get(character);
		}
		return initialSexManager.getSexControl(character);
	}
	
	public boolean isPositionChangingAllowed(GameCharacter characterWantingToChangePosition) {
		if(Main.sex.isMasturbation()) {
			return true;
		}
		if(isCharacterBannedFromPositioning(characterWantingToChangePosition)
				|| Main.sex.isCharacterForbiddenByOthersFromPositioning(characterWantingToChangePosition)
				|| Main.sex.isDom(characterWantingToChangePosition)==Main.sex.isDom(Main.sex.getTargetedPartner(characterWantingToChangePosition))) {
			return false; // Don't allow position changing if the character/target are sub/sub or dom/dom, as it can break positioning.
		}
		
		return Main.sex.getInitialSexManager().isPositionChangingAllowed(characterWantingToChangePosition);
	}
	
	public boolean isSexTypePossibleViaAvailablePositionsAndSlots(GameCharacter characterPerformingSexType, GameCharacter targetedCharacter, SexType sexType) {
		for(AbstractSexPosition position : initialSexManager.getAllowedSexPositions()) {
			for(Entry<SexSlot, Map<SexSlot, SexActionInteractions>> entry : position.getSlotTargets().entrySet()) {
				// Check for interactions between characterPerformingSexType -> targetedCharacter
				if(initialSexManager.isSlotAvailable(characterPerformingSexType, entry.getKey())) {
					for(Entry<SexSlot, SexActionInteractions> innerEntry : entry.getValue().entrySet()) {
						if(initialSexManager.isSlotAvailable(targetedCharacter, innerEntry.getKey())) {
							for(Entry<SexAreaInterface, List<SexAreaInterface>> interactions : innerEntry.getValue().getInteractions().entrySet()) {
								if(interactions.getKey()==sexType.getPerformingSexArea()) {
									if(interactions.getValue().contains(sexType.getTargetedSexArea())) {
										return true;
									}
								}
							}
						}
					}
				}
				// Check for reversed interactions between targetedCharacter -> characterPerformingSexType
				if(initialSexManager.isSlotAvailable(targetedCharacter, entry.getKey())) {
					for(Entry<SexSlot, SexActionInteractions> innerEntry : entry.getValue().entrySet()) {
						if(initialSexManager.isSlotAvailable(characterPerformingSexType, innerEntry.getKey())) {
							for(Entry<SexAreaInterface, List<SexAreaInterface>> interactions : innerEntry.getValue().getInteractions().entrySet()) {
								if(interactions.getKey()==sexType.getTargetedSexArea()) {
									if(interactions.getValue().contains(sexType.getPerformingSexArea())) {
										return true;
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	public boolean isPositionMenuChangingAllowed(GameCharacter characterWantingToChangePosition) {
		if(Main.sex.isMasturbation()) {
			return true;
		}
		if(isCharacterBannedFromPositioning(characterWantingToChangePosition)
				|| Main.sex.isCharacterForbiddenByOthersFromPositioning(characterWantingToChangePosition)) {
			return false;
		}
		
		return Main.sex.getInitialSexManager().isPositionChangingAllowed(characterWantingToChangePosition);
	}
	
	public List<SexType> getRequestsBlocked(GameCharacter character) {
		return requestsBlocked.get(character);
	}

	public void addRequestsBlocked(GameCharacter character, SexType sexTypeRequest) {
		if(!requestsBlocked.get(character).contains(sexTypeRequest)) {
			requestsBlocked.get(character).add(sexTypeRequest);
		}
	}
	
	public List<AbstractSexPosition> getPositioningRequestsBlocked(GameCharacter character) {
		return positioningRequestsBlocked.get(character);
	}

	public void addPositioningRequestsBlocked(GameCharacter character, AbstractSexPosition position) {
		if(!positioningRequestsBlocked.get(character).contains(position)) {
			positioningRequestsBlocked.get(character).add(position);
		}
	}
	
	public boolean isPositioningRequestBlocked(GameCharacter character, AbstractSexPosition position) {
		return positioningRequestsBlocked.get(character).contains(position);
	}

	/**
	 * @param targeter The character whose target is to be found.
	 * @return The character that the 'targeter' is currently focusing on.
	 */
	public GameCharacter getTargetedPartner(GameCharacter targeter) {
		if(Main.sex.isMasturbation()) {
			return Main.game.getPlayer();
		}
		return targetedCharacters.get(targeter);
	}
	
	public boolean isAbleToTarget(GameCharacter character) {
		return Main.sex.getSexPositionSlot(character)!=SexSlotGeneric.MISC_WATCHING;
	}
	
	public void setTargetedPartner(GameCharacter targeter, GameCharacter targetedCharacter) {
		if(targetedCharacter!=null && Main.sex.getSexPositionSlot(targetedCharacter)==SexSlotGeneric.MISC_WATCHING) {
			System.err.println("Sex error: "+targeter.getName(true)+" attempted to target the spectator: "+targetedCharacter.getName(true));
			return; // Cannot target spectators.
		}
//		System.out.println("Targeting: "+targeter.getName()+" "+targetedCharacter.getName());
		targetedCharacters.put(targeter, targetedCharacter);
	}
	
	public GameCharacter getCharacterPerformingAction() {
		return characterPerformingAction;
	}

	public void setCharacterPerformingAction(GameCharacter characterPerformingAction) {
		Main.sex.characterPerformingAction = characterPerformingAction;
	}
	
	public GameCharacter getCharacterTargetedForSexAction(SexActionInterface action) {
		if(action.getParticipantType()==SexParticipantType.SELF) {
			return getCharacterPerformingAction();
		}
		GameCharacter target = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
		if(target==null) {
			return getCharacterPerformingAction();
		}
		return target;
	}

	public int getTotalParticipantCount(boolean includeSpectators) {
		return getAllParticipants(includeSpectators).size();
	}
	
	public boolean isMasturbation() {
		return Main.sex.getAllParticipants(false).size()==1;
	}
	
	public List<GameCharacter> getAllParticipants(boolean includeSpectators) {
		List<GameCharacter> returnCharacters = new ArrayList<>();
		for(GameCharacter character : Main.sex.allParticipants) {
			if(includeSpectators || Main.sex.getSexPositionSlot(character)==null || Main.sex.getSexPositionSlot(character)!=SexSlotGeneric.MISC_WATCHING) {
				returnCharacters.add(character);
			}
		}
		return returnCharacters;
	}
	
	/**
	 * @return A list of everyone involved in this sex scene, <b>including</b> spectators.
	 */
	public List<GameCharacter> getAllParticipants() {
		return getAllParticipants(true);
	}

	public Map<GameCharacter, SexSlot> getAllOccupiedSlots(boolean includeSpectators) {
		Map<GameCharacter, SexSlot> allParticipantsMap = new HashMap<>();
		allParticipantsMap.putAll(getDominantParticipants(includeSpectators));
		allParticipantsMap.putAll(getSubmissiveParticipants(includeSpectators));
		return allParticipantsMap;
	}
	
	public Map<GameCharacter, SexSlot> getDominantParticipants(boolean includeSpectators) {
		if(includeSpectators) {
			return dominants;
			
		} else {
			Map<GameCharacter, SexSlot> map = new HashMap<>(dominants);
			for(GameCharacter character : Main.sex.getDominantSpectators()) {
				map.remove(character);
			}
			return map;
		}
	}
	
	public Map<GameCharacter, SexSlot> getSubmissiveParticipants(boolean includeSpectators) {
		if(includeSpectators) {
			return submissives;
			
		} else {
			Map<GameCharacter, SexSlot> map = new HashMap<>(submissives);
			for(GameCharacter character : Main.sex.getSubmissiveSpectators()) {
				map.remove(character);
			}
			return map;
		}
	}
	
	/**
	 * @return true if this character is the leader of either the submissives or dominants.
	 */
	public boolean isSexLeader(GameCharacter character) {
		return (!dominants.isEmpty() && dominants.keySet().iterator().next().equals(character))
				|| (!submissives.isEmpty() && submissives.keySet().iterator().next().equals(character));
	}
	
	public List<GameCharacter> getDominantSpectators() {
		return dominantSpectators;
	}
	
	public List<GameCharacter> getSubmissiveSpectators() {
		return submissiveSpectators;
	}
	
	public boolean isSpectator(GameCharacter character) {
		return dominantSpectators.contains(character) || submissiveSpectators.contains(character);
	}
	
	public String getUnequipClothingText() {
		return unequipClothingText;
	}

	public void setUnequipClothingText(AbstractClothing clothing, String unequipClothingText) {
		Main.sex.unequipClothingText =
						"<p style='text-align:center;'>"
								+ "<i style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Clothing removal</i>"+(clothing==null?"":": "+Util.capitaliseSentence(clothing.getName()))
								+"<br/>"
								+ unequipClothingText
						+ "</p>";
	}

	public void setUnequipWeaponText(AbstractWeapon weapon, String unequipClothingText) {
		Main.sex.unequipClothingText = 
				"<p style='text-align:center;'>"
						+ "<i style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Weapon removal</i>"+(weapon==null?"":": "+Util.capitaliseSentence(weapon.getName()))
						+"<br/>"
						+ unequipClothingText
				+ "</p>";
	}
	
	public void setDisplaceClothingText(AbstractClothing clothing, String unequipClothingText) {
		Main.sex.unequipClothingText = 
				"<p style='text-align:center;'>"
						+ "<i style='color:" + PresetColour.GENERIC_MINOR_BAD.toWebHexString() + ";'>Clothing displacement</i>"+(clothing==null?"":": "+Util.capitaliseSentence(clothing.getName()))
						+"<br/>"
						+ unequipClothingText
				+ "</p>";
	}
	
	public void setEquipClothingText(AbstractClothing clothing, String unequipClothingText) {
		Main.sex.unequipClothingText =
				"<p style='text-align:center;'>"
						+ "<i style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Clothing equip</i>"+(clothing==null?"":": "+Util.capitaliseSentence(clothing.getName()))
						+"<br/>"
						+ unequipClothingText
				+ "</p>";
	}

	public void setJinxRemovalClothingText(AbstractClothing clothing, String unequipClothingText) {
		Main.sex.unequipClothingText = 
				"<p style='text-align:center;'>"
						+ "<i style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>Seal removal</i>"+(clothing==null?"":": "+Util.capitaliseSentence(clothing.getName()))
						+"<br/>"
						+ unequipClothingText
				+ "</p>";
	}
	
	public String getDyeClothingText() {
		return dyeClothingText;
	}
	
	public void setDyeClothingText(String dyeClothingText) {
		Main.sex.dyeClothingText = dyeClothingText;
	}

	public String getUsingItemText() {
		return usingItemText;
	}

	public void setUsingItemText(String usingItemText) {
		Main.sex.usingItemText = usingItemText;
	}

	public AbstractClothing getClothingBeingRemoved() {
		return clothingBeingRemoved;
	}
	
	public boolean isAnyOngoingActionHappening() {
		for(GameCharacter character : Main.sex.getAllParticipants()) {
			if(isCharacterEngagedInOngoingAction(character)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isCharacterEngagedInOngoingAction(GameCharacter character, GameCharacter target) {
		if(!Main.sex.getAllParticipants().contains(character)) {
			System.err.println("isCharacterEngagedInOngoingAction("+character.getId()+") 1: This character is not in Sex!"); 
			return false;
		}
		if(!Main.sex.getAllParticipants().contains(target)) {
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
	
	public boolean isCharacterEngagedInOngoingAction(GameCharacter character) {
		if(!Main.sex.getAllParticipants().contains(character)) {
			System.err.println("isCharacterEngagedInOngoingAction("+character.getId()+") 2: This character is not in Sex!"); 
			return false;
		}

		for(Entry<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> entry : ongoingActionsMap.get(character).entrySet()) {
			for(GameCharacter penetrator : Main.sex.getAllParticipants()) {
				if(entry.getValue().containsKey(penetrator)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isAnyNonSelfOngoingActionHappening() {
		for(GameCharacter penetrator : Main.sex.getAllParticipants()) {
			for(GameCharacter penetrated : Main.sex.getAllParticipants()) {
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

	public boolean isCharacterSelfOngoingActionHappening(GameCharacter character) {
		for(Entry<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> entry : ongoingActionsMap.get(character).entrySet()) {
			if(entry.getValue().containsKey(character)) {
				return true;
			}
		}
		return false;
	}


	// Free area convenience methods:

	/**
	 * Note that this only checks if an ongoing action is currently using the orifice. You will likely still need to check if the orifice is covered by clothing or not.
	 */
	public boolean isOrificeFree(GameCharacter character, SexAreaOrifice orifice) {
		return ongoingActionsMap.get(character).get(orifice).isEmpty();
	}

	/**
	 * Note that this only checks if an ongoing action is currently using the orifice. You will likely still need to check if the orifice is covered by clothing or not.
	 */
	public boolean isOrificeNonSelfOngoingAction(GameCharacter characterOrifice, SexAreaOrifice orifice) {
		for(GameCharacter penetrator : Main.sex.allParticipants) {
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
	
	
	public int getPenetrationTypeFreeCount(GameCharacter penetrator, SexAreaPenetration penetrationType) {
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
				if(penetrator.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
					penetrationTypesAvailable = 1;
				} else {
					if(penetrator.getTailType()==TailType.NONE|| penetrator.getTailCount()==0) {
						return 0;
					}
					penetrationTypesAvailable = penetrator.getTailCount();
				}
				break;
			case TENTACLE:
				// Standing or not doesn't impact tentacle-legs, as they can always bend half-way to act as both support and a penetrative object
				penetrationTypesAvailable = penetrator.getTentacleCount();
//				if(penetrator.getLegConfiguration()==LegConfiguration.CEPHALOPOD) {
//					penetrationTypesAvailable += penetrator.getLegConfiguration().getNumberOfLegs();
//				}
				break;
			case TONGUE:
				break;
			case CLIT:
				if(!penetrator.hasVagina()) {
					return 0;
				}
				break;
			case FOOT:
				if(Main.sex.getSexPositionSlot(penetrator).isStanding(penetrator)) {
					// Need at least three legs to remain upright (if more than 4):
					penetrationTypesAvailable = Math.max(1, penetrator.getLegCount()-3);
				} else {
					penetrationTypesAvailable = penetrator.getLegCount();
				}
				break;
		}
		if(!Main.sex.isSpectator(penetrator)) {
			Map<SexAreaPenetration, Integer> restrictionMap = Main.sex.getPosition().getRestrictedPenetrationCounts(penetrator);
			if(restrictionMap!=null && restrictionMap.containsKey(penetrationType)) {
				penetrationTypesAvailable+=restrictionMap.get(penetrationType);
			}
		}
		int totalAreasUsed = 0;
		for(GameCharacter target : Main.sex.allParticipants) {
			if(ongoingActionsMap.get(penetrator).get(penetrationType).containsKey(target)) {
				totalAreasUsed += ongoingActionsMap.get(penetrator).get(penetrationType).get(target).size();
			}
		}
		return penetrationTypesAvailable-totalAreasUsed;
	}
	
	public boolean isPenetrationTypeFree(GameCharacter penetrator, SexAreaPenetration penetrationType) {
		return getPenetrationTypeFreeCount(penetrator, penetrationType)>0;
	}
	
	/**
	 * @return true if the penetrator is using their penetrationType on another character.
	 */
	public boolean isPenetrationNonSelfOngoingAction(GameCharacter penetrator, SexAreaPenetration penetrationType) {
		for(GameCharacter target : Main.sex.allParticipants) {
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

	
	// Item and clothing use methods:
	
	
	public boolean isItemUseAvailable() {
		return getInitialSexManager().isItemUseAvailable();
	}
	
	/**
	 * @return true if the user is willing to force the target to use items during sex (such as pills).
	 */
	public boolean isForcingItemUse(GameCharacter user, GameCharacter target) {
		return !Main.sex.isConsensual() && Main.sex.isDom(user) && !Main.sex.isDom(target);
	}

	public void setClothingSelfEquipInformation(GameCharacter clothingSelfEquipper, GameCharacter target, AbstractClothing clothing) {
		this.clothingSelfEquipInformation = new Value<>(clothingSelfEquipper, new Value<>(target, clothing));
	}
	
	/**
	 * @return Value<> Key is character who is self-equipping, to Value of character targeted and clothing being equipped.
	 */
	public Value<GameCharacter, Value<GameCharacter, AbstractClothing>> getClothingSelfEquipInformation() {
		return clothingSelfEquipInformation;
	}
	
	public void setClothingEquipInformation(GameCharacter clothingEquipper, GameCharacter target, AbstractClothing clothing) {
		this.clothingEquipInformation = new Value<>(clothingEquipper, new Value<>(target, clothing));
	}
	
	/**
	 * @return Value<> Key is character who is equipping, to Value of character targeted and clothing being equipped.
	 */
	public Value<GameCharacter, Value<GameCharacter, AbstractClothing>> getClothingEquipInformation() {
		return clothingEquipInformation;
	}
	
	public void setItemUseInformation(GameCharacter itemUser, GameCharacter itemTarget, AbstractItem item) {
		this.itemUseInformation = new Value<>(itemUser, new Value<>(itemTarget, item));
	}
	
	/**
	 * @return Value of character who is using item, to Value of character targeted and item type being used.
	 */
	public Value<GameCharacter, Value<GameCharacter, AbstractItem>> getItemUseInformation() {
		return itemUseInformation;
	}

	public void addItemUseDenial(GameCharacter user, GameCharacter target, AbstractItemType itemType) {
		itemUseDenials.putIfAbsent(user, new HashMap<>());
		itemUseDenials.get(user).putIfAbsent(target, new ArrayList<>());
		itemUseDenials.get(user).get(target).add(itemType);
	}
	
	/**
	 * @return A list of AbstractItemTypes which the user has tried to give to the target, but have been refused.
	 */
	public List<AbstractItemType> getItemUseDenials(GameCharacter user, GameCharacter target) {
		itemUseDenials.putIfAbsent(user, new HashMap<>());
		itemUseDenials.get(user).putIfAbsent(target, new ArrayList<>());
		return itemUseDenials.get(user).get(target);
	}
	
	

	// Orgasm convenience methods:

	public boolean isAnyCharacterReadyToOrgasm(boolean includeSpectators) {
		for(GameCharacter character : Main.sex.getAllParticipants(includeSpectators)) {
			if(isReadyToOrgasm(character)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isReadyToOrgasm(GameCharacter character) {
		return character.getArousal()>=ArousalLevel.FIVE_ORGASM_IMMINENT.getMaximumValue();
	}
	
	/**
	 * @return The character who is orgasming this turn, or, if there is no such character, the character who last orgasmed. (Might return null.)
	 */
	public GameCharacter getCharacterOrgasming() {
		return characterOrgasming;
	}
	
	public GameCharacter getCharacterLayingEggs() {
		return characterLayingEggs;
	}
	
	public void setCharacterLayingEggs(GameCharacter characterLayingEggs) {
		this.characterLayingEggs = characterLayingEggs;
	}
	
	public Map<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> getOngoingActionsMap(GameCharacter characterHavingOngoingActions) {
		return ongoingActionsMap.get(characterHavingOngoingActions);
	}
	
	/**
	 * @return A map of characters, with a set of SexAreas holding SexAreaInterfaces which are in contact with the passed-in character's sexArea.
	 */
	public Map<GameCharacter, Set<SexAreaInterface>> getOngoingSexAreas(GameCharacter character, SexAreaInterface sexArea) {
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
	public void setPrimaryOngoingCharacter(GameCharacter character, GameCharacter targetedCharacter, SexAreaInterface targetsSexArea) {
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
	public List<SexAreaInterface> getAllOngoingSexAreas(GameCharacter character, SexAreaInterface sexArea) {
		List<SexAreaInterface> returnList = new ArrayList<>();
		for(Set<SexAreaInterface> set : ongoingActionsMap.get(character).get(sexArea).values()) {
			returnList.addAll(set);
		}
		return returnList;
	}
	
	public List<SexAreaInterface> getOngoingSexAreas(GameCharacter character, SexAreaInterface sexArea, GameCharacter characterInteractingWith) {
		if(ongoingActionsMap.get(character).get(sexArea).containsKey(characterInteractingWith)) {
			return new ArrayList<>(ongoingActionsMap.get(character).get(sexArea).get(characterInteractingWith));
		} else {
			return new ArrayList<>();
		}
	}
	
	public SexAreaInterface getFirstOngoingSexArea(GameCharacter character, SexAreaInterface sexArea) {
		for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(character, sexArea).entrySet()) {
			for(SexAreaInterface sArea : entry.getValue()) {
				return sArea;
			}
		}
		return null;
	}
	
	public SexAreaPenetration getFirstOngoingSexAreaPenetration(GameCharacter character, SexAreaInterface sexArea) {
		for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(character, sexArea).entrySet()) {
			for(SexAreaInterface sArea : entry.getValue()) {
				if(sArea.isPenetration()) {
					return (SexAreaPenetration) sArea;
				}
			}
		}
		return null;
	}
	
	public SexAreaOrifice getFirstOngoingSexAreaOrifice(GameCharacter character, SexAreaInterface sexArea) {
		for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(character, sexArea).entrySet()) {
			for(SexAreaInterface sArea : entry.getValue()) {
				if(sArea.isOrifice()) {
					return (SexAreaOrifice) sArea;
				}
			}
		}
		return null;
	}
	
	/**
	 * @return The characters who are currently involved in an ongoing action with this target's sexArea.
	 */
	public List<GameCharacter> getCharacterOngoingSexArea(GameCharacter target, SexAreaInterface sexArea) {
		return new ArrayList<>(getOngoingSexAreas(target, sexArea).keySet());
	}
	
	/**
	 * @return The characters who are currently using their sexAreaUsing to contact this character's sexAreaBeingUsed.
	 */
	public Set<GameCharacter> getOngoingCharactersUsingAreas(GameCharacter character, SexAreaInterface sexAreaBeingUsed, SexAreaInterface sexAreaUsing) {
		Set<GameCharacter> characters = new HashSet<>();
		if(ongoingActionsMap==null || !ongoingActionsMap.containsKey(character) || !ongoingActionsMap.get(character).containsKey(sexAreaBeingUsed)) { // Catch for if sex has not yet finished initialising
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
	public List<SexAreaOrifice> getOrificesBeingPenetratedBy(GameCharacter characterPenetrating, SexAreaPenetration penetration, GameCharacter characterPenetrated) {
		if(ongoingActionsMap!=null
				&& ongoingActionsMap.containsKey(characterPenetrated)
				&& ongoingActionsMap.get(characterPenetrating).containsKey(penetration)
				&& ongoingActionsMap.get(characterPenetrating).get(penetration).containsKey(characterPenetrated)) {
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

	public List<GameCharacter> getCharactersHavingOngoingActionWith(GameCharacter character, SexAreaInterface area) {
		if(area.isOrifice()) {
			return getCharactersHavingOngoingActionWith(character, (SexAreaOrifice) area);
		} else {
			return getCharactersHavingOngoingActionWith(character, (SexAreaPenetration) area);
		}
	}
	
	/**
	 * Returns a list, not a single instance of GameCharacter, as the 'characterPenetrating' could be penetrating multiple characters with tentacles, tails, hands, etc.
	 */
	public List<GameCharacter> getCharactersHavingOngoingActionWith(GameCharacter characterPenetrating, SexAreaPenetration penetration) {
		if(ongoingActionsMap==null || !ongoingActionsMap.containsKey(characterPenetrating) || !ongoingActionsMap.get(characterPenetrating).containsKey(penetration)) {
			return new ArrayList<>();
		}
		return new ArrayList<>(ongoingActionsMap.get(characterPenetrating).get(penetration).keySet());
	}
	
	/**
	 * Returns a set, not a single instance of GameCharacter, as the 'characterPenetrating' could be penetrating multiple characters with tentacles, tails, hands, etc.
	 */
	public List<GameCharacter> getCharactersHavingOngoingActionWith(GameCharacter characterPenetrated, SexAreaOrifice orifice) {
		if(ongoingActionsMap==null || ongoingActionsMap.get(characterPenetrated)==null || ongoingActionsMap.get(characterPenetrated).get(orifice)==null) {
			return new ArrayList<>();
		}
		return new ArrayList<>(ongoingActionsMap.get(characterPenetrated).get(orifice).keySet());
	}
	
	public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getAllWetAreas() {
		return wetAreas;
	}
	
	public Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>> getWetAreas(GameCharacter character) {
		return wetAreas.get(character);
	}
	
	public boolean hasLubricationTypeFromAnyone(GameCharacter characterHavingLubrication, SexAreaInterface sexArea) {
		if(getWetAreas(characterHavingLubrication)==null) {
			return false;
		}
		for(GameCharacter lubricantProvider : Main.sex.getAllParticipants()) {
			if(!getWetAreas(characterHavingLubrication).get(sexArea).get(lubricantProvider).isEmpty()) {
				return true;
			}
		}
		
		return !getWetAreas(characterHavingLubrication).get(sexArea).get(null).isEmpty();
	}
	
	public boolean hasLubricationTypeFromAnyone(GameCharacter characterHavingLubrication, SexAreaInterface sexArea, LubricationType lubrication) {
		if(getWetAreas(characterHavingLubrication)==null) {
			return false;
		}
		for(GameCharacter lubricantProvider : Main.sex.getAllParticipants()) {
			if(getWetAreas(characterHavingLubrication).get(sexArea).get(lubricantProvider).contains(lubrication)) {
				return true;
			}
		}
		
		return getWetAreas(characterHavingLubrication).get(sexArea).get(null).contains(lubrication);
	}

	public AbstractClothing getSelectedClothing() {
		return selectedClothing;
	}

	public void setSelectedClothing(AbstractClothing selectedClothing) {
		Main.sex.selectedClothing = selectedClothing;
	}

	public SexManagerInterface getSexManager() {
		return sexManager;
	}

	public SexManagerInterface getInitialSexManager() {
		return initialSexManager;
	}


	public void setSexManager(SexManagerInterface sexManager) {
		setSexManager(sexManager, Main.sex.getDominantSpectators(), Main.sex.getSubmissiveSpectators());
	}
	
	public void setSexManager(SexManagerInterface sexManager, List<GameCharacter> dominantSpectators, List<GameCharacter> submissiveSpectators) {
		Main.sex.allParticipants = new ArrayList<>(sexManager.getDominants().keySet());
		Main.sex.allParticipants.addAll(sexManager.getSubmissives().keySet());
		Main.sex.allParticipants.addAll(dominantSpectators);
		Main.sex.allParticipants.addAll(submissiveSpectators);
		Main.sex.dominantSpectators = new ArrayList<>(dominantSpectators);
		Main.sex.submissiveSpectators = new ArrayList<>(submissiveSpectators);
		
		if(!sexInitFinished) {
			Main.sex.resetAllOngoingActions(true);
			
		} else {
			for(GameCharacter character : this.getAllParticipants(true)) {
				for(GameCharacter interactingCharacter : this.getAllParticipants(true)) {
					if(sexManager.getDominants().containsKey(character)) {
						if(this.getSexPositionSlot(character)!=sexManager.getDominants().get(character)) {
							stopAllOngoingActions(character, interactingCharacter);
						}
					} else if(sexManager.getSubmissives().containsKey(character)){
						if(this.getSexPositionSlot(character)!=sexManager.getSubmissives().get(character)) {
							stopAllOngoingActions(character, interactingCharacter);
						}
					}
				}
			}
		}
		
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
		
		Main.sex.dominants = new LinkedHashMap<>();
		for(GameCharacter character : tempCharacterList) {
			if(sexManager.getDominants().containsKey(character)) {
				Main.sex.dominants.put(character, sexManager.getDominants().get(character));
			} else {
				Main.sex.dominants.put(character, SexSlotGeneric.MISC_WATCHING);
			}
		}
		if(!Main.sex.isDom(Main.game.getPlayer())) {
			if(!tempCharacterList.isEmpty()) {
				Main.sex.setTargetedPartner(Main.game.getPlayer(), tempCharacterList.get(0));
			} else {
				Main.sex.setTargetedPartner(Main.game.getPlayer(), null);
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
		Main.sex.submissives = new LinkedHashMap<>();
		for(GameCharacter character : tempCharacterList) {
			if(sexManager.getSubmissives().containsKey(character)) {
				Main.sex.submissives.put(character, sexManager.getSubmissives().get(character));
			} else {
				Main.sex.submissives.put(character, SexSlotGeneric.MISC_WATCHING);
			}
		}
		if(Main.sex.isDom(Main.game.getPlayer())) {
			if(!tempCharacterList.isEmpty()) {
				Main.sex.setTargetedPartner(Main.game.getPlayer(), tempCharacterList.get(0));
			} else {
				Main.sex.setTargetedPartner(Main.game.getPlayer(), null);
			}
		}
		
		actionsAvailable.clear();
		orgasmActionsAvailable.clear();
		for(GameCharacter character : Main.sex.allParticipants) {
			actionsAvailable.put(character, new HashMap<>());
			orgasmActionsAvailable.put(character, new HashMap<>());
			
			for(GameCharacter target : Main.sex.allParticipants) {
				if(!character.equals(target) || Main.sex.isMasturbation()) {
					actionsAvailable.get(character).put(target, new HashSet<>());
					orgasmActionsAvailable.get(character).put(target, new HashSet<>());
				}
			}
		}
		
		Main.sex.sexManager = sexManager;
		
		Main.game.setInSex(true);
		
		updateAvailableActions();
		
		for(GameCharacter character : Main.sex.getAllParticipants()) {
			if(!character.isPlayer()) {
				Main.sex.getSexManager().assignNPCTarget(character);
			}
			// Make sure subs switched to doms and vice versa have correct paces:
			if(Main.sex.getSexPace(character).isDom() != Main.sex.isDom(character)) {
				Main.sex.setSexPace(character, Main.sex.getSexPace(character).getOppositeDomEquivalent());
			}
			if(actionsAvailable.get(Main.game.getPlayer()).containsKey(character)) {
				repeatActionsPlayer.removeIf((action) -> !actionsAvailable.get(Main.game.getPlayer()).get(character).contains(action));
			}
		}
		
		sexSB.append(
				"<p style='text-align:center;'><b>New position:</b> <b style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>"+Main.sex.sexManager.getPosition().getName()+"</b><br/>"
				+"<i><b>"+Main.sex.sexManager.getPosition().getDescription(Main.sex.getAllOccupiedSlots(false))+"</b></i></p>");
	}
	
	public void resetAllOngoingActions(boolean includeSpectators) {
		if(ongoingActionsMap==null) {
			ongoingActionsMap = new LinkedHashMap<>();
		}
		for(GameCharacter character : Main.sex.getAllParticipants()) {
			if(!Main.sex.isSpectator(character) || includeSpectators) {
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
	
	public void updateAvailableActions() {
		for(GameCharacter character : Main.sex.allParticipants) {
			for(GameCharacter target : Main.sex.allParticipants) {
				if(!character.equals(target) || Main.sex.isMasturbation()) {
					actionsAvailable.get(character).get(target).clear();
					orgasmActionsAvailable.get(character).get(target).clear();
				}
			}
		}
					
		for(GameCharacter character : Main.sex.allParticipants) {
			for(GameCharacter target : Main.sex.allParticipants) {
				if((!character.equals(target) || Main.sex.isMasturbation())
						&& (Main.sex.sexManager.getPosition().getAllAvailableSexPositions().contains(Main.sex.getSexPositionSlot(character)) || Main.sex.getSexPositionSlot(character)==SexSlotGeneric.MISC_WATCHING)) {
					
					//TODO Handle cloacas in here as well:
					if((character instanceof NPC) && ((NPC) character).getLimitedSexClasses()!=null) {
						for(SexActionInterface action : ((NPC)character).getLimitedSexClasses()) {
							Main.sex.addSexAction(
									character,
									target,
									Main.sex.sexManager.getPosition().getSexInteractions(Main.sex.getSexPositionSlot(character), Main.sex.getSexPositionSlot(target)),
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
						if(Main.sex.getSexPositionSlot(character)==SexSlotGeneric.MISC_WATCHING || Main.sex.getSexPositionSlot(target)==SexSlotGeneric.MISC_WATCHING) {
							interactions = StandardSexActionInteractions.spectator;
						} else {
							interactions = Main.sex.sexManager.getPosition().getSexInteractions(Main.sex.getSexPositionSlot(character), Main.sex.getSexPositionSlot(target));
						}
						
						if(interactions!=null) {
							// Cloaca reassignment (removing all anus interactions, as well as copying all vagina interactions and in those copies replacing vagina with anus):
							if(character.getGenitalArrangement()==GenitalArrangement.CLOACA || target.getGenitalArrangement()==GenitalArrangement.CLOACA) {
								interactions = getCloacaHandledInteractions(interactions, character, target);
							}
							
							if(Main.sex.sexManager.getPosition().isAddStandardActions()) {
								addActionsFromContainingClasses(character, target, interactions, SexActionPresets.allCommonActions);
							}
							if(Main.sex.sexManager.getPosition().getPositioningClasses()!=null) {
								addActionsFromContainingClasses(character, target, interactions, Main.sex.sexManager.getPosition().getPositioningClasses());
							}
							addActionsFromContainingClasses(character, target, interactions, Main.sex.sexManager.getPosition().getSpecialClasses());
							
							if(character instanceof NPC) {
								for(Class<?> sexClass : ((NPC)character).getUniqueSexClasses()) {
									Main.sex.addSexActionClass(character, target, interactions, sexClass);
								}
							}
							
							List<SexActionInterface> uniqueActionsFromManager = Main.sex.initialSexManager.getUniqueSexClasses(character);
							if(uniqueActionsFromManager!=null) {
								for(SexActionInterface action : uniqueActionsFromManager) {
									Main.sex.addSexAction(character, target, interactions, action, true, false);
								}
							}
						}
					}
				}
			}
		}
	}
	
	private SexActionInteractions getCloacaHandledInteractions(SexActionInteractions interactions, GameCharacter character, GameCharacter target) {
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
	
	private void addActionsFromContainingClasses(GameCharacter character, GameCharacter target, SexActionInteractions interactions, List<Class<?>> sexActionContainingClasses) {
		try {
			if(!sexActionContainingClasses.isEmpty()) {
				for(Class<?> container : sexActionContainingClasses) {
					addSexActionClass(character, target, interactions, container);
				}
			}
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
	private void addSexActionClass(GameCharacter character, GameCharacter target, SexActionInteractions interactions, Class<?> classToAddSexActionsFrom) {
		try {
			if(classToAddSexActionsFrom!=null) {
				Field[] fields = classToAddSexActionsFrom.getFields();
				
				for(Field f : fields){
					if(SexAction.class.isAssignableFrom(f.getType())) {
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
	
	private void addSexAction(GameCharacter character, GameCharacter target, SexActionInteractions interactions, SexActionInterface action, boolean addedForCharacter, boolean addedForTarget) {
		if(!addedForCharacter) {
			if(action.getSexAreaInteractions().isEmpty()
					|| action.getSexAreaInteractions().keySet().contains(null)
					|| action.getSexAreaInteractions().values().contains(null)) {
				// If no sex interactions are defined, or if there is an "interaction-to-null" defined (signifying that the action is performing a simple availability check), then add the action
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
			if(action.getSexAreaInteractions().isEmpty()
					|| action.getSexAreaInteractions().keySet().contains(null)
					|| action.getSexAreaInteractions().values().contains(null)) {
				// If no sex interactions are defined, or if there is an "interaction-to-null" defined (signifying that the action is performing a simple availability check), then add the action
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
		
		if(Main.sex.isMasturbation()
				?action.getParticipantType()==SexParticipantType.SELF || action.getCategory()==SexActionCategory.POSITIONING
				:((Main.sex.getSexPositionSlot(character)!=SexSlotGeneric.MISC_WATCHING && Main.sex.getSexPositionSlot(target)!=SexSlotGeneric.MISC_WATCHING)
						|| action.getParticipantType()==SexParticipantType.SELF
						|| (character.isPlayer() && action==GenericActions.PLAYER_SKIP_SEX)
						|| (Main.sex.isDom(character) && action.getCategory()==SexActionCategory.POSITIONING)
						|| action.getActionType().isOrgasmOption()
						|| action.getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)) {
			
			if(action.getActionType().isOrgasmOption()) {
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
	
	
	public Set<SexActionInterface> getActionsAvailablePlayer() {
		return actionsAvailable.get(Main.game.getPlayer()).get(Main.sex.getTargetedPartner(Main.game.getPlayer()));
	}

	public Set<SexActionInterface> getActionsAvailablePartner(GameCharacter characterPerformingAction, GameCharacter target) {
		return actionsAvailable.get(characterPerformingAction).get(target);
	}

	public Set<SexActionInterface> getOrgasmActionsPlayer() {
		return orgasmActionsAvailable.get(Main.game.getPlayer()).get(Main.sex.getTargetedPartner(Main.game.getPlayer()));
	}

	public Set<SexActionInterface> getOrgasmActionsPartner(GameCharacter characterPerformingAction, GameCharacter target) {
		return orgasmActionsAvailable.get(characterPerformingAction).get(target);
	}

//	public Set<SexActionInterface> getMutualOrgasmActions() {
//		return mutualOrgasmActions;
//	}
	
	//TODO is this needed in addition to the getActionsAvailablePlayer() method up above?
	public List<SexActionInterface> getAvailableSexActionsPlayer() {
		return availableSexActionsPlayer;
	}

	public SexActionInterface getLastUsedPlayerAction() {
		return getLastUsedSexAction(Main.game.getPlayer());
	}

	public SexActionInterface getLastUsedSexAction(GameCharacter character) {
		if(lastUsedSexAction!=null && lastUsedSexAction.containsKey(character)) {
			return lastUsedSexAction.get(character);
		} else {
			return null;
		}
	}

	public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
		return Main.sex.getSexManager().getForeplayPreference(character, targetedCharacter);
	}
	
	public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
		return Main.sex.getSexManager().getMainSexPreference(character, targetedCharacter);
	}
	
	public void setSexPace(GameCharacter character, SexPace sexPace) {
		forceSexPaceMap.put(character, sexPace);
	}
	
	public boolean isSexPaceForced(GameCharacter character) {
		return forceSexPaceMap.containsKey(character);
	}
	
	public SexPace getSexPace(GameCharacter character) {
		if(character==null) {
			return null;
		}
		
		if(forceSexPaceMap.containsKey(character)) {
			return forceSexPaceMap.get(character);
		}
		
		return LustLevel.getLustLevelFromValue(character.getLust()).getSexPace(Main.sex.isConsensual(), character);
	}
	
	/**
	 * @param position The position in which the returned character is currently occupying.
	 * @return null if no character is in the slot.
	 */
	public GameCharacter getCharacterInPosition(SexSlot position) {
//		if(!Main.sex.isSexStarted()) {
//			return Main.game.getPlayer(); // This is just a catch for when calculating maximum slot size before sex has started.
//		}
		
		for(Entry<GameCharacter, SexSlot> entry : Main.sex.dominants.entrySet()) {
			if(entry.getValue()==position) {
				return entry.getKey();
			}
		}
		for(Entry<GameCharacter, SexSlot> entry : Main.sex.submissives.entrySet()) {
			if(entry.getValue()==position) {
				return entry.getKey();
			}
		}
		return null;
	}
	
	public SexSlot getSexPositionSlot(GameCharacter character) {
		if(Main.sex.dominants.keySet().contains(character)) {
			return Main.sex.dominants.get(character);
			
		} else if(Main.sex.submissives.keySet().contains(character)) {
			return Main.sex.submissives.get(character);
		}
		
		throw new IllegalArgumentException("The passed character: "+character.getName(true)+" in Main.sex.getSexPositionSlot(character) is not detected as a participant in this Sex scene!");
	}
	
	public void swapSexPositionSlots(GameCharacter character1, GameCharacter character2) {
		SexSlot characterSlot1 = Main.sex.getSexPositionSlot(character1);
		SexSlot characterSlot2 = Main.sex.getSexPositionSlot(character2);
		
		if(Main.sex.dominants.keySet().contains(character1)) {
			Main.sex.dominants.put(character1, characterSlot2);
		}
		if(Main.sex.submissives.keySet().contains(character1)) {
			Main.sex.submissives.put(character1, characterSlot2);
		}
		
		if(Main.sex.dominants.keySet().contains(character2)) {
			Main.sex.dominants.put(character2, characterSlot1);
		}
		if(Main.sex.submissives.keySet().contains(character2)) {
			Main.sex.submissives.put(character2, characterSlot1);
		}
		
//		Main.sex.resetAllOngoingActions(false);
		for(GameCharacter character : this.getAllParticipants(true)) {
			stopAllOngoingActions(character1, character);
			stopAllOngoingActions(character2, character);
		}
		
		updateAvailableActions();
	}
	
	public List<OrgasmCumTarget> getAvailableCumTargets(GameCharacter orgasmingCharacter) {
		if(Main.sex.getSexPositionSlot(orgasmingCharacter)==SexSlotGeneric.MISC_WATCHING || Main.sex.getSexPositionSlot(Main.sex.getTargetedPartner(orgasmingCharacter))==SexSlotGeneric.MISC_WATCHING) {
			return StandardSexActionInteractions.spectator.getAvailableCumTargets();
			
		} else {
			GameCharacter target = Main.sex.getTargetedPartner(orgasmingCharacter);
			
			List<OrgasmCumTarget> areas = Util.mergeLists(
					Main.sex.sexManager.getPosition().getSexInteractions(Main.sex.getSexPositionSlot(orgasmingCharacter), Main.sex.getSexPositionSlot(target)).getAvailableCumTargets(),
					Main.sex.sexManager.getPosition().getSexInteractions(Main.sex.getSexPositionSlot(target), Main.sex.getSexPositionSlot(orgasmingCharacter)).getProvidedCumTargets());
			
			areas.removeAll(Main.sex.initialSexManager.getBannedOrgasmCumTargets(orgasmingCharacter, target));
			
			return areas;
		}
	}
	
	public boolean isDom(GameCharacter character) {
		try {
			return Main.sex.dominants.keySet().contains(character);
		} catch(Exception ex) {
			// This is a catch for when external sex managers are used and the sex control needs to be parsed before sex has finished initialising
			return ((SexManagerExternal)sexManager).getDominants().containsKey(character);
		}
	}
	
	public boolean isPublicSex() {
		return publicSex;
	}
	
	public boolean isSexFinished() {
		return sexFinished;
	}

	public void forceSexEnd() {
		sexFinished = true;
	}

	public boolean isSexStarted() {
		return sexStarted;
	}

	public void setSexStarted(boolean sexStarted) {
		Main.sex.sexStarted = sexStarted;
	}

	

	public Map<GameCharacter, GameCharacter> getCharactersKnottedTogether() {
		return charactersKnottedTogether;
	}
	
	/**
	 * @param characterKnotting The character who is doing the knotting.
	 * @return The character the characterKnotting is knotted to. Returns null if not knotted to anyone.
	 */
	public GameCharacter getCharacterKnotting(GameCharacter characterKnotting) {
		return charactersKnottedTogether.get(characterKnotting);
	}

	/**
	 * @param characterKnotted The character who is being knotted.
	 * @return The character the characterKnotted is being knotted by. Returns null if not knotted by anyone.
	 */
	public GameCharacter getCharacterBeingKnottedBy(GameCharacter characterKnotted) {
		for(Entry<GameCharacter, GameCharacter> entry : charactersKnottedTogether.entrySet()) {
			if(entry.getValue().equals(characterKnotted)) {
				return entry.getKey();
			}
		}
		return null;
	}
	
	public void addCharactersKnottedTogether(GameCharacter characterKnotting, GameCharacter characterKnotted) {
		charactersKnottedTogether.put(characterKnotting, characterKnotted);
	}
	
	public void removeCharactersKnottedTogether(GameCharacter characterKnotting) {
		charactersKnottedTogether.remove(characterKnotting);
	}
	
	
	public Set<SexAreaOrifice> getAreasCurrentlyStretching(GameCharacter character) {
		return areasCurrentlyStretching.get(character);
	}
	
	public Set<SexAreaOrifice> getAreasStretched(GameCharacter character) {
		return areasStretched.get(character);
	}

	public Set<SexAreaOrifice> getAreasTooLoose(GameCharacter character) {
		return areasTooLoose.get(character);
	}

	/**
	 * Characters which are penetrated too deep require checks for FETISH_SIZE_QUEEN to see if they are enjoying it or not.
	 * 
	 * @param character The character who is being penetrated.
	 * @param orifice The character's orifice which is of interest.
	 * @return A Map of GameCharacters who are penetrating the character's orifice, each mapped to a Set of SexAreaInterfaces which are penetrating too deep.
	 */
	public Map<GameCharacter, Set<SexAreaInterface>> getCharactersPenetratingTooDeep(GameCharacter character, SexAreaOrifice orifice) {
		Map<GameCharacter, Set<SexAreaInterface>> tooDeepMap = new HashMap<>();
		
		for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(character, orifice).entrySet()) {
			for(SexAreaInterface sArea : entry.getValue()) {
				if(sArea.isPenetration() && ((SexAreaPenetration)sArea).isTakesVirginity()) {
					if(entry.getKey().isFullPenetrationTooLong((SexAreaPenetration)sArea, character, orifice)) {
						tooDeepMap.putIfAbsent(entry.getKey(), new HashSet<>());
						tooDeepMap.get(entry.getKey()).add(sArea);
					}
				}
			}
		}
		
		return tooDeepMap;
	}
	
	/**
	 * @param character The character who is being penetrated.
	 * @param orifice The character's orifice which is of interest.
	 * @return A Map of GameCharacters who are penetrating the character's orifice, each mapped to a Set of SexAreaInterfaces which are penetrating far too little to be of any real pleasure (<50% of comfortable value).
	 */
	public Map<GameCharacter, Set<SexAreaInterface>> getCharactersPenetratingFarTooShallow(GameCharacter character, SexAreaOrifice orifice) {
		Map<GameCharacter, Set<SexAreaInterface>> tooShallowMap = new HashMap<>();
		
		for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(character, orifice).entrySet()) {
			for(SexAreaInterface sArea : entry.getValue()) {
				if(sArea.isPenetration() && ((SexAreaPenetration)sArea).isTakesVirginity()) {
					if(entry.getKey().isFullPenetrationFarTooShort((SexAreaPenetration)sArea, character, orifice)) {
						tooShallowMap.putIfAbsent(entry.getKey(), new HashSet<>());
						tooShallowMap.get(entry.getKey()).add(sArea);
					}
				}
			}
		}
		
		return tooShallowMap;
	}
	
	/**
	 * @param character The character to be checked.
	 * @param factorInOrgasmBlockers If true, and the character cannot orgasm, then this method does not check for actual satisfaction of this character, and instead returns true if they are simply at their maximum arousal.
	 * <br/>Note that this factors in denied orgasms if true.
	 * <br/>Should probably always be left as true, as otherwise characters unable to orgasm will always have this method return false.
	 * @return true if this character has orgasmed enough times to be satisfied.
	 */
	public boolean isSatisfiedFromOrgasms(GameCharacter character, boolean factorInOrgasmBlockers) {
		return isOrgasmCountMet(character, character.getOrgasmsBeforeSatisfied(), factorInOrgasmBlockers);
	}
	
	/**
	 * @param character The character to be checked.
	 * @param timesOrgasmed The number of orgasms which the character needs to have had in order for this method to return true.
	 * @param factorInOrgasmBlockers If true, and the character cannot orgasm, then this method does not check the character's orgasm count, and instead returns true if they are simply at their maximum arousal.
	 * <br/>Note that this factors in denied orgasms if true.
	 * <br/>Should probably always be left as true, as otherwise characters unable to orgasm will always have this method return false.
	 * @return true if this character has orgasmed 'timesOrgasmed' times.
	 */
	public boolean isOrgasmCountMet(GameCharacter character, int timesOrgasmed, boolean factorInOrgasmBlockers) {
		if(factorInOrgasmBlockers) {
			if(!character.isAbleToOrgasm()) {
				return character.getArousal()>=90f; // Although they stop at 95, some actions might be making them drop a little, so add some leeway down to 90
			}
			return getNumberOfDeniedOrgasms(character) + getNumberOfOrgasms(character) >= timesOrgasmed;
		}
		return getNumberOfOrgasms(character) >= timesOrgasmed;
	}
	
	public int getNumberOfOrgasms(GameCharacter character) {
		orgasmCountMap.putIfAbsent(character, 0);
		return orgasmCountMap.get(character);
	}
	
	public void setNumberOfOrgasms(GameCharacter character, int count) {
		character.setLastTimeOrgasmedSeconds(Main.game.getSecondsPassed());
		orgasmCountMap.put(character, count);
	}
	
	public void incrementNumberOfOrgasms(GameCharacter character, int increment) {
		character.setLastTimeOrgasmedSeconds(Main.game.getSecondsPassed());
		character.incrementDaysOrgasmCount(increment);
		character.incrementTotalOrgasmCount(increment);
		orgasmCountMap.putIfAbsent(character, 0);
		orgasmCountMap.put(character, orgasmCountMap.get(character)+increment);
	}

	public int getNumberOfDeniedOrgasms(GameCharacter characterDenied) {
		int denied = 0;
		for(Map<GameCharacter, Integer> value : deniedOrgasmsCountMap.values()) {
			if(value.containsKey(characterDenied)) {
				denied+=value.get(characterDenied);
			}
		}
		return denied;
	}
	
	public int getNumberOfDeniedOrgasms(GameCharacter denier, GameCharacter target) {
		deniedOrgasmsCountMap.putIfAbsent(denier, new HashMap<>());
		deniedOrgasmsCountMap.get(denier).putIfAbsent(target, 0);
		return deniedOrgasmsCountMap.get(denier).get(target);
	}
	
	public void setNumberOfDeniedOrgasms(GameCharacter denier, GameCharacter target, int count) {
		deniedOrgasmsCountMap.putIfAbsent(denier, new HashMap<>());
		deniedOrgasmsCountMap.get(denier).put(target, count);
	}
	
	public void incrementNumberOfDeniedOrgasms(GameCharacter denier, GameCharacter target, int increment) {
		deniedOrgasmsCountMap.putIfAbsent(denier, new HashMap<>());
		deniedOrgasmsCountMap.get(denier).putIfAbsent(target, 0);
		deniedOrgasmsCountMap.get(denier).put(target, deniedOrgasmsCountMap.get(denier).get(target)+increment);
	}
	
	/**
	 * @return The total number of times the character has cum inside the target during the sex scene. Use the other method signatures if you want to check for a specific orifice/penetration.
	 */
	public int getTimesCummedInside(GameCharacter character, GameCharacter target) {
		cummedInsideMap.putIfAbsent(character, new HashMap<>());
		cummedInsideMap.get(character).putIfAbsent(target, new HashMap<>());
		return cummedInsideMap.get(character).get(target).values().stream().collect(Collectors.summingInt(Integer::intValue));
	}
	
	public int getTimesCummedInside(GameCharacter character, GameCharacter target, SexAreaInterface areaCummedIn) {
		cummedInsideMap.putIfAbsent(character, new HashMap<>());
		cummedInsideMap.get(character).putIfAbsent(target, new HashMap<>());
		cummedInsideMap.get(character).get(target).putIfAbsent(areaCummedIn, 0);
		return cummedInsideMap.get(character).get(target).get(areaCummedIn);
	}
	
	public void setTimesCummedInside(GameCharacter character, GameCharacter target, SexAreaInterface areaCummedIn, int count) {
		cummedInsideMap.putIfAbsent(character, new HashMap<>());
		cummedInsideMap.get(character).putIfAbsent(target, new HashMap<>());
		cummedInsideMap.get(character).get(target).put(areaCummedIn, count);
	}
	
	public void incrementTimesCummedInside(GameCharacter character, GameCharacter target, SexAreaInterface areaCummedIn, int increment) {
		setTimesCummedInside(character, target, areaCummedIn, getTimesCummedInside(character, target, areaCummedIn)+increment);
	}
	
	public int getSexTypeCount(GameCharacter performer, GameCharacter partner, SexType sexType) {
		if(sexCountMap.containsKey(performer)) {
			if(sexCountMap.get(performer).containsKey(partner)) {
				if(sexCountMap.get(performer).get(partner).containsKey(sexType)) {
					return sexCountMap.get(performer).get(partner).get(sexType);
				}
			}
		}
		return 0;
	}
	
	public void incrementSexTypeCount(GameCharacter performer, GameCharacter partner, SexType sexType) {
		sexCountMap.putIfAbsent(performer, new HashMap<>());
		sexCountMap.get(performer).putIfAbsent(partner, new HashMap<>());
		sexCountMap.get(performer).get(partner).putIfAbsent(sexType, 0);
		
		sexCountMap.get(performer).get(partner).put(sexType, sexCountMap.get(performer).get(partner).get(sexType)+1);
	}
	
	public AbstractSexPosition getPosition() {
		return sexManager.getPosition();
	}

	public boolean isCharacterAllowedToUseSelfActions(GameCharacter character) {
		return !charactersSelfActionsBlocked.contains(character);
	}

	public void setCharacterAllowedToUseSelfActions(GameCharacter character, boolean allowedToUseSelfActions) {
		if(allowedToUseSelfActions) {
			charactersSelfActionsBlocked.remove(character);
		} else {
			charactersSelfActionsBlocked.add(character);
		}
	}

	public boolean isCharacterBannedFromRapePlay(GameCharacter character) {
		if(!Main.game.isInSex()) {
			return false;
		}
		return charactersBannedFromRapePlay.contains(character);
	}

	public void setCharacterBannedFromRapePlay(GameCharacter character, boolean bannedFromRapePlay) {
		if(!Main.game.isInSex()) {
			return;
		}
		if(bannedFromRapePlay) {
			charactersBannedFromRapePlay.add(character);
		} else {
			charactersBannedFromRapePlay.remove(character);
		}
	}
	
	public boolean isCanRemoveSelfClothing(GameCharacter character) {
		if(charactersBannedFromRemovingSelfClothing.contains(character)) {
			return false;
		}
		return initialSexManager.isAbleToRemoveSelfClothing(character);
	}

	public void setCanRemoveSelfClothing(GameCharacter character, boolean canRemoveSelfClothing) {
		if(canRemoveSelfClothing) {
			charactersBannedFromRemovingSelfClothing.remove(character);
		} else {
			charactersBannedFromRemovingSelfClothing.add(character);
		}
	}
	
	public boolean isCanRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
		if(charactersBannedFromRemovingOthersClothing.contains(character)) {
			return false;
		}
		return initialSexManager.isAbleToRemoveOthersClothing(character, clothing);
	}

	public void setCanRemoveOthersClothing(GameCharacter character, boolean canRemoveOthersClothing) {
		if(canRemoveOthersClothing) {
			charactersBannedFromRemovingOthersClothing.remove(character);
		} else {
			charactersBannedFromRemovingOthersClothing.add(character);
		}
	}
	
	public boolean isSelfTransformDisabled(GameCharacter character) {
		return initialSexManager.isSelfTransformDisabled(character);
	}

	public String getSexDescription() {
		return sexDescription;
	}

	public StringBuilder getSexSB() {
		return sexSB;
	}

	public List<SexActionInterface> getSelfActionsPlayer() {
		return selfActionsPlayer;
	}

	public List<SexActionInterface> getSexActionsPlayer() {
		return sexActionsPlayer;
	}

	public List<SexActionInterface> getPositionActionsPlayer() {
		return positionActionsPlayer;
	}

	public List<SexActionInterface> getRepeatActionsPlayer() {
		return repeatActionsPlayer;
	}

	public DialogueNode getPostSexDialogue() {
		return postSexDialogue;
	}

	public DialogueNode getSexDialogue() {
		return SEX_DIALOGUE;
	}

	public int getTurn() {
		return turn;
	}
	
	public boolean isDoubleFootJob(GameCharacter charactersFeet) {
		for(AbstractClothing clothing : charactersFeet.getClothingCurrentlyEquipped()) {
			if(clothing.getItemTags().contains(ItemTag.SPREADS_FEET)) {
				return false;
			}
		}
		for(AbstractStatusEffect se : charactersFeet.getStatusEffects()) {
			if(se.getTags().contains(ItemTag.SPREADS_FEET)) {
				return false;
			}
		}
		return !Main.sex.getSexPositionSlot(charactersFeet).isStanding(charactersFeet);
	}
	
	/**
	 * @return true if any character is <= 60% the size of another character.
	 */
	public boolean isSizeDifference(List<GameCharacter> sexParticipants) {
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
	public boolean isSizeDifference() {
		return isSizeDifference(Main.sex.getAllParticipants());
	}

	public PositioningData getPositionRequest() {
		return positionRequest;
	}

	public void setPositionRequest(PositioningData positionRequest) {
		Main.sex.positionRequest = positionRequest;
	}
	
	public boolean isCharacterWantingToStopSex(GameCharacter character) {
		return this.getInitialSexManager().isPartnerWantingToStopSex(character);
	}
	
	/**
	 * @return The Map of ImmobilisationTypes mapped to -> Maps of GameCharacters performing immobilisation mapped to -> Sets of GameCharacters who are immobilised.
	 */
	public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getCharactersImmobilised() {
		return charactersImmobilised;
	}

	/**
	 * @param type
	 * @return A Map of GameCharacters performing immobilisation mapped to -> Sets of GameCharacters who are currently being immobilised in the way specified by the ImmobilisationType argument 'type'.
	 */
	public Map<GameCharacter, Set<GameCharacter>> getCharactersImmobilised(ImmobilisationType type) {
		getCharactersImmobilised().putIfAbsent(type, new HashMap<>());
		return getCharactersImmobilised().get(type);
	}
	
	/**
	 * @param character
	 * @return A Value with the ImmobilisationType of this character (i.e. how they are currently being immobilised) as the key, and the GameCharacter responsible for performing the immobilisation as the value.
	 * <br/><b>Returns null if not immobilised.</b>
	 */
	public Value<ImmobilisationType, GameCharacter> getImmobilisationType(GameCharacter character) {
		for(Entry<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> entry : getCharactersImmobilised().entrySet()) {
			for(Entry<GameCharacter, Set<GameCharacter>> innerEntry : entry.getValue().entrySet()) {
				if(innerEntry.getValue().contains(character)) {
					return new Value<>(entry.getKey(), innerEntry.getKey());
				}
			}
		}
		return null;
	}
	
	/**
	 * @return true if the supplied character is immobilised in any way.
	 */
	public boolean isCharacterImmobilised(GameCharacter character) {
		return getImmobilisationType(character)!=null;
	}
	
	public boolean addCharacterImmobilised(ImmobilisationType type, GameCharacter characterPerformingImmobilisation, GameCharacter characterImmobilised) {
		getCharactersImmobilised().putIfAbsent(type, new HashMap<>());
		getCharactersImmobilised().get(type).putIfAbsent(characterPerformingImmobilisation, new HashSet<>());
		return getCharactersImmobilised().get(type).get(characterPerformingImmobilisation).add(characterImmobilised);
	}
	
	public void removeCharacterImmobilised(GameCharacter character) {
		for(Entry<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> entry : getCharactersImmobilised().entrySet()) {
			for(Entry<GameCharacter, Set<GameCharacter>> innerEntry : entry.getValue().entrySet()) {
				innerEntry.getValue().remove(character);
			}
		}
	}
	
	public Set<GameCharacter> getCharactersBannedFromPositioning() {
		return charactersBannedFromPositioning;
	}
	
	public boolean isCharacterBannedFromPositioning(GameCharacter character) {
		return getCharactersBannedFromPositioning().contains(character);
	}
	
	public boolean addCharacterBannedFromPositioning(GameCharacter character) {
		return getCharactersBannedFromPositioning().add(character);
	}

	public boolean removeCharacterBannedFromPositioning(GameCharacter character) {
		return getCharactersBannedFromPositioning().remove(character);
	}
	
	public Set<GameCharacter> getCharactersForbiddenByOthersFromPositioning() {
		return charactersForbiddenByOthersFromPositioning;
	}
	
	public boolean isCharacterForbiddenByOthersFromPositioning(GameCharacter character) {
		return getCharactersForbiddenByOthersFromPositioning().contains(character);
	}
	
	public boolean addCharacterForbiddenByOthersFromPositioning(GameCharacter character) {
		return getCharactersForbiddenByOthersFromPositioning().add(character);
	}

	public boolean removeCharacterForbiddenByOthersFromPositioning(GameCharacter character) {
		return getCharactersForbiddenByOthersFromPositioning().remove(character);
	}

	/** Maps: character orgasming -> Value of: characters forcing creampie, area used to force creampie */
	public Map<GameCharacter, Value<GameCharacter, Class<? extends BodyPartInterface>>> getCreampieLockedBy() {
		return creampieLockedBy;
	}

	/** Maps: character orgasming -> Value of: characters forcing creampie, area used to force creampie
	 * @param characterOrgasming The character who is being locked into a position during orgasm.
	 * @param creampieLockedBy If pass in null, then mapping for characterOrgasming is removed.
	 */
	public void setCreampieLockedBy(GameCharacter characterOrgasming, Value<GameCharacter, Class<? extends BodyPartInterface>> creampieLockedBy) {
		if(creampieLockedBy==null) {
			this.creampieLockedBy.remove(characterOrgasming);
		} else {
			this.creampieLockedBy.put(characterOrgasming, creampieLockedBy);
		}
	}
	
	public boolean isBipedSex() {
		for(GameCharacter character : Main.sex.getSubmissiveParticipants(false).keySet()) {
			if(character.getLegConfiguration()!=LegConfiguration.BIPEDAL) {
				return false;
			}
		}
		for(GameCharacter character : Main.sex.getDominantParticipants(false).keySet()) {
			if(character.getLegConfiguration()!=LegConfiguration.BIPEDAL) {
				return false;
			}
		}
		return true;
	}

	public boolean isOverridePlayerArousalRestriction() {
		return overridePlayerArousalRestriction;
	}

	public void setOverridePlayerArousalRestriction(boolean overridePlayerArousalRestriction) {
		Main.sex.overridePlayerArousalRestriction = overridePlayerArousalRestriction;
	}
	
	public boolean isCharacterDeniedOrgasm(GameCharacter character) {
		return charactersDeniedOrgasm.contains(character);
	}
	
	public boolean addCharacterDeniedOrgasm(GameCharacter character) {
		return charactersDeniedOrgasm.add(character);
	}
	
	public boolean removeCharacterDeniedOrgasm(GameCharacter character) {
		return charactersDeniedOrgasm.remove(character);
	}

	public Set<GameCharacter> getCharactersGrewCock() {
		return charactersGrewCock;
	}

	public void setCharactersGrewCock(Set<GameCharacter> charactersGrewCock) {
		Main.sex.charactersGrewCock = charactersGrewCock;
	}

	public Set<GameCharacter> getHeavyLipstickUsedCharacter() {
		return heavyLipstickUsedCharacter;
	}
	
	public void addHeavyLipstickUsedCharacter(GameCharacter character) {
		heavyLipstickUsedCharacter.add(character);
	}
	
	public boolean isRemoveEndSexAffection(GameCharacter character) {
		return removeEndSexAffection.contains(character);
	}

	public void addRemoveEndSexAffection(GameCharacter character) {
		Main.sex.removeEndSexAffection.add(character);
	}

	public Set<GameCharacter> getCharactersRequestingCreampie() {
		return charactersRequestingCreampie;
	}

	public Set<GameCharacter> getCharactersRequestingKnot() {
		return charactersRequestingKnot;
	}
	
	public Map<GameCharacter, OrgasmCumTarget> getCharactersRequestingPullout() {
		return charactersRequestingPullout;
	}
	
	public boolean isCharacterObeyingTarget(GameCharacter character, GameCharacter target) {
		if(Main.sex.isMasturbation()) { // Special catch for masturbation.
			return false;
		}
		
		if(target.hasTraitActivated(Perk.CONVINCING_REQUESTS)) { // If the target is convincing, then they always obey.
			return true;
		}
		
		if((character.isSlave() && character.getObedienceValue()<ObedienceLevel.POSITIVE_ONE_AGREEABLE.getMinimumValue())) { // Unruly slaves do what they want
			return false;
		}
		
		return Main.sex.getSexControl(target).getValue()>=Main.sex.getSexControl(character).getValue() || character.getFetishDesire(Fetish.FETISH_SUBMISSIVE).isPositive();
	}
	
	/**
	 * @param character The character who is thinking about pulling out.
	 * @return A positive value if they want to obey requests to pull out, or a negative value if they want to obey request to creampie. 0 is no requests.
	 */
	public int getRequestedPulloutWeighting(GameCharacter character) {
		int weighting = 0;
		
		for(GameCharacter pulloutRequester : Main.sex.getCharactersRequestingPullout().keySet()) {
			if(isCharacterObeyingTarget(character, pulloutRequester)) {
				if(pulloutRequester.hasTraitActivated(Perk.CONVINCING_REQUESTS)) {
					weighting+=50;
				} else {
					weighting+=5;
				}
			}
		}
		for(GameCharacter creampieRequester : Main.sex.getCharactersRequestingCreampie()) {
			if(isCharacterObeyingTarget(character, creampieRequester)) {
				if(creampieRequester.hasTraitActivated(Perk.CONVINCING_REQUESTS)) {
					weighting-=50;
				} else {
					weighting-=5;
				}
			}
		}
		for(GameCharacter creampieRequester : Main.sex.getCharactersRequestingKnot()) {
			if(isCharacterObeyingTarget(character, creampieRequester)) {
				if(creampieRequester.hasTraitActivated(Perk.CONVINCING_REQUESTS)) {
					weighting-=50;
				} else {
					weighting-=5;
				}
			}
		}
		
		return weighting;
	}
	
	public boolean isOngoingActionsBlockingSpeech(GameCharacter character) {
		try {
			for(Set<SexAreaInterface> saList : Main.sex.getOngoingActionsMap(character).get(SexAreaOrifice.MOUTH).values()) {
				for(SexAreaInterface sa : saList) {
					if(sa.isPenetration() && ((SexAreaPenetration)sa).isTakesVirginity()) {
						return true;
					}
				}
			}
		} catch(Exception ex) {}
		return false;
	}
	
	/**
	 * This method does <b>not</b> take into account whether the slot is accessible or not. It only checks for ongoing actions involving the specified slot.<br/>
	 * It also only accounts for the following slots: <b>ANUS</b>, <b>MOUTH</b>, <b>NIPPLE</b>, <b>PENIS</b>, <b>VAGINA</b>, <b>STOMACH</b>.<br/>
	 * Condoms are treated as always being able to be equipped.
	 */
	public boolean isClothingEquipAvailable(GameCharacter character, InventorySlot slot, AbstractClothing clothing) {
		if(clothing!=null && clothing.isCondom()) {
			return true;
		}
		switch(slot) {
			case ANUS:
				return Main.sex.getCharactersHavingOngoingActionWith(character, SexAreaOrifice.ANUS).isEmpty();
			case MOUTH:
				return Main.sex.getCharactersHavingOngoingActionWith(character, SexAreaOrifice.MOUTH).isEmpty();
			case NIPPLE:
				return Main.sex.getCharactersHavingOngoingActionWith(character, SexAreaOrifice.NIPPLE).isEmpty();
			case PENIS:
				return Main.sex.getCharactersHavingOngoingActionWith(character, SexAreaPenetration.PENIS).isEmpty();
			case VAGINA:
				return Main.sex.getCharactersHavingOngoingActionWith(character, SexAreaOrifice.VAGINA).isEmpty();
			case STOMACH:
				return Main.sex.getCharactersHavingOngoingActionWith(character, SexAreaOrifice.NIPPLE_CROTCH).isEmpty();
			default:
				break;
		}
		return true;
	}
	
	public boolean isSadisticActionsAllowed() {
		return initialSexManager.isSadisticActionsAllowed();
	}

	public boolean isLovingActionsAllowed() {
		return initialSexManager.isLovingActionsAllowed();
	}
	
	public String getDirtyTalk(GameCharacter character) {
		return initialSexManager.getDirtyTalk(character);
	}
	
	public String getRoughTalk(GameCharacter character) {
		return initialSexManager.getRoughTalk(character);
	}
	
	public String getLovingTalk(GameCharacter character) {
		return initialSexManager.getLovingTalk(character);
	}

	public String getLovingResponseTalk(GameCharacter character) {
		return initialSexManager.getLovingResponseTalk(character);
	}
	
	public String getSubmissiveTalk(GameCharacter character) {
		return initialSexManager.getSubmissiveTalk(character);
	}
	
	public SexManagerLoader getSexManagerLoader() {
		return sexManagerLoader;
	}

	public SexActionManager getSexActionManager() {
		return sexActionManager;
	}

	public Map<GameCharacter, Map<InventorySlot, Map<AbstractClothing, List<DisplacementType>>>> getClothingPreSexMap() {
		return clothingPreSexMap;
	}

	public Map<GameCharacter, Map<InventorySlot, AbstractWeapon>> getWeaponsPreSexMap() {
		return weaponsPreSexMap;
	}

	public Map<GameCharacter, Map<GameCharacter, List<AbstractClothing>>> getClothingEquippedDuringSex() {
		return clothingEquippedDuringSex;
	}
	
	public void addClothingEquippedDuringSex(GameCharacter clothingOwner, GameCharacter target, AbstractClothing clothing) {
		clothingEquippedDuringSex.get(clothingOwner).putIfAbsent(target, new ArrayList<>());
		clothingEquippedDuringSex.get(clothingOwner).get(target).add(clothing);
	}

	// ------------------------------------------------------ //
	
	/**
	 * Helper method so that there's a parser hook for generating a SexType.
	 */
	public SexType newSexType(SexParticipantType asParticipant, SexAreaInterface performingSexArea, SexAreaInterface targetedSexArea) {
		return new SexType(asParticipant, performingSexArea, targetedSexArea);
	}
	
	public SexType newSexType(SexAreaInterface performingSexArea, SexAreaInterface targetedSexArea) {
		return new SexType(SexParticipantType.NORMAL, performingSexArea, targetedSexArea);
	}
	
	public void applyGenericPullOutEffects() {
		GenericOrgasms.applyGenericPullOutEffects(null, null);
	}
}
