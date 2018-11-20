package com.lilithsthrone.game.occupantManagement;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.87
 * @version 0.2.5
 * @author Innoxia
 */
public enum SlaveJobSetting {
	
	SEX_ORAL(Colour.GENERIC_SEX, "Allow Oral", "Oral", "Allow this slave to perform oral on others."),
	SEX_VAGINAL(Colour.GENERIC_SEX, "Allow Vaginal", "Vaginal", "Allow this slave to receive vaginal sex (if they have a vagina)."),
	SEX_ANAL(Colour.GENERIC_SEX, "Allow Anal", "Anal", "Allow this slave to receive anal sex."),
	SEX_NIPPLES(Colour.GENERIC_SEX, "Allow Nipple Penetration", "Nipples", "Allow this slave to receive penetrative nipple sex (if they have penetrable nipples)."),

	MILKING_NO_PREFERENCE(Colour.BASE_GREY, "No Preference", "NP", "Set this slave to work in any available milking room."),
	MILKING_INDUSTRIAL(Colour.GENERIC_MINOR_BAD, "Industrial Milking", "IN", "Set this slave to work in a milking room with industrial milkers."),
	MILKING_REGULAR(Colour.BASE_YELLOW_LIGHT, "Regular Milking", "RG", "Set this slave to work in a milking room with regular milkers."),
	MILKING_ARTISAN(Colour.GENERIC_MINOR_GOOD, "Artisan Milking", "AR", "Set this slave to work in a milking room with artisan milkers."),
	MILKING_MILK_DISABLE(Colour.BASE_YELLOW_LIGHT, "Forbid Milk", "DM", "Do not allow this slave's milk to be collected."),
	MILKING_CUM_DISABLE(Colour.BASE_BLUE_LIGHT, "Forbid Cum", "DC", "Do not allow this slave's cum to be collected."),
	MILKING_GIRLCUM_DISABLE(Colour.BASE_PINK_LIGHT, "Forbid Girlcum", "DG", "Do not allow this slave's girlcum to be collected."),
	
	TEST_SUBJECT_ALLOW_TRANSFORMATIONS_FEMALE(Colour.FEMININE, "Feminine Transformations", "TF (F)", "Allow Lilaya to perform feminine transformations on this slave."),
	TEST_SUBJECT_ALLOW_TRANSFORMATIONS_MALE(Colour.MASCULINE, "Masculine Transformations", "TF (M)", "Allow Lilaya to perform masculine transformations on this slave.");
	
	private Colour colour;
	private String name;
	private String tag;
	private String description;
	
	private SlaveJobSetting(Colour colour, String name, String tag, String description) {
		this.colour = colour;
		this.name = name;
		this.tag = tag;
		this.description = description;
	}

	public Colour getColour() {
		return colour;
	}

	public String getName() {
		return name;
	}
	
	public String getTag() {
		return tag;
	}

	public String getDescription() {
		return description;
	}
	
	public String getDailyEffectsDescription() {
		return null;
	}
	
	public String applyDailyEffects() {
		return null;
	}
	
}
