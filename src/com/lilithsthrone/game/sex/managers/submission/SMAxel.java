package com.lilithsthrone.game.sex.managers.submission;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.Axel;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.main.Main;

/**
 * @since 0.3.5.5
 * @version 0.3.5.5
 * @author Innoxia
 */
public class SMAxel extends SexManagerDefault {

	private SexType axelSexTypePreference;
	private List<CoverableArea> exposeAxelAreas;
	private List<CoverableArea> exposePlayerAreas;
	
	public SMAxel(AbstractSexPosition position,
			SexType axelSexTypePreference,
			List<CoverableArea> exposeAxelAreas,
			List<CoverableArea> exposePlayerAreas,
			Map<GameCharacter, SexSlot> dominants,
			Map<GameCharacter, SexSlot> submissives) {
		super(position,
				dominants,
				submissives);
		this.axelSexTypePreference = axelSexTypePreference;
		this.exposeAxelAreas = exposeAxelAreas;
		this.exposePlayerAreas = exposePlayerAreas;
	}
	
	@Override
	public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
		Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
		map.put(Main.game.getNpc(Axel.class), exposeAxelAreas);
		map.put(Main.game.getPlayer(), exposePlayerAreas);
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
			return axelSexTypePreference;
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
