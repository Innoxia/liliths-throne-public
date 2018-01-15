package com.lilithsthrone.utils;

import java.util.List;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util.ListValue;

import javafx.scene.paint.Color;

/**
 * @since 0.1.0
 * @version 0.1.84
 * @author Innoxia
 */
public enum Colour {
	
	// This class and BaseColour are beyond saving x_x
	
	BASE_WHITE(BaseColour.WHITE, "white"),
	BASE_GREY(BaseColour.GREY, "grey"),
	
	BASE_ROSE(BaseColour.ROSE, "rose"),
	BASE_LILAC(BaseColour.LILAC, "lilac"),
	BASE_LILAC_LIGHT(BaseColour.LILAC_LIGHT, "light lilac"),
	BASE_PURPLE_DARK(BaseColour.PURPLE_DARK, "dark purple"),
	BASE_PURPLE(BaseColour.PURPLE, "purple"),
	BASE_PURPLE_LIGHT(BaseColour.PURPLE_LIGHT, "light purple"),
	
	BASE_VIOLET(BaseColour.VIOLET, "violet"),
	BASE_PINK(BaseColour.PINK, "pink"),
	BASE_PINK_LIGHT(BaseColour.PINK_LIGHT, "light pink"),
	BASE_PINK_DEEP(BaseColour.PINK_DEEP, "deep pink"),
		
	BASE_MAGENTA(BaseColour.MAGENTA, "magenta"),
	BASE_CRIMSON(BaseColour.CRIMSON, "crimson"),
	BASE_RED(BaseColour.RED, "red"),
	BASE_RED_LIGHT(BaseColour.RED_LIGHT, "light red"),
	
	BASE_TAN(BaseColour.TAN, "tan"),
	BASE_BROWN(BaseColour.BROWN, "brown"),
	BASE_BROWN_DARK(BaseColour.BROWN_DARK, "dark brown"),
	BASE_ORANGE(BaseColour.ORANGE, "orange"),
	BASE_GINGER(BaseColour.GINGER, "ginger"),
	
	BASE_GOLD(BaseColour.GOLD, "gold"),
	BASE_YELLOW(BaseColour.YELLOW, "yellow"),
	BASE_YELLOW_LIGHT(BaseColour.YELLOW_LIGHT, "light yellow"),
	
	BASE_GREEN_LIME(BaseColour.GREEN_LIME, "lime green"),
	BASE_GREEN_LIGHT(BaseColour.GREEN_LIGHT, "light green"),
	BASE_GREEN(BaseColour.GREEN, "green"),
	
	BASE_AQUA(BaseColour.AQUA, "aqua"),
	BASE_TEAL(BaseColour.TEAL, "teal"),
	BASE_BLUE_LIGHT(BaseColour.BLUE_LIGHT, "light blue"),
	BASE_BLUE(BaseColour.BLUE, "blue"),
	BASE_BLUE_STEEL(BaseColour.BLUE_STEEL, "steely blue"),
	
	BASE_BLACK(BaseColour.BLACK, "black"),
	
	
	
	// Game colours:
	GENERIC_SEX(BaseColour.PINK_LIGHT, "pink", Util.newArrayListOfValues(new ListValue<>("sex"))),
	GENERIC_COMBAT(BaseColour.CRIMSON, "crimson"),
	GENERIC_ARCANE(BaseColour.PINK, "pink", Util.newArrayListOfValues(new ListValue<>("arcane"))),
	GENERIC_TERRIBLE(BaseColour.PURPLE, "purple", Util.newArrayListOfValues(new ListValue<>("terrible"))),
	GENERIC_MINOR_BAD(BaseColour.RED_LIGHT, "red", Util.newArrayListOfValues(new ListValue<>("minorBad"))),
	GENERIC_MINOR_GOOD(BaseColour.GREEN_LIGHT, "light green", Util.newArrayListOfValues(new ListValue<>("minorGood"))),
	GENERIC_BAD(BaseColour.RED, "red", Util.newArrayListOfValues(new ListValue<>("bad"))),
	GENERIC_GOOD(BaseColour.GREEN, "green", Util.newArrayListOfValues(new ListValue<>("good"))),
	GENERIC_EXCELLENT(BaseColour.GOLD, "gold", Util.newArrayListOfValues(new ListValue<>("excellent"))),
	GENERIC_ATTRIBUTE(BaseColour.MAGENTA, "magenta"),
	GENERIC_EXPERIENCE(BaseColour.BLUE_LIGHT, "light blue"),

	PERK(BaseColour.AQUA, "aqua"),
	FETISH(BaseColour.ROSE, "rose", Util.newArrayListOfValues(new ListValue<>("fetish"))),
	STATUS_EFFECT(BaseColour.YELLOW, "yellow"),
	SPECIAL_ATTACK(BaseColour.ORANGE, "orange"),
	STATUS_EFFECT_TIME_OVERFLOW(BaseColour.BLUE, "aqua"),
	STATUS_EFFECT_TIME_HIGH(BaseColour.GREEN_LIGHT, "green"),
	STATUS_EFFECT_TIME_MEDIUM(BaseColour.ORANGE, "orange"),
	STATUS_EFFECT_TIME_LOW(BaseColour.RED, "red"),

	RACE_HUMAN(BaseColour.BLUE_STEEL, "pale blue", Util.newArrayListOfValues(new ListValue<>("human"))),
	RACE_DEMON(BaseColour.PURPLE_LIGHT, "light purple", Util.newArrayListOfValues(new ListValue<>("demon"))),
	RACE_ANGEL(BaseColour.AQUA, "aqua", Util.newArrayListOfValues(new ListValue<>("angel"))),
	RACE_DOG_MORPH(BaseColour.BROWN, "brown", Util.newArrayListOfValues(new ListValue<>("dogMorph"), new ListValue<>("dog"))),
	RACE_CAT_MORPH(BaseColour.VIOLET, "violet", Util.newArrayListOfValues(new ListValue<>("catMorph"), new ListValue<>("cat"))),
	RACE_COW_MORPH(BaseColour.TAN, "tan", Util.newArrayListOfValues(new ListValue<>("cowMorph"), new ListValue<>("cow"))),
	RACE_HORSE_MORPH(BaseColour.ORANGE, "orange", Util.newArrayListOfValues(new ListValue<>("horseMorph"), new ListValue<>("horse"))),
	RACE_REINDEER_MORPH(BaseColour.BROWN_DARK, "dark brown", Util.newArrayListOfValues(new ListValue<>("reindeerMorph"), new ListValue<>("reindeer"))),
	RACE_WOLF_MORPH(BaseColour.BLACK, "black", Util.newArrayListOfValues(new ListValue<>("wolfMorph"), new ListValue<>("wolf"))),
	RACE_HARPY(BaseColour.PINK_LIGHT, "light pink", Util.newArrayListOfValues(new ListValue<>("harpy"))),
	RACE_SLIME(BaseColour.PINK, "pink", Util.newArrayListOfValues(new ListValue<>("slime"))),
	RACE_SQUIRREL_MORPH(BaseColour.GINGER, "ginger", Util.newArrayListOfValues(new ListValue<>("squirrelMorph"), new ListValue<>("squirrel"))),
	RACE_ALLIGATOR_MORPH(BaseColour.GREEN_DARK, "dark green", Util.newArrayListOfValues(new ListValue<>("alligatorMorph"), new ListValue<>("alligator"), new ListValue<>("gatorMorph"), new ListValue<>("gator"))),
	
	QUEST_MAIN(BaseColour.PINK, "pink"),
	QUEST_SIDE(BaseColour.BLUE, "blue"),
	QUEST_ROMANCE(BaseColour.PINK_LIGHT, "pink"),

	MAP_MARKER(Util.newColour(0x6163DB), Util.newColour(0x6163DB), "blue"),

	ATTRIBUTE_HEALTH(BaseColour.MAGENTA, "red", Util.newArrayListOfValues(new ListValue<>("health"), new ListValue<>("hp"))),
	ATTRIBUTE_MANA(BaseColour.BLUE, "blue", Util.newArrayListOfValues(new ListValue<>("willpower"), new ListValue<>("wp"), new ListValue<>("mana"))),
	ATTRIBUTE_STAMINA(BaseColour.LILAC, "lilac", Util.newArrayListOfValues(new ListValue<>("stamina"), new ListValue<>("sp"), new ListValue<>("energy"))),

	ATTRIBUTE_STRENGTH(BaseColour.MAGENTA, "magenta", Util.newArrayListOfValues(new ListValue<>("strength"), new ListValue<>("str"))),
	ATTRIBUTE_INTELLIGENCE(BaseColour.BLUE, "blue", Util.newArrayListOfValues(new ListValue<>("intelligence"), new ListValue<>("int"))),
	ATTRIBUTE_FITNESS(BaseColour.LILAC, "light purple", Util.newArrayListOfValues(new ListValue<>("fitness"), new ListValue<>("fit"))),
	ATTRIBUTE_CORRUPTION(BaseColour.PINK_DEEP, "pink", Util.newArrayListOfValues(new ListValue<>("corruption"), new ListValue<>("cor"), new ListValue<>("corr"))),

	ATTRIBUTE_AROUSAL(BaseColour.PINK_DEEP, "pink", Util.newArrayListOfValues(new ListValue<>("arousal"), new ListValue<>("ars"))),
	ATTRIBUTE_LUST(BaseColour.MAGENTA, "magenta", Util.newArrayListOfValues(new ListValue<>("lust"), new ListValue<>("lst"))),

	//TODO
	STRENGTH_STAGE_ZERO(BaseColour.MAGENTA, "magenta"),
	STRENGTH_STAGE_ONE(BaseColour.MAGENTA, "magenta"),
	STRENGTH_STAGE_TWO(BaseColour.MAGENTA, "magenta"),
	STRENGTH_STAGE_THREE(BaseColour.MAGENTA, "magenta"),
	STRENGTH_STAGE_FOUR(BaseColour.MAGENTA, "magenta"),
	STRENGTH_STAGE_FIVE(BaseColour.GOLD, "gold"),
	
	//TODO
	INTELLIGENCE_STAGE_ZERO(BaseColour.BLUE, "blue"),
	INTELLIGENCE_STAGE_ONE(BaseColour.BLUE, "blue"),
	INTELLIGENCE_STAGE_TWO(BaseColour.BLUE, "blue"),
	INTELLIGENCE_STAGE_THREE(BaseColour.BLUE, "blue"),
	INTELLIGENCE_STAGE_FOUR(BaseColour.BLUE, "blue"),
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

	AFFECTION(BaseColour.PINK_LIGHT, "light pink",  Util.newArrayListOfValues(new ListValue<>("affection"))),
	OBEDIENCE(BaseColour.PURPLE_LIGHT, "light purple",  Util.newArrayListOfValues(new ListValue<>("obedience"))),
	
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

	MASCULINE(Util.newColour(0x8ABEFF), Util.newColour(0x8ABEFF), "blue", Util.newArrayListOfValues(new ListValue<>("masculine"), new ListValue<>("mas"), new ListValue<>("masculinePlus"))),
	MASCULINE_PLUS(Util.newColour(0x4D9DFF), Util.newColour(0x4D9DFF), "dark blue", Util.newArrayListOfValues(new ListValue<>("masculineStrong"), new ListValue<>("masStr"))),
	ANDROGYNOUS(Util.newColour(0xB39EFF), Util.newColour(0xB39EFF), "purple", Util.newArrayListOfValues(new ListValue<>("androgynous"), new ListValue<>("andro"))),
	FEMININE(Util.newColour(0xFFBDFF), Util.newColour(0xFFFBDFF), "pink", Util.newArrayListOfValues(new ListValue<>("feminine"), new ListValue<>("fem"))),
	FEMININE_PLUS(Util.newColour(0xFF85FF), Util.newColour(0xFF85FF), "pink", Util.newArrayListOfValues(new ListValue<>("feminineStrong"), new ListValue<>("femStr"), new ListValue<>("femininePlus"))),
	
	BODY_SIZE_ZERO(Util.newColour(0xFFEBD6), Util.newColour(0x241D00), "tan", Util.newArrayListOfValues(new ListValue<>("bodySizeZero"))),
	BODY_SIZE_ONE(Util.newColour(0xFFE0BD), Util.newColour(0x3D3100), "tan", Util.newArrayListOfValues(new ListValue<>("bodySizeOne"))),
	BODY_SIZE_TWO(Util.newColour(0xFFC88A), Util.newColour(0x5C4900), "tan", Util.newArrayListOfValues(new ListValue<>("bodySizeTwo"))),
	BODY_SIZE_THREE(Util.newColour(0xFFAB57), Util.newColour(0x806600), "tan", Util.newArrayListOfValues(new ListValue<>("bodySizeThree"))),
	BODY_SIZE_FOUR(Util.newColour(0xFF9124), Util.newColour(0x9E7E00), "tan", Util.newArrayListOfValues(new ListValue<>("bodySizeFour"))),

	MUSCLE_ZERO(Util.newColour(0xDBFFF6), Util.newColour(0x001F17), "teal", Util.newArrayListOfValues(new ListValue<>("muscleZero"))),
	MUSCLE_ONE(Util.newColour(0xBDFFED), Util.newColour(0x00382B), "teal", Util.newArrayListOfValues(new ListValue<>("muscleOne"))),
	MUSCLE_TWO(Util.newColour(0x8AFFE0), Util.newColour(0x00523F), "teal", Util.newArrayListOfValues(new ListValue<>("muscleTwo"))),
	MUSCLE_THREE(Util.newColour(0x57FFD2), Util.newColour(0x006B52), "teal", Util.newArrayListOfValues(new ListValue<>("muscleThree"))),
	MUSCLE_FOUR(Util.newColour(0x24FFC5), Util.newColour(0x008566), "teal", Util.newArrayListOfValues(new ListValue<>("muscleFour"))),


	TRANSFORMATION_SHRINK(BaseColour.RED, "red", Util.newArrayListOfValues(new ListValue<>("tfShrink"), new ListValue<>("shrink"), new ListValue<>("tfShrunk"), new ListValue<>("shrunk"), new ListValue<>("tfShrinking"), new ListValue<>("shrinking"))),
	TRANSFORMATION_GROW(BaseColour.GREEN, "green", Util.newArrayListOfValues(new ListValue<>("tfGrow"), new ListValue<>("grow"), new ListValue<>("tfGrown"), new ListValue<>("grown"), new ListValue<>("tfGrowth"), new ListValue<>("growth"))),

	GENERIC_SIZE_ONE(Util.newColour(0xAFE9B3), Util.newColour(0xc44670), "green"),
	GENERIC_SIZE_TWO(Util.newColour(0xA0E4A3), Util.newColour(0xbc325a), "green"),
	GENERIC_SIZE_THREE(Util.newColour(0x8FE096), Util.newColour(0xb21e44), "green"),
	GENERIC_SIZE_FOUR(Util.newColour(0x77DA7F), Util.newColour(0xa40123), "green"),
	GENERIC_SIZE_FIVE(Util.newColour(0x67D570), Util.newColour(0x8e011e), "green"),
	GENERIC_SIZE_SIX(Util.newColour(0x57D161), Util.newColour(0x8e011e), "green"),
	GENERIC_SIZE_SEVEN(Util.newColour(0x47CD52), Util.newColour(0x8e011e), "green"),
	GENERIC_SIZE_EIGHT(Util.newColour(0x37C843), Util.newColour(0x8e011e), "green"),
	
	TRANSFORMATION_GENERIC(BaseColour.GREEN_LIME, "lime", Util.newArrayListOfValues(new ListValue<>("tfGeneric"), new ListValue<>("tfBase"))),
	TRANSFORMATION_SEXUAL(BaseColour.PINK_LIGHT, "pink", Util.newArrayListOfValues(new ListValue<>("tfSex"), new ListValue<>("tfSexual"))),
	TRANSFORMATION_HUMAN(BaseColour.BLUE_STEEL, "pale blue", Util.newArrayListOfValues(new ListValue<>("tfHuman"))),
	TRANSFORMATION_PARTIAL(Util.newColour(0xff80bf), Util.newColour(0xff80bf), "purple", Util.newArrayListOfValues(new ListValue<>("tfPartial"))),
	TRANSFORMATION_PARTIAL_FULL(Util.newColour(0xff1a8c), Util.newColour(0xff1a8c), "purple", Util.newArrayListOfValues(new ListValue<>("tfMinor"))),
	TRANSFORMATION_LESSER(Util.newColour(0xe600ac), Util.newColour(0xe600ac), "purple", Util.newArrayListOfValues(new ListValue<>("tfLesser"))),
	TRANSFORMATION_GREATER(Util.newColour(0xd411d4), Util.newColour(0xd411d4), "purple-pink", Util.newArrayListOfValues(new ListValue<>("tfGreater"))),

	// Speech colours:
	MASCULINE_NPC(BaseColour.BLUE_LIGHT, "blue"),
	ANDROGYNOUS_NPC(BaseColour.LILAC_LIGHT, "purple"),
	FEMININE_NPC(BaseColour.ROSE, "pink"),

	// Combat colours:
	DAMAGE_TYPE_PHYSICAL(Util.newColour(0xFF428E), Util.newColour(0xFF428E), "red", Util.newArrayListOfValues(new ListValue<>("dmgPhysical"), new ListValue<>("resPhysical"))),
	DAMAGE_TYPE_MANA(Util.newColour(0x1DBAE2), Util.newColour(0x1DBAE2), "blue", Util.newArrayListOfValues(new ListValue<>("dmgMana"), new ListValue<>("resMana"))),
	DAMAGE_TYPE_STAMINA(Util.newColour(0x9999ff), Util.newColour(0x9999ff), "light purple", Util.newArrayListOfValues(new ListValue<>("dmgStamina"), new ListValue<>("resStamina"))),
	DAMAGE_TYPE_SPELL(Util.newColour(0xFF6BDA), Util.newColour(0xFF6BDA), "pink", Util.newArrayListOfValues(new ListValue<>("dmgSpell"), new ListValue<>("resSpell"))),
	DAMAGE_TYPE_FIRE(Util.newColour(0xff9955), Util.newColour(0xff9955), "orange", Util.newArrayListOfValues(new ListValue<>("dmgFire"), new ListValue<>("resFire"))),
	DAMAGE_TYPE_COLD(Util.newColour(0x85C6FF), Util.newColour(0x85C6FF), "blue", Util.newArrayListOfValues(new ListValue<>("dmgCold"), new ListValue<>("resCold"))),
	DAMAGE_TYPE_POISON(Util.newColour(0x85FF8B), Util.newColour(0x85FF8B), "green", Util.newArrayListOfValues(new ListValue<>("dmgPoison"), new ListValue<>("resPoison"))),
	DAMAGE_TYPE_PURE(Util.newColour(0xFFCC00), Util.newColour(0xFFCC00), "gold", Util.newArrayListOfValues(new ListValue<>("dmgPure"), new ListValue<>("resPure"))),

	// Rarity colours:
	RARITY_UNKNOWN(BaseColour.BLACK, "grey"),
	RARITY_JINXED(BaseColour.RED, "red", Util.newArrayListOfValues(new ListValue<>("jinxed"))),
	RARITY_COMMON(Util.newColour(0xf2f2f2), Util.newColour(0xf2f2f2), "white", Util.newArrayListOfValues(new ListValue<>("common"))),
	RARITY_UNCOMMON(Util.newColour(0x1de547), Util.newColour(0x108228), "green", Util.newArrayListOfValues(new ListValue<>("uncommon"))),
	RARITY_RARE(Util.newColour(0x47C2FF), Util.newColour(0x47C2FF), "blue", Util.newArrayListOfValues(new ListValue<>("rare"))),
	RARITY_EPIC(Util.newColour(0xFF4DFC), Util.newColour(0xFF4DFC), "purple", Util.newArrayListOfValues(new ListValue<>("epic"))),
	RARITY_LEGENDARY(Util.newColour(0xffcc00), Util.newColour(0xffcc00), "gold", Util.newArrayListOfValues(new ListValue<>("legendary"))),

	// Inventory colours:
	CURRENCY_GOLD(BaseColour.GOLD, "gold"),
	CURRENCY_SILVER(BaseColour.SILVER, "gold"),
	CURRENCY_COPPER(BaseColour.COPPER, "gold"),

	SEALED(BaseColour.PINK_DEEP, "red"),
	CUMMED(Util.newColour(0xE1E49B), Util.newColour(0xE1E49B), "light yellow",  Util.newArrayListOfValues(new ListValue<>("cummed"), new ListValue<>("dirty"))),
	DISPLACED(BaseColour.CRIMSON, "crimson"),

	// Text colours:
	TEXT(Util.newColour(0xDDDDDD), Util.newColour(0x262626), "grey", Util.newArrayListOfValues(new ListValue<>("text"))),
	TEXT_HALF_GREY(Util.newColour(0xBBBBBB), Util.newColour(0x444444), "grey", Util.newArrayListOfValues(new ListValue<>("halfDisabled"))),
	TEXT_GREY(Util.newColour(0x777777), Util.newColour(0x777777), "grey", Util.newArrayListOfValues(new ListValue<>("disabled"))),
	TEXT_GREY_DARK(Util.newColour(0x444444), Util.newColour(0xcccccc), "grey", Util.newArrayListOfValues(new ListValue<>("disabledDark"))),

	// Standard colours used for clothing:
	CLOTHING_RED(Util.newColour(0xD84646), Util.newColour(0xD84646), "red"),
	CLOTHING_ORANGE(Util.newColour(0xE79F6F), Util.newColour(0xE79F6F), "orange"),
	CLOTHING_TAN(BaseColour.TAN, "tan"),
	CLOTHING_BROWN(Util.newColour(0xC87137), Util.newColour(0xC87137), "brown"),
	CLOTHING_YELLOW(Util.newColour(0xE2C360), Util.newColour(0xE2C360), "yellow"),
	CLOTHING_GREEN_LIME(Util.newColour(0xD0E37D), Util.newColour(0xD0E37D), "lime green"),
	CLOTHING_GREEN(Util.newColour(0x74AA74), Util.newColour(0x74AA74), "green"),
	CLOTHING_TURQUOISE(Util.newColour(0x6EC4B3), Util.newColour(0x6EC4B3), "turquoise"),
	CLOTHING_BLUE_LIGHT(Util.newColour(0x72CFE3), Util.newColour(0x72CFE3), "light blue"),
	CLOTHING_BLUE(Util.newColour(0x3971C6), Util.newColour(0x3971C6), "blue"),
	CLOTHING_PURPLE(Util.newColour(0xA382D3), Util.newColour(0xA382D3), "purple"),
	CLOTHING_PURPLE_LIGHT(Util.newColour(0xC58ED7), Util.newColour(0xC58ED7), "violet"),
	CLOTHING_PINK(Util.newColour(0xD75086), Util.newColour(0xD75086), "pink"),
	CLOTHING_PINK_LIGHT(Util.newColour(0xF4B3F4), Util.newColour(0xF4B3F4), "light pink"),
	CLOTHING_BLACK(Util.newColour(0x333333), Util.newColour(0x333333), "black"),
	CLOTHING_GREY(Util.newColour(0x777777), Util.newColour(0x777777), "grey"),
	CLOTHING_WHITE(Util.newColour(0xdddddd), Util.newColour(0xdddddd), "white"),
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
	SKIN_PALE(Util.newColour(0xFBF4E9), Util.newColour(0x534946), "pale"),
	SKIN_LIGHT(BaseColour.YELLOW_LIGHT, "light"),
	SKIN_OLIVE(BaseColour.TAN, "olive"),
	SKIN_DARK(BaseColour.BROWN_DARK, "dark"),
	SKIN_EBONY(BaseColour.BLACK, "ebony"),
	
	SKIN_RED(BaseColour.CRIMSON, "scarlet"),
	SKIN_BROWN(BaseColour.BROWN, "brown"),
	SKIN_AMBER(BaseColour.AMBER, "amber"),
	SKIN_PINK(BaseColour.PINK_LIGHT, "light pink"),
	SKIN_GREEN(BaseColour.GREEN, "green"),
	SKIN_BLUE(BaseColour.BLUE_LIGHT, "light blue"),
	SKIN_LILAC(BaseColour.LILAC, "lilac"),
	SKIN_PURPLE(BaseColour.PURPLE, "purple"),
	SKIN_IVORY(BaseColour.WHITE, "ivory"),
	SKIN_GREY(BaseColour.GREY, "grey"),

	// Slime types:
	SLIME_BLUE(BaseColour.BLUE, "translucent blue"),
	SLIME_RED(BaseColour.RED, "translucent red"),
	SLIME_GREEN(BaseColour.GREEN, "translucent green"),
	SLIME_PINK(BaseColour.PINK, "translucent pink"),
	SLIME_BLACK(BaseColour.BLACK, "translucent black"),

	// Feathers:
	FEATHERS_BLACK(BaseColour.BLACK, "black"),
	FEATHERS_WHITE(BaseColour.WHITE, "white"),
	FEATHERS_BLUE(BaseColour.BLUE_LIGHT, "blue"),
	FEATHERS_LILAC(BaseColour.LILAC, "lilac"),
	FEATHERS_PINK(BaseColour.PINK_LIGHT, "pink"),
	FEATHERS_RED(BaseColour.RED, "red"),
	FEATHERS_GREEN(BaseColour.GREEN, "green"),
	FEATHERS_YELLOW(BaseColour.YELLOW, "yellow"),
	FEATHERS_ORANGE(BaseColour.ORANGE, "orange"),
	FEATHERS_GINGER(BaseColour.GINGER, "ginger"),
	FEATHERS_BLEACH_BLONDE(BaseColour.YELLOW_LIGHT, "bleach-blonde"),

	// Horns:
	HORN_WHITE(BaseColour.WHITE, "ivory"),
	HORN_RED(BaseColour.RED, "red"),
	HORN_GREY(BaseColour.RED, "grey"),
	HORN_DARK_GREY(BaseColour.RED, "dark-grey"),
	HORN_BLACK(BaseColour.BLACK, "black"),

	// Antlers:
	ANTLER_WHITE(BaseColour.WHITE, "ivory"),
	ANTLER_TAN(BaseColour.TAN, "tan"),
	ANTLER_GREY(BaseColour.RED, "grey"),
	ANTLER_DARK_GREY(BaseColour.RED, "dark-grey"),
	ANTLER_BLACK(BaseColour.BLACK, "black"),

	// Orifices:
	ORIFICE_INTERIOR(BaseColour.ROSE, "fleshy-pink"),

	// Misc:
	TONGUE(BaseColour.ROSE, "pink"),

	// Generic colours:
	COVERING_BROWN(BaseColour.BROWN, "brown"),
	COVERING_BROWN_DARK(BaseColour.BROWN_DARK, "dark brown"),
	COVERING_BLACK(BaseColour.BLACK, "black"),
	COVERING_GREY(BaseColour.GREY, "grey"),
	COVERING_BLONDE(BaseColour.YELLOW, "blonde"),
	COVERING_BLEACH_BLONDE(BaseColour.YELLOW_LIGHT, "bleach-blonde"),
	COVERING_GINGER(BaseColour.GINGER, "ginger"),
	COVERING_ORANGE(BaseColour.ORANGE, "orange"),
	COVERING_AMBER(BaseColour.AMBER, "amber"),
	COVERING_RED(BaseColour.RED, "red"),
	COVERING_WHITE(BaseColour.WHITE, "white"),
	COVERING_BLUE(BaseColour.BLUE, "blue"),
	COVERING_PURPLE(BaseColour.PURPLE, "purple"),
	COVERING_PINK(BaseColour.PINK_LIGHT, "pink"),
	COVERING_GREEN(BaseColour.GREEN, "green"),
	
	// Special nail polish:
	COVERING_CLEAR(BaseColour.WHITE, "clear"),
	COVERING_NONE(BaseColour.TAN, "none"),

	// Eye colours:
	EYE_BROWN(BaseColour.BROWN, "brown"),
	EYE_BLUE(BaseColour.BLUE_LIGHT, "blue"),
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
	
	
	// Clothing groups:
	
	public static List<Colour> allClothingColours = Util.newArrayListOfValues(
			new ListValue<Colour>(CLOTHING_WHITE),
			new ListValue<Colour>(CLOTHING_BLACK),
			new ListValue<Colour>(CLOTHING_GREY),
			new ListValue<Colour>(CLOTHING_RED),
			new ListValue<Colour>(CLOTHING_ORANGE),
			new ListValue<Colour>(CLOTHING_BROWN),
			new ListValue<Colour>(CLOTHING_TAN),
			new ListValue<Colour>(CLOTHING_YELLOW),
			new ListValue<Colour>(CLOTHING_GREEN_LIME),
			new ListValue<Colour>(CLOTHING_GREEN),
			new ListValue<Colour>(CLOTHING_TURQUOISE),
			new ListValue<Colour>(CLOTHING_BLUE_LIGHT),
			new ListValue<Colour>(CLOTHING_BLUE),
			new ListValue<Colour>(CLOTHING_PURPLE),
			new ListValue<Colour>(CLOTHING_PURPLE_LIGHT),
			new ListValue<Colour>(CLOTHING_PINK),
			new ListValue<Colour>(CLOTHING_PINK_LIGHT));

	public static List<Colour> masculineColours = Util.newArrayListOfValues(
			new ListValue<Colour>(CLOTHING_WHITE),
			new ListValue<Colour>(CLOTHING_BLACK),
			new ListValue<Colour>(CLOTHING_GREY),
			new ListValue<Colour>(CLOTHING_TURQUOISE),
			new ListValue<Colour>(CLOTHING_BLUE_LIGHT),
			new ListValue<Colour>(CLOTHING_BLUE));

	public static List<Colour> lingerieColours = Util.newArrayListOfValues(
			new ListValue<Colour>(CLOTHING_WHITE),
			new ListValue<Colour>(CLOTHING_BLACK),
			new ListValue<Colour>(CLOTHING_GREY),
			new ListValue<Colour>(CLOTHING_RED),
			new ListValue<Colour>(CLOTHING_ORANGE),
			new ListValue<Colour>(CLOTHING_YELLOW),
			new ListValue<Colour>(CLOTHING_GREEN_LIME),
			new ListValue<Colour>(CLOTHING_GREEN),
			new ListValue<Colour>(CLOTHING_TURQUOISE),
			new ListValue<Colour>(CLOTHING_BLUE_LIGHT),
			new ListValue<Colour>(CLOTHING_BLUE),
			new ListValue<Colour>(CLOTHING_PURPLE),
			new ListValue<Colour>(CLOTHING_PURPLE_LIGHT),
			new ListValue<Colour>(CLOTHING_PINK),
			new ListValue<Colour>(CLOTHING_PINK_LIGHT));

	public static List<Colour> allMetalColours = Util.newArrayListOfValues(
			new ListValue<Colour>(CLOTHING_BLACK_STEEL),
			new ListValue<Colour>(CLOTHING_STEEL),
			new ListValue<Colour>(CLOTHING_COPPER),
			new ListValue<Colour>(CLOTHING_SILVER),
			new ListValue<Colour>(CLOTHING_ROSE_GOLD),
			new ListValue<Colour>(CLOTHING_GOLD),
			new ListValue<Colour>(CLOTHING_PLATINUM));
	
	
	
	// Skin/fur/body part groups:
	
	public static List<Colour> humanSkinColours = Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.SKIN_PALE),
			new ListValue<Colour>(Colour.SKIN_LIGHT),
			new ListValue<Colour>(Colour.SKIN_OLIVE),
			new ListValue<Colour>(Colour.SKIN_DARK),
			new ListValue<Colour>(Colour.SKIN_EBONY));
	
	public static List<Colour> demonSkinColours = Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.SKIN_RED),
			new ListValue<Colour>(Colour.SKIN_BROWN),
			new ListValue<Colour>(Colour.SKIN_AMBER),
			new ListValue<Colour>(Colour.SKIN_PINK),
			new ListValue<Colour>(Colour.SKIN_GREEN),
			new ListValue<Colour>(Colour.SKIN_BLUE),
			new ListValue<Colour>(Colour.SKIN_LILAC),
			new ListValue<Colour>(Colour.SKIN_PURPLE),
			new ListValue<Colour>(Colour.SKIN_IVORY),
			new ListValue<Colour>(Colour.SKIN_GREY),
			new ListValue<Colour>(Colour.SKIN_EBONY));

	public static List<Colour> allSkinColours = Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.SKIN_PALE),
			new ListValue<Colour>(Colour.SKIN_LIGHT),
			new ListValue<Colour>(Colour.SKIN_OLIVE),
			new ListValue<Colour>(Colour.SKIN_DARK),
			new ListValue<Colour>(Colour.SKIN_EBONY),
			new ListValue<Colour>(Colour.SKIN_GREY),
			new ListValue<Colour>(Colour.SKIN_RED),
			new ListValue<Colour>(Colour.SKIN_BROWN),
			new ListValue<Colour>(Colour.SKIN_AMBER),
			new ListValue<Colour>(Colour.SKIN_PINK),
			new ListValue<Colour>(Colour.SKIN_GREEN),
			new ListValue<Colour>(Colour.SKIN_BLUE),
			new ListValue<Colour>(Colour.SKIN_LILAC),
			new ListValue<Colour>(Colour.SKIN_PURPLE),
			new ListValue<Colour>(Colour.SKIN_IVORY));
	
	
	public static List<Colour> naturalFurColours = Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.COVERING_WHITE),
			new ListValue<Colour>(Colour.COVERING_BLONDE),
			new ListValue<Colour>(Colour.COVERING_GINGER),
			new ListValue<Colour>(Colour.COVERING_BROWN),
			new ListValue<Colour>(Colour.COVERING_BROWN_DARK),
			new ListValue<Colour>(Colour.COVERING_GREY),
			new ListValue<Colour>(Colour.COVERING_BLACK));

	public static List<Colour> dyeFurColours = Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.COVERING_BLEACH_BLONDE),
			new ListValue<Colour>(Colour.COVERING_BLUE),
			new ListValue<Colour>(Colour.COVERING_GREEN),
			new ListValue<Colour>(Colour.COVERING_PINK),
			new ListValue<Colour>(Colour.COVERING_PURPLE),
			new ListValue<Colour>(Colour.COVERING_RED));

	public static List<Colour> naturalScaleColours = Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.COVERING_WHITE),
			new ListValue<Colour>(Colour.COVERING_BROWN),
			new ListValue<Colour>(Colour.COVERING_BROWN_DARK),
			new ListValue<Colour>(Colour.COVERING_BLACK));

	public static List<Colour> dyeScaleColours = Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.COVERING_BLEACH_BLONDE),
			new ListValue<Colour>(Colour.COVERING_BLONDE),
			new ListValue<Colour>(Colour.COVERING_BLUE),
			new ListValue<Colour>(Colour.COVERING_GINGER),
			new ListValue<Colour>(Colour.COVERING_GREEN),
			new ListValue<Colour>(Colour.COVERING_PINK),
			new ListValue<Colour>(Colour.COVERING_PURPLE),
			new ListValue<Colour>(Colour.COVERING_RED));

	public static List<Colour> hornColours = Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.HORN_WHITE),
			new ListValue<Colour>(Colour.HORN_RED),
			new ListValue<Colour>(Colour.HORN_GREY),
			new ListValue<Colour>(Colour.HORN_DARK_GREY),
			new ListValue<Colour>(Colour.HORN_BLACK));
	
	public static List<Colour> antlerColours = Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.ANTLER_WHITE),
			new ListValue<Colour>(Colour.ANTLER_TAN),
			new ListValue<Colour>(Colour.ANTLER_GREY),
			new ListValue<Colour>(Colour.ANTLER_DARK_GREY),
			new ListValue<Colour>(Colour.ANTLER_BLACK));
	

	// Orifices:
	
	public static List<Colour> orificeInteriors = Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.ORIFICE_INTERIOR),
			new ListValue<Colour>(Colour.SKIN_PALE),
			new ListValue<Colour>(Colour.SKIN_LIGHT),
			new ListValue<Colour>(Colour.SKIN_OLIVE),
			new ListValue<Colour>(Colour.SKIN_DARK),
			new ListValue<Colour>(Colour.SKIN_EBONY),
			new ListValue<Colour>(Colour.SKIN_RED),
			new ListValue<Colour>(Colour.SKIN_BROWN),
			new ListValue<Colour>(Colour.SKIN_GREEN),
			new ListValue<Colour>(Colour.SKIN_PINK),
			new ListValue<Colour>(Colour.SKIN_BLUE),
			new ListValue<Colour>(Colour.SKIN_LILAC),
			new ListValue<Colour>(Colour.SKIN_PURPLE),
			new ListValue<Colour>(Colour.SKIN_IVORY));
	
	// Hair:
	
	public static List<Colour> naturalHairColours = Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.COVERING_WHITE),
			new ListValue<Colour>(Colour.COVERING_BLONDE),
			new ListValue<Colour>(Colour.COVERING_GINGER),
			new ListValue<Colour>(Colour.COVERING_BROWN),
			new ListValue<Colour>(Colour.COVERING_BROWN_DARK),
			new ListValue<Colour>(Colour.COVERING_GREY),
			new ListValue<Colour>(Colour.COVERING_BLACK));
	
	public static List<Colour> dyeHairColours = Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.COVERING_BLEACH_BLONDE),
			new ListValue<Colour>(Colour.COVERING_BLUE),
			new ListValue<Colour>(Colour.COVERING_GREEN),
			new ListValue<Colour>(Colour.COVERING_PINK),
			new ListValue<Colour>(Colour.COVERING_PURPLE),
			new ListValue<Colour>(Colour.COVERING_ORANGE),
			new ListValue<Colour>(Colour.COVERING_AMBER),
			new ListValue<Colour>(Colour.COVERING_RED));
	
	// Eyes:
	
	public static List<Colour> naturalIrisColours = Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.EYE_BROWN),
			new ListValue<Colour>(Colour.EYE_AMBER),
			new ListValue<Colour>(Colour.EYE_BLUE),
			new ListValue<Colour>(Colour.EYE_AQUA),
			new ListValue<Colour>(Colour.EYE_GREEN),
			new ListValue<Colour>(Colour.EYE_GREY));
	
	public static List<Colour> dyeIrisColours = Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.EYE_SILVER),
			new ListValue<Colour>(Colour.EYE_YELLOW),
			new ListValue<Colour>(Colour.EYE_GOLD),
			new ListValue<Colour>(Colour.EYE_RED),
			new ListValue<Colour>(Colour.EYE_CRIMSON),
			new ListValue<Colour>(Colour.EYE_ORANGE),
			new ListValue<Colour>(Colour.EYE_PINK),
			new ListValue<Colour>(Colour.EYE_VIOLET),
			new ListValue<Colour>(Colour.EYE_LILAC),
			new ListValue<Colour>(Colour.EYE_PURPLE),
			new ListValue<Colour>(Colour.EYE_BLACK));
	
	public static List<Colour> naturalPupilColours = Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.EYE_BLACK));
	
	public static List<Colour> dyePupilColours = Util.newArrayListOfValues(
			new ListValue<Colour>(Colour.EYE_SILVER),
			new ListValue<Colour>(Colour.EYE_BROWN),
			new ListValue<Colour>(Colour.EYE_BLUE),
			new ListValue<Colour>(Colour.EYE_AQUA),
			new ListValue<Colour>(Colour.EYE_GREEN),
			new ListValue<Colour>(Colour.EYE_GREY),
			new ListValue<Colour>(Colour.EYE_YELLOW),
			new ListValue<Colour>(Colour.EYE_GOLD),
			new ListValue<Colour>(Colour.EYE_RED),
			new ListValue<Colour>(Colour.EYE_CRIMSON),
			new ListValue<Colour>(Colour.EYE_ORANGE),
			new ListValue<Colour>(Colour.EYE_AMBER),
			new ListValue<Colour>(Colour.EYE_PINK),
			new ListValue<Colour>(Colour.EYE_VIOLET),
			new ListValue<Colour>(Colour.EYE_LILAC),
			new ListValue<Colour>(Colour.EYE_PURPLE));
	
	
	
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
			if(Main.getProperties().lightTheme)
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
