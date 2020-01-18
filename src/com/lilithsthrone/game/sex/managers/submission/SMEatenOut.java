package com.lilithsthrone.game.sex.managers.submission;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.NPC;
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
public class SMEatenOut extends SexManagerDefault {

	public SMEatenOut(AbstractSexPosition position, Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(position,
				dominants,
				submissives);
	}

	@Override
	public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
		Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();

		for(GameCharacter dom : this.getDominants().keySet()) {
			map.put(dom, Util.newArrayListOfValues(CoverableArea.MOUTH));
		}
		
		for(GameCharacter sub : this.getSubmissives().keySet()) {
			map.put(sub, Util.newArrayListOfValues(CoverableArea.VAGINA));
		}
		
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
	public SexType getForeplayPreference(NPC character, GameCharacter targetedCharacter) {
		if(Main.sex.isDom(character)) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
		}
		return character.getForeplayPreference(targetedCharacter);
	}
	
	@Override
	public SexType getMainSexPreference(NPC character, GameCharacter targetedCharacter) {
		if(Main.sex.isDom(character)) {
			return character.getForeplayPreference(targetedCharacter);
		}
		return character.getMainSexPreference(targetedCharacter);
	}
}
