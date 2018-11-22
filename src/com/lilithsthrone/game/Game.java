package com.lilithsthrone.game;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.controller.TooltipUpdateThread;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.*;
import com.lilithsthrone.game.character.npc.misc.*;
import com.lilithsthrone.game.character.npc.submission.*;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.*;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntry;
import com.lilithsthrone.game.dialogue.eventLog.SlaveryEventLogEntry;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaverAlleyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloor;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpFortressDialogue;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpCitadelDialogue;
import com.lilithsthrone.game.dialogue.responses.*;
import com.lilithsthrone.game.dialogue.utils.*;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.game.occupantManagement.OccupancyUtil;
import com.lilithsthrone.game.occupantManagement.SlavePermission;
import com.lilithsthrone.game.occupantManagement.SlavePermissionSetting;
import com.lilithsthrone.game.settings.KeyCodeWithModifiers;
import com.lilithsthrone.game.settings.KeyboardAction;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.*;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.Generation;
import com.lilithsthrone.world.World;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.Serializable;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @since 0.1.0
 * @version 0.2.12
 * @author Innoxia, AlacoGit
 */
public class Game implements Serializable, XMLSaving {
	private static final long serialVersionUID = 1L;

	public static final int FONT_SIZE_MINIMUM = 12;
	public static final int FONT_SIZE_NORMAL = 18;
	public static final int FONT_SIZE_LARGE = 24;
	public static final int FONT_SIZE_HUGE = 36;
	
	public static final int TIME_SKIP_YEARS = 3;
	
	public static String loadingVersion = Main.VERSION_NUMBER;
	
	private PlayerCharacter player;
	
	// NPCs:
	private NPC activeNPC;
	private AtomicInteger npcTally = new AtomicInteger(0);

	//Note : this is a ConcurrentHashMap
	private Map<String, NPC> NPCMap;
	
	private Map<WorldType, World> worlds;
	private long minutesPassed;
	private LocalDateTime startingDate;
	
	private boolean renderAttributesSection;
	private boolean renderMap;
	private boolean inCombat;
	private boolean inSex;
	private boolean requestAutosave;
	
	private Weather currentWeather;
	private long nextStormTime;
	private int gatheringStormDuration;
	private int weatherTimeRemaining;
	
	private Encounter currentEncounter;
	
	private boolean hintsOn, started, prologueFinished;
	
	private DialogueFlags dialogueFlags;
	
	// Responses:
	private int responsePointer = 0;
	
	// Dialogues:
	private DialogueNodeOld currentDialogueNode;
	private DialogueNodeOld savedDialogueNode = null;
	private String currentDialogue, savedDialogue, previousPastDialogueSBContents = "";
	private int initialPositionAnchor = 0, responsePage = 0, responseTab = 0;
	private StringBuilder pastDialogueSB = new StringBuilder(), choicesDialogueSB = new StringBuilder();
	private StringBuilder textEndStringBuilder = new StringBuilder(), textStartStringBuilder = new StringBuilder();
	
	// Logs:
	private List<EventLogEntry> eventLog = new ArrayList<>();
	private Map<Integer, List<SlaveryEventLogEntry>> slaveryEventLog = new HashMap<>();
	
	// Slavery:
	private OccupancyUtil occupancyUtil = new OccupancyUtil();

	public Game() {
		worlds = new EnumMap<>(WorldType.class);
		for (WorldType type : WorldType.values()) {
			worlds.put(type, null);
		}
		OccupantManagementDialogue.resetImportantCells();
		startingDate = LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(), 00, 00).plusYears(TIME_SKIP_YEARS);
		minutesPassed = 20 * 60;
		inCombat = false;
		inSex = false;
		renderAttributesSection = false;
		renderMap = false;

		dialogueFlags = new DialogueFlags();

		hintsOn = false;
		started = false;
		prologueFinished = true;

		NPCMap = new ConcurrentHashMap<>();

		// Start in clouds:
		currentWeather = Weather.CLOUD;
		weatherTimeRemaining = 300;
		nextStormTime = minutesPassed + (60*48) + (60*Util.random.nextInt(24)); // Next storm in 2-3 days
		
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
			// If player, modify birth date so that imported characters are the same age:
			if(character.isPlayer()) {
				character.setBirthday(character.getBirthday().plusYears(Game.TIME_SKIP_YEARS));
				character.saveAsXML(characterNode, doc);
				character.setBirthday(character.getBirthday().minusYears(Game.TIME_SKIP_YEARS));
			} else {
				character.saveAsXML(characterNode, doc);
			}
			
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
			String saveLocation = "data/characters/exported_"+character.getName()+"_day"+Main.game.getDayNumber()+".xml";
			if(new File("data/characters/exported_"+character.getName()+"_day"+Main.game.getDayNumber()+".xml").exists()) {
				saveLocation = "data/characters/exported_"+character.getName()+"_day"+Main.game.getDayNumber()+"("+saveNumber+").xml";
			}
			
			while(new File("data/characters/exported_"+character.getName()+"_day"+Main.game.getDayNumber()+"("+saveNumber+").xml").exists()) {
				saveNumber++;
				saveLocation = "data/characters/exported_"+character.getName()+"_day"+Main.game.getDayNumber()+"("+saveNumber+").xml";
			}
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
				importedSlave.loadFromXML(characterElement, doc, CharacterImportSetting.NO_PREGNANCY, CharacterImportSetting.NO_COMPANIONS, CharacterImportSetting.NO_ELEMENTAL, CharacterImportSetting.CLEAR_SLAVERY);
				importedSlave.applyNewlyImportedSlaveVariables();
				Main.game.addNPC(importedSlave, false);
				
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
							Main.game.flashMessage(Colour.GENERIC_BAD, "Name already exists!");
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
				CharacterUtils.addAttribute(doc, informationNode, "minutesPassed", String.valueOf(Main.game.minutesPassed));
				CharacterUtils.addAttribute(doc, informationNode, "weather", Main.game.currentWeather.toString());
				CharacterUtils.addAttribute(doc, informationNode, "nextStormTime", String.valueOf(Main.game.nextStormTime));
				CharacterUtils.addAttribute(doc, informationNode, "gatheringStormDuration", String.valueOf(Main.game.gatheringStormDuration));
				CharacterUtils.addAttribute(doc, informationNode, "weatherTimeRemaining", String.valueOf(Main.game.weatherTimeRemaining));
	
				try {
					Main.game.getOccupancyUtil().saveAsXML(game, doc);
				}catch(Exception ex) {
					System.err.println("SlaveryUtil saving failed!");
					Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "<style='color:"+Colour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail<b>", "SlaveryUtil failure"), false);
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
				Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "<style='color:"+Colour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail<b>", "coreInfo failure"), false);
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
				Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "<style='color:"+Colour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail<b>", "eventLog failure"), false);
			}
		
			try {
				Element slaveryEventLogNode = doc.createElement("slaveryEventLog");
				game.appendChild(slaveryEventLogNode);
				for(int day = Main.game.getDayNumber() ; day>Main.game.getDayNumber()-28 ; day--) {
					if(Main.game.getSlaveryEventLog().containsKey(day)) {
						Element element = doc.createElement("day");
						slaveryEventLogNode.appendChild(element);
						CharacterUtils.addAttribute(doc, element, "value", String.valueOf(day));
						for(SlaveryEventLogEntry event : Main.game.getSlaveryEventLog().get(day)) {
							event.saveAsXML(element, doc);
						}
					}
				}
			} catch(Exception ex) {
				System.err.println("slaveryEventLog saving failed!");
				Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "<style='color:"+Colour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail<b>", "slaveryEventLog failure"), false);
			}
			
			// Add maps:
			try {
				Element mapNode = doc.createElement("maps");
				game.appendChild(mapNode);
				for(World world : Main.game.getWorlds().values()) {
					if(world!=null) {
						world.saveAsXML(mapNode, doc);
					}
				}
			} catch(Exception ex) {
				System.err.println("maps saving failed!");
				Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "<style='color:"+Colour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail<b>", "maps failure"), false);
			}
			
			// Add player:
			try {
				Element characterNode = doc.createElement("playerCharacter");
				game.appendChild(characterNode);
				Main.game.getPlayer().saveAsXML(characterNode, doc);
			} catch(Exception ex) {
				System.err.println("playerCharacter saving failed!");
				Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "<style='color:"+Colour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail<b>", "playerCharacter failure"), false);
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
				Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "<style='color:"+Colour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail<b>", "NPC failure"), false);
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
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()), false, Colour.GENERIC_GOOD, "Save game overwritten!");
					} else {
						Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "[style.colourGood(Game saved)]", saveLocation), false);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()), false, Colour.GENERIC_GOOD, "Game saved!");
					}
				}
			} catch(Exception ex) {
				System.err.println("XML writing failed!");
				Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "<style='color:"+Colour.GENERIC_TERRIBLE.toWebHexString()+";'>Partial Save Fail<b>", "XML writing failure"), false);
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
		Main.game = new Game();

		File file = new File("data/saves/"+name+".xml");
		
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
				
				Main.game.minutesPassed = Long.valueOf(informationNode.getAttribute("minutesPassed"));
				Main.game.currentWeather = Weather.valueOf(informationNode.getAttribute("weather"));
				Main.game.nextStormTime = Long.valueOf(informationNode.getAttribute("nextStormTime"));
				try {
					Main.game.gatheringStormDuration = Integer.valueOf(informationNode.getAttribute("gatheringStormDuration"));
				} catch(Exception ex) {
				}
				Main.game.weatherTimeRemaining = Integer.valueOf(informationNode.getAttribute("weatherTimeRemaining"));

				try {
					Element slaveryNode = (Element) gameElement.getElementsByTagName("slavery").item(0);
					OccupancyUtil occupancyUtil = OccupancyUtil.loadFromXML(slaveryNode, doc);
					if(occupancyUtil!=null) {
						Main.game.occupancyUtil = occupancyUtil;
					}
				} catch(Exception ex) {
				}
				
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
				Main.game.eventLog.sort(Comparator.comparingLong(EventLogEntry::getTime));
				
				
				NodeList nodes = gameElement.getElementsByTagName("slaveryEventLog");
				Element extraEffectNode = (Element) nodes.item(0);
				if(extraEffectNode != null) {
					NodeList slaveryDayLogElements = extraEffectNode.getElementsByTagName("day");
					for(int i = 0; i < slaveryDayLogElements.getLength(); i++){
						Element e = (Element) gameElement.getElementsByTagName("day").item(i);
						int day = Integer.valueOf(e.getAttribute("value"));
						Main.game.slaveryEventLog.put(day, new ArrayList<>());
						
						NodeList dayEventLogElements = e.getElementsByTagName("eventLogEntry");
						for(int j = 0; j < dayEventLogElements.getLength(); j++){
							Element entry = (Element) dayEventLogElements.item(j);
							Main.game.slaveryEventLog.get(day).add(SlaveryEventLogEntry.loadFromXML(entry, doc));
						}
					}
				}
				
				if(debug) {
					System.out.println("Core info finished");
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
							&& (!worldType.equals("IMP_FORTRESS_DEMON")
									|| !Main.isVersionOlderThan(loadingVersion, "0.2.12"))
							&& (!worldType.equals("DOMINION") || !Main.isVersionOlderThan(loadingVersion, "0.2.2"))
							&& (!worldType.equals("SLAVER_ALLEY") || !Main.isVersionOlderThan(loadingVersion, "0.2.2"))
							&& (!worldType.equals("HARPY_NEST") || !Main.isVersionOlderThan(loadingVersion, "0.2.1.5"))
							&& (!worldType.equals("BAT_CAVERNS") || !Main.isVersionOlderThan(loadingVersion, "0.2.3.5"))
							&& (!worldType.equals("SLAVER_ALLEY") || !Main.isVersionOlderThan(loadingVersion, "0.2.10"))) {
						World world = World.loadFromXML(e, doc);
						Main.game.worlds.put(world.getWorldType(), world);
					}
				}
				
				// Add missing world types:
				for(WorldType wt : WorldType.values()) {
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
					if(Main.isVersionOlderThan(loadingVersion, "0.2.12")) {
						gen.worldGeneration(WorldType.IMP_FORTRESS_DEMON);
					}
					if(Main.isVersionOlderThan(loadingVersion, "0.2.2")) {
						gen.worldGeneration(WorldType.DOMINION);
						gen.worldGeneration(WorldType.SLAVER_ALLEY);
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
					if(Main.isVersionOlderThan(loadingVersion, "0.2.10")) {
						gen.worldGeneration(WorldType.SLAVER_ALLEY);
					}
					if(Main.game.worlds.get(wt)==null) {
						gen.worldGeneration(wt);
					}
				}

				if(Main.isVersionOlderThan(loadingVersion, "0.2.4")) {
					AbstractItem spellBook = AbstractItemType.generateItem(ItemType.getSpellBookType(Spell.ICE_SHARD));
					Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCell(PlaceType.LILAYA_HOME_ROOM_PLAYER).getInventory().addItem(spellBook);
				}
				
				if(debug) {
					System.out.println("Maps finished");
				}
				
				Main.game.player = PlayerCharacter.loadFromXML(null, (Element) ((Element) gameElement.getElementsByTagName("playerCharacter").item(0)), doc);

				if(debug) {
					System.out.println("Player finished");
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

								NPC npc = loadNPC(doc, e, className, npcClasses, loadFromXMLMethods, constructors);
								if(npc!=null)  {
									if(!Main.isVersionOlderThan(loadingVersion, "0.2.11.5")
											|| (npc.getClass()!=FortressDemonLeader.class
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

								} else {
									System.err.println("LOADNPC returned null: "+id);
									System.err.println("CLASS: " + className);
								}
							} else {
								System.err.println("duplicate character attempted to be imported");
							}
						});
				if(debug) {
					System.out.println("NPCs finished");
				}

				
				// Add in new NPCS:
				
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Scarlett.class))) {
					Main.game.addNPC(new Scarlett(), false);
					if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE)) {
						Main.game.getScarlett().setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_ALEXAS_NEST);
					}
				}
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Rose.class))) {
					Main.game.addNPC(new Rose(), false);
				}
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Kate.class))) {
					Main.game.addNPC(new Kate(), false);
				}
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Vicky.class))) {
					Main.game.addNPC(new Vicky(), false);
				}
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Pix.class))) {
					Main.game.addNPC(new Pix(), false);
				}
				
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Arthur.class))) {
					Main.game.addNPC(new Arthur(), false);
				}
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Amber.class))) {
					Main.game.addNPC(new Amber(), false);
				}
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(ZaranixMaidKatherine.class))) {
					Main.game.addNPC(new ZaranixMaidKatherine(), false);
				}
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(ZaranixMaidKelly.class))) {
					Main.game.addNPC(new ZaranixMaidKelly(), false);
				}
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Zaranix.class))) {
					Main.game.addNPC(new Zaranix(), false);
					
					NPC zaranix = Main.game.getZaranix();
					NPC amber = Main.game.getAmber();
					NPC kelly = Main.game.getKelly();
					NPC katherine = Main.game.getKatherine();
					
					zaranix.setAffection(katherine, AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
					zaranix.setAffection(kelly, AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
					zaranix.setAffection(amber, AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());

					amber.setAffection(zaranix, AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
					amber.setAffection(kelly, AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
					amber.setAffection(katherine, AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());

					kelly.setAffection(zaranix, AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
					kelly.setAffection(katherine, AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
					kelly.setAffection(amber, AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());

					katherine.setAffection(zaranix, AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
					katherine.setAffection(kelly, AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
					katherine.setAffection(amber, AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
				}
				
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Ashley.class))) {
					Main.game.addNPC(new Ashley(), false);
				}
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(SupplierLeader.class))) {
					Main.game.addNPC(new SupplierLeader(), false);
				}
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(SupplierPartner.class))) {
					Main.game.addNPC(new SupplierPartner(), false);
				}
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Angel.class))) {
					Main.game.addNPC(new Angel(), false);
				}
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Bunny.class))) {
					Main.game.addNPC(new Bunny(), false);
				}
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Loppy.class))) {
					Main.game.addNPC(new Loppy(), false);
				}
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Lumi.class))) {
					Main.game.addNPC(new Lumi(), false);
				}
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Claire.class))) {
					Main.game.addNPC(new Claire(), false);
				}
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(SlimeQueen.class))) { // Add slime queen quest NPCs:
					Main.game.addNPC(new SlimeQueen(), false);
					Main.game.addNPC(new SlimeGuardIce(), false);
					Main.game.addNPC(new SlimeGuardFire(), false);
					Main.game.addNPC(new SlimeRoyalGuard(), false);
				}
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Roxy.class))) { // Add gambling den NPCs:
					Main.game.addNPC(new Roxy(), false);
				}
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Axel.class))) {
					Main.game.addNPC(new Axel(), false);
				}
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Epona.class))) {
					Main.game.addNPC(new Epona(), false);
				}
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Jules.class))) { // Add nightclub NPCs:
					Main.game.addNPC(new Jules(), false);
				}
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Kruger.class))) {
					Main.game.addNPC(new Kruger(), false);
				}
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Kalahari.class))) {
					Main.game.addNPC(new Kalahari(), false);
					Main.game.getKalahari().setFather(Main.game.getKruger());
					Main.game.getKalahari().setAffection(Main.game.getKruger(), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
					Main.game.getKruger().setAffection(Main.game.getKalahari(), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
				}

				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(FortressDemonLeader.class))) {
					Main.game.addNPC(new FortressAlphaLeader(), false);
					Main.game.addNPC(new FortressDemonLeader(), false);
					Main.game.addNPC(new FortressFemalesLeader(), false);
					Main.game.addNPC(new FortressMalesLeader(), false);
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.2.8")) {
					Main.game.getJules().setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_ENTRANCE);
					Main.game.getKruger().setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_VIP_AREA);
					Main.game.getKalahari().setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_BAR);
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
						
						Main.game.getArthur().setLocation(WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_OFFICE, true);
						
						if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
							Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE);
						}
					}
				}
				
				if(Main.isVersionOlderThan(loadingVersion, "0.1.95")) {
					if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
						Main.game.getArthur().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
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
						npc.setSkinCovering(new Covering(npc.getSkinType().getBodyCoveringType(npc), npc.getCovering(npc.getSkinType().getBodyCoveringType(npc)).getPrimaryColour()), true);
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
				
				if(Main.isVersionOlderThan(loadingVersion, "0.2.11.9")) { //Reset imp fortresses
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
					
					while(Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY)) {
						Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY));
					}
				}
				if(Main.isVersionOlderThan(loadingVersion, "0.2.12.1")) {
					ImpCitadelDialogue.resetFortress();
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
		
		if(Main.game.getGenericAndrogynousNPC()==null) { // If was accidentally deleted in version 0.2.10:
			try {
				Main.game.addNPC(new GenericAndrogynousNPC(), false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		Main.game.setRenderMap(true);
		Main.game.setRenderAttributesSection(true);
		
		Main.game.started = true;
		
		Main.game.setRequestAutosave(false);
		
		DialogueNodeOld startingDialogueNode = Main.game.getPlayerCell().getPlace().getDialogue(false);
		Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "[style.colourGood(Game loaded)]", "data/saves/"+name+".xml"), false);
		Main.game.setContent(new Response(startingDialogueNode.getLabel(), startingDialogueNode.getDescription(), startingDialogueNode), false);
		
		Main.game.endTurn(0);
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
	
	public void initNewGame(DialogueNodeOld startingDialogueNode) {
		// Set up NPCs:
		try {
			NPCMap.clear();
			
			addNPC(new GenericMaleNPC(), false);
			addNPC(new GenericFemaleNPC(), false);
			addNPC(new GenericAndrogynousNPC(), false);
			
			addNPC(new PrologueMale(), false);
			
			addNPC(new PrologueFemale(), false);
			
			addNPC(new TestNPC(), false);

			// Story:
			
			Lilaya lilaya = new Lilaya();
			addNPC(lilaya, false);
			lilaya.setAffection(Main.game.getPlayer(), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
			
			Rose rose = new Rose();
			addNPC(rose, false);
			rose.setAffection(Main.game.getPlayer(), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
			lilaya.addSlave(rose);
			rose.setObedience(ObedienceLevel.POSITIVE_FIVE_SUBSERVIENT.getMedianValue());
			lilaya.setAffection(rose, AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
			rose.setAffection(lilaya, AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
			
			Brax brax = new Brax();
			addNPC(brax, false);
	
			CandiReceptionist candiReceptionist = new CandiReceptionist();
			addNPC(candiReceptionist, false);
	
			brax.setAffection(candiReceptionist, AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
			candiReceptionist.setAffection(brax, AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());

			// Shopping Promenade:
			
			addNPC(new Ralph(), false);
			
			addNPC(new Nyan(), false);
			
			addNPC(new Vicky(), false);
			
			addNPC(new Pix(), false);
			
			addNPC(new Kate(), false);

			// Harpy nests:
			
			Scarlett scarlett = new Scarlett();
			addNPC(scarlett, false);
			
			Alexa alexa = new Alexa();
			addNPC(alexa, false);
			
			alexa.setAffection(scarlett, AffectionLevel.NEGATIVE_FOUR_HATE.getMedianValue());
			scarlett.setAffection(alexa, AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
			scarlett.setAffection(Main.game.getPlayer(), AffectionLevel.NEGATIVE_TWO_DISLIKE.getMedianValue());
			
			HarpyBimbo harpyBimbo = new HarpyBimbo();
			addNPC(harpyBimbo, false);
			
			HarpyBimboCompanion harpyBimboCompanion = new HarpyBimboCompanion();
			addNPC(harpyBimboCompanion, false);
	
			harpyBimbo.setAffection(harpyBimboCompanion, AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
			harpyBimboCompanion.setAffection(harpyBimbo, AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
			
			HarpyDominant harpyDominant = new HarpyDominant();
			addNPC(harpyDominant, false);
	
			HarpyDominantCompanion harpyDominantCompanion = new HarpyDominantCompanion();
			addNPC(harpyDominantCompanion, false);
	
			harpyDominant.setAffection(harpyDominantCompanion, AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
			harpyDominantCompanion.setAffection(harpyDominant, AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
			
			HarpyNympho harpyNympho = new HarpyNympho();
			addNPC(harpyNympho, false);
	
			HarpyNymphoCompanion harpyNymphoCompanion = new HarpyNymphoCompanion();
			addNPC(harpyNymphoCompanion, false);
	
			harpyNympho.setAffection(harpyNymphoCompanion, AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
			harpyNymphoCompanion.setAffection(harpyNympho, AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
			
			addNPC(new Pazu(), false);
			
			addNPC(new Finch(), false);
			
			// Zaranix:
			Zaranix zaranix = new Zaranix();
			addNPC(zaranix, false);
			
			ZaranixMaidKatherine katherine = new ZaranixMaidKatherine();
			addNPC(katherine, false);
			
			ZaranixMaidKelly kelly = new ZaranixMaidKelly();
			addNPC(kelly, false);
			
			Amber amber = new Amber();
			addNPC(amber, false);
			
			
			zaranix.setAffection(katherine, AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
			zaranix.setAffection(kelly, AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
			zaranix.setAffection(amber, AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());

			amber.setAffection(zaranix, AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
			amber.setAffection(kelly, AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
			amber.setAffection(katherine, AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());

			kelly.setAffection(zaranix, AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
			kelly.setAffection(katherine, AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
			kelly.setAffection(amber, AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());

			katherine.setAffection(zaranix, AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
			katherine.setAffection(kelly, AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
			katherine.setAffection(amber, AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
			
			addNPC(new Arthur(), false);

			addNPC(new Ashley(), false);
			addNPC(new SupplierLeader(), false);
			addNPC(new SupplierPartner(), false);

			addNPC(new Angel(), false);
			addNPC(new Bunny(), false);
			addNPC(new Loppy(), false);
			
			addNPC(new Lumi(), false);
			addNPC(new Claire(), false);

			addNPC(new SlimeQueen(), false);
			addNPC(new SlimeGuardIce(), false);
			addNPC(new SlimeGuardFire(), false);
			addNPC(new SlimeRoyalGuard(), false);
			
			addNPC(new Roxy(), false);
			addNPC(new Axel(), false);
			addNPC(new Epona(), false);

			addNPC(new Jules(), false);
			addNPC(new Kruger(), false);
			addNPC(new Kalahari(), false);
			Main.game.getKalahari().setFather(Main.game.getKruger());
			Main.game.getKalahari().setAffection(Main.game.getKruger(), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
			Main.game.getKruger().setAffection(Main.game.getKalahari(), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());

			addNPC(new FortressAlphaLeader(), false);
			addNPC(new FortressDemonLeader(), false);
			addNPC(new FortressFemalesLeader(), false);
			addNPC(new FortressMalesLeader(), false);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// This is due to the fact that on new world creation, the player is placed at coordinates (0, 0), which reveals the three squares at the bottom left corner of the map:
		Main.game.getActiveWorld().getCell(0, 0).setDiscovered(false);
		Main.game.getActiveWorld().getCell(0, 1).setDiscovered(false);
		Main.game.getActiveWorld().getCell(1, 0).setDiscovered(false);
		
		started = true;
		
		SlaverAlleyDialogue.dailyReset();

		setContent(new Response(startingDialogueNode.getLabel(), startingDialogueNode.getDescription(), startingDialogueNode));
	}

	// Main updating for game mechanics, as everything is based on turns.
	public void endTurn(int turnTime) {
		endTurn(turnTime, true);
	}
	
	private boolean isInNPCUpdateLoop = false;
	public boolean pendingSlaveInStocksReset = true;
	private List<NPC> npcsToRemove = new ArrayList<>();
	private List<NPC> npcsToAdd = new ArrayList<>();
	
	public void endTurn(int turnTime, boolean advanceTime) {
		
//		long tStart = System.nanoTime();
		
		long startHour = getHour();
		
		if(advanceTime) {
			minutesPassed += turnTime;
			updateResponses();
		}
		
		// Reset imp tunnels after 5 days if DS is defeated:
		if(Main.game.getPlayer().hasQuest(QuestLine.MAIN) && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_B_SIRENS_CALL)) {
			boolean alphaReset = this.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated) && ((this.getMinutesPassed() - this.getDialogueFlags().impFortressAlphaDefeatedTime) > 60*24*5);
			boolean demonReset = this.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated) && ((this.getMinutesPassed() - this.getDialogueFlags().impFortressDemonDefeatedTime) > 60*24*5);
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
			if(demonReset && Main.game.getPlayer().getWorldLocation()!=WorldType.IMP_FORTRESS_DEMON) {
				ImpCitadelDialogue.resetFortress();
			}
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
		for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
			character.setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
		}
		
		// Occupancy:
		int hoursPassed = (int) (getHour() - startHour);
		int hourStartTo24 = (int) (startHour%24);
		for(int i=1; i <= hoursPassed; i++) {
			occupancyUtil.performHourlyUpdate(this.getDayNumber(startHour*60 + i*60), (hourStartTo24+i)%24);
		}
		
		// If the time has passed midnight on this turn:
		boolean newDay = getDayNumber(minutesPassed) != getDayNumber(minutesPassed - turnTime);
		
		if(newDay) {
			pendingSlaveInStocksReset = true;
			Main.game.getPlayer().resetDaysOrgasmCount();
			
			for(String id : Main.game.getPlayer().getFriendlyOccupants()) {
				try {
					NPC occupant = (NPC) Main.game.getNPCById(id);
					Main.game.getOccupancyUtil().dailyOccupantUpdate(occupant);
				} catch(Exception e) {
					System.err.println("Main.game.getNPCById("+id+") returning null in method: endTurn()");
				}
			}
			
			// Slaver alley reset:
			SlaverAlleyDialogue.dailyReset();
		}
		
		if(pendingSlaveInStocksReset && Main.game.getPlayer().getLocationPlace().getPlaceType()!=PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS) {
			List<NPC> npcsToBanish = new ArrayList<>();
			for(NPC npc : Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS))) {
				if(npc instanceof SlaveInStocks) {
					npcsToBanish.add(npc);
				}
			}
			for(NPC npc : npcsToBanish) {
				Main.game.banishNPC(npc);
			}
			
			for(int i=0; i<4; i++) {
				SlaveInStocks slave = new SlaveInStocks(Gender.getGenderFromUserPreferences(false, false));
				if(Math.random()>0.5f) {
					Main.game.getGenericFemaleNPC().addSlave(slave);
				} else {
					Main.game.getGenericMaleNPC().addSlave(slave);	
				}
				try {
					Main.game.addNPC(slave, false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			pendingSlaveInStocksReset = false;
		}
		
		handleAtmosphericConditions(turnTime);

		
		// Apply status effects and update all NPCs:
		isInNPCUpdateLoop = true;

		for(NPC npc : NPCMap.values()) {
			// Remove Dominion attackers if they aren't in alleyways: TODO this is because storm attackers need to be removed after a storm
			if(npc.getLocationPlace().getPlaceType() != PlaceType.DOMINION_BACK_ALLEYS
					&& npc.getLocationPlace().getPlaceType() != PlaceType.DOMINION_CANAL
					&& npc.getLocationPlace().getPlaceType() != PlaceType.DOMINION_CANAL_END
					&& npc.getLocationPlace().getPlaceType() != PlaceType.DOMINION_ALLEYS_CANAL_CROSSING
					&& npc.getWorldLocation() == WorldType.DOMINION
					&& npc instanceof DominionAlleywayAttacker
					&& !npc.isSlave()
					&& !Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId())
					&& !Main.game.getPlayer().getLocation().equals(npc.getLocation())) {
						banishNPC(npc);
					}
			
			// Non-slave NPCs clean clothes:
			if((!npc.isSlave() || (npc.isSlave() && !npc.getOwner().isPlayer())) && !Main.game.getCharactersPresent().contains(npc)) {
				npc.cleanAllClothing();
				npc.cleanAllDirtySlots();
			}
			
			// Set NPC resource values:
			if(!Main.game.isInCombat() && !Main.game.isInSex()) {
				if(!Main.game.getPlayer().getCompanions().contains(npc)) {
					if(!Main.game.getPlayer().getLocation().equals(npc.getLocation())) {
						npc.setHealthPercentage(1);
						npc.setManaPercentage(1);
					}
					npc.alignLustToRestingLust(turnTime*10);
				} else {
					// Regenerate health and stamina over time:
					if (npc.getHealthPercentage() < 1) {
						npc.incrementHealth(turnTime * npc.getRegenerationRate());
					}
					if (npc.getManaPercentage() < 1) {
						npc.incrementMana(turnTime * npc.getRegenerationRate());
					}
					npc.alignLustToRestingLust(turnTime);
				}
			}
			
			npc.calculateStatusEffects(turnTime);
			
			// Replace clothing if not in player's tile:
			if(hoursPassed > 0) {
				if(!Main.game.isInCombat() && !Main.game.isInSex()
						&& (Main.game.getCurrentDialogueNode().equals(Main.game.getPlayer().getLocationPlace().getDialogue(false))
								|| !(npc.getWorldLocation()==Main.game.getPlayer().getWorldLocation() && npc.getLocation().equals(Main.game.getPlayer().getLocation())))) {
					if(npc.isPendingClothingDressing()) {
						if(!npc.hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
							npc.replaceAllClothing();
						}
						npc.equipClothing(true, true, false, false);
						npc.setPendingClothingDressing(false);
						
					} else if(!npc.isSlave() && !npc.isUnique()
							&& (npc.hasStatusEffect(StatusEffect.EXPOSED) || npc.hasStatusEffect(StatusEffect.EXPOSED_BREASTS) || npc.hasStatusEffect(StatusEffect.EXPOSED_PLUS_BREASTS))){
						// Try to replace clothing to cover themselves up:
						if(!npc.hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
							npc.replaceAllClothing();
						}
						npc.calculateStatusEffects(0);
						// If still exposed after this, get new clothes:
						if(npc.hasStatusEffect(StatusEffect.EXPOSED) || npc.hasStatusEffect(StatusEffect.EXPOSED_BREASTS) || npc.hasStatusEffect(StatusEffect.EXPOSED_PLUS_BREASTS)) {
							npc.equipClothing(true, true, false, false);
//							System.out.println(npc.getName()+" "+npc.getClass().getName()+" got dressed");
						}
						npc.setPendingClothingDressing(false);
					}
				}
			}
			
			if(npc.isPendingTransformationToGenderIdentity()) {
				boolean assVirgin = npc.isAssVirgin();
				boolean faceVirgin = npc.isFaceVirgin();
				boolean nippleVirgin = npc.isNippleVirgin();
				boolean penisVirgin = npc.isPenisVirgin();
				boolean urethraVirgin = npc.isUrethraVirgin();
				boolean vaginaVirgin = npc.isVaginaVirgin();
				boolean vaginaUrethraVirgin = npc.isVaginaUrethraVirgin();
				
				npc.setBody(npc.getGenderIdentity(), RacialBody.valueOfRace(npc.getRace()), npc.getRaceStage());
				CharacterUtils.randomiseBody(npc, false);
				npc.setPendingTransformationToGenderIdentity(false);
				
				npc.setAssVirgin(assVirgin);
				npc.setFaceVirgin(faceVirgin);
				npc.setNippleVirgin(nippleVirgin);
				npc.setPenisVirgin(penisVirgin);
				npc.setUrethraVirgin(urethraVirgin);
				npc.setVaginaVirgin(vaginaVirgin);
				npc.setVaginaUrethraVirgin(vaginaUrethraVirgin);
			}
			
			// Prostitutes stay on promiscuity pills to avoid pregnancies, and, if the NPC is male, to avoid knocking up their clients
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
			
			if(npc.hasStatusEffect(StatusEffect.PREGNANT_3) && (minutesPassed - npc.getTimeProgressedToFinalPregnancyStage())>(12*60)) {
				if(npc instanceof Lilaya) {
					if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.reactedToPregnancyLilaya)) {
						// Lilaya will only end pregnancy after you've seen it.
						npc.endPregnancy(true);
					}
					
				} else {
					npc.endPregnancy(true);
					if(npc instanceof Kate) {
						Main.game.getDialogueFlags().values.remove(DialogueFlagValue.reactedToKatePregnancy);
					}
				}
			}
			
			if(npc.getLocation().equals(Main.game.getPlayer().getLocation()) && npc.getWorldLocation()==Main.game.getPlayer().getWorldLocation()) {
				for(CoverableArea ca : CoverableArea.values()) {
					if(npc.isCoverableAreaExposed(ca) && ca!=CoverableArea.MOUTH) {
						npc.setAreaKnownByCharacter(ca, Main.game.getPlayer(), true);
					}
				}
			}
			
			for(int i=1; i <= hoursPassed; i++) {
				npc.hourlyUpdate();
				if(npc.getLocationPlace().getPlaceType()!=PlaceType.GENERIC_EMPTY_TILE) { // Don't bother with banished NPCs:
					npc.performHourlyFluidsCheck();
				}
			}
			
			if(newDay) {
				npc.resetDaysOrgasmCount();
				try {
					npc.dailyReset();
				} catch(Exception ex) {
					System.err.println("Issue in method: dailyReset(), for character ID: "+npc.getId()+"\n"+ex.getMessage());
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
		isInNPCUpdateLoop = false;
		for(NPC npc : npcsToRemove) {
			NPCMap.remove(npc.getId());
		}
		for(NPC npc : npcsToAdd) {
			NPCMap.put(npc.getId(), npc);
		}
		npcsToRemove.clear();
		npcsToAdd.clear();
		
		// If not in combat:
		if (!isInCombat() && !isInSex() && !currentDialogueNode.isRegenerationDisabled()) {
			// Regenerate health and stamina over time:
			if (Main.game.getPlayer().getHealthPercentage() < 1) {
				Main.game.getPlayer().incrementHealth(turnTime * Main.game.getPlayer().getRegenerationRate());
			}
			if (Main.game.getPlayer().getManaPercentage() < 1) {
				Main.game.getPlayer().incrementMana(turnTime * Main.game.getPlayer().getRegenerationRate());
			}
			Main.game.getPlayer().alignLustToRestingLust(turnTime);
		}
		if(Main.game.getCurrentDialogueNode()!=MiscDialogue.STATUS_EFFECTS) {
			Main.game.getPlayer().calculateStatusEffects(turnTime);
		}
		
		for(int i=1; i <= hoursPassed; i++) {
			Main.game.getPlayer().performHourlyFluidsCheck();
		}
		
		RenderingEngine.ENGINE.renderButtons();
		MainController.updateUI();
		
		Main.mainController.getTooltip().hide();
		
		if(!Main.game.getPlayer().getStatusEffectDescriptions().isEmpty()
				&& Main.game.getCurrentDialogueNode()!=MiscDialogue.STATUS_EFFECTS
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
		
		if(Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.NORMAL) { // Catch slavery management NPC not correctly being assigned to null:
			Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(null);
		}
		
//		System.out.println((System.nanoTime()-tStart)/1000000000d+"s");
	}
	
	public Season getSeason() {
		return Season.getSeasonFromMonth(getDateNow().getMonth());
	}
	
	// Set weather and time remaining.
	// Handles Lilith's Lust build up.
	// Appends description of storm gathering and breaking to mainController.
	private void handleAtmosphericConditions(int timeTaken) {

		weatherTimeRemaining -= timeTaken;

		// Weather change:
		if (weatherTimeRemaining < 0) {
			switch (currentWeather) {
				case CLEAR:
					if(minutesPassed >= nextStormTime) {
						currentWeather = Weather.MAGIC_STORM_GATHERING;
						weatherTimeRemaining = (int) (gatheringStormDuration - (minutesPassed - nextStormTime));
					} else {
						currentWeather = Weather.CLOUD;
						weatherTimeRemaining = 2 * 60 + Util.random.nextInt(2 * 60); // Clouds last for 2-4 hours
					}
					break;
					
				case CLOUD:
					if(minutesPassed >= nextStormTime) {
						currentWeather = Weather.MAGIC_STORM_GATHERING;
						weatherTimeRemaining = (int) (gatheringStormDuration - (minutesPassed - nextStormTime));
					} else {
						if (Math.random() > 0.4) { // 40% chance that will start raining
							if(getSeason()==Season.WINTER) {
								currentWeather = Weather.SNOW;
							} else {
								currentWeather = Weather.RAIN;
							}
							weatherTimeRemaining = 1 * 60 + Util.random.nextInt(5 * 60); // Rain lasts for 1-6 hours
						} else {
							currentWeather = Weather.CLEAR;
							weatherTimeRemaining = 4 * 60 + Util.random.nextInt(4 * 60); // Clear weather lasts for 4-8 hours
						}
					}
					break;
					
				case MAGIC_STORM:
					nextStormTime = minutesPassed + (60*48) + (60*Util.random.nextInt(24)); // Next storm in 2-3 days
					gatheringStormDuration = 4 * 60 + Util.random.nextInt(2 * 60);
					currentWeather = Weather.CLEAR;
					weatherTimeRemaining = 4 * 60 + Util.random.nextInt(4 * 60);
					break;
					
				case MAGIC_STORM_GATHERING:
					currentWeather = Weather.MAGIC_STORM;
					Main.game.getDialogueFlags().values.add(DialogueFlagValue.stormTextUpdateRequired);
					weatherTimeRemaining = 8 * 60 + Util.random.nextInt(4 * 60); // Storm lasts 8-12 hours
					break;
					
				case RAIN: case SNOW:
					if(minutesPassed >= nextStormTime) {
						currentWeather = Weather.MAGIC_STORM_GATHERING;
						weatherTimeRemaining = (int) (gatheringStormDuration - (minutesPassed - nextStormTime));
					} else {
						currentWeather = Weather.CLOUD;
						weatherTimeRemaining = 2 * 60 + Util.random.nextInt(2 * 60); // Clouds last for 2-4 hours
					}
					break;
					
				default:
					break;
			}
		}
	}

	public long getNextStormTime() {
		return nextStormTime;
	}
	
	public String getNextStormTimeAsTimeString() {
		long hours = ((nextStormTime+gatheringStormDuration)-minutesPassed)/60;
		return (hours/24)+" days, "+hours%24+" hours, "+((nextStormTime+gatheringStormDuration)-minutesPassed)%60+" minutes";
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
			DialogueNodeOld node = response.getNextDialogue();
			response.applyEffects();
			
			if(response instanceof ResponseCombat) {
				setContent(new Response("", "", ((ResponseCombat)response).initCombat()));
				return;
				
			} else if(response instanceof ResponseSex) {
				setContent(new Response("", "", ((ResponseSex)response).initSex()));
				return;
				
			} else if(response instanceof ResponseEffectsOnly) {
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
								if (character instanceof NPC) {
									if (((NPC) character).isAddedToContacts()) {
										Main.game.getPlayer().addCharacterEncountered(character);
									}
									if(!character.isRaceConcealed()) {
										Main.getProperties().addRaceDiscovered(character.getSubspecies());
									}
									((NPC) character).setLastTimeEncountered(minutesPassed);
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
						if (!node.isNoTextForContinuesDialogue()) {
							if(Main.game.isInSex()) {
								dialogueTitle = UtilText.parse(node.getLabel());
							}

							if(node.isDisplaysActionTitleOnContinuesDialogue()) {
								if (currentDialogueNode.getDialogueNodeType() == DialogueNodeType.NORMAL) {
									positionAnchor++;
								}
							
								pastDialogueSB.append("<hr id='position" + positionAnchor + "'><p class='option-disabled'>&gt " + chosenResponse + "</p>");
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
						}
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
					Main.saveGame("AutoSave_"+Main.game.getPlayer().getName(), true);
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
					Main.game.endTurn(getCurrentDialogueNode().getMinutesPassed());
				}
				
				TooltipUpdateThread.cancelThreads=true;
				//Main.mainController.processNewDialogue();
			}
		}
	}

	public void setContent(Response response, boolean allowTimeProgress, Colour flashMessageColour, String flashMessageText){
		
		DialogueNodeOld node = response.getNextDialogue();
		response.applyEffects();
		
		if (node == null){
			return;
		}

		int currentPosition = 0;
		if(getCurrentDialogueNode()!=null) {
			currentPosition =  (int) Main.mainController.getWebEngine().executeScript("document.getElementById('content-block').scrollTop");
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
						if (((NPC) character).isAddedToContacts()) {
							Main.game.getPlayer().addCharacterEncountered(character);
						}
						if(!character.isRaceConcealed()) {
							Main.getProperties().addRaceDiscovered(character.getSubspecies());
						}
						((NPC) character).setLastTimeEncountered(minutesPassed);
					}
				}
			}
		}
		
		if (currentDialogueNode != null) {
			if (node.isContinuesDialogue()) {
				if (!node.isNoTextForContinuesDialogue()) {
					if(Main.game.isInSex()) {
						dialogueTitle = UtilText.parse(node.getLabel());
					}
					

					if(node.isDisplaysActionTitleOnContinuesDialogue()) {
						if (currentDialogueNode.getDialogueNodeType() == DialogueNodeType.NORMAL) {
							positionAnchor++;
						}
						
						pastDialogueSB.append(UtilText.parse("<hr id='position" + positionAnchor + "'><p class='option-disabled'>&gt " + currentDialogueNode.getLabel() + "</p>"));
					}
					
					pastDialogueSB.append(content);
				}
			} else {
				dialogueTitle = UtilText.parse(node.getLabel());
				if (currentDialogueNode.getDialogueNodeType() == DialogueNodeType.NORMAL) {
					positionAnchor = 0;
				}
				
				pastDialogueSB.setLength(0);
				pastDialogueSB.append(
						UtilText.parse(
							"<b id='position" + positionAnchor + "'></b>"
							+ textStartStringBuilder.toString()
							+ content
							 + textEndStringBuilder.toString()
						));
			}
		} else {
			dialogueTitle = UtilText.parse(node.getLabel());
			pastDialogueSB.setLength(0);
			pastDialogueSB.append(
					UtilText.parse(
						textStartStringBuilder.toString()
						+ content
						 + textEndStringBuilder.toString()
					));
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
			Main.saveGame("AutoSave_"+Main.game.getPlayer().getName(), true);
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
				Main.game.endTurn(getCurrentDialogueNode().getMinutesPassed());
			} else {
				Main.game.endTurn(0);
			}
		}
		//--------------------
		
	}
	
	private boolean requiresYScroll(DialogueNodeOld node) {
		return currentDialogueNode.getDialogueNodeType()==DialogueNodeType.INVENTORY
				&& (!node.equals(InventoryDialogue.DYE_CLOTHING)
						&& !node.equals(InventoryDialogue.DYE_CLOTHING_CHARACTER_CREATION)
						&& !node.equals(InventoryDialogue.DYE_EQUIPPED_CLOTHING)
						&& !node.equals(InventoryDialogue.DYE_EQUIPPED_CLOTHING_CHARACTER_CREATION)
						&& !node.equals(InventoryDialogue.DYE_EQUIPPED_WEAPON)
						&& !node.equals(InventoryDialogue.DYE_WEAPON));
	}
	
	private static boolean isContentScroll(DialogueNodeOld node) {
		return (node.getDialogueNodeType()!=DialogueNodeType.CHARACTERS_PRESENT
				&& !node.equals(PhoneDialogue.CHARACTER_APPEARANCE)
				&& !node.equals(PhoneDialogue.CONTACTS_CHARACTER))
				|| node.equals(CharactersPresentDialogue.PERKS)
				|| node.equals(BodyChanging.BODY_CHANGING_ASS)
				|| node.equals(BodyChanging.BODY_CHANGING_BREASTS)
				|| node.equals(BodyChanging.BODY_CHANGING_CORE)
				|| node.equals(BodyChanging.BODY_CHANGING_FACE)
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
		if(dialogueTitle.isEmpty()) {
			return "";
		}
		
		return "<div class='content-title'>"
					+ "<div class='title-button' id='copy-content-button'>"+SVGImages.SVG_IMAGE_PROVIDER.getCopyIcon()+"</div>"
					+ (Main.game.getCurrentDialogueNode().equals(CharactersPresentDialogue.MENU)
							|| Main.game.getCurrentDialogueNode().equals(PhoneDialogue.CHARACTER_APPEARANCE)
							|| Main.game.getCurrentDialogueNode().equals(PhoneDialogue.CONTACTS_CHARACTER)
							?"<div class='title-button' id='export-character-button' style='left:auto;right:4px;'>"+SVGImages.SVG_IMAGE_PROVIDER.getExportIcon()+"</div>"
							:"")
					+ "<h4 style='text-align:center;'>" + dialogueTitle + "</h4>"
				+ "</div>";
	}
	
	private String getMapDiv() {
		return "";
//		return (Main.game.getActiveWorld() != null && isStarted() && isRenderMap()
//					&& !Main.game.getCurrentDialogueNode().isMapDisabled() && Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.NORMAL && !Main.game.isInSex() && !Main.game.isInCombat()
//				? "<div style='float:left; width:250px; height:250px; padding:8px; margin:8px; border-radius:5px; background:#333;'>"
//					+ (Main.game.isStarted()?RenderingEngine.ENGINE.renderedHTMLMap():"")
//				+ "</div>"
//				:"");
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
	private String getResponsesDiv(DialogueNodeOld node) {
		return getResponsesDiv(node, true);
	}

	private String getResponsesDiv(DialogueNodeOld node, boolean withPointerReset) {
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
									?"style='color:"+Colour.TEXT_GREY.toWebHexString()+";'"
									:(responseTab==responsePageCounter
										?""
										:"style='color:"+Colour.TEXT_HALF_GREY.toWebHexString()+";'"))
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
	
	private boolean isResponseTabEmpty(DialogueNodeOld node, int responseTab) {
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
				+"<p style='text-align:center;font-size:0.6em;color:#777;'>Dialogue written by "+currentDialogueNode.getAuthor()+" for <i>Lilith's Throne v"+Main.VERSION_NUMBER+"</i></p>"
				+ "</body>";
	}

	private String getResponseBoxDiv(Response response, int option) {
		String style="", iconRight="", iconLeft="";
		boolean responseDisabled = false;
		
		if(response.disabledOnNullDialogue() && response.getNextDialogue()==null) {
			responseDisabled = true;
			
		} else if (response.isAbleToBypass()) {
			iconRight = "<div class='response-icon'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseCorruptionBypass()+"</div>";
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
					iconLeft = "<div class='response-icon-left'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseDomGentle()+"</div>";
					break;
				case DOM_NORMAL:
					iconLeft = "<div class='response-icon-left'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseDomNormal()+"</div>";
					break;
				case DOM_ROUGH:
					iconLeft = "<div class='response-icon-left'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseDomRough()+"</div>";
					break;
				case SUB_EAGER:
					iconLeft = "<div class='response-icon-left'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseSubEager()+"</div>";
					break;
				case SUB_NORMAL:
					iconLeft = "<div class='response-icon-left'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseSubNormal()+"</div>";
					break;
				case SUB_RESISTING:
					iconLeft = "<div class='response-icon-left'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseSubResist()+"</div>";
					break;
			}
		}
		
		
		float fontSize = 1;
		String strippedTitle = UtilText.parse(response.getTitle()).replaceAll("<.*?>", "").replaceAll(UtilText.getCurrencySymbol(), "1");
		if(strippedTitle.length()>14) {
			fontSize-=(strippedTitle.length()-14)*0.03f;
		}
		style = "style='font-size:"+fontSize+"em;'";
		
		if(response.getHighlightColour()!=Colour.TEXT) {
			style = "style='color:"+response.getHighlightColour().toWebHexString()+"; font-size:"+fontSize+"em;'";
		}
		
		String titleText = UtilText.parse(response.getTitle());
		
		if(responsePage==0) {
			return "<div class='response-box"+(responsePointer==option?" selected":"")+"' id='option_" + option + "'>"
						+ "<b class='hotkey-icon'>" + getResponseHotkey(option) + "</b>"
						+ "<p class='response-text"+(responsePointer==option?" selected":"")+"' "+style+">" + (!responseDisabled ? titleText : UtilText.getDisabledResponse(titleText)) + "</p>"
						+ iconLeft
						+ iconRight
					+ "</div>";
			
		} else {
			if(option == 0) {
				return "<div class='response-box"+(responsePointer-((responsePage+1)*MainController.RESPONSE_COUNT)+1==(option)?" selected":"")+"' id='option_" + option + "'>"
						+ "<b class='hotkey-icon'>" + getResponseHotkey(option) + "</b>"
						+ "<p class='response-text"+(responsePointer-((responsePage+1)*MainController.RESPONSE_COUNT)+1==option?" selected":"")+"' "+style+">"
						+ (!responseDisabled ? titleText : UtilText.getDisabledResponse(titleText)) + "</p>"
						+ iconLeft
						+ iconRight
					+ "</div>";
			} else {
				return "<div class='response-box"+(responsePointer-(responsePage*MainController.RESPONSE_COUNT)+1==(option)?" selected":"")+"' id='option_" + option + "'>"
						+ "<b class='hotkey-icon'>" + getResponseHotkey(option) + "</b>"
						+ "<p class='response-text"+(responsePointer-(responsePage*MainController.RESPONSE_COUNT)+1==option?" selected":"")+"' "+style+">"
						+ (!responseDisabled ? titleText : UtilText.getDisabledResponse(titleText)) + "</p>"
						+ iconLeft
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
	}
	
	/**
	 * Flashes a message at the bottom of the screen.
	 * @param colour Colour of the text message.
	 * @param text Content of the message.
	 */
	public void flashMessage(Colour colour, String text){
//		Main.game.addEvent(new EventLogEntry(minutesPassed, "", "<span style='color:"+colour.toWebHexString()+";'>"+text+"</span>"), false);
//		Main.mainController.updateUIRightPanel();
		Main.mainController.getWebEngine().executeScript(
				"document.getElementById('bottom-text').innerHTML=\"<span style='color:"+colour.toWebHexString()+";'>"+text+"</span>\";"
				+ "document.getElementById('bottom-text').classList.add('demo');"
				+ "setTimeout(function(){"
				+ "document.getElementById('bottom-text').classList.remove('demo');"
				+ "}, 2000);");
	}

	public void restoreSavedContent(boolean regenerateSceneDialogue) {
		positionAnchor = initialPositionAnchor;
		dialogueTitle = UtilText.parse(savedDialogueNode.getLabel());
		
		currentDialogueNode = savedDialogueNode;
		
		if(Main.game.isInSex()) {
			Sex.recalculateSexActions();
		}
		//TODO
		if (currentDialogueNode.reloadOnRestore() || regenerateSceneDialogue) {
//			System.out.println("restored with regenerated text");
			
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
			currentDialogue = currentDialogue.replaceFirst("<div class='div-center' style='font-size:" + FONT_SIZE_LARGE + "px'; line-height:" + (FONT_SIZE_LARGE + 6) + "px;>",
					"<div class='div-center' style='font-size:" + FONT_SIZE_NORMAL + "px'; line-height:" + (FONT_SIZE_NORMAL + 6) + "px;>");
			currentDialogue = currentDialogue.replaceFirst("<div class='div-center' style='font-size:" + FONT_SIZE_HUGE + "px'; line-height:" + (FONT_SIZE_HUGE + 6) + "px;>",
					"<div class='div-center' style='font-size:" + FONT_SIZE_NORMAL + "px'; line-height:" + (FONT_SIZE_NORMAL + 6) + "px;>");
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
	
	private static void setMainContentRegex(String prefix, String currentDialogue) {
		Main.mainController.setMainContent(prefix + currentDialogue.replaceAll("\\.\\.\\.", "&hellip;").replaceAll("\\.([\\D])", ".\u200b$1").replaceAll("\\[", "\u200b[\u200b"));
	}
	
	private List<NPC> charactersPresent = new ArrayList<>();
	private List<NPC> charactersHome = new ArrayList<>();
	public List<NPC> getCharactersPresent() {
		if(player==null) {
			charactersPresent.clear();
			return charactersPresent;
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
		charactersHome.clear();
		
		for(NPC npc : NPCMap.values()) {
			if(npc!=null
					&& npc.getHomeWorldLocation()!=null
					&& npc.getHomeWorldLocation()==cell.getType()
					&& npc.getHomeLocation()!=null
					&& npc.getHomeLocation().equals(cell.getLocation())) {
				charactersHome.add(npc);
			}
		}

		charactersHome.sort((c1, c2) ->
				(c2.getLevel()-c1.getLevel())==0
					?c2.getName().compareTo(c1.getName())
					:(c2.getLevel()-c1.getLevel()));
		
		return charactersHome;
	}
	
	public List<NPC> getCharactersPresent(Cell cell) {
		return getCharactersPresent(cell.getType(), cell.getLocation());
	}
	
	public List<NPC> getCharactersPresent(WorldType worldType, Vector2i location) {
		charactersPresent.clear();
		
		for(NPC npc : NPCMap.values()) {
			if(npc.getWorldLocation()==worldType && npc.getLocation().equals(location)) {
				charactersPresent.add(npc);
			}
		}
		
		charactersPresent.sort((c1, c2) ->
				(c2.getLevel()-c1.getLevel())==0
					?c2.getName().compareTo(c1.getName())
					:(c2.getLevel()-c1.getLevel()));
		
		return charactersPresent;
	}
	
	public List<NPC> getCharactersPresent(WorldType worldType, PlaceType placeType) {
		Cell cell = worlds.get(worldType).getCell(placeType);
		
		return getCharactersPresent(cell);
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

	public int getWeatherTimeRemaining() {
		return weatherTimeRemaining;
	}
	
	public void setWeather(Weather weather, int timeRemaining) {
		currentWeather = weather;
		weatherTimeRemaining = timeRemaining;
	}

	public World getActiveWorld() {
		return worlds.get(player.getWorldLocation());
	}

	public Map<WorldType, World> getWorlds() {
		return worlds;
	}

	/**
//	 * @param world
//	 * @param location Location to set player to
//	 */
	public void setActiveWorld(World world, Vector2i location, boolean setDefaultDialogue) {
//		activeWorld = world;
		player.setWorldLocation(world.getWorldType());
		player.setLocation(location);
		
		if(setDefaultDialogue) {
			DialogueNodeOld dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true);
			Main.game.setContent(new Response("", "", dn));
		}
	}
	
	public void setActiveWorld(World world, PlaceType placeType, boolean setDefaultDialogue) {
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

	public long getMinutesPassed() {
		return minutesPassed;
	}
	
	public LocalDateTime getStartingDate() {
		return startingDate;
	}
	
	public void setStartingDateMonth(Month month) {
		if(startingDate.getMonthValue() > month.getValue()) {
			startingDate = startingDate.minusMonths(startingDate.getMonthValue() - month.getValue());
		} else {
			startingDate = startingDate.plusMonths(month.getValue() - startingDate.getMonthValue());
		}
		
		
//		startingDate = LocalDateTime.of(LocalDateTime.now().getYear(), month, LocalDateTime.now().getDayOfMonth(), 00, 00).plusYears(3);
	}
	
	public LocalDateTime getDateNow() {
		return getStartingDate().plusMinutes(Main.game.getMinutesPassed());
	}

	public String getDisplayDate() {
		if (isInNewWorld()) {
			if (getDialogueFlags().hasFlag(DialogueFlagValue.knowsDate)) {
				return Units.date(getDateNow(), Units.DateType.LONG);
			}
			return "Unknown";
		}
		return Units.date(getDateNow().minusYears(TIME_SKIP_YEARS), Units.DateType.LONG);
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
	
	public boolean isDayTime() {
		return minutesPassed % (24 * 60) >= (60 * 7) && minutesPassed % (24 * 60) < (60 * 21);
	}
	
	public boolean isMorning() {
		return minutesPassed % (24 * 60) >= 0 && minutesPassed % (24 * 60) < (60 * 12);
	}

	public int getDayNumber() {
		return (int) (1 + (getMinutesPassed() / (24 * 60)));
	}
	
	public int getDayNumber(long minutes) {
		return (int) (1 + (minutes / (24 * 60)));
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
	
	public NPC getNPC(Class<? extends NPC> NPCclass) {
		for(NPC npc : NPCMap.values()) {
			if(npc.getClass().equals(NPCclass)) {
				return npc;
			}
		}
		return null;
	}

	public NPC getPrologueMale() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(PrologueMale.class));
		} catch (Exception e) {
			System.err.println("getPrologueMale() returning null!");
			return null;
		}
	}

	public NPC getPrologueFemale() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(PrologueFemale.class));
		} catch (Exception e) {
			System.err.println("getPrologueFemale() returning null!");
			return null;
		}
	}

	public NPC getTestNPC() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(TestNPC.class));
		} catch (Exception e) {
			System.err.println("getTestNPC() returning null!");
			return null;
		}
	}

	public NPC getLilaya() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Lilaya.class));
		} catch (Exception e) {
			System.err.println("getLilaya() returning null!");
			return null;
		}
	}

	public NPC getRose() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Rose.class));
		} catch (Exception e) {
			System.err.println("getRose() returning null!");
			return null;
		}
	}

	public NPC getBrax() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Brax.class));
		} catch (Exception e) {
			System.err.println("getBrax() returning null!");
			return null;
		}
	}

//	public NPC getArthur() {
//		return arthur;
//	}

	public NPC getPix() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Pix.class));
		} catch (Exception e) {
			System.err.println("getPix() returning null!");
			return null;
		}
	}

	public NPC getRalph() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Ralph.class));
		} catch (Exception e) {
			System.err.println("getRalph() returning null!");
			return null;
		}
	}

	public NPC getNyan() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Nyan.class));
		} catch (Exception e) {
			System.err.println("getNyan() returning null!");
			return null;
		}
	}

	public NPC getVicky() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Vicky.class));
		} catch (Exception e) {
			System.err.println("getVicky() returning null!");
			return null;
		}
	}

	public NPC getKate() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Kate.class));
		} catch (Exception e) {
			System.err.println("getKate() returning null!");
			return null;
		}
	}

	public NPC getScarlett() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Scarlett.class));
		} catch (Exception e) {
			System.err.println("getScarlett() returning null!");
			return null;
		}
	}
	
	public NPC getAlexa() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Alexa.class));
		} catch (Exception e) {
			System.err.println("getAlexa() returning null!");
			return null;
		}
	}

	public NPC getHarpyBimbo() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(HarpyBimbo.class));
		} catch (Exception e) {
			System.err.println("getHarpyBimbo() returning null!");
			return null;
		}
	}

	public NPC getHarpyDominant() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(HarpyDominant.class));
		} catch (Exception e) {
			System.err.println("getHarpyDominant() returning null!");
			return null;
		}
	}

	public NPC getHarpyNympho() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(HarpyNympho.class));
		} catch (Exception e) {
			System.err.println("getHarpyNympho() returning null!");
			return null;
		}
	}

	public NPC getHarpyBimboCompanion() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(HarpyBimboCompanion.class));
		} catch (Exception e) {
			System.err.println("getHarpyBimboCompanion() returning null!");
			return null;
		}
	}

	public NPC getHarpyDominantCompanion() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(HarpyDominantCompanion.class));
		} catch (Exception e) {
			System.err.println("getHarpyDominantCompanion() returning null!");
			return null;
		}
	}

	public NPC getHarpyNymphoCompanion() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(HarpyNymphoCompanion.class));
		} catch (Exception e) {
			System.err.println("getHarpyNymphoCompanion() returning null!");
			return null;
		}
	}

	public NPC getPazu() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Pazu.class));
		} catch (Exception e) {
			System.err.println("getPazu() returning null!");
			return null;
		}
	}
	
	public NPC getCandi() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(CandiReceptionist.class));
		} catch (Exception e) {
			System.err.println("getCandiReceptionist() returning null!");
			return null;
		}
	}
	
	public NPC getFinch() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Finch.class));
		} catch (Exception e) {
			System.err.println("getFinch() returning null!");
			return null;
		}
	}
	
	public NPC getZaranix() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Zaranix.class));
		} catch (Exception e) {
			System.err.println("getZaranix() returning null!");
			return null;
		}
	}
	
	public NPC getAmber() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Amber.class));
		} catch (Exception e) {
			System.err.println("getAmber() returning null!");
			return null;
		}
	}
	
	public NPC getArthur() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Arthur.class));
		} catch (Exception e) {
			System.err.println("getArthur() returning null!");
			return null;
		}
	}
	
	public NPC getKelly() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(ZaranixMaidKelly.class));
		} catch (Exception e) {
			System.err.println("getZaranixMaidKelly() returning null!");
			return null;
		}
	}
	
	public NPC getKatherine() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(ZaranixMaidKatherine.class));
		} catch (Exception e) {
			System.err.println("getZaranixMaidKatherine() returning null!");
			return null;
		}
	}

	public NPC getAshley() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Ashley.class));
		} catch (Exception e) {
			System.err.println("getAshley() returning null!");
			return null;
		}
	}
	
	public NPC getSupplierLeader() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(SupplierLeader.class));
		} catch (Exception e) {
			System.err.println("getSupplierLeader() returning null!");
			return null;
		}
	}
	
	public NPC getSupplierPartner() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(SupplierPartner.class));
		} catch (Exception e) {
			System.err.println("getSupplierPartner() returning null!");
			return null;
		}
	}
	
	public NPC getAngel() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Angel.class));
		} catch (Exception e) {
			System.err.println("getAngel() returning null!");
			return null;
		}
	}
	
	public NPC getBunny() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Bunny.class));
		} catch (Exception e) {
			System.err.println("getBunny() returning null!");
			return null;
		}
	}
	
	public NPC getLoppy() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Loppy.class));
		} catch (Exception e) {
			System.err.println("getLoppy() returning null!");
			return null;
		}
	}
	
	public NPC getLumi() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Lumi.class));
		} catch (Exception e) {
			System.err.println("getLumi() returning null!");
			return null;
		}
	}
	
	public NPC getClaire() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Claire.class));
		} catch (Exception e) {
			System.err.println("getClaire() returning null!");
			return null;
		}
	}

	public NPC getSlimeQueen() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(SlimeQueen.class));
		} catch (Exception e) {
			System.err.println("getSlimeQueen() returning null!");
			return null;
		}
	}

	public NPC getSlimeGuardIce() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(SlimeGuardIce.class));
		} catch (Exception e) {
			System.err.println("getSlimeGuardIce() returning null!");
			return null;
		}
	}

	public NPC getSlimeGuardFire() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(SlimeGuardFire.class));
		} catch (Exception e) {
			System.err.println("getSlimeGuardFire() returning null!");
			return null;
		}
	}

	public NPC getSlimeRoyalGuard() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(SlimeRoyalGuard.class));
		} catch (Exception e) {
			System.err.println("getSlimeRoyalGuard() returning null!");
			return null;
		}
	}

	public NPC getRoxy() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Roxy.class));
		} catch (Exception e) {
			System.err.println("getRoxy() returning null!");
			return null;
		}
	}

	public NPC getAxel() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Axel.class));
		} catch (Exception e) {
			System.err.println("getAxel() returning null!");
			return null;
		}
	}

	public NPC getEpona() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Epona.class));
		} catch (Exception e) {
			System.err.println("getEpona() returning null!");
			return null;
		}
	}

	public NPC getJules() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Jules.class));
		} catch (Exception e) {
			System.err.println("getJules() returning null!");
			return null;
		}
	}

	public NPC getKruger() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Kruger.class));
		} catch (Exception e) {
			System.err.println("getKruger() returning null!");
			return null;
		}
	}

	public NPC getKalahari() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(Kalahari.class));
		} catch (Exception e) {
			System.err.println("getKalahari() returning null!");
			return null;
		}
	}

	public NPC getFortressAlphaLeader() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(FortressAlphaLeader.class));
		} catch (Exception e) {
			System.err.println("getFortressAlphaLeader() returning null!");
			return null;
		}
	}

	public NPC getFortressDemonLeader() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(FortressDemonLeader.class));
		} catch (Exception e) {
			System.err.println("getFortressDemonLeader() returning null!");
			return null;
		}
	}

	public NPC getFortressMalesLeader() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(FortressMalesLeader.class));
		} catch (Exception e) {
			System.err.println("getFortressMalesLeader() returning null!");
			return null;
		}
	}

	public NPC getFortressFemalesLeader() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(FortressFemalesLeader.class));
		} catch (Exception e) {
			System.err.println("getFortressFemalesLeader() returning null!");
			return null;
		}
	}
	
	public NPC getGenericMaleNPC() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(GenericMaleNPC.class));
		} catch (Exception e) {
			System.err.println("getGenericMaleNPC() returning null!");
			return null;
		}
	}

	public NPC getGenericFemaleNPC() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(GenericFemaleNPC.class));
		} catch (Exception e) {
			System.err.println("getGenericFemaleNPC() returning null!");
			return null;
		}
	}

	public NPC getGenericAndrogynousNPC() {
		try {
			return (NPC) this.getNPCById(getUniqueNPCId(GenericAndrogynousNPC.class));
		} catch (Exception e) {
			System.err.println("getGenericAndrogynousNPC() returning null!");
			return null;
		}
	}
	
	public List<NPC> getOffspring() {
		List<NPC> offspring = new ArrayList<>();
		
		for(NPC npc : NPCMap.values()) {
			if((npc.getMother()!=null && npc.getMother().isPlayer()) || (npc.getFather()!=null && npc.getFather().isPlayer())) {
				if(npc.getMother()!=null) {
					if(!(npc.getMother().getPregnantLitter() != null && npc.getMother().getPregnantLitter().getOffspring().contains(npc.getId()))) {
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
		List<NPC> offspringSpawned = new ArrayList<>(getOffspring());
		
		offspringSpawned.removeIf(npc -> npc.getWorldLocation()==WorldType.EMPTY);
		
		return offspringSpawned;
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
//			return Main.game.getGenericAndrogynousNPC();
		}
		return NPCMap.get(id);
	}
	
	public Map<String, NPC> getNPCMap() {
		return NPCMap;
	}
	
	public String getUniqueNPCId(Class<? extends NPC> c) {
		return "-1"+","+c.getSimpleName();
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
		
		return npc.getId();
	}
	
	/**
	 * If the NPC has relationship stats with the player, don't delete entirely. Instead, move to PlaceType.GENERIC_EMPTY_TILE.
	 * If the NPC has no stats related to the player, then remove them from the game.
	 * @param npc
	 * @return true if NPC was deleted, false if they were moved to the empty world.
	 */
	public boolean banishNPC(NPC npc) {
		// check fluids in rooms and condoms
		if(Main.game.getPlayer().getSexPartners().containsKey(npc.getId())
				|| npc.getPregnantLitter()!=null
				|| npc.getLastLitterBirthed()!=null
				|| npc.getMother()!=null
				|| npc.getFather()!=null
				|| npc.isUnique()) {
			npc.deleteAllEquippedClothing(); // To cut down on save size.
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
			if(npc.equals(Main.game.getGenericAndrogynousNPC())) {
				return false; // This is the npc returned if there's a problem in getNPCById().
			}
			return banishNPC(npc);
		} catch (Exception e) {
			System.err.println("Main.game.getNPCById("+id+") returning null in method: banishNPC()");
			return false;
		}
	}

	public void removeNPC(String id) {
		if(isInNPCUpdateLoop) {
			npcsToRemove.add(NPCMap.get(id));
		} else {
			NPCMap.remove(id);
		}
	}
	
	public void removeNPC(NPC npc) {
		if(npc.isPregnant()) {
			npc.endPregnancy(false);
			
		} else if(npc.hasStatusEffect(StatusEffect.PREGNANT_0)) {
			npc.removeStatusEffect(StatusEffect.PREGNANT_0);
		}
		
		if(isInNPCUpdateLoop) {
			npcsToRemove.add(npc);
		} else {
			NPCMap.remove(npc.getId());
		}
	}
	
	public int getNumberOfWitches() {
		int i = 0;
		for(NPC npc : NPCMap.values()) {
			if(npc instanceof Cultist && npc.getLocationPlace().getPlaceType()!=PlaceType.GENERIC_EMPTY_TILE) {
				i++;
			}
		}
		return i;
	}
	
	public NPC getActiveNPC() {
		return activeNPC;
	}

	public void setActiveNPC(NPC activeNPC) {
		this.activeNPC = activeNPC;
	}

	public boolean isHintsOn() {
		return hintsOn;
	}

	public void setHintsOn(boolean hintsOn) {
		this.hintsOn = hintsOn;
	}

	public boolean isStarted() {
		return started;
	}

	// Dialogues:

	public boolean isInNewWorld() {
		if(Main.game.getPlayer()==null) {
			return true;
		}
		return Main.game.getPlayer().getWorldLocation()!=WorldType.EMPTY;
	}

	public boolean isPrologueFinished() {
		return prologueFinished;
	}

	public void setPrologueFinished(boolean prologueFinished) {
		this.prologueFinished = prologueFinished;
	}
	
	public StringBuilder getTextStartStringBuilder() {
		return textStartStringBuilder;
	}

	public void clearTextStartStringBuilder() {
		textStartStringBuilder.setLength(0);
	}

	public StringBuilder getTextEndStringBuilder() {
		return textEndStringBuilder;
	}

	public void clearTextEndStringBuilder() {
		textEndStringBuilder.setLength(0);
	}

	public DialogueNodeOld getCurrentDialogueNode() {
		return currentDialogueNode;
	}

	public DialogueNodeType getMapDisplay() {
		if (currentDialogueNode != null)
			return currentDialogueNode.getDialogueNodeType();
		else
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

	public DialogueNodeOld getSavedDialogueNode() {
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


	public boolean isDebugMode() {
		return Main.getProperties().hasValue(PropertyValue.debugMode);
	}
	
	public boolean isMapReveal() {
		return Main.getProperties().hasValue(PropertyValue.mapReveal);
	}
	
	public boolean isConcealedSlotsReveal() {
		return Main.getProperties().hasValue(PropertyValue.concealedSlotsReveal);
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
	
	public boolean isIncestEnabled() {
		return Main.getProperties().hasValue(PropertyValue.incestContent);
	}
	
	public boolean isFacialHairEnabled() {
		return Main.getProperties().hasValue(PropertyValue.facialHairContent);
	}
	
	public boolean isFemaleFacialHairEnabled() {
		return Main.getProperties().hasValue(PropertyValue.feminineBeardsContent);
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
	
	public boolean isRenderMap() {
		return renderMap;
	}

	public void setRenderMap(boolean renderMap) {
		this.renderMap = renderMap;
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
	
	public Map<Integer, List<SlaveryEventLogEntry>> getSlaveryEventLog() {
		return slaveryEventLog;
	}
	
	public void addSlaveryEvent(int day, NPC slave, SlaveryEventLogEntry event) {
		slaveryEventLog.putIfAbsent(day, new ArrayList<>());
		
		slaveryEventLog.get(day).add(event);
	}
	

	public int getNpcTally() {
		return npcTally.get();
	}

	public OccupancyUtil getOccupancyUtil() {
		return occupancyUtil;
	}
	
	public DialogueNodeOld getDefaultDialogue() {
		return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true);
	}

	public DialogueNodeOld getDefaultDialogueNoEncounter() {
		return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(false);
	}

	public boolean isRequestAutosave() {
		return requestAutosave;
	}

	public void setRequestAutosave(boolean requestAutosave) {
		this.requestAutosave = requestAutosave;
	}
	
}
