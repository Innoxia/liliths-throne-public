package com.lilithsthrone.game.dialogue.eventLog;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.85
 * @version 0.3.4
 * @author Innoxia
 */
public class EventLogEntry implements XMLSaving {
	
	protected String name;
	protected String description;

	public EventLogEntry(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("eventLogEntry");
		parentElement.appendChild(element);
		
		XMLUtil.addAttribute(doc, element, "name", name);
		XMLUtil.addAttribute(doc, element, "description", description);
		
		return element;
	}
	
	public static EventLogEntry loadFromXML(Element parentElement, Document doc) {
		return new EventLogEntry(
				parentElement.getAttribute("name"),
				parentElement.getAttribute("description"));
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
