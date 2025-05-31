package com.lilithsthrone.game.inventory.outfit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.3.3.10
 * @version 0.4.10.8
 * @author Innoxia
 */
public class Outfit implements XMLSaving {
	
	//TODO game ID responsible for creation
	private long gameCreationID;
	private String name;
	private Map<InventorySlot, AbstractWeapon> weapons;
	private Map<InventorySlot, AbstractClothing> clothing;
	
	private InventorySlot iconSlotPriority;
	
	/**
	 * A Set of slots which should be left as they are when equipping this outfit.
	 * i.e. If an InventorySlot is in this set, then when this outfit is applied, that InventorySlot will keep its currently equipped clothing/weapon.
	 */
	private Set<InventorySlot> ignoredSlots;
	
	public Outfit() {
		this("outfit", new HashMap<>(), new HashMap<>());
	}
	
	public Outfit(String name, Map<InventorySlot, AbstractWeapon> weapons, Map<InventorySlot, AbstractClothing> clothing) {
		this.gameCreationID = Main.game.getId();
		
		this.name = name;
		
		if(weapons==null) {
			this.weapons = new HashMap<>();
		}
		this.weapons = weapons;
		
		if(clothing==null) {
			this.clothing = new HashMap<>();
		}
		this.clothing = clothing;
		
		this.iconSlotPriority = null;
		
		ignoredSlots = new HashSet<>();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Outfit){
			if(((Outfit)o).gameCreationID == gameCreationID
					&& ((Outfit)o).getName().equals(name)
					&& ((Outfit)o).getWeapons().equals(weapons)
					&& ((Outfit)o).getClothing().equals(clothing)
					&& Objects.equals(((Outfit)o).iconSlotPriority, iconSlotPriority)
					&& ((Outfit)o).getIgnoredSlots().equals(ignoredSlots)){
					return true;
			}
		}
//		System.out.println("/////");
//		System.out.println("OBJ "+(o instanceof Outfit));
//		System.out.println("O "+(((Outfit)o).gameCreationID == gameCreationID));
//		System.out.println("N "+(((Outfit)o).getName().equals(name)));
//		System.out.println("CL "+((Outfit)o).getClothing().equals(clothing));
//		System.out.println("WP "+((Outfit)o).getWeapons().equals(weapons));
//		System.out.println("ISP "+(((Outfit)o).iconSlotPriority.equals(iconSlotPriority)));
//		System.out.println("IS "+(((Outfit)o).getIgnoredSlots().equals(ignoredSlots)));
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		if(name!=null) {
			result = 31 * result + name.hashCode();
		}
		if(weapons!=null) {
			result = 31 * result + weapons.hashCode();
		}
		if(clothing!=null) {
			result = 31 * result + clothing.hashCode();
		}
		if(iconSlotPriority!=null) {
			result = 31 * result + iconSlotPriority.hashCode();
		}
		if(ignoredSlots!=null) {
			result = 31 * result + ignoredSlots.hashCode();
		}
		
		result = 31 * result + Long.hashCode(gameCreationID);
		
		return result;
	}
	
	public long getGameCreationID() {
		return gameCreationID;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public InventorySlot getIconSlotPriority() {
		return iconSlotPriority;
	}

	public void setIconSlotPriority(InventorySlot iconSlotPriority) {
		this.iconSlotPriority = iconSlotPriority;
	}
	
	public Map<InventorySlot, AbstractWeapon> getWeapons() {
		return weapons;
	}

	/**
	 * Adds the weapon to the InventorySlot for this outfit.
	 * <br/><b>NOTE:</b> If this slot is currently ignored, it will be automatically removed from the ignore set.
	 */
	public void addWeapon(InventorySlot slot, AbstractWeapon weaponItem) {
		removeIgnoredSlot(slot);
		if(weaponItem==null) {
			weapons.remove(slot);
		} else {
			weapons.put(slot, weaponItem);
		}
	}
	
	public Map<InventorySlot, AbstractClothing> getClothing() {
		return clothing;
	}
	
	/**
	 * Adds the clothing to the InventorySlot for this outfit.
	 * <br/><b>NOTE:</b> If this slot is currently ignored, it will be automatically removed from the ignore set.
	 */
	public void addClothing(InventorySlot slot, AbstractClothing clothingItem) {
		removeIgnoredSlot(slot);
		if(clothingItem==null) {
			clothing.remove(slot);
		} else {
			clothing.put(slot, clothingItem);
		}
	}
	
	public void clearSlot(InventorySlot slot) {
		if(slot.isWeapon()) {
			addWeapon(slot, null);
		} else {
			addClothing(slot, null);
		}
		if(iconSlotPriority==slot) {
			slot = null;
		}
	}
	
	public Set<InventorySlot> getIgnoredSlots() {
		return ignoredSlots;
	}

	public boolean removeIgnoredSlot(InventorySlot ignoredSlot) {
		return this.ignoredSlots.remove(ignoredSlot);
	}

	/**
	 * Sets this slot to be ignored for this outfit.
	 * <br/>i.e. When this outfit is applied, this slot will keep its currently equipped clothing/weapon.
	 * <br/><b>NOTE:</b> If this slot currently has clothing or a weapon in it, that clothing/weapon <b>will be removed from this outfit!</b>
	 */
	public boolean addIgnoredSlot(InventorySlot ignoredSlot) {
		clearSlot(ignoredSlot);
		return this.ignoredSlots.add(ignoredSlot);
	}

	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("outfit");
		parentElement.appendChild(element);
		
		XMLUtil.addAttribute(doc, element, "gameCreationID", String.valueOf(gameCreationID));
		
		XMLUtil.addAttribute(doc, element, "name", name);

		if(iconSlotPriority!=null) {
			XMLUtil.addAttribute(doc, element, "iconSlotPriority", iconSlotPriority.toString());
		}
		
		if(!this.getWeapons().isEmpty()) {
			Element innerElement = doc.createElement("weapons");
			element.appendChild(innerElement);
			for(Entry<InventorySlot, AbstractWeapon> e : this.getWeapons().entrySet()) {
				Element weaponElement = doc.createElement("weaponWrapper");
				innerElement.appendChild(weaponElement);
				XMLUtil.addAttribute(doc, weaponElement, "slot", e.getKey().toString());
				e.getValue().saveAsXML(weaponElement, doc);
			}
		}

		if(!this.getClothing().isEmpty()) {
			Element innerElement = doc.createElement("clothing");
			element.appendChild(innerElement);
			for(Entry<InventorySlot, AbstractClothing> e : this.getClothing().entrySet()) {
				Element clothingElement = doc.createElement("clothingWrapper");
				innerElement.appendChild(clothingElement);
				XMLUtil.addAttribute(doc, clothingElement, "slot", e.getKey().toString());
				e.getValue().saveAsXML(clothingElement, doc);
			}
		}
		
		if(!this.getIgnoredSlots().isEmpty()) {
			Element innerElement = doc.createElement("ignoredSlots");
			element.appendChild(innerElement);
			for(InventorySlot slot : getIgnoredSlots()) {
				Element e = doc.createElement("slot");
				e.setTextContent(slot.toString());
				innerElement.appendChild(e);
			}
		}
		
		return element;
	}
	
	public static Outfit loadFromXML(Element parentElement, Document doc) {
		long newID = -1l;
		String newName = "outfit";
		InventorySlot newIconSlotPriority = null;
		Map<InventorySlot, AbstractWeapon> newWeapons = new HashMap<>();
		Map<InventorySlot, AbstractClothing> newClothing = new HashMap<>();
		Set<InventorySlot> newIgnoredSlots = new HashSet<>();
		
		try {
			newID = Long.valueOf(parentElement.getAttribute("gameCreationID"));
		} catch(Exception ex) {
			System.err.println("Warning: An instance of Outfit was missing the 'gameCreationID' Attribute. (Using '-1' as ID instead.)");
		}
		
		try {
			newName = parentElement.getAttribute("name");
		} catch(Exception ex) {
			System.err.println("Warning: An instance of Outfit was missing the 'name' Attribute. (Using 'outfit' as name instead.)");
		}

		if(parentElement.hasAttribute("iconSlotPriority")) {
			try {
				newIconSlotPriority = InventorySlot.valueOf(parentElement.getAttribute("iconSlotPriority"));
			} catch(Exception ex) {
				System.err.println("Warning: An instance of iconSlotPriority failed to load");
			}
		}
		

		try {
			Element element = (Element)parentElement.getElementsByTagName("weapons").item(0);
			if(element!=null) {
				NodeList weaponElements = element.getElementsByTagName("weaponWrapper");
				for(int i=0; i<weaponElements.getLength(); i++){
					Element e = ((Element)weaponElements.item(i));
					newWeapons.put(InventorySlot.valueOf(e.getAttribute("slot")), AbstractWeapon.loadFromXML((Element)e.getElementsByTagName("weapon").item(0), doc));
				}
			}
		} catch(Exception ex) {
		}
		
		try {
			Element element = (Element)parentElement.getElementsByTagName("clothing").item(0);
			if(element!=null) {
				NodeList clothingElements = element.getElementsByTagName("clothingWrapper");
				for(int i=0; i<clothingElements.getLength(); i++){
					Element e = ((Element)clothingElements.item(i));
					newClothing.put(InventorySlot.valueOf(e.getAttribute("slot")), AbstractClothing.loadFromXML((Element)e.getElementsByTagName("clothing").item(0), doc));
				}
			}
		} catch(Exception ex) {
		}

		try {
			Element element = (Element)parentElement.getElementsByTagName("ignoredSlots").item(0);
			if(element!=null) {
				NodeList slotElements = element.getElementsByTagName("slot");
				for(int i=0; i<slotElements.getLength(); i++){
					Element e = ((Element)slotElements.item(i));
					newIgnoredSlots.add(InventorySlot.valueOf(e.getTextContent()));
				}
			}
		} catch(Exception ex) {
		}
		
		Outfit newOutfit = new Outfit(newName, newWeapons, newClothing);
		
		newOutfit.gameCreationID = newID;
		newOutfit.ignoredSlots = newIgnoredSlots;
		
		if(newIconSlotPriority!=null) {
			newOutfit.iconSlotPriority = newIconSlotPriority;
		}
		
		return newOutfit;
	}
	
	public String getIconSVG() {
		if(iconSlotPriority!=null) {
			if(clothing.get(iconSlotPriority)!=null) {
				return clothing.get(iconSlotPriority).getSVGString();
			}
			if(weapons.get(iconSlotPriority)!=null) {
				return weapons.get(iconSlotPriority).getSVGString();
			}
		}
		
		// Clothing is priority for icon:
		int maxValue = 0;
		AbstractClothing maxValueClothing = null;
		
		for(AbstractClothing c : clothing.values()) {
			if(c.getValue()>maxValue) {
				maxValueClothing = c;
				maxValue = c.getValue();
			}
		}
		
		if(maxValueClothing!=null) {
			return maxValueClothing.getSVGString();
		}
		
		// Try now for weapons:
		maxValue = 0;
		AbstractWeapon maxValueWeapon = null;
		
		for(AbstractWeapon w : weapons.values()) {
			if(w.getValue()>maxValue) {
				maxValueWeapon = w;
				maxValue = w.getValue();
			}
		}
		
		if(maxValueWeapon!=null) {
			return maxValueWeapon.getSVGString();
		}
		
		return "";
	}
	
	public int getCost() {
		int cost = 0;
		
		for(AbstractClothing c : clothing.values()) {
			cost += c.getValue();
		}

		for(AbstractWeapon w : weapons.values()) {
			cost += w.getValue();
		}
		
		return cost;
	}
}
