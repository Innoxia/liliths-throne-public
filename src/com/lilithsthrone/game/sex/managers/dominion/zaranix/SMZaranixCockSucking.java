package com.lilithsthrone.game.sex.managers.dominion.zaranix;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.95
 * @version 0.3.3.10
 * @author Innoxia
 */
public class SMZaranixCockSucking extends SexManagerDefault {

	public SMZaranixCockSucking(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPosition.SITTING,
				dominants,
				submissives);
	}
	
	@Override
	public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
		return false;
	}

	@Override
	public boolean isPositionChangingAllowed(GameCharacter character) {
		return false;
	}
	
	@Override
	public SexControl getSexControl(GameCharacter character) {
		if(character.isPlayer()) {
			return SexControl.ONGOING_ONLY;
		}
		return super.getSexControl(character);
	}
	
	@Override
	public boolean isPartnerWantingToStopSex(GameCharacter partner) {
		return partner.equals(Main.game.getNpc(Zaranix.class)) && Sex.getNumberOfOrgasms(Main.game.getNpc(Zaranix.class))>=2;
	}

	@Override
	public boolean isAbleToEquipSexClothing(GameCharacter character){
		return false;
	}
	
	@Override
	public boolean isAbleToRemoveSelfClothing(GameCharacter character){
		return character.equals(Main.game.getNpc(Zaranix.class));
	}

	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
		return character.equals(Main.game.getNpc(Zaranix.class));
	}
}
