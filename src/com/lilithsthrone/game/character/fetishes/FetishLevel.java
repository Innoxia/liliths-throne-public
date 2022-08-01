package com.lilithsthrone.game.character.fetishes;

import java.io.IOException;
import java.io.InputStream;

import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.99
 * @version 0.4.4.2
 * @author Innoxia
 */
public enum FetishLevel {
	
	ZERO_NO_EXPERIENCE("inexperienced", "I", "", "overlay1", 0, 0, 0, 10, PresetColour.DESIRE_STAGE_ZERO),
	
	ONE_AMATEUR("amateur", "II", "", "overlay2", 0.5f, 1, 10, 50, PresetColour.DESIRE_STAGE_ONE),
	
	TWO_EXPERIENCED("experienced", "III", "", "overlay3", 1f, 2, 50, 100, PresetColour.DESIRE_STAGE_TWO),
	
	THREE_EXPERT("expert", "IV", "", "overlay4", 2f, 3, 100, 200, PresetColour.DESIRE_STAGE_THREE),
	
	FOUR_MASTERFUL("masterful", "V", "", "overlay5", 2.5f, 4, 200, 200, PresetColour.DESIRE_STAGE_FOUR);
	
	
	private String name;
	private String numeral;
	private String description;
	private String SVGImageOverlay;
	private float bonusArousalIncrease;
	private int bonusTeaseDamage;
	private int minimumExperience;
	private int maximumExperience;
	private Colour colour;
	
	private FetishLevel(String name, String numeral, String description, String pathName, float bonusArousalIncrease, int bonusTeaseDamage, int minimumExperience, int maximumExperience, Colour colour) {
		this.name = name;
		this.numeral = numeral;
		this.description = description;
		this.bonusArousalIncrease = bonusArousalIncrease;
		this.bonusTeaseDamage = bonusTeaseDamage;
		this.minimumExperience = minimumExperience;
		this.maximumExperience = maximumExperience;
		this.colour = colour;
		
		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/" + pathName + ".svg");
			if(is==null) {
				System.err.println("Error! FetishLevel icon file does not exist (Trying to read from '"+pathName+"')!");
			}
			SVGImageOverlay = Util.inputStreamToString(is);
			
			SVGImageOverlay = SvgUtil.colourReplacement(this.toString(), PresetColour.BASE_PINK, SVGImageOverlay);

			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}
	
	public String getNumeral() {
		return numeral;
	}
	
	public String getDescription() {
		return description;
	}

	public String getSVGImageOverlay() {
		return SVGImageOverlay;
	}

	public float getBonusArousalIncrease() {
		return bonusArousalIncrease;
	}

	public float getBonusArousalIncreasePartner() {
		return Math.round((bonusArousalIncrease/2f)*100)/100f;
	}
	
	public int getBonusTeaseDamage() {
		return bonusTeaseDamage;
	}
	
	public int getMinimumExperience() {
		return minimumExperience;
	}

	public int getMaximumExperience() {
		return maximumExperience;
	}

	public Colour getColour() {
		return colour;
	}
	
	public static FetishLevel getFetishLevelFromValue(int value){
		for(FetishLevel fl : FetishLevel.values()) {
			if(value>=fl.getMinimumExperience() && value<fl.getMaximumExperience())
				return fl;
		}
		return FOUR_MASTERFUL;
	}
	
}
