package com.base.utils;

import java.util.List;

import com.base.main.Main;
import com.base.utils.Util.ListValue;

import javafx.scene.paint.Color;

/**
 * @since 0.1.0
 * @version 0.1.78
 * @author Innoxia
 */
public enum Colour {

	// Game colours:
	GENERIC_SEX(BaseColour.PINK_LIGHT, "pink", Util.newArrayListOfValues(new ListValue<>("sex"))),
	GENERIC_COMBAT(BaseColour.CRIMSON, "crimson"),
	GENERIC_ARCANE(BaseColour.PINK, "pink", Util.newArrayListOfValues(new ListValue<>("arcane"))),
	GENERIC_TERRIBLE(BaseColour.PURPLE, "purple", Util.newArrayListOfValues(new ListValue<>("terrible"))),
	GENERIC_BAD(BaseColour.RED, "red", Util.newArrayListOfValues(new ListValue<>("bad"))),
	GENERIC_GOOD(BaseColour.GREEN_LIGHT, "light green", Util.newArrayListOfValues(new ListValue<>("good"))),
	GENERIC_EXCELLENT(BaseColour.GOLD, "gold", Util.newArrayListOfValues(new ListValue<>("excellent"))),
	GENERIC_ATTRIBUTE(BaseColour.MAGENTA, "magenta"),
	GENERIC_EXPERIENCE(BaseColour.BLUE_LIGHT, "light blue"),

	PERK(BaseColour.AQUA, "aqua"),
	FETISH(BaseColour.ROSE, "rose"),
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
	RACE_HORSE_MORPH(BaseColour.ORANGE, "orange", Util.newArrayListOfValues(new ListValue<>("horseMorph"), new ListValue<>("horse"))),
	RACE_WOLF_MORPH(BaseColour.BLACK, "black", Util.newArrayListOfValues(new ListValue<>("wolfMorph"), new ListValue<>("wolf"))),
	RACE_HARPY(BaseColour.PINK_LIGHT, "light pink", Util.newArrayListOfValues(new ListValue<>("harpy"))),
	RACE_SLIME(BaseColour.PINK, "pink", Util.newArrayListOfValues(new ListValue<>("slime"))),

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

	MASCULINE(Util.newColour(0x8ABEFF), Util.newColour(0x8ABEFF), "blue", Util.newArrayListOfValues(new ListValue<>("masculine"), new ListValue<>("mas"))),
	MASCULINE_PLUS(Util.newColour(0x4D9DFF), Util.newColour(0x4D9DFF), "dark blue"),
	ANDROGYNOUS(Util.newColour(0xB39EFF), Util.newColour(0xB39EFF), "purple"),
	FEMININE(Util.newColour(0xFFBDFF), Util.newColour(0xFFFBDFF), "pink", Util.newArrayListOfValues(new ListValue<>("feminine"), new ListValue<>("fem"))),
	FEMININE_PLUS(Util.newColour(0xFF85FF), Util.newColour(0xFF85FF), "pink"),

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
	RARITY_JINXED(BaseColour.RED, "red"),
	RARITY_COMMON(Util.newColour(0xf2f2f2), Util.newColour(0xf2f2f2), "white"),
	RARITY_UNCOMMON(Util.newColour(0x1de547), Util.newColour(0x108228), "green"),
	RARITY_RARE(Util.newColour(0x47C2FF), Util.newColour(0x47C2FF), "blue"),
	RARITY_EPIC(Util.newColour(0xFF4DFC), Util.newColour(0xFF4DFC), "purple"),
	RARITY_LEGENDARY(Util.newColour(0xffcc00), Util.newColour(0xffcc00), "gold"),

	// Inventory colours:
	CURRENCY(BaseColour.GOLD, "gold"),

	SEALED(BaseColour.PINK_DEEP, "red"),
	CUMMED(Util.newColour(0xE1E49B), Util.newColour(0xE1E49B), "light yellow"),
	DISPLACED(BaseColour.CRIMSON, "crimson"),

	// Text colours:
	TEXT_GREY(Util.newColour(0x777777), Util.newColour(0x777777), "grey",  Util.newArrayListOfValues(new ListValue<>("disabled"))),

	// Standard colours used for clothing:
	CLOTHING_RED(Util.newColour(0xD84646), Util.newColour(0xD84646), "red"),
	CLOTHING_ORANGE(Util.newColour(0xE79F6F), Util.newColour(0xE79F6F), "orange"),
	CLOTHING_TAN(BaseColour.TAN, "tan"),//TODO Not used yet
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
	CLOTHING_PLATINUM(Util.newColour(0xE4E5E2), Util.newColour(0xE4E5E2), "platinum"),
	
	// For special use with rainbow clothing:
	CLOTHING_MULTICOLOURED(Util.newColour(0xff3030), Util.newColour(0xccffff), "multicoloured"),

	// Body parts:

	// Human skin:
	HUMAN_SKIN_LIGHT(BaseColour.YELLOW_LIGHT, "white"),
	HUMAN_SKIN_OLIVE(BaseColour.TAN, "olive"),
	HUMAN_SKIN_DARK(BaseColour.BROWN_DARK, "brown"),
	HUMAN_SKIN_EBONY(BaseColour.BLACK, "ebony"),

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

	// Demon skin:
	DEMON_SKIN_RED(BaseColour.CRIMSON, "scarlet"),
	DEMON_SKIN_BROWN(BaseColour.BROWN, "brown"),
	DEMON_SKIN_PINK(BaseColour.PINK_LIGHT, "light pink"),
	DEMON_SKIN_BLUE(BaseColour.BLUE_LIGHT, "light blue"),
	DEMON_SKIN_LILAC(BaseColour.LILAC, "lilac"),
	DEMON_SKIN_PURPLE(BaseColour.PURPLE, "purple"),
	DEMON_SKIN_IVORY(BaseColour.WHITE, "ivory"),
	DEMON_SKIN_EBONY(BaseColour.BLACK, "ebony"),

	// Horns:
	HORN_WHITE(BaseColour.WHITE, "white"),

	// Cocks:
	CANINE_COCK(BaseColour.RED, "red"),
	FELINE_COCK(BaseColour.RED, "red"),
	EQUINE_COCK(BaseColour.BLACK, "black"),

	// Vaginas:
	EQUINE_VAGINA(BaseColour.BLACK, "black"),

	// Assholes:
	EQUINE_ASSHOLE(BaseColour.BLACK, "black"),

	// Misc:
	TONGUE(BaseColour.PINK_LIGHT, "pink"),

	// Generic colours:
	COVERING_BROWN(BaseColour.BROWN, "brown"),
	COVERING_BROWN_DARK(BaseColour.BROWN_DARK, "dark brown"),
	COVERING_BLACK(BaseColour.BLACK, "black"),
	COVERING_BLONDE(BaseColour.YELLOW, "blonde"),
	COVERING_BLEACH_BLONDE(BaseColour.YELLOW_LIGHT, "bleach-blonde"),
	COVERING_GINGER(BaseColour.GINGER, "ginger"),
	COVERING_RED(BaseColour.RED, "red"),
	COVERING_WHITE(BaseColour.WHITE, "white"),
	COVERING_BLUE(BaseColour.BLUE, "blue"),
	COVERING_PURPLE(BaseColour.PURPLE, "purple"),
	COVERING_PINK(BaseColour.PINK_LIGHT, "pink"),
	COVERING_GREEN(BaseColour.GREEN, "green"),

	// Eye colours:
	EYE_BROWN(BaseColour.BROWN, "brown"),
	EYE_BLUE(BaseColour.BLUE_LIGHT, "blue"),
	EYE_GREEN(BaseColour.GREEN, "green"),
	EYE_YELLOW(BaseColour.YELLOW, "yellow"),
	EYE_RED(BaseColour.RED, "red"),
	EYE_PINK(BaseColour.PINK, "pink"),
	EYE_ORANGE(BaseColour.ORANGE, "orange"),
	EYE_BLACK(BaseColour.BLACK, "black");

	
	public static List<Colour> allClothingColours = Util.newArrayListOfValues(
			new ListValue<Colour>(CLOTHING_WHITE),
			new ListValue<Colour>(CLOTHING_BLACK),
			new ListValue<Colour>(CLOTHING_GREY),
			new ListValue<Colour>(CLOTHING_RED),
			new ListValue<Colour>(CLOTHING_ORANGE),
			new ListValue<Colour>(CLOTHING_BROWN),
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
			new ListValue<Colour>(CLOTHING_RED),
			new ListValue<Colour>(CLOTHING_BLUE_LIGHT),
			new ListValue<Colour>(CLOTHING_PURPLE),
			new ListValue<Colour>(CLOTHING_PURPLE_LIGHT),
			new ListValue<Colour>(CLOTHING_PINK),
			new ListValue<Colour>(CLOTHING_PINK_LIGHT));

	public static List<Colour> allMetalColours = Util.newArrayListOfValues(
			new ListValue<Colour>(CLOTHING_BLACK_STEEL),
			new ListValue<Colour>(CLOTHING_STEEL),
			new ListValue<Colour>(CLOTHING_COPPER),
			new ListValue<Colour>(CLOTHING_SILVER),
			new ListValue<Colour>(CLOTHING_GOLD)),
			new ListValue<Colour>(CLOTHING_PLATINUM));
	
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
		return getColor().toString().substring(2, 8);
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
	 * Shades goes from dark to light. So shades[0] is the darkest.
	 * length 5
	 */
	public String[] getShades() {
		String[] shadesString = new String[5];
		float luminosity = -0.3f;
		String r = colour.toString().substring(2, 4), g = colour.toString().substring(4, 6), b = colour.toString().substring(6, 8), rgb = "#";
		int colourValue = Integer.parseInt(r, 16);

		for (int i = 0; i < 5; i++) {
			colourValue = Integer.parseInt(r, 16);
			colourValue += (colourValue * (i * 0.15f + luminosity));
			rgb += String.format("%02X", Math.max(Math.min(colourValue, 255), 0), 16);

			colourValue = Integer.parseInt(g, 16);
			colourValue += (colourValue * (i * 0.15f + luminosity));
			rgb += String.format("%02X", Math.max(Math.min(colourValue, 255), 0), 16);

			colourValue = Integer.parseInt(b, 16);
			colourValue += (colourValue * (i * 0.15f + luminosity));
			rgb += String.format("%02X", Math.max(Math.min(colourValue, 255), 0), 16);

			shadesString[i] = rgb;
			rgb = "#";
		}

		return shadesString;
	}

	public List<String> getFormattingNames() {
		return formattingNames;
	}

}
