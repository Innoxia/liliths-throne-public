package com.lilithsthrone.game.inventory;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.BlockedParts;
import com.lilithsthrone.game.inventory.clothing.BodyPartClothingBlock;
import com.lilithsthrone.game.inventory.clothing.ClothingAccess;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.inventory.item.AbstractFilledBreastPump;
import com.lilithsthrone.game.inventory.item.AbstractFilledCondom;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.utils.comparators.ClothingRarityComparator;
import com.lilithsthrone.utils.comparators.ClothingZLayerComparator;
import com.lilithsthrone.utils.comparators.InventoryClothingComparator;
import com.lilithsthrone.utils.comparators.InventoryItemComparator;
import com.lilithsthrone.utils.comparators.InventoryWeaponComparator;
import com.lilithsthrone.utils.comparators.ReverseClothingZLayerComparator;
import com.lilithsthrone.world.World;

/**
 * Inventory for a Character. Tracks weapons equipped, clothes worn & inventory space.<br/>
 * Only the very bravest dare venture past line 901.
 * 
 * @since 0.1.0
 * @version 0.3.9
 * @author Innoxia
 */
public class CharacterInventory implements XMLSaving {
	
	private final AbstractInventory<AbstractWeapon, AbstractWeaponType> weaponSubInventory;
	private final AbstractInventory<AbstractClothing, AbstractClothingType> clothingSubInventory;
	private final AbstractInventory<AbstractItem, AbstractItemType> itemSubInventory;

	/**Maps character IDs to the slots which have free unlocks.*/
	private final Map<String, List<InventorySlot>> unlockKeyMap;

	protected int essenceCount;
	protected int money;
	
	private Set<InventorySlot> dirtySlots;
	
	// Clothing that's currently blocking this inventory from unequipping/displacing something:
	private AbstractClothing blockingClothing;

	protected BlockedParts extraBlockedParts;
	
	// Weapons
	private AbstractWeapon[] mainWeapon;
	private AbstractWeapon[] offhandWeapon;

	private List<AbstractClothing> clothingCurrentlyEquipped;

	// ClothingSets being worn:
	private final Map<AbstractSetBonus, Integer> clothingSetCount;

	private int maxInventorySpace;

	public CharacterInventory(int money) {
		this(money, 32);
	}
		
	public CharacterInventory(int money, int maxInventorySpace) {
		this.money = money;

		weaponSubInventory = new AbstractInventory<>(new InventoryWeaponComparator(), AbstractWeapon::getWeaponType);
		clothingSubInventory = new AbstractInventory<>(new InventoryClothingComparator(), AbstractClothing::getClothingType);
		itemSubInventory = new AbstractInventory<>(new InventoryItemComparator(), AbstractItem::getItemType);
		
		
		dirtySlots = new HashSet<>();
		
		essenceCount = 0;
		
		unlockKeyMap = new HashMap<>();
		
		mainWeapon = new AbstractWeapon[Arm.MAXIMUM_ROWS];
		offhandWeapon = new AbstractWeapon[Arm.MAXIMUM_ROWS];
		
		clothingCurrentlyEquipped = new ArrayList<>();
		clothingSetCount = new HashMap<>();
		for(AbstractSetBonus clothingSet : SetBonus.getAllSetBonuses()) {
			clothingSetCount.put(clothingSet, 0);
		}
		
		this.maxInventorySpace = maxInventorySpace;
	}
	
	public CharacterInventory(CharacterInventory inventoryToCopy) {
		this.money = inventoryToCopy.money;

		weaponSubInventory = new AbstractInventory<>(inventoryToCopy.weaponSubInventory);
		clothingSubInventory = new AbstractInventory<>(inventoryToCopy.clothingSubInventory);
		itemSubInventory = new AbstractInventory<>(inventoryToCopy.itemSubInventory);
		
		dirtySlots = new HashSet<>(inventoryToCopy.getDirtySlots());
		
		essenceCount = inventoryToCopy.essenceCount;

		unlockKeyMap = new HashMap<>(inventoryToCopy.unlockKeyMap);
		
		mainWeapon = new AbstractWeapon[Arm.MAXIMUM_ROWS];
		for(int i=0; i<mainWeapon.length; i++) {
			mainWeapon[i] = inventoryToCopy.mainWeapon[i];
		}
		offhandWeapon = new AbstractWeapon[Arm.MAXIMUM_ROWS];
		for(int i=0; i<offhandWeapon.length; i++) {
			offhandWeapon[i] = inventoryToCopy.offhandWeapon[i];
		}
		
		clothingCurrentlyEquipped = new ArrayList<>(inventoryToCopy.clothingCurrentlyEquipped);
		clothingSetCount = new HashMap<>(inventoryToCopy.clothingSetCount);
		
		this.maxInventorySpace = inventoryToCopy.maxInventorySpace;
		
		this.blockingClothing = inventoryToCopy.blockingClothing;

		this.extraBlockedParts = inventoryToCopy.extraBlockedParts;
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element characterInventory = doc.createElement("characterInventory");
		parentElement.appendChild(characterInventory);
		XMLUtil.createXMLElementWithValue(doc, characterInventory, "maxInventorySpace", String.valueOf(this.getMaximumInventorySpace()));
		XMLUtil.createXMLElementWithValue(doc, characterInventory, "money", String.valueOf(this.getMoney()));
		XMLUtil.createXMLElementWithValue(doc, characterInventory, "essenceCount", String.valueOf(this.getEssenceCount()));
		
		if(extraBlockedParts!=null) {
			Element innerElement = doc.createElement("extraBlockedParts");
			characterInventory.appendChild(innerElement);
			extraBlockedParts.saveAsXML(innerElement, doc);
		}
		
		Element dirtySlotsElement = doc.createElement("dirtySlots");
		characterInventory.appendChild(dirtySlotsElement);
		for(InventorySlot slot : this.getDirtySlots()) {
			Element element = doc.createElement("dirtySlot");
			dirtySlotsElement.appendChild(element);
			XMLUtil.addAttribute(doc, element, "slot", slot.toString());
		}

		if(!unlockKeyMap.isEmpty()) {
			Element innerElement = doc.createElement("unlockKeyMap");
			characterInventory.appendChild(innerElement);
			for(Entry<String, List<InventorySlot>> entry : unlockKeyMap.entrySet()) {
				Element element = doc.createElement("character");
				innerElement.appendChild(element);
				XMLUtil.addAttribute(doc, element, "id", entry.getKey());
				
				for(InventorySlot slot : entry.getValue()) {
					Element elementSlot = doc.createElement("slot");
					element.appendChild(elementSlot);
					XMLUtil.addAttribute(doc, elementSlot, "id", slot.toString());
				}
			}
		}
		
		for(int i=0;i<this.mainWeapon.length;i++) {
			AbstractWeapon weapon = this.mainWeapon[i];
			if(weapon != null) {
				Element mainWeapon = doc.createElement("mainWeapon"+i);
				characterInventory.appendChild(mainWeapon);
				weapon.saveAsXML(mainWeapon, doc);
			}
		}

		for(int i=0;i<this.offhandWeapon.length;i++) {
			AbstractWeapon weapon = this.offhandWeapon[i];
			if(weapon != null) {
				Element offhandWeapon = doc.createElement("offhandWeapon"+i);
				characterInventory.appendChild(offhandWeapon);
				weapon.saveAsXML(offhandWeapon, doc);
			}
		}
		
		Element clothingEquipped = doc.createElement("clothingEquipped");
		characterInventory.appendChild(clothingEquipped);
		for(AbstractClothing clothing : this.getClothingCurrentlyEquipped()) {
			clothing.saveAsXML(clothingEquipped, doc);
		}

		if(!this.getAllItemsInInventory().isEmpty()) {
			Element itemsInInventory = doc.createElement("itemsInInventory");
			characterInventory.appendChild(itemsInInventory);
			for(Entry<AbstractItem, Integer> item : this.getAllItemsInInventory().entrySet()) {
				Element e = item.getKey().saveAsXML(itemsInInventory, doc);
				int value = 1;
				if(item.getValue()!=null) {
					value = item.getValue();
				}
				XMLUtil.addAttribute(doc, e, "count", String.valueOf(value));
			}
		}
		
		if(!this.getAllClothingInInventory().isEmpty()) {
			Element clothingInInventory = doc.createElement("clothingInInventory");
			characterInventory.appendChild(clothingInInventory);
			for(Entry<AbstractClothing, Integer> clothing : this.getAllClothingInInventory().entrySet()) {
				Element e = clothing.getKey().saveAsXML(clothingInInventory, doc);
				int value = 1;
				if(clothing.getValue()!=null) { // TODO figure out how this was being assigned to null
					value = clothing.getValue();
				}
				XMLUtil.addAttribute(doc, e, "count", String.valueOf(value));
			}
		}
		
		if(!this.getAllWeaponsInInventory().isEmpty()) {
			Element weaponsInInventory = doc.createElement("weaponsInInventory");
			characterInventory.appendChild(weaponsInInventory);
			for(Entry<AbstractWeapon, Integer> weapon : this.getAllWeaponsInInventory().entrySet()) {
				Element e = weapon.getKey().saveAsXML(weaponsInInventory, doc);
				int value = 1;
				if(weapon.getValue()!=null) {
					value = weapon.getValue();
				}
				XMLUtil.addAttribute(doc, e, "count", String.valueOf(value));
			}
		}
		
		return characterInventory;
	}
	
	public static CharacterInventory loadFromXML(Element parentElement, Document doc) {
		CharacterInventory inventory = new CharacterInventory(0);
		
//		if(parentElement.getElementsByTagName("maxInventorySpace").item(0)!=null) {
//			inventory.setMaximumInventorySpace(Integer.valueOf(((Element)parentElement.getElementsByTagName("maxInventorySpace").item(0)).getAttribute("value")));
//		}
		inventory.setMoney(Integer.valueOf(((Element)parentElement.getElementsByTagName("money").item(0)).getAttribute("value")));
		
		if(parentElement.getElementsByTagName("essences").item(0)!=null) { // Old version support.
			inventory.setEssenceCount(Integer.valueOf(((Element)parentElement.getElementsByTagName("essences").item(0)).getAttribute("value")));
		} else {
			inventory.setEssenceCount(Integer.valueOf(((Element)parentElement.getElementsByTagName("essenceCount").item(0)).getAttribute("value")));
		}
		
		try {
			NodeList nodes = parentElement.getElementsByTagName("extraBlockedParts");
			Element extraBlockedPartsElement = (Element) nodes.item(0);
			if(extraBlockedPartsElement!=null) {
				inventory.setExtraBlockedParts(BlockedParts.loadFromXML((Element) extraBlockedPartsElement.getElementsByTagName("blockedParts").item(0), doc, "CharacterInventory extraBlockedParts"));
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		NodeList nodes = parentElement.getElementsByTagName("dirtySlots");
		Element dirtySlotContainerElement = (Element) nodes.item(0);
		if(dirtySlotContainerElement!=null) {
			NodeList dirtySlotEntries = dirtySlotContainerElement.getElementsByTagName("dirtySlot");
			for(int i=0; i<dirtySlotEntries.getLength(); i++){
				Element e = ((Element)dirtySlotEntries.item(i));
				InventorySlot slot = InventorySlot.valueOf(e.getAttribute("slot"));
				inventory.addDirtySlot(slot);
			}
		}

		nodes = parentElement.getElementsByTagName("unlockKeyMap");
		Element unlockKeyMapContainerElement = (Element) nodes.item(0);
		if(unlockKeyMapContainerElement!=null) {
			NodeList characterIDs = unlockKeyMapContainerElement.getElementsByTagName("character");
			for(int i=0; i<characterIDs.getLength(); i++){
				Element e = ((Element)characterIDs.item(i));
				
				String id = e.getAttribute("id");
				
				NodeList slots = e.getElementsByTagName("slot");
				List<InventorySlot> loadedSlots = new ArrayList<>();
				for(int j=0; j<slots.getLength(); j++){
					Element slotElement = ((Element)slots.item(j));
					try {
						InventorySlot slot = InventorySlot.valueOf(slotElement.getAttribute("id"));
						loadedSlots.add(slot);
					}catch(IllegalArgumentException ex){
					}
				}
				inventory.unlockKeyMap.put(id, loadedSlots);
			}
		}
		
		nodes = parentElement.getElementsByTagName("mainWeapon");
		if(nodes.getLength()>0 && nodes.item(0)!=null) { // Pre v0.3.4.5 version support:
			AbstractWeapon weapon = AbstractWeapon.loadFromXML((Element) ((Element)nodes.item(0)).getElementsByTagName("weapon").item(0), doc);
			if(weapon!=null) {
				inventory.equipMainWeapon(0, weapon);
			}
		} else {
			for(int i=0;i<inventory.mainWeapon.length;i++) {
				nodes = parentElement.getElementsByTagName("mainWeapon"+i);
				if(nodes.getLength()>0 && nodes.item(0)!=null) {
					AbstractWeapon weapon = AbstractWeapon.loadFromXML((Element) ((Element)nodes.item(0)).getElementsByTagName("weapon").item(0), doc);
					if(weapon!=null) {
						inventory.equipMainWeapon(i, weapon);
					}
				}
			}
		}

		nodes = parentElement.getElementsByTagName("offhandWeapon");
		if(nodes.getLength()>0 && nodes.item(0)!=null) { // Pre v0.3.4.5 version support:
			AbstractWeapon weapon = AbstractWeapon.loadFromXML((Element) ((Element)nodes.item(0)).getElementsByTagName("weapon").item(0), doc);
			if(weapon!=null) {
				inventory.equipOffhandWeapon(0, weapon);
			}
		} else {
			for(int i=0;i<inventory.offhandWeapon.length;i++) {
				nodes = parentElement.getElementsByTagName("offhandWeapon"+i);
				if(nodes.getLength()>0 && nodes.item(0)!=null) {
					AbstractWeapon weapon = AbstractWeapon.loadFromXML((Element) ((Element)nodes.item(0)).getElementsByTagName("weapon").item(0), doc);
					if(weapon!=null) {
						inventory.equipOffhandWeapon(i, weapon);
					}
				}
			}
		}
		
		NodeList clothingEquipped = ((Element) parentElement.getElementsByTagName("clothingEquipped").item(0)).getElementsByTagName("clothing");
		for(int i=0; i<clothingEquipped.getLength(); i++){
			Element e = ((Element)clothingEquipped.item(i));
			
			AbstractClothing clothing = AbstractClothing.loadFromXML(e, doc);
			if(clothing!=null) {
				inventory.getClothingCurrentlyEquipped().add(clothing);
				if(clothing.getSlotEquippedTo()==null) { // If this is pre-version 0.3.3.9, set slot to default:
					clothing.setSlotEquippedTo(clothing.getClothingType().getEquipSlots().get(0));
				}
			}
		}
		
		nodes = parentElement.getElementsByTagName("itemsInInventory");
		if(nodes.getLength()>0 && nodes.item(0)!=null) {
			NodeList itemsInInventory = ((Element) nodes.item(0)).getElementsByTagName("item");
			Map<AbstractItem, Integer> itemMapToAdd = new HashMap<>();
			for(int i=0; i<itemsInInventory.getLength(); i++){
				Element e = ((Element)itemsInInventory.item(i));
				
				int count = Integer.parseInt(e.getAttribute("count"));
				String id = e.getAttribute("id");
				if(id.equals("GIFT_ROSE")) { // Changed the rose to a clothing item in v0.3.5.5
					inventory.addClothing(Main.game.getItemGen().generateClothing("innoxia_hair_rose", PresetColour.CLOTHING_RED_DARK, PresetColour.CLOTHING_GREEN_DARK, null, false), count);
					
				} else if(id.equals(ItemType.getIdFromItemType(ItemType.CONDOM_USED)) || id.equals(ItemType.getIdFromItemType(ItemType.CONDOM_USED_WEBBING))) {
					itemMapToAdd.put(AbstractFilledCondom.loadFromXML(e, doc), count);
					
				} else if(id.equals(ItemType.getIdFromItemType(ItemType.MOO_MILKER_FULL))) {
					itemMapToAdd.put(AbstractFilledBreastPump.loadFromXML(e, doc), count);
					
				} else {
					AbstractItem itemLoadedFromXML = AbstractItem.loadFromXML(e, doc);
					if (itemLoadedFromXML != null) {
						itemMapToAdd.put(itemLoadedFromXML, count);
					}
				}
			}
			inventory.addItems(itemMapToAdd);
		}

		nodes = parentElement.getElementsByTagName("clothingInInventory");
		if(nodes.getLength()>0 && nodes.item(0)!=null) {
			Element clothingInInventory = (Element) nodes.item(0);
			NodeList clothingElements = clothingInInventory.getElementsByTagName("clothing");
			for(int i=0; i<clothingElements.getLength(); i++){
				Element e = ((Element)clothingElements.item(i));
	
				for(int clothingCount = 0 ; clothingCount < Integer.valueOf(e.getAttribute("count")); clothingCount++) {
					AbstractClothing clothing = AbstractClothing.loadFromXML(e, doc);
					if(clothing!=null) {
						inventory.addClothing(clothing);
					}
				}
			}
		}

		nodes = parentElement.getElementsByTagName("weaponsInInventory");
		if(nodes.getLength()>0 && nodes.item(0)!=null) {
			Element weaponsInInventory = (Element) nodes.item(0);
			NodeList weaponElements = weaponsInInventory.getElementsByTagName("weapon");
			for(int i=0; i<weaponElements.getLength(); i++){
				Element e = ((Element)weaponElements.item(i));
	
				for(int weaponCount = 0; weaponCount < Integer.valueOf(e.getAttribute("count")); weaponCount++) {
					AbstractWeapon weapon = AbstractWeapon.loadFromXML(e, doc);
					if(weapon!=null) {
						inventory.addWeapon(weapon);
					}
				}
			}
		}
		
		return inventory;
	}
	
	public boolean isEmpty() {
		for(int i=0; i<Arm.MAXIMUM_ROWS; i++) {
			if(mainWeapon[i]!=null || offhandWeapon[i]!=null) {
				return false;
			}
		}
		return money == 0
				&& clothingSubInventory.isEmpty()
				&& weaponSubInventory.isEmpty()
				&& itemSubInventory.isEmpty()
				&& essenceCount == 0
				&& dirtySlots.isEmpty()
				&& clothingCurrentlyEquipped.isEmpty();
	}

	public int getMoney() {
		return money;
	}

	/**
	 * Does not allow money to fall below 0.
	 */
	public void setMoney(int newValue) {
		money = Math.max(0, newValue);
	}
	
	/**
	 * Does not allow money to fall below 0.
	 */
	public void incrementMoney(int increment) {
		setMoney(money + increment);
	}
	
	public int getEssenceCount() {
		return essenceCount;
	}

	/**
	 * Does not allow essence count to fall below 0.
	 */
	public void setEssenceCount(int essenceCount) {
		this.essenceCount = Math.max(0, essenceCount);
	}

	/**
	 * Does not allow essence count to fall below 0.
	 */
	public void incrementEssenceCount(int increment) {
		this.essenceCount = Math.max(0, this.essenceCount + increment);
	}

	/**
	 * @return A Map of IDs to InventorySlots, representing which slots this inventory can unlock for free. (i.e. Having a key to unlock clothing in that slot.)
	 */
	public Map<String, List<InventorySlot>> getUnlockKeyMap() {
		return unlockKeyMap;
	}

	public void addToUnlockKeyMap(String id, InventorySlot slot) {
		unlockKeyMap.putIfAbsent(id, new ArrayList<>());
		unlockKeyMap.get(id).add(slot);
	}
	
	public boolean removeFromUnlockKeyMap(String id, InventorySlot slot) {
		if(unlockKeyMap.containsKey(id)) {
			boolean removed = unlockKeyMap.get(id).remove(slot);
			if(unlockKeyMap.get(id).isEmpty()) {
				unlockKeyMap.remove(id);
			}
			return removed;
		}
		return false;
	}
	
	public int getMaximumInventorySpace() {
		return RenderingEngine.INVENTORY_PAGES * RenderingEngine.ITEMS_PER_PAGE;
	}
	
	public void clearNonEquippedInventory(boolean clearMoney) {
		clothingSubInventory.clear();
		weaponSubInventory.clear();
		itemSubInventory.clear();
		if(clearMoney) {
			money = 0;
		}
	}
	
//	public void setMaximumInventorySpace(int maxInventorySpace) {
//		this.maxInventorySpace = maxInventorySpace;
//	}
	
	public boolean isInventoryFull() {
		return getInventorySlotsTaken() >= getMaximumInventorySpace();
	}
	
	/**
	 * @return The number of inventory slots currently occupied. This takes into account weapon, clothing, and item stacking.
	 */
	public int getInventorySlotsTaken() {
		return getUniqueWeaponCount() - getUniqueQuestWeaponCount()
				+ getUniqueClothingCount() - getUniqueQuestClothingCount()
				+ getUniqueItemCount() - getUniqueQuestItemCount();
	}
	
	public boolean isAnyQuestItemPresent() {
		return getUniqueQuestWeaponCount()>0 || getUniqueQuestClothingCount()>0 || getUniqueQuestItemCount()>0;
	}

	/**
	 * @param extraBlockedParts A special BlockedParts object to define conceal overrides to CoverableAreas and InventorySlots. Starts as null, and should be set to such if you want to remove these overrides.
	 */
	public void setExtraBlockedParts(BlockedParts extraBlockedParts) {
		this.extraBlockedParts = extraBlockedParts;
	}
	
	/**
	 * @return A special BlockedParts object that provides conceal overrides to CoverableAreas and InventorySlots. Should only be used for characters that are to remain mostly concealed, such as Glory Hole participants.
	 */
	public BlockedParts getExtraBlockedParts() {
		return extraBlockedParts;
	}
	
	private void sortItemDuplicates() {
		itemSubInventory.sort();
	}

	private void sortWeaponDuplicates() {
		weaponSubInventory.sort();
	}

	private void sortClothingDuplicates() {
		clothingSubInventory.sort();
	}

	public void sortInventory() {
		sortItemDuplicates();
		sortWeaponDuplicates();
		sortClothingDuplicates();
	}
	
	/**
	 * @return The value of all non-equipped items, clothing, and weapons in this inventory.
	 */
	public int getNonEquippedValue() {
		int value = 0;
		for(Entry<AbstractItem, Integer> item : this.getAllItemsInInventory().entrySet()) {
			value += (item.getKey().getValue() * item.getValue());
		}
		for(Entry<AbstractClothing, Integer> clothing : this.getAllClothingInInventory().entrySet()) {
			value += (clothing.getKey().getValue() * clothing.getValue());
		}
		for(Entry<AbstractWeapon, Integer> weapon : this.getAllWeaponsInInventory().entrySet()) {
			value += (weapon.getKey().getValue() * weapon.getValue());
		}
		return value;
	}
	
	/**
	 * @return The value of all equipped clothing and weapons in this inventory.
	 */
	public int getEquippedValue() {
		int value = 0;
		for(AbstractClothing clothing : this.getClothingCurrentlyEquipped()) {
			value += clothing.getValue();
		}
		for(AbstractWeapon weapon : this.getMainWeaponArray()) {
			if(weapon!=null) {
				value += weapon.getValue();
			}
		}
		for(AbstractWeapon weapon : this.getOffhandWeaponArray()) {
			if(weapon!=null) {
				value += weapon.getValue();
			}
		}
		return value;
	}
	
	
	// -------------------- Items -------------------- //
	
	/**
	 * <b>DO NOT MODIFY!</b>
	 */
	public Map<AbstractItem, Integer> getAllItemsInInventory() {
		return itemSubInventory.getDuplicateCounts();
	}

	public int getTotalItemCount() {
		return itemSubInventory.getTotalItemCount();
	}
	
	public int getUniqueItemCount() {
		return itemSubInventory.getUniqueItemCount();
	}
	
	public int getUniqueQuestItemCount() {
		return itemSubInventory.getQuestEntryCount();
	}
	
	public int getItemCount(AbstractItem item) {
		return itemSubInventory.getItemCount(item);
	}
	
	/**
	 * For internal use only. Adds multiple items. Does not check size limits.
	 * @param itemMap
	 */
	private void addItems(Map<AbstractItem, Integer> itemMap) {
		itemSubInventory.addFromMap(itemMap);

		if (Main.game.isStarted()) {
			sortItemDuplicates();
		}
	}
	
	/**
	 * Add an item to this inventory.
	 * @return true if added, false if inventory was full.
	 */
	public boolean addItem(AbstractItem item, int count) {
		if(item==null) {
			return false;
		}

		boolean canAddItem = canAddItem(item);
		if (canAddItem) {
			itemSubInventory.addItem(item, count);

			if (Main.game.isStarted()) {
				sortItemDuplicates();
			}
		}

		return canAddItem;
	}
	
	/**
	 * Add an item to this inventory.
	 * @return true if added, false if inventory was full.
	 */
	public boolean addItem(AbstractItem item) {
		return addItem(item, 1);
	}
	
	public boolean canAddItem(AbstractItem item) {
		return !isInventoryFull() || hasItem(item) || item.getRarity()==Rarity.QUEST;
	}
	
	/**
	 * @param item The item to be removed.
	 * @return true if an item was removed, false if no item was found.
	 */
	public boolean removeItem(AbstractItem item) {
		return removeItem(item, 1);
	}

	/**
	 * @param item The item to be removed.
	 * @param count The number of items matching this item to be removed.
	 * @return true if an item was removed, false if no item was found.
	 */
	public boolean removeItem(AbstractItem item, int count) {
		return itemSubInventory.removeItem(item, count);
	}
	
	public boolean hasItem(AbstractItem item) {
		return itemSubInventory.hasItem(item);
	}
	
	/**
	 * @return true if one of the items in this inventory has the same type as the Item provided.
	 */
	public boolean hasItemType(AbstractItemType item) {
		return itemSubInventory.hasItemType(item);
	}
	
	public boolean removeItemByType(AbstractItemType itemType) {
		return removeItemByType(itemType, 1);
	}

	public boolean removeItemByType(AbstractItemType itemType, int count) {
		return itemSubInventory.removeItemByType(itemType, count);
	}
	
	public boolean removeAllItemsByRarity(Rarity rarity) {
		return itemSubInventory.removeAllItemsByRarity(rarity);
	}

	public boolean dropItem(AbstractItem item, World world, Vector2i location) {
		return dropItem(item, 1, world, location);
	}
	
	
	public boolean dropItem(AbstractItem item, int count, World world, Vector2i location) {
		if(hasItem(item)) {
			world.getCell(location).getInventory().addItem(item, count);
			removeItem(item, count);
			return true;
		}
		
		return false;
	}
	
	
	// -------------------- Weapons -------------------- //
	
	/**
	 * <b>DO NOT MODIFY!</b>
	 */
	public Map<AbstractWeapon, Integer> getAllWeaponsInInventory() {
		return weaponSubInventory.getDuplicateCounts();
	}

	public int getTotalWeaponCount() {
		return weaponSubInventory.getTotalItemCount();
	}

	public int getUniqueWeaponCount() {
		return weaponSubInventory.getUniqueItemCount();
	}
	
	public int getUniqueQuestWeaponCount() {
		return weaponSubInventory.getQuestEntryCount();
	}
	
	public int getWeaponCount(AbstractWeapon weapon) {
		return weaponSubInventory.getItemCount(weapon);
	}
	
	/**
	 * Add a weapon to this inventory.
	 * @return true if added, false if inventory was full.
	 */
	public boolean addWeapon(AbstractWeapon weapon, int count) {
		if(weapon==null) {
			return false;
		}
		
		if(canAddWeapon(weapon)) {
			weaponSubInventory.addItem(weapon, count);

			if(Main.game.isStarted()) {
				sortWeaponDuplicates();
			}
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Add a weapon to this inventory.
	 * @return true if added, false if inventory was full.
	 */
	public boolean addWeapon(AbstractWeapon weapon) {
		return addWeapon(weapon, 1);
	}
	
	public boolean canAddWeapon(AbstractWeapon weapon) {
		return !isInventoryFull() || hasWeapon(weapon) || weapon.getRarity()==Rarity.QUEST;
	}

	/**
	 * @param weapon The weapon to be removed.
	 * @return true if a weapon was removed, false if no weapon was found.
	 */
	public boolean removeWeapon(AbstractWeapon weapon) {
		return removeWeapon(weapon, 1);
	}

	/**
	 * @param weapon The weapon to be removed.
	 * @param count The number of weapons matching this weapon to be removed.
	 * @return true if a weapon was removed, false if no weapon was found.
	 */
	public boolean removeWeapon(AbstractWeapon weapon, int count) {
		return weaponSubInventory.removeItem(weapon, count);
	}

	public boolean hasWeapon(AbstractWeapon weapon) {
		return weaponSubInventory.hasItem(weapon);
	}
	
	/**
	 * @return true if one of the weapons in this inventory has the same type as the Weapon provided.
	 */
	public boolean hasWeaponType(AbstractWeaponType weapon) {
		return weaponSubInventory.hasItemType(weapon);
	}
	
	public boolean removeWeaponByType(AbstractWeaponType weaponType) {
		return weaponSubInventory.removeItemByType(weaponType, 1);
	}
	
	public boolean removeAllWeaponsByRarity(Rarity rarity) {
		return weaponSubInventory.removeAllItemsByRarity(rarity);
	}

	public boolean dropWeapon(AbstractWeapon weapon, World world, Vector2i location) {
		return dropWeapon(weapon, 1, world, location);
	}
	
	public boolean dropWeapon(AbstractWeapon weapon, int count, World world, Vector2i location) {
		if (hasWeapon(weapon)) {
			world.getCell(location).getInventory().addWeapon(weapon, count);
			removeWeapon(weapon, count);
			return true;
		}
		
		return false;
	}
	
	public AbstractWeapon getMainWeapon(int armRow) {
		return mainWeapon[armRow];
	}
	
	/**
	 * <b>DO NOT MODIFY!</b><br/>
	 * Use the <i>equipMainWeapon(int armRow, AbstractWeapon weapon)<i> and <i>unequipMainWeapon(int armRow)</i> methods to modify the underlying array.
	 * 
	 * @return The underlying array which stores weapons equipped in the main hand. The index corresponds to which arm row the weapon is held in.
	 */
	public AbstractWeapon[] getMainWeaponArray() {
		return mainWeapon;
	}
	
	public void equipMainWeapon(int armRow, AbstractWeapon weapon) {
		if(armRow>=Arm.MAXIMUM_ROWS) {
			throw new IllegalArgumentException("Equipping main weapon failed! Row was passed in as "+armRow+", but maximum arm rows is "+Arm.MAXIMUM_ROWS+"!");
		}
		mainWeapon[armRow] = weapon;
	}
	
	public void unequipMainWeapon(int armRow) {
		mainWeapon[armRow] = null;
	}
	
	public AbstractWeapon getOffhandWeapon(int armRow) {
		return offhandWeapon[armRow];
	}

	/**
	 * <b>DO NOT MODIFY!</b><br/>
	 * Use the <i>equipOffhandWeapon(int armRow, AbstractWeapon weapon)<i> and <i>unequipOffhandWeapon(int armRow)</i> methods to modify the underlying array.
	 * 
	 * @return The underlying array which stores weapons equipped in the off hand. The index corresponds to which arm row the weapon is held in.
	 */
	public AbstractWeapon[] getOffhandWeaponArray() {
		return offhandWeapon;
	}
	
	public void equipOffhandWeapon(int armRow, AbstractWeapon weapon) {
		if(armRow>=Arm.MAXIMUM_ROWS) {
			throw new IllegalArgumentException("Equipping main weapon failed! Row was passed in as "+armRow+", but maximum arm rows is "+Arm.MAXIMUM_ROWS+"!");
		}
		offhandWeapon[armRow] = weapon;
	}
	
	public void unequipOffhandWeapon(int armRow) {
		offhandWeapon[armRow] = null;
	}
	
	
	// -------------------- Clothing -------------------- //
	
	/**
	 * <b>DO NOT MODIFY!</b>
	 */
	public Map<AbstractClothing, Integer> getAllClothingInInventory() {
		return clothingSubInventory.getDuplicateCounts();
	}

	public int getTotalClothingCount() {
		return clothingSubInventory.getTotalItemCount();
	}

	public int getUniqueClothingCount() {
		return clothingSubInventory.getUniqueItemCount();
	}

	public int getUniqueQuestClothingCount() {
		return clothingSubInventory.getQuestEntryCount();
	}
	
	public int getClothingCount(AbstractClothing clothing) {
		return clothingSubInventory.getItemCount(clothing);
	}

	/**
	 * Add a clothing to this inventory.
	 * @return true if added, false if inventory was full.
	 */
	public boolean addClothing(AbstractClothing clothing, int count) {
		if(clothing==null) {
			return false;
		}
		
		if(canAddClothing(clothing)) {
			clothingSubInventory.addItem(clothing, count);

			if(Main.game.isStarted()) {
				sortClothingDuplicates();
			}
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Add a clothing to this inventory.
	 * @return true if added, false if inventory was full.
	 */
	public boolean addClothing(AbstractClothing clothing) {
		return addClothing(clothing, 1);
	}
	
	public boolean canAddClothing(AbstractClothing clothing) {
		return !isInventoryFull() || hasClothing(clothing) ||  clothing.getRarity()==Rarity.QUEST;
	}
	
	/**
	 * @param clothing The clothing to be removed.
	 * @return true if a clothing was removed, false if no clothing was found.
	 */
	public boolean removeClothing(AbstractClothing clothing) {
		return removeClothing(clothing, 1);
	}

	/**
	 * @param clothing The clothing to be removed.
	 * @param count The number of clothing matching this clothing to be removed.
	 * @return true if a clothing was removed, false if no clothing was found.
	 */
	public boolean removeClothing(AbstractClothing clothing, int count) {
		return clothingSubInventory.removeItem(clothing, count);
	}

	public boolean hasClothing(AbstractClothing clothing) {
		return clothingSubInventory.hasItem(clothing);
	}
	
	/**
	 * @return true if one of the clothings in this inventory has the same type as the Clothing provided.
	 */
	public boolean hasClothingType(AbstractClothingType type, boolean includeEquipped) {
		return clothingSubInventory.hasItemType(type) || (includeEquipped && hasEquippedClothingType(type));
	}

	public boolean hasEquippedClothingType(AbstractClothingType type) {
		return getClothingCurrentlyEquipped().stream().anyMatch(c -> c.getClothingType().equals(type));
	}
	
	public boolean removeClothingByType(AbstractClothingType clothingType) {
		return clothingSubInventory.removeItemByType(clothingType, 1);
	}
	
	public boolean removeAllClothingByRarity(Rarity rarity) {
		return clothingSubInventory.removeAllItemsByRarity(rarity);
	}

	public boolean dropClothing(AbstractClothing clothing, World world, Vector2i location) {
		return dropClothing(clothing, 1, world, location);
	}
	
	public boolean dropClothing(AbstractClothing clothing, int count, World world, Vector2i location) {
		if (hasClothing(clothing)) {
			world.getCell(location).getInventory().addClothing(clothing, count);
			removeClothing(clothing, count);
			return true;
		}
		
		return false;
	}
	
	public boolean isAnyClothingDirty() {
		for(AbstractClothing c : this.getClothingCurrentlyEquipped()) {
			if(c.isDirty()) {
				return true;
			}
		}
		return false;
	}
	
	public void cleanAllClothing(boolean includeNotEquippedClothing) {
		if(!isAnyClothingDirty() && !includeNotEquippedClothing) {
			return;
		}
		
		if(includeNotEquippedClothing) {
			clothingSubInventory.transform(c -> {
				c.setDirty(null, false);
				return c;
			});
		}
		
		for(AbstractClothing c : clothingCurrentlyEquipped) {
			c.setDirty(null, false);
		}
	}
	
	public List<AbstractClothing> getClothingCurrentlyEquipped() {
		return clothingCurrentlyEquipped;
	}

	/**
	 * @return Map of concealed slots as keys, with a list of clothing that's concealing said slot as the value. 
	 */
	public Map<InventorySlot, List<AbstractClothing>> getInventorySlotsConcealed(GameCharacter character) {
		Map<InventorySlot, List<AbstractClothing>> concealedMap = new HashMap<>();
		Set<InventorySlot> itemConcealed = new HashSet<>();
		Set<InventorySlot> itemRevealed = new HashSet<>();
		for(AbstractClothing c : getClothingCurrentlyEquipped()) {
			itemConcealed.clear();
			itemRevealed.clear();
			for(BlockedParts bp : c.getBlockedPartsMap(character, c.getSlotEquippedTo())) {
				if(!c.getDisplacedList().contains(bp.displacementType)) {
					itemConcealed.addAll(bp.concealedSlots);
					for(InventorySlot invSlot : bp.concealedSlots) {
						if(this.getClothingInSlot(invSlot)!=null
//								&& bp.displacementType!=DisplacementType.REMOVE_OR_EQUIP
								&& this.getClothingInSlot(invSlot).getItemTags().contains(ItemTag.REVEALS_CONCEALABLE_SLOT)) {//TODO
							itemRevealed.add(invSlot);
						}
					}
					
				} else {
					itemRevealed.addAll(bp.concealedSlots);
				}
			}
			itemConcealed.removeAll(itemRevealed);
			for(InventorySlot slot : itemConcealed) {
				concealedMap.putIfAbsent(slot, new ArrayList<>());
				concealedMap.get(slot).add(c);
			}
		}
		
		for(AbstractClothing c : getClothingCurrentlyEquipped()) {
			InventorySlot clothingSlot = c.getSlotEquippedTo();

			// Do not count clothing as being concealed if it is only partially covered:
			for(InventorySlot is : c.getIncompatibleSlots(character, clothingSlot)) {
				if(concealedMap.containsKey(clothingSlot) && !concealedMap.containsKey(is)) {
					concealedMap.remove(clothingSlot);
				}
			}

			// Remove concealed clothing if it is the only clothing which is concealing another slot:
			if(concealedMap.containsKey(clothingSlot)) {
				boolean remove = false;
				for(Entry<InventorySlot, List<AbstractClothing>> entry : concealedMap.entrySet()) {
					if(entry.getValue().contains(c) && entry.getValue().size()==1) {
						remove = true;
						break;
					}
				}
				if(remove) {
					concealedMap.remove(clothingSlot);
				}
			}
		}
		

		if(this.getExtraBlockedParts()!=null) {
			for(InventorySlot slot :this.getExtraBlockedParts().concealedSlots) {
				if(!concealedMap.containsKey(slot)) {
					concealedMap.put(slot, new ArrayList<>());
				}
			}
		}
		
		return concealedMap;
	}
	

	public List<AbstractClothing> getVisibleClothingConcealingSlot(GameCharacter character, InventorySlot slot) {
		List<AbstractClothing> visibleClothing = new ArrayList<>();
		
		if(getClothingInSlot(slot)!=null) {
			visibleClothing.add(getClothingInSlot(slot));
		}
		
		if(getInventorySlotsConcealed(character).get(slot)!=null) {
			visibleClothing.addAll(getInventorySlotsConcealed(character).get(slot));
		}
		
		if(!visibleClothing.isEmpty()) {
			List<InventorySlot> slotsToCheck = visibleClothing.stream().map(c -> c.getSlotEquippedTo()).collect(Collectors.toList());
			
			while(!slotsToCheck.isEmpty()) {
				for(InventorySlot checkSlot : new ArrayList<>(slotsToCheck)) {
					List<AbstractClothing> checkClothingSlot = getInventorySlotsConcealed(character).get(checkSlot);
					if(checkClothingSlot!=null && !checkClothingSlot.isEmpty()) {
						visibleClothing = visibleClothing.stream().filter(cl -> cl.getSlotEquippedTo()!=checkSlot).collect(Collectors.toList()); // Remove clothing which is concealed
						for(AbstractClothing c : checkClothingSlot) {
							visibleClothing.add(c);
							slotsToCheck.add(c.getSlotEquippedTo());
						}
					}
					slotsToCheck.remove(checkSlot);
				}
			}
		}
		
		return new ArrayList<>(new HashSet<>(visibleClothing)); // Remove duplicates
	}
	
	
	/**
	 * @return clothing in the slot specified. Returns null if no clothing in
	 *         that slot.
	 */
	public AbstractClothing getClothingInSlot(InventorySlot invSlot) {
		AbstractClothing clothingInSlot = null;
		for (AbstractClothing clothing : clothingCurrentlyEquipped)
			if (clothing.getSlotEquippedTo() == invSlot) {
				clothingInSlot = clothing;
				break;
			}
		return clothingInSlot;
	}

	/**
	 * @return The number of clothes being worn that belong to the specified
	 *         ClothingSet.
	 */
	public int getClothingSetCount(AbstractSetBonus clothingSet) {
		return clothingSetCount.get(clothingSet);
	}

	// Lasciate ogne speranza, voi ch'entrate //

	private StringBuilder tempSB;

	public String calculateClothingAndWeaponsPostTransformation(GameCharacter character) {
		tempSB = new StringBuilder();
		List<AbstractClothing> clothingToRemove = new ArrayList<>();
		for(AbstractClothing c : new ArrayList<>(clothingCurrentlyEquipped)) {
			BodyPartClothingBlock block = c.getSlotEquippedTo().getBodyPartClothingBlock(character);
			if (block != null && Collections.disjoint(block.getRequiredTags(), c.getItemTags())) { // Race:
				transformationIncompatible(character, c, clothingToRemove, UtilText.parse(character, block.getDescription()));
				
			} else if (!c.isCanBeEquipped(character, c.getSlotEquippedTo())) { // Clothing specials:
				transformationIncompatible(character, c, clothingToRemove, c.getCannotBeEquippedText(character, c.getSlotEquippedTo()));
			}
		}
		
		InventorySlot[] slots = InventorySlot.mainWeaponSlots;
		for(int i=0; i<character.getMainWeaponArray().length; i++) {
			AbstractWeapon weapon = character.getMainWeaponArray()[i];
			BodyPartClothingBlock block = slots[i].getBodyPartClothingBlock(character);
			if(weapon!=null) {
				if(character.getArmRows()-1<i || (block!=null && Collections.disjoint(block.getRequiredTags(), weapon.getItemTags()))) {
					transformationIncompatibleWeapon(character, weapon, character.unequipMainWeapon(i, false, true));
				}
			}
		}
		slots = InventorySlot.offhandWeaponSlots;
		for(int i=0; i<character.getOffhandWeaponArray().length; i++) {
			AbstractWeapon weapon = character.getOffhandWeaponArray()[i];
			BodyPartClothingBlock block = slots[i].getBodyPartClothingBlock(character);
			if(weapon!=null) {
				if(character.getArmRows()-1<i || (block!=null && Collections.disjoint(block.getRequiredTags(), weapon.getItemTags()))) {
					transformationIncompatibleWeapon(character, weapon, character.unequipOffhandWeapon(i, false, true));
				}
			}
		}
		
		return tempSB.toString();
	}
	
	private void transformationIncompatible(GameCharacter character, AbstractClothing c, List<AbstractClothing> clothingRemovalList, String description){
		if (tempSB.length() != 0) {
			tempSB.append("<br/><br/>");
		}
		tempSB.append("<br/><span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>"+UtilText.parse(character, description)+"</span>");
		
		c.setSealed(false);
		
		if(c.isDiscardedOnUnequip(c.getSlotEquippedTo())) {
			tempSB.append(character.unequipClothingIntoVoid(c, true, character));
		} else {
			tempSB.append(character.unequipClothingIntoInventory(c, true, character));
		}
		
		clothingRemovalList.add(c);	
	}
	
	private void transformationIncompatibleWeapon(GameCharacter character, AbstractWeapon w, String description){
		if (tempSB.length() != 0) {
			tempSB.append("<br/><br/>");
		}
		tempSB.append("<br/><span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>"+UtilText.parse(character, "Due to the loss of [npc.her] extra pair of arms, [npc.name] can no longer hold the "+w.getName()+"!")+"</span>");
		
		tempSB.append("<br/>" + description);
	}
	
	

	private StringBuilder equipTextSB = new StringBuilder();

	public String getEquipDescription() {
		return equipTextSB.toString();
	}

	public void resetEquipDescription() {
		equipTextSB.setLength(0);
	}
	
	private Set<AbstractClothing> incompatibleUnequippableClothing = new HashSet<>();
	private Set<AbstractClothing> incompatibleRemovableClothing = new HashSet<>();
	// Map of clothing that needs to be removed. If value is
	// DisplacementType.NONE, clothing will be fully removed.
	private Map<AbstractClothing, DisplacementType> clothingToRemove = new HashMap<>();

	/**
	 * Calculates if the character is able to remove or displace all blocking clothing in order to equip the supplied clothing.
	 */
	public boolean isAbleToEquip(AbstractClothing newClothing, InventorySlot slotToEquipInto, boolean equipIfAble, boolean automaticClothingManagement, GameCharacter characterClothingOwner, GameCharacter characterClothingEquipper) {
		
		displacementClothingChecked = new HashMap<>();
		
		clothingToRemove.clear();
		equipTextSB.setLength(0);
		
		// Check to see if any of the character's body parts are blocking equipping this item:
		BodyPartClothingBlock block = slotToEquipInto.getBodyPartClothingBlock(characterClothingOwner);
		if (block != null && Collections.disjoint(block.getRequiredTags(), newClothing.getItemTags())) {
			equipTextSB.append("[style.colourBad(" + UtilText.parse(characterClothingOwner, block.getDescription()) + ")]");
			return false;
		}
		
		if (!newClothing.isAbleToBeEquipped(characterClothingOwner, slotToEquipInto).getKey()) {
			equipTextSB.append("[style.colourBad(" + newClothing.isAbleToBeEquipped(characterClothingOwner, slotToEquipInto).getValue() + ")]");
			return false;
		}

		// Can't equip if InventorySlot is taken by a sealed piece of clothing:
		if (getClothingInSlot(slotToEquipInto) != null) {
			if(getClothingInSlot(slotToEquipInto).isSealed()) {
				if(characterClothingOwner.isPlayer()) {
					equipTextSB.append("You can't equip the "+newClothing.getName()+", as your <b style='color:" + PresetColour.SEALED.toWebHexString() + ";'>sealed</b> "
								+ getClothingInSlot(slotToEquipInto).getName() + " can't be removed!");
				} else {
					equipTextSB.append(UtilText.parse(characterClothingOwner,
							"[npc.Name] can't equip the "+newClothing.getName()+", as [npc.her] <b style='color:" + PresetColour.SEALED.toWebHexString() + ";'>sealed</b> "
							+ getClothingInSlot(slotToEquipInto).getName() + " can't be removed!"));
				}
				return false;
			}
		}
		
		// Check to see if any equipped clothing is incompatible with newClothing:
		incompatibleUnequippableClothing.clear();
		incompatibleRemovableClothing.clear();
		for (InventorySlot slot : newClothing.getIncompatibleSlots(characterClothingOwner, slotToEquipInto)) {
			if (getClothingInSlot(slot) != null) {
				if (!isAbleToUnequip(getClothingInSlot(slot), false, automaticClothingManagement, characterClothingOwner, characterClothingEquipper, false))
					incompatibleUnequippableClothing.add(getClothingInSlot(slot));
				else {
					clothingToRemove.put(getClothingInSlot(slot), DisplacementType.REMOVE_OR_EQUIP);
					incompatibleRemovableClothing.add(getClothingInSlot(slot));
				}
			}
		}

		// Check to see if newClothing is incompatible with any equipped clothing:
		for (AbstractClothing clothing : clothingCurrentlyEquipped) {
			if (clothing.getIncompatibleSlots(characterClothingOwner, clothing.getSlotEquippedTo()).contains(slotToEquipInto)) {
				if (!isAbleToUnequip(clothing, false, automaticClothingManagement, characterClothingOwner, characterClothingEquipper, false)) {
					incompatibleUnequippableClothing.add(clothing);
				} else {
					clothingToRemove.put(clothing, DisplacementType.REMOVE_OR_EQUIP);
					incompatibleRemovableClothing.add(clothing);
				}
			}
		}

		// There is at least one piece of clothing that is incompatible with newClothing, and that clothing cannot be removed.
		if (!incompatibleUnequippableClothing.isEmpty()) {
			for(AbstractClothing c : incompatibleUnequippableClothing) {
				if(c.isSealed()) {
					equipTextSB.append(characterClothingOwner.isPlayer()
							?"<br/>You can't equip the " + newClothing.getName() + " because your <b style='color:" + PresetColour.SEALED.toWebHexString() + ";'>sealed</b> "+c.getName()+(c.getClothingType().isPlural()?" are":" is")+" in the way."
							:UtilText.parse(characterClothingOwner,
									"<br/>[npc.Name] can't equip the " + newClothing.getName() + " because [npc.her] <b style='color:" + PresetColour.SEALED.toWebHexString() + ";'>sealed</b> "+c.getName()+(c.getClothingType().isPlural()?" are":" is")+" in the way."));
				}
			}
			return false;
		}

		// Check for access needed:
		for (BlockedParts bp : newClothing.getBlockedPartsMap(characterClothingOwner, slotToEquipInto)) {
			if (bp.displacementType == DisplacementType.REMOVE_OR_EQUIP) { // Check for all blocking types that affect REMOVE_OR_EQUIP. (As we are trying to equip this item of clothing.)
				if (bp.clothingAccessRequired == null) {
					break; // This clothing doesn't need any access in order to be equipped, so just carry on.

				} else {
					// This clothing has access requirements in order to be equipped. Check each piece of equipped clothing to see if it's blocking the access required:
					for (AbstractClothing equippedClothing : clothingCurrentlyEquipped) {
						for (BlockedParts bpEquipped : equippedClothing.getBlockedPartsMap(characterClothingOwner, equippedClothing.getSlotEquippedTo())) {
							for (ClothingAccess caBlocked : bpEquipped.clothingAccessBlocked) { // For each clothing access that is blocked by this equipped clothing, check to see if this clothing access is required by the new clothing:
								
								if (bp.clothingAccessRequired.contains(caBlocked)
										&& !equippedClothing.getDisplacedList().contains(bpEquipped.displacementType)
										&& !isDisplacementAvailableFromElsewhere(characterClothingOwner, equippedClothing, caBlocked)) {
									
									if (!clothingToRemove.containsKey(equippedClothing)) { // This clothing has not already been marked for removal:
										if(automaticClothingManagement && isAbleToBeDisplaced(equippedClothing, bpEquipped.displacementType, false, automaticClothingManagement, characterClothingOwner, characterClothingEquipper, true)) {
											clothingToRemove.put(equippedClothing, bpEquipped.displacementType);
											
										} else {
											equipTextSB.append(characterClothingOwner.isPlayer()
													?"Your <b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>" + equippedClothing.getName() + "</b> "
														+ (equippedClothing.getClothingType().isPlural() ? "are" : "is") + " preventing you from being able to equip the "+newClothing.getName()+"!"
													:UtilText.parse(characterClothingOwner,
															"[npc.NamePos] <b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>" + equippedClothing.getName() + "</b> "
																	+ (equippedClothing.getClothingType().isPlural() ? "are" : "is") + " preventing [npc.herHim] from being able to equip the "+newClothing.getName()+"!"));
											blockingClothing = equippedClothing;
											return false;
										}

									} else {
										if (isAbleToUnequip(equippedClothing, false, automaticClothingManagement, characterClothingOwner, characterClothingEquipper, false)) { // Can be removed:
											clothingToRemove.put(equippedClothing, DisplacementType.REMOVE_OR_EQUIP);
										} else {
											if(equippedClothing.isSealed()) {
												equipTextSB.append(characterClothingOwner.isPlayer()
														?"You can't equip the " + newClothing.getName() + " because your <b style='color:" + PresetColour.SEALED.toWebHexString() + ";'>sealed</b> "
															+equippedClothing.getName()+ " "+(equippedClothing.getClothingType().isPlural()?"are":"is")+" in the way!"
														:UtilText.parse(characterClothingOwner,
																"[npc.Name] can't equip the " + newClothing.getName() + " because [npc.her] <b style='color:" + PresetColour.SEALED.toWebHexString() + ";'>sealed</b> "
																		+equippedClothing.getName()+ " "+(equippedClothing.getClothingType().isPlural()?"are":"is")+" in the way!"));
												
											} else {
												equipTextSB.append(characterClothingOwner.isPlayer()
														?"You can't equip the " + newClothing.getName() + " because your "+equippedClothing.getName()+ " "+(equippedClothing.getClothingType().isPlural()?"are":"is")+" in the way!"
														:UtilText.parse(characterClothingOwner,
																"[npc.Name] can't equip the " + newClothing.getName() + " because [npc.her] "+equippedClothing.getName()+ " "+(equippedClothing.getClothingType().isPlural()?"are":"is")+" in the way!"));
											}
											blockingClothing = equippedClothing;
											return false;
										}
									}
									
								}
								
							}
						}
					}
				}
			}
		}

		// newClothing can be equipped!

		// If you want to equip this clothing now:
		if (equipIfAble) {
			equipTextSB = new StringBuilder();
			if (automaticClothingManagement || (clothingToRemove.size() == incompatibleRemovableClothing.size() + 1)) {
				if (getClothingInSlot(slotToEquipInto) != null) {
					AbstractClothing equippedClothing = getClothingInSlot(slotToEquipInto);
					
					if (isAbleToUnequip(equippedClothing, false, automaticClothingManagement, characterClothingOwner, characterClothingEquipper, false)) { // Can be removed:
						clothingToRemove.put(equippedClothing, DisplacementType.REMOVE_OR_EQUIP);
					} else {
						if(equippedClothing.isSealed()) {
							equipTextSB.append(characterClothingOwner.isPlayer()
									?"You can't equip the " + newClothing.getName() + " because your <b style='color:" + PresetColour.SEALED.toWebHexString() + ";'>sealed</b> "
										+equippedClothing.getName()+ " "+(equippedClothing.getClothingType().isPlural()?"are":"is")+" in the way!"
									:UtilText.parse(characterClothingOwner,
											"[npc.Name] can't equip the " + newClothing.getName() + " because [npc.her] <b style='color:" + PresetColour.SEALED.toWebHexString() + ";'>sealed</b> "
													+equippedClothing.getName()+ " "+(equippedClothing.getClothingType().isPlural()?"are":"is")+" in the way!"));
							
						} else {
							equipTextSB.append(characterClothingOwner.isPlayer()
									?"You can't equip the " + newClothing.getName() + " because your "+equippedClothing.getName()+ " "+(equippedClothing.getClothingType().isPlural()?"are":"is")+" in the way!"
									:UtilText.parse(characterClothingOwner,
											"[npc.Name] can't equip the " + newClothing.getName() + " because [npc.her] "+equippedClothing.getName()+ " "+(equippedClothing.getClothingType().isPlural()?"are":"is")+" in the way!"));
						}
						blockingClothing = equippedClothing;
						return false;
					}
				}
				
				
				// Sort clothing to remove in zLayer order(so you take off your
				// shirt before removing bra etc.):
				List<AbstractClothing> tempClothingList = new ArrayList<>();
				for (AbstractClothing cl : clothingToRemove.keySet()) {
					if (!tempClothingList.contains(cl)) {
						tempClothingList.add(cl);
					}
				}
				for (AbstractClothing cl : incompatibleRemovableClothing) {
					if (!tempClothingList.contains(cl)) {
						tempClothingList.add(cl);
					}
				}
				// tempClothingList.addAll(clothingToRemove.keySet());
				// tempClothingList.addAll(incompatibleRemovableClothing);
//				if (getClothingInSlot(newClothing.getClothingType().getSlot()) != null && !tempClothingList.contains(getClothingInSlot(newClothing.getClothingType().getSlot())))
//					tempClothingList.add(getClothingInSlot(newClothing.getClothingType().getSlot()));

				tempClothingList.sort(new ClothingZLayerComparator());

				List<AbstractClothing> clothingToBeReplaced = new ArrayList<>();
				// Description of each clothing item that is removed/displaced:
				for (AbstractClothing c : tempClothingList) {
					if (!incompatibleRemovableClothing.contains(c) && c != getClothingInSlot(slotToEquipInto)) {
						clothingToBeReplaced.add(c);
					}
					
					equipTextSB.append((equipTextSB.length() == 0 ? "" : "<br/>")
								+ (!clothingToRemove.containsKey(c) || clothingToRemove.get(c) == DisplacementType.REMOVE_OR_EQUIP
									? c.onUnequipText(characterClothingOwner, characterClothingEquipper, (Main.game.isInSex()?Main.sex.getSexPace(characterClothingEquipper)==SexPace.DOM_ROUGH:false))
									: (characterClothingOwner.isPlayer()
											?"You " + clothingToRemove.get(c).getDescription() + " your " + c.getName() + "."
											:"[npc.Name] " + clothingToRemove.get(c).getDescriptionThirdPerson() + " [npc.her] " + c.getName() + ".")));
				}
				
				// Remove all clothing that is incompatible with newClothing using the owner's accessor method.
				for (AbstractClothing c : incompatibleRemovableClothing) {
					if (!characterClothingOwner.isInventoryFull() || characterClothingOwner.hasClothing(c)) {
						equipTextSB.append("<br/>" + characterClothingOwner.addedItemToInventoryText(c));
					} else {
						equipTextSB.append("<br/>" + characterClothingOwner.droppedItemText(c));
					}
					String oldEquipText = equipTextSB.toString();// this is a hack to fix the string builder being overwritten
					
					if(Main.game.isInNewWorld()) {
						characterClothingOwner.unequipClothingIntoInventory(c, true, characterClothingEquipper);
					} else {
						characterClothingOwner.unequipClothingOntoFloor(c, true, characterClothingEquipper);
					}
					equipTextSB.setLength(0);
					equipTextSB.append(oldEquipText);
				}

				// Clear the new clothing's displacement list as a precaution:
				newClothing.getDisplacedList().clear();

				// Remove the old clothing in this slot using the owner's accessor method:
				if (getClothingInSlot(slotToEquipInto) != null) {
					if(getClothingInSlot(slotToEquipInto).isDiscardedOnUnequip(slotToEquipInto)) {
						String oldEquipText = equipTextSB.toString();// this is a hack to fix the string builder being overwritten
						characterClothingOwner.unequipClothingIntoVoid(getClothingInSlot(slotToEquipInto), true, characterClothingEquipper);
						equipTextSB.setLength(0);
						equipTextSB.append(oldEquipText);
						
					} else {
						if ((!characterClothingOwner.isInventoryFull() || characterClothingOwner.hasClothing(getClothingInSlot(slotToEquipInto))) && Main.game.isInNewWorld()) {
							equipTextSB.append("<br/>" + characterClothingOwner.addedItemToInventoryText(getClothingInSlot(slotToEquipInto)));
						} else {
							equipTextSB.append("<br/>" + characterClothingOwner.droppedItemText(getClothingInSlot(slotToEquipInto)));
						}
						String oldEquipText = equipTextSB.toString();// this is a hack to fix the string builder being overwritten
						if(Main.game.isInNewWorld()) {
							characterClothingOwner.unequipClothingIntoInventory(getClothingInSlot(slotToEquipInto), true, characterClothingEquipper);
						} else {
							characterClothingOwner.unequipClothingOntoFloor(getClothingInSlot(slotToEquipInto), true, characterClothingEquipper);
						}
						equipTextSB.setLength(0);
						equipTextSB.append(oldEquipText);
					}
				}

				// Actually equip the newClothing:
				clothingCurrentlyEquipped.add(newClothing);
				newClothing.setSlotEquippedTo(slotToEquipInto);
				
				equipTextSB.append((equipTextSB.length() == 0 ? "" : "<br/>")
						+ newClothing.onEquipApplyEffects(characterClothingOwner, characterClothingEquipper, (Main.game.isInSex()?Main.sex.getSexPace(characterClothingEquipper)==SexPace.DOM_ROUGH:false)));

				clothingToBeReplaced.sort(new ReverseClothingZLayerComparator());
				if (!clothingToBeReplaced.isEmpty()) {// clothingCountToBeReplaced-incompatibleUnequippableClothing.size()>0)
					equipTextSB.append(characterClothingOwner.isPlayer()
							?"<br/>You replace your " + Util.clothesToStringList(clothingToBeReplaced, false) + "."
							:UtilText.parse(characterClothingOwner,
									"<br/>[npc.Name] replaces [npc.her] " + Util.clothesToStringList(clothingToBeReplaced, false) + "."));
				}
				
				if(newClothing.getItemTags().contains(ItemTag.PROVIDES_KEY) && newClothing.isSealed()) {
					equipTextSB.append(UtilText.parse(characterClothingEquipper,
							"<br/>[style.italicsGood([npc.Name] obtained a key to unlock the "+newClothing.getName()+"!)]"));
				}
				
				// Check for clothing sets:
				AbstractSetBonus clothingSetOfNewClothing = newClothing.getClothingType().getClothingSet();
				if (clothingSetOfNewClothing != null) {
					clothingSetCount.merge(clothingSetOfNewClothing, 1, Integer::sum);
				}

			}

			clothingCurrentlyEquipped.sort(new ClothingRarityComparator());

		} else {
			if(clothingToRemove.isEmpty()) {
				equipTextSB.append(Util.capitaliseSentence(newClothing.getName(true))+ " "+(newClothing.getClothingType().isPlural()?"are":"is")+" able to be equipped.");
			} else {
				equipTextSB.append(Util.capitaliseSentence("Before" + newClothing.getClothingType().getDeterminer()) + " " + newClothing.getName()
					+ " is able to be equipped, " + Util.clothesToStringList(clothingToRemove.keySet(), false) + " need"
						+ (clothingToRemove.size() > 1 ? "" : "s") + " to be removed.");
			}
		}

		return true;
	}
	
	public void forceUnequipClothingIntoVoid(GameCharacter characterClothingOwner, GameCharacter characterRemovingClothing, AbstractClothing clothing) {
		clothing.onUnequipApplyEffects(characterClothingOwner, characterRemovingClothing, false);
		clothingCurrentlyEquipped.remove(clothing);
		clothing.getDisplacedList().clear();
	}
	
	public boolean isAbleToUnequip(AbstractClothing clothing, boolean unequipIfAble, boolean automaticClothingManagement, GameCharacter characterClothingOwner, GameCharacter characterRemovingClothing) {
		return isAbleToUnequip(clothing, unequipIfAble, automaticClothingManagement, characterClothingOwner, characterRemovingClothing, false);
	}

	private AbstractClothing previousClothingCheck = null;
	private boolean isAbleToUnequip(AbstractClothing clothing, boolean unequipIfAble, boolean automaticClothingManagement, GameCharacter characterClothingOwner, GameCharacter characterRemovingClothing, boolean continuingIsAbleToEquip) {
		if(!continuingIsAbleToEquip) {
			previousClothingCheck = null;
		}
		if(!unequipIfAble) {
			if(characterClothingOwner==null) {
				characterClothingOwner = Main.game.getPlayer();
			}
			if(characterRemovingClothing==null) {
				characterRemovingClothing = Main.game.getPlayer();
			}
		}
		
		if (!continuingIsAbleToEquip) {
			clothingToRemove.clear();
			equipTextSB.setLength(0);
		}

		if (clothing.isSealed()) {
			equipTextSB.append(characterClothingOwner.isPlayer()
					?"Your " + clothing.getName() + " can't be removed because "+(clothing.getClothingType().isPlural()?"they are":"it is")+" <b style='color:" + PresetColour.SEALED.toWebHexString() + ";'>sealed</b>!"
					:UtilText.parse(characterClothingOwner,
							"[npc.NamePos] " + clothing.getName() + " can't be removed because "+(clothing.getClothingType().isPlural()?"they are":"it is")+" <b style='color:" + PresetColour.SEALED.toWebHexString() + ";'>sealed</b>!"));
			
			blockingClothing = clothing;
			return false;
		} else if (!continuingIsAbleToEquip) {
			clothingToRemove.put(clothing, DisplacementType.REMOVE_OR_EQUIP);
		}
		
		// Check for access needed: TODO check this works TODO it doesn't TODO I did a temporary fix. please come back and fix this properly some time
		for (BlockedParts bp : clothing.getBlockedPartsMap(characterClothingOwner, clothing.getSlotEquippedTo())) {
			// Keep iterating through until we find the BlockedParts that corresponds to equipping (if not found, carry on, as this clothing doesn't need any access in order to be equipped):
			if (bp.displacementType == DisplacementType.REMOVE_OR_EQUIP) {
				if (bp.clothingAccessRequired == null) { // This clothing doesn't need any access in order to be equipped, so just carry on.
					break; 
					
				} else { // This clothing has access requirements in order to be unequipped. Check each piece of equipped clothing to see if it's blocking the access required:
					List<AbstractClothing> equippedClothingSorted = new ArrayList<>(clothingCurrentlyEquipped);
					equippedClothingSorted.sort(new ClothingZLayerComparator());
					for (AbstractClothing equippedClothing : equippedClothingSorted) {
						if (equippedClothing != clothing) {
							for (BlockedParts bpEquipped : equippedClothing.getBlockedPartsMap(characterClothingOwner, equippedClothing.getSlotEquippedTo())) {
								for (ClothingAccess caBlocked : bpEquipped.clothingAccessBlocked) {
									if (bp.clothingAccessRequired.contains(caBlocked)
											&& !equippedClothing.getDisplacedList().contains(bpEquipped.displacementType)
											&& !isDisplacementAvailableFromElsewhere(characterClothingOwner, equippedClothing, caBlocked)) {
										if (bpEquipped.displacementType != DisplacementType.REMOVE_OR_EQUIP) { // Can be displaced:
											// If clothingToRemove already contains this clothing, it's just going to be easier to remove the clothing fully than perform multiple displacements:
											if (!clothingToRemove.containsKey(equippedClothing))
												if (!equippedClothing.getDisplacedList().contains(bpEquipped.displacementType)){ // Not already displaced:
													if(automaticClothingManagement) {
//														System.out.println(equippedClothing +", "+ bpEquipped.displacementType);
														clothingToRemove.put(equippedClothing, bpEquipped.displacementType);
													} else {
														equipTextSB.append(characterClothingOwner.isPlayer()
																?"Your <b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>" + equippedClothing.getName() + "</b> "
																+ (equippedClothing.getClothingType().isPlural() ? "are" : "is") + " preventing your "+clothing.getName()+" from being removed!"
																:UtilText.parse(characterClothingOwner,
																		"[npc.NamePos] <b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>" + equippedClothing.getName() + "</b> "
																+ (equippedClothing.getClothingType().isPlural() ? "are" : "is") + " preventing [npc.her] "+clothing.getName()+" from being removed!"));
														
														blockingClothing = equippedClothing;
														return false;
													}
												}

										} else {
											if(equippedClothing.equals(previousClothingCheck)) {
												//TODO commented this out as it was causing issues, mainly in the situation where:
												// Unequipping karada with maid's dress and open-front cardigan equipped would cause bugs even though it was possible to unequip
//												System.err.println("Error: "+clothing.getName()+" and "+equippedClothing.getName()+" are blocking one another's removal!!!");
////												throw new IllegalArgumentException();
//												return true;
											}
											previousClothingCheck = clothing;
											if (isAbleToUnequip(equippedClothing, false, automaticClothingManagement, characterClothingOwner, characterRemovingClothing, true)) { // Can be removed:
												clothingToRemove.put(equippedClothing, DisplacementType.REMOVE_OR_EQUIP);
												
											} else {
												equipTextSB.append(characterClothingOwner.isPlayer()
														?"<br/>Your " + clothing.getName() + " can't be unequipped because your " + equippedClothing.getName() + " "
														+ (equippedClothing.getClothingType().isPlural() ? "are" : "is") + " in the way."
														:UtilText.parse(characterClothingOwner,
																"<br/>[npc.NamePos] " + clothing.getName() + " can't be unequipped because [npc.her] " + equippedClothing.getName() + " "
														+ (equippedClothing.getClothingType().isPlural() ? "are" : "is") + " in the way."));
												
												blockingClothing = equippedClothing;
												return false;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		if (continuingIsAbleToEquip && !unequipIfAble) {
			return true;
		}
		
		if (!automaticClothingManagement && clothingToRemove.size() > 1) { // Greater than 1, as it will contain the item of clothing that's trying to be removed.
//			Set<AbstractClothing> blockingClothingSet = clothingToRemove.keySet().stream().filter(c -> c != clothing).collect(Collectors.toSet());
			Set<AbstractClothing> blockingClothingSet = new HashSet<>(clothingToRemove.keySet());
			blockingClothingSet.removeIf(c -> c != clothing);
			
			equipTextSB.append(characterClothingOwner.isPlayer()
					?"Before your " + clothing.getName() + " "+(clothing.getClothingType().isPlural()?"are":"is")+" able to be removed, your " + Util.clothesToStringList(blockingClothingSet, false) + " need"
						+ (blockingClothingSet.size() > 1 ? "" : "s") + " to be removed."
					:UtilText.parse(characterClothingOwner,
							"Before [npc.namePos] " + clothing.getName() + " "+(clothing.getClothingType().isPlural()?"are":"is")+" able to be removed, [npc.her] " + Util.clothesToStringList(blockingClothingSet, false) + " need"
									+ (blockingClothingSet.size() > 1 ? "" : "s") + " to be removed."));
			
			blockingClothing = blockingClothingSet.stream().findAny().orElse(blockingClothing);
			
			return false;
		}

		// clothing can be removed!

		// If you want to unequip this clothing now:
		if (unequipIfAble) {
			// Sort clothing to remove in zLayer order(so you take off your shirt before removing bra etc.):
			// v0.3.14: Apparently iterating through clothingToRemove using this list makes Java cry and see values as null. Have add removalTextMap and iterate through that map and order removal text instead...
			List<AbstractClothing> tempClothingList = new ArrayList<>();
			tempClothingList.addAll(clothingToRemove.keySet());
			tempClothingList.sort(new ClothingZLayerComparator());
			
			Map<AbstractClothing, String> removalTextMap = new HashMap<>();
			
			// Description of each clothing item that is removed/displaced:
			for(Entry<AbstractClothing, DisplacementType> entry : clothingToRemove.entrySet()) {
				AbstractClothing c = entry.getKey();
				DisplacementType dt = entry.getValue();
				
				removalTextMap.put(c,
						(equipTextSB.length() == 0 ? "" : "<br/>")
						+ (dt == DisplacementType.REMOVE_OR_EQUIP
							? (c == clothing ? c.onUnequipApplyEffects(characterClothingOwner, characterRemovingClothing, (Main.game.isInSex()?Main.sex.getSexPace(characterRemovingClothing)==SexPace.DOM_ROUGH:false))
									: c.onUnequipText(characterClothingOwner, characterRemovingClothing, (Main.game.isInSex()?Main.sex.getSexPace(characterRemovingClothing)==SexPace.DOM_ROUGH:false)))
							: c.getClothingType().displaceText(
									characterClothingOwner,
									characterRemovingClothing,
									c.getSlotEquippedTo(),
									dt,
									(Main.game.isInSex()
										?Main.sex.getSexPace(characterRemovingClothing)==SexPace.DOM_ROUGH
										:false))));
			}
			
			// Append removal descriptions, sorted by zLayer:
			removalTextMap.keySet().stream().sorted(new ClothingZLayerComparator());
			for(Entry<AbstractClothing, String> entry : removalTextMap.entrySet()) {
				equipTextSB.append(entry.getValue());
			}
			

			// Actually unequip the clothing:
			clothingCurrentlyEquipped.remove(clothing);
			clothing.setSlotEquippedTo(null);

			// If it was displaced, clear it's displacements:
			clothing.getDisplacedList().clear();

			List<AbstractClothing> clothingToBeReplaced = new ArrayList<>();
			clothingToBeReplaced.addAll(clothingToRemove.keySet());
			clothingToBeReplaced.remove(clothing);
			clothingToBeReplaced.sort(new ReverseClothingZLayerComparator());

			if (!clothingToBeReplaced.isEmpty() && !continuingIsAbleToEquip) {
				equipTextSB.append(characterClothingOwner.isPlayer()
						?"<br/>You replace your " + Util.clothesToStringList(clothingToBeReplaced, false) + "."
						:UtilText.parse(characterClothingOwner,
								"<br/>You replace [npc.namePos] " + Util.clothesToStringList(clothingToBeReplaced, false) + "."));
			}
			
			// Check for clothing sets:
			if (clothing.getClothingType().getClothingSet() != null) {
				clothingSetCount.put(clothing.getClothingType().getClothingSet(), clothingSetCount.get(clothing.getClothingType().getClothingSet()) - 1);
			}
			
			clothingCurrentlyEquipped.sort(new ClothingRarityComparator());
		}

		return true;
	}

	private StringBuilder unableToDisplaceText = new StringBuilder();

	public String getDisplaceDescription() {
		return unableToDisplaceText.toString();
	}
	
	public void appendToDisplaceDescription(String textToAppend) {
		unableToDisplaceText.append(textToAppend);
	}
	
	public boolean isAbleToBeDisplaced(AbstractClothing clothing, DisplacementType dt, boolean displaceIfAble, boolean automaticClothingManagement, GameCharacter characterClothingOwner, GameCharacter characterRemovingClothing){
		return isAbleToBeDisplaced(clothing, dt, displaceIfAble, automaticClothingManagement, characterClothingOwner, characterRemovingClothing, false);
	}

	private Map<AbstractClothing, List<AbstractClothing>> displacementClothingChecked;
	/**
	 * If pass in DisplacementType.REMOVE_OR_EQUIP, returns isAbleToUnequip().
	 */
	public boolean isAbleToBeDisplaced(AbstractClothing clothing,
			DisplacementType dt,
			boolean displaceIfAble,
			boolean automaticClothingManagement,
			GameCharacter characterClothingOwner,
			GameCharacter characterRemovingClothing,
			boolean continuingIsAbleToEquip) {
		
		if(!continuingIsAbleToEquip) {
			displacementClothingChecked = new HashMap<>();
		}
		displacementClothingChecked.putIfAbsent(clothing, new ArrayList<>());
		
//		if(clothing.equals(previousClothingCheck)) {
//			System.err.println("Error: "+clothing.getName()+" and "+(displacementClothingCheck!=null?displacementClothingCheck.getName():"(unknown clothing)")+" are blocking one another's displacement!!!");
//			return true;
//		}
		
		previousClothingCheck = clothing;
		
		if(!displaceIfAble) {
			if(characterClothingOwner==null) {
				characterClothingOwner = Main.game.getPlayer();
			}
			if(characterRemovingClothing==null) {
				characterRemovingClothing = Main.game.getPlayer();
			}
		}
		
		if (dt == DisplacementType.REMOVE_OR_EQUIP) {
			return isAbleToUnequip(clothing, displaceIfAble, automaticClothingManagement, characterClothingOwner, characterRemovingClothing, true);
		}
		
		if (!continuingIsAbleToEquip) {
			clothingToRemove.clear();
			equipTextSB.setLength(0);
		}

		boolean displacementTypeFound = false;
		// Check for access needed:
		for (BlockedParts bp : clothing.getBlockedPartsMap(characterClothingOwner, clothing.getSlotEquippedTo())) {
			// Keep iterating through until we find the displacementType:
			if (bp.displacementType == dt) {
				displacementTypeFound = true;

				if (bp.clothingAccessRequired == null) {
					break; // This clothing doesn't need any access in order to be displaced in this manner, so just carry on.

				} else {
					// This clothing has access requirements in order to be displaced. Check each piece of equipped clothing to see if it's blocking the access required:
					for (AbstractClothing equippedClothing : clothingCurrentlyEquipped) {
						if(!displacementClothingChecked.get(clothing).contains(equippedClothing)) { // Only check if it hasn't been checked this way before.
							displacementClothingChecked.get(clothing).add(equippedClothing);
							
							if (equippedClothing != clothing) {
								for (BlockedParts bpEquipped : equippedClothing.getBlockedPartsMap(characterClothingOwner, equippedClothing.getSlotEquippedTo())) {
									for (ClothingAccess caBlocked : bpEquipped.clothingAccessBlocked) {
										if (bp.clothingAccessRequired.contains(caBlocked)
												&& (automaticClothingManagement
														?isAbleToBeDisplaced(equippedClothing, bpEquipped.displacementType, false, true, characterClothingOwner, characterRemovingClothing, true)
														:!equippedClothing.getDisplacedList().contains(bpEquipped.displacementType))
												&& !isDisplacementAvailableFromElsewhere(characterClothingOwner, equippedClothing, caBlocked)) {
											
											if (bpEquipped.displacementType != DisplacementType.REMOVE_OR_EQUIP /*&& !clothingToRemove.containsKey(equippedClothing)*/) { // Can be displaced:
												if (!equippedClothing.getDisplacedList().contains(bpEquipped.displacementType)){ // Not already displaced:
													if(automaticClothingManagement) {
														clothingToRemove.put(equippedClothing, bpEquipped.displacementType);
													} else {
														unableToDisplaceText = new StringBuilder(Util.capitaliseSentence(clothing.getClothingType().getDeterminer()) + " " + clothing.getName() + " can't be displaced because "
																+ equippedClothing.getClothingType().getDeterminer() + " " + equippedClothing.getName() + " " + (equippedClothing.getClothingType().isPlural() ? "are" : "is") + " in the way.");
														blockingClothing = equippedClothing;
														return false;
													}
												}
	
											} else {
												if (automaticClothingManagement && isAbleToUnequip(equippedClothing, false, automaticClothingManagement, characterClothingOwner, characterRemovingClothing, true)) { // Can be removed:
													clothingToRemove.put(equippedClothing, DisplacementType.REMOVE_OR_EQUIP);
												} else {
													unableToDisplaceText = new StringBuilder(Util.capitaliseSentence(clothing.getClothingType().getDeterminer()) + " " + clothing.getName() + " can't be displaced because "
															+ equippedClothing.getClothingType().getDeterminer() + " " + equippedClothing.getName() + " " + (equippedClothing.getClothingType().isPlural() ? "are" : "is") + " in the way.");
													blockingClothing = equippedClothing;
													return false;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		// The supplied clothing cannot be displaced in this manner!
		if (!displacementTypeFound) {
			throw new IllegalArgumentException("The supplied clothing cannot be displaced in this manner!");
		}

		// Is able to be displaced!

		if (displaceIfAble) {
			if (clothing.getDisplacedList().contains(dt)) {
				unableToDisplaceText = new StringBuilder(Util.capitaliseSentence(clothing.getClothingType().getDeterminer()) + " " + clothing.getName() + " is already displaced!");
				return false;
			}

			clothing.getDisplacedList().add(dt);

			List<AbstractClothing> tempClothingList = new ArrayList<>();
			tempClothingList.addAll(clothingToRemove.keySet());
			tempClothingList.sort(new ClothingZLayerComparator());

			unableToDisplaceText = new StringBuilder();

			// Description of each clothing item that is removed/displaced:
			for (AbstractClothing c : tempClothingList) {
				unableToDisplaceText.append(
						(unableToDisplaceText.length() == 0
							? ""
							: "<br/>")
						+ (clothingToRemove.get(c) == DisplacementType.REMOVE_OR_EQUIP
							? c.onUnequipText(characterClothingOwner, characterRemovingClothing, (Main.game.isInSex()?Main.sex.getSexPace(characterRemovingClothing)==SexPace.DOM_ROUGH:false))
							: c.getClothingType().displaceText(characterClothingOwner, characterRemovingClothing, c.getSlotEquippedTo(), clothingToRemove.get(c), (Main.game.isInSex()?Main.sex.getSexPace(characterRemovingClothing)==SexPace.DOM_ROUGH:false))));
			}

			unableToDisplaceText.append(
					(unableToDisplaceText.length() == 0
						? ""
						: "<br/><span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>")
					+ clothing.getClothingType().displaceText(characterClothingOwner, characterRemovingClothing, clothing.getSlotEquippedTo(), dt, (Main.game.isInSex()?Main.sex.getSexPace(characterRemovingClothing)==SexPace.DOM_ROUGH:false))
					+ "</span>");

			List<AbstractClothing> replaceClothingList = new ArrayList<>();
			replaceClothingList.addAll(clothingToRemove.keySet());
			replaceClothingList.remove(clothing);
			replaceClothingList.sort(new ReverseClothingZLayerComparator());
			if (!replaceClothingList.isEmpty()) {
				unableToDisplaceText.append(UtilText.parse(characterClothingOwner, "<br/>You replace [npc.namePos] ") + Util.clothesToStringList(replaceClothingList, false) + ".");
			}
			
			return true;
		}
		unableToDisplaceText = new StringBuilder(Util.capitaliseSentence(clothing.getClothingType().getDeterminer()) + " " + clothing.getName() + " is able to be displaced.");
		return true;
	}

	private StringBuilder unableToReplaceText = new StringBuilder();

	
	public String getReplaceDescription() {
		return unableToReplaceText.toString();
	}

	
	public boolean isAbleToBeReplaced(AbstractClothing clothing, DisplacementType dt, boolean replaceIfAble, boolean automaticClothingManagement, GameCharacter characterClothingOwner, GameCharacter characterRemovingClothing) {
		if (dt == DisplacementType.REMOVE_OR_EQUIP)
			throw new IllegalArgumentException("Can't replace DisplacementType.REMOVE_OR_EQUIP!");

		if (automaticClothingManagement)
			clothingToRemove.clear();

		boolean displacementTypeFound = false;
		// Check for access needed: TODO check this works
		for (BlockedParts bp : clothing.getBlockedPartsMap(characterClothingOwner, clothing.getSlotEquippedTo())) {

			// Keep iterating through until we find the displacementType:
			if (bp.displacementType == dt) {
				displacementTypeFound = true;

				if (bp.clothingAccessRequired == null) {
					break; // This clothing doesn't need any access in order to
							// be displaced in this manner, so just carry on.

				} else {
					// This clothing has access requirements in order to be displaced. Check each piece of equipped clothing to see if it's blocking the access required:
					for (AbstractClothing equippedClothing : clothingCurrentlyEquipped) {
						for (BlockedParts bpEquipped : equippedClothing.getBlockedPartsMap(characterClothingOwner, equippedClothing.getSlotEquippedTo())) {
							for (ClothingAccess caBlocked : bpEquipped.clothingAccessBlocked) {

								if (bp.clothingAccessRequired.contains(caBlocked)
										&& !equippedClothing.getDisplacedList().contains(bpEquipped.displacementType)
										&& !isDisplacementAvailableFromElsewhere(characterClothingOwner, equippedClothing, caBlocked)) {
									if (bpEquipped.displacementType != DisplacementType.REMOVE_OR_EQUIP && !clothingToRemove.containsKey(equippedClothing)) { // Can be displaced:
										if (!equippedClothing.getDisplacedList().contains(bpEquipped.displacementType)){ // Not already displaced:
											if(automaticClothingManagement)
												clothingToRemove.put(equippedClothing, bpEquipped.displacementType);
											else{
												unableToReplaceText = new StringBuilder(Util.capitaliseSentence(clothing.getClothingType().getDeterminer()) + " " + clothing.getName() + " can't be replaced because "
														+ equippedClothing.getClothingType().getDeterminer() + " " + equippedClothing.getName() + " " + (equippedClothing.getClothingType().isPlural() ? "are" : "is") + " in the way.");
												blockingClothing = equippedClothing;
												return false;
											}
										}
									} else {
										if (isAbleToUnequip(equippedClothing, false, automaticClothingManagement, characterClothingOwner, characterRemovingClothing, true)) // Can  be removed:
											clothingToRemove.put(equippedClothing, DisplacementType.REMOVE_OR_EQUIP);
										else {
											unableToReplaceText = new StringBuilder(Util.capitaliseSentence(clothing.getClothingType().getDeterminer()) + " " + clothing.getName() + " can't be replaced because "
													+ equippedClothing.getClothingType().getDeterminer() + " " + equippedClothing.getName() + " " + (equippedClothing.getClothingType().isPlural() ? "are" : "is") + " in the way.");
											blockingClothing = equippedClothing;
											return false;
										}
									}
								}

							}
						}
					}
				}
			}
		}

		// The supplied clothing cannot be displaced in this manner!
		if (!displacementTypeFound) {
			throw new IllegalArgumentException("The supplied clothing cannot be replaced in this manner!");
		}

		// Is able to be replaced!

		if (replaceIfAble) {
			if (!clothing.getDisplacedList().contains(dt)) {
				unableToReplaceText = new StringBuilder(Util.capitaliseSentence(clothing.getClothingType().getDeterminer()) + " " + clothing.getName() + " is already replaced!");
				return false;
			}

			clothing.getDisplacedList().remove(dt);

			List<AbstractClothing> tempClothingList = new ArrayList<>();
			tempClothingList.addAll(clothingToRemove.keySet());
			tempClothingList.sort(new ClothingZLayerComparator());

			unableToReplaceText = new StringBuilder();

			// Description of each clothing item that is removed/displaced:
			for (AbstractClothing c : tempClothingList)
				unableToReplaceText.append((unableToReplaceText.length() == 0 ? "" : "<br/>")
						+ (clothingToRemove.get(c) == DisplacementType.REMOVE_OR_EQUIP
							? (c == clothing
								? c.onUnequipApplyEffects(characterClothingOwner, characterRemovingClothing, (Main.game.isInSex()?Main.sex.getSexPace(characterRemovingClothing)==SexPace.DOM_ROUGH:false))
								: c.onUnequipText(characterClothingOwner, characterRemovingClothing, (Main.game.isInSex()?Main.sex.getSexPace(characterRemovingClothing)==SexPace.DOM_ROUGH:false)))
							: c.getClothingType().displaceText(characterClothingOwner, characterRemovingClothing, c.getSlotEquippedTo(), clothingToRemove.get(c), (Main.game.isInSex()?Main.sex.getSexPace(characterRemovingClothing)==SexPace.DOM_ROUGH:false))));

			unableToReplaceText
					.append(
							(unableToReplaceText.length() == 0
								? ""
								: "<br/><span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>")
							+ clothing.getClothingType().replaceText(characterClothingOwner, characterRemovingClothing, clothing.getSlotEquippedTo(), dt, (Main.game.isInSex()?Main.sex.getSexPace(characterRemovingClothing)==SexPace.DOM_ROUGH:false))
							+ "</span>");

			List<AbstractClothing> replaceClothingList = new ArrayList<>();
			replaceClothingList.addAll(clothingToRemove.keySet());
			replaceClothingList.sort(new ReverseClothingZLayerComparator());
			if (!replaceClothingList.isEmpty()) {
				unableToReplaceText.append(UtilText.parse(characterClothingOwner, "<br/>You replace [npc.namePos] ") + Util.clothesToStringList(replaceClothingList, false) + ".");
			}
			
			return true;
		}

		unableToReplaceText = new StringBuilder(Util.capitaliseSentence(clothing.getClothingType().getDeterminer()) + " " + clothing.getName() + " is able to be replaced.");
		return true;
	}

	private boolean isDisplacementAvailableFromElsewhere(GameCharacter character, AbstractClothing clothing, ClothingAccess accessRequired) {
		for (BlockedParts bp : clothing.getBlockedPartsMap(character, clothing.getSlotEquippedTo())) {
			if (bp.clothingAccessBlocked.contains(accessRequired)) {// If this clothing is blocking the area you are trying to access:
				if (clothing.getDisplacedList().contains(bp.displacementType)) { // If the clothing has been displaced:
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @param area Area you want to get to.
	 * @param byRemovingClothing Allow consideration of clothing removal or not.
	 * @return True if can access slot. (if byRemovingClothing is true, then it tells you if you are able to get to the slot by removing clothing, not that it is available right now).
	 */
	public boolean isAbleToAccessCoverableArea(GameCharacter character, CoverableArea area, boolean byRemovingClothing) {
		return getClothingBlockingCoverableAreaAccess(character, area, byRemovingClothing)==null;
	}
	
	/**
	 *  Copy of isAbleToAccessCoverableArea(), but returns the clothing responsible for the block.
	 * @param area Area to check to see if it's blocked.
	 * @param byRemovingClothing Allow removing of clothing in the check.
	 * @return Clothing that's responsible for blocking the supplied area. Null if not blocked.
	 */
	public AbstractClothing getClothingBlockingCoverableAreaAccess(GameCharacter character, CoverableArea area, boolean byRemovingClothing) {
		List<AbstractClothing> blockingClothingList = getBlockingCoverableAreaClothingList(character, area, byRemovingClothing);
		
 		if(!blockingClothingList.isEmpty()) {
	 		for(AbstractClothing c : blockingClothingList) {
	 			if(c.isSealed()) {
	 				return c;
	 			}
	 		}
	 		return blockingClothingList.get(0);
 		}
 		
		return null;
	}
	
	public List<AbstractClothing> getBlockingCoverableAreaClothingList(GameCharacter character, CoverableArea area, boolean byRemovingClothing) {
		List<AbstractClothing> blockingClothingList = new ArrayList<>();

		// For every piece of equipped clothing, if it's blocking the coverable area, see if it can be displaced or removed.
		// If it can't, continue searching to see if another displacement type has revealed that area.
 		for (AbstractClothing clothing : clothingCurrentlyEquipped) {
 			List<BlockedParts> blockedPartsMap = clothing.getBlockedPartsMap(character, clothing.getSlotEquippedTo());
 			if(blockedPartsMap==null) {
 				System.err.println("Clothing error in getBlockingCoverableAreaClothingList(): blockedPartsMap is returning null!");
 				continue;
 			}
			for (BlockedParts bp : blockedPartsMap) {
				if (bp.blockedBodyParts.contains(area)) {// If this clothing is blocking the area you are trying to access:
					if (!clothing.getDisplacedList().contains(bp.displacementType)) { // If the clothing  hasn't been displaced:
						if (byRemovingClothing) {
							if (bp.displacementType == DisplacementType.REMOVE_OR_EQUIP) {
								if (!isAbleToUnequip(clothing, false, byRemovingClothing, null, null)) {// If the clothing can't be removed from this area:
									if(!isCoverableAreaExposedFromElsewhere(character, clothing, area)) {
										blockingClothingList.add(clothing);
										continue;
									}
								}
							} else {
								if (!isAbleToBeDisplaced(clothing, bp.displacementType, false, byRemovingClothing, null, null)) {// If the clothing can't be displaced from this area:
									if(!isCoverableAreaExposedFromElsewhere(character, clothing, area)) {
										blockingClothingList.add(clothing);
										continue;
									}
								}
							}
						} else {
							if(!isCoverableAreaExposedFromElsewhere(character, clothing, area)) {
								blockingClothingList.add(clothing);
								continue;
							}
						}
					}
				}
			}
		}
 		
	 	return blockingClothingList;
	}
	
	private boolean isCoverableAreaExposedFromElsewhere(GameCharacter character, AbstractClothing clothing, CoverableArea area) {
		for (BlockedParts bp : clothing.getBlockedPartsMap(character, clothing.getSlotEquippedTo())) {
			if (bp.blockedBodyParts.contains(area)) {// If this clothing is blocking the area you are trying to access:
				if (clothing.getDisplacedList().contains(bp.displacementType)) { // If the clothing has been displaced:
					return true;
				}
			}
		}
		return false;
	}

	
	public SimpleEntry<AbstractClothing, DisplacementType> getNextClothingToRemoveForCoverableAreaAccess(GameCharacter character, CoverableArea coverableArea) { 
		List<AbstractClothing> zLayerSortedList = new ArrayList<>(clothingCurrentlyEquipped);
		zLayerSortedList.sort(new ClothingZLayerComparator());

		for (AbstractClothing clothing : zLayerSortedList) {
			for (BlockedParts bp : clothing.getBlockedPartsMap(character, clothing.getSlotEquippedTo())) {
				if (bp.blockedBodyParts.contains(coverableArea) 
						&& !clothing.getDisplacedList().contains(bp.displacementType)
						&& !isCoverableAreaExposedFromElsewhere(character, clothing, coverableArea)) {
					// this clothing is blocking the part we want access to, so make that our starting point:
					return findNextClothingDisplacement(character, coverableArea, clothing, bp.displacementType, zLayerSortedList, true);
				}
			}
		}
		//System.err.print("There is no clothing covering this part!");
		return null;
	}
	
	private Map<AbstractClothing, DisplacementType> previousDisplacements;
	public SimpleEntry<AbstractClothing, DisplacementType> findNextClothingDisplacement(
			GameCharacter character, CoverableArea coverableArea, AbstractClothing clothingToRemove, DisplacementType displacement, List<AbstractClothing> zLayerSortedList, boolean initialMethodCall) {
		if(initialMethodCall) {
			previousDisplacements = new HashMap<>();
			previousDisplacements.put(clothingToRemove, displacement);
		}
		
		for(BlockedParts bp : clothingToRemove.getBlockedPartsMap(character, clothingToRemove.getSlotEquippedTo())) {
			if (bp.displacementType == displacement) {
				for (ClothingAccess ca : bp.clothingAccessRequired) {
					for (AbstractClothing clothing : zLayerSortedList) {
						if (clothing != clothingToRemove) {
							for (BlockedParts bpIterated : clothing.getBlockedPartsMap(character, clothing.getSlotEquippedTo())) {
//								if(bpIterated.clothingAccessBlocked.contains(ca)) {
//									System.out.println(clothing.getName()+" | "+clothingToRemove.getName()+" | "+ca
//											+"\n\t"+!clothing.getDisplacedList().contains(bpIterated.displacementType)
//											+"\n\t"+!isCoverableAreaExposedFromElsewhere(character, clothing, coverableArea));
//								}
								if (bpIterated.clothingAccessBlocked.contains(ca)
										&& !clothing.getDisplacedList().contains(bpIterated.displacementType)
										// This fixes issues with NPCs performing redundant displacements (unzipping + pulling down):
										&& (bp.displacementType==DisplacementType.REMOVE_OR_EQUIP || !isCoverableAreaExposedFromElsewhere(character, clothing, coverableArea))
										) {
//									System.out.println(":3 "+clothing.getName()+" | "+clothingToRemove.getName()+" | "+ca);
									if(previousDisplacements.containsKey(clothing) && previousDisplacements.get(clothing).equals(bpIterated.displacementType)) {
										System.err.println("findNextClothingDisplacement() error: "+clothing.getName()+" is interfering with "+clothingToRemove.getName());
										return new SimpleEntry<>(clothing, bpIterated.displacementType);
									}
									previousDisplacements.put(clothing, bpIterated.displacementType);
									// This clothing is blocking the clothing we wanted to displace, so now we re-start by wanting to displace this new clothing:
									return findNextClothingDisplacement(character, coverableArea, clothing, bpIterated.displacementType, zLayerSortedList, false);
								}
							}
						}
					}
				}
			}
		}
		return new SimpleEntry<>(clothingToRemove, displacement);
	}

	/**
	 * @param character The character who is being checked.
	 * @param area The area to test for exposure.
	 * @param justVisible pass in true if you want to know if the area is visible (i.e. through transparent clothes). Pass in false if you want to know if there is any clothing blocking the area, regardless of transparency.
	 * @return
	 */
	public boolean isCoverableAreaExposed(GameCharacter character, CoverableArea area, boolean justVisible) {
		if(area==CoverableArea.TESTICLES) { // There are no proper checks in clothing for testicle access, so use penis access:
			return isCoverableAreaExposed(character, CoverableArea.PENIS, justVisible);
		}
		
		if(this.getExtraBlockedParts()!=null && justVisible) {
			if(this.getExtraBlockedParts().blockedBodyParts.contains(area)) {
				return false;
			}
		}
		
		if(area==CoverableArea.BREASTS_CROTCH || area==CoverableArea.NIPPLES_CROTCH) {
			switch(character.getLegConfiguration()) {
				case ARACHNID:
				case BIPEDAL:
				case CEPHALOPOD:
				case TAIL:
				case TAIL_LONG:
				case AVIAN:
				case WINGED_BIPED:
					// Crotch-boobs are concealed by stomach clothing for all but taurs:
//					return isAbleToAccessCoverableArea(character, CoverableArea.STOMACH, false);
					return isCoverableAreaExposed(character, CoverableArea.STOMACH, justVisible);
				case QUADRUPEDAL:// Crotch-boobs are concealed by thigh-concealing clothing for taurs:
					// Should only account for taur-specific clothing:
					List<AbstractClothing> clothingBlocking = getBlockingCoverableAreaClothingList(character, CoverableArea.THIGHS, false);
					clothingBlocking.removeIf(c -> !c.getItemTags().contains(ItemTag.FITS_TAUR_BODY) || c.getItemTags().contains(ItemTag.TRANSPARENT));
					return clothingBlocking.isEmpty();//TODO test
			}
		}
		
		if(justVisible) {
			List<AbstractClothing> clothingBlocking = getBlockingCoverableAreaClothingList(character, area, false);
			clothingBlocking.removeIf(c -> c.getItemTags().contains(ItemTag.TRANSPARENT));
			return clothingBlocking.isEmpty();//TODO test
		} else {
			return isAbleToAccessCoverableArea(character, area, false);
		}
	}

	public int getClothingAverageFemininity() {
		if(clothingCurrentlyEquipped.isEmpty()) {
			return 50;
		}
		
		int average = 50;
		for (AbstractClothing c : clothingCurrentlyEquipped) {
			if (c.getClothingType().getFemininityRestriction() == Femininity.FEMININE) {
				average += 75;
				
			} else if (c.getClothingType().getFemininityRestriction() == Femininity.MASCULINE) {
				average += 25;
				
			} else {
				average += 50;
				
			}
		}

		average /= (clothingCurrentlyEquipped.size() + 1);
		
		return average;
	}

	/**
	 * The lowest piece of clothing that is blocking this slot.<br/>
	 * <b>Note:</b> This takes into account displacement, so, for example, if your panties are displaced, and are the only piece of clothing otherwise blocking your vagina,
	 *  this method will return null for getLowestZLayerCoverableArea(CoverableArea.VAGINA)!
	 */
	public AbstractClothing getLowestZLayerCoverableArea(GameCharacter character, CoverableArea area) {
		AbstractClothing c = null;
		
		List<AbstractClothing> clothingBlocking = null;
		
		if(area==CoverableArea.BREASTS_CROTCH || area==CoverableArea.NIPPLES_CROTCH) {
			switch(character.getLegConfiguration()) {
				case ARACHNID:
				case BIPEDAL:
				case CEPHALOPOD:
				case TAIL:
				case TAIL_LONG:
				case AVIAN:
				case WINGED_BIPED:
					// Crotch-boobs are concealed by stomach clothing for all but taurs:
					clothingBlocking = getBlockingCoverableAreaClothingList(character, CoverableArea.STOMACH, false);
					break;
				case QUADRUPEDAL:// Crotch-boobs are concealed by thigh-concealing clothing for taurs:
					// Should only account for taur-specific clothing:
					clothingBlocking = getBlockingCoverableAreaClothingList(character, CoverableArea.THIGHS, false);
					clothingBlocking.removeIf(clothing -> !clothing.getItemTags().contains(ItemTag.FITS_TAUR_BODY) || clothing.getItemTags().contains(ItemTag.TRANSPARENT));
					break;
			}
		} else {
			clothingBlocking = getBlockingCoverableAreaClothingList(character, area, false);
		}
		
		for (AbstractClothing clothing : clothingBlocking) {
			if (c == null || clothing.getSlotEquippedTo().getZLayer() < c.getSlotEquippedTo().getZLayer()) {
				c = clothing;
			}
//			System.out.println(clothing.getName() + ": "+clothing.getClothingType().getzLayer() +", "+ c.getClothingType().getzLayer());
		}

		return c;
	}

	/**
	 * The highest piece of clothing that is blocking this slot.<br/>
	 * <b>Note:</b> This takes into account displacement, so, for example, if your yoga pants are displaced, and are revealing your panties,
	 *  this method will return panties for getHighestZLayerCoverableArea(CoverableArea.VAGINA)!
	 */
	public AbstractClothing getHighestZLayerCoverableArea(GameCharacter character, CoverableArea area) {
		AbstractClothing c = null;

		List<AbstractClothing> clothingBlocking = null;
		
		if(area==CoverableArea.BREASTS_CROTCH || area==CoverableArea.NIPPLES_CROTCH) {
			switch(character.getLegConfiguration()) {
				case ARACHNID:
				case BIPEDAL:
				case CEPHALOPOD:
				case TAIL:
				case TAIL_LONG:
				case AVIAN:
				case WINGED_BIPED:
					// Crotch-boobs are concealed by stomach clothing for all but taurs:
					clothingBlocking = getBlockingCoverableAreaClothingList(character, CoverableArea.STOMACH, false);
					break;
				case QUADRUPEDAL:// Crotch-boobs are concealed by thigh-concealing clothing for taurs:
					// Should only account for taur-specific clothing:
					clothingBlocking = getBlockingCoverableAreaClothingList(character, CoverableArea.THIGHS, false);
					clothingBlocking.removeIf(clothing -> !clothing.getItemTags().contains(ItemTag.FITS_TAUR_BODY) || clothing.getItemTags().contains(ItemTag.TRANSPARENT));
					break;
			}
		} else {
			clothingBlocking = getBlockingCoverableAreaClothingList(character, area, false);
		}
		
		for (AbstractClothing clothing : clothingBlocking) {
			if (c == null || clothing.getSlotEquippedTo().getZLayer() > c.getSlotEquippedTo().getZLayer()) {
				c = clothing;
			}
		}
		
		return c;
	}

	public AbstractClothing getBlockingClothing() {
		return blockingClothing;
	}
	
	public boolean isSlotIncompatible(GameCharacter clothingOwner, InventorySlot slot) {
		for(AbstractClothing ct : clothingCurrentlyEquipped) {
			for (InventorySlot incompatibleSlot : ct.getIncompatibleSlots(clothingOwner, ct.getSlotEquippedTo())) {
				if(incompatibleSlot == slot) {
					return true;
				}
			}
		}
		return false;
	}

	public Set<InventorySlot> getDirtySlots() {
		return dirtySlots;
	}
	
	public boolean isDirtySlot(InventorySlot slot) {
		return dirtySlots.contains(slot);
	}
	
	public boolean addDirtySlot(InventorySlot slot) {
		return dirtySlots.add(slot);
	}
	
	public boolean removeDirtySlot(InventorySlot slot) {
		return dirtySlots.remove(slot);
	}

	public void cleanAllDirtySlots() {
		dirtySlots.clear();
	}
	
}