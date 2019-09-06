package com.lilithsthrone.game.character;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
import com.lilithsthrone.game.character.body.types.SkinType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.DominionClubNPC;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.misc.NPCOffspring;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.Elizabeth;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.quests.QuestType;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntry;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.ShopTransaction;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.sex.CondomFailure;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.managers.submission.SMLyssiethDemonTF;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionOrgasmOverride;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.SizedStack;
import com.lilithsthrone.utils.TreeNode;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.4
 * @author Innoxia
 */
public class PlayerCharacter extends GameCharacter implements XMLSaving {
	
	private String title;
	
	private int karma;

	private Map<QuestLine, Quest> quests;

	private boolean mainQuestUpdated, sideQuestUpdated, relationshipQuestUpdated;

	private Set<Subspecies> racesDiscoveredFromBook;
	
	protected List<String> friendlyOccupants;
	
	// Trader buy-back:
	private SizedStack<ShopTransaction> buybackStack;

	private List<String> charactersEncountered;

	private List<WorldType> worldsVisited;
	
	public PlayerCharacter(NameTriplet nameTriplet, int level, LocalDateTime birthday, Gender gender, Subspecies startingSubspecies, RaceStage stage, WorldType startingWorld, AbstractPlaceType startingPlace) {
		super(nameTriplet, "", "", level, Main.game.getDateNow().minusYears(22), gender, startingSubspecies, stage, new CharacterInventory(0), startingWorld, startingPlace);

		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		
		title = "The Human";
		
		karma = 0;
		
		for(PersonalityTrait trait : PersonalityTrait.values()) {
			this.setPersonalityTrait(trait, PersonalityWeight.AVERAGE);
		}
		
		this.setMaxCompanions(1);
		
		quests = new EnumMap<>(QuestLine.class);

		mainQuestUpdated = false;
		sideQuestUpdated = false;
		relationshipQuestUpdated = false;
		
		racesDiscoveredFromBook = new HashSet<>();

		buybackStack = new SizedStack<>(24);

		charactersEncountered = new ArrayList<>();

		friendlyOccupants = new ArrayList<>();
		
		worldsVisited = new ArrayList<>();
		
		this.setAttribute(Attribute.MAJOR_PHYSIQUE, 0f, false);
		this.setAttribute(Attribute.MAJOR_ARCANE, 0f, false);
		this.setAttribute(Attribute.MAJOR_CORRUPTION, 0f, false);
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element properties = super.saveAsXML(parentElement, doc);
		
		Element playerSpecific = doc.createElement("playerSpecific");
		properties.appendChild(playerSpecific);
		
		CharacterUtils.createXMLElementWithValue(doc, playerSpecific, "title", this.getTitle());
		CharacterUtils.createXMLElementWithValue(doc, playerSpecific, "karma", String.valueOf(this.getKarma()));
		
		Element questUpdatesElement = doc.createElement("questUpdates");
		playerSpecific.appendChild(questUpdatesElement);
		CharacterUtils.createXMLElementWithValue(doc, playerSpecific, "mainQuestUpdated", String.valueOf(this.mainQuestUpdated));
		CharacterUtils.createXMLElementWithValue(doc, playerSpecific, "sideQuestUpdated", String.valueOf(this.sideQuestUpdated));
		CharacterUtils.createXMLElementWithValue(doc, playerSpecific, "relationshipQuestUpdated", String.valueOf(this.relationshipQuestUpdated));
		
		Element innerElement = doc.createElement("racesDiscovered");
		playerSpecific.appendChild(innerElement);
		for(Subspecies subspecies : racesDiscoveredFromBook) {
			if(subspecies != null) {
				CharacterUtils.createXMLElementWithValue(doc, innerElement, "race", subspecies.toString());
			}
		}
		
		Element charactersEncounteredElement = doc.createElement("charactersEncountered");
		playerSpecific.appendChild(charactersEncounteredElement);
		for(String id : charactersEncountered) {
			CharacterUtils.createXMLElementWithValue(doc, charactersEncounteredElement, "id", id);
		}
		
		innerElement = doc.createElement("questMap");
		playerSpecific.appendChild(innerElement);
		for(Entry<QuestLine, Quest> entry : quests.entrySet()) {
			Element e = doc.createElement("entry");
			innerElement.appendChild(e);
			CharacterUtils.addAttribute(doc, e, "questLine", entry.getKey().toString());
			CharacterUtils.addAttribute(doc, e, "quest", String.valueOf(entry.getValue()));
		}
		
		Element friendlyOccupants = doc.createElement("friendlyOccupants");
		playerSpecific.appendChild(friendlyOccupants);
		for(String occupant : this.getFriendlyOccupants()) {
			Element element = doc.createElement("occupant");
			friendlyOccupants.appendChild(element);
			
			CharacterUtils.addAttribute(doc, element, "id", occupant);
		}
		
		Element worldsVisitedElement = doc.createElement("worldsVisited");
		playerSpecific.appendChild(worldsVisitedElement);
		for(WorldType world : this.getWorldsVisited()) {
			Element element = doc.createElement("world");
			worldsVisitedElement.appendChild(element);
			
			CharacterUtils.addAttribute(doc, element, "id", world.toString());
		}
		
		
		
//		private SizedStack<ShopTransaction> buybackStack; TODO
		
//		Element slavesOwned = doc.createElement("slavesExported");
//		properties.appendChild(slavesOwned);
//		for(String id : this.getSlavesOwned()) {
//			Main.game.getNPCById(id).saveAsXML(slavesOwned, doc);
//		}
		
		return properties;
	}
	
	public static PlayerCharacter loadFromXML(StringBuilder log, Element parentElement, Document doc, CharacterImportSetting... settings) {
		PlayerCharacter character = new PlayerCharacter(new NameTriplet(""), 0, null, Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.HUMAN, WorldType.DOMINION, PlaceType.DOMINION_AUNTS_HOME);
		
		GameCharacter.loadGameCharacterVariablesFromXML(character, log, parentElement, doc, settings);
		
		character.sortInventory();
		
		NodeList nodes = parentElement.getElementsByTagName("core");
		Element element = (Element) nodes.item(0);
		String version = "";
		if(element.getElementsByTagName("version").item(0)!=null) {
			version = ((Element) element.getElementsByTagName("version").item(0)).getAttribute("value");
		}
		
		Element playerSpecificElement = (Element) parentElement.getElementsByTagName("playerSpecific").item(0);
		
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
				Element racesDiscoveredElement = (Element) playerSpecificElement.getElementsByTagName("racesDiscovered").item(0);
				if(racesDiscoveredElement != null) {
					
					NodeList races = racesDiscoveredElement.getElementsByTagName("race");
					for(int i=0; i < races.getLength(); i++){
						Element e = (Element) races.item(i);
						try {
							character.addRaceDiscoveredFromBook(Subspecies.valueOf(e.getAttribute("value")));
						} catch(Exception ex) {
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
					character.addCharacterEncountered(e.getAttribute("value"));
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
							
//							// Add one if quest is complete: (This is due to adding a 'complete quest' at the end of each quest line.)
//							if(questLine!=QuestLine.MAIN && !q.getChildren().isEmpty() && q.getChildren().get(0).getChildren().isEmpty()) {
//								q = q.getChildren().get(0);
//							}
							
							character.quests.put(
									questLine,
									q.getData());
							
						} catch(Exception ex) {
							System.err.println("ERR Quest!");
						}
					}
				} else {
					for(int i=0; i<questMapEntries.getLength(); i++){
						Element e = (Element) questMapEntries.item(i);
						try {
							String questLine = e.getAttribute("questLine");
							if(questLine.contains("SIDE_NYAN")) {
								questLine = questLine.replace("SIDE_NYAN", "RELATIONSHIP_NYAN");
							}
							
							String quest = e.getAttribute("quest");
							if(quest.contains("SIDE_NYAN")) {
								quest = quest.replace("SIDE_NYAN", "RELATIONSHIP_NYAN");
							}
							character.quests.put(
									QuestLine.valueOf(questLine),
									Quest.valueOf(quest));
						} catch(Exception ex) {
						}
					}
				}
			}
		}
		
		try {
			for(int i=0; i<((Element) playerSpecificElement.getElementsByTagName("friendlyOccupants").item(0)).getElementsByTagName("occupant").getLength(); i++){
				Element e = ((Element)playerSpecificElement.getElementsByTagName("occupant").item(i));
				
				if(!e.getAttribute("id").equals("NOT_SET")) {
					character.getFriendlyOccupants().add(e.getAttribute("id"));
					CharacterUtils.appendToImportLog(log, "<br/>Added occupant: "+e.getAttribute("id"));
				}
			}
		} catch(Exception ex) {	
		}
		
		try {
			for(int i=0; i<((Element) playerSpecificElement.getElementsByTagName("worldsVisited").item(0)).getElementsByTagName("world").getLength(); i++){
				Element e = ((Element)playerSpecificElement.getElementsByTagName("world").item(i));
				
				character.getWorldsVisited().add(WorldType.valueOf(e.getAttribute("id")));
				CharacterUtils.appendToImportLog(log, "<br/>Added world visited: "+e.getAttribute("id"));
			}
		} catch(Exception ex) {	
		}
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.5")) {
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
			if(character.getSkinType().getRace()==Race.DEMON) {
				character.setSkinType(SkinType.HUMAN);
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

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.5")) {
			character.equipBasicCombatMoves();
		}
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.4")) {
			character.ageAppearanceDifference = -Game.TIME_SKIP_YEARS;
		}
		
		return character;
	}

	@Override
	protected void updateAttributeListeners() {
		if (playerAttributeChangeEventListeners != null)
			for (CharacterChangeEventListener eventListener : playerAttributeChangeEventListeners)
				eventListener.onChange();
	}

	@Override
	protected void updateLocationListeners() {
		if (playerLocationChangeEventListeners != null)
			for (CharacterChangeEventListener eventListener : playerLocationChangeEventListeners)
				eventListener.onChange();
	}

	@Override
	public void updateInventoryListeners() {
		if (playerInventoryChangeEventListeners != null)
			for (CharacterChangeEventListener eventListener : playerInventoryChangeEventListeners)
				eventListener.onChange();
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
			return "Having been pulled into an enchanted mirror in your aunt Lily's museum, you woke up to find yourself in another world."
					+ " By a stroke of good fortune, one of the first people you met was Lilaya; this world's version of your aunt."
					+ " Having convinced her that your story is true, you're now working towards finding a way to get back to your old world.";
		}
	}
	
	@Override
	public void setLocation(WorldType worldLocation, Vector2i location, boolean setAsHomeLocation) {
		if(this.getWorldsVisited()!=null && !this.getWorldsVisited().contains(worldLocation)) {
			this.getWorldsVisited().add(worldLocation);
			if(Main.game.isStarted()) {
				Main.game.addEvent(new EventLogEntry("[style.colourExcellent(Discovered)]", Util.capitaliseSentence(worldLocation.getName())), false);
			}
		}
		
		if(Main.game.isStarted() && worldLocation!=this.getWorldLocation()) {
			Main.game.addEvent(new EventLogEntry("[style.colourMinorGood(Entered)]", Util.capitaliseSentence(worldLocation.getName())), false);
		}
		
		if(this.getWorldLocation()==WorldType.NIGHTLIFE_CLUB) {
			List<GameCharacter> clubbers = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
			clubbers.removeIf((npc) -> !(npc instanceof DominionClubNPC));
			
			WorldType worldLocationInitial = this.getWorldLocation();
			Vector2i locationInitial = this.getLocation();
			
			super.setLocation(worldLocation, location, setAsHomeLocation);
			
			for(GameCharacter clubber : clubbers) {
				clubber.setLocation(this, false);
				// TODO Why is this needed? I can't figure out why IDs are not being removed without this line:
				if(worldLocation!=worldLocationInitial || !location.equals(locationInitial)) {
					Main.game.getWorlds().get(worldLocationInitial).getCell(locationInitial).removeCharacterPresentId(clubber.getId());
				}
			}
			
		} else if(this.getWorldLocation()==WorldType.SUBMISSION) {
			super.setLocation(worldLocation, location, setAsHomeLocation);
			
			AbstractPlaceType place = Main.game.getWorlds().get(WorldType.SUBMISSION).getCell(location).getPlace().getPlaceType();
			if(place.equals(PlaceType.SUBMISSION_LILIN_PALACE_GATE) || place.equals(PlaceType.SUBMISSION_LILIN_PALACE)) {
				Main.game.getNpc(Elizabeth.class).setLocation(this, false);
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
			if(this.getRace()==Race.DEMON) {
				Set<Relationship> rSet = new LinkedHashSet<>();
				rSet.add(Relationship.HalfSibling);
				rSet.add(Relationship.Nibling);
				return rSet;
			}
			return Util.newHashSetOfValues(Relationship.Nibling);
		}
		if(character.getRace()==Race.DEMON) {
			if(character instanceof Lyssieth) {
				return Util.newHashSetOfValues(Relationship.Parent);
			}
			if(character instanceof DarkSiren) {
				return Util.newHashSetOfValues(Relationship.HalfSibling);
			}
		}
		return super.getRelationshipsTo(character, excludedRelationships);
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

	public String startQuest(QuestLine questLine) {
		return setQuestProgress(questLine, questLine.getQuestTree().getData());
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
			Quest currentQuest = questLine.getQuestTree().getFirstNodeWithData(quests.get(questLine)).getData();
			
			String experienceUpdate = incrementExperience(currentQuest.getExperienceReward(), true);
			
			quests.put(questLine, quest);
			
			if (questLine.getQuestTree().getFirstNodeWithData(quest).getChildren().isEmpty()) { // QuestLine complete (No more children in the tree)
				return "<p style='text-align:center;'>"
						+ "<b style='color:" + questLine.getType().getColour().toWebHexString() + ";'>Quest - " + questLine.getName() + "</b><br/>"
						+ "<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Task Completed</b><b> - "+currentQuest.getName()+"</b><br/>"
						+ "<b>All Tasks Completed!</b></p>"
						+ experienceUpdate;
			} else {
				return "<p style='text-align:center;'>"
						+ "<b style='color:" + questLine.getType().getColour().toWebHexString() + ";'>Quest - " + questLine.getName() + "</b><br/>"
						+ "<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Task Completed</b><br/>"
						+ "<b>New Task - " + quest.getName() + "</b></p>"
						+ experienceUpdate;
			}
			
		} else {
			quests.put(questLine, quest);
			
			return "<p style='text-align:center;'>"
					+ "<b style='color:" + questLine.getType().getColour().toWebHexString() + ";'>New Quest - " + questLine.getName() + "</b><br/>"
					+ "<b>New Task - " + quest.getName() + "</b></p>";
		}
		
	}
	
	public Map<QuestLine, Quest> getQuests() {
		return quests;
	}
	
	public Quest getQuest(QuestLine questLine) {
		return quests.get(questLine);
	}
	
	public boolean hasQuest(QuestLine questLine) {
		return quests.containsKey(questLine);
	}

	public boolean isQuestCompleted(QuestLine questLine) {
		if(!hasQuest(questLine)) {
			return false;
		}
		return questLine.getQuestTree().getFirstNodeWithData(quests.get(questLine)).getChildren().isEmpty();
	}
	
	public boolean isHasSlaverLicense() {
		return isQuestCompleted(QuestLine.SIDE_SLAVERY) || Main.game.isDebugMode();
	}
	
	public boolean isAbleToAccessRoomManagement() {
		return isHasSlaverLicense() || isQuestCompleted(QuestLine.SIDE_ACCOMMODATION);
	}
	
	public boolean isQuestProgressGreaterThan(QuestLine questLine, Quest quest) {
		if(!hasQuest(questLine)) {
			System.err.println("Player does not have Quest: "+questLine.toString()+", "+quest.toString());
			return false;
		}
		
		if(questLine.getQuestTree().getFirstNodeWithData(quest)==null) {
			System.err.println("Quest "+quest.toString()+" was not in QuestLine!");
			return false;
		}
		
		// Check to see if the current progress does not have a child with quest data:
		return questLine.getQuestTree().getFirstNodeWithData(getQuest(questLine)).getFirstNodeWithData(quest)==null;
	}
	
	public boolean isQuestProgressLessThan(QuestLine questLine, Quest quest) {
		if(!hasQuest(questLine)) {
			System.err.println("Player does not have Quest: "+quest.toString());
			return false;
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
		}
		if(Main.game.isStarted()) {
			sortCharactersEncountered();
		}
	}
	
	public void addCharacterEncountered(GameCharacter character) {
		if (!charactersEncountered.contains(character.getId())) {
			charactersEncountered.add(character.getId());
		}
		if(Main.game.isStarted()) {
			sortCharactersEncountered();
		}
	}
	
	/**
	 * @param expansiveSearch True if you want every possible character that the player has have encountered, including ones that are not usually added to the contacts list. (This will include random NPCs the player has had sex with.)
	 * @return
	 */
	public List<GameCharacter> getCharactersEncounteredAsGameCharacters(boolean expansiveSearch) {
		List<GameCharacter> npcsEncountered = new ArrayList<>();
		for(String characterId : charactersEncountered) {
			try {
				GameCharacter npc = Main.game.getNPCById(characterId);
				npcsEncountered.add(npc);
			} catch (Exception e) {
				Util.logGetNpcByIdError("getCharactersEncounteredAsGameCharacters()", characterId);
			}
		}
		
		if(expansiveSearch) {
			for(String id : this.getSexPartners().keySet()) {
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

	public boolean addRaceDiscoveredFromBook(Subspecies subspecies) {
		return racesDiscoveredFromBook.add(subspecies);
	}
	
	public Set<Subspecies> getRacesDiscoveredFromBook() {
		return racesDiscoveredFromBook;
	}

	@Override
	public String getMainAttackDescription(int armRow, GameCharacter target, boolean isHit) {
		if(this.getMainWeapon(armRow)!=null) {
			return this.getMainWeapon(armRow).getWeaponType().getAttackDescription(this, target, isHit);
		} else {
			return AbstractWeaponType.genericMeleeAttackDescription(this, target, isHit);
		}
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
	
	// This behaviour is overridden for unique scenes in which the player's orgasm requires special dialogue or effects.
	// At the time of this comment (v0.3.1), it's only used for Lilaya's creampie reaction and Lyssieth's demon TF scene.
	@Override
	public SexActionOrgasmOverride getSexActionOrgasmOverride(SexActionInterface sexAction, OrgasmCumTarget target, boolean applyExtraEffects) {
		if(Sex.getAllParticipants().contains(Main.game.getNpc(Lilaya.class))
				&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
				&& target==OrgasmCumTarget.INSIDE
				&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()
				&& this.getCurrentPenisRawCumStorageValue()>0
				&& Sex.getContactingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getNpc(Lilaya.class)).contains(SexAreaOrifice.VAGINA)) {
			
			StringBuilder sb = new StringBuilder();
			sb.append(GenericOrgasms.getGenericOrgasmDescription(sexAction, this, target));
			
			if(this.isWearingCondom()) {
				if(sexAction.getCondomFailure(this, Main.game.getNpc(Lilaya.class))!=CondomFailure.NONE) {
					Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lilayaCondomBroke, true);
					sb.append(UtilText.parseFromXMLFile("characters/dominion/lilaya", "ORGASM_REACTION_CREAMPIE_CONDOM_BROKE"));
				} else {
					Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lilayaCondomBroke, false);
					sb.append(UtilText.parseFromXMLFile("characters/dominion/lilaya", "ORGASM_REACTION_CREAMPIE_CONDOM"));
				}
			} else {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lilayaCondomBroke, false);
				sb.append(UtilText.parseFromXMLFile("characters/dominion/lilaya", "ORGASM_REACTION_CREAMPIE"));
			}
			
			return new SexActionOrgasmOverride(false, sb.toString()) {
				@Override
				public void applyEffects() {
				}
				@Override
				public boolean isEndsSex() {
					return Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)
							&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()
							&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative();
				}
			};
		}
		
		if(Sex.getSexManager() instanceof SMLyssiethDemonTF) { // Lyssieth's demon TF scene:
			StringBuilder sb = new StringBuilder();
			sb.append(GenericOrgasms.getGenericOrgasmDescription(sexAction, this, target));
			
			if(Sex.getLastUsedSexAction(Main.game.getNpc(Lyssieth.class)).getActionType()==SexActionType.ORGASM) { //These specials are only for follow-ups to Lyssieth's orgasms:
				if(Sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))==1) {
					// Stage 1) Player is sucking Lyssieth's cock:
					if(Sex.getContactingSexAreas(this, SexAreaOrifice.MOUTH, Main.game.getNpc(Lyssieth.class)).contains(SexAreaPenetration.PENIS)) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_1_PC_GIVING_LYSSIETH_BLOWJOB_END"));
						
						return new SexActionOrgasmOverride(false, sb.toString()) {
							@Override
							public void applyEffects() {
							}
						};
		
					// Stage 1) Lyssieth is sucking player's cock:
					} else if(Sex.getContactingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getNpc(Lyssieth.class)).contains(SexAreaOrifice.MOUTH)) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_1_PC_GETTING_BLOWJOB_FROM_LYSSIETH_END"));
						
						return new SexActionOrgasmOverride(false, sb.toString()) {
							@Override
							public void applyEffects() {
							}
						};
						
					// Stage 1) Lyssieth is eating the player out:
					} else if(Sex.getContactingSexAreas(this, SexAreaPenetration.TONGUE, Main.game.getNpc(Lyssieth.class)).contains(SexAreaOrifice.VAGINA)) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_1_PC_GETTING_CUNNILINGUS_FROM_LYSSIETH_END"));
						
						return new SexActionOrgasmOverride(false, sb.toString()) {
							@Override
							public void applyEffects() {
							}
						};
					}
					
				} else if(Sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))==2) {
					// Stage 2) Lyssieth is fucking the player:
					if(Sex.getContactingSexAreas(this, SexAreaOrifice.VAGINA, Main.game.getNpc(Lyssieth.class)).contains(SexAreaPenetration.PENIS)) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_2_PC_PUSSY_FUCKED_BY_LYSSIETH_END"));
						
						return new SexActionOrgasmOverride(false, sb.toString()) {
							@Override
							public void applyEffects() {
							}
							@Override
							public void applyEndEffects() {
								if(applyExtraEffects) {
									Sex.stopAllOngoingActions(Main.game.getPlayer(), Main.game.getNpc(Lyssieth.class));
								}
							}
						};
	
					// Stage 2) Lyssieth is fucking the player's ass:
					} else if(Sex.getContactingSexAreas(this, SexAreaOrifice.ANUS, Main.game.getNpc(Lyssieth.class)).contains(SexAreaPenetration.PENIS)) {
						if(Main.game.getPlayer().hasPenis() && Main.game.getPlayer().getPenisType().getRace()==Race.DEMON && Main.game.getPlayer().getPenisRawSizeValue()<=4) {
							sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_2_PC_ASS_FUCKED_BY_LYSSIETH_END_SISSY"));
						} else {
							sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_2_PC_ASS_FUCKED_BY_LYSSIETH_END"));
						}
						
						return new SexActionOrgasmOverride(false, sb.toString()) {
							@Override
							public void applyEffects() {
							}
							@Override
							public void applyEndEffects() {
								if(applyExtraEffects) {
									Sex.stopAllOngoingActions(Main.game.getPlayer(), Main.game.getNpc(Lyssieth.class));
								}
							}
						};
						
					// Stage 2) The player is fucking Lyssieth:
					} else if(Sex.getContactingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getNpc(Lyssieth.class)).contains(SexAreaOrifice.VAGINA)) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_2_PC_FUCKING_LYSSIETH_END"));
						
						return new SexActionOrgasmOverride(false, sb.toString()) {
							@Override
							public void applyEffects() {
							}
							@Override
							public void applyEndEffects() {
								if(applyExtraEffects) {
									Sex.stopAllOngoingActions(Main.game.getPlayer(), Main.game.getNpc(Lyssieth.class));
								}
							}
						};
					}
					
				} else if(Sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))==3) {
					// Stage 3) Player is fucking/breeding Lyssieth:
					if(Sex.getContactingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getNpc(Lyssieth.class)).contains(SexAreaOrifice.VAGINA)) {
						if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexSlotLyingDown.MATING_PRESS) {
							sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_BREEDING_LYSSIETH_END"));
						} else {
							sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_FUCKING_LYSSIETH_END"));
						}
						
						return new SexActionOrgasmOverride(true, sb.toString()) {
							@Override
							public void applyEffects() {
							}
						};

					// Stage 3) Lyssieth is fucking the player:
					} else if(Sex.getContactingSexAreas(this, SexAreaOrifice.VAGINA, Main.game.getNpc(Lyssieth.class)).contains(SexAreaPenetration.PENIS)) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_PUSSY_FUCKED_BY_LYSSIETH_END"));
						
						return new SexActionOrgasmOverride(true, sb.toString()) {
							@Override
							public void applyEffects() {
							}
						};

					// Stage 3) Lyssieth is fucking the player's ass:
					} else if(Sex.getContactingSexAreas(this, SexAreaOrifice.ANUS, Main.game.getNpc(Lyssieth.class)).contains(SexAreaPenetration.PENIS)) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_ASS_FUCKED_BY_LYSSIETH_END"));
						
						return new SexActionOrgasmOverride(true, sb.toString()) {
							@Override
							public void applyEffects() {
							}
						};
						
					// Stage 3) Lyssieth is sucking player's cock:
					} else if(Sex.getContactingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getNpc(Lyssieth.class)).contains(SexAreaOrifice.MOUTH)) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_GETTING_BLOWJOB_FROM_LYSSIETH_END"));
						
						return new SexActionOrgasmOverride(true, sb.toString()) {
							@Override
							public void applyEffects() {
							}
						};
						
					// Stage 3) Lyssieth is eating the player out:
					} else if(Sex.getContactingSexAreas(this, SexAreaPenetration.TONGUE, Main.game.getNpc(Lyssieth.class)).contains(SexAreaOrifice.VAGINA)) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_GETTING_CUNNILINGUS_FROM_LYSSIETH_END"));
						
						return new SexActionOrgasmOverride(true, sb.toString()) {
							@Override
							public void applyEffects() {
							}
						};
					}
				}
			}
		}

		return super.getSexActionOrgasmOverride(sexAction, target, applyExtraEffects); // Normal scene
	}
	
	/**
	 * Returns a list of NPCs either living in Lilaya's house or in an apartment known to the player.
	 */
	public List<String> getFriendlyOccupants() {
		return friendlyOccupants;
	}
	
	public boolean addFriendlyOccupant(NPC occupant) {
		return friendlyOccupants.add(occupant.getId());
	}
	
	public boolean removeFriendlyOccupant(GameCharacter occupant) {
		return friendlyOccupants.remove(occupant.getId());
	}

	public List<WorldType> getWorldsVisited() {
		return worldsVisited;
	}
	
	public boolean isDiscoveredWorldMap() {
		return this.isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN);
	}
}
