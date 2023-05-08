package com.lilithsthrone.game.character.markings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public class Scar implements XMLSaving {
	
	private ScarType type;
	private boolean plural;
	
	public Scar(ScarType type, boolean plural) {
		this.type = type;
		this.plural = plural;
	}
	
	@Override
	public boolean equals(Object o) {
		if(super.equals(o)) {
			return (o instanceof Scar)
					&& ((Scar)o).getType()==type
					&& ((Scar)o).isPlural()==plural;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + type.hashCode();
		result = 31 * result + (plural ? 1 : 0);
		return result;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("scar");
		parentElement.appendChild(element);

		XMLUtil.addAttribute(doc, element, "type", this.getType().toString());
		XMLUtil.addAttribute(doc, element, "plural", String.valueOf(this.isPlural()));
		
		return element;
	}
	
	public static Scar loadFromXML(Element parentElement, Document doc) {
		try {
			return new Scar(ScarType.getScarTypeFromString(parentElement.getAttribute("type")), Boolean.valueOf(parentElement.getAttribute("plural")));
			
		} catch(Exception ex) {
			System.err.println("Warning: An instance of Scar was unable to be imported!");
			return null;
		}
	}
	
	public String getName() {
		if(isPlural()) {
			return getType().getNamePlural();
		} else {
			return getType().getName();
		}
	}
	
	public ScarType getType() {
		return type;
	}
	
	public boolean isPlural() {
		return plural;
	}
	
}
