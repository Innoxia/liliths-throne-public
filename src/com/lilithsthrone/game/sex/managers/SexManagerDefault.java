package com.lilithsthrone.game.sex.managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.SexActionUtility;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericActions;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfFingerMouth;
import com.lilithsthrone.utils.Util;
import java.util.Set;

/**
 * @since 0.1.0
 * @version 0.2.8
 * @author Innoxia
 */
public abstract class SexManagerDefault implements SexManagerInterface {

	private SexPositionType position;
	private Map<GameCharacter, SexPositionSlot> dominants;
	private Map<GameCharacter, SexPositionSlot> submissives;
	protected Map<GameCharacter, List<SexAreaInterface>> orificesBannedMap;
	
	public SexManagerDefault(SexPositionType position, Map<GameCharacter, SexPositionSlot> dominants, Map<GameCharacter, SexPositionSlot> submissives) {
		if(dominants.size()+submissives.size()>position.getMaximumSlots()) {
			throw new IllegalArgumentException("Too many characters for Sex Manager!");
		}
		
		for(SexPositionSlot slot : position.getSlotTargets().keySet()) {
			int count = 0;
			if(dominants.values().contains(slot)) {
				count++;
			}
			if(submissives.values().contains(slot)) {
				count++;
			}
			if(count>1) {
				throw new IllegalArgumentException("Multiple partners assigned to a single slot!");
			}
		}
		
		this.position = position;
		this.dominants = dominants;
		this.submissives = submissives;
		orificesBannedMap = new HashMap<>();
	}
	
	@Override
	public SexPositionType getPosition() {
		return position;
	}

	@Override
	public Map<GameCharacter, SexPositionSlot> getDominants() {
		return dominants;
	}

	@Override
	public Map<GameCharacter, SexPositionSlot> getSubmissives() {
		return submissives;
	}

	public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap() {
		return orificesBannedMap;
	}
	
	private static List<SexActionInterface> possibleActions = new ArrayList<>(), bannedActions = new ArrayList<>();
	
	/**
	 * New:<br/>
	 * - Get accessible areas<br/>
	 * - Choose foreplay & main sex<br/>
	 * - Choose [npc.verb(position)] for each<br/>
	 * - Clothing for foreplay<br/>
	 * - position<br/>
	 * - foreplay (self-actions take minimum priority)<br/>
	 * - clothing for main<br/>
	 * - position<br/>
	 * - main (self-actions take minimum priority)<br/>
	 * - orgasm
	 */
	@Override
	public SexActionInterface getPartnerSexAction(SexActionInterface sexActionPlayer) {
		
		possibleActions.clear();
		bannedActions.clear();

		List<SexActionInterface> availableActions = Sex.getAvailableSexActionsPartner();
		
		// --- Priority 1 | If orgasming, bypass everything and use an orgasm option ---
		
		if (Sex.getActivePartner().getArousal() >= ArousalLevel.FIVE_ORGASM_IMMINENT.getMaximumValue() && SexFlags.playerPreparedForOrgasm) {
			List<SexActionInterface> priorityOrgasms = new ArrayList<>();
			
			for(SexActionInterface action : availableActions) {
				for(GameCharacter character : Sex.getAllParticipants()) {
					if(action.getAreasCummedIn(Sex.getActivePartner(), character) != null) {
						if((action.getAreasCummedIn(Sex.getActivePartner(), character).contains(SexAreaOrifice.VAGINA)
								&& (Sex.getActivePartner().hasFetish(Fetish.FETISH_IMPREGNATION)))
							|| SexFlags.playerRequestedCreampie) {
							priorityOrgasms.add(action);
							
						} else if(SexFlags.playerRequestedPullOut && (Sex.isConsensual() || Sex.isSubHasEqualControl())) {
							priorityOrgasms.add(action);
						}
					}
					if(action.getAreasCummedIn(character, Sex.getActivePartner()) != null) {
						if((action.getAreasCummedIn(character, Sex.getActivePartner()).contains(SexAreaOrifice.VAGINA)
									&& (Sex.getActivePartner().hasFetish(Fetish.FETISH_PREGNANCY)))) {
							priorityOrgasms.add(action);
							
						}
					}
				}
			}
			
			if(!priorityOrgasms.isEmpty()) {
				return priorityOrgasms.get(Util.random.nextInt(priorityOrgasms.size()));
				
			} else {
				return availableActions.get(Util.random.nextInt(availableActions.size()));
			}
		}
		
		
		// --- Priority 2 | Resisting ---
		
		// If the partner is resisting, they will not want to remove any clothing, and will instead simply use an available option. (Which will be a SUB_RESIST or neutral pace one.)
		if(Sex.getSexPace(Sex.getActivePartner())==SexPace.SUB_RESISTING) {
			possibleActions.addAll(Sex.getAvailableSexActionsPartner());
			
			if (!possibleActions.isEmpty()) {
				return possibleActions.get(Util.random.nextInt(possibleActions.size()));
			} else {
				return SexActionUtility.PARTNER_NONE;
			}
		}

		
		// --- Priority 3 | Move into one of the partner's preferred [npc.verb(position)] ---
		
		if(!Sex.getActivePartner().getSexPositionPreferences().contains(Sex.getSexPositionSlot(Sex.getActivePartner()))) {
			for(SexActionInterface action : availableActions) {
				if(action.getActionType()==SexActionType.POSITIONING) {
					possibleActions.add(action);
				}
			}
			// Choose a random position:
			if (!possibleActions.isEmpty()) {
//				for(SexActionInterface action : availableActions) {
//					if(action.getActionType() == SexActionType.PARTNER_STOP_PENETRATION) {
//						return (SexAction) action;
//					}
//				}
				return possibleActions.get(Util.random.nextInt(possibleActions.size()));
			}
		} else {
			for(SexActionInterface action : availableActions) {
				if(action.getActionType()==SexActionType.POSITIONING) {
					bannedActions.add(action);
				}
			}
		}
		
		
		// --- Priority 4 | Removing clothing ---
		
		// Skip over remove clothing if action is of HIGH or MAX priority
		if(Sex.getAvailableSexActionsPartner().get(0).getPriority()!=SexActionPriority.HIGH
				&& Sex.getAvailableSexActionsPartner().get(0).getPriority()!=SexActionPriority.UNIQUE_MAX) {
				
			List<CoverableArea> playerAreasToBeExposed = new ArrayList<>();
			List<CoverableArea> partnerAreasToBeExposed = new ArrayList<>();
			
			if(Sex.isInForeplay()) {
				if(Sex.getActivePartner().getForeplayPreference()!=null) {
					SexParticipantType participantType = Sex.getActivePartner().getForeplayPreference().getAsParticipant();

					partnerAreasToBeExposed.add(Sex.getActivePartner().getForeplayPreference().getPerformingSexArea().getRelatedCoverableArea());
					if(participantType==SexParticipantType.SELF) {
						partnerAreasToBeExposed.add(Sex.getActivePartner().getForeplayPreference().getTargetedSexArea().getRelatedCoverableArea());
					} else {
						playerAreasToBeExposed.add(Sex.getActivePartner().getForeplayPreference().getTargetedSexArea().getRelatedCoverableArea());
					}
					
				} else {
					partnerAreasToBeExposed.add(CoverableArea.NIPPLES);
					partnerAreasToBeExposed.add(CoverableArea.MOUTH);
					
					playerAreasToBeExposed.add(CoverableArea.NIPPLES);
					playerAreasToBeExposed.add(CoverableArea.MOUTH);
				}
				
			} else {
				if(Sex.getActivePartner().getMainSexPreference()!=null) {
					SexParticipantType participantType = Sex.getActivePartner().getMainSexPreference().getAsParticipant();
					
					partnerAreasToBeExposed.add(Sex.getActivePartner().getMainSexPreference().getPerformingSexArea().getRelatedCoverableArea());
					if(participantType==SexParticipantType.SELF) {
						partnerAreasToBeExposed.add(Sex.getActivePartner().getMainSexPreference().getTargetedSexArea().getRelatedCoverableArea());
					} else {
						playerAreasToBeExposed.add(Sex.getActivePartner().getMainSexPreference().getTargetedSexArea().getRelatedCoverableArea());
					}
					
				} 
				else {
					partnerAreasToBeExposed.add(CoverableArea.PENIS);
					partnerAreasToBeExposed.add(CoverableArea.VAGINA);
					
					playerAreasToBeExposed.add(CoverableArea.PENIS);
					playerAreasToBeExposed.add(CoverableArea.VAGINA);
				}
			}
			
			partnerAreasToBeExposed.removeIf((area) -> (Sex.getActivePartner().isCoverableAreaExposed(area) || !Sex.getActivePartner().isAbleToAccessCoverableArea(area, true))
					|| (area==CoverableArea.PENIS && !Sex.getActivePartner().hasPenisIgnoreDildo())
					|| (area==CoverableArea.VAGINA && !Sex.getActivePartner().hasVagina()));
			

			playerAreasToBeExposed.removeIf((area) -> (Sex.getTargetedPartner(Sex.getActivePartner()).isCoverableAreaExposed(area) || !Sex.getTargetedPartner(Sex.getActivePartner()).isAbleToAccessCoverableArea(area, true))
					|| (area==CoverableArea.PENIS && !Sex.getTargetedPartner(Sex.getActivePartner()).hasPenisIgnoreDildo())
					|| (area==CoverableArea.VAGINA && !Sex.getTargetedPartner(Sex.getActivePartner()).hasVagina()));
			
			if(!partnerAreasToBeExposed.isEmpty() && Sex.isCanRemoveSelfClothing(Sex.getActivePartner())) {
				Collections.shuffle(partnerAreasToBeExposed);
				if(partnerAreasToBeExposed.get(0) == CoverableArea.MOUND) {
					return Sex.manageClothingToAccessCoverableArea(Sex.getActivePartner(), Sex.getActivePartner(), CoverableArea.VAGINA);
				} else {
					return Sex.manageClothingToAccessCoverableArea(Sex.getActivePartner(), Sex.getActivePartner(), partnerAreasToBeExposed.get(0));
				}
			}
			if(!playerAreasToBeExposed.isEmpty() && Sex.isCanRemoveOthersClothing(Sex.getActivePartner())) {
				Collections.shuffle(playerAreasToBeExposed);
				if(playerAreasToBeExposed.get(0) == CoverableArea.MOUND) {
					return Sex.manageClothingToAccessCoverableArea(Sex.getActivePartner(), Sex.getTargetedPartner(Sex.getActivePartner()), CoverableArea.VAGINA);
				} else {
					return Sex.manageClothingToAccessCoverableArea(Sex.getActivePartner(), Sex.getTargetedPartner(Sex.getActivePartner()), playerAreasToBeExposed.get(0));
				}
			}
		}

		
		// --- Priority 5 | Ban actions that make no sense for the partner to perform ---
		
		// Ban all penetrations if the partner is a virgin in the associated orifice:
		for(SexActionInterface action : availableActions) {
			if(action.getActionType()==SexActionType.START_ONGOING
					&& action.isTakesVirginity(Sex.getCharacterTargetedForSexAction(action))) {
				for(SexAreaOrifice sArea : action.getPerformingCharacterOrifices()) {
					switch(sArea) {
						case ANUS:
							if(Sex.getCharacterPerformingAction().isAssVirgin()
									&& (Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_EAGER && !Sex.isDom(Sex.getCharacterPerformingAction()))) {
								bannedActions.add(action);
							}
							break;
						case MOUTH:
							if(Sex.getCharacterPerformingAction().isFaceVirgin()
									&& (Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_EAGER && !Sex.isDom(Sex.getCharacterPerformingAction()))) {
								bannedActions.add(action);
							}
							break;
						case NIPPLE:
							if(Sex.getCharacterPerformingAction().isNippleVirgin()
									&& (Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_EAGER && !Sex.isDom(Sex.getCharacterPerformingAction()))) {
								bannedActions.add(action);
							}
							break;
						case URETHRA_PENIS:
							if(Sex.getCharacterPerformingAction().isUrethraVirgin() && Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_EAGER) {
								bannedActions.add(action);
							}
							break;
						case URETHRA_VAGINA:
							if(Sex.getCharacterPerformingAction().isVaginaUrethraVirgin() && Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_EAGER) {
								bannedActions.add(action);
							}
							break;
						case VAGINA:
							if(Sex.getCharacterPerformingAction().hasStatusEffect(StatusEffect.FETISH_PURE_VIRGIN)
									|| Sex.getCharacterPerformingAction().hasStatusEffect(StatusEffect.FETISH_PURE_VIRGIN_LUSTY_MAIDEN)
									|| (Sex.getCharacterPerformingAction().isVaginaVirgin()
											&& Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_EAGER)) {
								bannedActions.add(action);
							}
							break;
						default:
							break;
					}
				}
			}
		}
		
		
		
		// --- Priority 6 | Perform actions based on foreplay or sex ---
		
		// Perform foreplay action if arousal is < 25 and haven't orgasmed yet:
		SexAction actionToPerform = null;
		if(Sex.isInForeplay()) {
			actionToPerform = performForeplayAction(sexActionPlayer);
			if(actionToPerform!=null) {
				return actionToPerform;
			}
			
		} else {
			actionToPerform = performSexAction(sexActionPlayer);
			if(actionToPerform!=null) {
				return actionToPerform;
			}
		}

		// --- Priority 7 using other options at random ---
		possibleActions.addAll(availableActions);

		possibleActions.removeAll(bannedActions);
		
		if (!possibleActions.isEmpty()) {
			return possibleActions.get(Util.random.nextInt(possibleActions.size()));
		}
		
		// Priority 9 (last resort):
		return SexActionUtility.PARTNER_NONE;
	}

	
	/**
	 * Finger and tongue actions are considered foreplay.
	 */
	private SexAction performForeplayAction(SexActionInterface sexActionPlayer) {
		List<SexActionInterface> availableActions = Sex.getAvailableSexActionsPartner();
		bannedActions.add(PartnerSelfFingerMouth.PARTNER_SELF_FINGER_MOUTH_PENETRATION);
		
		if(sexActionPlayer.getActionType()==SexActionType.STOP_ONGOING
				|| sexActionPlayer.equals(GenericActions.PLAYER_FORBID_PARTNER_SELF)
				|| sexActionPlayer.equals(GenericActions.PLAYER_STOP_PARTNER_SELF)
				|| sexActionPlayer.equals(GenericActions.PLAYER_STOP_ALL_PENETRATIONS)) {
			availableActions.removeIf(sexAction -> sexAction.getActionType()==SexActionType.START_ONGOING);
		}
		
		availableActions.removeAll(bannedActions);

		List<SexActionInterface> highPriorityList = new ArrayList<>();
		List<SexActionInterface> mediumPriorityList = new ArrayList<>();
		
		// If the NPC has a preference, they are more likely to choose actions related to that:
		if(Sex.getActivePartner().getForeplayPreference()!=null) {
			for(SexActionInterface action : availableActions) {
				if(((action.getPerformingCharacterPenetrations().contains(Sex.getActivePartner().getForeplayPreference().getPerformingSexArea())
						&& action.getTargetedCharacterOrifices().contains(Sex.getActivePartner().getForeplayPreference().getTargetedSexArea()))
						|| (action.getTargetedCharacterPenetrations().contains(Sex.getActivePartner().getForeplayPreference().getTargetedSexArea())
								&& action.getPerformingCharacterOrifices().contains(Sex.getActivePartner().getForeplayPreference().getPerformingSexArea())))
						&& action.getActionType() != SexActionType.STOP_ONGOING
						&& action.getParticipantType()!=SexParticipantType.SELF) {
					highPriorityList.add(action);
					if(action.getActionType() == SexActionType.START_ONGOING) { // If a penetrative action is in the list, always return that first.
						return (SexAction) action;
					}
				}
			}
			
			if(!highPriorityList.isEmpty() && Math.random()<0.7f) {
				return (SexAction) highPriorityList.get(Util.random.nextInt(highPriorityList.size()));
			}
		}
		
		highPriorityList.clear();
		for (SexActionInterface action : availableActions) {
			if((action.getActionType() == SexActionType.START_ONGOING || Sex.isAnyNonSelfOngoingActionHappening())
					&& action.getActionType() != SexActionType.STOP_ONGOING
					&& action.getParticipantType()!=SexParticipantType.SELF) {
				if(!action.isTakesVirginity(Sex.getCharacterPerformingAction())) {
					highPriorityList.add(action);
				}
				mediumPriorityList.add(action);
			}
		}
		
		if(!highPriorityList.isEmpty()) {
			return (SexAction) highPriorityList.get(Util.random.nextInt(highPriorityList.size()));
		}

		if(!mediumPriorityList.isEmpty()) {
			return (SexAction) mediumPriorityList.get(Util.random.nextInt(mediumPriorityList.size()));
		}
		
		// --- Ban stop penetration actions ---
		
		for(SexActionInterface action : availableActions) {
			if(action.getActionType() == SexActionType.STOP_ONGOING) {
				bannedActions.add(action);
			}
		}
		
		return null;
	}

//	private boolean removedAllPenetrationAfterForeplay = false;
	
	private SexAction performSexAction(SexActionInterface sexActionPlayer) {
		List<SexActionInterface> availableActions = Sex.getAvailableSexActionsPartner();
		
		if(sexActionPlayer.getActionType()==SexActionType.STOP_ONGOING
				|| sexActionPlayer.equals(GenericActions.PLAYER_FORBID_PARTNER_SELF)
				|| sexActionPlayer.equals(GenericActions.PLAYER_STOP_PARTNER_SELF)
				|| sexActionPlayer.equals(GenericActions.PLAYER_STOP_ALL_PENETRATIONS)) {
			availableActions.removeIf(sexAction -> sexAction.getActionType()==SexActionType.START_ONGOING);
		}
		
		NPC performingCharacter = (NPC)Sex.getCharacterPerformingAction();
		GameCharacter targetedCharacter = Sex.getTargetedPartner(Sex.getCharacterPerformingAction());
		
		bannedActions.add(PartnerSelfFingerMouth.PARTNER_SELF_FINGER_MOUTH_PENETRATION);
		availableActions.removeAll(bannedActions);
		List<SexActionInterface> returnableActions = new ArrayList<>();
		
		boolean isSexPenetration = false;
		boolean isSexPenetrationPossible =
				((performingCharacter.getMainSexPreference()==null || performingCharacter.getMainSexPreference().getPerformingSexArea()==SexAreaPenetration.PENIS)
						&& performingCharacter.hasPenis() && performingCharacter.isAbleToAccessCoverableArea(CoverableArea.PENIS, true))
				|| ((performingCharacter.getMainSexPreference()==null || performingCharacter.getMainSexPreference().getPerformingSexArea()==SexAreaPenetration.TAIL)
						&& performingCharacter.getTailType().isSuitableForPenetration())
				|| ((performingCharacter.getMainSexPreference()==null || performingCharacter.getMainSexPreference().getTargetedSexArea()==SexAreaPenetration.PENIS)
						&& targetedCharacter.hasPenis() && targetedCharacter.isAbleToAccessCoverableArea(CoverableArea.PENIS, true))
				|| ((performingCharacter.getMainSexPreference()==null || performingCharacter.getMainSexPreference().getTargetedSexArea()==SexAreaPenetration.TAIL)
						&& targetedCharacter.getTailType().isSuitableForPenetration());
		
		// Is any sexual penetration happening:
		outerloop:
		for(GameCharacter penetrator : Sex.getAllParticipants()) {
			for(GameCharacter penetrated : Sex.getAllParticipants()) {
				if((penetrator.equals(performingCharacter) || penetrated.equals(performingCharacter)) && !penetrator.equals(penetrated)) {
					for(Map<GameCharacter, Set<SexAreaInterface>> e : Sex.getOngoingActionsMap(penetrator).values()) {
						if(e.containsKey(penetrated)) {
							for(SexAreaInterface sArea : e.get(penetrated)) {
								if(sArea.isPenetration() && ((SexAreaPenetration)sArea).isTakesVirginity()) {
									isSexPenetration = true;
									break outerloop;
								}
							}
						}
					}
				}
			}
		}
		
		// If there is no real penetration going on:
		if(!isSexPenetration && isSexPenetrationPossible) {
			// --- Stop foreplay actions: ---
			for(SexActionInterface action : availableActions) {
				if(action.getActionType() == SexActionType.STOP_ONGOING) {
					// Don't stop kissing or fetishised oral actions:
					if(!((action.getPerformingCharacterPenetrations().contains(SexAreaPenetration.TONGUE)
							|| action.getPerformingCharacterOrifices().contains(SexAreaOrifice.MOUTH))
							&& (action.getTargetedCharacterPenetrations().contains(SexAreaPenetration.TONGUE)
									|| action.getTargetedCharacterOrifices().contains(SexAreaOrifice.MOUTH)))
							&& !(performingCharacter.hasFetish(Fetish.FETISH_ORAL_RECEIVING)
									&& (action.getTargetedCharacterOrifices().contains(SexAreaOrifice.MOUTH) || action.getTargetedCharacterPenetrations().contains(SexAreaPenetration.TONGUE)))
							&& !(performingCharacter.hasFetish(Fetish.FETISH_ORAL_GIVING)
									&& (action.getPerformingCharacterOrifices().contains(SexAreaOrifice.MOUTH) || action.getPerformingCharacterPenetrations().contains(SexAreaPenetration.TONGUE)))) {
						returnableActions.add(action);
					}
				}
			}
			if(returnableActions.size()<=1) {
				SexFlags.positioningBlockedPartner = false;
			}
			if(!returnableActions.isEmpty()) {
				return (SexAction) returnableActions.get(Util.random.nextInt(returnableActions.size()));
			}
			
			// If the NPC has a preference, they are more likely to choose actions related to that:
			List<SexActionInterface> penetrativeActionList = new ArrayList<>();
			if(performingCharacter.getMainSexPreference()!=null) {
				List<SexActionInterface> highPriorityList = new ArrayList<>();
				for(SexActionInterface action : availableActions) {
					if(((action.getPerformingCharacterPenetrations().contains(performingCharacter.getMainSexPreference().getPerformingSexArea())
							&& action.getTargetedCharacterOrifices().contains(performingCharacter.getMainSexPreference().getTargetedSexArea()))
							|| (action.getTargetedCharacterPenetrations().contains(performingCharacter.getMainSexPreference().getTargetedSexArea())
									&& action.getPerformingCharacterOrifices().contains(performingCharacter.getMainSexPreference().getPerformingSexArea())))
							&& action.getParticipantType()!=SexParticipantType.SELF
							&& action.getActionType() != SexActionType.STOP_ONGOING) {
						highPriorityList.add(action);
						if(action.getActionType() == SexActionType.START_ONGOING) { // If a penetrative action is in the list, always return that first.
							penetrativeActionList.add(action);
						}
					}
				}
				
				if(!penetrativeActionList.isEmpty()) {
					return (SexAction) penetrativeActionList.get(Util.random.nextInt(penetrativeActionList.size()));
				}
				
				if(penetrativeActionList.isEmpty() && !highPriorityList.isEmpty() && Math.random()<0.7f) { // 70% chance, so that there is some chance of using other actions as well:
					return (SexAction) highPriorityList.get(Util.random.nextInt(highPriorityList.size()));
				}
			}
			
			// --- Start penetrating: ---
			for(SexActionInterface action : availableActions) {
				if(action.getActionType()==SexActionType.START_ONGOING) {
					if(action.getPerformingCharacterPenetrations().contains(SexAreaPenetration.PENIS)
							|| action.getPerformingCharacterPenetrations().contains(SexAreaPenetration.TAIL)) {
						// Anal penetrations:
						if((performingCharacter.hasFetish(Fetish.FETISH_ANAL_GIVING))
								&& action.getTargetedCharacterOrifices().contains(SexAreaOrifice.ANUS)) {
							penetrativeActionList.add(action);
						}
						// Nipple penetrations:
						if((performingCharacter.hasFetish(Fetish.FETISH_BREASTS_OTHERS))
								&& action.getTargetedCharacterOrifices().contains(SexAreaOrifice.NIPPLE)) {
							penetrativeActionList.add(action);
						}
						// Paizuri:
						if((performingCharacter.hasFetish(Fetish.FETISH_BREASTS_OTHERS))
								&& action.getTargetedCharacterOrifices().contains(SexAreaOrifice.BREAST)) {
								penetrativeActionList.add(action);
						}
						// Vaginal:
						if((performingCharacter.hasFetish(Fetish.FETISH_VAGINAL_GIVING))
								&& action.getTargetedCharacterOrifices().contains(SexAreaOrifice.VAGINA)) {
								penetrativeActionList.add(action);
						}
						// Pregnancy penetration on player:
						if((performingCharacter.hasFetish(Fetish.FETISH_IMPREGNATION))
									&& action.getTargetedCharacterOrifices().contains(SexAreaOrifice.VAGINA) && action.getPerformingCharacterPenetrations().contains(SexAreaPenetration.PENIS)) {
							penetrativeActionList.add(action);
						}
						// Pregnancy penetration for self:
						if((performingCharacter.hasFetish(Fetish.FETISH_PREGNANCY))
								&& action.getPerformingCharacterOrifices().contains(SexAreaOrifice.VAGINA) && action.getTargetedCharacterPenetrations().contains(SexAreaPenetration.PENIS)) {
							penetrativeActionList.add(action);
						}
					}
				}
			}
			if(!penetrativeActionList.isEmpty()) {
				return (SexAction) penetrativeActionList.get(Util.random.nextInt(penetrativeActionList.size()));
			}
			
			
			for(SexActionInterface action : availableActions) {
				if(action.getActionType()==SexActionType.START_ONGOING
						&& action.getParticipantType()!=SexParticipantType.SELF) {
					if(action.getPerformingCharacterPenetrations().contains(SexAreaPenetration.PENIS)
							|| action.getPerformingCharacterPenetrations().contains(SexAreaPenetration.TAIL)) {
						returnableActions.add(action);
					}
				}
			}
			
			returnableActions.removeAll(bannedActions);
			if(!returnableActions.isEmpty()) {
				return (SexAction) returnableActions.get(Util.random.nextInt(returnableActions.size()));
			}
		}

		// Ban stop penetration actions:
		for(SexActionInterface action : availableActions) {
			if(action.getActionType() == SexActionType.STOP_ONGOING) {
				if(action.isTakesVirginity(Sex.getCharacterPerformingAction())) {
					bannedActions.add(action);
				}
				
				if(performingCharacter.hasFetish(Fetish.FETISH_ORAL_RECEIVING)
						&& (action.getTargetedCharacterOrifices().contains(SexAreaOrifice.MOUTH) || action.getTargetedCharacterPenetrations().contains(SexAreaPenetration.TONGUE))) {
					bannedActions.add(action);
				}
				
				if(performingCharacter.hasFetish(Fetish.FETISH_ORAL_GIVING)
						&& (action.getPerformingCharacterOrifices().contains(SexAreaOrifice.MOUTH) || action.getPerformingCharacterPenetrations().contains(SexAreaPenetration.TONGUE))) {
					bannedActions.add(action);
				}
			}
		}
		
		return null;
	}
	
}