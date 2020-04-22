package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.1.86
 * @author Innoxia
 */
public enum Femininity {
	MASCULINE_STRONG(0, 19, PresetColour.MASCULINE_PLUS),
	MASCULINE(20, 39, PresetColour.MASCULINE),
	ANDROGYNOUS(40, 59, PresetColour.ANDROGYNOUS),
	FEMININE(60, 79, PresetColour.FEMININE),
	FEMININE_STRONG(80, 100, PresetColour.FEMININE_PLUS);

	private int minimumFemininity, maximumFemininity;
	private Colour colour;

	private Femininity(int minimumFemininity, int maximumFemininity, Colour colour) {
		this.minimumFemininity = minimumFemininity;
		this.maximumFemininity = maximumFemininity;
		this.colour = colour;
	}

	public int getMinimumFemininity() {
		return minimumFemininity;
	}

	public int getMaximumFemininity() {
		return maximumFemininity;
	}
	
	public int getMedianFemininity() {
		return minimumFemininity + ((maximumFemininity - minimumFemininity)/2);
	}

	public static Femininity valueOf(int femininity) {
		for(Femininity f : Femininity.values()) {
			if(femininity>=f.getMinimumFemininity() && femininity<=f.getMaximumFemininity()) {
				return f;
			}
		}
		return FEMININE_STRONG;
	}
	
	public String getName(boolean withDeterminer) {

		if (this == MASCULINE_STRONG)
			return (withDeterminer ? "a " : "") + "very masculine";
		else if (this == MASCULINE)
			return (withDeterminer ? "a " : "") + "masculine";
		else if (this == ANDROGYNOUS)
			return (withDeterminer ? "an " : "") + "androgynous";
		else if (this == FEMININE)
			return (withDeterminer ? "a " : "") + "feminine";
		else
			return (withDeterminer ? "a " : "") + "very feminine";
	}

	public static String getFemininityName(int femininity, boolean withDeterminer) {

		if (femininity < MASCULINE_STRONG.maximumFemininity)
			return (withDeterminer ? "a " : "") + "very masculine";
		else if (femininity < MASCULINE.maximumFemininity)
			return (withDeterminer ? "a " : "") + "masculine";
		else if (femininity < ANDROGYNOUS.maximumFemininity)
			return (withDeterminer ? "an " : "") + "androgynous";
		else if (femininity < FEMININE.maximumFemininity)
			return (withDeterminer ? "a " : "") + "feminine";
		else
			return (withDeterminer ? "a " : "") + "very feminine";
	}

	public Colour getColour() {
		return colour;
	}
	
	public boolean isFeminine() {
		return this != MASCULINE && this != MASCULINE_STRONG;
	}
}
