package com.lilithsthrone.game.character.body.types;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractPenisType;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.3.8.9
 * @author Innoxia
 */
public class PenisType {
	
	public static AbstractPenisType NONE = new AbstractPenisType(null,
			Race.NONE,
			TesticleType.NONE,
			"[npc.She] [npc.verb(squirm)] and [npc.moansVerb] as [npc.her] cock and balls rapidly shrink away, and within seconds, nothing's left to remind [npc.herHim] of [npc.her] manhood.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldSex(no penis)].",
			"", // Shouldn't need a description of no cock.
			null) {
		@Override
		public String applyAdditionalTransformationEffects(GameCharacter owner, boolean applicationAfterChangeApplied) {
			if(!applicationAfterChangeApplied) {
				owner.setPiercedPenis(false);
			}
			return "";
		}
	};

	public static AbstractPenisType DILDO = new AbstractPenisType(BodyCoveringType.DILDO,
			Race.NONE,
			TesticleType.DILDO,
			Util.newArrayListOfValues("dildo"),
			Util.newArrayListOfValues("dildos"),
			Util.newArrayListOfValues("dildo"),
			Util.newArrayListOfValues("dildos"),
			Util.newArrayListOfValues("rubber", "rubbery", "silicone", "artificial"),
			"You have somehow transformed your penis into a dildo, which is a bug. (Please let Innoxia know!)", // Dildos are not a transformable option
			"[npc.She] [npc.is] currently wearing [npc.a_cockGirth], [npc.cockLengthValue] dildo, which is made out of [npc.cockFullDescription(true)].",
			null) {
	};
	
	public static AbstractPenisType HUMAN = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.HUMAN,
			TesticleType.HUMAN,
			"[npc.She] now [npc.has] a [style.boldHuman(human penis)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>"
				+ "[npc.She] [npc.has] [style.boldHuman([npc.ballsCount]#IF(npc.isInternalTesticles()) internal,#ENDIF human balls)],"
					+ " [npc.materialDescriptor] [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldHuman(human cum)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] human cock, which is [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			null) {
	};

	public static AbstractPenisType ANGEL = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.ANGEL,
			TesticleType.ANGEL,
			Util.newArrayListOfValues("angel-"),
			Util.newArrayListOfValues("angel-"),
			Util.newArrayListOfValues("angel-"),
			Util.newArrayListOfValues("angel-"),
			Util.newArrayListOfValues(""),
			"[npc.She] now [npc.has] an [style.boldAngel(angelic penis)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>"
				+ "[npc.She] [npc.has] [style.boldAngel([npc.ballsCount]#IF(npc.isInternalTesticles()) internal,#ENDIF angelic balls)],"
					+ " [npc.materialDescriptor] [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldAngel(angel cum)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] angel cock, which is [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			null) {
	};

	public static AbstractPenisType DEMON_COMMON = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.DEMON,
			TesticleType.DEMON_COMMON,
			Util.newArrayListOfValues("succubus-"),
			Util.newArrayListOfValues("incubus-"),
			Util.newArrayListOfValues("succubus-"),
			Util.newArrayListOfValues("incubus-"),
			Util.newArrayListOfValues("demonic"),
			"[npc.She] [npc.verb(squirm)] and [npc.moansVerb] as the skin covering [npc.her] cock transforms into a smooth, highly sensitive demonic counterpart."
				+ " Slimy precum starts drooling from the tip, and [npc.she] [npc.verb(let)] out [npc.a_moan+] as thick ridges suddenly press out all along its length."
				+ " As if that wasn't enough, rows of little bumps start to press out and form into little tentacles, which then start wriggling with a mind of their own.<br/>"
				+ "[npc.She] now [npc.has] a"
				+ "#IF(npc.isShortStature())"
					+ " [style.boldImp(impish penis)]"
				+ "#ELSE"
					+ " [style.boldDemon(demonic penis)]"
				+ "#ENDIF"
				+ ", [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>"
				+ "[npc.She] now [npc.has]"
					+ "#IF(npc.isShortStature())"
						+ " [style.boldImp([npc.ballsCount]#IF(npc.isInternalTesticles()) internal,#ENDIF impish balls)], [npc.materialDescriptor] [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldImp(imp cum)]."
					+ "#ELSE"
						+ " [style.boldDemon([npc.ballsCount]#IF(npc.isInternalTesticles()) internal,#ENDIF demonic balls)], [npc.materialDescriptor] [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldDemon(demon cum)]."
					+ "#ENDIF",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] demonic cock, which is [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.RIBBED,
				PenetrationModifier.TENTACLED,
				PenetrationModifier.PREHENSILE)) {
	};

	public static AbstractPenisType COW_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.COW_MORPH,
			TesticleType.BOVINE,
			Util.newArrayListOfValues("cow-"),
			Util.newArrayListOfValues("bull-"),
			Util.newArrayListOfValues("cow-"),
			Util.newArrayListOfValues("bull-"),
			Util.newArrayListOfValues(""),
			"Letting out an involuntary moan, [npc.name] [npc.verb(feel)] [npc.her] penis shifting into a new form, and [npc.sheIs] hit by a wave of overwhelming arousal as [npc.her] shaft grows wider and the head tapers down into a point.<br/>"
				+ "[npc.She] now [npc.has] a [style.boldCowMorph(bovine penis)], [npc.materialDescriptor] [npc.penisFullDescription].<br/>"
				+ "[npc.She] [npc.has] [style.boldCowMorph([npc.ballsCount]#IF(npc.isInternalTesticles()) internal,#ENDIF bovine balls)],"
					+ " [npc.materialDescriptor] [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldCowMorph(bovine cum)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] #IF(npc.isFeminine())cow#ELSEbull#ENDIF cock, which is [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.TAPERED,
				PenetrationModifier.VEINY,
				PenetrationModifier.SHEATHED)) {
	};
	
	public static AbstractPenisType DOG_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.DOG_MORPH,
			TesticleType.CANINE,
			Util.newArrayListOfValues("dog-", "bitch-"),
			Util.newArrayListOfValues("dog-"),
			Util.newArrayListOfValues("dog-", "bitch-"),
			Util.newArrayListOfValues("dog-"),
			Util.newArrayListOfValues("canine"),
			"Letting out an involuntary moan, [npc.name] [npc.verb(feel)] [npc.her] penis shifting into a new form, and [npc.sheIs] hit by a wave of overwhelming arousal as a thick knot suddenly presses out at the base of [npc.her] shaft."
				+ " As [npc.she] [npc.verb(pant)] and [npc.verb(gasp)] for air, the tip of [npc.her] cock narrows down as it tapers into its new form.<br/>"
				+ "[npc.She] now [npc.has] a [style.boldDogMorph(canine penis)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>"
				+ "[npc.She] [npc.has] [style.boldDogMorph([npc.ballsCount]#IF(npc.isInternalTesticles()) internal,#ENDIF canine balls)],"
					+ " [npc.materialDescriptor] [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldDogMorph(canine cum)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] dog cock, which is [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.KNOTTED,
				PenetrationModifier.SHEATHED,
				PenetrationModifier.TAPERED)) {
		@Override
		public String applyAdditionalTransformationEffects(GameCharacter owner, boolean applicationAfterChangeApplied) {
			if(applicationAfterChangeApplied) {
				return owner.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED), false);
			}
			return "";
		}
	};
	
	public static AbstractPenisType WOLF_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.WOLF_MORPH,
			TesticleType.LUPINE,
			Util.newArrayListOfValues("wolf-"),
			Util.newArrayListOfValues("wolf-"),
			Util.newArrayListOfValues("wolf-"),
			Util.newArrayListOfValues("wolf-"),
			Util.newArrayListOfValues("lupine"),
			"Letting out an involuntary moan, [npc.name] [npc.verb(feel)] [npc.her] penis shifting into a new form, and [npc.sheIs] hit by a wave of overwhelming arousal as a thick knot suddenly presses out at the base of [npc.her] shaft."
				+ " As [npc.she] [npc.verb(pant)] and [npc.verb(gasp)] for air, the tip of [npc.her] cock narrows down as it tapers into its new form.<br/>"
				+ "[npc.She] now [npc.has] a [style.boldWolfMorph(wolf-like penis)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>"
				+ "[npc.She] [npc.has] [style.boldWolfMorph([npc.ballsCount]#IF(npc.isInternalTesticles()) internal,#ENDIF lupine balls)],"
					+ " [npc.materialDescriptor] [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldWolfMorph(wolf cum)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] wolf cock, which is [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.KNOTTED,
				PenetrationModifier.SHEATHED,
				PenetrationModifier.TAPERED)) {
		@Override
		public String applyAdditionalTransformationEffects(GameCharacter owner, boolean applicationAfterChangeApplied) {
			if(applicationAfterChangeApplied) {
				return owner.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED), false);
			}
			return "";
		}
	};
	
	public static AbstractPenisType FOX_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.FOX_MORPH,
			TesticleType.FOX_MORPH,
			Util.newArrayListOfValues("fox-", "vixen-"),
			Util.newArrayListOfValues("fox-"),
			Util.newArrayListOfValues("fox-", "vixen-"),
			Util.newArrayListOfValues("fox-"),
			Util.newArrayListOfValues("vulpine"),
			"Letting out an involuntary moan, [npc.name] [npc.verb(feel)] [npc.her] penis shifting into a new form, and [npc.sheIs] hit by a wave of overwhelming arousal as a thick knot suddenly presses out at the base of [npc.her] shaft."
				+ " As [npc.she] [npc.verb(pant)] and [npc.verb(gasp)] for air, the tip of [npc.her] cock narrows down as it tapers into its new form.<br/>"
				+ "[npc.She] now [npc.has] a [style.boldFoxMorph(fox-like penis)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>"
				+ "[npc.She] [npc.has] [style.boldFoxMorph([npc.ballsCount]#IF(npc.isInternalTesticles()) internal,#ENDIF vulpine balls)],"
					+ " [npc.materialDescriptor] [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldFoxMorph(fox cum)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] fox cock, which is [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.KNOTTED,
				PenetrationModifier.SHEATHED,
				PenetrationModifier.TAPERED)) {
		@Override
		public String applyAdditionalTransformationEffects(GameCharacter owner, boolean applicationAfterChangeApplied) {
			if(applicationAfterChangeApplied) {
				return owner.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED), false);
			}
			return "";
		}
	};

	public static AbstractPenisType CAT_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.CAT_MORPH,
			TesticleType.FELINE,
			Util.newArrayListOfValues("cat-"),
			Util.newArrayListOfValues("cat-"),
			Util.newArrayListOfValues("cat-"),
			Util.newArrayListOfValues("cat-"),
			Util.newArrayListOfValues("feline"),
			"Letting out an involuntary moan, [npc.she] [npc.verb(feel)] [npc.her] penis shifting into a new form,"
					+ " and [npc.sheIs] hit by a wave of overwhelming arousal as rows of fleshy little backwards-facing barbs press out all along [npc.her] shaft.<br/>"
				+ "[npc.She] now [npc.has] a [style.boldCatMorph(feline penis)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>"
				+ "[npc.She] [npc.has] [style.boldCatMorph([npc.ballsCount]#IF(npc.isInternalTesticles()) internal,#ENDIF feline balls)],"
					+ " [npc.materialDescriptor] [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldCatMorph(feline cum)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] cat cock, which is [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.BARBED,
				PenetrationModifier.SHEATHED)) {
	};

	public static AbstractPenisType ALLIGATOR_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.ALLIGATOR_MORPH,
			TesticleType.ALLIGATOR_MORPH,
			Util.newArrayListOfValues("alligator-"),
			Util.newArrayListOfValues("alligator-"),
			Util.newArrayListOfValues("alligator-"),
			Util.newArrayListOfValues("alligator-"),
			Util.newArrayListOfValues("reptilian"),
			"Letting out an involuntary moan, [npc.name] [npc.verb(feel)] [npc.her] penis shifting into a new form, and [npc.sheIs] hit by a wave of overwhelming arousal as [npc.her] shaft grows erect and the head smoothes over.<br/>"
				+ "[npc.She] now [npc.has] an [style.boldGatorMorph(alligator penis)], [npc.materialDescriptor] [npc.penisFullDescription].<br/>"
				+ "[npc.She] [npc.has] [style.boldGatorMorph([npc.ballsCount]#IF(npc.isInternalTesticles()) internal,#ENDIF reptilian balls)],"
						+ " [npc.materialDescriptor] [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldGatorMorph(alligator-morph cum)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] alligator cock, which is [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.BLUNT)) {
	};

	public static AbstractPenisType EQUINE = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.HORSE_MORPH,
			TesticleType.EQUINE,
			Util.newArrayListOfValues("mare-", "horse-", "equine-"),
			Util.newArrayListOfValues("stallion-", "horse-", "equine-"),
			Util.newArrayListOfValues("mare-", "horse-", "equine-"),
			Util.newArrayListOfValues("stallion-", "horse-", "equine-"),
			Util.newArrayListOfValues(""),
			"Letting out an involuntary moan, [npc.name] [npc.verb(feel)] [npc.her] penis shifting into a new form, and [npc.sheIs] hit by a wave of overwhelming arousal as [npc.her] shaft grows wider and the head flattens down.<br/>"
				+ "[npc.She] now [npc.has] an [style.boldHorseMorph(equine penis)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>"
				+ "[npc.She] [npc.has] [style.boldHorseMorph([npc.ballsCount]#IF(npc.isInternalTesticles()) internal,#ENDIF equine balls)],"
					+ " [npc.materialDescriptor] [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldHorseMorph(equine cum)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] horse cock, which is [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.FLARED,
				PenetrationModifier.VEINY,
				PenetrationModifier.SHEATHED)) {
	};

	public static AbstractPenisType REINDEER_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.REINDEER_MORPH,
			TesticleType.REINDEER_MORPH,
			Util.newArrayListOfValues("reindeer-"),
			Util.newArrayListOfValues("reindeer-"),
			Util.newArrayListOfValues("reindeer-"),
			Util.newArrayListOfValues("reindeer-"),
			Util.newArrayListOfValues(""),
			"Letting out an involuntary moan, [npc.name] [npc.verb(feel)] [npc.her] penis shifting into a new form, and [npc.sheIs] hit by a wave of overwhelming arousal as [npc.her] shaft grows wider and the head flattens down.<br/>"
				+ "[npc.She] now [npc.has] an [style.boldReindeerMorph(reindeer-like penis)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>"
				+ "[npc.She] [npc.has] [style.boldReindeerMorph([npc.ballsCount]#IF(npc.isInternalTesticles()) internal,#ENDIF reindeer balls)],"
					+ " [npc.materialDescriptor] [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldReindeerMorph(reindeer cum)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] reindeer cock, which is [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.TAPERED,
				PenetrationModifier.SHEATHED)) {
	};

	public static AbstractPenisType HARPY = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.HARPY,
			TesticleType.AVIAN,
			Util.newArrayListOfValues("harpy-"),
			Util.newArrayListOfValues("harpy-"),
			Util.newArrayListOfValues("harpy-"),
			Util.newArrayListOfValues("harpy-"),
			Util.newArrayListOfValues("avian"),
			"Letting out an involuntary moan, [npc.name] [npc.verb(feel)] [npc.her] penis shifting into a new form, and [npc.sheIs] hit by a wave of overwhelming arousal as it retreats down into a new sheath that's formed at the base.<br/>"
				+ "[npc.She] now [npc.has] an [style.boldHarpy(avian penis)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>"
				+ "[npc.She] [npc.has] [style.boldHarpy([npc.ballsCount]#IF(npc.isInternalTesticles()) internal,#ENDIF avian balls)], [npc.materialDescriptor] [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldHarpy(bird cum)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] bird cock, which is [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.SHEATHED)) {
	};
	
	public static AbstractPenisType SQUIRREL_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.SQUIRREL_MORPH,
			TesticleType.SQUIRREL,
			Util.newArrayListOfValues("squirrel-"),
			Util.newArrayListOfValues("squirrel-"),
			Util.newArrayListOfValues("squirrel-"),
			Util.newArrayListOfValues("squirrel-"),
			Util.newArrayListOfValues("rodent"),
			"[npc.She] now [npc.has] a [style.boldSquirrelMorph(squirrel-morph's penis)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>"
				+ "[npc.She] [npc.has] [style.boldSquirrelMorph([npc.ballsCount]#IF(npc.isInternalTesticles()) internal,#ENDIF squirrel balls)],"
					+ " [npc.materialDescriptor] [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldSquirrelMorph(squirrel cum)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] squirrel cock, which is [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.SHEATHED)) {
	};
	
	public static AbstractPenisType RAT_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.RAT_MORPH,
			TesticleType.RAT_MORPH,
			Util.newArrayListOfValues("rat-"),
			Util.newArrayListOfValues("rat-"),
			Util.newArrayListOfValues("rat-"),
			Util.newArrayListOfValues("rat-"),
			Util.newArrayListOfValues(""),
			"[npc.She] now [npc.has] a [style.boldRatMorph(rat penis)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>"
				+ "[npc.She] [npc.has] [style.boldRatMorph([npc.ballsCount]#IF(npc.isInternalTesticles()) internal,#ENDIF rat balls)], [npc.materialDescriptor] [npc.ballsFullDescription(true)],"
					+ " which produce [npc.cumColour(true)] [style.boldRatMorph(rat cum)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] rat cock, which is [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.SHEATHED)) {
	};
	
	public static AbstractPenisType RABBIT_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.RABBIT_MORPH,
			TesticleType.RABBIT_MORPH,
			Util.newArrayListOfValues("rabbit-"),
			Util.newArrayListOfValues("rabbit-"),
			Util.newArrayListOfValues("rabbit-"),
			Util.newArrayListOfValues("rabbit-"),
			Util.newArrayListOfValues(""),
			"[npc.She] now [npc.has] a [style.boldRabbitMorph(rabbit penis)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>"
				+ "[npc.She] [npc.has] [style.boldRabbitMorph([npc.ballsCount]#IF(npc.isInternalTesticles()) internal,#ENDIF rabbit balls)],"
					+ " [npc.materialDescriptor] [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldRabbitMorph(rabbit cum)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] rabbit cock, which is [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.SHEATHED)) {
	};
	
	public static AbstractPenisType BAT_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.BAT_MORPH,
			TesticleType.BAT_MORPH,
			Util.newArrayListOfValues("bat-"),
			Util.newArrayListOfValues("bat-"),
			Util.newArrayListOfValues("bat-"),
			Util.newArrayListOfValues("bat-"),
			Util.newArrayListOfValues(""),
			"[npc.She] now [npc.has] a [style.boldBatMorph(bat penis)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>"
				+ "[npc.She] [npc.has] [style.boldBatMorph([npc.ballsCount]#IF(npc.isInternalTesticles()) internal,#ENDIF bat balls)],"
					+ " [npc.materialDescriptor] [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldBatMorph(bat cum)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] bat cock, which is [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.SHEATHED)) {
	};
	
	
	private static List<AbstractPenisType> allPenisTypes;
	private static Map<AbstractPenisType, String> penisToIdMap = new HashMap<>();
	private static Map<String, AbstractPenisType> idToPenisMap = new HashMap<>();
	
	static {
		allPenisTypes = new ArrayList<>();
		
		// Add in hard-coded penis types:
		Field[] fields = PenisType.class.getFields();
		
		for(Field f : fields){
			if (AbstractPenisType.class.isAssignableFrom(f.getType())) {
				
				AbstractPenisType ct;
				try {
					ct = ((AbstractPenisType) f.get(null));

					penisToIdMap.put(ct, f.getName());
					idToPenisMap.put(f.getName(), ct);
					
					allPenisTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static AbstractPenisType getPenisTypeFromId(String id) {
		if(id.equals("IMP")) {
			return PenisType.DEMON_COMMON;
		}
		if(id.equals("BOVINE")) {
			return PenisType.COW_MORPH;
		}
		if(id.equals("CANINE")) {
			return PenisType.DOG_MORPH;
		}
		if(id.equals("LUPINE")) {
			return PenisType.WOLF_MORPH;
		}
		if(id.equals("VULPINE")) {
			return PenisType.FOX_MORPH;
		}
		if(id.equals("FELINE")) {
			return PenisType.CAT_MORPH;
		}
		if(id.equals("AVIAN")) {
			return PenisType.HARPY;
		}
		if(id.equals("SQUIRREL")) {
			return PenisType.SQUIRREL_MORPH;
		}
		id = Util.getClosestStringMatch(id, idToPenisMap.keySet());
		return idToPenisMap.get(id);
	}
	
	public static String getIdFromPenisType(AbstractPenisType penisType) {
		return penisToIdMap.get(penisType);
	}
	
	public static List<AbstractPenisType> getAllPenisTypes() {
		return allPenisTypes;
	}
	
	private static Map<AbstractRace, List<AbstractPenisType>> typesMap = new HashMap<>();
	
	public static List<AbstractPenisType> getPenisTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractPenisType> types = new ArrayList<>();
		for(AbstractPenisType type : PenisType.getAllPenisTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}

}