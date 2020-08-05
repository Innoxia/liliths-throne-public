package com.lilithsthrone.utils.colours;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.main.Main;

import javafx.scene.paint.Color;

/**
 * @since 0.3.7
 * @version 0.3.9
 * @author Innoxia
 */
public class Colour {

	private boolean metallic;
	
	private Color colour;
	private Color lightColour;
	
	private String name;
	private List<String> formattingNames;

	private Colour colourLinkLighter = null;
	private Colour colourLinkDarker = null;
	
	public Colour(Color colour) {
		this.metallic = false;
		this.colour = colour;
		this.lightColour = colour;
		this.name = "";
	}
	
	public Colour(boolean metallic, Color colour, Color lightColour, String name) {
		this.metallic = metallic;
		this.colour = colour;
		this.lightColour = lightColour;
		this.name = name;
	}
	
	public Colour(boolean metallic, BaseColour colour, String name) {
		this.metallic = metallic;
		this.colour = colour.getColour();
		this.lightColour = colour.getLightColour();
		this.name = name;
	}
	
	// Constructors with formatting names:
	public Colour(boolean metallic, Color colour, Color lightColour, String name, List<String> formattingNames) {
		this.metallic = metallic;
		this.colour = colour;
		this.lightColour = lightColour;
		this.name = name;
		this.formattingNames = formattingNames;
	}
	
	public Colour(boolean metallic, BaseColour colour, String name, List<String> formattingNames) {
		this.metallic = metallic;
		this.colour = colour.getColour();
		this.lightColour = colour.getLightColour();
		this.name = name;
		this.formattingNames=formattingNames;
	}
	
	/**
	 * @return A String in the format RRGGBB
	 */
	public String toWebHexString() {
		return "#"+getColor().toString().substring(2, 8);
	}

	public Color getColor() {
		if(Main.getProperties()!=null) {
			if(Main.getProperties().hasValue(PropertyValue.lightTheme))
				return lightColour;
			else
				return colour;
			
		} else {
			return colour;
		}
	}

	public boolean isMetallic() {
		return metallic;
	}

	public List<String> getRainbowColours() {
		return null;
	}
	
	public String getRainbowDiv(int rainbowPixels) {
		if(getRainbowColours()==null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("repeating-linear-gradient(135deg,");
		int i=0;
		for(String s : getRainbowColours()) {
			sb.append(" "+s+" "+(i*rainbowPixels)+"px, "+s+" "+((i+1)*rainbowPixels)+"px,");
			i++;
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(");");
		return sb.toString();
	}
	
	public boolean isJetBlack() {
		return false;
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		if(PresetColour.getAllPresetColours().contains(this)) {
			return PresetColour.getIdFromColour(this);
		}
		return this.toWebHexString().replaceAll("#", "");
	}
	
	@Override
	public String toString() {
		System.err.println("Warning! Colour.toString() was called instead of getId()");
		return getId();
	}
	
	/**
	 * Sets this Colour's linked darker shade to the supplied colourLinkDarker, then sets the colourLinkDarker's lighter shade to this Colour, so that the two are linked.
	 * @return this Colour (so that it can be used as a Colour init method).
	 */
	public Colour setLinkedColourDarker(Colour colourLinkDarker) {
		this.colourLinkDarker = colourLinkDarker;
		colourLinkDarker.colourLinkLighter = this;
		return this;
	}
	
	/**
	 * @return The darker shade of this colour. <b>Is likely to return null!</b>
	 */
	public Colour getLinkedColourDarker() {
		return colourLinkDarker;
	}
	
	/**
	 * @return A List of Colours which are linked to this one as darker colours. The list is ordered with the darkest shade at index 0. The list might be empty.
	 */
	public List<Colour> getDarkerLinkedColours() {
		List<Colour> darkerList = new ArrayList<>();
		Colour darker = this.getLinkedColourDarker();
		if(darker==null) {
			return darkerList;
		}
		darkerList.add(darker);
		while(darker.getLinkedColourDarker()!=null) {
			darker = darker.getLinkedColourDarker();
			darkerList.add(darker);
		}
		Collections.reverse(darkerList);
		return darkerList;
	}
	
	/**
	 * Sets this Colour's linked lighter shade to the supplied colourLinkLighter, then sets the colourLinkLighter's darker shade to this Colour, so that the two are linked.
	 * @return this Colour (so that it can be used as a Colour init method).
	 */
	public Colour setLinkedColourLighter(Colour colourLinkLighter) {
		this.colourLinkLighter = colourLinkLighter;
		colourLinkLighter.colourLinkDarker = this;
		return this;
	}

	/**
	 * @return The lighter shade of this colour. <b>Is likely to return null!</b>
	 */
	public Colour getLinkedColourLighter() {
		return colourLinkLighter;
	}

	/**
	 * @return A List of Colours which are linked to this one as darker colours. The list is ordered with the darkest shade at index 0. The list might be empty.
	 */
	public List<Colour> getLighterLinkedColours() {
		List<Colour> lighterList = new ArrayList<>();
		Colour lighter = this.getLinkedColourLighter();
		if(lighter==null) {
			return lighterList;
		}
		lighterList.add(lighter);
		while(lighter.getLinkedColourLighter()!=null) {
			lighter = lighter.getLinkedColourLighter();
			lighterList.add(lighter);
		}
		return lighterList;
	}
	
	/**
	 * @return An array of length 5, with [0] being darkest, [4] being lightest.
	 */
	public String[] getShades() {
		return getShades(5);
	}
	
	public String[] getShades(int shadesCount) {
		return getShades(shadesCount, false, 1);
	}

	public String[] getShadesRgbaFormat(float opacity) {
		return getShades(5, true, opacity);
	}
	
	/**
	 * @param shadesCount Number of shades to calculate.
	 * @return Array of Strings, with each one being in the format:<br/>
	 * {@code rgba(63,107,169, opacity)}
	 */
	public String[] getShadesRgbaFormat(int shadesCount, float opacity) {
		return getShades(shadesCount, true, opacity);
	}
	
	private String[] getShades(int shadesCount, boolean rgba, float opacity) {
		String[] shadesString = new String[shadesCount];
		float luminosity = -0.5f;
		float increment = (Math.abs(luminosity)*2)/(shadesCount-1);
		int red = Integer.parseInt(colour.toString().substring(2, 4), 16);
		int gre = Integer.parseInt(colour.toString().substring(4, 6), 16);
		int blu = Integer.parseInt(colour.toString().substring(6, 8), 16);
		int r, g, b;

		for (int i = 0; i < shadesCount; i++) {
			r = red + (int)(red * (i * increment + luminosity));
			r = Math.max(Math.min(r, 255), 0);

			g = gre + (int)(gre * (i * increment + luminosity));
			g = Math.max(Math.min(g, 255), 0);

			b = blu + (int)(blu * (i * increment + luminosity));
			b = Math.max(Math.min(b, 255), 0);
			
			if(rgba) {
				shadesString[i] = "rgba("+r+","+g+","+b+", "+opacity+")";
			} else {
				shadesString[i] = String.format("#%02X%02X%02X", r, g, b);
			}
		}

		return shadesString;
	}

	public List<String> getFormattingNames() {
		return formattingNames;
	}

}
