package com.lilithsthrone.game.settings;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

public class Options {
    /* Multi-Slider Options */
    public static final MultiSliderOption PREGNANCY_LACTATION = new MultiSliderOption(
            "PREGNANCY_LACTATION",
            PresetColour.BASE_YELLOW,
            "Average Pregnancy Lactation",
            "Breasts",
            "Udders",
            "Set the <b>average</b> increase in lactation that characters will gain as a result of each pregnancy. Actual lactation increase will be within " + Units.fluid(Main.getProperties().pregnancyLactationIncreaseVariance) + " of this value.",
            "%UNIT_VOLUME",
            0,
            20,
            50,
            "pregnancyLactationIncrease",
            "pregnancyUdderLactationIncrease"
    );

    /* SLIDER OPTIONS */
    public static final SliderOption PREGNANCY_DURATION = new SliderOption(
            "PREGNANCY_DURATION",
            PresetColour.BASE_PINK_DEEP,
            "Pregnancy duration",
            "This sets the maximum time it takes for a pregnancy to progress from conception to birth.",
            "%VALUE week$",
            1,
            40,
            1,
            "pregnancyDuration");

    public static final SliderOption FORCED_FETISH = new SliderOption(
            "FORCED_FETISH",
            PresetColour.FETISH,
            "Forced Fetishes",
            "This sets the amount of NPCs spawning with the '"+Fetish.FETISH_KINK_GIVING.getName(null)+"' fetish, which causes them to try and forcibly give you fetishes after beating you in combat.",
            "%VALUE%",
            0,
            10,
            10,
            "forcedFetishPercentage"
    );

    public static final SliderOption FORCED_TF = new SliderOption(
            "FORCED_TF",
            PresetColour.TRANSFORMATION_GENERIC,
            "Forced TF",
            "This sets the amount of NPCs spawning with the '"+Fetish.FETISH_TRANSFORMATION_GIVING.getName(null)+"' fetish, which causes them to forcibly transform you after beating you in combat.",
            "%VALUE%",
            0,
            20,
            5,
            "forcedTFPercentage");

    public static final SliderOption PENIS_SIZE_PREFERENCE = new SliderOption(
            "PENIS_SIZE_PREFERENCE",
            PresetColour.PENIS,
            "Penis Size Preference",
            "Affects randomly-generated NPCs' penis sizes (will not be reduced to below " + Units.size(8) + ").",
            "$+%UNIT_SIZE",
            -20,
            -20,
            20,
            1,
            "penisSizePreference"
    );

    public static final SliderOption TRAP_PENIS_SIZE_PREFERENCE = new SliderOption(
            "TRAP_PENIS_SIZE_PREFERENCE",
            PresetColour.BASE_PINK_LIGHT,
            Util.capitaliseSentence(Gender.N_P_TRAP.getName()) + " penis size",
            "The penis size of randomly-generated " + Gender.N_P_TRAP.getName() + "s. 100% represents an unaltered size. Testicle size and cum production will also be altered in proportion to this setting.",
            "%VALUE%",
            -90,
            -9,
            10,
            10,
            "trapPenisSizePreference"
    );

    /* TOGGLE OPTIONS */
    public static final ToggleOption AUTO_LOCALE = new ToggleOption(
            "AUTO_LOCALE",
            PresetColour.BASE_BLUE_LIGHT,
            "Automatic",
            "When enabled, the system locale is used. Otherwise, the following options are applied.",
            PropertyValue.autoLocale
    );

    public static final ToggleOption METRIC_SIZES = new ToggleOption(
            "METRIC_SIZES",
            PresetColour.BASE_BLUE_STEEL,
            "Metric sizes",
            "The game will use metres and centimetres instead of feet and inches.",
            PropertyValue.metricSizes
    );

    public static final ToggleOption METRIC_FLUIDS = new ToggleOption(
            "METRIC_FLUIDS",
            PresetColour.BASE_BLUE_STEEL,
            "Metric fluids",
            "The game will use litres and millilitres instead of gallons and ounces.",
            PropertyValue.metricFluids
    );

    public static final ToggleOption METRIC_WEIGHTS = new ToggleOption(
            "METRIC_WEIGHTS",
            PresetColour.BASE_BLUE_STEEL,
            "Metric weights",
            "The game will use kilograms and grams instead of pounds and ounces.",
            PropertyValue.metricWeights
    );

    public static final ToggleOption TWENTYFOUR_HOUR_TIME = new ToggleOption(
            "TWENTYFOUR_HOUR_TIME",
            PresetColour.BASE_LILAC_LIGHT,
            "24 hour time",
            "The time will be displayed as 24 hours instead of AM/PM.",
            PropertyValue.twentyFourHourTime
    );

    public static final ToggleOption INTERNATIONAL_DATE = new ToggleOption(
            "INTERNATIONAL_DATE",
            PresetColour.BASE_LILAC_LIGHT,
            "International date",
            "The abbreviated date will be displayed as day.month.year instead of month/day/year.",
            PropertyValue.internationalDate
    );

    public static final ToggleOption ARTWORK = new ToggleOption(
            "ARTWORK",
            PresetColour.BASE_BLUE_LIGHT,
            "Artwork",
            "Enables artwork to be displayed in characters' information screens.",
            PropertyValue.artwork
    );

    public static final ToggleOption THUMBNAIL = new ToggleOption(
            "THUMBNAIL",
            PresetColour.BASE_BLUE_STEEL,
            "Thumbnails",
            "Enables tooltips containing thumbnail images of the character.",
            PropertyValue.thumbnail
    );

    public static final ToggleOption SHARED_ENCYCLOPEDIA = new ToggleOption(
            "SHARED_ENCYCLOPEDIA",
            PresetColour.GENERIC_EXCELLENT,
            "Shared Encyclopedia",
            "When enabled, your character will use the shared Encyclopedia (whose entries are unlocked across any playthrough). If disabled, unlocked Encyclopedia entries are only shown if your current character has discovered them.",
            PropertyValue.sharedEncyclopedia
    );

    public static final ToggleOption WEATHER_INTERRUPTION = new ToggleOption(
            "WEATHER_INTERRUPTION",
            PresetColour.GENERIC_ARCANE,
            "Storm interruptions",
            "When enabled, arcane storms will interrupt dialogue to let you know that they've started.",
            PropertyValue.weatherInterruptions
    );

    public static final ToggleOption DIALOGUE_COPY = new ToggleOption(
            "DIALOGUE_COPY",
            PresetColour.BASE_BLUE_STEEL,
            "Automatic text copying",
            "When enabled, the current scene's text will automatically be copied to your system's clipboard every time a new scene is loaded. This option is so that you can easily paste the game's text into text readers without needing to select and copy the scene's text every time.",
            PropertyValue.automaticDialogueCopy
    );

    public static final ToggleOption SILLY = new ToggleOption(
            "SILLY",
            PresetColour.GENERIC_GOOD,
            "Silly mode",
            "This enables funny flavour text throughout the game.",
            PropertyValue.sillyMode
    );

    public static final ToggleOption ENCHANTMENT_LIMITS = new ToggleOption(
            "ENCHANTMENT_LIMITS",
            PresetColour.GENERIC_ARCANE,
            "Enchantment Capacity",
            "Toggle the 'enchantment capacity' mechanic, which restricts how many enchanted items you can wear. This is on by default, and you will potentially break the balance of the game's combat by turning it off.",
            PropertyValue.enchantmentLimits
    );

    public static final ToggleOption BAD_END = new ToggleOption(
            "BAD_END",
            PresetColour.GENERIC_TERRIBLE,
            "Bad Ends",
            "Toggle the ability to trigger 'bad ends', which effectively end the game for your character when encountered.<br/>[style.italicsMinorBad(Please note that bad ends involve non-con content, regardless of whether or not your non-con option is enabled.)]",
            PropertyValue.badEndContent
    );

    public static final ToggleOption LEVEL_DRAIN = new ToggleOption(
            "LEVEL_DRAIN",
            PresetColour.GENERIC_TERRIBLE,
            "Level Drain",
            "Toggle the use of the 'orgasmic level drain' perk by unique NPCs (such as some scenes with Amber), which causes them to drain your level for each orgasm you have in sex with them.",
            PropertyValue.levelDrain
    );

    public static final ToggleOption OPPORTUNISTIC_ATTACKERS = new ToggleOption(
            "OPPORTUNISTIC_ATTACKERS",
            PresetColour.BASE_CRIMSON,
            "Opportunistic attackers",
            "This makes random attacks more likely when you're high on lust, low on health, covered in fluids, exposed, or drunk.",
            PropertyValue.opportunisticAttackers
    );

    public static final ToggleOption OFFSPRING_ENCOUNTERS = new ToggleOption(
            "OFFSPRING_ENCOUNTERS",
            PresetColour.BASE_INDIGO,
            "Offspring Encounters",
            "This enables you to randomly encounter your offspring throught the world.<br/><i>This setting has no effect on the Offspring Map, nor on offspring who you've already met.</i>",
            PropertyValue.offspringEncounters
    );

    public static final ToggleOption SPITTING_ENABLED = new ToggleOption(
            "SPITTING_ENABLED",
            PresetColour.BASE_BLUE,
            "Rejecting TF potions",
            "Forced TF potions may be spat out if this is enabled.",
            PropertyValue.spittingEnabled
    );

    public static final ToggleOption COMPANION = new ToggleOption(
            "COMPANION",
            PresetColour.BASE_GREEN_LIGHT,
            "Companions",
            "Enable the ability to add slaves or friendly occupants as your companion.<br/>[style.boldBad(Warning:)] This is an experimental feature, and support for companions was dropped in v0.3.9, so there will be no special dialogue or actions involving your companions outside of Dominion.",
            PropertyValue.companionContent
    );

    public static final ToggleOption NON_CON = new ToggleOption(
            "NON_CON",
            PresetColour.BASE_CRIMSON,
            "Non-consent",
            "This enables the 'resist' pace in sex scenes, which contains some more extreme non-consensual descriptions, as well as dialogue references and actions related to this content.<br/>[style.italicsMinorBad(Please note that bad ends involve non-con content, regardless of whether or not this option is enabled.)]",
            PropertyValue.nonConContent
    );

    public static final ToggleOption SADISTIC_SEX = new ToggleOption(
            "SADISTIC_SEX",
            PresetColour.BASE_RED,
            "Sadistic sex",
            "This unlocks 'sadistic' sex actions, such as choking, slapping, and spitting on partners in sex.",
            PropertyValue.sadisticSexContent
    );

    public static final ToggleOption LIPSTICK_MARKING = new ToggleOption(
            "LIPSTICK_MARKING",
            PresetColour.BASE_RED_DARK,
            "Lipstick marking",
            "This enables lipstick marking of bodyparts via kisses during sex.",
            PropertyValue.lipstickMarkingContent
    );

    public static final ToggleOption VOLUNTARY_NTR = new ToggleOption(
            "VOLUNTARY_NTR",
            PresetColour.GENERIC_MINOR_BAD,
            "Voluntary NTR",
            "When enabled, you will get the option to offer certain enemies sex with your companions as a way to avoid combat.",
            PropertyValue.voluntaryNTR
    );

    public static final ToggleOption INVOLUNTARY_NTR = new ToggleOption(
            "INVOLUNTARY_NTR",
            PresetColour.GENERIC_BAD,
            "Involuntary NTR",
            "When enabled, enemies might choose to only have sex with your companion after beating your party in combat. When disabled, all post-combat-loss sex scenes will involve you.",
            PropertyValue.involuntaryNTR
    );

    public static final ToggleOption INCEST = new ToggleOption(
            "INCEST",
            PresetColour.BASE_ROSE,
            "Incest",
            "This will enable sexual actions between characters who are related to one another.",
            PropertyValue.incestContent
    );

    public static final ToggleOption LACTATION = new ToggleOption(
            "LACTATION",
            PresetColour.BASE_YELLOW_LIGHT,
            "Lactation",
            "This enables lactation content.",
            PropertyValue.lactationContent
    );

    public static final ToggleOption SEXUAL_UDDERS = new ToggleOption(
            "SEXUAL_UDDERS",
            PresetColour.BASE_ORANGE_LIGHT,
            "Crotch-boob & udder content",
            "This enables crotch-boob & udder-related sex actions and allows crotch-boob & udder transformations to be inflicted upon the player.",
            PropertyValue.udderContent
    );

    public static final ToggleOption URETHRAL = new ToggleOption(
            "URETHRAL",
            PresetColour.BASE_PINK_DEEP,
            "Urethral content",
            "This enables urethral transformations and penetrations.",
            PropertyValue.urethralContent
    );

    public static final ToggleOption NIPPLE_PEN = new ToggleOption(
            "NIPPLE_PEN",
            PresetColour.BASE_PINK_DEEP,
            "Nipple penetrations",
            "This enables nipple-penetration transformations and sex actions.",
            PropertyValue.nipplePenContent
    );

    public static final ToggleOption ANAL = new ToggleOption(
            "ANAL",
            PresetColour.BASE_ORANGE,
            "Anal content",
            "When disabled, removes all anal-related actions from being available during sex.",
            PropertyValue.analContent
    );

    public static final ToggleOption GAPE = new ToggleOption(
            "GAPE",
            PresetColour.BASE_PINK_DEEP,
            "Gape content",
            "When disabled, changes descriptions of gaping orifices to simply be 'loose', and also hides any special gape-related content.",
            PropertyValue.gapeContent
    );

    public static final ToggleOption PENETRATION_LIMITATION = new ToggleOption(
            "PENETRATION_LIMITATION",
            PresetColour.BASE_PINK_DEEP,
            "Penetrative size-difference",
            "When enabled, orifices will have a limited depth to them, meaning that penetrative objects (penises and tails) can be too long to fit all the way inside.",
            PropertyValue.penetrationLimitations
    );

    public static final ToggleOption PENETRATION_LIMITATION_DYNAMIC = new ToggleOption(
            "PENETRATION_LIMITATION_DYNAMIC",
            PresetColour.BASE_PINK_DEEP,
            "Elasticity depth effects",
            "When enabled, if an orifice has an elasticity of at least 'limber', the maximum 'uncomfortable depth' value will be increased, with greater elasticity values increasing it further. (Note: Only applies when 'Penetrative size-difference' is also turned on.)",
            PropertyValue.elasticityAffectDepth
    );

    public static final ToggleOption FOOT = new ToggleOption(
            "FOOT",
            PresetColour.BASE_TAN,
            "Foot content",
            "When disabled, removes all foot-related actions from being available during sex.",
            PropertyValue.footContent
    );

    public static final ToggleOption ARMPIT = new ToggleOption(
            "ARMPIT",
            PresetColour.BASE_PINK_LIGHT,
            "Armpit content",
            "When disabled, removes all armpit-related actions from being available during sex.",
            PropertyValue.armpitContent
    );

    public static final ToggleOption MUSK = new ToggleOption(
            "MUSK",
            PresetColour.BASE_YELLOW_LIGHT,
            "Musk content",
            "When disabled, some scenes will either have reduced musk content or be omitted entirely, and the 'marked by musk' status effect will be disabled.",
            PropertyValue.muskContent
    );

    public static final ToggleOption FURRY_TAIL_PENETRATION = new ToggleOption(
            "FURRY_TAIL_PENETRATION",
            PresetColour.BASE_MAGENTA,
            "Furry tail penetrations",
            "This marks all tail types as being suitable for penetration, thereby enabling furry tails to engage in penetrative actions in sex.",
            PropertyValue.furryTailPenetrationContent
    );

    public static final ToggleOption INFLATION_CONTENT = new ToggleOption(
            "INFLATION_CONTENT",
            PresetColour.CUM,
            "Cum inflation",
            "This enables cum inflation mechanics.",
            PropertyValue.inflationContent
    );

    public static final ToggleOption AUTO_SEX_CLOTHING_MANAGEMENT = new ToggleOption(
            "AUTO_SEX_CLOTHING_MANAGEMENT",
            PresetColour.BASE_BLUE_STEEL,
            "Post-sex clothing replacement",
            "Enables equipped clothing to be automatically pulled back into their pre-sex states after sex scenes.",
            PropertyValue.autoSexClothingManagement
    );

    public static final ToggleOption AUTO_SEX_CLOTHING_STRIP = new ToggleOption(
            "AUTO_SEX_CLOTHING_STRIP",
            PresetColour.BASE_PINK_LIGHT,
            "Automatic stripping",
            "When enabled, all non-spectating characters which you are allowed to strip during sex (including yourself) will start sex naked.",
            PropertyValue.autoSexStrip
    );

    public static final ToggleOption RAPE_PLAY_BY_DEFAULT = new ToggleOption(
            "RAPE_PLAY_BY_DEFAULT",
            PresetColour.BASE_CRIMSON,
            "Rape-play allowed by default",
            "When enabled, submissive characters in sex who have the 'unwilling fuck-toy' fetish will be able to engage in rape-play without first being given permission to do so.",
            PropertyValue.rapePlayAtSexStart
    );

    public static final ToggleOption AGE = new ToggleOption(
            "AGE",
            PresetColour.AGE_TWENTIES,
            "Age",
            "This enables descriptions of the age that characters appear to be.",
            PropertyValue.ageContent
    );

    public static final ToggleOption FERAL = new ToggleOption(
            "FERAL",
            PresetColour.BASE_TAN,
            "Feral",
            "This enables feral content, which contains sexual and non-sexual interactions with sapient characters who have fully-animal bodies.",
            PropertyValue.feralContent
    );

    public static final ToggleOption CUM_REGENERATION = new ToggleOption(
            "CUM_REGENERATION",
            PresetColour.CUM,
            "Cum Regeneration",
            "This enables cum regeneration related content, such as decreasing quantity for multiple orgasms in one session and the full balls status effect.<br>When disabled, balls will always be treated as full, but without any negative effects.",
            PropertyValue.cumRegenerationContent
    );

    public static final ToggleOption FUTA_BALLS = new ToggleOption(
            "FUTA_BALLS",
            PresetColour.BASE_PINK,
            "Futanari Testicles",
            "When enabled, futanari NPCs will be able to have external testicles. When disabled, they are locked to always being internal.",
            PropertyValue.futanariTesticles
    );

    public static final ToggleOption CLOACA = new ToggleOption(
            "CLOACA",
            PresetColour.BASE_PINK_LIGHT,
            "Bipedal Cloacas",
            "When enabled, certain bipedal races (such as harpies and alligator-morphs) will have cloacas. When disabled, all bipeds with cloacas will be treated as having a regular genitalia configuration. Some special races, such as lamia, always have cloacas, and are not affected by this.",
            PropertyValue.bipedalCloaca
    );

    public static final ToggleOption VESTIGIAL_MULTI_BREAST = new ToggleOption(
            "VESTIGIAL_MULTI_BREAST",
            PresetColour.BASE_PURPLE_LIGHT,
            "Vestigial Multi-breasts",
            "When enabled, characters who have multiple rows of breasts will have the rows beneath their top one described as being vestigial in size. When disabled, breast rows will be described as being one cup size smaller than the one above them.",
            PropertyValue.vestigialMultiBreasts
    );

    public static final ToggleOption HAIR_FACIAL = new ToggleOption(
            "HAIR_FACIAL",
            PresetColour.BASE_LILAC_LIGHT,
            "Facial hair",
            "This enables facial hair descriptions and content.",
            PropertyValue.facialHairContent
    );

    public static final ToggleOption HAIR_PUBIC = new ToggleOption(
            "HAIR_PUBIC",
            PresetColour.BASE_LILAC,
            "Pubic hair",
            "This enables pubic hair descriptions and content.",
            PropertyValue.pubicHairContent
    );

    public static final ToggleOption HAIR_BODY = new ToggleOption(
            "HAIR_BODY",
            PresetColour.BASE_PURPLE,
            "Underarm hair",
            "This enables underarm hair descriptions and content.",
            PropertyValue.bodyHairContent
    );

    public static final ToggleOption HAIR_ASS = new ToggleOption(
            "HAIR_ASS",
            PresetColour.BASE_PURPLE_DARK,
            "Ass hair",
            "This enables ass hair descriptions and content.",
            PropertyValue.assHairContent
    );

    public static final ToggleOption FEMININE_BEARD = new ToggleOption(
            "FEMININE_BEARD",
            PresetColour.BASE_BLUE_STEEL,
            "Feminine beards",
            "This enables feminine characters to grow beards.",
            PropertyValue.feminineBeardsContent
    );

    public static final ToggleOption FURRY_HAIR = new ToggleOption(
            "FURRY_HAIR",
            PresetColour.CLOTHING_DESATURATED_BROWN,
            "Furry hair",
            "Toggles whether or not characters with a furry head type will spawn with human-like hair on their heads.",
            PropertyValue.furryHairContent
    );

    public static final ToggleOption SCALY_HAIR = new ToggleOption(
            "SCALY_HAIR",
            PresetColour.BASE_GREEN_DARK,
            "Scaly Hair",
            "Toggles whether or not characters with a reptilian or amphibious head type will spawn with human-like hair on their heads.",
            PropertyValue.scalyHairContent
    );
}