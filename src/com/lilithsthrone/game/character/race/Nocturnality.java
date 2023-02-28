package com.lilithsthrone.game.character.race;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.utils.time.DayPeriod;

/**
 * @since 0.4.7.1
 * @version 0.4.7.1
 * @author Innoxia
 */
public enum Nocturnality {

	DIURNAL("diurnal", "Active during the day and rests during the night.", true, false),
	NOCTURNAL("nocturnal", "Active during the night and rests during the day.", false, true),

	// All of the following are tagged as being active both at night and during the day:
	
	CREPUSCULAR("crepuscular", "Active during both the morning and evening twilight periods.", true, true), // Although crepuscular can refer to either matutinal, vespertine, or both, this term is used in LT to refer solely to meaning both
	MATUTINAL("matutinal", "Active during the morning twilight period.", true, true),
	VESPERTINE("vespertine", "Active during the evening twilight period.", true, true),
	
	CATHEMERAL("cathemeral", "Active for irregular periods during both the day and night.", true, true);
	
	private String name;
	private String description;
	
	private boolean activeAtDay;
	private boolean activeAtNight;

	private Nocturnality(String name, String description, boolean activeAtDay, boolean activeAtNight) {
		this.name = name;
		this.description = description;
		this.activeAtDay = activeAtDay;
		this.activeAtNight = activeAtNight;
	}

	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	
	public boolean isActiveAtDay() {
		return activeAtDay;
	}

	public boolean isActiveAtNight() {
		return activeAtNight;
	}
	
	/**
	 * @return A List of DayPeriods in which a character of this Nocturnality will want to sleep.
	 * <br/>Will be all DayPeriods if the Nocturnality is active at both day and night.
	 * <br/>Otherwise, will contain DayPeriod.DAY and DayPeriod.CIVIL_TWILIGHT if the Nocturnality is inactive during the day, or DayPeriod.NIGHT, DayPeriod.ASTRONOMICAL_TWILIGHT, and DayPeriod.NAUTICAL_TWILIGHT if inactive during the night.
	 */
	public List<DayPeriod> getSleepPeriods() {
		List<DayPeriod> sleepyTimes = new ArrayList<>();
		if(this.isActiveAtDay() && this.isActiveAtNight()) {
			Collections.addAll(sleepyTimes, DayPeriod.values());
		} else if(!this.isActiveAtDay()) {
			sleepyTimes.add(DayPeriod.DAY);
			sleepyTimes.add(DayPeriod.CIVIL_TWILIGHT);
		} else if(!this.isActiveAtNight()) {
			sleepyTimes.add(DayPeriod.NIGHT);
			sleepyTimes.add(DayPeriod.ASTRONOMICAL_TWILIGHT);
			sleepyTimes.add(DayPeriod.NAUTICAL_TWILIGHT);
		}
		return sleepyTimes;
	}
}
