package com.lilithsthrone.game.sex.sexActions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.CondomFailure;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.3.9
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

	public default boolean isPositionSwap() {
		return false;
	}

	public default boolean isSadisticAction() {
		return false;
	}

	public default boolean isLovingAction() {
		return false;
	}

	/**
	 * Default is false, meaning that only actions of SexPace.SUB_RESISTING are available to resisting characters.
	 * <br/>Override to return true if this action should be available to resisting characters.
	 */
	public default boolean isOverrideAvailableDuringResisting() {
		return false;
	}
	
	/**
	 * If the performing character is immobilised, then this action is only available if it's a SexActionType of: SPEECH, SPEECH_WITH_ALTERNATIVE, PREPARE_FOR_PARTNER_ORGASM, or ORGASM.
	 * <br/>ONGOING SexActionTypes are also available, but only so long as the performing areas doesn't include a virginity-taking penetration type.
	 * <br/><b>COMMAND</b> and <b>SLEEP</b> ImmobilisationTypes prevent SexActionType.ONGOING
	 * <br/>If any type returns true for isSilence(), then SexActionType.SPEECH and SexActionType.SPEECH_WITH_ALTERNATIVE are banned
	 * @return
	 */
	public default boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
		if(this.getActionType()==SexActionType.ONGOING) {
			if(types.contains(ImmobilisationType.SLEEP) || types.contains(ImmobilisationType.COMMAND)) {
				return false;
			}
			for(SexAreaInterface sa : this.getPerformingCharacterAreas()) {
				if(sa.isPenetration() && ((SexAreaPenetration)sa).isTakesVirginity()) {
					return false;
				}
			}
			return true;
		}
		boolean anySilences = types.stream().anyMatch(type->type.isSilence());
		return (this.getActionType()==SexActionType.SPEECH && !anySilences)
				|| (this.getActionType()==SexActionType.SPEECH_WITH_ALTERNATIVE && !anySilences)
				|| this.getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM
				|| this.getActionType()==SexActionType.ORGASM;
	}
	
	/**
	 * @return true if the character who's being targeted by this sex action is immobilised of the type 'COMMAND' or 'SLEEP'
	 */
	public default boolean isTargetedCharacterInanimate() {
		GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
		return target!=null
				&& Main.game.isInSex()
				&& Main.sex.isCharacterImmobilised(target)
				&& Main.sex.isCharacterInanimateFromImmobilisation(target);
	}
	
	/**
	 * @return true if you want this sex action to always be shown in the available list of actions, even if it is unavailable (in which case it will be greyed-out).
	 */
	public default boolean isDisplayedAsUnavailable() {
		return false;
	}
	
	public abstract String getActionTitle();

	public abstract String getActionDescription();
	
	public abstract String getDescription();
	
	public default Colour getHighlightColour() {
		return null;
	}
	
	public abstract String getFluidFlavourDescription(GameCharacter performing, GameCharacter receiving);

	public CorruptionLevel getCorruptionNeeded();
	
	/**
	 * Determines if an additional character can join in on the ongoing action.
	 * Only used as a check in SexActionType.START_ADDITIONAL_ONGOING actions.
	 */
	public default Map<String, Boolean> getAdditionalOngoingAvailableMap() {
		if(getActionType()==SexActionType.START_ADDITIONAL_ONGOING) {
			throw new NullPointerException("getAdditionalOngoingAvailableMap() should not be returning null for a SexAction of type 'SexActionType.START_ADDITIONAL_ONGOING'. (Occurring in SexAction '"+getActionTitle()+"')");
		}
		return null;
	}
	
	public default boolean isAdditionalOngoingAvailable() {
		if(getAdditionalOngoingAvailableMap()!=null) {
			for(Boolean b : getAdditionalOngoingAvailableMap().values()) {
				if(!b) {
					return false;
				}
			}
		}
		return true;
	}
	
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

	public default boolean isTakesPerformerVirginity(boolean includeForeplayOrifices, GameCharacter performer, GameCharacter target) {
		if(!isTakesVirginity(includeForeplayOrifices)) {
			return false;
		}
		if(performer.isAssVirgin() && this.getPerformingCharacterAreas().contains(SexAreaOrifice.ANUS)) {
			return true;
		}
		if(includeForeplayOrifices && performer.isFaceVirgin() && this.getPerformingCharacterAreas().contains(SexAreaOrifice.MOUTH)) {
			return true;
		}
		if(performer.isNippleCrotchVirgin() && this.getPerformingCharacterAreas().contains(SexAreaOrifice.NIPPLE_CROTCH)) {
			return true;
		}
		if(performer.isNippleVirgin() && this.getPerformingCharacterAreas().contains(SexAreaOrifice.NIPPLE)) {
			return true;
		}
		if(performer.isPenisVirgin() && this.getPerformingCharacterAreas().contains(SexAreaPenetration.PENIS)) {
			return true;
		}
		if(performer.isUrethraVirgin() && this.getPerformingCharacterAreas().contains(SexAreaOrifice.URETHRA_PENIS)) {
			return true;
		}
		if(performer.isVaginaUrethraVirgin() && this.getPerformingCharacterAreas().contains(SexAreaOrifice.URETHRA_VAGINA)) {
			return true;
		}
		if(performer.isVaginaVirgin() && this.getPerformingCharacterAreas().contains(SexAreaOrifice.VAGINA)) {
			return true;
		}
		return false;
	}

	public default boolean isTakesTargetVirginity(boolean includeForeplayOrifices, GameCharacter performer, GameCharacter target) {
		if(!isTakesVirginity(includeForeplayOrifices)) {
			return false;
		}
		if(target.isAssVirgin() && this.getTargetedCharacterAreas().contains(SexAreaOrifice.ANUS)) {
			return true;
		}
		if(includeForeplayOrifices && target.isFaceVirgin() && this.getTargetedCharacterAreas().contains(SexAreaOrifice.MOUTH)) {
			return true;
		}
		if(target.isNippleCrotchVirgin() && this.getTargetedCharacterAreas().contains(SexAreaOrifice.NIPPLE_CROTCH)) {
			return true;
		}
		if(target.isNippleVirgin() && this.getTargetedCharacterAreas().contains(SexAreaOrifice.NIPPLE)) {
			return true;
		}
		if(target.isPenisVirgin() && this.getTargetedCharacterAreas().contains(SexAreaPenetration.PENIS)) {
			return true;
		}
		if(target.isUrethraVirgin() && this.getTargetedCharacterAreas().contains(SexAreaOrifice.URETHRA_PENIS)) {
			return true;
		}
		if(target.isVaginaUrethraVirgin() && this.getTargetedCharacterAreas().contains(SexAreaOrifice.URETHRA_VAGINA)) {
			return true;
		}
		if(target.isVaginaVirgin() && this.getTargetedCharacterAreas().contains(SexAreaOrifice.VAGINA)) {
			return true;
		}
		return false;
	}
	
	public abstract SexParticipantType getParticipantType();
	
	/**
	 * @return A list of fetishes that affect the character in this sex action.
	 */
	public List<AbstractFetish> getFetishes(GameCharacter characterPerformingAction);

	/**
	 * @return A list of fetishes that affect the target of 'characterPerformingAction' in this sex action.
	 */
	public List<AbstractFetish> getFetishesForTargetedPartner(GameCharacter characterPerformingAction);
	
	// Sex-specific:
	
	public ArousalIncrease getArousalGainSelf();

	public ArousalIncrease getArousalGainTarget();

	/**
	 * Effects to be applied before this SexAction's description is parsed. The returned String is appended before anything else (including penetration stops if this action is a SexActionType.START_ONGOING).
	 * @return A description of the effects. Return an empty String if you don't want anything appended.
	 */
	public default String applyPreParsingEffects() {
		return "";
	}
		
	/**
	 * @return A String to be appended and displayed immediately before the sex action's description.
	 */
	public default String preDescriptionBaseEffects() {
		StringBuilder stopSB = new StringBuilder();
		
		String s = applyPreParsingEffects();
		if(s!=null) {
			stopSB.append(s);
		}
		
		if(getActionType()==SexActionType.START_ONGOING) {
			for(Entry<SexAreaInterface, SexAreaInterface> entry : getSexAreaInteractions().entrySet()) {
				try {
					if(!entry.getKey().isFree(Main.sex.getCharacterPerformingAction())) {
						Map<GameCharacter, Set<SexAreaInterface>> map = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(entry.getKey());
						Entry<GameCharacter, Set<SexAreaInterface>> firstEntry = map.entrySet().iterator().next();
						stopSB.append(Main.sex.stopOngoingAction(
								Main.sex.getCharacterPerformingAction(),
								entry.getKey(),
								firstEntry.getKey(),
								firstEntry.getValue().iterator().next(),
								false));
					}
				} catch(Exception ex) {
					// No first entry in iterator found
				}
				try {
					if(!entry.getValue().isFree(Main.sex.getCharacterTargetedForSexAction(this))) {
						Map<GameCharacter, Set<SexAreaInterface>> map = Main.sex.getOngoingActionsMap(Main.sex.getCharacterTargetedForSexAction(this)).get(entry.getValue());
						Entry<GameCharacter, Set<SexAreaInterface>> firstEntry = map.entrySet().iterator().next();
						stopSB.append(Main.sex.stopOngoingAction(
								firstEntry.getKey(),
								firstEntry.getValue().iterator().next(),
								Main.sex.getCharacterTargetedForSexAction(this),
								entry.getValue(),
								false));
					}
				} catch(Exception ex) {
					// No first entry in iterator found
				}
			}
		}
		
		return stopSB.toString();
	}
	
	public default String baseEffects() {
		if(getActionType()==SexActionType.START_ONGOING || getActionType()==SexActionType.START_ADDITIONAL_ONGOING) {
			for(Entry<SexAreaInterface, SexAreaInterface> entry : getSexAreaInteractions().entrySet()) {
				Main.sex.applyOngoingAction(
						Main.sex.getCharacterPerformingAction(),
						entry.getKey(),
						Main.sex.getCharacterTargetedForSexAction(this),
						entry.getValue(),
						getActionType()!=SexActionType.START_ADDITIONAL_ONGOING);
			}

			if(getPerformingCharacterAreas().stream().anyMatch((area) -> area.equals(SexAreaOrifice.MOUTH))
					|| getPerformingCharacterAreas().stream().anyMatch((area) -> area.equals(SexAreaPenetration.TONGUE))) {
				Main.sex.getCharacterPerformingAction().setAreaKnownByCharacter(CoverableArea.MOUTH, Main.sex.getCharacterTargetedForSexAction(this), true);
				
			} else if(getTargetedCharacterAreas().stream().anyMatch((area) -> area.equals(SexAreaOrifice.MOUTH))
					|| getTargetedCharacterAreas().stream().anyMatch((area) -> area.equals(SexAreaPenetration.TONGUE))) {
				Main.sex.getCharacterTargetedForSexAction(this).setAreaKnownByCharacter(CoverableArea.MOUTH, Main.sex.getCharacterPerformingAction(), true);
			}
		}
		
		if(getActionType()==SexActionType.POSITIONING) {
			// For reference, ongoing penetrations are reset in Main.sex.setSexManager()
			
			if(!Main.sex.getCharacterPerformingAction().isPlayer()) { // Ban further positioning actions (this is reset when moving from foreplay to main sex, or when orgasming):
				Main.sex.addCharacterBannedFromPositioning(Main.sex.getCharacterPerformingAction());
			}
		}
		
		if(getActionType()==SexActionType.STOP_ONGOING) {
			for(Entry<SexAreaInterface, SexAreaInterface> entry : getSexAreaInteractions().entrySet()) {
				if(entry.getKey()!=null) {
					if(entry.getValue()!=null) {
						Main.sex.stopOngoingAction(
								Main.sex.getCharacterPerformingAction(),
								entry.getKey(),
								Main.sex.getCharacterTargetedForSexAction(this),
								entry.getValue());
						
					} else {
						for(SexAreaInterface sArea : Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), entry.getKey(), Main.sex.getCharacterTargetedForSexAction(this))) {
							Main.sex.stopOngoingAction(
									Main.sex.getCharacterPerformingAction(),
									entry.getKey(),
									Main.sex.getCharacterTargetedForSexAction(this),
									sArea);
						}
					}
					
				} else {
					if(entry.getValue()!=null) {
						for(SexAreaInterface sArea : Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), entry.getValue(), Main.sex.getCharacterPerformingAction())) {
								Main.sex.stopOngoingAction(
										Main.sex.getCharacterPerformingAction(),
										entry.getKey(),
										Main.sex.getCharacterTargetedForSexAction(this),
										sArea);
						}
					}
				}
			}
		}
		
		applyEffects();
		
		StringBuilder sb = new StringBuilder();
		GameCharacter characterTargeted = Main.sex.getCharacterTargetedForSexAction(this);
		if(this.isSadisticAction()) {
			if(!characterTargeted.getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() && !characterTargeted.isDoll()) {
				sb.append("<p style='text-align:center'>"
							+ "[style.colourBad([npc2.Name] [npc2.verb(find)] this sadistic action to be a huge turn-off!)]"
							+ characterTargeted.incrementLust(-15, false)
						+"</p>");
			}
		}
		
		if(Main.game.isLipstickMarkingEnabled()) {
			if(Main.sex.getCharacterPerformingAction().isWearingLipstick()
					&& Main.sex.getCharacterPerformingAction().isHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK)
					&& (this.getPerformingCharacterAreas().contains(SexAreaOrifice.MOUTH) || this.getPerformingCharacterAreas().contains(SexAreaPenetration.TONGUE))) {
				for(SexAreaInterface areaTargeted : this.getTargetedCharacterAreas()) {
					sb.append(characterTargeted.addLipstickMarking(Main.sex.getCharacterPerformingAction(), areaTargeted.getRelatedInventorySlot(characterTargeted), Main.sex.getCharacterPerformingAction().getLipstick()));
				}
				Main.sex.addHeavyLipstickUsedCharacter(Main.sex.getCharacterPerformingAction());
			}
			if(characterTargeted.isWearingLipstick()
					&& characterTargeted.isHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK)
					&& (this.getTargetedCharacterAreas().contains(SexAreaOrifice.MOUTH) || this.getTargetedCharacterAreas().contains(SexAreaPenetration.TONGUE))) {
				for(SexAreaInterface areaTargeted : this.getPerformingCharacterAreas()) {
					sb.append(Main.sex.getCharacterPerformingAction().addLipstickMarking(characterTargeted, areaTargeted.getRelatedInventorySlot(Main.sex.getCharacterPerformingAction()), characterTargeted.getLipstick()));
				}
				Main.sex.addHeavyLipstickUsedCharacter(characterTargeted);
			}
		}
		
		// Sleeping wake conditions:
		if(characterTargeted.isAsleep()) {
			// Wake if oral or not in gentle pace
			if(Main.sex.getAllOngoingSexAreas(characterTargeted, SexAreaOrifice.MOUTH).stream().anyMatch(penetration->penetration.isPenetration() && ((SexAreaPenetration)penetration).isTakesVirginity())
					|| (Main.sex.isDom(Main.sex.getCharacterPerformingAction()) && Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.DOM_GENTLE)) {
				Main.sex.addCharacterWoken(Main.sex.getCharacterTargetedForSexAction(this));
			}
		}
		

		sb.append(applyEffectsString());
		
		return sb.toString();
	}

	public default void applyEffects(){
	}
	
	public default String applyEffectsString(){
		return "";
	}

	/**
	 * These effects are applied after everything else, so it is a safe place to put ongoing action stops or the like.
	 * @return A String describing these effects, to be appended to the SexAction description at the very end. Return an empty String if not needed.
	 */
	public default String applyEndEffects(){
		return "";
	}
	
	public default boolean isQuickSexRequirementsMet(GameCharacter performer) {
		return isBaseRequirementsMet();
	}
	
	public default boolean isBaseRequirementsMet() {
		return true;
	}
	
	public default boolean isBasicCoreRequirementsMet() {
		GameCharacter performingCharacter = Main.sex.getCharacterPerformingAction();
		
		if(this.isSadisticAction()
				&& (!Main.getProperties().hasValue(PropertyValue.sadisticSexContent)
						|| !Main.sex.isSadisticActionsAllowed()
						|| !performingCharacter.hasFetish(Fetish.FETISH_SADIST))) {
			return false;
		}
		
		if(this.isLovingAction()) {
			if(!Main.sex.isLovingActionsAllowed()) {
				return false;
			}
			if(!performingCharacter.isPlayer()
					&& (!performingCharacter.getAffectionLevel(Main.sex.getCharacterTargetedForSexAction(this)).isGreaterThan(AffectionLevel.POSITIVE_TWO_LIKE) || performingCharacter.hasFetish(Fetish.FETISH_SADIST))) {
				return false;
			}
		}
		
		if(Main.sex.isCharacterImmobilised(performingCharacter)) {
			if(!isAvailableDuringImmobilisation(Main.sex.getImmobilisationTypes(performingCharacter).keySet())) {
				return false;
			}
		}
		
		boolean analAllowed = Main.game.isAnalContentEnabled() || (!this.getPerformingCharacterOrifices().contains(SexAreaOrifice.ANUS) && !this.getTargetedCharacterOrifices().contains(SexAreaOrifice.ANUS));
		
		boolean footAllowed = true;
		try { // Wrap in try/catch block as some sex actions may make calls to ongoing actions that aren't ongoing yet
			footAllowed = Main.game.isFootContentEnabled()
					|| Collections.disjoint(Util.mergeLists(this.getFetishes(performingCharacter), this.getFetishesForTargetedPartner(performingCharacter)),
						Util.newArrayListOfValues(Fetish.FETISH_FOOT_GIVING, Fetish.FETISH_FOOT_RECEIVING));
		} catch(Exception ex) {
		}
		
		boolean crotchBoobsAllowed = true;
		try { // Wrap in try/catch block as some sex actions may make calls to ongoing actions that aren't ongoing yet
			crotchBoobsAllowed = Main.game.isUdderContentEnabled()
									|| (!this.getTargetedCharacterAreas().contains(SexAreaOrifice.BREAST_CROTCH)
											&& !this.getTargetedCharacterAreas().contains(SexAreaOrifice.NIPPLE_CROTCH)
											&& !this.getPerformingCharacterAreas().contains(SexAreaOrifice.BREAST_CROTCH)
											&& !this.getPerformingCharacterAreas().contains(SexAreaOrifice.NIPPLE_CROTCH));
		} catch(Exception ex) {
		}
		
		boolean underarmAllowed = Main.game.isArmpitContentEnabled() || (!this.getPerformingCharacterOrifices().contains(SexAreaOrifice.ARMPITS) && !this.getTargetedCharacterOrifices().contains(SexAreaOrifice.ARMPITS));
		
		return analAllowed
				&& footAllowed
				&& crotchBoobsAllowed
				&& underarmAllowed
				&& (this.getSexPace()==null
					|| (this.getSexPace().isDom() == Main.sex.getSexPace(performingCharacter).isDom()))
				&& (this.getActionType()!=SexActionType.STOP_ONGOING // Can only stop non-self ongoing penetrations if full control
					|| this.getParticipantType()==SexParticipantType.SELF
					|| Main.sex.getSexControl(performingCharacter)==SexControl.FULL)
				&& (Main.sex.getSexPositionSlot(performingCharacter)!=SexSlotGeneric.MISC_WATCHING // Cannot switch positions as spectator
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
				if(Main.sex.getSexControl(performer)!=SexControl.FULL) { // Doms and full control subs can always free up their own parts.
					for(GameCharacter character : Main.sex.getCharactersHavingOngoingActionWith(performer, orifice)) {
						if(!character.equals(performer) && Main.sex.isDom(character)) { // It's a non-self action with a dom:
							canAccessSelfParts = false;
						}
					}
				}
			}
		}
		for(SexAreaPenetration penetration : this.getPerformingCharacterPenetrations()) {
			if(!penetration.isFree(performer)) {
				if(Main.sex.getSexControl(performer)!=SexControl.FULL) { // Doms and full control subs can always free up their own parts.
					for(GameCharacter character : Main.sex.getCharactersHavingOngoingActionWith(performer, penetration)) {
						if(!character.equals(performer) && Main.sex.isDom(character)) { // It's a non-self action with a dom:
							canAccessSelfParts = false;
						}
					}
				}
			}
		}
		boolean canAccessOthersParts = true;
		GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
		for(SexAreaOrifice orifice : this.getTargetedCharacterOrifices()) {
			if(!orifice.isFree(target)) {
				for(GameCharacter character : Main.sex.getCharactersHavingOngoingActionWith(target, orifice)) {
					if(!character.equals(performer) && !character.equals(target)) { // It's someone else they're interacting with:
						canAccessOthersParts = false;
					}
				}
			}
			if(!target.isAbleToAccessCoverableArea(orifice.getRelatedCoverableArea(target), true)) {
				canAccessOthersParts = false;
			}
		}
		for(SexAreaPenetration penetration : this.getTargetedCharacterPenetrations()) {
			if(!penetration.isFree(target)) {
				for(GameCharacter character : Main.sex.getCharactersHavingOngoingActionWith(target, penetration)) {
					if(!character.equals(performer) && !character.equals(target)) { // It's someone else they're interacting with:
						canAccessOthersParts = false;
					}
				}
			}
			if(!target.isAbleToAccessCoverableArea(penetration.getRelatedCoverableArea(target), true)) {
				canAccessOthersParts = false;
			}
		}
		
		return canAccessSelfParts && canAccessOthersParts;
	}
	
	public default boolean isAddedToAvailableSexActions() {
		return toResponse() != null;
	}
	
	public default boolean isSwitchOngoingActionAvailable() {
		if(
				Main.sex.getCharacterPerformingAction().isPlayer() && //TODO test commenting this out to allow NPCs to switch actions
				Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS.getValue()) {
			List<GameCharacter> ongoingTargetedAreaCharacters = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), this.getTargetedCharacterAreas().get(0));
			List<GameCharacter> ongoingPerformingAreaCharacters = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), this.getPerformingCharacterAreas().get(0));
			
			// If targeted area is having multiple ongoing actions, or non-self actions that do not involve the player do not allow switch:
			if(ongoingTargetedAreaCharacters.size()>1 || ongoingPerformingAreaCharacters.size()>1) {
				return false;
			}
			if(!ongoingTargetedAreaCharacters.isEmpty()
					&& !ongoingTargetedAreaCharacters.contains(Main.sex.getCharacterTargetedForSexAction(this))
					&& !ongoingTargetedAreaCharacters.contains(Main.game.getPlayer())) {
				return false;
			}
//			//TODO needs more testing, and needs checks for tongue<->mouth, nipple<->breast, urethra/clit<->vagina
			// I added this to try and address the very rare issue of:
			// NPCs who are targeting c1 but have an ongoing with c2 will not switch ongoing to c1, repeatedly preparing to do so (stopping handjob) then reverting (starting handjob)	
//			// If NPCs are using ongoing parts in their action preference, do not allow switching to disrupt this
//			if(!Main.sex.getCharacterPerformingAction().isPlayer()) {
//				for(GameCharacter character : Main.sex.getAllParticipants(false)) {
//					if(!character.equals(Main.sex.getCharacterPerformingAction())) {
//						SexType preference = Main.sex.getCharacterPerformingAction().getCurrentSexPreference(character);
//						if(preference!=null && Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), preference.getPerformingSexArea(), preference.getTargetedSexArea()).contains(character)) {
//							System.out.println("x1a "+preference.getPerformingSexArea()+" | "+preference.getTargetedSexArea());
//							System.out.println("x1b "+this.getPerformingCharacterAreas().get(0)+" | "+this.getTargetedCharacterAreas().get(0));
//							if(this.getPerformingCharacterAreas().contains(preference.getPerformingSexArea()) || this.getTargetedCharacterAreas().contains(preference.getTargetedSexArea())) {
//								System.out.println("x2");
//								if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) { // Allow switching from preference if targeted character is not the same as the one who is involved with the preference
//									System.out.println("x3");
//									return false;
//								}
//							}
//						}
//					}
//				}
//			}
			
			try {
				return !Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(this.getPerformingCharacterAreas().get(0)).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(this.getTargetedCharacterAreas().get(0));
			} catch(Exception ex) {
				return true;
			}
		} else {
			return false;
		}
	}
	
	public default Response toResponse() {
		if(isBasicCoreRequirementsMet()
				&& isBaseRequirementsMet()
				&& isPhysicallyPossible()
				&& !isBannedFromSexManager()
				&& !Main.sex.getPosition().isActionBlocked(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), this)) {

			// Forbid self actions if control is limited to NONE:
			if(this.getParticipantType()==SexParticipantType.SELF
					&& !this.getActionType().isOrgasmOption()
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()<SexControl.SELF.getValue()) {
				return null;
			}
			
			// Forbid non-self actions if control is limited to SELF:
			if(this.getParticipantType()!=SexParticipantType.SELF
					&& (this.getActionType()==SexActionType.REQUIRES_EXPOSED
							 || this.getActionType()==SexActionType.REQUIRES_NO_PENETRATION
							 || this.getActionType()==SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED
							 || this.getActionType()==SexActionType.START_ADDITIONAL_ONGOING
							 || this.getActionType()==SexActionType.START_ONGOING)
					&& !this.getActionType().isOrgasmOption()
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()<=SexControl.SELF.getValue()) {
				return null;
			}
			
			if(this.getActionType()==SexActionType.POSITIONING
					&& !this.isPositionSwap()
					&& !Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())) {
				return null;
			}

			for(Entry<SexAreaInterface, SexAreaInterface> entry : getSexAreaInteractions().entrySet()) {
				if(isForbiddenArea(entry.getKey(), entry.getValue())) {
					return null;
				}
			}
			
			// Return null if the player doesn't know about the partners penis/vagina
			if(Main.sex.getCharacterPerformingAction().isPlayer()) {
				for(SexAreaOrifice sArea : this.getTargetedCharacterOrifices()) {
					switch(sArea){
						case NIPPLE:
							if(!Main.sex.getCharacterTargetedForSexAction(this).isAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer())) {
								return null;
							}
							break;
						case URETHRA_PENIS:
							if(!Main.sex.getCharacterTargetedForSexAction(this).isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())) {
								return null;
							}
							break;
						case URETHRA_VAGINA:
							if(!Main.sex.getCharacterTargetedForSexAction(this).isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer())) {
								return null;
							}
							break;
						case VAGINA:
							if(!Main.sex.getCharacterTargetedForSexAction(this).isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer())) {
								return null;
							}
							break;
						case NIPPLE_CROTCH:
							if(!Main.sex.getCharacterTargetedForSexAction(this).isAreaKnownByCharacter(CoverableArea.NIPPLES_CROTCH, Main.game.getPlayer())) {
								return null;
							}
							break;
						case ARMPITS:
						case BREAST_CROTCH:
						case ANUS:
						case ASS:
						case BREAST:
						case MOUTH:
						case THIGHS:
						case SPINNERET:
							break;
					}
				}
				for(SexAreaPenetration sArea : this.getTargetedCharacterPenetrations()) {
					switch(sArea){
						case CLIT:
							if(!Main.sex.getCharacterTargetedForSexAction(this).isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer())) {
								return null;
							}
							break;
						case PENIS:
							if(!Main.sex.getCharacterTargetedForSexAction(this).isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())) {
								return null;
							}
							break;
						case FINGER:
						case FOOT:
						case TAIL:
						case TENTACLE:
						case TONGUE:
							break;
					}
				}
			}
			
			// You can't prepare for orgasms if your partner won't orgasm on the next turn:
			if(Main.sex.getCharacterPerformingAction().isPlayer()
					&& getActionType() == SexActionType.PREPARE_FOR_PARTNER_ORGASM) {
				if(!Main.sex.isReadyToOrgasm(Main.sex.getTargetedPartner(Main.game.getPlayer()))) {
					return null;
				} else {
					return convertToResponse();
				}
			}
			// Can't prepare for orgasms if the target won't orgasm on the next turn or is hidden:
			if(!Main.sex.getCharacterPerformingAction().isPlayer()
					&& getActionType() == SexActionType.PREPARE_FOR_PARTNER_ORGASM) {
				if(!Main.sex.isReadyToOrgasm(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))
					|| Main.sex.getInitialSexManager().isHidden(Main.sex.getCharacterTargetedForSexAction(this))) {
					return null;
				} else {
					return convertToResponse();
				}
			}

			// You can't resist in scenes that don't allow it, if non-con is disabled, or if the performing character is a doll:
			if(getSexPace()==SexPace.SUB_RESISTING) {
				if((Main.sex.isConsensual() && !Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_NON_CON_SUB))
						|| !Main.game.isNonConEnabled()
						|| Main.sex.getCharacterPerformingAction().isDoll()) {
					return null;
				}
			}
			
			// If this is a positioning action:
			if(getActionType()==SexActionType.POSITIONING) {
//				// If there is size-difference and more than 1 participant, block non-switching with size-difference NPCS:
//				if(Main.sex.isSizeDifference() && Main.sex.getTotalParticipantCount(false)>2) {
//					if(Main.sex.getCharacterTargetedForSexAction(this).isSizeDifferenceShorterThan(Main.sex.getCharacterPerformingAction())
//							|| Main.sex.getCharacterTargetedForSexAction(this).isSizeDifferenceTallerThan(Main.sex.getCharacterPerformingAction())) {
//						return convertToNullResponse();
//					}
//				}
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
							if(Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), sArea, Main.sex.getCharacterTargetedForSexAction(this)).contains(sAreaTarget)) {
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
						if(Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())!=SexControl.FULL) {
							return null;
						}
					}
					
				} else {
					return null;
				}
				
				return convertToResponse();
				
			// If this is a 'start penetration' action, check to see if all the requirements are met:
			} else if(getActionType()==SexActionType.START_ONGOING || getActionType()==SexActionType.START_ADDITIONAL_ONGOING) {
				
				// Penetration actions (not including self-penetration actions) are only available in consensual sex or if the penetrator is the dom:
				if(!this.getSexAreaInteractions().isEmpty()) {
					if(this.getParticipantType() != SexParticipantType.SELF) { // This is a penetrative action between both partners:
						
						boolean canStartPenetration = Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
								|| Main.sex.isDom(Main.sex.getCharacterPerformingAction()) == Main.sex.isDom(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()));
						
						if(!canStartPenetration
								&& Main.sex.getSexPace(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))!=SexPace.DOM_ROUGH
								&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS) {
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

				// If this ongoing action is already ongoing, don't show the 'start' action:
				if(getActionType()==SexActionType.START_ONGOING) {
					if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), this.getPerformingCharacterAreas().get(0), this.getTargetedCharacterAreas().get(0)).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
						return null;
					}
				}
				
				// Make sure OrificeTypes are available:
				for(SexAreaOrifice sArea : this.getPerformingCharacterOrifices()) {
					// THis is already checked in isPhysicallyPossible:
//					switch(sArea){
//						case NIPPLE:
//							if(!Main.sex.getCharacterPerformingAction().isBreastFuckableNipplePenetration()) {
//								return null;
//							}
//							break;
//						case NIPPLE_CROTCH:
//							if(!Main.sex.getCharacterPerformingAction().isBreastCrotchFuckableNipplePenetration()) {
//								return null;
//							}
//							break;
//						default:
//							break;
//					}
					
					// Check for access:
					if(!Main.sex.getCharacterPerformingAction().isOrificeTypeExposed(sArea)) {
						return convertToNullResponse();
					}
					
					// Check to see if it's already in use:
					if(getActionType()==SexActionType.START_ONGOING) {
						if(!isSwitchOngoingActionAvailable() && !sArea.isFree(Main.sex.getCharacterPerformingAction())) {
							return convertToNullResponse();
						}
						
					} else { // Check to see if an additional ongoing action is allowed:
						if(!isAdditionalOngoingAvailable()) {
							return convertToNullResponse();
						}
					}
				}
				for(SexAreaOrifice sArea : this.getTargetedCharacterOrifices()) {
					// THis is already checked in isPhysicallyPossible:
//					switch(sArea){
//						case NIPPLE:
//							if(!Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()) {
//								return null;
//							}
//							break;
//						case NIPPLE_CROTCH:
//							if(!Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()) {
//								return null;
//							}
//							break;
//						default:
//							break;
//					}
					
					// Check for access:
					if(!Main.sex.getCharacterTargetedForSexAction(this).isOrificeTypeExposed(sArea)) {
						return convertToNullResponse();
					}
					
					// Check to see if it's already in use:
					if(getActionType()==SexActionType.START_ONGOING) {
						if(!isSwitchOngoingActionAvailable() && !sArea.isFree(Main.sex.getCharacterTargetedForSexAction(this))) {
							return convertToNullResponse();
						}
						
					} else { // Check to see if an additional ongoing action is allowed:
						if(!isAdditionalOngoingAvailable()) {
							return convertToNullResponse();
						}
					}
				}
				
				// Make sure PenetrationTypes is available:
				for(SexAreaPenetration sArea : this.getPerformingCharacterPenetrations()) {
					// Check for access:
					if(!Main.sex.getCharacterPerformingAction().isPenetrationTypeExposed(sArea)) {
						return convertToNullResponse();
					}
					
					// Check to see if it's already in use:
					if(getActionType()==SexActionType.START_ONGOING) {
						if(!isSwitchOngoingActionAvailable() && !sArea.isFree(Main.sex.getCharacterPerformingAction())) {
							return convertToNullResponse();
						}
						
					} else { // Check to see if an additional ongoing action is allowed:
						if(!isAdditionalOngoingAvailable()) {
							return convertToNullResponse();
						}
					}
				}
				for(SexAreaPenetration sArea : this.getTargetedCharacterPenetrations()) {
					// Check for access:
					if(!Main.sex.getCharacterTargetedForSexAction(this).isPenetrationTypeExposed(sArea)) {
						return convertToNullResponse();
					}
					
					// Check to see if it's already in use:
					if(getActionType()==SexActionType.START_ONGOING) {
						if(!isSwitchOngoingActionAvailable() && !sArea.isFree(Main.sex.getCharacterTargetedForSexAction(this))) {
							return convertToNullResponse();
						}
						
					} else { // Check to see if an additional ongoing action is allowed:
						if(!isAdditionalOngoingAvailable()) {
							return convertToNullResponse();
						}
					}
				}
				
				return convertToResponse();
				
				
			} else if(getActionType()==SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED) {
				if(Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.NONE) {
					return null;
				}
				
				// Check penetrations:
				for(SexAreaPenetration sArea : this.getPerformingCharacterPenetrations()) {
					if(!Main.sex.getCharacterPerformingAction().isPenetrationTypeExposed(sArea)) {
						return convertToNullResponse();
					}
					if(!sArea.isFree(Main.sex.getCharacterPerformingAction())) {
						return convertToNullResponse();
					}
				}
				for(SexAreaPenetration sArea : this.getTargetedCharacterPenetrations()) {
					if(!Main.sex.getCharacterTargetedForSexAction(this).isPenetrationTypeExposed(sArea)) {
						return convertToNullResponse();
					}
					if(!sArea.isFree(Main.sex.getCharacterTargetedForSexAction(this))) {
						return convertToNullResponse();
					}
				}
				
				// Check orifices:
				for(SexAreaOrifice sArea : this.getPerformingCharacterOrifices()) {
					if(!Main.sex.getCharacterPerformingAction().isOrificeTypeExposed(sArea)) {
						return convertToNullResponse();
					}
					if(!sArea.isFree(Main.sex.getCharacterPerformingAction())) {
						return convertToNullResponse();
					}
				}
				for(SexAreaOrifice sArea : this.getTargetedCharacterOrifices()) {
					if(!Main.sex.getCharacterTargetedForSexAction(this).isOrificeTypeExposed(sArea)) {
						return convertToNullResponse();
					}
					if(!sArea.isFree(Main.sex.getCharacterTargetedForSexAction(this))) {
						return convertToNullResponse();
					}
				}
				
				return convertToResponse();
				
				
			} else if(getActionType()==SexActionType.REQUIRES_EXPOSED) {
				if(Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.NONE) {
					return null;
				}

				// Check penetrations:
				for(SexAreaPenetration sArea : this.getPerformingCharacterPenetrations()) {
					if(!Main.sex.getCharacterPerformingAction().isPenetrationTypeExposed(sArea)) {
						return convertToNullResponse();
					}
				}
				for(SexAreaPenetration sArea : this.getTargetedCharacterPenetrations()) {
					if(!Main.sex.getCharacterTargetedForSexAction(this).isPenetrationTypeExposed(sArea)) {
						return convertToNullResponse();
					}
				}
				
				// Check orifices:
				for(SexAreaOrifice sArea : this.getPerformingCharacterOrifices()) {
					if(!Main.sex.getCharacterPerformingAction().isOrificeTypeExposed(sArea)) {
						return convertToNullResponse();
					}
				}
				for(SexAreaOrifice sArea : this.getTargetedCharacterOrifices()) {
					if(!Main.sex.getCharacterTargetedForSexAction(this).isOrificeTypeExposed(sArea)) {
						return convertToNullResponse();
					}
				}
				
				return convertToResponse();
				
				
			} else if(getActionType()==SexActionType.REQUIRES_NO_PENETRATION) {
				if(Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.NONE) {
					return null;
				}

				// Check penetrations:
				for(SexAreaPenetration sArea : this.getPerformingCharacterPenetrations()) {
					if(!sArea.isFree(Main.sex.getCharacterPerformingAction())
							|| Main.sex.getCharacterPerformingAction().isCoverableAreaBlockedFromGroping(sArea.getRelatedCoverableArea(Main.sex.getCharacterPerformingAction()))) {
						return convertToNullResponse();
					}
				}
				for(SexAreaPenetration sArea : this.getTargetedCharacterPenetrations()) {
					if(!sArea.isFree(Main.sex.getCharacterTargetedForSexAction(this))
							|| Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaBlockedFromGroping(sArea.getRelatedCoverableArea(Main.sex.getCharacterTargetedForSexAction(this)))) {
						return convertToNullResponse();
					}
				}
				
				// Check orifices:
				for(SexAreaOrifice sArea : this.getPerformingCharacterOrifices()) {
					if(!sArea.isFree(Main.sex.getCharacterPerformingAction())
							|| Main.sex.getCharacterPerformingAction().isCoverableAreaBlockedFromGroping(sArea.getRelatedCoverableArea(Main.sex.getCharacterPerformingAction()))) {
						return convertToNullResponse();
					}
				}
				for(SexAreaOrifice sArea : this.getTargetedCharacterOrifices()) {
					if(!sArea.isFree(Main.sex.getCharacterTargetedForSexAction(this))
							|| Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaBlockedFromGroping(sArea.getRelatedCoverableArea(Main.sex.getCharacterTargetedForSexAction(this)))) {
						return convertToNullResponse();
					}
				}
				
				return convertToResponse();
				
			
			} else if(getActionType()==SexActionType.SPEECH
					|| getActionType()==SexActionType.SPEECH_WITH_ALTERNATIVE) {
				// Check penetrations:
				if(Main.sex.getCharacterPerformingAction().hasPersonalityTrait(PersonalityTrait.MUTE)) {//Sex.isOngoingActionsBlockingSpeech(Main.sex.getCharacterPerformingAction())) {
					return convertToNullResponse();
				}
				
				return convertToResponse();
				
			} else { // ONGOING (and others?):
				if(!this.getSexAreaInteractions().isEmpty()) {
					boolean ongoingFound = false;
					// TODO check
					for(SexAreaInterface sArea : this.getSexAreaInteractions().keySet()) {
						for(SexAreaInterface sAreaTarget : this.getSexAreaInteractions().values()) {
							if(Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), sArea, Main.sex.getCharacterTargetedForSexAction(this)).contains(sAreaTarget)) {
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
			if(this.isDisplayedAsUnavailable()) {
				return convertToNullResponse();
			}
			return null;
		}
	}
	
	default boolean isForbiddenArea(SexAreaInterface performingArea, SexAreaInterface targetedArea) {
		if(targetedArea!=null && targetedArea.isOrifice()) {
			switch((SexAreaOrifice) targetedArea){
				case NIPPLE:
					if(!Main.getProperties().hasValue(PropertyValue.nipplePenContent) && this.getActionType()==SexActionType.START_ONGOING && performingArea.isPenetration() && performingArea!=SexAreaPenetration.TONGUE) {
						return true;
					}
					break;
				case NIPPLE_CROTCH:
					if(!Main.getProperties().hasValue(PropertyValue.nipplePenContent) && this.getActionType()==SexActionType.START_ONGOING && performingArea.isPenetration() && performingArea!=SexAreaPenetration.TONGUE) {
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
		if(performingArea!=null && performingArea.isOrifice()) {
			switch((SexAreaOrifice) performingArea){
				case NIPPLE:
					if(!Main.getProperties().hasValue(PropertyValue.nipplePenContent) && this.getActionType()==SexActionType.START_ONGOING && targetedArea.isPenetration() && targetedArea!=SexAreaPenetration.TONGUE) {
						return true;
					}
					break;
				case NIPPLE_CROTCH:
					if(!Main.getProperties().hasValue(PropertyValue.nipplePenContent) && this.getActionType()==SexActionType.START_ONGOING && targetedArea.isPenetration() && targetedArea!=SexAreaPenetration.TONGUE) {
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
	
	/**
	 * @return A higher number means that this action will be displayed closer to action 1. Default value is 0.
	 */
	public default int getActionRenderingPriority() {
		return 0;
	}
	
	public default SexActionCategory getCategory() {
		if(this.getSexAreaInteractions().isEmpty()) {
			if(getActionType() == SexActionType.POSITIONING
					|| getActionType() == SexActionType.POSITIONING_MENU) {
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

	default String getArousalHitWarning() {
		if(this.isSadisticAction()) {
			if(!Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive()) {
				return "<br/>[style.colourBad(As [npc2.name] doesn't have a positive desire towards the '"+Fetish.FETISH_MASOCHIST.getName(Main.sex.getCharacterTargetedForSexAction(this))+"' fetish, [npc2.she] will find this action to be a huge turn-off!)]";
			} else {
				return "<br/>[style.colourSex(As [npc2.name] has a positive desire towards the '"+Fetish.FETISH_MASOCHIST.getName(Main.sex.getCharacterTargetedForSexAction(this))+"' fetish, [npc2.she] finds such sadistic actions to be a turn-on!)]";
			}
		}
		return "";
	}
	
	default Response convertToResponse() {
		if(getCategory()!=SexActionCategory.CHARACTER_SWITCH
				&& getActionType()!=SexActionType.MISC_NO_TURN_END
				&& getActionType()!=SexActionType.POSITIONING_MENU) {
			
//			if(getActionDescription()==null) {
//				System.out.println(this.getClass().getName());
//			}
			
			return new Response(
					this.endsSex()
						?getActionTitle()
						:UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getActionTitle()),
					this.endsSex()
						?getActionDescription()+getArousalHitWarning()
						:UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getActionDescription()+getArousalHitWarning()),
					Main.sex.SEX_DIALOGUE,
					getFetishes(Main.game.getPlayer()),
					getCorruptionNeeded(),
					null, null, null,
					this.getActionType(),
					Main.sex.getCharacterPerformingAction(),
					this.getSexAreaInteractions().keySet(),
					Main.sex.getCharacterTargetedForSexAction(this),
					this.getSexAreaInteractions().values()){
				
				@Override
				public void effects() {
					if(SexActionInterface.this.getSexPace()!=null) {
						Main.sex.setSexPace(Main.sex.getCharacterPerformingAction(), (SexActionInterface.this.getSexPace()));
					}
					Main.sex.setSexStarted(true);
					Main.sex.endSexTurn(SexActionInterface.this);
				}
				@Override
				public boolean isSexPenetrationHighlight() {
					return getActionType()==SexActionType.START_ONGOING || getActionType()==SexActionType.START_ADDITIONAL_ONGOING;
				}
				@Override
				public boolean isSexPositioningHighlight() {
					return !SexActionInterface.this.isPositionSwap() && (SexActionInterface.this.getActionType()==SexActionType.POSITIONING || (SexActionInterface.this.getActionType()==SexActionType.SPECIAL && SexActionInterface.this.endsSex()));
				}
				@Override
				public Colour getHighlightColour() {
					if(SexActionInterface.this.getHighlightColour()!=null) {
						return SexActionInterface.this.getHighlightColour();
					}
					if(SexActionInterface.this.getActionType()==SexActionType.POSITIONING_MENU) {
						return PresetColour.BASE_INDIGO;
					}
					if(SexActionInterface.this.isPositionSwap() || getCategory()==SexActionCategory.CHARACTER_SWITCH) {
						return PresetColour.BASE_PURPLE_LIGHT;
					}
					if(isSadisticAction()) {
						return PresetColour.BASE_CRIMSON;
					}
					if(isSexPenetrationHighlight()) {
						if(SexActionInterface.this.getPerformingCharacterAreas().stream().anyMatch((area) -> area.isPenetration())) {
							return PresetColour.GENERIC_SEX_AS_DOM;
						} else {
							return PresetColour.GENERIC_SEX;
						}
					}
					if(getActionType()==SexActionType.STOP_ONGOING) {
						return PresetColour.BASE_RED;
					}
					return super.getHighlightColour();
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
				public boolean isSexActionSwitch() {
					if(getActionType()==SexActionType.START_ONGOING) {
						for(SexAreaInterface sArea : SexActionInterface.this.getPerformingCharacterAreas()) {
							if(!sArea.isFree(Main.sex.getCharacterPerformingAction())) {
								return isSwitchOngoingActionAvailable();
							}
						}
						for(SexAreaInterface sArea : SexActionInterface.this.getTargetedCharacterAreas()) {
							if(!sArea.isFree(Main.sex.getCharacterTargetedForSexAction(SexActionInterface.this))) {
								return isSwitchOngoingActionAvailable();
							}
						}
					}
					return false;
				}
				@Override
				public Map<String, Boolean> getAdditionalOngoingAvailableMap() {
					return SexActionInterface.this.getAdditionalOngoingAvailableMap();
				}
				@Override
				public boolean isAbleToBypass() {
					if(!Main.game.isBypassSexActionsEnabled()) {
						return false;
					}
					return super.isAbleToBypass();
				}
			};
			
		} else {
			return new ResponseEffectsOnly(
					this.endsSex()
						?getActionTitle()
						:UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getActionTitle()),
					this.endsSex()
						?getActionDescription()+getArousalHitWarning()
						:UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getActionDescription()+getArousalHitWarning())){
				@Override
				public void effects() {
					SexActionInterface.this.applyEffects();
					MainController.updateUI();
					Main.game.updateResponses();
				}
				@Override
				public boolean isSexPositioningHighlight() {
					return !SexActionInterface.this.isPositionSwap() && SexActionInterface.this.getActionType()==SexActionType.POSITIONING;
				}
				@Override
				public Colour getHighlightColour() {
					if(SexActionInterface.this.getHighlightColour()!=null) {
						return SexActionInterface.this.getHighlightColour();
					}
					if(SexActionInterface.this.getActionType()==SexActionType.POSITIONING_MENU) {
						return PresetColour.BASE_INDIGO;
					}
					if(SexActionInterface.this.isPositionSwap() || getCategory()==SexActionCategory.CHARACTER_SWITCH) {
						return PresetColour.BASE_PURPLE_LIGHT;
					}
					if(isSadisticAction()) {
						return PresetColour.BASE_CRIMSON;
					}
					return super.getHighlightColour();
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
				public boolean isSexActionSwitch() {
					if(getActionType()==SexActionType.START_ONGOING) {
						for(SexAreaInterface sArea : SexActionInterface.this.getPerformingCharacterAreas()) {
							if(!sArea.isFree(Main.sex.getCharacterPerformingAction())) {
								return isSwitchOngoingActionAvailable();
							}
						}
						for(SexAreaInterface sArea : SexActionInterface.this.getTargetedCharacterAreas()) {
							if(!sArea.isFree(Main.sex.getCharacterTargetedForSexAction(SexActionInterface.this))) {
								return isSwitchOngoingActionAvailable();
							}
						}
					}
					return false;
				}
				@Override
				public Map<String, Boolean> getAdditionalOngoingAvailableMap() {
					return SexActionInterface.this.getAdditionalOngoingAvailableMap();
				}
			};
		}
	}
	
	public default Response convertToNullResponse() {
		if(!Main.sex.getCharacterPerformingAction().isPlayer()) {
			return null;
		}
		
		if(getActionType()==SexActionType.POSITIONING) {
			return new Response(
					this.endsSex()
						?getActionTitle()
						:UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getActionTitle()),
					this.endsSex()
						?getActionDescription()+getArousalHitWarning()
						:UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getActionDescription()+getArousalHitWarning()),
					null,
					getFetishes(Main.game.getPlayer()),
					getCorruptionNeeded(),
					null, null, null,
					this.getActionType(),
					Main.sex.getCharacterPerformingAction(),
					this.getSexAreaInteractions().keySet(),
					Main.sex.getCharacterTargetedForSexAction(this),
					this.getSexAreaInteractions().values()){
				
				@Override
				public boolean isSexPenetrationHighlight() {
					return getActionType()==SexActionType.START_ONGOING || getActionType()==SexActionType.START_ADDITIONAL_ONGOING || getActionType()==SexActionType.STOP_ONGOING;
				}
				@Override
				public boolean isSexPositioningHighlight() {
					return !SexActionInterface.this.isPositionSwap() && (getActionType()==SexActionType.POSITIONING || (SexActionInterface.this.getActionType()==SexActionType.SPECIAL && SexActionInterface.this.endsSex()));
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
				public boolean isSexActionSwitch() {
					if(getActionType()==SexActionType.START_ONGOING) {
						for(SexAreaInterface sArea : SexActionInterface.this.getPerformingCharacterAreas()) {
							if(!sArea.isFree(Main.sex.getCharacterPerformingAction())) {
								return isSwitchOngoingActionAvailable();
							}
						}
						for(SexAreaInterface sArea : SexActionInterface.this.getTargetedCharacterAreas()) {
							if(!sArea.isFree(Main.sex.getCharacterTargetedForSexAction(SexActionInterface.this))) {
								return isSwitchOngoingActionAvailable();
							}
						}
					}
					return false;
				}
				@Override
				public Map<String, Boolean> getAdditionalOngoingAvailableMap() {
					return SexActionInterface.this.getAdditionalOngoingAvailableMap();
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
						for(AbstractFetish f : fetishesRequired){
							if(Main.game.getPlayer().hasFetish(f)) {
								SB.append("<br/>"
										+"<span style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>Associated Fetish</span>"
										+ " (<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>owned</span>): "
										+ Util.capitaliseSentence(f.getName(Main.game.getPlayer())));
								
							} else {
								SB.append("<br/>"
										+"<span style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>Associated Fetish</span>"
										+ " (<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>not owned</span>): "
										+ Util.capitaliseSentence(f.getName(Main.game.getPlayer())));
							}
						}
					}
					
					if(corruptionBypass!=null) {
						if(isCorruptionWithinRange()) {
							SB.append("<br/>"
									+"<span style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>Associated Corruption</span>"
									+ " (<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>within range</span>): "
									+ Util.capitaliseSentence(corruptionBypass.getName()));
						} else {
							SB.append("<br/>"
									+"<span style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>Associated Corruption</span>"
									+ " (<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>out of range</span>): "
									+ Util.capitaliseSentence(corruptionBypass.getName()));
						}
					}

					if(Main.sex.isSizeDifference() && Main.sex.getTotalParticipantCount(false)>2) {
						if(Main.sex.getCharacterTargetedForSexAction(SexActionInterface.this).isSizeDifferenceShorterThan(Main.sex.getCharacterPerformingAction())
								|| Main.sex.getCharacterTargetedForSexAction(SexActionInterface.this).isSizeDifferenceTallerThan(Main.sex.getCharacterPerformingAction())) {
							SB.append("<br/>"
									+"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Size-difference is blocking swap!</span>");
						}
					}
					
//					SB.append("<br/>"
//							+"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Requires no penetration</span>");
					
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
					:UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getActionTitle()),
				this.endsSex()
					?getActionDescription()+getArousalHitWarning()
					:UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getActionDescription()+getArousalHitWarning()),
				null,
				getFetishes(Main.game.getPlayer()),
				getCorruptionNeeded(),
				null, null, null,
				this.getActionType(),
				Main.sex.getCharacterPerformingAction(),
				this.getSexAreaInteractions().keySet(),
				Main.sex.getCharacterTargetedForSexAction(this),
				this.getSexAreaInteractions().values()){
			
			@Override
			public boolean isSexPenetrationHighlight() {
				return getActionType()==SexActionType.START_ONGOING || getActionType()==SexActionType.START_ADDITIONAL_ONGOING || getActionType()==SexActionType.STOP_ONGOING;
			}
			@Override
			public boolean isSexPositioningHighlight() {
				return !SexActionInterface.this.isPositionSwap() && (getActionType()==SexActionType.POSITIONING || (SexActionInterface.this.getActionType()==SexActionType.SPECIAL && SexActionInterface.this.endsSex()));
			}
			@Override
			public SexPace getSexPace() {
				return SexActionInterface.this.getSexPace();
			}
			@Override
			public SexActionType getSexActionType() {
				return getActionType();
			}

//			@Override
//			public boolean hasRequirements() {
//				return true;
//			}
			@Override
			public boolean isAvailable(){
				return false;
			}
//			@Override
//			public boolean isAbleToBypass(){
//				return false;
//			}
			
			@Override
			public boolean isSexActionSwitch() {
				if(getActionType()==SexActionType.START_ONGOING) {
					for(SexAreaInterface sArea : SexActionInterface.this.getPerformingCharacterAreas()) {
						if(!sArea.isFree(Main.sex.getCharacterPerformingAction())) {
							return isSwitchOngoingActionAvailable();
						}
					}
					for(SexAreaInterface sArea : SexActionInterface.this.getTargetedCharacterAreas()) {
						if(!sArea.isFree(Main.sex.getCharacterTargetedForSexAction(SexActionInterface.this))) {
							return isSwitchOngoingActionAvailable();
						}
					}
				}
				return false;
			}
			@Override
			public Map<String, Boolean> getAdditionalOngoingAvailableMap() {
				return SexActionInterface.this.getAdditionalOngoingAvailableMap();
			}
		};
	}
	
	public default boolean isBannedFromSexManager() {
		for(SexAreaInterface sArea : this.getSexAreaInteractions().keySet()) {
			if (Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterPerformingAction()) != null
					&& Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterPerformingAction()).contains(sArea)) {
				if(this.getParticipantType()==SexParticipantType.NORMAL || Main.sex.getInitialSexManager().isAreasBannedMapAppliedToSelfActions(Main.sex.getCharacterPerformingAction())) {
					return true;
				}
			}
		}
		for(SexAreaInterface sArea : this.getSexAreaInteractions().values()) {
			if (Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)) != null
					&& Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(sArea)) {
				if(this.getParticipantType()==SexParticipantType.NORMAL || Main.sex.getInitialSexManager().isAreasBannedMapAppliedToSelfActions(Main.sex.getCharacterTargetedForSexAction(this))) {
					return true;
				}
			}
		}

		List<SexType> sexTypesBanned = new ArrayList<>();
		if(Main.sex.getInitialSexManager().getSexTypesBannedMap().get(Main.sex.getCharacterPerformingAction())!=null) {
			sexTypesBanned.addAll(Main.sex.getInitialSexManager().getSexTypesBannedMap().get(Main.sex.getCharacterPerformingAction()));
		}
		if(Main.sex.getInitialSexManager().getSexTypesBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this))!=null) {
			for(SexType st : Main.sex.getInitialSexManager().getSexTypesBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this))) {
				sexTypesBanned.add(st.getReversedSexType());
			}
		}
		
		if(sexTypesBanned.contains(this.getAsSexType())) {
			return true;
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
	
//	/**
//	 * If this is a starting action, one character is tiny and the other is not of small stature, and the action takes virginity, then this is blocked due to impossible size-difference penetration.
//	 */
//	public default boolean isImpossibleSizeDifferencePenetration() {
//		return this.getParticipantType()!=SexParticipantType.SELF
//				&& (this.getActionType()==SexActionType.START_ONGOING || this.getActionType()==SexActionType.START_ADDITIONAL_ONGOING)
//					&& ((Main.sex.getCharacterPerformingAction().getHeight()==Height.NEGATIVE_THREE_MIMIMUM && !Main.sex.getCharacterTargetedForSexAction(this).getHeight().isShortStature())
//						|| (Main.sex.getCharacterTargetedForSexAction(this).getHeight()==Height.NEGATIVE_THREE_MIMIMUM && !Main.sex.getCharacterPerformingAction().getHeight().isShortStature()))
//					&& ((!this.getPerformingCharacterPenetrations().isEmpty() && this.getTargetedCharacterOrifices().stream().anyMatch(orifice -> orifice.isInternalOrifice())
//							|| (!this.getTargetedCharacterPenetrations().isEmpty() && this.getPerformingCharacterOrifices().stream().anyMatch(orifice -> orifice.isInternalOrifice()))));
//	}
	
	public default boolean isPhysicallyPossible() {
		if(this.getParticipantType()==SexParticipantType.SELF) {
			if(Main.sex.getCharacterPerformingAction().isTaur()) {
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
			if(!Main.sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedCrotchBoobs()) {
				if(this.getSexAreaInteractions().keySet().contains(SexAreaPenetration.FINGER)) {
					if(this.getSexAreaInteractions().values().contains(SexAreaOrifice.BREAST_CROTCH)
							|| this.getSexAreaInteractions().values().contains(SexAreaOrifice.NIPPLE_CROTCH)) {
						return false;
					}
				}
			}
		}
		
//		if(isImpossibleSizeDifferencePenetration()) {
//			return false;
//		}
		
		for(SexAreaInterface targetedArea : this.getSexAreaInteractions().keySet()) {
			for(SexAreaInterface interactingWithArea : this.getSexAreaInteractions().values()) {
				if(!performPhysicallyBlockedCheck(targetedArea, Main.sex.getCharacterPerformingAction(), interactingWithArea)) {
					return false;
				}
			}
		}
		for(SexAreaInterface performingArea : this.getSexAreaInteractions().values()) {
			for(SexAreaInterface interactingWithArea : this.getSexAreaInteractions().keySet()) {
				if(!performPhysicallyBlockedCheck(performingArea, Main.sex.getCharacterTargetedForSexAction(this), interactingWithArea)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	default boolean performPhysicallyBlockedCheck(SexAreaInterface performingArea, GameCharacter performingCharacter, SexAreaInterface interactingWithArea) {
		// Things that make *any* actions related to the penetration ***physically impossible***:
		if(performingArea != null && performingArea.isPenetration()) {
			switch((SexAreaPenetration) performingArea) {
				case FINGER:
					if(performingCharacter.isFeral() && !performingCharacter.getFeralAttributes().isFingerActionsAvailable()) {
						return false;
					}
					break;
				case PENIS:
					if(!performingCharacter.hasPenis())
						return false;
					break;
				case TAIL:
					if(performingCharacter.getLegConfiguration()!=LegConfiguration.TAIL_LONG && !performingCharacter.isTailSuitableForPenetration()) {
						return false;
					}
					break;
				case TENTACLE:
					if(!performingCharacter.hasTentacle() || !performingCharacter.getTentacleType().isSuitableForPenetration()) {
						return false;
					}
					break;
				case TONGUE:
					break;
				case CLIT:
					if(!performingCharacter.hasVagina()) {
						return false;
					}
					break;
				case FOOT:
					// IF no legs or feet, cannot use foot actions:
					if(!performingCharacter.hasFeet()) {
						return false;
					}
					break;
			}
		}
		// Things that make *any* actions related to the orifice ***physically impossible***:
		if(performingArea != null && performingArea.isOrifice()) {
			switch((SexAreaOrifice) performingArea){
				case ARMPITS:
				case ANUS:
				case ASS:
				case MOUTH:
					break;
				case NIPPLE:
					if(performingCharacter.isFeral() && !performingCharacter.getFeralAttributes().isBreastsPresent()) {
						return false;
					}
					if(interactingWithArea!=SexAreaPenetration.TONGUE
						&& this.getActionType()==SexActionType.START_ONGOING
						&& !performingCharacter.isBreastFuckableNipplePenetration()) {
						return false;
					}
					break;
				case BREAST:
					if(performingCharacter.isFeral() && !performingCharacter.getFeralAttributes().isBreastsPresent()) {
						return false;
					}
					break;
				case NIPPLE_CROTCH:
					if(!performingCharacter.hasBreastsCrotch() || (interactingWithArea!=SexAreaPenetration.TONGUE && this.getActionType()==SexActionType.START_ONGOING && !performingCharacter.isBreastCrotchFuckableNipplePenetration())) {
						return false;
					}
					break;
				case BREAST_CROTCH:
					if(!performingCharacter.hasBreastsCrotch()) {
						return false;
					}
					break;
				case URETHRA_PENIS:
					if(!performingCharacter.hasPenis() || !performingCharacter.isUrethraFuckable()) {
						return false;
					}
					break;
				case VAGINA:
					if(!performingCharacter.hasVagina()) {
						return false;
					}
					break;
				case URETHRA_VAGINA:
					if(!performingCharacter.hasVagina() || !performingCharacter.isVaginaUrethraFuckable()) {
						return false;
					}
					break;
				case THIGHS:
					if(!performingCharacter.hasLegs() || !performingCharacter.isThighSexAvailable()) {
						return false;
					}
					break;
				case SPINNERET:
					if(!performingCharacter.hasSpinneret()) {
						return false;
					}
					break;
			}
		}
		return true;
	}
	
	public default List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
		return null;
	}

	public default List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
		return null;
	}

	// This is in the SexAction Interface, as it might be necessary in some special actions to override this to prevent condom breaks.
	public default CondomFailure getCondomFailure(GameCharacter condomWearer, GameCharacter cumTarget) {
		AbstractClothing condom = condomWearer.getClothingInSlot(InventorySlot.PENIS);
		if(condom==null || !condom.isCondom(InventorySlot.PENIS)) {
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
		
		if(Main.sex.getOrificesBeingPenetratedBy(condomWearer, SexAreaPenetration.PENIS, cumTarget).contains(SexAreaOrifice.URETHRA_PENIS)
				&& Main.sex.getWetAreas(cumTarget).get(SexAreaOrifice.URETHRA_PENIS).get(cumTarget).contains(LubricationType.CUM)
				&& cumTarget.getCumModifiers().contains(FluidModifier.MINERAL_OIL)) {
			return CondomFailure.MINERAL_OIL_CUM;
		}
		
		if(Main.sex.getOrificesBeingPenetratedBy(condomWearer, SexAreaPenetration.PENIS, cumTarget).contains(SexAreaOrifice.VAGINA)
				&& cumTarget.getGirlcum().getFluidModifiers().contains(FluidModifier.MINERAL_OIL)) {
			return CondomFailure.MINERAL_OIL_GIRLCUM;
		}
		
		if(Main.sex.getOrificesBeingPenetratedBy(condomWearer, SexAreaPenetration.PENIS, cumTarget).contains(SexAreaOrifice.NIPPLE)
				&& cumTarget.getBreastRawStoredMilkValue()>0
				&& cumTarget.getMilk().getFluidModifiers().contains(FluidModifier.MINERAL_OIL)) {
			return CondomFailure.MINERAL_OIL_MILK;
		}
		
		if(Main.sex.getOrificesBeingPenetratedBy(condomWearer, SexAreaPenetration.PENIS, cumTarget).contains(SexAreaOrifice.NIPPLE_CROTCH)
				&& cumTarget.getBreastCrotchRawStoredMilkValue()>0
				&& cumTarget.getMilkCrotch().getFluidModifiers().contains(FluidModifier.MINERAL_OIL)) {
			return CondomFailure.MINERAL_OIL_MILK;
		}

		// Leave egg-laying failure to last, as cum effects are described first in orgasms involving egg-laying, and so they should be handled first.
		if(condomWearer.equals(Main.sex.getCharacterLayingEggs())) {
			return CondomFailure.EGG_LAYING;
		}
		
		return CondomFailure.NONE;
	}
	
	public default List<AbstractFetish> getFetishesFromPenetrationAndOrificeTypes(
			GameCharacter characterPerformingAction,
			SexAreaInterface performingArea,
			GameCharacter characterTarget,
			SexAreaInterface targetedArea,
			boolean characterPerformingActionFetishes) {
		
		SexType type = new SexType(this.getParticipantType(), performingArea, targetedArea);
		
		// Self areas:
		List<AbstractFetish> associatedFetishes = new ArrayList<>(type.getRelatedFetishes(characterPerformingAction, characterTarget, this.getActionType().isPenetratingOption(), this.getActionType()==SexActionType.ORGASM));
		
		// Add opposite fetishes for partner:
		List<AbstractFetish> associatedFetishesPartner = new ArrayList<>(type.getOppositeFetishes(characterPerformingAction, characterTarget, this.getActionType().isPenetratingOption(), this.getActionType()==SexActionType.ORGASM));
		
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
