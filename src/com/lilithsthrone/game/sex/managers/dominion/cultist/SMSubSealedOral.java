package com.lilithsthrone.game.sex.managers.dominion.cultist;

import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericActions;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PartnerTalk;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerFingerAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerFingerUrethra;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerFingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerTongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerTongueVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerPenisBreasts;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerPenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerPenisNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfFingerAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfFingerMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfFingerNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfFingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfNoPen;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfTailAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfTailMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfTailNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfTailVagina;
import com.lilithsthrone.game.sex.sexActions.dominion.cultist.SASpecialCultist;

/**
 * @since 0.1.88
 * @version 0.1.88
 * @author Innoxia
 */
public class SMSubSealedOral extends SexManagerDefault {

	public SMSubSealedOral() {
		super(
				// Self actions:
				
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
				PlayerPenisBreasts.class,

				PartnerFingerUrethra.class,
				PartnerFingerVagina.class,
				PartnerFingerAnus.class,
				PartnerTongueVagina.class,
				PartnerTongueAnus.class,
				
				SASpecialCultist.class,
				
				// Universal:
				
				PartnerTalk.class,
				GenericActions.class,
				GenericOrgasms.class);
	}
	
	@Override
	public SexPositionType getPosition() {
		return SexPositionType.CULTIST_ALTAR_MISSIONARY_ORAL;
	}

	@Override
	public String getStartSexDescription() {
		return "";
	}
	
	@Override
	public boolean isPlayerAbleToStopSex() {
		return false;
	}
	
	@Override
	public boolean isPlayerCanRemoveOwnClothes(){
		return false;
	}
	
	@Override
	public boolean isPlayerCanRemovePartnersClothes(){
		return false;
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
		return Sex.getActivePartner().getSexPaceDomPreference();
	}

}
