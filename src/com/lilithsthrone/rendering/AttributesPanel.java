package com.lilithsthrone.rendering;/*
 * Created by aimozg on 17.11.2017.
 * Confidential until published on GitHub
 */

import com.lilithsthrone.controller.WebEngineEx;
import com.lilithsthrone.game.character.attributes.*;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.MapDisplay;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import javafx.scene.web.WebEngine;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class AttributesPanel extends WebEngineEx {
	private static float
			renderedPlayerStrengthValue = 0,
			renderedPlayerIntelligenceValue = 0,
			renderedPlayerFitnessValue = 0,
			renderedPlayerCorruptionValue = 0,
			renderedPlayerHealthValue = 0,
			renderedPlayerManaValue = 0,
			renderedPlayerStaminaValue = 0,
			renderedPlayerArousalValue = 0;
	private boolean initialized = false;
	private double lastAttributesHeight;
	DialogueNodeOld renderedDialogueNode = null;

	public AttributesPanel(WebEngine engine) {
		super(engine);
		engine.getHistory().setMaxSize(0);
		setTheme();
	}

	private void initialize() {
		StringBuilder uiAttributeSB = new StringBuilder();

		uiAttributeSB.append(
				"<body onLoad='scrollEventLogToBottom()'>"
						+ " <script>"
							+"function scrollEventLogToBottom() {document.getElementById('event-log-inner-id').scrollTop = document.getElementById('event-log-inner-id').scrollHeight;}"
						+ "</script>"
						+ "<div class='full'>"
						// Name box:
						+ "<div class='attribute-container'>"
							+ "<div class='full-width-container'>"
								+ "<p class='data-name character-name'>"
									+ "HeroOrHeroine"
								+ "</p>"
								+ "<div class='overlay' id='EXTRA_ATTRIBUTES'></div>"
							+ "</div>"
							+ "<div class='full-width-container' style='margin:0;padding:0;'>"
								+ "<p style='text-align:center;' class='data-level'>"
									+ "<b>Level (level value)</b> <b style=''>(race-stage)</b> <b style=''>(race-singular-mf-name)</b> "
								+"</p>"
								+ "<div class='barBackgroundExp data-xpbar'></div>"
								+ "<div class='overlay' id='PLAYER_" + Attribute.EXPERIENCE.getName() + "' style='cursor:pointer;'></div>"
							+ "</div>"
							+ "<div class='full-width-container'>"
								+ "<div class='half-width-container data-arcane-essences' style='text-align:center;'>"
									+ "(SVG icon) (amount)"
								+ "</div>"
								+ "<div class='half-width-container data-money' style='text-align:center;'>"
								+ "(SVG icon) (amount)"
							+ "</div>"
						+ "</div>"
					+ "</div>"
		);

		if(Main.mainController.getWebViewAttributes().getHeight()>=750) {
			uiAttributeSB.append(
					"<div class='attribute-container'>"
						+ "<div class='full-width-container' style='margin:0; padding:0;'>"
							+ "<div class='icon small'><div class='icon-content data-strength-icon'>"
								+ "(SVG icon)" + "</div></div>"
							+ "<div class='barBackgroundAtt'>"
								+ "<div class='data-strength-bar' style='height:5vw; background:" + Colour.GENERIC_ATTRIBUTE.toWebHexString() + "; float:left; border-radius: 2px;'></div>"
								+ "</div>"
								+ "<p class='data-strength-value' style='text-align:center; margin:2vw 0 0 0; padding:0;'>"
									+ "(str value))"
								+ "</p>"
							+ "<div class='overlay' id='PLAYER_" + Attribute.STRENGTH.getName() + "'></div>"
						+ "</div>"

						// Intelligence:
						+ "<div class='full-width-container' style='margin:0;padding:0;'>"
							+ "<div class='icon small'><div class='icon-content data-intelligence-icon'>"
								+ "(SVG icon)" + "</div></div>"
							+ "<div class='barBackgroundAtt'>"
								+ "<div class='data-intelligence-bar' style='height:5vw; background:" + Colour.GENERIC_ATTRIBUTE.toWebHexString() + "; float:left; border-radius: 2px;'></div>"
								+ "</div>"
								+ "<p class='data-intelligence-value' style='text-align:center; margin:2vw 0 0 0; padding:0;'>"
									+ "(int value)"
								+ "</p>"
							+ "<div class='overlay' id='PLAYER_" + Attribute.INTELLIGENCE.getName() + "'></div>"
						+ "</div>"

						// Fitness:
						+ "<div class='full-width-container' style='margin:0;padding:0;'>"
							+ "<div class='icon small'><div class='icon-content data-fitness-icon'>"
								+ "(SVG icon)" + "</div></div>"
							+ "<div class='barBackgroundAtt'>"
								+ "<div class='data-fitness-bar' style='height:5vw; background:" + Colour.GENERIC_ATTRIBUTE.toWebHexString() + "; float:left; border-radius: 2px;'></div>"
								+ "</div>"
								+ "<p class='data-fitness-value' style='text-align:center; margin:2vw 0 0 0; padding:0;'>"
									+ "(fit value)"
								+ "</p>"
							+ "<div class='overlay' id='PLAYER_" + Attribute.FITNESS.getName() + "'></div>"
						+ "</div>"

						// Corruption:
						+ "<div class='full-width-container' style='padding:0;'>"
							+ "<div class='icon small'><div class='icon-content data-corruption-icon'>"
								+ "(SVG icon)</div></div>"
							+ "<div class='barBackgroundAtt corruption'>"
								+ "<div class='data-corruption-bar' style='height:5vw; background:"
								+ Attribute.CORRUPTION.getColour().toWebHexString() + "; float:left; border-radius: 2px;'></div>"
								+ "</div>"
								+ "<p class='data-corruption-value' style='text-align:center; margin:2vw 0 0 0; padding:0;'>"
									+ "(cor value)"
								+ "</p>"
							+ "<div class='overlay' id='PLAYER_" + Attribute.CORRUPTION.getName() + "'></div>"
						+ "</div>"

					+ "</div>");
		} else {
			uiAttributeSB.append(
					"<div class='attribute-container'>"
						+ "<div class='quarter-width-container'>"
							+ "<div class='icon' style='width:45%'><div class='icon-content data-strength-icon'>"
								+ "(SVG icon)</div></div>"
							+ "<div style='text-align:center;height:30px;padding:0;margin:0;line-height:30px;'>"+ "<b class='data-strength-value' style='color:" + Attribute.STRENGTH.getColour().toWebHexString() + ";'>"
								+ "(str value)" + "</b>" + "</div>"
							+ "<div class='overlay' id='PLAYER_"+ Attribute.STRENGTH.getName() + "'></div>"
						+ "</div>"

						+ "<div class='quarter-width-container'>"
							+ "<div class='icon' style='width:45%'><div class='icon-content data-intelligence-icon'>"
								+ "(SVG icon)" + "</div></div>"
							+ "<div style='text-align:center;height:30px;padding:0;margin:0;line-height:30px;'>" + "<b class='data-intelligence-value' style='color:" + Attribute.INTELLIGENCE.getColour().toWebHexString() + ";'>"
								+ "(int value)" + "</b>" + "</div>"
							+ "<div class='overlay' id='PLAYER_" + Attribute.INTELLIGENCE.getName() + "'></div>"
						+ "</div>"

						+ "<div class='quarter-width-container'>"
							+ "<div class='icon' style='width:45%'><div class='icon-content data-fitness-icon'>"
								+ "(SVG icon)" + "</div></div>"
							+ "<div style='text-align:center;height:30px;padding:0;margin:0;line-height:30px;'>" + "<b class='data-fitness-value' style='color:" + Attribute.FITNESS.getColour().toWebHexString() + ";'>"
								+ "(fit value)" + "</b>" + "</div>"
							+ "<div class='overlay' id='PLAYER_" + Attribute.FITNESS.getName() + "'></div>"
						+ "</div>"

						+ "<div class='quarter-width-container'>"
							+ "<div class='icon' style='width:45%'><div class='icon-content data-corruption-icon'>"
								+ "(SVG icon)" + "</div></div>"
							+ "<div style='text-align:center;height:30px;padding:0;margin:0;line-height:30px;'>" + "<b class='data-corruption-value style='color:"+ Attribute.CORRUPTION.getColour().toWebHexString() + ";'>"
								+ "(cor value)" + "</b>" + "</div>"
							+ "<div class='overlay' id='PLAYER_" + Attribute.CORRUPTION.getName() + "'></div>"
						+ "</div>"
					+ "</div>"
			);
		}

		// Visible if isInSex()
		uiAttributeSB.append("<div class='visible-if-insex attribute-container'>"
				+ "<p class='data-player-domsub' style='text-align:center;padding:0;margin:0;'>"
					+"(Dominant/submissive partner)"
				+"</p>"
				+ "<p style='text-align:center;padding:0;margin:0;'>"
					+ "<b class='data-player-pace'>(sex pace)</b><b> pace</b>"
				+ "</p>"
				+ "<p style='text-align:center;padding:0;margin:0;'><b>Orgasms: </b><b class='data-player-numorgasms'>(number of orgasms)</b></p>"

				// Arousal:
				+ "<div class='full-width-container' style='margin:8 0 0 0; margin:0; padding:0;'>"
					+ "<div class='icon small'><div class='icon-content data-player-arousal-icon'>(SVG icon)</div></div>"
					+ "<div class='barBackgroundAtt'>"
						+ "<div class='data-player-arousal-bar' style='height:5vw; float:left; border-radius: 2px;'></div>"
					+ "</div>"
					+ "<p class='data-player-arousal-value' style='text-align:center; margin:2vw 0 0 0; padding:0;color:"+ ";'>"
						+ "(arousal value)"
					+ "</p>"
					+ "<div class='overlay' id='PLAYER_" + Attribute.AROUSAL.getName() + "'></div>"
				+ "</div>"

			+ "</div>");



		// Visible unless isInSex()
		// Health, mana and experience:
		uiAttributeSB.append("<div class='visible-if-not-insex attribute-container'>"
					+ "<p style='text-align:center;padding:0;margin:0;'><b>Combat</b></p>"

					+ "<div class='full-width-container' style='margin:0;padding:0;'>"
						+ "<div class='icon small'><div class='icon-content'>" + Attribute.HEALTH_MAXIMUM.getSVGString() + "</div></div>"
						+ "<div class='barBackgroundAtt'>" + "<div class='data-health-bar' style='height:5vw; background:" + Colour.ATTRIBUTE_HEALTH.toWebHexString() + "; float:left; border-radius: 2px;'></div>" + "</div>"
						+ "<p class='data-health-value' style='text-align:center; margin:1 0 0 0; padding:0;'>(health value)</p>"
						+ "<div class='overlay' id='PLAYER_" + Attribute.HEALTH_MAXIMUM.getName() + "'></div>"
					+ "</div>"

					+ "<div class='full-width-container' style='margin:0;padding:0;'>"
						+ "<div class='icon small'><div class='icon-content'>" + Attribute.MANA_MAXIMUM.getSVGString() + "</div></div>"
						+ "<div class='barBackgroundAtt'>" + "<div class='data-mana-bar' style='height:5vw; background:" + Colour.ATTRIBUTE_MANA.toWebHexString() + "; float:left; border-radius: 2px;'></div>" + "</div>"
						+ "<p class='data-mana-value' style='text-align:center; margin:1 0 0 0; padding:0;'>(mana value)</p>"
						+ "<div class='overlay' id='PLAYER_" + Attribute.MANA_MAXIMUM.getName() + "'></div>"
					+ "</div>"

					+ "<div class='full-width-container' style='margin:0;padding:0;'>"
						+ "<div class='icon small'><div class='icon-content'>" + Attribute.STAMINA_MAXIMUM.getSVGString() + "</div></div>" + "<div class='barBackgroundAtt'>"
						+ "<div class='data-stamina-bar' style='height:5vw; background:" + Colour.ATTRIBUTE_FITNESS.toWebHexString() + "; float:left; border-radius: 2px;'></div>" + "</div>"
						+ "<p class='data-stamina-value' style='text-align:center; margin:1 0 0 0; padding:0;'>(stamina value)<p>" + "<div class='overlay' id='PLAYER_" + Attribute.STAMINA_MAXIMUM.getName() + "'></div>"
					+ "</div>"

				+ "</div>");

		// Status effects:
		uiAttributeSB.append("<div class='attribute-container effects data-effects'></div>");

		//TODO
		uiAttributeSB.append(""
				+ "<div class='attribute-container' style=' margin-bottom:1px;'>"
					+ "<div class='full-width-container' style='text-align:center; margin-left:4px;'>"
					+"<div class='item-inline' style='float:left;'><div class='overlay' id='DATE_DISPLAY_TOGGLE'>"+SVGImages.SVG_IMAGE_PROVIDER.getCalendarIcon()+"</div></div>"
							+ "<p class='data-date' style='color:"+Colour.TEXT.getShades(8)[3]+"; float:left; width:50%;'>"
								+ "(Day # / d MMMM)"
							+ "</p>"
							+ "<p style='float:right; margin-right:8px;'>");

		uiAttributeSB.append("<div class='item-inline' style='float:left;'><div class='overlay data-24hr-toggle-icon' id='TWENTY_FOUR_HOUR_TIME_TOGGLE'>"
				+"(SVG icon)</div></div>");

		uiAttributeSB.append("<span class='data-time'> HH:mm </span>");
		uiAttributeSB.append("</p>"
					+ "</div>"
				+ "</div>"
				+ "</div>"
				);

		// One of these will be hidden
		uiAttributeSB.append("<div class='inventory-equipped data-inventory-equipped'></div>");
		uiAttributeSB.append("<div class='data-map'></div>");

		uiAttributeSB.append("</body>");

		Main.mainController.setAttributePanelContent(uiAttributeSB.toString());
	}

	public void render() {
		if (!initialized || Main.mainController.getWebViewAttributes().getHeight() != lastAttributesHeight) {
			initialize();
			initialized = true;
			lastAttributesHeight = Main.mainController.getWebViewAttributes().getHeight();
		}
		update();
	}

	private void update() {
		boolean fullWidthAttributes = Main.mainController.getWebViewAttributes().getHeight()>=750;

		setStyleOf(".data-name",
				"color",
				Femininity.valueOf(Main.game.getPlayer().getFemininityValue()).getColour().toWebHexString()
		);
		setTextOf(".data-name",
				(Main.game.getPlayer().getName().length() == 0 ? (Main.game.getPlayer().getFemininityValue() <= Femininity.MASCULINE.getMaximumFemininity() ? "Hero" : "Heroine") : Main.game.getPlayer().getName())
		);

		setHtmlOf(".data-level",
				"<b>Level " + Main.game.getPlayer().getLevel()+ "</b> "
						+ (Main.game.getPlayer().getRaceStage().getName()!=""
						?"<b style='color:"+Main.game.getPlayer().getRaceStage().getColour().toWebHexString()+";'>" + Util.capitaliseSentence(Main.game.getPlayer().getRaceStage().getName())+"</b> ":"")
						+ "<b style='color:"+Main.game.getPlayer().getRace().getColour().toWebHexString()+";'>"
						+ (Main.game.getPlayer().isFeminine()?Util.capitaliseSentence(Main.game.getPlayer().getRace().getSingularFemaleName()):Util.capitaliseSentence(Main.game.getPlayer().getRace().getSingularMaleName()))
						+ "</b>"
		);

		setHtmlOf(".data-xpbar",
				(Main.game.getPlayer().getLevel() != 20
						? "<div style=' mix-blend-mode: difference; width:" + (Main.game.getPlayer().getExperience() / (Main.game.getPlayer().getLevel() * 10f)) * 90 + "vw; height:2vw; background:" + Colour.CLOTHING_BLUE_LIGHT.toWebHexString()
						+ "; float:left; border-radius: 2px;'></div>"
						: "<div style=' mix-blend-mode: difference; width:90vw; height:2vw; background:" + Colour.GENERIC_EXCELLENT.toWebHexString() + "; float:left; border-radius: 2px;'></div>")
		);

		setHtmlOf(".data-arcane-essences",
				UtilText.formatAsEssences(Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE), "b", true)
		);

		setHtmlOf(".data-money",
				UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "b")
		);

		setHtmlOf(".data-strength-icon",
				StrengthLevel.getStrengthLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer())
		);
		setStyleOf(".data-strength-bar",
				"width",
				""+Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH) * 0.65+"vw"
		);
		if (fullWidthAttributes) {
			setStyleOf(".data-strength-value",
					"color",
					(renderedPlayerStrengthValue < Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH)
							? (Colour.GENERIC_GOOD.toWebHexString())
							: (renderedPlayerStrengthValue > Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH) ? (Colour.GENERIC_BAD.toWebHexString()) : "default"))
			);
		}
		setTextOf(".data-strength-value",
				""+(int)Math.ceil(Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH))
		);

		setHtmlOf(".data-intelligence-icon",
				IntelligenceLevel.getIntelligenceLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer())
		);
		setStyleOf(".data-intelligence-bar",
				"width",
				""+Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE) * 0.65+"vw"
		);
		if (fullWidthAttributes) {
			setStyleOf(".data-intelligence-value",
					"color",
					(renderedPlayerIntelligenceValue < Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE)
							? (Colour.GENERIC_GOOD.toWebHexString())
							: (renderedPlayerIntelligenceValue > Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE) ? (Colour.GENERIC_BAD.toWebHexString()) : "default"))
			);
		}
		setTextOf(".data-intelligence-value",
				""+(int)Math.ceil(Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE))
		);

		setHtmlOf(".data-fitness-icon",
				FitnessLevel.getFitnessLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.FITNESS)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer())
		);
		setStyleOf(".data-fitness-bar",
				"width",
				""+Main.game.getPlayer().getAttributeValue(Attribute.FITNESS) * 0.65+"vw"
		);
		if (fullWidthAttributes) {
			setStyleOf(".data-fitness-value",
					"color",
					(renderedPlayerFitnessValue < Main.game.getPlayer().getAttributeValue(Attribute.FITNESS)
							? (Colour.GENERIC_GOOD.toWebHexString())
							: (renderedPlayerFitnessValue > Main.game.getPlayer().getAttributeValue(Attribute.FITNESS) ? (Colour.GENERIC_BAD.toWebHexString()) : "default"))
			);
		}
		setTextOf(".data-fitness-value",
				""+(int)Math.ceil(Main.game.getPlayer().getAttributeValue(Attribute.FITNESS))
		);

		setHtmlOf(".data-corruption-icon",
				CorruptionLevel.getCorruptionLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer())
		);
		setStyleOf(".data-corruption-bar",
				"width",
				""+Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION) * 0.65 + "vw"
		);
		if (fullWidthAttributes) {
			setStyleOf(".data-corruption-value",
					"color",
					(renderedPlayerCorruptionValue < Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION)
							? (Colour.GENERIC_GOOD.toWebHexString())
							: (renderedPlayerCorruptionValue > Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION) ? (Colour.GENERIC_BAD.toWebHexString()) : "default"))
			);
		}
		setTextOf(".data-corruption-value",
				"" + (int) Math.ceil(Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION)));

		if(Main.game.isInSex()) {
			showElements(".visible-if-insex");
			hideElements(".visible-if-not-insex");

			Colour playerOrgasmColour = Colour.GENERIC_ARCANE;
			if(Sex.getNumberOfPlayerOrgasms()<RenderingEngine.orgasmColours.length) {
				playerOrgasmColour = RenderingEngine.orgasmColours[Sex.getNumberOfPlayerOrgasms()];
			}
			setHtmlOf(".data-player-domsub",
					(Sex.isPlayerDom() ? "<b style='color:" + Colour.BASE_CRIMSON.toWebHexString() + ";'>Dominant</b>" : "<b style='color:" + Colour.BASE_PINK_LIGHT.toWebHexString() + ";'>Submissive</b>") + "<b> partner</b>"
			);
			setStyleOf(".data-player-pace",
					"color",
					Sex.getSexPacePlayer().getColour().toWebHexString()
			);
			setTextOf(".data-player-pace",
					Util.capitaliseSentence(Sex.getSexPacePlayer().getName())
			);
			setTextOf(".data-player-numorgasms",
					""+Sex.getNumberOfPlayerOrgasms()
			);
			setStyleOf(".data-player-numorgasms",
					"color",
					playerOrgasmColour.toWebHexString()
			);
			setHtmlOf(".data-player-arousal-icon",
					ArousalLevel.getArousalLevelFromValue(Main.game.getPlayer().getArousal()).getRelatedStatusEffect().getSVGString(Main.game.getPlayer())
			);
			setStyleOf(".data-player-arousal-bar",
					"width",
					"" + Main.game.getPlayer().getArousal() * 0.65 + "vw"
			);
			setStyleOf(".data-player-arousal-bar",
					"background",
					ArousalLevel.getArousalLevelFromValue(Main.game.getPlayer().getArousal()).getColour().toWebHexString()
			);
			setStyleOf(".data-player-arousal-value",
					"color",
					(renderedPlayerArousalValue < Main.game.getPlayer().getArousal()
							? (Colour.GENERIC_GOOD.toWebHexString())
							: (renderedPlayerArousalValue > Main.game.getPlayer().getArousal() ? (Colour.GENERIC_BAD.toWebHexString()) : "default"))
			);
			setTextOf(".data-player-arousal-value",
					"" + (int) Math.ceil(Main.game.getPlayer().getArousal())
			);
		} else {
			hideElements(".visible-if-insex");
			showElements(".visible-if-not-insex");

			setStyleOf(".data-health-bar","width",
					"" + (Main.game.getPlayer().getHealth() / Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM)) * 65 + "vw"
			);
			setStyleOf(".data-health-value", "color",
					(renderedPlayerHealthValue < Main.game.getPlayer().getHealth() ? Colour.GENERIC_GOOD.toWebHexString() : renderedPlayerHealthValue > Main.game.getPlayer().getHealth() ? (Colour.GENERIC_BAD.toWebHexString()) : "default")
			);
			setTextOf(".data-health-value",
					"" + (int) Math.ceil(Main.game.getPlayer().getHealth())
			);

			setStyleOf(".data-mana-bar","width",
					""+ (Main.game.getPlayer().getMana() / Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM)) * 65 + "vw"
			);
			setStyleOf(".data-mana-value","color",
					(renderedPlayerManaValue < Main.game.getPlayer().getMana() ? (Colour.GENERIC_GOOD.toWebHexString()) : (renderedPlayerManaValue > Main.game.getPlayer().getMana() ? (Colour.GENERIC_BAD.toWebHexString()) : "default"))
			);
			setTextOf(".data-mana-value",
					"" + (int) Math.ceil(Main.game.getPlayer().getMana())
			);

			setStyleOf(".data-stamina-bar","width",
					"" + (Main.game.getPlayer().getStaminaPercentage() * 65) + "vw"
			);
			setStyleOf(".data-stamina-value","color",
					(renderedPlayerStaminaValue < Main.game.getPlayer().getStamina() ? (Colour.GENERIC_GOOD.toWebHexString()) : (renderedPlayerStaminaValue > Main.game.getPlayer().getStamina() ? (Colour.GENERIC_BAD.toWebHexString()) : "default"))
			);
			setTextOf(".data-stamina-value",
					"" + (int) Math.ceil(Main.game.getPlayer().getStamina())
			);
		}

		StringBuilder effectsSb = new StringBuilder();
		effectsSb.append("<p style='text-align:center;padding:0;margin:0;'><b>Effects</b></p>");
		if(Main.game.isInSex()) {
			boolean hasStatusEffects = false;
			for (StatusEffect se : Main.game.getPlayer().getStatusEffects()) {
				if (se.isSexEffect()) {
					effectsSb.append(
							"<div class='icon data-effect'><div class='icon-content'>"
									+ se.getSVGString(Main.game.getPlayer())
									+ "<div class='overlay' id='SE_PLAYER_" + se + "'></div>"
									+ "</div></div>");
					hasStatusEffects = true;
				}
			}

			for (Fetish f : Main.game.getPlayer().getFetishes()) {
				effectsSb.append(
						"<div class='icon'><div class='icon-content'>"
								+ f.getSVGString()
								+ "<div class='overlay' id='FETISH_PLAYER_" + f + "'></div>"
								+ "</div></div>");
				hasStatusEffects = true;
			}

			if (Main.game.isInSex() && !hasStatusEffects) {
				effectsSb.append("<p style='text-align:center;padding:0;margin:0;height:12vw;'><b style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>No sex effects</b></p>");
			}
		} else {
			// Infinite duration:
			for (StatusEffect se : Main.game.getPlayer().getStatusEffects()) {
				if (!se.isCombatEffect() && Main.game.getPlayer().getStatusEffectDuration(se)==-1 && se.renderInEffectsPanel())
					effectsSb.append(
							"<div class='icon'>"
									+ "<div class='icon-content'>"
									+ se.getSVGString(Main.game.getPlayer())
									+ "<div class='overlay' id='SE_PLAYER_" + se + "'></div>"
									+ "</div>"
									+ "</div>");
			}
			// Timed:
			for (StatusEffect se : Main.game.getPlayer().getStatusEffects()) {
				if (!se.isCombatEffect() && Main.game.getPlayer().getStatusEffectDuration(se)!=-1 && se.renderInEffectsPanel()) {
					int timerHeight = (int) ((Main.game.getPlayer().getStatusEffectDuration(se)/(60*6f))*100);

					Colour timerColour = Colour.STATUS_EFFECT_TIME_HIGH;

					if(timerHeight>100) {
						timerHeight=100;
						timerColour = Colour.STATUS_EFFECT_TIME_OVERFLOW;
					} else if(timerHeight<15) {
						timerColour = Colour.STATUS_EFFECT_TIME_LOW;
					} else if (timerHeight<50) {
						timerColour = Colour.STATUS_EFFECT_TIME_MEDIUM;
					}

					effectsSb.append(
							"<div class='icon'>"
									+ "<div class='timer-background' style='width:"+timerHeight+"%; background:"+ timerColour.toWebHexString() + ";'></div>"
									+ "<div class='icon-content'>"
									+ se.getSVGString(Main.game.getPlayer())
									+ "<div class='overlay' id='SE_PLAYER_" + se + "'></div>"
									+ "</div>"
									+ "</div>");
				}
			}
//			// Fetishes:
//			for (Fetish f : Main.game.getPlayer().getFetishes()) {
//				uiAttributeSB.append(
//						"<div class='icon'><div class='icon-content'>"
//								+ f.getSVGString()
//								+ "<div class='overlay' id='FETISH_PLAYER_" + f + "'></div>"
//						+ "</div></div>");
//			}
//			// Special attacks:
//			for (SpecialAttack sa : Main.game.getPlayer().getSpecialAttacks()) {
//				uiAttributeSB.append(
//						"<div class='icon'><div class='icon-content'>"
//								+ sa.getSVGString()
//								+ "<div class='overlay' id='SA_" + sa + "'></div>"
//						+ "</div></div>");
//			}
//			if (Main.game.getPlayer().getMainWeapon() != null) {
//				for (Spell s : Main.game.getPlayer().getMainWeapon().getSpells()) {
//					uiAttributeSB.append(
//							"<div class='icon'><div class='icon-content'>"
//									+ s.getSVGString()
//									+ "<div class='overlay' id='SPELL_MAIN_" + s + "'></div>"
//							+ "</div></div>");
//				}
//			}
//			if (Main.game.getPlayer().getOffhandWeapon() != null) {
//				for (Spell s : Main.game.getPlayer().getOffhandWeapon().getSpells()) {
//					uiAttributeSB.append(
//							"<div class='icon'><div class='icon-content'>"
//									+ s.getSVGString()
//									+ "<div class='overlay' id='SPELL_OFFHAND_" + s + "'></div>"
//							+ "</div></div>");
//				}
//			}
		}

		setHtmlOf(".data-effects",
				effectsSb.toString()
		);

		setTextOf(".data-date",
				(Main.getProperties().calendarDisplay
						? Main.game.getDateNow().format(DateTimeFormatter.ofPattern("d", Locale.ENGLISH))
						+ Util.getDayOfMonthSuffix(Main.game.getDateNow().getDayOfMonth())
						+ " "
						+ Main.game.getDateNow().format(DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH))
						:"Day "+Main.game.getDayNumber())
		);

		if(Main.game.getPlayer().getClothingInSlot(InventorySlot.WRIST)!=null
				&& (Main.game.getPlayer().getClothingInSlot(InventorySlot.WRIST).getClothingType().equals(ClothingType.WRIST_WOMENS_WATCH)
				|| Main.game.getPlayer().getClothingInSlot(InventorySlot.WRIST).getClothingType().equals(ClothingType.WRIST_MENS_WATCH))) {
			setHtmlOf(".data-24hr-toggle-icon",
					Main.game.getPlayer().getClothingInSlot(InventorySlot.WRIST).getSVGEquippedString(Main.game.getPlayer())
			);
		} else {
			setHtmlOf(".data-24hr-toggle-icon",
					SVGImages.SVG_IMAGE_PROVIDER.getJournalIcon()
			);
		}
		setTextOf(".data-time",
				(Main.getProperties().twentyFourHourTime
						?Main.game.getDateNow().format(DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH))
						:Main.game.getDateNow().format(DateTimeFormatter.ofPattern("hh:mma", Locale.ENGLISH)))
		);

		if(Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.INVENTORY || Main.game.isInCombat() || Main.game.isInSex()) {
			showElements(".data-inventory-equipped");
			StringBuilder inventorySB = new StringBuilder();

			Set<InventorySlot> blockedSlots = new HashSet<>();

			for (AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
				if (c.getClothingType().getIncompatibleSlots() != null) {
					for (InventorySlot is : c.getClothingType().getIncompatibleSlots()) {
						blockedSlots.add(is);
					}
				}
			}
			// EQUIPPED:
			for (InventorySlot invSlot : RenderingEngine.inventorySlots) {

				AbstractClothing clothing = Main.game.getPlayer().getClothingInSlot(invSlot);

				if (clothing != null) {
					// add to content:
					inventorySB.append(
							// If slot is sealed:
							"<div class='inventory-item-slot" + RenderingEngine.getClassRarityIdentifier(clothing.getRarity()) + "'"
									+ (clothing.isSealed() ? "style='border-width:2px; border-color:" + Colour.SEALED.toWebHexString() + "; border-style:solid;'" : "") + ">"

									// Picture:
									+ "<div class='inventory-icon-content'>"+clothing.getSVGEquippedString(Main.game.getPlayer())+"</div>"

									// If clothing is displaced:
									+ (!clothing.getDisplacedList().isEmpty() ? "<div class='displacedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getDisplacedIcon() + "</div>" : "")
									// If clothing is cummed in:
									+ (clothing.isDirty() ? "<div class='cummedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getCummedInIcon() + "</div>" : "")
									// If clothing is too masculine:
									+ (clothing.getClothingType().getFemininityMaximum() < Main.game.getPlayer().getFemininityValue() ? "<div class='femininityIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getMasculineWarningIcon() + "</div>" : "")
									// If clothing is too feminine:
									+ (clothing.getClothingType().getFemininityMinimum() > Main.game.getPlayer().getFemininityValue() ? "<div class='femininityIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getFeminineWarningIcon() + "</div>" : "")

									+ "<div class='overlay-inventory' id='" + invSlot.toString() + "Slot'>" + "</div>" + "</div>");
				} else {
					// add to content:
					if (blockedSlots.contains(invSlot)) {
						inventorySB.append("<div class='inventory-item-slot disabled'><div class='overlay' id='" + invSlot.toString() + "Slot'></div></div>");

					} else if (invSlot.slotBlockedByRace(Main.game.getPlayer()) != null) {
						inventorySB.append(
								"<div class='inventory-item-slot disabled'>"
										+ "<div class='overlay' id='" + invSlot.toString() + "Slot'></div>"
										+ "<div class='raceBlockIcon'>" + invSlot.slotBlockedByRace(Main.game.getPlayer()).getStatusEffect().getSVGString(Main.game.getPlayer()) + "</div>"
										+ "</div>");

					} else {
						inventorySB.append("<div class='inventory-item-slot' id='" + invSlot.toString() + "Slot'></div>");
					}
				}
			}
			setHtmlOf(".data-inventory-equipped",
					inventorySB.toString());
		} else {
			hideElements(".data-inventory-equipped");
			setHtmlOf(".data-map",
					RenderingEngine.ENGINE.renderedHTMLMap()
			);
		}

		if (Main.game.getCurrentDialogueNode() != null) {
			if (!Main.game.getCurrentDialogueNode().isNoTextForContinuesDialogue() && renderedDialogueNode != Main.game.getCurrentDialogueNode()) {

				renderedPlayerHealthValue = Main.game.getPlayer().getHealth();
				renderedPlayerManaValue = Main.game.getPlayer().getMana();
				renderedPlayerStaminaValue = Main.game.getPlayer().getStamina();
				if (Main.game.isInSex()) {
					renderedPlayerArousalValue = Main.game.getPlayer().getArousal();
				}
				renderedPlayerStrengthValue = Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH);
				renderedPlayerIntelligenceValue = Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE);
				renderedPlayerFitnessValue = Main.game.getPlayer().getAttributeValue(Attribute.FITNESS);
				renderedPlayerCorruptionValue = Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION);


				renderedDialogueNode = Main.game.getCurrentDialogueNode();

			}
		}
	}
	
	public void setTheme() {
		if (Main.getProperties().lightTheme) {
			engine.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewAttributes_stylesheet_light.css").toExternalForm());
		} else {
			engine.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewAttributes_stylesheet.css").toExternalForm());
		}
	}
}
