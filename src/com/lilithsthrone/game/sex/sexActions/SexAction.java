package com.lilithsthrone.game.sex.sexActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.0
 * @version 0.1.98
 * @author Innoxia
 */
public abstract class SexAction implements SexActionInterface {

	private SexActionType sexActionType;
	
	private ArousalIncrease selfArousalGain;
	private ArousalIncrease targetArousalGain;
	
	private CorruptionLevel minimumCorruptionNeeded;
	private SexAreaPenetration penetrationTypeAccessRequired;
	private SexAreaOrifice orificeTypeAccessRequired;

	private SexParticipantType participantType;
	private SexPace sexPacePlayer, sexPacePartner;
	private Map<GameCharacter, Set<Fetish>> characterFetishes;
	private Map<GameCharacter, Set<Fetish>> characterFetishesForPartner;
	
	public SexAction(
			SexActionType sexActionType,
			ArousalIncrease selfArousalGain,
			ArousalIncrease targetArousalGain,
			CorruptionLevel minimumCorruptionNeeded,
			SexAreaPenetration penetrationTypeAccessRequired,
			SexAreaOrifice orificeTypeAccessRequired,
			SexParticipantType participantType) {
		
		this(sexActionType,
				selfArousalGain,
				targetArousalGain,
				minimumCorruptionNeeded,
				penetrationTypeAccessRequired,
				orificeTypeAccessRequired,
				participantType,
				null,
				null);
	}
	
	public SexAction(
			SexActionType sexActionType,
			ArousalIncrease selfArousalGain,
			ArousalIncrease targetArousalGain,
			CorruptionLevel minimumCorruptionNeeded,
			SexAreaPenetration penetrationTypeAccessRequired,
			SexAreaOrifice orificeTypeAccessRequired,
			SexParticipantType participantType,
			SexPace sexPacePlayer,
			SexPace sexPacePartner) {
		
		this.sexActionType = sexActionType;
		this.selfArousalGain = selfArousalGain;
		this.targetArousalGain = targetArousalGain;
		this.minimumCorruptionNeeded = minimumCorruptionNeeded;
		this.penetrationTypeAccessRequired = penetrationTypeAccessRequired;
		this.orificeTypeAccessRequired = orificeTypeAccessRequired;
		this.participantType = participantType;
		this.sexPacePlayer = sexPacePlayer;
		this.sexPacePartner = sexPacePartner;
	}
	
	public SexAction(SexActionInterface sexActionToCopy) {
		this.sexActionType = sexActionToCopy.getActionType();
		this.selfArousalGain = sexActionToCopy.getArousalGainSelf();
		this.targetArousalGain = sexActionToCopy.getArousalGainTarget();
		this.minimumCorruptionNeeded = sexActionToCopy.getCorruptionNeeded();
		this.penetrationTypeAccessRequired = sexActionToCopy.getAssociatedPenetrationType();
		this.orificeTypeAccessRequired = sexActionToCopy.getAssociatedOrificeType();
		this.participantType = sexActionToCopy.getParticipantType();
		this.sexPacePlayer = sexActionToCopy.getSexPace(Main.game.getPlayer());
		this.sexPacePartner = sexActionToCopy.getSexPace(Sex.getActivePartner());
	}
	
	
	@Override
	public SexPace getSexPace(GameCharacter character){
		if(character!=null && character.isPlayer()) {
			return sexPacePlayer;
		} else {
			return sexPacePartner;
		}
	}
	
	@Override
	public SexAreaPenetration getAssociatedPenetrationType() {
		return penetrationTypeAccessRequired;
	}

	@Override
	public SexAreaOrifice getAssociatedOrificeType() {
		return orificeTypeAccessRequired;
	}

	@Override
	public SexParticipantType getParticipantType() {
		return participantType;
	}
	
	@Override
	public SexActionType getActionType(){
		return sexActionType;
	}

	@Override
	public ArousalIncrease getArousalGainSelf() {
		if(!this.getActionType().isPlayerAction() && Sex.getSexPace(Sex.getActivePartner())==SexPace.SUB_RESISTING) {
			if(Sex.getActivePartner().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
				return ArousalIncrease.FOUR_HIGH;
			} else {
				// If it's an erogenous zone, they gain arousal. If not, arousal gain is 0.
				if((this.getParticipantType().isUsingSelfOrificeType() && this.getAssociatedOrificeType()!=null && this.getAssociatedOrificeType().getBaseArousalWhenPenetrated()>1)
						|| (this.getParticipantType().isUsingSelfPenetrationType() && this.getAssociatedPenetrationType() != null && this.getAssociatedPenetrationType().getBaseArousalWhenPenetrating()>1)) {
					return ArousalIncrease.TWO_LOW;
				}
				return ArousalIncrease.ZERO_NONE;
			}
		}
		
		if(this.getActionType().isPlayerAction() && Sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) {
			if(Main.game.getPlayer().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
				return ArousalIncrease.FOUR_HIGH;
			} else {
				// If it's an erogenous zone, they gain arousal. If not, arousal gain is 0.
				if((this.getParticipantType().isUsingSelfOrificeType() && this.getAssociatedOrificeType()!=null && this.getAssociatedOrificeType().getBaseArousalWhenPenetrated()>1)
						|| (this.getParticipantType().isUsingSelfPenetrationType() && this.getAssociatedPenetrationType() != null && this.getAssociatedPenetrationType().getBaseArousalWhenPenetrating()>1)) {
					return ArousalIncrease.TWO_LOW;
				}
				return ArousalIncrease.ZERO_NONE;
			}
		}
		
		return selfArousalGain;
	}

	@Override
	public ArousalIncrease getArousalGainTarget() {
		if(!Sex.isMasturbation()) {
			if(!this.getActionType().isPlayerAction() && Sex.getSexPace(Sex.getTargetedPartner(Sex.getActivePartner()))==SexPace.SUB_RESISTING) {
				if(Sex.getTargetedPartner(Sex.getActivePartner()).hasFetish(Fetish.FETISH_NON_CON_SUB)) {
					return ArousalIncrease.FOUR_HIGH;
				} else {
					// If it's an erogenous zone, they gain arousal. If not, arousal gain is 0.
					if((!this.getParticipantType().isUsingSelfOrificeType() && this.getAssociatedOrificeType()!=null && this.getAssociatedOrificeType().getBaseArousalWhenPenetrated()>1)
							|| (!this.getParticipantType().isUsingSelfPenetrationType() && this.getAssociatedPenetrationType() != null && this.getAssociatedPenetrationType().getBaseArousalWhenPenetrating()>1)) {
						return ArousalIncrease.TWO_LOW;
					}
					return ArousalIncrease.ZERO_NONE;
				}
			}
			
			if(this.getActionType().isPlayerAction() && Sex.getSexPace(Sex.getActivePartner())==SexPace.SUB_RESISTING) {
				if(Sex.getActivePartner().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
					return ArousalIncrease.FOUR_HIGH;
				} else {
					// If it's an erogenous zone, they gain arousal. If not, arousal gain is 0.
					if((!this.getParticipantType().isUsingSelfOrificeType() && this.getAssociatedOrificeType()!=null && this.getAssociatedOrificeType().getBaseArousalWhenPenetrated()>1)
							|| (!this.getParticipantType().isUsingSelfPenetrationType() && this.getAssociatedPenetrationType() != null && this.getAssociatedPenetrationType().getBaseArousalWhenPenetrating()>1)) {
						return ArousalIncrease.TWO_LOW;
					}
					return ArousalIncrease.ZERO_NONE;
				}
			}
		}
		
		return targetArousalGain;
	}

	@Override
	public CorruptionLevel getCorruptionNeeded(){
		return minimumCorruptionNeeded;
	}

	@Override
	public abstract String getActionTitle();

	@Override
	public abstract String getDescription();
	
	@Override
	public List<Fetish> getFetishesForTargetedPartner(GameCharacter characterPerformingAction) {
		return getFetishesForEitherPartner(characterPerformingAction, false);
	}

	@Override
	public List<Fetish> getFetishes(GameCharacter characterPerformingAction) {
		return getFetishesForEitherPartner(characterPerformingAction, true);
	}
	
	public List<Fetish> getExtraFetishes(GameCharacter characterPerformingAction) {
		return null;
	}
	
	public List<Fetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
//		if(characterFetishes==null || characterFetishes.get(characterPerformingAction)==null) {
			GameCharacter characterTarget = Sex.getTargetedPartner(characterPerformingAction);
			
			characterFetishes = new HashMap<>();
			characterFetishesForPartner = new HashMap<>();
			
			characterFetishes.putIfAbsent(characterPerformingAction, new HashSet<>());
			characterFetishesForPartner.putIfAbsent(characterPerformingAction, new HashSet<>());
			
			if(getExtraFetishes(characterPerformingAction)!=null) {
				for(Fetish f : getExtraFetishes(characterPerformingAction)) {
					characterFetishes.get(characterPerformingAction).add(f);
				}
			}
			
			if(this.getParticipantType()==SexParticipantType.SELF && !characterPerformingActionFetishes) { // If this is a self action, do not apply fetishes to other partner.
				return new ArrayList<>(characterFetishesForPartner.get(characterPerformingAction));
			}
			
			if(this.getParticipantType()!=SexParticipantType.SELF && characterPerformingAction.isRelatedTo(characterTarget)) {
				characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_INCEST);
				characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_INCEST);
			}
			
			if(Sex.isPublicSex()) {
				characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_EXHIBITIONIST);
				characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_EXHIBITIONIST);
			}
			
			if(this.getSexPace(characterPerformingAction)!=null) {
				switch(this.getSexPace(characterPerformingAction)) {
					case DOM_GENTLE:
						characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_DOMINANT);
						characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_SUBMISSIVE);
						break;
					case DOM_NORMAL:
						characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_DOMINANT);
						characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_SUBMISSIVE);
						break;
					case DOM_ROUGH:
						characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_DOMINANT);
						characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_SUBMISSIVE);
						characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_SADIST);
						characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_MASOCHIST);
						break;
					case SUB_EAGER:
						characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_SUBMISSIVE);
						characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_DOMINANT);
						break;
					case SUB_NORMAL:
						characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_SUBMISSIVE);
						characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_DOMINANT);
						break;
					case SUB_RESISTING:
						characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_SUBMISSIVE);
						characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_DOMINANT);
						characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_NON_CON_SUB);
						characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_NON_CON_DOM);
						break;
				}
			}
			
			List<CoverableArea> cummedOnList = this.getAreasCummedOn(characterPerformingAction, characterTarget);
			if(cummedOnList != null) {
				characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_CUM_STUD);
				characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_CUM_ADDICT);
				for(CoverableArea area : cummedOnList) {
					switch(area) {
						case ANUS: case ASS:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_ANAL_GIVING);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_ANAL_RECEIVING);
							break;
						case BACK:
							break;
						case BREASTS: case NIPPLES:
							if(characterTarget.getBreastRawMilkStorageValue()>0) {
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_LACTATION_OTHERS);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_LACTATION_SELF);
							}
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_BREASTS_OTHERS);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_BREASTS_SELF);
							break;
						case HAIR:
							break;
						case LEGS: case THIGHS:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_LEG_LOVER);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_STRUTTER);
							break;
						case MOUND:
							break;
						case MOUTH:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_ORAL_GIVING);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_ORAL_RECEIVING);
							break;
						case PENIS: case TESTICLES:
							break;
						case STOMACH:
							break;
						case VAGINA:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_VAGINAL_GIVING);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_VAGINAL_RECEIVING);
							break;
					}
				}
			}
			
			cummedOnList = this.getAreasCummedOn(characterTarget, characterPerformingAction);
			if(cummedOnList != null) {
				characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_CUM_ADDICT);
				characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_CUM_STUD);
				
				for(CoverableArea area : cummedOnList) {
					switch(area) {
						case ANUS: case ASS:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_ANAL_RECEIVING);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_ANAL_GIVING);
							break;
						case BACK:
							break;
						case BREASTS: case NIPPLES:
							if(characterPerformingAction.getBreastRawMilkStorageValue()>0) {
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_LACTATION_SELF);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_LACTATION_OTHERS);
							}
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_BREASTS_SELF);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_BREASTS_OTHERS);
							break;
						case HAIR:
							break;
						case LEGS: case THIGHS:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_STRUTTER);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_LEG_LOVER);
							break;
						case MOUND:
							break;
						case MOUTH:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_ORAL_RECEIVING);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_ORAL_GIVING);
							break;
						case PENIS: case TESTICLES:
							break;
						case STOMACH:
							break;
						case VAGINA:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_VAGINAL_RECEIVING);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_VAGINAL_GIVING);
							break;
					}
				}
			}
			
			
			
			List<SexAreaOrifice> cummedInList = this.getAreasCummedIn(characterPerformingAction, characterTarget);
			if(cummedInList != null) {
				characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_CUM_STUD);
				characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_CUM_ADDICT);
				
				for(SexAreaOrifice orifice : cummedInList) {
					switch(orifice) {
						case ANUS: case ASS:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_ANAL_GIVING);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_ANAL_RECEIVING);
							break;
						case BREAST: case NIPPLE:
							if(characterTarget.getBreastRawMilkStorageValue()>0) {
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_LACTATION_OTHERS);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_LACTATION_SELF);
							}
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_BREASTS_OTHERS);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_BREASTS_SELF);
							break;
						case THIGHS:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_LEG_LOVER);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_STRUTTER);
							break;
						case MOUTH:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_ORAL_GIVING);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_ORAL_RECEIVING);
							break;
						case VAGINA:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_VAGINAL_GIVING);
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_IMPREGNATION);
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_SEEDER);
							if(this.getParticipantType()==SexParticipantType.SELF) {
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_PREGNANCY);
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_BROODMOTHER);
							}
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_VAGINAL_RECEIVING);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_PREGNANCY);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_BROODMOTHER);
							break;
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							break;
					}
				}
			}
			
			cummedInList = this.getAreasCummedIn(characterTarget, characterPerformingAction);
			if(cummedInList != null) {
				characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_CUM_ADDICT);
				characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_CUM_STUD);
				
				for(SexAreaOrifice orifice : cummedInList) {
					switch(orifice) {
						case ANUS: case ASS:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_ANAL_RECEIVING);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_ANAL_GIVING);
							break;
						case BREAST: case NIPPLE:
							if(characterPerformingAction.getBreastRawMilkStorageValue()>0) {
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_LACTATION_SELF);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_LACTATION_OTHERS);
							}
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_BREASTS_SELF);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_BREASTS_OTHERS);
							break;
						case THIGHS:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_STRUTTER);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_LEG_LOVER);
							break;
						case MOUTH:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_ORAL_RECEIVING);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_ORAL_GIVING);
							break;
						case VAGINA:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_VAGINAL_RECEIVING);
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_PREGNANCY);
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_BROODMOTHER);
							if(this.getParticipantType()==SexParticipantType.SELF) {
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_IMPREGNATION);
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_SEEDER);
							}
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_VAGINAL_GIVING);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_IMPREGNATION);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_SEEDER);
							break;
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							break;
					}
				}
			}
			
			if(this.getAssociatedPenetrationType()!=null && this.getAssociatedOrificeType()!=null
					&& this.getActionType()!=SexActionType.PLAYER_STOP_PENETRATION
					&& this.getActionType()!=SexActionType.PARTNER_STOP_PENETRATION
					&& this.getParticipantType()!=SexParticipantType.MISC) {
				if(characterPerformingActionFetishes) {
					characterFetishes.get(characterPerformingAction).addAll(getFetishesFromPenetrationAndOrificeTypes(characterPerformingAction, this.getAssociatedPenetrationType(), this.getAssociatedOrificeType(), characterPerformingActionFetishes));
				} else {
					characterFetishesForPartner.get(characterPerformingAction).addAll(getFetishesFromPenetrationAndOrificeTypes(characterPerformingAction, this.getAssociatedPenetrationType(), this.getAssociatedOrificeType(), characterPerformingActionFetishes));
				}
			}
			
			characterFetishes.get(characterPerformingAction).removeIf(f -> !characterTarget.hasVagina() && (f==Fetish.FETISH_VAGINAL_GIVING || f==Fetish.FETISH_IMPREGNATION || f==Fetish.FETISH_SEEDER));
			characterFetishesForPartner.get(characterPerformingAction).removeIf(f -> !characterTarget.hasVagina() && (f==Fetish.FETISH_VAGINAL_GIVING || f==Fetish.FETISH_IMPREGNATION || f==Fetish.FETISH_SEEDER));
//		}
		
		
		if(characterPerformingActionFetishes) {
			return new ArrayList<>(characterFetishes.get(characterPerformingAction));
			
		} else {
			return new ArrayList<>(characterFetishesForPartner.get(characterPerformingAction));
		}
	}
	
	protected List<Fetish> getFetishesFromPenetrationAndOrificeTypes(GameCharacter characterPerformingAction, SexAreaPenetration penetrationBeingUsed, SexAreaOrifice orificeBeingUsed, boolean characterPerformingActionFetishes) {
		GameCharacter characterTarget = Sex.getTargetedPartner(characterPerformingAction);
		
		List<Fetish> associatedFetishes = new ArrayList<>();
		List<Fetish> associatedFetishesPartner = new ArrayList<>();

		boolean isCharacterPerformingActionUsingOrificeType = this.getParticipantType().isUsingSelfOrificeType();
		boolean isCharacterPerformingActionUsingPenetrationType = this.getParticipantType().isUsingSelfPenetrationType();
												
		switch(penetrationBeingUsed) {
			case FINGER:
				if(isCharacterPerformingActionUsingOrificeType) {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_RECEIVING);
							break;
						case BREAST:
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							break;
						case MOUTH:
							associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							break;
						case NIPPLE:
							if(characterPerformingAction.getBreastRawMilkStorageValue()>0) {
								associatedFetishes.add(Fetish.FETISH_LACTATION_SELF);
							}
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							break;
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							associatedFetishes.add(Fetish.FETISH_MASTURBATION);
							break;
						case VAGINA:
							associatedFetishes.add(Fetish.FETISH_MASTURBATION);
							associatedFetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
							break;
						case THIGHS:
							associatedFetishes.add(Fetish.FETISH_STRUTTER);
							break;
					}
				}
				if(isCharacterPerformingActionUsingPenetrationType) {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_GIVING);
							break;
						case BREAST:
							if(!isCharacterPerformingActionUsingOrificeType) {
								associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							}
							break;
						case MOUTH:
							break;
						case NIPPLE:
							if(!isCharacterPerformingActionUsingOrificeType) {
								if(characterTarget.getBreastRawMilkStorageValue()>0) {
									associatedFetishes.add(Fetish.FETISH_LACTATION_OTHERS);
								}
								associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							}
							break;
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							associatedFetishes.add(Fetish.FETISH_MASTURBATION);
							break;
						case VAGINA:
							associatedFetishes.add(Fetish.FETISH_VAGINAL_GIVING);
							associatedFetishes.add(Fetish.FETISH_MASTURBATION);
							break;
						case THIGHS:
							associatedFetishes.add(Fetish.FETISH_LEG_LOVER);
							break;
					}
				}
				break;
			case PENIS:
				if(isCharacterPerformingActionUsingOrificeType) {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_RECEIVING);
							break;
						case BREAST:
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							break;
						case MOUTH:
							associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							if(this.getParticipantType()==SexParticipantType.SELF) {
								associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							}
							break;
						case NIPPLE:
							if(characterPerformingAction.getBreastRawMilkStorageValue()>0) {
								associatedFetishes.add(Fetish.FETISH_LACTATION_SELF);
							}
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							break;
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							associatedFetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
							break;
						case THIGHS:
							associatedFetishes.add(Fetish.FETISH_STRUTTER);
							break;
					}
				}
				if(isCharacterPerformingActionUsingPenetrationType) {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_GIVING);
							break;
						case BREAST:
							if(!isCharacterPerformingActionUsingOrificeType) {
								associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							}
							break;
						case MOUTH:
							associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							break;
						case NIPPLE:
							if(!isCharacterPerformingActionUsingOrificeType) {
								if(characterTarget.getBreastRawMilkStorageValue()>0) {
									associatedFetishes.add(Fetish.FETISH_LACTATION_OTHERS);
								}
								associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							}
							break;
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							associatedFetishes.add(Fetish.FETISH_VAGINAL_GIVING);
							break;
						case THIGHS:
							associatedFetishes.add(Fetish.FETISH_LEG_LOVER);
							break;
					}
				}
				break;
			case TAIL:
				if(isCharacterPerformingActionUsingOrificeType) {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_RECEIVING);
							break;
						case BREAST:
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							break;
						case MOUTH:
							associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							if(this.getParticipantType()==SexParticipantType.SELF) {
								associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							}
							break;
						case NIPPLE:
							if(characterPerformingAction.getBreastRawMilkStorageValue()>0) {
								associatedFetishes.add(Fetish.FETISH_LACTATION_SELF);
							}
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							break;
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							associatedFetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
							break;
						case THIGHS:
							associatedFetishes.add(Fetish.FETISH_STRUTTER);
							break;
					}
				}
				if(isCharacterPerformingActionUsingPenetrationType) {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_GIVING);
							break;
						case BREAST:
							if(!isCharacterPerformingActionUsingOrificeType) {
								associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							}
							break;
						case MOUTH:
							associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							break;
						case NIPPLE:
							if(!isCharacterPerformingActionUsingOrificeType) {
								if(characterTarget.getBreastRawMilkStorageValue()>0) {
									associatedFetishes.add(Fetish.FETISH_LACTATION_OTHERS);
								}
								associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							}
							break;
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							associatedFetishes.add(Fetish.FETISH_VAGINAL_GIVING);
							break;
						case THIGHS:
							associatedFetishes.add(Fetish.FETISH_LEG_LOVER);
							break;
					}
				}
				break;
			case TENTACLE:
				if(isCharacterPerformingActionUsingOrificeType) {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_RECEIVING);
							break;
						case BREAST:
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							break;
						case MOUTH:
							associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							if(this.getParticipantType()==SexParticipantType.SELF) {
								associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							}
							break;
						case NIPPLE:
							if(characterPerformingAction.getBreastRawMilkStorageValue()>0) {
								associatedFetishes.add(Fetish.FETISH_LACTATION_SELF);
							}
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							break;
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							associatedFetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
							break;
						case THIGHS:
							associatedFetishes.add(Fetish.FETISH_STRUTTER);
							break;
					}
				}
				if(isCharacterPerformingActionUsingPenetrationType) {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_GIVING);
							break;
						case BREAST:
							if(!isCharacterPerformingActionUsingOrificeType) {
								associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							}
							break;
						case MOUTH:
							associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							break;
						case NIPPLE:
							if(!isCharacterPerformingActionUsingOrificeType) {
								if(characterTarget.getBreastRawMilkStorageValue()>0) {
									associatedFetishes.add(Fetish.FETISH_LACTATION_OTHERS);
								}
								associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							}
							break;
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							associatedFetishes.add(Fetish.FETISH_VAGINAL_GIVING);
							break;
						case THIGHS:
							associatedFetishes.add(Fetish.FETISH_LEG_LOVER);
							break;
					}
				}
				break;
			case TONGUE:
				if(isCharacterPerformingActionUsingOrificeType) {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_RECEIVING);
							associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							if(this.getParticipantType()==SexParticipantType.SELF) {
								associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							}
							break;
						case BREAST:
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							if(this.getParticipantType()==SexParticipantType.SELF) {
								associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							}
							break;
						case MOUTH:
							break;
						case NIPPLE:
							if(characterPerformingAction.getBreastRawMilkStorageValue()>0) {
								associatedFetishes.add(Fetish.FETISH_LACTATION_SELF);
							}
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							if(this.getParticipantType()==SexParticipantType.SELF) {
								associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							}
							break;
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							if(this.getParticipantType()==SexParticipantType.SELF) {
								associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							}
							break;
						case VAGINA:
							associatedFetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
							associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							if(this.getParticipantType()==SexParticipantType.SELF) {
								associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							}
							break;
						case THIGHS:
							associatedFetishes.add(Fetish.FETISH_STRUTTER);
							break;
					}
				}
				if(isCharacterPerformingActionUsingPenetrationType) {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_GIVING);
							associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							break;
						case BREAST:
							if(!isCharacterPerformingActionUsingOrificeType) {
								associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							}
							associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							break;
						case MOUTH:
							associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							break;
						case NIPPLE:
							if(!isCharacterPerformingActionUsingOrificeType) {
								if(characterTarget.getBreastRawMilkStorageValue()>0) {
									associatedFetishes.add(Fetish.FETISH_LACTATION_OTHERS);
								}
								associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							}
							associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							break;
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							break;
						case VAGINA:
							associatedFetishes.add(Fetish.FETISH_VAGINAL_GIVING);
							associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							break;
						case THIGHS:
							associatedFetishes.add(Fetish.FETISH_LEG_LOVER);
							break;
					}
				}
				break;
		}
		if(!associatedFetishes.contains(Fetish.FETISH_MASTURBATION) && this.getParticipantType()==SexParticipantType.SELF) {
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
				case FETISH_BROODMOTHER:
					associatedFetishesPartner.add(Fetish.FETISH_SEEDER);
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
				case FETISH_SEEDER:
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
			}
		}
		
		if(characterPerformingActionFetishes) {
			return associatedFetishes;
		} else {
			return associatedFetishesPartner;
		}
	}
}
