package com.lilithsthrone.utils;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.main.Main;
import javafx.application.Platform;

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
 * @version 0.2.10
 * @author Addi
 */
public enum Units {
    FORMATTER;

    public final static int MIN_PRECISION = 0;
    public final static int MAX_PRECISION = 2;

    DateTimeFormatter shortDate;
    DateTimeFormatter longDate;
    DateTimeFormatter time;
    NumberFormat number;

    Units() {
        updateDateFormat();
        updateTimeFormat();
        updateNumberFormat();
    }

    /**
     * Resets the date formatter depending on the system locale (if automatic) or the imperial number flag (if manual).
     */
    public void updateDateFormat() {
        shortDate = (Main.getProperties().hasValue(PropertyValue.autoLocale)
                ? DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                : DateTimeFormatter.ofPattern(Main.getProperties().hasValue(PropertyValue.imperialSystem) ? "MM/dd/yy" : "dd.MM.yy"))
                .withZone(ZoneId.systemDefault());
        longDate = (Main.getProperties().hasValue(PropertyValue.autoLocale)
                ? DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                : DateTimeFormatter.ofPattern(Main.getProperties().hasValue(PropertyValue.imperialSystem) ? "MMMM d, yyyy" : "d. MMMM yyyy"))
                .withZone(ZoneId.systemDefault());
    }

    /**
     * Resets the time formatter depending on the system locale (if automatic) or the 24 hour time flag (if manual).
     */
    public void updateTimeFormat() {
        time = (Main.getProperties().hasValue(PropertyValue.autoLocale)
                ? DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
                : DateTimeFormatter.ofPattern(Main.getProperties().hasValue(PropertyValue.twentyFourHourTime) ? "HH:mm" : "hh:mm a"))
                .withZone(ZoneId.systemDefault());
    }

    /**
     * Resets the number formatter depending on the system locale (if automatic), defaulting to English otherwise.
     * In all cases, output numbers are rounded correctly to the 2nd fraction digit.
     */
    public void updateNumberFormat() {
        number = NumberFormat.getNumberInstance(Main.getProperties().hasValue(PropertyValue.autoLocale) ? Locale.getDefault() : Locale.ENGLISH);
        number.setRoundingMode(RoundingMode.HALF_UP);
        number.setMinimumFractionDigits(MIN_PRECISION);
        number.setMaximumFractionDigits(MAX_PRECISION);
        Platform.runLater(StatusEffect::updateAttributeModifiers);
    }

    /**
     * Resets every formatter. See {@link Units#updateDateFormat()}, {@link Units#updateTimeFormat()} and {@link Units#updateNumberFormat()} for details.
     */
    public static void updateFormats() {
        FORMATTER.updateDateFormat();
        FORMATTER.updateTimeFormat();
        FORMATTER.updateNumberFormat();
    }

    /**
     * Formats a number with the current number formatter.
     * @param amount The floating point number to format
     * @return A string containing the localized number
     */
    public static String number(double amount) {
        return FORMATTER.number.format(amount);
    }

    /**
     * Convenience overload of {@link Units#number(double)} for integers.
     * @param amount The integer number to format
     */
    public static String number(long amount) {
        return FORMATTER.number.format(amount);
    }

    /**
     * Formats a number using the specified amount of minimum and maximum fractional places. Otherwise equal to
     * {@link Units#number(double)}.
     * @param minPrecision Minimum fractional digits
     * @param maxPrecision Maximum fractional digits
     */
    public static String number(double amount, int minPrecision, int maxPrecision) {
        // Apply new settings and generate output
        NumberFormat formatter = FORMATTER.number;
        formatter.setMinimumFractionDigits(minPrecision);
        formatter.setMaximumFractionDigits(maxPrecision);
        String output = formatter.format(amount);

        // Restore default settings
        formatter.setMinimumFractionDigits(MIN_PRECISION);
        formatter.setMaximumFractionDigits(MAX_PRECISION);

        return output;
    }

    /**
     * Specifies the length of dates, where SHORT will use the default numeric style and LONG will use the text style.
     */
    public enum DateType {
        SHORT,
        LONG
    }

    /**
     * Formats a short date and time. See {@link Units#dateTime(TemporalAccessor, DateType)} for details.
     */
    public static String dateTime(TemporalAccessor timePoint) {
        return dateTime(timePoint, DateType.SHORT);
    }

    /**
     * Formats a point in time with the current date and time formatter, separated with ", ".
     * @param timePoint The point in time to convert
     * @param type The style of the date, see {@link DateType} for details
     * @return A string containing the localized date and time
     */
    public static String dateTime(TemporalAccessor timePoint, DateType type) {
        return date(timePoint, type) + ", " + time(timePoint);
    }

    /**
     * Formats a short date. See {@link Units#date(TemporalAccessor, DateType)} for details.
     */
    public static String date(TemporalAccessor timePoint) {
        return date(timePoint, DateType.SHORT);
    }

    /**
     * Similar to {@link Units#dateTime(TemporalAccessor)}, except that this function only outputs the date.
     */
    public static String date(TemporalAccessor timePoint, DateType type) {
        if (type == DateType.SHORT)
            return FORMATTER.shortDate.format(timePoint);
        return FORMATTER.longDate.format(timePoint);
    }

    /**
     * Similar to {@link Units#dateTime(TemporalAccessor)}, except that this function only outputs the time.
     */
    public static String time(TemporalAccessor timePoint) {
        return FORMATTER.time.format(timePoint);
    }

    /**
     * Specifies the length of units, where NONE means that they will be omitted, SHORT means the abbreviation, LONG
     * means the full text unit, LONG_SINGULAR means full text singular units (concatenated with "-" instead of " " and
     * ROUGH_TEXT means more aggressively rounded full text numbers and long units.
     */
    public enum UnitType {
        NONE,
        SHORT,
        LONG,
        LONG_SINGULAR,
        ROUGH_TEXT
    }

    /**
     * Formats a size in centimetres with short units. See {@link Units#size(double, UnitType)} for details.
     */
    public static String size(double cm) {
        return size(cm, UnitType.SHORT);
    }

    /**
     * Formats a size, given in centimetres, with the current number formatter and units depending on the imperial unit
     * setting as well as the given type. For examples of the result, see {@link Units#sizeAsImperial(double, UnitType)} and
     * {@link Units#sizeAsMetric(double, UnitType)}.
     * @param cm Amount of centimetres to convert
     * @param type The desired length of the units, see {@link UnitType} for details
     * @return A string containing the localized, wrapped, converted size and its associated unit
     */
    public static String size(double cm, UnitType type) {
        if (Main.getProperties().hasValue(PropertyValue.imperialSystem))
            return sizeAsImperial(cm, type);
        else
            return sizeAsMetric(cm, type);
    }

    /**
     * Formats a size, given in centimetres, to the common imperial form. Note that only {@link UnitType#SHORT} applies
     * wrapping, so the output for 142 centimetres would be:
     * {@link UnitType#NONE}: 56
     * {@link UnitType#SHORT}: 4'8"
     * {@link UnitType#LONG}: 56 inches
     * {@link UnitType#LONG_SINGULAR}: 56-inch
     * {@link UnitType#ROUGH_TEXT}: five feet
     * @param cm Amount of centimetres to format
     * @param type The desired length of the units, see {@link UnitType} for details
     * @return A string containing the imperial, formatted size, including unit
     */
    public static String sizeAsImperial(double cm, UnitType type) {
        // Convert centimetres to inches
        double inches = adaptiveRound(cm / 2.54);

        switch (type) {
            case NONE:
                return number(inches);
            case ROUGH_TEXT:
                return roughly(inches / 12, "foot", "feet");
            case LONG:
            case LONG_SINGULAR:
                if (Math.floor(inches) == 0) {
                    return "less than 1 inch";
                } else {
                    String number = number(inches);
                    if (type == UnitType.LONG) {
                        return number + (Math.abs(inches) <= 1 ? " inch" : " inches");
                    } else {
                        return number + "-inch";
                    }
                }
            default:
                // Wrap inches to feet
                long feet = (long) (inches / 12);
                double inch = inches % 12;

                String output = "";
                if (feet != 0) {
                    output = number(feet) + "&#39;";
                }

                if (inch != 0) {
                    output += number(output.isEmpty() ? inch : Math.abs(inch)) + "&quot;";
                } else if (feet == 0) {
                    output = "0&quot;";
                }
                return output;
        }
    }

    /**
     * Converts a size, given in centimetres, to the common metric form. Note that the type {@link UnitType#NONE} does not
     * apply wrapping and centimetre values are rounded correctly, so the output for 142 centimetres would be:
     * {@link UnitType#NONE}: 142
     * {@link UnitType#SHORT}: 1.42 m
     * {@link UnitType#LONG}: 1.42 metres
     * {@link UnitType#LONG_SINGULAR}: 1.42-metre
     * {@link UnitType#ROUGH_TEXT}: one metre
     * @param cm Amount of centimetres to convert
     * @param type The desired length of the units, see {@link UnitType} for details
     * @return A string containing the metric, formatted, converted size, including unit
     */
    public static String sizeAsMetric(double cm, UnitType type) {
        double m = cm / 100;
        return withUnit(Math.round(cm), "cm", "centimetre", m, "m", "metre", type);
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
     * {@link UnitType#ROUGH_TEXT}: two gallons
     * @param ml Amount of millilitres to convert
     * @param type The desired length of the units, see {@link UnitType} for details
     * @return A string containing the imperial, formatted, converted volume, including unit
     */
    public static String fluidAsImperial(double ml, UnitType type) {
        // Convert millilitres to ounces
        double oz = ml / 28.4131;

        double gal = oz / 160;
        return withUnit(round(oz, 1), "oz", "ounce", gal, "gal", "gallon", type);
    }

    /**
     * Converts a fluid volume, given in millilitres, to the common metric form. Note that the type {@link UnitType#NONE}
     * does not apply wrapping and millilitre values are rounded correctly, so the output for 9000 millilitres would be:
     * {@link UnitType#NONE}: 9000
     * {@link UnitType#SHORT}: 9 L
     * {@link UnitType#LONG}: 9 litres
     * {@link UnitType#LONG_SINGULAR}: 9-litre
     * {@link UnitType#ROUGH_TEXT}: nine litres
     * @param ml Amount of millilitres to format
     * @param type The desired length of the units, see {@link UnitType} for details
     * @return A string containing the metric, formatted volume, including unit
     */
    public static String fluidAsMetric(double ml, UnitType type) {
        double l = ml / 1000;
        return withUnit(Math.round(ml), "mL", "millilitre", l, "L", "litre", type);
    }

    /**
     * Formats a weight in grams with short units. See {@link Units#size(double, UnitType)} for details.
     */
    public static String weight(double grams) {
        return weight(grams, UnitType.SHORT);
    }

    /**
     * Formats a weight, given in grams, with the current number formatter and units depending on the imperial unit
     * setting as well as the given type. For examples of the result, see {@link Units#weightAsImperial(double, UnitType)}
     * and {@link Units#weightAsMetric(double, UnitType)}.
     * @param grams Amount of grams to convert
     * @param type The desired length of the units, see {@link UnitType} for details
     * @return A string containing the localized, wrapped, converted weight and its associated unit
     */
    public static String weight(double grams, UnitType type) {
        if (Main.getProperties().hasValue(PropertyValue.imperialSystem))
            return weightAsImperial(grams, type);
        else
            return weightAsMetric(grams, type);
    }

    /**
     * Formats a weight, given in grams, to the common imperial form. Note that the type {@link UnitType#NONE} does not
     * apply wrapping and gram values are rounded correctly, so the output for 9000 grams would be:
     * {@link UnitType#NONE}: 317
     * {@link UnitType#SHORT}: 19.84 lb
     * {@link UnitType#LONG}: 19.84 pounds
     * {@link UnitType#LONG_SINGULAR}: 19.84-pound
     * {@link UnitType#ROUGH_TEXT}: twenty pounds
     * @param grams Amount of grams to convert
     * @param type The desired length of the units, see {@link UnitType} for details
     * @return A string containing the imperial, formatted, converted weight, including unit
     */
    public static String weightAsImperial(double grams, UnitType type) {
        // Convert grams to ounces
        double oz = grams / 28.34952;

        double lb = oz / 16;
        return withUnit(round(oz, 1), "oz", "ounce", lb, "lb", "pound", type);
    }

    /**
     * Converts a weight, given in grams, to the common metric form. Note that the type {@link UnitType#NONE} does not
     * apply wrapping and gram values are rounded correctly, so the output for 9000 grams would be:
     * {@link UnitType#NONE}: 9000
     * {@link UnitType#SHORT}: 9 kg
     * {@link UnitType#LONG}: 9 kilograms
     * {@link UnitType#LONG_SINGULAR}: 9-kilogram
     * {@link UnitType#ROUGH_TEXT}: nine kilograms
     * @param grams Amount of grams to format
     * @param type The desired length of the units, see {@link UnitType} for details
     * @return A string containing the metric, formatted weight, including unit
     */
    public static String weightAsMetric(double grams, UnitType type) {
        double kg = grams / 1000;
        return withUnit(Math.round(grams), "g", "gram", kg, "kg", "kilogram", type);
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
        if (type == UnitType.NONE) return number(Math.round(value));
        if (type == UnitType.ROUGH_TEXT) return roughly(wrappedValue, wrappedUnit, wrappedUnit + "s");
        if ((type == UnitType.LONG || type == UnitType.LONG_SINGULAR) && Math.floor(value) == 0) return "less than 1 " + unit;

        String usedUnit;
        double usedValue;

        if (wrappedValue > 1 || wrappedValue < -1) {
            // Use wrapped value
            usedUnit = type == UnitType.SHORT ? shortWrappedUnit
                    : (Math.abs(wrappedValue) <= 1 || type == UnitType.LONG_SINGULAR ? wrappedUnit : wrappedUnit + "s");
            usedValue = wrappedValue;
        } else {
            // Use base value
            usedUnit = type == UnitType.SHORT ? shortUnit
                    : (Math.abs(value) <= 1 || type == UnitType.LONG_SINGULAR ? unit : unit + "s");
            usedValue = value;
        }

        return number(usedValue) + (type == UnitType.LONG_SINGULAR ? "-" : " ") + usedUnit;
    }

    /**
     * Converts the given value to text and appends the given unit (in plural form, if necessary). Values above 10 are
     * rounded to the nearest multiple of 5.
     * @param value The number to convert
     * @param unit The unit to append
     * @param units The plural unit to append
     * @return A string containing the rounded number as text with the appropriate unit
     */
    public static String roughly(double value, String unit, String units) {
        if (value < 1) return "less than one " + unit;
        if (value >= 1995) return "thousands of " + units;

        long usedValue = value < 10 ? Math.round(value) : roundTo(value, 5);
        return Util.intToString((int) usedValue) + " " + (Math.abs(usedValue) > 1 ? units : unit);
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

    /**
     * Rounds a given number to the nearest multiple of the second parameter. Note that {@link Units#round(double, int)} is
     * more precise and should be preferred.
     * @param value Number to round
     * @param toNearest Number to round to
     * @return A rounded float
     */
    public static float roundTo(double value, double toNearest) {
        return (float) (Math.round(value / toNearest) * toNearest);
    }

    /**
     * Convenience overload of {@link Units#roundTo(double, double)} for integers.
     * @param toNearest Integer number to round to
     * @return A rounded integer
     */
    public static long roundTo(double value, long toNearest) {
        return Math.round(value / toNearest) * toNearest;
    }

    /**
     * Rounds a given number based on its value. Values below 2 are rounded to 1 fractional digit, values below 10 are
     * rounded to the nearest 0.5, otherwise the number is rounded to the nearest integer.
     * @param value Number to round
     * @return A variably rounded double
     */
    public static double adaptiveRound(double value) {
        double absoluteValue = Math.abs(value);
        if (absoluteValue < 2) return round(value, 1);
        if (absoluteValue < 10) return roundTo(value, 0.5);
        return Math.round(value);
    }
}
