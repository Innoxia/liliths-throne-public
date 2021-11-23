package com.lilithsthrone.game.character;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.lilithsthrone.utils.XMLSaving;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public final class Family implements XMLSaving {
	private final Map<Integer, Litter> litterMap = new TreeMap<>();
	private final AtomicInteger nextLitterId = new AtomicInteger();
	
	public Integer getId() {
		return nextLitterId.get();
	}

	public List<Litter> getLittersFathered(GameCharacter character) {
		return litterMap.values().stream().filter(litter -> character.equals(litter.getFather())).collect(Collectors.toList());
	}
	
	public List<Litter> getLittersMothered(GameCharacter character) {
		return litterMap.values().stream().filter(litter -> character.equals(litter.getMother())).collect(Collectors.toList());
	}
	
	public List<Litter> getLittersIncubated(GameCharacter character) {
		return litterMap.values().stream().filter(litter -> character.equals(litter.getIncubator())).collect(Collectors.toList());
	}
	
	public List<Litter> getLittersBirthed(GameCharacter character) {
		return litterMap.values().stream().filter(litter -> character.equals(litter.getMother()) && litter.getIncubatorId().isEmpty()).collect(Collectors.toList());
	}
	
	public List<Litter> getLittersImplanted(GameCharacter character) {
		return litterMap.values().stream().filter(litter -> character.equals(litter.getMother()) && !litter.getIncubatorId().isEmpty()).collect(Collectors.toList());
	}
	
	public Litter getLastLitterBirthed(GameCharacter character) {
		List<Litter> filteredLitterMap = getLittersBirthed(character);
		return filteredLitterMap.get(filteredLitterMap.size()-1);
	}
	
	public Litter getLastLitterIncubated(GameCharacter character) {
		List<Litter> filteredLitterMap = getLittersIncubated(character);
		return filteredLitterMap.get(filteredLitterMap.size()-1);
	}
	
	public Integer addLitter(Litter litter) {
		if(litterMap.containsValue(litter)) {
			for (Map.Entry<Integer, Litter> entry : litterMap.entrySet()) {
				if(entry.getValue().equals(litter)) {
					return entry.getKey();
				}
			}
		}
		Integer id = nextLitterId.incrementAndGet();
		litterMap.put(id, litter);
		return id;
	}
	
	public Litter addLitter(Litter litter, Integer id) {
		if(id > nextLitterId.get()) {
			nextLitterId.set(id);
		}
		return litterMap.put(id, litter);
	}
	
	public Litter getLitter(Integer id) {
		if(id!=null) {
			return litterMap.getOrDefault(id, null);
		}
		return null;
	}
	
	public void swapLitter(Integer key, String oldId, String newId) {
		if(key !=null && getLitter(key).getOffspring().contains(oldId)) {
			getLitter(key).getOffspring().remove(oldId);
			getLitter(key).getOffspring().add(newId);
		}
	}
	
	public void swapLitters(String oldId, String newId) {
		for(Map.Entry<Integer, Litter> entry : litterMap.entrySet()) {
			swapLitter(entry.getKey(), oldId, newId);
		}
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		parentElement.setAttribute("atomicId", nextLitterId.toString());
		for(Map.Entry<Integer, Litter> entry : litterMap.entrySet()) {
			Element litterElement = entry.getValue().saveAsXML(parentElement, doc);
			litterElement.setAttribute("key", entry.getKey().toString());
		}
		return parentElement;
	}
	
	public Element loadFromXML(Element parentElement, Document doc) {
		if(!parentElement.getAttribute("atomicId").isEmpty()) {
			nextLitterId.set(Integer.parseInt(parentElement.getAttribute("atomicId")));
		}
		NodeList litters = parentElement.getElementsByTagName("litter");
		for(int i = 0; i < litters.getLength(); i++) {
			if(!((Element) litters.item(i)).getAttribute("key").isEmpty()) {
				addLitter(Litter.loadFromXML((Element) litters.item(i), doc), Integer.valueOf(((Element) litters.item(i)).getAttribute("key")));
			}
		}
		return parentElement;
	}
}
