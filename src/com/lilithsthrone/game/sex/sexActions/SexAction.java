package com.lilithsthrone.game.sex.sexActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;

/**
 * @since 0.1.0
 * @version 0.2.8
 * @author Innoxia
 */
public abstract class SexAction implements SexActionInterface {

	private SexActionType sexActionType;
	
	private ArousalIncrease selfArousalGain;
	private ArousalIncrease targetArousalGain;
	
	private CorruptionLevel minimumCorruptionNeeded;
	
	/**A map of all the SexAreaInterfaces that are interacting with one another in this SexAction.
	 *  The keys are representing ownership of the character who performs the action, while the values are owned by the character upon which this is being performed.*/
	private Map<SexAreaInterface, SexAreaInterface> sexAreaInteractions;

	private SexParticipantType participantType;
	private SexPace associatedSexPace;
	private Map<GameCharacter, Set<Fetish>> characterFetishes;
	private Map<GameCharacter, Set<Fetish>> characterFetishesForPartner;
	
	public SexAction(SexActionInterface sexActionToCopy) {
		this.sexActionType = sexActionToCopy.getActionType();
		this.selfArousalGain = sexActionToCopy.getArousalGainSelf();
		this.targetArousalGain = sexActionToCopy.getArousalGainTarget();
		this.minimumCorruptionNeeded = sexActionToCopy.getCorruptionNeeded();
		this.sexAreaInteractions = sexActionToCopy.getSexAreaInteractions();
		this.participantType = sexActionToCopy.getParticipantType();
		this.associatedSexPace = sexActionToCopy.getSexPace();
	}
	
	public SexAction(
			SexActionType sexActionType,
			ArousalIncrease selfArousalGain,
			ArousalIncrease targetArousalGain,
			CorruptionLevel minimumCorruptionNeeded,
			Map<SexAreaInterface, SexAreaInterface> sexAreaInteractions,
			SexParticipantType participantType) {
		
		this(sexActionType,
				selfArousalGain,
				targetArousalGain,
				minimumCorruptionNeeded,
				sexAreaInteractions,
				participantType,
				null);
	}
	
	public SexAction(
			SexActionType sexActionType,
			ArousalIncrease selfArousalGain,
			ArousalIncrease targetArousalGain,
			CorruptionLevel minimumCorruptionNeeded,
			Map<SexAreaInterface, SexAreaInterface> sexAreaInteractions,
			SexParticipantType participantType,
			SexPace associatedSexPace) {
		
		this.sexActionType = sexActionType;
		this.selfArousalGain = selfArousalGain;
		this.targetArousalGain = targetArousalGain;
		this.minimumCorruptionNeeded = minimumCorruptionNeeded;
		
		if(sexAreaInteractions==null) {
			this.sexAreaInteractions = new HashMap<>();
		} else {
			this.sexAreaInteractions = sexAreaInteractions;
		}
		
		this.participantType = participantType;
		this.associatedSexPace = associatedSexPace;
	}

	@Override
	public SexPace getSexPace(){
		return associatedSexPace;
	}
	
	public Map<SexAreaInterface, SexAreaInterface> getSexAreaInteractions() {
		return sexAreaInteractions;
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
		if(Sex.getSexPace(Sex.getCharacterPerformingAction())==SexPace.SUB_RESISTING) {
			if(Sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
				return ArousalIncrease.FOUR_HIGH;
				
			} else {
				// If it's an erogenous zone, they gain arousal. If not, arousal gain is 0.
				for(SexAreaInterface sArea : this.getSexAreaInteractions().keySet()) {
					if((sArea.isOrifice() && ((SexAreaOrifice)sArea).getBaseArousalWhenPenetrated()>1)
							|| (sArea.isPenetration() && ((SexAreaPenetration)sArea).getBaseArousalWhenPenetrating()>1)) {
						return ArousalIncrease.TWO_LOW;
					}
				}
				return ArousalIncrease.ZERO_NONE;
			}
		}
		
		return selfArousalGain;
	}

	@Override
	public ArousalIncrease getArousalGainTarget() {
		if(!Sex.isMasturbation()) {
			if(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))==SexPace.SUB_RESISTING) {
				if(Sex.getCharacterTargetedForSexAction(this).hasFetish(Fetish.FETISH_NON_CON_SUB)) {
					return ArousalIncrease.FOUR_HIGH;
					
				} else {
					// If it's an erogenous zone, they gain arousal. If not, arousal gain is 0.
					for(SexAreaInterface sArea : this.getSexAreaInteractions().values()) {
						if((sArea.isOrifice() && ((SexAreaOrifice)sArea).getBaseArousalWhenPenetrated()>1)
								|| (sArea.isPenetration() && ((SexAreaPenetration)sArea).getBaseArousalWhenPenetrating()>1)) {
							return ArousalIncrease.TWO_LOW;
						}
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
			
//			if(this.getParticipantType()==SexParticipantType.SELF && !characterPerformingActionFetishes) { // If this is a self action, do not apply fetishes to other partner.
//				return new ArrayList<>(characterFetishesForPartner.get(characterPerformingAction));
//			}
			
			if(this.getParticipantType()!=SexParticipantType.SELF && characterPerformingAction.isRelatedTo(characterTarget)) {
				characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_INCEST);
				characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_INCEST);
			}
			
			if(Sex.isPublicSex()) {
				characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_EXHIBITIONIST);
				characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_EXHIBITIONIST);
			}
			
			if(this.getSexPace()!=null) {
				switch(this.getSexPace()) {
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
						case NONE:
							break;
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
						case NONE:
							break;
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
							if(this.getParticipantType()==SexParticipantType.SELF) {
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_PREGNANCY);
							}
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_VAGINAL_RECEIVING);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_PREGNANCY);
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
							if(this.getParticipantType()==SexParticipantType.SELF) {
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_IMPREGNATION);
							}
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_VAGINAL_GIVING);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_IMPREGNATION);
							break;
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							break;
					}
				}
			}
			
			for(Entry<SexAreaInterface, SexAreaInterface> entry : this.getSexAreaInteractions().entrySet()) {
				if(this.getActionType()!=SexActionType.STOP_ONGOING) {
					if(characterPerformingActionFetishes) {
						if(this.getParticipantType()==SexParticipantType.SELF) {
							characterFetishes.get(characterPerformingAction).addAll(
									getFetishesFromPenetrationAndOrificeTypes(characterPerformingAction, entry.getKey(), characterPerformingAction, entry.getValue(), !characterPerformingActionFetishes));
						}
						characterFetishes.get(characterPerformingAction).addAll(
								getFetishesFromPenetrationAndOrificeTypes(characterPerformingAction, entry.getKey(), Sex.getTargetedPartner(characterPerformingAction), entry.getValue(), characterPerformingActionFetishes));
						
					} else {
						if(this.getParticipantType()==SexParticipantType.SELF) {
							characterFetishesForPartner.get(characterPerformingAction).addAll(
									getFetishesFromPenetrationAndOrificeTypes(characterPerformingAction, entry.getKey(), characterPerformingAction, entry.getValue(), !characterPerformingActionFetishes));
						}
						characterFetishesForPartner.get(characterPerformingAction).addAll(
								getFetishesFromPenetrationAndOrificeTypes(characterPerformingAction, entry.getKey(), Sex.getTargetedPartner(characterPerformingAction), entry.getValue(), characterPerformingActionFetishes));
					}
				}
			}
			
			characterFetishes.get(characterPerformingAction).removeIf(f -> !characterTarget.hasVagina() && (f==Fetish.FETISH_VAGINAL_GIVING || f==Fetish.FETISH_IMPREGNATION));
			characterFetishesForPartner.get(characterPerformingAction).removeIf(f -> !characterTarget.hasVagina() && (f==Fetish.FETISH_VAGINAL_GIVING || f==Fetish.FETISH_IMPREGNATION));
//		}
		
		
		if(characterPerformingActionFetishes) {
			return new ArrayList<>(characterFetishes.get(characterPerformingAction));
			
		} else {
			return new ArrayList<>(characterFetishesForPartner.get(characterPerformingAction));
		}
	}
}
