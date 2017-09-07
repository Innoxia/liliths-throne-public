package com.base.game.character.body;

import java.io.Serializable;

import com.base.game.character.GameCharacter;
import com.base.game.character.body.types.ArmType;
import com.base.game.character.body.valueEnums.BodyHair;
import com.base.game.dialogue.utils.UtilText;
import com.base.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public class Arm implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	private ArmType type;
	private int armRows;
	private BodyHair underarmHair;

	public Arm(ArmType type, int armRows) {
		this.type = type;
		this.armRows = armRows;
		underarmHair = BodyHair.NONE;
	}
	
	@Override
	public ArmType getType() {
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

	public String setType(GameCharacter owner, ArmType type) {
		if (type == getType()) {
			if (owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(You already have the [pc.arms] of [pc.a_armRace], so nothing happens...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already has the [npc.arms] of [npc.a_armRace], so nothing happens...)]</p>");
			}
			
		} else {
			UtilText.transformationContentSB.setLength(0);
			
			if (owner.isPlayer()) {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "Your [pc.arms+] suddenly feel hot and itchy, and you gasp as you realise that they're starting to transform."
							+ " You let out a painful cry as your upper limbs twist and transform, leaving you powerless to do anything other than look on as they reshape themselves into a new form.");
			} else {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "[npc.Name] fidgets about as [npc.she] feels [npc.her] [npc.arms+] growing hot and itchy, and [npc.she] gasps as [npc.she] realises that they're starting to transform."
							+ " [npc.She] lets out a painful cry as [npc.her] upper limbs twist and transform, leaving [npc.her] powerless to do anything other than look on as they reshape themselves into a new form.");
			}
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
								" Thankfully, the transformation only lasts a matter of moments, leaving you with normal-looking human arms, complete with human hands.</br>"
								+ "You now have [style.boldHuman(human arms and hands)], which are covered in [pc.armFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Thankfully for [npc.herHim], the transformation only lasts a matter of moments, leaving [npc.herHim] with normal-looking human arms, complete with human hands.</br>"
								+ "[npc.Name] now has [style.boldHuman(human arms and hands)], which are covered in [npc.armFullDescription]."
							+ "</p>");
				}
				break;
			case DEMON_COMMON:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, they've changed into slender, human-like arms, complete with human-like hands."
								+ " Despite their somewhat-normal appearance, they have a subtle, alluring quality to them that reveals their true demonic nature.</br>"
								+ "You now have [style.boldDemon(demonic arms and hands)], which are covered in [pc.armFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, they've changed into slender, human-like arms, complete with human-like hands."
								+ " Despite their somewhat-normal appearance, they have a subtle, alluring quality to them that reveals their true demonic nature.</br>"
								+ "[npc.Name] now has [style.boldDemon(demonic arms and hands)], which are covered in [npc.armFullDescription]."
							+ "</p>");
				}
				break;
			case DOG_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [pc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " you see your fur growing over the backs of your new hands as little blunt claws push out to replace your fingernails."
								+ " Your palms rapidly transform to be covered in little leathery pads, and at your upper-biceps, your new fur smoothly transitions into the [pc.skin] that's covering the rest of your body.</br>"
								+ "As the transformation comes to an end, you're left with anthropomorphic, [style.boldDogMorph(dog-like arms and hands)], which are covered in [pc.armFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " [npc.she] sees [npc.her] fur growing over the backs of [npc.her] hands as little blunt claws push out to replace [npc.her] fingernails."
								+ " [npc.Her] palms rapidly transform to be covered in little leathery pads, and at [npc.her] upper-biceps, [npc.her] new fur smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.</br>"
								+ "As the transformation comes to an end, [npc.name] is left with anthropomorphic, [style.boldDogMorph(dog-like arms and hands)], which are covered in [npc.armFullDescription]."
							+ "</p>");
				}
				break;
			case LYCAN:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [pc.armFullDescription] has quickly grown over them, and, looking down, you see your fur growing over the backs of your new hands as sharp claws push out to replace your fingernails."
								+ " Your palms rapidly transform to be covered in tough leathery pads, and at your upper-biceps, your new fur smoothly transitions into the [pc.skin] that's covering the rest of your body.</br>"
								+ "As the transformation comes to an end, you're left with anthropomorphic, [style.boldWolfMorph(wolf-like arms and hands)], which are covered in [pc.armFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " [npc.she] sees [npc.her] fur growing over the backs of [npc.her] hands as sharp claws push out to replace [npc.her] fingernails."
								+ " [npc.Her] palms rapidly transform to be covered in tough leathery pads, and at [npc.her] upper-biceps, [npc.her] new fur smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.</br>"
								+ "As the transformation comes to an end, [npc.name] is left with anthropomorphic, [style.boldWolfMorph(wolf-like arms and hands)], which are covered in [npc.armFullDescription]."
							+ "</p>");
				}
				break;
			case CAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [pc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " you see your fur growing over the backs of your new hands as sharp, retractable claws push out to replace your fingernails."
								+ " Your palms rapidly transform to be covered in little pink pads, and at your upper-biceps, your new fur smoothly transitions into the [pc.skin] that's covering the rest of your body.</br>"
								+ "As the transformation comes to an end, you're left with anthropomorphic, [style.boldCatMorph(cat-like arms and hands)], which are covered in [pc.armFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " [npc.she] sees [npc.her] fur growing over the backs of [npc.her] hands as sharp, retractable claws push out to replace [npc.her] fingernails."
								+ " [npc.Her] palms rapidly transform to be covered in little pink pads, and at [npc.her] upper-biceps, [npc.her] new fur smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.</br>"
								+ "As the transformation comes to an end, [npc.name] is left with anthropomorphic, [style.boldCatMorph(cat-like arms and hands)], which are covered in [npc.armFullDescription]."
							+ "</p>");
				}
				break;
			case HORSE_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [pc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " you see your hair growing over the backs of your new hands as tough, hoof-like nails push out in place of regular, human-like ones."
								+ " Despite their appearance, you're relieved to discover that your hands have lost none of their dexterity."
								+ " As the transformation comes to an end, you see that at your upper-biceps, your new hair smoothly transitions into the [pc.skin] that's covering the rest of your body.</br>"
								+ "You're left with anthropomorphic, [style.boldHorseMorph(horse-like arms and hands)], which are covered in [pc.armFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [pc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " [npc.she] sees [npc.her] hair growing over the backs of [npc.her] new hands as tough, hoof-like nails push out in place of regular, human-like ones."
								+ " Despite their appearance, [npc.she]'s relieved to discover that [npc.her] hands have lost none of their dexterity."
								+ " As the transformation comes to an end, [npc.she] sees that at [npc.her] upper-biceps, [npc.her] new hair smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.</br>"
								+ "[npc.Name] is left with anthropomorphic, [style.boldHorseMorph(horse-like arms and hands)], which are covered in [npc.armFullDescription]."
							+ "</p>");
				}
				break;
			case SQUIRREL_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [pc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " you see your fur growing over the backs of your new hands as sharp little claws push out to replace your fingernails."
								+ " Your palms rapidly transform to be covered in little pink pads, and at your upper-biceps, your new fur smoothly transitions into the [pc.skin] that's covering the rest of your body.</br>"
								+ "As the transformation comes to an end, you're left with anthropomorphic, [style.boldSquirrelMorph(squirrel-like arms and hands)], which are covered in [pc.armFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " [npc.she] sees [npc.her] fur growing over the backs of [npc.her] hands as sharp little claws push out to replace [npc.her] fingernails."
								+ " [npc.Her] palms rapidly transform to be covered in little pink pads, and at [npc.her] upper-biceps, [npc.her] new fur smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.</br>"
								+ "As the transformation comes to an end, [npc.name] is left with anthropomorphic, [style.boldSquirrelMorph(squirrel-like arms and hands)], which are covered in [npc.armFullDescription]."
							+ "</p>");
				}
				break;
			case HARPY:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [pc.armFullDescription] quickly sprout out all over them, and, looking down, you see your feathers growing over the backs of your hands as well."
								+ " Just as you think that the transformation has finished, you cry out in shock as you feel your bones growing and snapping into a new form."
								+ " Thankfully, the transformation is quickly over, leaving you with a pair of huge, feathered wings in place of arms."
								+ " Where your hands once were, your fingers have shrunk down into the middle-joint of your new appendages."
								+ " All that's left is a feathered opposable thumb, which ends in a blunt claw."
								+ " By folding your wings back onto themselves, you can thankfully still use your thumb to grasp and manipulate objects."
								+ " Where your new wings meet your body at the shoulder, your feathers smoothly cover the transition into the [pc.skin] that's covering the rest of your torso.</br>"
								+ "You now have huge [style.boldHarpy(harpy wings)] in place of arms, which are covered in [pc.armFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [npc.armFullDescription] quickly sprout out all over them, and, looking down, [npc.she] sees [npc.her] feathers growing over the backs of [npc.her] hands as well."
								+ " Just as [npc.she] thinks that the transformation has finished, [npc.she] cries out in shock as [npc.her] bones grow and snap into a new form."
								+ " Thankfully for [npc.herHim], the transformation is quickly over, leaving [npc.herHim] with a pair of huge, feathered wings in place of arms."
								+ " Where [npc.her] hands once were, [npc.her] fingers have shrunk down into the middle-joint of your [npc.her] appendages."
								+ " All that's left is a feathered opposable thumb, which ends in a blunt claw."
								+ " By folding [npc.her] wings back onto themselves, [npc.she] can thankfully still use [npc.her] thumb to grasp and manipulate objects."
								+ " Where [npc.her] new wings meet [npc.her] body at the shoulder, [npc.her] feathers smoothly cover the transition into the [npc.skin] that's covering the rest of [npc.her] torso.</br>"
								+ "[npc.Name] now has huge [style.boldHarpy(harpy wings)] in place of arms, which are covered in [npc.armFullDescription]."
							+ "</p>");
				}
				break;
			default:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [pc.armFullDescription] has quickly grown over them, and, looking down, you see that your arms have transformed into a new form.</br>"
								+ "As the transformation comes to an end, you're left with [style.boldTfLesser([pc.arms+])], which are covered in [pc.armFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down, [npc.she] sees that [npc.her] arms have transformed into a new form.</br>"
								+ "As the transformation comes to an end, [npc.name] is left with [style.boldTfLesser([npc.arms+])], which are covered in [npc.armFullDescription]."
							+ "</p>");
				}
		}
		return UtilText.parse(owner, UtilText.transformationContentSB.toString())
				+ "</br></br>"
				+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public int getArmRows() {
		return armRows;
	}

	public String setArmRows(GameCharacter owner, int armRows) {
		
		if(armRows == getArmRows()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		if(armRows<=0) {
			armRows = 1;
		} else if (armRows>3) {
			armRows=3;
		}
		
		UtilText.transformationContentSB.setLength(0);
		
		if (armRows < getArmRows()) {
			if (owner.isPlayer()) {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "You feel a strange pressure building up around the base of "
							+ (getArmRows() == 3
								? (getArmRows() == 2 ? "the lowest of your extra pairs"
									: "your two extra pairs")
								: "your extra pair")
							+ " of [pc.arms], and before you can react, they rapidly shrink away into the [pc.skin] of your torso.</br>" 
							+ "You now have [style.boldTfLesser(" + Util.intToString(armRows) + " pair"+ (armRows > 1 ? "s" : "") + " of [pc.arms])], covered in [pc.armFullDescriptionColour]."
						+ "</p>");
			} else {
				UtilText.transformationContentSB.append(UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] glances worriedly down at "
							+ (getArmRows() == 3
								? (getArmRows() == 2 ? "the lowest of [npc.her] extra pair"
									: "[npc.her] two extra pairs")
								: "[npc.her] extra pair")
							+ " of [npc.arms], and before [npc.she] can react, they rapidly shrink away into the [npc.skin] of [npc.her] torso.</br>" 
							+ "[npc.She] now has [style.boldTfLesser(" + Util.intToString(armRows) + " pair"+ (armRows > 1 ? "s" : "") + " of [npc.arms])], covered in [npc.armFullDescriptionColour]."
						+ "</p>"));
			}
			
		} else if (armRows > getArmRows()) {
			if (owner.isPlayer()) {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "You feel a strange pressure building up down the sides of your torso, and before you have time to react, "
								+ (getArmRows() == 1
									? (getArmRows() == 3
										? "two extra pairs"
										: "an extra pair")
									: "an extra pair")
							+ " of [pc.arms] rapidly grow out of the [pc.skin] of your lower torso.</br>"
							+ "You now have [style.boldTfLesser(" + Util.intToString(armRows) + " pair"+ (armRows > 1 ? "s" : "") + " of [pc.arms])], covered in [pc.armFullDescriptionColour]."
						+ "</p>");
			} else {
				UtilText.transformationContentSB.append(UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] glances worriedly down at [npc.her] torso, and before [npc.she] can react, "
								+ (getArmRows() == 1
									? (getArmRows() == 3
										? "two extra pairs"
										: "an extra pair")
									: "an extra pair")
							+ " of [npc.arms] rapidly grow out of the [npc.skin] of [npc.her] lower torso.</br>"
							+ "[npc.She] now has [style.boldTfLesser(" + Util.intToString(armRows) + " pair"+ (armRows > 1 ? "s" : "") + " of [npc.arms])], covered in [npc.armFullDescriptionColour]."
						+ "</p>"));
			}
		}
		
		if (armRows <= 0) {
			this.armRows = 1;
		} else if (armRows > 3) {
			this.armRows = 3;
		} else {
			this.armRows = armRows;
		}
		
		return UtilText.transformationContentSB.toString();
	}

	public BodyHair getUnderarmHair() {
		return underarmHair;
	}

	public Covering getUnderarmHairType(GameCharacter owner) {
		return owner.getCovering(owner.getBodyHairCoveringType(owner.getArmType().getRace()));
	}

	public String setUnderarmHair(GameCharacter owner, BodyHair underarmHair) {
		
		if(getUnderarmHair() == underarmHair) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
			
		} else {
			UtilText.transformationContentSB.setLength(0);
			
			switch(underarmHair) {
				case NONE:
					if(owner.isPlayer()) {
						UtilText.transformationContentSB.append("<p>There is no longer any trace of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in your armpits.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner, "<p>There is no longer any trace of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in [npc.her] armpits.</p>"));
					}
					break;
				case MANICURED:
					if(owner.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a well-manicured patch of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in your armpits.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner, "<p>[npc.Name] now has a well-manicured patch of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in [npc.her] armpits.</p>"));
					}
					break;
				case TRIMMED:
					if(owner.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a trimmed patch of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in your armpits.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner, "<p>[npc.Name] now has a trimmed patch of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in [npc.her] armpits.</p>"));
					}
					break;
				case BUSHY:
					if(owner.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a thick mass of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in your armpits.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner, "<p>[npc.Name] now has a thick mass of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in [npc.her] armpits.</p>"));
					}
					break;
			}
		}
		
		this.underarmHair = underarmHair;

		return UtilText.transformationContentSB.toString();
	}
}
