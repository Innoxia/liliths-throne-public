package com.lilithsthrone.game.occupantManagement.slave;

/**
 * @since 0.1.87
 * @version 0.1.87
 * @author Innoxia
 */
public enum SlaveJobHours {

	NONE("None", "Do not assign any hours to this character.", 0, 0),
	
	DAY_NORMAL("Day shift", "Get this character to work eight hours over the course of the day.", 9, 8),
	DAY_LONG("Day shift +", "Get this character to work sixteen hours over the course of the day.", 6, 16),
	
	NIGHT_NORMAL("Night shift", "Get this character to work eight hours over the course of the night.", 20, 8),
	NIGHT_LONG("Night shift +", "Get this character to work sixteen hours over the course of the night.", 16, 16),

	TWENTY_FOUR_HOURS("24 hours", "Assign every hour as a work hour.", 0, 24),
	
	MIDNIGHT_SIX("Midnight shift", "Get this character to work six hours after midnight.", 2, 6),
	MORNING_SIX("Morning shift", "Get this character to work six hours in the morning.", 8, 6),

	DAY_TWELVE("All day", "Get this character to work twelve hours over the course of the day.", 8, 12),
	NIGHT_TWELVE("Overnight", "Get this character to work twelve hours over the course of the night.", 20, 12),

	NOON_SIX("Afternoon shift", "Get this character to work six hours in the afternoon.", 14, 6),
	EVENING_SIX("Evening shift", "Get this character to work six hours in the evening.", 20, 6);
	

	private String name;
	private String description;
	private int startHour;
	private int length;
	
	private SlaveJobHours(String name, String description, int startHour, int length) {
		this.name = name;
		this.description = description;
		this.startHour = startHour;
		this.length = length;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getStartHour() {
		return startHour;
	}

	public int getLength() {
		return length;
	}
}
