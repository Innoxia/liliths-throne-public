package com.base.game.character.attributes;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.base.game.character.GameCharacter;
import com.base.game.dialogue.utils.UtilText;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;

/**
 * @since 0.1.0
 * @version 0.1.78
 * @author Innoxia
 */
public enum Attribute {

	/**
	 * Physical strength: Health & bonus melee damage Physical corruption:
	 * -Arousal resistance & bonus arousal damage Mental strength: Composure &
	 * bonus ranged damage Mental corruption: -Arousal resistance & bonus spell
	 * damage
	 * 
	 * Modifier guidelines: 1 or 2 is normal 5 is good 10 is exceptional 15 is
	 * maximum unbelievably good
	 */

	HEALTH_MAXIMUM(0,
			"health",
			"Health",
			"healthIcon",
			Colour.ATTRIBUTE_HEALTH,
			"health",
			"nausea",
			null, null, null, null) {
				@Override
				public String getDescription(GameCharacter owner) {
					if(owner.isPlayer())
						return "How much damage you can take before being defeated. Maximum health is calculated by:</br></br>"
								+ "<b>" + GameCharacter.HEALTH_CALCULATION + "</b>";
					else
						return UtilText.genderParsing(owner,
								"How much damage "+owner.getName("the")+" can take before being defeated.");
				}
			},

	MANA_MAXIMUM(0,
			"willpower",
			"Willpower",
			"manaIcon",
			Colour.ATTRIBUTE_MANA,
			"strong-will",
			"weak-will",
			null, null, null, null) {
				@Override
				public String getDescription(GameCharacter owner) {
					if(owner.isPlayer())
						return "A measure of how much self-control you have left. Maximum willpower is calculated by:</br></br>"
								+ "<b>" + GameCharacter.MANA_CALCULATION + "</b>";
					else
						return UtilText.genderParsing(owner,
								"How much self-control "+owner.getName("the")+" has.");
				}
			},

	STAMINA_MAXIMUM(
			0,
			"stamina",
			"Stamina",
			"staminaIcon",
			Colour.ATTRIBUTE_FITNESS,
			"stamina",
			"fatigue",
			null, null, null, null) {
				@Override
				public String getDescription(GameCharacter owner) {
					if(owner.isPlayer())
						return "A measure of how much energy you have left. Maximum stamina is calculated by:</br></br>"
							+ "<b>" + GameCharacter.STAMINA_CALCULATION + "</b>";
					else
						return UtilText.genderParsing(owner,
								"How much energy "+owner.getName("the")+" has.");
				}
			},

	EXPERIENCE(0,
			"experience",
			"Experience",
			"experienceIcon",
			Colour.DAMAGE_TYPE_STAMINA,
			"learning",
			"forgetfulness",
			null, null, null, null) {
				@Override
				public String getDescription(GameCharacter owner) {
					if(owner.isPlayer())
						return "How much progress you have made to the next level.";
					else
						return UtilText.genderParsing(owner,
								"How much progress "+owner.getName("the")+" has made to the next level.");
				}
			},

	AROUSAL(0,
			"arousal",
			"Arousal",
			"arousalIcon",
			Colour.ATTRIBUTE_AROUSAL,
			"long-lasting",
			"prematurity",
			null, null, null, null) {
				@Override
				public String getDescription(GameCharacter owner) {
					if(owner.isPlayer())
						return "How aroused you currently are. You will orgasm when your arousal maxes out.";
					else
						return UtilText.genderParsing(owner,
								"How aroused "+owner.getName("the")+" is. <She> will orgasm when <her> arousal maxes out.");
				}
			},

	STRENGTH(0,
			"strength",
			"Strength",
			"strengthIcon",
			Colour.ATTRIBUTE_STRENGTH,
			"power",
			"weakness",
			Util.newArrayListOfValues(new ListValue<String>("I'm feeling stronger."), new ListValue<String>("My muscles are getting bigger!"), new ListValue<String>("I'm gaining strength.")),
			Util.newArrayListOfValues(new ListValue<String>("I don't feel any stronger."), new ListValue<String>("I feel about the same.")),
			Util.newArrayListOfValues(new ListValue<String>("I feel weaker."), new ListValue<String>("My muscles are shrinking."), new ListValue<String>("I'm getting weaker.")),
			Util.newArrayListOfValues(new ListValue<String>("<b>+1</b> <b style='color: " + Colour.ATTRIBUTE_HEALTH.() + "'>health</b> per 1 physical strength"),
					new ListValue<String>("<b>+0.25</b> <b style='color: " + Colour.DAMAGE_TYPE_PHYSICAL.() + "'>melee damage</b> per 1 physical strength"))) {
						@Override
						public String getDescription(GameCharacter owner) {
							if(owner.isPlayer())
								return "A measure of how powerful your body is, strength <b style='color:" + Colour.GENERIC_GOOD.() + ";'>passively increases</b> your"
									+ " <b style='color:" + Colour.ATTRIBUTE_HEALTH.() + ";'>maximum health</b> and <b style='color:" + Colour.DAMAGE_TYPE_PHYSICAL.() + ";'>melee damage</b>.";
							else
								return UtilText.genderParsing(owner,
										"A measure of "+owner.getName("the")+"'s strength.");
						}
					},

	INTELLIGENCE(0,
			"intelligence",
			"Intelligence",
			"intelligenceIcon",
			Colour.ATTRIBUTE_INTELLIGENCE,
			"intellect",
			"stupidity",
			Util.newArrayListOfValues(new ListValue<String>("I'm getting smarter."), new ListValue<String>("I feel like I'm getting smarter!"), new ListValue<String>("I'm feeling more intelligent.")),
			Util.newArrayListOfValues(new ListValue<String>("I don't feel any smarter."), new ListValue<String>("I feel about the same.")),
			Util.newArrayListOfValues(new ListValue<String>("I feel less intelligent."), new ListValue<String>("My mind's slowing down."), new ListValue<String>("I feel like I'm getting stupider.")),
			Util.newArrayListOfValues(new ListValue<String>("<b>+1</b> <b style='color: " + Colour.ATTRIBUTE_MANA.() + "'>composure</b> per 1 mental strength"),
					new ListValue<String>("<b>+0.5</b> <b style='color: " + Colour.DAMAGE_TYPE_SPELL.() + "'>spell damage</b> per 1 mental strength"))) {
						@Override
						public String getDescription(GameCharacter owner) {
							if(owner.isPlayer())
								return "A measure of how easily you can solve problems, intelligence <b style='color:" + Colour.GENERIC_GOOD.() + ";'>passively increases</b> your"
									+ " <b style='color:" + Colour.ATTRIBUTE_MANA.() + ";'>maximum willpower</b> and <b style='color:" + Colour.DAMAGE_TYPE_SPELL.() + ";'>spell damage</b>.";
							else
								return UtilText.genderParsing(owner,
										"A measure of "+owner.getName("the")+"'s intelligence.");
						}
					},

	FITNESS(0,
			"fitness",
			"Fitness",
			"agilityIcon",
			Colour.ATTRIBUTE_FITNESS,
			"endurance",
			"lethargy",
			Util.newArrayListOfValues(new ListValue<String>("I'm getting healthier.")), Util.newArrayListOfValues(new ListValue<String>("I feel about the same.")),
			Util.newArrayListOfValues(new ListValue<String>("I'm not as fit as I once was.")),
			Util.newArrayListOfValues(new ListValue<String>("<b>+1</b> <b style='color: " + Colour.ATTRIBUTE_FITNESS.() + "'>stamina</b> per 1 fitness"))) {
						@Override
						public String getDescription(GameCharacter owner) {
							if(owner.isPlayer())
								return "A measure of how agile and athletic you are, fitness <b style='color:" + Colour.GENERIC_GOOD.() + ";'>passively increases</b> your"
										+ " <b style='color:" + Colour.ATTRIBUTE_STAMINA.() + ";'>maximum stamina</b> and <b style='color:" + Colour.DAMAGE_TYPE_MANA.() + ";'>willpower damage</b>.";
							else
								return UtilText.genderParsing(owner,
										"A measure of "+owner.getName("the")+"'s fitness.");
						}
					},

	CORRUPTION(0,
			"corruption",
			"Corruption",
			"corruptionIcon",
			Colour.ATTRIBUTE_CORRUPTION,
			"corruption",
			"purity",
			Util.newArrayListOfValues(new ListValue<String>("My body feels so dirty."), new ListValue<String>("My body feels so filthy."), new ListValue<String>("I feel... dirtier.")),
			Util.newArrayListOfValues(new ListValue<String>("Nothing is happening."), new ListValue<String>("I feel about the same.")),
			Util.newArrayListOfValues(new ListValue<String>("I feel cleaner inside."), new ListValue<String>("The corruption is leaving my body.")),
			Util.newArrayListOfValues(new ListValue<String>("<b>-0.5</b> <b style='color: " + Colour.ATTRIBUTE_MANA.() + "'>arousal resistance</b> per 1 physical corruption"),
					new ListValue<String>("<b>+0.5</b> <b style='color: " + Colour.DAMAGE_TYPE_MANA.() + "'>arousal damage</b> per 1 physical corruption"))) {
						@Override
						public String getDescription(GameCharacter owner) {
							if(owner.isPlayer())
								return "Corruption is a measure of how dirty minded you are, and affects <b style='color:" + Colour.ATTRIBUTE_CORRUPTION.() + ";'>availability of actions both in and out of sex</b>.";
							else
								return UtilText.genderParsing(owner,
										"A measure of "+owner.getName("the")+"'s corruption.");
						}
					},
	
	// Miscellaneous attributes:

	FERTILITY(10, "fertility", "Fertility", "staminaIcon", Colour.GENERIC_SEX, "fertility", "infertility", null, null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases the likelihood of becoming pregnant.";
		}
	},
	
	VIRILITY(10, "virility", "Virility", "staminaIcon", Colour.GENERIC_SEX, "virility", "sterility", null, null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases the likelihood of impregnating someone.";
		}
	},
	
	SPELL_COST_MODIFIER(0, "spell cost reduction", "Spell cost reduction", "staminaIcon", Colour.ATTRIBUTE_MANA, "proficiency", "incompetence", null, null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point reduces spell costs by 1%.";
		}
	},

	// Combat attributes:

	CRITICAL_CHANCE(5, "critical hit chance", "Critical chance", "staminaIcon", Colour.ATTRIBUTE_HEALTH, "luck", "misfortune", null, null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point gives 1% chance to perform a critical hit.";
		}
	},
	CRITICAL_DAMAGE(150, "critical hit damage", "Critical damage", "staminaIcon", Colour.ATTRIBUTE_HEALTH, "impact", "failure", null, null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point gives 1% extra critical hit damage.";
		}
	},

	RESISTANCE_ATTACK(0, "attack resistance", "Attack resistance", "shieldIcon", Colour.ATTRIBUTE_HEALTH, "blocking", "weak defence", null, null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point reduces basic attack taken by 1%.";
		}
	},
	RESISTANCE_SPELLS(0, "spell resistance", "Spell resistance", "shieldIcon", Colour.ATTRIBUTE_MANA, "nullification", "arcane weakness", null, null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point reduces spell damage taken by 1%.";
		}
	},

	RESISTANCE_PHYSICAL(0, "physical resistance", "Physical resist", "shieldIcon", Colour.DAMAGE_TYPE_PHYSICAL, "toughness", "softness", null, null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point reduces physical damage taken by 1%.";
		}
	},
	RESISTANCE_MANA(0, "willpower resistance", "Willpower resist", "shieldIcon", Colour.DAMAGE_TYPE_MANA, "willpower", "temptation", null, null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point reduces willpower damage taken by 1%.";
		}
	},
	RESISTANCE_STAMINA(0, "stamina resistance", "Stamina resist", "shieldIcon", Colour.DAMAGE_TYPE_STAMINA, "endurance", "weakness", null, null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point reduces shock damage taken by 1%.";
		}
	},
	RESISTANCE_FIRE(0, "fire resistance", "Fire resist", "shieldIcon", Colour.DAMAGE_TYPE_FIRE, "extinguishing", "flammability", null, null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point reduces fire damage taken by 1%.";
		}
	},
	RESISTANCE_ICE(0, "cold resistance", "Cold resist", "shieldIcon", Colour.DAMAGE_TYPE_COLD, "warmth", "frostbite", null, null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point reduces cold damage taken by 1%.";
		}
	},
	RESISTANCE_POISON(0, "poison resistance", "Poison resist", "shieldIcon", Colour.DAMAGE_TYPE_POISON, "anti-venom", "sickness", null, null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point reduces poison damage taken by 1%.";
		}
	},
	RESISTANCE_PURE(0, "global resistance", "Global resist", "shieldIcon", Colour.DAMAGE_TYPE_PURE, "invulnerability", "vulnerability", null, null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point reduces all damage taken by 1%.";
		}
	},

	DAMAGE_ATTACK(100, "attack damage", "Attack damage", "swordIcon", Colour.ATTRIBUTE_HEALTH, "kung-fu", "puny hits", null, null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases basic attack damage done by 1%.";
		}
	},
	DAMAGE_SPELLS(100, "spell damage", "Spell damage", "swordIcon", Colour.ATTRIBUTE_MANA, "arcane power", "arcane dulling", null, null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases spell damage done by 1%.";
		}
	},

	DAMAGE_PHYSICAL(100, "physical damage", "Physical damage", "swordIcon", Colour.DAMAGE_TYPE_PHYSICAL, "force", "softness", null, null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases physical damage done by 1%.";
		}
	},
	DAMAGE_MANA(100, "willpower damage", "Willpower damage", "swordIcon", Colour.DAMAGE_TYPE_MANA, "seduction", "repelling", null, null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases willpower damage done by 1%.";
		}
	},
	DAMAGE_STAMINA(100, "stamina damage", "Stamina damage", "swordIcon", Colour.DAMAGE_TYPE_STAMINA, "energy", "draining", null, null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases stamina damage done by 1%.";
		}
	},
	DAMAGE_FIRE(100, "fire damage", "Fire damage", "swordIcon", Colour.DAMAGE_TYPE_FIRE, "inferno", "dying embers", null, null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases fire damage done by 1%.";
		}
	},
	DAMAGE_ICE(100, "cold damage", "Cold damage", "swordIcon", Colour.DAMAGE_TYPE_COLD, "blizzard", "slush", null, null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases cold damage done by 1%.";
		}
	},
	DAMAGE_POISON(100, "poison damage", "Poison damage", "swordIcon", Colour.DAMAGE_TYPE_POISON, "venom", "dilution", null, null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases poison damage done by 1%.";
		}
	},
	DAMAGE_PURE(0, "global damage", "Global damage", "swordIcon", Colour.DAMAGE_TYPE_PURE, "smiting", "debilitation", null, null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point increases all damage done by 1%.";
		}
	};

	private String name, nameAbbreviation, positiveEnchantment, negativeEnchantment;
	private Colour colour;
	private List<String> attributeGain, attributeNoChange, attributeLoss;
	public static final List<Attribute> attributeBonusesForEnchanting = new ArrayList<>(), baseAttributesGood = new ArrayList<>();
	private List<String> extraEffects;
	private String SVGString;
	private int baseValue;

	static {

		attributeBonusesForEnchanting.add(Attribute.CRITICAL_CHANCE);
		attributeBonusesForEnchanting.add(Attribute.CRITICAL_DAMAGE);

		attributeBonusesForEnchanting.add(Attribute.SPELL_COST_MODIFIER);

		attributeBonusesForEnchanting.add(Attribute.RESISTANCE_PHYSICAL);
		attributeBonusesForEnchanting.add(Attribute.RESISTANCE_MANA);
		attributeBonusesForEnchanting.add(Attribute.RESISTANCE_FIRE);
		attributeBonusesForEnchanting.add(Attribute.RESISTANCE_ICE);
		attributeBonusesForEnchanting.add(Attribute.RESISTANCE_POISON);
		attributeBonusesForEnchanting.add(Attribute.RESISTANCE_STAMINA);
		attributeBonusesForEnchanting.add(Attribute.RESISTANCE_PURE);

		attributeBonusesForEnchanting.add(Attribute.DAMAGE_ATTACK);
		attributeBonusesForEnchanting.add(Attribute.DAMAGE_PHYSICAL);
		attributeBonusesForEnchanting.add(Attribute.DAMAGE_SPELLS);
		attributeBonusesForEnchanting.add(Attribute.DAMAGE_MANA);
		attributeBonusesForEnchanting.add(Attribute.DAMAGE_FIRE);
		attributeBonusesForEnchanting.add(Attribute.DAMAGE_ICE);
		attributeBonusesForEnchanting.add(Attribute.DAMAGE_POISON);
		attributeBonusesForEnchanting.add(Attribute.DAMAGE_STAMINA);

		baseAttributesGood.add(Attribute.STRENGTH);
		baseAttributesGood.add(Attribute.INTELLIGENCE);
		baseAttributesGood.add(Attribute.FITNESS);
	}

	private Attribute(int baseValue, String name, String nameAbbreviation, String pathName, Colour colour, String positiveEnchantment, String negativeEnchantment,
			List<String> attributeGain,
			List<String> attributeNoChange,
			List<String> attributeLoss,
			List<String> extraEffects) {
		
		this.baseValue = baseValue;
		this.name = name;
		this.nameAbbreviation = nameAbbreviation;
		this.attributeGain = attributeGain;
		this.attributeNoChange = attributeNoChange;
		this.attributeLoss = attributeLoss;
		this.colour = colour;
		this.positiveEnchantment = positiveEnchantment;
		this.negativeEnchantment = negativeEnchantment;
		this.extraEffects = extraEffects;

		try {
			InputStream is = this.getClass().getResourceAsStream("/com/base/res/UIElements/" + pathName + ".svg");
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
		return new Attribute[] { STRENGTH, INTELLIGENCE, FITNESS, CORRUPTION };
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

	// What a mess...
	public String getAttributeChangeText(GameCharacter target, float value) {
		if(target.isPlayer()) {
			if (value > 0) {
				return (attributeGain != null
							? "<p>" + UtilText.parsePlayerThought(attributeGain.get(Util.random.nextInt(attributeGain.size()))) + "</p>"
							: "")
							+ "<p>"
								+ "You gain <b>" + value + "</b> <b style='color:"+ (this == Attribute.CORRUPTION ? Colour.GENERIC_TERRIBLE : Colour.GENERIC_EXCELLENT).() + ";'>core</b>"
								+ " <b style='color:" + this.getColour().() + ";'>" + this.getName() + "</b>!"
							+ "</p>";
	
			} else if (value < 0) {
				return (attributeLoss != null ? "<p>" + UtilText.parsePlayerThought(attributeLoss.get(Util.random.nextInt(attributeLoss.size()))) + "</p>" : "") + "<p>" + "You lose <b>" + value + "</b> <b style='color:"
						+ (this == Attribute.CORRUPTION ? Colour.GENERIC_EXCELLENT : Colour.GENERIC_TERRIBLE).() + ";'>core</b>" + " <b style='color:" + this.getColour().() + ";'>" + this.getName() + "</b>!" + "</p>";
	
			} else {
				return (attributeNoChange != null ? "<p>" + UtilText.parsePlayerThought(attributeNoChange.get(Util.random.nextInt(attributeNoChange.size()))) + "</p>" : "") + "<p>" + "Your <b style='color:"
						+ (this == Attribute.CORRUPTION ? Colour.GENERIC_TERRIBLE : Colour.GENERIC_EXCELLENT).() + ";'>core</b>" + " <b style='color:" + this.getColour().() + ";'>" + this.getName() + "</b> doesn't change..."
						+ "</p>";
			}
		} else {
			if (value > 0) {
				return UtilText.parse(target,
						"<p>"
							+ "[npc.Name] gains <b>" + value + "</b> <b style='color:"+ (this == Attribute.CORRUPTION ? Colour.GENERIC_TERRIBLE : Colour.GENERIC_EXCELLENT).() + ";'>core</b>"
							+ " <b style='color:" + this.getColour().() + ";'>" + this.getName() + "</b>!"
						+ "</p>");
	
			} else if (value < 0) {
				return UtilText.parse(target,
						"<p>"
							+ "[npc.Name] loses <b>" + value + "</b> <b style='color:" + this.getColour().() + ";'>" + this.getName() + "</b>!"
						+ "</p>");
				
	
			} else {
				return UtilText.parse(target,
						"<p>"
							+ "[npc.Name]'s <b style='color:" + this.getColour().() + ";'>" + this.getName() + "</b> remains unchanged..."
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
