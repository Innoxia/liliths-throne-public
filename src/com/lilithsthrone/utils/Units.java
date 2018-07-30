package com.lilithsthrone.utils;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.main.Main;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

/**
 * Collection of utility functions for date, time and number format conversion.
 *
 * @since 0.2.9
 * @version 0.2.9
 * @author Addi
 */
public enum Units {
    INSTANCE;

    DateTimeFormatter dateFormat;
    DateTimeFormatter timeFormat;
    NumberFormat numberFormat;

    Units() {
        updateDateFormat();
        updateTimeFormat();
        updateNumberFormat();
    }

    /**
     * Resets the date formatter depending on the system locale (if automatic) or the imperial number flag (if manual).
     */
    public void updateDateFormat() {
        dateFormat = (Main.getProperties().hasValue(PropertyValue.autoLocale)
                ? DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                : DateTimeFormatter.ofPattern(Main.getProperties().hasValue(PropertyValue.imperialSystem) ? "MM/dd/yy" : "dd.MM.yy"))
                .withZone(ZoneId.systemDefault());
    }

    /**
     * Resets the time formatter depending on the system locale (if automatic) or the 24 hour time flag (if manual).
     */
    public void updateTimeFormat() {
        timeFormat = (Main.getProperties().hasValue(PropertyValue.autoLocale)
                ? DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
                : DateTimeFormatter.ofPattern(Main.getProperties().hasValue(PropertyValue.twentyFourHourTime) ? "HH:mm" : "hh:mm a"))
                .withZone(ZoneId.systemDefault());
    }

    /**
     * Resets the number formatter depending on the system locale (if automatic), defaulting to English otherwise.
     * In all cases, output numbers are rounded correctly to the 2nd fraction digit.
     */
    public void updateNumberFormat() {
        numberFormat = NumberFormat.getNumberInstance(Main.getProperties().hasValue(PropertyValue.autoLocale) ? Locale.getDefault() : Locale.ENGLISH);
        numberFormat.setRoundingMode(RoundingMode.HALF_UP);
        numberFormat.setMaximumFractionDigits(2);
    }

    /**
     * Resets every formatter. See {@link Units#updateDateFormat()}, {@link Units#updateTimeFormat()} and {@link Units#updateNumberFormat()} for details.
     */
    public static void updateFormats() {
        INSTANCE.updateDateFormat();
        INSTANCE.updateTimeFormat();
        INSTANCE.updateNumberFormat();
    }

    /**
     * Formats a number with the current number formatter.
     * @param amount The floating point number to format
     * @return A string containing the localized number
     */
    public static String number(double amount) {
        return INSTANCE.numberFormat.format(amount);
    }

    /**
     * See {@link Units#number(double)}.
     * @param amount The integer number to format
     */
    public static String number(long amount) {
        return INSTANCE.numberFormat.format(amount);
    }

    /**
     * Formats a point in time with the current date and time formatter, separated with " - ".
     * @param timePoint The point in time to convert
     * @return A string containing the localized date and time
     */
    public static String dateTime(TemporalAccessor timePoint) {
        return date(timePoint) + " - " + time(timePoint);
    }

    /**
     * Similar to {@link Units#dateTime(TemporalAccessor)}, except that this function only outputs the date.
     */
    public static String date(TemporalAccessor timePoint) {
        return INSTANCE.dateFormat.format(timePoint);
    }

    /**
     * Similar to {@link Units#dateTime(TemporalAccessor)}, except that this function only outputs the time.
     */
    public static String time(TemporalAccessor timePoint) {
        return INSTANCE.timeFormat.format(timePoint);
    }

    /**
     * Specifies the length of units, where NONE means that they will be omitted, SHORT means the abbreviation and LONG
     * means the full text unit.
     */
    public enum UnitType {
        NONE,
        SHORT,
        LONG
    }

    /**
     * Formats a size in inches with short units. See {@link Units#size(double, UnitType)} for details.
     */
    public static String size(double inches) {
        return size(inches, UnitType.SHORT);
    }

    /**
     * Formats a size, given in inches, with the current number formatter and units depending on the imperial unit
     * setting as well as the given type. For examples of the result, see {@link Units#asImperial(double, UnitType)} and
     * {@link Units#asMetric(double, UnitType)}.
     * @param inches Amount of inches to convert
     * @param type The desired length of the units, see {@link UnitType} for details
     * @return A string containing the localized, wrapped, converted numbers and their associated units
     */
    public static String size(double inches, UnitType type) {
        if (Main.getProperties().hasValue(PropertyValue.imperialSystem))
            return asImperial(inches, type);
        else
            return asMetric(inches, type);
    }

    /**
     * Formats a size, given in inches, to the common imperial form. Note that only {@link UnitType#SHORT} applies
     * wrapping, so the output for 56 inches, would be:
     * {@link UnitType#NONE}: 56
     * {@link UnitType#SHORT}: 4'8"
     * {@link UnitType#LONG}: 56 inches
     * @param inches Amount of inches to format
     * @param type The desired length of the units, see {@link UnitType} for details
     * @return A string containing the localized, formatted numbers, including units
     */
    public static String asImperial(double inches, UnitType type) {
        String output = "";

        if (type != UnitType.SHORT) {
            // Don't wrap with long or no units
            output = number(inches);
            if (type == UnitType.LONG) {
                output += Math.abs(inches) == 1 ? " inch" : " inches";
            }
        } else {
            // Wrap inches to feet
            long feet = (long) (inches / 12);
            double inch = inches % 12;

            if (feet != 0) {
                output = number(feet) + "&#39;";
            }

            if (inch != 0) {
                output += number(output.isEmpty() ? inch : Math.abs(inch)) + "&quot;";
            } else if (feet == 0) {
                output = type == UnitType.LONG ? "less than 1 inch" : "0&quot;";
            }
        }

        return output;
    }

    /**
     * Converts a size, given in inches, to the common metric form. Note that the type {@link UnitType#NONE} does not
     * apply wrapping and centimeter values are rounded correctly, so the output for 56 inches would be:
     * {@link UnitType#NONE}: 142
     * {@link UnitType#SHORT}: 1.42 m
     * {@link UnitType#LONG}: 1.42 meters
     * @param inches Amount of inches to convert
     * @param type The desired length of the units, see {@link UnitType} for details
     * @return A string containing the localized, formatted, converted numbers, including units
     */
    public static String asMetric(double inches, UnitType type) {
        // Convert to meter and centimeter
        double cm = inches * 2.54;
        double m = cm / 100;

        // Don't wrap without units
        if (type == UnitType.NONE) return number(Math.round(cm));
        if (type == UnitType.LONG && (long) cm == 0) return "less than 1 centimeter";

        // Wrap centimeters to meters
        String output;
        if (m > 1 || m < -1) {
            String mUnit = type == UnitType.SHORT ? " m" : (Math.abs(m) == 1 ? " meter" : " meters");
            output = number(m) + mUnit;
        } else {
            String cmUnit = type == UnitType.SHORT ? " cm" : (Math.abs(m) == 1 ? " centimeter" : " centimeters");
            output = number(Math.round(cm)) + cmUnit;
        }

        return output;
    }

    /**
     * Rounds a given number to a given amount of fractional places.
     * @param value Number to round
     * @param places Amount of fractional places
     * @return A correctly rounded float
     */
    public static float round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException("Amount of fractional places cannot be less than 0.");
        return BigDecimal.valueOf(value).setScale(places, BigDecimal.ROUND_HALF_UP).floatValue();
    }
}
