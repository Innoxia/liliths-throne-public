package com.lilithsthrone.game;

/**
 * @since 0.2.2
 * @version 0.2.11
 * @author Innoxia
 */
public enum PropertyValue {
	
	debugMode(false),
	mapReveal(false),
	concealedSlotsReveal(false),
	
	artwork(true),
	thumbnail(true),
	
	lightTheme(false),
	overwriteWarning(true),
	fadeInText(false),
	calendarDisplay(true),
	twentyFourHourTime(true),
	tattooRemovalConfirmations(true),
	sillyMode(false),

	autoSexClothingManagement(true),
	
	ageContent(true),
	furryTailPenetrationContent(false),
	nonConContent(false),
	incestContent(false),
	forcedTransformationContent(false),
	inflationContent(true),
	facialHairContent(false),
	pubicHairContent(false),
	bodyHairContent(false),
	assHairContent(false),
	feminineBeardsContent(false),
	lactationContent(true),
	cumRegenerationContent(true),
	urethralContent(false),
	nipplePenContent(true),
	analContent(true),
	futanariTesticles(true),
	voluntaryNTR(false),
	involuntaryNTR(false),

	levelUpHightlight(false),
	newWeaponDiscovered(false),
	newClothingDiscovered(false),
	newItemDiscovered(false),
	newRaceDiscovered(false);
	
	
	private boolean defaultValue;

	private PropertyValue(boolean defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public boolean getDefaultValue() {
		return defaultValue;
	}
}
