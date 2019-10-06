package com.lilithsthrone.game.occupantManagement;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.87
 * @version 0.3.5.1
 * @author Innoxia
 */
public enum SlaveJobSetting {
	
	SEX_ORAL(Colour.GENERIC_SEX, "Allow Oral", "Oral", "Allow this slave to perform oral on others."),
	SEX_VAGINAL(Colour.GENERIC_SEX, "Allow Vaginal", "Vaginal", "Allow this slave to receive vaginal sex."),
	SEX_ANAL(Colour.GENERIC_SEX, "Allow Anal", "Anal", "Allow this slave to receive anal sex."),
	SEX_NIPPLES(Colour.GENERIC_SEX, "Allow Nipples", "Nipples", "Allow this slave to receive penetrative nipple sex."),

	
	MILKING_NO_PREFERENCE(Colour.BASE_GREY, "No Preference", "NP", "Set this slave to work in any available milking room."),
	MILKING_INDUSTRIAL(Colour.GENERIC_MINOR_BAD, "Industrial Milking", "IN", "Set this slave to work in a milking room with industrial milkers."),
	MILKING_REGULAR(Colour.BASE_YELLOW_LIGHT, "Regular Milking", "RG", "Set this slave to work in a milking room with regular milkers."),
	MILKING_ARTISAN(Colour.GENERIC_MINOR_GOOD, "Artisan Milking", "AR", "Set this slave to work in a milking room with artisan milkers."),
	MILKING_MILK_DISABLE(Colour.BASE_YELLOW_LIGHT, "Forbid Milk", "DM", "Do not allow this slave's milk to be collected."),
	MILKING_MILK_CROTCH_DISABLE(Colour.BASE_YELLOW_LIGHT, "Forbid Udder-milk", "DU", "Do not allow this slave's udders to be milked."),
	MILKING_CUM_DISABLE(Colour.BASE_BLUE_LIGHT, "Forbid Cum", "DC", "Do not allow this slave's cum to be collected."),
	MILKING_GIRLCUM_DISABLE(Colour.BASE_PINK_LIGHT, "Forbid Girlcum", "DG", "Do not allow this slave's girlcum to be collected."),
	
	MILKING_MILK_AUTO_SELL(Colour.CURRENCY_GOLD, "Auto-sell Milk", "SM", "Set this slave's milk to be automatically sold instead of stored."),
	MILKING_MILK_CROTCH_AUTO_SELL(Colour.CURRENCY_GOLD, "Auto-sell Udder-milk", "SCM", "Set this slave's crotch-milk to be automatically sold instead of stored."),
	MILKING_CUM_AUTO_SELL(Colour.CURRENCY_GOLD, "Auto-sell Cum", "SC", "Set this slave's cum to be automatically sold instead of stored."),
	MILKING_GIRLCUM_AUTO_SELL(Colour.CURRENCY_GOLD, "Auto-sell Girlcum", "SG", "Set this slave's girlcum to be automatically sold instead of stored."),
	
	
	TEST_SUBJECT_ALLOW_TRANSFORMATIONS_FEMALE(Colour.FEMININE, "Feminine TF", "TF (F)", "Allow this slave to receive feminine transformations."),
	TEST_SUBJECT_ALLOW_TRANSFORMATIONS_MALE(Colour.MASCULINE, "Masculine TF", "TF (M)", "Allow this slave to receive masculine transformations."),
	
	BEDROOM_GREETING(Colour.GENERIC_MINOR_GOOD, "Greeting", "BG", "Instruct this slave to greet you whenever you enter your room."),
	BEDROOM_CLEAN(Colour.BASE_BLUE_LIGHT, "Cleaning", "BC", "Tell this slave to keep your room clean."),
	BEDROOM_WAKE_UP(Colour.BASE_YELLOW_LIGHT, "Waking", "BK", "Allow this slave to serve as your alarm clock, allowing them to rouse you from your slumber at the instructed time."),
	BEDROOM_HELP_WASH(Colour.BASE_AQUA, "Washing", "BW", "Have this slave assist you in the bathroom when you wash yourself. (This will override the slave's 'Cleanliness' permissions, as they will get washed with you.)"),
	
	BEDROOM_SLEEP_FLOOR(Colour.GENERIC_MINOR_BAD, "Sleep on floor", "BSF", "Tell this slave that they must sleep on the floor of your room."),
	BEDROOM_SLEEP_ON_BED(Colour.BASE_PURPLE_LIGHT, "Sleep on bed", "BSO", "Permit this slave to sleep on your bed, but not under the covers."),
	BEDROOM_SLEEP_IN_BED(Colour.BASE_PINK, "Sleep in bed", "BSI", "Permit this slave to sleep in your bed beside you, under the covers."),
	
	
	;
	
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
