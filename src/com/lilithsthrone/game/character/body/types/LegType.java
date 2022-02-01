package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractLegType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTentacleType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.1
 * @author Innoxia
 */
public class LegType {
	
	public static AbstractLegType HUMAN = new AbstractLegType(BodyCoveringType.HUMAN,
			Race.HUMAN,
			FootStructure.PLANTIGRADE,
			FootType.HUMANOID,
			"a pair of",
			"leg",
			"legs",
			Util.newArrayListOfValues("masculine"),
			Util.newArrayListOfValues("feminine"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"They rapidly shift into normal-looking human legs, complete with human feet.<br/>"
				+ "[npc.She] now [npc.has] [style.boldHuman(human legs and feet)], which are [npc.materialDescriptor] [npc.legFullDescription].",
			"[npc.Her] legs and feet are human, and are [npc.materialCompositionDescriptor] [npc.legFullDescription(true)].",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL), false) {
	};
	
	public static AbstractLegType ANGEL = new AbstractLegType(BodyCoveringType.ANGEL,
			Race.ANGEL,
			FootStructure.PLANTIGRADE,
			FootType.HUMANOID,
			"a pair of",
			"leg",
			"legs",
			Util.newArrayListOfValues("masculine", "radiant", "angelic"),
			Util.newArrayListOfValues("feminine", "radiant", "angelic"),
			Util.newArrayListOfValues("angelic"),
			Util.newArrayListOfValues("angelic"),
			Util.newArrayListOfValues("angelic"),
			Util.newArrayListOfValues("angelic"),
			"They quickly shift into a pair of smooth, slender legs, and [npc.she] [npc.verb(let)] out a gasp as a layer of flawless, angelic skin rapidly grows to cover them."
				+ " As they finish transforming, [npc.she] almost [npc.verb(lose)] [npc.her] balance as the bones in [npc.her] feet start to shift and rearrange themselves."
				+ " After a moment, they've transformed into slender, human-like feet, ending in soft, delicate toes.<br/>"
				+ "[npc.Name] now [npc.has] [style.boldAngel(angelic legs and feet)], which are [npc.materialDescriptor] [npc.legFullDescription].",
			"[npc.Her] legs and feet are human in shape, but are [npc.materialCompositionDescriptor] [npc.legFullDescription(true)].",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL), false) {
	};

	public static AbstractLegType DEMON_COMMON = new AbstractLegType(BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			FootStructure.PLANTIGRADE,
			FootType.HUMANOID,
			"a pair of",
			"leg",
			"legs",
			Util.newArrayListOfValues("masculine", "demonic"),
			Util.newArrayListOfValues("feminine", "flawless", "demonic"),
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			"-",
			"[npc.Her] legs and feet are human in shape, but are [npc.materialCompositionDescriptor] [npc.legFullDescription(true)].",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL), false) {
		@Override
		public String getTransformationDescription(GameCharacter owner) {
			if (!owner.isShortStature()) {
				return UtilText.parse(owner,
						" They quickly shift into a pair of smooth, slender legs, and [npc.she] [npc.verb(let)] out a gasp as a layer of flawless, demonic skin rapidly grows to cover them."
						+ " As they finish transforming, [npc.she] almost [npc.verb(lose)] [npc.her] balance as the bones in [npc.her] feet start to shift and rearrange themselves."
						+ " After a moment, they've transformed into slender human-like feet, ending in soft, delicate toes.<br/>"
						+ "[npc.Name] now [npc.has] [style.boldDemon(demonic legs and feet)], which are [npc.materialDescriptor] [npc.legFullDescription]."
					+ "</p>");
			} else {
				return UtilText.parse(owner,
						" They quickly shift into a pair of smooth, slender legs, and [npc.she] [npc.verb(let)] out a gasp as a layer of flawless, impish skin rapidly grows to cover them."
						+ " As they finish transforming, [npc.she] almost [npc.verb(lose)] [npc.her] balance as the bones in [npc.her] feet start to shift and rearrange themselves."
						+ " After a moment, they've transformed into slender human-like feet, ending in soft, delicate toes.<br/>"
						+ "[npc.Name] now [npc.has] [style.boldImp(impish legs and feet)], which are [npc.materialDescriptor] [npc.legFullDescription]."
					+ "</p>");
			}
		}
	};

	public static AbstractLegType DEMON_HOOFED = new AbstractLegType(BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			FootStructure.UNGULIGRADE,
			FootType.HOOFS,
			"a pair of",
			"leg",
			"legs",
			Util.newArrayListOfValues("masculine", "demonic"),
			Util.newArrayListOfValues("feminine", "flawless", "demonic"),
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			"-",
			"[npc.Her] demonic legs are [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], and [npc.her] feet are formed into hard hoofs.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL), false) {
		@Override
		public String getTransformationDescription(GameCharacter owner) {
			if (!owner.isShortStature()) {
				return UtilText.parse(owner,
							" They quickly shift into a pair of smooth, slender legs, and [npc.name] [npc.verb(let)] out a gasp as a layer of flawless, demonic skin rapidly grows to cover them."
							+ " As [npc.her] new skin spreads down to the ends of [npc.her] toes, they suddenly push together, and [npc.she] [npc.verb(let)] out a cry as a thick, hoof-like nail grows in their place,"
								+ " quickly transforming to turn [npc.her] feet into hard, demonic hoofs."
							+ " As the transformation ends, [npc.she] [npc.verb(see)] that [npc.her] new skin smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
							+ "[npc.NameIsFull] left with [style.boldDemon(demonic legs and hoofed feet)], which are [npc.materialDescriptor] [npc.legFullDescription]."
						+ "</p>");
			} else {
				return UtilText.parse(owner,
						" They quickly shift into a pair of smooth, slender legs, and [npc.name] [npc.verb(let)] out a gasp as a layer of flawless, demonic skin rapidly grows to cover them."
						+ " As [npc.her] new skin spreads down to the ends of [npc.her] toes, they suddenly push together, and [npc.she] [npc.verb(let)] out a cry as a thick, hoof-like nail grows in their place,"
							+ " quickly transforming to turn [npc.her] feet into hard, demonic hoofs."
						+ " As the transformation ends, [npc.she] [npc.verb(see)] that [npc.her] new skin smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
						+ "[npc.NameIsFull] left with [style.boldImp(impish legs and hoofed feet)], which are [npc.materialDescriptor] [npc.legFullDescription]."
					+ "</p>");
			}
		}
		@Override
		public String getTransformName() {
			return "demonic-hoofed";
		}
	};

	public static AbstractLegType DEMON_HORSE_HOOFED = new AbstractLegType(BodyCoveringType.HORSE_HAIR,
			Race.DEMON,
			FootStructure.UNGULIGRADE,
			FootType.HOOFS,
			"a pair of",
			"leg",
			"legs",
			Util.newArrayListOfValues("masculine", "hair-coated", "demonic-horse"),
			Util.newArrayListOfValues("feminine", "hair-coated", "demonic-horse"),
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			"-",
			"[npc.Her] demonic, horse-like legs are [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], and [npc.her] feet are formed into hard hoofs.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
		@Override
		public String getTransformationDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"A layer of short, horse-like hair quickly grows over [npc.her] demonic legs as they shift into a new form."
					+ " As this hair spreads down to the ends of [npc.her] toes, they suddenly push together, and [npc.she] [npc.verb(let)] out a cry as a thick, hoof-like nail grows in their place,"
						+ " before quickly transforming to turn [npc.her] feet into hard, demonic hoofs."
					+ " As the transformation ends, [npc.she] [npc.verb(see)] that [npc.her] new hair smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh."
					+ "<br/>[npc.Name] now [npc.has]"
						+ (!owner.isShortStature()
							?" [style.boldDemon(animalistic, demonic legs with hoofed feet)], which are [npc.materialDescriptor] [npc.legFullDescription]."
							:"[style.boldImp(animalistic, impish legs with hoofed feet)], which are [npc.materialDescriptor] [npc.legFullDescription]."));
		}
		@Override
		public String getTransformName() {
			return "demonic-horse";
		}
	};

	public static AbstractLegType DEMON_SNAKE = new AbstractLegType(BodyCoveringType.SNAKE_SCALES,
			Race.DEMON,
			FootStructure.NONE,
			FootType.NONE,
			"a",
			"tail",
			"tails",
			Util.newArrayListOfValues("masculine", "scaly", "demonic-snake"),
			Util.newArrayListOfValues("feminine", "scaly", "demonic-snake"),
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			"-",
			"[npc.Her] demonic, snake-like lower body is [npc.materialCompositionDescriptor] [npc.legFullDescription(true)].",
			Util.newArrayListOfValues(
					LegConfiguration.TAIL_LONG),
			false) {
		@Override
		public boolean isDefaultPlural(GameCharacter gc) {
			return false;
		}
		@Override
		public String getTransformationDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"A layer of smooth scales quickly grow over [npc.her] demonic legs as they take on a snake-like appearance."
					+ " Quickly coming to an end, the transformation leaves [npc.name] with [npc.her] new scales smoothly transitioning into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh."
					+ "<br/>[npc.Name] now [npc.has]"
						+ (!owner.isShortStature()
							?" [style.boldDemon(demonic, snake-like legs)], which are [npc.materialDescriptor] [npc.legFullDescription]."
							:"[style.boldImp(impish, snake-like legs)], which are [npc.materialDescriptor] [npc.legFullDescription]."));
		}
		@Override
		public String getTransformName() {
			return "demonic-snake";
		}
		@Override
		public boolean isAvailableForSelfTransformMenu(GameCharacter gc) {
			return gc.hasPerkAnywhereInTree(Perk.POWER_OF_LYXIAS_6); //TODO?
		}
	};

	public static AbstractLegType DEMON_SPIDER = new AbstractLegType(BodyCoveringType.SPIDER_CHITIN,
			Race.DEMON,
			FootStructure.DIGITIGRADE,
			FootType.ARACHNID,
			"a",
			"leg",
			"legs",
			Util.newArrayListOfValues("masculine", "arachnid", "demonic-spider"),
			Util.newArrayListOfValues("feminine", "arachnid", "demonic-spider"),
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			"-",
			"[npc.Her] demonic, spider-like lower body is [npc.materialCompositionDescriptor] [npc.legFullDescription(true)].",
			Util.newArrayListOfValues(
					LegConfiguration.ARACHNID),
			true) {
		@Override
		public String getTransformationDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"A layer of smooth chitin quickly grows over [npc.her] demonic [npc.legs] as they take on a spider-like appearance."
					+ " Quickly coming to an end, the transformation leaves [npc.name] with [npc.her] new chitin smoothly transitioning into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh."
					+ "<br/>[npc.Name] now [npc.has]"
						+ (!owner.isShortStature()
							?" [style.boldDemon(demonic, spider-like legs)], which are [npc.materialDescriptor] [npc.legFullDescription]."
							:"[style.boldImp(impish, spider-like legs)], which are [npc.materialDescriptor] [npc.legFullDescription]."));
		}
		@Override
		public String getTransformName() {
			return "demonic-spider";
		}
		@Override
		public boolean isAvailableForSelfTransformMenu(GameCharacter gc) {
			return gc.hasPerkAnywhereInTree(Perk.POWER_OF_LUNETTE_5); //TODO?
		}
	};

	public static AbstractLegType DEMON_OCTOPUS = new AbstractLegType(BodyCoveringType.OCTOPUS_SKIN,
			Race.DEMON,
			FootStructure.TENTACLED,
			FootType.TENTACLE,
			"a",
			"tentacle",
			"tentacles",
			Util.newArrayListOfValues("masculine", "cephalopod", "demonic-octopus"),
			Util.newArrayListOfValues("feminine", "cephalopod", "demonic-octopus"),
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			"-",
			"[npc.Her] demonic, octopus-like lower body is [npc.materialCompositionDescriptor] [npc.legFullDescription(true)].",
			Util.newArrayListOfValues(
					LegConfiguration.CEPHALOPOD),
			false) {
		@Override
		public String getTransformationDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"A layer of smooth skin quickly grows over [npc.her] demonic [npc.legs] as they alarmingly take on a tentacle-like appearance."
					+ " Quickly coming to an end, the powerful transformation leaves [npc.name] with [npc.her] new skin smoothly transitioning into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh."
					+ "<br/>[npc.Name] now [npc.has]"
						+ (!owner.isShortStature()
							?" [style.boldDemon(demonic, octopus-like legs)], which are [npc.materialDescriptor] [npc.legFullDescription]."
							:"[style.boldImp(impish, octopus-like legs)], which are [npc.materialDescriptor] [npc.legFullDescription]."));
		}
		@Override
		public String getTransformName() {
			return "demonic-octopus";
		}
		@Override
		public AbstractTentacleType getTentacleType() {
			return TentacleType.LEG_DEMON_OCTOPUS;
		}
		public int getTentacleCount() {
			return 8;
		}
		@Override
		public boolean isAvailableForSelfTransformMenu(GameCharacter gc) {
			return gc.hasPerkAnywhereInTree(Perk.POWER_OF_LIRECEA_1); //TODO?
		}
	};

	public static AbstractLegType DEMON_FISH = new AbstractLegType(BodyCoveringType.FISH_SCALES,
			Race.DEMON,
			FootStructure.NONE,
			FootType.NONE,
			"a",
			"tail",
			"tails",
			Util.newArrayListOfValues("masculine", "scaly", "demonic-fish"),
			Util.newArrayListOfValues("feminine", "scaly", "demonic-fish"),
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			"-",
			"[npc.Her] demonic, fish-like lower body is [npc.materialCompositionDescriptor] [npc.legFullDescription(true)].",
			Util.newArrayListOfValues(
					LegConfiguration.TAIL),
			false) {
		@Override
		public boolean isDefaultPlural(GameCharacter gc) {
			return gc.hasStatusEffect(StatusEffect.AQUATIC_NEGATIVE);
		}
		@Override
		public String getNameSingular(GameCharacter gc) {
			if(gc.hasStatusEffect(StatusEffect.AQUATIC_NEGATIVE)) {
				return "leg";
			} else {
				return "tail";
			}
		}
		@Override
		public String getNamePlural(GameCharacter gc) {
			if(gc.hasStatusEffect(StatusEffect.AQUATIC_NEGATIVE)) {
				return "legs";
			} else {
				return "tails";
			}
		}
		@Override
		public String getTransformationDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"A layer of smooth scales quickly grow over [npc.her] demonic legs as they take on a fish-like appearance."
					+ " Quickly coming to an end, the transformation leaves [npc.name] with [npc.her] new scales smoothly transitioning into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh."
					+ "<br/>[npc.Name] now [npc.has]"
						+ (!owner.isShortStature()
							?" [style.boldDemon(demonic, fish-like legs)], which are [npc.materialDescriptor] [npc.legFullDescription]."
							:"[style.boldImp(impish, fish-like legs)], which are [npc.materialDescriptor] [npc.legFullDescription]."));
		}
		@Override
		public String getTransformName() {
			return "demonic-fish";
		}
		@Override
		public boolean isAvailableForSelfTransformMenu(GameCharacter gc) {
			return gc.hasPerkAnywhereInTree(Perk.POWER_OF_LIRECEA_1); //TODO?
		}
	};

	public static AbstractLegType DEMON_EAGLE = new AbstractLegType(BodyCoveringType.FEATHERS,
			Race.DEMON,
			FootStructure.DIGITIGRADE,
			FootType.TALONS,
			"a",
			"leg",
			"legs",
			Util.newArrayListOfValues("masculine", "clawed", "anthropomorphic, bird-like"),
			Util.newArrayListOfValues("feminine", "clawed", "anthropomorphic, bird-like"),
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic"),
			"-",
			"[npc.Her] demonic, bird-like lower body is [npc.materialCompositionDescriptor] [npc.legFullDescription(true)].",
			Util.newArrayListOfValues(
					LegConfiguration.AVIAN),
			false) {
		@Override
		public String getTransformationDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"A layer of feathers quickly grows over [npc.her] demonic [npc.legs] as they take on a bird-like appearance."
					+ " Quickly coming to an end, the transformation leaves [npc.name] with [npc.her] new feathers smoothly transitioning into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh."
					+ "<br/>[npc.Name] now [npc.has]"
						+ (!owner.isShortStature()
							?" [style.boldDemon(demonic, bird-like legs)], which are [npc.materialDescriptor] [npc.legFullDescription]."
							:"[style.boldImp(impish, bird-like legs)], which are [npc.materialDescriptor] [npc.legFullDescription]."));
		}
		@Override
		public String getTransformName() {
			return "demonic-eagle";
		}
		@Override
		public boolean isAvailableForSelfTransformMenu(GameCharacter gc) {
			return gc.hasPerkAnywhereInTree(Perk.POWER_OF_LISOPHIA_7); //TODO?
		}
	};
	
	public static AbstractLegType COW_MORPH = new AbstractLegType(BodyCoveringType.BOVINE_FUR,
			Race.COW_MORPH,
			FootStructure.UNGULIGRADE,
			FootType.HOOFS,
			"a pair of",
			"leg",
			"legs",
			Util.newArrayListOfValues("masculine", "hoofed", "furry", "fur-coated", "anthropomorphic, cow-like"),
			Util.newArrayListOfValues("feminine", "hoofed", "furry", "fur-coated", "anthropomorphic, cow-like"),
			Util.newArrayListOfValues("cow-like", "bovine"),
			Util.newArrayListOfValues("cow-like", "bovine"),
			Util.newArrayListOfValues("cow-like", "bovine"),
			Util.newArrayListOfValues("cow-like", "bovine"),
			"A layer of short, cow-like hair quickly grows over [npc.her] legs as they shift into a new form."
				+ " As [npc.her] new fur spreads down to the ends of [npc.her] toes, they suddenly push together, and [npc.she] [npc.verb(let)] out a cry as a thick, hoof-like nail grows in their place,"
					+ " before quickly transforming to turn [npc.her] feet into cow-like hoofs."
				+ " As the transformation ends, [npc.she] [npc.verb(see)] that [npc.her] new fur smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
				+ "[npc.Name] now [npc.has] anthropomorphic, [style.boldCowMorph(cow-like legs and hoofed feet)], which are [npc.materialDescriptor] [npc.legFullDescription].",
			"[npc.Her] legs are [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], and [npc.her] feet are formed into anthropomorphic cow-like hoofs.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType DOG_MORPH = new AbstractLegType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			FootStructure.DIGITIGRADE,
			FootType.PAWS,
			"a pair of",
			"leg",
			"legs",
			Util.newArrayListOfValues("masculine", "furry", "fur-coated", "anthropomorphic, dog-like"),
			Util.newArrayListOfValues("feminine", "furry", "fur-coated", "anthropomorphic, dog-like"),
			Util.newArrayListOfValues("dog-like", "canine"),
			Util.newArrayListOfValues("dog-like", "canine"),
			Util.newArrayListOfValues("dog-like", "canine"),
			Util.newArrayListOfValues("dog-like", "canine"),
			"A layer of dog-like fur quickly grows over [npc.her] legs as they shift into a new form."
				+ " As [npc.her] new fur spreads down to the ends of [npc.her] toes, [npc.her] toenails thicken into little blunt claws, and leathery pads grow to cover [npc.her] soles, leaving [npc.herHim] with paw-like feet."
				+ " As the transformation ends, [npc.she] [npc.verb(see)] that [npc.her] new fur smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
				+ "[npc.Name] now [npc.has] anthropomorphic, [style.boldDogMorph(dog-like legs and feet)], which are [npc.materialDescriptor] [npc.legFullDescription].",
			"[npc.Her] legs are [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], and [npc.her] feet are formed into anthropomorphic dog-like paws, complete with little blunt claws and leathery pads.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType WOLF_MORPH = new AbstractLegType(BodyCoveringType.LYCAN_FUR,
			Race.WOLF_MORPH,
			FootStructure.DIGITIGRADE,
			FootType.PAWS,
			"a pair of",
			"leg",
			"legs",
			Util.newArrayListOfValues("masculine", "furry", "fur-coated", "anthropomorphic, wolf-like"),
			Util.newArrayListOfValues("feminine", "furry", "fur-coated", "anthropomorphic, wolf-like"),
			Util.newArrayListOfValues("wolf-like", "lupine"),
			Util.newArrayListOfValues("wolf-like", "lupine"),
			Util.newArrayListOfValues("wolf-like", "lupine"),
			Util.newArrayListOfValues("wolf-like", "lupine"),
			"A layer of wolf-like fur quickly grows over [npc.her] legs as they shift into a new form."
				+ " As [npc.her] new fur spreads down to the ends of [npc.her] toes, [npc.her] toenails thicken into sharp claws, and tough leathery pads grow to cover [npc.her] soles, leaving [npc.herHim] with paw-like feet."
				+ " As the transformation ends, [npc.she] [npc.verb(see)] that [npc.her] new fur smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
				+ "[npc.Name] now [npc.has] anthropomorphic, [style.boldWolfMorph(wolf-like legs and feet)], which are [npc.materialDescriptor] [npc.legFullDescription].",
			"[npc.Her] legs are [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], and [npc.her] feet are formed into anthropomorphic wolf-like paws, complete with sharp claws and tough leathery pads.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType FOX_MORPH = new AbstractLegType(BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			FootStructure.DIGITIGRADE,
			FootType.PAWS,
			"a pair of",
			"leg",
			"legs",
			Util.newArrayListOfValues("masculine", "furry", "fur-coated", "anthropomorphic, fox-like"),
			Util.newArrayListOfValues("feminine", "furry", "fur-coated", "anthropomorphic, fox-like"),
			Util.newArrayListOfValues("fox-like", "vulpine"),
			Util.newArrayListOfValues("fox-like", "vulpine"),
			Util.newArrayListOfValues("fox-like", "vulpine"),
			Util.newArrayListOfValues("fox-like", "vulpine"),
			"A layer of fox-like fur quickly grows over [npc.her] legs as they shift into a new form."
				+ " As [npc.her] new fur spreads down to the ends of [npc.her] toes, [npc.her] toenails thicken into sharp claws, and little pads grow to cover [npc.her] soles, leaving [npc.herHim] with paw-like feet."
				+ " As the transformation ends, [npc.she] [npc.verb(see)] that [npc.her] new fur smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.</br>"
				+ "[npc.Name] now [npc.has] anthropomorphic, [style.boldFoxMorph(fox-like legs and feet)], which are [npc.materialDescriptor] [npc.legFullDescription].",
			"[npc.Her] legs are [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], and [npc.her] feet are formed into anthropomorphic fox-like paws, complete with sharp claws and tough leathery pads.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType SQUIRREL_MORPH = new AbstractLegType(BodyCoveringType.SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			FootStructure.PLANTIGRADE,
			FootType.PAWS,
			"a pair of",
			"leg",
			"legs",
			Util.newArrayListOfValues("masculine", "furry", "fur-coated", "anthropomorphic, squirrel-like"),
			Util.newArrayListOfValues("feminine", "furry", "fur-coated", "anthropomorphic, squirrel-like"),
			Util.newArrayListOfValues("squirrel-like"),
			Util.newArrayListOfValues("squirrel-like"),
			Util.newArrayListOfValues("squirrel-like"),
			Util.newArrayListOfValues("squirrel-like"),
			"A layer of squirrel-like fur quickly grows over [npc.her] legs as they shift into a new form."
				+ " As [npc.her] new fur spreads down to the ends of [npc.her] toes, [npc.her] toenails thicken into sharp claws, and little pink pads grow to cover [npc.her] soles, leaving [npc.herHim] with paw-like feet."
				+ " As the transformation ends, [npc.she] [npc.verb(see)] that [npc.her] new fur smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
				+ "[npc.Name] now [npc.has] anthropomorphic, [style.boldSquirrelMorph(squirrel-like legs and feet)], which are [npc.materialDescriptor] [npc.legFullDescription].",
			"[npc.Her] legs are [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], and [npc.her] feet are formed into anthropomorphic squirrel-like paws, complete with claws and pink pads.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType RAT_MORPH = new AbstractLegType(BodyCoveringType.RAT_FUR,
			Race.RAT_MORPH,
			FootStructure.PLANTIGRADE,
			FootType.PAWS,
			"a pair of",
			"leg",
			"legs",
			Util.newArrayListOfValues("masculine", "furry", "fur-coated", "anthropomorphic, rat-like"),
			Util.newArrayListOfValues("feminine", "furry", "fur-coated", "anthropomorphic, rat-like"),
			Util.newArrayListOfValues("rat-like"),
			Util.newArrayListOfValues("rat-like"),
			Util.newArrayListOfValues("rat-like"),
			Util.newArrayListOfValues("rat-like"),
			"A layer of rat-like fur quickly grows over [npc.her] legs as they shift into a new form."
				+ " As [npc.her] new fur spreads down to the ends of [npc.her] toes, [npc.her] toenails thicken into sharp claws, and little pink pads grow to cover [npc.her] soles, leaving [npc.herHim] with paw-like feet."
				+ " As the transformation ends, [npc.she] [npc.verb(see)] that [npc.her] new fur smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
				+ "[npc.Name] now [npc.has] anthropomorphic, [style.boldRatMorph(rat-like legs and feet)], which are [npc.materialDescriptor] [npc.legFullDescription].",
			"[npc.Her] legs are [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], and [npc.her] feet are formed into anthropomorphic rat-like paws, complete with claws and leathery pads.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType RABBIT_MORPH = new AbstractLegType(BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			FootStructure.PLANTIGRADE,
			FootType.PAWS,
			"a pair of",
			"leg",
			"legs",
			Util.newArrayListOfValues("masculine", "furry", "fur-coated", "anthropomorphic, rabbit-like"),
			Util.newArrayListOfValues("feminine", "furry", "fur-coated", "anthropomorphic, rabbit-like"),
			Util.newArrayListOfValues("rabbit-like"),
			Util.newArrayListOfValues("rabbit-like"),
			Util.newArrayListOfValues("rabbit-like"),
			Util.newArrayListOfValues("rabbit-like"),
			"A layer of rabbit-like fur quickly grows over [npc.her] legs as they shift into a new form."
				+ " As [npc.her] new fur spreads down to the ends of [npc.her] toes, [npc.her] toenails thicken into blunt claws, and soft little pads grow to cover [npc.her] soles, leaving [npc.herHim] with long, paw-like feet."
				+ " As the transformation ends, [npc.she] [npc.verb(see)] that [npc.her] new fur smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
				+ "[npc.Name] now [npc.has] anthropomorphic, [style.boldRabbitMorph(rabbit-like legs and feet)], which are [npc.materialDescriptor] [npc.legFullDescription].",
			"[npc.Her] legs are [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], and [npc.her] feet are formed into long, anthropomorphic, rabbit-like paws, complete with blunt claws and soft pads.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType BAT_MORPH = new AbstractLegType(BodyCoveringType.BAT_FUR,
			Race.BAT_MORPH,
			FootStructure.DIGITIGRADE,
			FootType.PAWS,
			"a pair of",
			"leg",
			"legs",
			Util.newArrayListOfValues("masculine", "furry", "fur-coated", "anthropomorphic, bat-like"),
			Util.newArrayListOfValues("feminine", "furry", "fur-coated", "anthropomorphic, bat-like"),
			Util.newArrayListOfValues("bat-like"),
			Util.newArrayListOfValues("bat-like"),
			Util.newArrayListOfValues("bat-like"),
			Util.newArrayListOfValues("bat-like"),
			"A layer of bat-like fur quickly grows over [npc.her] legs as they shift into a new form."
				+ " As [npc.her] new fur spreads down to the ends of [npc.her] toes, [npc.her] toenails thicken into sharp claws, and little pink pads grow to cover [npc.her] soles, leaving [npc.herHim] with paw-like feet."
				+ " As the transformation ends, [npc.she] [npc.verb(see)] that [npc.her] new fur smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
				+ "[npc.Name] now [npc.has] anthropomorphic, [style.boldBatMorph(bat-like legs and feet)], which are [npc.materialDescriptor] [npc.legFullDescription].",
			"[npc.Her] legs are [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], and [npc.her] feet are formed into anthropomorphic bat-like paws, complete with claws and leathery pads.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL), false) {
	};
	
	public static AbstractLegType CAT_MORPH = new AbstractLegType(BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			FootStructure.DIGITIGRADE,
			FootType.PAWS,
			"a pair of",
			"leg",
			"legs",
			Util.newArrayListOfValues("masculine", "furry", "fur-coated", "anthropomorphic, cat-like"),
			Util.newArrayListOfValues("feminine", "furry", "fur-coated", "anthropomorphic, cat-like"),
			Util.newArrayListOfValues("cat-like", "feline"),
			Util.newArrayListOfValues("cat-like", "feline"),
			Util.newArrayListOfValues("cat-like", "feline"),
			Util.newArrayListOfValues("cat-like", "feline"),
			"A layer of cat-like fur quickly grows over [npc.her] legs as they shift into a new form."
				+ " As [npc.her] new fur spreads down to the ends of [npc.her] toes, [npc.her] toenails thicken into sharp, retractable claws, and little pink pads grow to cover [npc.her] soles, leaving [npc.herHim] with paw-like feet."
				+ " As the transformation ends, [npc.she] [npc.verb(see)] that [npc.her] new fur smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
				+ "[npc.Name] now [npc.has] anthropomorphic, [style.boldCatMorph(cat-like legs and feet)], which are [npc.materialDescriptor] [npc.legFullDescription].",
			"[npc.Her] legs are [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], and [npc.her] feet are formed into anthropomorphic cat-like paws, complete with retractable claws and pink pads.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType ALLIGATOR_MORPH = new AbstractLegType(BodyCoveringType.ALLIGATOR_SCALES,
			Race.ALLIGATOR_MORPH,
			FootStructure.PLANTIGRADE,
			FootType.HUMANOID,
			"a pair of",
			"leg",
			"legs",
			Util.newArrayListOfValues("masculine", "scaly", "reptilian", "anthropomorphic, alligator-like"),
			Util.newArrayListOfValues("feminine", "scaly", "reptilian", "anthropomorphic, alligator-like"),
			Util.newArrayListOfValues("alligator-like", "scaly", "reptilian"),
			Util.newArrayListOfValues("alligator-like", "scaly", "reptilian"),
			Util.newArrayListOfValues("alligator-like", "scaly", "reptilian"),
			Util.newArrayListOfValues("alligator-like", "scaly", "reptilian"),
			"A layer of alligator-like scales quickly grows over [npc.her] legs as they shift into a new form."
				+ " As [npc.her] new scales spread down to the ends of [npc.her] toes, [npc.her] toenails thicken into sharp claws, and little scales grow to cover [npc.her] soles, leaving [npc.herHim] with alligator-like feet."
				+ " As the transformation ends, [npc.she] [npc.verb(see)] that [npc.her] new scales smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
				+ "[npc.Name] now [npc.has] anthropomorphic, [style.boldGatorMorph(alligator-like legs and feet)], which are [npc.materialDescriptor] [npc.legFullDescription].",
			"[npc.Her] legs are [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], and [npc.her] feet are formed into anthropomorphic alligator-like feet, complete with sharp claws.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType HORSE_MORPH = new AbstractLegType(BodyCoveringType.HORSE_HAIR,
			Race.HORSE_MORPH,
			FootStructure.UNGULIGRADE,
			FootType.HOOFS,
			"a pair of",
			"leg",
			"legs",
			Util.newArrayListOfValues("masculine", "hair-coated", "anthropomorphic, horse-like"),
			Util.newArrayListOfValues("feminine", "hair-coated", "anthropomorphic, horse-like"),
			Util.newArrayListOfValues("horse-like", "equine"),
			Util.newArrayListOfValues("horse-like", "equine"),
			Util.newArrayListOfValues("horse-like", "equine"),
			Util.newArrayListOfValues("horse-like", "equine"),
			"A layer of short, horse-like hair quickly grows over [npc.her] legs as they shift into a new form."
				+ " As [npc.her] new fur spreads down to the ends of [npc.her] toes, they suddenly push together, and [npc.she] [npc.verb(let)] out a cry as a thick, hoof-like nail grows in their place,"
					+ " before quickly transforming to turn [npc.her] feet into horse-like hoofs."
				+ " As the transformation ends, [npc.she] [npc.verb(see)] that [npc.her] new hair smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
				+ "[npc.Name] now [npc.has] anthropomorphic, [style.boldHorseMorph(horse-like legs and hoofed feet)], which are [npc.materialDescriptor] [npc.legFullDescription].",
			"[npc.Her] legs are [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], and [npc.her] feet are formed into anthropomorphic horse-like hoofs.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};

//	public static AbstractLegType HORSE_FISH = new AbstractLegType(BodyCoveringType.FISH_SCALES,
//			Race.HORSE_MORPH,
//			FootStructure.PLANTIGRADE, // FootStructure and Type is for when legs are grown on land.
//			FootType.HUMANOID,
//			"a",
//			"tail",
//			"tails",
//			Util.newArrayListOfValues("masculine", "scaly", "fish"),
//			Util.newArrayListOfValues("feminine", "scaly", "fish"),
//			Util.newArrayListOfValues("horse"),
//			Util.newArrayListOfValues("horse"),
//			Util.newArrayListOfValues("horse"),
//			Util.newArrayListOfValues("horse"),
//			"-",
//			"[npc.Her] fish-like lower body is [npc.materialCompositionDescriptor] [npc.legFullDescription(true)].",
//			Util.newArrayListOfValues(
//					LegConfiguration.TAIL),
//			false) {
//		@Override
//		public boolean isDefaultPlural(GameCharacter gc) {
//			return gc.hasStatusEffect(StatusEffect.AQUATIC_NEGATIVE);
//		}
//		@Override
//		public String getNameSingular(GameCharacter gc) {
//			if(gc.hasStatusEffect(StatusEffect.AQUATIC_NEGATIVE)) {
//				return "leg";
//			} else {
//				return "tail";
//			}
//		}
//		@Override
//		public String getNamePlural(GameCharacter gc) {
//			if(gc.hasStatusEffect(StatusEffect.AQUATIC_NEGATIVE)) {
//				return "legs";
//			} else {
//				return "tails";
//			}
//		}
//		@Override
//		public String getTransformationDescription(GameCharacter owner) {
//			return UtilText.parse(owner,
//					"A layer of smooth scales quickly grow over [npc.her] legs as they take on a fish-like appearance."
//					+ " Quickly coming to an end, the transformation leaves [npc.name] with [npc.her] new scales smoothly transitioning into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh."
//					+ "<br/>[npc.Name] now [npc.has] [style.boldHorseMorph(fish-like legs)], which are [npc.materialDescriptor] [npc.legFullDescription].");
//		}
//		@Override
//		public String getTransformName() {
//			return "hippocampus";
//		}
//	};
	
	public static AbstractLegType REINDEER_MORPH = new AbstractLegType(BodyCoveringType.REINDEER_FUR,
			Race.REINDEER_MORPH,
			FootStructure.UNGULIGRADE,
			FootType.HOOFS,
			"a pair of",
			"leg",
			"legs",
			Util.newArrayListOfValues("masculine", "fur-coated", "anthropomorphic, reindeer-like"),
			Util.newArrayListOfValues("feminine", "fur-coated", "anthropomorphic, reindeer-like"),
			Util.newArrayListOfValues("reindeer-like"),
			Util.newArrayListOfValues("reindeer-like"),
			Util.newArrayListOfValues("reindeer-like"),
			Util.newArrayListOfValues("reindeer-like"),
			"A layer of furry, reindeer-like hair quickly grows over [npc.her] legs as they shift into a new form."
				+ " As [npc.her] new fur spreads down to the ends of [npc.her] toes, they suddenly push together, and [npc.she] [npc.verb(let)] out a cry as a crescent-shaped, cloven hoof grows in their place,"
					+ " before quickly transforming to turn [npc.her] feet into reindeer-like hoofs."
				+ " As the transformation ends, [npc.she] [npc.verb(see)] that [npc.her] new fur smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
				+ "[npc.Name] now [npc.has] anthropomorphic, [style.boldReindeerMorph(reindeer-like legs and hoofed feet)], which are [npc.materialDescriptor] [npc.legFullDescription].",
			"[npc.Her] legs are [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], and [npc.her] feet are formed into anthropomorphic reindeer-like hoofs.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType HARPY = new AbstractLegType(BodyCoveringType.HARPY_SKIN,
			Race.HARPY,
			FootStructure.DIGITIGRADE,
			FootType.TALONS,
			"a pair of",
			"leg",
			"legs",
			Util.newArrayListOfValues("masculine", "clawed", "anthropomorphic, bird-like"),
			Util.newArrayListOfValues("feminine", "clawed", "anthropomorphic, bird-like"),
			Util.newArrayListOfValues("bird-like"),
			Util.newArrayListOfValues("bird-like"),
			Util.newArrayListOfValues("bird-like"),
			Util.newArrayListOfValues("bird-like"),
			"A layer of scaly, bird-like leather quickly grows over [npc.her] legs as they shift into a new form."
				+ " As [npc.her] new leathery skin spreads down to the ends of [npc.her] toes, [npc.her] feet start to undergo an extreme transformation."
				+ " [npc.Her] toes combine together and re-shape themselves into three forward-facing talons, as a fourth, thumb-like talon branches out behind them."
				+ " As the transformation ends, this leathery skin sharply transitions into [npc.her] body's [npc.skinColour] [npc.skin] at [npc.her] upper-thigh.<br/>"
				+ "[npc.Name] now [npc.has] anthropomorphic, [style.boldHarpy(bird-like legs and talons in place of feet)], which are [npc.materialDescriptor] [npc.legFullDescription].",
			"[npc.Her] bird-like legs are [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], which sharply transitions into [npc.her] body's [npc.skinColour] [npc.skin] at [npc.her] upper-thigh."
				+ " At the end of each of [npc.her] [npc.legs], [npc.she] [npc.has] sharp, bird-like talons.",
				Util.newArrayListOfValues(
						LegConfiguration.BIPEDAL,
						LegConfiguration.AVIAN), false) {
	};
	
	private static List<AbstractLegType> allLegTypes;
	private static Map<AbstractLegType, String> legToIdMap = new HashMap<>();
	private static Map<String, AbstractLegType> idToLegMap = new HashMap<>();
	
	static {
		allLegTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("leg")) {
					try {
						AbstractLegType type = new AbstractLegType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allLegTypes.add(type);
						legToIdMap.put(type, id);
						idToLegMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// External res types:
		
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("leg")) {
					try {
						AbstractLegType type = new AbstractLegType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allLegTypes.add(type);
						legToIdMap.put(type, id);
						idToLegMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded leg types:
		
		Field[] fields = LegType.class.getFields();
		
		for(Field f : fields){
			if (AbstractLegType.class.isAssignableFrom(f.getType())) {
				
				AbstractLegType ct;
				try {
					ct = ((AbstractLegType) f.get(null));

					legToIdMap.put(ct, f.getName());
					idToLegMap.put(f.getName(), ct);
					
					allLegTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allLegTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractLegType getLegTypeFromId(String id) {
		if(id.equals("IMP")) {
			return LegType.DEMON_COMMON;
		}
		if(id.equals("LYCAN")) {
			return LegType.WOLF_MORPH;
		}

		id = Util.getClosestStringMatch(id, idToLegMap.keySet());
		return idToLegMap.get(id);
	}
	
	public static String getIdFromLegType(AbstractLegType legType) {
		return legToIdMap.get(legType);
	}
	
	public static List<AbstractLegType> getAllLegTypes() {
		return allLegTypes;
	}
	
	private static Map<AbstractRace, List<AbstractLegType>> typesMap = new HashMap<>();
	public static List<AbstractLegType> getLegTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractLegType> types = new ArrayList<>();
		for(AbstractLegType type : LegType.getAllLegTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}

}