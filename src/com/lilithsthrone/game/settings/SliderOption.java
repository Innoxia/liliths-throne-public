package com.lilithsthrone.game.settings;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.colours.Colour;
import org.w3c.dom.events.EventTarget;

public class SliderOption {
    public final String OPTION_ID;
    public final Colour OPTION_COLOUR;
    public final String OPTION_TITLE;
    public final String OPTION_DESCRIPTION;
    public final String OPTION_DISPLAY;
    public final int OPTION_MIN_VALUE;
    public final int OPTION_MIN_STEP;
    public final int OPTION_MAX_STEP;
    public final int OPTION_VALUE_PER_STEP;
    public final String OPTION_FIELD_NAME;

    public SliderOption(String id, Colour colour, String title, String description, String display, int minVal, int maxSteps, int valPerStep, String field) {
        this(id, colour, title, description, display, minVal, 0, maxSteps, valPerStep, field);
    }

    public SliderOption(String id, Colour colour, String title, String description, String display, int minVal, int minStep, int maxSteps, int valPerStep, String field) {
        OPTION_ID = id;
        OPTION_COLOUR = colour;
        OPTION_TITLE = title;
        OPTION_DESCRIPTION = description;
        OPTION_DISPLAY = display;
        OPTION_MIN_VALUE = minVal;
        OPTION_MIN_STEP = minStep;
        OPTION_MAX_STEP = maxSteps;
        OPTION_VALUE_PER_STEP = valPerStep;
        OPTION_FIELD_NAME = field;
    }

    public String getDiv() {
        return getContentPreferenceSliderDiv(OPTION_ID, OPTION_COLOUR, OPTION_TITLE, OPTION_DESCRIPTION, OPTION_DISPLAY, OPTION_MIN_STEP, OPTION_MAX_STEP, OPTION_VALUE_PER_STEP, Main.getProperties().getIntByRef(OPTION_FIELD_NAME));
    }

    public void addListener() {
        addSliderListeners(OPTION_ID, OPTION_FIELD_NAME, OPTION_VALUE_PER_STEP, OPTION_MIN_VALUE, OPTION_DISPLAY);
    }
    /** Returns a slider Div with the specified parameters
     *
     * */
    private static String getContentPreferenceSliderDiv(String id, Colour colour, String title, String description, String valueDisplay, int minStep, int maxSteps, int valPerStep, int value) {
        return "<div class='container-full-width' style='padding:0; margin:2px 0;'>" +
                    "<div class='container-half-width' style='width:calc(55% - 16px);'>" +
                        "<b style='text-align:center; color:" + colour.toWebHexString() + ";'>" + title + "</b><b>:</b> " + description +
                    "</div>" +
                    "<div class='container-half-width' style='width:calc(45% - 16px);'>" +
                        "<div id='" + id + "' class='container-full-width' style='text-align:center; float:right; width:55%; margin-bottom:-10; margin-top:-1%;'><b>" + getValueForDisplay(valueDisplay, value) + "</b></div>" +
                        "<div class='container-full-width' style='float:right;'>" +
                            "<input style='float:right; width:55%; margin-bottom:-2%;' type=\"range\" min=" + minStep + " max=" + maxSteps + " value=" + Math.round(value / (float) valPerStep) + " class=\"slider\" id=" + id + "_SLIDER>" +
                        "</div>" +
                    "</div>" +
                "</div>";
    }

    private static String getValueForDisplay(String display, int val) {
        String valD = display.replace("%VALUE", String.valueOf(val));
        if (valD.contains("$s")) {
            valD = valD.replace("$s", val > 1 ? "s" : "");
        }
        if (valD.contains("$+")) {
            valD = valD.replace("$+", val > 0 ? "+" : "");
        }
        if (valD.contains("%UNIT_SIZE")) {
            valD = valD.replace("%UNIT_SIZE", Units.size(val, Units.ValueType.PRECISE, Units.UnitType.SHORT));
        }
        if (valD.contains("%UNIT_VOLUME")) {
            valD = valD.replace("%UNIT_VOLUME", Units.fluid(val, Units.ValueType.PRECISE, Units.UnitType.SHORT));
        }
        return valD;
    }

    /** Adds the JS listener to the Doc for Relevant Option
     * @param elementID The HTML ID of the option
     * @param fieldName The name of the field in Properties.java
     * @param settingValueMulti The # the slider's 'points' are stepped by
     * @param floor The minimum value of the option (Allows setting the slider to other scaling sizes)
     * */
    public static void addSliderListeners(String elementID, String fieldName, int settingValueMulti, int floor, String display) {
        if (MainController.document.getElementById(elementID + "_SLIDER") == null) return;
        ((EventTarget) MainController.document.getElementById(elementID + "_SLIDER")).addEventListener("input", e -> {
            int newVal = Math.max(floor, Integer.parseInt(Main.mainController.getWebEngine().executeScript("document.getElementById('" + elementID + "_SLIDER" + "').value;").toString()) * settingValueMulti);
            Main.getProperties().setIntByRef(fieldName, newVal);
            /* Doing this allows us to change the value live, instead of redrawing the page every time */
            Main.mainController.getWebEngine().executeScript("document.getElementById('" + elementID + "').innerHTML = \"<b>" + getValueForDisplay(display, newVal) + "</b>\";");
            Main.saveProperties();
        }, false);
    }
}