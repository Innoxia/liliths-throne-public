package com.lilithsthrone.game.sex.managers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.utils.Util;

/**
 * Handles the loading and id generation of sex managers from external files.
 * 
 * @since 0.4.1
 * @version 0.4.1
 * @author Innoxia
 */
public class SexManagerLoader {
	
	private static List<SexManagerExternal> allSexManagers = new ArrayList<>();
	private static Map<SexManagerExternal, String> sexManagerToIdMap = new HashMap<>();
	private static Map<String, SexManagerExternal> idToSexManagerMap = new HashMap<>();
	
	public static List<SexManagerExternal> getAllSexManagers() {
		return allSexManagers;
	}
	
	public static SexManagerExternal getSexManagerFromId(String id) {
		id = id.trim(); // Just make sure that any parsed ids have been trimmed
		id = Util.getClosestStringMatch(id, idToSexManagerMap.keySet());
		return idToSexManagerMap.get(id);
	}
	
	public static String getIdFromSexManager(SexManagerExternal sexManager) {
		return sexManagerToIdMap.get(sexManager);
	}
	
	static {
		// Modded sexManager types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/sex/managers");
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					SexManagerExternal sexManager = new SexManagerExternal(innerEntry.getValue(), innerEntry.getKey(), true);
					String id = innerEntry.getKey();
					id = id.replaceAll("sex_managers_", "");
					allSexManagers.add(sexManager);
					sexManagerToIdMap.put(sexManager, id);
					idToSexManagerMap.put(id, sexManager);
//					System.out.println("modded sex manager: "+innerEntry.getKey());
				} catch(Exception ex) {
					System.err.println("Loading modded sexManager type failed at 'SexManager'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		// External res sexManager types:

		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/sex");
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("sexManager")) {
					try {
						SexManagerExternal sexManager = new SexManagerExternal(innerEntry.getValue(), innerEntry.getKey(), false);
						String id = innerEntry.getKey();
						id = id.replaceAll("managers_", "");
						allSexManagers.add(sexManager);
						sexManagerToIdMap.put(sexManager, id);
						idToSexManagerMap.put(id, sexManager);
//						System.out.println("Added sex manager: "+id);
							
					} catch(Exception ex) {
						System.err.println("Loading sexManager type failed at 'SexManager'. File path: "+innerEntry.getValue().getAbsolutePath());
						System.err.println("Actual exception: ");
						ex.printStackTrace(System.err);
					}
				}
			}
		}
	}
	
}
