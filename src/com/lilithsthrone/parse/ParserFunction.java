package com.lilithsthrone.parse;

import com.lilithsthrone.game.character.GameCharacter;

/**
 * @author Funtacles
 */
public abstract class ParserFunction {
	private String[] tags;
	private boolean appliesFormatting;
	
	public ParserFunction(String[] tags) {
		this(tags, false);
	}

	public ParserFunction(String[] tags, boolean appliesFormatting) {
		this.tags = tags;
		this.appliesFormatting = appliesFormatting;
	}

	public String[] getTags() {
		return tags;
	}

	public boolean getAppliesFormatting() {
		return this.appliesFormatting;
	}
  	
	public abstract String parse(String command, String arguments, GameCharacter target);
}
