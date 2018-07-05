package com.lilithsthrone.game.dialogue;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.0
 * @version 0.2.8
 * @author Innoxia
 */
public class DialogueFlags implements Serializable, XMLSaving {

	private static final long serialVersionUID = 1L;

	public Set<DialogueFlagValue> values;
	
	public long ralphDiscountStartTime;
	public int ralphDiscount;
	public int scarlettPrice;
	public int eponaStamps;
	public long kalahariBreakStartTime;
	
	// Amount of dialogue choices you can make before offspring interaction ends:
	public int offspringDialogueTokens = 2;
	
	// Reindeer event related flags:
	private Set<String> reindeerEncounteredIDs = new HashSet<>();
	private Set<String> reindeerWorkedForIDs = new HashSet<>();
	private Set<String> reindeerFuckedIDs = new HashSet<>();
	
	// Supplier storage rooms checked:
	public Set<Vector2i> supplierStorageRoomsChecked = new HashSet<>();
	
	private String slaveTrader;
	private String slaveryManagerSlaveSelected;
	
	public DialogueFlags() {
		values = new HashSet<>();
		
		slaveryManagerSlaveSelected = null;
		slaveTrader = null;
		
		ralphDiscountStartTime = -1;
		kalahariBreakStartTime = -1;
		ralphDiscount = 0;
		
		eponaStamps = 0;
		
		scarlettPrice = 15000;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("dialogueFlags");
		parentElement.appendChild(element);
		
		CharacterUtils.createXMLElementWithValue(doc, element, "ralphDiscountStartTime", String.valueOf(ralphDiscountStartTime));
		CharacterUtils.createXMLElementWithValue(doc, element, "ralphDiscount", String.valueOf(ralphDiscount));
		CharacterUtils.createXMLElementWithValue(doc, element, "scarlettPrice", String.valueOf(scarlettPrice));
		CharacterUtils.createXMLElementWithValue(doc, element, "eponaStamps", String.valueOf(eponaStamps));
		CharacterUtils.createXMLElementWithValue(doc, element, "kalahariBreakStartTime", String.valueOf(kalahariBreakStartTime));
		
		CharacterUtils.createXMLElementWithValue(doc, element, "offspringDialogueTokens", String.valueOf(offspringDialogueTokens));
		CharacterUtils.createXMLElementWithValue(doc, element, "slaveTrader", slaveTrader);
		CharacterUtils.createXMLElementWithValue(doc, element, "slaveryManagerSlaveSelected", slaveryManagerSlaveSelected);
		
		Element valuesElement = doc.createElement("dialogueValues");
		element.appendChild(valuesElement);
		for(DialogueFlagValue value : values) {
			CharacterUtils.createXMLElementWithValue(doc, valuesElement, "dialogueValue", value.toString());
		}
		
		saveSet(element, doc, reindeerEncounteredIDs, "reindeerEncounteredIDs");
		saveSet(element, doc, reindeerWorkedForIDs, "reindeerWorkedForIDs");
		saveSet(element, doc, reindeerFuckedIDs, "reindeerFuckedIDs");
		
		Element supplierStorageRoomsCheckedElement = doc.createElement("supplierStorageRoomsChecked");
		element.appendChild(supplierStorageRoomsCheckedElement);
		for(Vector2i value : supplierStorageRoomsChecked) {
			Element location = doc.createElement("location");
			supplierStorageRoomsCheckedElement.appendChild(location);
			CharacterUtils.addAttribute(doc, location, "x", String.valueOf(value.getX()));
			CharacterUtils.addAttribute(doc, location, "y", String.valueOf(value.getY()));
		}
		
		
		return element;
	}
	
	public static DialogueFlags loadFromXML(Element parentElement, Document doc) {
		DialogueFlags newFlags = new DialogueFlags();
		
		newFlags.ralphDiscountStartTime = Long.valueOf(((Element)parentElement.getElementsByTagName("ralphDiscountStartTime").item(0)).getAttribute("value"));
		newFlags.ralphDiscount = Integer.valueOf(((Element)parentElement.getElementsByTagName("ralphDiscount").item(0)).getAttribute("value"));
		newFlags.scarlettPrice = Integer.valueOf(((Element)parentElement.getElementsByTagName("scarlettPrice").item(0)).getAttribute("value"));
		
		newFlags.offspringDialogueTokens = Integer.valueOf(((Element)parentElement.getElementsByTagName("offspringDialogueTokens").item(0)).getAttribute("value"));
		newFlags.slaveTrader = ((Element)parentElement.getElementsByTagName("slaveTrader").item(0)).getAttribute("value");
		newFlags.slaveryManagerSlaveSelected = ((Element)parentElement.getElementsByTagName("slaveryManagerSlaveSelected").item(0)).getAttribute("value");
		
		try {
			newFlags.eponaStamps = Integer.valueOf(((Element)parentElement.getElementsByTagName("eponaStamps").item(0)).getAttribute("value"));
		} catch(Exception ex) {
		}
		
		try {
			newFlags.kalahariBreakStartTime = Long.valueOf(((Element)parentElement.getElementsByTagName("kalahariBreakStartTime").item(0)).getAttribute("value"));
		} catch(Exception ex) {
		}
		
		for(int i=0; i<((Element) parentElement.getElementsByTagName("dialogueValues").item(0)).getElementsByTagName("dialogueValue").getLength(); i++){
			Element e = (Element) ((Element) parentElement.getElementsByTagName("dialogueValues").item(0)).getElementsByTagName("dialogueValue").item(i);
			
			try {
				newFlags.values.add(DialogueFlagValue.valueOf(e.getAttribute("value")));
			} catch(Exception ex) {
			}
		}
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.6.1")) {
			newFlags.values.remove(DialogueFlagValue.axelIntroduced);
			newFlags.values.remove(DialogueFlagValue.roxyIntroduced);
			newFlags.values.remove(DialogueFlagValue.eponaIntroduced);
		}

		loadSet(parentElement, doc, newFlags.reindeerEncounteredIDs, "reindeerEncounteredIDs");
		loadSet(parentElement, doc, newFlags.reindeerWorkedForIDs, "reindeerWorkedForIDs");
		loadSet(parentElement, doc, newFlags.reindeerFuckedIDs, "reindeerFuckedIDs");
		
		if(parentElement.getElementsByTagName("supplierStorageRoomsChecked").item(0)!=null) {
			for(int i=0; i<((Element) parentElement.getElementsByTagName("supplierStorageRoomsChecked").item(0)).getElementsByTagName("location").getLength(); i++){
				Element e = (Element) ((Element) parentElement.getElementsByTagName("supplierStorageRoomsChecked").item(0)).getElementsByTagName("location").item(i);
				
				newFlags.supplierStorageRoomsChecked.add(
						new Vector2i(
								Integer.valueOf(e.getAttribute("x")),
								Integer.valueOf(e.getAttribute("y"))));
			}
		}
		return newFlags;
	}
	
	private static void saveSet(Element parentElement, Document doc, Set<String> set, String title) {
		Element valuesElement = doc.createElement(title);
		parentElement.appendChild(valuesElement);
		for(String value : set) {
			CharacterUtils.createXMLElementWithValue(doc, valuesElement, "value", value.toString());
		}
	}
	
	private static void loadSet(Element parentElement, Document doc, Set<String> set, String title) {
		try {
			if(parentElement.getElementsByTagName(title).item(0)!=null) {
				for(int i=0; i<((Element) parentElement.getElementsByTagName(title).item(0)).getElementsByTagName("value").getLength(); i++){
					Element e = (Element) ((Element) parentElement.getElementsByTagName(title).item(0)).getElementsByTagName("value").item(i);
				
					set.add(e.getAttribute("value"));
				}
			}
		} catch(Exception ex) {
			// What is this...
			System.err.println("Whoopsie :^)"); // Prints out "Whoopsie :^) to the error output stream."
		}
	}

	public boolean hasFlag(DialogueFlagValue flag) {
		return values.contains(flag);
	}
	
	public void setFlag(DialogueFlagValue flag, boolean flagMarker) {
		if(flagMarker) {
			values.add(flag);
		} else {
			values.remove(flag);
		}
	}
	
	public NPC getSlaveTrader() {
		if(slaveTrader==null) {
			return null;
		}
		return (NPC) Main.game.getNPCById(slaveTrader);
	}

	public void setSlaveTrader(GameCharacter slaveTrader) {
		if(slaveTrader==null) {
			this.slaveTrader = null;
		} else {
			this.slaveTrader = slaveTrader.getId();
		}
	}
	
	public String getSlaveTraderId() {
		return slaveTrader;
	}

	public void setSlaveTraderId(String slaveTrader) {
		this.slaveTrader = slaveTrader;
	}

	public NPC getSlaveryManagerSlaveSelected() {
		if(slaveryManagerSlaveSelected==null
				|| slaveryManagerSlaveSelected.isEmpty()) {
			return null;
		}
		return (NPC) Main.game.getNPCById(slaveryManagerSlaveSelected);
	}

	public void setSlaveryManagerSlaveSelected(GameCharacter slaveryManagerSlaveSelected) {
		if(slaveryManagerSlaveSelected==null) {
			this.slaveryManagerSlaveSelected = null;
		} else {
			this.slaveryManagerSlaveSelected = slaveryManagerSlaveSelected.getId();
		}
	}
	
	public String getSlaveryManagerSlaveSelectedId() {
		return slaveryManagerSlaveSelected;
	}

	public void setSlaveryManagerSlaveSelectedId(String slaveryManagerSlaveSelected) {
		this.slaveryManagerSlaveSelected = slaveryManagerSlaveSelected;
	}

	// Reindeer event:
	
	public void addReindeerEncountered(String reindeerID) {
		reindeerEncounteredIDs.add(reindeerID);
	}
	
	public boolean hasEncounteredReindeer(String reindeerID) {
		return reindeerEncounteredIDs.contains(reindeerID);
	}
	
	public boolean hasEncounteredAnyReindeers() {
		return !reindeerEncounteredIDs.isEmpty();
	}
	
	public void addReindeerDailyWorkedFor(String reindeerID) {
		reindeerWorkedForIDs.add(reindeerID);
	}
	
	public boolean hasWorkedForReindeer(String reindeerID) {
		return reindeerWorkedForIDs.contains(reindeerID);
	}
	
	public void addReindeerDailyFucked(String reindeerID) {
		reindeerFuckedIDs.add(reindeerID);
	}
	
	public boolean hasFuckedReindeer(String reindeerID) {
		return reindeerFuckedIDs.contains(reindeerID);
	}
	
	public void dailyReindeerReset(String reindeerID) {
		reindeerWorkedForIDs.remove(reindeerID);
	}
	
	public void resetNyanActions() {
		this.setFlag(DialogueFlagValue.nyanTalkedTo, false);
		this.setFlag(DialogueFlagValue.nyanComplimented, false);
		this.setFlag(DialogueFlagValue.nyanFlirtedWith, false);
		this.setFlag(DialogueFlagValue.nyanKissed, false);
		this.setFlag(DialogueFlagValue.nyanMakeOut, false);
		this.setFlag(DialogueFlagValue.nyanSex, false);
		this.setFlag(DialogueFlagValue.nyanGift, false);
	}
}
