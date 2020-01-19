package com.lilithsthrone.game.sex.managers.submission;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.submission.Shadow;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.5.5
 * @version 0.3.5.5
 * @author Innoxia
 */
public class SMShadowSilence extends SexManagerDefault {

	public SMShadowSilence(AbstractSexPosition position, Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(position,
				dominants,
				submissives);
	}

	@Override
	public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
		Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
		map.put(Main.game.getNpc(Silence.class), Util.newArrayListOfValues(CoverableArea.VAGINA));
		return map;
	}
	
	@Override
	public boolean isPlayerAbleToStopSex() {
		return false;
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
	public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
		if(character.equals(Main.game.getNpc(Silence.class))) {
			return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
		}
		if(character.equals(Main.game.getNpc(Shadow.class))) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
		}
		return character.getForeplayPreference(targetedCharacter);
	}
	
	@Override
	public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
		if(Main.sex.isDom(character)) {
			return character.getForeplayPreference(targetedCharacter);
		}
		return character.getMainSexPreference(targetedCharacter);
	}
}
