package com.lilithsthrone.game;

import java.io.File;
import java.io.Serializable;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.GenderPreference;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Alexa;
import com.lilithsthrone.game.character.npc.dominion.Amber;
import com.lilithsthrone.game.character.npc.dominion.Angel;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Ashley;
import com.lilithsthrone.game.character.npc.dominion.Brax;
import com.lilithsthrone.game.character.npc.dominion.Bunny;
import com.lilithsthrone.game.character.npc.dominion.CandiReceptionist;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.character.npc.dominion.DominionAlleywayAttacker;
import com.lilithsthrone.game.character.npc.dominion.Finch;
import com.lilithsthrone.game.character.npc.dominion.HarpyBimbo;
import com.lilithsthrone.game.character.npc.dominion.HarpyBimboCompanion;
import com.lilithsthrone.game.character.npc.dominion.HarpyDominant;
import com.lilithsthrone.game.character.npc.dominion.HarpyDominantCompanion;
import com.lilithsthrone.game.character.npc.dominion.HarpyNympho;
import com.lilithsthrone.game.character.npc.dominion.HarpyNymphoCompanion;
import com.lilithsthrone.game.character.npc.dominion.Kate;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Loppy;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.npc.dominion.Pazu;
import com.lilithsthrone.game.character.npc.dominion.Pix;
import com.lilithsthrone.game.character.npc.dominion.Ralph;
import com.lilithsthrone.game.character.npc.dominion.ReindeerOverseer;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.npc.dominion.SlaveInStocks;
import com.lilithsthrone.game.character.npc.dominion.SupplierLeader;
import com.lilithsthrone.game.character.npc.dominion.SupplierPartner;
import com.lilithsthrone.game.character.npc.dominion.TestNPC;
import com.lilithsthrone.game.character.npc.dominion.Vicky;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKatherine;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKelly;
import com.lilithsthrone.game.character.npc.misc.GenericAndrogynousNPC;
import com.lilithsthrone.game.character.npc.misc.GenericFemaleNPC;
import com.lilithsthrone.game.character.npc.misc.GenericMaleNPC;
import com.lilithsthrone.game.character.npc.misc.PrologueFemale;
import com.lilithsthrone.game.character.npc.misc.PrologueMale;
import com.lilithsthrone.game.character.npc.misc.SlaveImport;
import com.lilithsthrone.game.character.persona.History;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueFlags;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.MapDisplay;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntry;
import com.lilithsthrone.game.dialogue.eventLog.SlaveryEventLogEntry;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloor;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.MiscDialogue;
import com.lilithsthrone.game.dialogue.utils.PhoneDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.settings.KeyCodeWithModifiers;
import com.lilithsthrone.game.settings.KeyboardAction;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.slavery.SlaveJobSetting;
import com.lilithsthrone.game.slavery.SlaveryUtil;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.Generation;
import com.lilithsthrone.world.World;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.2.4
 * @author Innoxia
 */
public class Game implements Serializable, XMLSaving {
	private static final long serialVersionUID = 1L;

	public static final int FONT_SIZE_MINIMUM = 12, FONT_SIZE_NORMAL = 18, FONT_SIZE_LARGE = 24, FONT_SIZE_HUGE = 36;

	private PlayerCharacter player;
	
	// NPCs:
	private NPC activeNPC;
	private int npcTally = 0;
	private Map<String, NPC> NPCMap;
	
	private Map<WorldType, World> worlds;
	private long minutesPassed;
	private LocalDateTime startingDate;
	private boolean debugMode, renderAttributesSection, renderMap, inCombat, inSex, imperialMeasurements;
	
	private Weather currentWeather;
	private long nextStormTime;
	private int weatherTimeRemaining;
	
	private Encounter currentEncounter;
	
	private boolean hintsOn, started;
	
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
	private SlaveryUtil slaveryUtil = new SlaveryUtil();

	public Game() {
		worlds = new EnumMap<>(WorldType.class);
		for (WorldType type : WorldType.values()) {
			worlds.put(type, null);
		}
		startingDate = LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(), 00, 00).plusYears(3);
		minutesPassed = 20 * 60;
		inCombat = false;
		inSex = false;
		renderAttributesSection = false;
		renderMap = false;
		debugMode = false;
		imperialMeasurements = false;

		dialogueFlags = new DialogueFlags();

		hintsOn = false;
		started = false;

		NPCMap = new HashMap<>();

		// Start in clouds:
		currentWeather = Weather.CLOUD;
		weatherTimeRemaining = 300;
		nextStormTime = minutesPassed + (60*48) + (60*Util.random.nextInt(24)); // Next storm in 2-3 days

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
			String saveLocation = "data/characters/exported_"+character.getName()+"_day"+Main.game.getDayNumber()+".xml";
			if(new File("data/characters/exported_"+character.getName()+"_day"+Main.game.getDayNumber()+".xml").exists()) {
				saveLocation = "data/characters/exported_"+character.getName()+"_day"+Main.game.getDayNumber()+"("+saveNumber+").xml";
			}
			
			while(new File("data/characters/exported_"+character.getName()+"_day"+Main.game.getDayNumber()+"("+saveNumber+").xml").exists()) {
				saveNumber++;
				saveLocation = "data/characters/exported_"+character.getName()+"_day"+Main.game.getDayNumber()+"("+saveNumber+").xml";
			}
			StreamResult result = new StreamResult(new File(saveLocation));
			
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
				importedSlave.loadFromXML(characterElement, doc);
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
			

			Element informationNode = doc.createElement("coreInfo");
			game.appendChild(informationNode);
			CharacterUtils.addAttribute(doc, informationNode, "version", Main.VERSION_NUMBER);
			CharacterUtils.addAttribute(doc, informationNode, "minutesPassed", String.valueOf(Main.game.minutesPassed));
			CharacterUtils.addAttribute(doc, informationNode, "debugMode", String.valueOf(Main.game.debugMode));
			CharacterUtils.addAttribute(doc, informationNode, "imperialMeasurements", String.valueOf(Main.game.imperialMeasurements));
			CharacterUtils.addAttribute(doc, informationNode, "weather", Main.game.currentWeather.toString());
			CharacterUtils.addAttribute(doc, informationNode, "nextStormTime", String.valueOf(Main.game.nextStormTime));
			CharacterUtils.addAttribute(doc, informationNode, "weatherTimeRemaining", String.valueOf(Main.game.weatherTimeRemaining));

			Element slaveryNode = doc.createElement("slavery");
			game.appendChild(slaveryNode);
			CharacterUtils.addAttribute(doc, slaveryNode, "generatedIncome", String.valueOf(Main.game.getSlaveryUtil().getGeneratedIncome()));
			CharacterUtils.addAttribute(doc, slaveryNode, "generatedUpkeep", String.valueOf(Main.game.getSlaveryUtil().getGeneratedUpkeep()));
			
			Element dateNode = doc.createElement("date");
			informationNode.appendChild(dateNode);
			CharacterUtils.addAttribute(doc, dateNode, "year", String.valueOf(Main.game.startingDate.getYear()));
			CharacterUtils.addAttribute(doc, dateNode, "month", String.valueOf(Main.game.startingDate.getMonthValue()));
			CharacterUtils.addAttribute(doc, dateNode, "dayOfMonth", String.valueOf(Main.game.startingDate.getDayOfMonth()));
			CharacterUtils.addAttribute(doc, dateNode, "hour", String.valueOf(Main.game.startingDate.getHour()));
			CharacterUtils.addAttribute(doc, dateNode, "minute", String.valueOf(Main.game.startingDate.getMinute()));
			
			
			Main.game.dialogueFlags.saveAsXML(game, doc);
			

			Element eventLogNode = doc.createElement("eventLog");
			game.appendChild(eventLogNode);
			for(EventLogEntry event : Main.game.getEventLog().subList(Math.max(0, Main.game.getEventLog().size()-50), Main.game.getEventLog().size())) {
				event.saveAsXML(eventLogNode, doc);
			}
			
			Element slaveryEventLogNode = doc.createElement("slaveryEventLog");
			game.appendChild(slaveryEventLogNode);
			for(int day : Main.game.getSlaveryEventLog().keySet()) {
				Element element = doc.createElement("day");
				slaveryEventLogNode.appendChild(element);
				CharacterUtils.addAttribute(doc, element, "value", String.valueOf(day));
				for(SlaveryEventLogEntry event : Main.game.getSlaveryEventLog().get(day)) {
					event.saveAsXML(element, doc);
				}
			}
			
			
			// Add maps:
			Element mapNode = doc.createElement("maps");
			game.appendChild(mapNode);
			for(World world : Main.game.getWorlds().values()) {
				if(world!=null) {
					world.saveAsXML(mapNode, doc);
				}
			}
			
			// Add player:
			Element characterNode = doc.createElement("playerCharacter");
			game.appendChild(characterNode);
			Main.game.getPlayer().saveAsXML(characterNode, doc);
			
			// Add all NPCs:
			for(GameCharacter character : Main.game.getNPCMap().values()) {
				characterNode = doc.createElement("NPC");
				game.appendChild(characterNode);
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
			
			String saveLocation = "data/saves/"+exportFileName+".xml";
			StreamResult result = new StreamResult(new File(saveLocation));
			
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
			
			if(timeLog) {
				System.out.println("Difference: "+(System.nanoTime()-timeStart)/1000000000f);
			}
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
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
				
				String version = informationNode.getAttribute("version");
				
				Main.game.minutesPassed = Long.valueOf(informationNode.getAttribute("minutesPassed"));
				Main.game.debugMode = Boolean.valueOf(informationNode.getAttribute("debugMode"));
				Main.game.imperialMeasurements = Boolean.valueOf(informationNode.getAttribute("imperialMeasurements"));
				Main.game.currentWeather = Weather.valueOf(informationNode.getAttribute("weather"));
				Main.game.nextStormTime = Long.valueOf(informationNode.getAttribute("nextStormTime"));
				Main.game.weatherTimeRemaining = Integer.valueOf(informationNode.getAttribute("weatherTimeRemaining"));


				try {
					Element slaveryNode = (Element) gameElement.getElementsByTagName("slavery").item(0);
					Main.game.getSlaveryUtil().setGeneratedIncome(Integer.valueOf(slaveryNode.getAttribute("generatedIncome")));
					Main.game.getSlaveryUtil().setGeneratedUpkeep(Integer.valueOf(slaveryNode.getAttribute("generatedUpkeep")));
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
				
				for(int i=0; i<((Element) gameElement.getElementsByTagName("eventLog").item(0)).getElementsByTagName("eventLogEntry").getLength(); i++){
					Element e = (Element) ((Element) gameElement.getElementsByTagName("eventLog").item(0)).getElementsByTagName("eventLogEntry").item(i);
					Main.game.eventLog.add(EventLogEntry.loadFromXML(e, doc));
				}
				Main.game.eventLog.sort(Comparator.comparingLong(EventLogEntry::getTime));
				
				
				NodeList nodes = gameElement.getElementsByTagName("slaveryEventLog");
				Element extraEffectNode = (Element) nodes.item(0);
				if(extraEffectNode != null) {
					for(int i=0; i< extraEffectNode.getElementsByTagName("day").getLength(); i++){
						Element e = (Element) gameElement.getElementsByTagName("day").item(i);
						int day = Integer.valueOf(e.getAttribute("value"));
						Main.game.slaveryEventLog.put(day, new ArrayList<>());
						
						for(int j=0; j< e.getElementsByTagName("eventLogEntry").getLength(); j++){
							Element entry = (Element) e.getElementsByTagName("eventLogEntry").item(j);
							Main.game.slaveryEventLog.get(day).add(SlaveryEventLogEntry.loadFromXML(entry, doc));
						}
					}
				}
				
				if(debug) {
					System.out.println("Core info finished");
				}
				
				// Maps:
				for(int i=0; i<((Element) gameElement.getElementsByTagName("maps").item(0)).getElementsByTagName("world").getLength(); i++){
					
					Element e = (Element) ((Element) gameElement.getElementsByTagName("maps").item(0)).getElementsByTagName("world").item(i);
					
					if((!e.getAttribute("worldType").equals("SEWERS") || !Main.isVersionOlderThan(version, "0.2.0.5"))
							&& (!e.getAttribute("worldType").equals("SUBMISSION") || !Main.isVersionOlderThan(version, "0.2.1.5"))
							&& (!e.getAttribute("worldType").equals("DOMINION") || !Main.isVersionOlderThan(version, "0.2.2"))
							&& (!e.getAttribute("worldType").equals("SLAVER_ALLEY") || !Main.isVersionOlderThan(version, "0.2.2"))
							&& (!e.getAttribute("worldType").equals("HARPY_NEST") || !Main.isVersionOlderThan(version, "0.2.1.5"))
							&& (!e.getAttribute("worldType").equals("BAT_CAVERNS") || !Main.isVersionOlderThan(version, "0.2.3.5"))) {
						World world = World.loadFromXML(e, doc);
						Main.game.worlds.put(world.getWorldType(), world);
					}
					
				}
				
				// Add missing world types:
				for(WorldType wt : WorldType.values()) {
					Generation gen = new Generation();
					if(Main.isVersionOlderThan(version, "0.1.99.5")) {
						gen.worldGeneration(WorldType.SHOPPING_ARCADE);
					}
					if(Main.isVersionOlderThan(version, "0.2.1.5")) {
						gen.worldGeneration(WorldType.SUBMISSION);
						gen.worldGeneration(WorldType.DOMINION);
						gen.worldGeneration(WorldType.HARPY_NEST);
					}
					if(Main.isVersionOlderThan(version, "0.2.2")) {
						gen.worldGeneration(WorldType.DOMINION);
						gen.worldGeneration(WorldType.SLAVER_ALLEY);
					}
					if(Main.isVersionOlderThan(version, "0.2.3.5")) {
						gen.worldGeneration(WorldType.BAT_CAVERNS);
					}
					if(Main.game.worlds.get(wt)==null) {
						gen.worldGeneration(wt);
					}
				}

				if(Main.isVersionOlderThan(version, "0.2.4")) {
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
				
				List<String> addedIds = new ArrayList<>();
				List<NPC> slaveImports = new ArrayList<>();
				// Load NPCs:
				for(int i=0; i<gameElement.getElementsByTagName("NPC").getLength(); i++) {
					Element e = (Element) gameElement.getElementsByTagName("NPC").item(i);
					
					if(!addedIds.contains(((Element)e.getElementsByTagName("id").item(0)).getAttribute("value"))) {
						String className = ((Element)e.getElementsByTagName("pathName").item(0)).getAttribute("value");
						if(Main.isVersionOlderThan(version, "0.2.4")) {
							int lastIndex = className.lastIndexOf('.');
							if(className.substring(lastIndex-3, lastIndex).equals("npc")) {
								className = className.substring(0, lastIndex) + ".misc" + className.substring(lastIndex, className.length());
//								System.out.println(className);
							}
						}
						@SuppressWarnings("unchecked")
						Class<? extends NPC> npcClass = (Class<? extends NPC>) Class.forName(className);
						Method m = npcClass.getMethod("loadFromXML", Element.class, Document.class, CharacterImportSetting[].class);
						
						NPC npc = npcClass.getDeclaredConstructor(boolean.class).newInstance(true);
						m.invoke(npc, e, doc, new CharacterImportSetting[] {});
						Main.game.addNPC(npc, true);
						addedIds.add(npc.getId());
						
						// To fix issues with older versions hair length:
						if(Main.isVersionOlderThan(version, "0.1.90.5")) {
							npc.getBody().getHair().setLength(null, npc.isFeminine()?RacialBody.valueOfRace(npc.getRace()).getFemaleHairLength():RacialBody.valueOfRace(npc.getRace()).getMaleHairLength());
						}

						// Generate desires in non-unique NPCs:
						if(Main.isVersionOlderThan(version, "0.1.98.5") && !npc.isUnique() && npc.getFetishDesireMap().isEmpty()) {
							CharacterUtils.generateDesires(npc);
						}
						
						if(Main.isVersionOlderThan(version, "0.2.0") && npc.getFetishDesireMap().size()>10) {
							npc.clearFetishDesires();
							CharacterUtils.generateDesires(npc);
						}
						
						if(npc instanceof SlaveImport) {
							slaveImports.add(npc);
						}
					} else {
						System.err.println("duplicate character attempted to be imported");
					}
					if(debug) {
						System.out.println("NPC: "+i);
					}
				}

				if(debug) {
					System.out.println("NPCs finished");
				}
				
				// Add in new NPCS:
				if(!Main.game.NPCMap.containsKey(Main.game.getUniqueNPCId(Zaranix.class))) {
					Zaranix zaranix = new Zaranix();
					Main.game.addNPC(zaranix, false);
					
					ZaranixMaidKatherine katherine = new ZaranixMaidKatherine();
					Main.game.addNPC(katherine, false);
					
					ZaranixMaidKelly kelly = new ZaranixMaidKelly();
					Main.game.addNPC(kelly, false);
					
					Amber amber = new Amber();
					Main.game.addNPC(amber, false);
					
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
					
					Main.game.addNPC(new Arthur(), false);
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
				
				// To prevent errors from previous versions, reset Zaranix progress if prior to 0.1.95:
				if(Main.isVersionOlderThan(version, "0.1.90.5")) {
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
				
				if(Main.isVersionOlderThan(version, "0.1.95")) {
					if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
						Main.game.getArthur().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
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
		
		
		Main.game.setRenderMap(true);
		Main.game.setRenderAttributesSection(true);
		
		Main.game.started = true;
		
		DialogueNodeOld startingDialogueNode = Main.game.getPlayerCell().getPlace().getDialogue(false);
		Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "[style.colourGood(Game loaded)]", "data/saves/"+name+".xml"), false);
		Main.game.setContent(new Response(startingDialogueNode.getLabel(), startingDialogueNode.getDescription(), startingDialogueNode), false);
		
		Main.game.endTurn(0);
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// This is due to the fact that on new world creation, the player is placed at coordinates (0, 0), which reveals the three squares at the bottom left corner of the map:
		Main.game.getActiveWorld().getCell(0, 0).setDiscovered(false);
		Main.game.getActiveWorld().getCell(0, 1).setDiscovered(false);
		Main.game.getActiveWorld().getCell(1, 0).setDiscovered(false);
		
		started = true;

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
		
		if(Main.game.getCurrentWeather()!=Weather.SNOW && Main.game.getSeason()!=Season.WINTER) {
			Main.game.getDialogueFlags().values.remove(DialogueFlagValue.hasSnowedThisWinter);
			for(NPC npc : Main.game.getReindeerOverseers()) {
				if(npc.getLocation()!=Main.game.getPlayer().getLocation()) {
					npc.setLocation(WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE, true);
				}
			}
		}
		
		// Slavery: TODO
		int hoursPassed = (int) (getHour() - startHour);
		int hourStartTo24 = (int) (startHour%24);
		for(int i=1; i <= hoursPassed; i++) {
			slaveryUtil.performHourlyUpdate(this.getDayNumber(startHour*60 + i*60), (hourStartTo24+i)%24);
		}
		
		// If the time has passed midnight on this turn:
		boolean newDay = ((int) (minutesPassed / (60 * 24)) != (int) (((minutesPassed - turnTime) / (60 * 24))));
		
		if(newDay) {
			pendingSlaveInStocksReset = true;
			Main.game.getPlayer().resetDaysOrgasmCount();
			
			// Reindeer:
			for(NPC npc : Main.game.getReindeerOverseers()) {
				if(npc.getLocationPlace().getPlaceType()==PlaceType.DOMINION_STREET && !npc.getLocation().equals(Main.game.getPlayer().getLocation())) {
					npc.moveToAdjacentMatchingCellType();
					Main.game.getDialogueFlags().dailyReindeerReset(npc.getId());
				}
			}
		}
		
		if(pendingSlaveInStocksReset && Main.game.getPlayer().getLocationPlace().getPlaceType()!=PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS) {
			for(NPC npc : Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS))) {
				if(npc instanceof SlaveInStocks) {
					Main.game.banishNPC(npc);
				}
			}
			
			for(int i=0; i<4; i++) {
				SlaveInStocks slave = new SlaveInStocks(GenderPreference.getGenderFromUserPreferences());
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
					&& npc.getWorldLocation() == WorldType.DOMINION
					&& npc instanceof DominionAlleywayAttacker
					&& !Main.game.getPlayer().getLocation().equals(npc.getLocation())) {
						banishNPC(npc);
					}
			
			// Non-slave NPCs clean clothes:
			if((!npc.isSlave() || (npc.isSlave() && !npc.getOwner().isPlayer())) && !Main.game.getPlayer().getLocation().equals(npc.getLocation())) {
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
			if(!Main.game.isInCombat() && !Main.game.isInSex()
					&& (Main.game.getCurrentDialogueNode().equals(Main.game.getPlayer().getLocationPlace().getDialogue(false))
							|| !(npc.getWorldLocation()==Main.game.getPlayer().getWorldLocation() && npc.getLocation().equals(Main.game.getPlayer().getLocation())))) {
				if(npc.isPendingClothingDressing()) {
					if(!npc.hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						npc.replaceAllClothing();
					}
					npc.equipClothing(true, true);
					npc.setPendingClothingDressing(false);
					
				} else if((!npc.isSlave() && !npc.isUnique() && (npc.hasStatusEffect(StatusEffect.EXPOSED) || npc.hasStatusEffect(StatusEffect.EXPOSED_BREASTS) || npc.hasStatusEffect(StatusEffect.EXPOSED_PLUS_BREASTS)))){
					// Try to replace clothing to cover themselves up:
					if(!npc.hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						npc.replaceAllClothing();
					}
					npc.calculateStatusEffects(0);
					// If still exposed after this, get new clothes:
					if(npc.hasStatusEffect(StatusEffect.EXPOSED) || npc.hasStatusEffect(StatusEffect.EXPOSED_BREASTS) || npc.hasStatusEffect(StatusEffect.EXPOSED_PLUS_BREASTS)) {
						npc.equipClothing(true, true);
					}
					npc.setPendingClothingDressing(false);
				}
			}
			
			if(npc.isPendingTransformationToGenderIdentity()) {
				npc.setBody(npc.getGenderIdentity(), RacialBody.valueOfRace(npc.getRace()), npc.getRaceStage());
				CharacterUtils.randomiseBody(npc);
				npc.setPendingTransformationToGenderIdentity(false);
			}
			
			// Prostitutes stay on promiscuity pills to avoid pregnancies, and, if the NPC is male, to avoid knocking up their clients
			if((!npc.isPregnant()
					&& !npc.isSlave()
					&& npc.getHistory()==History.PROSTITUTE
					&& !npc.hasStatusEffect(StatusEffect.PROMISCUITY_PILL)
					&& !npc.getLocation().equals(Main.game.getPlayer().getLocation()))
					|| (npc.isSlave() && npc.getSlaveJobSettings().contains(SlaveJobSetting.SEX_PROMISCUITY_PILLS))) {
				npc.useItem(AbstractItemType.generateItem(ItemType.PROMISCUITY_PILL), npc, false);
			}
			
			if(npc.isSlave() && npc.getSlaveJobSettings().contains(SlaveJobSetting.SEX_VIXENS_VIRILITY)) {
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
						npc.getPlayerKnowsAreas().add(ca);
					}
				}
			}
			
			for(int i=1; i <= hoursPassed; i++) {
				npc.hourlyUpdate();
			}
			
			if(newDay) {
				npc.resetDaysOrgasmCount();
				npc.dailyReset();
			}
			
			// Companions:
			
			List<GameCharacter> companionsToRemove = new ArrayList<>();
			for(GameCharacter companion : npc.getCompanions()) {
				// Updating companion NPCs
				if(companion.isCompanionAvailable(npc)) {
					companion.setLocation(npc.getWorldLocation(), npc.getLocation(), false);
				} else {
					companionsToRemove.add(companion);
				}
			}
			for(GameCharacter character : companionsToRemove) {
				npc.removeCompanion(character);
				character.returnToHome();
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
		
		
		RenderingEngine.ENGINE.renderButtons();
		Main.mainController.updateUI();
		
		Main.mainController.getTooltip().hide();
		
		if(!Main.game.getPlayer().getStatusEffectDescriptions().isEmpty() && Main.game.getCurrentDialogueNode()!=MiscDialogue.STATUS_EFFECTS){
			if(Main.game.getCurrentDialogueNode().getMapDisplay()==MapDisplay.NORMAL) {
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
		
		List<GameCharacter> companionsToRemove = new ArrayList<>();
		for(GameCharacter npc : Main.game.getPlayer().getCompanions()) {
			// Updating companion NPCs
			if(npc.isCompanionAvailable(Main.game.getPlayer())) {
				npc.setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
			} else {
				companionsToRemove.add(npc);
				// TODO : Add NPCs leaving you to the report.
			}
		}
		for(GameCharacter character : companionsToRemove) {
			Main.game.getPlayer().removeCompanion(character);
			character.returnToHome();
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
						weatherTimeRemaining = 4 * 60 + Util.random.nextInt(2 * 60); // Gathering storm lasts for 4-6 hours
					} else {
						currentWeather = Weather.CLOUD;
						weatherTimeRemaining = 2 * 60 + Util.random.nextInt(2 * 60); // Clouds last for 2-4 hours
					}
					break;
					
				case CLOUD:
					if(minutesPassed >= nextStormTime) {
						currentWeather = Weather.MAGIC_STORM_GATHERING;
						weatherTimeRemaining = 4 * 60 + Util.random.nextInt(2 * 60); // Gathering storm lasts for 4-6 hours
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
						weatherTimeRemaining = 4 * 60 + Util.random.nextInt(2 * 60); // Gathering storm lasts for 4-6 hours
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
		long hours = (nextStormTime-minutesPassed)/60;
		return (hours/24)+" days, "+hours%24+" hours, "+(nextStormTime-minutesPassed)%60+" minutes";
	}
	
	public Weather getCurrentWeather() {
		return currentWeather;
	}

	/**
	 * Sets the content of the main WebView based on the response of the current
	 * Dialogue Node's index.
	 * 
	 * @param index
	 *            The dialogue choice index.
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
										Main.getProperties().addRaceDiscovered(character.getRace());
									}
									((NPC) character).setLastTimeEncountered(minutesPassed);
								}
							}
						}
					}
				}
				
				String headerContent = node.getHeaderContent();
				String content = node.getContent();

				if (currentDialogueNode != null) {
					if (node.isContinuesDialogue()) {
						if (!node.isNoTextForContinuesDialogue()) {
							if(Main.game.isInSex()) {
								dialogueTitle = UtilText.parse(node.getLabel());
							}

							if(node.isDisplaysActionTitleOnContinuesDialogue()) {
								if (currentDialogueNode.getMapDisplay() == MapDisplay.NORMAL) {
									positionAnchor++;
								}
							
								pastDialogueSB.append("<hr id='position" + positionAnchor + "'><p class='option-disabled'>&gt " + chosenResponse + "</p>");
							}
							
							if (getMapDisplay() == MapDisplay.NORMAL)
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
						
						if (getMapDisplay() == MapDisplay.NORMAL)
							initialPositionAnchor = positionAnchor;

						if (currentDialogueNode.getMapDisplay() == MapDisplay.NORMAL)
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
				
				
				if (node.isContinuesDialogue()) {
					currentDialogue = 
								(isContentScroll(node)
									?"<body onLoad='scrollToElement()'>"
										+ "<script>function scrollToElement() {document.getElementById('content-block').scrollTop = document.getElementById('position" + (positionAnchor) + "').offsetTop -64;}</script>"
									:"<body>")
								+ "<div id='main-content'>"
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
													? "<div "+(Main.getProperties().hasValue(PropertyValue.fadeInText)?"id='text-content'":"")+" style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;'>"
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
					currentDialogue = "<body>"
							+ "<div id='main-content'>"
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
												? "<div "+(Main.getProperties().hasValue(PropertyValue.fadeInText)?"id='text-content'":"")+" style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;'>"
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
				Main.mainController.setMainContent(currentDialogue);
				
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
		String content = node.getContent();
		boolean resetPointer = false;
		
		if (getMapDisplay() == MapDisplay.NORMAL) {
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
							Main.getProperties().addRaceDiscovered(character.getRace());
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
						if (currentDialogueNode.getMapDisplay() == MapDisplay.NORMAL) {
							positionAnchor++;
						}
						
						pastDialogueSB.append(UtilText.parse("<hr id='position" + positionAnchor + "'><p class='option-disabled'>&gt " + currentDialogueNode.getLabel() + "</p>"));
					}
					
					pastDialogueSB.append(content);
				}
			} else {
				dialogueTitle = UtilText.parse(node.getLabel());
				if (currentDialogueNode.getMapDisplay() == MapDisplay.NORMAL) {
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


		if (node.isContinuesDialogue()) {
			currentDialogue =
					(isContentScroll(node)
							?"<body onLoad='scrollToElement()'>"
								+ "<script>function scrollToElement() {document.getElementById('content-block').scrollTop = document.getElementById('position" + (positionAnchor) + "').offsetTop -64;}</script>"
							:"<body>")
					+ "<div id='main-content'>"
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
									? "<div "+(Main.getProperties().hasValue(PropertyValue.fadeInText)?"id='text-content'":"")+" style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;'>"
									+ pastDialogueSB.toString() + "</div>" : "")
	//									+ textStartStringBuilder.toString() + pastDialogueSB.toString() + textEndStringBuilder.toString() + "</div>" : "")
//							+ "</div>"
						+ "</div>"
						+"<div id='bottom-text'>Game saved!</div>"
						+ getResponsesDiv(currentDialogueNode, resetPointer)
					+ "</div>"
				+ "</body>";

		} else {
			currentDialogue =
					(isContentScroll(node)
						?"<body onLoad='scrollBack()'>"
								+ "<script>function scrollBack() {document.getElementById('content-block').scrollTop = "+currentPosition+";}</script>"
						:"<body>")
					+ "<div id='main-content'>"
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
										? "<div "+(Main.getProperties().hasValue(PropertyValue.fadeInText)?"id='text-content'":"")+" style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;'>"
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
		Main.mainController.setMainContent(currentDialogue);
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
	
	private static boolean isContentScroll(DialogueNodeOld node) {
		return (node.getMapDisplay()!=MapDisplay.CHARACTERS_PRESENT
				&& !node.equals(PhoneDialogue.CHARACTER_APPEARANCE)
				&& !node.equals(PhoneDialogue.CONTACTS_CHARACTER))
				|| node.equals(BodyChanging.BODY_CHANGING_ASS)
				|| node.equals(BodyChanging.BODY_CHANGING_BREASTS)
				|| node.equals(BodyChanging.BODY_CHANGING_CORE)
				|| node.equals(BodyChanging.BODY_CHANGING_FACE)
				|| node.equals(BodyChanging.BODY_CHANGING_PENIS)
				|| node.equals(BodyChanging.BODY_CHANGING_VAGINA);
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
	
	public void reloadContent() {
		reloadContent(null, null);
	}
	
	public void reloadContent(Colour colour, String messageString) {
		Main.mainController.setFlashMessageColour(colour);
		Main.mainController.setFlashMessageText(messageString);

		//-------------------- MEMORY LEAK PROBLEM
		Main.mainController.setMainContent(currentDialogue);
//		setResponses(currentDialogueNode, false);

		if(started) {
			Main.game.endTurn(0);
		}
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
	
	public void updateResponses() {
		String content = getResponsesDiv(Main.game.getCurrentDialogueNode(), false);
		content=content.replaceAll("\r", "");
		content=content.replaceAll("\n", "");
		content=content.replaceAll("\"", "'");
		Main.mainController.getWebEngine().executeScript("document.getElementById('RESPONSE_BOX').innerHTML = \""+content+"\"");
		Main.mainController.setResponseEventListeners();
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
								?"<b class='hotkey-icon'>"+ (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_PREVIOUS_TAB) == null ? "" : Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_PREVIOUS_TAB).getFullName()) + "</b>"
								:(responsePageCounter==responseTab+1
									?"<b class='hotkey-icon'>"+ (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_NEXT_TAB) == null ? "" : Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_NEXT_TAB).getFullName()) + "</b>"
									:""))
//							+ (responseTab==responsePageCounter+1?"<b class='hotkey-icon'>" + KeyboardAction.RESPOND_PREVIOUS_PAGE + "</b>" : "" )
							+ node.getResponseTabTitle(responsePageCounter)
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
		return "<body style='background:#1e1e20; color:#DDD; font-family:Calibri;'>"
				+ "<style>"
				+ ".speech:before { content: '\"'; }"
				+ ".speech:after { content: '\"'; }"
				+ "</style>"
					+ "<h4 style='text-align:center; font-size:1.4em;'>" + dialogueTitle + "</h4>"
					+ "<div style='max-width:800px; margin:0 auto;'>"
						+ (currentDialogueNode.getHeaderContent() != null ? "<div id='header-content'>" + currentDialogueNode.getHeaderContent() + "</div>" : "")
						+ (currentDialogueNode.getContent() != null ? "<div id='text-content' style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;'>"
								+ textStartStringBuilder.toString() + pastDialogueSB.toString() + textEndStringBuilder.toString() + "</div>" : "")
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
		String strippedTitle = UtilText.parse(response.getTitle()).replaceAll("<.*?>", "");
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
			int minIndex = ((responsePointer/5))*5;
			
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

	public void restoreSavedContent() {
		positionAnchor = initialPositionAnchor;
		dialogueTitle = UtilText.parse(savedDialogueNode.getLabel());
		
		currentDialogueNode = savedDialogueNode;
		
		if(Main.game.isInSex()) {
			Sex.recalculateSexActions();
		}
		//TODO
		if (currentDialogueNode.reloadOnRestore()) {
			String headerContent = currentDialogueNode.getHeaderContent();
			String content = currentDialogueNode.getContent();
			
			currentDialogue = 
					(savedDialogueNode.getMapDisplay()!=MapDisplay.PHONE && savedDialogueNode.getMapDisplay()!=MapDisplay.CHARACTERS_PRESENT
						?"<body onLoad='scrollToElement()'>"
							+ "<script>function scrollToElement() {document.getElementById('content-block').scrollTop = document.getElementById('position" + (positionAnchor) + "').offsetTop -64;}</script>"
						:"<body>")
					+ "<div id='main-content'>"
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
			
			
//			currentDialogue = "<body onLoad='scrollBack()'>"
//								+ " <script>function scrollBack() {"
//										+ "document.getElementById('content-block').scrollTop = document.getElementById('position" + (positionAnchor) + "').offsetTop;"
//								+ "}</script>"
//								+ "<div id='main-content'>"
//									+ getTitleDiv(dialogueTitle)
//									+ "<span id='position" + positionAnchor + "'></span>"
//										+ "<div class='div-center' id='content-block'>"
//											+ getMapDiv()
//											+ (headerContent != null
//												? "<div id='header-content' style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;-webkit-user-select: none;'>"
//													+ (currentDialogueNode.disableHeaderParsing() ? headerContent : UtilText.parse(headerContent))
//													+ "</div>"
//												: "")
//											+ (content != null
//												? "<div "+(Main.getProperties().fadeInText?"id='text-content'":"")+" style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;'>"
//														+ content
//													+ "</div>"
//												: "")
//										+ "</div>"
//									+"<div id='bottom-text'>Game saved!</div>"
//								+ "</div>"
//								+ getResponsesDiv(currentDialogueNode)
//							+ "</body>";
			
			
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

		Main.mainController.setMainContent(currentDialogue);

		textEndStringBuilder.setLength(0);
		textStartStringBuilder.setLength(0);
		
		Main.game.endTurn(0);
		//Main.mainController.processNewDialogue();

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
		nonCompanionCharactersPresent.removeIf((npc) -> Main.game.getPlayer().hasCompanion(npc));
		return nonCompanionCharactersPresent;
	}
	
	public List<NPC> getCharactersTreatingCellAsHome(Cell cell) {
		charactersHome.clear();
		
		for(NPC npc : NPCMap.values()) {
			if(npc.getHomeWorldLocation()==cell.getType() && npc.getHomeLocation().equals(cell.getLocation())) {
				charactersHome.add(npc);
			}
		}
		
		charactersHome.sort(Comparator.comparing(GameCharacter::getName));
		
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
		
		charactersPresent.sort(Comparator.comparing(GameCharacter::getName));
		
		return charactersPresent;
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
		
		if(Main.game.started) {
			Main.saveGame("AutoSave_"+Main.game.getPlayer().getName(), true);
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
	
	public int getYear() {
		return Main.game.getDateNow().getYear();
	}

	public long getHour() {
		return Main.game.getMinutesPassed() / 60l;
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

	public boolean isDebugMode() {
		return debugMode;
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}

	public boolean isImperialMeasurements() {
		return imperialMeasurements;
	}

	public void setImperialMeasurements(boolean imperialMeasurements) {
		this.imperialMeasurements = imperialMeasurements;
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
		return (NPC) this.getNPCById(getUniqueNPCId(PrologueMale.class));
	}

	public NPC getPrologueFemale() {
		return (NPC) this.getNPCById(getUniqueNPCId(PrologueFemale.class));
	}

	public NPC getTestNPC() {
		return (NPC) this.getNPCById(getUniqueNPCId(TestNPC.class));
	}

	public NPC getLilaya() {
		return (NPC) this.getNPCById(getUniqueNPCId(Lilaya.class));
	}

	public NPC getRose() {
		return (NPC) this.getNPCById(getUniqueNPCId(Rose.class));
	}

	public NPC getBrax() {
		return (NPC) this.getNPCById(getUniqueNPCId(Brax.class));
	}

//	public NPC getArthur() {
//		return arthur;
//	}

	public NPC getPix() {
		return (NPC) this.getNPCById(getUniqueNPCId(Pix.class));
	}

	public NPC getRalph() {
		return (NPC) this.getNPCById(getUniqueNPCId(Ralph.class));
	}

	public NPC getNyan() {
		return (NPC) this.getNPCById(getUniqueNPCId(Nyan.class));
	}

	public NPC getVicky() {
		return (NPC) this.getNPCById(getUniqueNPCId(Vicky.class));
	}

	public NPC getKate() {
		return (NPC) this.getNPCById(getUniqueNPCId(Kate.class));
	}

	public NPC getScarlett() {
		return (NPC) this.getNPCById(getUniqueNPCId(Scarlett.class));
	}
	
	public NPC getAlexa() {
		return (NPC) this.getNPCById(getUniqueNPCId(Alexa.class));
	}

	public NPC getHarpyBimbo() {
		return (NPC) this.getNPCById(getUniqueNPCId(HarpyBimbo.class));
	}

	public NPC getHarpyDominant() {
		return (NPC) this.getNPCById(getUniqueNPCId(HarpyDominant.class));
	}

	public NPC getHarpyNympho() {
		return (NPC) this.getNPCById(getUniqueNPCId(HarpyNympho.class));
	}

	public NPC getHarpyBimboCompanion() {
		return (NPC) this.getNPCById(getUniqueNPCId(HarpyBimboCompanion.class));
	}

	public NPC getHarpyDominantCompanion() {
		return (NPC) this.getNPCById(getUniqueNPCId(HarpyDominantCompanion.class));
	}

	public NPC getHarpyNymphoCompanion() {
		return (NPC) this.getNPCById(getUniqueNPCId(HarpyNymphoCompanion.class));
	}

	public NPC getPazu() {
		return (NPC) this.getNPCById(getUniqueNPCId(Pazu.class));
	}
	
	public NPC getCandi() {
		return (NPC) this.getNPCById(getUniqueNPCId(CandiReceptionist.class));
	}
	
	public NPC getFinch() {
		return (NPC) this.getNPCById(getUniqueNPCId(Finch.class));
	}
	
	public NPC getZaranix() {
		return (NPC) this.getNPCById(getUniqueNPCId(Zaranix.class));
	}
	
	public NPC getAmber() {
		return (NPC) this.getNPCById(getUniqueNPCId(Amber.class));
	}
	
	public NPC getArthur() {
		return (NPC) this.getNPCById(getUniqueNPCId(Arthur.class));
	}
	
	public NPC getKelly() {
		return (NPC) this.getNPCById(getUniqueNPCId(ZaranixMaidKelly.class));
	}
	
	public NPC getKatherine() {
		return (NPC) this.getNPCById(getUniqueNPCId(ZaranixMaidKatherine.class));
	}

	public NPC getAshley() {
		return (NPC) this.getNPCById(getUniqueNPCId(Ashley.class));
	}
	
	public NPC getSupplierLeader() {
		return (NPC) this.getNPCById(getUniqueNPCId(SupplierLeader.class));
	}
	
	public NPC getSupplierPartner() {
		return (NPC) this.getNPCById(getUniqueNPCId(SupplierPartner.class));
	}
	
	public NPC getAngel() {
		return (NPC) this.getNPCById(getUniqueNPCId(Angel.class));
	}
	
	public NPC getBunny() {
		return (NPC) this.getNPCById(getUniqueNPCId(Bunny.class));
	}
	
	public NPC getLoppy() {
		return (NPC) this.getNPCById(getUniqueNPCId(Loppy.class));
	}

	public NPC getGenericMaleNPC() {
		return (NPC) this.getNPCById(getUniqueNPCId(GenericMaleNPC.class));
	}

	public NPC getGenericFemaleNPC() {
		return (NPC) this.getNPCById(getUniqueNPCId(GenericFemaleNPC.class));
	}

	public NPC getGenericAndrogynousNPC() {
		return (NPC) this.getNPCById(getUniqueNPCId(GenericAndrogynousNPC.class));
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
	
	public GameCharacter getNPCById(String id) {
		if(id==null || id.isEmpty()) {
			return null;
		}
		
		if(id.equals(Main.game.getPlayer().getId())) {
			return Main.game.getPlayer();
		}
		if(!NPCMap.containsKey(id)) {
			System.err.println("!WARNING! getNPC("+id+") is returning null!");
			return null;
//			new NullPointerException().printStackTrace();
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
		return npcTally+","+c.getSimpleName();
	}
	
	public String getNextNPCId(Class<? extends NPC> c) {
		return (npcTally+1)+","+c.getSimpleName();
	}
	
	public String addNPC(NPC npc, boolean isImported) throws Exception {
		
		if(isImported) {
			int tallyCount = 0;
			// Support old versions (in the format "Stan-Stan-Stan-49"):
			String[] split = npc.getId().split("-");
			try{
				tallyCount = Integer.valueOf(split[split.length-1]);
			}catch(NumberFormatException ex) {
				tallyCount = Integer.valueOf(npc.getId().split(",")[0]);
			}
			if(tallyCount>npcTally) {
				npcTally = tallyCount;
			}
			
		} else {
			if(npc.isUnique()) {
				npc.setId(getUniqueNPCId(npc.getClass()));
			} else {
				npcTally++;
				npc.setId(getNPCId(npc.getClass()));
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
	 */
	public void banishNPC(NPC npc) {
		if(npc.getTotalTimesHadSex()!=0
				|| npc.getPregnantLitter()!=null
				|| npc.getLastLitterBirthed()!=null
				|| npc.getMother()!=null
				|| npc.getFather()!=null) {
			npc.setLocation(WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE, true);
		} else {
			removeNPC(npc);
		}
	}
	
	public void banishNPC(String id) {
		NPC npc = (NPC) getNPCById(id);
		if(npc.getTotalTimesHadSex()!=0 || npc.getPregnantLitter()!=null || npc.getLastLitterBirthed()!=null || npc.getMother()!=null || npc.getFather()!=null) {
			npc.setLocation(WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE, true);
		} else {
			removeNPC(npc);
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

	public MapDisplay getMapDisplay() {
		if (currentDialogueNode != null)
			return currentDialogueNode.getMapDisplay();
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
	
	public boolean isNonConEnabled() {
		return Main.getProperties().hasValue(PropertyValue.nonConContent);
	}
	
	public boolean isIncestEnabled() {
		return Main.getProperties().hasValue(PropertyValue.incestContent);
	}
	
	public boolean isFacialHairEnabled() {
		return Main.getProperties().hasValue(PropertyValue.facialHairContent);
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
		return npcTally;
	}

	public SlaveryUtil getSlaveryUtil() {
		return slaveryUtil;
	}
}
