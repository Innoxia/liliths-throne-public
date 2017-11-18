package com.lilithsthrone.rendering;/*
 * Created by aimozg on 18.11.2017.
 * Confidential until published on GitHub
 */

import com.lilithsthrone.controller.WebEngineEx;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.dialogue.MapDisplay;
import com.lilithsthrone.main.Main;
import javafx.scene.web.WebEngine;

public class ButtonsPanel extends WebEngineEx {
	public ButtonsPanel(WebEngine engine) {
		super(engine);
		engine.getHistory().setMaxSize(0);
		setTheme();
	}

	public void setTheme() {
		if (Main.getProperties().lightTheme) {
			engine.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewButtons_stylesheet_light.css").toExternalForm());
		} else {
			engine.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewButtons_stylesheet.css").toExternalForm());
		}
	}

	private boolean isInitialized = false;

	public void render() {
		if (!isInitialized) {
			initialize();
			isInitialized = true;
		}
		update();
	}

	private void update() {
		if (Main.game.getCurrentDialogueNode().isOptionsDisabled()) {
			addClass("#mainMenu","disabled");
			setHtmlOf("#mainMenu",SVGImages.SVG_IMAGE_PROVIDER.getMenuIcon()
					+ "<div class='disabledLayer'></div>");
		} else {
			removeClass("#mainMenu","disabled");
			setHtmlOf("#mainMenu",SVGImages.SVG_IMAGE_PROVIDER.getMenuIcon());
		}

		boolean highlightJournal = Main.game.getPlayer().isMainQuestUpdated()
				|| Main.game.getPlayer().isSideQuestUpdated()
				|| Main.game.getPlayer().isRomanceQuestUpdated()
				|| Main.getProperties().isNewWeaponDiscovered()
				|| Main.getProperties().isNewClothingDiscovered()
				|| Main.getProperties().isNewItemDiscovered()
				|| Main.getProperties().isNewRaceDiscovered()
				|| Main.game.getPlayer().getPerkPoints()>0
				|| (Main.game.getPlayer().getLevelUpPoints()>0
				&& (Main.game.getPlayer().getBaseAttributeValue(Attribute.STRENGTH) + Main.game.getPlayer().getBaseAttributeValue(Attribute.INTELLIGENCE) + Main.game.getPlayer().getBaseAttributeValue(Attribute.FITNESS))<300);
		boolean disabledJournal = Main.game.getCurrentDialogueNode().isOptionsDisabled() || !Main.game.isInNewWorld();
		if (highlightJournal) {
			addClass("#journal","highlight");
		} else {
			removeClass("#journal","highlight");
		}
		if (disabledJournal) {
			addClass("#journal","disabled");
			setHtmlOf("#journal",SVGImages.SVG_IMAGE_PROVIDER.getJournalIcon()
					+ "<div class='disabledLayer'></div>");
		} else {
			removeClass("#journal","disabled");
			setHtmlOf("#journal",SVGImages.SVG_IMAGE_PROVIDER.getJournalIcon());
		}

		boolean highlightInventory = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().getInventorySlotsTaken()>0;
		if (highlightInventory) {
			addClass("#inventory","highlight");
		} else {
			removeClass("#inventory","highlight");
		}
		if (Main.mainController.isInventoryDisabled()) {
			addClass("#inventory","disabled");
			setHtmlOf("#inventory",SVGImages.SVG_IMAGE_PROVIDER.getInventoryIcon()
					+ "<div class='disabledLayer'></div>");
		} else {
			removeClass("#inventory","disabled");
			setHtmlOf("#inventory",SVGImages.SVG_IMAGE_PROVIDER.getInventoryIcon());
		}

		boolean charsDisabled = Main.game.getCharactersPresent().isEmpty() && Main.game.getCurrentDialogueNode().getMapDisplay() != MapDisplay.CHARACTERS_PRESENT;
		if (charsDisabled){
			addClass("#charactersPresent","disabled");
			setHtmlOf("#charactersPresent",SVGImages.SVG_IMAGE_PROVIDER.getPeopleIcon()
					+ "<div class='disabledLayer'></div>");
		} else {
			removeClass("#charactersPresent","disabled");
			setHtmlOf("#charactersPresent",SVGImages.SVG_IMAGE_PROVIDER.getPeopleIcon());
		}

		boolean zoomDisabled = Main.game.getCurrentDialogueNode().isTravelDisabled();
		String zoomIcon = (RenderingEngine.isZoomedIn() ? SVGImages.SVG_IMAGE_PROVIDER.getZoomOutIcon() : SVGImages.SVG_IMAGE_PROVIDER.getZoomInIcon());
		if (zoomDisabled){
			addClass("#mapZoom","disabled");
			setHtmlOf("#mapZoom",zoomIcon
					+ "<div class='disabledLayer'></div>");
		} else {
			removeClass("#mapZoom","disabled");
			setHtmlOf("#mapZoom", zoomIcon);
		}
	}

	private void initialize() {
		setBodyContent(
				"<div class='quarterContainer'>"
					+ "<div class='button' id='mainMenu'>"
						+ "(SVG icon, optional div.disabledLayer)"
					+ "</div>"
				+ "</div>"

				+ "<div class='quarterContainer'>"
					+ "<div class='button' id='journal'>"
						+ "(SVG icon, optional div.disabledLayer)"
					+ "</div>"
				+ "</div>"

				+ "<div class='quarterContainer'>"
					+ "<div class='button' id='inventory'>"
						+ "(SVG icon, optional div.disabledLayer)"
					+ "</div>"
				+ "</div>"

				+ "<div class='quarterContainer'>"
					+ "<div class='button' id='charactersPresent'>"
						+ "(SVG icon, optional div.disabledLayer)"
					+ "</div>"
				+ "</div>"

				+ "<div class='quarterContainer'>"
					+ "<div class='button' id='mapZoom'>"
						+ "(SVG icon, optional div.disabledLayer)"
					+ "</div>"
				+ "</div>"
		);
	}
}
