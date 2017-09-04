package com.base.game.sex.managers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.base.game.character.attributes.ArousalLevel;
import com.base.game.character.effects.Fetish;
import com.base.game.character.effects.StatusEffect;
import com.base.game.inventory.clothing.CoverableArea;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.Sex;
import com.base.game.sex.SexFlags;
import com.base.game.sex.SexPace;
import com.base.game.sex.SexPosition;
import com.base.game.sex.sexActions.SexAction;
import com.base.game.sex.sexActions.SexActionInterface;
import com.base.game.sex.sexActions.SexActionPriority;
import com.base.game.sex.sexActions.SexActionType;
import com.base.game.sex.sexActions.SexActionUtility;
import com.base.game.sex.sexActions.baseActionsPartner.PartnerFingerVagina;
import com.base.game.sex.sexActions.baseActionsPartner.PartnerTongueMouth;
import com.base.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfFingerMouth;
import com.base.main.Main;
import com.base.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.79
 * @author Innoxia
 */
public abstract class SexManagerDefault implements SexManagerInterface {
	
	private static List<SexActionInterface> actionsAvailablePlayer, actionsAvailablePartner, orgasmActionsPlayer, orgasmActionsPartner, mutualOrgasmActions;
	
	public SexManagerDefault(Class<?>... coreContainers) {
		actionsAvailablePlayer = new ArrayList<>();
		actionsAvailablePartner = new ArrayList<>();
		orgasmActionsPlayer = new ArrayList<>();
		orgasmActionsPartner = new ArrayList<>();
		mutualOrgasmActions = new ArrayList<>();

		try {
			if (coreContainers.length != 0) {
				for(Class<?> container : coreContainers) {
				
					Field[] fields = container.getFields();
					
					for(Field f : fields){
						
						if (SexAction.class.isAssignableFrom(f.getType())) {
							if (((SexAction) f.get(null)).getActionType().isOrgasmOption()) {
								if (((SexAction) f.get(null)).getActionType() == SexActionType.MUTUAL_ORGASM) {
									mutualOrgasmActions.add(((SexAction) f.get(null)));
									
								} else  if (((SexAction) f.get(null)).getActionType().isPlayerAction()) {
									orgasmActionsPlayer.add(((SexAction) f.get(null)));
									
								} else {
									orgasmActionsPartner.add(((SexAction) f.get(null)));
								}
								
							} else {
								if (((SexAction) f.get(null)).getActionType().isPlayerAction()) {
									actionsAvailablePlayer.add(((SexAction) f.get(null)));
									
								} else {
									actionsAvailablePartner.add(((SexAction) f.get(null)));
								}
							}
						}
					}
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	// A+ quality code example: /s
	protected void overrideAction(SexActionInterface oldAction, SexActionInterface newAction){
		for(int i=0 ; i< actionsAvailablePlayer.size(); i++){
			if(actionsAvailablePlayer.get(i) == oldAction) {
				actionsAvailablePlayer.set(i, newAction);
				return;
			}
		}
		for(int i=0 ; i< actionsAvailablePartner.size(); i++){
			if(actionsAvailablePartner.get(i) == oldAction) {
				actionsAvailablePartner.set(i, newAction);
				return;
			}
		}
		for(int i=0 ; i< orgasmActionsPlayer.size(); i++){
			if(orgasmActionsPlayer.get(i) == oldAction) {
				orgasmActionsPlayer.set(i, newAction);
				return;
			}
		}
		for(int i=0 ; i< orgasmActionsPartner.size(); i++){
			if(orgasmActionsPartner.get(i) == oldAction) {
				orgasmActionsPartner.set(i, newAction);
				return;
			}
		}
		for(int i=0 ; i< mutualOrgasmActions.size(); i++){
			if(mutualOrgasmActions.get(i) == oldAction) {
				mutualOrgasmActions.set(i, newAction);
				return;
			}
		}
	}
	
	protected void removeAction(SexActionInterface action){
		actionsAvailablePlayer.remove(action);
		actionsAvailablePartner.remove(action);
		orgasmActionsPlayer.remove(action);
		orgasmActionsPartner.remove(action);
		mutualOrgasmActions.remove(action);
	}
	
	@Override
	public List<SexActionInterface> getActionsAvailablePlayer(){
			return actionsAvailablePlayer;
	}
	@Override
	public List<SexActionInterface> getActionsAvailablePartner(){
		return actionsAvailablePartner;
	}
	@Override
	public List<SexActionInterface> getOrgasmActionsPlayer(){
		return orgasmActionsPlayer;
	}
	@Override
	public List<SexActionInterface> getOrgasmActionsPartner(){
		return orgasmActionsPartner;
	}
	@Override
	public List<SexActionInterface> getMutualOrgasmActions(){
		return mutualOrgasmActions;
	}

	private static List<SexActionInterface> possibleActions = new ArrayList<>(), bannedActions = new ArrayList<>();
	
	@Override
	public SexActionInterface getPartnerSexAction(SexActionInterface sexActionPlayer) {
		
		possibleActions.clear();
		bannedActions.clear();

		List<SexActionInterface> availableActions = Sex.getAvailableSexActionsPartner();
		
		// --- Priority 1 | If orgasming, bypass everything and use an orgasm option ---
		
		if (ArousalLevel.getArousalLevelFromValue(Sex.getPartner().getArousal()) == ArousalLevel.FIVE_ORGASM_IMMINENT) {
			List<SexActionInterface> priorityOrgasms = new ArrayList<>();
			
			for(SexActionInterface action : availableActions) {
				if(action.getPlayerAreasCummedIn() != null) {
					if((action.getPlayerAreasCummedIn().contains(OrificeType.VAGINA_PLAYER)
							&& (Sex.getPartner().hasFetish(Fetish.FETISH_IMPREGNATION) || Sex.getPartner().hasFetish(Fetish.FETISH_SEEDER)))
						|| (action.getPlayerAreasCummedIn().contains(OrificeType.VAGINA_PARTNER)
								&& (Sex.getPartner().hasFetish(Fetish.FETISH_PREGNANCY) || Sex.getPartner().hasFetish(Fetish.FETISH_BROODMOTHER)))
						|| SexFlags.playerRequestedCreampie) {
						priorityOrgasms.add(action);
						
					} else if(SexFlags.playerRequestedPullOut) {
						priorityOrgasms.add(action);
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
		if(Sex.getSexPacePartner()==SexPace.SUB_RESISTING) {

			for (SexActionInterface action : Sex.getAvailableSexActionsPartner()) {
				possibleActions.add(action);
			}
			
			if (!possibleActions.isEmpty()) {
				return possibleActions.get(Util.random.nextInt(possibleActions.size()));
			} else {
				return SexActionUtility.PARTNER_NONE;
			}
		}

		
		// --- Priority 3 | Removing clothing ---
		
		// Skip over remove clothing if action is of HIGH or MAX priority
		if(Sex.getAvailableSexActionsPartner().get(0).getPriority()!=SexActionPriority.HIGH
				&& Sex.getAvailableSexActionsPartner().get(0).getPriority()!=SexActionPriority.UNIQUE_MAX) {
				
			if(!Sex.isPartnerCanRemovePlayersClothes()) {
				if(Sex.isPartnerCanRemoveOwnClothes()) {
					// Get access to own mouth before anything else:
					if(!Sex.getPartner().isCoverableAreaExposed(CoverableArea.MOUTH)) {
						if (Sex.getPartner().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return Sex.partnerManageClothingToAccessCoverableArea(false, CoverableArea.MOUTH);
						}
					}
					
					// Get access to own nipples:
					if (Sex.getPartner().hasBreasts()) {
						if(!Sex.getPartner().isCoverableAreaExposed(CoverableArea.NIPPLES)) {
							if (Sex.getPartner().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
								return Sex.partnerManageClothingToAccessCoverableArea(false, CoverableArea.NIPPLES);
							}
						}
					}
					
					// If the partner is sufficiently turned on (giving some time for foreplay), they will remove clothing blocking their genitals:
					if(Sex.getPartner().getArousal()>=ArousalLevel.ONE_TURNED_ON.getMinimumValue()) {
						// Get access to own penis:
						if (Sex.getPartner().hasPenis()) {
							if(!Sex.getPartner().isCoverableAreaExposed(CoverableArea.PENIS)) {
								if (Sex.getPartner().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
									return Sex.partnerManageClothingToAccessCoverableArea(false, CoverableArea.PENIS);
								}
							}
						}
						// Get access to own vagina:
						if (Sex.getPartner().hasVagina()) {
							if(!Sex.getPartner().isCoverableAreaExposed(CoverableArea.VAGINA)) {
								if (Sex.getPartner().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
									return Sex.partnerManageClothingToAccessCoverableArea(false, CoverableArea.VAGINA);
								}
							}
						}
					}
				}
				
			} else {
				// Get access to player's mouth before their own:
				if(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.MOUTH)) {
					if (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return Sex.partnerManageClothingToAccessCoverableArea(true, CoverableArea.MOUTH);
					}
				}
				
				// Get access to own mouth:
				if(!Sex.getPartner().isCoverableAreaExposed(CoverableArea.MOUTH)) {
					if (Sex.getPartner().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return Sex.partnerManageClothingToAccessCoverableArea(false, CoverableArea.MOUTH);
					}
				}
				
				// Get access to player's nipples before their own:
				if (Main.game.getPlayer().hasBreasts()) {
					if(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.NIPPLES)) {
						if (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
							return Sex.partnerManageClothingToAccessCoverableArea(true, CoverableArea.NIPPLES);
						}
					}
				}
				
				// Get access to own nipples:
				if(Sex.isPartnerCanRemoveOwnClothes()) {
					if (Sex.getPartner().hasBreasts()) {
						if(!Sex.getPartner().isCoverableAreaExposed(CoverableArea.NIPPLES)) {
							if (Sex.getPartner().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
								return Sex.partnerManageClothingToAccessCoverableArea(false, CoverableArea.NIPPLES);
							}
						}
					}
				}

				// If the partner is sufficiently turned on (giving some time for foreplay), they will remove clothing blocking the player's genitals, then clothing blocking their own genitals:
				if(Sex.getPartner().getArousal()>=ArousalLevel.ONE_TURNED_ON.getMinimumValue()) {
					
					// Get access to player's penis:
					if (Main.game.getPlayer().hasPenis()) {
						if(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)) {
							if (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
								return Sex.partnerManageClothingToAccessCoverableArea(true, CoverableArea.PENIS);
							}
						}
					}
					
					// Get access to player's vagina:
					if (Main.game.getPlayer().hasVagina()) {
						if(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.VAGINA)) {
							if (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
								return Sex.partnerManageClothingToAccessCoverableArea(true, CoverableArea.VAGINA);
							}
						}
					}
					
					// Get access to own penis:
					if(Sex.isPartnerCanRemoveOwnClothes()) {
						if (Sex.getPartner().hasPenis()) {
							if(!Sex.getPartner().isCoverableAreaExposed(CoverableArea.PENIS)) {
								if (Sex.getPartner().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
									return Sex.partnerManageClothingToAccessCoverableArea(false, CoverableArea.PENIS);
								}
							}
						}
					}
					
					// Get access to own vagina:
					if(Sex.isPartnerCanRemoveOwnClothes()) {
						if (Sex.getPartner().hasVagina()) {
							if(!Sex.getPartner().isCoverableAreaExposed(CoverableArea.VAGINA)) {
								if (Sex.getPartner().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
									return Sex.partnerManageClothingToAccessCoverableArea(false, CoverableArea.VAGINA);
								}
							}
						}
					}
				}
			}
		}

		
		
		// --- Priority 4 | Move into one of the partner's preferred positions ---
		
		if(getPosition()==SexPosition.STANDING) {
			for(SexActionInterface action : availableActions) {
				if(action.getActionType()==SexActionType.PARTNER_POSITIONING) {
					possibleActions.add(action);
				}
			}
			if (!possibleActions.isEmpty()) {
				return possibleActions.get(Util.random.nextInt(possibleActions.size()));
			}
		}
		
		
		// --- Priority 5 | Ban actions that make no sense for the partner to perform ---
		
		// Ban all vaginal penetrations if the partner is a virgin.
		if(Sex.getPartner().hasStatusEffect(StatusEffect.FETISH_PURE_VIRGIN)) {
			for(SexActionInterface action : availableActions) {
				if(action.getAssociatedOrificeType()!=null) {
					if(action.getAssociatedOrificeType()==OrificeType.VAGINA_PARTNER && action.getActionType()==SexActionType.PARTNER_PENETRATION) {
						bannedActions.add(action);
					}
				}
			}
		}
		
		// Ban all anal actions unless the partner has an anal fetish, or if there is no vagina available to use.
		if(!Sex.getPartner().hasFetish(Fetish.FETISH_ANAL_GIVING)) {
			for(SexActionInterface action : availableActions) {
				if(action.getAssociatedOrificeType()!=null) {
					if(action.getAssociatedOrificeType().isAnus()) {
						if(action.getAssociatedOrificeType().isPlayer()) {
							if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) { // If the player has a vagina, ban the action so vaginal actions are preferred:
								bannedActions.add(action);
							}
						} else {
							bannedActions.add(action);
						}
					}
				}
			}
		}

		
		// --- Priority 6 | Perform actions based on foreplay or sex ---
		
		// Perform foreplay action if arousal is < 25 and haven't orgasmed yet:
		SexAction actionToPerform = null;
		if(Sex.getPartner().getArousal()<ArousalLevel.ONE_TURNED_ON.getMaximumValue() && Sex.getNumberOfPartnerOrgasms()==0) {
			actionToPerform = performForeplayAction();
			if(actionToPerform!=null) {
				return actionToPerform;
			}
			
		} else {
			actionToPerform = performSexAction();
			if(actionToPerform!=null) {
				return actionToPerform;
			}
		}

//		System.out.println("---------------------------");
		
		// --- Priority 7 using other options at random ---
		for (SexActionInterface action : availableActions) {
			possibleActions.add(action);
//			System.out.println(action.getActionTitle());
		}

		possibleActions.removeAll(bannedActions);
		
//		System.out.println("REMOVED:");
//		for (SexActionInterface action : possibleActions) {
//			System.out.println(action.getActionTitle());
//		}
		
		if (!possibleActions.isEmpty()) {
			return possibleActions.get(Util.random.nextInt(possibleActions.size()));
		}
		
		// Priority 9 (last resort):
		return SexActionUtility.PARTNER_NONE;
	}

	
	/**
	 * Finger and tongue actions are considered foreplay.
	 */
	private SexAction performForeplayAction() {
		List<SexActionInterface> availableActions = Sex.getAvailableSexActionsPartner();
		
		// --- Fingering the player: ---
		
		// If the partner is able to finger the player, start sucking on fingers to lubricate them:
		if(Main.game.getPlayer().hasVagina()
				&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
				&& Sex.getWetPenetrationTypes().get(PenetrationType.FINGER_PARTNER).isEmpty()) {
			if(availableActions.contains(PartnerSelfFingerMouth.PARTNER_SELF_FINGER_MOUTH_PENETRATION)) {
				return PartnerSelfFingerMouth.PARTNER_SELF_FINGER_MOUTH_PENETRATION;
			}
		} else {
			bannedActions.add(PartnerSelfFingerMouth.PARTNER_SELF_FINGER_MOUTH_PENETRATION);
		}
		// If the partner is able to stop sucking on fingers, stop it:
		if(availableActions.contains(PartnerSelfFingerMouth.PARTNER_SELF_FINGER_MOUTH_STOP_PENETRATION)) {
			return PartnerSelfFingerMouth.PARTNER_SELF_FINGER_MOUTH_STOP_PENETRATION;
		}
		// Start fingering the player:
		if(availableActions.contains(PartnerFingerVagina.PARTNER_FINGERING_START)) {
			return PartnerFingerVagina.PARTNER_FINGERING_START;
		}

		
		// --- Kissing the player: ---

		// Start kissing the player:
		if(availableActions.contains(PartnerTongueMouth.PARTNER_KISS_START)) {
			return PartnerTongueMouth.PARTNER_KISS_START;
		}

		
		// --- Block penetrative actions that involve penis or tail: ---
		
		for(SexActionInterface action : availableActions) {
			if(action.getActionType() == SexActionType.PARTNER_PENETRATION) {
				// Ban penetrative actions that don't involve tongue or finger:
				if(action.getAssociatedPenetrationType()!=PenetrationType.TONGUE_PLAYER && action.getAssociatedPenetrationType()!=PenetrationType.TONGUE_PARTNER
						&& action.getAssociatedPenetrationType()!=PenetrationType.FINGER_PARTNER && action.getAssociatedPenetrationType()!=PenetrationType.TONGUE_PARTNER) {
					bannedActions.add(action);
				}
			}
			// Ban stop penetration actions:
			if(action.getActionType() == SexActionType.PARTNER_STOP_PENETRATION) {
				bannedActions.add(action);
			}
		}
		
		return null;
	}

	private boolean removedAllPenetrationAfterForeplay = false;
	
	private SexAction performSexAction() {
		List<SexActionInterface> availableActions = Sex.getAvailableSexActionsPartner();
		List<SexActionInterface> returnableActions = new ArrayList<>();
		
		boolean isSexPenetration = false;
		
		// Ban this sick filth!
		bannedActions.add(PartnerSelfFingerMouth.PARTNER_SELF_FINGER_MOUTH_PENETRATION);
		
		// Is any sexual penetration happening:
		for(Entry<PenetrationType, Set<OrificeType>> e : Sex.getOngoingPenetrationMap().entrySet()) {
			if(e.getKey().isPenis() || e.getKey().isTail() || e.getKey().isTentacle()) {
				isSexPenetration = true;
			}
		}
		
		if(!isSexPenetration) {
			// --- Stop foreplay actions: ---
			if(!removedAllPenetrationAfterForeplay) {
				for(SexActionInterface action : availableActions) {
					if(action.getActionType() == SexActionType.PARTNER_STOP_PENETRATION) {
						return (SexAction) action;
					}
				}
			}
			removedAllPenetrationAfterForeplay = true;
			
			// --- Start penetrating: ---
			for(SexActionInterface action : availableActions) {
				if(action.getActionType()==SexActionType.PARTNER_PENETRATION) {
					if(action.getAssociatedPenetrationType().isPenis() || action.getAssociatedPenetrationType().isTail() || action.getAssociatedPenetrationType().isTentacle()) {
						// Anal penetrations:
						if((Sex.getPartner().hasFetish(Fetish.FETISH_ANAL_GIVING) || Sex.getPlayerPenetrationRequests().contains(OrificeType.ANUS_PLAYER)) && action.getAssociatedOrificeType()!=null) {
							if(action.getAssociatedOrificeType().isAnus()) {
								returnableActions.add(action);
							}
						}
						// Nipple penetrations:
						if((Sex.getPartner().hasFetish(Fetish.FETISH_BREASTS_OTHERS) || Sex.getPlayerPenetrationRequests().contains(OrificeType.NIPPLE_PLAYER)) && action.getAssociatedOrificeType()!=null) {
							if(action.getAssociatedOrificeType().isNipple()) {
								returnableActions.add(action);
							}
						}
						// Vaginal penetration on player:
						if((Sex.getPartner().hasFetish(Fetish.FETISH_IMPREGNATION) || Sex.getPartner().hasFetish(Fetish.FETISH_SEEDER) || Sex.getPlayerPenetrationRequests().contains(OrificeType.VAGINA_PLAYER))
								&& action.getAssociatedOrificeType()!=null && action.getAssociatedPenetrationType().isPenis()) {
							if(action.getAssociatedOrificeType()==OrificeType.VAGINA_PLAYER) {
								returnableActions.add(action);
							}
						}
						// Vaginal penetration for self:
						if((Sex.getPartner().hasFetish(Fetish.FETISH_PREGNANCY) || Sex.getPartner().hasFetish(Fetish.FETISH_BROODMOTHER)) && action.getAssociatedOrificeType()!=null && action.getAssociatedPenetrationType().isPenis()) {
							if(action.getAssociatedOrificeType()==OrificeType.VAGINA_PARTNER) {
								returnableActions.add(action);
							}
						}
					}
				}
			}
			if(returnableActions.isEmpty()) {
				for(SexActionInterface action : availableActions) {
					if(action.getActionType()==SexActionType.PARTNER_PENETRATION) {
						if(action.getAssociatedPenetrationType().isPenis() || action.getAssociatedPenetrationType().isTail() || action.getAssociatedPenetrationType().isTentacle()) {
							returnableActions.add(action);
						}
					}
				}
			}
			if(!returnableActions.isEmpty()) {
				return (SexAction) returnableActions.get(Util.random.nextInt(returnableActions.size()));
			}
		}

		// Ban stop penetration actions:
		for(SexActionInterface action : availableActions) {
			if(action.getActionType() == SexActionType.PARTNER_STOP_PENETRATION) {
				if(action.getAssociatedPenetrationType().isPenis() || action.getAssociatedPenetrationType().isTail() || action.getAssociatedPenetrationType().isTentacle()) {
					bannedActions.add(action);
				}
			}
		}
		
		return null;
	}
	
	@Override
	public boolean isConsensualSex(){
		return true;
	}

	@Override
	public boolean isPlayerCanRemoveOwnClothes(){
		return true;
	}
	
	@Override
	public boolean isPlayerCanRemovePartnersClothes(){
		return true;
	}
	
	@Override
	public boolean isPartnerCanRemoveOwnClothes(){
		return true;
	}
	
	@Override
	public boolean isPartnerCanRemovePlayersClothes(){
		return true;
	}

	@Override
	public String getStartSexDescription() {
		return "";//"<p>You are having sex with " + Sex.getPartner().getName("the") + ".</p>"; // TODO
	}
	
}