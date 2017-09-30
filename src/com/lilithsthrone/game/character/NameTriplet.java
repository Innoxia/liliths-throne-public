package com.lilithsthrone.game.character;

import java.io.Serializable;

/**
 * @since 0.1.75
 * @version 0.1.85
 * @author Innoxia
 */
public class NameTriplet implements Serializable {
	private static final long serialVersionUID = 1L;
	
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