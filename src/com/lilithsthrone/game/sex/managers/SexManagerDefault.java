package com.lilithsthrone.game.sex.managers;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.sex.*;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.game.sex.sexActions.*;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisFeet;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisFoot;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericActions;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericPositioning;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfFingerMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfTailMouth;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

import java.util.AbstractMap.SimpleEntry;
import java.util.*;

/**
 * @since 0.1.0
 * @version 0.3.5.5
 * @author Innoxia
 */
public abstract class SexManagerDefault implements SexManagerInterface {

	protected AbstractSexPosition position;
	protected Map<GameCharacter, SexSlot> dominants;
	protected Map<GameCharacter, SexSlot> submissives;
	protected Map<GameCharacter, List<SexAreaInterface>> areasBannedMap;
	protected boolean ableToSkipSexScene;

	public SexManagerDefault(AbstractSexPosition position, Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		this(true, position, dominants, submissives);
	}

	public SexManagerDefault(boolean ableToSkipSexScene, AbstractSexPosition position, Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		this.ableToSkipSexScene = ableToSkipSexScene;
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

	@Override
	public boolean isAbleToSkipSexScene() {
		return ableToSkipSexScene;
	}
	
	@Override
	public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap() {
		return areasBannedMap;
	}

	@Override
	public Map<GameCharacter, List<SexType>> getSexTypesBannedMap() {
		return new HashMap<>();
	}
	
	private static List<SexActionInterface> possibleActions = new ArrayList<>();
	private static List<SexActionInterface> bannedActions = new ArrayList<>();
	
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
	public SexActionInterface getPartnerSexAction(NPC partner, SexActionInterface sexActionPlayer) {
		
		possibleActions.clear();
		bannedActions.clear();
		
//		NPC partner = (NPC) Main.sex.getCharacterPerformingAction();

		List<SexActionInterface> availableActions = Main.sex.getAvailableSexActionsPartner();
		
		
		// --- Priority 1 | If orgasming, bypass everything and use an orgasm option ---
		
		if (Main.sex.isReadyToOrgasm(partner)
				&& (SexFlags.playerPreparedForCharactersOrgasm.contains(partner) || Main.sex.isSpectator(partner))) { // Player does not prepare for spectator orgasms
			List<SexActionInterface> priorityOrgasms = new ArrayList<>();
			
			for(SexActionInterface action : availableActions) {
				for(GameCharacter character : Main.sex.getAllParticipants()) {
					if(action.getAreasCummedIn(partner, character) != null) {
						if((action.getAreasCummedIn(partner, character).contains(SexAreaOrifice.VAGINA) && (partner.hasFetish(Fetish.FETISH_IMPREGNATION))) || Main.sex.getRequestedPulloutWeighting(partner)<0) {
							priorityOrgasms.add(action);
							
						}
//						else if(SexFlags.characterRequestedPullOut && (Main.sex.isConsensual() || Main.sex.getSexControl(partner)==SexControl.FULL)) {
//							priorityOrgasms.add(action);
//						}
					} else {
						if(Main.sex.getRequestedPulloutWeighting(partner)>0) {
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
		
		
		// --- Priority 2 | Resisting and Cocooned ---
		
		// If the partner is resisting, they will not want to remove any clothing, and will instead simply use an available option. (Which will be a SUB_RESIST or neutral pace one.)
//		Value<ImmobilisationType, GameCharacter> value = Main.sex.getImmobilisationType(Main.sex.getCharacterPerformingAction());
//		if(value!=null) {
//			switch(value.getKey()) {
//				case ROPE:
//					return GenericActions.ROPE_BOUND;
//				case CHAINS:
//					return GenericActions.CHAINS_BOUND;
//				case COCOON:
//					return GenericActions.COCOONED;
//				case TAIL_CONSTRICTION:
//					return GenericActions.TAIL_CONSTRICTED;
//				case TENTACLE_RESTRICTION:
//					return GenericActions.TENTACLE_BOUND;
//				case WITCH_SEAL:
//					return GenericActions.WITCH_SEALED;
//			}
//		}
		if(Main.sex.getSexPace(partner)==SexPace.SUB_RESISTING) {
			possibleActions.addAll(Main.sex.getAvailableSexActionsPartner());
			
			if (!possibleActions.isEmpty()) {
				return possibleActions.get(Util.random.nextInt(possibleActions.size()));
			} else {
				return SexActionUtility.PARTNER_NONE;
			}
		}

		
		// --- Priority 3 | Move into one of the partner's preferred positions ---
		
		if(Main.sex.isSexLeader(partner)) { // Only the leader should be able to switch positions:
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
			
			for(GameCharacter character : Main.sex.getAllParticipants()) {
				if(!character.equals(partner)
						&& Main.sex.getSexPositionSlot(character)!=SexSlotGeneric.MISC_WATCHING
						&& partner.isHappyToBeInSlot(Main.sex.getPosition(), Main.sex.getSexPositionSlot(partner), Main.sex.getSexPositionSlot(character), character)
//						&& highPriorityActions.isEmpty()
						) {
//					System.out.println("Happy in slot");
					suitablePosition = true;
					break;
				} else {
//					System.out.println("Not happy in slot");
				}
			}
			
			// Prefer not to stay in standing if prefer to be fucking:
			if(Main.sex.getPosition()==SexPosition.STANDING) {
				SexType currentSexPreference = partner.getCurrentSexPreference(Main.sex.getTargetedPartner(partner));
				if(currentSexPreference != null) {
					SexAreaInterface performingArea = currentSexPreference.getPerformingSexArea();
					SexAreaInterface targetedArea = currentSexPreference.getTargetedSexArea();
					if(performingArea.isPenetration() && ((SexAreaPenetration)performingArea).isTakesVirginity()) {
						if(targetedArea.isOrifice()
								&& (targetedArea==SexAreaOrifice.ANUS || targetedArea==SexAreaOrifice.VAGINA || targetedArea==SexAreaOrifice.URETHRA_PENIS || targetedArea==SexAreaOrifice.URETHRA_VAGINA)) {
							suitablePosition = false;
						}
					}
					if(targetedArea.isPenetration() && ((SexAreaPenetration)targetedArea).isTakesVirginity()) {
						if(performingArea.isOrifice()
								&& (performingArea==SexAreaOrifice.ANUS || performingArea==SexAreaOrifice.VAGINA || performingArea==SexAreaOrifice.URETHRA_PENIS || performingArea==SexAreaOrifice.URETHRA_VAGINA)) {
							suitablePosition = false;
						}
					}
				}
			}
			
			if(!suitablePosition && Main.sex.getLastUsedPlayerAction().getActionType()!=SexActionType.POSITIONING) {
				// Choose a random position:
				if (!highPriorityActions.isEmpty()) {
					if(partner.isFeral()) {
						List<SexActionInterface> actions = Util.newArrayListOfValues(
								GenericPositioning.POSITION_ALL_FOURS_FUCKING,
								GenericPositioning.POSITION_ALL_FOURS_GETTING_FUCKED,
								GenericPositioning.POSITION_ORAL_RECEIVING,
								GenericPositioning.POSITION_ORAL_PERFORMING);
						if(!Collections.disjoint(highPriorityActions, actions)) {
							highPriorityActions.retainAll(actions);
						}
					}
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
		
		GameCharacter targetedCharacter = Main.sex.getTargetedPartner(partner);
		
		// Skip over remove clothing if action is of HIGH or MAX priority
		if(!availableActions.isEmpty()
				&& availableActions.get(0).getPriority()!=SexActionPriority.HIGH
				&& availableActions.get(0).getPriority()!=SexActionPriority.UNIQUE_MAX
				&& !Main.sex.isCharacterImmobilised(partner) // DO not allow immobilised characters to manage clothing of any kind
				) {
				
			List<CoverableArea> targetAreasToBeExposed = new ArrayList<>();
			List<CoverableArea> partnerAreasToBeExposed = new ArrayList<>();

			partnerAreasToBeExposed.addAll(Main.sex.getInitialSexManager().getAdditionalAreasToExposeDuringSex(partner, partner));
			targetAreasToBeExposed.addAll(Main.sex.getInitialSexManager().getAdditionalAreasToExposeDuringSex(partner, targetedCharacter));
			
			if(Main.sex.isInForeplay(partner)) {
				if(Main.sex.getForeplayPreference(partner, targetedCharacter)!=null) {
					SexParticipantType participantType = Main.sex.getForeplayPreference(partner, targetedCharacter).getAsParticipant();

					partnerAreasToBeExposed.add(Main.sex.getForeplayPreference(partner, targetedCharacter).getPerformingSexArea().getRelatedCoverableArea(partner));
					if(participantType==SexParticipantType.SELF) {
						partnerAreasToBeExposed.add(Main.sex.getForeplayPreference(partner, targetedCharacter).getTargetedSexArea().getRelatedCoverableArea(partner));
					} else {
						targetAreasToBeExposed.add(Main.sex.getForeplayPreference(partner, targetedCharacter).getTargetedSexArea().getRelatedCoverableArea(targetedCharacter));
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
					
					partnerAreasToBeExposed.add(partner.getMainSexPreference(targetedCharacter).getPerformingSexArea().getRelatedCoverableArea(partner));
					if(participantType==SexParticipantType.SELF) {
						partnerAreasToBeExposed.add(partner.getMainSexPreference(targetedCharacter).getTargetedSexArea().getRelatedCoverableArea(partner));
					} else {
						targetAreasToBeExposed.add(partner.getMainSexPreference(targetedCharacter).getTargetedSexArea().getRelatedCoverableArea(targetedCharacter));
					}
					
				} else {
					partnerAreasToBeExposed.add(CoverableArea.PENIS);
					partnerAreasToBeExposed.add(CoverableArea.VAGINA);
					
					targetAreasToBeExposed.add(CoverableArea.PENIS);
					targetAreasToBeExposed.add(CoverableArea.VAGINA);
				}
			}
			
			if(Main.sex.getActionsAvailablePartner(partner, targetedCharacter)!=null) {
				if((Main.sex.getActionsAvailablePartner(partner, targetedCharacter).contains(PenisFoot.FOOT_JOB_SINGLE_GIVING_START)
								&& partner.calculateSexTypeWeighting(PenisFoot.FOOT_JOB_SINGLE_GIVING_START.getAsSexType(), targetedCharacter, null)>0)
						|| (Main.sex.getActionsAvailablePartner(partner, targetedCharacter).contains(PenisFeet.FOOT_JOB_DOUBLE_GIVING_START)
								&& partner.calculateSexTypeWeighting(PenisFeet.FOOT_JOB_DOUBLE_GIVING_START.getAsSexType(), targetedCharacter, null)>0)) {
					partnerAreasToBeExposed.add(CoverableArea.FEET);
				}
	
				if((Main.sex.getActionsAvailablePartner(partner, targetedCharacter).contains(PenisFoot.FOOT_JOB_SINGLE_RECEIVING_START)
							&& partner.calculateSexTypeWeighting(PenisFoot.FOOT_JOB_SINGLE_RECEIVING_START.getAsSexType(), targetedCharacter, null)>0)
					|| (Main.sex.getActionsAvailablePartner(partner, targetedCharacter).contains(PenisFeet.FOOT_JOB_DOUBLE_RECEIVING_START)
							&& partner.calculateSexTypeWeighting(PenisFeet.FOOT_JOB_DOUBLE_RECEIVING_START.getAsSexType(), targetedCharacter, null)>0)) {
					targetAreasToBeExposed.add(CoverableArea.FEET);
				}
			}
			partnerAreasToBeExposed.removeIf((area) -> (partner.isCoverableAreaExposed(area) || !partner.isAbleToAccessCoverableArea(area, true))
					|| (area==CoverableArea.PENIS && !partner.hasPenis())
					|| (area==CoverableArea.VAGINA && !partner.hasVagina()));

			partnerAreasToBeExposed.removeIf((area) -> (area==CoverableArea.FEET && (partner.getFetishDesire(Fetish.FETISH_SADIST).isPositive() || partner.getClothingInSlot(InventorySlot.FOOT)==null)));
			targetAreasToBeExposed.removeIf((area) -> (area==CoverableArea.FEET && (partner.getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() || targetedCharacter.getClothingInSlot(InventorySlot.FOOT)==null)));
			
			targetAreasToBeExposed.removeIf((area) -> (targetedCharacter.isCoverableAreaExposed(area) || !targetedCharacter.isAbleToAccessCoverableArea(area, true))
					|| (area==CoverableArea.PENIS && !targetedCharacter.hasPenis())
					|| (area==CoverableArea.VAGINA && !targetedCharacter.hasVagina()));
			
			if(!partnerAreasToBeExposed.isEmpty() && Main.sex.isCanRemoveSelfClothing(partner)) {
				Collections.shuffle(partnerAreasToBeExposed);
				CoverableArea exposeArea = partnerAreasToBeExposed.get(0);
				if(exposeArea==CoverableArea.MOUND) {
					exposeArea = CoverableArea.VAGINA;
				}
				SexType preference = partner.getCurrentSexPreference(targetedCharacter);
				// Only displace clothing if its the desired area, or if the clothing to be displaced is not a sex toy:
				SimpleEntry<AbstractClothing, DisplacementType> clothingToRemove = partner.getNextClothingToRemoveForCoverableAreaAccess(exposeArea);
				if((preference!=null && preference.getPerformingSexArea().getRelatedCoverableArea(partner)==exposeArea) //TODO
						|| (clothingToRemove!=null && !clothingToRemove.getKey().isSexToy(clothingToRemove.getKey().getSlotEquippedTo()))) {
					return Main.sex.manageClothingToAccessCoverableArea(partner, partner, exposeArea);
				}
			}
			
			if(!Main.sex.isSpectator(partner) && !targetAreasToBeExposed.isEmpty() && Main.sex.isCanRemoveOthersClothing(partner, null)) {
				Collections.shuffle(targetAreasToBeExposed);
				List<CoverableArea> areas = new ArrayList<>(targetAreasToBeExposed);
				for(CoverableArea area : areas) {
					SimpleEntry<AbstractClothing, DisplacementType> clothingRemoval = targetedCharacter.getNextClothingToRemoveForCoverableAreaAccess(area);
					if(clothingRemoval==null || !Main.sex.isCanRemoveOthersClothing(partner, clothingRemoval.getKey())) {
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
					if((preference!=null && preference.getTargetedSexArea().getRelatedCoverableArea(targetedCharacter)==exposeArea)
							|| (clothingToRemove!=null && !clothingToRemove.getKey().isSexToy(clothingToRemove.getKey().getSlotEquippedTo()))) {
						return Main.sex.manageClothingToAccessCoverableArea(partner, targetedCharacter, exposeArea);
					}
				}
			}
		}
		
		
		// --- Priority 5 | Using special items ---

		for(GameCharacter character : Main.sex.getAllParticipants()) {
//			if(!character.isPlayer()) {
			Value<AbstractItem, String> sexItemValue = partner.getSexItemToUse(character);
			if(sexItemValue!=null) {
				Main.sex.setItemUseInformation(partner, character, sexItemValue.getKey());
				return SexActionUtility.PARTNER_USE_ITEM;
			}

			if(!character.equals(partner)) {
				Value<AbstractClothing, String> sexClothingValue = partner.getSexClothingToSelfEquip(character, false);
				if(sexClothingValue!=null) {
					Main.sex.setClothingSelfEquipInformation(partner, character, sexClothingValue.getKey());
					if(SexActionUtility.PARTNER_SELF_EQUIP_CLOTHING.isBaseRequirementsMet()) {
						return SexActionUtility.PARTNER_SELF_EQUIP_CLOTHING;
					}
				}

				sexClothingValue = partner.getSexClothingToEquip(character, false);
				if(sexClothingValue!=null) {
					Main.sex.setClothingEquipInformation(partner, character, sexClothingValue.getKey());
					if(SexActionUtility.PARTNER_EQUIP_CLOTHING.isBaseRequirementsMet()) {
						return SexActionUtility.PARTNER_EQUIP_CLOTHING;
					}
				}
			}
		}
		
		
		// --- Priority 6 | Ban actions that make no sense for the partner to perform ---
		
		// Ban all penetrations if the partner is a virgin in the associated orifice:
		for(SexActionInterface action : availableActions) {
			if(action.getActionType()==SexActionType.START_ONGOING && action.isTakesVirginity(true)) {
				for(SexAreaOrifice sArea : action.getPerformingCharacterOrifices()) {
					switch(sArea) {
						case ANUS:
							if(Main.sex.getCharacterPerformingAction().isAssVirgin() && (Main.sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue() || action.getParticipantType()==SexParticipantType.SELF)) {
								bannedActions.add(action);
							}
							break;
						case MOUTH:
							// NPCs don't care about losing oral virginity.
							break;
						case NIPPLE:
							if(Main.sex.getCharacterPerformingAction().isNippleVirgin() && (Main.sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue() || action.getParticipantType()==SexParticipantType.SELF)) {
								bannedActions.add(action);
							}
							break;
						case NIPPLE_CROTCH:
							if(Main.sex.getCharacterPerformingAction().isNippleCrotchVirgin() && (Main.sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue() || action.getParticipantType()==SexParticipantType.SELF)) {
								bannedActions.add(action);
							}
							break;
						case URETHRA_PENIS:
							if(Main.sex.getCharacterPerformingAction().isUrethraVirgin() && (Main.sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue() || action.getParticipantType()==SexParticipantType.SELF)) {
								bannedActions.add(action);
							}
							break;
						case URETHRA_VAGINA:
							if(Main.sex.getCharacterPerformingAction().isVaginaUrethraVirgin() && (Main.sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue() || action.getParticipantType()==SexParticipantType.SELF)) {
								bannedActions.add(action);
							}
							break;
						case VAGINA:
							if((Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_PURE_VIRGIN) && (Main.sex.getCharacterPerformingAction().isVaginaVirgin() || Main.sex.getCharacterPerformingAction().hasHymen()))
									|| (Main.sex.getCharacterPerformingAction().isVaginaVirgin() && (Main.sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue() || action.getParticipantType()==SexParticipantType.SELF))) {
								bannedActions.add(action);
							}
							break;
						case SPINNERET:
							if(Main.sex.getCharacterPerformingAction().isSpinneretVirgin() && (Main.sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue() || action.getParticipantType()==SexParticipantType.SELF)) {
								bannedActions.add(action);
							}
							break;
						// No virginity to lose:
						case ARMPITS:
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
								if(Main.sex.getCharacterPerformingAction().isAssVirgin()) {// && Main.sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue()) {
									bannedActions.add(action);
								}
								break;
							case MOUTH:
								// NPCs don't care about losing oral virginity.
								break;
							case NIPPLE:
								if(Main.sex.getCharacterPerformingAction().isNippleVirgin()) {// && Main.sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue()) {
									bannedActions.add(action);
								}
								break;
							case NIPPLE_CROTCH:
								if(Main.sex.getCharacterPerformingAction().isNippleCrotchVirgin()) {// && Main.sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue()) {
									bannedActions.add(action);
								}
								break;
							case URETHRA_PENIS:
								if(Main.sex.getCharacterPerformingAction().isUrethraVirgin()) {// && Main.sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue()) {
									bannedActions.add(action);
								}
								break;
							case URETHRA_VAGINA:
								if(Main.sex.getCharacterPerformingAction().isVaginaUrethraVirgin()) {// && Main.sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue()) {
									bannedActions.add(action);
								}
								break;
							case VAGINA:
								if(Main.sex.getCharacterPerformingAction().hasStatusEffect(StatusEffect.FETISH_PURE_VIRGIN)
										|| Main.sex.getCharacterPerformingAction().hasStatusEffect(StatusEffect.FETISH_PURE_VIRGIN_NO_HYMEN)
										|| Main.sex.getCharacterPerformingAction().hasStatusEffect(StatusEffect.FETISH_PURE_VIRGIN_ONLY_HYMEN)
										|| Main.sex.getCharacterPerformingAction().hasStatusEffect(StatusEffect.FETISH_LUSTY_MAIDEN)
										|| Main.sex.getCharacterPerformingAction().hasStatusEffect(StatusEffect.FETISH_LUSTY_MAIDEN_NO_HYMEN)
										|| Main.sex.getCharacterPerformingAction().hasStatusEffect(StatusEffect.FETISH_LUSTY_MAIDEN_ONLY_HYMEN)
										|| (Main.sex.getCharacterPerformingAction().isVaginaVirgin())) {// && Main.sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue())) {
									bannedActions.add(action);
								}
								break;
							case SPINNERET:
								if(Main.sex.getCharacterPerformingAction().isSpinneretVirgin()) {// && Main.sex.getCharacterPerformingAction().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue()) {
									bannedActions.add(action);
								}
								break;
							// No virginity to lose:
							case ARMPITS:
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
		
		
		
		// --- Priority 7 | Perform actions based on foreplay or sex ---
		
		// Perform foreplay action if arousal is < 25 and haven't orgasmed yet:
		SexAction actionToPerform = null;
		if(Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
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

		// --- Priority 8 using other options at random ---
		possibleActions.addAll(availableActions);

		possibleActions.removeAll(bannedActions);
		
		if (!possibleActions.isEmpty()) {
			return possibleActions.get(Util.random.nextInt(possibleActions.size()));
		}
		
		// Priority 9 (last resort):
		return SexActionUtility.PARTNER_NONE;
	}
	
	private static void applyGeneralActionBans(GameCharacter performingCharacter, GameCharacter targetedCharacter, List<SexActionInterface> availableActions) {
		// If the performing character is fucking someone, they shouldn't start fingering their own ass/pussy:
		if(Main.sex.getAllOngoingSexAreas(performingCharacter, SexAreaPenetration.PENIS).stream().anyMatch((area) -> area.isOrifice() && ((SexAreaOrifice)area).isInternalOrifice())) {
			for(SexActionInterface sexAction : availableActions) {
				if(sexAction.getParticipantType()==SexParticipantType.SELF
						&& sexAction.getPerformingCharacterAreas().contains(SexAreaPenetration.FINGER)
						&& (sexAction.getTargetedCharacterAreas().contains(SexAreaOrifice.ANUS) || sexAction.getTargetedCharacterAreas().contains(SexAreaOrifice.VAGINA))) {
					bannedActions.add(sexAction);
				}
			}
		}
		
		// Ban some annoying/nonsensical actions:
		bannedActions.add(SelfFingerMouth.SELF_FINGER_MOUTH_PENETRATION);
		if(!performingCharacter.hasFetish(Fetish.FETISH_ORAL_RECEIVING) && !performingCharacter.hasFetish(Fetish.FETISH_ORAL_GIVING)) {
			bannedActions.add(SelfTailMouth.PARTNER_SELF_TAIL_MOUTH_PENETRATION);
		}
		
		if(!performingCharacter.hasBreasts()) {
			bannedActions.add(TongueNipple.BREASTFEED);
		}
		if(!targetedCharacter.hasBreasts()) {
			bannedActions.add(TongueNipple.SUCKLE_START);
		}
	}
	
	/**
	 * Finger and tongue actions are considered foreplay.
	 */
	private SexAction performForeplayAction(SexActionInterface sexActionPlayer) {
		boolean debug = false;

		NPC performingCharacter = (NPC)Main.sex.getCharacterPerformingAction();
		GameCharacter targetedCharacter = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
		
		List<SexActionInterface> availableActions = new ArrayList<>(Main.sex.getAvailableSexActionsPartner());
		availableActions.removeIf(si -> !si.isAddedToAvailableSexActions() && !si.isAbleToAccessParts(performingCharacter));
		
		applyGeneralActionBans(performingCharacter, targetedCharacter, availableActions);
		
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
		SexType foreplayPreference = Main.sex.getForeplayPreference(performingCharacter, targetedCharacter);
		if(!Main.sex.isInForeplay(performingCharacter)) { // Use main sex preference if in main sex (as this method can be called as a fallthrough from performSexAction())
			foreplayPreference = Main.sex.getMainSexPreference(performingCharacter, targetedCharacter);
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
			if((action.getActionType() == SexActionType.START_ONGOING || action.getActionType()==SexActionType.START_ADDITIONAL_ONGOING || Main.sex.isCharacterEngagedInOngoingAction(performingCharacter, targetedCharacter))
					&& action.getActionType() != SexActionType.STOP_ONGOING
					&& action.getParticipantType()!=SexParticipantType.SELF) {
				if(!action.isTakesVirginity(false)) { // Do not want to use full penetrative sex in foreplay
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
		
		// --- Banning actions ---
		
		for(SexActionInterface action : availableActions) {
			if(action.getActionType()==SexActionType.STOP_ONGOING) { // Ban stop penetration actions
				bannedActions.add(action);
			}
			if(action.isTakesPerformerVirginity(true, performingCharacter, targetedCharacter) && action.getParticipantType()==SexParticipantType.SELF) { // Ban taking own virginity
				if(performingCharacter.isAssVirgin())
				bannedActions.add(action);
			}
		}
		
		return null;
	}

//	private boolean removedAllPenetrationAfterForeplay = false;
	
	private SexAction performSexAction(SexActionInterface sexActionPlayer) {
		
		NPC performingCharacter = (NPC)Main.sex.getCharacterPerformingAction();
		GameCharacter targetedCharacter = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());

		boolean debug = false;
		boolean debugFullActionList = false;

		if(debug) {
			System.out.println();
			System.out.println("Character: "+performingCharacter.getName(true));
			System.out.println("Target: "+targetedCharacter.getName(true));
		}
		
		List<SexActionInterface> availableActions = new ArrayList<>(Main.sex.getAvailableSexActionsPartner()); //TODO need to check this
//		List<SexActionInterface> availableActions = new ArrayList<>(Main.sex.getActionsAvailablePartner(performingCharacter, targetedCharacter));
		availableActions.removeIf(si -> !si.isAddedToAvailableSexActions() && !si.isAbleToAccessParts(performingCharacter));
		
		if(sexActionPlayer.getActionType()==SexActionType.STOP_ONGOING
				|| sexActionPlayer.equals(GenericActions.PLAYER_FORBID_PARTNER_SELF)
				|| sexActionPlayer.equals(GenericActions.PLAYER_STOP_PARTNER_SELF)
				|| sexActionPlayer.equals(GenericActions.PLAYER_STOP_ALL_PENETRATIONS)
				|| sexActionPlayer.equals(GenericActions.PLAYER_STOP_ALL_PENETRATIONS_SELF)) {
			availableActions.removeIf(sexAction -> sexAction.getActionType()==SexActionType.START_ONGOING || sexAction.getActionType()==SexActionType.START_ADDITIONAL_ONGOING);
		}
		
		applyGeneralActionBans(performingCharacter, targetedCharacter, availableActions);
		
		availableActions.removeAll(bannedActions);
		List<SexActionInterface> returnableActions = new ArrayList<>();
		
		boolean isSexPenetrationPossible = false;
		if(Main.sex.getActionsAvailablePartner(performingCharacter, targetedCharacter)!=null && !Main.sex.getActionsAvailablePartner(performingCharacter, targetedCharacter).isEmpty()) {
			actionLoop:
			for(SexActionInterface action : Main.sex.getActionsAvailablePartner(performingCharacter, targetedCharacter)) {
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
		}
		if(isSexPenetrationPossible) {
			isSexPenetrationPossible =
					((Main.sex.getMainSexPreference(performingCharacter, targetedCharacter)==null || Main.sex.getMainSexPreference(performingCharacter, targetedCharacter).getPerformingSexArea()==SexAreaPenetration.PENIS)
							&& performingCharacter.hasPenis() && performingCharacter.isAbleToAccessCoverableArea(CoverableArea.PENIS, true))
					|| ((Main.sex.getMainSexPreference(performingCharacter, targetedCharacter)==null || Main.sex.getMainSexPreference(performingCharacter, targetedCharacter).getPerformingSexArea()==SexAreaPenetration.TAIL)
							&& performingCharacter.isTailSuitableForPenetration())
					|| ((Main.sex.getMainSexPreference(performingCharacter, targetedCharacter)==null || Main.sex.getMainSexPreference(performingCharacter, targetedCharacter).getTargetedSexArea()==SexAreaPenetration.PENIS)
							&& targetedCharacter.hasPenis() && targetedCharacter.isAbleToAccessCoverableArea(CoverableArea.PENIS, true))
					|| ((Main.sex.getMainSexPreference(performingCharacter, targetedCharacter)==null || Main.sex.getMainSexPreference(performingCharacter, targetedCharacter).getTargetedSexArea()==SexAreaPenetration.TAIL)
							&& targetedCharacter.isTailSuitableForPenetration());
		}
		
		boolean isOngoingSexPenetration = false;
		// Is any sexual penetration happening, or is the NPC's preferred penetration happening:
		outerloop:
		for(SexAreaPenetration pen : SexAreaPenetration.values()) {
			if((pen.isTakesVirginity() || (Main.sex.getMainSexPreference(performingCharacter, targetedCharacter)!=null && Main.sex.getMainSexPreference(performingCharacter, targetedCharacter).getPerformingSexArea()==pen))
					&& Main.sex.getOngoingActionsMap(performingCharacter).get(pen).containsKey(targetedCharacter)) {
				for(SexAreaInterface sai : Main.sex.getOngoingActionsMap(performingCharacter).get(pen).get(targetedCharacter)) {
					if((sai.isOrifice() && ((SexAreaOrifice) sai).isInternalOrifice())
							|| (Main.sex.getMainSexPreference(performingCharacter, targetedCharacter)!=null && Main.sex.getMainSexPreference(performingCharacter, targetedCharacter).getTargetedSexArea()==sai
									&& (sai!=SexAreaOrifice.ASS || !targetedCharacter.isAbleToAccessCoverableArea(CoverableArea.ANUS, true)))) {
						isOngoingSexPenetration = true;
						if(debug) {
							System.out.println(performingCharacter.getName(true)+" isOngoingSexPenetration found 1");
						}
						break outerloop;
					}
				}
			}
			if((pen.isTakesVirginity() || (Main.sex.getMainSexPreference(performingCharacter, targetedCharacter)!=null && Main.sex.getMainSexPreference(performingCharacter, targetedCharacter).getTargetedSexArea()==pen))
					&& Main.sex.getOngoingActionsMap(targetedCharacter).get(pen).containsKey(performingCharacter)) {
				for(SexAreaInterface sai : Main.sex.getOngoingActionsMap(targetedCharacter).get(pen).get(performingCharacter)) {
					if((sai.isOrifice() && ((SexAreaOrifice) sai).isInternalOrifice())
							|| (Main.sex.getMainSexPreference(performingCharacter, targetedCharacter)!=null && Main.sex.getMainSexPreference(performingCharacter, targetedCharacter).getPerformingSexArea()==sai
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

				SexType mainSexPreference = Main.sex.getMainSexPreference(performingCharacter, targetedCharacter);
				// --- If the NPC has a preference, they are more likely to choose actions related to that: ---
					List<SexActionInterface> penetrativeActionList = new ArrayList<>();
					if(mainSexPreference!=null) {
						List<SexActionInterface> highPriorityList = new ArrayList<>();
						for(SexActionInterface action : availableActions) {
							if((action.getPerformingCharacterAreas().contains(mainSexPreference.getPerformingSexArea()) && action.getTargetedCharacterAreas().contains(mainSexPreference.getTargetedSexArea()))
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
				// -------

				// --- Stop foreplay actions: ---
					for(SexActionInterface action : availableActions) {
						if(action.getActionType() == SexActionType.STOP_ONGOING) {
							// Don't stop kissing or fetishised oral actions:
							if(Main.sex.getMainSexPreference(performingCharacter, targetedCharacter)==null) { //TODO More testing
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
						Main.sex.removeCharacterBannedFromPositioning(performingCharacter);
					}
					if(!returnableActions.isEmpty()) {
						return (SexAction) returnableActions.get(Util.random.nextInt(returnableActions.size()));
					}
				// -------
				
				
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
					int weight = ((NPC)Main.sex.getCharacterPerformingAction()).calculateSexTypeWeighting(action.getAsSexType(), targetedCharacter, null);
					
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
			if(action.isTakesPerformerVirginity(true, performingCharacter, targetedCharacter) && action.getParticipantType()==SexParticipantType.SELF) { // Ban taking own virginity
				bannedActions.add(action);
			}
		}
		
		return null;
	}
	
	@Override
	public void assignNPCTarget(GameCharacter targeter) {
		
		List<GameCharacter> availableTargets = new ArrayList<>(Main.sex.getAllParticipants());
		
		availableTargets.removeIf((character) -> Main.sex.getSexPositionSlot(character)==SexSlotGeneric.MISC_WATCHING);
		
		GameCharacter preferredTarget = Main.sex.getInitialSexManager().getPreferredSexTarget((NPC) targeter);
		
		// Always target those who are about to cum:
		if(Main.sex.isReadyToOrgasm(targeter) && SexFlags.playerPreparedForCharactersOrgasm.contains(targeter)) {
			availableTargets.clear();
			List<GameCharacter> floorTargets = new ArrayList<>();
			
			for(GameCharacter character : Main.sex.getAllParticipants()) {
				if(!character.equals(targeter) && Main.sex.isAbleToTarget(character)) {
					List<OrgasmCumTarget> cumTargets = Main.sex.getSexManager().getPosition().getSexInteractions(Main.sex.getSexPositionSlot(targeter), Main.sex.getSexPositionSlot(character)).getAvailableCumTargets();
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
					Main.sex.setTargetedPartner(targeter, preferredTarget);
					return;
				}
				Main.sex.setTargetedPartner(targeter, Util.randomItemFrom(availableTargets));
				return;
			}
		}

		if(preferredTarget!=null && availableTargets.contains(preferredTarget)) {
			Main.sex.setTargetedPartner(targeter, preferredTarget);
			return;
		}
		
		// If positioning is blocked, then only prioritise characters that actually have actions available that the targeter wants to perform:
		if(!this.isPositionChangingAllowed(targeter)) {
			Map<GameCharacter, SexType> sexPreferences = new HashMap<>();
			
			for(GameCharacter character : availableTargets) {
				if(Main.sex.getActionsAvailablePartner(targeter, character)!=null && !Main.sex.getActionsAvailablePartner(targeter, character).isEmpty()) {
					if(Main.sex.isInForeplay(targeter)) {
						sexPreferences.put(character, Main.sex.getForeplayPreference(((NPC)targeter), character));
					} else {
						sexPreferences.put(character, Main.sex.getMainSexPreference(((NPC)targeter), character));
					}
				}
			}
			
			List<GameCharacter> priorityTargets = new ArrayList<>();
			
			characterLoop:
			for(GameCharacter character : availableTargets) {
				SexType preference = sexPreferences.get(character);
				if(preference!=null) {
					for(SexActionInterface action : Main.sex.isReadyToOrgasm(targeter)?Main.sex.getOrgasmActionsPartner(targeter, character):Main.sex.getActionsAvailablePartner(targeter, character)) {
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
			if(!character.equals(targeter) && Main.sex.isAbleToTarget(character)) {
				
				if(Main.sex.getActionsAvailablePartner(targeter, character).isEmpty()) { // If no interactions, skip character.
					continue;
				}
				
				Set<SexActionInterface> availableActions = Main.sex.isReadyToOrgasm(targeter)?Main.sex.getOrgasmActionsPartner(targeter, character):Main.sex.getActionsAvailablePartner(targeter, character);
				
				int attractionModifier = 100;
				
				 // If targeter is not attracted to the character, or if the character is in the same dom/sub type, and is not attracted to the targeter, then they are a hundred times less likely to target them:
				if(!targeter.isAttractedTo(character) || (Main.sex.isDom(targeter)==Main.sex.isDom(character) && !character.isAttractedTo(targeter))) {
					attractionModifier/=100;
				}
				
				if(!availableActions.isEmpty()) {
					if(Main.sex.isReadyToOrgasm(targeter) && Main.sex.getCharactersHavingOngoingActionWith(targeter, SexAreaPenetration.PENIS).contains(character)) {
						weightedTargets.put(character, 100*attractionModifier); // Prioritise characters who the targeter is penetrating when orgasming.
						
					} else if(Main.sex.isDom(targeter) != Main.sex.isDom(character)) {
						weightedTargets.put(character, 5*attractionModifier*availableActions.size()); // Prioritise targeting opposite character by a factor of five (i.e subs target doms and vice versa).
						
					} else {
						if(Main.sex.getSexPace(targeter)!=SexPace.SUB_RESISTING) { // If resisting, don't target a sub (as resisting actions are targeted towards doms).
							weightedTargets.put(character, 1*attractionModifier*availableActions.size());
						}
					}
				}
			}
		}
		
		if(weightedTargets.isEmpty()) {
			Main.sex.setTargetedPartner(targeter, Util.randomItemFrom(availableTargets));
		} else {
			Main.sex.setTargetedPartner(targeter, Util.getRandomObjectFromWeightedMap(weightedTargets));
		}
	}
	
}