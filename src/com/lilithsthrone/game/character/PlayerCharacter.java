package com.lilithsthrone.game.character;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.ShopTransaction;
import com.lilithsthrone.game.inventory.clothing.CoverableArea;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.SizedStack;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceInterface;

/**
 * @since 0.1.0
 * @version 0.1.84
 * @author Innoxia
 */
public class PlayerCharacter extends GameCharacter {

	private static final long serialVersionUID = 1L;

	private String title;

	private Map<QuestLine, Integer> quests;

	private boolean mainQuestUpdated, sideQuestUpdated, romanceQuestUpdated;

	// Trader buy-back:
	private SizedStack<ShopTransaction> buybackStack;

	private List<GameCharacter> charactersEncountered;
	
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

		buybackStack = new SizedStack<>(24);

		charactersEncountered = new ArrayList<>();
		
		attributes.put(Attribute.CORRUPTION, 0f);
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

	public List<GameCharacter> getCharactersEncountered() {
		return charactersEncountered;
	}

	public void addCharacterEncountered(GameCharacter character) {
		if (!charactersEncountered.contains(character)) {
			charactersEncountered.add(character);
		}
	}
	
	public SizedStack<ShopTransaction> getBuybackStack() {
		return buybackStack;
	}

}
