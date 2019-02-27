package com.lilithsthrone.game.sex.sexActions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.CondomFailure;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.SexSlotGeneric;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericActions;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.1
 * @author Innoxia
 */
public interface SexActionInterface {
	
	/**
	 * This is a method to support old sex actions. Do not set the return value of this to anything other than null.
	 * @return
	 */
	public default SexActionLimitation getLimitation() {//TODO remove this
		return null;
	}
	
	
	public abstract SexActionType getActionType();
	
	public abstract String getActionTitle();

	public abstract String getActionDescription();
	
	public abstract String getDescription();

	public CorruptionLevel getCorruptionNeeded();
	
	/**
	 * The keys are areas that belong to the performing character. The values are areas that belong to the targeted character.
	 * @return
	 */
	public Map<SexAreaInterface, SexAreaInterface> getSexAreaInteractions();
	
	public default List<SexAreaOrifice> getPerformingCharacterOrifices() {
		List<SexAreaOrifice> list = new ArrayList<>();
		for(SexAreaInterface sArea : getSexAreaInteractions().keySet()) {
			if(sArea!=null && sArea.isOrifice()) {
				list.add((SexAreaOrifice)sArea);
			}
		}
		return list;
	}
	
	public default List<SexAreaPenetration> getPerformingCharacterPenetrations() {
		List<SexAreaPenetration> list = new ArrayList<>();
		for(SexAreaInterface sArea : getSexAreaInteractions().keySet()) {
			if(sArea!=null && sArea.isPenetration()) {
				list.add((SexAreaPenetration)sArea);
			}
		}
		return list;
	}
	
	public default List<SexAreaInterface> getPerformingCharacterAreas() {
		List<SexAreaInterface> list = new ArrayList<>();
		for(SexAreaInterface sArea : getSexAreaInteractions().keySet()) {
			if(sArea!=null) {
				list.add(sArea);
			}
		}
		return list;
	}
	
	public default List<SexAreaOrifice> getTargetedCharacterOrifices() {
		List<SexAreaOrifice> list = new ArrayList<>();
		for(SexAreaInterface sArea : getSexAreaInteractions().values()) {
			if(sArea!=null && sArea.isOrifice()) {
				list.add((SexAreaOrifice)sArea);
			}
		}
		return list;
	}
	
	public default List<SexAreaPenetration> getTargetedCharacterPenetrations() {
		List<SexAreaPenetration> list = new ArrayList<>();
		for(SexAreaInterface sArea : getSexAreaInteractions().values()) {
			if(sArea!=null && sArea.isPenetration()) {
				list.add((SexAreaPenetration)sArea);
			}
		}
		return list;
	}
	
	public default List<SexAreaInterface> getTargetedCharacterAreas() {
		List<SexAreaInterface> list = new ArrayList<>();
		for(SexAreaInterface sArea : getSexAreaInteractions().values()) {
			if(sArea!=null) {
				list.add(sArea);
			}
		}
		return list;
	}
	
	/**
	 * @return true if any character can lose virginity from this action.
	 */
	public default boolean isTakesVirginity(boolean includeForeplayOrifices) {
		
		boolean penetrationTakesVirginity = false;
		boolean orificeHasVirginity = false;
		for(SexAreaPenetration sArea : this.getPerformingCharacterPenetrations()) {
			if(sArea.isTakesVirginity()) {
				penetrationTakesVirginity = true;
			}
		}
		for(SexAreaOrifice sArea : this.getTargetedCharacterOrifices()) {
			if(sArea.isInternalOrifice() && (includeForeplayOrifices || sArea!=SexAreaOrifice.MOUTH)) {
				orificeHasVirginity = true;
			}
		}
		if(penetrationTakesVirginity && orificeHasVirginity) {
			return true;
		}
		
		for(SexAreaPenetration sArea : this.getTargetedCharacterPenetrations()) {
			if(sArea.isTakesVirginity()) {
				penetrationTakesVirginity = true;
			}
		}
		for(SexAreaOrifice sArea : this.getPerformingCharacterOrifices()) {
			if(sArea.isInternalOrifice() && (includeForeplayOrifices || sArea!=SexAreaOrifice.MOUTH)) {
				orificeHasVirginity = true;
			}
		}
		return penetrationTakesVirginity && orificeHasVirginity;
	}
	
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
	
	public default String baseEffects() {
		
		if(getActionType()==SexActionType.START_ONGOING) {
			for(Entry<SexAreaInterface, SexAreaInterface> entry : getSexAreaInteractions().entrySet()) {
				Sex.applyOngoingAction(
						Sex.getCharacterPerformingAction(),
						entry.getKey(),
						Sex.getCharacterTargetedForSexAction(this),
						entry.getValue());
			}
		}
		
		if(getActionType()==SexActionType.POSITIONING) {
			// For reference, ongoing penetrations are reset in Sex.setSexManager()
			
			if(!Sex.getCharacterPerformingAction().isPlayer()) { // Ban further positioning actions (this is reset when moving from foreplay to main sex, or when orgasming):
				Sex.addCharacterBannedFromPositioning(Sex.getCharacterPerformingAction());
			}
		}
		
		if(getActionType()==SexActionType.STOP_ONGOING) {
			for(Entry<SexAreaInterface, SexAreaInterface> entry : getSexAreaInteractions().entrySet()) {
				if(entry.getKey()!=null) {
					if(entry.getValue()!=null) {
						Sex.stopOngoingAction(
								Sex.getCharacterPerformingAction(),
								entry.getKey(),
								Sex.getCharacterTargetedForSexAction(this),
								entry.getValue());
						
					} else {
						for(SexAreaInterface sArea : Sex.getContactingSexAreas(Sex.getCharacterPerformingAction(), entry.getKey(), Sex.getCharacterTargetedForSexAction(this))) {
							Sex.stopOngoingAction(
									Sex.getCharacterPerformingAction(),
									entry.getKey(),
									Sex.getCharacterTargetedForSexAction(this),
									sArea);
						}
					}
					
				} else {
					if(entry.getValue()!=null) {
						for(SexAreaInterface sArea : Sex.getContactingSexAreas(Sex.getCharacterTargetedForSexAction(this), entry.getValue(), Sex.getCharacterPerformingAction())) {
								Sex.stopOngoingAction(
										Sex.getCharacterPerformingAction(),
										entry.getKey(),
										Sex.getCharacterTargetedForSexAction(this),
										sArea);
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

	/**
	 * These effects are applied after everything else, so it is a safe place to put ongoing action stops or the like.
	 */
	public default void applyEndEffects(){
	}
	
	public default boolean isBaseRequirementsMet() {
		return true;
	}
	
	public default boolean isBasicCoreRequirementsMet() {
		return (this.getSexPace()==null
					|| (this.getSexPace().isDom() == Sex.getSexPace(Sex.getCharacterPerformingAction()).isDom()))
				&& (this.getActionType()!=SexActionType.STOP_ONGOING // Can only stop non-self ongoing penetrations if full control
					|| this.getParticipantType()==SexParticipantType.SELF
					|| Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL)
				&& (Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())!=SexSlotGeneric.MISC_WATCHING // Cannot switch positions as spectator
					|| this.getActionType()!=SexActionType.POSITIONING); 
	}
	
	/**
	 * Used for determining how likely an NPC is to use this action.
	 */
	public default SexActionPriority getPriority() {
		return SexActionPriority.NORMAL;
	}
	
	/**
	 * Defines if this SexAction switches the pace of sex for the performing character.
	 * @return null if no switch.
	 */
	public SexPace getSexPace();
	
	public default boolean endsSex() {
		return false;
	}
	
	/**
	 * @return true If performer is able to free up orifices in order to perform this action.
	 */
	public default boolean isAbleToAccessParts(GameCharacter performer) {
		if(!this.isPhysicallyPossible()) {
			return false;
		}
		boolean canAccessSelfParts = true;
		for(SexAreaOrifice orifice : this.getPerformingCharacterOrifices()) {
			if(!orifice.isFree(performer)) {
				if(Sex.getSexControl(performer)!=SexControl.FULL) { // Doms and full control subs can always free up their own parts.
					for(GameCharacter character : Sex.getCharactersHavingOngoingActionWith(performer, orifice)) {
						if(!character.equals(performer) && Sex.isDom(character)) { // It's a non-self action with a dom:
							canAccessSelfParts = false;
						}
					}
				}
			}
		}
		for(SexAreaPenetration penetration : this.getPerformingCharacterPenetrations()) {
			if(!penetration.isFree(performer)) {
				if(Sex.getSexControl(performer)!=SexControl.FULL) { // Doms and full control subs can always free up their own parts.
					for(GameCharacter character : Sex.getCharactersHavingOngoingActionWith(performer, penetration)) {
						if(!character.equals(performer) && Sex.isDom(character)) { // It's a non-self action with a dom:
							canAccessSelfParts = false;
						}
					}
				}
			}
		}
		boolean canAccessOthersParts = true;
		GameCharacter target = Sex.getCharacterTargetedForSexAction(this);
		for(SexAreaOrifice orifice : this.getTargetedCharacterOrifices()) {
			if(!orifice.isFree(target)) {
				for(GameCharacter character : Sex.getCharactersHavingOngoingActionWith(target, orifice)) {
					if(!character.equals(performer) && !character.equals(target)) { // It's someone else they're interacting with:
						canAccessOthersParts = false;
					}
				}
			}
		}
		for(SexAreaPenetration penetration : this.getTargetedCharacterPenetrations()) {
			if(!penetration.isFree(target)) {
				for(GameCharacter character : Sex.getCharactersHavingOngoingActionWith(target, penetration)) {
					if(!character.equals(performer) && !character.equals(target)) { // It's someone else they're interacting with:
						canAccessOthersParts = false;
					}
				}
			}
		}
		return canAccessSelfParts && canAccessOthersParts;
	}
	
	public default boolean isAddedToAvailableSexActions() {
		return toResponse() != null;
	}
	
	public default Response toResponse() {
		if(isBasicCoreRequirementsMet()
				&& isBaseRequirementsMet()
				&& isPhysicallyPossible()
				&& !isBannedFromSexManager()
				&& !Sex.getPosition().isActionBlocked(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), this)) {

			if(this.getActionType()==SexActionType.POSITIONING && !Sex.isPositionChangingAllowed(Sex.getCharacterPerformingAction())) {
				return null;
			}

			for(Entry<SexAreaInterface, SexAreaInterface> entry : getSexAreaInteractions().entrySet()) {
				if(isForbiddenArea(entry.getKey()) || isForbiddenArea(entry.getValue())) {
					return null;
				}
			}
			
			// Return null if the player doesn't know about the partners penis/vagina
			if(Sex.getCharacterPerformingAction().isPlayer()) { //TODO check
				if(this.getTargetedCharacterPenetrations().contains(SexAreaPenetration.PENIS)
						&& !Sex.getCharacterTargetedForSexAction(this).isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())) {
					return null;
				}
				for(SexAreaOrifice sArea : this.getTargetedCharacterOrifices()) {
					switch(sArea){
						case NIPPLE:
							if(!Sex.getCharacterTargetedForSexAction(this).isAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer())) {
								return null;
							}
							break;
						case URETHRA_PENIS:
							if(!Sex.getCharacterTargetedForSexAction(this).isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())) {
								return null;
							}
							break;
						case URETHRA_VAGINA:
							if(!Sex.getCharacterTargetedForSexAction(this).isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer())) {
								return null;
							}
							break;
						case VAGINA:
							if(!Sex.getCharacterTargetedForSexAction(this).isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer())) {
								return null;
							}
							break;
						default:
							break;
					}
				}
			}
			
			// You can't prepare for orgasms if your partner won't orgasm on the next turn:
			if(Sex.getCharacterPerformingAction().isPlayer()
					&& getActionType() == SexActionType.PREPARE_FOR_PARTNER_ORGASM) {
				if(!Sex.isReadyToOrgasm(Sex.getActivePartner())) {
					return null;
				} else {
					return convertToResponse();
				}
			}
			// Your partner can't prepare for orgasms if you won't orgasm on the next turn:
			if(!Sex.getCharacterPerformingAction().isPlayer()
					&& getActionType() == SexActionType.PREPARE_FOR_PARTNER_ORGASM) {
				if(!Sex.isReadyToOrgasm(Main.game.getPlayer())) {
					return null;
				} else {
					return convertToResponse();
				}
			}

			// You can't resist in scenes that don't allow it or if non-con is disabled:
			if(getSexPace()==SexPace.SUB_RESISTING) {
				if((Sex.isConsensual() && !Sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_NON_CON_SUB)) || !Main.game.isNonConEnabled()) {
					return null;
				}
			}
			
			// If this is a positioning action:
			if(getActionType()==SexActionType.POSITIONING) {
				// If there is size-difference and more than 1 participant, block non-switching with size-difference NPCS:
				if(Sex.isSizeDifference() && Sex.getTotalParticipantCount(false)>2) {
					if(Sex.getCharacterTargetedForSexAction(this).isSizeDifferenceShorterThan(Sex.getCharacterPerformingAction())
							|| Sex.getCharacterTargetedForSexAction(this).isSizeDifferenceTallerThan(Sex.getCharacterPerformingAction())) {
						return convertToNullResponse();
					}
				}
				return convertToResponse();
				
			// If this is a 'stop penetration' action, check to see if all the requirements are met:
			} else if(getActionType()==SexActionType.STOP_ONGOING) {
				if(!this.getSexAreaInteractions().isEmpty()) {
					// If there is no ongoing action involving the targeted character, return null:
					boolean ongoingFound = false;
					//TODO check
					outerloop:
					for(SexAreaInterface sArea : this.getSexAreaInteractions().keySet()) {
						for(SexAreaInterface sAreaTarget : this.getSexAreaInteractions().values()) {
							if(Sex.getContactingSexAreas(Sex.getCharacterPerformingAction(), sArea, Sex.getCharacterTargetedForSexAction(this)).contains(sAreaTarget)) {
								ongoingFound = true;
								break outerloop;
							}
						}
					}
					if(!ongoingFound) {
						return null;
					}

					// The sub stopping penetration actions (not including self-penetration actions) is only available if the sub has equal control:
					if(this.getParticipantType()!=SexParticipantType.SELF) {
						if(Sex.getSexControl(Sex.getCharacterPerformingAction())!=SexControl.FULL) {
							return null;
						}
					}
					
				} else {
					return null;
				}
				
				return convertToResponse();
				
			// If this is a 'start penetration' action, check to see if all the requirements are met:
			} else if(getActionType()==SexActionType.START_ONGOING) {
				
				// Penetration actions (not including self-penetration actions) are only available in consensual sex or if the penetrator is the dom:
				if(!this.getSexAreaInteractions().isEmpty()) {
					if(this.getParticipantType() != SexParticipantType.SELF) { // This is a penetrative action between both partners:
						
						boolean canStartPenetration = Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL
								|| Sex.isDom(Sex.getCharacterPerformingAction()) == Sex.isDom(Sex.getTargetedPartner(Sex.getCharacterPerformingAction()));
						
						if(!canStartPenetration
								&& Sex.getSexPace(Sex.getTargetedPartner(Sex.getCharacterPerformingAction()))!=SexPace.DOM_ROUGH
								&& Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS) {
							if(this.getTargetedCharacterOrifices().isEmpty()) {
								canStartPenetration = true; // Can start submissive penetrations (getting penetrated, not doing the penetrating) when character is a sub with restricted control.
							} else {
								boolean virginityTakingPenetration = false;
								for(SexAreaPenetration pen :this.getPerformingCharacterPenetrations()) {
									if(pen.isTakesVirginity()) {
										virginityTakingPenetration = true;
									}
								}
								canStartPenetration = !virginityTakingPenetration;
							}
						}
						
						if(!canStartPenetration || getSexPace()==SexPace.SUB_RESISTING) {
							return null;
						}
					}
				}
				
				// Make sure OrificeTypes are available:
				for(SexAreaOrifice sArea : this.getPerformingCharacterOrifices()) {
					switch(sArea){
						case NIPPLE:
							if(!Sex.getCharacterPerformingAction().isBreastFuckableNipplePenetration()) {
								return null;
							}
							break;
						case NIPPLE_CROTCH:
							if(!Sex.getCharacterPerformingAction().isBreastCrotchFuckableNipplePenetration()) {
								return null;
							}
							break;
						default:
							break;
					}
					
					// Check for access:
					if(!Sex.getCharacterPerformingAction().isOrificeTypeExposed(sArea)) {
						return convertToNullResponse();
					}
					
					// Check to see if it's already in use:
					if(!sArea.isFree(Sex.getCharacterPerformingAction())) {
						return convertToNullResponse();
					}
				}
				for(SexAreaOrifice sArea : this.getTargetedCharacterOrifices()) {
					switch(sArea){
						case NIPPLE:
							if(!Sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()) {
								return null;
							}
							break;
						case NIPPLE_CROTCH:
							if(!Sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()) {
								return null;
							}
							break;
						default:
							break;
					}
					
					// Check for access:
					if(!Sex.getCharacterTargetedForSexAction(this).isOrificeTypeExposed(sArea)) {
						return convertToNullResponse();
					}
					
					// Check to see if it's already in use:
					if(!sArea.isFree(Sex.getCharacterTargetedForSexAction(this))) {
						return convertToNullResponse();
					}
				}
				
				// Make sure PenetrationTypes is available:
				for(SexAreaPenetration sArea : this.getPerformingCharacterPenetrations()) {
					// Check for access:
					if(!Sex.getCharacterPerformingAction().isPenetrationTypeExposed(sArea)) {
						return convertToNullResponse();
					}
					
					// Check to see if it's already in use:
					if(!sArea.isFree(Sex.getCharacterPerformingAction())) {
						return convertToNullResponse();
					}
				}
				for(SexAreaPenetration sArea : this.getTargetedCharacterPenetrations()) {
					// Check for access:
					if(!Sex.getCharacterTargetedForSexAction(this).isPenetrationTypeExposed(sArea)) {
						return convertToNullResponse();
					}
					
					// Check to see if it's already in use:
					if(!sArea.isFree(Sex.getCharacterTargetedForSexAction(this))) {
						return convertToNullResponse();
					}
				}
				
				return convertToResponse();
				
				
			} else if(getActionType()==SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED) {

				// Check penetrations:
				for(SexAreaPenetration sArea : this.getPerformingCharacterPenetrations()) {
					if(!Sex.getCharacterPerformingAction().isPenetrationTypeExposed(sArea)) {
						return convertToNullResponse();
					}
					if(!sArea.isFree(Sex.getCharacterPerformingAction())) {
						return convertToNullResponse();
					}
				}
				for(SexAreaPenetration sArea : this.getTargetedCharacterPenetrations()) {
					if(!Sex.getCharacterTargetedForSexAction(this).isPenetrationTypeExposed(sArea)) {
						return convertToNullResponse();
					}
					if(!sArea.isFree(Sex.getCharacterTargetedForSexAction(this))) {
						return convertToNullResponse();
					}
				}
				
				// Check orifices:
				for(SexAreaOrifice sArea : this.getPerformingCharacterOrifices()) {
					if(!Sex.getCharacterPerformingAction().isOrificeTypeExposed(sArea)) {
						return convertToNullResponse();
					}
					if(!sArea.isFree(Sex.getCharacterPerformingAction())) {
						return convertToNullResponse();
					}
				}
				for(SexAreaOrifice sArea : this.getTargetedCharacterOrifices()) {
					if(!Sex.getCharacterTargetedForSexAction(this).isOrificeTypeExposed(sArea)) {
						return convertToNullResponse();
					}
					if(!sArea.isFree(Sex.getCharacterTargetedForSexAction(this))) {
						return convertToNullResponse();
					}
				}
				
				return convertToResponse();
				
				
			} else if(getActionType()==SexActionType.REQUIRES_EXPOSED) {

				// Check penetrations:
				for(SexAreaPenetration sArea : this.getPerformingCharacterPenetrations()) {
					if(!Sex.getCharacterPerformingAction().isPenetrationTypeExposed(sArea)) {
						return convertToNullResponse();
					}
				}
				for(SexAreaPenetration sArea : this.getTargetedCharacterPenetrations()) {
					if(!Sex.getCharacterTargetedForSexAction(this).isPenetrationTypeExposed(sArea)) {
						return convertToNullResponse();
					}
				}
				
				// Check orifices:
				for(SexAreaOrifice sArea : this.getPerformingCharacterOrifices()) {
					if(!Sex.getCharacterPerformingAction().isOrificeTypeExposed(sArea)) {
						return convertToNullResponse();
					}
				}
				for(SexAreaOrifice sArea : this.getTargetedCharacterOrifices()) {
					if(!Sex.getCharacterTargetedForSexAction(this).isOrificeTypeExposed(sArea)) {
						return convertToNullResponse();
					}
				}
				
				return convertToResponse();
				
				
			} else if(getActionType()==SexActionType.REQUIRES_NO_PENETRATION) {

				// Check penetrations:
				for(SexAreaPenetration sArea : this.getPerformingCharacterPenetrations()) {
					if(!sArea.isFree(Sex.getCharacterPerformingAction())) {
						return convertToNullResponse();
					}
				}
				for(SexAreaPenetration sArea : this.getTargetedCharacterPenetrations()) {
					if(!sArea.isFree(Sex.getCharacterTargetedForSexAction(this))) {
						return convertToNullResponse();
					}
				}
				
				// Check orifices:
				for(SexAreaOrifice sArea : this.getPerformingCharacterOrifices()) {
					if(!sArea.isFree(Sex.getCharacterPerformingAction())) {
						return convertToNullResponse();
					}
				}
				for(SexAreaOrifice sArea : this.getTargetedCharacterOrifices()) {
					if(!sArea.isFree(Sex.getCharacterTargetedForSexAction(this))) {
						return convertToNullResponse();
					}
				}
				
				return convertToResponse();
				
			
			} else {
				if(!this.getSexAreaInteractions().isEmpty()) {
					boolean ongoingFound = false;
					// TODO check
					for(SexAreaInterface sArea : this.getSexAreaInteractions().keySet()) {
						for(SexAreaInterface sAreaTarget : this.getSexAreaInteractions().values()) {
							if(Sex.getContactingSexAreas(Sex.getCharacterPerformingAction(), sArea, Sex.getCharacterTargetedForSexAction(this)).contains(sAreaTarget)) {
								ongoingFound = true;
								return convertToResponse();
							}
						}
					}
					if(!ongoingFound) {
						return null;
					}
				}
				return convertToResponse();
			}
			
		} else {
			return null;
		}
	}
	
	default boolean isForbiddenArea(SexAreaInterface sArea) {
		if(sArea!=null && sArea.isOrifice()) {
			switch((SexAreaOrifice) sArea){
				case NIPPLE:
					if(!Main.getProperties().hasValue(PropertyValue.nipplePenContent) && this.getActionType()==SexActionType.START_ONGOING) {
						return true;
					}
					break;
				case URETHRA_PENIS:
					if(!Main.getProperties().hasValue(PropertyValue.urethralContent) && this.getActionType()==SexActionType.START_ONGOING) {
						return true;
					}
					break;
				case URETHRA_VAGINA:
					if(!Main.getProperties().hasValue(PropertyValue.urethralContent) && this.getActionType()==SexActionType.START_ONGOING) {
						return true;
					}
					break;
				default:
					break;
			}
		}
		return false;
	}
	
	public default SexActionCategory getCategory() {
		if(this.getSexAreaInteractions().isEmpty()) {
			if(getActionType() == SexActionType.POSITIONING) {
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
			
//			if(getActionDescription()==null) {
//				System.out.println(this.getClass().getName());
//			}
			
			return new Response(
					this.endsSex()
						?getActionTitle()
						:UtilText.parse(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), getActionTitle()),
					this.endsSex()
						?getActionDescription()
						:UtilText.parse(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), getActionDescription()),
					Sex.SEX_DIALOGUE,
					getFetishes(Main.game.getPlayer()),
					getCorruptionNeeded(),
					null, null, null,
					Sex.getCharacterPerformingAction(),
					this.getSexAreaInteractions().keySet(),
					Sex.getCharacterTargetedForSexAction(this),
					this.getSexAreaInteractions().values()){
				
				@Override
				public void effects() {
					if(SexActionInterface.this.getSexPace()!=null) {
						Sex.setSexPace(Sex.getCharacterPerformingAction(), (SexActionInterface.this.getSexPace()));
					}
					
					Sex.setSexStarted(true);
					Sex.endSexTurn(SexActionInterface.this);
				}
				@Override
				public boolean isSexPenetrationHighlight() {
					return getActionType()==SexActionType.START_ONGOING || getActionType()==SexActionType.STOP_ONGOING;
				}
				@Override
				public boolean isSexPositioningHighlight() {
					return getActionType()==SexActionType.POSITIONING || SexActionInterface.this.equals(GenericActions.PLAYER_STOP_SEX);
				}
				@Override
				public SexPace getSexPace() {
					return SexActionInterface.this.getSexPace();
				}
				@Override
				public SexActionType getSexActionType() {
					return getActionType();
				}
			};
			
		} else {
			return new ResponseEffectsOnly(
					this.endsSex()
						?getActionTitle()
						:UtilText.parse(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), getActionTitle()),
					this.endsSex()
						?getActionDescription()
						:UtilText.parse(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), getActionDescription())){
				@Override
				public void effects() {
					SexActionInterface.this.applyEffects();
					MainController.updateUI();
					Main.game.updateResponses();
				}
				@Override
				public Colour getHighlightColour() {
					return Colour.BASE_PURPLE_LIGHT;
				}
				@Override
				public SexPace getSexPace() {
					return SexActionInterface.this.getSexPace();
				}
				@Override
				public SexActionType getSexActionType() {
					return getActionType();
				}
			};
		}
	}
	
	public default Response convertToNullResponse() {
		if(!Sex.getCharacterPerformingAction().isPlayer()) {
			return null;
		}
		
		if(getActionType()==SexActionType.POSITIONING) {
			return new Response(
					this.endsSex()
						?getActionTitle()
						:UtilText.parse(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), getActionTitle()),
					this.endsSex()
						?getActionDescription()
						:UtilText.parse(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), getActionDescription()),
					null,
					getFetishes(Main.game.getPlayer()),
					getCorruptionNeeded(),
					null, null, null,
					Sex.getCharacterPerformingAction(),
					this.getSexAreaInteractions().keySet(),
					Sex.getCharacterTargetedForSexAction(this),
					this.getSexAreaInteractions().values()){
				
				@Override
				public boolean isSexPenetrationHighlight() {
					return getActionType()==SexActionType.START_ONGOING || getActionType()==SexActionType.STOP_ONGOING;
				}
				@Override
				public boolean isSexPositioningHighlight() {
					return getActionType()==SexActionType.POSITIONING || SexActionInterface.this.equals(GenericActions.PLAYER_STOP_SEX);
				}
				@Override
				public SexPace getSexPace() {
					return SexActionInterface.this.getSexPace();
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
								SB.append("<br/>"
										+"<span style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Associated Fetish</span>"
										+ " (<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>owned</span>): "
										+ Util.capitaliseSentence(f.getName(Main.game.getPlayer())));
								
							} else {
								SB.append("<br/>"
										+"<span style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Associated Fetish</span>"
										+ " (<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>not owned</span>): "
										+ Util.capitaliseSentence(f.getName(Main.game.getPlayer())));
							}
						}
					}
					
					if(corruptionBypass!=null) {
						if(isCorruptionWithinRange()) {
							SB.append("<br/>"
									+"<span style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Associated Corruption</span>"
									+ " (<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>within range</span>): "
									+ Util.capitaliseSentence(corruptionBypass.getName()));
						} else {
							SB.append("<br/>"
									+"<span style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Associated Corruption</span>"
									+ " (<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>out of range</span>): "
									+ Util.capitaliseSentence(corruptionBypass.getName()));
						}
					}

					if(Sex.isSizeDifference() && Sex.getTotalParticipantCount(false)>2) {
						if(Sex.getCharacterTargetedForSexAction(SexActionInterface.this).isSizeDifferenceShorterThan(Sex.getCharacterPerformingAction())
								|| Sex.getCharacterTargetedForSexAction(SexActionInterface.this).isSizeDifferenceTallerThan(Sex.getCharacterPerformingAction())) {
							SB.append("<br/>"
									+"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Size-difference is blocking swap!</span>");
						}
					}
					
//					SB.append("<br/>"
//							+"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requires no penetration</span>");
					
					return SB.toString();
				}
				@Override
				public int lineHeight(){
					return super.lineHeight()+1;
				}
			};
		}
		
		
		return new Response(
				this.endsSex()
					?getActionTitle()
					:UtilText.parse(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), getActionTitle()),
				this.endsSex()
					?getActionDescription()
					:UtilText.parse(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), getActionDescription()),
				null,
				getFetishes(Main.game.getPlayer()),
				getCorruptionNeeded(),
				null, null, null,
				Sex.getCharacterPerformingAction(),
				this.getSexAreaInteractions().keySet(),
				Sex.getCharacterTargetedForSexAction(this),
				this.getSexAreaInteractions().values()){
			
			@Override
			public boolean isSexPenetrationHighlight() {
				return getActionType()==SexActionType.START_ONGOING || getActionType()==SexActionType.STOP_ONGOING;
			}
			@Override
			public boolean isSexPositioningHighlight() {
				return getActionType()==SexActionType.POSITIONING || SexActionInterface.this.equals(GenericActions.PLAYER_STOP_SEX);
			}
			@Override
			public SexPace getSexPace() {
				return SexActionInterface.this.getSexPace();
			}
			@Override
			public SexActionType getSexActionType() {
				return getActionType();
			}
		};
	}
	
	public default boolean isBannedFromSexManager() {
		for(SexAreaInterface sArea : this.getSexAreaInteractions().keySet()) {
			if (Sex.getSexManager().getAreasBannedMap().get(Sex.getCharacterPerformingAction()) != null
					&& Sex.getSexManager().getAreasBannedMap().get(Sex.getCharacterPerformingAction()).contains(sArea)) {
				if(this.getParticipantType()==SexParticipantType.NORMAL || Sex.getSexManager().isAreasBannedMapAppliedToSelfActions(Sex.getCharacterPerformingAction())) {
					return true;
				}
			}
		}
		for(SexAreaInterface sArea : this.getSexAreaInteractions().values()) {
			if (Sex.getSexManager().getAreasBannedMap().get(Sex.getCharacterTargetedForSexAction(this)) != null
					&& Sex.getSexManager().getAreasBannedMap().get(Sex.getCharacterTargetedForSexAction(this)).contains(sArea)) {
				if(this.getParticipantType()==SexParticipantType.NORMAL || Sex.getSexManager().isAreasBannedMapAppliedToSelfActions(Sex.getCharacterTargetedForSexAction(this))) {
					return true;
				}
			}
		}
		
		return false;
	}

	static List<SexAreaInterface> prohibitedNonBipedFingerSelfAreas = Util.newArrayListOfValues(
			SexAreaPenetration.CLIT,
			SexAreaPenetration.PENIS,
			SexAreaOrifice.ANUS,
			SexAreaOrifice.ASS,
			SexAreaOrifice.URETHRA_PENIS,
			SexAreaOrifice.URETHRA_VAGINA,
			SexAreaOrifice.VAGINA);
	public default boolean isPhysicallyPossible() {
		if(this.getParticipantType()==SexParticipantType.SELF) {
			if(!Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) {
				if(this.getSexAreaInteractions().keySet().contains(SexAreaPenetration.FINGER)) {
					if(!Collections.disjoint(prohibitedNonBipedFingerSelfAreas, this.getSexAreaInteractions().values())) {
						return false;
					}
				}
				if(this.getSexAreaInteractions().keySet().contains(SexAreaPenetration.TAIL)
						|| this.getSexAreaInteractions().keySet().contains(SexAreaPenetration.CLIT)
						|| this.getSexAreaInteractions().keySet().contains(SexAreaPenetration.PENIS)) {
					if(!Collections.disjoint(SexActionPresets.upperHalf, this.getSexAreaInteractions().values())) {
						return false;
					}
				}
			}
			if(!Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedCrotchBoobs()) {
				if(this.getSexAreaInteractions().keySet().contains(SexAreaPenetration.FINGER)) {
					if(this.getSexAreaInteractions().values().contains(SexAreaOrifice.BREAST_CROTCH)
							|| this.getSexAreaInteractions().values().contains(SexAreaOrifice.NIPPLE_CROTCH)) {
						return false;
					}
				}
			}
		}
		
		for(SexAreaInterface sArea : this.getSexAreaInteractions().keySet()) {
			if(!performPhysicallyBlockedCheck(sArea, Sex.getCharacterPerformingAction())) {
				return false;
			}
		}
		for(SexAreaInterface sArea : this.getSexAreaInteractions().values()) {
			if(!performPhysicallyBlockedCheck(sArea, Sex.getCharacterTargetedForSexAction(this))) {
				return false;
			}
		}
		
		return true;
	}
	
	default boolean performPhysicallyBlockedCheck(SexAreaInterface sArea, GameCharacter character) {
		// Things that make *any* actions related to the penetration ***physically impossible***:
		if(sArea != null && sArea.isPenetration()) {
			switch((SexAreaPenetration) sArea) {
				case FINGER:
					break;
				case PENIS:
					if(!character.hasPenis())
						return false;
					break;
				case TAIL:
					if(!character.getTailType().isSuitableForPenetration()) {
						return false;
					}
					break;
				case TENTACLE:
					break;
				case TONGUE:
					break;
				case CLIT:
					if(!character.hasVagina()) {
						return false;
					}
					break;
				case FOOT:
					break;
			}
		}
		// Things that make *any* actions related to the orifice ***physically impossible***:
		if(sArea != null && sArea.isOrifice()) {
			switch((SexAreaOrifice) sArea){
				case ANUS:
				case ASS:
				case MOUTH:
				case NIPPLE:
				case BREAST:
					break;
				case NIPPLE_CROTCH:
				case BREAST_CROTCH:
					if(!character.hasBreastsCrotch()) {
						return false;
					}
					break;
				case URETHRA_PENIS:
					if(!character.hasPenis() || !character.isUrethraFuckable()) {
						return false;
					}
					break;
				case VAGINA:
					if(!character.hasVagina()) {
						return false;
					}
					break;
				case URETHRA_VAGINA:
					if(!character.hasVagina() || !character.isVaginaUrethraFuckable()) {
						return false;
					}
					break;
				case THIGHS: //TODO mermaid/centaur legs
					break;
			}
		}
		return true;
	}
	
	public default List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) { return null; }

	public default List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) { return null; }

	// This is in the SexAction Interface, as it might be necessary in some special actions to override this to prevent condom breaks.
	public default CondomFailure getCondomFailure(GameCharacter condomWearer, GameCharacter cumTarget) {
		AbstractClothing condom = condomWearer.getClothingInSlot(InventorySlot.PENIS);
		if(condom==null || !condom.isCondom()) {
			return CondomFailure.NONE;
		}
		
		int cumQuantity = condomWearer.getPenisRawOrgasmCumQuantity();
		
		for(ItemEffect effect : condom.getEffects()) {
			if(effect.getPrimaryModifier()==TFModifier.CLOTHING_CONDOM) {
				switch(effect.getPotency()) {
					case MINOR_BOOST:
						if(cumQuantity>CumProduction.FIVE_HUGE.getMaximumValue()) {
							return CondomFailure.CUM_OVERLOAD;
						}
						break;
					case BOOST:
						if(cumQuantity>CumProduction.SIX_EXTREME.getMaximumValue()) {
							return CondomFailure.CUM_OVERLOAD;
						}
						break;
					case MAJOR_BOOST:
						break;
					case MAJOR_DRAIN:
					case DRAIN:
					case MINOR_DRAIN:
						return CondomFailure.SABOTAGED;
				}
			}
		}
		
		if(cumQuantity>0 && condomWearer.getCumModifiers().contains(FluidModifier.MINERAL_OIL)) {
			return CondomFailure.MINERAL_OIL_SELF_CUM;
		}
		
		//TODO saliva
		
		if(Sex.getOrificesBeingPenetratedBy(condomWearer, SexAreaPenetration.PENIS, cumTarget).contains(SexAreaOrifice.URETHRA_PENIS)
				&& Sex.getWetAreas(cumTarget).get(SexAreaOrifice.URETHRA_PENIS).get(cumTarget).contains(LubricationType.CUM)
				&& cumTarget.getCumModifiers().contains(FluidModifier.MINERAL_OIL)) {
			return CondomFailure.MINERAL_OIL_CUM;
		}
		
		if(Sex.getOrificesBeingPenetratedBy(condomWearer, SexAreaPenetration.PENIS, cumTarget).contains(SexAreaOrifice.VAGINA)
				&& cumTarget.getGirlcum().getFluidModifiers().contains(FluidModifier.MINERAL_OIL)) {
			return CondomFailure.MINERAL_OIL_GIRLCUM;
		}
		
		if(Sex.getOrificesBeingPenetratedBy(condomWearer, SexAreaPenetration.PENIS, cumTarget).contains(SexAreaOrifice.NIPPLE)
				&& cumTarget.getBreastRawStoredMilkValue()>0
				&& cumTarget.getMilk().getFluidModifiers().contains(FluidModifier.MINERAL_OIL)) {
			return CondomFailure.MINERAL_OIL_MILK;
		}
		
		return CondomFailure.NONE;
	}
	
	public default List<Fetish> getFetishesFromPenetrationAndOrificeTypes(
			GameCharacter characterPerformingAction,
			SexAreaInterface performingArea,
			GameCharacter characterTarget,
			SexAreaInterface targetedArea,
			boolean characterPerformingActionFetishes) {
		
		SexType type = new SexType(this.getParticipantType(), performingArea, targetedArea);
		
		// Self areas:
		List<Fetish> associatedFetishes = new ArrayList<>(type.getRelatedFetishes(characterPerformingAction, characterTarget, this.getActionType().isPenetratingOption(), this.getActionType()==SexActionType.ORGASM));
		
		// Add opposite fetishes for partner:
		List<Fetish> associatedFetishesPartner = new ArrayList<>(type.getOppositeFetishes(characterPerformingAction, characterTarget, this.getActionType().isPenetratingOption(), this.getActionType()==SexActionType.ORGASM));
		
		if(characterPerformingActionFetishes) {
			return associatedFetishes;
		} else {
			return associatedFetishesPartner;
		}
	}
	
	/**
	 * @return A SexType object which is representative of this SexAction. Will only represent the first area found in both performing and targeted areas, due to SexType limitations.
	 */
	public default SexType getAsSexType() {
		return new SexType(this.getParticipantType(), getPerformingCharacterAreas().isEmpty()?null:getPerformingCharacterAreas().get(0), getTargetedCharacterAreas().isEmpty()?null:getTargetedCharacterAreas().get(0));
	}
}
