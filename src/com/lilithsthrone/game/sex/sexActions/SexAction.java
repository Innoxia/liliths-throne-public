package com.lilithsthrone.game.sex.sexActions;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.SexPace;

/**
 * @since 0.1.0
 * @version 0.1.90
 * @author Innoxia
 */
public abstract class SexAction implements SexActionInterface {

	private SexActionType sexActionType;
	private ArousalIncrease playerArousalGain, partnerArousalGain;
	private CorruptionLevel minimumCorruptionNeeded;
	private PenetrationType penetrationTypeAccessRequired;
	private OrificeType orificeTypeAccessRequired;
	private SexPace sexPacePlayer, sexPacePartner;
	private List<Fetish> fetishesPlayer, fetishesPartner;
	
	public SexAction(
			SexActionType sexActionType,
			ArousalIncrease playerArousalGain,
			ArousalIncrease partnerArousalGain,
			CorruptionLevel minimumCorruptionNeeded,
			PenetrationType penetrationTypeAccessRequired,
			OrificeType orificeTypeAccessRequired) {
		
		this(sexActionType,
				 playerArousalGain,
				 partnerArousalGain,
				 minimumCorruptionNeeded,
				 penetrationTypeAccessRequired,
				 orificeTypeAccessRequired,
				 null,
				 null);
	}
	
	public SexAction(
			SexActionType sexActionType,
			ArousalIncrease playerArousalGain,
			ArousalIncrease partnerArousalGain,
			CorruptionLevel minimumCorruptionNeeded,
			PenetrationType penetrationTypeAccessRequired,
			OrificeType orificeTypeAccessRequired,
			SexPace sexPacePlayer,
			SexPace sexPacePartner) {
		
		this.sexActionType = sexActionType;
		this.playerArousalGain = playerArousalGain;
		this.partnerArousalGain = partnerArousalGain;
		this.minimumCorruptionNeeded = minimumCorruptionNeeded;
		this.penetrationTypeAccessRequired = penetrationTypeAccessRequired;
		this.orificeTypeAccessRequired = orificeTypeAccessRequired;
		this.sexPacePlayer = sexPacePlayer;
		this.sexPacePartner = sexPacePartner;
	}
	
	public SexAction(SexActionInterface sexActionToCopy) {
		this.sexActionType = sexActionToCopy.getActionType();
		this.playerArousalGain = sexActionToCopy.getArousalGainPlayer();
		this.partnerArousalGain = sexActionToCopy.getArousalGainPartner();
		this.minimumCorruptionNeeded = sexActionToCopy.getCorruptionNeeded();
		this.penetrationTypeAccessRequired = sexActionToCopy.getAssociatedPenetrationType();
		this.orificeTypeAccessRequired = sexActionToCopy.getAssociatedOrificeType();
		this.sexPacePlayer = sexActionToCopy.getSexPacePlayer();
		this.sexPacePartner = sexActionToCopy.getSexPacePartner();
	}
	
	
	@Override
	public SexPace getSexPacePlayer(){
		return sexPacePlayer;
	}
	
	@Override
	public SexPace getSexPacePartner(){
		return sexPacePartner;
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
	public SexActionType getActionType(){
		return sexActionType;
	}

	@Override
	public ArousalIncrease getArousalGainPlayer() {
		return playerArousalGain;
	}

	@Override
	public ArousalIncrease getArousalGainPartner() {
		return partnerArousalGain;
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
	public List<Fetish> getFetishesPlayer() {
		if(fetishesPlayer==null) {
			fetishesPlayer = new ArrayList<>();
			if(this.getSexPacePlayer()!=null) {
				switch(this.getSexPacePlayer()) {
					case DOM_GENTLE:
						fetishesPlayer.add(Fetish.FETISH_SUBMISSIVE);
						break;
					case DOM_NORMAL:
						break;
					case DOM_ROUGH:
						fetishesPlayer.add(Fetish.FETISH_DOMINANT);
						fetishesPlayer.add(Fetish.FETISH_SADIST);
						break;
					case SUB_EAGER:
						fetishesPlayer.add(Fetish.FETISH_SUBMISSIVE);
						break;
					case SUB_NORMAL:
						fetishesPlayer.add(Fetish.FETISH_SUBMISSIVE);
						break;
					case SUB_RESISTING:
						fetishesPlayer.add(Fetish.FETISH_NON_CON_SUB);
						break;
				}
			}
			if(this.getSexPacePartner()!=null) {
				switch(this.getSexPacePartner()) {
					case DOM_GENTLE:
						break;
					case DOM_NORMAL:
						break;
					case DOM_ROUGH:
						fetishesPlayer.add(Fetish.FETISH_MASOCHIST);
						break;
					case SUB_EAGER:
						break;
					case SUB_NORMAL:
						break;
					case SUB_RESISTING:
						fetishesPlayer.add(Fetish.FETISH_NON_CON_DOM);
						break;
				}
			}
			if(this.getAssociatedPenetrationType()!=null && this.getAssociatedOrificeType()!=null) {
				switch(this.getAssociatedPenetrationType()) {
					case FINGER_PARTNER:
						switch(this.getAssociatedOrificeType()) {
							case ANUS_PLAYER: case ASS_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_ANAL_RECEIVING);
								break;
							case BREAST_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case MOUTH_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case NIPPLE_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case URETHRA_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_MASTURBATION);
								break;
							case VAGINA_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_MASTURBATION);
								break;
							default:
								break;
						}
						break;
					case FINGER_PLAYER:
						switch(this.getAssociatedOrificeType()) {
							case ANUS_PLAYER: case ASS_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_ANAL_RECEIVING);
								break;
							case BREAST_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case MOUTH_PLAYER:
								break;
							case NIPPLE_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case URETHRA_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_MASTURBATION);
								break;
							case VAGINA_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_MASTURBATION);
								break;
							case THIGHS_PLAYER:
								break;
								
							case ANUS_PARTNER: case ASS_PARTNER:
								fetishesPlayer.add(Fetish.FETISH_ANAL_GIVING);
								break;
							case BREAST_PARTNER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_OTHERS);
								break;
							case MOUTH_PARTNER:
								break;
							case NIPPLE_PARTNER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_OTHERS);
								break;
							case URETHRA_PARTNER:
								fetishesPlayer.add(Fetish.FETISH_MASTURBATION);
								break;
							case VAGINA_PARTNER:
								fetishesPlayer.add(Fetish.FETISH_MASTURBATION);
								break;
							case THIGHS_PARTNER:
								break;
						}
						break;
					case PENIS_PARTNER:
						switch(this.getAssociatedOrificeType()) {
							case ANUS_PLAYER:  case ASS_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_ANAL_RECEIVING);
								break;
							case BREAST_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case MOUTH_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case NIPPLE_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case URETHRA_PLAYER:
								break;
							case VAGINA_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_PREGNANCY);
								fetishesPlayer.add(Fetish.FETISH_BROODMOTHER);
								break;
							case THIGHS_PLAYER:
								break;
							default:
								break;
						}
						break;
					case PENIS_PLAYER:
						switch(this.getAssociatedOrificeType()) {
							case ANUS_PLAYER: case ASS_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_ANAL_RECEIVING);
								break;
							case BREAST_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case MOUTH_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_ORAL_GIVING);
								fetishesPlayer.add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case NIPPLE_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case URETHRA_PLAYER:
								break;
							case VAGINA_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_PREGNANCY);
								fetishesPlayer.add(Fetish.FETISH_BROODMOTHER);
								fetishesPlayer.add(Fetish.FETISH_IMPREGNATION);
								fetishesPlayer.add(Fetish.FETISH_SEEDER);
								break;
							case THIGHS_PLAYER:
								break;
								
							case ANUS_PARTNER: case ASS_PARTNER:
								fetishesPlayer.add(Fetish.FETISH_ANAL_GIVING);
								break;
							case BREAST_PARTNER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_OTHERS);
								break;
							case MOUTH_PARTNER:
								fetishesPlayer.add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case NIPPLE_PARTNER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_OTHERS);
								break;
							case URETHRA_PARTNER:
								break;
							case VAGINA_PARTNER:
								fetishesPlayer.add(Fetish.FETISH_IMPREGNATION);
								fetishesPlayer.add(Fetish.FETISH_SEEDER);
								break;
							case THIGHS_PARTNER:
								break;
						}
						break;
					case TAIL_PARTNER:
						switch(this.getAssociatedOrificeType()) {
							case ANUS_PLAYER: case ASS_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_ANAL_RECEIVING);
								break;
							case BREAST_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case MOUTH_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case NIPPLE_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case URETHRA_PLAYER:
								break;
							case VAGINA_PLAYER:
								break;
							case THIGHS_PLAYER:
								break;
							default:
								break;
						}
						break;
					case TAIL_PLAYER:
						switch(this.getAssociatedOrificeType()) {
							case ANUS_PLAYER: case ASS_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_ANAL_RECEIVING);
								break;
							case BREAST_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case MOUTH_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_ORAL_GIVING);
								fetishesPlayer.add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case NIPPLE_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case URETHRA_PLAYER:
								break;
							case VAGINA_PLAYER:
								break;
							case THIGHS_PLAYER:
								break;
								
							case ANUS_PARTNER: case ASS_PARTNER:
								fetishesPlayer.add(Fetish.FETISH_ANAL_GIVING);
								break;
							case BREAST_PARTNER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_OTHERS);
								break;
							case MOUTH_PARTNER:
								fetishesPlayer.add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case NIPPLE_PARTNER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_OTHERS);
								break;
							case URETHRA_PARTNER:
								break;
							case VAGINA_PARTNER:
								break;
							case THIGHS_PARTNER:
								break;
						}
						break;
					case TENTACLE_PARTNER:
						switch(this.getAssociatedOrificeType()) {
							case ANUS_PLAYER: case ASS_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_ANAL_RECEIVING);
								break;
							case BREAST_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case MOUTH_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case NIPPLE_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case URETHRA_PLAYER:
								break;
							case VAGINA_PLAYER:
								break;
							case THIGHS_PLAYER:
								break;
							default:
								break;
						}
						break;
					case TENTACLE_PLAYER:
						switch(this.getAssociatedOrificeType()) {
							case ANUS_PLAYER: case ASS_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_ANAL_RECEIVING);
								break;
							case BREAST_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case MOUTH_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_ORAL_GIVING);
								fetishesPlayer.add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case NIPPLE_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case URETHRA_PLAYER:
								break;
							case VAGINA_PLAYER:
								break;
							case THIGHS_PLAYER:
								break;
								
							case ANUS_PARTNER: case ASS_PARTNER:
								fetishesPlayer.add(Fetish.FETISH_ANAL_GIVING);
								break;
							case BREAST_PARTNER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_OTHERS);
								break;
							case MOUTH_PARTNER:
								fetishesPlayer.add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case NIPPLE_PARTNER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_OTHERS);
								break;
							case URETHRA_PARTNER:
								break;
							case VAGINA_PARTNER:
								break;
							case THIGHS_PARTNER:
								break;
						}
						break;
					case TONGUE_PARTNER:
						switch(this.getAssociatedOrificeType()) {
							case ANUS_PLAYER: case ASS_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_ANAL_RECEIVING);
								fetishesPlayer.add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case BREAST_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_SELF);
								fetishesPlayer.add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case MOUTH_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_ORAL_GIVING);
								fetishesPlayer.add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case NIPPLE_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_SELF);
								fetishesPlayer.add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case URETHRA_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case VAGINA_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case THIGHS_PLAYER:
								break;
							default:
								break;
						}
						break;
					case TONGUE_PLAYER:
						switch(this.getAssociatedOrificeType()) {
							case ANUS_PLAYER: case ASS_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_ANAL_RECEIVING);
								fetishesPlayer.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case BREAST_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_SELF);
								fetishesPlayer.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case MOUTH_PLAYER:
								break;
							case NIPPLE_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_SELF);
								fetishesPlayer.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case URETHRA_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case VAGINA_PLAYER:
								fetishesPlayer.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case THIGHS_PLAYER:
								break;
								
							case ANUS_PARTNER: case ASS_PARTNER:
								fetishesPlayer.add(Fetish.FETISH_ANAL_GIVING);
								fetishesPlayer.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case BREAST_PARTNER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_OTHERS);
								fetishesPlayer.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case MOUTH_PARTNER:
								fetishesPlayer.add(Fetish.FETISH_ORAL_RECEIVING);
								fetishesPlayer.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case NIPPLE_PARTNER:
								fetishesPlayer.add(Fetish.FETISH_BREASTS_OTHERS);
								fetishesPlayer.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case URETHRA_PARTNER:
								fetishesPlayer.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case VAGINA_PARTNER:
								fetishesPlayer.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case THIGHS_PARTNER:
								break;
						}
						break;
				}
				if(!fetishesPlayer.contains(Fetish.FETISH_MASTURBATION) && this.getAssociatedOrificeType().isPlayer() && this.getAssociatedPenetrationType().isPlayer()) {
					fetishesPlayer.add(Fetish.FETISH_MASTURBATION);
				}
			}
		}
		
		return fetishesPlayer;
	}

	@Override
	public List<Fetish> getFetishesPartner() {
		if(fetishesPartner==null) {
			fetishesPartner = new ArrayList<>();
			if(this.getSexPacePartner()!=null) {
				switch(this.getSexPacePartner()) {
					case DOM_GENTLE:
						fetishesPartner.add(Fetish.FETISH_SUBMISSIVE);
						break;
					case DOM_NORMAL:
						break;
					case DOM_ROUGH:
						fetishesPartner.add(Fetish.FETISH_DOMINANT);
						fetishesPartner.add(Fetish.FETISH_SADIST);
						break;
					case SUB_EAGER:
						fetishesPartner.add(Fetish.FETISH_SUBMISSIVE);
						break;
					case SUB_NORMAL:
						fetishesPartner.add(Fetish.FETISH_SUBMISSIVE);
						break;
					case SUB_RESISTING:
						fetishesPartner.add(Fetish.FETISH_NON_CON_SUB);
						break;
				}
			}
			if(this.getSexPacePlayer()!=null) {
				switch(this.getSexPacePlayer()) {
					case DOM_GENTLE:
						break;
					case DOM_NORMAL:
						break;
					case DOM_ROUGH:
						fetishesPartner.add(Fetish.FETISH_MASOCHIST);
						break;
					case SUB_EAGER:
						break;
					case SUB_NORMAL:
						break;
					case SUB_RESISTING:
						fetishesPartner.add(Fetish.FETISH_NON_CON_DOM);
						break;
				}
			}
			if(this.getAssociatedPenetrationType()!=null  && this.getAssociatedOrificeType()!=null) {
				switch(this.getAssociatedPenetrationType()) {
					case FINGER_PLAYER:
						switch(this.getAssociatedOrificeType()) {
							case ANUS_PARTNER: case ASS_PARTNER:
								fetishesPartner.add(Fetish.FETISH_ANAL_RECEIVING);
								break;
							case BREAST_PARTNER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case MOUTH_PARTNER:
								fetishesPartner.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case NIPPLE_PARTNER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case URETHRA_PARTNER:
								fetishesPartner.add(Fetish.FETISH_MASTURBATION);
								break;
							case VAGINA_PARTNER:
								fetishesPartner.add(Fetish.FETISH_MASTURBATION);
								break;
							default:
								break;
						}
						break;
					case FINGER_PARTNER:
						switch(this.getAssociatedOrificeType()) {
							case ANUS_PARTNER: case ASS_PARTNER:
								fetishesPartner.add(Fetish.FETISH_ANAL_RECEIVING);
								break;
							case BREAST_PARTNER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case MOUTH_PARTNER:
								break;
							case NIPPLE_PARTNER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case URETHRA_PARTNER:
								fetishesPartner.add(Fetish.FETISH_MASTURBATION);
								break;
							case VAGINA_PARTNER:
								fetishesPartner.add(Fetish.FETISH_MASTURBATION);
								break;
							case THIGHS_PARTNER:
								break;
								
							case ANUS_PLAYER: case ASS_PLAYER:
								fetishesPartner.add(Fetish.FETISH_ANAL_GIVING);
								break;
							case BREAST_PLAYER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_OTHERS);
								break;
							case MOUTH_PLAYER:
								break;
							case NIPPLE_PLAYER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_OTHERS);
								break;
							case URETHRA_PLAYER:
								fetishesPartner.add(Fetish.FETISH_MASTURBATION);
								break;
							case VAGINA_PLAYER:
								fetishesPartner.add(Fetish.FETISH_MASTURBATION);
								break;
							case THIGHS_PLAYER:
								break;
						}
						break;
					case PENIS_PLAYER:
						switch(this.getAssociatedOrificeType()) {
							case ANUS_PARTNER: case ASS_PARTNER:
								fetishesPartner.add(Fetish.FETISH_ANAL_RECEIVING);
								break;
							case BREAST_PARTNER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case MOUTH_PARTNER:
								fetishesPartner.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case NIPPLE_PARTNER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case URETHRA_PARTNER:
								break;
							case VAGINA_PARTNER:
								fetishesPartner.add(Fetish.FETISH_PREGNANCY);
								fetishesPartner.add(Fetish.FETISH_BROODMOTHER);
								break;
							case THIGHS_PARTNER:
								break;
							default:
								break;
						}
						break;
					case PENIS_PARTNER:
						switch(this.getAssociatedOrificeType()) {
							case ANUS_PARTNER: case ASS_PARTNER:
								fetishesPartner.add(Fetish.FETISH_ANAL_RECEIVING);
								break;
							case BREAST_PARTNER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case MOUTH_PARTNER:
								fetishesPartner.add(Fetish.FETISH_ORAL_GIVING);
								fetishesPartner.add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case NIPPLE_PARTNER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case URETHRA_PARTNER:
								break;
							case VAGINA_PARTNER:
								fetishesPartner.add(Fetish.FETISH_PREGNANCY);
								fetishesPartner.add(Fetish.FETISH_BROODMOTHER);
								fetishesPartner.add(Fetish.FETISH_IMPREGNATION);
								fetishesPartner.add(Fetish.FETISH_SEEDER);
								break;
							case THIGHS_PARTNER:
								break;
								
							case ANUS_PLAYER: case ASS_PLAYER:
								fetishesPartner.add(Fetish.FETISH_ANAL_GIVING);
								break;
							case BREAST_PLAYER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_OTHERS);
								break;
							case MOUTH_PLAYER:
								fetishesPartner.add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case NIPPLE_PLAYER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_OTHERS);
								break;
							case URETHRA_PLAYER:
								break;
							case VAGINA_PLAYER:
								fetishesPartner.add(Fetish.FETISH_IMPREGNATION);
								fetishesPartner.add(Fetish.FETISH_SEEDER);
								break;
							case THIGHS_PLAYER:
								break;
						}
						break;
					case TAIL_PLAYER:
						switch(this.getAssociatedOrificeType()) {
							case ANUS_PARTNER: case ASS_PARTNER:
								fetishesPartner.add(Fetish.FETISH_ANAL_RECEIVING);
								break;
							case BREAST_PARTNER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case MOUTH_PARTNER:
								fetishesPartner.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case NIPPLE_PARTNER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case URETHRA_PARTNER:
								break;
							case VAGINA_PARTNER:
								break;
							case THIGHS_PARTNER:
								break;
							default:
								break;
						}
						break;
					case TAIL_PARTNER:
						switch(this.getAssociatedOrificeType()) {
							case ANUS_PARTNER: case ASS_PARTNER:
								fetishesPartner.add(Fetish.FETISH_ANAL_RECEIVING);
								break;
							case BREAST_PARTNER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case MOUTH_PARTNER:
								fetishesPartner.add(Fetish.FETISH_ORAL_GIVING);
								fetishesPartner.add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case NIPPLE_PARTNER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case URETHRA_PARTNER:
								break;
							case VAGINA_PARTNER:
								break;
							case THIGHS_PARTNER:
								break;
								
							case ANUS_PLAYER: case ASS_PLAYER:
								fetishesPartner.add(Fetish.FETISH_ANAL_GIVING);
								break;
							case BREAST_PLAYER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_OTHERS);
								break;
							case MOUTH_PLAYER:
								fetishesPartner.add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case NIPPLE_PLAYER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_OTHERS);
								break;
							case URETHRA_PLAYER:
								break;
							case VAGINA_PLAYER:
								break;
							case THIGHS_PLAYER:
								break;
						}
						break;
					case TENTACLE_PLAYER:
						switch(this.getAssociatedOrificeType()) {
							case ANUS_PARTNER: case ASS_PARTNER:
								fetishesPartner.add(Fetish.FETISH_ANAL_RECEIVING);
								break;
							case BREAST_PARTNER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case MOUTH_PARTNER:
								fetishesPartner.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case NIPPLE_PARTNER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case URETHRA_PARTNER:
								break;
							case VAGINA_PARTNER:
								break;
							case THIGHS_PARTNER:
								break;
							default:
								break;
						}
						break;
					case TENTACLE_PARTNER:
						switch(this.getAssociatedOrificeType()) {
							case ANUS_PARTNER: case ASS_PARTNER:
								fetishesPartner.add(Fetish.FETISH_ANAL_RECEIVING);
								break;
							case BREAST_PARTNER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case MOUTH_PARTNER:
								fetishesPartner.add(Fetish.FETISH_ORAL_GIVING);
								fetishesPartner.add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case NIPPLE_PARTNER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_SELF);
								break;
							case URETHRA_PARTNER:
								break;
							case VAGINA_PARTNER:
								break;
							case THIGHS_PARTNER:
								break;
								
							case ANUS_PLAYER: case ASS_PLAYER:
								fetishesPartner.add(Fetish.FETISH_ANAL_GIVING);
								break;
							case BREAST_PLAYER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_OTHERS);
								break;
							case MOUTH_PLAYER:
								fetishesPartner.add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case NIPPLE_PLAYER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_OTHERS);
								break;
							case URETHRA_PLAYER:
								break;
							case VAGINA_PLAYER:
								break;
							case THIGHS_PLAYER:
								break;
						}
						break;
					case TONGUE_PLAYER:
						switch(this.getAssociatedOrificeType()) {
							case ANUS_PARTNER: case ASS_PARTNER:
								fetishesPartner.add(Fetish.FETISH_ANAL_RECEIVING);
								fetishesPartner.add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case BREAST_PARTNER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_SELF);
								fetishesPartner.add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case MOUTH_PARTNER:
								fetishesPartner.add(Fetish.FETISH_ORAL_GIVING);
								fetishesPartner.add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case NIPPLE_PARTNER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_SELF);
								fetishesPartner.add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case URETHRA_PARTNER:
								fetishesPartner.add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case VAGINA_PARTNER:
								fetishesPartner.add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case THIGHS_PARTNER:
								break;
							default:
								break;
						}
						break;
					case TONGUE_PARTNER:
						switch(this.getAssociatedOrificeType()) {
							case ANUS_PARTNER: case ASS_PARTNER:
								fetishesPartner.add(Fetish.FETISH_ANAL_RECEIVING);
								fetishesPartner.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case BREAST_PARTNER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_SELF);
								fetishesPartner.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case MOUTH_PARTNER:
								break;
							case NIPPLE_PARTNER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_SELF);
								fetishesPartner.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case URETHRA_PARTNER:
								fetishesPartner.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case VAGINA_PARTNER:
								fetishesPartner.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case THIGHS_PARTNER:
								break;
								
							case ANUS_PLAYER: case ASS_PLAYER:
								fetishesPartner.add(Fetish.FETISH_ANAL_GIVING);
								fetishesPartner.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case BREAST_PLAYER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_OTHERS);
								fetishesPartner.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case MOUTH_PLAYER:
								fetishesPartner.add(Fetish.FETISH_ORAL_RECEIVING);
								fetishesPartner.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case NIPPLE_PLAYER:
								fetishesPartner.add(Fetish.FETISH_BREASTS_OTHERS);
								fetishesPartner.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case URETHRA_PLAYER:
								fetishesPartner.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case VAGINA_PLAYER:
								fetishesPartner.add(Fetish.FETISH_ORAL_GIVING);
								break;
							case THIGHS_PLAYER:
								break;
						}
						break;
				}
				if(!fetishesPartner.contains(Fetish.FETISH_MASTURBATION) && !this.getAssociatedOrificeType().isPlayer() && !this.getAssociatedPenetrationType().isPlayer()) {
					fetishesPartner.add(Fetish.FETISH_MASTURBATION);
				}
			}
		}
		
		return fetishesPartner;
	}
	
}
