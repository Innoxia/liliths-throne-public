package com.lilithsthrone.game.sex.positions.slots;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.utils.Util;

/**
 * Handles the id generation of SexSlots from internal files.
 * 
 * @since 0.4.1
 * @version 0.4.1
 * @author Innoxia
 */
public class SexSlotManager {
	
	private static List<SexSlot> allSexSlots = new ArrayList<>();
	private static Map<SexSlot, String> sexSlotToIdMap = new HashMap<>();
	private static Map<String, SexSlot> idToSexSlotMap = new HashMap<>();


	public static List<SexSlot> getAllSexSlots() {
		return allSexSlots;
	}

	public static Map<String, SexSlot> getIdToSexSlotMap() {
		return idToSexSlotMap;
	}
	
	public static List<String> getAllSexSlotIds() {
		return new ArrayList<>(idToSexSlotMap.keySet());
	}
	
	public static SexSlot getSexSlotFromId(String id) {
		id = id.trim(); // Just make sure that any parsed ids have been trimmed
		id = Util.getClosestStringMatch(id, idToSexSlotMap.keySet());
		return idToSexSlotMap.get(id);
	}
	
	public static String getIdFromSexSlot(SexSlot placeType) {
		return sexSlotToIdMap.get(placeType);
	}
	
	static {
		Map<String, Field[]> positionIdToSexSlotFields = new HashMap<>();
		
		positionIdToSexSlotFields.put("AGAINST_WALL", SexSlotAgainstWall.class.getFields());
		positionIdToSexSlotFields.put("ALL_FOURS", SexSlotAllFours.class.getFields());
		positionIdToSexSlotFields.put("BREEDING_STALL", SexSlotBreedingStall.class.getFields());
		positionIdToSexSlotFields.put("OVER_DESK", SexSlotDesk.class.getFields());
		positionIdToSexSlotFields.put("GENERIC", SexSlotGeneric.class.getFields());
		positionIdToSexSlotFields.put("LYING_DOWN", SexSlotLyingDown.class.getFields());
		positionIdToSexSlotFields.put("MASTURBATION", SexSlotMasturbation.class.getFields());
		positionIdToSexSlotFields.put("MILKING_STALL", SexSlotMilkingStall.class.getFields());
		positionIdToSexSlotFields.put("SITTING", SexSlotSitting.class.getFields());
		positionIdToSexSlotFields.put("STANDING", SexSlotStanding.class.getFields());
		positionIdToSexSlotFields.put("STOCKS", SexSlotStocks.class.getFields());
		positionIdToSexSlotFields.put("UNIQUE", SexSlotUnique.class.getFields());
		
		for(Entry<String, Field[]> entry : positionIdToSexSlotFields.entrySet()) {
			for(Field field : entry.getValue()) {
				if (SexSlot.class.isAssignableFrom(field.getType())) {
					SexSlot sexSlot;
					try {
						sexSlot = ((SexSlot) field.get(null));
						
						String id = entry.getKey()+"_"+field.getName();
						sexSlotToIdMap.put(sexSlot, id);
						idToSexSlotMap.put(id, sexSlot);
						
						allSexSlots.add(sexSlot);
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
}
