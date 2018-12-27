package com.lilithsthrone.game.occupantManagement;

import java.util.List;

import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.87
 * @version 0.2.5
 * @author Innoxia
 */
public enum SlavePermission {
	
	GENERAL(Colour.TRANSFORMATION_GENERIC,
			"General",
			Util.newArrayListOfValues(
					SlavePermissionSetting.GENERAL_SILENCE,
					SlavePermissionSetting.GENERAL_CRAWLING,
					SlavePermissionSetting.GENERAL_HOUSE_FREEDOM,
					SlavePermissionSetting.GENERAL_OUTSIDE_FREEDOM),
			false),

	SEX(Colour.GENERIC_SEX,
			"Sex",
			Util.newArrayListOfValues(
					SlavePermissionSetting.SEX_MASTURBATE,
					SlavePermissionSetting.SEX_INITIATE_SLAVES,
					SlavePermissionSetting.SEX_INITIATE_PLAYER,
					SlavePermissionSetting.SEX_RECEIVE_SLAVES,
					SlavePermissionSetting.SEX_IMPREGNATED,
					SlavePermissionSetting.SEX_IMPREGNATE),
			false),

	PREGNANCY(Colour.ATTRIBUTE_LUST,
			"Pregnancy",
			Util.newArrayListOfValues(
					SlavePermissionSetting.PREGNANCY_PROMISCUITY_PILLS,
					SlavePermissionSetting.PREGNANCY_NO_PILLS,
					SlavePermissionSetting.PREGNANCY_VIXENS_VIRILITY),
			true),
	
	DIET(Colour.BODY_SIZE_TWO,
			"Diet",
			Util.newArrayListOfValues(
					SlavePermissionSetting.FOOD_DIET_EXTREME,
					SlavePermissionSetting.FOOD_DIET,
					SlavePermissionSetting.FOOD_NORMAL,
					SlavePermissionSetting.FOOD_PLUS,
					SlavePermissionSetting.FOOD_LAVISH),
			true),

	EXERCISE(Colour.MUSCLE_TWO,
			"Exercise",
			Util.newArrayListOfValues(
					SlavePermissionSetting.EXERCISE_FORBIDDEN,
					SlavePermissionSetting.EXERCISE_REST,
					SlavePermissionSetting.EXERCISE_NORMAL,
					SlavePermissionSetting.EXERCISE_TRAINING,
					SlavePermissionSetting.EXERCISE_BODY_BUILDING),
			true),
	
	CLEANLINESS(Colour.BASE_AQUA,
			"Cleanliness",
			Util.newArrayListOfValues(
					SlavePermissionSetting.CLEANLINESS_WASH_CLOTHES,
					SlavePermissionSetting.CLEANLINESS_WASH_BODY),
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
