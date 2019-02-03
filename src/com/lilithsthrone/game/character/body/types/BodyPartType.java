package com.lilithsthrone.game.character.body.types;

/**
 * @since 0.1.69.9
 * @version 0.3.1
 * @author Innoxia
 */
public enum BodyPartType {
	
	GENERIC("generic"),
	
	ANTENNA("antenna"),
	ARM("arms"),
	ASS("ass"),
	ANUS("anus"),
	
	BREAST("breasts"),
	NIPPLES("nipples"),
	MILK("milk"),
	
	BREAST_CROTCH("crotch-breasts"),
	NIPPLES_CROTCH("crotch-nipples"),
	MILK_CROTCH("crotch-breast-milk"),
	
	EAR("ears"),
	EYE("eyes"),
	FACE("face"),
	MOUTH("mouth"),
	HAIR("hair"),
	HORN("horns"),
	LEG("legs"),
	PENIS("penis"),
	SECOND_PENIS("second penis"),
	TESTICLES("testicles"),
	CUM("cum"),
	SKIN("skin"),
	TAIL("tail"),
	TENTACLE("tentacle"),
	TONGUE("tongue"),
	CLIT("clitoris"),
	VAGINA("vagina"),
	GIRL_CUM("girl cum"),
	WING("wings");
	
	private String name;
	private BodyPartType(String name){
		this.name=name;
	}
	public String getName() {
		return name;
	}
	
}
