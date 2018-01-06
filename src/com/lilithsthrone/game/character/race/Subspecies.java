package com.lilithsthrone.game.character.race;

/**
 * @since 0.1.91
 * @version 0.1.91
 * @author tukaima
 */
public enum Subspecies {

	// HUMAN:
	HUMAN(Race.HUMAN.getName(), Race.HUMAN, RacialBody.HUMAN, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical "+Race.HUMAN.getName()),

	// ANGEL:
	ANGEL(Race.ANGEL.getName(), Race.ANGEL, RacialBody.ANGEL, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical "+Race.ANGEL.getName()),

	// DEMON:
	DEMON(Race.DEMON.getName(), Race.DEMON, RacialBody.DEMON, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical "+Race.DEMON.getName()),

	// BOVINES:
	COW_MORPH(Race.COW_MORPH.getName(), Race.COW_MORPH, RacialBody.COW_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical "+Race.COW_MORPH.getName()),
	
	// CANINES:
	DOG_MORPH(Race.DOG_MORPH.getName(), Race.DOG_MORPH, RacialBody.DOG_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical "+Race.DOG_MORPH.getName()),
	
	WOLF_MORPH(Race.WOLF_MORPH.getName(), Race.WOLF_MORPH, RacialBody.WOLF_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical "+Race.WOLF_MORPH.getName()),

	// FELINES:
	CAT_MORPH(Race.CAT_MORPH.getName(), Race.CAT_MORPH, RacialBody.CAT_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical "+Race.CAT_MORPH.getName()),

	// EQUINES:
	HORSE_MORPH(Race.HORSE_MORPH.getName(), Race.HORSE_MORPH, RacialBody.HORSE_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical "+Race.HORSE_MORPH.getName()),

	REINDEER_MORPH(Race.REINDEER_MORPH.getName(), Race.REINDEER_MORPH, RacialBody.REINDEER_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical "+Race.REINDEER_MORPH.getName()),

	// REPTILE:
	ALLIGATOR_MORPH(Race.ALLIGATOR_MORPH.getName(), Race.ALLIGATOR_MORPH, RacialBody.ALLIGATOR_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical "+Race.ALLIGATOR_MORPH.getName()),
			
	// SLIMES:
	SLIME(Race.SLIME.getName(), Race.SLIME, RacialBody.SLIME, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical "+Race.SLIME.getName()),
	
	// GARGOYLES:
	GARGOYLE(Race.GARGOYLE.getName(), Race.GARGOYLE, RacialBody.GARGOYLE, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical "+Race.GARGOYLE.getName()),
	GARGOYLE_CAT(Race.CAT_MORPH.getName()+" "+Race.GARGOYLE.getName(), Race.GARGOYLE, RacialBody.GARGOYLE_CAT, SubspeciesPreference.TWO_LOW,
			"A "+Race.GARGOYLE.getName()+" resembling a typical "+Race.CAT_MORPH.getName()),
	GARGOYLE_DOG(Race.DOG_MORPH.getName()+" "+Race.GARGOYLE.getName(), Race.GARGOYLE, RacialBody.GARGOYLE_DOG, SubspeciesPreference.TWO_LOW,
			"A "+Race.GARGOYLE.getName()+" resembling a typical "+Race.DOG_MORPH.getName()),
	GARGOYLE_WOLF(Race.WOLF_MORPH.getName()+" "+Race.GARGOYLE.getName(), Race.GARGOYLE, RacialBody.GARGOYLE_WOLF, SubspeciesPreference.TWO_LOW,
			"A "+Race.GARGOYLE.getName()+" resembling a typical "+Race.WOLF_MORPH.getName()),
	GARGOYLE_HORSE(Race.HORSE_MORPH.getName()+" "+Race.GARGOYLE.getName(), Race.GARGOYLE, RacialBody.GARGOYLE_HORSE, SubspeciesPreference.TWO_LOW,
			"A "+Race.GARGOYLE.getName()+" resembling a typical "+Race.HORSE_MORPH.getName()),

	// RODENTS:
	SQUIRREL_MORPH(Race.SQUIRREL_MORPH.getName(), Race.SQUIRREL_MORPH, RacialBody.SQUIRREL_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical "+Race.SQUIRREL_MORPH.getName()),

	// AVIAN:
	HARPY(Race.HARPY.getName(), Race.HARPY, RacialBody.HARPY, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical "+Race.HARPY.getName());

	private String name;
	private Race race;
	private RacialBody racialBody;
	private SubspeciesPreference subspeciesPreferenceDefault;
	private String description;

	private Subspecies(String name, Race race, RacialBody racialBody, SubspeciesPreference subspeciesPreferenceDefault, String description) {
		this.name = name;
		this.race = race;
		this.racialBody = racialBody;
		this.subspeciesPreferenceDefault = subspeciesPreferenceDefault;
		this.description = description;
	}

	public String getName() {
		return name;
	}
	
	public Race getRace() {
		return race;
	}
	
	public RacialBody getRacialBody() {
		return racialBody;
	}
	
	public SubspeciesPreference getSubspeciesPreferenceDefault() {
		return subspeciesPreferenceDefault;
	}
	
	public String getDescription() {
		return description;
	}
}
