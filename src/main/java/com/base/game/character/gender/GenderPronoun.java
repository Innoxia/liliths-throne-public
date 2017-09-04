package com.base.game.character.gender;

/**
 * @since 0.1.67
 * @version 0.1.67
 * @author Innoxia
 */
public enum GenderPronoun {

	NOUN("Noun", "woman", "man"),
	YOUNG_NOUN("Young noun", "girl", "boy"),
	
	PENIS_NO_VAGINA("Penis, No Vagina", "trap", "male"),
	NO_PENIS_VAGINA("No Penis, Vagina", "female", "cuntboy"),
	PENIS_VAGINA("Penis, Vagina", "futanari", "hermaphrodite"),
	NO_PENIS_NO_VAGINA("No Penis, No Vagina", "doll", "doll"),
	
	SECOND_PERSON("Second person", "she", "he"),
	THIRD_PERSON("Third person", "her", "him"),
	POSSESSIVE_BEFORE_NOUN("Possessive before noun", "her", "his"),
	POSSESSIVE_ALONE("Possessive alone", "hers", "his");
	
	private String name, feminine, masculine;
	
	private GenderPronoun(String name, String feminine, String masculine){
		this.name = name;
		this.feminine = feminine;
		this.masculine = masculine;
	}

	public String getName() {
		return name;
	}
	
	public String getFeminine() {
		return feminine;
	}

	public String getMasculine() {
		return masculine;
	}
}
