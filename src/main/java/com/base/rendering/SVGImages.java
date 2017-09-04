package com.base.rendering;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;

import com.base.game.inventory.enchanting.TFModifier;
import com.base.game.inventory.item.ItemEffectType;
import com.base.utils.Colour;
import com.base.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.79
 * @author Innoxia
 */
public enum SVGImages {
	SVG_IMAGE_PROVIDER;

	private String

	displacedIcon, cummedInIcon, feminineWarningIcon, masculineWarningIcon, jinxedIcon,

			menuIcon, inventoryIcon, inventorySwitchIcon, inventorySwitchOppositeIcon, journalIcon, peopleIcon, zoomInIcon, zoomOutIcon, copyIcon,

			itemsOnFloorIcon,
			
			playerMapIconMasculine, playerPlaceMapIconMasculine,
			playerMapIconAndrogynous, playerPlaceMapIconAndrogynous,
			playerMapIconFeminine, playerPlaceMapIconFeminine,
			playerMapDangerousIcon, playerPlaceMapDangerousIcon,

			perkTreeArrow,

			weatherDayClear, weatherDayCloud, weatherDayRain, weatherDayStormIncoming, weatherDayStorm, weatherDayStormProtected,
			weatherNightClear, weatherNightCloud, weatherNightRain, weatherNightStormIncoming, weatherNightStorm, weatherNightStormProtected,

			womensWatchHourHand, womensWatchMinuteHand, mensWatchHourHand, mensWatchMinuteHand,
			
			protectionEnabled, protectionDisabled, tattoo,
			
			responseCombat, responseSex, responseLocked, responseUnlocked, responseCorruptionBypass,
			responseSubResist, responseSubNormal, responseSubEager,
			responseDomGentle, responseDomNormal, responseDomRough,
			
			NPCWarningMale, NPCWarningFemale, NPCWarningDemon,
			
			// Sex:
			coverableAreaMouth, coverableAreaAnus, coverableAreaNipple, coverableAreaVagina,
			penetrationTypeFinger, penetrationTypePenis, penetrationTypeTail, penetrationTypeTongue,
			combinationStretching, combinationTooLoose, combinationWet, combinationDry,
			stretching, holeTooBig;
	
	private Map<Colour, String> refinedBackgroundMap = new EnumMap<>(Colour.class);
	private Map<Colour, String> refinedSwirlsMap = new EnumMap<>(Colour.class);

	@SuppressWarnings("resource")
	private SVGImages() {

		try {
			InputStream is = this.getClass().getResourceAsStream("/com/base/res/InventoryIcons/displacedWarningIcon.svg");
			displacedIcon = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/base/res/InventoryIcons/cummedInWarningIcon.svg");
			cummedInIcon = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/base/res/InventoryIcons/feminineWarningIcon.svg");
			feminineWarningIcon = Util.inputStreamToString(is);
			feminineWarningIcon = setColour(feminineWarningIcon, Colour.FEMININE_PLUS);

			is = this.getClass().getResourceAsStream("/com/base/res/InventoryIcons/masculineWarningIcon.svg");
			masculineWarningIcon = Util.inputStreamToString(is);
			masculineWarningIcon = setColour(masculineWarningIcon, Colour.MASCULINE_PLUS);

			is = this.getClass().getResourceAsStream("/com/base/res/InventoryIcons/jinxed.svg");
			jinxedIcon = Util.inputStreamToString(is);
			jinxedIcon = setColour(jinxedIcon, Colour.ATTRIBUTE_CORRUPTION);

			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/menu.svg");
			menuIcon = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/inventory.svg");
			inventoryIcon = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/inventorySwitch.svg");
			inventorySwitchIcon = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/inventorySwitchOpposite.svg");
			inventorySwitchOppositeIcon = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/journal.svg");
			journalIcon = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/people.svg");
			peopleIcon = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/zoomIn.svg");
			zoomInIcon = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/zoomOut.svg");
			zoomOutIcon = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/copy.svg");
			copyIcon = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/itemsOnFloor.svg");
			itemsOnFloorIcon = Util.inputStreamToString(is);
			itemsOnFloorIcon = setColour(itemsOnFloorIcon, Colour.GENERIC_EXCELLENT);
			
			is = this.getClass().getResourceAsStream("/com/base/res/map/playerIcon.svg");
			playerMapIconMasculine = Util.inputStreamToString(is);
			playerMapIconMasculine = setColour(playerMapIconMasculine, Colour.MASCULINE_PLUS);
			
			is = this.getClass().getResourceAsStream("/com/base/res/map/playerPlaceIcon.svg");
			playerPlaceMapIconMasculine = Util.inputStreamToString(is);
			playerPlaceMapIconMasculine = setColour(playerPlaceMapIconMasculine, Colour.MASCULINE_PLUS);
			
			is = this.getClass().getResourceAsStream("/com/base/res/map/playerIcon.svg");
			playerMapIconAndrogynous = Util.inputStreamToString(is);
			playerMapIconAndrogynous = setColour(playerMapIconAndrogynous, Colour.ANDROGYNOUS);
			
			is = this.getClass().getResourceAsStream("/com/base/res/map/playerPlaceIcon.svg");
			playerPlaceMapIconAndrogynous = Util.inputStreamToString(is);
			playerPlaceMapIconAndrogynous = setColour(playerPlaceMapIconAndrogynous, Colour.ANDROGYNOUS);
			
			is = this.getClass().getResourceAsStream("/com/base/res/map/playerIcon.svg");
			playerMapIconFeminine = Util.inputStreamToString(is);
			playerMapIconFeminine = setColour(playerMapIconFeminine, Colour.FEMININE_PLUS);
			
			is = this.getClass().getResourceAsStream("/com/base/res/map/playerPlaceIcon.svg");
			playerPlaceMapIconFeminine = Util.inputStreamToString(is);
			playerPlaceMapIconFeminine = setColour(playerPlaceMapIconFeminine, Colour.FEMININE_PLUS);
			
			is = this.getClass().getResourceAsStream("/com/base/res/map/playerIcon.svg");
			playerMapDangerousIcon = Util.inputStreamToString(is);
			playerMapDangerousIcon = setColour(playerMapDangerousIcon, Colour.GENERIC_BAD);
			
			is = this.getClass().getResourceAsStream("/com/base/res/map/playerPlaceIcon.svg");
			playerPlaceMapDangerousIcon = Util.inputStreamToString(is);
			playerPlaceMapDangerousIcon = setColour(playerPlaceMapDangerousIcon, Colour.GENERIC_BAD);

			
			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/perkTreeArrow.svg");
			perkTreeArrow = Util.inputStreamToString(is);

			is = this.getClass().getResourceAsStream("/com/base/res/clothing/wrist_womens_watch_hourhand.svg");
			womensWatchHourHand = Util.inputStreamToString(is);
			// womensWatchHourHand = setColour(womensWatchHourHand,
			// Colour.ATTRIBUTE_HEALTH);

			is = this.getClass().getResourceAsStream("/com/base/res/clothing/wrist_womens_watch_minutehand.svg");
			womensWatchMinuteHand = Util.inputStreamToString(is);
			// womensWatchMinuteHand = setColour(womensWatchMinuteHand,
			// Colour.ATTRIBUTE_MANA);

			is = this.getClass().getResourceAsStream("/com/base/res/clothing/wrist_mens_watch_hourhand.svg");
			mensWatchHourHand = Util.inputStreamToString(is);
			// mensWatchHourHand = setColour(mensWatchHourHand,
			// Colour.ATTRIBUTE_AGILTY);

			is = this.getClass().getResourceAsStream("/com/base/res/clothing/wrist_mens_watch_minutehand.svg");
			mensWatchMinuteHand = Util.inputStreamToString(is);
			// mensWatchMinuteHand = setColour(mensWatchMinuteHand,
			// Colour.ATTRIBUTE_MANA);

			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/weatherDayClear.svg");
			weatherDayClear = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/weatherDayCloudy.svg");
			weatherDayCloud = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/weatherDayRain.svg");
			weatherDayRain = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/weatherDayStormIncoming.svg");
			weatherDayStormIncoming = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/weatherDayStorm.svg");
			weatherDayStorm = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/weatherDayStormProtected.svg");
			weatherDayStormProtected = Util.inputStreamToString(is);
			weatherDayStormProtected = setColour(weatherDayStormProtected, Colour.CLOTHING_BLUE_LIGHT);

			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/weatherNightClear.svg");
			weatherNightClear = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/weatherNightCloudy.svg");
			weatherNightCloud = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/weatherNightRain.svg");
			weatherNightRain = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/weatherNightStormIncoming.svg");
			weatherNightStormIncoming = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/weatherNightStorm.svg");
			weatherNightStorm = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/weatherNightStormProtected.svg");
			weatherNightStormProtected = Util.inputStreamToString(is);
			weatherNightStormProtected = setColour(weatherNightStormProtected, Colour.CLOTHING_BLUE_LIGHT);
			
			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/protection.svg");
			protectionDisabled = Util.inputStreamToString(is);
			protectionDisabled = setColour(protectionDisabled, Colour.CLOTHING_BLACK);
			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/protection.svg");
			protectionEnabled = Util.inputStreamToString(is);
			protectionEnabled = setColour(protectionEnabled, Colour.GENERIC_GOOD);
			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/tattoo.svg");
			tattoo = Util.inputStreamToString(is);
			tattoo = setColour(tattoo, Colour.CLOTHING_BLACK);
			

			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/responseCombat.svg");
			responseCombat = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/responseSex.svg");
			responseSex = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/responseLocked.svg");
			responseLocked = Util.inputStreamToString(is);
			responseLocked = setColour(responseLocked, Colour.GENERIC_BAD);
			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/responseUnlocked.svg");
			responseUnlocked = Util.inputStreamToString(is);
			responseUnlocked = setColour(responseUnlocked, Colour.GENERIC_GOOD);
			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/responseCorruptionBypass.svg");
			responseCorruptionBypass = Util.inputStreamToString(is);
			responseCorruptionBypass = setColour(responseCorruptionBypass, Colour.GENERIC_ARCANE);
			
			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/responseSubResist.svg");
			responseSubResist = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/responseSubNormal.svg");
			responseSubNormal = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/responseSubEager.svg");
			responseSubEager = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/responseDomGentle.svg");
			responseDomGentle = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/responseDomNormal.svg");
			responseDomNormal = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/responseDomRough.svg");
			responseDomRough = Util.inputStreamToString(is);
			
			
			
			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/responseNPC.svg");
			NPCWarningMale = Util.inputStreamToString(is);
			NPCWarningMale = setColour(NPCWarningMale, Colour.MASCULINE_PLUS);
			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/responseNPC.svg");
			NPCWarningFemale = Util.inputStreamToString(is);
			NPCWarningFemale = setColour(NPCWarningFemale, Colour.FEMININE_PLUS);
			is = this.getClass().getResourceAsStream("/com/base/res/UIElements/responseNPC.svg");
			NPCWarningDemon = Util.inputStreamToString(is);
			NPCWarningDemon = setColour(NPCWarningDemon, Colour.GENERIC_ARCANE);
			
			
			// Sex:
			
			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/sexEffects/coverableAreaAnus.svg");
			coverableAreaAnus = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/sexEffects/coverableAreaMouth.svg");
			coverableAreaMouth = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/sexEffects/coverableAreaNipple.svg");
			coverableAreaNipple = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/sexEffects/coverableAreaVagina.svg");
			coverableAreaVagina = Util.inputStreamToString(is);
			
			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/sexEffects/penetrationTypeFinger.svg");
			penetrationTypeFinger = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/sexEffects/penetrationTypePenis.svg");
			penetrationTypePenis = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/sexEffects/penetrationTypeTail.svg");
			penetrationTypeTail = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/sexEffects/penetrationTypeTongue.svg");
			penetrationTypeTongue = Util.inputStreamToString(is);
			
			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/sexEffects/combinationStretching.svg");
			combinationStretching = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/sexEffects/combinationTooLoose.svg");
			combinationTooLoose = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/sexEffects/combinationWet.svg");
			combinationWet = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/sexEffects/combinationDry.svg");
			combinationDry = Util.inputStreamToString(is);
			
			
			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/sexEffects/stretching.svg");
			stretching = Util.inputStreamToString(is);
			is = this.getClass().getResourceAsStream("/com/base/res/statusEffects/sexEffects/holeTooBig.svg");
			holeTooBig = Util.inputStreamToString(is);
			
			String tempString = "";
			for(ItemEffectType effect : ItemEffectType.values()) {
				is = this.getClass().getResourceAsStream("/com/base/res/items/refined_background.svg");
				tempString = Util.inputStreamToString(is);
				tempString = setColour(tempString, effect.getColour());
				
				refinedBackgroundMap.put(effect.getColour(), tempString);
			}
			
			tempString = "";
			for(TFModifier secondaryModifier : TFModifier.values()) {
				is = this.getClass().getResourceAsStream("/com/base/res/items/refined_swirls.svg");
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

	public String getCoverableAreaVagina() {
		return coverableAreaVagina;
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

}
