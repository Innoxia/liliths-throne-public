package com.lilithsthrone.game.character.effects;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum Perk implements PerkInterface {
	
	// Physical:
	BRAWLER(20,
			"brawler",
			PerkLevel.LEVEL_ONE,
			PerkCategory.PHYSICAL,
			"physical_brawler",
			Colour.ATTRIBUTE_STRENGTH,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 15), new Value<Attribute, Integer>(Attribute.RESISTANCE_PHYSICAL, 15)),
			null) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return "";
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You're quite competent at fighting. You gain a bonus to your physical damage and resistance.";
			else
				return UtilText.parse(owner, "[npc.Name] is a competent fighter. [npc.She] gains a bonus to [npc.her] physical damage and resistance.");
		}
	},
	ACCURATE(20,
			"deadeye",
			PerkLevel.LEVEL_FIVE,
			PerkCategory.PHYSICAL,
			"physical_accurate",
			Colour.ATTRIBUTE_STRENGTH,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.CRITICAL_CHANCE, 10)),
			null) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return "";
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You almost never miss your target.";
			else
				return UtilText.parse(owner, "[npc.Name] almost never misses [npc.her] target.");
		}
	},
	TANK(20,
			"tank",
			PerkLevel.LEVEL_FIVE,
			PerkCategory.PHYSICAL,
			"physical_tank",
			Colour.ATTRIBUTE_STRENGTH,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.HEALTH_MAXIMUM, 25)),
			null) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return "";
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return "";
		}

		@Override
		public Perk getPreviousLevelPerk() {
			return null;
		}

		@Override
		public Perk getNextLevelPerk() {
			return TANK_2;
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You can shrug off attacks that would floor most people.";
			else
				return UtilText.parse(owner, "[npc.Name] can shrug off attacks that would floor most people.");
		}
	},
	TANK_2(20,
			"indomitable",
			PerkLevel.LEVEL_TEN,
			PerkCategory.PHYSICAL,
			"physical_tank_2",
			Colour.ATTRIBUTE_STRENGTH,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.HEALTH_MAXIMUM, 60)),
			null) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			character.removePerk(TANK);
			return "";
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return "";
		}

		@Override
		public Perk getPreviousLevelPerk() {
			return TANK;
		}

		@Override
		public Perk getNextLevelPerk() {
			return null;
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "The mightiest of blows from the greatest of warriors would do little to impede you.";
			else
				return UtilText.parse(owner, "The mightiest of blows from the greatest of warriors would do little to impede [npc.name].");
		}
	},
	INDEFATIGABLE(20,
			"indefatigable",
			PerkLevel.LEVEL_FIFTEEN,
			PerkCategory.PHYSICAL,
			"physical_indefatigable",
			Colour.ATTRIBUTE_STRENGTH,
			null,
			Util.newArrayListOfValues(new ListValue<>("<span style='color:"
					+ Colour.ATTRIBUTE_STRENGTH.toWebHexString()
					+ ";'>Improved Combat</span>"))) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return "";
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You can continue fighting through almost anything. You will no longer lose combat if your willpower or stamina drop to 0.";
			else
				return UtilText.parse(owner, "[npc.Name] is relentless. Even if [npc.her] willpower or stamina drop to 0, [npc.she] will continue fighting.");
		}
	},

	OBSERVANT(60,
			"observant",
			PerkLevel.LEVEL_ONE,
			PerkCategory.PHYSICAL,
			"misc_observant",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.CRITICAL_CHANCE, 5)),
			Util.newArrayListOfValues(new ListValue<>("<span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>Gender detection</span>"))) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You are very perceptive, and are capable of noticing the slightest of changes in your surroundings."
						+ " You are always able to determine a person's gender, even if you have no knowledge of what their groin looks like.";
			} else {
				return UtilText.parse(owner, "[npc.Name] is very perceptive, and [npc.she] continuously scans [npc.her] surroundings for signs of danger.");
			}
		}
	},

	// Arcane:
	SPELL_POWER_1(20,
			"arcane power",
			PerkLevel.LEVEL_ONE,
			PerkCategory.ARCANE,
			"arcane_power_1",
			Colour.ATTRIBUTE_INTELLIGENCE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, 5)),
			null) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return "";
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return "";
		}

		@Override
		public Perk getNextLevelPerk() {
			return SPELL_POWER_2;
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You have focused on improving your ability to harness the arcane and cast spells.";
			else
				return UtilText.parse(owner, "[npc.Name] seems reasonably competent at casting spells.");
		}
	},
	SPELL_POWER_2(20,
			"arcane conduit",
			PerkLevel.LEVEL_FIVE,
			PerkCategory.ARCANE,
			"arcane_power_2",
			Colour.ATTRIBUTE_INTELLIGENCE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, 10)),
			null) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			character.removePerk(SPELL_POWER_1);
			return "";
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return "";
		}

		@Override
		public Perk getPreviousLevelPerk() {
			return SPELL_POWER_1;
		}

		@Override
		public Perk getNextLevelPerk() {
			return SPELL_POWER_3;
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You have focused your ability to harness the arcane to the point where you can greatly enhance the effects of any spell.";
			else
				return UtilText.parse(owner, "[npc.Name] is highly competent at harnessing the arcane and improving [npc.her] spells.");
		}
	},
	SPELL_POWER_3(20,
			"arcane mastery",
			PerkLevel.LEVEL_TEN,
			PerkCategory.ARCANE,
			"arcane_power_3",
			Colour.ATTRIBUTE_INTELLIGENCE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, 15)),
			null) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			character.removePerk(SPELL_POWER_2);
			return "";
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return "";
		}

		@Override
		public Perk getPreviousLevelPerk() {
			return SPELL_POWER_2;
		}

		@Override
		public Perk getNextLevelPerk() {
			return null;
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You are a master at harnessing the arcane. Even if you didn't have an aura as strong as a demon's, you'd still be one of the greatest arcane users in Dominion.";
			} else
				return UtilText.parse(owner, "[npc.Name] is a master of harnessing the arcane and improving [npc.her] spells.");
		}
	},

	FIRE_ENHANCEMENT(20,
			"firebrand",
			PerkLevel.LEVEL_TEN,
			PerkCategory.ARCANE,
			"arcane_fire_1",
			Colour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_FIRE, 10)),
			null) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return "";
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return "";
		}

		@Override
		public Perk getNextLevelPerk() {
			return FIRE_ENHANCEMENT_2;
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You have a heightened affinity with arcane fire. You know just how to manipulate it in order to maximise the damage caused.";
			else
				return UtilText.parse(owner, "[npc.Name] has a heightened affinity with arcane fire. [npc.She] knows just how to manipulate it in order to maximise the damage caused.");
		}
	},
	FIRE_ENHANCEMENT_2(20,
			"incendiary",
			PerkLevel.LEVEL_FIFTEEN,
			PerkCategory.ARCANE,
			"arcane_fire_2",
			Colour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_FIRE, 10), new Value<Attribute, Integer>(Attribute.RESISTANCE_FIRE, 10)),
			null) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			character.removePerk(FIRE_ENHANCEMENT);
			return "";
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return "";
		}

		@Override
		public Perk getPreviousLevelPerk() {
			return FIRE_ENHANCEMENT;
		}

		@Override
		public Perk getNextLevelPerk() {
			return null;
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You are an expert at manipulating arcane fire. Not only are you able to maximise its damage, but you also have a heightened resistance to its effects.";
			else
				return UtilText.parse(owner, "[npc.Name] is an expert at manipulating arcane fire. Not only is [npc.she] able to maximise its damage, but [npc.she] also has a heightened resistance to its effects.");
		}
	},
	COLD_ENHANCEMENT(20,
			"frosty",
			PerkLevel.LEVEL_TEN,
			PerkCategory.ARCANE,
			"arcane_ice_1",
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_ICE, 10)),
			null) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return "";
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return "";
		}

		@Override
		public Perk getNextLevelPerk() {
			return COLD_ENHANCEMENT_2;
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You have a heightened affinity with arcane ice. You know just how to manipulate it in order to maximise the damage caused.";
			else
				return UtilText.parse(owner, "[npc.Name] has a heightened affinity with arcane ice. [npc.She] knows just how to manipulate it in order to maximise the damage caused.");
		}
	},
	COLD_ENHANCEMENT_2(20,
			"ice cold",
			PerkLevel.LEVEL_FIFTEEN,
			PerkCategory.ARCANE,
			"arcane_ice_2",
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_ICE, 10), new Value<Attribute, Integer>(Attribute.RESISTANCE_ICE, 10)),
			null) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			character.removePerk(COLD_ENHANCEMENT);
			return "";
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return "";
		}

		@Override
		public Perk getPreviousLevelPerk() {
			return COLD_ENHANCEMENT;
		}

		@Override
		public Perk getNextLevelPerk() {
			return null;
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You are an expert at manipulating arcane ice. Not only are you able to maximise its damage, but you also have a heightened resistance to its effects.";
			else
				return UtilText.parse(owner, "[npc.Name] is an expert at manipulating arcane ice. Not only is [npc.she] able to maximise its damage, but [npc.she] also has a heightened resistance to its effects.");
		}
	},
	POISON_ENHANCEMENT(20,
			"venomous",
			PerkLevel.LEVEL_TEN,
			PerkCategory.ARCANE,
			"arcane_poison_1",
			Colour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_POISON, 10)),
			null) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return "";
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return "";
		}

		@Override
		public Perk getNextLevelPerk() {
			return POISON_ENHANCEMENT_2;
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You have a heightened affinity with arcane poison. You know just how to manipulate it in order to maximise the damage caused.";
			else
				return UtilText.parse(owner, "[npc.Name] has a heightened affinity with arcane poison. [npc.She] knows just how to manipulate it in order to maximise the damage caused.");
		}
	},
	POISON_ENHANCEMENT_2(20,
			"toxic",
			PerkLevel.LEVEL_FIFTEEN,
			PerkCategory.ARCANE,
			"arcane_poison_2",
			Colour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_POISON, 10), new Value<Attribute, Integer>(Attribute.RESISTANCE_POISON, 10)),
			null) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			character.removePerk(POISON_ENHANCEMENT);
			return "";
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return "";
		}

		@Override
		public Perk getPreviousLevelPerk() {
			return POISON_ENHANCEMENT;
		}

		@Override
		public Perk getNextLevelPerk() {
			return null;
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You are an expert at manipulating arcane poison. Not only are you able to maximise its damage, but you also have a heightened resistance to its effects.";
			else
				return UtilText.parse(owner, "[npc.Name] is an expert at manipulating arcane poison. Not only is [npc.she] able to maximise its damage, but [npc.she] also has a heightened resistance to its effects.");
		}
	},

	// Fitness:
	RUNNER(20,
			"runner",
			PerkLevel.LEVEL_FIVE,
			PerkCategory.FITNESS,
			"fitness_runner",
			Colour.ATTRIBUTE_FITNESS,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.STAMINA_MAXIMUM, 25)),
			Util.newArrayListOfValues(new ListValue<>("<b>*</b> <span style='color:"
					+ Colour.ATTRIBUTE_FITNESS.toWebHexString()
					+ ";'>Improved escape chance</span>"))) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return "";
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return "";
		}

		@Override
		public Perk getPreviousLevelPerk() {
			return null;
		}

		@Override
		public Perk getNextLevelPerk() {
			return RUNNER_2;
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You're a natural runner and possess a good level of stamina.";
			else
				return UtilText.parse(owner, "[npc.Name] is natural runner and possesses a good level of stamina.");
		}
	},
	RUNNER_2(20,
			"cardio champion",
			PerkLevel.LEVEL_TEN,
			PerkCategory.FITNESS,
			"fitness_runner_2",
			Colour.ATTRIBUTE_FITNESS,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.STAMINA_MAXIMUM, 60)),
			Util.newArrayListOfValues(new ListValue<>("<b>*</b> <span style='color:"
					+ Colour.ATTRIBUTE_FITNESS.toWebHexString()
					+ ";'>Improved escape chance</span>"))) {
		@Override
		public String getName(GameCharacter character) {
			if (character.isFeminine())
				return "Cardio Queen";
			else
				return "Cardio King";
		}

		@Override
		public String applyPerkGained(GameCharacter character) {
			character.removePerk(RUNNER);
			return "";
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return "";
		}

		@Override
		public Perk getPreviousLevelPerk() {
			return RUNNER;
		}

		@Override
		public Perk getNextLevelPerk() {
			return null;
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You're the " + (owner.isFeminine() ? "queen" : "king") + " of cardio, possessing a seemingly endless reserve of energy.";
			else
				return UtilText.parse(owner, "[npc.Name] is the " + (owner.isFeminine() ? "queen" : "king") + " of cardio, possessing a seemingly endless reserve of energy.");
		}
	},
	FEMALE_ATTRACTION(60,
			"ladykiller",
			PerkLevel.LEVEL_ONE,
			PerkCategory.FITNESS,
			"fitness_female_attraction",
			Colour.FEMININE,
			null,
			Util.newArrayListOfValues(new ListValue<>("+10% <span style='color:" + Colour.DAMAGE_TYPE_MANA.toWebHexString() + ";'>willpower damage</span>"
					+ " vs <span style='color:" + Colour.FEMININE.toWebHexString()+ ";'>feminine opponents</span>"))) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You're very flirtatious, and although your charms work well on both sexes, you find that you get more opportunities to seduce women than you do men.";
			else
				return UtilText.parse(owner, "[npc.Name] is very popular with the ladies.");
		}

		@Override
		public boolean isAvailable(GameCharacter character, List<PerkInterface> additionalPerks) {
			if (additionalPerks != null) {
				if (character.hasPerk(Perk.MALE_ATTRACTION)
						|| additionalPerks.contains(Perk.MALE_ATTRACTION))
					return false;
			} else if (character.hasPerk(Perk.MALE_ATTRACTION))
				return false;

			if (character.getLevel() < this.requiredLevel.getLevel())
				return false;

			if (this.getPreviousLevelPerk() != null) {
				if (additionalPerks == null) {
					if (character.hasPerk(this.getPreviousLevelPerk()))
						return true;
				} else {
					if (character.hasPerk(this.getPreviousLevelPerk())
							|| additionalPerks.contains(this.getPreviousLevelPerk()))
						return true;
				}
				return false;
			} else
				return true;
		}

		@Override
		public List<String> getPerkRequirements(GameCharacter character, List<PerkInterface> additionalPerks) {
			perkRequirementsList.clear();

			if (character.getLevel() < this.requiredLevel.getLevel())
				perkRequirementsList.add("<span style='color:"
						+ Colour.GENERIC_BAD.toWebHexString()
						+ ";'>Level "
						+ requiredLevel.getLevel()
						+ "</span>");
			else
				perkRequirementsList.add("<span style='color:"
						+ Colour.GENERIC_GOOD.toWebHexString()
						+ ";'>Level "
						+ requiredLevel.getLevel()
						+ "</span>");

			if (this.getPreviousLevelPerk() != null) {
				if (character.hasPerk(this.getPreviousLevelPerk())
						|| additionalPerks.contains(this.getPreviousLevelPerk()))
					perkRequirementsList.add("<span style='color:"
							+ Colour.GENERIC_GOOD.toWebHexString()
							+ ";'>Unlocked '"
							+ this.getPreviousLevelPerk().getName(character)
							+ "'</span>");
				else
					perkRequirementsList.add("<span style='color:"
							+ Colour.GENERIC_BAD.toWebHexString()
							+ ";'>Unlocked '"
							+ this.getPreviousLevelPerk().getName(character)
							+ "'</span>");
			}
			if (character.hasPerk(Perk.MALE_ATTRACTION)
					|| additionalPerks.contains(Perk.MALE_ATTRACTION))
				perkRequirementsList.add("<span style='color:"
						+ Colour.GENERIC_BAD.toWebHexString()
						+ ";'>Incompatible with 'Minx'</span>");
			else
				perkRequirementsList.add("<span style='color:"
						+ Colour.GENERIC_GOOD.toWebHexString()
						+ ";'>Incompatible with 'Minx'</span>");

			return perkRequirementsList;
		}
	},
	MALE_ATTRACTION(60,
			"minx",
			PerkLevel.LEVEL_ONE,
			PerkCategory.FITNESS,
			"fitness_male_attraction",
			Colour.MASCULINE,
			null,
			Util.newArrayListOfValues(new ListValue<>("+10% <span style='color:" + Colour.DAMAGE_TYPE_MANA.toWebHexString() + ";'>willpower damage</span>"
					+ " vs <span style='color:" + Colour.MASCULINE.toWebHexString()+ ";'>masculine opponents</span>"))) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You're quite a tease, and although your charms work well on both sexes, you find that you get more opportunities to seduce men than you do women.";
			else
				return UtilText.parse(owner, "[npc.Name] is very popular with men.");
		}

		@Override
		public boolean isAvailable(GameCharacter character, List<PerkInterface> additionalPerks) {
			if (additionalPerks != null) {
				if (character.hasPerk(Perk.FEMALE_ATTRACTION)
						|| additionalPerks.contains(Perk.FEMALE_ATTRACTION))
					return false;
			} else if (character.hasPerk(Perk.FEMALE_ATTRACTION))
				return false;

			if (character.getLevel() < this.requiredLevel.getLevel())
				return false;

			if (this.getPreviousLevelPerk() != null) {
				if (additionalPerks == null) {
					if (character.hasPerk(this.getPreviousLevelPerk()))
						return true;
				} else {
					if (character.hasPerk(this.getPreviousLevelPerk())
							|| additionalPerks.contains(this.getPreviousLevelPerk()))
						return true;
				}
				return false;
			} else
				return true;
		}

		@Override
		public List<String> getPerkRequirements(GameCharacter character, List<PerkInterface> additionalPerks) {
			perkRequirementsList.clear();

			if (character.getLevel() < this.requiredLevel.getLevel())
				perkRequirementsList.add("<span style='color:"
						+ Colour.GENERIC_BAD.toWebHexString()
						+ ";'>Level "
						+ requiredLevel.getLevel()
						+ "</span>");
			else
				perkRequirementsList.add("<span style='color:"
						+ Colour.GENERIC_GOOD.toWebHexString()
						+ ";'>Level "
						+ requiredLevel.getLevel()
						+ "</span>");

			if (this.getPreviousLevelPerk() != null) {
				if (character.hasPerk(this.getPreviousLevelPerk())
						|| additionalPerks.contains(this.getPreviousLevelPerk()))
					perkRequirementsList.add("<span style='color:"
							+ Colour.GENERIC_GOOD.toWebHexString()
							+ ";'>Unlocked '"
							+ this.getPreviousLevelPerk().getName(character)
							+ "'</span>");
				else
					perkRequirementsList.add("<span style='color:"
							+ Colour.GENERIC_BAD.toWebHexString()
							+ ";'>Unlocked '"
							+ this.getPreviousLevelPerk().getName(character)
							+ "'</span>");
			}
			if (character.hasPerk(Perk.FEMALE_ATTRACTION)
					|| additionalPerks.contains(Perk.FEMALE_ATTRACTION))
				perkRequirementsList.add("<span style='color:"
						+ Colour.GENERIC_BAD.toWebHexString()
						+ ";'>Incompatible with 'Ladykiller'</span>");
			else
				perkRequirementsList.add("<span style='color:"
						+ Colour.GENERIC_GOOD.toWebHexString()
						+ ";'>Incompatible with 'Ladykiller'</span>");

			return perkRequirementsList;
		}

	},
	
	NYMPHOMANIAC(20,
			"nymphomaniac",
			PerkLevel.LEVEL_ONE,
			PerkCategory.FITNESS,
			"fitness_nymphomaniac",
			Colour.GENERIC_SEX,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.RESISTANCE_MANA, -25)),
			Util.newArrayListOfValues(new ListValue<>("Doubles <span style='color:" + Colour.GENERIC_ARCANE.toWebHexString()+ ";'>arcane essence gain</span> from each orgasm"))) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return "";
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return "";
		}

		@Override
		public Perk getPreviousLevelPerk() {
			return null;
		}

		@Override
		public Perk getNextLevelPerk() {
			return null;
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You are completely and hopelessly addicted to sex.";
			else
				return UtilText.parse(owner, "[npc.Name] is completely and hopelessly addicted to sex.");
		}
	},
	
	SEDUCTION(20,
			"flirty",
			PerkLevel.LEVEL_ONE,
			PerkCategory.FITNESS,
			"fitness_seduction_1",
			Colour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_MANA, 5)),
			null) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return "";
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return "";
		}

		@Override
		public Perk getPreviousLevelPerk() {
			return null;
		}

		@Override
		public Perk getNextLevelPerk() {
			return SEDUCTION_2;
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You love flirting, and, from your experience, your partners love it too!";
			else
				return UtilText.parse(owner, "[npc.Name] is extremely flirty.");
		}
	},
	SEDUCTION_2(20,
			"seductive",
			PerkLevel.LEVEL_FIVE,
			PerkCategory.FITNESS,
			"fitness_seduction_2",
			Colour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_MANA, 10)),
			null) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			character.removePerk(SEDUCTION);
			return "";
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return "";
		}

		@Override
		public Perk getPreviousLevelPerk() {
			return SEDUCTION;
		}

		@Override
		public Perk getNextLevelPerk() {
			return SEDUCTION_3;
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You're somewhat more than the typical flirt. You know just how to move your body in order to seduce even the most frigid of potential partners.";
			else
				return UtilText.parse(owner, "[npc.Name] moves in a highly seductive manner.");
		}
	},
	SEDUCTION_3(20,
			"sex bomb",
			PerkLevel.LEVEL_TEN,
			PerkCategory.FITNESS,
			"fitness_seduction_3",
			Colour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_MANA, 15)),
			null) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			character.removePerk(SEDUCTION_2);
			return "";
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return "";
		}

		@Override
		public Perk getPreviousLevelPerk() {
			return SEDUCTION_2;
		}

		@Override
		public Perk getNextLevelPerk() {
			return null;
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "Your every move drips with sexually suggestive body language. You're a walking sex bomb, and from the reactions of those around you, everyone can see it.";
			else
				return UtilText.parse(owner, "[npc.Name] is a walking sex bomb. [npc.Her] every movement drips with suggestive body language, and you can't help but feel extremely aroused just by looking at [npc.herHim].");
		}
	},
	
	BARREN(20,
			"barren",
			PerkLevel.LEVEL_ONE,
			PerkCategory.FITNESS,
			"fitness_barren",
			Colour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.FERTILITY, -100)),
			null) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return "";
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You are very infertile, and are highly unlikely to ever get pregnant.";
			} else {
				return UtilText.parse(owner, "[npc.Name] is highly unlikely to get pregnant.");
			}
		}
	};

	private int renderingPriority;
	protected String name;

	// Attributes modified by this Virtue:
	private HashMap<Attribute, Integer> attributeModifiers;

	private PerkCategory perkCategory;

	protected PerkLevel requiredLevel;

	private String SVGString;

	private List<String> extraEffects;

	private List<String> modifiersList;

	private Perk(int renderingPriority, String name, PerkLevel requiredLevel, PerkCategory perkCategory, String pathName, Colour colourShade, HashMap<Attribute, Integer> attributeModifiers, List<String> extraEffects) {

		this.renderingPriority = renderingPriority;
		this.name = name;

		this.requiredLevel = requiredLevel;
		this.perkCategory = perkCategory;

		this.attributeModifiers = attributeModifiers;

		this.extraEffects = extraEffects;

		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/perks/"
					+ pathName
					+ ".svg");
			SVGString = Util.inputStreamToString(is);

			SVGString = SVGString.replaceAll("#ff2a2a", colourShade.getShades()[0]);
			SVGString = SVGString.replaceAll("#ff5555", colourShade.getShades()[1]);
			SVGString = SVGString.replaceAll("#ff8080", colourShade.getShades()[2]);
			SVGString = SVGString.replaceAll("#ffaaaa", colourShade.getShades()[3]);
			SVGString = SVGString.replaceAll("#ffd5d5", colourShade.getShades()[4]);

			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		modifiersList = new ArrayList<>();

		if (attributeModifiers != null)
			for (Entry<Attribute, Integer> e : attributeModifiers.entrySet())
				modifiersList.add("<b>"
						+ (e.getValue() > 0 ? "+" : "")
						+ e.getValue()
						+ "</b>"
						+ " <b style='color: "
						+ e.getKey().getColour().toWebHexString()
						+ ";'>"
						+ Util.capitaliseSentence(e.getKey().getAbbreviatedName())
						+ "</b>");

		if (extraEffects != null)
			modifiersList.addAll(extraEffects);
	}

	@Override
	public boolean isAvailable(GameCharacter character) {
		return isAvailable(character, null);
	}

	@Override
	public boolean isAvailable(GameCharacter character, List<PerkInterface> additionalPerks) {
		if (character.getLevel() < this.requiredLevel.getLevel())
			return false;

		if (this.getPreviousLevelPerk() != null) {
			if (additionalPerks == null) {
				if (character.hasPerk(this.getPreviousLevelPerk()))
					return true;
			} else {
				if (character.hasPerk(this.getPreviousLevelPerk())
						|| additionalPerks.contains(this.getPreviousLevelPerk()))
					return true;
			}
			return false;
		} else
			return true;
	}

	private static List<String> perkRequirementsList = new ArrayList<>();

	@Override
	public List<String> getPerkRequirements(GameCharacter character, List<PerkInterface> additionalPerks) {
		perkRequirementsList.clear();

		if (character.getLevel() < this.requiredLevel.getLevel())
			perkRequirementsList.add("<span style='color:"
					+ Colour.GENERIC_BAD.toWebHexString()
					+ ";'>Level "
					+ requiredLevel.getLevel()
					+ "</span>");
		else
			perkRequirementsList.add("<span style='color:"
					+ Colour.GENERIC_GOOD.toWebHexString()
					+ ";'>Level "
					+ requiredLevel.getLevel()
					+ "</span>");

		if (this.getPreviousLevelPerk() != null) {
			if (character.hasPerk(this.getPreviousLevelPerk())
					|| additionalPerks.contains(this.getPreviousLevelPerk()))
				perkRequirementsList.add("<span style='color:"
						+ Colour.GENERIC_GOOD.toWebHexString()
						+ ";'>Unlocked '"
						+ this.getPreviousLevelPerk().getName(character)
						+ "'</span>");
			else
				perkRequirementsList.add("<span style='color:"
						+ Colour.GENERIC_BAD.toWebHexString()
						+ ";'>Unlocked '"
						+ this.getPreviousLevelPerk().getName(character)
						+ "'</span>");
		}

		return perkRequirementsList;
	}

	@Override
	public String getName(GameCharacter owner) {
		return name;
	}

	@Override
	public abstract String getDescription(GameCharacter target);

	@Override
	public List<String> getModifiersAsStringList() {
		return modifiersList;
	}

	@Override
	public HashMap<Attribute, Integer> getAttributeModifiers() {
		return attributeModifiers;
	}

	@Override
	public abstract String applyPerkGained(GameCharacter character);

	@Override
	public abstract String applyPerkLost(GameCharacter character);

	@Override
	public Perk getPreviousLevelPerk() {
		return null;
	}

	@Override
	public Perk getNextLevelPerk() {
		return null;
	}
	
	@Override
	public CorruptionLevel getAssociatedCorruptionLevel() {
		return CorruptionLevel.ZERO_PURE;
	}

	@Override
	public int getRenderingPriority() {
		return renderingPriority;
	}

	@Override
	public List<String> getExtraEffects() {
		return extraEffects;
	}

	@Override
	public String getSVGString() {
		return SVGString;
	}

	@Override
	public PerkLevel getRequiredLevel() {
		return requiredLevel;
	}

	@Override
	public PerkCategory getPerkCategory() {
		return perkCategory;
	}
}
