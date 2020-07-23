package com.lilithsthrone.game;

import java.io.File;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.controller.TooltipUpdateThread;
import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInformationEventListener;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Amber;
import com.lilithsthrone.game.character.npc.dominion.Angel;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Ashley;
import com.lilithsthrone.game.character.npc.dominion.Brax;
import com.lilithsthrone.game.character.npc.dominion.Bunny;
import com.lilithsthrone.game.character.npc.dominion.CandiReceptionist;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.DominionAlleywayAttacker;
import com.lilithsthrone.game.character.npc.dominion.EnforcerPatrol;
import com.lilithsthrone.game.character.npc.dominion.Finch;
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
import com.lilithsthrone.game.character.npc.dominion.Kruger;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Loppy;
import com.lilithsthrone.game.character.npc.dominion.Lumi;
import com.lilithsthrone.game.character.npc.dominion.Natalya;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
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
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKatherine;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKelly;
import com.lilithsthrone.game.character.npc.misc.GenericAndrogynousNPC;
import com.lilithsthrone.game.character.npc.misc.GenericFemaleNPC;
import com.lilithsthrone.game.character.npc.misc.GenericMaleNPC;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.npc.misc.NPCOffspring;
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
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.npc.submission.Murk;
import com.lilithsthrone.game.character.npc.submission.Roxy;
import com.lilithsthrone.game.character.npc.submission.Shadow;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.character.npc.submission.SlimeGuardFire;
import com.lilithsthrone.game.character.npc.submission.SlimeGuardIce;
import com.lilithsthrone.game.character.npc.submission.SlimeQueen;
import com.lilithsthrone.game.character.npc.submission.SlimeRoyalGuard;
import com.lilithsthrone.game.character.npc.submission.SubmissionCitadelArcanist;
import com.lilithsthrone.game.character.npc.submission.Vengar;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueFlags;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntry;
import com.lilithsthrone.game.dialogue.eventLog.SlaveryEventLogEntry;
import com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ.EnforcerHQDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaverAlleyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict.DominionExpress;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloor;
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
import com.lilithsthrone.game.dialogue.utils.DebugDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.game.dialogue.utils.MiscDialogue;
import com.lilithsthrone.game.dialogue.utils.PhoneDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.game.occupantManagement.OccupancyUtil;
import com.lilithsthrone.game.occupantManagement.slave.SlaveEvent;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermission;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.game.settings.KeyCodeWithModifiers;
import com.lilithsthrone.game.settings.KeyboardAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
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
	
	private PlayerCharacter player;
	
	// NPCs:
	private NPC activeNPC;
	private AtomicInteger npcTally = new AtomicInteger(0);

	//Note : this is a ConcurrentHashMap
	private Map<String, NPC> NPCMap;
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
	
	private boolean started;
	
	private DialogueFlags dialogueFlags;
	
	private static Map<String, CharacterInventory> savedInventories; // Map of ID to inventory
	
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
	private List<EventLogEntry> eventLog = new ArrayList<>();
	private SizedStack<Value<Integer, List<SlaveryEventLogEntry>>> slaveryEventLog = new SizedStack<>(7);
	
	// Slavery:
	private OccupancyUtil occupancyUtil = new OccupancyUtil();

	public Game() {
		worlds = new HashMap<>();
		for(AbstractWorldType type : WorldType.getAllWorldTypes()) {
			worlds.put(type, null);
		}
		OccupantManagementDialogue.resetImportantCells();
		startingDate = LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(), 00, 00);
		secondsPassed = TIME_START_SECONDS;
		inCombat = false;
		inSex = false;
		renderAttributesSection = false;
		renderMap = false;

		dialogueFlags = new DialogueFlags();

		started = false;

		NPCMap = new ConcurrentHashMap<>();
		
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
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			

			// Writing game stuff to export:
			
			Element characterNode = doc.createElement("exportedCharacter");
			doc.appendChild(characterNode);
			
			character.saveAsXML(characterNode, doc);
			
			// Ending stuff:
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer1 = tf.newTransformer();
			transformer1.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();

			transformer1.transform(new DOMSource(doc), new StreamResult(writer));
			
			// Save this xml:
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
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

			if(timeLog) {
				System.out.println("Difference: "+(System.nanoTime()-timeStart)/1000000000f);
			}
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
	
	public static GameCharacter importCharacterAsSlave(String name) {
		File file = new File("data/characters/"+name+".xml");
		
		if (file.exists()) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(file);
				
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
						CharacterImportSetting.REMOVE_RACE_CONCEALED);
				try {
					if(((Element)((Element)((Element)characterElement.getElementsByTagName("character").item(0)).getElementsByTagName("core").item(0)).getElementsByTagName("id").item(0)).getAttribute("value").equals("PlayerCharacter")) {
						importedSlave.setBirthday(importedSlave.getBirthday().plusYears(18)); // If the imported character is a player character, they need to have their age adjusted to fit with the fact that NPCs start at age 18
					}
				} catch(Exception ex) {	
				}
				Main.game.addNPC(importedSlave, false);
				importedSlave.applyNewlyImportedSlaveVariables();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public static void exportGame(String exportFileName, boolean allowOverwrite) {
		
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
		
		try {
			if(timeLog) {
				timeStart = System.nanoTime();
				System.out.println(timeStart);
			}
			// Starting stuff:
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			

			// Writing game stuff to export:
			
			Element game = doc.createElement("game");
			doc.appendChild(game);
			
			try {
				Element informationNode = doc.createElement("coreInfo");
				game.appendChild(informationNode);
				CharacterUtils.addAttribute(doc, informationNode, "version", Main.VERSION_NUMBER);
				CharacterUtils.addAttribute(doc, informationNode, "lastAutoSaveTime", String.valueOf(Main.game.lastAutoSaveTime));
				CharacterUtils.addAttribute(doc, informationNode, "secondsPassed", String.valueOf(Main.game.secondsPassed));
				CharacterUtils.addAttribute(doc, informationNode, "weather", Main.game.currentWeather.toString());
				CharacterUtils.addAttribute(doc, informationNode, "nextStormTimeInSeconds", String.valueOf(Main.game.nextStormTimeInSeconds));
				CharacterUtils.addAttribute(doc, informationNode, "gatheringStormDurationInSeconds", String.valueOf(Main.game.gatheringStormDurationInSeconds));
				CharacterUtils.addAttribute(doc, informationNode, "weatherTimeRemainingInSeconds", String.valueOf(Main.game.weatherTimeRemainingInSeconds));

				Element inventoryNode = doc.createElement("savedInventories");
				game.appendChild(inventoryNode);
				for(Entry<String, CharacterInventory> entry : savedInventories.entrySet()) {
					Element element = doc.createElement("savedInventory");
					CharacterUtils.addAttribute(doc, element, "character", entry.getKey());
					inventoryNode.appendChild(element);
					entry.getValue().saveAsXML(element, doc);
				}

				Element savedEnforcersNode = doc.createElement("savedEnforcers");
				game.appendChild(savedEnforcersNode);
				for(Entry<AbstractWorldType, List<List<String>>> entrySet : Main.game.savedEnforcers.entrySet()) {
					Element element = doc.createElement("world");
					savedEnforcersNode.appendChild(element);
					CharacterUtils.addAttribute(doc, element, "type", WorldType.getIdFromWorldType(entrySet.getKey()));
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
					Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "<style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail<b>", "SlaveryUtil failure"), false);
				}
				
				Element dateNode = doc.createElement("date");
				informationNode.appendChild(dateNode);
				CharacterUtils.addAttribute(doc, dateNode, "year", String.valueOf(Main.game.startingDate.getYear()));
				CharacterUtils.addAttribute(doc, dateNode, "month", String.valueOf(Main.game.startingDate.getMonthValue()));
				CharacterUtils.addAttribute(doc, dateNode, "dayOfMonth", String.valueOf(Main.game.startingDate.getDayOfMonth()));
				CharacterUtils.addAttribute(doc, dateNode, "hour", String.valueOf(Main.game.startingDate.getHour()));
				CharacterUtils.addAttribute(doc, dateNode, "minute", String.valueOf(Main.game.startingDate.getMinute()));
			} catch(Exception ex) {
				System.err.println("coreInfo saving failed!");
				Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "<style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail<b>", "coreInfo failure"), false);
			}
			
			Main.game.dialogueFlags.saveAsXML(game, doc);
			
			try {
				Element eventLogNode = doc.createElement("eventLog");
				game.appendChild(eventLogNode);
				for(EventLogEntry event : Main.game.getEventLog().subList(Math.max(0, Main.game.getEventLog().size()-50), Main.game.getEventLog().size())) {
					event.saveAsXML(eventLogNode, doc);
				}
			} catch(Exception ex) {
				System.err.println("eventLog saving failed!");
				Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "<style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail<b>", "eventLog failure"), false);
			}
		
			try {
				Element slaveryEventLogNode = doc.createElement("slaveryEventLog");
				game.appendChild(slaveryEventLogNode);
				for(Value<Integer, List<SlaveryEventLogEntry>> entry : Main.game.getSlaveryEventLog()) {
					Element element = doc.createElement("day");
					slaveryEventLogNode.appendChild(element);
					CharacterUtils.addAttribute(doc, element, "value", String.valueOf(entry.getKey()));
					for(SlaveryEventLogEntry event : entry.getValue()) {
						event.saveAsXML(element, doc);
					}
				}
			} catch(Exception ex) {
				System.err.println("slaveryEventLog saving failed!");
				Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "<style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail<b>", "slaveryEventLog failure"), false);
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
				Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "<style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail<b>", "maps failure"), false);
			}
			
			// Add player:
			try {
				Element characterNode = doc.createElement("playerCharacter");
				game.appendChild(characterNode);
				Main.game.getPlayer().saveAsXML(characterNode, doc);
			} catch(Exception ex) {
				System.err.println("playerCharacter saving failed!");
				Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "<style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail<b>", "playerCharacter failure"), false);
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
				Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "<style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail<b>", "NPC failure"), false);
			}
			
			
			// Ending stuff:
			try {
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer transformer1 = tf.newTransformer();
				transformer1.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
				StringWriter writer = new StringWriter();
	
				transformer1.transform(new DOMSource(doc), new StreamResult(writer));
				
				// Save this xml:
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
				DOMSource source = new DOMSource(doc);
				
				String saveLocation = "data/saves/"+exportFileName+".xml";
				StreamResult result = new StreamResult(saveLocation);
				
				transformer.transform(source, result);
				
				if(!exportFileName.startsWith("AutoSave")) {
					if(overwrite) {
						Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "[style.colourGood(Game saved)]", saveLocation), false);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()), false, PresetColour.GENERIC_GOOD, "Save game overwritten!");
					} else {
						Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "[style.colourGood(Game saved)]", saveLocation), false);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()), false, PresetColour.GENERIC_GOOD, "Game saved!");
					}
				}
			} catch(Exception ex) {
				System.err.println("XML writing failed!");
				ex.printStackTrace();
				Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "<style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail</span>", "XML writing failure"), false);
			}
			
			if(timeLog) {
				System.out.println("Difference: "+(System.nanoTime()-timeStart)/1000000000f);
			}
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}
	}
	
	private static boolean debug = false;

	public static void importGame(String name) {
		File file = new File("data"+System.getProperty("file.separator")+"saves"+System.getProperty("file.separator"), name+".xml");
		
		importGame(file);
	}
	
	public static void importGame(File file) {
		Main.game = new Game();
		
		if (file.exists()) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(file);

				long time = System.nanoTime();
				if(debug) {
					System.out.println("Load game start");
				}
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element gameElement = (Element) doc.getElementsByTagName("game").item(0);
				
				Element informationNode = (Element) gameElement.getElementsByTagName("coreInfo").item(0);
				
				loadingVersion = informationNode.getAttribute("version");

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
					Main.game.eventLog.add(EventLogEntry.loadFromXML(e, doc));
				}
				Main.game.eventLog.sort(Comparator.comparingLong(EventLogEntry::getTime).reversed());
				
				
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
//							&& (!worldType.equals("SLAVER_ALLEY") || !Main.isVersionOlderThan(loadingVersion, "0.2.2"))
							&& (!worldType.equals("HARPY_NEST") || !Main.isVersionOlderThan(loadingVersion, "0.2.1.5"))
							&& (!worldType.equals("BAT_CAVERNS") || !Main.isVersionOlderThan(loadingVersion, "0.2.3.5"))
//							&& (!worldType.equals("SLAVER_ALLEY") || !Main.isVersionOlderThan(loadingVersion, "0.2.10"))
							&& (!worldType.equals("LYSSIETH_PALACE") || !Main.isVersionOlderThan(loadingVersion, "0.3"))
//							&& (!worldType.equals("GAMBLING_DEN") || !Main.isVersionOlderThan(loadingVersion, "0.3.5.4"))
							&& (!worldType.equals("RAT_WARRENS") || !Main.isVersionOlderThan(loadingVersion, "0.3.5.6"))
							&& (!worldType.equals("SLAVER_ALLEY") || !Main.isVersionOlderThan(loadingVersion, "0.3.5.6"))
							&& (!worldType.equals("DOMINION_EXPRESS") || !Main.isVersionOlderThan(loadingVersion, "0.3.7.9"))
							&& !worldType.equals("JUNGLE")
							) {
						World world = World.loadFromXML(e, doc);
						Main.game.worlds.put(world.getWorldType(), world);
					}
				}
				
				// Add missing world types:
				for(AbstractWorldType wt : WorldType.getAllWorldTypes()) {
					Generation gen = new Generation();
					if(Main.isVersionOlderThan(loadingVersion, "0.1.99.5")) {
						gen.worldGeneration(WorldType.SHOPPING_ARCADE);
					}
					if(Main.isVersionOlderThan(loadingVersion, "0.2.1.5")) {
						gen.worldGeneration(WorldType.DOMINION);
						gen.worldGeneration(WorldType.HARPY_NEST);
					}
					if(Main.isVersionOlderThan(loadingVersion, "0.2.10.8")) {
						gen.worldGeneration(WorldType.SUBMISSION);
					}
					if(Main.isVersionOlderThan(loadingVersion, "0.2.11")) {
						gen.worldGeneration(WorldType.IMP_FORTRESS_ALPHA);
						gen.worldGeneration(WorldType.IMP_FORTRESS_FEMALES);
						gen.worldGeneration(WorldType.IMP_FORTRESS_MALES);
					}
					if(Main.isVersionOlderThan(loadingVersion, "0.2.12.5")) {
						gen.worldGeneration(WorldType.IMP_FORTRESS_DEMON);
					}
					if(Main.isVersionOlderThan(loadingVersion, "0.2.2")) {
						gen.worldGeneration(WorldType.DOMINION);
//						gen.worldGeneration(WorldType.SLAVER_ALLEY);
					}
					if(Main.isVersionOlderThan(loadingVersion, "0.2.3.5")) {
						gen.worldGeneration(WorldType.BAT_CAVERNS);
					}
					if(Main.isVersionOlderThan(loadingVersion, "0.2.8")) {
						gen.worldGeneration(WorldType.NIGHTLIFE_CLUB);
					}
					if(Main.isVersionOlderThan(loadingVersion, "0.2.8.1")) {
						gen.worldGeneration(WorldType.EMPTY);
					}
//					if(Main.isVersionOlderThan(loadingVersion, "0.2.10")) {
//						gen.worldGeneration(WorldType.SLAVER_ALLEY);
//					}
					if(Main.isVersionOlderThan(loadingVersion, "0.3")) {
						gen.worldGeneration(WorldType.LYSSIETH_PALACE);
					}
					if(Main.isVersionOlderThan(loadingVersion, "0.3.2.2")) {
						gen.worldGeneration(WorldType.CITY_HALL);
					}
					if(Main.isVersionOlderThan(loadingVersion, "0.3.4.9")) {
						gen.worldGeneration(WorldType.ENFORCER_WAREHOUSE);
						gen.worldGeneration(WorldType.ENFORCER_HQ);
					}
					if(Main.isVersionOlderThan(loadingVersion, "0.3.5.4")) {
						gen.worldGeneration(WorldType.GAMBLING_DEN);
					}
					if(Main.isVersionOlderThan(loadingVersion, "0.3.5.6")) {
						gen.worldGeneration(WorldType.RAT_WARRENS);
						gen.worldGeneration(WorldType.SLAVER_ALLEY);
					}
					if(Main.isVersionOlderThan(loadingVersion, "0.3.7")) {
						gen.worldGeneration(WorldType.HELENAS_APARTMENT);
					}
					if(Main.isVersionOlderThan(loadingVersion, "0.3.7.9")) {
						gen.worldGeneration(WorldType.DOMINION_EXPRESS);
					}
					if(Main.game.worlds.get(wt)==null) {
						gen.worldGeneration(wt);
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
					AbstractItem spellBook = AbstractItemType.generateItem(ItemType.getSpellBookType(Spell.ICE_SHARD));
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
								if(Main.isVersionOlderThan(loadingVersion, "0.3")) {
									className = className.replace("FortressDemonLeader", "DarkSiren");
								}
								
								if(!Main.isVersionOlderThan(loadingVersion, "0.3.5.9") || !id.contains("Helena")) {
									if(Main.isVersionOlderThan(loadingVersion, "0.3.5.9")) {
										className = className.replace("Alexa", "Helena");
									}
									NPC npc = loadNPC(doc, e, className, npcClasses, loadFromXMLMethods, constructors);
									//TODO
//									// In versions prior to v0.3.8.6, deleted NPCs who had relationship or sex data with the player were moved to an empty tile instead of being deleted.
//									// This was causing save file bloat, so now they are fully deleted.
//									if(npc!=null
//											&& Main.isVersionOlderThan(loadingVersion, "0.3.8.6")
//											&& (!npc.isUnique()
//													&& !(npc instanceof Elemental)
//													&& !(npc instanceof ReindeerOverseer)
//													&& !(npc instanceof GenericFemaleNPC)
//													&& !(npc instanceof GenericMaleNPC)
//													&& !(npc instanceof GenericAndrogynousNPC)
//													&& !(npc instanceof PrologueFemale)
//													&& !(npc instanceof PrologueMale)
//													&& !(npc instanceof NPCOffspring))
//											&& npc.getLocationPlace().getPlaceType()==PlaceType.GENERIC_EMPTY_TILE) {
//										System.out.println("Deleted NPC: "+npc.getId());
//										
//									} else
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
											CharacterUtils.generateDesires(npc);
										}
	
										if(Main.isVersionOlderThan(loadingVersion, "0.2.0") && npc.getFetishDesireMap().size()>10) {
											npc.clearFetishDesires();
											CharacterUtils.generateDesires(npc);
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
				
				if(debug) {
					System.out.println("NPCs finished: "+ (System.nanoTime()-time)/1000000000d);
				}

				
				// Add in new NPCS:
				Main.game.initUniqueNPCs();
				
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
					
					Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY));
					Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_2));
					Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_3));
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
						Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.LYSSIETHS_RING));
					}
					if(Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), true)) {
						Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY));
						Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_2));
						Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_3));
					}
					while(Main.game.getPlayer().getItemCount(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY))>1) {
						Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY));
					}
					while(Main.game.getPlayer().getItemCount(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_2))>1) {
						Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_2));
					}
					while(Main.game.getPlayer().getItemCount(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_3))>1) {
						Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_3));
					}
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.3")) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
						Main.game.getNpc(SubmissionCitadelArcanist.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
					}
					if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL)) {
						Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL);
						if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_LILIN_PALACE)
								|| Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_LILIN_PALACE_GATE)){
							Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_LILIN_PALACE_CAVERN);
						}
					}
					Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_LIBRARY).getInventory().addItem(AbstractItemType.generateItem(ItemType.getLoreBook(Subspecies.HALF_DEMON)));
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.3.0.5")) {
					if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL)) {
						ImpCitadelDialogue.clearFortress();
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

				if(Main.isVersionOlderThan(loadingVersion, "0.3.3.8")) {
					Main.game.getPlayer().resetPerksMap(false);
					for(NPC npc : Main.game.getAllNPCs()) {
						if(!(npc.isElemental())) {
							npc.resetPerksMap(true, false);
						}
						PerkManager.initialiseSpecialPerksUponCreation(npc); // Generate unique perks for slaves/occupants as well
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
							Main.game.getNpc(Brax.class).addClothing(AbstractClothingType.generateClothing("dsg_eep_servequipset_enfskirt", PresetColour.CLOTHING_BLACK, false), false);
							Main.game.getNpc(Brax.class).addClothing(AbstractClothingType.generateClothing("dsg_eep_ptrlequipset_flsldshirt", PresetColour.CLOTHING_PINK, false), false);
							Main.game.getNpc(Brax.class).addClothing(AbstractClothingType.generateClothing("innoxia_neck_tie", PresetColour.CLOTHING_BLACK, false), false);
						} else {
							Main.game.getNpc(Brax.class).addClothing(AbstractClothingType.generateClothing("dsg_eep_servequipset_enfdslacks", PresetColour.CLOTHING_BLACK, false), false);
							Main.game.getNpc(Brax.class).addClothing(AbstractClothingType.generateClothing("dsg_eep_ptrlequipset_ssldshirt", PresetColour.CLOTHING_BLUE, false), false);
							Main.game.getNpc(Brax.class).addClothing(AbstractClothingType.generateClothing("innoxia_neck_tie", PresetColour.CLOTHING_BLACK, false), false);
						}
						Main.game.getNpc(Brax.class).addClothing(AbstractClothingType.generateClothing("dsg_eep_uniques_enfdjacket_brax", PresetColour.CLOTHING_BLACK, false), false);
						Main.game.getNpc(Brax.class).addClothing(AbstractClothingType.generateClothing("dsg_eep_servequipset_enfdbelt", PresetColour.CLOTHING_DESATURATED_BROWN, false), false);
						Main.game.getNpc(Brax.class).addClothing(AbstractClothingType.generateClothing("dsg_eep_ptrlequipset_pcap", PresetColour.CLOTHING_BLACK, false), false);
					}
				}

				if(Main.isVersionOlderThan(loadingVersion, "0.3.5.6")) {
					if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_BUYING_BRAX)
							&& (!Main.game.getNpc(Brax.class).isSlave() || !Main.game.getNpc(Brax.class).getOwner().isPlayer() || Main.game.getNpc(Brax.class).getLocationPlace().getPlaceType()==PlaceType.ENFORCER_HQ_RECEPTION_DESK)) {
						EnforcerHQDialogue.obtainBraxAsSlave();
					}
				}

				if(Main.isVersionOlderThan(loadingVersion, "0.3.5.7")) {
					if(Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_THREE_END)) {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, false);
						RatWarrensDialogue.banishMilkers();
					}
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.3.6.6")) {
					for(NPC npc : Main.game.getAllNPCs()) {
						if(Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId()) && npc.getHistory().isLowlife()) {
							npc.setHistory(Occupation.NPC_UNEMPLOYED);
						}
					}
				}

				if(Main.isVersionOlderThan(loadingVersion, "0.3.7")) { // This map update is loacted here as the character present in the new alleyway tile needs to have been initialised.
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
				
				Main.game.pendingSlaveInStocksReset = false;
				
				
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
		
		DialogueNode startingDialogueNode = Main.game.getPlayerCell().getPlace().getDialogue(false);
		Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "[style.colourGood(Game loaded)]", "data/saves/"+Util.getFileName(file)+".xml"), false);
		Main.game.setContent(new Response("", startingDialogueNode.getDescription(), startingDialogueNode), false);
		
		Main.game.endTurn(0);
		Main.game.started = true;
		// Do a zero-time status effect update after declaring that the game has started to make sure that everything is initialised properly (mainly just so external status effects are initialised):
		for(NPC npc : Main.game.getAllNPCs()) {
			npc.calculateStatusEffects(0);
		}
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
		initUniqueNPCs();

		// This is due to the fact that on new world creation, the player is placed at coordinates (0, 0), which reveals the three squares at the bottom left corner of the map:
		Main.game.getActiveWorld().getCell(0, 0).setDiscovered(false);
		Main.game.getActiveWorld().getCell(0, 1).setDiscovered(false);
		Main.game.getActiveWorld().getCell(1, 0).setDiscovered(false);
		

		started = false;
		
		SlaverAlleyDialogue.dailyReset();
		
		UtilText.initScriptEngine();

		started = true;
		
		setContent(new Response(startingDialogueNode.getLabel(), startingDialogueNode.getDescription(), startingDialogueNode));
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
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(TestNPC.class))) { addNPC(new TestNPC(), false); addedNpcs.add(TestNPC.class); }

			// Contributors:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Lumi.class))) { addNPC(new Lumi(), false); addedNpcs.add(Lumi.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Pazu.class))) { addNPC(new Pazu(), false); addedNpcs.add(Pazu.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Ashley.class))) { addNPC(new Ashley(), false); addedNpcs.add(Ashley.class); }
			
			// Story:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Rose.class))) { addNPC(new Rose(), false); addedNpcs.add(Rose.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Lilaya.class))) { addNPC(new Lilaya(), false); addedNpcs.add(Lilaya.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Arthur.class))) { addNPC(new Arthur(), false); addedNpcs.add(Arthur.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Lyssieth.class))) { addNPC(new Lyssieth(), false); addedNpcs.add(Lyssieth.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Elizabeth.class))) { addNPC(new Elizabeth(), false); addedNpcs.add(Elizabeth.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(SubmissionCitadelArcanist.class))) { addNPC(new SubmissionCitadelArcanist(), false); addedNpcs.add(SubmissionCitadelArcanist.class); }
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

			// Shopping Promenade:
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Ralph.class))) { addNPC(new Ralph(), false); addedNpcs.add(Ralph.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Nyan.class))) { addNPC(new Nyan(), false); addedNpcs.add(Nyan.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Vicky.class))) { addNPC(new Vicky(), false); addedNpcs.add(Vicky.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Pix.class))) { addNPC(new Pix(), false); addedNpcs.add(Pix.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Kate.class))) { addNPC(new Kate(), false); addedNpcs.add(Kate.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(SupplierLeader.class))) { addNPC(new SupplierLeader(), false); addedNpcs.add(SupplierLeader.class); }
			if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(SupplierPartner.class))) { addNPC(new SupplierPartner(), false); addedNpcs.add(SupplierPartner.class); }

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
	public boolean pendingSlaveInStocksReset = true;
	private List<NPC> npcsToRemove = new ArrayList<>();
	private List<NPC> npcsToAdd = new ArrayList<>();
	
	public void endTurn(int secondsPassedThisTurn, boolean advanceTime) {

		boolean loopDebug = false;
		
		long tStart = System.nanoTime();
		
		long startHour = getHour();
		
		if(advanceTime) {
			secondsPassed += secondsPassedThisTurn;
			updateResponses();
		}

		if(loopDebug) {
			System.out.println();
			System.out.println("debug end turn start");
		}
		// Reset imp tunnels after 5 days if DS is defeated:
		if(Main.game.getPlayer().hasQuest(QuestLine.MAIN) && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_B_SIRENS_CALL)) {
			boolean alphaReset = this.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated) && ((this.getMinutesPassed() - this.getDialogueFlags().impFortressAlphaDefeatedTime) > 60*24*5);
//			boolean demonReset = this.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated) && ((this.getMinutesPassed() - this.getDialogueFlags().impFortressDemonDefeatedTime) > 60*24*5);
			boolean femalesReset = this.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated) && ((this.getMinutesPassed() - this.getDialogueFlags().impFortressFemalesDefeatedTime) > 60*24*5);
			boolean malesReset = this.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated) && ((this.getMinutesPassed() - this.getDialogueFlags().impFortressMalesDefeatedTime) > 60*24*5);
			
			if(alphaReset && Main.game.getPlayer().getWorldLocation()!=WorldType.IMP_FORTRESS_ALPHA) {
				ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_ALPHA);
			}
			if(femalesReset && Main.game.getPlayer().getWorldLocation()!=WorldType.IMP_FORTRESS_FEMALES) {
				ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_FEMALES);
			}
			if(malesReset && Main.game.getPlayer().getWorldLocation()!=WorldType.IMP_FORTRESS_MALES) {
				ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_MALES);
			}
//			if(demonReset && Main.game.getPlayer().getWorldLocation()!=WorldType.IMP_FORTRESS_DEMON) {
//				ImpCitadelDialogue.resetFortress();
//			}
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
		int hoursPassed = (int) (getHour() - startHour);
		int hourStartTo24 = (int) (startHour%24);
		boolean slavesUpdated = hoursPassed>0;
		if(slavesUpdated) {
			for(int i=1; i <= hoursPassed; i++) {
				occupancyUtil.performHourlyUpdate(this.getDayNumber((startHour*60*60) + (i*60)), (hourStartTo24+i)%24);
				for(String slaveId : Main.game.getPlayer().getSlavesOwned()) { // Update slaves' status effects per hour to give them a chance to refill fluids and such.
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
				System.out.println("starting slaver alley reset");
			}
			
			// Place resets:
			SlaverAlleyDialogue.dailyReset();
			VengarCaptiveDialogue.applyDailyReset();
			getDialogueFlags().dailyReset();
		}
		
		if(pendingSlaveInStocksReset && !Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS)) {
			SlaverAlleyDialogue.stocksReset();
			pendingSlaveInStocksReset = false;
		}
		
		if(loopDebug) {
			System.out.println("Slaver alley end");
		}
		
		handleAtmosphericConditions(secondsPassedThisTurn);

		
		// Apply status effects and update all NPCs:
		isInNPCUpdateLoop = true;
		long tLoopStart = System.nanoTime();
		if(loopDebug) {
			System.out.println("NPC loop start: "+(System.nanoTime()-tLoopStart)/1000000000f+"s");
		}
		for(NPC npc : NPCMap.values()) {
			boolean inGame = !npc.getLocationPlace().getPlaceType().equals(PlaceType.GENERIC_EMPTY_TILE);
			
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
						&& (Main.game.getCurrentDialogueNode().equals(Main.game.getPlayer().getLocationPlace().getDialogue(false)) || !(getCharactersPresent().contains(npc)))) {
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
						|| (npc.isSlave() && npc.getSlavePermissionSettings().get(SlavePermission.PREGNANCY).contains(SlavePermissionSetting.PREGNANCY_PROMISCUITY_PILLS))) {
					npc.useItem(AbstractItemType.generateItem(ItemType.PROMISCUITY_PILL), npc, false);
				}
				
				if(npc.isSlave() && npc.getSlavePermissionSettings().get(SlavePermission.PREGNANCY).contains(SlavePermissionSetting.PREGNANCY_VIXENS_VIRILITY)) {
					npc.useItem(AbstractItemType.generateItem(ItemType.VIXENS_VIRILITY), npc, false);
				}
			}
			
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
					npc.endPregnancy(true);
					
					if(npc.isSlave() && npc.getOwner().isPlayer()) {
						List<String> events = Util.newArrayListOfValues(UtilText.parse(npc, "[npc.She] gave birth to:<br/>")+npc.getLastLitterBirthed().getBirthedDescription());
						SlaveryEventLogEntry entry = new SlaveryEventLogEntry(getHourOfDay(),
								npc,
								SlaveEvent.GAVE_BIRTH,
								null,
								events,
								true);
						Main.game.addSlaveryEvent(getDayNumber(), entry);
					}
					
					if(npc instanceof Kate) {
						Main.game.getDialogueFlags().values.remove(DialogueFlagValue.reactedToKatePregnancy);
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
			
			for(int i=0; i<hoursPassed; i++) {
				npc.hourlyUpdate();
				if(inGame) {
					npc.performHourlyFluidsCheck();
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
						&& !npc.isUnique()
						&& !Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId())
						&& !Main.game.isInCombat()
						&& !Main.game.isInSex()
						&& !npc.isAllowingPlayerToManageInventory()
						&& (Main.game.getCurrentDialogueNode().equals(Main.game.getPlayer().getLocationPlace().getDialogue(false)) || !(getCharactersPresent().contains(npc)))) {
					npc.clearNonEquippedInventory(false);
					CharacterUtils.generateItemsInInventory(npc);
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
			companions = new ArrayList<>(npc.getCompanions());
			for(GameCharacter companion : companions) {
				// Updating companion NPCs:
				companion.companionshipCheck();
			}
			for(GameCharacter character : npc.getCompanions()) {
				character.setLocation(npc.getWorldLocation(), npc.getLocation(), false);
			}
			
			npc.turnUpdate();
		}
		if(loopDebug) {
			System.out.println("NPC loop end: "+(System.nanoTime()-tLoopStart)/1000000000f+"s");
		}
		isInNPCUpdateLoop = false;
		for(NPC npc : npcsToRemove) {
			removeNPC(npc);
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
		
		for(int i=1; i <= hoursPassed; i++) {
			Main.game.getPlayer().performHourlyFluidsCheck();
		}

		if(loopDebug) {
			System.out.println("Fluid checks done: "+(System.nanoTime()-tLoopStart)/1000000000f+"s");
		}
		
//		RenderingEngine.ENGINE.renderButtons();
		MainController.updateUI();

		if(loopDebug) {
			System.out.println("Rendering done: "+(System.nanoTime()-tLoopStart)/1000000000f+"s");
		}
		
		Main.mainController.getTooltip().hide();
		
		if(!Main.game.getPlayer().getStatusEffectDescriptions().isEmpty()
				&& Main.game.getCurrentDialogueNode()!=MiscDialogue.STATUS_EFFECTS
				&& !Main.game.getCurrentDialogueNode().isTravelDisabled()
				&& !Main.game.isInSex()
				&& !Main.game.isInCombat()) {
			
			if(Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.NORMAL) {
				Main.game.saveDialogueNode();
			}
			
			Main.game.setContent(new Response("", "", MiscDialogue.STATUS_EFFECTS){
				@Override
				public void effects() {
					if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().hasNonArcaneEssences()) {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY));
					}
					
					if (!Main.game.getPlayer().hasQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY) && Main.game.getPlayer().isVisiblyPregnant()) {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY));
					}
				}	
			});
			
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
	
	public Weather getCurrentWeather() {
		return currentWeather;
	}
	
	/**
	 * Sets the content of the main WebView based on the response of the current Dialogue Node's index.
	 * 
	 * @param index The dialogue choice index.
	 */
	private int positionAnchor = 0;
	private String dialogueTitle = "";
	public void setContent(int index) {
		informationTooltips = new HashMap<>();
		
		Response response = currentDialogueNode.getResponse(responseTab, index);
		
		if (response != null) {
			String corruptionGains = "";
			
			if(!response.isAvailable()) {
				if(!response.isAbleToBypass()) {
					return;
				} else {
					Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_CORRUPTION, response.getCorruptionNeeded().getCorruptionBypass());
					corruptionGains = ("<p style='text-align:center;'>"
							+ "<b>You have gained +"+response.getCorruptionNeeded().getCorruptionBypass()+"</b> <b style='color:"+Attribute.MAJOR_CORRUPTION.getColour().toWebHexString()+";'>corruption</b><b>!</b>"
							+ "</p>");
				}
			}
			
			String chosenResponse = response.getTitle();
			DialogueNode node = response.getNextDialogue();
			response.applyEffects();
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
					content = node.getContent();
				} catch(Exception ex) {
					content = "<p style='text-align:center;'>"
								+ "[style.italicsBad(Error: getContent() method is throwing an exception in the node: '"+node.getLabel()+"')]"
							+ "</p>";
					ex.printStackTrace();
				}

				if (currentDialogueNode != null) {
					if (node.isContinuesDialogue()) {
						if(Main.game.isInSex()) {
							dialogueTitle = UtilText.parse(node.getLabel());
						}

						if(node.isDisplaysActionTitleOnContinuesDialogue()) {
							if (currentDialogueNode.getDialogueNodeType() == DialogueNodeType.NORMAL) {
								positionAnchor++;
							}
						
							pastDialogueSB.append("<hr id='position" + positionAnchor + "'/><p class='option-disabled' style='padding-bottom:0; margin-bottom:0;'>&gt " + chosenResponse + "</p>");
						}
						
						if (getMapDisplay() == DialogueNodeType.NORMAL)
							initialPositionAnchor = positionAnchor;

						pastDialogueSB.append(
								UtilText.parse(
									corruptionGains 
									+ textStartStringBuilder.toString()
									+ content
									+ textEndStringBuilder.toString()
								));
						
					} else {
						dialogueTitle = UtilText.parse(node.getLabel());
						
						if (getMapDisplay() == DialogueNodeType.NORMAL)
							initialPositionAnchor = positionAnchor;

						if (currentDialogueNode.getDialogueNodeType() == DialogueNodeType.NORMAL)
							positionAnchor = 0;
						
						pastDialogueSB.setLength(0);
						pastDialogueSB.append(
								UtilText.parse(
									corruptionGains
									+ textStartStringBuilder.toString()
									+ content
									+ textEndStringBuilder.toString()
								));
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
					Main.saveGame("AutoSave_"+Main.game.getPlayer().getName(false), true);
					Main.game.setRequestAutosave(false);
				}
				
				
				if (node.isContinuesDialogue()) {
					currentDialogue = 
								"<div id='main-content'>"
									+ getTitleDiv(dialogueTitle)
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
								+ getTitleDiv(dialogueTitle)
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
						(node.isContinuesDialogue() && isContentScroll(node)
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
		response.applyEffects();
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
			content = node.getContent();
		} catch(Exception ex) {
			content = "<p style='text-align:center;'>"
						+ "[style.italicsBad(Error: getContent() method is throwing an exception in the node: '"+node.getLabel()+"')]"
					+ "</p>";
			ex.printStackTrace();
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
		
		if (currentDialogueNode != null) {
			if (node.isContinuesDialogue()) {
				if(Main.game.isInSex()) {
					dialogueTitle = UtilText.parse(node.getLabel());
				}
				
				if(node.isDisplaysActionTitleOnContinuesDialogue()) {
					if (currentDialogueNode.getDialogueNodeType() == DialogueNodeType.NORMAL) {
						positionAnchor++;
					}
					
					pastDialogueSB.append(UtilText.parse("<hr id='position" + positionAnchor + "'><p class='option-disabled'>&gt " + currentDialogueNode.getLabel() + "</p>"));
				}
				
//				pastDialogueSB.append(content);
				pastDialogueSB.append(UtilText.parse(
							textStartStringBuilder.toString()
							+ content
							 + textEndStringBuilder.toString()));
					
			} else {
				dialogueTitle = UtilText.parse(node.getLabel());
				if (currentDialogueNode.getDialogueNodeType() == DialogueNodeType.NORMAL) {
					positionAnchor = 0;
				}
				
				pastDialogueSB.setLength(0);
				pastDialogueSB.append(UtilText.parse(
							"<b id='position" + positionAnchor + "'></b>"
							+ textStartStringBuilder.toString()
							+ content
							 + textEndStringBuilder.toString()));
			}
			
		} else {
			dialogueTitle = UtilText.parse(node.getLabel());
			pastDialogueSB.setLength(0);
			pastDialogueSB.append(UtilText.parse(
						textStartStringBuilder.toString()
						+ content
						 + textEndStringBuilder.toString()));
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
			Main.saveGame("AutoSave_"+Main.game.getPlayer().getName(false), true);
			Main.game.setRequestAutosave(false);
		}


		if (node.isContinuesDialogue()) {
			currentDialogue = "<div id='main-content'>"
						+ getTitleDiv(dialogueTitle)
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
						+ getTitleDiv(dialogueTitle)
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
		setMainContentRegex(node.isContinuesDialogue()
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
	
	public String getContentForClipboard(){
		String content;
		try {
			content = currentDialogueNode.getContent();
		} catch(Exception ex) {
			content = "<p style='text-align:center;'>"
						+ "[style.italicsBad(Error: getContent() method is throwing an exception in the node: '"+currentDialogueNode.getLabel()+"')]"
					+ "</p>";
			ex.printStackTrace();
		}
		
		return "<body style='background:#1e1e20; color:#DDD; font-family:Calibri;'>"
				+ "<style>"
				+ ".speech:before { content: '\"'; }"
				+ ".speech:after { content: '\"'; }"
				+ "</style>"
					+ "<h4 style='text-align:center; font-size:1.4em;'>" + dialogueTitle + "</h4>"
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
		String iconRight="";
		boolean responseDisabled = false;
		
		if(response.disabledOnNullDialogue() && response.getNextDialogue()==null) {
			responseDisabled = true;
			
		} else if (response.isAbleToBypass()) {
			iconLeftBottom = "<div class='response-icon-leftBottom'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseCorruptionBypass()+"</div>";
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

		if(response.isSexActionSwitch()) {
			iconRight = "<div class='response-icon-rightBottom'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseSexSwitch()+"</div>";
			
		} else if(response.getSexActionType()==SexActionType.START_ADDITIONAL_ONGOING) {
			iconRight = "<div class='response-icon-rightBottom'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseSexAdditional()+"</div>";
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
						+ iconRight
					+ "</div>";
			
		} else {
			if(option == 0) {
				return "<div class='response-box"+(responsePointer-((responsePage+1)*MainController.RESPONSE_COUNT)+1==(option)?" selected":"")+"' id='option_" + option + "'>"
						+ "<b class='hotkey-icon'>" + getResponseHotkey(option) + "</b>"
						+ "<p class='response-text"+(responsePointer-((responsePage+1)*MainController.RESPONSE_COUNT)+1==option?" selected":"")+"' "+style+">"
						+ (!responseDisabled ? titleText : UtilText.getDisabledResponse(titleText)) + "</p>"
						+ iconLeftTop
						+ iconLeftBottom
						+ iconRight
					+ "</div>";
			} else {
				return "<div class='response-box"+(responsePointer-(responsePage*MainController.RESPONSE_COUNT)+1==(option)?" selected":"")+"' id='option_" + option + "'>"
						+ "<b class='hotkey-icon'>" + getResponseHotkey(option) + "</b>"
						+ "<p class='response-text"+(responsePointer-(responsePage*MainController.RESPONSE_COUNT)+1==option?" selected":"")+"' "+style+">"
						+ (!responseDisabled ? titleText : UtilText.getDisabledResponse(titleText)) + "</p>"
						+ iconLeftTop
						+ iconLeftBottom
						+ iconRight
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
						+ "timer=false;"
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
				ex.printStackTrace();
			}
			
			currentDialogue = 
					"<div id='main-content'>"
						+ getTitleDiv(dialogueTitle)
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
	
	public List<NPC> getNonCompanionCharactersPresent() {
		List<NPC> nonCompanionCharactersPresent = new ArrayList<>();
		nonCompanionCharactersPresent.addAll(getCharactersPresent());
		nonCompanionCharactersPresent.removeIf((npc) -> Main.game.getPlayer().hasCompanion(npc) || (npc.getPartyLeader()!=null && Main.game.getPlayer().hasCompanion(npc.getPartyLeader())));
		return nonCompanionCharactersPresent;
	}

	public List<NPC> getNonCompanionCharactersPresent(Cell cell) {
		List<NPC> nonCompanionCharactersPresent = new ArrayList<>();
		nonCompanionCharactersPresent.addAll(getCharactersPresent(cell));
		nonCompanionCharactersPresent.removeIf((npc) -> Main.game.getPlayer().hasCompanion(npc) || (npc.getPartyLeader()!=null && Main.game.getPlayer().hasCompanion(npc.getPartyLeader())));
		return nonCompanionCharactersPresent;
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
						?c2.getName(true).compareTo(c1.getName(true))
						:(c2.getLevel()-c1.getLevel()));
		} catch(Exception ex) {
		}
		
		return charactersPresent;
	}
	
	public int getModifierTravelTime(boolean onLand, int time) {
		int maxTime = 0;
		
		for(GameCharacter character : Main.game.getPlayer().getParty()) {
			int speed = onLand
						?character.getLegConfiguration().getLandSpeedModifier()
						:character.getLegConfiguration().getWaterSpeedModifier();
			
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

	public World getActiveWorld() {
		worlds.size();
		player.isFeminine();
		return worlds.get(player.getWorldLocation());
	}

	public Map<AbstractWorldType, World> getWorlds() {
		return worlds;
	}

	/**
//	 * @param world
//	 * @param location Location to set player to
//	 */
	public void setActiveWorld(World world, Vector2i location, boolean setDefaultDialogue) {
//		activeWorld = world;
		player.setLocation(world.getWorldType(), location, false);
		
		if(setDefaultDialogue) {
			DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true);
			Main.game.setContent(new Response("", "", dn));
		}
	}
	
	public void setActiveWorld(World world, AbstractPlaceType placeType, boolean setDefaultDialogue) {
		setActiveWorld(
				world,
				world.getClosestCell(Main.game.getPlayer().getLocation(), placeType).getLocation(),
				setDefaultDialogue);
	}

	public void setPlayer(PlayerCharacter player) {
		this.player = player;
	}

	public PlayerCharacter getPlayer() {
		return player;
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
	
	public LocalDateTime getStartingDate() {
		return startingDate;
	}
	
	public void applyStartingDateChange() {
		startingDate = startingDate.plusYears(TIME_SKIP_YEARS);
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

	public String getDisplayDate(boolean withYear) {
		String date = Units.date(getDateNow(), Units.DateType.LONG);
		
		if(isInNewWorld() && !getDialogueFlags().hasFlag(DialogueFlagValue.knowsDate)) {
			return UtilText.parse("[style.colourMinorBad(Unknown date)]");
		}
		
//		if(withYear) {
//			return Units.date(getDateNow().minusYears(TIME_SKIP_YEARS), Units.DateType.LONG);
//		} else {
//			String date = Units.date(getDateNow().minusYears(TIME_SKIP_YEARS), Units.DateType.LONG);
//			return date.substring(0, date.length()-5);
//		}
		if(withYear) {
			return Units.date(getDateNow(), Units.DateType.LONG);
		} else {
			return date.substring(0, date.length()-5);
		}
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
	
	public boolean isMorning() {
		return getMinutesPassed() % (24 * 60) >= 0 && getMinutesPassed() % (24 * 60) < (60 * 12);
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

	public List<NPC> getOffspring(boolean includeNotBorn) {
		List<NPC> offspring = new ArrayList<>();
		
		for(NPC npc : NPCMap.values()) {
			if((npc.getMother()!=null && npc.getMother().isPlayer()) || (npc.getFather()!=null && npc.getFather().isPlayer())) {
				if(npc.getMother()!=null) {
					if(includeNotBorn || npc.getMother().getPregnantLitter()==null || !npc.getMother().getPregnantLitter().getOffspring().contains(npc.getId())) {
						offspring.add(npc);
					}
				} else {
					offspring.add(npc);
				}
			}
		}
		
		return offspring;
	}
	
	public List<NPC> getOffspringSpawned() {
		List<NPC> offspringSpawned = new ArrayList<>(getOffspring(false));
		
		offspringSpawned.removeIf(npc -> npc.getWorldLocation()==WorldType.EMPTY);
		
		return offspringSpawned;
	}

	public List<NPC> getOffspringNotSpawned(Predicate<NPC> matcher) {
		List<NPC> offspringAvailable = Main.game.getOffspring(false).stream().filter(npc -> !npc.isSlave())
										.filter(npc -> npc.getWorldLocation()==WorldType.EMPTY)
										.filter(npc -> npc.getLastTimeEncountered()==NPC.DEFAULT_TIME_START_VALUE)
										.filter(matcher).collect(Collectors.toList());
		return offspringAvailable;
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
				npc.setId(id+","+(npc.getClass().getSimpleName()));
			}
		}
		
		if(NPCMap.keySet().contains(npc.getId())) {
			throw new Exception("NPC map already contained an NPC with this Id ("+npc.getId()+"). SOMETHING HAS GONE HORRIBLY WRONG! PANIC!");
		}
		
		if(isInNPCUpdateLoop) {
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
		npc.deleteAllEquippedClothing(true); // To cut down on save size and return unique items to the player.
		
		if(npc.isSlave()) {
			npc.getOwner().removeSlave(npc);
		}
		if(npc.hasSlaves()) {
			for(GameCharacter c : new ArrayList<>(npc.getSlavesOwnedAsCharacters())) {
				npc.removeSlave(c);
			}
		}
		
		// TODO Actually delete them
		if(Main.game.getPlayer().hasSexCountWith(npc)
				|| npc.getPregnantLitter()!=null
				|| npc.getLastLitterBirthed()!=null
				|| npc.getMother()!=null
				|| npc.getFather()!=null
				|| npc.isUnique()) {
			npc.setLocation(WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE, true);
			return false;
			
		} else {
			removeNPC(npc);
			return true;
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

	public void removeNPC(String id) {
		try {
			removeNPC((NPC)Main.game.getNPCById(id));
		} catch (Exception e) {
			System.err.println("Trying to remove an NPC that doesn't exist?");
			e.printStackTrace();
		}
	}
	
	public void removeNPC(NPC npc) {
		Main.game.getPlayer().removeCompanion(npc);
		
		npc.resetFluidsStored();
		if(npc.isPregnant()) {
			npc.endPregnancy(false);
			
		} else if(npc.hasStatusEffect(StatusEffect.PREGNANT_0)) {
			npc.removeStatusEffect(StatusEffect.PREGNANT_0);
		}
		
		if(isInNPCUpdateLoop) {
			npcsToRemove.add(npc);
			
		} else {
			npc.getCell().removeCharacterPresentId(npc.getId());
			npc.getHomeCell().removeCharacterHomeId(npc.getId());
			NPCMap.remove(npc.getId());
		}
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
	
	public boolean isStarted() {
		return started;
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
	
	public void appendToTextEndStringBuilder(GameCharacter npc, String text) {
		textEndStringBuilder.append(UtilText.parse(npc, text));
	}
	
	public void appendToTextEndStringBuilder(List<GameCharacter> npcs, String text) {
		textEndStringBuilder.append(UtilText.parse(npcs, text));
	}

	public void clearTextEndStringBuilder() {
		textEndStringBuilder.setLength(0);
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
		return Main.game.getSavedDialogueNode().equals(Main.game.getPlayer().getLocationPlace().getDialogue(false));
	}
	
	public Cell getPlayerCell() {
		return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation());
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
	
	public boolean isSillyModeEnabled() {
		return Main.getProperties().hasValue(PropertyValue.sillyMode);
	}
	
	public boolean isNonConEnabled() {
		return Main.getProperties().hasValue(PropertyValue.nonConContent);
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

	public boolean isAnalContentEnabled() {
		return Main.getProperties().hasValue(PropertyValue.analContent);
	}

	public boolean isGapeContentEnabled() {
		return Main.getProperties().hasValue(PropertyValue.gapeContent);
	}

	public boolean isPenetrationLimitationsEnabled() {
		return Main.getProperties().hasValue(PropertyValue.penetrationLimitations);
	}
	
	public boolean isFootContentEnabled() {
		return Main.getProperties().hasValue(PropertyValue.footContent);
	}

	public boolean isLactationContentEnabled() {
		return Main.getProperties().hasValue(PropertyValue.lactationContent);
	}

	public boolean isCumRegenerationEnabled() {
		return Main.getProperties().hasValue(PropertyValue.cumRegenerationContent);
	}

	public boolean isBadEndsEnabled() {
		return Main.getProperties().hasValue(PropertyValue.badEndContent);
	}
	
	public boolean isCompanionContentEnabled() {
		return Main.getProperties().hasValue(PropertyValue.companionContent);
	}
	
	public boolean isCrotchBoobContentEnabled() {
		return Main.getProperties().udders>0;
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

	public List<EventLogEntry> getEventLog() {
		return eventLog;
	}
	
	public void addEvent(EventLogEntry event, boolean appendAdditionTextToMainDialogue) {
		eventLog.add(event);
		if(appendAdditionTextToMainDialogue) {
			Main.game.getTextEndStringBuilder().append(event.getMainDialogueDescription());
		}
	}
	
	public void setEventLog(List<EventLogEntry> eventLog) {
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
	 *  Be careful using this, as it has a chance to trigger the tile's random encounter.
	 */
	public DialogueNode getDefaultDialogue() {
		return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true);
	}
	
	public DialogueNode getDefaultDialogue(boolean withRandomEncounter) {
		return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(withRandomEncounter);
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
	
	public boolean isBypassSexActions() {
		return Main.getProperties().hasValue(PropertyValue.bypassSexActions);
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
}
