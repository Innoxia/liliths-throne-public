package com.lilithsthrone.game.character.body.types;

/**
 * @since 0.1.69.9
 * @version 0.4
 * @author Innoxia
 */
public enum BodyPartType {
	
	GENERIC("generic"),
	
	// Limbs/body:
	ARM("arms"),
	LEG("legs"),
	SKIN("skin"),
	TAIL("tail"),
	TENTACLE("tentacle"),
	WING("wings"),
	
	// Ass:
	ASS("ass"),
	ANUS("anus"),

	// Breasts:
	BREAST("breasts"),
	NIPPLES("nipples"),
	MILK("milk"),
	
	// Crotch-boobs:
	BREAST_CROTCH("crotch-breasts"),
	NIPPLES_CROTCH("crotch-nipples"),
	MILK_CROTCH("crotch-breast-milk"),
	
	// Head:
	ANTENNA("antenna"),
	EAR("ears"),
	EYE("eyes"),
	FACE("face"),
	MOUTH("mouth"),
	TONGUE("tongue"),
	HAIR("hair"),
	HORN("horns"),
	
	// Penis:
	PENIS("penis"),
	TESTICLES("testicles"),
	CUM("cum"),
	
	// Vagina:
	CLIT("clitoris"),
	VAGINA("vagina"),
	GIRL_CUM("girl cum"),
	
	// Spinneret:

	SPINNERET("spinneret");
	
	private String name;
	private BodyPartType(String name){
		this.name=name;
	}
	public String getName() {
		return name;
	}
	
}
