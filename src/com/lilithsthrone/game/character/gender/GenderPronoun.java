package com.lilithsthrone.game.character.gender;

/**
 * @since 0.1.67
 * @version 0.1.86
 * @author Innoxia
 */
public enum GenderPronoun {

	NOUN("Noun", "woman", "man", "person"),
	YOUNG_NOUN("Young noun", "girl", "boy", "person"),
	
	SECOND_PERSON("Second person", "she", "he", "they"),
	THIRD_PERSON("Third person", "her", "him", "them"),
	POSSESSIVE_BEFORE_NOUN("Possessive before noun", "her", "his", "their"),
	POSSESSIVE_ALONE("Possessive alone", "hers", "his", "theirs");
	
	private String name, feminine, masculine, neutral;
	
	private GenderPronoun(String name, String feminine, String masculine, String neutral){
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

	public String getNeutral() {
		return neutral;
	}
}
