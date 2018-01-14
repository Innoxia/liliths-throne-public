package com.lilithsthrone.game.character;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.ShopTransaction;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.SizedStack;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.1.89
 * @author Innoxia
 */
public class PlayerCharacter extends GameCharacter implements XMLSaving {

	private static final long serialVersionUID = 1L;

	private String title;
	
	private int karma;

	private Map<QuestLine, Integer> quests;

	private boolean mainQuestUpdated, sideQuestUpdated, romanceQuestUpdated;

	private Set<AbstractItemType> booksRead;
	
	// Trader buy-back:
	private SizedStack<ShopTransaction> buybackStack;

	private List<String> charactersEncountered;
	
	public PlayerCharacter(NameTriplet nameTriplet, String description, int level, Gender gender, RacialBody startingRace, RaceStage stage, CharacterInventory inventory, WorldType startingWorld, PlaceType startingPlace) {
		super(nameTriplet, description, level, gender, startingRace, stage, new CharacterInventory(0), startingWorld, startingPlace);

		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		
		for(CoverableArea ca : CoverableArea.values()) {
			playerKnowsAreasMap.put(ca, true);
		}
		
		title = "The Human";
		
		karma = 0;
		
		quests = new EnumMap<>(QuestLine.class);

		mainQuestUpdated = false;
		sideQuestUpdated = false;
		romanceQuestUpdated = false;
		
		booksRead = new HashSet<>();

		buybackStack = new SizedStack<>(24);

		charactersEncountered = new ArrayList<>();
		
		attributes.put(Attribute.CORRUPTION, 0f);
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
		CharacterUtils.createXMLElementWithValue(doc, playerSpecific, "romanceQuestUpdated", String.valueOf(this.romanceQuestUpdated));
		
		Element innerElement = doc.createElement("booksRead");
		playerSpecific.appendChild(innerElement);
		for(AbstractItemType book : booksRead) {
			CharacterUtils.createXMLElementWithValue(doc, innerElement, "book", book.getId());
		}
		
		Element charactersEncounteredElement = doc.createElement("charactersEncountered");
		playerSpecific.appendChild(charactersEncounteredElement);
		for(String id : charactersEncountered) {
			CharacterUtils.createXMLElementWithValue(doc, charactersEncounteredElement, "id", id);
		}
		
		innerElement = doc.createElement("questMap");
		playerSpecific.appendChild(innerElement);
		for(Entry<QuestLine, Integer> entry : quests.entrySet()) {
			Element e = doc.createElement("entry");
			innerElement.appendChild(e);
			CharacterUtils.addAttribute(doc, e, "questLine", entry.getKey().toString());
			CharacterUtils.addAttribute(doc, e, "progress", String.valueOf(entry.getValue()));
		}
		
//		private SizedStack<ShopTransaction> buybackStack; TODO
		
//		Element slavesOwned = doc.createElement("slavesExported");
//		properties.appendChild(slavesOwned);
//		for(String id : this.getSlavesOwned()) {
//			Main.game.getNPCById(id).saveAsXML(slavesOwned, doc);
//		}
		
		return properties;
	}
	
	public static PlayerCharacter loadFromXML(StringBuilder log, Element parentElement, Document doc) {
		PlayerCharacter character = new PlayerCharacter(new NameTriplet(""), "", 0, Gender.F_V_B_FEMALE, RacialBody.HUMAN, RaceStage.HUMAN, new CharacterInventory(0), WorldType.DOMINION, PlaceType.DOMINION_AUNTS_HOME);
		
		GameCharacter.loadGameCharacterVariablesFromXML(character, log, parentElement, doc);
		
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
			if(playerSpecificElement.getElementsByTagName("romanceQuestUpdated").getLength()!=0) {
				character.setRomanceQuestUpdated(Boolean.valueOf(((Element)playerSpecificElement.getElementsByTagName("romanceQuestUpdated").item(0)).getAttribute("value")));
			}
	
			if(playerSpecificElement.getElementsByTagName("booksRead").item(0)!=null) {
				for(int i=0; i<((Element) playerSpecificElement.getElementsByTagName("booksRead").item(0)).getElementsByTagName("book").getLength(); i++){
					Element e = (Element) ((Element) playerSpecificElement.getElementsByTagName("booksRead").item(0)).getElementsByTagName("book").item(i);
					character.addBooksRead(ItemType.idToItemMap.get(e.getAttribute("value")));
				}
			}
	
			if(playerSpecificElement.getElementsByTagName("charactersEncountered").item(0)!=null) {
				for(int i=0; i<((Element) playerSpecificElement.getElementsByTagName("charactersEncountered").item(0)).getElementsByTagName("id").getLength(); i++){
					Element e = (Element) ((Element) playerSpecificElement.getElementsByTagName("charactersEncountered").item(0)).getElementsByTagName("id").item(i);
					character.addCharacterEncountered(e.getAttribute("value"));
				}
			}
	
			if(playerSpecificElement.getElementsByTagName("questMap").item(0)!=null) {
				for(int i=0; i<((Element) playerSpecificElement.getElementsByTagName("questMap").item(0)).getElementsByTagName("entry").getLength(); i++){
					Element e = (Element) ((Element) playerSpecificElement.getElementsByTagName("questMap").item(0)).getElementsByTagName("entry").item(i);
					character.quests.put(
							QuestLine.valueOf(e.getAttribute("questLine")),
							Integer.valueOf(e.getAttribute("progress")));
				}
			}
		}
		
		
//		// Slaves:
//		
//		Element slavesOwned = (Element) parentElement.getElementsByTagName("slavesExported").item(0);
//		if(slavesOwned!=null) {
//			for(int i=0; i< slavesOwned.getElementsByTagName("character").getLength(); i++){
//				Element e = ((Element)slavesOwned.getElementsByTagName("character").item(i));
//				
//				SlaveImport slave = SlaveImport.loadFromXML2(log, e, doc);
//				
//				//TODO move into slave's import:
//				slave.setMana(slave.getAttributeValue(Attribute.MANA_MAXIMUM));
//				slave.setHealth(slave.getAttributeValue(Attribute.HEALTH_MAXIMUM));
//				slave.setStamina(slave.getAttributeValue(Attribute.STAMINA_MAXIMUM));
//				
//				try {
//					Main.game.getSlaveImports().add(slave);
////					character.addSlave(slave);
//					slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
//					
//				} catch (Exception e1) {
//					e1.printStackTrace();
//				}
//			}
//		}
		
		
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getKarma() {
		return karma;
	}

	public void setKarma(int karma) {
		this.karma = karma;
	}
	
	/**
	 * This is just an internal stat that isn't used for anything, other than to help me feel better about writing horrible scenes.</br></br>
	 * 
	 * -100 would be for something huge, like attacking and enslaving one of your children.</br>
	 * -10 would be for something large, like stealing from someone.</br>
	 * -1 would be for something small, like insulting someone who doesn't deserve it.</br>
	 * 0 = neutral</br>
	 * +1 would be for something small, like giving a gift.</br>
	 * +10 would be for something large, like sacrificing your own well-being to help another person.</br>
	 * +100 would be for something huge, like buying and then immediately freeing a slave.</br>
	 * @param increment
	 */
	public void incrementKarma(int increment) {
		this.karma += increment;
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

	public boolean isRomanceQuestUpdated() {
		return romanceQuestUpdated;
	}

	public void setRomanceQuestUpdated(boolean romanceQuestUpdated) {
		this.romanceQuestUpdated = romanceQuestUpdated;
	}

	/**
	 * <b>Please use incrementQuest() instead! This is a utility method for game imports.</b>
	 */
	public void setQuestProgress(QuestLine questLine, int progress) {
		quests.put(questLine, progress);
	}
	
	/**
	 * Increments the quest to the next stage.
	 * 
	 * @return Description of new quest added.
	 */
	public String incrementQuest(QuestLine questLine) {
		
		if (questLine.getType() == QuestType.MAIN) {
			setMainQuestUpdated(true);
			
		} else if (questLine.getType() == QuestType.SIDE) {
			setSideQuestUpdated(true);
			
		} else {
			setRomanceQuestUpdated(true);
		}
		
		if(quests.containsKey(questLine)) {
			int progress = quests.get(questLine)+1;
			incrementExperience(questLine.getQuestArray()[quests.get(questLine)].getExperienceReward());
			
			quests.put(questLine, progress);
			
			Quest quest = questLine.getQuestArray()[quests.get(questLine)-1];
			
			if (questLine.isCompleted(quests.get(questLine))) {
				return "<p style='text-align:center;'>"
						+ "<b style='color:" + questLine.getType().getColour().toWebHexString() + ";'>Quest - " + questLine.getName() + "</b></br>"
						+ "<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Task Completed</b><b> - "+quest.getName()+"</b> <b style='color:"+Colour.GENERIC_EXPERIENCE.toWebHexString()+";'>+"+quest.getExperienceReward()+" xp</b></br>"
						+ "<b>All Tasks Completed!</b></p>";
			} else {
				return "<p style='text-align:center;'>"
						+ "<b style='color:" + questLine.getType().getColour().toWebHexString() + ";'>Quest - " + questLine.getName() + "</b></br>"
						+ "<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Task Completed</b><b> - "+quest.getName()+"</b> <b style='color:"+Colour.GENERIC_EXPERIENCE.toWebHexString()+";'>+"+quest.getExperienceReward()+" xp</b></br>"
						+ "<b>New Task - " + questLine.getQuestArray()[quests.get(questLine)].getName() + "</b></p>";
			}
			
		} else {
			quests.put(questLine, 0);
			
			return "<p style='text-align:center;'>"
					+ "<b style='color:" + questLine.getType().getColour().toWebHexString() + ";'>New Quest - " + questLine.getName() + "</b></br>"
					+ "<b>New Task - " + questLine.getQuestArray()[quests.get(questLine)].getName() + "</b></p>";
		}
		
	}
	
	public Map<QuestLine, Integer> getQuests() {
		return quests;
	}
	
	public Quest getQuest(QuestLine questLine) {
		if(!hasQuest(questLine)) {
			return null;
		}
		
		if(quests.get(questLine) >= questLine.getQuestArray().length) {
			return questLine.getQuestArray()[questLine.getQuestArray().length-1];
		}
		
		return questLine.getQuestArray()[quests.get(questLine)];
	}
	
	public boolean hasQuest(QuestLine questLine) {
		return quests.containsKey(questLine);
	}

	public boolean isQuestCompleted(QuestLine questLine) {
		if (!quests.containsKey(questLine)) {
			return false;
		}
		return questLine.isCompleted(quests.get(questLine));
	}
	
	public boolean isHasSlaverLicense() {
		return isQuestCompleted(QuestLine.SIDE_SLAVERY) || Main.game.isDebugMode();
	}
	
	public boolean isQuestProgressGreaterThan(QuestLine questLine, Quest quest) {
		boolean questInArray = false;
		for(Quest q : questLine.getQuestArray()) {
			if(quest == q) {
				questInArray = true;
				break;
			}
		}
		if(!questInArray) {
			System.err.println("Quest "+quest.toString()+" was not in QuestLine!");
			return false;
		}
		
		if(getQuest(questLine)==null) {
			System.err.println("Player does not have Quest: "+quest.toString());
			return false;
		}
		
		return getQuest(questLine).getSortingOrder() > quest.getSortingOrder();
	}
	
	public boolean isQuestProgressLessThan(QuestLine questLine, Quest quest) {
		boolean questInArray = false;
		for(Quest q : questLine.getQuestArray()) {
			if(quest == q) {
				questInArray = true;
				break;
			}
		}
		if(!questInArray) {
			System.err.println("Quest "+quest.toString()+" was not in QuestLine!");
			return false;
		}
		
		if(getQuest(questLine)==null) {
			System.err.println("Player does not have Quest: "+quest.toString());
			return false;
		}
		
		return getQuest(questLine).getSortingOrder() < quest.getSortingOrder();
	}

	// Other stuff:

	public List<String> getCharactersEncountered() {
		return charactersEncountered;
	}

	public void addCharacterEncountered(String character) {
		if (!charactersEncountered.contains(character)) {
			charactersEncountered.add(character);
		}
	}
	
	public void addCharacterEncountered(GameCharacter character) {
		if (!charactersEncountered.contains(character.getId())) {
			charactersEncountered.add(character.getId());
		}
	}
	
	public SizedStack<ShopTransaction> getBuybackStack() {
		return buybackStack;
	}

	public boolean addBooksRead(AbstractItemType book) {
		return booksRead.add(book);
	}
	
	public Set<AbstractItemType> getBooksRead() {
		return booksRead;
	}

}
