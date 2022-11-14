package com.lilithsthrone.utils.colours;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.0
 * @version 0.3.7
 * @author Innoxia
 */
public class PresetColour {
	
	// This class and BaseColour are beyond saving x_x
	public static Colour BASE_FREEDOM = new Colour(false, BaseColour.RED,
			"<span style='color:#EA5D76;'>f</span>"
			+ "<span style='color:#FFFFFF;'>r</span>"
			+ "<span style='color:#0090BF;'>e</span>"
			+ "<span style='color:#EA5D76;'>e</span>"
			+ "<span style='color:#FFFFFF;'>d</span>"
			+ "<span style='color:#0090BF;'>o</span>"
			+ "<span style='color:#EA5D76;'>m</span>", 
			Util.newArrayListOfValues("freedom", "america", "usa")) {
		@Override
		public List<String> getRainbowColours() {
			return Util.newArrayListOfValues(
					"#EA5D76",
					"#FFFFFF",
					"#0090BF");
		}
	};
	
	public static Colour BASE_WHITE = new Colour(false, BaseColour.WHITE, "white", Util.newArrayListOfValues("white")) {};

	public static Colour BASE_GREY_LIGHT = new Colour(false, BaseColour.GREY_LIGHT, "light grey", Util.newArrayListOfValues("lightGrey")) {};
	public static Colour BASE_GREY = new Colour(false, BaseColour.GREY, "grey", Util.newArrayListOfValues("grey")) {};
	public static Colour BASE_GREY_DARK = new Colour(false, BaseColour.GREY_DARK, "dark grey", Util.newArrayListOfValues("darkGrey", "greyDark")) {};
	
	public static Colour BASE_ROSE = new Colour(false, BaseColour.ROSE, "rose", Util.newArrayListOfValues("rose")) {};
	public static Colour BASE_LILAC = new Colour(false, BaseColour.LILAC, "lilac", Util.newArrayListOfValues("lilac")) {};
	public static Colour BASE_LILAC_LIGHT = new Colour(false, BaseColour.LILAC_LIGHT, "light lilac", Util.newArrayListOfValues("lightLilac", "lilacLight")) {};
	public static Colour BASE_INDIGO = new Colour(false, BaseColour.INDIGO, "indigo", Util.newArrayListOfValues("indigo")) {};
	public static Colour BASE_PURPLE_DARK = new Colour(false, BaseColour.PURPLE_DARK, "violet", Util.newArrayListOfValues("darkPurple", "purpleDark", "violet")) {};
	public static Colour BASE_PURPLE = new Colour(false, BaseColour.PURPLE, "purple", Util.newArrayListOfValues("purple")) {};
	public static Colour BASE_PURPLE_LIGHT = new Colour(false, BaseColour.PURPLE_LIGHT, "light purple", Util.newArrayListOfValues("lightPurple", "purpleLight")) {};
	
	public static Colour BASE_PINK_DEEP = new Colour(false, BaseColour.PINK_DEEP, "deep pink", Util.newArrayListOfValues("deepPink", "darkPink", "pinkDeep", "pinkDark")) {};
	public static Colour BASE_PINK_SALMON = new Colour(false, BaseColour.PINK_SALMON, "salmon-pink", Util.newArrayListOfValues("pinkSalmon", "salmonPink")) {};
	public static Colour BASE_PINK = new Colour(false, BaseColour.PINK, "pink", Util.newArrayListOfValues("pink")) {};
	public static Colour BASE_PINK_LIGHT = new Colour(false, BaseColour.PINK_LIGHT, "light pink", Util.newArrayListOfValues("lightPink", "pinkLight")) {};
		
	public static Colour BASE_MAGENTA = new Colour(false, BaseColour.MAGENTA, "magenta", Util.newArrayListOfValues("magenta")) {};
	public static Colour BASE_CRIMSON = new Colour(false, BaseColour.CRIMSON, "crimson", Util.newArrayListOfValues("crimson")) {};
	public static Colour BASE_RED_DARK = new Colour(false, BaseColour.RED_DARK, "dark red", Util.newArrayListOfValues("darkRed", "redDark")) {};
	public static Colour BASE_RED = new Colour(false, BaseColour.RED, "red", Util.newArrayListOfValues("red")) {};
	public static Colour BASE_RED_LIGHT = new Colour(false, BaseColour.RED_LIGHT, "light red", Util.newArrayListOfValues("lightRed", "redLight")) {};
	
	public static Colour BASE_TAN = new Colour(false, BaseColour.TAN, "tan", Util.newArrayListOfValues("tan")) {};
	public static Colour BASE_BROWN = new Colour(false, BaseColour.BROWN, "brown", Util.newArrayListOfValues("brown")) {};
	public static Colour BASE_BROWN_DARK = new Colour(false, BaseColour.BROWN_DARK, "dark brown", Util.newArrayListOfValues("darkBrown", "brownDark")) {};
	public static Colour BASE_BROWN_LIGHT= new Colour(false, BaseColour.BROWN_LIGHT, "light brown", Util.newArrayListOfValues("lightBrown", "brownLight")) {};

	public static Colour BASE_COPPER = new Colour(false, BaseColour.COPPER, "copper", Util.newArrayListOfValues("baseCopper")) {};
	public static Colour BASE_ORANGE = new Colour(false, BaseColour.ORANGE, "orange", Util.newArrayListOfValues("orange")) {};
	public static Colour BASE_ORANGE_LIGHT = new Colour(false, BaseColour.ORANGE_LIGHT, "light orange", Util.newArrayListOfValues("orangeLight", "lightOrange")) {};
	public static Colour BASE_GINGER = new Colour(false, BaseColour.GINGER, "ginger", Util.newArrayListOfValues("ginger")) {};
	
	public static Colour BASE_GOLD = new Colour(false, BaseColour.GOLD, "gold", Util.newArrayListOfValues("gold")) {};
	public static Colour BASE_YELLOW_PALE = new Colour(false, BaseColour.YELLOW_PALE, "pale yellow", Util.newArrayListOfValues("paleYellow", "yellowPale")) {};
	public static Colour BASE_YELLOW = new Colour(false, BaseColour.YELLOW, "yellow", Util.newArrayListOfValues("yellow")) {};
	public static Colour BASE_YELLOW_LIGHT = new Colour(false, BaseColour.YELLOW_LIGHT, "light yellow", Util.newArrayListOfValues("lightYellow", "yellowLight")) {};
	
	public static Colour BASE_GREEN_LIME = new Colour(false, BaseColour.GREEN_LIME, "lime green", Util.newArrayListOfValues("limeGreen", "greenLime")) {};
	public static Colour BASE_GREEN_LIGHT = new Colour(false, BaseColour.GREEN_LIGHT, "light green", Util.newArrayListOfValues("lightGreen", "greenLight")) {};
	public static Colour BASE_GREEN = new Colour(false, BaseColour.GREEN, "green", Util.newArrayListOfValues("green")) {};
	public static Colour BASE_GREEN_DARK = new Colour(false, BaseColour.GREEN_DARK, "dark green", Util.newArrayListOfValues("darkGreen", "greenDark")) {};
	
	public static Colour BASE_AQUA = new Colour(false, BaseColour.AQUA, "aqua", Util.newArrayListOfValues("aqua")) {};
	public static Colour BASE_TEAL = new Colour(false, BaseColour.TEAL, "teal", Util.newArrayListOfValues("teal")) {};
	public static Colour BASE_PERIWINKLE = new Colour(false, BaseColour.PERIWINKLE, "periwinkle", Util.newArrayListOfValues("periwinkle")) {};
	public static Colour BASE_BLUE_DARK = new Colour(false, BaseColour.BLUE_DARK, "dark blue", Util.newArrayListOfValues("darkBlue", "blueDark")) {};
	public static Colour BASE_BLUE_LIGHT = new Colour(false, BaseColour.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("lightBlue", "blueLight")) {};
	public static Colour BASE_BLUE = new Colour(false, BaseColour.BLUE, "blue", Util.newArrayListOfValues("blue")) {};
	public static Colour BASE_BLUE_STEEL = new Colour(false, BaseColour.BLUE_STEEL, "steely blue", Util.newArrayListOfValues("steelyBlue", "steelBlue", "blueSteel")) {};
	
	public static Colour BASE_BLACK = new Colour(false, BaseColour.BLACK, "black", Util.newArrayListOfValues("black")) {};
	public static Colour BASE_PITCH_BLACK = new Colour(false, BaseColour.PITCH_BLACK, "black", Util.newArrayListOfValues("black")) {};
	
	
	
	// Game colours:
	public static Colour BACKGROUND_DARK = new Colour(false, Util.newColour(0x19191a), Util.newColour(0xf0f0f0), "grey") {};
	public static Colour BACKGROUND = new Colour(false, Util.newColour(0x222222), Util.newColour(0xcccccc), "grey") {};
	public static Colour BACKGROUND_ALT = new Colour(false, Util.newColour(0x292929), Util.newColour(0xbbbbbb), "grey") {};
	
	public static Colour BACKGROUND_DAY = new Colour(false, Util.newColour(0x222222), Util.newColour(0xcccccc), "grey") {};
	public static Colour BACKGROUND_TWILIGHT = new Colour(false, Util.newColour(0x191919), Util.newColour(0xbbbbbb), "black") {};
	public static Colour BACKGROUND_NIGHT = new Colour(false, Util.newColour(0x121212), Util.newColour(0xaaaaaa), "pitch black") {};
	
	public static Colour MAP_BACKGROUND_UNEXPLORED = new Colour(false, Util.newColour(0x121212), Util.newColour(0x121212), "black") {};
	public static Colour MAP_BACKGROUND_PINK = new Colour(false, Util.newColour(0xb2a4bb), Util.newColour(0xb2a4bb), "pink") {};
	public static Colour MAP_BACKGROUND = new Colour(false, Util.newColour(0xbbbbbb), Util.newColour(0xbbbbbb), "grey") {};
	public static Colour MAP_BACKGROUND_DARK = new Colour(false, Util.newColour(0x888888), Util.newColour(0x8f8f8f), "dark grey") {};
	public static Colour MAP_BACKGROUND_DANGEROUS = new Colour(false, Util.newColour(0x303030), Util.newColour(0xaaaaaa), "dark grey") {};
	public static Colour MAP_BACKGROUND_BLUE = new Colour(false,  Util.newColour(0xbbbbdd), Util.newColour(0xbbbbdd), "light blue") {};
	public static Colour MAP_BACKGROUND_GREEN = new Colour(false,  Util.newColour(0xbbddbb), Util.newColour(0xbbddbb), "light green") {};
	public static Colour MAP_BACKGROUND_GREEN_DARK = new Colour(false,  Util.newColour(0x5E685E), Util.newColour(0x5E685E), "dark green") {};

	public static Colour GENERIC_NPC_REMOVAL = new Colour(false, BaseColour.RED_LIGHT, "red") {};
	public static Colour GENERIC_SEX = new Colour(false, BaseColour.PINK_LIGHT, "light pink", Util.newArrayListOfValues("sex", "sexSub", "subSex", "sub", "submissive")) {};
	public static Colour GENERIC_SEX_AS_DOM = new Colour(false, Util.newColour(0xFF75BA), Util.newColour(0xD82C8B), "pink", Util.newArrayListOfValues("sexDom", "domSex", "dom", "dominant")) {};
//	public static AbstractColour DOMINANT = new AbstractColour(false, BaseColour.PINK_DEEP, "deep pink", Util.newArrayListOfValues("dom", "dominant")) {};
//	public static AbstractColour SUBMISSIVE = new AbstractColour(false, BaseColour.PINK_LIGHT, "light pink", Util.newArrayListOfValues("sub", "submissive")) {};
	public static Colour GENERIC_COMBAT = new Colour(false, BaseColour.CRIMSON, "crimson", Util.newArrayListOfValues("combat")) {};
	public static Colour GENERIC_ARCANE = new Colour(false, BaseColour.PINK, "pink", Util.newArrayListOfValues("arcane")) {};
	public static Colour GENERIC_TERRIBLE = new Colour(false, BaseColour.CRIMSON, "crimson", Util.newArrayListOfValues("terrible")) {};
	public static Colour GENERIC_MINOR_BAD = new Colour(false, BaseColour.RED_LIGHT, "red", Util.newArrayListOfValues("minorBad", "badMinor")) {};
	public static Colour GENERIC_MINOR_GOOD = new Colour(false, BaseColour.GREEN_LIGHT, "light green", Util.newArrayListOfValues("minorGood", "goodMinor")) {};
	public static Colour GENERIC_BAD = new Colour(false, BaseColour.RED, "red", Util.newArrayListOfValues("bad")) {};
	public static Colour GENERIC_NEUTRAL = new Colour(false, BaseColour.BLUE, "blue", Util.newArrayListOfValues("neutral")) {};
	public static Colour GENERIC_GOOD = new Colour(false, BaseColour.GREEN, "green", Util.newArrayListOfValues("good")) {};
	public static Colour GENERIC_EXCELLENT = new Colour(false, BaseColour.GOLD, "gold", Util.newArrayListOfValues("excellent")) {};
	public static Colour GENERIC_ATTRIBUTE = new Colour(false, BaseColour.MAGENTA, "magenta") {};
	public static Colour GENERIC_EXPERIENCE = new Colour(false, BaseColour.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("experience", "xp")) {};
	public static Colour GENERIC_ACTION_POINTS = new Colour(false, BaseColour.AQUA, "aqua", Util.newArrayListOfValues("actionPoint", "actionPoints", "ap")) {};
	public static Colour GENERIC_ENCHANTMENT = new Colour(false, BaseColour.PINK, "pink", Util.newArrayListOfValues("enchantment", "enchanting")) {};
	public static Colour COOLDOWN = new Colour(false, BaseColour.CRIMSON, "crimson", Util.newArrayListOfValues("cooldown")) {};
	public static Colour CRIT = new Colour(false, BaseColour.GOLD, "gold", Util.newArrayListOfValues("crit")) {};

	public static Colour SCAR = new Colour(false, BaseColour.TAN, "tan", Util.newArrayListOfValues("scar")) {};
	public static Colour TATTOO = new Colour(false, BaseColour.GREY, "grey", Util.newArrayListOfValues("tattoo")) {};
	
	public static Colour PERK = new Colour(false, BaseColour.AQUA, "aqua", Util.newArrayListOfValues("perk")) {};
	public static Colour TRAIT = new Colour(false, BaseColour.GREEN_LIGHT, "green", Util.newArrayListOfValues("trait")) {};
	public static Colour FETISH = new Colour(false, BaseColour.PINK_LIGHT, "light pink", Util.newArrayListOfValues("fetish")) {};
	public static Colour STATUS_EFFECT = new Colour(false, BaseColour.YELLOW, "yellow", Util.newArrayListOfValues("statusEffect", "statusEffects")) {};
	public static Colour SPECIAL_ATTACK = new Colour(false, BaseColour.CRIMSON, "crimson") {};
	public static Colour STATUS_EFFECT_TIME_OVERFLOW = new Colour(false, BaseColour.BLUE, "aqua") {};
	public static Colour STATUS_EFFECT_TIME_HIGH = new Colour(false, BaseColour.GREEN_LIGHT, "green") {};
	public static Colour STATUS_EFFECT_TIME_MEDIUM = new Colour(false, BaseColour.ORANGE, "orange") {};
	public static Colour STATUS_EFFECT_TIME_LOW = new Colour(false, BaseColour.RED, "red") {};

	public static Colour RACE_BESTIAL = new Colour(false, BaseColour.TAN, "tan", Util.newArrayListOfValues("bestial", "animal", "feral")) {};
	public static Colour RACE_UNKNOWN = new Colour(false, BaseColour.GREY, "grey", Util.newArrayListOfValues("unknown")) {};
	public static Colour RACE_HUMAN = new Colour(false, BaseColour.BLUE_STEEL, "pale blue", Util.newArrayListOfValues("human")) {};
	public static Colour RACE_HALF_DEMON = new Colour(false, BaseColour.INDIGO, "indigo", Util.newArrayListOfValues("halfDemon")) {};
	public static Colour RACE_DEMON = new Colour(false, BaseColour.PURPLE_LIGHT, "light purple", Util.newArrayListOfValues("demon")) {};
	public static Colour RACE_LILIN = new Colour(false, BaseColour.PURPLE, "purple", Util.newArrayListOfValues("lilin")) {};
	public static Colour RACE_IMP = new Colour(false, BaseColour.PURPLE, "purple", Util.newArrayListOfValues("imp")) {};
	public static Colour RACE_ANGEL = new Colour(false, BaseColour.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("angel")) {};
	public static Colour RACE_DOG_MORPH = new Colour(false, BaseColour.BROWN, "brown", Util.newArrayListOfValues("dogMorph", "dog")) {};
	public static Colour RACE_CAT_MORPH = new Colour(false, BaseColour.PINK_SALMON, "salmon-pink", Util.newArrayListOfValues("catMorph", "cat")) {};
	public static Colour RACE_CAT_MORPH_CARACAL = new Colour(false, BaseColour.PINK_SALMON, "salmon-pink", Util.newArrayListOfValues("ocelotMorph", "ocelot")) {};
	public static Colour RACE_CAT_MORPH_LION = new Colour(false, BaseColour.YELLOW, "yellow", Util.newArrayListOfValues("lionMorph", "lion")) {};
	public static Colour RACE_CAT_MORPH_LEOPARD = new Colour(false, BaseColour.GINGER, "ginger", Util.newArrayListOfValues("leopardMorph", "leopard")) {};
	public static Colour RACE_CAT_MORPH_TIGER = new Colour(false, BaseColour.GINGER, "ginger", Util.newArrayListOfValues("tigerMorph", "tiger")) {};
	public static Colour RACE_CAT_MORPH_CHEETAH = new Colour(false, BaseColour.YELLOW, "yellow", Util.newArrayListOfValues("cheetahMorph", "cheetah")) {};
	public static Colour RACE_CAT_MORPH_LYNX = new Colour(false, BaseColour.SILVER, "silver", Util.newArrayListOfValues("lynxMorph", "lynx")) {};
	public static Colour RACE_CAT_MORPH_LEOPARD_SNOW = new Colour(false, BaseColour.SILVER, "silver", Util.newArrayListOfValues("leopardSnowMorph", "snowLeopard", "snep", "leopardSnow", "snowLeopardMorph")) {};
	public static Colour RACE_COW_MORPH = new Colour(false, BaseColour.TAN, "tan", Util.newArrayListOfValues("cowMorph", "cow")) {};
	public static Colour RACE_HORSE_MORPH = new Colour(false, BaseColour.ORANGE, "orange", Util.newArrayListOfValues("horseMorph", "horse")) {};
	public static Colour RACE_PEGASUS = new Colour(false, BaseColour.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("pegasusMorph", "pegasus")) {};
	public static Colour RACE_UNICORN = new Colour(false, BaseColour.WHITE, "white", Util.newArrayListOfValues("unicornMorph", "unicorn")) {};
	public static Colour RACE_ALICORN = new Colour(false, BaseColour.YELLOW_LIGHT, "light yellow", Util.newArrayListOfValues("alicornMorph", "alicorn")) {};
	public static Colour RACE_CENTAUR = new Colour(false, BaseColour.BROWN_DARK, "dark brown", Util.newArrayListOfValues("centaur")) {};
	public static Colour RACE_PEGATAUR = new Colour(false, BaseColour.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("pegataur")) {};
	public static Colour RACE_REINDEER_MORPH = new Colour(false, BaseColour.BROWN_DARK, "dark brown", Util.newArrayListOfValues("reindeerMorph", "reindeer")) {};
	public static Colour RACE_WOLF_MORPH = new Colour(false, BaseColour.BLACK, "black", Util.newArrayListOfValues("wolfMorph", "wolf")) {};
	public static Colour RACE_FOX_MORPH = new Colour(false, BaseColour.GINGER, "ginger", Util.newArrayListOfValues("foxMorph", "fox")) {};
	public static Colour RACE_FOX_MORPH_FENNEC = new Colour(false, Util.newColour(0xddc48b), Util.newColour(0xddc48b), "sandy", Util.newArrayListOfValues("fennecFoxMorph", "fennecFox")) {};
	public static Colour RACE_FOX_MORPH_ARCTIC = new Colour(false, BaseColour.WHITE, "white", Util.newArrayListOfValues("arcticFoxMorph", "arcticFox")) {};
	public static Colour RACE_HARPY = new Colour(false, BaseColour.PINK_LIGHT, "light pink", Util.newArrayListOfValues("harpy")) {};
	public static Colour RACE_SLIME = new Colour(false, BaseColour.GREEN_LIGHT, "light green", Util.newArrayListOfValues("slime")) {};
	public static Colour RACE_SQUIRREL_MORPH = new Colour(false, BaseColour.GINGER, "ginger", Util.newArrayListOfValues("squirrelMorph", "squirrel")) {};
	public static Colour RACE_RAT_MORPH = new Colour(false, BaseColour.BROWN_DARK, "dark brown", Util.newArrayListOfValues("ratMorph", "rat")) {};
	public static Colour RACE_RABBIT_MORPH = new Colour(false, BaseColour.BROWN_DARK, "dark brown", Util.newArrayListOfValues("rabbitMorph", "rabbit")) {};
	public static Colour RACE_BAT_MORPH = new Colour(false, BaseColour.BLACK, "black", Util.newArrayListOfValues("batMorph", "bat")) {};
	public static Colour RACE_ALLIGATOR_MORPH = new Colour(false, BaseColour.GREEN_DARK, "dark green", Util.newArrayListOfValues("alligatorMorph", "alligator", "gatorMorph", "gator")) {};

	public static Colour GENERIC_BAD_END = new Colour(false, BaseColour.CRIMSON, "crimson", Util.newArrayListOfValues("badEnd")) {};
	
	public static Colour QUEST_MAIN = new Colour(false, BaseColour.PINK, "pink", Util.newArrayListOfValues("questMain", "mainQuest")) {};
	public static Colour QUEST_SIDE = new Colour(false, BaseColour.BLUE, "blue", Util.newArrayListOfValues("questSide", "sideQuest")) {};
	public static Colour QUEST_RELATIONSHIP = new Colour(false, BaseColour.PINK_PALE, "pink", Util.newArrayListOfValues("questRelationship", "relationshipQuest", "questRomance", "romanceQuest")) {};

	public static Colour MAP_MARKER = new Colour(false, Util.newColour(0x6163DB), Util.newColour(0x6163DB), "blue") {};

	public static Colour ATTRIBUTE_HEALTH = new Colour(false, BaseColour.CRIMSON, "crimson", Util.newArrayListOfValues("health", "hp", "energy")) {};
	public static Colour ATTRIBUTE_MANA = new Colour(false, BaseColour.PURPLE_LIGHT, "light purple", Util.newArrayListOfValues("willpower", "wp", "mana", "aura")) {};

	public static Colour ATTRIBUTE_PHYSIQUE = new Colour(false, BaseColour.MAGENTA, "magenta", Util.newArrayListOfValues("physique", "phys", "strength", "str")) {};
	public static Colour ATTRIBUTE_ARCANE = new Colour(false, BaseColour.PURPLE, "purple", Util.newArrayListOfValues("intelligence", "int")) {};
	public static Colour ATTRIBUTE_CORRUPTION = new Colour(false, BaseColour.PINK, "pink", Util.newArrayListOfValues("corruption", "cor", "corr")) {};

	public static Colour ATTRIBUTE_AROUSAL = new Colour(false, BaseColour.PINK_DEEP, "pink", Util.newArrayListOfValues("arousal", "ars")) {};
	public static Colour ATTRIBUTE_LUST = new Colour(false, BaseColour.MAGENTA, "magenta", Util.newArrayListOfValues("lust", "lst", "seduction")) {};

	public static Colour PHYSIQUE_STAGE_ZERO = new Colour(false, BaseColour.MAGENTA, "magenta") {};
	public static Colour PHYSIQUE_STAGE_ONE = new Colour(false, BaseColour.MAGENTA, "magenta") {};
	public static Colour PHYSIQUE_STAGE_TWO = new Colour(false, BaseColour.MAGENTA, "magenta") {};
	public static Colour PHYSIQUE_STAGE_THREE = new Colour(false, BaseColour.MAGENTA, "magenta") {};
	public static Colour PHYSIQUE_STAGE_FOUR = new Colour(false, BaseColour.MAGENTA, "magenta") {};
	public static Colour PHYSIQUE_STAGE_FIVE = new Colour(false, BaseColour.GOLD, "gold") {};
	
	public static Colour INTELLIGENCE_STAGE_ZERO = new Colour(false, BaseColour.PURPLE, "purple") {};
	public static Colour INTELLIGENCE_STAGE_ONE = new Colour(false, BaseColour.PURPLE, "purple") {};
	public static Colour INTELLIGENCE_STAGE_TWO = new Colour(false, BaseColour.PURPLE, "purple") {};
	public static Colour INTELLIGENCE_STAGE_THREE = new Colour(false, BaseColour.PURPLE, "purple") {};
	public static Colour INTELLIGENCE_STAGE_FOUR = new Colour(false, BaseColour.PURPLE, "purple") {};
	public static Colour INTELLIGENCE_STAGE_FIVE = new Colour(false, BaseColour.GOLD, "gold") {};
	
	public static Colour FITNESS_STAGE_ZERO = new Colour(false, BaseColour.LILAC, "light purple") {};
	public static Colour FITNESS_STAGE_ONE = new Colour(false, BaseColour.LILAC, "light purple") {};
	public static Colour FITNESS_STAGE_TWO = new Colour(false, BaseColour.LILAC, "light purple") {};
	public static Colour FITNESS_STAGE_THREE = new Colour(false, BaseColour.LILAC, "light purple") {};
	public static Colour FITNESS_STAGE_FOUR = new Colour(false, BaseColour.LILAC, "light purple") {};
	public static Colour FITNESS_STAGE_FIVE = new Colour(false, BaseColour.GOLD, "gold") {};
	
	public static Colour CORRUPTION_STAGE_ZERO = new Colour(false, Util.newColour(0xffdf80), Util.newColour(0xffdf80), "gold") {};
	public static Colour CORRUPTION_STAGE_ONE = new Colour(false, Util.newColour(0xff80bf), Util.newColour(0xff80bf), "pink") {};
	public static Colour CORRUPTION_STAGE_TWO = new Colour(false, Util.newColour(0xff1a8c), Util.newColour(0xff1a8c), "pink") {};
	public static Colour CORRUPTION_STAGE_THREE = new Colour(false, Util.newColour(0xe600ac), Util.newColour(0xe600ac), "pink") {};
	public static Colour CORRUPTION_STAGE_FOUR = new Colour(false, Util.newColour(0xd411d4), Util.newColour(0xd411d4), "pink") {};
	public static Colour CORRUPTION_STAGE_FIVE = new Colour(false, Util.newColour(0xbf00ff), Util.newColour(0xbf00ff), "pink") {};
	
	public static Colour AROUSAL_STAGE_ZERO = new Colour(false, Util.newColour(0xfee6ff), Util.newColour(0xfee6ff), "pink") {};
	public static Colour AROUSAL_STAGE_ONE = new Colour(false, Util.newColour(0xfcb3ff), Util.newColour(0xfcb3ff), "pink") {};
	public static Colour AROUSAL_STAGE_TWO = new Colour(false, Util.newColour(0xfb80ff), Util.newColour(0xfb80ff), "pink") {};
	public static Colour AROUSAL_STAGE_THREE = new Colour(false, Util.newColour(0xf94dff), Util.newColour(0xf94dff), "pink") {};
	public static Colour AROUSAL_STAGE_FOUR = new Colour(false, Util.newColour(0xf824ff), Util.newColour(0xf824ff), "pink") {};
	public static Colour AROUSAL_STAGE_FIVE = new Colour(false, Util.newColour(0xf700ff), Util.newColour(0xf700ff), "pink") {};
	
	public static Colour LUST_STAGE_ZERO = new Colour(false, Util.newColour(0x80CAFF), Util.newColour(0xfee6ff), "blue") {};
	public static Colour LUST_STAGE_ONE = new Colour(false, Util.newColour(0xB699FF), Util.newColour(0xfcb3ff), "purple") {};
	public static Colour LUST_STAGE_TWO = new Colour(false, Util.newColour(0xFF99D1), Util.newColour(0xfb80ff), "pink") {};
	public static Colour LUST_STAGE_THREE = new Colour(false, Util.newColour(0xFF61AB), Util.newColour(0xf94dff), "pink") {};
	public static Colour LUST_STAGE_FOUR = new Colour(false, Util.newColour(0xFF3377), Util.newColour(0xf824ff), "dark pink") {};
	public static Colour LUST_STAGE_FIVE = new Colour(false, Util.newColour(0xFF1A66), Util.newColour(0xf824ff), "dark pink") {};

	public static Colour DESIRE_STAGE_ZERO = new Colour(false, Util.newColour(0xB699FF), Util.newColour(0xfcb3ff), "purple") {};
	public static Colour DESIRE_STAGE_ONE = new Colour(false, Util.newColour(0xFF99D1), Util.newColour(0xfb80ff), "pink") {};
	public static Colour DESIRE_STAGE_TWO = new Colour(false, Util.newColour(0xFF61AB), Util.newColour(0xf94dff), "pink") {};
	public static Colour DESIRE_STAGE_THREE = new Colour(false, Util.newColour(0xFF3377), Util.newColour(0xf824ff), "dark pink") {};
	public static Colour DESIRE_STAGE_FOUR = new Colour(false, Util.newColour(0xffdf80), Util.newColour(0xffdf80), "gold") {};


	public static Colour COMPANION = new Colour(false, BaseColour.GREEN_LIGHT, "light green", Util.newArrayListOfValues("companion", "companions")) {};

	public static Colour STAMINA = new Colour(false, BaseColour.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("stamina")) {};
	
	public static Colour AFFECTION = new Colour(false, BaseColour.PINK_LIGHT, "light pink", Util.newArrayListOfValues("affection")) {};
	public static Colour OBEDIENCE = new Colour(false, BaseColour.PURPLE_LIGHT, "light purple", Util.newArrayListOfValues("obedience")) {};
	
	public static Colour AFFECTION_NEGATIVE_FIVE = new Colour(false, Util.newColour(0xff0066), Util.newColour(0x8e011e), "magenta") {};
	public static Colour AFFECTION_NEGATIVE_FOUR = new Colour(false, Util.newColour(0xff2a7f), Util.newColour(0xa40123), "magenta") {};
	public static Colour AFFECTION_NEGATIVE_THREE = new Colour(false, Util.newColour(0xff5599), Util.newColour(0xb21e44), "pink") {};
	public static Colour AFFECTION_NEGATIVE_TWO = new Colour(false, Util.newColour(0xff80b2), Util.newColour(0xbc325a), "pink") {};
	public static Colour AFFECTION_NEGATIVE_ONE = new Colour(false, Util.newColour(0xffaacc), Util.newColour(0xc44670), "pink") {};
	public static Colour AFFECTION_NEUTRAL = new Colour(false, Util.newColour(0xe3dedb), Util.newColour(0xcd5986), "grey") {};
	public static Colour AFFECTION_POSITIVE_ONE = new Colour(false, Util.newColour(0xffeeaa), Util.newColour(0xd66e9d), "yellow") {};
	public static Colour AFFECTION_POSITIVE_TWO = new Colour(false, Util.newColour(0xffe680), Util.newColour(0xe082b3), "yellow") {};
	public static Colour AFFECTION_POSITIVE_THREE = new Colour(false, Util.newColour(0xffdd55), Util.newColour(0xe996c9), "yellow") {};
	public static Colour AFFECTION_POSITIVE_FOUR = new Colour(false, Util.newColour(0xffd42a), Util.newColour(0xf2aadf), "gold") {};
	public static Colour AFFECTION_POSITIVE_FIVE = new Colour(false, Util.newColour(0xffcc00), Util.newColour(0xfbbcf4), "gold") {};

	public static Colour MASCULINE_PLUS = new Colour(false, Util.newColour(0x649fe7), Util.newColour(0x649fe7), "dark blue", Util.newArrayListOfValues("masculineStrong", "masStr", "masculinePlus")) {};
	public static Colour MASCULINE = new Colour(false, Util.newColour(0x8ABEFF), Util.newColour(0x8ABEFF), "blue", Util.newArrayListOfValues("masculine", "mas")) {};
	public static Colour ANDROGYNOUS = new Colour(false, Util.newColour(0xB39EFF), Util.newColour(0xB39EFF), "purple", Util.newArrayListOfValues("androgynous", "andro")) {};
	public static Colour FEMININE = new Colour(false, Util.newColour(0xFFBDFF), Util.newColour(0xFFFBDFF), "pink", Util.newArrayListOfValues("feminine", "fem")) {};
	public static Colour FEMININE_PLUS = new Colour(false, Util.newColour(0xFF85FF), Util.newColour(0xFF85FF), "pink", Util.newArrayListOfValues("feminineStrong", "femStr", "femininePlus")) {};
	
	public static Colour BODY_SIZE_ZERO = new Colour(false, Util.newColour(0xFFEBD6), Util.newColour(0x241D00), "tan", Util.newArrayListOfValues("bodySizeZero")) {};
	public static Colour BODY_SIZE_ONE = new Colour(false, Util.newColour(0xFFE0BD), Util.newColour(0x3D3100), "tan", Util.newArrayListOfValues("bodySizeOne")) {};
	public static Colour BODY_SIZE_TWO = new Colour(false, Util.newColour(0xFFC88A), Util.newColour(0x5C4900), "tan", Util.newArrayListOfValues("bodySizeTwo")) {};
	public static Colour BODY_SIZE_THREE = new Colour(false, Util.newColour(0xFFAB57), Util.newColour(0x806600), "tan", Util.newArrayListOfValues("bodySizeThree")) {};
	public static Colour BODY_SIZE_FOUR = new Colour(false, Util.newColour(0xFF9124), Util.newColour(0x9E7E00), "tan", Util.newArrayListOfValues("bodySizeFour")) {};

	public static Colour MUSCLE_ZERO = new Colour(false, Util.newColour(0xDBFFF6), Util.newColour(0x001F17), "teal", Util.newArrayListOfValues("muscleZero")) {};
	public static Colour MUSCLE_ONE = new Colour(false, Util.newColour(0xBDFFED), Util.newColour(0x00382B), "teal", Util.newArrayListOfValues("muscleOne")) {};
	public static Colour MUSCLE_TWO = new Colour(false, Util.newColour(0x8AFFE0), Util.newColour(0x00523F), "teal", Util.newArrayListOfValues("muscleTwo")) {};
	public static Colour MUSCLE_THREE = new Colour(false, Util.newColour(0x57FFD2), Util.newColour(0x006B52), "teal", Util.newArrayListOfValues("muscleThree")) {};
	public static Colour MUSCLE_FOUR = new Colour(false, Util.newColour(0x24FFC5), Util.newColour(0x008566), "teal", Util.newArrayListOfValues("muscleFour")) {};

	public static Colour AGE_TEENS = new Colour(false, Util.newColour(0xE1F0C1), Util.newColour(0x73A112), "green", Util.newArrayListOfValues("ageTeens")) {};
	public static Colour AGE_TWENTIES = new Colour(false, Util.newColour(0xCCE698), Util.newColour(0x638A0F), "green", Util.newArrayListOfValues("ageTwenties")) {};
	public static Colour AGE_THIRTIES = new Colour(false, Util.newColour(0xB8DC6F), Util.newColour(0x52730D), "green", Util.newArrayListOfValues("age", "ageThirties")) {}; // This is the 'default' age colour
	public static Colour AGE_FORTIES = new Colour(false, Util.newColour(0xA4D246), Util.newColour(0x41590D), "green", Util.newArrayListOfValues("ageForties")) {};
	public static Colour AGE_FIFTIES = new Colour(false, Util.newColour(0x8AB92D), Util.newColour(0x334408), "green", Util.newArrayListOfValues("ageFifties")) {};
	public static Colour AGE_SIXTIES = new Colour(false, Util.newColour(0x6B9023), Util.newColour(0x232E05), "green", Util.newArrayListOfValues("ageSixties")) {};
	
	public static Colour ALCOHOL = new Colour(false, BaseColour.YELLOW_LIGHT, "light yellow", Util.newArrayListOfValues("alcohol")) {};
	public static Colour ALCOHOL_LEVEL_ZERO = new Colour(false, Util.newColour(0xF2E8C0), Util.newColour(0x967F22), "light yellow") {};
	public static Colour ALCOHOL_LEVEL_ONE = new Colour(false, Util.newColour(0xEDDFAB), Util.newColour(0x967F22), "light yellow") {};
	public static Colour ALCOHOL_LEVEL_TWO = new Colour(false, Util.newColour(0xE8D696), Util.newColour(0x967F22), "yellow") {};
	public static Colour ALCOHOL_LEVEL_THREE = new Colour(false, Util.newColour(0xE3CE82), Util.newColour(0x967F22), "yellow") {};
	public static Colour ALCOHOL_LEVEL_FOUR = new Colour(false, Util.newColour(0xDEC66E), Util.newColour(0x967F22), "yellow") {};
	public static Colour ALCOHOL_LEVEL_FIVE = new Colour(false, Util.newColour(0xD9BD59), Util.newColour(0x967F22), "gold") {};
	
	public static Colour PSYCHOACTIVE = new Colour(false, BaseColour.MAGENTA, "magenta", Util.newArrayListOfValues("psychoactive")) {};

	public static Colour TRANSFORMATION_SHRINK = new Colour(false, BaseColour.RED, "red", Util.newArrayListOfValues("tfShrink", "shrink", "tfShrunk", "shrunk", "tfShrinking", "shrinking")) {};
	public static Colour TRANSFORMATION_GROW = new Colour(false, BaseColour.GREEN, "green", Util.newArrayListOfValues("tfGrow", "grow", "tfGrown", "grown", "tfGrowth", "growth")) {};

	public static Colour GENERIC_SIZE_ZERO = new Colour(false, Util.newColour(0xbdf1c1), Util.newColour(0x6da672), "green", Util.newArrayListOfValues("size0")) {};
	public static Colour GENERIC_SIZE_ONE = new Colour(false, Util.newColour(0xAFE9B3), Util.newColour(0x6da672), "green", Util.newArrayListOfValues("size1")) {};
	public static Colour GENERIC_SIZE_TWO = new Colour(false, Util.newColour(0xA0E4A3), Util.newColour(0x6dad72), "green", Util.newArrayListOfValues("size2")) {};
	public static Colour GENERIC_SIZE_THREE = new Colour(false, Util.newColour(0x8FE096), Util.newColour(0x6db572), "green", Util.newArrayListOfValues("size3")) {};
	public static Colour GENERIC_SIZE_FOUR = new Colour(false, Util.newColour(0x77DA7F), Util.newColour(0x6dbd72), "green", Util.newArrayListOfValues("size4")) {};
	public static Colour GENERIC_SIZE_FIVE = new Colour(false, Util.newColour(0x67D570), Util.newColour(0x6dc472), "green", Util.newArrayListOfValues("size5")) {};
	public static Colour GENERIC_SIZE_SIX = new Colour(false, Util.newColour(0x57D161), Util.newColour(0x6dcf72), "green", Util.newArrayListOfValues("size6")) {};
	public static Colour GENERIC_SIZE_SEVEN = new Colour(false, Util.newColour(0x47CD52), Util.newColour(0x6dd672), "green", Util.newArrayListOfValues("size7")) {};
	public static Colour GENERIC_SIZE_EIGHT = new Colour(false, Util.newColour(0x37C843), Util.newColour(0x6dde72), "green", Util.newArrayListOfValues("size8")) {};
	public static Colour GENERIC_SIZE_NINE = new Colour(false, Util.newColour(0x19ba26), Util.newColour(0x6dde72), "green", Util.newArrayListOfValues("size9")) {};
	public static Colour GENERIC_SIZE_TEN = new Colour(false, Util.newColour(0x0ba518), Util.newColour(0x6dde72), "green", Util.newArrayListOfValues("size10")) {};
	
	public static Colour GENERIC_WETNESS_ONE = new Colour(false, Util.newColour(0xddd2bf), Util.newColour(0xddd2bf), "brown", Util.newArrayListOfValues("wetness1")) {};
	public static Colour GENERIC_WETNESS_TWO = new Colour(false, Util.newColour(0xe3f8ff), Util.newColour(0xcbf5ff), "blue", Util.newArrayListOfValues("wetness2")) {};
	public static Colour GENERIC_WETNESS_THREE = new Colour(false, Util.newColour(0xcbf1ff), Util.newColour(0xb3f0ff), "blue", Util.newArrayListOfValues("wetness3")) {};
	public static Colour GENERIC_WETNESS_FOUR = new Colour(false, Util.newColour(0x99ebff), Util.newColour(0x99ebff), "blue", Util.newArrayListOfValues("wetness4")) {};
	public static Colour GENERIC_WETNESS_FIVE = new Colour(false, Util.newColour(0x7cccff), Util.newColour(0x7cccff), "blue", Util.newArrayListOfValues("wetness5")) {};
	public static Colour GENERIC_WETNESS_SIX = new Colour(false, Util.newColour(0x79acff), Util.newColour(0x79acff), "blue", Util.newArrayListOfValues("wetness6")) {};
	public static Colour GENERIC_WETNESS_SEVEN = new Colour(false, Util.newColour(0x649fff), Util.newColour(0x649fff), "blue", Util.newArrayListOfValues("wetness7")) {};
	public static Colour GENERIC_WETNESS_EIGHT = new Colour(false, Util.newColour(0x488eff), Util.newColour(0x488eff), "blue", Util.newArrayListOfValues("wetness8")) {};
	
	public static Colour WETNESS = new Colour(false, BaseColour.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("wetness", "wet", "tfWetness", "tfWet")) {};
	public static Colour PLASTICITY = new Colour(false, BaseColour.INDIGO, "lilac", Util.newArrayListOfValues("plasticity", "tfPlasticity")) {};
	public static Colour ELASTICITY = new Colour(false, BaseColour.PURPLE_LIGHT, "light purple", Util.newArrayListOfValues("elasticity", "tfElasticity")) {};
	public static Colour TRANSFORMATION_GENERIC = new Colour(false, BaseColour.GREEN_LIME, "lime", Util.newArrayListOfValues("tfGeneric", "tfBase", "genericTF")) {};
	public static Colour TRANSFORMATION_SEXUAL = new Colour(false, BaseColour.PINK_LIGHT, "pink", Util.newArrayListOfValues("tfSex", "tfSexual", "sexualTF")) {};
	public static Colour TRANSFORMATION_HUMAN = new Colour(false, BaseColour.BLUE_STEEL, "pale blue", Util.newArrayListOfValues("tfHuman")) {};
	public static Colour TRANSFORMATION_PARTIAL = new Colour(false, Util.newColour(0xff80bf), Util.newColour(0xff80bf), "purple", Util.newArrayListOfValues("tfPartial")) {};
	public static Colour TRANSFORMATION_PARTIAL_FULL = new Colour(false, Util.newColour(0xff1a8c), Util.newColour(0xff1a8c), "purple", Util.newArrayListOfValues("tfMinor")) {};
	public static Colour TRANSFORMATION_LESSER = new Colour(false, Util.newColour(0xe600ac), Util.newColour(0xe600ac), "purple", Util.newArrayListOfValues("tfLesser")) {};
	public static Colour TRANSFORMATION_GREATER = new Colour(false, Util.newColour(0xd411d4), Util.newColour(0xd411d4), "purple-pink", Util.newArrayListOfValues("tfGreater")) {};

	// Speech colours:
//	public static Colour MASCULINE_PLUS_NPC = new Colour(false, BaseColour.BLUE, "blue") {};
//	public static Colour MASCULINE_NPC = new Colour(false, BaseColour.BLUE_LIGHT, "blue") {};
//	public static Colour ANDROGYNOUS_NPC = new Colour(false, BaseColour.LILAC_LIGHT, "purple") {};
//	public static Colour FEMININE_NPC = new Colour(false, BaseColour.ROSE, "pink") {};
//	public static Colour FEMININE_PLUS_NPC = new Colour(false, BaseColour.PINK, "pink") {};
	
	public static Colour MASCULINE_PLUS_NPC = new Colour(false, Util.newColour(0x5c9ff2), Util.newColour(0x649fe7), "dark blue") {};
	public static Colour MASCULINE_NPC = new Colour(false, Util.newColour(0x8ABEFF), Util.newColour(0x8ABEFF), "blue") {};
	public static Colour ANDROGYNOUS_NPC = new Colour(false, Util.newColour(0xB39EFF), Util.newColour(0xB39EFF), "purple") {};
	public static Colour FEMININE_NPC = new Colour(false, Util.newColour(0xFFBDFF), Util.newColour(0xFFFBDFF), "pink") {};
	public static Colour FEMININE_PLUS_NPC = new Colour(false, Util.newColour(0xFF85FF), Util.newColour(0xFF85FF), "pink") {};
	
	// Combat colours:
	public static Colour DAMAGE_TYPE_UNARMED = new Colour(false, Util.newColour(0xedd6ba), Util.newColour(0xDC8D2E), "tan", Util.newArrayListOfValues("unarmed")) {};
	public static Colour DAMAGE_TYPE_MELEE = new Colour(false, Util.newColour(0xea98a1), Util.newColour(0xDD1D40), "light red", Util.newArrayListOfValues("melee")) {};
	public static Colour DAMAGE_TYPE_RANGED = new Colour(false, Util.newColour(0xd2f2ff), Util.newColour(0x00A7D1), "light blue", Util.newArrayListOfValues("ranged")) {};
	
	public static Colour DAMAGE_TYPE_PHYSICAL = new Colour(false, Util.newColour(0xA79E90), Util.newColour(0x5C4D42), "grey-brown", Util.newArrayListOfValues("dmgPhysical", "resPhysical", "physical")) {};
	public static Colour DAMAGE_TYPE_MANA = new Colour(false, BaseColour.PURPLE_LIGHT, "purple", Util.newArrayListOfValues("dmgMana", "resMana")) {};
	public static Colour DAMAGE_TYPE_LUST = new Colour(false, BaseColour.PINK_LIGHT, "light pink", Util.newArrayListOfValues("dmgLust", "resLust")) {};
	public static Colour DAMAGE_TYPE_SPELL = new Colour(false, Util.newColour(0xFF6BDA), Util.newColour(0xFF6BDA), "pink", Util.newArrayListOfValues("dmgSpell", "resSpell", "spell", "spells")) {};
	public static Colour DAMAGE_TYPE_FIRE = new Colour(false, Util.newColour(0xff9955), Util.newColour(0xff9955), "orange", Util.newArrayListOfValues("dmgFire", "resFire", "fire")) {};
	public static Colour DAMAGE_TYPE_COLD = new Colour(false, Util.newColour(0x85C6FF), Util.newColour(0x85C6FF), "blue", Util.newArrayListOfValues("dmgCold", "resCold", "cold", "ice")) {};
	public static Colour DAMAGE_TYPE_POISON = new Colour(false, Util.newColour(0x85FF8B), Util.newColour(0x85FF8B), "green", Util.newArrayListOfValues("dmgPoison", "resPoison", "poison")) {};
	public static Colour DAMAGE_TYPE_PURE = new Colour(false, Util.newColour(0xFFCC00), Util.newColour(0xFFCC00), "gold", Util.newArrayListOfValues("dmgPure", "resPure", "pure")) {};

	public static Colour SPELL_SCHOOL_FIRE = new Colour(false, BaseColour.ORANGE, "orange", Util.newArrayListOfValues("spellFire", "schoolFire")) {};
	public static Colour SPELL_SCHOOL_WATER = new Colour(false, BaseColour.AQUA, "aqua", Util.newArrayListOfValues("water", "spellWater", "schoolWater")) {};
	public static Colour SPELL_SCHOOL_EARTH = new Colour(false, BaseColour.BROWN, "brown", Util.newArrayListOfValues("earth", "spellEarth", "schoolEarth")) {};
	public static Colour SPELL_SCHOOL_AIR = new Colour(false, BaseColour.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("air", "spellAir", "schoolAir")) {};
	public static Colour SPELL_SCHOOL_ARCANE = new Colour(false, BaseColour.PINK, "pink", Util.newArrayListOfValues("spellArcane", "schoolArcane")) {};
	
	// Rarity colours:
	public static Colour RARITY_UNKNOWN = new Colour(false, BaseColour.BLACK, "grey") {};
	public static Colour RARITY_JINXED = new Colour(false, BaseColour.RED, "red", Util.newArrayListOfValues("jinx", "jinxed")) {};
	public static Colour RARITY_COMMON = new Colour(false, Util.newColour(0xf2f2f2), Util.newColour(0xf2f2f2), "white", Util.newArrayListOfValues("common")) {};
	public static Colour RARITY_UNCOMMON = new Colour(false, Util.newColour(0x57f17c), Util.newColour(0x108228), "green", Util.newArrayListOfValues("uncommon")) {};
	public static Colour RARITY_RARE = new Colour(false, Util.newColour(0x47C2FF), Util.newColour(0x47C2FF), "blue", Util.newArrayListOfValues("rare")) {};
	public static Colour RARITY_EPIC = new Colour(false, Util.newColour(0xFF4DFC), Util.newColour(0xFF4DFC), "purple", Util.newArrayListOfValues("epic")) {};
	public static Colour RARITY_LEGENDARY = new Colour(false, Util.newColour(0xffcc00), Util.newColour(0xffcc00), "gold", Util.newArrayListOfValues("legendary")) {};
	public static Colour RARITY_QUEST = new Colour(false, BaseColour.TEAL, "teal", Util.newArrayListOfValues("teal")) {};

	public static Colour RARITY_UNKNOWN_BACKGROUND = new Colour(false, Util.newColour(0x252527), Util.newColour(0xAFAFB6), "grey") {};
	public static Colour RARITY_JINXED_BACKGROUND = new Colour(false, Util.newColour(0x694b4e), Util.newColour(0xE3B5B5), "red") {};
	public static Colour RARITY_COMMON_BACKGROUND = new Colour(false, Util.newColour(0x404040), Util.newColour(0xBDBDC1), "white") {};
	public static Colour RARITY_UNCOMMON_BACKGROUND = new Colour(false, Util.newColour(0x3D5C47), Util.newColour(0x3D5C47), "green") {};
	public static Colour RARITY_RARE_BACKGROUND = new Colour(false, Util.newColour(0x4b4b6c), Util.newColour(0xB4D0EE), "blue") {};
	public static Colour RARITY_EPIC_BACKGROUND = new Colour(false, Util.newColour(0x523455), Util.newColour(0xDDC1E2), "purple") {};
	public static Colour RARITY_LEGENDARY_BACKGROUND = new Colour(false, Util.newColour(0x4b4b33), Util.newColour(0xF1DEA7), "gold") {};
	public static Colour RARITY_QUEST_BACKGROUND = new Colour(false, Util.newColour(0x344C4B), Util.newColour(0xBEE4E4), "teal") {};

	// Inventory colours:
	public static Colour CURRENCY_GOLD = new Colour(true, BaseColour.GOLD, "gold", Util.newArrayListOfValues("money", "currency", "currencyGold")) {};
	public static Colour CURRENCY_SILVER = new Colour(true, BaseColour.SILVER, "silver", Util.newArrayListOfValues("currencySilver")) {};
	public static Colour CURRENCY_COPPER = new Colour(true, BaseColour.COPPER, "copper", Util.newArrayListOfValues("currencyCopper")) {};

	public static Colour MILK = new Colour(false, BaseColour.YELLOW_PALE, "pale yellow", Util.newArrayListOfValues("milk", "lactation")) {};
	public static Colour CUM = new Colour(false, BaseColour.BLUE_PALE, "pale blue", Util.newArrayListOfValues("cum", "cummed")) {};
	public static Colour GIRLCUM = new Colour(false, BaseColour.PINK_PALE, "pale pink", Util.newArrayListOfValues("girlcum", "gcum")) {};

	public static Colour EGG = new Colour(false, BaseColour.YELLOW_PALE, "pale yellow", Util.newArrayListOfValues("egg")) {};
	
	public static Colour VAGINA = new Colour(false, BaseColour.PINK, "pink", Util.newArrayListOfValues("vagina", "pussy", "cunt")) {};
	public static Colour ANUS = new Colour(false, BaseColour.PINK_DEEP, "deep pink", Util.newArrayListOfValues("asshole", "anus", "ass")) {};
	public static Colour PENIS = new Colour(false, BaseColour.PURPLE, "purple", Util.newArrayListOfValues("penis", "cock")) {};
	public static Colour NIPPLES = new Colour(false, BaseColour.PINK_LIGHT, "light pink", Util.newArrayListOfValues("nipples", "nipple")) {};
	public static Colour NIPPLES_CROTCH = new Colour(false, BaseColour.PURPLE_LIGHT, "light purple", Util.newArrayListOfValues("nipplesCrotch", "crotchNipples", "nippleCrotch", "crotchNipple")) {};
	public static Colour URETHRA_PENIS = new Colour(false, BaseColour.PINK_SALMON, "salmon-pink", Util.newArrayListOfValues("penisUrethra", "urethraPenis", "urethra")) {};
	public static Colour UREHTRA_VAGINA = new Colour(false, BaseColour.LILAC, "lilac", Util.newArrayListOfValues("vaginaUrethra", "urethraVagina")) {};
	public static Colour MOUTH = new Colour(false, BaseColour.LILAC_LIGHT, "pale lilac", Util.newArrayListOfValues("mouth", "throat")) {};

	public static Colour DIRTY = new Colour(false, BaseColour.YELLOW_LIGHT, "light yellow", Util.newArrayListOfValues("dirty")) {};
	public static Colour SEALED = new Colour(false, BaseColour.PINK_DEEP, "pink", Util.newArrayListOfValues("seal", "sealed")) {};
	public static Colour DISPLACED = new Colour(false, BaseColour.CRIMSON, "crimson", Util.newArrayListOfValues("displaced")) {};

	// Text colours:
	public static Colour TEXT = new Colour(false, Util.newColour(0xDDDDDD), Util.newColour(0x262626), "grey", Util.newArrayListOfValues("text")) {};
	public static Colour TEXT_HALF_GREY = new Colour(false, Util.newColour(0xBBBBBB), Util.newColour(0x444444), "grey", Util.newArrayListOfValues("halfDisabled")) {};
	public static Colour TEXT_GREY = new Colour(false, Util.newColour(0x777777), Util.newColour(0x777777), "grey", Util.newArrayListOfValues("disabled")) {};
	public static Colour TEXT_GREY_DARK = new Colour(false, Util.newColour(0x444444), Util.newColour(0xcccccc), "grey", Util.newArrayListOfValues("disabledDark")) {};

	
	// Standard colours used for clothing:
	public static Colour CLOTHING_WHITE = new Colour(false, Util.newColour(0xdddddd), Util.newColour(0xdddddd), "white") {};
	public static Colour CLOTHING_GREY_LIGHT = new Colour(false, Util.newColour(0xaaaaaa), Util.newColour(0xaaaaaa), "light grey") {};
	public static Colour CLOTHING_GREY = new Colour(false, Util.newColour(0x777777), Util.newColour(0x777777), "grey") {};
	public static Colour CLOTHING_GREY_DARK = new Colour(false, Util.newColour(0x555555), Util.newColour(0x555555), "dark grey") {};
	public static Colour CLOTHING_BLACK = new Colour(false, Util.newColour(0x333333), Util.newColour(0x333333), "black") {};
	public static Colour CLOTHING_BLACK_JET = new Colour(false, Util.newColour(0x1a1a1a), Util.newColour(0x1a1a1a), "pitch black") {};
	
	public static Colour CLOTHING_RED_VERY_DARK = new Colour(false, Util.newColour(0x4d000c), Util.newColour(0x4d000c), "midnight red") {};
	public static Colour CLOTHING_RED_BURGUNDY = new Colour(false, Util.newColour(0x800020), Util.newColour(0x800020), "burgundy") {};
	public static Colour CLOTHING_RED_DARK = new Colour(false, Util.newColour(0xa41a1a), Util.newColour(0xa41a1a), "dark red") {};
	public static Colour CLOTHING_RED = new Colour(false, Util.newColour(0xd73939), Util.newColour(0xd73939), "red") {};
	public static Colour CLOTHING_RED_BRIGHT = new Colour(false, Util.newColour(0xFA2424), Util.newColour(0xFA2424), "bright red") {};
	public static Colour CLOTHING_DESATURATED_BROWN = new Colour(false, Util.newColour(0x6c5d53), Util.newColour(0x6c5d53), "desaturated brown") {};
	public static Colour CLOTHING_DESATURATED_BROWN_DARK = new Colour(false, Util.newColour(0x3a2e25), Util.newColour(0x3a2e25), "dark desaturated brown") {};
	public static Colour CLOTHING_BROWN = new Colour(false, Util.newColour(0x8c5b39), Util.newColour(0x8c5b39), "brown") {};
	public static Colour CLOTHING_BROWN_DARK = new Colour(false, Util.newColour(0x634028), Util.newColour(0x634028), "dark brown") {};
	public static Colour CLOTHING_BROWN_VERY_DARK = new Colour(false, Util.newColour(0x3c2618), Util.newColour(0x3c2618), "midnight brown") {};
	public static Colour CLOTHING_ORANGE = new Colour(false, Util.newColour(0xE79F6F), Util.newColour(0xE79F6F), "orange") {};
	public static Colour CLOTHING_ORANGE_BRIGHT = new Colour(false, Util.newColour(0xFF7900), Util.newColour(0xFF7900), "bright orange") {};
	public static Colour CLOTHING_ORANGE_DARK = new Colour(false, Util.newColour(0xE56D00), Util.newColour(0xE56D00), "dark orange") {};
	public static Colour CLOTHING_TAN = new Colour(false, Util.newColour(0xd9bc98), Util.newColour(0xd9bc98), "tan") {};
	public static Colour CLOTHING_KHAKI = new Colour(false, Util.newColour(0xaa9e76), Util.newColour(0xaa9e76), "khaki") {};
	public static Colour CLOTHING_OLIVE = new Colour(false, Util.newColour(0x5f4a2a), Util.newColour(0x5f4a2a), "olive") {};//0x887509 0x5f4a2a
	public static Colour CLOTHING_YELLOW = new Colour(false, Util.newColour(0xE2C360), Util.newColour(0xE2C360), "yellow") {};
	public static Colour CLOTHING_YELLOW_DARK = new Colour(false, Util.newColour(0x7F691A), Util.newColour(0x7F691A), "mustard yellow") {};
	public static Colour CLOTHING_GREEN_LIME = new Colour(false, Util.newColour(0xD0E37D), Util.newColour(0xD0E37D), "lime green") {};
	public static Colour CLOTHING_GREEN = new Colour(false, Util.newColour(0x74AA74), Util.newColour(0x74AA74), "green") {};
	public static Colour CLOTHING_GREEN_DRAB = new Colour(false, Util.newColour(0x4C5D4C), Util.newColour(0x4C5D4C), "drab green") {};
	public static Colour CLOTHING_GREEN_DARK = new Colour(false, Util.newColour(0x3B6F3D), Util.newColour(0x3B6F3D), "dark green") {};
	public static Colour CLOTHING_GREEN_VERY_DARK = new Colour(false, Util.newColour(0x1C311C), Util.newColour(0x1C311C), "midnight green") {};
	public static Colour CLOTHING_TURQUOISE = new Colour(false, Util.newColour(0x6EC4B3), Util.newColour(0x6EC4B3), "turquoise") {};
	public static Colour CLOTHING_BLUE_LIGHT = new Colour(false, Util.newColour(0x72CFE3), Util.newColour(0x72CFE3), "light blue") {};
	public static Colour CLOTHING_BLUE = new Colour(false, Util.newColour(0x3971C6), Util.newColour(0x3971C6), "blue") {};
	public static Colour CLOTHING_BLUE_GREY = new Colour(false, Util.newColour(0x5D8AA8), Util.newColour(0x5D8AA8), "blue-grey") {};
	public static Colour CLOTHING_BLUE_NAVY = new Colour(false, Util.newColour(0x1f2c3f), Util.newColour(0x1f2c3f), "navy blue") {};
	public static Colour CLOTHING_BLUE_DARK = new Colour(false, Util.newColour(0x003C89), Util.newColour(0x003C89), "dark blue") {};
	public static Colour CLOTHING_BLUE_VERY_DARK = new Colour(false, Util.newColour(0x002C66), Util.newColour(0x002C66), "midnight blue") {};
	public static Colour CLOTHING_PURPLE_VERY_DARK = new Colour(false, Util.newColour(0x322145), Util.newColour(0x322145), "midnight purple") {};
	public static Colour CLOTHING_PURPLE_ROYAL = new Colour(false, Util.newColour(0x66023C), Util.newColour(0x66023C), "royal purple") {};
	public static Colour CLOTHING_PURPLE_DARK = new Colour(false, Util.newColour(0x674A95), Util.newColour(0x674A95), "dark purple") {};
	public static Colour CLOTHING_PURPLE = new Colour(false, Util.newColour(0xA382D3), Util.newColour(0xA382D3), "purple") {};
	public static Colour CLOTHING_PURPLE_LIGHT = new Colour(false, Util.newColour(0xC58ED7), Util.newColour(0xC58ED7), "violet") {};
	public static Colour CLOTHING_PERIWINKLE = new Colour(false, Util.newColour(0xCCCCFF), Util.newColour(0xCCCCFF), "periwinkle") {};
	public static Colour CLOTHING_PINK_LIGHT = new Colour(false, Util.newColour(0xF4B3F4), Util.newColour(0xF4B3F4), "light pink") {};
	public static Colour CLOTHING_PINK = new Colour(false, Util.newColour(0xD75086), Util.newColour(0xD75086), "pink") {};
	public static Colour CLOTHING_PINK_DARK = new Colour(false, Util.newColour(0xFF1493), Util.newColour(0xFF1493), "deep pink") {};
	public static Colour CLOTHING_PINK_HOT = new Colour(false, Util.newColour(0xff69b4), Util.newColour(0xff69b4), "hot pink") {};
	
	
	public static Colour CLOTHING_BLACK_STEEL = new Colour(true, Util.newColour(0x333333), Util.newColour(0x333333), "black steel") {};
	public static Colour CLOTHING_GUNMETAL = new Colour(true, Util.newColour(0x555555), Util.newColour(0x555555), "gunmetal gray") {};
	public static Colour CLOTHING_STEEL = new Colour(true, Util.newColour(0x969696), Util.newColour(0x969696), "steel") {};
	public static Colour CLOTHING_IRON = new Colour(true, Util.newColour(0xa9a39b), Util.newColour(0xa9a39b), "iron") {};
	public static Colour CLOTHING_BRASS = new Colour(true, BaseColour.BRASS, "brass") {};
	public static Colour CLOTHING_COPPER = new Colour(true, Util.newColour(0xD46F2B), Util.newColour(0xD46F2B), "copper", Util.newArrayListOfValues("copper")) {};
	public static Colour CLOTHING_SILVER = new Colour(true, Util.newColour(0xC4C4C4), Util.newColour(0xC4C4C4), "silver", Util.newArrayListOfValues("silver")) {};
	public static Colour CLOTHING_BRONZE = new Colour(true, BaseColour.BRONZE, "bronze", Util.newArrayListOfValues("bronze")) {};
	public static Colour CLOTHING_GOLD = new Colour(true, Util.newColour(0xEBC633), Util.newColour(0xEBC633), "gold") {};
	public static Colour CLOTHING_ROSE_GOLD = new Colour(true, BaseColour.ROSE_GOLD, "rose gold") {};
	public static Colour CLOTHING_PLATINUM = new Colour(true, BaseColour.PLATINUM, "platinum") {};
	
	
	// Body parts:

	// Skin (Human and Demon):
	// Standard:
	public static Colour SKIN_PALE = new Colour(false, Util.newColour(0xFBF4E9), Util.newColour(0x534946), "pale") {};
	public static Colour SKIN_LIGHT = new Colour(false, Util.newColour(0xEFDBD7), Util.newColour(0x534946), "light") {}.setLinkedColourLighter(SKIN_PALE);
	public static Colour SKIN_PORCELAIN = new Colour(false, Util.newColour(0xDBCDB9), Util.newColour(0xDBCDB9), "porcelain") {}.setLinkedColourLighter(SKIN_LIGHT);
	public static Colour SKIN_ROSY = new Colour(false, Util.newColour(0xDDAA93), Util.newColour(0xDDAA93), "rosy") {}.setLinkedColourLighter(SKIN_PORCELAIN);
	public static Colour SKIN_OLIVE = new Colour(false, BaseColour.TAN, "olive") {}.setLinkedColourLighter(SKIN_ROSY);
	public static Colour SKIN_TANNED = new Colour(false, Util.newColour(0xC39D6B), Util.newColour(0xC39D6B), "tanned") {}.setLinkedColourLighter(SKIN_OLIVE);
	public static Colour SKIN_DARK = new Colour(false, BaseColour.BROWN_DARK, "dark") {}.setLinkedColourLighter(SKIN_TANNED);
	public static Colour SKIN_CHOCOLATE = new Colour(false, Util.newColour(0x59372D), Util.newColour(0x59372D), "chocolate") {}.setLinkedColourLighter(SKIN_DARK);
	public static Colour SKIN_EBONY = new Colour(false, BaseColour.BLACK, "ebony") {}.setLinkedColourLighter(SKIN_CHOCOLATE);
	// Monochrome:
	public static Colour SKIN_IVORY = new Colour(false, BaseColour.WHITE, "ivory") {};
	public static Colour SKIN_GREY = new Colour(false, BaseColour.GREY, "grey") {}.setLinkedColourLighter(SKIN_IVORY);
	public static Colour SKIN_JET_BLACK = new Colour(false, BaseColour.BLACK, "pitch black") { public String getCoveringIconColour() { return BaseColour.PITCH_BLACK.toWebHexString(); } }.setLinkedColourLighter(SKIN_GREY);
	// Pink:
	public static Colour SKIN_PINK_PALE = new Colour(false, BaseColour.PINK_PALE, "pale pink") {};
	public static Colour SKIN_PINK_LIGHT = new Colour(false, BaseColour.PINK_LIGHT, "light pink") {}.setLinkedColourLighter(SKIN_PINK_PALE);
	public static Colour SKIN_PINK = new Colour(false, BaseColour.PINK, "pink") {}.setLinkedColourLighter(SKIN_PINK_LIGHT);
	// Red:
	public static Colour SKIN_RED = new Colour(false, BaseColour.CRIMSON, "scarlet") {};
	public static Colour SKIN_RED_DARK = new Colour(false, BaseColour.RED_DARK, "dark red") {}.setLinkedColourLighter(SKIN_RED);
	// Brown:
	public static Colour SKIN_BROWN = new Colour(false, BaseColour.BROWN, "brown") {};
	// Orange:
	public static Colour SKIN_ORANGE = new Colour(false, BaseColour.ORANGE, "orange") {};
	// Yellow:
	public static Colour SKIN_YELLOW = new Colour(false, BaseColour.YELLOW, "yellow") {};
	public static Colour SKIN_AMBER = new Colour(false, BaseColour.AMBER, "amber") {}.setLinkedColourLighter(SKIN_YELLOW);
	// Green:
	public static Colour SKIN_GREEN_LIGHT = new Colour(false, BaseColour.GREEN_LIGHT, "light green") {};
	public static Colour SKIN_GREEN = new Colour(false, BaseColour.GREEN, "green") {}.setLinkedColourLighter(SKIN_GREEN_LIGHT);
	public static Colour SKIN_GREEN_DARK = new Colour(false, BaseColour.GREEN_DARK, "dark green") {}.setLinkedColourLighter(SKIN_GREEN);
	// Blue:
	public static Colour SKIN_BLUE_LIGHT = new Colour(false, BaseColour.BLUE_LIGHT, "light blue") {};
	public static Colour SKIN_BLUE = new Colour(false, BaseColour.BLUE, "blue") {}.setLinkedColourLighter(SKIN_BLUE_LIGHT);
	public static Colour SKIN_BLUE_DARK = new Colour(false, BaseColour.BLUE_DARK, "dark blue") {}.setLinkedColourLighter(SKIN_BLUE);
	// Purple:
	public static Colour SKIN_PERIWINKLE = new Colour(false, BaseColour.PERIWINKLE, "periwinkle") {};
	public static Colour SKIN_LILAC_LIGHT = new Colour(false, BaseColour.LILAC_LIGHT, "pale lilac") {}.setLinkedColourLighter(SKIN_PERIWINKLE);
	public static Colour SKIN_LILAC = new Colour(false, BaseColour.LILAC, "lilac") {}.setLinkedColourLighter(SKIN_LILAC_LIGHT);
	public static Colour SKIN_INDIGO = new Colour(false, BaseColour.INDIGO, "indigo") {}.setLinkedColourLighter(SKIN_LILAC);
	public static Colour SKIN_PURPLE = new Colour(false, BaseColour.PURPLE, "purple") {}.setLinkedColourLighter(SKIN_INDIGO);
	public static Colour SKIN_PURPLE_DARK = new Colour(false, BaseColour.PURPLE_DARK, "dark purple") {}.setLinkedColourLighter(SKIN_PURPLE);
	
	// Orifices:
	public static Colour ORIFICE_INTERIOR = new Colour(false, BaseColour.ROSE, "fleshy-pink") {};

	// Misc:
	public static Colour TONGUE = new Colour(false, BaseColour.ROSE, "pink") {};

	// Generic colours:
	
	// Metallic:
	public static Colour COVERING_BRONZE = new Colour(true, BaseColour.BRONZE, "metallic bronze") {};
	public static Colour COVERING_SILVER = new Colour(true, BaseColour.GREY, "metallic silver") {};
	public static Colour COVERING_GOLD = new Colour(true, BaseColour.GOLD, "metallic gold") {};
	public static Colour COVERING_PLATINUM = new Colour(true, BaseColour.PLATINUM, "metallic platinum") {};
	public static Colour COVERING_ROSE_GOLD = new Colour(true, BaseColour.ROSE_GOLD, "metallic rose gold") {};
	public static Colour COVERING_COPPER = new Colour(true, BaseColour.COPPER, "metallic copper") {};
	public static Colour COVERING_BRASS = new Colour(true, BaseColour.BRASS, "metallic brass") {};
	public static Colour COVERING_STEEL = new Colour(true, Util.newColour(0x969696), Util.newColour(0x969696), "metallic steel") {};
	public static Colour COVERING_BLACK_STEEL = new Colour(true, Util.newColour(0x555555), Util.newColour(0x111111), "metallic black steel") {};
	// Monochrome:
	public static Colour COVERING_WHITE = new Colour(false, BaseColour.WHITE, "white") {};
	public static Colour COVERING_GREY = new Colour(false, BaseColour.GREY, "grey") {}.setLinkedColourLighter(COVERING_WHITE);
	public static Colour COVERING_DARK_GREY = new Colour(false, BaseColour.GREY_DARK, "dark-grey") {}.setLinkedColourLighter(COVERING_GREY);
	public static Colour COVERING_BLACK = new Colour(false, BaseColour.BLACK, "black") {}.setLinkedColourLighter(COVERING_DARK_GREY);
	public static Colour COVERING_JET_BLACK = new Colour(false, BaseColour.BLACK, "pitch black") { public String getCoveringIconColour() { return BaseColour.PITCH_BLACK.toWebHexString(); } }.setLinkedColourLighter(COVERING_BLACK);
	// Pink:
	public static Colour COVERING_PINK_LIGHT = new Colour(false, BaseColour.PINK_LIGHT, "light pink") {};
	public static Colour COVERING_PINK = new Colour(false, BaseColour.PINK, "pink") {}.setLinkedColourLighter(COVERING_PINK_LIGHT);
	public static Colour COVERING_PINK_DARK = new Colour(false, BaseColour.PINK_DEEP, "dark pink") {}.setLinkedColourLighter(COVERING_PINK);
	// Red:
	public static Colour COVERING_RED_LIGHT = new Colour(false, BaseColour.RED_LIGHT, "light red") {};
	public static Colour COVERING_RED = new Colour(false, BaseColour.RED, "red") {}.setLinkedColourLighter(COVERING_RED_LIGHT);
	public static Colour COVERING_RED_DARK = new Colour(false, BaseColour.RED_DARK, "dark red") {}.setLinkedColourLighter(COVERING_RED);
	public static Colour COVERING_SCARLET = new Colour(false, BaseColour.CRIMSON, "scarlet") {}.setLinkedColourLighter(COVERING_RED_DARK);
	// Brown:
	public static Colour COVERING_TAN = new Colour(false, BaseColour.TAN, "tan") {};
	public static Colour COVERING_BROWN_LIGHT = new Colour(false, BaseColour.BROWN_LIGHT, "light brown") {}.setLinkedColourLighter(COVERING_TAN);
	public static Colour COVERING_BROWN = new Colour(false, BaseColour.BROWN, "brown") {}.setLinkedColourLighter(COVERING_BROWN_LIGHT);
	public static Colour COVERING_BROWN_DARK = new Colour(false, BaseColour.BROWN_DARK, "dark brown") {}.setLinkedColourLighter(COVERING_BROWN);
	// Orange:
	public static Colour COVERING_ORANGE_LIGHT = new Colour(false, BaseColour.ORANGE_LIGHT, "light orange") {};
	public static Colour COVERING_AMBER = new Colour(false, BaseColour.AMBER, "amber") {}.setLinkedColourLighter(COVERING_ORANGE_LIGHT);
	public static Colour COVERING_ORANGE = new Colour(false, BaseColour.ORANGE, "orange") {}.setLinkedColourLighter(COVERING_AMBER);
	public static Colour COVERING_GINGER = new Colour(false, BaseColour.GINGER, "ginger") {}.setLinkedColourLighter(COVERING_ORANGE);
	public static Colour COVERING_ORANGE_DARK = new Colour(false, BaseColour.ORANGE_DARK, "dark orange") {}.setLinkedColourLighter(COVERING_GINGER);
	public static Colour COVERING_AUBURN = new Colour(false, BaseColour.AUBURN, "auburn") {}.setLinkedColourLighter(COVERING_ORANGE_DARK);
	// Yellow:
	public static Colour COVERING_DIRTY_BLONDE = new Colour(false, BaseColour.TAN, "dirty-blonde") {};
	public static Colour COVERING_SANDY = new Colour(false, Util.newColour(0xddc48b), Util.newColour(0xddc48b), "sandy") {}.setLinkedColourLighter(COVERING_DIRTY_BLONDE);
	public static Colour COVERING_YELLOW = new Colour(false, BaseColour.YELLOW, "yellow") {}.setLinkedColourLighter(COVERING_SANDY);
	public static Colour COVERING_BLONDE = new Colour(false, BaseColour.YELLOW_LIGHT, "blonde") {}.setLinkedColourLighter(COVERING_YELLOW);
	public static Colour COVERING_BLEACH_BLONDE = new Colour(false, BaseColour.YELLOW_PALE, "bleach-blonde") {}.setLinkedColourLighter(COVERING_BLONDE);
	// Green:
	public static Colour COVERING_GREEN_LIGHT = new Colour(false, BaseColour.GREEN_LIGHT, "light green") {};
	public static Colour COVERING_GREEN = new Colour(false, BaseColour.GREEN, "green") {}.setLinkedColourLighter(COVERING_GREEN_LIGHT);
	public static Colour COVERING_GREEN_DARK = new Colour(false, BaseColour.GREEN_DARK, "dark green") {}.setLinkedColourLighter(COVERING_GREEN);
	// Blue:
	public static Colour COVERING_BLUE_LIGHT = new Colour(false, BaseColour.BLUE_LIGHT, "light blue") {};
	public static Colour COVERING_BLUE = new Colour(false, BaseColour.BLUE, "blue") {}.setLinkedColourLighter(COVERING_BLUE_LIGHT);
	public static Colour COVERING_BLUE_DARK = new Colour(false, BaseColour.BLUE_DARK, "dark blue") {}.setLinkedColourLighter(COVERING_BLUE);
	// Purple:
	public static Colour COVERING_PERIWINKLE = new Colour(false, BaseColour.PERIWINKLE, "periwinkle") {};
	public static Colour COVERING_LILAC_LIGHT = new Colour(false, BaseColour.LILAC_LIGHT, "pale lilac") {}.setLinkedColourLighter(COVERING_PERIWINKLE);
	public static Colour COVERING_LILAC = new Colour(false, BaseColour.LILAC, "lilac") {}.setLinkedColourLighter(COVERING_LILAC_LIGHT);
	public static Colour COVERING_INDIGO = new Colour(false, BaseColour.INDIGO, "indigo") {}.setLinkedColourLighter(COVERING_LILAC);
	public static Colour COVERING_PURPLE_LIGHT = new Colour(false, BaseColour.PURPLE_LIGHT, "light purple") {}.setLinkedColourLighter(COVERING_INDIGO);
	public static Colour COVERING_PURPLE = new Colour(false, BaseColour.PURPLE, "purple") {}.setLinkedColourLighter(COVERING_PURPLE_LIGHT);
	public static Colour COVERING_PURPLE_DARK = new Colour(false, BaseColour.PURPLE_DARK, "dark purple") {}.setLinkedColourLighter(COVERING_PURPLE);
	
	
	// Specials:
	public static Colour COVERING_CLEAR = new Colour(false, BaseColour.WHITE, "clear") {}; // For nail-polish
	public static Colour COVERING_RAINBOW = new Colour(false, BaseColour.BLUE_LIGHT,
			"<span style='color:#E64C4C;'>r</span>"
			+ "<span style='color:#E6854C;'>a</span>"
			+ "<span style='color:#E6C74C;'>i</span>"
			+ "<span style='color:#6EE64C;'>n</span>"
			+ "<span style='color:#4CB2E6;'>b</span>"
			+ "<span style='color:#AD4CE6;'>o</span>"
			+ "<span style='color:#E64CA8;'>w</span>", 
			Util.newArrayListOfValues("rainbow")) {
		@Override
		public List<String> getRainbowColours() {
			return Util.newArrayListOfValues(
					"#E64C4C",
					"#E6854C",
					"#E6C74C",
					"#6EE64C",
					"#4CB2E6",
					"#AD4CE6",
					"#E64CA8");
		}
	};
	public static Colour COVERING_RAINBOW_PASTEL = new Colour(false, BaseColour.PINK_LIGHT,
			"<span style='color:#4bb1d0;'>p</span>"
			+ "<span style='color:#7bd8b0;'>a</span>"
			+ "<span style='color:#83a5ef;'>s</span>"
			+ "<span style='color:#ecb6f9;'>t</span>"
			+ "<span style='color:#4bb1d0;'>e</span>"
			+ "<span style='color:#7bd8b0;'>l</span>"
			+ " "
			+ "<span style='color:#83a5ef;'>r</span>"
			+ "<span style='color:#ecb6f9;'>a</span>"
			+ "<span style='color:#4bb1d0;'>i</span>"
			+ "<span style='color:#7bd8b0;'>n</span>"
			+ "<span style='color:#83a5ef;'>b</span>"
			+ "<span style='color:#ecb6f9;'>o</span>"
			+ "<span style='color:#4bb1d0;'>w</span>", 
			Util.newArrayListOfValues("pastelRainbow")) {
		@Override
		public List<String> getRainbowColours() {
			return Util.newArrayListOfValues(
					"#4bb1d0",
					"#7bd8b0",
					"#83a5ef",
					"#ecb6f9");
		}
	};
	public static Colour COVERING_NONE = new Colour(false, BaseColour.GREY, "none") {};

	// Eye colours:

	// Special:
	public static Colour EYE_GOLD = new Colour(false, BaseColour.GOLD, "golden") {};
	public static Colour EYE_SILVER = new Colour(false, BaseColour.SILVER, "silver") {};
	// Monochrome:
	public static Colour EYE_WHITE = new Colour(false, BaseColour.WHITE, "white") {};
	public static Colour EYE_GREY = new Colour(false, BaseColour.GREY, "grey") {}.setLinkedColourLighter(EYE_WHITE);
	public static Colour EYE_BLACK = new Colour(false, BaseColour.BLACK, "black") {}.setLinkedColourLighter(EYE_GREY);
	public static Colour EYE_PITCH_BLACK = new Colour(false, BaseColour.BLACK, "pitch black") { public String getCoveringIconColour() { return BaseColour.PITCH_BLACK.toWebHexString(); } }.setLinkedColourLighter(EYE_BLACK);
	// Pink:
	public static Colour EYE_PINK_SALMON = new Colour(false, BaseColour.PINK_SALMON, "salmon-pink") {};
	public static Colour EYE_PINK = new Colour(false, BaseColour.PINK, "pink") {}.setLinkedColourLighter(EYE_PINK_SALMON);
	// Red:
	public static Colour EYE_RED = new Colour(false, BaseColour.RED, "red") {};
	public static Colour EYE_CRIMSON = new Colour(false, BaseColour.CRIMSON, "crimson") {}.setLinkedColourLighter(EYE_RED);
	// Brown:
	public static Colour EYE_HAZEL = new Colour(false, BaseColour.TAN, "hazel") {};
	public static Colour EYE_BROWN = new Colour(false, BaseColour.BROWN, "brown") {}.setLinkedColourLighter(EYE_HAZEL);
	// Orange:
	public static Colour EYE_AMBER = new Colour(false, BaseColour.AMBER, "amber") {};
	public static Colour EYE_ORANGE = new Colour(false, BaseColour.ORANGE, "orange") {}.setLinkedColourLighter(EYE_AMBER);
	// Yellow:
	public static Colour EYE_YELLOW = new Colour(false, BaseColour.YELLOW, "yellow") {};
	// Green:
	public static Colour EYE_GREY_GREEN = new Colour(false, Util.newColour(0xA9BA9D), Util.newColour(0xA9BA9D), "grey-green") {};
	public static Colour EYE_GREEN = new Colour(false, BaseColour.GREEN_DARK, "green") {}.setLinkedColourLighter(EYE_GREY_GREEN);
	// Blue:
	public static Colour EYE_BLUE_LIGHT = new Colour(false, BaseColour.BLUE_LIGHT, "light blue") {};
	public static Colour EYE_BLUE = new Colour(false, BaseColour.BLUE, "blue") {}.setLinkedColourLighter(EYE_BLUE_LIGHT);
	public static Colour EYE_AQUA = new Colour(false, BaseColour.AQUA, "aqua") {}.setLinkedColourLighter(EYE_BLUE);
	public static Colour EYE_BLUE_DARK = new Colour(false, BaseColour.BLUE_DARK, "dark blue") {}.setLinkedColourLighter(EYE_AQUA);
	// Purple:
	public static Colour EYE_PERIWINKLE = new Colour(false, BaseColour.PERIWINKLE, "periwinkle") {};
	public static Colour EYE_LILAC = new Colour(false, BaseColour.LILAC, "lilac") {}.setLinkedColourLighter(EYE_PERIWINKLE);
	public static Colour EYE_INDIGO = new Colour(false, BaseColour.INDIGO, "indigo") {}.setLinkedColourLighter(EYE_LILAC);
	public static Colour EYE_PURPLE = new Colour(false, BaseColour.PURPLE, "purple") {}.setLinkedColourLighter(EYE_INDIGO);
	public static Colour EYE_VIOLET = new Colour(false, BaseColour.PURPLE_DARK, "violet") {}.setLinkedColourLighter(EYE_PURPLE);


	
	public static final Colour[] ACTION_POINT_COLOURS = new Colour[] {
			PresetColour.GENERIC_EXCELLENT,
			PresetColour.GENERIC_GOOD,
			PresetColour.GENERIC_MINOR_GOOD,
			PresetColour.GENERIC_MINOR_BAD,
			PresetColour.GENERIC_BAD,
			PresetColour.GENERIC_TERRIBLE};
	
	// Skin/fur/body part groups:
	
	private static Map<Colour, Integer> humanSkinColours = Util.newHashMapOfValues(
			new Value<>(PresetColour.SKIN_PALE, 5),
			new Value<>(PresetColour.SKIN_LIGHT, 5),
			new Value<>(PresetColour.SKIN_PORCELAIN, 5),
			new Value<>(PresetColour.SKIN_ROSY, 5),
			new Value<>(PresetColour.SKIN_OLIVE, 5),
			new Value<>(PresetColour.SKIN_TANNED, 5),
			new Value<>(PresetColour.SKIN_DARK, 5),
			new Value<>(PresetColour.SKIN_CHOCOLATE, 5),
			new Value<>(PresetColour.SKIN_EBONY, 5));

	public static Map<Colour, Integer> getHumanSkinColoursMap() {
		return humanSkinColours;
	}
	
	public static List<Colour> getHumanSkinColours() {
		return new ArrayList<>(humanSkinColours.keySet());
	}

	public static List<Colour> ratSkinColours = Util.newArrayListOfValues(
			PresetColour.SKIN_PINK_PALE);
	
	public static List<Colour> demonSkinColours = Util.newArrayListOfValues(
			PresetColour.SKIN_PALE,
			PresetColour.SKIN_LIGHT,
			PresetColour.SKIN_PORCELAIN,
			PresetColour.SKIN_ROSY,
			PresetColour.SKIN_OLIVE,
			PresetColour.SKIN_TANNED,
			PresetColour.SKIN_DARK,
			PresetColour.SKIN_CHOCOLATE,
			PresetColour.SKIN_EBONY,
			PresetColour.SKIN_IVORY,
			PresetColour.SKIN_GREY,
			PresetColour.SKIN_JET_BLACK,
			PresetColour.SKIN_RED,
			PresetColour.SKIN_RED_DARK,
			PresetColour.SKIN_BROWN,
			PresetColour.SKIN_AMBER,
			PresetColour.SKIN_ORANGE,
			PresetColour.SKIN_YELLOW,
			PresetColour.SKIN_GREEN_LIGHT,
			PresetColour.SKIN_GREEN,
			PresetColour.SKIN_GREEN_DARK,
			PresetColour.SKIN_BLUE_LIGHT,
			PresetColour.SKIN_BLUE,
			PresetColour.SKIN_BLUE_DARK,
			PresetColour.SKIN_PERIWINKLE,
			PresetColour.SKIN_LILAC_LIGHT,
			PresetColour.SKIN_LILAC,
			PresetColour.SKIN_INDIGO,
			PresetColour.SKIN_PURPLE,
			PresetColour.SKIN_PURPLE_DARK,
			PresetColour.SKIN_PINK_PALE,
			PresetColour.SKIN_PINK_LIGHT,
			PresetColour.SKIN_PINK);

	public static List<Colour> allSkinColours = Util.newArrayListOfValues(
			PresetColour.SKIN_PALE,
			PresetColour.SKIN_LIGHT,
			PresetColour.SKIN_PORCELAIN,
			PresetColour.SKIN_ROSY,
			PresetColour.SKIN_OLIVE,
			PresetColour.SKIN_TANNED,
			PresetColour.SKIN_DARK,
			PresetColour.SKIN_CHOCOLATE,
			PresetColour.SKIN_EBONY,
			PresetColour.SKIN_IVORY,
			PresetColour.SKIN_GREY,
			PresetColour.SKIN_JET_BLACK,
			PresetColour.SKIN_RED,
			PresetColour.SKIN_RED_DARK,
			PresetColour.SKIN_BROWN,
			PresetColour.SKIN_AMBER,
			PresetColour.SKIN_ORANGE,
			PresetColour.SKIN_YELLOW,
			PresetColour.SKIN_GREEN_LIGHT,
			PresetColour.SKIN_GREEN,
			PresetColour.SKIN_GREEN_DARK,
			PresetColour.SKIN_BLUE_LIGHT,
			PresetColour.SKIN_BLUE,
			PresetColour.SKIN_BLUE_DARK,
			PresetColour.SKIN_PERIWINKLE,
			PresetColour.SKIN_LILAC_LIGHT,
			PresetColour.SKIN_LILAC,
			PresetColour.SKIN_INDIGO,
			PresetColour.SKIN_PURPLE,
			PresetColour.SKIN_PURPLE_DARK,
			PresetColour.SKIN_PINK_PALE,
			PresetColour.SKIN_PINK_LIGHT,
			PresetColour.SKIN_PINK,
			PresetColour.COVERING_RAINBOW,
			PresetColour.COVERING_RAINBOW_PASTEL);


	public static List<Colour> naturalSlimeColours = Util.newArrayListOfValues(
			PresetColour.COVERING_CLEAR,
			PresetColour.COVERING_WHITE,
			PresetColour.COVERING_GREY,
			PresetColour.COVERING_BLACK,
			PresetColour.COVERING_JET_BLACK,
			PresetColour.COVERING_SCARLET,
			PresetColour.COVERING_RED_LIGHT,
			PresetColour.COVERING_RED,
			PresetColour.COVERING_RED_DARK,
			PresetColour.COVERING_TAN,
			PresetColour.COVERING_BROWN_LIGHT,
			PresetColour.COVERING_BROWN,
			PresetColour.COVERING_BROWN_DARK,
			PresetColour.COVERING_ORANGE_DARK,
			PresetColour.COVERING_ORANGE,
			PresetColour.COVERING_YELLOW,
			PresetColour.COVERING_AMBER,
			PresetColour.COVERING_ORANGE_LIGHT,
			PresetColour.COVERING_GREEN_LIGHT,
			PresetColour.COVERING_GREEN,
			PresetColour.COVERING_GREEN_DARK,
			PresetColour.COVERING_BLUE_LIGHT,
			PresetColour.COVERING_BLUE,
			PresetColour.COVERING_BLUE_DARK,
			PresetColour.COVERING_PERIWINKLE,
			PresetColour.COVERING_LILAC_LIGHT,
			PresetColour.COVERING_LILAC,
			PresetColour.COVERING_INDIGO,
			PresetColour.COVERING_PURPLE_LIGHT,
			PresetColour.COVERING_PURPLE,
			PresetColour.COVERING_PURPLE_DARK,
			PresetColour.COVERING_PINK,
			PresetColour.COVERING_PINK_LIGHT
			);
	
	public static List<Colour> dyeSlimeColours = Util.newArrayListOfValues(
			PresetColour.COVERING_PLATINUM,
			PresetColour.COVERING_ROSE_GOLD,
			PresetColour.COVERING_GOLD,
			PresetColour.COVERING_SILVER,
			PresetColour.COVERING_BRONZE,
			PresetColour.COVERING_COPPER,
			PresetColour.COVERING_BRASS,
			PresetColour.COVERING_STEEL,
			PresetColour.COVERING_BLACK_STEEL,
			
			PresetColour.COVERING_RAINBOW,
			PresetColour.COVERING_RAINBOW_PASTEL
	);
	
	public static List<Colour> naturalFeatherColours = Util.newArrayListOfValues(
			PresetColour.COVERING_WHITE,
			PresetColour.COVERING_GREY,
			PresetColour.COVERING_BLACK,
			PresetColour.COVERING_JET_BLACK,
			PresetColour.COVERING_SCARLET,
			PresetColour.COVERING_RED_LIGHT,
			PresetColour.COVERING_RED,
			PresetColour.COVERING_RED_DARK,
			PresetColour.COVERING_AUBURN,
			PresetColour.COVERING_TAN,
			PresetColour.COVERING_BROWN_LIGHT,
			PresetColour.COVERING_BROWN,
			PresetColour.COVERING_BROWN_DARK,
			PresetColour.COVERING_ORANGE_DARK,
			PresetColour.COVERING_ORANGE,
			PresetColour.COVERING_ORANGE_LIGHT,
			PresetColour.COVERING_GINGER,
			PresetColour.COVERING_BLEACH_BLONDE,
			PresetColour.COVERING_YELLOW,
			PresetColour.COVERING_AMBER,
			PresetColour.COVERING_GREEN_LIGHT,
			PresetColour.COVERING_GREEN,
			PresetColour.COVERING_GREEN_DARK,
			PresetColour.COVERING_BLUE_LIGHT,
			PresetColour.COVERING_BLUE,
			PresetColour.COVERING_BLUE_DARK,
			PresetColour.COVERING_PERIWINKLE,
			PresetColour.COVERING_LILAC_LIGHT,
			PresetColour.COVERING_LILAC,
			PresetColour.COVERING_INDIGO,
			PresetColour.COVERING_PURPLE_LIGHT,
			PresetColour.COVERING_PURPLE,
			PresetColour.COVERING_PURPLE_DARK,
			PresetColour.COVERING_PINK,
			PresetColour.COVERING_PINK_LIGHT
			);
			
			
	public static List<Colour> dyeFeatherColours = Util.newArrayListOfValues(
			PresetColour.COVERING_PLATINUM,
			PresetColour.COVERING_ROSE_GOLD,
			PresetColour.COVERING_GOLD,
			PresetColour.COVERING_SILVER,
			PresetColour.COVERING_BRONZE,
			PresetColour.COVERING_COPPER,
			PresetColour.COVERING_BRASS,
			PresetColour.COVERING_STEEL,
			PresetColour.COVERING_BLACK_STEEL,
			
			PresetColour.COVERING_RAINBOW,
			PresetColour.COVERING_RAINBOW_PASTEL
			);
	
	public static List<Colour> naturalFurColours = Util.newArrayListOfValues(
			PresetColour.COVERING_WHITE,
//			PresetColour.COVERING_BLONDE,
			PresetColour.COVERING_SANDY,
//			PresetColour.COVERING_GINGER,
			PresetColour.COVERING_BROWN_LIGHT,
			PresetColour.COVERING_BROWN,
			PresetColour.COVERING_BROWN_DARK,
			PresetColour.COVERING_TAN,
			PresetColour.COVERING_GREY,
			PresetColour.COVERING_BLACK
//			,
//			PresetColour.COVERING_JET_BLACK
			);

	public static List<Colour> allCoveringColours = Util.newArrayListOfValues(
			PresetColour.COVERING_PLATINUM,
			PresetColour.COVERING_ROSE_GOLD,
			PresetColour.COVERING_GOLD,
			PresetColour.COVERING_SILVER,
			PresetColour.COVERING_BRONZE,
			PresetColour.COVERING_COPPER,
			PresetColour.COVERING_BRASS,
			PresetColour.COVERING_STEEL,
			PresetColour.COVERING_BLACK_STEEL,
			
			PresetColour.COVERING_WHITE,
			PresetColour.COVERING_GREY,
			PresetColour.COVERING_BLACK,
			PresetColour.COVERING_JET_BLACK,
			PresetColour.COVERING_SCARLET,
			PresetColour.COVERING_RED_LIGHT,
			PresetColour.COVERING_RED,
			PresetColour.COVERING_RED_DARK,
			PresetColour.COVERING_AUBURN,
			PresetColour.COVERING_TAN,
			PresetColour.COVERING_BROWN_LIGHT,
			PresetColour.COVERING_BROWN,
			PresetColour.COVERING_BROWN_DARK,
			PresetColour.COVERING_ORANGE_DARK,
			PresetColour.COVERING_ORANGE,
			PresetColour.COVERING_ORANGE_LIGHT,
			PresetColour.COVERING_GINGER,
			PresetColour.COVERING_SANDY,
			PresetColour.COVERING_BLEACH_BLONDE,
			PresetColour.COVERING_BLONDE,
			PresetColour.COVERING_YELLOW,
			PresetColour.COVERING_AMBER,
			PresetColour.COVERING_GREEN_LIGHT,
			PresetColour.COVERING_GREEN,
			PresetColour.COVERING_GREEN_DARK,
			PresetColour.COVERING_BLUE_LIGHT,
			PresetColour.COVERING_BLUE,
			PresetColour.COVERING_BLUE_DARK,
			PresetColour.COVERING_PERIWINKLE,
			PresetColour.COVERING_LILAC_LIGHT,
			PresetColour.COVERING_LILAC,
			PresetColour.COVERING_INDIGO,
			PresetColour.COVERING_PURPLE_LIGHT,
			PresetColour.COVERING_PURPLE,
			PresetColour.COVERING_PURPLE_DARK,
			PresetColour.COVERING_PINK,
			PresetColour.COVERING_PINK_LIGHT,
			PresetColour.COVERING_RAINBOW,
			PresetColour.COVERING_RAINBOW_PASTEL
			);
	
	// I know...
	public static List<Colour> allCoveringColoursWithClear = Util.mergeLists(Util.newArrayListOfValues(PresetColour.COVERING_CLEAR), allCoveringColours);
	public static List<Colour> allMakeupColours = Util.mergeLists(Util.newArrayListOfValues(PresetColour.COVERING_CLEAR), allCoveringColours);

	public static List<Colour> naturalScaleColours = Util.newArrayListOfValues(
			PresetColour.COVERING_WHITE,
			PresetColour.COVERING_TAN,
			PresetColour.COVERING_BROWN_LIGHT,
			PresetColour.COVERING_BROWN,
			PresetColour.COVERING_BROWN_DARK,
			PresetColour.COVERING_BLACK,
			PresetColour.COVERING_JET_BLACK);

	public static List<Colour> hornColours = Util.newArrayListOfValues(
			PresetColour.COVERING_WHITE,
			PresetColour.COVERING_GREY,
			PresetColour.COVERING_DARK_GREY,
			PresetColour.COVERING_BLACK,
			PresetColour.COVERING_JET_BLACK);
	
	// Antlers:
	public static List<Colour> antlerColours = Util.newArrayListOfValues(
			PresetColour.COVERING_WHITE,
			PresetColour.COVERING_TAN,
			PresetColour.COVERING_BROWN_LIGHT,
			PresetColour.COVERING_BROWN,
			PresetColour.COVERING_BROWN_DARK,
			PresetColour.COVERING_GREY,
			PresetColour.COVERING_DARK_GREY,
			PresetColour.COVERING_BLACK,
			PresetColour.COVERING_JET_BLACK);
	
	// Hair:
	
	public static List<Colour> naturalHairColours = Util.newArrayListOfValues(
			PresetColour.COVERING_WHITE,
			PresetColour.COVERING_BLONDE,
			PresetColour.COVERING_DIRTY_BLONDE,
			PresetColour.COVERING_SANDY,
			PresetColour.COVERING_GINGER,
			PresetColour.COVERING_BROWN_LIGHT,
			PresetColour.COVERING_BROWN,
			PresetColour.COVERING_BROWN_DARK,
			PresetColour.COVERING_AUBURN,
			PresetColour.COVERING_GREY,
			PresetColour.COVERING_BLACK,
			PresetColour.COVERING_JET_BLACK);
	
	// Eyes:
	
	public static List<Colour> naturalIrisColours = Util.newArrayListOfValues(
			PresetColour.EYE_BROWN,
			PresetColour.EYE_AMBER,
			PresetColour.EYE_HAZEL,
			PresetColour.EYE_BLUE_DARK,
			PresetColour.EYE_BLUE,
			PresetColour.EYE_BLUE_LIGHT,
			PresetColour.EYE_AQUA,
			PresetColour.EYE_GREEN,
			PresetColour.EYE_GREY_GREEN,
			PresetColour.EYE_GREY);
	
	public static List<Colour> dyeIrisColours = Util.newArrayListOfValues(
			PresetColour.EYE_SILVER,
			PresetColour.EYE_YELLOW,
			PresetColour.EYE_GOLD,
			PresetColour.EYE_RED,
			PresetColour.EYE_CRIMSON,
			PresetColour.EYE_ORANGE,
			PresetColour.EYE_PINK,
			PresetColour.EYE_PINK_SALMON,
			PresetColour.EYE_PERIWINKLE,
			PresetColour.EYE_LILAC,
			PresetColour.EYE_INDIGO,
			PresetColour.EYE_PURPLE,
			PresetColour.EYE_VIOLET,
			PresetColour.EYE_BLACK,
			PresetColour.EYE_PITCH_BLACK,
			PresetColour.COVERING_RAINBOW,
			PresetColour.COVERING_RAINBOW_PASTEL);

	public static List<Colour> naturalDemonIrisColours = Util.newArrayListOfValues(
			PresetColour.EYE_BROWN,
			PresetColour.EYE_AMBER,
			PresetColour.EYE_HAZEL,
			PresetColour.EYE_BLUE_DARK,
			PresetColour.EYE_BLUE,
			PresetColour.EYE_BLUE_LIGHT,
			PresetColour.EYE_AQUA,
			PresetColour.EYE_GREEN,
			PresetColour.EYE_GREY_GREEN,
			PresetColour.EYE_GREY,
			PresetColour.EYE_RED,
			PresetColour.EYE_CRIMSON,
			PresetColour.EYE_ORANGE,
			PresetColour.EYE_YELLOW,
			PresetColour.EYE_PINK,
			PresetColour.EYE_PINK_SALMON,
			PresetColour.EYE_PERIWINKLE,
			PresetColour.EYE_LILAC,
			PresetColour.EYE_INDIGO,
			PresetColour.EYE_PURPLE,
			PresetColour.EYE_VIOLET,
			PresetColour.EYE_BLACK,
			PresetColour.EYE_PITCH_BLACK);
	
	public static List<Colour> dyeDemonIrisColours = Util.newArrayListOfValues(
			PresetColour.EYE_SILVER,
			PresetColour.EYE_GOLD,
			PresetColour.COVERING_RAINBOW,
			PresetColour.COVERING_RAINBOW_PASTEL);
	
	
	public static List<Colour> naturalPredatorIrisColours = Util.newArrayListOfValues(
			PresetColour.EYE_BROWN,
			PresetColour.EYE_AMBER,
			PresetColour.EYE_YELLOW,
			PresetColour.EYE_BLUE_DARK,
			PresetColour.EYE_BLUE,
			PresetColour.EYE_BLUE_LIGHT,
			PresetColour.EYE_AQUA,
			PresetColour.EYE_GREEN,
			PresetColour.EYE_GREY_GREEN,
			PresetColour.EYE_GREY);
	
	public static List<Colour> dyePredatorIrisColours = Util.newArrayListOfValues(
			PresetColour.EYE_SILVER,
			PresetColour.EYE_GOLD,
			PresetColour.EYE_RED,
			PresetColour.EYE_CRIMSON,
			PresetColour.EYE_ORANGE,
			PresetColour.EYE_PINK,
			PresetColour.EYE_PINK_SALMON,
			PresetColour.EYE_LILAC,
			PresetColour.EYE_INDIGO,
			PresetColour.EYE_PURPLE,
			PresetColour.EYE_VIOLET,
			PresetColour.EYE_BLACK,
			PresetColour.EYE_PITCH_BLACK,
			PresetColour.COVERING_RAINBOW,
			PresetColour.COVERING_RAINBOW_PASTEL);
	
	
	public static List<Colour> naturalPupilColours = Util.newArrayListOfValues(
			PresetColour.EYE_BLACK);
	
	public static List<Colour> dyePupilColours = Util.newArrayListOfValues(
			PresetColour.EYE_WHITE,
			PresetColour.EYE_SILVER,
			PresetColour.EYE_BROWN,
			PresetColour.EYE_BLUE_DARK,
			PresetColour.EYE_BLUE,
			PresetColour.EYE_BLUE_LIGHT,
			PresetColour.EYE_AQUA,
			PresetColour.EYE_GREEN,
			PresetColour.EYE_GREY_GREEN,
			PresetColour.EYE_GREY,
			PresetColour.EYE_YELLOW,
			PresetColour.EYE_GOLD,
			PresetColour.EYE_RED,
			PresetColour.EYE_CRIMSON,
			PresetColour.EYE_ORANGE,
			PresetColour.EYE_AMBER,
			PresetColour.EYE_PINK,
			PresetColour.EYE_PINK_SALMON,
			PresetColour.EYE_PERIWINKLE,
			PresetColour.EYE_LILAC,
			PresetColour.EYE_INDIGO,
			PresetColour.EYE_PURPLE,
			PresetColour.EYE_VIOLET,
			PresetColour.EYE_PITCH_BLACK,
			PresetColour.COVERING_RAINBOW,
			PresetColour.COVERING_RAINBOW_PASTEL);
	
	public static List<Colour> naturalScleraColours = Util.newArrayListOfValues(
			PresetColour.EYE_WHITE);
	
	public static List<Colour> dyeScleraColours = Util.newArrayListOfValues(
			PresetColour.EYE_BLACK,
			PresetColour.EYE_SILVER,
			PresetColour.EYE_BROWN,
			PresetColour.EYE_BLUE_DARK,
			PresetColour.EYE_BLUE,
			PresetColour.EYE_BLUE_LIGHT,
			PresetColour.EYE_AQUA,
			PresetColour.EYE_GREEN,
			PresetColour.EYE_GREY,
			PresetColour.EYE_YELLOW,
			PresetColour.EYE_GOLD,
			PresetColour.EYE_RED,
			PresetColour.EYE_CRIMSON,
			PresetColour.EYE_ORANGE,
			PresetColour.EYE_AMBER,
			PresetColour.EYE_PINK,
			PresetColour.EYE_PINK_SALMON,
			PresetColour.EYE_PERIWINKLE,
			PresetColour.EYE_LILAC,
			PresetColour.EYE_INDIGO,
			PresetColour.EYE_PURPLE,
			PresetColour.EYE_VIOLET,
			PresetColour.EYE_PITCH_BLACK,
			PresetColour.COVERING_RAINBOW,
			PresetColour.COVERING_RAINBOW_PASTEL);
	
	
	private static List<Colour> allPresetColours;
	private static Map<Colour, String> colourToIdMap = new HashMap<>();
	private static Map<String, Colour> idToColourMap = new HashMap<>();
	
	static {
		allPresetColours = new ArrayList<>();

		// Modded colours:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/colours");
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					Colour colour = new Colour(innerEntry.getValue(), entry.getKey(), true) {};
					String id = innerEntry.getKey();
					allPresetColours.add(colour);
					colourToIdMap.put(colour, id);
					idToColourMap.put(id, colour);
				} catch(Exception ex) {
					System.err.println("Loading modded colour failed at 'Colour'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		// External res colours:
		
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/colours");
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					Colour colour = new Colour(innerEntry.getValue(), entry.getKey(), false) {};
					String id = innerEntry.getKey();
					allPresetColours.add(colour);
					colourToIdMap.put(colour, id);
					idToColourMap.put(id, colour);
				} catch(Exception ex) {
					System.err.println("Loading colour failed at 'Colour'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		// Tags are only used for externally-loaded colours, so handle addition to lists here before hard-coded colours are checked:
		for(Colour colour : allPresetColours) {
			for(ColourTag tag : colour.getTags()) {
				switch(tag) {
					case ANTLER:
						antlerColours.add(colour);
						break;
					case FEATHER_NATURAL:
						naturalFeatherColours.add(colour);
						break;
					case FEATHER_DYE:
						dyeFeatherColours.add(colour);
						break;
					case FUR:
						naturalFurColours.add(colour);
						break;
					case GENERIC_COVERING:
						allCoveringColours.add(colour);
						allCoveringColoursWithClear.add(colour);
						break;
					case HAIR:
						naturalHairColours.add(colour);
						break;
					case HORN:
						hornColours.add(colour);
						break;
					case MAKEUP:
						allMakeupColours.add(colour);
						break;
					case SCALE:
						naturalScaleColours.add(colour);
						break;
					case SKIN:
						allSkinColours.add(colour);
						break;
					case SLIME_NATURAL:
						naturalSlimeColours.add(colour);
						break;
					case SLIME_DYE:
						dyeSlimeColours.add(colour);
						break;
						
					case IRIS_DYE:
						dyeIrisColours.add(colour);
						break;
					case IRIS_NATURAL:
						naturalIrisColours.add(colour);
						break;
					case IRIS_PREDATOR_NATURAL:
						naturalPredatorIrisColours.add(colour);
						break;
					case IRIS_PREDATOR_DYE:
						dyePredatorIrisColours.add(colour);
						break;
					case PUPIL_DYE:
						dyePupilColours.add(colour);
						break;
					case PUPIL_NATURAL:
						naturalPupilColours.add(colour);
						break;
					case SCLERA_NATURAL:
						naturalScleraColours.add(colour);
						break;
					case SCLERA_DYE:
						dyeScleraColours.add(colour);
						break;
				}
			}
		}
		
		// Hard-coded colours:
		
		Field[] fields = PresetColour.class.getFields();
		
		for(Field f : fields){
			if (Colour.class.isAssignableFrom(f.getType())) {
				
				Colour ct;
				try {
					ct = ((Colour) f.get(null));

					colourToIdMap.put(ct, f.getName());
					idToColourMap.put(f.getName(), ct);
					
					allPresetColours.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static List<String> getAllColourIds() {
		return new ArrayList<>(idToColourMap.keySet());
	}
	
	public static Colour getColourFromId(String id) {
		if(id.equals("HORN_DARK_BROWN") || id.equals("COVERING_DARK_BROWN")) {
			return PresetColour.COVERING_BROWN_DARK;
		}
		if(id.equals("CLOTHING_MULTICOLOURED")) {
			return PresetColour.CLOTHING_RED;
		}
		String modId = id.replaceAll("SLIME_", "COVERING_");
		modId = modId.replaceAll("HORN_", "COVERING_");
		modId = modId.replaceAll("ANTLER_", "COVERING_");

		modId = Util.getClosestStringMatchUnordered(modId, 1, idToColourMap.keySet());
		
		return idToColourMap.get(modId);
	}
	
	public static String getIdFromColour(Colour colour) {
		return colourToIdMap.get(colour);
	}
	
	public static List<Colour> getAllPresetColours() {
		return allPresetColours;
	}

	public static List<Colour> getAllPresetColours(String prefix) {
		return idToColourMap.entrySet()
							.parallelStream()
							.filter(e -> e.getKey().startsWith(prefix))
							.map(Map.Entry::getValue)
							.collect(Collectors.toList());
	}
	
}
