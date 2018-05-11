package com.lilithsthrone.game.inventory.enchanting;

import java.util.List;

import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemEffect;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.2.5
 * @version 0.2.5
 * @author Innoxia
 */
public class LoadedEnchantment {
	
	private String name;
	private AbstractItemType itemType;
	private AbstractClothingType clothingType;
	private AbstractWeaponType weaponType;
	private List<ItemEffect> effects;
	
	public LoadedEnchantment(String name, AbstractItemType itemType, List<ItemEffect> effects) {
		this.name = name;
		this.itemType = itemType;
		this.clothingType = null;
		this.weaponType = null;
		this.effects = effects;
	}
	
	public LoadedEnchantment(String name, AbstractClothingType clothingType, List<ItemEffect> effects) {
		this.name = name;
		this.itemType = null;
		this.clothingType = clothingType;
		this.weaponType = null;
		this.effects = effects;
	}
	
	public LoadedEnchantment(String name, AbstractWeaponType weaponType, List<ItemEffect> effects) {
		this.name = name;
		this.itemType = null;
		this.clothingType = null;
		this.weaponType = weaponType;
		this.effects = effects;
	}

	public String getName() {
		return name;
	}
	
	public boolean isSuitableItemAvailable() {
		return getSuitableItem()!=null;
	}
	
	public AbstractCoreItem getSuitableItem() {
		if(itemType!=null) {
			for(AbstractItem item : Main.game.getPlayer().getAllItemsInInventory()) {
				if(item.getItemType().equals(itemType)) {
					return item;
				}
			}
			
		} else if(clothingType!=null) {
			for(AbstractClothing c :  Main.game.getPlayer().getAllClothingInInventory()) {
				if(c.getClothingType().equals(clothingType) && c.getEffects().isEmpty()) {
					return c;
				}
			}
			
		} else {
			for(AbstractWeapon w :  Main.game.getPlayer().getAllWeaponsInInventory()) {
				if(w.getWeaponType().equals(weaponType) && w.getEffects().isEmpty()) {
					return w;
				}
			}
		}
		
		return null;
	}

	public String getSVGString() {
		AbstractCoreItem item = getSuitableItem();
		
		if(itemType!=null) {
			if(item!=null) {
				return ((AbstractItem)item).getSVGString();
			}
			return itemType.getSVGString();
			
		} else if(clothingType!=null) {
			if(item!=null) {
				return ((AbstractClothing)item).getSVGString();
			}
			return clothingType.getSVGImage();
			
		} else {
			if(item!=null) {
				return ((AbstractWeapon)item).getSVGString();
			}
			return weaponType.getSVGString();
		}
	}
	
	public AbstractItemType getItemType() {
		return itemType;
	}

	public AbstractClothingType getClothingType() {
		return clothingType;
	}

	public AbstractWeaponType getWeaponType() {
		return weaponType;
	}

	public List<ItemEffect> getEffects() {
		return effects;
	}
}
