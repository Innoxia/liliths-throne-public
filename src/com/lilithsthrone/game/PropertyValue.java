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
	automaticDialogueCopy(false),

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
	sadisticSexContent(true),
	inflationContent(true),

	lipstickMarkingContent(true),
	facialHairContent(true),
	pubicHairContent(true),
	bodyHairContent(true),
	assHairContent(false),
	feminineBeardsContent(false),
	furryHairContent(true),
	scalyHairContent(false),
	
	nonConContent(true, true),
	incestContent(true, true),
	lactationContent(true, true),
	udderContent(true, true),
	urethralContent(false, true),
	analContent(true, true),
	footContent(true, true),
	armpitContent(true, true),
	nipplePenContent(true, true),
	gapeContent(true, true),
	feralContent(true, true),
	
	cumRegenerationContent(true),
	penetrationLimitations(true),
	elasticityAffectDepth(true), // Added in PR#1413
	
	futanariTesticles(true),
	bipedalCloaca(true),
	vestigialMultiBreasts(true),
	
	// I know a lot of people hate NTR, but this 'voluntary NTR' setting is just giving the player the option to give their companion to other people, rather than having their companion taken against their will.
	// For this reason, it's on by default (to give the player more options), but involutary NTR will always be off by default.
	voluntaryNTR(true),
	involuntaryNTR(false),

	spittingEnabled(true),
	opportunisticAttackers(true),
	
	// Game properties:
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
