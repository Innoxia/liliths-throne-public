package com.lilithsthrone.utils.time;

import java.time.LocalDateTime;

public enum ChineseZodiac {
	RAT("rat", 0),
	OX("ox", 1),
	TIGER("tiger", 2),
	RABBIT("rabbit", 3),
	DRAGON("dragon", 4),
	SNAKE("snake", 5),
	HORSE("horse", 6),
	GOAT("goat", 7),
	MONKEY("monkey", 8),
	ROOSTER("rooster", 9),
	DOG("dog", 10),
	PIG("pig", 11);
	
	private final String name;
	private final int index;
	
	ChineseZodiac(String name, int index) {
		this.name = name;
		this.index = index;
	}
	
	public String getName() {
		return name;
	}
	
	public int getIndex() {
		return index;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public ChineseZodiac getSignFromDate(LocalDateTime dateTime) {
		// Remove 4 from the year to bring it inline with the chinese calendar https://en.wikipedia.org/wiki/Chinese_zodiac#Years
		int signIndex = (dateTime.getYear() - 4) % 12;
		if (ChineseCalender.getCycleFromDate(dateTime) != (dateTime.getYear() - 5) % 19) {
			signIndex--;
			if (signIndex < 0) {
				signIndex = 11;
			}
		}
		for(ChineseZodiac sign : ChineseZodiac.values()) {
			if (signIndex == sign.index) {
				return sign;
			}
		}
		return null; // This should be impossible...
	}
}
