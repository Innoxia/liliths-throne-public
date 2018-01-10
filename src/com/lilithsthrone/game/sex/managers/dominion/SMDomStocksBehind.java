package com.lilithsthrone.game.sex.managers.dominion;

import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericActions;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PartnerTalk;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PlayerTalk;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerTailAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerTailVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerFingerAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerFingerNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerFingerUrethra;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerFingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerPenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerPenisAss;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerPenisThighs;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerPenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerTailAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerTailVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfTailAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfTailVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfFingerAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfFingerMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfFingerNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfFingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfNoPen;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfTailAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfTailMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfTailNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfTailVagina;
import com.lilithsthrone.game.sex.sexActions.dominion.stocks.SADomStocksBehind;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.95
 * @version 0.1.95
 * @author Innoxia
 */
public class SMDomStocksBehind extends SexManagerDefault {

	public SMDomStocksBehind(boolean vaginalAllowed, boolean analAllowed) {
		super(
				// Self actions:
				
				PlayerSelfFingerAnus.class,
				PlayerSelfFingerMouth.class,
				PlayerSelfFingerNipple.class,
				PlayerSelfFingerVagina.class,
				
				PlayerSelfTailAnus.class,
				PlayerSelfTailMouth.class,
				PlayerSelfTailNipple.class,
				PlayerSelfTailVagina.class,
				
				PlayerSelfNoPen.class,
				
				
				(analAllowed?PartnerSelfTailAnus.class:null),
				(vaginalAllowed?PartnerSelfTailVagina.class:null),
				
				// Scene-specific:
				
				PlayerFingerNipple.class,
				PlayerFingerUrethra.class,
				PlayerFingerVagina.class,
				PlayerFingerAnus.class,
				(vaginalAllowed?PlayerPenisVagina.class:null),
				PlayerPenisThighs.class,
				(analAllowed?PlayerPenisAnus.class:null),
				PlayerPenisAss.class,
				(vaginalAllowed?PlayerTailVagina.class:null),
				(analAllowed?PlayerTailAnus.class:null),

				PartnerTailVagina.class,
				PartnerTailAnus.class,
				
				SADomStocksBehind.class,
				
				// Universal:
				
				PlayerTalk.class,
				PartnerTalk.class,
				GenericActions.class,
				GenericOrgasms.class);
	}
	
	@Override
	public SexPositionType getPosition() {
		return SexPositionType.STOCKS_PARTNER_BEING_USED;
	}

	@Override
	public String getStartSexDescription() {
		return "";
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
		return Sex.getActivePartner().getSexPaceSubPreference(Main.game.getPlayer());
	}

}
