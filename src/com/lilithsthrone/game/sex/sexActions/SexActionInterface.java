package com.lilithsthrone.game.sex.sexActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericActions;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.2.7
 * @author Innoxia
 */
public interface SexActionInterface {
	
	/**
	 * This is a method to support old sex actions. Do not set the return value of this to anything other than null.
	 * @return
	 */
	public default SexActionLimitation getLimitation() {
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
	
	/**
	 * @param character The character to check for virginity losses.
	 * @return true if the character can lose virginity from this action.
	 */
	public default boolean isTakesVirginity(GameCharacter character) {
		if(character.equals(Sex.getCharacterPerformingAction())) {
			for(SexAreaPenetration sArea : this.getTargetedCharacterPenetrations()) {
				if(sArea.isTakesVirginity()) {
					return true;
				}
			}
			
		} else if(character.equals(Sex.getCharacterTargetedForSexAction(this))) {
			for(SexAreaPenetration sArea : this.getPerformingCharacterPenetrations()) {
				if(sArea.isTakesVirginity()) {
					return true;
				}
			}
		}
		return false;
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
			// Ongoing penetrations are reset in Sex.setSexManager()
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
	
	public default boolean isBaseRequirementsMet() {
		return true;
	}
	
	public default boolean isBasicCoreRequirementsMet() {
		return (this.getSexPace()==null
					|| (this.getSexPace().isDom() && Sex.getSexPace(Sex.getCharacterPerformingAction()).isDom())
					|| (!this.getSexPace().isDom() && !Sex.getSexPace(Sex.getCharacterPerformingAction()).isDom()))
				&& (this.getActionType()!=SexActionType.STOP_ONGOING // Can only stop if dom or equal control
					|| Sex.getSexPace(Sex.getCharacterPerformingAction()).isDom()
					|| Sex.isSubHasEqualControl())
				&& (Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())!=SexPositionSlot.MISC_WATCHING
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
						&& !Sex.getCharacterTargetedForSexAction(this).getPlayerKnowsAreas().contains(CoverableArea.PENIS)) {
					return null;
				}
				for(SexAreaOrifice sArea : this.getTargetedCharacterOrifices()) {
					switch(sArea){
						case NIPPLE:
							if(!Sex.getCharacterTargetedForSexAction(this).getPlayerKnowsAreas().contains(CoverableArea.NIPPLES)) {
								return null;
							}
							break;
						case URETHRA_PENIS:
							if(!Sex.getCharacterTargetedForSexAction(this).getPlayerKnowsAreas().contains(CoverableArea.PENIS)) {
								return null;
							}
							break;
						case URETHRA_VAGINA:
							if(!Sex.getCharacterTargetedForSexAction(this).getPlayerKnowsAreas().contains(CoverableArea.VAGINA)) {
								return null;
							}
							break;
						case VAGINA:
							if(!Sex.getCharacterTargetedForSexAction(this).getPlayerKnowsAreas().contains(CoverableArea.VAGINA)) {
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
				if(Sex.isConsensual() || !Main.game.isNonConEnabled()) {
					return null;
				}
			}
			
			// If this is a positioning action:
			if(getActionType()==SexActionType.POSITIONING) {
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
						if(!Sex.isSubHasEqualControl() && !Sex.isDom(Sex.getCharacterPerformingAction())) {
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
						if((!Sex.isSubHasEqualControl() && !Sex.isDom(Sex.getCharacterPerformingAction()) && Sex.isDom(Sex.getTargetedPartner(Sex.getCharacterPerformingAction())))
								|| getSexPace()==SexPace.SUB_RESISTING) {
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
					if(getCategory() == SexActionCategory.POSITIONING) {
						Sex.responseCategory = null;
					}
					
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

					SB.append("<br/>"
							+"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requires no penetration</span>");
					
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
	
	public default boolean isPhysicallyPossible() {
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
				case TOES:
					break;
			}
		}
		// Things that make *any* actions related to the orifice ***physically impossible***:
		if(sArea != null && sArea.isOrifice()) {
			switch((SexAreaOrifice) sArea){
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
	
	public default List<SexAreaOrifice> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) { return null; }

	public default List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) { return null; }
	
	public default boolean ignoreCondom(GameCharacter condomWearer) {
		return false;
	}
	
	public default List<Fetish> getFetishesFromPenetrationAndOrificeTypes(
			GameCharacter characterPerformingAction,
			SexAreaInterface performingArea,
			GameCharacter characterTarget,
			SexAreaInterface targetedArea,
			boolean characterPerformingActionFetishes) {
		
		List<Fetish> associatedFetishes = new ArrayList<>();
		List<Fetish> associatedFetishesPartner = new ArrayList<>();

		// Self areas:
		
		if(performingArea!=null && performingArea.isPenetration()) {
			switch((SexAreaPenetration)performingArea) {
				case CLIT:
					associatedFetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
					break;
				case FINGER:
					break;
				case PENIS:
					associatedFetishes.add(Fetish.FETISH_PENIS_GIVING);
					break;
				case TAIL:
					break;
				case TENTACLE:
					break;
				case TOES:
					associatedFetishes.add(Fetish.FETISH_FOOT_GIVING);
					break;
				case TONGUE:
					associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
					break;
			}
		}
		if(performingArea!=null && performingArea.isOrifice()) {
			switch((SexAreaOrifice)performingArea) {
				case ANUS:
					associatedFetishes.add(Fetish.FETISH_ANAL_RECEIVING);
					break;
				case ASS:
					associatedFetishes.add(Fetish.FETISH_ANAL_RECEIVING);
					break;
				case BREAST:
					associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
					break;
				case MOUTH:
					associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
					break;
				case NIPPLE:
					if(characterTarget.getBreastRawStoredMilkValue()>0) {
						associatedFetishes.add(Fetish.FETISH_LACTATION_SELF);
					}
					associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
					break;
				case THIGHS:
					associatedFetishes.add(Fetish.FETISH_STRUTTER);
					break;
				case URETHRA_PENIS:
					associatedFetishes.add(Fetish.FETISH_PENIS_GIVING);
					break;
				case URETHRA_VAGINA:
					associatedFetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
					break;
				case VAGINA:
					if(characterTarget.hasPenisIgnoreDildo()
							&& characterTarget.getPenisRawStoredCumValue()>0
							&& targetedArea==SexAreaPenetration.PENIS
							&& this.getActionType()==SexActionType.ORGASM) {
						associatedFetishes.add(Fetish.FETISH_PREGNANCY);
					}
					associatedFetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
					break;
			}
		}
		
		// Targeted areas:
		
		if(targetedArea!=null && targetedArea.isPenetration()) {
			switch((SexAreaPenetration)targetedArea) {
				case CLIT:
					associatedFetishes.add(Fetish.FETISH_VAGINAL_GIVING);
					break;
				case FINGER:
					break;
				case PENIS:
					associatedFetishes.add(Fetish.FETISH_PENIS_RECEIVING);
					break;
				case TAIL:
					break;
				case TENTACLE:
					break;
				case TOES:
					associatedFetishes.add(Fetish.FETISH_FOOT_RECEIVING);
					break;
				case TONGUE:
					associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
					break;
			}
		}
		if(targetedArea!=null && targetedArea.isOrifice()) {
			switch((SexAreaOrifice)targetedArea) {
				case ANUS:
					associatedFetishes.add(Fetish.FETISH_ANAL_GIVING);
					break;
				case ASS:
					associatedFetishes.add(Fetish.FETISH_ANAL_GIVING);
					break;
				case BREAST:
					associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
					break;
				case MOUTH:
					associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
					break;
				case NIPPLE:
					if(characterTarget.getBreastRawStoredMilkValue()>0) {
						associatedFetishes.add(Fetish.FETISH_LACTATION_OTHERS);
					}
					associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
					break;
				case THIGHS:
					associatedFetishes.add(Fetish.FETISH_LEG_LOVER);
					break;
				case URETHRA_PENIS:
					associatedFetishes.add(Fetish.FETISH_PENIS_RECEIVING);
					break;
				case URETHRA_VAGINA:
					associatedFetishes.add(Fetish.FETISH_VAGINAL_GIVING);
					break;
				case VAGINA:
					if(characterPerformingAction.hasPenisIgnoreDildo() 
							&& characterPerformingAction.getPenisRawStoredCumValue()>0
							&& performingArea==SexAreaPenetration.PENIS
							&& this.getActionType()==SexActionType.ORGASM) {
						associatedFetishes.add(Fetish.FETISH_IMPREGNATION);
					}
					associatedFetishes.add(Fetish.FETISH_VAGINAL_GIVING);
					break;
			}
		}
		
		// Check for masturbation:
		if(!associatedFetishes.contains(Fetish.FETISH_MASTURBATION) && characterPerformingAction.equals(characterTarget)) {
			associatedFetishes.add(Fetish.FETISH_MASTURBATION);
		}
		
		// Add opposite fetishes for partner:
		for(Fetish f : associatedFetishes) {
			switch(f) {
				case FETISH_ANAL_GIVING:
					associatedFetishesPartner.add(Fetish.FETISH_ANAL_RECEIVING);
					break;
				case FETISH_ANAL_RECEIVING:
					associatedFetishesPartner.add(Fetish.FETISH_ANAL_GIVING);
					break;
				case FETISH_BIMBO:
					break;
				case FETISH_BREASTS_OTHERS:
					associatedFetishesPartner.add(Fetish.FETISH_BREASTS_SELF);
					break;
				case FETISH_BREASTS_SELF:
					associatedFetishesPartner.add(Fetish.FETISH_BREASTS_OTHERS);
					break;
				case FETISH_LACTATION_OTHERS:
					associatedFetishesPartner.add(Fetish.FETISH_LACTATION_SELF);
					break;
				case FETISH_LACTATION_SELF:
					associatedFetishesPartner.add(Fetish.FETISH_LACTATION_OTHERS);
					break;
				case FETISH_BREEDER:
					break;
				case FETISH_CROSS_DRESSER:
					break;
				case FETISH_CUM_ADDICT:
					associatedFetishesPartner.add(Fetish.FETISH_CUM_STUD);
					break;
				case FETISH_CUM_STUD:
					associatedFetishesPartner.add(Fetish.FETISH_CUM_ADDICT);
					break;
				case FETISH_DEFLOWERING:
					break;
				case FETISH_DENIAL:
					associatedFetishesPartner.add(Fetish.FETISH_DENIAL_SELF);
					break;
				case FETISH_DENIAL_SELF:
					associatedFetishesPartner.add(Fetish.FETISH_DENIAL);
					break;
				case FETISH_DOMINANT:
					associatedFetishesPartner.add(Fetish.FETISH_SUBMISSIVE);
					break;
				case FETISH_EXHIBITIONIST:
					break;
				case FETISH_IMPREGNATION:
					associatedFetishesPartner.add(Fetish.FETISH_PREGNANCY);
					break;
				case FETISH_INCEST:
					associatedFetishesPartner.add(Fetish.FETISH_INCEST);
					break;
				case FETISH_LEG_LOVER:
					associatedFetishesPartner.add(Fetish.FETISH_STRUTTER);
					break;
				case FETISH_LUSTY_MAIDEN:
					break;
				case FETISH_MASOCHIST:
					associatedFetishesPartner.add(Fetish.FETISH_SADIST);
					break;
				case FETISH_MASTURBATION:
					associatedFetishesPartner.add(Fetish.FETISH_MASTURBATION);
					break;
				case FETISH_NON_CON_DOM:
					associatedFetishesPartner.add(Fetish.FETISH_NON_CON_SUB);
					break;
				case FETISH_NON_CON_SUB:
					associatedFetishesPartner.add(Fetish.FETISH_NON_CON_DOM);
					break;
				case FETISH_ORAL_GIVING:
					associatedFetishesPartner.add(Fetish.FETISH_ORAL_RECEIVING);
					break;
				case FETISH_ORAL_RECEIVING:
					associatedFetishesPartner.add(Fetish.FETISH_ORAL_GIVING);
					break;
				case FETISH_PREGNANCY:
					associatedFetishesPartner.add(Fetish.FETISH_IMPREGNATION);
					break;
				case FETISH_PURE_VIRGIN:
					break;
				case FETISH_SADIST:
					associatedFetishesPartner.add(Fetish.FETISH_MASOCHIST);
					break;
				case FETISH_SADOMASOCHIST:
					break;
				case FETISH_STRUTTER:
					associatedFetishesPartner.add(Fetish.FETISH_LEG_LOVER);
					break;
				case FETISH_SUBMISSIVE:
					associatedFetishesPartner.add(Fetish.FETISH_DOMINANT);
					break;
				case FETISH_SWITCH:
					break;
				case FETISH_TRANSFORMATION_GIVING:
					associatedFetishesPartner.add(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					break;
				case FETISH_TRANSFORMATION_RECEIVING:
					associatedFetishesPartner.add(Fetish.FETISH_TRANSFORMATION_GIVING);
					break;
				case FETISH_VAGINAL_GIVING:
					associatedFetishesPartner.add(Fetish.FETISH_VAGINAL_RECEIVING);
					break;
				case FETISH_VAGINAL_RECEIVING:
					associatedFetishesPartner.add(Fetish.FETISH_VAGINAL_GIVING);
					break;
				case FETISH_VOYEURIST:
					break;
				case FETISH_KINK_GIVING:
					break;
				case FETISH_KINK_RECEIVING:
					break;
				case FETISH_PENIS_GIVING:
					associatedFetishesPartner.add(Fetish.FETISH_PENIS_RECEIVING);
					break;
				case FETISH_PENIS_RECEIVING:
					associatedFetishesPartner.add(Fetish.FETISH_PENIS_GIVING);
					break;
				case FETISH_FOOT_GIVING:
					associatedFetishesPartner.add(Fetish.FETISH_FOOT_RECEIVING);
					break;
				case FETISH_FOOT_RECEIVING:
					associatedFetishesPartner.add(Fetish.FETISH_FOOT_GIVING);
					break;
			}
		}
		
		if(characterPerformingActionFetishes) {
			return associatedFetishes;
		} else {
			return associatedFetishesPartner;
		}
	}
}
