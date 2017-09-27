package com.lilithsthrone.game.sex.managers.dominion;

import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPosition;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.sexActions.dominion.pix.PixOrgasms;
import com.lilithsthrone.game.sex.sexActions.dominion.pix.PixShowerTime;

/**
 * @since 0.1.69.9
 * @version 0.1.79
 * @author Innoxia
 */
public class SMPixShowerTime extends SexManagerDefault {

	public SMPixShowerTime() {
		super(PixShowerTime.class, PixOrgasms.class);
	}
	
	@Override
	public SexPosition getPosition() {
		return SexPosition.PIX_SHOWER_RAPE;
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
	public boolean isItemUseAvailable() {
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
	public boolean isPlayerStartNaked() {
		return true;
	}

	@Override
	public boolean isPartnerStartNaked() {
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
