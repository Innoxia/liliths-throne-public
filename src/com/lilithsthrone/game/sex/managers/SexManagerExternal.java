package com.lilithsthrone.game.sex.managers;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.controller.xmlParsing.XMLMissingTagException;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.LubricationType;
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
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
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

	private String deskName;
	private String wallName;
	
	private String consensualString;
	private boolean consensual;

	private String subHasEqualControlString;
	private boolean subHasEqualControl;
	
	private String canSkipSexString;

	private String washingSceneString;
	private boolean washingScene;

	public String sadisticActionsAllowedString;
	public boolean sadisticActionsAllowed;

	public String lovingActionsAllowedString;
	public boolean lovingActionsAllowed;
	
	private String canItemsBeUsedString;
	private boolean canItemsBeUsed;

	private String exposingReactionsString;
	private boolean exposingReactions;
	
	private List<AbstractSexPosition> positionsAllowed;
	private List<String> positionsAllowedIds;
	private boolean positionsExclusive;

	private Map<String, CharacterBehaviour> characterBehavioursWithParserIds;
	private Map<String, CharacterBehaviour> characterBehaviours;
	
	private Map<GameCharacter, List<SexType>> sexTypesBannedMap;
	private Map<GameCharacter, List<SexAreaInterface>> areasBannedMap;
	
	private Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> startingCharactersImmobilised;

	/**Maps: character who is lubricated -> Map of areas -> Map of owner of lubrication -> lubrications*/
	private Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> startingWetAreas;
	
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
		
		public String canSwapPositions;
		public boolean canSwapPositionsBool;
		
		public String endSexAffectionChanges;
		public boolean endSexAffectionChangesBool;

		public String showStartingExposedDescriptions;
		public boolean showStartingExposedDescriptionsBool;

		public String canSelfTransform;
		public boolean canSelfTransformBool;

		public String rapePlayBannedAtStart;
		public boolean rapePlayBannedAtStartBool;
		
		public String hidden;
		public boolean hiddenBool;
		
		public String sexClothingEquippable;

		public String selfClothingRemoval;
		public boolean selfClothingRemovalBool;

		public String canRemoveClothingSeals;
		public boolean canRemoveClothingSealsBool;
		
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
		
		public Value<String, String> startingImmobilisation;
		public Map<String, Map<String, Set<String>>> startingWetAreas;

		public String orgasmBehaviour;
		
		
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
		public boolean concealedSlotsExclusive;

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

		public boolean lovingTalkInitCompleted = false;
		public boolean additionToDefaultLovingTalk = true;
		public Map<String, List<DirtyTalkLine>> lovingTalk;

		public boolean lovingResponseTalkInitCompleted = false;
		public boolean additionToDefaultLovingResponseTalk = true;
		public Map<String, List<DirtyTalkLine>> lovingResponseTalk;
		
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
			canSwapPositionsBool = initBool(canSwapPositions, true);
			endSexAffectionChangesBool = initBool(endSexAffectionChanges, true);
			showStartingExposedDescriptionsBool = initBool(showStartingExposedDescriptions, true);
			canSelfTransformBool = initBool(canSelfTransform, true);
			rapePlayBannedAtStartBool = initBool(rapePlayBannedAtStart, true);
			hiddenBool = initBool(hidden, false);
			sadisticActionsAllowed = initBool(sadisticActionsAllowedString, true);
			lovingActionsAllowed = initBool(lovingActionsAllowedString, true);
			selfClothingRemovalBool = initBool(selfClothingRemoval, true);
			canRemoveClothingSealsBool = initBool(canRemoveClothingSeals, true);
			startNakedBool = initBool(startNaked, false);
			
			// Other values:
			if(sexPace!=null && !sexPace.isEmpty()) {
				sexPaceParsed = SexPace.valueOf(UtilText.parse(sexPace).trim());
			}
			
			if(control!=null && !control.isEmpty()) {
				controlParsed = SexControl.valueOf(UtilText.parse(control).trim());
			}
			
			
			// Slots:
			if(slotsAvailableIds==null || slotsAvailableIds.isEmpty()) {
				slotsAvailable = SexSlotManager.getAllSexSlots();
				
			} else {
				if(slotsExcluded) {
					slotsAvailable = SexSlotManager.getAllSexSlots();
					for(String id : slotsAvailableIds) {
						if(!id.isEmpty()) {
							String sexSlotParsed = UtilText.parse(id).trim();
							if(!sexSlotParsed.isEmpty()) {
								slotsAvailable.remove(SexSlotManager.getSexSlotFromId(sexSlotParsed));
							}
						}
					}
					
				} else {
					slotsAvailable = new ArrayList<>();
					for(String id : slotsAvailableIds) {
						if(!id.isEmpty()) {
							String sexSlotParsed = UtilText.parse(id).trim();
							if(!sexSlotParsed.isEmpty()) {
								slotsAvailable.add(SexSlotManager.getSexSlotFromId(sexSlotParsed));
							}
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
					if(concealedSlotsExclusive) {
						for(InventorySlot slot : InventorySlot.values()) {
							slots.add(slot);
						}
					}
					for(String id : entry.getValue()) {
						String slotParsed = UtilText.parse(id).trim();
						if(!slotParsed.isEmpty()) {
							if(concealedSlotsExclusive) {
								slots.remove(InventorySlot.valueOf(slotParsed));
							} else {
								slots.add(InventorySlot.valueOf(slotParsed));
							}
						}
					}
					concealedSlots.put(UtilText.findFirstCharacterFromParserTarget(entry.getKey()).getId(), slots);
				}
			}
			
			// Preferences:
			foreplayPreferences = new HashMap<>();
			if(foreplayPreferenceIds!=null) {
				for(Entry<String, Value<String, String>> entry : foreplayPreferenceIds.entrySet()) {
					String performing = UtilText.parse(entry.getValue().getKey()).trim();
					String targeted = UtilText.parse(entry.getValue().getValue()).trim();
					if(!performing.isEmpty() && !targeted.isEmpty()) {
						foreplayPreferences.put(
								UtilText.findFirstCharacterFromParserTarget(entry.getKey()),
								new SexType(SexParticipantType.NORMAL,
										getSexArea(performing),
										getSexArea(targeted)));
					}
				}
			}
			
			sexPreferences = new HashMap<>();
			if(sexPreferenceIds!=null) {
				for(Entry<String, Value<String, String>> entry : sexPreferenceIds.entrySet()) {
					String performing = UtilText.parse(entry.getValue().getKey()).trim();
					String targeted = UtilText.parse(entry.getValue().getValue()).trim();
					if(!performing.isEmpty() && !targeted.isEmpty()) {
						sexPreferences.put(
								UtilText.findFirstCharacterFromParserTarget(entry.getKey()),
								new SexType(SexParticipantType.NORMAL,
										getSexArea(performing),
										getSexArea(targeted)));
					}
				}
			}
			
			// Sex types banned:
			sexTypesBanned = new ArrayList<>();
			if(sexTypesBannedIds!=null) {
				for(Entry<String, String> entry : sexTypesBannedIds.entrySet()) {
					String performing = UtilText.parse(entry.getKey()).trim();
					String targeted = UtilText.parse(entry.getValue()).trim();
					if(!performing.isEmpty() && !targeted.isEmpty()) {
						sexTypesBanned.add(
								new SexType(SexParticipantType.NORMAL,
										getSexArea(performing),
										getSexArea(targeted)));
					}
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
			if(!lovingTalkInitCompleted) {
				Map<String, List<DirtyTalkLine>> tempDirtyTalkMap = new HashMap<>(lovingTalk);
				lovingTalk = new HashMap<>();
				for(Entry<String, List<DirtyTalkLine>> entry : tempDirtyTalkMap.entrySet()) {
					lovingTalk.put(UtilText.findFirstCharacterFromParserTarget(entry.getKey()).getId(), entry.getValue());
				}
				lovingTalkInitCompleted = true;
			}
			if(!lovingResponseTalkInitCompleted) {
				Map<String, List<DirtyTalkLine>> tempDirtyTalkMap = new HashMap<>(lovingResponseTalk);
				lovingResponseTalk = new HashMap<>();
				for(Entry<String, List<DirtyTalkLine>> entry : tempDirtyTalkMap.entrySet()) {
					lovingResponseTalk.put(UtilText.findFirstCharacterFromParserTarget(entry.getKey()).getId(), entry.getValue());
				}
				lovingResponseTalkInitCompleted = true;
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
		
		// Pre-parsed booleans:
		
		public boolean isCanStopSexBool() {
			return canStopSexBool;
		}

		public boolean isCanChangePositionsBool() {
			return canChangePositionsBool;
		}

		public boolean isCanSwapPositionsBool() {
			return canSwapPositionsBool;
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
		
		public boolean isRapePlayBannedAtStartBool() {
			return rapePlayBannedAtStartBool;
		}

		public boolean isHiddenBool() {
			return hiddenBool;
		}

		public boolean isSelfClothingRemovalBool() {
			return selfClothingRemovalBool;
		}

		public boolean isCanRemoveClothingSealsBool() {
			return canRemoveClothingSealsBool;
		}
		
		public boolean isStartNakedBool() {
			return startNakedBool;
		}
		
		public boolean isForceCreampieDisabled(GameCharacter orgasmingPartner) {
			return disabledForceCreampieIds.contains(orgasmingPartner.getId());
		}
		
		// Parse on check booleans:

		public boolean isPartnerClothingRemovalUsed() {
			return partnerClothingRemoval!=null;
		}
		
		public boolean isPartnerClothingRemoval(GameCharacter target, AbstractClothing clothing) {
			UtilText.setClothingTypeForParsing(clothing==null?null:clothing.getClothingType());
			
			return Boolean.valueOf(UtilText.parse(target, partnerClothingRemoval).trim());
		}

		public boolean isSexClothingEquippable(GameCharacter target, AbstractClothing clothing) {
			UtilText.setClothingTypeForParsing(clothing==null?null:clothing.getClothingType());
			
			return Boolean.valueOf(UtilText.parse(target, sexClothingEquippable).trim());
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

		public Value<String, String> getStartingImmobilisation() {
			return startingImmobilisation;
		}
		
		public Map<String, Map<String, Set<String>>> getStartingWetAreas() {
			return startingWetAreas;
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

		public OrgasmBehaviour getOrgasmBehaviour() {
			if(Main.game.isInSex() && orgasmBehaviour!=null && !orgasmBehaviour.isEmpty()) {
				return OrgasmBehaviour.valueOf(UtilText.parse(orgasmBehaviour).trim());
			}
			return null;
		}
		
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
				
				if(elementPresentAndNotEmpty(sexManagerElement, "deskName")) {
					deskName = sexManagerElement.getMandatoryFirstOf("deskName").getTextContent();
				} else {
					deskName = null;
				}
				
				if(elementPresentAndNotEmpty(sexManagerElement, "wallName")) {
					wallName = sexManagerElement.getMandatoryFirstOf("wallName").getTextContent();
				} else {
					wallName = null;
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

				if(elementPresentAndNotEmpty(sexManagerElement, "lovingActionsAllowed")) {
					lovingActionsAllowedString = sexManagerElement.getMandatoryFirstOf("lovingActionsAllowed").getTextContent();
				} else {
					lovingActionsAllowedString = "true";
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
				characterBehavioursWithParserIds = new HashMap<>();
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
						
						if(characterElement.getOptionalFirstOf("canSwapPositions").isPresent()) {
							behaviour.canSwapPositions = characterElement.getMandatoryFirstOf("canSwapPositions").getTextContent();
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
						
						if(characterElement.getOptionalFirstOf("rapePlayBannedAtStart").isPresent()) {
							behaviour.rapePlayBannedAtStart = characterElement.getMandatoryFirstOf("rapePlayBannedAtStart").getTextContent();
						}
						
						if(characterElement.getOptionalFirstOf("hidden").isPresent()) {
							behaviour.hidden = characterElement.getMandatoryFirstOf("hidden").getTextContent();
						}
						
						if(characterElement.getOptionalFirstOf("control").isPresent()) {
							behaviour.control = characterElement.getMandatoryFirstOf("control").getTextContent();
						}

						if(elementPresentAndNotEmpty(characterElement, "startingImmobilisation")) {
							behaviour.startingImmobilisation = new Value<>(
									characterElement.getMandatoryFirstOf("startingImmobilisation").getMandatoryFirstOf("applierId").getTextContent(),
									characterElement.getMandatoryFirstOf("startingImmobilisation").getMandatoryFirstOf("type").getTextContent());
						}

						if(elementPresentAndNotEmpty(characterElement, "startingLubrications")) {
							behaviour.startingWetAreas = new HashMap<>();
							for(Element areaElement : characterElement.getMandatoryFirstOf("startingLubrications").getAllOf("lubedArea")) {
								Map<String, Set<String>> innerMap = new HashMap<>();
								behaviour.startingWetAreas.put(areaElement.getAttribute("area"), innerMap);
								for(Element lubricationElement : areaElement.getAllOf("lubrication")) {
									Set<String> innerLubeSet = new HashSet<>();
									innerMap.put(lubricationElement.getMandatoryFirstOf("luberId").getTextContent(), innerLubeSet);
									for(Element lubricationTypeElement : lubricationElement.getAllOf("type")) {
										innerLubeSet.add(lubricationTypeElement.getTextContent());
									}
								}
							}
						}
						
						if(characterElement.getOptionalFirstOf("sexClothingEquippable").isPresent()) {
							behaviour.sexClothingEquippable = characterElement.getMandatoryFirstOf("sexClothingEquippable").getTextContent();
						}

						if(characterElement.getOptionalFirstOf("selfClothingRemoval").isPresent()) {
							behaviour.selfClothingRemoval = characterElement.getMandatoryFirstOf("selfClothingRemoval").getTextContent();
						}

						if(characterElement.getOptionalFirstOf("canRemoveClothingSeals").isPresent()) {
							behaviour.canRemoveClothingSeals = characterElement.getMandatoryFirstOf("canRemoveClothingSeals").getTextContent();
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
						
						//TODO moved all init of maps from here outside conditional presence
						behaviour.orgasmCumTargets = new HashMap<>();
						if(characterElement.getOptionalFirstOf("orgasmCumTarget").isPresent()) {
							for(Element targetElement : characterElement.getMandatoryFirstOf("orgasmCumTarget").getAllOf("target")) {
								behaviour.orgasmCumTargets.put(targetElement.getAttribute("id"), targetElement.getTextContent());
							}
						}

						behaviour.orgasmEncourageBehaviours = new HashMap<>();
						if(characterElement.getOptionalFirstOf("orgasmEncourageBehaviours").isPresent()) {
							for(Element behaviourElement : characterElement.getMandatoryFirstOf("orgasmEncourageBehaviours").getAllOf("behaviour")) {
								behaviour.orgasmEncourageBehaviours.putIfAbsent(behaviourElement.getAttribute("orgasming"), new HashMap<>());
								behaviour.orgasmEncourageBehaviours.get(behaviourElement.getAttribute("orgasming")).put(behaviourElement.getAttribute("target"), behaviourElement.getTextContent());
							}
						}

						behaviour.concealedSlotIds = new HashMap<>();
						if(characterElement.getOptionalFirstOf("concealedSlots").isPresent()) {
							behaviour.concealedSlotsExclusive = Boolean.valueOf(characterElement.getMandatoryFirstOf("concealedSlots").getAttribute("exclusive"));
							for(Element viewingCharacterElement : characterElement.getMandatoryFirstOf("concealedSlots").getAllOf("viewingCharacter")) {
								String targetId = viewingCharacterElement.getAttribute("id");
								List<String> slotIds = new ArrayList<>();
								for(Element inventorySlotElement : viewingCharacterElement.getAllOf("inventorySlot")) {
									slotIds.add(inventorySlotElement.getTextContent());
								}
								behaviour.concealedSlotIds.put(targetId, slotIds);
							}
						}

						behaviour.foreplayPreferenceIds = new HashMap<>();
						behaviour.sexPreferenceIds = new HashMap<>();
						if(characterElement.getOptionalFirstOf("preferences").isPresent()) {
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

						behaviour.sexTypesBannedIds = new HashMap<>();
						if(characterElement.getOptionalFirstOf("sexTypesBanned").isPresent()) {
							for(Element sexTypeElement : characterElement.getMandatoryFirstOf("sexTypesBanned").getAllOf("sexType")) {
								behaviour.sexTypesBannedIds.put(
										sexTypeElement.getMandatoryFirstOf("performing").getTextContent(),
										sexTypeElement.getMandatoryFirstOf("targeted").getTextContent());
							}
						}

						behaviour.areasBannedIds = new ArrayList<>();
						if(characterElement.getOptionalFirstOf("areasBanned").isPresent()) {
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
						behaviour.lovingTalk = new HashMap<>();
						behaviour.lovingResponseTalk = new HashMap<>();
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
										SexAreaInterface performing = lineElement.getAttribute("performing").isEmpty()?null:getSexArea(lineElement.getAttribute("performing"));
										SexAreaInterface targeted = lineElement.getAttribute("targeted").isEmpty()?null:getSexArea(lineElement.getAttribute("targeted"));
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
										SexAreaInterface performing = lineElement.getAttribute("performing").isEmpty()?null:getSexArea(lineElement.getAttribute("performing"));
										SexAreaInterface targeted = lineElement.getAttribute("targeted").isEmpty()?null:getSexArea(lineElement.getAttribute("targeted"));
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
										SexAreaInterface performing = lineElement.getAttribute("performing").isEmpty()?null:getSexArea(lineElement.getAttribute("performing"));
										SexAreaInterface targeted = lineElement.getAttribute("targeted").isEmpty()?null:getSexArea(lineElement.getAttribute("targeted"));
										boolean performingAnyPenetration = Boolean.valueOf(lineElement.getAttribute("performingAnyPenetration"));
										boolean takingAnyPenetration = Boolean.valueOf(lineElement.getAttribute("takingAnyPenetration"));
										boolean extraNoises = lineElement.getAttribute("extraNoises").isEmpty()?true:Boolean.valueOf(lineElement.getAttribute("extraNoises"));
										
										lines.add(new DirtyTalkLine(performing, targeted, performingAnyPenetration, takingAnyPenetration, extraNoises, lineElement.getTextContent()));
									}
									behaviour.roughTalk.put(dirtyTalkId, lines);
								}
								// Loving talk:
								if(dirtyTalkCharacterElement.getOptionalFirstOf("loving").isPresent()) {
									Element lovingTalkElement = dirtyTalkCharacterElement.getMandatoryFirstOf("loving");
									if(!lovingTalkElement.getAttribute("additionToDefault").isEmpty()) {
										behaviour.additionToDefaultLovingTalk = Boolean.valueOf(lovingTalkElement.getAttribute("additionToDefault"));
									}
									List<DirtyTalkLine> lines = new ArrayList<>();
									for(Element lineElement : lovingTalkElement.getAllOf("line")) {
										SexAreaInterface performing = lineElement.getAttribute("performing").isEmpty()?null:getSexArea(lineElement.getAttribute("performing"));
										SexAreaInterface targeted = lineElement.getAttribute("targeted").isEmpty()?null:getSexArea(lineElement.getAttribute("targeted"));
										boolean performingAnyPenetration = Boolean.valueOf(lineElement.getAttribute("performingAnyPenetration"));
										boolean takingAnyPenetration = Boolean.valueOf(lineElement.getAttribute("takingAnyPenetration"));
										boolean extraNoises = lineElement.getAttribute("extraNoises").isEmpty()?true:Boolean.valueOf(lineElement.getAttribute("extraNoises"));
										
										lines.add(new DirtyTalkLine(performing, targeted, performingAnyPenetration, takingAnyPenetration, extraNoises, lineElement.getTextContent()));
									}
									behaviour.lovingTalk.put(dirtyTalkId, lines);
								}
								// Loving response talk:
								if(dirtyTalkCharacterElement.getOptionalFirstOf("lovingResponse").isPresent()) {
									Element lovingTalkElement = dirtyTalkCharacterElement.getMandatoryFirstOf("lovingResponse");
									if(!lovingTalkElement.getAttribute("additionToDefault").isEmpty()) {
										behaviour.additionToDefaultLovingResponseTalk = Boolean.valueOf(lovingTalkElement.getAttribute("additionToDefault"));
									}
									List<DirtyTalkLine> lines = new ArrayList<>();
									for(Element lineElement : lovingTalkElement.getAllOf("line")) {
										SexAreaInterface performing = lineElement.getAttribute("performing").isEmpty()?null:getSexArea(lineElement.getAttribute("performing"));
										SexAreaInterface targeted = lineElement.getAttribute("targeted").isEmpty()?null:getSexArea(lineElement.getAttribute("targeted"));
										boolean performingAnyPenetration = Boolean.valueOf(lineElement.getAttribute("performingAnyPenetration"));
										boolean takingAnyPenetration = Boolean.valueOf(lineElement.getAttribute("takingAnyPenetration"));
										boolean extraNoises = lineElement.getAttribute("extraNoises").isEmpty()?true:Boolean.valueOf(lineElement.getAttribute("extraNoises"));
										
										lines.add(new DirtyTalkLine(performing, targeted, performingAnyPenetration, takingAnyPenetration, extraNoises, lineElement.getTextContent()));
									}
									behaviour.lovingResponseTalk.put(dirtyTalkId, lines);
								}
							}
						}

						if(elementPresentAndNotEmpty(characterElement, "preferredTarget")) {
							behaviour.preferredTarget = characterElement.getMandatoryFirstOf("preferredTarget").getTextContent();
						}
						

						behaviour.sexClassesIds = new ArrayList<>();
						if(elementPresentAndNotEmpty(characterElement, "uniqueSexActions")) {
							for(Element sexActionElement : characterElement.getMandatoryFirstOf("uniqueSexActions").getAllOf("sexAction")) {
								behaviour.sexClassesIds.add(sexActionElement.getTextContent());
							}
						}

						behaviour.bannedOrgasmCumTargets = new HashMap<>();
						if(characterElement.getOptionalFirstOf("orgasmCumTargetsBlocked").isPresent()) {
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
						
						
						characterBehavioursWithParserIds.put(id, behaviour);
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

		publicSex = publicSexString==null?false:Boolean.valueOf(UtilText.parse(publicSexString).trim());

		for(Entry<String, CharacterBehaviour> entry : characterBehavioursWithParserIds.entrySet()) {
			entry.getValue().initCharacterBehaviour();
		}

		sexTypesBannedMap = new HashMap<>();
		areasBannedMap =  new HashMap<>();
		startingCharactersImmobilised = new HashMap<>();
		startingWetAreas = new HashMap<>();
		
		characterBehaviours = new HashMap<>();
		for(Entry<String, CharacterBehaviour> entry : characterBehavioursWithParserIds.entrySet()) {
			GameCharacter character = UtilText.findFirstCharacterFromParserTarget(entry.getKey());
			// SexTypes banned:
			if(entry.getValue().getSexTypesBanned()!=null) {
				sexTypesBannedMap.put(character, entry.getValue().getSexTypesBanned());
			}
			// Areas banned:
			if(entry.getValue().getAreasBanned()!=null) {
				areasBannedMap.put(character, entry.getValue().getAreasBanned());
			}
			// Starting immobilisations:
			if(entry.getValue().getStartingImmobilisation()!=null) {
				ImmobilisationType type = ImmobilisationType.valueOf(UtilText.parse(entry.getValue().getStartingImmobilisation().getValue()).trim());
				GameCharacter applier = UtilText.findFirstCharacterFromParserTarget(UtilText.parse(entry.getValue().getStartingImmobilisation().getKey()).trim());
				startingCharactersImmobilised.putIfAbsent(type, new HashMap<>());
				startingCharactersImmobilised.get(type).putIfAbsent(applier, new HashSet<>());
				startingCharactersImmobilised.get(type).get(applier).add(character);
			}
			// Starting wet areas:
			if(entry.getValue().getStartingWetAreas()!=null) {
				for(Entry<String, Map<String, Set<String>>> wetAreaEntry : entry.getValue().getStartingWetAreas().entrySet()) {
					SexAreaInterface type = getSexArea(UtilText.parse(wetAreaEntry.getKey()).trim());
					for(Entry<String, Set<String>> innerWetAreaEntry : wetAreaEntry.getValue().entrySet()) {
						GameCharacter applier = UtilText.findFirstCharacterFromParserTarget(UtilText.parse(innerWetAreaEntry.getKey()).trim());
						Set<LubricationType> lubes = new HashSet<>();
						for(String lubeString : innerWetAreaEntry.getValue()) {
							String parsedLube = UtilText.parse(lubeString).trim();
							if(!parsedLube.isEmpty()) {
								lubes.add(LubricationType.valueOf(parsedLube));
							}
						}
						startingWetAreas.putIfAbsent(character, new HashMap<>());
						startingWetAreas.get(character).putIfAbsent(type, new HashMap<>());
						startingWetAreas.get(character).get(type).putIfAbsent(applier, lubes);
					}
				}
			}
			characterBehaviours.put(character.getId(), entry.getValue());
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
	public String getDeskName() {
		return deskName;
	}
	
	@Override
	public String getWallName() {
		return wallName;
	}
	
	@Override
	public boolean isSadisticActionsAllowed() {
		return sadisticActionsAllowed;
	}
	
	@Override
	public boolean isLovingActionsAllowed() {
		return lovingActionsAllowed;
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
	public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
		if(characterBehaviours.containsKey(character.getId()) && !characterBehaviours.get(character.getId()).isCanSwapPositionsBool()) {
			return false;
		}
		return super.isSwapPositionAllowed(character, target);
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
	public boolean isRapePlayBannedAtStart(GameCharacter character) {
		if(characterBehaviours.containsKey(character.getId())) {
			return characterBehaviours.get(character.getId()).isRapePlayBannedAtStartBool();
		}
		return super.isRapePlayBannedAtStart(character);
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
	public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
		return startingCharactersImmobilised;
	}

	@Override
	public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
		return startingWetAreas;
	}
	
	@Override
	public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip){
		if(characterBehaviours.containsKey(equippingCharacter.getId())) {
			return characterBehaviours.get(equippingCharacter.getId()).isSexClothingEquippable(targetedCharacter, clothingToEquip);
		}
		return super.isAbleToEquipSexClothing(equippingCharacter, targetedCharacter, clothingToEquip);
	}

	@Override
	public boolean isAbleToRemoveSelfClothing(GameCharacter character){
		if(characterBehaviours.containsKey(character.getId())) {
			return characterBehaviours.get(character.getId()).isSelfClothingRemovalBool();
		}
		return super.isAbleToRemoveSelfClothing(character);
	}

	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
		if(characterBehaviours.containsKey(character.getId()) && characterBehaviours.get(character.getId()).isPartnerClothingRemovalUsed()) {
			return characterBehaviours.get(character.getId()).isPartnerClothingRemoval(character, clothing);
		}
		return super.isAbleToRemoveOthersClothing(character, clothing);
	}

	@Override
	public boolean isAbleToRemoveClothingSeals(GameCharacter character) {
		if(characterBehaviours.containsKey(character.getId())) {
			return characterBehaviours.get(character.getId()).isCanRemoveClothingSealsBool();
		}
		return super.isAbleToRemoveClothingSeals(character);
	}

	@Override
	public boolean isCharacterStartNaked(GameCharacter character) {
		if(characterBehaviours.containsKey(character.getId())) {
			return characterBehaviours.get(character.getId()).isStartNakedBool();
		}
		return super.isCharacterStartNaked(character);
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
		return super.isExposeAtStartOfSexMapRemoval(character);
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
		if(position==SexPosition.GLORY_HOLE || position==SexPosition.GLORY_HOLE_SEX) {
			return getSlotsConcealedForGloryHole(characterBeingExposed, characterViewing);
		}
		if(characterBehaviours.containsKey(characterBeingExposed.getId())) {
			return characterBehaviours.get(characterBeingExposed.getId()).getConcealedSlots(characterViewing);
		}
		return super.getSlotsConcealed(characterBeingExposed, characterViewing);
	}
	
	/**
	 * Special concealed slots for glory hole, as it was too much of a pain to get this defined in external file...
	 */
	private List<InventorySlot> getSlotsConcealedForGloryHole(GameCharacter characterBeingExposed, GameCharacter characterViewing) {
		List<InventorySlot> concealedSlots = new ArrayList<>();
		
		if(Main.sex.getSexPositionSlot(characterBeingExposed).equals(SexSlotUnique.GLORY_HOLE_KNEELING)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			concealedSlots.remove(InventorySlot.MOUTH);
			return concealedSlots;
			
		} else if(Main.sex.getSexPositionSlot(characterBeingExposed).equals(SexSlotUnique.GLORY_HOLE_FUCKED)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			concealedSlots.remove(InventorySlot.MOUTH);
			concealedSlots.remove(InventorySlot.PENIS);
			concealedSlots.remove(InventorySlot.VAGINA);
			concealedSlots.remove(InventorySlot.GROIN);
			return concealedSlots;
			
		} else if(Main.sex.getSexPositionSlot(characterBeingExposed).equals(SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			concealedSlots.remove(InventorySlot.MOUTH);
			concealedSlots.remove(InventorySlot.ANUS);
			concealedSlots.remove(InventorySlot.GROIN);
			return concealedSlots;
		}
		
		// The ones on the other side of the hole cannot see one another
		if(Main.sex.getSexPositionSlot(characterViewing).equals(SexSlotUnique.GLORY_HOLE_FUCKING)
				|| Main.sex.getSexPositionSlot(characterViewing).equals(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)
				|| Main.sex.getSexPositionSlot(characterViewing).equals(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			return concealedSlots;
		}
		
		if(Main.sex.getSexPositionSlot(characterBeingExposed).equals(SexSlotUnique.GLORY_HOLE_FUCKING)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			if(!characterBeingExposed.isTaur()) {
				concealedSlots.remove(InventorySlot.PENIS);
			}
			concealedSlots.remove(InventorySlot.VAGINA);
			concealedSlots.remove(InventorySlot.GROIN);
			
		} else if(Main.sex.getSexPositionSlot(characterBeingExposed).equals(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)
					|| Main.sex.getSexPositionSlot(characterBeingExposed).equals(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			
			if(!characterBeingExposed.isTaur()) {
				concealedSlots.remove(InventorySlot.PENIS);
			}
			if(characterBeingExposed.getGenitalArrangement()==GenitalArrangement.CLOACA
					|| characterBeingExposed.getGenitalArrangement()==GenitalArrangement.CLOACA_BEHIND) {
				concealedSlots.remove(InventorySlot.ANUS);
				concealedSlots.remove(InventorySlot.PENIS);
			}
			concealedSlots.remove(InventorySlot.VAGINA);
			concealedSlots.remove(InventorySlot.GROIN);
			
		}
		
		return concealedSlots;
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
	public String getLovingTalk(GameCharacter character) {
		if(characterBehaviours.containsKey(character.getId())) {
			GameCharacter target = Main.sex.getTargetedPartner(character);
			if(target!=null && characterBehaviours.get(character.getId()).lovingTalk.containsKey(target.getId())) {
				List<String> lines = new ArrayList<>();
				for(DirtyTalkLine line : characterBehaviours.get(character.getId()).lovingTalk.get(target.getId())) {
					if(line.isConditionalMet(character)) {
						lines.add(line.getParsedContent(character));
					}
				}
				lines.removeIf(l->l.isEmpty());
				if(!lines.isEmpty() && (!characterBehaviours.get(character.getId()).additionToDefaultLovingTalk || Math.random()<0.5f)) {
					return Util.randomItemFrom(lines);
				}
			}
		}
		return character.getLovingTalk();
	}

	@Override
	public String getLovingResponseTalk(GameCharacter character) {
		if(characterBehaviours.containsKey(character.getId())) {
			GameCharacter target = Main.sex.getTargetedPartner(character);
			if(target!=null && characterBehaviours.get(character.getId()).lovingResponseTalk.containsKey(target.getId())) {
				List<String> lines = new ArrayList<>();
				for(DirtyTalkLine line : characterBehaviours.get(character.getId()).lovingResponseTalk.get(target.getId())) {
					if(line.isConditionalMet(character)) {
						lines.add(line.getParsedContent(character));
					}
				}
				lines.removeIf(l->l.isEmpty());
				if(!lines.isEmpty() && (!characterBehaviours.get(character.getId()).additionToDefaultLovingResponseTalk || Math.random()<0.5f)) {
					return Util.randomItemFrom(lines);
				}
			}
		}
		return character.getLovingTalk();
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
	
	// Utility Methods:

	protected SexAreaInterface getSexArea(String areaId) {
		if(areaId.startsWith("ORIFICE_")) {
			return SexAreaOrifice.valueOf(areaId.replace("ORIFICE_", ""));
		} else {
			return SexAreaPenetration.valueOf(areaId.replace("PENETRATION_", ""));
		}
	}
}
