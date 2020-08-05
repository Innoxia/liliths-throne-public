package com.lilithsthrone.game.character.body.types;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractFaceType;
import com.lilithsthrone.game.character.body.tags.FaceTypeTag;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.7
 * @author Innoxia
 */
public class FaceType {
	
	public static AbstractFaceType HUMAN = new AbstractFaceType(BodyCoveringType.HUMAN,
			Race.HUMAN,
			FaceStructure.HUMAN,
			MouthType.HUMAN,
			NoseType.HUMAN,
			"Thankfully#IF(!npc.isPlayer())for [npc.herHim]#ENDIF, the transformation only lasts a matter of moments, leaving [npc.herHim] with a normal human face, [npc.materialDescriptor] [npc.faceSkin+].<br/>"
				+ "[npc.Name] now [npc.has] a [style.boldHuman(human face)], [npc.materialDescriptor] [npc.faceFullDescription]."
				+ " Within [npc.her] [npc.mouth], [npc.she] has a [style.boldHuman(human tongue)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], human face, [npc.materialDescriptor] [npc.faceFullDescription(true)].",
			Util.newArrayListOfValues()){
	};

	public static AbstractFaceType ANGEL = new AbstractFaceType(BodyCoveringType.ANGEL,
			Race.ANGEL,
			FaceStructure.ANGEL,
			MouthType.ANGEL,
			NoseType.ANGEL,
			"Thankfully#IF(!npc.isPlayer())for [npc.herHim]#ENDIF, the transformation only lasts a matter of moments, leaving [npc.herHim] with an angelic, human-looking face, [npc.materialDescriptor] [npc.faceSkin+].<br/>"
					+ "[npc.Name] now [npc.has] an [style.boldAngel(angelic face)], [npc.materialDescriptor] [npc.faceFullDescription]."
					+ " Within [npc.her] [npc.mouth], [npc.she] has an [style.boldAngel(angelic tongue)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], angelic face, [npc.materialDescriptor] [npc.faceFullDescription(true)].",
			Util.newArrayListOfValues()){
	};

	public static AbstractFaceType DEMON_COMMON = new AbstractFaceType(BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			FaceStructure.DEMON_COMMON,
			MouthType.DEMON_COMMON,
			NoseType.DEMON_COMMON,
			"#IF(npc.isShortStature())"
				+ "Thankfully#IF(!npc.isPlayer())for [npc.herHim]#ENDIF, the transformation only lasts a matter of moments, leaving [npc.herHim] with an impish, human-looking face, [npc.materialDescriptor] [npc.faceSkin+].<br/>"
				+ "[npc.Name] now [npc.has] a [style.boldImp(impish face)], [npc.materialDescriptor] [npc.faceFullDescription]."
				+ " Within [npc.her] [npc.mouth], [npc.she] has a [style.boldImp(impish tongue)]."
			+ "#ELSE"
				+ "Thankfully#IF(!npc.isPlayer())for [npc.herHim]#ENDIF, the transformation only lasts a matter of moments, leaving [npc.herHim] with a demonic, human-looking face, [npc.materialDescriptor] [npc.faceSkin+].<br/>"
				+ "[npc.Name] now [npc.has] a [style.boldDemon(demonic face)], [npc.materialDescriptor] [npc.faceFullDescription]."
				+ " Within [npc.her] [npc.mouth], [npc.she] has a [style.boldDemon(demonic tongue)]."
			+ "#ENDIF",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], #IF(npc.isShortStature())impish#ELSEdemonic#ENDIF face, [npc.materialDescriptor] [npc.faceFullDescription(true)].",
			Util.newArrayListOfValues()){
	};

	public static AbstractFaceType ALLIGATOR_MORPH = new AbstractFaceType(BodyCoveringType.ALLIGATOR_SCALES,
			Race.ALLIGATOR_MORPH,
			FaceStructure.ALLIGATOR_MORPH,
			MouthType.ALLIGATOR_MORPH,
			NoseType.ALLIGATOR_MORPH,
			"[npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic reptilian muzzle, and [npc.her] tongue transforms into a strong, alligator-like one."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face"
				+ "#ELSE"
					+ " Just like the rest of [npc.her] body, [npc.her] new face is [npc.materialDescriptor] [npc.faceSkin+]"
				+ "#ENDIF"
					+ ", and as the transformation finally comes to an end, [npc.sheIs] left panting and sighing as [npc.she] [npc.verb(try)] to catch [npc.her] breath.<br/>"
				+ "[npc.Name] now [npc.has] an anthropomorphic [style.boldAlligatorMorph(alligator-like face)], [npc.materialDescriptor] [npc.faceFullDescription]."
				+ " Within [npc.her] [npc.mouth], [npc.she] has a [style.boldAlligatorMorph(strong, alligator-like tongue)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], anthropomorphic, alligator-like face [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)] and complete with a long, flat muzzle.",
			Util.newArrayListOfValues(
					FaceTypeTag.MUZZLE,
					FaceTypeTag.FANGS,
					FaceTypeTag.NATURAL_BALDNESS_SCALY
				)){
	};

	public static AbstractFaceType BAT_MORPH = new AbstractFaceType(BodyCoveringType.BAT_FUR,
			Race.BAT_MORPH,
			FaceStructure.BAT_MORPH,
			MouthType.BAT_MORPH,
			NoseType.BAT_MORPH,
			"[npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic bat-like muzzle, and [npc.her] tongue transforms into a thin, bat-like one."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face"
				+ "#ELSE"
					+ " Just like the rest of [npc.her] body, [npc.her] new face is [npc.materialDescriptor] [npc.faceSkin+]"
				+ "#ENDIF"
					+ ", and as the transformation finally comes to an end, [npc.sheIs] left panting and sighing as [npc.she] [npc.verb(try)] to catch [npc.her] breath.<br/>"
				+ "[npc.Name] now [npc.has] an anthropomorphic [style.boldBatMorph(bat-like face)], [npc.materialDescriptor] [npc.faceFullDescription]."
				+ " Within [npc.her] [npc.mouth], [npc.she] has a [style.boldBatMorph(thin, bat-like tongue)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], anthropomorphic, bat-like face [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)] and complete with a short muzzle.",
			Util.newArrayListOfValues(
					FaceTypeTag.MUZZLE,
					FaceTypeTag.FANGS,
					FaceTypeTag.NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType CAT_MORPH = new AbstractFaceType(BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			FaceStructure.CAT_MORPH,
			MouthType.CAT_MORPH,
			NoseType.CAT_MORPH,
			"[npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic feline muzzle, and [npc.her] tongue flattens and transforms into a cat-like one."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face"
				+ "#ELSE"
					+ " Just like the rest of [npc.her] body, [npc.her] new face is [npc.materialDescriptor] [npc.faceSkin+]"
				+ "#ENDIF"
					+ ", and as the transformation finally comes to an end, [npc.sheIs] left panting and sighing as [npc.she] [npc.verb(try)] to catch [npc.her] breath.<br/>"
				+ "[npc.Name] now [npc.has] an anthropomorphic [style.boldCatMorph(cat-like face)], [npc.materialDescriptor] [npc.faceFullDescription]."
				+ " Within [npc.her] [npc.mouth], [npc.she] has a [style.boldCatMorph(flat, cat-like tongue)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], anthropomorphic, cat-like face [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)] and complete with a cute little feline muzzle.",
			Util.newArrayListOfValues(
					FaceTypeTag.MUZZLE,
					FaceTypeTag.FANGS,
					FaceTypeTag.NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType CAT_MORPH_PANTHER = new AbstractFaceType(BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			FaceStructure.CAT_MORPH_PANTHER,
			MouthType.CAT_MORPH,
			NoseType.CAT_MORPH_PANTHER,
			"[npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic panther-like muzzle, and [npc.her] tongue flattens and transforms into a cat-like one."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face"
				+ "#ELSE"
					+ " Just like the rest of [npc.her] body, [npc.her] new face is [npc.materialDescriptor] [npc.faceSkin+]"
				+ "#ENDIF"
					+ ", and as the transformation finally comes to an end, [npc.sheIs] left panting and sighing as [npc.she] [npc.verb(try)] to catch [npc.her] breath.<br/>"
				+ "[npc.Name] now [npc.has] an anthropomorphic [style.boldCatMorph(panther-like face)], [npc.materialDescriptor] [npc.faceFullDescription]."
				+ " Within [npc.her] [npc.mouth], [npc.she] has a [style.boldCatMorph(flat, cat-like tongue)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], anthropomorphic, panther-like face [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)] and complete with a powerful, toothy muzzle, big nose, and strong jawline.",
			Util.newArrayListOfValues(
					FaceTypeTag.MUZZLE,
					FaceTypeTag.FANGS,
					FaceTypeTag.NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType COW_MORPH = new AbstractFaceType(BodyCoveringType.BOVINE_FUR,
			Race.COW_MORPH,
			FaceStructure.COW_MORPH,
			MouthType.COW_MORPH,
			NoseType.COW_MORPH,
			"[npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic bovine-like muzzle, and [npc.her] tongue transforms into a strong, cow-like one."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face"
				+ "#ELSE"
					+ " Just like the rest of [npc.her] body, [npc.her] new face is [npc.materialDescriptor] [npc.faceSkin+]"
				+ "#ENDIF"
					+ ", and as the transformation finally comes to an end, [npc.sheIs] left panting and sighing as [npc.she] [npc.verb(try)] to catch [npc.her] breath.<br/>"
				+ "[npc.Name] now [npc.has] an anthropomorphic [style.boldCowMorph(cow-like face)], [npc.materialDescriptor] [npc.faceFullDescription]."
				+ " Within [npc.her] [npc.mouth], [npc.she] has a [style.boldCowMorph(strong, cow-like tongue)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], anthropomorphic, cow-like face [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)] and complete with a bovine muzzle.",
			Util.newArrayListOfValues(
					FaceTypeTag.MUZZLE,
					FaceTypeTag.NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType DOG_MORPH = new AbstractFaceType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			FaceStructure.DOG_MORPH,
			MouthType.DOG_MORPH,
			NoseType.DOG_MORPH,
			"[npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic canine muzzle, and [npc.her] tongue flattens and grows wider, turning into a dog-like one."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face"
				+ "#ELSE"
					+ " Just like the rest of [npc.her] body, [npc.her] new face is [npc.materialDescriptor] [npc.faceSkin+]"
				+ "#ENDIF"
					+ ", and as the transformation finally comes to an end, [npc.sheIs] left panting and sighing as [npc.she] [npc.verb(try)] to catch [npc.her] breath.<br/>"
				+ "[npc.Name] now [npc.has] an anthropomorphic [style.boldDogMorph(dog-like face)], [npc.materialDescriptor] [npc.faceFullDescription]."
				+ " Within [npc.her] [npc.mouth], [npc.she] has a [style.boldDogMorph(flat, dog-like tongue)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], anthropomorphic, dog-like face [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)] and complete with a canine muzzle.",
			Util.newArrayListOfValues(
					FaceTypeTag.MUZZLE,
					FaceTypeTag.FANGS,
					FaceTypeTag.NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType FOX_MORPH = new AbstractFaceType(BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			FaceStructure.FOX_MORPH,
			MouthType.FOX_MORPH,
			NoseType.FOX_MORPH,
			"[npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic vulpine muzzle, and [npc.her] tongue flattens and transforms into a fox-like one."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face"
				+ "#ELSE"
					+ " Just like the rest of [npc.her] body, [npc.her] new face is [npc.materialDescriptor] [npc.faceSkin+]"
				+ "#ENDIF"
					+ ", and as the transformation finally comes to an end, [npc.sheIs] left panting and sighing as [npc.she] [npc.verb(try)] to catch [npc.her] breath.<br/>"
				+ "[npc.Name] now [npc.has] an anthropomorphic [style.boldFoxMorph(fox-like face)], [npc.materialDescriptor] [npc.faceFullDescription]."
				+ " Within [npc.her] [npc.mouth], [npc.she] has a [style.boldFoxMorph(flat, fox-like tongue)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], anthropomorphic, fox-like face [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)] and complete with a slender, vulpine muzzle.",
			Util.newArrayListOfValues(
					FaceTypeTag.MUZZLE,
					FaceTypeTag.FANGS,
					FaceTypeTag.NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType HARPY = new AbstractFaceType(BodyCoveringType.FEATHERS,
			Race.HARPY,
			FaceStructure.HARPY,
			MouthType.HARPY,
			NoseType.HARPY,			
			"[npc.Her] nose and mouth twitch and transform as they fuse together and push out into a short beak, and [npc.her] tongue thins down, turning into a bird-like one."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face"
				+ "#ELSE"
					+ " Just like the rest of [npc.her] body, [npc.her] new face is [npc.materialDescriptor] [npc.faceSkin+]"
				+ "#ENDIF"
					+ ", and as the transformation finally comes to an end, [npc.sheIs] left panting and sighing as [npc.she] [npc.verb(try)] to catch [npc.her] breath."
				+ " [npc.She] [npc.verb(find)], much to [npc.her] relief, that [npc.sheIs] able to harden or soften the edges of [npc.her] beak at will,"
					+ " allowing [npc.herHim] to portray facial emotions as well as to wrap [npc.her] beak's edges around anything [npc.she] might want to put in [npc.her] mouth<br/>"
				+ "[npc.Name] now has an anthropomorphic [style.boldHarpy(bird-like face)], [npc.materialDescriptor] [npc.faceFullDescription]."
				+ " Within [npc.her] [npc.mouth], [npc.she] has a [style.boldHarpy(thin, bird-like tongue)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], anthropomorphic, bird-like face [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)] and complete with a beak.",
			Util.newArrayListOfValues(
					FaceTypeTag.BEAK,
					FaceTypeTag.NATURAL_BALDNESS_AVIAN
				)){
	};

	public static AbstractFaceType HORSE_MORPH = new AbstractFaceType(BodyCoveringType.HORSE_HAIR,
			Race.HORSE_MORPH,
			FaceStructure.HORSE_MORPH,
			MouthType.HORSE_MORPH,
			NoseType.HORSE_MORPH,
			"[npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic equine muzzle, and [npc.her] tongue transforms into a strong, horse-like one."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face"
				+ "#ELSE"
					+ " Just like the rest of [npc.her] body, [npc.her] new face is [npc.materialDescriptor] [npc.faceSkin+]"
				+ "#ENDIF"
					+ ", and as the transformation finally comes to an end, [npc.sheIs] left panting and sighing as [npc.she] [npc.verb(try)] to catch [npc.her] breath.<br/>"
				+ "[npc.Name] now [npc.has] an anthropomorphic [style.boldHorseMorph(horse-like face)], [npc.materialDescriptor] [npc.faceFullDescription]."
				+ " Within [npc.her] [npc.mouth], [npc.she] has a [style.boldHorseMorph(strong, horse-like tongue)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], anthropomorphic, horse-like face [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)] and complete with a long, equine muzzle.",
			Util.newArrayListOfValues(
					FaceTypeTag.MUZZLE,
					FaceTypeTag.NATURAL_BALDNESS_FURRY // Note: Some horse races only have hair on the neck aka a mane so its not totally unnatural to have a bald face
				)){
	};

	public static AbstractFaceType RABBIT_MORPH = new AbstractFaceType(BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			FaceStructure.RABBIT_MORPH,
			MouthType.RABBIT_MORPH,
			NoseType.RABBIT_MORPH,
			"[npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic rabbit-like muzzle, and [npc.her] tongue transforms into a thin, rabbit-like one."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face"
				+ "#ELSE"
					+ " Just like the rest of [npc.her] body, [npc.her] new face is [npc.materialDescriptor] [npc.faceSkin+]"
				+ "#ENDIF"
					+ ", and as the transformation finally comes to an end, [npc.sheIs] left panting and sighing as [npc.she] [npc.verb(try)] to catch [npc.her] breath.<br/>"
				+ "[npc.Name] now [npc.has] an anthropomorphic [style.boldRabbitMorph(rabbit-like face)], [npc.materialDescriptor] [npc.faceFullDescription]."
				+ " Within [npc.her] [npc.mouth], [npc.she] has a [style.boldRabbitMorph(thin, rabbit-like tongue)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], anthropomorphic, rabbit-like face [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)] and complete with a short muzzle.",
			Util.newArrayListOfValues(
					FaceTypeTag.MUZZLE,
					FaceTypeTag.NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType RAT_MORPH = new AbstractFaceType(BodyCoveringType.RAT_FUR,
			Race.RAT_MORPH,
			FaceStructure.RAT_MORPH,
			MouthType.RAT_MORPH,
			NoseType.RAT_MORPH,
			"[npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic rat-like muzzle, and [npc.her] tongue transforms into a thin, rat-like one."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face"
				+ "#ELSE"
					+ " Just like the rest of [npc.her] body, [npc.her] new face is [npc.materialDescriptor] [npc.faceSkin+]"
				+ "#ENDIF"
					+ ", and as the transformation finally comes to an end, [npc.sheIs] left panting and sighing as [npc.she] [npc.verb(try)] to catch [npc.her] breath.<br/>"
				+ "[npc.Name] now [npc.has] an anthropomorphic [style.boldRatMorph(rat-like face)], [npc.materialDescriptor] [npc.faceFullDescription]."
				+ " Within [npc.her] [npc.mouth], [npc.she] has a [style.boldRatMorph(thin, rat-like tongue)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], anthropomorphic, rat-like face [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)] and complete with a long, rodent muzzle.",
			Util.newArrayListOfValues(
					FaceTypeTag.MUZZLE,
					FaceTypeTag.NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType REINDEER_MORPH = new AbstractFaceType(BodyCoveringType.REINDEER_FUR,
			Race.REINDEER_MORPH,
			FaceStructure.REINDEER_MORPH,
			MouthType.REINDEER_MORPH,
			NoseType.REINDEER_MORPH,
			"[npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic reindeer muzzle, and [npc.her] tongue transforms into a strong, reindeer-like one."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face"
				+ "#ELSE"
					+ " Just like the rest of [npc.her] body, [npc.her] new face is [npc.materialDescriptor] [npc.faceSkin+]"
				+ "#ENDIF"
					+ ", and as the transformation finally comes to an end, [npc.sheIs] left panting and sighing as [npc.she] [npc.verb(try)] to catch [npc.her] breath.<br/>"
				+ "[npc.Name] now [npc.has] an anthropomorphic [style.boldReindeerMorph(horse-reindeer face)], [npc.materialDescriptor] [npc.faceFullDescription]."
				+ " Within [npc.her] [npc.mouth], [npc.she] has a [style.boldReindeerMorph(strong, reindeer-like tongue)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], anthropomorphic, reindeer-like face [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)] and complete with a long muzzle.",
			Util.newArrayListOfValues(
					FaceTypeTag.MUZZLE,
					FaceTypeTag.NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType SQUIRREL_MORPH = new AbstractFaceType(BodyCoveringType.SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			FaceStructure.SQUIRREL_MORPH,
			MouthType.SQUIRREL_MORPH,
			NoseType.SQUIRREL_MORPH,
			"[npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic squirrel-like muzzle, and [npc.her] tongue transforms into a thin, squirrel-like one."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face"
				+ "#ELSE"
					+ " Just like the rest of [npc.her] body, [npc.her] new face is [npc.materialDescriptor] [npc.faceSkin+]"
				+ "#ENDIF"
					+ ", and as the transformation finally comes to an end, [npc.sheIs] left panting and sighing as [npc.she] [npc.verb(try)] to catch [npc.her] breath.<br/>"
				+ "[npc.Name] now [npc.has] an anthropomorphic [style.boldSquirrelMorph(squirrel-like face)], [npc.materialDescriptor] [npc.faceFullDescription]."
				+ " Within [npc.her] [npc.mouth], [npc.she] has a [style.boldSquirrelMorph(thin, squirrel-like tongue)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], anthropomorphic, squirrel-like face [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)] and complete with a short muzzle.",
			Util.newArrayListOfValues(
					FaceTypeTag.MUZZLE,
					FaceTypeTag.NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType WOLF_MORPH = new AbstractFaceType(BodyCoveringType.LYCAN_FUR,
			Race.WOLF_MORPH,
			FaceStructure.WOLF_MORPH,
			MouthType.WOLF_MORPH,
			NoseType.WOLF_MORPH,
			"[npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic lupine muzzle, and [npc.her] tongue flattens and grows wider, turning into a wolf-like one."
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face"
				+ "#ELSE"
					+ " Just like the rest of [npc.her] body, [npc.her] new face is [npc.materialDescriptor] [npc.faceSkin+]"
				+ "#ENDIF"
					+ ", and as the transformation finally comes to an end, [npc.sheIs] left panting and sighing as [npc.she] [npc.verb(try)] to catch [npc.her] breath.<br/>"
				+ "[npc.Name] now [npc.has] an anthropomorphic [style.boldWolfMorph(wolf-like face)], [npc.materialDescriptor] [npc.faceFullDescription]."
				+ " Within [npc.her] [npc.mouth], [npc.she] has a [style.boldWolfMorph(flat, wolf-like tongue)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], anthropomorphic, wolf-like face [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)] and complete with a long muzzle.",
			Util.newArrayListOfValues(
					FaceTypeTag.MUZZLE,
					FaceTypeTag.FANGS,
					FaceTypeTag.NATURAL_BALDNESS_FURRY
				)){
	};
	

	private static List<AbstractFaceType> allFaceTypes;
	private static Map<AbstractFaceType, String> faceToIdMap = new HashMap<>();
	private static Map<String, AbstractFaceType> idToFaceMap = new HashMap<>();
	
	static {
		allFaceTypes = new ArrayList<>();
		
		// Add in hard-coded face types:
		Field[] fields = FaceType.class.getFields();
		
		for(Field f : fields){
			if (AbstractFaceType.class.isAssignableFrom(f.getType())) {
				
				AbstractFaceType ct;
				try {
					ct = ((AbstractFaceType) f.get(null));

					faceToIdMap.put(ct, f.getName());
					idToFaceMap.put(f.getName(), ct);
					
					allFaceTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static AbstractFaceType getFaceTypeFromId(String id) {
		if(id.equals("IMP")) {
			return FaceType.DEMON_COMMON;
		}
		if(id.equals("LYCAN")) {
			return FaceType.WOLF_MORPH;
		}
		if(id.equals("TENGU")) {
			return FaceType.HARPY;
		}
		
		id = Util.getClosestStringMatch(id, idToFaceMap.keySet());
		return idToFaceMap.get(id);
	}
	
	public static String getIdFromFaceType(AbstractFaceType faceType) {
		return faceToIdMap.get(faceType);
	}
	
	public static List<AbstractFaceType> getAllFaceTypes() {
		return allFaceTypes;
	}
	
	private static Map<Race, List<AbstractFaceType>> typesMap = new HashMap<>();
	
	public static List<AbstractFaceType> getFaceTypes(Race r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractFaceType> types = new ArrayList<>();
		for(AbstractFaceType type : FaceType.getAllFaceTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}