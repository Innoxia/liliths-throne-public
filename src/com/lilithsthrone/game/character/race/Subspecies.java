package com.lilithsthrone.game.character.race;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.LegConfigurationAffinity;
import com.lilithsthrone.game.character.body.Wing;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFaceType;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringCategory;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldRegion;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;

/**
 * @since 0.1.91
 * @version 0.4.0
 * @author tukaima, Innoxia
 */
public class Subspecies {
	
	// ---- TODO planned races ---- //
	
	//LIZARD_MORPH(Race.LIZARD_MORPH.getName(), Race.LIZARD_MORPH, RacialBody.LIZARD_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.LIZARD_MORPH.getName()),
	
	// AQUATIC:
	//TIGER_SHARK(Race.TIGER_SHARK.getName(), Race.TIGER_SHARK, RacialBody.TIGER_SHARK, SubspeciesPreference.FIVE_ABUNDANT,
	//		"An extremely aggressive variety of "+Race.SHARK_MORPH.getName()),
	
	// INSECTS:
	//BEE_MORPH(Race.BEE_MORPH.getName(), Race.BEE_MORPH, RacialBody.BEE_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.BEE_MORPH.getName()),
	//ROYAL_BEE(Race.ROYAL_BEE.getName(), Race.BEE_MORPH, RacialBody.ROYAL_BEE, SubspeciesPreference.ZERO_NONE,
	//		"A bipedal "+Race.BEE_MORPH.getName()+" at the top of the bee-morph hierarchy"),
	//WASP_MORPH(Race.WASP_MORPH.getName(), Race.WASP_MORPH, RacialBody.WASP_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.WASP_MORPH.getName()),
	
//	FOX_TAILED("statusEffects/race/raceFoxMorph",
//			"pipefox",
//			"pipefoxes",
//			"pipefox-boy",
//			"pipefox-girl",
//			"pipefox-boys",
//			"pipefox-girls",
//			Race.FOX_MORPH,
//			PresetColour.RACE_FOX_MORPH,
//			SubspeciesPreference.FOUR_ABUNDANT,
//			"A fox-morph with a serpentine lower body, devoid of legs.",
//			Util.newHashMapOfValues(WorldType.DOMINION)) {
//		@Override
//		public void applySpeciesChanges(Body body) {
//			//apply fox coloring
//		}
//	};
	
//	FOX_TAUR("statusEffects/race/raceFoxMorph",
//			"yegan",
//			"yegans",
//			"yegan-boy",
//			"yegan-girl",
//			"yegan-boys",
//			"yegan-girls",
//			Race.FOX_MORPH,
//			PresetColour.RACE_FOX_MORPH,
//			SubspeciesPreference.FOUR_ABUNDANT,
//			"A fox-morph a bestial lower body that walks on four legs.",
//			Util.newHashMapOfValues(WorldType.DOMINION)) {
//		@Override
//		public void applySpeciesChanges(Body body) {
//			//apply fox coloring
//		}
//	};
	
	// ---- ---- //
	
	// HUMAN:
	public static AbstractSubspecies HUMAN = new AbstractSubspecies(true,
			4000,
			"innoxia_race_human_vanilla_water",
			"innoxia_race_human_bread_roll",
			"statusEffects/race/raceHuman",
			"statusEffects/race/raceBackground",
			"human",
			"humans",
			"man",
			"woman",
			"men",
			"women",
			null,
			Nocturnality.DIURNAL,
			"Humans have a much higher resistance to the arousing effects of the arcane than any other race.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null,
			"Concerning Humans",
			"Concerning Humans",
			"HUMAN_BASIC",
			"HUMAN_ADVANCED",
			Race.HUMAN,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 10),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_HUMAN,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical human.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HUMAN) {
				return 100;
			}
			return 0;
		}
	};

	// ANGEL:
	public static AbstractSubspecies ANGEL = new AbstractSubspecies(true,
			80000,
			"innoxia_race_angel_angels_tears",
			null,
			"statusEffects/race/raceAngel",
			"statusEffects/race/raceBackground",
			"angel",
			"angels",
			"angel",
			"angel",
			"angels",
			"angels",
			null,
			Nocturnality.DIURNAL,
			"As an angel, [npc.nameIsFull] highly resistant to the arousing effects of the arcane, and [npc.is] particularly adept at fighting demons."
					+ " [npc.Her] natural instinct to protect humans, however, leaves [npc.her] quite vulnerable to them...",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 0f),
					new Value<>(Attribute.MAJOR_ARCANE, 15f),
					new Value<>(Attribute.MAJOR_CORRUPTION, -100f),
					new Value<>(Attribute.RESISTANCE_LUST, 50f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.DEMON), 50f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.HUMAN), -50f)),
			null,
			"The Protectors",
			"The Protectors",
			"ANGEL_BASIC",
			"ANGEL_ADVANCED",
			Race.ANGEL,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 0),
					new Value<>(PerkCategory.ARCANE, 5)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 0),
					new Value<>(PerkCategory.ARCANE, 2)),
			PresetColour.RACE_ANGEL,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical angel.",
			null,
			Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 10;
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"fallen angel",
					"fallen angels",
					"fallen angel",
					"fallen angel",
					"fallen angels",
					"fallen angels"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "fallen angel", false, false),
					applyNonBipedNameChange(body, "fallen angel", false, true),
					applyNonBipedNameChange(body, "fallen angel", false, false),
					applyNonBipedNameChange(body, "fallen angel", true, false),
					applyNonBipedNameChange(body, "fallen angel", false, true),
					applyNonBipedNameChange(body, "fallen angel", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.ANGEL) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};

	// DEMON:
	public static AbstractSubspecies ELDER_LILIN = new AbstractSubspecies(false,
			1000000000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceElderLilin",
			"statusEffects/race/raceBackground",
			"elder lilin",
			"elder lilin",
			"elder lilin",
			"elder lilin",
			"elder lilin",
			"elder lilin",
			null,
			Nocturnality.CATHEMERAL,
			"[npc.NameIsFull] one of the seven elder lilin, and [npc.is] one of the most powerful beings in existence. [npc.She] can transform [npc.her] body into any form [npc.she] [npc.verb(desire)], and [npc.has] absolute mastery over the arcane.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 1000f),
					new Value<>(Attribute.MAJOR_ARCANE, 1000f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 1000f),
					new Value<>(Attribute.HEALTH_MAXIMUM, 1000f),
					new Value<>(Attribute.MANA_MAXIMUM, 1000f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Unlimited)] <b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'> self-transformations</b>",
					"<b style='color: "+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Immune to race transformations</b>"),
			"Lilith's Spawn",
			"Lilith's Spawns",
			"ELDER_LILIN_BASIC",
			"ELDER_LILIN_ADVANCED",
			Race.DEMON,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_LILIN,
			SubspeciesPreference.ONE_LOW,
			"One of the seven elder lilin.",
			null,
			Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 10_000;
		}
		@Override
		public void applySpeciesChanges(Body body) {
			body.setSubspeciesOverride(ELDER_LILIN);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			// As Elder Lilin will always have a SubspeciesOverride, there is no need to set any conditional weighting for it.
			return 0;
		}
		@Override
		public AbstractAttribute getDamageMultiplier() {
			return Attribute.DAMAGE_ELDER_LILIN;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	
	public static AbstractSubspecies LILIN = new AbstractSubspecies(false,
			500000000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceLilin",
			"statusEffects/race/raceBackground",
			"lilin",
			"lilin",
			"lilin",
			"lilin",
			"lilin",
			"lilin",
			null,
			Nocturnality.CATHEMERAL,
			"[npc.Name] is a lilin, and as such is far more powerful than a regular demon. [npc.She] can transform [npc.her] body into any form [npc.she] desires, and has a vast amount of arcane power.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 500f),
					new Value<>(Attribute.MAJOR_ARCANE, 500f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 500f),
					new Value<>(Attribute.HEALTH_MAXIMUM, 500f),
					new Value<>(Attribute.MANA_MAXIMUM, 500f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Unlimited)] <b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'> self-transformations</b>",
					"<b style='color: "+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Immune to race transformations</b>"),
			"Lilith's Brood",
			"Lilith's Broods",
			"LILIN_BASIC",
			"LILIN_ADVANCED",
			Race.DEMON,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_LILIN,
			SubspeciesPreference.ONE_LOW,
			"A lilin.",
			null,
			null, null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 5_000;
		}
		@Override
		public void applySpeciesChanges(Body body) {
			body.setSubspeciesOverride(LILIN);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			// As Elder Lilin will always have a SubspeciesOverride, there is no need to set any conditional weighting for it.
			return 0;
		}
		@Override
		public AbstractAttribute getDamageMultiplier() {
			return Attribute.DAMAGE_LILIN;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	
	public static AbstractSubspecies DEMON = new AbstractSubspecies(true,
			120000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceDemon",
			"statusEffects/race/raceBackground",
			"demon",
			"demons",
			"incubus",
			"succubus",
			"incubi",
			"succubi",
			null,
			Nocturnality.CATHEMERAL,
			"Due to the fact that demons are very easily able to harness arcane power, [npc.namePos] spell-casting abilities are truly a terrifying force to behold!",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.MAJOR_ARCANE, 30f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 100f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 25f),
					new Value<>(Attribute.DAMAGE_LUST, 25f),
					new Value<>(Attribute.DAMAGE_SPELLS, 75f)),
			Util.newArrayListOfValues(
					"[style.boldDemon(Demonic)] <b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'> self-transformations</b>",
					"<b style='color: "+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Immune to race transformations</b>"),
			"Demonic Origins",
			"Demonic Origins'",
			"DEMON_BASIC",
			"DEMON_ADVANCED",
			Race.DEMON,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 10),
					new Value<>(PerkCategory.ARCANE, 5)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.RACE_DEMON,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical demon.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB,  SubspeciesSpawnRarity.THREE)), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 20;
		}
		@Override
		public String getFeralName(Body body) {
			if(body!=null) {
				AbstractRace r = body.getLegType().getRace();
				LegConfiguration legConfiguration = body.getLegConfiguration();

				switch(legConfiguration) {
					case BIPEDAL:
						return "demon";
					case ARACHNID:
					case CEPHALOPOD:
					case QUADRUPEDAL:
					case TAIL:
					case TAIL_LONG:
					case AVIAN:
					case WINGED_BIPED:
						return r==Race.HUMAN || r==Race.DEMON
								?Race.DEMON.getFeralName(new LegConfigurationAffinity(legConfiguration, getAffinity()), false)
								:"demonic-"+r.getName(body, true);
				}
			}
			return "demon";
		}
		@Override
		public void applySpeciesChanges(Body body) {
			if(Math.random()<0.25f && body.getLeg().getType().equals(LegType.DEMON_COMMON)) {
				body.getLeg().setType(null, LegType.DEMON_HOOFED);
			}
			if(Math.random()<0.2f && body.getPenis().getType()!=PenisType.NONE) {
				body.getPenis().getTesticle().setTesticleCount(null, 4);
			}
			if(body.getLegConfiguration()==LegConfiguration.BIPEDAL && body.getTailType()==TailType.DEMON_HORSE) {
				body.setTailType(TailType.DEMON_COMMON);
			}
		}
		
		@Override
		public String getName(Body body) {
			if(body ==null || body.getRaceStage()==RaceStage.GREATER) {
				if(body !=null) {
					return demonLegConfigurationNames.get(body.getLegConfiguration())[0];
				}
				return super.getName(body);
			}
			return HALF_DEMON.getName(body);
		}
		
		@Override
		public String getNamePlural(Body body) {
			if(body ==null || body.getRaceStage()==RaceStage.GREATER) {
				if(body !=null) {
					return demonLegConfigurationNames.get(body.getLegConfiguration())[1];
				}
				return super.getNamePlural(body);
			}
			return HALF_DEMON.getNamePlural(body);
		}

		@Override
		public String getSingularMaleName(Body body) {
			if(body ==null || body.getRaceStage()==RaceStage.GREATER) {
				if(body !=null) {
					return demonLegConfigurationNames.get(body.getLegConfiguration())[2];
				}
				return super.getSingularMaleName(body);
			}
			return HALF_DEMON.getSingularMaleName(body);
		}

		@Override
		public String getSingularFemaleName(Body body) {
			if(body ==null || body.getRaceStage()==RaceStage.GREATER) {
				if(body !=null) {
					return demonLegConfigurationNames.get(body.getLegConfiguration())[3];
				}
				return super.getSingularFemaleName(body);
			}
			return HALF_DEMON.getSingularFemaleName(body);
		}

		@Override
		public String getPluralMaleName(Body body) {
			if(body ==null || body.getRaceStage()==RaceStage.GREATER) {
				if(body !=null) {
					return demonLegConfigurationNames.get(body.getLegConfiguration())[4];
				}
				return super.getPluralMaleName(body);
			}
			return HALF_DEMON.getPluralMaleName(body);
		}

		@Override
		public String getPluralFemaleName(Body body) {
			if(body ==null || body.getRaceStage()==RaceStage.GREATER) {
				if(body !=null) {
					return demonLegConfigurationNames.get(body.getLegConfiguration())[5];
				}
				return super.getPluralFemaleName(body);
			}
			return HALF_DEMON.getPluralFemaleName(body);
		}

		@Override
		public String getSVGString(GameCharacter character) {
			if(character==null || character.getRaceStage()==RaceStage.GREATER) {
				return super.getSVGString(character);
			}
			return HALF_DEMON.getSVGString(character);
		}

		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			if(character==null || character.getRaceStage()==RaceStage.GREATER) {
				return super.getSVGStringDesaturated(character);
			}
			return HALF_DEMON.getSVGStringDesaturated(character);
		}

		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DEMON) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	
	public static AbstractSubspecies HALF_DEMON = new AbstractSubspecies(false,
			50000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceDemon",
			"statusEffects/race/raceBackground",
			"half-demon",
			"half-demons",
			"half-incubus",
			"half-succubus",
			"half-incubi",
			"half-succubi",
			null,
			Nocturnality.CATHEMERAL,
			"Half-demons are almost as capable as regular demons at harnessing arcane power, and as a result, [npc.namePos] spell-casting abilities are exceptionally powerful!",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.MAJOR_ARCANE, 20f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 50f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 20f),
					new Value<>(Attribute.DAMAGE_LUST, 20f),
					new Value<>(Attribute.DAMAGE_SPELLS, 60f)),
			Util.newArrayListOfValues(
					"<b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'>Limited self-transformations</b>",
					"<b style='color: "+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Immune to race transformations</b>"),
			"Demonic Half-breeds",
			"Demonic Half-breeds'",
			"HALF_DEMON_BASIC",
			"HALF_DEMON_ADVANCED",
			Race.DEMON,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 2)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 3),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 2)),
			PresetColour.RACE_HALF_DEMON,
			SubspeciesPreference.FOUR_ABUNDANT,
			"The result of copulation between a demon and a non-demonic partner.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.TWO)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.HARPY_NEST, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TWO)), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 5;
		}
		@Override
		public void applySpeciesChanges(Body body) {
			if(body.getLegConfiguration()==LegConfiguration.BIPEDAL && body.getTailType()==TailType.DEMON_HORSE) {
				body.setTailType(TailType.DEMON_COMMON);
			}
		}

		@Override
		public String getFeralName(Body body) {
			if(body!=null) {
				if(body.getHalfDemonSubspecies()!=null) {
					return body.getHalfDemonSubspecies().getFeralName(body);
				}
			}
			return DEMON.getFeralName(body);
		}
		
		@Override
		public String getName(Body body) {
			if(body ==null || body.getHalfDemonSubspecies()==null) {
				return super.getName(body);
			}
			return body.getHalfDemonSubspecies().getHalfDemonName(body)[0];
		}
		
		@Override
		public String getNamePlural(Body body) {
			if(body ==null || body.getHalfDemonSubspecies()==null) {
				return super.getNamePlural(body);
			}
			return body.getHalfDemonSubspecies().getHalfDemonName(body)[1];
		}

		@Override
		public String getSingularMaleName(Body body) {
			if(body ==null || body.getHalfDemonSubspecies()==null) {
				return super.getSingularMaleName(body);
			}
			return body.getHalfDemonSubspecies().getHalfDemonName(body)[2];
		}

		@Override
		public String getSingularFemaleName(Body body) {
			if(body ==null || body.getHalfDemonSubspecies()==null) {
				return super.getSingularFemaleName(body);
			}
			return body.getHalfDemonSubspecies().getHalfDemonName(body)[3];
		}

		@Override
		public String getPluralMaleName(Body body) {
			if(body ==null || body.getHalfDemonSubspecies()==null) {
				return super.getPluralMaleName(body);
			}
			return body.getHalfDemonSubspecies().getHalfDemonName(body)[4];
		}

		@Override
		public String getPluralFemaleName(Body body) {
			if(body ==null || body.getHalfDemonSubspecies()==null) {
				return super.getPluralFemaleName(body);
			}
			return body.getHalfDemonSubspecies().getHalfDemonName(body)[5];
		}

		@Override
		public String getSVGString(GameCharacter character) {
			if(character==null || character.getHalfDemonSubspecies()==null) {
				return Subspecies.HUMAN.getHalfDemonSVGString(character);
			}
			AbstractSubspecies coreSubspecies = character.getHalfDemonSubspecies();
			if(coreSubspecies==Subspecies.HALF_DEMON) {
				coreSubspecies = Subspecies.HUMAN;
			}
			return coreSubspecies.getHalfDemonSVGString(character);
		}

		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			if(character==null || character.getHalfDemonSubspecies()==null) {
				return Subspecies.HUMAN.getSVGStringDesaturated(character);
			}
			AbstractSubspecies coreSubspecies = character.getHalfDemonSubspecies();
			if(coreSubspecies==Subspecies.HALF_DEMON) {
				coreSubspecies = Subspecies.HUMAN;
			}
			return coreSubspecies.getSVGStringDesaturated(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DEMON) {
				if(body.getRaceWeightMap().size()>1) {
					return 200;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	
	public static AbstractSubspecies IMP = new AbstractSubspecies(false,
			1000,
			"innoxia_race_imp_impish_brew",
			null,
			"statusEffects/race/raceImp",
			"statusEffects/race/raceBackground",
			"imp",
			"imps",
			"imp",
			"imp",
			"imps",
			"imps",
			null,
			Nocturnality.CATHEMERAL,
			"[npc.NamePos] impish body has a deep, insatiable craving for sex. Due to imps' uncouth mannerisms and reputation as being on the very bottom rung of society, [npc.she] [npc.verb(find)] it difficult to seduce others...",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, -5f),
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 200f),
					new Value<>(Attribute.RESISTANCE_LUST, -25f),
					new Value<>(Attribute.DAMAGE_LUST, -75f)),
			Util.newArrayListOfValues(
					"[style.boldDemon(Demonic)] <b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'> self-transformations</b>",
					"<b style='color: "+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Immune to race transformations</b>"),
			"Impish Fiends",
			"Impish Fiends'",
			"IMP_BASIC",
			"IMP_ADVANCED",
			Race.DEMON,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 4),
					new Value<>(PerkCategory.LUST, 10),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 8),
					new Value<>(PerkCategory.LUST, 4),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_IMP,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical imp.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 1;
		}
		@Override
		public void applySpeciesChanges(Body body) {
			body.setHeight(Height.NEGATIVE_TWO_MINIMUM.getRandomValue());
			body.getPenis().setPenisLength(null, 8+Util.random.nextInt(8)); // 3-7 inches
			body.getWing().setSize(null,  WingSize.THREE_LARGE.getValue());
			if(body.getLegConfiguration()==LegConfiguration.BIPEDAL && body.getTailType()==TailType.DEMON_HORSE) {
				body.setTailType(TailType.DEMON_COMMON);
			}
		}
		@Override
		public boolean isShortStature() {
			return true;
		}
		@Override
		public AbstractAttribute getDamageMultiplier() {
			return Attribute.DAMAGE_IMP;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DEMON) {
				if(body.getHeight()==Height.NEGATIVE_TWO_MINIMUM) {
					return 150;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	
	public static AbstractSubspecies IMP_ALPHA = new AbstractSubspecies(false,
			1000,
			"innoxia_race_imp_impish_brew",
			null,
			"statusEffects/race/raceImpAlpha",
			"statusEffects/race/raceBackground",
			"alpha-imp",
			"alpha-imps",
			"alpha-imp",
			"alpha-imp",
			"alpha-imps",
			"alpha-imps",
			null,
			Nocturnality.CATHEMERAL,
			"[npc.NamePos] impish body has a deep, insatiable craving for sex. Due to imps' uncouth mannerisms and reputation as being on the very bottom rung of society, [npc.she] [npc.verb(find)] it difficult to seduce others...",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 0f),
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 200f),
					new Value<>(Attribute.RESISTANCE_LUST, -50f),
					new Value<>(Attribute.DAMAGE_LUST, -50f)),
			Util.newArrayListOfValues(
					"[style.boldDemon(Demonic)] <b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'> self-transformations</b>",
					"<b style='color: "+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Immune to race transformations</b>"),
			"Impish Fiends",
			"Impish Fiends'",
			"IMP_BASIC",
			"IMP_ADVANCED",
			Race.DEMON,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 4),
					new Value<>(PerkCategory.LUST, 10),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 8),
					new Value<>(PerkCategory.LUST, 4),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_IMP,
			SubspeciesPreference.ONE_LOW,
			"A more powerful form of imp, standing at over [style.sizes(107)] tall.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 2;
		}
		@Override
		public void applySpeciesChanges(Body body) {
			body.setHeight(Height.NEGATIVE_ONE_TINY.getRandomValue());
			body.getPenis().setPenisLength(null, 8+Util.random.nextInt(12)); // 3-8 inches
			body.getWing().setSize(null,  WingSize.THREE_LARGE.getValue());
			if(body.getLegConfiguration()==LegConfiguration.BIPEDAL && body.getTailType()==TailType.DEMON_HORSE) {
				body.setTailType(TailType.DEMON_COMMON);
			}
		}
		@Override
		public boolean isShortStature() {
			return true;
		}
		@Override
		public AbstractAttribute getDamageMultiplier() {
			return Attribute.DAMAGE_IMP;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DEMON) {
				if(body.getHeight()==Height.NEGATIVE_ONE_TINY) {
					return 150;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	
	// BOVINES:
	public static AbstractSubspecies COW_MORPH = new AbstractSubspecies(true,
			15000,
			"innoxia_race_cow_bubble_milk",
			"innoxia_race_cow_bubble_cream",
			"statusEffects/race/raceCowMorph",
			"statusEffects/race/raceBackground",
			"cattle-morph",
			"cattle-morphs",
			"bull-boy",
			"cow-girl",
			"bull-boys",
			"cow-girls",
			new FeralAttributes(
					"cattle",
					"cattle",
					"bull",
					"cow",
					"bulls",
					"cows",
					LegConfiguration.QUADRUPEDAL,
					160,
					0,
					1,
					1,
					4, false),
			Nocturnality.DIURNAL,
			"Although [npc.namePos] body possesses a great strength and toughness, [npc.her] mind isn't exactly the quickest...",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 30f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, -5f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 2f)),
			null,
			"Milking Cows",
			"Milking Cows'",
			"COW_MORPH_BASIC",
			"COW_MORPH_ADVANCED",
			Race.COW_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 10),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_COW_MORPH, SubspeciesPreference.FOUR_ABUNDANT,
			"An anthropomorphic cow, known as a 'cattle-morph' when bipedal, and a 'cowtaur'/'bulltaur' when the lower body is that of a feral cow or bull.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.TWO)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.THREE)), null, null) {
		@Override
		public String[] getHalfDemonName(Body body) {
			return new String[] {
					"minotaur",
					"minotaurs",
					"minotaur",
					"minotaur",
					"minotaurs",
					"minotaurs"};
		}
		@Override
		protected String applyNonBipedNameChange(Body body, String baseName, boolean applyFeminineForm, boolean plural) {
			if(body.getLegConfiguration()==LegConfiguration.QUADRUPEDAL) {
				return applyFeminineForm
						?("cowtaur"+(plural?"s":""))
						:("bulltaur"+(plural?"s":""));
			}
			return super.applyNonBipedNameChange(body, baseName, applyFeminineForm, plural);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.COW_MORPH) {
				return 100;
			}
			return 0;
		}
	};
	
	// CANIDS:
	public static AbstractSubspecies DOG_MORPH = new AbstractSubspecies(true,
			12000,
			"innoxia_race_dog_canine_crush",
			"innoxia_race_dog_canine_crunch",
			"statusEffects/race/raceDogMorph",
			"statusEffects/race/raceBackground",
			"dog-morph",
			"dog-morphs",
			"dog-boy",
			"dog-girl",
			"dog-boys",
			"dog-girls",
			new FeralAttributes(
					"dog",
					"dogs",
					"dog",
					"bitch",
					"dogs",
					"bitches",
					LegConfiguration.QUADRUPEDAL,
					70,
					0,
					1,
					5,
					1, false),
			Nocturnality.DIURNAL,
			"[npc.Name] always [npc.has] lots of energy, and [npc.she] [npc.verb(get)] excited about new things very easily."
					+ " [npc.She] also [npc.has] an instinctive desire to display [npc.her] dominance over innocent cat-morphs...",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.HEALTH_MAXIMUM, 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.CAT_MORPH), 5f)),
			null,
			"Canine Culture",
			"Canine Cultures",
			"DOG_MORPH_BASIC",
			"DOG_MORPH_ADVANCED",
			Race.DOG_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 3),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_DOG_MORPH, SubspeciesPreference.FOUR_ABUNDANT,
			"An anthropomorphic dog, known as a 'dog-morph' when bipedal, and a 'dogtaur' when the lower body is that of an oversized feral dog.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"hellhound",
					"hellhounds",
					"hellhound",
					"hellhound",
					"hellhounds",
					"hellhounds"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "hellhound", false, false),
					applyNonBipedNameChange(body, "hellhound", false, true),
					applyNonBipedNameChange(body, "hellhound", false, false),
					applyNonBipedNameChange(body, "hellhound", true, false),
					applyNonBipedNameChange(body, "hellhound", false, true),
					applyNonBipedNameChange(body, "hellhound", true, true)
				};
			}
			
			return names;
		}

		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DOG_MORPH) {
				return 100;
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies DOG_MORPH_BORDER_COLLIE = new AbstractSubspecies(false,
			24000,
			"innoxia_race_dog_canine_crush",
			"innoxia_race_dog_canine_crunch",
			"statusEffects/race/raceDogMorph",
			"statusEffects/race/raceBackground",
			"border collie-morph",
			"border collie-morphs",
			"border collie-boy",
			"border collie-girl",
			"border collie-boys",
			"border collie-girls",
			new FeralAttributes(
					"border collie",
					"border collies",
					"border collie dog",
					"border collie bitch",
					"border collie dogs",
					"border collie bitches",
					LegConfiguration.QUADRUPEDAL,
					65,
					0,
					1,
					5,
					1, false),
			Nocturnality.DIURNAL,
			"[npc.NameIsFull] more intelligent than an average dog-morph, and [npc.has] strong urges to try and herd people around."
					+ " [npc.She] also [npc.has] an instinctive desire to display [npc.her] dominance over innocent sheep-morphs...",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 5f),
					new Value<>(Attribute.HEALTH_MAXIMUM, 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.getRaceFromId("innoxia_sheep")), 25f)),
			null,
			"Canine Culture",
			"Canine Cultures",
			"DOG_MORPH_BASIC",
			"DOG_MORPH_ADVANCED",
			Race.DOG_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 6),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 6),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_DOG_MORPH, SubspeciesPreference.TWO_AVERAGE,
			"A particularly energetic and intelligent dog-morph, which resembles an anthropomorphised border collie."
							+ " They are known as 'border collie-morphs' when bipedal, and 'border collie-taurs' when the lower body is that of an oversized feral border collie."
							+ " To be identified as a border collie-morph, a character must be a dog-morph that has either upright or folded ears, and fluffy, black fur with white markings.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public void applySpeciesChanges(Body body) {
			body.getCoverings().put(BodyCoveringType.CANINE_FUR, new Covering(BodyCoveringType.CANINE_FUR, CoveringPattern.MARKED, CoveringModifier.FLUFFY, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_WHITE, false));
			if(body.getEar().getType()==EarType.DOG_MORPH) {
				if(Math.random()<0.5f) {
					body.getEar().setType(null, EarType.DOG_MORPH_POINTED);
				} else {
					body.getEar().setType(null, EarType.DOG_MORPH_FOLDED);
				}
			}
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"hellhound",
					"hellhounds",
					"hellhound",
					"hellhound",
					"hellhounds",
					"hellhounds"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "hellhound", false, false),
					applyNonBipedNameChange(body, "hellhound", false, true),
					applyNonBipedNameChange(body, "hellhound", false, false),
					applyNonBipedNameChange(body, "hellhound", true, false),
					applyNonBipedNameChange(body, "hellhound", false, true),
					applyNonBipedNameChange(body, "hellhound", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DOG_MORPH) {
				AbstractBodyCoveringType canineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.CANINE_FUR;
				if(body.getCoverings().get(canineFur).getPrimaryColour()==PresetColour.COVERING_BLACK
						&& body.getCoverings().get(canineFur).getSecondaryColour()==PresetColour.COVERING_WHITE
						&& body.getCoverings().get(canineFur).getPattern() == CoveringPattern.MARKED
						&& body.getCoverings().get(canineFur).getModifier() == CoveringModifier.FLUFFY
						&& (body.getEar().getType()==EarType.DOG_MORPH_FOLDED || body.getEar().getType()==EarType.DOG_MORPH_POINTED)) {
					return 150;
				}
			}
			return 0;
		}
		@Override
		public String getPathName() {
			return "res/race/neverLucky/dog/border_collie";
		}
	};
	
	public static AbstractSubspecies DOG_MORPH_DOBERMANN = new AbstractSubspecies(false,
			18000,
			"innoxia_race_dog_canine_crush",
			"innoxia_race_dog_canine_crunch",
			"statusEffects/race/raceDogMorphDobermann",
			"statusEffects/race/raceBackground",
			"dobermann",
			"dobermanns",
			"dobermann-boy",
			"dobermann-girl",
			"dobermann-boys",
			"dobermann-girls",
			new FeralAttributes(
					"dobermann",
					"dobermanns",
					"dobermann dog",
					"dobermann bitch",
					"dobermann dogs",
					"dobermann bitches",
					LegConfiguration.QUADRUPEDAL,
					70,
					0,
					1,
					5,
					1, false),
			Nocturnality.DIURNAL,
			"[npc.NameIsFull] always ready to defend those [npc.she] [npc.verb(call)] [npc.her] friend, and, thanks to [npc.her] powerful body, [npc.sheIs] able to do just that."
					+ " [npc.She] also [npc.has] an instinctive desire to display [npc.her] dominance over innocent cat-morphs...",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 15f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.CAT_MORPH), 5f)),
			null,
			"Canine Culture",
			"Canine Cultures",
			"DOG_MORPH_BASIC",
			"DOG_MORPH_ADVANCED",
			Race.DOG_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 6),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_DOG_MORPH,
			SubspeciesPreference.TWO_AVERAGE,
			"A dog-morph which resembles an anthropomorphised dobermann."
				+ " They are known as 'dobermanns' when bipedal, and 'dobermanntaurs' when the lower body is that of an oversized feral dobermann."
				+ " To be identified as a dobermann, a character must be a dog-morph that has short, black fur, with either brown, dark-brown, or tan markings.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public void applySpeciesChanges(Body body) {
			Colour secondaryColour = PresetColour.COVERING_BROWN;
			double rand = Math.random();
			if(rand<0.3f) {
				secondaryColour = PresetColour.COVERING_TAN;
			} else if(rand<0.6f) {
				secondaryColour = PresetColour.COVERING_BROWN_DARK;
			}
			body.getCoverings().put(BodyCoveringType.CANINE_FUR, new Covering(BodyCoveringType.CANINE_FUR, CoveringPattern.MARKED, CoveringModifier.SHORT, PresetColour.COVERING_BLACK, false, secondaryColour, false));
			body.getCoverings().put(BodyCoveringType.HAIR_CANINE_FUR, new Covering(BodyCoveringType.HAIR_CANINE_FUR, CoveringPattern.NONE, PresetColour.COVERING_BLACK, false, secondaryColour, false));
			body.getCoverings().put(BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, PresetColour.SKIN_EBONY, false, PresetColour.SKIN_EBONY, false));
			body.updateCoverings(true, true, true, true);
			if(body.getPenis().getType()==PenisType.DOG_MORPH) {
				body.getCoverings().put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED));
			}
			if(body.getEar().getType()==EarType.DOG_MORPH) {
				body.getEar().setType(null, EarType.DOG_MORPH_POINTED);
			}
			if(body.getTail().getType()==TailType.DOG_MORPH) {
				body.getTail().setType(null, TailType.DOG_MORPH_STUBBY);
			}
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"hellhound",
					"hellhounds",
					"hellhound",
					"hellhound",
					"hellhounds",
					"hellhounds"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "hellhound", false, false),
					applyNonBipedNameChange(body, "hellhound", false, true),
					applyNonBipedNameChange(body, "hellhound", false, false),
					applyNonBipedNameChange(body, "hellhound", true, false),
					applyNonBipedNameChange(body, "hellhound", false, true),
					applyNonBipedNameChange(body, "hellhound", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DOG_MORPH) {
				AbstractBodyCoveringType canineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.CANINE_FUR;
				
				if((body.getCoverings().get(canineFur).getPrimaryColour()==PresetColour.COVERING_BLACK
						|| body.getCoverings().get(canineFur).getPrimaryColour()==PresetColour.COVERING_JET_BLACK)
					&& (body.getCoverings().get(canineFur).getSecondaryColour()==PresetColour.COVERING_BROWN
							|| body.getCoverings().get(canineFur).getSecondaryColour()==PresetColour.COVERING_BROWN_DARK
							|| body.getCoverings().get(canineFur).getSecondaryColour()==PresetColour.COVERING_TAN)
					&& body.getCoverings().get(canineFur).getPattern() == CoveringPattern.MARKED
					&& body.getCoverings().get(canineFur).getModifier() == CoveringModifier.SHORT) {
					return 150;
				}
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies DOG_MORPH_GERMAN_SHEPHERD = new AbstractSubspecies(false,
			18000,
			"innoxia_race_dog_canine_crush",
			"innoxia_race_dog_canine_crunch",
			"statusEffects/race/raceDogMorph",
			"statusEffects/race/raceBackground",
			"german-shepherd-morph",
			"german-shepherd-morphs",
			"german-shepherd-boy",
			"german-shepherd-girl",
			"german-shepherd-boys",
			"german-shepherd-girls",
			new FeralAttributes(
					"german-shepherd",
					"german-shepherds",
					"german-shepherd dog",
					"german-shepherd bitch",
					"german-shepherd dogs",
					"german-shepherd bitches",
					LegConfiguration.QUADRUPEDAL,
					70,
					0,
					1,
					5,
					1, false),
			Nocturnality.DIURNAL,
			"[npc.NameHasFull] a primitive, wolf-like appearance, and possesses levels of strength and intelligence above that of most other dog-morphs.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 5f),
					new Value<>(Attribute.HEALTH_MAXIMUM, 5f)),
			null,
			"Canine Culture",
			"Canine Cultures",
			"DOG_MORPH_BASIC",
			"DOG_MORPH_ADVANCED",
			Race.DOG_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 6),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 6),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_DOG_MORPH,
			SubspeciesPreference.TWO_AVERAGE,
				"A strong, intelligent, and loyal subspecies of dog-morph, which resembles an anthropomorphised German-shepherd."
					+ " They are known as 'German-shepherd-morphs' when bipedal, and 'German-shepherd-taurs' when the lower body is that of an oversized feral German-shepherd."
					+ " To be identified as a German-shepherd-morph, a character must be a dog-morph that has upright ears, and fluffy, black fur with tan markings.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.FIVE)),
			Util.newHashMapOfValues(
			new Value<>(WorldType.DOMINION, SubspeciesSpawnRarity.TEN),
			new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public void applySpeciesChanges(Body body) {
			body.getCoverings().put(BodyCoveringType.CANINE_FUR, new Covering(BodyCoveringType.CANINE_FUR, CoveringPattern.MARKED, CoveringModifier.FLUFFY, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_TAN, false));
			body.getCoverings().put(BodyCoveringType.HAIR_CANINE_FUR, new Covering(BodyCoveringType.HAIR_CANINE_FUR, CoveringPattern.NONE, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_TAN, false));

			if(body.getEar().getType()==EarType.DOG_MORPH) {
				body.getEar().setType(null, EarType.DOG_MORPH_POINTED);
			}
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"hellhound",
					"hellhounds",
					"hellhound",
					"hellhound",
					"hellhounds",
					"hellhounds"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "hellhound", false, false),
					applyNonBipedNameChange(body, "hellhound", false, true),
					applyNonBipedNameChange(body, "hellhound", false, false),
					applyNonBipedNameChange(body, "hellhound", true, false),
					applyNonBipedNameChange(body, "hellhound", false, true),
					applyNonBipedNameChange(body, "hellhound", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DOG_MORPH) {
				AbstractBodyCoveringType canineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.CANINE_FUR;
				
				if(body.getCoverings().get(canineFur).getPrimaryColour()==PresetColour.COVERING_BLACK
						&& body.getCoverings().get(canineFur).getSecondaryColour()==PresetColour.COVERING_TAN
						&& body.getCoverings().get(canineFur).getPattern() == CoveringPattern.MARKED
						&& body.getCoverings().get(canineFur).getModifier() == CoveringModifier.FLUFFY
						&& body.getEar().getType()==EarType.DOG_MORPH_POINTED) {
						return 150;
				}
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies WOLF_MORPH = new AbstractSubspecies(true,
			10000,
			"innoxia_race_wolf_wolf_whiskey",
			"innoxia_race_wolf_meat_and_marrow",
			"statusEffects/race/raceWolfMorph",
			"statusEffects/race/raceBackground",
			"wolf-morph",
			"wolf-morphs",
			"wolf-boy",
			"wolf-girl",
			"wolf-boys",
			"wolf-girls",
			new FeralAttributes(
					"wolf",
					"wolves",
					"wolf",
					"wolf",
					"wolves",
					"wolves",
					LegConfiguration.QUADRUPEDAL,
					80,
					0,
					1,
					4,
					1, false),
			Nocturnality.NOCTURNAL,
			"[npc.NamePos] wolf-like body is very strong, and [npc.she] often [npc.verb(get)] powerful urges to try and dominate people [npc.she] [npc.verb(meet)].",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 20f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 5f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.DAMAGE_UNARMED, 25f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.getRaceFromId("innoxia_sheep")), 25f)),
			null,
			"Prowling Lupines",
			"Prowling Lupines'",
			"WOLF_MORPH_BASIC",
			"WOLF_MORPH_ADVANCED",
			Race.WOLF_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_WOLF_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"An anthropomorphic wolf, known as a 'wolf-morph' when bipedal, and a 'wolftaur' when the lower body is that of a typically-oversized feral wolf.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.FIVE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String getName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && (body ==null || (!body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL))) {
				return "awoo-morph";
			}
			return super.getName(body);
		}
		@Override
		public String getNamePlural(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && (body ==null || (!body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL))) {
				return "awoo-morphs";
			}
			return super.getNamePlural(body);
		}
		@Override
		public String getSingularMaleName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && body !=null && !body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL) {
				return "awoo-boi";
			}
			return super.getSingularMaleName(body);
		}
		@Override
		public String getSingularFemaleName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && body !=null && !body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL) {
				return "awoo-girl";
			}
			return super.getSingularFemaleName(body);
		}
		@Override
		public void applySpeciesChanges(Body body) {
			List<Colour> naturalWolfFurColours = Util.newArrayListOfValues(
					PresetColour.COVERING_GREY,
					PresetColour.COVERING_BLACK,
					PresetColour.COVERING_JET_BLACK);
			
			Colour c = Util.randomItemFrom(naturalWolfFurColours);
			body.getCoverings().put(BodyCoveringType.LYCAN_FUR, new Covering(BodyCoveringType.LYCAN_FUR, c));
			body.getCoverings().put(BodyCoveringType.HAIR_LYCAN_FUR, new Covering(BodyCoveringType.HAIR_LYCAN_FUR, c));
			body.getCoverings().put(BodyCoveringType.BODY_HAIR_LYCAN_FUR, new Covering(BodyCoveringType.BODY_HAIR_LYCAN_FUR, c));
		}
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"vargr",
					"vargar",
					"vargr",
					"vargr",
					"vargar",
					"vargar"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "vargr", false, false),
					applyNonBipedNameChange(body, "vargr", false, true),
					applyNonBipedNameChange(body, "vargr", false, false),
					applyNonBipedNameChange(body, "vargr", true, false),
					applyNonBipedNameChange(body, "vargr", false, true),
					applyNonBipedNameChange(body, "vargr", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.WOLF_MORPH) {
				return 100;
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies FOX_MORPH = new AbstractSubspecies(true,
			16000,
			"innoxia_race_fox_vulpines_vineyard",
			"innoxia_race_fox_chicken_pot_pie",
			"statusEffects/race/raceFoxMorph",
			"statusEffects/race/raceBackground",
			"fox-morph",
			"fox-morphs",
			"fox-boy",
			"fox-girl",
			"fox-boys",
			"fox-girls",
			new FeralAttributes(
					"fox",
					"foxes",
					"fox",
					"vixen",
					"foxes",
					"vixens",
					LegConfiguration.QUADRUPEDAL,
					45,
					0,
					1,
					4,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NameIsFull] very sly and nimble, and [npc.sheIs] able to use [npc.her] heightened senses to detect opportune moments in which to attack.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.ENERGY_SHIELDING, 1f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f)),
			null,
			"Skulking Vulpines",
			"Skulking Vulpines",
			"FOX_MORPH_BASIC",
			"FOX_MORPH_ADVANCED",
			Race.FOX_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 6),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 6),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_FOX_MORPH, SubspeciesPreference.FOUR_ABUNDANT,
			"An anthropomorphic fox, known as a 'fox-morph' when bipedal, and a 'foxtaur' when the lower body is that of a typically-oversized feral fox.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public void applySpeciesChanges(Body body) {
			AbstractSubspecies.applyFoxColoring(body);
		}

		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.FOX_MORPH) {
				return 100;
			}
			return 0;
		}
	};

	public static AbstractSubspecies FOX_MORPH_ARCTIC = new AbstractSubspecies(false,
			20000,
			"innoxia_race_fox_vulpines_vineyard",
			"innoxia_race_fox_chicken_pot_pie",
			"statusEffects/race/raceFoxMorph",
			"statusEffects/race/raceBackground",
			"arctic fox-morph",
			"arctic fox-morphs",
			"arctic fox-boy",
			"arctic fox-girl",
			"arctic fox-boys",
			"arctic fox-girls",
			new FeralAttributes(
					"arctic fox",
					"arctic foxes",
					"arctic fox",
					"arctic vixen",
					"arctic foxes",
					"arctic vixens",
					LegConfiguration.QUADRUPEDAL,
					30,
					0,
					1,
					4,
					1, false),
			Nocturnality.NOCTURNAL,
			"[npc.NameIsFull] very sly and nimble, and [npc.sheIs] able to use [npc.her] heightened senses to detect opportune moments in which to attack."
					+ " As [npc.sheIsFull] adapted to arctic conditions, [npc.sheIs] also better able to resist incoming cold damage.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.ENERGY_SHIELDING, 1f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f),
					new Value<>(Attribute.RESISTANCE_ICE, 5f)),
			null,
			"Skulking Vulpines",
			"Skulking Vulpines",
			"FOX_MORPH_BASIC",
			"FOX_MORPH_ADVANCED",
			Race.FOX_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_FOX_MORPH_ARCTIC,
			SubspeciesPreference.ONE_LOW,
			"An anthropomorphic fox with white fur, known as an 'arctic fox-morph' when bipedal, and an 'arctic foxtaur' when the lower body is that of a typically-oversized feral fox.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.SNOW, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.THREE)), null, null) {
		@Override
		public void applySpeciesChanges(Body body) {
			body.getCoverings().put(BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, PresetColour.SKIN_PALE, false, PresetColour.SKIN_PALE, true));
			body.getCoverings().put(BodyCoveringType.FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false));
			body.getCoverings().put(BodyCoveringType.HAIR_FOX_FUR, new Covering(BodyCoveringType.HAIR_FOX_FUR, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false));
			body.getCoverings().put(BodyCoveringType.BODY_HAIR_FOX_FUR, new Covering(BodyCoveringType.BODY_HAIR_FOX_FUR, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false));
			body.updateCoverings(false, false, true, true);
		}

		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.FOX_MORPH) {
				AbstractBodyCoveringType foxFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FOX_FUR;
				Covering fox_fur = body.getCoverings().get(foxFur);
				
				if(fox_fur.getPrimaryColour()==PresetColour.COVERING_WHITE && body.getTail().getType()!=TailType.FOX_MORPH_MAGIC) {
					return 150;
				}
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies FOX_MORPH_FENNEC = new AbstractSubspecies(false,
			16000,
			"innoxia_race_fox_vulpines_vineyard",
			"innoxia_race_fox_chicken_pot_pie",
			"statusEffects/race/raceFoxMorph",
			"statusEffects/race/raceBackground",
			"fennec-morph",
			"fennec-morphs",
			"fennec-boy",
			"fennec-girl",
			"fennec-boys",
			"fennec-girls",
			new FeralAttributes(
					"fennec-fox",
					"fennec-foxes",
					"fennec-fox",
					"fennec-vixen",
					"fennec-foxes",
					"fennec-vixens",
					LegConfiguration.QUADRUPEDAL,
					20,
					0,
					1,
					4,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NameIsFull] very sly and nimble, and [npc.sheIs] able to use [npc.her] heightened senses to detect opportune moments in which to attack.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.ENERGY_SHIELDING, 1f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f)),
			null,
			"Skulking Vulpines",
			"Skulking Vulpines",
			"FOX_MORPH_BASIC",
			"FOX_MORPH_ADVANCED",
			Race.FOX_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_FOX_MORPH_FENNEC,
			SubspeciesPreference.FOUR_ABUNDANT,
			"An anthropomorphic fox with distinctive large ears, and with either tan, dirty blonde, or bleach-blonde fur."
					+ " They are known as a 'fennec-morph' when bipedal, and a 'fennectaur' when the lower body is that of a typically-oversized feral fennec fox.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.DESERT, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.FIVE)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public void applySpeciesChanges(Body body) {
			Colour fennecColour = Util.randomItemFrom(Util.newArrayListOfValues(PresetColour.COVERING_DIRTY_BLONDE, PresetColour.COVERING_BLEACH_BLONDE, PresetColour.COVERING_TAN));
			
			body.getCoverings().put(BodyCoveringType.FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, fennecColour, false, fennecColour, false));
			body.getCoverings().put(BodyCoveringType.HAIR_FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, fennecColour, false, fennecColour, false));
			body.getCoverings().put(BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, PresetColour.SKIN_OLIVE, false, PresetColour.SKIN_OLIVE, false));
			body.updateCoverings(true, true, true, true);
			if(body.getPenis().getType()==PenisType.FOX_MORPH) {
				body.getCoverings().put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED));
			}
			if(body.getEar().getType()==EarType.FOX_MORPH) {
				body.getEar().setType(null, EarType.FOX_MORPH_BIG);
			}
		}

		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.FOX_MORPH) {
				AbstractBodyCoveringType foxFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FOX_FUR;
				Covering fox_fur = body.getCoverings().get(foxFur);
				List<Colour> fennecColours = Util.newArrayListOfValues(PresetColour.COVERING_DIRTY_BLONDE, PresetColour.COVERING_BLEACH_BLONDE, PresetColour.COVERING_TAN);
				
				if (fennecColours.contains(fox_fur.getPrimaryColour())
						&& (fennecColours.contains(fox_fur.getSecondaryColour()) || fox_fur.getPattern()==CoveringPattern.NONE)
						&& (body.getEar().getType()==EarType.FOX_MORPH_BIG)
						&& body.getTail().getType() != TailType.FOX_MORPH_MAGIC) {
					return 150;
				}
			}
			return 0;
		}
		@Override
		public String getPathName() {
			return "res/race/neverLucky/fox/fennec";
		}
		@Override
		public int getIconSize() {
			return 70;
		}
	};
	
	public static AbstractSubspecies FOX_ASCENDANT = new AbstractSubspecies(false,
			15000,
			"innoxia_race_fox_vulpines_vineyard",
			"innoxia_race_fox_chicken_pot_pie",
			"statusEffects/race/raceFoxMorph",
			"statusEffects/race/raceBackground",
			"youko",
			"youko",
			"youko-boy",
			"youko-girl",
			"youko-boys",
			"youko-girls",
			new FeralAttributes(
					"youko-fox",
					"youko-foxes",
					"youko-fox",
					"youko-vixen",
					"youko-foxes",
					"youko-vixens",
					LegConfiguration.QUADRUPEDAL,
					50,
					0,
					1,
					4,
					1, false),
			Nocturnality.CATHEMERAL,
			"",
			null,
			null,
			"Nine-tails",
			"Nine-tails'",
			"FOX_MORPH_BASIC",
			"FOX_MORPH_ADVANCED",
			Race.FOX_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 10)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.RACE_FOX_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A fox-morph, empowered by the gifts of a Lilin.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.YOUKO_FOREST, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.DISABLE_SPAWN_PREFERENCE)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 40;
		}
		@Override
		public int getBaseSlaveValue(GameCharacter character) {
			if(character==null) {
				return 15000;
			} else {
				return 15000 * character.getMaxTailCount();
			}
		}
		@Override
		public boolean isAbleToSelfTransform() {
			return true;
		}
		@Override
		public void applySpeciesChanges(Body body) {
			AbstractSubspecies.applyFoxColoring(body);
			if(body.getTail().getType()==TailType.FOX_MORPH) {
				body.getTail().setType(null, TailType.FOX_MORPH_MAGIC);
			}
		}
		
		@Override
		public String getStatusEffectDescription(GameCharacter character) {
			if(character.getMaxTailCount()<9) {
				return UtilText.parse(character, "[npc.NameIsFull] a fox-morph, [npc.his] service to a particular Lilin having afforded [npc.him] [npc.tailMaxCount] arcane tail"+(character.getMaxTailCount()==1?"":"s")+".");
			} else {
				return UtilText.parse(character, "[npc.NameIsFull] a fox-morph, [npc.his] vast number of arcane tails a sign of [npc.her] unending devotion to a particular Lilin.");
			}
		}

		@Override
		public Map<AbstractAttribute, Float> getStatusEffectAttributeModifiers(GameCharacter character) {
			//TODO move the variable racial bonuses out of Subspecies and put them in the special youko perks
			if(character!=null && character.getMaxTailCount()<9) {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
						new Value<>(Attribute.MAJOR_ARCANE, (float) (10*character.getMaxTailCount())),
//						new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
						new Value<>(Attribute.SPELL_COST_MODIFIER, (float) (10 + 5*character.getMaxTailCount())),
						new Value<>(Attribute.CRITICAL_DAMAGE, (float) (20 + 5*character.getMaxTailCount())));
			} else {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
						new Value<>(Attribute.MAJOR_ARCANE, 100f),
//						new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
						new Value<>(Attribute.SPELL_COST_MODIFIER, 60f),
						new Value<>(Attribute.CRITICAL_DAMAGE, 100f));
			}
		}

		@Override
		public String getSVGString(GameCharacter character) {
			if(character==null) {
				return youkoIconMap.get(9);
			}
			return getBipedBackground(youkoIconMap.get(character.getMaxTailCount()), character, this.getColour(character));
		}

		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			if(character==null) {
				return youkoDesaturatedIconMap.get(9);
			}
			return getBipedBackground(youkoDesaturatedIconMap.get(character.getMaxTailCount()), character, PresetColour.BASE_GREY);
		}
		
		@Override
		public String getHalfDemonSVGString(GameCharacter character) {
			if(character!=null && character.getSubspeciesOverride()!=null && character.getSubspeciesOverride().equals(Subspecies.DEMON)) {
				return super.getHalfDemonSVGString(character);
			} else {
				return getBipedBackground(youkoHalfDemonIconMap.get(character.getMaxTailCount()), character, PresetColour.RACE_HALF_DEMON);
			}
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.FOX_MORPH) {
				if(body.getTail().getType()==TailType.FOX_MORPH_MAGIC) {
					body.setSubspeciesOverride(Subspecies.FOX_ASCENDANT);
					return 200;
				}
			}
			return 0;
		}
	};

	public static AbstractSubspecies FOX_ASCENDANT_ARCTIC = new AbstractSubspecies(false,
			15000,
			"innoxia_race_fox_vulpines_vineyard",
			"innoxia_race_fox_chicken_pot_pie",
			"statusEffects/race/raceFoxMorph",
			"statusEffects/race/raceBackground",
			"arctic youko",
			"arctic youko",
			"arctic youko-boy",
			"arctic youko-girl",
			"arctic youko-boys",
			"arctic youko-girls",
			new FeralAttributes(
					"arctic youko-fox",
					"arctic youko-foxes",
					"arctic youko-fox",
					"arctic youko-vixen",
					"arctic youko-foxes",
					"arctic youko-vixens",
					LegConfiguration.QUADRUPEDAL,
					40,
					0,
					1,
					4,
					1, false),
			Nocturnality.CATHEMERAL,
			"",
			null,
			null, "Nine-tails",
			"Nine-tails'",
			"FOX_MORPH_BASIC",
			"FOX_MORPH_ADVANCED",
			Race.FOX_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 10)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.RACE_FOX_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"An arctic fox-morph, empowered by the gifts of a Lilin.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.YOUKO_FOREST, SubspeciesSpawnRarity.TWO)),
			Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.DISABLE_SPAWN_PREFERENCE)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 40;
		}
		@Override
		public int getBaseSlaveValue(GameCharacter character) {
			if(character==null) {
				return 15000;
			} else {
				return 15000 * character.getMaxTailCount();
			}
		}
		@Override
		public boolean isAbleToSelfTransform() {
			return true;
		}
		@Override
		public void applySpeciesChanges(Body body) {
			body.getCoverings().put(BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, PresetColour.SKIN_PALE, false, PresetColour.SKIN_PALE, true));
			body.getCoverings().put(BodyCoveringType.FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false));
			body.getCoverings().put(BodyCoveringType.HAIR_FOX_FUR, new Covering(BodyCoveringType.HAIR_FOX_FUR, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false));
			body.getCoverings().put(BodyCoveringType.BODY_HAIR_FOX_FUR, new Covering(BodyCoveringType.BODY_HAIR_FOX_FUR, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false));
			if(body.getTail().getType()==TailType.FOX_MORPH) {
				body.getTail().setType(null, TailType.FOX_MORPH_MAGIC);
			}
			body.updateCoverings(false, false, true, true);
		}
		
		@Override
		public String getStatusEffectDescription(GameCharacter character) {
			if(character.getMaxTailCount()<9) {
				return UtilText.parse(character, "[npc.NameIsFull] an arctic fox-morph, [npc.his] service to a particular Lilin having afforded [npc.him] [npc.tailMaxCount] arcane tail"+(character.getMaxTailCount()==1?"":"s")+".");
			} else {
				return UtilText.parse(character, "[npc.NameIsFull] an arctic fox-morph, [npc.his] vast number of arcane tails a sign of [npc.her] unending devotion to a particular Lilin.");
			}
		}

		@Override
		public Map<AbstractAttribute, Float> getStatusEffectAttributeModifiers(GameCharacter character) {
			if(character!=null && character.getMaxTailCount()<9) {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
						new Value<>(Attribute.MAJOR_ARCANE, (float) (10*character.getMaxTailCount())),
//						new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
						new Value<>(Attribute.SPELL_COST_MODIFIER, (float) (10 + 5*character.getMaxTailCount())),
						new Value<>(Attribute.CRITICAL_DAMAGE, (float) (20 + 5*character.getMaxTailCount())));
			} else {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
						new Value<>(Attribute.MAJOR_ARCANE, 100f),
//						new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
						new Value<>(Attribute.SPELL_COST_MODIFIER, 60f),
						new Value<>(Attribute.CRITICAL_DAMAGE, 100f));
			}
		}
		
		@Override
		public String getSVGString(GameCharacter character) {
			return FOX_ASCENDANT.getSVGString(character);
		}

		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			return FOX_ASCENDANT.getSVGStringDesaturated(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.FOX_MORPH) {
				AbstractBodyCoveringType foxFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FOX_FUR;
				Covering fox_fur = body.getCoverings().get(foxFur);
				
				if(fox_fur.getPrimaryColour()==PresetColour.COVERING_WHITE &&  body.getTail().getType() == TailType.FOX_MORPH_MAGIC) {
					body.setSubspeciesOverride(Subspecies.FOX_ASCENDANT_ARCTIC);
					return 250;
				}
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies FOX_ASCENDANT_FENNEC = new AbstractSubspecies(false,
			15000,
			"innoxia_race_fox_vulpines_vineyard",
			"innoxia_race_fox_chicken_pot_pie",
			"statusEffects/race/raceFoxMorph",
			"statusEffects/race/raceBackground",
			"fennec-youko",
			"fennec-youko",
			"fennec-youko-boy",
			"fennec-youko-girl",
			"fennec-youko-boys",
			"fennec-youko-girls",
			new FeralAttributes(
					"fennec-youko-fox",
					"fennec-youko-foxes",
					"fennec-youko-fox",
					"fennec-youko-vixen",
					"fennec-youko-foxes",
					"fennec-youko-vixens",
					LegConfiguration.QUADRUPEDAL,
					30,
					0,
					1,
					4,
					1, false),
			Nocturnality.CATHEMERAL,
			"",
			null,
			null, "Nine-tails",
			"Nine-tails'",
			"FOX_MORPH_BASIC",
			"FOX_MORPH_ADVANCED",
			Race.FOX_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 10)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.RACE_FOX_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A fennec-morph, empowered by the gifts of a Lilin.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.YOUKO_FOREST, SubspeciesSpawnRarity.ONE)),
			Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.DISABLE_SPAWN_PREFERENCE)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 40;
		}
		@Override
		public int getBaseSlaveValue(GameCharacter character) {
			if(character==null) {
				return 15000;
			} else {
				return 15000 * character.getMaxTailCount();
			}
		}
		@Override
		public boolean isAbleToSelfTransform() {
			return true;
		}
		@Override
		public void applySpeciesChanges(Body body) {
			Colour fennecColour = PresetColour.COVERING_BLEACH_BLONDE;
			double rand = Math.random();
			if(rand<0.5f) {
				fennecColour = PresetColour.COVERING_DIRTY_BLONDE;
			}
			body.getCoverings().put(BodyCoveringType.FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, fennecColour, false, fennecColour, false));
			body.getCoverings().put(BodyCoveringType.HAIR_FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, fennecColour, false, fennecColour, false));
			body.getCoverings().put(BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, PresetColour.SKIN_OLIVE, false, PresetColour.SKIN_OLIVE, false));
			body.updateCoverings(true, true, true, true);
			if(body.getPenis().getType()==PenisType.FOX_MORPH) {
				body.getCoverings().put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED));
			}
			if(body.getEar().getType()==EarType.FOX_MORPH) {
				body.getEar().setType(null, EarType.FOX_MORPH_BIG);
			}
			if(body.getTail().getType()==TailType.FOX_MORPH) {
				body.getTail().setType(null, TailType.FOX_MORPH_MAGIC);
			}
		}
		@Override
		public String getStatusEffectDescription(GameCharacter character) {
			if(character.getMaxTailCount()<9) {
				return UtilText.parse(character, "[npc.NameIsFull] a fox-morph, [npc.his] service to a particular Lilin having afforded [npc.him] [npc.tailMaxCount] arcane tail"+(character.getMaxTailCount()==1?"":"s")+".");
			} else {
				return UtilText.parse(character, "[npc.NameIsFull] a fox-morph, [npc.his] vast number of arcane tails a sign of [npc.her] unending devotion to a particular Lilin.");
			}
		}
		@Override
		public Map<AbstractAttribute, Float> getStatusEffectAttributeModifiers(GameCharacter character) {
			if(character!=null && character.getMaxTailCount()<9) {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
						new Value<>(Attribute.MAJOR_ARCANE, (float) (10*character.getMaxTailCount())),
//						new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
						new Value<>(Attribute.SPELL_COST_MODIFIER, (float) (10 + 5*character.getMaxTailCount())),
						new Value<>(Attribute.CRITICAL_DAMAGE, (float) (20 + 5*character.getMaxTailCount())));
			} else {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
						new Value<>(Attribute.MAJOR_ARCANE, 100f),
//						new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
						new Value<>(Attribute.SPELL_COST_MODIFIER, 60f),
						new Value<>(Attribute.CRITICAL_DAMAGE, 100f));
			}
		}
		@Override
		public String getSVGString(GameCharacter character) {
			return FOX_ASCENDANT.getSVGString(character);
		}
		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			return FOX_ASCENDANT.getSVGStringDesaturated(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.FOX_MORPH) {
				AbstractBodyCoveringType foxFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FOX_FUR;
				Covering fox_fur = body.getCoverings().get(foxFur);
				List<Colour> fennecColours = Util.newArrayListOfValues(PresetColour.COVERING_DIRTY_BLONDE, PresetColour.COVERING_BLEACH_BLONDE, PresetColour.COVERING_TAN);
				
				if (fennecColours.contains(fox_fur.getPrimaryColour())
						&& (fennecColours.contains(fox_fur.getSecondaryColour()) || fox_fur.getPattern()==CoveringPattern.NONE)
						&& (body.getEar().getType()==EarType.FOX_MORPH_BIG)
						&& body.getTail().getType() == TailType.FOX_MORPH_MAGIC) {
					body.setSubspeciesOverride(Subspecies.FOX_ASCENDANT_FENNEC);
					return 250;
				}
			}
			return 0;
		}
	};
	
	// FELINES:
	public static AbstractSubspecies CAT_MORPH = new AbstractSubspecies(true,
			12000,
			"innoxia_race_cat_felines_fancy",
			"innoxia_race_cat_kittys_reward",
			"statusEffects/race/raceCatMorph",
			"statusEffects/race/raceBackground",
			"cat-morph",
			"cat-morphs",
			"cat-boy",
			"cat-girl",
			"cat-boys",
			"cat-girls",
			new FeralAttributes(
					"cat",
					"cats",
					"tomcat",
					"cat",
					"tomcats",
					"cats",
					LegConfiguration.QUADRUPEDAL,
					25,
					0,
					1,
					4,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NamePos] body is incredibly agile, and [npc.she] [npc.verb(possess)] lightning reflexes."
					+ " [npc.She] also [npc.has] an instinctive desire to display [npc.her] dominance over innocent harpies and rodent-morphs...",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.HARPY), 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.RAT_MORPH), 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.SQUIRREL_MORPH), 5f)),
			null,
			"Curious Kitties",
			"Curious Kitties",
			"CAT_MORPH_BASIC",
			"CAT_MORPH_ADVANCED",
			Race.CAT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_CAT_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"An anthropomorphic cat, known as a 'cat-morph' when bipedal, and a 'cat-taur' when the lower body is that of a typically-oversized feral cat.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String getName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && (body ==null || (!body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL))) {
				return "catte-morph";
			}
			return super.getName(body);
		}
		@Override
		public String getNamePlural(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && (body ==null || (!body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL))) {
				return "catte-morphs";
			}
			return super.getNamePlural(body);
		}
		@Override
		public String getSingularMaleName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && body !=null && !body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL) {
				return "catte-boi";
			}
			return super.getSingularMaleName(body);
		}
		@Override
		public String getSingularFemaleName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && body !=null && !body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL) {
				return "catte-girl";
			}
			return super.getSingularFemaleName(body);
		}
		@Override
		public void applySpeciesChanges(Body body) {
		}

		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.CAT_MORPH) {
				return 100;
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies CAT_MORPH_LYNX = new AbstractSubspecies(false,
			12000,
			"innoxia_race_cat_felines_fancy",
			"innoxia_race_cat_kittys_reward",
			"statusEffects/race/raceCatMorph",
			"statusEffects/race/raceBackground",
			"lynx-morph",
			"lynx-morphs",
			"lynx-boy",
			"lynx-girl",
			"lynx-boys",
			"lynx-girls",
			new FeralAttributes(
					"lynx",
					"lynxes",
					LegConfiguration.QUADRUPEDAL,
					60,
					0,
					1,
					4,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NamePos] body is incredibly agile, and [npc.she] [npc.verb(possess)] lightning reflexes."
					+ " [npc.She] also [npc.has] an instinctive desire to display [npc.her] dominance over innocent harpies and rodent-morphs...",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.HARPY), 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.RAT_MORPH), 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.SQUIRREL_MORPH), 5f)),
			null,
			"Curious Kitties",
			"Curious Kitties",
			"CAT_MORPH_BASIC",
			"CAT_MORPH_ADVANCED",
			Race.CAT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_CAT_MORPH_LYNX,
			SubspeciesPreference.TWO_AVERAGE,
			"An anthropomorphic lynx, known as a 'lynx-morph' when bipedal, and a 'lynxtaur' when the lower body is that of a typically-oversized feral lynx."
				+ " To be identified as a Lynx-morph, a character must be a cat-morph that has fluffy fur, tufted ears, a short tail, and side-fluff hair type.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public void applySpeciesChanges(Body body) {
			Colour primaryColor = PresetColour.COVERING_BROWN;
			double rand = Math.random();
			if(rand<0.3f) {
				primaryColor = PresetColour.COVERING_TAN;
			} else if(rand<0.6f) {
				primaryColor = PresetColour.COVERING_BROWN_DARK;
			}
			body.getCoverings().put(BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.SPOTTED, CoveringModifier.FLUFFY, primaryColor, false, PresetColour.COVERING_BLACK, false));
			body.getCoverings().put(BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, PresetColour.COVERING_BLACK, false));
			body.updateCoverings(true, true, true, true);
			if(body.getEar().getType().getRace()==Race.CAT_MORPH) {
				body.getEar().setType(null, EarType.CAT_MORPH_TUFTED);
			}
			if(body.getTail().getType().getRace()==Race.CAT_MORPH) {
				body.getTail().setType(null, TailType.CAT_MORPH_SHORT);
			}
			if(body.getHair().getType().getRace()==Race.CAT_MORPH) {
				body.getHair().setType(null, HairType.CAT_MORPH_SIDEFLUFF);
			}
		}

		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.CAT_MORPH) {
				AbstractFaceType faceType = body.getFace().getType();
				AbstractBodyCoveringType felineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FELINE_FUR;
				
				if((faceType == FaceType.CAT_MORPH || faceType == FaceType.HUMAN)
						&& body.getHair().getType() == HairType.CAT_MORPH_SIDEFLUFF
						&& body.getEar().getType()==EarType.CAT_MORPH_TUFTED
						&& body.getCoverings().get(felineFur).getModifier() == CoveringModifier.FLUFFY
						&& body.getTail().getType()==TailType.CAT_MORPH_SHORT) {
					return 150;
				}
			}
			return 0;
		}
	};

	public static AbstractSubspecies CAT_MORPH_CHEETAH = new AbstractSubspecies(false,
			16000,
			"innoxia_race_cat_felines_fancy",
			"innoxia_race_cat_kittys_reward",
			"statusEffects/race/raceCatMorph",
			"statusEffects/race/raceBackgroundCheetah",
			"cheetah-morph",
			"cheetah-morphs",
			"cheetah-boy",
			"cheetah-girl",
			"cheetah-boys",
			"cheetah-girls",
			new FeralAttributes(
					"cheetah",
					"cheetahs",
					"cheetah",
					"cheetah",
					"cheetahs",
					"cheetahs",
					LegConfiguration.QUADRUPEDAL,
					80,
					0,
					1,
					4,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NameIsFull] extremely fast, and in short bursts, is capable of running at speeds far greater than any other race.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 25f)),
			Util.newArrayListOfValues("[style.boldExcellent(100%)] chance of escape vs non-cheetah-morphs"),
			"Curious Kitties",
			"Curious Kitties",
			"CAT_MORPH_BASIC",
			"CAT_MORPH_ADVANCED",
			Race.CAT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_CAT_MORPH_CHEETAH,
			SubspeciesPreference.TWO_AVERAGE,
				"An anthropomorphic cheetah, known as a 'cheetah-morph' when bipedal, and a 'cheetahtaur' when the lower body is that of a typically-oversized feral cheetah."
					+ " To be identified as a cheetah-morph, a character must be a cat-morph that has short, spotted fur and not identified as other feline morphs.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public void applySpeciesChanges(Body body) {
			Colour primaryColor = PresetColour.COVERING_ORANGE;
			double rand = Math.random();
			if(rand<0.35f) {
				primaryColor = PresetColour.COVERING_TAN;
			}
			Colour secondaryColor = PresetColour.COVERING_BLACK;
			body.getCoverings().put(BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.SPOTTED, CoveringModifier.SHORT, primaryColor, false, secondaryColor, false));
			body.getCoverings().put(BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
			body.updateCoverings(true, true, true, true);
			if(body.getTail().getType().getRace()==Race.CAT_MORPH) {
				body.getTail().setType(null, TailType.CAT_MORPH);
			}
			
			// Body size adjustment
			if(body.getBreast().getRawSizeValue()>CupSize.B.getMeasurement()) {
				rand = Math.random();
				if(rand<0.35f) {
					body.getBreast().setSize(null, CupSize.B.getMeasurement());
				} else if(rand<0.70f) {
					body.getBreast().setSize(null, CupSize.A.getMeasurement());
				} else {
					body.getBreast().setSize(null, CupSize.AA.getMeasurement());
				}
			}
			
			body.setBodySize(BodySize.ZERO_SKINNY.getMedianValue());
			body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		}

		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.CAT_MORPH) {
				AbstractFaceType faceType = body.getFace().getType();
				AbstractBodyCoveringType felineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FELINE_FUR;
				
				if((faceType == FaceType.CAT_MORPH || faceType == FaceType.HUMAN)
						&& body.getCoverings().get(felineFur).getPattern() == CoveringPattern.SPOTTED
						&& body.getCoverings().get(felineFur).getModifier() == CoveringModifier.SHORT
						&& body.getTail().getType()==TailType.CAT_MORPH) {
					return 150;
				}
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies CAT_MORPH_CARACAL = new AbstractSubspecies(false,
			12000,
			"innoxia_race_cat_felines_fancy",
			"innoxia_race_cat_kittys_reward",
			"statusEffects/race/raceCatMorph",
			"statusEffects/race/raceBackground",
			"caracal-morph",
			"caracal-morphs",
			"caracal-boy",
			"caracal-girl",
			"caracal-boys",
			"caracal-girls",
			new FeralAttributes(
					"caracal",
					"caracals",
					"caracal",
					"caracal",
					"caracals",
					"caracals",
					LegConfiguration.QUADRUPEDAL,
					50,
					0,
					1,
					4,
					1, false),
			Nocturnality.NOCTURNAL,
			"[npc.NamePos] body is incredibly agile, and [npc.she] [npc.verb(possess)] lightning reflexes."
					+ " [npc.She] also [npc.has] an instinctive desire to display [npc.her] dominance over innocent harpies and rodent-morphs...",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.HARPY), 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.RAT_MORPH), 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.SQUIRREL_MORPH), 5f)),
			null,
			"Curious Kitties",
			"Curious Kitties",
			"CAT_MORPH_BASIC",
			"CAT_MORPH_ADVANCED",
			Race.CAT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_CAT_MORPH_CARACAL,
			SubspeciesPreference.TWO_AVERAGE,
				"An anthropomorphic caracal, known as a 'caracal-morph' when bipedal, and a 'caracaltaur' when the lower body is that of a typically-oversized feral caracal."
					+ " To be identified as a caracal-morph, a character must be a cat-morph with tufted ears.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public void applySpeciesChanges(Body body) {
			if(body.getEar().getType().getRace()==Race.CAT_MORPH) {
				body.getEar().setType(null, EarType.CAT_MORPH_TUFTED);
			}
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.CAT_MORPH) {
				if(body.getEar().getType()==EarType.CAT_MORPH_TUFTED) {
					return 140;
				}
			}
			return 0;
		}
	};

//	public static AbstractSubspecies CAT_MORPH_LEOPARD_SNOW = new AbstractSubspecies(false,
//			18000,
//			"innoxia_race_cat_felines_fancy",
//			"innoxia_race_cat_kittys_reward",
//			"statusEffects/race/raceCatMorph",
//			"statusEffects/race/raceBackgroundSnowLeopard",
//			"snow leopard-morph",
//			"snow leopard-morphs",
//			"snow leopard-boy",
//			"snow leopard-girl",
//			"snow leopard-boys",
//			"snow leopard-girls",
//			new FeralAttributes(
//					"snow leopard",
//					"snow leopards",
//					"snow leopard",
//					"snow leopardess",
//					"snow leopards",
//					"snow leopardesses",
//					LegConfiguration.QUADRUPEDAL,
//					60,
//					0,
//					1,
//					4,
//					1, false),
//			"[npc.NamePos] body is very strong and agile, and [npc.sheIsFull] capable of great feats of strength and stealth alike."
//					+ " [npc.She] also [npc.has] a very high resistance to both natural and arcane cold.",
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 20f),
//					new Value<>(Attribute.MAJOR_ARCANE, 0f),
//					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
//					new Value<>(Attribute.CRITICAL_DAMAGE, 15f),
//					new Value<>(Attribute.DAMAGE_UNARMED, 5f),
//					new Value<>(Attribute.DAMAGE_PHYSICAL, 5f),
//					new Value<>(Attribute.RESISTANCE_ICE, 5f)),
//			null,
//			"Curious Kitties",
//			"Curious Kitties",
//			"CAT_MORPH_BASIC",
//			"CAT_MORPH_ADVANCED",
//			Race.CAT_MORPH,
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 10),
//					new Value<>(PerkCategory.LUST, 5),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 10),
//					new Value<>(PerkCategory.LUST, 2),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			PresetColour.RACE_CAT_MORPH_LEOPARD_SNOW,
//			SubspeciesPreference.TWO_AVERAGE,
//			"An anthropomorphic snow leopard, known as a 'snow leopard-morph' when bipedal, and a 'snow leopardtaur' when the lower body is that of a typically-oversized feral snow leopard."
//				+ " To be identified as a snow leopard-morph, a character must be a cat-morph that has fluffy spotted fur, normal tail and panther face.",
//			Util.newHashMapOfValues(
//					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.ZERO_EXTREMELY_RARE),
//					new Value<>(WorldRegion.MOUNTAINS, SubspeciesSpawnRarity.ZERO_EXTREMELY_RARE),
//					new Value<>(WorldRegion.SNOW, SubspeciesSpawnRarity.ZERO_EXTREMELY_RARE)),
//			Util.newHashMapOfValues(
//				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.FOUR_COMMON)),
//			null) {
//		@Override
//		public void applySpeciesChanges(Body body) {
//			Colour primaryColor = PresetColour.COVERING_WHITE;
//			Colour secondaryColor = PresetColour.COVERING_BLACK;
//			double rand = Math.random();
//			if(rand<0.3f) {
//				primaryColor = PresetColour.COVERING_WHITE;
//			} else if(rand<0.6f) {
//				primaryColor = PresetColour.COVERING_GREY;
//			} else if(rand<0.65f) {
//				primaryColor = PresetColour.COVERING_BLACK;
//			}
//			body.getCoverings().put(BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.SPOTTED, CoveringModifier.FLUFFY, primaryColor, false, secondaryColor, false));
//			body.getCoverings().put(BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
//			body.updateCoverings(true, true, true, true);
//			if(body.getFace().getType()==FaceType.CAT_MORPH) {
//				body.getFace().setType(null, FaceType.CAT_MORPH_PANTHER);
//			}
//			if(body.getTail().getType().getRace()==Race.CAT_MORPH) {
//				body.getTail().setType(null, TailType.CAT_MORPH);
//				body.getTail().setTailGirth(null, PenetrationGirth.FOUR_THICK.getValue());
//			}
//			
//			body.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
//			body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
//		}
//
//		@Override
//		public int getSubspeciesWeighting(Body body, AbstractRace race) {
//			if(race==Race.CAT_MORPH) {
//				AbstractFaceType faceType = body.getFace().getType();
//				AbstractBodyCoveringType felineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FELINE_FUR;
//				
//				if((faceType == FaceType.CAT_MORPH_PANTHER || faceType == FaceType.HUMAN)
//					&& body.getCoverings().get(felineFur).getPattern() == CoveringPattern.SPOTTED
//					&& body.getCoverings().get(felineFur).getModifier() == CoveringModifier.FLUFFY
//					&& body.getTail().getType()==TailType.CAT_MORPH) {
//					return 150;
//				}
//			}
//			return 0;
//		}
//	};
//	
//	public static AbstractSubspecies CAT_MORPH_LEOPARD = new AbstractSubspecies(false,
//			16000,
//			"innoxia_race_cat_felines_fancy",
//			"innoxia_race_cat_kittys_reward",
//			"statusEffects/race/raceCatMorph",
//			"statusEffects/race/raceBackgroundLeopard",
//			"leopard-morph",
//			"leopard-morphs",
//			"leopard-boy",
//			"leopard-girl",
//			"leopard-boys",
//			"leopard-girls",
//			new FeralAttributes(
//					"leopard",
//					"leopards",
//					"leopard",
//					"leopardess",
//					"leopard",
//					"leopardesses",
//					LegConfiguration.QUADRUPEDAL,
//					70,
//					0,
//					1,
//					4,
//					1, false),
//			"[npc.NamePos] body is very strong and agile, and [npc.sheIsFull] capable of great feats of strength and stealth alike. [npc.She] also [npc.has] a high resistance to both natural and arcane heat.",
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 20f),
//					new Value<>(Attribute.MAJOR_ARCANE, 0f),
//					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
//					new Value<>(Attribute.CRITICAL_DAMAGE, 15f),
//					new Value<>(Attribute.DAMAGE_UNARMED, 5f),
//					new Value<>(Attribute.DAMAGE_PHYSICAL, 5f),
//					new Value<>(Attribute.RESISTANCE_FIRE, 5f)),
//			null,
//			"Curious Kitties",
//			"Curious Kitties",
//			"CAT_MORPH_BASIC",
//			"CAT_MORPH_ADVANCED",
//			Race.CAT_MORPH,
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 10),
//					new Value<>(PerkCategory.LUST, 5),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 10),
//					new Value<>(PerkCategory.LUST, 2),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			PresetColour.RACE_CAT_MORPH_LEOPARD,
//			SubspeciesPreference.TWO_AVERAGE,
//			"An anthropomorphic leopard, known as a 'leopard-morph' when bipedal, and a 'leopardtaur' when the lower body is that of a typically-oversized feral leopard."
//			+ " To be identified as a leopard-morph, a character must be a cat-morph that has short spotted fur, normal tail and panther face.",
//			Util.newHashMapOfValues(
//					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.ONE_VERY_RARE),
//					new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.TWO_RARE),
//					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TWO_RARE),
//					new Value<>(WorldRegion.JUNGLE, SubspeciesSpawnRarity.TWO_RARE),
//					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.TWO_RARE)),
//			Util.newHashMapOfValues(
//				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.FOUR_COMMON)),
//			null) {
//		@Override
//		public void applySpeciesChanges(Body body) {
//			Colour primaryColor = PresetColour.COVERING_SANDY;
//			Colour secondaryColor = PresetColour.COVERING_BLACK;
//			double rand = Math.random();
//			if(rand<0.05f) {
//				primaryColor = PresetColour.COVERING_BLACK;
//			}
//			body.getCoverings().put(BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.SPOTTED, CoveringModifier.SHORT, primaryColor, false, secondaryColor, false));
//			body.getCoverings().put(BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
//			body.updateCoverings(true, true, true, true);
//			if(body.getFace().getType()==FaceType.CAT_MORPH) {
//				body.getFace().setType(null, FaceType.CAT_MORPH_PANTHER);
//			}
//			if(body.getTail().getType().getRace()==Race.CAT_MORPH) {
//				body.getTail().setType(null, TailType.CAT_MORPH);
//			}
//			body.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
//			body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
//		}
//
//		@Override
//		public int getSubspeciesWeighting(Body body, AbstractRace race) {
//			if(race==Race.CAT_MORPH) {
//				AbstractFaceType faceType = body.getFace().getType();
//				AbstractBodyCoveringType felineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FELINE_FUR;
//				
//				if((faceType == FaceType.CAT_MORPH_PANTHER || faceType == FaceType.HUMAN)
//					&& body.getCoverings().get(felineFur).getPattern() == CoveringPattern.SPOTTED
//					&& body.getCoverings().get(felineFur).getModifier() == CoveringModifier.SHORT
//					&& body.getTail().getType()==TailType.CAT_MORPH) {
//					return 150;
//				}
//			}
//			return 0;
//		}
//	};
//	
//	public static AbstractSubspecies CAT_MORPH_LION = new AbstractSubspecies(false,
//			24000,
//			"innoxia_race_cat_felines_fancy",
//			"innoxia_race_cat_kittys_reward",
//			"statusEffects/race/raceCatMorph",
//			"statusEffects/race/raceBackground",
//			"lion-morph",
//			"lion-morphs",
//			"lion-boy",
//			"lion-girl",
//			"lion-boys",
//			"lion-girls",
//			new FeralAttributes(
//					"lion",
//					"lions",
//					"lion",
//					"lioness",
//					"lions",
//					"lionesses",
//					LegConfiguration.QUADRUPEDAL,
//					100,
//					0,
//					1,
//					4,
//					1,
//					true),
//			"[npc.NamePos] body is extremely strong, and [npc.sheIsFull] capable of great feats of strength. [npc.She] also [npc.has] a high resistance to both natural and arcane heat.",
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 30f),
//					new Value<>(Attribute.MAJOR_ARCANE, 0f),
//					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
//					new Value<>(Attribute.DAMAGE_UNARMED, 15f),
//					new Value<>(Attribute.DAMAGE_PHYSICAL, 15f),
//					new Value<>(Attribute.RESISTANCE_FIRE, 5f)),
//			null,
//			"Curious Kitties",
//			"Curious Kitties",
//			"CAT_MORPH_BASIC",
//			"CAT_MORPH_ADVANCED",
//			Race.CAT_MORPH,
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 20),
//					new Value<>(PerkCategory.LUST, 5),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 20),
//					new Value<>(PerkCategory.LUST, 2),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			PresetColour.RACE_CAT_MORPH_LION,
//			SubspeciesPreference.TWO_AVERAGE,
//			"An anthropomorphic lion, known as a 'lion-morph' when bipedal, and a 'liontaur' when the lower body is that of a feral lion."
//				+ " To be identified as a lion-morph, a character must be a cat-morph that has short fur, tufted tail and panther face.",
//			Util.newHashMapOfValues(
//					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.ONE_VERY_RARE),
//					new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.TWO_RARE),
//					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TWO_RARE)),
//			Util.newHashMapOfValues(
//				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.FOUR_COMMON)),
//			null) {
//		@Override
//		public void applySpeciesChanges(Body body) {
//			Colour primaryColor = PresetColour.COVERING_TAN;
//			Colour secondaryColor = PresetColour.COVERING_BLACK;
//			double rand = Math.random();
//			if(rand<0.05f) {
//				primaryColor = PresetColour.COVERING_BLACK;
//			}
//			else if(rand<0.1f) {
//				primaryColor = PresetColour.COVERING_WHITE;
//			}
//			body.getCoverings().put(BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, CoveringModifier.SHORT, primaryColor, false, secondaryColor, false));
//			body.getCoverings().put(BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
//			body.updateCoverings(true, true, true, true);
//			if(body.getFace().getType()==FaceType.CAT_MORPH) {
//				body.getFace().setType(null, FaceType.CAT_MORPH_PANTHER);
//			}
//			if(body.getTail().getType().getRace()==Race.CAT_MORPH) {
//				body.getTail().setType(null, TailType.CAT_MORPH_TUFTED);
//			}
//			body.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
//			body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
//		}
//
//		@Override
//		public int getSubspeciesWeighting(Body body, AbstractRace race) {
//			if(race==Race.CAT_MORPH) {
//				AbstractFaceType faceType = body.getFace().getType();
//				AbstractBodyCoveringType felineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FELINE_FUR;
//				
//				if((faceType == FaceType.CAT_MORPH_PANTHER || faceType == FaceType.HUMAN)
//					&& body.getCoverings().get(felineFur).getModifier() == CoveringModifier.SHORT
//					&& body.getTail().getType()==TailType.CAT_MORPH_TUFTED) {
//					return 150;
//				}
//			}
//			return 0;
//		}
//	};
//	
//	public static AbstractSubspecies CAT_MORPH_TIGER = new AbstractSubspecies(false,
//			30000,
//			"innoxia_race_cat_felines_fancy",
//			"innoxia_race_cat_kittys_reward",
//			"statusEffects/race/raceCatMorph",
//			"statusEffects/race/raceBackgroundTiger",
//			"tiger-morph",
//			"tiger-morphs",
//			"tiger-boy",
//			"tiger-girl",
//			"tiger-boys",
//			"tiger-girls",
//			new FeralAttributes(
//					"tiger",
//					"tigers",
//					"tiger",
//					"tigress",
//					"tigers",
//					"tigresses",
//					LegConfiguration.QUADRUPEDAL,
//					100,
//					0,
//					1,
//					4,
//					1, false),
//			"[npc.NamePos] body is extremely strong, and [npc.sheIsFull] capable of great feats of strength.",
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 30f),
//					new Value<>(Attribute.MAJOR_ARCANE, 0f),
//					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
//					new Value<>(Attribute.DAMAGE_UNARMED, 25f),
//					new Value<>(Attribute.DAMAGE_PHYSICAL, 25f)),
//			null,
//			"Curious Kitties",
//			"Curious Kitties",
//			"CAT_MORPH_BASIC",
//			"CAT_MORPH_ADVANCED",
//			Race.CAT_MORPH,
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 20),
//					new Value<>(PerkCategory.LUST, 5),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 20),
//					new Value<>(PerkCategory.LUST, 2),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			PresetColour.RACE_CAT_MORPH_TIGER,
//			SubspeciesPreference.TWO_AVERAGE,
//			"An anthropomorphic tiger, known as a 'tiger-morph' when bipedal, and a 'tigertaur' when the lower body is that of a feral tiger."
//				+ " To be identified as a tiger-morph, a character must be a cat-morph that has striped fur, normal tail and panther face.",
//			Util.newHashMapOfValues(
//					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.ONE_VERY_RARE),
//					new Value<>(WorldRegion.JUNGLE, SubspeciesSpawnRarity.TWO_RARE),
//					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.TWO_RARE)),
//			Util.newHashMapOfValues(
//				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.FOUR_COMMON)),
//			null) {
//		@Override
//		public void applySpeciesChanges(Body body) {
//			Colour primaryColor = PresetColour.COVERING_ORANGE;
//			Colour secondaryColor = PresetColour.COVERING_BLACK;
//			double rand = Math.random();
//			if(rand<0.6f) {
//				primaryColor = Util.randomItemFrom(Util.newArrayListOfValues(PresetColour.COVERING_TAN, PresetColour.COVERING_AUBURN, PresetColour.COVERING_AMBER));
//			} else if(rand<0.12f) {
//				primaryColor = PresetColour.COVERING_WHITE;
//			} else if(rand<0.16f) {
//				primaryColor = PresetColour.COVERING_BLACK;
//			}
//			body.getCoverings().put(BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.STRIPED, CoveringModifier.SHORT, primaryColor, false, secondaryColor, false));
//			body.getCoverings().put(BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
//			body.updateCoverings(true, true, true, true);
//			if(body.getFace().getType()==FaceType.CAT_MORPH) {
//				body.getFace().setType(null, FaceType.CAT_MORPH_PANTHER);
//			}
//			if(body.getTail().getType().getRace()==Race.CAT_MORPH) {
//				body.getTail().setType(null, TailType.CAT_MORPH);
//			}
//			body.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
//			body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
//		}
//
//		@Override
//		public int getSubspeciesWeighting(Body body, AbstractRace race) {
//			if(race==Race.CAT_MORPH) {
//				AbstractFaceType faceType = body.getFace().getType();
//				AbstractBodyCoveringType felineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FELINE_FUR;
//				
//				if((faceType == FaceType.CAT_MORPH_PANTHER || faceType == FaceType.HUMAN)
//					&& body.getCoverings().get(felineFur).getPattern() == CoveringPattern.STRIPED
//					&& body.getTail().getType()==TailType.CAT_MORPH) {
//					return 150;
//					
//				}
//			}
//			return 0;
//		}
//	};
	
	// EQUINES:
	public static AbstractSubspecies HORSE_MORPH = new AbstractSubspecies(true,
			18000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"horse-morph",
			"horse-morphs",
			"horse-boy",
			"horse-girl",
			"horse-boys",
			"horse-girls",
			new FeralAttributes(
					"horse",
					"horses",
					"stallion",
					"mare",
					"stallions",
					"mares",
					LegConfiguration.QUADRUPEDAL,
					175,
					0,
					1,
					1,
					1,
					true),
			Nocturnality.DIURNAL,
			"While [npc.namePos] body possesses remarkable strength and speed, [npc.sheIs] not the sharpest tool in the shed, and [npc.verb(struggle)] more than most when it comes to harnessing the arcane.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, -10f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 25f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 25f)),
			null,
			"Equine Encyclopedia",
			"Equine Encyclopedias",
			"HORSE_MORPH_BASIC",
			"HORSE_MORPH_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_HORSE_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"An anthropomorphic, bipedal horse.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public void applySpeciesChanges(Body body) {
			body.getHorn().setType(null, HornType.NONE);
			body.getWing().setType(null, WingType.NONE);
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				body.getHair().setStyle(null, HairStyle.NONE); // Sets hair style to mane
			}
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"nightmare",
					"nightmares",
					"nightmare",
					"nightmare",
					"nightmares",
					"nightmares"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "nightmare", false, false),
					applyNonBipedNameChange(body, "nightmare", false, true),
					applyNonBipedNameChange(body, "nightmare", false, false),
					applyNonBipedNameChange(body, "nightmare", true, false),
					applyNonBipedNameChange(body, "nightmare", false, true),
					applyNonBipedNameChange(body, "nightmare", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH) {
				return 100;
			}
			return 0;
		}
	};
	public static AbstractSubspecies HORSE_MORPH_UNICORN = new AbstractSubspecies(false,
			30000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"unicorn-morph",
			"unicorn-morphs",
			"unicorn-boy",
			"unicorn-girl",
			"unicorn-boys",
			"unicorn-girls",
			new FeralAttributes(
					"unicorn",
					"unicorns",
					"unicorn-stallion",
					"unicorn-mare",
					"unicorn-stallions",
					"unicorn-mares",
					LegConfiguration.QUADRUPEDAL,
					175,
					0,
					1,
					1,
					1,
					true),
			Nocturnality.DIURNAL,
			"Although physically weaker than a regular horse-morph, [npc.nameHasFull] a special bond with the arcane, and [npc.is] able to cast many spells before exhausting [npc.her] aura.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 20f),
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 50f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 5f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f)),
			null,
			"Equine Encyclopedia",
			"Equine Encyclopedias",
			"HORSE_MORPH_BASIC",
			"HORSE_MORPH_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 3),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 3)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 4),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 3)),
			PresetColour.RACE_UNICORN,
			SubspeciesPreference.ONE_LOW,
			"An anthropomorphic, bipedal horse, who has a single magical horn growing from their forehead.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.DOMINION, SubspeciesSpawnRarity.TWO),
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TWO)), null, null) {
		@Override
		public void applySpeciesChanges(Body body) {
			body.getHorn().setType(null, HornType.HORSE_STRAIGHT);
			body.getHorn().setHornRows(null, 1);
			body.getHorn().setHornsPerRow(null, 1);
			body.getHorn().setHornLength(null, HornLength.TWO_LONG.getMedianValue());
			body.getWing().setType(null, WingType.NONE);
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				body.getHair().setStyle(null, HairStyle.NONE); // Sets hair style to mane
			}
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"unicorn-nightmare",
					"unicorn-nightmares",
					"unicorn-nightmare",
					"unicorn-nightmare",
					"unicorn-nightmares",
					"unicorn-nightmares"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "unicorn-nightmare", false, false),
					applyNonBipedNameChange(body, "unicorn-nightmare", false, true),
					applyNonBipedNameChange(body, "unicorn-nightmare", false, false),
					applyNonBipedNameChange(body, "unicorn-nightmare", true, false),
					applyNonBipedNameChange(body, "unicorn-nightmare", false, true),
					applyNonBipedNameChange(body, "unicorn-nightmare", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH) {
				if(!body.getHorn().getType().equals(HornType.NONE) && body.getHorn().getHornRows()==1 && body.getHorn().getHornsPerRow()==1) {
					return 150;
				}
			}
			return 0;
		}
	};
	public static AbstractSubspecies HORSE_MORPH_PEGASUS = new AbstractSubspecies(false,
			24000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"pegasus-morph",
			"pegasus-morphs",
			"pegasus-boy",
			"pegasus-girl",
			"pegasus-boys",
			"pegasus-girls",
			new FeralAttributes(
					"pegasus",
					"pegasuss",
					"pegasus-stallion",
					"pegasus-mare",
					"pegasus-stallions",
					"pegasus-mares",
					LegConfiguration.QUADRUPEDAL,
					175,
					0,
					1,
					1,
					1,
					true),
			Nocturnality.DIURNAL,
			"Although physically weaker than a regular horse-morph, [npc.nameIsFull] a lot more agile, allowing [npc.herHim] to strike at [npc.her] enemies' weak points.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 30f)),
			null,
			"Equine Encyclopedia",
			"Equine Encyclopedias",
			"HORSE_MORPH_BASIC",
			"HORSE_MORPH_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_PEGASUS,
			SubspeciesPreference.ONE_LOW,
			"An anthropomorphic, bipedal horse, who has a pair of feathered wings growing from their back.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TWO)), null, null) {
		@Override
		public void applySpeciesChanges(Body body) {
			body.getHorn().setType(null, HornType.NONE);
			body.setWing(new Wing(WingType.FEATHERED, WingSize.THREE_LARGE.getValue()));
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				body.getHair().setStyle(null, HairStyle.NONE); // Sets hair style to mane
			}
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"pegasus-nightmare",
					"pegasus-nightmares",
					"pegasus-nightmare",
					"pegasus-nightmare",
					"pegasus-nightmares",
					"pegasus-nightmares"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "pegasus-nightmare", false, false),
					applyNonBipedNameChange(body, "pegasus-nightmare", false, true),
					applyNonBipedNameChange(body, "pegasus-nightmare", false, false),
					applyNonBipedNameChange(body, "pegasus-nightmare", true, false),
					applyNonBipedNameChange(body, "pegasus-nightmare", false, true),
					applyNonBipedNameChange(body, "pegasus-nightmare", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH) {
				if(body.getWing().getType()==WingType.FEATHERED) {
					return 150;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	public static AbstractSubspecies HORSE_MORPH_ALICORN = new AbstractSubspecies(false,
			60000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"alicorn-morph",
			"alicorn-morphs",
			"alicorn-boy",
			"alicorn-girl",
			"alicorn-boys",
			"alicorn-girls",
			new FeralAttributes(
					"alicorn",
					"alicorns",
					"alicorn-stallion",
					"alicorn-mare",
					"alicorn-stallions",
					"alicorn-mares",
					LegConfiguration.QUADRUPEDAL,
					175,
					0,
					1,
					1,
					1,
					true),
			Nocturnality.DIURNAL,
			"Possessing both feathered wings and a unicorn horn, [npc.nameIsFull] classified as a powerful alicorn, and [npc.verb(find)] it almost effortlessly trivial to cast spells.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15f),
					new Value<>(Attribute.MAJOR_ARCANE, 15f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 75f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 50f)),
			null,
			"Equine Encyclopedia",
			"Equine Encyclopedias",
			"HORSE_MORPH_BASIC",
			"HORSE_MORPH_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 3)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 3)),
			PresetColour.RACE_ALICORN,
			SubspeciesPreference.ONE_LOW,
			"An anthropomorphic, bipedal horse, who has both a pair of feathered wings growing from their back, as well as a single magical horn growing from their forehead.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.ONE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.ONE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.ONE)), null, null) {
		@Override
		public void applySpeciesChanges(Body body) {
			body.getHorn().setType(null, HornType.HORSE_STRAIGHT);
			body.getHorn().setHornRows(null, 1);
			body.getHorn().setHornsPerRow(null, 1);
			body.getHorn().setHornLength(null, HornLength.TWO_LONG.getMedianValue());
			body.setWing(new Wing(WingType.FEATHERED, WingSize.THREE_LARGE.getValue()));
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				body.getHair().setStyle(null, HairStyle.NONE); // Sets hair style to mane
			}
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"alicorn-nightmare",
					"alicorn-nightmares",
					"alicorn-nightmare",
					"alicorn-nightmare",
					"alicorn-nightmares",
					"alicorn-nightmares"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "alicorn-nightmare", false, false),
					applyNonBipedNameChange(body, "alicorn-nightmare", false, true),
					applyNonBipedNameChange(body, "alicorn-nightmare", false, false),
					applyNonBipedNameChange(body, "alicorn-nightmare", true, false),
					applyNonBipedNameChange(body, "alicorn-nightmare", false, true),
					applyNonBipedNameChange(body, "alicorn-nightmare", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH) {
				if(body.getWing().getType()==WingType.FEATHERED
						&& !body.getHorn().getType().equals(HornType.NONE)
						&& body.getHorn().getHornRows()==1
						&& body.getHorn().getHornsPerRow()==1) {
					return 200;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	
	public static AbstractSubspecies CENTAUR = new AbstractSubspecies(false,
			25000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"centaur",
			"centaurs",
			"centaur",
			"centauress",
			"centaurs",
			"centauresses",
			null,
			Nocturnality.DIURNAL,
			"Thanks to having the lower body of a horse, [npc.nameIsFull] capable of running at great speed, and [npc.is] also capable of dealing significant physical damage.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 35f),
					new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, -10f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 25f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 50f)),
			null,
			"Centaurs & More",
			"Centaurs & More",
			"CENTAUR_BASIC",
			"CENTAUR_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_CENTAUR,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Anyone who has the feral, quadrupedal lower body of a horse is classified as a centaur.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TWO)), null, null) {
		@Override
		public boolean isNonBiped() {
			return true;
		}
		@Override
		public void applySpeciesChanges(Body body) {
			body.getHorn().setType(null, HornType.NONE);
			body.getWing().setType(null, WingType.NONE);
			body.getLeg().setType(null, LegType.HORSE_MORPH);
			LegType.HORSE_MORPH.applyLegConfigurationTransformation(body, LegConfiguration.QUADRUPEDAL, true);
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				body.getHair().setStyle(null, HairStyle.NONE); // Sets hair style to mane
			}
			body.getPenis().getTesticle().getCum().addFluidModifier(null, FluidModifier.MUSKY);
			body.getVagina().getGirlcum().addFluidModifier(null, FluidModifier.MUSKY);
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			return new String[] {
					"demonic-centaur",
					"demonic-centaurs",
					"demonic-centaur",
					"demonic-centauress",
					"demonic-centaurs",
					"demonic-centauresses"};
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {	
			if(race==Race.HORSE_MORPH && body.getLeg().getLegConfiguration()==LegConfiguration.QUADRUPEDAL) {
				return 1000;
			}
			return 0;
		}
	};
	public static AbstractSubspecies PEGATAUR = new AbstractSubspecies(false,
			35000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"pegataur",
			"pegataurs",
			"pegataur",
			"pegatauress",
			"pegataurs",
			"pegatauresses",
			null,
			Nocturnality.DIURNAL,
			"Although physically weaker than a regular centaur, [npc.nameIsFull] a lot more agile, allowing [npc.herHim] to avoid incoming damage.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 30f),
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f)),
			null,
			"Centaurs & More",
			"Centaurs & More",
			"CENTAUR_BASIC",
			"CENTAUR_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_PEGATAUR,
			SubspeciesPreference.ONE_LOW,
			"Anyone who has the feral, winged, quadrupedal lower body of a horse is classified as a pegataur.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.ONE)), null, null) {
		@Override
		public boolean isNonBiped() {
			return true;
		}
		@Override
		public void applySpeciesChanges(Body body) {
			body.getHorn().setType(null, HornType.NONE);
			body.getLeg().setType(null, LegType.HORSE_MORPH);
			LegType.HORSE_MORPH.applyLegConfigurationTransformation(body, LegConfiguration.QUADRUPEDAL, true);
			body.setWing(new Wing(WingType.FEATHERED, WingSize.FOUR_HUGE.getValue()));
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				body.getHair().setStyle(null, HairStyle.NONE); // Sets hair style to mane
			}
			body.getPenis().getTesticle().getCum().addFluidModifier(null, FluidModifier.MUSKY);
			body.getVagina().getGirlcum().addFluidModifier(null, FluidModifier.MUSKY);
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			return new String[] {
					"demonic-pegataur",
					"demonic-pegataurs",
					"demonic-pegataur",
					"demonic-pegatauress",
					"demonic-pegataurs",
					"demonic-pegatauresses"};
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH && body.getLeg().getLegConfiguration()==LegConfiguration.QUADRUPEDAL) {
				if(body.getWing().getType()==WingType.FEATHERED) {
					return 1150;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	public static AbstractSubspecies UNITAUR = new AbstractSubspecies(false,
			50000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"unitaur",
			"unitaurs",
			"unitaur",
			"unitauress",
			"unitaurs",
			"unitauresses",
			null,
			Nocturnality.DIURNAL,
			"Although physically weaker than a regular horse-morph, [npc.nameHasFull] a special bond with the arcane, and [npc.is] able to cast many spells before exhausting [npc.her] aura.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 50f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 5f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f)),
			null,
			"Centaurs & More",
			"Centaurs & More",
			"CENTAUR_BASIC",
			"CENTAUR_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_UNICORN,
			SubspeciesPreference.ONE_LOW,
			"Anyone who has the feral, quadrupedal lower body of a horse, along with a single unicorn horn, is classified as a unitaur.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.ONE)), null, null) {
		@Override
		public boolean isNonBiped() {
			return true;
		}
		@Override
		public void applySpeciesChanges(Body body) {
			body.getHorn().setType(null, HornType.HORSE_STRAIGHT);
			body.getHorn().setHornRows(null, 1);
			body.getHorn().setHornsPerRow(null, 1);
			body.getHorn().setHornLength(null, HornLength.TWO_LONG.getMedianValue());
			body.getLeg().setType(null, LegType.HORSE_MORPH);
			LegType.HORSE_MORPH.applyLegConfigurationTransformation(body, LegConfiguration.QUADRUPEDAL, true);
			body.getWing().setType(null, WingType.NONE);
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				body.getHair().setStyle(null, HairStyle.NONE); // Sets hair style to mane
			}
			body.getPenis().getTesticle().getCum().addFluidModifier(null, FluidModifier.MUSKY);
			body.getVagina().getGirlcum().addFluidModifier(null, FluidModifier.MUSKY);
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			return new String[] {
					"demonic-unitaur",
					"demonic-unitaurs",
					"demonic-unitaur",
					"demonic-unitauress",
					"demonic-unitaurs",
					"demonic-unitauresses"};
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH && body.getLeg().getLegConfiguration()==LegConfiguration.QUADRUPEDAL) {
				if(!body.getHorn().getType().equals(HornType.NONE) && body.getHorn().getHornRows()==1 && body.getHorn().getHornsPerRow()==1) {
					return 1150;
				}
			}
			return 0;
		}
	};
	public static AbstractSubspecies ALITAUR = new AbstractSubspecies(false,
			100000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"alitaur",
			"alitaurs",
			"alitaur",
			"alitauress",
			"alitaurs",
			"alitauresses",
			null,
			Nocturnality.DIURNAL,
			"Possessing both feathered wings and a unicorn horn, [npc.nameIsFull] classified as a powerful alicorn, and [npc.verb(find)] it almost effortlessly trivial to cast spells.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.MAJOR_ARCANE, 15f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 75f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 50f)),
			null,
			"Centaurs & More",
			"Centaurs & More",
			"CENTAUR_BASIC",
			"CENTAUR_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 2)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 2)),
			PresetColour.RACE_ALICORN,
			SubspeciesPreference.ONE_LOW,
			"Anyone who has the feral, winged, quadrupedal lower body of a horse, along with a single unicorn horn, is classified as an alitaur.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.ONE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.ONE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.ONE)), null, null) {
		@Override
		public boolean isNonBiped() {
			return true;
		}
		@Override
		public void applySpeciesChanges(Body body) {
			body.getHorn().setType(null, HornType.HORSE_STRAIGHT);
			body.getHorn().setHornRows(null, 1);
			body.getHorn().setHornsPerRow(null, 1);
			body.getHorn().setHornLength(null, HornLength.TWO_LONG.getMedianValue());
			body.getLeg().setType(null, LegType.HORSE_MORPH);
			LegType.HORSE_MORPH.applyLegConfigurationTransformation(body, LegConfiguration.QUADRUPEDAL, true);
			body.setWing(new Wing(WingType.FEATHERED, WingSize.FOUR_HUGE.getValue()));
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				body.getHair().setStyle(null, HairStyle.NONE); // Sets hair style to mane
			}
			body.getPenis().getTesticle().getCum().addFluidModifier(null, FluidModifier.MUSKY);
			body.getVagina().getGirlcum().addFluidModifier(null, FluidModifier.MUSKY);
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			return new String[] {
					"demonic-alitaur",
					"demonic-alitaurs",
					"demonic-alitaur",
					"demonic-alitauress",
					"demonic-alitaurs",
					"demonic-alitauresses"};
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH && body.getLeg().getLegConfiguration()==LegConfiguration.QUADRUPEDAL) {
				if(body.getWing().getType()==WingType.FEATHERED
						&& !body.getHorn().getType().equals(HornType.NONE)
						&& body.getHorn().getHornRows()==1
						&& body.getHorn().getHornsPerRow()==1) {
					return 1200;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	
	public static AbstractSubspecies HORSE_MORPH_ZEBRA = new AbstractSubspecies(false,
			18000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorphZebra",
			"statusEffects/race/raceBackgroundZebra",
			"zebra-morph",
			"zebra-morphs",
			"zebra-boy",
			"zebra-girl",
			"zebra-boys",
			"zebra-girls",
			new FeralAttributes(
					"zebra",
					"zebras",
					"zebra-stallion",
					"zebra-mare",
					"zebra-stallions",
					"zebra-mares",
					LegConfiguration.QUADRUPEDAL,
					130,
					0,
					1,
					1,
					1,
					true),
			Nocturnality.DIURNAL,
			"While [npc.namePos] body possesses an impressive level of both strength and speed, [npc.sheIs] not the sharpest tool in the shed, and [npc.verb(struggle)] more than most when it comes to harnessing the arcane."
					+ " [npc.She] also [npc.has] a high resistance to both natural and arcane heat.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, -10f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 20f),
					new Value<>(Attribute.RESISTANCE_FIRE, 5f)),
			null,
			"Equine Encyclopedia",
			"Equine Encyclopedias",
			"HORSE_MORPH_BASIC",
			"HORSE_MORPH_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.BASE_BLACK,
			SubspeciesPreference.ONE_LOW,
			"An anthropomorphic horse with black-and-white striped fur, known as a 'zebra-morph' when bipedal, and a 'zebrataur' when the lower body is that of a feral zebra."
					+" To be identified as a zebra-morph, a character must be a horse-morph that has black-and-white striped hair, with a zebra-morph's tail.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.FIVE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public void applySpeciesChanges(Body body) {
			body.getHorn().setType(null, HornType.NONE);
			body.getWing().setType(null, WingType.NONE);
			body.getCoverings().put(BodyCoveringType.HORSE_HAIR, new Covering(BodyCoveringType.HORSE_HAIR, CoveringPattern.STRIPED, CoveringModifier.SHORT, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_WHITE, false));
			body.getCoverings().put(BodyCoveringType.HAIR_HORSE_HAIR, new Covering(BodyCoveringType.HAIR_HORSE_HAIR, CoveringPattern.NONE, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_WHITE, false));
			body.getCoverings().put(BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, PresetColour.SKIN_EBONY, false, PresetColour.SKIN_EBONY, false));
			body.updateCoverings(true, true, true, true);
			
			if(body.getTail().getType()==TailType.HORSE_MORPH) {
				body.getTail().setType(null, TailType.HORSE_MORPH_ZEBRA);
			}
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				body.getHair().setStyle(null, HairStyle.NONE); // Sets hair style to mane
			}
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"zebra-nightmare",
					"zebra-nightmares",
					"zebra-nightmare",
					"zebra-nightmare",
					"zebra-nightmares",
					"zebra-nightmares"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "zebra-nightmare", false, false),
					applyNonBipedNameChange(body, "zebra-nightmare", false, true),
					applyNonBipedNameChange(body, "zebra-nightmare", false, false),
					applyNonBipedNameChange(body, "zebra-nightmare", true, false),
					applyNonBipedNameChange(body, "zebra-nightmare", false, true),
					applyNonBipedNameChange(body, "zebra-nightmare", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH) {
				AbstractBodyCoveringType horseHair = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_HAIR):BodyCoveringType.HORSE_HAIR;
				Colour zebraPrimary = body.getCoverings().get(horseHair).getPrimaryColour();
				Colour zebraSecondary = body.getCoverings().get(horseHair).getSecondaryColour();
				if((((zebraPrimary==PresetColour.COVERING_BLACK || zebraPrimary==PresetColour.COVERING_JET_BLACK) && zebraSecondary==PresetColour.COVERING_WHITE)
						|| (zebraPrimary==PresetColour.COVERING_WHITE && (zebraSecondary==PresetColour.COVERING_BLACK || zebraSecondary==PresetColour.COVERING_JET_BLACK)))
					&& body.getTail().getType()==TailType.HORSE_MORPH_ZEBRA) {
//						return 125;
						return 1500;// Zebra-morphs should override centaur types
					}
			}
			return 0;
		}
	};

	public static AbstractSubspecies HORSE_MORPH_DONKEY = new AbstractSubspecies(false,
			12000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"donkey-morph",
			"donkey-morphs",
			"donkey-boy",
			"donkey-girl",
			"donkey-boys",
			"donkey-girls",
			new FeralAttributes(
					"donkey",
					"donkeys",
					"donkey-stallion",
					"donkey-mare",
					"donkey-stallions",
					"donkey-mares",
					LegConfiguration.QUADRUPEDAL,
					120,
					0,
					1,
					1,
					1,
					true),
			Nocturnality.DIURNAL,
			"While [npc.namePos] body possesses an impressive level of both strength and endurance, [npc.she] [npc.verb(struggle)] more than most when it comes to harnessing the arcane."
					+ " [npc.She] also [npc.has] an innate knowledge of how best to fight against canines.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, -10f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 20f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.DOG_MORPH), 25f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.WOLF_MORPH), 25f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.FOX_MORPH), 25f)),
			null,
			"Equine Encyclopedia",
			"Equine Encyclopedias",
			"HORSE_MORPH_BASIC",
			"HORSE_MORPH_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 15),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.CLOTHING_DESATURATED_BROWN,
			SubspeciesPreference.ONE_LOW,
			"An anthropomorphic donkey, known as a 'donkey-morph' when bipedal, and a 'donkeytaur' when the lower body is that of a feral donkey."
					+" To be identified as a donkey-morph, a character must be a horse-morph that has tall, upright ears.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.THREE)), null, null) {
		@Override
		public void applySpeciesChanges(Body body) {
			body.getHorn().setType(null, HornType.NONE);
			body.getWing().setType(null, WingType.NONE);
			if(Math.random()<0.75f) { // 75% of donkey morphs are the classic brown with white markings:
				body.getCoverings().put(BodyCoveringType.HORSE_HAIR, new Covering(BodyCoveringType.HORSE_HAIR, CoveringPattern.MARKED, CoveringModifier.SHORT, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_WHITE, false));
				body.getCoverings().put(BodyCoveringType.HAIR_HORSE_HAIR, new Covering(BodyCoveringType.HAIR_HORSE_HAIR, CoveringPattern.NONE, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_WHITE, false));
				body.getCoverings().put(BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, PresetColour.SKIN_DARK, false, PresetColour.SKIN_DARK, false));
			}
			body.updateCoverings(true, true, true, true);
			
			if(body.getEar().getType()==EarType.HORSE_MORPH) {
				body.getEar().setType(null, EarType.HORSE_MORPH_UPRIGHT);
			}
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				body.getHair().setStyle(null, HairStyle.NONE); // Sets hair style to mane
			}
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"donkey-nightmare",
					"donkey-nightmares",
					"donkey-nightmare",
					"donkey-nightmare",
					"donkey-nightmares",
					"donkey-nightmares"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "donkey-nightmare", false, false),
					applyNonBipedNameChange(body, "donkey-nightmare", false, true),
					applyNonBipedNameChange(body, "donkey-nightmare", false, false),
					applyNonBipedNameChange(body, "donkey-nightmare", true, false),
					applyNonBipedNameChange(body, "donkey-nightmare", false, true),
					applyNonBipedNameChange(body, "donkey-nightmare", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH) {
				if(body.getEar().getType()==EarType.HORSE_MORPH_UPRIGHT) {
//					return 110; // Less than zebra
					return 1400;// Donkey-morphs should override centaur types
				}
			}
			return 0;
		}
		@Override
		public Map<PersonalityTrait, Float> getPersonalityTraitChances() {
			Map<PersonalityTrait, Float> map = super.getPersonalityTraitChances();
			map.put(PersonalityTrait.BRAVE, 0.5f);// Donkeys do not act like typical prey animals
			return map;
		}
	};
	
	public static AbstractSubspecies REINDEER_MORPH = new AbstractSubspecies(true,
			18000,
			"innoxia_race_reindeer_rudolphs_egg_nog",
			"innoxia_race_reindeer_sugar_cookie",
			"statusEffects/race/raceReindeerMorph",
			"statusEffects/race/raceBackground",
			"reindeer-morph",
			"reindeer-morphs",
			"reindeer-boy",
			"reindeer-girl",
			"reindeer-boys",
			"reindeer-girls",
			new FeralAttributes(
					"reindeer",
					"reindeers",
					"reindeer-bull",
					"reindeer-cow",
					"reindeer-bulls",
					"reindeer-cows",
					LegConfiguration.QUADRUPEDAL,
					130,
					0,
					1,
					1,
					4, false),
			Nocturnality.DIURNAL,
			"[npc.NamePos] body is very well suited to resisting both natural and arcane cold, and is also particularly strong and hardy.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 2f),
					new Value<>(Attribute.RESISTANCE_ICE, 5f)),
			null,
			"Reindeer Migrations",
			"Reindeer Migrations",
			"REINDEER_MORPH_BASIC",
			"REINDEER_MORPH_ADVANCED",
			Race.REINDEER_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_REINDEER_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"An anthropomorphic reindeer, known as a 'reindeer-morph' when bipedal, and a 'reindeertaur' when the lower body is that of a feral reindeer.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.ONE),
					new Value<>(WorldRegion.SNOW, SubspeciesSpawnRarity.FIVE)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.THREE)), null, null) {
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.REINDEER_MORPH) {
				return 100;
			}
			return 0;
		}
	};
	
	// REPTILE:
	public static AbstractSubspecies ALLIGATOR_MORPH = new AbstractSubspecies(true,
			10000,
			"innoxia_race_alligator_swamp_water",
			"innoxia_race_alligator_gators_gumbo",
			"statusEffects/race/raceGatorMorph",
			"statusEffects/race/raceBackground",
			"alligator-morph",
			"alligator-morphs",
			"alligator-boy",
			"alligator-girl",
			"alligator-boys",
			"alligator-girls",
			new FeralAttributes(
					"alligator",
					"alligators",
					LegConfiguration.QUADRUPEDAL,
					false,
					180,
					0,
					1,
					0,
					1, false),
			Nocturnality.NOCTURNAL,
			"[npc.NamePos] body is incredibly tough, and [npc.she] [npc.verb(possess)] lightning reflexes, as well as the strength required to make the most of any sudden attacks.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 30f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f)),
			null,
			"Rasselin' Gators",
			"Rasselin' Gators",
			"ALLIGATOR_MORPH_BASIC",
			"ALLIGATOR_MORPH_ADVANCED",
			Race.ALLIGATOR_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_ALLIGATOR_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"An anthropomorphic alligator, known as an 'alligator-morph' when bipedal, and an 'alligatortaur' when the lower body is that of a typically-oversized feral alligator.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.RIVER, SubspeciesSpawnRarity.TWO)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TWO)), null, null) {
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.ALLIGATOR_MORPH) {
				return 100;
			}
			return 0;
		}
	};
	
	// SLIMES:
	public static AbstractSubspecies SLIME = new AbstractSubspecies(true,
			10000,
			"innoxia_race_slime_slime_quencher",
			"innoxia_race_slime_biojuice_canister",
			"statusEffects/race/raceSlime",
			"statusEffects/race/raceBackgroundSlime",
			"slime",
			"slimes",
			"slime-boy",
			"slime-girl",
			"slime-boys",
			"slime-girls",
			null,
			Nocturnality.DIURNAL,
			"Due to [npc.her] soft, slimy body, [npc.nameIsFull] almost completely immune to physical damage, but [npc.sheIs] also unable to inflict any significant damage while unarmed."
					+ " [npc.She] can also morph [npc.her] body at will, allowing [npc.herHim] to take on any form that [npc.she] [npc.verb(desire)].",
			"Due to [npc.her] soft, slimy body, [npc.nameIsFull] almost completely immune to physical damage, but [npc.she] is also unable to inflict any serious unarmed damage."
					+ " [npc.Her] slime core is pulsating with an immense power, revealing the fact that [npc.sheIs] a true demonic slime.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 0f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 25f)),
			Util.newArrayListOfValues(),
			"Slimy Fun",
			"Slimy Funs",
			"SLIME_BASIC",
			"SLIME_ADVANCED",
			Race.SLIME,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_SLIME,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Someone who is made completely of slime, with a sold core suspended in the place where their heart should be.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.BAT_CAVERNS, SubspeciesSpawnRarity.TEN)), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES),
			true, BodyMaterial.SLIME
	) {
	    
		@Override
		public String getSVGString(GameCharacter character) {
			if(character==null) {
				return Subspecies.HUMAN.getBodyMaterialSVGString(null, getSubspeciesBodyMaterial());
			}
			AbstractSubspecies fleshSubspecies = character.getBody().getFleshSubspecies();
			if (fleshSubspecies == Subspecies.HUMAN) {
				return Subspecies.SLIME.getBodyMaterialSVGString(character, getSubspeciesBodyMaterial());
			}
			return fleshSubspecies.getBodyMaterialSVGString(character, getSubspeciesBodyMaterial());
		}
		
		@Override
		public String getName(Body body) {
			if(body == null) {
				return super.getName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getName(body);
			} else if(coreSubspecies==Subspecies.DEMON && body.getSubspeciesOverride()==null) {
				return coreSubspecies.getName(body)+"-mimic-slime";
			}
			return coreSubspecies.getName(body)+"-slime";
		}
		
		@Override
		public String getNamePlural(Body body) {
			if(body ==null) {
				return super.getNamePlural(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getNamePlural(body);
			} else if(coreSubspecies==Subspecies.DEMON && body.getSubspeciesOverride()==null) {
				return coreSubspecies.getName(body)+"-mimic-slimes";
			}
			return coreSubspecies.getName(body)+"-slimes";
		}

		@Override
		public String getSingularMaleName(Body body) {
			if(body ==null) {
				return super.getSingularMaleName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getSingularMaleName(body);
			} else if(coreSubspecies==Subspecies.DEMON && body.getSubspeciesOverride()==null) {
				return coreSubspecies.getSingularMaleName(body)+"-mimic-slime";
			}
			return coreSubspecies.getSingularMaleName(body)+"-slime";
		}

		@Override
		public String getSingularFemaleName(Body body) {
			if(body ==null) {
				return super.getSingularFemaleName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getSingularFemaleName(body);
			} else if(coreSubspecies==Subspecies.DEMON && body.getSubspeciesOverride()==null) {
				return coreSubspecies.getSingularFemaleName(body)+"-mimic-slime";
			}
			return coreSubspecies.getSingularFemaleName(body)+"-slime";
		}

		@Override
		public String getPluralMaleName(Body body) {
			if(body ==null) {
				return super.getPluralMaleName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getPluralMaleName(body);
			} else if(coreSubspecies==Subspecies.DEMON && body.getSubspeciesOverride()==null) {
				return coreSubspecies.getSingularMaleName(body)+"-mimic-slimes";
			}
			return coreSubspecies.getSingularMaleName(body)+"-slimes";
		}

		@Override
		public String getPluralFemaleName(Body body) {
			if(body ==null) {
				return super.getPluralFemaleName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getPluralFemaleName(body);
			} else if(coreSubspecies==Subspecies.DEMON && body.getSubspeciesOverride()==null) {
				return coreSubspecies.getSingularFemaleName(body)+"-mimic-slimes";
			}
			return coreSubspecies.getSingularFemaleName(body)+"-slimes";
		}


		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			if(character==null) {
				return Subspecies.HUMAN.getSVGStringDesaturated(null);
			}
			return character.getBody().getFleshSubspecies().getSVGStringDesaturated(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.SLIME) {
				return 10_000; // Slimes should always be slime, no matter their underlying subspecies
			}
			return 0;
		}
		public FeralAttributes getFeralAttributes(Body body) {
			if(body==null) {
				return super.getFeralAttributes(body);
			}
			return body.getFleshSubspecies().getFeralAttributes(body);
		}
	};
	
	// LATEX CREATURE:
	public static AbstractSubspecies LATEX_CREATURE = new AbstractSubspecies(true,
			10000,
			"sightglass_race_latex_liqueur",
			"sightglass_race_latex_licorice",
			"statusEffects/race/raceSlime",
			"statusEffects/race/raceBackgroundSlime",
			"latex creature",
			"latex creatures",
			"rubber-boy",
			"rubber-girl",
			"rubber-boys",
			"rubber-girls",
			null,
			Nocturnality.DIURNAL,
			"Due to [npc.her] body being made of living latex, [npc.nameIsFull] highly resistant to physical damage and generally immune to substances that would be toxic to fleshy creatures."
			    + " The constant rubbing and roiling of the slick, pliable latex constantly stimulates [npc.himHer], raising [npc.her] lust during the excitement of combat.",
			"Due to [npc.her] body being made of living latex, [npc.nameIsFull] highly resistant to physical damage."
			    + " The slick, glossy latex that makes up [npc.her] body shimmers with an eerie iridescence, hinting at [npc.her] true nature as a latex demon.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 0f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.RESISTANCE_POISON, 25f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 25f),
					new Value<>(Attribute.RESTING_LUST, 15f)),
			Util.newArrayListOfValues(),
			"Playing with Latex",
			"Playing with Latex",
			"LATEX_BASIC",
			"LATEX_ADVANCED",
			Race.LATEX_CREATURE,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.BASE_BLACK,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Someone who is made completely of living rubber.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.BAT_CAVERNS, SubspeciesSpawnRarity.TEN)), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES),
			true, BodyMaterial.RUBBER,
			true, "latex ", "corrupted latex ","latex "
	) {
	    
		@Override
		public String getSVGString(GameCharacter character) {
			if(character==null) {
				return Subspecies.HUMAN.getBodyMaterialSVGString(null, getSubspeciesBodyMaterial(), 
					"#ffffff", Util.newArrayListOfValues("#404040", "#202020", "#101010"));
			}
			AbstractSubspecies fleshSubspecies = character.getBody().getFleshSubspecies();
			return fleshSubspecies.getBodyMaterialSVGString(character, getSubspeciesBodyMaterial(), 
				"#ffffff", Util.newArrayListOfValues("#404040", "#202020", "#101010"));
		}
			
		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			if(character==null) {
				return Subspecies.HUMAN.getSVGStringDesaturated(null);
			}
			return character.getBody().getFleshSubspecies().getSVGStringDesaturated(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.LATEX_CREATURE) {
				return 10_000;
			}
			return 0;
		}
		
		@Override
		public FeralAttributes getFeralAttributes(Body body) {
			if(body==null) {
				return super.getFeralAttributes(body);
			}
			return body.getFleshSubspecies().getFeralAttributes(body);
		}
	};
	
	// PLANT CREATURE:
	public static AbstractSubspecies PLANT_CREATURE = new AbstractSubspecies(true,
			10000,
			"sightglass_race_plant_water",
			"sightglass_race_plant_seed",
			"statusEffects/race/raceSlime",
			"statusEffects/race/raceBackgroundSlime",
			"plant-folk",
			"plant-folk",
			"treant",
			"dryads",
			"treants",
			"dryads",
			null,
			Nocturnality.DIURNAL,
			"[npc.NameIsFull] a plant creature, composed of magically-enhanced foliage given motion. [npc.She] [npc.has] a deep affinity for growing new life, and [npc.verb(benefit)] from bright light.",
			"[npc.NameIsFull] a plant creature, composed of magically-enhanced foliage given motion. [npc.She] [npc.has] a deep affinity for growing new life, and [npc.verb(benefit)] from bright light." 
			    + "[npc.Her] demon core is visible wrapped in a leafy calyx, pulsing with potential.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.MAJOR_ARCANE, 10f),
					new Value<>(Attribute.DAMAGE_LUST, 10f),
					new Value<>(Attribute.VIRILITY, 25f),
					new Value<>(Attribute.FERTILITY, 25f)
					),
			Util.newArrayListOfValues(),
			"Green Vines",
			"Green Vines",
			"PLANT_BASIC",
			"PLANT_ADVANCED",
			Race.PLANT_CREATURE,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.BASE_GREEN,
			SubspeciesPreference.FOUR_ABUNDANT,
			"An animated plant-creature",
			Util.newHashMapOfValues(
				new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.THREE),
				new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.FIVE),
				new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TWO),
				new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.SEVEN),
				new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.FOUR),
				new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.SEVEN),
				new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.FOUR),
				new Value<>(WorldRegion.JUNGLE, SubspeciesSpawnRarity.SEVEN),
				new Value<>(WorldRegion.YOUKO_FOREST, SubspeciesSpawnRarity.FIVE),
				new Value<>(WorldRegion.MOUNTAINS, SubspeciesSpawnRarity.TWO)
			),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TWO)
			), null, 
			Util.newArrayListOfValues(SubspeciesFlag.HIDDEN_FROM_PREFERENCES),
			true, BodyMaterial.PLANT,
			true, "plantfolk "," mandragora ", "beast-plant "
	) {
	    
		@Override
		public Map<AbstractAttribute, Float> getStatusEffectAttributeModifiers(GameCharacter character) {
			if (character != null && character.isVisiblyPregnant()) {
			    return Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15f),
					new Value<>(Attribute.MAJOR_ARCANE, 15f),
					new Value<>(Attribute.DAMAGE_LUST, 20f),
					new Value<>(Attribute.HEALTH_MAXIMUM, 50f),
					new Value<>(Attribute.VIRILITY, 25f),
					new Value<>(Attribute.FERTILITY, 25f)
					);
			}
			return super.getStatusEffectAttributeModifiers(character);
		}
		
		@Override
		public String getStatusEffectDescription(GameCharacter character) {
			if (character != null && character.isVisiblyPregnant()) {
			    return super.getStatusEffectDescription(character) 
				    + UtilText.parse(character, " [npc.Her] pregnancy gives [npc.him] a healthy glow!");
			}
			return super.getStatusEffectDescription(character);
		}
	    
		@Override
		public String getSVGString(GameCharacter character) {
			if(character==null) {
				return Subspecies.HUMAN.getBodyMaterialSVGString(null, getSubspeciesBodyMaterial(), 
					"#203603", Util.newArrayListOfValues("#5dd446", "#27912f", "#18a34b"));
			}
			AbstractSubspecies fleshSubspecies = character.getBody().getFleshSubspecies();
			return fleshSubspecies.getBodyMaterialSVGString(character, getSubspeciesBodyMaterial(), 
				"#203603", Util.newArrayListOfValues("#5dd446", "#27912f", "#18a34b"));
		}
		
		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			if(character==null) {
				return Subspecies.HUMAN.getSVGStringDesaturated(null);
			}
			return character.getBody().getFleshSubspecies().getSVGStringDesaturated(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.PLANT_CREATURE) {
				return 10_000;
			}
			return 0;
		}
		
		@Override
		public FeralAttributes getFeralAttributes(Body body) {
			if(body==null) {
				return super.getFeralAttributes(body);
			}
			return body.getFleshSubspecies().getFeralAttributes(body);
		}
	};
	
	// FUNGUS CREATURE:
	public static AbstractSubspecies FUNGUS_CREATURE = new AbstractSubspecies(true,
			10000,
			"sightglass_race_fungus_kombucha",
			"sightglass_race_fungus_truffle",
			"statusEffects/race/raceSlime",
			"statusEffects/race/raceBackgroundSlime",
			"mushroom creature",
			"mushroom creatures",
			"mushroom-boy",
			"mushroom-girl",
			"mushroom-boys",
			"mushroom-girls",
			null,
			Nocturnality.NOCTURNAL,
			"[npc.NameIsFull] a fungus creature, composed of a network of magically-enhanced fungal mycelia. [npc.She] gradually [npc.verb(sap)] the strength of opponents in battle.",
			"[npc.NameIsFull] a fungus creature, composed of a network of magically-enhanced fungal mycelia. [npc.She] gradually [npc.verb(sap)] the strength of opponents in battle." 
			    + "The glow of [npc.her] demon core is visible embedded in [npc.her] chest, pulsing with potential.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.MAJOR_ARCANE, 15f),
					new Value<>(Attribute.DAMAGE_LUST, 12f),
					new Value<>(Attribute.DAMAGE_POISON, 12f)
					),
			Util.newArrayListOfValues(),
			"Kingdom of Mushrooms",
			"Kingdom of Mushrooms",
			"FUNGUS_BASIC",
			"FUNGUS_ADVANCED",
			Race.FUNGUS_CREATURE,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 3),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 3),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.BASE_PINK_SALMON,
			SubspeciesPreference.FOUR_ABUNDANT,
			"An animated fungus-creature",
			Util.newHashMapOfValues(
				new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
				new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.FOUR),
				new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TWO),
				new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.SIX),
				new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
				new Value<>(WorldRegion.JUNGLE, SubspeciesSpawnRarity.SIX),
				new Value<>(WorldRegion.YOUKO_FOREST, SubspeciesSpawnRarity.THREE),
				new Value<>(WorldRegion.MOUNTAINS, SubspeciesSpawnRarity.TWO),
				new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.SEVEN)
			),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TWO)
			), null, 
			Util.newArrayListOfValues(SubspeciesFlag.HIDDEN_FROM_PREFERENCES),
			true, BodyMaterial.FUNGUS,
			true, "fungal ", "corrupted fungal ", "fungal "
	) {
					
		@Override
		public String getSVGString(GameCharacter character) {
			if(character==null) {
				return Subspecies.HUMAN.getBodyMaterialSVGString(null, getSubspeciesBodyMaterial(), 
					"#cf2b5c", Util.newArrayListOfValues("#fffbf2", "#e3b394", "#d96c7e"));
			}
			AbstractSubspecies fleshSubspecies = character.getBody().getFleshSubspecies();
			return fleshSubspecies.getBodyMaterialSVGString(character, getSubspeciesBodyMaterial(), 
				"#cf2b5c", Util.newArrayListOfValues("#fffbf2", "#e3b394", "#d96c7e"));
		}

		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			if(character==null) {
				return Subspecies.HUMAN.getSVGStringDesaturated(null);
			}
			return character.getBody().getFleshSubspecies().getSVGStringDesaturated(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.FUNGUS_CREATURE) {
				return 10_000;
			}
			return 0;
		}
		
		@Override
		public FeralAttributes getFeralAttributes(Body body) {
			if(body==null) {
				return super.getFeralAttributes(body);
			}
			return body.getFleshSubspecies().getFeralAttributes(body);
		}
		
		@Override
		public void applySpeciesChanges(Body body) {
			super.applySpeciesChanges(body);
			if(body.hasPenisIgnoreDildo() && Math.random()<0.35) {
				body.getPenis().getTesticle().getCum().addFluidModifier(null, FluidModifier.HALLUCINOGENIC);
			}
			if(body.hasVagina() && Math.random()<0.35) {
				body.getVagina().getGirlcum().addFluidModifier(null, FluidModifier.HALLUCINOGENIC);
			}
		}
	};
        
	// RODENTS:
	public static AbstractSubspecies SQUIRREL_MORPH = new AbstractSubspecies(true,
			6000,
			"innoxia_race_squirrel_squirrel_java",
			"innoxia_race_squirrel_round_nuts",
			"statusEffects/race/raceSquirrelMorph",
			"statusEffects/race/raceBackground",
			"squirrel-morph",
			"squirrel-morphs",
			"squirrel-boy",
			"squirrel-girl",
			"squirrel-boys",
			"squirrel-girls",
			new FeralAttributes(
					"squirrel",
					"squirrels",
					LegConfiguration.QUADRUPEDAL,
					15,
					0,
					1,
					4,
					1, false),
			Nocturnality.DIURNAL,
			"[npc.NameIsFull] very agile and alert, and [npc.is] capable of leaping great distances with [npc.her] powerful [npc.legs].",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f)),
			null,
			"Chasing Squirrels",
			"Chasing Squirrels'",
			"SQUIRREL_MORPH_BASIC",
			"SQUIRREL_MORPH_ADVANCED",
			Race.SQUIRREL_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_SQUIRREL_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"An anthropomorphic squirrel, known as a 'squirrel-morph' when bipedal, and a 'squirreltaur' when the lower body is that of an oversized feral squirrel.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.SQUIRREL_MORPH) {
				return 100;
			}
			return 0;
		}
	};
	
	//MOUSE_MORPH(Race.MOUSE_MORPH.getName(), Race.MOUSE_MORPH, RacialBody.MOUSE_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.MOUSE_MORPH.getName()),
	
	public static AbstractSubspecies RAT_MORPH = new AbstractSubspecies(true,
			6000,
			"innoxia_race_rat_black_rats_rum",
			"innoxia_race_rat_brown_rats_burger",
			"statusEffects/race/raceRatMorph",
			"statusEffects/race/raceBackground",
			"rat-morph",
			"rat-morphs",
			"rat-boy",
			"rat-girl",
			"rat-boys",
			"rat-girls",
			new FeralAttributes(
					"rat",
					"rats",
					LegConfiguration.QUADRUPEDAL,
					15,
					0,
					1,
					6,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NamePos] body is very hardy, and [npc.she] [npc.has] both a high resistance to, and affinity with, arcane poison.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 0f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 10f),
					new Value<>(Attribute.DAMAGE_POISON, 15f),
					new Value<>(Attribute.RESISTANCE_POISON, 5f)),
			null,
			"Causing Mischief",
			"Causing Mischiefs",
			"RAT_MORPH_BASIC",
			"RAT_MORPH_ADVANCED",
			Race.RAT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_RAT_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"An anthropomorphic rat, known as a 'rat-morph' when bipedal, and a 'rat-taur' when the lower body is that of an oversized feral rat.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.FIVE)), Util.newHashMapOfValues(
							new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TWO)), null, null) {
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.RAT_MORPH) {
				return 100;
			}
			return 0;
		}
	};

	public static AbstractSubspecies RABBIT_MORPH = new AbstractSubspecies(true,
			12000,
			"innoxia_race_rabbit_bunny_juice",
			"innoxia_race_rabbit_bunny_carrot_cake",
			"statusEffects/race/raceRabbitMorph",
			"statusEffects/race/raceBackground",
			"rabbit-morph",
			"rabbit-morphs",
			"rabbit-boy",
			"rabbit-girl",
			"rabbit-boys",
			"rabbit-girls",
			new FeralAttributes(
					"rabbit",
					"rabbits",
					LegConfiguration.QUADRUPEDAL,
					20,
					0,
					1,
					5,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NameIsFull] very agile and alert, and [npc.is] capable of short bursts of incredible speed."
					+ " [npc.Her] body, whether [npc.she] [npc.verb(like)] it or not, is also adapted for producing as many offspring as possible.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 5f),
					new Value<>(Attribute.FERTILITY, 50f),
					new Value<>(Attribute.VIRILITY, 50f)),
			null,
			"Bunny Litters",
			"Bunny Litters'",
			"RABBIT_MORPH_BASIC",
			"RABBIT_MORPH_ADVANCED",
			Race.RABBIT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_RABBIT_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"An anthropomorphic rabbit, known as a 'rabbit-morph' when bipedal, and a 'rabbit-taur' when the lower body is that of an oversized feral rabbit.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.FIVE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public void applySpeciesChanges(Body body) {
			if(body.isFeminine() && body.getRaceStage()==RaceStage.GREATER) {
				body.getHair().setNeckFluff(null, Math.random()<0.1f);
			}
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"jackalope",
					"jackalopes",
					"jackalope",
					"jackalope",
					"jackalopes",
					"jackalopes"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "jackalope", false, false),
					applyNonBipedNameChange(body, "jackalope", false, true),
					applyNonBipedNameChange(body, "jackalope", false, false),
					applyNonBipedNameChange(body, "jackalope", true, false),
					applyNonBipedNameChange(body, "jackalope", false, true),
					applyNonBipedNameChange(body, "jackalope", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.RABBIT_MORPH) {
				return 100;
			}
			return 0;
		}
	};

	public static AbstractSubspecies RABBIT_MORPH_LOP = new AbstractSubspecies(false,
			12000,
			"innoxia_race_rabbit_bunny_juice",
			"innoxia_race_rabbit_bunny_carrot_cake",
			"statusEffects/race/raceRabbitLopMorph",
			"statusEffects/race/raceBackground",
			"lop-rabbit-morph",
			"lop-rabbit-morphs",
			"lop-rabbit-boy",
			"lop-rabbit-girl",
			"lop-rabbit-boys",
			"lop-rabbit-girls",
			new FeralAttributes(
					"lop-rabbit",
					"lop-rabbits",
					LegConfiguration.QUADRUPEDAL,
					20,
					0,
					1,
					5,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NameIsFull] very agile and alert, and [npc.is] capable of short bursts of incredible speed."
					+ " [npc.Her] body, whether [npc.she] [npc.verb(like)] it or not, is also adapted for producing as many offspring as possible.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 5f),
					new Value<>(Attribute.FERTILITY, 50f),
					new Value<>(Attribute.VIRILITY, 50f)),
			null,
			"Bunny Litters",
			"Bunny Litters'",
			"RABBIT_MORPH_BASIC",
			"RABBIT_MORPH_ADVANCED",
			Race.RABBIT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_RABBIT_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"An anthropomorphic rabbit which has floppy ears instead of the usual upright ones."
					+ " Known as a 'lop-rabbit-morph' when bipedal, and a 'lop-rabbit-taur' when the lower body is that of an oversized feral lop-rabbit.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.FIVE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public void applySpeciesChanges(Body body) {
			if(body.getEar().getType()==EarType.RABBIT_MORPH) {
				body.getEar().setType(null, EarType.RABBIT_MORPH_FLOPPY);
			}
			if(body.isFeminine() && body.getRaceStage()==RaceStage.GREATER) {
				body.getHair().setNeckFluff(null, Math.random()<0.1f);
			}
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"jackalope",
					"jackalopes",
					"jackalope",
					"jackalope",
					"jackalopes",
					"jackalopes"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "jackalope", false, false),
					applyNonBipedNameChange(body, "jackalope", false, true),
					applyNonBipedNameChange(body, "jackalope", false, false),
					applyNonBipedNameChange(body, "jackalope", true, false),
					applyNonBipedNameChange(body, "jackalope", false, true),
					applyNonBipedNameChange(body, "jackalope", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.RABBIT_MORPH && body.getEar().getType()==EarType.RABBIT_MORPH_FLOPPY) {
				return 150;
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies BAT_MORPH = new AbstractSubspecies(true,
			10000,
			"innoxia_race_bat_fruit_bats_juice_box",
			"innoxia_race_bat_fruit_bats_salad",
			"statusEffects/race/raceBatMorph",
			"statusEffects/race/raceBackground",
			"bat-morph",
			"bat-morphs",
			"bat-boy",
			"bat-girl",
			"bat-boys",
			"bat-girls",
			new FeralAttributes(
					"bat",
					"bats",
					LegConfiguration.WINGED_BIPED,
					30,
					0,
					1,
					1,
					1, false) {
				@Override
				public boolean isArmsOrWingsPresent() {
					return true;
				}
			},
			Nocturnality.NOCTURNAL,
			"Due to their unique echolocation ability, all bat-morphs have a natural desire to talk as much as possible."
					+ " Due to this, [npc.name] continuously [npc.verb(play)] out conversations in [npc.her] head, allowing [npc.herHim] to think up new and exciting ways to seduce people before having ever met them.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null,
			"Flying Foxes",
			"Flying Foxes'",
			"BAT_MORPH_BASIC",
			"BAT_MORPH_ADVANCED",
			Race.BAT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_BAT_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"An anthropomorphic, bipedal bat.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.JUNGLE, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.SUBMISSION, SubspeciesSpawnRarity.TWO),
				new Value<>(WorldType.BAT_CAVERNS, SubspeciesSpawnRarity.TEN),
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.ONE)), null, null) {
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.BAT_MORPH) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	
	// AVIAN:
	public static AbstractSubspecies HARPY = new AbstractSubspecies(true,
			12000,
			"innoxia_race_harpy_harpy_perfume",
			"innoxia_race_harpy_bubblegum_lollipop",
			"statusEffects/race/raceHarpy",
			"statusEffects/race/raceBackground",
			"harpy",
			"harpies",
			"harpy",
			"harpy",
			"harpies",
			"harpies",
			new FeralAttributes(
					"bird-of-paradise",
					"birds-of-paradise",
					LegConfiguration.AVIAN,
					30,
					0,
					1,
					0,
					1,
					true) {
				@Override
				public boolean isArmsOrWingsPresent() {
					return true;
				}
			},
			Nocturnality.DIURNAL,
			"[npc.NameIsFull] obsessed with [npc.her] appearance, and wouldn't think it unusual for someone to want to spend at least half of their waking hours preening themselves in order to look as attractive as possible.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 0f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null,
			"All About Harpies",
			"All About Harpies'",
			"HARPY_BASIC",
			"HARPY_ADVANCED",
			Race.HARPY,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_HARPY,
			SubspeciesPreference.FOUR_ABUNDANT,
			"An anthropomorphic, bipedal bird. Typically only possessing non-human arms, legs, eyes, ears, and hair.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.JUNGLE, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.HARPY_NEST, SubspeciesSpawnRarity.TEN),
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, Util.newArrayListOfValues(
				SubspeciesFlag.DISABLE_SPAWN_PREFERENCE,
				SubspeciesFlag.DISABLE_FURRY_PREFERENCE)) {
		@Override
		public String getName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && (body ==null || (!body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL))) {
				return "birb";
			}
			return super.getName(body);
		}
		@Override
		public String getNamePlural(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && (body ==null || (!body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL))) {
				return "birbs";
			}
			return super.getNamePlural(body);
		}
		@Override
		public String getSingularMaleName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && body !=null && !body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL) {
				return "birb";
			}
			return super.getSingularMaleName(body);
		}
		@Override
		public String getSingularFemaleName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && body !=null && !body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL) {
				return "birb";
			}
			return super.getSingularFemaleName(body);
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"fury",
					"furies",
					"fury",
					"fury",
					"furies",
					"furies"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "fury", false, false),
					applyNonBipedNameChange(body, "fury", false, true),
					applyNonBipedNameChange(body, "fury", false, false),
					applyNonBipedNameChange(body, "fury", true, false),
					applyNonBipedNameChange(body, "fury", false, true),
					applyNonBipedNameChange(body, "fury", true, true)
				};
			}
			
			return names;
		}
		@Override
		public String getNonBipedRaceName(Body body) {
			return "harpy";
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HARPY) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	
	public static AbstractSubspecies HARPY_RAVEN = new AbstractSubspecies(false,
			14000,
			"innoxia_race_harpy_harpy_perfume",
			"innoxia_race_harpy_bubblegum_lollipop",
			"statusEffects/race/raceHarpy",
			"statusEffects/race/raceBackground",
			"raven-harpy",
			"raven-harpies",
			"raven-harpy",
			"raven-harpy",
			"raven-harpies",
			"raven-harpies",
			new FeralAttributes(
					"raven",
					"ravens",
					LegConfiguration.AVIAN,
					60,
					0,
					1,
					0,
					1, false) {
				public boolean isArmsOrWingsPresent() {
					return true;
				}
			},
			Nocturnality.DIURNAL,
			"[npc.NameIsFull] obsessed with [npc.her] appearance, and wouldn't think it unusual for someone to want to spend at least half of their waking hours preening themselves in order to look as attractive as possible.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 0f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null,
			"All About Harpies",
			"All About Harpies'",
			"HARPY_BASIC",
			"HARPY_ADVANCED",
			Race.HARPY,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.BASE_BLACK,
			SubspeciesPreference.ONE_LOW,
			"An anthropomorphic, bipedal raven, with dark black feathers. Typically only possessing non-human arms, legs, eyes, ears, and hair.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.HARPY_NEST, SubspeciesSpawnRarity.THREE),
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, Util.newArrayListOfValues(
				SubspeciesFlag.DISABLE_FURRY_PREFERENCE)) {
		@Override
		public void applySpeciesChanges(Body body) {
			Colour ravenColour = PresetColour.COVERING_BLACK;
			if(Math.random()<0.5f) {
				ravenColour = PresetColour.COVERING_JET_BLACK;
			}
			body.getCoverings().put(BodyCoveringType.FEATHERS, new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, ravenColour, false, ravenColour, false));
			body.getCoverings().put(BodyCoveringType.BODY_HAIR_HARPY, new Covering(BodyCoveringType.BODY_HAIR_HARPY, CoveringPattern.NONE, ravenColour, false, ravenColour, false));
			body.getCoverings().put(BodyCoveringType.HAIR_HARPY, new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, ravenColour, false, ravenColour, false));
			body.getCoverings().put(BodyCoveringType.HARPY_SKIN, new Covering(BodyCoveringType.HARPY_SKIN, CoveringPattern.NONE, PresetColour.SKIN_EBONY, false, PresetColour.SKIN_EBONY, false));
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"fury",
					"furies",
					"fury",
					"fury",
					"furies",
					"furies"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "fury", false, false),
					applyNonBipedNameChange(body, "fury", false, true),
					applyNonBipedNameChange(body, "fury", false, false),
					applyNonBipedNameChange(body, "fury", true, false),
					applyNonBipedNameChange(body, "fury", false, true),
					applyNonBipedNameChange(body, "fury", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HARPY) {
				AbstractBodyCoveringType feathers = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FEATHER):BodyCoveringType.FEATHERS;
				
				if(body.getCoverings().get(feathers).getPrimaryColour()==PresetColour.COVERING_BLACK
						|| body.getCoverings().get(feathers).getPrimaryColour()==PresetColour.COVERING_JET_BLACK) {
					return 150;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	
	public static AbstractSubspecies HARPY_SWAN = new AbstractSubspecies(false,
			22000,
			"innoxia_race_harpy_harpy_perfume",
			"innoxia_race_harpy_bubblegum_lollipop",
			"statusEffects/race/raceHarpy",
			"statusEffects/race/raceBackground",
			"swan-harpy",
			"swan-harpies",
			"swan-harpy",
			"swan-harpy",
			"swan-harpies",
			"swan-harpies",
			new FeralAttributes(
					"swan",
					"swans",
					LegConfiguration.AVIAN,
					100,
					0,
					1,
					0,
					1, false) {
				public boolean isArmsOrWingsPresent() {
					return true;
				}
			},
			Nocturnality.DIURNAL,
			"[npc.NameIsFull] obsessed with [npc.her] appearance, and wouldn't think it unusual for someone to want to spend at least half of their waking hours preening themselves in order to look as attractive as possible.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 0f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null,
			"All About Harpies",
			"All About Harpies'",
			"HARPY_BASIC",
			"HARPY_ADVANCED",
			Race.HARPY,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.BASE_WHITE,
			SubspeciesPreference.ONE_LOW,
			"An anthropomorphic, bipedal swan, with white feathers and either ebony or grey leg skin. Typically only possessing non-human arms, legs, eyes, ears, and hair.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.RIVER, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.HARPY_NEST, SubspeciesSpawnRarity.THREE),
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, Util.newArrayListOfValues(
				SubspeciesFlag.DISABLE_FURRY_PREFERENCE)) {
		@Override
		public void applySpeciesChanges(Body body) {
			body.getCoverings().put(BodyCoveringType.FEATHERS, new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false));
			body.getCoverings().put(BodyCoveringType.HAIR_HARPY, new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false));
			body.getCoverings().put(BodyCoveringType.BODY_HAIR_HARPY, new Covering(BodyCoveringType.BODY_HAIR_HARPY, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false));
			body.getCoverings().put(BodyCoveringType.HARPY_SKIN, new Covering(BodyCoveringType.HARPY_SKIN, CoveringPattern.NONE, PresetColour.SKIN_EBONY, false, PresetColour.SKIN_EBONY, false));
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"fury",
					"furies",
					"fury",
					"fury",
					"furies",
					"furies"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "fury", false, false),
					applyNonBipedNameChange(body, "fury", false, true),
					applyNonBipedNameChange(body, "fury", false, false),
					applyNonBipedNameChange(body, "fury", true, false),
					applyNonBipedNameChange(body, "fury", false, true),
					applyNonBipedNameChange(body, "fury", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HARPY) {
				AbstractBodyCoveringType feathers = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FEATHER):BodyCoveringType.FEATHERS;
				AbstractBodyCoveringType legSkin = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_SKIN):BodyCoveringType.HARPY_SKIN;
				Colour legColour = body.getCoverings().get(legSkin).getPrimaryColour();
				
				if(body.getCoverings().get(feathers).getPrimaryColour()==PresetColour.COVERING_WHITE
						&& (legColour==PresetColour.SKIN_GREY || legColour==PresetColour.SKIN_EBONY)) {
					return 150;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	
//	public static AbstractSubspecies HARPY_BALD_EAGLE = new AbstractSubspecies(false,
//			16000,
//			"innoxia_race_harpy_harpy_perfume",
//			"innoxia_race_harpy_bubblegum_lollipop",
//			"statusEffects/race/raceHarpy",
//			"statusEffects/race/raceBackground",
//			"bald-eagle-harpy",
//			"bald-eagle-harpies",
//			"bald-eagle-harpy",
//			"bald-eagle-harpy",
//			"bald-eagle-harpies",
//			"bald-eagle-harpies",
//			new FeralAttributes(
//					"bald-eagle",
//					"bald-eagles",
//					LegConfiguration.AVIAN,
//					90,
//					0,
//					1,
//					0,
//					1,
//					true) {
//				public boolean isArmsOrWingsPresent() {
//					return true;
//				}
//			},
//			"[npc.NameIsFull] obsessed with [npc.her] appearance, and wouldn't think it unusual for someone to want to spend at least half of their waking hours preening themselves in order to look as attractive as possible.",
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
//					new Value<>(Attribute.MAJOR_ARCANE, 0f),
//					new Value<>(Attribute.MAJOR_CORRUPTION, 5f),
//					new Value<>(Attribute.DAMAGE_LUST, 15f)),
//			null,
//			"All About Harpies",
//			"All About Harpies'",
//			"HARPY_BASIC",
//			"HARPY_ADVANCED",
//			Race.HARPY,
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 2),
//					new Value<>(PerkCategory.LUST, 5),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 2),
//					new Value<>(PerkCategory.LUST, 5),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			PresetColour.BASE_GREY_LIGHT,
//			SubspeciesPreference.ONE_LOW,
//			"An anthropomorphic, bipedal bald eagle, dark brown feathers covering their body and white feathers on their head. Typically only possessing non-human arms, legs, eyes, ears, and hair.",
//			Util.newHashMapOfValues(
//					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE_UNCOMMON),
//					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.THREE_UNCOMMON),
//					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TWO_RARE)),
//			Util.newHashMapOfValues(
//					new Value<>(WorldType.HARPY_NEST, SubspeciesSpawnRarity.ONE_VERY_RARE),
//					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.FOUR_COMMON)),
//			null, Util.newArrayListOfValues(
//					SubspeciesFlag.DISABLE_FURRY_PREFERENCE)) {
//		@Override
//		public void applySpeciesChanges(Body body) {
//			body.getCoverings().put(BodyCoveringType.FEATHERS, new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_BROWN_DARK, false));
//			body.getCoverings().put(BodyCoveringType.HAIR_HARPY, new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false));
//			body.getCoverings().put(BodyCoveringType.BODY_HAIR_HARPY, new Covering(BodyCoveringType.BODY_HAIR_HARPY, CoveringPattern.NONE, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_BROWN_DARK, false));
//			body.getCoverings().put(BodyCoveringType.HARPY_SKIN, new Covering(BodyCoveringType.HARPY_SKIN, CoveringPattern.NONE, PresetColour.SKIN_YELLOW, false, PresetColour.SKIN_YELLOW, false));
//		}
//		@Override
//		public String[] getHalfDemonName(GameCharacter character) {
//			String[] names = new String[] {
//					"fury",
//					"furies",
//					"fury",
//					"fury",
//					"furies",
//					"furies"};
//			
//			if(character!=null && !character.getHalfDemonSubspecies().isNonBiped()) {
//				names = new String[] {
//					applyNonBipedNameChange(character, "fury", false, false),
//					applyNonBipedNameChange(character, "fury", false, true),
//					applyNonBipedNameChange(character, "fury", false, false),
//					applyNonBipedNameChange(character, "fury", true, false),
//					applyNonBipedNameChange(character, "fury", false, true),
//					applyNonBipedNameChange(character, "fury", true, true)
//				};
//			}
//			
//			return names;
//		}
//		@Override
//		public int getSubspeciesWeighting(Body body, AbstractRace race) {
//			if(race==Race.HARPY) {
//				AbstractBodyCoveringType feathers = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FEATHER):BodyCoveringType.FEATHERS;
//				AbstractBodyCoveringType headFeathers = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.HAIR):BodyCoveringType.HAIR_HARPY;
//				
//				if(body.getCoverings().get(feathers).getPrimaryColour()==PresetColour.COVERING_BROWN_DARK
//						&& body.getCoverings().get(headFeathers).getPrimaryColour()==PresetColour.COVERING_WHITE) {
//					return 150;
//				}
//			}
//			return 0;
//		}
//		@Override
//		public String getPathName() {
//			return "res/race/neverLucky/harpy/bald_eagle";
//		}
//		@Override
//		public Colour getSecondaryColour() {
//			return PresetColour.BASE_PITCH_BLACK;
//		}
//		@Override
//		public Colour getTertiaryColour() {
//			return PresetColour.BASE_YELLOW;
//		}
//	};
	
	public static AbstractSubspecies HARPY_PHOENIX = new AbstractSubspecies(false,
			50000,
			"innoxia_race_harpy_harpy_perfume",
			"innoxia_race_harpy_bubblegum_lollipop",
			"statusEffects/race/raceHarpy",
			"statusEffects/race/raceBackgroundPhoenix",
			"phoenix-harpy",
			"phoenix-harpies",
			"phoenix-harpy",
			"phoenix-harpy",
			"phoenix-harpies",
			"phoenix-harpies",
			new FeralAttributes(
					"phoenix",
					"phoenixes",
					LegConfiguration.AVIAN,
					90,
					0,
					1,
					0,
					1,
					true) {
				public boolean isArmsOrWingsPresent() {
					return true;
				}
			},
			Nocturnality.DIURNAL,
			"While just as obsessed with [npc.her] looks as other harpies, [npc.nameIsFull] also naturally talented at harnessing the arcane, allowing [npc.herHim] to learn and cast spells with relative ease."
				+ " In particular, [npc.she] [npc.has] an exceptionally high affinity with arcane fire...",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 10f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 10f),
					new Value<>(Attribute.DAMAGE_FIRE, 75f),
					new Value<>(Attribute.RESISTANCE_FIRE, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null,
			"All About Harpies",
			"All About Harpies'",
			"HARPY_BASIC",
			"HARPY_ADVANCED",
			Race.HARPY,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 5)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.BASE_ORANGE,
			SubspeciesPreference.ONE_LOW,
			"An anthropomorphic, bipedal, mythological bird, whose feathers are either glowing red, orange, or yellow, or are actually made out of arcane fire."
					+ " They are extremely rare and typically only possess non-human arms, legs, eyes, ears, and hair.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.VOLCANO, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.HARPY_NEST, SubspeciesSpawnRarity.ONE)), null, Util.newArrayListOfValues(
					SubspeciesFlag.DISABLE_FURRY_PREFERENCE)) {
		@Override
		public void applySpeciesChanges(Body body) {
			CoveringPattern pattern = CoveringPattern.OMBRE;
			if(Math.random()<0.5f) {
				pattern = CoveringPattern.HIGHLIGHTS;
			}
			body.getCoverings().put(BodyCoveringType.FEATHERS, new Covering(BodyCoveringType.FEATHERS, pattern, PresetColour.COVERING_ORANGE, true, PresetColour.COVERING_YELLOW, true));
			body.getCoverings().put(BodyCoveringType.HAIR_HARPY, new Covering(BodyCoveringType.HAIR_HARPY, pattern, PresetColour.COVERING_RED, true, PresetColour.COVERING_ORANGE, true));
			body.getCoverings().put(BodyCoveringType.BODY_HAIR_HARPY, new Covering(BodyCoveringType.BODY_HAIR_HARPY, CoveringPattern.NONE, PresetColour.COVERING_RED, true, PresetColour.COVERING_RED, true));
			body.getCoverings().put(BodyCoveringType.HARPY_SKIN, new Covering(BodyCoveringType.HARPY_SKIN, CoveringPattern.NONE, PresetColour.SKIN_ORANGE, false, PresetColour.SKIN_ORANGE, false));
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"phoenix-fury",
					"phoenix-furies",
					"phoenix-fury",
					"phoenix-fury",
					"phoenix-furies",
					"phoenix-furies"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "phoenix-fury", false, false),
					applyNonBipedNameChange(body, "phoenix-fury", false, true),
					applyNonBipedNameChange(body, "phoenix-fury", false, false),
					applyNonBipedNameChange(body, "phoenix-fury", true, false),
					applyNonBipedNameChange(body, "phoenix-fury", false, true),
					applyNonBipedNameChange(body, "phoenix-fury", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HARPY) {
				AbstractBodyCoveringType feathers = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FEATHER):BodyCoveringType.FEATHERS;
				
				if((body.getCoverings().get(feathers).isPrimaryGlowing()
						&& (body.getCoverings().get(feathers).getPrimaryColour()==PresetColour.COVERING_RED
							|| body.getCoverings().get(feathers).getPrimaryColour()==PresetColour.COVERING_ORANGE
							|| body.getCoverings().get(feathers).getPrimaryColour()==PresetColour.COVERING_YELLOW))
					|| body.getBodyMaterial()==BodyMaterial.FIRE) {
					return 200;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};

	// DOLLS:
	public static AbstractSubspecies DOLL = new AbstractSubspecies(true,
			10000,
			"innoxia_race_doll_silic_oil",
			null,
			"statusEffects/race/raceDoll",
			"statusEffects/race/raceBackgroundDoll",
			"doll",
			"dolls",
			"doll",
			"doll",
			"dolls",
			"dolls",
			null,
			Nocturnality.CATHEMERAL,
			"[npc.NameIsFull] an extremely realistic-looking, autonomous sex doll, created from arcane-infused silicone at the shop 'Lovienne's Luxuries'."
					+ " [npc.Her] artificial body and the nature of [npc.her] arcane-powered automation grants [npc.herHim] numerous characteristics.",
			"[npc.NameIsFull] an extremely realistic-looking, autonomous sex doll, created from arcane-infused silicone at the shop 'Lovienne's Luxuries'."
					+ " [npc.Her] artificial body and the nature of [npc.her] arcane-powered automation grants [npc.herHim] numerous characteristics.",
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Doll-specific perk tree)]",
					"[style.boldGood(Perks grant numerous effects)]"),
			"The Ultimate Toy",
			"The Ultimate Toys",
			"DOLL_BASIC",
			"DOLL_ADVANCED",
			Race.DOLL,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_DOLL,
			SubspeciesPreference.ZERO_NONE,
			"A lifelike rubber doll, which has been enchanted so as to be able to move, speak, and obey commands.",
			null,
			Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES),
			true, BodyMaterial.SILICONE
		) {
		@Override
		public AbstractItemType getTransformativeItem(GameCharacter owner) {
			return null;
		}
		@Override
		public void applySpeciesChanges(Body body) {
			// Doll subspecies are set in the Main.game.getCharacterUtils().generateBody() method
			body.setBodyMaterial(BodyMaterial.SILICONE);
		}
		@Override
		public String getName(Body body) {
			if(body == null) {
				return super.getName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getName(body);
			}
			return coreSubspecies.getName(body)+"-doll";
		}
		
		@Override
		public String getNamePlural(Body body) {
			if(body ==null) {
				return super.getNamePlural(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getNamePlural(body);
			}
			return coreSubspecies.getName(body)+"-dolls";
		}

		@Override
		public String getSingularMaleName(Body body) {
			if(body ==null) {
				return super.getSingularMaleName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getSingularMaleName(body);
			}
			return coreSubspecies.getSingularMaleName(body)+"-doll";
		}

		@Override
		public String getSingularFemaleName(Body body) {
			if(body ==null) {
				return super.getSingularFemaleName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getSingularFemaleName(body);
			}
			return coreSubspecies.getSingularFemaleName(body)+"-doll";
		}

		@Override
		public String getPluralMaleName(Body body) {
			if(body ==null) {
				return super.getPluralMaleName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getPluralMaleName(body);
			}
			return coreSubspecies.getSingularMaleName(body)+"-dolls";
		}

		@Override
		public String getPluralFemaleName(Body body) {
			if(body ==null) {
				return super.getPluralFemaleName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getPluralFemaleName(body);
			}
			return coreSubspecies.getSingularFemaleName(body)+"-dolls";
		}

		@Override
		public String getSVGString(GameCharacter character) {
			if(character==null) {
				return Subspecies.HUMAN.getBodyMaterialSVGString(null, getSubspeciesBodyMaterial());
			}
			AbstractSubspecies fleshSubspecies = character.getBody().getFleshSubspecies();
			if (fleshSubspecies == Subspecies.HUMAN) {
				return Subspecies.DOLL.getBodyMaterialSVGString(character, getSubspeciesBodyMaterial());
			}
			return fleshSubspecies.getBodyMaterialSVGString(character, getSubspeciesBodyMaterial());
		}

		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DOLL) {
				return 20_000; // Dolls should always be dolls, no matter their underlying subspecies
			}
			return 0;
		}
		public FeralAttributes getFeralAttributes(Body body) {
			if(body==null) {
				return super.getFeralAttributes(body);
			}
			return body.getFleshSubspecies().getFeralAttributes(body);
		}
	};
	
	// ELEMENTALS:
	
	public static AbstractSubspecies ELEMENTAL_FIRE = new AbstractSubspecies(false,
			100000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceElemental",
			"statusEffects/race/raceBackgroundFire",
			"fire elemental",
			"fire elementals",
			"fire elemental",
			"fire elemental",
			"fire elementals",
			"fire elementals",
			null,
			Nocturnality.DIURNAL,
			"[npc.NameIsFull] a summoned elemental, currently bound to the school of Fire.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15f),
					new Value<>(Attribute.DAMAGE_FIRE, 50f),
					new Value<>(Attribute.RESISTANCE_FIRE, 50f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Unlimited)] <b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'> self-transformations</b>"),
			"Fire Elementals",
			"Fire Elementals'",
			"ELEMENTAL_FIRE_BASIC",
			"ELEMENTAL_FIRE_ADVANCED",
			Race.ELEMENTAL,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 5)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.SPELL_SCHOOL_FIRE,
			SubspeciesPreference.FOUR_ABUNDANT, "An arcane elemental bound to the school of Fire.", null, Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 50_000;
		}
		public AbstractItemType getBook() {
			return ItemType.getLoreBook(this);
		}
		@Override
		public void applySpeciesChanges(Body body) {
			body.setBodyMaterial(BodyMaterial.FIRE);
		}
		@Override
		public String getSVGString(GameCharacter character) {
			if(character!=null && (character instanceof Elemental) && ((Elemental)character).getSummoner()!=null && !((Elemental)character).getSummoner().isElementalActive()) {
				if(((Elemental)character).getPassiveForm()==null) {
					String wispSVG = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_FIRE),
									this.getColour(character),
									this.getColour(character),
									this.getColour(character),
									"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getRaceWisp()+"</div>");
					return wispSVG;
				}
				AbstractSubspecies passiveForm = ((Elemental)character).getPassiveForm();
				if (passiveForm.SVGString == null) {
					passiveForm.initSVGStrings();
				}
				String raceSvg = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_FIRE),
						this.getColour(character),
						this.getColour(character),
						this.getColour(character),
						passiveForm.SVGStringUncoloured);
				return getBipedBackground(raceSvg, character, this.getColour(character));
			}
			return super.getSVGString(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.ELEMENTAL && body.getBodyMaterial()==BodyMaterial.FIRE) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		
		@Override
		public boolean isMaterialSubspecies() {
			return true;
		}
		
		@Override
		public BodyMaterial getSubspeciesBodyMaterial() {
			return BodyMaterial.FIRE;
		}
	};
	
	public static AbstractSubspecies ELEMENTAL_EARTH = new AbstractSubspecies(false,
			100000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceElemental",
			"statusEffects/race/raceBackgroundEarth",
			"earth elemental",
			"earth elementals",
			"earth elemental",
			"earth elemental",
			"earth elementals",
			"earth elementals",
			null,
			Nocturnality.DIURNAL,
			"[npc.NameIsFull] a summoned elemental, currently bound to the school of Earth.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 50f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 50f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 50f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Unlimited)] <b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'> self-transformations</b>"),
			"Earth Elementals",
			"Earth Elementals'",
			"ELEMENTAL_EARTH_BASIC",
			"ELEMENTAL_EARTH_ADVANCED",
			Race.ELEMENTAL,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 5)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.SPELL_SCHOOL_EARTH,
			SubspeciesPreference.FOUR_ABUNDANT, "An arcane elemental bound to the school of Earth.", null, Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return ELEMENTAL_FIRE.getSubspeciesOverridePriority();
		}
		@Override
		public void applySpeciesChanges(Body body) {
			body.setBodyMaterial(BodyMaterial.STONE);
		}
		@Override
		public String getSVGString(GameCharacter character) {
			if(character!=null && (character instanceof Elemental) && ((Elemental)character).getSummoner()!=null && !((Elemental)character).getSummoner().isElementalActive()) {
				if(((Elemental)character).getPassiveForm()==null) {
					String wispSVG = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_EARTH),
							this.getColour(character),
							this.getColour(character),
							this.getColour(character),
							"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getRaceWisp()+"</div>");
					return wispSVG;
				}
				AbstractSubspecies passiveForm = ((Elemental)character).getPassiveForm();
				if (passiveForm.SVGString == null) {
					passiveForm.initSVGStrings();
				}
				String raceSvg = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_EARTH),
						this.getColour(character),
						this.getColour(character),
						this.getColour(character),
						passiveForm.SVGStringUncoloured);
				return getBipedBackground(raceSvg, character, this.getColour(character));
			}
			return super.getSVGString(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.ELEMENTAL
					&& (body.getBodyMaterial()==BodyMaterial.STONE || body.getBodyMaterial()==BodyMaterial.RUBBER || body.getBodyMaterial()==BodyMaterial.FLESH || body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		
		@Override
		public boolean isMaterialSubspecies() {
			return true;
		}
		
		@Override
		public BodyMaterial getSubspeciesBodyMaterial() {
			return BodyMaterial.STONE;
		}
	};

	public static AbstractSubspecies ELEMENTAL_WATER = new AbstractSubspecies(false,
			100000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceElemental",
			"statusEffects/race/raceBackgroundWater",
			"water elemental",
			"water elementals",
			"water elemental",
			"water elemental",
			"water elementals",
			"water elementals",
			null,
			Nocturnality.DIURNAL,
			"[npc.NameIsFull] a summoned elemental, currently bound to the school of Water.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15f),
					new Value<>(Attribute.DAMAGE_ICE, 50f),
					new Value<>(Attribute.RESISTANCE_ICE, 50f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Unlimited)] <b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'> self-transformations</b>"),
			"Water Elementals",
			"Water Elementals'",
			"ELEMENTAL_WATER_BASIC",
			"ELEMENTAL_WATER_ADVANCED",
			Race.ELEMENTAL,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 5)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.SPELL_SCHOOL_WATER,
			SubspeciesPreference.FOUR_ABUNDANT, "An arcane elemental bound to the school of Water.", null, Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return ELEMENTAL_FIRE.getSubspeciesOverridePriority();
		}
		@Override
		public void applySpeciesChanges(Body body) {
			body.setBodyMaterial(BodyMaterial.WATER);
		}
		@Override
		public String getSVGString(GameCharacter character) {
			if(character!=null && (character instanceof Elemental) && ((Elemental)character).getSummoner()!=null && !((Elemental)character).getSummoner().isElementalActive()) {
				if(((Elemental)character).getPassiveForm()==null) {
					String wispSVG = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_WATER),
									this.getColour(character),
									this.getColour(character),
									this.getColour(character),
									"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getRaceWisp()+"</div>");
					return wispSVG;
				}
				AbstractSubspecies passiveForm = ((Elemental)character).getPassiveForm();
				if (passiveForm.SVGString == null) {
					passiveForm.initSVGStrings();
				}
				String raceSvg = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_WATER),
						this.getColour(character),
						this.getColour(character),
						this.getColour(character),
						passiveForm.SVGStringUncoloured);
				return getBipedBackground(raceSvg, character, this.getColour(character));
			}
			return super.getSVGString(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.ELEMENTAL && (body.getBodyMaterial()==BodyMaterial.WATER || body.getBodyMaterial()==BodyMaterial.ICE)) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		
		@Override
		public boolean isMaterialSubspecies() {
			return true;
		}
		
		@Override
		public BodyMaterial getSubspeciesBodyMaterial() {
			return BodyMaterial.WATER;
		}
	};

	public static AbstractSubspecies ELEMENTAL_AIR = new AbstractSubspecies(false,
			100000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceElemental",
			"statusEffects/race/raceBackgroundAir",
			"air elemental",
			"air elementals",
			"air elemental",
			"air elemental",
			"air elementals",
			"air elementals",
			null,
			Nocturnality.DIURNAL,
			"[npc.NameIsFull] a summoned elemental, currently bound to the school of Air.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.DAMAGE_POISON, 50f),
					new Value<>(Attribute.RESISTANCE_POISON, 50f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Unlimited)] <b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'> self-transformations</b>"),
			"Air Elementals",
			"Air Elementals'",
			"ELEMENTAL_AIR_BASIC",
			"ELEMENTAL_AIR_ADVANCED",
			Race.ELEMENTAL,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 5)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.SPELL_SCHOOL_AIR,
			SubspeciesPreference.FOUR_ABUNDANT, "An arcane elemental bound to the school of Air.", null, Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return ELEMENTAL_FIRE.getSubspeciesOverridePriority();
		}
		@Override
		public void applySpeciesChanges(Body body) {
			body.setBodyMaterial(BodyMaterial.AIR);
		}
		@Override
		public String getSVGString(GameCharacter character) {
			if(character!=null && (character instanceof Elemental) && ((Elemental)character).getSummoner()!=null && !((Elemental)character).getSummoner().isElementalActive()) {
				if(((Elemental)character).getPassiveForm()==null) {
					String wispSVG = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_AIR),
									this.getColour(character),
									this.getColour(character),
									this.getColour(character),
									"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getRaceWisp()+"</div>");
					return wispSVG;
				}
				AbstractSubspecies passiveForm = ((Elemental)character).getPassiveForm();
				if (passiveForm.SVGString == null) {
					passiveForm.initSVGStrings();
				}
				String raceSvg = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_AIR),
						this.getColour(character),
						this.getColour(character),
						this.getColour(character),
						passiveForm.SVGStringUncoloured);
				return getBipedBackground(raceSvg, character, this.getColour(character));
			}
			return super.getSVGString(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.ELEMENTAL && body.getBodyMaterial()==BodyMaterial.AIR) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		
		@Override
		public boolean isMaterialSubspecies() {
			return true;
		}
		
		@Override
		public BodyMaterial getSubspeciesBodyMaterial() {
			return BodyMaterial.AIR;
		}
	};

	public static AbstractSubspecies ELEMENTAL_ARCANE = new AbstractSubspecies(true,
			100000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceElemental",
			"statusEffects/race/raceBackgroundArcane",
			"arcane elemental",
			"arcane elementals",
			"arcane elemental",
			"arcane elemental",
			"arcane elementals",
			"arcane elementals",
			null,
			Nocturnality.DIURNAL,
			"[npc.NameIsFull] a summoned elemental, currently bound to the school of Arcane.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15f),
					new Value<>(Attribute.DAMAGE_LUST, 50f),
					new Value<>(Attribute.RESISTANCE_LUST, 50f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Unlimited)] <b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'> self-transformations</b>"),
			"Arcane Elementals",
			"Arcane Elementals'",
			"ELEMENTAL_ARCANE_BASIC",
			"ELEMENTAL_ARCANE_ADVANCED",
			Race.ELEMENTAL,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 5)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.SPELL_SCHOOL_ARCANE,
			SubspeciesPreference.FOUR_ABUNDANT, "An arcane elemental bound to the school of Arcane.", null, Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return ELEMENTAL_FIRE.getSubspeciesOverridePriority();
		}
		@Override
		public void applySpeciesChanges(Body body) {
			body.setBodyMaterial(BodyMaterial.ARCANE);
		}
		@Override
		public String getSVGString(GameCharacter character) {
			if(character!=null && (character instanceof Elemental) && ((Elemental)character).getSummoner()!=null && !((Elemental)character).getSummoner().isElementalActive()) {
				if(((Elemental)character).getPassiveForm()==null) {
					String wispSVG = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_ARCANE),
									this.getColour(character),
									this.getColour(character),
									this.getColour(character),
									"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getRaceWisp()+"</div>");
					return wispSVG;
				}
				AbstractSubspecies passiveForm = ((Elemental)character).getPassiveForm();
				if (passiveForm.SVGString == null) {
					passiveForm.initSVGStrings();
				}
				String raceSvg = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_ARCANE),
						this.getColour(character),
						this.getColour(character),
						this.getColour(character),
						passiveForm.SVGStringUncoloured);
				return getBipedBackground(raceSvg, character, this.getColour(character));
			}
			return super.getSVGString(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.ELEMENTAL && body.getBodyMaterial()==BodyMaterial.ARCANE) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		
		@Override
		public boolean isMaterialSubspecies() {
			return true;
		}
		
		@Override
		public BodyMaterial getSubspeciesBodyMaterial() {
			return BodyMaterial.ARCANE;
		}
	};


	public static List<AbstractSubspecies> allSubspecies;
	
	public static Map<AbstractSubspecies, String> subspeciesToIdMap = new HashMap<>();
	public static Map<String, AbstractSubspecies> idToSubspeciesMap = new HashMap<>();

	private static Map<WorldRegion, Map<AbstractSubspecies, SubspeciesSpawnRarity>> regionSpecies;
	private static Map<AbstractWorldType, Map<AbstractSubspecies, SubspeciesSpawnRarity>> worldSpecies;
	private static Map<AbstractPlaceType, Map<AbstractSubspecies, SubspeciesSpawnRarity>> placeSpecies;
	
	protected static Map<AbstractSubspecies, SubspeciesSpawnRarity> dominionStormImmuneSpecies;
	protected static Map<AbstractRace, List<AbstractSubspecies>> subspeciesFromRace;
	
	public static AbstractSubspecies getSubspeciesFromId(String id) {
		if(id.equalsIgnoreCase("CAT_MORPH_LEOPARD_SNOW")) {
			id = "innoxia_panther_subspecies_snow_leopard";
		} else if(id.equalsIgnoreCase("CAT_MORPH_LEOPARD")) {
			id = "innoxia_panther_subspecies_leopard";
		} else if(id.equalsIgnoreCase("CAT_MORPH_LION")) {
			id = "innoxia_panther_subspecies_lion";
		} else if(id.equalsIgnoreCase("CAT_MORPH_TIGER")) {
			id = "innoxia_panther_subspecies_tiger";
		} else if(id.equalsIgnoreCase("HARPY_BALD_EAGLE")) {
			id = "innoxia_raptor_subspecies_bald_eagle";
		}
		id = Util.getClosestStringMatch(id, idToSubspeciesMap.keySet());
		return idToSubspeciesMap.get(id);
	}
	
	public static String getIdFromSubspecies(AbstractSubspecies subspecies) {
		return subspeciesToIdMap.get(subspecies);
	}

	static {
		allSubspecies = new ArrayList<>();

		// Modded subspecies:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "subspecies", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("subspecies")) {
					try {
						AbstractSubspecies subspecies = new AbstractSubspecies(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("_race", "");
						allSubspecies.add(subspecies);
						subspeciesToIdMap.put(subspecies, id);
						idToSubspeciesMap.put(id, subspecies);
//						System.out.println("subspecies: "+id);
					} catch(Exception ex) {
						System.err.println("Loading modded subspecies failed at 'Subspecies'. File path: "+innerEntry.getValue().getAbsolutePath());
						System.err.println("Actual exception: ");
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// External res subspecies:
		
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/race", "subspecies", null);
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("subspecies")) {
					try {
						AbstractSubspecies subspecies = new AbstractSubspecies(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("_race", "");
						allSubspecies.add(subspecies);
						subspeciesToIdMap.put(subspecies, id);
						idToSubspeciesMap.put(id, subspecies);
					} catch(Exception ex) {
						System.err.println("Loading subspecies failed at 'Subspecies'. File path: "+innerEntry.getValue().getAbsolutePath());
						System.err.println("Actual exception: ");
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Hard-coded:
		
		Field[] fields = Subspecies.class.getFields();
		
		for(Field f : fields){
			if (AbstractSubspecies.class.isAssignableFrom(f.getType())) {
				
				AbstractSubspecies subspecies;
				
				try {
					subspecies = ((AbstractSubspecies) f.get(null));

					subspeciesToIdMap.put(subspecies, f.getName());
					idToSubspeciesMap.put(f.getName(), subspecies);
					allSubspecies.add(subspecies);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		worldSpecies = new HashMap<>();
		regionSpecies = new HashMap<>();
		placeSpecies = new HashMap<>();
		dominionStormImmuneSpecies = new HashMap<>();
		subspeciesFromRace = new HashMap<>();
		
		for(AbstractSubspecies species : Subspecies.getAllSubspecies()) {
			subspeciesFromRace.putIfAbsent(species.getRace(), new ArrayList<>());
			subspeciesFromRace.get(species.getRace()).add(species);
			
			for(Entry<WorldRegion, SubspeciesSpawnRarity> type : species.getRegionLocations().entrySet()) {
				regionSpecies.putIfAbsent(type.getKey(), new HashMap<>());
				regionSpecies.get(type.getKey()).put(species, type.getValue());
			}
			
			for(Entry<AbstractWorldType, SubspeciesSpawnRarity> type : species.getWorldLocations().entrySet()) {
				worldSpecies.putIfAbsent(type.getKey(), new HashMap<>());
				worldSpecies.get(type.getKey()).put(species, type.getValue());
				
				try {
					if(type.getKey()==WorldType.DOMINION && species.getRace()==Race.DEMON && species.getStatusEffectAttributeModifiers(null).get(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.TWO_SMART.getMinimumValue()) {
						dominionStormImmuneSpecies.put(species, type.getValue());
					}
				} catch(Exception ex) {	
				}
			}
			
			for(Entry<AbstractPlaceType, SubspeciesSpawnRarity> type : species.getPlaceLocations().entrySet()) {
				placeSpecies.putIfAbsent(type.getKey(), new HashMap<>());
				placeSpecies.get(type.getKey()).put(species, type.getValue());
			}
		}
		
		for(List<AbstractSubspecies> e : subspeciesFromRace.values()) {
			e.sort((s1, s2) -> s1.getName(null).compareTo(s2.getName(null)));
		}

		allSubspecies.sort((s1, s2) -> s1.getRace().getName(false).compareTo(s2.getRace().getName(false)));
	}
	
	public static List<AbstractSubspecies> getAllSubspecies() {
		return allSubspecies;
	}

	public static Map<AbstractSubspecies, SubspeciesSpawnRarity> getWorldSpecies(AbstractWorldType worldType, AbstractPlaceType placeType, boolean onlyCoreRaceSpecies, AbstractSubspecies... subspeciesToExclude) {
		return getWorldSpecies(worldType, placeType, onlyCoreRaceSpecies, true, subspeciesToExclude);
	}

	public static Map<AbstractSubspecies, SubspeciesSpawnRarity> getWorldSpecies(AbstractWorldType worldType, AbstractPlaceType placeType, boolean onlyCoreRaceSpecies, boolean includeRegionSpecies, AbstractSubspecies... subspeciesToExclude) {
		return getWorldSpecies(worldType, placeType, onlyCoreRaceSpecies, includeRegionSpecies, Arrays.asList(subspeciesToExclude));
	}
	
	/**
	 * @param worldType The WorldType from which to fetch Subspecies present.
	 * @param placeType The PlaceType from which to fetch Subspecies present. Can be passed in as null to ignore.
	 * @param onlyCoreRaceSpecies true if only core Subspecies should be returned. (e.g. Cat-morph would be returned, but not Lion-morph, Tiger-morph, etc.)
	 * @param includeRegionSpecies true if the species of the WorldRegion should be included.
	 * @param subspeciesToExclude Any Subspecies that should be excluded from the returned map.
	 * @return A weighted map of subspecies that can spawn in that world, region and/or place.
	 */
	public static Map<AbstractSubspecies, SubspeciesSpawnRarity> getWorldSpecies(AbstractWorldType worldType, AbstractPlaceType placeType, boolean onlyCoreRaceSpecies, boolean includeRegionSpecies, List<AbstractSubspecies> subspeciesToExclude) {
		worldSpecies.putIfAbsent(worldType, new HashMap<>());
		regionSpecies.putIfAbsent(worldType.getWorldRegion(), new HashMap<>());
		
		Map<AbstractSubspecies, SubspeciesSpawnRarity> map = new HashMap<>(worldSpecies.get(worldType));
		if (includeRegionSpecies) {
			for(Entry<AbstractSubspecies, SubspeciesSpawnRarity> regionEntry : regionSpecies.get(worldType.getWorldRegion()).entrySet()) {
				if(!map.containsKey(regionEntry.getKey())) {
					map.put(regionEntry.getKey(), regionEntry.getValue());
				}
			}
		}
		if(placeType!=null) {
			placeSpecies.putIfAbsent(placeType, new HashMap<>());
			regionSpecies.putIfAbsent(placeType.getWorldRegion(), new HashMap<>());
		    for(Entry<AbstractSubspecies, SubspeciesSpawnRarity> placeEntry : placeSpecies.get(placeType).entrySet()) {
			if(!map.containsKey(placeEntry.getKey())) {
			    map.put(placeEntry.getKey(), placeEntry.getValue());
			}
		    }
			if (includeRegionSpecies && regionSpecies.get(placeType.getWorldRegion())!=null) {
			    for(Entry<AbstractSubspecies, SubspeciesSpawnRarity> regionEntry : regionSpecies.get(placeType.getWorldRegion()).entrySet()) {
				if(!map.containsKey(regionEntry.getKey())) {
				    map.put(regionEntry.getKey(), regionEntry.getValue());
				}
			    }
			}
		}
		
		Map<AbstractSubspecies, SubspeciesSpawnRarity> filteredMap = new HashMap<>(map);
		if(onlyCoreRaceSpecies) {
			for(AbstractSubspecies sub : map.keySet()) {
				if(AbstractSubspecies.getMainSubspeciesOfRace(sub.getRace())!=sub) {
					filteredMap.remove(sub);
				}
			}
		}
		
		for(AbstractSubspecies sub : subspeciesToExclude) {
			filteredMap.remove(sub);
		}
		
		return filteredMap;
	}

	/**
	 * @param onlyCoreRaceSpecies true if only core Subspecies should be returned. (e.g. Cat-morph would be returned, but not Lion-morph, Tiger-morph, etc.)
	 * @param subspeciesToExclude Any Subspecies that should be excluded from the returned map.
	 */
	public static Map<AbstractSubspecies, SubspeciesSpawnRarity> getDominionStormImmuneSpecies(boolean onlyCoreRaceSpecies, AbstractSubspecies... subspeciesToExclude) {
		Map<AbstractSubspecies, SubspeciesSpawnRarity> map = new HashMap<>(dominionStormImmuneSpecies);
		
		if(onlyCoreRaceSpecies) {
			for(AbstractSubspecies sub : dominionStormImmuneSpecies.keySet()) {
				if(AbstractSubspecies.getMainSubspeciesOfRace(sub.getRace())!=sub) {
					map.remove(sub);
				}
			}
		}
		
		for(AbstractSubspecies sub : subspeciesToExclude) {
			map.remove(sub);
		}
		
		return map;
	}

	public static List<AbstractSubspecies> getSubspeciesOfRace(AbstractRace race) {
		return subspeciesFromRace.get(race);
	}
}