package com.lilithsthrone.game.sex.sexActions;

import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericActions;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.98
 * @author Innoxia
 */
public interface SexActionInterface {
	
	public abstract SexActionType getActionType();
	
	public abstract String getActionTitle();

	public abstract String getActionDescription();
	
	public abstract String getDescription();

	public CorruptionLevel getCorruptionNeeded();
	
	public default PenetrationType getAssociatedPenetrationType() { return null; }
	
	public default OrificeType getAssociatedOrificeType() { return null; }
	
	public abstract SexParticipantType getParticipantType();
	
	/**
	 * @return A list of fetishes that affect the character in this sex action.
	 */
	public List<Fetish> getFetishes(GameCharacter characterPerformingAction);

	/**
	 * @return A list of fetishes that affect the target of 'characterPerformingAction' in this sex action.
	 */
	public List<Fetish> getFetishesForTargetedPartner(GameCharacter characterPerformingAction);
	
	// Sex-specific:
	
	public ArousalIncrease getArousalGainSelf();

	public ArousalIncrease getArousalGainTarget();
	
	public default boolean isPartnerSelfAction() {
		return this.getParticipantType() == SexParticipantType.SELF && !this.getActionType().isPlayerAction();
	}
	
	public default boolean isPartnerSelfPenetration() {
		return getActionType()==SexActionType.PARTNER_PENETRATION && this.getParticipantType() == SexParticipantType.SELF;
	}
	
	public default String baseEffects() {
		
		if(getActionType()==SexActionType.PLAYER_PENETRATION) {
			if(getAssociatedPenetrationType()!=null && getAssociatedOrificeType()!=null) {
					Sex.applyPenetration(
							this.getParticipantType()!=SexParticipantType.CATCHER?Main.game.getPlayer():Sex.getActivePartner(),
							this.getParticipantType()!=SexParticipantType.PITCHER?Main.game.getPlayer():Sex.getActivePartner(),
							getAssociatedPenetrationType(),
							getAssociatedOrificeType());
			}
		} else if(getActionType() == SexActionType.PARTNER_PENETRATION) {
			if(getAssociatedPenetrationType()!=null && getAssociatedOrificeType()!=null) {
				Sex.applyPenetration(
						this.getParticipantType()!=SexParticipantType.CATCHER?Sex.getActivePartner():Main.game.getPlayer(),
						this.getParticipantType()!=SexParticipantType.PITCHER?Sex.getActivePartner():Main.game.getPlayer(),
						getAssociatedPenetrationType(),
						getAssociatedOrificeType());
			}
		}
		
		
		if(getActionType()==SexActionType.PLAYER_POSITIONING || getActionType() == SexActionType.PARTNER_POSITIONING) { //TODO
			// Ongoing penetrations are reset in Sex.setSexManager()
		}
		
		if(getActionType()==SexActionType.PLAYER_STOP_PENETRATION || getActionType() == SexActionType.PARTNER_STOP_PENETRATION) {
			if(getAssociatedPenetrationType()!=null) {
				if(getAssociatedOrificeType()!=null) {
					if(getActionType()==SexActionType.PLAYER_STOP_PENETRATION) {
						Sex.removePenetration(
								this.getParticipantType()!=SexParticipantType.CATCHER?Main.game.getPlayer():Sex.getActivePartner(),
								this.getParticipantType()!=SexParticipantType.PITCHER?Main.game.getPlayer():Sex.getActivePartner(),
								getAssociatedPenetrationType(),
								getAssociatedOrificeType());
					} else {
						Sex.removePenetration(
								this.getParticipantType()!=SexParticipantType.CATCHER?Sex.getActivePartner():Main.game.getPlayer(),
								this.getParticipantType()!=SexParticipantType.PITCHER?Sex.getActivePartner():Main.game.getPlayer(),
								getAssociatedPenetrationType(),
								getAssociatedOrificeType());
					}
					
				} else {
					if(getActionType()==SexActionType.PLAYER_STOP_PENETRATION) {
						Set<OrificeType> orificesToRemove =  Sex.getOngoingPenetrationMap(this.getParticipantType()!=SexParticipantType.CATCHER?Main.game.getPlayer():Sex.getActivePartner())
								.get(this.getParticipantType()!=SexParticipantType.PITCHER?Main.game.getPlayer():Sex.getActivePartner())
								.get(getAssociatedPenetrationType()); // Remove all penetration if no orifice is specified.
						
						for(OrificeType orifice : orificesToRemove) {
							Sex.removePenetration(
									this.getParticipantType()!=SexParticipantType.CATCHER?Main.game.getPlayer():Sex.getActivePartner(),
											this.getParticipantType()!=SexParticipantType.PITCHER?Main.game.getPlayer():Sex.getActivePartner(),
									getAssociatedPenetrationType(),
									orifice);
						}
					} else {
						Set<OrificeType> orificesToRemove =  Sex.getOngoingPenetrationMap(this.getParticipantType()!=SexParticipantType.CATCHER?Sex.getActivePartner():Main.game.getPlayer())
								.get(this.getParticipantType()!=SexParticipantType.PITCHER?Sex.getActivePartner():Main.game.getPlayer())
								.get(getAssociatedPenetrationType()); // Remove all penetration if no orifice is specified.
						
						for(OrificeType orifice : orificesToRemove) {
							Sex.removePenetration(
									this.getParticipantType()!=SexParticipantType.CATCHER?Sex.getActivePartner():Main.game.getPlayer(),
									this.getParticipantType()!=SexParticipantType.PITCHER?Sex.getActivePartner():Main.game.getPlayer(),
									getAssociatedPenetrationType(),
									orifice);
						}
					}
				}
				
			} else {
				if(getAssociatedOrificeType()!=null) {
					for(GameCharacter penetrator : Sex.getAllParticipants()) {
						for(GameCharacter penetrated : Sex.getAllParticipants()) {
							for(PenetrationType pt : PenetrationType.values()) {
								if(Sex.getOngoingPenetrationMap(penetrator).get(penetrated).containsKey(pt)) {
									Sex.removePenetration(penetrator, penetrated, pt, getAssociatedOrificeType());
								}
							}
						}
					}
					
				}
			}
		}
		
		applyEffects();
		
		return applyEffectsString();
	}

	public default void applyEffects(){
	}
	
	public default String applyEffectsString(){
		return "";
	}
	
	public default boolean isBaseRequirementsMet() { return true; }
	
	/**
	 * Used for determining how likely an NPC is to use this action.
	 */
	public default SexActionPriority getPriority() {
		return SexActionPriority.NORMAL;
	}
	
	/**
	 * Defines if this SexAction switches the pace of sex for the character.
	 * @return null if no switch.
	 */
	public SexPace getSexPace(GameCharacter character);
	
	public default boolean endsSex() {
		return false;
	}
	
	public default boolean isAddedToAvailableSexActions() {
		return toResponse() != null;
	}
	
	public default GameCharacter getPenetratingCharacter() {
		if(this.getAssociatedPenetrationType()!=null) {
			if(this.getParticipantType().isUsingSelfPenetrationType()) {
				if(this.getActionType().isPlayerAction()) {
					return Main.game.getPlayer();
				} else {
					return Sex.getActivePartner();
				}
			} else {
				if(this.getActionType().isPlayerAction()) {
					return Sex.getActivePartner();
				} else {
					return Main.game.getPlayer();
				}
			}
		}
		return null;
	}
	
	public default GameCharacter getOrificeCharacter() {
		if(this.getAssociatedOrificeType()!=null) {
			if(this.getParticipantType().isUsingSelfOrificeType()) {
				if(this.getActionType().isPlayerAction()) {
					return Main.game.getPlayer();
				} else {
					return Sex.getActivePartner();
				}
			} else {
				if(this.getActionType().isPlayerAction()) {
					return Sex.getActivePartner();
				} else {
					return Main.game.getPlayer();
				}
			}
		}
		return null;
	}
	
	public default Response toResponse() {
		if(isBaseRequirementsMet() && isPhysicallyPossible() && !isBannedFromSexManager()) {
			
			if(!this.getParticipantType().isUsingSelfOrificeType()) {
				if(getAssociatedOrificeType()!=null) {
					switch(getAssociatedOrificeType()){ //TODO urethral checks:
						case URETHRA_PENIS:
							if((!Main.getProperties().hasValue(PropertyValue.urethralContent) && getAssociatedPenetrationType()==PenetrationType.PENIS)) {
								return null;
							}
							break;
						case URETHRA_VAGINA:
							if(!Main.getProperties().hasValue(PropertyValue.urethralContent)) {
								return null;
							}
							break;
						case VAGINA:
							if(!Sex.getActivePartner().getPlayerKnowsAreas().contains(CoverableArea.VAGINA)) {
								return null;
							}
							break;
						default:
							break;
					}
				}
			}
			
			// Return null if the player doesn't know about the partners penis/vagina
			if(this.getActionType().isPlayerAction()) {
				if(this.getParticipantType()==SexParticipantType.CATCHER && getAssociatedPenetrationType()==PenetrationType.PENIS && !Sex.getActivePartner().getPlayerKnowsAreas().contains(CoverableArea.PENIS)) {
					return null;
				}
				if(!this.getParticipantType().isUsingSelfOrificeType()) {
					if(getAssociatedOrificeType()!=null) {
						switch(getAssociatedOrificeType()){ //TODO urethral checks:
							case URETHRA_PENIS:
								if(!Sex.getActivePartner().getPlayerKnowsAreas().contains(CoverableArea.PENIS)) {
									return null;
								}
								break;
							case URETHRA_VAGINA:
								if(!Sex.getActivePartner().getPlayerKnowsAreas().contains(CoverableArea.VAGINA)) {
									return null;
								}
								break;
							case VAGINA:
								if(!Sex.getActivePartner().getPlayerKnowsAreas().contains(CoverableArea.VAGINA)) {
									return null;
								}
								break;
							default:
								break;
						}
					}
				}
			}
			
			// You can't prepare for orgasms if your partner won't orgasm on the next turn:
			if(getActionType() == SexActionType.PLAYER_PREPARE_PARTNER_ORGASM) {
				if(!Sex.isPartnerReadyToOrgasm()) {
					return null;
				} else {
					return convertToResponse();
				}
			}
			// Your partner can't prepare for orgasms if you won't orgasm on the next turn:
			if(getActionType() == SexActionType.PARTNER_PREPARE_PLAYER_ORGASM) {
				if(!Sex.isPlayerReadyToOrgasm()) {
					return null;
				} else {
					return convertToResponse();
				}
			}

			// You can't resist in scenes that don't allow it or if non-con is disabled:
			if((getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING || getSexPace(Sex.getActivePartner())==SexPace.SUB_RESISTING)) {
				if(Sex.isConsensual() || !Main.game.isNonConEnabled()) {
					return null;
				}
			}
			
			// If this is a positioning action:
			if(getActionType()==SexActionType.PLAYER_POSITIONING || getActionType() == SexActionType.PARTNER_POSITIONING) {
				return convertToResponse();
				
			// If this is a 'stop penetration' action, check to see if all the requirements are met:
			} else if(getActionType()==SexActionType.PLAYER_STOP_PENETRATION || getActionType() == SexActionType.PARTNER_STOP_PENETRATION) {
				// The sub stopping penetration actions (not including self-penetration actions) is only available if the sub has equal control:
				if(getAssociatedPenetrationType()!=null && getAssociatedOrificeType()!=null) {
					
					GameCharacter currentCharacterPenetrating = Sex.getPenetratingCharacterUsingOrifice(getOrificeCharacter(), getAssociatedOrificeType());
					if(currentCharacterPenetrating!=null && !currentCharacterPenetrating.equals(getPenetratingCharacter())) {
						return null;
					}
					if(!Sex.getCharactersBeingPenetratedBy(getPenetratingCharacter(), getAssociatedPenetrationType()).contains(getOrificeCharacter())) {
						return null;
					}
					
					 // This is a penetrative action between both partners:
					if(this.getParticipantType()!=SexParticipantType.SELF) {
						if(getActionType().isPlayerAction()) { // Player is performing action:
							if(!Sex.isSubHasEqualControl() && !Sex.isDom(Main.game.getPlayer())) {
								return null;
							}
							
						} else { // Partner is performing action:
							if((!Sex.isSubHasEqualControl() && Sex.isDom(Main.game.getPlayer()))) {
								return null;
							}
						}
						
					}
				}
				// Check to see if the penetrationType is actually in the orifice:
				if(getAssociatedPenetrationType()!=null && getAssociatedOrificeType()!=null) {
					if(Sex.getPenetrationTypeInOrifice(getOrificeCharacter(), getAssociatedOrificeType()) != getAssociatedPenetrationType()) {
						return null;
					}
				}
				return convertToResponse();
				
			// If this is a 'start penetration' action, check to see if all the requirements are met:
			} else if(getActionType()==SexActionType.PLAYER_PENETRATION || getActionType() == SexActionType.PARTNER_PENETRATION) {
				
				if(getActionType() == SexActionType.PARTNER_PENETRATION
						&& this.getParticipantType() == SexParticipantType.SELF
						&& getAssociatedOrificeType() == OrificeType.VAGINA
						&& getAssociatedPenetrationType().isTakesVirginity()
						&& (Sex.getActivePartner().hasStatusEffect(StatusEffect.FETISH_PURE_VIRGIN) || Sex.getActivePartner().hasStatusEffect(StatusEffect.FETISH_PURE_VIRGIN_LUSTY_MAIDEN))) {
					return null;
				}
				
				// Penetration actions (not including self-penetration actions) are only available in consensual sex or if the penetrator is the dom:
				if(getAssociatedPenetrationType()!=null && getAssociatedOrificeType()!=null) {
					if(this.getParticipantType() != SexParticipantType.SELF) { // This is a penetrative action between both partners:
						if(getActionType().isPlayerAction()) { // Player is performing action:
							if((!Sex.isSubHasEqualControl() && !Sex.isDom(Main.game.getPlayer())) || getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) {
								return null;
							}
							
						} else { // Partner is performing action:
							if((!Sex.isSubHasEqualControl() && !Sex.isDom(Sex.getActivePartner())) || getSexPace(Sex.getActivePartner())==SexPace.SUB_RESISTING) {
								return null;
							}
						}
					}
				}
				
				// Make sure the OrificeType is available:
				if(getAssociatedOrificeType() != null) {
					switch(getAssociatedOrificeType()){
						case NIPPLE:
							if(!getOrificeCharacter().isBreastFuckableNipplePenetration()) {
								return null;
							}
							break;
						default:
							break;
					}
					
					// Check for access:
					if(!getOrificeCharacter().isOrificeTypeExposed(getAssociatedOrificeType())) {
						return convertToNullResponse();
					}
					
					// Check to see if it's already in use:
					if(!getAssociatedOrificeType().isFree(getOrificeCharacter())) {
						return convertToNullResponse();
					}
				}
				
				// Make sure the PenetrationType is available:
				if(getAssociatedPenetrationType()!=null) {
					// Check for access:
					if(getPenetratingCharacter().isPlayer()) {
						if(!Main.game.getPlayer().isPenetrationTypeExposed(getAssociatedPenetrationType())) {
							return convertToNullResponse();
						}
					} else {
						if(!Sex.getActivePartner().isPenetrationTypeExposed(getAssociatedPenetrationType())) {
							return convertToNullResponse();
						}
					}
					
					// Check to see if it's already in use:
					if(!getAssociatedPenetrationType().isFree(getPenetratingCharacter())) {
						return convertToNullResponse();
					}
				}
				
				return convertToResponse();
				
			// If it's a no-penetration action, the PenetrationType and the OrificeType both need to be exposed and free.
			} else if(getActionType()==SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED || getActionType() == SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED) {
				
				if(getAssociatedPenetrationType()!=null) {
					// Check for access:
					if(!getPenetratingCharacter().isPenetrationTypeExposed(getAssociatedPenetrationType())) {
						return convertToNullResponse();
					}
					
					// Check to see if it's already in use:
					if(!getAssociatedPenetrationType().isFree(getPenetratingCharacter())) {
						return convertToNullResponse();
					}
				}
				
				if(getAssociatedOrificeType() != null) {
					// Check for access:
					if(!getOrificeCharacter().isOrificeTypeExposed(getAssociatedOrificeType())) {
						return convertToNullResponse();
					}
					
					// Check to see if it's already in use:
					if(!getAssociatedOrificeType().isFree(getOrificeCharacter())) {
						return convertToNullResponse();
					}
				}
				
				return convertToResponse();
				
				// If it's a no-penetration action, the PenetrationType and the OrificeType both need to be free.
			} else if(getActionType()==SexActionType.PLAYER_REQUIRES_NO_PENETRATION || getActionType() == SexActionType.PARTNER_REQUIRES_NO_PENETRATION) {
				
				if(getAssociatedPenetrationType()!=null) {
					if(!getAssociatedPenetrationType().isFree(getPenetratingCharacter()))
						return convertToNullResponse();
				}
					
				if(getAssociatedOrificeType()!=null) {
					if(!getAssociatedOrificeType().isFree(getOrificeCharacter()))
						return convertToNullResponse();
				}
				
				return convertToResponse();
				
			// The PenetrationType needs to be penetrating the OrificeType to unlock this action.
			} else {
				if(getAssociatedPenetrationType()!=null && getAssociatedOrificeType()!=null) {
					GameCharacter currentCharacterPenetrating = Sex.getPenetratingCharacterUsingOrifice(getOrificeCharacter(), getAssociatedOrificeType());
					if(currentCharacterPenetrating!=null && !currentCharacterPenetrating.equals(getPenetratingCharacter())) {
						return null;
					}
					if(!Sex.getCharactersBeingPenetratedBy(getPenetratingCharacter(), getAssociatedPenetrationType()).contains(getOrificeCharacter())) {
						return null;
					}
					
					if(Sex.getPenetrationTypeInOrifice(getOrificeCharacter(), getAssociatedOrificeType()) != getAssociatedPenetrationType()
							|| (this.getParticipantType()==SexParticipantType.SELF
										?!this.getPenetratingCharacter().equals(this.getOrificeCharacter())
										:this.getPenetratingCharacter().equals(this.getOrificeCharacter()))) { //TODO
						return null;
					}
				}
				return convertToResponse();
			}
			
		} else {
			return null;
		}
	}
	
	public default SexActionCategory getCategory() {
		if(getAssociatedPenetrationType()==null && getAssociatedOrificeType()==null) {
			if(getActionType() == SexActionType.PLAYER_POSITIONING || getActionType() == SexActionType.PARTNER_POSITIONING) {
				return SexActionCategory.POSITIONING;
			} else {
				return SexActionCategory.MISCELLANEOUS;
			}
			
		} else {
			if(this.getParticipantType()==SexParticipantType.SELF) {
				return SexActionCategory.SELF;
			} else {
				return SexActionCategory.SEX;
			}
		}
	}
	
	public default Response convertToResponse() {
		if(getCategory() != SexActionCategory.CHARACTER_SWITCH) {
			return new Response(getActionTitle(),
					getActionDescription(),
					Sex.SEX_DIALOGUE,
					getFetishes(Main.game.getPlayer()),
					getCorruptionNeeded(),
					null, null, null,
					getPenetratingCharacter(),
					getAssociatedPenetrationType(),
					getOrificeCharacter(),
					getAssociatedOrificeType()){
				
				@Override
				public void effects() {
					if(getCategory() == SexActionCategory.POSITIONING) {
						Sex.responseCategory = null;
					}
					
					if(SexActionInterface.this.getSexPace(Main.game.getPlayer())!=null) {
						Sex.setSexPace(Main.game.getPlayer(), (SexActionInterface.this.getSexPace(Main.game.getPlayer())));
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
					return getActionType()==SexActionType.PLAYER_POSITIONING || SexActionInterface.this.equals(GenericActions.PLAYER_STOP_SEX);
				}
				@Override
				public SexPace getSexPace() {
					return SexActionInterface.this.getSexPace(Main.game.getPlayer());
				}
				@Override
				public SexActionType getSexActionType() {
					return getActionType();
				}
			};
		} else {
			return new ResponseEffectsOnly(getActionTitle(), getActionDescription()){
				@Override
				public void effects() {
					SexActionInterface.this.applyEffects();
					Main.mainController.updateUI();
					Main.game.updateResponses();
				}
				@Override
				public Colour getHighlightColour() {
					return Colour.BASE_PURPLE_LIGHT;
				}
				@Override
				public SexPace getSexPace() {
					return SexActionInterface.this.getSexPace(Main.game.getPlayer());
				}
				@Override
				public SexActionType getSexActionType() {
					return getActionType();
				}
			};
		}
	}
	
	public default Response convertToNullResponse() {
		if(!getActionType().isPlayerAction()) {
			return null;
		}
		
		if(getActionType()==SexActionType.PLAYER_POSITIONING || getActionType()==SexActionType.PARTNER_POSITIONING) {
			return new Response(getActionTitle(), getActionDescription(), null,
					getFetishes(Main.game.getPlayer()),
					getCorruptionNeeded(),
					null, null, null,
					getPenetratingCharacter(), getAssociatedPenetrationType(),
					getOrificeCharacter(), getAssociatedOrificeType()){
				
				@Override
				public boolean isSexPenetrationHighlight() {
					return getActionType()==SexActionType.PLAYER_PENETRATION || getActionType()==SexActionType.PLAYER_STOP_PENETRATION;
				}
				@Override
				public boolean isSexPositioningHighlight() {
					return getActionType()==SexActionType.PLAYER_POSITIONING || SexActionInterface.this.equals(GenericActions.PLAYER_STOP_SEX);
				}
				@Override
				public SexPace getSexPace() {
					return SexActionInterface.this.getSexPace(Main.game.getPlayer());
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
										+"<span style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Associated Fetish</span>"
										+ " (<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>owned</span>): "
										+ Util.capitaliseSentence(f.getName(Main.game.getPlayer())));
								
							} else {
								SB.append("</br>"
										+"<span style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Associated Fetish</span>"
										+ " (<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>not owned</span>): "
										+ Util.capitaliseSentence(f.getName(Main.game.getPlayer())));
							}
						}
					}
					
					if(corruptionBypass!=null) {
						if(isCorruptionWithinRange()) {
							SB.append("</br>"
									+"<span style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Associated Corruption</span>"
									+ " (<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>within range</span>): "
									+ Util.capitaliseSentence(corruptionBypass.getName()));
						} else {
							SB.append("</br>"
									+"<span style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Associated Corruption</span>"
									+ " (<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>out of range</span>): "
									+ Util.capitaliseSentence(corruptionBypass.getName()));
						}
					}

					SB.append("</br>"
							+"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requires no penetration</span>");
					
					return SB.toString();
				}
				@Override
				public int lineHeight(){
					return super.lineHeight()+1;
				}
			};
		}
		
		
		return new Response(getActionTitle(), getActionDescription(), null,
				getFetishes(Main.game.getPlayer()),
				getCorruptionNeeded(),
				null, null, null,
				getPenetratingCharacter(), getAssociatedPenetrationType(),
				getOrificeCharacter(), getAssociatedOrificeType()){
			
			@Override
			public boolean isSexPenetrationHighlight() {
				return getActionType()==SexActionType.PLAYER_PENETRATION || getActionType()==SexActionType.PLAYER_STOP_PENETRATION;
			}
			@Override
			public boolean isSexPositioningHighlight() {
				return getActionType()==SexActionType.PLAYER_POSITIONING || SexActionInterface.this.equals(GenericActions.PLAYER_STOP_SEX);
			}
			@Override
			public SexPace getSexPace() {
				return SexActionInterface.this.getSexPace(Main.game.getPlayer());
			}
			@Override
			public SexActionType getSexActionType() {
				return getActionType();
			}
		};
	}
	
	public default boolean isBannedFromSexManager() {
		if(getAssociatedOrificeType() != null) {
			for(GameCharacter character : Sex.getAllParticipants()) {
				if(Sex.getSexManager().getOrificesBannedMap().get(character)!=null && Sex.getSexManager().getOrificesBannedMap().get(character).contains(getAssociatedOrificeType())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public default boolean isPhysicallyPossible() {
		
		// Things that make *any* actions related to the penetration ***physically impossible***:
		if(getAssociatedPenetrationType() != null) {
			switch(getAssociatedPenetrationType()) {
				case FINGER:
					break;
				case PENIS:
					if(!getPenetratingCharacter().hasPenis())
						return false;
					break;
				case TAIL:
					if(!getPenetratingCharacter().getTailType().isSuitableForPenetration()) {
						return false;
					}
					break;
				case TENTACLE:
					break;
				case TONGUE:
					break;
			}
		}
		// Things that make *any* actions related to the orifice ***physically impossible***:
		if(getAssociatedOrificeType() != null) {
			switch(getAssociatedOrificeType()){
				case ANUS:
					break;
				case ASS:
					break;
				case MOUTH:
					break;
				case NIPPLE:
					break;
				case BREAST:
					break;
				case URETHRA_PENIS:
					if(!getOrificeCharacter().hasPenis() || !getOrificeCharacter().isUrethraFuckable()) {
						return false;
					}
					break;
				case VAGINA:
				case URETHRA_VAGINA:
					if(!getOrificeCharacter().hasVagina() || !getOrificeCharacter().isVaginaUrethraFuckable()) {
						return false;
					}
					break;
				case THIGHS: //TODO mermaid/centaur legs
					break;
			}
		}
		
		return true;
	}
	
	public default List<OrificeType> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) { return null; }

	public default List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) { return null; }
	
	public default boolean ignoreCondom(GameCharacter condomWearer) {
		return false;
	}
}
