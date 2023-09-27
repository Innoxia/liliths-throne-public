package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEyeType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.0
 * @version 0.3.7
 * @author Innoxia
 */
public class Eye implements BodyPartInterface {

	public static final int MAXIMUM_PAIRS = 4;
	
	protected AbstractEyeType type;
	protected int eyePairs;
	protected EyeShape irisShape, pupilShape;
	
	public Eye(AbstractEyeType type) {
		this.type = type;
		eyePairs = type.getDefaultPairCount();
		irisShape = type.getDefaultIrisShape();
		pupilShape = type.getDefaultPupilShape();
	}

	@Override
	public AbstractEyeType getType() {
		return type;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		return type.getDeterminer(gc);
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
	
	public String setType(GameCharacter owner, AbstractEyeType type) {
		if(!Main.game.isStarted() || owner==null) {
			this.type = type;
			irisShape = type.getDefaultIrisShape();
			pupilShape = type.getDefaultPupilShape();
			if(owner!=null) {
				owner.resetAreaKnownByCharacters(CoverableArea.EYES);
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.has] the [npc.eyes] of [npc.a_eyeRace], so nothing happens...)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();

		sb.append("<p>");
		if(owner.isArmMovementHindered()) {
			sb.append("[npc.NamePos] [npc.eyes] suddenly grow hot and itchy, causing [npc.herHim] to instinctively scrunch them up tight. ");
		} else {
			sb.append("[npc.NamePos] [npc.eyes] suddenly grow hot and itchy, and [npc.she] instinctively [npc.verb(scrunch)] them up tight as [npc.she] [npc.verb(reach)] up to rub at them. ");
		}

		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		
		this.type = type;
		irisShape = type.getDefaultIrisShape();
		pupilShape = type.getDefaultPupilShape();
		owner.resetAreaKnownByCharacters(CoverableArea.EYES);

		sb.append(type.getTransformationDescription(owner));
		
		sb.append("</p>");
		
		return UtilText.parse(owner, sb.toString())
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
			return UtilText.parse(owner,
					"<p>"
						+ "A tingling feeling spreads over [npc.namePos] [npc.face], before moving up and concentrating in [npc.her] [npc.eyes]."
						+ " [npc.She] [npc.verb(scrunch)] them shut and [npc.verb(let)] out an involuntary cry as [npc.she] [npc.verb(feel)] some of them [style.boldShrink(disappearing)] into the [npc.faceSkin] above [npc.her] main pair.<br/>"
						+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric([npc.a_eyes])]."
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "A tingling feeling spreads over [npc.namePos] [npc.face], before moving up and concentrating in [npc.her] [npc.eyes]."
						+ " [npc.She] [npc.verb(scrunch)] them shut and [npc.verb(let)] out an involuntary cry as [npc.she] [npc.verb(feel)] the alarming sensation"
							+ " of new [npc.eyes] [style.boldGrow(growing)] out of the [npc.faceSkin] above [npc.her] main pair.<br/>"
						+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric([npc.a_eyes])]."
					+ "</p>");
		}
	}

	public EyeShape getIrisShape() {
		return irisShape;
	}

	public String setIrisShape(GameCharacter owner, EyeShape irisShape) {
		if(owner==null) {
			this.irisShape = irisShape;
			return "";
		}
		if(owner.getIrisShape() == irisShape) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}

		this.irisShape = irisShape;
		
		return UtilText.parse(owner,
				"<p>"
					+ "An irritable itchiness suddenly breaks out around [npc.namePos] [npc.eyes], but it passes so quickly that [npc.she] [npc.do]n't even have time to reach up and rub at them.<br/>"
					+ "[npc.Name] now [npc.has] [style.boldTfGeneric([npc.irisShape] irises)]!"
				+ "</p>");
	}

	public EyeShape getPupilShape() {
		return pupilShape;
	}

	public String setPupilShape(GameCharacter owner, EyeShape pupilShape) {
		if(owner==null) {
			this.pupilShape = pupilShape;
			return "";
		}
		if(owner.getPupilShape() == pupilShape) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}

		this.pupilShape = pupilShape;
		
		return UtilText.parse(owner,
				"<p>"
					+ "An irritable itchiness suddenly breaks out around [npc.namePos] [npc.eyes], but it passes so quickly that [npc.she] [npc.do]n't even have time to reach up and rub at them.<br/>"
					+ "[npc.Name] now [npc.has] [style.boldTfGeneric([npc.pupilShape] pupils)]!"
				+ "</p>");
	}
	
	public String setEyeCovering(GameCharacter owner, Covering covering) {
		if(owner.getCovering(owner.getEyeCovering()).equals(covering)
				|| owner.getCovering(BodyCoveringType.EYE_PUPILS).equals(covering)
				|| owner.getCovering(BodyCoveringType.EYE_SCLERA).equals(covering)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		StringBuilder sb = new StringBuilder();
		
		owner.getBody().getCoverings().put(covering.getType(), covering);
		
		sb.append(
				"<p>"
					+ "[npc.NamePos] vision goes blurry for just a moment, and after blinking a few times, [npc.her] [npc.eyes] suddenly shift and change colour.<br/>"
					+ "[npc.She] now [npc.has] ");
		
		if(covering.getPattern() == CoveringPattern.EYE_IRISES_HETEROCHROMATIC) {
			sb.append("heterochromatic [npc.irisPrimaryColour(true)]-and-[npc.irisSecondaryColour(true)] [npc.irisShape] irises, ");
		} else {
			sb.append("[npc.irisPrimaryColour(true)] [npc.irisShape] irises ");
		}
		if(covering.getPattern() == CoveringPattern.EYE_PUPILS_HETEROCHROMATIC) {
			sb.append("with heterochromatic [npc.pupilPrimaryColour(true)]-and-[npc.pupilSecondaryColour(true)] [npc.pupilShape] pupils ");
		} else {
			sb.append("with [npc.pupilPrimaryColour(true)] [npc.pupilShape] pupils ");
		}
		if(covering.getPattern() == CoveringPattern.EYE_SCLERA_HETEROCHROMATIC) {
			sb.append("and heterochromatic [npc.scleraPrimaryColour(true)]-and-[npc.scleraSecondaryColour(true)] sclerae.</p>");
		} else {
			sb.append("and [npc.scleraPrimaryColour(true)] sclerae.</p>");
		}
		
		return UtilText.parse(owner, sb.toString());
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Eye.class) && getType().getRace().isFeralPartsAvailable());
	}
}
