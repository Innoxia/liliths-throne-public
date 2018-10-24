package com.lilithsthrone.game.sex.managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.SexActionUtility;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericActions;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfFingerMouth;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public abstract class SexManagerDefault implements SexManagerInterface {

	private SexPositionType position;
	private Map<GameCharacter, SexPositionSlot> dominants;
	private Map<GameCharacter, SexPositionSlot> submissives;
	protected Map<GameCharacter, List<SexAreaInterface>> orificesBannedMap;
	

	public SexManagerDefault(SexPositionType position, Map<GameCharacter, SexPositionSlot> dominants, Map<GameCharacter, SexPositionSlot> submissives) {
		int totalParticipants = dominants.size() + submissives.size();
		
//		for(GameCharacter character : dominants.keySet()) {
//			if(dominants.get(character)==SexPositionSlot.MISC_WATCHING) {
//				totalParticipants--;
//			}
//		}
//		for(GameCharacter character : submissives.keySet()) {
//			if(submissives.get(character)==SexPositionSlot.MISC_WATCHING) {
//				totalParticipants--;
//			}
//		}
		
		if(totalParticipants > position.getMaximumSlots()) {
			throw new IllegalArgumentException("Too many characters for Sex Manager!");
		}
		
		for(SexPositionSlot slot : position.getAllAvailableSexPositions()) {
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
		
		if (Sex.getActivePartner().getArousal() >= ArousalLevel.FIVE_ORGASM_IMMINENT.getMaximumValue() && SexFlags.playerPreparedForCharactersOrgasm.contains(Sex.getActivePartner())) {
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

		
		// --- Priority 3 | Move into one of the partner's preferred positions ---
		
		boolean suitablePosition = false;
		
		for(GameCharacter character : Sex.getAllParticipants()) {
			if(!character.equals(Sex.getActivePartner())
					&& Sex.getSexPositionSlot(character)!=SexPositionSlot.MISC_WATCHING
					&& Sex.getActivePartner().getSexPositionPreferences(character).contains(Sex.getSexPositionSlot(Sex.getActivePartner()))) {
				suitablePosition = true;
				break;
			}
		}
		
		if(!suitablePosition) {
			for(SexActionInterface action : availableActions) {
				if(action.getActionType()==SexActionType.POSITIONING) {
					possibleActions.add(action);
				}
			}
			// Choose a random position:
			if (!possibleActions.isEmpty()) {
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
		
		GameCharacter targetedCharacter = Sex.getTargetedPartner(Sex.getActivePartner());
		
		// Skip over remove clothing if action is of HIGH or MAX priority
		if(Sex.getAvailableSexActionsPartner().get(0).getPriority()!=SexActionPriority.HIGH
				&& Sex.getAvailableSexActionsPartner().get(0).getPriority()!=SexActionPriority.UNIQUE_MAX) {
				
			List<CoverableArea> targetAreasToBeExposed = new ArrayList<>();
			List<CoverableArea> partnerAreasToBeExposed = new ArrayList<>();
			
			if(Sex.isInForeplay(Sex.getActivePartner())) {
				if(Sex.getActivePartner().getForeplayPreference(targetedCharacter)!=null) {
					SexParticipantType participantType = Sex.getActivePartner().getForeplayPreference(targetedCharacter).getAsParticipant();

					partnerAreasToBeExposed.add(Sex.getActivePartner().getForeplayPreference(targetedCharacter).getPerformingSexArea().getRelatedCoverableArea());
					if(participantType==SexParticipantType.SELF) {
						partnerAreasToBeExposed.add(Sex.getActivePartner().getForeplayPreference(targetedCharacter).getTargetedSexArea().getRelatedCoverableArea());
					} else {
						targetAreasToBeExposed.add(Sex.getActivePartner().getForeplayPreference(targetedCharacter).getTargetedSexArea().getRelatedCoverableArea());
					}
					
				} else {
					
					partnerAreasToBeExposed.add(CoverableArea.NIPPLES);
					partnerAreasToBeExposed.add(CoverableArea.MOUTH);
					
					targetAreasToBeExposed.add(CoverableArea.NIPPLES);
					targetAreasToBeExposed.add(CoverableArea.MOUTH);
					
					if(Math.random()<0.4) {
						partnerAreasToBeExposed.add(CoverableArea.PENIS);
						partnerAreasToBeExposed.add(CoverableArea.VAGINA);
						
						targetAreasToBeExposed.add(CoverableArea.PENIS);
						targetAreasToBeExposed.add(CoverableArea.VAGINA);
					}
				}
				
			} else {
				if(Sex.getActivePartner().getMainSexPreference(targetedCharacter)!=null) {
					SexParticipantType participantType = Sex.getActivePartner().getMainSexPreference(targetedCharacter).getAsParticipant();
					
					partnerAreasToBeExposed.add(Sex.getActivePartner().getMainSexPreference(targetedCharacter).getPerformingSexArea().getRelatedCoverableArea());
					if(participantType==SexParticipantType.SELF) {
						partnerAreasToBeExposed.add(Sex.getActivePartner().getMainSexPreference(targetedCharacter).getTargetedSexArea().getRelatedCoverableArea());
					} else {
						targetAreasToBeExposed.add(Sex.getActivePartner().getMainSexPreference(targetedCharacter).getTargetedSexArea().getRelatedCoverableArea());
					}
					
				} 
				else {
					partnerAreasToBeExposed.add(CoverableArea.PENIS);
					partnerAreasToBeExposed.add(CoverableArea.VAGINA);
					
					targetAreasToBeExposed.add(CoverableArea.PENIS);
					targetAreasToBeExposed.add(CoverableArea.VAGINA);
				}
			}
			
			partnerAreasToBeExposed.removeIf((area) -> (Sex.getActivePartner().isCoverableAreaExposed(area) || !Sex.getActivePartner().isAbleToAccessCoverableArea(area, true))
					|| (area==CoverableArea.PENIS && !Sex.getActivePartner().hasPenis())
					|| (area==CoverableArea.VAGINA && !Sex.getActivePartner().hasVagina()));
			

			targetAreasToBeExposed.removeIf((area) -> (Sex.getTargetedPartner(Sex.getActivePartner()).isCoverableAreaExposed(area) || !Sex.getTargetedPartner(Sex.getActivePartner()).isAbleToAccessCoverableArea(area, true))
					|| (area==CoverableArea.PENIS && !Sex.getTargetedPartner(Sex.getActivePartner()).hasPenis())
					|| (area==CoverableArea.VAGINA && !Sex.getTargetedPartner(Sex.getActivePartner()).hasVagina()));
			
			if(!partnerAreasToBeExposed.isEmpty() && Sex.isCanRemoveSelfClothing(Sex.getActivePartner())) {
				Collections.shuffle(partnerAreasToBeExposed);
				if(partnerAreasToBeExposed.get(0) == CoverableArea.MOUND) {
					return Sex.manageClothingToAccessCoverableArea(Sex.getActivePartner(), Sex.getActivePartner(), CoverableArea.VAGINA);
				} else {
					return Sex.manageClothingToAccessCoverableArea(Sex.getActivePartner(), Sex.getActivePartner(), partnerAreasToBeExposed.get(0));
				}
			}
			if(!targetAreasToBeExposed.isEmpty() && Sex.isCanRemoveOthersClothing(Sex.getActivePartner())) {
				Collections.shuffle(targetAreasToBeExposed);
				if(targetAreasToBeExposed.get(0) == CoverableArea.MOUND) {
					return Sex.manageClothingToAccessCoverableArea(Sex.getActivePartner(), Sex.getTargetedPartner(Sex.getActivePartner()), CoverableArea.VAGINA);
				} else {
					return Sex.manageClothingToAccessCoverableArea(Sex.getActivePartner(), Sex.getTargetedPartner(Sex.getActivePartner()), targetAreasToBeExposed.get(0));
				}
			}
		}
		
		
		// --- Priority 5 | Ban actions that make no sense for the partner to perform ---
		
		// Ban all penetrations if the partner is a virgin in the associated orifice:
		for(SexActionInterface action : availableActions) {
			if(action.getActionType()==SexActionType.START_ONGOING && action.isTakesVirginity(Sex.getCharacterPerformingAction())) {
				for(SexAreaOrifice sArea : action.getPerformingCharacterOrifices()) {
					switch(sArea) {
						case ANUS:
							if(Sex.getCharacterPerformingAction().isAssVirgin() && Sex.getCharacterPerformingAction().getArousal()<ArousalLevel.FOUR_PASSIONATE.getMinimumValue()) {
								bannedActions.add(action);
							}
							break;
						case MOUTH:
							// NPCs don't care about losing oral virginity.
//							if(Sex.getCharacterPerformingAction().isFaceVirgin() && Sex.getCharacterPerformingAction().getArousal()<ArousalLevel.FOUR_PASSIONATE.getMinimumValue()) {
//								bannedActions.add(action);
//							}
							break;
						case NIPPLE:
							if(Sex.getCharacterPerformingAction().isNippleVirgin() && Sex.getCharacterPerformingAction().getArousal()<ArousalLevel.FOUR_PASSIONATE.getMinimumValue()) {
								bannedActions.add(action);
							}
							break;
						case URETHRA_PENIS:
							if(Sex.getCharacterPerformingAction().isUrethraVirgin() && Sex.getCharacterPerformingAction().getArousal()<ArousalLevel.FOUR_PASSIONATE.getMinimumValue()) {
								bannedActions.add(action);
							}
							break;
						case URETHRA_VAGINA:
							if(Sex.getCharacterPerformingAction().isVaginaUrethraVirgin() && Sex.getCharacterPerformingAction().getArousal()<ArousalLevel.FOUR_PASSIONATE.getMinimumValue()) {
								bannedActions.add(action);
							}
							break;
						case VAGINA:
							if(Sex.getCharacterPerformingAction().hasStatusEffect(StatusEffect.FETISH_PURE_VIRGIN)
									|| Sex.getCharacterPerformingAction().hasStatusEffect(StatusEffect.FETISH_PURE_VIRGIN_LUSTY_MAIDEN)
									|| (Sex.getCharacterPerformingAction().isVaginaVirgin() && Sex.getCharacterPerformingAction().getArousal()<ArousalLevel.FOUR_PASSIONATE.getMinimumValue())) {
								bannedActions.add(action);
							}
							break;
						// No virginity to lose:
						case ASS:
							break;
						case BREAST:
							break;
						case THIGHS:
							break;
					}
				}
			}
			
			
			if((action.getSexAreaInteractions().keySet().contains(SexAreaOrifice.ANUS) || action.getSexAreaInteractions().values().contains(SexAreaOrifice.ANUS))) {
				
				if(action.getParticipantType()==SexParticipantType.SELF && !Sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_ANAL_RECEIVING)) {
					bannedActions.add(action); // Ban self-anal actions unless the character loves anal
				}
				
				if(!action.getSexAreaInteractions().keySet().contains(SexAreaPenetration.PENIS)
						&& !action.getSexAreaInteractions().values().contains(SexAreaPenetration.PENIS)
						&& !Sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_ANAL_RECEIVING)) {
					bannedActions.add(action); // Ban non-penis anal actions (like fingering and tail sex) unless the character loves anal
				}
				
			}
		}
		
		
		
		// --- Priority 6 | Perform actions based on foreplay or sex ---
		
		// Perform foreplay action if arousal is < 25 and haven't orgasmed yet:
		SexAction actionToPerform = null;
		if(Sex.isInForeplay(Sex.getCharacterPerformingAction())) {
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
		availableActions.removeIf(si -> !si.isAddedToAvailableSexActions() && !si.isAbleToAccessParts(Sex.getActivePartner()));
		
		bannedActions.add(PartnerSelfFingerMouth.PARTNER_SELF_FINGER_MOUTH_PENETRATION);
		
		if(sexActionPlayer.getActionType()==SexActionType.STOP_ONGOING
				|| sexActionPlayer.equals(GenericActions.PLAYER_FORBID_PARTNER_SELF)
				|| sexActionPlayer.equals(GenericActions.PLAYER_STOP_PARTNER_SELF)
				|| sexActionPlayer.equals(GenericActions.PLAYER_STOP_ALL_PENETRATIONS)) {
			availableActions.removeIf(sexAction -> sexAction.getActionType()==SexActionType.START_ONGOING);
		}
		
		availableActions.removeAll(bannedActions);

//		if(Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.DOGGY_INFRONT
//				|| Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.DOGGY_INFRONT_TWO) {
//			System.out.println(Sex.getActivePartner().getName() +"/"+Sex.getCharacterPerformingAction().getName());
//			if(availableActions.contains(FingerVagina.FINGERED_START)) {
//				System.out.println(" get fingered available");
//			}
//			if(availableActions.contains(FingerPenis.COCK_MASTURBATED_START)) {
//				System.out.println(" get handjob available");
//			}
//		}
		
		List<SexActionInterface> highPriorityList = new ArrayList<>();
		List<SexActionInterface> mediumPriorityList = new ArrayList<>();

		GameCharacter targetedCharacter = Sex.getTargetedPartner(Sex.getActivePartner());
		
		// If the NPC has a preference, they are more likely to choose actions related to that:
		if(Sex.getActivePartner().getForeplayPreference(targetedCharacter)!=null) {
			for(SexActionInterface action : availableActions) {
				if(((action.getPerformingCharacterPenetrations().contains(Sex.getActivePartner().getForeplayPreference(targetedCharacter).getPerformingSexArea())
								&& action.getTargetedCharacterOrifices().contains(Sex.getActivePartner().getForeplayPreference(targetedCharacter).getTargetedSexArea()))
						|| (action.getPerformingCharacterPenetrations().contains(Sex.getActivePartner().getForeplayPreference(targetedCharacter).getTargetedSexArea())
								&& action.getTargetedCharacterOrifices().contains(Sex.getActivePartner().getForeplayPreference(targetedCharacter).getPerformingSexArea()))
						|| (action.getTargetedCharacterPenetrations().contains(Sex.getActivePartner().getForeplayPreference(targetedCharacter).getPerformingSexArea())
								&& action.getPerformingCharacterOrifices().contains(Sex.getActivePartner().getForeplayPreference(targetedCharacter).getTargetedSexArea()))
						|| (action.getTargetedCharacterPenetrations().contains(Sex.getActivePartner().getForeplayPreference(targetedCharacter).getTargetedSexArea())
								&& action.getPerformingCharacterOrifices().contains(Sex.getActivePartner().getForeplayPreference(targetedCharacter).getPerformingSexArea())))
						&& action.getActionType() != SexActionType.STOP_ONGOING
						&& action.getParticipantType() != SexParticipantType.SELF) {
					highPriorityList.add(action);
					if(action.getActionType() == SexActionType.START_ONGOING) { // If a penetrative action is in the list, always return that first.
//						System.out.println(Sex.getActivePartner().getName()+" performs "+action.getActionTitle()+" on "+targetedCharacter.getName());
						return (SexAction) action;
					}
				}
			}
			
			if(!highPriorityList.isEmpty() && Math.random()<0.66f) {
				return (SexAction) highPriorityList.get(Util.random.nextInt(highPriorityList.size()));
			}
		}
		
		highPriorityList.clear();
		for (SexActionInterface action : availableActions) {
			if((action.getActionType() == SexActionType.START_ONGOING || Sex.isAnyNonSelfOngoingActionHappening())
					&& action.getActionType() != SexActionType.STOP_ONGOING
					&& action.getParticipantType()!=SexParticipantType.SELF) {
				if(!action.isTakesVirginity(targetedCharacter)) { // Do not want to take target's virginity in foreplay
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
		
		NPC performingCharacter = (NPC)Sex.getCharacterPerformingAction();
		GameCharacter targetedCharacter = Sex.getTargetedPartner(Sex.getCharacterPerformingAction());
		
		List<SexActionInterface> availableActions = Sex.getAvailableSexActionsPartner();
		availableActions.removeIf(si -> !si.isAddedToAvailableSexActions() && !si.isAbleToAccessParts(performingCharacter));
		
		if(sexActionPlayer.getActionType()==SexActionType.STOP_ONGOING
				|| sexActionPlayer.equals(GenericActions.PLAYER_FORBID_PARTNER_SELF)
				|| sexActionPlayer.equals(GenericActions.PLAYER_STOP_PARTNER_SELF)
				|| sexActionPlayer.equals(GenericActions.PLAYER_STOP_ALL_PENETRATIONS)) {
			availableActions.removeIf(sexAction -> sexAction.getActionType()==SexActionType.START_ONGOING);
		}
		
		bannedActions.add(PartnerSelfFingerMouth.PARTNER_SELF_FINGER_MOUTH_PENETRATION);
		availableActions.removeAll(bannedActions);
		List<SexActionInterface> returnableActions = new ArrayList<>();
		
		boolean isSexPenetration = false;
		boolean isSexPenetrationPossible = false;
		actionLoop:
		for(SexActionInterface action : Sex.getActionsAvailablePartner(performingCharacter, targetedCharacter)) {
			boolean sexPenetration = false;
			boolean sexOrifice = false;
			if(action.getParticipantType()!=SexParticipantType.SELF && (action.isAddedToAvailableSexActions() || action.isAbleToAccessParts(performingCharacter))) {
				for(SexAreaPenetration pen : action.getPerformingCharacterPenetrations()) {
					if(pen.isTakesVirginity()) {
						sexPenetration = true;
					}
				}
				for(SexAreaPenetration pen : action.getTargetedCharacterPenetrations()) {
					if(pen.isTakesVirginity()) {
						sexPenetration = true;
					}
				}
				if(sexPenetration) {
					for(SexAreaOrifice orifice : action.getPerformingCharacterOrifices()) {
						if(orifice.isInternalOrifice()) {
							sexOrifice = true;
						}
					}
					for(SexAreaOrifice orifice : action.getTargetedCharacterOrifices()) {
						if(orifice.isInternalOrifice()) {
							sexOrifice = true;
						}
					}
					if(sexOrifice) {
						isSexPenetrationPossible = true;
						break actionLoop;
					}
				}
			}
		}
		if(isSexPenetrationPossible) {
			isSexPenetrationPossible =
					((performingCharacter.getMainSexPreference(targetedCharacter)==null || performingCharacter.getMainSexPreference(targetedCharacter).getPerformingSexArea()==SexAreaPenetration.PENIS)
							&& performingCharacter.hasPenis() && performingCharacter.isAbleToAccessCoverableArea(CoverableArea.PENIS, true))
					|| ((performingCharacter.getMainSexPreference(targetedCharacter)==null || performingCharacter.getMainSexPreference(targetedCharacter).getPerformingSexArea()==SexAreaPenetration.TAIL)
							&& performingCharacter.getTailType().isSuitableForPenetration())
					|| ((performingCharacter.getMainSexPreference(targetedCharacter)==null || performingCharacter.getMainSexPreference(targetedCharacter).getTargetedSexArea()==SexAreaPenetration.PENIS)
							&& targetedCharacter.hasPenis() && targetedCharacter.isAbleToAccessCoverableArea(CoverableArea.PENIS, true))
					|| ((performingCharacter.getMainSexPreference(targetedCharacter)==null || performingCharacter.getMainSexPreference(targetedCharacter).getTargetedSexArea()==SexAreaPenetration.TAIL)
							&& targetedCharacter.getTailType().isSuitableForPenetration());
		}
		// Is any sexual penetration happening, or is the NPC's preferred penetration happening:
		outerloop:
		for(SexAreaPenetration pen : SexAreaPenetration.values()) {
			if((pen.isTakesVirginity() || (performingCharacter.getMainSexPreference(targetedCharacter)!=null && performingCharacter.getMainSexPreference(targetedCharacter).getPerformingSexArea()==pen))
					&& Sex.getOngoingActionsMap(performingCharacter).get(pen).containsKey(targetedCharacter)) {
				for(SexAreaInterface sai : Sex.getOngoingActionsMap(performingCharacter).get(pen).get(targetedCharacter)) {
					if(sai.isOrifice() && ((SexAreaOrifice) sai).isInternalOrifice()
							|| (performingCharacter.getMainSexPreference(targetedCharacter)!=null && performingCharacter.getMainSexPreference(targetedCharacter).getTargetedSexArea()==sai
									&& (sai!=SexAreaOrifice.ASS || !targetedCharacter.isAbleToAccessCoverableArea(CoverableArea.ANUS, true)))) {
						isSexPenetration = true;
						break outerloop;
					}
				}
			}
			if((pen.isTakesVirginity() || (performingCharacter.getMainSexPreference(targetedCharacter)!=null && performingCharacter.getMainSexPreference(targetedCharacter).getTargetedSexArea()==pen))
					&& Sex.getOngoingActionsMap(targetedCharacter).get(pen).containsKey(performingCharacter)) {
				for(SexAreaInterface sai : Sex.getOngoingActionsMap(targetedCharacter).get(pen).get(performingCharacter)) {
					if(sai.isOrifice() && ((SexAreaOrifice) sai).isInternalOrifice()
							|| (performingCharacter.getMainSexPreference(targetedCharacter)!=null && performingCharacter.getMainSexPreference(targetedCharacter).getPerformingSexArea()==sai
									&& (sai!=SexAreaOrifice.ASS || !targetedCharacter.isAbleToAccessCoverableArea(CoverableArea.ANUS, true)))) {
						isSexPenetration = true;
						break outerloop;
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
			if(performingCharacter.getMainSexPreference(targetedCharacter)!=null) {
				List<SexActionInterface> highPriorityList = new ArrayList<>();
				for(SexActionInterface action : availableActions) {
					if(((action.getPerformingCharacterPenetrations().contains(performingCharacter.getMainSexPreference(targetedCharacter).getPerformingSexArea())
							&& action.getTargetedCharacterOrifices().contains(performingCharacter.getMainSexPreference(targetedCharacter).getTargetedSexArea()))
							|| (action.getTargetedCharacterPenetrations().contains(performingCharacter.getMainSexPreference(targetedCharacter).getTargetedSexArea())
									&& action.getPerformingCharacterOrifices().contains(performingCharacter.getMainSexPreference(targetedCharacter).getPerformingSexArea())))
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
				
				if(penetrativeActionList.isEmpty() && !highPriorityList.isEmpty() && Math.random()<0.66f) { // 2/3 chance, so that there is some chance of using other actions as well:
					return (SexAction) highPriorityList.get(Util.random.nextInt(highPriorityList.size()));
				}
			}
			
			// --- Start penetrating: ---
			for(SexActionInterface action : availableActions) {
				if(action.getActionType()==SexActionType.START_ONGOING
						&& action.getParticipantType()!=SexParticipantType.SELF) {
					
					if(performingCharacter.hasFetish(Fetish.FETISH_IMPREGNATION)
								&& action.getTargetedCharacterOrifices().contains(SexAreaOrifice.VAGINA)
								&& action.getPerformingCharacterPenetrations().contains(SexAreaPenetration.PENIS)) {
						return (SexAction) action; // Instantly return, as this takes priority above everything else
					}
					
					if(performingCharacter.hasFetish(Fetish.FETISH_PREGNANCY)
							&& action.getPerformingCharacterOrifices().contains(SexAreaOrifice.VAGINA)
							&& action.getTargetedCharacterPenetrations().contains(SexAreaPenetration.PENIS)) {
						return (SexAction) action; // Instantly return, as this takes priority above everything else
					}
					
					if(action.getPerformingCharacterPenetrations().contains(SexAreaPenetration.PENIS)
							|| action.getPerformingCharacterPenetrations().contains(SexAreaPenetration.TAIL)
							|| action.getTargetedCharacterPenetrations().contains(SexAreaPenetration.PENIS)
							|| action.getTargetedCharacterPenetrations().contains(SexAreaPenetration.TAIL)) {
						if(!Collections.disjoint(performingCharacter.getFetishes(), action.getFetishes(performingCharacter))) {
							penetrativeActionList.add(action); // Start any penis/tail penetrations that are loved by the performing character
						} else {
							returnableActions.add(action);
						}
					}
				}
			}
			
			// Perform filtering, preferring to return actions that penetrate an actual orifice:
			
			// Prefer penetrativeActionList (used as high priority list):
			penetrativeActionList.removeAll(bannedActions);
			
			List<SexActionInterface> actualOrifices = new ArrayList<>(penetrativeActionList);
			for(SexActionInterface si : penetrativeActionList) {
				boolean orificeFound = false;
				for(SexAreaOrifice orifice : si.getPerformingCharacterOrifices()) {
					if(orifice.isInternalOrifice()) {
						orificeFound = true;
						break;
					}
				}
				for(SexAreaOrifice orifice : si.getTargetedCharacterOrifices()) {
					if(orifice.isInternalOrifice()) {
						orificeFound = true;
						break;
					}
				}
				if(!orificeFound) {
					actualOrifices.remove(si);
				}
			}
			
			List<SexActionInterface> nonPenetrativeOrifices = new ArrayList<>(penetrativeActionList);
			nonPenetrativeOrifices.removeAll(actualOrifices);
			
			if(!actualOrifices.isEmpty()) {
				return (SexAction) actualOrifices.get(Util.random.nextInt(actualOrifices.size()));
				
			} else if(!nonPenetrativeOrifices.isEmpty()) {
				return (SexAction) nonPenetrativeOrifices.get(Util.random.nextInt(nonPenetrativeOrifices.size()));
			}
			
			// If no entries in penetrativeActionList, use returnableActions:
			returnableActions.removeAll(bannedActions);
			
			actualOrifices = new ArrayList<>(returnableActions);
			actualOrifices.removeIf((ac) -> (!ac.getPerformingCharacterOrifices().isEmpty() && !ac.getPerformingCharacterOrifices().get(0).isInternalOrifice())
					|| (!ac.getTargetedCharacterOrifices().isEmpty() && !ac.getTargetedCharacterOrifices().get(0).isInternalOrifice()));
			
			nonPenetrativeOrifices = new ArrayList<>(returnableActions);
			nonPenetrativeOrifices.removeAll(actualOrifices);
			
			if(!actualOrifices.isEmpty()) {
				return (SexAction) actualOrifices.get(Util.random.nextInt(actualOrifices.size()));
				
			} else if(!nonPenetrativeOrifices.isEmpty()) {
				return (SexAction) nonPenetrativeOrifices.get(Util.random.nextInt(nonPenetrativeOrifices.size()));
			}
			
		} else if(!isSexPenetrationPossible) { // If there is no sex penetration available, just use foreplay:
			return performForeplayAction(sexActionPlayer);
		}

		// Ban stop penetration actions:
		for(SexActionInterface action : availableActions) {
			if(action.getActionType() == SexActionType.STOP_ONGOING) {
				if(action.isTakesVirginity(Sex.getCharacterPerformingAction()) || action.isTakesVirginity(Sex.getCharacterTargetedForSexAction(action))) {
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
	
	@Override
	public void assignNPCTarget(GameCharacter targeter) {
		
		List<GameCharacter> availableTargets = new ArrayList<>(Sex.getAllParticipants());
		
		// Always target those who are about to cum:
		if(Sex.isReadyToOrgasm(targeter) && SexFlags.playerPreparedForCharactersOrgasm.contains(targeter)) {
			availableTargets.clear();
			List<GameCharacter> floorTargets = new ArrayList<>();
			
			for(GameCharacter character : Sex.getAllParticipants()) {
				if(!character.equals(targeter) && Sex.isAbleToTarget(character)) {
					List<OrgasmCumTarget> cumTargets = Sex.getSexManager().getPosition().getSexInteractions(Sex.getSexPositionSlot(targeter), Sex.getSexPositionSlot(character)).getAvailableCumTargets();
					if(cumTargets!=null && !cumTargets.isEmpty()) {
						if(cumTargets.size()>1 || cumTargets.get(0)!=OrgasmCumTarget.FLOOR) {
							availableTargets.add(character);
						} else {
							floorTargets.add(character);
						}
					}
				}
			}
			
			if(availableTargets.isEmpty()) {
				availableTargets.addAll(floorTargets);
			}
		}
		
		// If positioning is blocked, then only prioritise characters that actually have actions available that the targeter wants to perform:
		if(!this.isPositionChangingAllowed(targeter)) {
			Map<GameCharacter, SexType> sexPreferences = new HashMap<>();
			
			for(GameCharacter character : availableTargets) {
				if(Sex.getActionsAvailablePartner(targeter, character)!=null && !Sex.getActionsAvailablePartner(targeter, character).isEmpty()) {
					if(Sex.isInForeplay(targeter)) {
						sexPreferences.put(character, ((NPC)targeter).getForeplayPreference(character));
					} else {
						sexPreferences.put(character, ((NPC)targeter).getMainSexPreference(character));
					}
				}
			}
			
			List<GameCharacter> priorityTargets = new ArrayList<>();
			
			characterLoop:
			for(GameCharacter character : availableTargets) {
				SexType preference = sexPreferences.get(character);
				if(preference!=null) {
					for(SexActionInterface action : Sex.isReadyToOrgasm(targeter)?Sex.getOrgasmActionsPartner(targeter, character):Sex.getActionsAvailablePartner(targeter, character)) {
						if(action.getParticipantType()!=SexParticipantType.SELF
								&& (action.getPerformingCharacterOrifices().contains(preference.getPerformingSexArea())
									|| action.getPerformingCharacterPenetrations().contains(preference.getPerformingSexArea()))
								&& (action.getTargetedCharacterOrifices().contains(preference.getTargetedSexArea())
										|| action.getTargetedCharacterPenetrations().contains(preference.getTargetedSexArea()))) {
							priorityTargets.add(character);
//							System.out.println(targeter.getName()+" prioritising "+character.getName());
							continue characterLoop;
						}
					}
				}
			}
			
			if(!priorityTargets.isEmpty()) {
				availableTargets.clear();
				availableTargets.addAll(priorityTargets);
			}
		}
		
		// Select a target:

		Map<GameCharacter, Integer> weightedTargets = new HashMap<>();
		
		for(GameCharacter character : availableTargets) {
			if(!character.equals(targeter) && Sex.isAbleToTarget(character)) {
				
				if(Sex.getActionsAvailablePartner(targeter, character).isEmpty()) { // If no interactions, skip character.
					continue;
				}
				
				Set<SexActionInterface> availableActions = Sex.isReadyToOrgasm(targeter)?Sex.getOrgasmActionsPartner(targeter, character):Sex.getActionsAvailablePartner(targeter, character);
				
				int attractionModifier = 100;
				
				 // If targeter is not attracted to the character, or if the character is in the same dom/sub type, and is not attracted to the targeter, then they are a hundred times less likely to target them:
				if(!targeter.isAttractedTo(character) || (Sex.isDom(targeter)==Sex.isDom(character) && !character.isAttractedTo(targeter))) {
					attractionModifier/=100;
				}
				
				if(!availableActions.isEmpty()) {
					if(Sex.isDom(targeter) != Sex.isDom(character)) {
						weightedTargets.put(character, 5*attractionModifier*availableActions.size()); // Prioritise targeting opposite character by a factor of five (i.e subs target doms and vice versa).
						
					} else {
						if(Sex.getSexPace(targeter)!=SexPace.SUB_RESISTING) { // If resisting, don't target a sub (as resisting actions are targeted towards doms).
							weightedTargets.put(character, 1*attractionModifier*availableActions.size());
						}
					}
				}
			}
		}
		
		if(weightedTargets.isEmpty()) {
			Sex.setTargetedPartner(targeter, Util.randomItemFrom(availableTargets));
		} else {
			Sex.setTargetedPartner(targeter, Util.getRandomObjectFromWeightedMap(weightedTargets));
		}
	}
	
}