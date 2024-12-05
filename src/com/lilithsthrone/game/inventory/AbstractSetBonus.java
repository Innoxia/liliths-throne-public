package com.lilithsthrone.game.inventory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.3.8.2
 * @version 0.3.8.2
 * @author Innoxia
 */
public abstract class AbstractSetBonus {
	
	private boolean mod;
	private String name;
	private int numberRequiredForCompleteSet;
	private boolean countDuplicates;
	private List<InventorySlot> blockedSlotsCountingTowardsFullSet;
	private String statusEffectId;
	private AbstractStatusEffect associatedStatusEffect;

	public AbstractSetBonus(String name, AbstractStatusEffect associatedStatusEffect, int numberRequiredForCompleteSet, List<InventorySlot> blockedSlotsCountingTowardsFullSet) {
		this.name = name;
		this.numberRequiredForCompleteSet = numberRequiredForCompleteSet;
		this.countDuplicates = true;
		
		if(blockedSlotsCountingTowardsFullSet==null) {
			this.blockedSlotsCountingTowardsFullSet = new ArrayList<>();
		} else {
			this.blockedSlotsCountingTowardsFullSet = blockedSlotsCountingTowardsFullSet;
		}
		
		this.associatedStatusEffect = associatedStatusEffect;
	}
	
	public AbstractSetBonus(File XMLFile, String author, boolean mod) {
		if (XMLFile.exists()) {
			try {
				Document doc = Main.getDocBuilder().parse(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile); // Loads the document and returns the root element - in setBonus files it's <setBonus>
				
				this.mod = mod;
				
				this.name = coreElement.getMandatoryFirstOf("name").getTextContent();
				
				Element element = coreElement.getMandatoryFirstOf("numberRequiredForCompleteSet");
				this.numberRequiredForCompleteSet = Integer.valueOf(element.getTextContent());
				this.countDuplicates = true;
				if(!element.getAttribute("countDuplicates").isEmpty()) {
					this.countDuplicates = Boolean.valueOf(element.getAttribute("countDuplicates"));
				}
				
				this.statusEffectId = coreElement.getMandatoryFirstOf("statusEffect").getTextContent();

				this.blockedSlotsCountingTowardsFullSet = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("blockedSlotsCountingTowardsFullSet").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("blockedSlotsCountingTowardsFullSet").getAllOf("slot")) {
						InventorySlot slot = InventorySlot.valueOf(e.getTextContent());
						this.blockedSlotsCountingTowardsFullSet.add(slot);
					};
				}
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("SetBonus was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
	}
	
	public boolean isMod() {
		return mod;
	}
	
	public boolean isCharacterWearingCompleteSet(GameCharacter target) {
		int setCount = 0;
		
		for(InventorySlot slot : this.getBlockedSlotsCountingTowardsFullSet()) {
			if(slot.getBodyPartClothingBlock(target) != null) {
				setCount++;
			}
		}
		
		Set<AbstractClothingType> uniqueClothingTypes = new HashSet<>();
		Set<AbstractWeaponType> uniqueWeaponTypes = new HashSet<>();
		boolean atLeastOneFound = false;
		
		for (AbstractClothing c : target.getClothingCurrentlyEquipped()) {
			AbstractClothingType type = c.getClothingType();
			if (type.getClothingSet()==this && (isCountDuplicates() || !uniqueClothingTypes.contains(type))) {
				setCount++;
				uniqueClothingTypes.add(type);
				atLeastOneFound = true;
			}
		}
		
		int weaponSetCount = 0;
		for(AbstractWeapon weapon : target.getMainWeaponArray()) {
			if(weapon!=null) {
				AbstractWeaponType type = weapon.getWeaponType();
				if(type.getClothingSet() == this
						&& (isCountDuplicates() || !uniqueWeaponTypes.contains(type))) {
					weaponSetCount++;
					uniqueWeaponTypes.add(type);
					atLeastOneFound = true;
				}
			}
		}
		for(AbstractWeapon weapon : target.getOffhandWeaponArray()) {
			if(weapon!=null) {
				AbstractWeaponType type = weapon.getWeaponType();
				if(type.getClothingSet() == this
						&& (isCountDuplicates() || !uniqueWeaponTypes.contains(type))) {
					weaponSetCount++;
					uniqueWeaponTypes.add(type);
					atLeastOneFound = true;
				}
			}
		}
		
		setCount += Math.min(2, weaponSetCount);
		
		return atLeastOneFound && setCount >= this.getNumberRequiredForCompleteSet();
	}

	public String getName() {
		return name;
	}
	
	/**
	 * @return true if duplicate ClothingTypes or WeaponTypes will count towards the total for getNumberRequiredForCompleteSet().
	 * <br/>This is true by default.
	 */
	public boolean isCountDuplicates() {
		return countDuplicates;
	}
	
	public int getNumberRequiredForCompleteSet() {
		return numberRequiredForCompleteSet;
	}

	public AbstractStatusEffect getAssociatedStatusEffect() {
		if(associatedStatusEffect==null) {
			associatedStatusEffect = StatusEffect.getStatusEffectFromId(statusEffectId);
		}
		return associatedStatusEffect;
	}

	public List<InventorySlot> getBlockedSlotsCountingTowardsFullSet() {
		return blockedSlotsCountingTowardsFullSet;
	}
}
