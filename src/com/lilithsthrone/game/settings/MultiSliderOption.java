package com.lilithsthrone.game.settings;

import com.lilithsthrone.utils.colours.Colour;

public class MultiSliderOption {
    public final String OPTION_ID;
    public final Colour OPTION_COLOUR;
    public final String OPTION_TITLE, OPTION_NAME, OPTION_NAME_ALT;
    public final String OPTION_DESCRIPTION;
    public final String OPTION_DISPLAY;
    public final int OPTION_MIN_VALUE, OPTION_MIN_VALUE_ALT;
    public final int OPTION_MIN_STEP, OPTION_MIN_STEP_ALT;
    public final int OPTION_MAX_STEP, OPTION_MAX_STEP_ALT;
    public final int OPTION_VALUE_PER_STEP, OPTION_VALUE_PER_STEP_ALT;
    public final String OPTION_FIELD_NAME, OPTION_FIELD_NAME_ALT;

    public MultiSliderOption(String id, Colour colour, String title, String var1Name, String var2Name, String description, String display, int minVal, int maxSteps, int valPerStep, String field, String fieldAlt) {
        this(id, colour, title, var1Name, var2Name, description, display, minVal, 0, maxSteps, valPerStep, field, minVal, 0, maxSteps, valPerStep, fieldAlt);
    }
    public MultiSliderOption(String id, Colour colour, String title, String var1Name, String var2Name, String description, String display, int minVal, int maxSteps, int valPerStep, String field, int minValAlt, int maxStepsAlt, int valPerStepAlt, String fieldAlt) {
        this(id, colour, title, var1Name, var2Name, description, display, minVal, 0, maxSteps, valPerStep, field, minValAlt, 0, maxStepsAlt, valPerStepAlt, fieldAlt);
    }
    public MultiSliderOption(String id, Colour colour, String title, String var1Name, String var2Name, String description, String display, int minVal, int minStep, int maxSteps, int valPerStep, String field, int minValAlt, int minStepAlt, int maxStepsAlt, int valPerStepAlt, String fieldAlt) {
        OPTION_ID = id;
        OPTION_COLOUR = colour;
        OPTION_TITLE = title;
        OPTION_NAME = var1Name;
        OPTION_NAME_ALT = var2Name;
        OPTION_DESCRIPTION = description;
        OPTION_DISPLAY = display;
        OPTION_MIN_VALUE = minVal;
        OPTION_MIN_VALUE_ALT = minValAlt;
        OPTION_MIN_STEP = minStep;
        OPTION_MIN_STEP_ALT = minStepAlt;
        OPTION_MAX_STEP = maxSteps;
        OPTION_MAX_STEP_ALT = maxStepsAlt;
        OPTION_VALUE_PER_STEP = valPerStep;
        OPTION_VALUE_PER_STEP_ALT = valPerStepAlt;
        OPTION_FIELD_NAME = field;
        OPTION_FIELD_NAME_ALT = fieldAlt;
    }

    public String getDiv() {
        return getMultiVariableSliderDiv(OPTION_ID, OPTION_COLOUR, OPTION_TITLE, OPTION_DESCRIPTION, OPTION_DISPLAY, OPTION_NAME, OPTION_NAME_ALT, OPTION_FIELD_NAME, OPTION_FIELD_NAME_ALT, OPTION_MIN_STEP, OPTION_MAX_STEP, OPTION_VALUE_PER_STEP, OPTION_MIN_STEP_ALT, OPTION_MAX_STEP_ALT, OPTION_VALUE_PER_STEP_ALT);
    }

    public void addListeners() {
        SliderOption.addSliderListeners(OPTION_ID, OPTION_FIELD_NAME, OPTION_VALUE_PER_STEP, OPTION_MIN_VALUE, OPTION_DISPLAY);
        SliderOption.addSliderListeners(OPTION_ID + "_ALT", OPTION_FIELD_NAME_ALT, OPTION_VALUE_PER_STEP_ALT, OPTION_MIN_VALUE_ALT, OPTION_DISPLAY);
    }

    private static String getMultiVariableSliderDiv(
            String id,
            Colour colour,
            String title,
            String description,
            String valueDisplay, String valueName, String valueNameAlt,
            String fieldName, String fieldNameAlt,
            int minStep, int maxStep, int valPerStep,
            int minStepAlt, int maxStepAlt, int valPerStepAlt) {
        return "<div class='container-full-width' style='padding:0; margin:2px 0;'>" +
                    "<div class='container-half-width' style='width:calc(55% - 16px); padding:0;'>" +
                        "<b style='text-align:center; color:" + colour.toWebHexString() + ";'>" + title + "</b><b>:</b> " +
                        description +
                    "</div>" +
                    "<div class='container-half-width' style='width:calc(43% - 16px); padding:0;'>" +
                        "<div class='container-full-width' style='width:100%; padding:0;'>" +
                            "<div class='container-full-width' style='text-align:center; float:right; width:55%; margin: 0px; margin-top:-2%;'>" +
                                valueName + ": " +
                                "<b id='" + id + "' style='text-align: center;'>" + SliderOption.getValueForDisplay(valueDisplay, fieldName) + "</b>" +
                            "</div>" +
                            SliderOption.getSlider(id, minStep, maxStep, valPerStep, fieldName) +
                        "</div>" +
                        "<div class='container-full-width' style='width:100%; padding:0;'>" +
                            "<div class='container-full-width' style='text-align:center; float:right; width:55%; margin: 0px; margin-top:-2%;'>" +
                                valueNameAlt + ": " +
                                "<b id='" + id + "_ALT' style='text-align: center;'>" + SliderOption.getValueForDisplay(valueDisplay, fieldNameAlt) + "</b>" +
                            "</div>" +
                            SliderOption.getSlider(id + "_ALT", minStepAlt, maxStepAlt, valPerStepAlt, fieldNameAlt) +
                        "</div>" +
                    "</div>" +
                "</div>";
    }
}