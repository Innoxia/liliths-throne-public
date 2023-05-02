package com.lilithsthrone.game.sex.managers.dominion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.dominion.Vicky;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.main.Main;

/**
 * @since 0.4.7.8
 * @version 0.4.7.8
 * @author Innoxia
 */
public class SMVicky extends SexManagerDefault {

	private SexType preferenceForeplay;
	private SexType preferenceSex;
	private Map<GameCharacter, List<CoverableArea>> exposeAtStart;
	
	public SMVicky(AbstractSexPosition position,
			Map<GameCharacter, SexSlot> dominants,
			Map<GameCharacter, SexSlot> submissives,
			SexType preferenceForeplay,
			SexType preferenceSex,
			List<CoverableArea> exposeAtStartPlayer,
			List<CoverableArea> exposeAtStartVicky) {
		super(position,
				dominants,
				submissives);
		this.preferenceForeplay = preferenceForeplay;
		this.preferenceSex = preferenceSex;
		exposeAtStart = new HashMap<>();
		if(exposeAtStartPlayer!=null) {
			exposeAtStart.put(Main.game.getPlayer(), exposeAtStartPlayer);
		}
		if(exposeAtStartVicky!=null) {
			exposeAtStart.put(Main.game.getNpc(Vicky.class), exposeAtStartVicky);
		}
	}
	
	@Override
	public String getDeskName() {
		if(Main.game.getDialogueFlags().hasFlag("innoxia_vicky_apartment")) {
			return "coffee table";
		}
		return "counter";
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
	public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
		return exposeAtStart;
	}
	
	@Override
	public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
		if(preferenceForeplay==null) {
			return super.getForeplayPreference(character, targetedCharacter);
		}
		if(character.isPlayer()) {
			return preferenceForeplay.getReversedSexType();
		}
		return preferenceForeplay;
	}

	@Override
	public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
		if(preferenceSex==null) {
			return super.getMainSexPreference(character, targetedCharacter);
		}
		if(character.isPlayer()) {
			return preferenceSex.getReversedSexType();
		}
		return preferenceSex;
	}
}
