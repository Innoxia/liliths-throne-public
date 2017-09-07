package com.base.game.character.gender;

import com.base.main.Main;
import com.base.utils.Colour;

/**
 * @since 0.1.0
 * @version 0.1.78
 * @author Innoxia
 */
public enum Gender {
	// Looks male:
	MALE(false, Colour.MASCULINE_PLUS, GenderPreference.NORMAL),
	CUNT_BOY(false, Colour.MASCULINE, GenderPreference.NONE),
	HERMAPHRODITE(false, Colour.ANDROGYNOUS, GenderPreference.NONE),
	GENDERLESS_MASCULINE(false, Colour.ANDROGYNOUS, GenderPreference.NONE),

	// Looks female:
	FEMALE(true, Colour.FEMININE_PLUS, GenderPreference.NORMAL),
	SHEMALE(true, Colour.FEMININE, GenderPreference.LOW),
	FUTANARI(true, Colour.ANDROGYNOUS, GenderPreference.LOW),

	// Other (treated as feminine):
	GENDERLESS_FEMININE(true, Colour.ANDROGYNOUS, GenderPreference.NONE);

	
	private Colour colour;
	private boolean feminine;
	private GenderPreference genderPreferenceDefault;

	private Gender(boolean feminine, Colour colour, GenderPreference genderPreferenceDefault) {
		this.feminine = feminine;
		this.colour = colour;
		this.genderPreferenceDefault = genderPreferenceDefault;
	}

	public boolean isFeminine() {
		return feminine;
	}

	public Colour getColour() {
		return colour;
	}

	/**
	 * boy, girl
	 * 
	 * @return
	 */
	public String getNounYoung() {
		if(feminine)
			return Main.getProperties().genderPronounFemale.get(GenderPronoun.YOUNG_NOUN);
		else
			return Main.getProperties().genderPronounMale.get(GenderPronoun.YOUNG_NOUN);
	}

	/**
	 * man, woman
	 * 
	 * @return
	 */
	public String getNoun() {
		if(feminine)
			return Main.getProperties().genderPronounFemale.get(GenderPronoun.NOUN);
		else
			return Main.getProperties().genderPronounMale.get(GenderPronoun.NOUN);
	}

	public String getName() {
		switch(this) {
			case CUNT_BOY:
				return Main.getProperties().genderPronounMale.get(GenderPronoun.NO_PENIS_VAGINA);
			case FEMALE:
				return Main.getProperties().genderPronounFemale.get(GenderPronoun.NO_PENIS_VAGINA);
			case FUTANARI:
				return Main.getProperties().genderPronounFemale.get(GenderPronoun.PENIS_VAGINA);
			case GENDERLESS_FEMININE:
				return Main.getProperties().genderPronounFemale.get(GenderPronoun.NO_PENIS_NO_VAGINA);
			case GENDERLESS_MASCULINE:
				return Main.getProperties().genderPronounMale.get(GenderPronoun.NO_PENIS_NO_VAGINA);
			case HERMAPHRODITE:
				return Main.getProperties().genderPronounMale.get(GenderPronoun.PENIS_VAGINA);
			case MALE:
				return Main.getProperties().genderPronounMale.get(GenderPronoun.PENIS_NO_VAGINA);
			case SHEMALE:
				return Main.getProperties().genderPronounFemale.get(GenderPronoun.PENIS_NO_VAGINA);
			default:
				return "unknown";
		}
	}

	/**
	 * Possessive: his, her
	 * 
	 * @return
	 */
	public String getPossessiveBeforeNoun() {
		if(feminine)
			return Main.getProperties().genderPronounFemale.get(GenderPronoun.POSSESSIVE_BEFORE_NOUN);
		else
			return Main.getProperties().genderPronounMale.get(GenderPronoun.POSSESSIVE_BEFORE_NOUN);
	}

	/**
	 * Possessive: his, hers
	 * 
	 * @return
	 */
	public String getPossessiveAlone() {
		if(feminine)
			return Main.getProperties().genderPronounFemale.get(GenderPronoun.POSSESSIVE_ALONE);
		else
			return Main.getProperties().genderPronounMale.get(GenderPronoun.POSSESSIVE_ALONE);
	}

	/**
	 * Second person: he, she
	 * 
	 * @return
	 */
	public String getSecondPerson() {
		if(feminine)
			return Main.getProperties().genderPronounFemale.get(GenderPronoun.SECOND_PERSON);
		else
			return Main.getProperties().genderPronounMale.get(GenderPronoun.SECOND_PERSON);
	}

	/**
	 * Pronoun: him, her
	 * 
	 * @return
	 */
	public String getThirdPerson() {
		if(feminine)
			return Main.getProperties().genderPronounFemale.get(GenderPronoun.THIRD_PERSON);
		else
			return Main.getProperties().genderPronounMale.get(GenderPronoun.THIRD_PERSON);
	}

	public GenderPreference getGenderPreferenceDefault() {
		return genderPreferenceDefault;
	}
}
