package com.lilithsthrone.game.sex.sexActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Fetish;
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
			
			if(this.getSexPace(character)!=null) {
				switch(this.getSexPace(character)) {
					case DOM_GENTLE:
						characterFetishes.get(character).add(Fetish.FETISH_SUBMISSIVE);
						break;
					case DOM_NORMAL:
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
			if(this.getAssociatedPenetrationType()!=null && this.getAssociatedOrificeType()!=null) {
				switch(this.getAssociatedPenetrationType()) {
					case FINGER:
						if(this.getParticipantType()==SexParticipantType.CATCHER || this.getParticipantType()==SexParticipantType.SELF) {
							switch(this.getAssociatedOrificeType()) {
								case ANUS: case ASS:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									break;
								case BREAST:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case MOUTH:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case NIPPLE:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case URETHRA:
									characterFetishes.get(character).add(Fetish.FETISH_MASTURBATION);
									break;
								case VAGINA:
									characterFetishes.get(character).add(Fetish.FETISH_MASTURBATION);
									break;
								case THIGHS:
									break;
							}
						} else {
							switch(this.getAssociatedOrificeType()) {
								case ANUS: case ASS:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_GIVING);
									break;
								case BREAST:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									break;
								case MOUTH:
									break;
								case NIPPLE:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									break;
								case URETHRA:
									characterFetishes.get(character).add(Fetish.FETISH_MASTURBATION);
									break;
								case VAGINA:
									characterFetishes.get(character).add(Fetish.FETISH_MASTURBATION);
									break;
								case THIGHS:
									break;
							}
						}
						break;
					case PENIS:
						if(this.getParticipantType()==SexParticipantType.CATCHER || this.getParticipantType()==SexParticipantType.SELF) {
							switch(this.getAssociatedOrificeType()) {
								case ANUS: case ASS:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									break;
								case BREAST:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case MOUTH:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									if(this.getParticipantType()==SexParticipantType.SELF) {
										characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									}
									break;
								case NIPPLE:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case URETHRA:
									break;
								case VAGINA:
									characterFetishes.get(character).add(Fetish.FETISH_PREGNANCY);
									characterFetishes.get(character).add(Fetish.FETISH_BROODMOTHER);
									if(this.getParticipantType()==SexParticipantType.SELF) {
										characterFetishes.get(character).add(Fetish.FETISH_IMPREGNATION);
										characterFetishes.get(character).add(Fetish.FETISH_SEEDER);
									}
									break;
								case THIGHS:
									break;
							}
						} else {
							switch(this.getAssociatedOrificeType()) {
								case ANUS: case ASS:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_GIVING);
									break;
								case BREAST:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									break;
								case MOUTH:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case NIPPLE:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									break;
								case URETHRA:
									break;
								case VAGINA:
									characterFetishes.get(character).add(Fetish.FETISH_IMPREGNATION);
									characterFetishes.get(character).add(Fetish.FETISH_SEEDER);
									break;
								case THIGHS:
									break;
							}
						}
						break;
					case TAIL:
						if(this.getParticipantType()==SexParticipantType.CATCHER || this.getParticipantType()==SexParticipantType.SELF) {
							switch(this.getAssociatedOrificeType()) {
								case ANUS: case ASS:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									break;
								case BREAST:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case MOUTH:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									if(this.getParticipantType()==SexParticipantType.SELF) {
										characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									}
									break;
								case NIPPLE:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case URETHRA:
									break;
								case VAGINA:
									break;
								case THIGHS:
									break;
							}
						} else {
							switch(this.getAssociatedOrificeType()) {
								case ANUS: case ASS:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_GIVING);
									break;
								case BREAST:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									break;
								case MOUTH:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case NIPPLE:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									break;
								case URETHRA:
									break;
								case VAGINA:
									break;
								case THIGHS:
									break;
							}
						}
						break;
					case TENTACLE:
						if(this.getParticipantType()==SexParticipantType.CATCHER || this.getParticipantType()==SexParticipantType.SELF) {
							switch(this.getAssociatedOrificeType()) {
								case ANUS: case ASS:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									break;
								case BREAST:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case MOUTH:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									if(this.getParticipantType()==SexParticipantType.SELF) {
										characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									}
									break;
								case NIPPLE:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case URETHRA:
									break;
								case VAGINA:
									break;
								case THIGHS:
									break;
							}
						} else {
							switch(this.getAssociatedOrificeType()) {
								case ANUS: case ASS:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_GIVING);
									break;
								case BREAST:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									break;
								case MOUTH:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case NIPPLE:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									break;
								case URETHRA:
									break;
								case VAGINA:
									break;
								case THIGHS:
									break;
							}
						}
						break;
					case TONGUE:
						if(this.getParticipantType()==SexParticipantType.CATCHER || this.getParticipantType()==SexParticipantType.SELF) {
							switch(this.getAssociatedOrificeType()) {
								case ANUS: case ASS:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									if(this.getParticipantType()==SexParticipantType.SELF) {
										characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									}
									break;
								case BREAST:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									if(this.getParticipantType()==SexParticipantType.SELF) {
										characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									}
									break;
								case MOUTH:
									break;
								case NIPPLE:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									if(this.getParticipantType()==SexParticipantType.SELF) {
										characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									}
									break;
								case URETHRA:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									if(this.getParticipantType()==SexParticipantType.SELF) {
										characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									}
									break;
								case VAGINA:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									if(this.getParticipantType()==SexParticipantType.SELF) {
										characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									}
									break;
								case THIGHS:
									break;
							}
						} else {
							switch(this.getAssociatedOrificeType()) {
								case ANUS: case ASS:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_GIVING);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case BREAST:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case MOUTH:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case NIPPLE:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case URETHRA:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case VAGINA:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case THIGHS:
									break;
							}
						}
						break;
				}
				if(!characterFetishes.get(character).contains(Fetish.FETISH_MASTURBATION) && this.getParticipantType()==SexParticipantType.SELF) {
					characterFetishes.get(character).add(Fetish.FETISH_MASTURBATION);
				}
			}
			
		}
		
		return characterFetishes.get(character);
	}

}
