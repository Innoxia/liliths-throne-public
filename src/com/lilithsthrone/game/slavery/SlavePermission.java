package com.lilithsthrone.game.slavery;

import java.util.List;

import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.87
 * @version 0.2.5
 * @author Innoxia
 */
public enum SlavePermission {
	
	GENERAL(Colour.TRANSFORMATION_GENERIC,
			"General",
			Util.newArrayListOfValues(
					new ListValue<>(SlavePermissionSetting.GENERAL_SILENCE),
					new ListValue<>(SlavePermissionSetting.GENERAL_CRAWLING),
					new ListValue<>(SlavePermissionSetting.GENERAL_HOUSE_FREEDOM),
					new ListValue<>(SlavePermissionSetting.GENERAL_OUTSIDE_FREEDOM)),
			false),

	SEX(Colour.GENERIC_SEX,
			"Sex",
			Util.newArrayListOfValues(
					new ListValue<>(SlavePermissionSetting.SEX_MASTURBATE),
					new ListValue<>(SlavePermissionSetting.SEX_INITIATE_SLAVES),
					new ListValue<>(SlavePermissionSetting.SEX_INITIATE_PLAYER),
					new ListValue<>(SlavePermissionSetting.SEX_RECEIVE_SLAVES),
					new ListValue<>(SlavePermissionSetting.SEX_IMPREGNATED),
					new ListValue<>(SlavePermissionSetting.SEX_IMPREGNATE)),
			false),

	PREGNANCY(Colour.ATTRIBUTE_LUST,
			"Pregnancy",
			Util.newArrayListOfValues(
					new ListValue<>(SlavePermissionSetting.PREGNANCY_PROMISCUITY_PILLS),
					new ListValue<>(SlavePermissionSetting.PREGNANCY_NO_PILLS),
					new ListValue<>(SlavePermissionSetting.PREGNANCY_VIXENS_VIRILITY)),
			true),
	
	DIET(Colour.BODY_SIZE_TWO,
			"Diet",
			Util.newArrayListOfValues(
					new ListValue<>(SlavePermissionSetting.FOOD_DIET_EXTREME),
					new ListValue<>(SlavePermissionSetting.FOOD_DIET),
					new ListValue<>(SlavePermissionSetting.FOOD_NORMAL),
					new ListValue<>(SlavePermissionSetting.FOOD_PLUS),
					new ListValue<>(SlavePermissionSetting.FOOD_LAVISH)),
			true),

	EXERCISE(Colour.MUSCLE_TWO,
			"Exercise",
			Util.newArrayListOfValues(
					new ListValue<>(SlavePermissionSetting.EXERCISE_FORBIDDEN),
					new ListValue<>(SlavePermissionSetting.EXERCISE_REST),
					new ListValue<>(SlavePermissionSetting.EXERCISE_NORMAL),
					new ListValue<>(SlavePermissionSetting.EXERCISE_TRAINING),
					new ListValue<>(SlavePermissionSetting.EXERCISE_BODY_BUILDING)),
			true),
	
	CLEANLINESS(Colour.BASE_AQUA,
			"Cleanliness",
			Util.newArrayListOfValues(
					new ListValue<>(SlavePermissionSetting.CLEANLINESS_WASH_CLOTHES),
					new ListValue<>(SlavePermissionSetting.CLEANLINESS_WASH_BODY)),
			false);
	
	private Colour colour;
	private String name;
	private List<SlavePermissionSetting> settings;
	private boolean mutuallyExclusiveSettings;
	
	private SlavePermission(Colour colour, String name, List<SlavePermissionSetting> settings, boolean mutuallyExclusiveSettings) {
		this.colour = colour;
		this.name = name;
		this.settings = settings;
		this.mutuallyExclusiveSettings = mutuallyExclusiveSettings;
	}

	public Colour getColour() {
		return colour;
	}

	public String getName() {
		return name;
	}

	public List<SlavePermissionSetting> getSettings() {
		return settings;
	}

	public boolean isMutuallyExclusiveSettings() {
		return mutuallyExclusiveSettings;
	}
	
}
