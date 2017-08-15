package com.base.game.character;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.base.game.character.gender.Gender;
import com.base.game.character.race.Race;
import com.base.game.character.race.RaceStage;
import com.base.game.character.race.RacialBody;
import com.base.game.inventory.CharacterInventory;
import com.base.game.inventory.ShopTransaction;
import com.base.game.inventory.clothing.ClothingType;
import com.base.game.inventory.clothing.CoverableArea;
import com.base.game.inventory.item.ItemType;
import com.base.game.inventory.weapon.WeaponType;
import com.base.utils.Colour;
import com.base.utils.SizedStack;
import com.base.world.WorldType;
import com.base.world.places.PlaceInterface;

/**
 * @since 0.1.0
 * @version 0.1.82
 * @author Innoxia
 */
public class PlayerCharacter extends GameCharacter {

	private static final long serialVersionUID = 1L;

	private String title;

	private Map<QuestLine, Integer> quests;

	private boolean mainQuestUpdated, sideQuestUpdated, romanceQuestUpdated,
		newWeaponDiscovered, newClothingDiscovered, newItemDiscovered, newRaceDiscovered;

	// Trader buy-back:
	private SizedStack<ShopTransaction> buybackStack;

	// Gameplay stats:
	private Set<ItemType> itemsDiscovered;
	private Set<WeaponType> weaponsDiscovered;
	private Set<ClothingType> clothingDiscovered;
	private List<GameCharacter> charactersEncountered;
	private List<Race> racesDiscovered, racesAdvancedKnowledge;



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
		
		newWeaponDiscovered = false;
		newClothingDiscovered = false;
		newItemDiscovered = false;
		newRaceDiscovered = false;

		buybackStack = new SizedStack<>(24);

		itemsDiscovered = new HashSet<>();
		weaponsDiscovered = new HashSet<>();
		clothingDiscovered = new HashSet<>();
		charactersEncountered = new ArrayList<>();

		racesDiscovered = new ArrayList<>();
		racesAdvancedKnowledge = new ArrayList<>();
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
	
	public boolean isSlaveTrader() {
		return isQuestCompleted(QuestLine.SIDE_SLAVERY);
	}

	// Other stuff:

	public boolean isNewWeaponDiscovered() {
		return newWeaponDiscovered;
	}

	public void setNewWeaponDiscovered(boolean newWeaponDiscovered) {
		this.newWeaponDiscovered = newWeaponDiscovered;
	}

	public boolean isNewClothingDiscovered() {
		return newClothingDiscovered;
	}

	public void setNewClothingDiscovered(boolean newClothingDiscovered) {
		this.newClothingDiscovered = newClothingDiscovered;
	}

	public boolean isNewItemDiscovered() {
		return newItemDiscovered;
	}

	public void setNewItemDiscovered(boolean newItemDiscovered) {
		this.newItemDiscovered = newItemDiscovered;
	}

	public Set<ItemType> getItemsDiscovered() {
		return itemsDiscovered;
	}

	public Set<WeaponType> getWeaponsDiscovered() {
		return weaponsDiscovered;
	}

	public Set<ClothingType> getClothingDiscovered() {
		return clothingDiscovered;
	}

	public List<GameCharacter> getCharactersEncountered() {
		return charactersEncountered;
	}

	public void addCharacterEncountered(GameCharacter character) {
		if (!charactersEncountered.contains(character)) {
			charactersEncountered.add(character);
		}
	}

	public boolean isNewRaceDiscovered() {
		return newRaceDiscovered;
	}

	public void setNewRaceDiscovered(boolean newRaceDiscovered) {
		this.newRaceDiscovered = newRaceDiscovered;
	}
	
	public boolean addRaceDiscovered(Race race) {
		if(!racesDiscovered.contains(race)) {
			racesDiscovered.add(race);
			racesDiscovered.sort((Race r1, Race r2) -> (r1.getName()).compareTo(r2.getName()));
			newRaceDiscovered = true;
			return true;
		} else {
			return false;
		}
	}

	public List<Race> getRacesDiscovered() {
		return racesDiscovered;
	}
	
	public boolean addRacesAdvancedKnowledge(Race race) {
		if(!racesAdvancedKnowledge.contains(race)) {
			racesAdvancedKnowledge.add(race);
			racesAdvancedKnowledge.sort((Race r1, Race r2) -> (r1.getName()).compareTo(r2.getName()));
			newRaceDiscovered = true;
			return true;
		} else {
			return false;
		}
	}

	public List<Race> getRacesAdvancedKnowledge() {
		return racesAdvancedKnowledge;
	}
	
	public SizedStack<ShopTransaction> getBuybackStack() {
		return buybackStack;
	}

}
