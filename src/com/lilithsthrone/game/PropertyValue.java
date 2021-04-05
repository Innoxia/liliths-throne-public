package com.lilithsthrone.game;

/**
 * @since 0.2.2
 * @version 0.3.8.9
 * @author Innoxia
 */
public enum PropertyValue {
	
	debugMode(false),
	mapReveal(false),
	concealedSlotsReveal(false),
	allStickersUnlocked(false),
	
	enchantmentLimits(true),
	levelDrain(true),
	
	artwork(true),
	thumbnail(true),
	
	lightTheme(false),
	overwriteWarning(true),
	fadeInText(false),
	calendarDisplay(true),
	tattooRemovalConfirmations(true),
	sillyMode(false),
	weatherInterruptions(true),

	autoLocale(true),
	metricSizes(true),
	metricFluids(true),
	metricWeights(true),
	twentyFourHourTime(true),
	internationalDate(true),

	autoSexStrip(false),
	autoSexClothingManagement(true),

	companionContent(false),

	badEndContent(true),
	ageContent(true),
	furryTailPenetrationContent(false),
	sadisticSexContent(false),
	inflationContent(true),

	lipstickMarkingContent(true),
	facialHairContent(true),
	pubicHairContent(true),
	bodyHairContent(false),
	assHairContent(false),
	feminineBeardsContent(false),
	furryHairContent(false),
	scalyHairContent(false),
	
	nonConContent(false, true),
	incestContent(false, true),
	lactationContent(true, true),
	urethralContent(false, true),
	analContent(true, true),
	footContent(true, true),
	nipplePenContent(true, true),
	gapeContent(true, true),
	
	cumRegenerationContent(true),
	penetrationLimitations(true),
	elasticityAffectDepth(false), // Added in PR#1413
	
	futanariTesticles(true),
	bipedalCloaca(true),
	
	// I know a lot of people hate NTR, but this 'voluntary NTR' setting is just giving the player the option to give their companion to other people, rather than having their companion taken against their will.
	// For this reason, it's on by default (to give the player more options), but involutary NTR will always be off by default.
	voluntaryNTR(true),
	involuntaryNTR(false),

	spittingEnabled(true),
	opportunisticAttackers(false),

	levelUpHightlight(false),
	sharedEncyclopedia(false),
	newWeaponDiscovered(false),
	newClothingDiscovered(false),
	newItemDiscovered(false),
	newRaceDiscovered(false);

	private boolean defaultValue;
	private boolean fetishRelated;

	private PropertyValue(boolean defaultValue) {
		this(defaultValue, false);
	}
	
	private PropertyValue(boolean defaultValue, boolean fetishRelated) {
		this.defaultValue = defaultValue;
		this.fetishRelated = fetishRelated;
	}
	
	public boolean getDefaultValue() {
		return defaultValue;
	}
	
	public boolean isFetishRelated() {
		return fetishRelated;
	}
}
