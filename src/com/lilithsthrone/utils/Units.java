package com.lilithsthrone.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.main.Main;

/**
 * Collection of utility functions for date, time and number format conversion.
 *
 * @since 0.2.11
 * @version 0.3.1
 * @author Addi
 */
public enum Units {
    FORMATTER;

    public final int MIN_PRECISION = 0;
    public final int MAX_PRECISION = 2;

    public final List<String> imperialCountries = Arrays.asList("US", "LR", "MM");
    public final List<String> twelveHourCountries = Arrays.asList("US", "UK", "PH", "CA", "AU", "NZ", "IN", "EG", "SA", "CO", "PK", "MY", "SG", "ZA");

    DateTimeFormatter shortDate;
    DateTimeFormatter longDate;
    DateTimeFormatter time;
    NumberFormat number;

    Locale defaultLocale;

    Units() {
        defaultLocale = Locale.getDefault();

        if (Main.getProperties() == null) {
            new NullPointerException("Unit formatters initialized before properties. Assuming auto locale.").printStackTrace();
            updateFormats(true);
        } else {
            updateSettings();
            updateFormats(Main.getProperties().hasValue(PropertyValue.autoLocale));
        }
    }

    /**
     * Resets the 24-hour and imperial system settings to reflect automatically detected values.
     */
    public void updateSettings() {
        if (Main.getProperties().hasValue(PropertyValue.autoLocale)) {
            String countryCode = defaultLocale.getCountry().toUpperCase();
            boolean isMetric = !imperialCountries.contains(countryCode);
            Main.getProperties().setValue(PropertyValue.metricSizes, isMetric);
            Main.getProperties().setValue(PropertyValue.metricFluids, isMetric);
            Main.getProperties().setValue(PropertyValue.metricWeights, isMetric);
            Main.getProperties().setValue(PropertyValue.twentyFourHourTime, !twelveHourCountries.contains(countryCode));
            Main.getProperties().setValue(PropertyValue.internationalDate, isMetric);
        }
    }

    /**
     * Resets the date formatter depending on the system locale (if automatic) or the imperial number flag (if manual).
     * @param autoLocale Determines if automatic or manual detection is used
     */
    public void updateDateFormat(boolean autoLocale) {
        Locale.setDefault(autoLocale ? defaultLocale : Locale.ENGLISH);
        shortDate = (autoLocale ? DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                : DateTimeFormatter.ofPattern(Main.getProperties().hasValue(PropertyValue.internationalDate) ? "dd.MM.yy" : "MM/dd/yy"))
                .withZone(ZoneId.systemDefault());
        longDate = DateTimeFormatter.ofPattern("d'%o %m' yyyy")
                .withZone(ZoneId.systemDefault());
    }

    /**
     * Resets the time formatter depending on the system locale (if automatic) or the 24 hour time flag (if manual).
     * @param autoLocale Determines if automatic or manual detection is used
     */
    public void updateTimeFormat(boolean autoLocale) {
        time = (autoLocale ? DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
                : DateTimeFormatter.ofPattern(Main.getProperties().hasValue(PropertyValue.twentyFourHourTime) ? "HH:mm" : "hh:mm a"))
                .withZone(ZoneId.systemDefault());
    }

    /**
     * Resets the number formatter depending on the system locale (if automatic), defaulting to English otherwise.
     * In all cases, output numbers are rounded correctly to the 2nd fraction digit.
     * @param autoLocale Determines if automatic or manual detection is used
     */
    public void updateNumberFormat(boolean autoLocale) {
        number = NumberFormat.getNumberInstance(autoLocale ? Locale.getDefault() : Locale.ENGLISH);
        number.setRoundingMode(RoundingMode.HALF_UP);
        number.setMinimumFractionDigits(MIN_PRECISION);
        number.setMaximumFractionDigits(MAX_PRECISION);
//        Platform.runLater(AbstractStatusEffect::updateAttributeModifiers);
    }

    /**
     * Resets every formatter. See {@link Units#updateDateFormat(boolean)}, {@link Units#updateTimeFormat(boolean)} and
     * {@link Units#updateNumberFormat(boolean)} for details.
     * @param autoLocale Determines if automatic or manual detection is used
     */
    public void updateFormats(boolean autoLocale) {
        updateDateFormat(autoLocale);
        updateTimeFormat(autoLocale);
        updateNumberFormat(autoLocale);
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
        formatter.setMinimumFractionDigits(FORMATTER.MIN_PRECISION);
        formatter.setMaximumFractionDigits(FORMATTER.MAX_PRECISION);

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
        return FORMATTER.longDate.format(timePoint)
                .replaceFirst("%o", getOrdinal(timePoint.get(ChronoField.DAY_OF_MONTH)))
                .replaceFirst("%m", getMonthName(timePoint.get(ChronoField.MONTH_OF_YEAR)));
    }

    /**
     * Similar to {@link Units#dateTime(TemporalAccessor)}, except that this function only outputs the time.
     */
    public static String time(TemporalAccessor timePoint) {
        return FORMATTER.time.format(timePoint);
    }

    /**
     * Specifies the display of values, where NUMERIC is a rounded number, PRECISE is a number rounded to at most 2
     * places in the metric and eights in the imperial system and TEXT is a more aggressively rounded full text form.
     */
    public enum ValueType {
        NUMERIC,
        PRECISE,
        TEXT
    }

    /**
     * Specifies the length of units, where NONE means that they will be omitted, SHORT means the abbreviation, LONG
     * means the full text unit and LONG_SINGULAR means full text singular units concatenated with "-" instead of " ".
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
     * Shortcut for {@link Units#size(double, ValueType, UnitType)} with numeric value.
     */
    public static String size(double cm, UnitType uType) {
        return size(cm, ValueType.NUMERIC, uType);
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
        if (Main.getProperties().hasValue(PropertyValue.metricSizes)) {
            return sizeAsMetric(cm, vType, uType);
        } else {
            return sizeAsImperial(cm, vType, uType);
        }
    }

    public final static String INCH_SYMBOL = "&quot;";
    public final static String FOOT_SYMBOL = "&#39;";
    /**
     * Converts a size, given in centimetres, to the imperial form.
     * @param cm Amount of centimetres to format
     * @param vType The format of the value, see {@link ValueType}
     * @param uType The format of the units, see {@link UnitType}
     * @return A string containing the imperial, formatted size, including unit
     */
    @SuppressWarnings("incomplete-switch")
    public static String sizeAsImperial(double cm, ValueType vType, UnitType uType) {
        // Convert centimetres to inches
        double inches = cm / 2.54;

        // Wrap inches to feet
        double roundingFactor = (vType == ValueType.PRECISE || Math.abs(inches) < 1) ? 0.25 : 1;
        long feet = (long) (roundTo(inches, roundingFactor) / 12);
        double remainingInches = roundTo(inches, roundingFactor) % 12;


        StringBuilder output = new StringBuilder();

        boolean both = uType != UnitType.NONE && vType != ValueType.TEXT
                && feet != 0 && remainingInches != 0;
        boolean wrap = (vType == ValueType.TEXT && Math.abs(inches) >= 11.5)
                || both || (feet != 0 && remainingInches == 0);
        double usedValue = wrap ? feet : inches;

        // Append first unit, which may be either feet or inches
        output.append(value(usedValue, vType, true));
        switch (uType) {
            case NONE:
                break;
            case SHORT:
                output.append(wrap ? FOOT_SYMBOL : INCH_SYMBOL);
                break;
            case LONG:
                if(Math.floor(inches) == 0 && vType != ValueType.PRECISE) {
                    output.setLength(0);
                    return output.append("less than ")
                            .append(vType == ValueType.TEXT ? "one" : "1")
                            .append(" inch").toString();
                }
                
                output.append(" ");
                if(Math.abs(usedValue) >= 1 + roundingFactor/2
                		|| usedValue == 0) {
                	output.append(wrap ? "feet" : "inches");
                } else {
                	output.append(wrap ? "foot" : "inch");
                }
                break;
            case LONG_SINGULAR:
                output.append("-").append(wrap ? "foot" : "inch");
        }

        // Append second unit for long or short notation and if neither value is 0
        if (both) {
            if (uType == UnitType.LONG) output.append(" and ");
            if (uType == UnitType.LONG_SINGULAR) output.append("-");
            remainingInches = Math.abs(roundTo(remainingInches, roundingFactor));
            output.append(value(remainingInches, vType, true));
            switch (uType) {
                case SHORT:
                    output.append(INCH_SYMBOL);
                    break;
                case LONG:
                    output.append(" ").append(remainingInches >= 1 + roundingFactor/2 ? "inches" : "inch");
                    break;
                case LONG_SINGULAR:
                    break;
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
        return valueWithUnit(cm, "cm", "centimetre"/*+(cm!=1?"s":"")*/, m, "m", "metre"/*+(cm!=100?"s":"")*/, vType, uType, false);
    }

    /**
     * Shortcut for {@link Units#fluid(double, ValueType, UnitType)} with numeric value and short unit.
     */
    public static String fluid(double ml) {
        return fluid(ml, ValueType.NUMERIC, UnitType.SHORT);
    }

    /**
     * Shortcut for {@link Units#fluid(double, ValueType, UnitType)} with numeric value.
     */
    public static String fluid(double ml, UnitType uType) {
        return fluid(ml, ValueType.NUMERIC, uType);
    }

    /**
     * Shortcut for {@link Units#fluid(double, ValueType, UnitType)} with short units.
     */
    public static String fluid(double ml, ValueType vType) {
        return fluid(ml, vType, UnitType.SHORT);
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
        if (Main.getProperties().hasValue(PropertyValue.metricFluids))
            return fluidAsMetric(ml, vType, uType);
        else
            return fluidAsImperial(ml, vType, uType);
    }

    public static float mlToOz(float ml) {
    	return (float) (ml / 28.4131);
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
        return valueWithUnit(ml, "mL", "millilitre", l, "L", "litre", vType, uType, false); // Innoxia's note: I usually prefer the lowercase l for ml and l, but LT's font makes it look bad.
    }

    /**
     * Shortcut for {@link Units#weight(double, ValueType, UnitType)} with numeric value and short unit.
     */
    public static String weight(double grams) {
        return weight(grams, ValueType.NUMERIC, UnitType.SHORT);
    }

    /**
     * Shortcut for {@link Units#weight(double, ValueType, UnitType)} with numeric value.
     */
    public static String weight(double grams, UnitType uType) {
        return weight(grams, ValueType.NUMERIC, uType);
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
        if (Main.getProperties().hasValue(PropertyValue.metricWeights))
            return weightAsMetric(grams, vType, uType);
        else
            return weightAsImperial(grams, vType, uType);
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

    private static String value(double value, ValueType vType, boolean useQuarters) {
        switch (vType) {
            case PRECISE:
                if (useQuarters) return withQuarters(value);
                return number(value);
            case TEXT:
                return Util.intToString((int) aggressiveRound(value));
            default:
                if (useQuarters) return Math.abs(value) < 0.875 ? withQuarters(value) : number(Math.round(value));
                return number(adaptiveRound(value));
        }
    }

    private static String valueWithUnit(double value, String shortUnit, String unit,
                                        double wrappedValue, String shortWrappedUnit, String wrappedUnit,
                                        ValueType vType, UnitType uType, boolean useQuarters) {
        StringBuilder output = new StringBuilder();
        boolean wrap = Math.abs(wrappedValue) >= 1 && vType != ValueType.PRECISE;
        double usedValue = wrap ? wrappedValue : value;
        if (useQuarters) usedValue = roundTo(usedValue, 0.25);
        
        if(value>0 && usedValue==0) {
        	output.append("&lt;");
        	usedValue = 0.25;
        }
        
        // Append value with increased precision if it is wrapped and numeric
        output.append(value(usedValue, wrap && vType == ValueType.NUMERIC ? ValueType.PRECISE : vType, useQuarters));

        // Append unit
        switch (uType) {
            case NONE:
                break;
            case SHORT:
                output
                //.append(" ")
                .append(wrap ? shortWrappedUnit : shortUnit);
                break;
            case LONG:
                if (Math.floor(value) == 0 && vType != ValueType.PRECISE) {
                    output.setLength(0);
                    return output.append("less than ")
                            .append(vType == ValueType.TEXT ? "one " : "1 ")
                            .append(unit).toString();
                }

                output.append(" ").append(wrap ? wrappedUnit : unit);
                if (Math.abs(usedValue) != 1.0) output.append("s");
                break;
            case LONG_SINGULAR:
                output.append(" ").append((wrap ? wrappedUnit : unit));
        }

        return output.toString();
    }

    /**
     * Formats the given value with eighth fraction symbols instead of fractional digits.
     * @param value The number to format
     * @return A string containing the formatted number with an optional eighth symbol
     */
    public static String withQuarters(double value) {
        boolean negative = value < 0;
        value = roundTo(Math.abs(value), 0.25);
        double floor = Math.floor(value);

        if (value == floor) return (negative ? '-' : "") + number(floor);

        int quarters = (int) Math.round((value - floor) / 0.25);
        return (negative ? '-' : "") + (floor == 0 ? "" : number(floor)) + getQuarterSymbol(quarters);
    }

    private static String getQuarterSymbol(int quarters) {
        switch (quarters) {
            case 1: return "&frac14;";
            case 2: return "&frac12;";
            case 3: return "&frac34;";
            default: return "";
        }
    }

    private final static String[] suffixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
    /**
     * Selects the correct ordinal for a given value. Only the ordinal itself is returned.
     * @param value The number to select an ordinal for
     * @return A string containing the ordinal
     */
    public static String getOrdinal(int value) {
        switch (value % 100) {
            case 11:
            case 12:
            case 13:
                return "th";
            default:
                return suffixes[value % 10];
        }
    }

    public static String getMonthName(int month) {
        switch (month) {
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
        }
        return "";
    }

    /**
     * Rounds a given number to a given amount of fractional places.
     * @param value Number to round
     * @param places Amount of fractional places
     * @return A correctly rounded float
     */
    public static float round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException("Amount of fractional places cannot be less than 0.");
        return BigDecimal.valueOf(value).setScale(places, RoundingMode.HALF_UP).floatValue();
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
        if (absoluteValue <= 2) return round(value, 1);
        if (absoluteValue <= 10) return roundTo(value, 0.5);
        return Math.round(value);
    }

    /**
     * Rounds a given number based on its value, possibly exceeding the original value by more than 1. Values below 10
     * are rounded to the nearest integer, values below 50 are rounded to the nearest 5, otherwise the number is rounded
     * to the nearest 10.
     * @param value Number to round
     * @return A variably rounded long
     */
    public static long aggressiveRound(double value) {
        double absoluteValue = Math.abs(value);
        if (absoluteValue <= 10) return Math.round(value);
        if (absoluteValue <= 50) return roundTo(value, 5);
        return roundTo(value, 10);
    }
}
