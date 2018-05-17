package com.lilithsthrone.game.character.body;

import java.io.Serializable;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.2.2
 * @author Innoxia
 */
public class Eye implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	protected EyeType type;
	protected int eyePairs;
	protected EyeShape irisShape, pupilShape;
	
	public Eye(EyeType type) {
		this.type = type;
		eyePairs = type.getEyePairs();
		irisShape = type.getIrisShape();
		pupilShape = type.getPupilShape();
	}

	@Override
	public EyeType getType() {
		return type;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc.getEyePairs()==1) {
			return "a pair of";
		} else {
			return Util.intToString(gc.getEyePairs())+" pairs of";
		}
	}

	@Override
	public String getName(GameCharacter gc) {
		return type.getName(gc);
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		return type.getNameSingular(gc);
	}

	@Override
	public String getNamePlural(GameCharacter gc) {
		return type.getNamePlural(gc);
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		return type.getDescriptor(gc);
	}
	
	public String setType(GameCharacter owner, EyeType type) {
		if (type == getType()) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(You already have the [pc.eyes] of [pc.a_eyeRace], so nothing happens...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already has the [npc.eyes] of [npc.a_eyeRace], so nothing happens...)]</p>");
			}
		}
		
		UtilText.transformationContentSB.setLength(0);
		
		if (owner.isPlayer()) {
			UtilText.transformationContentSB.append(
					"<p>"
						+ "Your [pc.eyes] suddenly grow hot and itchy, and you instinctively scrunch them up tight as you reach up to rub at them.");
		} else {
			UtilText.transformationContentSB.append(
					"<p>"
						+ "[npc.Name]'s [npc.eyes] suddenly grow hot and itchy, and [npc.she] instinctively scrunches them up tight as [npc.she] reaches up to rub at them.");
		}

		// Parse existing content before transformation:
		String s = UtilText.parse(owner, UtilText.transformationContentSB.toString());
		UtilText.transformationContentSB.setLength(0);
		UtilText.transformationContentSB.append(s);
		this.type = type;
		
		switch (type) {
			case HUMAN:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" By the time you hesitantly open them again, they've changed into human eyes, with normally-proportioned irises and pupils."
								+ "</br>"
								+ "You now have [style.boldHuman(human eyes)]");
				} else {
					UtilText.transformationContentSB.append(
								" By the time [npc.she] hesitantly opens them again, they've changed into human eyes, with normally-proportioned irises and pupils."
								+ "</br>"
								+ "[npc.Name] now has [style.boldHuman(human eyes)]");
				}
				break;
			case DEMON_COMMON:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" By the time you hesitantly open them again, they've changed into demonic eyes, with smaller-than-average pupils and large irises."
								+ "</br>"
								+ "You now have [style.boldDemon(demonic eyes)]");
				} else {
					UtilText.transformationContentSB.append(
								" By the time [npc.she] hesitantly opens them again, they've changed into demonic eyes, with smaller-than-average pupils and large irises."
								+ "</br>"
								+ "[npc.Name] now has [style.boldDemon(demonic eyes)]");
				}
				break;
			case IMP:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" By the time you hesitantly open them again, they've changed into impish eyes, with smaller-than-average pupils and large irises."
								+ "</br>"
								+ "You now have [style.boldImp(impish eyes)]");
				} else {
					UtilText.transformationContentSB.append(
								" By the time [npc.she] hesitantly opens them again, they've changed into impish eyes, with smaller-than-average pupils and large irises."
								+ "</br>"
								+ "[npc.Name] now has [style.boldImp(impish eyes)]");
				}
				break;
			case COW_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" By the time you hesitantly open them again, they've changed into cow-like eyes, with larger-than-average pupils and irises."
								+ "</br>"
								+ "You now have [style.boldCowMorph(cow-like eyes)]");
				} else {
					UtilText.transformationContentSB.append(
								" By the time [npc.she] hesitantly opens them again, they've changed into cow-like eyes, with larger-than-average pupils and irises."
								+ "</br>"
								+ "[npc.Name] now has [style.boldCowMorph(cow-like eyes)]");
				}
				break;
			case DOG_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" By the time you hesitantly open them again, they've changed into dog-like eyes, with larger-than-average pupils and irises."
								+ "</br>"
								+ "You now have [style.boldDogMorph(dog-like eyes)]");
				} else {
					UtilText.transformationContentSB.append(
								" By the time [npc.she] hesitantly opens them again, they've changed into dog-like eyes, with larger-than-average pupils and irises."
								+ "</br>"
								+ "[npc.Name] now has [style.boldDogMorph(dog-like eyes)]");
				}
				break;
			case LYCAN:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" By the time you hesitantly open them again, they've changed into wolf-like eyes, with larger-than-average pupils and irises."
								+ "</br>"
								+ "You now have [style.boldWolfMorph(wolf-like eyes)]");
				} else {
					UtilText.transformationContentSB.append(
								" By the time [npc.she] hesitantly opens them again, they've changed into wolf-like eyes, with larger-than-average pupils and irises."
								+ "</br>"
								+ "[npc.Name] now has [style.boldWolfMorph(wolf-like eyes)]");
				}
				break;
			case CAT_MORPH:
				if (owner.isPlayer()) {
						UtilText.transformationContentSB.append(
								" By the time you hesitantly open them again, they've changed into cat-like eyes, with smaller-than-average pupils and large irises."
								+ "</br>"
								+ "You now have [style.boldCatMorph(cat-like eyes)]");
				} else {
					UtilText.transformationContentSB.append(
								" By the time [npc.she] hesitantly opens them again, they've changed into cat-like eyes, with smaller-than-average pupils and large irises."
								+ "</br>"
								+ "[npc.Name] now has [style.boldCatMorph(cat-like eyes)]");
				}
				break;
			case SQUIRREL_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" By the time you hesitantly open them again, they've changed into squirrel-like eyes, with larger-than-average pupils and small irises."
								+ "</br>"
								+ "You now have [style.boldSquirrelMorph(squirrel-like eyes)]");
				} else {
					UtilText.transformationContentSB.append(
								" By the time [npc.she] hesitantly opens them again, they've changed into squirrel-like eyes, with larger-than-average pupils and small irises."
								+ "</br>"
								+ "[npc.Name] now has [style.boldSquirrelMorph(squirrel-like eyes)]");
				}
				break;
			case RAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" By the time you hesitantly open them again, they've changed into rat-like eyes, with larger-than-average pupils and small irises."
								+ "</br>"
								+ "You now have [style.boldRatMorph(rat-like eyes)]");
				} else {
					UtilText.transformationContentSB.append(
								" By the time [npc.she] hesitantly opens them again, they've changed into rat-like eyes, with larger-than-average pupils and small irises."
								+ "</br>"
								+ "[npc.Name] now has [style.boldRatMorph(rat-like eyes)]");
				}
				break;
			case RABBIT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" By the time you hesitantly open them again, they've changed into rabbit-like eyes, with larger-than-average pupils and small irises."
								+ "</br>"
								+ "You now have [style.boldRabbitMorph(rabbit-like eyes)]");
				} else {
					UtilText.transformationContentSB.append(
								" By the time [npc.she] hesitantly opens them again, they've changed into rabbit-like eyes, with larger-than-average pupils and small irises."
								+ "</br>"
								+ "[npc.Name] now has [style.boldRabbitMorph(rabbit-like eyes)]");
				}
				break;
			case BAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" By the time you hesitantly open them again, they've changed into bat-like eyes, with larger-than-average pupils and small irises."
								+ "</br>"
								+ "You now have [style.boldBatMorph(bat-like eyes)]");
				} else {
					UtilText.transformationContentSB.append(
								" By the time [npc.she] hesitantly opens them again, they've changed into bat-like eyes, with larger-than-average pupils and small irises."
								+ "</br>"
								+ "[npc.Name] now has [style.boldBatMorph(bat-like eyes)]");
				}
				break;
			case ALLIGATOR_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" By the time you hesitantly open them again, they've changed into alligator-like eyes, with larger-than-average pupils and small irises."
								+ "</br>"
								+ "You now have [style.boldGatorMorph(alligator-like eyes)]");
				} else {
					UtilText.transformationContentSB.append(
								" By the time [npc.she] hesitantly opens them again, they've changed into alligator-like eyes, with larger-than-average pupils and small irises."
								+ "</br>"
								+ "[npc.Name] now has [style.boldGatorMorph(alligator-like eyes)]");
				}
				break;
			case HORSE_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" By the time you hesitantly open them again, they've changed into horse-like eyes, with larger-than-average pupils and irises."
								+ "</br>"
								+ "You now have [style.boldHorseMorph(horse-like eyes)]");
				} else {
					UtilText.transformationContentSB.append(
								" By the time [npc.she] hesitantly opens them again, they've changed into horse-like eyes, with larger-than-average pupils and irises."
								+ "</br>"
								+ "[npc.Name] now has [style.boldHorseMorph(horse-like eyes)]");
				}
				break;
			case REINDEER_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" By the time you hesitantly open them again, they've changed into reindeer-like eyes, with larger-than-average pupils and irises."
								+ "</br>"
								+ "You now have [style.boldReindeerMorph(reindeer-like eyes)]");
				} else {
					UtilText.transformationContentSB.append(
								" By the time [npc.she] hesitantly opens them again, they've changed into reindeer-like eyes, with larger-than-average pupils and irises."
								+ "</br>"
								+ "[npc.Name] now has [style.boldReindeerMorph(reindeer-like eyes)]");
				}
				break;
			case HARPY:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" By the time you hesitantly open them again, they've changed into bird-like eyes, with larger-than-average pupils and small irises."
								+ "</br>"
								+ "You now have [style.boldHarpy(harpy eyes)]");
				} else {
					UtilText.transformationContentSB.append(
								" By the time [npc.she] hesitantly opens them again, they've changed into bird-like eyes, with larger-than-average pupils and small irises."
								+ "</br>"
								+ "[npc.Name] now has [style.boldHarpy(harpy eyes)]");
				}
				break;
			case ANGEL://TODO
				break;
		}
		
		if(owner.isPlayer()) {
			UtilText.transformationContentSB.append(", with [pc.irisFullDescription(true)] and [pc.pupilFullDescription(true)]."
					+ "</p>");
		} else {
			UtilText.transformationContentSB.append(", with [npc.irisFullDescription(true)] and [npc.pupilFullDescription(true)]."
				+ "</p>");
		}
		
		return UtilText.parse(owner, UtilText.transformationContentSB.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public int getEyePairs() {
		return eyePairs;
	}

	public String setEyePairs(GameCharacter owner, int eyePairs) {
		eyePairs = Math.max(1, Math.min(eyePairs, 4));
		
		if(owner.getEyePairs() == eyePairs) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		boolean removingExtraEyes = owner.getEyePairs() > eyePairs;
		this.eyePairs = eyePairs;
		
		if(removingExtraEyes) {
			if(owner.isPlayer()) {
				return "<p>"
							+ "A tingling feeling spreads over your [pc.face], before moving up and concentrating into your [pc.eyes]."
							+ " You scrunch them shut and let out a little cry as you feel some of them [style.boldShrink(disappearing)] back into the [pc.faceSkin] above your main pair.</br>"
							+ "After a few moments, you're left with [style.boldTfGeneric([pc.a_eyes])]."
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "A tingling feeling spreads over [npc.name]'s [npc.face], before moving up and concentrating into [npc.her] [npc.eyes]."
							+ " [npc.She] scrunches them shut and lets out a little cry as [npc.she] feels some of them [style.boldShrink(disappearing)] into the [npc.faceSkin] above [npc.her] main pair.</br>"
							+ "After a few moments, [npc.she]'s left with [style.boldTfGeneric([npc.a_eyes])]."
						+ "</p>");
			}
			
		} else {
			if(owner.isPlayer()) {
				return "<p>"
							+ "A tingling feeling spreads over your [pc.face], before moving up and concentrating into your [pc.eyes]."
							+ " You scrunch them shut and let out a little cry as you feel the strange sensation of new [pc.eyes] [style.boldGrow(growing)] out of the [pc.faceSkin] above your main pair.</br>"
							+ "After a few moments, you're left with [style.boldTfGeneric([pc.a_eyes])]."
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "A tingling feeling spreads over [npc.name]'s [npc.face], before moving up and concentrating into [npc.her] [npc.eyes]."
							+ " [npc.She] scrunches them shut and lets out a little cry as [npc.she] feels the strange sensation of new [npc.eyes] [style.boldGrow(growing)] out of the [npc.faceSkin] above [npc.her] main pair.</br>"
							+ "After a few moments, [npc.she]'s left with [style.boldTfGeneric([npc.a_eyes])]."
						+ "</p>");
			}
		}
	}

	public EyeShape getIrisShape() {
		return irisShape;
	}

	public String setIrisShape(GameCharacter owner, EyeShape irisShape) {
		if(owner.getIrisShape() == irisShape) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}

		this.irisShape = irisShape;
		
		if(owner.isPlayer()) {
			return UtilText.parse(owner, 
					"<p>"
						+ "An irritable itchy feeling rises up into your [pc.eyes], but, much to your relief, it passes even before you're able to reach up and rub at them.</br>"
						+ "You now have [style.boldTfGeneric([pc.irisShape] irises)]!"
					+ "</p>");
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "An irritable itchy feeling rises up into [npc.name]'s [npc.eyes], but, much to [npc.her] relief, it passes even before [npc.she]'s able to reach up and rub at them.</br>"
						+ "[npc.Name] now has [style.boldTfGeneric([npc.irisShape] irises)]!"
					+ "</p>");
		}
	}

	public EyeShape getPupilShape() {
		return pupilShape;
	}

	public String setPupilShape(GameCharacter owner, EyeShape pupilShape) {
		if(owner.getPupilShape() == pupilShape) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}

		this.pupilShape = pupilShape;
		
		if(owner.isPlayer()) {
			return UtilText.parse(owner, 
					"<p>"
						+ "An irritable itchy feeling rises up into your [pc.eyes], but, much to your relief, it passes even before you're able to reach up and rub at them.</br>"
						+ "You now have [style.boldTfGeneric([pc.pupilShape] pupils)]!"
					+ "</p>");
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "An irritable itchy feeling rises up into [npc.name]'s [npc.eyes], but, much to [npc.her] relief, it passes even before [npc.she]'s able to reach up and rub at them.</br>"
						+ "[npc.Name] now has [style.boldTfGeneric([npc.pupilShape] pupils)]!"
					+ "</p>");
		}
	}
	
	public String setEyeCovering(GameCharacter owner, Covering covering) {
		if(owner.getCovering(owner.getEyeType().getBodyCoveringType(owner)).equals(covering)
				|| owner.getCovering(BodyCoveringType.EYE_PUPILS).equals(covering)
				|| owner.getCovering(BodyCoveringType.EYE_SCLERA).equals(covering)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		UtilText.transformationContentSB.setLength(0);
		
		owner.getBody().getCoverings().put(covering.getType(), covering);
		
		if (owner.isPlayer()) {
			UtilText.transformationContentSB.append(
					"<p>"
						+ "Your vision goes blurry for a moment as your [pc.eyes] shift and change colour.</br>"
						+ "You now have ");
			
			if(covering.getPattern() == CoveringPattern.EYE_IRISES_HETEROCHROMATIC) {
				UtilText.transformationContentSB.append("heterochromatic [pc.irisPrimaryColour(true)]-and-[pc.irisSecondaryColour(true)] [pc.irisShape] irises, ");
			} else {
				UtilText.transformationContentSB.append("[pc.irisPrimaryColour(true)] [pc.irisShape] irises ");
			}
			if(covering.getPattern() == CoveringPattern.EYE_PUPILS_HETEROCHROMATIC) {
				UtilText.transformationContentSB.append("with heterochromatic [pc.pupilPrimaryColour(true)]-and-[pc.pupilSecondaryColour(true)] [pc.pupilShape] pupils ");
			} else {
				UtilText.transformationContentSB.append("with [pc.pupilPrimaryColour(true)] [pc.pupilShape] pupils ");
			}
			if(covering.getPattern() == CoveringPattern.EYE_SCLERA_HETEROCHROMATIC) {
				UtilText.transformationContentSB.append("and heterochromatic [pc.scleraPrimaryColour(true)]-and-[pc.scleraSecondaryColour(true)] sclerae.</p>");
			} else {
				UtilText.transformationContentSB.append("and [pc.scleraPrimaryColour(true)] sclerae.</p>");
			}
			
		} else {
			UtilText.transformationContentSB.append(
					"<p>"
						+ "[npc.Name] blinks a few times as [npc.her] [npc.eyes] shift and change colour.</br>"
						+ "[npc.She] now has ");
		
			if(covering.getPattern() == CoveringPattern.EYE_IRISES_HETEROCHROMATIC) {
				UtilText.transformationContentSB.append("heterochromatic [npc.irisPrimaryColour(true)]-and-[npc.irisSecondaryColour(true)] [npc.irisShape] irises, ");
			} else {
				UtilText.transformationContentSB.append("[npc.irisPrimaryColour(true)] [npc.irisShape] irises ");
			}
			if(covering.getPattern() == CoveringPattern.EYE_PUPILS_HETEROCHROMATIC) {
				UtilText.transformationContentSB.append("with heterochromatic [npc.pupilPrimaryColour(true)]-and-[npc.pupilSecondaryColour(true)] [npc.pupilShape] pupils ");
			} else {
				UtilText.transformationContentSB.append("with [npc.pupilPrimaryColour(true)] [npc.pupilShape] pupils ");
			}
			if(covering.getPattern() == CoveringPattern.EYE_SCLERA_HETEROCHROMATIC) {
				UtilText.transformationContentSB.append("and heterochromatic [npc.scleraPrimaryColour(true)]-and-[npc.scleraSecondaryColour(true)] sclerae.</p>");
			} else {
				UtilText.transformationContentSB.append("and [npc.scleraPrimaryColour(true)] sclerae.</p>");
			}
		}
		
		return UtilText.parse(owner, UtilText.transformationContentSB.toString());
	}
}
