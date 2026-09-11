package com.lilithsthrone.game.sex.recording;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.4.11.4
 * @version 0.4.11.5
 * @author Innoxia
 */
public class SexActionRecording implements XMLSaving {
	
	/** The index at which this action was performed during a complete turn in sex. */
	private int turnIndex;

	/** The ID of the character who performed this action. */
	private String performerId;

	/** The IDs of all the characters who are involved in this action. Note that this DOES include the performerId. */
	private List<String> participantIds;

	/** The String of this action's title. */
	private String actionTitle;

	/** The String of this action's description. */
	private String actionDescription;
	
	public SexActionRecording(int turnIndex, String performerId, List<String> participantIds, String actionTitle, String actionDescription) {
		this.turnIndex = turnIndex;
		this.performerId = performerId;
		this.participantIds = participantIds;
		this.actionTitle = actionTitle;
		this.actionDescription = actionDescription;
	}

	public int getTurnIndex() {
		return turnIndex;
	}

	public void setTurnIndex(int turnIndex) {
		this.turnIndex = turnIndex;
	}

	public String getPerformerId() {
		return performerId;
	}

	public void setPerformerId(String performerId) {
		this.performerId = performerId;
	}

	public List<String> getParticipantIds() {
		return participantIds;
	}

	public void setParticipantIds(List<String> participantIds) {
		this.participantIds = participantIds;
	}

	public String getActionTitle() {
		return actionTitle;
	}

	public void setActionTitle(String actionTitle) {
		this.actionTitle = actionTitle;
	}

	public String getActionDescription() {
		return actionDescription;
	}

	public void setActionDescription(String actionDescription) {
		this.actionDescription = actionDescription;
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("sexActionRecording");
		parentElement.appendChild(element);
		
		// Turn index and performer ID:
		XMLUtil.addAttribute(doc, element, "turnIndex", String.valueOf(getTurnIndex()));
		XMLUtil.addAttribute(doc, element, "performerID", String.valueOf(getPerformerId()));
		
		// Performer ID list:
		Element performerIDsElement = doc.createElement("performerIDs");
		element.appendChild(performerIDsElement);
		for(int i=0; i<getParticipantIds().size();i++) {
			Element idElement = doc.createElement("id");
			performerIDsElement.appendChild(idElement);
			XMLUtil.addAttribute(doc, idElement, "index", String.valueOf(i));
			idElement.setTextContent(getParticipantIds().get(i));
		}
		
		
		// Action String information:
		Element actionElement = doc.createElement("action");
		element.appendChild(actionElement);
		
		// Title:
		Element actionTitleElement = doc.createElement("title");
		CDATASection actionTitleCDATA = doc.createCDATASection(getActionTitle());
		actionTitleElement.appendChild(actionTitleCDATA);
		actionElement.appendChild(actionTitleElement);
//		actionTitleElement.setTextContent(getActionTitle());
		
		// Description:
		Element actionDescriptionElement = doc.createElement("description");
		CDATASection actionDescriptionCDATA = doc.createCDATASection(getActionDescription());
		actionDescriptionElement.appendChild(actionDescriptionCDATA);
		actionElement.appendChild(actionDescriptionElement);
//		actionDescriptionElement.setTextContent(getActionDescription());
		
		return element;
	}
	
	public static SexActionRecording loadFromXML(Element parentElement, Document doc) {
		try {
			// Turn index and performer ID:
			int importedTurnIndex = Integer.valueOf(parentElement.getAttribute("turnIndex"));
			String importedPerformerID = parentElement.getAttribute("performerID");

			// Performer ID list:
			Element performerIDsElement = (Element) parentElement.getElementsByTagName("performerIDs").item(0);
			NodeList IDs = performerIDsElement.getElementsByTagName("id");
			List<String> importedParticipantIDs = new ArrayList<>(IDs.getLength());
			for(int i=0; i<IDs.getLength(); i++) {
				Element idElement = ((Element)IDs.item(i));
				importedParticipantIDs.add(Integer.valueOf(idElement.getAttribute("index")), idElement.getTextContent());
			}

			// Action String information:
			Element actionElement = (Element) parentElement.getElementsByTagName("action").item(0);
			Element actionTitleElement = (Element) actionElement.getElementsByTagName("title").item(0);
			String importedActionTitle = actionTitleElement.getTextContent();
			Element actionDescriptionElement = (Element) actionElement.getElementsByTagName("description").item(0);
			String importedActionDescription = actionDescriptionElement.getTextContent();
			
			
			return new SexActionRecording(importedTurnIndex, importedPerformerID, importedParticipantIDs, importedActionTitle, importedActionDescription);
			
		} catch(Exception ex) {
			System.err.println("Warning: SexActionRecording failed to import!");
			ex.printStackTrace();
			return null;
		}
	}
}
