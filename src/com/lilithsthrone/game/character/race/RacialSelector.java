package com.lilithsthrone.game.character.race;

import java.util.HashMap;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum RacialSelector {

	// HUMAN:
	HUMAN(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.HUMAN, 100))),

	// ANGEL:
	ANGEL(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.ANGEL, 100))),

	// DEMON:
	DEMON(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.DEMON, 100))),

	// BOVINES:
	COW_MORPH(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.COW_MORPH, 100))),
	
	// CANINES:
	DOG_MORPH(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.DOG_MORPH, 100))),
	
	WOLF_MORPH(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.WOLF_MORPH, 100))),

	// FELINES:
	CAT_MORPH(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.CAT_MORPH, 100))),

	// EQUINES:
	HORSE_MORPH(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.HORSE_MORPH, 100))),

	REINDEER_MORPH(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.REINDEER_MORPH, 100))),

	// REPTILE:
	ALLIGATOR_MORPH(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.ALLIGATOR_MORPH, 100))),
			
	// SLIMES:
	SLIME(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.SLIME, 100))),
	
	// GARGOYLES:
	GARGOYLE(Util.newHashMapOfValues(
			new Value<RacialBody, Integer>(RacialBody.GARGOYLE, 100),
			new Value<RacialBody, Integer>(RacialBody.CAT_MORPH, 20),
			new Value<RacialBody, Integer>(RacialBody.DOG_MORPH, 20),
			new Value<RacialBody, Integer>(RacialBody.WOLF_MORPH, 20),
			new Value<RacialBody, Integer>(RacialBody.HORSE_MORPH, 20))),

	// RODENTS:
	SQUIRREL_MORPH(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.SQUIRREL_MORPH, 100))),

	// AVIAN:
	HARPY(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.HARPY, 100)));

	private HashMap<RacialBody, Integer> selectableBodies;

	private RacialSelector(HashMap<RacialBody, Integer> selectableBodies) {
		this.selectableBodies = selectableBodies;
	}

	public static RacialSelector valueOfRace(Race race) {
		switch (race) {
			case ANGEL:
				return RacialSelector.ANGEL;
			case CAT_MORPH:
				return RacialSelector.CAT_MORPH;
			case COW_MORPH:
				return RacialSelector.COW_MORPH;
			case DEMON:
				return RacialSelector.DEMON;
			case GARGOYLE:
				return RacialSelector.GARGOYLE;
			case DOG_MORPH:
				return RacialSelector.DOG_MORPH;
			case ALLIGATOR_MORPH:
				return RacialSelector.ALLIGATOR_MORPH;
			case HARPY:
				return RacialSelector.HARPY;
			case HORSE_MORPH:
				return RacialSelector.HORSE_MORPH;
			case REINDEER_MORPH:
				return RacialSelector.REINDEER_MORPH;
			case HUMAN:
				return RacialSelector.HUMAN;
			case WOLF_MORPH:
				return RacialSelector.WOLF_MORPH;
			case SLIME:
				return RacialSelector.SLIME;
			case SQUIRREL_MORPH:
				return RacialSelector.SQUIRREL_MORPH;
		}
		return RacialSelector.HUMAN;
	}

	public HashMap<RacialBody, Integer> getSelectableBodies() {
		return selectableBodies;
	}
	
	public static RacialBody getBodyFromSelector(RacialSelector racialSelector) {
		return Util.getRandomObjectFromWeightedMap(racialSelector.selectableBodies);
	}
}
