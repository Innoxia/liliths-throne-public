package com.lilithsthrone.rendering;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.inventory.ColourReplacement;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @author Innoxia
 * @version 0.3.7.5
 * @since 0.1.0
 */
public enum SVGImages {
    SVG_IMAGE_PROVIDER;

    private String

            flagUs,

    fist,

    displacedIcon, concealedIcon, dirtyIcon, lipstickIcon, feminineWarningIcon, masculineWarningIcon, jinxedIcon, tattooSwitchTattoo, tattooSwitchClothing, tattooSwitchClothingHighlight, scarIcon,

    menuIcon,
            inventoryIcon, inventoryIconDisabled,
            questInventoryIcon, questInventoryIconDisabled,
            journalIcon, peopleIcon, zoomInIcon, zoomOutIcon, copyIcon, exportIcon, calendarIcon, informationIcon, addIcon,

    diskSave, diskSaveDisabled, diskSaveConfirm, diskOverwrite,
            diskLoad, diskLoadConfirm, diskLoadDisabled, diskLoadQuick,
            diskDelete, diskDeleteConfirm,

    essence, essenceUncoloured,
            itemsOnFloorIcon,

    cornerGlowNight, cornerGlowTwilight, cornerGlowAlwaysLight,

    drinkSmall, drink,

    dice1, dice2, dice3, dice4, dice5, dice6, diceGlow,

    playerMapIconMasculine,
            playerMapIconAndrogynous,
            playerMapIconFeminine,
            playerMapDangerousIcon,

    raceBackground,
            raceBackgroundHalf,
            raceBackgroundSlime,
            raceBackgroundDoll,
            raceBackgroundDemon,
            raceUnknown,
            raceDobermann,
            raceDobermannDesaturated,
            raceWisp,

    raceBackgroundFungus,
            raceBackgroundPlant,
            raceBackgroundRubber,

    perkTreeArrow, spellOverlay,

    weatherDayClear, weatherDayCloud, weatherDayRain, weatherDaySnow, weatherDayStormIncoming, weatherDayStorm, weatherDayStormProtected,
            weatherNightClear, weatherNightCloud, weatherNightRain, weatherNightSnow, weatherNightStormIncoming, weatherNightStorm, weatherNightStormProtected,

    womensWatchHourHand, womensWatchMinuteHand, mensWatchHourHand, mensWatchMinuteHand,

    protectionEnabled, protectionDisabled, tattoo,

    responseCombat, responseSex, responseLocked, responseUnlocked, responseUnlockedDisabled, responseOption, responseOptionDisabled, responseCorruptionBypass,
            responseSubResist, responseSubNormal, responseSubEager,
            responseDomGentle, responseDomNormal, responseDomRough,
            responseSexSwitch, responseSexAdditional,

    NPCWarningMale, NPCWarningFemale, NPCWarningDemon,

    stopwatch,

    counterZero, counterOne, counterTwo, counterThree, counterFour, counterFive, counterFivePlus,
            counterZeroDisabled, counterOneDisabled, counterTwoDisabled, counterThreeDisabled, counterFourDisabled, counterFiveDisabled, counterFivePlusDisabled,

    scaleZero, scaleOne, scaleTwo, scaleThree, scaleFour,
            scaleZeroDisabled, scaleOneDisabled, scaleTwoDisabled, scaleThreeDisabled, scaleFourDisabled,

    slaveBuy, slaveBuyDisabled, slaveSell, slaveSellDisabled, slaveInspect, slaveInspectDisabled, slaveJob, slaveJobDisabled,
            slavePermissionsDisabled, slavePermissions, slaveTransfer, slaveTransferDisabled, slaveCosmetics, slaveCosmeticsDisabled,

    transactionBuy, transactionBuyDisabled, transactionBid, transactionBidDisabled, transactionSell, transactionSellDisabled,

    // Effects:
    creampie, creampieMasochist,
            fluidIngested, fluidIngestedMasochist,

    // Items:

    hypnoWatchBase, hypnoWatchGynephilic, hypnoWatchAmbiphilic, hypnoWatchAndrophilic, hypnoWatchSpeechAdd, hypnoWatchSpeechRemove,

    // Sex:
    coverableAreaMouth,
            coverableAreaAnus, coverableAreaAss,
            coverableAreaBreasts, coverableAreaBreastsFlat, coverableAreaNipple,
            coverableAreaBreastsCrotch, coverableAreaUdders, coverableAreaNippleCrotch,
            coverableAreaVagina, coverableAreaUrethraVagina,
            coverableAreaSpinneret,
            coverableAreaMound,
            coverableAreaThighs,
            coverableAreaArmpits,
            coverableAreaUrethraPenis,

    eggIncubation1, eggIncubation2, eggIncubation3,

    penetrationTypeFinger, penetrationTypePenis, penetrationTypeTail, penetrationTypeTailSerpent, penetrationTypeTentacle, penetrationTypeTongue, penetrationTypeFoot, penetrationTypeClit,
            combinationStretching, combinationStretchRecoveryPrevented, combinationTooLoose, combinationWet, combinationDry, combinationDepthMinimum, combinationDepthMaximum,
            stretching, holeTooBig,
            activeSexBackground;

    private Map<Integer, String> youkoTailsMap = new HashMap<>();
    private Map<Integer, String> youkoTailsDesaturatedMap = new HashMap<>();
    private Map<Integer, String> youkoTailsDemonMap = new HashMap<>();


    private Map<Colour, String> refinedBackgroundMap = new HashMap<>();
    private Map<Colour, String> refinedSwirlsMap = new HashMap<>();

    private SVGImages() {

        try {
            InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/flag_us.svg");
            flagUs = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/fist.svg");
            fist = Util.inputStreamToString(is);
            fist = setColour(fist, PresetColour.BASE_BLACK);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/displacedWarningIcon.svg");
            displacedIcon = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/concealed.svg");
            concealedIcon = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/dirtyIcon.svg");
            dirtyIcon = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/lipstickIcon.svg");
            lipstickIcon = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/feminineWarningIcon.svg");
            feminineWarningIcon = Util.inputStreamToString(is);
            feminineWarningIcon = setColour(feminineWarningIcon, PresetColour.FEMININE);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/masculineWarningIcon.svg");
            masculineWarningIcon = Util.inputStreamToString(is);
            masculineWarningIcon = setColour(masculineWarningIcon, PresetColour.BASE_BLUE_LIGHT);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/jinxed.svg");
            jinxedIcon = Util.inputStreamToString(is);
            jinxedIcon = setColour(jinxedIcon, PresetColour.ATTRIBUTE_CORRUPTION);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/tattooSwitchTattoo.svg");
            tattooSwitchTattoo = Util.inputStreamToString(is);
            tattooSwitchTattoo = setColour(tattooSwitchTattoo, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_WHITE, null);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/tattooSwitchClothing.svg");
            tattooSwitchClothing = Util.inputStreamToString(is);
            tattooSwitchClothing = setColour(tattooSwitchClothing, PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/tattooSwitchClothing.svg");
            tattooSwitchClothingHighlight = Util.inputStreamToString(is);
            tattooSwitchClothingHighlight = setColour(tattooSwitchClothingHighlight, PresetColour.CLOTHING_WHITE, PresetColour.GENERIC_EXCELLENT, PresetColour.CLOTHING_BLACK);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/InventoryIcons/scar.svg");
            scarIcon = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/menu.svg");
            menuIcon = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/inventory.svg");
            inventoryIcon = Util.inputStreamToString(is);
            inventoryIcon = setColour(inventoryIcon, PresetColour.BASE_BLACK);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/inventory.svg");
            inventoryIconDisabled = Util.inputStreamToString(is);
            inventoryIconDisabled = setColour(inventoryIconDisabled, PresetColour.BASE_PITCH_BLACK);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/questInventory.svg");
            questInventoryIcon = Util.inputStreamToString(is);
            questInventoryIcon = setColour(questInventoryIcon, PresetColour.BASE_BLACK);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/questInventory.svg");
            questInventoryIconDisabled = Util.inputStreamToString(is);
            questInventoryIconDisabled = setColour(questInventoryIconDisabled, PresetColour.BASE_PITCH_BLACK);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/crafting/essenceArcane.svg");
            essence = Util.inputStreamToString(is);
            essence = setColour(essence, PresetColour.GENERIC_ARCANE);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/crafting/essenceArcane.svg");
            essenceUncoloured = Util.inputStreamToString(is);
            essenceUncoloured = setColour(essenceUncoloured, PresetColour.BASE_GREY);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/itemsOnFloor.svg");
            itemsOnFloorIcon = Util.inputStreamToString(is);
            itemsOnFloorIcon = setColour(itemsOnFloorIcon, PresetColour.BASE_BLACK, PresetColour.BASE_YELLOW, PresetColour.BASE_BLACK);


            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/cornerGlow.svg");
            cornerGlowNight = Util.inputStreamToString(is);
            cornerGlowNight = setColour(cornerGlowNight, PresetColour.BASE_PITCH_BLACK);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/cornerGlowLight.svg");
            cornerGlowTwilight = Util.inputStreamToString(is);
            cornerGlowTwilight = setColour(cornerGlowTwilight, PresetColour.BASE_PITCH_BLACK);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/cornerGlowAlwaysLight.svg");
            cornerGlowAlwaysLight = Util.inputStreamToString(is);
            cornerGlowAlwaysLight = setColour(cornerGlowAlwaysLight, PresetColour.BASE_PITCH_BLACK);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/drink_small.svg");
            drinkSmall = Util.inputStreamToString(is);
            drinkSmall = setColour(drinkSmall, PresetColour.BASE_WHITE);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/drink.svg");
            drink = Util.inputStreamToString(is);
            drink = setColour(drink, PresetColour.BASE_WHITE);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/dice1.svg");
            dice1 = Util.inputStreamToString(is);
            dice1 = setColour(dice1, PresetColour.BASE_WHITE);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/dice2.svg");
            dice2 = Util.inputStreamToString(is);
            dice2 = setColour(dice2, PresetColour.BASE_WHITE);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/dice3.svg");
            dice3 = Util.inputStreamToString(is);
            dice3 = setColour(dice3, PresetColour.BASE_WHITE);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/dice4.svg");
            dice4 = Util.inputStreamToString(is);
            dice4 = setColour(dice4, PresetColour.BASE_WHITE);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/dice5.svg");
            dice5 = Util.inputStreamToString(is);
            dice5 = setColour(dice5, PresetColour.BASE_WHITE);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/dice6.svg");
            dice6 = Util.inputStreamToString(is);
            dice6 = setColour(dice6, PresetColour.BASE_WHITE);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/glow.svg");
            diceGlow = Util.inputStreamToString(is);
            diceGlow = setColour(diceGlow, PresetColour.BASE_GOLD);

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
            copyIcon = setColour(copyIcon, PresetColour.BASE_BLACK);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/export.svg");
            exportIcon = Util.inputStreamToString(is);
            exportIcon = setColour(exportIcon, PresetColour.BASE_BLACK);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/information.svg");
            informationIcon = Util.inputStreamToString(is);
            informationIcon = setColour(informationIcon, PresetColour.BASE_BLACK);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/add.svg");
            addIcon = Util.inputStreamToString(is);
            addIcon = setColour(addIcon, PresetColour.BASE_BLACK);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/calendar.svg");
            calendarIcon = Util.inputStreamToString(is);
            calendarIcon = setColour(calendarIcon, PresetColour.BASE_CRIMSON);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskSave.svg");
            diskSave = Util.inputStreamToString(is);
            diskSave = setColour(diskSave, PresetColour.BASE_BLACK, PresetColour.BASE_YELLOW_LIGHT, PresetColour.BASE_GREY);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskSave.svg");
            diskSaveDisabled = Util.inputStreamToString(is);
            diskSaveDisabled = setColour(diskSaveDisabled, PresetColour.BASE_GREY, PresetColour.BASE_GREY, PresetColour.BASE_GREY);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskSave.svg");
            diskOverwrite = Util.inputStreamToString(is);
            diskOverwrite = setColour(diskOverwrite, PresetColour.BASE_BLACK, PresetColour.BASE_YELLOW_LIGHT, PresetColour.BASE_GREY);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskSave.svg");
            diskSaveConfirm = Util.inputStreamToString(is);
            diskSaveConfirm = setColour(diskSaveConfirm, PresetColour.GENERIC_EXCELLENT, PresetColour.BASE_YELLOW_LIGHT, PresetColour.BASE_GREY);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskLoad.svg");
            diskLoad = Util.inputStreamToString(is);
            diskLoad = setColour(diskLoad, PresetColour.BASE_BLUE_LIGHT);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskLoad.svg");
            diskLoadConfirm = Util.inputStreamToString(is);
            diskLoadConfirm = setColour(diskLoadConfirm, PresetColour.GENERIC_EXCELLENT);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskLoad.svg");
            diskLoadDisabled = Util.inputStreamToString(is);
            diskLoadDisabled = setColour(diskLoadDisabled, PresetColour.BASE_GREY);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskLoad.svg");
            diskLoadQuick = Util.inputStreamToString(is);
            diskLoadQuick = setColour(diskLoadQuick, PresetColour.BASE_WHITE);


            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskDelete.svg");
            diskDelete = Util.inputStreamToString(is);
            diskDelete = setColour(diskDelete, PresetColour.BASE_CRIMSON);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/diskDelete.svg");
            diskDeleteConfirm = Util.inputStreamToString(is);
            diskDeleteConfirm = setColour(diskDeleteConfirm, PresetColour.GENERIC_EXCELLENT);


            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/map/playerIcon.svg");
            playerMapIconMasculine = Util.inputStreamToString(is);
            playerMapIconMasculine = setColour(playerMapIconMasculine, PresetColour.MASCULINE_PLUS);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/map/playerIcon.svg");
            playerMapIconAndrogynous = Util.inputStreamToString(is);
            playerMapIconAndrogynous = setColour(playerMapIconAndrogynous, PresetColour.ANDROGYNOUS);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/map/playerIcon.svg");
            playerMapIconFeminine = Util.inputStreamToString(is);
            playerMapIconFeminine = setColour(playerMapIconFeminine, PresetColour.FEMININE_PLUS);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/map/playerIcon.svg");
            playerMapDangerousIcon = Util.inputStreamToString(is);
            playerMapDangerousIcon = setColour(playerMapDangerousIcon, PresetColour.GENERIC_BAD);


            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceBackground.svg");
            raceBackground = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceBackgroundHalf.svg");
            raceBackgroundHalf = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceBackgroundSlime.svg");
            raceBackgroundSlime = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceBackgroundDoll.svg");
            raceBackgroundDoll = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceBackgroundDemon.svg");
            raceBackgroundDemon = Util.inputStreamToString(is);


            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceUnknown.svg");
            raceUnknown = Util.inputStreamToString(is);
            raceUnknown = setColour(raceUnknown, PresetColour.RACE_UNKNOWN);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceDogMorphDobermann.svg");
            raceDobermann = Util.inputStreamToString(is);
            raceDobermann = setColour(raceDobermann, PresetColour.RACE_DOG_MORPH);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceDogMorphDobermann.svg");
            raceDobermannDesaturated = Util.inputStreamToString(is);
            raceDobermannDesaturated = setColour(raceDobermannDesaturated, PresetColour.BASE_GREY);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceWisp.svg");
            raceWisp = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceBackgroundFungus.svg");
            raceBackgroundFungus = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceBackgroundPlant.svg");
            raceBackgroundPlant = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceBackgroundRubber.svg");
            raceBackgroundRubber = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/perkTreeArrow.svg");
            perkTreeArrow = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/combat/spell/spell_overlay.svg");
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
            weatherDayStormProtected = setColour(weatherDayStormProtected, PresetColour.CLOTHING_BLUE_LIGHT);

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
            weatherNightStormProtected = setColour(weatherNightStormProtected, PresetColour.CLOTHING_BLUE_LIGHT);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/protection.svg");
            protectionDisabled = Util.inputStreamToString(is);
            protectionDisabled = setColour(protectionDisabled, PresetColour.CLOTHING_BLACK);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/protection.svg");
            protectionEnabled = Util.inputStreamToString(is);
            protectionEnabled = setColour(protectionEnabled, PresetColour.GENERIC_GOOD);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/tattoo.svg");
            tattoo = Util.inputStreamToString(is);
            tattoo = setColour(tattoo, PresetColour.CLOTHING_BLACK);


            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseCombat.svg");
            responseCombat = Util.inputStreamToString(is);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseSex.svg");
            responseSex = Util.inputStreamToString(is);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseLocked.svg");
            responseLocked = Util.inputStreamToString(is);
            responseLocked = setColour(responseLocked, PresetColour.GENERIC_BAD);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseUnlocked.svg");
            responseUnlocked = Util.inputStreamToString(is);
            responseUnlocked = setColour(responseUnlocked, PresetColour.GENERIC_GOOD);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseUnlocked.svg");
            responseUnlockedDisabled = Util.inputStreamToString(is);
            responseUnlockedDisabled = setColour(responseUnlockedDisabled, PresetColour.BASE_BLACK);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseOption.svg");
            responseOption = Util.inputStreamToString(is);
            responseOption = setColour(responseOption, PresetColour.GENERIC_GOOD);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseOption.svg");
            responseOptionDisabled = Util.inputStreamToString(is);
            responseOptionDisabled = setColour(responseOptionDisabled, PresetColour.BASE_BLACK);


            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseCorruptionBypass.svg");
            responseCorruptionBypass = Util.inputStreamToString(is);
            responseCorruptionBypass = setColour(responseCorruptionBypass, PresetColour.GENERIC_ARCANE);

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

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseSexSwitch.svg");
            responseSexSwitch = Util.inputStreamToString(is);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseSexAdditional.svg");
            responseSexAdditional = Util.inputStreamToString(is);


            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseNPC.svg");
            NPCWarningMale = Util.inputStreamToString(is);
            NPCWarningMale = setColour(NPCWarningMale, PresetColour.MASCULINE_PLUS);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseNPC.svg");
            NPCWarningFemale = Util.inputStreamToString(is);
            NPCWarningFemale = setColour(NPCWarningFemale, PresetColour.FEMININE_PLUS);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/responseNPC.svg");
            NPCWarningDemon = Util.inputStreamToString(is);
            NPCWarningDemon = setColour(NPCWarningDemon, PresetColour.GENERIC_ARCANE);


            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/stopwatch.svg");
            stopwatch = Util.inputStreamToString(is);
            stopwatch = setColour(stopwatch, PresetColour.BASE_GREY);

            // scales:

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay0.svg");
            counterZero = Util.inputStreamToString(is);
            counterZero = setColour(counterZero, PresetColour.BASE_PINK);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay1.svg");
            counterOne = Util.inputStreamToString(is);
            counterOne = setColour(counterOne, PresetColour.BASE_PINK);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay2.svg");
            counterTwo = Util.inputStreamToString(is);
            counterTwo = setColour(counterTwo, PresetColour.BASE_PINK);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay3.svg");
            counterThree = Util.inputStreamToString(is);
            counterThree = setColour(counterThree, PresetColour.BASE_PINK);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay4.svg");
            counterFour = Util.inputStreamToString(is);
            counterFour = setColour(counterFour, PresetColour.BASE_PINK);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay5.svg");
            counterFive = Util.inputStreamToString(is);
            counterFive = setColour(counterFive, PresetColour.BASE_PINK);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay5Plus.svg");
            counterFivePlus = Util.inputStreamToString(is);
            counterFivePlus = setColour(counterFivePlus, PresetColour.BASE_PINK);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay0.svg");
            counterZeroDisabled = Util.inputStreamToString(is);
            counterZeroDisabled = setColour(counterZeroDisabled, PresetColour.BASE_BLACK);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay1.svg");
            counterOneDisabled = Util.inputStreamToString(is);
            counterOneDisabled = setColour(counterOneDisabled, PresetColour.BASE_BLACK);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay2.svg");
            counterTwoDisabled = Util.inputStreamToString(is);
            counterTwoDisabled = setColour(counterTwoDisabled, PresetColour.BASE_BLACK);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay3.svg");
            counterThreeDisabled = Util.inputStreamToString(is);
            counterThreeDisabled = setColour(counterThreeDisabled, PresetColour.BASE_BLACK);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay4.svg");
            counterFourDisabled = Util.inputStreamToString(is);
            counterFourDisabled = setColour(counterFourDisabled, PresetColour.BASE_BLACK);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay5.svg");
            counterFiveDisabled = Util.inputStreamToString(is);
            counterFiveDisabled = setColour(counterFiveDisabled, PresetColour.BASE_BLACK);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/overlay5Plus.svg");
            counterFivePlusDisabled = Util.inputStreamToString(is);
            counterFivePlusDisabled = setColour(counterFivePlusDisabled, PresetColour.BASE_BLACK);


            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_zero.svg");
            scaleZero = Util.inputStreamToString(is);
            scaleZero = setColour(scaleZero, PresetColour.BASE_MAGENTA);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_one.svg");
            scaleOne = Util.inputStreamToString(is);
            scaleOne = setColour(scaleOne, PresetColour.BASE_GREEN);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_two.svg");
            scaleTwo = Util.inputStreamToString(is);
            scaleTwo = setColour(scaleTwo, PresetColour.BASE_GREEN);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_three.svg");
            scaleThree = Util.inputStreamToString(is);
            scaleThree = setColour(scaleThree, PresetColour.BASE_GREEN);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_four.svg");
            scaleFour = Util.inputStreamToString(is);
            scaleFour = setColour(scaleFour, PresetColour.BASE_GREEN);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_zero.svg");
            scaleZeroDisabled = Util.inputStreamToString(is);
            scaleZeroDisabled = setColour(scaleZeroDisabled, PresetColour.BASE_GREY);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_one.svg");
            scaleOneDisabled = Util.inputStreamToString(is);
            scaleOneDisabled = setColour(scaleOneDisabled, PresetColour.BASE_GREY);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_two.svg");
            scaleTwoDisabled = Util.inputStreamToString(is);
            scaleTwoDisabled = setColour(scaleTwoDisabled, PresetColour.BASE_GREY);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_three.svg");
            scaleThreeDisabled = Util.inputStreamToString(is);
            scaleThreeDisabled = setColour(scaleThreeDisabled, PresetColour.BASE_GREY);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/scale_four.svg");
            scaleFourDisabled = Util.inputStreamToString(is);
            scaleFourDisabled = setColour(scaleFourDisabled, PresetColour.BASE_GREY);


            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveBuy.svg");
            slaveBuy = Util.inputStreamToString(is);
            slaveBuy = setColour(slaveBuy, PresetColour.GENERIC_BAD);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveBuyDisabled.svg");
            slaveBuyDisabled = Util.inputStreamToString(is);
            slaveBuyDisabled = setColour(slaveBuyDisabled, PresetColour.BASE_GREY);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveSell.svg");
            slaveSell = Util.inputStreamToString(is);
            slaveSell = setColour(slaveSell, PresetColour.GENERIC_GOOD);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveSellDisabled.svg");
            slaveSellDisabled = Util.inputStreamToString(is);
            slaveSellDisabled = setColour(slaveSellDisabled, PresetColour.BASE_GREY);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveInspect.svg");
            slaveInspect = Util.inputStreamToString(is);
            slaveInspect = setColour(slaveInspect, PresetColour.BASE_BLUE_STEEL);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveInspect.svg");
            slaveInspectDisabled = Util.inputStreamToString(is);
            slaveInspectDisabled = setColour(slaveInspectDisabled, PresetColour.BASE_GREY);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveJob.svg");
            slaveJob = Util.inputStreamToString(is);
            slaveJob = setColour(slaveJob, PresetColour.BASE_BROWN_DARK);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveJob.svg");
            slaveJobDisabled = Util.inputStreamToString(is);
            slaveJobDisabled = setColour(slaveJobDisabled, PresetColour.BASE_GREY);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slavePermissions.svg");
            slavePermissions = Util.inputStreamToString(is);
            slavePermissions = setColour(slavePermissions, PresetColour.BASE_GREY);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slavePermissionsDisabled.svg");
            slavePermissionsDisabled = Util.inputStreamToString(is);
            slavePermissionsDisabled = setColour(slavePermissionsDisabled, PresetColour.BASE_GREY);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveTransfer.svg");
            slaveTransfer = Util.inputStreamToString(is);
            slaveTransfer = setColour(slaveTransfer, PresetColour.GENERIC_GOOD);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveTransfer.svg");
            slaveTransferDisabled = Util.inputStreamToString(is);
            slaveTransferDisabled = setColour(slaveTransferDisabled, PresetColour.BASE_GREY);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveCosmetics.svg");
            slaveCosmetics = Util.inputStreamToString(is);
            slaveCosmetics = setColour(slaveCosmetics, PresetColour.BASE_CRIMSON);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/slaveCosmetics.svg");
            slaveCosmeticsDisabled = Util.inputStreamToString(is);
            slaveCosmeticsDisabled = setColour(slaveCosmeticsDisabled, PresetColour.BASE_GREY);


            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/transactionBuy.svg");
            transactionBuy = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/transactionBuyDisabled.svg");
            transactionBuyDisabled = Util.inputStreamToString(is);
            transactionBuyDisabled = setColour(transactionBuyDisabled, PresetColour.BASE_GREY);


            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/transactionBid.svg");
            transactionBid = Util.inputStreamToString(is);
            transactionBid = setColour(transactionBid, PresetColour.BASE_BROWN);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/transactionBid.svg");
            transactionBidDisabled = Util.inputStreamToString(is);
            transactionBidDisabled = setColour(transactionBidDisabled, PresetColour.BASE_GREY);


            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/transactionSell.svg");
            transactionSell = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/transactionSellDisabled.svg");
            transactionSellDisabled = Util.inputStreamToString(is);
            transactionSellDisabled = setColour(transactionSellDisabled, PresetColour.BASE_GREY);


            for (int i = 1; i <= 9; i++) {
                is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceFoxTail" + i + ".svg");
                String svg = Util.inputStreamToString(is);
                svg = SvgUtil.colourReplacement("foxTail" + i,
                        PresetColour.RACE_FOX_MORPH,
                        PresetColour.RACE_FOX_MORPH,
                        PresetColour.RACE_FOX_MORPH,
                        svg);

                youkoTailsMap.put(i, svg);

                is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceFoxTail" + i + ".svg");
                svg = Util.inputStreamToString(is);
                svg = SvgUtil.colourReplacement("foxTail" + i,
                        PresetColour.RACE_HALF_DEMON,
                        PresetColour.RACE_HALF_DEMON,
                        PresetColour.RACE_HALF_DEMON,
                        svg);
                youkoTailsDemonMap.put(i, svg);

                is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceFoxTail" + i + ".svg");
                svg = Util.inputStreamToString(is);
                svg = SvgUtil.colourReplacement("foxTail" + i,
                        PresetColour.BASE_GREY,
                        PresetColour.BASE_GREY,
                        PresetColour.BASE_GREY,
                        svg);

                youkoTailsDesaturatedMap.put(i, svg);
            }


            // Effects:

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/creampie.svg");
            creampie = Util.inputStreamToString(is);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/creampieMasochist.svg");
            creampieMasochist = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/fluidIngested.svg");
            fluidIngested = Util.inputStreamToString(is);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/fluidIngestedMasochist.svg");
            fluidIngestedMasochist = Util.inputStreamToString(is);


            // Items:

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/hypnoClockBase.svg");
            hypnoWatchBase = Util.inputStreamToString(is);
            hypnoWatchBase = setColour(hypnoWatchBase, PresetColour.BASE_GREY);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/hypnoClockEnchanted.svg");
            hypnoWatchGynephilic = Util.inputStreamToString(is);
            hypnoWatchGynephilic = SvgUtil.colourReplacement(null, PresetColour.FEMININE_PLUS, PresetColour.CLOTHING_ROSE_GOLD, null, hypnoWatchGynephilic);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/hypnoClockEnchanted.svg");
            hypnoWatchAmbiphilic = Util.inputStreamToString(is);
            hypnoWatchAmbiphilic = SvgUtil.colourReplacement(null, PresetColour.ANDROGYNOUS, PresetColour.CLOTHING_SILVER, null, hypnoWatchAmbiphilic);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/hypnoClockEnchanted.svg");
            hypnoWatchAndrophilic = Util.inputStreamToString(is);
            hypnoWatchAndrophilic = SvgUtil.colourReplacement(null, PresetColour.MASCULINE_PLUS, PresetColour.CLOTHING_BLACK_STEEL, null, hypnoWatchAndrophilic);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/hypnoClockEnchanted.svg");
            hypnoWatchSpeechRemove = Util.inputStreamToString(is);
            hypnoWatchSpeechRemove = SvgUtil.colourReplacement(null, PresetColour.BASE_RED, PresetColour.CLOTHING_GOLD, null, hypnoWatchSpeechRemove);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/hypnoClockEnchanted.svg");
            hypnoWatchSpeechAdd = Util.inputStreamToString(is);
            hypnoWatchSpeechAdd = SvgUtil.colourReplacement(null, PresetColour.BASE_GREEN, PresetColour.CLOTHING_GOLD, null, hypnoWatchSpeechAdd);

            // Sex:

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaAnus.svg");
            coverableAreaAnus = Util.inputStreamToString(is);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaAss.svg");
            coverableAreaAss = Util.inputStreamToString(is);
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
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaMound.svg");
            coverableAreaMound = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaSpinneret.svg");
            coverableAreaSpinneret = Util.inputStreamToString(is);


            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaBreastsCrotch.svg");
            coverableAreaBreastsCrotch = Util.inputStreamToString(is);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaUdders.svg");
            coverableAreaUdders = Util.inputStreamToString(is);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaNippleCrotch.svg");
            coverableAreaNippleCrotch = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaUrethraVagina.svg");
            coverableAreaUrethraVagina = Util.inputStreamToString(is);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaUrethraPenis.svg");
            coverableAreaUrethraPenis = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaThighs.svg");
            coverableAreaThighs = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/coverableAreaArmpits.svg");
            coverableAreaArmpits = Util.inputStreamToString(is);


            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/penetrationTypeFinger.svg");
            penetrationTypeFinger = Util.inputStreamToString(is);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/penetrationTypePenis.svg");
            penetrationTypePenis = Util.inputStreamToString(is);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/penetrationTypeTail.svg");
            penetrationTypeTail = Util.inputStreamToString(is);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/penetrationTypeTailSerpent.svg");
            penetrationTypeTailSerpent = Util.inputStreamToString(is);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/penetrationTypeTentacle.svg");
            penetrationTypeTentacle = Util.inputStreamToString(is);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/penetrationTypeTongue.svg");
            penetrationTypeTongue = Util.inputStreamToString(is);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/penetrationTypeFoot.svg");
            penetrationTypeFoot = Util.inputStreamToString(is);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/penetrationTypeClit.svg");
            penetrationTypeClit = Util.inputStreamToString(is);


            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/incubation1.svg");
            eggIncubation1 = Util.inputStreamToString(is);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/incubation2.svg");
            eggIncubation2 = Util.inputStreamToString(is);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/incubation3.svg");
            eggIncubation3 = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/combinationStretching.svg");
            combinationStretching = Util.inputStreamToString(is);
            combinationStretching = SvgUtil.colourReplacement("", PresetColour.BASE_PINK_DEEP, combinationStretching);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/combinationStretchRecoveryPrevented.svg");
            combinationStretchRecoveryPrevented = Util.inputStreamToString(is);
            combinationStretchRecoveryPrevented = SvgUtil.colourReplacement("", PresetColour.BASE_PINK_DEEP, PresetColour.GENERIC_BAD, null, combinationStretchRecoveryPrevented);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/combinationTooLoose.svg");
            combinationTooLoose = Util.inputStreamToString(is);
            combinationTooLoose = SvgUtil.colourReplacement("", PresetColour.BASE_RED, combinationTooLoose);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/combinationWet.svg");
            combinationWet = Util.inputStreamToString(is);
            combinationWet = SvgUtil.colourReplacement("", PresetColour.BASE_BLUE_STEEL, combinationWet);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/combinationDry.svg");
            combinationDry = Util.inputStreamToString(is);
            combinationDry = SvgUtil.colourReplacement("", PresetColour.BASE_BLUE_STEEL, PresetColour.BASE_RED, null, combinationDry);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/combinationDepthMinimum.svg");
            combinationDepthMinimum = Util.inputStreamToString(is);
            combinationDepthMinimum = SvgUtil.colourReplacement("", PresetColour.BASE_PINK_LIGHT, combinationDepthMinimum);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/combinationDepthMaximum.svg");
            combinationDepthMaximum = Util.inputStreamToString(is);
            combinationDepthMaximum = SvgUtil.colourReplacement("", PresetColour.BASE_CRIMSON, combinationDepthMaximum);


            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/stretching.svg");
            stretching = Util.inputStreamToString(is);
            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/holeTooBig.svg");
            holeTooBig = Util.inputStreamToString(is);

            is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/sexEffects/active_background.svg");
            activeSexBackground = Util.inputStreamToString(is);


            String tempString = "";
            for (AbstractItemEffectType effect : ItemEffectType.getAllEffectTypes()) {
                is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/refined_background.svg");
                tempString = Util.inputStreamToString(is);
                tempString = setColour(tempString, effect.getColour());

                refinedBackgroundMap.put(effect.getColour(), tempString);
            }

            tempString = "";
            for (TFModifier secondaryModifier : TFModifier.values()) {
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
        s = SvgUtil.colourReplacement(null, colourShade, s);
        return s;
    }

    private String setColour(String stringSVG, Colour colour1, Colour colour2, Colour colour3) {
        String s = stringSVG;
        s = SvgUtil.colourReplacement(null, colour1, colour2, colour3, s);
        return s;
    }

    public String getFlagUs() {
        return flagUs;
    }

    public String getFist() {
        return fist;
    }

    public String getDisplacedIcon() {
        return displacedIcon;
    }

    public String getConcealedIcon() {
        return concealedIcon;
    }

    public String getDirtyIcon() {
        return dirtyIcon;
    }

    public String getLipstickIcon(Covering covering) {
        return setColour(lipstickIcon, covering.getPrimaryColour());
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

    public String getTattooSwitchTattoo() {
        return tattooSwitchTattoo;
    }

    public String getTattooSwitchClothing(GameCharacter characterBeingRendered) {
        // Didn't look good
//		if(characterBeingRendered.hasAnyTattoos()) {
//			return tattooSwitchClothingHighlight;
//		}
        return tattooSwitchClothing;
    }

    public String getScarIcon() {
        return scarIcon;
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

    public String getQuestInventoryIcon() {
        return questInventoryIcon;
    }

    public String getQuestInventoryIconDisabled() {
        return questInventoryIconDisabled;
    }

    public String getEssence() {
        return essence;
    }

    public String getEssenceUncoloured() {
        return essenceUncoloured;
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

    public String getInformationIcon() {
        return informationIcon;
    }

    public String getAddIcon() {
        return addIcon;
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

    public String getDiskLoadDisabled() {
        return diskLoadDisabled;
    }

    public String getDiskLoadQuick() {
        return diskLoadQuick;
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

    public String getCornerGlowNight() {
        return cornerGlowNight;
    }

    public String getCornerGlowTwilight() {
        return cornerGlowTwilight;
    }

    public String getCornerGlowAlwaysLight() {
        return cornerGlowAlwaysLight;
    }

    public String getPlayerMapIconMasculine() {
        return playerMapIconMasculine;
    }

    public String getPlayerMapIconAndrogynous() {
        return playerMapIconAndrogynous;
    }

    public String getPlayerMapIconFeminine() {
        return playerMapIconFeminine;
    }

    public String getPlayerMapDangerousIcon() {
        return playerMapDangerousIcon;
    }

    public String getPerkTreeArrow() {
        return perkTreeArrow;
    }

    public String getSpellOverlay() {
        return spellOverlay;
    }

    public String getWomensWatchHourHand(List<Colour> colours, List<ColourReplacement> replacements) {
        return SvgUtil.colourReplacement("womanWatchHour", colours, replacements, womensWatchHourHand);
    }

    public String getWomensWatchMinuteHand(List<Colour> colours, List<ColourReplacement> replacements) {
        return SvgUtil.colourReplacement("womanWatchMinute", colours, replacements, womensWatchMinuteHand);
    }

    public String getMensWatchHourHand(List<Colour> colours, List<ColourReplacement> replacements) {
        return SvgUtil.colourReplacement("manWatchHour", colours, replacements, mensWatchHourHand);
    }

    public String getMensWatchMinuteHand(List<Colour> colours, List<ColourReplacement> replacements) {
        return SvgUtil.colourReplacement("manWatchMinute", colours, replacements, mensWatchMinuteHand);
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

    public String getResponseSexSwitch() {
        return responseSexSwitch;
    }

    public String getResponseSexAdditional() {
        return responseSexAdditional;
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

    public String getCoverableAreaAss() {
        return coverableAreaAss;
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

    public String getCoverableAreaBreastsCrotch() {
        return coverableAreaBreastsCrotch;
    }

    public String getCoverableAreaUdders() {
        return coverableAreaUdders;
    }

    public String getCoverableAreaNippleCrotch() {
        return coverableAreaNippleCrotch;
    }

    public String getCoverableAreaVagina() {
        return coverableAreaVagina;
    }

    public String getCoverableAreaSpinneret() {
        return coverableAreaSpinneret;
    }

    public String getCoverableAreaMound() {
        return coverableAreaMound;
    }

    public String getCoverableAreaUrethraVagina() {
        return coverableAreaUrethraVagina;
    }

    public String getCoverableAreaUrethraPenis() {
        return coverableAreaUrethraPenis;
    }

    public String getCoverableAreaThighs() {
        return coverableAreaThighs;
    }

    public String getCoverableAreaArmpits() {
        return coverableAreaArmpits;
    }

    public String getPenetrationTypeFinger() {
        return penetrationTypeFinger;
    }

    public String getPenetrationTypeTail(boolean serpentTail) {
        if (serpentTail) {
            return penetrationTypeTailSerpent;
        }
        return penetrationTypeTail;
    }

    public String getPenetrationTypeTentacle() {
        return penetrationTypeTentacle;
    }

    public String getPenetrationTypeTongue() {
        return penetrationTypeTongue;
    }

    public String getPenetrationTypePenis() {
        return penetrationTypePenis;
    }

    public String getPenetrationTypeFoot() {
        return penetrationTypeFoot;
    }

    public String getPenetrationTypeClit() {
        return penetrationTypeClit;
    }

    public String getCombinationStretching() {
        return combinationStretching;
    }

    public String getCombinationStretchRecoveryPrevented() {
        return combinationStretchRecoveryPrevented;
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

    public String getCombinationDepthMinimum() {
        return combinationDepthMinimum;
    }

    public String getCombinationDepthMaximum() {
        return combinationDepthMaximum;
    }

    public String getEggIncubation(int stage) {
        if (stage <= 1) {
            return eggIncubation1;
        } else if (stage == 2) {
            return eggIncubation2;
        } else {
            return eggIncubation3;
        }
    }

    public String getStretching() {
        return stretching;
    }

    public String getHoleTooBig() {
        return holeTooBig;
    }

    public String getActiveSexBackground() {
        return activeSexBackground;
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

    public String getHypnoWatchSpeechAdd() {
        return hypnoWatchSpeechAdd;
    }

    public String getHypnoWatchSpeechRemove() {
        return hypnoWatchSpeechRemove;
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

    public String getRaceBackground() {
        return raceBackground;
    }

    public String getRaceBackgroundHalf() {
        return raceBackgroundHalf;
    }

    public String getRaceBackgroundSlime() {
        return raceBackgroundSlime;
    }

    public String getRaceBackgroundDoll() {
        return raceBackgroundDoll;
    }

    public String getRaceBackgroundDemon() {
        return raceBackgroundDemon;
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

    public String getRaceWisp() {
        return raceWisp;
    }

    public String getRaceBackgroundFungus() {
        return raceBackgroundFungus;
    }

    public String getRaceBackgroundPlant() {
        return raceBackgroundPlant;
    }

    public String getRaceBackgroundRubber() {
        return raceBackgroundRubber;
    }

    public String getCounterZero() {
        return counterZero;
    }

    public String getCounterOne() {
        return counterOne;
    }

    public String getCounterTwo() {
        return counterTwo;
    }

    public String getCounterThree() {
        return counterThree;
    }

    public String getCounterFour() {
        return counterFour;
    }

    public String getCounterFive() {
        return counterFive;
    }

    public String getCounterFivePlus() {
        return counterFivePlus;
    }

    public String getCounterZeroDisabled() {
        return counterZeroDisabled;
    }

    public String getCounterOneDisabled() {
        return counterOneDisabled;
    }

    public String getCounterTwoDisabled() {
        return counterTwoDisabled;
    }

    public String getCounterThreeDisabled() {
        return counterThreeDisabled;
    }

    public String getCounterFourDisabled() {
        return counterFourDisabled;
    }

    public String getCounterFiveDisabled() {
        return counterFiveDisabled;
    }

    public String getCounterFivePlusDisabled() {
        return counterFivePlusDisabled;
    }

    public String getStopwatch() {
        return stopwatch;
    }

    public String getDrinkSmall() {
        return drinkSmall;
    }

    public String getDrink() {
        return drink;
    }

    public String getDice1() {
        return dice1;
    }

    public String getDice2() {
        return dice2;
    }

    public String getDice3() {
        return dice3;
    }

    public String getDice4() {
        return dice4;
    }

    public String getDice5() {
        return dice5;
    }

    public String getDice6() {
        return dice6;
    }

    public String getDiceGlow() {
        return diceGlow;
    }

    public String getFluidIngested() {
        return fluidIngested;
    }

    public String getFluidIngestedMasochist() {
        return fluidIngestedMasochist;
    }

    public String getFoxTail(int i) {
        return youkoTailsMap.get(i);
    }

    public String getFoxTailDesaturated(int i) {
        return youkoTailsDesaturatedMap.get(i);
    }

    public String getFoxTailDemon(int i) {
        return youkoTailsDemonMap.get(i);
    }

}
