package com.lilithsthrone.rendering;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;

import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.item.ItemEffectType;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.97
 * @author Innoxia
 */
public enum SVGImages {
	SVG_IMAGE_PROVIDER;

	private String

	displacedIcon, concealedIcon, cummedInIcon, feminineWarningIcon, masculineWarningIcon, jinxedIcon,

			menuIcon, inventoryIcon, inventoryIconDisabled, inventorySwitchIcon, inventorySwitchOppositeIcon, journalIcon, peopleIcon, zoomInIcon, zoomOutIcon, copyIcon, exportIcon, calendarIcon,

			diskSave, diskSaveDisabled, diskSaveConfirm, diskOverwrite, diskLoad, diskLoadConfirm, diskDelete, diskDeleteConfirm,
			
			itemsOnFloorIcon,
			
			playerMapIconMasculine, playerPlaceMapIconMasculine,
			playerMapIconAndrogynous, playerPlaceMapIconAndrogynous,
			playerMapIconFeminine, playerPlaceMapIconFeminine,
			playerMapDangerousIcon, playerPlaceMapDangerousIcon,

			raceUnknown, raceDobermann, raceDobermannDesaturated,
			
			perkTreeArrow, spellOverlay,

			weatherDayClear, weatherDayCloud, weatherDayRain, weatherDaySnow, weatherDayStormIncoming, weatherDayStorm, weatherDayStormProtected,
			weatherNightClear, weatherNightCloud, weatherNightRain, weatherNightSnow, weatherNightStormIncoming, weatherNightStorm, weatherNightStormProtected,

			womensWatchHourHand, womensWatchMinuteHand, mensWatchHourHand, mensWatchMinuteHand,
			
			protectionEnabled, protectionDisabled, tattoo,
			
			responseCombat, responseSex, responseLocked, responseUnlocked, responseUnlockedDisabled, responseOption, responseOptionDisabled, responseCorruptionBypass,
			responseSubResist, responseSubNormal, responseSubEager,
			responseDomGentle, responseDomNormal, responseDomRough,
			
			NPCWarningMale, NPCWarningFemale, NPCWarningDemon,
			
			scaleZero, scaleOne, scaleTwo, scaleThree, scaleFour,
			scaleZeroDisabled, scaleOneDisabled, scaleTwoDisabled, scaleThreeDisabled, scaleFourDisabled,

			slaveBuy, slaveBuyDisabled, slaveSell, slaveSellDisabled, slaveInspect, slaveInspectDisabled, slaveJob, slaveJobDisabled,
			slavePermissionsDisabled, slavePermissions, slaveTransfer, slaveTransferDisabled, slaveCosmetics, slaveCosmeticsDisabled,
			
			transactionBuy, transactionBuyDisabled, transactionBid, transactionBidDisabled, transactionSell, transactionSellDisabled,
			
			// Effects:
			creampie, creampieMasochist,
			
			// Items:
			
			hypnoWatchBase, hypnoWatchGynephilic, hypnoWatchAmbiphilic, hypnoWatchAndrophilic,
			
			// Sex:
			coverableAreaMouth, coverableAreaAnus, coverableAreaBreasts, coverableAreaBreastsFlat, coverableAreaNipple, coverableAreaVagina, coverableAreaThighs,
			penetrationTypeFinger, penetrationTypePenis, penetrationTypeTail, penetrationTypeTongue,
			combinationStretching, combinationTooLoose, combinationWet, combinationDry,
			stretching, holeTooBig;
	

	private Map<Colour, String> refinedBackgroundMap = new EnumMap<>(Colour.class);
	private Map<Colour, String> refinedSwirlsMap = new EnumMap<>(Colour.class);

	@SuppressWarnings("resource")
	private SVGImages() {

		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/displacedWarningIcon.svg");
			displacedIcon = Util.inputStreamToString(is);

			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/concealed.svg");
			concealedIcon = Util.inputStreamToString(is);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/cummedInWarningIcon.svg");
			cummedInIcon = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/feminineWarningIcon.svg");
			feminineWarningIcon = Util.inputStreamToString(is);
			feminineWarningIcon = setColour(feminineWarningIcon, Colour.FEMININE);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/masculineWarningIcon.svg");
			masculineWarningIcon = Util.inputStreamToString(is);
			masculineWarningIcon = setColour(masculineWarningIcon, Colour.BASE_BLUE_LIGHT);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/jinxed.svg");
			jinxedIcon = Util.inputStreamToString(is);
			jinxedIcon = setColour(jinxedIcon, Colour.ATTRIBUTE_CORRUPTION);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/menu.svg");
			menuIcon = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/inventory.svg");
			inventoryIcon = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/inventoryDisabled.svg");
			inventoryIconDisabled = Util.inputStreamToString(is);
			inventoryIconDisabled = setColour(inventoryIconDisabled, Colour.BASE_GREY);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/inventorySwitch.svg");
			inventorySwitchIcon = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/inventorySwitchOpposite.svg");
			inventorySwitchOppositeIcon = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/journal.svg");
			journalIcon = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/people.svg");
			peopleIcon = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/zoomIn.svg");
			zoomInIcon = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/zoomOut.svg");
			zoomOutIcon = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/copy.svg");
			copyIcon = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/export.svg");
			exportIcon = Util.inputStreamToString(is);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/calendar.svg");
			calendarIcon = Util.inputStreamToString(is);
			calendarIcon = setColour(calendarIcon, Colour.BASE_CRIMSON);
			

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskSave.svg");
			diskSave = Util.inputStreamToString(is);
			diskSave = setColour(diskSave, Colour.BASE_BLACK);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskSave.svg");
			diskSaveDisabled = Util.inputStreamToString(is);
			diskSaveDisabled = setColour(diskSaveDisabled, Colour.BASE_GREY);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskSave.svg");
			diskOverwrite = Util.inputStreamToString(is);
			diskOverwrite = setColour(diskOverwrite, Colour.BASE_BLACK);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskSave.svg");
			diskSaveConfirm = Util.inputStreamToString(is);
			diskSaveConfirm = setColour(diskSaveConfirm, Colour.GENERIC_EXCELLENT);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskLoad.svg");
			diskLoad = Util.inputStreamToString(is);
			diskLoad = setColour(diskLoad, Colour.BASE_BLUE_LIGHT);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskLoad.svg");
			diskLoadConfirm = Util.inputStreamToString(is);
			diskLoadConfirm = setColour(diskLoadConfirm, Colour.GENERIC_EXCELLENT);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskDelete.svg");
			diskDelete = Util.inputStreamToString(is);
			diskDelete = setColour(diskDelete, Colour.BASE_CRIMSON);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskDelete.svg");
			diskDeleteConfirm = Util.inputStreamToString(is);
			diskDeleteConfirm = setColour(diskDeleteConfirm, Colour.GENERIC_EXCELLENT);
			
			
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/itemsOnFloor.svg");
			itemsOnFloorIcon = Util.inputStreamToString(is);
			itemsOnFloorIcon = setColour(itemsOnFloorIcon, Colour.GENERIC_EXCELLENT);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/map/playerIcon.svg");
			playerMapIconMasculine = Util.inputStreamToString(is);
			playerMapIconMasculine = setColour(playerMapIconMasculine, Colour.MASCULINE_PLUS);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/map/playerPlaceIcon.svg");
			playerPlaceMapIconMasculine = Util.inputStreamToString(is);
			playerPlaceMapIconMasculine = setColour(playerPlaceMapIconMasculine, Colour.MASCULINE_PLUS);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/map/playerIcon.svg");
			playerMapIconAndrogynous = Util.inputStreamToString(is);
			playerMapIconAndrogynous = setColour(playerMapIconAndrogynous, Colour.ANDROGYNOUS);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/map/playerPlaceIcon.svg");
			playerPlaceMapIconAndrogynous = Util.inputStreamToString(is);
			playerPlaceMapIconAndrogynous = setColour(playerPlaceMapIconAndrogynous, Colour.ANDROGYNOUS);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/map/playerIcon.svg");
			playerMapIconFeminine = Util.inputStreamToString(is);
			playerMapIconFeminine = setColour(playerMapIconFeminine, Colour.FEMININE_PLUS);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/map/playerPlaceIcon.svg");
			playerPlaceMapIconFeminine = Util.inputStreamToString(is);
			playerPlaceMapIconFeminine = setColour(playerPlaceMapIconFeminine, Colour.FEMININE_PLUS);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/map/playerIcon.svg");
			playerMapDangerousIcon = Util.inputStreamToString(is);
			playerMapDangerousIcon = setColour(playerMapDangerousIcon, Colour.GENERIC_BAD);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/map/playerPlaceIcon.svg");
			playerPlaceMapDangerousIcon = Util.inputStreamToString(is);
			playerPlaceMapDangerousIcon = setColour(playerPlaceMapDangerousIcon, Colour.GENERIC_BAD);

			
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/raceUnknown.svg");
			raceUnknown = Util.inputStreamToString(is);
			raceUnknown = setColour(raceUnknown, Colour.RACE_UNKNOWN);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/raceDogMorphDobermann.svg");
			raceDobermann = Util.inputStreamToString(is);
			raceDobermann = setColour(raceDobermann, Colour.RACE_DOG_MORPH);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/raceDogMorphDobermann.svg");
			raceDobermannDesaturated = Util.inputStreamToString(is);
			raceDobermannDesaturated = setColour(raceDobermannDesaturated, Colour.BASE_GREY);
			
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/perkTreeArrow.svg");
			perkTreeArrow = Util.inputStreamToString(is);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/combat/spell_overlay.svg");
			spellOverlay = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/wrist_womens_watch_hourhand.svg");
			womensWatchHourHand = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/wrist_womens_watch_minutehand.svg");
			womensWatchMinuteHand = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/wrist_mens_watch_hourhand.svg");
			mensWatchHourHand = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/wrist_mens_watch_minutehand.svg");
			mensWatchMinuteHand = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherDayClear.svg");
			weatherDayClear = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherDayCloudy.svg");
			weatherDayCloud = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherDayRain.svg");
			weatherDayRain = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherDaySnow.svg");
			weatherDaySnow = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherDayStormIncoming.svg");
			weatherDayStormIncoming = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherDayStorm.svg");
			weatherDayStorm = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherDayStormProtected.svg");
			weatherDayStormProtected = Util.inputStreamToString(is);
			weatherDayStormProtected = setColour(weatherDayStormProtected, Colour.CLOTHING_BLUE_LIGHT);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherNightClear.svg");
			weatherNightClear = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherNightCloudy.svg");
			weatherNightCloud = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherNightRain.svg");
			weatherNightRain = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherNightSnow.svg");
			weatherNightSnow = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherNightStormIncoming.svg");
			weatherNightStormIncoming = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherNightStorm.svg");
			weatherNightStorm = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/weatherNightStormProtected.svg");
			weatherNightStormProtected = Util.inputStreamToString(is);
			weatherNightStormProtected = setColour(weatherNightStormProtected, Colour.CLOTHING_BLUE_LIGHT);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/protection.svg");
			protectionDisabled = Util.inputStreamToString(is);
			protectionDisabled = setColour(protectionDisabled, Colour.CLOTHING_BLACK);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/protection.svg");
			protectionEnabled = Util.inputStreamToString(is);
			protectionEnabled = setColour(protectionEnabled, Colour.GENERIC_GOOD);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/tattoo.svg");
			tattoo = Util.inputStreamToString(is);
			tattoo = setColour(tattoo, Colour.CLOTHING_BLACK);
			

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseCombat.svg");
			responseCombat = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseSex.svg");
			responseSex = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseLocked.svg");
			responseLocked = Util.inputStreamToString(is);
			responseLocked = setColour(responseLocked, Colour.GENERIC_BAD);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseUnlocked.svg");
			responseUnlocked = Util.inputStreamToString(is);
			responseUnlocked = setColour(responseUnlocked, Colour.GENERIC_GOOD);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseUnlocked.svg");
			responseUnlockedDisabled = Util.inputStreamToString(is);
			responseUnlockedDisabled = setColour(responseUnlockedDisabled, Colour.BASE_GREY);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseOption.svg");
			responseOption = Util.inputStreamToString(is);
			responseOption = setColour(responseOption, Colour.GENERIC_GOOD);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseOption.svg");
			responseOptionDisabled = Util.inputStreamToString(is);
			responseOptionDisabled = setColour(responseOptionDisabled, Colour.BASE_GREY);
			
			
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseCorruptionBypass.svg");
			responseCorruptionBypass = Util.inputStreamToString(is);
			responseCorruptionBypass = setColour(responseCorruptionBypass, Colour.GENERIC_ARCANE);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseSubResist.svg");
			responseSubResist = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseSubNormal.svg");
			responseSubNormal = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseSubEager.svg");
			responseSubEager = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseDomGentle.svg");
			responseDomGentle = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseDomNormal.svg");
			responseDomNormal = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseDomRough.svg");
			responseDomRough = Util.inputStreamToString(is);
			
			
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseNPC.svg");
			NPCWarningMale = Util.inputStreamToString(is);
			NPCWarningMale = setColour(NPCWarningMale, Colour.MASCULINE_PLUS);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseNPC.svg");
			NPCWarningFemale = Util.inputStreamToString(is);
			NPCWarningFemale = setColour(NPCWarningFemale, Colour.FEMININE_PLUS);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseNPC.svg");
			NPCWarningDemon = Util.inputStreamToString(is);
			NPCWarningDemon = setColour(NPCWarningDemon, Colour.GENERIC_ARCANE);
			
			
			// scales:

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_zero.svg");
			scaleZero = Util.inputStreamToString(is);
			scaleZero = setColour(scaleZero, Colour.BASE_MAGENTA);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_one.svg");
			scaleOne = Util.inputStreamToString(is);
			scaleOne = setColour(scaleOne, Colour.BASE_GREEN);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_two.svg");
			scaleTwo = Util.inputStreamToString(is);
			scaleTwo = setColour(scaleTwo, Colour.BASE_GREEN);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_three.svg");
			scaleThree = Util.inputStreamToString(is);
			scaleThree = setColour(scaleThree, Colour.BASE_GREEN);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_four.svg");
			scaleFour = Util.inputStreamToString(is);
			scaleFour = setColour(scaleFour, Colour.BASE_GREEN);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_zero.svg");
			scaleZeroDisabled = Util.inputStreamToString(is);
			scaleZeroDisabled = setColour(scaleZeroDisabled, Colour.BASE_GREY);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_one.svg");
			scaleOneDisabled = Util.inputStreamToString(is);
			scaleOneDisabled = setColour(scaleOneDisabled, Colour.BASE_GREY);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_two.svg");
			scaleTwoDisabled = Util.inputStreamToString(is);
			scaleTwoDisabled = setColour(scaleTwoDisabled, Colour.BASE_GREY);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_three.svg");
			scaleThreeDisabled = Util.inputStreamToString(is);
			scaleThreeDisabled = setColour(scaleThreeDisabled, Colour.BASE_GREY);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_four.svg");
			scaleFourDisabled = Util.inputStreamToString(is);
			scaleFourDisabled = setColour(scaleFourDisabled, Colour.BASE_GREY);
			
			
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveBuy.svg");
			slaveBuy = Util.inputStreamToString(is);
			slaveBuy = setColour(slaveBuy, Colour.GENERIC_BAD);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveBuyDisabled.svg");
			slaveBuyDisabled = Util.inputStreamToString(is);
			slaveBuyDisabled = setColour(slaveBuyDisabled, Colour.BASE_GREY);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveSell.svg");
			slaveSell = Util.inputStreamToString(is);
			slaveSell = setColour(slaveSell, Colour.GENERIC_GOOD);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveSellDisabled.svg");
			slaveSellDisabled = Util.inputStreamToString(is);
			slaveSellDisabled = setColour(slaveSellDisabled, Colour.BASE_GREY);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveInspect.svg");
			slaveInspect = Util.inputStreamToString(is);
			slaveInspect = setColour(slaveInspect, Colour.BASE_BLUE_STEEL);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveInspect.svg");
			slaveInspectDisabled = Util.inputStreamToString(is);
			slaveInspectDisabled = setColour(slaveInspectDisabled, Colour.BASE_GREY);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveJob.svg");
			slaveJob = Util.inputStreamToString(is);
			slaveJob = setColour(slaveJob, Colour.BASE_BROWN_DARK);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveJob.svg");
			slaveJobDisabled = Util.inputStreamToString(is);
			slaveJobDisabled = setColour(slaveJobDisabled, Colour.BASE_GREY);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slavePermissions.svg");
			slavePermissions = Util.inputStreamToString(is);
			slavePermissions = setColour(slavePermissions, Colour.BASE_GREY);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slavePermissionsDisabled.svg");
			slavePermissionsDisabled = Util.inputStreamToString(is);
			slavePermissionsDisabled = setColour(slavePermissionsDisabled, Colour.BASE_GREY);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveTransfer.svg");
			slaveTransfer = Util.inputStreamToString(is);
			slaveTransfer = setColour(slaveTransfer, Colour.GENERIC_GOOD);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveTransfer.svg");
			slaveTransferDisabled = Util.inputStreamToString(is);
			slaveTransferDisabled = setColour(slaveTransferDisabled, Colour.BASE_GREY);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveCosmetics.svg");
			slaveCosmetics = Util.inputStreamToString(is);
			slaveCosmetics = setColour(slaveCosmetics, Colour.BASE_CRIMSON);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveCosmetics.svg");
			slaveCosmeticsDisabled  = Util.inputStreamToString(is);
			slaveCosmeticsDisabled = setColour(slaveCosmeticsDisabled, Colour.BASE_GREY);
			
			
			
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/transactionBuy.svg");
			transactionBuy = Util.inputStreamToString(is);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/transactionBuyDisabled.svg");
			transactionBuyDisabled = Util.inputStreamToString(is);
			transactionBuyDisabled = setColour(transactionBuyDisabled, Colour.BASE_GREY);
			
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/transactionBid.svg");
			transactionBid = Util.inputStreamToString(is);
			transactionBid = setColour(transactionBid, Colour.BASE_BROWN);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/transactionBid.svg");
			transactionBidDisabled = Util.inputStreamToString(is);
			transactionBidDisabled = setColour(transactionBidDisabled, Colour.BASE_GREY);
			
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/transactionSell.svg");
			transactionSell = Util.inputStreamToString(is);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/transactionSellDisabled.svg");
			transactionSellDisabled = Util.inputStreamToString(is);
			transactionSellDisabled = setColour(transactionSellDisabled, Colour.BASE_GREY);
			
			// Effects:

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/creampie.svg");
			creampie = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/creampieMasochist.svg");
			creampieMasochist = Util.inputStreamToString(is);
			
			// Items:
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/hypnoClockBase.svg");
			hypnoWatchBase = Util.inputStreamToString(is);
			hypnoWatchBase = setColour(hypnoWatchBase, Colour.BASE_GREY);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/hypnoClockGyne.svg");
			hypnoWatchGynephilic = Util.inputStreamToString(is);
			hypnoWatchGynephilic = setColour(hypnoWatchGynephilic, Colour.FEMININE_PLUS);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/hypnoClockAmbi.svg");
			hypnoWatchAmbiphilic = Util.inputStreamToString(is);
			hypnoWatchAmbiphilic = setColour(hypnoWatchAmbiphilic, Colour.ANDROGYNOUS);

			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/hypnoClockAndro.svg");
			hypnoWatchAndrophilic = Util.inputStreamToString(is);
			hypnoWatchAndrophilic = setColour(hypnoWatchAndrophilic, Colour.MASCULINE_PLUS);
			
			
			// Sex:
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaAnus.svg");
			coverableAreaAnus = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaMouth.svg");
			coverableAreaMouth = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaBreasts.svg");
			coverableAreaBreasts = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaBreastsFlat.svg");
			coverableAreaBreastsFlat = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaNipple.svg");
			coverableAreaNipple = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaVagina.svg");
			coverableAreaVagina = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaThighs.svg");
			coverableAreaThighs = Util.inputStreamToString(is);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/penetrationTypeFinger.svg");
			penetrationTypeFinger = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/penetrationTypePenis.svg");
			penetrationTypePenis = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/penetrationTypeTail.svg");
			penetrationTypeTail = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/penetrationTypeTongue.svg");
			penetrationTypeTongue = Util.inputStreamToString(is);
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/combinationStretching.svg");
			combinationStretching = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/combinationTooLoose.svg");
			combinationTooLoose = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/combinationWet.svg");
			combinationWet = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/combinationDry.svg");
			combinationDry = Util.inputStreamToString(is);
			
			
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/stretching.svg");
			stretching = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/holeTooBig.svg");
			holeTooBig = Util.inputStreamToString(is);
			
			String tempString = "";
			for(ItemEffectType effect : ItemEffectType.values()) {
				is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/refined_background.svg");
				tempString = Util.inputStreamToString(is);
				tempString = setColour(tempString, effect.getColour());
				
				refinedBackgroundMap.put(effect.getColour(), tempString);
			}
			
			tempString = "";
			for(TFModifier secondaryModifier : TFModifier.values()) {
				is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/refined_swirls.svg");
				tempString = Util.inputStreamToString(is);
				tempString = setColour(tempString, secondaryModifier.getColour());
				
				refinedSwirlsMap.put(secondaryModifier.getColour(), tempString);
			}

			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String setColour(String stringSVG, Colour colourShade) {
		String s = stringSVG;
		s = s.replaceAll("#ff2a2a", colourShade.getShades()[0]);
		s = s.replaceAll("#ff5555", colourShade.getShades()[1]);
		s = s.replaceAll("#ff8080", colourShade.getShades()[2]);
		s = s.replaceAll("#ffaaaa", colourShade.getShades()[3]);
		s = s.replaceAll("#ffd5d5", colourShade.getShades()[4]);
		return s;
	}

	public String getDisplacedIcon() {
		return displacedIcon;
	}

	public String getConcealedIcon() {
		return concealedIcon;
	}
	
	public String getCummedInIcon() {
		return cummedInIcon;
	}

	public String getFeminineWarningIcon() {
		return feminineWarningIcon;
	}

	public String getMasculineWarningIcon() {
		return masculineWarningIcon;
	}

	public String getJinxedIcon() {
		return jinxedIcon;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public String getInventoryIcon() {
		return inventoryIcon;
	}

	public String getInventoryIconDisabled() {
		return inventoryIconDisabled;
	}

	public String getInventorySwitchIcon() {
		return inventorySwitchIcon;
	}

	public String getInventorySwitchOppositeIcon() {
		return inventorySwitchOppositeIcon;
	}

	public String getJournalIcon() {
		return journalIcon;
	}

	public String getPeopleIcon() {
		return peopleIcon;
	}

	public String getZoomInIcon() {
		return zoomInIcon;
	}

	public String getZoomOutIcon() {
		return zoomOutIcon;
	}

	public String getCopyIcon() {
		return copyIcon;
	}
	
	public String getExportIcon() {
		return exportIcon;
	}

	public String getCalendarIcon() {
		return calendarIcon;
	}

	public String getDiskSave() {
		return diskSave;
	}
	public String getDiskSaveDisabled() {
		return diskSaveDisabled;
	}
	
	public String getDiskSaveConfirm() {
		return diskSaveConfirm;
	}

	public String getDiskOverwrite() {
		return diskOverwrite;
	}

	public String getDiskLoad() {
		return diskLoad;
	}
	
	public String getDiskLoadConfirm() {
		return diskLoadConfirm;
	}

	public String getDiskDelete() {
		return diskDelete;
	}
	
	public String getDiskDeleteConfirm() {
		return diskDeleteConfirm;
	}

	public String getItemsOnFloorIcon() {
		return itemsOnFloorIcon;
	}

	public String getPlayerMapIconMasculine() {
		return playerMapIconMasculine;
	}

	public String getPlayerPlaceMapIconMasculine() {
		return playerPlaceMapIconMasculine;
	}

	public String getPlayerMapIconAndrogynous() {
		return playerMapIconAndrogynous;
	}

	public String getPlayerPlaceMapIconAndrogynous() {
		return playerPlaceMapIconAndrogynous;
	}

	public String getPlayerMapIconFeminine() {
		return playerMapIconFeminine;
	}

	public String getPlayerPlaceMapIconFeminine() {
		return playerPlaceMapIconFeminine;
	}

	public String getPlayerMapDangerousIcon() {
		return playerMapDangerousIcon;
	}

	public String getPlayerPlaceMapDangerousIcon() {
		return playerPlaceMapDangerousIcon;
	}

	public String getPerkTreeArrow() {
		return perkTreeArrow;
	}
	
	public String getSpellOverlay() {
		return spellOverlay;
	}

	public String getWomensWatchHourHand() {
		return womensWatchHourHand;
	}

	public String getWomensWatchMinuteHand() {
		return womensWatchMinuteHand;
	}

	public String getMensWatchHourHand() {
		return mensWatchHourHand;
	}

	public String getMensWatchMinuteHand() {
		return mensWatchMinuteHand;
	}

	public String getWeatherDayClear() {
		return weatherDayClear;
	}

	public String getWeatherDayCloud() {
		return weatherDayCloud;
	}

	public String getWeatherDayRain() {
		return weatherDayRain;
	}
	
	public String getWeatherDaySnow() {
		return weatherDaySnow;
	}

	public String getWeatherDayStormIncoming() {
		return weatherDayStormIncoming;
	}

	public String getWeatherDayStormProtected() {
		return weatherDayStormProtected;
	}

	public String getWeatherDayStorm() {
		return weatherDayStorm;
	}

	public String getWeatherNightClear() {
		return weatherNightClear;
	}

	public String getWeatherNightCloud() {
		return weatherNightCloud;
	}

	public String getWeatherNightRain() {
		return weatherNightRain;
	}
	
	public String getWeatherNightSnow() {
		return weatherNightSnow;
	}

	public String getWeatherNightStormIncoming() {
		return weatherNightStormIncoming;
	}

	public String getWeatherNightStorm() {
		return weatherNightStorm;
	}

	public String getWeatherNightStormProtected() {
		return weatherNightStormProtected;
	}

	public String getProtectionEnabled() {
		return protectionEnabled;
	}

	public String getProtectionDisabled() {
		return protectionDisabled;
	}

	public String getTattoo() {
		return tattoo;
	}

	public String getResponseCombat() {
		return responseCombat;
	}

	public String getResponseSex() {
		return responseSex;
	}

	public String getResponseLocked() {
		return responseLocked;
	}

	public String getResponseUnlocked() {
		return responseUnlocked;
	}
	
	public String getResponseUnlockedDisabled() {
		return responseUnlockedDisabled;
	}

	public String getResponseCorruptionBypass() {
		return responseCorruptionBypass;
	}

	public String getResponseSubResist() {
		return responseSubResist;
	}

	public String getResponseSubNormal() {
		return responseSubNormal;
	}

	public String getResponseSubEager() {
		return responseSubEager;
	}

	public String getResponseDomGentle() {
		return responseDomGentle;
	}

	public String getResponseDomNormal() {
		return responseDomNormal;
	}

	public String getResponseDomRough() {
		return responseDomRough;
	}

	public String getNPCWarningMale() {
		return NPCWarningMale;
	}

	public String getNPCWarningFemale() {
		return NPCWarningFemale;
	}

	public String getNPCWarningDemon() {
		return NPCWarningDemon;
	}

	public String getCoverableAreaMouth() {
		return coverableAreaMouth;
	}

	public String getCoverableAreaAnus() {
		return coverableAreaAnus;
	}

	public String getCoverableAreaNipple() {
		return coverableAreaNipple;
	}
	
	public String getCoverableAreaBreasts() {
		return coverableAreaBreasts;
	}
	
	public String getCoverableAreaBreastsFlat() {
		return coverableAreaBreastsFlat;
	}

	public String getCoverableAreaVagina() {
		return coverableAreaVagina;
	}
	
	public String getCoverableAreaThighs() {
		return coverableAreaThighs;
	}

	public String getPenetrationTypeFinger() {
		return penetrationTypeFinger;
	}
	
	public String getPenetrationTypeTail() {
		return penetrationTypeTail;
	}
	
	public String getPenetrationTypeTongue() {
		return penetrationTypeTongue;
	}
	
	public String getPenetrationTypePenis() {
		return penetrationTypePenis;
	}
	
	public String getCombinationStretching() {
		return combinationStretching;
	}

	public String getCombinationTooLoose() {
		return combinationTooLoose;
	}

	public String getCombinationWet() {
		return combinationWet;
	}

	public String getCombinationDry() {
		return combinationDry;
	}

	public String getStretching() {
		return stretching;
	}
	
	public String getHoleTooBig() {
		return holeTooBig;
	}

	public Map<Colour, String> getRefinedBackgroundMap() {
		return refinedBackgroundMap;
	}

	public Map<Colour, String> getRefinedSwirlsMap() {
		return refinedSwirlsMap;
	}
	
	public String getHypnoWatchBase() {
		return hypnoWatchBase;
	}
	
	public String getHypnoWatchGynephilic() {
		return hypnoWatchGynephilic;
	}

	public String getHypnoWatchAmbiphilic() {
		return hypnoWatchAmbiphilic;
	}

	public String getHypnoWatchAndrophilic() {
		return hypnoWatchAndrophilic;
	}

	public String getScaleZero() {
		return scaleZero;
	}

	public String getScaleOne() {
		return scaleOne;
	}

	public String getScaleTwo() {
		return scaleTwo;
	}

	public String getScaleThree() {
		return scaleThree;
	}

	public String getScaleFour() {
		return scaleFour;
	}

	public String getScaleZeroDisabled() {
		return scaleZeroDisabled;
	}

	public String getScaleOneDisabled() {
		return scaleOneDisabled;
	}

	public String getScaleTwoDisabled() {
		return scaleTwoDisabled;
	}

	public String getScaleThreeDisabled() {
		return scaleThreeDisabled;
	}

	public String getScaleFourDisabled() {
		return scaleFourDisabled;
	}

	public String getSlaveBuy() {
		return slaveBuy;
	}

	public String getSlaveSell() {
		return slaveSell;
	}

	public String getSlaveInspect() {
		return slaveInspect;
	}
	
	public String getSlaveInspectDisabled() {
		return slaveInspectDisabled;
	}

	public String getSlaveJob() {
		return slaveJob;
	}
	
	public String getSlaveJobDisabled() {
		return slaveJobDisabled;
	}

	public String getSlavePermissions() {
		return slavePermissions;
	}
	
	public String getSlavePermissionsDisabled() {
		return slavePermissionsDisabled;
	}

	public String getSlaveTransfer() {
		return slaveTransfer;
	}

	public String getSlaveBuyDisabled() {
		return slaveBuyDisabled;
	}

	public String getSlaveSellDisabled() {
		return slaveSellDisabled;
	}

	public String getSlaveTransferDisabled() {
		return slaveTransferDisabled;
	}
	
	public String getSlaveCosmetics() {
		return slaveCosmetics;
	}
	
	public String getSlaveCosmeticsDisabled() {
		return slaveCosmeticsDisabled;
	}

	public String getTransactionBuy() {
		return transactionBuy;
	}

	public String getTransactionBuyDisabled() {
		return transactionBuyDisabled;
	}
	
	public String getTransactionBid() {
		return transactionBid;
	}
	
	public String getTransactionBidDisabled() {
		return transactionBidDisabled;
	}

	public String getTransactionSell() {
		return transactionSell;
	}

	public String getTransactionSellDisabled() {
		return transactionSellDisabled;
	}

	public String getResponseOption() {
		return responseOption;
	}

	public String getResponseOptionDisabled() {
		return responseOptionDisabled;
	}

	public String getCreampie() {
		return creampie;
	}

	public String getCreampieMasochist() {
		return creampieMasochist;
	}

	public String getRaceUnknown() {
		return raceUnknown;
	}

	public String getRaceDobermann() {
		return raceDobermann;
	}
	
	public String getRaceDobermannDesaturated() {
		return raceDobermannDesaturated;
	}
	
	
}
