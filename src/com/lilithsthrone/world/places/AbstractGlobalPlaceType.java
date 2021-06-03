package com.lilithsthrone.world.places;

import java.io.IOException;
import java.io.InputStream;

import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.encounters.AbstractEncounter;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldRegion;

/**
 * @since 0.3.1
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractGlobalPlaceType extends AbstractPlaceType {

	public AbstractGlobalPlaceType(WorldRegion worldRegion,
			String name,
			String SVGPath,
			String tooltipDescription,
			Colour colour,
			DialogueNode dialogue,
			AbstractEncounter encounterType,
			String virginityLossDescription) {
		this(worldRegion, name, tooltipDescription, SVGPath, colour, colour, dialogue, encounterType, virginityLossDescription);
	}
	
	public AbstractGlobalPlaceType(WorldRegion worldRegion,
			String name,
			String tooltipDescription,
			String SVGPath,
			Colour colour,
			Colour backgroundColour,
			DialogueNode dialogue,
			AbstractEncounter encounterType,
			String virginityLossDescription) {
		super(worldRegion, name, tooltipDescription, null, null, dialogue, Darkness.DAYLIGHT, encounterType, virginityLossDescription);
		
		this.name = name;
		
		this.colour = colour;
		
		if(backgroundColour==null) {
			this.backgroundColour = PresetColour.MAP_BACKGROUND;
		} else {
			this.backgroundColour = backgroundColour;
		}
		
		this.encounterType = encounterType;
		this.virginityLossDescription = virginityLossDescription;
		this.dialogue = dialogue;
		
		this.globalMapTile = true;
		
		if(SVGPath!=null) {
			try {
				InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/map/" + SVGPath + ".svg");
				if(is==null) {
					System.err.println("Error! PlaceType icon file does not exist (Trying to read from '"+SVGPath+"')! (Code 1)");
				}
				String s = Util.inputStreamToString(is);
				
				try {
					s = SvgUtil.colourReplacement("placeColour"+colourReplacementId, colour, s);
					colourReplacementId++;
				} catch(Exception ex) {
					System.err.println(SVGPath+" error!");
				}
				
				SVGString = s;
	
				is.close();
	
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			SVGString = null;
		}
	}
	
	@Override
	public AbstractGlobalPlaceType initDangerous() {
		this.dangerous = true;
		return this;
	}

	@Override
	public AbstractGlobalPlaceType initAquatic(Aquatic aquatic) {
		this.aquatic = aquatic;
		return this;
	}

	@Override
	public AbstractGlobalPlaceType initItemsPersistInTile() {
		this.itemsDisappear = false;
		return this;
	}
	
	public abstract AbstractWorldType getGlobalLinkedWorldType();
}
