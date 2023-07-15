package com.lilithsthrone.game;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.controller.TooltipUpdateThread;
import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInformationEventListener;
import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.SexCount;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Amber;
import com.lilithsthrone.game.character.npc.dominion.Angel;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Ashley;
import com.lilithsthrone.game.character.npc.dominion.Brax;
import com.lilithsthrone.game.character.npc.dominion.Bunny;
import com.lilithsthrone.game.character.npc.dominion.Callie;
import com.lilithsthrone.game.character.npc.dominion.CandiReceptionist;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.DominionAlleywayAttacker;
import com.lilithsthrone.game.character.npc.dominion.DominionClubNPC;
import com.lilithsthrone.game.character.npc.dominion.Elle;
import com.lilithsthrone.game.character.npc.dominion.EnforcerPatrol;
import com.lilithsthrone.game.character.npc.dominion.Felicia;
import com.lilithsthrone.game.character.npc.dominion.Finch;
import com.lilithsthrone.game.character.npc.dominion.Hannah;
import com.lilithsthrone.game.character.npc.dominion.HarpyBimbo;
import com.lilithsthrone.game.character.npc.dominion.HarpyBimboCompanion;
import com.lilithsthrone.game.character.npc.dominion.HarpyDominant;
import com.lilithsthrone.game.character.npc.dominion.HarpyDominantCompanion;
import com.lilithsthrone.game.character.npc.dominion.HarpyNympho;
import com.lilithsthrone.game.character.npc.dominion.HarpyNymphoCompanion;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Jules;
import com.lilithsthrone.game.character.npc.dominion.Kalahari;
import com.lilithsthrone.game.character.npc.dominion.Kate;
import com.lilithsthrone.game.character.npc.dominion.Kay;
import com.lilithsthrone.game.character.npc.dominion.Kruger;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Loppy;
import com.lilithsthrone.game.character.npc.dominion.Lumi;
import com.lilithsthrone.game.character.npc.dominion.Natalya;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.npc.dominion.NyanMum;
import com.lilithsthrone.game.character.npc.dominion.Pazu;
import com.lilithsthrone.game.character.npc.dominion.Pix;
import com.lilithsthrone.game.character.npc.dominion.Ralph;
import com.lilithsthrone.game.character.npc.dominion.ReindeerOverseer;
import com.lilithsthrone.game.character.npc.dominion.RentalMommy;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.npc.dominion.Sean;
import com.lilithsthrone.game.character.npc.dominion.SupplierLeader;
import com.lilithsthrone.game.character.npc.dominion.SupplierPartner;
import com.lilithsthrone.game.character.npc.dominion.TestNPC;
import com.lilithsthrone.game.character.npc.dominion.Vanessa;
import com.lilithsthrone.game.character.npc.dominion.Vicky;
import com.lilithsthrone.game.character.npc.dominion.Wes;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKatherine;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKelly;
import com.lilithsthrone.game.character.npc.fields.Arion;
import com.lilithsthrone.game.character.npc.fields.Astrapi;
import com.lilithsthrone.game.character.npc.fields.Aurokaris;
import com.lilithsthrone.game.character.npc.fields.Belle;
import com.lilithsthrone.game.character.npc.fields.Ceridwen;
import com.lilithsthrone.game.character.npc.fields.Dale;
import com.lilithsthrone.game.character.npc.fields.Daphne;
import com.lilithsthrone.game.character.npc.fields.Eisek;
import com.lilithsthrone.game.character.npc.fields.Evelyx;
import com.lilithsthrone.game.character.npc.fields.EvelyxMilker;
import com.lilithsthrone.game.character.npc.fields.EvelyxSexualPartner;
import com.lilithsthrone.game.character.npc.fields.Fae;
import com.lilithsthrone.game.character.npc.fields.Farah;
import com.lilithsthrone.game.character.npc.fields.FieldsBandit;
import com.lilithsthrone.game.character.npc.fields.Flash;
import com.lilithsthrone.game.character.npc.fields.Hale;
import com.lilithsthrone.game.character.npc.fields.HeadlessHorseman;
import com.lilithsthrone.game.character.npc.fields.Heather;
import com.lilithsthrone.game.character.npc.fields.Imsu;
import com.lilithsthrone.game.character.npc.fields.Jess;
import com.lilithsthrone.game.character.npc.fields.Kazik;
import com.lilithsthrone.game.character.npc.fields.Kheiron;
import com.lilithsthrone.game.character.npc.fields.LunetteMelee;
import com.lilithsthrone.game.character.npc.fields.LunetteRanged;
import com.lilithsthrone.game.character.npc.fields.Lunexis;
import com.lilithsthrone.game.character.npc.fields.Minotallys;
import com.lilithsthrone.game.character.npc.fields.Monica;
import com.lilithsthrone.game.character.npc.fields.Moreno;
import com.lilithsthrone.game.character.npc.fields.Nizhoni;
import com.lilithsthrone.game.character.npc.fields.Oglix;
import com.lilithsthrone.game.character.npc.fields.Penelope;
import com.lilithsthrone.game.character.npc.fields.Silvia;
import com.lilithsthrone.game.character.npc.fields.Sterope;
import com.lilithsthrone.game.character.npc.fields.Ursa;
import com.lilithsthrone.game.character.npc.fields.Vronti;
import com.lilithsthrone.game.character.npc.fields.Wynter;
import com.lilithsthrone.game.character.npc.fields.Yui;
import com.lilithsthrone.game.character.npc.fields.Ziva;
import com.lilithsthrone.game.character.npc.misc.ClubberImport;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.npc.misc.GenericAndrogynousNPC;
import com.lilithsthrone.game.character.npc.misc.GenericFemaleNPC;
import com.lilithsthrone.game.character.npc.misc.GenericMaleNPC;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.npc.misc.GenericTrader;
import com.lilithsthrone.game.character.npc.misc.LodgerImport;
import com.lilithsthrone.game.character.npc.misc.ModdedCharacter;
import com.lilithsthrone.game.character.npc.misc.NPCOffspring;
import com.lilithsthrone.game.character.npc.misc.OffspringSeed;
import com.lilithsthrone.game.character.npc.misc.PrologueFemale;
import com.lilithsthrone.game.character.npc.misc.PrologueMale;
import com.lilithsthrone.game.character.npc.misc.SlaveImport;
import com.lilithsthrone.game.character.npc.submission.Axel;
import com.lilithsthrone.game.character.npc.submission.Claire;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.Elizabeth;
import com.lilithsthrone.game.character.npc.submission.Epona;
import com.lilithsthrone.game.character.npc.submission.FortressAlphaLeader;
import com.lilithsthrone.game.character.npc.submission.FortressFemalesLeader;
import com.lilithsthrone.game.character.npc.submission.FortressMalesLeader;
import com.lilithsthrone.game.character.npc.submission.GamblingDenPatron;
import com.lilithsthrone.game.character.npc.submission.HazmatRat;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.npc.submission.Murk;
import com.lilithsthrone.game.character.npc.submission.RatWarrensCaptive;
import com.lilithsthrone.game.character.npc.submission.Roxy;
import com.lilithsthrone.game.character.npc.submission.Shadow;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.character.npc.submission.SlimeGuardFire;
import com.lilithsthrone.game.character.npc.submission.SlimeGuardIce;
import com.lilithsthrone.game.character.npc.submission.SlimeQueen;
import com.lilithsthrone.game.character.npc.submission.SlimeRoyalGuard;
import com.lilithsthrone.game.character.npc.submission.Takahashi;
import com.lilithsthrone.game.character.npc.submission.Vengar;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.pregnancy.Litter;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.AbstractDialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueFlags;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.encounters.AbstractEncounter;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntry;
import com.lilithsthrone.game.dialogue.eventLog.SlaveryEventLogEntry;
import com.lilithsthrone.game.dialogue.npcDialogue.QuickTransformations;
import com.lilithsthrone.game.dialogue.places.dominion.RedLightDistrict;
import com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ.EnforcerHQDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Lab;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaHomeGeneric;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaverAlleyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict.DominionExpress;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloor;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePokerTable;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpCitadelDialogue;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpFortressDialogue;
import com.lilithsthrone.game.dialogue.places.submission.ratWarrens.RatWarrensDialogue;
import com.lilithsthrone.game.dialogue.places.submission.ratWarrens.VengarCaptiveDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.CosmeticsDialogue;
import com.lilithsthrone.game.dialogue.utils.DebugDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.MapTravelType;
import com.lilithsthrone.game.dialogue.utils.MiscDialogue;
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.dialogue.utils.ParserTarget;
import com.lilithsthrone.game.dialogue.utils.PhoneDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.ItemGeneration;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.game.occupantManagement.OccupancyUtil;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermission;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.game.occupantManagement.slaveEvent.SlaveEvent;
import com.lilithsthrone.game.settings.KeyCodeWithModifiers;
import com.lilithsthrone.game.settings.KeyboardAction;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Artwork;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.SizedStack;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.utils.time.DateAndTime;
import com.lilithsthrone.utils.time.DayPeriod;
import com.lilithsthrone.utils.time.SolarElevationAngle;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.Generation;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.World;
import com.lilithsthrone.world.WorldRegion;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.1.0
 * @version 0.3.7.3
 * @author Innoxia, AlacoGit
 */
public class Game implements XMLSaving {

	public static final int FONT_SIZE_MINIMUM = 12;
	public static final int FONT_SIZE_NORMAL = 18;
	public static final int FONT_SIZE_LARGE = 24;
	public static final int FONT_SIZE_HUGE = 36;
	
	public static final int TIME_SKIP_YEARS = 3;
	public static final int TIME_START_SECONDS = (20*60 + 34)*60;

	public static final double DOMINION_LONGITUDE = 0;
	public static final double DOMINION_LATITUDE = 51.4934;
	
	
	public static String loadingVersion = Main.VERSION_NUMBER;
	
	private long id;
	
	private PlayerCharacter player;
	private ItemGeneration itemGeneration;
	private CharacterUtils characterUtils;
	
	// NPCs:
	private NPC activeNPC;
	private AtomicInteger npcTally = new AtomicInteger(0);
	private AtomicInteger offspringSeedTally = new AtomicInteger(0);

	//Note : this is a ConcurrentHashMap
	private Map<String, NPC> NPCMap;
	private Map<String, OffspringSeed> OffspringSeedMap;

	/** Key is the world to which the Enforcers patrol. Value is a List of Enforcer groups who are patrolling. */
	private Map<AbstractWorldType, List<List<String>>> savedEnforcers;
	
	private Map<AbstractWorldType, World> worlds;
	private long lastAutoSaveTime = 0;
	private long secondsPassed; // Seconds passed since the start of the game
	private LocalDateTime startingDate;
	
	private boolean renderAttributesSection;
	private boolean renderMap;
	private boolean inCombat;
	private boolean inSex;
	private boolean requestAutosave;
	
	private Weather currentWeather;
	private long nextStormTimeInSeconds;
	private int gatheringStormDurationInSeconds;
	private int weatherTimeRemainingInSeconds;
	
	private Encounter currentEncounter;
	// Need to always return the same encounter at the same time in case it gets triggered multiple times in logic somewhere, so once it's been calculated at a certain time, reuse that result.
	// These two variables are responsible for holding that information (and are located here as they need to be reset upon new game or loading a game).
	public Value<Long, DialogueNode> forcedEncounterAtSeconds = new Value<>(-1l, null);
	public Value<Long, DialogueNode> encounterAtSeconds = new Value<>(-1l, null);

	private boolean started;
	
	private static Map<String, CharacterInventory> savedInventories; // Map of ID to inventory

	// Managing dialogue:
	private DialogueManager dialogueManager;
	private DialogueFlags dialogueFlags;
	
	// Responses:
	private int responsePointer = 0;
	
	// Dialogues:
	private DialogueNode currentDialogueNode;
	private DialogueNode savedDialogueNode = null;
	
	private String currentDialogue;
	private String savedDialogue;
	private String previousPastDialogueSBContents = "";
	
	private int initialPositionAnchor = 0;
	private int responsePage = 0;
	private int responseTab = 0;
	private int savedResponseTab = 0;
	
	private StringBuilder pastDialogueSB = new StringBuilder();
	private StringBuilder choicesDialogueSB = new StringBuilder();
	private StringBuilder textEndStringBuilder = new StringBuilder();
	private StringBuilder textStartStringBuilder = new StringBuilder();

	public static Map<String, TooltipInformationEventListener> informationTooltips = new HashMap<>();
	
	// Logs:
	private SizedStack<EventLogEntry> eventLog = new SizedStack<>(50);
	private SizedStack<Value<Integer, List<SlaveryEventLogEntry>>> slaveryEventLog = new SizedStack<>(7);
	
	// Slavery:
	private OccupancyUtil occupancyUtil = new OccupancyUtil();

	public Game() {
		// Surely this will work as a unique id (unless someone creates two new games within the same second, but surely that will never happen...)
		id = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
		
		worlds = new HashMap<>();
		for(AbstractWorldType type : WorldType.getAllWorldTypes()) {
			worlds.put(type, null);
		}
		
		itemGeneration = new ItemGeneration();
		characterUtils = new CharacterUtils();
		OccupantManagementDialogue.resetImportantCells();
		startingDate = LocalDateTime.of(
				2019, // LocalDateTime.now().getYear(),
				LocalDateTime.now().getMonth(),
				LocalDateTime.now().getDayOfMonth(),
				00,
				00);
		secondsPassed = TIME_START_SECONDS;
		inCombat = false;
		inSex = false;
		renderAttributesSection = false;
		renderMap = false;

		dialogueManager = new DialogueManager();
		dialogueFlags = new DialogueFlags();

		started = false;

		NPCMap = new ConcurrentHashMap<>();
		OffspringSeedMap = new ConcurrentHashMap<>();

		savedEnforcers = new HashMap<>();
		
		savedInventories = new HashMap<>();

		// Start in clouds:
		currentWeather = Weather.CLOUD;
		weatherTimeRemainingInSeconds = 5*60*60;
		nextStormTimeInSeconds = getSecondsPassed() + (((60*48) + (60*Util.random.nextInt(24)))*60); // Next storm in 2-3 days
		
		UtilText.resetParsingEngine();
	}
	
	private static boolean timeLog = false;
	private static long timeStart = 0;
	
	public static void exportCharacter(GameCharacter character) {
		try {
			if(timeLog) {
				timeStart = System.nanoTime();
				System.out.println(timeStart);
			}
			// Starting stuff:

			Document doc = Main.getDocBuilder().newDocument();

			// Writing game stuff to export:
			
			Element characterNode = doc.createElement("exportedCharacter");
			doc.appendChild(characterNode);
			
			character.saveAsXML(characterNode, doc);
			
			// Ending stuff:

			Transformer transformer1 = Main.transformerFactory.newTransformer();
			transformer1.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();

			transformer1.transform(new DOMSource(doc), new StreamResult(writer));
			
			// Save this xml:

			Transformer transformer = Main.transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			
			File dir = new File("data/");
			dir.mkdir();
			
			File dirCharacter = new File("data/characters/");
			dirCharacter.mkdir();
			
			int saveNumber = 0;
			String savePostfix = "_export_day"+Main.game.getDayNumber();
			String characterName = character.getName(false).replaceAll("\\s", "");
			String saveLocation = "data/characters/"+characterName+savePostfix+".xml";
			while(new File(saveLocation).exists()) {
				saveNumber++;
				saveLocation = "data/characters/"+characterName+savePostfix+"("+saveNumber+").xml";
			}
			
//			if(new File("data/characters/exported_"+characterName+"_day"+Main.game.getDayNumber()+".xml").exists()) {
//				saveLocation = "data/characters/exported_"+characterName+"_day"+Main.game.getDayNumber()+"("+saveNumber+").xml";
//			}
//			
//			while(new File("data/characters/exported_"+characterName+"_day"+Main.game.getDayNumber()+"("+saveNumber+").xml").exists()) {
//				saveNumber++;
//				saveLocation = "data/characters/exported_"+characterName+"_day"+Main.game.getDayNumber()+"("+saveNumber+").xml";
//			}
			
			
			StreamResult result = new StreamResult(saveLocation);
			
			transformer.transform(source, result);
			
			// Export artwork into folder beside the character's xml file
			if(character.hasArtwork()) {
				try {
					Path destination = Paths.get("data", "characters", characterName+savePostfix);
					Files.createDirectories(destination);
					
					for(Artwork art : character.getArtworkList()) {
						for (String s : art.getAllImagePaths()) {
							// Copy to temporary file and use atomic move to guarantee that the file is available
							File f = new File(s);
							System.out.println("x: "+s);
							Path tmp = destination.resolve(f.getName() + ".tmp");
							Files.copy(f.toPath(), tmp);
							Files.move(tmp, destination.resolve(f.getName()), StandardCopyOption.ATOMIC_MOVE);
						}
					}
				} catch (IOException e1) {
				}
			}
			
			if(timeLog) {
				System.out.println("Difference: "+(System.nanoTime()-timeStart)/1000000000f);
			}
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
	
	private static void handleCharacterImportImages(GameCharacter importedCharacter, String fileName) {
		File potentialArtworkFolder = new File("data/characters/"+fileName);
		if(potentialArtworkFolder.exists() && potentialArtworkFolder.isDirectory()) {
			List<File> imageFiles = new ArrayList<>();
			for(File f : potentialArtworkFolder.listFiles()) {
				if(f.getName().endsWith(".jpg") || f.getName().endsWith(".png") || f.getName().endsWith(".gif")) {
					imageFiles.add(f);
				}
			}
			importedCharacter.importImages(imageFiles);
		}
	}
	
	public static GameCharacter importCharacterAsSlave(String name) {
		File file = new File("data/characters/"+name+".xml");
		
		if (file.exists()) {
			try {
				Document doc = Main.getDocBuilder().parse(file);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element characterElement = (Element) doc.getElementsByTagName("exportedCharacter").item(0);
				if(characterElement == null) {
					characterElement = (Element) doc.getElementsByTagName("playerCharacter").item(0);
				}
				
				// Load NPCs:
				SlaveImport importedSlave = new SlaveImport();
				importedSlave.loadFromXML(characterElement, doc,
						CharacterImportSetting.NO_PREGNANCY,
						CharacterImportSetting.NO_COMPANIONS,
						CharacterImportSetting.NO_ELEMENTAL,
						CharacterImportSetting.CLEAR_SLAVERY,
						CharacterImportSetting.NO_LOCATION_SETUP,
						CharacterImportSetting.CLEAR_KEY_ITEMS,
						CharacterImportSetting.CLEAR_COMBAT_HISTORY,
						CharacterImportSetting.CLEAR_SEX_HISTORY,
						CharacterImportSetting.REMOVE_RACE_CONCEALED,
						CharacterImportSetting.CLEAR_FAMILY_ID);
				try {
					if(((Element)((Element)((Element)characterElement.getElementsByTagName("character").item(0)).getElementsByTagName("core").item(0)).getElementsByTagName("id").item(0)).getAttribute("value").equals("PlayerCharacter")) {
						importedSlave.setBirthday(importedSlave.getBirthday().plusYears(18)); // If the imported character is a player character, they need to have their age adjusted to fit with the fact that NPCs start at age 18
					}
				} catch(Exception ex) {	
				}
				Main.game.addNPC(importedSlave, false);
				importedSlave.applyNewlyImportedSlaveVariables();
				
				handleCharacterImportImages(importedSlave, name);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	public Integer importModdedCharacter(String path, String parserTarget) {
		File file = new File(path);

		if (file.exists()) {
			try {
				Document doc = Main.getDocBuilder().parse(file);

				// Cast magic:
				doc.getDocumentElement().normalize();

				Element characterElement = (Element) doc.getElementsByTagName("exportedCharacter").item(0);

				ModdedCharacter npc = new ModdedCharacter();
				npc.loadFromXML(characterElement, doc, CharacterImportSetting.NO_LOCATION_SETUP);

				Main.game.addNPC(npc, false, true);
				if (parserTarget != null && !parserTarget.isEmpty()) {
					ParserTarget.addAdditionalParserTarget(parserTarget, npc);
				}
				// id should be something like "123,moddedCharacter"
				return Integer.parseInt(npc.getId().split(",")[0]);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return null;
	}

	public void setModdedCharacterParserTarget(Long id, String parserTarget) {
		try {
			ParserTarget.addAdditionalParserTarget(parserTarget, (NPC) getNPCById(id + ",ModdedCharacter"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static GameCharacter importCharacterAsLodger(String name) {
		File file = new File("data/characters/"+name+".xml");
		
		if (file.exists()) {
			try {
				Document doc = Main.getDocBuilder().parse(file);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element characterElement = (Element) doc.getElementsByTagName("exportedCharacter").item(0);
				if(characterElement == null) {
					characterElement = (Element) doc.getElementsByTagName("playerCharacter").item(0);
				}
				
				// Load NPCs:
				LodgerImport importedLodger = new LodgerImport();
				importedLodger.loadFromXML(characterElement, doc,
						CharacterImportSetting.NO_PREGNANCY,
						CharacterImportSetting.NO_COMPANIONS,
						CharacterImportSetting.NO_ELEMENTAL,
						CharacterImportSetting.CLEAR_SLAVERY,
						CharacterImportSetting.NO_LOCATION_SETUP,
						CharacterImportSetting.CLEAR_KEY_ITEMS,
						CharacterImportSetting.CLEAR_COMBAT_HISTORY,
						CharacterImportSetting.CLEAR_SEX_HISTORY,
						CharacterImportSetting.REMOVE_RACE_CONCEALED,
						CharacterImportSetting.CLEAR_FAMILY_ID);
				try {
					if(((Element)((Element)((Element)characterElement.getElementsByTagName("character").item(0)).getElementsByTagName("core").item(0)).getElementsByTagName("id").item(0)).getAttribute("value").equals("PlayerCharacter")) {
						importedLodger.setBirthday(importedLodger.getBirthday().plusYears(18)); // If the imported character is a player character, they need to have their age adjusted to fit with the fact that NPCs start at age 18
					}
				} catch(Exception ex) {
				}
				Main.game.addNPC(importedLodger, false);
				importedLodger.applyNewlyImportedLodgerVariables();

				handleCharacterImportImages(importedLodger, name);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	public static GameCharacter importCharacterAsClubber(String name) {
		File file = new File("data/characters/"+name+".xml");
		
		if (file.exists()) {
			try {
				Document doc = Main.getDocBuilder().parse(file);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element characterElement = (Element) doc.getElementsByTagName("exportedCharacter").item(0);
				if(characterElement == null) {
					characterElement = (Element) doc.getElementsByTagName("playerCharacter").item(0);
				}
				
				// Load NPCs:
				ClubberImport importedClubber = new ClubberImport();
				importedClubber.loadFromXML(characterElement, doc,
						CharacterImportSetting.NO_PREGNANCY,
						CharacterImportSetting.NO_COMPANIONS,
						CharacterImportSetting.NO_ELEMENTAL,
						CharacterImportSetting.CLEAR_SLAVERY,
						CharacterImportSetting.NO_LOCATION_SETUP,
						CharacterImportSetting.CLEAR_KEY_ITEMS,
						CharacterImportSetting.CLEAR_COMBAT_HISTORY,
						CharacterImportSetting.CLEAR_SEX_HISTORY,
						CharacterImportSetting.REMOVE_RACE_CONCEALED,
						CharacterImportSetting.CLEAR_FAMILY_ID);
				try {
					if(((Element)((Element)((Element)characterElement.getElementsByTagName("character").item(0)).getElementsByTagName("core").item(0)).getElementsByTagName("id").item(0)).getAttribute("value").equals("PlayerCharacter")) {
						importedClubber.setBirthday(importedClubber.getBirthday().plusYears(18)); // If the imported character is a player character, they need to have their age adjusted to fit with the fact that NPCs start at age 18
					}
				} catch(Exception ex) {
				}
				Main.game.addNPC(importedClubber, false);
				importedClubber.applyNewlyImportedClubberVariables();

				handleCharacterImportImages(importedClubber, name);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public static void exportGame(String exportFileName, boolean allowOverwrite, boolean isAutoSave) {
		
		File dir = new File("data/");
		dir.mkdir();

		dir = new File("data/saves");
		dir.mkdir();
		
		boolean overwrite = false;
		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, filename) -> filename.endsWith(".xml"));
			if (directoryListing != null) {
				for (File child : directoryListing) {
					if (child.getName().equals(exportFileName+".xml")){
						if(!allowOverwrite) {
							Main.game.flashMessage(PresetColour.GENERIC_BAD, "Name already exists!");
							return;
						} else {
							overwrite = true;
						}
					}
				}
			}
		}

		if(timeLog) {
			timeStart = System.nanoTime();
			System.out.println(timeStart);
		}
		// Starting stuff:

		Document doc = Main.getDocBuilder().newDocument();

		// Writing game stuff to export:

		Element game = doc.createElement("game");
		doc.appendChild(game);

		try {
			Element informationNode = doc.createElement("coreInfo");
			game.appendChild(informationNode);
			XMLUtil.addAttribute(doc, informationNode, "version", Main.VERSION_NUMBER);
			XMLUtil.addAttribute(doc, informationNode, "id", String.valueOf(Main.game.id));
			XMLUtil.addAttribute(doc, informationNode, "lastAutoSaveTime", String.valueOf(Main.game.lastAutoSaveTime));
			XMLUtil.addAttribute(doc, informationNode, "secondsPassed", String.valueOf(Main.game.secondsPassed));
			XMLUtil.addAttribute(doc, informationNode, "weather", Main.game.currentWeather.toString());
			XMLUtil.addAttribute(doc, informationNode, "nextStormTimeInSeconds", String.valueOf(Main.game.nextStormTimeInSeconds));
			XMLUtil.addAttribute(doc, informationNode, "gatheringStormDurationInSeconds", String.valueOf(Main.game.gatheringStormDurationInSeconds));
			XMLUtil.addAttribute(doc, informationNode, "weatherTimeRemainingInSeconds", String.valueOf(Main.game.weatherTimeRemainingInSeconds));

			Element inventoryNode = doc.createElement("savedInventories");
			game.appendChild(inventoryNode);
			for(Entry<String, CharacterInventory> entry : savedInventories.entrySet()) {
				Element element = doc.createElement("savedInventory");
				XMLUtil.addAttribute(doc, element, "character", entry.getKey());
				inventoryNode.appendChild(element);
				entry.getValue().saveAsXML(element, doc);
			}

			Element savedEnforcersNode = doc.createElement("savedEnforcers");
			game.appendChild(savedEnforcersNode);
			for(Entry<AbstractWorldType, List<List<String>>> entrySet : Main.game.savedEnforcers.entrySet()) {
				Element element = doc.createElement("world");
				savedEnforcersNode.appendChild(element);
				XMLUtil.addAttribute(doc, element, "type", WorldType.getIdFromWorldType(entrySet.getKey()));
				for(List<String> ids : entrySet.getValue()) {
					Element enforcersElement = doc.createElement("enforcers");
					element.appendChild(enforcersElement);
					for(String s : ids) {
						Element idElement = doc.createElement("id");
						enforcersElement.appendChild(idElement);
						idElement.setTextContent(s);
					}
				}
			}

			try {
				Main.game.getOccupancyUtil().saveAsXML(game, doc);
			}catch(Exception ex) {
				System.err.println("SlaveryUtil saving failed!");
				Main.game.addEvent(new EventLogEntry("<style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail<b>", "SlaveryUtil failure"), false);
			}

			Element dateNode = doc.createElement("date");
			informationNode.appendChild(dateNode);
			XMLUtil.addAttribute(doc, dateNode, "year", String.valueOf(Main.game.startingDate.getYear()));
			XMLUtil.addAttribute(doc, dateNode, "month", String.valueOf(Main.game.startingDate.getMonthValue()));
			XMLUtil.addAttribute(doc, dateNode, "dayOfMonth", String.valueOf(Main.game.startingDate.getDayOfMonth()));
			XMLUtil.addAttribute(doc, dateNode, "hour", String.valueOf(Main.game.startingDate.getHour()));
			XMLUtil.addAttribute(doc, dateNode, "minute", String.valueOf(Main.game.startingDate.getMinute()));
		} catch(Exception ex) {
			System.err.println("coreInfo saving failed!");
			Main.game.addEvent(new EventLogEntry("<style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail<b>", "coreInfo failure"), false);
		}

		Main.game.dialogueFlags.saveAsXML(game, doc);

		try {
			Element eventLogNode = doc.createElement("eventLog");
			game.appendChild(eventLogNode);
			for(EventLogEntry event : Main.game.getEventLog()) {
				event.saveAsXML(eventLogNode, doc);
			}
		} catch(Exception ex) {
			System.err.println("eventLog saving failed!");
			Main.game.addEvent(new EventLogEntry("<style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail<b>", "eventLog failure"), false);
		}

		try {
			Element slaveryEventLogNode = doc.createElement("slaveryEventLog");
			game.appendChild(slaveryEventLogNode);
			for(Value<Integer, List<SlaveryEventLogEntry>> entry : Main.game.getSlaveryEventLog()) {
				Element element = doc.createElement("day");
				slaveryEventLogNode.appendChild(element);
				XMLUtil.addAttribute(doc, element, "value", String.valueOf(entry.getKey()));
				for(SlaveryEventLogEntry event : entry.getValue()) {
					event.saveAsXML(element, doc);
				}
			}
		} catch(Exception ex) {
			System.err.println("slaveryEventLog saving failed!");
			Main.game.addEvent(new EventLogEntry("<style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail<b>", "slaveryEventLog failure"), false);
		}

		// Add maps:
		try {
			Element mapNode = doc.createElement("maps");
			game.appendChild(mapNode);
			for(World world : Main.game.getWorlds().values()) {
//					if(world!=null && world.getWorldType()!=WorldType.WORLD_MAP) { // Do not save world map, as it is for all intents and purposes immutable.
					world.saveAsXML(mapNode, doc);
//					}
			}
		} catch(Exception ex) {
			System.err.println("maps saving failed!");
			Main.game.addEvent(new EventLogEntry("<style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail<b>", "maps failure"), false);
		}

		// Add player:
		try {
			Element characterNode = doc.createElement("playerCharacter");
			game.appendChild(characterNode);
			Main.game.getPlayer().saveAsXML(characterNode, doc);
		} catch(Exception ex) {
			System.err.println("playerCharacter saving failed!");
			Main.game.addEvent(new EventLogEntry("<style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail<b>", "playerCharacter failure"), false);
		}

		// Add all NPCs:
		try {
			for(GameCharacter character : Main.game.getNPCMap().values()) {
				Element characterNode = doc.createElement("NPC");
				game.appendChild(characterNode);
				character.saveAsXML(characterNode, doc);
			}
		} catch(Exception ex) {
			System.err.println("NPC saving failed!");
			ex.printStackTrace();
			Main.game.addEvent(new EventLogEntry("<style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail<b>", "NPC failure"), false);
		}

		// Add all offspringSeed:
		try {
			for(OffspringSeed offspringSeed : Main.game.getOffspringSeedMap().values()) {
				Element characterNode = doc.createElement("OffspringSeed");
				game.appendChild(characterNode);
				offspringSeed.saveAsXML(characterNode, doc);
			}
		} catch(Exception ex) {
			System.err.println("offspringSeed saving failed!");
			ex.printStackTrace();
			Main.game.addEvent(new EventLogEntry("<style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail<b>", "offspringSeed failure"), false);
		}

		// Ending stuff:
		try {
			Transformer transformer1 = Main.transformerFactory.newTransformer();
			transformer1.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();

			transformer1.transform(new DOMSource(doc), new StreamResult(writer));

			// Save this xml:
			Transformer transformer = Main.transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);

			String saveLocation = "data/saves/"+exportFileName+".xml";
			StreamResult result = new StreamResult(saveLocation);

			transformer.transform(source, result);

			if(!isAutoSave) {
				if(overwrite) {
					Main.game.addEvent(new EventLogEntry("[style.colourGood(Game saved)]", saveLocation), false);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()), false, PresetColour.GENERIC_GOOD, "Save game overwritten!");
				} else {
					Main.game.addEvent(new EventLogEntry("[style.colourGood(Game saved)]", saveLocation), false);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()), false, PresetColour.GENERIC_GOOD, "Game saved!");
				}
			}
		} catch(Exception ex) {
			System.err.println("XML writing failed!");
			ex.printStackTrace();
			Main.game.addEvent(new EventLogEntry("<style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail</span>", "XML writing failure"), false);
		}

		if(timeLog) {
			System.out.println("Difference: "+(System.nanoTime()-timeStart)/1000000000f);
		}
	}
	
	private static boolean debug = false;

	public static void importGame(String name) {
		File file = new File("data"+System.getProperty("file.separator")+"saves"+System.getProperty("file.separator"), name+".xml");

		importGame(file);
	}
	
	public static void importGame(File file) {
		Main.game = new Game();
		UtilText.initScriptEngine(); // Have to init the script engine before loading game variables as some classes (such as race) call parsing as part of their initialisation (Race's 'applyRaceChanges')
		
		if (file.exists()) {
			try {
				Document doc = Main.getDocBuilder().parse(file);

				long time = System.nanoTime();
				if(debug) {
					System.out.println("Load game start");
				}
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element gameElement = (Element) doc.getElementsByTagName("game").item(0);
				
				Element informationNode = (Element) gameElement.getElementsByTagName("coreInfo").item(0);
				
				loadingVersion = informationNode.getAttribute("version");
				
				if(!informationNode.getAttribute("id").isEmpty()) {
					Main.game.id = Long.valueOf(informationNode.getAttribute("id"));
				}
				
				if(!informationNode.getAttribute("lastAutoSaveTime").isEmpty()) {
					Main.game.lastAutoSaveTime = Long.valueOf(informationNode.getAttribute("lastAutoSaveTime"));
				}
				
				if(!informationNode.getAttribute("minutesPassed").isEmpty()) { // Support for before time was converted from minutes to seconds:
					Main.game.secondsPassed = Long.valueOf(informationNode.getAttribute("minutesPassed"))*60;
					Main.game.nextStormTimeInSeconds = Long.valueOf(informationNode.getAttribute("nextStormTime"))*60;
					try {
						Main.game.gatheringStormDurationInSeconds = Integer.valueOf(informationNode.getAttribute("gatheringStormDuration"))*60;
					} catch(Exception ex) {
					}
					Main.game.weatherTimeRemainingInSeconds = Integer.valueOf(informationNode.getAttribute("weatherTimeRemaining"))*60;
					
				} else {
					Main.game.secondsPassed = Long.valueOf(informationNode.getAttribute("secondsPassed"));
					Main.game.nextStormTimeInSeconds = Long.valueOf(informationNode.getAttribute("nextStormTimeInSeconds"));
					try {
						Main.game.gatheringStormDurationInSeconds = Integer.valueOf(informationNode.getAttribute("gatheringStormDurationInSeconds"));
					} catch(Exception ex) {
					}
					Main.game.weatherTimeRemainingInSeconds = Integer.valueOf(informationNode.getAttribute("weatherTimeRemainingInSeconds"));
				}
				Main.game.currentWeather = Weather.valueOf(informationNode.getAttribute("weather"));

				try {
					Element slaveryNode = (Element) gameElement.getElementsByTagName("slavery").item(0);
					OccupancyUtil occupancyUtil = OccupancyUtil.loadFromXML(slaveryNode, doc);
					if(occupancyUtil!=null) {
						Main.game.occupancyUtil = occupancyUtil;
					}
				} catch(Exception ex) {
				}
				
				
				// Saved inventories:
				Element inventoryNode = (Element) gameElement.getElementsByTagName("savedInventories").item(0);
				if(inventoryNode!=null) {
					NodeList nodes = inventoryNode.getElementsByTagName("savedInventory");
					for (int i=0; i < nodes.getLength(); i++) {
						Element savedInventory = (Element) nodes.item(i);
						String id = savedInventory.getAttribute("character");
						CharacterInventory inventory = CharacterInventory.loadFromXML((Element) savedInventory.getElementsByTagName("characterInventory").item(0), doc);
						savedInventories.put(id, inventory);
					}
				}
				
				Element savedEnforcersNode = (Element) gameElement.getElementsByTagName("savedEnforcers").item(0);
				Main.game.savedEnforcers = new HashMap<>();
				if(savedEnforcersNode!=null) {
					NodeList nodes = savedEnforcersNode.getElementsByTagName("world");
					for (int i=0; i < nodes.getLength(); i++) {
						Element world = (Element) nodes.item(i);
						AbstractWorldType worldType = WorldType.getWorldTypeFromId(world.getAttribute("type"));
						
						List<List<String>> loadedEnforcers = new ArrayList<>();
						
						NodeList enforcerNodes = world.getElementsByTagName("enforcers");
						for (int j=0; j < enforcerNodes.getLength(); j++) {
							Element enforcerIds = (Element) enforcerNodes.item(j);
							
							NodeList idNodes = enforcerIds.getElementsByTagName("id");
							List<String> ids = new ArrayList<>();
							for(int k=0; k < idNodes.getLength(); k++) {
								ids.add(((Element)idNodes.item(k)).getTextContent());
							}
							loadedEnforcers.add(ids);
						}
						Main.game.savedEnforcers.put(worldType, loadedEnforcers);
					}
				}
				
				
				// Date:
				
				Element dateNode = (Element) gameElement.getElementsByTagName("date").item(0);
				Main.game.startingDate = LocalDateTime.of(
						Integer.valueOf(dateNode.getAttribute("year")),
						Integer.valueOf(dateNode.getAttribute("month")),
						Integer.valueOf(dateNode.getAttribute("dayOfMonth")),
						Integer.valueOf(dateNode.getAttribute("hour")),
						Integer.valueOf(dateNode.getAttribute("minute")));
				
				Main.game.dialogueFlags = DialogueFlags.loadFromXML((Element) gameElement.getElementsByTagName("dialogueFlags").item(0), doc);
				
				NodeList eventLogEntryElements = ((Element) gameElement.getElementsByTagName("eventLog").item(0)).getElementsByTagName("eventLogEntry");
				for(int i = 0; i < eventLogEntryElements.getLength(); i++){
					Element e = (Element) eventLogEntryElements.item(i);
					Main.game.addEvent(EventLogEntry.loadFromXML(e, doc), false);
				}
				
				
				NodeList nodes = gameElement.getElementsByTagName("slaveryEventLog");
				Element extraEffectNode = (Element) nodes.item(0);
				if(extraEffectNode != null) {
					NodeList slaveryDayLogElements = extraEffectNode.getElementsByTagName("day");
					for(int i = 0; i < slaveryDayLogElements.getLength() && i < 7; i++){
						Element e = (Element) gameElement.getElementsByTagName("day").item(i);
						int day = Integer.valueOf(e.getAttribute("value"));
						Main.game.slaveryEventLog.push(new Value<>(day, new ArrayList<>()));
//						System.out.println(day+": added");
						
						NodeList dayEventLogElements = e.getElementsByTagName("eventLogEntry");
						for(int j = 0; j < dayEventLogElements.getLength(); j++){
							Element entry = (Element) dayEventLogElements.item(j);
							try {
								Main.game.addSlaveryEvent(day, SlaveryEventLogEntry.loadFromXML(entry, doc));
							} catch(Exception ex) {
							}
//							System.out.println(j);
						}
					}
				}
				
				if(debug) {
					System.out.println("Core info finished: "+ (System.nanoTime()-time)/1000000000d);
				}
				
				// Maps:
				NodeList worlds = ((Element) gameElement.getElementsByTagName("maps").item(0)).getElementsByTagName("world");
				for(int i = 0; i < worlds.getLength(); i++) {
					Element e = (Element) worlds.item(i);
					String worldType = e.getAttribute("worldType");
					if((!worldType.equals("SEWERS") || !Main.isVersionOlderThan(loadingVersion, "0.2.0.5"))
							&& (!worldType.equals("SUBMISSION") || !Main.isVersionOlderThan(loadingVersion, "0.2.10.8"))
							&& ((!worldType.equals("IMP_FORTRESS_ALPHA")
									&& !worldType.equals("IMP_FORTRESS_FEMALES")
									&& !worldType.equals("IMP_FORTRESS_MALES"))
									|| !Main.isVersionOlderThan(loadingVersion, "0.2.11"))
							&& (!worldType.equals("IMP_FORTRESS_DEMON") || !Main.isVersionOlderThan(loadingVersion, "0.2.12.5"))
							&& (!worldType.equals("DOMINION") || !Main.isVersionOlderThan(loadingVersion, "0.2.2"))
							&& (!worldType.equals("HARPY_NEST") || !Main.isVersionOlderThan(loadingVersion, "0.2.1.5"))
							&& (!worldType.equals("BAT_CAVERNS") || !Main.isVersionOlderThan(loadingVersion, "0.2.3.5"))
							&& (!worldType.equals("LYSSIETH_PALACE") || !Main.isVersionOlderThan(loadingVersion, "0.3"))
							&& (!worldType.equals("RAT_WARRENS") || !Main.isVersionOlderThan(loadingVersion, "0.3.5.6"))
							&& (!worldType.equals("DOMINION_EXPRESS") || !Main.isVersionOlderThan(loadingVersion, "0.3.7.9"))
							&& (!worldType.equals("SHOPPING_ARCADE") || !Main.isVersionOlderThan(loadingVersion, "0.3.14"))
							&& (!worldType.equals("innoxia_fields_elis_market") || !Main.isVersionOlderThan(loadingVersion, "0.4.1.1"))
							&& (!worldType.equals("innoxia_fields_elis_tavern_alley") || !Main.isVersionOlderThan(loadingVersion, "0.4.4"))
							&& (!worldType.equals("innoxia_fields_themiscyra") || !Main.isVersionOlderThan(loadingVersion, "0.4.4.5"))
							&& (!worldType.equals("EMPTY") || !Main.isVersionOlderThan(loadingVersion, "0.4.5.7"))
							&& (!worldType.equals("SLAVER_ALLEY") || !Main.isVersionOlderThan(loadingVersion, "0.4.5.7"))
							&& (!worldType.equals("innoxia_fields_elis_market") || !Main.isVersionOlderThan(loadingVersion, "0.4.8.7"))
							&& !worldType.equals("SUPPLIER_DEN") // Removed
							&& !worldType.equals("JUNGLE") // Removed
//                          && !worldType.equals("REBEL_BASE")
							) {
						World world = World.loadFromXML(e, doc);
						Main.game.worlds.put(world.getWorldType(), world);
					}
				}
				
				// Add missing world types:
				Generation gen = new Generation();
//				if(Main.isVersionOlderThan(loadingVersion, "0.1.99.5")) { 
//					Main.game.getWorlds().put(WorldType.SHOPPING_ARCADE, gen.worldGeneration(WorldType.SHOPPING_ARCADE));
//				}
				if(Main.isVersionOlderThan(loadingVersion, "0.2.1.5")) {
					Main.game.getWorlds().put(WorldType.HARPY_NEST, gen.worldGeneration(WorldType.HARPY_NEST));
				}
				if(Main.isVersionOlderThan(loadingVersion, "0.2.10.8")) {
					Main.game.getWorlds().put(WorldType.SUBMISSION, gen.worldGeneration(WorldType.SUBMISSION));
				}
				if(Main.isVersionOlderThan(loadingVersion, "0.2.11")) {
					Main.game.getWorlds().put(WorldType.IMP_FORTRESS_ALPHA, gen.worldGeneration(WorldType.IMP_FORTRESS_ALPHA));
					Main.game.getWorlds().put(WorldType.IMP_FORTRESS_FEMALES, gen.worldGeneration(WorldType.IMP_FORTRESS_FEMALES));
					Main.game.getWorlds().put(WorldType.IMP_FORTRESS_MALES, gen.worldGeneration(WorldType.IMP_FORTRESS_MALES));
				}
				if(Main.isVersionOlderThan(loadingVersion, "0.2.12.5")) {
					Main.game.getWorlds().put(WorldType.IMP_FORTRESS_DEMON, gen.worldGeneration(WorldType.IMP_FORTRESS_DEMON));
				}
				if(Main.isVersionOlderThan(loadingVersion, "0.2.2")) {
					Main.game.getWorlds().put(WorldType.DOMINION, gen.worldGeneration(WorldType.DOMINION));
				}
				if(Main.isVersionOlderThan(loadingVersion, "0.2.3.5")) {
					Main.game.getWorlds().put(WorldType.BAT_CAVERNS, gen.worldGeneration(WorldType.BAT_CAVERNS));
				}
				if(Main.isVersionOlderThan(loadingVersion, "0.2.8")) {
					Main.game.getWorlds().put(WorldType.NIGHTLIFE_CLUB, gen.worldGeneration(WorldType.NIGHTLIFE_CLUB));
				}
				if(Main.isVersionOlderThan(loadingVersion, "0.2.8.1")) {
					Main.game.getWorlds().put(WorldType.EMPTY, gen.worldGeneration(WorldType.EMPTY));
				}
				if(Main.isVersionOlderThan(loadingVersion, "0.3")) {
					Main.game.getWorlds().put(WorldType.LYSSIETH_PALACE, gen.worldGeneration(WorldType.LYSSIETH_PALACE));
				}
				if(Main.isVersionOlderThan(loadingVersion, "0.3.2.2")) {
					Main.game.getWorlds().put(WorldType.CITY_HALL, gen.worldGeneration(WorldType.CITY_HALL));
				}
				if(Main.isVersionOlderThan(loadingVersion, "0.3.4.9")) {
					Main.game.getWorlds().put(WorldType.ENFORCER_WAREHOUSE, gen.worldGeneration(WorldType.ENFORCER_WAREHOUSE));
				}
				if(Main.isVersionOlderThan(loadingVersion, "0.3.5.4")) {
					Main.game.getWorlds().put(WorldType.GAMBLING_DEN, gen.worldGeneration(WorldType.GAMBLING_DEN));
				}
				if(Main.isVersionOlderThan(loadingVersion, "0.3.5.6")) {
					Main.game.getWorlds().put(WorldType.RAT_WARRENS, gen.worldGeneration(WorldType.RAT_WARRENS));
					Main.game.getWorlds().put(WorldType.SLAVER_ALLEY, gen.worldGeneration(WorldType.SLAVER_ALLEY));
				}
				if(Main.isVersionOlderThan(loadingVersion, "0.3.7")) {
					Main.game.getWorlds().put(WorldType.HELENAS_APARTMENT, gen.worldGeneration(WorldType.HELENAS_APARTMENT));
				}
				if(Main.isVersionOlderThan(loadingVersion, "0.3.7.9")) {
					Main.game.getWorlds().put(WorldType.DOMINION_EXPRESS, gen.worldGeneration(WorldType.DOMINION_EXPRESS));
				}
				if(Main.isVersionOlderThan(loadingVersion, "0.3.9.4")) {
					Main.game.getWorlds().put(WorldType.ENFORCER_HQ, gen.worldGeneration(WorldType.ENFORCER_HQ));
				}
				if(Main.isVersionOlderThan(loadingVersion, "0.4.0.5")) {
					Main.game.getWorlds().put(WorldType.getWorldTypeFromId("innoxia_fields_elis_town"), gen.worldGeneration(WorldType.getWorldTypeFromId("innoxia_fields_elis_town")));
					Main.game.getWorlds().put(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_f1"), gen.worldGeneration(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_f1")));
				}
				if(Main.isVersionOlderThan(loadingVersion, "0.4.3.9")) {
					Main.game.getWorlds().put(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_alley"), gen.worldGeneration(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_alley")));
				}
				if(Main.isVersionOlderThan(loadingVersion, "0.4.5.7")) {
					Main.game.getWorlds().put(WorldType.EMPTY, gen.worldGeneration(WorldType.EMPTY));
				}
				if(Main.isVersionOlderThan(loadingVersion, "0.4.5.7")) {
					Main.game.getWorlds().put(WorldType.SLAVER_ALLEY, gen.worldGeneration(WorldType.SLAVER_ALLEY));
				}
				if(Main.isVersionOlderThan(loadingVersion, "0.4.7.11")) {
					Main.game.getWorlds().put(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_taur"), gen.worldGeneration(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_taur")));
				}
				for(AbstractWorldType wt : WorldType.getAllWorldTypes()) {
					if(Main.game.worlds.get(wt)==null) {
						Main.game.getWorlds().put(wt, gen.worldGeneration(wt));
					}
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.3.6.9")) {
					// Add home improvements:
					Vector2i vec = Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_SLAVER_ALLEY).getLocation();
					vec.setX(vec.getX()+1);
					vec.setY(vec.getY()-5);
					Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setPlaceType(PlaceType.DOMINION_HOME_IMPROVEMENT);
					Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setName(PlaceType.DOMINION_HOME_IMPROVEMENT.getName());
					Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).setDiscovered(true);
					
					// Add warehouse district:
					vec = Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_SLAVER_ALLEY).getLocation();
					vec.setX(vec.getX()+1);
					vec.setY(vec.getY()+4);
					Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setPlaceType(PlaceType.DOMINION_WAREHOUSES);
					Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setName(PlaceType.DOMINION_WAREHOUSES.getName());
					Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).setDiscovered(true);
					
					// Add antiques:
					vec = Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(PlaceType.SHOPPING_ARCADE_NYANS_SHOP).getLocation();
					vec.setX(vec.getX()+1);
					vec.setY(vec.getY()-1);
					Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(vec).getPlace().setPlaceType(PlaceType.SHOPPING_ARCADE_ANTIQUES);
					Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(vec).getPlace().setName(PlaceType.SHOPPING_ARCADE_ANTIQUES.getName());
					Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(vec).setDiscovered(true);
					
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.2.4")) {
					AbstractItem spellBook = Main.game.getItemGen().generateItem(ItemType.getSpellBookType(Spell.ICE_SHARD));
					Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCell(PlaceType.LILAYA_HOME_ROOM_PLAYER).getInventory().addItem(spellBook);
				}
				if(Main.isVersionOlderThan(loadingVersion, "0.3.8.8")) {
					List<Vector2i> vecs = Util.newArrayListOfValues(
							new Vector2i(2, 5),
							new Vector2i(5, 8),
							new Vector2i(8, 5));
					for(Vector2i vec : vecs) {
						Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(vec).getPlace().setPlaceType(PlaceType.LILAYA_HOME_STAIR_UP_SECONDARY);
						Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(vec).getPlace().setName(PlaceType.LILAYA_HOME_STAIR_UP_SECONDARY.getName());
						Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(vec).setDiscovered(true);
						Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCell(vec).getPlace().setPlaceType(PlaceType.LILAYA_HOME_STAIR_DOWN_SECONDARY);
						Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCell(vec).getPlace().setName(PlaceType.LILAYA_HOME_STAIR_DOWN_SECONDARY.getName());
						Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCell(vec).setDiscovered(true);
					}
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.4.7.2")) {
					// Add shaft tile:
					Vector2i vec = Main.game.getWorlds().get(WorldType.BAT_CAVERNS).getCell(PlaceType.BAT_CAVERN_SLIME_QUEEN_LAIR).getLocation();
					vec.setY(vec.getY()-3);
					Main.game.getWorlds().get(WorldType.BAT_CAVERNS).getCell(vec).getPlace().setPlaceType(PlaceType.BAT_CAVERN_SHAFT);
					Main.game.getWorlds().get(WorldType.BAT_CAVERNS).getCell(vec).getPlace().setName(PlaceType.BAT_CAVERN_SHAFT.getName());
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.4.7.7")) {
					// Add bank to Dominion tile:
					Vector2i vec = Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_SHOPPING_ARCADE).getLocation();
					vec.setY(vec.getY()+2);
					vec.setX(vec.getX()-1);
					Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setPlaceType(PlaceType.DOMINION_BANK);
					Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setName(PlaceType.DOMINION_BANK.getName());
				}
				
				if(debug) {
					System.out.println("Maps finished: "+ (System.nanoTime()-time)/1000000000d);
				}
				
				Main.game.player = PlayerCharacter.loadFromXML(null, (Element) ((Element) gameElement.getElementsByTagName("playerCharacter").item(0)), doc);
				
				if(debug) {
					System.out.println("Player finished: "+ (System.nanoTime()-time)/1000000000d);
				}

				// Load NPCs:
				NodeList npcs = gameElement.getElementsByTagName("NPC");
				Map<String, Class<? extends NPC>> npcClasses = new ConcurrentHashMap<>();
				Map<Class<? extends NPC>, Method> loadFromXMLMethods = new ConcurrentHashMap<>();
				Map<Class<? extends NPC>, Constructor<? extends NPC>> constructors = new ConcurrentHashMap<>();
				int totalNpcCount = npcs.getLength();
				IntStream.range(0,totalNpcCount).parallel().mapToObj(i -> ((Element) npcs.item(i)))
						.forEach(e ->{
							String id = ((Element)e.getElementsByTagName("id").item(0)).getAttribute("value");
							if(!Main.game.NPCMap.containsKey(id)) {
								String className = ((Element)e.getElementsByTagName("pathName").item(0)).getAttribute("value");
								if(Main.isVersionOlderThan(loadingVersion, "0.2.4")) {
									int lastIndex = className.lastIndexOf('.');
									if(className.substring(lastIndex-3, lastIndex).equals("npc")) {
										className = className.substring(0, lastIndex) + ".misc" + className.substring(lastIndex, className.length());
									}
								}
								if(Main.isVersionOlderThan(loadingVersion, "0.4.1.5")) {
									className = className.replace("SubmissionCitadelArcanist", "Takahashi");
								}
								if(Main.isVersionOlderThan(loadingVersion, "0.4")) {
									className = className.replace("BatMorphCavernAttacker", "BatCavernLurkerAttacker");
									className = className.replace("SlimeCavernAttacker", "BatCavernSlimeAttacker");
								}
								if(Main.isVersionOlderThan(loadingVersion, "0.3")) {
									className = className.replace("FortressDemonLeader", "DarkSiren");
								}

								if(!Main.isVersionOlderThan(loadingVersion, "0.3.5.9") || !id.contains("Helena")) {
									if(Main.isVersionOlderThan(loadingVersion, "0.3.5.9")) {
										className = className.replace("Alexa", "Helena");
									}
									NPC npc = loadNPC(doc, e, className, npcClasses, loadFromXMLMethods, constructors);
									if(npc!=null)  {
										if(!Main.isVersionOlderThan(loadingVersion, "0.2.11.5")
												|| (npc.getClass()!=DarkSiren.class
												&& npc.getClass()!=FortressAlphaLeader.class
												&& npc.getClass()!=FortressMalesLeader.class
												&& npc.getClass()!=FortressFemalesLeader.class)) {
											Main.game.safeAddNPC(npc, true);
										}

										// To fix issues with older versions hair length:
										if(Main.isVersionOlderThan(loadingVersion, "0.1.90.5")) {
											npc.getBody().getHair().setLength(null, npc.isFeminine()?RacialBody.valueOfRace(npc.getRace()).getFemaleHairLength():RacialBody.valueOfRace(npc.getRace()).getMaleHairLength());
										}
										// Generate desires in non-unique NPCs:
										if(Main.isVersionOlderThan(loadingVersion, "0.1.98.5") && !npc.isUnique() && npc.getFetishDesireMap().isEmpty()) {
											Main.game.getCharacterUtils().generateDesires(npc);
										}

										if(Main.isVersionOlderThan(loadingVersion, "0.2.0") && npc.getFetishDesireMap().size()>10) {
											npc.clearFetishDesires();
											Main.game.getCharacterUtils().generateDesires(npc);
										}
										if(Main.isVersionOlderThan(loadingVersion, "0.3.5.4") && npc.getWorldLocation()==WorldType.GAMBLING_DEN) {
											if(npc instanceof Roxy) {
												npc.setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_TRADER, true);

											} else if(npc instanceof Axel) {
												npc.setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE, true);

											} else if(npc instanceof Epona) {
												npc.setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE, true);

											} else {
												npc.setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_GAMBLING, true);
											}
										}

									} else {
										System.err.println("LOADNPC returned null: "+id);
										System.err.println("CLASS: " + className);
									}
								}
							} else {
								if(!id.contains("Helena")) {
									System.err.println("duplicate character attempted to be imported");
								}
							}
						});

				// Load offspringSeed:
				NodeList offspringSeedList = gameElement.getElementsByTagName("OffspringSeed");
				if(offspringSeedList.getLength()>0) {
					for(int i = 0; i < offspringSeedList.getLength(); i++){
						Element e = (Element) offspringSeedList.item(i);
						Main.game.addOffspringSeed(OffspringSeed.loadFromXML(e, doc), true);
					}
				}

				if(debug) {
					System.out.println("OffspringSeed finished: "+ (System.nanoTime()-time)/1000000000d);
				}

				int OSConverstions = 0;
				if(Main.isVersionOlderThan(loadingVersion, "0.4.2.4")) {
					for(NPC npc: Main.game.getAllNPCs()) {
						if(npc instanceof NPCOffspring &&
						   npc.getLocationPlace().getPlaceType()==PlaceType.GENERIC_HOLDING_CELL &&
						   npc.getHomeLocationPlace().getPlaceType()==PlaceType.GENERIC_HOLDING_CELL) {
							// Remove this npc and replace with offspringSeed.
							// new OffspringSeed(npc) calls the following methods to do this:
								// Main.game.getOffspring().remove(npc);
								// Main.game.removeNPC(npc);
							new OffspringSeed(npc);
							OSConverstions++;
						}
					}
					if(debug) {
						System.out.println("OSConversions count: " + OSConverstions);
					}
				}

				if(debug) {
					System.out.println("Convert NPC finished: "+ (System.nanoTime()-time)/1000000000d);
				}

				// Add in new NPCS:
				Main.game.initUniqueNPCs();

				//TODO This needs more thorough testing...
				// In versions prior to v0.4.1, deleted NPCs who had relationship or sex data with the player were moved to an empty tile instead of being deleted.
				// This was causing save file bloat, so now they are fully deleted.
				for(NPC npc: Main.game.getAllNPCs()) {
					if(npc!=null
						&& npc.getLocationPlace().getPlaceType() == PlaceType.GENERIC_EMPTY_TILE
						&& npc.isReadyToBeDeleted()) {
						Main.game.banishNPC(npc);
						System.out.println("Deleted NPC: "+npc.getId());
					}
				}

				if(debug) {
					System.out.println("NPCs finished: "+ (System.nanoTime()-time)/1000000000d);
				}

				if(Main.isVersionOlderThan(loadingVersion, "0.2.8")) { // Fix for incorrect positioning bug in an old version:
					Main.game.getNpc(Jules.class).setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_ENTRANCE);
					Main.game.getNpc(Kruger.class).setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_VIP_AREA);
					Main.game.getNpc(Kalahari.class).setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_BAR);
				}
				
				// To prevent errors from previous versions, reset Zaranix progress if prior to 0.1.95:
				if(Main.isVersionOlderThan(loadingVersion, "0.1.90.5")) {
					if(Main.game.getPlayer().getWorldLocation() == WorldType.ZARANIX_HOUSE_GROUND_FLOOR
							|| Main.game.getPlayer().getWorldLocation() == WorldType.ZARANIX_HOUSE_FIRST_FLOOR) {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME, false);
						
						ZaranixHomeGroundFloor.resetHouseAfterLeaving();
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKickedDownDoor, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKnockedOnDoor, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixMaidsHostile, false);
						
						Main.game.getNpc(Arthur.class).setLocation(WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_OFFICE, true);
						
						if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
							Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE);
						}
					}
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.1.95")) {
					if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
						Main.game.getNpc(Arthur.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
					}
				}

				if(Main.isVersionOlderThan(loadingVersion, "0.2.4.5")) { //Try to remove unused NPCs TODO better method to delete all banished NPCs
					for(NPC npc : Main.game.getAllNPCs()) {
						if(!npc.isUnique() && npc.getWorldLocation()==WorldType.EMPTY) {
							Main.game.banishNPC(npc);
						}
					}
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.2.5.1")) { //Reset ass/nipple/lip colours
					for(NPC npc : Main.game.getAllNPCs()) {
						if(!npc.isSlave() || (npc.getOwner()!=null && !npc.getOwner().isPlayer()))
						npc.setSkinCovering(new Covering(npc.getTorsoCovering(), npc.getCovering(npc.getTorsoCovering()).getPrimaryColour()), true);
					}
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.2.5")) { //Add milking rooms
					
					Cell[][] grid = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCellGrid();
					for(int i=0 ; i<grid.length ; i++) {
						for(int j=0 ; j<grid[0].length ; j++) {
							if(grid[i][j].getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM)) {
								Main.game.getOccupancyUtil().addMilkingRoom(new MilkingRoom(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, new Vector2i(i, j)));
							}
						}
					}
					grid = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCellGrid();
					for(int i=0 ; i<grid.length ; i++) {
						for(int j=0 ; j<grid[0].length ; j++) {
							if(grid[i][j].getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM)) {
								Main.game.getOccupancyUtil().addMilkingRoom(new MilkingRoom(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, new Vector2i(i, j)));
							}
						}
					}
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.2.12.6")) { //Reset imp fortresses
					ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_ALPHA);
					ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_FEMALES);
					ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_MALES);

					Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaBossEncountered, false);
					Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesBossEncountered, false);
					Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesBossEncountered, false);
					
					if(Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_ALPHA) {
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_ALPHA);
						
					} else if(Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_FEMALES) {
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_FEMALES);
						
					} else if(Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_MALES) {
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_MALES);
					}
					
					Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY));
					Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_2));
					Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_3));
				}
				if(Main.isVersionOlderThan(loadingVersion, "0.2.12.6")
						|| (Main.isVersionOlderThan(loadingVersion, "0.3.0.5") && !Main.game.getNpc(DarkSiren.class).isSlave() && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated))) {
					if(Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_DEMON) {
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_DEMON);
					}
					ImpCitadelDialogue.resetFortress();
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.2.12.9")) {
					for(NPC npc : Main.game.getAllNPCs()) {
						if(Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId()) && npc.getHomeWorldLocation()==WorldType.DOMINION && !npc.hasJob()) {
							npc.assignNewJob();
						}
						// Set surname to be the same as their mother's line:
						if(npc.getSurname()==null || npc.getSurname().isEmpty()) {
							GameCharacter mother = npc.getMother();
							if(mother!=null) {
								while(mother.getMother()!=null) {
									mother = mother.getMother();
								}
								if(mother.getSurname()!=null && !mother.getSurname().isEmpty()) {
									npc.setSurname(mother.getSurname());
								}
							}
						}
					}
					if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL)) {
						Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.LYSSIETHS_RING));
					}
					if(Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), true)) {
						Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY));
						Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_2));
						Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_3));
					}
					while(Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY))>1) {
						Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY));
					}
					while(Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_2))>1) {
						Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_2));
					}
					while(Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_3))>1) {
						Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_3));
					}
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.3")) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
						Main.game.getNpc(Takahashi.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
					}
					if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL)) {
						Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL);
						if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_LILIN_PALACE)
								|| Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_LILIN_PALACE_GATE)){
							Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_LILIN_PALACE_CAVERN);
						}
					}
					Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_LIBRARY).getInventory().addItem(Main.game.getItemGen().generateItem(ItemType.getLoreBook(Subspecies.HALF_DEMON)));
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.3.0.5")) {
					if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL)) {
						ImpCitadelDialogue.clearFortress(true);
						ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_ALPHA);
						ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_FEMALES);
						ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_MALES);
						((DarkSiren)Main.game.getNpc(DarkSiren.class)).postDefeatReset();
					}
				}
				
				// For affection/incest gains that I missed in v0.3 & v0.3.0.5:
				if(Main.isVersionOlderThan(loadingVersion, "0.3.0.6")) {
					if(Main.game.getNpc(Lilaya.class).getRaceStage()==RaceStage.GREATER) {
						Main.game.getNpc(Lilaya.class).setAffection(Main.game.getNpc(Lyssieth.class), 75);
						Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 50);
					}
					if(Main.game.getNpc(DarkSiren.class).getRaceStage()==RaceStage.GREATER) {
						Main.game.getNpc(DarkSiren.class).setAffection(Main.game.getNpc(Lyssieth.class), 75);
						Main.game.getNpc(DarkSiren.class).addFetish(Fetish.FETISH_INCEST);
					}
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.3.1.2")
						&& Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN)
						&& !Main.game.getNpc(Brax.class).getLocationPlace().getPlaceType().equals(PlaceType.ENFORCER_HQ_RECEPTION_DESK)) {
					// Move Brax to reception desk if he was incorrectly reset to his office (after fixing the bug where NPCs were disappearing):
					Main.game.getNpc(Brax.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_RECEPTION_DESK);
					
					// Remove all NPC offspring who are not related to the player:
					for(NPC npc : new HashSet<>(Main.game.getNPCMap().values())) {
						if(npc instanceof NPCOffspring) {
							if(!npc.isRelatedTo(Main.game.getPlayer())) {
								Main.game.banishNPC(npc);
							}
						}
					}
				}
				
				// Fix overflow of clients in brothel:
				for(Cell cell : Main.game.getWorlds().get(WorldType.ANGELS_KISS_GROUND_FLOOR).getCells(PlaceType.ANGELS_KISS_BEDROOM)) {
					if(Main.isVersionOlderThan(loadingVersion, "0.3.1.7")) {
						for(NPC npc : Main.game.getCharactersPresent(cell)) {
							if(npc instanceof GenericSexualPartner) {
								Main.game.banishNPC(npc);
							}
						}
					}
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.3.3.9")) { // Add in starting Elemental Perks that were overlooked.
					for(NPC npc : Main.game.getAllNPCs()) {
						if((npc.isElemental())) {
							PerkManager.initialisePerks(npc);
						}
					}
					if(Main.game.getPlayer().removeTrait(Perk.JOB_ELDER_LILIN)) {
						Main.game.getPlayer().addTrait(Main.game.getPlayer().getHistory().getAssociatedPerk());
					}
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.3.3.9")) { // Generate new Zaranix tile and remove Arthur tile (if applicable):
					if(!Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)
							&& Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_DEMON_HOME_ZARANIX)==null) {
						((Zaranix) Main.game.getNpc(Zaranix.class)).generateNewTile();
					}
					if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_1_B_DEMON_HOME)
							&& Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_DEMON_HOME_ARTHUR)!=null) {
						Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_DEMON_HOME_ARTHUR).getPlace().setPlaceType(PlaceType.DOMINION_DEMON_HOME);
					}
				}

				if(Main.isVersionOlderThan(loadingVersion, "0.3.4.1")) {
					Cell c = Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_DEMON_HOME_ARTHUR);
					if(c!=null) {
						while(c!=null) {
							c.getPlace().setPlaceType(PlaceType.DOMINION_DEMON_HOME);
							c = Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_DEMON_HOME_ARTHUR);
						}
						((Arthur)Main.game.getNpc(Arthur.class)).generateNewTile();
					}
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.3.4.5")) {
					for(NPC npc : Main.game.getAllNPCs()) {
						if(npc instanceof DominionAlleywayAttacker && ((DominionAlleywayAttacker) npc).isStormAttacker()) { 
							Main.game.banishNPC(npc); // Catch for storm attackers who were stuck on a dominion street tile.
							
						} else {
							if(!npc.isSlave() && !npc.isUnique() && !Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId())) {
								npc.setHomeLocation();
							}
						}
					}
				}

				if(Main.isVersionOlderThan(loadingVersion, "0.3.4.9")) {
					if(!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN)) {
						Main.game.getWorlds().get(WorldType.ENFORCER_HQ).getCell(PlaceType.ENFORCER_HQ_BRAXS_OFFICE).getInventory().clearNonEquippedInventory(false);
						PlaceType.ENFORCER_HQ_BRAXS_OFFICE.applyInventoryInit(Main.game.getWorlds().get(WorldType.ENFORCER_HQ).getCell(PlaceType.ENFORCER_HQ_BRAXS_OFFICE).getInventory());
					}
					if(Main.game.getNpc(Brax.class).isSlave() && Main.game.getNpc(Brax.class).getOwner().isPlayer()) {
						if(Main.game.getNpc(Brax.class).isFeminine()) {
							Main.game.getNpc(Brax.class).addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfskirt", PresetColour.CLOTHING_BLACK, false), false);
							Main.game.getNpc(Brax.class).addClothing(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_flsldshirt", PresetColour.CLOTHING_PINK, false), false);
							Main.game.getNpc(Brax.class).addClothing(Main.game.getItemGen().generateClothing("innoxia_neck_tie", PresetColour.CLOTHING_BLACK, false), false);
						} else {
							Main.game.getNpc(Brax.class).addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdslacks", PresetColour.CLOTHING_BLACK, false), false);
							Main.game.getNpc(Brax.class).addClothing(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_ssldshirt", PresetColour.CLOTHING_BLUE, false), false);
							Main.game.getNpc(Brax.class).addClothing(Main.game.getItemGen().generateClothing("innoxia_neck_tie", PresetColour.CLOTHING_BLACK, false), false);
						}
						
						AbstractClothing jacket = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLUE, null, false);
						jacket.setSticker("collar", "tab_ip");
						jacket.setSticker("name", "name_brax");
						jacket.setSticker("ribbon", "ribbon_brax");
						Main.game.getNpc(Brax.class).addClothing(jacket, false);
						
						Main.game.getNpc(Brax.class).addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdbelt", PresetColour.CLOTHING_DESATURATED_BROWN, false), false);
						
						AbstractClothing hat = Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_pcap", PresetColour.CLOTHING_BLACK, false);
						hat.setSticker("badge", "badge_dominion");
						Main.game.getNpc(Brax.class).addClothing(hat, false);
					}
				}

				if(Main.isVersionOlderThan(loadingVersion, "0.3.5.6")) {
					if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_BUYING_BRAX)
							&& (!Main.game.getNpc(Brax.class).isSlave() || !Main.game.getNpc(Brax.class).getOwner().isPlayer() || Main.game.getNpc(Brax.class).getLocationPlace().getPlaceType()==PlaceType.ENFORCER_HQ_RECEPTION_DESK)) {
						EnforcerHQDialogue.obtainBraxAsSlave();
					}
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.3.6.6")) {
					for(NPC npc : Main.game.getAllNPCs()) {
						if(Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId()) && npc.getHistory().isLowlife()) {
							npc.setHistory(Occupation.NPC_UNEMPLOYED);
						}
					}
				}

				if(Main.isVersionOlderThan(loadingVersion, "0.3.7")) { // This map update is located here as the character present in the new alleyway tile needs to have been initialised.
					// Add safe alleyway:
					Vector2i vec = Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_SLAVER_ALLEY).getLocation();
					vec.setY(vec.getY()+1);
					Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setPlaceType(PlaceType.DOMINION_BACK_ALLEYS_SAFE);
					Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setName(PlaceType.DOMINION_BACK_ALLEYS_SAFE.getName());
					Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).setDiscovered(true);
					
					
					vec = new Vector2i(0, 3);
					Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setPlaceType(PlaceType.DOMINION_BACK_ALLEYS);
					Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setName(PlaceType.DOMINION_BACK_ALLEYS.getName());
					Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).setDiscovered(true);
					
					vec = new Vector2i(0, 4);
					Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setPlaceType(PlaceType.DOMINION_BACK_ALLEYS);
					Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setName(PlaceType.DOMINION_BACK_ALLEYS.getName());
					Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).setDiscovered(true);
					
					// Move mugger character out of safe alleyway and into new one:
					if(!Main.game.getCharactersTreatingCellAsHome(Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_BACK_ALLEYS_SAFE)).isEmpty()) {
						GameCharacter character = Main.game.getCharactersTreatingCellAsHome(Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_BACK_ALLEYS_SAFE)).get(0);
						character.setLocation(WorldType.DOMINION, vec, true);
					}
				}

				// Catch for pre-v0.3.7.3 versions where characters could end up not having their pregnancy possibilities cleared upon PREGNANT_0 removal:
				if(Main.isVersionOlderThan(loadingVersion, "0.3.7.3")) {
					for(NPC npc : Main.game.getAllNPCs()) {
						if(!npc.isPregnant() && !npc.hasStatusEffect(StatusEffect.PREGNANT_0)) {
							npc.endPregnancy(false);
						}
					}
					if(!Main.game.getPlayer().isPregnant() && !Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_0)) {
						Main.game.getPlayer().endPregnancy(false);
					}
				}
				
				// Catch for pre-v0.3.8.2 versions where Thunder could have been deleted:
				if(Main.isVersionOlderThan(loadingVersion, "0.3.8.2") && Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)) {
					try {
						GameCharacter thunder = Main.game.getNPCById(Main.game.getDialogueFlags().getSadistNatalyaSlave());
						thunder.setHomeLocation(WorldType.DOMINION_EXPRESS, PlaceType.DOMINION_EXPRESS_STABLES);
					} catch (Exception e) {
						Main.game.getNpc(Natalya.class).getSlavesOwned().remove(Main.game.getDialogueFlags().getSadistNatalyaSlave()); // Make sure that she doesn't still count the deleted slave as hers.
						GameCharacter thunder = DominionExpress.spawnSlave(Main.game.getPlayer().getSexualOrientation().isAttractedToFeminine(), PresetColour.CLOTHING_BRONZE);
						DominionExpress.applySadistSlave(thunder);
						thunder.returnToHome();
					}
				}
				
				// Remove companions:
				if(Main.isVersionOlderThan(loadingVersion, "0.3.8.6")) {
					if(Main.game.getPlayer().isElementalSummoned()) {
						Main.game.getPlayer().removeCompanion(Main.game.getPlayer().getElemental());
						Main.game.getPlayer().getElemental().returnToHome();
					}
					for(GameCharacter companion : new ArrayList<>(Main.game.getPlayer().getCompanions())) {
						Main.game.getPlayer().removeCompanion(companion);
						companion.returnToHome();
					}
					for(NPC npc : Main.game.getAllNPCs()) {
						if(npc instanceof EnforcerPatrol) { 
							Main.game.banishNPC(npc);
						}
					}
				}
				
				// Set Murk's milkers as loving him:
				if(Main.isVersionOlderThan(loadingVersion, "0.3.9")) {
					for(NPC npc : Main.game.getAllNPCs()) {
						if(npc instanceof RatWarrensCaptive) { 
							npc.setAffection(Main.game.getNpc(Murk.class), 100);
						}
					}
				}

				if(Main.isVersionOlderThan(loadingVersion, "0.3.9.1")) {
					// Rat Warrens now always end up being raided:
					if(Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_THREE_END)
							|| Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_THREE_COOPERATION_END)) {
						RatWarrensDialogue.applyRatWarrensRaid();
					}
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.3.9.3")) {
					for(NPC npc : Main.game.getAllNPCs()) {
						if(npc instanceof DominionAlleywayAttacker && ((DominionAlleywayAttacker) npc).isStormAttacker()) { 
							Main.game.banishNPC(npc); // Catch for storm attackers who were stuck on a dominion street tile. (Again again...)
						}
						if(npc.getSubspecies()==Subspecies.IMP || npc.getSubspecies()==Subspecies.IMP_ALPHA) { // Fix issue with imps having an override of 'DEMON'
							npc.setSubspeciesOverride(npc.getSubspecies());
						}
					}
				}

				// Resets for Nyan's expanded romance quest:
				if(Main.isVersionOlderThan(loadingVersion, "0.3.14")) {
//					if(Main.game.getPlayer().hasQuest(QuestLine.RELATIONSHIP_NYAN_HELP)) {
					// Reset quest:
					Main.game.getPlayer().removeQuest(QuestLine.RELATIONSHIP_NYAN_HELP);
					
					// Reset player knowledge:
					Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanIntroduced, false);
					Main.game.getDialogueFlags().setFlag(DialogueFlagValue.supplierDepotDoorUnlocked, false);
					Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, false);
					Main.game.getPlayer().getCharactersEncountered().remove(Main.game.getNpc(Nyan.class).getId());
					Main.game.getPlayer().getCharactersEncountered().remove(Main.game.getNpc(SupplierPartner.class).getId());
					Main.game.getPlayer().getCharactersEncountered().remove(Main.game.getNpc(SupplierLeader.class).getId());
					Main.game.getNpc(SupplierLeader.class).setPlayerKnowsName(false);
					Main.game.getNpc(SupplierPartner.class).setPlayerKnowsName(false);
					
					// Reset Nyan:
					Main.game.getNpc(Nyan.class).clearAffectionMap();
					if(Main.game.getNpc(Nyan.class).isPregnant()) {
						Main.game.getNpc(Nyan.class).endPregnancy(false);
					}
					Main.game.getNpc(Nyan.class).setStartingBody(true);
					Main.game.getNpc(Nyan.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
					Main.game.getNpc(Nyan.class).getSexCountMap().clear();
					Main.game.getPlayer().getSexCountMap().put(Main.game.getNpc(Nyan.class).getId(), new SexCount());
					
					// Reset Supplier leader:
					Main.game.getNpc(SupplierLeader.class).clearAffectionMap();
					Main.game.getNpc(SupplierLeader.class).setStartingBody(true);
					if(Main.game.getNpc(SupplierLeader.class).isPregnant()) {
						Main.game.getNpc(SupplierLeader.class).endPregnancy(false);
					}
					Main.game.getNpc(SupplierLeader.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
					Main.game.getNpc(SupplierLeader.class).getSexCountMap().clear();
					Main.game.getPlayer().getSexCountMap().put(Main.game.getNpc(SupplierLeader.class).getId(), new SexCount());
					Main.game.getNpc(SupplierLeader.class).setLocation(WorldType.TEXTILES_WAREHOUSE, PlaceType.TEXTILE_WAREHOUSE_OVERSEER_STATION, true);
					
					// Reset Supplier partner:
					Main.game.getNpc(SupplierPartner.class).clearAffectionMap();
					Main.game.getNpc(SupplierPartner.class).setStartingBody(true);
					if(Main.game.getNpc(SupplierPartner.class).isPregnant()) {
						Main.game.getNpc(SupplierPartner.class).endPregnancy(false);
					}
					Main.game.getNpc(SupplierPartner.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
					Main.game.getNpc(SupplierPartner.class).getSexCountMap().clear();
					Main.game.getPlayer().getSexCountMap().put(Main.game.getNpc(SupplierPartner.class).getId(), new SexCount());
					Main.game.getNpc(SupplierPartner.class).setLocation(WorldType.TEXTILES_WAREHOUSE, PlaceType.TEXTILE_WAREHOUSE_OVERSEER_STATION, true);
					
					// Apply starting affections:
					Main.game.getNpc(SupplierLeader.class).setAffection(Main.game.getNpc(SupplierPartner.class), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
					Main.game.getNpc(SupplierPartner.class).setAffection(Main.game.getNpc(SupplierLeader.class), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
						
//					}
					// Move out of supplier den:
					if(Main.game.getPlayer().getWorldLocation()==WorldType.TEXTILES_WAREHOUSE) {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_ENTRANCE);
					}
					Main.game.getPlayer().getWorldsVisited().remove(WorldType.TEXTILES_WAREHOUSE);
					// Remove supplier den tile:
				}
				if(Main.isVersionOlderThan(loadingVersion, "0.3.15")) {
					Main.game.getNpc(Nyan.class).setAffection(Main.game.getNpc(Ashley.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
					Main.game.getNpc(Ashley.class).setAffection(Main.game.getNpc(Nyan.class), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
				}

				NPC lyssieth = Main.game.getNpc(Lyssieth.class);
				if(Main.isVersionOlderThan(loadingVersion, "0.3.17")
						&& Main.game.getPlayer().getTrueRace()==Race.DEMON) { // Players could only become a demon via Lyssieth before 0.3.17, so it's ok to just check for true race being demon
					if(lyssieth.getAffection(Main.game.getPlayer())<75) {
						lyssieth.setAffection(Main.game.getPlayer(), 75);
					}
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.3.17")) { // Reset home locations:
					for(NPC npc : Main.game.getAllNPCs()) {
						if(npc instanceof EnforcerPatrol) {
							npc.setHomeLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CELLS_OFFICE);
						}
					}
					Main.game.getNpc(Wes.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, true);
					Main.game.getNpc(Elle.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, true);
				}

				if(Main.isVersionOlderThan(loadingVersion, "0.4.0.5")) { // Reset Meraxis sex counts:
					Main.game.getPlayer().getSexCountMap().remove(Main.game.getNpc(DarkSiren.class).getId());
					Main.game.getNpc(Lilaya.class).getSexCountMap().remove(Main.game.getNpc(DarkSiren.class).getId());
					Main.game.getNpc(Lyssieth.class).getSexCountMap().remove(Main.game.getNpc(DarkSiren.class).getId());
				}
				
				if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.1.5") && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_B_DEMON_HOME)) {
					Main.game.getNpc(Felicia.class).setPlayerKnowsName(true); // If progressed past meeting Felicia, set her name as known
					Main.game.getPlayer().addCharacterEncountered(Main.game.getNpc(Felicia.class));
		        }

				if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.1.5")) {
					for(NPC npc : Main.game.getAllNPCs()) {
						if(npc instanceof FieldsBandit && !npc.isSlave() && !Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId())) {
							Main.game.banishNPC(npc); // Catch for fields bandits who were stuck in the fields (happened if player escaped from combat with them)
						}
					}
		        }

				if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.2.1")) { // NPCs generated before this version were affected by the game's 3-year time skip at the start, so were older than intended
					for(NPC npc : Main.game.getAllNPCs()) {
						if(npc.isUnique()) {
							npc.setBirthday(npc.getBirthday().plusYears(TIME_SKIP_YEARS));
						}
					}
				}

				if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.2.2")) {
					Main.game.getNpc(Ashley.class).clearAffectionMap();
					Main.game.getNpc(Ashley.class).setAffection(Main.game.getNpc(Nyan.class), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.4.3.1")) { // Update Dominion to add Callie's bakery:
					Vector2i vec = new Vector2i(2, 18);
					Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setPlaceType(PlaceType.DOMINION_CALLIE_BAKERY);
					Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setName(PlaceType.DOMINION_CALLIE_BAKERY.getName());
				}
				
				Main.game.pendingSlaveInStocksReset = false;

				if(Main.isVersionOlderThan(loadingVersion, "0.4.4.2")) {
					// Player perk resets:
					Main.game.getPlayer().resetPerksMap(false);
					if(!Main.game.getPlayer().hasPerkAnywhereInTree(Perk.SPECIAL_PLAYER)) {
						Main.game.getPlayer().addSpecialPerk(Perk.SPECIAL_PLAYER);
					}
					
					// NPC perk resets:
					for(NPC npc : Main.game.getAllNPCs()) {
						if(!(npc.isElemental())) {
							npc.resetPerksMap(true, false);
						}
						PerkManager.initialiseSpecialPerksUponCreation(npc); // Generate unique perks for slaves/occupants as well
					}
					
					// Arthur's room change:
					if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_I_ARTHURS_TALE)) {
						if(!Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(PlaceUpgrade.LILAYA_ARTHUR_ROOM).isEmpty()) {
							Cell arthurRoom = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(PlaceUpgrade.LILAYA_ARTHUR_ROOM).get(0);
							arthurRoom.removePlaceUpgrade(PlaceUpgrade.LILAYA_ARTHUR_ROOM);
							if(arthurRoom.getLocation().getX()>2 && arthurRoom.getLocation().getX()<8 && arthurRoom.getLocation().getY()>2 && arthurRoom.getLocation().getY()<8) {
							arthurRoom.getPlace().setPlaceType(PlaceType.LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR);
							} else {
								arthurRoom.getPlace().setPlaceType(PlaceType.LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR);
							}
						} else if(!Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCells(PlaceUpgrade.LILAYA_ARTHUR_ROOM).isEmpty()) {
							Cell arthurRoom = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCells(PlaceUpgrade.LILAYA_ARTHUR_ROOM).get(0);
							arthurRoom.removePlaceUpgrade(PlaceUpgrade.LILAYA_ARTHUR_ROOM);
							if(arthurRoom.getLocation().getX()>2 && arthurRoom.getLocation().getX()<8 && arthurRoom.getLocation().getY()>2 && arthurRoom.getLocation().getY()<8) {
							arthurRoom.getPlace().setPlaceType(PlaceType.LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR);
							} else {
								arthurRoom.getPlace().setPlaceType(PlaceType.LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR);
							}
						}
						Cell c = Lab.addArthurRoom();
						Main.game.getNpc(Arthur.class).setLocation(c, true);
					}
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.4.4.3")) {
					if(Main.game.getPlayer().hasPerkAnywhereInTree(Perk.POWER_OF_LYSSIETH_4) && Main.game.getPlayer().getTrueRace()==Race.DEMON) {
						Main.game.getPlayer().removeSpecialPerk(Perk.POWER_OF_LYSSIETH_4);
						Main.game.getPlayer().addSpecialPerk(Perk.POWER_OF_LYSSIETH_4_DEMON);
					}
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.4.5.7")) {
					// Moving Ursa, Aurokaris, and Lunexis to their proper tiles:
					if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_3_E_THEMISCYRA_ATTACK)) {
						Main.game.getNpc(Ursa.class).setLocation("innoxia_fields_elis_amazon_camp", "innoxia_fields_elis_amazon_camp_queen", true);
						Main.game.getNpc(Aurokaris.class).setLocation("innoxia_fields_elis_amazon_camp", "innoxia_fields_elis_amazon_camp_aurokaris", true);
						Main.game.getNpc(Lunexis.class).setLocation("innoxia_fields_elis_amazon_camp", "innoxia_fields_elis_amazon_camp_lunexis", true);
					}
				
					// Moving clubber NPCs to the newly-added club holding cell:
					for(NPC npc : Main.game.getAllNPCs()) {
						if(npc instanceof DominionClubNPC) {
							if(!npc.getHomeLocationPlace().getPlaceType().equals(PlaceType.DOMINION_BOULEVARD)) {
								npc.setHomeLocation(WorldType.EMPTY, PlaceType.GENERIC_CLUB_HOLDING_CELL);
							}
							if(npc.getWorldLocation()==WorldType.EMPTY) {
								npc.returnToHome();
							}
						}
					}
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.4.6.1") && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_SLIME_QUEEN)) {
					Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("dsg_quest_hazmat_rat_card"), false);
				}

				if(Main.isVersionOlderThan(loadingVersion, "0.4.6.6") && Main.game.getPlayer().getTrueRace()==Race.DEMON) {
					Main.game.getDialogueFlags().setFlag("innoxia_child_of_lyssieth", true); // Players could only become a demon via Lyssieth before v0.4.6.6, so set the flag to represent this
				}

				if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.7.2")) {
					Main.game.getNpc(Lunexis.class).setAffection(Main.game.getPlayer(), -100);
					Main.game.getNpc(Lunexis.class).setAffection(Main.game.getNpc(DarkSiren.class), -100);
					Main.game.getNpc(Lunexis.class).setAffection(Main.game.getNpc(Aurokaris.class), -100);
					Main.game.getNpc(Lunexis.class).setAffection(Main.game.getNpc(Ursa.class), -100);
				}

				if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.7.9")) {
					Main.game.getNpc(Pix.class).setLocation(WorldType.getWorldTypeFromId("innoxia_dominion_shopping_arcade_gym"), PlaceType.getPlaceTypeFromId("innoxia_dominion_shopping_arcade_gym_reception"), true);
				}

				if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.7.11")) {
					if(Main.game.getPlayer().getWorldLocation() == WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_taur")) {
						Main.game.getPlayer().setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_town"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_tavern_taur"), false);
					}
					Main.game.getNpc(Kheiron.class).setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_taur"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_tavern_taur_bar"), true);
					if(Main.game.getNpc(Fae.class).getWorldLocation()==WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_taur")) {
						Main.game.getNpc(Fae.class).setRandomLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_taur"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_tavern_taur_seating"), false);
						Main.game.getNpc(Silvia.class).setLocation(Main.game.getNpc(Fae.class));
					}
				}
				
				if(debug) {
					System.out.println("New NPCs finished");
					System.out.println("All finished");
					System.out.println((System.nanoTime()-time)/1000000000d);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(Main.game.getNpc(GenericAndrogynousNPC.class)==null) { // If was accidentally deleted in version 0.2.10:
			try {
				Main.game.addNPC(new GenericAndrogynousNPC(), false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		Main.game.setRenderMap(true);
		Main.game.setRenderAttributesSection(true);
		
		Main.game.setRequestAutosave(false);
		
		DialogueNode startingDialogueNode = Main.game.getPlayerCell().getDialogue(false);
		Main.game.addEvent(new EventLogEntry("[style.colourGood(Game loaded)]", "data/saves/"+Util.getFileName(file)+".xml"), false);
		Main.game.setStarted(true); // Set started before setting content so that it parses correctly (as the scripting engine is initialised fully in the setStarted() method).
		Main.game.setContent(new Response("", startingDialogueNode.getDescription(), startingDialogueNode), false);
		
		Main.game.occupancyUtil.updateSlavesResting(Main.game.getHourOfDay()); // Makes sure that resting slaves are correctly accounted for
		Main.game.endTurn(0);
		
		// Do a zero-time status effect update after declaring that the game has started to make sure that everything is initialised properly (mainly just so external status effects are initialised):
		for(NPC npc : Main.game.getAllNPCs()) {
			npc.updateInventoryListeners();
			npc.updateAttributeListeners(true);
			npc.calculateStatusEffects(0);
		}
		Main.game.getPlayer().updateInventoryListeners();
		Main.game.getPlayer().updateAttributeListeners(true);
		Main.game.getPlayer().calculateStatusEffects(0);
	}

	@SuppressWarnings("unchecked")
	private static NPC loadNPC(Document doc,
			Element e,
			String className, 
			Map<String, Class<? extends NPC>> classMap,
			Map<Class<? extends NPC>, Method> loadFromXMLMethodMap,
			Map<Class<? extends NPC>, Constructor<? extends NPC>> constructorMap){
		
		try {
			Class<? extends NPC> npcClass = classMap.get(className);
			if (npcClass == null) {
				npcClass = (Class<? extends NPC>) Class.forName(className);
				synchronized (npcClass) {
					if(classMap.containsKey(className)){
						npcClass = classMap.get(className);
					} else {
						classMap.putIfAbsent(className, npcClass);
						Method m = npcClass.getMethod("loadFromXML", Element.class, Document.class, CharacterImportSetting[].class);
						loadFromXMLMethodMap.put(npcClass, m);

						Constructor<? extends NPC> declaredConstructor = npcClass.getDeclaredConstructor(boolean.class);
						constructorMap.put(npcClass, declaredConstructor);
					}
				}
			}
			Constructor<? extends NPC> declaredConstructor = constructorMap.get(npcClass);
			if (declaredConstructor == null) {
				synchronized (npcClass) {
					declaredConstructor = constructorMap.get(npcClass);
				}
			}
			NPC npc = declaredConstructor.newInstance(true);
			loadFromXMLMethodMap.get(npcClass).invoke(npc, e, doc, new CharacterImportSetting[] {});
			return npc;
		} catch(NoSuchMethodException nsme) {
			System.err.println("Couldn't find required method(loadFromXML or constructor(boolean)) for class: " + className);
			nsme.printStackTrace();
			return null;
		} catch(Exception ex) {
			System.err.println("Failed to load NPC class: "+className);
			ex.printStackTrace();
			return null;
		}
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("game");
		
		parentElement.appendChild(element);
		
		return element;
	}
	
	public static Game loadFromXML(Element parentElement, Document doc) {
		Game loadedGame = new Game();
		
		return loadedGame;
	}
	
	public void initNewGame(DialogueNode startingDialogueNode) {
		NPCMap.clear();
		OffspringSeedMap.clear();
		initUniqueNPCs();

		// This is due to the fact that on new world creation, the player is placed at coordinates (0, 0), which reveals the three squares at the bottom left corner of the map:
		Main.game.getActiveWorld().getCell(0, 0).setDiscovered(false);
		Main.game.getActiveWorld().getCell(0, 1).setDiscovered(false);
		Main.game.getActiveWorld().getCell(1, 0).setDiscovered(false);
		
		setStarted(false);
		
		SlaverAlleyDialogue.dailyReset();
		
		UtilText.initScriptEngine();

		setStarted(true);
		
		setContent(new Response(startingDialogueNode.getLabel(), startingDialogueNode.getDescription(), startingDialogueNode));
	}
	
	/**
	 * This needs to be run after initialising a new or loaded game.
	 */
	private void handlePostGameInit() {
		UtilText.initScriptEngine();
		
		// Handle Subspecies detection after UtilText's parsing engine has been initialised (as modded races require parsing of a conditional to determine weighting).
		for(NPC npc : this.getAllNPCs()) {
			npc.getBody().calculateRace(npc);
		}
		player.getBody().calculateRace(player);
		
		// Refresh UI to update race icons:
		if(getCurrentDialogueNode()!=null) {
			MainController.updateUI();
		}
	}
	
	private void initUniqueNPCs() {
		// Set up NPCs:
		try {
			List<Class<? extends NPC>> addedNpcs = new ArrayList<>();
			
			// Misc.:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(GenericMaleNPC.class))) { addNPC(new GenericMaleNPC(), false); addedNpcs.add(GenericMaleNPC.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(GenericFemaleNPC.class))) { addNPC(new GenericFemaleNPC(), false);  addedNpcs.add(GenericFemaleNPC.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(GenericAndrogynousNPC.class))) { addNPC(new GenericAndrogynousNPC(), false); addedNpcs.add(GenericAndrogynousNPC.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(PrologueMale.class))) { addNPC(new PrologueMale(), false); addedNpcs.add(PrologueMale.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(PrologueFemale.class))) { addNPC(new PrologueFemale(), false); addedNpcs.add(PrologueFemale.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(GenericTrader.class))) { addNPC(new GenericTrader(), false); addedNpcs.add(GenericTrader.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(TestNPC.class))) { addNPC(new TestNPC(), false); addedNpcs.add(TestNPC.class); }

			// Contributors:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Lumi.class))) { addNPC(new Lumi(), false); addedNpcs.add(Lumi.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Pazu.class))) { addNPC(new Pazu(), false); addedNpcs.add(Pazu.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Ashley.class))) { addNPC(new Ashley(), false); addedNpcs.add(Ashley.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Callie.class))) { addNPC(new Callie(), false); addedNpcs.add(Callie.class); }
			
			// Story:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Rose.class))) { addNPC(new Rose(), false); addedNpcs.add(Rose.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Lilaya.class))) { addNPC(new Lilaya(), false); addedNpcs.add(Lilaya.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Arthur.class))) { addNPC(new Arthur(), false); addedNpcs.add(Arthur.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Lyssieth.class))) { addNPC(new Lyssieth(), false); addedNpcs.add(Lyssieth.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Elizabeth.class))) { addNPC(new Elizabeth(), false); addedNpcs.add(Elizabeth.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Takahashi.class))) { addNPC(new Takahashi(), false); addedNpcs.add(Takahashi.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(DarkSiren.class))) { addNPC(new DarkSiren(), false); addedNpcs.add(DarkSiren.class); }
			
			if(addedNpcs.contains(Lilaya.class)) {
				getNpc(Lilaya.class).setAffection(Main.game.getPlayer(), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
				getNpc(Lilaya.class).setAffection(getNpc(Rose.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
				getNpc(Lilaya.class).addSlave(getNpc(Rose.class));
				
				getNpc(Lilaya.class).setAffection(getNpc(Lyssieth.class), -60);
				getNpc(Lilaya.class).setAffection(getNpc(DarkSiren.class), 15);
				getNpc(Lilaya.class).setMother(getNpc(Lyssieth.class));
			}
			if(addedNpcs.contains(Rose.class)) {
				getNpc(Rose.class).setAffection(Main.game.getPlayer(), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
				getNpc(Rose.class).setAffection(getNpc(Lilaya.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
				getNpc(Rose.class).setObedience(ObedienceLevel.POSITIVE_FIVE_SUBSERVIENT.getMedianValue());
				getNpc(Rose.class).setAffection(getNpc(Lyssieth.class), -40);
			}
			if(addedNpcs.contains(DarkSiren.class)) {
				getNpc(DarkSiren.class).setAffection(getNpc(Lyssieth.class), -25);
				getNpc(DarkSiren.class).setAffection(getNpc(Lilaya.class), 35);
				getNpc(DarkSiren.class).setMother(getNpc(Lyssieth.class));
			}
			if(addedNpcs.contains(Elizabeth.class)) {
				getNpc(Elizabeth.class).setMother(getNpc(Lyssieth.class));
				getNpc(Elizabeth.class).setAffection(getNpc(Lyssieth.class), 100);
			}
			if(addedNpcs.contains(Lyssieth.class)) {
				getNpc(Lyssieth.class).setAffection(getNpc(Lilaya.class), 100);
				getNpc(Lyssieth.class).setAffection(getNpc(DarkSiren.class), 50);
				getNpc(Lyssieth.class).setAffection(getNpc(Elizabeth.class), 75);
				getNpc(Lyssieth.class).setAffection(getNpc(Rose.class), -80);
			}

			// Enforcers:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Brax.class))) { addNPC(new Brax(), false); addedNpcs.add(Brax.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(CandiReceptionist.class))) { addNPC(new CandiReceptionist(), false); addedNpcs.add(CandiReceptionist.class); }
			
			if(addedNpcs.contains(Brax.class)) {
				getNpc(Brax.class).setAffection(getNpc(CandiReceptionist.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
			}
			if(addedNpcs.contains(CandiReceptionist.class)) {
				getNpc(CandiReceptionist.class).setAffection(getNpc(Brax.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
			}

			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Wes.class))) { addNPC(new Wes(), false); addedNpcs.add(Wes.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Elle.class))) { addNPC(new Elle(), false); addedNpcs.add(Elle.class); }

			if(addedNpcs.contains(Wes.class)) {
				getNpc(Wes.class).setAffection(getNpc(Elle.class), AffectionLevel.NEGATIVE_THREE_STRONG_DISLIKE.getMedianValue());
			}
			if(addedNpcs.contains(Elle.class)) {
				getNpc(Elle.class).setAffection(getNpc(Wes.class), AffectionLevel.NEGATIVE_ONE_ANNOYED.getMedianValue());
			}
			
			// Shopping Promenade:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Ralph.class))) { addNPC(new Ralph(), false); addedNpcs.add(Ralph.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Nyan.class))) { addNPC(new Nyan(), false); addedNpcs.add(Nyan.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(NyanMum.class))) { addNPC(new NyanMum(), false); addedNpcs.add(NyanMum.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Vicky.class))) { addNPC(new Vicky(), false); addedNpcs.add(Vicky.class); }
			
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Pix.class))) { addNPC(new Pix(), false); addedNpcs.add(Pix.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Hannah.class))) { addNPC(new Hannah(), false); addedNpcs.add(Hannah.class); }
			if(addedNpcs.contains(Pix.class) || addedNpcs.contains(Hannah.class)) {
				Main.game.getNpc(Pix.class).setAffection(Main.game.getNpc(Hannah.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
				Main.game.getNpc(Hannah.class).setAffection(Main.game.getNpc(Pix.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
			}
			
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Kate.class))) { addNPC(new Kate(), false); addedNpcs.add(Kate.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(SupplierLeader.class))) { addNPC(new SupplierLeader(), false); addedNpcs.add(SupplierLeader.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(SupplierPartner.class))) { addNPC(new SupplierPartner(), false); addedNpcs.add(SupplierPartner.class); }

			if(addedNpcs.contains(Nyan.class) || addedNpcs.contains(NyanMum.class)) {
				Main.game.getNpc(Nyan.class).setAffection(Main.game.getNpc(NyanMum.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
				Main.game.getNpc(NyanMum.class).setAffection(Main.game.getNpc(Nyan.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
				
				Main.game.getNpc(Nyan.class).setAffection(Main.game.getNpc(Ashley.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
				Main.game.getNpc(Ashley.class).setAffection(Main.game.getNpc(Nyan.class), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
			}
			
			if(addedNpcs.contains(SupplierLeader.class)) {
				Main.game.getNpc(SupplierLeader.class).setAffection(Main.game.getNpc(SupplierPartner.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
				Main.game.getNpc(SupplierPartner.class).setAffection(Main.game.getNpc(SupplierLeader.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
			}

			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Kay.class))) { addNPC(new Kay(), false); addedNpcs.add(Kay.class); }
			if(addedNpcs.contains(Kay.class)) {
				Main.game.getNpc(Nyan.class).setAffection(Main.game.getNpc(Kay.class), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
				Main.game.getNpc(Kay.class).setAffection(Main.game.getNpc(Nyan.class), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
			}
			
			// Harpy nests:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Scarlett.class))) { addNPC(new Scarlett(), false); addedNpcs.add(Scarlett.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Helena.class))) { addNPC(new Helena(), false); addedNpcs.add(Helena.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(HarpyBimbo.class))) { addNPC(new HarpyBimbo(), false); addedNpcs.add(HarpyBimbo.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(HarpyBimboCompanion.class))) { addNPC(new HarpyBimboCompanion(), false); addedNpcs.add(HarpyBimboCompanion.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(HarpyDominant.class))) { addNPC(new HarpyDominant(), false); addedNpcs.add(HarpyDominant.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(HarpyDominantCompanion.class))) { addNPC(new HarpyDominantCompanion(), false); addedNpcs.add(HarpyDominantCompanion.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(HarpyNympho.class))) { addNPC(new HarpyNympho(), false); addedNpcs.add(HarpyNympho.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(HarpyNymphoCompanion.class))) { addNPC(new HarpyNymphoCompanion(), false); addedNpcs.add(HarpyNymphoCompanion.class); }

			if(addedNpcs.contains(Scarlett.class)) {
				Main.game.getNpc(Scarlett.class).setAffection(Main.game.getPlayer(), AffectionLevel.NEGATIVE_TWO_DISLIKE.getMedianValue());
				Main.game.getNpc(Scarlett.class).setAffection(Main.game.getNpc(Helena.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
				if(Main.game.getPlayer().hasQuest(QuestLine.MAIN) && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE)) {
					Main.game.getNpc(Scarlett.class).setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST);
				}
			}
			if(addedNpcs.contains(Helena.class)) {
				Main.game.getNpc(Helena.class).setAffection(Main.game.getNpc(Scarlett.class), AffectionLevel.NEGATIVE_FOUR_HATE.getMedianValue());
			}
			if(addedNpcs.contains(HarpyBimbo.class)) {
				Main.game.getNpc(HarpyBimbo.class).setAffection(Main.game.getNpc(HarpyBimboCompanion.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
			}
			if(addedNpcs.contains(HarpyBimboCompanion.class)) {
				Main.game.getNpc(HarpyBimboCompanion.class).setAffection(Main.game.getNpc(HarpyBimbo.class), AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
			}
			if(addedNpcs.contains(HarpyDominant.class)) {
				Main.game.getNpc(HarpyDominant.class).setAffection(Main.game.getNpc(HarpyDominantCompanion.class), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
			}
			if(addedNpcs.contains(HarpyDominantCompanion.class)) {
				Main.game.getNpc(HarpyDominantCompanion.class).setAffection(Main.game.getNpc(HarpyDominant.class), AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
			}
			if(addedNpcs.contains(HarpyNympho.class)) {
				Main.game.getNpc(HarpyNympho.class).setAffection(Main.game.getNpc(HarpyNymphoCompanion.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
			}
			if(addedNpcs.contains(HarpyNymphoCompanion.class)) {
				Main.game.getNpc(HarpyNymphoCompanion.class).setAffection(Main.game.getNpc(HarpyNympho.class), AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
			}
			
			// City hall:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Vanessa.class))) { addNPC(new Vanessa(), false); addedNpcs.add(Vanessa.class); }
			
			// Dominion Express:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Natalya.class))) { addNPC(new Natalya(), false); addedNpcs.add(Natalya.class); }
			
			// Slaver alley:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Finch.class))) { addNPC(new Finch(), false); addedNpcs.add(Finch.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Sean.class))) { addNPC(new Sean(), false); addedNpcs.add(Sean.class); }
			if(addedNpcs.contains(Sean.class)) {
				getNpc(Brax.class).setPetName(Main.game.getNpc(Sean.class), Main.game.getNpc(Sean.class).getName(false));
			}
			
			// Rental mommy;
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(RentalMommy.class))) { addNPC(new RentalMommy(), false); addedNpcs.add(RentalMommy.class); }
			
			// 'Daddy':
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Daddy.class))) { addNPC(new Daddy(), false); addedNpcs.add(Daddy.class); }
			if(addedNpcs.contains(Daddy.class)) {
				getNpc(Rose.class).setAffection(getNpc(Daddy.class), -50);
			}

            // Sawlty Towers (Arthur/Felicia's apartment building):
            if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Felicia.class))) {
                addNPC(new Felicia(), false);
                addedNpcs.add(Felicia.class);
            }
            if(addedNpcs.contains(Felicia.class)) {
            	getNpc(Felicia.class).setAffection(getNpc(Arthur.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
            }

			// Zaranix's home:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Zaranix.class))) { addNPC(new Zaranix(), false); addedNpcs.add(Zaranix.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Amber.class))) { addNPC(new Amber(), false); addedNpcs.add(Amber.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(ZaranixMaidKatherine.class))) { addNPC(new ZaranixMaidKatherine(), false); addedNpcs.add(ZaranixMaidKatherine.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(ZaranixMaidKelly.class))) { addNPC(new ZaranixMaidKelly(), false); addedNpcs.add(ZaranixMaidKelly.class); }

			if(addedNpcs.contains(Zaranix.class)) {
				Main.game.getNpc(Zaranix.class).setAffection(Main.game.getNpc(ZaranixMaidKatherine.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
				Main.game.getNpc(Zaranix.class).setAffection(Main.game.getNpc(ZaranixMaidKelly.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
				Main.game.getNpc(Zaranix.class).setAffection(Main.game.getNpc(Amber.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
			}
			if(addedNpcs.contains(Amber.class)) {
				Main.game.getNpc(Amber.class).setAffection(Main.game.getNpc(Zaranix.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
				Main.game.getNpc(Amber.class).setAffection(Main.game.getNpc(ZaranixMaidKelly.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
				Main.game.getNpc(Amber.class).setAffection(Main.game.getNpc(ZaranixMaidKatherine.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
			}
			if(addedNpcs.contains(ZaranixMaidKatherine.class)) {
				Main.game.getNpc(ZaranixMaidKatherine.class).setAffection(Main.game.getNpc(Zaranix.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
				Main.game.getNpc(ZaranixMaidKatherine.class).setAffection(Main.game.getNpc(ZaranixMaidKelly.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
				Main.game.getNpc(ZaranixMaidKatherine.class).setAffection(Main.game.getNpc(Amber.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
			}
			if(addedNpcs.contains(ZaranixMaidKelly.class)) {
				Main.game.getNpc(ZaranixMaidKelly.class).setAffection(Main.game.getNpc(Zaranix.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
				Main.game.getNpc(ZaranixMaidKelly.class).setAffection(Main.game.getNpc(ZaranixMaidKatherine.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
				Main.game.getNpc(ZaranixMaidKelly.class).setAffection(Main.game.getNpc(Amber.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
			}

			// Angel's kiss:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Angel.class))) { addNPC(new Angel(), false); addedNpcs.add(Angel.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Bunny.class))) { addNPC(new Bunny(), false); addedNpcs.add(Bunny.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Loppy.class))) { addNPC(new Loppy(), false); addedNpcs.add(Loppy.class); }
			
			// Slime queen:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(SlimeQueen.class))) { addNPC(new SlimeQueen(), false); addedNpcs.add(SlimeQueen.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(SlimeGuardIce.class))) { addNPC(new SlimeGuardIce(), false); addedNpcs.add(SlimeGuardIce.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(SlimeGuardFire.class))) { addNPC(new SlimeGuardFire(), false); addedNpcs.add(SlimeGuardFire.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(SlimeRoyalGuard.class))) { addNPC(new SlimeRoyalGuard(), false); addedNpcs.add(SlimeRoyalGuard.class); }
			
			// Submission:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Claire.class))) { addNPC(new Claire(), false); addedNpcs.add(Claire.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(FortressAlphaLeader.class))) { addNPC(new FortressAlphaLeader(), false); addedNpcs.add(FortressAlphaLeader.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(FortressFemalesLeader.class))) { addNPC(new FortressFemalesLeader(), false); addedNpcs.add(FortressFemalesLeader.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(FortressMalesLeader.class))) { addNPC(new FortressMalesLeader(), false); addedNpcs.add(FortressMalesLeader.class); }
			
			// Nightclub:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Jules.class))) { addNPC(new Jules(), false); addedNpcs.add(Jules.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Kruger.class))) { addNPC(new Kruger(), false); addedNpcs.add(Kruger.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Kalahari.class))) { addNPC(new Kalahari(), false); addedNpcs.add(Kalahari.class); }

			if(addedNpcs.contains(Kalahari.class)) {
				Main.game.getNpc(Kalahari.class).setFather(Main.game.getNpc(Kruger.class));
				Main.game.getNpc(Kalahari.class).setAffection(Main.game.getNpc(Kruger.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
			}
			if(addedNpcs.contains(Kruger.class)) {
				Main.game.getNpc(Kruger.class).setAffection(Main.game.getNpc(Kalahari.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
			}
			
			// Gambling den:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Roxy.class))) { addNPC(new Roxy(), false); addedNpcs.add(Roxy.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Axel.class))) { addNPC(new Axel(), false); addedNpcs.add(Axel.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Epona.class))) { addNPC(new Epona(), false); addedNpcs.add(Epona.class); }

			// Rat Warrens:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Vengar.class))) { addNPC(new Vengar(), false); addedNpcs.add(Vengar.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Shadow.class))) { addNPC(new Shadow(), false); addedNpcs.add(Shadow.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Silence.class))) { addNPC(new Silence(), false); addedNpcs.add(Silence.class); }
			if(addedNpcs.contains(Vengar.class)) {
				getNpc(Vengar.class).setAffection(getNpc(Shadow.class), 50);
				getNpc(Vengar.class).setAffection(getNpc(Silence.class), 50);
				getNpc(Shadow.class).setAffection(getNpc(Vengar.class), -10);
				getNpc(Shadow.class).setAffection(getNpc(Silence.class), 80);
				getNpc(Silence.class).setAffection(getNpc(Vengar.class), 20);
				getNpc(Silence.class).setAffection(getNpc(Shadow.class), 100);
			}
			if(Main.isVersionOlderThan(loadingVersion, "0.3.5.6")) {
				getNpc(Roxy.class).setAffection(getNpc(Vengar.class), -80);
				getNpc(Vengar.class).setAffection(getNpc(Roxy.class), 50);
			}
			if(Main.isVersionOlderThan(loadingVersion, "0.3.5.9")) {
				getNpc(Silence.class).setAffection(getNpc(Shadow.class), 100);
				getNpc(Silence.class).getAffectionMap().remove(getNpc(Silence.class).getId());
			}
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Murk.class))) { addNPC(new Murk(), false); addedNpcs.add(Murk.class); }

			// Hazmat Rat:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(HazmatRat.class))) { addNPC(new HazmatRat(), false); addedNpcs.add(HazmatRat.class); }
			
			
			// Elis:

			// The Red Dragon:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Flash.class))) { addNPC(new Flash(), false); addedNpcs.add(Flash.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Jess.class))) { addNPC(new Jess(), false); addedNpcs.add(Jess.class); }

			if(addedNpcs.contains(Flash.class) || addedNpcs.contains(Jess.class)) {
				Main.game.getNpc(Jess.class).setAffection(Main.game.getNpc(Flash.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
				Main.game.getNpc(Flash.class).setAffection(Main.game.getNpc(Jess.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
			}
			
			// Astrapi/Vronti/Kheiron:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Astrapi.class))) { addNPC(new Astrapi(), false); addedNpcs.add(Astrapi.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Vronti.class))) { addNPC(new Vronti(), false); addedNpcs.add(Vronti.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Kheiron.class))) { addNPC(new Kheiron(), false); addedNpcs.add(Kheiron.class); }

			if(addedNpcs.contains(Astrapi.class) || addedNpcs.contains(Vronti.class) || addedNpcs.contains(Kheiron.class)) {
				Main.game.getNpc(Astrapi.class).setAffection(Main.game.getNpc(Vronti.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
				Main.game.getNpc(Astrapi.class).setAffection(Main.game.getNpc(Kheiron.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
				
				Main.game.getNpc(Vronti.class).setAffection(Main.game.getNpc(Astrapi.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
				Main.game.getNpc(Vronti.class).setAffection(Main.game.getNpc(Kheiron.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());

				Main.game.getNpc(Kheiron.class).setAffection(Main.game.getNpc(Astrapi.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
				Main.game.getNpc(Kheiron.class).setAffection(Main.game.getNpc(Vronti.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());

				getNpc(Astrapi.class).setFather(getNpc(Kheiron.class));
				getNpc(Vronti.class).setFather(getNpc(Kheiron.class));
			}
			
			// Minotallys/Arion
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Arion.class))) { addNPC(new Arion(), false); addedNpcs.add(Arion.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Minotallys.class))) { addNPC(new Minotallys(), false); addedNpcs.add(Minotallys.class); }

			if(addedNpcs.contains(Arion.class) || addedNpcs.contains(Minotallys.class)) {
				Main.game.getNpc(Arion.class).setAffection(Main.game.getNpc(Minotallys.class), AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
				Main.game.getNpc(Minotallys.class).setAffection(Main.game.getNpc(Arion.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
			}
			
			// Farmer's Market:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Fae.class))) { addNPC(new Fae(), false); addedNpcs.add(Fae.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Silvia.class))) { addNPC(new Silvia(), false); addedNpcs.add(Silvia.class); }

			if(addedNpcs.contains(Fae.class) || addedNpcs.contains(Silvia.class)) {
				Main.game.getNpc(Silvia.class).setAffection(Main.game.getNpc(Fae.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
				Main.game.getNpc(Fae.class).setAffection(Main.game.getNpc(Silvia.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
			}
			
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Kazik.class))) { addNPC(new Kazik(), false); addedNpcs.add(Kazik.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Yui.class))) { addNPC(new Yui(), false); addedNpcs.add(Yui.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Nizhoni.class))) { addNPC(new Nizhoni(), false); addedNpcs.add(Nizhoni.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Moreno.class))) { addNPC(new Moreno(), false); addedNpcs.add(Moreno.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Heather.class))) { addNPC(new Heather(), false); addedNpcs.add(Heather.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Ziva.class))) { addNPC(new Ziva(), false); addedNpcs.add(Ziva.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Eisek.class))) { addNPC(new Eisek(), false); addedNpcs.add(Eisek.class); }

			// Wall's End:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Monica.class))) { addNPC(new Monica(), false); addedNpcs.add(Monica.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Ceridwen.class))) { addNPC(new Ceridwen(), false); addedNpcs.add(Ceridwen.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Imsu.class))) { addNPC(new Imsu(), false); addedNpcs.add(Imsu.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Hale.class))) { addNPC(new Hale(), false); addedNpcs.add(Hale.class); }
			if(addedNpcs.contains(Imsu.class) || addedNpcs.contains(Hale.class)) {
				Main.game.getNpc(Imsu.class).setAffection(Main.game.getNpc(Hale.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
				Main.game.getNpc(Hale.class).setAffection(Main.game.getNpc(Imsu.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
			}
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Penelope.class))) { addNPC(new Penelope(), false); addedNpcs.add(Penelope.class); }
			if(addedNpcs.contains(Penelope.class) || addedNpcs.contains(Pix.class) || Main.isVersionOlderThan(Game.loadingVersion, "0.4.2.7")) {
				Main.game.getNpc(Penelope.class).setAffection(Main.game.getNpc(Pix.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
				Main.game.getNpc(Pix.class).setAffection(Main.game.getNpc(Penelope.class), AffectionLevel.NEGATIVE_ONE_ANNOYED.getMedianValue());
			}
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Belle.class))) { addNPC(new Belle(), false); addedNpcs.add(Belle.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Daphne.class))) { addNPC(new Daphne(), false); addedNpcs.add(Daphne.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Farah.class))) { addNPC(new Farah(), false); addedNpcs.add(Farah.class); }

			// The Crossed Blades:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Oglix.class))) { addNPC(new Oglix(), false); addedNpcs.add(Oglix.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Wynter.class))) { addNPC(new Wynter(), false); addedNpcs.add(Wynter.class); }
			if(addedNpcs.contains(Oglix.class)) {
				Main.game.getNpc(Oglix.class).setAffection(Main.game.getNpc(Kheiron.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
				Main.game.getNpc(Kheiron.class).setAffection(Main.game.getNpc(Oglix.class), AffectionLevel.NEGATIVE_THREE_STRONG_DISLIKE.getMedianValue());
			}

			// Enforcer station:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Sterope.class))) { addNPC(new Sterope(), false); addedNpcs.add(Sterope.class); }
			
			
			// Evelyx's Dairy:
			
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Evelyx.class))) { addNPC(new Evelyx(), false); addedNpcs.add(Evelyx.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Dale.class))) { addNPC(new Dale(), false); addedNpcs.add(Dale.class); }
			if(addedNpcs.contains(Evelyx.class) || addedNpcs.contains(Dale.class)) {
				Main.game.getNpc(Evelyx.class).setAffection(Main.game.getNpc(Dale.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
				Main.game.getNpc(Dale.class).setAffection(Main.game.getNpc(Evelyx.class), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
			}

			// Headless horseman:
			
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(HeadlessHorseman.class))) { addNPC(new HeadlessHorseman(), false); addedNpcs.add(HeadlessHorseman.class); }
			
			// Themiscyra:
			
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Lunexis.class))) { addNPC(new Lunexis(), false); addedNpcs.add(Lunexis.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Ursa.class))) { addNPC(new Ursa(), false); addedNpcs.add(Ursa.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Aurokaris.class))) { addNPC(new Aurokaris(), false); addedNpcs.add(Aurokaris.class); }
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Main updating for game mechanics, as everything is based on turns.
	public void endTurn(int secondsPassed) {
		endTurn(secondsPassed, true);
	}
	
	public void endTurn(Response response, DialogueNode dialogue) {
		int seconds = 0;
		if(dialogue!=null) {
			seconds = dialogue.getSecondsPassed();
		}
		if(response!=null && response.getSecondsPassed()!=Response.DEFAULT_TIME_PASSED_VALUE) {
			seconds = response.getSecondsPassed();
		}
//		if(isPlayerMovedLocation()) {
//			System.out.println(":1");
//			Main.game.endTurn(getModifierTravelTime(Main.game.getPlayer().getLocationPlace().getPlaceType().isLand(), seconds), true);
//		} else {
//			System.out.println(":2");
			Main.game.endTurn(seconds, true);
//		}
	}
	
	private boolean isInNPCUpdateLoop = false;
	// These should start as false so that they aren't reset upon the initial loading of a game
	public boolean pendingSlaveInStocksReset = false;
	public boolean pendingSlaveShopsReset = false;
	
	private List<NPC> npcsToRemove = new ArrayList<>();
	private List<NPC> npcsToAdd = new ArrayList<>();
	
	public void endTurn(int secondsPassedThisTurn, boolean advanceTime) {

		boolean loopDebug = false;
		long tStart = System.nanoTime();
		long startHour = getHour();
		int startHourOfDay = getHourOfDay();
		List<AbstractDialogueFlagValue> flagsReset = new ArrayList<>();
		
		if(advanceTime) {
			secondsPassed += secondsPassedThisTurn;
			updateResponses();
		}
		int hoursPassed = (int) (getHour() - startHour);
		
		if(hoursPassed>0) {
			flagsReset = Main.game.getDialogueFlags().applyTimePassingResets(startHourOfDay, hoursPassed);
		}
		
		if(loopDebug) {
			System.out.println();
			System.out.println("debug end turn start");
		}
		// Reset imp tunnels after 5 days if DS is defeated:
		if(Main.game.getPlayer().hasQuest(QuestLine.MAIN) && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_B_SIRENS_CALL)) {
			boolean alphaReset = this.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)
					&& ((this.getMinutesPassed() - this.getDialogueFlags().getSavedLong(ImpFortressDialogue.FORTRESS_ALPHA_CLEAR_TIMER_ID)) > 60*24*5);
			
			boolean femalesReset = this.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)
					&& ((this.getMinutesPassed() - this.getDialogueFlags().getSavedLong(ImpFortressDialogue.FORTRESS_FEMALES_CLEAR_TIMER_ID)) > 60*24*5);
			
			boolean malesReset = this.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)
					&& ((this.getMinutesPassed() - this.getDialogueFlags().getSavedLong(ImpFortressDialogue.FORTRESS_MALES_CLEAR_TIMER_ID)) > 60*24*5);
			
			if(alphaReset && Main.game.getPlayer().getWorldLocation()!=WorldType.IMP_FORTRESS_ALPHA) {
				ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_ALPHA);
			}
			if(femalesReset && Main.game.getPlayer().getWorldLocation()!=WorldType.IMP_FORTRESS_FEMALES) {
				ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_FEMALES);
			}
			if(malesReset && Main.game.getPlayer().getWorldLocation()!=WorldType.IMP_FORTRESS_MALES) {
				ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_MALES);
			}
		}
		if(loopDebug) {
			System.out.println("imp tunnels reset");
		}
		
		// Do the player's companion check before anything else, as if a companion leaves, then the follow-up check to send to work needs to be performed.
		List<GameCharacter> companions = new ArrayList<>(Main.game.getPlayer().getCompanions());
		for(GameCharacter companion : companions) {
			// Updating companion NPCs:
			if(companion.getPartyLeader()==null) {
				companion.setPartyLeader(Main.game.getPlayer().getId());
			}
			companion.companionshipCheck();
		}
		if(!Main.game.getCurrentDialogueNode().isTravelDisabled()) {
			for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
				character.setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
			}
		}

		if(loopDebug) {
			System.out.println("companions done");
		}
		
		// Occupancy:
		int hourStartTo24 = (int) (startHour%24);
		boolean slavesUpdated = hoursPassed>0;
		if(slavesUpdated) {
			for(int i=1; i <= hoursPassed; i++) {
				Main.game.getPlayer().performHourlyFluidsCheck();
				occupancyUtil.performHourlyUpdate(this.getDayNumber((startHour*60*60) + (i*60)), (hourStartTo24+i)%24);
				for(String slaveId : occupancyUtil.getAllCharacters()) { // Update slaves' status effects per hour to give them a chance to refill fluids and such.
					try {
						Main.game.getNPCById(slaveId).calculateStatusEffects(3600);
					} catch (Exception e) {
					}
				}
			}
			for(String slaveId : Main.game.getPlayer().getSlavesOwned()) {// Update slaves' status effects by whatever time is remaining.
				try {
					Main.game.getNPCById(slaveId).calculateStatusEffects(secondsPassedThisTurn%3600);
				} catch (Exception e) {
				}
			}
		}
		if(!Main.game.getPlayer().getLocationPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM)) {
			MilkingRoom.setTargetedCharacter(Main.game.getPlayer());
		}


		if(loopDebug) {
			System.out.println("occupancy done");
		}
		
		// If the time has passed midnight on this turn:
		boolean newDay = getDayNumber(getSecondsPassed()) != getDayNumber(getSecondsPassed() - secondsPassedThisTurn);
		
		if(newDay) {
			pendingSlaveShopsReset = true;
			pendingSlaveInStocksReset = true;
			Main.game.getPlayer().resetDaysOrgasmCount();
			
			for(String id : Main.game.getPlayer().getFriendlyOccupants()) {
				try {
					NPC occupant = (NPC) Main.game.getNPCById(id);
					Main.game.getOccupancyUtil().dailyOccupantUpdate(occupant);
				} catch(Exception e) {
					Util.logGetNpcByIdError("endTurn()", id);
				}
			}
			if(loopDebug) {
				System.out.println("starting daily location reset");
			}
			
			// Place resets and calculations:
			LilayaHomeGeneric.dailyUpdate();
			VengarCaptiveDialogue.applyDailyReset();
			calculateBankInterest();
		}
		// v0.4.8.4: Only generating slaves when the player enters slaver alley is marginally better performance-wise, but creates the issue of the newly-generated slaves not being saved, so I removed this check.
//		if (WorldType.SLAVER_ALLEY.getPlacesMap().values().contains(Main.game.getPlayer().getLocationPlaceType())) {
			if (pendingSlaveShopsReset
					&& !Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SLAVER_ALLEY_STALL_ANAL)
					&& !Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SLAVER_ALLEY_STALL_FEMALES)
					&& !Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SLAVER_ALLEY_STALL_MALES)
					&& !Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SLAVER_ALLEY_STALL_ORAL)
					&& !Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SLAVER_ALLEY_STALL_VAGINAL)) {
				SlaverAlleyDialogue.dailyReset();
				pendingSlaveShopsReset = false;
			}
			if (pendingSlaveInStocksReset && !Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS)) {
				SlaverAlleyDialogue.stocksReset();
				pendingSlaveInStocksReset = false;
			}
//		}
		
		// Angels Kiss update
		for(int i=1; i <= hoursPassed; i++) {
			RedLightDistrict.prostituteUpdate();
			SlaverAlleyDialogue.stocksUpdate();
		}
		
		if(loopDebug) {
			System.out.println("Daily location end");
		}
		
		handleAtmosphericConditions(secondsPassedThisTurn);

		
		// Apply status effects and update all NPCs:
		isInNPCUpdateLoop = true;
		long tLoopStart = System.nanoTime();
		if(loopDebug) {
			System.out.println("NPC loop start");
		}
		
		for(NPC npc : NPCMap.values()) {
			boolean inGame = !npc.getLocationPlace().getPlaceType().equals(PlaceType.GENERIC_EMPTY_TILE)
					|| (npc instanceof Elemental && ((Elemental)npc).getSummoner()!=null && ((Elemental)npc).getSummoner().isElementalSummoned());
			
			// Non-slave NPCs clean clothes:
			if(inGame) {
				if(!Main.game.getCharactersPresent().contains(npc)) {
					if(!npc.isSlave() || npc.hasSlavePermissionSetting(SlavePermissionSetting.CLEANLINESS_WASH_CLOTHES)) {
						npc.cleanAllClothing(true, false);
					}
					if(!npc.isSlave() || npc.hasSlavePermissionSetting(SlavePermissionSetting.CLEANLINESS_WASH_BODY)) {
						npc.cleanAllDirtySlots(true);
					}
				}
			}
			
			// Set NPC resource values:
			if(secondsPassedThisTurn>=0) {
				if(inGame) {
					if(!Main.game.isInCombat() && !Main.game.isInSex() && Main.game.isPrologueFinished()) { // Do not alter values during combat, sex, or prologue
						if(!Main.game.getPlayer().getCompanions().contains(npc)) {
							if(!Main.game.getCharactersPresent().contains(npc)) {
								npc.setHealthPercentage(1);
								npc.setManaPercentage(1);
							}
							npc.alignLustToRestingLust(secondsPassedThisTurn*10);
							
						} else {
							// Regenerate health and stamina over time:
							if (npc.getHealthPercentage() < 1) {
								npc.incrementHealth((secondsPassedThisTurn/60f) * npc.getRegenerationRate());
							}
							if (npc.getManaPercentage() < 1) {
								npc.incrementMana((secondsPassedThisTurn/60f) * npc.getRegenerationRate());
							}
							npc.alignLustToRestingLust(secondsPassedThisTurn);
						}
					}
				}
				if(!slavesUpdated || !npc.isSlave() || !npc.getOwner().isPlayer()) { // Player-owned slaves already had their status effects updated in the slavery events update loop
					npc.calculateStatusEffects(secondsPassedThisTurn);
				}
			}
			
			// Clothing and item management:
			if(inGame) {
				//TODO Not sure why this was here... Commented out in v0.3.8.2
//				if(getCharactersPresent().contains(npc) && !npc.isUnique() && !npc.isSlave()) {
//					npc.setPendingClothingDressing(true);
//				}
				
				// Replace clothing if not in player's tile:
				if(!Main.game.isInCombat()
						&& !Main.game.isInSex()
						&& !npc.isAllowingPlayerToManageInventory()
						&& (Main.game.getCurrentDialogueNode().equals(Main.game.getPlayerCell().getDialogue(false)) || !(getCharactersPresent().contains(npc)))) {
					if(hoursPassed>0 && npc.isPendingClothingDressing()) {
						npc.equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.ADD_WEAPONS));
						npc.setPendingClothingDressing(false);
						
					} else if(!npc.isSlave()
							&& !npc.isUnique()
							&& !npc.hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						// Try to replace clothing to cover themselves up:
						if((npc.hasStatusEffect(StatusEffect.EXPOSED) || npc.hasStatusEffect(StatusEffect.EXPOSED_BREASTS) || npc.hasStatusEffect(StatusEffect.EXPOSED_PLUS_BREASTS))
								 && hoursPassed>0) {
							npc.replaceAllClothing();
							
							npc.calculateStatusEffects(0);
							// If still exposed after this, get new clothes:
							if(npc.hasStatusEffect(StatusEffect.EXPOSED) || npc.hasStatusEffect(StatusEffect.EXPOSED_BREASTS) || npc.hasStatusEffect(StatusEffect.EXPOSED_PLUS_BREASTS)) {
								npc.equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.ADD_WEAPONS));
	
								if(loopDebug) {
									System.out.println(npc.getName(true)+" "+npc.getClass().getName()+" got dressed");
								}
							}
							npc.setPendingClothingDressing(false);
						}
						
						if(npc.hasStatusEffect(StatusEffect.STRETCHING_ORIFICE)) {
							for(AbstractClothing c : npc.getSexToyOrificeTooDeep().values()) {
								npc.unequipClothingIntoInventory(c, true, npc);
							}
							for(AbstractClothing c : npc.getSexToyOrificeStretching().values()) {
								npc.unequipClothingIntoInventory(c, true, npc);
							}
							for(AbstractClothing c : npc.getSexToyOrificePreventingStretchRecovery().values()) {
								npc.unequipClothingIntoInventory(c, true, npc);
							}
						}
					}
				}
			
				if(npc.isPendingTransformationToGenderIdentity()
						&& !npc.getLocation().equals(Main.game.getPlayer().getLocation())) {
					npc.setBodyToGenderIdentity(false);
				}
				
				// Prostitutes stay on slut pills to avoid pregnancies, and, if the NPC is male, to avoid knocking up their clients
				if((!npc.isPregnant()
						&& !npc.isSlave()
						&& npc.getHistory()==Occupation.NPC_PROSTITUTE
						&& !npc.hasStatusEffect(StatusEffect.PROMISCUITY_PILL)
						&& !npc.getLocation().equals(Main.game.getPlayer().getLocation()))
					|| (npc.isSlave() && npc.getSlavePermissionSettings().get(SlavePermission.PILLS).contains(SlavePermissionSetting.PILLS_PROMISCUITY_PILLS))) {
					npc.useItem(Main.game.getItemGen().generateItem("innoxia_pills_sterility"), npc, false);
				}
				
				if(npc.isSlave() && npc.getSlavePermissionSettings().get(SlavePermission.PILLS).contains(SlavePermissionSetting.PILLS_VIXENS_VIRILITY)) {
					npc.useItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), npc, false);
				}
				if(npc.isSlave() && npc.getSlavePermissionSettings().get(SlavePermission.PILLS).contains(SlavePermissionSetting.PILLS_BROODMOTHER)) {
					npc.useItem(Main.game.getItemGen().generateItem("innoxia_pills_broodmother"), npc, false);
				}
			}
			
			// Giving birth:
			if(npc.hasStatusEffect(StatusEffect.PREGNANT_3)
					&& !Main.game.getCharactersPresent().contains(npc)
					&& !Main.game.getPlayer().getCompanions().contains(npc)
					&& (Main.game.getSecondsPassed() - npc.getTimeProgressedToFinalPregnancyStage())>(12*60*60)) {
				if(npc instanceof Lilaya) {
					// Lilaya will only end pregnancy after you've seen it, or if she's a full demon:
					if(Main.game.getNpc(Lilaya.class).isCharacterReactedToPregnancy(Main.game.getPlayer()) || npc.getRaceStage()==RaceStage.GREATER) {
						npc.endPregnancy(true);
					}
					
				} else {
					if(npc.isSlave() && npc.getOwner().isPlayer()) {
						if(npc.getSlavePermissionSettings().get(SlavePermission.PREGNANCY).contains(SlavePermissionSetting.PREGNANCY_ALLOW_BIRTHING)) {
							npc.endPregnancy(true, false);
							
							List<String> events = Util.newArrayListOfValues(UtilText.parse(npc, "[npc.She] gave birth to:<br/>")+npc.getLastLitterBirthed().getBirthedDescription());
							SlaveryEventLogEntry entry = new SlaveryEventLogEntry(getHourOfDay(),
									npc,
									null,
									SlaveEvent.GAVE_BIRTH,
									null,
									events,
									true);
							Main.game.addSlaveryEvent(getDayNumber(), entry);
						}
						
					} else {
						npc.endPregnancy(true);
					}
					
					if(npc instanceof Kate) {
						Main.game.getDialogueFlags().values.remove(DialogueFlagValue.reactedToKatePregnancy);
					}
				}
			}
			
			// Laying implanted eggs:
			if(!npc.getIncubatingLitters().isEmpty()
					&& !Main.game.getCharactersPresent().contains(npc)
					&& !Main.game.getPlayer().getCompanions().contains(npc)) {
				for(Entry<SexAreaOrifice, Litter> entry : new HashMap<>(npc.getIncubatingLitters()).entrySet()) {
					long finalStageTime = npc.getTimeProgressedToFinalIncubationStage(entry.getKey());
//					System.out.println(finalStageTime+", "+(Main.game.getSecondsPassed() - finalStageTime));
					if(finalStageTime>0 && (Main.game.getSecondsPassed() - finalStageTime) > (12*60*60)) {
						if(npc.isSlave() && npc.getOwner().isPlayer()) {
							if(npc.getSlavePermissionSettings().get(SlavePermission.PREGNANCY).contains(SlavePermissionSetting.PREGNANCY_ALLOW_EGG_LAYING)) {
								npc.endIncubationPregnancy(entry.getKey(), true, false);
								
								String areaEgged = "";
								switch(entry.getKey()) {
									case ANUS:
									case MOUTH:
										areaEgged = "stomach";
										break;
									case NIPPLE:
										areaEgged = "[npc.breasts]";
										break;
									case NIPPLE_CROTCH:
										areaEgged = "[npc.crotchBoobs]";
										break;
									case SPINNERET:
										areaEgged = "[npc.spinneret]";
										break;
									case VAGINA:
										areaEgged = "womb";
										break;
									case ARMPITS:
									case ASS:
									case BREAST:
									case BREAST_CROTCH:
									case THIGHS:
									case URETHRA_PENIS:
									case URETHRA_VAGINA:
										break;
								}
								List<String> events = Util.newArrayListOfValues(UtilText.parse(npc, "[npc.She] completed [npc.her] "+areaEgged+" incubation and gave birth to:<br/>")+npc.getLastLitterIncubated().getBirthedDescription());
								SlaveryEventLogEntry incubationBirthEntry = new SlaveryEventLogEntry(getHourOfDay(),
										npc,
										null,
										SlaveEvent.GAVE_BIRTH_INCUBATION,
										null,
										events,
										true);
								Main.game.addSlaveryEvent(getDayNumber(), incubationBirthEntry);
							}
							
						} else {
							npc.endIncubationPregnancy(entry.getKey(), true);
							
						}
					}
				}
			}
			
			if(!npc.isRaceConcealed()
					&& npc.getWorldLocation()==Main.game.getPlayer().getWorldLocation()
					&& npc.getLocation().equals(Main.game.getPlayer().getLocation())) {
				for(CoverableArea ca : CoverableArea.values()) {
					if(npc.isCoverableAreaVisible(ca) && ca!=CoverableArea.MOUTH) {
						npc.setAreaKnownByCharacter(ca, Main.game.getPlayer(), true);
					}
				}
			}
			
			if(hoursPassed>=1) {
				 // Reset flags to their original values and then reset them to false during the loop so that the flags can be safely used in the hourlyUpdate()
				for(AbstractDialogueFlagValue value : flagsReset) {
					this.getDialogueFlags().setFlag(value, true);
				}
				for(int i=1; i<=hoursPassed; i++) {
					int incrementedHourOfDay = (startHourOfDay + i) % 24;
					for(AbstractDialogueFlagValue value : flagsReset) {
						if(value.getResetHour()==incrementedHourOfDay) {
							this.getDialogueFlags().setFlag(value, false);
						}
					}
					npc.hourlyUpdate(incrementedHourOfDay);
					if(inGame) {
						npc.performHourlyFluidsCheck();
					}
				}
			}
			
			if(newDay) {
				long tL = System.nanoTime();
				if(loopDebug && npc.isUnique()) {
					System.out.print(npc.getName(true)+" daily reset loop: ");
				}
				npc.resetDaysOrgasmCount();
				// Non-unique NPCs get a new inventory every day:
				// Do this before npc.dailyUpdate(), as the daily update method might need to set items (as is the case for reindeer overseers).
				if(!npc.isSlave()
						&& !npc.isElemental()
						&& !npc.isUnique()
						&& !Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId())
						&& !Main.game.isInCombat()
						&& !Main.game.isInSex()
						&& !npc.isAllowingPlayerToManageInventory()
						&& (Main.game.getCurrentDialogueNode().equals(Main.game.getPlayerCell().getDialogue(false)) || !(getCharactersPresent().contains(npc)))) {
					npc.clearNonEquippedInventory(false);
					Main.game.getCharacterUtils().regenerateItemsInInventory(npc);
				}
				try {
					npc.dailyUpdate();
				} catch(Exception ex) {
					System.err.println("Issue in method: dailyReset(), for character ID: "+npc.getId()+"\n"+ex.getMessage());
					ex.printStackTrace();
				}
				if(loopDebug && npc.isUnique()) {
					System.out.println((System.nanoTime()-tL)/1000000000f+"s");
				}
			}
			
			// Companions:
			ArrayList<GameCharacter> npcCompanions = new ArrayList<>(npc.getCompanions());
			for(GameCharacter companion : npcCompanions) {
				// Updating companion NPCs:
				companion.companionshipCheck();
			}
			for(GameCharacter character : npc.getCompanions()) {
				character.setLocation(npc.getWorldLocation(), npc.getLocation(), false);
			}
			
			npc.turnUpdate();
		}
		if(loopDebug) {
			System.out.println("NPC loop end. Time since start: "+(System.nanoTime()-tLoopStart)/1000000000f+"s");
		}
		isInNPCUpdateLoop = false;
		for(NPC npc : npcsToRemove) {
			banishNPC(npc);
		}
		for(NPC npc : npcsToAdd) {
			NPCMap.put(npc.getId(), npc);
		}
		npcsToRemove.clear();
		npcsToAdd.clear();
		if(loopDebug) {
			System.out.println("Removal handling ended: "+(System.nanoTime()-tLoopStart)/1000000000f+"s");
		}

		if(secondsPassedThisTurn>=0) {
			if (!isInCombat() && !isInSex() && !currentDialogueNode.isRegenerationDisabled() && Main.game.isPrologueFinished()) {// If not in combat, sex, prologue, or regen-disabled scene, handle resources values:
				// Regenerate health and stamina over time:
				if (Main.game.getPlayer().getHealthPercentage() < 1) {
					Main.game.getPlayer().incrementHealth((secondsPassedThisTurn/60) * Main.game.getPlayer().getRegenerationRate());
				}
				if (Main.game.getPlayer().getManaPercentage() < 1) {
					Main.game.getPlayer().incrementMana((secondsPassedThisTurn/60) * Main.game.getPlayer().getRegenerationRate());
				}
				Main.game.getPlayer().alignLustToRestingLust(secondsPassedThisTurn);
			}
			if(Main.game.getCurrentDialogueNode()!=MiscDialogue.STATUS_EFFECTS) { // Handle status effects:
				Main.game.getPlayer().calculateStatusEffects(secondsPassedThisTurn);
			}
		}
		
//		RenderingEngine.ENGINE.renderButtons();
		MainController.updateUI();

		if(loopDebug) {
			System.out.println("Rendering done: "+(System.nanoTime()-tLoopStart)/1000000000f+"s");
		}
		
		Main.mainController.getTooltip().hide();

		if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().hasNonArcaneEssences()) {
			Main.game.getPlayer().addStatusEffectDescription(null, Main.game.getPlayer().startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY));
		}
		if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY) && Main.game.getPlayer().isVisiblyPregnant()) {
			Main.game.getPlayer().addStatusEffectDescription(null, Main.game.getPlayer().startQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY));
		}
		if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION) && !Main.game.getPlayer().getIncubatingLitters().isEmpty()) {
			Main.game.getPlayer().addStatusEffectDescription(null, Main.game.getPlayer().startQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION));
		}
		
		if(!Main.game.getPlayer().getStatusEffectDescriptions().isEmpty()
				&& Main.game.getPlayer().getStatusEffectDescriptions().values().stream().anyMatch(m->!m.isEmpty())
				&& Main.game.getCurrentDialogueNode()!=MiscDialogue.STATUS_EFFECTS
				&& !Main.game.getCurrentDialogueNode().isTravelDisabled()
				&& !Main.game.isInSex()
				&& !Main.game.isInCombat()) {
			
			if(Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.NORMAL) {
				Main.game.saveDialogueNode();
			}

//			System.out.println("SE here");
//			for(Map<AbstractStatusEffect, String> e : Main.game.getPlayer().getStatusEffectDescriptions().values()) {
//				for(Entry<AbstractStatusEffect, String> entry : e.entrySet()) {
//					System.out.println(entry.getKey()+": "+entry.getValue());
//				}
//			}
			
			Main.game.setContent(new Response("", "", MiscDialogue.STATUS_EFFECTS){
				@Override
				public void effects() {
//					if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().hasNonArcaneEssences()) {
//						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY));
//					}
//					if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY) && Main.game.getPlayer().isVisiblyPregnant()) {
//						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY));
//					}
//					if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION) && !Main.game.getPlayer().getIncubatingLitters().isEmpty()) {
//						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION));
//					}
				}	
			});
			
//			Main.game.getPlayer().getStatusEffectDescriptions().clear();
		}
		if(!Main.game.getPlayer().getStatusEffectDescriptions().values().stream().anyMatch(m->!m.isEmpty())) {
			Main.game.getPlayer().getStatusEffectDescriptions().clear();
		}
		
		// Miscellaneous things:
		
		if(Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.NORMAL
				&& !Main.game.getCurrentDialogueNode().isTravelDisabled()) { // Catch slavery management NPC not correctly being assigned to null:
			Main.game.getDialogueFlags().setManagementCompanion(null);
		}
		
		if(loopDebug) {
			System.out.println((System.nanoTime()-tStart)/1000000000d+"s");
		}
	}
	
	private static void calculateBankInterest() {
		float APR = 0.12f; // 12%
		long savings = 0;
		float interest = 0;

		savings += Main.game.getWorlds().get(WorldType.getWorldTypeFromId("innoxia_dominion_bank")).getCell(PlaceType.getPlaceTypeFromId("innoxia_dominion_bank_deposit_box")).getInventory().getMoney();
		if(savings>0) {
			interest = (savings * APR ) / 365f;
			
			if(!Main.game.getDialogueFlags().hasSavedLong("bank_interest")) {
				Main.game.getDialogueFlags().setSavedLong("bank_interest", 0);
			}
			Main.game.getDialogueFlags().incrementSavedLong("bank_interest", Math.max(1, Math.round(interest)));
		}
	}
	
	public Season getSeason() {
		return Season.getSeasonFromMonth(getDateNow().getMonth());
	}
	
	// Set weather and time remaining.
	// Handles Lilith's Lust build up.
	// Appends description of storm gathering and breaking to mainController.
	private void handleAtmosphericConditions(int secondsPassed) {

		weatherTimeRemainingInSeconds -= secondsPassed;
		
		// Weather change:
		if (weatherTimeRemainingInSeconds < 0) {
			switch (currentWeather) {
				case CLEAR:
					if(getSecondsPassed() >= nextStormTimeInSeconds) {
						currentWeather = Weather.MAGIC_STORM_GATHERING;
						weatherTimeRemainingInSeconds = (int) (gatheringStormDurationInSeconds - (getSecondsPassed() - nextStormTimeInSeconds));
					} else {
						currentWeather = Weather.CLOUD;
						weatherTimeRemainingInSeconds = (2*60 + Util.random.nextInt(2 * 60))*60; // Clouds last for 2-4 hours
					}
					break;
					
				case CLOUD:
					if(getSecondsPassed() >= nextStormTimeInSeconds) {
						currentWeather = Weather.MAGIC_STORM_GATHERING;
						weatherTimeRemainingInSeconds = (int) (gatheringStormDurationInSeconds - (getSecondsPassed() - nextStormTimeInSeconds));
					} else {
						if (Math.random() > 0.4) { // 40% chance that will start raining
							if(getSeason()==Season.WINTER) {
								currentWeather = Weather.SNOW;
							} else {
								currentWeather = Weather.RAIN;
							}
							weatherTimeRemainingInSeconds = (1 * 60 + Util.random.nextInt(5 * 60))*60; // Rain lasts for 1-6 hours
						} else {
							currentWeather = Weather.CLEAR;
							weatherTimeRemainingInSeconds= (4 * 60 + Util.random.nextInt(4 * 60))*60; // Clear weather lasts for 4-8 hours
						}
					}
					break;
					
				case MAGIC_STORM:
					nextStormTimeInSeconds = getSecondsPassed() + ((60*48) + (60*Util.random.nextInt(24)))*60; // Next storm in 2-3 days
					gatheringStormDurationInSeconds = (4 * 60 + Util.random.nextInt(2 * 60))*60;
					currentWeather = Weather.CLEAR;
					weatherTimeRemainingInSeconds = (4 * 60 + Util.random.nextInt(4 * 60))*60;
					break;
					
				case MAGIC_STORM_GATHERING:
					currentWeather = Weather.MAGIC_STORM;
					Main.game.getDialogueFlags().values.add(DialogueFlagValue.stormTextUpdateRequired);
					weatherTimeRemainingInSeconds = (8 * 60 + Util.random.nextInt(4 * 60))*60; // Storm lasts 8-12 hours
					break;
					
				case RAIN: case SNOW:
					if(getSecondsPassed() >= nextStormTimeInSeconds) {
						currentWeather = Weather.MAGIC_STORM_GATHERING;
						weatherTimeRemainingInSeconds = (int) (gatheringStormDurationInSeconds - (getSecondsPassed() - nextStormTimeInSeconds));
					} else {
						currentWeather = Weather.CLOUD;
						weatherTimeRemainingInSeconds = (2*60 + Util.random.nextInt(2 * 60))*60; // Clouds last for 2-4 hours
					}
					break;
					
				default:
					break;
			}
		}
	}

	public long getNextStormTimeInSeconds() {
		return nextStormTimeInSeconds;
	}
	
	public String getNextStormTimeAsTimeString() {
		long minutes = ((nextStormTimeInSeconds+gatheringStormDurationInSeconds)-getSecondsPassed())/60;
		long hours = minutes/60;
		return (hours/24)+" days, "+hours%24+" hours, "+minutes%60+" minutes";
	}
	
	public Weather getWeather() {
		return currentWeather;
	}
	
	public Weather getCurrentWeather() { // An old name for the method above, still used in xml files so don't delete
		return getWeather();
	}
	
	/**
	 * Sets the content of the main WebView based on the response of the current Dialogue Node's index.
	 * 
	 * @param index The dialogue choice index.
	 */
	private int positionAnchor = 0;
	private String dialogueTitle = "";
	public void setContent(int index) {
		if(OptionsDialogue.startingNewGame) {
			return;
		}
		informationTooltips = new HashMap<>();
		
		Response response = currentDialogueNode.getResponse(responseTab, index);
		
		if (response != null) {
			String corruptionGains = "";
			if(response.isActionCorrupting() && !response.isAvailableFromFetishes()) {
				Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_CORRUPTION, response.getCorruptionNeeded().getCorruptionBypass());
				corruptionGains = ("<p style='text-align:center;'>"
						+ "<b>You have gained +"+response.getCorruptionNeeded().getCorruptionBypass()+"</b> <b style='color:"+Attribute.MAJOR_CORRUPTION.getColour().toWebHexString()+";'>corruption</b><b>!</b>"
						+ "</p>");
			};

			if(!response.isAvailable() && !response.isAbleToBypass()) {
				return;
			}
			
			String chosenResponse = response.getTitle();
			DialogueNode node = response.getNextDialogue();
			if(node!=null || response instanceof ResponseCombat || response instanceof ResponseSex || response instanceof ResponseEffectsOnly || response instanceof ResponseTrade) {
				response.applyEffects(); // Only apply effects if this response is a non-standard one or if the next response is not null
			}
			if(node!=null) {
				node.specialPreParsingEffects();
				node.applyPreParsingEffects();
			}
			
			if(response instanceof ResponseCombat) {
				setContent(new Response("", "", ((ResponseCombat)response).initCombat()));
				return;
				
			} else if(response instanceof ResponseSex) {
				setContent(new Response("", "", ((ResponseSex)response).initSex()));
				Main.sex.postSexInitSetup();
				((ResponseSex)response).postSexInitEffects();
				Main.mainController.updateUILeftPanel();
				return;
				
			} else if(response instanceof ResponseEffectsOnly) {
				if(Main.game.isStarted()) {
					Main.game.endTurn(response, null); // Only increment time if response overrides getSecondsPassed
					if(response.postEndTurnEffects()) {
						Main.game.endTurn(0);
					}
				}
				return;
				
			} else if(response instanceof ResponseTrade) {
				((ResponseTrade)response).openTrade();
				return;
			}
			
			
			if (node != null) {
				// Add characters in this scene to the player's encountered characters list:
				if(started) {
					if (!getCharactersPresent().isEmpty()) {
						for (GameCharacter character : getCharactersPresent()) {
							if (!Main.game.getPlayer().getCharactersEncountered().contains(character.getId())) {
								if ((character instanceof NPC)) {
									if (((NPC) character).isAddedToContacts() && character.isPlayerKnowsName()) {
										Main.game.getPlayer().addCharacterEncountered(character);
									}
									if(!character.isRaceConcealed()) {
										Main.getProperties().addRaceDiscovered(character.getSubspecies());
									}
									((NPC) character).setLastTimeEncountered(getMinutesPassed());
								}
							}
						}
					}
				}
				
				String headerContent = node.getHeaderContent();
				String content;
				try {
					if(response.isStripContent()) {
						content = "";
					} else {
						content = node.getContent();
					}
				} catch(Exception ex) {
					content = "<p style='text-align:center;'>"
								+ "[style.italicsBad(Error: getContent() method is throwing an exception in the node: '"+node.getLabel()+"')]"
							+ "</p>";
					
					// Hopefully bug reports will include this ;_;
					content += "<p style='font-size:0.75em;'>";
						for(StackTraceElement ste : ex.getStackTrace()) {
							content += "<br/>"+ste.toString();
						}
					content += "</p>";
					
					ex.printStackTrace();
				}
				if(content==null) {
					content = "";
				}

				if (currentDialogueNode != null) {
					if (node.isContinuesDialogue() || response.isForceContinue()) {
						if(Main.game.isInSex()) {
							dialogueTitle = UtilText.parse(node.getLabel());
						}

						if(node.isDisplaysActionTitleOnContinuesDialogue()) {
							if (currentDialogueNode.getDialogueNodeType() == DialogueNodeType.NORMAL) {
								positionAnchor++;
							}
						
							pastDialogueSB.append("<hr id='position" + positionAnchor + "'/><p class='option-disabled' style='padding-bottom:0; margin-bottom:0;'>&gt " + chosenResponse + "</p>");
						}
						
						if (getMapDisplay() == DialogueNodeType.NORMAL) {
							initialPositionAnchor = positionAnchor;
						}
					} else {
						dialogueTitle = UtilText.parse(node.getLabel());
						
						if (getMapDisplay() == DialogueNodeType.NORMAL)
							initialPositionAnchor = positionAnchor;

						if (currentDialogueNode.getDialogueNodeType() == DialogueNodeType.NORMAL)
							positionAnchor = 0;
						
						pastDialogueSB.setLength(0);
					}
					String dialogueParsed = UtilText.parse(corruptionGains + textStartStringBuilder.toString())
						+ (node.isContentParsed() ? UtilText.parse(content) : content)
						+ UtilText.parse(textEndStringBuilder.toString());
					if(Main.game.isStarted() && Main.game.getPlayer().getHistory()==Occupation.TOURIST) {
						dialogueParsed = UtilText.convertToAmericanEnglish(dialogueParsed);
					}
					pastDialogueSB.append(dialogueParsed);
					if(isAutomaticDialogueCopy() && Main.game.isStarted()) {
						Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
						StringSelection data = new StringSelection(formatContentForAutomaticClipboard(dialogueParsed));
						clipboard.setContents(data, data);
					}

				} else {
					dialogueTitle = UtilText.parse(node.getLabel());
				}
				// currentDialogueNode.applyResponse(index, true);
				// updateUIAttributes();


				boolean resetPointer = true;
				if(node != currentDialogueNode) {
					responsePage = 0;
					currentDialogueNode = node;
					
				} else {
					currentDialogueNode = node;
					checkForResponsePage();
					resetPointer = false;
				}
				checkForResponseTab();
				
				if(Main.game!=null
						&& Main.game.isStarted()
						&& Main.game.isRequestAutosave()
						&& (Main.game.getCurrentDialogueNode()!=null && !Main.game.getCurrentDialogueNode().isTravelDisabled())) {
					lastAutoSaveTime = Main.game.getSecondsPassed();
					Main.saveGame("AutoSave_"+Main.game.getPlayer().getName(false), true, true);
					Main.game.setRequestAutosave(false);
				}
				
				
				if (node.isContinuesDialogue() || response.isForceContinue()) {
					currentDialogue = 
								"<div id='main-content'>"
									+ getTitleDiv(getDialogueTitle())
									+ "<div class='div-center' id='content-block'>"
//										+ "<div class='inner-text-content'>"
											+ getMapDiv()
											+ (headerContent != null
												? "<div id='header-content' style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px; -webkit-user-select: none;'>"
													+ (currentDialogueNode.disableHeaderParsing() ? headerContent : UtilText.parse(headerContent))
													+ "</div>"
												: "")
											+ (content != null
													? "<div "+(Main.getProperties().hasValue(PropertyValue.fadeInText)?"id='text-content'":"")
															+" style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;"
																+ (requiresYScroll(node)?" overflow-y:scroll; overflow-x:hidden;":"")+"'>"
															+ pastDialogueSB.toString()
														+ "</div>"
													: "")
	//												+ textStartStringBuilder.toString() + pastDialogueSB.toString() + textEndStringBuilder.toString() + "</div>" : "")
//										+ "</div>"
									+ "</div>"
									+"<div id='bottom-text'>Game saved!</div>"
									+ getResponsesDiv(currentDialogueNode, resetPointer)
								+ "</div>"
							+ "</body>";

				} else {
					currentDialogue = "<div id='main-content'>"
								+ getTitleDiv(getDialogueTitle())
								+ "<span id='position" + positionAnchor + "'></span>"
								+ "<div class='div-center' id='content-block'>"
//									+ "<div class='inner-text-content'>"
										+ getMapDiv()
										+ (headerContent != null
											? "<div id='header-content' style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;-webkit-user-select: none;'>"
												+ (currentDialogueNode.disableHeaderParsing() ? headerContent : UtilText.parse(headerContent))
												+ "</div>"
											: "")
										+ (content != null
												? "<div "+(Main.getProperties().hasValue(PropertyValue.fadeInText)?"id='text-content'":"")
														+" style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;"
															+ (requiresYScroll(node)?" overflow-y:scroll; overflow-x:hidden;":"")+ "'>"
														+ pastDialogueSB.toString()
													+ "</div>"
												: "")
	//									+ textStartStringBuilder.toString() + pastDialogueSB.toString() + textEndStringBuilder.toString() + "</div>" : "")
//									+ "</div>"
								+ "</div>"
								+"<div id='bottom-text'>Game saved!</div>"
								+ getResponsesDiv(currentDialogueNode, resetPointer)
							+ "</div>"
							+ "</body>";
				}

//				Main.mainController.unbindListeners();
				setMainContentRegex(
						((node.isContinuesDialogue() || response.isForceContinue()) && isContentScroll(node)
							?"<body onLoad='scrollToElement()'>"
							+ "<script>function scrollToElement() {document.getElementById('content-block').scrollTop = document.getElementById('position" + (positionAnchor) + "').offsetTop -64;}</script>"
						:"<body>"),
						currentDialogue);
				
				textEndStringBuilder.setLength(0);
				textStartStringBuilder.setLength(0);

				if(started) {
					Main.game.endTurn(response, node);
					if(response.postEndTurnEffects()) {
						Main.game.endTurn(0);
					}
				}
				
				TooltipUpdateThread.cancelThreads=true;
				//Main.mainController.processNewDialogue();
			}
		}
	}

	public void setContent(Response response, boolean allowTimeProgress, Colour flashMessageColour, String flashMessageText){
		informationTooltips = new HashMap<>();
		
		DialogueNode node = response.getNextDialogue();
		if(node!=null || response instanceof ResponseCombat || response instanceof ResponseSex || response instanceof ResponseEffectsOnly || response instanceof ResponseTrade) {
			response.applyEffects(); // Only apply effects if this response is a non-standard one or if the next response is not null
		}
		if(node!=null) {
			node.specialPreParsingEffects();
			node.applyPreParsingEffects();
		}
		
		if(response instanceof ResponseCombat) {
			setContent(new Response("", "", ((ResponseCombat)response).initCombat()));
			return;
			
		} else if(response instanceof ResponseSex) {
			setContent(new Response("", "", ((ResponseSex)response).initSex()));
			Main.sex.postSexInitSetup();
			((ResponseSex)response).postSexInitEffects();
			Main.mainController.updateUILeftPanel();
			return;
			
		} else if(response instanceof ResponseEffectsOnly) {
			if(Main.game.isStarted()) {
				Main.game.endTurn(response, null); // Only increment time if response overrides getSecondsPassed
				if(response.postEndTurnEffects()) {
					Main.game.endTurn(0);
				}
			}
			return;
			
		} else if(response instanceof ResponseTrade) {
			((ResponseTrade)response).openTrade();
			return;
		}
		
		if (node == null){
			return;
		}
		
		int currentPosition = 0;
		if(getCurrentDialogueNode()!=null) {
			if(!Main.game.isInSex() || Main.sex.getTurn()>1) { // First turn of sex should always reset to top
				currentPosition =  (int) Main.mainController.getWebEngine().executeScript("document.getElementById('content-block').scrollTop");
			}
		}
		
		String headerContent = node.getHeaderContent();
		String content;
		try {
			if(response.isStripContent()) {
				content = "";
			} else {
				content = node.getContent();
			}
		} catch(Exception ex) {
			content = "<p style='text-align:center;'>"
						+ "[style.italicsBad(Error: getContent() method is throwing an exception in the node: '"+node.getLabel()+"')]"
					+ "</p>";
			
			// Hopefully bug reports will include this ;_;
			content += "<p style='font-size:0.75em;'>";
				for(StackTraceElement ste : ex.getStackTrace()) {
					content += "<br/>"+ste.toString();
				}
			content += "</p>";
			
			ex.printStackTrace();
		}
		if(content==null) {
			content = "";
		}
		boolean resetPointer = false;
		
		if (getMapDisplay() == DialogueNodeType.NORMAL) {
			initialPositionAnchor = positionAnchor;
		}
		
		
		// Add characters in this scene to the player's encountered characters list:
		if(started) {
			if (!getCharactersPresent().isEmpty()) {
				for (GameCharacter character : getCharactersPresent()) {
					if (character instanceof NPC) {
						if (((NPC) character).isAddedToContacts() && character.isPlayerKnowsName()) {
							Main.game.getPlayer().addCharacterEncountered(character);
						}
						if(!character.isRaceConcealed()) {
							Main.getProperties().addRaceDiscovered(character.getSubspecies());
						}
						((NPC) character).setLastTimeEncountered(getMinutesPassed());
					}
				}
			}
		}
		
		String dialogueParsed;
		if (currentDialogueNode != null) {
			if (node.isContinuesDialogue() || response.isForceContinue()) {
				if(Main.game.isInSex()) {
					dialogueTitle = UtilText.parse(node.getLabel());
				}
				
				if(node.isDisplaysActionTitleOnContinuesDialogue()) {
					if (currentDialogueNode.getDialogueNodeType() == DialogueNodeType.NORMAL) {
						positionAnchor++;
					}
					
					pastDialogueSB.append(UtilText.parse("<hr id='position" + positionAnchor + "'><p class='option-disabled'>&gt " + currentDialogueNode.getLabel() + "</p>"));
				}
				
				dialogueParsed = UtilText.parse(
					textStartStringBuilder.toString())
					+ (node.isContentParsed() ? UtilText.parse(content) : content)
					+ UtilText.parse(textEndStringBuilder.toString());

			} else {
				dialogueTitle = UtilText.parse(node.getLabel());
				if (currentDialogueNode.getDialogueNodeType() == DialogueNodeType.NORMAL) {
					positionAnchor = 0;
				}
				
				pastDialogueSB.setLength(0);
				
				dialogueParsed = UtilText.parse(
					"<b id='position" + positionAnchor + "'></b>"
					+ textStartStringBuilder.toString())
					+ (node.isContentParsed() ? UtilText.parse(content) : content)
					+ UtilText.parse(textEndStringBuilder.toString());
			}
		} else {
			dialogueTitle = UtilText.parse(node.getLabel());
			pastDialogueSB.setLength(0);
			
			dialogueParsed = UtilText.parse(textStartStringBuilder.toString())
				+ (node.isContentParsed() ? UtilText.parse(content) : content)
				+ UtilText.parse(textEndStringBuilder.toString());
		}

		if(Main.game.isStarted() && Main.game.getPlayer().getHistory()==Occupation.TOURIST) {
			dialogueParsed = UtilText.convertToAmericanEnglish(dialogueParsed);
		}
		pastDialogueSB.append(dialogueParsed);
		if(isAutomaticDialogueCopy() && Main.game.isStarted()) {
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			StringSelection data = new StringSelection(formatContentForAutomaticClipboard(dialogueParsed));
			clipboard.setContents(data, data);
		}
		
		if(node != currentDialogueNode) {
			responsePage = 0;
			currentDialogueNode = node;
			resetPointer = true;
			
		} else {
			currentDialogueNode = node;
			checkForResponsePage();
			resetPointer = false;
		}
		checkForResponseTab();
		
		if(Main.game!=null
				&& Main.game.isStarted()
				&& Main.game.isRequestAutosave()
				&& (Main.game.getCurrentDialogueNode()!=null && !Main.game.getCurrentDialogueNode().isTravelDisabled())) {
			lastAutoSaveTime = Main.game.getSecondsPassed();
			Main.saveGame("AutoSave_"+Main.game.getPlayer().getName(false), true, true);
			Main.game.setRequestAutosave(false);
		}


		if (node.isContinuesDialogue() || response.isForceContinue()) {
			currentDialogue = "<div id='main-content'>"
						+ getTitleDiv(getDialogueTitle())
						+ "<div class='div-center' id='content-block'>"
//							+ "<div class='inner-text-content'>"
								+ getMapDiv()
								+ (headerContent != null
									? "<div id='header-content' style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;-webkit-user-select: none;'>"
										+ (currentDialogueNode.disableHeaderParsing() ? headerContent : UtilText.parse(headerContent))
										+ "</div>"
									: "") 
								+ (content != null
									? "<div "+(Main.getProperties().hasValue(PropertyValue.fadeInText)?"id='text-content'":"")
											+" style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;"
												+ (requiresYScroll(node)?" overflow-y:scroll; overflow-x:hidden;":"")+ "'>"
									+ pastDialogueSB.toString() + "</div>" : "")
	//									+ textStartStringBuilder.toString() + pastDialogueSB.toString() + textEndStringBuilder.toString() + "</div>" : "")
//							+ "</div>"
						+ "</div>"
						+"<div id='bottom-text'>Game saved!</div>"
						+ getResponsesDiv(currentDialogueNode, resetPointer)
					+ "</div>"
				+ "</body>";

		} else {
			currentDialogue = "<div id='main-content'>"
						+ getTitleDiv(getDialogueTitle())
						+ "<span id='position" + positionAnchor + "'></span>"
							+ "<div class='div-center' id='content-block'>"
//								+ "<div class='inner-text-content'>"
									+ getMapDiv()
									+ (headerContent != null
										? "<div id='header-content' style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;-webkit-user-select: none;'>"
											+ (currentDialogueNode.disableHeaderParsing() ? headerContent : UtilText.parse(headerContent))
											+ "</div>"
										: "")
									+ (content != null
										? "<div "+(Main.getProperties().hasValue(PropertyValue.fadeInText)?"id='text-content'":"")
												+" style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;"
													+ (requiresYScroll(node)?" overflow-y:scroll; overflow-x:hidden;":"")+ "'>"
												+ pastDialogueSB.toString()
											+ "</div>"
										: "")
	//								+ textStartStringBuilder.toString() + pastDialogueSB.toString() + textEndStringBuilder.toString() + "</div>" : "")
//								+ "</div>"
							+ "</div>"
						+"<div id='bottom-text'>Game saved!</div>"
						+ getResponsesDiv(currentDialogueNode, resetPointer)
					+ "</div>"
				+ "</body>";

		}
	
		Main.mainController.setFlashMessageColour(flashMessageColour);
		Main.mainController.setFlashMessageText(flashMessageText);

		//-------------------- MEMORY LEAK PROBLEM
		setMainContentRegex(node.isContinuesDialogue() || response.isForceContinue()
				?(isContentScroll(node)
					?"<body onLoad='scrollToElement()'>"
						+ "<script>function scrollToElement() {document.getElementById('content-block').scrollTop = document.getElementById('position" + (positionAnchor) + "').offsetTop -64;}</script>"
					:"<body>")
				:(isContentScroll(node)
					?"<body onLoad='scrollToElement()'>"
						+ "<script>function scrollToElement() {document.getElementById('content-block').scrollTop = "+currentPosition+";}</script>"
					:"<body>"),
				currentDialogue);
		//--------------------
		
		textEndStringBuilder.setLength(0);
		textStartStringBuilder.setLength(0);

		//-------------------- MEMORY LEAK PROBLEM
		if(started) {
			if(allowTimeProgress) {
				Main.game.endTurn(response, node);
				if(response.postEndTurnEffects()) {
					Main.game.endTurn(0);
				}
			} else {
				Main.game.endTurn(0);
				if(response.postEndTurnEffects()) {
					Main.game.endTurn(0);
				}
			}
		}
		//--------------------
		
	}
	
	private boolean requiresYScroll(DialogueNode node) {
		return currentDialogueNode.getDialogueNodeType()==DialogueNodeType.INVENTORY
				&& (!node.equals(InventoryDialogue.DYE_CLOTHING)
						&& !node.equals(InventoryDialogue.DYE_CLOTHING_CHARACTER_CREATION)
						&& !node.equals(InventoryDialogue.DYE_EQUIPPED_CLOTHING)
						&& !node.equals(InventoryDialogue.DYE_EQUIPPED_CLOTHING_CHARACTER_CREATION)
						&& !node.equals(InventoryDialogue.DYE_EQUIPPED_WEAPON)
						&& !node.equals(InventoryDialogue.DYE_WEAPON));
	}
	
	private static boolean isContentScroll(DialogueNode node) {
		return (node.getDialogueNodeType()!=DialogueNodeType.CHARACTERS_PRESENT
				&& !node.equals(PhoneDialogue.CHARACTER_APPEARANCE)
				&& !node.equals(PhoneDialogue.CONTACTS_CHARACTER))
				|| node.equals(CharactersPresentDialogue.PERKS)
				|| node.equals(BodyChanging.BODY_CHANGING_ASS)
				|| node.equals(BodyChanging.BODY_CHANGING_BREASTS)
				|| node.equals(BodyChanging.BODY_CHANGING_CORE)
				|| node.equals(BodyChanging.BODY_CHANGING_EYES)
				|| node.equals(BodyChanging.BODY_CHANGING_HAIR)
				|| node.equals(BodyChanging.BODY_CHANGING_HEAD)
				|| node.equals(BodyChanging.BODY_CHANGING_PENIS)
				|| node.equals(BodyChanging.BODY_CHANGING_VAGINA)
				|| node.equals(InventoryDialogue.DYE_CLOTHING)
				|| node.equals(InventoryDialogue.DYE_CLOTHING_CHARACTER_CREATION)
				|| node.equals(InventoryDialogue.DYE_EQUIPPED_CLOTHING)
				|| node.equals(InventoryDialogue.DYE_EQUIPPED_CLOTHING_CHARACTER_CREATION)
				|| node.equals(InventoryDialogue.DYE_EQUIPPED_WEAPON)
				|| node.equals(InventoryDialogue.DYE_WEAPON);
	}
	
	private String getDialogueTitle() {
		if(isBadEnd()) {
			return "<b style='color:"+PresetColour.GENERIC_BAD_END.toWebHexString()+";'>Bad End: "+Main.getProperties().badEndTitle+"</b>";
		}
		return dialogueTitle;
	}
	
	private String getTitleDiv(String title) {
		if(title.isEmpty()) {
			return "";
		}
		
		return "<div class='content-title'>"
//					+ "<div class='title-button' id='copy-content-button'>"+SVGImages.SVG_IMAGE_PROVIDER.getCopyIcon()+"</div>"
//					+ (Main.game.getCurrentDialogueNode().equals(CharactersPresentDialogue.MENU)
//							|| Main.game.getCurrentDialogueNode().equals(PhoneDialogue.CHARACTER_APPEARANCE)
//							|| Main.game.getCurrentDialogueNode().equals(PhoneDialogue.CONTACTS_CHARACTER)
//							?"<div class='title-button' id='export-character-button' style='left:auto;right:4px;'>"+SVGImages.SVG_IMAGE_PROVIDER.getExportIcon()+"</div>"
//							:"")
					+ "<h4 style='text-align:center;'>" + title + "</h4>"
				+ "</div>";
	}
	
	private String getMapDiv() {
		return "";
	}
	
	/**
	 * Sets the content of the main WebView based on a DialogueNode.
	 * 
	 * @param response
	 */
	public void setContent(Response response) {
		setContent(response, true, null, null);
	}
	
	public void setContent(Response response, boolean allowTimeProgress) {
		setContent(response, allowTimeProgress, null, null);
	}
	
	public void setContent(Response response, Colour colour, String messageText) {
		setContent(response, true, colour, messageText);
	}
	
	private void resetResponsePointer() {
		responsePointer=responsePage*MainController.RESPONSE_COUNT;
		
		for (int i=responsePage*MainController.RESPONSE_COUNT; i<responsePage*MainController.RESPONSE_COUNT+(MainController.RESPONSE_COUNT-1); i++) {
			if(currentDialogueNode.getResponse(responseTab, i) != null) {
				responsePointer = i;
				break;
			}
		}
	}
	
	private void checkForResponsePage() {
		for (int i = responsePage*MainController.RESPONSE_COUNT; i<responsePage*MainController.RESPONSE_COUNT+(MainController.RESPONSE_COUNT-1); i++) {
			if(currentDialogueNode.getResponse(responseTab, i) != null) {
				return;
			}
		}
		responsePage=0;
	}
	
	public boolean decrementResponseTab() {
		for(int i = -1; i > -6; i--) {
			if(currentDialogueNode.getResponseTabTitle(responseTab+i)!=null) {
				responseTab+=i;
				checkForResponsePage();
				return true;
			}
		}
		return false;
	}
	
	public boolean incrementResponseTab() {
		for(int i=1; i<6; i++) {
			if(currentDialogueNode.getResponseTabTitle(responseTab+i)!=null) {
				responseTab+=i;
				checkForResponsePage();
				return true;
			}
		}
		return false;
	}
	
	private void checkForResponseTab() {
		if(currentDialogueNode.getResponseTabTitle(responseTab)==null) {
			// I felt like it was more intuitive to go back to 0 rather than the nearest tab.
//			for(int i=responseTab; i>0; i--) {
//				if(currentDialogueNode.getResponseTabTitle(i)!=null) {
//					responseTab = i;
//					return;
//				}
//			}
			responseTab=0;
		}
	}
	
	public void updateResponses() {
		String content = getResponsesDiv(Main.game.getCurrentDialogueNode(), false);
		content=content.replaceAll("\r", "");
		content=content.replaceAll("\n", "");
		content=content.replaceAll("\"", "'");
		Main.mainController.getWebEngine().executeScript("document.getElementById('RESPONSE_BOX').innerHTML = \""+content+"\"");
		MainController.setResponseEventListeners();
	}
	
	/**
	 * Create the response box html.
	 */
	private String getResponsesDiv(DialogueNode node) {
		return getResponsesDiv(node, true);
	}

	private String getResponsesDiv(DialogueNode node, boolean withPointerReset) {
		if(withPointerReset) {
			resetResponsePointer();
		}
		
		choicesDialogueSB = new StringBuilder();

		choicesDialogueSB.append("<div id='RESPONSE_BOX'>");
		
		if(node.getResponseTabTitle(0) != null && !node.getResponseTabTitle(0).isEmpty()) {
			choicesDialogueSB.append("<div class='response-container tabs'>");
			
			int responsePageCounter = 0;
			while (node.getResponseTabTitle(responsePageCounter) != null){
				choicesDialogueSB.append(
						"<div class='response-tab"+(responseTab==responsePageCounter?" selected'":"'")
							+ (isResponseTabEmpty(node, responsePageCounter)
									?"style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'"
									:(responseTab==responsePageCounter
										?""
										:"style='color:"+PresetColour.TEXT_HALF_GREY.toWebHexString()+";'"))
							+" id='tab_" + responsePageCounter + "'>"
							+(responsePageCounter==responseTab-1
								?"<b class='hotkey-icon'>"
									+ (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_PREVIOUS_TAB) == null
										? "" 
										: Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_PREVIOUS_TAB).getFullName()) + "</b>"
								:(responsePageCounter==responseTab+1
									?"<b class='hotkey-icon'>"
										+ (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_NEXT_TAB) == null
											? ""
											: Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_NEXT_TAB).getFullName()) + "</b>"
									:""))
//							+ (responseTab==responsePageCounter+1?"<b class='hotkey-icon'>" + KeyboardAction.RESPOND_PREVIOUS_PAGE + "</b>" : "" )
							+ UtilText.parse(node.getResponseTabTitle(responsePageCounter))
						+"</div>");
				responsePageCounter++;
			}
			choicesDialogueSB.append("</div>");
			
		} else {
			responseTab = 0;
		}

		choicesDialogueSB.append("<div class='response-full-container'>");
		
		if (responsePage > 0) {
			choicesDialogueSB.append("<div class='response-switcher left' id='switch_left'><b class='hotkey-icon'>"
					+ (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_PREVIOUS_PAGE) == null ? "" : Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_PREVIOUS_PAGE).getFullName()) + "</b>&#60</div>");
		} else {
			choicesDialogueSB.append("<div class='response-switcher left disabled' id='switch_left'><b class='hotkey-icon disabled'>"
					+ (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_PREVIOUS_PAGE) == null ? "" : Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_PREVIOUS_PAGE).getFullName())
					+ "</b><span class='option-disabled'>&#60</span></div>");
		}
		
		choicesDialogueSB.append("<div class='response-container'>");
		
		Response response;
		if (responsePage == 0) {
			for (int i = 1; i < MainController.RESPONSE_COUNT; i++) {
				response = node.getResponse(responseTab, i);
				if (response != null) {
					choicesDialogueSB.append(getResponseBoxDiv(response, i));
				} else
					choicesDialogueSB.append("<div class='response-box disabled"+(responsePointer==i?" selected":"")+"' id='option_" + i + "'>"
												+ "<b class='hotkey-icon disabled'>" + getResponseHotkey(i) + "</b>"
											+ "</div>");
			}
			response = node.getResponse(responseTab, 0);
			if (response != null) {
				choicesDialogueSB.append(getResponseBoxDiv(response, 0));

			} else
				choicesDialogueSB.append("<div class='response-box disabled"+(responsePointer==0?" selected":"")+"' id='option_0'>"
											+ "<b class='hotkey-icon disabled'>" + getResponseHotkey(0) + "</b>"
										+ "</div>");
			
		} else {
			for (int i = 0; i < (MainController.RESPONSE_COUNT-1); i++) {
				response = node.getResponse(responseTab, i + (responsePage * MainController.RESPONSE_COUNT));
				if (response != null) {
					choicesDialogueSB.append(getResponseBoxDiv(response, i + 1));
				} else {
					choicesDialogueSB.append("<div class='response-box disabled"+(responsePointer-(responsePage*MainController.RESPONSE_COUNT)==i?" selected":"")+"' id='option_" + (i + 1) + "'>"
												+ "<b class='hotkey-icon disabled'>" + getResponseHotkey(i + 1) + "</b>"
											+ "</div>");
				}
			}
			response = node.getResponse(responseTab, MainController.RESPONSE_COUNT-1 + (responsePage * MainController.RESPONSE_COUNT));
			if (response != null) {
				choicesDialogueSB.append(getResponseBoxDiv(response, 0));
			} else {
				choicesDialogueSB.append("<div class='response-box disabled"+(responsePointer-((responsePage+1)*MainController.RESPONSE_COUNT)+1==0?" selected":"")+"' id='option_0'>"
												+ "<b class='hotkey-icon disabled'>" + getResponseHotkey(0) + "</b>"
											+ "</div>");
			}
			
		}
		choicesDialogueSB.append("</div>");
		
		if (node.getResponse(responseTab, ((responsePage + 1) * MainController.RESPONSE_COUNT)) != null){
			choicesDialogueSB.append("<div class='response-switcher right' id='switch_right'><b class='hotkey-icon'>"
					+ (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_NEXT_PAGE) == null ? "" : Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_NEXT_PAGE).getFullName()) + "</b>" + "&#62</div>");
			
		}else{
			choicesDialogueSB.append("<div class='response-switcher right disabled' id='switch_right'><b class='hotkey-icon disabled'>"
					+ (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_NEXT_PAGE) == null ? "" : Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_NEXT_PAGE).getFullName())
					+ "</b><span class='option-disabled'>&#62</span></div>");
		}
		
		choicesDialogueSB.append("</div>");
		choicesDialogueSB.append("</div>");
		
		return choicesDialogueSB.toString();
	}
	
	private boolean isResponseTabEmpty(DialogueNode node, int responseTab) {
		for (int i = 1; i < MainController.RESPONSE_COUNT; i++) {
			if(node.getResponse(responseTab, i)!=null) {
				return false;
			}
		}
		
		return true;
	}

	/**
	 * Strips html tags so that content is formatted in a way that makes it easier to read.
	 */
	public String formatContentForAutomaticClipboard(String input) {
		input = input.replaceAll("\t", "");
		input = input.replaceAll("\n", "");
		input = input.replaceAll("</p><p>", "\n\n");
		input = input.replaceAll("<(.*?)>", "");
		
		return input;
	}
	
	public String getContentForClipboard(){
		String content;
		try {
			content = currentDialogueNode.getContent();
		} catch(Exception ex) {
			content = "<p style='text-align:center;'>"
						+ "[style.italicsBad(Error: getContent() method is throwing an exception in the node: '"+currentDialogueNode.getLabel()+"')]"
					+ "</p>";
			
			// Hopefully bug reports will include this ;_;
			content += "<p style='font-size:0.75em;'>";
				for(StackTraceElement ste : ex.getStackTrace()) {
					content += "<br/>"+ste.toString();
				}
			content += "</p>";
			
			ex.printStackTrace();
		}
		
		return "<body style='background:#1e1e20; color:#DDD; font-family:Calibri;'>"
				+ "<style>"
				+ ".speech:before { content: '\"'; }"
				+ ".speech:after { content: '\"'; }"
				+ "</style>"
					+ "<h4 style='text-align:center; font-size:1.4em;'>" + getDialogueTitle() + "</h4>"
					+ "<div style='max-width:800px; margin:0 auto;'>"
						+ (currentDialogueNode.getHeaderContent() != null ? "<div id='header-content'>" + currentDialogueNode.getHeaderContent() + "</div>" : "")
						+ (content != null
								? "<div id='text-content'"
									+ " style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;"
										+ (requiresYScroll(currentDialogueNode)?" overflow-y:scroll; overflow-x:hidden;":"")+ "'>"
									+ textStartStringBuilder.toString() + pastDialogueSB.toString() + textEndStringBuilder.toString() + "</div>"
								: "")
					+ "</div>"
				+ "</div>"
				+"<p style='text-align:center;font-size:0.6em;color:#777;'>Dialogue written by "+currentDialogueNode.getAuthor()+" for <i>"+Main.GAME_NAME+" v"+Main.VERSION_NUMBER+"</i></p>"
				+ "</body>";
	}

	private String getResponseBoxDiv(Response response, int option) {
		String style="";
		String iconLeftBottom="";
		String iconLeftTop="";
		String iconRightBottom="";
		String iconRightTop="";
		boolean responseDisabled = false;
		
		if(response.disabledOnNullDialogue() && response.getNextDialogue()==null) {
			responseDisabled = true;
			
		} else if (response.isActionCorrupting()) {
			if(response.isAvailableFromFetishes()) {
				iconLeftBottom = "<div class='response-icon-leftBottom' style='filter:grayscale(100%);opacity:0.5;'>" + SVGImages.SVG_IMAGE_PROVIDER.getResponseCorruptionBypass() + "</div>";
			} else {
				iconLeftBottom = "<div class='response-icon-leftBottom'>" + SVGImages.SVG_IMAGE_PROVIDER.getResponseCorruptionBypass() + "</div>";
			}
		}
		else if(response.hasRequirements()) {
			if(response.isAvailable()) {
				responseDisabled = false;
			} else {
				responseDisabled = true;
			}
		}
		
		if(response.getSexPace()!=null) {
			switch(response.getSexPace()) {
				case DOM_GENTLE:
					iconLeftTop = "<div class='response-icon-leftTop'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseDomGentle()+"</div>";
					break;
				case DOM_NORMAL:
					iconLeftTop = "<div class='response-icon-leftTop'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseDomNormal()+"</div>";
					break;
				case DOM_ROUGH:
					iconLeftTop = "<div class='response-icon-leftTop'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseDomRough()+"</div>";
					break;
				case SUB_EAGER:
					iconLeftTop = "<div class='response-icon-leftTop'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseSubEager()+"</div>";
					break;
				case SUB_NORMAL:
					iconLeftTop = "<div class='response-icon-leftTop'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseSubNormal()+"</div>";
					break;
				case SUB_RESISTING:
					iconLeftTop = "<div class='response-icon-leftTop'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseSubResist()+"</div>";
					break;
			}
		}
		
		if(response instanceof ResponseSex && ((ResponseSex)response).isNonConWarning()) {
			iconRightTop = "<div class='response-icon-rightTop'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseSubResist()+"</div>";
		}
		
		if(response.isSexActionSwitch()) {
			iconRightBottom = "<div class='response-icon-rightBottom'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseSexSwitch()+"</div>";
			
		} else if(response.getSexActionType()==SexActionType.START_ADDITIONAL_ONGOING) {
			iconRightBottom = "<div class='response-icon-rightBottom'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseSexAdditional()+"</div>";
		}
		
		float fontSize = 1;
		String strippedTitle = UtilText.parse(response.getTitle()).replaceAll("<.*?>", "").replaceAll(UtilText.getCurrencySymbol(), "1");
		if(strippedTitle.length()>14) {
			int overflow = (strippedTitle.length()-14);
			if(overflow<=10) {
				fontSize-=overflow*0.03f;
			} else {
				fontSize-=overflow*0.025f;
			}
//			style = "style='font-size:"+fontSize+"em; line-height:10px; white-space:normal;'";
		}
		style = "style='font-size:"+fontSize+"em;'";
		
		if(response.getHighlightColour()!=PresetColour.TEXT) {
			style = "style='color:"+response.getHighlightColour().toWebHexString()+"; font-size:"+fontSize+"em;'";
		}
		
		String titleText = UtilText.parse(response.getTitle());
		
		if(responsePage==0) {
			return "<div class='response-box"+(responsePointer==option?" selected":"")+"' id='option_" + option + "'>"
						+ "<b class='hotkey-icon'>" + getResponseHotkey(option) + "</b>"
						+ "<p class='response-text"+(responsePointer==option?" selected":"")+"' "+style+">" + (!responseDisabled ? titleText : UtilText.getDisabledResponse(titleText)) + "</p>"
						+ iconLeftTop
						+ iconLeftBottom
						+ iconRightTop
						+ iconRightBottom
					+ "</div>";
			
		} else {
			if(option == 0) {
				return "<div class='response-box"+(responsePointer-((responsePage+1)*MainController.RESPONSE_COUNT)+1==(option)?" selected":"")+"' id='option_" + option + "'>"
						+ "<b class='hotkey-icon'>" + getResponseHotkey(option) + "</b>"
						+ "<p class='response-text"+(responsePointer-((responsePage+1)*MainController.RESPONSE_COUNT)+1==option?" selected":"")+"' "+style+">"
						+ (!responseDisabled ? titleText : UtilText.getDisabledResponse(titleText)) + "</p>"
						+ iconLeftTop
						+ iconLeftBottom
						+ iconRightTop
						+ iconRightBottom
					+ "</div>";
			} else {
				return "<div class='response-box"+(responsePointer-(responsePage*MainController.RESPONSE_COUNT)+1==(option)?" selected":"")+"' id='option_" + option + "'>"
						+ "<b class='hotkey-icon'>" + getResponseHotkey(option) + "</b>"
						+ "<p class='response-text"+(responsePointer-(responsePage*MainController.RESPONSE_COUNT)+1==option?" selected":"")+"' "+style+">"
						+ (!responseDisabled ? titleText : UtilText.getDisabledResponse(titleText)) + "</p>"
						+ iconLeftTop
						+ iconLeftBottom
						+ iconRightTop
						+ iconRightBottom
					+ "</div>";
			}
		}
	}

	private String getResponseHotkey(int i) {
		if (!(0 <= i && i <= 14)) { return ""; }
		
		KeyboardAction[] keyboardActions = 
			{
					KeyboardAction.RESPOND_0, KeyboardAction.RESPOND_1, KeyboardAction.RESPOND_2, KeyboardAction.RESPOND_3, KeyboardAction.RESPOND_4,
					KeyboardAction.RESPOND_5, KeyboardAction.RESPOND_6, KeyboardAction.RESPOND_7, KeyboardAction.RESPOND_8, KeyboardAction.RESPOND_9,
					KeyboardAction.RESPOND_10, KeyboardAction.RESPOND_11, KeyboardAction.RESPOND_12, KeyboardAction.RESPOND_13, KeyboardAction.RESPOND_14
			};
		KeyboardAction currentAction = keyboardActions[i];
		KeyCodeWithModifiers hotkeyForCurrentAction = Main.getProperties().hotkeyMapPrimary.get(currentAction);
		if (hotkeyForCurrentAction == null) {
			return "";
		} else {
			return hotkeyForCurrentAction.asHotkey();
		}
	}
	
	public void responseNavigationUp(){
		int minIndex = responsePage*MainController.RESPONSE_COUNT;
		
		if(responsePointer==0) {
			responsePointer=MainController.RESPONSE_COUNT-5;
		} else if(responsePointer>minIndex+5) {
			responsePointer-=5;
		}
		Main.game.updateResponses();
//		setResponses(currentDialogueNode, false);
	}

	public void responseNavigationDown(){
		int maxIndex = responsePage*MainController.RESPONSE_COUNT + MainController.RESPONSE_COUNT-1;
		
		if(responsePointer==MainController.RESPONSE_COUNT-5) {
			responsePointer=0;
			
		} else if(responsePointer<=maxIndex-5 && responsePointer!=0) {
			responsePointer+=5;
		}
		Main.game.updateResponses();
//		setResponses(currentDialogueNode, false);
	}
	
	public void responseNavigationLeft(){
		if(responsePage==0) {
			int minIndex = responsePointer - ((responsePointer-1)%5);
			
			if(responsePointer==0) {
				responsePointer=MainController.RESPONSE_COUNT-1;
				
			} else if(responsePointer > minIndex) {
				responsePointer--;
			}
			
		} else {
			int minIndex = (responsePointer/5)*5;
			
			if(responsePointer > minIndex) {
				responsePointer--;
			}
		}
		Main.game.updateResponses();
//		setResponses(currentDialogueNode, false);
	}
	
	public void responseNavigationRight(){
		if(responsePage==0) {
			int maxIndex = responsePointer + (4 - (responsePointer-1)%5);
			
			if(responsePointer==MainController.RESPONSE_COUNT-1) {
				responsePointer=0;
				
			} else if(responsePointer < maxIndex && responsePointer!=0) {
				responsePointer++;
			}
			
		} else {
			int maxIndex = ((responsePointer/5)+1)*5;
			
			if(responsePointer < maxIndex-1) {
				responsePointer++;
			}
		}
		Main.game.updateResponses();
//		setResponses(currentDialogueNode, false);
	}

	public void saveDialogueNode() {
		savedDialogue = currentDialogue;
		savedDialogueNode = currentDialogueNode;
		previousPastDialogueSBContents = pastDialogueSB.toString();
		savedResponseTab = responseTab;
	}
	
	
	/**
	 * Flashes a message at the bottom of the screen.
	 * @param colour Colour of the text message.
	 * @param text Content of the message.
	 */
	public void flashMessage(Colour colour, String text){
		try {
			Main.mainController.getWebEngine().executeScript(
					"\"use strict\";"
					+ "document.getElementById('bottom-text').innerHTML=\"<span style='color:"+colour.toWebHexString()+";'>"+text+"</span>\";"
					+ "try{"
						+ "timer;"
					+ "} catch(e){"
						+ "var timer=false;"
					+ "}"
					+ "if(!timer) {"
						+ "document.getElementById('bottom-text').classList.add('demo');"
						+ "timer = true;"
						+ "timer = setTimeout(function(){"
							+ "document.getElementById('bottom-text').classList.remove('demo');"
							+ "timer = false;"
						+ "}, 2000);"
					+ "}");
		} catch(Exception ex) {
			System.err.println("var timer not found...");
		}
	}

	public void restoreSavedContent(boolean regenerateSceneDialogue) {
		positionAnchor = initialPositionAnchor;
		dialogueTitle = UtilText.parse(savedDialogueNode.getLabel());
		responseTab = savedResponseTab;
		
		currentDialogueNode = savedDialogueNode;
		
		if(Main.game.isInSex()) {
			Main.sex.recalculateSexActions();
		}
		
		if(currentDialogueNode.reloadOnRestore() || regenerateSceneDialogue) {
			String headerContent = currentDialogueNode.getHeaderContent();
			String content;
			try {
				content = currentDialogueNode.getContent();
			} catch(Exception ex) {
				content = "<p style='text-align:center;'>"
							+ "[style.italicsBad(Error: getContent() method is throwing an exception in the node: '"+currentDialogueNode.getLabel()+"')]"
						+ "</p>";
				
				// Hopefully bug reports will include this ;_;
				content += "<p style='font-size:0.75em;'>";
					for(StackTraceElement ste : ex.getStackTrace()) {
						content += "<br/>"+ste.toString();
					}
				content += "</p>";
				
				ex.printStackTrace();
			}
			
			currentDialogue = 
					"<div id='main-content'>"
						+ getTitleDiv(getDialogueTitle())
						+ "<div class='div-center' id='content-block'>"
								+ getMapDiv()
								+ (headerContent != null
									? "<div id='header-content' style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;-webkit-user-select: none;'>"
										+ (currentDialogueNode.disableHeaderParsing() ? headerContent : UtilText.parse(headerContent))
										+ "</div>"
									: "")
								+ (content != null
										? "<div "+(Main.getProperties().hasValue(PropertyValue.fadeInText)?"id='text-content'":"")+" style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;'>"
												+ content
											+ "</div>"
										: "")
						+ "</div>"
						+"<div id='bottom-text'>Game saved!</div>"
						+ getResponsesDiv(currentDialogueNode)
					+ "</div>"
				+ "</body>";
			
			
		} else {
			currentDialogue = savedDialogue;
		}
		
		pastDialogueSB.setLength(0);
		pastDialogueSB.append(previousPastDialogueSBContents);

		if (Main.getProperties().fontSize == FONT_SIZE_NORMAL) {
			currentDialogue = currentDialogue.replaceFirst("<div class='div-center' style='font-size:" + FONT_SIZE_LARGE + "px'; line-height:" + (FONT_SIZE_LARGE + 6) + "px;'>",
					"<div class='div-center' style='font-size:" + FONT_SIZE_NORMAL + "px'; line-height:" + (FONT_SIZE_NORMAL + 6) + "px;'>");
			currentDialogue = currentDialogue.replaceFirst("<div class='div-center' style='font-size:" + FONT_SIZE_HUGE + "px'; line-height:" + (FONT_SIZE_HUGE + 6) + "px;'>",
					"<div class='div-center' style='font-size:" + FONT_SIZE_NORMAL + "px'; line-height:" + (FONT_SIZE_NORMAL + 6) + "px;'>");
		} else if (Main.getProperties().fontSize == FONT_SIZE_LARGE) {
			currentDialogue = currentDialogue.replaceFirst("<div class='div-center' style='font-size:" + FONT_SIZE_NORMAL + "px; line-height:" + (FONT_SIZE_NORMAL + 6) + "px;'>",
					"<div class='div-center' style='font-size:" + FONT_SIZE_LARGE + "px; line-height:" + (FONT_SIZE_LARGE + 6) + "px;'>");
			currentDialogue = currentDialogue.replaceFirst("<div class='div-center' style='font-size:" + FONT_SIZE_HUGE + "px; line-height:" + (FONT_SIZE_HUGE + 6) + "px;'>",
					"<div class='div-center' style='font-size:" + FONT_SIZE_LARGE + "px; line-height:" + (FONT_SIZE_LARGE + 6) + "px;'>");
		} else if (Main.getProperties().fontSize == FONT_SIZE_HUGE) {
			currentDialogue = currentDialogue.replaceFirst("<div class='div-center' style='font-size:" + FONT_SIZE_NORMAL + "px; line-height:" + (FONT_SIZE_NORMAL + 6) + "px;'>",
					"<div class='div-center' style='font-size:" + FONT_SIZE_HUGE + "px; line-height:" + (FONT_SIZE_HUGE + 6) + "px;'>");
			currentDialogue = currentDialogue.replaceFirst("<div class='div-center' style='font-size:" + FONT_SIZE_LARGE + "px; line-height:" + (FONT_SIZE_LARGE + 6) + "px;'>",
					"<div class='div-center' style='font-size:" + FONT_SIZE_HUGE + "px; line-height:" + (FONT_SIZE_HUGE + 6) + "px;'>");
		}
		
		setMainContentRegex(
				(savedDialogueNode.getDialogueNodeType()!=DialogueNodeType.PHONE && savedDialogueNode.getDialogueNodeType()!=DialogueNodeType.CHARACTERS_PRESENT
					?"<body onLoad='scrollToElement()'>"
						+ "<script>function scrollToElement() {document.getElementById('content-block').scrollTop = document.getElementById('position" + (positionAnchor) + "').offsetTop -64;}</script>"
					:"<body>"),
			currentDialogue);

		textEndStringBuilder.setLength(0);
		textStartStringBuilder.setLength(0);
		
		Main.game.endTurn(0);
		//Main.mainController.processNewDialogue();

	}
	
	private void setMainContentRegex(String prefix, String currentDialogue) {
		String sanitizedDialogue = currentDialogue.replaceAll("\\.\\.\\.", "&hellip;");
		if(currentDialogueNode!=DebugDialogue.PARSER) {
			sanitizedDialogue = sanitizedDialogue.replaceAll("\\.([\\D])", ".\u200b$1").replaceAll("\\[", "\u200b[\u200b");
		}
		Main.mainController.setMainContent(prefix + sanitizedDialogue);
	}
	
	public List<NPC> getCharactersPresent() {
		if(player==null) {
			return new ArrayList<>();
			
		} else {
			return getCharactersPresent(player.getWorldLocation(), player.getLocation());
		}
	}

	public List<NPC> getNonCompanionCharactersPresent(boolean includeUniqueNPCs) {
		List<NPC> nonCompanionCharactersPresent = new ArrayList<>();
		nonCompanionCharactersPresent.addAll(getCharactersPresent());
		nonCompanionCharactersPresent.removeIf((npc) -> (Main.game.getPlayer().hasCompanion(npc) || (npc.getPartyLeader()!=null && Main.game.getPlayer().hasCompanion(npc.getPartyLeader()))) && (includeUniqueNPCs || !npc.isUnique()));
		return nonCompanionCharactersPresent;
	}
	
	public List<NPC> getNonCompanionCharactersPresent() {
		return getNonCompanionCharactersPresent(true);
	}

	public List<NPC> getNonCompanionCharactersPresent(Cell cell, boolean includeUniqueNPCs) {
		List<NPC> nonCompanionCharactersPresent = new ArrayList<>();
		nonCompanionCharactersPresent.addAll(getCharactersPresent(cell));
		nonCompanionCharactersPresent.removeIf((npc) -> (Main.game.getPlayer().hasCompanion(npc) || (npc.getPartyLeader()!=null && Main.game.getPlayer().hasCompanion(npc.getPartyLeader()))) && (includeUniqueNPCs || !npc.isUnique()));
		return nonCompanionCharactersPresent;
	}

	public List<NPC> getNonCompanionCharactersPresent(Cell cell) {
		return getNonCompanionCharactersPresent(cell, true);
	}
	
	/**
	 * Uses the player's current location.
	 */
	public List<NPC> getCharactersTreatingCellAsHome() {
		return getCharactersTreatingCellAsHome(Main.game.getPlayerCell());
	}
	
	public List<NPC> getCharactersTreatingCellAsHome(Cell cell) {
		List<NPC> charactersHome = new ArrayList<>();

		if(cell.getCharactersHomeIds()!=null) {
			Set<String> ids = new HashSet<>(cell.getCharactersHomeIds());
			for(String id : ids) {
				try {
					GameCharacter character = getNPCById(id);
					if(character instanceof NPC) {
						charactersHome.add((NPC) character);
					}
				} catch (Exception e) {
					if(Main.game.isStarted()) { // Only check once game has started, otherwise initialisation methods (such as equipClothing) may end up breaking this:
						System.err.println("Failed to load character present home: "+id);
						cell.removeCharacterHomeId(id);
					}
//					e.printStackTrace();
				}
			}
		}

		charactersHome.sort((c1, c2) ->
				(c2.getLevel()-c1.getLevel())==0
					?c2.getName(true).compareTo(c1.getName(true))
					:(c2.getLevel()-c1.getLevel()));
		
		return charactersHome;
	}
	
	public List<NPC> getCharactersPresent(AbstractWorldType worldType, AbstractPlaceType placeType) {
		Cell cell = worlds.get(worldType).getCell(placeType);
		
		return getCharactersPresent(cell);
	}
	
	public List<NPC> getCharactersPresent(Cell cell) {
		return getCharactersPresent(cell.getType(), cell.getLocation());
	}
	
	public List<NPC> getCharactersPresent(AbstractWorldType worldType, Vector2i location) {
		List<NPC> charactersPresent = new ArrayList<>();
		
		if(getWorlds().get(worldType).getCell(location).getCharactersPresentIds()!=null) {
			Set<String> ids = new HashSet<>(getWorlds().get(worldType).getCell(location).getCharactersPresentIds());
			for(String id : ids) {
				try {
					GameCharacter character = getNPCById(id);
					if(character instanceof NPC) {
						charactersPresent.add((NPC) character);
					}
				} catch (Exception e) {
					if(Main.game.isStarted()) { // Only check once game has started, otherwise initialisation methods (such as equipClothing) may end up breaking this:
						System.err.println("Failed to load character present: "+id);
						getWorlds().get(worldType).getCell(location).removeCharacterPresentId(id);
					}
//					e.printStackTrace();
				}
			}
		}
		
		try {
			charactersPresent.sort((c1, c2) ->
					(c2.getLevel()-c1.getLevel())==0
						?c2.getNameIgnoresPlayerKnowledge().compareTo(c1.getNameIgnoresPlayerKnowledge())
						:(c2.getLevel()-c1.getLevel()));
		} catch(Exception ex) {
		}
		
		return charactersPresent;
	}
	
	public int getModifierTravelTime(boolean onLand, int time) {
		int maxTime = 0;
		
		for(GameCharacter character : Main.game.getPlayer().getParty()) {
			int speed = onLand
						?character.getLandSpeedModifier()
						:character.getWaterSpeedModifier();
			
			int travelTime = time;
			travelTime = (int) (travelTime*((100+speed)/100f));
			if(time>0) {
				travelTime = Math.max(1, travelTime);
			}
			if(Math.abs(travelTime)>maxTime) {
				maxTime = travelTime;
			}
		}
//		System.out.println(maxTime);
		return maxTime;
	}
	
	public String getWeatherImage() {
		if (isDayTime()) {
			switch (currentWeather) {
				case CLEAR:
					return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayClear();
				case CLOUD:
					return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayCloud();
				case RAIN:
					return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayRain();
				case SNOW:
					return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDaySnow();
				case MAGIC_STORM_GATHERING:
					return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayStormIncoming();
				case MAGIC_STORM:
					return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayStorm();
			}
			
		} else {
			switch (currentWeather) {
				case CLEAR:
					return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightClear();
				case CLOUD:
					return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightCloud();
				case RAIN:
					return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightRain();
				case SNOW:
					return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightSnow();
				case MAGIC_STORM_GATHERING:
					return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightStormIncoming();
				case MAGIC_STORM:
					return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightStorm();
			}
		}
		return "";
	}

	public int getWeatherTimeRemainingInSeconds() {
		return weatherTimeRemainingInSeconds;
	}

	public String getWeatherTimeRemainingAsTimeString() {
		long minutes = weatherTimeRemainingInSeconds/60;
		long hours = minutes/60;
		return (hours/24)+" days, "+hours%24+" hours, "+minutes%60+" minutes";
	}
	
	public void setWeatherInSeconds(Weather weather, int secondsRemaining) {
		currentWeather = weather;
		weatherTimeRemainingInSeconds = secondsRemaining;
	}
	
	public long getId() {
		return id;
	}
	
	public World getActiveWorld() {
		worlds.size();
		player.isFeminine();
		return worlds.get(player.getWorldLocation());
	}

	public Map<AbstractWorldType, World> getWorlds() {
		return worlds;
	}

	public void setPlayer(PlayerCharacter player) {
		this.player = player;
	}

	public PlayerCharacter getPlayer() {
		return player;
	}

	public ItemGeneration getItemGen() {
		return itemGeneration;
	}

	public CharacterUtils getCharacterUtils() {
		return characterUtils;
	}
	
	public long getSecondsPassed() {
		return secondsPassed;
	}

	public long getMinutesPassed() {
		return secondsPassed/60;
	}
	
	public int getMinutesUntilNextMorningOrEvening() {
		int minutesPassed = Main.game.getDayMinutes();
		
		LocalDateTime[] sunriseSunset = DateAndTime.getTimeOfSolarElevationChange(Main.game.getDateNow(), SolarElevationAngle.SUN_ALTITUDE_SUNRISE_SUNSET, Game.DOMINION_LATITUDE, Game.DOMINION_LONGITUDE);
		
		return (Main.game.isDayTime()
				? (sunriseSunset[1].get(ChronoField.MINUTE_OF_DAY) - minutesPassed)
				: (minutesPassed<sunriseSunset[0].get(ChronoField.MINUTE_OF_DAY)
					?sunriseSunset[0].get(ChronoField.MINUTE_OF_DAY)
					:(24*60)+sunriseSunset[0].get(ChronoField.MINUTE_OF_DAY)) - minutesPassed)
				+1;
	}
	
	public int getSunriseTimeInMinutes() {
		LocalDateTime[] sunriseSunset = DateAndTime.getTimeOfSolarElevationChange(Main.game.getDateNow(), SolarElevationAngle.SUN_ALTITUDE_SUNRISE_SUNSET, Game.DOMINION_LATITUDE, Game.DOMINION_LONGITUDE);
		return sunriseSunset[0].get(ChronoField.MINUTE_OF_DAY);
	}

	public int getSunsetTimeInMinutes() {
		LocalDateTime[] sunriseSunset = DateAndTime.getTimeOfSolarElevationChange(Main.game.getDateNow(), SolarElevationAngle.SUN_ALTITUDE_SUNRISE_SUNSET, Game.DOMINION_LATITUDE, Game.DOMINION_LONGITUDE);
		return sunriseSunset[1].get(ChronoField.MINUTE_OF_DAY);
	}
	
	public LocalDateTime getStartingDate() {
		return startingDate;
	}
	
	public void applyStartingDateChange() {
		startingDate = startingDate.plusYears(TIME_SKIP_YEARS);
		for(NPC npc : Main.game.getAllNPCs()) {
			npc.setBirthday(npc.getBirthday().plusYears(TIME_SKIP_YEARS)); // Have to do this to keep NPC starting ages as planned
		}
	}
	
	/**
	 * Should only be used for debugging purposes.
	 */
	public void incrementStartingDateDays(int days) {
		startingDate = startingDate.plusDays(days);
	}

	/**
	 * Should only be used for game start or debugging purposes.
	 */
	public void setStartingDateMonth(Month month) {
		if(startingDate.getMonthValue() > month.getValue()) {
			startingDate = startingDate.minusMonths(startingDate.getMonthValue() - month.getValue());
		} else {
			startingDate = startingDate.plusMonths(month.getValue() - startingDate.getMonthValue());
		}
	}
	
	public LocalDateTime getDateNow() {
		return getStartingDate().plusSeconds(Main.game.getSecondsPassed());
	}

	public LocalDateTime getCustomDateTime(int year, int month, int dayOfMonth, int hour, int minute, int second) {
		return LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
	}

	public LocalDate getCustomDate(int year, int month, int dayOfMonth) {
		return LocalDate.of(year, month, dayOfMonth);
	}

	public LocalTime getCustomTime(int hour, int minute, int second) {
		return LocalTime.of(hour, minute, second);
	}

	public String getDisplayDate(TemporalAccessor dateNow, boolean withYear) {
		if(isBadEnd()) {
			return UtilText.parse("[style.colourBad(Unknown date)]");
		}
		if(isInNewWorld() && !getDialogueFlags().hasFlag(DialogueFlagValue.knowsDate)) {
			return UtilText.parse("[style.colourMinorBad(Unknown date)]");
		}
		
		String date = Units.date(dateNow, Units.DateType.LONG);
		if(withYear) {
			return date;
		} else {
			return date.substring(0, date.length()-5);
		}
	}
	
	public String getDisplayDate(boolean withYear) {
		return getDisplayDate(getDateNow(), withYear);
	}

	public String getDisplayDate(TemporalAccessor dateNow) {
		return getDisplayDate(dateNow, false);
	}

	public String getDisplayDate() {
		return getDisplayDate(getDateNow(), false);
	}

	public String getDisplayTime(TemporalAccessor timeNow) {
		return Units.time(timeNow);
	}

	public String getDisplayTime() {
		return getDisplayTime(getDateNow());
	}

	public int getYear() {
		return Main.game.getDateNow().getYear();
	}

	public long getHour() {
		return Main.game.getMinutesPassed() / 60l;
	}
	
	public int getHourOfDay() {
		return (int) (getHour()%24);
	}
	
	/**
	 * @return The number of minutes that have passed in the current day.
	 */
	public int getDayMinutes() {
		return (int) (getMinutesPassed()%(24*60));
	}

	/**
	 * @return The number of seconds that have passed in the current day.
	 */
	public int getDaySeconds() {
		return (int) (getSecondsPassed()%(24*60*60));
	}
	
	/**
	 * @param desiredTime The targeted time, in minutes of the day. (i.e. a number from 0 to 1440)
	 * @return The number of minutes it will take to reach this time, flowing over into the next day if necessary. Returned number will be at max 1439.
	 */
	public int getMinutesUntilTimeInMinutes(int desiredTime) {
		int timeDifference = desiredTime - getDayMinutes();
		if(timeDifference<0) {
			timeDifference = (24*60)-getDayMinutes() + desiredTime;
		}
		return timeDifference;
	}
	
	/**
	 * @return true If the hour is between 09:00 and 17:00.
	 */
	public boolean isWorkTime() {
		return this.getHourOfDay()>=9 && this.getHourOfDay()<17;
	}

	/**
	 * @return true If the hour is between 06:00 and 22:00.
	 */
	public boolean isExtendedWorkTime() {
		return this.getHourOfDay()>=6 && this.getHourOfDay()<22;
	}

	/**
	 * @return true If the hour is between 01:00 and 04:00.
	 */
	public boolean isSmallHours() {
		return this.getHourOfDay()>=1 && this.getHourOfDay()<4;
	}

	/**
	 * This method works with hours that pass over midnight into the next day.
	 * <br/><i>e.g. isHourBetween(22, 8) will return true when the time is 23:00, 00:00, 01:00, etc.</i>
	 * 
	 * @param startHour The starting hour, with decimal places correctly converted to fraction of hour.
	 * @param endHour The end hour, with decimal places correctly converted to fraction of hour.
	 * @return true If the hour is between startHour and endHour, inclusive of start and exclusive of end.
	 */
	public boolean isHourBetween(float startHour, float endHour) {
		int dayMinutes = this.getDayMinutes();
		if(endHour<startHour) {
			endHour+=24;
			if(dayMinutes<startHour*60) {
				dayMinutes+=(24*60);
			}
		}
		return dayMinutes>=(startHour*60) && dayMinutes<(endHour*60);
	}
	
	/**
	 * @return true If the time is currently somewhere between sunrise and sunset.
	 */
	public boolean isDayTime() {
		return getCurrentDayPeriod() == DayPeriod.DAY;
	}
	
	public boolean isWeekend() {
		return getDateNow().getDayOfWeek()==DayOfWeek.SATURDAY || getDateNow().getDayOfWeek()==DayOfWeek.SUNDAY;
	}
	
	public DayPeriod getCurrentDayPeriod() {
		return DateAndTime.getDayPeriod(this.getDateNow(), Game.DOMINION_LATITUDE, Game.DOMINION_LONGITUDE);
	}

	public DayPeriod getDayPeriodAtHour(int hourOfDay) {
		LocalDateTime ldt = this.getDateNow().withHour(hourOfDay);
		return DateAndTime.getDayPeriod(ldt, Game.DOMINION_LATITUDE, Game.DOMINION_LONGITUDE);
	}
	
	public boolean isMorning() {
		return getMinutesPassed() % (24 * 60) >= 0 && getMinutesPassed() % (24 * 60) < (60 * 12);
	}
	
	public boolean isAfternoon() {
		return !isMorning();
	}

	public int getDayNumber() {
		return getDayNumber(getSecondsPassed());
	}
	
	private int getDayNumber(long seconds) {
		return (int) (1 + (seconds / (24 * 60 * 60)));
	}

	public DayOfWeek getDayOfWeek() {
		return getDateNow().getDayOfWeek();
	}

	public Month getMonth() {
		return getDateNow().getMonth();
	}

	public boolean isInCombat() {
		return inCombat;
	}

	public void setInCombat(boolean inCombat) {
		this.inCombat = inCombat;
	}

	public boolean isInSex() {
		return inSex;
	}

	public void setInSex(boolean inSex) {
		this.inSex = inSex;
	}

	public Encounter getCurrentEncounter() {
		return currentEncounter;
	}

	public void setCurrentEncounter(Encounter currentEncounter) {
		this.currentEncounter = currentEncounter;
	}
	
	public NPC getNpc(Class<? extends NPC> npcClass) {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(npcClass));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("getNpc("+npcClass.getName()+") returning null!");
			return null;
		}
	}

	/**
	 * @return a list of all offspring that have been encountered in the game.
	 */
	public List<NPC> getOffspring() {
		List<NPC> offspring = new ArrayList<>();
		
		for(NPC npc : NPCMap.values()) {
			if((npc.getMother()!=null && npc.getMother().isPlayer()) ||
			   (npc.getFather()!=null && npc.getFather().isPlayer()) ||
			   (npc.getIncubator()!=null && npc.getIncubator().isPlayer())){
					offspring.add(npc);
			}
		}
		
		return offspring;
	}
	
	/**
	 * @return a list of all offspring that have been born but have not been encountered yet.
	 * @param matcher a function to filter out offspring (usually based on the location where offspring can spawn).
	 */
	public List<OffspringSeed> getOffspringNotSpawned(Predicate<OffspringSeed> matcher) {
		return getOffspringNotSpawned(matcher, false);
	}

	/**
	 * @return a list of all offspring that have not been encountered yet.
	 * @param matcher a function to filter out offspring (usually based on the location where offspring can spawn).
	 * @param includeUnborn include all offspring that have not been born yet
	 */
	public List<OffspringSeed> getOffspringNotSpawned(Predicate<OffspringSeed> matcher, boolean includeUnborn) {
		List<OffspringSeed> offspringAvailable = new ArrayList<>();
		
		for(OffspringSeed os : OffspringSeedMap.values()) {
			if(os.isFromPlayer() && (includeUnborn || os.isBorn())) {
				offspringAvailable.add(os);
			}
		}

		return offspringAvailable.stream().filter(matcher).collect(Collectors.toList());
	}
	
	public List<NPC> getReindeerOverseers() {
		List<NPC> reindeerOverseers = new ArrayList<>(getAllNPCs());
		
		reindeerOverseers.removeIf(npc -> !npc.getClass().equals(ReindeerOverseer.class));
		
		return reindeerOverseers;
	}
	
	public List<NPC> getAllNPCs() {
		return new ArrayList<NPC>(NPCMap.values());
	}
	
	public boolean isCharacterExisting(String id) {
		return NPCMap.containsKey(id);
	}
	
	public GameCharacter getNPCById(String id) throws Exception {
		if(id==null || id.isEmpty()) {
			throw new NullPointerException();
//			return null;
		}
		
		if(id.equals(Main.game.getPlayer().getId())) {
			return Main.game.getPlayer();
		}
		if(!NPCMap.containsKey(id)) {
			throw new NullPointerException();
			
//			if(!nullCharacterIds.contains(id)) {
//				System.err.println("!WARNING! getNPC("+id+") is returning null! GenericAndrogynousNPC will be returned for all instances of this!");
//				nullCharacterIds.add(id);
//			}
//			
//			if(Main.DEBUG) {
//				new NullPointerException().printStackTrace();
//			}
//			
//			return Main.game.getNpc(GenericAndrogynousNPC.class);
		}
		return NPCMap.get(id);
	}
	
	public Map<String, NPC> getNPCMap() {
		return NPCMap;
	}
	
	public String getUniqueNPCId(Class<? extends NPC> c) {
		if(c.equals(DarkSiren.class)) {
			return "-1,FortressDemonLeader";
		}
		return "-1,"+c.getSimpleName();
	}

	public String getNPCId(Class<? extends NPC> c) {
		return npcTally.get()+","+c.getSimpleName();
	}
	
	public String getNextNPCId(Class<? extends NPC> c) {
		return (npcTally.incrementAndGet())+","+c.getSimpleName();
	}

	/**
	 * Generates and instantly adds a new NPC of the type 'npcGenerationId'.
	 * The game does not wait until the NPC Update Loop has finished, and will immediately add this NPC.
	 *
	 * @param npcGenerationId The ID of the NPC class to spawn. Use one of the names below, or use a qualified name (like dominion.DominionAlleywayAttacker) for any other NPC class.
	 * @param parserTarget The parser id to assign to this NPC, which can then be used in the parsing engine. Pass in an empty String or a null to not assign a parserTarget to this NPC.
	 * @return The ID of the NPC which is spawned as a result of calling this method.
	 */
	public String addNPC(String npcGenerationId, String parserTarget) throws Exception {
		//TODO add npcGenerationId map
		boolean forceImmediateAddition = true; // TODO this may need testing, but I'm 99% sure that immediate addition will be fine, and it should prevent potential issues with NPC removal when resting/loitering for multiple hours at a time
		
		NPC npc = null;
		if(npcGenerationId.equalsIgnoreCase("GenericSexualPartner")) {
			npc = new GenericSexualPartner();
		} else if(npcGenerationId.equalsIgnoreCase("EvelyxSexualPartner")) {
			npc = new EvelyxSexualPartner();
		} else if(npcGenerationId.equalsIgnoreCase("LunetteMelee")) {
			npc = new LunetteMelee();
		} else if(npcGenerationId.equalsIgnoreCase("LunetteRanged")) {
			npc = new LunetteRanged();
		} else if(npcGenerationId.equalsIgnoreCase("FieldsBandit")) {
			npc = new FieldsBandit();
		} else if(npcGenerationId.equalsIgnoreCase("EvelyxMilker")) {
			npc = new EvelyxMilker();
		}
		if(npc==null) {
			try {
				npc = (NPC) Class.forName("com.lilithsthrone.game.character.npc."+npcGenerationId).getConstructor().newInstance();
			} catch (Exception ex) {
				System.err.println("Failed to add NPC: "+npcGenerationId);
				ex.printStackTrace();
				return "";
			}
		}
		String idGenerated = addNPC(npc, false, forceImmediateAddition);
		if(parserTarget!=null && !parserTarget.isEmpty()) {
			ParserTarget.addAdditionalParserTarget(parserTarget, npc);
		}
		return idGenerated;
	}
	
	// Alaco : Methods in lambdas can't throw exceptions, so we have to wrap addNPC
	public String safeAddNPC(final NPC npc, boolean isImported) {
		String id = "";
		try{
			id = addNPC(npc,isImported);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return id;
	}

	public String addNPC(NPC npc, boolean isImported) throws Exception {
		return addNPC(npc, isImported, false);
	}
	
	/**
	 * @param npc The NPC to add.
	 * @param isImported IF it's being added from an imported file.
	 * @param forceImmediateAddition If true, the addition of this npc will not wait until the NPC Update Loop has finished, and will immediately be added.
	 * @return The id which has been assigned to the npc.
	 * @throws Exception
	 */
	public String addNPC(NPC npc, boolean isImported, boolean forceImmediateAddition) throws Exception {
		if(isImported) {
			int tallyCount;
			String rawId = npc.getId();
			// Support old versions (in the format "Stan-Stan-Stan-49"):
			if(rawId.contains("-") && !rawId.contains(",")){
				String[] split = rawId.split("-");
				tallyCount = Integer.parseInt(split[split.length-1]);
			} else {
				tallyCount = Integer.parseInt(rawId.split(",")[0]);
			}

			npcTally.updateAndGet(x -> Math.max(x, tallyCount));
			
		} else {
			if(npc.isUnique()) {
				npc.setId(getUniqueNPCId(npc.getClass()));
			} else {
				int id = npcTally.incrementAndGet();
				npc.setId(id+","+(npc.getClass().isAnonymousClass() ? npc.getClass().getSuperclass().getSimpleName() : npc.getClass().getSimpleName()));
			}
		}
		
		if(NPCMap.keySet().contains(npc.getId())) {
			throw new Exception("NPC map already contained an NPC with this Id ("+npc.getId()+"). SOMETHING HAS GONE HORRIBLY WRONG! PANIC!");
		}
		
		if(isInNPCUpdateLoop && !forceImmediateAddition) {
			npcsToAdd.add(npc);
		} else {
			NPCMap.put(npc.getId(), npc);
		}
		
		// Set locations after the NPC has the correct id:
		npc.setLocation(npc.getWorldLocation(), npc.getLocation(), false);
		npc.setHomeLocation(npc.getHomeWorldLocation(), npc.getHomeLocation());
		
		return npc.getId();
	}
	
//	/**
//	 * If the NPC has relationship stats with the player, don't delete entirely. Instead, move to PlaceType.GENERIC_EMPTY_TILE.
//	 * If the NPC has no stats related to the player, then remove them from the game.
	/**
	 * If the NPC is not unique, they are deleted. Otherwise, they are moved to PlaceType.GENERIC_EMPTY_TILE.
	 * 
	 * @return true if NPC was deleted, false if they were moved to the empty world.
	 */
	public boolean banishNPC(NPC npc) {
		Main.game.getPlayer().removeCompanion(npc);
		for (AbstractClothing clothing : npc.getClothingCurrentlyEquipped()) {
			if (clothing.getRarity() == Rarity.QUEST) { // Return any unique clothing to the player
				Main.game.getPlayer().addClothing(clothing, false);
			}
		}
		
		if(npc.isSlave()) {
			npc.getOwner().removeSlave(npc);
		}
		if(npc.hasSlaves()) {
			for(GameCharacter c : new ArrayList<>(npc.getSlavesOwnedAsCharacters())) {
				npc.removeSlave(c);
			}
		}

		for(NPC loopNpc : Main.game.getAllNPCs()) {
			loopNpc.setAllAreasKnownByCharacter(npc, false);
			loopNpc.setAffection(npc, 0f);
		}
		
		//TODO Why are the unique checks necessary?
		// Use separate loops so that we only check if the banished npc isUnique once
		if(npc.isUnique()) {
			for(NPC loopNpc : Main.game.getAllNPCs()) {
				loopNpc.setAllAreasKnownByCharacter(npc, false);
			}
		} else {
			for(NPC loopNpc : Main.game.getAllNPCs()) {
				loopNpc.setAllAreasKnownByCharacter(npc, false);
				if(!loopNpc.isUnique()) {
					loopNpc.setAffection(npc, 0f);
				}
			}
		}
		
		// TODO This needs more thorough testing...
//				Main.game.getPlayer().getTotalTimesHadSex(npc) > 0
//				|| npc.getLastLitterBirthed()!=null
//				|| npc.getMother()!=null
//				|| npc.getFather()!=null
		if (npc.isReadyToBeDeleted()) {
			removeNPC(npc);
			return true;
		} else {
			ParserTarget.removeAdditionalParserTarget(npc);
			npc.setLocation(WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE, true);
			// Remove unnecessary data from banished NPCs
			npc.resetInventory(true);
			npc.resetAllPregnancyReactions();
			npc.resetPerksMap(false);
			npc.clearFetishes();
			npc.clearFetishDesires();
			npc.clearEquippedMoves();
			npc.clearAddictions();
			npc.clearAllLipstickMarkings();
			for(CoverableArea area : CoverableArea.values()) {
				npc.resetAreaKnownByCharacters(area);
			}
			npc.resetFluidsStored();
			return false;
		}
	}
	
	public boolean banishNPC(String id) {
		try {
			NPC npc = (NPC) getNPCById(id);
			if(npc.equals(Main.game.getNpc(GenericAndrogynousNPC.class))) {
				return false; // This is the npc returned if there's a problem in getNPCById().
			}
			return banishNPC(npc);
		} catch (Exception e) {
			Util.logGetNpcByIdError("banishNPC()", id);
			return false;
		}
	}

	private void removeNPC(NPC npc) {
		if(npc.isPregnant()) {
			// End with birth if father is player
			npc.endPregnancy(npc.getPregnantLitter().getFather()!=null && npc.getPregnantLitter().getFather().isPlayer());
			
		} else if(npc.hasStatusEffect(StatusEffect.PREGNANT_0)) {
			npc.removeStatusEffect(StatusEffect.PREGNANT_0);
		}
		
		 // remove NPC's elemental before this NPC is removed to prevent the elemental persisting in LT-purgatory (which was causing a crash due to the elemental having no summoner when loading saved games)
		if(npc.hasDiscoveredElemental() && npc.getElemental()!=null) {
			removeNPC(npc.getElemental());
		}
		
		if(!npc.getIncubatingLitters().isEmpty()) {
			for(SexAreaOrifice orifice : new ArrayList<>(npc.getIncubatingLitters().keySet())) {
				npc.endIncubationPregnancy(orifice, ((npc.getPregnantLitter().getFather()!=null && npc.getIncubationLitter(orifice).getFather().isPlayer()) || (npc.getPregnantLitter().getMother()!=null && npc.getIncubationLitter(orifice).getMother().isPlayer())));
			}
		}
		
		if(isInNPCUpdateLoop) {
			npcsToRemove.add(npc);
			
		} else {
			// Iterate through all characters, and if a character has had their virginity taken by this npc, then set the backup virginity loss text
			// This will prevent the player from seeing the backup text: 'X lost their virginity to someone they can't remember.'
			List<GameCharacter> allCharactersWithPlayer = new ArrayList<>();
			allCharactersWithPlayer.add(Main.game.getPlayer());
			allCharactersWithPlayer.addAll(Main.game.getAllNPCs());
			for(GameCharacter character : allCharactersWithPlayer) {
				for(Entry<SexType, Entry<String, String>> entry : character.getVirginityLossMap().entrySet()) {
					if(entry.getValue()!=null) {
						if(entry.getValue().getKey().equals(npc.getId())) {
							character.setBackupVirginityLoss(entry.getKey());
						}
					}
				}
			}
			
			for(Value<Integer, List<SlaveryEventLogEntry>> value : slaveryEventLog) {
				for(SlaveryEventLogEntry entry : value.getValue()) {
					if(entry.getSlaveID().equals(npc.getId())) {
						entry.applySlaveDeleted();
					}
				}
			}
			
			npc.getCell().removeCharacterPresentId(npc.getId());
			npc.getHomeCell().removeCharacterHomeId(npc.getId());
			ParserTarget.removeAdditionalParserTarget(npc);
			NPCMap.remove(npc.getId());
		}
	}
	
	public Map<String, OffspringSeed> getOffspringSeedMap() {
		return OffspringSeedMap;
	}

	public OffspringSeed getOffspringSeedById(String id) {
		if(id==null || id.isEmpty()) {
			throw new NullPointerException();
		}
		if(!OffspringSeedMap.containsKey(id)) {
			throw new NullPointerException();
		}
		return OffspringSeedMap.get(id);
	}
	
	public String addOffspringSeed(OffspringSeed os, boolean isImported) throws Exception {
		if(isImported) {
			int tallyCount;
			String rawId = os.getId();
			tallyCount = Integer.parseInt(rawId.split(",")[0]);

			offspringSeedTally.updateAndGet(x -> Math.max(x, tallyCount));

		} else {
			int id = offspringSeedTally.incrementAndGet();
			os.setId(id+","+(os.getClass().getSimpleName()));
		}

		if(OffspringSeedMap.keySet().contains(os.getId())) {
			throw new Exception("OffspringSeed map already contained an OffspringSeed with this Id ("+os.getId()+"). SOMETHING HAS GONE HORRIBLY WRONG! PANIC!");
		}

		OffspringSeedMap.put(os.getId(), os);

		return os.getId();
	}

	public void removeOffspringSeed(String id) {
		try {
			removeOffspringSeed(Main.game.getOffspringSeedById(id));
		} catch (Exception e) {
			System.err.println("Trying to remove an OffspringSeed that doesn't exist?");
			e.printStackTrace();
		}
	}

	public void removeOffspringSeed(OffspringSeed os) {
		OffspringSeedMap.remove(os.getId());
	}

	public int getNumberOfWitches() {
		int i = 0;
		for(NPC npc : NPCMap.values()) {
			if(npc instanceof Cultist && !npc.getLocationPlace().getPlaceType().equals(PlaceType.GENERIC_EMPTY_TILE)) {
				i++;
			}
		}
		return i;
	}

	public List<List<String>> getSavedEnforcers(AbstractWorldType worldType) {
		savedEnforcers.putIfAbsent(worldType, new ArrayList<>());
		return savedEnforcers.get(worldType);
	}
	
	public void addSavedEnforcers(AbstractWorldType worldType, List<String> enforcerIds) {
		savedEnforcers.putIfAbsent(worldType, new ArrayList<>());
		savedEnforcers.get(worldType).add(enforcerIds);
	}
	
	public void removeSavedEnforcers(AbstractWorldType worldType, List<String> enforcerIds) {
		savedEnforcers.putIfAbsent(worldType, new ArrayList<>());
		savedEnforcers.get(worldType).removeIf((innerList) -> !Collections.disjoint(innerList, enforcerIds));
	}
	
	public NPC getActiveNPC() {
		return activeNPC;
	}

	public void setActiveNPC(NPC activeNPC) {
		this.activeNPC = activeNPC;
	}

	public void clearActiveNPC() {
		this.activeNPC = null;
	}
	
	public boolean isStarted() {
		return started;
	}
	
	/**
	 * Sets whether the game has finished initialisation and has started.
	 * <b/>If set to true, the method handlePostGameInit() is called to finish post-init setups.
	 */
	public void setStarted(boolean started) {
		this.started = started;
		if(started) {
			Main.game.handlePostGameInit();
		}
	}

	public boolean isPrologueFinished() {
		return getPlayer()!=null && getPlayer().hasQuest(QuestLine.MAIN) && getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_PROLOGUE);
	}

	// Dialogues:

	public boolean isInNewWorld() {
		if(Main.game.getPlayer()==null) {
			return true;
		}
		return Main.game.getPlayer().getWorldLocation()!=WorldType.EMPTY
				&& Main.game.getPlayer().getWorldLocation()!=WorldType.MUSEUM
					&& Main.game.getPlayer().getWorldLocation()!=WorldType.MUSEUM_LOST;
	}
	
	public StringBuilder getTextStartStringBuilder() {
		return textStartStringBuilder;
	}

	public void appendToTextStartStringBuilder(String text) {
		textStartStringBuilder.append(UtilText.parse(text));
	}
	
	public void appendToTextStartStringBuilder(GameCharacter npc, String text) {
		textStartStringBuilder.append(UtilText.parse(npc, text));
	}
	
	public void appendToTextStartStringBuilder(List<GameCharacter> npcs, String text) {
		textStartStringBuilder.append(UtilText.parse(npcs, text));
	}

	public void clearTextStartStringBuilder() {
		textStartStringBuilder.setLength(0);
	}

	public StringBuilder getTextEndStringBuilder() {
		return textEndStringBuilder;
	}

	public void appendToTextEndStringBuilder(String text) {
		textEndStringBuilder.append(UtilText.parse(text));
	}
	
	public void appendToTextEndStringBuilder(GameCharacter npc, String text) {
		textEndStringBuilder.append(UtilText.parse(npc, text));
	}
	
	public void appendToTextEndStringBuilder(List<GameCharacter> npcs, String text) {
		textEndStringBuilder.append(UtilText.parse(npcs, text));
	}

	public void clearTextEndStringBuilder() {
		textEndStringBuilder.setLength(0);
	}
	
	/**
	 * @return true if (the player's travel is not restricted && can use walking fast-travel), or if (the current dialogue is the default dialogue for the player's cell).
	 */
	public boolean isInNeutralDialogue() {
		return (!Main.game.getCurrentDialogueNode().isTravelDisabled() && MapTravelType.WALK_SAFE.isAvailable(Main.game.getPlayerCell(), Main.game.getPlayer()))
				|| Main.game.getCurrentDialogueNode().equals(Main.game.getDefaultDialogue(false));
	}
	
	public DialogueNode getCurrentDialogueNode() {
		return currentDialogueNode;
	}

	public String getCurrentDialogue() {
		return currentDialogue;
	}

	public DialogueNodeType getMapDisplay() {
		if(currentDialogueNode != null) {
			return currentDialogueNode.getDialogueNodeType();
		}
		return null;
	}

	public boolean isRenderAttributesSection() {
		return renderAttributesSection;
	}

	public void setRenderAttributesSection(boolean renderAttributesSection) {
		this.renderAttributesSection = renderAttributesSection;
	}

	public int getResponsePage() {
		return responsePage;
	}

	public void setResponsePage(int responsePage) {
		this.responsePage = responsePage;
	}

	public boolean isHasNextResponsePage() {
		return currentDialogueNode.getResponse(responseTab, ((responsePage + 1) * MainController.RESPONSE_COUNT)) != null;
	}

	public int getResponseTab() {
		return responseTab;
	}

	public void setResponseTab(int responseTab) {
		this.responseTab = responseTab;
		checkForResponsePage();
	}

	public DialogueNode getSavedDialogueNode() {
		return savedDialogueNode;
	}

	/**
	 * @return true if the currently saved DialogueNode is the tile's default DialogueNode.
	 */
	public boolean isSavedDialogueNeutral() {
		return (!Main.game.getSavedDialogueNode().isTravelDisabled() && MapTravelType.WALK_SAFE.isAvailable(Main.game.getPlayerCell(), Main.game.getPlayer()))
				|| Main.game.getSavedDialogueNode().equals(Main.game.getDefaultDialogue(false));
//		return Main.game.getSavedDialogueNode().equals(Main.game.getPlayerCell().getDialogue(false));
	}
	
	public Cell getPlayerCell() {
		return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation());
	}

	public DialogueManager getDialogueManager() {
		return dialogueManager;
	}

	public DialogueFlags getDialogueFlags() {
		return dialogueFlags;
	}
	
	public int getResponsePointer() {
		return responsePointer;
	}

	public void setResponsePointer(int responsePointer) {
		this.responsePointer = responsePointer;
	}
	
	public boolean isPlayerTileFull() {
		return getActiveWorld().getCell(getPlayer().getLocation()).getInventory().isInventoryFull();
	}
	
	public String runXmlTest(String pathName) {
		return UtilText.runXmlTest(pathName);
	}

	public boolean isDebugMode() {
		return Main.getProperties().hasValue(PropertyValue.debugMode);
	}

	public boolean isLightTheme() {
		return Main.getProperties().hasValue(PropertyValue.lightTheme);
	}
	
	public boolean isAllStickersUnlocked() {
		return Main.getProperties().hasValue(PropertyValue.allStickersUnlocked);
	}

	public boolean isMetricSizes() {
		return Main.getProperties().hasValue(PropertyValue.metricSizes);
	}

	public boolean isMetricWeights() {
		return Main.getProperties().hasValue(PropertyValue.metricWeights);
	}

	public boolean isMetricFluids() {
		return Main.getProperties().hasValue(PropertyValue.metricFluids);
	}
	
	public boolean isMapReveal() {
		return Main.getProperties().hasValue(PropertyValue.mapReveal);
	}
	
	public boolean isConcealedSlotsReveal() {
		return Main.getProperties().hasValue(PropertyValue.concealedSlotsReveal);
	}

	public boolean isEnchantmentCapacityEnabled() {
		return Main.getProperties().hasValue(PropertyValue.enchantmentLimits);
	}

	public boolean isLevelDrainContentEnabled() {
		return Main.getProperties().hasValue(PropertyValue.levelDrain);
	}
	
	public boolean isSillyMode() {
		return isSillyModeEnabled();
	}
	
	public boolean isSillyModeEnabled() {
		return Main.getProperties().hasValue(PropertyValue.sillyMode);
	}
	
	public boolean isNonConEnabled() {
		return Main.getProperties().hasValue(PropertyValue.nonConContent);
	}

	public boolean isInflationContentEnabled() {
		return Main.getProperties().hasValue(PropertyValue.inflationContent);
	}
	
	public boolean isNipplePenEnabled() {
		return Main.getProperties().hasValue(PropertyValue.nipplePenContent);
	}
	
	public boolean isUrethraEnabled() {
		return Main.getProperties().hasValue(PropertyValue.urethralContent);
	}
	
	public boolean isIncestEnabled() {
		return Main.getProperties().hasValue(PropertyValue.incestContent);
	}
	
	public boolean isSadisticSexContent() {
		return Main.getProperties().hasValue(PropertyValue.sadisticSexContent);
	}
	
	public boolean isLipstickMarkingEnabled() {
		return Main.getProperties().hasValue(PropertyValue.lipstickMarkingContent);
	}
	
	public boolean isWeatherInterruptionsEnabled() {
		return Main.getProperties().hasValue(PropertyValue.weatherInterruptions);
	}
	
	public boolean isAutomaticDialogueCopy() {
		return Main.getProperties().hasValue(PropertyValue.automaticDialogueCopy);
	}
	
	public boolean isFacialHairEnabled() {
		return Main.getProperties().hasValue(PropertyValue.facialHairContent);
	}
	
	public boolean isFemaleFacialHairEnabled() {
		return Main.getProperties().hasValue(PropertyValue.feminineBeardsContent);
	}

	public boolean isFurryHairEnabled() {
		return Main.getProperties().hasValue(PropertyValue.furryHairContent);
	}
	
	public boolean isScalyHairEnabled() {
		return Main.getProperties().hasValue(PropertyValue.scalyHairContent);
	}
	
	public boolean isPubicHairEnabled() {
		return Main.getProperties().hasValue(PropertyValue.pubicHairContent);
	}
	
	public boolean isBodyHairEnabled() {
		return Main.getProperties().hasValue(PropertyValue.bodyHairContent);
	}
	
	public boolean isAssHairEnabled() {
		return Main.getProperties().hasValue(PropertyValue.assHairContent);
	}
	
	public boolean isVoluntaryNTREnabled() {
		return Main.getProperties().hasValue(PropertyValue.voluntaryNTR);
	}

	public boolean isInvoluntaryNTREnabled() {
		return Main.getProperties().hasValue(PropertyValue.involuntaryNTR);
	}

	public boolean isFutanariTesticlesEnabled() {
		return Main.getProperties().hasValue(PropertyValue.futanariTesticles);
	}

	public boolean isFurryTailPenetrationContentEnabled() {
		return Main.getProperties().hasValue(PropertyValue.furryTailPenetrationContent);
	}

	public boolean isVestigialMultiBreastsEnabled() {
		return Main.getProperties().hasValue(PropertyValue.vestigialMultiBreasts);
	}

	public boolean isAnalContentEnabled() {
		return Main.getProperties().hasValue(PropertyValue.analContent);
	}

	public boolean isGapeContentEnabled() {
		return Main.getProperties().hasValue(PropertyValue.gapeContent);
	}

	public boolean isPenetrationLimitationsEnabled() {
		return Main.getProperties().hasValue(PropertyValue.penetrationLimitations);
	}
	
	public boolean isElasticityAffectDepthEnabled() {
		return Main.getProperties().hasValue(PropertyValue.elasticityAffectDepth);
	}
	
	public boolean isFootContentEnabled() {
		return Main.getProperties().hasValue(PropertyValue.footContent);
	}
	
	public boolean isArmpitContentEnabled() {
		return Main.getProperties().hasValue(PropertyValue.armpitContent);
	}
	
	public boolean isLactationContentEnabled() {
		return Main.getProperties().hasValue(PropertyValue.lactationContent);
	}
	
	public boolean isUdderContentEnabled() {
		return Main.getProperties().hasValue(PropertyValue.udderContent);
	}

	public boolean isCumRegenerationEnabled() {
		return Main.getProperties().hasValue(PropertyValue.cumRegenerationContent);
	}

	public boolean isBadEndsEnabled() {
		return Main.getProperties().hasValue(PropertyValue.badEndContent);
	}
	
	public void setBadEnd(String badEndTitle) {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.badEnd, true);
		Main.getProperties().badEndTitle = badEndTitle;
	}

	public boolean isBadEnd() {
		return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.badEnd);
	}

	public boolean isCompanionContentEnabled() {
		return Main.getProperties().hasValue(PropertyValue.companionContent);
	}
	
	public boolean isMuskContentEnabled() {
		return Main.getProperties().hasValue(PropertyValue.muskContent);
	}
	
	public boolean isFeralContentEnabled() {
		return Main.getProperties().hasValue(PropertyValue.feralContent);
	}

	public boolean isPlotDiscovered() {
		return Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN);
	}
	
	
	public boolean isRenderMap() {
		return renderMap;
	}

	public void setRenderMap(boolean renderMap) {
		this.renderMap = renderMap;
	}
	
	public boolean isInGlobalMap() {
		return this.getPlayer().getLocationPlace().getPlaceType().isGlobalMapTile();
	}

	public SizedStack<EventLogEntry> getEventLog() {
		return eventLog;
	}
	
	public void addEvent(EventLogEntry event, boolean appendAdditionTextToMainDialogue) {
		eventLog.push(event);
		if(appendAdditionTextToMainDialogue) {
			Main.game.getTextEndStringBuilder().append(event.getMainDialogueDescription());
		}
	}
	
	public void setEventLog(SizedStack<EventLogEntry> eventLog) {
		this.eventLog = eventLog;
	}
	
	public SizedStack<Value<Integer, List<SlaveryEventLogEntry>>> getSlaveryEventLog() {
		return slaveryEventLog;
	}
	
	public List<SlaveryEventLogEntry> getSlaveryEvents(int day) {
		for(Value<Integer, List<SlaveryEventLogEntry>> value : slaveryEventLog) {
			if(value.getKey()==day) {
				return value.getValue();
			}
		}
		return null;
	}
	
	public void addSlaveryEvent(int day, SlaveryEventLogEntry event) {
		for(Value<Integer, List<SlaveryEventLogEntry>> value : slaveryEventLog) {
			if(value.getKey()==day) {
				value.getValue().add(event);
				return;
			}
		}
		slaveryEventLog.push(new Value<>(day, Util.newArrayListOfValues(event)));
	}
	

	public int getNpcTally() {
		return npcTally.get();
	}

	public OccupancyUtil getOccupancyUtil() {
		return occupancyUtil;
	}
	
	/**
	 *  <b>Be careful using this</b>, as it has a chance to trigger the tile's random encounter.
	 */
	public DialogueNode getDefaultDialogue() {
		return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(true);
	}
	
	public DialogueNode getDefaultDialogue(boolean withRandomEncounter) {
		return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(withRandomEncounter);
	}
	
	public DialogueNode getDefaultDialogue(boolean withRandomEncounter, boolean forceEncounter) {
		return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(withRandomEncounter, forceEncounter);
	}

	public boolean isRequestAutosave() {
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.badEnd)) { // Do not autosave during a bad end (as otherwise it could overwrite an important autosave before the bad end was encountered)
			return false;
		}
		if(Main.getProperties().autoSaveFrequency==2 && lastAutoSaveTime+(7*24*60*60)>Main.game.getSecondsPassed()) {
			return false;
		} else if(Main.getProperties().autoSaveFrequency==1 && lastAutoSaveTime+(24*60*60)>Main.game.getSecondsPassed()) {
			return false;
		} else {
			return requestAutosave;
		}
	}

	public void setRequestAutosave(boolean requestAutosave) {
		this.requestAutosave = requestAutosave;
	}

	public boolean isSpittingDisabled() {
		return !Main.getProperties().hasValue(PropertyValue.spittingEnabled);
	}
	
	public boolean isOpportunisticAttackersEnabled() {
		return Main.getProperties().hasValue(PropertyValue.opportunisticAttackers);
	}
	
	public boolean isOffspringEncountersEnabled() {
		return Main.getProperties().hasValue(PropertyValue.offspringEncounters);
	}
	
	public boolean isBypassSexActionsEnabled() {
		return Main.getProperties().bypassSexActions!=0;
	}
	
	public boolean isBraxMainQuestComplete() {
		return Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN);
	}

	public Map<String, CharacterInventory> getSavedInventories() {
		return savedInventories;
	}

	public void addSavedInventory(GameCharacter character) {
		savedInventories.put(character.getId(), new CharacterInventory(character.getInventory()));
//		System.out.println("Saved: "+character.getName());
	}
	
	
	// These methods are for access to static methods via external xml files

	
	/**
	 * For use in external res files as a way to get a hook to UtilText.parseFromXMLFile
	 */
	public String parseFromFile(String pathName, String tag, GameCharacter... specialNPCs) {
		return getDialogueManager().getDialogueFromFile(pathName, tag, specialNPCs);
	}
	
	/**
	 * For use in external res files as a way to get a hook to UtilText.parse
	 */
	public String forceParse(String content) {
		return UtilText.parse(content);
	}
	
	/**
	 * For use in external res files as a way to get a hook to UtilText.addSpecialParsingString
	 */
	public void addSpecialParsingString(String content, boolean clearListBeforeAdding) {
		UtilText.addSpecialParsingString(content, clearListBeforeAdding);
	}

	/**
	 * For use in external res files as a way to get a hook to UtilText.clearSpecialParsingStrings
	 */
	public void clearSpecialParsingStrings() {
		UtilText.clearSpecialParsingStrings();
	}

	/**
	 * For use in external res files as a way to get a hook to Main.mainController.openInventory
	 */
	public void openInventoryDialogue(NPC target, InventoryInteraction interactionType) {
		Main.mainController.openInventory(target, interactionType);
	}
	
	public void initCosmeticsDialogue(NPC beautician, GameCharacter target, DialogueNode returnToNode) {
		CosmeticsDialogue.initDialogue(beautician, target, returnToNode);
	}

	public void initTransformationMenu(GameCharacter target) {
		Main.game.saveDialogueNode();
		BodyChanging.setTarget(target);
	}

	public void initQuickTransformationMenu(GameCharacter target, DialogueNode endingNode) {
		QuickTransformations.initQuickTransformations("misc/quickTransformations", target, endingNode);
	}
	
	public void setParserTarget(String parserTarget, NPC npc) {
		ParserTarget.addAdditionalParserTarget(parserTarget, npc);
	}
	
	public boolean isFreeRoomAvailableForOccupant() {
		return OccupancyUtil.isFreeRoomAvailableForOccupant();
	}
	
	public Cell getFreeRoomForOccupant() {
		return OccupancyUtil.getFreeRoomForOccupant();
	}
	
	public boolean isSpaceForMoreProstitutes() {
		return RedLightDistrict.isSpaceForMoreProstitutes();
	}

	AbstractCoreItem randomItem = null;
	public AbstractItem getAlleywayItem() {
		return (AbstractItem) randomItem;
	}
	
	public AbstractClothing getAlleywayClothing() {
		return (AbstractClothing) randomItem;
	}
	
	public AbstractWeapon getAlleywayWeapon() {
		return (AbstractWeapon) randomItem;
	}
	
	public void generateAlleywayItem() {
		if(!Main.game.isSillyModeEnabled() || Math.random()<0.99f) {
			List<AbstractItemType> itemsToDrawFrom = ItemType.getDominionAlleywayItems();
			
			if(Main.game.getPlayer().getWorldLocation()==WorldType.BAT_CAVERNS) {
				itemsToDrawFrom = ItemType.getBatCavernItems();
				
			} else if(Main.game.getPlayer().getWorldLocation().getWorldRegion()==WorldRegion.SUBMISSION) {
				itemsToDrawFrom = ItemType.getSubmissionTunnelItems();
				
			} else if(Main.game.getPlayer().getWorldLocation().getWorldRegion()==WorldRegion.FIELD_CITY
					|| Main.game.getPlayer().getWorldLocation().getWorldRegion()==WorldRegion.FIELDS
					|| Main.game.getPlayer().getWorldLocation().getWorldRegion()==WorldRegion.WOODLAND
					|| Main.game.getPlayer().getWorldLocation().getWorldRegion()==WorldRegion.RIVER) {
				itemsToDrawFrom = ItemType.getElisAlleywayItems();
			}
			randomItem = Main.game.getItemGen().generateItem(itemsToDrawFrom.get(Util.random.nextInt(itemsToDrawFrom.size())));
			
		} else {
			if(Math.random()<0.5f) {
				randomItem = Main.game.getItemGen().generateItem(ItemType.EGGPLANT);
			} else {
				randomItem = Main.game.getItemGen().generateItem("innoxia_cheat_unlikely_whammer");
			}
		}
	}
	
	public void generateAlleywayClothing() {
		if(Math.random()<0.01f) {
			randomItem = Main.game.getItemGen().generateClothing(ClothingType.MEGA_MILK);
			Main.game.getPlayerCell().getInventory().addClothing((AbstractClothing) randomItem);
			
		} else {
			List<AbstractClothingType> randomClothingList = new ArrayList<>(ClothingType.getAllClothing());
			randomClothingList.removeIf((clothing) ->
					(!clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_KATE)
						&& !clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_NYAN)
						&& !clothing.getDefaultItemTags().contains(ItemTag.DOMINION_ALLEYWAY_SPAWN))
					|| clothing.getDefaultItemTags().contains(ItemTag.NO_RANDOM_SPAWN)
					|| clothing.getRarity()==Rarity.EPIC
					|| clothing.getRarity()==Rarity.LEGENDARY);
			randomItem = Main.game.getItemGen().generateClothing(randomClothingList.get(Util.random.nextInt(randomClothingList.size())));
		}
	}
	
	public void generateAlleywayWeapon() {
		List<AbstractWeaponType> weapons = new ArrayList<>(WeaponType.getAllWeapons());
		weapons.removeIf(w -> !w.getItemTags().contains(ItemTag.DOMINION_ALLEYWAY_SPAWN));
		randomItem = Main.game.getItemGen().generateWeapon(weapons.get(Util.random.nextInt(weapons.size())));
	}
	
	public boolean isOffspringEncounterAvailable(AbstractWorldType worldType, AbstractPlaceType placeType) {
		List<OffspringSeed> offspringAvailable = Main.game.getOffspringNotSpawned(
				os-> (os.getSubspecies()==Subspecies.HALF_DEMON
					?(os.getHalfDemonSubspecies().isAbleToNaturallySpawnInLocation(worldType, placeType))
					:(os.getSubspecies().isAbleToNaturallySpawnInLocation(worldType, placeType))));
		
		return !offspringAvailable.isEmpty();
	}
	
	public void initOffspringEncounter(AbstractWorldType worldType, AbstractPlaceType placeType) {
		List<OffspringSeed> offspringAvailable = Main.game.getOffspringNotSpawned(
				os-> (os.getSubspecies()==Subspecies.HALF_DEMON
					?(os.getHalfDemonSubspecies().isAbleToNaturallySpawnInLocation(worldType, placeType))
					:(os.getSubspecies().isAbleToNaturallySpawnInLocation(worldType, placeType))));
		
		if(!offspringAvailable.isEmpty()) {
			AbstractEncounter.SpawnAndStartChildHere(offspringAvailable);
		}
	}
	
	// Glory holes:

	public void spawnDomGloryHoleNPC(String genericName) {
		spawnDomGloryHoleNPC(genericName, null);
	}
	
	public void spawnDomGloryHoleNPC(String genericName, String parserTarget) {
		NPC npc = new GenericSexualPartner(Gender.getGenderFromUserPreferences(false, true), Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false, (s)->s.isNonBiped()) {
			@Override
			public void turnUpdate() {
				if(this.getGenitalArrangement()==GenitalArrangement.NORMAL) { // Hide ass areas if normal genitals (not entirely sure why this was added...)
					this.setAreaKnownByCharacter(CoverableArea.ASS, Main.game.getPlayer(), false);
					this.setAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer(), false);
				}
			}
		};
		
		npc.setRaceConcealed(true);
		List<String> excludedAdjectives = Util.newArrayListOfValues();
		for(NPC npcPresent : Main.game.getCharactersPresent()) {
			if(npcPresent instanceof GenericSexualPartner && npcPresent.getGenericName().contains(" ")) {
				excludedAdjectives.add(npcPresent.getGenericName().split(" ")[0]);
			}
		}
		Main.game.getCharacterUtils().setGenericName(npc, genericName, excludedAdjectives);
		
		npc.setDescription("[npc.Name] is a complete stranger to you, who, after wandering in to use the toilet, found you offering to service [npc.her] #IF(npc.hasPenis())cock#ELSEpussy#ENDIF at a gloryhole...");
		
		if(Math.random()<0.4f) {
			npc.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		} else {
			if(Main.game.getPlayer().isFeminine()) {
				npc.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			} else {
				npc.setSexualOrientation(SexualOrientation.ANDROPHILIC);
			}
		}
		npc.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.THREE_LIKE);
		npc.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.TWO_NEUTRAL);
		npc.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE);
		npc.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
		npc.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE);
		npc.removeFetish(Fetish.FETISH_NON_CON_SUB);
		if(npc.hasVagina()) {
			npc.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
		}
		if(npc.hasPenis()) {
			npc.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.THREE_LIKE);
		}
		
		npc.unequipAllClothingIntoVoid(true, true);
		
		npc.setPenisVirgin(false);
		npc.setVaginaVirgin(false);
		
		npc.setAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer(), false);
		
		try {
			Main.game.addNPC(npc, false);
			Main.game.setActiveNPC(npc);
			if(parserTarget!=null && !parserTarget.isEmpty()) {
				ParserTarget.addAdditionalParserTarget(parserTarget, npc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void spawnSubGloryHoleNPC(String genericName) {
		spawnSubGloryHoleNPC(genericName, null);
	}
	
	public void spawnSubGloryHoleNPC(String genericName, String parserTarget) {
		NPC npc = new GenericSexualPartner(Gender.getGenderFromUserPreferences(false, false), Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false, (s)->s.isNonBiped());

		npc.setRaceConcealed(true);
		Main.game.getCharacterUtils().setGenericName(npc, genericName, Util.newArrayListOfValues());

		npc.setDescription("[npc.Name] is a complete stranger to you, who, after wandering in to use the toilet, decided to service a gloryhole...");
		
		if(Math.random()<0.4f) {
			npc.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		} else {
			if(Main.game.getPlayer().isFeminine()) {
				npc.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			} else {
				npc.setSexualOrientation(SexualOrientation.ANDROPHILIC);
			}
		}
		npc.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.TWO_NEUTRAL);
		npc.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
		npc.setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.THREE_LIKE);
		npc.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE);
		npc.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE);
		npc.removeFetish(Fetish.FETISH_NON_CON_SUB);
		if(npc.hasVagina()) {
			npc.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
		}
		if(Math.random()>0.75f) {
			npc.addFetish(Fetish.FETISH_ORAL_GIVING);
		}
		
		npc.unequipAllClothingIntoVoid(true, true);
		
		npc.setPenisVirgin(false);
		npc.setVaginaVirgin(false);
		npc.setAssVirgin(false);
		npc.setFaceVirgin(false);
		
		npc.setAllAreasKnownByCharacter(Main.game.getPlayer(), false);
		
		try {
			Main.game.addNPC(npc, false);
			Main.game.setActiveNPC(npc);
			if(parserTarget!=null && !parserTarget.isEmpty()) {
				ParserTarget.addAdditionalParserTarget(parserTarget, npc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initGamblersInElisTavern() {
		AbstractWorldType wt = WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_alley");
		AbstractPlaceType pt = PlaceType.getPlaceTypeFromId("innoxia_fields_elis_tavern_alley_dice_poker");
		
		List<NPC> gamblersPresent = Main.game.getCharactersPresent(Main.game.getWorlds().get(wt).getCell(pt));
		for(NPC npc : gamblersPresent) {
			if(npc instanceof GamblingDenPatron) {
				Main.game.banishNPC(npc);
			}
		}
		try {
			Main.game.addNPC(new GamblingDenPatron(Gender.getGenderFromUserPreferences(false, false), DicePokerTable.COPPER, wt, pt, false), false);
			Main.game.addNPC(new GamblingDenPatron(Gender.getGenderFromUserPreferences(false, false), DicePokerTable.COPPER, wt, pt, false), false);
			Main.game.addNPC(new GamblingDenPatron(Gender.getGenderFromUserPreferences(false, false), DicePokerTable.SILVER, wt, pt, false), false);
			Main.game.addNPC(new GamblingDenPatron(Gender.getGenderFromUserPreferences(false, false), DicePokerTable.SILVER, wt, pt, false), false);
			Main.game.addNPC(new GamblingDenPatron(Gender.getGenderFromUserPreferences(false, false), DicePokerTable.GOLD, wt, pt, false), false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Moves the currently-used inventory from whatever bank it's in to the bank that the player is currently in.
	 */
	public void moveBankInventory() {
		Map<AbstractWorldType, AbstractPlaceType> bankPlaces = new HashMap<>();
		bankPlaces.put(WorldType.getWorldTypeFromId("innoxia_dominion_bank"), PlaceType.getPlaceTypeFromId("innoxia_dominion_bank_deposit_box"));
		bankPlaces.put(WorldType.getWorldTypeFromId("innoxia_fields_elis_bank"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_bank_deposit_box"));
		
		CharacterInventory inventory = null;
		for(Entry<AbstractWorldType, AbstractPlaceType> entry : bankPlaces.entrySet()) {
			CharacterInventory bankInventory = Main.game.getWorlds().get(entry.getKey()).getCell(entry.getValue()).getInventory();
			if(!bankInventory.isEmpty()) {
				inventory = new CharacterInventory(bankInventory);
				Main.game.getWorlds().get(entry.getKey()).getCell(entry.getValue()).setInventory(new CharacterInventory(0));
				break;
			}
		}
		
		if(inventory!=null) {
			for(Entry<AbstractWorldType, AbstractPlaceType> entry : bankPlaces.entrySet()) {
				if(Main.game.getPlayer().getWorldLocation()==entry.getKey()) {
					Main.game.getWorlds().get(entry.getKey()).getCell(entry.getValue()).setInventory(inventory);
				}
			}
		}
	}
	
	// UtilText method access:
	
	public boolean isVowel(char c) {
		return UtilText.isVowel(c);
	}
	
	// For use in debug menu:

	public void startGenericSex(GameCharacter character) {
//		Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
		Main.game.setContent(
				new ResponseSex(UtilText.parse(character, "Sex with [npc.name]"),
					UtilText.parse(character, "Start a generic sex scene with [npc.name]"),
					true,
					true,
					new SMGeneric(
							Util.newArrayListOfValues(Main.game.getPlayer()),
							Util.newArrayListOfValues(character),
					null,
					null),
					Main.game.getDefaultDialogue(false),
					"<p>"
						+ UtilText.parse(character, "You start having sex with [npc.name]")
					+ "</p>"));
	}
}
