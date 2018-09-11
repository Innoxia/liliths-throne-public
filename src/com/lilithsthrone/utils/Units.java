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

    Locale defaultLocale;

    Units() {
        defaultLocale = Locale.getDefault();
        updateDateFormat();
        updateTimeFormat();
        updateNumberFormat();
    }

    /**
     * Resets the date formatter depending on the system locale (if automatic) or the imperial number flag (if manual).
     */
    public void updateDateFormat() {
        boolean autoLocale = Main.getProperties().hasValue(PropertyValue.autoLocale);
        Locale.setDefault(autoLocale ? defaultLocale : Locale.ENGLISH);
        shortDate = (autoLocale
                ? DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                : DateTimeFormatter.ofPattern(Main.getProperties().hasValue(PropertyValue.imperialSystem) ? "MM/dd/yy" : "dd.MM.yy"))
                .withZone(ZoneId.systemDefault());
        longDate = (autoLocale
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
     * Specifies the display of values, where NUMERIC is a rounded number, PRECISE is a number rounded to at most 2
     * places in the metric and eights in the imperial system and TEXT is a full text form.
     */
    public enum ValueType {
        NUMERIC,
        PRECISE,
        TEXT
    }

    /**
     * Specifies the length of units, where NONE means that they will be omitted, SHORT means the abbreviation, LONG
     * means the full text unit, LONG_SINGULAR means full text singular units concatenated with "-" instead of " ".
     */
    public enum UnitType {
        NONE,
        SHORT,
        LONG,
        LONG_SINGULAR
    }

    /**
     * Shortcut for {@link Units#size(double, ValueType, UnitType)} with numeric value and short unit.
     */
    public static String size(double cm) {
        return size(cm, ValueType.NUMERIC, UnitType.SHORT);
    }

    /**
     * Formats a size, given in centimetres, with the current number formatter and units depending on the imperial unit
     * setting as well as the given type.
     * @param cm Amount of centimetres to convert
     * @param vType The format of the value, see {@link ValueType}
     * @param uType The format of the units, see {@link UnitType}
     * @return A string containing the localized, wrapped, converted size and its associated unit
     */
    public static String size(double cm, ValueType vType, UnitType uType) {
        if (Main.getProperties().hasValue(PropertyValue.imperialSystem))
            return sizeAsImperial(cm, vType, uType);
        else
            return sizeAsMetric(cm, vType, uType);
    }

    /**
     * Converts a size, given in centimetres, to the imperial form.
     * @param cm Amount of centimetres to format
     * @param vType The format of the value, see {@link ValueType}
     * @param uType The format of the units, see {@link UnitType}
     * @return A string containing the imperial, formatted size, including unit
     */
    public static String sizeAsImperial(double cm, ValueType vType, UnitType uType) {
        // Convert centimetres to inches
        double inches = cm / 2.54;

        // Wrap inches to feet
        long feet = (long) (inches / 12);
        double remainingInches = inches % 12;

        StringBuilder output = new StringBuilder();

        if (uType == UnitType.SHORT && feet != 0) {
            // Append feet
            output.append(vType == ValueType.TEXT ? Util.intToString((int) feet) : number(feet)).append("&#39;");

            remainingInches = Math.abs(remainingInches);
            if (remainingInches > 0) {
                // Append inches
                switch (vType) {
                    case NUMERIC:
                        if (remainingInches >= 0.5)
                            output.append(number(Math.round(remainingInches))).append("&quot;");
                        break;
                    case PRECISE:
                        if (remainingInches >= 0.0625)
                            output.append(withEighths(remainingInches)).append("&quot;");
                        break;
                    case TEXT:
                        if (remainingInches >= 0.5)
                            output.append(Util.intToString((int) Math.round(remainingInches))).append("&quot;");
                }
            }
        } else {
            // Only wrap when the value is flat
            boolean wrap = feet != 0 && roundTo(remainingInches, 0.125) == 0;
            double usedValue = wrap ? feet : inches;

            appendValue(output, usedValue, vType, true);

            // Append unit
            switch (uType) {
                case NONE:
                    break;
                case SHORT:
                    // Short format wrapping is handled separately
                    output.append("&quot;");
                    break;
                case LONG:
                    if (Math.floor(inches) == 0 && vType != ValueType.PRECISE) {
                        output.setLength(0);
                        return output.append("less than ")
                                .append(vType == ValueType.TEXT ? "one " : "1 ")
                                .append("inch").toString();
                    }

                    output.append(" ");
                    if (usedValue > 1) output.append(wrap ? "feet" : "inches");
                    else output.append(wrap ? "foot" : "inch");
                    break;
                case LONG_SINGULAR:
                    output.append("-").append(wrap ? "foot" : "inch");
            }
        }

        return output.toString();
    }

    /**
     * Formats a size, given in centimetres, to the metric form.
     * @param cm Amount of centimetres to convert
     * @param vType The format of the value, see {@link ValueType}
     * @param uType The format of the units, see {@link UnitType}
     * @return A string containing the metric, formatted size, including unit
     */
    public static String sizeAsMetric(double cm, ValueType vType, UnitType uType) {
        double m = cm / 100;
        return valueWithUnit(cm, "cm", "centimetre", m, "m", "metre", vType, uType, false);
    }

    /**
     * Shortcut for {@link Units#fluid(double, ValueType, UnitType)} with numeric value and short unit.
     */
    public static String fluid(double ml) {
        return fluid(ml, ValueType.NUMERIC, UnitType.SHORT);
    }

    /**
     * Formats a fluid volume, given in millilitres, with the current number formatter and units depending on the
     * imperial unit setting as well as the given type.
     * @param ml Amount of millilitres to convert
     * @param vType The format of the value, see {@link ValueType}
     * @param uType The format of the units, see {@link UnitType}
     * @return A string containing the localized, wrapped, converted volume and its associated unit
     */
    public static String fluid(double ml, ValueType vType, UnitType uType) {
        if (Main.getProperties().hasValue(PropertyValue.imperialSystem))
            return fluidAsImperial(ml, vType, uType);
        else
            return fluidAsMetric(ml, vType, uType);
    }

    /**
     * Converts a fluid volume, given in millilitres, to the imperial form.
     * @param ml Amount of millilitres to convert
     * @param vType The format of the value, see {@link ValueType}
     * @param uType The format of the units, see {@link UnitType}
     * @return A string containing the imperial, formatted volume, including unit
     */
    public static String fluidAsImperial(double ml, ValueType vType, UnitType uType) {
        // Convert millilitres to ounces
        double oz = ml / 28.4131;

        double gal = oz / 160;
        return valueWithUnit(oz, "oz", "ounce", gal, "gal", "gallon", vType, uType, true);
    }

    /**
     * Formats a fluid volume, given in millilitres, to the metric form.
     * @param ml Amount of millilitres to format
     * @param vType The format of the value, see {@link ValueType}
     * @param uType The format of the units, see {@link UnitType}
     * @return A string containing the metric, formatted volume, including unit
     */
    public static String fluidAsMetric(double ml, ValueType vType, UnitType uType) {
        double l = ml / 1000;
        return valueWithUnit(ml, "mL", "millilitre", l, "L", "litre", vType, uType, false);
    }

    /**
     * Shortcut for {@link Units#weight(double, ValueType, UnitType)} with numeric value and short unit.
     */
    public static String weight(double grams) {
        return weight(grams, ValueType.NUMERIC, UnitType.SHORT);
    }

    /**
     * Formats a weight, given in grams, with the current number formatter and units depending on the imperial unit
     * setting as well as the given type.
     * @param grams Amount of grams to convert
     * @param vType The format of the value, see {@link ValueType}
     * @param uType The format of the units, see {@link UnitType}
     * @return A string containing the localized, wrapped, converted weight and its associated unit
     */
    public static String weight(double grams, ValueType vType, UnitType uType) {
        if (Main.getProperties().hasValue(PropertyValue.imperialSystem))
            return weightAsImperial(grams, vType, uType);
        else
            return weightAsMetric(grams, vType, uType);
    }

    /**
     * Converts a weight, given in grams, to the imperial form.
     * @param grams Amount of grams to convert
     * @param vType The format of the value, see {@link ValueType}
     * @param uType The format of the units, see {@link UnitType}
     * @return A string containing the imperial, formatted weight, including unit
     */
    public static String weightAsImperial(double grams, ValueType vType, UnitType uType) {
        // Convert grams to ounces
        double oz = grams / 28.34952;

        double lb = oz / 16;
        return valueWithUnit(oz, "oz", "ounce", lb, "lb", "pound", vType, uType, true);
    }

    /**
     * Formats a weight, given in grams, to the metric form.
     * @param grams Amount of grams to format
     * @param vType The format of the value, see {@link ValueType}
     * @param uType The format of the units, see {@link UnitType}
     * @return A string containing the metric, formatted weight, including unit
     */
    public static String weightAsMetric(double grams, ValueType vType, UnitType uType) {
        double kg = grams / 1000;
        return valueWithUnit(grams, "g", "gram", kg, "kg", "kilogram", vType, uType, false);
    }

    private static StringBuilder appendValue(StringBuilder output, double value, ValueType vType, boolean useEighths) {
        switch (vType) {
            case NUMERIC:
                if (useEighths) output.append(value < 1 ? withEighths(value) : number(Math.round(value)));
                else output.append(number(adaptiveRound(value))); // TODO round less for wrapped values?
                break;
            case PRECISE:
                if (useEighths) output.append(withEighths(value));
                else output.append(value);
                break;
            case TEXT:
                output.append(Util.intToString((int) Math.round(value)));
        }
        return output;
    }

    private static String valueWithUnit(double value, String shortUnit, String unit,
                                        double wrappedValue, String shortWrappedUnit, String wrappedUnit,
                                        ValueType vType, UnitType uType, boolean useEighths) {
        StringBuilder output = new StringBuilder();
        boolean wrap = Math.abs(wrappedValue) >= 1;
        double usedValue = wrap ? wrappedValue : value;

        appendValue(output, usedValue, vType, useEighths);

        // Append unit
        switch (uType) {
            case NONE:
                break;
            case SHORT:
                output.append(" ").append(wrap ? shortWrappedUnit : shortUnit);
                break;
            case LONG:
                if (Math.floor(value) == 0 && vType != ValueType.PRECISE) {
                    output.setLength(0);
                    return output.append("less than ")
                            .append(vType == ValueType.TEXT ? "one " : "1 ")
                            .append(unit).toString();
                }

                output.append(" ").append(wrap ? wrappedUnit : unit);
                if (Math.abs(usedValue) > 1) output.append("s");
                break;
            case LONG_SINGULAR:
                output.append("-").append((wrap ? wrappedUnit : unit));
        }

        return output.toString();
    }

    /**
     * Formats the given value with eighth fraction symbols instead of fractional digits.
     * @param value The number to format
     * @return A string containing the formatted number with an optional eighth symbol
     */
    public static String withEighths(double value) {
        boolean negative = value < 0;
        value = roundTo(Math.abs(value), 0.125);
        double floor = Math.floor(value);

        if (value == floor) return number(floor);

        int eights = (int) Math.round((value - floor) / 0.125);
        return (negative ? '-' : "") + (floor == 0 ? "" : number(floor)) + getEighthSymbol(eights);
    }

    private static String getEighthSymbol(int eighths) {
        switch (eighths) {
            case 1: return "&frac18;";
            case 2: return "&frac14;";
            case 3: return "&frac38;";
            case 4: return "&frac12;";
            case 5: return "&frac58;";
            case 6: return "&frac34;";
            case 7: return "&frac78;";
            default: return "";
        }
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
     * @param nearest Number to round to
     * @return A rounded float
     */
    public static float roundTo(double value, double nearest) {
        return (float) (Math.round(value / nearest) * nearest);
    }

    /**
     * Convenience overload of {@link Units#roundTo(double, double)} for integers.
     * @param nearest Integer number to round to
     * @return A rounded integer
     */
    public static long roundTo(double value, long nearest) {
        return Math.round(value / nearest) * nearest;
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
