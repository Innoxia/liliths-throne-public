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
import com.lilithsthrone.main.Main;

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
	private Map<GameCharacter, List<Fetish>> characterFetishes;
	
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
		this.sexPacePlayer = sexActionToCopy.getSexPace(Main.game.getPlayer());
		this.sexPacePartner = sexActionToCopy.getSexPace(Sex.getActivePartner());
	}
	
	
	@Override
	public SexPace getSexPace(GameCharacter character){
		if(character.isPlayer()) {
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
	public List<Fetish> getFetishes(GameCharacter character) {
		if(characterFetishes==null || characterFetishes.get(character)==null) {
			characterFetishes = new HashMap<>();
			characterFetishes.putIfAbsent(character, new ArrayList<>());
			if(character.isPlayer()) {
				if(this.getSexPace(Main.game.getPlayer())!=null) {
					switch(this.getSexPace(Main.game.getPlayer())) {
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
				if(this.getSexPace(Sex.getActivePartner())!=null) {
					switch(this.getSexPace(Sex.getActivePartner())) {
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
						case FINGER_PARTNER:
							switch(this.getAssociatedOrificeType()) {
								case ANUS_PLAYER: case ASS_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									break;
								case BREAST_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case MOUTH_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case NIPPLE_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case URETHRA_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_MASTURBATION);
									break;
								case VAGINA_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_MASTURBATION);
									break;
								default:
									break;
							}
							break;
						case FINGER_PLAYER:
							switch(this.getAssociatedOrificeType()) {
								case ANUS_PLAYER: case ASS_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									break;
								case BREAST_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case MOUTH_PLAYER:
									break;
								case NIPPLE_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case URETHRA_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_MASTURBATION);
									break;
								case VAGINA_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_MASTURBATION);
									break;
								case THIGHS_PLAYER:
									break;
									
								case ANUS_PARTNER: case ASS_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_GIVING);
									break;
								case BREAST_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									break;
								case MOUTH_PARTNER:
									break;
								case NIPPLE_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									break;
								case URETHRA_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_MASTURBATION);
									break;
								case VAGINA_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_MASTURBATION);
									break;
								case THIGHS_PARTNER:
									break;
							}
							break;
						case PENIS_PARTNER:
							switch(this.getAssociatedOrificeType()) {
								case ANUS_PLAYER:  case ASS_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									break;
								case BREAST_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case MOUTH_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case NIPPLE_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case URETHRA_PLAYER:
									break;
								case VAGINA_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_PREGNANCY);
									characterFetishes.get(character).add(Fetish.FETISH_BROODMOTHER);
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
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									break;
								case BREAST_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case MOUTH_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case NIPPLE_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case URETHRA_PLAYER:
									break;
								case VAGINA_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_PREGNANCY);
									characterFetishes.get(character).add(Fetish.FETISH_BROODMOTHER);
									characterFetishes.get(character).add(Fetish.FETISH_IMPREGNATION);
									characterFetishes.get(character).add(Fetish.FETISH_SEEDER);
									break;
								case THIGHS_PLAYER:
									break;
									
								case ANUS_PARTNER: case ASS_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_GIVING);
									break;
								case BREAST_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									break;
								case MOUTH_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case NIPPLE_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									break;
								case URETHRA_PARTNER:
									break;
								case VAGINA_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_IMPREGNATION);
									characterFetishes.get(character).add(Fetish.FETISH_SEEDER);
									break;
								case THIGHS_PARTNER:
									break;
							}
							break;
						case TAIL_PARTNER:
							switch(this.getAssociatedOrificeType()) {
								case ANUS_PLAYER: case ASS_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									break;
								case BREAST_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case MOUTH_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case NIPPLE_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
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
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									break;
								case BREAST_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case MOUTH_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case NIPPLE_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case URETHRA_PLAYER:
									break;
								case VAGINA_PLAYER:
									break;
								case THIGHS_PLAYER:
									break;
									
								case ANUS_PARTNER: case ASS_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_GIVING);
									break;
								case BREAST_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									break;
								case MOUTH_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case NIPPLE_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
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
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									break;
								case BREAST_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case MOUTH_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case NIPPLE_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
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
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									break;
								case BREAST_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case MOUTH_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case NIPPLE_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case URETHRA_PLAYER:
									break;
								case VAGINA_PLAYER:
									break;
								case THIGHS_PLAYER:
									break;
									
								case ANUS_PARTNER: case ASS_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_GIVING);
									break;
								case BREAST_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									break;
								case MOUTH_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case NIPPLE_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
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
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case BREAST_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case MOUTH_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case NIPPLE_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case URETHRA_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case VAGINA_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
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
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case BREAST_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case MOUTH_PLAYER:
									break;
								case NIPPLE_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case URETHRA_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case VAGINA_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case THIGHS_PLAYER:
									break;
									
								case ANUS_PARTNER: case ASS_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_GIVING);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case BREAST_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case MOUTH_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case NIPPLE_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case URETHRA_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case VAGINA_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case THIGHS_PARTNER:
									break;
							}
							break;
					}
					if(!characterFetishes.get(character).contains(Fetish.FETISH_MASTURBATION) && this.getAssociatedOrificeType().isPlayer() && this.getAssociatedPenetrationType().isPlayer()) {
						characterFetishes.get(character).add(Fetish.FETISH_MASTURBATION);
					}
				}
				
			} else { // partner fetishes:
				if(this.getSexPace(Sex.getActivePartner())!=null) {
					switch(this.getSexPace(Sex.getActivePartner())) {
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
				if(this.getSexPace(Main.game.getPlayer())!=null) {
					switch(this.getSexPace(Main.game.getPlayer())) {
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
				if(this.getAssociatedPenetrationType()!=null  && this.getAssociatedOrificeType()!=null) {
					switch(this.getAssociatedPenetrationType()) {
						case FINGER_PLAYER:
							switch(this.getAssociatedOrificeType()) {
								case ANUS_PARTNER: case ASS_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									break;
								case BREAST_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case MOUTH_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case NIPPLE_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case URETHRA_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_MASTURBATION);
									break;
								case VAGINA_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_MASTURBATION);
									break;
								default:
									break;
							}
							break;
						case FINGER_PARTNER:
							switch(this.getAssociatedOrificeType()) {
								case ANUS_PARTNER: case ASS_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									break;
								case BREAST_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case MOUTH_PARTNER:
									break;
								case NIPPLE_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case URETHRA_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_MASTURBATION);
									break;
								case VAGINA_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_MASTURBATION);
									break;
								case THIGHS_PARTNER:
									break;
									
								case ANUS_PLAYER: case ASS_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_GIVING);
									break;
								case BREAST_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									break;
								case MOUTH_PLAYER:
									break;
								case NIPPLE_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									break;
								case URETHRA_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_MASTURBATION);
									break;
								case VAGINA_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_MASTURBATION);
									break;
								case THIGHS_PLAYER:
									break;
							}
							break;
						case PENIS_PLAYER:
							switch(this.getAssociatedOrificeType()) {
								case ANUS_PARTNER: case ASS_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									break;
								case BREAST_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case MOUTH_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case NIPPLE_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case URETHRA_PARTNER:
									break;
								case VAGINA_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_PREGNANCY);
									characterFetishes.get(character).add(Fetish.FETISH_BROODMOTHER);
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
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									break;
								case BREAST_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case MOUTH_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case NIPPLE_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case URETHRA_PARTNER:
									break;
								case VAGINA_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_PREGNANCY);
									characterFetishes.get(character).add(Fetish.FETISH_BROODMOTHER);
									characterFetishes.get(character).add(Fetish.FETISH_IMPREGNATION);
									characterFetishes.get(character).add(Fetish.FETISH_SEEDER);
									break;
								case THIGHS_PARTNER:
									break;
									
								case ANUS_PLAYER: case ASS_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_GIVING);
									break;
								case BREAST_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									break;
								case MOUTH_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case NIPPLE_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									break;
								case URETHRA_PLAYER:
									break;
								case VAGINA_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_IMPREGNATION);
									characterFetishes.get(character).add(Fetish.FETISH_SEEDER);
									break;
								case THIGHS_PLAYER:
									break;
							}
							break;
						case TAIL_PLAYER:
							switch(this.getAssociatedOrificeType()) {
								case ANUS_PARTNER: case ASS_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									break;
								case BREAST_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case MOUTH_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case NIPPLE_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
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
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									break;
								case BREAST_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case MOUTH_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case NIPPLE_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case URETHRA_PARTNER:
									break;
								case VAGINA_PARTNER:
									break;
								case THIGHS_PARTNER:
									break;
									
								case ANUS_PLAYER: case ASS_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_GIVING);
									break;
								case BREAST_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									break;
								case MOUTH_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case NIPPLE_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
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
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									break;
								case BREAST_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case MOUTH_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case NIPPLE_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
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
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									break;
								case BREAST_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case MOUTH_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case NIPPLE_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									break;
								case URETHRA_PARTNER:
									break;
								case VAGINA_PARTNER:
									break;
								case THIGHS_PARTNER:
									break;
									
								case ANUS_PLAYER: case ASS_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_GIVING);
									break;
								case BREAST_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									break;
								case MOUTH_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case NIPPLE_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
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
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case BREAST_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case MOUTH_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case NIPPLE_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case URETHRA_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									break;
								case VAGINA_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
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
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_RECEIVING);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case BREAST_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case MOUTH_PARTNER:
									break;
								case NIPPLE_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_SELF);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case URETHRA_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case VAGINA_PARTNER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case THIGHS_PARTNER:
									break;
									
								case ANUS_PLAYER: case ASS_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ANAL_GIVING);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case BREAST_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case MOUTH_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_RECEIVING);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case NIPPLE_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_BREASTS_OTHERS);
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case URETHRA_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case VAGINA_PLAYER:
									characterFetishes.get(character).add(Fetish.FETISH_ORAL_GIVING);
									break;
								case THIGHS_PLAYER:
									break;
							}
							break;
					}
					if(!characterFetishes.get(character).contains(Fetish.FETISH_MASTURBATION) && !this.getAssociatedOrificeType().isPlayer() && !this.getAssociatedPenetrationType().isPlayer()) {
						characterFetishes.get(character).add(Fetish.FETISH_MASTURBATION);
					}
				}
			}
		}
		
		return characterFetishes.get(character);
	}

}
