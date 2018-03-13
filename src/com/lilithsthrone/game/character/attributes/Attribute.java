package com.lilithsthrone.game.character.attributes;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.0
 * @version 0.1.99
 * @author Innoxia
 */
public enum Attribute {

	HEALTH_MAXIMUM(0,
			"energy",
			"Energy",
			"healthIcon",
			Colour.ATTRIBUTE_HEALTH,
			"energy",
			"lethargy",
			null) {
				@Override
				public String getDescription(GameCharacter owner) {
					if(owner.isPlayer())
						return "How much physical energy you have. You will be defeated in combat if this reaches 0.</br></br>"
								+ "<b>Stamina = " + GameCharacter.HEALTH_CALCULATION + "</b>";
					else
						return UtilText.parse(owner,
								"How much physical energy [npc.name] has. [npc.She] will be defeated in combat if this reaches 0.");
				}
			},

	MANA_MAXIMUM(0,
			"aura",
			"Aura",
			"manaIcon",
			Colour.ATTRIBUTE_MANA,
			"aura-boost",
			"aura-drain",
			null) {
				@Override
				public String getDescription(GameCharacter owner) {
					if(owner.isPlayer())
						return "A measure of the amount of arcane energy in your aura. You will be defeated in combat if this reaches 0.</br></br>"
								+ "<b>Aura = " + GameCharacter.MANA_CALCULATION + "</b>";
					else
						return UtilText.parse(owner,
								"How much arcane energy [npc.name] has in [npc.her] aura. [npc.She] will be defeated in combat if this reaches 0.");
				}
			},

	EXPERIENCE(0,
			"experience",
			"Experience",
			"experienceIcon",
			Colour.GENERIC_EXPERIENCE,
			"learning",
			"forgetfulness",
			null) {
				@Override
				public String getDescription(GameCharacter owner) {
					if(owner.isPlayer())
						return "How much progress you have made to the next level.";
					else
						return UtilText.parse(owner,
								"How much progress [npc.name] has made to the next level.");
				}
			},

	AROUSAL(0,
			"arousal",
			"Arousal",
			"arousalIcon",
			Colour.ATTRIBUTE_AROUSAL,
			"long-lasting",
			"prematurity",
			null) {
				@Override
				public String getDescription(GameCharacter owner) {
					if(owner.isPlayer())
						return "How aroused you currently are. You will orgasm when your arousal maxes out.";
					else
						return UtilText.parse(owner,
								"How aroused [npc.name] is. [npc.She] will orgasm when [npc.her] arousal maxes out.");
				}
			},
	
	LUST(0,
			"lust",
			"Lust",
			"arousalIcon",
			Colour.ATTRIBUTE_LUST,
			"passion",
			"indifference",
			null) {
				@Override
				public String getDescription(GameCharacter owner) {
					if(owner.isPlayer()) {
						return "How desperate for sexual contact you are. Your lust will move towards your resting lust value over time.</br>"
								+ "<b>Resting Lust = " + GameCharacter.RESTING_LUST_CALCULATION + "</b>";
					} else {
						return UtilText.parse(owner,
								"How desperate for sexual contact [npc.name] is. The higher [npc.her] lust, the more aura damage [npc.she] will take from seduction attacks.");
					}
				}
			},

	BLADDER(0,
			"bladder",
			"Bladder",
			"bladderIcon",
			Colour.ATTRIBUTE_BLADDER,
			"urge",
			"relieved",
			null) {
				@Override
				public String getDescription(GameCharacter owner) {
					if(owner.isPlayer()) {
						return "How urgently you need to pee. If your need is high this affects your actions.";
					} else {
						return UtilText.parse(owner,
								"How urgently [npc.name] needs to pee. The higher [npc.her] need, the more likely [npc.she] will pee.");
					}
				}
			},

	THIRST(0,
			"thirst",
			"thirst",
			"thirstIcon",
			Colour.ATTRIBUTE_THIRST,
			"thirst",
			"quenched",
			null) {
				@Override
				public String getDescription(GameCharacter owner) {
					if(owner.isPlayer()) {
						return "How much you'd like to drink. If you are very thirsty this may affect you.";
					} else {
						return UtilText.parse(owner, "How much [npc.name] would like to drink.");
					}
				}
			},

	HUNGER(0,
			"hunger",
			"hunger",
			"hungerIcon",
			Colour.ATTRIBUTE_HUNGER,
			"hunger",
			"fed",
			null) {
				@Override
				public String getDescription(GameCharacter owner) {
					if(owner.isPlayer()) {
						return "How much you'd like to eat. If you are very hungry this may affect you.";
					} else {
						return UtilText.parse(owner, "How much [npc.name] would like to eat.");
					}
				}
			},

	BLADDER_PRESSURE(0,
			"bladder",
			"Bladder",
			"bladderIcon",
			Colour.ATTRIBUTE_BLADDER,
			"urge",
			"relieved",
			null) {
				@Override
				public String getDescription(GameCharacter owner) {
					if(owner.isPlayer()) {
						return "How much faster you need to pee.";
					} else {
						return UtilText.parse(owner,
								"How much faster [npc.name] needs to pee.");
					}
				}
			},

	MAJOR_PHYSIQUE(0,
			"physique",
			"Physique",
			"strengthIcon",
			Colour.ATTRIBUTE_PHYSIQUE,
			"power",
			"weakness",
			Util.newArrayListOfValues(new ListValue<String>("<b>+2</b> <b style='color: " + Colour.ATTRIBUTE_HEALTH.toWebHexString() + "'>Energy</b> per 1 physique"))) {
						@Override
						public String getDescription(GameCharacter owner) {
							if(owner.isPlayer())
								return "A measure of how physically healthy you are, physique <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>passively increases</b> your"
									+ " <b style='color:" + Colour.ATTRIBUTE_HEALTH.toWebHexString() + ";'>maximum energy</b>.";
							else
								return UtilText.parse(owner,
										"A measure of [npc.name]'s physical health.");
						}
					},

	MAJOR_ARCANE(0,
			"arcane",
			"Arcane",
			"intelligenceIcon",
			Colour.ATTRIBUTE_ARCANE,
			"arcane-boost",
			"arcane-drain",
			Util.newArrayListOfValues(new ListValue<String>("<b>+2</b> <b style='color: " + Colour.ATTRIBUTE_MANA.toWebHexString() + "'>Aura</b> per 1 arcane"))) {
						@Override
						public String getDescription(GameCharacter owner) {
							if(owner.isPlayer())
								return "A measure of your affinity with the arcane. This <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>passively increases</b> your"
									+ " <b style='color:" + Colour.ATTRIBUTE_MANA.toWebHexString() + ";'>maximum Aura</b>.";
							else
								return UtilText.parse(owner,
										"A measure of [npc.name]'s affinity with the arcane.");
						}
					},

	MAJOR_CORRUPTION(0,
			"corruption",
			"Corruption",
			"corruptionIcon",
			Colour.ATTRIBUTE_CORRUPTION,
			"corruption",
			"purity",
			Util.newArrayListOfValues(new ListValue<String>("<b>-0.5</b> <b style='color: " + Colour.ATTRIBUTE_MANA.toWebHexString() + "'>arousal resistance</b> per 1 physical corruption"),
					new ListValue<String>("<b>+0.5</b> <b style='color: " + Colour.DAMAGE_TYPE_MANA.toWebHexString() + "'>arousal damage</b> per 1 physical corruption"))) {
						@Override
						public String getDescription(GameCharacter owner) {
							if(owner.isPlayer()) {
								return "Corruption is a measure of your perversion and depravity, and affects <b style='color:" + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + ";'>which sex actions you are comfortable performing</b>.";
							} else {
								return UtilText.parse(owner,
										"Corruption is a measure of [npc.name]'s perversion and depravity. It does <i>not</i> reflect how good or evil [npc.she] is.");
							}
						}
					},
	
	// Miscellaneous attributes:

	FERTILITY(10, "fertility", "Fertility", "shieldIcon", Colour.GENERIC_SEX, "fertility", "infertility", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases the likelihood of becoming pregnant.";
		}
	},
	
	VIRILITY(10, "virility", "Virility", "shieldIcon", Colour.GENERIC_SEX, "virility", "sterility", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases the likelihood of impregnating someone.";
		}
	},
	
	SPELL_COST_MODIFIER(0, "spell cost reduction", "Spell efficiency", "shieldIcon", Colour.ATTRIBUTE_MANA, "proficiency", "incompetence", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point reduces spell costs by 1%.";
		}
	},

	// Combat attributes:

	CRITICAL_CHANCE(5, "critical hit chance", "Critical chance", "shieldIcon", Colour.ATTRIBUTE_HEALTH, "luck", "misfortune", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point gives 1% chance to perform a critical hit.";
		}
	},
	CRITICAL_DAMAGE(150, "critical hit damage", "Critical damage", "shieldIcon", Colour.ATTRIBUTE_HEALTH, "impact", "failure", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point gives 1% extra critical hit damage.";
		}
	},

	// Resistances:
	
	RESISTANCE_SPELLS(0, "spell resistance", "Spell resistance", "shieldIcon", Colour.ATTRIBUTE_MANA, "nullification", "arcane weakness", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point reduces spell damage taken by 1%.";
		}
	},

	RESISTANCE_PHYSICAL(0, "physical resistance", "Physical resist", "shieldIcon", Colour.DAMAGE_TYPE_PHYSICAL, "toughness", "softness", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point reduces physical damage taken by 1%.";
		}
	},
	
	RESISTANCE_LUST(0, "seduction resistance", "Seduction resist", "shieldIcon", Colour.GENERIC_SEX, "chastity", "temptation", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point reduces aura and lust damage taken by 1%.";
		}
	},
	
	RESISTANCE_FIRE(0, "fire resistance", "Fire resist", "shieldIcon", Colour.DAMAGE_TYPE_FIRE, "extinguishing", "flammability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point reduces fire damage taken by 1%.";
		}
	},
	
	RESISTANCE_ICE(0, "cold resistance", "Cold resist", "shieldIcon", Colour.DAMAGE_TYPE_COLD, "warmth", "frostbite", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point reduces cold damage taken by 1%.";
		}
	},
	
	RESISTANCE_POISON(0, "poison resistance", "Poison resist", "shieldIcon", Colour.DAMAGE_TYPE_POISON, "anti-venom", "sickness", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point reduces poison damage taken by 1%.";
		}
	},

	// Damages:
	
	DAMAGE_SPELLS(0, "spell damage", "Spell damage", "swordIcon", Colour.ATTRIBUTE_MANA, "arcane power", "arcane dulling", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases spell damage done by 1%.";
		}
	},

	DAMAGE_PHYSICAL(0, "physical damage", "Physical damage", "swordIcon", Colour.DAMAGE_TYPE_PHYSICAL, "force", "softness", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases physical damage done by 1%.";
		}
	},
	
	DAMAGE_LUST(0, "seduction damage", "Seduction damage", "swordIcon", Colour.GENERIC_SEX, "seduction", "repulsion", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases aura and lust damage done by 1%.";
		}
	},
	
	DAMAGE_FIRE(0, "fire damage", "Fire damage", "swordIcon", Colour.DAMAGE_TYPE_FIRE, "inferno", "dying embers", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases fire damage done by 1%.";
		}
	},
	
	DAMAGE_ICE(0, "cold damage", "Cold damage", "swordIcon", Colour.DAMAGE_TYPE_COLD, "blizzard", "slush", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases cold damage done by 1%.";
		}
	},
	
	DAMAGE_POISON(0, "poison damage", "Poison damage", "swordIcon", Colour.DAMAGE_TYPE_POISON, "venom", "dilution", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases poison damage done by 1%.";
		}
	},
	
	// Racial:
	
	DAMAGE_ANGEL(0, "angelic damage", "Angelic damage", "swordIcon", Colour.RACE_ANGEL, "angelic-obliteration", "angelic-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases damage done vs angels by 1%.";
		}
	},
	DAMAGE_CAT_MORPH(0, "cat-morph damage", "Cat-morph damage", "swordIcon", Colour.RACE_CAT_MORPH, "cat-morph-obliteration", "cat-morph-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases damage done vs cat-morphs by 1%.";
		}
	},
	DAMAGE_COW_MORPH(0, "cow-morph damage", "Cow-morph damage", "swordIcon", Colour.RACE_COW_MORPH, "cow-morph-obliteration", "cow-morph-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases damage done vs cow-morphs by 1%.";
		}
	},
	DAMAGE_DEMON(0, "demonic damage", "Demonic damage", "swordIcon", Colour.RACE_DEMON, "demonic-obliteration", "demonic-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases damage done vs demons by 1%.";
		}
	},
	DAMAGE_DOG_MORPH(0, "dog-morph damage", "Dog-morph damage", "swordIcon", Colour.RACE_DOG_MORPH, "dog-morph-obliteration", "dog-morph-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases damage done vs dog-morphs by 1%.";
		}
	},
	DAMAGE_HARPY(0, "harpy damage", "Harpy damage", "swordIcon", Colour.RACE_HARPY, "harpy-obliteration", "harpy-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases damage done vs harpies by 1%.";
		}
	},
	DAMAGE_HORSE_MORPH(0, "horse-morph damage", "Horse-morph damage", "swordIcon", Colour.RACE_HORSE_MORPH, "horse-morph-obliteration", "horse-morph-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases damage done vs horse-morphs by 1%.";
		}
	},
	DAMAGE_IMP(0, "imp damage", "Imp damage", "swordIcon", Colour.RACE_IMP, "impish-obliteration", "impish-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases damage done vs imps by 1%.";
		}
	},
	DAMAGE_REINDEER_MORPH(0, "reindeer-morph damage", "Reindeer-morph damage", "swordIcon", Colour.RACE_REINDEER_MORPH, "reindeer-morph-obliteration", "reindeer-morph-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases damage done vs reindeer-morphs by 1%.";
		}
	},
	DAMAGE_HUMAN(0, "human damage", "Human damage", "swordIcon", Colour.RACE_HUMAN, "human-obliteration", "human-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases damage done vs humans by 1%.";
		}
	},
	DAMAGE_SQUIRREL_MORPH(0, "squirrel-morph damage", "Squirrel-morph damage", "swordIcon", Colour.RACE_SQUIRREL_MORPH, "squirrel-morph-obliteration", "squirrel-morph-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases damage done vs squirrel-morphs by 1%.";
		}
	},
	DAMAGE_ALLIGATOR_MORPH(0, "alligator-morph damage", "Alligator-morph damage", "swordIcon", Colour.RACE_ALLIGATOR_MORPH, "alligator-morph-obliteration", "alligator-morph-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases damage done vs alligator-morphs by 1%.";
		}
	},
	DAMAGE_WOLF_MORPH(0, "wolf-morph damage", "Wolf-morph damage", "swordIcon", Colour.RACE_WOLF_MORPH, "wolf-morph-obliteration", "wolf-morph-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases damage done vs wolf-morphs by 1%.";
		}
	},
	DAMAGE_SLIME(0, "slime damage", "Slime damage", "swordIcon", Colour.RACE_SLIME, "slime-obliteration", "slime-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases damage done vs slimes by 1%.";
		}
	},
	
	RESISTANCE_ANGEL(0, "angelic resistance", "Angelic resistance", "shieldIcon", Colour.RACE_ANGEL, "angelic-immunity", "angelic-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases resistance vs damage inflicted by angels by 1%.";
		}
	},
	RESISTANCE_CAT_MORPH(0, "cat-morph resistance", "Cat-morph resistance", "shieldIcon", Colour.RACE_CAT_MORPH, "cat-morph-immunity", "cat-morph-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases resistance vs damage inflicted by cat-morphs by 1%.";
		}
	},
	RESISTANCE_COW_MORPH(0, "cow-morph resistance", "Cow-morph resistance", "shieldIcon", Colour.RACE_COW_MORPH, "cow-morph-immunity", "cow-morph-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases resistance vs damage inflicted by cow-morphs by 1%.";
		}
	},
	RESISTANCE_DEMON(0, "demonic resistance", "Demonic resistance", "shieldIcon", Colour.RACE_DEMON, "demonic-immunity", "demonic-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases resistance vs damage inflicted by demons by 1%.";
		}
	},
	RESISTANCE_DOG_MORPH(0, "dog-morph resistance", "Dog-morph resistance", "shieldIcon", Colour.RACE_DOG_MORPH, "dog-morph-immunity", "dog-morph-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases resistance vs damage inflicted by dog-morphs by 1%.";
		}
	},
	RESISTANCE_HARPY(0, "harpy resistance", "Harpy resistance", "shieldIcon", Colour.RACE_HARPY, "harpy-immunity", "harpy-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases resistance vs damage inflicted by harpies by 1%.";
		}
	},
	RESISTANCE_HORSE_MORPH(0, "horse-morph resistance", "Horse-morph resistance", "shieldIcon", Colour.RACE_HORSE_MORPH, "horse-morph-immunity", "horse-morph-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases resistance vs damage inflicted by horse-morphs by 1%.";
		}
	},
	RESISTANCE_IMP(0, "imp resistance", "Imp resistance", "shieldIcon", Colour.RACE_IMP, "impish-immunity", "impish-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases resistance vs damage inflicted by imps by 1%.";
		}
	},
	RESISTANCE_REINDEER_MORPH(0, "reindeer-morph resistance", "Reindeer-morph resistance", "shieldIcon", Colour.RACE_REINDEER_MORPH, "reindeer-morph-immunity", "reindeer-morph-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases resistance vs damage inflicted by reindeer-morphs by 1%.";
		}
	},
	RESISTANCE_HUMAN(0, "human resistance", "Human resistance", "shieldIcon", Colour.RACE_HUMAN, "human-immunity", "human-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases resistance vs damage inflicted by humans by 1%.";
		}
	},
	RESISTANCE_SQUIRREL_MORPH(0, "squirrel-morph resistance", "Squirrel-morph resistance", "shieldIcon", Colour.RACE_SQUIRREL_MORPH, "squirrel-morph-immunity", "squirrel-morph-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases resistance vs damage inflicted by squirrel-morphs by 1%.";
		}
	},
	RESISTANCE_ALLIGATOR_MORPH(0, "alligator-morph resistance", "Alligator-morph resistance", "shieldIcon", Colour.RACE_ALLIGATOR_MORPH, "alligator-morph-immunity", "alligator-morph-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases resistance vs damage inflicted by alligator-morphs by 1%.";
		}
	},
	RESISTANCE_WOLF_MORPH(0, "wolf-morph resistance", "Wolf-morph resistance", "shieldIcon", Colour.RACE_WOLF_MORPH, "wolf-morph-immunity", "wolf-morph-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases resistance vs damage inflicted by wolf-morphs by 1%.";
		}
	},
	RESISTANCE_SLIME(0, "slime resistance", "Slime resistance", "shieldIcon", Colour.RACE_SLIME, "slime-immunity", "slime-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases resistance vs damage inflicted by slimes by 1%.";
		}
	};

	private String name, nameAbbreviation, positiveEnchantment, negativeEnchantment;
	private Colour colour;
	public static final List<Attribute> attributeBonusesForEnchanting = new ArrayList<>(), baseAttributesGood = new ArrayList<>();
	private List<String> extraEffects;
	private String SVGString;
	private int baseValue;

	static {
		attributeBonusesForEnchanting.add(Attribute.CRITICAL_CHANCE);
		attributeBonusesForEnchanting.add(Attribute.CRITICAL_DAMAGE);

		attributeBonusesForEnchanting.add(Attribute.SPELL_COST_MODIFIER);

		attributeBonusesForEnchanting.add(Attribute.RESISTANCE_PHYSICAL);
		attributeBonusesForEnchanting.add(Attribute.RESISTANCE_FIRE);
		attributeBonusesForEnchanting.add(Attribute.RESISTANCE_ICE);
		attributeBonusesForEnchanting.add(Attribute.RESISTANCE_POISON);

		attributeBonusesForEnchanting.add(Attribute.DAMAGE_PHYSICAL);
		attributeBonusesForEnchanting.add(Attribute.DAMAGE_SPELLS);
		attributeBonusesForEnchanting.add(Attribute.DAMAGE_FIRE);
		attributeBonusesForEnchanting.add(Attribute.DAMAGE_ICE);
		attributeBonusesForEnchanting.add(Attribute.DAMAGE_POISON);

		baseAttributesGood.add(Attribute.MAJOR_PHYSIQUE);
		baseAttributesGood.add(Attribute.MAJOR_ARCANE);
	}

	private Attribute(int baseValue, String name, String nameAbbreviation, String pathName, Colour colour, String positiveEnchantment, String negativeEnchantment,
			List<String> extraEffects) {
		
		this.baseValue = baseValue;
		this.name = name;
		this.nameAbbreviation = nameAbbreviation;
		this.colour = colour;
		this.positiveEnchantment = positiveEnchantment;
		this.negativeEnchantment = negativeEnchantment;
		this.extraEffects = extraEffects;

		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/" + pathName + ".svg");
			SVGString = Util.inputStreamToString(is);

			if (colour.getShades() != null) {
				SVGString = SVGString.replaceAll("#ff2a2a", colour.getShades()[0]);
				SVGString = SVGString.replaceAll("#ff5555", colour.getShades()[1]);
				SVGString = SVGString.replaceAll("#ff8080", colour.getShades()[2]);
				SVGString = SVGString.replaceAll("#ffaaaa", colour.getShades()[3]);
				SVGString = SVGString.replaceAll("#ffd5d5", colour.getShades()[4]);
			}

			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Attribute[] getCoreAttributes() {
		return new Attribute[] { MAJOR_PHYSIQUE, MAJOR_ARCANE, MAJOR_CORRUPTION };
	}

	public int getBaseValue() {
		return baseValue;
	}

	public String getName() {
		return name;
	}

	public String getAbbreviatedName() {
		return nameAbbreviation;
	}

	public abstract String getDescription(GameCharacter owner);

	public Colour getColour() {
		return colour;
	}

	public String getAttributeChangeText(GameCharacter target, float value) {
		if(target==null) {
			return "";
		}
		
		if(target.isPlayer()) {
			if (value > 0) {
				return "<p style='text-align:center;'>"
							+ "You gain <b>"+value+"</b> <b style='color:" + this.getColour().toWebHexString() + ";'>" + this.getName() + "</b>!"
						+ "</p>";
	
			} else if (value < 0) {
				return "<p style='text-align:center;'>"
							+ "You lose <b>" + value + "</b> <b style='color:" + this.getColour().toWebHexString() + ";'>" + this.getName() + "</b>!"
						+ "</p>";
	
			} else {
				return "<p style='text-align:center;'>"
							+ "Your <b style='color:" + this.getColour().toWebHexString() + ";'>" + this.getName() + "</b> doesn't change..."
						+ "</p>";
			}
		} else {
			if (value > 0) {
				return UtilText.parse(target,
						"<p>"
							+ "[npc.Name] gains <b>" + value + "</b> <b style='color:" + this.getColour().toWebHexString() + ";'>" + this.getName() + "</b>!"
						+ "</p>");
	
			} else if (value < 0) {
				return UtilText.parse(target,
						"<p>"
							+ "[npc.Name] loses <b>" + value + "</b> <b style='color:" + this.getColour().toWebHexString() + ";'>" + this.getName() + "</b>!"
						+ "</p>");
				
	
			} else {
				return UtilText.parse(target,
						"<p>"
							+ "[npc.Name]'s <b style='color:" + this.getColour().toWebHexString() + ";'>" + this.getName() + "</b> remains unchanged..."
						+ "</p>");
			}
		}
	}

	private StringBuilder descriptionSB = new StringBuilder();

	public String getEffectsAsStringList() {
		descriptionSB = new StringBuilder();

		if (extraEffects != null)
			for (String s : extraEffects)
				descriptionSB.append("</br>" + s);

		return descriptionSB.toString();
	}

	public String getPositiveEnchantment() {
		return positiveEnchantment;
	}

	public String getNegativeEnchantment() {
		return negativeEnchantment;
	}

	public String getSVGString() {
		return SVGString;
	}

}
