package com.lilithsthrone.game.character.fetishes;

import java.util.Arrays;
import java.util.EnumSet;

public enum ContentFlag {
	ANAL,
	ARMPITS,
	CROTCHBOOBS,
	CUM_REGENERATION,
	FEET,
	FERAL,
	GAPE,
	INCEST,
	INFLATION,
	LACTATION,
	NIPPLE_PENETRATION,
	NON_CON,
	PENETRATION_SIZE_LIMITATIONS, //?
	SADISTIC_SEX,
	URETHRAL_INSERTION;

	public static EnumSet<ContentFlag> NONE = EnumSet.noneOf(ContentFlag.class);
	public static EnumSet<ContentFlag> ALL = EnumSet.allOf(ContentFlag.class);
	
	public static EnumSet<ContentFlag> allOf(ContentFlag ...args) {
		return EnumSet.copyOf(Arrays.asList(args));
	}
}
