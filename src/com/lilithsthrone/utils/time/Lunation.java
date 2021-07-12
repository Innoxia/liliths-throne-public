package com.lilithsthrone.utils.time;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

import com.lilithsthrone.main.Main;

/**
 * Implemented using equations found here: https://www.subsystems.us/uploads/9/8/9/4/98948044/moonphase.pdf
 * 
 * @since 0.4
 * @version 0.4.1
 * @author Amarok
 */
 public class Lunation {

	private static double synodicMonth = 29.5305888531;
	private static LocalDateTime referenceDate = LocalDateTime.of(2020, 1, 24, 21, 42, 0, 0);    // reference new moon, 2020/1/24 21:42 UTC

	private static double lunarPhase(LocalDateTime date) {	// 0 = new moon, 0.5 = full moon
		return ((DateAndTime.julianDate(date, true) - DateAndTime.julianDate(referenceDate, true)) / synodicMonth) % 1;
	}

	private static double lunarPortion(LocalDateTime date) {
		return (8*lunarPhase(date) + 0.5) % 8;		// turns the [0, 1] phase into [0, 8] portion and then shifts left by 1/16 so phases are at their maximum when equal to x.5, rather than x.0
	}

	static int lunarIllumination(LocalDateTime date) {	// returns a number between 0 and 100 describing how much of the moon's surface is illuminated
		double math = Math.pow(Math.sin(Math.PI * lunarPhase(date)), 2);
		return (int) Math.round(100 * math);
	}

	public static String lunarDescription(LocalDateTime date) {
		StringBuilder sb = new StringBuilder();
		
		if(isMaximumPhaseToday(date, MoonPhase.NEW_MOON)
				|| isMaximumPhaseToday(date, MoonPhase.FULL_MOON)) {
			sb.append("Today is the day of the " + getMoonPhaseName(date));
			
		} else if(isMaximumPhaseToday(date.plusDays(1), MoonPhase.FULL_MOON)
				&& dateOfNextPhase(date, MoonPhase.FULL_MOON, 0).get(ChronoField.MINUTE_OF_DAY) < Main.game.getSunriseTimeInMinutes()) {
			sb.append("Today is the eve of the " + getMoonPhaseName(date));
			
		} else if(isMaximumPhaseToday(date, MoonPhase.FIRST_QUARTER)
				|| isMaximumPhaseToday(date, MoonPhase.THIRD_QUARTER)) {
			sb.append("The moon is currently in " + getMoonPhaseName(date));
			
		} else if(getMoonPhase(date) == MoonPhase.NEW_MOON
				|| getMoonPhase(date) == MoonPhase.FIRST_QUARTER
				|| getMoonPhase(date) == MoonPhase.FULL_MOON
				|| getMoonPhase(date) == MoonPhase.THIRD_QUARTER) {
			sb.append("The moon is currently " + (getMoonPhaseName(date).contains("moon")?"a near ":"near ") + getMoonPhaseName(date));
			
		} else {
			sb.append("The moon is currently a " + getMoonPhaseName(date));
		}
		
		sb.append(" (" + lunarIllumination(date) + "% lit)");
		
		return sb.toString();
	}

	public enum MoonPhase {
		NEW_MOON        ("new moon", 0, 1),         // 0 is the start of the new moon, 0.5 is the new moon (illumination = 0), 1 is the end of the new moon/start of the waxing crescent
		WAXING_CRESCENT ("waxing crescent", 1, 2),
		FIRST_QUARTER   ("first quarter", 2, 3),
		WAXING_GIBBOUS  ("waxing gibbous", 3, 4),
		FULL_MOON       ("full moon", 4, 5),
		WANING_GIBBOUS  ("waning gibbous", 5, 6),
		THIRD_QUARTER   ("third quarter", 6, 7),
		WANING_CRESCENT ("waning crescent", 7, 8);

		private String name;
		private int minimumOctal;
		private int maximumOctal;

		private MoonPhase(String name, int min, int max) {
			this.name = name;
			this.minimumOctal = min;
			this.maximumOctal = max;
		}

		public String getName() {
			return name;
		}
		
		public int getMinimumValue() {
			return minimumOctal;
		}
	
		public int getMaximumValue() {
			return maximumOctal;
		}
		
		public double getMedianValue() {
			return minimumOctal + (maximumOctal - minimumOctal) / 2d;
		}
	
		public static MoonPhase valueOf(LocalDateTime date) {
			double portion = lunarPortion(date);
			
			for(MoonPhase f : MoonPhase.values()) {
				if(portion>=f.getMinimumValue() && portion<f.getMaximumValue()) {
					return f;
				}
			}
			return NEW_MOON;
		}
	}

	public static double daysToNextPhase(LocalDateTime date, MoonPhase phase, long dayOffset) {
		double portion = lunarPortion(date.minusDays(dayOffset));
		double target = phase.getMedianValue();
		double wait = target - portion + (portion<=target?0:8);
		return wait * synodicMonth / 8;
	}

	public static LocalDateTime dateOfNextPhase(LocalDateTime date, MoonPhase phase, long dayOffset) {
		double days = daysToNextPhase(date, phase, dayOffset);
		long period = Math.round(days * 86400d);
		LocalDateTime next = date.plusSeconds(period);
		return next;
	}
	
	public static boolean isMaximumPhaseToday(LocalDateTime date, MoonPhase phase) {		// ie, does the true full moon happen today?, closest day where portion = x.5
		LocalDateTime daystart = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 0, 0, 0, 0);
		LocalDateTime dayend = daystart.plusDays(1).minusNanos(1);
		
		double timestart = lunarPortion(daystart);
		double timeend = lunarPortion(dayend);
		double portion = phase.getMedianValue();
		
		return timestart<=portion && portion<=timeend;
	}

	public static MoonPhase getMoonPhase(LocalDateTime date) {
		return MoonPhase.valueOf(date);
	}

	public static String getMoonPhaseName(LocalDateTime date) {
		return MoonPhase.valueOf(date).getName();
	}

	public boolean isSocialFullMoon(LocalDateTime date) {
		if(isMaximumPhaseToday(date.plusDays(1), MoonPhase.FULL_MOON)	// if full moon is this evening, but actually happens tomorrow morning
				&& dateOfNextPhase(date, MoonPhase.FULL_MOON, 0).get(ChronoField.MINUTE_OF_DAY) < Main.game.getSunriseTimeInMinutes()) {
			return true;
		} else if(isMaximumPhaseToday(date, MoonPhase.FULL_MOON)		// if the full moon is this evening, and happens today
				&& dateOfNextPhase(date, MoonPhase.FULL_MOON, 1).get(ChronoField.MINUTE_OF_DAY) >= Main.game.getSunsetTimeInMinutes()) {
			return true;
		}
		return false;
	}

}