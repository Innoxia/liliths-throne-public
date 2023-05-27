package com.lilithsthrone.game.dialogue;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.utils.Util;

/**
 * Yes, this is horrible. I refactored it into this to easily allow modded flags.
 * 
 * @since 0.1.89
 * @version 0.4
 * @author Innoxia
 */
public class DialogueFlagValue {
	
	// Main quest:
	public static AbstractDialogueFlagValue firstReactionLiberate = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue firstReactionUsurp = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue firstReactionJoin = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue firstReactionNothing = new AbstractDialogueFlagValue();
	
	
	// Essence reactions:
	public static AbstractDialogueFlagValue jinxedClothingDiscovered = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue essencePostCombatDiscovered = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue essenceOrgasmDiscovered = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue essenceBottledDiscovered = new AbstractDialogueFlagValue();

	
	// Misc.:
	public static AbstractDialogueFlagValue quickTrade = new AbstractDialogueFlagValue();
	/** This is reset to false every time a transaction occurs, and should only be set to true in an NPC's applyItemTransactionEffects() method to prevent their default getTraderDescription() text from being displayed. */
	public static AbstractDialogueFlagValue removeTraderDescription = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue stormTextUpdateRequired = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue hasSnowedThisWinter = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue foundHappiness = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue visitedSubmission = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue mommyFound = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue daddyFound = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue rudeToDaddy = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue flirtingWithDaddy = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue talkedWithDaddy = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue daddySendingReward = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue coveringChangeListenersRequired = new AbstractDialogueFlagValue(); // Set to false on every Response preparsing, and only set to true in getKatesDivCoveringsNew(). Used for setting up listeners in MainControllerInitMethod.

	public static AbstractDialogueFlagValue badEnd = new AbstractDialogueFlagValue(); // When the game is in a state of a bad end (meaning that the player is in an inescapable gameplay loop)
	
	// Introductions:
	public static AbstractDialogueFlagValue angelIntroduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue angelsOfficeIntroduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue bunnyIntroduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue loppyIntroduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue ashleyIntroduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue ralphIntroduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue nyanIntroduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue nyanmumIntroduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue kateIntroduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue vickyIntroduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue vanessaIntroduced = new AbstractDialogueFlagValue();

	public static AbstractDialogueFlagValue roxyIntroduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue axelIntroduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue eponaIntroduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue elizabethIntroduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue vengarIntroduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue murkIntroduced = new AbstractDialogueFlagValue();
	
	
	// Red-light district:
	public static AbstractDialogueFlagValue prostitutionLicenseObtained = new AbstractDialogueFlagValue();

	
	// City hall:
	public static AbstractDialogueFlagValue cityHallLodgerBoardSeen = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue vanessaHelped = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue vanessaDailyHelped = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue vanessaDailyMassage = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue vanessaMassaged = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue vanessaFucked = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue vanessaAskedAboutCatalogue = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue vanessaAskedAboutSolitary = new AbstractDialogueFlagValue();
	
	
	// Shopping arcade:
	public static AbstractDialogueFlagValue ralphAskedAboutHundredKisses = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue ralphDailyBred = new AbstractDialogueFlagValue(true);
	
	public static AbstractDialogueFlagValue ashleySexToysDiscovered = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue ashleyAttitude = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue reactedToKatePregnancy = new AbstractDialogueFlagValue();
	
	// Nyan:
	public static AbstractDialogueFlagValue nyanHiding = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue nyanDating = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue nyanFirstKissed = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue nyanRestaurantDateRequested = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue nyanRestaurantDateCompleted = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue nyanmumInterviewPassed = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue nyanmumDateCompleted = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue nyanWeekendDated = new AbstractDialogueFlagValue(); // Reset every Monday in Nyan's dailyUpdate() method
	public static AbstractDialogueFlagValue nyanmumGirlfriend = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue nyanApologised = new AbstractDialogueFlagValue(); // Apologise for her mum's behaviour
	public static AbstractDialogueFlagValue nyanCreampied = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue nyanmumCreampied = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue nyanAnalTalk = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue nyanmumAnalTalk = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue nyanTalkedTo = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue nyanComplimented = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue nyanFlirtedWith = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue nyanHeadPatted = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue nyanKissed = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue nyanTummyRubbed = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue nyanWalked = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue nyanMakeOut = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue nyanSex = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue nyanGift = new AbstractDialogueFlagValue(true);
	
	// Lilaya's Home:
	public static AbstractDialogueFlagValue knowsDate = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue lilayaDateTalk = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue auntHomeJustEntered = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue hadSexWithLilaya = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue lilayaCondomBroke = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue lilayaAmazonsSecretImpregnation = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue reactedToPregnancyLilaya = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue waitingOnLilayaPregnancyResults = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue waitingOnLilayaBirthNews = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue essenceExtractionKnown = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue roseToldOnYou = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue lilayaReactedToPlayerAsDemon = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue lilayaHug = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue obtainedScientistClothing = new AbstractDialogueFlagValue();

	public static AbstractDialogueFlagValue lilayaGardenPickRose = new AbstractDialogueFlagValue(true);
	
	public static AbstractDialogueFlagValue readBook1 = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue readBook2 = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue readBook3 = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue readBook4 = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue readBookSlavery = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue arthursPackageObtained = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue givenLilayaPresent1 = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue givenLilayaPresent2 = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue givenLilayaPresent3 = new AbstractDialogueFlagValue();
	
	// Enforcer HQ:
	public static AbstractDialogueFlagValue braxEncountered = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue accessToEnforcerHQ = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue braxTransformedPlayer = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue seenBraxAfterQuest = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue feminisedBrax = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue bimbofiedBrax = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue candiHarpyTransformation = new AbstractDialogueFlagValue();
	
	// Harpy Nests:
	public static AbstractDialogueFlagValue hasHarpyNestAccess = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue bimboEncountered = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue bimboPacified = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue dominantEncountered = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue dominantPacified = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue nymphoEncountered = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue nymphoPacified = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue punishedByHelena = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue scarlettRelaxed = new AbstractDialogueFlagValue(true);
	
	// Slaver Alley:
	public static AbstractDialogueFlagValue finchIntroduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue finchFreedomTalk = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue seanIntroduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue seanSeenBrax = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue statueTruthRevealed = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slaverAlleyTalked = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slaverAlleyTalkedBraxReveal = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slaverAlleyTalkedFreedSlaves = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slaverAlleyComplained = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slaverAlleyVisitedHiddenAlleyway = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slaverAlleyBribed = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slaverAlleyTookPlace = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slaverAlleyCompanionInStocks = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slaverAlleyAcceptedDeal = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slaverAlleyCompanionAcceptedDeal = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slaverAlleyTwoPartners = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slaverAlleySlavesFreed = new AbstractDialogueFlagValue(); // Reset every day at midnight (as part of stocks reset method)

	public static AbstractDialogueFlagValue slaverAlleyCafe1Visited = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slaverAlleyCafe1Demonstrated = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slaverAlleyCafe1DailyDemonstrated = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue slaverAlleyCafe2Visited = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slaverAlleyCafe2Demonstrated = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slaverAlleyCafe2DailyDemonstrated = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue slaverAlleyCafe3Visited = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slaverAlleyCafe3Demonstrated = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slaverAlleyCafe3DailyDemonstrated = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue slaverAlleyCafe4Visited = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slaverAlleyCafe4Demonstrated = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slaverAlleyCafe4DailyDemonstrated = new AbstractDialogueFlagValue(true);
	
	// Wes:
	public static AbstractDialogueFlagValue wesQuestLilayaHelp = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue wesQuestMet = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue wesQuestRefused = new AbstractDialogueFlagValue();

	public static AbstractDialogueFlagValue wesQuestTalked = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue wesQuestTalkedAlt = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue wesQuestFlirted = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue wesQuestSex = new AbstractDialogueFlagValue(true);
	
	// Helena (romance quest):
	public static AbstractDialogueFlagValue helenaCheapPaint = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue helenaGoneHome = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue scarlettGoneHome = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue helenaScarlettToldToReturn = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue helenaScarlettSleepoverSex = new AbstractDialogueFlagValue();

	// Helena (post-romance quest):
	public static AbstractDialogueFlagValue helenaSlutSeen = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue helenaShopTalkedTo = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue helenaShopFucked = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue helenaNestTalkedTo = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue helenaNestFucked = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue helenaShopScarlettTalkedTo = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue helenaShopScarlettCounterOral = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue helenaShopScarlettCafe = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue helenaShopScarlettCafeRevealed = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue helenaShopScarlettExtraTransformationDiscussed = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue helenaShopScarlettExtraTransformationApplied = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue helenaShopScarlettExtraTransformationHelenaReacted = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue helenaDateApartmentSeen = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue helenaDateFirstDateComplete = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue helenaDateRomanticSetup = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue helenaDateRomanticSetupEatenOut = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue helenaGift = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue helenaDateSexLifeTalk = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue helenaDateVirginityTalk = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue helenaScarlettThreesome = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue helenaBedroomFromNest = new AbstractDialogueFlagValue();
	
	// Natalya:
	public static AbstractDialogueFlagValue playerSubmittedToNatalya = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue natalyaDemandedFacial = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue playerReceivedNatalyaFacial = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue natalyaVisited = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue natalyaInterviewOffered = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue natalyaBusy = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue natalyaDailySexAsSub = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue natalyaDailySexAsDom = new AbstractDialogueFlagValue(true);

	public static AbstractDialogueFlagValue natalyaParkEncounter = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue playerSubmittedToNatalyaInPark = new AbstractDialogueFlagValue();
	
	
	// Kay:
	public static AbstractDialogueFlagValue kayTalkedTo = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue kayFlirtedWith = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue kaySubmitted = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue kayPreviouslyFeminised = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue kayFeminised = new AbstractDialogueFlagValue();

	public static AbstractDialogueFlagValue kayDommed = new AbstractDialogueFlagValue(true);
	
	public static AbstractDialogueFlagValue kayCratesSearched = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue dobermannDefeatPaid = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue dobermannDefeatEnforcer = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue dobermannDefeatDemon = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue dobermannDefeatSeduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue dobermannDefeatCombat = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue supplierDepotDoorUnlocked = new AbstractDialogueFlagValue(); // Named 'suppliers' from old quest structure, refers to dobermanns
	public static AbstractDialogueFlagValue suppliersEncountered = new AbstractDialogueFlagValue();
	
	// Zaranix:
	public static AbstractDialogueFlagValue zaranixDiscoveredHome = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue zaranixMaidsHostile = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue zaranixKnockedOnDoor = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue zaranixKickedDownDoor = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue zaranixAmberSubdued = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue zaranixKatherineSubdued = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue zaranixKellySubdued = new AbstractDialogueFlagValue();

	public static AbstractDialogueFlagValue zaranixTransformedPlayer = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue amberSatOnFloor = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue amberRepeatEncountered = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue katherineRepeatEncountered = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue kellyRepeatEncountered = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue zaranixRepeatEncountered = new AbstractDialogueFlagValue();
	
	// Lumi:
	public static AbstractDialogueFlagValue lumiMet = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue lumiDisabled = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue lumiPromisedDinner = new AbstractDialogueFlagValue();
	
	// Slime Queen's Tower:
	public static AbstractDialogueFlagValue slimeGuardsIntroduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slimeGuardsBluffed = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slimeGuardsDefeated = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slimeRoyalGuardIntroduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slimeRoyalGuardDefeated = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slimeRoyalGuardDefeatReacted = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slimeQueenHelped = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slimeQueenConvinced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue slimeQueenForced = new AbstractDialogueFlagValue();
	
	// Gambling Den:
	public static AbstractDialogueFlagValue axelMentionedVengar = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue axelExplainedVengar = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue axelToldSubmit = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue axelSissified = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue axelFeminised = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue axelClothingFeminine = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue axelClothingWhore = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue axelClothingMaid = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue roxyAddicted = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue roxyVengarOwnerIntroduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue playedPregnancyRouletteAsMother = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue playedPregnancyRouletteAsBreeder = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue eponaMurkOwnerIntroduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue eponaMurkSeen = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue eponaMurkSubmitted = new AbstractDialogueFlagValue();
	
	// Nightlife:
	public static AbstractDialogueFlagValue julesIntroduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue suckedJulesCock = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue passedJules = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue kalahariIntroduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue kalahariWantsSex = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue krugerIntroduced = new AbstractDialogueFlagValue();
	
	// Submission:
	public static AbstractDialogueFlagValue claireAskedTeleportation = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue claireWarning = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue clairePadsInvestigated = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue claireEnclosureEscaped = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue claireObtainedLightningGlobe = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue claireAskedWarehouseEscape = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue impCitadelEncountered = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue impCitadelArcanistEncountered = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue impCitadelArcanistAcceptedTF = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue impCitadelTreasurySearched = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue impCitadelLaboratorySearched = new AbstractDialogueFlagValue();

	public static AbstractDialogueFlagValue impCitadelPrisonerMale = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue impCitadelPrisonerFemale = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue impCitadelPrisonerAlpha = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue impFortressAlphaGuardsPacified = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue impFortressAlphaGuardsKnowPlayerDemon = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue impFortressAlphaBossEncountered = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue impFortressAlphaPacified = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue impFortressAlphaDefeated = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue impFortressFemalesGuardsPacified = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue impFortressFemalesGuardsKnowPlayerDemon = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue impFortressFemalesBossEncountered = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue impFortressFemalesPacified = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue impFortressFemalesDefeated = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue impFortressMalesGuardsPacified = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue impFortressMalesGuardsKnowPlayerDemon = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue impFortressMalesBossEncountered = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue impFortressMalesPacified = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue impFortressMalesDefeated = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue impFortressDemonBossEncountered = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue impFortressDemonDefeated = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue impFortressDemonImpsDefeated = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue elizabethAskedAboutUniforms = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue elizabethAskedAboutSurname = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue elizabethAskedAboutRoutine = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue lyssiethQuestionAsked1 = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue lyssiethQuestionAsked2 = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue lyssiethQuestionAsked3 = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue lyssiethQuestionAsked4 = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue lyssiethQuestionAsked5 = new AbstractDialogueFlagValue();

	public static AbstractDialogueFlagValue lyssiethNoCockDemonTF = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue meraxisRepeatDemonTF = new AbstractDialogueFlagValue();

	public static AbstractDialogueFlagValue vendingMachineTalked = new AbstractDialogueFlagValue();
	
	
	// Rebel base:

	public static AbstractDialogueFlagValue rebelBaseDarkPassFound = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue rebelBaseLightPassFound = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue rebelBaseInsaneSurvivorEncountered = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue rebelBaseElleCostumeEncountered = new AbstractDialogueFlagValue();
    
	
	// Rat warrens:
	
	public static AbstractDialogueFlagValue ratWarrensEntry = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue ratWarrensEntryWhore = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue ratWarrensHostile = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue ratWarrensEntranceGuardsFight = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue ratWarrensSeenMilkers = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue ratWarrensMilkersBackground = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue ratWarrensMilkersFreeAttempt = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue ratWarrensSilenceIntroduced = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue ratWarrensClearedLeft = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue ratWarrensClearedCentre = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue ratWarrensClearedRight = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue ratWarrensLootedDiceDen = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue vengarThreatened = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue vengarPersuaded = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue vengarSeduced = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue ratWarrensUsedResonanceStone = new AbstractDialogueFlagValue();

	public static AbstractDialogueFlagValue vengarCaptiveRoomCleaned = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue vengarCaptiveVengarSatisfied = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue vengarCaptiveShadowSatisfied = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue vengarCaptiveSilenceSatisfied = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue vengarCaptiveCompanionGivenBirth = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue vengarCaptiveGangBanged = new AbstractDialogueFlagValue(true);

	public static AbstractDialogueFlagValue ratWarrensCaptiveInitialNightDescription = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue ratWarrensCaptiveAttemptingEscape = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue ratWarrensCaptiveEscaped = new AbstractDialogueFlagValue();
//	public static AbstractDialogueFlagValue ratWarrensCaptiveTransformationsStarted = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue ratWarrensCaptiveFeminine = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue ratWarrensCaptiveFuta = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue ratWarrensCaptiveMasculine = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue ratWarrensCaptiveSissy = new AbstractDialogueFlagValue();

	public static AbstractDialogueFlagValue murkCaptiveBlowjob = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue murkMaster = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue ratWarrensCaptiveCalledOut = new AbstractDialogueFlagValue();
	public static AbstractDialogueFlagValue ratWarrensCaptiveWashed = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue ratWarrensCaptiveCompanionGivenBirth = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue ratWarrensCaptiveOwnerSex = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue ratWarrensCaptiveOwnerCompanionSex = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue ratWarrensCaptiveDailyTransformed = new AbstractDialogueFlagValue(true);

	public static AbstractDialogueFlagValue murkLectured = new AbstractDialogueFlagValue(true);
	public static AbstractDialogueFlagValue murkSpanked = new AbstractDialogueFlagValue(true);

	public static AbstractDialogueFlagValue milkersClaireDialogue = new AbstractDialogueFlagValue(false);
	
	
	//Felicia
	public static AbstractDialogueFlagValue feliciaAskedArthurPersonality = new AbstractDialogueFlagValue(false);
	public static AbstractDialogueFlagValue feliciaAskedArthurHobbies = new AbstractDialogueFlagValue(false);
	public static AbstractDialogueFlagValue feliciaAskedAboutHerSurname = new AbstractDialogueFlagValue(false);
	public static AbstractDialogueFlagValue feliciaAskedAboutHerPlace = new AbstractDialogueFlagValue(false);
	public static AbstractDialogueFlagValue feliciaAskedAboutHerFur = new AbstractDialogueFlagValue(false);
	public static AbstractDialogueFlagValue feliciaAskedAboutHerFavoriteStore = new AbstractDialogueFlagValue(false);
	public static AbstractDialogueFlagValue feliciaToldAboutArthur = new AbstractDialogueFlagValue(false);
	public static AbstractDialogueFlagValue feliciaLewdTalkAborted = new AbstractDialogueFlagValue(false);
	public static AbstractDialogueFlagValue feliciaRejectedPlayer = new AbstractDialogueFlagValue(false);
	
	
	// Fields area:
	
	public static AbstractDialogueFlagValue leftDominionFirstTime = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue centaurTransportEncountered = new AbstractDialogueFlagValue();

	public static AbstractDialogueFlagValue lunetteTerrorEnded = new AbstractDialogueFlagValue();
	
	public static AbstractDialogueFlagValue minotallys_tf_required = new AbstractDialogueFlagValue();

	// ------------------------------------------------------------------------------------------------------------//
	
	// Utility methods for loading in external values:
	
	public static List<AbstractDialogueFlagValue> allDialogueFlagValues;
	
	public static Map<AbstractDialogueFlagValue, String> dialogueFlagValueToIdMap = new HashMap<>();
	public static Map<String, AbstractDialogueFlagValue> idToDialogueFlagValueMap = new HashMap<>();
	
	/**
	 * @param id Will be in the format of: 'innoxia_elis_berries_found'.
	 * @return The flag that has an id closest to the supplied id. <b>Will return null</b> if the matching distance is greater than 3 (which typically will be more than enough to catch spelling errors, indicating that the flag has been removed).
	 */
	public static AbstractDialogueFlagValue getDialogueFlagValueFromId(String id) {

//		public static AbstractDialogueFlagValue gymIntroduced = new AbstractDialogueFlagValue();
//		public static AbstractDialogueFlagValue gymHadTour = new AbstractDialogueFlagValue();
//		public static AbstractDialogueFlagValue gymIsMember = new AbstractDialogueFlagValue();
		// Removed flags:
		if(id.equals("ratWarrensRaid")
				|| id.equals("suppliersTriedConvincing")
				// Reset gym flags so that the new gym starts out as a fresh start for versions loaded from prior to 0.4.7.8:
				|| id.equals("gymIsMember")
				|| id.equals("gymIntroduced")
				|| id.equals("gymHadTour")) {
			return null;
		}
		
		if(id.equals("innoxia_elis_alleyway_transformations_applied")) {
			id = "innoxia_alleyway_transformations_applied";
		}
//		if(id.equals("gymIntroduced")) {
//			id = "innoxia_pix_introduced";
//		}
//		if(id.equals("gymHadTour")) {
//			id = "innoxia_pix_had_tour";
//		}
		
		id = Util.getClosestStringMatch(id, idToDialogueFlagValueMap.keySet(), 3);
				
		return idToDialogueFlagValueMap.get(id);
	}
	
	public static String getIdFromDialogueFlagValue(AbstractDialogueFlagValue perk) {
		return dialogueFlagValueToIdMap.get(perk);
	}

	static {
		allDialogueFlagValues = new ArrayList<>();
		
		// Modded dialogueFlagValues:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/dialogue", null, "flags");
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					List<AbstractDialogueFlagValue> dialogueFlagValues = AbstractDialogueFlagValue.loadFlagsFromFile(innerEntry.getValue(), entry.getKey(), true);
					
					for(AbstractDialogueFlagValue loadedFlag : dialogueFlagValues) {
						allDialogueFlagValues.add(loadedFlag);
						dialogueFlagValueToIdMap.put(loadedFlag, loadedFlag.getId());
						idToDialogueFlagValueMap.put(loadedFlag.getId(), loadedFlag);
//						System.out.println("modded DFV: "+innerEntry.getKey()+" "+loadedFlag.getId());
					}
				} catch(Exception ex) {
					System.err.println("Loading modded dialogueFlagValue failed at 'DialogueFlagValue'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		// External res dialogueFlagValues:

		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/dialogue", null, "flags");
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					List<AbstractDialogueFlagValue> dialogueFlagValues = AbstractDialogueFlagValue.loadFlagsFromFile(innerEntry.getValue(), entry.getKey(), false);
					
					for(AbstractDialogueFlagValue loadedFlag : dialogueFlagValues) {
						allDialogueFlagValues.add(loadedFlag);
						dialogueFlagValueToIdMap.put(loadedFlag, loadedFlag.getId());
						idToDialogueFlagValueMap.put(loadedFlag.getId(), loadedFlag);
//						System.out.println("res DFV: "+innerEntry.getKey());
					}
				} catch(Exception ex) {
					System.err.println("Loading dialogueFlagValue failed at 'DialogueFlagValue'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		// Hard-coded dialogueFlagValues (all those up above):
		
		Field[] fields = DialogueFlagValue.class.getFields();
		
		for(Field f : fields){
			if (AbstractDialogueFlagValue.class.isAssignableFrom(f.getType())) {
				
				AbstractDialogueFlagValue dialogueFlagValue;
				
				try {
					dialogueFlagValue = ((AbstractDialogueFlagValue) f.get(null));

					String id = f.getName();
					dialogueFlagValueToIdMap.put(dialogueFlagValue, id);
					dialogueFlagValue.setId(id);
					idToDialogueFlagValueMap.put(id, dialogueFlagValue);
					allDialogueFlagValues.add(dialogueFlagValue);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static List<AbstractDialogueFlagValue> getAllDialogueFlagValues() {
		return allDialogueFlagValues;
	}
}
