package com.base.game.inventory;

import java.io.Serializable;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.base.game.character.GameCharacter;
import com.base.game.character.body.types.PenisType;
import com.base.game.character.body.types.VaginaType;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.inventory.clothing.ClothingAccess;
import com.base.game.inventory.clothing.ClothingSet;
import com.base.game.inventory.clothing.ClothingType;
import com.base.game.inventory.clothing.BlockedParts;
import com.base.game.inventory.clothing.CoverableArea;
import com.base.game.inventory.clothing.DisplacementType;
import com.base.game.inventory.enchanting.TFEssence;
import com.base.game.inventory.item.AbstractItem;
import com.base.game.inventory.item.ItemType;
import com.base.game.inventory.weapon.AbstractWeapon;
import com.base.main.Main;
import com.base.utils.AbstractClothingRarityComparator;
import com.base.utils.ClothingZLayerComparator;
import com.base.utils.Colour;
import com.base.utils.InventoryClothingComparator;
import com.base.utils.InventoryItemComparator;
import com.base.utils.InventoryWeaponComparator;
import com.base.utils.ReverseClothingZLayerComparator;
import com.base.utils.Util;
import com.base.utils.Vector2i;;

/**
 * Inventory for a Character. Tracks weapons equipped, clothes worn & inventory space.
 * 
 * @since 0.1.0
 * @version 0.1.7
 * @author Innoxia
 */
public class CharacterInventory implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<AbstractItem> itemsInInventory;
	private List<AbstractWeapon> weaponsInInventory;
	private List<AbstractClothing> clothingInInventory;
	
	private Map<AbstractWeapon, Integer> weaponDuplicates;
	private Map<AbstractClothing, Integer> clothingDuplicates;
	private Map<AbstractItem, Integer> itemDuplicates;
	
	private Map<TFEssence, Integer> essenceMap;
	
	protected int money;
	
	// Clothing that's currently blocking this inventory from unequipping/displacing something:
	private AbstractClothing blockingClothing;

	// Weapons
	private AbstractWeapon mainWeapon, offhandWeapon;

	private List<AbstractClothing> clothingCurrentlyEquipped;

	// ClothingSets being worn:
	private Map<ClothingSet, Integer> clothingSetCount = new EnumMap<>(ClothingSet.class);

	private int maxInventorySpace;

	public CharacterInventory(int money) {

		this.money = money;

		itemsInInventory = new ArrayList<>();
		weaponsInInventory = new ArrayList<>();
		clothingInInventory = new ArrayList<>();

		weaponDuplicates = new LinkedHashMap<>();
		clothingDuplicates = new LinkedHashMap<>();
		itemDuplicates = new LinkedHashMap<>();
		
		essenceMap = new EnumMap<>(TFEssence.class);
		for(TFEssence essence : TFEssence.values()) {
			essenceMap.put(essence, 0);
		}
		
		mainWeapon = null;
		offhandWeapon = null;

		clothingCurrentlyEquipped = new ArrayList<>();
		clothingSetCount = new EnumMap<>(ClothingSet.class);

		maxInventorySpace = 24;
	}
	
	public List<AbstractItem> getItemsInInventory() {
		return itemsInInventory;
	}

	public List<AbstractWeapon> getWeaponsInInventory() {
		return weaponsInInventory;
	}

	public List<AbstractClothing> getClothingInInventory() {
		return clothingInInventory;
	}

	public int getMoney() {
		return money;
	}

	/**
	 * Does not allow money to fall below 0.
	 */
	public void setMoney(int money) {
		if (money < 0)
			this.money = 0;
		else
			this.money = money;
	};
	
	/**
	 * Does not allow money to fall below 0.
	 */
	public void incrementMoney(int increment) {
		money += increment;
		if (money < 0)
			money = 0;
	}
	
	public Map<TFEssence, Integer> getEssenceMap() {
		return essenceMap;
	}
	
	public int getEssenceCount(TFEssence essence) {
		return essenceMap.get(essence);
	}
	
	public void incrementEssenceCount(TFEssence essence, int increment) {
		if(getEssenceCount(essence)+increment < 0)
			essenceMap.put(essence, 0);
		else
			essenceMap.put(essence, getEssenceCount(essence)+increment);
	}

	public int getMaximumInventorySpace() {
		return maxInventorySpace;
	}
	
	public void clearNonEquippedInventory(){
		itemsInInventory.clear();
		weaponsInInventory.clear();
		clothingInInventory.clear();
		recalculateMapOfDuplicateClothing();
		money=0;
	}
	
	public void setMaximumInventorySpace(int maxInventorySpace) {
		this.maxInventorySpace = maxInventorySpace;
	}
	
	public boolean isInventoryFull() {
		return getInventorySlotsTaken() >= maxInventorySpace;
	}
	
	/**
	 * @return The number of inventory slots currently occupied. This takes into account weapon, clothing, and item stacking.
	 */
	public int getInventorySlotsTaken() {
		int count = getUniqueWeaponCount() + getUniqueClothingCount() + getUniqueItemCount();
		
		return count;
	}
	
	
	// -------------------- Items -------------------- //
	
	public Map<AbstractItem, Integer> getMapOfDuplicateItems() {
		return itemDuplicates;
	}
	
	private void recalculateMapOfDuplicateItems() {
		itemDuplicates.clear();
		
		itemsInInventory.sort(new InventoryItemComparator());
		
		for (AbstractItem item : itemsInInventory) {
			if (!itemDuplicates.containsKey(item))
				itemDuplicates.put(item, 1);
			else
				itemDuplicates.put(item, itemDuplicates.get(item)+1);
		}
	}
	
	public int getUniqueItemCount() {
		return getMapOfDuplicateItems().size();
	}
	
	public int getItemCount() {
		return itemsInInventory.size();
	}
	
	public int getItemCount(AbstractItem item) {
		if (!itemDuplicates.containsKey(item))
			return itemsInInventory.contains(item)?1:0;
		else
			return itemDuplicates.get(item);
		
	}
	
	public AbstractItem getItem(int index) {
		return itemsInInventory.get(index);
	}
	
	/**
	 * Add an item to this inventory.
	 * @return true if added, false if inventory was full.
	 */
	public boolean addItem(AbstractItem item) {
		if (!isInventoryFull() || hasItem(item)){
			itemsInInventory.add(item);
			recalculateMapOfDuplicateItems();
			return true;
		}
		
		return false;
	}
	
	public boolean removeItem(AbstractItem item) {
		if(itemsInInventory.remove(item)) {
			recalculateMapOfDuplicateItems();
			return true;
		} else {
			return false;
		}
	}
	
	public boolean hasItem(AbstractItem item) {
		return itemsInInventory.contains(item);
	}
	
	/**
	 * @return true if one of the items in this inventory has the same type as the Item provided.
	 */
	public boolean hasItemType(ItemType item) {
		for(AbstractItem abstractItem : itemsInInventory)
			if(abstractItem.getItemType() == item)
				return true;
		
		return false;
	}
	
	public boolean dropItem(AbstractItem item, Vector2i location) {
		if (itemsInInventory.contains(item)) {
			Main.game.getActiveWorld().getCell(location).getInventory().addItem(item);
			removeItem(item);
			recalculateMapOfDuplicateItems();
			return true;
		}
		
		return false;
	}
	
	
	// -------------------- Weapons -------------------- //
	
	public Map<AbstractWeapon, Integer> getMapOfDuplicateWeapons() {
		return weaponDuplicates;
	}
	
	private void recalculateMapOfDuplicateWeapons() {
		weaponDuplicates.clear();

		weaponsInInventory.sort(new InventoryWeaponComparator());
		
		for (AbstractWeapon weapon : weaponsInInventory) {
			
			if (!weaponDuplicates.containsKey(weapon))
				weaponDuplicates.put(weapon, 1);
			else
				weaponDuplicates.put(weapon, weaponDuplicates.get(weapon)+1);
		}
	}
	
	public int getUniqueWeaponCount() {
		return getMapOfDuplicateWeapons().size();
	}
	
	public int getWeaponCount() {
		return weaponsInInventory.size();
	}
	
	public int getWeaponCount(AbstractWeapon weapon) {
		if (!weaponDuplicates.containsKey(weapon))
			return weaponsInInventory.contains(weapon)?1:0;
		else
			return weaponDuplicates.get(weapon);
		
	}
	
	public AbstractWeapon getWeapon(int index) {
		return weaponsInInventory.get(index);
	}
	
	/**
	 * Add a weapon to this inventory.
	 * @return true if added, false if inventory was full.
	 */
	public boolean addWeapon(AbstractWeapon weapon) {
		if (!isInventoryFull() || hasWeapon(weapon)){
			weaponsInInventory.add(weapon);
			recalculateMapOfDuplicateWeapons();
			return true;
		}
		
		return false;
	}
	
	public boolean removeWeapon(AbstractWeapon weapon) {
		if(weaponsInInventory.remove(weapon)) {
			recalculateMapOfDuplicateWeapons();
			return true;
		} else {
			return false;
		}
	}
	
	public boolean hasWeapon(AbstractWeapon weapon) {
		return weaponsInInventory.contains(weapon);
	}

	public boolean dropWeapon(AbstractWeapon weapon, Vector2i location) {
		if (weaponsInInventory.contains(weapon)) {
			Main.game.getActiveWorld().getCell(location).getInventory().addWeapon(weapon);
			weaponsInInventory.remove(weapon);
			recalculateMapOfDuplicateWeapons();
			return true;
		}

		return false;
	}
	
	public AbstractWeapon getMainWeapon() {
		return mainWeapon;
	}
	public void equipMainWeapon(AbstractWeapon weapon) {
		mainWeapon = weapon;
	}
	public void unequipMainWEapon() {
		mainWeapon = null;
	}
	
	public AbstractWeapon getOffhandWeapon() {
		return offhandWeapon;
	}
	public void equipOffhandWeapon(AbstractWeapon weapon) {
		offhandWeapon = weapon;
	}
	public void unequipOffhandWeapon() {
		offhandWeapon = null;
	}
	
	
	// -------------------- Clothing -------------------- //
	
	public Map<AbstractClothing, Integer> getMapOfDuplicateClothing() {
		return clothingDuplicates;
	}
	
	private void recalculateMapOfDuplicateClothing() {
		clothingDuplicates.clear();

		clothingInInventory.sort(new InventoryClothingComparator());
		
		for (AbstractClothing clothing : clothingInInventory) {
			if (!clothingDuplicates.containsKey(clothing))
				clothingDuplicates.put(clothing, 1);
			else
				clothingDuplicates.put(clothing, clothingDuplicates.get(clothing)+1);
		}
	}
	
	public int getUniqueClothingCount() {
		return getMapOfDuplicateClothing().size();
	}
	
	public int getClothingCount(AbstractClothing clothing) {
		if (!clothingDuplicates.containsKey(clothing))
			return clothingInInventory.contains(clothing)?1:0;
		else
			return clothingDuplicates.get(clothing);
		
	}
	
	public int getClothingCount() {
		return clothingInInventory.size();
	}
	
	public AbstractClothing getClothing(int index) {
		return clothingInInventory.get(index);
	}
	
	public boolean removeClothing(AbstractClothing clothing) {
		if(clothingInInventory.remove(clothing)) {
			recalculateMapOfDuplicateClothing();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Add an item to this inventory.
	 * 
	 * @return true if added, false if inventory was full.
	 */
	
	public boolean addClothing(AbstractClothing clothing) {
		if (!isInventoryFull() || hasClothing(clothing)) {
			clothingInInventory.add(clothing);
			recalculateMapOfDuplicateClothing();
			return true;
		} else
			return false;
	}
	
	public boolean hasClothing(AbstractClothing clothing) {
		return clothingInInventory.contains(clothing);
	}
	
	public boolean dropClothing(AbstractClothing clothing, Vector2i location) {
		if (clothingInInventory.contains(clothing)) {
			Main.game.getActiveWorld().getCell(location).getInventory().addClothing(clothing);
			clothingInInventory.remove(clothing);
			recalculateMapOfDuplicateClothing();
			return true;
		}

		return false;
	}
	
	public void cleanAllClothing() {
		for (AbstractClothing c : clothingInInventory)
			c.setDirty(false);
	}
	
	public List<AbstractClothing> getClothingCurrentlyEquipped() {
		return clothingCurrentlyEquipped;
	}

	/**
	 * @return clothing in the slot specified. Returns null if no clothing in
	 *         that slot.
	 */
	
	public AbstractClothing getClothingInSlot(InventorySlot invSlot) {
		AbstractClothing clothingInSlot = null;
		for (AbstractClothing clothing : clothingCurrentlyEquipped)
			if (clothing.getClothingType().getSlot() == invSlot) {
				clothingInSlot = clothing;
				break;
			}
		return clothingInSlot;
	}

	/**
	 * @return The number of clothes being worn that belong to the specified
	 *         ClothingSet.
	 */
	public int getClothingSetCount(ClothingSet clothingSet) {
		return clothingSetCount.get(clothingSet);
	}

	public int getClothingSetCount(ClothingSet clothingSet, int increment) {
		return clothingSetCount.get(clothingSet);
	}
	
	// Lasciate ogne speranza, voi ch'intrate //

	private StringBuilder tempSB;

	public String calculateClothingPostTransformation(GameCharacter character) {
		tempSB = new StringBuilder();
		List<AbstractClothing> clothingToRemove = new ArrayList<>();
		for (AbstractClothing c : clothingCurrentlyEquipped){
			
			// Race:
			if (ClothingType.slotBlockedByRace(character, c.getClothingType().getSlot()) != null) {
				transformationIncompatible(character, c, clothingToRemove, ClothingType.getCannotBeWornDescription(character, c.getClothingType().getSlot()));
				
			// Piercings:
			} else if(c.getClothingType().getSlot()==InventorySlot.PIERCING_EAR && !character.isPiercedEar()){
				transformationIncompatible(character, c, clothingToRemove, "Your ears are no longer pierced, so you can't wear the "+c.getName()+"!");
			
			} else if(c.getClothingType().getSlot()==InventorySlot.PIERCING_LIP && !character.isPiercedLip()){
				transformationIncompatible(character, c, clothingToRemove, "Your lips are no longer pierced, so you can't wear the "+c.getName()+"!");
				
			} else if(c.getClothingType().getSlot()==InventorySlot.PIERCING_NIPPLE && !character.isPiercedNipple()){
				transformationIncompatible(character, c, clothingToRemove, "Your nipples are no longer pierced, so you can't wear the "+c.getName()+"!");
				
			} else if(c.getClothingType().getSlot()==InventorySlot.PIERCING_NOSE && !character.isPiercedNose()){
				transformationIncompatible(character, c, clothingToRemove, "Your nose is no longer pierced, so you can't wear the "+c.getName()+"!");
				
			} else if(c.getClothingType().getSlot()==InventorySlot.PIERCING_PENIS && !character.isPiercedPenis()){
				if(character.getPenisType()==PenisType.NONE)
					transformationIncompatible(character, c, clothingToRemove, "You no longer have a penis, so you can't wear the "+c.getName()+"!");
				else
					transformationIncompatible(character, c, clothingToRemove, "Your penis is no longer pierced, so you can't wear the "+c.getName()+"!");
				
			} else if(c.getClothingType().getSlot()==InventorySlot.PIERCING_STOMACH && !character.isPiercedNavel()){
				transformationIncompatible(character, c, clothingToRemove, "Your navel is no longer pierced, so you can't wear the "+c.getName()+"!");
				
			} else if(c.getClothingType().getSlot()==InventorySlot.PIERCING_TONGUE && !character.isPiercedTongue()){
				transformationIncompatible(character, c, clothingToRemove, "Your tongue is no longer pierced, so you can't wear the "+c.getName()+"!");
				
			} else if(c.getClothingType().getSlot()==InventorySlot.PIERCING_VAGINA && !character.isPiercedVagina()){
				if(character.getVaginaType()==VaginaType.NONE)
					transformationIncompatible(character, c, clothingToRemove, "You no longer have a vagina, so you can't wear the "+c.getName()+"!");
				else
					transformationIncompatible(character, c, clothingToRemove, "Your vagina is no longer pierced, so you can't wear the "+c.getName()+"!");
			}
			
		}
		clothingCurrentlyEquipped.removeAll(clothingToRemove);

		return tempSB.toString();
	}
	private void transformationIncompatible(GameCharacter character, AbstractClothing c, List<AbstractClothing> clothingRemovalList, String description){
		if (tempSB.length() != 0)
			tempSB.append("</br></br>");
		tempSB.append("</br><span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>"+description+"</span>");
		if (isInventoryFull()) {
			Main.game.getActiveWorld().getCell(character.getLocation()).getInventory().addClothing(c);
			tempSB.append("</br>" + character.droppedItemText(c));
		} else {
			character.addClothing(c, false);
			tempSB.append("</br>" + character.addedItemToInventoryText(c));
		}
		clothingRemovalList.add(c);	
	}
	
	

	private StringBuilder equipTextSB = new StringBuilder();

	public String getEquipDescription() {
		return equipTextSB.toString();
	}

	private Set<AbstractClothing> incompatibleUnequippableClothing = new HashSet<>();
	private Set<AbstractClothing> incompatibleRemovableClothing = new HashSet<>();
	// Map of clothing that needs to be removed. If value is
	// DisplacementType.NONE, clothing will be fully removed.
	private Map<AbstractClothing, DisplacementType> clothingToRemove = new HashMap<>();

	/**
	 * Calculates if the character is able to remove or displace all blocking clothing in order to equip the supplied clothing.
	 */
	public boolean isAbleToEquip(AbstractClothing newClothing, boolean equipIfAble, boolean automaticClothingManagement, GameCharacter characterClothingOwner, GameCharacter characterClothingEquipper) {

		clothingToRemove.clear();
		equipTextSB.setLength(0);

		// //Can't equip stomach clothing if have three breast rows:
		// if(newClothing.getClothingType().getSlot()==InventorySlot.STOMACH &&
		// Main.game.getPlayer().getBreastRows()==3){
		// equipDescription = "Your extra breasts get in the way of equipping
		// this item!";
		// return false;
		// }

		// Check to see if any of the character's body parts are blocking equipping this item:
		if (ClothingType.slotBlockedByRace(characterClothingOwner, newClothing.getClothingType().getSlot()) != null) {
			equipTextSB.append("<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>" + ClothingType.getCannotBeWornDescription(characterClothingOwner, newClothing.getClothingType().getSlot()) + "</span>");
			return false;
		}
		
		if (!newClothing.getClothingType().isCanBeEquipped(characterClothingOwner)) {
			equipTextSB.append("<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>" + newClothing.getClothingType().getCannotBeEquippedText(characterClothingOwner) + "</span>");
			return false;
		}

		// Can't equip if InventorySlot is taken by a sealed piece of clothing:
		if (getClothingInSlot(newClothing.getClothingType().getSlot()) != null) {
			if(getClothingInSlot(newClothing.getClothingType().getSlot()).isSealed()) {
				equipTextSB.append("You can't equip the "+newClothing.getName()+", as your <b style='color:" + Colour.SEALED.toWebHexString() + ";'>sealed</b> "
							+ getClothingInSlot(newClothing.getClothingType().getSlot()).getName() + " can't be removed!");
				return false;
			}
		}
		
		// Can't equip piercings if that body part isn't pierced:
		if (!characterClothingOwner.isPiercedEar() && newClothing.getClothingType().getSlot() == InventorySlot.PIERCING_EAR) {
			equipTextSB.append("Your ears need to be pierced before you can wear this!");
			return false;
		}
		if (!characterClothingOwner.isPiercedNose() && newClothing.getClothingType().getSlot() == InventorySlot.PIERCING_NOSE) {
			equipTextSB.append("Your nose needs to be pierced before you can wear this!");
			return false;
		}
		if (!characterClothingOwner.isPiercedLip() && newClothing.getClothingType().getSlot() == InventorySlot.PIERCING_LIP) {
			equipTextSB.append("Your lip needs to be pierced before you can wear this!");
			return false;
		}
		if (!characterClothingOwner.isPiercedTongue() && newClothing.getClothingType().getSlot() == InventorySlot.PIERCING_TONGUE) {
			equipTextSB.append("Your tongue needs to be pierced before you can wear this!");
			return false;
		}
		if (!characterClothingOwner.isPiercedNavel() && newClothing.getClothingType().getSlot() == InventorySlot.PIERCING_STOMACH) {
			equipTextSB.append("Your navel needs to be pierced before you can wear this!");
			return false;
		}
		if (!characterClothingOwner.isPiercedNipple() && newClothing.getClothingType().getSlot() == InventorySlot.PIERCING_NIPPLE) {
			equipTextSB.append("Your nipples need to be pierced before you can wear this!");
			return false;
		}
		if (!characterClothingOwner.isPiercedVagina() && newClothing.getClothingType().getSlot() == InventorySlot.PIERCING_VAGINA) {
			equipTextSB.append("Your clit needs to be pierced before you can wear this!");
			return false;
		}
		if (!characterClothingOwner.isPiercedPenis() && newClothing.getClothingType().getSlot() == InventorySlot.PIERCING_PENIS) {
			equipTextSB.append("Your cock needs to be pierced before you can wear this!");
			return false;
		}

		// Check to see if any equipped clothing is incompatible with newClothing:
		incompatibleUnequippableClothing.clear();
		incompatibleRemovableClothing.clear();
		for (InventorySlot slot : newClothing.getClothingType().getIncompatibleSlots()) {
			if (getClothingInSlot(slot) != null) {
				if (!isAbleToUnequip(getClothingInSlot(slot), false, automaticClothingManagement, characterClothingOwner, characterClothingEquipper, true))
					incompatibleUnequippableClothing.add(getClothingInSlot(slot));
				else {
					clothingToRemove.put(getClothingInSlot(slot), DisplacementType.REMOVE_OR_EQUIP);
					incompatibleRemovableClothing.add(getClothingInSlot(slot));
				}
			}
		}

		// Check to see if newClothing is incompatible with any equipped clothing:
		for (AbstractClothing clothing : clothingCurrentlyEquipped) {
			if (clothing.getClothingType().getIncompatibleSlots().contains(newClothing.getClothingType().getSlot())) {
				if (!isAbleToUnequip(clothing, false, automaticClothingManagement, characterClothingOwner, characterClothingEquipper, true)) {
					incompatibleUnequippableClothing.add(clothing);
				} else {
					clothingToRemove.put(clothing, DisplacementType.REMOVE_OR_EQUIP);
					incompatibleRemovableClothing.add(clothing);
				}
			}
		}

		// There is at least one piece of clothing that is incompatible with newClothing, and that clothing cannot be removed.
		if (incompatibleUnequippableClothing.size() != 0) {
			for(AbstractClothing c : incompatibleUnequippableClothing) {
				if(c.isSealed())
					equipTextSB.append("You can't equip the " + newClothing.getName() + " because your <b style='color:" + Colour.SEALED.toWebHexString() + ";'>sealed</b> "
							+c.getName()+ " "+(c.getClothingType().isPlural()?"are":"is")+" in the way.");
			}
			return false;
		}

		// Check for access needed:
		for (BlockedParts bp : newClothing.getClothingType().getBlockedPartsList()) {

			if (bp.displacementType == DisplacementType.REMOVE_OR_EQUIP) { // Check for all blocking types that affect REMOVE_OR_EQUIP. (As we are trying to equip this item of clothing.)
				if (bp.clothingAccessRequired == null) {
					break; // This clothing doesn't need any access in order to be equipped, so just carry on.

				} else {
					// This clothing has access requirements in order to be equipped. Check each piece of equipped clothing to see if it's blocking the access required:
					for (AbstractClothing equippedClothing : clothingCurrentlyEquipped) {
						for (BlockedParts bpEquipped : equippedClothing.getClothingType().getBlockedPartsList()) {
							for (ClothingAccess caBlocked : bpEquipped.clothingAccessBlocked) { // For each clothing access that is blocked by this equipped clothing, check to see if this clothing access is required by the new clothing:
								
								if (bp.clothingAccessRequired.contains(caBlocked) && !equippedClothing.getDisplacedList().contains(bpEquipped.displacementType)) { // Blocking clothing found which hasn't been displaced:
									
									if (!clothingToRemove.containsKey(equippedClothing)) { // This clothing has not already been marked for removal:
										if(automaticClothingManagement && isAbleToBeDisplaced(equippedClothing, bpEquipped.displacementType, false, automaticClothingManagement, characterClothingOwner, characterClothingEquipper, true)) {
											clothingToRemove.put(equippedClothing, bpEquipped.displacementType);
										} else {
											equipTextSB.append("Your <b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>" + equippedClothing.getName() + "</b> "
													+ (equippedClothing.getClothingType().isPlural() ? "are" : "is") + " preventing you from being able to equip the "+newClothing.getName()+"!");
											blockingClothing = equippedClothing;
											return false;
										}

									} else {
										if (isAbleToUnequip(equippedClothing, false, automaticClothingManagement, characterClothingOwner, characterClothingEquipper, true)) { // Can be removed:
											clothingToRemove.put(equippedClothing, DisplacementType.REMOVE_OR_EQUIP);
										} else {
											if(equippedClothing.isSealed()) {
												equipTextSB.append("You can't equip the " + newClothing.getName() + " because your <b style='color:" + Colour.SEALED.toWebHexString() + ";'>sealed</b> "
														+equippedClothing.getName()+ " "+(equippedClothing.getClothingType().isPlural()?"are":"is")+" in the way.");
											} else {
												equipTextSB.append("You can't equip the " + newClothing.getName() + " because your "
														+equippedClothing.getName()+ " "+(equippedClothing.getClothingType().isPlural()?"are":"is")+" in the way.");
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

				if (getClothingInSlot(newClothing.getClothingType().getSlot()) != null) {
					AbstractClothing equippedClothing = getClothingInSlot(newClothing.getClothingType().getSlot());
					
					if (isAbleToUnequip(equippedClothing, false, automaticClothingManagement, characterClothingOwner, characterClothingEquipper, true)) { // Can be removed:
						clothingToRemove.put(equippedClothing, DisplacementType.REMOVE_OR_EQUIP);
					} else {
						if(equippedClothing.isSealed()) {
							equipTextSB.append("You can't equip the " + newClothing.getName() + " because your <b style='color:" + Colour.SEALED.toWebHexString() + ";'>sealed</b> "
									+equippedClothing.getName()+ " "+(equippedClothing.getClothingType().isPlural()?"are":"is")+" in the way.");
						} else {
							equipTextSB.append("You can't equip the " + newClothing.getName() + " because your "
									+equippedClothing.getName()+ " "+(equippedClothing.getClothingType().isPlural()?"are":"is")+" in the way.");
						}
						blockingClothing = equippedClothing;
						return false;
					}
				}
				
				
				// Sort clothing to remove in zLayer order(so you take off your
				// shirt before removing bra etc.):
				List<AbstractClothing> tempClothingList = new ArrayList<>();
				for (AbstractClothing cl : clothingToRemove.keySet())
					if (!tempClothingList.contains(cl))
						tempClothingList.add(cl);
				for (AbstractClothing cl : incompatibleRemovableClothing)
					if (!tempClothingList.contains(cl))
						tempClothingList.add(cl);

				// tempClothingList.addAll(clothingToRemove.keySet());
				// tempClothingList.addAll(incompatibleRemovableClothing);
//				if (getClothingInSlot(newClothing.getClothingType().getSlot()) != null && !tempClothingList.contains(getClothingInSlot(newClothing.getClothingType().getSlot())))
//					tempClothingList.add(getClothingInSlot(newClothing.getClothingType().getSlot()));

				tempClothingList.sort(new ClothingZLayerComparator());

				List<AbstractClothing> clothingToBeReplaced = new ArrayList<>();
				// Description of each clothing item that is removed/displaced:
				for (AbstractClothing c : tempClothingList) {
					if (!incompatibleRemovableClothing.contains(c) && c != getClothingInSlot(newClothing.getClothingType().getSlot())) {
						clothingToBeReplaced.add(c);
					}

					equipTextSB.append((equipTextSB.length() == 0 ? "" : "</br>")
								+ (clothingToRemove.get(c) == DisplacementType.REMOVE_OR_EQUIP
									? c.onUnequipText(characterClothingOwner, characterClothingEquipper, false)// (Main.game.isInSex()?Sex.isSubResisting():false))
									: "You " + clothingToRemove.get(c).getDescription() + " your " + c.getName() + "."));
				}

				// Remove all clothing that is incompatible with newClothing using the owner's accessor method.
				for (AbstractClothing c : incompatibleRemovableClothing) {
					if (!characterClothingOwner.isInventoryFull())
						equipTextSB.append("</br>" + characterClothingOwner.addedItemToInventoryText(c));
					else
						equipTextSB.append("</br>" + characterClothingOwner.droppedItemText(c));
					characterClothingOwner.unequipClothingIntoInventory(c, true, characterClothingEquipper);
				}

				// Clear the new clothing's displacement list as a precaution:
				newClothing.getDisplacedList().clear();

				// Remove the old clothing in this slot using the owner's accessor method:
				if (getClothingInSlot(newClothing.getClothingType().getSlot()) != null) {
					if (!characterClothingOwner.isInventoryFull())
						equipTextSB.append("</br>" + characterClothingOwner.addedItemToInventoryText(getClothingInSlot(newClothing.getClothingType().getSlot())));
					else
						equipTextSB.append("</br>" + characterClothingOwner.droppedItemText(getClothingInSlot(newClothing.getClothingType().getSlot())));
					characterClothingOwner.unequipClothingIntoInventory(getClothingInSlot(newClothing.getClothingType().getSlot()), true, characterClothingEquipper);
				}

				// Actually equip the newClothing:
				clothingCurrentlyEquipped.add(newClothing);
				// newClothing.getClothingType().setColourShade(newClothing.getColourShade());

				equipTextSB.append((equipTextSB.length() == 0 ? "" : "</br>") + newClothing.onEquipApplyEffects(characterClothingOwner, characterClothingEquipper, false));// (Main.game.isInSex()?Sex.isSubResisting():false)));

				clothingToBeReplaced.sort(new ReverseClothingZLayerComparator());
				if (!clothingToBeReplaced.isEmpty())// clothingCountToBeReplaced-incompatibleUnequippableClothing.size()>0)
					equipTextSB.append("</br>You replace your " + Util.clothesToStringList(clothingToBeReplaced) + ".");

				// Check for clothing sets:
				if (newClothing.getClothingType().getClothingSet() != null) {
					if (clothingSetCount.get(newClothing.getClothingType().getClothingSet()) == null)
						clothingSetCount.put(newClothing.getClothingType().getClothingSet(), 1);
					else {
						clothingSetCount.put(newClothing.getClothingType().getClothingSet(), clothingSetCount.get(newClothing.getClothingType().getClothingSet()) + 1);
					}
				}

			}

			clothingCurrentlyEquipped.sort(new AbstractClothingRarityComparator());

		} else {
			equipTextSB.append(Util.capitaliseSentence("Before" + newClothing.getClothingType().getDeterminer()) + " " + newClothing.getName() + " is able to be equipped, " + Util.clothesToStringList(clothingToRemove.keySet()) + " need"
					+ (clothingToRemove.size() > 1 ? "" : "s") + " to be removed.");
		}

		return true;
	}

	public boolean isAbleToUnequip(AbstractClothing clothing, boolean unequipIfAble, boolean automaticClothingManagement, GameCharacter characterClothingOwner, GameCharacter characterRemovingClothing) {
		return isAbleToUnequip(clothing, unequipIfAble, automaticClothingManagement, characterClothingOwner, characterRemovingClothing, false);
	}

	private boolean isAbleToUnequip(AbstractClothing clothing, boolean unequipIfAble, boolean automaticClothingManagement, GameCharacter characterClothingOwner, GameCharacter characterRemovingClothing, boolean continuingIsAbleToEquip) {

		if (!continuingIsAbleToEquip) {
			clothingToRemove.clear();
			equipTextSB.setLength(0);
		}

		if (clothing.isSealed()) {
			equipTextSB.append("Your " + clothing.getName() + " can't be removed because "+(clothing.getClothingType().isPlural()?"they are":"it is")+" <b style='color:" + Colour.SEALED.toWebHexString() + ";'>sealed</b>!");
			blockingClothing = clothing;
			return false;
		} else if (!continuingIsAbleToEquip) {
			clothingToRemove.put(clothing, DisplacementType.REMOVE_OR_EQUIP);
		}
		
		// Check for access needed: TODO check this works
		for (BlockedParts bp : clothing.getClothingType().getBlockedPartsList()) {

			// Keep iterating through until until we find the BlockedParts that corresponds to equipping (if not found, carry on, as this clothing doesn't need any access in order to be equipped):
			if (bp.displacementType == DisplacementType.REMOVE_OR_EQUIP)
				if (bp.clothingAccessRequired == null) {
					break; // This clothing doesn't need any access in order to be equipped, so just carry on.

				} else {
					// This clothing has access requirements in order to be equipped. Check each piece of equipped clothing to see if it's blocking the access required:
					for (AbstractClothing equippedClothing : clothingCurrentlyEquipped) {
						if (equippedClothing != clothing)
							for (BlockedParts bpEquipped : equippedClothing.getClothingType().getBlockedPartsList()) {
								for (ClothingAccess caBlocked : bpEquipped.clothingAccessBlocked) {
									if (bp.clothingAccessRequired.contains(caBlocked) && !equippedClothing.getDisplacedList().contains(bpEquipped.displacementType)) {
										if (bpEquipped.displacementType != DisplacementType.REMOVE_OR_EQUIP) { // Can be displaced:
											// If clothingToRemove already contains this clothing, it's just going to be easier to remove the clothing fully than perform multiple displacements:
											if (!clothingToRemove.containsKey(equippedClothing))
												if (!equippedClothing.getDisplacedList().contains(bpEquipped.displacementType)){ // Not already displaced:
													if(automaticClothingManagement)
														clothingToRemove.put(equippedClothing, bpEquipped.displacementType);
													else{
														equipTextSB.append("Your <b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>" + equippedClothing.getName() + "</b> "
																+ (equippedClothing.getClothingType().isPlural() ? "are" : "is") + " preventing you from being able to unequip your "+clothing.getName()+"!");
														
														blockingClothing = equippedClothing;
														return false;
													}
												}

										} else {
											if (isAbleToUnequip(equippedClothing, false, automaticClothingManagement, characterClothingOwner, characterRemovingClothing, true)) // Can  be removed:
												clothingToRemove.put(equippedClothing, DisplacementType.REMOVE_OR_EQUIP);
											else {
												equipTextSB.append("</br>Your " + clothing.getName() + " can't be unequipped because your " + equippedClothing.getName() + " "
														+ (equippedClothing.getClothingType().isPlural() ? "are" : "is") + " in the way.");
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

		if (continuingIsAbleToEquip)
			return true;

		if (!automaticClothingManagement && clothingToRemove.size() != 1) {
			equipTextSB.append("Before your " + clothing.getName() + " "+(clothing.getClothingType().isPlural()?"are":"is")+" able to be removed, " + Util.clothesToStringList(clothingToRemove.keySet()) + " need"
					+ (clothingToRemove.size() > 1 ? "" : "s") + " to be removed.");
			
			for(AbstractClothing c : clothingToRemove.keySet())
				if(c!=clothing){
					blockingClothing=c;
					break;
				}
			
			return false;
		}

		// clothing can be removed!

		// If you want to unequip this clothing now:
		if (unequipIfAble) {
			// Sort clothing to remove in zLayer order(so you take off your
			// shirt before removing bra etc.):
			List<AbstractClothing> tempClothingList = new ArrayList<>();
			tempClothingList.addAll(clothingToRemove.keySet());
			tempClothingList.sort(new ClothingZLayerComparator());

			// TODO Take in removerCharacter and targetCharacter:
			// Rachel removes your panties. You pull down Rachel's panties

			// Description of each clothing item that is removed/displaced:
			for (AbstractClothing c : tempClothingList) {
				equipTextSB.append((equipTextSB.length() == 0 ? "" : "</br>") + (clothingToRemove.get(c) == DisplacementType.REMOVE_OR_EQUIP
						? (c == clothing ? c.onUnequipApplyEffects(characterClothingOwner, characterRemovingClothing, false)// (Main.game.isInSex()?Sex.isSubResisting():false))
								: c.onUnequipText(characterClothingOwner, characterRemovingClothing, false))// (Main.game.isInSex()?Sex.isSubResisting():false)))
						: c.getClothingType().displaceText(characterClothingOwner, characterRemovingClothing, clothingToRemove.get(c), false)));// (Main.game.isInSex()?Sex.isSubResisting():false))));
				// if(c==clothing)
				// unequipTextSB.append("</br>"+(isInventoryFull()?characterClothingOwner.droppedItemText(clothing):characterClothingOwner.addedItemToInventoryText(clothing)));
			}

			// Actually unequip the clothing:
			clothingCurrentlyEquipped.remove(clothing);

			// If it was displaced, clear it's displacements:
			clothing.getDisplacedList().clear();

			List<AbstractClothing> clothingToBeReplaced = new ArrayList<>();
			clothingToBeReplaced.addAll(clothingToRemove.keySet());
			clothingToBeReplaced.remove(clothing);
			clothingToBeReplaced.sort(new ReverseClothingZLayerComparator());

			if (!clothingToBeReplaced.isEmpty() && !continuingIsAbleToEquip)
				equipTextSB.append("</br>You replace your " + Util.clothesToStringList(clothingToBeReplaced) + ".");

			// Check for clothing sets:
			if (clothing.getClothingType().getClothingSet() != null) {
				clothingSetCount.put(clothing.getClothingType().getClothingSet(), clothingSetCount.get(clothing.getClothingType().getClothingSet()) - 1);
			}
			
			clothingCurrentlyEquipped.sort(new AbstractClothingRarityComparator());

		}

		return true;
	}

	private StringBuilder unableToDisplaceText;

	public String getDisplaceDescription() {
		return unableToDisplaceText.toString();
	}
	
	public boolean isAbleToBeDisplaced(AbstractClothing clothing, DisplacementType dt, boolean displaceIfAble, boolean automaticClothingManagement, GameCharacter characterClothingOwner, GameCharacter characterRemovingClothing){
		return isAbleToBeDisplaced(clothing, dt, displaceIfAble, automaticClothingManagement, characterClothingOwner, characterRemovingClothing, false);
	}
	
	/**
	 * If pass in DisplacementType.REMOVE_OR_EQUIP, returns isAbleToUnequip().
	 */
	public boolean isAbleToBeDisplaced(AbstractClothing clothing, DisplacementType dt, boolean displaceIfAble, boolean automaticClothingManagement,
			GameCharacter characterClothingOwner, GameCharacter characterRemovingClothing, boolean continuingIsAbleToEquip) {
		
		if (dt == DisplacementType.REMOVE_OR_EQUIP) {
			return isAbleToUnequip(clothing, displaceIfAble, automaticClothingManagement, characterClothingOwner, characterRemovingClothing, continuingIsAbleToEquip);
		}
		
		if (!continuingIsAbleToEquip) {
			clothingToRemove.clear();
			equipTextSB.setLength(0);
		}

		boolean displacementTypeFound = false;
		// Check for access needed: TODO check this works
		for (BlockedParts bp : clothing.getClothingType().getBlockedPartsList()) {

			// Keep iterating through until until we find the displacementType:
			if (bp.displacementType == dt) {
				displacementTypeFound = true;

				if (bp.clothingAccessRequired == null) {
					break; // This clothing doesn't need any access in order to
							// be displaced in this manner, so just carry on.

				} else {
					// This clothing has access requirements in order to be displaced. Check each piece of equipped clothing to see if it's blocking the access required:
					for (AbstractClothing equippedClothing : clothingCurrentlyEquipped) {
						if (equippedClothing != clothing)
							for (BlockedParts bpEquipped : equippedClothing.getClothingType().getBlockedPartsList()) {
								for (ClothingAccess caBlocked : bpEquipped.clothingAccessBlocked) {

									if (bp.clothingAccessRequired.contains(caBlocked) && !equippedClothing.getDisplacedList().contains(bpEquipped.displacementType)) {
										
										if (bpEquipped.displacementType != DisplacementType.REMOVE_OR_EQUIP && !clothingToRemove.containsKey(equippedClothing)) { // Can be displaced:
											if (!equippedClothing.getDisplacedList().contains(bpEquipped.displacementType)){ // Not already displaced:
												if(automaticClothingManagement)
													clothingToRemove.put(equippedClothing, bpEquipped.displacementType);
												else{
													unableToDisplaceText = new StringBuilder(Util.capitaliseSentence(clothing.getClothingType().getDeterminer()) + " " + clothing.getName() + " can't be displaced because "
															+ equippedClothing.getClothingType().getDeterminer() + " " + equippedClothing.getName() + " " + (equippedClothing.getClothingType().isPlural() ? "are" : "is") + " in the way.");
													blockingClothing = equippedClothing;
													return false;
												}
											}

										} else {
											if (isAbleToUnequip(equippedClothing, false, automaticClothingManagement, characterClothingOwner, characterRemovingClothing, true)) // Can be removed:
												clothingToRemove.put(equippedClothing, DisplacementType.REMOVE_OR_EQUIP);
											else {
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
				unableToDisplaceText.append((unableToDisplaceText.length() == 0 ? "" : "</br>") + (clothingToRemove.get(c) == DisplacementType.REMOVE_OR_EQUIP ? c.onUnequipText(characterClothingOwner, characterRemovingClothing, false)// (Main.game.isInSex()?Sex.isSubResisting():false))
						: c.getClothingType().displaceText(characterClothingOwner, characterRemovingClothing, clothingToRemove.get(c), false)));// (Main.game.isInSex()?Sex.isSubResisting():false))));
			}

			unableToDisplaceText.append(
					(unableToDisplaceText.length() == 0 ? "" : "</br><span style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>") + clothing.getClothingType().displaceText(characterClothingOwner, characterRemovingClothing, dt, false)// (Main.game.isInSex()?Sex.isSubResisting():false))
							+ "</span>");

			List<AbstractClothing> replaceClothingList = new ArrayList<>();
			replaceClothingList.addAll(clothingToRemove.keySet());
			replaceClothingList.remove(clothing);
			replaceClothingList.sort(new ReverseClothingZLayerComparator());
			if (!replaceClothingList.isEmpty())
				unableToDisplaceText.append("</br>You replace your " + Util.clothesToStringList(replaceClothingList) + ".");

			return true;
		}
		unableToDisplaceText = new StringBuilder(Util.capitaliseSentence(clothing.getClothingType().getDeterminer()) + " " + clothing.getName() + " is able to be displaced.");
		return true;
	}

	private StringBuilder unableToReplaceText;

	
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
		for (BlockedParts bp : clothing.getClothingType().getBlockedPartsList()) {

			// Keep iterating through until until we find the displacementType:
			if (bp.displacementType == dt) {
				displacementTypeFound = true;

				if (bp.clothingAccessRequired == null) {
					break; // This clothing doesn't need any access in order to
							// be displaced in this manner, so just carry on.

				} else {
					// This clothing has access requirements in order to be displaced. Check each piece of equipped clothing to see if it's blocking the access required:
					for (AbstractClothing equippedClothing : clothingCurrentlyEquipped) {
						for (BlockedParts bpEquipped : equippedClothing.getClothingType().getBlockedPartsList()) {
							for (ClothingAccess caBlocked : bpEquipped.clothingAccessBlocked) {

								if (bp.clothingAccessRequired.contains(caBlocked) && !equippedClothing.getDisplacedList().contains(bpEquipped.displacementType)) {
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
				unableToReplaceText.append((unableToReplaceText.length() == 0 ? "" : "</br>") + (clothingToRemove.get(c) == DisplacementType.REMOVE_OR_EQUIP
						? (c == clothing ? c.onUnequipApplyEffects(characterClothingOwner, characterRemovingClothing, false)// (Main.game.isInSex()?Sex.isSubResisting():false))
								: c.onUnequipText(characterClothingOwner, characterRemovingClothing, false))// (Main.game.isInSex()?Sex.isSubResisting():false)))
						: c.getClothingType().displaceText(characterClothingOwner, characterRemovingClothing, clothingToRemove.get(c), false)));// (Main.game.isInSex()?Sex.isSubResisting():false))));

			unableToReplaceText
					.append((unableToReplaceText.length() == 0 ? "" : "</br><span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>") + clothing.getClothingType().replaceText(characterClothingOwner, characterRemovingClothing, dt, false)// (Main.game.isInSex()?Sex.isSubResisting():false))
							+ "</span>");

			List<AbstractClothing> replaceClothingList = new ArrayList<>();
			replaceClothingList.addAll(clothingToRemove.keySet());
			replaceClothingList.sort(new ReverseClothingZLayerComparator());
			if (!replaceClothingList.isEmpty())
				unableToReplaceText.append("</br>You replace your " + Util.clothesToStringList(replaceClothingList) + ".");

			return true;
		}

		unableToReplaceText = new StringBuilder(Util.capitaliseSentence(clothing.getClothingType().getDeterminer()) + " " + clothing.getName() + " is able to be replaced.");
		return true;
	}

	/**
	 * @param area
	 *            Area you want to get to.
	 * @param byRemovingClothing
	 *            Allow consideration of clothing removal or not.
	 * @return True if can access slot. (if byRemovingClothing is true, then it
	 *         tells you if you are able to get to the slot by removing
	 *         clothing, not that it is available right now).
	 */
	public boolean isAbleToAccessCoverableArea(CoverableArea area, boolean byRemovingClothing) {

		// For every piece of equipped clothing, if it's blocking the coverable
		// area, see if it can be displaced or removed. If it can't, return
		// false.
		for (AbstractClothing clothing : clothingCurrentlyEquipped) {
			for (BlockedParts bp : clothing.getClothingType().getBlockedPartsList()) {
				if (bp.blockedBodyParts.contains(area)) {// If this clothing is blocking the area you are trying to access:
					if (!clothing.getDisplacedList().contains(bp.displacementType)) { // If the clothing  hasn't been displaced:
						if (byRemovingClothing) {
							if (bp.displacementType == DisplacementType.REMOVE_OR_EQUIP) {
								if (!isAbleToUnequip(clothing, false, byRemovingClothing, null, null))// If the clothing can't be removed from this area:
									return false;
							} else {
								if (!isAbleToBeDisplaced(clothing, bp.displacementType, false, byRemovingClothing, null, null))// If the clothing can't be displaced from this area:
									return false;
							}
						} else {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	
	public SimpleEntry<AbstractClothing, DisplacementType> getNextClothingToRemoveForCoverableAreaAccess(CoverableArea coverableArea) {

		AbstractClothing clothingToRemove = null;
		DisplacementType displacement = null;

		for (AbstractClothing clothing : clothingCurrentlyEquipped) {
			for (BlockedParts bp : clothing.getClothingType().getBlockedPartsList())
				if (bp.blockedBodyParts.contains(coverableArea) && !clothing.getDisplacedList().contains(bp.displacementType)) {
					// this clothing is blocking the part we want access to, so
					// make that our starting point:
					clothingToRemove = clothing;
					displacement = bp.displacementType;
					break;
				}
		}

		if (clothingToRemove == null)
			throw new IllegalArgumentException("There is no clothing covering this part!");

		boolean finished = false;

		while (!finished) {

			finished = true;

			outerloop: for (BlockedParts bp : clothingToRemove.getClothingType().getBlockedPartsList())
				if (bp.displacementType == displacement) {
					for (ClothingAccess ca : bp.clothingAccessRequired)
						for (AbstractClothing clothing : clothingCurrentlyEquipped) {
							if (clothing != clothingToRemove)
								for (BlockedParts bpIterated : clothing.getClothingType().getBlockedPartsList()) {
									if (bpIterated.clothingAccessBlocked.contains(ca) && !clothing.getDisplacedList().contains(bpIterated.displacementType)) {
										// this clothing is blocking the clothing we wanted to displace, so now we re-start by wanting to  displace this new clothing:
										clothingToRemove = clothing;
										displacement = bpIterated.displacementType;
										finished = false;
										break outerloop;
									}
								}
						}
				}
		}

		return new SimpleEntry<AbstractClothing, DisplacementType>(clothingToRemove, displacement);
	}

	public boolean isCoverableAreaExposed(CoverableArea area) {
		return isAbleToAccessCoverableArea(area, false);
	}

	public int getClothingAverageFemininity() {
		if(clothingCurrentlyEquipped.size()==0)
			return 50;
		
		int average = 50, count = 1;
		for (AbstractClothing c : clothingCurrentlyEquipped) {
			if (c.getClothingType().getFemininityRestriction() == Femininity.FEMININE) {
				average += 75;
				count++;
			} else if (c.getClothingType().getFemininityRestriction() == Femininity.MASCULINE) {
				average += 25;
				count++;
			} else {
				average += 50;
				count++;
			}
		}

		average /= count;
		
		return (int) average;
	}

	/**
	 * The lowest piece of clothing that is blocking this slot.</br>
	 * <b>Note:</b> This takes into account displacement, so, for example, if your panties are displaced, and are the only piece of clothing otherwise blocking your vagina,
	 *  this method will return null for getLowestZLayerCoverableArea(CoverableArea.VAGINA)!
	 */
	public AbstractClothing getLowestZLayerCoverableArea(CoverableArea area) {
		AbstractClothing c = null;

		// Iterate through currently worn clothing:
		for (AbstractClothing clothing : clothingCurrentlyEquipped) {
			// If this clothing is blocking the slot you are trying to access:
			for (BlockedParts bp : clothing.getClothingType().getBlockedPartsList())
				if (bp.blockedBodyParts.contains(area) && !clothing.getDisplacedList().contains(bp.displacementType)) {
					// Replace if ZLayer is lower than previous found clothing:
					if (c == null)
						c = clothing;
					else if (clothing.getClothingType().getzLayer() < c.getClothingType().getzLayer())
						c = clothing;
				}
		}

		return c;
	}
	
	/**
	 * The highest piece of clothing that is blocking this slot.</br>
	 * <b>Note:</b> This takes into account displacement, so, for example, if your yoga pants are displaced, and are revealing your panties,
	 *  this method will return panties for getLowestZLayerCoverableArea(CoverableArea.VAGINA)!
	 */
	public AbstractClothing getHighestZLayerCoverableArea(CoverableArea area) {
		AbstractClothing c = null;

		// Iterate through currently worn clothing:
		for (AbstractClothing clothing : clothingCurrentlyEquipped) {
			// If this clothing is blocking the slot you are trying to access:
			for (BlockedParts bp : clothing.getClothingType().getBlockedPartsList())
				if (bp.blockedBodyParts.contains(area) && !clothing.getDisplacedList().contains(bp.displacementType)) {
					// Replace if ZLayer is higher than previous found clothing:
					if (c == null)
						c = clothing;
					else if (clothing.getClothingType().getzLayer() > c.getClothingType().getzLayer())
						c = clothing;
				}
		}

		return c;
	}

	public AbstractClothing getBlockingClothing() {
		return blockingClothing;
	}
	
	public boolean isSlotIncompatible(InventorySlot slot) {
		for(AbstractClothing ct : clothingCurrentlyEquipped) {
			for (InventorySlot incompatibleSlot : ct.getClothingType().getIncompatibleSlots()) {
				if(incompatibleSlot == slot)
					return true;
			}
		}
		return false;
	}
}
