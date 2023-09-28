package com.lilithsthrone.game.settings;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.PropertyValue;
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

    public ToggleOption(String id, Colour colour, String title, String description, PropertyValue field) {
        OPTION_ID = id;
        OPTION_COLOUR = colour;
        OPTION_TITLE = title;
        OPTION_DESCRIPTION = description;
        OPTION_PROPERTY_VALUE = field;
    }

    public String getDiv() {
        return getContentPreferenceDiv(OPTION_ID, OPTION_COLOUR, OPTION_TITLE, OPTION_DESCRIPTION, Main.getProperties().hasValue(OPTION_PROPERTY_VALUE));
    }

    public void addListener() {
        createToggleListener(OPTION_ID, OPTION_PROPERTY_VALUE);
    }

    private static void createToggleListener(String id, PropertyValue propertyValue) {
        if (MainController.document.getElementById(id + "_ON") != null) {
            ((EventTarget) MainController.document.getElementById(id + "_ON")).addEventListener("click", e -> {
                Main.getProperties().setValue(propertyValue, true);
                updateUIButton(id, propertyValue);
            }, false);
        }
        if (MainController.document.getElementById(id + "_OFF") != null) {
            ((EventTarget) MainController.document.getElementById(id + "_OFF")).addEventListener("click", e -> {
                Main.getProperties().setValue(propertyValue, false);
                updateUIButton(id, propertyValue);
            }, false);
        }
    }

    private static void updateUIButton(String id, PropertyValue fieldName) {
        Main.saveProperties();
        /* Doing this allows us to change the value live, instead of redrawing the page every time */
        MainController.document.getElementById(id + "_OFF").setAttribute("class", !Main.getProperties().hasValue(fieldName) ? "normal-button selected" : "normal-button");
        MainController.document.getElementById(id + "_ON").setAttribute("class", Main.getProperties().hasValue(fieldName) ? "normal-button selected" : "normal-button");
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