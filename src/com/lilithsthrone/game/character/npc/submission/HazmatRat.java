package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.6
 * @version 0.3.5.5
 * @author Innoxia
 */
public class HazmatRat extends NPC {

	private static List<AbstractItemType> itemsForSale = Util.newArrayListOfValues(
			ItemType.CIGARETTE_PACK,
			ItemType.FETISH_UNREFINED,
			ItemType.MOO_MILKER_EMPTY,
			ItemType.getItemTypeFromId("innoxia_pills_fertility"),
			ItemType.getItemTypeFromId("innoxia_pills_broodmother"),
			ItemType.getItemTypeFromId("innoxia_pills_sterility"),
			ItemType.MOTHERS_MILK,
			ItemType.PREGNANCY_TEST);
	
	private static Map<Vector2i, Map<AbstractItem, Integer>> itemsAtVendingMachine = new HashMap<>();
	
	static {
		for(AbstractItemType itemType : ItemType.getAllItems()) {
			if(!itemType.getItemTags().contains(ItemTag.NOT_FOR_SALE)
					&& (itemType.getItemTags().contains(ItemTag.ATTRIBUTE_TF_ITEM) || itemType.getItemTags().contains(ItemTag.RACIAL_TF_ITEM))
					&& (itemType.getItemTags().contains(ItemTag.SUBMISSION_TUNNEL_SPAWN) || itemType.getItemTags().contains(ItemTag.BAT_CAVERNS_SPAWN))) {
				itemsForSale.add(itemType);
			}
		}
		for(Cell c : Main.game.getWorlds().get(WorldType.SUBMISSION).getCells(PlaceType.SUBMISSION_ENTRANCE)) {
			itemsAtVendingMachine.put(c.getLocation(), new HashMap<>());
		}
	}
	
	public HazmatRat() {
		this(false);
	}
	
	public HazmatRat(boolean isImported) {
		super(isImported, new NameTriplet("Rat"), "Tata",
				"Is this a rat-morph that's inside the vending machine?! Surely not...",
				30, Month.JANUARY, 1,
				15, Gender.M_P_MALE, Subspecies.RAT_MORPH, RaceStage.GREATER,
				new CharacterInventory(500),
				WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL,
				false);

		if(!isImported) {
			this.setPlayerKnowsName(false);
			this.setGenericName("Hazmat Rat");
			this.dailyUpdate();
		}
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element properties = super.saveAsXML(parentElement, doc);
		
		Element itemsElement = doc.createElement("itemsAtVendingMachine");
		properties.appendChild(itemsElement);
		for(Entry<Vector2i, Map<AbstractItem, Integer>> entry : itemsAtVendingMachine.entrySet()) {
			Element locationElement = doc.createElement("location");
			itemsElement.appendChild(locationElement);
			locationElement.setAttribute("x", String.valueOf(entry.getKey().getX()));
			locationElement.setAttribute("y", String.valueOf(entry.getKey().getY()));
			for(Entry<AbstractItem, Integer> item : entry.getValue().entrySet()) {
				try {
					Element e = item.getKey().saveAsXML(locationElement, doc);
					e.setAttribute("count", String.valueOf(item.getValue()));
				} catch(Exception ex) {
				}
			}
		}

		return properties;
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);

		Element itemsElement = (Element) parentElement.getElementsByTagName("itemsAtVendingMachine").item(0);
		if(itemsElement!=null) {
			itemsAtVendingMachine.clear();
			
			NodeList nodeList = itemsElement.getElementsByTagName("location");
			for(int i=0; i < nodeList.getLength(); i++){
				Element e = (Element) nodeList.item(i);
				Vector2i vec = new Vector2i(Integer.valueOf(e.getAttribute("x")), Integer.valueOf(e.getAttribute("y")));
				NodeList nodeList2 = e.getElementsByTagName("item");
				for(int j=0; j < nodeList2.getLength(); j++){
					Element e2 = (Element) nodeList2.item(j);
					try {
						AbstractItem item = AbstractItem.loadFromXML(e2, doc);
						int count = 1;
						try {
							count = Integer.valueOf(e2.getAttribute("count"));
						} catch(Exception ex) {
						}
						itemsAtVendingMachine.putIfAbsent(vec, new HashMap<>());
						itemsAtVendingMachine.get(vec).put(item, count);
					} catch(Exception ex) {
					}
				}
			}
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_RANGED_EXPERT);
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 3),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		
		this.setRaceConcealed(true);
		
		// Persona:
		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.SELFISH,
					PersonalityTrait.COWARDLY,
					PersonalityTrait.SHY);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_STORE_OWNER);
		}
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torsoOver_vending_machine", false), true, this);
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	@Override
	public String getSpeechColour() {
		return PresetColour.BASE_GREEN_LIME.toWebHexString();
	}
	
	@Override
	public void dailyUpdate() {
		clearNonEquippedInventory(false);
		
		// Stocks random items each day:
		for(Entry<Vector2i, Map<AbstractItem, Integer>> entry : itemsAtVendingMachine.entrySet()) {
			entry.getValue().clear();
			List<AbstractItemType> itemTypesToSell = new ArrayList<>(itemsForSale);
			Collections.shuffle(itemTypesToSell);
			int listSize = itemTypesToSell.size();
			int maxItemCount = 9;
			itemTypesToSell.subList(0, itemTypesToSell.size()-(listSize<=maxItemCount?1:maxItemCount)).clear();
			for(AbstractItemType type : itemTypesToSell) {
				entry.getValue().put(Main.game.getItemGen().generateItem(type), !type.isConsumedOnUse()?1:(4+Util.random.nextInt(7)));
			}
		}
	}
	
	@Override
	public void applyItemTransactionEffects(AbstractCoreItem itemSold, int quantity, int individualPrice, boolean soldToPlayer) {
		if(soldToPlayer && itemSold instanceof AbstractItem) {
			Map<AbstractItem, Integer> currentLocationItems = itemsAtVendingMachine.get(Main.game.getPlayer().getLocation());
			int oldCount;
			if(currentLocationItems.containsKey(itemSold)) {
				oldCount = currentLocationItems.get(itemSold);
				if(oldCount > quantity) {
					currentLocationItems.put((AbstractItem) itemSold, oldCount-quantity);
				} else {
					currentLocationItems.remove((AbstractItem) itemSold);
				}
			}
		}
	}
	
	public void applyRestock() {
		Map<AbstractItem, Integer> currentLocationItems = itemsAtVendingMachine.get(Main.game.getPlayer().getLocation());
		this.clearNonEquippedInventory(false);
		
		for(Entry<AbstractItem, Integer> item : currentLocationItems.entrySet()) {
			if(this.isInventoryFull()) {
				break;
			}
			this.addItem(item.getKey(), item.getValue(), false, false);
		}
	}
	
	@Override
	public float getBuyModifier() {
		return 0.25f;
	}
	
	@Override
	public float getSellModifier(AbstractCoreItem item) {
		if(Main.game.getPlayer().hasItemType(ItemType.getItemTypeFromId("dsg_quest_hazmat_rat_card"))) {
			return 1.25f;
		}
		return 2.5f;
	}
	
	@Override
	public String getTraderDescription() {
		return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "VENDING_MACHINE_TRANSACTION_START");
	}

	@Override
	public boolean isTrader() {
		return true;
	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		return false;
	}
}