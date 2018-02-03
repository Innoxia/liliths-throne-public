package com.lilithsthrone.game.character.fetishes;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.99
 * @version 0.1.99
 * @author Innoxia
 */
public enum FetishDesire {
	
	ZERO_HATE("hate", "hate", "hates", "fondness1", -50f, Colour.BASE_CRIMSON),
	
	ONE_DISLIKE("dislike", "dislike", "dislikes", "fondness2", -10f, Colour.BASE_RED),
	
	TWO_NEUTRAL("indifferent", "are indifferent to", "is indifferent to", "fondness3", 0, Colour.BASE_GREY),
	
	THREE_LIKE("like", "like", "likes", "fondness4", 5f, Colour.BASE_PINK_LIGHT),
	
	FOUR_LOVE("love", "love", "loves", "fondness5", 10f, Colour.BASE_PINK);
	
	
	private String name;
	private String nameAsPlayerVerb;
	private String nameAsVerb;
	private String SVGImage;
	private float lustIncrement;
	private Colour colour;
	private List<String> modifiersList;
	
	private FetishDesire(String name, String nameAsPlayerVerb, String nameAsVerb, String pathName, float lustIncrement, Colour colour) {
		this.name = name;
		this.nameAsPlayerVerb = nameAsPlayerVerb;
		this.nameAsVerb = nameAsVerb;
		this.lustIncrement = lustIncrement;
		this.colour = colour;
		
		modifiersList = new ArrayList<>();
		modifiersList.add((lustIncrement >= 0 ? "[style.boldSex(+" + lustIncrement : "[style.boldBad(" + lustIncrement) + ")] [style.boldLust("+ Util.capitaliseSentence(Attribute.LUST.getAbbreviatedName())+ ")] from related sex actions");

		
		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/" + pathName + ".svg");
			SVGImage = Util.inputStreamToString(is);

			SVGImage = SVGImage.replaceAll("#ff2a2a", colour.getShades()[0]);
			SVGImage = SVGImage.replaceAll("#ff5555", colour.getShades()[1]);
			SVGImage = SVGImage.replaceAll("#ff8080", colour.getShades()[2]);
			SVGImage = SVGImage.replaceAll("#ffaaaa", colour.getShades()[3]);
			SVGImage = SVGImage.replaceAll("#ffd5d5", colour.getShades()[4]);

			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int getCostToChange() {
		return 1;
	}
	
	public FetishDesire getPreviousDesire() {
		switch(this) {
			case ZERO_HATE:
				return ZERO_HATE;
			case ONE_DISLIKE:
				return ZERO_HATE;
			case TWO_NEUTRAL:
				return ONE_DISLIKE;
			case THREE_LIKE:
				return TWO_NEUTRAL;
			case FOUR_LOVE:
				return THREE_LIKE;
		}
		return TWO_NEUTRAL;
	}
	
	public FetishDesire getNextDesire() {
		switch(this) {
			case ZERO_HATE:
				return ONE_DISLIKE;
			case ONE_DISLIKE:
				return TWO_NEUTRAL;
			case TWO_NEUTRAL:
				return THREE_LIKE;
			case THREE_LIKE:
				return FOUR_LOVE;
			case FOUR_LOVE:
				return FOUR_LOVE;
		}
		return TWO_NEUTRAL;
	}
	
	public String getName() {
		return name;
	}

	public String getNameAsPlayerVerb() {
		return nameAsPlayerVerb;
	}
	
	public String getNameAsVerb() {
		return nameAsVerb;
	}

	public float getLustIncrement() {
		return lustIncrement;
	}

	public String getSVGImage() {
		return SVGImage;
	}

	public Colour getColour() {
		return colour;
	}
	
	public List<String> getModifiersAsStringList() {
		return modifiersList;
	}

}
