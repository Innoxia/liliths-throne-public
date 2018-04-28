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
 * @version 0.2.1
 * @author Innoxia
 */
public enum Perk {
	
	// NPC Histories:
	
	JOB_PROSTITUTE(20,
			true,
			"The oldest profession",
			PerkCategory.JOB,
			"perks/jobs/prostitute",
			Colour.BASE_PINK,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 25),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_LUST, 25)),
			Util.newArrayListOfValues(new ListValue<>("[style.boldExcellent(Doubles)] all slave and self-prostitution income"))) {
		@Override
		public String getDescription(GameCharacter owner) {
			return ".";//TODO
		}
	},
	
	JOB_MUGGER(20,
			true,
			"Outlaw",
			PerkCategory.JOB,
			"perks/jobs/mugger",
			Colour.BASE_CRIMSON,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 15),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_PHYSICAL, 15)),
			Util.newArrayListOfValues(new ListValue<>("[style.boldExcellent(Triples)] all mugging income"))) {
		@Override
		public String getDescription(GameCharacter owner) {
			return ".";//TODO
		}
	},
	
	// Player Histories:
	
	JOB_UNEMPLOYED(20,
			true,
			"NEET",
			PerkCategory.JOB,
			"perks/jobs/unemployed",
			Colour.BASE_RED,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 2),
					new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 5),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_PHYSICAL, 5),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_LUST, 5)),
			Util.newArrayListOfValues(new ListValue<>("[style.boldExcellent(Boosts)] 'Well Rested' bonus"))) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "With so much free time on your hands, you've managed to improve yourself in several different ways."
					+ " You also benefit from knowing exactly how best to relax, boosting the bonus you get from sleeping.";
		}
	},
	
	JOB_OFFICE_WORKER(20,
			true,
			"The Salaryman",
			PerkCategory.JOB,
			"perks/jobs/officeWorker",
			Colour.BASE_BROWN,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.CRITICAL_DAMAGE, 50)),
			Util.newArrayListOfValues(new ListValue<>("[style.boldExcellent(+25%)] all slave income"))) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner.isFeminine()) {
				return "The Career Woman";
			} else {
				return "The Salaryman";
			}
		}
		@Override
		public String getDescription(GameCharacter owner) {
			return "From your considerable office experience, you know exactly how to motivate those working beneath you."
					+ " The stressful work environment has caused you to bottle up a lot of frustration, which manifests in increased critical damage.";
		}
	},
	
	JOB_STUDENT(20,
			true,
			"Student Discount",
			PerkCategory.JOB,
			"perks/jobs/student",
			Colour.BASE_YELLOW,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 10)),
			Util.newArrayListOfValues(new ListValue<>("[style.boldExcellent(25%)] discount in all stores"))) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Your student discount has never failed you before!"
					+ " Along with a guaranteed 25% discount in all stores, you can be confident in your ability to quickly learn new things.";
		}
	},
	
	JOB_MUSICIAN(20,
			true,
			"Arcane Composition",
			PerkCategory.JOB,
			"perks/jobs/musician",
			Colour.BASE_GREY,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 25)),
			Util.newArrayListOfValues(new ListValue<>("[style.boldExcellent(Double)] length of all spell effects"))) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "You find that your abilities as a musician translate quite well into the art of seduction."
					+ " You feel the same sort of rhythm in casting spells as you do with music, resulting in all of your spell effects lasting twice as long as usual.";
		}
	},
	
	JOB_TEACHER(20,
			true,
			"In Control",
			PerkCategory.JOB,
			"perks/jobs/teacher",
			Colour.BASE_BLUE_LIGHT,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.SPELL_COST_MODIFIER, 10)),
			Util.newArrayListOfValues(new ListValue<>("[style.boldExcellent(Triple)] all slave obedience gains"))) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "You know exactly how to deal with unruly students."
					+ " Your ability to clearly understand and explain difficult subjects is reflected in a reduced cost of casting spells.";
		}
	},
	
	JOB_WRITER(20,
			true,
			"Meditations",
			PerkCategory.JOB,
			"perks/jobs/writer",
			Colour.BASE_PURPLE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, 25)),
			Util.newArrayListOfValues(new ListValue<>("[style.boldExcellent(+25%)] to all experience gains"))) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "You keep a diary of your personal thoughts and encounters, allowing you to reflect upon and learn from your experiences."
					+ " Your keen interest in books also allows you to quickly read up on the most effective application of spells.";
		}
	},

	JOB_CHEF(20,
			true,
			"Fine Taste",
			PerkCategory.JOB,
			"perks/jobs/chef",
			Colour.BASE_ORANGE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.RESISTANCE_POISON, 50)),
			Util.newArrayListOfValues(new ListValue<>("[style.boldExcellent(Double)] all potions effects' strength and length"))) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Thanks to spending a considerable amount of time tasting food, you have both a significant resistance to poison, as well as the ability to make the most of things that are a culinary marvel.";
		}
	},

	JOB_SOLDIER(20,
			true,
			"Controlled Aggression",
			PerkCategory.JOB,
			"perks/jobs/soldier",
			Colour.BASE_GREEN,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<Attribute, Integer>(Attribute.HEALTH_MAXIMUM, 20),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_PHYSICAL, 10),
					new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 10)),
			Util.newArrayListOfValues(new ListValue<>("Your first strike in combat deals [style.boldExcellent(double)] damage"))) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "You've spent a considerable amount of time training to fight, and as a result, you are far stronger and healthier than a normal person."
					+ " Thanks to this training, you are also able to channel your aggression into your attacks.";
		}
	},

	JOB_ATHLETE(20,
			true,
			"Ten-Second Barrier",
			PerkCategory.JOB,
			"perks/jobs/athlete",
			Colour.BASE_TEAL,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 10)),
			Util.newArrayListOfValues(new ListValue<>("All non-zero escape chances in combat are boosted to [style.boldExcellent(100%)]"))) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "You are a world-class sprinter, and have a guaranteed 100% success of escaping any combat situation where running away is an option.";
		}
	},

	JOB_MAID(20,
			true,
			"Housekeeper",
			PerkCategory.JOB,
			"perks/jobs/maid",
			Colour.BASE_PINK_LIGHT,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 5),
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5)),
			Util.newArrayListOfValues(
					new ListValue<>("[style.boldExcellent(Boosted)] Maid's set bonuses"),
					new ListValue<>("[style.boldExcellent(Double)] slave income from maids and butlers"))) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "You are the perfect example of a hard-working maid, and while wearing a complete set of maid's clothes, the bonus that you receive is considerably boosted."
					+ " You also know how to train butlers and other maids to be exceptional at their jobs.";
		}
	},

	JOB_BUTLER(20,
			true,
			"Legacy of Jeeves",
			PerkCategory.JOB,
			"perks/jobs/butler",
			Colour.BASE_BLUE_STEEL,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.RESISTANCE_LUST, 25),
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5)),
			Util.newArrayListOfValues(
					new ListValue<>("[style.boldExcellent(Boosted)] Butler's set bonuses"),
					new ListValue<>("[style.boldExcellent(Double)] slave income from maids and butlers"))) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "You are the perfect example of a hard-working and composed butler, and while wearing a complete set of butler's clothes, the bonus that you receive is considerably boosted."
					+ " You also know how to train maids and other butlers to be exceptional at their jobs.";
		}
	},
	
	// Physical:
	
	PHYSICAL_BASE(20,
			false,
			"natural fitness",
			PerkCategory.PHYSICAL,
			"perks/attStrength5",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "You have a natural amount of physical fitness.";
		}
	},
	
	PHYSIQUE_1(20,
			false,
			"physically fit I",
			PerkCategory.PHYSICAL,
			"perks/attStrength1",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "You feel as though your physical fitness is improving!";
		}
	},
	
	PHYSIQUE_3(20,
			false,
			"physically fit III",
			PerkCategory.PHYSICAL,
			"perks/attStrength3",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 3)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "Your physical fitness is definitely improving!";
		}
	},

	PHYSIQUE_5(20,
			false,
			"physically fit V",
			PerkCategory.PHYSICAL,
			"perks/attStrength5",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "Your physical fitness is improving massively!";
		}
	},
	
	PHYSICAL_DAMAGE_5(20,
			false,
			"striker V",
			PerkCategory.PHYSICAL,
			"UIElements/swordIcon",
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "Your physical damage is improving massively!";
		}
	},
	
	PHYSICAL_RESISTANCE_5(20,
			false,
			"defender V",
			PerkCategory.PHYSICAL,
			"UIElements/shieldIcon",
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.RESISTANCE_PHYSICAL, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "Your physical resistance is improving massively!";
		}
	},
	
	SPELL_DAMAGE_5(20,
			false,
			"spell power V",
			PerkCategory.ARCANE,
			"perks/arcane_power_3",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "Your spell damage is improving massively!";
		}
	},
	
	SPELL_EFFICIENCY_5(20,
			false,
			"spell efficiency V",
			PerkCategory.ARCANE,
			"perks/arcane_power_3",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.SPELL_COST_MODIFIER, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "Your spell efficiency is improving massively!";
		}
	},
	
	AURA_BOOST_10(20,
			false,
			"aura reserves X",
			PerkCategory.ARCANE,
			"UIElements/shieldIcon",
			Colour.ATTRIBUTE_MANA,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, 10)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "The capacity of your aura is growing!";
		}
	},
	
	ENERGY_BOOST_10(20,
			false,
			"energy reserves X",
			PerkCategory.ARCANE,
			"UIElements/shieldIcon",
			Colour.ATTRIBUTE_HEALTH,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.HEALTH_MAXIMUM, 10)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "Your energy reserves are growing!";
		}
	},
	
	ELEMENTALIST_5(20,
			false,
			"elementalist V",
			PerkCategory.BOTH,
			"perks/elementalist5",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_FIRE, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_ICE, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_POISON, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "You are learning how to harness arcane elements more effectively.";
		}
	},
	
	
	ARCANE_BASE(20,
			false,
			"natural arcane power",
			PerkCategory.ARCANE,
			"perks/attIntelligence5",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 40)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "You have a surprisingly large amount of natural arcane power.";
		}
	},
	
	ARCANE_1(20,
			false,
			"arcane affinity I",
			PerkCategory.ARCANE,
			"perks/attIntelligence1",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "You feel as though your ability to harness the arcane is improving!";
		}
	},
	
	ARCANE_3(20,
			false,
			"arcane affinity III",
			PerkCategory.ARCANE,
			"perks/attIntelligence3",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 3)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "Your ability to harness the arcane is definitely improving!";
		}
	},

	ARCANE_5(20,
			false,
			"arcane affinity V",
			PerkCategory.ARCANE,
			"perks/attIntelligence5",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "Your ability to harness the arcane is improving massively!";
		}
	},
	
	
	SEDUCTION_1(20,
			false,
			"seductive I",
			PerkCategory.ARCANE,
			"perks/attSeduction1",
			Colour.BASE_PINK_LIGHT,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You love flirting, and, from your experience, your partners love it too!";
			else
				return UtilText.parse(owner, "[npc.Name] is extremely flirty.");
		}
	},
	
	SEDUCTION_3(20,
			false,
			"seductive III",
			PerkCategory.ARCANE,
			"perks/attSeduction3",
			Colour.BASE_PINK,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 3)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You're somewhat more than the typical flirt. You know just how to move your body in order to seduce even the most frigid of potential partners.";
			else
				return UtilText.parse(owner, "[npc.Name] moves in a highly seductive manner.");
		}
	},
	
	SEDUCTION_5(20,
			false,
			"seductive V",
			PerkCategory.ARCANE,
			"perks/attSeduction5",
			Colour.BASE_PINK_DEEP,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "Your every move drips with sexually suggestive body language. You're a walking sex bomb, and from the reactions of those around you, everyone can see it.";
			else
				return UtilText.parse(owner, "[npc.Name] is a walking sex bomb. [npc.Her] every movement drips with suggestive body language, and you can't help but feel extremely aroused just by looking at [npc.herHim].");
		}
	},
	
	SEDUCTION_5_B(20,
			false,
			"seductive V",
			PerkCategory.ARCANE,
			"perks/attSeduction5",
			Colour.BASE_PINK_DEEP,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "Your every move drips with sexually suggestive body language. You're a walking sex bomb, and from the reactions of those around you, everyone can see it.";
			else
				return UtilText.parse(owner, "[npc.Name] is a walking sex bomb. [npc.Her] every movement drips with suggestive body language, and you can't help but feel extremely aroused just by looking at [npc.herHim].");
		}
	},
	
	
	
	ARCANE_COMBATANT(20,
			true,
			"arcane combatant",
			PerkCategory.ARCANE,
			"perks/physical_brawler",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, 15),
					new Value<Attribute, Integer>(Attribute.SPELL_COST_MODIFIER, 15)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "You're quite competent at fighting using the arcane. You gain a bonus to your spell damage and effeciency.";
		}
	},
	
	AURA_RESILIENCE(20,
			true,
			"resilient aura",
			PerkCategory.ARCANE,
			"perks/fitness_runner",
			Colour.ATTRIBUTE_MANA,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, 25)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "You have a considerable pool of arcane energy stored in your aura.";
		}
	},
	
	AURA_RESILIENCE_2(20,
			true,
			"indomitable aura",
			PerkCategory.ARCANE,
			"perks/fitness_runner_2",
			Colour.ATTRIBUTE_MANA,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "Your aura reserves are seemingly endless.";
		}
	},
	
	
	BRAWLER(20,
			true,
			"brawler",
			PerkCategory.PHYSICAL,
			"perks/physical_brawler",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 15),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_PHYSICAL, 15)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You're quite competent at fighting. You gain a bonus to your physical damage and resistance.";
			else
				return UtilText.parse(owner, "[npc.Name] is a competent fighter. [npc.She] gains a bonus to [npc.her] physical damage and resistance.");
		}
	},
	
	
	OBSERVANT(60,
			true,
			"observant",
			PerkCategory.PHYSICAL,
			"perks/misc_observant",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.CRITICAL_CHANCE, 5)),
			Util.newArrayListOfValues(
					new ListValue<>("<span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>Gender detection</span>"))) {
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
	
	ARCANE_CRITICALS(60,
			true,
			"arcane precision",
			PerkCategory.PHYSICAL,
			"perks/physical_accurate",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.CRITICAL_CHANCE, 5)),
			Util.newArrayListOfValues(new ListValue<>("<span style='color:"+ Colour.GENERIC_COMBAT.toWebHexString()+ ";'>Critical</span> spells apply <b style='color:"+Colour.ATTRIBUTE_LUST.toWebHexString()+";'>Arcane weakness</b>"))) {
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
			return "Your spells are particularly effective when striking a target's weak spots."
					+ " Any critical hits from your spells apply 'Arcane weakness' for one turn (-10 to all resistances).";
		}
	},
	
//	
//	TELEPATHY(60,
//			true,
//			"arcane telepathy",
//			PerkCategory.ARCANE,
//			"perks/misc_observant",
//			Colour.GENERIC_ARCANE,
//			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 5)),
//			Util.newArrayListOfValues(new ListValue<>("<span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>Telepathic seduction</span>"))) {
//		@Override
//		public String applyPerkGained(GameCharacter character) {
//			return UtilText.parsePlayerThought("");
//		}
//
//		@Override
//		public String applyPerkLost(GameCharacter character) {
//			return UtilText.parsePlayerThought("");
//		}
//
//		@Override
//		public String getDescription(GameCharacter owner) {
//			if (owner.isPlayer()) {
//				return "By concentrating your arcane power into the mind of others, you're able to deliver a .";
//			} else {
//				return UtilText.parse(owner, "[npc.Name] is very perceptive, and [npc.she] continuously scans [npc.her] surroundings for signs of danger.");
//			}
//		}
//	},
	
//	SPELL_POWER_1(20,
//			true,
//			"arcane power",
//			PerkCategory.ARCANE,
//			"perks/arcane_power_1",
//			Colour.ATTRIBUTE_ARCANE,
//			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, 5)), null) {
//
//		@Override
//		public String getDescription(GameCharacter owner) {
//			if (owner.isPlayer())
//				return "You have focused on improving your ability to harness the arcane and cast spells.";
//			else
//				return UtilText.parse(owner, "[npc.Name] seems reasonably competent at casting spells.");
//		}
//	},
//	SPELL_POWER_2(20,
//			true,
//			"arcane conduit",
//			PerkCategory.ARCANE,
//			"perks/arcane_power_2",
//			Colour.ATTRIBUTE_ARCANE,
//			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, 10)), null) {
//
//		@Override
//		public String getDescription(GameCharacter owner) {
//			if (owner.isPlayer())
//				return "You have focused your ability to harness the arcane to the point where you can greatly enhance the effects of any spell.";
//			else
//				return UtilText.parse(owner, "[npc.Name] is highly competent at harnessing the arcane and improving [npc.her] spells.");
//		}
//	},
//	SPELL_POWER_3(20,
//			true,
//			"arcane mastery",
//			PerkCategory.ARCANE,
//			"perks/arcane_power_3",
//			Colour.ATTRIBUTE_ARCANE,
//			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, 15)), null) {
//
//		@Override
//		public String getDescription(GameCharacter owner) {
//			if (owner.isPlayer()) {
//				return "You are a master at harnessing the arcane. Even if you didn't have an aura as strong as a demon's, you'd still be one of the greatest arcane users in Dominion.";
//			} else
//				return UtilText.parse(owner, "[npc.Name] is a master of harnessing the arcane and improving [npc.her] spells.");
//		}
//	},

	FIRE_ENHANCEMENT(20,
			false,
			"firebrand",
			PerkCategory.ARCANE,
			"perks/attIntelligence3",
			Colour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_FIRE, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You have a heightened affinity with arcane fire. You know just how to manipulate it in order to maximise the damage caused.";
			else
				return UtilText.parse(owner, "[npc.Name] has a heightened affinity with arcane fire. [npc.She] knows just how to manipulate it in order to maximise the damage caused.");
		}
	},
	FIRE_ENHANCEMENT_2(20,
			false,
			"incendiary",
			PerkCategory.ARCANE,
			"perks/arcane_fire_1",
			Colour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_FIRE, 10), new Value<Attribute, Integer>(Attribute.RESISTANCE_FIRE, 10)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You are an expert at manipulating arcane fire. Not only are you able to maximise its damage, but you also have a heightened resistance to its effects.";
			else
				return UtilText.parse(owner, "[npc.Name] is an expert at manipulating arcane fire. Not only is [npc.she] able to maximise its damage, but [npc.she] also has a heightened resistance to its effects.");
		}
	},
	COLD_ENHANCEMENT(20,
			false,
			"frosty",
			PerkCategory.ARCANE,
			"perks/attIntelligence3",
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_ICE, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You have a heightened affinity with arcane ice. You know just how to manipulate it in order to maximise the damage caused.";
			else
				return UtilText.parse(owner, "[npc.Name] has a heightened affinity with arcane ice. [npc.She] knows just how to manipulate it in order to maximise the damage caused.");
		}
	},
	COLD_ENHANCEMENT_2(20,
			false,
			"ice cold",
			PerkCategory.ARCANE,
			"perks/arcane_ice_1",
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_ICE, 10), new Value<Attribute, Integer>(Attribute.RESISTANCE_ICE, 10)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You are an expert at manipulating arcane ice. Not only are you able to maximise its damage, but you also have a heightened resistance to its effects.";
			else
				return UtilText.parse(owner, "[npc.Name] is an expert at manipulating arcane ice. Not only is [npc.she] able to maximise its damage, but [npc.she] also has a heightened resistance to its effects.");
		}
	},
	POISON_ENHANCEMENT(20,
			false,
			"venomous",
			PerkCategory.ARCANE,
			"perks/attIntelligence3",
			Colour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_POISON, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You have a heightened affinity with arcane poison. You know just how to manipulate it in order to maximise the damage caused.";
			else
				return UtilText.parse(owner, "[npc.Name] has a heightened affinity with arcane poison. [npc.She] knows just how to manipulate it in order to maximise the damage caused.");
		}
	},
	POISON_ENHANCEMENT_2(20,
			false,
			"toxic",
			PerkCategory.ARCANE,
			"perks/arcane_poison_1",
			Colour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_POISON, 10), new Value<Attribute, Integer>(Attribute.RESISTANCE_POISON, 10)), null) {

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
			true,
			"runner",
			PerkCategory.PHYSICAL,
			"perks/fitness_runner",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 3)),
			Util.newArrayListOfValues(new ListValue<>("<span style='color:"+ Colour.ATTRIBUTE_PHYSIQUE.toWebHexString()+ ";'>Improved escape chance</span>"))) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You're a natural runner and possess a good level of stamina.";
			else
				return UtilText.parse(owner, "[npc.Name] is natural runner and possesses a good level of stamina.");
		}
	},
	RUNNER_2(20,
			true,
			"cardio champion",
			PerkCategory.PHYSICAL,
			"perks/fitness_runner_2",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5)),
			Util.newArrayListOfValues(new ListValue<>("<span style='color:"+ Colour.ATTRIBUTE_PHYSIQUE.toWebHexString()+ ";'>Improved escape chance</span>"))) {
		@Override
		public String getName(GameCharacter character) {
			if (character.isFeminine())
				return "Cardio Queen";
			else
				return "Cardio King";
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
			true,
			"ladykiller",
			PerkCategory.ARCANE,
			"perks/fitness_female_attraction",
			Colour.FEMININE,
			null, Util.newArrayListOfValues(new ListValue<>("+10% <span style='color:" + Attribute.DAMAGE_LUST.getColour().toWebHexString() + ";'>lust damage</span>"
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

	},
	MALE_ATTRACTION(60,
			true,
			"minx",
			PerkCategory.ARCANE,
			"perks/fitness_male_attraction",
			Colour.MASCULINE,
			null, Util.newArrayListOfValues(new ListValue<>("+10% <span style='color:" + Attribute.DAMAGE_LUST.getColour().toWebHexString() + ";'>lust damage</span>"
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

	},
	
	NYMPHOMANIAC(20,
			true,
			"nymphomaniac",
			PerkCategory.ARCANE,
			"perks/fitness_nymphomaniac",
			Colour.GENERIC_SEX,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.RESISTANCE_LUST, -25)),
			Util.newArrayListOfValues(new ListValue<>("Doubles <span style='color:" + Colour.GENERIC_ARCANE.toWebHexString()+ ";'>arcane essence gain</span> from each orgasm"))) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You are completely and hopelessly addicted to sex.";
			else
				return UtilText.parse(owner, "[npc.Name] is completely and hopelessly addicted to sex.");
		}
	},
	
	
	CLOTHING_ENCHANTER(20,
			false,
			"arcane weaver",
			PerkCategory.ARCANE,
			"perks/arcaneWeaver",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 1)),
			Util.newArrayListOfValues(new ListValue<>("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Halves cost of all clothing enchantments</span>"))) {

		@Override
		public boolean isAlwaysAvailable() {
			return true;
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You have a natural affinity for weaving arcane enchantments into items of clothing, allowing you to expend only half of the usual arcane essences when enchanting clothing.";
			} else {
				return UtilText.parse(owner, "[npc.Name] has a natural affinity for weaving arcane enchantments into items of clothing, allowing [npc.herHim] to expend only half of the usual arcane essences when enchanting clothing.");
			}
		}
	},
	
	BARREN(20,
			true,
			"barren",
			PerkCategory.PHYSICAL,
			"perks/barren",
			Colour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.FERTILITY, -100)), null) {

		@Override
		public boolean isAlwaysAvailable() {
			return true;
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You are very infertile, and are highly unlikely to ever get pregnant.";
			} else {
				return UtilText.parse(owner, "[npc.Name] is highly unlikely to get pregnant.");
			}
		}
	},
	
	FIRING_BLANKS(20,
			true,
			"firing blanks",
			PerkCategory.PHYSICAL,
			"perks/firing_blanks",
			Colour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.VIRILITY, -100)), null) {

		@Override
		public boolean isAlwaysAvailable() {
			return true;
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Your seed is incredibly weak, and you are highly unlikely to ever get anyone pregnant.";
			} else {
				return UtilText.parse(owner, "[npc.Name]'s seed is incredibly weak, and [npc.she]'s highly unlikely to ever get anyone pregnant.");
			}
		}
	};

	private int renderingPriority;
	protected String name;
	private Colour colour;
	private boolean equippableTrait;

	// Attributes modified by this Virtue:
	private HashMap<Attribute, Integer> attributeModifiers;

	private PerkCategory perkCategory;

	private String SVGString;

	private List<String> extraEffects;

	private List<String> modifiersList;

	private Perk(int renderingPriority, boolean major, String name, PerkCategory perkCategory, String pathName, Colour colour, HashMap<Attribute, Integer> attributeModifiers, List<String> extraEffects) {

		this.renderingPriority = renderingPriority;
		this.name = name;
		this.colour = colour;
		
		this.equippableTrait = major;
		
		this.perkCategory = perkCategory;

		this.attributeModifiers = attributeModifiers;

		this.extraEffects = extraEffects;

		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/"
					+ pathName
					+ ".svg");
			SVGString = Util.inputStreamToString(is);

			SVGString = SVGString.replaceAll("#ff2a2a", colour.getShades()[0]);
			SVGString = SVGString.replaceAll("#ff5555", colour.getShades()[1]);
			SVGString = SVGString.replaceAll("#ff8080", colour.getShades()[2]);
			SVGString = SVGString.replaceAll("#ffaaaa", colour.getShades()[3]);
			SVGString = SVGString.replaceAll("#ffd5d5", colour.getShades()[4]);

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

	public boolean isAlwaysAvailable() {
		return false;
	}
	
	public String getName(GameCharacter owner) {
		return name;
	}

	public Colour getColour() {
		return colour;
	}

	public boolean isEquippableTrait() {
		return equippableTrait;
	}

	public abstract String getDescription(GameCharacter target);

	public List<String> getModifiersAsStringList() {
		return modifiersList;
	}

	public HashMap<Attribute, Integer> getAttributeModifiers() {
		return attributeModifiers;
	}

	public String applyPerkGained(GameCharacter character) {
		return "";
	};

	public String applyPerkLost(GameCharacter character) {
		return "";
	};

	public CorruptionLevel getAssociatedCorruptionLevel() {
		return CorruptionLevel.ZERO_PURE;
	}

	public int getRenderingPriority() {
		return renderingPriority;
	}

	public List<String> getExtraEffects() {
		return extraEffects;
	}

	public String getSVGString() {
		return SVGString;
	}

	public PerkCategory getPerkCategory() {
		return perkCategory;
	}
}
