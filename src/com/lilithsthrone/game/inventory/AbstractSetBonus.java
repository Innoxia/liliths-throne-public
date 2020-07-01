package com.lilithsthrone.game.inventory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;

/**
 * @since 0.3.8.2
 * @version 0.3.8.2
 * @author Innoxia
 */
public abstract class AbstractSetBonus {
	
	private boolean mod;
	private String name;
	private int numberRequiredForCompleteSet;
	private List<InventorySlot> blockedSlotsCountingTowardsFullSet;
	private AbstractStatusEffect associatedStatusEffect;

	public AbstractSetBonus(String name, AbstractStatusEffect associatedStatusEffect, int numberRequiredForCompleteSet, List<InventorySlot> blockedSlotsCountingTowardsFullSet) {
		this.name = name;
		this.numberRequiredForCompleteSet = numberRequiredForCompleteSet;
		
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
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile); // Loads the document and returns the root element - in setBonus files it's <setBonus>
				
				this.mod = mod;
				
				this.name = coreElement.getMandatoryFirstOf("name").getTextContent();
				
				this.numberRequiredForCompleteSet = Integer.valueOf(coreElement.getMandatoryFirstOf("numberRequiredForCompleteSet").getTextContent());
				
				this.associatedStatusEffect = StatusEffect.getStatusEffectFromId(coreElement.getMandatoryFirstOf("statusEffect").getTextContent());

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
		
		boolean atLeastOneClothingFound = false;
		for (AbstractClothing c : target.getClothingCurrentlyEquipped()) {
			if (c.getClothingType().getClothingSet() == this) {
				setCount++;
				atLeastOneClothingFound = true;
			}
		}
		
		int weaponSetCount = 0;
		for(AbstractWeapon weapon : target.getMainWeaponArray()) {
			if(weapon!=null && weapon.getWeaponType().getClothingSet() == this) {
				weaponSetCount++;
				atLeastOneClothingFound = true;
			}
		}
		for(AbstractWeapon weapon : target.getOffhandWeaponArray()) {
			if(weapon!=null && weapon.getWeaponType().getClothingSet() == this) {
				weaponSetCount++;
				atLeastOneClothingFound = true;
			}
		}
		
		setCount += Math.min(2, weaponSetCount);
		
		return atLeastOneClothingFound && setCount >= this.getNumberRequiredForCompleteSet();
	}

	public String getName() {
		return name;
	}

	public int getNumberRequiredForCompleteSet() {
		return numberRequiredForCompleteSet;
	}

	public AbstractStatusEffect getAssociatedStatusEffect() {
		return associatedStatusEffect;
	}

	public List<InventorySlot> getBlockedSlotsCountingTowardsFullSet() {
		return blockedSlotsCountingTowardsFullSet;
	}
}
