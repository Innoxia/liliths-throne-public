package com.lilithsthrone.game.sex.managers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.controller.xmlParsing.XMLMissingTagException;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotManager;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionManager;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * A SexManger which has been loaded from an external xml file.
 * 
 * @since 0.4.1
 * @version 0.4.1
 * @author Innoxia
 */
public class SexManagerExternal extends SexManagerDefault {

	private boolean mod = false;
	private String author;
	
	private String titleString;
	private String title;

	private String consensualString;
	private boolean consensual;

	private String subHasEqualControlString;
	private boolean subHasEqualControl;
	
	private String canSkipSexString;

	private String washingSceneString;
	private boolean washingScene;

	public String sadisticActionsAllowedString;
	public boolean sadisticActionsAllowed;
	
	private String canItemsBeUsedString;
	private boolean canItemsBeUsed;

	private String exposingReactionsString;
	private boolean exposingReactions;
	
	private List<AbstractSexPosition> positionsAllowed;
	private List<String> positionsAllowedIds;
	private boolean positionsExclusive;

	private Map<String, CharacterBehaviour> characterBehaviours;
	private boolean characterBehavioursSetUp;
	
	private Map<GameCharacter, List<SexType>> sexTypesBannedMap;
	private Map<GameCharacter, List<SexAreaInterface>> areasBannedMap;
	
	// Public sex:
	
	private boolean publicSexOverride;
	private String publicSexString;
	private boolean publicSex;
	private String startingPublicSexDescription;
	private List<String> randomPublicSexDescriptions;
	private boolean randomPublicDescriptionsEnabled;
	
	
	private class DirtyTalkLine {
		public SexAreaInterface performingConditional;
		public SexAreaInterface takingConditional;
		public boolean performingAnyPenetrationConditional;
		public boolean takingAnyPenetrationConditional;
		public boolean applySpeechEffects;
		public String content;
		
		public DirtyTalkLine(SexAreaInterface performingConditional, SexAreaInterface takingConditional, boolean performingAnyPenetrationConditional, boolean takingAnyPenetrationConditional, boolean applySpeechEffects, String content) {
			this.performingConditional = performingConditional;
			this.takingConditional = takingConditional;
			this.performingAnyPenetrationConditional = performingAnyPenetrationConditional;
			this.takingAnyPenetrationConditional = takingAnyPenetrationConditional;
			this.applySpeechEffects = applySpeechEffects;
			this.content = content;
		}
		
		/**
		 * Assumes that there has already been a check to make sure that the speaker's sex target is the correct character for this DirtyTalkLine class.
		 */
		public boolean isConditionalMet(GameCharacter speaker) {
			if(!Main.game.isInSex() || !Main.sex.getAllParticipants().contains(speaker)) {
				return false;
			}
			GameCharacter target = Main.sex.getTargetedPartner(speaker);
			if(performingAnyPenetrationConditional) {
				boolean penetrationFound = false;
				for(Entry<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> entry : Main.sex.getOngoingActionsMap(speaker).entrySet()) {
					if(entry.getKey().isPenetration() && ((SexAreaPenetration)entry.getKey()).isTakesVirginity()) {
						for(GameCharacter targetOfPenetration : entry.getValue().keySet()) {
							if(targetOfPenetration.equals(target)) {
								penetrationFound = true;
								break;
							}
						}
					}
				}
				if(!penetrationFound) {
					return false;
				}
			}
			if(takingAnyPenetrationConditional) {
				boolean penetrationFound = false;
				for(Entry<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> entry : Main.sex.getOngoingActionsMap(target).entrySet()) {
					if(entry.getKey().isPenetration() && ((SexAreaPenetration)entry.getKey()).isTakesVirginity()) {
						for(GameCharacter targetOfPenetration : entry.getValue().keySet()) {
							if(targetOfPenetration.equals(speaker)) {
								penetrationFound = true;
								break;
							}
						}
					}
				}
				if(!penetrationFound) {
					return false;
				}
			}
			if(performingConditional!=null
					&& (!Main.sex.getOngoingActionsMap(speaker).containsKey(performingConditional)
							|| !Main.sex.getOngoingActionsMap(speaker).get(performingConditional).containsKey(target))) {
				return false;
			}
			if(takingConditional!=null
					&& (!Main.sex.getOngoingActionsMap(target).containsKey(takingConditional)
							|| !Main.sex.getOngoingActionsMap(target).get(takingConditional).containsKey(speaker))) {
				return false;
			}
			
			return true;
		}
		
		public String getParsedContent(GameCharacter speaker) {
			if(!Main.game.isInSex() || !Main.sex.getAllParticipants().contains(speaker)) {
				return "";
			}
			GameCharacter target = Main.sex.getTargetedPartner(speaker);
			if(applySpeechEffects) {
				return UtilText.parse(speaker, target, "[npc.speech("+content+")]");
			} else {
				return UtilText.parse(speaker, target, "[npc.speechNoExtraEffects("+content+")]");
			}
		}
	}
	
	/**
	 * Class for handling custom character information during sex scenes.
	 * Works by storing the raw Strings drawn from an external sex manager xml file, then parsing most of those Strings in the initCharacterBehaviour method.
	 * Parsing is handled in this way due to concerns over performance when repeatedly parsing Strings, so as many as possible are pre-parsed in this init method.
	 */
	private class CharacterBehaviour {
		// Basic booleans for pre-parsing:
		
		public String canStopSex;
		public boolean canStopSexBool;
		
		public String canChangePositions;
		public boolean canChangePositionsBool;
		
		public String endSexAffectionChanges;
		public boolean endSexAffectionChangesBool;

		public String showStartingExposedDescriptions;
		public boolean showStartingExposedDescriptionsBool;

		public String canSelfTransform;
		public boolean canSelfTransformBool;

		public String hidden;
		public boolean hiddenBool;
		
		public String sexClothingEquippable;
		public boolean sexClothingEquippableBool;

		public String selfClothingRemoval;
		public boolean selfClothingRemovalBool;

		public String startNaked;
		public boolean startNakedBool;

		public List<String> disabledForceCreampieIds;
		
		
		// Booleans which need to be parsed on every check:
		
		public String partnerClothingRemoval;
		
		
		// Basic other values for pre-parsing:
		
		public boolean sexPaceLocked;
		public String sexPace;
		public SexPace sexPaceParsed;

		public String control;
		public SexControl controlParsed;

		public String orgasmBehaviour;
		public OrgasmBehaviour orgasmBehaviourParsed;
		
		
		// Other values which need to be parsed on every check:

		public Map<String, String> orgasmCumTargets;

		/** Maps orgasming character id to a Map of targeted character id to behaviour */
		public Map<String, Map<String, String>> orgasmEncourageBehaviours;
		
		// Slots:
		public boolean slotsExcluded = false;
		public List<String> slotsAvailableIds = null;
		public List<SexSlot> slotsAvailable = null;
		
		// Expose at start:
		public boolean preferRemoval = false;
		public List<String> exposeAtStartIds;
		public List<CoverableArea> exposeAtStart;

		// Concealed slots:
		public Map<String, List<String>> concealedSlotIds;
		public Map<String, List<InventorySlot>> concealedSlots;

		// Preferences:
		/** Maps target id to a Value of performing-targeted area IDs */
		public Map<String, Value<String, String>> foreplayPreferenceIds;
		public Map<GameCharacter, SexType> foreplayPreferences;
		
		/** Maps target id to a Value of performing-targeted area IDs */
		public Map<String, Value<String, String>> sexPreferenceIds;
		public Map<GameCharacter, SexType> sexPreferences;
		
		// SexTypes banned:
		/** Maps performing area IDs to targeted area IDs */
		public Map<String, String> sexTypesBannedIds;
		public List<SexType> sexTypesBanned;
		
		// Areas blocked:
		public boolean areaBansApplyToSelf;
		public List<String> areasBannedIds;
		public List<SexAreaInterface> areasBanned;
		
		public String stopSexCondition;
		
		// Dirty talk:
		
		public boolean additionToDefaultDirtyTalk = true;
		public boolean dirtyTalkInitCompleted = false;
		public Map<String, List<DirtyTalkLine>> dirtyTalk;
		
		public boolean roughTalkInitCompleted = false;
		public boolean additionToDefaultRoughTalk = true;
		public Map<String, List<DirtyTalkLine>> roughTalk;
		
		public boolean submissiveTalkInitCompleted = false;
		public boolean additionToDefaultSubmissiveTalk = true;
		public Map<String, List<DirtyTalkLine>> submissiveTalk;
		
		public String preferredTarget;
		
		public List<String> sexClassesIds;
		public List<SexActionInterface> sexClasses;

		public boolean bannedOrgasmCumTargetsInitCompleted = false;
		public Map<String, List<OrgasmCumTarget>> bannedOrgasmCumTargets;
		
		/**
		 * Handles parsing of the String variables, dealing with default values in the process.
		 * Should be called prior to SexManager init.
		 */
		public void initCharacterBehaviour() {
			// Booleans:
			canStopSexBool = initBool(canStopSex, true);
			canChangePositionsBool = initBool(canChangePositions, true);
			endSexAffectionChangesBool = initBool(endSexAffectionChanges, true);
			showStartingExposedDescriptionsBool = initBool(showStartingExposedDescriptions, true);
			canSelfTransformBool = initBool(canSelfTransform, true);
			hiddenBool = initBool(hidden, false);
			sadisticActionsAllowed = initBool(sadisticActionsAllowedString, true);
			sexClothingEquippableBool = initBool(sexClothingEquippable, true);
			selfClothingRemovalBool = initBool(selfClothingRemoval, true);
			startNakedBool = initBool(startNaked, false);
			
			// Other values:
			if(sexPace!=null && !sexPace.isEmpty()) {
				sexPaceParsed = SexPace.valueOf(sexPace);
			}
			if(control!=null && !control.isEmpty()) {
				controlParsed = SexControl.valueOf(control);
			}
			if(orgasmBehaviour!=null && !orgasmBehaviour.isEmpty()) {
				orgasmBehaviourParsed = OrgasmBehaviour.valueOf(orgasmBehaviour);
			}
			
			
			// Slots:
			if(slotsAvailableIds==null || slotsAvailableIds.isEmpty()) {
				slotsAvailable = SexSlotManager.getAllSexSlots();
				
			} else {
				if(slotsExcluded) {
					slotsAvailable = SexSlotManager.getAllSexSlots();
					for(String id : slotsAvailableIds) {
						if(!id.isEmpty()) {
							slotsAvailable.remove(SexSlotManager.getSexSlotFromId(id));
						}
					}
					
				} else {
					slotsAvailable = new ArrayList<>();
					for(String id : slotsAvailableIds) {
						if(!id.isEmpty()) {
							slotsAvailable.add(SexSlotManager.getSexSlotFromId(id));
						}
					}
				}
			}
			
			// Expose at start:
			exposeAtStart = new ArrayList<>();
			if(exposeAtStartIds!=null) {
				for(String id : exposeAtStartIds) {
					String areaParsed = UtilText.parse(id).trim();
					if(!areaParsed.isEmpty()) {
						exposeAtStart.add(CoverableArea.valueOf(areaParsed));
					}
				}
			}

			// Concealed slots:
			concealedSlots = new HashMap<>();
			if(concealedSlotIds!=null) {
				for(Entry<String, List<String>> entry : concealedSlotIds.entrySet()) {
					List<InventorySlot> slots = new ArrayList<>();
					for(String id : entry.getValue()) {
						String slotParsed = UtilText.parse(id).trim();
						if(!slotParsed.isEmpty()) {
							slots.add(InventorySlot.valueOf(slotParsed));
						}
					}
					concealedSlots.put(UtilText.findFirstCharacterFromParserTarget(entry.getKey()).getId(), slots);
				}
			}
			
			// Preferences:
			foreplayPreferences = new HashMap<>();
			if(foreplayPreferenceIds!=null) {
				for(Entry<String, Value<String, String>> entry : foreplayPreferenceIds.entrySet()) {
					foreplayPreferences.put(
							UtilText.findFirstCharacterFromParserTarget(entry.getKey()),
							new SexType(SexParticipantType.NORMAL,
									getSexArea(UtilText.parse(entry.getValue().getKey()).trim()),
									getSexArea(UtilText.parse(entry.getValue().getValue()).trim())));
				}
			}
			
			sexPreferences = new HashMap<>();
			if(sexPreferenceIds!=null) {
				for(Entry<String, Value<String, String>> entry : sexPreferenceIds.entrySet()) {
					sexPreferences.put(
							UtilText.findFirstCharacterFromParserTarget(entry.getKey()),
							new SexType(SexParticipantType.NORMAL,
									getSexArea(UtilText.parse(entry.getValue().getKey()).trim()),
									getSexArea(UtilText.parse(entry.getValue().getValue()).trim())));
				}
			}
			
			// Sex types banned:
			sexTypesBanned = new ArrayList<>();
			if(sexTypesBannedIds!=null) {
				for(Entry<String, String> entry : sexTypesBannedIds.entrySet()) {
					sexTypesBanned.add(
							new SexType(SexParticipantType.NORMAL,
									getSexArea(UtilText.parse(entry.getKey()).trim()),
									getSexArea(UtilText.parse(entry.getValue()).trim())));
				}
			}

			// Areas banned:
			areasBanned = new ArrayList<>();
			if(areasBannedIds!=null) {
				for(String area : areasBannedIds) {
					String areaParsed = UtilText.parse(area).trim();
					if(!areaParsed.isEmpty()) {
						areasBanned.add(getSexArea(areaParsed));
					}
				}
			}
			
			// Forced creampies disabled:
			List<String> rawIds = new ArrayList<>(disabledForceCreampieIds);
			disabledForceCreampieIds = new ArrayList<>();
			for(String s : rawIds) {
				String parsedId = UtilText.parse(s).trim();
				if(!parsedId.isEmpty()) {
					disabledForceCreampieIds.add(UtilText.findFirstCharacterFromParserTarget(parsedId).getId());
				}
			}
			
			// Converting parser ids to actual ids:
			
			Map<String, String> tempCumTargetMap = new HashMap<>(orgasmCumTargets);
			orgasmCumTargets = new HashMap<>();
			for(Entry<String, String> entry : tempCumTargetMap.entrySet()) {
				orgasmCumTargets.put(UtilText.findFirstCharacterFromParserTarget(entry.getKey()).getId(), entry.getValue());
			}

			Map<String, Map<String, String>> tempOrgasmEncourageMap = new HashMap<>(orgasmEncourageBehaviours);
			orgasmEncourageBehaviours = new HashMap<>();
			for(Entry<String, Map<String, String>> entry : tempOrgasmEncourageMap.entrySet()) {
				String keyCharacterId = UtilText.findFirstCharacterFromParserTarget(entry.getKey()).getId();
				orgasmEncourageBehaviours.put(keyCharacterId, new HashMap<>());
				for(Entry<String, String> innerEntry : entry.getValue().entrySet()) {
					orgasmEncourageBehaviours.get(keyCharacterId).put(UtilText.findFirstCharacterFromParserTarget(innerEntry.getKey()).getId(), innerEntry.getValue());
				}
			}
			
			if(!dirtyTalkInitCompleted) {
				Map<String, List<DirtyTalkLine>> tempDirtyTalkMap = new HashMap<>(dirtyTalk);
				dirtyTalk = new HashMap<>();
				for(Entry<String, List<DirtyTalkLine>> entry : tempDirtyTalkMap.entrySet()) {
					dirtyTalk.put(UtilText.findFirstCharacterFromParserTarget(entry.getKey()).getId(), entry.getValue());
				}
				dirtyTalkInitCompleted = true;
			}
			if(!roughTalkInitCompleted) {
				Map<String, List<DirtyTalkLine>> tempDirtyTalkMap = new HashMap<>(roughTalk);
				roughTalk = new HashMap<>();
				for(Entry<String, List<DirtyTalkLine>> entry : tempDirtyTalkMap.entrySet()) {
					roughTalk.put(UtilText.findFirstCharacterFromParserTarget(entry.getKey()).getId(), entry.getValue());
				}
				roughTalkInitCompleted = true;
			}
			if(!submissiveTalkInitCompleted) {
				Map<String, List<DirtyTalkLine>> tempDirtyTalkMap = new HashMap<>(submissiveTalk);
				submissiveTalk = new HashMap<>();
				for(Entry<String, List<DirtyTalkLine>> entry : tempDirtyTalkMap.entrySet()) {
					submissiveTalk.put(UtilText.findFirstCharacterFromParserTarget(entry.getKey()).getId(), entry.getValue());
				}
				submissiveTalkInitCompleted = true;
			}
			
			sexClasses = new ArrayList<>();
			if(sexClassesIds!=null) {
				for(String id : sexClassesIds) {
					sexClasses.add(SexActionManager.getSexActionFromId(id));
				}
			}

			if(!bannedOrgasmCumTargetsInitCompleted && bannedOrgasmCumTargets!=null) {
				Map<String, List<OrgasmCumTarget>> tempMap = new HashMap<>(bannedOrgasmCumTargets);
				bannedOrgasmCumTargets = new HashMap<>();
				for(Entry<String, List<OrgasmCumTarget>> entry : tempMap.entrySet()) {
					bannedOrgasmCumTargets.put(UtilText.findFirstCharacterFromParserTarget(entry.getKey()).getId(), entry.getValue());
				}
				bannedOrgasmCumTargetsInitCompleted = true;
			}
		}
		
		private boolean initBool(String input, boolean defaultValue) {
			if(input==null || input.isEmpty()) {
				return defaultValue;
			}
			return Boolean.valueOf(UtilText.parse(input).trim());
		}
		
		private SexAreaInterface getSexArea(String areaId) {
			if(areaId.startsWith("ORIFICE_")) {
				return SexAreaOrifice.valueOf(areaId.replace("ORIFICE_", ""));
			} else {
				return SexAreaPenetration.valueOf(areaId.replace("PENETRATION_", ""));
			}
		}
		
		// Pre-parsed booleans:
		
		public boolean isCanStopSexBool() {
			return canStopSexBool;
		}

		public boolean isCanChangePositionsBool() {
			return canChangePositionsBool;
		}
		
		public boolean isEndSexAffectionChangesBool() {
			return endSexAffectionChangesBool;
		}

		public boolean isShowStartingExposedDescriptionsBool() {
			return showStartingExposedDescriptionsBool;
		}

		public boolean isCanSelfTransformBool() {
			return canSelfTransformBool;
		}

		public boolean isHiddenBool() {
			return hiddenBool;
		}

		public boolean isSexClothingEquippableBool() {
			return sexClothingEquippableBool;
		}

		public boolean isSelfClothingRemovalBool() {
			return selfClothingRemovalBool;
		}

		public boolean isStartNakedBool() {
			return startNakedBool;
		}
		
		public boolean isForceCreampieDisabled(GameCharacter orgasmingPartner) {
			return disabledForceCreampieIds.contains(orgasmingPartner.getId());
		}
		
		// Parse on check booleans:
		
		public boolean isPartnerClothingRemoval(GameCharacter target, AbstractClothing clothing) {
			UtilText.setClothingTypeForParsing(clothing==null?null:clothing.getClothingType());
			
			return Boolean.valueOf(UtilText.parse(target, partnerClothingRemoval).trim());
		}
		
		// Pre-parsed other values:
		
		public SexPace getSexPace() {
			return sexPaceParsed;
		}

		public boolean isSexPaceLocked() {
			return sexPaceLocked;
		}

		public SexControl getControl() {
			return controlParsed;
		}

		public OrgasmBehaviour getOrgasmBehaviour() {
			return orgasmBehaviourParsed;
		}

		public List<SexSlot> getSlotsAvailable() {
			return slotsAvailable;
		}

		public boolean isPreferExposingRemoval() {
			return preferRemoval;
		}
		
		public List<CoverableArea> getAreasExposedOnStart() {
			return exposeAtStart;
		}

		public List<InventorySlot> getConcealedSlots(GameCharacter viewer) {
			List<InventorySlot> slots = concealedSlots.get(viewer.getId());
			if(slots==null) {
				return new ArrayList<>();
			}
			return slots;
		}
		
		public SexType getForeplayPreference(GameCharacter partner) {
			if(foreplayPreferences!=null) {
				return foreplayPreferences.get(partner);
			}
			return null;
		}
		
		public SexType getSexPreference(GameCharacter partner) {
			if(sexPreferences!=null) {
				return sexPreferences.get(partner);
			}
			return null;
		}
		
		public List<SexType> getSexTypesBanned() {
			return sexTypesBanned;
		}
		
		public boolean isAreaBansApplyToSelf() {
			return areaBansApplyToSelf;
		}
		
		public List<SexAreaInterface> getAreasBanned() {
			return areasBanned;
		}
		
		// Parse on check other values:

		public OrgasmCumTarget getOrgasmCumTarget(GameCharacter targetedCharacter) {
			if(orgasmCumTargets.containsKey(targetedCharacter.getId())) {
				return OrgasmCumTarget.valueOf(UtilText.parse(orgasmCumTargets.get(targetedCharacter.getId())).trim());
			}
			return null;
		}

		public OrgasmEncourageBehaviour getOrgasmEncourageBehaviour(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			if(orgasmEncourageBehaviours.containsKey(orgasmingCharacter.getId())) {
				if(orgasmEncourageBehaviours.get(orgasmingCharacter.getId()).containsKey(targetedCharacter.getId())) {
					return OrgasmEncourageBehaviour.valueOf(UtilText.parse(orgasmEncourageBehaviours.get(orgasmingCharacter.getId()).get(targetedCharacter.getId())).trim());
				}
			}
			return OrgasmEncourageBehaviour.DEFAULT;
		}
		
		public boolean isWantingToStopSex() {
			return Boolean.valueOf(UtilText.parse(stopSexCondition).trim());
		}
		
	}
	
	public SexManagerExternal(File XMLFile, String author, boolean mod) {
		super(null, null, null);
		
		if (XMLFile.exists()) {
			try {
				Document doc = Main.getDocBuilder().parse(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element sexManagerElement = Element.getDocumentRootElement(XMLFile); // Loads the document and returns the root element - in sexManager files it's <sexManager>

				this.mod = mod;
				this.author = author;
				
				if(elementPresentAndNotEmpty(sexManagerElement, "title")) {
					titleString = sexManagerElement.getMandatoryFirstOf("title").getTextContent();
				} else {
					titleString = "";
				}

				if(elementPresentAndNotEmpty(sexManagerElement, "consensual")) {
					consensualString = sexManagerElement.getMandatoryFirstOf("consensual").getTextContent();
				} else {
					consensualString = "true";
				}

				if(elementPresentAndNotEmpty(sexManagerElement, "subHasEqualControl")) {
					subHasEqualControlString = sexManagerElement.getMandatoryFirstOf("subHasEqualControl").getTextContent();
				} else {
					subHasEqualControlString = "true";
				}

				if(elementPresentAndNotEmpty(sexManagerElement, "canSkipSex")) {
					canSkipSexString = sexManagerElement.getMandatoryFirstOf("canSkipSex").getTextContent();
				} else {
					canSkipSexString = "true";
				}

				if(elementPresentAndNotEmpty(sexManagerElement, "washingScene")) {
					washingSceneString = sexManagerElement.getMandatoryFirstOf("washingScene").getTextContent();
				} else {
					washingSceneString = "true";
				}

				if(elementPresentAndNotEmpty(sexManagerElement, "sadisticActionsAllowed")) {
					sadisticActionsAllowedString = sexManagerElement.getMandatoryFirstOf("sadisticActionsAllowed").getTextContent();
				} else {
					sadisticActionsAllowedString = "true";
				}
				
				if(elementPresentAndNotEmpty(sexManagerElement, "canItemsBeUsed")) {
					canItemsBeUsedString = sexManagerElement.getMandatoryFirstOf("canItemsBeUsed").getTextContent();
				} else {
					canItemsBeUsedString = "true";
				}

				if(elementPresentAndNotEmpty(sexManagerElement, "exposingReactions")) {
					exposingReactionsString = sexManagerElement.getMandatoryFirstOf("exposingReactions").getTextContent();
				} else {
					exposingReactionsString = "true";
				}
				
				positionsAllowedIds = new ArrayList<>();
				positionsExclusive = false;
				if(sexManagerElement.getOptionalFirstOf("positionsAvailable").isPresent()) {
					Element positionsElement = sexManagerElement.getMandatoryFirstOf("positionsAvailable");
					positionsExclusive = Boolean.valueOf(positionsElement.getAttribute("exclusive"));
					
					for(Element positionElement : positionsElement.getAllOf("position")) {
						positionsAllowedIds.add(positionElement.getTextContent());
					}
				}
				
				if(sexManagerElement.getOptionalFirstOf("publicSex").isPresent()) {
					Element publicSexElement = sexManagerElement.getMandatoryFirstOf("publicSex");

					if(elementPresentAndNotEmpty(publicSexElement, "isPublic")) {
						publicSexOverride = true;
						publicSexString = publicSexElement.getMandatoryFirstOf("isPublic").getTextContent();
					} else {
						publicSexOverride = false;
					}
					
					if(elementPresentAndNotEmpty(publicSexElement, "startingDescription")) {
						startingPublicSexDescription = publicSexElement.getMandatoryFirstOf("startingDescription").getTextContent();
					} else {
						startingPublicSexDescription = "";
					}
					
					randomPublicSexDescriptions = new ArrayList<>();
					randomPublicDescriptionsEnabled = true;
					if(publicSexElement.getOptionalFirstOf("randomDescriptions").isPresent()) {
						if(!publicSexElement.getMandatoryFirstOf("randomDescriptions").getAttribute("enabled").isEmpty()) {
							randomPublicDescriptionsEnabled = Boolean.valueOf(publicSexElement.getMandatoryFirstOf("randomDescriptions").getAttribute("enabled"));
						}
						for(Element descriptionElement : publicSexElement.getMandatoryFirstOf("randomDescriptions").getAllOf("description")) {
							randomPublicSexDescriptions.add(descriptionElement.getTextContent());
						}
					}
				}
				
				// Setting up the character information map:
				characterBehaviours = new HashMap<>();
				if(sexManagerElement.getOptionalFirstOf("characterInformationContainer").isPresent()) {
					for(Element characterElement : sexManagerElement.getMandatoryFirstOf("characterInformationContainer").getAllOf("characterInformation")) {
						String id = characterElement.getMandatoryFirstOf("characterId").getTextContent();
						CharacterBehaviour behaviour = new CharacterBehaviour();
						
						// Added in same order as in xml file:
						
						if(characterElement.getOptionalFirstOf("slotsAvailable").isPresent()) {
							if(Boolean.valueOf(characterElement.getMandatoryFirstOf("slotsAvailable").getAttribute("excluded"))) {
								behaviour.slotsExcluded = true;
							}
							behaviour.slotsAvailableIds = new ArrayList<>();
							for(Element slotElement : characterElement.getMandatoryFirstOf("slotsAvailable").getAllOf("slot")) {
								behaviour.slotsAvailableIds.add(slotElement.getTextContent());
							}
						}
						
						if(characterElement.getOptionalFirstOf("canStopSex").isPresent()) {
							behaviour.canStopSex = characterElement.getMandatoryFirstOf("canStopSex").getTextContent();
						}
						
						if(characterElement.getOptionalFirstOf("canChangePositions").isPresent()) {
							behaviour.canChangePositions = characterElement.getMandatoryFirstOf("canChangePositions").getTextContent();
						}
						
						if(characterElement.getOptionalFirstOf("endSexAffectionChanges").isPresent()) {
							behaviour.endSexAffectionChanges = characterElement.getMandatoryFirstOf("endSexAffectionChanges").getTextContent();
						}
						
						if(characterElement.getOptionalFirstOf("showStartingExposedDescriptions").isPresent()) {
							behaviour.showStartingExposedDescriptions = characterElement.getMandatoryFirstOf("showStartingExposedDescriptions").getTextContent();
						}

						if(characterElement.getOptionalFirstOf("sexPace").isPresent()) {
							behaviour.sexPaceLocked = Boolean.valueOf(characterElement.getMandatoryFirstOf("sexPace").getAttribute("locked"));
							behaviour.sexPace = characterElement.getMandatoryFirstOf("sexPace").getTextContent();
						}
						
						if(characterElement.getOptionalFirstOf("canSelfTransform").isPresent()) {
							behaviour.canSelfTransform = characterElement.getMandatoryFirstOf("canSelfTransform").getTextContent();
						}
						
						if(characterElement.getOptionalFirstOf("hidden").isPresent()) {
							behaviour.hidden = characterElement.getMandatoryFirstOf("hidden").getTextContent();
						}
						
						if(characterElement.getOptionalFirstOf("control").isPresent()) {
							behaviour.control = characterElement.getMandatoryFirstOf("control").getTextContent();
						}

						if(characterElement.getOptionalFirstOf("sexClothingEquippable").isPresent()) {
							behaviour.sexClothingEquippable = characterElement.getMandatoryFirstOf("sexClothingEquippable").getTextContent();
						}

						if(characterElement.getOptionalFirstOf("selfClothingRemoval").isPresent()) {
							behaviour.selfClothingRemoval = characterElement.getMandatoryFirstOf("selfClothingRemoval").getTextContent();
						}

						if(characterElement.getOptionalFirstOf("partnerClothingRemoval").isPresent()) {
							behaviour.partnerClothingRemoval = characterElement.getMandatoryFirstOf("partnerClothingRemoval").getTextContent();
						}

						if(characterElement.getOptionalFirstOf("startNaked").isPresent()) {
							behaviour.startNaked = characterElement.getMandatoryFirstOf("startNaked").getTextContent();
						}
						
						if(characterElement.getOptionalFirstOf("exposeAtStart").isPresent()) {
							behaviour.preferRemoval = Boolean.valueOf(characterElement.getMandatoryFirstOf("exposeAtStart").getAttribute("preferRemoval"));
							behaviour.exposeAtStartIds = new ArrayList<>();
							for(Element areaElement : characterElement.getMandatoryFirstOf("exposeAtStart").getAllOf("coverableArea")) {
								behaviour.exposeAtStartIds.add(areaElement.getTextContent());
							}
						}
						
						behaviour.disabledForceCreampieIds = new ArrayList<>();
						if(characterElement.getOptionalFirstOf("forcedCreampiesDisabled").isPresent()) {
							for(Element characterIdElement : characterElement.getMandatoryFirstOf("forcedCreampiesDisabled").getAllOf("character")) {
								behaviour.disabledForceCreampieIds.add(characterIdElement.getTextContent());
							}
						}
						
						if(characterElement.getOptionalFirstOf("orgasmBehaviour").isPresent()) {
							behaviour.orgasmBehaviour = characterElement.getMandatoryFirstOf("orgasmBehaviour").getTextContent();
						}
						
						if(characterElement.getOptionalFirstOf("orgasmCumTarget").isPresent()) {
							behaviour.orgasmCumTargets = new HashMap<>();
							for(Element targetElement : characterElement.getMandatoryFirstOf("orgasmCumTarget").getAllOf("target")) {
								behaviour.orgasmCumTargets.put(targetElement.getAttribute("id"), characterElement.getTextContent());
							}
						}
						
						if(characterElement.getOptionalFirstOf("orgasmEncourageBehaviours").isPresent()) {
							behaviour.orgasmEncourageBehaviours = new HashMap<>();
							for(Element behaviourElement : characterElement.getMandatoryFirstOf("orgasmEncourageBehaviours").getAllOf("behaviour")) {
								behaviour.orgasmEncourageBehaviours.putIfAbsent(behaviourElement.getAttribute("orgasming"), new HashMap<>());
								behaviour.orgasmEncourageBehaviours.get(behaviourElement.getAttribute("orgasming")).put(behaviourElement.getAttribute("target"), behaviourElement.getTextContent());
							}
						}
						
						if(characterElement.getOptionalFirstOf("concealedSlots").isPresent()) {
							behaviour.concealedSlotIds = new HashMap<>();
							for(Element viewingCharacterElement : characterElement.getMandatoryFirstOf("concealedSlots").getAllOf("viewingCharacter")) {
								String targetId = viewingCharacterElement.getAttribute("id");
								List<String> slotIds = new ArrayList<>();
								for(Element inventorySlotElement : viewingCharacterElement.getAllOf("inventorySlot")) {
									slotIds.add(inventorySlotElement.getTextContent());
								}
								behaviour.concealedSlotIds.put(targetId, slotIds);
							}
						}

						if(characterElement.getOptionalFirstOf("preferences").isPresent()) {
							behaviour.foreplayPreferenceIds = new HashMap<>();
							behaviour.sexPreferenceIds = new HashMap<>();
							for(Element targetElement : characterElement.getMandatoryFirstOf("preferences").getAllOf("character")) {
								String targetId = targetElement.getAttribute("id");
								boolean copyForeplay = false;
								
								if(targetElement.getOptionalFirstOf("foreplay").isPresent()) {
									copyForeplay = Boolean.valueOf(targetElement.getMandatoryFirstOf("foreplay").getAttribute("copyForMain"));
									behaviour.foreplayPreferenceIds.put(
											targetId,
											new Value<>(
												targetElement.getMandatoryFirstOf("foreplay").getMandatoryFirstOf("performing").getTextContent(),
												targetElement.getMandatoryFirstOf("foreplay").getMandatoryFirstOf("targeted").getTextContent()));
									if(copyForeplay) {
										behaviour.sexPreferenceIds.put(
												targetId,
												new Value<>(
													targetElement.getMandatoryFirstOf("foreplay").getMandatoryFirstOf("performing").getTextContent(),
													targetElement.getMandatoryFirstOf("foreplay").getMandatoryFirstOf("targeted").getTextContent()));
									}
								}
								if(!copyForeplay && targetElement.getOptionalFirstOf("mainSex").isPresent()) {
									behaviour.sexPreferenceIds.put(
											targetId,
											new Value<>(
												targetElement.getMandatoryFirstOf("mainSex").getMandatoryFirstOf("performing").getTextContent(),
												targetElement.getMandatoryFirstOf("mainSex").getMandatoryFirstOf("targeted").getTextContent()));
								}
							}
						}

						if(characterElement.getOptionalFirstOf("sexTypesBanned").isPresent()) {
							behaviour.sexTypesBannedIds = new HashMap<>();
							for(Element sexTypeElement : characterElement.getMandatoryFirstOf("sexTypesBanned").getAllOf("sexType")) {
								behaviour.sexTypesBannedIds.put(
										sexTypeElement.getMandatoryFirstOf("performing").getTextContent(),
										sexTypeElement.getMandatoryFirstOf("targeted").getTextContent());
							}
						}

						if(characterElement.getOptionalFirstOf("areasBanned").isPresent()) {
							behaviour.areasBannedIds = new ArrayList<>();
							behaviour.areaBansApplyToSelf = Boolean.valueOf(characterElement.getMandatoryFirstOf("areasBanned").getAttribute("appliesToSelf"));
							for(Element areaElement : characterElement.getMandatoryFirstOf("areasBanned").getAllOf("sexArea")) {
								behaviour.areasBannedIds.add(areaElement.getTextContent());
							}
						}
						
						if(elementPresentAndNotEmpty(characterElement, "stopSexCondition")) {
							behaviour.stopSexCondition = characterElement.getMandatoryFirstOf("stopSexCondition").getTextContent();
						}
						
						behaviour.dirtyTalk = new HashMap<>();
						behaviour.submissiveTalk = new HashMap<>();
						behaviour.roughTalk = new HashMap<>();
						if(characterElement.getOptionalFirstOf("dirtyTalk").isPresent()) {
							for(Element dirtyTalkCharacterElement : characterElement.getMandatoryFirstOf("dirtyTalk").getAllOf("character")) {
								String dirtyTalkId = dirtyTalkCharacterElement.getAttribute("id");
								// Normal dirty talk:
								if(dirtyTalkCharacterElement.getOptionalFirstOf("standard").isPresent()) {
									Element standardTalkElement = dirtyTalkCharacterElement.getMandatoryFirstOf("standard");
									if(!standardTalkElement.getAttribute("additionToDefault").isEmpty()) {
										behaviour.additionToDefaultDirtyTalk = Boolean.valueOf(standardTalkElement.getAttribute("additionToDefault"));
									}
									List<DirtyTalkLine> lines = new ArrayList<>();
									for(Element lineElement : standardTalkElement.getAllOf("line")) {
										SexAreaInterface performing = lineElement.getAttribute("performing").isEmpty()?null:behaviour.getSexArea(lineElement.getAttribute("performing"));
										SexAreaInterface targeted = lineElement.getAttribute("targeted").isEmpty()?null:behaviour.getSexArea(lineElement.getAttribute("targeted"));
										boolean performingAnyPenetration = Boolean.valueOf(lineElement.getAttribute("performingAnyPenetration"));
										boolean takingAnyPenetration = Boolean.valueOf(lineElement.getAttribute("takingAnyPenetration"));
										boolean extraNoises = lineElement.getAttribute("extraNoises").isEmpty()?true:Boolean.valueOf(lineElement.getAttribute("extraNoises"));
										
										lines.add(new DirtyTalkLine(performing, targeted, performingAnyPenetration, takingAnyPenetration, extraNoises, lineElement.getTextContent()));
									}
									behaviour.dirtyTalk.put(dirtyTalkId, lines);
								}
								// Submissive talk:
								if(dirtyTalkCharacterElement.getOptionalFirstOf("submissive").isPresent()) {
									Element submissiveTalkElement = dirtyTalkCharacterElement.getMandatoryFirstOf("submissive");
									if(!submissiveTalkElement.getAttribute("additionToDefault").isEmpty()) {
										behaviour.additionToDefaultSubmissiveTalk = Boolean.valueOf(submissiveTalkElement.getAttribute("additionToDefault"));
									}
									List<DirtyTalkLine> lines = new ArrayList<>();
									for(Element lineElement : submissiveTalkElement.getAllOf("line")) {
										SexAreaInterface performing = lineElement.getAttribute("performing").isEmpty()?null:behaviour.getSexArea(lineElement.getAttribute("performing"));
										SexAreaInterface targeted = lineElement.getAttribute("targeted").isEmpty()?null:behaviour.getSexArea(lineElement.getAttribute("targeted"));
										boolean performingAnyPenetration = Boolean.valueOf(lineElement.getAttribute("performingAnyPenetration"));
										boolean takingAnyPenetration = Boolean.valueOf(lineElement.getAttribute("takingAnyPenetration"));
										boolean extraNoises = lineElement.getAttribute("extraNoises").isEmpty()?true:Boolean.valueOf(lineElement.getAttribute("extraNoises"));
										
										lines.add(new DirtyTalkLine(performing, targeted, performingAnyPenetration, takingAnyPenetration, extraNoises, lineElement.getTextContent()));
									}
									behaviour.submissiveTalk.put(dirtyTalkId, lines);
								}
								// Rough talk:
								if(dirtyTalkCharacterElement.getOptionalFirstOf("rough").isPresent()) {
									Element roughTalkElement = dirtyTalkCharacterElement.getMandatoryFirstOf("rough");
									if(!roughTalkElement.getAttribute("additionToDefault").isEmpty()) {
										behaviour.additionToDefaultRoughTalk = Boolean.valueOf(roughTalkElement.getAttribute("additionToDefault"));
									}
									List<DirtyTalkLine> lines = new ArrayList<>();
									for(Element lineElement : roughTalkElement.getAllOf("line")) {
										SexAreaInterface performing = lineElement.getAttribute("performing").isEmpty()?null:behaviour.getSexArea(lineElement.getAttribute("performing"));
										SexAreaInterface targeted = lineElement.getAttribute("targeted").isEmpty()?null:behaviour.getSexArea(lineElement.getAttribute("targeted"));
										boolean performingAnyPenetration = Boolean.valueOf(lineElement.getAttribute("performingAnyPenetration"));
										boolean takingAnyPenetration = Boolean.valueOf(lineElement.getAttribute("takingAnyPenetration"));
										boolean extraNoises = lineElement.getAttribute("extraNoises").isEmpty()?true:Boolean.valueOf(lineElement.getAttribute("extraNoises"));
										
										lines.add(new DirtyTalkLine(performing, targeted, performingAnyPenetration, takingAnyPenetration, extraNoises, lineElement.getTextContent()));
									}
									behaviour.roughTalk.put(dirtyTalkId, lines);
								}
							}
						}

						if(elementPresentAndNotEmpty(characterElement, "preferredTarget")) {
							behaviour.preferredTarget = characterElement.getMandatoryFirstOf("preferredTarget").getTextContent();
						}
						
						
						if(elementPresentAndNotEmpty(characterElement, "uniqueSexActions")) {
							behaviour.sexClassesIds = new ArrayList<>();
							for(Element sexActionElement : characterElement.getMandatoryFirstOf("uniqueSexActions").getAllOf("sexAction")) {
								behaviour.sexClassesIds.add(sexActionElement.getTextContent());
							}
						}
						
						if(characterElement.getOptionalFirstOf("orgasmCumTargetsBlocked").isPresent()) {
							behaviour.bannedOrgasmCumTargets = new HashMap<>();
							for(Element characterBlocksElement : characterElement.getMandatoryFirstOf("orgasmCumTargetsBlocked").getAllOf("character")) {
								String targetBlocksId = characterBlocksElement.getAttribute("id");
								behaviour.bannedOrgasmCumTargets.put(targetBlocksId, new ArrayList<>());
								if(Boolean.valueOf(characterBlocksElement.getAttribute("allBodyAreas"))) {
									for(OrgasmCumTarget target : OrgasmCumTarget.values()) {
										if(target.isRequiresPartner()) {
											behaviour.bannedOrgasmCumTargets.get(targetBlocksId).add(target);
										}
									}
								} else {
									for(Element targetAreaElement : characterBlocksElement.getAllOf("target")) {
										behaviour.bannedOrgasmCumTargets.get(targetBlocksId).add(OrgasmCumTarget.valueOf(targetAreaElement.getTextContent().trim()));
									}
								}
							}
						}
						
						
						characterBehaviours.put(id, behaviour);
					}
				}
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("SetBonus was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
	}
	
	public void initManager(AbstractSexPosition position, Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		this.position = position;
		this.dominants = dominants;
		this.submissives = submissives;
		
		title = UtilText.parse(titleString);
		consensual = Boolean.valueOf(UtilText.parse(consensualString).trim());
		subHasEqualControl = Boolean.valueOf(UtilText.parse(subHasEqualControlString).trim());
		ableToSkipSexScene = Boolean.valueOf(UtilText.parse(canSkipSexString).trim());
		washingScene = Boolean.valueOf(UtilText.parse(washingSceneString).trim());
		canItemsBeUsed = Boolean.valueOf(UtilText.parse(canItemsBeUsedString).trim());
		exposingReactions = Boolean.valueOf(UtilText.parse(exposingReactionsString).trim());

		positionsAllowed = new ArrayList<>();
		if(positionsExclusive) {
			positionsAllowed.addAll(SexPosition.getAllSexPositions());
		}
		for(String id : positionsAllowedIds) {
			String parsedId = UtilText.parse(id).trim();
			if(!parsedId.isEmpty()) {
				if(positionsExclusive) {
					positionsAllowed.remove(SexPosition.getSexPositionFromId(parsedId));
				} else {
					positionsAllowed.add(SexPosition.getSexPositionFromId(parsedId));
				}
			}
		}

		publicSex = Boolean.valueOf(UtilText.parse(publicSexString).trim());

		for(Entry<String, CharacterBehaviour> entry : characterBehaviours.entrySet()) {
			entry.getValue().initCharacterBehaviour();
		}

		sexTypesBannedMap = new HashMap<>();
		areasBannedMap =  new HashMap<>();
		
		if(!characterBehavioursSetUp) {
			Map<String, CharacterBehaviour> characterBehavioursWithCorrectIds = new HashMap<>();
			for(Entry<String, CharacterBehaviour> entry : characterBehaviours.entrySet()) {
				GameCharacter character = UtilText.findFirstCharacterFromParserTarget(entry.getKey());
				if(entry.getValue().getSexTypesBanned()!=null) {
					sexTypesBannedMap.put(character, entry.getValue().getSexTypesBanned());
				}
				if(entry.getValue().getAreasBanned()!=null) {
					areasBannedMap.put(character, entry.getValue().getAreasBanned());
				}
				characterBehavioursWithCorrectIds.put(character.getId(), entry.getValue());
			}
			characterBehaviours = characterBehavioursWithCorrectIds;
			characterBehavioursSetUp = true;
		}
	}
	
	private boolean elementPresentAndNotEmpty(Element element, String tag) {
		try {
			return element.getOptionalFirstOf(tag).isPresent() && !element.getMandatoryFirstOf(tag).getTextContent().isEmpty();
		} catch (XMLMissingTagException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isMod() {
		return mod;
	}

	public String getAuthor() {
		return author;
	}

	@Override
	public String getSexTitle() {
		if(title==null || title.isEmpty()) {
			return super.getSexTitle();
		}
		return UtilText.parse(title);
	}
	
	public boolean isConsensual() {
		return consensual;
	}

	public boolean isSubHasEqualControl() {
		return subHasEqualControl;
	}

	@Override
	public boolean isWashingScene() {
		return washingScene;
	}

	@Override
	public boolean isSadisticActionsAllowed() {
		return sadisticActionsAllowed;
	}
	
	@Override
	public boolean isItemUseAvailable() {
		return canItemsBeUsed;
	}

	@Override
	public boolean isCharactersReactingToExposedAreas() {
		return exposingReactions;
	}

	@Override
	public List<AbstractSexPosition> getAllowedSexPositions() {
		if(positionsAllowed==null || positionsAllowed.isEmpty()) {
			return super.getAllowedSexPositions();
		}
		return positionsAllowed;
	}
	
	@Override
	public boolean isPublicSex() {
		if(!publicSexOverride) {
			return super.isPublicSex();
		}
		return publicSex;
	}
	
	@Override
	public String getPublicSexStartingDescription() {
		if(startingPublicSexDescription.isEmpty()) {
			return super.getPublicSexStartingDescription();
		}
		return applyPublicSexFormatting(UtilText.parse(startingPublicSexDescription));
	}

	@Override
	public String getRandomPublicSexDescription() {
		if(!randomPublicDescriptionsEnabled) {
			return "";
		}
		if(randomPublicSexDescriptions==null || randomPublicSexDescriptions.isEmpty()) {
			return super.getRandomPublicSexDescription();
		}
		return applyPublicSexFormatting(UtilText.parse(Util.randomItemFrom(randomPublicSexDescriptions)));
	}
	
	// Character-specific methods, in the order in which they appear in the xml file:
	
	@Override
	public boolean isSlotAvailable(GameCharacter character, SexSlot slot) {
		if(characterBehaviours.containsKey(character.getId())) {
			return characterBehaviours.get(character.getId()).getSlotsAvailable()!=null
					&& !characterBehaviours.get(character.getId()).getSlotsAvailable().isEmpty()
					&& characterBehaviours.get(character.getId()).getSlotsAvailable().contains(slot);
		}
		return super.isSlotAvailable(character, slot);
	}

	@Override
	public boolean isCharacterAbleToStopSex(GameCharacter character) {
		if(characterBehaviours.containsKey(character.getId()) && !characterBehaviours.get(character.getId()).isCanStopSexBool()) {
			return false;
		}
		return super.isCharacterAbleToStopSex(character);
	}

	@Override
	public boolean isPositionChangingAllowed(GameCharacter character) {
		if(characterBehaviours.containsKey(character.getId()) && !characterBehaviours.get(character.getId()).isCanChangePositionsBool()) {
			return false;
		}
		return super.isPositionChangingAllowed(character);
	}
	
	@Override
	public boolean isEndSexAffectionChangeEnabled(GameCharacter character) {
		if(characterBehaviours.containsKey(character.getId())) {
			return characterBehaviours.get(character.getId()).isEndSexAffectionChangesBool();
		}
		return super.isEndSexAffectionChangeEnabled(character);
	}

	@Override
	public boolean isAppendStartingExposedDescriptions(GameCharacter character) {
		if(characterBehaviours.containsKey(character.getId())) {
			return characterBehaviours.get(character.getId()).isShowStartingExposedDescriptionsBool();
		}
		return super.isAppendStartingExposedDescriptions(character);
	}
	
	@Override
	public SexPace getStartingSexPaceModifier(GameCharacter character) {
		if(characterBehaviours.containsKey(character.getId())) {
			return characterBehaviours.get(character.getId()).getSexPace();
		}
		return super.getStartingSexPaceModifier(character);
	}

	@Override
	public SexPace getForcedSexPace(GameCharacter character) {
		if(characterBehaviours.containsKey(character.getId()) && characterBehaviours.get(character.getId()).isSexPaceLocked()) {
			return characterBehaviours.get(character.getId()).getSexPace();
		}
		return super.getForcedSexPace(character);
	}
	
	@Override
	public boolean isSelfTransformDisabled(GameCharacter character) {
		if(characterBehaviours.containsKey(character.getId())) {
			return !characterBehaviours.get(character.getId()).isCanSelfTransformBool();
		}
		return super.isSelfTransformDisabled(character);
	}

	@Override
	public boolean isHidden(GameCharacter character) {
		if(characterBehaviours.containsKey(character.getId())) {
			return characterBehaviours.get(character.getId()).isHiddenBool();
		}
		return super.isHidden(character);
	}

	@Override
	public SexControl getSexControl(GameCharacter character) {
		if(characterBehaviours.containsKey(character.getId()) && characterBehaviours.get(character.getId()).getControl()!=null) {
			return characterBehaviours.get(character.getId()).getControl();
		}
		return super.getSexControl(character);
	}
	
	@Override
	public boolean isAbleToEquipSexClothing(GameCharacter character){
		if(characterBehaviours.containsKey(character.getId())) {
			return characterBehaviours.get(character.getId()).isSexClothingEquippableBool();
		}
		return super.isHidden(character);
	}

	@Override
	public boolean isAbleToRemoveSelfClothing(GameCharacter character){
		if(characterBehaviours.containsKey(character.getId())) {
			return characterBehaviours.get(character.getId()).isSelfClothingRemovalBool();
		}
		return super.isHidden(character);
	}

	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
		if(characterBehaviours.containsKey(character.getId())) {
			return characterBehaviours.get(character.getId()).isPartnerClothingRemoval(character, clothing);
		}
		return super.isHidden(character);
	}

	@Override
	public boolean isCharacterStartNaked(GameCharacter character) {
		if(characterBehaviours.containsKey(character.getId())) {
			return characterBehaviours.get(character.getId()).isStartNakedBool();
		}
		return super.isHidden(character);
	}
	
	@Override
	public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
		Map<GameCharacter, List<CoverableArea>> exposeMap = new HashMap<>();
		
		for(Entry<String, CharacterBehaviour> entry : characterBehaviours.entrySet()) {
			try {
				GameCharacter character = Main.game.getNPCById(entry.getKey());
				exposeMap.put(character, entry.getValue().getAreasExposedOnStart());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return exposeMap;
	}
	
	@Override
	public boolean isExposeAtStartOfSexMapRemoval(GameCharacter character) {
		if(characterBehaviours.containsKey(character.getId())) {
			return characterBehaviours.get(character.getId()).isPreferExposingRemoval();
		}
		return super.isHidden(character);
	}

	@Override
	public boolean isForceCreampieAllowed(GameCharacter characterOrgasming, GameCharacter characterRecevingCreampie) {
		if(characterBehaviours.containsKey(characterRecevingCreampie.getId())) {
			return !characterBehaviours.get(characterRecevingCreampie.getId()).isForceCreampieDisabled(characterOrgasming);
		}
		return super.isForceCreampieAllowed(characterOrgasming, characterRecevingCreampie);
	}

	@Override
	public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
		if(characterBehaviours.containsKey(character.getId()) && characterBehaviours.get(character.getId()).getOrgasmBehaviour()!=null) {
			return characterBehaviours.get(character.getId()).getOrgasmBehaviour();
		}
		return super.getCharacterOrgasmBehaviour(character);
	}

	@Override
	public OrgasmEncourageBehaviour getCharacterOrgasmEncourageBehaviour(GameCharacter character, GameCharacter characterOrgasming, GameCharacter characterPenetrated) {
		if(characterBehaviours.containsKey(character.getId()) && characterBehaviours.get(character.getId()).getOrgasmEncourageBehaviour(characterOrgasming, characterPenetrated)!=null) {
			return characterBehaviours.get(character.getId()).getOrgasmEncourageBehaviour(characterOrgasming, characterPenetrated);
		}
		return super.getCharacterOrgasmEncourageBehaviour(character, characterOrgasming, characterPenetrated);
	}

	@Override
	public OrgasmCumTarget getCharacterPullOutOrgasmCumTarget(GameCharacter character, GameCharacter target) {
		if(characterBehaviours.containsKey(character.getId()) && characterBehaviours.get(character.getId()).getOrgasmCumTarget(target)!=null) {
			return characterBehaviours.get(character.getId()).getOrgasmCumTarget(target);
		}
		return super.getCharacterPullOutOrgasmCumTarget(character, target);
	}
	
	@Override
	public List<InventorySlot> getSlotsConcealed(GameCharacter characterBeingExposed, GameCharacter characterViewing) {
		if(characterBehaviours.containsKey(characterBeingExposed.getId())) {
			return characterBehaviours.get(characterBeingExposed.getId()).getConcealedSlots(characterViewing);
		}
		return super.getSlotsConcealed(characterBeingExposed, characterViewing);
	}

	@Override
	public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
		if(characterBehaviours.containsKey(character.getId()) && characterBehaviours.get(character.getId()).getForeplayPreference(targetedCharacter)!=null) {
			return characterBehaviours.get(character.getId()).getForeplayPreference(targetedCharacter);
		}
		return super.getForeplayPreference(character, targetedCharacter);
	}

	@Override
	public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
		if(characterBehaviours.containsKey(character.getId()) && characterBehaviours.get(character.getId()).getSexPreference(targetedCharacter)!=null) {
			return characterBehaviours.get(character.getId()).getSexPreference(targetedCharacter);
		}
		return super.getMainSexPreference(character, targetedCharacter);
	}
	
	@Override
	public Map<GameCharacter, List<SexType>> getSexTypesBannedMap() {
		return sexTypesBannedMap;
	}

	@Override
	public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap() {
		return areasBannedMap;
	}
	
	@Override
	public boolean isAreasBannedMapAppliedToSelfActions(GameCharacter character) {
		if(characterBehaviours.containsKey(character.getId())) {
			return characterBehaviours.get(character.getId()).isAreaBansApplyToSelf();
		}
		return super.isAreasBannedMapAppliedToSelfActions(character);
	}
	
	@Override
	public boolean isPartnerWantingToStopSex(GameCharacter partner) {
		if(characterBehaviours.containsKey(partner.getId())
				&& characterBehaviours.get(partner.getId()).stopSexCondition!=null
				&& !UtilText.parse(characterBehaviours.get(partner.getId()).stopSexCondition).trim().isEmpty()) {
			return characterBehaviours.get(partner.getId()).isWantingToStopSex();
		}
		return super.isPartnerWantingToStopSex(partner);
	}

	@Override
	public String getDirtyTalk(GameCharacter character) {
		if(characterBehaviours.containsKey(character.getId())) {
			GameCharacter target = Main.sex.getTargetedPartner(character);
			if(target!=null && characterBehaviours.get(character.getId()).dirtyTalk.containsKey(target.getId())) {
				List<String> lines = new ArrayList<>();
				for(DirtyTalkLine line : characterBehaviours.get(character.getId()).dirtyTalk.get(target.getId())) {
					if(line.isConditionalMet(character)) {
						lines.add(line.getParsedContent(character));
					}
				}
				lines.removeIf(l->l.isEmpty());
				if(!lines.isEmpty() && (!characterBehaviours.get(character.getId()).additionToDefaultDirtyTalk || Math.random()<0.5f)) {
					return Util.randomItemFrom(lines);
				}
			}
		}
		return character.getDirtyTalk();
	}

	@Override
	public String getRoughTalk(GameCharacter character) {
		if(characterBehaviours.containsKey(character.getId())) {
			GameCharacter target = Main.sex.getTargetedPartner(character);
			if(target!=null && characterBehaviours.get(character.getId()).roughTalk.containsKey(target.getId())) {
				List<String> lines = new ArrayList<>();
				for(DirtyTalkLine line : characterBehaviours.get(character.getId()).roughTalk.get(target.getId())) {
					if(line.isConditionalMet(character)) {
						lines.add(line.getParsedContent(character));
					}
				}
				lines.removeIf(l->l.isEmpty());
				if(!lines.isEmpty() && (!characterBehaviours.get(character.getId()).additionToDefaultRoughTalk || Math.random()<0.5f)) {
					return Util.randomItemFrom(lines);
				}
			}
		}
		return character.getRoughTalk();
	}

	@Override
	public String getSubmissiveTalk(GameCharacter character) {
		if(characterBehaviours.containsKey(character.getId())) {
			GameCharacter target = Main.sex.getTargetedPartner(character);
			if(target!=null && characterBehaviours.get(character.getId()).submissiveTalk.containsKey(target.getId())) {
				List<String> lines = new ArrayList<>();
				for(DirtyTalkLine line : characterBehaviours.get(character.getId()).submissiveTalk.get(target.getId())) {
					if(line.isConditionalMet(character)) {
						lines.add(line.getParsedContent(character));
					}
				}
				lines.removeIf(l->l.isEmpty());
				if(!lines.isEmpty() && (!characterBehaviours.get(character.getId()).additionToDefaultSubmissiveTalk || Math.random()<0.5f)) {
					return Util.randomItemFrom(lines);
				}
			}
		}
		return character.getSubmissiveTalk();
	}
	
	@Override
	public GameCharacter getPreferredSexTarget(NPC character) {
		if(characterBehaviours.containsKey(character.getId())
				&& characterBehaviours.get(character.getId()).preferredTarget!=null
				&& !characterBehaviours.get(character.getId()).preferredTarget.isEmpty()) {
			return UtilText.findFirstCharacterFromParserTarget(UtilText.parse(characterBehaviours.get(character.getId()).preferredTarget).trim());
		}
		return super.getPreferredSexTarget(character);
	}
	
	@Override
	public List<SexActionInterface> getUniqueSexClasses(GameCharacter character) {
		if(characterBehaviours.containsKey(character.getId())
				&& characterBehaviours.get(character.getId()).sexClasses!=null) {
			return characterBehaviours.get(character.getId()).sexClasses;
		}
		return new ArrayList<>();
	}
	
	@Override
	public List<OrgasmCumTarget> getBannedOrgasmCumTargets(GameCharacter character, GameCharacter target) {
		if(characterBehaviours.containsKey(character.getId())
				&& characterBehaviours.get(character.getId()).bannedOrgasmCumTargets!=null
				&& characterBehaviours.get(character.getId()).bannedOrgasmCumTargets.get(target.getId())!=null) {
			return characterBehaviours.get(character.getId()).bannedOrgasmCumTargets.get(target.getId());
		}
		return new ArrayList<>();
	}
}
