package com.lilithsthrone.game.character.gender;

/**
 * @since 0.1.67
 * @version 0.1.86
 * @author Innoxia
 */
public enum GenderPronoun {

	NOUN("Noun", "", "", "woman", "man", "person"),
	YOUNG_NOUN("Young noun", "", "", "girl", "boy", "person"),

	SUBJECTIVE_PRONOUN("Subjective pronoun", "I", "you", "she", "he", "they"),
	OBJECTIVE_PRONOUN("Objective pronoun", "me", "you", "her", "him", "them"),
	DEPENDENT_POSSESSIVE_PRONOUN("Dependent possessive pronoun", "my", "your", "her", "his", "their"),
	INDEPENDENT_POSSESSIVE_PRONOUN("Independent possessive pronoun", "mine", "yours", "hers", "his", "theirs"),
	REFLEXIVE_PRONOUN("Reflexive pronoun", "myself", "yourself", "herself", "himself", "themselves");
	
	private String name, first, second, feminine, masculine, neutral;
	
	private GenderPronoun(String name, String first, String second, String feminine, String masculine, String neutral){
		this.name = name;
		this.first = first;
		this.second = second;
		this.feminine = feminine;
		this.masculine = masculine;
		this.neutral = neutral;
	}

	public String getName() {
		return name;
	}

	public String getFirst() {
		return first;
	}

	public String getSecond() {
		return second;
	}
	
	public String getFeminine() {
		return feminine;
	}

	public String getMasculine() {
		return masculine;
	}

	public String getNeutral() {
		return neutral;
	}
}
