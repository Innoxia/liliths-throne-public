package com.lilithsthrone.game.inventory.outfit;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.3.3.10
 * @version 0.3.3.10
 * @author Innoxia
 */
public class Outfit implements XMLSaving {
	
	private String name;
	private OutfitType outfitType;
	private Map<String, String> weapons;
	private Map<String, String> clothing;
	
	public Outfit(String name, OutfitType outfitType, Map<String, String> weapons, Map<String, String> clothing) {
		this.name = name;
		this.outfitType = outfitType;
		
		if(weapons==null) {
			this.weapons = new HashMap<>();
		}
		this.weapons = weapons;
		
		if(clothing==null) {
			this.clothing = new HashMap<>();
		}
		this.clothing = clothing;
		
	}
	
	public String getName() {
		return name;
	}
	
	public OutfitType getOutfitType() {
		return outfitType;
	}
	
	/**
	 * @return A map of unique weapon IDs, each mapped to a value of the WeaponType id.
	 */
	public Map<String, String> getWeapons() {
		return weapons;
	}

	/**
	 * @return A map of unique clothing IDs, each mapped to a value of the ClothingType id.
	 */
	public Map<String, String> getClothing() {
		return clothing;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("outfit");
		parentElement.appendChild(element);
		
		XMLUtil.addAttribute(doc, element, "name", name);
		XMLUtil.addAttribute(doc, element, "outfitType", outfitType.toString());
		
		if(!this.getWeapons().isEmpty()) {
			Element innerElement = doc.createElement("weapons");
			element.appendChild(innerElement);
			for(Entry<String, String> e : this.getWeapons().entrySet()) {
				Element weaponElement = doc.createElement("w");
				innerElement.appendChild(weaponElement);
				XMLUtil.addAttribute(doc, weaponElement, "id", e.getKey());
				XMLUtil.addAttribute(doc, weaponElement, "typeId", e.getValue());
			}
		}

		if(!this.getClothing().isEmpty()) {
			Element innerElement = doc.createElement("clothing");
			element.appendChild(innerElement);
			for(Entry<String, String> e : this.getWeapons().entrySet()) {
				Element clothingElement = doc.createElement("c");
				innerElement.appendChild(clothingElement);
				XMLUtil.addAttribute(doc, clothingElement, "id", e.getKey());
				XMLUtil.addAttribute(doc, clothingElement, "typeId", e.getValue());
			}
		}
		
		return element;
	}
	
	public static Outfit loadFromXML(Element parentElement, Document doc) {
		
		String newName = "";
		OutfitType newOutfitType = null;
		Map<String, String> newWeapons = new HashMap<>();
		Map<String, String> newClothing = new HashMap<>();
		
		try {
			newName = parentElement.getAttribute("name");
		} catch(Exception ex) {
			System.err.println("Warning: An instance of AbstractClothing was unable to be imported. ("+parentElement.getAttribute("name")+")");
			return null;
		}
		try {
			newOutfitType = OutfitType.valueOf(parentElement.getAttribute("outfitType"));
		} catch(Exception ex) {
			System.err.println("Warning: An instance of AbstractClothing was unable to be imported. ("+parentElement.getAttribute("name")+")");
			return null;
		}
		

		try {
			Element element = (Element)parentElement.getElementsByTagName("weapons").item(0);
			if(element!=null) {
				NodeList effectElements = element.getElementsByTagName("w");
				for(int i=0; i<effectElements.getLength(); i++){
					Element e = ((Element)effectElements.item(i));
					newWeapons.put(e.getAttribute("id"), e.getAttribute("typeId"));
				}
			}
		} catch(Exception ex) {
		}
		
		try {
			Element element = (Element)parentElement.getElementsByTagName("clothing").item(0);
			if(element!=null) {
				NodeList effectElements = element.getElementsByTagName("c");
				for(int i=0; i<effectElements.getLength(); i++){
					Element e = ((Element)effectElements.item(i));
					newClothing.put(e.getAttribute("id"), e.getAttribute("typeId"));
				}
			}
		} catch(Exception ex) {
		}
		
		return new Outfit(newName, newOutfitType, newWeapons, newClothing);
	}
	
}
