package com.lilithsthrone.game.dialogue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Kalahari;
import com.lilithsthrone.game.character.npc.dominion.Ralph;
import com.lilithsthrone.game.dialogue.places.dominion.helenaHotel.HelenaConversationTopic;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpFortressDialogue;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.4.4
 * @author Innoxia
 */
public class DialogueFlags implements XMLSaving {
	
	private int muggerDemand1 = 250; // Dominion
	private int muggerDemand2 = 500; // Submission
	private int muggerDemand3 = 750; // Elis
	
	private int prostituteFine = 10_000;
	
	public Set<AbstractDialogueFlagValue> values;
	
	public int ralphDiscount;
	public int scarlettPrice;
	public int eponaStamps;
	
	// Timers:
	public Map<String, Long> savedLongs = new HashMap<>();
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
	private String sadistNatalyaSlave;
	
	// --- Sets: --- //
	
	private Set<String> helenaConversationTopics = new HashSet<>();
	
	// Reindeer event related flags:
	private Set<String> reindeerEncounteredIDs = new HashSet<>();
	private Set<String> reindeerWorkedForIDs = new HashSet<>();
	private Set<String> reindeerFuckedIDs = new HashSet<>();
	
	// Enforcer warehouse guards defeated:
	public Set<String> warehouseDefeatedIDs = new HashSet<>();

//	// Storage tiles checked:
//	public Set<Vector2i> supplierStorageRoomsChecked = new HashSet<>();
	
	
	public DialogueFlags() {
		values = new HashSet<>();

		slaveTrader = null;
		
		managementCompanion = null;
		slaveryManagerJobSelected = SlaveJob.IDLE;
		
//		ralphDiscountStartTime = -1;
//		kalahariBreakStartTime = -1;
//		daddyResetTimer = -1;
//		candiSexTimer = -1;
//		ralphSexTimer = -1;
//		
//		impFortressAlphaDefeatedTime
//			= impFortressDemonDefeatedTime 
//			= impFortressFemalesDefeatedTime
//			= impFortressMalesDefeatedTime
//			= -50000;
		
		helenaSlaveOrderDay = -1;
				
		ralphDiscount = 0;
		
		eponaStamps = 0;
		
		scarlettPrice = 15000;
		
		
		impCitadelImpWave = 0;
		
		murkPlayerTfStage = 0;
		murkCompanionTfStage = 0;
		
		natalyaCollarColour = PresetColour.CLOTHING_BRONZE;
		natalyaPoints = 0;
		sadistNatalyaSlave = "";
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("dialogueFlags");
		parentElement.appendChild(element);
		
//		XMLUtil.createXMLElementWithValue(doc, element, "ralphDiscountStartTime", String.valueOf(ralphDiscountStartTime));
//		XMLUtil.createXMLElementWithValue(doc, element, "kalahariBreakStartTime", String.valueOf(kalahariBreakStartTime));
//		XMLUtil.createXMLElementWithValue(doc, element, "daddyResetTimer", String.valueOf(daddyResetTimer));
//		
//		XMLUtil.createXMLElementWithValue(doc, element, "impFortressAlphaDefeatedTime", String.valueOf(impFortressAlphaDefeatedTime));
//		XMLUtil.createXMLElementWithValue(doc, element, "impFortressDemonDefeatedTime", String.valueOf(impFortressDemonDefeatedTime));
//		XMLUtil.createXMLElementWithValue(doc, element, "impFortressFemalesDefeatedTime", String.valueOf(impFortressFemalesDefeatedTime));
//		XMLUtil.createXMLElementWithValue(doc, element, "impFortressMalesDefeatedTime", String.valueOf(impFortressMalesDefeatedTime));
		
		XMLUtil.createXMLElementWithValue(doc, element, "ralphDiscount", String.valueOf(ralphDiscount));
		XMLUtil.createXMLElementWithValue(doc, element, "scarlettPrice", String.valueOf(scarlettPrice));
		XMLUtil.createXMLElementWithValue(doc, element, "eponaStamps", String.valueOf(eponaStamps));
		XMLUtil.createXMLElementWithValue(doc, element, "helenaSlaveOrderDay", String.valueOf(helenaSlaveOrderDay));


		XMLUtil.createXMLElementWithValue(doc, element, "impCitadelImpWave", String.valueOf(impCitadelImpWave));

		XMLUtil.createXMLElementWithValue(doc, element, "murkPlayerTfStage", String.valueOf(murkPlayerTfStage));
		XMLUtil.createXMLElementWithValue(doc, element, "murkCompanionTfStage", String.valueOf(murkCompanionTfStage));
		
		XMLUtil.createXMLElementWithValue(doc, element, "offspringDialogueTokens", String.valueOf(offspringDialogueTokens));
		XMLUtil.createXMLElementWithValue(doc, element, "slaveTrader", slaveTrader);
		XMLUtil.createXMLElementWithValue(doc, element, "slaveryManagerSlaveSelected", managementCompanion);

		XMLUtil.createXMLElementWithValue(doc, element, "natalyaCollarColour", PresetColour.getIdFromColour(natalyaCollarColour));
		XMLUtil.createXMLElementWithValue(doc, element, "natalyaPoints", String.valueOf(natalyaPoints));
		XMLUtil.createXMLElementWithValue(doc, element, "sadistNatalyaSlave", sadistNatalyaSlave);
		
		Element savedLongsElement = doc.createElement("savedLongs");
		element.appendChild(savedLongsElement);
		for(Entry<String, Long> savedLong : savedLongs.entrySet()) {
			Element save = doc.createElement("save");
			savedLongsElement.appendChild(save);
			save.setAttribute("id", savedLong.getKey());
			save.setTextContent(String.valueOf(savedLong.getValue()));
		}
		
		Element valuesElement = doc.createElement("dialogueValues");
		element.appendChild(valuesElement);
		for(AbstractDialogueFlagValue value : values) {
			XMLUtil.createXMLElementWithValue(doc, valuesElement, "dialogueValue", DialogueFlagValue.getIdFromDialogueFlagValue(value));
		}
		
		
		saveSet(element, doc, helenaConversationTopics, "helenaConversationTopics");
		
		saveSet(element, doc, reindeerEncounteredIDs, "reindeerEncounteredIDs");
		saveSet(element, doc, reindeerWorkedForIDs, "reindeerWorkedForIDs");
		saveSet(element, doc, reindeerFuckedIDs, "reindeerFuckedIDs");
		
		saveSet(element, doc, warehouseDefeatedIDs, "warehouseDefeatedIDs");
		
//		Element supplierStorageRoomsCheckedElement = doc.createElement("supplierStorageRoomsChecked");
//		element.appendChild(supplierStorageRoomsCheckedElement);
//		for(Vector2i value : supplierStorageRoomsChecked) {
//			Element location = doc.createElement("location");
//			supplierStorageRoomsCheckedElement.appendChild(location);
//			XMLUtil.addAttribute(doc, location, "x", String.valueOf(value.getX()));
//			XMLUtil.addAttribute(doc, location, "y", String.valueOf(value.getY()));
//		}
		
		return element;
	}
	
	public static DialogueFlags loadFromXML(Element parentElement, Document doc) {
		DialogueFlags newFlags = new DialogueFlags();
		
		
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
			newFlags.helenaSlaveOrderDay = Integer.valueOf(((Element)parentElement.getElementsByTagName("helenaSlaveOrderDay").item(0)).getAttribute("value"));
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
			if(newFlags.natalyaCollarColour==PresetColour.CLOTHING_STEEL) {
				newFlags.natalyaCollarColour = PresetColour.CLOTHING_BRONZE;
			}
			newFlags.natalyaPoints = Integer.valueOf(((Element)parentElement.getElementsByTagName("natalyaPoints").item(0)).getAttribute("value"));
			newFlags.sadistNatalyaSlave = ((Element)parentElement.getElementsByTagName("sadistNatalyaSlave").item(0)).getAttribute("value");
		} catch(Exception ex) {
		}

		// Load saved longs:
		if(parentElement.getElementsByTagName("savedLongs").item(0)!=null) {
			for(int i=0; i<((Element) parentElement.getElementsByTagName("savedLongs").item(0)).getElementsByTagName("save").getLength(); i++){
				Element e = (Element) ((Element) parentElement.getElementsByTagName("savedLongs").item(0)).getElementsByTagName("save").item(i);
				
				String id = e.getAttribute("id");
				if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.15")) {
					if(id.equals("MACHINES")
							|| id.equals("INTERCOM")
							|| id.equals("BUSINESS")
							|| id.equals("BOUNTY_HUNTERS")) {
						id = "KAY_"+id;
					}
				}
				newFlags.setSavedLong(id, Long.valueOf(e.getTextContent()));
			}
			
		} else { // Support for old timers (pre-version 0.3.9):
			newFlags.setSavedLong(Ralph.RALPH_DISCOUNT_TIMER_ID, Long.valueOf(((Element)parentElement.getElementsByTagName("ralphDiscountStartTime").item(0)).getAttribute("value")));
			
			try {
				newFlags.setSavedLong(Kalahari.KALAHARI_BREAK_TIMER_ID, Long.valueOf(((Element)parentElement.getElementsByTagName("kalahariBreakStartTime").item(0)).getAttribute("value")));
			} catch(Exception ex) {
			}
			try {
				newFlags.setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Long.valueOf(((Element)parentElement.getElementsByTagName("daddyResetTimer").item(0)).getAttribute("value")));
			} catch(Exception ex) {
			}
			try {
				if(!Main.isVersionOlderThan(Game.loadingVersion, "0.2.11.5")) {
					newFlags.setSavedLong(ImpFortressDialogue.FORTRESS_ALPHA_CLEAR_TIMER_ID, Long.valueOf(((Element)parentElement.getElementsByTagName("impFortressAlphaDefeatedTime").item(0)).getAttribute("value")));
					newFlags.setSavedLong(ImpFortressDialogue.FORTRESS_FEMALES_CLEAR_TIMER_ID, Long.valueOf(((Element)parentElement.getElementsByTagName("impFortressFemalesDefeatedTime").item(0)).getAttribute("value")));
					newFlags.setSavedLong(ImpFortressDialogue.FORTRESS_MALES_CLEAR_TIMER_ID, Long.valueOf(((Element)parentElement.getElementsByTagName("impFortressMalesDefeatedTime").item(0)).getAttribute("value")));
				}
			} catch(Exception ex) {
			}
		}
		
		// Load flags:
		for(int i=0; i<((Element) parentElement.getElementsByTagName("dialogueValues").item(0)).getElementsByTagName("dialogueValue").getLength(); i++){
			Element e = (Element) ((Element) parentElement.getElementsByTagName("dialogueValues").item(0)).getElementsByTagName("dialogueValue").item(i);
			
			try {
				String flag = e.getAttribute("value");
				if(flag.equalsIgnoreCase("punishedByAlexa")) {
					newFlags.values.add(DialogueFlagValue.punishedByHelena);
				} else {
					AbstractDialogueFlagValue flagValue = DialogueFlagValue.getDialogueFlagValueFromId(flag);
					if(flagValue!=null) {
						newFlags.values.add(flagValue);
					}
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
		
//		if(parentElement.getElementsByTagName("supplierStorageRoomsChecked").item(0)!=null) {
//			for(int i=0; i<((Element) parentElement.getElementsByTagName("supplierStorageRoomsChecked").item(0)).getElementsByTagName("location").getLength(); i++){
//				Element e = (Element) ((Element) parentElement.getElementsByTagName("supplierStorageRoomsChecked").item(0)).getElementsByTagName("location").item(i);
//				
//				newFlags.supplierStorageRoomsChecked.add(
//						new Vector2i(
//								Integer.valueOf(e.getAttribute("x")),
//								Integer.valueOf(e.getAttribute("y"))));
//			}
//		}

		return newFlags;
	}
	
	private static void saveSet(Element parentElement, Document doc, Set<String> set, String title) {
		Element valuesElement = doc.createElement(title);
		parentElement.appendChild(valuesElement);
		for(String value : set) {
			XMLUtil.createXMLElementWithValue(doc, valuesElement, "value", value.toString());
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

	public void applyTimePassingResets(int startHour, int hoursPassed) {
		for(AbstractDialogueFlagValue flag : new HashSet<>(values)) {
			if(flag.getResetHour()>-1) {
				if((startHour<flag.getResetHour() && startHour+hoursPassed>=flag.getResetHour())
						|| ((startHour-24)+hoursPassed>=flag.getResetHour())) {
					values.remove(flag);
				}
			}
		}
	}

	public boolean hasFlag(AbstractDialogueFlagValue flag) {
		return values.contains(flag);
	}

	public boolean hasFlag(String flagId) {
		return values.contains(DialogueFlagValue.getDialogueFlagValueFromId(flagId));
	}

	public void setFlag(String flagId, boolean flagMarker) {
		if(flagMarker) {
			values.add(DialogueFlagValue.getDialogueFlagValueFromId(flagId));
		} else {
			values.remove(DialogueFlagValue.getDialogueFlagValueFromId(flagId));
		}
	}
	
	public void setFlag(AbstractDialogueFlagValue flag, boolean flagMarker) {
		if(flagMarker) {
			values.add(flag);
		} else {
			values.remove(flag);
		}
	}
	
	public void setSavedLong(String id, long value) {
		savedLongs.put(id, value);
	}
	
	public void setSavedLong(String id, String value) {
		try {
			long valueLong = Long.valueOf(value);
			setSavedLong(id, valueLong);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	

	/**
	 * @return Increments the long saved to this id. Sets to -1 before incrementing if there was no entry found.
	 */
	public void incrementSavedLong(String id, long increment) {
		savedLongs.put(id, getSavedLong(id)+increment);
	}

	public boolean hasSavedLong(String id) {
		return savedLongs.containsKey(id);
	}

	public void removeSavedLong(String id) {
		savedLongs.remove(id);
	}
	
	/**
	 * @return The long saved to this id. Sets and returns -1 if there was no entry found.
	 */
	public long getSavedLong(String id) {
		savedLongs.putIfAbsent(id, -1l);
		return savedLongs.get(id);
	}
	
	public int getMuggerDemand1() {
		return muggerDemand1;
	}

	public int getMuggerDemand2() {
		return muggerDemand2;
	}

	public int getMuggerDemand3() {
		return muggerDemand3;
	}

	public int getProstituteFine() {
		return prostituteFine;
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
			Util.logGetNpcByIdError("getManagementCompanion()", managementCompanion);
			//e.printStackTrace();
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
		return helenaConversationTopics.contains(topic.toString());
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
				sb.append("You [style.colourGood(gained)] [style.boldPink("+increment+")] [style.colourPinkLight(filly point"+(plural?"s":"")+")]!");
			} else {
				sb.append("You [style.colourBad(lost)] [style.boldPink("+(-increment)+")] [style.colourPinkLight(filly point"+(plural?"s":"")+")]!");
			}
			sb.append("<br/>You now have [style.boldPink("+getNatalyaPoints()+")] [style.colourPinkLight(filly points)]!");
		sb.append("</p>");
		return sb.toString();
	}
	
	public String getSadistNatalyaSlave() {
		return sadistNatalyaSlave;
	}

	public void setSadistNatalyaSlave(String sadistNatalyaSlave) {
		this.sadistNatalyaSlave = sadistNatalyaSlave;
	}
}
