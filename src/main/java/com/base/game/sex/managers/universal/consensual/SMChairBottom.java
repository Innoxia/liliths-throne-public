package com.base.game.sex.managers.universal.consensual;

import com.base.game.sex.SexPace;
import com.base.game.sex.SexPosition;
import com.base.game.sex.managers.SexManagerDefault;
import com.base.game.sex.sexActions.baseActionsMisc.GenericActions;
import com.base.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfFingerAnus;
import com.base.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfFingerMouth;
import com.base.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfFingerNipple;
import com.base.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfFingerVagina;
import com.base.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfNoPen;
import com.base.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfTailAnus;
import com.base.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfTailMouth;
import com.base.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfTailNipple;
import com.base.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfTailVagina;
import com.base.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfFingerAnus;
import com.base.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfFingerMouth;
import com.base.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfFingerNipple;
import com.base.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfFingerVagina;
import com.base.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfNoPen;
import com.base.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfTailAnus;
import com.base.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfTailMouth;
import com.base.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfTailNipple;
import com.base.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfTailVagina;
import com.base.game.sex.sexActions.universal.GenericOrgasms;
import com.base.game.sex.sexActions.universal.consensual.ConChairBottom;
import com.base.game.sex.sexActions.universal.consensual.ConChairBottomPositions;

/**
 * @since 0.1.69.9
 * @version 0.1.79
 * @author Innoxia
 */
public class SMChairBottom extends SexManagerDefault {

	public SMChairBottom() {
		super(// Self actions:
				
				PlayerSelfFingerAnus.class,
				PlayerSelfFingerMouth.class,
				PlayerSelfFingerNipple.class,
				PlayerSelfFingerVagina.class,
				
				PlayerSelfTailAnus.class,
				PlayerSelfTailMouth.class,
				PlayerSelfTailNipple.class,
				PlayerSelfTailVagina.class,
				
				PlayerSelfNoPen.class,
				
				
				PartnerSelfFingerAnus.class,
				PartnerSelfFingerMouth.class,
				PartnerSelfFingerNipple.class,
				PartnerSelfFingerVagina.class,
				
				PartnerSelfTailAnus.class,
				PartnerSelfTailMouth.class,
				PartnerSelfTailNipple.class,
				PartnerSelfTailVagina.class,
				
				PartnerSelfNoPen.class,
				
				// Scene-specific:
				
				ConChairBottom.class,
				
				// Positioning:
				
				ConChairBottomPositions.class,
				
				// Universal:

				GenericActions.class,
				GenericOrgasms.class);
	}
	
	@Override
	public SexPosition getPosition() {
		return SexPosition.CHAIR_BOTTOM;
	}

	@Override
	public String getStartSexDescription() {
		return "";
	}
	
	@Override
	public boolean isConsensualSex(){
		return true;
	}
	
	@Override
	public boolean isPlayerAbleToStopSex() {
		return true;
	}
	
	@Override
	public boolean isPlayerCanRemoveOwnClothes(){
		return true;
	}
	
	@Override
	public boolean isPlayerCanRemovePartnersClothes(){
		return true;
	}
	
	@Override
	public boolean isPartnerCanRemoveOwnClothes(){
		return true;
	}
	
	@Override
	public boolean isPartnerCanRemovePlayersClothes(){
		return true;
	}

	@Override
	public SexPace getStartingSexPacePlayer() {
		return SexPace.SUB_NORMAL;
	}

	@Override
	public SexPace getStartingSexPacePartner() {
		return SexPace.DOM_NORMAL;
	}

}
