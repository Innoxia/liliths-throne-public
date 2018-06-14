package com.lilithsthrone.game.dialogue.eventLog;

import java.io.Serializable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.85
 * @version 0.1.89
 * @author Innoxia
 */
public class EventLogEntry implements Serializable, XMLSaving {

	private static final long serialVersionUID = 1L;
	
	protected long time;
	protected String name, description;
	
	public EventLogEntry(long time, String name, String description) {
		this.time = time;
		this.name = name;
		this.description = description;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("eventLogEntry");
		parentElement.appendChild(element);
		
		CharacterUtils.addAttribute(doc, element, "time", String.valueOf(time));
		CharacterUtils.addAttribute(doc, element, "name", name);
		CharacterUtils.addAttribute(doc, element, "description", description);
		
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
