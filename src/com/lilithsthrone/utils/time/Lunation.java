package com.lilithsthrone.utils.time;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;

import com.lilithsthrone.main.Main;

/**
 * Implemented using equations found here: https://www.subsystems.us/uploads/9/8/9/4/98948044/moonphase.pdf
 * 
 * @since 0.4
 * @version 0.4
 * @author Amarok
 */

 public class Lunation {

	private static double synodicMonth = 29.530589;
	private static int socialFullMoon = 1;	// how many days before and after the true full moon are socially considered 'full moons', set to 0 to turn off
	private static LocalDateTime referenceDate = LocalDateTime.of(2000, 1, 6, 18, 3, 0, 0);    // reference new moon, 2000/6/1 18:03 UTC

	private static double lunarPhase(LocalDateTime date) {	// 0 = new moon, 0.5 = full moon
		return (DateAndTime.julianDate(date) - DateAndTime.julianDate(referenceDate)) % synodicMonth;
	}
	
	private static double lunarPortion(LocalDateTime date) {
		return (8*lunarPhase(date) + 0.5) % 8;		// turns the [0, 1] phase into [0, 8] phase and then shifts left by 1/16 so phases are at their maximum when equal to x.5, rather than x.0
	}
	
	static double lunarIllumination(LocalDateTime date) {	// returns a number between 0 and 100 describing how much of the moon's surface is illuminated
		return 100 * Math.pow(Math.sin(Math.PI * lunarPhase(date)), 2);
	}
	
	public static String lunarDescription() {
		StringBuilder sb = new StringBuilder();
		
		LocalDateTime date = Main.game.getDateNow();
		
		if(isMaximumPhaseToday(date, MoonPhase.NEW_MOON)
				|| isMaximumPhaseToday(date, MoonPhase.FIRST_QUARTER)
				|| isMaximumPhaseToday(date, MoonPhase.FULL_MOON)
				|| isMaximumPhaseToday(date, MoonPhase.THIRD_QUARTER)) {
			sb.append("Today is the day of the " + getMoonPhaseName(date) + ".");
		} else {
			sb.append("The current lunar phase is a " + getMoonPhaseName(date) + ".");
		}
		
		sb.append("<br>The moon is " + lunarIllumination(date) + "% illuminated");
		
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
		
		public int getMedianValue() {
			return minimumOctal + (maximumOctal - minimumOctal) / 2;
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

	public static double daysToNextPhase(LocalDateTime date, MoonPhase phase, double dayOffset) {
		double portion = lunarPortion(date);
		double target = phase.getMedianValue();
		double wait = target - portion + (portion<=target?0:8);
		
		return wait * synodicMonth / 8;
	}

	public static LocalDateTime dateOfNextPhase(LocalDateTime date, MoonPhase phase, double dayOffset) {
		long period = (long) daysToNextPhase(date, phase, dayOffset);
		return date.plusSeconds(period * 24 * 3600);
	}
	
	public static boolean isMaximumPhaseToday(LocalDateTime date, MoonPhase phase) {		// ie, does the true full moon happen today?, closest day where portion = x.5
		LocalDateTime daystart = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 0, 0, 0, 0);
		LocalDateTime dayend = daystart.plusDays(1).minusNanos(1);
		
		double timestart = (8*lunarPhase(daystart) + 0.5) % 8;
		double timeend = (8*lunarPhase(dayend) + 0.5) % 8;
		double portion = phase.getMedianValue();
		
		return timestart>=portion && portion<=timeend;
	}

	public static String getMoonPhaseName(LocalDateTime date) {
		return MoonPhase.valueOf(date).getName();
	}
	
	public boolean isSocialFullMoon(LocalDateTime date) {
		for(int i = -socialFullMoon; i <= socialFullMoon; i++) {
			if(isMaximumPhaseToday(date.plusDays(i), MoonPhase.FULL_MOON)) {
				return true;
			}
		}
		return false;
	}

}