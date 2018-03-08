package com.lilithsthrone.world;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.world.places.GenericPlace;

/**
 * @since 0.1.0
 * @version 0.1.97
 * @author Innoxia
 * 
 */
public class Cell implements Serializable, XMLSaving {
	private static final long serialVersionUID = 1L;

	public static final int CELL_MAXIMUM_INVENTORY_SPACE = 48;
	
	private WorldType type;

	private Vector2i location;

	private String name;
	private boolean discovered, northAccess, southAccess, eastAccess, westAccess, blocked;
	private GenericPlace place;
	private CharacterInventory inventory;

	public Cell(WorldType type, Vector2i location) {
		this.type = type;
		this.location = location;
		
		name = "";
		discovered = false;
		place = new GenericPlace(type.getStandardPlace());
		
		inventory = new CharacterInventory(0, CELL_MAXIMUM_INVENTORY_SPACE);
		
		northAccess = false;
		southAccess = false;
		eastAccess = false;
		westAccess = false;
		blocked = false;
	}

	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("cell");
		parentElement.appendChild(element);
		
		CharacterUtils.addAttribute(doc, element, "worldType", this.getType().toString());
		
		Element location = doc.createElement("location");
		element.appendChild(location);
		CharacterUtils.addAttribute(doc, location, "x", String.valueOf(this.getLocation().getX()));
		CharacterUtils.addAttribute(doc, location, "y", String.valueOf(this.getLocation().getY()));
		
		CharacterUtils.addAttribute(doc, element, "name", this.getName());
		
		CharacterUtils.addAttribute(doc, element, "discovered", String.valueOf(this.discovered));
		CharacterUtils.addAttribute(doc, element, "northAccess", String.valueOf(this.northAccess));
		CharacterUtils.addAttribute(doc, element, "southAccess", String.valueOf(this.southAccess));
		CharacterUtils.addAttribute(doc, element, "eastAccess", String.valueOf(this.eastAccess));
		CharacterUtils.addAttribute(doc, element, "westAccess", String.valueOf(this.westAccess));
		CharacterUtils.addAttribute(doc, element, "blocked", String.valueOf(this.blocked));
		
		place.saveAsXML(element, doc);
		inventory.saveAsXML(element, doc);
		
		return element;
	}
	
	public static Cell loadFromXML(Element parentElement, Document doc) {
		Cell cell = new Cell(
				WorldType.valueOf(parentElement.getAttribute("worldType")),
				new Vector2i(
					Integer.valueOf(((Element)parentElement.getElementsByTagName("location").item(0)).getAttribute("x")),
					Integer.valueOf(((Element)parentElement.getElementsByTagName("location").item(0)).getAttribute("y"))));
		
		cell.setDiscovered(Boolean.valueOf(parentElement.getAttribute("discovered")));
		cell.setNorthAccess(Boolean.valueOf(parentElement.getAttribute("northAccess")));
		cell.setSouthAccess(Boolean.valueOf(parentElement.getAttribute("southAccess")));
		cell.setEastAccess(Boolean.valueOf(parentElement.getAttribute("eastAccess")));
		cell.setWestAccess(Boolean.valueOf(parentElement.getAttribute("westAccess")));
		cell.setBlocked(Boolean.valueOf(parentElement.getAttribute("blocked")));
		
		cell.setPlace(GenericPlace.loadFromXML(((Element)parentElement.getElementsByTagName("place").item(0)), doc));
		cell.setInventory(CharacterInventory.loadFromXML(((Element)parentElement.getElementsByTagName("characterInventory").item(0)), doc));
		
		cell.getInventory().setMaximumInventorySpace(CELL_MAXIMUM_INVENTORY_SPACE);
		
		return cell;
	}
	
	@Override
	public String toString() {
		return "Name: " + name;
	}
	
	public String getId() {
		return type.toString()+"-X:"+location.getX()+"-Y:"+location.getY();
	}

	public WorldType getType() {
		return type;
	}

	public void setType(WorldType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPlaceName() {
		return place.getName();
	}

	public boolean isDiscovered() {
		return discovered;
	}

	public void setDiscovered(boolean discovered) {
		this.discovered = discovered;
	}

	public GenericPlace getPlace() {
		return place;
	}

	public void setPlace(GenericPlace place) {
		this.place = place;
	}

	public Vector2i getLocation() {
		return location;
	}

	public void setLocation(Vector2i location) {
		this.location = location;
	}

	public boolean isNorthAccess() {
		return northAccess;
	}

	public void setNorthAccess(boolean northAccess) {
		this.northAccess = northAccess;
	}

	public boolean isSouthAccess() {
		return southAccess;
	}

	public void setSouthAccess(boolean southAccess) {
		this.southAccess = southAccess;
	}

	public boolean isEastAccess() {
		return eastAccess;
	}

	public void setEastAccess(boolean eastAccess) {
		this.eastAccess = eastAccess;
	}

	public boolean isWestAccess() {
		return westAccess;
	}

	public void setWestAccess(boolean westAccess) {
		this.westAccess = westAccess;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public void resetInventory(){
		resetInventory(null);
	}
	
	public void resetInventory(List<Rarity> rarityOfItemsToSave){
		if(rarityOfItemsToSave!=null && rarityOfItemsToSave.size()>0) {
			List<AbstractItem> itemsToSave = new ArrayList<>();
			for(AbstractItem item : this.inventory.getItemsInInventory()) {
				if(rarityOfItemsToSave.contains(item.getRarity())) {
					itemsToSave.add(item);
				}
			}
			
			List<AbstractWeapon> weaponsToSave = new ArrayList<>();
			for(AbstractWeapon weapon : this.inventory.getWeaponsInInventory()) {
				if(rarityOfItemsToSave.contains(weapon.getRarity())) {
					weaponsToSave.add(weapon);
				}
			}
			
			List<AbstractClothing> clothingToSave = new ArrayList<>();
			for(AbstractClothing clothing : this.inventory.getClothingInInventory()) {
				if(rarityOfItemsToSave.contains(clothing.getRarity())) {
					clothingToSave.add(clothing);
				}
			}
			
			this.inventory = new CharacterInventory(0, 48);
			
			for(AbstractItem item : itemsToSave) {
				this.inventory.addItem(item);
			}
			for(AbstractWeapon weapon : weaponsToSave) {
				this.inventory.addWeapon(weapon);
			}
			for(AbstractClothing clothing : clothingToSave) {
				this.inventory.addClothing(clothing);
			}
			
		} else {
			this.inventory = new CharacterInventory(0, 48);
		}
	}

	public CharacterInventory getInventory() {
		return inventory;
	}

	public void setInventory(CharacterInventory inventory) {
		this.inventory = inventory;
	}
}
