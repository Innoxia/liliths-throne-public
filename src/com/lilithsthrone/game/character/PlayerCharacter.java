package com.lilithsthrone.game.character;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.TorsoType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.npc.misc.NPCOffspring;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.quests.QuestType;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueFlags;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntry;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.ShopTransaction;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.CondomFailure;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.submission.SMLyssiethDemonTF;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionOrgasmOverride;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.SizedStack;
import com.lilithsthrone.utils.TreeNode;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.7.7
 * @author Innoxia
 */
public class PlayerCharacter extends GameCharacter implements XMLSaving {
	
	private String title;
	
	private int karma;

	private Map<QuestLine, List<Quest>> quests;
	private Map<QuestLine, Quest> questsFailed;

	private boolean mainQuestUpdated;
	private boolean sideQuestUpdated;
	private boolean relationshipQuestUpdated;

	private boolean isActive;

	protected List<String> friendlyOccupants;
	
	//Discoveries:
	private List<String> charactersEncountered;
	private Set<AbstractWorldType> worldsVisited;
	
	private Set<AbstractSubspecies> racesDiscoveredFromBook;
	
	private Set<AbstractItemType> itemsDiscovered;
	private Set<AbstractWeaponType> weaponsDiscovered;
	private Set<AbstractClothingType> clothingDiscovered;
	private Set<AbstractSubspecies> subspeciesDiscovered;
	private Set<AbstractSubspecies> subspeciesAdvancedKnowledge;
	
	// Trader buy-back:
	private SizedStack<ShopTransaction> buybackStack;

	
	public PlayerCharacter(NameTriplet nameTriplet, int level, LocalDateTime birthday, Gender gender, AbstractSubspecies startingSubspecies, RaceStage stage, AbstractWorldType startingWorld, AbstractPlaceType startingPlace) {
		super(nameTriplet, "", "", level, Main.game.getDateNow().minusYears(22), gender, startingSubspecies, stage, new CharacterInventory(0), startingWorld, startingPlace);

		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		
		title = "The Human";
		
		karma = 0;
		
		this.setMaxCompanions(1);
		
		quests = new HashMap<>();
		questsFailed = new HashMap<>();

		mainQuestUpdated = false;
		sideQuestUpdated = false;
		relationshipQuestUpdated = false;

		isActive = true;

		racesDiscoveredFromBook = new HashSet<>();

		itemsDiscovered = new HashSet<>();
		weaponsDiscovered = new HashSet<>();
		clothingDiscovered = new HashSet<>();
		subspeciesDiscovered = new HashSet<>();
		subspeciesAdvancedKnowledge = new HashSet<>();
		
		buybackStack = new SizedStack<>(24);

		charactersEncountered = new ArrayList<>();

		friendlyOccupants = new ArrayList<>();
		
		worldsVisited = new HashSet<>();
		
		this.setAttribute(Attribute.MAJOR_PHYSIQUE, 0f, false);
		this.setAttribute(Attribute.MAJOR_ARCANE, 0f, false);
		this.setAttribute(Attribute.MAJOR_CORRUPTION, 0f, false);
		
		equipBasicCombatMoves();
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element playerElement = super.saveAsXML(parentElement, doc);
		
		Element playerSpecific = doc.createElement("playerSpecific");
		playerElement.appendChild(playerSpecific);
		
		XMLUtil.createXMLElementWithValue(doc, playerSpecific, "title", this.getTitle());
		XMLUtil.createXMLElementWithValue(doc, playerSpecific, "karma", String.valueOf(this.getKarma()));
		
		Element questUpdatesElement = doc.createElement("questUpdates");
		playerSpecific.appendChild(questUpdatesElement);
		XMLUtil.createXMLElementWithValue(doc, playerSpecific, "mainQuestUpdated", String.valueOf(this.mainQuestUpdated));
		XMLUtil.createXMLElementWithValue(doc, playerSpecific, "sideQuestUpdated", String.valueOf(this.sideQuestUpdated));
		XMLUtil.createXMLElementWithValue(doc, playerSpecific, "relationshipQuestUpdated", String.valueOf(this.relationshipQuestUpdated));
		
		Element innerElement = doc.createElement("raceBooksDiscovered");
		playerSpecific.appendChild(innerElement);
		for(AbstractSubspecies subspecies : racesDiscoveredFromBook) {
			if(subspecies != null) {
				Element e = doc.createElement("race");
				innerElement.appendChild(e);
				e.setTextContent(Subspecies.getIdFromSubspecies(subspecies));
			}
		}
		
		Element charactersEncounteredElement = doc.createElement("charactersEncountered");
		playerSpecific.appendChild(charactersEncounteredElement);
		for(String id : charactersEncountered) {
			XMLUtil.createXMLElementWithValue(doc, charactersEncounteredElement, "id", id);
		}
		
		innerElement = doc.createElement("questMap");
		playerSpecific.appendChild(innerElement);
		for(Entry<QuestLine, List<Quest>> entry : quests.entrySet()) {
			Element e = doc.createElement("entry");
			innerElement.appendChild(e);
			XMLUtil.addAttribute(doc, e, "questLine", entry.getKey().toString());
			for(int i=0; i<entry.getValue().size(); i++) {
				XMLUtil.addAttribute(doc, e, "q"+i, String.valueOf(entry.getValue().get(i)));
			}
		}

		innerElement = doc.createElement("questFailedMap");
		playerSpecific.appendChild(innerElement);
		for(Entry<QuestLine, Quest> entry : questsFailed.entrySet()) {
			Element e = doc.createElement("entry");
			innerElement.appendChild(e);
			XMLUtil.addAttribute(doc, e, "questLine", entry.getKey().toString());
			XMLUtil.addAttribute(doc, e, "q", String.valueOf(entry.getValue()));
		}
		
		Element friendlyOccupants = doc.createElement("friendlyOccupants");
		playerSpecific.appendChild(friendlyOccupants);
		for(String occupant : this.getFriendlyOccupants()) {
			Element element = doc.createElement("occupant");
			friendlyOccupants.appendChild(element);
			
			XMLUtil.addAttribute(doc, element, "id", occupant);
		}
		
		Element worldsVisitedElement = doc.createElement("worldsVisited");
		playerSpecific.appendChild(worldsVisitedElement);
		for(AbstractWorldType world : this.getWorldsVisited()) {
			Element element = doc.createElement("world");
			worldsVisitedElement.appendChild(element);
			
			XMLUtil.addAttribute(doc, element, "id", WorldType.getIdFromWorldType(world));
		}

		// Discoveries:
		Element itemsDiscovered = doc.createElement("itemsDiscovered");
		playerSpecific.appendChild(itemsDiscovered);
		for (AbstractItemType itemType : this.itemsDiscovered) {
			try {
				if(itemType!=null) {
					Element element = doc.createElement("type");
					itemsDiscovered.appendChild(element);
					element.setTextContent(itemType.getId());
				}
			} catch(Exception ex) {
				// Catch errors from modded items being removed
			}
		}
		
		Element weaponsDiscovered = doc.createElement("weaponsDiscovered");
		playerSpecific.appendChild(weaponsDiscovered);
		for (AbstractWeaponType weaponType : this.weaponsDiscovered) {
			try {
				if(weaponType!=null) {
					Element element = doc.createElement("type");
					weaponsDiscovered.appendChild(element);
					element.setTextContent(weaponType.getId());
				}
			} catch(Exception ex) {
				// Catch errors from modded weapons being removed
			}
		}
		
		Element clothingDiscovered = doc.createElement("clothingDiscovered");
		playerSpecific.appendChild(clothingDiscovered);
		for (AbstractClothingType clothingType : this.clothingDiscovered) {
			try {
				if(clothingType!=null) {
					Element element = doc.createElement("type");
					clothingDiscovered.appendChild(element);
					element.setTextContent(clothingType.getId());
				}
			} catch(Exception ex) {
				// Catch errors from modded items being removed
			}
		}
		
		Element racesDiscovered = doc.createElement("racesDiscovered");
		playerSpecific.appendChild(racesDiscovered);
		for(AbstractSubspecies subspecies : this.subspeciesDiscovered) {
			if(!this.subspeciesAdvancedKnowledge.contains(subspecies)) {
				Element element = doc.createElement("race");
				racesDiscovered.appendChild(element);
				element.setTextContent(Subspecies.getIdFromSubspecies(subspecies));
			}
		}
		Element racesDiscoveredAdvanced = doc.createElement("racesDiscoveredAdvanced");
		playerSpecific.appendChild(racesDiscoveredAdvanced);
		for(AbstractSubspecies subspecies : this.subspeciesAdvancedKnowledge) {
			Element element = doc.createElement("race");
			racesDiscoveredAdvanced.appendChild(element);
			element.setTextContent(Subspecies.getIdFromSubspecies(subspecies));
		}
		
		
//		private SizedStack<ShopTransaction> buybackStack; TODO
		
//		Element slavesOwned = doc.createElement("slavesExported");
//		properties.appendChild(slavesOwned);
//		for(String id : this.getSlavesOwned()) {
//			Main.game.getNPCById(id).saveAsXML(slavesOwned, doc);
//		}
		
		return playerElement;
	}
	
	private static boolean debug = false;
	
	public static PlayerCharacter loadFromXML(StringBuilder log, Element parentElement, Document doc, CharacterImportSetting... settings) {
		long time = System.nanoTime();
		if(debug) {
			System.out.println("Player loading start");
		}
		
		PlayerCharacter character = new PlayerCharacter(new NameTriplet(""), 0, null, Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.HUMAN, WorldType.DOMINION, PlaceType.DOMINION_AUNTS_HOME);

		if(debug) {
			System.out.println("character created: "+((System.nanoTime()-time)/1000000000d));
		}
		
		GameCharacter.loadGameCharacterVariablesFromXML(character, log, parentElement, doc, settings);

		if(debug) {
			System.out.println("Variables loaded: "+((System.nanoTime()-time)/1000000000d));
		}

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.6")) {
			character.setGenderIdentity(character.getGender());
		}
		
		character.sortInventory();
		
		boolean newGameImport = Arrays.asList(settings).contains(CharacterImportSetting.NEW_GAME_IMPORT);
		
		NodeList nodes = parentElement.getElementsByTagName("core");
		Element element = (Element) nodes.item(0);
		String version = "";
		if(element.getElementsByTagName("version").item(0)!=null) {
			version = ((Element) element.getElementsByTagName("version").item(0)).getAttribute("value");
		}
		
		Element playerSpecificElement = (Element) parentElement.getElementsByTagName("playerSpecific").item(0);
		
		if(newGameImport) {
			character.setLocation(WorldType.MUSEUM_LOST, PlaceType.MUSEUM_MIRROR);
		}
		
		if(!newGameImport) {
			if(playerSpecificElement!=null) {
				if(playerSpecificElement.getElementsByTagName("title").getLength()!=0) {
					character.setTitle(((Element)playerSpecificElement.getElementsByTagName("title").item(0)).getAttribute("value"));
				}
				
				if(playerSpecificElement.getElementsByTagName("karma").getLength()!=0) {
					character.setKarma(Integer.valueOf(((Element)playerSpecificElement.getElementsByTagName("karma").item(0)).getAttribute("value")));
				}
				
				if(playerSpecificElement.getElementsByTagName("mainQuestUpdated").getLength()!=0) {
					character.setMainQuestUpdated(Boolean.valueOf(((Element)playerSpecificElement.getElementsByTagName("mainQuestUpdated").item(0)).getAttribute("value")));
				}
				if(playerSpecificElement.getElementsByTagName("sideQuestUpdated").getLength()!=0) {
					character.setSideQuestUpdated(Boolean.valueOf(((Element)playerSpecificElement.getElementsByTagName("sideQuestUpdated").item(0)).getAttribute("value")));
				}
				if(playerSpecificElement.getElementsByTagName("relationshipQuestUpdated").getLength()!=0) {
					character.setRelationshipQuestUpdated(Boolean.valueOf(((Element)playerSpecificElement.getElementsByTagName("relationshipQuestUpdated").item(0)).getAttribute("value")));
				}
		
				try {
					if(Main.isVersionOlderThan(version, "0.3.7.7")) {
						Element racesDiscoveredElement = (Element) playerSpecificElement.getElementsByTagName("racesDiscovered").item(0);
						if(racesDiscoveredElement != null) {
							NodeList races = racesDiscoveredElement.getElementsByTagName("race");
							for(int i=0; i < races.getLength(); i++){
								Element e = (Element) races.item(i);
								try {
									character.addRaceDiscoveredFromBook(Subspecies.getSubspeciesFromId(e.getAttribute("value")));
								} catch(Exception ex) {
								}
							}
						}
					} else {
						Element racesDiscoveredElement = (Element) playerSpecificElement.getElementsByTagName("raceBooksDiscovered").item(0);
						if(racesDiscoveredElement != null) {
							NodeList races = racesDiscoveredElement.getElementsByTagName("race");
							for(int i=0; i < races.getLength(); i++){
								Element e = (Element) races.item(i);
								try {
									character.addRaceDiscoveredFromBook(Subspecies.getSubspeciesFromId(e.getTextContent()));
								} catch(Exception ex) {
								}
							}
						}
					}
				} catch(Exception ex) {
				}
				
				Element charactersEncounteredElement = (Element) playerSpecificElement.getElementsByTagName("charactersEncountered").item(0);
				if(charactersEncounteredElement != null) {
					NodeList charactersEncounteredIds = charactersEncounteredElement.getElementsByTagName("id");
					for(int i=0; i<charactersEncounteredIds.getLength(); i++){
						Element e = (Element) charactersEncounteredIds.item(i);
						String id = e.getAttribute("value");
						if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.9")) {
							id = id.replaceAll("Alexa", "Helena");
						}
						character.addCharacterEncountered(id);
					}
				}
				
				Element questMapElement = (Element) playerSpecificElement.getElementsByTagName("questMap").item(0);
				if(questMapElement!=null) {
					NodeList questMapEntries = questMapElement.getElementsByTagName("entry");
					if(Main.isVersionOlderThan(version, "0.1.99.5")) {
						for(int i=0; i< questMapEntries.getLength(); i++){
							Element e = (Element) questMapEntries.item(i);
							
							try {
								int progress = Integer.valueOf(e.getAttribute("progress"));
								QuestLine questLine = QuestLine.valueOf(e.getAttribute("questLine"));
								TreeNode<Quest> q = questLine.getQuestTree();
								
								for(int it=0;it<progress;it++) {
									if(!q.getChildren().isEmpty()) {
										q = q.getChildren().get(0);
									}
								}
								
								Quest quest = q.getData();
								List<Quest> questList = new ArrayList<>();
								TreeNode<Quest> node = questLine.getQuestTree().getFirstNodeWithData(quest);
								
								while(node!=null) {
									questList.add(node.getData());
									node = node.getParent();
								}
								Collections.reverse(questList);
								
								character.quests.put(
										questLine,
										questList);
								
							} catch(Exception ex) {
								System.err.println("ERR Quest!");
							}
						}
						
					} else if(Main.isVersionOlderThan(version, "0.3.5.3")) {
						for(int i=0; i<questMapEntries.getLength(); i++){
							Element e = (Element) questMapEntries.item(i);
							try {
								String questLineString = e.getAttribute("questLine");
								if(questLineString.contains("SIDE_NYAN")) {
									questLineString = questLineString.replace("SIDE_NYAN", "RELATIONSHIP_NYAN");
								}
								
								String questString = e.getAttribute("quest");
								if(questString.contains("SIDE_NYAN")) {
									questString = questString.replace("SIDE_NYAN", "RELATIONSHIP_NYAN");
								}
								if(questString.equals("MAIN_1_E_REPORT_TO_ALEXA")) {
									questString = "MAIN_1_E_REPORT_TO_HELENA";
								}
								
								QuestLine questLine = QuestLine.valueOf(questLineString);
								Quest quest = Quest.getQuestFromId(questString);
								
								List<Quest> questList = new ArrayList<>();
								TreeNode<Quest> node = questLine.getQuestTree().getFirstNodeWithData(quest);
								
								while(node!=null) {
									questList.add(node.getData());
									node = node.getParent();
								}
								Collections.reverse(questList);
								
								character.quests.put(
										questLine,
										questList);
							} catch(Exception ex) {
							}
						}
						
					} else {
						for(int i=0; i<questMapEntries.getLength(); i++){
							Element e = (Element) questMapEntries.item(i);
							String questLineString = e.getAttribute("questLine");
							QuestLine questLine = QuestLine.valueOf(questLineString);
							String questString = e.getAttribute("q"+0);
							if(questString.equals("MAIN_1_E_REPORT_TO_ALEXA")) {
								questString = "MAIN_1_E_REPORT_TO_HELENA";
							}
							if(!version.isEmpty() && Main.isVersionOlderThan(version, "0.3.14") && questString.startsWith("RELATIONSHIP_NYAN")) {
								continue;
							}
							try {
								Quest quest = Quest.getQuestFromId(questString);
								List<Quest> questList = new ArrayList<>();
								
								int questIncrement=0;
								while(!questString.isEmpty()) {
									quest = Quest.getQuestFromId(questString);
									if(quest!=Quest.MAIN_1_J_ARTHURS_ROOM) {
										questList.add(quest);
									}
									
									questIncrement++;
									questString = e.getAttribute("q"+questIncrement);
									if(questString.equals("MAIN_1_E_REPORT_TO_ALEXA")) {
										questString = "MAIN_1_E_REPORT_TO_HELENA";
									}
								}
								
								character.quests.put(
										questLine,
										questList);
							} catch(Exception ex) {
								System.out.println("Error in PlayerCharacter loading: QuestLine failed to load: "+questString);
								ex.printStackTrace();
							}
						}
					}
				}
				
				// Failed quests:
				questMapElement = (Element) playerSpecificElement.getElementsByTagName("questFailedMap").item(0);
				if(questMapElement!=null) {
					NodeList questMapEntries = questMapElement.getElementsByTagName("entry");
					for(int i=0; i<questMapEntries.getLength(); i++){
						Element e = (Element) questMapEntries.item(i);
						String questLineString = e.getAttribute("questLine");
						QuestLine questLine = QuestLine.valueOf(questLineString);
						String questString = e.getAttribute("q");
						Quest quest = Quest.getQuestFromId(questString);
						character.questsFailed.put(
								questLine,
								quest);
					}
				}
			}
			
			try {
				for(int i=0; i<((Element) playerSpecificElement.getElementsByTagName("friendlyOccupants").item(0)).getElementsByTagName("occupant").getLength(); i++){
					Element e = ((Element)playerSpecificElement.getElementsByTagName("occupant").item(i));
					
					if(!e.getAttribute("id").equals("NOT_SET")) {
						character.getFriendlyOccupants().add(e.getAttribute("id"));
						Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added occupant: "+e.getAttribute("id"));
					}
				}
			} catch(Exception ex) {	
			}
			
			try {
				for(int i=0; i<((Element) playerSpecificElement.getElementsByTagName("worldsVisited").item(0)).getElementsByTagName("world").getLength(); i++){
					Element e = ((Element)playerSpecificElement.getElementsByTagName("world").item(i));
					
					character.getWorldsVisited().add(WorldType.getWorldTypeFromId(e.getAttribute("id")));
					Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added world visited: "+e.getAttribute("id"));
				}
			} catch(Exception ex) {	
			}
		}

		if(debug) {
			System.out.println("Initial loading: "+((System.nanoTime()-time)/1000000000d));
		}
		
		if(playerSpecificElement!=null && !Main.isVersionOlderThan(version, "0.3.7.7")) {
			nodes = playerSpecificElement.getElementsByTagName("itemsDiscovered");
			element = (Element) nodes.item(0);
			nodes = element.getElementsByTagName("type");
			if(element!=null && nodes!=null) {
				for(int i=0; i<nodes.getLength(); i++){
					Element e = ((Element)nodes.item(i));
					character.itemsDiscovered.add(ItemType.getItemTypeFromId(e.getTextContent()));
				}
			}
			
			nodes = playerSpecificElement.getElementsByTagName("weaponsDiscovered");
			element = (Element) nodes.item(0);
			nodes = element.getElementsByTagName("type");
			if(element!=null && nodes!=null) {
				for(int i=0; i<nodes.getLength(); i++){
					Element e = ((Element)nodes.item(i));
					character.weaponsDiscovered.add(WeaponType.getWeaponTypeFromId(e.getTextContent()));
				}
			}
			
			nodes = playerSpecificElement.getElementsByTagName("clothingDiscovered");
			element = (Element) nodes.item(0);
			nodes = element.getElementsByTagName("type");
			if(element!=null && nodes!=null) {
				for(int i=0; i<nodes.getLength(); i++){
					Element e = ((Element)nodes.item(i));
					character.clothingDiscovered.add(ClothingType.getClothingTypeFromId(e.getTextContent()));
				}
			}
			
			nodes = playerSpecificElement.getElementsByTagName("racesDiscovered");
			element = (Element) nodes.item(0);
			NodeList races = element.getElementsByTagName("race");
			if(element!=null && races!=null) {
				for(int i=0; i<races.getLength(); i++){
					Element e = ((Element)races.item(i));
					try {
						character.subspeciesDiscovered.add(Subspecies.getSubspeciesFromId(e.getTextContent()));
					} catch(Exception ex) {
					}
				}
			}
			nodes = playerSpecificElement.getElementsByTagName("racesDiscoveredAdvanced");
			element = (Element) nodes.item(0);
			races = element.getElementsByTagName("race");
			if(element!=null && races!=null) {
				for(int i=0; i<races.getLength(); i++){
					Element e = ((Element)races.item(i));
					try {
						character.subspeciesDiscovered.add(Subspecies.getSubspeciesFromId(e.getTextContent()));
						character.subspeciesAdvancedKnowledge.add(Subspecies.getSubspeciesFromId(e.getTextContent()));
					} catch(Exception ex) {
					}
				}
			}
		}

		if(debug) {
			System.out.println("encyclopedia loading: "+((System.nanoTime()-time)/1000000000d));
		}
		
		if(Main.isVersionOlderThan(version, "0.3.0.5")) {
			// Reset player's demon parts to human if prior to 0.3.0.5:
			if(character.getArmType().getRace()==Race.DEMON) {
				character.setArmType(ArmType.HUMAN);
			}
			if(character.getAssType().getRace()==Race.DEMON) {
				character.setAssType(AssType.HUMAN);
			}
			if(character.getBreastType().getRace()==Race.DEMON) {
				character.setBreastType(BreastType.HUMAN);
			}
			if(character.getEarType().getRace()==Race.DEMON) {
				character.setEarType(EarType.HUMAN);
			}
			if(character.getEyeType().getRace()==Race.DEMON) {
				character.setEyeType(EyeType.HUMAN);
			}
			if(character.getFaceType().getRace()==Race.DEMON) {
				character.setFaceType(FaceType.HUMAN);
			}
			if(character.getHairType().getRace()==Race.DEMON) {
				character.setHairType(HairType.HUMAN);
			}
			if(character.getHornType().getRace()==Race.DEMON) {
				character.setHornType(HornType.NONE);
			}
			if(character.getLegType().getRace()==Race.DEMON) {
				character.setLegType(LegType.HUMAN);
			}
			if(character.getPenisType().getRace()==Race.DEMON) {
				character.setPenisType(PenisType.HUMAN);
			}
			if(character.getTorsoType().getRace()==Race.DEMON) {
				character.setTorsoType(TorsoType.HUMAN);
			}
			if(character.getTailType().getRace()==Race.DEMON) {
				character.setTailType(TailType.NONE);
			}
			if(character.getVaginaType().getRace()==Race.DEMON) {
				character.setVaginaType(VaginaType.HUMAN);
			}
			if(character.getWingType().getRace()==Race.DEMON) {
				character.setWingType(WingType.NONE);
			}
			character.setSubspeciesOverride(null);
			character.getBody().calculateRace(character);
		}

		if(Main.isVersionOlderThan(version, "0.3.3.5")) {
			character.equipBasicCombatMoves();
		}
		
		if(Main.isVersionOlderThan(version, "0.3.4")) {
			character.ageAppearanceDifference = -Game.TIME_SKIP_YEARS;
		}
		
		if(Main.isVersionOlderThan(version, "0.3.7.9") && character.hasQuest(QuestLine.ROMANCE_NATALYA)) {
			character.removeQuest(QuestLine.ROMANCE_NATALYA);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaVisited, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaInterviewOffered, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaBusy, false);
		}

		if(Main.isVersionOlderThan(version, "0.3.8") && character.isHasSlaverLicense()) {
			character.addItem(Main.game.getItemGen().generateItem(ItemType.SLAVER_LICENSE), false);
		}
		
		if(Main.isVersionOlderThan(version, "0.3.8.1")) {
			if(character.hasItemType(ItemType.NATALYA_BUSINESS_CARD_STAMPED)) {
				character.removeItemByType(ItemType.NATALYA_BUSINESS_CARD);
				
			} else if(character.isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR)
					&& !character.hasItemType(ItemType.NATALYA_BUSINESS_CARD)
					&& !character.hasItemType(ItemType.NATALYA_BUSINESS_CARD_STAMPED)) {
				character.addItem(Main.game.getItemGen().generateItem(ItemType.NATALYA_BUSINESS_CARD), false);
			}
		}
		
		if(Main.isVersionOlderThan(version, "0.4.1.8")) {
			if(!character.hasItemType("innoxia_quest_clothing_keys")) {
				character.addItem(Main.game.getItemGen().generateItem("innoxia_quest_clothing_keys"), false);
			}
		}

		if(Main.isVersionOlderThan(version, "0.4.4.2")) {
			if(character.getQuest(QuestLine.MAIN)==Quest.MAIN_1_J_ARTHURS_ROOM) {
				character.setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_I_ARTHURS_TALE);
			}
		}
		
		if(debug) {
			System.out.println("Player loading finished: "+((System.nanoTime()-time)/1000000000d));
		}
		
		return character;
	}

	@Override
	public void updateAttributeListeners(boolean requiresStatusEffectUpdate) {
		if (playerAttributeChangeEventListeners != null) {
			for (CharacterChangeEventListener eventListener : playerAttributeChangeEventListeners) {
				eventListener.onChange();
			}
		}
		if(requiresStatusEffectUpdate) {
//			if(Main.game.isStarted() && this.getLocationPlace().getPlaceType()==PlaceType.SHOPPING_ARCADE_GENERIC_SHOP) {
//				throw new IllegalArgumentException();
//			}
			requiresAttributeStatusEffectCheck = true;
		}
	}

	@Override
	protected void updateLocationListeners() {
		if (playerLocationChangeEventListeners != null)
			for (CharacterChangeEventListener eventListener : playerLocationChangeEventListeners)
				eventListener.onChange();
	}

	@Override
	public void updateInventoryListeners() {
		if (playerInventoryChangeEventListeners != null) {
			for (CharacterChangeEventListener eventListener : playerInventoryChangeEventListeners) {
				eventListener.onChange();
			}
		}
		requiresInventoryStatusEffectCheck = true;
	}
	
	@Override
	public String getId() {
		return "PlayerCharacter";//-"+Main.game.getNpcTally();
	}
	
	@Override
	public boolean isPlayer() {
		return true;
	}
	
	@Override
	public String getBodyDescription() {
		return body.getDescription(this);
	}

	@Override
	public String getDescription() {
		if(!Main.game.isInNewWorld()) {
			return ""; // This isn't displayed anywhere before the game starts for real.
		} else {
			if(description==null || description.isEmpty()) {
				return "Having been pulled into an enchanted mirror in your aunt Lily's museum, you woke up to find yourself in another world."
					+ " By a stroke of good fortune, one of the first people you met was Lilaya; this world's version of your aunt."
					+ " Having convinced her that your story is true, you're now working towards finding a way to get back to your old world.";
			} else {
				return UtilText.parse(this, description);
			}
		}
	}
	
	@Override
	public void setLocation(AbstractWorldType worldLocation, Vector2i location, boolean setAsHomeLocation) {
		if(this.getWorldsVisited()!=null && !this.getWorldsVisited().contains(worldLocation)) {
			this.getWorldsVisited().add(worldLocation);
			if(Main.game.isStarted()) {
				Main.game.addEvent(new EventLogEntry("[style.colourExcellent(Discovered)]", Util.capitaliseSentence(worldLocation.getName())), false);
			}
		}
		
		if(Main.game.isStarted() && worldLocation!=this.getWorldLocation()) {
			Main.game.addEvent(new EventLogEntry("[style.colourMinorGood(Entered)]", Util.capitaliseSentence(worldLocation.getName())), false);
		}

		if(Main.game.isStarted() && Main.game.getActiveWorld().getCell(this.getLocation()).getPlace().isItemsDisappear()) {
			Main.game.getActiveWorld().getCell(this.getLocation()).resetInventory(Util.newArrayListOfValues(Rarity.LEGENDARY, Rarity.QUEST));
		}
		
		if(this.getWorldLocation()==WorldType.NIGHTLIFE_CLUB) {
			List<GameCharacter> clubbers = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
//			clubbers.removeIf((npc) -> !(npc instanceof DominionClubNPC));
			clubbers.removeIf((npc) -> npc.isUnique());
			
			AbstractWorldType worldLocationInitial = this.getWorldLocation();
			Vector2i locationInitial = this.getLocation();
			
			super.setLocation(worldLocation, location, setAsHomeLocation);
			
			for(GameCharacter clubber : clubbers) {
				clubber.setLocation(this, false);
				// TODO Why is this needed? I can't figure out why IDs are not being removed without this line:
				if(worldLocation!=worldLocationInitial || !location.equals(locationInitial)) {
					Main.game.getWorlds().get(worldLocationInitial).getCell(locationInitial).removeCharacterPresentId(clubber.getId());
				}
			}
			
		} else {
			super.setLocation(worldLocation, location, setAsHomeLocation);
		}
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPetName(GameCharacter target) {
		if(target instanceof Lyssieth && this.getRace()==Race.DEMON) {
			if(this.hasFetish(Fetish.FETISH_INCEST)) {
				return "mommy";
			} else {
				return "mother";
			}
		}
		return super.getPetName(target);
	}

	public int getKarma() {
		return karma;
	}

	public void setKarma(int karma) {
		this.karma = karma;
	}
	
	/**
	 * This is just an internal stat that isn't used for anything, other than to help me feel better about writing horrible scenes.<br/><br/>
	 * 
	 * -100 would be for something huge, like attacking and enslaving one of your children.<br/>
	 * -10 would be for something large, like stealing from someone.<br/>
	 * -1 would be for something small, like insulting someone who doesn't deserve it.<br/>
	 * 0 = neutral<br/>
	 * +1 would be for something small, like giving a gift.<br/>
	 * +10 would be for something large, like sacrificing your own well-being to help another person.<br/>
	 * +100 would be for something huge, like buying and then immediately freeing a slave.<br/>
	 * @param increment
	 */
	public void incrementKarma(int increment) {
		this.karma += increment;
	}
	
	@Override
	public Set<Relationship> getRelationshipsTo(GameCharacter character, Relationship... excludedRelationships) {
		if(character instanceof Lilaya) {
			Set<Relationship> rSet = new LinkedHashSet<>();
			rSet.add(Relationship.Nibling);
			if(Main.game.getDialogueFlags().hasFlag("innoxia_child_of_lyssieth")) {
				rSet.add(Relationship.HalfSibling);
			}
			return rSet;
		}
		if(Main.game.getDialogueFlags().hasFlag("innoxia_child_of_lyssieth")) {
			if(character instanceof Lyssieth) {
				return Util.newHashSetOfValues(Relationship.Parent);
			}
			if(character instanceof DarkSiren) {
				return Util.newHashSetOfValues(Relationship.HalfSibling);
			}
		}
		return super.getRelationshipsTo(character, excludedRelationships);
	}

	public GameCharacter getLilinMother(){
		DialogueFlags dialogueFlags = Main.game.getDialogueFlags();
		if(dialogueFlags.hasFlag("innoxia_child_of_lyssieth")){
			return Main.game.getNpc(Lyssieth.class);
		} else if(dialogueFlags.hasFlag("innoxia_child_of_lunette")){
			//TODO
		} else if(dialogueFlags.hasFlag("innoxia_child_of_lirecea")){
			//TODO
		} else if(dialogueFlags.hasFlag("innoxia_child_of_lovienne")){
			//TODO
		} else if(dialogueFlags.hasFlag("innoxia_child_of_lasielle")){
			//TODO
		} else if(dialogueFlags.hasFlag("innoxia_child_of_lyxias")){
			//TODO
		} else if(dialogueFlags.hasFlag("innoxia_child_of_lisophia")){
			//TODO
		} else if(dialogueFlags.hasFlag("innoxia_child_of_lilith")){
			//TODO
		}
		
		System.err.println("Warning: Did not find a suitable lilin in getLilinMother()!");
		new Exception().printStackTrace();
		return Main.game.getNpc(Lyssieth.class);
	}

	// Quests:

	public void resetAllQuests() {
		quests = new EnumMap<>(QuestLine.class);
	}
	
	public boolean isMainQuestUpdated() {
		return mainQuestUpdated;
	}

	public void setMainQuestUpdated(boolean mainQuestUpdated) {
		this.mainQuestUpdated = mainQuestUpdated;
	}

	public boolean isSideQuestUpdated() {
		return sideQuestUpdated;
	}

	public void setSideQuestUpdated(boolean sideQuestUpdated) {
		this.sideQuestUpdated = sideQuestUpdated;
	}

	public boolean isRelationshipQuestUpdated() {
		return relationshipQuestUpdated;
	}

	public void setRelationshipQuestUpdated(boolean relationshipQuestUpdated) {
		this.relationshipQuestUpdated = relationshipQuestUpdated;
	}

	public boolean isActive() { return isActive; }

	public void setActive(boolean active) { this.isActive = active; }

	public Map<QuestLine, Quest> getQuestsFailed() {
		return questsFailed;
	}
	
	public boolean isQuestFailed(QuestLine questLine) {
		return questsFailed.containsKey(questLine);
	}
	
	public String setQuestFailed(QuestLine questLine, Quest questFail) {
//		removeQuest(questLine);
		questsFailed.put(questLine, questFail);

		return "<p style='text-align:center;'>"
					+ "[style.boldBad(Quest Failed - " + questLine.getName() + ")]"
				+ "</p>";
	}

	public String startQuest(QuestLine questLine) {
		return setQuestProgress(questLine, questLine.getQuestTree().getData());
	}

	public String addOptionalQuestProgress(QuestLine questLine, Quest quest) {
		if(!quests.containsKey(questLine)) {
			System.err.println("Player does not have quest line "+questLine+", so cannot add optional quest: "+quest);
			return "";
		}
		
		if(questLine.getType() == QuestType.MAIN) {
			setMainQuestUpdated(true);
			
		} else if(questLine.getType() == QuestType.SIDE) {
			setSideQuestUpdated(true);
			
		} else {
			setRelationshipQuestUpdated(true);
		}
		
		String experienceUpdate = incrementExperience(quest.getExperienceReward(), true);
		
		quests.get(questLine).add(0, quest);
		
		Main.game.addEvent(new EventLogEntry("[style.colourGood(Optional Task Complete)]", quest.getName()), false);
		return "<p style='text-align:center;'>"
				+ "<b style='color:" + questLine.getType().getColour().toWebHexString() + ";'>Quest - " + questLine.getName() + "</b><br/>"
				+ "<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Optional Task Completed: " + quest.getName() + "</b><br/>"
				+ experienceUpdate;
	}
	
	public String setQuestProgress(QuestLine questLine, Quest quest) {
		if(!questLine.getQuestTree().childrenContainsData(quest)) {
			System.err.println("QuestTree in quest line "+questLine+" does not contain quest: "+quest);
			return "";
		}
		
		if (questLine.getType() == QuestType.MAIN) {
			setMainQuestUpdated(true);
			
		} else if (questLine.getType() == QuestType.SIDE) {
			setSideQuestUpdated(true);
			
		} else {
			setRelationshipQuestUpdated(true);
		}
		
		
		if(quests.containsKey(questLine)) {
			Quest currentQuest = questLine.getQuestTree().getFirstNodeWithData(getQuest(questLine)).getData();
			
			String experienceUpdate = incrementExperience(currentQuest.getExperienceReward(), true);
			
			quests.get(questLine).add(quest);
			
			if (questLine.getQuestTree().getFirstNodeWithData(quest).getChildren().isEmpty()) { // QuestLine complete (No more children in the tree)
				Main.game.addEvent(new EventLogEntry("[style.colourExcellent(Quest Complete)]", questLine.getName()), false);
				return "<p style='text-align:center;'>"
						+ "<b style='color:" + questLine.getType().getColour().toWebHexString() + ";'>Quest - " + questLine.getName() + "</b><br/>"
						+ "<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Task Completed</b><b> - "+currentQuest.getName()+"</b><br/>"
						+ "<b>All Tasks Completed!</b></p>"
						+ experienceUpdate;
				
			} else {
				Main.game.addEvent(new EventLogEntry("[style.colourMinorGood(New Task)]", quest.getName()), false);
				return "<p style='text-align:center;'>"
						+ "<b style='color:" + questLine.getType().getColour().toWebHexString() + ";'>Quest - " + questLine.getName() + "</b><br/>"
						+ "<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Task Completed - "+currentQuest.getName()+"</b><br/>"
						+ "<b>New Task - " + quest.getName() + "</b></p>"
						+ experienceUpdate;
			}
			
		} else {
			quests.put(questLine, new ArrayList<>());
			quests.get(questLine).add(quest);
			
			Main.game.addEvent(new EventLogEntry("[style.colourGood(Quest Started)]", questLine.getName()), false);
			
			return "<p style='text-align:center;'>"
					+ "<b style='color:" + questLine.getType().getColour().toWebHexString() + ";'>New Quest - " + questLine.getName() + "</b><br/>"
					+ "<b>New Task - " + quest.getName() + "</b></p>";
		}
		
	}

	/**
	 * <b>This method should only be used in very special circumstances!</b>
	 * @param questLine the QuestLine to be removed.
	 */
	public void removeQuest(QuestLine questLine) {
		quests.remove(questLine);
	}
	
	public Map<QuestLine, List<Quest>> getQuests() {
		return quests;
	}
	
	public Quest getQuest(QuestLine questLine) {
		List<Quest> quests = this.quests.get(questLine);
		if(quests==null) {
			return null;
		}
		return quests.get(quests.size()-1);
	}
	
	public boolean hasQuest(QuestLine questLine) {
		return quests.containsKey(questLine);
	}
	
	public boolean hasQuestInLine(QuestLine questLine, Quest quest) {
		if(!hasQuest(questLine)) {
			return false;
		}
		return quests.get(questLine).contains(quest);
	}
	
	public boolean isSubQuestCompleted(Quest subQuest, QuestLine questLine) {
		if(quests.containsKey(questLine) && quests.get(questLine).contains(subQuest) && getQuest(questLine)!=subQuest) {
			return true;
		}
		return false;
	}

	public boolean isQuestCompleted(QuestLine questLine) {
		if(!hasQuest(questLine)) {
			return false;
		}
		return questLine.getQuestTree().getFirstNodeWithData(getQuest(questLine)).getChildren().isEmpty();
	}
	
	public boolean isHasSlaverLicense() {
		return isQuestCompleted(QuestLine.SIDE_SLAVERY) || Main.game.isDebugMode();
	}
	
	public boolean isAbleToAccessRoomManagement() {
		return isHasSlaverLicense() || isQuestCompleted(QuestLine.SIDE_ACCOMMODATION);
	}

	/**
	 * Prints an error if the quest is not within the specified questLine.
	 * 
	 * @return true if the player's quest progress is greater than the specified quest.
	 */
	public boolean isQuestProgressGreaterThan(QuestLine questLine, Quest quest) {
		if(!hasQuest(questLine)) {
//			System.err.println("Player does not have Quest: "+questLine.toString()+", "+quest.toString());
			return false;
		}
		
		if(questLine.getQuestTree().getFirstNodeWithData(quest)==null) {
			System.err.println("Quest "+quest.toString()+" was not in QuestLine!");
			return false;
		}
		
		// Check to see if the current progress does not have a child with quest data:
		return questLine.getQuestTree().getFirstNodeWithData(getQuest(questLine)).getFirstNodeWithData(quest)==null;
	}
	
	/**
	 * Prints an error if the quest is not within the specified questLine.
	 * 
	 * @return true if the player's quest progress is less than the specified quest. Also returns true if the player does not yet have this quest.
	 */
	public boolean isQuestProgressLessThan(QuestLine questLine, Quest quest) {
		if(!hasQuest(questLine)) {
//			System.err.println("Player does not have Quest: "+quest.toString());
			return true;
		}
		
		if(getQuest(questLine)==quest) {
			return false;
		}
		
		if(questLine.getQuestTree().getFirstNodeWithData(quest)==null) {
			System.err.println("Quest "+quest.toString()+" was not in QuestLine!");
			return false;
		}
		
		// Check to see if the current progress has a child with quest data:
		return questLine.getQuestTree().getFirstNodeWithData(getQuest(questLine)).getFirstNodeWithData(quest)!=null;
	}

	// Other stuff:

	public List<String> getCharactersEncountered() {
		return charactersEncountered;
	}

	public void addCharacterEncountered(String character) {
		if (!charactersEncountered.contains(character)) {
			charactersEncountered.add(character);
			if(Main.game.isStarted()) {
				sortCharactersEncountered();
			}
		}
	}

	public void addCharacterEncountered(GameCharacter character) {
		if (!charactersEncountered.contains(character.getId())) {
			charactersEncountered.add(character.getId());
			if(Main.game.isStarted()) {
				sortCharactersEncountered();
			}
		}
	}
	
	/**
	 * @param expansiveSearch True if you want every possible character that the player has have encountered, including ones that are not usually added to the contacts list. (This will include random NPCs the player has had sex with.)
	 * @return
	 */
	public List<GameCharacter> getCharactersEncounteredAsGameCharacters(boolean expansiveSearch) {
		List<GameCharacter> npcsEncountered = new ArrayList<>();
		charactersEncountered.removeIf(id -> !Main.game.isCharacterExisting(id));
		for(String characterId : charactersEncountered) {
			try {
				GameCharacter npc = Main.game.getNPCById(characterId);
				npcsEncountered.add(npc);
			} catch (Exception e) {
				Util.logGetNpcByIdError("getCharactersEncounteredAsGameCharacters()", characterId);
			}
		}
		
		if(expansiveSearch) {
			for(String id : this.sexCount.keySet()) {
				try {
					GameCharacter npc = Main.game.getNPCById(id);
					npcsEncountered.add(npc);
				} catch(Exception ex) {
				}
			}
		}
		
		return npcsEncountered;
	}
	
	public void sortCharactersEncountered() {
		List<GameCharacter> npcsEncountered = new ArrayList<>();
		charactersEncountered.removeIf(id -> !Main.game.isCharacterExisting(id));
		for(String characterId : charactersEncountered) {
			try {
				GameCharacter npc = Main.game.getNPCById(characterId);
				npcsEncountered.add(npc);
			} catch (Exception e) {
				Util.logGetNpcByIdError("sortCharactersEncountered()", characterId);
			}
		}
		npcsEncountered.sort((npc1, npc2) -> npc1 instanceof NPCOffspring
				?(npc2 instanceof NPCOffspring
					?npc1.getName(true).compareTo(npc2.getName(true))
					:1)
				:(npc2 instanceof NPCOffspring
						?-1
						:npc1.getName(true).compareTo(npc2.getName(true))));
		List<String> sortedIDs = new ArrayList<>();
		for(GameCharacter character : npcsEncountered) {
			sortedIDs.add(character.getId());
		}
		charactersEncountered = sortedIDs;
	}
	
	public SizedStack<ShopTransaction> getBuybackStack() {
		return buybackStack;
	}
	
	// Discoveries:
	
	public boolean addRaceDiscoveredFromBook(AbstractSubspecies subspecies) {
		return racesDiscoveredFromBook.add(subspecies);
	}
	
	public Set<AbstractSubspecies> getRacesDiscoveredFromBook() {
		return racesDiscoveredFromBook;
	}

	/** <b>You should be using the Properties class to access this!</b> */
	public int getItemsDiscoveredCount() {
		return itemsDiscovered.size();
	}
	
	/** <b>You should be using the Properties class to add this!</b> */
	public boolean addItemDiscovered(AbstractItemType itemType) {
		return itemsDiscovered.add(itemType);
	}

	/** <b>You should be using the Properties class to access this!</b> */
	public boolean isItemDiscovered(AbstractItemType itemType) {
		return itemsDiscovered.contains(itemType);
	}

	/** <b>You should be using the Properties class to access this!</b> */
	public int getClothingDiscoveredCount() {
		return clothingDiscovered.size();
	}
	
	/** <b>You should be using the Properties class to add this!</b> */
	public boolean addClothingDiscovered(AbstractClothingType clothingType) {
		return clothingDiscovered.add(clothingType);
	}

	/** <b>You should be using the Properties class to access this!</b> */
	public boolean isClothingDiscovered(AbstractClothingType clothingType) {
		return clothingDiscovered.contains(clothingType);
	}

	/** <b>You should be using the Properties class to access this!</b> */
	public int getWeaponsDiscoveredCount() {
		return weaponsDiscovered.size();
	}
	
	/** <b>You should be using the Properties class to add this!</b> */
	public boolean addWeaponDiscovered(AbstractWeaponType weaponType) {
		return weaponsDiscovered.add(weaponType);
	}

	/** <b>You should be using the Properties class to access this!</b> */
	public boolean isWeaponDiscovered(AbstractWeaponType weaponType) {
		return weaponsDiscovered.contains(weaponType);
	}

	/** <b>You should be using the Properties class to access this!</b> */
	public int getSubspeciesDiscoveredCount() {
		return subspeciesDiscovered.size();
	}

	/** <b>You should be using the Properties class to add this!</b> */
	public boolean addRaceDiscovered(AbstractSubspecies subspecies) {
		return subspeciesDiscovered.add(subspecies);
	}

	/** <b>You should be using the Properties class to access this!</b> */
	public boolean isRaceDiscovered(AbstractSubspecies subspecies) {
		return subspeciesDiscovered.contains(subspecies);
	}

	/** <b>You should be using the Properties class to access this!</b> */
	public int getSubspeciesAdvancedDiscoveredCount() {
		return subspeciesAdvancedKnowledge.size();
	}

	/** <b>You should be using the Properties class to add this!</b> */
	public boolean addAdvancedRaceKnowledge(AbstractSubspecies subspecies) {
		return subspeciesAdvancedKnowledge.add(subspecies);
	}

	/** <b>You should be using the Properties class to access this!</b> */
	public boolean isAdvancedRaceKnowledgeDiscovered(AbstractSubspecies subspecies) {
		if(subspeciesAdvancedKnowledge.contains(subspecies)) {
			return true;
		}
		// If this subspecies shares a lore book with the parent subspecies, and that parent subspecies is unlocked, then return true:
		AbstractSubspecies coreSubspecies = AbstractSubspecies.getMainSubspeciesOfRace(subspecies.getRace());
		if(ItemType.getLoreBook(subspecies).equals(ItemType.getLoreBook(coreSubspecies))) {
			return subspeciesAdvancedKnowledge.contains(coreSubspecies);
		}
		
		return false;
	}

	@Override
	public String getSpellDescription() {
		return "<p>"
				+ UtilText.parse(this,
						UtilText.returnStringAtRandom(
						"Concentrating on harnessing the power of your arcane aura, you thrust your [pc.arm] into mid air and cast a spell!"))
			+ "</p>";
	}

	@Override
	public String getSeductionDescription(GameCharacter target) {
		String description = "";
		if(this.hasStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION)
				|| this.hasStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION)
				|| this.hasStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH)) {
			if(this.isFeminine()) {
				return UtilText.parse(target,
						UtilText.returnStringAtRandom(
								"You put on a smouldering look, and as your [pc.eyes] meet [npc.namePos], you project an extremely lewd moan into [npc.her] head,"
										+ " [pc.thought(~Aaah!~ "
											+(this.hasVagina()
													?"You're making me so wet!"
													:this.hasPenis()
														?"You're getting me so hard!"
														:"You're turning me on so much!")+")]",
								"You lock your [pc.eyes] with [npc.namePos], and, putting on your most innocent look as you pout at [npc.herHim], you project an echoing moan deep into [npc.her] mind,"
									+ " [pc.thought("+
											(this.hasVagina()
													?"~Mmm!~ Fuck me! ~Aaa!~ My pussy's wet and ready for you!"
													:this.hasPenis()
														?"~Mmm!~ I can't wait to fuck you! ~Aaa!~ You're going to love my cock!"
														:"~Mmm!~ Fuck me! ~Aaa!~ I need you so badly!")+")]",
								(this.hasStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION)
										|| this.hasStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH)
										?"You pout innocently at [npc.name], before blowing [npc.herHim] a wet kiss."
												+ " As you straighten back up, you project the feeling of a ghostly pair of wet lips pressing against [npc.her] cheek."
										:"")));
			} else {
				return UtilText.parse(target,
						UtilText.returnStringAtRandom(
								"You put on a confident look, and as your [pc.eyes] meet [npc.namePos], you project an extremely lewd groan into [npc.her] head,"
									+ " [pc.thought(~Mmm!~ "
											+(this.hasVagina()
													?"You're making me so wet!"
													:this.hasPenis()
														?"You're getting me so hard!"
														:"You're turning me on so much!")+")]",
								"You lock your [pc.eyes] with [npc.namePos], and, throwing [npc.herHim] a charming smile, you project an echoing groan deep into [npc.her] mind,"
									+ " [pc.thought("+
											(this.hasVagina()
													?"~Mmm!~ Fuck me! ~Aaa!~ My pussy's wet and ready for you!"
													:this.hasPenis()
														?"~Mmm!~ I can't wait to fuck you! You're going to love my cock!"
														:"~Mmm!~ I can't wait to have some fun with you!")+")]",
								(this.hasStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION)
										|| this.hasStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH)
										?"You throw [npc.name] a charming smile, before winking at [npc.herHim] and striking a heroic pose."
												+ " As you straighten back up, you project the feeling of a ghostly pair of arms pulling [npc.herHim] into a strong, confident embrace."
										:"")));
			}
		}
		
		if(this.isFeminine()) {
			description = UtilText.parse(target,
					UtilText.returnStringAtRandom(
					"You blow a kiss at [npc.name] and wink suggestively at [npc.herHim].",
					"Biting your lip and putting on your most smouldering look, you run your hands slowly up your inner thighs.",
					"As you give [npc.name] your most innocent look, you blow [npc.herHim] a little kiss.",
					"Turning around, you let out a playful giggle as you give your [pc.ass+] a slap.",
					"You slowly run your hands up the length of your body, before pouting at [npc.name]."));
			
		} else {
			description = UtilText.parse(target,
					UtilText.returnStringAtRandom(
					"You blow a kiss at [npc.name] and wink suggestively at [npc.herHim].",
					"Smiling confidently at [npc.name], you slowly run your hands up your inner thighs.",
					"As you give [npc.name] your most seductive look, you blow [npc.herHim] a little kiss.",
					"Turning around, you let out a playful laugh as you give your [pc.ass+] a slap.",
					"You try to look as commanding as possible as you smirk playfully at [npc.name]."));
		}

		return description;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public boolean isAbleToBeEgged() {
		return true;
	}
	
	// This behaviour is overridden for unique scenes in which the player's orgasm requires special dialogue or effects.
	//TODO move this into the NPC's class
	@Override
	public SexActionOrgasmOverride getSexActionOrgasmOverride(SexActionInterface sexAction, OrgasmCumTarget target, boolean applyExtraEffects, String description) {

		// SCARLETT:
		
		if(Main.sex.getAllParticipants().contains(Main.game.getNpc(Scarlett.class))
				&& Main.sex.getOngoingSexAreas(this, SexAreaOrifice.ANUS, Main.game.getNpc(Scarlett.class)).contains(SexAreaPenetration.PENIS)
				&& Main.sex.getSexPace(Main.game.getNpc(Scarlett.class))==SexPace.DOM_ROUGH) { // Orgasm reaction when you cum from Scarlett's anal:
			StringBuilder sb = new StringBuilder();
			if(description!=null) {
				sb.append(description);
			} else {
				sb.append(GenericOrgasms.getGenericOrgasmDescription(sexAction, this, target));
			}
			
			sb.append(UtilText.parseFromXMLFile("characters/dominion/scarlett", "ROUGH_ANAL_ORGASM"));
			
			return new SexActionOrgasmOverride(false) {
				@Override
				public String getDescription() {
					return sb.toString();
				}
				@Override
				public void applyEffects() {
				}
			};
		}
		
		
		// LILAYA:
		
		if(Main.sex.getAllParticipants().contains(Main.game.getNpc(Lilaya.class))
				&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
				&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lilayaAmazonsSecretImpregnation, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lilayaCondomBroke, false);
			boolean triggerEndScene = false;
			
			StringBuilder sb = new StringBuilder();
			if(description!=null) {
				sb.append(description);
			} else {
				sb.append(GenericOrgasms.getGenericOrgasmDescription(sexAction, this, target));
			}
			
			// Penis cumming inside reaction:
			if(target==OrgasmCumTarget.INSIDE
					&& this.getCurrentPenisRawCumStorageValue()>0
					&& Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getNpc(Lilaya.class)).contains(SexAreaOrifice.VAGINA)) {
				if(this.isWearingCondom()) {
					if(sexAction.getCondomFailure(this, Main.game.getNpc(Lilaya.class))!=CondomFailure.NONE) {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lilayaCondomBroke, true);
						sb.append(UtilText.parseFromXMLFile("characters/dominion/lilaya", "ORGASM_REACTION_CREAMPIE_CONDOM_BROKE"));
					} else {
						sb.append(UtilText.parseFromXMLFile("characters/dominion/lilaya", "ORGASM_REACTION_CREAMPIE_CONDOM"));
					}
				} else {
					sb.append(UtilText.parseFromXMLFile("characters/dominion/lilaya", "ORGASM_REACTION_CREAMPIE"));
				}
				triggerEndScene = true;
				
			// Amazon's Secret status effect reaction:
			} else if(this.hasStatusEffect("innoxia_amazons_secret")) {
				Set<GameCharacter> charactersContactingVagina = new HashSet<>(Main.sex.getOngoingCharactersUsingAreas(this, SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT));
				charactersContactingVagina.addAll(Main.sex.getOngoingCharactersUsingAreas(this, SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA));
				charactersContactingVagina.addAll(Main.sex.getOngoingCharactersUsingAreas(this, SexAreaOrifice.VAGINA, SexAreaOrifice.VAGINA));
				charactersContactingVagina.addAll(Main.sex.getOngoingCharactersUsingAreas(this, SexAreaPenetration.CLIT, SexAreaPenetration.CLIT));

				if(charactersContactingVagina.contains(Main.game.getNpc(Lilaya.class))) {
					Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lilayaAmazonsSecretImpregnation, true);
					triggerEndScene = true;
					sb.append(UtilText.parseFromXMLFile("characters/dominion/lilaya", "ORGASM_REACTION_AMAZONS_SECRET"));
				}
			}
			
			if(triggerEndScene) {
				return new SexActionOrgasmOverride(false) {
					@Override
					public String getDescription() {
						return sb.toString();
					}
					@Override
					public void applyEffects() {
					}
					@Override
					public boolean isEndsSex() {
						return Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.PREGNANT_0)
								&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative();
					}
				};
			}
		}
		
		
		// LYSSIETH:
		
		if(Main.sex.getSexManager() instanceof SMLyssiethDemonTF) { // Lyssieth's demon TF scene:
			StringBuilder sb = new StringBuilder();if(description!=null) {
				sb.append(description);
			} else {
				sb.append(GenericOrgasms.getGenericOrgasmDescription(sexAction, this, target));
			}
			
			if(Main.sex.getLastUsedSexAction(Main.game.getNpc(Lyssieth.class)).getActionType()==SexActionType.ORGASM) { //These specials are only for follow-ups to Lyssieth's orgasms:
				if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))==1) {
					// Stage 1) Player is sucking Lyssieth's cock:
					if(Main.sex.getOngoingSexAreas(this, SexAreaOrifice.MOUTH, Main.game.getNpc(Lyssieth.class)).contains(SexAreaPenetration.PENIS)) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_1_PC_GIVING_LYSSIETH_BLOWJOB_END"));
						
						return new SexActionOrgasmOverride(false) {
							@Override
							public String getDescription() {
								return sb.toString();
							}
							@Override
							public void applyEffects() {
							}
						};
						
					// Stage 1) Player is eating Lyssieth out:
					} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.TONGUE, Main.game.getNpc(Lyssieth.class)).contains(SexAreaOrifice.VAGINA)) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_1_PC_GIVING_LYSSIETH_CUNNILINGUS_END"));
						
						return new SexActionOrgasmOverride(false) {
							@Override
							public String getDescription() {
								return sb.toString();
							}
							@Override
							public void applyEffects() {
							}
						};
							
					// Stage 1) Lyssieth is sucking player's cock:
					} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getNpc(Lyssieth.class)).contains(SexAreaOrifice.MOUTH)) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_1_PC_GETTING_BLOWJOB_FROM_LYSSIETH_END"));
						
						return new SexActionOrgasmOverride(false) {
							@Override
							public String getDescription() {
								return sb.toString();
							}
							@Override
							public void applyEffects() {
							}
						};
						
					// Stage 1) Lyssieth is eating the player out:
					} else if(Main.sex.getOngoingSexAreas(this, SexAreaOrifice.VAGINA, Main.game.getNpc(Lyssieth.class)).contains(SexAreaPenetration.TONGUE)) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_1_PC_GETTING_CUNNILINGUS_FROM_LYSSIETH_END"));
						
						return new SexActionOrgasmOverride(false) {
							@Override
							public String getDescription() {
								return sb.toString();
							}
							@Override
							public void applyEffects() {
							}
						};
					}
					
				} else if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))==2) {
					// Stage 2) Lyssieth is fucking the player:
					if(Main.sex.getOngoingSexAreas(this, SexAreaOrifice.VAGINA, Main.game.getNpc(Lyssieth.class)).contains(SexAreaPenetration.PENIS)) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_2_PC_PUSSY_FUCKED_BY_LYSSIETH_END"));
						
						return new SexActionOrgasmOverride(false) {
							@Override
							public String getDescription() {
								return sb.toString();
							}
							@Override
							public void applyEffects() {
							}
							@Override
							public void applyEndEffects() {
								if(applyExtraEffects) {
									Main.sex.stopAllOngoingActions(Main.game.getPlayer(), Main.game.getNpc(Lyssieth.class));
								}
							}
						};
	
					// Stage 2) Lyssieth is fucking the player's ass:
					} else if(Main.sex.getOngoingSexAreas(this, SexAreaOrifice.ANUS, Main.game.getNpc(Lyssieth.class)).contains(SexAreaPenetration.PENIS)) {
						if(Main.game.getPlayer().hasPenis() && Main.game.getPlayer().getPenisType().getRace()==Race.DEMON && Main.game.getPlayer().getPenisRawSizeValue()<=4) {
							sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_2_PC_ASS_FUCKED_BY_LYSSIETH_END_SISSY"));
						} else {
							sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_2_PC_ASS_FUCKED_BY_LYSSIETH_END"));
						}
						
						return new SexActionOrgasmOverride(false) {
							@Override
							public String getDescription() {
								return sb.toString();
							}
							@Override
							public void applyEffects() {
							}
							@Override
							public void applyEndEffects() {
								if(applyExtraEffects) {
									Main.sex.stopAllOngoingActions(Main.game.getPlayer(), Main.game.getNpc(Lyssieth.class));
								}
							}
						};
						
					// Stage 2) The player is fucking Lyssieth:
					} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getNpc(Lyssieth.class)).contains(SexAreaOrifice.VAGINA)) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_2_PC_FUCKING_LYSSIETH_END"));
						
						return new SexActionOrgasmOverride(false) {
							@Override
							public String getDescription() {
								return sb.toString();
							}
							@Override
							public void applyEffects() {
							}
							@Override
							public void applyEndEffects() {
								if(applyExtraEffects) {
									Main.sex.stopAllOngoingActions(Main.game.getPlayer(), Main.game.getNpc(Lyssieth.class));
								}
							}
						};
						
					// Stage 2) Scissoring:
					} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.CLIT, Main.game.getNpc(Lyssieth.class)).contains(SexAreaPenetration.CLIT)) {
						if(Main.sex.getSexPositionSlot(this)==SexSlotLyingDown.SCISSORING) {
							sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_2_SCISSOR_PC_TOP_END"));
						} else {
							sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_2_SCISSOR_PC_BOTTOM_END"));
						}
						
						return new SexActionOrgasmOverride(false) {
							@Override
							public String getDescription() {
								return sb.toString();
							}
							@Override
							public void applyEffects() {
							}
							@Override
							public void applyEndEffects() {
								if(applyExtraEffects) {
									Main.sex.stopAllOngoingActions(Main.game.getPlayer(), Main.game.getNpc(Lyssieth.class));
								}
							}
						};
					}
					
				} else if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))==3) {
					// Stage 3) Player is fucking/breeding Lyssieth:
					if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getNpc(Lyssieth.class)).contains(SexAreaOrifice.VAGINA)) {
						if(Main.sex.getSexPositionSlot(Main.game.getPlayer())==SexSlotLyingDown.MATING_PRESS) {
							sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_BREEDING_LYSSIETH_END"));
						} else {
							sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_FUCKING_LYSSIETH_END"));
						}
						
						return new SexActionOrgasmOverride(true) {
							@Override
							public String getDescription() {
								return sb.toString();
							}
							@Override
							public void applyEffects() {
							}
						};

					// Stage 3) Lyssieth is fucking the player:
					} else if(Main.sex.getOngoingSexAreas(this, SexAreaOrifice.VAGINA, Main.game.getNpc(Lyssieth.class)).contains(SexAreaPenetration.PENIS)) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_PUSSY_FUCKED_BY_LYSSIETH_END"));
						
						return new SexActionOrgasmOverride(true) {
							@Override
							public String getDescription() {
								return sb.toString();
							}
							@Override
							public void applyEffects() {
							}
						};

					// Stage 3) Lyssieth is fucking the player's ass:
					} else if(Main.sex.getOngoingSexAreas(this, SexAreaOrifice.ANUS, Main.game.getNpc(Lyssieth.class)).contains(SexAreaPenetration.PENIS)) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_ASS_FUCKED_BY_LYSSIETH_END"));
						
						return new SexActionOrgasmOverride(true) {
							@Override
							public String getDescription() {
								return sb.toString();
							}
							@Override
							public void applyEffects() {
							}
						};
						
					// Stage 3) Lyssieth is sucking player's cock:
					} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getNpc(Lyssieth.class)).contains(SexAreaOrifice.MOUTH)) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_GETTING_BLOWJOB_FROM_LYSSIETH_END"));
						
						return new SexActionOrgasmOverride(true) {
							@Override
							public String getDescription() {
								return sb.toString();
							}
							@Override
							public void applyEffects() {
							}
						};
						
					// Stage 3) Lyssieth is eating the player out:
					} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.TONGUE, Main.game.getNpc(Lyssieth.class)).contains(SexAreaOrifice.VAGINA)) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_GETTING_CUNNILINGUS_FROM_LYSSIETH_END"));
						
						return new SexActionOrgasmOverride(true) {
							@Override
							public String getDescription() {
								return sb.toString();
							}
							@Override
							public void applyEffects() {
							}
						};
					}
				}
			}
		}

		return super.getSexActionOrgasmOverride(sexAction, target, applyExtraEffects, description); // Normal scene
	}
	
	/**
	 * Returns a list of NPCs' IDs who are either living in Lilaya's house or in an apartment known to the player.
	 */
	public List<String> getFriendlyOccupants() {
		return friendlyOccupants;
	}
	
	/**
	 * Adds this npc to the list of 'friendly occupants', and sets their occupation to 'NPC_UNEMPLOYED'.
	 * @return true (as specified by Collection.add)
	 */
	public boolean addFriendlyOccupant(NPC npc) {
		npc.setHistory(Occupation.NPC_UNEMPLOYED);
		
		for(Occupation occ : Occupation.values()) {
			if(!occ.isAvailableToPlayer()
					&& occ.isAvailable(this)
					&& occ!=Occupation.UNEMPLOYED
					&& occ!=Occupation.NPC_UNEMPLOYED
					&& !occ.isLowlife()) {
				npc.addDesiredJob(occ);
			}
		}
		
		return friendlyOccupants.add(npc.getId());
	}
	
	public boolean removeFriendlyOccupant(GameCharacter occupant) {
		return friendlyOccupants.remove(occupant.getId());
	}

	public Set<AbstractWorldType> getWorldsVisited() {
		return worldsVisited;
	}
	
	public boolean isDiscoveredWorldMap() {
		return this.isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN);
	}
	
	public void handleDemonicTransformationPerkEffects() {
		if(Main.game.getPlayer().hasPerkAnywhereInTree(Perk.POWER_OF_LIRECEA_1)) {
			Main.game.getPlayer().removeSpecialPerk(Perk.POWER_OF_LIRECEA_1);
			Main.game.getPlayer().addSpecialPerk(Perk.POWER_OF_LIRECEA_1_DEMON);
		}
		if(Main.game.getPlayer().hasPerkAnywhereInTree(Perk.POWER_OF_LOVIENNE_2)) {
			Main.game.getPlayer().removeSpecialPerk(Perk.POWER_OF_LOVIENNE_2);
			Main.game.getPlayer().addSpecialPerk(Perk.POWER_OF_LOVIENNE_2_DEMON);
		}
		if(Main.game.getPlayer().hasPerkAnywhereInTree(Perk.POWER_OF_LASIELLE_3)) {
			Main.game.getPlayer().removeSpecialPerk(Perk.POWER_OF_LASIELLE_3);
			Main.game.getPlayer().addSpecialPerk(Perk.POWER_OF_LASIELLE_3_DEMON);
		}
		if(Main.game.getPlayer().hasPerkAnywhereInTree(Perk.POWER_OF_LYSSIETH_4)) {
			Main.game.getPlayer().removeSpecialPerk(Perk.POWER_OF_LYSSIETH_4);
			Main.game.getPlayer().addSpecialPerk(Perk.POWER_OF_LYSSIETH_4_DEMON);
		}
		if(Main.game.getPlayer().hasPerkAnywhereInTree(Perk.POWER_OF_LUNETTE_5)) {
			Main.game.getPlayer().removeSpecialPerk(Perk.POWER_OF_LUNETTE_5);
			Main.game.getPlayer().addSpecialPerk(Perk.POWER_OF_LUNETTE_5_DEMON);
		}
		if(Main.game.getPlayer().hasPerkAnywhereInTree(Perk.POWER_OF_LYXIAS_6)) {
			Main.game.getPlayer().removeSpecialPerk(Perk.POWER_OF_LYXIAS_6);
			Main.game.getPlayer().addSpecialPerk(Perk.POWER_OF_LYXIAS_6_DEMON);
		}
		if(Main.game.getPlayer().hasPerkAnywhereInTree(Perk.POWER_OF_LISOPHIA_7)) {
			Main.game.getPlayer().removeSpecialPerk(Perk.POWER_OF_LISOPHIA_7);
			Main.game.getPlayer().addSpecialPerk(Perk.POWER_OF_LISOPHIA_7_DEMON);
		}
	}
	
	protected String losingPureVirginity(GameCharacter characterPenetrating, SexAreaPenetration penetrationType) {
		if(characterPenetrating.isPlayer()) {
			return UtilText.parse(this,
					"<p style='text-align:center;'>"
						+ "<b style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Broken Virgin</b>"
					+ "</p>"
					+ "<p>"
						+ "You can't quite believe what you're doing to yourself."
						+ " As your "+(Main.sex.getFirstOngoingSexAreaPenetration(Main.game.getPlayer(), SexAreaOrifice.VAGINA).getName(characterPenetrating))
							+" takes your own virginity in a single thrust, you find yourself letting out a desperate gasp."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "[pc.thought(W-What am I doing?!<br/>"
								+ "I-I'm just so... <i>horny</i>!<br/>"
								+ "I-I can't help myself!)]"
					+ "</p>"
					+ "<p>"
						+ "You don't quite know how to react to your own actions."
						+ " The virginity that you prized so highly is now gone, and a vacant gaze settles over your face as your [pc.pussy+] spreads lewdly around your own "+penetrationType.getName(characterPenetrating)+"."
					+ "</p>"
					+ "<p>"
						+ "While you were a virgin, you felt invincible."
						+ " As though you could overcome any obstacle that was placed in your way."
						+ " Now, however..."
					+ "</p>"
					+ "<p style='text-align:center;'>"
					+ "[pc.thought(Now I'm just some regular old slut...<br/>"
							+ "So turned on that I choose to fuck myself...<br/>"
							+ "All I'm good for now is being a worthless fuck-toy...)]"
					+ "</p>"
					+ "<p>"
						+ "With a shuddering sigh, you decide to resign yourself to the fact that now you're nothing more than a <b style='color:"+StatusEffect.FETISH_BROKEN_VIRGIN.getColour().toWebHexString()+";'>broken virgin</b>..."
					+ "</p>");
			
		} else {
			return UtilText.parse(characterPenetrating,
					"<p style='text-align:center;'>"
						+ "<b style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Broken Virgin</b>"
					+ "</p>"
					+ "<p>"
						+ "You can't believe what's happening."
						+ " As [npc.namePos] "+(Main.sex.getFirstOngoingSexArea(Main.game.getPlayer(), SexAreaOrifice.VAGINA).getName(characterPenetrating))
						+" takes your virginity in a single thrust, you find yourself letting out a desperate gasp."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "[pc.thought(This is how I lose my virginity?!<br/>"
								+ "To...<i>"
								+ (characterPenetrating.isSlave() && characterPenetrating.getOwner().isPlayer()
										?" my own slave"
										:" [npc.a_race]")
								+ "</i>?!<br/>"
								+ "This can't be happening!)]"
					+ "</p>"
					+ "<p>"
						+ "You don't quite know how to react."
						+ " The virginity that you prized so highly has been suddenly taken from you, and a vacant gaze settles over your face as your [pc.pussy+] spreads lewdly around [npc.namePos] "+penetrationType.getName(characterPenetrating)+"."
					+ "</p>"
					+ "<p>"
						+ "While you were a virgin, you felt invincible."
						+ " As though you could overcome any obstacle that was placed in your way."
						+ " Now, however..."
					+ "</p>"
					+ "<p style='text-align:center;'>"
					+ "[pc.thought(Now I'm just some regular old slut...<br/>"
							+ "Getting fucked by any random person I meet...<br/>"
							+ "All I'm good for now is being the next lucky guy's fuck-toy...)]"
					+ "</p>"
					+ "<p>"
					+ "You're vaguely aware of [npc.name] [npc.moaning] somewhere in the background, completely oblivious to how hard you've been hit by the loss of your virginity."
					+ " With a shuddering sigh, you decide to resign yourself to the fact that now you're nothing more than a <b style='color:"+StatusEffect.FETISH_BROKEN_VIRGIN.getColour().toWebHexString()+";'>broken virgin</b>..."
					+ "</p>");
		}
	}
	
	@Override
	protected String getAnalVirginityLossDescription(GameCharacter characterPenetrating, SexAreaPenetration penetration){
		StringBuilder sb = new StringBuilder();
		
		boolean isPenis = penetration == SexAreaPenetration.PENIS;
		boolean isTail = penetration == SexAreaPenetration.TAIL;
		
		if(characterPenetrating.isPlayer()) { // SELF-PENETRATION
			// Initial penetration:
			if(!Main.sex.hasLubricationTypeFromAnyone(Main.game.getPlayer(), SexAreaOrifice.ANUS)) {
				// Dry:
				sb.append(
						"<p>"
							+ "You let out a painful cry as you force your "+(isPenis?"[pc.penis+]":"")+(isTail?"[pc.tail+]":"")+" into your dry [pc.asshole]."
							+ " Squirming and shuffling in discomfort, your cries grow louder and louder as you start fucking your own [pc.ass]; the lack of lubrication turning your first anal experience into one of mind-numbing agony."
						+ "</p>");
				
			} else {
				 // Wet:
				sb.append(
						"<p>"
							+ "You let out a painful cry as you force your "+(isPenis?"[pc.penis+]":"")+(isTail?"[pc.tail+]":"")+" into your [pc.asshole+]."
							+ " Squirming and shuffling in discomfort, you continue letting out little whimpers as you start fucking your own [pc.ass]."
							+ " Thankfully, your [pc.asshole] was lubricated beforehand, and you dread to think of how painful your first anal experience would have been otherwise."
						+ "</p>");
			}
			
			// Player masochist reaction:
			if(Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)) {
				sb.append(
						"<p>"
							+ "Due to being an extreme masochist, you find your painful cries being interspersed with lewd moans of pleasure."
							+ " The pain and discomfort at the feeling of losing your anal virginity is pure bliss, and you soon find yourself [pc.moaning] in a delightful haze of overwhelming ecstasy."
						+ "</p>");
			} else {
				sb.append(
						"<p>"
							+ "With tears welling up in your [pc.eyes], you let out another painful wail as you draw"+(isTail?" your [pc.tail]":"")+" back, before thrusting deep inside yourself once again."
							+ " This time, the pain isn't as extreme as before, and you realise that you're starting to get used to the feeling of using your own ass."
						+ "</p>");
			}
			
			// Ending:
			sb.append(
					"<p>"
						+ "The throbbing, painful ache in your [pc.ass] slowly starts to fade away, and as your "+(isPenis?"[pc.penis+]":"")+(isTail?"[pc.tail+]":"")
							+" pushes into your [pc.asshole+] once again, you let out a little whimper of relief as you feel that you're quickly getting used to the penetration."
					+ "</p>");
			
		} else {
			if((characterPenetrating instanceof NPC) && ((NPC)characterPenetrating).getSpecialPlayerVirginityLoss(characterPenetrating, penetration, this, SexAreaOrifice.ANUS)!=null) {
				return ((NPC)characterPenetrating).getSpecialPlayerVirginityLoss(characterPenetrating, penetration, this, SexAreaOrifice.ANUS);
				
			} else {
				// Initial penetration:
				if(!Main.sex.hasLubricationTypeFromAnyone(Main.game.getPlayer(), SexAreaOrifice.ANUS)) {
					// Dry:
					sb.append(
							"<p>"
								+ "You let out a painful cry as you feel [npc.namePos] "+(isPenis?"[npc.penis+]":"")+(isTail?"[npc.tail+]":"")+" push into your dry [pc.asshole]."
								+ " Squirming and shuffling in discomfort, your cries grow louder and louder as [npc.name] starts fucking your [pc.ass]; the lack of lubrication turning your first anal experience into one of mind-numbing agony."
							+ "</p>");
					
				} else {
					 // Wet:
					sb.append(
							"<p>"
								+ "You let out a painful cry as you feel [npc.namePos] "+(isPenis?"[npc.penis+]":"")+(isTail?"[npc.tail+]":"")+" push into your [pc.asshole+]."
								+ " Squirming and shuffling in discomfort, you continue letting out little whimpers as [npc.name] starts fucking your [pc.ass]."
								+ " Thankfully, your [pc.asshole] was lubricated beforehand, and you dread to think of how painful your first anal experience would have been otherwise."
							+ "</p>");
				}
				
				// Player masochist reaction:
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)) {
					sb.append(
							"<p>"
								+ "Due to being an extreme masochist, you find your painful cries being interspersed with lewd moans of pleasure."
								+ " The pain and discomfort at the feeling of losing your anal virginity is pure bliss, and you soon find yourself [pc.moaning] in a delightful haze of overwhelming ecstasy."
							+ "</p>");
				}
				
				// Partner sadistic reaction:
				if(characterPenetrating.hasFetish(Fetish.FETISH_SADIST)) {
					sb.append(
							"<p>"
								+ "With tears welling up in your [pc.eyes], you let out another painful wail as [npc.name] draws"+(isTail?" [npc.her] [npc.tail]":"")+" back, before violently thrusting deep inside you once again."
								+ " [npc.She] lets out an evil laugh as [npc.she] causes you to writhe about in pain, [npc.her] sadistic nature fuelling [npc.her] rough thrusts into your [pc.asshole] as [npc.she] ruthlessly fucks your [pc.ass]."
							+ "</p>");
				} else {
					sb.append(
							"<p>"
								+ "With tears welling up in your [pc.eyes], you let out another painful wail as [npc.name] draws"+(isTail?" [npc.her] [npc.tail]":"")+" back, before thrusting deep inside you once again."
								+ " This time, the pain isn't as extreme as before, and you realise that you're starting to get used to the feeling of being fucked in the ass."
							+ "</p>");
				}
				
				// Partner deflowering reaction:
				if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
					sb.append(
							"<p>"
								+ "[npc.speech(Oh, yes!)] [npc.she] cries, [npc.speech(Good [pc.girl], saving your anal virginity for me!"
									+ " Remember this moment, remember that <i>my</i> "+(isPenis?"cock":"")+(isTail?"tail":"")+" was the one that turned you into "+(Main.game.getPlayer().isFeminine()?"a horny buttslut":"a little fucktoy")+"!)]"
							+ "</p>");
				}
				
				// Ending:
				sb.append(
						"<p>"
							+ "The throbbing, painful ache in your [pc.ass] slowly starts to fade away, and as [npc.namePos] "+(isPenis?"[npc.penis+]":"")+(isTail?"[npc.tail+]":"")
								+" pushes into your [pc.asshole+] once again, you let out a little whimper of relief as you feel that there's no accompanying stab of pain."
						+ "</p>");
			}
		}
		
		
		sb.append(UtilText.formatVirginityLoss("You'll always remember this moment as the time that you lost your anal virginity!"));

		if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
			sb.append("<p style='text-align:center;'>"
										+ "[style.italicsArcane(Due to [npc.namePos] deflowering fetish, [npc.she] [npc.verb(gain)])]"
										+ " [style.italicsExperience("+AbstractFetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating)+")] [style.italicsArcane(experience!)]"
								+ "</p>");
		}
		
		return UtilText.parse(characterPenetrating, sb.toString());
	
	}
	
	@Override
	protected String getVaginaVirginityLossDescription(GameCharacter characterPenetrating, SexAreaPenetration penetration){ //TODO need to take in account penetration depth and stretching
		StringBuilder sb = new StringBuilder();
		
		String penetrationName = "";
		switch(penetration) {
			case CLIT:
				penetrationName = "[npc.clit+]";
				break;
			case PENIS:
				penetrationName = "[npc.penis+]";
				break;
			case TAIL:
				penetrationName = "[npc.tail+]";
				break;
			case TENTACLE:
				penetrationName = "[npc.tentacle+]";
				break;
			default:
				break;
		}
		
		if(characterPenetrating.isPlayer()) { // SELF-PENETRATION
			// Initial penetration:
			sb.append("<p>");
				if(Main.game.isInSex() && !Main.sex.hasLubricationTypeFromAnyone(Main.game.getPlayer(), SexAreaOrifice.VAGINA)) { // Dry:
					if(Main.game.getPlayer().hasHymen()) {
						sb.append("As you drive your "+penetrationName+" deep into your dry [pc.pussy], your vision suddenly explodes in stars, and a painful, high-pitched shriek escapes from between your [pc.lips]."
									+ " Being penetrated without any form of lubrication would be uncomfortable at the best of times, but due to the fact that you've also just torn your hymen, it's more than just a little discomfort,"
										+ " and your shriek turns into a shuddering cry as you shuffle about in pure agony.");
					} else {
						sb.append("As you drive your "+penetrationName+" deep into your dry [pc.pussy], you can't help but let out an uncomfortable groan."
								+ " Being penetrated without any form of lubrication would be uncomfortable at the best of times, but due to the fact that this is the moment in which you're taking your own virginity,"
									+ " you're entirely unused to the sensation you're currently experiencing, and find it to be extremely unpleasant.");
					}
					
				} else { // Wet:
					if(Main.game.getPlayer().hasHymen()) {
						sb.append("As you drive your "+penetrationName+" deep into your [pc.pussy+], your vision suddenly narrows down, and a painful, desperate wail escapes from between your [pc.lips]."
										+ " Luckily, your pussy was lubricated before being penetrated, but due to the fact that you've also just torn your hymen, it isn't enough to completely prevent the pain you now feel in your groin,"
											+ " and your wail turns into a shuddering moan as you shuffle about in discomfort.");
					} else {
						sb.append("As you drive your "+penetrationName+" deep into your [pc.pussy+], your vision suddenly narrows down, and a lewd [pc.moan] bursts out from between your [pc.lips]."
								+ " As you're well lubricated, and you've already lost your hymen, the first experience of having your virgin pussy penetrated is not at all an unpleasant one,"
									+ " and you let out a second eager [pc.moan] as you focus on the amazing feeling of your "+penetrationName+" thrusting into it.");
					}
				}
			sb.append("</p>");

			sb.append("<p>");
				if(Main.game.getPlayer().hasHymen()) {
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)) {
						sb.append("Due to being an extreme masochist, you find your painful cries being interspersed with lewd moans of pleasure."
									+ " The agony between your legs is pure bliss, and you focus on the pain as you squeal and moan in a delightful haze of overwhelming ecstasy."
									+ "  Unfortunately for your masochistic desires, your hymen is now torn, and so the pain isn't as extreme as before, but even without this, you find yourself starting to let out lustful [pc.moans].");
					} else {
						sb.append("Instinctively trying to clench your legs together, you let out another painful wail as you draw your "+penetrationName+" back, before thrusting deep inside yourself once again."
									+ " As your hymen is now torn, the pain isn't as extreme as before, and your pained cries start to turn into lustful [pc.moans].");
					}
					
				} else {
					sb.append("Starting to get into the flow of things, you eagerly draw your "+penetrationName+" back, before thrusting deep inside yourself once again."
								+ " As you start fucking yourself, you can't help but let out a series of extremely lewd, lustful [pc.moans].");
				}
			sb.append("</p>");
			
			// Ending:
			sb.append("<p>");
				if(Main.game.getPlayer().hasHymen()) {
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
						sb.append("With the initial pain now having faded away into a faint, dull ache, you can't help but let out one final whimper as you feel a little trickle of blood running out of your broken-in pussy."
									+ " Picking up the pace, you thrust your "+penetrationName+" into your [pc.pussy+] once again, letting out [pc.a_moan+] as you succumb to this new pleasurable experience...");
					} else {
						sb.append("With the initial pain now having faded away into a faint, dull ache, you let out one final whimper as you reflect on the fact that this is how you'll always remember losing your virginity."
								+ " Picking up the pace, you thrust your "+penetrationName+" into your [pc.pussy+] once again, letting out [pc.a_moan+] as you succumb to this new pleasurable experience...");
					}
				} else {
					sb.append("Not having had to experience the pain of losing your hymen, you let out a deep [pc.moan] as you reflect on the fact that this is how you'll always remember losing your virginity."
							+ " Picking up the pace, you thrust your "+penetrationName+" into your [pc.pussy+] once again, letting out [pc.a_moan+] as you succumb to this new pleasurable experience...");
				}
			sb.append("</p>");
			
		} else {
			if((characterPenetrating instanceof NPC) && ((NPC)characterPenetrating).getSpecialPlayerVirginityLoss(characterPenetrating, penetration, this, SexAreaOrifice.VAGINA)!=null) {
				sb.append(((NPC)characterPenetrating).getSpecialPlayerVirginityLoss(characterPenetrating, penetration, this, SexAreaOrifice.VAGINA));
				
			} else {
				sb.append("<p>");
					if(Main.game.isInSex() && !Main.sex.hasLubricationTypeFromAnyone(Main.game.getPlayer(), SexAreaOrifice.VAGINA)) { // Dry:
						if(Main.game.getPlayer().hasHymen()) {
							sb.append("As [npc.name] drives [npc.her] "+penetrationName+" deep into your dry [pc.pussy], your vision suddenly explodes in stars, and a painful, high-pitched shriek escapes from between your [pc.lips]."
										+ " Being penetrated without any form of lubrication would be uncomfortable at the best of times, but due to the fact that [npc.nameHasFull] also just torn through your hymen, it's more than just a little discomfort,"
											+ " and your shriek turns into a shuddering cry as you shuffle about in pure agony.");
						} else {
							sb.append("As [npc.name] drives [npc.her] "+penetrationName+" deep into your dry [pc.pussy], you can't help but let out an uncomfortable groan."
									+ " Being penetrated without any form of lubrication would be uncomfortable at the best of times, but due to the fact that this is the moment in which [npc.name] is taking your virginity,"
										+ " you're entirely unused to the sensation you're currently experiencing, and find it to be extremely unpleasant.");
						}
						
					} else { // Wet:
						if(Main.game.getPlayer().hasHymen()) {
							sb.append("As [npc.name] drives [npc.her] "+penetrationName+" deep into your [pc.pussy+], your vision suddenly narrows down, and a painful, desperate wail escapes from between your [pc.lips]."
											+ " Luckily, your pussy was lubricated before being penetrated, but due to the fact that [npc.nameHasFull] also just torn through your hymen,"
												+ " it isn't enough to completely prevent the pain you now feel in your groin, and your wail turns into a shuddering moan as you shuffle about in discomfort.");
						} else {
							sb.append("As [npc.name] drives [npc.her] "+penetrationName+" deep into your [pc.pussy+], your vision suddenly narrows down, and a lewd [pc.moan] bursts out from between your [pc.lips]."
									+ " As you're well lubricated, and you've already lost your hymen, the first experience of having your virgin pussy penetrated is not at all an unpleasant one,"
										+ " and you let out a second eager [pc.moan] as you focus on the amazing feeling of [npc.namePos] "+penetrationName+" thrusting into it.");
						}
					}
				sb.append("</p>");
	
				sb.append("<p>");
					if(Main.game.getPlayer().hasHymen()) {
						if(characterPenetrating.hasFetish(Fetish.FETISH_SADIST)) {
							sb.append("[npc.She] lets out an evil laugh as [npc.she] causes you to writhe about in pain, [npc.her] sadistic nature fuelling [npc.her] rough thrusts into your pussy as [npc.she] ruthlessly tears through your hymen.");
						}
						if(Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)) {
							sb.append(" Due to being an extreme masochist, you find your painful cries being interspersed with lewd moans of pleasure."
										+ " The agony between your legs is pure bliss, and you focus on the pain as you squeal and moan in a delightful haze of overwhelming ecstasy."
										+ "  Unfortunately for your masochistic desires, your hymen is now torn, and so the pain isn't as extreme as before, but even without this, you find yourself starting to let out lustful [pc.moans].");
						} else {
							sb.append(" Instinctively trying to clench your legs together, you let out another painful wail as [npc.name] draws [npc.her] "+penetrationName+" back, before thrusting deep inside you once again."
										+ " As your hymen is now torn, the pain isn't as extreme as before, and your pained cries start to turn into lustful [pc.moans].");
						}
						
					} else {
						sb.append("Starting to get into the flow of things, you let out [pc.a_moan+] as [npc.name] draws [npc.her] "+penetrationName+" back, before thrusting deep inside you once again."
									+ " As [npc.she] starts fucking you, you can't help but let out a series of extremely lewd, lustful [pc.moans].");
					}
				sb.append("</p>");
				
				// Partner deflowering reaction:
				if(!characterPenetrating.isMute()) {
					sb.append("<p>");
						if(Main.game.isInSex()) {
							switch(Main.sex.getSexPace(characterPenetrating)) {
								case DOM_GENTLE:
									sb.append("[npc.speech(You were a virgin?)] [npc.she] softly muses to [npc.herself],"
											+ " [npc.speech(You're a very good [pc.girl], saving your virginity for me!"
												+ " You'll always remember that it was my "+penetration.getName(characterPenetrating, true)+" which was the first to break you in...)]");
									break;
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append("[npc.speech(Oh, yes!)] [npc.she] cries,"
											+ " [npc.speech(Good [pc.girl], saving your virginity for me!"
												+ " Remember this moment, remember that <i>my</i> "+penetration.getName(characterPenetrating, true)+" was the one that broke you in!)]");
									break;
								case DOM_ROUGH:
									sb.append("[npc.speech(This is just the start, slut!)] [npc.she] roughly [npc.moansVerb],"
											+ " [npc.speech(Now that I've claimed your virginity, I'll show you how it feels to get a good, hard pounding!"
												+ " You'll never forget this feeling of my "+penetration.getName(characterPenetrating, true)+" breaking you in!)]");
									break;
								case SUB_RESISTING:
									sb.append("[npc.speech(Stop it! I didn't want to do this!)] [npc.she] complains as [npc.she] realises that [npc.sheHas] taken your virginity,"
											+ " [npc.speech(Just get off me and leave me alone!)]");
									break;
							}
						} else {
							sb.append("[npc.speech(Oh, yes!)] [npc.she] cries, [npc.speech(Good [pc.girl], saving your virginity for me!"
									+ " Remember this moment, remember that <i>my</i> "+penetration.getName(characterPenetrating, true)+" was the one that broke you in!)]");
						}
					sb.append("</p>");
				}
				
				// Ending:
				sb.append("<p>");
					if(Main.game.getPlayer().hasHymen()) {
						if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
							sb.append("With the initial pain now having faded away into a faint, dull ache, you can't help but let out one final whimper as you feel a little trickle of blood running out of your broken-in pussy."
										+ " With [npc.namePos] "+penetrationName+" thrusting into your [pc.pussy+] once again, you find yourself letting out [pc.a_moan+] as you succumb to this new pleasurable experience...");
						} else {
							sb.append("With the initial pain now having faded away into a faint, dull ache, you let out one final whimper as you reflect on the fact that this is how you'll always remember losing your virginity."
									+ " With [npc.namePos] "+penetrationName+" thrusting into your [pc.pussy+] once again, you find yourself letting out [pc.a_moan+] as you succumb to this new pleasurable experience...");
						}
					} else {
						sb.append("Not having had to experience the pain of losing your hymen, you let out a deep [pc.moan] as you reflect on the fact that this is how you'll always remember losing your virginity."
								+ " With [npc.namePos] "+penetrationName+" thrusting into your [pc.pussy+] once again, you find yourself letting out [pc.a_moan+] as you succumb to this new pleasurable experience...");
					}
				sb.append("</p>");
			}
		}
		
		if(this.hasHymen()) {
			sb.append(UtilText.formatVirginityLoss("Your hymen has been torn; you have lost your virginity!"));
			
		} else {
			sb.append(UtilText.formatVirginityLoss("Although your hymen had already been torn, you've now officially lost your virginity!"));
		}
		
		if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
			if((characterPenetrating instanceof NPC) && ((NPC)characterPenetrating).getSpecialPlayerPureVirginityLoss(characterPenetrating, penetration)!=null) {
				sb.append(((NPC)characterPenetrating).getSpecialPlayerPureVirginityLoss(characterPenetrating, penetration));
				
			} else {
				sb.append(losingPureVirginity(characterPenetrating, penetration));
			}
		}
		
		if(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)) {
			sb.append("<p style='text-align:center;'>"
						+ "[style.italicsArcane(Due to [npc.namePos] deflowering fetish, [npc.she] [npc.verb(gain)])]"
						+ " [style.italicsExperience("+AbstractFetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating)+")] [style.italicsArcane(experience!)]"
					+ "</p>");
		}
		
		return UtilText.parse(characterPenetrating, sb.toString());
	}
	
	@Override
	protected String getPenileVirginityLossDescription(GameCharacter characterPenetrated, SexAreaOrifice orifice){
		if((characterPenetrated instanceof NPC) && ((NPC)characterPenetrated).getSpecialPlayerVirginityLoss(this, SexAreaPenetration.PENIS, characterPenetrated, orifice)!=null) {
			return ((NPC)characterPenetrated).getSpecialPlayerVirginityLoss(this, SexAreaPenetration.PENIS, characterPenetrated, orifice);
		}
		
		return UtilText.parse(characterPenetrated, this,
				(characterPenetrated.equals(this)
						?UtilText.formatVirginityLoss("[npc2.Name] [npc2.has] taken [npc2.her] own penile virginity!")
						:UtilText.formatVirginityLoss("[npc.Name] [npc.has] taken [npc2.namePos] penile virginity!"))
				+(characterPenetrated.hasFetish(Fetish.FETISH_DEFLOWERING)
						?"<p style='text-align:center;'>"
							+ "[style.italicsArcane(Due to [npc.namePos] deflowering fetish, [npc.she] [npc.verb(gain)])]"
								+ " [style.italicsExperience("+AbstractFetish.getExperienceGainFromTakingOtherVirginity(characterPenetrated)+")] [style.italicsArcane(experience!)]"
						+ "</p>"
						:""));
	}

	@Override
	protected String getNippleVirginityLossDescription(GameCharacter characterPenetrating, SexAreaPenetration penetration){
		if((characterPenetrating instanceof NPC) && ((NPC)characterPenetrating).getSpecialPlayerVirginityLoss(characterPenetrating, penetration, this, SexAreaOrifice.NIPPLE)!=null) {
			return ((NPC)characterPenetrating).getSpecialPlayerVirginityLoss(characterPenetrating, penetration, this, SexAreaOrifice.NIPPLE);
		}
		
		return UtilText.parse(this, characterPenetrating,
				(this.equals(characterPenetrating)
						?UtilText.formatVirginityLoss("[npc2.Name] [npc2.has] taken [npc2.her] own nipple virginity!")
						:UtilText.formatVirginityLoss("[npc2.Name] [npc2.has] taken [npc.namePos] nipple virginity!"))
				+(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)
						?"<p style='text-align:center;'>"
							+ "[style.italicsArcane(Due to [npc2.namePos] deflowering fetish, [npc2.she] [npc2.verb(gain)])]"
								+ " [style.italicsExperience("+AbstractFetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating)+")] [style.italicsArcane(experience!)]"
						+ "</p>"
						:""));
	}

	@Override
	protected String getNippleCrotchVirginityLossDescription(GameCharacter characterPenetrating, SexAreaPenetration penetration){
		if((characterPenetrating instanceof NPC) && ((NPC)characterPenetrating).getSpecialPlayerVirginityLoss(characterPenetrating, penetration, this, SexAreaOrifice.NIPPLE_CROTCH)!=null) {
			return ((NPC)characterPenetrating).getSpecialPlayerVirginityLoss(characterPenetrating, penetration, this, SexAreaOrifice.NIPPLE_CROTCH);
		}
		
		return UtilText.parse(this, characterPenetrating,
				(this.equals(characterPenetrating)
						?UtilText.formatVirginityLoss("[npc2.Name] [npc2.has] taken [npc2.her] own [npc2.crotchNipple] virginity!")
						:UtilText.formatVirginityLoss("[npc2.Name] [npc2.has] taken [npc.namePos] [npc.crotchNipple] virginity!"))
				+(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)
						?"<p style='text-align:center;'>"
							+ "[style.italicsArcane(Due to [npc2.namePos] deflowering fetish, [npc2.she] [npc2.verb(gain)])]"
								+ " [style.italicsExperience("+AbstractFetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating)+")] [style.italicsArcane(experience!)]"
						+ "</p>"
						:""));
	}

	@Override
	protected String getUrethraVirginityLossDescription(GameCharacter characterPenetrating, SexAreaPenetration penetration){
		if((characterPenetrating instanceof NPC) && ((NPC)characterPenetrating).getSpecialPlayerVirginityLoss(characterPenetrating, penetration, this, SexAreaOrifice.URETHRA_PENIS)!=null) {
			return ((NPC)characterPenetrating).getSpecialPlayerVirginityLoss(characterPenetrating, penetration, this, SexAreaOrifice.URETHRA_PENIS);
		}
		
		return UtilText.parse(this, characterPenetrating,
				(this.equals(characterPenetrating)
						?UtilText.formatVirginityLoss("[npc2.Name] [npc2.has] taken [npc2.her] own urethral virginity!")
						:UtilText.formatVirginityLoss("[npc2.Name] [npc2.has] taken [npc.namePos] urethral virginity!"))
				+(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)
						?"<p style='text-align:center;'>"
							+ "[style.italicsArcane(Due to [npc2.namePos] deflowering fetish, [npc2.she] [npc2.verb(gain)])]"
								+ " [style.italicsExperience("+AbstractFetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating)+")] [style.italicsArcane(experience!)]"
						+ "</p>"
						:""));
	}

	@Override
	protected String getVaginalUrethraVirginityLossDescription(GameCharacter characterPenetrating, SexAreaPenetration penetration){
		if((characterPenetrating instanceof NPC) && ((NPC)characterPenetrating).getSpecialPlayerVirginityLoss(characterPenetrating, penetration, this, SexAreaOrifice.URETHRA_VAGINA)!=null) {
			return ((NPC)characterPenetrating).getSpecialPlayerVirginityLoss(characterPenetrating, penetration, this, SexAreaOrifice.URETHRA_VAGINA);
		}
		
		return UtilText.parse(this, characterPenetrating,
				(this.equals(characterPenetrating)
						?UtilText.formatVirginityLoss("[npc2.Name] [npc2.has] taken [npc2.her] own urethral virginity!")
						:UtilText.formatVirginityLoss("[npc2.Name] [npc2.has] taken [npc.namePos] urethral virginity!"))
				+(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)
						?"<p style='text-align:center;'>"
							+ "[style.italicsArcane(Due to [npc2.namePos] deflowering fetish, [npc2.she] [npc2.verb(gain)])]"
								+ " [style.italicsExperience("+AbstractFetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating)+")] [style.italicsArcane(experience!)]"
						+ "</p>"
						:""));
	}

	@Override
	protected String getMouthVirginityLossDescription(GameCharacter characterPenetrating, SexAreaPenetration penetration){
		if((characterPenetrating instanceof NPC) && ((NPC)characterPenetrating).getSpecialPlayerVirginityLoss(characterPenetrating, penetration, this, SexAreaOrifice.MOUTH)!=null) {
			return ((NPC)characterPenetrating).getSpecialPlayerVirginityLoss(characterPenetrating, penetration, this, SexAreaOrifice.MOUTH);
		}
		
		return UtilText.parse(this, characterPenetrating,
				(this.equals(characterPenetrating)
						?UtilText.formatVirginityLoss("[npc2.Name] [npc2.has] given [npc2.herself] [npc.her] first oral experience!")
						:UtilText.formatVirginityLoss("[npc2.Name] [npc2.has] given [npc.name] [npc.her] first oral experience!"))
				+(characterPenetrating.hasFetish(Fetish.FETISH_DEFLOWERING)
						?"<p style='text-align:center;'>"
							+ "[style.italicsArcane(Due to [npc2.namePos] deflowering fetish, [npc2.she] [npc2.verb(gain)])]"
								+ " [style.italicsExperience("+AbstractFetish.getExperienceGainFromTakingOtherVirginity(characterPenetrating)+")] [style.italicsArcane(experience!)]"
						+ "</p>"
						:""));
	}
}
