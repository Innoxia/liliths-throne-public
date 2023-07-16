package com.lilithsthrone.utils.time;

import java.time.LocalDateTime;

public enum ChineseCalender {
	ONE(0, 2, 5),
	TWO(1, 1, 24),
	THREE(2, 2, 13),
	FOUR(3, 2, 2),
	FIVE(4, 1, 23),
	SIX(5, 2, 10),
	SEVEN(6, 1, 30),
	EIGHT(7, 2, 17),
	NINE(8, 2, 6),
	TEN(9,1, 26),
	ELEVEN(10, 2, 14),
	TWELVE(11, 2, 4),
	THIRTEEN(12, 1, 24),
	FOURTEEN(13, 2, 11),
	FIFTEEN(14, 1, 31),
	SIXTEEN(15, 2, 19),
	SEVENTEEN(16, 2, 8),
	EIGHTEEN(17, 1, 27),
	NINETEEN(18, 2, 15);
	
	private final int index;
	private final int startMonth;
	private final int startDay;
	ChineseCalender(int index, int startMonth, int startDay) {
		this.index = index;
		this.startMonth = startMonth;
		this.startDay = startDay;
	}
	
	public int getIndex() {
		return index;
	}
	
	public int getStartMonth() {
		return startMonth;
	}
	
	public int getStartDay() {
		return startDay;
	}
	
	public static ChineseCalender getCycleFromIndex(int index) {
		for(ChineseCalender cycle : ChineseCalender.values()) {
			if (cycle.index == index) {
				return cycle;
			}
		}
		return null;
	}
	
	public static int getCycleFromDate(LocalDateTime dateTime) {
		int day = dateTime.getDayOfMonth();
		int month = dateTime.getMonthValue();
		// Remove 5 from the year to bring it inline with the chinese calendar https://en.wikipedia.org/wiki/Chinese_zodiac#Years
		int index = (dateTime.getYear() - 5) % 19;
		ChineseCalender cycle = getCycleFromIndex(index);
		if (cycle == null) {
			return -1;
		}
		if (month < cycle.getStartMonth() || (month == cycle.getStartMonth() && day < cycle.getStartDay())) {
			return index - 1;
		}
		return index;
	}
}
