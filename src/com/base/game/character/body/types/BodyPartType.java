package com.base.game.character.body.types;

/**
 * @since 0.1.69.9
 * @version 0.1.69.9
 * @author Innoxia
 */
public enum BodyPartType {
	
	GENERIC("generic"),
	
	ARM("arms"),
	ASS("ass"),
	BREAST("breasts"),
	EAR("ears"),
	EYE("eyes"),
	FACE("face"),
	HAIR("hair"),
	HORN("horns"),
	LEG("legs"),
	PENIS("penis"),
	SKIN("skin"),
	TAIL("tail"),
	TONGUE("tongue"),
	VAGINA("vagina"),
	WING("wings");
	
	private String name;
	private BodyPartType(String name){
		this.name=name;
	}
	public String getName() {
		return name;
	}
	
}
