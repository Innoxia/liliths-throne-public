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
     * Specifies the length of units, where NONE means that they will be omitted, SHORT means the abbreviation, LONG
     * means the full text unit and LONG_SINGULAR means full text singular units (concatenated with "-" instead of " ".
     */
    public enum UnitType {
        NONE,
        SHORT,
        LONG,
        LONG_SINGULAR
    }

    /**
     * Formats a size in inches with short units. See {@link Units#size(double, UnitType)} for details.
     */
    public static String size(double inches) {
        return size(inches, UnitType.SHORT);
    }

    /**
     * Formats a size, given in inches, with the current number formatter and units depending on the imperial unit
     * setting as well as the given type. For examples of the result, see {@link Units#sizeAsImperial(double, UnitType)} and
     * {@link Units#sizeAsMetric(double, UnitType)}.
     * @param inches Amount of inches to convert
     * @param type The desired length of the units, see {@link UnitType} for details
     * @return A string containing the localized, wrapped, converted size and its associated unit
     */
    public static String size(double inches, UnitType type) {
        if (Main.getProperties().hasValue(PropertyValue.imperialSystem))
            return sizeAsImperial(inches, type);
        else
            return sizeAsMetric(inches, type);
    }

    /**
     * Formats a size, given in inches, to the common imperial form. Note that only {@link UnitType#SHORT} applies
     * wrapping, so the output for 56 inches, would be:
     * {@link UnitType#NONE}: 56
     * {@link UnitType#SHORT}: 4'8"
     * {@link UnitType#LONG}: 56 inches
     * {@link UnitType#LONG_SINGULAR}: 56-inch
     * @param inches Amount of inches to format
     * @param type The desired length of the units, see {@link UnitType} for details
     * @return A string containing the imperial, formatted size, including unit
     */
    public static String sizeAsImperial(double inches, UnitType type) {
        String output = "";

        if (type != UnitType.SHORT) {
            // Don't wrap with long or no units
            output = number(inches);
            if (type == UnitType.LONG) {
                output += Math.abs(inches) == 1 ? " inch" : " inches";
            } else if (type == UnitType.LONG_SINGULAR) {
                output += "-inch";
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
     * {@link UnitType#LONG_SINGULAR}: 1.42-meter
     * @param inches Amount of inches to convert
     * @param type The desired length of the units, see {@link UnitType} for details
     * @return A string containing the metric, formatted, converted size, including unit
     */
    public static String sizeAsMetric(double inches, UnitType type) {
        // Convert inches to centimetres
        double cm = inches * 2.54;

        // Don't wrap without units
        if (type == UnitType.NONE) return number(Math.round(cm));
        if (type == UnitType.LONG && (long) cm == 0) return "less than 1 centimeter";

        // Wrap centimeters to meters
        double m = cm / 100;
        return withUnit(cm, "cm", "centimeter", m, "m", "meter", type);
    }

    /**
     * Formats a fluid volume in millilitres with short units. See {@link Units#size(double, UnitType)} for details.
     */
    public static String fluid(double ml) {
        return fluid(ml, UnitType.SHORT);
    }

    /**
     * Formats a fluid volume, given in millilitres, with the current number formatter and units depending on the
     * imperial unit setting as well as the given type. For examples of the result, see {@link Units#fluidAsImperial(double, UnitType)}
     * and {@link Units#fluidAsMetric(double, UnitType)}.
     * @param ml Amount of millilitres to convert
     * @param type The desired length of the units, see {@link UnitType} for details
     * @return A string containing the localized, wrapped, converted volume and its associated unit
     */
    public static String fluid(double ml, UnitType type) {
        if (Main.getProperties().hasValue(PropertyValue.imperialSystem))
            return fluidAsImperial(ml, type);
        else
            return fluidAsMetric(ml, type);
    }

    /**
     * Formats a fluid volume, given in millilitres, to the common imperial form. Note that the type {@link UnitType#NONE}
     * does not apply wrapping and ounce values are rounded correctly, so the output for 9000 millilitres would be:
     * {@link UnitType#NONE}: 317
     * {@link UnitType#SHORT}: 1.98 gal
     * {@link UnitType#LONG}: 1.98 gallons
     * {@link UnitType#LONG_SINGULAR}: 1.98-gallon
     * @param ml Amount of millilitres to format
     * @param type The desired length of the units, see {@link UnitType} for details
     * @return A string containing the imperial, formatted volume, including unit
     */
    public static String fluidAsImperial(double ml, UnitType type) {
        // Convert millilitres to ounces
        double oz = ml / 28.4131;

        // Don't wrap without units
        if (type == UnitType.NONE) return number(Math.round(oz));

        // Wrap ounces to gallons
        double gal = oz / 160;
        return withUnit(oz, "oz", "ounce", gal, "gal", "gallon", type);
    }

    /**
     * Converts a fluid volume, given in millilitres, to the common metric form. Note that the type {@link UnitType#NONE}
     * does not apply wrapping and millilitre values are rounded correctly, so the output for 9000 millilitres would be:
     * {@link UnitType#NONE}: 9000
     * {@link UnitType#SHORT}: 9 l
     * {@link UnitType#LONG}: 9 liters
     * {@link UnitType#LONG_SINGULAR}: 9-liter
     * @param ml Amount of millilitres to convert
     * @param type The desired length of the units, see {@link UnitType} for details
     * @return A string containing the metric, formatted, converted volume, including unit
     */
    public static String fluidAsMetric(double ml, UnitType type) {
        // Don't wrap without units
        if (type == UnitType.NONE) return number(Math.round(ml));

        // Wrap millilitres to litres
        double l = ml / 1000;
        return withUnit(ml, "ml", "millilitre", l, "l", "litre", type);
    }

    /**
     * Selects the appropriate unit, depending on the given values and type, and appends it to the localized value.
     * Applies wrapping whenever the wrapped value is above 1 or below -1.
     * @param value The base value
     * @param shortUnit The abbreviation of the base value unit
     * @param unit The full name of the base value unit
     * @param wrappedValue The wrapped value
     * @param shortWrappedUnit The abbreviation of the wrapped value unit
     * @param wrappedUnit The full name of the wrapped value unit
     * @param type The desired length of the units, see {@link UnitType} for details
     * @return A string containing, the localized, formatted number with the appropriate unit
     */
    public static String withUnit(double value, String shortUnit, String unit,
                                   double wrappedValue, String shortWrappedUnit, String wrappedUnit,
                                   UnitType type) {
        String usedUnit;
        double usedValue;

        if (wrappedValue > 1 || wrappedValue < -1) {
            usedUnit = type == UnitType.SHORT ? shortWrappedUnit
                    : (Math.abs(wrappedValue) == 1 || type == UnitType.LONG_SINGULAR ? wrappedUnit : wrappedUnit + "s");
            usedValue = wrappedValue;
        } else {
            usedUnit = type == UnitType.SHORT ? shortUnit
                    : (Math.abs(value) == 1 || type == UnitType.LONG_SINGULAR ? unit : unit + "s");
            usedValue = value;
        }

        return number(usedValue) + (type == UnitType.LONG_SINGULAR ? "-" : " ") + usedUnit;
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
