package com.lilithsthrone.game.character;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.generic.SlaveImport;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.ShopTransaction;
import com.lilithsthrone.game.inventory.clothing.CoverableArea;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.SizedStack;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.Dominion;
import com.lilithsthrone.world.places.PlaceInterface;
import com.lilithsthrone.world.places.SlaverAlley;

/**
 * @since 0.1.0
 * @version 0.1.89
 * @author Innoxia
 */
public class PlayerCharacter extends GameCharacter implements XMLSaving {

	private static final long serialVersionUID = 1L;

	private String title;

	private Map<QuestLine, Integer> quests;

	private boolean mainQuestUpdated, sideQuestUpdated, romanceQuestUpdated;

	private Set<AbstractItemType> booksRead;
	
	// Trader buy-back:
	private SizedStack<ShopTransaction> buybackStack;

	private List<String> charactersEncountered;
	
	public PlayerCharacter(NameTriplet nameTriplet, String description, int level, Gender gender, RacialBody startingRace, RaceStage stage, CharacterInventory inventory, WorldType startingWorld, PlaceInterface startingPlace) {
		super(nameTriplet, description, level, gender, startingRace, stage, new CharacterInventory(0), startingWorld, startingPlace);

		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		
		for(CoverableArea ca : CoverableArea.values()) {
			playerKnowsAreasMap.put(ca, true);
		}
		
		title = "The Human";

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
		
		
		
//		private Map<QuestLine, Integer> quests;
//
//		private boolean mainQuestUpdated, sideQuestUpdated, romanceQuestUpdated;
//
//		private Set<AbstractItemType> booksRead;
//		
//		private SizedStack<ShopTransaction> buybackStack;
//		private List<String> charactersEncountered;
		
		
		Element slavesOwned = doc.createElement("slavesExported");
		properties.appendChild(slavesOwned);
		for(String id : this.getSlavesOwned()) {
			Main.game.getNPCById(id).saveAsXML(slavesOwned, doc);
		}
		
		return properties;
	}
	
	public static PlayerCharacter loadFromXML(StringBuilder log, Element parentElement, Document doc) {
		PlayerCharacter character = new PlayerCharacter(new NameTriplet(""), "", 0, Gender.F_V_B_FEMALE, RacialBody.HUMAN, RaceStage.HUMAN, new CharacterInventory(0), WorldType.DOMINION, Dominion.CITY_AUNTS_HOME);
		
		GameCharacter.loadGameCharacterVariablesFromXML(character, log, parentElement, doc);
		
		
		// Slaves:
		
		Element slavesOwned = (Element) parentElement.getElementsByTagName("slavesExported").item(0);
		if(slavesOwned!=null) {
			for(int i=0; i< slavesOwned.getElementsByTagName("character").getLength(); i++){
				Element e = ((Element)slavesOwned.getElementsByTagName("character").item(i));
				
				SlaveImport slave = SlaveImport.loadFromXML(log, e, doc);
				
				//TODO move into slave's import:
				slave.setMana(slave.getAttributeValue(Attribute.MANA_MAXIMUM));
				slave.setHealth(slave.getAttributeValue(Attribute.HEALTH_MAXIMUM));
				slave.setStamina(slave.getAttributeValue(Attribute.STAMINA_MAXIMUM));
				
				try {
					Main.game.getSlaveImports().add(slave);
//					character.addSlave(slave);
					slave.setLocation(WorldType.SLAVER_ALLEY, SlaverAlley.SLAVERY_ADMINISTRATION, true);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		
		character.setMana(character.getAttributeValue(Attribute.MANA_MAXIMUM));
		character.setHealth(character.getAttributeValue(Attribute.HEALTH_MAXIMUM));
		character.setStamina(character.getAttributeValue(Attribute.STAMINA_MAXIMUM));
		
		character.setLocation(new Vector2i(0, 0));
		
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

	// Quests:

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
