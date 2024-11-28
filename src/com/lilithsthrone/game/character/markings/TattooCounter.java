package com.lilithsthrone.game.character.markings;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.GameCharacter;
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
	private int retroactiveApplicationOffset;

	public TattooCounter(TattooCounter counterToCopy) {
		this(counterToCopy.type, counterToCopy.countType, counterToCopy.colour, counterToCopy.glow, counterToCopy.retroactiveApplicationOffset);
	}
	
//	public TattooCounter(TattooCounterType type, TattooCountType countType, Colour colour, boolean glow) {
//		this.type = type;
//		this.countType = countType;
//		this.colour = colour;
//		this.glow = glow;
//		this.retroactiveApplicationOffset = 0;
//	}

	public TattooCounter(TattooCounterType type, TattooCountType countType, Colour colour, boolean glow, GameCharacter bearer) {
		this.type = type;
		this.countType = countType;
		this.colour = colour;
		this.glow = glow;
		this.retroactiveApplicationOffset = type.getNonRetroactiveOffset(bearer);
	}
	
	public TattooCounter(TattooCounterType type, TattooCountType countType, Colour colour, boolean glow, int retroactiveApplicationOffset) {
		this.type = type;
		this.countType = countType;
		this.colour = colour;
		this.glow = glow;
		this.retroactiveApplicationOffset = retroactiveApplicationOffset;
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
					&& ((TattooCounter)o).isGlow()==glow
					&& ((TattooCounter)o).getRetroactiveApplicationOffset()==this.getRetroactiveApplicationOffset();
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
		result = 31 * result + this.getRetroactiveApplicationOffset();
		return result;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("tattooCounter");
		parentElement.appendChild(element);

		XMLUtil.addAttribute(doc, element, "type", this.getType().toString());
		XMLUtil.addAttribute(doc, element, "countType", this.getCountType().toString());
		XMLUtil.addAttribute(doc, element, "colour", this.getColour().getId());
		XMLUtil.addAttribute(doc, element, "glow", String.valueOf(this.isGlow()));
		XMLUtil.addAttribute(doc, element, "retroactiveApplicationOffset", String.valueOf(this.getRetroactiveApplicationOffset()));
		
		return element;
	}
	
	public static TattooCounter loadFromXML(Element parentElement, Document doc) {
		try {
			int loadedRetroactiveApplicationOffset = 0;
			String loadedRAOString = parentElement.getAttribute("retroactiveApplicationOffset");
			if(!loadedRAOString.isEmpty()) {
				loadedRetroactiveApplicationOffset = Integer.valueOf(loadedRAOString);
			}
			
			return new TattooCounter(
					TattooCounterType.valueOf(parentElement.getAttribute("type")),
					TattooCountType.valueOf(parentElement.getAttribute("countType")),
					PresetColour.getColourFromId(parentElement.getAttribute("colour")),
					Boolean.valueOf(parentElement.getAttribute("glow")),
					loadedRetroactiveApplicationOffset);
			
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

	public void setType(TattooCounterType type, GameCharacter bearer) {
		this.type = type;
		if(!type.isRetroactiveApplicationAvailable()) {
			setRetroactiveApplicationOffset(0);
			
		} else {
			if(getRetroactiveApplicationOffset()<0) {
				setRetroactiveApplicationOffset(type.getNonRetroactiveOffset(bearer));
			}
		}
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

	/**
	 * @return A negative int (or 0) which should be added to the counter before displaying to the player.
	 */
	public int getRetroactiveApplicationOffset() {
		return retroactiveApplicationOffset;
	}

	public void setRetroactiveApplicationOffset(int retroactiveApplicationOffset) {
		this.retroactiveApplicationOffset = retroactiveApplicationOffset;
	}

	public void setRetroactiveApplicationOffset(GameCharacter bearer) {
		this.retroactiveApplicationOffset = getType().getNonRetroactiveOffset(bearer);
	}
	
	public boolean isRetroactiveApplication() {
		return getRetroactiveApplicationOffset()!=0;
	}
	
}
