package com.base.game.sex.managers.dominion.harpies;

import com.base.game.sex.Sex;
import com.base.game.sex.SexPace;
import com.base.game.sex.SexPosition;
import com.base.game.sex.managers.SexManagerDefault;
import com.base.game.sex.sexActions.baseActionsMisc.GenericActions;
import com.base.game.sex.sexActions.baseActionsMisc.PartnerTalk;
import com.base.game.sex.sexActions.baseActionsMisc.PlayerTalk;
import com.base.game.sex.sexActions.baseActionsPartner.PartnerFingerAnus;
import com.base.game.sex.sexActions.baseActionsPartner.PartnerFingerUrethra;
import com.base.game.sex.sexActions.baseActionsPartner.PartnerFingerVagina;
import com.base.game.sex.sexActions.baseActionsPartner.PartnerTongueVagina;
import com.base.game.sex.sexActions.baseActionsPlayer.PlayerPenisMouth;
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
import com.base.game.sex.sexActions.dominion.harpies.HarpyPositioning;
import com.base.game.sex.sexActions.universal.GenericOrgasms;
import com.base.game.sex.sexActions.universal.dom.DomKneeling;

/**
 * @since 0.1.8
 * @version 0.1.8
 * @author Innoxia
 */
public class SMHarpyKneeling extends SexManagerDefault {

	public SMHarpyKneeling() {
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

				PlayerPenisMouth.class,

				PartnerFingerUrethra.class,
				PartnerFingerVagina.class,
				PartnerFingerAnus.class,
				PartnerTongueVagina.class,
				
				DomKneeling.class,
				
				// Positioning:
				
				HarpyPositioning.class,
				
				// Universal:

				PlayerTalk.class,
				PartnerTalk.class,
				GenericActions.class,
				GenericOrgasms.class);
	}
	
	@Override
	public SexPosition getPosition() {
		return SexPosition.KNEELING_PARTNER_PERFORMING_ORAL;
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
		return false;
	}

	@Override
	public SexPace getStartingSexPacePlayer() {
		return SexPace.DOM_NORMAL;
	}

	@Override
	public SexPace getStartingSexPacePartner() {
		return Sex.getPartner().getSexPaceSubPreference();
	}

}
