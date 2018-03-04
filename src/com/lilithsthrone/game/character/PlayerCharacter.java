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
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.quests.QuestType;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.ShopTransaction;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.SizedStack;
import com.lilithsthrone.utils.TreeNode;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.1.99
 * @author Innoxia
 */
public class PlayerCharacter extends GameCharacter implements XMLSaving {

	private static final long serialVersionUID = 1L;

	private String title;
	
	private int karma;

	private Map<QuestLine, Quest> quests;

	private boolean mainQuestUpdated, sideQuestUpdated, romanceQuestUpdated;

	private Set<AbstractItemType> booksRead;
	
	// Trader buy-back:
	private SizedStack<ShopTransaction> buybackStack;

	private List<String> charactersEncountered;
	
	public PlayerCharacter(NameTriplet nameTriplet, String description, int level, Gender gender, RacialBody startingRace, RaceStage stage, CharacterInventory inventory, WorldType startingWorld, PlaceType startingPlace) {
		super(nameTriplet, description, level, gender, startingRace, stage, new CharacterInventory(0), startingWorld, startingPlace);

		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		
		for(CoverableArea ca : CoverableArea.values()) {
			playerKnowsAreas.add(ca);
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

		this.setAttribute(Attribute.MAJOR_PHYSIQUE, 10f);
		this.setAttribute(Attribute.MAJOR_ARCANE, 0f);
		this.setAttribute(Attribute.MAJOR_CORRUPTION, 0f);
		
		this.addPerk(Perk.PHYSICAL_BASE);
		this.addPerk(Perk.ARCANE_BASE);
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
		for(Entry<QuestLine, Quest> entry : quests.entrySet()) {
			Element e = doc.createElement("entry");
			innerElement.appendChild(e);
			CharacterUtils.addAttribute(doc, e, "questLine", entry.getKey().toString());
			CharacterUtils.addAttribute(doc, e, "quest", String.valueOf(entry.getValue()));
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
		PlayerCharacter character = new PlayerCharacter(new NameTriplet(""), "", 0, Gender.F_V_B_FEMALE, RacialBody.HUMAN, RaceStage.HUMAN, new CharacterInventory(0), WorldType.DOMINION, PlaceType.DOMINION_AUNTS_HOME);
		
		GameCharacter.loadGameCharacterVariablesFromXML(character, log, parentElement, doc, settings);

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
			
			if(Main.isVersionOlderThan(version, "0.1.99.5")) {
				if(playerSpecificElement.getElementsByTagName("questMap").item(0)!=null) {
					for(int i=0; i<((Element) playerSpecificElement.getElementsByTagName("questMap").item(0)).getElementsByTagName("entry").getLength(); i++){
						Element e = (Element) ((Element) playerSpecificElement.getElementsByTagName("questMap").item(0)).getElementsByTagName("entry").item(i);
						
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
				}
				
			} else {
				if(playerSpecificElement.getElementsByTagName("questMap").item(0)!=null) {
					for(int i=0; i<((Element) playerSpecificElement.getElementsByTagName("questMap").item(0)).getElementsByTagName("entry").getLength(); i++){
						Element e = (Element) ((Element) playerSpecificElement.getElementsByTagName("questMap").item(0)).getElementsByTagName("entry").item(i);
						try {
						character.quests.put(
								QuestLine.valueOf(e.getAttribute("questLine")),
								Quest.valueOf(e.getAttribute("quest")));
						} catch(Exception ex) {
						}
					}
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
	
	@Override
	public boolean isRelatedTo(GameCharacter character) {
		if(character.equals(Main.game.getLilaya())) {
			return true;
		}
		return super.isRelatedTo(character);
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
			setRomanceQuestUpdated(true);
		}
		
		
		if(quests.containsKey(questLine)) {
			Quest currentQuest = questLine.getQuestTree().getFirstNodeWithData(quests.get(questLine)).getData();
			
			incrementExperience(currentQuest.getExperienceReward());
			
			quests.put(questLine, quest);
			
			if (questLine.getQuestTree().getFirstNodeWithData(quest).getChildren().isEmpty()) { // QuestLine complete (No more children in the tree)
				return "<p style='text-align:center;'>"
						+ "<b style='color:" + questLine.getType().getColour().toWebHexString() + ";'>Quest - " + questLine.getName() + "</b></br>"
						+ "<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Task Completed</b><b> - "+currentQuest.getName()+"</b> <b style='color:"+Colour.GENERIC_EXPERIENCE.toWebHexString()+";'>+"+currentQuest.getExperienceReward()+" xp</b></br>"
						+ "<b>All Tasks Completed!</b></p>";
			} else {
				return "<p style='text-align:center;'>"
						+ "<b style='color:" + questLine.getType().getColour().toWebHexString() + ";'>Quest - " + questLine.getName() + "</b></br>"
						+ "<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Task Completed</b><b> - "+currentQuest.getName()+"</b> <b style='color:"+Colour.GENERIC_EXPERIENCE.toWebHexString()+";'>+"+currentQuest.getExperienceReward()+" xp</b></br>"
						+ "<b>New Task - " + quest.getName() + "</b></p>";
			}
			
		} else {
			quests.put(questLine, quest);
			
			return "<p style='text-align:center;'>"
					+ "<b style='color:" + questLine.getType().getColour().toWebHexString() + ";'>New Quest - " + questLine.getName() + "</b></br>"
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
	
	public boolean isQuestProgressGreaterThan(QuestLine questLine, Quest quest) {
		if(!hasQuest(questLine)) {
			System.err.println("Player does not have Quest: "+quest.toString());
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

	@Override
	public String getMainAttackDescription(boolean isHit) {
		if(this.getMainWeapon()!=null) {
			return this.getMainWeapon().getWeaponType().getAttackDescription(this, Combat.getTargetedCombatant(this), isHit);
		} else {
			return AbstractWeaponType.genericMeleeAttackDescription(this, Combat.getTargetedCombatant(this), isHit);
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
	public String getSeductionDescription() {
		String description = "";
		
		if(this.isFeminine()) {
			description = UtilText.parse(Combat.getTargetedCombatant(this),
					UtilText.returnStringAtRandom(
					"You blow a kiss at [npc.name] and wink suggestively at [npc.herHim].",
					"Biting your lip and putting on your most smouldering look, you run your hands slowly up your inner thighs.",
					"As you give [npc.name] your most innocent look, you blow [npc.herHim] a little kiss.",
					"Turning around, you let out a playful giggle as you give your [pc.ass+] a slap.",
					"You slowly run your hands up the length of your body, before pouting at [npc.name]."));
			
		} else {
			description = UtilText.parse(Combat.getTargetedCombatant(this),
					UtilText.returnStringAtRandom(
					"You blow a kiss at [npc.name] and wink suggestively at [npc.herHim].",
					"Smiling confidently at [npc.name], you slowly run your hands up your inner thighs.",
					"As you give [npc.name] your most seductive look, you blow [npc.herHim] a little kiss.",
					"Turning around, you let out a playful laugh as you give your [pc.ass+] a slap.",
					"You try to look as commanding as possible as you smirk playfully at [npc.name]."));
		}

		return "<p>"
				+ description
				+ "</p>";
	}
}
