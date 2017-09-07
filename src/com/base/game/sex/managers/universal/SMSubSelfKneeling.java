package com.base.game.sex.managers.universal;

import com.base.game.sex.Sex;
import com.base.game.sex.SexPace;
import com.base.game.sex.SexPosition;
import com.base.game.sex.managers.SexManagerDefault;
import com.base.game.sex.sexActions.baseActionsMisc.GenericActions;
import com.base.game.sex.sexActions.baseActionsMisc.GenericPositioning;
import com.base.game.sex.sexActions.baseActionsMisc.PartnerTalk;
import com.base.game.sex.sexActions.baseActionsMisc.PlayerTalk;
import com.base.game.sex.sexActions.baseActionsPartner.PartnerFingerAnus;
import com.base.game.sex.sexActions.baseActionsPartner.PartnerFingerUrethra;
import com.base.game.sex.sexActions.baseActionsPartner.PartnerFingerVagina;
import com.base.game.sex.sexActions.baseActionsPartner.PartnerTongueVagina;
import com.base.game.sex.sexActions.baseActionsPlayer.PlayerPenisMouth;
import com.base.game.sex.sexActions.baseActionsPlayer.PlayerPenisNipple;
import com.base.game.sex.sexActions.baseActionsSelfPartner.*;
import com.base.game.sex.sexActions.baseActionsSelfPlayer.*;
import com.base.game.sex.sexActions.universal.GenericOrgasms;

/**
 * @since 0.1.82
 * @version 0.1.82
 * @author Innoxia
 */
public class SMSubSelfKneeling extends SexManagerDefault {

	public SMSubSelfKneeling() {
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
				PlayerPenisNipple.class,

				PartnerFingerUrethra.class,
				PartnerFingerVagina.class,
				PartnerFingerAnus.class,
				PartnerTongueVagina.class,

				// Positioning:

				GenericPositioning.class,

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
		return false;
	}
	
	@Override
	public boolean isPlayerAbleToStopSex() {
		return false;
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
		return Sex.getPartner().getSexPaceDomPreference();
	}
}
