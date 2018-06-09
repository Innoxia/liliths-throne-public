package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.2.5.8
 * @version 0.2.5.8
 * @author tukaima
 */
public enum GenesRate {
	ALWAYS_RECESSIVE(-500, -100, Colour.GENERIC_SEX),
	SUPER_RECESSIVE(-100, -50, Colour.GENERIC_SEX),
	RECESSIVE(-50, -5, Colour.GENERIC_SEX),
	NORMAL(-5, 5, Colour.GENERIC_SEX),
	DOMINANT(5, 50, Colour.GENERIC_SEX),
	SUPER_DOMINANT(50, 100, Colour.GENERIC_SEX),
	ALWAYS_DOMINANT(100, 500, Colour.GENERIC_SEX);

	private int minimumGenesRate, maximumGenesRate;
	private Colour colour;

	private GenesRate(int minimumGenesRate, int maximumGenesRate, Colour colour) {
		this.minimumGenesRate = minimumGenesRate;
		this.maximumGenesRate = maximumGenesRate;
		this.colour = colour;
	}

	public int getMinimumGenesRate() {
		return minimumGenesRate;
	}

	public int getMaximumGenesRate() {
		return maximumGenesRate;
	}
	
	public int getMedianGenesRate() {
		return minimumGenesRate + ((maximumGenesRate - minimumGenesRate)/2);
	}

	public static GenesRate valueOf(int genesRate) {
		for(GenesRate f : GenesRate.values()) {
			if(genesRate>=f.getMinimumGenesRate() && genesRate<f.getMaximumGenesRate()) {
				return f;
			}
		}
		return NORMAL;
	}
	
	public String getName() {

		if (this == ALWAYS_RECESSIVE)
			return "extremely low";
		else if (this == SUPER_RECESSIVE)
			return "very low";
		else if (this == RECESSIVE)
			return "low";
		else if (this == NORMAL)
			return "normal";
		else if (this == DOMINANT)
			return "high";
		else if (this == SUPER_DOMINANT)
			return "very high";
		else
			return "extremely high";
	}

	public static String getGenesRateName(int genesRate) {

		if (genesRate < ALWAYS_RECESSIVE.maximumGenesRate)
			return "extremely low";
		else if (genesRate < SUPER_RECESSIVE.maximumGenesRate)
			return "very low";
		else if (genesRate < RECESSIVE.maximumGenesRate)
			return "low";
		else if (genesRate < NORMAL.maximumGenesRate)
			return "normal";
		else if (genesRate < DOMINANT.maximumGenesRate)
			return "high";
		else if (genesRate < SUPER_DOMINANT.maximumGenesRate)
			return "very high";
		else
			return "extremely high";
	}
	
	public Colour getColour() {
		return colour;
	}
}