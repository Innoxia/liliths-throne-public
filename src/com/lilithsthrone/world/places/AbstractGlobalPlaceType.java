package com.lilithsthrone.world.places;
import java.io.IOException;
import java.io.InputStream;

import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.3.1
 * @version 0.3.1
 * @author Innoxia
 */
public abstract class AbstractGlobalPlaceType extends AbstractPlaceType {

	public AbstractGlobalPlaceType(String name,
			String SVGPath,
			String tooltipDescription,
			String colourString,
			DialogueNode dialogue,
			Encounter encounterType,
			String virginityLossDescription) {
		this(name, tooltipDescription, SVGPath, colourString, null, dialogue, encounterType, virginityLossDescription);
	}
	
	public AbstractGlobalPlaceType(String name,
			String tooltipDescription,
			String SVGPath,
			String colourString,
			String backgroundColourString,
			DialogueNode dialogue,
			Encounter encounterType,
			String virginityLossDescription) {
		super(name, tooltipDescription, null, null, dialogue, encounterType, virginityLossDescription);
		
		this.name = name;
		
		this.colourString = colourString;
		
		if(backgroundColourString==null) {
			this.backgroundColourString = colourString;
		} else {
			this.backgroundColourString = backgroundColourString;
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
					s = SvgUtil.colourReplacement("placeColour"+colourReplacementId, colourString, s);
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
	public AbstractGlobalPlaceType initItemsPersistInTile() {
		this.itemsDisappear = false;
		return this;
	}
	
	public abstract WorldType getGlobalLinkedWorldType();
}
