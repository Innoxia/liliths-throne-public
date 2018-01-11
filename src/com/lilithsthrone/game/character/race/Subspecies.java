package com.lilithsthrone.game.character.race;

/**
 * @since 0.1.91
 * @version 0.1.91
 * @author tukaima
 */
public enum Subspecies {

	// HUMAN:
	HUMAN(Race.HUMAN.getName(), Race.HUMAN, RacialBody.HUMAN, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical "+Race.HUMAN.getName()+"."),

	// ANGEL:
	ANGEL(Race.ANGEL.getName(), Race.ANGEL, RacialBody.ANGEL, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical "+Race.ANGEL.getName()+"."),

	// DEMON:
	DEMON(Race.DEMON.getName(), Race.DEMON, RacialBody.DEMON, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical "+Race.DEMON.getName()+"."),
	//IMP(Race.IMP.getName(), Race.DEMON, RacialBody.IMP, SubspeciesPreference.THREE_AVERAGE,
	//		"A smaller variety of "+Race.DEMON.getName()+"."),
	//IMP_ALPHA(Race.IMP_ALPHA.getName(), Race.DEMON, RacialBody.IMP_ALPHA, SubspeciesPreference.ONE_MINIMAL,
	//		"A more powerful form of the "+Race.IMP.getName()+"."),
	//LILIN(Race.LILIN.getName(), Race.DEMON, RacialBody.LILIN, SubspeciesPreference.ONE_MINIMAL,
	//		"A typical "+Race.LILIN.getName()+"."),

	// BOVINES:
	COW_MORPH(Race.COW_MORPH.getName(), Race.COW_MORPH, RacialBody.COW_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical bipedal "+Race.COW_MORPH.getName()+"."),
	//MINOTAUR(Race.MINOTAUR.getName(), Race.COW_MORPH, RacialBody.MINOTAUR, SubspeciesPreference.TWO_LOW,
	//		"An aggressive male-only variety of "+Race.COW_MORPH.getName()+"."),
	
	// CANINES:
	DOG_MORPH(Race.DOG_MORPH.getName(), Race.DOG_MORPH, RacialBody.DOG_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical bipedal "+Race.DOG_MORPH.getName()+"."),
	
	WOLF_MORPH(Race.WOLF_MORPH.getName(), Race.WOLF_MORPH, RacialBody.WOLF_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical bipedal "+Race.WOLF_MORPH.getName()+"."),
	
	//FOX_MORPH(Race.FOX_MORPH.getName(), Race.FOX_MORPH, RacialBody.FOX_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.FOX_MORPH.getName()+"."),
	// Kitsune have a separate racialbody than normal foxes but for subspecies preference purposes they should probably be considered the same as FOX_MORPH.
	// Just refer to the preferences for FOX_MORPH when building RacialSelectors instead of the preferences for KITSUNE.
	//FOX_TAILED("Pipefox", Race.FOX_MORPH, RacialBody.FOX_MORPH, SubspeciesPreference.TWO_LOW,
	//		"A "+Race.FOX_MORPH.getName()+" with a serpentine lower body, devoid of legs."),
	//FOX_TAUR("Yegan", Race.FOX_MORPH, RacialBody.FOX_MORPH, SubspeciesPreference.TWO_LOW,
	//		"A "+Race.FOX_MORPH.getName()+" with a bestial lower body that walks on four legs."),

	// FELINES:
	CAT_MORPH(Race.CAT_MORPH.getName(), Race.CAT_MORPH, RacialBody.CAT_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical bipedal "+Race.CAT_MORPH.getName()+"."),

	// EQUINES:
	HORSE_MORPH(Race.HORSE_MORPH.getName(), Race.HORSE_MORPH, RacialBody.HORSE_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical bipedal "+Race.HORSE_MORPH.getName()+"."),

	REINDEER_MORPH(Race.REINDEER_MORPH.getName(), Race.REINDEER_MORPH, RacialBody.REINDEER_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical bipedal "+Race.REINDEER_MORPH.getName()+"."),
	
	//CENTAUR(Race.CENTAUR.getName(), Race.HORSE_MORPH, RacialBody.CENTAUR, SubspeciesPreference.TWO_LOW,
	//		"A "+Race.HORSE_MORPH.getName()+" with a bestial lower body that walks on four legs."),

	// REPTILE:
	ALLIGATOR_MORPH(Race.ALLIGATOR_MORPH.getName(), Race.ALLIGATOR_MORPH, RacialBody.ALLIGATOR_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical bipedal "+Race.ALLIGATOR_MORPH.getName()+"."),
	//LIZARD_MORPH(Race.LIZARD_MORPH.getName(), Race.LIZARD_MORPH, RacialBody.LIZARD_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.LIZARD_MORPH.getName()+"."),
	//LAMIA(Race.LAMIA.getName(), Race.LIZARD_MORPH, RacialBody.LAMIA, SubspeciesPreference.TWO_LOW,
	//		"A "+Race.LIZARD_MORPH.getName()+" with a serpentine lower body, devoid of legs."),
	
	// AQUATIC:
	//SHARK_MORPH(Race.SHARK_MORPH.getName(), Race.SHARK_MORPH, RacialBody.SHARK_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.SHARK_MORPH.getName()+"."),
	//TIGER_SHARK(Race.TIGER_SHARK.getName(), Race.TIGER_SHARK, RacialBody.TIGER_SHARK, SubspeciesPreference.FIVE_ABUNDANT,
	//		"An extremely aggressive variety of "+Race.SHARK_MORPH.getName()+"."),
	
	// INSECTS:
	//BEE_MORPH(Race.BEE_MORPH.getName(), Race.BEE_MORPH, RacialBody.BEE_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.BEE_MORPH.getName()+"."),
	//ROYAL_BEE(Race.ROYAL_BEE.getName(), Race.BEE_MORPH, RacialBody.ROYAL_BEE, SubspeciesPreference.ZERO_NONE,
	//		"A bipedal "+Race.BEE_MORPH.getName()+" at the top of the bee-morph hierarchy."),
	//WASP_MORPH(Race.WASP_MORPH.getName(), Race.WASP_MORPH, RacialBody.WASP_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.WASP_MORPH.getName()+"."),
	
	// ARACHNIDS:
	//SPIDER_MORPH(Race.SPIDER_MORPH.getName(), Race.SPIDER_MORPH, RacialBody.SPIDER_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.SPIDER_MORPH.getName()+"."),
	//ARACHNE(Race.ARACHNE.getName(), Race.SPIDER_MORPH, RacialBody.ARACHNE, SubspeciesPreference.TWO_LOW,
	//		"A "+Race.SPIDER_MORPH.getName()+" with an arachnid lower body that walks on eight legs."),
			
	// DRAGONS:
	//DRAGON(Race.DRAGON.getName(), Race.DRAGON, RacialBody.DRAGON, SubspeciesPreference.FOUR_HIGH,
	//		"A typical bipedal "+Race.DRAGON.getName()+"."),
	//DRAGON_FUR("Fur "+Race.DRAGON.getName(), Race.DRAGON, RacialBody.DRAGON_FUR, SubspeciesPreference.ZERO_NONE,
	//		"A "+Race.DRAGON.getName()+" with a thick coat of fur, rather than scales."),
	//WYVERN(Race.WYVERN.getName(), Race.DRAGON, RacialBody.WYVERN, SubspeciesPreference.ONE_MINIMAL,
	//		"A bipedal "+Race.DRAGON.getName()+" with arms that act as wings."),
	//WYRM(Race.WYRM.getName(), Race.DRAGON, RacialBody.WYRM, SubspeciesPreference.ONE_MINIMAL,
	//		"A "+Race.DRAGON.getName()+" with a serpentine lower body, devoid of legs."),
	
	// SLIMES:
	SLIME(Race.SLIME.getName(), Race.SLIME, RacialBody.SLIME, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical "+Race.SLIME.getName()+"."),
	//SLIME_QUEEN(Race.SLIME_QUEEN.getName(), Race.SLIME, RacialBody.SLIME_QUEEN, SubspeciesPreference.ONE_MINIMAL,
	//		"A female-only variety of "+Race.SLIME.getName()+" which cannibalizes its lesser kin to increase its intelligence and body mass."),
	
	// GARGOYLES:
	GARGOYLE(Race.GARGOYLE.getName(), Race.GARGOYLE, RacialBody.GARGOYLE, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical "+Race.GARGOYLE.getName()+"."),
	GARGOYLE_CAT(Race.CAT_MORPH.getName()+" "+Race.GARGOYLE.getName(), Race.GARGOYLE, RacialBody.GARGOYLE_CAT, SubspeciesPreference.TWO_LOW,
			"A "+Race.GARGOYLE.getName()+" resembling a typical bipedal "+Race.CAT_MORPH.getName()+"."),
	GARGOYLE_DOG(Race.DOG_MORPH.getName()+" "+Race.GARGOYLE.getName(), Race.GARGOYLE, RacialBody.GARGOYLE_DOG, SubspeciesPreference.TWO_LOW,
			"A "+Race.GARGOYLE.getName()+" resembling a typical bipedal "+Race.DOG_MORPH.getName()+"."),
	GARGOYLE_WOLF(Race.WOLF_MORPH.getName()+" "+Race.GARGOYLE.getName(), Race.GARGOYLE, RacialBody.GARGOYLE_WOLF, SubspeciesPreference.TWO_LOW,
			"A "+Race.GARGOYLE.getName()+" resembling a typical bipedal "+Race.WOLF_MORPH.getName()+"."),
	GARGOYLE_HORSE(Race.HORSE_MORPH.getName()+" "+Race.GARGOYLE.getName(), Race.GARGOYLE, RacialBody.GARGOYLE_HORSE, SubspeciesPreference.TWO_LOW,
			"A "+Race.GARGOYLE.getName()+" resembling a typical bipedal "+Race.HORSE_MORPH.getName()+"."),

	// RODENTS:
	SQUIRREL_MORPH(Race.SQUIRREL_MORPH.getName(), Race.SQUIRREL_MORPH, RacialBody.SQUIRREL_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical bipedal "+Race.SQUIRREL_MORPH.getName()+"."),
	//MOUSE_MORPH(Race.MOUSE_MORPH.getName(), Race.MOUSE_MORPH, RacialBody.MOUSE_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.MOUSE_MORPH.getName()+"."),
	//RAT_MORPH(Race.RAT_MORPH.getName(), Race.MOUSE_MORPH, RacialBody.RAT_MORPH, SubspeciesPreference.TWO_LOW,
	//		"An extremely aggressive variety of "+Race.MOUSE_MORPH.getName()+"."),

	// AVIAN:
	HARPY(Race.HARPY.getName(), Race.HARPY, RacialBody.HARPY, SubspeciesPreference.FIVE_ABUNDANT,
			"A typical "+Race.HARPY.getName()+".");
	//TENGU(Race.TENGU.getName(), Race.TENGU, RacialBody.TENGU, SubspeciesPreference.TWO_LOW,
	//		"A hermetic kind of "+Race.HARPY.getName()+".");
	
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
