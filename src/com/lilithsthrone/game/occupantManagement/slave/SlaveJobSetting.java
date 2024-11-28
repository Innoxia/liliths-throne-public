package com.lilithsthrone.game.occupantManagement.slave;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.87
 * @version 0.4.9.1
 * @author Innoxia
 */
public enum SlaveJobSetting {
	
	SEX_ORAL(PresetColour.GENERIC_SEX, "Allow Oral", "Oral", "Allow this slave to perform oral on others."),
	SEX_VAGINAL(PresetColour.GENERIC_SEX, "Allow Vaginal", "Vaginal", "Allow this slave to receive vaginal sex."),
	SEX_ANAL(PresetColour.GENERIC_SEX, "Allow Anal", "Anal", "Allow this slave to receive anal sex."),
	SEX_NIPPLES(PresetColour.GENERIC_SEX, "Allow Nipples", "Nipples", "Allow this slave to receive penetrative nipple sex."),

	
	MILKING_NO_PREFERENCE(PresetColour.BASE_GREY, "No Preference", "NP", "Set this slave to work in any available milking room."),
	MILKING_INDUSTRIAL(PresetColour.GENERIC_MINOR_BAD, "Industrial Milking", "IN", "Set this slave to work in a milking room with industrial milkers."),
	MILKING_REGULAR(PresetColour.BASE_YELLOW_LIGHT, "Regular Milking", "RG", "Set this slave to work in a milking room with regular milkers."),
	MILKING_ARTISAN(PresetColour.GENERIC_MINOR_GOOD, "Artisan Milking", "AR", "Set this slave to work in a milking room with artisan milkers."),
	MILKING_MILK(PresetColour.BASE_YELLOW_LIGHT, "Collect Milk", "CM", "Allow this slave's milk to be collected."),
	MILKING_MILK_CROTCH(PresetColour.BASE_YELLOW_LIGHT, "Collect Udder-milk", "CU", "Allow this slave's udders to be milked."),
	MILKING_CUM(PresetColour.BASE_BLUE_LIGHT, "Collect Cum", "CC", "Allow this slave's cum to be collected."),
	MILKING_GIRLCUM(PresetColour.BASE_PINK_LIGHT, "Collect Girlcum", "CG", "Allow this slave's girlcum to be collected."),
	MILKING_TEAR_HYMEN(PresetColour.BASE_PINK_DEEP, "Tear Hymen", "TH", "If this slave has an intact hymen, allow the 'pussy pump' to be inserted anyway (which will result in it being torn)."
			+ " If this permission is disabled, slaves with intact hymens will not insert 'pussy pumps', and so will not have their girlcum collected."),
	
	MILKING_MILK_AUTO_SELL(PresetColour.CURRENCY_GOLD, "Auto-sell Milk", "SM", "Set this slave's milk to be automatically sold instead of stored."),
	MILKING_MILK_CROTCH_AUTO_SELL(PresetColour.CURRENCY_GOLD, "Auto-sell Udder-milk", "SCM", "Set this slave's crotch-milk to be automatically sold instead of stored."),
	MILKING_CUM_AUTO_SELL(PresetColour.CURRENCY_GOLD, "Auto-sell Cum", "SC", "Set this slave's cum to be automatically sold instead of stored."),
	MILKING_GIRLCUM_AUTO_SELL(PresetColour.CURRENCY_GOLD, "Auto-sell Girlcum", "SG", "Set this slave's girlcum to be automatically sold instead of stored."),
	
	
	TEST_SUBJECT_ALLOW_TRANSFORMATIONS_FEMALE(PresetColour.FEMININE, "Feminine TF", "TF (F)", "Allow this slave to receive feminine transformations."),
	TEST_SUBJECT_ALLOW_TRANSFORMATIONS_MALE(PresetColour.MASCULINE, "Masculine TF", "TF (M)", "Allow this slave to receive masculine transformations."),

	DOLL_STATUE_ARTISTIC(PresetColour.BASE_TAN, "Artistic", "DPAR", "Order this doll to strike an artistic pose when acting as a statue."),
	DOLL_STATUE_ATTENTION(PresetColour.BASE_TAN, "At Attention", "DPAT", "Order this doll to stand at attention when acting as a statue."),
	DOLL_STATUE_STANDING_SPLIT(PresetColour.BASE_TAN, "Standing Split", "DPSS", "Order this doll to perform a standing split when acting as a statue."),
	DOLL_STATUE_MISSIONARY(PresetColour.BASE_TAN, "Missionary", "DPMI", "Order this doll to lie back in a missionary position when acting as a statue."),
	DOLL_STATUE_ALL_FOURS(PresetColour.BASE_TAN, "All Fours", "DPAF", "Order this doll to get down on all fours when acting as a statue."),
	DOLL_STATUE_SQUATTING(PresetColour.BASE_TAN, "Squatting", "DPSQ", "Order this doll to squat down with spread legs and hands behind their head when acting as a statue."),
	DOLL_STATUE_BRIDGE(PresetColour.BASE_TAN, "Bridge", "DPBR", "Order this doll to perform an acrobatic bridge when acting as a statue."),

	SECURITY_ENTRANCE_PRIORITY(PresetColour.BASE_GOLD, "Entrance Priority", "EP", "This slave will be chosen above others to be positioned at the entrance. (If multiple slaves have this permission, the one who gets there first will remain there.)"),
	SECURITY_ANSWER_DOOR(PresetColour.BASE_GREEN_LIGHT, "Answer Door", "AD", "If this slave is located at the entrance, they will answer the door instead of Rose."),
	
	BEDROOM_GREETING(PresetColour.GENERIC_MINOR_GOOD, "Greeting", "BG", "Instruct this slave to greet you whenever you enter your room."),
	BEDROOM_CLEAN(PresetColour.BASE_BLUE_LIGHT, "Cleaning", "BC", "Tell this slave to keep your room clean."),
	BEDROOM_WAKE_UP(PresetColour.BASE_YELLOW_LIGHT, "Waking", "BK", "Allow this slave to serve as your alarm clock, allowing them to rouse you from your slumber at the instructed time."),
	BEDROOM_HELP_WASH(PresetColour.BASE_BLUE, "Washing", "BW", "Have this slave assist you in the bathroom when you wash yourself. (This will override the slave's 'Cleanliness' permissions, as they will get washed with you.)"),
	
	BEDROOM_SLEEP_FLOOR(PresetColour.GENERIC_MINOR_BAD, "Sleep on Floor", "BSF", "Tell this slave that they must sleep on the floor of your room."),
	BEDROOM_SLEEP_ON_BED(PresetColour.BASE_PURPLE_LIGHT, "Sleep on Bed", "BSO", "Permit this slave to sleep on your bed, but not under the covers."),
	BEDROOM_SLEEP_IN_BED(PresetColour.BASE_PINK, "Sleep in Bed", "BSI", "Permit this slave to sleep in your bed beside you, under the covers."),
	
	
	SPA_SHOWERING(PresetColour.BASE_BLUE, "Showering", "SSH", "Have this slave assist you in the changing room's showers when you wash yourself. (This will override the slave's 'Cleanliness' permissions, as they will get washed with you.)"),
	SPA_BATHING(PresetColour.BASE_AQUA, "Bathing", "SBA", "Have this slave join you in the spa when you bathe. (This will override the slave's 'Cleanliness' permissions, as they will get washed with you.)"),
	SPA_STRIP_TO_BATHE(PresetColour.BASE_PERIWINKLE, "Bathe Naked", "SBN", "Have this slave strip off all of their clothes when joining you to bathe in the spa."),
	SPA_MASSAGE(PresetColour.BASE_BROWN, "Massage", "SMA", "Have this slave available for giving you a massage."),
	SPA_SAUNA(PresetColour.BASE_ROSE, "Sauna", "SSA", "Have this slave join you when you use the sauna (once you've constructed that extension)."),
	SPA_POOL(PresetColour.BASE_BLUE_LIGHT, "Swimming", "SPA", "Have this slave join you for a swim when you use the pool (once you've constructed that extension)."),

//	SPA_RECEPTION(PresetColour.BASE_BROWN, "Receptionist", "SRA", "Assign this slave to work at the spa's reception."),
//	SPA_ASSISTANT(PresetColour.BASE_GREEN_LIME, "Assistant", "SPA", "Assign this slave to work as one of the assistants within the spa proper."),
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
