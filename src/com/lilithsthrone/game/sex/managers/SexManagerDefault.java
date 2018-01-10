package com.lilithsthrone.game.sex.managers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.SexActionUtility;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerFingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerTongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfFingerMouth;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import java.util.Set;

/**
 * @since 0.1.0
 * @version 0.1.96
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
					if(container!=null) {
						Field[] fields = container.getFields();
						
						for(Field f : fields){
							
							if (SexAction.class.isAssignableFrom(f.getType())) {
								if (((SexAction) f.get(null)).getActionType().isOrgasmOption()) {
									if (((SexAction) f.get(null)).getActionType() == SexActionType.MUTUAL_ORGASM) {
										mutualOrgasmActions.add(((SexAction) f.get(null)));
										
									} else if (((SexAction) f.get(null)).getActionType().isPlayerAction()) {
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
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addSexActionClass(Class<?> container) {
		try {
			if(container!=null) {
				Field[] fields = container.getFields();
				
				for(Field f : fields){
					
					if (SexAction.class.isAssignableFrom(f.getType())) {
						if (((SexAction) f.get(null)).getActionType().isOrgasmOption()) {
							if (((SexAction) f.get(null)).getActionType().isPlayerAction()) {
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
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
//	// A+ quality code example: /s
//	protected void overrideAction(SexActionInterface oldAction, SexActionInterface newAction){
//		for(int i=0 ; i< actionsAvailablePlayer.size(); i++){
//			if(actionsAvailablePlayer.get(i) == oldAction) {
//				actionsAvailablePlayer.set(i, newAction);
//				return;
//			}
//		}
//		for(int i=0 ; i< actionsAvailablePartner.size(); i++){
//			if(actionsAvailablePartner.get(i) == oldAction) {
//				actionsAvailablePartner.set(i, newAction);
//				return;
//			}
//		}
//		for(int i=0 ; i< orgasmActionsPlayer.size(); i++){
//			if(orgasmActionsPlayer.get(i) == oldAction) {
//				orgasmActionsPlayer.set(i, newAction);
//				return;
//			}
//		}
//		for(int i=0 ; i< orgasmActionsPartner.size(); i++){
//			if(orgasmActionsPartner.get(i) == oldAction) {
//				orgasmActionsPartner.set(i, newAction);
//				return;
//			}
//		}
//		for(int i=0 ; i< mutualOrgasmActions.size(); i++){
//			if(mutualOrgasmActions.get(i) == oldAction) {
//				mutualOrgasmActions.set(i, newAction);
//				return;
//			}
//		}
//	}
	
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
	
	
	/*
	 * New:
	 * - Get accessible areas
	 * - Choose foreplay & main sex
	 * - Choose positions for each
	 * - Clothing for foreplay
	 * - position
	 * - foreplay (self-actions take minimum priority)
	 * - clothing for main
	 * - position
	 * - main (self-actions take minimum priority)
	 * - orgasm
	 */
	
	@Override
	public SexActionInterface getPartnerSexAction(SexActionInterface sexActionPlayer) {
		
		possibleActions.clear();
		bannedActions.clear();

		List<SexActionInterface> availableActions = Sex.getAvailableSexActionsPartner();
		
		// --- Priority 1 | If orgasming, bypass everything and use an orgasm option ---
		
		if (ArousalLevel.getArousalLevelFromValue(Sex.getActivePartner().getArousal()) == ArousalLevel.FIVE_ORGASM_IMMINENT) {
			List<SexActionInterface> priorityOrgasms = new ArrayList<>();
			
			for(SexActionInterface action : availableActions) {
				if(action.getPlayerAreasCummedIn() != null) {
					if((action.getPlayerAreasCummedIn().contains(OrificeType.VAGINA_PLAYER)
							&& (Sex.getActivePartner().hasFetish(Fetish.FETISH_IMPREGNATION) || Sex.getActivePartner().hasFetish(Fetish.FETISH_SEEDER)))
						|| (action.getPlayerAreasCummedIn().contains(OrificeType.VAGINA_PARTNER)
								&& (Sex.getActivePartner().hasFetish(Fetish.FETISH_PREGNANCY) || Sex.getActivePartner().hasFetish(Fetish.FETISH_BROODMOTHER)))
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
		if(Sex.getSexPace(Sex.getActivePartner())==SexPace.SUB_RESISTING) {
			for (SexActionInterface action : Sex.getAvailableSexActionsPartner()) {
				possibleActions.add(action);
			}
			
			if (!possibleActions.isEmpty()) {
				return possibleActions.get(Util.random.nextInt(possibleActions.size()));
			} else {
				return SexActionUtility.PARTNER_NONE;
			}
		}

		
		// --- Priority 3 | Move into one of the partner's preferred positions ---
		
		if(!Sex.getActivePartner().getSexPositionPreferences().contains(getPosition())) {
			for(SexActionInterface action : availableActions) {
				if(action.getActionType()==SexActionType.PARTNER_POSITIONING) {
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
				if(action.getActionType()==SexActionType.PARTNER_POSITIONING) {
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
					PenetrationType pen = Sex.getActivePartner().getForeplayPreference().getPenetrationType();
					if(pen.isPlayer()) {
						if(pen.isPenis() && !Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							playerAreasToBeExposed.add(CoverableArea.PENIS);
						} else if(pen.isTongue() && !Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.MOUTH) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							playerAreasToBeExposed.add(CoverableArea.MOUTH);
						}
					} else {
						if(pen.isPenis() && !Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.PENIS) && Sex.getActivePartner().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							partnerAreasToBeExposed.add(CoverableArea.PENIS);
						} else if(pen.isTongue() && !Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.MOUTH) && Sex.getActivePartner().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							partnerAreasToBeExposed.add(CoverableArea.MOUTH);
						}
					}
					
					OrificeType orifice = Sex.getActivePartner().getForeplayPreference().getOrificeType();
					if(orifice.isPlayer()) {
						if(orifice.isAnus() && !Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.ANUS) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
							playerAreasToBeExposed.add(CoverableArea.ANUS);
						} else if((orifice.isBreasts() || orifice.isNipple()) && !Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.NIPPLES) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
							playerAreasToBeExposed.add(CoverableArea.NIPPLES);
						} else if(orifice.isMouth() && !Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.MOUTH) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							playerAreasToBeExposed.add(CoverableArea.MOUTH);
						} else if(orifice.isUrethra() && !Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							playerAreasToBeExposed.add(CoverableArea.PENIS);
						} else if(orifice.isVagina() && !Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.VAGINA) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
							playerAreasToBeExposed.add(CoverableArea.VAGINA);
						}
					} else {
						if(orifice.isAnus() && !Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.ANUS) && Sex.getActivePartner().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
							partnerAreasToBeExposed.add(CoverableArea.ANUS);
						} else if((orifice.isBreasts() || orifice.isNipple()) && !Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.NIPPLES) && Sex.getActivePartner().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
							partnerAreasToBeExposed.add(CoverableArea.NIPPLES);
						} else if(orifice.isMouth() && !Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.MOUTH) && Sex.getActivePartner().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							partnerAreasToBeExposed.add(CoverableArea.MOUTH);
						} else if(orifice.isUrethra() && !Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.PENIS) && Sex.getActivePartner().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							partnerAreasToBeExposed.add(CoverableArea.PENIS);
						} else if(orifice.isVagina() && !Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.VAGINA) && Sex.getActivePartner().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
							partnerAreasToBeExposed.add(CoverableArea.VAGINA);
						}
					}
					
				} else {
					if(playerAreasToBeExposed.isEmpty()){
//						if(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.ANUS) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
//							playerAreasToBeExposed.add(CoverableArea.ANUS);
//						} else if(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
//							playerAreasToBeExposed.add(CoverableArea.PENIS);
//						} else if(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.VAGINA) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
//							playerAreasToBeExposed.add(CoverableArea.VAGINA);
//						}
						if(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.NIPPLES) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
							playerAreasToBeExposed.add(CoverableArea.NIPPLES);
						} else if(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.MOUTH) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							playerAreasToBeExposed.add(CoverableArea.MOUTH);
						}
					}
					if(partnerAreasToBeExposed.isEmpty()){
//						if(!Sex.getPartner().isCoverableAreaExposed(CoverableArea.ANUS) && Sex.getPartner().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
//							partnerAreasToBeExposed.add(CoverableArea.ANUS);
//						} else if(!Sex.getPartner().isCoverableAreaExposed(CoverableArea.PENIS) && Sex.getPartner().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
//							partnerAreasToBeExposed.add(CoverableArea.PENIS);
//						} else if(!Sex.getPartner().isCoverableAreaExposed(CoverableArea.VAGINA) && Sex.getPartner().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
//							partnerAreasToBeExposed.add(CoverableArea.VAGINA);
//						}
						if(!Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.NIPPLES) && Sex.getActivePartner().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
							partnerAreasToBeExposed.add(CoverableArea.NIPPLES);
						} else if(!Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.MOUTH) && Sex.getActivePartner().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							partnerAreasToBeExposed.add(CoverableArea.MOUTH);
						}
					}
				}
				
			} else {
				if(Sex.getActivePartner().getMainSexPreference()!=null) {
					PenetrationType pen = Sex.getActivePartner().getMainSexPreference().getPenetrationType();
					if(pen.isPlayer()) {
						if(pen.isPenis() && !Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							playerAreasToBeExposed.add(CoverableArea.PENIS);
						} else if(pen.isTongue() && !Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.MOUTH) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							playerAreasToBeExposed.add(CoverableArea.MOUTH);
						}
					} else {
						if(pen.isPenis() && !Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.PENIS) && Sex.getActivePartner().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							partnerAreasToBeExposed.add(CoverableArea.PENIS);
						} else if(pen.isTongue() && !Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.MOUTH) && Sex.getActivePartner().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							partnerAreasToBeExposed.add(CoverableArea.MOUTH);
						}
					}
					
					OrificeType orifice = Sex.getActivePartner().getMainSexPreference().getOrificeType();
					if(orifice.isPlayer()) {
						if(orifice.isAnus() && !Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.ANUS) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
							playerAreasToBeExposed.add(CoverableArea.ANUS);
						} else if((orifice.isBreasts() || orifice.isNipple()) && !Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.NIPPLES) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
							playerAreasToBeExposed.add(CoverableArea.NIPPLES);
						} else if(orifice.isMouth() && !Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.MOUTH) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							playerAreasToBeExposed.add(CoverableArea.MOUTH);
						} else if(orifice.isUrethra() && !Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							playerAreasToBeExposed.add(CoverableArea.PENIS);
						} else if(orifice.isVagina() && !Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.VAGINA) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
							playerAreasToBeExposed.add(CoverableArea.VAGINA);
						}
					} else {
						if(orifice.isAnus() && !Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.ANUS) && Sex.getActivePartner().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
							partnerAreasToBeExposed.add(CoverableArea.ANUS);
						} else if((orifice.isBreasts() || orifice.isNipple()) && !Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.NIPPLES) && Sex.getActivePartner().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
							partnerAreasToBeExposed.add(CoverableArea.NIPPLES);
						} else if(orifice.isMouth() && !Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.MOUTH) && Sex.getActivePartner().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							partnerAreasToBeExposed.add(CoverableArea.MOUTH);
						} else if(orifice.isUrethra() && !Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.PENIS) && Sex.getActivePartner().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							partnerAreasToBeExposed.add(CoverableArea.PENIS);
						} else if(orifice.isVagina() && !Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.VAGINA) && Sex.getActivePartner().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
							partnerAreasToBeExposed.add(CoverableArea.VAGINA);
						}
					}
					
				} 
//				else {
					if(playerAreasToBeExposed.isEmpty()){
//						if(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.ANUS) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
//							playerAreasToBeExposed.add(CoverableArea.ANUS);
//						} else
						if(Main.game.getPlayer().hasPenis() && !Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							playerAreasToBeExposed.add(CoverableArea.PENIS);
						} else if(Main.game.getPlayer().hasVagina() && !Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.VAGINA) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
							playerAreasToBeExposed.add(CoverableArea.VAGINA);
						}
//						else if(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.NIPPLES) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
//							playerAreasToBeExposed.add(CoverableArea.NIPPLES);
//						} else if(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.MOUTH) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
//							playerAreasToBeExposed.add(CoverableArea.MOUTH);
//						}
					}
					if(partnerAreasToBeExposed.isEmpty()){
//						if(!Sex.getPartner().isCoverableAreaExposed(CoverableArea.ANUS) && Sex.getPartner().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
//							partnerAreasToBeExposed.add(CoverableArea.ANUS);
//						} else
						if(Sex.getActivePartner().hasPenis() && !Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.PENIS) && Sex.getActivePartner().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							partnerAreasToBeExposed.add(CoverableArea.PENIS);
						} else if(Sex.getActivePartner().hasVagina() && !Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.VAGINA) && Sex.getActivePartner().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
							partnerAreasToBeExposed.add(CoverableArea.VAGINA);
						}
//						else if(!Sex.getPartner().isCoverableAreaExposed(CoverableArea.NIPPLES) && Sex.getPartner().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
//							partnerAreasToBeExposed.add(CoverableArea.NIPPLES);
//						} else if(!Sex.getPartner().isCoverableAreaExposed(CoverableArea.MOUTH) && Sex.getPartner().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
//							partnerAreasToBeExposed.add(CoverableArea.MOUTH);
//						}
					}
//				}
			}
			
			if(!partnerAreasToBeExposed.isEmpty() && Sex.isPartnerCanRemoveOwnClothes()) {
				Collections.shuffle(partnerAreasToBeExposed);
				if(partnerAreasToBeExposed.get(0) == CoverableArea.MOUND) {
					return Sex.partnerManageClothingToAccessCoverableArea(false, CoverableArea.VAGINA);
				} else {
					return Sex.partnerManageClothingToAccessCoverableArea(false, partnerAreasToBeExposed.get(0));
				}
			}
			if(!playerAreasToBeExposed.isEmpty() && Sex.isPartnerCanRemovePlayersClothes()) {
				Collections.shuffle(playerAreasToBeExposed);
				if(playerAreasToBeExposed.get(0) == CoverableArea.MOUND) {
					return Sex.partnerManageClothingToAccessCoverableArea(true, CoverableArea.VAGINA);
				} else {
					return Sex.partnerManageClothingToAccessCoverableArea(true, playerAreasToBeExposed.get(0));
				}
			}
			
		}

		
		// --- Priority 5 | Ban actions that make no sense for the partner to perform ---
		
		// Ban all penetrations if the partner is a virgin in the associated orifice:
		for(SexActionInterface action : availableActions) {
			if(action.getAssociatedOrificeType()!=null && action.getActionType()==SexActionType.PARTNER_PENETRATION) {
				switch(action.getAssociatedOrificeType()) {
					case ANUS_PARTNER:
						if(Sex.getActivePartner().isAssVirgin() && Sex.getSexPace(Sex.getActivePartner())!=SexPace.SUB_EAGER) {
							bannedActions.add(action);
						}
						break;
					case MOUTH_PARTNER:
						if(Sex.getActivePartner().isFaceVirgin() && Sex.getSexPace(Sex.getActivePartner())!=SexPace.SUB_EAGER) {
							bannedActions.add(action);
						}
						break;
					case NIPPLE_PARTNER:
						if(Sex.getActivePartner().isNippleVirgin() && Sex.getSexPace(Sex.getActivePartner())!=SexPace.SUB_EAGER) {
							bannedActions.add(action);
						}
						break;
					case URETHRA_PARTNER:
						if(Sex.getActivePartner().isUrethraVirgin() && Sex.getSexPace(Sex.getActivePartner())!=SexPace.SUB_EAGER) {
							bannedActions.add(action);
						}
						break;
					case VAGINA_PARTNER:
						if(Sex.getActivePartner().hasStatusEffect(StatusEffect.FETISH_PURE_VIRGIN)
								|| Sex.getActivePartner().hasStatusEffect(StatusEffect.FETISH_PURE_VIRGIN_LUSTY_MAIDEN)
								|| (Sex.getActivePartner().isVaginaVirgin() && Sex.getSexPace(Sex.getActivePartner())!=SexPace.SUB_EAGER)) {
							bannedActions.add(action);
						}
						break;
					default:
						break;
				}
			}
		}
		
		// Ban all anal actions unless the partner has an anal fetish, or if there is no vagina available to use.
		if(!Sex.getActivePartner().hasFetish(Fetish.FETISH_ANAL_GIVING)) {
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
	private SexAction performForeplayAction(SexActionInterface sexActionPlayer) {
		List<SexActionInterface> availableActions = Sex.getAvailableSexActionsPartner();
		bannedActions.add(PartnerSelfFingerMouth.PARTNER_SELF_FINGER_MOUTH_PENETRATION);
		
		if(sexActionPlayer.getActionType()==SexActionType.PLAYER_STOP_PENETRATION) {
			availableActions.removeIf(sexAction -> sexAction.getActionType()==SexActionType.PARTNER_PENETRATION);
		}
		
		availableActions.removeAll(bannedActions);

		// If the NPC has a preference, they are more likely to choose actions related to that:
		if(Sex.getActivePartner().getForeplayPreference()!=null) {
			List<SexActionInterface> highPriorityList = new ArrayList<>();
			for(SexActionInterface action : availableActions) {
				if(action.getAssociatedOrificeType() == Sex.getActivePartner().getForeplayPreference().getOrificeType()
						&& action.getAssociatedPenetrationType() == Sex.getActivePartner().getForeplayPreference().getPenetrationType()) {
					highPriorityList.add(action);
					if(action.getActionType() == SexActionType.PARTNER_PENETRATION) { // If a penetrative action is in the list, always return that first.
						return (SexAction) action;
					}
				}
			}
			
			if(!highPriorityList.isEmpty() && Math.random()<0.7f) { // 70% chance, so that there is some chance of using other actions as well:
				// Kissing is a fundamental part of foreplay!
				if(availableActions.contains(PartnerTongueMouth.PARTNER_KISS_START)) {
					highPriorityList.add(PartnerTongueMouth.PARTNER_KISS_START);
				}
				
				return (SexAction) highPriorityList.get(Util.random.nextInt(highPriorityList.size()));
			}
		}

		// --- Fingering the player: ---
		if(availableActions.contains(PartnerFingerVagina.PARTNER_FINGERING_START)) {
			return PartnerFingerVagina.PARTNER_FINGERING_START;
		}
		
		// --- Kissing the player: ---

		// Start kissing the player:
		if(availableActions.contains(PartnerTongueMouth.PARTNER_KISS_START)) {
			return PartnerTongueMouth.PARTNER_KISS_START;
		}
		
		// --- Block penetrative actions that involve penis or tail in vagina/anus ---
		
		for(SexActionInterface action : availableActions) {
			if(action.getActionType() == SexActionType.PARTNER_PENETRATION) {
				if((action.getAssociatedPenetrationType()==PenetrationType.PENIS_PLAYER
						|| action.getAssociatedPenetrationType()==PenetrationType.PENIS_PARTNER
						|| action.getAssociatedPenetrationType()==PenetrationType.TAIL_PLAYER
						|| action.getAssociatedPenetrationType()==PenetrationType.TAIL_PARTNER)
						&&
						(action.getAssociatedOrificeType()!=OrificeType.MOUTH_PLAYER
						&& action.getAssociatedOrificeType()!=OrificeType.MOUTH_PARTNER
						&& action.getAssociatedOrificeType()!=OrificeType.BREAST_PLAYER
						&& action.getAssociatedOrificeType()!=OrificeType.BREAST_PARTNER)) {
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
	
	private SexAction performSexAction(SexActionInterface sexActionPlayer) {
		List<SexActionInterface> availableActions = Sex.getAvailableSexActionsPartner();
		
		if(sexActionPlayer.getActionType()==SexActionType.PLAYER_STOP_PENETRATION) {
			availableActions.removeIf(sexAction -> sexAction.getActionType()==SexActionType.PARTNER_PENETRATION);
		}
		
		bannedActions.add(PartnerSelfFingerMouth.PARTNER_SELF_FINGER_MOUTH_PENETRATION);
		availableActions.removeAll(bannedActions);
		List<SexActionInterface> returnableActions = new ArrayList<>();
		
		boolean isSexPenetration = false;
		
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
						if(!(action.getAssociatedPenetrationType()==PenetrationType.TONGUE_PARTNER && action.getAssociatedOrificeType()==OrificeType.MOUTH_PLAYER)
								&& !(action.getAssociatedPenetrationType()==PenetrationType.TONGUE_PLAYER && action.getAssociatedOrificeType()==OrificeType.MOUTH_PARTNER)) { // Don't stop kissing actions:
							returnableActions.add(action);
						}
					}
				}
			}
			if(returnableActions.size()<=1) {
				removedAllPenetrationAfterForeplay = true;
				SexFlags.positioningBlockedPartner = false;
			}
			if(!returnableActions.isEmpty()) {
				return (SexAction) returnableActions.get(Util.random.nextInt(returnableActions.size()));
			}
			
			// If the NPC has a preference, they are more likely to choose actions related to that:
			List<SexActionInterface> penetrativeActionList = new ArrayList<>();
			if(Sex.getActivePartner().getMainSexPreference()!=null) {
				List<SexActionInterface> highPriorityList = new ArrayList<>();
				for(SexActionInterface action : availableActions) {
					if(action.getAssociatedOrificeType() == Sex.getActivePartner().getMainSexPreference().getOrificeType()
							&& action.getAssociatedPenetrationType() == Sex.getActivePartner().getMainSexPreference().getPenetrationType()) {
						highPriorityList.add(action);
						if(action.getActionType() == SexActionType.PARTNER_PENETRATION) { // If a penetrative action is in the list, always return that first.
							penetrativeActionList.add(action);
						}
					}
					
				}
				
				if(penetrativeActionList.isEmpty() && !highPriorityList.isEmpty() && Math.random()<0.7f) { // 70% chance, so that there is some chance of using other actions as well:
					return (SexAction) highPriorityList.get(Util.random.nextInt(highPriorityList.size()));
				}
			}
			
			// --- Start penetrating: ---
			for(SexActionInterface action : availableActions) {
				if(action.getActionType()==SexActionType.PARTNER_PENETRATION) {
					if(action.getAssociatedPenetrationType().isPenis() || action.getAssociatedPenetrationType().isTail() || action.getAssociatedPenetrationType().isTentacle()) {
						// Anal penetrations:
						if((Sex.getActivePartner().hasFetish(Fetish.FETISH_ANAL_GIVING) || Sex.getPlayerPenetrationRequests().contains(OrificeType.ANUS_PLAYER))
								&& action.getAssociatedOrificeType()!=null && action.getAssociatedOrificeType().isAnus()) {
							penetrativeActionList.add(action);
						}
						// Nipple penetrations:
						if((Sex.getActivePartner().hasFetish(Fetish.FETISH_BREASTS_OTHERS) || Sex.getPlayerPenetrationRequests().contains(OrificeType.NIPPLE_PLAYER))
								&& action.getAssociatedOrificeType()!=null && action.getAssociatedOrificeType().isNipple()) {
							penetrativeActionList.add(action);
						}
						// Paizuri:
						if((Sex.getActivePartner().hasFetish(Fetish.FETISH_BREASTS_OTHERS) || Sex.getPlayerPenetrationRequests().contains(OrificeType.BREAST_PLAYER))
								&& action.getAssociatedOrificeType()!=null && action.getAssociatedOrificeType().isBreasts()) {
								penetrativeActionList.add(action);
						}
						// Vaginal penetration on player:
						if((Sex.getActivePartner().hasFetish(Fetish.FETISH_IMPREGNATION) || Sex.getActivePartner().hasFetish(Fetish.FETISH_SEEDER) || Sex.getPlayerPenetrationRequests().contains(OrificeType.VAGINA_PLAYER))
								&& action.getAssociatedOrificeType()!=null && action.getAssociatedOrificeType()==OrificeType.VAGINA_PLAYER && action.getAssociatedPenetrationType()!=null && action.getAssociatedPenetrationType().isPenis()) {
							penetrativeActionList.add(action);
						}
						// Vaginal penetration for self:
						if((Sex.getActivePartner().hasFetish(Fetish.FETISH_PREGNANCY) || Sex.getActivePartner().hasFetish(Fetish.FETISH_BROODMOTHER))
								&& action.getAssociatedOrificeType()!=null && action.getAssociatedOrificeType()==OrificeType.VAGINA_PARTNER && action.getAssociatedPenetrationType()!=null && action.getAssociatedPenetrationType().isPenis()) {
							penetrativeActionList.add(action);
						}
					}
				}
			}
			if(!penetrativeActionList.isEmpty()) {
				return (SexAction) penetrativeActionList.get(Util.random.nextInt(penetrativeActionList.size()));
			}
			
			for(SexActionInterface action : availableActions) {
				if(action.getActionType()==SexActionType.PARTNER_PENETRATION) {
					if(action.getAssociatedPenetrationType().isPenis() || action.getAssociatedPenetrationType().isTail() || action.getAssociatedPenetrationType().isTentacle()) {
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
			if(action.getActionType() == SexActionType.PARTNER_STOP_PENETRATION) {
				if(action.getAssociatedPenetrationType().isPenis() || action.getAssociatedPenetrationType().isTail() || action.getAssociatedPenetrationType().isTentacle()) {
					bannedActions.add(action);
				}
			}
		}
		
		return null;
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