package com.lilithsthrone.game.sex.recording;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.4.11.4
 * @version 0.4.11.5
 * @author Innoxia
 */
public class SexSceneRecording implements XMLSaving {
	
	private long saveGameUID;
	
	private List<GameCharacter> participants;
	
	private Map<Integer, List<SexActionRecording>> sexActionRecordingMap;
	
	public SexSceneRecording(long saveGameUID, List<GameCharacter> participants, Map<Integer, List<SexActionRecording>> sexActionRecordingMap) {
		this.saveGameUID = saveGameUID;
		this.participants = new ArrayList<>(participants);
		this.sexActionRecordingMap = sexActionRecordingMap;
		
		//TODO replace IDs
		// replace the characters with new class copies which store original IDs?
	}
	
	public SexSceneRecording(long saveGameUID, List<GameCharacter> participants) {
		this(saveGameUID, participants, new HashMap<>());
	}

	public long getSaveGameUID() {
		return saveGameUID;
	}
	
	public int getLength() {
		return sexActionRecordingMap.size();
	}
	
	public List<GameCharacter> getParticipants() {
		return participants;
	}
	
	public List<SexActionRecording> getActionInformationList(int sexTurn) {
		return sexActionRecordingMap.get(sexTurn);
	}
	
	/**
	 * @return The ActionInformation which corresponds to the sexTurn and turnIndex in which it occurred. If either sexTurn or turnIndex are out of bounds then this will throw a NoSuchElementException.
	 */
	public SexActionRecording getActionInformationList(int sexTurn, int turnIndex) {
		return getActionInformationList(sexTurn).stream().filter(ai -> ai.getTurnIndex()==turnIndex).findFirst().get();
	}
	
	public void addActionInformation(int sexTurn, SexActionRecording information) {
		sexActionRecordingMap.putIfAbsent(sexTurn, new ArrayList<>());
		
		if(sexActionRecordingMap.get(sexTurn).stream().anyMatch(ai -> ai.getTurnIndex()==information.getTurnIndex())) {
			System.err.println("WARNING: An instance of ActionInformation shares the same turn index as the ActionInformation which you're adding to SceneRecording!");
			new IllegalArgumentException().printStackTrace();
		}
		
		sexActionRecordingMap.get(sexTurn).add(information);
	}

	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("sexSceneRecording");
		parentElement.appendChild(element);
		
		// Save game UID:
		XMLUtil.addAttribute(doc, element, "saveGameUID", String.valueOf(getSaveGameUID()));
		
		// Participants:
		Element participantsElement = doc.createElement("participants");
		element.appendChild(participantsElement);
		for(int i=0; i<getParticipants().size();i++) {
			getParticipants().get(i).saveAsXML(participantsElement, doc);
		}
		
		// sexActionRecordingMap:
		Element sexActionRecordingsMapElement = doc.createElement("sexActionRecordingsMap");
		element.appendChild(sexActionRecordingsMapElement);
		for(Entry<Integer, List<SexActionRecording>> entry : sexActionRecordingMap.entrySet()) {
			Element sexSceneTurnElement = doc.createElement("sexSceneTurn");
			XMLUtil.addAttribute(doc, sexSceneTurnElement, "index", String.valueOf(entry.getKey()));
			
			if(!entry.getValue().isEmpty()) {
				for(SexActionRecording recording : entry.getValue()) {
					recording.saveAsXML(sexSceneTurnElement, doc);
				}
			}
			element.appendChild(sexSceneTurnElement);
		}
		
		return element;
	}
	
	public static SexSceneRecording loadFromXML(Element parentElement, Document doc) {
		try {
			// Save game UID:
			int importedSaveGameUID = Integer.valueOf(parentElement.getAttribute("saveGameUID"));

			// Participants:
			Element participantsElement = (Element) parentElement.getElementsByTagName("participants").item(0);
			NodeList gameCharacterElements = participantsElement.getElementsByTagName("character");
			List<GameCharacter> importedGameCharacters = new ArrayList<>();
			for(int i=0; i<gameCharacterElements.getLength(); i++) {
				Element characterElement = ((Element)gameCharacterElements.item(i));
				try {
					GenericSexualPartner loadedNPC = new GenericSexualPartner();
					loadedNPC.loadFromXML(characterElement, doc);
					Main.game.addNPC(loadedNPC, false, true);
					importedGameCharacters.add(loadedNPC);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// sexActionRecordingMap

			Map<Integer, List<SexActionRecording>> importedTurns = new HashMap<>();
			Element sexActionRecordingsMapElement = (Element) parentElement.getElementsByTagName("sexActionRecordingsMap").item(0);
			
			NodeList recordings = sexActionRecordingsMapElement.getElementsByTagName("sexSceneTurn");
			for(int i=0; i<recordings.getLength(); i++) {
				Element recordingElement = ((Element)recordings.item(i));
				int turnIndex = Integer.valueOf(recordingElement.getAttribute("index"));
				if(importedTurns.containsKey(turnIndex)) {
					System.err.println("WARNING: SexSceneRecording loading is replacing the key '"+turnIndex+"'!");
				}
				importedTurns.put(turnIndex, new ArrayList<>());
				
				NodeList sexActionRecordingList = recordingElement.getElementsByTagName("sexActionRecording");
				for(int j=0; j<sexActionRecordingList.getLength(); j++) {
					SexActionRecording loadedRecording = SexActionRecording.loadFromXML((Element)sexActionRecordingList.item(j), doc);
					importedTurns.get(turnIndex).add(loadedRecording);
				}
			}
			
			return new SexSceneRecording(importedSaveGameUID, importedGameCharacters, importedTurns);
			
		} catch(Exception ex) {
			System.err.println("Warning: SexSceneRecording failed to import!");
			ex.printStackTrace();
			return null;
		}
	}
	
	public static void saveToExternalXMLFile(SexSceneRecording sexSceneRecording, String name) {
		name = Main.checkFileName(name);
		if(name.isEmpty()) {
			return;
		}
		
		File dir = new File("data/");
		dir.mkdir();

		dir = new File("data/sexSceneRecordings");
		dir.mkdir();

		String originalName = name;
		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, filename) -> filename.endsWith(".xml"));
			if (directoryListing != null) {
				int appendingIndex = 0;
				for (File child : directoryListing) {
					if(child.getName().equals(name+".xml")){
						appendingIndex++;
						name = originalName+"_"+appendingIndex;
					}
				}
			}
		}

		try {
			// Starting stuff:
			Document doc = Main.getDocBuilder().newDocument();
			
			Element coreElement = doc.createElement("savedSexSceneRecording");
			doc.appendChild(coreElement);
			
			sexSceneRecording.saveAsXML(coreElement, doc);
			
			// Ending stuff:
			
			Transformer transformer1 = Main.transformerFactory.newTransformer();
			transformer1.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();

			transformer1.transform(new DOMSource(doc), new StreamResult(writer));
			
			// Save this xml:
			Transformer transformer = Main.transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			
			String saveLocation = "data/sexSceneRecordings/"+name+".xml";
			StreamResult result = new StreamResult(saveLocation);
			
			transformer.transform(source, result);
			
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

		Main.game.flashMessage(PresetColour.GENERIC_GOOD, "Sex Scene Recorded!");
	}
}
