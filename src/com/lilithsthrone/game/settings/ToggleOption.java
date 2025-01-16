package com.lilithsthrone.game.settings;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.Colour;
import org.w3c.dom.events.EventTarget;

public class ToggleOption {
    public final String OPTION_ID;
    public final Colour OPTION_COLOUR;
    public final String OPTION_TITLE;
    public final String OPTION_DESCRIPTION;
    public final PropertyValue OPTION_PROPERTY_VALUE;
    public final Runnable OPTION_RUNNABLE;

    public ToggleOption(String id, Colour colour, String title, String description, PropertyValue property) {
        OPTION_ID = id;
        OPTION_COLOUR = colour;
        OPTION_TITLE = title;
        OPTION_DESCRIPTION = description;
        OPTION_PROPERTY_VALUE = property;
        OPTION_RUNNABLE = null;
    }

    public ToggleOption(String id, Colour colour, String title, String description, PropertyValue property, Runnable runnable) {
        OPTION_ID = id;
        OPTION_COLOUR = colour;
        OPTION_TITLE = title;
        OPTION_DESCRIPTION = description;
        OPTION_PROPERTY_VALUE = property;
        OPTION_RUNNABLE = runnable;
    }

    public String getDiv() {
        return getContentPreferenceDiv(OPTION_ID, OPTION_COLOUR, OPTION_TITLE, OPTION_DESCRIPTION, Main.getProperties().hasValue(OPTION_PROPERTY_VALUE));
    }

    public void addListener() {
        createToggleListener(OPTION_ID, OPTION_PROPERTY_VALUE, OPTION_RUNNABLE);
    }

    private static void createToggleListener(String id, PropertyValue property, Runnable runnable) {
        if (MainController.document.getElementById(id + "_ON") != null) {
            ((EventTarget) MainController.document.getElementById(id + "_ON")).addEventListener("click", e -> {
                Main.getProperties().setValue(property, true);
                if (runnable != null) runnable.run();
                updateUIButton(id, property);
            }, false);
        }
        if (MainController.document.getElementById(id + "_OFF") != null) {
            ((EventTarget) MainController.document.getElementById(id + "_OFF")).addEventListener("click", e -> {
                Main.getProperties().setValue(property, false);
                if (runnable != null) runnable.run();
                updateUIButton(id, property);
            }, false);
        }
    }

    private static void updateUIButton(String id, PropertyValue property) {
        if (property.isFetishRelated() && Main.game.isStarted()) {
            Main.game.getPlayer().recalculateAvailableCombatMoves();
            Main.game.getPlayer().calculateSpecialFetishes();
            for (GameCharacter character : Main.game.getAllNPCs()) {
                character.recalculateAvailableCombatMoves();
                character.calculateSpecialFetishes();
            }
        }
        Main.saveProperties();
        MainController.document.getElementById(id + "_OFF").setAttribute("class", !Main.getProperties().hasValue(property) ? "normal-button selected" : "normal-button");
        MainController.document.getElementById(id + "_ON").setAttribute("class", Main.getProperties().hasValue(property) ? "normal-button selected" : "normal-button");
        Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
    }

    private static String getContentPreferenceDiv(String id, Colour colour, String title, String description, boolean enabled) {
        StringBuilder contentSB = new StringBuilder();

        contentSB.append("<div class='container-full-width' style='padding:0; margin:2px 0;'><div class='container-half-width' style='width:calc(55% - 16px);'><b style='text-align:center; color:").append(colour.toWebHexString()).append(";'>").append(title).append("</b><b>:</b> ").append(description).append("</div>").append("<div class='container-half-width' style='width:calc(45% - 16px);'>");

        if (enabled)
            contentSB.append("<div id='").append(id).append("_ON' class='normal-button selected' style='width:25%; margin-right:4%; text-align:center; float:right;'>").append("[style.boldGood(ON)]").append("</div>").append("<div id='").append(id).append("_OFF' class='normal-button' style='width:25%; margin-right:4%; text-align:center; float:right;'>").append("[style.colourDisabled(OFF)]").append("</div>");
        else
            contentSB.append("<div id='").append(id).append("_ON' class='normal-button' style='width:25%; margin-right:4%; text-align:center; float:right;'>").append("[style.colourDisabled(ON)]").append("</div>").append("<div id='").append(id).append("_OFF' class='normal-button selected' style='width:25%; margin-right:4%; text-align:center; float:right;'>").append("[style.boldBad(OFF)]").append("</div>");

        contentSB.append("</div></div>");
        return contentSB.toString();
    }
}