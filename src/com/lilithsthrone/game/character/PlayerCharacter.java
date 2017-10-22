package com.lilithsthrone.game.character;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.generic.SlaveImport;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.ShopTransaction;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.CoverableArea;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.SexType;
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
 * @version 0.1.87
 * @author Innoxia
 */
public class PlayerCharacter extends GameCharacter implements XMLSaving {

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
	public Element saveAsXML(Element parentElement, Document doc) {
		Element properties = super.saveAsXML(parentElement, doc);
		
		Element playerSpecific = doc.createElement("playerSpecific");
		properties.appendChild(playerSpecific);
		CharacterUtils.createXMLElementWithValue(doc, playerSpecific, "title", this.getTitle());
		
		Element slavesOwned = doc.createElement("slavesOwned");
		properties.appendChild(slavesOwned);
		for(NPC slaveNPC : this.getSlavesOwned()) {
			slaveNPC.saveAsXML(slavesOwned, doc);
		}
		
		return properties;
	}
	
	public static PlayerCharacter loadFromXML(StringBuilder log, Element parentElement, Document doc) {
		PlayerCharacter character = new PlayerCharacter(new NameTriplet(""), "", 0, Gender.F_V_B_FEMALE, RacialBody.HUMAN, RaceStage.HUMAN, new CharacterInventory(0), WorldType.DOMINION, Dominion.CITY_AUNTS_HOME);
		
		// Core info:
		NodeList nodes = parentElement.getElementsByTagName("core");
		Element element = (Element) nodes.item(0);

		// Name:
		character.setName(new NameTriplet(((Element)element.getElementsByTagName("name").item(0)).getAttribute("value")));
		CharacterUtils.appendToImportLog(log, "</br>Set name: " + ((Element)element.getElementsByTagName("name").item(0)).getAttribute("value"));
		
		// Surname:
		if(element.getElementsByTagName("surname")!=null && element.getElementsByTagName("surname").getLength()>0) {
			character.setSurname(((Element)element.getElementsByTagName("surname").item(0)).getAttribute("value"));
			CharacterUtils.appendToImportLog(log, "</br>Set surname: " + ((Element)element.getElementsByTagName("surname").item(0)).getAttribute("value"));
		}
		
		// Level:
		character.setLevel(Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")));
		CharacterUtils.appendToImportLog(log, "</br>Set level: " + Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")));
		
		// Sexual Orientation:
		if(element.getElementsByTagName("sexualOrientation").getLength()!=0) {
			if(!((Element)element.getElementsByTagName("sexualOrientation").item(0)).getAttribute("value").equals("null")) {
				character.setSexualOrientation(SexualOrientation.valueOf(((Element)element.getElementsByTagName("sexualOrientation").item(0)).getAttribute("value")));
				CharacterUtils.appendToImportLog(log, "</br>Set Sexual Orientation: " + SexualOrientation.valueOf(((Element)element.getElementsByTagName("sexualOrientation").item(0)).getAttribute("value")));
			} else {
				character.setSexualOrientation(SexualOrientation.AMBIPHILIC);
				CharacterUtils.appendToImportLog(log, "</br>Set Sexual Orientation: " + SexualOrientation.AMBIPHILIC);
			}
		}

		if(element.getElementsByTagName("description").getLength()!=0) {
			character.setDescription(((Element)element.getElementsByTagName("description").item(0)).getAttribute("value"));
		}
		if(element.getElementsByTagName("playerPetName").getLength()!=0) {
			character.setPlayerPetName(((Element)element.getElementsByTagName("playerPetName").item(0)).getAttribute("value"));
		}
		if(element.getElementsByTagName("playerKnowsName").getLength()!=0) {
			character.setPlayerKnowsName(Boolean.valueOf(((Element)element.getElementsByTagName("playerKnowsName").item(0)).getAttribute("value")));
		}
		if(element.getElementsByTagName("history").getLength()!=0) {
			character.setHistory(History.valueOf(((Element)element.getElementsByTagName("history").item(0)).getAttribute("value")));
		}
		if(element.getElementsByTagName("personality").getLength()!=0) {
			character.setPersonality(Personality.valueOf(((Element)element.getElementsByTagName("personality").item(0)).getAttribute("value")));
		}
		if(element.getElementsByTagName("obedience").getLength()!=0) {
			character.setObedience(Float.valueOf(((Element)element.getElementsByTagName("obedience").item(0)).getAttribute("value")));
		}
		
		
		
		
		// Temp fix for perk points:
		character.setPerkPoints((Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")))-1);
		CharacterUtils.appendToImportLog(log, "</br>Set perkPoints: (TEMP FIX) " + (Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value"))-1));
		
		int extraLevelUpPoints=0;
		// If there is a version number, attributes should be working correctly:
		if(element.getElementsByTagName("version").item(0)!=null) {
			nodes = parentElement.getElementsByTagName("attributes");
			element = (Element) nodes.item(0);
			for(int i=0; i<element.getElementsByTagName("attribute").getLength(); i++){
				Element e = ((Element)element.getElementsByTagName("attribute").item(i));
				
				try {
					character.setAttribute(Attribute.valueOf(e.getAttribute("type")), Float.valueOf(e.getAttribute("value")));
					CharacterUtils.appendToImportLog(log, "</br>Set Attribute: "+Attribute.valueOf(e.getAttribute("type")).getName() +" to "+ Float.valueOf(e.getAttribute("value")));
				}catch(IllegalArgumentException ex){
				}
			}
			
		} else {
			extraLevelUpPoints = (Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")) * 5);
			CharacterUtils.appendToImportLog(log, "</br>Old character version. Extra LevelUpPoints set to: "+(Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")) * 5));
		}

		
		nodes = parentElement.getElementsByTagName("characterRelationships");
		element = (Element) nodes.item(0);
		if(element!=null) {
			for(int i=0; i<element.getElementsByTagName("characterRelationships").getLength(); i++){
				Element e = ((Element)element.getElementsByTagName("relationship").item(i));
				
				if(e.getAttribute("character").equals("PlayerCharacter") && !character.isPlayer()) {
					character.setAffection(Main.game.getPlayer(), Float.valueOf(e.getAttribute("value")));
					CharacterUtils.appendToImportLog(log, "</br>Set Relationship: "+e.getAttribute("character") +" , "+ Float.valueOf(e.getAttribute("value")));
				}
			}
		}
		
		// Fetishes:
		nodes = parentElement.getElementsByTagName("fetishes");
		element = (Element) nodes.item(0);
		if(element!=null) {
			for(int i=0; i<element.getElementsByTagName("fetish").getLength(); i++){
				Element e = ((Element)element.getElementsByTagName("fetish").item(i));
				
				try {
					if(Fetish.valueOf(e.getAttribute("type")) != null) {
						character.addFetish(Fetish.valueOf(e.getAttribute("type")));
						CharacterUtils.appendToImportLog(log, "</br>Added Fetish: "+Fetish.valueOf(e.getAttribute("type")).getName(character));
					}
				}catch(IllegalArgumentException ex){
				}
			}
		}
		
		// Perks:
//				nodes = parentElement.getElementsByTagName("perks");
//				element = (Element) nodes.item(0);
//				for(int i=0; i<element.getElementsByTagName("perk").getLength(); i++){
//					Element e = ((Element)element.getElementsByTagName("perk").item(i));
//					
//					try {
//						if(Boolean.valueOf(e.getAttribute("value"))) {
//							character.addPerk(Perk.valueOf(e.getAttribute("type")));
//							CharacterUtils.appendToImportLog(log, "</br>Added Perk: "+Perk.valueOf(e.getAttribute("type")).getName(character));
//						}
//					}catch(IllegalArgumentException ex){
//					}
//				}
		
		// Status Effects:
		nodes = parentElement.getElementsByTagName("statusEffects");
		element = (Element) nodes.item(0);
		for(int i=0; i<element.getElementsByTagName("statusEffect").getLength(); i++){
			Element e = ((Element)element.getElementsByTagName("statusEffect").item(i));
			
			try {
				if(Integer.valueOf(e.getAttribute("value"))!=-1) {
					character.addStatusEffect(StatusEffect.valueOf(e.getAttribute("type")), Integer.valueOf(e.getAttribute("value")));
					CharacterUtils.appendToImportLog(log, "</br>Added Status Effect: "+StatusEffect.valueOf(e.getAttribute("type")).getName(character)+" ("+Integer.valueOf(e.getAttribute("value"))+" minutes)");
				}
			}catch(IllegalArgumentException ex){
			}
		}
		
		// Combat:
		// TODO Combat is all derived from weapons/perks/body parts, so there's no reason for this to even be here...
		
		// Pregnancy: TODO
//				nodes = parentElement.getElementsByTagName("pregnancy");
//				// Possibilities:
//				element = (Element) ((Element) nodes.item(0)).getElementsByTagName("pregnancyPossibilities").item(0);
//				for(int i=0; i<element.getElementsByTagName("possibility").getLength(); i++){
//					Element e = ((Element)element.getElementsByTagName("possibility").item(i));
//					
//					try {
//						character.getPotentialPartnersAsMother().add(new PregnancyPossibility(
//								null,
//								null,
//								Float.valueOf(e.getAttribute("probability"))));
//						CharacterUtils.appendToImportLog(log, "</br>Added Pregnancy Possibility: Father:"+e.getAttribute("fatherName"));
//					}catch(IllegalArgumentException ex){
//					}
//				}
//				// Litter:
//				element = (Element) ((Element) nodes.item(0)).getElementsByTagName("pregnantLitter").item(0);
//				for(int i=0; i<element.getElementsByTagName("litter").getLength(); i++){
//					Element e = ((Element)element.getElementsByTagName("litter").item(i));
//					
//					try {
//						character.setPregnantLitter(new Litter(
//								Integer.valueOf(e.getAttribute("dayOfConception")),
//								Integer.valueOf(e.getAttribute("dayOfBirth")),
//								null,
//								null,
//								Race.valueOf(e.getAttribute("race")),
//								Integer.valueOf(e.getAttribute("sons")),
//								Integer.valueOf(e.getAttribute("daughters"))));
//						CharacterUtils.appendToImportLog(log, "</br>Added Litter: Father:"+e.getAttribute("fatherName"));
//					}catch(IllegalArgumentException ex){
//					}
//				}
//				// Birthed Litters:
//				element = (Element) ((Element) nodes.item(0)).getElementsByTagName("birthedLitters").item(0);
//				for(int i=0; i<element.getElementsByTagName("birthedLitter").getLength(); i++){
//					Element e = ((Element)element.getElementsByTagName("birthedLitter").item(i));
//					
//					try {
//						character.getLittersBirthed().add(new Litter(
//								Integer.valueOf(e.getAttribute("dayOfConception")),
//								Integer.valueOf(e.getAttribute("dayOfBirth")),
//								null,
//								null,
//								Race.valueOf(e.getAttribute("race")),
//								Integer.valueOf(e.getAttribute("sons")),
//								Integer.valueOf(e.getAttribute("daughters"))));
//						CharacterUtils.appendToImportLog(log, "</br>Added Birthed Litter: Father:"+e.getAttribute("fatherName"));
//					}catch(IllegalArgumentException ex){
//					}
//				}
		
		// Body:
		character.body = Body.loadFromXML(log, (Element) parentElement.getElementsByTagName("body").item(0), doc);
		
		// Player Core info:
		nodes = parentElement.getElementsByTagName("playerCore");
		element = (Element) nodes.item(0);

		// Experience:
		character.incrementExperience(Integer.valueOf(((Element)element.getElementsByTagName("experience").item(0)).getAttribute("value")));
		CharacterUtils.appendToImportLog(log, "</br>Set experience: " + Integer.valueOf(((Element)element.getElementsByTagName("experience").item(0)).getAttribute("value")));
		
		// Level up points:
		character.setLevelUpPoints(Integer.valueOf(((Element)element.getElementsByTagName("levelUpPoints").item(0)).getAttribute("value")) + extraLevelUpPoints);
		CharacterUtils.appendToImportLog(log, "</br>Set levelUpPoints: " + (Integer.valueOf(((Element)element.getElementsByTagName("levelUpPoints").item(0)).getAttribute("value")) + extraLevelUpPoints));

		// Perk points:
		//character.setPerkPoints(Integer.valueOf(((Element)element.getElementsByTagName("perkPoints").item(0)).getAttribute("value")));
//				character.setPerkPoints((Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value"))));
//				
//				CharacterUtils.appendToImportLog(log, "</br>Set perkPoints: (TEMP FIX) " + (Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value"))));
		
		// Sex stats:
		nodes = parentElement.getElementsByTagName("sexStats");
		
		// Cum counts:
		element = (Element) ((Element) nodes.item(0)).getElementsByTagName("cumCounts").item(0);
		for(int i=0; i<element.getElementsByTagName("cumCount").getLength(); i++){
			Element e = ((Element)element.getElementsByTagName("cumCount").item(i));
			
			try {
				for(int it =0 ; it<Integer.valueOf(e.getAttribute("count")) ; it++)
					character.incrementCumCount(new SexType(PenetrationType.valueOf(e.getAttribute("penetrationType")), OrificeType.valueOf(e.getAttribute("orificeType"))));
				CharacterUtils.appendToImportLog(log, "</br>Added cum count:"+e.getAttribute("penetrationType")+" "+e.getAttribute("orificeType")+" x "+Integer.valueOf(e.getAttribute("count")));
			}catch(IllegalArgumentException ex){
			}
		}
		
		// Sex counts:
		element = (Element) ((Element) nodes.item(0)).getElementsByTagName("sexCounts").item(0);
		for(int i=0; i<element.getElementsByTagName("sexCount").getLength(); i++){
			Element e = ((Element)element.getElementsByTagName("sexCount").item(i));
			
			try {
				for(int it =0 ; it<Integer.valueOf(e.getAttribute("count")) ; it++)
					character.incrementSexCount(new SexType(PenetrationType.valueOf(e.getAttribute("penetrationType")), OrificeType.valueOf(e.getAttribute("orificeType"))));
				CharacterUtils.appendToImportLog(log, "</br>Added sex count:"+e.getAttribute("penetrationType")+" "+e.getAttribute("orificeType")+" x "+Integer.valueOf(e.getAttribute("count")));
			}catch(IllegalArgumentException ex){
			}
		}
		
		// Virginity losses:
		element = (Element) ((Element) nodes.item(0)).getElementsByTagName("virginityTakenBy").item(0);
		for(int i=0; i<element.getElementsByTagName("virginity").getLength(); i++){
			Element e = ((Element)element.getElementsByTagName("virginity").item(i));

			character.setVirginityLoss(new SexType(PenetrationType.valueOf(e.getAttribute("penetrationType")), OrificeType.valueOf(e.getAttribute("orificeType"))), e.getAttribute("takenBy"));
			CharacterUtils.appendToImportLog(log, "</br>Added virginity loss:"+e.getAttribute("penetrationType")+" "+e.getAttribute("orificeType")+" (taken by:"+e.getAttribute("takenBy")+")");
		}
		
		
		// Inventory:
		character.resetInventory();
		nodes = parentElement.getElementsByTagName("characterInventory");
		element = (Element) nodes.item(0);
		if(element!=null) {
			character.setMoney(Integer.valueOf(((Element)element.getElementsByTagName("money").item(0)).getAttribute("value")));
			character.setEssenceCount(TFEssence.ARCANE, Integer.valueOf(((Element)element.getElementsByTagName("essences").item(0)).getAttribute("value")));
			
			if(element.getElementsByTagName("mainWeapon").item(0)!=null) {
				character.equipMainWeaponFromNowhere(AbstractWeapon.loadFromXML(
						(Element) ((Element)element.getElementsByTagName("mainWeapon").item(0)).getElementsByTagName("weapon").item(0),
						doc));
			}
	
			if(element.getElementsByTagName("offhandWeapon").item(0)!=null) {
				character.equipOffhandWeaponFromNowhere(AbstractWeapon.loadFromXML(
						(Element) ((Element)element.getElementsByTagName("offhandWeapon").item(0)).getElementsByTagName("weapon").item(0),
						doc));
			}
			
			Element clothingEquipped = (Element) element.getElementsByTagName("clothingEquipped").item(0);
			for(int i=0; i<clothingEquipped.getElementsByTagName("clothing").getLength(); i++){
				Element e = ((Element)clothingEquipped.getElementsByTagName("clothing").item(i));
				
				character.equipClothingOverride(AbstractClothing.loadFromXML(e, doc));
			}
			//TODO count
			Element itemsInInventory = (Element) element.getElementsByTagName("itemsInInventory").item(0);
			for(int i=0; i<itemsInInventory.getElementsByTagName("item").getLength(); i++){
				Element e = ((Element)itemsInInventory.getElementsByTagName("item").item(i));
				
				for(int itemCount = 0 ; itemCount < Integer.valueOf(e.getAttribute("count")); itemCount++) {
					character.addItem(AbstractItem.loadFromXML(e, doc), false);
				}
			}
			
			Element clothingInInventory = (Element) element.getElementsByTagName("clothingInInventory").item(0);
			for(int i=0; i<clothingInInventory.getElementsByTagName("clothing").getLength(); i++){
				Element e = ((Element)clothingInInventory.getElementsByTagName("clothing").item(i));
	
				for(int clothingCount = 0 ; clothingCount < Integer.valueOf(e.getAttribute("count")); clothingCount++) {
					character.addClothing(AbstractClothing.loadFromXML(e, doc), false);
				}
			}
			
			Element weaponsInInventory = (Element) element.getElementsByTagName("weaponsInInventory").item(0);
			for(int i=0; i<weaponsInInventory.getElementsByTagName("weapon").getLength(); i++){
				Element e = ((Element)weaponsInInventory.getElementsByTagName("weapon").item(i));
	
				for(int weaponCount = 0 ; weaponCount < Integer.valueOf(e.getAttribute("count")); weaponCount++) {
					character.addWeapon(AbstractWeapon.loadFromXML(e, doc), false);
				}
			}
		}
		
		// Slaves:
		
		Element slavesOwned = (Element) parentElement.getElementsByTagName("slavesOwned").item(0);
		if(slavesOwned!=null) {
			for(int i=0; i< slavesOwned.getElementsByTagName("character").getLength(); i++){
				Element e = ((Element)slavesOwned.getElementsByTagName("character").item(i));
				
				SlaveImport slave = SlaveImport.loadFromXML(log, e, doc);
				
				//TODO move into slave's import:
				slave.setMana(slave.getAttributeValue(Attribute.MANA_MAXIMUM));
				slave.setHealth(slave.getAttributeValue(Attribute.HEALTH_MAXIMUM));
				slave.setStamina(slave.getAttributeValue(Attribute.STAMINA_MAXIMUM));
				
				try {
					Main.game.addNPC(slave);
					character.addSlave(slave);
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
