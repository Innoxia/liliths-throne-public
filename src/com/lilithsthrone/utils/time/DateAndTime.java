package com.lilithsthrone.utils.time;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

/**
 * Implemented using equations found here: https://en.wikipedia.org/wiki/Sunrise_equation#Complete_calculation_on_Earth
 * 
 * @since 0.3.5.5
 * @version 0.3.5.5
 * @author Innoxia
 */
public class DateAndTime {

	private static final boolean DEBUG = false;
	
	private static double sin(double deg) {
		return Math.sin(Math.toRadians(deg));
	}

	private static double cos(double deg) {
		return Math.cos(Math.toRadians(deg));
	}

	private static double asin(double x) {
		return Math.toDegrees(Math.asin(x));
	}

	private static double acos(double x) {
		return Math.toDegrees(Math.acos(x));
	}
	
	/**
	 * <b>Only works for dates of 4712 BCE or greater.</b>
	 * 
	 * @param date The date to convert to julianDate
	 * @return A double value representing days since 4713 BCE.
	 */
	private static double julianDate(LocalDateTime date) {
		int years = Math.abs(-4713 - date.getYear()) - 1; // -1 to get rid of the year which we are currently in
		int leapYearSwitch = 4713 + 1582;
		int leapYears = 0;
		if(years>leapYearSwitch) {
			int extraYears = (years%leapYearSwitch);
			leapYears = 1 + ((leapYearSwitch-1)/4) // 4712 BCE was a leap year, so add 1 for that, then start from 4712
					+ (extraYears/4) - extraYears/100 + extraYears/400; // After 1582, leap years are not counted for years that are divisible by 100 but not divisible by 400.
		} else {
			leapYears = years/4;
		}

		double day = date.get(ChronoField.DAY_OF_YEAR);
		double julianDate = (years * 365d) + day + leapYears - 10 - 0.5; // Subtract 10 to account for changeover from Julian to Gregorian in 1582
		
		if(DEBUG) {
			System.out.println("leapYears: "+leapYears);
			System.out.println("julianDate: "+julianDate);
		}
//		System.out.println("julianDate: "+julianDate);
		
		return julianDate;
	}
	
	/**
	 * https://en.wikipedia.org/wiki/Julian_day
	 * 
	 * @param date The date you want to convert to julian days.
	 * @return How many julian days have passed since Jan 1st, 2000 12:00.
	 */
	private static double julianDay(LocalDateTime date) {
		return julianDate(date) - 2451545.0 + 0.0008;
	}
	
	/**
	 * https://en.wikipedia.org/wiki/Solar_time#Mean_solar_time
	 * 
	 * @param julianDay The date, converted to julianDays.
	 * @param longitudeWest The longitude, in degrees, of the observer (west is negative, east is positive).
	 * @return An approximation of mean solar time at julianDay.
	 */
	private static double meanSolarNoon(double julianDay, double longitudeWest) {
		return julianDay - (longitudeWest/360);
	}
	
	/**
	 * https://en.wikipedia.org/wiki/Mean_anomaly
	 * 
	 * @param meanSolarNoon Pass in a value from meanSolarNoon().
	 * @return The solar mean anomaly.
	 */
	private static double solarMeanAnomaly(double meanSolarNoon) {
		return (357.5291 + 0.98560028 * meanSolarNoon) % 360;
	}

	/**
	 * https://en.wikipedia.org/wiki/Equation_of_the_center
	 * 
	 * @param solarMeanAnomaly Pass in value calculated from solarMeanAnomaly().
	 * @return The Equation of the centre value needed to calculate ecliptic longitude.
	 */
	private static double equationOfTheCenter(double solarMeanAnomaly) {
		return (1.9148*sin(solarMeanAnomaly)) + (0.0200*sin(2*solarMeanAnomaly)) + (0.0003*sin(3*solarMeanAnomaly));
	}

	/**
	 * https://en.wikipedia.org/wiki/Ecliptic_coordinate_system#Spherical_coordinates
	 * 
	 * @param solarMeanAnomaly Pass in value calculated from solarMeanAnomaly().
	 * @param equationOfTheCenter Pass in value calculated from equationOfTheCenter().
	 * @return The ecliptic longitude.
	 */
	private static double eclipticLongitude(double solarMeanAnomaly, double equationOfTheCenter) {
		return (solarMeanAnomaly + equationOfTheCenter + 180 + 102.9372) % 360;
	}

	/**
	 * https://en.wikipedia.org/wiki/Julian_day
	 *  
	 * @param meanSolarNoon Pass in value calculated from meanSolarNoon().
	 * @param solarMeanAnomaly Pass in value calculated from solarMeanAnomaly().
	 * @param eclipticLongitude Pass in value calculated from eclipticLongitude().
	 * @return The julain date for the local true solar transit.
	 */
	private static double solarTransit(double meanSolarNoon, double solarMeanAnomaly, double eclipticLongitude) {
		return 2451545.0 + meanSolarNoon + (0.0053*sin(solarMeanAnomaly)) - (0.0069*sin(2*eclipticLongitude));
	}
	
	/**
	 * https://en.wikipedia.org/wiki/Declination
	 * 
	 * @param eclipticLongitude Pass in value calculated from eclipticLongitude().
	 * @return The declination of the sun.
	 */
	private static double declinationOfTheSun(double eclipticLongitude) {
		return asin(sin(eclipticLongitude) * sin(23.44));
	}

	/**
	 * https://en.wikipedia.org/wiki/Hour_angle
	 * 
	 * @param declinationOfTheSun Pass in value calculated from declinationOfTheSun().
	 * @param angle The desired SolarElevationAngle from which times of 'rise' and 'set' can be found.
	 * @param latitude The latitude of the observer.
	 * @return The hour angle from the observer's zenith.
	 */
	private static double hourAngle(double declinationOfTheSun, SolarElevationAngle angle, double latitude) {
		return acos((sin(angle.getAngle()) - sin(latitude) * sin(declinationOfTheSun)) / (cos(latitude) * cos(declinationOfTheSun)));
	}
	
	/**
	 * @param solarTransit Pass in value calculated from solarTransit().
	 * @param hourAngle Pass in value calculated from hourAngle().
	 * @return The julian date at which the desired SolarElevationAngle transitions into the next phase (i.e. sunrise).
	 */
	private static double sunrise(double solarTransit, double hourAngle) {
		return solarTransit - (hourAngle/360d);
	}
	
	/**
	 * @param solarTransit Pass in value calculated from solarTransit().
	 * @param hourAngle Pass in value calculated from hourAngle().
	 * @return The julian date at which the desired SolarElevationAngle transitions back into the previous phase (i.e. sunset).
	 */
	private static double sunset(double solarTransit, double hourAngle) {
		return solarTransit + (hourAngle/360d);
	}
	
	/**
	 * @param julianDay The Julian date which you want converted into LocalDateTime.
	 * @return The LocalDateTime of the passed in Julian date.
	 */
	public static LocalDateTime julianDayToDate(double julianDate) {
//		  Q = JD+0.5
//		  Z = Integer part of Q
//		  W = (Z - 1867216.25)/36524.25
//		  X = W/4
//		  A = Z+1+W-X
//		  B = A+1524
//		  C = (B-122.1)/365.25
//		  D = 365.25xC
//		  E = (B-D)/30.6001
//		  F = 30.6001xE
//		  Day of month = B-D-F+(Q-Z)
//		  Month = E-1 or E-13 (must get number less than or equal to 12)
//		  Year = C-4715 (if Month is January or February) or C-4716 (otherwise)

		double Q = (julianDate);
		int Z = (int) Q;
		int W = (int) ((Z - 1867216.25)/36524.25);
		int X = W/4;
		int A = Z+1+W-X;
		int B = A+1524;
		int C = (int) ((B-122.1)/365.25);
		int D = (int) (365.25*C);
		int E = (int) ((B-D)/30.6001);
		int F = (int) (30.6001*E);
		
		int day = (int) (B-D-F+(Q-Z)) + 1;
		int month = (E-1)<=12?(E-1):E-13;
		int year = month==1||month==2?C-4715:C-4716;
		
		final double dayFraction = julianDate%1;
		final int hours = Math.min(23, (int) (dayFraction * 24));
		final int minutes = Math.min(59, (int) ((dayFraction * 24 - hours) * 60d));
		final int seconds = Math.min(59, (int) ((dayFraction * 24 * 3600 - (hours * 3600 + minutes * 60)) + .5));
		
		try {
			return LocalDateTime.of(year, month, day, hours, minutes, seconds);
		} catch(Exception ex) {
			if(DEBUG) {
				System.err.println("Error in julianDayToDate(): "+julianDate+": "+hours+":"+minutes+":"+seconds+" | "+day+", "+month+", "+year);
			}
			return null;
		}
	}
	
	/**
	 * @param date The date at which you want to find times of change of SolarElevationAngle.
	 * @param angle The SolarElevationAngle that you are interested in.
	 * @return The times when this SolarElevationAngle transitions into a different state, with [0] being the AM change (sunrise) and [1] being the PM change (sunset).<br/>
	 * <b>This method will return null if the supplied SolarElevationAngle does not have a transition!</b> This typically occurs at higher or lower latitudes.
	 *  For example, at the latitude of London during summer months, Astronomical Twilight never fully transitions into night.
	 */
	public static LocalDateTime[] getTimeOfSolarElevationChange(LocalDateTime date, SolarElevationAngle angle, double latitude, double longitudeWest) {
		double julianDay = julianDay(date);
		double meanSolarNoon = meanSolarNoon(julianDay, longitudeWest);
		double solarMeanAnomaly = solarMeanAnomaly(meanSolarNoon);
		double equationOfTheCenter = equationOfTheCenter(solarMeanAnomaly);
		double eclipticLongitude = eclipticLongitude(solarMeanAnomaly, equationOfTheCenter);
		double solarTransit = solarTransit(meanSolarNoon, solarMeanAnomaly, eclipticLongitude);
		double declinationOfTheSun = declinationOfTheSun(eclipticLongitude);
		double hourAngle = hourAngle(declinationOfTheSun, angle, latitude);

		double sunrise = sunrise(solarTransit, hourAngle);
		LocalDateTime sunriseLDT = Double.isNaN(sunrise)?null:julianDayToDate(sunrise);

		double sunset = sunset(solarTransit, hourAngle);
		LocalDateTime sunsetLDT = Double.isNaN(sunset)?null:julianDayToDate(sunset);
		
		if(DEBUG) {
			System.out.println("julianDay: "+julianDay);
			System.out.println("meanSolarNoon: "+meanSolarNoon);
			System.out.println("solarMeanAnomaly: "+solarMeanAnomaly);
			System.out.println("equationOfTheCenter: "+equationOfTheCenter);
			System.out.println("eclipticLongitude: "+eclipticLongitude);
			System.out.println("solarTransit: "+solarTransit);
			System.out.println("declinationOfTheSun: "+declinationOfTheSun);
			System.out.println("hourAngle: "+hourAngle);
			
			System.out.println(sunrise);
			System.out.println("Sunrise: "+sunriseLDT);

			System.out.println(sunset);
			System.out.println("Sunset: "+sunsetLDT);
		}
//		if(angle==SolarElevationAngle.SUN_ALTITUDE_SUNRISE_SUNSET) {
//			System.out.println(angle);
//			System.out.println("Sunrise: "+sunrise+" | "+sunriseLDT);
//			System.out.println("Sunset: "+sunset+" | "+sunsetLDT);
//		}
		
		return new LocalDateTime[] {sunriseLDT, sunsetLDT};
	}
	
	public static DayPeriod getDayPeriod(LocalDateTime date, double latitude, double longitudeWest) {
		
		LocalDateTime[] times = getTimeOfSolarElevationChange(date, SolarElevationAngle.SUN_ALTITUDE_SUNRISE_SUNSET, latitude, longitudeWest);
		if(times[0]==null || (date.isAfter(times[0]) && date.isBefore(times[1]))) {
			return DayPeriod.DAY;
		}

		times = getTimeOfSolarElevationChange(date, SolarElevationAngle.SUN_ALTITUDE_CIVIL_TWILIGHT, latitude, longitudeWest);
		if(times[0]==null || (date.isAfter(times[0]) && date.isBefore(times[1]))) {
			return DayPeriod.CIVIL_TWILIGHT;
		}

		times = getTimeOfSolarElevationChange(date, SolarElevationAngle.SUN_ALTITUDE_NAUTICAL_TWILIGHT, latitude, longitudeWest);
		if(times[0]==null || (date.isAfter(times[0]) && date.isBefore(times[1]))) {
			return DayPeriod.NAUTICAL_TWILIGHT;
		}

		times = getTimeOfSolarElevationChange(date, SolarElevationAngle.SUN_ALTITUDE_ASTRONOMICAL_TWILIGHT, latitude, longitudeWest);
		if(times[0]==null || (date.isAfter(times[0]) && date.isBefore(times[1]))) {
			return DayPeriod.ASTRONOMICAL_TWILIGHT;
		}
		
		return DayPeriod.NIGHT;
	}
	
}
