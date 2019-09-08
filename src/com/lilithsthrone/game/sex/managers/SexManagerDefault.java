package com.lilithsthrone.game.sex.managers;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.SexActionUtility;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisFeet;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisFoot;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericActions;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfFingerMouth;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.1
 * @author Innoxia
 */
public abstract class SexManagerDefault implements SexManagerInterface {

	protected AbstractSexPosition position;
	protected Map<GameCharacter, SexSlot> dominants;
	protected Map<GameCharacter, SexSlot> submissives;
	protected Map<GameCharacter, List<SexAreaInterface>> areasBannedMap;
	

	public SexManagerDefault(AbstractSexPosition position, Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		
		this.position = position;
		this.dominants = dominants==null?new HashMap<>():dominants;
		this.submissives = submissives==null?new HashMap<>():submissives;
		
		if(position!=null) {
			int totalParticipants = this.dominants.size() + this.submissives.size();
			
			if(totalParticipants > position.getMaximumSlots()) {
				throw new IllegalArgumentException("Too many characters("+totalParticipants+") for Sex Manager("+(position.getMaximumSlots())+")!");
			}
			
			try {
				for(SexSlot slot : position.getAllAvailableSexPositions()) {
					int count = 0;
					if(this.dominants.values().contains(slot)) {
						count++;
					}
					if(this.submissives.values().contains(slot)) {
						count++;
					}
					if(count>1) {
						throw new IllegalArgumentException("Multiple partners assigned to a single slot!");
					}
				}
			} catch(Exception ex) {
//				System.err.println("Error in sex position '"+position.getName()+"': Cannot check if partners are assigned to the same slot.");
			}
		}
		
		areasBannedMap = new HashMap<>();
	}
	
	@Override
	public AbstractSexPosition getPosition() {
		return position;
	}

	@Override
	public Map<GameCharacter, SexSlot> getDominants() {
		return dominants;
	}

	@Override
	public Map<GameCharacter, SexSlot> getSubmissives() {
		return submissives;
	}

	public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap() {
		return areasBannedMap;
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
		
		NPC partner = (NPC) Sex.getCharacterPerformingAction();

		List<SexActionInterface> availableActions = Sex.getAvailableSexActionsPartner();
		
		
		// --- Priority 1 | If orgasming, bypass everything and use an orgasm option ---
		
		if (partner.getArousal() >= ArousalLevel.FIVE_ORGASM_IMMINENT.getMaximumValue() && SexFlags.playerPreparedForCharactersOrgasm.contains(partner)) {
			List<SexActionInterface> priorityOrgasms = new ArrayList<>();
			
			for(SexActionInterface action : availableActions) {
				for(GameCharacter character : Sex.getAllParticipants()) {
					if(action.getAreasCummedIn(partner, character) != null) {
						if((action.getAreasCummedIn(partner, character).contains(SexAreaOrifice.VAGINA) && (partner.hasFetish(Fetish.FETISH_IMPREGNATION))) || Sex.getRequestedPulloutWeighting(partner)<0) {
							priorityOrgasms.add(action);
							
						}
//						else if(SexFlags.characterRequestedPullOut && (Sex.isConsensual() || Sex.getSexControl(partner)==SexControl.FULL)) {
//							priorityOrgasms.add(action);
//						}
					} else {
						if(Sex.getRequestedPulloutWeighting(partner)>0) {
							priorityOrgasms.add(action);
						}
					}
					if(action.getAreasCummedIn(character, partner) != null) {
						if((action.getAreasCummedIn(character, partner).contains(SexAreaOrifice.VAGINA) && (partner.hasFetish(Fetish.FETISH_PREGNANCY)))) {
							priorityOrgasms.add(action);
							
						}
					}
				}
			}
			
			if(!priorityOrgasms.isEmpty()) {
				return priorityOrgasms.get(Util.random.nextInt(priorityOrgasms.size()));
				
			} else {
				if(availableActions.isEmpty()) {
					System.err.println(partner.getName(true)+" has no orgasm actions!!!");
					return GenericOrgasms.PARTNER_GENERIC_ORGASM;
				}
				return availableActions.get(Util.random.nextInt(availableActions.size()));
			}
		}
		
		
		// --- Priority 2 | Resisting ---
		
		// If the partner is resisting, they will not want to remove any clothing, and will instead simply use an available option. (Which will be a SUB_RESIST or neutral pace one.)
		if(Sex.getSexPace(partner)==SexPace.SUB_RESISTING) {
			possibleActions.addAll(Sex.getAvailableSexActionsPartner());
			
			if (!possibleActions.isEmpty()) {
				return possibleActions.get(Util.random.nextInt(possibleActions.size()));
			} else {
				return SexActionUtility.PARTNER_NONE;
			}
		}

		
		// --- Priority 3 | Move into one of the partner's preferred positions ---
		
		if(Sex.isSexLeader(partner)) { // Only the leader should be able to switch positions:
			boolean suitablePosition = false;
			
			List<SexActionInterface> highPriorityActions = new ArrayList<>();
			for(SexActionInterface action : availableActions) {
				if(action.getActionType()==SexActionType.POSITIONING) {
					if(action.getPriority()==SexActionPriority.HIGH) {
						highPriorityActions.add(action);
					} else {
						possibleActions.add(action);
					}
				}
			}
			
			for(GameCharacter character : Sex.getAllParticipants()) {
				if(!character.equals(partner)
						&& Sex.getSexPositionSlot(character)!=SexSlotGeneric.MISC_WATCHING
						&& partner.isHappyToBeInSlot(Sex.getPosition(), Sex.getSexPositionSlot(partner), Sex.getSexPositionSlot(character), character)
//						&& highPriorityActions.isEmpty()
						) {
//					System.out.println("Happy in slot");
					suitablePosition = true;
					break;
				} else {
//					System.out.println("Not happy in slot");
				}
			}
			
			if(!suitablePosition && Sex.getLastUsedPlayerAction().getActionType()!=SexActionType.POSITIONING) {
				// Choose a random position:
				if (!highPriorityActions.isEmpty()) {
					return Util.randomItemFrom(highPriorityActions);
				}
				if (!possibleActions.isEmpty()) {
					return Util.randomItemFrom(possibleActions);
				}
				
			} else {
				for(SexActionInterface action : availableActions) {
					if(action.getActionType()==SexActionType.POSITIONING) {
						bannedActions.add(action);
					}
				}
			}
		}
		
		// --- Priority 4 | Removing clothing ---
		
		GameCharacter targetedCharacter = Sex.getTargetedPartner(partner);
		
		// Skip over remove clothing if action is of HIGH or MAX priority
		if(!availableActions.isEmpty()
				&& availableActions.get(0).getPriority()!=SexActionPriority.HIGH
				&& availableActions.get(0).getPriority()!=SexActionPriority.UNIQUE_MAX) {
				
			List<CoverableArea> targetAreasToBeExposed = new ArrayList<>();
			List<CoverableArea> partnerAreasToBeExposed = new ArrayList<>();

			targetAreasToBeExposed.add(CoverableArea.NIPPLES);
			if(Sex.isConsensual() || partner.hasBreasts()) {
				partnerAreasToBeExposed.add(CoverableArea.NIPPLES);
			}
			
			if(Sex.isInForeplay(partner)) {
				if(Sex.getForeplayPreference(partner, targetedCharacter)!=null) {
					SexParticipantType participantType = Sex.getForeplayPreference(partner, targetedCharacter).getAsParticipant();

					partnerAreasToBeExposed.add(Sex.getForeplayPreference(partner, targetedCharacter).getPerformingSexArea().getRelatedCoverableArea());
					if(participantType==SexParticipantType.SELF) {
						partnerAreasToBeExposed.add(Sex.getForeplayPreference(partner, targetedCharacter).getTargetedSexArea().getRelatedCoverableArea());
					} else {
						targetAreasToBeExposed.add(Sex.getForeplayPreference(partner, targetedCharacter).getTargetedSexArea().getRelatedCoverableArea());
					}
					
				} else {
					partnerAreasToBeExposed.add(CoverableArea.MOUTH);
					
					targetAreasToBeExposed.add(CoverableArea.MOUTH);
					
					if(Math.random()<0.4) {
						partnerAreasToBeExposed.add(CoverableArea.PENIS);
						partnerAreasToBeExposed.add(CoverableArea.VAGINA);
						
						targetAreasToBeExposed.add(CoverableArea.PENIS);
						targetAreasToBeExposed.add(CoverableArea.VAGINA);
					}
				}
				
			} else {
				if(partner.getMainSexPreference(targetedCharacter)!=null) {
					SexParticipantType participantType = partner.getMainSexPreference(targetedCharacter).getAsParticipant();
					
					partnerAreasToBeExposed.add(partner.getMainSexPreference(targetedCharacter).getPerformingSexArea().getRelatedCoverableArea());
					if(participantType==SexParticipantType.SELF) {
						partnerAreasToBeExposed.add(partner.getMainSexPreference(targetedCharacter).getTargetedSexArea().getRelatedCoverableArea());
					} else {
						targetAreasToBeExposed.add(partner.getMainSexPreference(targetedCharacter).getTargetedSexArea().getRelatedCoverableArea());
					}
					
				} else {
					partnerAreasToBeExposed.add(CoverableArea.PENIS);
					partnerAreasToBeExposed.add(CoverableArea.VAGINA);
					
					targetAreasToBeExposed.add(CoverableArea.PENIS);
					targetAreasToBeExposed.add(CoverableArea.VAGINA);
				}
			}
			
			if((Sex.getActionsAvailablePartner(partner, targetedCharacter).contains(PenisFoot.FOOT_JOB_SINGLE_GIVING_START)
							&& partner.calculateSexTypeWeighting(PenisFoot.FOOT_JOB_SINGLE_GIVING_START.getAsSexType(), targetedCharacter, null)>0)
					|| (Sex.getActionsAvailablePartner(partner, targetedCharacter).contains(PenisFeet.FOOT_JOB_DOUBLE_GIVING_START)
							&& partner.calculateSexTypeWeighting(PenisFeet.FOOT_JOB_DOUBLE_GIVING_START.getAsSexType(), targetedCharacter, null)>0)) {
				partnerAreasToBeExposed.add(CoverableArea.FEET);
			}

			if((Sex.getActionsAvailablePartner(partner, targetedCharacter).contains(PenisFoot.FOOT_JOB_SINGLE_RECEIVING_START)
						&& partner.calculateSexTypeWeighting(PenisFoot.FOOT_JOB_SINGLE_RECEIVING_START.getAsSexType(), targetedCharacter, null)>0)
				|| (Sex.getActionsAvailablePartner(partner, targetedCharacter).contains(PenisFeet.FOOT_JOB_DOUBLE_RECEIVING_START)
						&& partner.calculateSexTypeWeighting(PenisFeet.FOOT_JOB_DOUBLE_RECEIVING_START.getAsSexType(), targetedCharacter, null)>0)) {
				targetAreasToBeExposed.add(CoverableArea.FEET);
			}
			
			partnerAreasToBeExposed.removeIf((area) -> (partner.isCoverableAreaExposed(area) || !partner.isAbleToAccessCoverableArea(area, true))
					|| (area==CoverableArea.PENIS && !partner.hasPenis())
					|| (area==CoverableArea.VAGINA && !partner.hasVagina()));

			partnerAreasToBeExposed.removeIf((area) -> (area==CoverableArea.FEET && (partner.getFetishDesire(Fetish.FETISH_SADIST).isPositive() || partner.getClothingInSlot(InventorySlot.FOOT)==null)));
			targetAreasToBeExposed.removeIf((area) -> (area==CoverableArea.FEET && (partner.getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() || targetedCharacter.getClothingInSlot(InventorySlot.FOOT)==null)));
			
			targetAreasToBeExposed.removeIf((area) -> (targetedCharacter.isCoverableAreaExposed(area) || !targetedCharacter.isAbleToAccessCoverableArea(area, true))
					|| (area==CoverableArea.PENIS && !targetedCharacter.hasPenis())
					|| (area==CoverableArea.VAGINA && !targetedCharacter.hasVagina()));
			
			if(!partnerAreasToBeExposed.isEmpty() && Sex.isCanRemoveSelfClothing(partner)) {
				Collections.shuffle(partnerAreasToBeExposed);
				CoverableArea exposeArea = partnerAreasToBeExposed.get(0);
				if(exposeArea==CoverableArea.MOUND) {
					exposeArea = CoverableArea.VAGINA;
				}
				SexType preference = partner.getCurrentSexPreference(targetedCharacter);
				// Only displace clothing if its the desired area, or if the clothing to be displaced is not a sex toy:
				SimpleEntry<AbstractClothing, DisplacementType> clothingToRemove = partner.getNextClothingToRemoveForCoverableAreaAccess(exposeArea);
				if((preference!=null && preference.getPerformingSexArea().getRelatedCoverableArea()==exposeArea) //TODO
						|| (clothingToRemove!=null && !clothingToRemove.getKey().getClothingType().isSexToy(clothingToRemove.getKey().getSlotEquippedTo()))) {
					return Sex.manageClothingToAccessCoverableArea(partner, partner, exposeArea);
				}
			}
			
			if(!Sex.isSpectator(partner) && !targetAreasToBeExposed.isEmpty() && Sex.isCanRemoveOthersClothing(partner, null)) {
				Collections.shuffle(targetAreasToBeExposed);
				List<CoverableArea> areas = new ArrayList<>(targetAreasToBeExposed);
				for(CoverableArea area : areas) {
					SimpleEntry<AbstractClothing, DisplacementType> clothingRemoval = targetedCharacter.getNextClothingToRemoveForCoverableAreaAccess(area);
					if(clothingRemoval==null || !Sex.isCanRemoveOthersClothing(partner, clothingRemoval.getKey())) {
						targetAreasToBeExposed.remove(area);
					}
				}

				if(!targetAreasToBeExposed.isEmpty()) {
					CoverableArea exposeArea = targetAreasToBeExposed.get(0);
					if(exposeArea==CoverableArea.MOUND) {
						exposeArea = CoverableArea.VAGINA;
					}
					SexType preference = partner.getCurrentSexPreference(targetedCharacter);
					// Only displace clothing if its the desired area, or if the clothing to be displaced is not a sex toy:
					SimpleEntry<AbstractClothing, DisplacementType> clothingToRemove = targetedCharacter.getNextClothingToRemoveForCoverableAreaAccess(exposeArea);
					if((preference!=null && preference.getTargetedSexArea().getRelatedCoverableArea()==exposeArea)
							|| (clothingToRemove!=null && !clothingToRemove.getKey().getClothingType().isSexToy(clothingToRemove.getKey().getSlotEquippedTo()))) {
						return Sex.manageClothingToAccessCoverableArea(partner, targetedCharacter, exposeArea);
					}
				}
			}
		}
		
		
		// --- Priority 5 | Ban actions that make no sense for the partner to perform ---
		
		// Ban all penetrations if the partner is a virgin in the associated orifice:
		for(SexActionInterface action : availableActions) {
			if(action.getActionType()==SexActionType.START_ONGOING && action.isTakesVirginity(true)) {
				for(SexAreaOrifice sArea : action.getPerformingCharacterOrifices()) {
					switch(sArea) {
						case ANUS:
							if(Sex.getCharacterPerformingAction().isAssVirgin() && (Sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue() || action.getParticipantType()==SexParticipantType.SELF)) {
								bannedActions.add(action);
							}
							break;
						case MOUTH:
							// NPCs don't care about losing oral virginity.
							break;
						case NIPPLE:
							if(Sex.getCharacterPerformingAction().isNippleVirgin() && (Sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue() || action.getParticipantType()==SexParticipantType.SELF)) {
								bannedActions.add(action);
							}
							break;
						case NIPPLE_CROTCH:
							if(Sex.getCharacterPerformingAction().isNippleCrotchVirgin() && (Sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue() || action.getParticipantType()==SexParticipantType.SELF)) {
								bannedActions.add(action);
							}
							break;
						case URETHRA_PENIS:
							if(Sex.getCharacterPerformingAction().isUrethraVirgin() && (Sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue() || action.getParticipantType()==SexParticipantType.SELF)) {
								bannedActions.add(action);
							}
							break;
						case URETHRA_VAGINA:
							if(Sex.getCharacterPerformingAction().isVaginaUrethraVirgin() && (Sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue() || action.getParticipantType()==SexParticipantType.SELF)) {
								bannedActions.add(action);
							}
							break;
						case VAGINA:
							if(Sex.getCharacterPerformingAction().hasStatusEffect(StatusEffect.FETISH_PURE_VIRGIN)
									|| Sex.getCharacterPerformingAction().hasStatusEffect(StatusEffect.FETISH_PURE_VIRGIN_LUSTY_MAIDEN)
									|| (Sex.getCharacterPerformingAction().isVaginaVirgin() && (Sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue() || action.getParticipantType()==SexParticipantType.SELF))) {
								bannedActions.add(action);
							}
							break;
						// No virginity to lose:
						case ASS:
						case BREAST:
						case BREAST_CROTCH:
						case THIGHS:
							break;
					}
				}
				if(action.getParticipantType()==SexParticipantType.SELF) {
					for(SexAreaOrifice sArea : action.getTargetedCharacterOrifices()) {
						switch(sArea) {
							case ANUS:
								if(Sex.getCharacterPerformingAction().isAssVirgin()) {// && Sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue()) {
									bannedActions.add(action);
								}
								break;
							case MOUTH:
								// NPCs don't care about losing oral virginity.
								break;
							case NIPPLE:
								if(Sex.getCharacterPerformingAction().isNippleVirgin()) {// && Sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue()) {
									bannedActions.add(action);
								}
								break;
							case NIPPLE_CROTCH:
								if(Sex.getCharacterPerformingAction().isNippleCrotchVirgin()) {// && Sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue()) {
									bannedActions.add(action);
								}
								break;
							case URETHRA_PENIS:
								if(Sex.getCharacterPerformingAction().isUrethraVirgin()) {// && Sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue()) {
									bannedActions.add(action);
								}
								break;
							case URETHRA_VAGINA:
								if(Sex.getCharacterPerformingAction().isVaginaUrethraVirgin()) {// && Sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue()) {
									bannedActions.add(action);
								}
								break;
							case VAGINA:
								if(Sex.getCharacterPerformingAction().hasStatusEffect(StatusEffect.FETISH_PURE_VIRGIN)
										|| Sex.getCharacterPerformingAction().hasStatusEffect(StatusEffect.FETISH_PURE_VIRGIN_LUSTY_MAIDEN)
										|| (Sex.getCharacterPerformingAction().isVaginaVirgin())) {// && Sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue())) {
									bannedActions.add(action);
								}
								break;
							// No virginity to lose:
							case ASS:
							case BREAST:
							case BREAST_CROTCH:
							case THIGHS:
								break;
						}
					}
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
		boolean debug = false;

		NPC performingCharacter = (NPC)Sex.getCharacterPerformingAction();
		GameCharacter targetedCharacter = Sex.getTargetedPartner(Sex.getCharacterPerformingAction());
		
		List<SexActionInterface> availableActions = new ArrayList<>(Sex.getAvailableSexActionsPartner());
		availableActions.removeIf(si -> !si.isAddedToAvailableSexActions() && !si.isAbleToAccessParts(performingCharacter));
		
		bannedActions.add(PartnerSelfFingerMouth.PARTNER_SELF_FINGER_MOUTH_PENETRATION);
		
		if(sexActionPlayer.getActionType()==SexActionType.STOP_ONGOING
				|| sexActionPlayer.equals(GenericActions.PLAYER_FORBID_PARTNER_SELF)
				|| sexActionPlayer.equals(GenericActions.PLAYER_STOP_PARTNER_SELF)
				|| sexActionPlayer.equals(GenericActions.PLAYER_STOP_ALL_PENETRATIONS)
				|| sexActionPlayer.equals(GenericActions.PLAYER_STOP_ALL_PENETRATIONS_SELF)) {
			availableActions.removeIf(sexAction -> sexAction.getActionType()==SexActionType.START_ONGOING || sexAction.getActionType()==SexActionType.START_ADDITIONAL_ONGOING);
//			System.out.println("hmm");
		}
		
		availableActions.removeAll(bannedActions);
		
		List<SexActionInterface> highPriorityList = new ArrayList<>();
		List<SexActionInterface> mediumPriorityList = new ArrayList<>();
		
		// If the NPC has a preference, they are more likely to choose actions related to that:
		SexType foreplayPreference = Sex.getForeplayPreference(performingCharacter, targetedCharacter);
		if(!Sex.isInForeplay(performingCharacter)) { // Use main sex preference if in main sex (as this method can be called as a fallthrough from performSexAction())
			foreplayPreference = Sex.getMainSexPreference(performingCharacter, targetedCharacter);
		}
		if(debug) {
			System.out.println(performingCharacter.getName(true)+" wants to use: "+foreplayPreference.toString());
		}
		if(foreplayPreference!=null) {
			for(SexActionInterface action : availableActions) {
				if((action.getPerformingCharacterAreas().contains(foreplayPreference.getPerformingSexArea()) && action.getTargetedCharacterAreas().contains(foreplayPreference.getTargetedSexArea()))
//						((action.getPerformingCharacterPenetrations().contains(foreplayPreference.getPerformingSexArea()) && action.getTargetedCharacterOrifices().contains(foreplayPreference.getTargetedSexArea()))
//						|| (action.getPerformingCharacterPenetrations().contains(foreplayPreference.getTargetedSexArea()) && action.getTargetedCharacterOrifices().contains(foreplayPreference.getPerformingSexArea()))
//						|| (action.getTargetedCharacterPenetrations().contains(foreplayPreference.getPerformingSexArea()) && action.getPerformingCharacterOrifices().contains(foreplayPreference.getTargetedSexArea()))
//						|| (action.getTargetedCharacterPenetrations().contains(foreplayPreference.getTargetedSexArea()) && action.getPerformingCharacterOrifices().contains(foreplayPreference.getPerformingSexArea())))
						&& action.getActionType() != SexActionType.STOP_ONGOING
						&& action.getParticipantType() != SexParticipantType.SELF) {
					highPriorityList.add(action);
					if(action.getActionType() == SexActionType.START_ONGOING
							|| action.getActionType()==SexActionType.START_ADDITIONAL_ONGOING) { // If a penetrative action is in the list, always return that first.
						if(debug) {
							System.out.println(performingCharacter.getName(true)+" performs "+action.getActionTitle()+" on "+targetedCharacter.getName(true));
						}
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
			if((action.getActionType() == SexActionType.START_ONGOING || action.getActionType()==SexActionType.START_ADDITIONAL_ONGOING || Sex.isCharacterEngagedInOngoingAction(performingCharacter, targetedCharacter))
					&& action.getActionType() != SexActionType.STOP_ONGOING
					&& action.getParticipantType()!=SexParticipantType.SELF) {
				if(!action.isTakesVirginity(false)) { // Do not want to take any virginity in foreplay TODO?
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

		boolean debug = false;
		boolean debugFullActionList = false;

		if(debug) {
			System.out.println();
			System.out.println("Character: "+performingCharacter.getName(true));
			System.out.println("Target: "+targetedCharacter.getName(true));
		}
		
		List<SexActionInterface> availableActions = new ArrayList<>(Sex.getAvailableSexActionsPartner()); //TODO need to check this
//		List<SexActionInterface> availableActions = new ArrayList<>(Sex.getActionsAvailablePartner(performingCharacter, targetedCharacter));
		availableActions.removeIf(si -> !si.isAddedToAvailableSexActions() && !si.isAbleToAccessParts(performingCharacter));
		
		if(sexActionPlayer.getActionType()==SexActionType.STOP_ONGOING
				|| sexActionPlayer.equals(GenericActions.PLAYER_FORBID_PARTNER_SELF)
				|| sexActionPlayer.equals(GenericActions.PLAYER_STOP_PARTNER_SELF)
				|| sexActionPlayer.equals(GenericActions.PLAYER_STOP_ALL_PENETRATIONS)
				|| sexActionPlayer.equals(GenericActions.PLAYER_STOP_ALL_PENETRATIONS_SELF)) {
			availableActions.removeIf(sexAction -> sexAction.getActionType()==SexActionType.START_ONGOING || sexAction.getActionType()==SexActionType.START_ADDITIONAL_ONGOING);
		}
		
		bannedActions.add(PartnerSelfFingerMouth.PARTNER_SELF_FINGER_MOUTH_PENETRATION);
		availableActions.removeAll(bannedActions);
		List<SexActionInterface> returnableActions = new ArrayList<>();
		
		boolean isSexPenetrationPossible = false;
		actionLoop:
		for(SexActionInterface action : Sex.getActionsAvailablePartner(performingCharacter, targetedCharacter)) {
//		for(SexActionInterface action : availableActions) { //TODO need to check this
			boolean penetrationAction = false;
			boolean sexOrifice = false;
			if(action.getParticipantType()!=SexParticipantType.SELF
					&& (action.getActionType()==SexActionType.START_ONGOING || action.getActionType()==SexActionType.START_ADDITIONAL_ONGOING)
					&& performingCharacter.calculateSexTypeWeighting(action.getAsSexType(), targetedCharacter, null)>0
					&& (action.isAddedToAvailableSexActions() || action.isAbleToAccessParts(performingCharacter))) {
				if(debugFullActionList) {
					System.out.println("A ");
				}
				for(SexAreaPenetration pen : action.getPerformingCharacterPenetrations()) {
					if(pen.isTakesVirginity()) {
						penetrationAction = true;
					}
				}
				for(SexAreaPenetration pen : action.getTargetedCharacterPenetrations()) {
					if(pen.isTakesVirginity()) {
						penetrationAction = true;
					}
				}
				if(penetrationAction) {
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
			} else {
				if(debugFullActionList) {
					System.out.print("U ");
				}
			}
			if(debugFullActionList) {
				System.out.print("action: "+action.getActionTitle()+"\n");
			}
		}
		if(isSexPenetrationPossible) {
			isSexPenetrationPossible =
					((Sex.getMainSexPreference(performingCharacter, targetedCharacter)==null || Sex.getMainSexPreference(performingCharacter, targetedCharacter).getPerformingSexArea()==SexAreaPenetration.PENIS)
							&& performingCharacter.hasPenis() && performingCharacter.isAbleToAccessCoverableArea(CoverableArea.PENIS, true))
					|| ((Sex.getMainSexPreference(performingCharacter, targetedCharacter)==null || Sex.getMainSexPreference(performingCharacter, targetedCharacter).getPerformingSexArea()==SexAreaPenetration.TAIL)
							&& performingCharacter.getTailType().isSuitableForPenetration())
					|| ((Sex.getMainSexPreference(performingCharacter, targetedCharacter)==null || Sex.getMainSexPreference(performingCharacter, targetedCharacter).getTargetedSexArea()==SexAreaPenetration.PENIS)
							&& targetedCharacter.hasPenis() && targetedCharacter.isAbleToAccessCoverableArea(CoverableArea.PENIS, true))
					|| ((Sex.getMainSexPreference(performingCharacter, targetedCharacter)==null || Sex.getMainSexPreference(performingCharacter, targetedCharacter).getTargetedSexArea()==SexAreaPenetration.TAIL)
							&& targetedCharacter.getTailType().isSuitableForPenetration());
		}
		
		boolean isOngoingSexPenetration = false;
		// Is any sexual penetration happening, or is the NPC's preferred penetration happening:
		outerloop:
		for(SexAreaPenetration pen : SexAreaPenetration.values()) {
			if((pen.isTakesVirginity() || (Sex.getMainSexPreference(performingCharacter, targetedCharacter)!=null && Sex.getMainSexPreference(performingCharacter, targetedCharacter).getPerformingSexArea()==pen))
					&& Sex.getOngoingActionsMap(performingCharacter).get(pen).containsKey(targetedCharacter)) {
				for(SexAreaInterface sai : Sex.getOngoingActionsMap(performingCharacter).get(pen).get(targetedCharacter)) {
					if((sai.isOrifice() && ((SexAreaOrifice) sai).isInternalOrifice())
							|| (Sex.getMainSexPreference(performingCharacter, targetedCharacter)!=null && Sex.getMainSexPreference(performingCharacter, targetedCharacter).getTargetedSexArea()==sai
									&& (sai!=SexAreaOrifice.ASS || !targetedCharacter.isAbleToAccessCoverableArea(CoverableArea.ANUS, true)))) {
						isOngoingSexPenetration = true;
						if(debug) {
							System.out.println(performingCharacter.getName(true)+" isOngoingSexPenetration found 1");
						}
						break outerloop;
					}
				}
			}
			if((pen.isTakesVirginity() || (Sex.getMainSexPreference(performingCharacter, targetedCharacter)!=null && Sex.getMainSexPreference(performingCharacter, targetedCharacter).getTargetedSexArea()==pen))
					&& Sex.getOngoingActionsMap(targetedCharacter).get(pen).containsKey(performingCharacter)) {
				for(SexAreaInterface sai : Sex.getOngoingActionsMap(targetedCharacter).get(pen).get(performingCharacter)) {
					if((sai.isOrifice() && ((SexAreaOrifice) sai).isInternalOrifice())
							|| (Sex.getMainSexPreference(performingCharacter, targetedCharacter)!=null && Sex.getMainSexPreference(performingCharacter, targetedCharacter).getPerformingSexArea()==sai
									&& (sai!=SexAreaOrifice.ASS || !targetedCharacter.isAbleToAccessCoverableArea(CoverableArea.ANUS, true)))) {
						isOngoingSexPenetration = true;
						if(debug) {
							System.out.println(performingCharacter.getName(true)+" isOngoingSexPenetration found 2");
						}
						break outerloop;
					}
				}
			}
		}
		
		// If there is no real penetration going on:
		if(!isOngoingSexPenetration) {
			if(debug) {
				System.out.println("!isOngoingSexPenetration");
			}
			if(isSexPenetrationPossible) {
				if(debug) {
					System.out.println("isSexPenetrationPossible");
				}
				// --- Stop foreplay actions: ---
				for(SexActionInterface action : availableActions) {
					if(action.getActionType() == SexActionType.STOP_ONGOING) {
						// Don't stop kissing or fetishised oral actions:
						if(Sex.getMainSexPreference(performingCharacter, targetedCharacter)==null) { //TODO More testing
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
						} else {
							returnableActions.add(action);
						}
					}
				}
				if(returnableActions.size()<=1) {
					Sex.removeCharacterBannedFromPositioning(performingCharacter);
	//				SexFlags.positioningBlockedPartner = false;
				}
				if(!returnableActions.isEmpty()) {
					return (SexAction) returnableActions.get(Util.random.nextInt(returnableActions.size()));
				}

				SexType mainSexPreference = Sex.getMainSexPreference(performingCharacter, targetedCharacter);
				
				// If the NPC has a preference, they are more likely to choose actions related to that:
				List<SexActionInterface> penetrativeActionList = new ArrayList<>();
				if(mainSexPreference!=null) {
					List<SexActionInterface> highPriorityList = new ArrayList<>();
					for(SexActionInterface action : availableActions) {
						if(
//								((action.getPerformingCharacterPenetrations().contains(Sex.getMainSexPreference(performingCharacter, targetedCharacter).getPerformingSexArea())
//								&& action.getTargetedCharacterOrifices().contains(Sex.getMainSexPreference(performingCharacter, targetedCharacter).getTargetedSexArea()))
//								|| (action.getTargetedCharacterPenetrations().contains(Sex.getMainSexPreference(performingCharacter, targetedCharacter).getTargetedSexArea())
//										&& action.getPerformingCharacterOrifices().contains(Sex.getMainSexPreference(performingCharacter, targetedCharacter).getPerformingSexArea())))
							(action.getPerformingCharacterAreas().contains(mainSexPreference.getPerformingSexArea()) && action.getTargetedCharacterAreas().contains(mainSexPreference.getTargetedSexArea()))
								&& action.getParticipantType()!=SexParticipantType.SELF
								&& action.getActionType() != SexActionType.STOP_ONGOING) {
							highPriorityList.add(action);
							if(action.getActionType() == SexActionType.START_ONGOING
									|| action.getActionType()==SexActionType.START_ADDITIONAL_ONGOING) { // If a penetrative action is in the list, always return that first.
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
				if(debug) {
					System.out.println("Start penetrating");
				}
				for(SexActionInterface action : availableActions) {
					if((action.getActionType()==SexActionType.START_ONGOING || action.getActionType()==SexActionType.START_ADDITIONAL_ONGOING)
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
							if(!Collections.disjoint(performingCharacter.getFetishes(true), action.getFetishes(performingCharacter))) {
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
						&& (!ac.getTargetedCharacterOrifices().isEmpty() && !ac.getTargetedCharacterOrifices().get(0).isInternalOrifice()));
				
				nonPenetrativeOrifices = new ArrayList<>(returnableActions);
				nonPenetrativeOrifices.removeAll(actualOrifices);
				
				if(!actualOrifices.isEmpty()) {
					return (SexAction) actualOrifices.get(Util.random.nextInt(actualOrifices.size()));
					
				} else if(!nonPenetrativeOrifices.isEmpty()) {
					return (SexAction) nonPenetrativeOrifices.get(Util.random.nextInt(nonPenetrativeOrifices.size()));
				}
				
			} else { // If there is no sex penetration available, just use foreplay:
				return performForeplayAction(sexActionPlayer);
			}
		}

		// Ban stop penetration actions:
		for(SexActionInterface action : availableActions) {
			if(action.getActionType() == SexActionType.STOP_ONGOING) {
				if(performingCharacter.getMainSexPreference(targetedCharacter)!=null
						&& (action.getPerformingCharacterAreas().contains(performingCharacter.getMainSexPreference(targetedCharacter).getPerformingSexArea())
								&& action.getTargetedCharacterAreas().contains(performingCharacter.getMainSexPreference(targetedCharacter).getTargetedSexArea()))) {
					if(debug) {
						System.out.println("banned action from preference");
					}
					bannedActions.add(action); // If they don't mind the associated fetishes, stop them from stopping it
				} else {
					int weight = ((NPC)Sex.getCharacterPerformingAction()).calculateSexTypeWeighting(action.getAsSexType(), targetedCharacter, null);
					
					if(weight<0) {
						return (SexAction) action; // If they don't like the associated fetishes, stop it.
					} else {
						bannedActions.add(action); // If they don't mind the associated fetishes, stop them from stopping it
					}
					
//					for(Fetish f : action.getFetishes(performingCharacter)) {
//						if(!performingCharacter.getFetishDesire(f).isNegative()) {
//							bannedActions.add(action); // If they don't mind the associated fetishes, stop them from stopping it
//						} else {
//							return (SexAction) action; // IF they don't like the associated fetishes, stop it.
//						}
//					}
				}
			}
		}
		
		return null;
	}
	
	@Override
	public void assignNPCTarget(GameCharacter targeter) {
		
		List<GameCharacter> availableTargets = new ArrayList<>(Sex.getAllParticipants());
		
		availableTargets.removeIf((character) -> Sex.getSexPositionSlot(character)==SexSlotGeneric.MISC_WATCHING);
		
		GameCharacter preferredTarget = ((NPC) targeter).getPreferredSexTarget();
		
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
			
			if(!availableTargets.isEmpty()) {
				if(preferredTarget!=null && availableTargets.contains(preferredTarget)) {
					Sex.setTargetedPartner(targeter, preferredTarget);
					return;
				}
				Sex.setTargetedPartner(targeter, Util.randomItemFrom(availableTargets));
				return;
			}
		}

		if(preferredTarget!=null && availableTargets.contains(preferredTarget)) {
			Sex.setTargetedPartner(targeter, preferredTarget);
			return;
		}
		
		// If positioning is blocked, then only prioritise characters that actually have actions available that the targeter wants to perform:
		if(!this.isPositionChangingAllowed(targeter)) {
			Map<GameCharacter, SexType> sexPreferences = new HashMap<>();
			
			for(GameCharacter character : availableTargets) {
				if(Sex.getActionsAvailablePartner(targeter, character)!=null && !Sex.getActionsAvailablePartner(targeter, character).isEmpty()) {
					if(Sex.isInForeplay(targeter)) {
						sexPreferences.put(character, Sex.getForeplayPreference(((NPC)targeter), character));
					} else {
						sexPreferences.put(character, Sex.getMainSexPreference(((NPC)targeter), character));
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
					if(Sex.isReadyToOrgasm(targeter) && Sex.getCharactersHavingOngoingActionWith(targeter, SexAreaPenetration.PENIS).contains(character)) {
						weightedTargets.put(character, 100*attractionModifier); // Prioritise characters who the targeter is penetrating when orgasming.
						
					} else if(Sex.isDom(targeter) != Sex.isDom(character)) {
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