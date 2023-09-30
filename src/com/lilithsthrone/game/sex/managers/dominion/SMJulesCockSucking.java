package com.lilithsthrone.game.sex.managers.dominion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.dominion.Jules;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.4.9
 * @author Innoxia
 */
public class SMJulesCockSucking extends SexManagerDefault {

	public SMJulesCockSucking(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPosition.STANDING,
				dominants,
				submissives);
	}

	@Override
	public SexControl getSexControl(GameCharacter character) {
		if(character.isPlayer()) {
			return SexControl.ONGOING_ONLY;
		}
		return super.getSexControl(character);
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
	public boolean isPartnerWantingToStopSex(GameCharacter partner) {
		return Main.sex.isSatisfiedFromOrgasms(Main.game.getNpc(Jules.class), true);
	}
	
	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
		return !character.isPlayer();
	}
	
	@Override
	public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
		return Util.newHashMapOfValues(
				new Value<>(Main.game.getNpc(Jules.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
				new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)));
	}
	
	@Override
	public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
		return new ArrayList<>();
	}

	@Override
	public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
		if(character instanceof Jules) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
		}
		return super.getForeplayPreference(character, targetedCharacter);
	}

	@Override
	public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
		if(character instanceof Jules) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
		}
		return super.getMainSexPreference(character, targetedCharacter);
	}
}
