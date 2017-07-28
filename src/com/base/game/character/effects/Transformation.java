package com.base.game.character.effects;

import java.util.ArrayList;
import java.util.List;

import com.base.game.character.GameCharacter;
import com.base.game.character.attributes.Attribute;
import com.base.game.character.body.types.HornType;
import com.base.game.character.body.types.PenisType;
import com.base.game.character.body.types.TailType;
import com.base.game.character.body.types.VaginaType;
import com.base.game.character.body.types.WingType;
import com.base.game.character.race.RacialBody;
import com.base.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.65
 * @author Innoxia
 */
public enum Transformation {

	// Very basic, single-effect transformations.

	// GENERIC ADD & REMOVE:

	// Core:
	FEMININITY_INCREASE("increase femininity") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementFemininity(5);
		}
	},
	FEMININITY_DECREASE("decrease femininty") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementFemininity(-5);
		}
	},
	FEMININITY_INCREASE_LARGE("large femininity increase") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementFemininity(15);
		}
	},
	FEMININITY_DECREASE_LARGE("large femininty decrease") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementFemininity(-15);
		}
	},
	HEIGHT_INCREASE("increase height") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementHeight(3);
		}
	},
	HEIGHT_DECREASE("decrease height") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementHeight(-3);
		}
	},

	// Ass:

	GROW_ASS("grow ass") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementAssSize(1);
		}
	},
	SHRINK_ASS("shrink ass") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementAssSize(-1);
		}
	},
	GROW_HIPS("grow hips") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementHipSize(1);
		}
	},
	SHRINK_HIPS("shrink hips") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementHipSize(-1);
		}
	},
	ASS_WETNESS_INCREASE("increase ass wetness") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementAssWetness(1);
		}
	},
	ASS_WETNESS_DECREASE("decrease ass wetness") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementAssWetness(-1);
		}
	},
	ASS_ELASTICITY_INCREASE("increase ass elasticity") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementAssElasticity(1);
		}
	},
	ASS_ELASTICITY_DECREASE("decrease ass elasticity") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementAssElasticity(-1);
		}
	},
	ASS_CAPACITY_INCREASE("increase ass capacity") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementAssCapacity(1);
		}
	},
	ASS_CAPACITY_DECREASE("decrease ass capacity") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementAssCapacity(-1);
		}
	},

	// Penis:

	GROW_PENIS("grow penis") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementPenisSize(1);
		}
	},
	SHRINK_PENIS("shrink penis") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementPenisSize(-1);
		}
	},
	PENIS_CAPACITY_INCREASE("increase penis capacity") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementPenisCapacity(1);
		}
	},
	PENIS_CAPACITY_DECREASE("decrease penis capacity") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementPenisCapacity(-1);
		}
	},
	GROW_TESTICLES("grow testicles") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementTesticleSize(1);
		}
	},
	SHRINK_TESTICLES("shrink testicles") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementTesticleSize(-1);
		}
	},
	CUM_PRODUCTION_INCREASE("increase cum production") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementPenisCumProduction(1);
		}
	},
	CUM_PRODUCTION_DECREASE("decrease cum production") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementPenisCumProduction(-1);
		}
	},
	CUM_PRODUCTION_INCREASE_LARGE("increase cum production") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementPenisCumProduction(10);
		}
	},
	CUM_PRODUCTION_DECREASE_LARGE("decrease cum production") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementPenisCumProduction(-10);
		}
	},

	// Vagina:

	VAGINA_CAPACITY_INCREASE("increase vagina capacity") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementVaginaCapacity(1);
		}
	},
	VAGINA_CAPACITY_DECREASE("decrease vagina capacity") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementVaginaCapacity(-1);
		}
	},
	VAGINA_WETNESS_INCREASE("increase vagina wetness") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementVaginaWetness(1);
		}
	},
	VAGINA_WETNESS_DECREASE("decrease vagina wetness") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementVaginaWetness(-1);
		}
	},
	VAGINA_ELASTICITY_INCREASE("increase vagina elasticity") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementVaginaElasticity(1);
		}
	},
	VAGINA_ELASTICITY_DECREASE("decrease vagina elasticity") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementVaginaElasticity(-1);
		}
	},
	GROW_CLITORIS("grow clitoris") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementVaginaClitorisSize(1);
		}
	},
	SHRINK_CLITORIS("shrink clitoris") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementVaginaClitorisSize(-1);
		}
	},

	// Breasts & Nipples:

	GROW_BREASTS("grow breasts") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementBreastSize(1);
		}
	},
	SHRINK_BREASTS("shrink breasts") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementBreastSize(-1);
		}
	},
	BREAST_ROW_ADD("add breast row") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementBreastRows(1);
		}
	},
	BREAST_ROW_REMOVE("remove breast row") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementBreastRows(-1);
		}
	},
	BREAST_ROW_THREE("set three breast rows") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.setBreastRows(3);
		}
	},
	BREAST_CAPACITY_INCREASE("increase nipple capacity") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementBreastCapacity(1);
		}
	},
	BREAST_CAPACITY_DECREASE("decrease nipple capacity") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementBreastCapacity(-1);
		}
	},
	FUCKABLE_NIPPLES_REMOVE("remove fuckable nipples") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementBreastCapacity(-100);
		}
	},
	LACTATION_INCREASE("increase lactation") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			if((target.getBreastLactation().getMaximumValue()-target.getBreastLactation().getMinimumValue())/4 < 5)
				return target.incrementBreastLactation(5);
			else
				return target.incrementBreastLactation((target.getBreastLactation().getMaximumValue()-target.getBreastLactation().getMinimumValue())/4);
		}
	},
	LACTATION_DECREASE("decrease lactation") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.incrementBreastLactation(-(target.getBreastLactation().getMaximumValue()-target.getBreastLactation().getMinimumValue())/4);
		}
	},
//	LACTATION_INCREASE_LARGE("large lactation increase") {
//		@Override
//		public String applyEffect(GameCharacter target, RacialBody body) {
//			return target.incrementBreastLactation((target.getBreastLactation().getMaximumValue()-target.getBreastLactation().getMinimumValue())/2);
//		}
//	},
//	LACTATION_DECREASE_LARGE("large lactation decrease") {
//		@Override
//		public String applyEffect(GameCharacter target, RacialBody body) {
//			return target.incrementBreastLactation(-(target.getBreastLactation().getMaximumValue()-target.getBreastLactation().getMinimumValue())/2);
//		}
//	},

	// BODY PART TRANSFORMATION:

	// Removals:
	REMOVE_TAIL("remove tail") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.setTailType(TailType.NONE);
		}
	},
	REMOVE_HORNS("remove horns") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.setHornType(HornType.NONE);
		}
	},
	REMOVE_WINGS("remove wings") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.setWingType(WingType.NONE);
		}
	},
	REMOVE_PENIS("remove penis") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.setPenisType(PenisType.NONE);
		}
	},
	REMOVE_VAGINA("remove vagina") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.setVaginaType(VaginaType.NONE);
		}
	},

	// Full greater transformation:
	GREATER("greater transformation") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.setSkinType(body.getSkinType()) + target.setFaceType(body.getFaceType());
		}
	},

	// Full lesser transformation:
	LESSER("lesser transformation") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.setLegType(body.getLegType()) + target.setArmType(body.getArmType());
		}
	},

	// Lesser transformations:
	FACE("face transformation") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.setFaceType(body.getFaceType());
		}
	},
	EYE("eye transformation") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.setEyeType(body.getEyeType());
		}
	},
	EAR("ear transformation") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.setEarType(body.getEarType());
		}
	},
	HAIR("hair transformation") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.setHairType(body.getHairType());
		}
	},
	TAIL("tail transformation") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.setTailType(body.getTailType());
		}
	},
	HORNS_FEMALE("horns transformation") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.setHornType(body.getHornTypeFemale());
		}
	},
	HORNS_MALE("horns transformation") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.setHornType(body.getHornTypeMale());
		}
	},
	WINGS("wings transformation") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.setWingType(body.getWingType());
		}
	},

	// Greater transformations:
	ARMS("arms transformation") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.setArmType(body.getArmType());
		}
	},
	LEGS("legs transformation") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.setLegType(body.getLegType());
		}
	},

	// Monster transformations:
	SKIN("skin transformation") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.setSkinType(body.getSkinType());
		}
	},

	// Sexual transformations:
	PENIS("penis transformation") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.setPenisType(body.getPenisType());
		}
	},
	// PENIS_SECOND("second penis transformation"){
	// @Override
	// public String applyEffect(GameCharacter target, RacialBody body) {
	// return target.setSecondPenisType(body.getPenisType());
	// }
	// },
	VAGINA("vagina transformation") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.setVaginaType(body.getVaginaType());
		}
	},
	ASS("ass transformation") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.setAssType(body.getAssType());
		}
	},
	BREASTS("breasts transformation") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.setBreastType(body.getBreastType());
		}
	},

	// Stats:
//	STATS("stats change") {
//		@Override
//		public String applyEffect(GameCharacter target, RacialBody body) {
//			if (body == RacialBody.DOG_MORPH)
//				return STATS_DOG_MORPH.applyEffect(target, body);
//			else if (body == RacialBody.WOLF_MORPH)
//				return STATS_WOLF_MORPH.applyEffect(target, body);
//			else if (body == RacialBody.CAT_MORPH)
//				return STATS_CAT_MORPH.applyEffect(target, body);
//			else if (body == RacialBody.HORSE_MORPH)
//				return STATS_HORSE_MORPH.applyEffect(target, body);
//
//			return raceStatChange(target, body);
//		}
//	},

	ANGEL_TEARS("human stats") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			if (target.getBaseAttributeValue(Attribute.STRENGTH) < 60)
				attributeChanges.add(Attribute.STRENGTH);
			if (target.getBaseAttributeValue(Attribute.INTELLIGENCE) < 60)
				attributeChanges.add(Attribute.FITNESS);
			if (target.getBaseAttributeValue(Attribute.FITNESS) < 60)
				attributeChanges.add(Attribute.FITNESS);
			if (target.getBaseAttributeValue(Attribute.CORRUPTION) > 0)
				attributeChanges.add(Attribute.CORRUPTION);

			Attribute a = attributeChanges.get(Util.random.nextInt(attributeChanges.size()));

			switch (a) {
				case STRENGTH:
					return "<p>"
							+ "The world around you suddenly seems to grow brighter, and you're filled with an inexplicable sense of hope and happiness."
							+ " A soft feminine voice, speaking in an incomprehensible language, sounds from somewhere within your head,"
							+ " but even though you don't understand the words, you know that the speaker is granting you a portion of her strength."
						+ "</p>"
						+ "<p>"
							+ target.incrementAttribute(Attribute.STRENGTH, 1)
						+ "</p>";
	
				case INTELLIGENCE:
					return "<p>"
								+ "The world around you suddenly seems to grow brighter, and you're filled with an inexplicable sense of hope and happiness."
								+ " A soft feminine voice, speaking in an incomprehensible language, sounds from somewhere within your head,"
								+ " but even though you don't understand the words, you know that the speaker is helping to fortify your mind."
							+ "</p>"
							+ "<p>"
								+ target.incrementAttribute(Attribute.INTELLIGENCE, 1)
							+ "</p>";
	
				case FITNESS:
					return "<p>"
								+ "The world around you suddenly seems to grow brighter, and you're filled with an inexplicable sense of hope and happiness."
								+ " A soft feminine voice, speaking in an incomprehensible language, sounds from somewhere within your head,"
								+ " but even though you don't understand the words, you know that the speaker is granting you a portion of her energy."
							+ "</p>"
							+ "<p>"
								+ target.incrementAttribute(Attribute.FITNESS, 1)
							+ "</p>";
					
				case CORRUPTION:
					return "<p>"
								+ "The world around you suddenly seems to grow brighter, and you're filled with an inexplicable sense of hope and happiness."
								+ " A soft feminine voice, speaking in an incomprehensible language, sounds from somewhere within your head,"
								+ " but even though you don't understand the words, you know that the speaker is trying to purify your body and mind."
							+ "</p>"
							+ "<p>"
								+ target.incrementAttribute(Attribute.CORRUPTION, -1)
							+ "</p>";
	
				default:
					target.incrementHealth(25);
					target.incrementMana(25);
					target.incrementStamina(25);
					return "<p>"
								+ "The world around you suddenly seems to grow brighter, and you're filled with an inexplicable sense of hope and happiness"
								+ " A surge of energy flows through your body, and you realise that you've been healed by some sort of arcane power."
							+ "</p>";
			}
		}
	},
	STATS_CORRUPTION("corruption stats") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			if (target.getBaseAttributeValue(Attribute.CORRUPTION) < 100)
				return "<p>"
							+ "Your mind suddenly feels foggy, and as you shake your head to try and clear it, a strange image flashes before your eyes."
							+ " For a moment, you're fooled into thinking that you're suddenly outside after sunset, but after a moment you realise that you're actually standing in a collossal, poorly-illuminated hall."
							+ " A series of gigantic, gothically-styled obsidian pillars form a huge circle around you."
							+ " Each one is as large as a giant redwood tree, and although flickering arcane lanterns illuminate their bases, their tops are lost in the ceiling's inky blackness high above you."
						+ "</p>"
						+ "<p>"
							+ "Your mind barely registers this, however, as you're far more concerned with what's right in front of you."
							+ " A winding series of narrow steps have been cleverly carved into a gigantic, ragged spire of obsidian, which, while larger than a house, is dwarfed by the scale of the room it's sitting in."
							+ " On either side of the steps, little platforms have been chiseled out of the rock, and on each one is bound a slave, many of whom have been forced into lewd poses by chains and metal rods."
						+ "</p>"
						+ "<p>"
							+ "As your eyes finally make it to the top of the steps, all the lights in the room are suddenly extinguished."
							+ " In that split second before you're cast into darkness, you thought you caught a glimpse of the most jaw-droppingly beautiful woman to ever have existed."
							+ " As the name 'Lilith' shoots through your head like a bolt of sexually-charged arcane lightning, a pair of red eyes suddenly glow in the dark, and as they turn to bore a hole in your skull, you snap out of the vision."
						+ "</p>"
						+ "<p>"
							+ "You blink and look around, making sure that you're back in reality, and as you let out a sigh of relief, a sickly sweetness starts to branch out through your mind..."
						+ "</p>"
						+ "<p>"
							+ target.incrementAttribute(Attribute.CORRUPTION, 5)
						+ "</p>";
					
			else{
				target.incrementHealth(25);
				target.incrementMana(25);
				target.incrementStamina(25);
				return "<p>" 
							+ "A surge of energy flows through your body, and you realise that you've been healed by some sort of arcane power."
						+ "</p>";
			}
		}
	},
	STATS_DOG_MORPH("dog-morph stats") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			if (target.getBaseAttributeValue(Attribute.STRENGTH) < 30)
				attributeChanges.add(Attribute.STRENGTH);
			if (target.getBaseAttributeValue(Attribute.INTELLIGENCE) < 20)
				attributeChanges.add(Attribute.INTELLIGENCE);
			if (target.getBaseAttributeValue(Attribute.FITNESS) < 40)
				attributeChanges.add(Attribute.FITNESS);
			if (target.getBaseAttributeValue(Attribute.CORRUPTION) < 10)
				attributeChanges.add(Attribute.CORRUPTION);

			Attribute a = attributeChanges.get(Util.random.nextInt(attributeChanges.size()));

			switch (a) {
				case STRENGTH:
					return "<p>"
							+ "Images of strong, handsome dog-boys flash through your mind, and you feel your own muscles rapidly growing as you get stronger."
						+ "</p>"
						+ "<p>"
							+ target.incrementAttribute(Attribute.STRENGTH, 1)
						+ "</p>";
	
				case INTELLIGENCE:
					return "<p>"
							+ "You briefly see an image of a lesser dog-girl commanding you to sit, and it somehow makes your mind feel a lot clearer."
						+ "</p>"
						+ "<p>"
							+ target.incrementAttribute(Attribute.INTELLIGENCE, 1)
						+ "</p>";
	
				case FITNESS:
					return "<p>"
								+ "For a split second, the image of an energetic dog-girl running through a field flashes through your mind."
							+ "</p>"
								+ "<p>" + target.incrementAttribute(Attribute.FITNESS, 1)
							+ "</p>";
	
				case CORRUPTION:
					return "<p>"
								+ "Your mind suddenly feels foggy, and as you shake your head to try and clear it, a strange image flashes before your eyes."
								+ " A lesser dog-girl is down on all fours, being fucked by a group of greater dog-boys."
								+ " Their knotted red cocks slam in and out of every available orifice, and a lewd, wet slapping sound echoes around the room."
								+ " For a brief moment, you suddenly slip into the body of the dog-girl, and you let out a delighted squeal as you feel the dog-boys cumming in all your holes."
								+ " At that moment, your mind instantly clears, and you snap back to reality, feeling a little dirtier than before."
							+ "</p>"
							+ "<p>"
								+ target.incrementAttribute(Attribute.CORRUPTION, 1)
							+ "</p>";
					
				default:
					target.incrementHealth(25);
					target.incrementMana(25);
					target.incrementStamina(25);
					return "<p>" 
								+ "A surge of energy flows through your body, and you realise that you've been healed by some sort of arcane power."
							+ "</p>";
			}
		}
	},
	STATS_WOLF_MORPH("wolf-morph stats") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			if (target.getBaseAttributeValue(Attribute.STRENGTH) < 60)
				attributeChanges.add(Attribute.STRENGTH);
			if (target.getBaseAttributeValue(Attribute.INTELLIGENCE) < 20)
				attributeChanges.add(Attribute.INTELLIGENCE);
			if (target.getBaseAttributeValue(Attribute.FITNESS) < 50)
				attributeChanges.add(Attribute.FITNESS);
			if (target.getBaseAttributeValue(Attribute.CORRUPTION) < 20)
				attributeChanges.add(Attribute.CORRUPTION);

			Attribute a = attributeChanges.get(Util.random.nextInt(attributeChanges.size()));

			switch (a) {
			case STRENGTH:
				return "<p>"
							+ "Images of strong, muscular wolf-boys fill your mind, and you feel your own muscles rapidly growing as your body tries to match their obvious strength."
						+ "</p>"
						+ "<p>" + target.incrementAttribute(Attribute.STRENGTH, 1) + "</p>";

			case INTELLIGENCE:
				return "<p>"
						+ "An image of a greater wolf-girl flashes across your mind, and as she instructs you on how to behave around your pack's alpha, you feel your mind grow stronger."
					+ "</p>"
					+ "<p>" + target.incrementAttribute(Attribute.INTELLIGENCE, 1) + "</p>";

			case FITNESS:
				return "<p>"
							+ "For a split second, you find yourself running amongst a group of greater wolf-morphs, and you feel your body getting fitter as it tires to keep up."
						+ "</p>"
						+ "<p>" + target.incrementAttribute(Attribute.FITNESS, 1) + "</p>";
				
			case CORRUPTION:
				return "<p>"
							+ "Your mind suddenly feels foggy, and as you shake your head to try and clear it, a strange image flashes before your eyes."
							+ " A lesser wolf-girl is down on all fours, being watched by a group of wolf-morphs as a huge, muscular wolf-boy penetrates her, doggy-style."
							+ " His massive, knotted red cock slams in and out of her dripping pussy as he holds her fluffy, wolf-like tail firmly in one hand."
							+ " For a brief moment, you suddenly slip into the body of the wolf-girl, and as the wolf-boy starts cumming deep into your womb, he growls at you to remember that he's your alpha."
							+ " At that moment, your mind instantly clears, and you snap back to reality, feeling a little dirtier than before."
						+ "</p>"
						+ "<p>"
							+ target.incrementAttribute(Attribute.CORRUPTION, 1)
						+ "</p>";

			default:
				target.incrementHealth(25);
				target.incrementMana(25);
				target.incrementStamina(25);
				return "<p>" 
							+ "A surge of energy flows through your body, and you realise that you've been healed by some sort of arcane power."
						+ "</p>";
			}
		}
	},
	STATS_CAT_MORPH("cat-morph stats") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			if (target.getBaseAttributeValue(Attribute.STRENGTH) < 20)
				attributeChanges.add(Attribute.STRENGTH);
			if (target.getBaseAttributeValue(Attribute.INTELLIGENCE) < 30)
				attributeChanges.add(Attribute.INTELLIGENCE);
			if (target.getBaseAttributeValue(Attribute.FITNESS) < 40)
				attributeChanges.add(Attribute.FITNESS);
			if (target.getBaseAttributeValue(Attribute.CORRUPTION) < 10)
				attributeChanges.add(Attribute.CORRUPTION);

			Attribute a = attributeChanges.get(Util.random.nextInt(attributeChanges.size()));

			switch (a) {
				case STRENGTH:
					return "<p>"
								+ "An image of a powerful-looking cat-boy flashes through your mind, and you feel your muscles grow as your body tries to compete with his strength."
							+ "</p>"
							+ "<p>" + target.incrementAttribute(Attribute.STRENGTH, 1) + "</p>";
				
				case INTELLIGENCE:
					return "<p>"
								+ "An image of a lesser cat-girl flashes through your mind, and as she instructs you on how to behave, you find that your mind starts to feel somehow clearer than it did before."
							+ "</p>"
							+ "<p>" + target.incrementAttribute(Attribute.INTELLIGENCE, 1) + "</p>";
	
				case FITNESS:
					return "<p>"
								+ "For a split second, you find yourself alongside an energetic cat-girl, leaping across rooftops under the light of a full moon."
							+ "</p>"
							+ "<p>" + target.incrementAttribute(Attribute.FITNESS, 1) + "</p>";

				case CORRUPTION:
					return "<p>"
								+ "Your mind suddenly feels foggy, and as you shake your head to try and clear it, a strange image flashes before your eyes."
								+ " A lesser cat-girl is bouncing up and down on a lucky cat-boy's cock, in the reverse-cowgirl position."
								+ " As the thick, barbed cock slides in and out of her dripping pussy, she focuses her attention on giving handjobs to the two cat-boys that are standing on either side of her."
								+ " For a brief moment, you suddenly slip into the body of the cat-girl, and as the cat-boy you're riding pulls you down onto his cock, all three of your partners start to cum."
								+ " At that moment, your mind instantly clears, and you snap back to reality, feeling a little dirtier than before."
							+ "</p>"
							+ "<p>"
							+ target.incrementAttribute(Attribute.CORRUPTION, 1) + "</p>";
					
				default:
					target.incrementHealth(25);
					target.incrementMana(25);
					target.incrementStamina(25);
					return "<p>" 
								+ "A surge of energy flows through your body, and you realise that you've been healed by some sort of arcane power."
							+ "</p>";
			}
		}
	},
	STATS_HORSE_MORPH("horse-morph stats") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			if (target.getBaseAttributeValue(Attribute.STRENGTH) < 50)
				attributeChanges.add(Attribute.STRENGTH);
			if (target.getBaseAttributeValue(Attribute.INTELLIGENCE) < 10)
				attributeChanges.add(Attribute.INTELLIGENCE);
			if (target.getBaseAttributeValue(Attribute.FITNESS) < 30)
				attributeChanges.add(Attribute.FITNESS);
			if (target.getBaseAttributeValue(Attribute.CORRUPTION) < 10)
				attributeChanges.add(Attribute.CORRUPTION);

			Attribute a = attributeChanges.get(Util.random.nextInt(attributeChanges.size()));

			switch (a) {
				case STRENGTH:
					return "<p>"
								+ "Images of huge, muscular horse-boys fill your mind, and you feel your own muscles rapidly growing as your body tries to match their strength."
							+ "</p>"
							+ "<p>" + target.incrementAttribute(Attribute.STRENGTH, 1) + "</p>";
	
				case INTELLIGENCE:
					return "<p>"
								+ "The image of a blonde-haired lesser horse-girl falshes across your mind, and as she tries to explain the best way to get horse-boys to fuck you, you feel your mind getting clearer."
							+ "</p>"
							+ "<p>" + target.incrementAttribute(Attribute.INTELLIGENCE, 1) + "</p>";
	
				case FITNESS:
					return "<p>"
								+ "For a split second, you find yourself running alongside a horse-girl as you chase after a couple of strong horse-boys."
							+ "</p>"
							+ "<p>" + target.incrementAttribute(Attribute.FITNESS, 1) + "</p>";
					
				case CORRUPTION:
					return "<p>"
								+ "Your mind suddenly feels foggy, and as you shake your head to try and clear it, a strange image flashes before your eyes."
								+ " A lesser horse-girl is being pushed against a wall, face-first, as she's being fucked by a greater horse-boy."
								+ " As the massive, flared cock slides in and out of her dripping pussy, she squeals and begs to be filled with the strong horse-boy's cum."
								+ " For a brief moment, you suddenly slip into the body of the horse-girl, and as the horse-boy grunts in your ear, he presses you tightly against the wall and starts to fill your womb with his potent seed."
								+ " At that moment, your mind instantly clears, and you snap back to reality, feeling a little dirtier than before."
							+ "</p>"
							+ "<p>"
							+ target.incrementAttribute(Attribute.CORRUPTION, 1) + "</p>";
	
				default:
					target.incrementHealth(25);
					target.incrementMana(25);
					target.incrementStamina(25);
					return "<p>" 
								+ "A surge of energy flows through your body, and you realise that you've been healed by some sort of arcane power."
							+ "</p>";
			}
		}
	},
	STATS_HARPY("harpy stats") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			if (target.getBaseAttributeValue(Attribute.STRENGTH) < 10)
				attributeChanges.add(Attribute.STRENGTH);
			if (target.getBaseAttributeValue(Attribute.INTELLIGENCE) < 20)
				attributeChanges.add(Attribute.INTELLIGENCE);
			if (target.getBaseAttributeValue(Attribute.FITNESS) < 50)
				attributeChanges.add(Attribute.FITNESS);
			if (target.getBaseAttributeValue(Attribute.CORRUPTION) < 10)
				attributeChanges.add(Attribute.CORRUPTION);

			Attribute a = attributeChanges.get(Util.random.nextInt(attributeChanges.size()));

			switch (a) {
				case STRENGTH:
					return "<p>"
								+ "The image of a male harpy suddenly flashes through your mind, and although nobody would ever describe him as 'strong',"
								+ " his muscles are still bigger than yours, and you feel your body grow stronger as it realises how weak it really is."
							+ "</p>"
							+ "<p>" + target.incrementAttribute(Attribute.STRENGTH, 1) + "</p>";
				
				case INTELLIGENCE:
					return "<p>"
								+ "For a split second, you see an image of a bright pink female harpy, and as she instructs you on how to treat the pathetic males of her species, you find your mind getting clearer." 
							+ "</p>"
							+ "<p>" + target.incrementAttribute(Attribute.INTELLIGENCE, 1) + "</p>";
				
				case FITNESS:
					return "<p>"
								+ "For a split second, you find yourself high up in the clouds, soaring alongside a blonde-feathered harpy."
								+ " She turns and winks at you, and you find yourself swooping down to your nest as you race her to see who can get there first."
								+ " You feel a surge of energy pass through your body, and as the image fades away, you find yourself feeling a little fitter than you did before."
							+ "</p>"
							+ "<p>" + target.incrementAttribute(Attribute.FITNESS, 1) + "</p>";
				
				case CORRUPTION:
					return "<p>"
								+ "Your mind suddenly feels foggy, and as you shake your head to try and clear it, a strange image flashes before your eyes."
								+ " A female harpy is riding a lesser horse-boy's massive cock, squealing in delight as her pussy squeezes down on his thick member."
								+ " A pair of harpy males are eagerly sucking on her breasts as they try their best to provide even more pleasure for their mistress."
								+ " For a brief moment, you suddenly slip into the body of the female harpy, and as your servants suckle and kiss your sensitive little nipples,"
								+ " you feel the real man beneath you start to cum, filling you with his potent seed."
								+ " At that moment, your mind instantly clears, and you snap back to reality, feeling a little dirtier than before."
							+ "</p>"
							+ "<p>"
							+ target.incrementAttribute(Attribute.CORRUPTION, 1) + "</p>";
					
				default:
					target.incrementHealth(25);
					target.incrementMana(25);
					target.incrementStamina(25);
					return "<p>" 
								+ "A surge of energy flows through your body, and you realise that you've been healed by some sort of arcane power."
							+ "</p>";
			}
		}
	},

	// SISSY:

	SISSY_TRANSFORMATION("sissyfication") {
		@Override
		public String applyEffect(GameCharacter target, RacialBody body) {
			return target.setFemininity(55) + "</br></br>" + target.setAssCapacity(16) + "</br></br>" + target.setAssWetness(16) + "</br></br>" + target.setVaginaType(VaginaType.NONE) + "</br></br>"
					+ (target.getPenisType() == PenisType.NONE ? target.setPenisType(PenisType.HUMAN) + "</br></br>" : "") + target.setPenisSize(1) + "</br></br>" + target.setTesticleSize(1);
		}
	};

	private static List<Attribute> attributeChanges;
	static {
		attributeChanges = new ArrayList<>();
	}

	private String name;

	private Transformation(String name) {
		this.name = name;
	}

	public abstract String applyEffect(GameCharacter target, RacialBody body);

	public String getName() {
		return name;
	}
}
