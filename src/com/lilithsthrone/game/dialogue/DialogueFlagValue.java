package com.lilithsthrone.game.dialogue;

import java.util.List;

import com.lilithsthrone.utils.Util;

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
	
	nyanTalkedTo(true),
	nyanComplimented(true),
	nyanFlirtedWith(true),
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
	
	
	// Rat warrens:
	
	ratWarrensEntry,
	ratWarrensEntryWhore,
	ratWarrensHostile,
	ratWarrensEntranceGuardsFight,
	ratWarrensSeenMilkers,
	ratWarrensMilkersBackground,
	ratWarrensSilenceIntroduced,
	
	ratWarrensClearedLeft,
	ratWarrensClearedCentre,
	ratWarrensClearedRight,
	
	ratWarrensLootedDiceDen,
	
	vengarThreatened,
	vengarPersuaded,
	vengarSeduced,
	ratWarrensRaid,
	ratWarrensUsedResonanceStone,

	vengarCaptiveRoomCleaned(true),
	vengarCaptiveVengarSatisfied(true),
	vengarCaptiveShadowSatisfied(true),
	vengarCaptiveSilenceSatisfied(true),
	vengarCaptiveCompanionGivenBirth(true),
	vengarCaptiveGangBanged(true),
	
	ratWarrensCaptiveAttemptingEscape,
	ratWarrensCaptiveEscaped,
//	ratWarrensCaptiveTransformationsStarted,
	
	ratWarrensCaptiveFeminine,
	ratWarrensCaptiveFuta,
	ratWarrensCaptiveMasculine,
	ratWarrensCaptiveSissy,
	
	murkMaster,
	murkSir,
	murkDaddy,
	
	ratWarrensCaptiveFuckedByMurk,
	ratWarrensCaptiveCompanionFuckedByMurk,
	
	ratWarrensCaptiveCalledOut, // Reset to false when sleep
	
	ratWarrensCaptiveCompanionGivenBirth(true),
	ratWarrensCaptiveOwnerSex(true),
	ratWarrensCaptiveOwnerCompanionSex(true),
	ratWarrensCaptiveDailyTransformed(true)
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
	
	public static List<DialogueFlagValue> getRatWarrensFlags() {
		return Util.newArrayListOfValues(
				ratWarrensEntry,
				ratWarrensEntryWhore,
				ratWarrensHostile,
				ratWarrensEntranceGuardsFight,
				ratWarrensSeenMilkers,
				ratWarrensMilkersBackground,
				ratWarrensSilenceIntroduced,
				
				ratWarrensClearedLeft,
				ratWarrensClearedCentre,
				ratWarrensClearedRight,
				
				ratWarrensLootedDiceDen,
				
				vengarThreatened,
				vengarPersuaded,
				vengarSeduced,
				ratWarrensRaid,
				ratWarrensUsedResonanceStone,

				vengarCaptiveRoomCleaned,
				vengarCaptiveVengarSatisfied,
				vengarCaptiveShadowSatisfied,
				vengarCaptiveSilenceSatisfied,
				vengarCaptiveCompanionGivenBirth,
				vengarCaptiveGangBanged,
				
				ratWarrensCaptiveAttemptingEscape,
				ratWarrensCaptiveEscaped,
				
				ratWarrensCaptiveFeminine,
				ratWarrensCaptiveFuta,
				ratWarrensCaptiveMasculine,
				ratWarrensCaptiveSissy,
				
				murkMaster,
				murkSir,
				murkDaddy,
				
				ratWarrensCaptiveFuckedByMurk,
				ratWarrensCaptiveCompanionFuckedByMurk,
				
				ratWarrensCaptiveCalledOut,
				
				ratWarrensCaptiveCompanionGivenBirth,
				ratWarrensCaptiveOwnerSex,
				ratWarrensCaptiveOwnerCompanionSex,
				ratWarrensCaptiveDailyTransformed);
	}
	
}
