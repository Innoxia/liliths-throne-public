package com.lilithsthrone.game.dialogue.eventLog;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.85
 * @version 0.3.4
 * @author Innoxia
 */
public class EventLogEntry implements XMLSaving {
	
	protected long time;
	protected String name;
	protected String description;

	public EventLogEntry(String name, String description) {
		this(Main.game.getSecondsPassed(), name, description);
	}
	
	public EventLogEntry(long time, String name, String description) {
		this.time = time;
		this.name = name;
		this.description = description;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("eventLogEntry");
		parentElement.appendChild(element);
		
		XMLUtil.addAttribute(doc, element, "time", String.valueOf(time));
		XMLUtil.addAttribute(doc, element, "name", name);
		XMLUtil.addAttribute(doc, element, "description", description);
		
		return element;
	}
	
	public static EventLogEntry loadFromXML(Element parentElement, Document doc) {
		EventLogEntry newFlags = new EventLogEntry(
				Long.valueOf(parentElement.getAttribute("time")),
				parentElement.getAttribute("name"),
				parentElement.getAttribute("description"));
		
		return newFlags;
	}
	
	public String getFormattedEntry() {
		if(name == null || name.isEmpty()) {
			return description;
		}
		return name+": "+description;
	}
	
	/**
	 * @return A formatted paragraph suitable for appending to the end of the main dialogue.
	 */
	public String getMainDialogueDescription() {
		return "<p style='text-align:center;'>"
					+ "<b>"+name+"</b>"
					+ "<br/>"
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
