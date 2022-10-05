package com.lilithsthrone.game.dialogue.eventLog;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.slaveEvent.SlaveEvent;
import com.lilithsthrone.game.occupantManagement.slaveEvent.SlaveEventTag;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.87
 * @version 0.3.9.2
 * @author Innoxia
 */
public class SlaveryEventLogEntry implements XMLSaving {

	protected long time;
	private boolean slaveDeleted;
	private String slaveID;
	private List<String> involvedSlaveIDs;
	private SlaveEvent event;
	private List<SlaveEventTag> tags;
	private List<String> extraEffects;
	
	// For use when the slave has been deleted:
	private String parsedName;
	private String parsedDescription;
	
//	private String parsedTagText;
//	private String parsedEffectsText;
	
	public SlaveryEventLogEntry(long time, NPC slave, List<String> involvedSlaveIDs, SlaveEvent event, boolean applyEffectsToSlave) {
		this(time, slave, involvedSlaveIDs, event, null, null, applyEffectsToSlave);
	}
	
	public SlaveryEventLogEntry(long time, NPC slave, List<String> involvedSlaveIDs, SlaveEvent event, List<SlaveEventTag> tags, boolean applyEffectsToSlave) {
		this(time, slave, involvedSlaveIDs, event, tags, null, applyEffectsToSlave);
	}

	public SlaveryEventLogEntry(long time, String slaveID, List<String> involvedSlaveIDs, SlaveEvent event, List<SlaveEventTag> tags, List<String> extraEffects) {
		this.time = time;
		this.slaveDeleted = false;
		this.slaveID = slaveID;
		if(involvedSlaveIDs==null) {
			this.involvedSlaveIDs = new ArrayList<>();
		} else {
			this.involvedSlaveIDs = new ArrayList<>(involvedSlaveIDs);
		}
		this.event = event;
		this.tags = tags;
		this.extraEffects = extraEffects;
	}
	
	public SlaveryEventLogEntry(long time, NPC slave, List<String> involvedSlaveIDs, SlaveEvent event, List<SlaveEventTag> tags, List<String> extraEffects, boolean applyEffectsToSlave) {
		this.time = time;

		this.slaveDeleted = false;
		this.slaveID = slave.getId();
		
		if(involvedSlaveIDs==null) {
			this.involvedSlaveIDs = new ArrayList<>();
		} else {
			this.involvedSlaveIDs = new ArrayList<>(involvedSlaveIDs);
		}
		
		this.event = event;
		
		if(applyEffectsToSlave) {
			event.applyEffects(slave);
		}
		
		this.tags = tags;
		if(tags!=null && applyEffectsToSlave) {
			for(SlaveEventTag tag : tags) {
				tag.applyEffects(slave);
			}
		}
		
		this.extraEffects = extraEffects;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("eventLogEntry");
		parentElement.appendChild(element);
		
		XMLUtil.addAttribute(doc, element, "time", String.valueOf(time));
		XMLUtil.addAttribute(doc, element, "slaveID", slaveID);
		XMLUtil.addAttribute(doc, element, "event", event.toString());
		if(slaveDeleted) {
			XMLUtil.addAttribute(doc, element, "slaveDeleted", String.valueOf(slaveDeleted));
			
			Element deletedInfoNode = doc.createElement("deletedInfo");
			element.appendChild(deletedInfoNode);
			Element node = doc.createElement("parsedName");
			deletedInfoNode.appendChild(node);
			node.setTextContent(parsedName);
			node = doc.createElement("parsedDescription");
			deletedInfoNode.appendChild(node);
			node.setTextContent(parsedDescription);
		}
		
		if(this.involvedSlaveIDs!=null && !this.involvedSlaveIDs.isEmpty()) {
			Element slavesNode = doc.createElement("involvedSlaves");
			element.appendChild(slavesNode);
			for(String id : involvedSlaveIDs) {
				Element idNode = doc.createElement("id");
				slavesNode.appendChild(idNode);
				idNode.setTextContent(id);
			}
		}
		
		if(tags!=null) {
			Element tagsNode = doc.createElement("tags");
			element.appendChild(tagsNode);
			for(SlaveEventTag tag : tags) {
				Element tagDataNode = doc.createElement("tag");
				tagsNode.appendChild(tagDataNode);
				XMLUtil.addAttribute(doc, tagDataNode, "value", String.valueOf(tag));
			}
		}

		if(extraEffects!=null) {
			Element extraEffectsNode = doc.createElement("extraEffects");
			element.appendChild(extraEffectsNode);
			for(String s : extraEffects) {
				Element extraEffectsDataNode = doc.createElement("entry");
				extraEffectsNode.appendChild(extraEffectsDataNode);
				XMLUtil.addAttribute(doc, extraEffectsDataNode, "value", s);
			}
		}
		
		return element;
	}
	
	public static SlaveryEventLogEntry loadFromXML(Element parentElement, Document doc) {
		
		List<SlaveEventTag> loadedTags = null;
		NodeList nodes = parentElement.getElementsByTagName("tags");
		Element tagNode = (Element) nodes.item(0);
		if(tagNode != null) {
			loadedTags = new ArrayList<>();
			NodeList tagElements = tagNode.getElementsByTagName("tag");
			for(int i=0; i<tagElements.getLength(); i++){
				Element e = ((Element)tagElements.item(i));

				try {
					String value = e.getAttribute("value");
					if(value.equals("JOB_LILAYA_MILK_SOLD")) {
						value = "JOB_MILK_SOLD";
					} else if(value.equals("JOB_LILAYA_CUM_SOLD")) {
						value = "JOB_CUM_SOLD";
					}
					loadedTags.add(SlaveEventTag.valueOf(value));
				}catch(IllegalArgumentException ex){
				}
			}
		}
		
		
		List<String> loadedSlaveIDs = null;
		nodes = parentElement.getElementsByTagName("involvedSlaves");
		Element slaveIdsNode = (Element) nodes.item(0);
		if(slaveIdsNode != null) {
			loadedSlaveIDs = new ArrayList<>();
			NodeList idElements = slaveIdsNode.getElementsByTagName("id");
			for(int i=0; i<idElements.getLength(); i++){
				Element e = ((Element)idElements.item(i));
				try {
					loadedSlaveIDs.add(e.getTextContent());
				}catch(IllegalArgumentException ex){
				}
			}
		}
		
		
		List<String> loadedExtraEffects = null;
		nodes = parentElement.getElementsByTagName("extraEffects");
		Element extraEffectNode = (Element) nodes.item(0);
		if(extraEffectNode != null) {
			NodeList extraEffectElements = extraEffectNode.getElementsByTagName("entry");
			loadedExtraEffects = new ArrayList<>(extraEffectElements.getLength());
			
			for(int i=0; i<extraEffectElements.getLength(); i++){
				Element e = ((Element)extraEffectElements.item(i));
				
				try {
					loadedExtraEffects.add(e.getAttribute("value"));
				}catch(IllegalArgumentException ex){
				}
			}
		}

		SlaveryEventLogEntry entry = new SlaveryEventLogEntry(
				Long.valueOf(parentElement.getAttribute("time")),
				parentElement.getAttribute("slaveID"),
				loadedSlaveIDs,
				SlaveEvent.valueOf(parentElement.getAttribute("event")),
				loadedTags,
				loadedExtraEffects);
		
		boolean loadedSlaveDeleted = false;
		if(parentElement.hasAttribute("slaveDeleted")) {
			loadedSlaveDeleted = Boolean.valueOf(parentElement.getAttribute("slaveDeleted"));

			nodes = parentElement.getElementsByTagName("deletedInfo");
			Element deletedInfoNode = (Element) nodes.item(0);
			if(deletedInfoNode != null) {
				entry.parsedName = deletedInfoNode.getElementsByTagName("parsedName").item(0).getTextContent();
				entry.parsedDescription = deletedInfoNode.getElementsByTagName("parsedDescription").item(0).getTextContent();
			}
		}
		
		entry.slaveDeleted = loadedSlaveDeleted;
		
		return entry;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getName() {
		return event.getName();
	}
	
	public String getDescription() {
		if(slaveDeleted) {
			return parsedDescription;
		}
		StringBuilder descriptionSB = new StringBuilder();
		
		descriptionSB.append(event.getDescription());
		
		if(tags!=null) {
			for(SlaveEventTag tag : tags) {
				if(descriptionSB.length()>0) {
					descriptionSB.append("<br/>");
				}
				descriptionSB.append(tag.getDescription());
			}
		}
		
		if(extraEffects!=null) {
			for(String s : extraEffects) {
				if(descriptionSB.length()>0) {
					descriptionSB.append("<br/>");
				}
				descriptionSB.append(s);
			}
		}
		
		try {
			GameCharacter slave = Main.game.getNPCById(slaveID);
			return UtilText.parse(slave, descriptionSB.toString());
			
		} catch (Exception e) {
			Util.logGetNpcByIdError("SlaveryEventLogEntry.getDescription()", slaveID);
			return descriptionSB.toString();
		}
	}
	
	public String getSlaveName() {
		if(slaveDeleted) {
			return parsedName;
		}
		try {
			GameCharacter slave = Main.game.getNPCById(slaveID);
			return "<b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>"+slave.getName(true)+"</b>";
		
		} catch (Exception e) {
			Util.logGetNpcByIdError("SlaveryEventLogEntry.getSlaveName()", slaveID);
			return "<b>Slave</b>";
		}
	}
	
	public String getSlaveID() {
		return slaveID;
	}
	
	/**
	 * @return A list of IDs of other slaves which were involved in this event.
	 */
	public List<String> getInvolvedSlaveIDs() {
		return involvedSlaveIDs;
	}

	public SlaveEvent getEvent() {
		return event;
	}

	public List<SlaveEventTag> getTags() {
		return tags;
	}
	
	public boolean addTag(SlaveEventTag tag, GameCharacter slave, boolean withEffects) {
		if(tags==null) {
			tags = new ArrayList<>();
		}
		
		if(tags.add(tag)) {
			if(withEffects) {
				tag.applyEffects(slave);
			}
			return true;
			
		} else {
			return false;
		}
	}

	public List<String> getExtraEffects() {
		return extraEffects;
	}
	
	public void addExtraEffect(String extraEffect) {
		if(extraEffects==null) {
			extraEffects = new ArrayList<>();
		}
		
		extraEffects.add(extraEffect);
	}
	
	public void applySlaveDeleted() {
		this.parsedName = this.getSlaveName();
		this.parsedDescription = this.getDescription();
		
//		try {
//			GameCharacter slave = Main.game.getNPCById(slaveID);
//
//			StringBuilder descriptionSB = new StringBuilder();
//			this.parsedTagText = "";
//			if(tags!=null) {
//				for(SlaveEventTag tag : tags) {
//					if(descriptionSB.length()>0) {
//						descriptionSB.append("<br/>");
//					}
//					descriptionSB.append(tag.getDescription());
//				}
//			}
//			this.parsedTagText = UtilText.parse(slave, descriptionSB.toString());
//			
//			descriptionSB.setLength(0);
//			this.parsedEffectsText = "";
//			if(extraEffects!=null) {
//				for(String s : extraEffects) {
//					if(descriptionSB.length()>0) {
//						descriptionSB.append("<br/>");
//					}
//					descriptionSB.append(s);
//				}
//			}
//			this.parsedEffectsText = UtilText.parse(slave, descriptionSB.toString());
//			
//		} catch (Exception e) {
//			Util.logGetNpcByIdError("SlaveryEventLogEntry.applySlaveDeleted()", slaveID);
//		}
		
		slaveDeleted = true;
	}
}
