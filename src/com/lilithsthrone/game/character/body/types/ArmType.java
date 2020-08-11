package com.lilithsthrone.game.character.body.types;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractArmType;
import com.lilithsthrone.game.character.body.tags.ArmTypeTag;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.BodyPartClothingBlock;
import com.lilithsthrone.utils.Util;

/**
 * Contains static instances of AbstractArmType.
 * 
 * @since 0.1.0
 * @version 0.3
 * @author Innoxia
 */
public class ArmType {

	public static AbstractArmType HUMAN = new AbstractArmType(BodyCoveringType.HUMAN,
			Race.HUMAN,
			ArmStructure.HUMAN,
			HandType.HUMAN,
			FingerType.HUMAN,
			"Thankfully, the transformation only lasts a matter of moments, leaving [npc.herHim] with normal-looking human arms, complete with human hands.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldHuman(human arms and hands)], which are [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] normal human arms and hands, which are [npc.materialCompositionDescriptor] [npc.armFullDescription(true)].") {
	};

	public static AbstractArmType ANGEL = new AbstractArmType(BodyCoveringType.HUMAN,
			Race.ANGEL,
			ArmStructure.ANGEL,
			HandType.ANGEL,
			FingerType.ANGEL,
			"Within a matter of moments, they've changed into slender, human-like arms, complete with human-like hands."
				+ " Despite their somewhat-normal appearance, they have a subtle, alluring quality to them that reveals their true angelic nature.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldAngel(angelic arms and hands)], which are [npc.materialDescriptor] [npc.armFullDescription]",
			"[npc.She] [npc.has] [npc.armRows] human-like arms and hands, which are [npc.materialCompositionDescriptor] [npc.armFullDescription(true)].") {
	};

	public static AbstractArmType DEMON_COMMON = new AbstractArmType(BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			ArmStructure.DEMON,
			HandType.DEMON,
			FingerType.DEMON,
			"Within a matter of moments, they've changed into slender, human-like arms, complete with human-like hands."
				+ " Despite their somewhat-normal appearance, they have a subtle, alluring quality to them that reveals their true demonic nature.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldDemon(demonic arms and hands)], which are [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] slender human-looking arms and hands, which are [npc.materialCompositionDescriptor] [npc.armFullDescription(true)].") {
	};

	public static AbstractArmType COW_MORPH = new AbstractArmType(BodyCoveringType.BOVINE_FUR,
			Race.COW_MORPH,
			ArmStructure.FURRY,
			HandType.BOVINE,
			FingerType.BOVINE,
			"Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down,"
					+ " [npc.she] [npc.verb(see)] [npc.her] new hair growing over the backs of [npc.her] hands as tough, hoof-like nails push out in place of regular, human-like ones."
				+ " Despite their appearance, [npc.sheIsFull] relieved to discover that [npc.her] hands have lost none of their dexterity."
				+ " As the transformation comes to an end, [npc.she] [npc.verb(see)] that at [npc.her] upper-biceps, [npc.her] hair smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.<br/>"
				+ "[npc.NameIsFull] left with anthropomorphic, [style.boldCowMorph(cow-like arms and hands)], which are [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] arms, which are [npc.materialCompositionDescriptor] [npc.armFullDescription(true)]."
				+ " [npc.Her] hands, while human in shape, have tough little hoof-like nails.") {
	};

	public static AbstractArmType DOG_MORPH = new AbstractArmType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			ArmStructure.FURRY,
			HandType.DOG_MORPH,
			FingerType.DOG_MORPH,
			"Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down,"
					+ " [npc.she] [npc.verb(see)] [npc.her] new fur growing over the backs of [npc.her] hands as blunt, dog-like claws push out to replace [npc.her] fingernails."
				+ " [npc.Her] palms rapidly transform to be [npc.materialDescriptor] little leathery pads, and at [npc.her] upper-biceps, [npc.her] fur smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.<br/>"
				+ "As the transformation comes to an end, [npc.nameIsFull] left with anthropomorphic, [style.boldDogMorph(dog-like arms and hands)], which are [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] arms, which are [npc.materialCompositionDescriptor] [npc.armFullDescription(true)]."
				+ " [npc.Her] hands are formed into anthropomorphic, dog-like hands, complete with little blunt claws and leathery pads.") {
	};

	public static AbstractArmType WOLF_MORPH = new AbstractArmType(BodyCoveringType.LYCAN_FUR,
			Race.WOLF_MORPH,
			ArmStructure.FURRY,
			HandType.WOLF_MORPH,
			FingerType.WOLF_MORPH,
			"Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down,"
					+ " [npc.she] [npc.verb(see)] [npc.her] new fur growing over the backs of [npc.her] hands as sharp claws push out to replace [npc.her] fingernails."
				+ " [npc.Her] palms rapidly transform to be [npc.materialDescriptor] tough leathery pads, and at [npc.her] upper-biceps, [npc.her] fur smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.<br/>"
				+ "As the transformation comes to an end, [npc.nameIsFull] left with anthropomorphic, [style.boldWolfMorph(wolf-like arms and hands)], which are [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] arms, which are [npc.materialCompositionDescriptor] [npc.armFullDescription(true)]."
				+ " [npc.Her] hands are formed into anthropomorphic, wolf-like hands, complete with sharp claws and tough leathery pads.") {
	};

	public static AbstractArmType FOX_MORPH = new AbstractArmType(BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			ArmStructure.FURRY,
			HandType.FOX_MORPH,
			FingerType.FOX_MORPH,
			"Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down,"
					+ " [npc.she] [npc.verb(see)] [npc.her] new fur growing over the backs of [npc.her] hands as sharp claws push out to replace [npc.her] fingernails."
				+ " [npc.Her] palms rapidly transform to be [npc.materialDescriptor] little pads, and at [npc.her] upper-biceps, [npc.her] fur smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.</br>"
				+ "As the transformation comes to an end, [npc.nameIsFull] left with anthropomorphic, [style.boldFoxMorph(fox-like arms and hands)], which are [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] arms, which are [npc.materialCompositionDescriptor] [npc.armFullDescription(true)]."
				+ " [npc.Her] hands are formed into anthropomorphic, fox-like hands, complete with sharp claws and tough leathery pads.") {
	};

	public static AbstractArmType CAT_MORPH = new AbstractArmType(BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			ArmStructure.FURRY,
			HandType.CAT_MORPH,
			FingerType.CAT_MORPH,
			"Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down,"
					+ " [npc.she] [npc.verb(see)] [npc.her] fur growing over the backs of [npc.her] hands as sharp, retractable claws push out to replace [npc.her] fingernails."
				+ " [npc.Her] palms rapidly transform to be [npc.materialDescriptor] little pink pads, and at [npc.her] upper-biceps, [npc.her] fur smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.<br/>"
				+ "As the transformation comes to an end, [npc.nameIsFull] left with anthropomorphic, [style.boldCatMorph(cat-like arms and hands)], which are [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] arms, which are [npc.materialCompositionDescriptor] [npc.armFullDescription(true)]."
				+ " [npc.Her] hands are formed into anthropomorphic, cat-like hands, complete with retractable claws and pink pads.") {
	};

	public static AbstractArmType HORSE_MORPH = new AbstractArmType(BodyCoveringType.HORSE_HAIR,
			Race.HORSE_MORPH,
			ArmStructure.HAIR_COATED,
			HandType.EQUINE,
			FingerType.EQUINE,
			"Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down,"
					+ " [npc.she] [npc.verb(see)] [npc.her] new hair growing over the backs of [npc.her] hands as tough, hoof-like nails push out in place of regular, human-like ones."
				+ " Despite their appearance, [npc.sheIs] relieved to discover that [npc.her] hands have lost none of their dexterity."
				+ " As the transformation comes to an end, [npc.she] [npc.verb(see)] that at [npc.her] upper-biceps, [npc.her] hair smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.<br/>"
				+ "[npc.NameIsFull] left with anthropomorphic, [style.boldHorseMorph(horse-like arms and hands)], which are [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] arms, which are [npc.materialCompositionDescriptor] [npc.armFullDescription(true)]."
				+ " [npc.Her] hands, while human in shape, have tough little hoof-like nails.") {
	};

	public static AbstractArmType REINDEER_MORPH = new AbstractArmType(BodyCoveringType.REINDEER_FUR,
			Race.REINDEER_MORPH,
			ArmStructure.HAIR_COATED,
			HandType.REINDEER_MORPH,
			FingerType.REINDEER_MORPH,
			"Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down,"
					+ " [npc.she] [npc.verb(see)] [npc.her] new fur growing over the backs of [npc.her] hands as tough, hoof-like nails push out in place of regular, human-like ones."
				+ " Despite their appearance, [npc.sheIs] relieved to discover that [npc.her] hands have lost none of their dexterity."
				+ " As the transformation comes to an end, [npc.she] [npc.verb(see)] that at [npc.her] upper-biceps, [npc.her] fur smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.<br/>"
				+ "[npc.NameIsFull] left with anthropomorphic, [style.boldReindeerMorph(reindeer-like arms and hands)], which are [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] arms, which are [npc.materialCompositionDescriptor] [npc.armFullDescription(true)]."
				+ " [npc.Her] hands, while human in shape, have tough little hoof-like nails.") {
	};

	public static AbstractArmType ALLIGATOR_MORPH = new AbstractArmType(BodyCoveringType.ALLIGATOR_SCALES,
			Race.ALLIGATOR_MORPH,
			ArmStructure.REPTILE,
			HandType.REPTILE,
			FingerType.REPTILE,
			"Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down,"
				+ " [npc.she] [npc.verb(see)] [npc.her] new scales growing over the backs of [npc.her] hands as sharp claws push out to replace [npc.her] fingernails."
				+ " [npc.Her] palms rapidly transform to be [npc.materialDescriptor] little scales, and at [npc.her] upper-biceps, [npc.her] scales smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.<br/>"
				+ "As the transformation comes to an end, [npc.nameIsFull] left with anthropomorphic, [style.boldGatorMorph(alligator-like arms and hands)], which are [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] arms, which are [npc.materialCompositionDescriptor] [npc.armFullDescription(true)]."
				+ " [npc.Her] hands are formed into anthropomorphic, alligator-like hands, complete with little claws.") {
	};

	public static AbstractArmType SQUIRREL_MORPH = new AbstractArmType(BodyCoveringType.SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			ArmStructure.FURRY,
			HandType.SQUIRREL_MORPH,
			FingerType.RODENT,
			"Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down,"
					+ " [npc.she] [npc.verb(see)] [npc.her] new fur growing over the backs of [npc.her] hands as sharp little claws push out to replace [npc.her] fingernails."
				+ " [npc.Her] palms rapidly transform to be [npc.materialDescriptor] little pink pads, and at [npc.her] upper-biceps, [npc.her] fur smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.<br/>"
				+ "As the transformation comes to an end, [npc.nameIsFull] left with anthropomorphic, [style.boldSquirrelMorph(squirrel-like arms and hands)], which are [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] arms, which are [npc.materialCompositionDescriptor] [npc.armFullDescription(true)]."
				+ " [npc.Her] hands are formed into anthropomorphic, squirrel-like hands, complete with claws.") {
	};

	public static AbstractArmType RAT_MORPH = new AbstractArmType(BodyCoveringType.RAT_FUR,
			Race.RAT_MORPH,
			ArmStructure.FURRY,
			HandType.RAT_MORPH,
			FingerType.RAT_MORPH,
			"Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down,"
					+ " [npc.she] [npc.verb(see)] [npc.her] fur growing over the backs of [npc.her] hands as sharp little claws push out to replace [npc.her] fingernails."
				+ " [npc.Her] palms rapidly transform to be [npc.materialDescriptor] little pink pads, and at [npc.her] upper-biceps, [npc.her] new fur smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.<br/>"
				+ "As the transformation comes to an end, [npc.nameIsFull] left with anthropomorphic, [style.boldRatMorph(rat-like arms and hands)], which are [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] arms, which are [npc.materialCompositionDescriptor] [npc.armFullDescription(true)]."
				+ " [npc.Her] hands are formed into anthropomorphic, rat-like hands, complete with claws.") {
	};

	public static AbstractArmType RABBIT_MORPH = new AbstractArmType(BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			ArmStructure.FURRY,
			HandType.RABBIT_MORPH,
			FingerType.RABBIT_MORPH,
			"Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down,"
					+ " [npc.she] [npc.verb(see)] [npc.her] new fur growing over the backs of [npc.her] hands as blunt, rabbit-like claws push out to replace [npc.her] fingernails."
				+ " [npc.Her] palms rapidly transform to be [npc.materialDescriptor] soft little pads, and at [npc.her] upper-biceps, [npc.her] new fur smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.<br/>"
				+ "As the transformation comes to an end, [npc.nameIsFull] left with anthropomorphic, [style.boldRabbitMorph(rabbit-like arms and paw-like hands)], which are [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] arms, which are [npc.materialCompositionDescriptor] [npc.armFullDescription(true)]."
				+ " [npc.Her] hands are formed into anthropomorphic, rabbit-like hands, complete with blunt little claws.") {
	};

	public static AbstractArmType BAT_MORPH = new AbstractArmType(BodyCoveringType.BAT_SKIN,
			Race.BAT_MORPH,
			ArmStructure.BAT_WINGS,
			HandType.BAT_MORPH,
			FingerType.BAT_MORPH,
			"Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, staring at [npc.her] hands in shock,"
					+ " [npc.name] [npc.verb(watch)] [npc.her] fingers narrowing down and growing longer and longer as a tough membrane of skin starts to grow between them."
				+ " [npc.She] [npc.verb(cry)] out in alarm as [npc.she] [npc.verb(feel)] [npc.her] bones growing and snapping into a new form, and within moments,"
					+ " [npc.her] hands and arms have completely transformed into huge, bat-like wings."
				+ " Where [npc.her] hands once were, two of [npc.her] fingers have shrunk down into the middle-joint of [npc.her] new appendages,"
					+ " leaving [npc.herHim] with two small forefingers and an opposable thumb, each of which ends in a little claw."
				+ " Where [npc.her] new wings meet [npc.her] body at the shoulder, [npc.her] [npc.armFullDescription] smoothly covers the transition into the [npc.skin] that's covering the rest of [npc.her] torso.<br/>"
				+ "[npc.Name] now [npc.has] huge [style.boldBatMorph(bat-like wings)] in place of arms, which are [npc.materialDescriptor] [npc.armFullDescription].",
			"In place of arms and hands, [npc.she] [npc.has] [npc.armRows] huge bat-like wings, [npc.materialCompositionDescriptor] [npc.armFullDescription(true)]."
				+ " Where [npc.her] hands should be, [npc.she] [npc.has] two forefingers and a thumb, each of which ends in a little blunt claw."
				+ " Although slightly less dexterous than a human hand, [npc.sheIs] still able to use [npc.her] digits to form a hand-like grip.") {
		@Override
		public boolean allowsFlight() {
			return true;
		}

		private BodyPartClothingBlock clothingBlock = new BodyPartClothingBlock(
				Util.newArrayListOfValues(
						InventorySlot.HAND,
						InventorySlot.WRIST,
						InventorySlot.TORSO_OVER,
						InventorySlot.TORSO_UNDER),
				Race.BAT_MORPH,
				"Due to the fact that [npc.nameHasFull] leathery wings instead of arms, only specialist clothing can be worn in this slot.",
				Util.newArrayListOfValues(
					ItemTag.FITS_LEATHERY_ARM_WINGS,
					ItemTag.FITS_LEATHERY_ARM_WINGS_EXCLUSIVE,
					ItemTag.FITS_ARM_WINGS,
					ItemTag.FITS_ARM_WINGS_EXCLUSIVE
				));
		@Override
		public BodyPartClothingBlock getBodyPartClothingBlock() {
			return clothingBlock;
		}
		@Override
		public List<ArmTypeTag> getTags() {
			return Util.newArrayListOfValues(ArmTypeTag.WINGS, ArmTypeTag.WINGS_LEATHERY);
		}
	};

	public static AbstractArmType HARPY = new AbstractArmType(BodyCoveringType.FEATHERS,
			Race.HARPY,
			ArmStructure.AVIAN_WINGS,
			HandType.AVIAN_FEATHERED,
			FingerType.AVIAN_FEATHERED,
			"Within a matter of moments, a thick layer of [npc.armFullDescription] quickly sprouts out all over them, and, looking down, [npc.she] [npc.verb(see)] [npc.her] new feathers growing over the backs of [npc.her] hands as well."
				+ " Just as [npc.she] thinks that the transformation has finished, [npc.she] [npc.verb(cry)] out in shock as [npc.her] bones grow and snap into a new form."
				+ " Thankfully, the transformation is quickly over, leaving [npc.herHim] with a pair of huge, feathered wings in place of arms."
				+ " Where [npc.her] hands once were, two of [npc.her] fingers have shrunk down into the middle-joint of [npc.her] appendages, leaving [npc.herHim] with two feathered forefingers and an opposable thumb,"
					+ " each of which ends in a blunt claw."
				+ " Where [npc.her] new wings meet [npc.her] body at the shoulder, [npc.her] feathers smoothly cover the transition into the [npc.skin] that's covering the rest of [npc.her] torso.<br/>"
				+ "[npc.Name] now [npc.has] huge [style.boldHarpy(harpy wings)] in place of arms, which are [npc.materialDescriptor] [npc.armFullDescription].",
			"In place of arms and hands, [npc.she] [npc.has] [npc.armRows] huge wings, which are [npc.materialCompositionDescriptor] beautiful [npc.armFullDescription(true)]."
				+ " Where [npc.her] hands should be, [npc.she] [npc.has] two feathered forefingers and a thumb, each of which ends in a little blunt claw."
				+ " Although slightly less dexterous than a human hand, [npc.sheIs] still able to use [npc.her] digits to form a hand-like grip.") {
		@Override
		public boolean allowsFlight() {
			return true;
		}

		private BodyPartClothingBlock clothingBlock = new BodyPartClothingBlock(
				Util.newArrayListOfValues(
						InventorySlot.HAND,
						InventorySlot.WRIST,
						InventorySlot.TORSO_OVER,
						InventorySlot.TORSO_UNDER),
				Race.HARPY,
				"Due to the fact that [npc.nameHasFull] bird-like wings instead of arms, only specialist clothing can be worn in this slot.",
				Util.newArrayListOfValues(
					ItemTag.FITS_FEATHERED_ARM_WINGS,
					ItemTag.FITS_FEATHERED_ARM_WINGS_EXCLUSIVE,
					ItemTag.FITS_ARM_WINGS,
					ItemTag.FITS_ARM_WINGS_EXCLUSIVE
				));
		@Override
		public BodyPartClothingBlock getBodyPartClothingBlock() {
			return clothingBlock;
		}
		@Override
		public List<ArmTypeTag> getTags() {
			return Util.newArrayListOfValues(ArmTypeTag.WINGS, ArmTypeTag.WINGS_FEATHERED);
		}
	};
	
	
	private static List<AbstractArmType> allArmTypes;
	private static Map<AbstractArmType, String> armToIdMap = new HashMap<>();
	private static Map<String, AbstractArmType> idToArmMap = new HashMap<>();
	
	static {
		allArmTypes = new ArrayList<>();
		
		// Add in hard-coded arm types:
		Field[] fields = ArmType.class.getFields();
		
		for(Field f : fields){
			if (AbstractArmType.class.isAssignableFrom(f.getType())) {
				
				AbstractArmType ct;
				try {
					ct = ((AbstractArmType) f.get(null));

					armToIdMap.put(ct, f.getName());
					idToArmMap.put(f.getName(), ct);
					
					allArmTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static AbstractArmType getArmTypeFromId(String id) {
		if(id.equals("IMP")) {
			return ArmType.DEMON_COMMON;
		}
		if(id.equals("LYCAN")) {
			return ArmType.WOLF_MORPH;
		}

		id = Util.getClosestStringMatch(id, idToArmMap.keySet());
		return idToArmMap.get(id);
	}
	
	public static String getIdFromArmType(AbstractArmType armType) {
		return armToIdMap.get(armType);
	}
	
	public static List<AbstractArmType> getAllArmTypes() {
		return allArmTypes;
	}
	
	private static Map<AbstractRace, List<AbstractArmType>> typesMap = new HashMap<>();
	
	public static List<AbstractArmType> getArmTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractArmType> types = new ArrayList<>();
		for(AbstractArmType type : ArmType.getAllArmTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
	
}
