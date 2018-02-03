package com.lilithsthrone.game.sex.sexActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
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
	private PenetrationType penetrationTypeAccessRequired;
	private OrificeType orificeTypeAccessRequired;

	private SexParticipantType participantType;
	private SexPace sexPacePlayer, sexPacePartner;
	private Map<GameCharacter, List<Fetish>> characterFetishes;
	
	public SexAction(
			SexActionType sexActionType,
			ArousalIncrease playerArousalGain,
			ArousalIncrease partnerArousalGain,
			CorruptionLevel minimumCorruptionNeeded,
			PenetrationType penetrationTypeAccessRequired,
			OrificeType orificeTypeAccessRequired,
			SexParticipantType participantType) {
		
		this(sexActionType,
				 playerArousalGain,
				 partnerArousalGain,
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
			PenetrationType penetrationTypeAccessRequired,
			OrificeType orificeTypeAccessRequired,
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
	public PenetrationType getAssociatedPenetrationType() {
		return penetrationTypeAccessRequired;
	}

	@Override
	public OrificeType getAssociatedOrificeType() {
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
		return selfArousalGain;
	}

	@Override
	public ArousalIncrease getArousalGainTarget() {
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
	public List<Fetish> getFetishes(GameCharacter character) {
		
		if(characterFetishes==null || characterFetishes.get(character)==null) {
			
			characterFetishes = new HashMap<>();
			characterFetishes.putIfAbsent(character, new ArrayList<>());
			
			if(this.getParticipantType()!=SexParticipantType.SELF && character.isRelatedTo(Sex.getTargetedPartner(character))) {
				characterFetishes.get(character).add(Fetish.FETISH_INCEST);
			}
			
			if(this.getSexPace(character)!=null) {
				switch(this.getSexPace(character)) {
					case DOM_GENTLE:
						characterFetishes.get(character).add(Fetish.FETISH_DOMINANT);
						break;
					case DOM_NORMAL:
						characterFetishes.get(character).add(Fetish.FETISH_DOMINANT);
						break;
					case DOM_ROUGH:
						characterFetishes.get(character).add(Fetish.FETISH_DOMINANT);
						characterFetishes.get(character).add(Fetish.FETISH_SADIST);
						break;
					case SUB_EAGER:
						characterFetishes.get(character).add(Fetish.FETISH_SUBMISSIVE);
						break;
					case SUB_NORMAL:
						characterFetishes.get(character).add(Fetish.FETISH_SUBMISSIVE);
						break;
					case SUB_RESISTING:
						characterFetishes.get(character).add(Fetish.FETISH_SUBMISSIVE);
						characterFetishes.get(character).add(Fetish.FETISH_NON_CON_SUB);
						break;
				}
			}
			if(this.getSexPace(Sex.getTargetedPartner(character))!=null) {
				switch(this.getSexPace(Sex.getTargetedPartner(character))) {
					case DOM_GENTLE:
						break;
					case DOM_NORMAL:
						break;
					case DOM_ROUGH:
						characterFetishes.get(character).add(Fetish.FETISH_MASOCHIST);
						break;
					case SUB_EAGER:
						break;
					case SUB_NORMAL:
						break;
					case SUB_RESISTING:
						characterFetishes.get(character).add(Fetish.FETISH_NON_CON_DOM);
						break;
				}
			}
			
			List<CoverableArea> cummedOnList = this.getAreasCummedOn(character, Sex.getTargetedPartner(character));
			if(cummedOnList != null) {
				characterFetishes.get(character).add(Fetish.FETISH_CUM_STUD);
				for(CoverableArea area : cummedOnList) {
					switch(area) {
						case ANUS: case ASS:
							characterFetishes.get(character).add(Fetish.FETISH_ANAL_GIVING);
							break;
						case BACK:
							break;
						case BREASTS: case NIPPLES:
							characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
							break;
						case HAIR:
							break;
						case LEGS: case THIGHS:
							characterFetishes.get(character).add(Fetish.FETISH_LEG_LOVER);
							break;
						case MOUND:
							break;
						case MOUTH:
							characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
							break;
						case PENIS: case TESTICLES:
							break;
						case STOMACH:
							break;
						case VAGINA:
							characterFetishes.get(character).add(Fetish.FETISH_VAGINAL_GIVING);
							break;
					}
				}
			}
			
			cummedOnList = this.getAreasCummedOn(Sex.getTargetedPartner(character), character);
			if(cummedOnList != null) {
				characterFetishes.get(character).add(Fetish.FETISH_CUM_ADDICT);
				
				for(CoverableArea area : cummedOnList) {
					switch(area) {
						case ANUS: case ASS:
							characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
							break;
						case BACK:
							break;
						case BREASTS: case NIPPLES:
							characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
							break;
						case HAIR:
							break;
						case LEGS: case THIGHS:
							characterFetishes.get(character).add(Fetish.FETISH_STRUTTER);
							break;
						case MOUND:
							break;
						case MOUTH:
							characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
							break;
						case PENIS: case TESTICLES:
							break;
						case STOMACH:
							break;
						case VAGINA:
							characterFetishes.get(character).add(Fetish.FETISH_VAGINAL_RECEIVING);
							break;
					}
				}
			}
			
			
			
			List<OrificeType> cummedInList = this.getAreasCummedIn(character, Sex.getTargetedPartner(character));
			if(cummedInList != null) {
				characterFetishes.get(character).add(Fetish.FETISH_CUM_STUD);
				
				for(OrificeType orifice : cummedInList) {
					switch(orifice) {
						case ANUS: case ASS:
							characterFetishes.get(character).add(Fetish.FETISH_ANAL_GIVING);
							break;
						case BREAST: case NIPPLE:
							characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
							break;
						case THIGHS:
							characterFetishes.get(character).add(Fetish.FETISH_LEG_LOVER);
							break;
						case MOUTH:
							characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
							break;
						case VAGINA:
							characterFetishes.get(character).add(Fetish.FETISH_VAGINAL_GIVING);
							characterFetishes.get(character).add(Fetish.FETISH_IMPREGNATION);
							characterFetishes.get(character).add(Fetish.FETISH_SEEDER);
							if(this.getParticipantType()==SexParticipantType.SELF) {
								characterFetishes.get(character).add(Fetish.FETISH_PREGNANCY);
								characterFetishes.get(character).add(Fetish.FETISH_BROODMOTHER);
							}
							break;
						case URETHRA:
							break;
					}
				}
			}
			
			cummedInList = this.getAreasCummedIn(Sex.getTargetedPartner(character), character);
			if(cummedInList != null) {
				characterFetishes.get(character).add(Fetish.FETISH_CUM_ADDICT);
				
				for(OrificeType orifice : cummedInList) {
					switch(orifice) {
						case ANUS: case ASS:
							characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
							break;
						case BREAST: case NIPPLE:
							characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
							break;
						case THIGHS:
							characterFetishes.get(character).add(Fetish.FETISH_STRUTTER);
							break;
						case MOUTH:
							characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
							break;
						case VAGINA:
							characterFetishes.get(character).add(Fetish.FETISH_VAGINAL_RECEIVING);
							characterFetishes.get(character).add(Fetish.FETISH_PREGNANCY);
							characterFetishes.get(character).add(Fetish.FETISH_BROODMOTHER);
							if(this.getParticipantType()==SexParticipantType.SELF) {
								characterFetishes.get(character).add(Fetish.FETISH_IMPREGNATION);
								characterFetishes.get(character).add(Fetish.FETISH_SEEDER);
							}
							break;
						case URETHRA:
							break;
					}
				}
			}
			
			if(this.getAssociatedPenetrationType()!=null && this.getAssociatedOrificeType()!=null
					&& this.getActionType()!=SexActionType.PLAYER_STOP_PENETRATION
					&& this.getActionType()!=SexActionType.PARTNER_STOP_PENETRATION
					&& this.getParticipantType()!=SexParticipantType.MISC) {
				characterFetishes.get(character).addAll(getFetishesFromPenetrationAndOrificeTypes(character, this.getAssociatedPenetrationType(), this.getAssociatedOrificeType()));
			}
		}
		
		return characterFetishes.get(character);
	}
	
	protected List<Fetish> getFetishesFromPenetrationAndOrificeTypes(GameCharacter character, PenetrationType penetrationBeingUsed, OrificeType orificeBeingUsed) {
		List<Fetish> associatedFetishes = new ArrayList<>();

		boolean isCharacterPerformingAction = this.getActionType().isPlayerAction()
												?character.isPlayer()
												:Sex.getActivePartner().equals(character);
												
		switch(penetrationBeingUsed) {
			case FINGER:
				if(isCharacterPerformingAction
						?this.getParticipantType().isUsingSelfOrificeType()
						:!this.getParticipantType().isUsingSelfOrificeType()) {
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
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							break;
						case URETHRA:
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
				} else {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_GIVING);
							break;
						case BREAST:
							associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							break;
						case MOUTH:
							break;
						case NIPPLE:
							associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							break;
						case URETHRA:
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
				if(isCharacterPerformingAction
						?this.getParticipantType().isUsingSelfOrificeType()
						:!this.getParticipantType().isUsingSelfOrificeType()) {
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
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							break;
						case URETHRA:
							break;
						case VAGINA:
							associatedFetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
							break;
						case THIGHS:
							associatedFetishes.add(Fetish.FETISH_STRUTTER);
							break;
					}
				} else {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_GIVING);
							break;
						case BREAST:
							associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							break;
						case MOUTH:
							associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							break;
						case NIPPLE:
							associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							break;
						case URETHRA:
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
				if(isCharacterPerformingAction
						?this.getParticipantType().isUsingSelfOrificeType()
						:!this.getParticipantType().isUsingSelfOrificeType()) {
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
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							break;
						case URETHRA:
							break;
						case VAGINA:
							associatedFetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
							break;
						case THIGHS:
							associatedFetishes.add(Fetish.FETISH_STRUTTER);
							break;
					}
				} else {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_GIVING);
							break;
						case BREAST:
							associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							break;
						case MOUTH:
							associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							break;
						case NIPPLE:
							associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							break;
						case URETHRA:
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
				if(isCharacterPerformingAction
						?this.getParticipantType().isUsingSelfOrificeType()
						:!this.getParticipantType().isUsingSelfOrificeType()) {
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
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							break;
						case URETHRA:
							break;
						case VAGINA:
							associatedFetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
							break;
						case THIGHS:
							associatedFetishes.add(Fetish.FETISH_STRUTTER);
							break;
					}
				} else {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_GIVING);
							break;
						case BREAST:
							associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							break;
						case MOUTH:
							associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							break;
						case NIPPLE:
							associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							break;
						case URETHRA:
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
				if(isCharacterPerformingAction
						?this.getParticipantType().isUsingSelfOrificeType()
						:!this.getParticipantType().isUsingSelfOrificeType()) {
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
							associatedFetishes.add(Fetish.FETISH_BREASTS_SELF);
							associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							if(this.getParticipantType()==SexParticipantType.SELF) {
								associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							}
							break;
						case URETHRA:
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
				} else {
					switch(orificeBeingUsed) {
						case ANUS: case ASS:
							associatedFetishes.add(Fetish.FETISH_ANAL_GIVING);
							associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							break;
						case BREAST:
							associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							break;
						case MOUTH:
							associatedFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
							associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							break;
						case NIPPLE:
							associatedFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
							associatedFetishes.add(Fetish.FETISH_ORAL_GIVING);
							break;
						case URETHRA:
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
		
		return associatedFetishes;
	}
}
