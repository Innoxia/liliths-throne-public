package com.base.game.sex.sexActions;

import java.util.List;
import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.effects.Fetish;
import com.base.game.dialogue.responses.Response;
import com.base.game.sex.ArousalIncrease;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.Sex;
import com.base.game.sex.SexPace;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.8
 * @author Innoxia
 */
public interface SexActionInterface {
	
	public abstract SexActionType getActionType();
	
	public abstract String getActionTitle();

	public abstract String getActionDescription();
	
	public abstract String getDescription();

	/**
	 * @return A list of fetishes that affect the player in this sex action.
	 */
	public default List<Fetish> getFetishesPlayer() {
		return null;
	}

	public CorruptionLevel getCorruptionNeeded();
	
	public default PenetrationType getAssociatedPenetrationType() { return null; }
	
	public default OrificeType getAssociatedOrificeType() { return null; }
	
	// Sex-specific:
	
	public ArousalIncrease getArousalGainPlayer();

	public ArousalIncrease getArousalGainPartner();
	
	public default boolean isPartnerSelfAction() {

		boolean selfInteraction = false;
		
		if(getAssociatedPenetrationType()!=null) {
			if(getAssociatedPenetrationType().isPlayer()) {
				return false;
			} else {
				selfInteraction=true;
			}
		}
		
		if(getAssociatedOrificeType()!=null) {
			if(getAssociatedOrificeType().isPlayer()) {
				return false;
			} else {
				selfInteraction=true;
			}
		}
		
		return selfInteraction;
	}
	
	public default boolean isPartnerSelfPenetration() {
		if(getAssociatedPenetrationType()==null)
			return false;
		
		if(getAssociatedOrificeType()==null)
			return false;
		
		return getActionType()==SexActionType.PARTNER_PENETRATION && !getAssociatedPenetrationType().isPlayer() && !getAssociatedOrificeType().isPlayer();
	}
	
	public default void baseEffects() {
		
		if(getActionType()==SexActionType.PLAYER_PENETRATION || getActionType() == SexActionType.PARTNER_PENETRATION) {
			if(getAssociatedPenetrationType()!=null && getAssociatedOrificeType()!=null)
				Sex.applyPenetration(getAssociatedPenetrationType(), getAssociatedOrificeType());
		}
		
		if(getActionType()==SexActionType.PLAYER_STOP_PENETRATION || getActionType() == SexActionType.PARTNER_STOP_PENETRATION) {
			if(getAssociatedPenetrationType()!=null) {
				if(getAssociatedOrificeType()!=null) {
					Sex.removePenetration(getAssociatedPenetrationType(), getAssociatedOrificeType());
				} else {
					Sex.getOngoingPenetrationMap().remove(getAssociatedPenetrationType()); // Remove all penetration if no orifice is specified.
				}
				
			} else {
				if(getAssociatedOrificeType()!=null) {
					for(PenetrationType pt : PenetrationType.values()) {
						if(Sex.getOngoingPenetrationMap().containsKey(pt)) {
							Sex.removePenetration(pt, getAssociatedOrificeType());
						}
					}
				}
			}
		}
		
		applyEffects();
	};

	public default void applyEffects(){
	}
	
	public default boolean isBaseRequirementsMet() { return true; };
	
	/**
	 * Used for determining how likely an NPC is to use this action.
	 */
	public default SexActionPriority getPriority() {
		return SexActionPriority.NORMAL;
	}
	
	/**
	 * Defines if this SexAction switches the pace of sex.
	 * @return null if no switch.
	 */
	public SexPace getSexPacePlayer();
	/**
	 * Defines if this SexAction switches the pace of sex.
	 * @return null if no switch.
	 */
	public SexPace getSexPacePartner();
	
	public default boolean endsSex() {
		return false;
	}
	
	public default boolean isAddedToAvailableSexActions() {
		return toResponse() != null;
	}
	
	public default Response toResponse() {
		if(isBaseRequirementsMet()){
			
			if(!isPhysicallyPossible()) {
				return null;
			}
			
			// You can't resist in consensual sex or if non-con is disabled:
			if((getSexPacePlayer()==SexPace.SUB_RESISTING || getSexPacePartner()==SexPace.SUB_RESISTING)) {
				if(Sex.getSexManager().isConsensualSex() || !Main.game.isNonConEnabled()) {
					return null;
				}
			}
			
			// If this is a positioning action, only allow it if there is no penetration occurring:
			if(getActionType()==SexActionType.PLAYER_POSITIONING || getActionType() == SexActionType.PARTNER_POSITIONING) {
				
				if(Sex.isAnyNonSelfPenetrationHappening()
						&& !Sex.getSexManager().isConsensualSex()
						&& ((getActionType().isPlayerAction() && !Sex.isPlayerDom()) || (!getActionType().isPlayerAction() && Sex.isPlayerDom()))) {
					return convertToNullResponse();
					
				} else {
					return convertToResponse();
				}
			
				
			// If this is a 'start penetration' action, check to see if all the requirements are met:
			} else if(getActionType()==SexActionType.PLAYER_PENETRATION || getActionType() == SexActionType.PARTNER_PENETRATION) {
				
				// Penetration actions (not including self-penetration actions) are only available in consensual sex or if the penetrator is the dom:
				if(getAssociatedPenetrationType()!=null && getAssociatedOrificeType()!=null) {
					if(getAssociatedPenetrationType().isPlayer() != getAssociatedOrificeType().isPlayer()) { // This is a penetrative action between both partners:
						if(getActionType().isPlayerAction()) { // Player is performing action:
							if(!Sex.getSexManager().isConsensualSex() && !Sex.isPlayerDom()) {
								return null;
							}
							
						} else { // Partner is performing action:
							if(!Sex.getSexManager().isConsensualSex() && Sex.isPlayerDom()) {
								return null;
							}
						}
					}
				}
				
				// Make sure the PenetrationType is available:
				if(getAssociatedPenetrationType()!=null) {
					// Check for access:
					if(getAssociatedPenetrationType().isPlayer()) {
						if(!Main.game.getPlayer().isPenetrationTypeExposed(getAssociatedPenetrationType())) {
							return convertToNullResponse();
						}
					} else {
						if(!Sex.getPartner().isPenetrationTypeExposed(getAssociatedPenetrationType())) {
							return convertToNullResponse();
						}
					}
					
					// Check to see if it's already in use:
					if(!getAssociatedPenetrationType().isFree()) {
						return null;
					}
				}
				
				// Make sure the OrificeType is available:
				if(getAssociatedOrificeType() != null) {
					switch(getAssociatedOrificeType()){
						case NIPPLE_PARTNER:
							if(!Sex.getPartner().isBreastFuckable())
								return null;
							break;
						case NIPPLE_PLAYER:
							if(!Main.game.getPlayer().isBreastFuckable())
								return null;
							break;
						default:
							break;
					}
					
					// Check for access:
					if(getAssociatedOrificeType().isPlayer()) {
						if(!Main.game.getPlayer().isOrificeTypeExposed(getAssociatedOrificeType())) {
							return convertToNullResponse();
						}
					} else {
						if(!Sex.getPartner().isOrificeTypeExposed(getAssociatedOrificeType())) {
							return convertToNullResponse();
						}
					}
					
					// Check to see if it's already in use:
					if(!getAssociatedOrificeType().isFree()) {
						return null;
					}
				}
				
				return convertToResponse();
				
			// If it's a no-penetration action, the PenetrationType and the OrificeType both need to be exposed and free.
			} else if(getActionType()==SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED || getActionType() == SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED) {
				
				if(getAssociatedPenetrationType()!=null) {
					// Check for access:
					if(getAssociatedPenetrationType().isPlayer()) {
						if(!Main.game.getPlayer().isPenetrationTypeExposed(getAssociatedPenetrationType())) {
							return convertToNullResponse();
						}
					} else {
						if(!Sex.getPartner().isPenetrationTypeExposed(getAssociatedPenetrationType())) {
							return convertToNullResponse();
						}
					}
					
					// Check to see if it's already in use:
					if(!getAssociatedPenetrationType().isFree()) {
						return null;
					}
				}
				
				if(getAssociatedOrificeType() != null) {
					// Check for access:
					if(getAssociatedOrificeType().isPlayer()) {
						if(!Main.game.getPlayer().isOrificeTypeExposed(getAssociatedOrificeType())) {
							return convertToNullResponse();
						}
					} else {
						if(!Sex.getPartner().isOrificeTypeExposed(getAssociatedOrificeType())) {
							return convertToNullResponse();
						}
					}
					
					// Check to see if it's already in use:
					if(!getAssociatedOrificeType().isFree()) {
						return null;
					}
				}
				
				return convertToResponse();
				
				// If it's a no-penetration action, the PenetrationType and the OrificeType both need to be free.
			} else if(getActionType()==SexActionType.PLAYER_REQUIRES_NO_PENETRATION || getActionType() == SexActionType.PARTNER_REQUIRES_NO_PENETRATION) {
				
				if(getAssociatedPenetrationType()!=null) {
					if(!getAssociatedPenetrationType().isFree())
						return convertToNullResponse();
				}
					
				if(getAssociatedOrificeType()!=null) {
					if(!getAssociatedOrificeType().isFree())
						return convertToNullResponse();
				}
				
				return convertToResponse();
				
			// The PenetrationType needs to be penetrating the OrificeType to unlock this action.
			} else {
				if(getAssociatedPenetrationType()!=null && getAssociatedOrificeType()!=null) {
					if(Sex.getPenetrationTypeInOrifice(getAssociatedOrificeType()) != getAssociatedPenetrationType()) {
						return null;
					}
				}
				return convertToResponse();
			}
			
		} else {
			return null;
		}
	}
	
	public default Response convertToResponse() {
		return new Response(getActionTitle(), getActionDescription(), Sex.SEX_DIALOGUE,
				getFetishesPlayer(),
				getCorruptionNeeded(),
				null, null, null,
				getAssociatedPenetrationType(), getAssociatedOrificeType(), Sex.getPartner()){
			@Override
			public void effects() {
				if(getActionType()==SexActionType.PLAYER_POSITIONING || getActionType()==SexActionType.PARTNER_POSITIONING) {
					for(PenetrationType pt : PenetrationType.values()) {
						Sex.getOngoingPenetrationMap().remove(pt);
					}
				}
				
				Sex.setSexStarted(true);
				Sex.endSexTurn(SexActionInterface.this);
			}
			@Override
			public boolean isSexPenetrationHighlight() {
				return getActionType()==SexActionType.PLAYER_PENETRATION || getActionType()==SexActionType.PLAYER_STOP_PENETRATION;
			}
			@Override
			public boolean isSexPositioningHighlight() {
				return getActionType()==SexActionType.PLAYER_POSITIONING;
			}
			@Override
			public SexPace getSexPace() {
				return getSexPacePlayer();
			}
			@Override
			public SexActionType getSexActionType() {
				return getActionType();
			}
		};
	}
	
	public default Response convertToNullResponse() {
		if(!getActionType().isPlayerAction()) {
			return null;
		}
		
		if(getActionType()==SexActionType.PLAYER_POSITIONING || getActionType()==SexActionType.PARTNER_POSITIONING) {
			return new Response(getActionTitle(), getActionDescription(), null,
					getFetishesPlayer(),
					getCorruptionNeeded(),
					null, null, null,
					getAssociatedPenetrationType(), getAssociatedOrificeType(), Sex.getPartner()){
				@Override
				public boolean isSexPenetrationHighlight() {
					return getActionType()==SexActionType.PLAYER_PENETRATION || getActionType()==SexActionType.PLAYER_STOP_PENETRATION;
				}
				@Override
				public boolean isSexPositioningHighlight() {
					return getActionType()==SexActionType.PLAYER_POSITIONING;
				}
				@Override
				public SexPace getSexPace() {
					return getSexPacePlayer();
				}
				@Override
				public SexActionType getSexActionType() {
					return getActionType();
				}
				@Override
				public boolean hasRequirements() {
					return true;
				}
				@Override
				public boolean isAvailable(){
					return false;
				}
				@Override
				public boolean isAbleToBypass(){
					return false;
				}
				@Override
				public String getTooltipRequiredList(){
					StringBuilder SB = new StringBuilder();
					
					if(fetishesRequired!=null) {
						for(Fetish f : fetishesRequired){
							if(Main.game.getPlayer().hasFetish(f)) {
								SB.append("</br>"
										+"<span style='color:"+Colour.GENERIC_SEX+";'>Associated Fetish</span>"
										+ " (<span style='color:"+Colour.GENERIC_GOOD+";'>owned</span>): "
										+ Util.capitaliseSentence(f.getName(Main.game.getPlayer())));
								
							} else {
								SB.append("</br>"
										+"<span style='color:"+Colour.GENERIC_SEX+";'>Associated Fetish</span>"
										+ " (<span style='color:"+Colour.GENERIC_BAD+";'>not owned</span>): "
										+ Util.capitaliseSentence(f.getName(Main.game.getPlayer())));
							}
						}
					}
					
					if(corruptionBypass!=null) {
						if(isCorruptionWithinRange()) {
							SB.append("</br>"
									+"<span style='color:"+Colour.GENERIC_ARCANE+";'>Associated Corruption</span>"
									+ " (<span style='color:"+Colour.GENERIC_GOOD+";'>within range</span>): "
									+ Util.capitaliseSentence(corruptionBypass.getName()));
						} else {
							SB.append("</br>"
									+"<span style='color:"+Colour.GENERIC_ARCANE+";'>Associated Corruption</span>"
									+ " (<span style='color:"+Colour.GENERIC_BAD+";'>out of range</span>): "
									+ Util.capitaliseSentence(corruptionBypass.getName()));
						}
					}

					SB.append("</br>"
							+"<span style='color:"+Colour.GENERIC_BAD+";'>Requires no penetration</span>");
					
					return SB.toString();
				}
				@Override
				public int lineHeight(){
					return super.lineHeight()+1;
				}
			};
		}
		
		
		return new Response(getActionTitle(), getActionDescription(), null,
				getFetishesPlayer(),
				getCorruptionNeeded(),
				null, null, null,
				getAssociatedPenetrationType(), getAssociatedOrificeType(), Sex.getPartner()){
			@Override
			public boolean isSexPenetrationHighlight() {
				return getActionType()==SexActionType.PLAYER_PENETRATION || getActionType()==SexActionType.PLAYER_STOP_PENETRATION;
			}
			@Override
			public boolean isSexPositioningHighlight() {
				return getActionType()==SexActionType.PLAYER_POSITIONING;
			}
			@Override
			public SexPace getSexPace() {
				return getSexPacePlayer();
			}
			@Override
			public SexActionType getSexActionType() {
				return getActionType();
			}
		};
	}
	
	public default boolean isPhysicallyPossible() {
		// Things that make *any* actions related to the penetration ***physically impossible***:
		if(getAssociatedPenetrationType() != null) {
			switch(getAssociatedPenetrationType()) {
				case FINGER_PARTNER:
					break;
				case FINGER_PLAYER:
					break;
				case PENIS_PARTNER:
					if(!Sex.getPartner().hasPenis())
						return false;
					break;
				case PENIS_PLAYER:
					if(!Main.game.getPlayer().hasPenis())
						return false;
					break;
				case TAIL_PARTNER:
					if(!Sex.getPartner().getTailType().isPrehensile())
						return false;
					break;
				case TAIL_PLAYER:
					if(!Main.game.getPlayer().getTailType().isPrehensile())
						return false;
					break;
				case TENTACLE_PARTNER:
					break;
				case TENTACLE_PLAYER:
					break;
				case TONGUE_PARTNER:
					break;
				case TONGUE_PLAYER:
					break;
			}
		}
		// Things that make *any* actions related to the orifice ***physically impossible***:
		if(getAssociatedOrificeType() != null) {
			switch(getAssociatedOrificeType()){
				case ANUS_PARTNER:
					break;
				case ANUS_PLAYER:
					break;
				case MOUTH_PARTNER:
					break;
				case MOUTH_PLAYER:
					break;
				case NIPPLE_PARTNER:
					break;
				case NIPPLE_PLAYER:
					break;
				case URETHRA_PARTNER:
					if(!Sex.getPartner().hasPenis())
						return false;
					break;
				case URETHRA_PLAYER:
					if(!Main.game.getPlayer().hasPenis())
						return false;
					break;
				case VAGINA_PARTNER:
					if(!Sex.getPartner().hasVagina())
						return false;
					break;
				case VAGINA_PLAYER:
					if(!Main.game.getPlayer().hasVagina())
						return false;
					break;
			}
		}
		
		return true;
	}
	
	public default List<OrificeType> getPlayerAreasCummedIn() { return null; }

	public default List<OrificeType> getPartnerAreasCummedIn() { return null; }
	
	/**
	 * @return A list of fetishes that affect the partner in this sex action.
	 */
	public default List<Fetish> getFetishesPartner() {
		return null;
	}
	
	public default boolean ignorePlayerCondom() {
		return false;
	}
	
	public default boolean ignorePartnerCondom() {
		return false;
	}
}
