package com.lilithsthrone.game.dialogue.eventLog;

import java.io.Serializable;

/**
 * @since 0.1.85
 * @version 0.1.85
 * @author Innoxia
 */
public class EventLogEntry implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected long time;
	protected String name, description;
	
	public EventLogEntry(long time, String name, String description) {
		this.time = time;
		this.name = name;
		this.description = description;
	}
	
	public String getFormattedEntry() {
		return name+": "+description;
	}
	
	/**
	 * @return A formatted paragraph suitable for appending to the end of the main dialogue.
	 */
	public String getMainDialogueDescription() {
		return "<p style='text-align:center;'>"
					+ "<b>"+name+"</b>"
					+ "</br>"
					+ description
				+ "</p>";
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
