package com.lilithsthrone.game.sex.managers.dominion;

import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPosition;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.dominion.SARoseHands;

/**
 * @since 0.1.7?
 * @version 0.1.79
 * @author Innoxia
 */
public class SMRoseHands extends SexManagerDefault {

	public SMRoseHands() {
		super(SARoseHands.class, GenericOrgasms.class);
	}
	
	@Override
	public SexPosition getPosition() {
		return SexPosition.ROSE_STANDING;
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
	public boolean isItemUseAvailable() {
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
		return false;
	}
	
	@Override
	public boolean isPartnerCanRemovePlayersClothes(){
		return false;
	}

	@Override
	public boolean isPlayerStartNaked() {
		return false;
	}

	@Override
	public boolean isPartnerStartNaked() {
		return false;
	}

	@Override
	public SexPace getStartingSexPacePlayer() {
		return SexPace.DOM_NORMAL;
	}

	@Override
	public SexPace getStartingSexPacePartner() {
		return SexPace.SUB_NORMAL;
	}
}
