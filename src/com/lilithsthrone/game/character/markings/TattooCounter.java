package com.lilithsthrone.game.character.markings;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.ColourListPresets;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public class TattooCounter implements XMLSaving {

	private TattooCounterType type;
	private TattooCountType countType;
	private Colour colour;
	private boolean glow;

	public TattooCounter(TattooCounter counterToCopy) {
		this(counterToCopy.type, counterToCopy.countType, counterToCopy.colour, counterToCopy.glow);
	}
	
	public TattooCounter(TattooCounterType type, TattooCountType countType, Colour colour, boolean glow) {
		this.type = type;
		this.countType = countType;
		this.colour = colour;
		this.glow = glow;
	}
	
	public static List<Colour> getAvailableColours() {
		return ColourListPresets.ALL;
	}

	@Override
	public boolean equals(Object o) {
		if(super.equals(o)) {
			return (o instanceof TattooCounter)
					&& ((TattooCounter)o).getType().equals(this.getType())
					&& ((TattooCounter)o).getCountType().equals(this.getCountType())
					&& ((TattooCounter)o).getColour().equals(this.getColour())
					&& ((TattooCounter)o).isGlow()==glow;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + getType().hashCode();
		result = 31 * result + getCountType().hashCode();
		result = 31 * result + getColour().hashCode();
		result = 31 * result + (isGlow() ? 1 : 0);
		return result;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("tattooCounter");
		parentElement.appendChild(element);

		XMLUtil.addAttribute(doc, element, "type", this.getType().toString());
		XMLUtil.addAttribute(doc, element, "countType", this.getCountType().toString());
		XMLUtil.addAttribute(doc, element, "colour", this.getColour().getId());
		XMLUtil.addAttribute(doc, element, "glow", String.valueOf(this.isGlow()));
		
		return element;
	}
	
	public static TattooCounter loadFromXML(Element parentElement, Document doc) {
		try {
			return new TattooCounter(
					TattooCounterType.valueOf(parentElement.getAttribute("type")),
					TattooCountType.valueOf(parentElement.getAttribute("countType")),
					PresetColour.getColourFromId(parentElement.getAttribute("colour")),
					Boolean.valueOf(parentElement.getAttribute("glow")));
			
		} catch(Exception ex) {
			System.err.println("Warning: An instance of TattooCounter was unable to be imported!");
			return null;
		}
	}
	
	public TattooCounterType getType() {
		return type;
	}

	public TattooCountType getCountType() {
		return countType;
	}

	public Colour getColour() {
		return colour;
	}

	public boolean isGlow() {
		return glow;
	}

	public void setType(TattooCounterType type) {
		this.type = type;
	}

	public void setCountType(TattooCountType countType) {
		this.countType = countType;
	}

	public void setColour(Colour colour) {
		this.colour = colour;
	}

	public void setGlow(boolean glow) {
		this.glow = glow;
	}
	
	
}
