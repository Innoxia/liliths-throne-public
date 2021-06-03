package com.lilithsthrone.game.sex.managers.universal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.8.8
 * @version 0.4.1
 * @author Innoxia
 */
public class SMShower extends SexManagerDefault {

	public SMShower(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		this(SexPosition.STANDING, dominants, submissives);
	}
	
	/**
	 * @param startingPosition Need to be either SexPosition.AGAINST_WALL or SexPosition.STANDING.
	 */
	public SMShower(AbstractSexPosition startingPosition, Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(startingPosition,
				dominants,
				submissives);
		if(startingPosition!=SexPosition.AGAINST_WALL && startingPosition!=SexPosition.STANDING) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String getSexTitle() {
		return (!Main.sex.isConsensual() && Main.getProperties().hasValue(PropertyValue.nonConContent)?"Non-consensual ":"")
				+(Main.sex.isPublicSex()?"Public ":"")
				+(Main.sex.getAllParticipants(false).size()>1?"Sex: ":"Masturbation: ")
				+" Shower";
	}
	
	@Override
	public List<AbstractSexPosition> getAllowedSexPositions() {
		List<AbstractSexPosition> positions = Util.newArrayListOfValues(
				SexPosition.AGAINST_WALL,
				SexPosition.STANDING);
		
		return positions;
	}
	
	@Override
	public boolean isCharacterStartNaked(GameCharacter character) {
		return true;
	}

	@Override
	public boolean isWashingScene() {
		return true;
	}
	
	@Override
	public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
		Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
		
		List<GameCharacter> allCharacters = new ArrayList<>(this.getDominants().keySet());
		allCharacters.addAll(this.getSubmissives().keySet());
		
		for(GameCharacter character : allCharacters) {
			map.put(character, new HashMap<>());
			for(SexAreaPenetration penetration : SexAreaPenetration.values()) {
				map.get(character).put(penetration, Util.newHashMapOfValues(new Value<>(null, Util.newHashSetOfValues(LubricationType.WATER))));
			}
			for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
				if(!orifice.isInternalOrifice()
						 || orifice == SexAreaOrifice.NIPPLE
						 || orifice == SexAreaOrifice.NIPPLE_CROTCH) {
					map.get(character).put(orifice, Util.newHashMapOfValues(new Value<>(null, Util.newHashSetOfValues(LubricationType.WATER))));
				}
			}
		}
		
		return map;
	}
}
