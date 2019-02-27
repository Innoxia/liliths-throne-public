package com.lilithsthrone.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.1.0
 * @version 0.3.1
 * @author Innoxia
 */
public class Cell implements XMLSaving {

	public static final int CELL_MAXIMUM_INVENTORY_SPACE = 48;
	
	private WorldType type;

	private Vector2i location;

	private boolean discovered;
	private boolean travelledTo;
	private GenericPlace place;
	private CharacterInventory inventory;
	private Set<String> charactersPresentIds;
	private Set<String> charactersHomeIds;
	private Set<String> charactersGlobalIds;

	public Cell(WorldType type, Vector2i location) {
		this.type = type;
		this.location = location;
		
		discovered = false;
		travelledTo = false;
		place = new GenericPlace(type.getStandardPlace());
		
		inventory = new CharacterInventory(0, CELL_MAXIMUM_INVENTORY_SPACE);
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
		
		CharacterUtils.addAttribute(doc, element, "discovered", String.valueOf(this.discovered));
		CharacterUtils.addAttribute(doc, element, "travelledTo", String.valueOf(this.travelledTo));
		
		place.saveAsXML(element, doc);
		
		if(!inventory.isEmpty()) {
			inventory.saveAsXML(element, doc);
		}
		return element;
	}
	
	public static Cell loadFromXML(Element parentElement, Document doc) {
		
		WorldType type = WorldType.EMPTY;
		if(parentElement.getAttribute("worldType").equals("SEWERS")) {
			type = WorldType.SUBMISSION;
		} else {
			type = WorldType.valueOf(parentElement.getAttribute("worldType"));
		}
		
		Cell cell = new Cell(
				type,
				new Vector2i(
					Integer.valueOf(((Element)parentElement.getElementsByTagName("location").item(0)).getAttribute("x")),
					Integer.valueOf(((Element)parentElement.getElementsByTagName("location").item(0)).getAttribute("y"))));
		
		cell.setDiscovered(Boolean.valueOf(parentElement.getAttribute("discovered")));
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.11.5")) {
			cell.setTravelledTo(Boolean.valueOf(parentElement.getAttribute("discovered")));
			
		} else {
			try {
				if(type.isRevealedOnStart()) {
					cell.setTravelledTo(true);
				} else {
					cell.setTravelledTo(Boolean.valueOf(parentElement.getAttribute("travelledTo")));
				}
			} catch(Exception ex) {	
			}
		}
		
		
		
		cell.setPlace(GenericPlace.loadFromXML(((Element)parentElement.getElementsByTagName("place").item(0)), doc, cell), false);
		
		try {
			if(parentElement.getElementsByTagName("characterInventory").getLength()>0) {
				cell.setInventory(CharacterInventory.loadFromXML(((Element)parentElement.getElementsByTagName("characterInventory").item(0)), doc));
			}
		} catch(Exception ex) {	
			System.err.println("Cell import error 1");
		}
		
		cell.getInventory().setMaximumInventorySpace(CELL_MAXIMUM_INVENTORY_SPACE);

		return cell;
	}
	
	@Override
	public String toString() {
		return "Cell ("+this.getLocation().toString()+"): Place: "+this.getPlace().getPlaceType().getName();
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
	
	public String getPlaceName() {
		return place.getName();
	}

	public boolean isDiscovered() {
		return discovered;
	}

	public void setDiscovered(boolean discovered) {
		this.discovered = discovered;
	}

	public boolean isTravelledTo() {
		return travelledTo;
	}

	public void setTravelledTo(boolean travelledTo) {
		this.travelledTo = travelledTo;
	}

	public GenericPlace getPlace() {
		return place;
	}

	public void setPlace(GenericPlace place, boolean applyInventoryInit) {
		this.place = place;
		if(applyInventoryInit) {
			place.getPlaceType().applyInventoryInit(this.getInventory());
		}
	}

	public boolean addPlaceUpgrade(PlaceUpgrade upgrade) {
		return getPlace().addPlaceUpgrade(this, upgrade);
	}
	
	public boolean removePlaceUpgrade(PlaceUpgrade upgrade) {
		return getPlace().removePlaceUpgrade(this, upgrade);
	}
	
	public Vector2i getLocation() {
		return location;
	}

	public void setLocation(Vector2i location) {
		this.location = location;
	}

	public void resetInventory(){
		resetInventory(null);
	}
	
	public void resetInventory(List<Rarity> rarityOfItemsToSave){
		if(rarityOfItemsToSave!=null && !rarityOfItemsToSave.isEmpty()) {
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

	public Set<String> getCharactersPresentIds() {
		return charactersPresentIds;
	}

	public void addCharacterPresentId(String id) {
		if(id.equals("NOT_SET")) {
			return;
		}
		if(charactersPresentIds==null) {
			charactersPresentIds = Collections.synchronizedSet(new HashSet<>());
		}
		synchronized (charactersPresentIds) {
			charactersPresentIds.add(id);
		}
	}

	public void removeCharacterPresentId(String id) {
		if(charactersPresentIds==null) {
			return;
		}
		synchronized (charactersPresentIds) {
//			System.out.println("removed "+id);
			charactersPresentIds.remove(id);
		}
	}

	public Set<String> getCharactersHomeIds() {
		return charactersHomeIds;
	}

	public void addCharacterHomeId(String id) {
		if(id.equals("NOT_SET")) {
			return;
		}
		if(charactersHomeIds==null) {
			charactersHomeIds = Collections.synchronizedSet(new HashSet<>());
		}
		synchronized (charactersHomeIds) {
			charactersHomeIds.add(id);
		}
	}

	public void removeCharacterHomeId(String id) {
		if(charactersHomeIds==null) {
			return;
		}
		synchronized (charactersHomeIds) {
			charactersHomeIds.remove(id);
		}
	}


	public Set<String> getCharactersGlobalIds() {
		return charactersGlobalIds;
	}

	public void addCharacterGlobalId(String id) {
		if(id.equals("NOT_SET")) {
			return;
		}
		if(charactersGlobalIds==null) {
			charactersGlobalIds = Collections.synchronizedSet(new HashSet<>());
		}
		synchronized (charactersGlobalIds) {
			charactersGlobalIds.add(id);
		}
	}

	public void removeCharacterGlobalId(String id) {
		if(charactersGlobalIds==null) {
			return;
		}
		synchronized (charactersGlobalIds) {
			charactersGlobalIds.remove(id);
		}
	}
	
}
