package com.lilithsthrone.game.dialogue;

import java.util.HashSet;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.places.dominion.helenaHotel.HelenaConversationTopic;
import com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict.DominionExpress;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.SlaveJob;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.3.7
 * @author Innoxia
 */
public class DialogueFlags implements XMLSaving {
	
	public static int MUGGER_DEMAND_1 = 250;
	public static int MUGGER_DEMAND_2 = 500;
	
	public Set<DialogueFlagValue> values;
	
	public int ralphDiscount;
	public int scarlettPrice;
	public int eponaStamps;
	
	// Timers:
	public long ralphDiscountStartTime;
	public long kalahariBreakStartTime;
	public long daddyResetTimer;
	public long candiSexTimer;
	public long ralphSexTimer;
	public long impFortressAlphaDefeatedTime;
	public long impFortressDemonDefeatedTime;
	public long impFortressFemalesDefeatedTime;
	public long impFortressMalesDefeatedTime;
	public int helenaSlaveOrderDay;

	public int impCitadelImpWave;
	
	// Amount of dialogue choices you can make before offspring interaction ends:
	public int offspringDialogueTokens = 2;
	
	// Murk transformation stage tracking:
	private int murkPlayerTfStage;
	private int murkCompanionTfStage;
	
	private String slaveTrader;
	
	private String managementCompanion;
	private SlaveJob slaveryManagerJobSelected;
	
	private Colour natalyaCollarColour;
	private int natalyaPoints;
	private int natalyaWages;
	
	// --- Sets: --- //
	
	private Set<String> helenaConversationTopics = new HashSet<>();
	
	// Reindeer event related flags:
	private Set<String> reindeerEncounteredIDs = new HashSet<>();
	private Set<String> reindeerWorkedForIDs = new HashSet<>();
	private Set<String> reindeerFuckedIDs = new HashSet<>();
	
	// Enforcer warehouse guards defeated:
	public Set<String> warehouseDefeatedIDs = new HashSet<>();
	
	// Storage tiles checked:
	public Set<Vector2i> supplierStorageRoomsChecked = new HashSet<>();
	
	
	public DialogueFlags() {
		values = new HashSet<>();

		slaveTrader = null;
		
		managementCompanion = null;
		slaveryManagerJobSelected = SlaveJob.IDLE;
		
		ralphDiscountStartTime = -1;
		kalahariBreakStartTime = -1;
		daddyResetTimer = -1;
		candiSexTimer = -1;
		ralphSexTimer = -1;
		helenaSlaveOrderDay = -1;
				
		ralphDiscount = 0;
		
		eponaStamps = 0;
		
		scarlettPrice = 15000;
		
		impFortressAlphaDefeatedTime
			= impFortressDemonDefeatedTime 
			= impFortressFemalesDefeatedTime
			= impFortressMalesDefeatedTime
			= -50000;
		
		impCitadelImpWave = 0;
		
		murkPlayerTfStage = 0;
		murkCompanionTfStage = 0;
		
		natalyaCollarColour = PresetColour.CLOTHING_STEEL;
		natalyaPoints = 0;
		natalyaWages = 0;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("dialogueFlags");
		parentElement.appendChild(element);
		
		CharacterUtils.createXMLElementWithValue(doc, element, "ralphDiscountStartTime", String.valueOf(ralphDiscountStartTime));
		CharacterUtils.createXMLElementWithValue(doc, element, "ralphDiscount", String.valueOf(ralphDiscount));
		CharacterUtils.createXMLElementWithValue(doc, element, "scarlettPrice", String.valueOf(scarlettPrice));
		CharacterUtils.createXMLElementWithValue(doc, element, "eponaStamps", String.valueOf(eponaStamps));
		CharacterUtils.createXMLElementWithValue(doc, element, "kalahariBreakStartTime", String.valueOf(kalahariBreakStartTime));
		CharacterUtils.createXMLElementWithValue(doc, element, "daddyResetTimer", String.valueOf(daddyResetTimer));
		CharacterUtils.createXMLElementWithValue(doc, element, "helenaSlaveOrderDay", String.valueOf(helenaSlaveOrderDay));

		CharacterUtils.createXMLElementWithValue(doc, element, "impFortressAlphaDefeatedTime", String.valueOf(impFortressAlphaDefeatedTime));
		CharacterUtils.createXMLElementWithValue(doc, element, "impFortressDemonDefeatedTime", String.valueOf(impFortressDemonDefeatedTime));
		CharacterUtils.createXMLElementWithValue(doc, element, "impFortressFemalesDefeatedTime", String.valueOf(impFortressFemalesDefeatedTime));
		CharacterUtils.createXMLElementWithValue(doc, element, "impFortressMalesDefeatedTime", String.valueOf(impFortressMalesDefeatedTime));

		CharacterUtils.createXMLElementWithValue(doc, element, "impCitadelImpWave", String.valueOf(impCitadelImpWave));

		CharacterUtils.createXMLElementWithValue(doc, element, "murkPlayerTfStage", String.valueOf(murkPlayerTfStage));
		CharacterUtils.createXMLElementWithValue(doc, element, "murkCompanionTfStage", String.valueOf(murkCompanionTfStage));
		
		CharacterUtils.createXMLElementWithValue(doc, element, "offspringDialogueTokens", String.valueOf(offspringDialogueTokens));
		CharacterUtils.createXMLElementWithValue(doc, element, "slaveTrader", slaveTrader);
		CharacterUtils.createXMLElementWithValue(doc, element, "slaveryManagerSlaveSelected", managementCompanion);

		CharacterUtils.createXMLElementWithValue(doc, element, "natalyaCollarColour", PresetColour.getIdFromColour(natalyaCollarColour));
		CharacterUtils.createXMLElementWithValue(doc, element, "natalyaPoints", String.valueOf(natalyaPoints));
		CharacterUtils.createXMLElementWithValue(doc, element, "natalyaWages", String.valueOf(natalyaWages));
		
		
		Element valuesElement = doc.createElement("dialogueValues");
		element.appendChild(valuesElement);
		for(DialogueFlagValue value : values) {
			CharacterUtils.createXMLElementWithValue(doc, valuesElement, "dialogueValue", value.toString());
		}
		
		
		saveSet(element, doc, helenaConversationTopics, "helenaConversationTopics");
		
		saveSet(element, doc, reindeerEncounteredIDs, "reindeerEncounteredIDs");
		saveSet(element, doc, reindeerWorkedForIDs, "reindeerWorkedForIDs");
		saveSet(element, doc, reindeerFuckedIDs, "reindeerFuckedIDs");
		
		saveSet(element, doc, warehouseDefeatedIDs, "warehouseDefeatedIDs");
		
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
		newFlags.managementCompanion = ((Element)parentElement.getElementsByTagName("slaveryManagerSlaveSelected").item(0)).getAttribute("value");
		
		try {
			newFlags.eponaStamps = Integer.valueOf(((Element)parentElement.getElementsByTagName("eponaStamps").item(0)).getAttribute("value"));
		} catch(Exception ex) {
		}
		
		try {
			newFlags.kalahariBreakStartTime = Long.valueOf(((Element)parentElement.getElementsByTagName("kalahariBreakStartTime").item(0)).getAttribute("value"));
		} catch(Exception ex) {
		}
		
		try {
			newFlags.daddyResetTimer = Long.valueOf(((Element)parentElement.getElementsByTagName("daddyResetTimer").item(0)).getAttribute("value"));
		} catch(Exception ex) {
		}
		
		try {
			newFlags.helenaSlaveOrderDay = Integer.valueOf(((Element)parentElement.getElementsByTagName("helenaSlaveOrderDay").item(0)).getAttribute("value"));
		} catch(Exception ex) {
		}
		
		try {
			if(!Main.isVersionOlderThan(Game.loadingVersion, "0.2.11.5")) {
				newFlags.impFortressAlphaDefeatedTime = Long.valueOf(((Element)parentElement.getElementsByTagName("impFortressAlphaDefeatedTime").item(0)).getAttribute("value"));
				newFlags.impFortressDemonDefeatedTime = Long.valueOf(((Element)parentElement.getElementsByTagName("impFortressDemonDefeatedTime").item(0)).getAttribute("value"));
				newFlags.impFortressFemalesDefeatedTime = Long.valueOf(((Element)parentElement.getElementsByTagName("impFortressFemalesDefeatedTime").item(0)).getAttribute("value"));
				newFlags.impFortressMalesDefeatedTime = Long.valueOf(((Element)parentElement.getElementsByTagName("impFortressMalesDefeatedTime").item(0)).getAttribute("value"));
			}
		} catch(Exception ex) {
		}
		
		try {
			newFlags.impCitadelImpWave = Integer.valueOf(((Element)parentElement.getElementsByTagName("impCitadelImpWave").item(0)).getAttribute("value"));
		} catch(Exception ex) {
		}

		try {
			newFlags.murkPlayerTfStage = Integer.valueOf(((Element)parentElement.getElementsByTagName("murkPlayerTfStage").item(0)).getAttribute("value"));
			newFlags.murkCompanionTfStage = Integer.valueOf(((Element)parentElement.getElementsByTagName("murkCompanionTfStage").item(0)).getAttribute("value"));
		} catch(Exception ex) {
		}

		try {
			newFlags.natalyaCollarColour = PresetColour.getColourFromId(((Element)parentElement.getElementsByTagName("natalyaCollarColour").item(0)).getAttribute("value"));
			newFlags.natalyaPoints = Integer.valueOf(((Element)parentElement.getElementsByTagName("natalyaPoints").item(0)).getAttribute("value"));
			newFlags.natalyaWages = Integer.valueOf(((Element)parentElement.getElementsByTagName("natalyaWages").item(0)).getAttribute("value"));
		} catch(Exception ex) {
		}
		
		for(int i=0; i<((Element) parentElement.getElementsByTagName("dialogueValues").item(0)).getElementsByTagName("dialogueValue").getLength(); i++){
			Element e = (Element) ((Element) parentElement.getElementsByTagName("dialogueValues").item(0)).getElementsByTagName("dialogueValue").item(i);
			
			try {
				String flag = e.getAttribute("value");
				if(flag.equalsIgnoreCase("punishedByAlexa")) {
					newFlags.values.add(DialogueFlagValue.punishedByHelena);
				} else {
					newFlags.values.add(DialogueFlagValue.valueOf(flag));
				}
			} catch(Exception ex) {
			}
		}
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.6.1")) {
			newFlags.values.remove(DialogueFlagValue.axelIntroduced);
			newFlags.values.remove(DialogueFlagValue.roxyIntroduced);
			newFlags.values.remove(DialogueFlagValue.eponaIntroduced);
		}
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.11.5")) { // Add defeated flags so that the fortress will reset.
			newFlags.values.add(DialogueFlagValue.impFortressAlphaDefeated);
			newFlags.values.add(DialogueFlagValue.impFortressDemonDefeated);
			newFlags.values.add(DialogueFlagValue.impFortressFemalesDefeated);
			newFlags.values.add(DialogueFlagValue.impFortressMalesDefeated);
		}
		
		loadSet(parentElement, doc, newFlags.helenaConversationTopics, "helenaConversationTopics");
		
		loadSet(parentElement, doc, newFlags.reindeerEncounteredIDs, "reindeerEncounteredIDs");
		loadSet(parentElement, doc, newFlags.reindeerWorkedForIDs, "reindeerWorkedForIDs");
		loadSet(parentElement, doc, newFlags.reindeerFuckedIDs, "reindeerFuckedIDs");
		
		loadSet(parentElement, doc, newFlags.warehouseDefeatedIDs, "warehouseDefeatedIDs");
		
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
		}
	}

	public void dailyReset() {
		values.removeIf((flag)->flag.isDailyReset());
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
		if(slaveTrader==null || slaveTrader.isEmpty()) {
			return null;
		}
		try {
			return (NPC) Main.game.getNPCById(slaveTrader);
		} catch (Exception e) {
			Util.logGetNpcByIdError("getSlaveTrader()", slaveTrader);
			return null;
		}
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

	public NPC getManagementCompanion() {
		if(managementCompanion==null || managementCompanion.isEmpty()) {
			return null;
		}
		try {
			return (NPC) Main.game.getNPCById(managementCompanion);
		} catch (Exception e) {
			Util.logGetNpcByIdError("getSlaveryManagerSlaveSelected()", managementCompanion);
			return null;
		}
	}

	public void setManagementCompanion(GameCharacter managementCompanion) {
		if(managementCompanion==null) {
			this.managementCompanion = null;
		} else {
			this.managementCompanion = managementCompanion.getId();
		}
	}
	
	public String getManagementCompanionId() {
		return managementCompanion;
	}

	public void setManagementCompanionId(String managementCompanion) {
		this.managementCompanion = managementCompanion;
	}

	// Reindeer event:
	
	public SlaveJob getSlaveryManagerJobSelected() {
		return slaveryManagerJobSelected;
	}

	public void setSlaveryManagerJobSelected(SlaveJob slaveryManagerJobSelected) {
		this.slaveryManagerJobSelected = slaveryManagerJobSelected;
	}

	public void addHelenaConversationTopic(HelenaConversationTopic topic) {
		helenaConversationTopics.add(topic.toString());
	}
	
	public boolean hasHelenaConversationTopic(HelenaConversationTopic topic) {
		return reindeerEncounteredIDs.contains(topic.toString());
	}
	
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
	
	public int getMurkTfStage(GameCharacter target) {
		if(target.isPlayer()) {
			return murkPlayerTfStage;
		}
		return murkCompanionTfStage;
	}
	
	public void setMurkTfStage(GameCharacter target, int stage) {
		if(target.isPlayer()) {
			this.murkPlayerTfStage = stage;
		} else {
			this.murkCompanionTfStage = stage;
		}
	}

	public Colour getNatalyaCollarColour() {
		return natalyaCollarColour;
	}

	public void setNatalyaCollarColour(Colour natalyaCollarColour) {
		this.natalyaCollarColour = natalyaCollarColour;
	}

	public int getNatalyaPoints() {
		return natalyaPoints;
	}

	public void setNatalyaPoints(int natalyaPoints) {
		this.natalyaPoints = natalyaPoints;
		if(this.natalyaPoints<0) {
			this.natalyaPoints = 0;
		}
	}
	
	public String incrementNatalyaPoints(int increment) {
		setNatalyaPoints(getNatalyaPoints()+increment);
		boolean plural = increment!=1;
		StringBuilder sb = new StringBuilder();
		sb.append("<p style='text-align:center;'>");
			if(increment>0) {
				sb.append("You [style.colourGood(gained "+increment+")] filly point"+(plural?"s":"")+"!");
			} else {
				sb.append("You [style.colourBad(lost "+increment+")] filly point"+(plural?"s":"")+"!");
			}
			sb.append("<br/>");
			Colour colour = DominionExpress.getColourFromPoints();
			sb.append("You now have "+getNatalyaPoints()+" filly points, qualifying you for "+UtilText.generateSingularDeterminer(colour.getName())+" <span style='color:"+colour.toWebHexString()+";'>"+colour.getName()+"</span> choker!");
		sb.append("</p>");
		return sb.toString();
	}
	

	public int getNatalyaWages() {
		return natalyaWages;
	}

	public void setNatalyaWages(int natalyaWages) {
		this.natalyaWages = natalyaWages;
		if(this.natalyaWages<0) {
			this.natalyaWages = 0;
		}
	}
	
	public void incrementNatalyaWages(int increment) {
		setNatalyaWages(getNatalyaWages()+increment);
	}
}
