package com.lilithsthrone.game.character.race;

import java.util.List;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.0
 * @version 0.1.87
 * @author Innoxia
 */
public enum Race {

	// HUMAN:
	HUMAN("human",
			"humans",
			
			"man",
			"woman",
			
			"men",
			"women",
			
			UtilText.parseFromXMLFile("characters/raceInfo", "HUMAN_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "HUMAN_ADVANCED"),
			
			Colour.RACE_HUMAN,
			Disposition.CIVILIZED,
			StatusEffect.PURE_HUMAN,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.MAIN)),
			true,
			0.5f,
			1,
			1,
			Attribute.DAMAGE_HUMAN,
			Attribute.RESISTANCE_HUMAN,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL),

	// ANGEL:
	ANGEL("angel",
			"angels",
			"angel",
			"angel",
			"angel",
			"angel",

			UtilText.parseFromXMLFile("characters/raceInfo", "ANGEL_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "ANGEL_ADVANCED"),

			Colour.CLOTHING_WHITE,
			Disposition.CIVILIZED,
			StatusEffect.PURE_HUMAN,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.MAIN), new ListValue<Attack>(Attack.SPELL)),
			false,
			0.25f,
			1,
			1,
			Attribute.DAMAGE_ANGEL,
			Attribute.RESISTANCE_ANGEL,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL),

	// DEMON:
	DEMON("demon",
			"demons",
			"incubus",
			"succubus",
			"incubi",
			"succubi",

			UtilText.parseFromXMLFile("characters/raceInfo", "DEMON_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "DEMON_ADVANCED"),

			Colour.RACE_DEMON,
			Disposition.CIVILIZED,
			StatusEffect.DEMON,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.SEDUCTION), new ListValue<Attack>(Attack.SPELL)),
			false,
			0.75f,
			2,
			3,
			Attribute.DAMAGE_DEMON,
			Attribute.RESISTANCE_DEMON,
			FurryPreference.MAXIMUM,
			FurryPreference.MAXIMUM),

	IMP("imp",
			"imps",
			"imp",
			"imp",
			"imps",
			"imps",

			UtilText.parseFromXMLFile("characters/raceInfo", "IMP_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "IMP_ADVANCED"),

			Colour.RACE_DEMON,
			Disposition.UNPREDICTABLE,
			StatusEffect.IMP,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.SEDUCTION), new ListValue<Attack>(Attack.SPELL)),
			true,
			0.75f,
			2,
			3,
			Attribute.DAMAGE_IMP,
			Attribute.RESISTANCE_IMP,
			FurryPreference.MAXIMUM,
			FurryPreference.MAXIMUM),

	// BOVINES:
	COW_MORPH("cow-morph",
			"cow-morphs",
			"cow-boy",
			"cow-girl",
			"cow-boys",
			"cow-girls",

			UtilText.parseFromXMLFile("characters/raceInfo", "COW_MORPH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "COW_MORPH_ADVANCED"),

			Colour.RACE_COW_MORPH,
			Disposition.CIVILIZED,
			StatusEffect.COW_MORPH,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.SPECIAL_ATTACK), new ListValue<Attack>(Attack.MAIN), new ListValue<Attack>(Attack.SEDUCTION)),
			true,
			0.5f,
			1,
			1,
			Attribute.DAMAGE_COW_MORPH,
			Attribute.RESISTANCE_COW_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL),

	// CANINES:
	DOG_MORPH("dog-morph",
			"dog-morphs",
			"dog-boy",
			"dog-girl",
			"dog-boys",
			"dog-girls",

			UtilText.parseFromXMLFile("characters/raceInfo", "DOG_MORPH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "DOG_MORPH_ADVANCED"),

			Colour.RACE_DOG_MORPH,
			Disposition.CIVILIZED,
			StatusEffect.DOG_MORPH,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.SPECIAL_ATTACK), new ListValue<Attack>(Attack.MAIN), new ListValue<Attack>(Attack.SEDUCTION)),
			true,
			0.5f,
			1,
			2,
			Attribute.DAMAGE_DOG_MORPH,
			Attribute.RESISTANCE_DOG_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL),

	WOLF_MORPH("wolf-morph",
			"wolf-morphs",
			"wolf-boy",
			"wolf-girl",
			"wolf-boys",
			"wolf-girls",

			UtilText.parseFromXMLFile("characters/raceInfo", "WOLF_MORPH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "WOLF_MORPH_ADVANCED"),
			
			Colour.RACE_WOLF_MORPH,
			Disposition.SAVAGE,
			StatusEffect.WOLF_MORPH,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.SPECIAL_ATTACK), new ListValue<Attack>(Attack.MAIN)),
			true,
			0.5f,
			1,
			2,
			Attribute.DAMAGE_WOLF_MORPH,
			Attribute.RESISTANCE_WOLF_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL),

	// FELINES:
	CAT_MORPH("cat-morph",
			"cat-morphs",
			"cat-boy",
			"cat-girl",
			"cat-boys",
			"cat-girls",

			UtilText.parseFromXMLFile("characters/raceInfo", "CAT_MORPH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "CAT_MORPH_ADVANCED"),

			Colour.RACE_CAT_MORPH,
			Disposition.CIVILIZED,
			StatusEffect.CAT_MORPH,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.SPECIAL_ATTACK), new ListValue<Attack>(Attack.MAIN), new ListValue<Attack>(Attack.SEDUCTION)),
			true,
			0.5f,
			1,
			2,
			Attribute.DAMAGE_CAT_MORPH,
			Attribute.RESISTANCE_CAT_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL),

	// EQUINE:
	HORSE_MORPH("horse-morph",
			"horse-morphs",
			"horse-boy",
			"horse-girl",
			"horse-boys",
			"horse-girls",

			UtilText.parseFromXMLFile("characters/raceInfo", "HORSE_MORPH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "HORSE_MORPH_ADVANCED"),

			Colour.RACE_HORSE_MORPH,
			Disposition.CIVILIZED,
			StatusEffect.HORSE_MORPH,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.MAIN)),
			true,
			0.5f,
			1,
			1,
			Attribute.DAMAGE_HORSE_MORPH,
			Attribute.RESISTANCE_HORSE_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL),

	
	 REINDEER_MORPH("reindeer-morph",
			"reindeer-morphs",
			"reindeer-boy",
			"reindeer-girl",
			"reindeer-boys",
			"reindeer-girls",

			UtilText.parseFromXMLFile("characters/raceInfo", "REINDEER_MORPH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "REINDEER_MORPH_ADVANCED"),
		 
	  Colour.RACE_REINDEER_MORPH,
			Disposition.CIVILIZED,
			StatusEffect.REINDEER_MORPH,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.SPECIAL_ATTACK), new ListValue<Attack>(Attack.MAIN)),
			true,
			0.5f,
			1,
			2,
			Attribute.DAMAGE_REINDEER_MORPH,
			Attribute.RESISTANCE_REINDEER_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL),
			

	SQUIRREL_MORPH("squirrel-morph",
			"squirrel-morphs",
			"squirrel-boy",
			"squirrel-girl",
			"squirrel-boys",
			"squirrel-girls",

			UtilText.parseFromXMLFile("characters/raceInfo", "SQUIRREL_MORPH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "SQUIRREL_MORPH_ADVANCED"),

			Colour.RACE_SQUIRREL_MORPH,
			Disposition.CIVILIZED,
			StatusEffect.SQUIRREL_MORPH,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.SPECIAL_ATTACK), new ListValue<Attack>(Attack.MAIN)),
			true,
			0.5f,
			1,
			2,
			Attribute.DAMAGE_SQUIRREL_MORPH,
			Attribute.RESISTANCE_SQUIRREL_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL),

	RAT_MORPH("rat-morph",
			"rat-morphs",
			"rat-boy",
			"rat-girl",
			"rat-boys",
			"rat-girls",

			UtilText.parseFromXMLFile("characters/raceInfo", "RAT_MORPH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "RAT_MORPH_ADVANCED"),

			Colour.RACE_RAT_MORPH,
			Disposition.NEUTRAL,
			StatusEffect.RAT_MORPH,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.SPECIAL_ATTACK), new ListValue<Attack>(Attack.MAIN)),
			true,
			0.5f,
			1,
			4,
			Attribute.DAMAGE_RAT_MORPH,
			Attribute.RESISTANCE_RAT_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL),

	RABBIT_MORPH("rabbit-morph",
			"rabbit-morphs",
			"rabbit-boy",
			"rabbit-girl",
			"rabbit-boys",
			"rabbit-girls",

			UtilText.parseFromXMLFile("characters/raceInfo", "RABBIT_MORPH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "RABBIT_MORPH_ADVANCED"),

			Colour.RACE_RABBIT_MORPH,
			Disposition.NEUTRAL,
			StatusEffect.RABBIT_MORPH,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.SPECIAL_ATTACK), new ListValue<Attack>(Attack.MAIN)),
			true,
			0.5f,
			2,
			8,
			Attribute.DAMAGE_RABBIT_MORPH,
			Attribute.RESISTANCE_RABBIT_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL),
	
	BAT_MORPH("bat-morph",
			"bat-morphs",
			"bat-boy",
			"bat-girl",
			"bat-boys",
			"bat-girls",

			UtilText.parseFromXMLFile("characters/raceInfo", "BAT_MORPH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "BAT_MORPH_ADVANCED"),

			Colour.RACE_BAT_MORPH,
			Disposition.NEUTRAL,
			StatusEffect.BAT_MORPH,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.SPECIAL_ATTACK), new ListValue<Attack>(Attack.MAIN)),
			true,
			0.5f,
			1,
			2,
			Attribute.DAMAGE_BAT_MORPH,
			Attribute.RESISTANCE_BAT_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL),
	
	ALLIGATOR_MORPH("alligator-morph",
			"alligator-morphs",
			"alligator-boy",
			"alligator-girl",
			"alligator-boys",
			"alligator-girls",

			UtilText.parseFromXMLFile("characters/raceInfo", "ALLIGATOR_MORPH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "ALLIGATOR_MORPH_ADVANCED"),

			Colour.RACE_ALLIGATOR_MORPH,
			Disposition.NEUTRAL,
			StatusEffect.ALLIGATOR_MORPH,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.SPECIAL_ATTACK), new ListValue<Attack>(Attack.MAIN)),
			true,
			0.5f,
			1,
			4,
			Attribute.DAMAGE_ALLIGATOR_MORPH,
			Attribute.RESISTANCE_ALLIGATOR_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL),

	// SLIME:
	SLIME("slime",
			"slimes",
			"slime",
			"slime",
			"slimes",
			"slimes",

			UtilText.parseFromXMLFile("characters/raceInfo", "SLIME_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "SLIME_ADVANCED"),

			Colour.RACE_SLIME,
			Disposition.NEUTRAL,
			StatusEffect.SLIME,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.SEDUCTION)),
			true,
			0.5f,
			1,
			1,
			Attribute.DAMAGE_SLIME,
			Attribute.RESISTANCE_SLIME,
			FurryPreference.MAXIMUM,
			FurryPreference.MAXIMUM),

	// AVIAN:
	HARPY("harpy",
			"harpies",
			"harpy",
			"harpy",
			"harpies",
			"harpies",

			UtilText.parseFromXMLFile("characters/raceInfo", "HARPY_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "HARPY_ADVANCED"),
			
			Colour.RACE_HARPY,
			Disposition.NEUTRAL,
			StatusEffect.HARPY,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.SPECIAL_ATTACK), new ListValue<Attack>(Attack.SEDUCTION)),
			true,
			0.5f,
			3,
			4,
			Attribute.DAMAGE_HARPY,
			Attribute.RESISTANCE_HARPY,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL),
	

	// ELEMENTALS:
	ELEMENTAL_EARTH("earth elemental",
			"earth elementals",
			"earth elemental",
			"earth elemental",
			"earth elementals",
			"earth elementals",

			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_EARTH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_EARTH_ADVANCED"),

			Colour.SPELL_SCHOOL_EARTH,
			Disposition.NEUTRAL,
			StatusEffect.ELEMENTAL_EARTH,
			Util.newArrayListOfValues( new ListValue<Attack>(Attack.MAIN), new ListValue<Attack>(Attack.SEDUCTION)),
			false,
			0.5f,
			1,
			1,
			Attribute.DAMAGE_ELEMENTAL_EARTH,
			Attribute.RESISTANCE_ELEMENTAL_EARTH,
			FurryPreference.MAXIMUM,
			FurryPreference.MAXIMUM),
	
	ELEMENTAL_WATER("water elemental",
			"water elementals",
			"water elemental",
			"water elemental",
			"water elementals",
			"water elementals",

			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_WATER_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_WATER_ADVANCED"),

			Colour.SPELL_SCHOOL_WATER,
			Disposition.NEUTRAL,
			StatusEffect.ELEMENTAL_WATER,
			Util.newArrayListOfValues( new ListValue<Attack>(Attack.MAIN), new ListValue<Attack>(Attack.SEDUCTION)),
			false,
			0.5f,
			1,
			1,
			Attribute.DAMAGE_ELEMENTAL_WATER,
			Attribute.RESISTANCE_ELEMENTAL_WATER,
			FurryPreference.MAXIMUM,
			FurryPreference.MAXIMUM),
	
	ELEMENTAL_AIR("air elemental",
			"air elementals",
			"air elemental",
			"air elemental",
			"air elementals",
			"air elementals",

			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_AIR_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_AIR_ADVANCED"),

			Colour.SPELL_SCHOOL_AIR,
			Disposition.NEUTRAL,
			StatusEffect.ELEMENTAL_AIR,
			Util.newArrayListOfValues( new ListValue<Attack>(Attack.MAIN), new ListValue<Attack>(Attack.SEDUCTION)),
			false,
			0.5f,
			1,
			1,
			Attribute.DAMAGE_ELEMENTAL_AIR,
			Attribute.RESISTANCE_ELEMENTAL_AIR,
			FurryPreference.MAXIMUM,
			FurryPreference.MAXIMUM),
	
	ELEMENTAL_FIRE("fire elemental",
			"fire elementals",
			"fire elemental",
			"fire elemental",
			"fire elementals",
			"fire elementals",

			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_FIRE_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_FIRE_ADVANCED"),

			Colour.SPELL_SCHOOL_FIRE,
			Disposition.NEUTRAL,
			StatusEffect.ELEMENTAL_FIRE,
			Util.newArrayListOfValues( new ListValue<Attack>(Attack.MAIN), new ListValue<Attack>(Attack.SEDUCTION)),
			false,
			0.5f,
			1,
			1,
			Attribute.DAMAGE_ELEMENTAL_FIRE,
			Attribute.RESISTANCE_ELEMENTAL_FIRE,
			FurryPreference.MAXIMUM,
			FurryPreference.MAXIMUM),
	
	ELEMENTAL_ARCANE("arcane elemental",
			"arcane elementals",
			"arcane elemental",
			"arcane elemental",
			"arcane elementals",
			"arcane elementals",

			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_ARCANE_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_ARCANE_ADVANCED"),

			Colour.SPELL_SCHOOL_ARCANE,
			Disposition.NEUTRAL,
			StatusEffect.ELEMENTAL_ARCANE,
			Util.newArrayListOfValues( new ListValue<Attack>(Attack.MAIN), new ListValue<Attack>(Attack.SEDUCTION)),
			false,
			0.5f,
			1,
			1,
			Attribute.DAMAGE_ELEMENTAL_ARCANE,
			Attribute.RESISTANCE_ELEMENTAL_ARCANE,
			FurryPreference.MAXIMUM,
			FurryPreference.MAXIMUM),
	
	;

	/*
	 * // INSECTS: BEE_MORPH("bee-morph",
	 * 
	 * "<p>Bee-morphs are one of the many demi-human races now populating the world."
	 * +
	 * " The vast majority of bee-morphs live outside of society in huge hives, consisting of up to 200 individuals."
	 * +
	 * " Each hive is governed by a single King or Queen bee, who commands the complete loyalty of the hive's members."
	 * +
	 * " The hive is constructed and guarded by the hive's bee-boys, who will aggressively attack anyone who strays too close.</p>"
	 * 
	 * +
	 * "<p>Bee-girls are responsible for gathering nectar, which they do by fucking the many phallic demonic plants."
	 * +
	 * " Their body then transforms the nectar into honey-milk, and as it does so, the bee-girl grows incredibly horny."
	 * +
	 * " When her abdomen is swollen with honey-milk, she will return to her hive to be fucked, secreting the sweet liquid from her breasts and pussy.</p>"
	 * 
	 * +
	 * "<p>Bee-morphs are only aggressive when defending their hive, and will try to avoid contact with strangers wherever possible."
	 * +
	 * " If forced into a fight, they will not hesitate to use the powerful stinger found on the end of their abdomen, the venom of which"
	 * +
	 * " will drive a person into a state of uncontrollable lust. After victory, a female bee-morph will"
	 * +
	 * " use the opportunity to deposit her eggs in the attacker, using an ovipositor located in their abdomen.</p>"
	 * ,
	 * 
	 * "Bee-boys are very rarely seen away from their hive.",
	 * 
	 * "Bee-girls can commonly be found gathering nectar near their hive.",
	 * 
	 * RacialBody.BEE_MORPH, Genus.INSECT, Disposition.NEUTRAL,
	 * StatusEffect.BEE_MORPH, Utilities.newArrayListOfValues(new
	 * ListValue<Attack>(Attack.SPECIAL_ATTACK), new
	 * ListValue<Attack>(Attack.MELEE), new
	 * ListValue<Attack>(Attack.SEDUCTION)), false), ROYAL_BEE("royal bee",
	 * 
	 * "<p>Royal bees are a humanoid monster race, and are referred to as Queen or King bees."
	 * +
	 * " A Royal bee can be found leading a colony of bee-morphs, and they almost never leave the safety of their hive."
	 * +
	 * " Their responsibility is to keep the hive functioning, and they command the complete loyalty and devotion of their colony."
	 * +
	 * " They spend most of their time seeing to the needs of the hive's bee-girls, and along with the bee-boys, fuck and pleasure the bee-girls in order to collect their honey-milk."
	 * +
	 * " Royal bees are able to convert honey-milk into royal jelly in their abdomens, which they are able to excrete as a thick, sticky cum.</p>"
	 * 
	 * +
	 * "<p>Royal bees are created by transforming a bee-morph through use of Royal jelly."
	 * +
	 * " When a hive grows too large for one Royal bee to manage, a bee-morph is chosen to be transformed into a new Queen or King bee."
	 * +
	 * " The hive's current Queen or King will mate with the chosen bee-morph, forcing them to drink their Royal jelly."
	 * +
	 * " This causes the chosen bee-morph to transform into a new Royal bee, who will then leave the hive, taking about a third of the colony's members with them.</p>"
	 * 
	 * +
	 * "<p>Royal bees almost never venture out of their hives, but will attack anyone that manages to break through the colony's bee-boy guards."
	 * +
	 * " Those that are foolish enough to enter an active bee hive are never heard from again. It has been speculated that such intruders are forcefully"
	 * + " transformed into another member of the hive.</p>",
	 * 
	 * "King bees, like all royal bees, will never leave their hive.",
	 * 
	 * "Queen bees, like all royal bees, will never leave their hive.",
	 * 
	 * RacialBody.ROYAL_BEE, Genus.INSECT, Disposition.SAVAGE,
	 * StatusEffect.ROYAL_BEE, Utilities.newArrayListOfValues(new
	 * ListValue<Attack>(Attack.SPECIAL_ATTACK), new
	 * ListValue<Attack>(Attack.MELEE), new
	 * ListValue<Attack>(Attack.SEDUCTION)), false),
	 */

	/*
	 * TIGER_MORPH("tiger-morph",
	 * 
	 * "<p>Tiger-morphs are a dangerous humanoid monster race, primarily found in the demonic jungle."
	 * +
	 * " Tiger-morphs are extremely aggressive, and as a result have been driven out of Dominion's society."
	 * +
	 * " They spend most of their lives as solitary hunters, and roam their territory looking for individuals to prey upon."
	 * +
	 * " Once a suitable target is found, the tiger-morph will stalk them, remaining concealed until the perfect moment to pounce."
	 * +
	 * " Any unfortunate person falling victim to a tiger-morph will not only be robbed, but will often also be subjected to extremely dominant and humiliating rape.</p>"
	 * 
	 * +
	 * "<p>Tiger-morphs are always willing to assert their dominance through sex, but females will often prefer to force their prey to pleasure them orally,"
	 * +
	 * " refusing to let them penetrate her. When a magic storm hits, tiger-morphs become extremely promiscuous, and will go on a tireless lust-fuelled rampage,"
	 * + " the females now mating with whatever they can find.</p>",
	 * 
	 * "Tiger-boys will humiliate and rape their prey.",
	 * 
	 * "Tiger-girls will force their prey to pleasure them orally.",
	 * 
	 * RacialBody.TIGER_MORPH, Genus.FELINE, Disposition.SAVAGE,
	 * StatusEffect.TIGER_MORPH, Utilities.newArrayListOfValues(new
	 * ListValue<Attack>(Attack.SPECIAL_ATTACK), new
	 * ListValue<Attack>(Attack.MELEE), new
	 * ListValue<Attack>(Attack.SEDUCTION)), true),
	 */

	/*
	 * CENTAUR("centaur",
	 * 
	 * "<p>Centaurs are an unpredictable monster race that is rarely seen in Dominion."
	 * +
	 * " Centaurs are usually quite civilized, but they can get extremely territorial, and as a result they don't feel comfortable living in Dominion."
	 * +
	 * " Outside of Dominion, centaurs group up to form herds of up to fifty individuals, although they are rarely all together at once,"
	 * + " instead wandering their territory in search of intruders.</p>"
	 * 
	 * +
	 * "<p>There will usually only be a handful of males in the herd, who are responsible for taking decisions on behalf of the entire herd."
	 * +
	 * " All the females are part of their harem, and are happy to submit to being bred whenever the males feel like using them."
	 * +
	 * " Centaurs consider horse-morphs as having failed to reach their full potential, and each race strongly dislikes the other.</p>"
	 * 
	 * +
	 * "<p>Centaurs are very aggressive towards intruders, and will attack anyone they consider to be encroaching on their territory."
	 * +
	 * " When outside of their territory, they are quite civilized, and will prefer to avoid conflict where possible.</p>"
	 * 
	 * +
	 * "<p>Male centaurs will try and impregnate anything they can catch on their territory, while the females will only want to breed with their herd's leaders."
	 * +
	 * " Centaurs get extremely affected by magic-storms, and females will sink into an extremely powerful heat, desperately seeking anyone and anything to fuck"
	 * +
	 * " her pregnant. Similarly, males will enter a powerful rut, and while under the storm's effects, their only concern is breeding as many people"
	 * + " as they can catch.</p>",
	 * 
	 * "Male centaurs are extremely dominant, and will attack anyone on their territory."
	 * ,
	 * 
	 * "Female centaurs are slightly more forgiving than their male counterparts, but will not hesitate to fight any intruders."
	 * ,
	 * 
	 * RacialBody.CENTAUR, Genus.EQUINE, Disposition.UNPREDICTABLE,
	 * StatusEffect.CENTAUR, Utilities.newArrayListOfValues(new
	 * ListValue<Attack>(Attack.SPECIAL_ATTACK), new
	 * ListValue<Attack>(Attack.MELEE)), true),
	 */

	/*
	 * SLIME_QUEEN("slime queen",
	 * 
	 * "<p>A slime queen is a dangerous monster, having lost themselves to the desire for more cum."
	 * +
	 * " Slime queens will seek to absorb other slimes into their bodies, breaking their minds and forcing them to become a part of her."
	 * +
	 * " Due to this threat, normal slimes will group together to drive off slime queens, and as a result, slime queens are typically found isolated near bodies of water they have claimed as their home."
	 * +
	 * " Whereas normal slimes are quite stupid, the slime queen is able to harness the intelligence of all the slimes she has absorbed, making her a formidable threat."
	 * +
	 * "A slime queen will wander from her home to hunt down any individual she can find."
	 * +
	 * " Once she has found a victim, she will attempt to smother and seduce her prey and then feed off of them."
	 * +
	 * " Slime queens represent a threat to all races, and as a result Lilith's enforcers have driven them out of Dominion.</p>"
	 * 
	 * +
	 * "<p>A slime queen looks like a group of slimes all huddling together, with a particularly attractive individual at the centre."
	 * +
	 * " This is an illusion however, and a closer inspection will reveal all the slimes are linked together into one slime queen."
	 * +
	 * " Each outer slime will take on the appearance of the slime that the queen absorbed, although none are permitted to be more attractive than the queen herself.</p>"
	 * ,
	 * 
	 * "Slime queens are only ever female in appearance.",
	 * 
	 * "Slime queens are only ever female in appearance.",
	 * 
	 * RacialBody.SLIME_QUEEN, Genus.SLIME, Disposition.SAVAGE,
	 * StatusEffect.SLIME_QUEEN, Utilities.newArrayListOfValues(new
	 * ListValue<Attack>(Attack.SPECIAL_ATTACK), new
	 * ListValue<Attack>(Attack.SEDUCTION)), false),
	 */

	/*
	 * TENGU("tengu", INACCURATE
	 * "<p>Tengus are a female-only humanoid monster race." +
	 * " Unlike other monster races, tengus have not been driven out of Dominion society, but instead actively choose to live in solitude."
	 * +
	 * " They are, without exception, extremely intelligent, and have an excellent grasp of magic."
	 * +
	 * " They make their nests as high as they can, and try to avoid contact with all other races."
	 * +
	 * " When they are forced to interact with others, tengus display remarkable understanding and kindness, and will almost always avoid conflict where possible.</p>"
	 * ,
	 * 
	 * "Tengu males look like females, but are less attractive and have a small cock."
	 * ,
	 * 
	 * "Tengu females are extremely attractive and only really care about themselves."
	 * ,
	 * 
	 * RacialBody.TENGU, Genus.AVIAN, Disposition.UNPREDICTABLE,
	 * StatusEffect.TENGU, Utilities.newArrayListOfValues(new
	 * ListValue<Attack>(Attack.SPECIAL_ATTACK), new
	 * ListValue<Attack>(Attack.SEDUCTION)), false);
	 */

	// TODO:
	/*
	 * NOT DONE
	 * -------------------------------------------------------------------------
	 * ------------------------------- // AQUATIC: SHARK_MORPH("shark morph",
	 * "",
	 * 
	 * "Shark morphs are a humanoid race most commonly found in the city of Dominion."
	 * +
	 * " Shark morphs are typically very dominant, but can control their aggression."
	 * +
	 * " They like hanging out around bodies of water, as there is almost nothing a shark morph likes more than swimming."
	 * +
	 * " When a shark morph sees something they like, they will do anything it takes to get it."
	 * +
	 * " Male shark morphs will attempt to build a harem of females, having to beat each female he wants to claim in a fight in order to show his dominance."
	 * ,
	 * 
	 * "Shark morphs are humanoid, with a human body and face." +
	 * " Their legs and arms are covered in smooth grey skin, ending at the thigh and upper arm."
	 * +
	 * " A huge shark-like tail grows from above their ass, allowing them to swim at incredible speeds."
	 * + " All shark morphs are incredibly fit from all the swimming they do."
	 * + " Shark morphs have sharp pointed teeth that they can retract at will."
	 * +
	 * " Females typically have very small breasts, streamlining them for swimming."
	 * + " Males have a shark-like cock.",
	 * 
	 * "Shark morphs are easy to provoke, and are always eager to fight." +
	 * " They typically do not know any magic, and will prefer fighting using brute force."
	 * ,
	 * 
	 * "Female shark morphs will typically be part of a harem, being claimed by a strong male."
	 * +
	 * " Females in a harem will only mate with their leader, unless defeated in combat by another individual."
	 * +
	 * " Each shark morph pregnancy will result in up to three shark morphs, which will rapidly reach full maturity and leave to make their own way in Dominion."
	 * ,
	 * 
	 * RacialBody.SHARK_MORPH, Genus.AQUATIC, Disposition.CIVILIZED,
	 * StatusEffect.SHARK_MORPH, false, false), TIGER_SHARK("tiger shark", "",
	 * 
	 * "Tiger sharks are a humanoid monster race." +
	 * " Tiger sharks are extremely dominant and aggressive, and will attack almost anything on sight."
	 * + " As a result, Tiger sharks have been driven out of Dominion society."
	 * +
	 * " Like shark morphs, they like hanging out around bodies of water, as they love swimming."
	 * +
	 * " They will sometimes lurk just beneath the water, waiting for a clueless passer-by to ambush."
	 * +
	 * " If a harem of hark morphs are foolish enough to stray from the safety of Dominion, a male Tiger shark will seek to defeat their leader and mate with all the harem's females."
	 * +
	 * " The Tiger shark will also typically forcefully transform the harem's male leader into a female shark morph before breeding her."
	 * ,
	 * 
	 * "Tiger sharks are humanoid monsters, with smooth shark-like skin covering their entire bodies."
	 * +
	 * " Their stomach and breasts are a creamy-white, while the rest of their skin is grey with dark-grey stripes, like a tiger."
	 * +
	 * " A huge shark-like tail grows from above their ass, allowing them to swim at incredible speeds."
	 * +
	 * " They have an anthropomorphic shark-like face, with sharp pointed teeth that they can retract at will."
	 * +
	 * " Females typically have very small breasts, streamlining them for swimming."
	 * + " Males have a huge shark-like cock.",
	 * 
	 * "Tiger sharks will attack almost anything they see." +
	 * " They typically do not know any magic, and will prefer fighting using brute force."
	 * ,
	 * 
	 * "Female Tiger sharks will seek to breed with any strong male they can catch."
	 * + " Males will likewise seek to impregnate any female they see." +
	 * " Each Tiger shark pregnancy will result in up to three Tiger sharks, which will rapidly reach full maturity and leave to find a body of water to claim as their own."
	 * ,
	 * 
	 * RacialBody.TIGER_SHARK, Genus.AQUATIC, Disposition.SAVAGE,
	 * StatusEffect.TIGER_SHARK, true, false);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * MOUSE_MORPH("mouse morph",
	 * 
	 * "Mouse-morphs are one of the most common races inhabiting Dominion." +
	 * "Usually found in colonies numbering 10-20 individuals, mouse-morphs typically inhabit basements or burrows that they have dug out themselves."
	 * +
	 * "Mouse-morphs do everything as a cohesive unit with their colony, be it eating, exploring or fucking."
	 * +
	 * " Mouse-morphs are typically quite shy, preferring to avoid any sort of interaction with strangers that isn't completely necessary."
	 * +
	 * "A colony of mouse-morphs will have no clear leader, but work together as a whole to get things done."
	 * ,
	 * 
	 * "The average mouse-morph is about 5' tall, and possesses youthful features and a lithe frame."
	 * +
	 * " Like the other morphs inhabiting Dominion, their face and body remain human-looking."
	 * +
	 * "A pair of mouse-like ears and a thin tail are the extremities that betray their race on first glance."
	 * +
	 * "The front teeth of most mouse-morphs are slightly larger than an average human's, although not by much."
	 * +
	 * "Their arms and legs are very delicate, especially on females, and have a soft layer of fur running up to their elbows and inner-thighs."
	 * +
	 * "The hands and feet are humanoid, but with little claws on the end of each digit, and the undersides have pads."
	 * ,
	 * 
	 * "Mouse-morphs are very timid, and will usually try to run from combat when able."
	 * + "They are completely unable to harness any demon magic." +
	 * "If forced to fight, a mouse-morph will usually rely on presenting themselves as a submissive sex-toy, hoping to fog the enemies' mind with lust."
	 * ,
	 * 
	 * "Mouse-morphs, along with rat-morphs, are the most prolific breeders in Dominion."
	 * +
	 * "Colonies of mouse-morphs are constantly engaged in sexual intercourse, trying to impregnate their friends."
	 * +
	 * "If an individual finds a partner outside of their colony, they will be expected to bring them back to the colony so everyone can try and breed their new partner."
	 * ,
	 * 
	 * RacialBody.MOUSE_MORPH, Genus.RODENT, Disposition.CIVILIZED,
	 * StatusEffect.MOUSE_MORPH),
	 * 
	 * RAT_MORPH("rat morph",
	 * 
	 * "Rat-morphs are the larger and more aggressive cousins of the mouse-morph."
	 * +
	 * " Found primarily in the undercity of Submission, a rat-morph is a rare sight on the surface."
	 * +
	 * " Due to their corrupted and bestial nature, rat-morphs are considered one of the dangerous races."
	 * +
	 * " Despite this, they are the weakest of the dangerous races, and are only a threat when hunting in groups."
	 * +
	 * "Rat-morphs are aggressive, territorial and cunning, and all they ever seem to think about is breeding."
	 * +
	 * " Rat-morphs have a tendency to form gangs around a strong leader, and will submit to their commands for as long as they remain strong."
	 * +
	 * " The surface dwellers of Dominion do not tolerate their presence, and as such, rat-morphs are found almost exclusively in the undercity of Submission."
	 * +
	 * " Occasionally, a gang of rat-morphs will venture to the surface, looking for isolated individuals to overwhelm and rape."
	 * +
	 * " These unlucky victims will be claimed by the leader first, with the rest of the gang soon to follow."
	 * ,
	 * 
	 * "A rat-morph usually stands at around 6\", with gang leaders typically being slightly taller."
	 * +
	 * " Rat-morphs are covered in a layer of hair, which thins out on their stomach and chest."
	 * +
	 * " Their feet and hands, although humanoid, end in little claws, and their palms and soles have soft pads."
	 * +
	 * " They possess a thick rat-like tail and their faces and ears are similarly morphed into the look of a rat."
	 * + " Females have three pairs of breasts." +
	 * " The average male cock size is 10\".",
	 * 
	 * "Alone, a rat-morph is cowardly, and will likely try to avoid combat." +
	 * " If backed into a corner, however, even a solo rat-morph will fight ferociously."
	 * +
	 * " Most of the time, rat-morphs will be with their gang, and will happily seek out individuals of any species to overwhelm and rape."
	 * +
	 * " Rat-morphs cannot use magic, and will instead rely on physical attacks."
	 * ,
	 * 
	 * "Rat-morphs, along with mouse-morphs, are the most prolific breeders." +
	 * "Gangs of rat-morphs are constantly engaged in sexual intercourse, trying to impregnate each other."
	 * +
	 * "Once they grow tired of fucking one another, the gang will seek out other rat-morph gangs to fight and fuck, and will sometimes even venture to the surface to breed an unlucky citizen."
	 * ,
	 * 
	 * RacialBody.RAT_MORPH, Genus.RODENT, Disposition.UNPREDICTABLE,
	 * StatusEffect.RAT_MORPH),
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // They bully bee-girls and drink their honey-milk WASP_MORPH(
	 * "wasp morph",
	 * 
	 * "Wasp morphs are aggressive monsters that inhabit the jungle." +
	 * " Unlike bee morphs, wasp morphs are exclusively male, except for the colonies' leader, which is female."
	 * +
	 * " Wasp morphs are regarded as annoying pests by every other race, and as such are driven out of Dominion and into the jungle."
	 * +
	 * " Each wasp morph is part of a colony, which will consist of up to fifty individuals, all led by a female queen."
	 * +
	 * " Wasp morphs will typically attack others on sight, but will sometimes avoid individuals that look too strong to fight."
	 * ,
	 * 
	 * "Wasp morphs are humanoid monsters, covered in yellow and black chitin."
	 * +
	 * " They have a humanoid face, but have large insect-like eyes and mandibles on either side of their otherwise normal mouth."
	 * +
	 * " The most notable feature of a wasp morph is the huge yellow-and-black abdomen sprouting from above their ass."
	 * +
	 * " A pair of antennae grow from underneath their hair, which is invariably a shiny yellow and black."
	 * + " Wasp morphs have a shiny black penis, typically 12\" long.",
	 * 
	 * "Like bee morphs, wasp morphs are unable to use magic, though the queen is able to use some spells."
	 * +
	 * " Wasp morphs will always use physical attacks to attempt to beat down their opponents."
	 * +
	 * " Their abdomen gives them the ability to deliver a powerful sting, which delivers a pain-inducing venom to their victims."
	 * ,
	 * 
	 * "Wasp morphs reproduce by willing being filled with eggs from their queen by use of her ovipositor."
	 * +
	 * " The queen fertilises her eggs by selecting members of the colony to fuck."
	 * +
	 * " If anyone is foolish enough to trespass into the colonies' hive, the queen will invariably forcefully transform the trespasser into a wasp morph drone."
	 * ,
	 * 
	 * RacialBody.WASP_MORPH, Genus.INSECT, Disposition.SAVAGE,
	 * StatusEffect.WASP_MORPH),
	 * 
	 * 
	 * 
	 * LIZARD_MORPH("lizard morph",
	 * 
	 * "Lizard morphs are a humanoid race most commonly found in the city of Dominion."
	 * +
	 * " Lizard morphs are typically very laid back, and are one of the most docile races of Dominion."
	 * +
	 * " They enjoy heat, and will spend a large portion of the day sunbathing."
	 * ,
	 * 
	 * "Lizard morphs are humanoid, with a human body and face." +
	 * " Their legs and arms are covered in scaly skin, ending at the thigh and upper arm."
	 * + " A large lizard-like tail grows from above their ass." +
	 * " Females typically have small breasts." +
	 * " Males have a lizard-like cock.",
	 * 
	 * "Lizard morphs are slow to anger, but will defend themselves from an attacker."
	 * +
	 * " They typically do not know any magic, and will prefer fighting using brute force or seduction."
	 * ,
	 * 
	 * " Lizard morphs breed by laying eggs, that can be fertilised by any race."
	 * +
	 * " Once the eggs hatch, the new Lizard morphs will rapidly reach full maturity and leave to make their own way in Dominion."
	 * ,
	 * 
	 * RacialBody.LIZARD_MORPH, Genus.REPTILE, Disposition.CIVILIZED,
	 * StatusEffect.LIZARD_MORPH), LAMIA("lamia",
	 * 
	 * "Lamias are a monster found everywhere except Dominion." +
	 * " Lamias are typically very solitary, and the only time they gather is for reproduction."
	 * +
	 * " They are very dangerous and aggressive, and will attack travellers unprovoked."
	 * +
	 * " Lamias are known for using hypnotism to force their prey to do whatever they want."
	 * + " Due to this behaviour, Lamias have been forced out of Dominion.",
	 * 
	 * "Lamias are humanoid/animal monsters, having a normal-looking Lizard morph upper body, but instead of legs and a tail they possess the lower body of a snake."
	 * ,
	 * 
	 * "Lamias are very aggressive, and will attack anyone they consider to be encroaching on their territory."
	 * +
	 * " They typically know some spells related to mind control, and will prefer to use these and seduction rather than brute force."
	 * ,
	 * 
	 * "Female Lamias will use their prey to breed with, forcing them to cum on demand by using hypnotism."
	 * + " Males will try to impregnate anyone they catch.",
	 * 
	 * RacialBody.LAMIA, Genus.REPTILE, Disposition.CIVILIZED,
	 * StatusEffect.LAMIA),
	 * 
	 * 
	 * 
	 * FOX_MORPH("fox morph",
	 * 
	 * "Vulpines are a humanoid race most commonly found in the city of Dominion."
	 * +
	 * " Vulpines are very cunning and mischievous, and love nothing more than playing practical jokes on others."
	 * +
	 * " They often seem insensitive to other's feeling because of this, though to them it's just a bit of fun."
	 * +
	 * " Vulpines make friends easily, and are one of the most common races in Dominion."
	 * ,
	 * 
	 * "Vulpines are humanoid, with a human body and face." +
	 * " Their legs and arms are covered in dark orange fur, ending at the thigh and upper arm."
	 * + " A large fox-like bushy tail grows from above their ass." +
	 * " Vulpines have a pair of fox-like ears." +
	 * " Females typically have three pairs of average sized breasts." +
	 * " Males have a dog-like cock.",
	 * 
	 * "Vulpines are not aggressive, but will defend themselves when attacked."
	 * +
	 * " They typically know a small amount of magic, which they primarily use to play tricks on others."
	 * +
	 * "When forced into confrontation, Vulpines will typically use magic or seduction."
	 * ,
	 * 
	 * " Vulpines will breed with any other race." +
	 * " A Vulpine pregnancy results in up to 4 Vulpines, which will rapidly reach full maturity and leave to make their own way in Dominion."
	 * ,
	 * 
	 * RacialBody.VULPINE, Genus.VULPINE, Disposition.CIVILIZED,
	 * StatusEffect.FOX_MORPH), KITSUNE("kitsune",
	 * 
	 * "Kitsunes are an exclusively female humanoid monster race found everywhere."
	 * +
	 * " Like Vulpines, Kitsunes are very cunning and mischievous, and love nothing more than playing practical jokes on others."
	 * +
	 * " They have a strong mastery of illusion-based magic, which they use to play pranks on others."
	 * +
	 * " They are more aggressive than Vulpines, and their practical jokes will typically go too far."
	 * +
	 * " Due to this, there have been many attempts to force Kitsunes out of Dominion, but die to their strong illusion magic, all efforts have so far been in vain."
	 * ,
	 * 
	 * "Vulpines are humanoid monsters, covered in fur that ranges from dark orange to pure white."
	 * +
	 * " They have digitigrade legs, and their hands and feet have leathery pads, with each digit ending in little claws."
	 * +
	 * " Kitsunes can have up to nine fox-like bushy tails growing from above their ass."
	 * +
	 * " The number of tails they have is typically a good indication of their mastery of illusion magic."
	 * +
	 * " Their faces are of an anthropomorphic fox-like appearance, and they have a pair of fox-like ears."
	 * + " Kitsunes have three pairs of large breasts.",
	 * 
	 * "Kitsunes are not too aggressive, but will sometimes decide to attack individuals."
	 * +
	 * " They know a large amount amount of magic, which is limited to illusion-based spells."
	 * + "When fighting, Kitsunes will typically use magic or seduction.",
	 * 
	 * " Kitsunes will only rarely seek to breed with other races, instead inflicting orgasm-denial on their defeated foes."
	 * +
	 * " A Kitsune pregnancy results in up to 4 Kitsunes, which will rapidly reach full maturity and leave to make their own way."
	 * ,
	 * 
	 * RacialBody.KITSUNE, Genus.VULPINE, Disposition.NEUTRAL,
	 * StatusEffect.KITSUNE),
	 * 
	 * 
	 * 
	 * COW_MORPH("cow morph",
	 * 
	 * "Bovines are a humanoid race most commonly found in the city of Dominion."
	 * +
	 * " Bovines are quite stupid and the females are very submissive, and will happily do whatever their friends tell them to do."
	 * +
	 * " They are mostly known for their females having gigantic breasts and heavy lactation."
	 * +
	 * " Due to this, they are often the target of unwanted attention from the other races."
	 * +
	 * " Bovines love to have their breasts milked, and prefer their sexual partners to pay attention to their breasts."
	 * ,
	 * 
	 * "Bovines are humanoid, with a human body and face." +
	 * " Their legs and arms are covered in a fine layer of fur, ending at the thigh and upper arm."
	 * + " Their feet are shaped into hooves." +
	 * " A cow-like tail grows from above their ass." +
	 * " They have a pair of cow-like ears and a pair of horns, with males having considerable larger horns than females."
	 * +
	 * " Females typically have three pairs of gigantic breasts, with multiple nipples on each."
	 * + " Males have a horse-like cock.",
	 * 
	 * "Female bovines are extremely passive, and will usually submit when attacked."
	 * + " Males, however, will defend themselves." +
	 * " They are completely unable to use magic." +
	 * "Males will use physical attacks in combat, and if a female decides to fight, she will use seduction."
	 * ,
	 * 
	 * " Bovines will breed with any other race." +
	 * " A Bovine pregnancy results in a pair of Bovines, which will rapidly reach full maturity and leave to make their own way in Dominion."
	 * ,
	 * 
	 * RacialBody.BOVINE, Genus.BOVINE, Disposition.CIVILIZED,
	 * StatusEffect.COW_MORPH),
	 * 
	 * MINOTAUR("minotaur",
	 * 
	 * "Minotaurs are an exclusively male humanoid monster race." +
	 * " Minotaurs are extremely dominant and aggressive, and will attack almost anything on sight."
	 * + " As a result, Minotaurs have been driven out of Dominion society." +
	 * " A minotaur will usually have a herd of submissive Bovines following him everywhere."
	 * +
	 * " Minotaurs show a special interest in free Bovines, and after raping them, will force them to join his herd."
	 * +
	 * " The minotaur will then forcefully transform the Bovine into a hyper-breasted submissive fuck toy."
	 * ,
	 * 
	 * "Minotaurs are humanoid monsters, with a fine layer of fur covering their entire bodies."
	 * + " Their feet are shaped into hooves and their legs are digitigrade." +
	 * " A cow-like tail grows from above their ass." +
	 * " They have a pair of cow-like ears and a pair of huge horns." +
	 * " They have anthropomorphic cow-like faces." +
	 * " They have a gigantic horse-like cock.",
	 * 
	 * "Minotaurs will attack almost anything they see." +
	 * " They are unable to learn magic, and will always fight using brute force."
	 * ,
	 * 
	 * "Minotaurs will breed with anything they can catch." +
	 * " Each Minotaur pregnancy will result in a single Minotaur, which will rapidly reach full maturity and leave to find a herd of his own."
	 * ,
	 * 
	 * RacialBody.MINOTAUR, Genus.BOVINE, Disposition.UNPREDICTABLE,
	 * StatusEffect.MINOTAUR),
	 * 
	 * 
	 * 
	 * SPIDER_MORPH("spider morph",
	 * 
	 * "Spider morphs are a humanoid race most commonly found in the city of Dominion."
	 * +
	 * " Spider morphs are a sly, tricky race, and have come close the being driven out of Dominion on several occasions due to their predatory ways."
	 * + " They like to spin webs and trick people into them." +
	 * " Once caught, they will inject their victims with venom, causing them to become filled with lust."
	 * + " They will then engage in bondage sex using their webs.",
	 * 
	 * "Spider morphs are humanoid, with a human body and face." +
	 * " Their legs and arms are covered in a black chitin, ending at the thigh and upper arm."
	 * +
	 * " The most notable feature of a spider morph is the huge spider-like abdomen sprouting from above their ass."
	 * +
	 * " They have a smaller pair of eyes above their normal eyes, which are typically jet black."
	 * ,
	 * 
	 * "Spider morphs are unable to use magic." +
	 * " Spider morphs will typically vary between physical attacks and attempting to seduce their opponents."
	 * +
	 * " Their spider-like abdomen gives them the ability to shoot webs at their opponents."
	 * +
	 * " They also have fangs that inject a lust-inducting venom into their prey."
	 * ,
	 * 
	 * "Spider morphs reproduce by depositing eggs in a target host by use of an ovipositor."
	 * +
	 * " They will not lay their eggs in anyone without permission, but after being injected with venom, not many partners refuse."
	 * ,
	 * 
	 * RacialBody.SPIDER_MORPH, Genus.ARACHNID, Disposition.CIVILIZED,
	 * StatusEffect.SPIDER_MORPH),
	 * 
	 * ARACHNE("arachne",
	 * 
	 * "Arachne are a monster race found in the jungle and ruins." +
	 * " Arachne are very aggressive and territorial, and as a result have been driven out of Dominion's society."
	 * +
	 * " They lurk in dark places in the jungle and ruins, waiting for passersby to ensnare in their webs."
	 * + " After catching their prey, Arachne will use them for breeding.",
	 * 
	 * "Arachne are humanoid/animal monsters, having a normal-looking spider morph upper body, but instead of legs they possess the body of a spider."
	 * ,
	 * 
	 * "Arachne are very aggressive, and will prey on anyone passing near their web."
	 * +
	 * " Arachne will know some spells, and will prefer fighting using magic and seduction."
	 * ,
	 * 
	 * "Male Arachne will fuck anything they catch, and will keep females bound in their webs for days, continually breeding them before growing bored and dumping them in the wilderness."
	 * +
	 * " Females will deposit their eggs in whoever they catch, using their prey's cum to fertilise them beforehand."
	 * ,
	 * 
	 * RacialBody.ARACHNE, Genus.ARACHNID, Disposition.SAVAGE,
	 * StatusEffect.ARACHNE),
	 * 
	 * 
	 * WYVERN("wyvern",
	 * 
	 * "Wyverns are a humanoid race most commonly found in the city of Dominion."
	 * +
	 * " Wyverns are very arrogant, and will not tolerate any sleights against them."
	 * +
	 * " They resent being treated in a submissive manner, and will always seek to take charge of a situation."
	 * ,
	 * 
	 * "Wyverns are humanoid, with a human body and face." +
	 * " The scaly arms of a Wyvern extend into large, leathery wings, giving them the ability to fly."
	 * +
	 * " Their legs are also scaly, starting at mid thigh, and their feet have claws instead of toes."
	 * +
	 * " Wyverns have a crest instead of hair, and they have a long, lizard-like tail."
	 * +
	 * " Scale colour differs from Wyvern to Wyvern, but is usually a shade of red or green."
	 * + " Females have small breasts." + " Males have a lizard-like cock.",
	 * 
	 * "Wyverns are not overly aggressive, but will take action against anyone who insults them."
	 * +
	 * " They typically know  small amount of magic, and will prefer fighting using brute force and spells."
	 * ,
	 * 
	 * "Wyverns breed by laying eggs that can be fertilised by any race." +
	 * " They will hate-fuck anyone they defeat in combat, with females refusing to become fertilised by what they consider inferior races."
	 * +
	 * " Once fertilised eggs hatch, the new Wyverns will rapidly reach full maturity and leave to make their own way in Dominion."
	 * ,
	 * 
	 * RacialBody.WYVERN, Genus.DRAGON, Disposition.CIVILIZED,
	 * StatusEffect.WYVERN),
	 * 
	 * DRAGON("dragon",
	 * 
	 * "Dragons are a humanoid monster race most commonly found in the ruins." +
	 * " Dragons are extremely proud and arrogant, and will not tolerate any sleights against them."
	 * +
	 * " They are extremely dominant, and will always seek to break their foes into submission."
	 * +
	 * " Dragons are considered the single most dangerous race, as they are extremely strong, tough and intelligent."
	 * +
	 * " They inhabit buildings in the ruins, and will seek to rob travellers and accumulate a hoard of wealth."
	 * +
	 * " Dragons will take a person prisoner, using them to breed and turning them into their submissive partner."
	 * +
	 * " They will quickly replace their partner if they manage to capture a more attractive or more important individual."
	 * ,
	 * 
	 * "Dragons are humanoid monsters, covered in hard scales." +
	 * " They have a huge pair of scaly and leathery wings on their back, giving them the ability to fly."
	 * + " Their feet and hands have claws on the end of their digits." +
	 * " Dragons have a crest instead of hair, and they have a long, lizard-like tail."
	 * +
	 * " Scale colour differs from Dragon to Dragon, but is usually a shade of red or green."
	 * + " Females have huge breasts." +
	 * " Males have a gigantic lizard-like cock.",
	 * 
	 * "Dragons are extremely aggressive, and will attack anything that comes close to their lair."
	 * +
	 * " They typically know a large amount of magic, and will prefer fighting using brute force and spells."
	 * ,
	 * 
	 * "Dragons breed by laying eggs that can be fertilised by any race." +
	 * " They will hate-fuck anyone they defeat in combat, with females refusing to become fertilised by what they consider inferior races."
	 * +
	 * " Once fertilised eggs hatch, the new Dragons will rapidly reach full maturity and leave to make their own way in the ruins."
	 * ,
	 * 
	 * RacialBody.DRAGON, Genus.DRAGON, Disposition.UNPREDICTABLE,
	 * StatusEffect.DRAGON),
	 * 
	 * 
	 * LILIN("lilin",
	 * 
	 * "Lilin are a humanoid race found everywhere." +
	 * " Lilin are physically indistinguishable from demons, as both species can alter their bodies at will."
	 * +
	 * " The main difference between the two is that Lilin are able to use far more powerful magic than regular demons, and they have the ability to create new demons."
	 * +
	 * " Their corruptive aura is also far more potent, and any creature not highly resistant to the dark passion will surrender to their will almost instantly."
	 * +
	 * " Lilin are created by Lilith's own hand, being corrupted into her deviant minions."
	 * ,
	 * 
	 * "Lilin are humanoid, with a human-like face and body." +
	 * " Their skin will range in colour from dark red to a light lilac." +
	 * " Lilin can change the appearance of their bodies at will, but they will commonly have one or two pairs of horns and a thin spaded tail."
	 * +
	 * " When in succubus form, many Lilin like to morph their legs into black leather reaching up to their thighs, forming their feet into high heels."
	 * +
	 * " They often give themselves up to three pairs of breasts, each pair being at least a DD cup."
	 * +
	 * " When in Incubus form, a Lilin will take on a muscular masculine form, giving themselves a gigantic demonic cock."
	 * +
	 * " Some Lilin prefer to never leave their Succubus form, and when ready to impregnate someone, will simply grow a demonic cock above their pussy."
	 * ,
	 * 
	 * "Lilin have the ability to cast extremely powerful spells, being able to corrupt people into demons."
	 * +
	 * " When in combat, a Lilin will mostly use seduction and spells in order to defeat their foe."
	 * ,
	 * 
	 * "A Lilin will take on their Succubus form and seek out males of any species to breed with."
	 * +
	 * " Once they have found and seduced a suitable mate, they will present themselves to be fucked, using demonic magic to force their partner to repeatedly orgasm."
	 * +
	 * " After milking their victim of cum, they will move on to their next conquest.</br>"
	 * +
	 * "The cum that they gather is transformed within their bodies into their own highly corruptive and addictive seed."
	 * +
	 * " Once they have gathered enough cum, they will transform into their Incubus form and seek out a suitable female to breed."
	 * +
	 * " Having located a suitable victim, the Lilin will fuck them, filling them with their corruptive spooge."
	 * +
	 * " Such a union will more often than not result in the female birthing a litter of imps a few days later.</br>"
	 * +
	 * "Lilin are also able to create new demons by force-feeding them their corruptive cum over the course of a few days."
	 * ,
	 * 
	 * RacialBody.DEMON, Genus.DEMON, Disposition.UNPREDICTABLE,
	 * StatusEffect.LILIN);
	 */

	private String name, basicDescription, advancedDescription;
	private Colour colour;
	private Disposition disposition;
	private StatusEffect statusEffect;
	private List<Attack> preferredAttacks;
	private boolean vulnerableToLilithsLustStorm;
	private int numberOfOffspringLow, numberOfOffspringHigh;
	private float chanceForMaleOffspring;
	private Attribute damageMultiplier, resistanceMultiplier;
	private FurryPreference defaultFemininePreference, defaultMasculinePreference;
	
	private Race(String name,
			String namePlural,
			String singularMaleName,
			String singularFemaleName,
			String pluralMaleName,
			String pluralFemaleName,

			String basicDescription,
			String advancedDescription,

			Colour colour,
			Disposition disposition,
			StatusEffect statusEffect,
			List<Attack> preferredAttacks,
			boolean vulnerableToLilithsLustStorm,
			
			float chanceForMaleOffspring,
			int numberOfOffspringLow,
			int numberOfOffspringHigh,
			
			Attribute damageMultiplier,
			Attribute resistanceMultiplier,
			
			FurryPreference defaultFemininePreference,
			FurryPreference defaultMasculinePreference) {
		this.name = name;

		this.basicDescription = basicDescription;
		this.advancedDescription = advancedDescription;

		this.colour = colour;
		this.disposition = disposition;
		this.statusEffect = statusEffect;

		this.preferredAttacks = preferredAttacks;

		this.vulnerableToLilithsLustStorm = vulnerableToLilithsLustStorm;

		this.chanceForMaleOffspring=chanceForMaleOffspring;
		
		this.numberOfOffspringLow = numberOfOffspringLow;
		this.numberOfOffspringHigh = numberOfOffspringHigh;
		
		this.damageMultiplier = damageMultiplier;
		this.resistanceMultiplier = resistanceMultiplier;
		
		this.defaultFemininePreference = defaultFemininePreference;
		this.defaultMasculinePreference = defaultMasculinePreference;
	}

	public String getName() {
		return name;
	}

	public String getBasicDescription() {
		return basicDescription;
	}

	public String getAdvancedDescription() {
		return advancedDescription;
	}

	public Disposition getDisposition() {
		return disposition;
	}

	public StatusEffect getStatusEffect() {
		return statusEffect;
	}

	public List<Attack> getPreferredAttacks() {
		return preferredAttacks;
	}

	public boolean isVulnerableToLilithsLustStorm() {
		return vulnerableToLilithsLustStorm;
	}

	public int getNumberOfOffspringLow() {
		return numberOfOffspringLow;
	}

	public int getNumberOfOffspringHigh() {
		return numberOfOffspringHigh;
	}
	
	public Colour getColour() {
		return colour;
	}
	
	public boolean isAffectedByFurryPreference() {
		return defaultFemininePreference != null && defaultMasculinePreference!=null;
	}
	
	// Offspring names:
	
	public float getChanceForMaleOffspring() {
		return chanceForMaleOffspring;
	}

	public Attribute getDamageMultiplier() {
		return damageMultiplier;
	}

	public Attribute getResistanceMultiplier() {
		return resistanceMultiplier;
	}

	public FurryPreference getDefaultFemininePreference() {
		return defaultFemininePreference;
	}

	public FurryPreference getDefaultMasculinePreference() {
		return defaultMasculinePreference;
	}

}
