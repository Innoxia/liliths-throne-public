package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.84
 * @version 0.3.7
 * @author Innoxia
 */
public enum PiercingType {
	
	EAR("ear",
			"Ears are the most common area of the body that are pierced, and enable the equipping of earrings and other ear-related jewellery."),
	
	NOSE("nose",
			"Having a nose piercing allows you to equip jewellery such as nose rings or studs."),
	
	LIP("lip",
			"Lip piercings allow you to wear lip rings."),
	
	TONGUE("tongue",
			"Getting a tongue piercing will allow you to equip tongue bars."),
	
	NAVEL("navel",
			"Getting your navel (belly button) pierced allows you to equip navel-related jewellery."),
	
	NIPPLE("nipple",
			"Nipple piercings will allow you to equip nipple bars."),
	
	VAGINA("vagina",
			"Having a vagina piercing will allow you to equip vagina-related jewellery."),
	
	PENIS("penis",
			"Having a penis piercing will allow you to equip penis-related jewellery.");
	
	private String name;
	private String description;

	private PiercingType(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}
