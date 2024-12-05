package com.lilithsthrone.game.sex.managers.dominion.vanessa;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Vanessa;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisFeet;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.2
 * @version 0.3.4
 * @author Innoxia
 */
public class SMVanessaOralFootjob extends SexManagerDefault {
	
	public SMVanessaOralFootjob(AbstractSexPosition position, Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(position,
				dominants,
				submissives);
	}
	
	@Override
	public boolean isPlayerAbleToStopSex() {
		return false;
	}

	@Override
	public SexActionInterface getPartnerSexAction(NPC partner, SexActionInterface sexActionPlayer) {
		// To restart footjob if player pulled out during orgasm:
		// If orgasming, use an orgasm action:
		if(partner instanceof Vanessa
				&& ArousalLevel.getArousalLevelFromValue(Main.sex.getCharacterPerformingAction().getArousal()) != ArousalLevel.FIVE_ORGASM_IMMINENT
				&& !isPartnerWantingToStopSex(partner)) {
			if(!Main.sex.getOngoingCharactersUsingAreas(Main.game.getNpc(Vanessa.class), SexAreaPenetration.FOOT, SexAreaPenetration.PENIS).contains(Main.game.getPlayer())
					&& Main.sex.getActionsAvailablePartner(partner, Main.game.getPlayer()).contains(PenisFeet.FOOT_JOB_DOUBLE_GIVING_START)) {
				return PenisFeet.FOOT_JOB_DOUBLE_GIVING_START;
			}
		}
		return super.getPartnerSexAction(partner, sexActionPlayer);
	}
	
	@Override
	public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
		return Util.newHashMapOfValues(
				new Value<>(Main.game.getNpc(Vanessa.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
				new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)));
	}
	
	@Override
	public SexControl getSexControl(GameCharacter character) {
		if(character.isPlayer()) {
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
		// She waits for a player orgasm or 2 of her own orgasms:
		if(Main.sex.getNumberOfOrgasms(Main.game.getPlayer())>=1) {
			return Main.sex.getNumberOfOrgasms(partner) >= 1;
		} else {
			return Main.sex.getNumberOfOrgasms(partner) >= 2;
		}
	}
	
}
