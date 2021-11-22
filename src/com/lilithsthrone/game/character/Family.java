package com.lilithsthrone.game.character;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public final class Family {
	public static final Map<Integer, Litter> litterMap = new TreeMap<>();
	private static final AtomicInteger nextLitterId = new AtomicInteger();
	
	public static Integer getId() {
		return nextLitterId.get();
	}

	public static List<Litter> getLittersFathered(String character) {
		return litterMap.values().stream().filter(litter -> litter.getFatherId().equals(character)).collect(Collectors.toList());
	}

	public static List<Litter> getLittersFathered(GameCharacter character) {
		return litterMap.values().stream().filter(litter -> litter.getFather().equals(character)).collect(Collectors.toList());
	}
	
	public static List<Litter> getLittersMothered(String character) {
		return litterMap.values().stream().filter(litter -> litter.getMotherId().equals(character)).collect(Collectors.toList());
	}

	public static List<Litter> getLittersMothered(GameCharacter character) {
		return litterMap.values().stream().filter(litter -> litter.getMother().equals(character)).collect(Collectors.toList());
	}
	
	public static List<Litter> getLittersIncubated(String character) {
		return litterMap.values().stream().filter(litter -> litter.getIncubatorId().equals(character)).collect(Collectors.toList());
	}

	public static List<Litter> getLittersIncubated(GameCharacter character) {
		return litterMap.values().stream().filter(litter -> litter.getIncubator().equals(character)).collect(Collectors.toList());
	}
	
	public static List<Litter> getLittersBirthed(String character) {
		return litterMap.values().stream().filter(litter -> litter.getMotherId().equals(character) && litter.getIncubatorId() == null).collect(Collectors.toList());
	}

	public static List<Litter> getLittersBirthed(GameCharacter character) {
		return litterMap.values().stream().filter(litter -> litter.getMother().equals(character) && litter.getIncubatorId() == null).collect(Collectors.toList());
	}
	
	public static List<Litter> getLittersImplanted(String character) {
		return litterMap.values().stream().filter(litter -> litter.getMotherId().equals(character) && litter.getIncubatorId() != null).collect(Collectors.toList());
	}

	public static List<Litter> getLittersImplanted(GameCharacter character) {
		return litterMap.values().stream().filter(litter -> litter.getMother().equals(character) && litter.getIncubatorId() != null).collect(Collectors.toList());
	}
	
	public static Litter getLastLitterBirthed(GameCharacter character) {
		List<Litter> filteredLitterMap = getLittersBirthed(character);
		return filteredLitterMap.get(filteredLitterMap.size()-1);
	}
	
	public static Litter getLastLitterIncubated(GameCharacter character) {
		List<Litter> filteredLitterMap = getLittersIncubated(character);
		return filteredLitterMap.get(filteredLitterMap.size()-1);
	}
	
	public static Integer addLitter(Litter litter) {
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
	
	public static Litter addLitter(Litter litter, Integer id) {
		return litterMap.put(id, litter);
	}
	
	public static Litter getLitter(Integer id) {
		if(id!=null) {
			return litterMap.getOrDefault(id, null);
		}
		return null;
	}
	
	public static void swapLitter(Integer key, String oldId, String newId) {
		if(key !=null && Family.getLitter(key).getOffspring().contains(oldId)) {
			Family.getLitter(key).getOffspring().remove(oldId);
			Family.getLitter(key).getOffspring().add(newId);
		}
	}
	
	public static void swapLitters(String oldId, String newId) {
		for(Map.Entry<Integer, Litter> entry : litterMap.entrySet()) {
			swapLitter(entry.getKey(), oldId, newId);
		}
	}
	
	public static Element saveAsXML(Element parentElement, Document doc) {
		parentElement.setAttribute("atomicId", nextLitterId.toString());
		for(Map.Entry<Integer, Litter> entry : litterMap.entrySet()) {
			Element litterElement = entry.getValue().saveAsXML(parentElement, doc);
			litterElement.setAttribute("key", entry.getKey().toString());
		}
		return parentElement;
	}
	
	public static Element loadFromXML(Element parentElement, Document doc) {
		nextLitterId.set(Integer.parseInt(parentElement.getAttribute("atomicId")));
		NodeList litters = parentElement.getElementsByTagName("litter");
		for(int i = 0; i < litters.getLength(); i++) {
			addLitter(Litter.loadFromXML((Element) litters.item(i), doc), Integer.valueOf(((Element) litters.item(i)).getAttribute("key")));
		}
		return parentElement;
	}
}
