package com.lilithsthrone.game.slavery;

/**
 * @since 0.1.87
 * @version 0.1.87
 * @author Innoxia
 */
public enum SlaveJobHours {
	
	// TODO scrap this and use the new system instead
	
//	EVENING_SHORT("Evening shift", "", 1, 1),
//	EVENING_NORMAL("Evening shift", "", 1, 1),
//	EVENING_LONG("Evening shift", "", 1, 1),
	
	NIGHT_SHORT("Night shift (short)", "Get this slave to work a few hours during the evening.", 60*19, 60*3),
	NIGHT_NORMAL("Night shift", "Get this slave to work eight hours over the course of the night.", 60*20, 60*8),
	NIGHT_LONG("Night shift (long)", "Get this slave to work sixteen hours over the course of the night.", 60*16, 60*16),
	
//	MORNING_SHORT("Evening shift", "", 1, 1),
//	MORNING_NORMAL("Evening shift", "", 1, 1),
//	MORNING_LONG("Evening shift", "", 1, 1),

	DAY_SHORT("Evening shift", "Get this slave to work a few hours during the morning.", 60*7, 60*3),
	DAY_NORMAL("Evening shift", "Get this slave to work eight hours over the course of the day.", 60*9, 60*8),
	DAY_LONG("Evening shift", "Get this slave to work sixteen hours over the course of the day.", 60*6, 60*16);

	private String name;
	private String description;
	private int startMinute;
	private int length;
	
	private SlaveJobHours(String name, String description, int startMinute, int length) {
		this.name = name;
		this.description = description;
		this.startMinute = startMinute;
		this.length = length;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getStartMinute() {
		return startMinute;
	}

	public int getLength() {
		return length;
	}
}
