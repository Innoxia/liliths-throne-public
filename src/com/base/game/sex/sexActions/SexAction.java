package com.base.game.sex.sexActions;

import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.sex.ArousalIncrease;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.SexPace;

/**
 * @since 0.1.0
 * @version 0.1.78
 * @author Innoxia
 */
public abstract class SexAction implements SexActionInterface {

	private SexActionType sexActionType;
	private ArousalIncrease playerArousalGain, partnerArousalGain;
	private CorruptionLevel minimumCorruptionNeeded;
	private PenetrationType penetrationTypeAccessRequired;
	private OrificeType orificeTypeAccessRequired;
	private SexPace sexPacePlayer, sexPacePartner;
	
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
	
}
