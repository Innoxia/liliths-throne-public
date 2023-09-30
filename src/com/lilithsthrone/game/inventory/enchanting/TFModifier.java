package com.lilithsthrone.game.inventory.enchanting;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.7
 * @version 0.3.5.5
 * @author Innoxia
 */
public enum TFModifier {
	
	NONE("Empty",
		"No modifier",
		"nullification",
		"modifier_circle",
		PresetColour.TEXT_GREY,
		Rarity.COMMON),
	
	// Pos/Neg
	
	ARCANE_BOOST("arcane booster",
			"Applies a more powerful effect in relation to the primary essence.",
			"boosted",
			"modifier_circle_arcane",
			PresetColour.GENERIC_ARCANE,
			Rarity.COMMON),

	REMOVAL("removal",
			"Applies an effect such that the primary modifier's body part is removed.",
			"vanishing",
			"modifier_circle_removal",
			PresetColour.GENERIC_TERRIBLE,
			Rarity.UNCOMMON),
	
	// Misc:
	
	ORIENTATION_GYNEPHILIC("gynephilia",
			"Applies an effect related to changing orientation to gynephilic.",
			"gynephilia",
			"modifier_circle_orientation_gynephilic",
			PresetColour.FEMININE_PLUS,
			Rarity.LEGENDARY),
	
	ORIENTATION_AMBIPHILIC("ambiphilia",
			"Applies an effect related to changing orientation to ambiphilic.",
			"ambiphilia",
			"modifier_circle_orientation_ambiphilic",
			PresetColour.ANDROGYNOUS,
			Rarity.LEGENDARY),
	
	ORIENTATION_ANDROPHILIC("androphilia",
			"Applies an effect related to changing orientation to androphilic.",
			"androphilia",
			"modifier_circle_orientation_androphilic",
			PresetColour.MASCULINE_PLUS,
			Rarity.LEGENDARY),
	
	PERSONALITY_TRAIT_SPEECH_LISP("lisp",
			"Applies an effect related to a person speaking with a lisp.",
			"lisp",
			"modifier_circle_speech",
			PresetColour.BASE_PURPLE_LIGHT,
			Rarity.LEGENDARY),

	PERSONALITY_TRAIT_SPEECH_STUTTER("stutter",
			"Applies an effect related to a person speaking with a stutter.",
			"stutter",
			"modifier_circle_speech",
			PresetColour.BASE_PINK_SALMON,
			Rarity.LEGENDARY),

	PERSONALITY_TRAIT_SPEECH_SLOVENLY("slovenly speech",
			"Applies an effect related to a person speaking in a slovenly manner.",
			"slovenly",
			"modifier_circle_speech",
			PresetColour.BASE_BROWN,
			Rarity.LEGENDARY),
	
	// Attributes:

	HEALTH_MAXIMUM(AttributeCategory.STRENGTH,
			Attribute.HEALTH_MAXIMUM,
			"Applies an effect related to the primary attribute 'Maximum Health'.",
			"modifier_circle_strength",//"modifier_circle_health",
			Rarity.EPIC),

	MANA_MAXIMUM(AttributeCategory.INTELLIGENCE,
			Attribute.MANA_MAXIMUM,
			"Applies an effect related to the primary attribute 'Maximum Aura'.",
			"modifier_circle_mana",
			Rarity.EPIC),
	
	STRENGTH(AttributeCategory.STRENGTH,
			Attribute.MAJOR_PHYSIQUE,
			"Applies an effect related to the primary attribute 'Physique'.",
			"modifier_circle_strength",
			Rarity.LEGENDARY),
	
	INTELLIGENCE(AttributeCategory.INTELLIGENCE,
			Attribute.MAJOR_ARCANE,
			"Applies an effect related to the primary attribute 'Arcane'.",
			"modifier_circle_intelligence",
			Rarity.LEGENDARY),
	
	CORRUPTION(AttributeCategory.CORRUPTION,
			Attribute.MAJOR_CORRUPTION,
			"Applies an effect related to the primary attribute 'Corruption'.",
			"modifier_circle_corruption",
			Rarity.LEGENDARY),

	/** This TFModifier is a special case, as it is not added to the available clothing TF lists.
	 * It is simply defined so that modded clothing can add this as a secondary TFModifier (to the primary TFModifier 'CLOTHING_MAJOR_ATTRIBUTE') to increase enchantment capacity of the wearer. */
	ENCHANTMENT_LIMIT(AttributeCategory.CORRUPTION,
			Attribute.ENCHANTMENT_LIMIT,
			"Applies an effect related to the secondary attribute 'Enchantment Capacity'.",
			"modifier_circle_corruption",
			Rarity.LEGENDARY),
	
	FERTILITY(AttributeCategory.CORRUPTION,
			Attribute.FERTILITY,
			"Applies an effect related to the secondary attribute 'Fertility'.",
			"modifier_circle_fertility",
			Rarity.RARE),

	VIRILITY(AttributeCategory.CORRUPTION,
			Attribute.VIRILITY,
			"Applies an effect related to the secondary attribute 'Virility'.",
			"modifier_circle_virility",
			Rarity.RARE),
	
	SPELL_COST_MODIFIER(AttributeCategory.INTELLIGENCE,
			Attribute.SPELL_COST_MODIFIER,
			"Applies an effect related to the secondary attribute '"+Attribute.SPELL_COST_MODIFIER.getName()+"'.",
			"modifier_circle_spell_efficiency",
			Rarity.RARE),
	
	
	CRITICAL_DAMAGE(AttributeCategory.STRENGTH,
			Attribute.CRITICAL_DAMAGE,
			"Applies an effect related to the secondary attribute 'Critical power'.",
			"modifier_circle_critical_damage",
			Rarity.RARE),
	
	
	DAMAGE_LUST(AttributeCategory.CORRUPTION,
			Attribute.DAMAGE_LUST,
			"Applies an effect related to the secondary attribute 'Seduction damage'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_SPELLS(AttributeCategory.INTELLIGENCE,
			Attribute.DAMAGE_SPELLS,
			"Applies an effect related to the secondary attribute 'Spell damage'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_UNARMED(AttributeCategory.STRENGTH,
			Attribute.DAMAGE_UNARMED,
			"Applies an effect related to the secondary attribute 'Unarmed damage'.",
			"modifier_circle_damage_unarmed",
			Rarity.RARE),

	DAMAGE_MELEE_WEAPON(AttributeCategory.STRENGTH,
			Attribute.DAMAGE_MELEE_WEAPON,
			"Applies an effect related to the secondary attribute 'Melee weapon damage'.",
			"modifier_circle_damage_melee",
			Rarity.RARE),

	DAMAGE_RANGED_WEAPON(AttributeCategory.STRENGTH,
			Attribute.DAMAGE_RANGED_WEAPON,
			"Applies an effect related to the secondary attribute 'Ranged weapon damage'.",
			"modifier_circle_damage_ranged",
			Rarity.RARE),
	
	DAMAGE_PHYSICAL(AttributeCategory.STRENGTH,
			Attribute.DAMAGE_PHYSICAL,
			"Applies an effect related to the secondary attribute 'Physical damage'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_FIRE(AttributeCategory.INTELLIGENCE,
			Attribute.DAMAGE_FIRE,
			"Applies an effect related to the secondary attribute 'Fire damage'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_ICE(AttributeCategory.INTELLIGENCE,
			Attribute.DAMAGE_ICE,
			"Applies an effect related to the secondary attribute 'Ice damage'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_POISON(AttributeCategory.INTELLIGENCE,
			Attribute.DAMAGE_POISON,
			"Applies an effect related to the secondary attribute 'Poison damage'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	/**
	 * Utility value for initialising a weapon to have attribute bonuses related to its damage type.
	 */
	DAMAGE_WEAPON(AttributeCategory.STRENGTH,
			Attribute.DAMAGE_PHYSICAL,
			"Applies a damage effect related to the weapon's damage type.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	
	RESISTANCE_LUST(AttributeCategory.CORRUPTION,
			Attribute.RESISTANCE_LUST,
			"Applies an effect related to the secondary attribute 'Seduction shielding'.",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	RESISTANCE_PHYSICAL(AttributeCategory.STRENGTH,
			Attribute.RESISTANCE_PHYSICAL,
			"Applies an effect related to the secondary attribute 'Physical shielding'.",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	RESISTANCE_FIRE(AttributeCategory.INTELLIGENCE,
			Attribute.RESISTANCE_FIRE,
			"Applies an effect related to the secondary attribute 'Fire shielding'.",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	RESISTANCE_ICE(AttributeCategory.INTELLIGENCE,
			Attribute.RESISTANCE_ICE,
			"Applies an effect related to the secondary attribute 'Cold shielding'.",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	RESISTANCE_POISON(AttributeCategory.INTELLIGENCE,
			Attribute.RESISTANCE_POISON,
			"Applies an effect related to the secondary attribute 'Poison shielding'.",
			"modifier_circle_resistance",
			Rarity.RARE),

	/**
	 * Utility value for initialising a weapon to have attribute bonuses related to its damage type.
	 */
	RESISTANCE_WEAPON(AttributeCategory.STRENGTH,
			Attribute.RESISTANCE_PHYSICAL,
			"Applies a shielding effect related to the weapon's damage type.",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	
	// Clothing parts:
	
	CLOTHING_MAJOR_ATTRIBUTE("core attribute",
			"Applies a modifier to a core attribute.",
			"attribute",
			"modifier_circle_attribute_major",
			PresetColour.GENERIC_ATTRIBUTE,
			Rarity.LEGENDARY),
	
	CLOTHING_ATTRIBUTE("attribute",
			"Applies a modifier to an attribute.",
			"attribute",
			"modifier_circle_attribute",
			PresetColour.GENERIC_ATTRIBUTE,
			Rarity.UNCOMMON),

	CLOTHING_SPECIAL("special effects",
			"Applies a special effect.",
			"special",
			"modifier_circle_special",
			PresetColour.BASE_TEAL,
			Rarity.LEGENDARY),

	CLOTHING_ENSLAVEMENT("enslavement",
			"Makes this piece of clothing enslave the wearer.",
			"enslavement",
			"modifier_circle_enslavement",
			PresetColour.BASE_PURPLE,
			Rarity.LEGENDARY),
	
	CLOTHING_SEALING("sealing",
			"Makes this piece of clothing seal itself onto the wearer.",
			"sealing",
			"modifier_circle_sealing",
			PresetColour.SEALED,
			Rarity.LEGENDARY),
	
	//CLOTHING_ANTI_SELF_TRANSFORMATION
	CLOTHING_SERVITUDE("servitude",
			"Makes the wearer unable to self-transform or unseal clothing.",
			"servitude",
			"modifier_circle_servitude",
			PresetColour.BASE_PINK_LIGHT,
			Rarity.LEGENDARY),

	CLOTHING_CONDOM("condom strength",
			"Provides underlying strength to the condom.",
			"condom strength",
			"modifier_circle_resistance",
			PresetColour.BASE_GREEN,
			Rarity.COMMON),

	CLOTHING_VIBRATION("vibration",
			"Makes this piece of clothing vibrate when worn.",
			"vibration",
			"modifier_circle_vibration",
			PresetColour.BASE_PINK,
			Rarity.EPIC),

	CLOTHING_ORGASM_PREVENTION("orgasm prevention",
			"Makes the wearer unable to orgasm.",
			"orgasm prevention",
			"modifier_circle_orgasm_prevention",
			PresetColour.BASE_CRIMSON,
			Rarity.EPIC),
	
	// Racial parts:

	TF_MATERIAL_FLESH("flesh",
			"Turns a person's body material to flesh.",
			"flesh",
			"modifier_circle_tf_material_flesh",
			PresetColour.BASE_CRIMSON,
			Rarity.LEGENDARY),
	
	TF_ANTENNA("antennae",
			"Applies a transformative effect to your antennae.",
			"antennae",
			"modifier_circle_tf_antenna",
			PresetColour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_ARMS("arms",
			"Applies a transformative effect to your arms.",
			"arms",
			"modifier_circle_tf_arm",
			PresetColour.TRANSFORMATION_LESSER,
			Rarity.RARE),
	
	TF_ASS("ass",
			"Applies a transformative effect to your ass.",
			"asses",
			"modifier_circle_tf_ass",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_BREASTS("breasts",
			"Applies a transformative effect to your breasts.",
			"breasts",
			"modifier_circle_tf_breast",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_BREASTS_CROTCH("crotch-boobs",
			"Applies a transformative effect to your crotch-boobs/udders.",
			"crotch-boobs",
			"modifier_circle_tf_breast_crotch",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_CORE("core",
			"Applies a transformative effect to your body.",
			"core",
			"modifier_circle_tf_core",
			PresetColour.TRANSFORMATION_GREATER,
			Rarity.RARE),
	
	TF_EARS("ears",
			"Applies a transformative effect to your ears.",
			"ears",
			"modifier_circle_tf_ear",
			PresetColour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_EYES("eyes",
			"Applies a transformative effect to your eyes.",
			"eyes",
			"modifier_circle_tf_eye",
			PresetColour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_FACE("face",
			"Applies a transformative effect to your face.",
			"faces",
			"modifier_circle_tf_face",
			PresetColour.TRANSFORMATION_GREATER,
			Rarity.EPIC),
	
	TF_HAIR("hair",
			"Applies a transformative effect to your hair.",
			"hair",
			"modifier_circle_tf_hair",
			PresetColour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_HORNS("horns",
			"Applies a transformative effect to your horns.",
			"horns",
			"modifier_circle_tf_horn",
			PresetColour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_LEGS("legs",
			"Applies a transformative effect to your legs.",
			"legs",
			"modifier_circle_tf_leg",
			PresetColour.TRANSFORMATION_LESSER,
			Rarity.RARE),
	
	TF_PENIS("penis",
			"Applies a transformative effect to your penis.",
			"cocks",
			"modifier_circle_tf_penis",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_SKIN("torso",
			"Applies a transformative effect to your torso.",
			"torso",
			"modifier_circle_tf_skin",
			PresetColour.TRANSFORMATION_GREATER,
			Rarity.EPIC),
	
	TF_TAIL("tail",
			"Applies a transformative effect to your tail.",
			"tails",
			"modifier_circle_tf_tail",
			PresetColour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),

	TF_TENTACLE("tentacle",
			"Applies a transformative effect to your tentacles.",
			"tentacles",
			"modifier_circle_tf_tentacle",
			PresetColour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_VAGINA("vagina",
			"Applies a transformative effect to your vagina.",
			"pussies",
			"modifier_circle_tf_vagina",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_WINGS("wings",
			"Applies a transformative effect to your wings.",
			"wings",
			"modifier_circle_tf_wing",
			PresetColour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_MILK("milk",
			"Applies a transformative effect to your milk.",
			"milk",
			"modifier_circle_tf_milk",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_MILK_CROTCH("udder-milk",
			"Applies a transformative effect to your udder-milk.",
			"udder-milk",
			"modifier_circle_tf_milk_crotch",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_CUM("cum",
			"Applies a transformative effect to your cum.",
			"cum",
			"modifier_circle_tf_cum",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_GIRLCUM("girlcum",
			"Applies a transformative effect to your girlcum.",
			"girlcum",
			"modifier_circle_tf_girlcum",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	
	// Body part modifiers:
	
	TF_TYPE_1("transformative I",
			"Transforms the related body part into that of a different race.",
			"transformative",
			"modifier_circle_tf_1",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_2("transformative II",
			"Transforms the related body part into that of a different race.",
			"transformative",
			"modifier_circle_tf_2",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_3("transformative III",
			"Transforms the related body part into that of a different race.",
			"transformative",
			"modifier_circle_tf_3",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_4("transformative IV",
			"Transforms the related body part into that of a different race.",
			"transformative",
			"modifier_circle_tf_4",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_5("transformative V",
			"Transforms the related body part into that of a different race.",
			"transformative",
			"modifier_circle_tf_5",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),

	TF_TYPE_6("transformative VI",
			"Transforms the related body part into that of a different race.",
			"transformative",
			"modifier_circle_tf_6",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_7("transformative VII",
			"Transforms the related body part into that of a different race.",
			"transformative",
			"modifier_circle_tf_7",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_8("transformative VIII",
			"Transforms the related body part into that of a different race.",
			"transformative",
			"modifier_circle_tf_8",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_9("transformative IX",
			"Transforms the related body part into that of a different race.",
			"transformative",
			"modifier_circle_tf_9",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_10("transformative X",
			"Transforms the related body part into that of a different race.",
			"transformative",
			"modifier_circle_tf_10",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_MOD_FEMININITY("femininity",
			"Applies an effect to change the user's femininity.",
			"feminine",
			"modifier_circle_femininity",
			PresetColour.ANDROGYNOUS,
			Rarity.COMMON),
	
	TF_MOD_COUNT("count",
			"Applies an effect related to adding or removing extra body parts.",
			"counting",
			"modifier_circle_count",
			PresetColour.BASE_AQUA,
			Rarity.COMMON),
	
	TF_MOD_COUNT_SECONDARY("secondary count",
			"Applies an effect related to adding or removing extra body parts.",
			"counting",
			"modifier_circle_count_secondary",
			PresetColour.BASE_TEAL,
			Rarity.COMMON),
	
	TF_MOD_BODY_HAIR("hairiness",
			"Applies an effect related to adding or removing body hair.",
			"hairy",
			"modifier_circle_bodyHair",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_FOOT_STRUCTURE_PLANTIGRADE("plantigrade feet",
			"Applies an effect to give the user a plantigrade foot structure.",
			"plantigrade feet",
			"modifier_circle_tf_footStructure_plantigrade",
			PresetColour.BASE_BLUE_LIGHT,
			Rarity.LEGENDARY),
	
	TF_MOD_FOOT_STRUCTURE_DIGITIGRADE("digitigrade feet",
			"Applies an effect to give the user a digitigrade foot structure.",
			"digitigrade feet",
			"modifier_circle_tf_footStructure_digitigrade",
			PresetColour.BASE_BROWN_DARK,
			Rarity.LEGENDARY),
	
	TF_MOD_FOOT_STRUCTURE_UNGULIGRADE("unguligrade feet",
			"Applies an effect to give the user an unguligrade foot structure.",
			"unguligrade feet",
			"modifier_circle_tf_footStructure_unguligrade",
			PresetColour.BASE_TAN,
			Rarity.LEGENDARY),
	
	TF_MOD_LEG_CONFIG_BIPEDAL("bidepal body",
			"Applies an effect to give the user a bipedal body.",
			"bipedal body",
			"modifier_circle_tf_legConfig_bipedal",
			PresetColour.BASE_BLUE_LIGHT,
			Rarity.LEGENDARY),
	
	TF_MOD_LEG_CONFIG_TAUR("taur body",
			"Applies an effect to give the user a taur body.",
			"taur body",
			"modifier_circle_tf_legConfig_taur",
			PresetColour.BASE_TAN,
			Rarity.LEGENDARY),
	
	TF_MOD_LEG_CONFIG_TAIL_LONG("long-tailed body",
			"Applies an effect to give the user a long-tailed body.",
			"long-tailed body",
			"modifier_circle_tf_legConfig_tail_long",
			PresetColour.BASE_GREEN,
			Rarity.LEGENDARY),
	
	TF_MOD_LEG_CONFIG_TAIL("tailed body",
			"Applies an effect to give the user a tailed body.",
			"tailed body",
			"modifier_circle_tf_legConfig_tail",
			PresetColour.BASE_AQUA,
			Rarity.LEGENDARY),
	
	TF_MOD_LEG_CONFIG_ARACHNID("arachnid body",
			"Applies an effect to give the user an arachnid body.",
			"arachnid body",
			"modifier_circle_tf_legConfig_arachnid",
			PresetColour.BASE_BLACK,
			Rarity.LEGENDARY),
	
	TF_MOD_LEG_CONFIG_CEPHALOPOD("cephalopod body",
			"Applies an effect to give the user a cephalopod body.",
			"cephalopod body",
			"modifier_circle_tf_legConfig_cephalopod",
			PresetColour.BASE_RED,
			Rarity.LEGENDARY),

	TF_MOD_LEG_CONFIG_AVIAN("avian body",
			"Applies an effect to give the user an avian body.",
			"avian body",
			"modifier_circle_tf_legConfig_avian",
			PresetColour.BASE_YELLOW,
			Rarity.LEGENDARY),
	
	TF_MOD_LEG_CONFIG_WINGED_BIPED("winged bipedal body",
			"Applies an effect to give the user a winged bipedal body.",
			"winged bipedal body",
			"modifier_circle_tf_legConfig_avian",
			PresetColour.BASE_YELLOW,
			Rarity.LEGENDARY),
	
	TF_MOD_INTERNAL("internal",
			"Applies an effect related to making a body part internal.",
			"internal",
			"modifier_circle_internal",
			PresetColour.BASE_BLUE_STEEL,
			Rarity.COMMON),
	
	TF_MOD_SIZE("size",
			"Applies an effect related to the change in size of a certain body part.",
			"resizing",
			"modifier_circle_size",
			PresetColour.BASE_LILAC,
			Rarity.COMMON),
	
	TF_MOD_SIZE_SECONDARY("secondary size",
			"Applies an effect related to the change in size of a different body part.",
			"resizing",
			"modifier_circle_size_secondary",
			PresetColour.BASE_LILAC_LIGHT,
			Rarity.COMMON),

	TF_MOD_SIZE_TERTIARY("tertiary size",
			"Applies an effect related to the change in size of yet another body part.",
			"resizing",
			"modifier_circle_size_tertiary",
			PresetColour.BASE_ROSE,
			Rarity.COMMON),
	

	TF_MOD_REGENERATION("regeneration",
			"Applies an effect related to the regeneration rate of fluids.",
			"refilling",
			"modifier_circle_regeneration",
			PresetColour.BASE_GREEN_LIGHT,
			Rarity.COMMON),
	
	// Orifices:
	
	TF_MOD_CAPACITY("capacity",
			"Applies an effect related to changing an orifice's capacity.",
			"capacious",
			"modifier_circle_capacity",
			PresetColour.BASE_PINK_LIGHT,
			Rarity.COMMON),

	TF_MOD_CAPACITY_2("capacity II",
			"Applies an effect related to changing an orifice's capacity.",
			"capacious",
			"modifier_circle_capacity",
			PresetColour.BASE_PINK,
			Rarity.COMMON),
	
	TF_MOD_WETNESS("wetness",
			"Applies an effect related to changing the wetness of an orifice, or the fluid production of a body part.",
			"wet",
			"modifier_circle_wetness",
			PresetColour.BASE_BLUE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_DEPTH("depth",
			"Applies an effect related to changing the depth of an orifice.",
			"deep",
			"modifier_circle_orifice_deep",
			PresetColour.BASE_GREY,
			Rarity.COMMON),
	
	TF_MOD_DEPTH_2("depth II",
			"Applies an effect related to changing the depth of an orifice.",
			"deep",
			"modifier_circle_orifice_deep",
			PresetColour.BASE_BLACK,
			Rarity.COMMON),
	
	TF_MOD_ELASTICITY("elasticity",
			"Applies an effect related to changing the elasticity of an orifice.",
			"elastic",
			"modifier_circle_elasticity",
			PresetColour.BASE_AQUA,
			Rarity.COMMON),
	
	TF_MOD_ELASTICITY_2("elasticity II",
			"Applies an effect related to changing the elasticity of an orifice.",
			"elastic",
			"modifier_circle_elasticity",
			PresetColour.BASE_BLUE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_PLASTICITY("plasticity",
			"Applies an effect related to changing the plasticity of an orifice.",
			"plastic",
			"modifier_circle_plasticity",
			PresetColour.BASE_TEAL,
			Rarity.COMMON),
	
	TF_MOD_PLASTICITY_2("plasticity II",
			"Applies an effect related to changing the plasticity of an orifice.",
			"plastic",
			"modifier_circle_plasticity",
			PresetColour.BASE_BLUE,
			Rarity.COMMON),
	
	// modifiers:
	
	TF_MOD_ORIFICE_PUFFY("puffiness",
			"Applies an effect related to making an orifice puffy.",
			"puffy",
			"modifier_circle_orifice_puffy",
			PresetColour.BASE_BLUE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_RIBBED("ribbing",
			"Applies an effect related to making an orifice internally-ribbed.",
			"ribbed",
			"modifier_circle_orifice_ribbed",
			PresetColour.BASE_PINK_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_TENTACLED("tentacled",
			"Applies an effect related to making an orifice internally-tentacled.",
			"tentacled",
			"modifier_circle_orifice_tentacled",
			PresetColour.BASE_CRIMSON,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_MUSCLED("muscled",
			"Applies an effect related to making an orifice internally-muscled.",
			"muscled",
			"modifier_circle_orifice_muscled",
			PresetColour.BASE_MAGENTA,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_PUFFY_2("puffiness II",
			"Applies an effect related to making an orifice puffy.",
			"puffy",
			"modifier_circle_orifice_puffy",
			PresetColour.BASE_BLUE,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_RIBBED_2("ribbing II",
			"Applies an effect related to making an orifice internally-ribbed.",
			"ribbed",
			"modifier_circle_orifice_ribbed",
			PresetColour.BASE_PINK,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_TENTACLED_2("tentacled II",
			"Applies an effect related to making an orifice internally-tentacled.",
			"tentacled",
			"modifier_circle_orifice_tentacled",
			PresetColour.BASE_RED,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_MUSCLED_2("muscled II",
			"Applies an effect related to making an orifice internally-muscled.",
			"muscled",
			"modifier_circle_orifice_muscled",
			PresetColour.BASE_PURPLE,
			Rarity.COMMON),
	
	// eye shapes:
	
	TF_MOD_EYE_IRIS_CIRCLE("circular irises",
			"Applies an effect related to making irises shaped like normal circles.",
			"circular irises",
			"modifier_circle_eye_iris_normal",
			PresetColour.BASE_PURPLE,
			Rarity.COMMON),
	
	TF_MOD_EYE_IRIS_HORIZONTAL("horizontal irises",
			"Applies an effect related to making irises take a more horizontal shape.",
			"horizontal irises",
			"modifier_circle_eye_iris_horizontal",
			PresetColour.BASE_LILAC_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_EYE_IRIS_VERTICAL("vertical irises",
			"Applies an effect related to making irises take a more vertical shape.",
			"vertical irises",
			"modifier_circle_eye_iris_vertical",
			PresetColour.BASE_PURPLE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_EYE_IRIS_HEART("heart-shaped irises",
			"Applies an effect related to making irises shaped like hearts.",
			"heart-shaped irises",
			"modifier_circle_eye_iris_heart",
			PresetColour.BASE_PINK,
			Rarity.COMMON),
	
	TF_MOD_EYE_IRIS_STAR("star-shaped irises",
			"Applies an effect related to making irises shaped like stars.",
			"star-shaped irises",
			"modifier_circle_eye_iris_star",
			PresetColour.BASE_YELLOW,
			Rarity.COMMON),
	
	TF_MOD_EYE_PUPIL_CIRCLE("circular pupils",
			"Applies an effect related to making pupils shaped like normal circles.",
			"circular pupils",
			"modifier_circle_eye_iris_normal",
			PresetColour.BASE_BLACK,
			Rarity.COMMON),
	
	TF_MOD_EYE_PUPIL_HORIZONTAL("horizontal pupils",
			"Applies an effect related to making pupils take a more horizontal shape.",
			"horizontal pupils",
			"modifier_circle_eye_iris_horizontal",
			PresetColour.BASE_AQUA,
			Rarity.COMMON),
	
	TF_MOD_EYE_PUPIL_VERTICAL("vertical pupils",
			"Applies an effect related to making pupils take a more vertical shape.",
			"vertical pupils",
			"modifier_circle_eye_iris_vertical",
			PresetColour.BASE_TEAL,
			Rarity.COMMON),
	
	TF_MOD_EYE_PUPIL_HEART("heart-shaped pupils",
			"Applies an effect related to making pupils shaped like hearts.",
			"heart-shaped pupils",
			"modifier_circle_eye_iris_heart",
			PresetColour.BASE_BLUE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_EYE_PUPIL_STAR("star-shaped pupils",
			"Applies an effect related to making pupils shaped like stars.",
			"star-shaped pupils",
			"modifier_circle_eye_iris_star",
			PresetColour.BASE_BLUE,
			Rarity.COMMON),
	
	// breast shapes:
	
	TF_MOD_BREAST_SHAPE_UDDERS("udders",
			"Applies an effect related to turning crotch-boobs into udders.",
			"udders",
			"modifier_circle_breastShape_udders",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_BREAST_SHAPE_ROUND("round breasts",
			"Applies an effect related to making breasts take on a round shape.",
			"round",
			"modifier_circle_breastShape_round",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_BREAST_SHAPE_NARROW("narrow breasts",
			"Applies an effect related to making breasts take on a narrow shape.",
			"narrow",
			"modifier_circle_breastShape_narrow",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_BREAST_SHAPE_WIDE("wide breasts",
			"Applies an effect related to making breasts take on a wide shape.",
			"wide",
			"modifier_circle_breastShape_wide",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_BREAST_SHAPE_POINTY("pointy breasts",
			"Applies an effect related to making breasts take on a pointy shape.",
			"pointy",
			"modifier_circle_breastShape_pointy",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_BREAST_SHAPE_PERKY("perky breasts",
			"Applies an effect related to making breasts take on a perky shape.",
			"perky",
			"modifier_circle_breastShape_perky",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_BREAST_SHAPE_SIDESET("side-set breasts",
			"Applies an effect related to making breasts take on a side-set shape.",
			"side-set",
			"modifier_circle_breastShape_sideset",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	// nipple shapes:
	
	TF_MOD_NIPPLE_NORMAL("normal nipples",
			"Applies an effect related to making nipples look normal.",
			"normal nipples",
			"modifier_circle_nipple_normal",
			PresetColour.BASE_PURPLE_LIGHT,
			Rarity.COMMON),

	TF_MOD_NIPPLE_INVERTED("inverted nipples",
			"Applies an effect related to making nipples inverted.",
			"inverted nipples",
			"modifier_circle_nipple_normal",
			PresetColour.BASE_RED_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_NIPPLE_VAGINA("nipple cunts",
			"Applies an effect related to making nipples look like pussies.",
			"nipple cunts",
			"modifier_circle_nipple_vagina",
			PresetColour.BASE_PINK_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_NIPPLE_LIPS("lipples",
			"Applies an effect related to making nipples look like a pair of lips.",
			"lipples",
			"modifier_circle_nipple_lips",
			PresetColour.BASE_MAGENTA,
			Rarity.COMMON),
	
	// areolae shapes:
	
	TF_MOD_AREOLAE_CIRCLE("circular areolae",
			"Applies an effect related to making areolae shaped like normal circles.",
			"circular areolae",
			"modifier_circle_areolae_normal",
			PresetColour.BASE_PURPLE,
			Rarity.COMMON),
	
	TF_MOD_AREOLAE_HEART("heart-shaped areolae",
			"Applies an effect related to making areolae shaped like hearts.",
			"heart-shaped areolae",
			"modifier_circle_areolae_heart",
			PresetColour.BASE_PINK,
			Rarity.COMMON),
	
	TF_MOD_AREOLAE_STAR("star-shaped areolae",
			"Applies an effect related to making areolae shaped like stars.",
			"star-shaped areolae",
			"modifier_circle_areolae_star",
			PresetColour.BASE_YELLOW,
			Rarity.COMMON),
	
	
	// tongue modifiers:
	
	TF_MOD_TONGUE_RIBBED("ribbing",
			"Applies an effect related to adding ribbing to a tongue.",
			"ribbed",
			"modifier_circle_orifice_ribbed",
			PresetColour.BASE_PINK,
			Rarity.COMMON),
	
	TF_MOD_TONGUE_TENTACLED("tentacled",
			"Applies an effect related to adding tentacles to a tongue.",
			"tentacled",
			"modifier_circle_orifice_tentacled",
			PresetColour.BASE_PINK_SALMON,
			Rarity.COMMON),
	
	TF_MOD_TONGUE_BIFURCATED("bifurcated",
			"Applies an effect related to making a tongue bifurcated.",
			"bifurcated",
			"modifier_circle_tongue_bifurcated",
			PresetColour.BASE_CRIMSON,
			Rarity.COMMON),
	
	TF_MOD_TONGUE_WIDE("wide",
			"Applies an effect related to making a tongue wide.",
			"wide",
			"modifier_circle_tongue_wide",
			PresetColour.BASE_PURPLE,
			Rarity.COMMON),
	
	TF_MOD_TONGUE_FLAT("flat",
			"Applies an effect related to making a tongue flat.",
			"flat",
			"modifier_circle_tongue_flat",
			PresetColour.BASE_PURPLE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_TONGUE_STRONG("strong",
			"Applies an effect related to making a tongue strong.",
			"strong",
			"modifier_circle_orifice_muscled",
			PresetColour.BASE_MAGENTA,
			Rarity.COMMON),
	
	
	// penis & clit modifiers:
	
	TF_MOD_PENIS_SHEATHED("sheathing",
			"Applies an effect related to making a body part sheathed.",
			"sheathed",
			"modifier_circle_penis_sheathed",
			PresetColour.BASE_ORANGE,
			Rarity.COMMON),
	
	TF_MOD_PENIS_RIBBED("ribbing",
			"Applies an effect related to making a body part ribbed.",
			"ribbed",
			"modifier_circle_penis_ribbed",
			PresetColour.BASE_PINK,
			Rarity.COMMON),
	
	TF_MOD_PENIS_TENTACLED("tentacled",
			"Applies an effect related to making a body part tentacled.",
			"tentacled",
			"modifier_circle_orifice_tentacled",
			PresetColour.BASE_PINK_SALMON,
			Rarity.COMMON),
	
	TF_MOD_PENIS_KNOTTED("knotting",
			"Applies an effect related to making a body part knotted.",
			"knotted",
			"modifier_circle_penis_knotted",
			PresetColour.BASE_CRIMSON,
			Rarity.COMMON),
	
	TF_MOD_PENIS_TAPERED("tapering",
			"Applies an effect related to making a body part tapered.",
			"tapered",
			"modifier_circle_penis_tapered",
			PresetColour.BASE_LILAC,
			Rarity.COMMON),
	
	TF_MOD_PENIS_FLARED("flaring",
			"Applies an effect related to making a body part flared.",
			"flared",
			"modifier_circle_penis_flared",
			PresetColour.BASE_BROWN,
			Rarity.COMMON),
	
	TF_MOD_PENIS_BLUNT("blunting",
			"Applies an effect related to making a body part blunted.",
			"blunt",
			"modifier_circle_penis_blunt",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_PENIS_BARBED("barbing",
			"Applies an effect related to making a body part barbed.",
			"barbed",
			"modifier_circle_penis_barbed",
			PresetColour.BASE_RED,
			Rarity.COMMON),
	
	TF_MOD_PENIS_VEINY("veins",
			"Applies an effect related to making a body part veiny.",
			"veiny",
			"modifier_circle_penis_veiny",
			PresetColour.BASE_PINK_SALMON,
			Rarity.COMMON),
	
	TF_MOD_PENIS_PREHENSILE("prehensile",
			"Applies an effect related to making a body part prehensile.",
			"prehensile",
			"modifier_circle_penis_prehensile",
			PresetColour.BASE_TEAL,
			Rarity.COMMON),
	
	TF_MOD_PENIS_OVIPOSITOR("ovipositor",
			"Applies an effect related to making a body part act as an ovipositor.",
			"oviposition",
			"modifier_circle_penis_ovipositor",
			PresetColour.BASE_WHITE,
			Rarity.COMMON),

	
	TF_MOD_CUM_EXPULSION("cum expulsion",
			"Applies an effect related to modifying the amount of cum expelled at each orgasm.",
			"cumming",
			"modifier_circle_squirter",
			PresetColour.BASE_AQUA,
			Rarity.COMMON),
	
	
	// Vagina:
	
	TF_MOD_VAGINA_SQUIRTER("squirter",
			"Applies an effect related to making someone a squirter.",
			"squirting",
			"modifier_circle_squirter",
			PresetColour.BASE_AQUA,
			Rarity.COMMON),
	
	TF_MOD_VAGINA_EGG_LAYER("egg-layer",
			"Applies an effect related to making someone lay eggs.",
			"egg-laying",
			"modifier_circle_vagina_eggLayer",
			PresetColour.EGG,
			Rarity.COMMON),

	TF_MOD_HYMEN("hymen",
			"Applies an effect related to growing or removing a hymen.",
			"hymen",
			"modifier_circle_tf_vagina",
			PresetColour.BASE_PINK_LIGHT,
			Rarity.COMMON),
	
	
	// fluid modifiers:
	
	TF_MOD_FLUID_MUSKY("musky",
			"Applies an effect related to changing a fluid.",
			"musk",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_BROWN,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.MUSKY.getColour();
		}
	},
	
	TF_MOD_FLUID_VISCOUS("viscous",
			"Applies an effect related to changing a fluid.",
			"viscous",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_GREY,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.VISCOUS.getColour();
		}
	},
	
	TF_MOD_FLUID_STICKY("sticky",
			"Applies an effect related to changing a fluid.",
			"sticky",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_YELLOW_LIGHT,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.STICKY.getColour();
		}
	},
	
	TF_MOD_FLUID_SLIMY("slimy",
			"Applies an effect related to changing a fluid.",
			"slimy",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_BLUE_LIGHT,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.SLIMY.getColour();
		}
	},
	
	TF_MOD_FLUID_BUBBLING("bubbling",
			"Applies an effect related to changing a fluid.",
			"bubbling",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_AQUA,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.BUBBLING.getColour();
		}
	},
	
	TF_MOD_FLUID_ALCOHOLIC("strongly alcoholic",
			"Applies an effect related to changing a fluid.",
			"alcoholic",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_ORANGE,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.ALCOHOLIC.getColour();
		}
	},
	
	TF_MOD_FLUID_ALCOHOLIC_WEAK("alcoholic",
			"Applies an effect related to changing a fluid.",
			"alcoholic",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_ORANGE_LIGHT,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.ALCOHOLIC_WEAK.getColour();
		}
	},
	
	TF_MOD_FLUID_ADDICTIVE("addictive",
			"Applies an effect related to changing a fluid.",
			"addictive",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_PINK_DEEP,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.ADDICTIVE.getColour();
		}
	},
	
	TF_MOD_FLUID_HALLUCINOGENIC("psychoactive",
			"Applies an effect related to changing a fluid.",
			"psychoactive",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_MAGENTA,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.HALLUCINOGENIC.getColour();
		}
	},

	TF_MOD_FLUID_MINERAL_OIL("mineral oil",
			"Applies an effect related to changing a fluid.",
			"mineral oil",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_BLACK,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.MINERAL_OIL.getColour();
		}
	},
	
	
	// Fluid flavours:
	
	TF_MOD_FLAVOUR_CUM(FluidFlavour.CUM, "flavours/cum"),
	
	TF_MOD_FLAVOUR_MILK(FluidFlavour.MILK, "flavours/cum"),
	
	TF_MOD_FLAVOUR_GIRLCUM(FluidFlavour.GIRL_CUM, "flavours/cum"),

	TF_MOD_FLAVOUR_FLAVOURLESS(FluidFlavour.FLAVOURLESS, "flavours/flavourless"),
	
	TF_MOD_FLAVOUR_BUBBLEGUM(FluidFlavour.BUBBLEGUM, "flavours/bubblegum"),
	
	TF_MOD_FLAVOUR_BEER(FluidFlavour.BEER, "flavours/beer"),
	
	TF_MOD_FLAVOUR_VANILLA(FluidFlavour.VANILLA, "flavours/vanilla"),
	
	TF_MOD_FLAVOUR_STRAWBERRY(FluidFlavour.STRAWBERRY, "flavours/strawberry"),
	
	TF_MOD_FLAVOUR_CHOCOLATE(FluidFlavour.CHOCOLATE, "flavours/chocolate"),
	
	TF_MOD_FLAVOUR_PINEAPPLE(FluidFlavour.PINEAPPLE, "flavours/pineapple"),
	
	TF_MOD_FLAVOUR_HONEY(FluidFlavour.HONEY, "flavours/honey"),
	
	TF_MOD_FLAVOUR_MINT(FluidFlavour.MINT, "flavours/mint"),

	TF_MOD_FLAVOUR_CHERRY(FluidFlavour.CHERRY, "flavours/cherry"),

	TF_MOD_FLAVOUR_COFFEE(FluidFlavour.COFFEE, "flavours/coffee"),

	TF_MOD_FLAVOUR_TEA(FluidFlavour.TEA, "flavours/tea"),

	TF_MOD_FLAVOUR_MAPLE(FluidFlavour.MAPLE, "flavours/maple"),

	TF_MOD_FLAVOUR_CINNAMON(FluidFlavour.CINNAMON, "flavours/cinnamon"),

	TF_MOD_FLAVOUR_LEMON(FluidFlavour.LEMON, "flavours/lemon"),

	TF_MOD_FLAVOUR_ORANGE(FluidFlavour.ORANGE, "flavours/orange"),
	
	TF_MOD_FLAVOUR_GRAPE(FluidFlavour.GRAPE, "flavours/grape"),
	
	TF_MOD_FLAVOUR_MELON(FluidFlavour.MELON, "flavours/melon"),
	
	TF_MOD_FLAVOUR_COCONUT(FluidFlavour.COCONUT, "flavours/coconut"),
	
	TF_MOD_FLAVOUR_BLUEBERRY(FluidFlavour.BLUEBERRY, "flavours/blueberry"),
	
	TF_MOD_FLAVOUR_BANANA(FluidFlavour.BANANA, "flavours/banana"),
	
	
	// Fetishes:
	
	TF_MOD_FETISH_BODY_PART("body part fetishes",
			"Applies an effect related to a body part fetish.",
			"fetish",
			"modifier_circle_desires",
			PresetColour.BASE_PINK,
			Rarity.COMMON),

	TF_MOD_FETISH_BEHAVIOUR("behavioural fetishes",
			"Applies an effect related to a behavioural fetish.",
			"fetish",
			"modifier_circle_desires",
			PresetColour.BASE_PURPLE,
			Rarity.COMMON),
	
	
	TF_MOD_FETISH_ANAL_GIVING(Fetish.FETISH_ANAL_GIVING),
	TF_MOD_FETISH_ANAL_RECEIVING(Fetish.FETISH_ANAL_RECEIVING),
	TF_MOD_FETISH_VAGINAL_GIVING(Fetish.FETISH_VAGINAL_GIVING),
	TF_MOD_FETISH_VAGINAL_RECEIVING(Fetish.FETISH_VAGINAL_RECEIVING),
	TF_MOD_FETISH_PENIS_GIVING(Fetish.FETISH_PENIS_GIVING),
	TF_MOD_FETISH_PENIS_RECEIVING(Fetish.FETISH_PENIS_RECEIVING),
	TF_MOD_FETISH_BREASTS_OTHERS(Fetish.FETISH_BREASTS_OTHERS),
	TF_MOD_FETISH_BREASTS_SELF(Fetish.FETISH_BREASTS_SELF),
	TF_MOD_FETISH_ORAL_RECEIVING(Fetish.FETISH_ORAL_RECEIVING),
	TF_MOD_FETISH_ORAL_GIVING(Fetish.FETISH_ORAL_GIVING),
	TF_MOD_FETISH_LEG_LOVER(Fetish.FETISH_LEG_LOVER),
	TF_MOD_FETISH_STRUTTER(Fetish.FETISH_STRUTTER),
	TF_MOD_FETISH_FOOT_GIVING(Fetish.FETISH_FOOT_GIVING),
	TF_MOD_FETISH_FOOT_RECEIVING(Fetish.FETISH_FOOT_RECEIVING),
	TF_MOD_FETISH_ARMPIT_GIVING(Fetish.FETISH_ARMPIT_GIVING),
	TF_MOD_FETISH_ARMPIT_RECEIVING(Fetish.FETISH_ARMPIT_RECEIVING),
	TF_MOD_FETISH_LACTATION_OTHERS(Fetish.FETISH_LACTATION_OTHERS),
	TF_MOD_FETISH_LACTATION_SELF(Fetish.FETISH_LACTATION_SELF),
	
	TF_MOD_FETISH_DOMINANT(Fetish.FETISH_DOMINANT),
	TF_MOD_FETISH_SUBMISSIVE(Fetish.FETISH_SUBMISSIVE),
	TF_MOD_FETISH_BONDAGE_VICTIM(Fetish.FETISH_BONDAGE_VICTIM),
	TF_MOD_FETISH_BONDAGE_APPLIER(Fetish.FETISH_BONDAGE_APPLIER),
	TF_MOD_FETISH_CROSS_DRESSER(Fetish.FETISH_CROSS_DRESSER),
	TF_MOD_FETISH_CUM_ADDICT(Fetish.FETISH_CUM_ADDICT),
	TF_MOD_FETISH_CUM_STUD(Fetish.FETISH_CUM_STUD),
	TF_MOD_FETISH_DEFLOWERING(Fetish.FETISH_DEFLOWERING),
	TF_MOD_FETISH_DENIAL(Fetish.FETISH_DENIAL),
	TF_MOD_FETISH_DENIAL_SELF(Fetish.FETISH_DENIAL_SELF),
	TF_MOD_FETISH_EXHIBITIONIST(Fetish.FETISH_EXHIBITIONIST),
	TF_MOD_FETISH_VOYEURIST(Fetish.FETISH_VOYEURIST),
	TF_MOD_FETISH_IMPREGNATION(Fetish.FETISH_IMPREGNATION),
	TF_MOD_FETISH_INCEST(Fetish.FETISH_INCEST),
	TF_MOD_FETISH_MASOCHIST(Fetish.FETISH_MASOCHIST),
	TF_MOD_FETISH_MASTURBATION(Fetish.FETISH_MASTURBATION),
	TF_MOD_FETISH_NON_CON_DOM(Fetish.FETISH_NON_CON_DOM),
	TF_MOD_FETISH_NON_CON_SUB(Fetish.FETISH_NON_CON_SUB),
	TF_MOD_FETISH_PREGNANCY(Fetish.FETISH_PREGNANCY),
	TF_MOD_FETISH_PURE_VIRGIN(Fetish.FETISH_PURE_VIRGIN),
	TF_MOD_FETISH_SADIST(Fetish.FETISH_SADIST),
	TF_MOD_FETISH_TRANSFORMATION_GIVING(Fetish.FETISH_TRANSFORMATION_GIVING),
	TF_MOD_FETISH_TRANSFORMATION_RECEIVING(Fetish.FETISH_TRANSFORMATION_RECEIVING),
	TF_MOD_FETISH_BIMBO(Fetish.FETISH_BIMBO),
	TF_MOD_FETISH_KINK_GIVING(Fetish.FETISH_KINK_GIVING),
	TF_MOD_FETISH_KINK_RECEIVING(Fetish.FETISH_KINK_RECEIVING),
	TF_MOD_FETISH_SIZE_QUEEN(Fetish.FETISH_SIZE_QUEEN),
	;

	private static List<TFModifier> TFModStrengthList = new ArrayList<>();
	private static List<TFModifier> TFModIntelligenceList = new ArrayList<>();
	private static List<TFModifier> TFModCorruptionList = new ArrayList<>();
	private static List<TFModifier> TFModSexualList = new ArrayList<>();
	private static List<TFModifier> TFAttributeList = new ArrayList<>();
	private static List<TFModifier> TFRacialBodyPartsList = new ArrayList<>();
	private static List<TFModifier> TFBodyPartFetishList = new ArrayList<>();
	private static List<TFModifier> TFBehaviouralFetishList = new ArrayList<>();

	private static List<TFModifier> clothingPrimaryList = new ArrayList<>();
	private static List<TFModifier> clothingAttributeList = new ArrayList<>();
	private static List<TFModifier> clothingMajorAttributeList = new ArrayList<>();
	
	private static List<TFModifier> tattooPrimaryList = new ArrayList<>();

	private static List<TFModifier> weaponPrimaryList = new ArrayList<>();
	private static List<TFModifier> weaponAttributeList = new ArrayList<>();
	private static List<TFModifier> weaponMajorAttributeList = new ArrayList<>();

	private static List<TFModifier> dollPrimaryList = new ArrayList<>();
	private static List<TFModifier> dollSecondaryList = new ArrayList<>();
	
	
	static {

		TFModStrengthList.add(NONE);
		TFModIntelligenceList.add(NONE);
		TFModCorruptionList.add(NONE);
		TFModSexualList.add(NONE);
		
		for(TFModifier tfMod : TFModifier.values()) {
			if(tfMod.getAttributeCategory()!=null && tfMod!=TFModifier.DAMAGE_WEAPON && tfMod!=TFModifier.RESISTANCE_WEAPON) {
				switch(tfMod.getAttributeCategory()) {
					case CORRUPTION:
						TFModCorruptionList.add(tfMod);
						break;
					case INTELLIGENCE:
						TFModIntelligenceList.add(tfMod);
						break;
					case STRENGTH:
						TFModStrengthList.add(tfMod);
						break;
				}
			}
		}
		
		TFModSexualList.add(FERTILITY);
		TFModSexualList.add(VIRILITY);
		TFModSexualList.add(DAMAGE_LUST);
		TFModSexualList.add(RESISTANCE_LUST);
		
		// Body parts:
		
		TFRacialBodyPartsList.add(NONE);
		TFRacialBodyPartsList.add(TF_FACE);
		TFRacialBodyPartsList.add(TF_SKIN);
		TFRacialBodyPartsList.add(TF_CORE);
		
		TFRacialBodyPartsList.add(TF_ARMS);
		TFRacialBodyPartsList.add(TF_LEGS);
		
		TFRacialBodyPartsList.add(TF_EARS);
		TFRacialBodyPartsList.add(TF_EYES);
		TFRacialBodyPartsList.add(TF_HAIR);
		TFRacialBodyPartsList.add(TF_ANTENNA);
		TFRacialBodyPartsList.add(TF_HORNS);
		TFRacialBodyPartsList.add(TF_TAIL);
		TFRacialBodyPartsList.add(TF_TENTACLE);
		TFRacialBodyPartsList.add(TF_WINGS);
		
		TFRacialBodyPartsList.add(TF_ASS);
		TFRacialBodyPartsList.add(TF_BREASTS);
		TFRacialBodyPartsList.add(TF_BREASTS_CROTCH);
		TFRacialBodyPartsList.add(TF_PENIS);
		TFRacialBodyPartsList.add(TF_VAGINA);
		
		TFRacialBodyPartsList.add(TF_MILK);
		TFRacialBodyPartsList.add(TF_MILK_CROTCH);
		TFRacialBodyPartsList.add(TF_CUM);
		TFRacialBodyPartsList.add(TF_GIRLCUM);
		
//		TFAttributeList.add(NONE);
		TFAttributeList.add(ARCANE_BOOST);
		

		TFBodyPartFetishList.add(TF_MOD_FETISH_ANAL_GIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_ANAL_RECEIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_BREASTS_OTHERS);
		TFBodyPartFetishList.add(TF_MOD_FETISH_BREASTS_SELF);
		TFBodyPartFetishList.add(TF_MOD_FETISH_ORAL_GIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_ORAL_RECEIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_VAGINAL_GIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_VAGINAL_RECEIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_PENIS_GIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_PENIS_RECEIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_LEG_LOVER);
		TFBodyPartFetishList.add(TF_MOD_FETISH_STRUTTER);
		TFBodyPartFetishList.add(TF_MOD_FETISH_FOOT_GIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_FOOT_RECEIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_ARMPIT_GIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_ARMPIT_RECEIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_LACTATION_OTHERS);
		TFBodyPartFetishList.add(TF_MOD_FETISH_LACTATION_SELF);

		TFBehaviouralFetishList.add(TF_MOD_FETISH_DOMINANT);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_SUBMISSIVE);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_BONDAGE_APPLIER);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_BONDAGE_VICTIM);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_CUM_STUD);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_CUM_ADDICT);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_DEFLOWERING);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_PURE_VIRGIN);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_IMPREGNATION);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_PREGNANCY);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_TRANSFORMATION_GIVING);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_TRANSFORMATION_RECEIVING);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_SADIST);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_MASOCHIST);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_NON_CON_DOM);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_NON_CON_SUB);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_DENIAL);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_DENIAL_SELF);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_VOYEURIST);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_EXHIBITIONIST);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_BIMBO);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_CROSS_DRESSER);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_MASTURBATION);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_INCEST);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_KINK_GIVING);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_KINK_RECEIVING);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_SIZE_QUEEN);

		clothingPrimaryList.add(TFModifier.CLOTHING_MAJOR_ATTRIBUTE);
		clothingPrimaryList.add(TFModifier.CLOTHING_ATTRIBUTE);
		clothingPrimaryList.add(TFModifier.CLOTHING_SPECIAL);
		clothingPrimaryList.add(TFModifier.TF_MOD_FETISH_BODY_PART);
		clothingPrimaryList.add(TFModifier.TF_MOD_FETISH_BEHAVIOUR);
		clothingPrimaryList.add(TF_FACE);
		clothingPrimaryList.add(TF_CORE);
		clothingPrimaryList.add(TF_ARMS);
		clothingPrimaryList.add(TF_HAIR);
		clothingPrimaryList.add(TF_ASS);
		clothingPrimaryList.add(TF_BREASTS);
		clothingPrimaryList.add(TF_BREASTS_CROTCH);
		clothingPrimaryList.add(TF_PENIS);
		clothingPrimaryList.add(TF_VAGINA);

		clothingMajorAttributeList.add(TFModifier.HEALTH_MAXIMUM);
		clothingMajorAttributeList.add(TFModifier.MANA_MAXIMUM);
		clothingMajorAttributeList.add(TFModifier.STRENGTH);
		clothingMajorAttributeList.add(TFModifier.INTELLIGENCE);
		clothingMajorAttributeList.add(TFModifier.CORRUPTION);
		
		clothingAttributeList.add(TFModifier.FERTILITY);
		clothingAttributeList.add(TFModifier.VIRILITY);
		clothingAttributeList.add(TFModifier.DAMAGE_UNARMED);
		clothingAttributeList.add(TFModifier.DAMAGE_MELEE_WEAPON);
		clothingAttributeList.add(TFModifier.DAMAGE_RANGED_WEAPON);
		clothingAttributeList.add(TFModifier.DAMAGE_PHYSICAL);
		clothingAttributeList.add(TFModifier.DAMAGE_LUST);
		clothingAttributeList.add(TFModifier.DAMAGE_FIRE);
		clothingAttributeList.add(TFModifier.DAMAGE_ICE);
		clothingAttributeList.add(TFModifier.DAMAGE_POISON);
		clothingAttributeList.add(TFModifier.DAMAGE_SPELLS);
		clothingAttributeList.add(TFModifier.RESISTANCE_FIRE);
		clothingAttributeList.add(TFModifier.RESISTANCE_ICE);
		clothingAttributeList.add(TFModifier.RESISTANCE_LUST);
		clothingAttributeList.add(TFModifier.RESISTANCE_PHYSICAL);
		clothingAttributeList.add(TFModifier.RESISTANCE_POISON);
		clothingAttributeList.add(TFModifier.SPELL_COST_MODIFIER);
		clothingAttributeList.add(TFModifier.CRITICAL_DAMAGE);
		

		tattooPrimaryList.add(TFModifier.CLOTHING_MAJOR_ATTRIBUTE);
		tattooPrimaryList.add(TFModifier.CLOTHING_ATTRIBUTE);
		tattooPrimaryList.add(TFModifier.TF_MOD_FETISH_BODY_PART);
		tattooPrimaryList.add(TFModifier.TF_MOD_FETISH_BEHAVIOUR);
		tattooPrimaryList.add(TF_FACE);
		tattooPrimaryList.add(TF_CORE);
		tattooPrimaryList.add(TF_HAIR);
		tattooPrimaryList.add(TF_ASS);
		tattooPrimaryList.add(TF_BREASTS);
		tattooPrimaryList.add(TF_BREASTS_CROTCH);
		tattooPrimaryList.add(TF_PENIS);
		tattooPrimaryList.add(TF_VAGINA);
		

		weaponPrimaryList.add(TFModifier.CLOTHING_MAJOR_ATTRIBUTE);
		weaponPrimaryList.add(TFModifier.CLOTHING_ATTRIBUTE);
		
//		weaponAttributeList.add(TFModifier.RESISTANCE_WEAPON);
//		weaponAttributeList.add(TFModifier.DAMAGE_WEAPON);

		weaponMajorAttributeList.add(TFModifier.HEALTH_MAXIMUM);
		weaponMajorAttributeList.add(TFModifier.MANA_MAXIMUM);
		weaponMajorAttributeList.add(TFModifier.STRENGTH);
		weaponMajorAttributeList.add(TFModifier.INTELLIGENCE);
		weaponMajorAttributeList.add(TFModifier.CORRUPTION);
		
		weaponAttributeList.add(TFModifier.FERTILITY);
		weaponAttributeList.add(TFModifier.VIRILITY);
		weaponAttributeList.add(TFModifier.DAMAGE_UNARMED);
		weaponAttributeList.add(TFModifier.DAMAGE_MELEE_WEAPON);
		weaponAttributeList.add(TFModifier.DAMAGE_RANGED_WEAPON);
		weaponAttributeList.add(TFModifier.DAMAGE_PHYSICAL);
		weaponAttributeList.add(TFModifier.DAMAGE_LUST);
		weaponAttributeList.add(TFModifier.DAMAGE_FIRE);
		weaponAttributeList.add(TFModifier.DAMAGE_ICE);
		weaponAttributeList.add(TFModifier.DAMAGE_POISON);
		weaponAttributeList.add(TFModifier.DAMAGE_SPELLS);
		weaponAttributeList.add(TFModifier.RESISTANCE_FIRE);
		weaponAttributeList.add(TFModifier.RESISTANCE_ICE);
		weaponAttributeList.add(TFModifier.RESISTANCE_LUST);
		weaponAttributeList.add(TFModifier.RESISTANCE_PHYSICAL);
		weaponAttributeList.add(TFModifier.RESISTANCE_POISON);
		weaponAttributeList.add(TFModifier.SPELL_COST_MODIFIER);
		weaponAttributeList.add(TFModifier.CRITICAL_DAMAGE);
	}
	
	
	private enum AttributeCategory {
		STRENGTH,
		INTELLIGENCE,
		CORRUPTION;
	}
	
	private AttributeCategory attributeCategory;
	private AbstractAttribute associatedAttribute;
	
	private String name;
	private String description;
	private String descriptor;
	private String path;
	private String SVGString;
	
	private Colour colour;
	private Rarity rarity;
	private AbstractFetish fetish;
	
	private TFModifier(AttributeCategory attributeCategory, AbstractAttribute associatedAttribute, String description, String SVGString, Rarity rarity) {
		this.attributeCategory=attributeCategory;
		this.associatedAttribute=associatedAttribute;
		this.name = associatedAttribute.getName();
		this.description = description;
		this.descriptor = associatedAttribute.getPositiveEnchantment();
		this.colour = associatedAttribute.getColour();
		this.rarity=rarity;
		
		this.path = SVGString;
		this.SVGString = null;
	}
	
	private TFModifier(String name, String description, String descriptor, String SVGString, Colour colour, Rarity rarity) {
		this.name = name;
		this.description = description;
		this.descriptor = descriptor;
		this.rarity=rarity;
		
		if (colour == null) {
			this.colour = PresetColour.CLOTHING_BLACK;
		} else {
			this.colour = colour;
		}
		
		this.path = SVGString;
		this.SVGString = null;
	}
	
	private TFModifier(AbstractFetish f) {
		this.name = f.getName(null);
		this.description = "Applies an effect related to the "+name+" fetish. ("+Util.capitaliseSentence(f.getShortDescriptor(null))+".)";
		this.descriptor = name;
		this.rarity = Rarity.EPIC;
		this.colour = PresetColour.FETISH;
		this.fetish = f;
		this.SVGString = f.getSVGString(null);
	}

	private TFModifier(FluidFlavour flavour, String pathName) {
		this(flavour, pathName, flavour.getColour());
	}
	
	private TFModifier(FluidFlavour flavour, String pathName, Colour colour) {
		this.name = flavour.getName()+(flavour==FluidFlavour.FLAVOURLESS?"":"-flavour");
		this.description = "Applies an effect related to changing a fluid's flavour.";
		this.descriptor = name;
		this.rarity = Rarity.COMMON;
		this.colour = colour;
		this.path = pathName;
		this.SVGString = null;
	}
	
	public int getValue() {
		switch(getRarity()) {
			case JINXED:
				return 1;
			case COMMON:
				return 1;
			case UNCOMMON:
				return 2;
			case RARE:
				return 4;
			case EPIC:
				return 8;
			case LEGENDARY:
				return 12;
			case QUEST:
				return 1;
		}
		return 1;
	}
	
	public AttributeCategory getAttributeCategory() {
		return attributeCategory;
	}

	public AbstractAttribute getAssociatedAttribute() {
		return associatedAttribute;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public String getSVGString() {
		if(SVGString==null) {
			// Set this item's file image:
			try {
				InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/crafting/" + path + ".svg");
				if(is==null) {
					System.err.println("Error! TFModifier icon file does not exist (Trying to read from '"+path+"')! (Code 2)");
				}
				String s = Util.inputStreamToString(is);

				is.close();
				
				if(path.contains("flavour")) {
					String SVGStringBackground = "";
					is = Subspecies.class.getClassLoader().getResourceAsStream("com/lilithsthrone/res/crafting/flavours/background.svg");
					if(is==null) {
						System.err.println("Error! Subspecies background icon file does not exist (Trying to read from 'flavours/background')!");
					}
					SVGStringBackground = "<div style='width:80%;height:80%;position:absolute;left:10%;bottom:10%;'>"+SvgUtil.colourReplacement(this.toString()+"_B", this.getColour(), Util.inputStreamToString(is))+"</div>";
					
					s = SVGStringBackground + "<div style='width:50%;height:50%;position:absolute;left:25%;bottom:25%;'>" + SvgUtil.colourReplacement(this.toString(), this.getColour(), s)+"</div>";
					
				} else {
					s = SvgUtil.colourReplacement(this.toString(), this.getColour(), s);
				}
				
				this.SVGString = s;

				is.close();

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return SVGString;
	}

	public Colour getColour() {
		return colour;
	}
	
	public boolean isSoloDescriptor() {
		return false;
	}
	
	public Rarity getRarity() {
		return rarity;
	}

	public static List<TFModifier> getTFModStrengthList() {
		return TFModStrengthList;
	}

	public static List<TFModifier> getTFModIntelligenceList() {
		return TFModIntelligenceList;
	}

	public static List<TFModifier> getTFModCorruptionList() {
		return TFModCorruptionList;
	}
	
	public static List<TFModifier> getTFModSexualList() {
		return TFModSexualList;
	}

	public static List<TFModifier> getTFRacialBodyPartsList() {
		List<TFModifier> returnList = new ArrayList<>(TFRacialBodyPartsList);
		if(!Main.game.isUdderContentEnabled()) {
			returnList.remove(TFModifier.TF_BREASTS_CROTCH);
			returnList.remove(TFModifier.TF_MILK_CROTCH);
		}
		return returnList;
	}

	public static List<TFModifier> getTFAttributeList() {
		return TFAttributeList;
	}

	public static List<TFModifier> getTFBodyPartFetishList() {
		List<TFModifier> returnList = new ArrayList<>(TFBodyPartFetishList);
		returnList.removeIf(modifier->!modifier.fetish.isContentEnabled());
		return returnList;
	}
	
	public static List<TFModifier> getTFBehaviouralFetishList() {
		List<TFModifier> returnList = new ArrayList<>(TFBehaviouralFetishList);
		returnList.removeIf(modifier->!modifier.fetish.isContentEnabled());
		return returnList;
	}

	public AbstractFetish getFetish() {
		return fetish;
	}

	public static List<TFModifier> getClothingAttributeList() {
		return clothingAttributeList;
	}

	public static List<TFModifier> getClothingMajorAttributeList() {
		return clothingMajorAttributeList;
	}

	public static List<TFModifier> getClothingPrimaryList() {
		List<TFModifier> returnList = new ArrayList<>(clothingPrimaryList);
		if(!Main.game.isBodyHairEnabled()) {
			returnList.remove(TF_ARMS);
		}
		if(!Main.game.isUdderContentEnabled()) {
			returnList.remove(TFModifier.TF_BREASTS_CROTCH);
			returnList.remove(TFModifier.TF_MILK_CROTCH);
		}
		return returnList;
	}

	public static List<TFModifier> getTattooPrimaryList() {
		return tattooPrimaryList;
	}

	public static List<TFModifier> getWeaponPrimaryList() {
		return weaponPrimaryList;
	}

	public static List<TFModifier> getWeaponMajorAttributeList() {
		return weaponMajorAttributeList;
	}
	
	public static List<TFModifier> getWeaponAttributeList() {
		return weaponAttributeList;
	}

	public static List<TFModifier> getDollPrimaryList() {
		return dollPrimaryList;
	}

	public static List<TFModifier> getDollSecondaryList() {
		return dollSecondaryList;
	}
}
