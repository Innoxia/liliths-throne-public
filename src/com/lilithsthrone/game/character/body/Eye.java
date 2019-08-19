package com.lilithsthrone.game.character.body;


import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.1
 * @author Innoxia
 */
public class Eye implements BodyPartInterface {

	
	public static final int MAXIMUM_PAIRS = 4;
	
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
		if(!Main.game.isStarted() || owner==null) {
			this.type = type;
			irisShape = type.getIrisShape();
			pupilShape = type.getPupilShape();
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.has] the [npc.eyes] of [npc.a_eyeRace], so nothing happens...)]</p>");
		}
		
		UtilText.transformationContentSB.setLength(0);
		
		if (owner.isPlayer()) {
			UtilText.transformationContentSB.append(
					"<p>"
						+ "Your [pc.eyes] suddenly grow hot and itchy, and you instinctively scrunch them up tight as you reach up to rub at them.");
		} else {
			UtilText.transformationContentSB.append(
					"<p>"
						+ "[npc.NamePos] [npc.eyes] suddenly grow hot and itchy, and [npc.she] instinctively scrunches them up tight as [npc.she] reaches up to rub at them.");
		}

		// Parse existing content before transformation:
		String s = UtilText.parse(owner, UtilText.transformationContentSB.toString());
		UtilText.transformationContentSB.setLength(0);
		UtilText.transformationContentSB.append(s);
		
		this.type = type;
		irisShape = type.getIrisShape();
		pupilShape = type.getPupilShape();
		
		switch (type) {
			case HUMAN:
				UtilText.transformationContentSB.append(
							" By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into human eyes, with normally-proportioned irises and pupils."
							+ "<br/>"
							+ "[npc.Name] now [npc.has] [style.boldHuman(human eyes)]");
				break;
			case DEMON_COMMON:
				if (owner.isShortStature()) {
					UtilText.transformationContentSB.append(
							" By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into impish eyes, with vertical pupils and large irises."
							+ "<br/>"
							+ "[npc.Name] now [npc.has] [style.boldImp(impish eyes)]");
				} else {
					UtilText.transformationContentSB.append(
							" By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into demonic eyes, with vertical pupils and large irises."
							+ "<br/>"
							+ "[npc.Name] now [npc.has] [style.boldDemon(demonic eyes)]");
				}
				break;
			case COW_MORPH:
				UtilText.transformationContentSB.append(
							" By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into cow-like eyes, with larger-than-average pupils and irises."
							+ "<br/>"
							+ "[npc.Name] now [npc.has] [style.boldCowMorph(cow-like eyes)]");
				break;
			case DOG_MORPH:
				UtilText.transformationContentSB.append(
							" By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into dog-like eyes, with larger-than-average pupils and irises."
							+ "<br/>"
							+ "[npc.Name] now [npc.has] [style.boldDogMorph(dog-like eyes)]");
				break;
			case FOX_MORPH:
				UtilText.transformationContentSB.append(
							" By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into fox-like eyes, with larger-than-average pupils and irises."
							+ "</br>"
							+ "[npc.Name] now [npc.has] [style.boldFoxMorph(fox-like eyes)]");
				break;
			case LYCAN:
				UtilText.transformationContentSB.append(
							" By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into wolf-like eyes, with larger-than-average pupils and irises."
							+ "<br/>"
							+ "[npc.Name] now [npc.has] [style.boldWolfMorph(wolf-like eyes)]");
				break;
			case CAT_MORPH:
				UtilText.transformationContentSB.append(
							" By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into cat-like eyes, with smaller-than-average pupils and large irises."
							+ "<br/>"
							+ "[npc.Name] now [npc.has] [style.boldCatMorph(cat-like eyes)]");
				break;
			case SQUIRREL_MORPH:
				UtilText.transformationContentSB.append(
							" By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into squirrel-like eyes, with larger-than-average pupils and small irises."
							+ "<br/>"
							+ "[npc.Name] now [npc.has] [style.boldSquirrelMorph(squirrel-like eyes)]");
				break;
			case RAT_MORPH:
				UtilText.transformationContentSB.append(
							" By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into rat-like eyes, with larger-than-average pupils and small irises."
							+ "<br/>"
							+ "[npc.Name] now [npc.has] [style.boldRatMorph(rat-like eyes)]");
				break;
			case RABBIT_MORPH:
				UtilText.transformationContentSB.append(
							" By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into rabbit-like eyes, with larger-than-average pupils and small irises."
							+ "<br/>"
							+ "[npc.Name] now [npc.has] [style.boldRabbitMorph(rabbit-like eyes)]");
				break;
			case BAT_MORPH:
				UtilText.transformationContentSB.append(
							" By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into bat-like eyes, with larger-than-average pupils and small irises."
							+ "<br/>"
							+ "[npc.Name] now [npc.has] [style.boldBatMorph(bat-like eyes)]");
				break;
			case ALLIGATOR_MORPH:
				UtilText.transformationContentSB.append(
							" By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into alligator-like eyes, with larger-than-average pupils and small irises."
							+ "<br/>"
							+ "[npc.Name] now [npc.has] [style.boldGatorMorph(alligator-like eyes)]");
				break;
			case HORSE_MORPH:
				UtilText.transformationContentSB.append(
							" By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into horse-like eyes, with larger-than-average pupils and irises."
							+ "<br/>"
							+ "[npc.Name] now [npc.has] [style.boldHorseMorph(horse-like eyes)]");
				break;
			case REINDEER_MORPH:
				UtilText.transformationContentSB.append(
							" By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into reindeer-like eyes, with larger-than-average pupils and irises."
							+ "<br/>"
							+ "[npc.Name] now [npc.has] [style.boldReindeerMorph(reindeer-like eyes)]");
				break;
			case HARPY:
				UtilText.transformationContentSB.append(
							" By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into bird-like eyes, with larger-than-average pupils and small irises."
							+ "<br/>"
							+ "[npc.Name] now [npc.has] [style.boldHarpy(harpy eyes)]");
				break;
			case ANGEL://TODO
				break;
		}
		
		UtilText.transformationContentSB.append(", with [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] and [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)]."
			+ "</p>");
		
		return UtilText.parse(owner, UtilText.transformationContentSB.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public int getEyePairs() {
		return eyePairs;
	}

	public String setEyePairs(GameCharacter owner, int eyePairs) {
		eyePairs = Math.max(1, Math.min(eyePairs, MAXIMUM_PAIRS));
		
		if(owner.getEyePairs() == eyePairs) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		boolean removingExtraEyes = owner.getEyePairs() > eyePairs;
		this.eyePairs = eyePairs;
		
		if(removingExtraEyes) {
			if(owner.isPlayer()) {
				return "<p>"
							+ "A tingling feeling spreads over your [pc.face], before moving up and concentrating into your [pc.eyes]."
							+ " You scrunch them shut and let out a little cry as you feel some of them [style.boldShrink(disappearing)] back into the [pc.faceSkin] above your main pair.<br/>"
							+ "After a few moments, you're left with [style.boldTfGeneric([pc.a_eyes])]."
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "A tingling feeling spreads over [npc.namePos] [npc.face], before moving up and concentrating into [npc.her] [npc.eyes]."
							+ " [npc.She] scrunches them shut and lets out a little cry as [npc.she] feels some of them [style.boldShrink(disappearing)] into the [npc.faceSkin] above [npc.her] main pair.<br/>"
							+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric([npc.a_eyes])]."
						+ "</p>");
			}
			
		} else {
			if(owner.isPlayer()) {
				return "<p>"
							+ "A tingling feeling spreads over your [pc.face], before moving up and concentrating into your [pc.eyes]."
							+ " You scrunch them shut and let out a little cry as you feel the strange sensation of new [pc.eyes] [style.boldGrow(growing)] out of the [pc.faceSkin] above your main pair.<br/>"
							+ "After a few moments, you're left with [style.boldTfGeneric([pc.a_eyes])]."
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "A tingling feeling spreads over [npc.namePos] [npc.face], before moving up and concentrating into [npc.her] [npc.eyes]."
							+ " [npc.She] scrunches them shut and lets out a little cry as [npc.she] feels the strange sensation of new [npc.eyes] [style.boldGrow(growing)] out of the [npc.faceSkin] above [npc.her] main pair.<br/>"
							+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric([npc.a_eyes])]."
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
		
		return UtilText.parse(owner,
				"<p>"
					+ "An irritable itchy feeling rises up into [npc.namePos] [npc.eyes], but, much to [npc.her] relief, it passes even before [npc.sheIs] able to reach up and rub at them.<br/>"
					+ "[npc.Name] now [npc.has] [style.boldTfGeneric([npc.irisShape] irises)]!"
				+ "</p>");
	}

	public EyeShape getPupilShape() {
		return pupilShape;
	}

	public String setPupilShape(GameCharacter owner, EyeShape pupilShape) {
		if(owner.getPupilShape() == pupilShape) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}

		this.pupilShape = pupilShape;
		
		return UtilText.parse(owner,
				"<p>"
					+ "An irritable itchy feeling rises up into [npc.namePos] [npc.eyes], but, much to [npc.her] relief, it passes even before [npc.sheIs] able to reach up and rub at them.<br/>"
					+ "[npc.Name] now [npc.has] [style.boldTfGeneric([npc.pupilShape] pupils)]!"
				+ "</p>");
	}
	
	public String setEyeCovering(GameCharacter owner, Covering covering) {
		if(owner.getCovering(owner.getEyeCovering()).equals(covering)
				|| owner.getCovering(BodyCoveringType.EYE_PUPILS).equals(covering)
				|| owner.getCovering(BodyCoveringType.EYE_SCLERA).equals(covering)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		UtilText.transformationContentSB.setLength(0);
		
		owner.getBody().getCoverings().put(covering.getType(), covering);
		
		if (owner.isPlayer()) {
			UtilText.transformationContentSB.append(
					"<p>"
						+ "Your vision goes blurry for a moment as your [pc.eyes] shift and change colour.<br/>"
						+ "You now have ");
		} else {
			UtilText.transformationContentSB.append(
					"<p>"
						+ "[npc.Name] blinks a few times as [npc.her] [npc.eyes] shift and change colour.<br/>"
						+ "[npc.She] now has ");
		}
		
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
		
		return UtilText.parse(owner, UtilText.transformationContentSB.toString());
	}

	@Override
	public boolean isBestial(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.getLegConfiguration().getBestialParts().contains(Eye.class) && getType().getRace().isBestialPartsAvailable();
	}
}
