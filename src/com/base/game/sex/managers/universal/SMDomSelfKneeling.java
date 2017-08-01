package com.base.game.sex.managers.universal;

import com.base.game.sex.Sex;
import com.base.game.sex.SexPace;
import com.base.game.sex.SexPosition;
import com.base.game.sex.managers.SexManagerDefault;
import com.base.game.sex.sexActions.baseActionsMisc.GenericActions;
import com.base.game.sex.sexActions.baseActionsMisc.GenericPositioning;
import com.base.game.sex.sexActions.baseActionsMisc.PartnerTalk;
import com.base.game.sex.sexActions.baseActionsMisc.PlayerTalk;
import com.base.game.sex.sexActions.baseActionsPartner.PartnerPenisMouth;
import com.base.game.sex.sexActions.baseActionsPlayer.PlayerFingerAnus;
import com.base.game.sex.sexActions.baseActionsPlayer.PlayerFingerUrethra;
import com.base.game.sex.sexActions.baseActionsPlayer.PlayerFingerVagina;
import com.base.game.sex.sexActions.baseActionsPlayer.PlayerTongueVagina;
import com.base.game.sex.sexActions.baseActionsSelfPartner.*;
import com.base.game.sex.sexActions.baseActionsSelfPlayer.*;
import com.base.game.sex.sexActions.universal.GenericOrgasms;

public class SMDomSelfKneeling extends SexManagerDefault {

	public SMDomSelfKneeling() {
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

				PartnerPenisMouth.class,

				PlayerFingerUrethra.class,
				PlayerFingerVagina.class,
				PlayerFingerAnus.class,
				PlayerTongueVagina.class,

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
		return SexPosition.KNEELING_PLAYER_PERFORMING_ORAL;
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
