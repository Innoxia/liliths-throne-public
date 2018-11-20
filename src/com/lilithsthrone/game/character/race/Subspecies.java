package com.lilithsthrone.game.character.race;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.AttributeRange;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.1.91
 * @version 0.2.11
 * @author tukaima, Innoxia
 */
public enum Subspecies {

	// HUMAN:
	HUMAN("statusEffects/race/raceHuman",
			"statusEffects/race/raceBackground",
			"human",
			"humans",
			"man",
			"woman",
			"men",
			"women",
			"Humans have a much higher resistance to the arousing effects of the arcane than any other race.",
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, 20f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "HUMAN_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "HUMAN_ADVANCED"),
			Race.HUMAN,
			Colour.RACE_HUMAN,
			SubspeciesPreference.FOUR_ABUNDANT, "A typical human.", Util.newArrayListOfValues(
					WorldType.DOMINION,
					WorldType.NIGHTLIFE_CLUB)),

	// ANGEL:
	ANGEL("statusEffects/race/raceAngel",
			"statusEffects/race/raceBackground",
			"angel",
			"angels",
			"angel",
			"angel",
			"angel",
			"angel",
			"As an angel, [npc.nameIsFull] highly resistant to the arousing effects of the arcane, and [npc.is] particularly adept at fighting demons."
					+ " [npc.Her] natural instinct to protect humans, however, leaves [npc.her] quite vulnerable to them...",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, 50f),
					new Value<Attribute, Float>(Attribute.DAMAGE_DEMON, 50f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_DEMON, 50f),
					new Value<Attribute, Float>(Attribute.DAMAGE_HUMAN, -50f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_HUMAN, -50f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "ANGEL_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "ANGEL_ADVANCED"),
			Race.ANGEL,
			Colour.RACE_ANGEL, SubspeciesPreference.FOUR_ABUNDANT, "A typical angel.", Util.newArrayListOfValues()),

	// DEMON:
	DEMON("statusEffects/race/raceDemon",
			"statusEffects/race/raceBackground",
			"demon",
			"demons",
			"incubus",
			"succubus",
			"incubi",
			"succubi",
			"Due to the fact that demons are very easily able to harness arcane power, [npc.namePos] spell-casting abilities are truly a terrifying force to behold!",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_CORRUPTION, 25f),
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, 25f),
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 25f),
					new Value<Attribute, Float>(Attribute.DAMAGE_SPELLS, 75f)),
			Util.newArrayListOfValues("<b style='color: "+ Colour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'>Can morph body at will</b>"),
			UtilText.parseFromXMLFile("characters/raceInfo", "DEMON_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "DEMON_ADVANCED"),
			Race.DEMON,
			Colour.RACE_DEMON, SubspeciesPreference.FOUR_ABUNDANT, "A typical demon.", Util.newArrayListOfValues(
					WorldType.DOMINION,
					WorldType.NIGHTLIFE_CLUB)) {
		
		@Override
		public void applySpeciesChanges(Body body) {
			if(Math.random()<0.25f) {
				if(body.getLeg().getType()==LegType.DEMON_COMMON) {
					body.getLeg().setType(null, LegType.DEMON_HOOFED);
				}
			}
		}
		
		@Override
		public Subspecies getOffspringSubspecies() {
			return IMP;
		}
	},
	
	IMP("statusEffects/race/raceImp",
			"statusEffects/race/raceBackground",
			"imp",
			"imps",
			"imp",
			"imp",
			"imps",
			"imps",
			"[npc.NamePos] impish body has a deep, insatiable craving for sex...",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_CORRUPTION, 50f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues("<b style='color: "+ Colour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'>Limited body-morphing abilities</b>"),
			UtilText.parseFromXMLFile("characters/raceInfo", "IMP_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "IMP_ADVANCED"),
			Race.DEMON,
			Util.newHashMapOfValues(
					new Value<Attribute, AttributeRange>(Attribute.MAJOR_PHYSIQUE, new AttributeRange(5f, 10f)),
					new Value<Attribute, AttributeRange>(Attribute.MAJOR_ARCANE, new AttributeRange(10f, 20f)),
					new Value<Attribute, AttributeRange>(Attribute.MAJOR_CORRUPTION, new AttributeRange(95f, 100f))),
			Colour.RACE_IMP,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical imp.",
			Util.newArrayListOfValues(WorldType.SUBMISSION)) {
		@Override
		public void applySpeciesChanges(Body body) {
			body.setHeight(Height.NEGATIVE_TWO_MIMIMUM.getMinimumValue() + Util.random.nextInt(Height.NEGATIVE_TWO_MIMIMUM.getMaximumValue() - Height.NEGATIVE_TWO_MIMIMUM.getMinimumValue()));
			body.getPenis().setPenisSize(null, 3+Util.random.nextInt(6)); // 3-8 inches
			body.getWing().setSize(null,  WingSize.THREE_LARGE.getValue());
		}
		@Override
		public boolean isShortStature() {
			return true;
		}
	},
	
	IMP_ALPHA("statusEffects/race/raceImpAlpha",
			"statusEffects/race/raceBackground",
			"alpha-imp",
			"alpha-imps",
			"alpha-imp",
			"alpha-imp",
			"alpha-imps",
			"alpha-imps",
			"[npc.NamePos] impish body has a deep, insatiable craving for sex...",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_CORRUPTION, 75f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -50f)),
			Util.newArrayListOfValues("<b style='color: "+ Colour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'>Limited body-morphing abilities</b>"),
			UtilText.parseFromXMLFile("characters/raceInfo", "IMP_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "IMP_ADVANCED"),
			Race.DEMON,
			Util.newHashMapOfValues(
					new Value<Attribute, AttributeRange>(Attribute.MAJOR_PHYSIQUE, new AttributeRange(10f, 25f)),
					new Value<Attribute, AttributeRange>(Attribute.MAJOR_ARCANE, new AttributeRange(15f, 30f)),
					new Value<Attribute, AttributeRange>(Attribute.MAJOR_CORRUPTION, new AttributeRange(95f, 100f))),
			Colour.RACE_IMP,
			SubspeciesPreference.ONE_LOW,
			"A more powerful form of imp, standing at over 3'6\" tall.",
			Util.newArrayListOfValues(WorldType.SUBMISSION)) {
		@Override
		public Subspecies getOffspringSubspecies() {
			return IMP;
		}
		@Override
		public void applySpeciesChanges(Body body) {
			body.setHeight(Height.NEGATIVE_ONE_TINY.getMinimumValue() + Util.random.nextInt(Height.NEGATIVE_ONE_TINY.getMaximumValue() - Height.NEGATIVE_ONE_TINY.getMinimumValue()));
			body.getPenis().setPenisSize(null, 6+Util.random.nextInt(5)); // 6-10 inches
			body.getWing().setSize(null,  WingSize.THREE_LARGE.getValue());
		}
		@Override
		public boolean isShortStature() {
			return true;
		}
	},
	
	// BOVINES:
	COW_MORPH("statusEffects/race/raceCowMorph",
			"statusEffects/race/raceBackground",
			"cow-morph",
			"cow-morphs",
			"cow-boy",
			"cow-girl",
			"cow-boys",
			"cow-girls",
			"Although [npc.namePos] body possesses a great strength and toughness, [npc.her] mind isn't exactly the quickest...",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, -5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 10f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "COW_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "COW_MORPH_ADVANCED"),
			Race.COW_MORPH,
			Colour.RACE_COW_MORPH, SubspeciesPreference.FOUR_ABUNDANT, "A typical bipedal cow-morph.", Util.newArrayListOfValues(
					WorldType.DOMINION,
					WorldType.NIGHTLIFE_CLUB)),
	
	// CANIDS:
	DOG_MORPH("statusEffects/race/raceDogMorph",
			"statusEffects/race/raceBackground",
			"dog-morph",
			"dog-morphs",
			"dog-boy",
			"dog-girl",
			"dog-boys",
			"dog-girls",
			"[npc.Name] always [npc.has] lots of energy, and [npc.she] [npc.verb(get)] excited about new things very easily."
					+ " [npc.She] also [npc.has] an instinctive desire to display [npc.her] dominance over innocent cat-morphs...",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_CAT_MORPH, 5f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "DOG_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "DOG_MORPH_ADVANCED"),
			Race.DOG_MORPH,
			Colour.RACE_DOG_MORPH, SubspeciesPreference.FOUR_ABUNDANT, "A typical bipedal dog-morph.", Util.newArrayListOfValues(
					WorldType.DOMINION,
					WorldType.NIGHTLIFE_CLUB)) {
				@Override
				public void applySpeciesChanges(Body body) {
					if(body.getPenis().getType()==PenisType.CANINE) {
						body.getCoverings().put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, Colour.SKIN_RED));
					}
				}
			},
	
	DOG_MORPH_BORDER_COLLIE("statusEffects/race/raceDogMorph",
			"statusEffects/race/raceBackground",
			"border-collie-morph",
			"border-collie-morphs",
			"border-collie-boy",
			"border-collie-girl",
			"border-collie-boys",
			"border-collie-girls",
			"[npc.NameIsFull] more intelligent than an average dog-morph, and [npc.has] strong urges to try and herd people around."
					+ " [npc.She] also [npc.has] an instinctive desire to display [npc.her] dominance over innocent sheep-morphs...",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, 5f),
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 5f)), //TODO sheep=morph damage
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "DOG_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "DOG_MORPH_ADVANCED"),
			Race.DOG_MORPH,
			Colour.RACE_DOG_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A particularly energetic and intelligent dog-morph which resembles an anthropomorphised border collie."
											+ " To be identified as a border-collie-morph, a character must be a dog-morph that has either upright or folder ears, and fluffy, black fur with white markings.",
			Util.newArrayListOfValues(
					WorldType.DOMINION,
					WorldType.NIGHTLIFE_CLUB)) {
				@Override
				public void applySpeciesChanges(Body body) {
					if(body.getPenis().getType()==PenisType.CANINE) {
						body.getCoverings().put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, Colour.SKIN_RED));
					}
					body.getCoverings().put(BodyCoveringType.CANINE_FUR, new Covering(BodyCoveringType.CANINE_FUR, CoveringPattern.MARKED, CoveringModifier.FLUFFY, Colour.COVERING_BLACK, false, Colour.COVERING_WHITE, false));
					if(body.getEar().getType()==EarType.DOG_MORPH) {
						if(Math.random()<0.5f) {
							body.getEar().setType(null, EarType.DOG_MORPH_POINTED);
						} else {
							body.getEar().setType(null, EarType.DOG_MORPH_FOLDED);
						}
					}
				}
			},
	
	DOG_MORPH_DOBERMANN("statusEffects/race/raceDogMorphDobermann",
			"statusEffects/race/raceBackground",
			"dobermann",
			"dobermanns",
			"dobermann",
			"dobermann",
			"dobermanns",
			"dobermanns",
			"[npc.NameIsFull] always ready to defend those [npc.she] [npc.verb(call)] [npc.her] friend, and, thanks to [npc.her] powerful dobermann's body, [npc.sheIs] able to do just that."
					+ " [npc.She] also [npc.has] an instinctive desire to display [npc.her] dominance over innocent cat-morphs...",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 15f),
					new Value<Attribute, Float>(Attribute.DAMAGE_CAT_MORPH, 5f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "DOG_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "DOG_MORPH_ADVANCED"),
			Race.DOG_MORPH,
			Colour.RACE_DOG_MORPH,
			SubspeciesPreference.TWO_AVERAGE,
			"A dog-morph which resembles an anthropomorphised dobermann. To be identified as a dobermann, a character must be a dog-morph that has short, black fur, with either brown, dark-brown, or tan markings.",
			Util.newArrayListOfValues(
					WorldType.DOMINION,
					WorldType.NIGHTLIFE_CLUB)) {
		@Override
		public void applySpeciesChanges(Body body) {
			if(body.getPenis().getType()==PenisType.CANINE) {
				body.getCoverings().put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, Colour.SKIN_RED));
			}
			Colour secondaryColour = Colour.COVERING_BROWN;
			double rand = Math.random();
			if(rand<0.3f) {
				secondaryColour = Colour.COVERING_TAN;
			} else if(rand<0.6f) {
				secondaryColour = Colour.COVERING_BROWN_DARK;
			}
			body.getCoverings().put(BodyCoveringType.CANINE_FUR, new Covering(BodyCoveringType.CANINE_FUR, CoveringPattern.MARKED, CoveringModifier.SHORT, Colour.COVERING_BLACK, false, secondaryColour, false));
			body.getCoverings().put(BodyCoveringType.HAIR_CANINE_FUR, new Covering(BodyCoveringType.HAIR_CANINE_FUR, CoveringPattern.NONE, Colour.COVERING_BLACK, false, secondaryColour, false));
			body.getCoverings().put(BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, Colour.SKIN_EBONY, false, Colour.SKIN_EBONY, false));
			body.updateCoverings(true, true, true, true);
			if(body.getEar().getType()==EarType.DOG_MORPH) {
				body.getEar().setType(null, EarType.DOG_MORPH_POINTED);
			}
			if(body.getTail().getType()==TailType.DOG_MORPH) {
				body.getTail().setType(null, TailType.DOG_MORPH_STUBBY);
			}
		}
	},
	
	WOLF_MORPH("statusEffects/race/raceWolfMorph",
			"statusEffects/race/raceBackground",
			"wolf-morph",
			"wolf-morphs",
			"wolf-boy",
			"wolf-girl",
			"wolf-boys",
			"wolf-girls",
			"[npc.NamePos] wolf-like body is very strong, and [npc.she] often [npc.verb(get)] powerful urges to try and dominate people [npc.she] [npc.verb(meet)].",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<Attribute, Float>(Attribute.DAMAGE_UNARMED, 25f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "WOLF_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "WOLF_MORPH_ADVANCED"),
			Race.WOLF_MORPH,
			Colour.RACE_WOLF_MORPH, SubspeciesPreference.FOUR_ABUNDANT, "A typical bipedal wolf-morph.", Util.newArrayListOfValues(
					WorldType.DOMINION,
					WorldType.NIGHTLIFE_CLUB)) {
		@Override
		public void applySpeciesChanges(Body body) {
			if(body.getPenis().getType()==PenisType.LUPINE) {
				body.getCoverings().put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, Colour.SKIN_RED));
			}
		}
	},
	
	FOX_MORPH("statusEffects/race/raceFoxMorph",
			"statusEffects/race/raceBackground",
			"fox-morph",
			"fox-morphs",
			"fox-boy",
			"fox-girl",
			"fox-boys",
			"fox-girls",
			"[npc.NameIsFull] very sly and nimble, and [npc.sheIs] able to use [npc.her] heightened senses to detect opportune moments in which to attack.",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 5f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 20f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "FOX_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "FOX_MORPH_ADVANCED"),
			Race.FOX_MORPH,
			Colour.RACE_FOX_MORPH, SubspeciesPreference.FOUR_ABUNDANT, "A typical bipedal fox-morph.", Util.newArrayListOfValues(
					WorldType.DOMINION,
					WorldType.NIGHTLIFE_CLUB)) {
		@Override
		public void applySpeciesChanges(Body body) {
			Subspecies.applyFoxColoring(body);
			if(body.getPenis().getType()==PenisType.VULPINE) {
				body.getCoverings().put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, Colour.SKIN_RED));
			}
		}
	},
	
	FOX_MORPH_FENNEC("statusEffects/race/raceFoxMorph",
			"statusEffects/race/raceBackground",
			"fennec-morph",
			"fennec-morphs",
			"fennec-boy",
			"fennec-girl",
			"fennec-boys",
			"fennec-girls",
			"[npc.NameIsFull] very sly and nimble, and [npc.sheIs] able to use [npc.her] heightened senses to detect opportune moments in which to attack.",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 5f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 20f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "FOX_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "FOX_MORPH_ADVANCED"),
			Race.FOX_MORPH,
			Colour.RACE_FOX_MORPH, SubspeciesPreference.FOUR_ABUNDANT, "A bipedal fox-morph with tan or bleach blonde fur and distinctive large ears.", Util.newArrayListOfValues(
					WorldType.DOMINION,
					WorldType.NIGHTLIFE_CLUB)) {
		@Override
		public void applySpeciesChanges(Body body) {
			Colour fennecColour = Colour.COVERING_BLEACH_BLONDE;
			double rand = Math.random();
			if(rand<0.5f) {
				fennecColour = Colour.COVERING_DIRTY_BLONDE;
			}
			body.getCoverings().put(BodyCoveringType.FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, fennecColour, false, fennecColour, false));
			body.getCoverings().put(BodyCoveringType.HAIR_FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, fennecColour, false, fennecColour, false));
			body.getCoverings().put(BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, Colour.SKIN_OLIVE, false, Colour.SKIN_OLIVE, false));
			body.updateCoverings(true, true, true, true);
			if(body.getEar().getType()==EarType.FOX_MORPH) {
				body.getEar().setType(null, EarType.FOX_MORPH_BIG);
			}
			if(body.getPenis().getType()==PenisType.VULPINE) {
				body.getCoverings().put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, Colour.SKIN_RED));
			}
		}
	},
	
	FOX_ASCENDANT("statusEffects/race/raceFoxMorph",
			"statusEffects/race/raceBackground",
			"youko",
			"youkos",
			"youko-boy",
			"youko-girl",
			"youko-boys",
			"youko-girls",
			"",
			null,
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "FOX_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "FOX_MORPH_ADVANCED"),
			Race.FOX_MORPH,
			Colour.RACE_FOX_MORPH, SubspeciesPreference.FOUR_ABUNDANT, "A fox-morph, empowered by the gifts of a Lilin.", Util.newArrayListOfValues(WorldType.DOMINION)) {
		
		@Override
		public void applySpeciesChanges(Body body) {
			Subspecies.applyFoxColoring(body);
			if(body.getTail().getType()==TailType.FOX_MORPH) {
				body.getTail().setType(null, TailType.FOX_MORPH_MAGIC);
			}
			if(body.getPenis().getType()==PenisType.VULPINE) {
				body.getCoverings().put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, Colour.SKIN_RED));
			}
		}
		
		@Override
		public String getStatusEffectDescription(GameCharacter character) {
			if(character.getTailCount()<9) {
				return UtilText.parse(character, "[npc.NameIsFull] fox-morph, [npc.his] service to a particular Lilin having afforded [npc.him] [npc.tailCount] magical tail"+(character.getTailCount()==1?"":"s")+".");
			} else {
				return UtilText.parse(character, "[npc.NameIsFull] fox-morph, [npc.his] vast number of tails a sign of [npc.her] unending devotion to a particular Lilin.");
			}
		}

		@Override
		public Map<Attribute, Float> getStatusEffectAttributeModifiers(GameCharacter character) {
			if(character.getTailCount()<9) {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, (float) (10 + 5*character.getTailCount())),
						new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, (float) (20 + 5*character.getTailCount())),
						new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, (float) (10*character.getTailCount())));
			} else {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 25f),
						new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 100f),
						new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 100f));
			}
		}

		@Override
		public String getSVGString(GameCharacter character) {
			switch(character.getTailCount()) {
				case 1:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTail1();
				case 2:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTail2();
				case 3:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTail3();
				case 4:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTail4();
				case 5:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTail5();
				case 6:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTail6();
				case 7:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTail7();
				case 8:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTail8();
				case 9:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTail9();
			}
			return "";
		}

		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			switch(character.getTailCount()) {
				case 1:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTailDesaturated1();
				case 2:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTailDesaturated2();
				case 3:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTailDesaturated3();
				case 4:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTailDesaturated4();
				case 5:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTailDesaturated5();
				case 6:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTailDesaturated6();
				case 7:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTailDesaturated7();
				case 8:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTailDesaturated8();
				case 9:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTailDesaturated9();
			}
			return "";
		}
	},
	
	FOX_ASCENDANT_FENNEC("statusEffects/race/raceFoxMorph",
			"statusEffects/race/raceBackground",
			"fennec-youko",
			"fennec-youko",
			"fennec-youko-boy",
			"fennec-youko-girl",
			"fennec-youko-boys",
			"fennec-youko-girls",
			"",
			null,
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "FOX_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "FOX_MORPH_ADVANCED"),
			Race.FOX_MORPH,
			Colour.RACE_FOX_MORPH, SubspeciesPreference.FOUR_ABUNDANT, "A fennec-morph, empowered by the gifts of a Lilin.", Util.newArrayListOfValues(WorldType.DOMINION)) {
		@Override
		public void applySpeciesChanges(Body body) {
			Colour fennecColour = Colour.COVERING_BLEACH_BLONDE;
			double rand = Math.random();
			if(rand<0.5f) {
				fennecColour = Colour.COVERING_DIRTY_BLONDE;
			}
			body.getCoverings().put(BodyCoveringType.FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, fennecColour, false, fennecColour, false));
			body.getCoverings().put(BodyCoveringType.HAIR_FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, fennecColour, false, fennecColour, false));
			body.getCoverings().put(BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, Colour.SKIN_OLIVE, false, Colour.SKIN_OLIVE, false));
			body.updateCoverings(true, true, true, true);
			if(body.getEar().getType()==EarType.FOX_MORPH) {
				body.getEar().setType(null, EarType.FOX_MORPH_BIG);
			}
			if(body.getTail().getType()==TailType.FOX_MORPH) {
				body.getTail().setType(null, TailType.FOX_MORPH_MAGIC);
			}
			if(body.getPenis().getType()==PenisType.VULPINE) {
				body.getCoverings().put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, Colour.SKIN_RED));
			}
		}
		
		@Override
		public String getStatusEffectDescription(GameCharacter character) {
			if(character.getTailCount()<9) {
				return UtilText.parse(character, "[npc.NameIsFull] fox-morph, [npc.his] service to a particular Lilin having afforded [npc.him] [npc.tailCount] magical tail"+(character.getTailCount()==1?"":"s")+".");
			} else {
				return UtilText.parse(character, "[npc.NameIsFull] fox-morph, [npc.his] vast number of tails a sign of [npc.her] unending devotion to a particular Lilin.");
			}
		}

		@Override
		public Map<Attribute, Float> getStatusEffectAttributeModifiers(GameCharacter character) {
			if(character.getTailCount()<9) {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, (float) (10 + 5*character.getTailCount())),
						new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, (float) (20 + 5*character.getTailCount())),
						new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, (float) (10*character.getTailCount())));
			} else {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 25f),
						new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 100f),
						new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 100f));
			}
		}
		
		@Override
		public String getSVGString(GameCharacter character) {
			switch(character.getTailCount()) {
				case 1:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTail1();
				case 2:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTail2();
				case 3:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTail3();
				case 4:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTail4();
				case 5:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTail5();
				case 6:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTail6();
				case 7:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTail7();
				case 8:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTail8();
				case 9:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTail9();
			}
			return "";
		}

		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			switch(character.getTailCount()) {
				case 1:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTailDesaturated1();
				case 2:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTailDesaturated2();
				case 3:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTailDesaturated3();
				case 4:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTailDesaturated4();
				case 5:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTailDesaturated5();
				case 6:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTailDesaturated6();
				case 7:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTailDesaturated7();
				case 8:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTailDesaturated8();
				case 9:
					return SVGImages.SVG_IMAGE_PROVIDER.getFoxTailDesaturated9();
			}
			return "";
		}
	},
	
//	FOX_TAILED("statusEffects/race/raceFoxMorph",
//			"pipefox",
//			"pipefoxes",
//			"pipefox-boy",
//			"pipefox-girl",
//			"pipefox-boys",
//			"pipefox-girls",
//			Race.FOX_MORPH,
//			Colour.RACE_FOX_MORPH,
//			SubspeciesPreference.FOUR_ABUNDANT,
//			"A fox-morph with a serpentine lower body, devoid of legs.",
//			Util.newArrayListOfValues(WorldType.DOMINION)) {
//		@Override
//		public void applySpeciesChanges(Body body) {
//			//apply fox coloring
//		}
//	},
	
//	FOX_TAUR("statusEffects/race/raceFoxMorph",
//			"yegan",
//			"yegans",
//			"yegan-boy",
//			"yegan-girl",
//			"yegan-boys",
//			"yegan-girls",
//			Race.FOX_MORPH,
//			Colour.RACE_FOX_MORPH,
//			SubspeciesPreference.FOUR_ABUNDANT,
//			"A fox-morph a bestial lower body that walks on four legs.",
//			Util.newArrayListOfValues(WorldType.DOMINION)) {
//		@Override
//		public void applySpeciesChanges(Body body) {
//			//apply fox coloring
//		}
//	},

	// FELINES:
	CAT_MORPH("statusEffects/race/raceCatMorph",
			"statusEffects/race/raceBackground",
			"cat-morph",
			"cat-morphs",
			"cat-boy",
			"cat-girl",
			"cat-boys",
			"cat-girls",
			"[npc.NamePos] body is incredibly agile, and [npc.she] [npc.verb(possess)] lightning reflexes."
					+ " [npc.She] also [npc.has] an instinctive desire to display [npc.her] dominance over innocent harpies and rodent-morphs...",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 10f),
					new Value<Attribute, Float>(Attribute.DAMAGE_HARPY, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_RAT_MORPH, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_SQUIRREL_MORPH, 5f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "CAT_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "CAT_MORPH_ADVANCED"),
			Race.CAT_MORPH,
			Colour.RACE_CAT_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical bipedal cat-morph.",
			Util.newArrayListOfValues(
					WorldType.DOMINION,
					WorldType.NIGHTLIFE_CLUB)) {
		@Override
		public void applySpeciesChanges(Body body) {
			// TODO Auto-generated method stub
		}
	},
	
	CAT_MORPH_LYNX("statusEffects/race/raceCatMorph",
			"statusEffects/race/raceBackground",
			"lynx-morph",
			"lynx-morphs",
			"lynx",
			"lynx",
			"lynxes",
			"lynxes",
			"[npc.NamePos] body is incredibly agile, and [npc.she] [npc.verb(possess)] lightning reflexes."
					+ " [npc.She] also [npc.has] an instinctive desire to display [npc.her] dominance over innocent harpies and rodent-morphs...",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 10f),
					new Value<Attribute, Float>(Attribute.DAMAGE_HARPY, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_HARPY, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_RAT_MORPH, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_SQUIRREL_MORPH, 5f)),//TODO sheep
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "CAT_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "CAT_MORPH_ADVANCED"),
			Race.CAT_MORPH,
			Colour.RACE_CAT_MORPH_LYNX,
			SubspeciesPreference.TWO_AVERAGE,
			"A cat-morph which resembles an anthropomorphised lynx. To be identified as a Lynx-morph, a character must be a cat-morph that has fluffy fur, tufted ears, a short tail, and side-fluff hair type.",
			Util.newArrayListOfValues(
					WorldType.DOMINION,
					WorldType.NIGHTLIFE_CLUB)) {
		@Override
		public void applySpeciesChanges(Body body) {
			Colour primaryColor = Colour.COVERING_BROWN;
			double rand = Math.random();
			if(rand<0.3f) {
				primaryColor = Colour.COVERING_TAN;
			} else if(rand<0.6f) {
				primaryColor = Colour.COVERING_BROWN_DARK;
			}
			body.getCoverings().put(BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.SPOTTED, CoveringModifier.FLUFFY, primaryColor, false, Colour.COVERING_BLACK, false));
			body.getCoverings().put(BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, Colour.COVERING_BLACK, false));
			body.updateCoverings(true, true, true, true);
			body.getEar().setType(null, EarType.CAT_MORPH_TUFTED);
			body.getTail().setType(null, TailType.CAT_MORPH_SHORT);
			body.getHair().setType(null, HairType.CAT_MORPH_SIDEFLUFF);
		}
	},
	
	CAT_MORPH_LEOPARD_SNOW("statusEffects/race/raceCatMorph",
			"statusEffects/race/raceBackgroundSnowLeopard",
			"snow leopard-morph",
			"snow leopard-morphs",
			"snow leopard",
			"snow leopardess",
			"snow leopards",
			"snow leopardesses",
			"[npc.NamePos] body is very strong and agile, and [npc.sheIsFull] capable of great feats of strength and stealth alike."
					+ " [npc.She] also [npc.has] a very high resistance to both natural and arcane cold.",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 15f),
					new Value<Attribute, Float>(Attribute.DAMAGE_UNARMED, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, 50f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "CAT_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "CAT_MORPH_ADVANCED"),
			Race.CAT_MORPH,
			Colour.RACE_CAT_MORPH_LEOPARD_SNOW,
			SubspeciesPreference.TWO_AVERAGE,
			"A cat-morph which resembles an anthropomorphised snow leopard. To be identified as a snow leopard-morph, a character must be a cat-morph that has fluffy spotted fur, normal tail and panther face.",
			Util.newArrayListOfValues(
					WorldType.DOMINION,
					WorldType.NIGHTLIFE_CLUB)) {
		@Override
		public void applySpeciesChanges(Body body) {
			Colour primaryColor = Colour.COVERING_WHITE;
			Colour secondaryColor = Colour.COVERING_BLACK;
			double rand = Math.random();
			if(rand<0.3f) {
				primaryColor = Colour.COVERING_WHITE;
			} else if(rand<0.6f) {
				primaryColor = Colour.COVERING_GREY;
			} else if(rand<0.65f) {
				primaryColor = Colour.COVERING_BLACK;
			}
			body.getCoverings().put(BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.SPOTTED, CoveringModifier.FLUFFY, primaryColor, false, secondaryColor, false));
			body.getCoverings().put(BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
			body.updateCoverings(true, true, true, true);
			if(body.getFace().getType()==FaceType.CAT_MORPH) {
				body.getFace().setType(null, FaceType.CAT_MORPH_PANTHER);
			}
			body.getTail().setType(null, TailType.CAT_MORPH);
			
			body.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		}
	},
	
	CAT_MORPH_LEOPARD("statusEffects/race/raceCatMorph",
			"statusEffects/race/raceBackgroundLeopard",
			"leopard-morph",
			"leopard-morphs",
			"leopard",
			"leopardess",
			"leopard",
			"leopardesses",
			"[npc.NamePos] body is very strong and agile, and [npc.sheIsFull] capable of great feats of strength and stealth alike."
					+ " [npc.She] also [npc.has] a high resistance to both natural and arcane heat.",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 15f),
					new Value<Attribute, Float>(Attribute.DAMAGE_UNARMED, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_FIRE, 25f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "CAT_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "CAT_MORPH_ADVANCED"),
			Race.CAT_MORPH,
			Colour.RACE_CAT_MORPH_LEOPARD,
			SubspeciesPreference.TWO_AVERAGE,
			"A cat-morph which resembles an anthropomorphised leopard. To be identified as a leopard-morph, a character must be a cat-morph that has short spotted fur, normal tail and panther face.",
			Util.newArrayListOfValues(
					WorldType.DOMINION,
					WorldType.NIGHTLIFE_CLUB)) {
		@Override
		public void applySpeciesChanges(Body body) {
			Colour primaryColor = Colour.COVERING_ORANGE;
			Colour secondaryColor = Colour.COVERING_BLACK;
			double rand = Math.random();
			if(rand<0.05f) {
				primaryColor = Colour.COVERING_BLACK;
			}
			body.getCoverings().put(BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.SPOTTED, CoveringModifier.SHORT, primaryColor, false, secondaryColor, false));
			body.getCoverings().put(BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
			body.updateCoverings(true, true, true, true);
			if(body.getFace().getType()==FaceType.CAT_MORPH) {
				body.getFace().setType(null, FaceType.CAT_MORPH_PANTHER);
			}
			body.getTail().setType(null, TailType.CAT_MORPH);
			
			body.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		}
	},
	
	CAT_MORPH_LION("statusEffects/race/raceCatMorphLion",
			"statusEffects/race/raceBackground",
			"lion-morph",
			"lion-morphs",
			"lion",
			"lioness",
			"lions",
			"lionesses",
			"[npc.NamePos] body is extremely strong, and [npc.sheIsFull] capable of great feats of strength."
					+ " [npc.She] also [npc.has] a high resistance to both natural and arcane heat.",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_UNARMED, 15f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 15f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_FIRE, 25f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "CAT_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "CAT_MORPH_ADVANCED"),
			Race.CAT_MORPH,
			Colour.RACE_CAT_MORPH_LION,
			SubspeciesPreference.TWO_AVERAGE,
			"A cat-morph which resembles an anthropomorphised lion. To be identified as a lion-morph, a character must be a cat-morph that has short fur, tufted tail and panther face.",
			Util.newArrayListOfValues(
					WorldType.DOMINION,
					WorldType.NIGHTLIFE_CLUB)) {
		@Override
		public void applySpeciesChanges(Body body) {
			Colour primaryColor = Colour.COVERING_TAN;
			Colour secondaryColor = Colour.COVERING_BLACK;
			double rand = Math.random();
			if(rand<0.05f) {
				primaryColor = Colour.COVERING_BLACK;
			}
			else if(rand<0.1f) {
				primaryColor = Colour.COVERING_WHITE;
			}
			body.getCoverings().put(BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, CoveringModifier.SHORT, primaryColor, false, secondaryColor, false));
			body.getCoverings().put(BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
			body.updateCoverings(true, true, true, true);
			if(body.getFace().getType()==FaceType.CAT_MORPH) {
				body.getFace().setType(null, FaceType.CAT_MORPH_PANTHER);
			}
			body.getTail().setType(null, TailType.CAT_MORPH_TUFTED);
			
			body.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		}
	},
	
	CAT_MORPH_TIGER("statusEffects/race/raceCatMorph",
			"statusEffects/race/raceBackgroundTiger",
			"tiger-morph",
			"tiger-morphs",
			"tiger",
			"tigress",
			"tigers",
			"tigresses",
			"[npc.NamePos] body is extremely strong, and [npc.sheIsFull] capable of great feats of strength.",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_UNARMED, 25f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 25f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "CAT_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "CAT_MORPH_ADVANCED"),
			Race.CAT_MORPH,
			Colour.RACE_CAT_MORPH_TIGER,
			SubspeciesPreference.TWO_AVERAGE,
			"A cat-morph which resembles an anthropomorphised tiger. To be identified as a tiger-morph, a character must be a cat-morph that has striped fur, normal tail and panther face.",
			Util.newArrayListOfValues(
					WorldType.DOMINION,
					WorldType.NIGHTLIFE_CLUB)) {
		@Override
		public void applySpeciesChanges(Body body) {
			Colour primaryColor = Colour.COVERING_ORANGE;
			Colour secondaryColor = Colour.COVERING_BLACK;
			double rand = Math.random();
			if(rand<0.05f) {
				primaryColor = Colour.COVERING_BLACK;
			}
			else if(rand<0.10f) {
				primaryColor = Colour.COVERING_WHITE;
			}
			body.getCoverings().put(BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.STRIPED, CoveringModifier.SHORT, primaryColor, false, secondaryColor, false));
			body.getCoverings().put(BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
			body.updateCoverings(true, true, true, true);
			if(body.getFace().getType()==FaceType.CAT_MORPH) {
				body.getFace().setType(null, FaceType.CAT_MORPH_PANTHER);
			}
			body.getTail().setType(null, TailType.CAT_MORPH);
			
			body.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		}
	},
	
	CAT_MORPH_CHEETAH("statusEffects/race/raceCatMorph",
			"statusEffects/race/raceBackgroundCheetah",
			"cheetah-morph",
			"cheetah-morphs",
			"cheetah",
			"cheetah",
			"cheetahs",
			"cheetahs",
			"[npc.NameIsFull] extremely fast, and in short bursts, is capable of running at speeds far greater than any other race.",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 25f)),
			Util.newArrayListOfValues("[style.boldExcellent(100%)] chance of escape vs non-cheetah-morphs"),
			UtilText.parseFromXMLFile("characters/raceInfo", "CAT_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "CAT_MORPH_ADVANCED"),
			Race.CAT_MORPH,
			Colour.RACE_CAT_MORPH_CHEETAH,
			SubspeciesPreference.TWO_AVERAGE,
			"A cat-morph which resembles an anthropomorphised cheetah. To be identified as a cheetah-morph, a character must be a cat-morph that has short, spotted fur and not identified as other feline morphs.",
			Util.newArrayListOfValues(
					WorldType.DOMINION,
					WorldType.NIGHTLIFE_CLUB)) {
		@Override
		public void applySpeciesChanges(Body body) {
			Colour primaryColor = Colour.COVERING_ORANGE;
			double rand = Math.random();
			if(rand<0.35f) {
				primaryColor = Colour.COVERING_TAN;
			}
			Colour secondaryColor = Colour.COVERING_BLACK;
			body.getCoverings().put(BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.SPOTTED, CoveringModifier.SHORT, primaryColor, false, secondaryColor, false));
			body.getCoverings().put(BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
			body.updateCoverings(true, true, true, true);
			body.getTail().setType(null, TailType.CAT_MORPH);
			
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
	},
	
	CAT_MORPH_CARACAL("statusEffects/race/raceCatMorph",
			"statusEffects/race/raceBackground",
			"caracal-morph",
			"caracal-morphs",
			"caracal",
			"caracal",
			"caracals",
			"caracals",
			"[npc.NamePos] body is incredibly agile, and [npc.she] [npc.verb(possess)] lightning reflexes."
					+ " [npc.She] also [npc.has] an instinctive desire to display [npc.her] dominance over innocent harpies and rodent-morphs...",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 10f),
					new Value<Attribute, Float>(Attribute.DAMAGE_HARPY, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_RAT_MORPH, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_SQUIRREL_MORPH, 5f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "CAT_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "CAT_MORPH_ADVANCED"),
			Race.CAT_MORPH,
			Colour.RACE_CAT_MORPH_CARACAL,
			SubspeciesPreference.TWO_AVERAGE,
			"A cat-morph which resembles an anthropomorphised caracal. To be identified as a caracal-morph, a character must be a cat-morph with tufted ears.",
			Util.newArrayListOfValues(
					WorldType.DOMINION,
					WorldType.NIGHTLIFE_CLUB)) {
		@Override
		public void applySpeciesChanges(Body body) {
			body.getEar().setType(null, EarType.CAT_MORPH_TUFTED);
		}
	},

	// EQUINES:
	HORSE_MORPH("statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"horse-morph",
			"horse-morphs",
			"horse-boy",
			"horse-girl",
			"horse-boys",
			"horse-girls",
			"While [npc.namePos] body possesses remarkable strength and speed, [npc.sheIs] not the sharpest tool in the shed, and struggles more than most when it comes to harnessing the arcane.",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, -10f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 15f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 25f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "HORSE_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "HORSE_MORPH_ADVANCED"),
			Race.HORSE_MORPH,
			Colour.RACE_HORSE_MORPH, SubspeciesPreference.FOUR_ABUNDANT, "A typical bipedal horse-morph.", Util.newArrayListOfValues(
					WorldType.DOMINION,
					WorldType.NIGHTLIFE_CLUB)),

	HORSE_MORPH_ZEBRA("statusEffects/race/raceHorseMorphZebra",
			"statusEffects/race/raceBackgroundZebra",
			"zebra-morph",
			"zebra-morphs",
			"zebra-boy",
			"zebra-girl",
			"zebra-boys",
			"zebra-girls",
			"While [npc.namePos] body possesses an impressive level of both strength and speed, [npc.sheIs] not the sharpest tool in the shed, and struggles more than most when it comes to harnessing the arcane."
					+ " [npc.She] also [npc.has] a high resistance to both natural and arcane heat.",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, -10f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 20f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_FIRE, 25f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "HORSE_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "HORSE_MORPH_ADVANCED"),
			Race.HORSE_MORPH,
			Colour.BASE_BLACK, SubspeciesPreference.ONE_LOW, "A bipedal horse-morph which has black-and-white striped fur. To be identified as a zebra-morph, a character must be a horse-morph that has black-and-white striped hair, with a zebra-morph's tail.", Util.newArrayListOfValues(
					WorldType.DOMINION,
					WorldType.NIGHTLIFE_CLUB)) {
		@Override
		public void applySpeciesChanges(Body body) {
			body.getCoverings().put(BodyCoveringType.HORSE_HAIR, new Covering(BodyCoveringType.HORSE_HAIR, CoveringPattern.STRIPED, CoveringModifier.SHORT, Colour.COVERING_BLACK, false, Colour.COVERING_WHITE, false));
			body.getCoverings().put(BodyCoveringType.HAIR_HORSE_HAIR, new Covering(BodyCoveringType.HAIR_HORSE_HAIR, CoveringPattern.NONE, Colour.COVERING_BLACK, false, Colour.COVERING_WHITE, false));
			body.getCoverings().put(BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, Colour.SKIN_EBONY, false, Colour.SKIN_EBONY, false));
			body.updateCoverings(true, true, true, true);
			
			if(body.getTail().getType()==TailType.HORSE_MORPH) {
				body.getTail().setType(null, TailType.HORSE_MORPH_ZEBRA);
			}
		}
	},

	REINDEER_MORPH("statusEffects/race/raceReindeerMorph",
			"statusEffects/race/raceBackground",
			"reindeer-morph",
			"reindeer-morphs",
			"reindeer-boy",
			"reindeer-girl",
			"reindeer-boys",
			"reindeer-girls",
			"[npc.NamePos] body is very well suited to resisting both natural and arcane cold, and is also particularly strong and hardy.",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 10f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, 50f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "REINDEER_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "REINDEER_MORPH_ADVANCED"),
			Race.REINDEER_MORPH,
			Colour.RACE_REINDEER_MORPH, SubspeciesPreference.FOUR_ABUNDANT, "A typical bipedal reindeer-morph.", Util.newArrayListOfValues(
					WorldType.DOMINION,
					WorldType.NIGHTLIFE_CLUB)),
	
	//CENTAUR(Race.CENTAUR.getName(), Race.HORSE_MORPH, RacialBody.CENTAUR, SubspeciesPreference.TWO_LOW,
	//		"A "+Race.HORSE_MORPH.getName()+" with a bestial lower body that walks on four legs"),

	// REPTILE:
	ALLIGATOR_MORPH("statusEffects/race/raceGatorMorph",
			"statusEffects/race/raceBackground",
			"alligator-morph",
			"alligator-morphs",
			"alligator-boy",
			"alligator-girl",
			"alligator-boys",
			"alligator-girls",
			"[npc.NamePos] body is incredibly tough, and [npc.she] [npc.verb(possess)] lightning reflexes, as well as the strength required to make the most of any sudden attacks.",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 10f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 25f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "ALLIGATOR_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "ALLIGATOR_MORPH_ADVANCED"),
			Race.ALLIGATOR_MORPH,
			Colour.RACE_ALLIGATOR_MORPH, SubspeciesPreference.FOUR_ABUNDANT, "A typical bipedal alligator-morph.", Util.newArrayListOfValues(
					WorldType.SUBMISSION,
					WorldType.NIGHTLIFE_CLUB)),
	
	//LIZARD_MORPH(Race.LIZARD_MORPH.getName(), Race.LIZARD_MORPH, RacialBody.LIZARD_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.LIZARD_MORPH.getName()),
	//LAMIA(Race.LAMIA.getName(), Race.LIZARD_MORPH, RacialBody.LAMIA, SubspeciesPreference.TWO_LOW,
	//		"A "+Race.LIZARD_MORPH.getName()+" with a serpentine lower body, devoid of legs"),
	
	// AQUATIC:
	//SHARK_MORPH(Race.SHARK_MORPH.getName(), Race.SHARK_MORPH, RacialBody.SHARK_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.SHARK_MORPH.getName()),
	//TIGER_SHARK(Race.TIGER_SHARK.getName(), Race.TIGER_SHARK, RacialBody.TIGER_SHARK, SubspeciesPreference.FIVE_ABUNDANT,
	//		"An extremely aggressive variety of "+Race.SHARK_MORPH.getName()),
	
	// INSECTS:
	//BEE_MORPH(Race.BEE_MORPH.getName(), Race.BEE_MORPH, RacialBody.BEE_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.BEE_MORPH.getName()),
	//ROYAL_BEE(Race.ROYAL_BEE.getName(), Race.BEE_MORPH, RacialBody.ROYAL_BEE, SubspeciesPreference.ZERO_NONE,
	//		"A bipedal "+Race.BEE_MORPH.getName()+" at the top of the bee-morph hierarchy"),
	//WASP_MORPH(Race.WASP_MORPH.getName(), Race.WASP_MORPH, RacialBody.WASP_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.WASP_MORPH.getName()),
	
	// ARACHNIDS:
	//SPIDER_MORPH(Race.SPIDER_MORPH.getName(), Race.SPIDER_MORPH, RacialBody.SPIDER_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.SPIDER_MORPH.getName()),
	//ARACHNE(Race.ARACHNE.getName(), Race.SPIDER_MORPH, RacialBody.ARACHNE, SubspeciesPreference.TWO_LOW,
	//		"A "+Race.SPIDER_MORPH.getName()+" with an arachnid lower body that walks on eight legs"),
			
	// DRAGONS:
	//DRAGON(Race.DRAGON.getName(), Race.DRAGON, RacialBody.DRAGON, SubspeciesPreference.FOUR_HIGH,
	//		"A typical bipedal "+Race.DRAGON.getName()),
	//DRAGON_FUR("Fur "+Race.DRAGON.getName(), Race.DRAGON, RacialBody.DRAGON_FUR, SubspeciesPreference.ZERO_NONE,
	//		"A "+Race.DRAGON.getName()+" with a thick coat of fur, rather than scales"),
	//WYVERN(Race.WYVERN.getName(), Race.DRAGON, RacialBody.WYVERN, SubspeciesPreference.ONE_MINIMAL,
	//		"A bipedal "+Race.DRAGON.getName()+" with arms that act as wings"),
	//WYRM(Race.WYRM.getName(), Race.DRAGON, RacialBody.WYRM, SubspeciesPreference.ONE_MINIMAL,
	//		"A "+Race.DRAGON.getName()+" with a serpentine lower body, devoid of legs"),
	
	// SLIMES:
	SLIME("statusEffects/race/raceSlime",
			"statusEffects/race/raceBackgroundSlime",
			"slime",
			"slimes",
			"slime-boy",
			"slime-girl",
			"slime-boys",
			"slime-girls",
			"Due to [npc.her] soft and morphable body, [npc.nameIsFull] all-but immune to physical damage, but this boon is a double-edged sword, as [npc.she] [npc.has] no hope of inflicting any physical damage without a weapon."
					+ " [npc.She] can also morph [npc.her] body at will, allowing [npc.herHim] to take on any form that [npc.she] [npc.verb(desire)].",
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 100f),
					new Value<Attribute, Float>(Attribute.DAMAGE_UNARMED, -100f)),
			Util.newArrayListOfValues(
					"<b style='color: "+ Colour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'>Can morph body at will</b>",
					"<b style='color: "+ Colour.GENERIC_SEX.toWebHexString()+ ";'>Impregnated through any orifice</b>"),
			UtilText.parseFromXMLFile("characters/raceInfo", "SLIME_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "SLIME_ADVANCED"),
			Race.SLIME,
			Colour.RACE_SLIME, SubspeciesPreference.FOUR_ABUNDANT, "A typical slime.", Util.newArrayListOfValues(
					WorldType.SUBMISSION,
					WorldType.BAT_CAVERNS)) {
		@Override
		public void applySpeciesChanges(Body body) {
			// Slime subspecies are set in the CharacterUtils.generateBody() method
			body.setBodyMaterial(BodyMaterial.SLIME);
		}
		
		@Override
		public String getName(GameCharacter character) {
			if(character==null) {
				return super.getName(character);
			}
			Subspecies coreSubspecies = Subspecies.getFleshSubspecies(character.getBody());
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getName(character);
			}
			return coreSubspecies.getName(character)+"-"+super.getName(character);
		}
		
		@Override
		public String getNamePlural(GameCharacter character) {
			if(character==null) {
				return super.getNamePlural(character);
			}
			Subspecies coreSubspecies = Subspecies.getFleshSubspecies(character.getBody());
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getNamePlural(character);
			}
			return coreSubspecies.getName(character)+"-"+super.getNamePlural(character);
		}

		@Override
		public String getSingularMaleName(GameCharacter character) {
			if(character==null) {
				return super.getSingularMaleName(character);
			}
			Subspecies coreSubspecies = Subspecies.getFleshSubspecies(character.getBody());
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getSingularMaleName(character);
			}
			return coreSubspecies.getSingularMaleName(character)+"-"+super.getName(character);
		}

		@Override
		public String getSingularFemaleName(GameCharacter character) {
			if(character==null) {
				return super.getSingularFemaleName(character);
			}
			Subspecies coreSubspecies = Subspecies.getFleshSubspecies(character.getBody());
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getSingularFemaleName(character);
			}
			return coreSubspecies.getSingularFemaleName(character)+"-"+super.getName(character);
		}

		@Override
		public String getPluralMaleName(GameCharacter character) {
			if(character==null) {
				return super.getPluralMaleName(character);
			}
			Subspecies coreSubspecies = Subspecies.getFleshSubspecies(character.getBody());
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getPluralMaleName(character);
			}
			return coreSubspecies.getSingularMaleName(character)+"-"+super.getNamePlural(character);
		}

		@Override
		public String getPluralFemaleName(GameCharacter character) {
			if(character==null) {
				return super.getPluralFemaleName(character);
			}
			Subspecies coreSubspecies = Subspecies.getFleshSubspecies(character.getBody());
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getPluralFemaleName(character);
			}
			return coreSubspecies.getSingularFemaleName(character)+"-"+super.getNamePlural(character);
		}

		@Override
		public String getSVGString(GameCharacter character) {
			return Subspecies.getFleshSubspecies(character.getBody()).getSlimeSVGString(character);
		}

		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			return Subspecies.getFleshSubspecies(character.getBody()).getSVGStringDesaturated(character);
		}
	},
	
//	
//	// GARGOYLES:
//	GARGOYLE(Race.GARGOYLE.getName(), Race.GARGOYLE, RacialBody.GARGOYLE, SubspeciesPreference.FIVE_ABUNDANT,
//			"A typical "+Race.GARGOYLE.getName()),
//	GARGOYLE_CAT(Race.CAT_MORPH.getName()+" "+Race.GARGOYLE.getName(), Race.GARGOYLE, RacialBody.GARGOYLE_CAT, SubspeciesPreference.TWO_LOW,
//			"A "+Race.GARGOYLE.getName()+" resembling a typical bipedal "+Race.CAT_MORPH.getName()),
//	GARGOYLE_DOG(Race.DOG_MORPH.getName()+" "+Race.GARGOYLE.getName(), Race.GARGOYLE, RacialBody.GARGOYLE_DOG, SubspeciesPreference.TWO_LOW,
//			"A "+Race.GARGOYLE.getName()+" resembling a typical bipedal "+Race.DOG_MORPH.getName()),
//	GARGOYLE_WOLF(Race.WOLF_MORPH.getName()+" "+Race.GARGOYLE.getName(), Race.GARGOYLE, RacialBody.GARGOYLE_WOLF, SubspeciesPreference.TWO_LOW,
//			"A "+Race.GARGOYLE.getName()+" resembling a typical bipedal "+Race.WOLF_MORPH.getName()),
//	GARGOYLE_HORSE(Race.HORSE_MORPH.getName()+" "+Race.GARGOYLE.getName(), Race.GARGOYLE, RacialBody.GARGOYLE_HORSE, SubspeciesPreference.TWO_LOW,
//			"A "+Race.GARGOYLE.getName()+" resembling a typical bipedal "+Race.HORSE_MORPH.getName()),

	// RODENTS:
	SQUIRREL_MORPH("statusEffects/race/raceSquirrelMorph",
			"statusEffects/race/raceBackground",
			"squirrel-morph",
			"squirrel-morphs",
			"squirrel-boy",
			"squirrel-girl",
			"squirrel-boys",
			"squirrel-girls",
			"[npc.NameIsFull] very agile and alert, and is capable of leaping great distances with [npc.her] powerful [npc.legs].",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 5f),
					new Value<Attribute, Float>(Attribute.DODGE_CHANCE, 2f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "SQUIRREL_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "SQUIRREL_MORPH_ADVANCED"),
			Race.SQUIRREL_MORPH,
			Colour.RACE_SQUIRREL_MORPH, SubspeciesPreference.FOUR_ABUNDANT, "A typical bipedal squirrel-morph.", Util.newArrayListOfValues(
					WorldType.DOMINION,
					WorldType.NIGHTLIFE_CLUB)),
	
	//MOUSE_MORPH(Race.MOUSE_MORPH.getName(), Race.MOUSE_MORPH, RacialBody.MOUSE_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.MOUSE_MORPH.getName()),
	
	RAT_MORPH("statusEffects/race/raceRatMorph",
			"statusEffects/race/raceBackground",
			"rat-morph",
			"rat-morphs",
			"rat-boy",
			"rat-girl",
			"rat-boys",
			"rat-girls",
			"[npc.NamePos] body is very hardy, and [npc.she] [npc.has] both a high resistance to, and affinity with, arcane poison.",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_POISON, 15f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_POISON, 15f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "RAT_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "RAT_MORPH_ADVANCED"),
			Race.RAT_MORPH,
			Colour.RACE_RAT_MORPH, SubspeciesPreference.FOUR_ABUNDANT, "A typical bipedal rat-morph.", Util.newArrayListOfValues(
					WorldType.SUBMISSION,
					WorldType.NIGHTLIFE_CLUB)),

	RABBIT_MORPH("statusEffects/race/raceRabbitMorph",
			"statusEffects/race/raceBackground",
			"rabbit-morph",
			"rabbit-morphs",
			"rabbit-boy",
			"rabbit-girl",
			"rabbit-boys",
			"rabbit-girls",
			"[npc.NameIsFull] very agile and alert, and is capable of short bursts of incredible speed."
					+ " [npc.Her] body, whether [npc.she] [npc.verb(like)] it or not, is also adapted for producing as many offspring as possible.",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 5f),
					new Value<Attribute, Float>(Attribute.FERTILITY, 50f),
					new Value<Attribute, Float>(Attribute.VIRILITY, 50f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "RABBIT_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "RABBIT_MORPH_ADVANCED"),
			Race.RABBIT_MORPH,
			Colour.RACE_RABBIT_MORPH, SubspeciesPreference.FOUR_ABUNDANT, "A typical bipedal rabbit-morph.", Util.newArrayListOfValues(
					WorldType.DOMINION,
					WorldType.NIGHTLIFE_CLUB)), //TODO move to fields

	RABBIT_MORPH_LOP("statusEffects/race/raceRabbitLopMorph",
			"statusEffects/race/raceBackground",
			"lop-rabbit-morph",
			"lop-rabbit-morphs",
			"lop-rabbit-boy",
			"lop-rabbit-girl",
			"lop-rabbit-boys",
			"lop-rabbit-girls",
			"[npc.NameIsFull] very agile and alert, and is capable of short bursts of incredible speed."
					+ " [npc.Her] body, whether [npc.she] [npc.verb(like)] it or not, is also adapted for producing as many offspring as possible.",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 5f),
					new Value<Attribute, Float>(Attribute.FERTILITY, 50f),
					new Value<Attribute, Float>(Attribute.VIRILITY, 50f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "RABBIT_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "RABBIT_MORPH_ADVANCED"),
			Race.RABBIT_MORPH,
			Colour.RACE_RABBIT_MORPH, SubspeciesPreference.FOUR_ABUNDANT, "A bipedal rabbit-morph, with floppy ears instead of the usual upright ones.", Util.newArrayListOfValues(
					WorldType.DOMINION,
					WorldType.NIGHTLIFE_CLUB)) {  //TODO move to fields
		@Override
		public void applySpeciesChanges(Body body) {
			if(body.getEar().getType()==EarType.RABBIT_MORPH) {
				body.getEar().setType(null, EarType.RABBIT_MORPH_FLOPPY);
			}
		}
	},
	
	BAT_MORPH("statusEffects/race/raceBatMorph",
			"statusEffects/race/raceBackground",
			"bat-morph",
			"bat-morphs",
			"bat-boy",
			"bat-girl",
			"bat-boys",
			"bat-girls",
			"Due to their unique echolocation ability, all bat-morphs have a natural desire to talk as much as possible."
					+ " Due to this, [npc.name] continuously [npc.verb(play)] out conversations in [npc.her] head, allowing [npc.herHim] to think up new and exciting ways to seduce people before having ever met them.",
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 15f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "BAT_MORPH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "BAT_MORPH_ADVANCED"),
			Race.BAT_MORPH,
			Colour.RACE_BAT_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical bipedal bat-morph.",
			Util.newArrayListOfValues(
					WorldType.SUBMISSION,
					WorldType.BAT_CAVERNS,
					WorldType.NIGHTLIFE_CLUB)),
	
	// AVIAN:
	HARPY("statusEffects/race/raceHarpy",
			"statusEffects/race/raceBackground",
			"harpy",
			"harpies",
			"harpy",
			"harpy",
			"harpies",
			"harpies",
			"[npc.NameIsFull] obsessed with [npc.her] appearance, and wouldn't think it unusual for someone to want to spend at least half of their waking hours preening themselves in order to look as attractive as possible.",
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 15f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "HARPY_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "HARPY_ADVANCED"),
			Race.HARPY,
			Colour.RACE_HARPY,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical harpy.",
			Util.newArrayListOfValues(
					WorldType.HARPY_NEST,
					WorldType.NIGHTLIFE_CLUB)),
	
	HARPY_RAVEN("statusEffects/race/raceHarpy",
			"statusEffects/race/raceBackground",
			"raven-harpy",
			"raven-harpies",
			"raven-harpy",
			"raven-harpy",
			"raven-harpies",
			"raven-harpies",
			"[npc.NameIsFull] obsessed with [npc.her] appearance, and wouldn't think it unusual for someone to want to spend at least half of their waking hours preening themselves in order to look as attractive as possible.",
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 15f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "HARPY_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "HARPY_ADVANCED"),
			Race.HARPY,
			Colour.BASE_BLACK,
			SubspeciesPreference.ONE_LOW,
			"A harpy that has dark black feathers, resembling those of a raven.",
			Util.newArrayListOfValues(
					WorldType.HARPY_NEST,
					WorldType.NIGHTLIFE_CLUB)) {
		@Override
		public void applySpeciesChanges(Body body) {
			body.getCoverings().put(BodyCoveringType.FEATHERS, new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, Colour.COVERING_BLACK, false, Colour.COVERING_BLACK, false));
			body.getCoverings().put(BodyCoveringType.BODY_HAIR_HARPY, new Covering(BodyCoveringType.BODY_HAIR_HARPY, CoveringPattern.NONE, Colour.COVERING_BLACK, false, Colour.COVERING_BLACK, false));
		}
	},

	HARPY_BALD_EAGLE("statusEffects/race/raceHarpy",
			"statusEffects/race/raceBackground",
			"bald-eagle-harpy",
			"bald-eagle-harpies",
			"bald-eagle-harpy",
			"bald-eagle-harpy",
			"bald-eagle-harpies",
			"bald-eagle-harpies",
			"[npc.NameIsFull] obsessed with [npc.her] appearance, and wouldn't think it unusual for someone to want to spend at least half of their waking hours preening themselves in order to look as attractive as possible.",
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 15f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "HARPY_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "HARPY_ADVANCED"),
			Race.HARPY,
			Colour.BASE_WHITE,
			SubspeciesPreference.ONE_LOW,
			"A harpy that has dark brown feathers covering their body, with white feathers on their head, resembling the colouring of a bald eagle.",
			Util.newArrayListOfValues(
					WorldType.HARPY_NEST,
					WorldType.NIGHTLIFE_CLUB)) {
		@Override
		public void applySpeciesChanges(Body body) {
			body.getCoverings().put(BodyCoveringType.FEATHERS, new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, Colour.COVERING_BROWN_DARK, false, Colour.COVERING_BROWN_DARK, false));
			body.getCoverings().put(BodyCoveringType.HAIR_HARPY, new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, Colour.COVERING_WHITE, false, Colour.COVERING_WHITE, false));
			body.getCoverings().put(BodyCoveringType.BODY_HAIR_HARPY, new Covering(BodyCoveringType.BODY_HAIR_HARPY, CoveringPattern.NONE, Colour.COVERING_BROWN_DARK, false, Colour.COVERING_BROWN_DARK, false));
		}
	},
	
	// ELEMENTALS:

	ELEMENTAL_EARTH("combat/spell/elemental_earth",
			"",
			"earth elemental",
			"earth elementals",
			"earth elemental",
			"earth elemental",
			"earth elementals",
			"earth elementals",
			"[npc.NameIsFull] a summoned elemental, currently bound to the school of Earth.",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 100f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 50f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_EARTH_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_EARTH_ADVANCED"),
			Race.ELEMENTAL_EARTH,
			Colour.SPELL_SCHOOL_EARTH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"An arcane elemental bound to the school of Earth.",
			Util.newArrayListOfValues()) {
		@Override
		public void applySpeciesChanges(Body body) {
			body.setBodyMaterial(BodyMaterial.STONE);
		}
	},

	ELEMENTAL_WATER("combat/spell/elemental_water",
			"",
			"water elemental",
			"water elementals",
			"water elemental",
			"water elemental",
			"water elementals",
			"water elementals",
			"[npc.NameIsFull] a summoned elemental, currently bound to the school of Water.",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, 100f),
					new Value<Attribute, Float>(Attribute.DAMAGE_ICE, 50f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_WATER_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_WATER_ADVANCED"),
			Race.ELEMENTAL_WATER,
			Colour.SPELL_SCHOOL_WATER,
			SubspeciesPreference.FOUR_ABUNDANT,
			"An arcane elemental bound to the school of Water.",
			Util.newArrayListOfValues()) {
		@Override
		public void applySpeciesChanges(Body body) {
			body.setBodyMaterial(BodyMaterial.WATER);
		}
	},

	ELEMENTAL_AIR("combat/spell/elemental_air",
			"",
			"air elemental",
			"air elementals",
			"air elemental",
			"air elemental",
			"air elementals",
			"air elementals",
			"[npc.NameIsFull] a summoned elemental, currently bound to the school of Air.",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_POISON, 100f),
					new Value<Attribute, Float>(Attribute.DAMAGE_POISON, 50f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_AIR_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_AIR_ADVANCED"),
			Race.ELEMENTAL_AIR,
			Colour.SPELL_SCHOOL_AIR,
			SubspeciesPreference.FOUR_ABUNDANT,
			"An arcane elemental bound to the school of Air.",
			Util.newArrayListOfValues()) {
		@Override
		public void applySpeciesChanges(Body body) {
			body.setBodyMaterial(BodyMaterial.AIR);
		}
	},

	ELEMENTAL_FIRE("combat/spell/elemental_fire",
			"",
			"fire elemental",
			"fire elementals",
			"fire elemental",
			"fire elemental",
			"fire elementals",
			"fire elementals",
			"[npc.NameIsFull] a summoned elemental, currently bound to the school of Fire.",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_FIRE, 100f),
					new Value<Attribute, Float>(Attribute.DAMAGE_FIRE, 50f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_FIRE_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_FIRE_ADVANCED"),
			Race.ELEMENTAL_FIRE,
			Colour.SPELL_SCHOOL_FIRE,
			SubspeciesPreference.FOUR_ABUNDANT,
			"An arcane elemental bound to the school of Fire.",
			Util.newArrayListOfValues()) {
		@Override
		public void applySpeciesChanges(Body body) {
			body.setBodyMaterial(BodyMaterial.FIRE);
		}
	},

	ELEMENTAL_ARCANE("combat/spell/elemental_arcane",
			"",
			"arcane elemental",
			"arcane elementals",
			"arcane elemental",
			"arcane elemental",
			"arcane elementals",
			"arcane elementals",
			"[npc.NameIsFull] a summoned elemental, currently bound to the school of Arcane.",
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, 100f),
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 50f)),
			null,
			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_ARCANE_BASIC"),
			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_ARCANE_ADVANCED"),
			Race.ELEMENTAL_ARCANE,
			Colour.SPELL_SCHOOL_ARCANE,
			SubspeciesPreference.FOUR_ABUNDANT,
			"An arcane elemental bound to the school of Arcane.",
			Util.newArrayListOfValues()) {
		@Override
		public void applySpeciesChanges(Body body) {
			body.setBodyMaterial(BodyMaterial.ARCANE);
		}
	},
	
	;
	//TENGU(Race.TENGU.getName(), Race.TENGU, RacialBody.TENGU, SubspeciesPreference.TWO_LOW,
	//		"A hermetic kind of "+Race.HARPY.getName());

	
	private String name;
	private String namePlural;
	private String singularMaleName;
	private String singularFemaleName;
	private String pluralMaleName;
	private String pluralFemaleName;
	
	private String statusEffectDescription;
	private Map<Attribute, AttributeRange> attributeModifiers;
	private Map<Attribute, Float> statusEffectAttributeModifiers;
	private List<String> extraEffects;
	
	private String basicDescription;
	private String advancedDescription;
	
	private Race race;
	private Colour colour;
	private SubspeciesPreference subspeciesPreferenceDefault;
	private String description;
	protected String SVGString;
	protected String SVGStringDesaturated;
	protected String slimeSVGString;
	private List<WorldType> worldLocations;

	private static Map<WorldType, List<Subspecies>> worldSpecies;
	private static List<Subspecies> dominionStormImmuneSpecies;
	
	static {
		worldSpecies = new HashMap<>();
		dominionStormImmuneSpecies = new ArrayList<>();
		
		for(Subspecies species : Subspecies.values()) {
			for(WorldType type : species.getWorldLocations()) {
				worldSpecies.putIfAbsent(type, new ArrayList<>());
				worldSpecies.get(type).add(species);

				try {
					if(type == WorldType.DOMINION && RacialBody.valueOfRace(species.getRace()).getAttributeModifiers().get(Attribute.MAJOR_ARCANE).getMinimum()>=IntelligenceLevel.TWO_SMART.getMinimumValue()) {
						dominionStormImmuneSpecies.add(species);
					}
				} catch(Exception ex) {	
				}
			}
		}
	}

	private Subspecies(
			String iconPathName,
			String iconBackgroundPathName,
			String name,
			String namePlural,
			String singularMaleName,
			String singularFemaleName,
			String pluralMaleName,
			String pluralFemaleName,
			String statusEffectDescription,
			Map<Attribute, Float> statusEffectAttributeModifiers,
			List<String> extraEffects,
			String basicDescription,
			String advancedDescription,
			Race race,
			Colour colour,
			SubspeciesPreference subspeciesPreferenceDefault,
			String description,
			List<WorldType> worldLocations) {
		this(iconPathName,
				 iconBackgroundPathName,
				 name,
				 namePlural,
				 singularMaleName,
				 singularFemaleName,
				 pluralMaleName,
				 pluralFemaleName,
				 statusEffectDescription,
				 statusEffectAttributeModifiers,
				 extraEffects,
				 basicDescription,
				 advancedDescription,
				 race,
				 null,
				 colour,
				 subspeciesPreferenceDefault,
				 description,
				 worldLocations);
	}
	
	private Subspecies(
			String iconPathName,
			String iconBackgroundPathName,
			String name,
			String namePlural,
			String singularMaleName,
			String singularFemaleName,
			String pluralMaleName,
			String pluralFemaleName,
			String statusEffectDescription,
			Map<Attribute, Float> statusEffectAttributeModifiers,
			List<String> extraEffects,
			String basicDescription,
			String advancedDescription,
			Race race,
			Map<Attribute, AttributeRange> attributeModifiers,
			Colour colour,
			SubspeciesPreference subspeciesPreferenceDefault,
			String description,
			List<WorldType> worldLocations) {
		
		this.name = name;
		this.namePlural = namePlural;

		this.singularMaleName = singularMaleName;
		this.singularFemaleName = singularFemaleName;
		
		this.pluralMaleName = pluralMaleName;
		this.pluralFemaleName = pluralFemaleName;

		this.statusEffectDescription = statusEffectDescription;
		this.statusEffectAttributeModifiers = statusEffectAttributeModifiers;
		
		if(attributeModifiers!=null) {
			this.attributeModifiers = attributeModifiers;
		} else {
			this.attributeModifiers = new HashMap<>();
		}

		if(extraEffects == null) {
			this.extraEffects = new ArrayList<>();
		} else {
			this.extraEffects = extraEffects;
		}
		
		this.basicDescription = basicDescription;
		this.advancedDescription = advancedDescription;
		
		this.race = race;
		this.colour = colour;
		this.subspeciesPreferenceDefault = subspeciesPreferenceDefault;
		this.description = description;
		
		this.worldLocations = worldLocations;
		
		if(iconPathName!=null) {
			try {
				InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/" + iconPathName + ".svg");
				if(is==null) {
					System.err.println("Error! Subspecies icon file does not exist (Trying to read from '"+iconPathName+"')! (Code 1)");
				}
				SVGString = Util.inputStreamToString(is);
				
				is.close();
				
				String SVGStringBackground = "";
				if(!iconBackgroundPathName.isEmpty()) {
					is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/" + iconBackgroundPathName + ".svg");
					if(is==null) {
						System.err.println("Error! Subspecies background icon file does not exist (Trying to read from '"+iconPathName+"')! (Code 1)");
					}
					SVGStringBackground = "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+Util.inputStreamToString(is)+"</div>";
	
					is.close();
				}
				
				String baseSVGString = SVGStringBackground + "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGString+"</div>";
				
				slimeSVGString = Util.colourReplacement(this.toString(),
						Colour.RACE_SLIME,
						Colour.RACE_SLIME,
						Colour.RACE_SLIME,
						"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>" + SVGImages.SVG_IMAGE_PROVIDER.getRaceBackgroundSlime()+"</div>"
						+ "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGString+"</div>");
				
				SVGStringDesaturated = Util.colourReplacement(this.toString(),
						Colour.BASE_GREY,
						Colour.BASE_GREY,
						Colour.BASE_GREY,
						baseSVGString);
				
				SVGString = Util.colourReplacement(this.toString(),
						colour,
						colour,
						colour,
						baseSVGString);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
//			try {
//				InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/" + iconPathName + ".svg");
//				if(is==null) {
//					System.err.println("Error! Subspecies icon file does not exist (Trying to read from '"+iconPathName+"')! (Code 2)");
//				}
//				SVGStringDesaturated = Util.inputStreamToString(is);
//	
//				SVGStringDesaturated = SVGStringDesaturated.replaceAll("#ff2a2a", Colour.BASE_GREY.getShades()[0]);
//				SVGStringDesaturated = SVGStringDesaturated.replaceAll("#ff5555", Colour.BASE_GREY.getShades()[1]);
//				SVGStringDesaturated = SVGStringDesaturated.replaceAll("#ff8080", Colour.BASE_GREY.getShades()[2]);
//				SVGStringDesaturated = SVGStringDesaturated.replaceAll("#ffaaaa", Colour.BASE_GREY.getShades()[3]);
//				SVGStringDesaturated = SVGStringDesaturated.replaceAll("#ffd5d5", Colour.BASE_GREY.getShades()[4]);
//	
//				is.close();
//	
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			
		} else {
			SVGString = "";
		}
	}

	public void applySpeciesChanges(Body body) {	
	}

	/**
	 * Changes that should be applied to any offspring of this species.
	 */
	public void applyOffspringSpeciesChanges(Body body) {
		applySpeciesChanges(body);
	}

	public static Subspecies getMainSubspeciesOfRace(Race race) {
		switch(race) {
			case NONE:
				break;
			case ALLIGATOR_MORPH:
				return Subspecies.ALLIGATOR_MORPH;
			case ANGEL:
				return Subspecies.ANGEL;
			case BAT_MORPH:
				return Subspecies.BAT_MORPH;
			case CAT_MORPH:
				return Subspecies.CAT_MORPH;
			case COW_MORPH:
				return Subspecies.COW_MORPH;
			case DEMON:
				return Subspecies.DEMON;
			case DOG_MORPH:
				return Subspecies.DOG_MORPH;
			case FOX_MORPH:
				return Subspecies.FOX_MORPH;
			case HARPY:
				return Subspecies.HARPY;
			case HORSE_MORPH:
				return Subspecies.HORSE_MORPH;
			case HUMAN:
				return Subspecies.HUMAN;
			case RABBIT_MORPH:
				return Subspecies.RABBIT_MORPH;
			case RAT_MORPH:
				return Subspecies.RAT_MORPH;
			case REINDEER_MORPH:
				return Subspecies.REINDEER_MORPH;
			case SLIME:
				return Subspecies.SLIME;
			case SQUIRREL_MORPH:
				return Subspecies.SQUIRREL_MORPH;
			case WOLF_MORPH:
				return Subspecies.WOLF_MORPH;
			case ELEMENTAL_AIR:
				return Subspecies.ELEMENTAL_AIR;
			case ELEMENTAL_ARCANE:
				return Subspecies.ELEMENTAL_ARCANE;
			case ELEMENTAL_EARTH:
				return Subspecies.ELEMENTAL_EARTH;
			case ELEMENTAL_FIRE:
				return Subspecies.ELEMENTAL_FIRE;
			case ELEMENTAL_WATER:
				return Subspecies.ELEMENTAL_WATER;
		}
		return Subspecies.HUMAN;
	}
	
	/**
	 * @return The race of this body if it were made from flesh. (i.e. The body's race ignoring slime/elemental modifiers.)
	 */
	public static Subspecies getFleshSubspecies(Body body) {
		return getSubspeciesFromBody(body, body.getRaceFromPartWeighting());
	}
	
	public static Subspecies getSubspeciesFromBody(Body body, Race race) {
		switch(body.getBodyMaterial()) {
			case FIRE:
				return Subspecies.ELEMENTAL_FIRE;
			case ICE:
			case WATER:
				return Subspecies.ELEMENTAL_WATER;
			case RUBBER:
			case STONE:
				return Subspecies.ELEMENTAL_EARTH;
			case AIR:
				return Subspecies.ELEMENTAL_AIR;
			case ARCANE:
				return Subspecies.ELEMENTAL_ARCANE;
			case SLIME:
			case FLESH:
				break;
		}
		
		Subspecies subspecies = null;
		switch(race) {
			case NONE:
				break;
			case ALLIGATOR_MORPH:
				subspecies = Subspecies.ALLIGATOR_MORPH;
				break;
			case ANGEL:
				subspecies = Subspecies.ANGEL;
				break;
			case CAT_MORPH:
				subspecies = Subspecies.CAT_MORPH;
				FaceType faceType = body.getFace().getType();
				
				if(body.getHair().getType() == HairType.CAT_MORPH_SIDEFLUFF
					&& body.getEar().getType()==EarType.CAT_MORPH_TUFTED
					&& body.getCoverings().get(BodyCoveringType.FELINE_FUR).getModifier() == CoveringModifier.FLUFFY
					&& body.getTail().getType()==TailType.CAT_MORPH_SHORT) {
					subspecies = Subspecies.CAT_MORPH_LYNX;
						
				} else if((faceType == FaceType.CAT_MORPH_PANTHER || faceType == FaceType.HUMAN)
					&& body.getCoverings().get(BodyCoveringType.FELINE_FUR).getPattern() == CoveringPattern.SPOTTED
					&& body.getCoverings().get(BodyCoveringType.FELINE_FUR).getModifier() == CoveringModifier.FLUFFY
					&& body.getTail().getType()==TailType.CAT_MORPH) {
					subspecies = Subspecies.CAT_MORPH_LEOPARD_SNOW;
					
				} else if((faceType == FaceType.CAT_MORPH_PANTHER || faceType == FaceType.HUMAN)
					&& body.getCoverings().get(BodyCoveringType.FELINE_FUR).getPattern() == CoveringPattern.SPOTTED
					&& body.getCoverings().get(BodyCoveringType.FELINE_FUR).getModifier() == CoveringModifier.SHORT
					&& body.getTail().getType()==TailType.CAT_MORPH) {
					subspecies = Subspecies.CAT_MORPH_LEOPARD;
					
				} else if((faceType == FaceType.CAT_MORPH_PANTHER || faceType == FaceType.HUMAN)
					&& body.getCoverings().get(BodyCoveringType.FELINE_FUR).getModifier() == CoveringModifier.SHORT
					&& body.getTail().getType()==TailType.CAT_MORPH_TUFTED) {
					subspecies = Subspecies.CAT_MORPH_LION;
					
				} else if((faceType == FaceType.CAT_MORPH_PANTHER || faceType == FaceType.HUMAN)
					&& body.getCoverings().get(BodyCoveringType.FELINE_FUR).getPattern() == CoveringPattern.STRIPED
					&& body.getTail().getType()==TailType.CAT_MORPH) {
					subspecies = Subspecies.CAT_MORPH_TIGER;
					
				} else if(body.getCoverings().get(BodyCoveringType.FELINE_FUR).getPattern() == CoveringPattern.SPOTTED
					&& body.getCoverings().get(BodyCoveringType.FELINE_FUR).getModifier() == CoveringModifier.SHORT) {
					subspecies = Subspecies.CAT_MORPH_CHEETAH;
					
				} else if(body.getEar().getType()==EarType.CAT_MORPH_TUFTED) {
					subspecies = Subspecies.CAT_MORPH_CARACAL;
				}
				break;
				
			case COW_MORPH:
				subspecies = Subspecies.COW_MORPH;
				break;
			case DEMON:
				subspecies = Subspecies.DEMON;
				if(body.getHeight()==Height.NEGATIVE_TWO_MIMIMUM) {
					subspecies = Subspecies.IMP;
				} else if(body.getHeight()==Height.NEGATIVE_ONE_TINY) {
					subspecies = Subspecies.IMP_ALPHA;
				}
				break;
			case ELEMENTAL_AIR:
				subspecies = Subspecies.ELEMENTAL_AIR;
				break;
			case ELEMENTAL_ARCANE:
				subspecies = Subspecies.ELEMENTAL_ARCANE;
				break;
			case ELEMENTAL_EARTH:
				subspecies = Subspecies.ELEMENTAL_EARTH;
				break;
			case ELEMENTAL_FIRE:
				subspecies = Subspecies.ELEMENTAL_FIRE;
				break;
			case ELEMENTAL_WATER:
				subspecies = Subspecies.ELEMENTAL_WATER;
				break;
			case DOG_MORPH:
				subspecies = Subspecies.DOG_MORPH;
			
				if(body.getCoverings().get(BodyCoveringType.CANINE_FUR).getPrimaryColour()==Colour.COVERING_BLACK
					&& (body.getCoverings().get(BodyCoveringType.CANINE_FUR).getSecondaryColour()==Colour.COVERING_BROWN
							|| body.getCoverings().get(BodyCoveringType.CANINE_FUR).getSecondaryColour()==Colour.COVERING_BROWN_DARK
							|| body.getCoverings().get(BodyCoveringType.CANINE_FUR).getSecondaryColour()==Colour.COVERING_TAN)
					&& body.getCoverings().get(BodyCoveringType.CANINE_FUR).getPattern() == CoveringPattern.MARKED
					&& body.getCoverings().get(BodyCoveringType.CANINE_FUR).getModifier() == CoveringModifier.SHORT
					) {
					subspecies = Subspecies.DOG_MORPH_DOBERMANN;
				}
			
				if(body.getCoverings().get(BodyCoveringType.CANINE_FUR).getPrimaryColour()==Colour.COVERING_BLACK
						&& body.getCoverings().get(BodyCoveringType.CANINE_FUR).getSecondaryColour()==Colour.COVERING_WHITE
						&& body.getCoverings().get(BodyCoveringType.CANINE_FUR).getPattern() == CoveringPattern.MARKED
						&& body.getCoverings().get(BodyCoveringType.CANINE_FUR).getModifier() == CoveringModifier.FLUFFY
						&& (body.getEar().getType()==EarType.DOG_MORPH_FOLDED || body.getEar().getType()==EarType.DOG_MORPH_POINTED)
						) {
						subspecies = Subspecies.DOG_MORPH_BORDER_COLLIE;
				}
				break;
			case HARPY:
				subspecies = Subspecies.HARPY;
				if(body.getCoverings().get(BodyCoveringType.FEATHERS).getPrimaryColour()==Colour.COVERING_BLACK) {
					subspecies = Subspecies.HARPY_RAVEN;
				}
				if(body.getCoverings().get(BodyCoveringType.FEATHERS).getPrimaryColour()==Colour.COVERING_BROWN_DARK
						&& body.getCoverings().get(BodyCoveringType.HAIR_HARPY).getPrimaryColour()==Colour.COVERING_WHITE) {
					subspecies = Subspecies.HARPY_BALD_EAGLE;
				}
				break;
			case FOX_MORPH:
				subspecies = Subspecies.FOX_MORPH;
				Covering fox_fur = body.getCoverings().get(BodyCoveringType.FOX_FUR);
				List<Colour> fennecColours = Util.newArrayListOfValues(Colour.COVERING_BLEACH_BLONDE, Colour.COVERING_TAN);
				
				if (fennecColours.contains(fox_fur.getPrimaryColour())
						&& (fennecColours.contains(fox_fur.getSecondaryColour()) || fox_fur.getPattern()==CoveringPattern.NONE)
						&& (body.getEar().getType()==EarType.FOX_MORPH_BIG)) {
					subspecies = body.getTail().getType() == TailType.FOX_MORPH_MAGIC
							?Subspecies.FOX_ASCENDANT_FENNEC
							: Subspecies.FOX_MORPH_FENNEC;
					
				} else if (body.getTail().getType() == TailType.FOX_MORPH_MAGIC) {
					subspecies = Subspecies.FOX_ASCENDANT;
				}
				break;
			case HORSE_MORPH:
				subspecies = Subspecies.HORSE_MORPH;
				
				if(((body.getCoverings().get(BodyCoveringType.HORSE_HAIR).getPrimaryColour()==Colour.COVERING_BLACK
						&& body.getCoverings().get(BodyCoveringType.HORSE_HAIR).getSecondaryColour()==Colour.COVERING_WHITE)
						|| (body.getCoverings().get(BodyCoveringType.HORSE_HAIR).getPrimaryColour()==Colour.COVERING_WHITE
								&& body.getCoverings().get(BodyCoveringType.HORSE_HAIR).getSecondaryColour()==Colour.COVERING_BLACK))
						&& body.getTail().getType()==TailType.HORSE_MORPH_ZEBRA) {
						subspecies = Subspecies.HORSE_MORPH_ZEBRA;
					}
				break;
			case HUMAN:
				subspecies = Subspecies.HUMAN;
				break;
			case REINDEER_MORPH:
				subspecies = Subspecies.REINDEER_MORPH;
				break;
			case SQUIRREL_MORPH:
				subspecies = Subspecies.SQUIRREL_MORPH;
				break;
			case RAT_MORPH:
				subspecies = Subspecies.RAT_MORPH;
				break;
			case BAT_MORPH:
				subspecies = Subspecies.BAT_MORPH;
				break;
			case WOLF_MORPH:
				subspecies = Subspecies.WOLF_MORPH;
				break;
			case SLIME:
				subspecies = Subspecies.SLIME;
				break;
			case RABBIT_MORPH:
				subspecies = Subspecies.RABBIT_MORPH;
				if(body.getEar().getType()==EarType.RABBIT_MORPH_FLOPPY) {
					subspecies = Subspecies.RABBIT_MORPH_LOP;
				}
				break;
		}
		
		return subspecies;
	}
	
	private static void applyFoxColoring(Body body) {
		Colour c1 = body.getCoverings().get(BodyCoveringType.FOX_FUR).getPrimaryColour();
		Colour c2 = Colour.COVERING_WHITE;
		CoveringPattern pat = CoveringPattern.MARKED;
		double rand = Math.random();
		
		switch (c1) {
			case COVERING_BROWN:
				if(rand<0.5f) {
					c2 = Colour.COVERING_BROWN;
					pat = CoveringPattern.NONE;
				} else {
					c2 = Colour.COVERING_TAN;
				}
				break;
			case COVERING_BROWN_DARK:
				if(rand<0.5f) {
					c2 = Colour.COVERING_BROWN_DARK;
					pat = CoveringPattern.NONE;
				} else {
					c2 = Colour.COVERING_BROWN;
				}
				break;
			case COVERING_BLONDE:
			case COVERING_GINGER:
			default:
				// Set primary color to GINGER if we have a color that otherwise wouldn't be in this switch statement.
				if(c1 != Colour.COVERING_BLONDE) {c1 = Colour.COVERING_GINGER;}
				if(rand<0.025f) {
					c2 = Colour.COVERING_BLACK;
				} else if(rand<0.05f) {
					c2 = Colour.COVERING_BROWN;
				} else if(rand<0.5f) {
					c2 = Colour.COVERING_GREY;
				}
				break;
			case COVERING_SILVER:
			case COVERING_GREY:
				if(rand<0.5f) {
					c2 = c1;
					pat = CoveringPattern.NONE;
				}
				break;
			case COVERING_BLACK:
				if(rand<0.5f) {
					c2 = Colour.COVERING_BLACK;
					pat = CoveringPattern.NONE;
				} else {
					c2 = Colour.COVERING_GREY;
				}
				break;
			case COVERING_TAN:
			case COVERING_WHITE:
				c2 = c1;
				pat = CoveringPattern.NONE;
				break;
		}
		body.getCoverings().put(BodyCoveringType.FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, pat, c1, false, c2, false));
	}
	
	public Subspecies getOffspringSubspecies() {
		return this;
	}
	
	public boolean isOffspringAlwaysMothersRace() {
		return false;
	}
	
	public boolean isShortStature() {
		return false;
	}
	
	/**
	 * @param The character whose subspecies's name is to be returned. Can pass in null.
	 * @return The singular name of this character's subspecies.
	 */
	public String getName(GameCharacter character) {
		return name;
	}

	/**
	 * @param The character whose subspecies's pluralised name is to be returned. Can pass in null.
	 * @return The plural name of this character's subspecies.
	 */
	public String getNamePlural(GameCharacter character) {
		return namePlural;
	}
	
	/**
	 * @param The character whose male subspecies name is to be returned. Can pass in null.
	 * @return The singular male name of this character's subspecies.
	 */
	public String getSingularMaleName(GameCharacter character) {
		if(getRace() == Race.WOLF_MORPH && Main.game.isSillyModeEnabled()){
			return "awoo-boy";
			
		} else{
			return singularMaleName;
		}
	}

	/**
	 * @param The character whose female subspecies name is to be returned. Can pass in null.
	 * @return The singular female name of this character's subspecies.
	 */
	public String getSingularFemaleName(GameCharacter character) {
		if(getRace() == Race.WOLF_MORPH && Main.game.isSillyModeEnabled()){
			return "awoo-girl";
			
		} else{
			return singularFemaleName;
		}
	}

	/**
	 * @param The character whose male subspecies's pluralised name is to be returned. Can pass in null.
	 * @return The plural male name of this character's subspecies.
	 */
	public String getPluralMaleName(GameCharacter character) {
		return pluralMaleName;
	}

	/**
	 * @param The character whose female subspecies's pluralised name is to be returned. Can pass in null.
	 * @return The plural female name of this character's subspecies.
	 */
	public String getPluralFemaleName(GameCharacter character) {
		return pluralFemaleName;
	}

	public String getStatusEffectDescription(GameCharacter character) {
		return UtilText.parse(character, statusEffectDescription);
	}

	public Map<Attribute, Float> getStatusEffectAttributeModifiers(GameCharacter character) {
		return statusEffectAttributeModifiers;
	}

	public Map<Attribute, AttributeRange> getAttributeModifiers(GameCharacter character) {
		return attributeModifiers;
	}

	public List<String> getExtraEffects() {
		return extraEffects;
	}

	public String getBasicDescription(GameCharacter character) {
		return basicDescription;
	}

	public String getAdvancedDescription(GameCharacter character) {
		return advancedDescription;
	}
	
	public Race getRace() {
		return race;
	}
	
	public Colour getColour(GameCharacter character) {
		return colour;
	}
	
	public SubspeciesPreference getSubspeciesPreferenceDefault() {
		return subspeciesPreferenceDefault;
	}
	
	public String getDescription(GameCharacter character) {
		return description;
	}
	
	public String getSVGString(GameCharacter character) {
		// I tested using skin colour as backgrounds, but it looked bad... I'll leave this here in case I want to have another go some day.
//		Covering covering =  character.getCovering(RacialBody.valueOfRace(character.getRace()).getSkinType().getBodyCoveringType(character));
//		Colour primary = covering.getPrimaryColour();
//		Colour secondary = covering.getSecondaryColour();
//		if(character.getRaceStage()!=RaceStage.GREATER && character.getRaceStage()!=RaceStage.HUMAN) {
//			secondary = primary;
//			primary = character.getCovering(BodyCoveringType.HUMAN).getPrimaryColour();
//		}
//		
//		String backgroundSVGString =
//				"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"
//						+(character.getSubspecies()==Subspecies.SLIME
//							?SVGImages.SVG_IMAGE_PROVIDER.getRaceBackgroundSlime()
//							:(SVGStringBackgroundPath.equalsIgnoreCase("statusEffects/race/raceBackground")
//								&& primary != secondary
//									?SVGImages.SVG_IMAGE_PROVIDER.getRaceBackgroundHalf()
//									:SVGStringBackground))
//				+"</div>"
//				+ "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGString+"</div>";
//				
//		backgroundSVGString = Util.colourReplacement(this.toString(),
//				this.getColour(character),
//				primary,
//				secondary,
//				backgroundSVGString);
//		
//		return backgroundSVGString;
		
		return SVGString;
	}
	
	public String getSVGStringDesaturated(GameCharacter character) {
//		String backgroundSVGString =
//				"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGStringBackground+"</div>"
//				+ "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGStringDesaturated+"</div>";
//				
//		backgroundSVGString = Util.colourReplacement(this.toString(),
//				Colour.BASE_GREY_DARK,
//				Colour.BASE_WHITE,
//				Colour.BASE_GREY,
//				backgroundSVGString);
//		
//		return backgroundSVGString;

		return SVGStringDesaturated;
	}

	public String getSlimeSVGString(GameCharacter character) {
		return slimeSVGString;
	}

	public List<WorldType> getWorldLocations() {
		return worldLocations;
	}

	public static Map<WorldType, List<Subspecies>> getWorldSpecies() {
		return worldSpecies;
	}

	public static List<Subspecies> getDominionStormImmuneSpecies() {
		return dominionStormImmuneSpecies;
	}
}