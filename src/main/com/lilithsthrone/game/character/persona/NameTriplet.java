package com.lilithsthrone.game.character.persona;

/**
 * @since 0.1.75
 * @version 0.3
 * @author Innoxia
 */
public class NameTriplet {
	
	private String masculine, androgynous, feminine;

	public NameTriplet(String masculine, String androgynous, String feminine) {
		this.masculine = masculine;
		this.androgynous = androgynous;
		this.feminine = feminine;
	}
	
	public NameTriplet(String name) {
		this.masculine = name;
		this.androgynous = name;
		this.feminine = name;
	}
	
	public String toString() {
		return masculine+"-"+androgynous+"-"+feminine;
	}

	public String getMasculine() {
		return masculine;
	}

	public String getAndrogynous() {
		return androgynous;
	}

	public String getFeminine() {
		return feminine;
	}
}