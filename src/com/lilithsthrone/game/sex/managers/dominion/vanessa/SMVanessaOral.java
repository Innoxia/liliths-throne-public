package com.lilithsthrone.game.sex.managers.dominion.vanessa;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.dominion.Vanessa;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPositionOther;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.2
 * @version 0.3.4
 * @author Innoxia
 */
public class SMVanessaOral extends SexManagerDefault {
	
	public SMVanessaOral(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(Main.game.getPlayer().getLegConfiguration().isBipedalPositionedGenitals()
					?SexPositionOther.SITTING
					:SexPositionOther.STANDING,
				dominants,
				submissives);
	}
	
	@Override
	public boolean isPlayerAbleToStopSex() {
		return false;
	}
	
	@Override
	public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
		if(Sex.isDom(Main.game.getPlayer())) {
			return Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)));
		}
		return Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), Util.newArrayListOfValues(CoverableArea.VAGINA)));
	}
	
	@Override
	public SexControl getSexControl(GameCharacter character) {
		if(character.isPlayer() && !Sex.isDom(character)) {
			return SexControl.ONGOING_ONLY;
		}
		return super.getSexControl(character);
	}
	
	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
		return false;
	}
	
	@Override
	public boolean isPositionChangingAllowed(GameCharacter character) {
		return false;
	}
	
	@Override
	public boolean isPartnerWantingToStopSex(GameCharacter partner) {
		// When player is performing cunnilingus, she stops after orgasming once:
		if(Sex.isDom(partner)) {
			return Sex.getNumberOfOrgasms(partner)>=1;
		}

		// When player is receiving cunnilingus, or fucking Vanessa, she stops after both of you are satisfied:
		return Sex.getNumberOfOrgasms(Main.game.getPlayer())>=Main.game.getPlayer().getOrgasmsBeforeSatisfied()
				&& Sex.getNumberOfOrgasms(Main.game.getNpc(Vanessa.class))>=Main.game.getNpc(Vanessa.class).getOrgasmsBeforeSatisfied();
	}
	
}
