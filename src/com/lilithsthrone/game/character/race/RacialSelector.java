package com.lilithsthrone.game.character.race;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.91
 * @version 0.1.91
 * @author Tukaima
 */
public enum RacialSelector {

	// HUMAN:
	HUMAN(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.HUMAN, Main.getProperties().subspeciesPreferencesMap.get(Subspecies.HUMAN)))),

	// ANGEL:
	ANGEL(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.ANGEL, Main.getProperties().subspeciesPreferencesMap.get(Subspecies.ANGEL)))),

	// DEMON:
	DEMON(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.DEMON, Main.getProperties().subspeciesPreferencesMap.get(Subspecies.DEMON)))),

	// BOVINES:
	COW_MORPH(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.COW_MORPH, Main.getProperties().subspeciesPreferencesMap.get(Subspecies.COW_MORPH)))),
	
	// CANINES:
	DOG_MORPH(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.DOG_MORPH, Main.getProperties().subspeciesPreferencesMap.get(Subspecies.DOG_MORPH)))),
	
	WOLF_MORPH(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.WOLF_MORPH, Main.getProperties().subspeciesPreferencesMap.get(Subspecies.WOLF_MORPH)))),

	// FELINES:
	CAT_MORPH(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.CAT_MORPH, Main.getProperties().subspeciesPreferencesMap.get(Subspecies.CAT_MORPH)))),

	// EQUINES:
	HORSE_MORPH(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.HORSE_MORPH, Main.getProperties().subspeciesPreferencesMap.get(Subspecies.HORSE_MORPH)))),

	REINDEER_MORPH(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.REINDEER_MORPH, Main.getProperties().subspeciesPreferencesMap.get(Subspecies.REINDEER_MORPH)))),

	// REPTILE:
	ALLIGATOR_MORPH(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.ALLIGATOR_MORPH, Main.getProperties().subspeciesPreferencesMap.get(Subspecies.ALLIGATOR_MORPH)))),
			
	// SLIMES:
	SLIME(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.SLIME, Main.getProperties().subspeciesPreferencesMap.get(Subspecies.SLIME)))),
	
	// GARGOYLES:
	GARGOYLE(Util.newHashMapOfValues(
			new Value<RacialBody, Integer>(RacialBody.GARGOYLE, Main.getProperties().subspeciesPreferencesMap.get(Subspecies.GARGOYLE)),
			new Value<RacialBody, Integer>(RacialBody.GARGOYLE_CAT, Main.getProperties().subspeciesPreferencesMap.get(Subspecies.GARGOYLE_CAT)),
			new Value<RacialBody, Integer>(RacialBody.GARGOYLE_DOG, Main.getProperties().subspeciesPreferencesMap.get(Subspecies.GARGOYLE_DOG)),
			new Value<RacialBody, Integer>(RacialBody.GARGOYLE_WOLF, Main.getProperties().subspeciesPreferencesMap.get(Subspecies.GARGOYLE_WOLF)),
			new Value<RacialBody, Integer>(RacialBody.GARGOYLE_HORSE, Main.getProperties().subspeciesPreferencesMap.get(Subspecies.GARGOYLE_HORSE))
			)),

	// RODENTS:
	SQUIRREL_MORPH(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.SQUIRREL_MORPH, Main.getProperties().subspeciesPreferencesMap.get(Subspecies.SQUIRREL_MORPH)))),

	// AVIAN:
	HARPY(Util.newHashMapOfValues(new Value<RacialBody, Integer>(RacialBody.HARPY, Main.getProperties().subspeciesPreferencesMap.get(Subspecies.HARPY))));

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
	
	// This checks only to see if the RacialSelector is filled with at least one non-zero value.
	public static boolean selectorUsable(Map<RacialBody, Integer> map) {
		for(@SuppressWarnings("unused") int i : map.values()) {
			for(Entry<RacialBody, Integer> entry : map.entrySet()) {
				if(entry.getValue() > 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static RacialBody getBodyFromSelector(RacialSelector racialSelector, RacialBody fallback) {
		if (selectorUsable(racialSelector.getSelectableBodies()) == true) {
			return Util.getRandomObjectFromWeightedMap(racialSelector.selectableBodies);
		} else {
			return fallback;
		}
	}
	
	public static RacialBody getBodyFromSelector(RacialSelector racialSelector) {
		return RacialSelector.getBodyFromSelector(racialSelector, RacialBody.HUMAN);
	}
}
