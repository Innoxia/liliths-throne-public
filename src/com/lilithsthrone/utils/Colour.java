package com.lilithsthrone.utils;

import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.main.Main;

import javafx.scene.paint.Color;

/**
 * @since 0.1.0
 * @version 0.2.3
 * @author Innoxia
 */
public enum Colour {
	
	// This class and BaseColour are beyond saving x_x
	
	BASE_WHITE(BaseColour.WHITE, "white", Util.newArrayListOfValues("white")),
	BASE_GREY(BaseColour.GREY, "grey", Util.newArrayListOfValues("grey")),
	BASE_GREY_DARK(BaseColour.GREY_DARK, "dark grey", Util.newArrayListOfValues("darkGrey")),
	
	BASE_ROSE(BaseColour.ROSE, "rose", Util.newArrayListOfValues("rose")),
	BASE_LILAC(BaseColour.LILAC, "lilac", Util.newArrayListOfValues("lilac")),
	BASE_LILAC_LIGHT(BaseColour.LILAC_LIGHT, "light lilac", Util.newArrayListOfValues("lightLilac")),
	BASE_PURPLE_DARK(BaseColour.PURPLE_DARK, "dark purple", Util.newArrayListOfValues("darkPurple")),
	BASE_PURPLE(BaseColour.PURPLE, "purple", Util.newArrayListOfValues("purple")),
	BASE_PURPLE_LIGHT(BaseColour.PURPLE_LIGHT, "light purple", Util.newArrayListOfValues("lightPurple")),
	
	BASE_PINK_DEEP(BaseColour.PINK_DEEP, "deep pink", Util.newArrayListOfValues("deepPink", "darkPink")),
	BASE_VIOLET(BaseColour.VIOLET, "violet", Util.newArrayListOfValues("violet")),
	BASE_PINK(BaseColour.PINK, "pink", Util.newArrayListOfValues("pink")),
	BASE_PINK_LIGHT(BaseColour.PINK_LIGHT, "light pink", Util.newArrayListOfValues("lightPink")),
		
	BASE_MAGENTA(BaseColour.MAGENTA, "magenta", Util.newArrayListOfValues("magenta")),
	BASE_CRIMSON(BaseColour.CRIMSON, "crimson", Util.newArrayListOfValues("crimson")),
	BASE_RED(BaseColour.RED, "red", Util.newArrayListOfValues("red")),
	BASE_RED_LIGHT(BaseColour.RED_LIGHT, "light red", Util.newArrayListOfValues("lightRed")),
	
	BASE_TAN(BaseColour.TAN, "tan", Util.newArrayListOfValues("tan")),
	BASE_BROWN(BaseColour.BROWN, "brown", Util.newArrayListOfValues("brown")),
	BASE_BROWN_DARK(BaseColour.BROWN_DARK, "dark brown", Util.newArrayListOfValues("darkBrown")),
	BASE_ORANGE(BaseColour.ORANGE, "orange", Util.newArrayListOfValues("orange")),
	BASE_GINGER(BaseColour.GINGER, "ginger", Util.newArrayListOfValues("ginger")),
	
	BASE_GOLD(BaseColour.GOLD, "gold", Util.newArrayListOfValues("gold")),
	BASE_YELLOW(BaseColour.YELLOW, "yellow", Util.newArrayListOfValues("yellow")),
	BASE_YELLOW_LIGHT(BaseColour.YELLOW_LIGHT, "light yellow", Util.newArrayListOfValues("lightYellow")),
	
	BASE_GREEN_LIME(BaseColour.GREEN_LIME, "lime green", Util.newArrayListOfValues("limeGreen")),
	BASE_GREEN_LIGHT(BaseColour.GREEN_LIGHT, "light green", Util.newArrayListOfValues("lightGreen")),
	BASE_GREEN(BaseColour.GREEN, "green", Util.newArrayListOfValues("green")),
	BASE_GREEN_DARK(BaseColour.GREEN_DARK, "dark green", Util.newArrayListOfValues("darkGreen")),
	
	BASE_AQUA(BaseColour.AQUA, "aqua", Util.newArrayListOfValues("aqua")),
	BASE_TEAL(BaseColour.TEAL, "teal", Util.newArrayListOfValues("teal")),
	BASE_BLUE_LIGHT(BaseColour.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("lightBlue")),
	BASE_BLUE(BaseColour.BLUE, "blue", Util.newArrayListOfValues("blue")),
	BASE_BLUE_STEEL(BaseColour.BLUE_STEEL, "steely blue", Util.newArrayListOfValues("steelyBlue")),
	
	BASE_BLACK(BaseColour.BLACK, "black", Util.newArrayListOfValues("black")),
	BASE_PITCH_BLACK(BaseColour.PITCH_BLACK, "black", Util.newArrayListOfValues("black")),
	
	
	
	// Game colours:
	BACKGROUND(Util.newColour(0x222222), Util.newColour(0xcccccc), "grey"),
	BACKGROUND_ALT(Util.newColour(0x292929), Util.newColour(0xbbbbbb), "grey"),
	
	MAP_BACKGROUND_UNEXPLORED(Util.newColour(0x111), Util.newColour(0x111), "black"),
	MAP_BACKGROUND_PINK(Util.newColour(0xb2a4bb), Util.newColour(0xb2a4bb), "pink"),
	MAP_BACKGROUND(Util.newColour(0xbbbbbb), Util.newColour(0xbbbbbb), "grey"),
	MAP_BACKGROUND_DARK(Util.newColour(0x888888), Util.newColour(0x8f8f8f), "dark grey"),
	MAP_BACKGROUND_BLUE(BaseColour.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("lightBlue")),
	
	GENERIC_SEX(BaseColour.PINK_LIGHT, "pink", Util.newArrayListOfValues("sex")),
	GENERIC_COMBAT(BaseColour.CRIMSON, "crimson"),
	GENERIC_ARCANE(BaseColour.PINK, "pink", Util.newArrayListOfValues("arcane")),
	GENERIC_TERRIBLE(BaseColour.CRIMSON, "crimson", Util.newArrayListOfValues("terrible")),
	GENERIC_MINOR_BAD(BaseColour.RED_LIGHT, "red", Util.newArrayListOfValues("minorBad", "badMinor")),
	GENERIC_MINOR_GOOD(BaseColour.GREEN_LIGHT, "light green", Util.newArrayListOfValues("minorGood", "goodMinor")),
	GENERIC_BAD(BaseColour.RED, "red", Util.newArrayListOfValues("bad")),
	GENERIC_GOOD(BaseColour.GREEN, "green", Util.newArrayListOfValues("good")),
	GENERIC_EXCELLENT(BaseColour.GOLD, "gold", Util.newArrayListOfValues("excellent")),
	GENERIC_ATTRIBUTE(BaseColour.MAGENTA, "magenta"),
	GENERIC_EXPERIENCE(BaseColour.BLUE_LIGHT, "light blue"),
	COOLDOWN(BaseColour.CRIMSON, "crimson", Util.newArrayListOfValues("cooldown")),

	SCAR(BaseColour.TAN, "tan"),
	TATTOO(BaseColour.GREY, "grey"),
	
	PERK(BaseColour.AQUA, "aqua"),
	TRAIT(BaseColour.GREEN_LIGHT, "green"),
	FETISH(BaseColour.PINK_LIGHT, "light pink", Util.newArrayListOfValues("fetish")),
	STATUS_EFFECT(BaseColour.YELLOW, "yellow"),
	SPECIAL_ATTACK(BaseColour.CRIMSON, "crimson"),
	STATUS_EFFECT_TIME_OVERFLOW(BaseColour.BLUE, "aqua"),
	STATUS_EFFECT_TIME_HIGH(BaseColour.GREEN_LIGHT, "green"),
	STATUS_EFFECT_TIME_MEDIUM(BaseColour.ORANGE, "orange"),
	STATUS_EFFECT_TIME_LOW(BaseColour.RED, "red"),

	RACE_UNKNOWN(BaseColour.BLACK, "black", Util.newArrayListOfValues("unknown")),
	RACE_HUMAN(BaseColour.BLUE_STEEL, "pale blue", Util.newArrayListOfValues("human")),
	RACE_DEMON(BaseColour.PURPLE_LIGHT, "light purple", Util.newArrayListOfValues("demon")),
	RACE_IMP(BaseColour.PURPLE, "purple", Util.newArrayListOfValues("imp")),
	RACE_ANGEL(BaseColour.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("angel")),
	RACE_DOG_MORPH(BaseColour.BROWN, "brown", Util.newArrayListOfValues("dogMorph", "dog")),
	RACE_CAT_MORPH(BaseColour.VIOLET, "violet", Util.newArrayListOfValues("catMorph", "cat")),
	RACE_COW_MORPH(BaseColour.TAN, "tan", Util.newArrayListOfValues("cowMorph", "cow")),
	RACE_HORSE_MORPH(BaseColour.ORANGE, "orange", Util.newArrayListOfValues("horseMorph", "horse")),
	RACE_REINDEER_MORPH(BaseColour.BROWN_DARK, "dark brown", Util.newArrayListOfValues("reindeerMorph", "reindeer")),
	RACE_WOLF_MORPH(BaseColour.BLACK, "black", Util.newArrayListOfValues("wolfMorph", "wolf")),
	RACE_HARPY(BaseColour.PINK_LIGHT, "light pink", Util.newArrayListOfValues("harpy")),
	RACE_SLIME(BaseColour.PINK, "pink", Util.newArrayListOfValues("slime")),
	RACE_SQUIRREL_MORPH(BaseColour.GINGER, "ginger", Util.newArrayListOfValues("squirrelMorph", "squirrel")),
	RACE_RAT_MORPH(BaseColour.BROWN_DARK, "dark brown", Util.newArrayListOfValues("ratMorph", "rat")),
	RACE_RABBIT_MORPH(BaseColour.BROWN_DARK, "dark brown", Util.newArrayListOfValues("rabbitMorph", "rabbit")),
	RACE_BAT_MORPH(BaseColour.BLACK, "black", Util.newArrayListOfValues("batMorph", "bat")),
	RACE_ALLIGATOR_MORPH(BaseColour.GREEN_DARK, "dark green", Util.newArrayListOfValues("alligatorMorph", "alligator", "gatorMorph", "gator")),
	
	QUEST_MAIN(BaseColour.PINK, "pink"),
	QUEST_SIDE(BaseColour.BLUE, "blue"),
	QUEST_RELATIONSHIP(BaseColour.PINK_LIGHT, "pink"),

	MAP_MARKER(Util.newColour(0x6163DB), Util.newColour(0x6163DB), "blue"),

	ATTRIBUTE_HEALTH(BaseColour.CRIMSON, "crimson", Util.newArrayListOfValues("health", "hp", "energy")),
	ATTRIBUTE_MANA(BaseColour.PURPLE_LIGHT, "light purple", Util.newArrayListOfValues("willpower", "wp", "mana", "aura")),
//	ATTRIBUTE_STAMINA(BaseColour.LILAC, "lilac", Util.newArrayListOfValues("stamina", "sp", "energy")),

	ATTRIBUTE_PHYSIQUE(BaseColour.MAGENTA, "magenta", Util.newArrayListOfValues("physique", "phys", "strength", "str")),
	ATTRIBUTE_ARCANE(BaseColour.PURPLE, "purple", Util.newArrayListOfValues("intelligence", "int")),
//	ATTRIBUTE_FITNESS(BaseColour.LILAC, "light purple", Util.newArrayListOfValues("fitness", "fit")),
	ATTRIBUTE_CORRUPTION(BaseColour.PINK_DEEP, "pink", Util.newArrayListOfValues("corruption", "cor", "corr")),

	ATTRIBUTE_AROUSAL(BaseColour.PINK_DEEP, "pink", Util.newArrayListOfValues("arousal", "ars")),
	ATTRIBUTE_LUST(BaseColour.MAGENTA, "magenta", Util.newArrayListOfValues("lust", "lst", "seduction")),

	//TODO
	PHYSIQUE_STAGE_ZERO(BaseColour.MAGENTA, "magenta"),
	PHYSIQUE_STAGE_ONE(BaseColour.MAGENTA, "magenta"),
	PHYSIQUE_STAGE_TWO(BaseColour.MAGENTA, "magenta"),
	PHYSIQUE_STAGE_THREE(BaseColour.MAGENTA, "magenta"),
	PHYSIQUE_STAGE_FOUR(BaseColour.MAGENTA, "magenta"),
	PHYSIQUE_STAGE_FIVE(BaseColour.GOLD, "gold"),
	
	//TODO
	INTELLIGENCE_STAGE_ZERO(BaseColour.PURPLE, "purple"),
	INTELLIGENCE_STAGE_ONE(BaseColour.PURPLE, "purple"),
	INTELLIGENCE_STAGE_TWO(BaseColour.PURPLE, "purple"),
	INTELLIGENCE_STAGE_THREE(BaseColour.PURPLE, "purple"),
	INTELLIGENCE_STAGE_FOUR(BaseColour.PURPLE, "purple"),
	INTELLIGENCE_STAGE_FIVE(BaseColour.GOLD, "gold"),
	
	//TODO
	FITNESS_STAGE_ZERO(BaseColour.LILAC, "light purple"),
	FITNESS_STAGE_ONE(BaseColour.LILAC, "light purple"),
	FITNESS_STAGE_TWO(BaseColour.LILAC, "light purple"),
	FITNESS_STAGE_THREE(BaseColour.LILAC, "light purple"),
	FITNESS_STAGE_FOUR(BaseColour.LILAC, "light purple"),
	FITNESS_STAGE_FIVE(BaseColour.GOLD, "gold"),
	
	CORRUPTION_STAGE_ZERO(Util.newColour(0xffdf80), Util.newColour(0xffdf80), "gold"),
	CORRUPTION_STAGE_ONE(Util.newColour(0xff80bf), Util.newColour(0xff80bf), "pink"),
	CORRUPTION_STAGE_TWO(Util.newColour(0xff1a8c), Util.newColour(0xff1a8c), "pink"),
	CORRUPTION_STAGE_THREE(Util.newColour(0xe600ac), Util.newColour(0xe600ac), "pink"),
	CORRUPTION_STAGE_FOUR(Util.newColour(0xd411d4), Util.newColour(0xd411d4), "pink"),
	CORRUPTION_STAGE_FIVE(Util.newColour(0xbf00ff), Util.newColour(0xbf00ff), "pink"),
	
	AROUSAL_STAGE_ZERO(Util.newColour(0xfee6ff), Util.newColour(0xfee6ff), "pink"),
	AROUSAL_STAGE_ONE(Util.newColour(0xfcb3ff), Util.newColour(0xfcb3ff), "pink"),
	AROUSAL_STAGE_TWO(Util.newColour(0xfb80ff), Util.newColour(0xfb80ff), "pink"),
	AROUSAL_STAGE_THREE(Util.newColour(0xf94dff), Util.newColour(0xf94dff), "pink"),
	AROUSAL_STAGE_FOUR(Util.newColour(0xf824ff), Util.newColour(0xf824ff), "pink"),
	AROUSAL_STAGE_FIVE(Util.newColour(0xf700ff), Util.newColour(0xf700ff), "pink"),
	
	LUST_STAGE_ZERO(Util.newColour(0x80CAFF), Util.newColour(0xfee6ff), "blue"),
	LUST_STAGE_ONE(Util.newColour(0xB699FF), Util.newColour(0xfcb3ff), "purple"),
	LUST_STAGE_TWO(Util.newColour(0xFF99D1), Util.newColour(0xfb80ff), "pink"),
	LUST_STAGE_THREE(Util.newColour(0xFF61AB), Util.newColour(0xf94dff), "pink"),
	LUST_STAGE_FOUR(Util.newColour(0xFF3377), Util.newColour(0xf824ff), "dark pink"),
	LUST_STAGE_FIVE(Util.newColour(0xFF1A66), Util.newColour(0xf824ff), "dark pink"),

	DESIRE_STAGE_ZERO(Util.newColour(0xB699FF), Util.newColour(0xfcb3ff), "purple"),
	DESIRE_STAGE_ONE(Util.newColour(0xFF99D1), Util.newColour(0xfb80ff), "pink"),
	DESIRE_STAGE_TWO(Util.newColour(0xFF61AB), Util.newColour(0xf94dff), "pink"),
	DESIRE_STAGE_THREE(Util.newColour(0xFF3377), Util.newColour(0xf824ff), "dark pink"),
	DESIRE_STAGE_FOUR(Util.newColour(0xffdf80), Util.newColour(0xffdf80), "gold"),


	COMPANION(BaseColour.GREEN_LIGHT, "light green",  Util.newArrayListOfValues("companion", "companions")),
	
	AFFECTION(BaseColour.PINK_LIGHT, "light pink",  Util.newArrayListOfValues("affection")),
	OBEDIENCE(BaseColour.PURPLE_LIGHT, "light purple",  Util.newArrayListOfValues("obedience")),
	
	AFFECTION_NEGATIVE_FIVE(Util.newColour(0xff0066), Util.newColour(0x8e011e), "magenta"),
	AFFECTION_NEGATIVE_FOUR(Util.newColour(0xff2a7f), Util.newColour(0xa40123), "magenta"),
	AFFECTION_NEGATIVE_THREE(Util.newColour(0xff5599), Util.newColour(0xb21e44), "pink"),
	AFFECTION_NEGATIVE_TWO(Util.newColour(0xff80b2), Util.newColour(0xbc325a), "pink"),
	AFFECTION_NEGATIVE_ONE(Util.newColour(0xffaacc), Util.newColour(0xc44670), "pink"),
	AFFECTION_NEUTRAL(Util.newColour(0xe3dedb), Util.newColour(0xcd5986), "grey"),
	AFFECTION_POSITIVE_ONE(Util.newColour(0xffeeaa), Util.newColour(0xd66e9d), "yellow"),
	AFFECTION_POSITIVE_TWO(Util.newColour(0xffe680), Util.newColour(0xe082b3), "yellow"),
	AFFECTION_POSITIVE_THREE(Util.newColour(0xffdd55), Util.newColour(0xe996c9), "yellow"),
	AFFECTION_POSITIVE_FOUR(Util.newColour(0xffd42a), Util.newColour(0xf2aadf), "gold"),
	AFFECTION_POSITIVE_FIVE(Util.newColour(0xffcc00), Util.newColour(0xfbbcf4), "gold"),

	MASCULINE_PLUS(Util.newColour(0x4D9DFF), Util.newColour(0x4D9DFF), "dark blue", Util.newArrayListOfValues("masculineStrong", "masStr", "masculinePlus")),
	MASCULINE(Util.newColour(0x8ABEFF), Util.newColour(0x8ABEFF), "blue", Util.newArrayListOfValues("masculine", "mas")),
	ANDROGYNOUS(Util.newColour(0xB39EFF), Util.newColour(0xB39EFF), "purple", Util.newArrayListOfValues("androgynous", "andro")),
	FEMININE(Util.newColour(0xFFBDFF), Util.newColour(0xFFFBDFF), "pink", Util.newArrayListOfValues("feminine", "fem")),
	FEMININE_PLUS(Util.newColour(0xFF85FF), Util.newColour(0xFF85FF), "pink", Util.newArrayListOfValues("feminineStrong", "femStr", "femininePlus")),
	
	BODY_SIZE_ZERO(Util.newColour(0xFFEBD6), Util.newColour(0x241D00), "tan", Util.newArrayListOfValues("bodySizeZero")),
	BODY_SIZE_ONE(Util.newColour(0xFFE0BD), Util.newColour(0x3D3100), "tan", Util.newArrayListOfValues("bodySizeOne")),
	BODY_SIZE_TWO(Util.newColour(0xFFC88A), Util.newColour(0x5C4900), "tan", Util.newArrayListOfValues("bodySizeTwo")),
	BODY_SIZE_THREE(Util.newColour(0xFFAB57), Util.newColour(0x806600), "tan", Util.newArrayListOfValues("bodySizeThree")),
	BODY_SIZE_FOUR(Util.newColour(0xFF9124), Util.newColour(0x9E7E00), "tan", Util.newArrayListOfValues("bodySizeFour")),

	MUSCLE_ZERO(Util.newColour(0xDBFFF6), Util.newColour(0x001F17), "teal", Util.newArrayListOfValues("muscleZero")),
	MUSCLE_ONE(Util.newColour(0xBDFFED), Util.newColour(0x00382B), "teal", Util.newArrayListOfValues("muscleOne")),
	MUSCLE_TWO(Util.newColour(0x8AFFE0), Util.newColour(0x00523F), "teal", Util.newArrayListOfValues("muscleTwo")),
	MUSCLE_THREE(Util.newColour(0x57FFD2), Util.newColour(0x006B52), "teal", Util.newArrayListOfValues("muscleThree")),
	MUSCLE_FOUR(Util.newColour(0x24FFC5), Util.newColour(0x008566), "teal", Util.newArrayListOfValues("muscleFour")),

	ALCOHOL(BaseColour.YELLOW_LIGHT, "light yellow", Util.newArrayListOfValues("alcohol")),
	PSYCHOACTIVE(BaseColour.MAGENTA, "magenta", Util.newArrayListOfValues("psychoactive")),

	TRANSFORMATION_SHRINK(BaseColour.RED, "red", Util.newArrayListOfValues("tfShrink", "shrink", "tfShrunk", "shrunk", "tfShrinking", "shrinking")),
	TRANSFORMATION_GROW(BaseColour.GREEN, "green", Util.newArrayListOfValues("tfGrow", "grow", "tfGrown", "grown", "tfGrowth", "growth")),

	GENERIC_SIZE_ONE(Util.newColour(0xAFE9B3), Util.newColour(0xc44670), "green"),
	GENERIC_SIZE_TWO(Util.newColour(0xA0E4A3), Util.newColour(0xbc325a), "green"),
	GENERIC_SIZE_THREE(Util.newColour(0x8FE096), Util.newColour(0xb21e44), "green"),
	GENERIC_SIZE_FOUR(Util.newColour(0x77DA7F), Util.newColour(0xa40123), "green"),
	GENERIC_SIZE_FIVE(Util.newColour(0x67D570), Util.newColour(0x8e011e), "green"),
	GENERIC_SIZE_SIX(Util.newColour(0x57D161), Util.newColour(0x8e011e), "green"),
	GENERIC_SIZE_SEVEN(Util.newColour(0x47CD52), Util.newColour(0x8e011e), "green"),
	GENERIC_SIZE_EIGHT(Util.newColour(0x37C843), Util.newColour(0x8e011e), "green"),
	
	WETNESS(BaseColour.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("wetness", "wet", "tfWetness", "tfWet")),
	PLASTICITY(BaseColour.LILAC, "lilac", Util.newArrayListOfValues("plasticity", "tfPlasticity")),
	ELASTICITY(BaseColour.PURPLE_LIGHT, "light purple", Util.newArrayListOfValues("elasticity", "tfElasticity")),
	TRANSFORMATION_GENERIC(BaseColour.GREEN_LIME, "lime", Util.newArrayListOfValues("tfGeneric", "tfBase")),
	TRANSFORMATION_SEXUAL(BaseColour.PINK_LIGHT, "pink", Util.newArrayListOfValues("tfSex", "tfSexual")),
	TRANSFORMATION_HUMAN(BaseColour.BLUE_STEEL, "pale blue", Util.newArrayListOfValues("tfHuman")),
	TRANSFORMATION_PARTIAL(Util.newColour(0xff80bf), Util.newColour(0xff80bf), "purple", Util.newArrayListOfValues("tfPartial")),
	TRANSFORMATION_PARTIAL_FULL(Util.newColour(0xff1a8c), Util.newColour(0xff1a8c), "purple", Util.newArrayListOfValues("tfMinor")),
	TRANSFORMATION_LESSER(Util.newColour(0xe600ac), Util.newColour(0xe600ac), "purple", Util.newArrayListOfValues("tfLesser")),
	TRANSFORMATION_GREATER(Util.newColour(0xd411d4), Util.newColour(0xd411d4), "purple-pink", Util.newArrayListOfValues("tfGreater")),

	// Speech colours:
	MASCULINE_PLUS_NPC(BaseColour.BLUE, "blue"),
	MASCULINE_NPC(BaseColour.BLUE_LIGHT, "blue"),
	ANDROGYNOUS_NPC(BaseColour.LILAC_LIGHT, "purple"),
	FEMININE_NPC(BaseColour.ROSE, "pink"),
	FEMININE_PLUS_NPC(BaseColour.PINK, "pink"),

	// Combat colours:
	DAMAGE_TYPE_PHYSICAL(Util.newColour(0xFF428E), Util.newColour(0xFF428E), "red", Util.newArrayListOfValues("dmgPhysical", "resPhysical", "physical")),
	DAMAGE_TYPE_MANA(BaseColour.PURPLE_LIGHT, "purple", Util.newArrayListOfValues("dmgMana", "resMana")),
	DAMAGE_TYPE_LUST(BaseColour.MAGENTA, "magenta", Util.newArrayListOfValues("dmgLust", "resLust")),
	DAMAGE_TYPE_SPELL(Util.newColour(0xFF6BDA), Util.newColour(0xFF6BDA), "pink", Util.newArrayListOfValues("dmgSpell", "resSpell", "spell")),
	DAMAGE_TYPE_FIRE(Util.newColour(0xff9955), Util.newColour(0xff9955), "orange", Util.newArrayListOfValues("dmgFire", "resFire", "fire")),
	DAMAGE_TYPE_COLD(Util.newColour(0x85C6FF), Util.newColour(0x85C6FF), "blue", Util.newArrayListOfValues("dmgCold", "resCold", "cold", "ice")),
	DAMAGE_TYPE_POISON(Util.newColour(0x85FF8B), Util.newColour(0x85FF8B), "green", Util.newArrayListOfValues("dmgPoison", "resPoison", "poison")),
	DAMAGE_TYPE_PURE(Util.newColour(0xFFCC00), Util.newColour(0xFFCC00), "gold", Util.newArrayListOfValues("dmgPure", "resPure", "pure")),

	SPELL_SCHOOL_FIRE(BaseColour.ORANGE, "orange", Util.newArrayListOfValues("spellFire", "schoolFire")),
	SPELL_SCHOOL_WATER(BaseColour.AQUA, "aqua", Util.newArrayListOfValues("water", "spellWater", "schoolWater")),
	SPELL_SCHOOL_EARTH(BaseColour.BROWN, "brown", Util.newArrayListOfValues("earth", "spellEarth", "schoolEarth")),
	SPELL_SCHOOL_AIR(BaseColour.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("air", "spellAir", "schoolAir")),
	SPELL_SCHOOL_ARCANE(BaseColour.PINK, "pink", Util.newArrayListOfValues("spellArcane", "schoolArcane")),
	
	// Rarity colours:
	RARITY_UNKNOWN(BaseColour.BLACK, "grey"),
	RARITY_JINXED(BaseColour.RED, "red", Util.newArrayListOfValues("jinx", "jinxed")),
	RARITY_COMMON(Util.newColour(0xf2f2f2), Util.newColour(0xf2f2f2), "white", Util.newArrayListOfValues("common")),
	RARITY_UNCOMMON(Util.newColour(0x1de547), Util.newColour(0x108228), "green", Util.newArrayListOfValues("uncommon")),
	RARITY_RARE(Util.newColour(0x47C2FF), Util.newColour(0x47C2FF), "blue", Util.newArrayListOfValues("rare")),
	RARITY_EPIC(Util.newColour(0xFF4DFC), Util.newColour(0xFF4DFC), "purple", Util.newArrayListOfValues("epic")),
	RARITY_LEGENDARY(Util.newColour(0xffcc00), Util.newColour(0xffcc00), "gold", Util.newArrayListOfValues("legendary")),

	// Inventory colours:
	CURRENCY_GOLD(BaseColour.GOLD, "gold"),
	CURRENCY_SILVER(BaseColour.SILVER, "gold"),
	CURRENCY_COPPER(BaseColour.COPPER, "gold"),

	MILK(BaseColour.YELLOW_LIGHT, "light yellow",  Util.newArrayListOfValues("milk", "lactation")),
	CUM(BaseColour.BLUE_LIGHT, "light blue",  Util.newArrayListOfValues("cum", "cummed", "dirty")),
	GIRLCUM(BaseColour.PINK_LIGHT, "light pink",  Util.newArrayListOfValues("girlcum", "gcum")),
	
	SEALED(BaseColour.PINK_DEEP, "pink"),
	DISPLACED(BaseColour.CRIMSON, "crimson"),

	// Text colours:
	TEXT(Util.newColour(0xDDDDDD), Util.newColour(0x262626), "grey", Util.newArrayListOfValues("text")),
	TEXT_HALF_GREY(Util.newColour(0xBBBBBB), Util.newColour(0x444444), "grey", Util.newArrayListOfValues("halfDisabled")),
	TEXT_GREY(Util.newColour(0x777777), Util.newColour(0x777777), "grey", Util.newArrayListOfValues("disabled")),
	TEXT_GREY_DARK(Util.newColour(0x444444), Util.newColour(0xcccccc), "grey", Util.newArrayListOfValues("disabledDark")),

	// Standard colours used for clothing:
	CLOTHING_WHITE(Util.newColour(0xdddddd), Util.newColour(0xdddddd), "white"),
	CLOTHING_GREY(Util.newColour(0x777777), Util.newColour(0x777777), "grey"),
	CLOTHING_BLACK(Util.newColour(0x333333), Util.newColour(0x333333), "black"),
	
	CLOTHING_RED_DARK(Util.newColour(0x900020), Util.newColour(0x900020), "burgundy"),
	CLOTHING_RED_BRIGHT(Util.newColour(0xFA2424), Util.newColour(0xFA2424), "bright red"),
	CLOTHING_RED(Util.newColour(0xD84646), Util.newColour(0xD84646), "red"),
	CLOTHING_BROWN(Util.newColour(0xC87137), Util.newColour(0xC87137), "brown"),
	CLOTHING_BROWN_DARK(Util.newColour(0x63391C), Util.newColour(0x63391C), "dark brown"),
	CLOTHING_ORANGE(Util.newColour(0xE79F6F), Util.newColour(0xE79F6F), "orange"),
	CLOTHING_ORANGE_BRIGHT(Util.newColour(0xFF7900), Util.newColour(0xFF7900), "bright orange"),
	CLOTHING_ORANGE_DARK(Util.newColour(0xE56D00), Util.newColour(0xE56D00), "dark orange"),
	CLOTHING_TAN(BaseColour.TAN, "tan"),
	CLOTHING_YELLOW(Util.newColour(0xE2C360), Util.newColour(0xE2C360), "yellow"),
	CLOTHING_GREEN_LIME(Util.newColour(0xD0E37D), Util.newColour(0xD0E37D), "lime green"),
	CLOTHING_GREEN(Util.newColour(0x74AA74), Util.newColour(0x74AA74), "green"),
	CLOTHING_GREEN_DARK(Util.newColour(0x3B6F3D), Util.newColour(0x3B6F3D), "dark green"),
	CLOTHING_TURQUOISE(Util.newColour(0x6EC4B3), Util.newColour(0x6EC4B3), "turquoise"),
	CLOTHING_BLUE_LIGHT(Util.newColour(0x72CFE3), Util.newColour(0x72CFE3), "light blue"),
	CLOTHING_BLUE(Util.newColour(0x3971C6), Util.newColour(0x3971C6), "blue"),
	CLOTHING_BLUE_DARK(Util.newColour(0x003C89), Util.newColour(0x003C89), "dark blue"),
	CLOTHING_PURPLE_DARK(Util.newColour(0x674A95), Util.newColour(0x674A95), "dark purple"),
	CLOTHING_PURPLE(Util.newColour(0xA382D3), Util.newColour(0xA382D3), "purple"),
	CLOTHING_PURPLE_LIGHT(Util.newColour(0xC58ED7), Util.newColour(0xC58ED7), "violet"),
	CLOTHING_PINK_LIGHT(Util.newColour(0xF4B3F4), Util.newColour(0xF4B3F4), "light pink"),
	CLOTHING_PINK(Util.newColour(0xD75086), Util.newColour(0xD75086), "pink"),
	
	CLOTHING_BLACK_STEEL(Util.newColour(0x333333), Util.newColour(0x333333), "black"),
	CLOTHING_STEEL(Util.newColour(0x969696), Util.newColour(0x969696), "steel"),
	CLOTHING_COPPER(Util.newColour(0xD46F2B), Util.newColour(0xD46F2B), "copper"),
	CLOTHING_SILVER(Util.newColour(0xC4C4C4), Util.newColour(0xC4C4C4), "silver"),
	CLOTHING_GOLD(Util.newColour(0xEBC633), Util.newColour(0xEBC633), "gold"),
	CLOTHING_ROSE_GOLD(Util.newColour(0xE7C1BB), Util.newColour(0xE7C1BB), "rose gold"),
	CLOTHING_PLATINUM(Util.newColour(0xE4E5E2), Util.newColour(0xE4E5E2), "platinum"),
	
	// For special use with rainbow clothing:
	CLOTHING_MULTICOLOURED(Util.newColour(0xff3030), Util.newColour(0xccffff), "multicoloured"),

	// Body parts:

	// Skin (Human and Demon):
	SKIN_PORCELAIN(Util.newColour(0xDBCDB9), Util.newColour(0xDBCDB9), "porcelain"),
	SKIN_PALE(Util.newColour(0xFBF4E9), Util.newColour(0x534946), "pale"),
	SKIN_LIGHT(BaseColour.YELLOW_LIGHT, "light"),
	SKIN_ROSY(Util.newColour(0xDDAA93), Util.newColour(0xDDAA93), "rosy"),
	SKIN_TANNED(Util.newColour(0xC39D6B), Util.newColour(0xC39D6B), "Tanned"),
	SKIN_OLIVE(BaseColour.TAN, "olive"),
	SKIN_CHOCOLATE(Util.newColour(0x59372D), Util.newColour(0x59372D), "chocolate"),
	SKIN_DARK(BaseColour.BROWN_DARK, "dark"),
	SKIN_EBONY(BaseColour.BLACK, "ebony"),
	
	SKIN_RED(BaseColour.CRIMSON, "scarlet"),
	SKIN_RED_DARK(BaseColour.RED_DARK, "dark red"),
	SKIN_BROWN(BaseColour.BROWN, "brown"),
	SKIN_YELLOW(BaseColour.YELLOW, "yellow"),
	SKIN_AMBER(BaseColour.AMBER, "amber"),
	SKIN_PINK(BaseColour.PINK, "pink"),
	SKIN_PINK_LIGHT(BaseColour.PINK_LIGHT, "light pink"),
	SKIN_GREEN(BaseColour.GREEN, "green"),
	SKIN_GREEN_DARK(BaseColour.GREEN_DARK, "dark green"),
	SKIN_BLUE_LIGHT(BaseColour.BLUE_LIGHT, "light blue"),
	SKIN_BLUE(BaseColour.BLUE, "blue"),
	SKIN_BLUE_DARK(BaseColour.BLUE_DARK, "dark blue"),
	SKIN_LILAC(BaseColour.LILAC, "lilac"),
	SKIN_PURPLE(BaseColour.PURPLE, "purple"),
	SKIN_PURPLE_DARK(BaseColour.PURPLE_DARK, "dark purple"),
	SKIN_IVORY(BaseColour.WHITE, "ivory"),
	SKIN_GREY(BaseColour.GREY, "grey"),

	// Slime types:

	SLIME_RED(BaseColour.RED, "translucent red"),
	SLIME_RED_DARK(BaseColour.RED_DARK, "translucent dark red"),
	SLIME_BROWN_DARK(BaseColour.BROWN_DARK, "translucent dark brown"),
	SLIME_BROWN(BaseColour.BROWN, "translucent brown"),
	SLIME_ORANGE(BaseColour.ORANGE, "translucent orange"),
	SLIME_TAN(BaseColour.TAN, "translucent tan"),
	SLIME_YELLOW(BaseColour.YELLOW, "translucent yellow"),
	SLIME_AMBER(BaseColour.AMBER, "translucent amber"),
	SLIME_PINK_DARK(BaseColour.PINK_DEEP, "translucent dark pink"),
	SLIME_PINK(BaseColour.PINK, "translucent pink"),
	SLIME_PINK_LIGHT(BaseColour.PINK_LIGHT, "translucent light pink"),
	SLIME_GREEN(BaseColour.GREEN, "translucent green"),
	SLIME_GREEN_DARK(BaseColour.GREEN_DARK, "translucent dark green"),
	SLIME_BLUE_LIGHT(BaseColour.BLUE_LIGHT, "translucent light blue"),
	SLIME_BLUE(BaseColour.BLUE, "translucent blue"),
	SLIME_BLUE_DARK(BaseColour.BLUE_DARK, "translucent dark blue"),
	SLIME_LILAC(BaseColour.LILAC, "translucent lilac"),
	SLIME_PURPLE(BaseColour.PURPLE, "translucent purple"),
	SLIME_PURPLE_DARK(BaseColour.PURPLE_DARK, "translucent dark purple"),
	SLIME_CLEAR(BaseColour.WHITE, "clear"),
	SLIME_GREY(BaseColour.GREY, "translucent grey"),
	SLIME_BLACK(BaseColour.BLACK, "translucent black"),
	SLIME_WHITE(BaseColour.WHITE, "translucent white"),

	// Feathers:
	FEATHERS_RED(BaseColour.RED, "red"),
	FEATHERS_RED_DARK(BaseColour.RED_DARK, "dark red"),
	FEATHERS_BROWN_DARK(BaseColour.BROWN_DARK, "dark brown"),
	FEATHERS_BROWN(BaseColour.BROWN, "brown"),
	FEATHERS_TAN(BaseColour.TAN, "tan"),
	FEATHERS_ORANGE(BaseColour.ORANGE, "orange"),
	FEATHERS_GINGER(BaseColour.GINGER, "ginger"),
	FEATHERS_BLEACH_BLONDE(BaseColour.YELLOW_LIGHT, "bleach-blonde"),
	FEATHERS_YELLOW(BaseColour.YELLOW, "yellow"),
	FEATHERS_AMBER(BaseColour.AMBER, "amber"),
	FEATHERS_PINK(BaseColour.PINK, "pink"),
	FEATHERS_PINK_LIGHT(BaseColour.PINK_LIGHT, "light pink"),
	FEATHERS_GREEN(BaseColour.GREEN, "green"),
	FEATHERS_GREEN_DARK(BaseColour.GREEN_DARK, "dark green"),
	FEATHERS_BLUE_LIGHT(BaseColour.BLUE_LIGHT, "light blue"),
	FEATHERS_BLUE(BaseColour.BLUE, "blue"),
	FEATHERS_BLUE_DARK(BaseColour.BLUE_DARK, "dark blue"),
	FEATHERS_LILAC(BaseColour.LILAC, "lilac"),
	FEATHERS_PURPLE(BaseColour.PURPLE, "purple"),
	FEATHERS_PURPLE_DARK(BaseColour.PURPLE_DARK, "dark purple"),
	FEATHERS_GREY(BaseColour.GREY, "grey"),
	FEATHERS_BLACK(BaseColour.BLACK, "black"),
	FEATHERS_WHITE(BaseColour.WHITE, "white"),

	// Horns:
	HORN_WHITE(BaseColour.WHITE, "ivory"),
	HORN_GREY(BaseColour.GREY, "grey"),
	HORN_DARK_GREY(BaseColour.GREY_DARK, "dark-grey"),
	HORN_BLACK(BaseColour.BLACK, "black"),
	
	HORN_RED(BaseColour.RED, "red"),
	HORN_SCARLET(BaseColour.CRIMSON, "scarlet"),
	HORN_BROWN(BaseColour.BROWN, "brown"),
	HORN_DARK_BROWN(BaseColour.BROWN_DARK, "dark brown"),
	HORN_AMBER(BaseColour.AMBER, "amber"),
	HORN_PINK(BaseColour.PINK_LIGHT, "light pink"),
	HORN_GREEN(BaseColour.GREEN, "green"),
	HORN_BLUE(BaseColour.BLUE_LIGHT, "light blue"),
	HORN_LILAC(BaseColour.LILAC, "lilac"),
	HORN_PURPLE(BaseColour.PURPLE, "purple"),

	
	// Antlers:
	ANTLER_WHITE(BaseColour.WHITE, "ivory"),
	ANTLER_TAN(BaseColour.TAN, "tan"),
	ANTLER_BROWN(BaseColour.BROWN, "brown"),
	ANTLER_DARK_BROWN(BaseColour.BROWN_DARK, "dark brown"),
	ANTLER_GREY(BaseColour.GREY, "grey"),
	ANTLER_DARK_GREY(BaseColour.GREY_DARK, "dark-grey"),
	ANTLER_BLACK(BaseColour.BLACK, "black"),
	
	ANTLER_RED(BaseColour.RED, "red"),
	ANTLER_SCARLET(BaseColour.CRIMSON, "scarlet"),
	ANTLER_AMBER(BaseColour.AMBER, "amber"),
	ANTLER_PINK(BaseColour.PINK_LIGHT, "light pink"),
	ANTLER_GREEN(BaseColour.GREEN, "green"),
	ANTLER_BLUE(BaseColour.BLUE_LIGHT, "light blue"),
	ANTLER_LILAC(BaseColour.LILAC, "lilac"),
	ANTLER_PURPLE(BaseColour.PURPLE, "purple"),

	// Orifices:
	ORIFICE_INTERIOR(BaseColour.ROSE, "fleshy-pink"),

	// Misc:
	TONGUE(BaseColour.ROSE, "pink"),

	// Generic colours:
	COVERING_TAN(BaseColour.TAN, "tan"),
	COVERING_BROWN(BaseColour.BROWN, "brown"),
	COVERING_BROWN_DARK(BaseColour.BROWN_DARK, "dark brown"),
	COVERING_BLACK(BaseColour.BLACK, "black"),
	COVERING_GREY(BaseColour.GREY, "grey"),
	COVERING_DIRTY_BLONDE(BaseColour.TAN, "dirty-blonde"),
	COVERING_BLONDE(BaseColour.YELLOW, "blonde"),
	COVERING_BLEACH_BLONDE(BaseColour.YELLOW_LIGHT, "bleach-blonde"),
	COVERING_GINGER(BaseColour.GINGER, "ginger"),
	COVERING_ORANGE(BaseColour.ORANGE, "orange"),
	COVERING_AMBER(BaseColour.AMBER, "amber"),
	COVERING_RED(BaseColour.RED, "red"),
	COVERING_RED_DARK(BaseColour.RED_DARK, "dark red"),
	COVERING_AUBURN(BaseColour.AUBURN, "auburn"),
	COVERING_WHITE(BaseColour.WHITE, "white"),
	COVERING_SILVER(BaseColour.GREY, "silver"),
	COVERING_BLUE_LIGHT(BaseColour.BLUE_LIGHT, "light blue"),
	COVERING_BLUE(BaseColour.BLUE, "blue"),
	COVERING_BLUE_DARK(BaseColour.BLUE_DARK, "dark blue"),
	COVERING_PURPLE(BaseColour.PURPLE, "purple"),
	COVERING_PURPLE_DARK(BaseColour.PURPLE_DARK, "dark purple"),
	COVERING_PINK(BaseColour.PINK_LIGHT, "light pink"),
	COVERING_PINK_DARK(BaseColour.PINK, "pink"),
	COVERING_GREEN(BaseColour.GREEN, "green"),
	COVERING_GREEN_DARK(BaseColour.GREEN_DARK, "dark green"),
	
	// Special nail polish:
	COVERING_CLEAR(BaseColour.WHITE, "clear"),
	COVERING_NONE(BaseColour.GREY, "none"),

	// Eye colours:
	EYE_WHITE(BaseColour.WHITE, "white"),
	
	EYE_BROWN(BaseColour.BROWN, "brown"),
	EYE_BLUE(BaseColour.BLUE_LIGHT, "blue"),
	EYE_HAZEL(BaseColour.TAN, "hazel"),
	EYE_AQUA(BaseColour.AQUA, "aqua"),
	EYE_GREEN(BaseColour.GREEN, "green"),
	EYE_GREY(BaseColour.GREY, "grey"),

	EYE_LILAC(BaseColour.LILAC, "lilac"),
	EYE_PURPLE(BaseColour.PURPLE, "purple"),
	EYE_VIOLET(BaseColour.VIOLET, "violet"),
	EYE_CRIMSON(BaseColour.CRIMSON, "crimson"),
	EYE_GOLD(BaseColour.GOLD, "golden"),
	EYE_SILVER(BaseColour.SILVER, "silver"),
	
	EYE_YELLOW(BaseColour.YELLOW, "yellow"),
	EYE_AMBER(BaseColour.AMBER, "amber"),
	EYE_RED(BaseColour.RED, "red"),
	EYE_PINK(BaseColour.PINK, "pink"),
	EYE_ORANGE(BaseColour.ORANGE, "orange"),
	EYE_BLACK(BaseColour.BLACK, "black");
	
	

	// Skin/fur/body part groups:
	
	public static List<Colour> humanSkinColours = Util.newArrayListOfValues(

			Colour.SKIN_PORCELAIN,
			Colour.SKIN_PALE,
			Colour.SKIN_LIGHT,
			Colour.SKIN_ROSY,
			Colour.SKIN_TANNED,
			Colour.SKIN_OLIVE,
			Colour.SKIN_CHOCOLATE,
			Colour.SKIN_DARK,
			Colour.SKIN_EBONY);

	public static List<Colour> ratSkinColours = Util.newArrayListOfValues(
			Colour.SKIN_PINK_LIGHT);
	
	public static List<Colour> demonSkinColours = Util.newArrayListOfValues(

			Colour.SKIN_PORCELAIN,
			Colour.SKIN_PALE,
			Colour.SKIN_LIGHT,
			Colour.SKIN_ROSY,
			Colour.SKIN_TANNED,
			Colour.SKIN_OLIVE,
			Colour.SKIN_CHOCOLATE,
			Colour.SKIN_DARK,
			Colour.SKIN_EBONY,
			Colour.SKIN_IVORY,
			Colour.SKIN_GREY,
			Colour.SKIN_RED,
			Colour.SKIN_RED_DARK,
			Colour.SKIN_BROWN,
			Colour.SKIN_AMBER,
			Colour.SKIN_YELLOW,
			Colour.SKIN_GREEN,
			Colour.SKIN_GREEN_DARK,
			Colour.SKIN_BLUE_LIGHT,
			Colour.SKIN_BLUE,
			Colour.SKIN_BLUE_DARK,
			Colour.SKIN_LILAC,
			Colour.SKIN_PURPLE,
			Colour.SKIN_PURPLE_DARK,
			Colour.SKIN_PINK_LIGHT,
			Colour.SKIN_PINK);

	public static List<Colour> allSkinColours = Util.newArrayListOfValues(
			Colour.SKIN_PORCELAIN,
			Colour.SKIN_PALE,
			Colour.SKIN_LIGHT,
			Colour.SKIN_ROSY,
			Colour.SKIN_TANNED,
			Colour.SKIN_OLIVE,
			Colour.SKIN_CHOCOLATE,
			Colour.SKIN_DARK,
			Colour.SKIN_EBONY,
			Colour.SKIN_IVORY,
			Colour.SKIN_GREY,
			Colour.SKIN_RED,
			Colour.SKIN_RED_DARK,
			Colour.SKIN_BROWN,
			Colour.SKIN_AMBER,
			Colour.SKIN_YELLOW,
			Colour.SKIN_GREEN,
			Colour.SKIN_GREEN_DARK,
			Colour.SKIN_BLUE_LIGHT,
			Colour.SKIN_BLUE,
			Colour.SKIN_BLUE_DARK,
			Colour.SKIN_LILAC,
			Colour.SKIN_PURPLE,
			Colour.SKIN_PURPLE_DARK,
			Colour.SKIN_PINK_LIGHT,
			Colour.SKIN_PINK);


	public static List<Colour> allSlimeColours = Util.newArrayListOfValues(
			Colour.SLIME_CLEAR,
			Colour.SLIME_WHITE,
			Colour.SLIME_GREY,
			Colour.SLIME_BLACK,
			Colour.SLIME_RED,
			Colour.SLIME_RED_DARK,
			Colour.SLIME_BROWN_DARK,
			Colour.SLIME_BROWN,
			Colour.SLIME_TAN,
			Colour.SLIME_YELLOW,
			Colour.SLIME_AMBER,
			Colour.SLIME_GREEN,
			Colour.SLIME_GREEN_DARK,
			Colour.SLIME_BLUE_LIGHT,
			Colour.SLIME_BLUE,
			Colour.SLIME_BLUE_DARK,
			Colour.SLIME_LILAC,
			Colour.SLIME_PURPLE,
			Colour.SLIME_PURPLE_DARK,
			Colour.SLIME_PINK_DARK,
			Colour.SLIME_PINK,
			Colour.SLIME_PINK_LIGHT
			);
	
	public static List<Colour> allFeatherColours = Util.newArrayListOfValues(
			Colour.FEATHERS_WHITE,
			Colour.FEATHERS_GREY,
			Colour.FEATHERS_BLACK,
			Colour.FEATHERS_RED,
			Colour.FEATHERS_RED_DARK,
			Colour.FEATHERS_BROWN_DARK,
			Colour.FEATHERS_BROWN,
			Colour.FEATHERS_TAN,
			Colour.FEATHERS_ORANGE,
			Colour.FEATHERS_GINGER,
			Colour.FEATHERS_BLEACH_BLONDE,
			Colour.FEATHERS_YELLOW,
			Colour.FEATHERS_AMBER,
			Colour.FEATHERS_GREEN,
			Colour.FEATHERS_GREEN_DARK,
			Colour.FEATHERS_BLUE_LIGHT,
			Colour.FEATHERS_BLUE,
			Colour.FEATHERS_BLUE_DARK,
			Colour.FEATHERS_LILAC,
			Colour.FEATHERS_PURPLE,
			Colour.FEATHERS_PURPLE_DARK,
			Colour.FEATHERS_PINK,
			Colour.FEATHERS_PINK_LIGHT
			);
			
	public static List<Colour> naturalFurColours = Util.newArrayListOfValues(
			Colour.COVERING_WHITE,
			Colour.COVERING_SILVER,
			Colour.COVERING_BLONDE,
			Colour.COVERING_GINGER,
			Colour.COVERING_BROWN,
			Colour.COVERING_TAN,
			Colour.COVERING_BROWN_DARK,
			Colour.COVERING_GREY,
			Colour.COVERING_BLACK);

	public static List<Colour> dyeFurColours = Util.newArrayListOfValues(
			Colour.COVERING_BLEACH_BLONDE,
			Colour.COVERING_BLUE_LIGHT,
			Colour.COVERING_BLUE,
			Colour.COVERING_BLUE_DARK,
			Colour.COVERING_GREEN,
			Colour.COVERING_GREEN_DARK,
			Colour.COVERING_PINK,
			Colour.COVERING_PINK_DARK,
			Colour.COVERING_PURPLE,
			Colour.COVERING_PURPLE_DARK,
			Colour.COVERING_RED,
			Colour.COVERING_RED_DARK);

	public static List<Colour> naturalScaleColours = Util.newArrayListOfValues(
			Colour.COVERING_WHITE,
			Colour.COVERING_BROWN,
			Colour.COVERING_TAN,
			Colour.COVERING_BROWN_DARK,
			Colour.COVERING_BLACK);

	public static List<Colour> dyeScaleColours = Util.newArrayListOfValues(
			Colour.COVERING_BLEACH_BLONDE,
			Colour.COVERING_BLONDE,
			Colour.COVERING_BLUE_LIGHT,
			Colour.COVERING_BLUE,
			Colour.COVERING_BLUE_DARK,
			Colour.COVERING_GINGER,
			Colour.COVERING_GREEN,
			Colour.COVERING_GREEN_DARK,
			Colour.COVERING_PINK,
			Colour.COVERING_PINK_DARK,
			Colour.COVERING_PURPLE,
			Colour.COVERING_PURPLE_DARK,
			Colour.COVERING_RED,
			Colour.COVERING_RED_DARK);

	public static List<Colour> hornColours = Util.newArrayListOfValues(
			Colour.HORN_WHITE,
			Colour.HORN_GREY,
			Colour.HORN_DARK_GREY,
			Colour.HORN_BLACK);
	
	public static List<Colour> dyeHornColours = Util.newArrayListOfValues(
			Colour.HORN_RED,
			Colour.HORN_SCARLET,
			Colour.HORN_BROWN,
			Colour.HORN_DARK_BROWN,
			Colour.HORN_AMBER,
			Colour.HORN_PINK,
			Colour.HORN_GREEN,
			Colour.HORN_BLUE,
			Colour.HORN_LILAC,
			Colour.HORN_PURPLE);

	
	// Antlers:
	public static List<Colour> antlerColours = Util.newArrayListOfValues(
			Colour.ANTLER_WHITE,
			Colour.ANTLER_TAN,
			Colour.ANTLER_BROWN,
			Colour.ANTLER_DARK_BROWN,
			Colour.ANTLER_GREY,
			Colour.ANTLER_DARK_GREY,
			Colour.ANTLER_BLACK);
	

	public static List<Colour> dyeAntlerColours = Util.newArrayListOfValues(
			Colour.ANTLER_RED,
			Colour.ANTLER_SCARLET,
			Colour.ANTLER_AMBER,
			Colour.ANTLER_PINK,
			Colour.ANTLER_GREEN,
			Colour.ANTLER_BLUE,
			Colour.ANTLER_LILAC,
			Colour.ANTLER_PURPLE);
	
	// Hair:
	
	public static List<Colour> naturalHairColours = Util.newArrayListOfValues(
			Colour.COVERING_WHITE,
			Colour.COVERING_SILVER,
			Colour.COVERING_BLONDE,
			Colour.COVERING_DIRTY_BLONDE,
			Colour.COVERING_GINGER,
			Colour.COVERING_BROWN,
			Colour.COVERING_BROWN_DARK,
			Colour.COVERING_AUBURN,
			Colour.COVERING_GREY,
			Colour.COVERING_BLACK);
	
	public static List<Colour> dyeHairColours = Util.newArrayListOfValues(
			Colour.COVERING_BLEACH_BLONDE,
			Colour.COVERING_BLUE_LIGHT,
			Colour.COVERING_BLUE,
			Colour.COVERING_BLUE_DARK,
			Colour.COVERING_GREEN,
			Colour.COVERING_GREEN_DARK,
			Colour.COVERING_PINK,
			Colour.COVERING_PINK_DARK,
			Colour.COVERING_PURPLE,
			Colour.COVERING_PURPLE_DARK,
			Colour.COVERING_ORANGE,
			Colour.COVERING_AMBER,
			Colour.COVERING_RED,
			Colour.COVERING_RED_DARK);
	
	// Eyes:
	
	public static List<Colour> naturalIrisColours = Util.newArrayListOfValues(
			Colour.EYE_BROWN,
			Colour.EYE_AMBER,
			Colour.EYE_HAZEL,
			Colour.EYE_BLUE,
			Colour.EYE_AQUA,
			Colour.EYE_GREEN,
			Colour.EYE_GREY);
	
	public static List<Colour> dyeIrisColours = Util.newArrayListOfValues(
			Colour.EYE_SILVER,
			Colour.EYE_YELLOW,
			Colour.EYE_GOLD,
			Colour.EYE_RED,
			Colour.EYE_CRIMSON,
			Colour.EYE_ORANGE,
			Colour.EYE_PINK,
			Colour.EYE_VIOLET,
			Colour.EYE_LILAC,
			Colour.EYE_PURPLE,
			Colour.EYE_BLACK);

	public static List<Colour> naturalDemonIrisColours = Util.newArrayListOfValues(
			Colour.EYE_BROWN,
			Colour.EYE_AMBER,
			Colour.EYE_HAZEL,
			Colour.EYE_BLUE,
			Colour.EYE_AQUA,
			Colour.EYE_GREEN,
			Colour.EYE_GREY,
			Colour.EYE_RED,
			Colour.EYE_CRIMSON,
			Colour.EYE_ORANGE,
			Colour.EYE_YELLOW,
			Colour.EYE_PINK,
			Colour.EYE_VIOLET,
			Colour.EYE_LILAC,
			Colour.EYE_PURPLE,
			Colour.EYE_BLACK);
	
	public static List<Colour> dyeDemonIrisColours = Util.newArrayListOfValues(
			Colour.EYE_SILVER,
			Colour.EYE_GOLD);
	
	
	public static List<Colour> naturalPredatorIrisColours = Util.newArrayListOfValues(
			Colour.EYE_BROWN,
			Colour.EYE_AMBER,
			Colour.EYE_YELLOW,
			Colour.EYE_BLUE,
			Colour.EYE_AQUA,
			Colour.EYE_GREEN,
			Colour.EYE_GREY);
	
	public static List<Colour> dyePredatorIrisColours = Util.newArrayListOfValues(
			Colour.EYE_SILVER,
			Colour.EYE_GOLD,
			Colour.EYE_RED,
			Colour.EYE_CRIMSON,
			Colour.EYE_ORANGE,
			Colour.EYE_PINK,
			Colour.EYE_VIOLET,
			Colour.EYE_LILAC,
			Colour.EYE_PURPLE,
			Colour.EYE_BLACK);
	
	
	public static List<Colour> naturalPupilColours = Util.newArrayListOfValues(
			Colour.EYE_BLACK);
	
	public static List<Colour> dyePupilColours = Util.newArrayListOfValues(
			Colour.EYE_WHITE,
			Colour.EYE_SILVER,
			Colour.EYE_BROWN,
			Colour.EYE_BLUE,
			Colour.EYE_AQUA,
			Colour.EYE_GREEN,
			Colour.EYE_GREY,
			Colour.EYE_YELLOW,
			Colour.EYE_GOLD,
			Colour.EYE_RED,
			Colour.EYE_CRIMSON,
			Colour.EYE_ORANGE,
			Colour.EYE_AMBER,
			Colour.EYE_PINK,
			Colour.EYE_VIOLET,
			Colour.EYE_LILAC,
			Colour.EYE_PURPLE);
	
	public static List<Colour> naturalScleraColours = Util.newArrayListOfValues(
			Colour.EYE_WHITE);
	
	public static List<Colour> dyeScleraColours = Util.newArrayListOfValues(
			Colour.EYE_BLACK,
			Colour.EYE_SILVER,
			Colour.EYE_BROWN,
			Colour.EYE_BLUE,
			Colour.EYE_AQUA,
			Colour.EYE_GREEN,
			Colour.EYE_GREY,
			Colour.EYE_YELLOW,
			Colour.EYE_GOLD,
			Colour.EYE_RED,
			Colour.EYE_CRIMSON,
			Colour.EYE_ORANGE,
			Colour.EYE_AMBER,
			Colour.EYE_PINK,
			Colour.EYE_VIOLET,
			Colour.EYE_LILAC,
			Colour.EYE_PURPLE);
	
	
	
	private Color colour, lightColour;
	private String name;
	private List<String> formattingNames;

	private Colour(Color colour, Color lightColour, String name) {
		this.colour = colour;
		this.lightColour = lightColour;
		this.name = name;
	}
	
	private Colour(BaseColour colour, String name) {
		this.colour = colour.getColour();
		this.lightColour = colour.getLightColour();
		this.name = name;
	}
	
	// Constructors with formatting names:
	private Colour(Color colour, Color lightColour, String name, List<String> formattingNames) {
		this.colour = colour;
		this.lightColour = lightColour;
		this.name = name;
		this.formattingNames=formattingNames;
	}
	
	private Colour(BaseColour colour, String name, List<String> formattingNames) {
		this.colour = colour.getColour();
		this.lightColour = colour.getLightColour();
		this.name = name;
		this.formattingNames=formattingNames;
	}

	/**
	 * Returns a String in the format RRGGBB
	 * 
	 * @return
	 */
	public String toWebHexString() {
		return "#"+getColor().toString().substring(2, 8);
	}

	public Color getColor() {
		if(Main.getProperties()!=null) {
			if(Main.getProperties().hasValue(PropertyValue.lightTheme))
				return lightColour;
			else
				return colour;
			
		} else {
			return colour;
		}
	}

	public String getName() {
		return name;
	}

	/**
	 * @return An array of length 5, with [0] being darkest, [4] being lightest.
	 */
	public String[] getShades() {
		return getShades(5);
	}
	
	public String[] getShades(int shadesCount) {
		String[] shadesString = new String[shadesCount];
		float luminosity = -0.5f;
		float increment = (Math.abs(luminosity)*2)/(shadesCount-1);
		int red = Integer.parseInt(colour.toString().substring(2, 4), 16);
		int gre = Integer.parseInt(colour.toString().substring(4, 6), 16);
		int blu = Integer.parseInt(colour.toString().substring(6, 8), 16);
		int r, g, b;

		for (int i = 0; i < shadesCount; i++) {
			r = red + (int)(red * (i * increment + luminosity));
			r = Math.max(Math.min(r, 255), 0);

			g = gre + (int)(gre * (i * increment + luminosity));
			g = Math.max(Math.min(g, 255), 0);

			b = blu + (int)(blu * (i * increment + luminosity));
			b = Math.max(Math.min(b, 255), 0);

			shadesString[i] = String.format("#%02X%02X%02X", r, g, b);
		}

		return shadesString;
	}

	public List<String> getFormattingNames() {
		return formattingNames;
	}

}
