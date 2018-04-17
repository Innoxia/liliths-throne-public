package com.lilithsthrone.game.character.gender;

import java.util.HashMap;
import java.util.Map;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.0
 * @version 0.1.86
 * @author Innoxia
 */
public enum Gender {
	
	// Masculine:
	M_P_V_B_HERMAPHRODITE(GenderNames.Y_PENIS_Y_VAGINA_Y_BREASTS, PronounType.MASCULINE, Colour.MASCULINE, GenderPreference.ZERO_NONE),
	M_P_V_HERMAPHRODITE(GenderNames.Y_PENIS_Y_VAGINA_N_BREASTS, PronounType.MASCULINE, Colour.MASCULINE, GenderPreference.ZERO_NONE),
	M_P_B_BUSTYBOY(GenderNames.Y_PENIS_N_VAGINA_Y_BREASTS, PronounType.MASCULINE, Colour.MASCULINE, GenderPreference.ZERO_NONE),
	M_P_MALE(GenderNames.Y_PENIS_N_VAGINA_N_BREASTS, PronounType.MASCULINE, Colour.MASCULINE, GenderPreference.THREE_AVERAGE),
	M_V_B_BUTCH(GenderNames.N_PENIS_Y_VAGINA_Y_BREASTS, PronounType.MASCULINE, Colour.MASCULINE, GenderPreference.ZERO_NONE),
	M_V_CUNTBOY(GenderNames.N_PENIS_Y_VAGINA_N_BREASTS, PronounType.MASCULINE, Colour.MASCULINE, GenderPreference.ZERO_NONE),
	M_B_MANNEQUIN(GenderNames.N_PENIS_N_VAGINA_Y_BREASTS, PronounType.MASCULINE, Colour.MASCULINE, GenderPreference.ZERO_NONE),
	M_MANNEQUIN(GenderNames.N_PENIS_N_VAGINA_N_BREASTS, PronounType.MASCULINE, Colour.MASCULINE, GenderPreference.ZERO_NONE),
	
	// Feminine:
	F_P_V_B_FUTANARI(GenderNames.Y_PENIS_Y_VAGINA_Y_BREASTS, PronounType.FEMININE, Colour.FEMININE, GenderPreference.ONE_MINIMAL),
	F_P_V_FUTANARI(GenderNames.Y_PENIS_Y_VAGINA_N_BREASTS, PronounType.FEMININE, Colour.FEMININE, GenderPreference.ZERO_NONE),
	F_P_B_SHEMALE(GenderNames.Y_PENIS_N_VAGINA_Y_BREASTS, PronounType.FEMININE, Colour.FEMININE, GenderPreference.ZERO_NONE),
	F_P_TRAP(GenderNames.Y_PENIS_N_VAGINA_N_BREASTS, PronounType.FEMININE, Colour.FEMININE, GenderPreference.ONE_MINIMAL),
	F_V_B_FEMALE(GenderNames.N_PENIS_Y_VAGINA_Y_BREASTS, PronounType.FEMININE, Colour.FEMININE, GenderPreference.THREE_AVERAGE),
	F_V_FEMALE(GenderNames.N_PENIS_Y_VAGINA_N_BREASTS, PronounType.FEMININE, Colour.FEMININE, GenderPreference.ZERO_NONE),
	F_B_DOLL(GenderNames.N_PENIS_N_VAGINA_Y_BREASTS, PronounType.FEMININE, Colour.FEMININE, GenderPreference.ZERO_NONE),
	F_DOLL(GenderNames.N_PENIS_N_VAGINA_N_BREASTS, PronounType.FEMININE, Colour.FEMININE, GenderPreference.ZERO_NONE),

	// Neutral (treated as feminine for now):
	N_P_V_B_HERMAPHRODITE(GenderNames.Y_PENIS_Y_VAGINA_Y_BREASTS, PronounType.NEUTRAL, Colour.ANDROGYNOUS, GenderPreference.ZERO_NONE),
	N_P_V_HERMAPHRODITE(GenderNames.Y_PENIS_Y_VAGINA_N_BREASTS, PronounType.NEUTRAL, Colour.ANDROGYNOUS, GenderPreference.ZERO_NONE),
	N_P_B_SHEMALE(GenderNames.Y_PENIS_N_VAGINA_Y_BREASTS, PronounType.NEUTRAL, Colour.ANDROGYNOUS, GenderPreference.ZERO_NONE),
	N_P_TRAP(GenderNames.Y_PENIS_N_VAGINA_N_BREASTS, PronounType.NEUTRAL, Colour.ANDROGYNOUS, GenderPreference.ZERO_NONE),
	N_V_B_TOMBOY(GenderNames.N_PENIS_Y_VAGINA_Y_BREASTS, PronounType.NEUTRAL, Colour.ANDROGYNOUS, GenderPreference.ZERO_NONE),
	N_V_TOMBOY(GenderNames.N_PENIS_Y_VAGINA_N_BREASTS, PronounType.NEUTRAL, Colour.ANDROGYNOUS, GenderPreference.ZERO_NONE),
	N_B_DOLL(GenderNames.N_PENIS_N_VAGINA_Y_BREASTS, PronounType.NEUTRAL, Colour.ANDROGYNOUS, GenderPreference.ZERO_NONE),
	N_NEUTER(GenderNames.N_PENIS_N_VAGINA_N_BREASTS, PronounType.NEUTRAL, Colour.ANDROGYNOUS, GenderPreference.ZERO_NONE);

	
	private GenderNames name;
	private Colour colour;
	private PronounType type;
	private GenderPreference genderPreferenceDefault;

	private Gender(GenderNames name, PronounType type, Colour colour, GenderPreference genderPreferenceDefault) {
		this.name = name;
		this.type = type;
		this.colour = colour;
		this.genderPreferenceDefault = genderPreferenceDefault;
	}
	
	public GenderNames getGenderName() {
		return name;
	}
	
	public PronounType getType() {
		return type;
	}

	public boolean isFeminine() {
		return type == PronounType.FEMININE || type == PronounType.NEUTRAL;
	}
	
	public Colour getColour() {
		return colour;
	}

	/**
	 * boy, girl
	 */
	public String getNounYoung() {
		if(isFeminine()) {
			return Main.getProperties().genderPronounFemale.get(GenderPronoun.YOUNG_NOUN);
		} else {
			return Main.getProperties().genderPronounMale.get(GenderPronoun.YOUNG_NOUN);
		}
	}

	/**
	 * man, woman
	 */
	public String getNoun() {
		if(isFeminine()) {
			return Main.getProperties().genderPronounFemale.get(GenderPronoun.NOUN);
		} else {
			return Main.getProperties().genderPronounMale.get(GenderPronoun.NOUN);
		}
	}

	public String getName() {
		switch(type) {
			case FEMININE:
				return Main.getProperties().genderNameFemale.get(name);
			case MASCULINE:
				return Main.getProperties().genderNameMale.get(name);
			case NEUTRAL:
				return Main.getProperties().genderNameNeutral.get(name);
		}
		return Main.getProperties().genderNameFemale.get(name);
	}

	/**
	 * Possessive: his, her
	 */
	public String getPossessiveBeforeNoun() {
		if(isFeminine()) {
			return Main.getProperties().genderPronounFemale.get(GenderPronoun.POSSESSIVE_BEFORE_NOUN);
		} else {
			return Main.getProperties().genderPronounMale.get(GenderPronoun.POSSESSIVE_BEFORE_NOUN);
		}
	}

	/**
	 * Possessive: his, hers
	 */
	public String getPossessiveAlone() {
		if(isFeminine()) {
			return Main.getProperties().genderPronounFemale.get(GenderPronoun.POSSESSIVE_ALONE);
		} else {
			return Main.getProperties().genderPronounMale.get(GenderPronoun.POSSESSIVE_ALONE);
		}
	}

	/**
	 * Second person: he, she
	 */
	public String getSecondPerson() {
		if(isFeminine()) {
			return Main.getProperties().genderPronounFemale.get(GenderPronoun.SECOND_PERSON);
		} else {
			return Main.getProperties().genderPronounMale.get(GenderPronoun.SECOND_PERSON);
		}
	}

	/**
	 * Pronoun: him, her
	 */
	public String getThirdPerson() {
		if(isFeminine()) {
			return Main.getProperties().genderPronounFemale.get(GenderPronoun.THIRD_PERSON);
		} else {
			return Main.getProperties().genderPronounMale.get(GenderPronoun.THIRD_PERSON);
		}
	}

	public GenderPreference getGenderPreferenceDefault() {
		return genderPreferenceDefault;
	}

	public Gender toMasculine() {
		Map<Gender, Gender> map = new HashMap<Gender, Gender>();
		map.put(M_P_V_B_HERMAPHRODITE, M_P_V_B_HERMAPHRODITE);
		map.put(M_P_V_HERMAPHRODITE, M_P_V_HERMAPHRODITE);
		map.put(M_P_B_BUSTYBOY, M_P_B_BUSTYBOY);
		map.put(M_P_MALE, M_P_MALE);
		map.put(M_V_B_BUTCH, M_V_B_BUTCH);
		map.put(M_V_CUNTBOY, M_V_CUNTBOY);
		map.put(M_B_MANNEQUIN, M_B_MANNEQUIN);
		map.put(M_MANNEQUIN, M_MANNEQUIN);
		map.put(F_P_V_B_FUTANARI, M_P_V_B_HERMAPHRODITE);
		map.put(F_P_V_FUTANARI, M_P_V_HERMAPHRODITE);
		map.put(F_P_B_SHEMALE, M_P_B_BUSTYBOY);
		map.put(F_P_TRAP, M_P_MALE);
		map.put(F_V_B_FEMALE, M_V_B_BUTCH);
		map.put(F_V_FEMALE, M_V_CUNTBOY);
		map.put(F_B_DOLL, M_B_MANNEQUIN);
		map.put(F_DOLL, M_MANNEQUIN);
		map.put(N_P_V_B_HERMAPHRODITE, M_P_V_B_HERMAPHRODITE);
		map.put(N_P_V_HERMAPHRODITE, M_P_V_HERMAPHRODITE);
		map.put(N_P_B_SHEMALE, M_P_B_BUSTYBOY);
		map.put(N_P_TRAP, M_P_MALE);
		map.put(N_V_B_TOMBOY, M_V_B_BUTCH);
		map.put(N_V_TOMBOY, M_V_CUNTBOY);
		map.put(N_B_DOLL, M_B_MANNEQUIN);
		map.put(N_NEUTER, M_MANNEQUIN);
		return map.get(this);
	}

	public Gender toFeminine() {
		Map<Gender, Gender> map = new HashMap<Gender, Gender>();
		map.put(M_P_V_B_HERMAPHRODITE, F_P_V_B_FUTANARI);
		map.put(M_P_V_HERMAPHRODITE, F_P_V_FUTANARI);
		map.put(M_P_B_BUSTYBOY, F_P_B_SHEMALE);
		map.put(M_P_MALE, F_P_TRAP);
		map.put(M_V_B_BUTCH, F_V_B_FEMALE);
		map.put(M_V_CUNTBOY, F_V_FEMALE);
		map.put(M_B_MANNEQUIN, F_B_DOLL);
		map.put(M_MANNEQUIN, F_DOLL);
		map.put(F_P_V_B_FUTANARI, F_P_V_B_FUTANARI);
		map.put(F_P_V_FUTANARI, F_P_V_FUTANARI);
		map.put(F_P_B_SHEMALE, F_P_B_SHEMALE);
		map.put(F_P_TRAP, F_P_TRAP);
		map.put(F_V_B_FEMALE, F_V_B_FEMALE);
		map.put(F_V_FEMALE, F_V_FEMALE);
		map.put(F_B_DOLL, F_B_DOLL);
		map.put(F_DOLL, F_DOLL);
		map.put(N_P_V_B_HERMAPHRODITE, F_P_V_B_FUTANARI);
		map.put(N_P_V_HERMAPHRODITE, F_P_V_FUTANARI);
		map.put(N_P_B_SHEMALE, F_P_B_SHEMALE);
		map.put(N_P_TRAP, F_P_TRAP);
		map.put(N_V_B_TOMBOY, F_V_B_FEMALE);
		map.put(N_V_TOMBOY, F_V_FEMALE);
		map.put(N_B_DOLL, F_B_DOLL);
		map.put(N_NEUTER, F_DOLL);
		return map.get(this);
	}

	public Gender toAndrogynous() {
		Map<Gender, Gender> map = new HashMap<Gender, Gender>();
		map.put(M_P_V_B_HERMAPHRODITE, N_P_V_B_HERMAPHRODITE);
		map.put(M_P_V_HERMAPHRODITE, N_P_V_HERMAPHRODITE);
		map.put(M_P_B_BUSTYBOY, N_P_B_SHEMALE);
		map.put(M_P_MALE, N_P_TRAP);
		map.put(M_V_B_BUTCH, N_V_B_TOMBOY);
		map.put(M_V_CUNTBOY, N_V_TOMBOY);
		map.put(M_B_MANNEQUIN, N_B_DOLL);
		map.put(M_MANNEQUIN, N_NEUTER);
		map.put(F_P_V_B_FUTANARI, N_P_V_B_HERMAPHRODITE);
		map.put(F_P_V_FUTANARI, N_P_V_HERMAPHRODITE);
		map.put(F_P_B_SHEMALE, N_P_B_SHEMALE);
		map.put(F_P_TRAP, N_P_TRAP);
		map.put(F_V_B_FEMALE, N_V_B_TOMBOY);
		map.put(F_V_FEMALE, N_V_TOMBOY);
		map.put(F_B_DOLL, N_B_DOLL);
		map.put(F_DOLL, N_NEUTER);
		map.put(N_P_V_B_HERMAPHRODITE, N_P_V_B_HERMAPHRODITE);
		map.put(N_P_V_HERMAPHRODITE, N_P_V_HERMAPHRODITE);
		map.put(N_P_B_SHEMALE, N_P_B_SHEMALE);
		map.put(N_P_TRAP, N_P_TRAP);
		map.put(N_V_B_TOMBOY, N_V_B_TOMBOY);
		map.put(N_V_TOMBOY, N_V_TOMBOY);
		map.put(N_B_DOLL, N_B_DOLL);
		map.put(N_NEUTER, N_NEUTER);
		return map.get(this);
	}
}
