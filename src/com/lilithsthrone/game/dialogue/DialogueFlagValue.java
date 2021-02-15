package com.lilithsthrone.game.dialogue;

/**
 * @since 0.1.89
 * @version 0.3.8.9
 * @author Innoxia
 */
public enum DialogueFlagValue {
	
	quickTrade,
	stormTextUpdateRequired,
	hasSnowedThisWinter,
	
	
	// Essence reactions:
	jinxedClothingDiscovered,
	essencePostCombatDiscovered,
	essenceOrgasmDiscovered,
	essenceBottledDiscovered,

	
	// Misc.:
	foundHappiness,
	visitedSubmission,
	mommyFound,
	
	daddyFound,
	rudeToDaddy,
	flirtingWithDaddy,
	talkedWithDaddy,
	daddySendingReward,
	
	coveringChangeListenersRequired, // Set to false on every Response preparsing, and only set to true in getKatesDivCoveringsNew(). Used for setting up listeners in MainControllerInitMethod.

	badEnd, // When the game is in a state of a bad end (meaning that the player is in an inescapable gameplay loop)
	
	// Gym:
	gymIntroduced,
	gymHadTour,
	gymIsMember,
	
	
	// Introductions:
	angelIntroduced,
	angelsOfficeIntroduced,
	bunnyIntroduced,
	loppyIntroduced,
	ashleyIntroduced,
	ralphIntroduced,
	nyanIntroduced,
	kateIntroduced,
	vickyIntroduced,
	vanessaIntroduced,

	roxyIntroduced,
	axelIntroduced,
	eponaIntroduced,
	elizabethIntroduced,
	vengarIntroduced,
	murkIntroduced,
	
	
	// Red-light district:
	prostitutionLicenseObtained,

	
	// City hall:
	vanessaHelped,
	vanessaDailyHelped,
	vanessaDailyMassage,
	vanessaMassaged,
	vanessaFucked,
	vanessaAskedAboutCatalogue,
	vanessaAskedAboutSolitary,
	
	
	// Shopping arcade:
	ralphAskedAboutHundredKisses,
	ralphDailyBred(true),
	
	nyanTalkedTo(true),
	nyanComplimented(true),
	nyanFlirtedWith(true),
	nyanHeadPatted(true),
	nyanKissed(true),
	nyanMakeOut(true),
	nyanSex(true),
	nyanGift(true),
	
	supplierDepotDoorUnlocked,
	suppliersEncountered,
	suppliersTriedConvincing,
	
	ashleySexToysDiscovered,
	ashleyAttitude,
	
	reactedToKatePregnancy,
	
	// Lilaya's Home:
	knowsDate,
	lilayaDateTalk,
	auntHomeJustEntered,
	hadSexWithLilaya,
	lilayaCondomBroke,
	reactedToPregnancyLilaya,
	waitingOnLilayaPregnancyResults,
	waitingOnLilayaBirthNews,
	essenceExtractionKnown,
	roseToldOnYou,
	lilayaReactedToPlayerAsDemon,
	lilayaHug(true),
	
	readBook1,
	readBook2,
	readBook3,
	readBook4,
	
	arthursPackageObtained,
	
	givenLilayaPresent1,
	givenLilayaPresent2,
	givenLilayaPresent3,
	
	// Enforcer HQ:
	braxEncountered,
	accessToEnforcerHQ,
	braxTransformedPlayer,
	seenBraxAfterQuest,
	feminisedBrax,
	bimbofiedBrax,
	candiHarpyTransformation,
	
	// Harpy Nests:
	hasHarpyNestAccess,
	bimboEncountered,
	bimboPacified,
	dominantEncountered,
	dominantPacified,
	nymphoEncountered,
	nymphoPacified,
	punishedByHelena,
	scarlettRelaxed(true),
	
	// Slaver Alley:
	finchIntroduced,
	seanIntroduced,
	seanSeenBrax,
	statueTruthRevealed,
	slaverAlleyTalked,
	slaverAlleyTalkedBraxReveal,
	slaverAlleyTalkedFreedSlaves,
	slaverAlleyComplained,
	slaverAlleyVisitedHiddenAlleyway,
	slaverAlleyBribed,
	slaverAlleyTookPlace,
	slaverAlleyCompanionInStocks,
	slaverAlleyAcceptedDeal,
	slaverAlleyCompanionAcceptedDeal,
	slaverAlleyTwoPartners,
	slaverAlleySlavesFreed, // Reset every day at midnight (as part of stocks reset method)
	
	// Wes:
	wesQuestLilayaHelp,
	wesQuestMet(true),
	wesQuestRefused,

	wesQuestTalked(true),
	wesQuestTalkedAlt(true),
	wesQuestFlirted(true),
	wesQuestSex(true),
	
	// Helena (romance quest):
	helenaCheapPaint,
	helenaGoneHome(true),
	scarlettGoneHome(true),
	helenaScarlettToldToReturn,
	helenaScarlettSleepoverSex,

	// Helena (post-romance quest):
	helenaSlutSeen,
	helenaShopTalkedTo(true),
	helenaShopFucked(true),
	helenaNestTalkedTo(true),
	helenaNestFucked(true),
	helenaShopScarlettTalkedTo(true),
	helenaShopScarlettCounterOral(true),
	helenaShopScarlettCafe(true),
	helenaShopScarlettCafeRevealed,
	helenaShopScarlettExtraTransformationDiscussed,
	helenaShopScarlettExtraTransformationApplied,
	helenaShopScarlettExtraTransformationHelenaReacted,
	
	helenaDateApartmentSeen,
	helenaDateFirstDateComplete,
	helenaDateRomanticSetup,
	helenaDateRomanticSetupEatenOut,
	helenaGift(true),
	helenaDateSexLifeTalk,
	helenaDateVirginityTalk,
	helenaScarlettThreesome,
	
	helenaBedroomFromNest,
	
	// Natalya:
	playerSubmittedToNatalya,
	natalyaDemandedFacial,
	playerReceivedNatalyaFacial,
	
	natalyaVisited,
	natalyaInterviewOffered,
	natalyaBusy(true),
	natalyaDailySexAsSub(true),
	natalyaDailySexAsDom(true),
	
	
	// Zaranix:
	zaranixDiscoveredHome,
	zaranixMaidsHostile,
	zaranixKnockedOnDoor,
	zaranixKickedDownDoor,
	
	zaranixAmberSubdued,
	zaranixKatherineSubdued,
	zaranixKellySubdued,
	
	amberRepeatEncountered,
	katherineRepeatEncountered,
	kellyRepeatEncountered,
	zaranixRepeatEncountered,
	
	// Lumi:
	lumiMet,
	lumiDisabled,
	lumiPromisedDinner,
	
	// Slime Queen's Tower:
	slimeGuardsIntroduced,
	slimeGuardsBluffed,
	slimeGuardsDefeated,
	slimeRoyalGuardIntroduced,
	slimeRoyalGuardDefeated,
	slimeRoyalGuardDefeatReacted,
	slimeQueenHelped,
	slimeQueenConvinced,
	slimeQueenForced,
	
	// Gambling Den:
	axelMentionedVengar,
	axelExplainedVengar,
	axelToldSubmit,
	
	axelSissified,
	axelFeminised,
	axelClothingFeminine,
	axelClothingWhore,
	axelClothingMaid,
	
	roxyAddicted,
	roxyVengarOwnerIntroduced,
	playedPregnancyRouletteAsMother,
	playedPregnancyRouletteAsBreeder,
	
	eponaMurkOwnerIntroduced,
	eponaMurkSeen,
	eponaMurkSubmitted,
	
	// Nightlife:
	julesIntroduced,
	suckedJulesCock,
	passedJules,
	kalahariIntroduced,
	kalahariWantsSex,
	krugerIntroduced,
	
	// Submission:
	claireAskedTeleportation,
	claireWarning,
	clairePadsInvestigated,
	claireEnclosureEscaped,
	claireObtainedLightningGlobe,
	claireAskedWarehouseEscape,
	
	impCitadelEncountered,
	impCitadelArcanistEncountered,
	impCitadelArcanistAcceptedTF,
	impCitadelTreasurySearched,
	impCitadelLaboratorySearched,

	impCitadelPrisonerMale,
	impCitadelPrisonerFemale,
	impCitadelPrisonerAlpha,
	
	impFortressAlphaGuardsPacified,
	impFortressAlphaBossEncountered,
	impFortressAlphaPacified,
	impFortressAlphaDefeated,
	
	impFortressFemalesGuardsPacified,
	impFortressFemalesBossEncountered,
	impFortressFemalesPacified,
	impFortressFemalesDefeated,
	
	impFortressMalesGuardsPacified,
	impFortressMalesBossEncountered,
	impFortressMalesPacified,
	impFortressMalesDefeated,
	
	impFortressDemonBossEncountered,
	impFortressDemonDefeated,
	impFortressDemonImpsDefeated,
	
	elizabethAskedAboutUniforms,
	elizabethAskedAboutSurname,
	
	lyssiethQuestionAsked1,
	lyssiethQuestionAsked2,
	lyssiethQuestionAsked3,
	lyssiethQuestionAsked4,
	lyssiethQuestionAsked5,

	meraxisRepeatDemonTF,
	
        rebelBaseInsaneSurvivorEncountered,
        rebelBaseRoxyDeal,
        rebelBaseRoxyDealComplete,
	
	// Rat warrens:
	
	ratWarrensEntry,
	ratWarrensEntryWhore,
	ratWarrensHostile,
	ratWarrensEntranceGuardsFight,
	ratWarrensSeenMilkers,
	ratWarrensMilkersBackground,
	ratWarrensMilkersFreeAttempt,
	ratWarrensSilenceIntroduced,
	
	ratWarrensClearedLeft,
	ratWarrensClearedCentre,
	ratWarrensClearedRight,
	
	ratWarrensLootedDiceDen,
	
	vengarThreatened,
	vengarPersuaded,
	vengarSeduced,
	ratWarrensUsedResonanceStone,

	vengarCaptiveRoomCleaned(true),
	vengarCaptiveVengarSatisfied(true),
	vengarCaptiveShadowSatisfied(true),
	vengarCaptiveSilenceSatisfied(true),
	vengarCaptiveCompanionGivenBirth(true),
	vengarCaptiveGangBanged(true),

	ratWarrensCaptiveInitialNightDescription,
	ratWarrensCaptiveAttemptingEscape,
	ratWarrensCaptiveEscaped,
//	ratWarrensCaptiveTransformationsStarted,
	
	ratWarrensCaptiveFeminine,
	ratWarrensCaptiveFuta,
	ratWarrensCaptiveMasculine,
	ratWarrensCaptiveSissy,

	murkCaptiveBlowjob,
	murkMaster,
	
	ratWarrensCaptiveCalledOut,
	ratWarrensCaptiveWashed,
	
	ratWarrensCaptiveCompanionGivenBirth(true),
	ratWarrensCaptiveOwnerSex(true),
	ratWarrensCaptiveOwnerCompanionSex(true),
	ratWarrensCaptiveDailyTransformed(true),

	murkLectured(true),
	murkSpanked(true),
	;
	
	boolean dailyReset;

	private DialogueFlagValue() {
		this(false);
	}
	
	private DialogueFlagValue(boolean dailyReset) {
		this.dailyReset = dailyReset;
	}

	public boolean isDailyReset() {
		return dailyReset;
	}
	
}
