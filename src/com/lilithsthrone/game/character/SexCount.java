package com.lilithsthrone.game.character;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.2.9
 * @version 0.3.7.6
 * @author Innoxia
 */
public class SexCount implements XMLSaving {
	
	private Map<SexType, Integer> sexCount;
	private Map<SexType, Integer> cumCount;
	
	private int sexConsensualCount;
	private int sexAsSubCount;
	private int sexAsDomCount;
	
	public SexCount() {
		sexCount =  new HashMap<>();
		cumCount =  new HashMap<>();
		sexConsensualCount = 0;
		sexAsSubCount = 0;
		sexAsDomCount = 0;
	}

	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("sexCount");
		parentElement.appendChild(element);
		
		CharacterUtils.addAttribute(doc, element, "consensual", String.valueOf(this.sexConsensualCount));
		CharacterUtils.addAttribute(doc, element, "asSub", String.valueOf(this.sexAsSubCount));
		CharacterUtils.addAttribute(doc, element, "asDom",String.valueOf(this.sexAsDomCount));
		
		Map<SexType, Element> typesAssigned = new HashMap<>();
		
		for(Entry<SexType, Integer> entry : sexCount.entrySet()) {
			Element e = entry.getKey().saveAsXML(element, doc);
			e.setAttribute("sex", String.valueOf(entry.getValue()));
			typesAssigned.put(entry.getKey(), e);
		}

		for(Entry<SexType, Integer> entry : cumCount.entrySet()) {
			if(typesAssigned.containsKey(entry.getKey())) {
				typesAssigned.get(entry.getKey()).setAttribute("cum", String.valueOf(entry.getValue()));
			} else {
				Element e = entry.getKey().saveAsXML(element, doc);
				e.setAttribute("cum", String.valueOf(entry.getValue()));
			}
		}
		
		return element;
	}

	public static SexCount loadFromXML(StringBuilder log, Element parentElement, Document doc) {
		SexCount count = new SexCount();
		try {
			count.setSexConsensualCount(Integer.valueOf(parentElement.getAttribute("consensual")));
			count.setSexAsSubCount(Integer.valueOf(parentElement.getAttribute("asSub")));
			count.setSexAsDomCount(Integer.valueOf(parentElement.getAttribute("asDom")));
			
			NodeList nodes = parentElement.getElementsByTagName("sexType");
			for(int i=0; i<nodes.getLength(); i++){
				Element entryElement = (Element)nodes.item(i);
				SexType st = SexType.loadFromXML(entryElement, doc);
				String sexCount = entryElement.getAttribute("sex");
				if(!sexCount.isEmpty()) {
					count.incrementSexCount(st, Integer.valueOf(sexCount));
				}
				String cumCount = entryElement.getAttribute("cum");
				if(!cumCount.isEmpty()) {
					count.incrementCumCount(st, Integer.valueOf(cumCount));
				}
			}
		} catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
		return count;
	}

	
	public Map<SexType, Integer> getSexCountMap() {
		return sexCount;
	}
	
	public int getSexCount(SexType sexType) {
		if(sexCount.containsKey(sexType)) {
			return sexCount.get(sexType);
		}
		return 0;
	}
	
	public void setSexCount(SexType sexType, int count) {
		sexCount.put(sexType, count);
	}
	
	public void incrementSexCount(SexType sexType, int increment) {
		sexCount.putIfAbsent(sexType, 0);
		sexCount.put(sexType, sexCount.get(sexType)+increment);
	}

	public Map<SexType, Integer> getCumCountMap() {
		return cumCount;
	}
	
	public int getCumCount(SexType sexType) {
		if(cumCount.containsKey(sexType)) {
			return cumCount.get(sexType);
		}
		return 0;
	}
	
	public void setCumCount(SexType sexType, int count) {
		cumCount.put(sexType, count);
	}
	
	public void incrementCumCount(SexType sexType, int increment) {
		cumCount.putIfAbsent(sexType, 0);
		cumCount.put(sexType, cumCount.get(sexType)+increment);
	}
	
	public int getSexConsensualCount() {
		return sexConsensualCount;
	}
	
	public void setSexConsensualCount(int sexConsensualCount) {
		this.sexConsensualCount = sexConsensualCount;
	}
	
	public int getSexAsSubCount() {
		return sexAsSubCount;
	}
	
	public void setSexAsSubCount(int sexAsSubCount) {
		this.sexAsSubCount = sexAsSubCount;
	}
	
	public int getSexAsDomCount() {
		return sexAsDomCount;
	}
	
	public void setSexAsDomCount(int sexAsDomCount) {
		this.sexAsDomCount = sexAsDomCount;
	}
	
	public int getTotalTimesHadSex() {
		return getSexAsDomCount() + getSexAsSubCount();
	}
}
