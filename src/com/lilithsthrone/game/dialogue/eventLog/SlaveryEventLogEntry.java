package com.lilithsthrone.game.dialogue.eventLog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.SlaveEvent;
import com.lilithsthrone.game.occupantManagement.SlaveEventTag;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.87
 * @version 0.2.2
 * @author Innoxia
 */
public class SlaveryEventLogEntry implements Serializable, XMLSaving {

	private static final long serialVersionUID = 1L;

	protected long time;
	private String slaveID;
	private SlaveEvent event;
	private List<SlaveEventTag> tags;
	private List<String> extraEffects;
	
	private static StringBuilder descriptionSB = new StringBuilder();
	
	public SlaveryEventLogEntry(long time, NPC slave, SlaveEvent event, boolean applyEffectsToSlave) {
		this(time, slave, event, null, null, applyEffectsToSlave);
	}
	
	public SlaveryEventLogEntry(long time, NPC slave, SlaveEvent event, List<SlaveEventTag> tags, boolean applyEffectsToSlave) {
		this(time, slave, event, tags, null, applyEffectsToSlave);
	}

	public SlaveryEventLogEntry(long time, String slaveID, SlaveEvent event, List<SlaveEventTag> tags, List<String> extraEffects) {
		this.time = time;
		this.slaveID = slaveID;
		this.event = event;
		this.tags = tags;
		this.extraEffects = extraEffects;
	}
	
	public SlaveryEventLogEntry(long time, NPC slave, SlaveEvent event, List<SlaveEventTag> tags, List<String> extraEffects, boolean applyEffectsToSlave) {
		this.time = time;
		
		slaveID = slave.getId();
		
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
		
		CharacterUtils.addAttribute(doc, element, "time", String.valueOf(time));
		CharacterUtils.addAttribute(doc, element, "slaveID", slaveID);
		CharacterUtils.addAttribute(doc, element, "event", event.toString());

		if(tags!=null) {
			Element tagsNode = doc.createElement("tags");
			element.appendChild(tagsNode);
			for(SlaveEventTag tag : tags) {
				Element tagDataNode = doc.createElement("tag");
				tagsNode.appendChild(tagDataNode);
				CharacterUtils.addAttribute(doc, tagDataNode, "value", String.valueOf(tag));
			}
		}

		if(extraEffects!=null) {
			Element extraEffectsNode = doc.createElement("extraEffects");
			element.appendChild(extraEffectsNode);
			for(String s : extraEffects) {
				Element extraEffectsDataNode = doc.createElement("entry");
				extraEffectsNode.appendChild(extraEffectsDataNode);
				CharacterUtils.addAttribute(doc, extraEffectsDataNode, "value", s);
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
		
		return new SlaveryEventLogEntry(
				Long.valueOf(parentElement.getAttribute("time")),
				parentElement.getAttribute("slaveID"),
				SlaveEvent.valueOf(parentElement.getAttribute("event")),
				loadedTags,
				loadedExtraEffects);
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
		descriptionSB.setLength(0);
		
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
		
		GameCharacter slave = Main.game.getNPCById(slaveID);
		if(slave!=null) {
			return UtilText.parse(Main.game.getNPCById(slaveID), descriptionSB.toString());
		} else {
			return descriptionSB.toString();
		}
	}
	
	public String getSlaveName() {
		GameCharacter slave = Main.game.getNPCById(slaveID);
		if(slave==null) {
			return "<b>Slave</b>";
		} else {
			return "<b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>"+slave.getName()+"</b>";
		}
	}
	
	public String getSlaveID() {
		return slaveID;
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
}
