package com.lilithsthrone.game.character.body.types;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractEarType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.2
 * @author Innoxia
 */
public class EarType {
	
	public static AbstractEarType HUMAN = new AbstractEarType(BodyCoveringType.HUMAN,
			Race.HUMAN,
			false,
			"human",
			"ear",
			"ears",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues("soft", "feminine"),
			"The hot itching feeling passes after a few moments, leaving [npc.herHim] with normal-looking human ears.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldHuman(human ears)], which are [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] a pair of normal, human ears, which are [npc.materialCompositionDescriptor] [npc.earFullDescription(true)]#IF(npc.isPiercedEar()), and which have been pierced#ENDIF.") {
	};

	public static AbstractEarType ANGEL = new AbstractEarType(BodyCoveringType.ANGEL,
			Race.ANGEL,
			false,
			"angel",
			"ear",
			"ears",
			Util.newArrayListOfValues("pointed", "delicate", "angelic"),
			Util.newArrayListOfValues("soft", "feminine", "pointed", "delicate", "angelic"),
			"The hot itching feeling passes after a few moments, leaving [npc.herHim] with delicate, humanoid ears, with long, pointed tips.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldAngel(pointed, angelic ears)], which are [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] a pair of pointed angelic ears, which are [npc.materialCompositionDescriptor] [npc.earFullDescription(true)]#IF(npc.isPiercedEar()), and which have been pierced#ENDIF.") {
	};

	public static AbstractEarType DEMON_COMMON = new AbstractEarType(BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			false,
			"demonic",
			"ear",
			"ears",
			Util.newArrayListOfValues("pointed", "demonic"),
			Util.newArrayListOfValues("soft", "feminine", "pointed", "demonic"),
			"The hot itching feeling passes after a few moments, leaving [npc.herHim] with delicate, humanoid ears, with long, pointed tips.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldDemon(pointed, demonic ears)], which are [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] a pair of pointed demonic ears, which are [npc.materialCompositionDescriptor] [npc.earFullDescription(true)]#IF(npc.isPiercedEar()), and which have been pierced#ENDIF.") {
	};
	
	public static AbstractEarType DOG_MORPH = new AbstractEarType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			true,
			"floppy dog",
			"ear",
			"ears",
			Util.newArrayListOfValues("floppy", "furry", "fur-coated", "dog-like"),
			Util.newArrayListOfValues("feminine", "floppy", "furry", "fur-coated", "dog-like"),
			"They quickly expand in size, and shift to sit higher up on [npc.her] head than a normal pair of human ears would."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ " A layer of [npc.earFullDescriptionColour] quickly grows to cover them,"
				+ "#ELSE"
				+ " Just like the rest of [npc.her] body, they're made out of [npc.earFullDescription],"
				+ "#ENDIF"
				+ " and as the transformation finishes, [npc.she] experimentally [npc.verb(twitch)] [npc.her] new dog-like ears back and forth.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldDog(floppy, dog-like ears)], which are [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] a pair of floppy,#IF(npc.isPiercedEar()) pierced,#ENDIF dog-like ears, which are positioned high up on [npc.her] head and are [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType DOG_MORPH_POINTED = new AbstractEarType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			false,
			"pointed dog",
			"ear",
			"ears",
			Util.newArrayListOfValues("pointed", "furry", "fur-coated", "dog-like"),
			Util.newArrayListOfValues("feminine", "pointed", "furry", "fur-coated", "dog-like"),
			"They quickly grow into upright points, and shift to sit higher up on [npc.her] head than a normal pair of human ears would."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ " A layer of [npc.earFullDescriptionColour] quickly grows to cover them,"
				+ "#ELSE"
				+ " Just like the rest of [npc.her] body, they're made out of [npc.earFullDescription],"
				+ "#ENDIF"
				+ " and as the transformation finishes, [npc.she] experimentally [npc.verb(twitch)] [npc.her] new dog-like ears back and forth.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldDog(pointed, dog-like ears)], which are [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] a pair of pointed,#IF(npc.isPiercedEar()) pierced,#ENDIF dog-like ears, which are positioned high up on [npc.her] head and are [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType DOG_MORPH_FOLDED = new AbstractEarType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			false,
			"folded dog",
			"ear",
			"ears",
			Util.newArrayListOfValues("folded", "furry", "fur-coated", "dog-like"),
			Util.newArrayListOfValues("feminine", "folded", "furry", "fur-coated", "dog-like"),
			"They quickly grow into upright points, before folding over at the top and shifting to sit higher up on [npc.her] head than a normal pair of human ears would."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ " A layer of [npc.earFullDescriptionColour] quickly grows to cover them,"
				+ "#ELSE"
				+ " Just like the rest of [npc.her] body, they're made out of [npc.earFullDescription],"
				+ "#ENDIF"
				+ " and as the transformation finishes, [npc.she] experimentally [npc.verb(twitch)] [npc.her] new dog-like ears back and forth.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldDog(folded, dog-like ears)], which are [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] a pair of folded,#IF(npc.isPiercedEar()) pierced,#ENDIF dog-like ears, which are positioned high up on [npc.her] head and are [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType WOLF_MORPH = new AbstractEarType(BodyCoveringType.LYCAN_FUR,
			Race.WOLF_MORPH,
			false,
			"wolf",
			"ear",
			"ears",
			Util.newArrayListOfValues("furry", "fur-coated", "wolf-like"),
			Util.newArrayListOfValues("feminine", "furry", "fur-coated", "wolf-like"),
			"They quickly grow into large, upright points, and shift to sit higher up on [npc.her] head than a normal pair of human ears would."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ " A layer of [npc.earFullDescriptionColour] quickly grows to cover them,"
				+ "#ELSE"
				+ " Just like the rest of [npc.her] body, they're made out of [npc.earFullDescription],"
				+ "#ENDIF"
				+ " and as the transformation finishes, [npc.she] experimentally [npc.verb(twitch)] [npc.her] new wolf-like ears back and forth.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldWolf(large, wolf-like ears)], which are [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] a pair of upright,#IF(npc.isPiercedEar()) pierced,#ENDIF wolf-like ears, which are positioned high up on [npc.her] head and are [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType FOX_MORPH = new AbstractEarType(BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			false,
			"fox",
			"ear",
			"ears",
			Util.newArrayListOfValues("pointed", "furry", "fur-coated", "fox-like"),
			Util.newArrayListOfValues("feminine", "pointed", "furry", "fur-coated", "fox-like"),
			"They quickly grow into large, upright points, and shift to sit higher up on [npc.her] head than a normal pair of human ears would."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ " A layer of [npc.earFullDescriptionColour] quickly grows to cover them,"
				+ "#ELSE"
				+ " Just like the rest of [npc.her] body, they're made out of [npc.earFullDescription],"
				+ "#ENDIF"
				+ " and as the transformation finishes, [npc.she] experimentally [npc.verb(twitch)] [npc.her] new fox-like ears back and forth.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldFox(pointed, fox-like ears)], which are [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] a pair of pointed,#IF(npc.isPiercedEar()) pierced,#ENDIF fox-like ears, which are positioned high up on [npc.her] head and are [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType FOX_MORPH_BIG = new AbstractEarType(BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			true,
			"fennec fox",
			"ear",
			"ears",
			Util.newArrayListOfValues("pointed", "furry", "fur-coated", "large", "fennec-fox-like"),
			Util.newArrayListOfValues("feminine", "pointed", "furry", "fur-coated", "large", "fennec-fox-like"),
			"They quickly grow into massive, upright points, and shift to sit higher up on [npc.her] head than a normal pair of human ears would."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ " A layer of [npc.earFullDescriptionColour] quickly grows to cover them,"
				+ "#ELSE"
				+ " Just like the rest of [npc.her] body, they're made out of [npc.earFullDescription],"
				+ "#ENDIF"
				+ " and as the transformation finishes, [npc.she] experimentally [npc.verb(twitch)] [npc.her] new fox-like ears back and forth.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldFox(massive, fennec-fox-like ears)], which are [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] a pair of massive,#IF(npc.isPiercedEar()) pierced,#ENDIF fennec-fox-like ears, which are positioned high up on [npc.her] head and are [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType COW_MORPH = new AbstractEarType(BodyCoveringType.BOVINE_FUR,
			Race.COW_MORPH,
			false,
			"cow",
			"ear",
			"ears",
			Util.newArrayListOfValues("furry", "fur-coated", "cow-like"),
			Util.newArrayListOfValues("feminine", "furry", "fur-coated", "cow-like"),
			"They quickly take on a distinctly bovine shape by growing out and narrowing down into long, slightly-folded ovals."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ " A layer of [npc.earFullDescriptionColour] quickly grows to cover them,"
				+ "#ELSE"
				+ " Just like the rest of [npc.her] body, they're made out of [npc.earFullDescription],"
				+ "#ENDIF"
				+ " and as the transformation finishes, [npc.she] experimentally [npc.verb(twitch)] [npc.her] new cow-like ears back and forth.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldCow(cow-like ears)], which are [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] a pair of #IF(npc.isPiercedEar()) pierced,#ENDIF cow-like ears, which are positioned high up on [npc.her] head and are [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType CAT_MORPH = new AbstractEarType(BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			false,
			"cat",
			"ear",
			"ears",
			Util.newArrayListOfValues("furry", "fur-coated", "cat-like"),
			Util.newArrayListOfValues("feminine", "furry", "fur-coated", "cat-like"),
			"They quickly grow into upright points, and shift to sit higher up on [npc.her] head than a normal pair of human ears would."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ " A layer of [npc.earFullDescriptionColour] quickly grows to cover them,"
				+ "#ELSE"
				+ " Just like the rest of [npc.her] body, they're made out of [npc.earFullDescription],"
				+ "#ENDIF"
				+ " and as the transformation finishes, [npc.she] experimentally [npc.verb(twitch)] [npc.her] new cat-like ears back and forth.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldCat(cat-like ears)], which are [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] a pair of #IF(npc.isPiercedEar()) pierced,#ENDIF cat-like ears, which are positioned high up on [npc.her] head and are [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType CAT_MORPH_TUFTED = new AbstractEarType(BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			false,
			"tufted cat",
			"ear",
			"ears",
			Util.newArrayListOfValues("tufted", "furry", "fur-coated", "cat-like"),
			Util.newArrayListOfValues("feminine", "tufted", "furry", "fur-coated", "cat-like"),
			"They quickly grow into upright points, which are topped off with a small patch of sensitive fur, and shift to sit higher up on [npc.her] head than a normal pair of human ears would."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ " A layer of [npc.earFullDescriptionColour] quickly grows to cover them,"
				+ "#ELSE"
				+ " Just like the rest of [npc.her] body, they're made out of [npc.earFullDescription],"
				+ "#ENDIF"
				+ " and as the transformation finishes, [npc.she] experimentally [npc.verb(twitch)] [npc.her] new cat-like ears back and forth.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldCat(tufted, cat-like ears)], which are [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] a pair of tufted,#IF(npc.isPiercedEar()) pierced,#ENDIF cat-like ears, which are positioned high up on [npc.her] head and are [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType SQUIRREL_MORPH = new AbstractEarType(BodyCoveringType.SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			false,
			"squirrel",
			"ear",
			"ears",
			Util.newArrayListOfValues("furry", "fur-coated", "squirrel-like"),
			Util.newArrayListOfValues("feminine", "furry", "fur-coated", "squirrel-like"),
			"They quickly grow into small, upright ovals, and shift to sit higher up on [npc.her] head than a normal pair of human ears would."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ " A layer of [npc.earFullDescriptionColour] quickly grows to cover them,"
				+ "#ELSE"
				+ " Just like the rest of [npc.her] body, they're made out of [npc.earFullDescription],"
				+ "#ENDIF"
				+ " and as the transformation finishes, [npc.she] experimentally [npc.verb(twitch)] [npc.her] new squirrel-like ears back and forth.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldSquirrel(squirrel-like ears)], which are [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] a pair of#IF(npc.isPiercedEar()) pierced,#ENDIF squirrel-like ears, which are positioned high up on [npc.her] head and are [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType RAT_MORPH = new AbstractEarType(BodyCoveringType.RAT_FUR,
			Race.RAT_MORPH,
			false,
			"rat",
			"ear",
			"ears",
			Util.newArrayListOfValues("rat-like"),
			Util.newArrayListOfValues("feminine", "rat-like"),
			"They quickly grow into small, upright ovals, and shift to sit higher up on [npc.her] head than a normal pair of human ears would."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ " A layer of [npc.earFullDescriptionColour] quickly grows to cover them,"
				+ "#ELSE"
				+ " Just like the rest of [npc.her] body, they're made out of [npc.earFullDescription],"
				+ "#ENDIF"
				+ " and as the transformation finishes, [npc.she] experimentally [npc.verb(twitch)] [npc.her] new rat-like ears back and forth.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldRat(rat-like ears)], which are [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] a pair of #IF(npc.isPiercedEar()) pierced,#ENDIF rat-like ears, which are positioned high up on [npc.her] head and are [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType RABBIT_MORPH = new AbstractEarType(BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			true,
			"upright rabbit",
			"ear",
			"ears",
			Util.newArrayListOfValues("upright", "furry", "fur-coated", "rabbit-like"),
			Util.newArrayListOfValues("feminine", "upright", "furry", "fur-coated", "rabbit-like"),
			"They quickly grow into large, upright points, and shift to sit higher up on [npc.her] head than a normal pair of human ears would."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ " A layer of [npc.earFullDescriptionColour] quickly grows to cover them,"
				+ "#ELSE"
				+ " Just like the rest of [npc.her] body, they're made out of [npc.earFullDescription],"
				+ "#ENDIF"
				+ " and as the transformation finishes, [npc.she] experimentally [npc.verb(twitch)] [npc.her] new rabbit-like ears back and forth.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldRabbit(upright, rabbit-like ears)], which are [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] a pair of upright,#IF(npc.isPiercedEar()) pierced,#ENDIF rabbit-like ears, which are positioned high up on [npc.her] head and are [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType RABBIT_MORPH_FLOPPY = new AbstractEarType(BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			true,
			"floppy rabbit",
			"ear",
			"ears",
			Util.newArrayListOfValues("floppy", "furry", "fur-coated", "rabbit-like"),
			Util.newArrayListOfValues("feminine", "floppy", "furry", "fur-coated", "rabbit-like"),
			"They quickly grow into large, upright points, and shift to sit higher up than a normal pair of human ears would, before suddenly collapsing and flopping down on either side of [npc.her] head."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ " A layer of [npc.earFullDescriptionColour] quickly grows to cover them,"
				+ "#ELSE"
				+ " Just like the rest of [npc.her] body, they're made out of [npc.earFullDescription],"
				+ "#ENDIF"
				+ " and as the transformation finishes, [npc.she] experimentally [npc.verb(twitch)] [npc.her] new rabbit-like ears back and forth.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldRabbit(floppy, rabbit-like ears)], which are [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] a pair of floppy,#IF(npc.isPiercedEar()) pierced,#ENDIF rabbit-like ears, which are positioned high up on [npc.her] head and are [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType BAT_MORPH = new AbstractEarType(BodyCoveringType.BAT_FUR,
			Race.BAT_MORPH,
			false,
			"bat",
			"ear",
			"ears",
			Util.newArrayListOfValues("large", "bat-like"),
			Util.newArrayListOfValues("feminine", "large", "bat-like"),
			"They quickly grow into large, upright points, and shift to sit higher up on [npc.her] head than a normal pair of human ears would."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ " A layer of [npc.earFullDescriptionColour] quickly grows to cover them,"
				+ "#ELSE"
				+ " Just like the rest of [npc.her] body, they're made out of [npc.earFullDescription],"
				+ "#ENDIF"
				+ " and as the transformation finishes, [npc.she] [npc.verb(discover)] that [npc.she] can easily twitch [npc.her] new bat-like ears back and forth.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldBat(large, bat-like ears)], which are [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] a pair of large,#IF(npc.isPiercedEar()) pierced,#ENDIF bat-like ears, which are positioned high up on [npc.her] head and are [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType HORSE_MORPH = new AbstractEarType(BodyCoveringType.HORSE_HAIR,
			Race.HORSE_MORPH,
			false,
			"horse",
			"ear",
			"ears",
			Util.newArrayListOfValues("furry", "upright", "horse-like"),
			Util.newArrayListOfValues("feminine", "furry", "upright", "horse-like"),
			"They quickly grow into sturdy little upright points, and shift to sit higher up on [npc.her] head than a normal pair of human ears would."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ " A layer of [npc.earFullDescriptionColour] quickly grows to cover them,"
				+ "#ELSE"
				+ " Just like the rest of [npc.her] body, they're made out of [npc.earFullDescription],"
				+ "#ENDIF"
				+ " and as the transformation finishes, [npc.she] experimentally [npc.verb(twitch)] [npc.her] new horse-like ears back and forth.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldHorse(horse-like ears)], which are [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] a pair of upright,#IF(npc.isPiercedEar()) pierced,#ENDIF horse-like ears, which are positioned high up on [npc.her] head and are [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType REINDEER_MORPH = new AbstractEarType(BodyCoveringType.REINDEER_FUR,
			Race.REINDEER_MORPH,
			false,
			"reindeer",
			"ear",
			"ears",
			Util.newArrayListOfValues("furry", "reindeer-like"),
			Util.newArrayListOfValues("feminine", "furry", "reindeer-like"),
			"They quickly take on a distinctly reindeer-like shape by growing out and narrowing down into long, slightly-folded ovals, before shifting to sit higher up on [npc.her] head than a normal pair of human ears would."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ " A layer of [npc.earFullDescriptionColour] quickly grows to cover them,"
				+ "#ELSE"
				+ " Just like the rest of [npc.her] body, they're made out of [npc.earFullDescription],"
				+ "#ENDIF"
				+ " and as the transformation finishes, [npc.she] experimentally [npc.verb(twitch)] [npc.her] new reindeer-like ears back and forth.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldReindeer(reindeer-like ears)], which are [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] a pair of#IF(npc.isPiercedEar()) pierced,#ENDIF reindeer-like ears, which are positioned high up on [npc.her] head and are [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType ALLIGATOR_MORPH = new AbstractEarType(BodyCoveringType.ALLIGATOR_SCALES,
			Race.ALLIGATOR_MORPH,
			false,
			"alligator",
			"ear",
			"ears",
			Util.newArrayListOfValues("scaled", "scale-covered", "alligator-like"),
			Util.newArrayListOfValues("feminine", "scaled", "scale-covered", "alligator-like"),
			"They quickly shrink down into little nubs as most of the external cartilage shifts down into the sides of [npc.her] head."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ " A layer of [npc.earFullDescriptionColour] grow to cover [npc.her] now-fully-internal ears,"
				+ "#ELSE"
				+ " Just like the rest of [npc.her] body, [npc.her] now-fully-internal ears are made out of [npc.earFullDescription],"
				+ "#ENDIF"
				+ " and as the transformation finishes, [npc.sheIs] left with the ears of an alligator-morph.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldAlligator(internal, scale-covered alligator ears)], which are [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.Her] ears are an internal part of [npc.her] head, and are covered by a fan of [npc.earFullDescription(true)]."
				+ "#IF(npc.isPiercedEar()) They have been cleverly pierced so as to allow [npc.herHim] to wear ear-specific jewellery.#ENDIF") {
	};
	
	public static AbstractEarType HARPY = new AbstractEarType(BodyCoveringType.FEATHERS,
			Race.HARPY,
			false,
			"harpy",
			"ear",
			"ears",
			Util.newArrayListOfValues("feathered", "feather-covered", "bird-like"),
			Util.newArrayListOfValues("feminine", "feathered", "feather-covered", "bird-like"),
			"They quickly shrink down into little nubs as most of the external cartilage shifts down into the sides of [npc.her] head."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ " A layer of [npc.earFullDescriptionColour] grow to cover [npc.her] now-fully-internal ears,"
				+ "#ELSE"
				+ " Just like the rest of [npc.her] body, [npc.her] now-fully-internal ears are made out of [npc.earFullDescription],"
				+ "#ENDIF"
				+ " and as the transformation finishes, [npc.sheIs] left with a pair of beautifully-feathered harpy ears.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldHarpy(internal, feather-covered harpy ears)], which are [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.Her] ears are an internal part of [npc.her] head, and are covered by a fan of [npc.earFullDescription(true)]."
				+ "#IF(npc.isPiercedEar()) They have been cleverly pierced so as to allow [npc.herHim] to wear ear-specific jewellery.#ENDIF") {
	};
	
	private static List<AbstractEarType> allEarTypes;
	private static Map<AbstractEarType, String> earToIdMap = new HashMap<>();
	private static Map<String, AbstractEarType> idToEarMap = new HashMap<>();
	
	static {
		allEarTypes = new ArrayList<>();
		
		// Add in hard-coded ear types:
		Field[] fields = EarType.class.getFields();
		
		for(Field f : fields){
			if (AbstractEarType.class.isAssignableFrom(f.getType())) {
				
				AbstractEarType ct;
				try {
					ct = ((AbstractEarType) f.get(null));

					earToIdMap.put(ct, f.getName());
					idToEarMap.put(f.getName(), ct);
					
					allEarTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allEarTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractEarType getEarTypeFromId(String id) {
		if(id.equals("IMP")) {
			return EarType.DEMON_COMMON;
		}
		if(id.equals("LYCAN")) {
			return EarType.WOLF_MORPH;
		}
		id = Util.getClosestStringMatch(id, idToEarMap.keySet());
		return idToEarMap.get(id);
	}
	
	public static String getIdFromEarType(AbstractEarType earType) {
		return earToIdMap.get(earType);
	}
	
	public static List<AbstractEarType> getAllEarTypes() {
		return allEarTypes;
	}
	
	private static Map<AbstractRace, List<AbstractEarType>> typesMap = new HashMap<>();
	
	public static List<AbstractEarType> getEarTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractEarType> types = new ArrayList<>();
		for(AbstractEarType type : EarType.getAllEarTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}