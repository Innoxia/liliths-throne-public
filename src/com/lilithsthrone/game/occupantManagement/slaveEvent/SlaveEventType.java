package com.lilithsthrone.game.occupantManagement.slaveEvent;

/**
 * @since 0.3.9.2
 * @version 0.3.9.2
 * @author Innoxia
 */
public enum SlaveEventType {
	
	BONDING("Bonding", "Events in which two slaves spend some time either getting to know one another or arguing about something."),
	
	JOB("Job", "Events related to the slave's job. This includes sex events if the slave's job involves sex."),
	
	SEX("Sex", "Events in which the slave does something sexual or experiences an effect of having sex (such as giving birth)."),
	
	MISCELLANEOUS("Misc.", "Miscellaneous, mundane events, such as slaves washing themselves.");
	
	private String name;
	private String description;
	
	private SlaveEventType(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}
