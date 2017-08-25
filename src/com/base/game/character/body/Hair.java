package com.base.game.character.body;

import java.io.Serializable;

import com.base.game.character.GameCharacter;
import com.base.game.character.body.types.HairType;
import com.base.game.character.body.valueEnums.HairLength;
import com.base.game.character.body.valueEnums.HairStyle;
import com.base.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public class Hair implements BodyPartInterface, Serializable {

	private static final long serialVersionUID = 1L;
	private HairType type;
	private int length;
	private HairStyle style;

	public Hair(HairType type, int length, HairStyle style) {
		this.type = type;
		this.length = length;
		this.style = style;
		
	}

	@Override
	public HairType getType() {
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
	
	public String getDescriptor(GameCharacter owner) {
		return type.getDescriptor(owner);
	}
	
	public String setType(GameCharacter owner, HairType type) {
		if (type == getType()) {
			if (owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(You already have the [pc.hair] of a [pc.hairRace], so nothing happens...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already has the [npc.hair] of [npc.a_hairRace], so nothing happens...)]</p>");
			}
			
		} else {
			UtilText.transformationContentSB.setLength(0);
			
			if (owner.isPlayer()) {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "Your scalp tingles and itches, and you rub the top of your head as you feel your [pc.hair] start to transform.");
			} else {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "[npc.Name]'s scalp tingles and itches, and [npc.she] rubs the top of [npc.her] head as [npc.she] feels [npc.her] [npc.hair] start to transform.");
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
								" The feeling goes away almost as quickly as it came, leaving you with human-like hair.</br>"
								+ "You now have [pc.hairColour] [style.boldHuman(human hair)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" The transformation only lasts a matter of moments, leaving [npc.herHim] with human-like hair.</br>"
								+ "[npc.Name] now has [npc.hairColour] [style.boldHuman(human hair)]."
							+ "</p>");
				}
				break;
			case DEMON_COMMON:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" The feeling goes away almost as quickly as it came, leaving you with silky, demonic hair.</br>"
								+ "You now have [pc.hairColour] [style.boldDemon(demonic hair)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" The transformation only lasts a matter of moments, leaving [npc.herHim] with silky, demonic hair.</br>"
								+ "[npc.Name] now has [npc.hairColour] [style.boldDemon(demonic hair)]."
							+ "</p>");
				}
				break;
			case DOG_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" The feeling goes away almost as quickly as it came, leaving you with fur-like hair.</br>"
								+ "You now have [pc.hairColour] [style.boldDogMorph(canine hair)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.</br>"
								+ "[npc.Name] now has [npc.hairColour] [style.boldDogMorph(canine hair)]."
							+ "</p>");
				}
				break;
			case LYCAN:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" The feeling goes away almost as quickly as it came, leaving you with fur-like hair.</br>"
								+ "You now have [pc.hairColour] [style.boldWolfMorph(lupine hair)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.</br>"
								+ "[npc.Name] now has [npc.hairColour] [style.boldWolfMorph(lupine hair)]."
							+ "</p>");
				}
				break;
			case CAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" The feeling goes away almost as quickly as it came, leaving you with fur-like hair.</br>"
								+ "You now have [pc.hairColour] [style.boldCatMorph(feline hair)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.</br>"
								+ "[npc.Name] now has [npc.hairColour] [style.boldCatMorph(feline hair)]."
							+ "</p>");
				}
				break;
			case HORSE_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" The feeling goes away almost as quickly as it came, leaving you with coarse, horse-like hair.</br>"
								+ "You now have [pc.hairColour] [style.boldHorseMorph(equine hair)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" The transformation only lasts a matter of moments, leaving [npc.herHim] with coarse, horse-like hair.</br>"
								+ "[npc.Name] now has [npc.hairColour] [style.boldHorseMorph(equine hair)]."
							+ "</p>");
				}
				break;
			case SQUIRREL_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" The feeling goes away almost as quickly as it came, leaving you with fur-like hair.</br>"
								+ "You now have [pc.hairColour] [style.boldSquirrelMorph(squirrel-like hair)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.</br>"
								+ "[npc.Name] now has [npc.hairColour] [style.boldSquirrelMorph(squirrel-like hair)]."
							+ "</p>");
				}
				break;
			case HARPY:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" The feeling goes away almost as quickly as it came, leaving you with a plume of feathers in place of hair.</br>"
								+ "You now have [pc.hairColour] [style.boldHarpy(harpy feathers in place of hair)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" The transformation only lasts a matter of moments, leaving [npc.herHim] with a plume of feathers in place of hair.</br>"
								+ "[npc.Name] now has [npc.hairColour] [style.boldHarpy(harpy feathers in place of hair)]."
							+ "</p>");
				}
				break;
			case ANGEL://TODO
				break;
			case SLIME://TODO
				break;
		}
		
		return UtilText.parse(owner, UtilText.transformationContentSB.toString())
				+ "</br></br>"
				+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public HairLength getLength() {
		return HairLength.getHairLengthFromInt(length);
	}

	public int getRawLengthValue() {
		return length;
	}

	/**
	 * Sets the length value. Value is bound to >=0 && <=HairLength.SEVEN_TO_FLOOR.getMaximumValue()
	 */
	public String setLength(GameCharacter owner, int length) {
		int sizeChange = 0;
		
		if (length <= 0) {
			if (this.length != 0) {
				sizeChange = 0 - this.length;
				this.length = 0;
			}
		} else if (length >= HairLength.SEVEN_TO_FLOOR.getMaximumValue()) {
			if (this.length != HairLength.SEVEN_TO_FLOOR.getMaximumValue()) {
				sizeChange = HairLength.SEVEN_TO_FLOOR.getMaximumValue() - this.length;
				this.length = HairLength.SEVEN_TO_FLOOR.getMaximumValue();
			}
		} else {
			if (this.length != length) {
				sizeChange = length - this.length;
				this.length = length;
			}
		}
		
		if(sizeChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The length of your [pc.hair] doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The length of [npc.name]'s [pc.hair] doesn't change...)]</p>");
			}
		}
		
		if(this.length > length) {
			if(owner.isPlayer()) {
				return "<p>Your scalp itches for a moment as you feel your [pc.hair] [style.boldShrink(getting shorter)].</br>"
						+ "You now have [style.boldTfGeneric([pc.hairLength], "+length+"-inch [pc.hair])]!</p>";
			} else {
				return UtilText.parse(owner, "<p>[npc.Name] lets out a little cry and rubs at [npc.her] scalp as [npc.her] [npc.hair] [style.boldShrink(gets shorter)].</br>"
						+ "[npc.She] now has [style.boldTfGeneric([npc.hairLength], "+length+"-inch [npc.hair])]!</p>");
			}
			
		} else {
			if(owner.isPlayer()) {
				return "<p>Your scalp itches for a moment as you feel your [pc.hair] [style.boldGrow(growing longer)].</br>"
						+ "You now have [style.boldTfGeneric([pc.hairLength], "+length+"-inch [pc.hair])]!</p>";
			} else {
				return UtilText.parse(owner, "<p>[npc.Name] lets out a little cry and rubs at [npc.her] scalp as [npc.her] [npc.hair] [style.boldGrow(grows longer)].</br>"
						+ "[npc.She] now has [style.boldTfGeneric([npc.hairLength], "+length+"-inch [npc.hair])]!</p>");
			}
		}
	}

	public HairStyle getStyle() {
		return style;
	}
	
	public String setStyle(GameCharacter owner, HairStyle style) {
		this.style = style;
		
		switch(style) {
			case BRAIDED:
				if(owner.isPlayer()) {
					return "<p>Your [pc.hair] "+(type.isDefaultPlural()?"are":"is")+" now styled into braids.</p>";
				} else {
					return UtilText.parse(owner, "<p>[npc.Name]'s [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now styled into braids.</p>");
				}
			case CURLY:
				if(owner.isPlayer()) {
					return "<p>Your [pc.hair] "+(type.isDefaultPlural()?"are":"is")+" now curled.</p>";
				} else {
					return UtilText.parse(owner, "<p>[npc.Name]'s [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now curled.</p>");
				}
			case LOOSE:
				if(owner.isPlayer()) {
					return "<p>Your [pc.hair] "+(type.isDefaultPlural()?"are":"is")+" now loose.</p>";
				} else {
					return UtilText.parse(owner, "<p>[npc.Name]'s [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now loose.</p>");
				}
			case NONE:
				if(owner.isPlayer()) {
					return "<p>Your [pc.hair] "+(type.isDefaultPlural()?"are":"is")+" now unstyled, and left to take on "+(type.isDefaultPlural()?"their":"its")+" natural shape.</p>";
				} else {
					return UtilText.parse(owner, "<p>[npc.Name]'s [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now unstyled, and left to take on "+(type.isDefaultPlural()?"their":"its")+" natural shape.</p>");
				}
			case PONYTAIL:
				if(owner.isPlayer()) {
					return "<p>Your [pc.hair] "+(type.isDefaultPlural()?"are":"is")+" now styled into a ponytail.</p>";
				} else {
					return UtilText.parse(owner, "<p>[npc.Name]'s [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now styled into a ponytail.</p>");
				}
			case STRAIGHT:
				if(owner.isPlayer()) {
					return "<p>Your [pc.hair] "+(type.isDefaultPlural()?"are":"is")+" now straightened.</p>";
				} else {
					return UtilText.parse(owner, "<p>[npc.Name]'s [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now straightened.</p>");
				}
			case TWIN_TAILS:
				if(owner.isPlayer()) {
					return "<p>Your [pc.hair] "+(type.isDefaultPlural()?"are":"is")+" now styled into twin-tails.</p>";
				} else {
					return UtilText.parse(owner, "<p>[npc.Name]'s [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now styled into twin-tails.</p>");
				}
			case WAVY:
				if(owner.isPlayer()) {
					return "<p>Your [pc.hair] "+(type.isDefaultPlural()?"are":"is")+" now wavy.</p>";
				} else {
					return UtilText.parse(owner, "<p>[npc.Name]'s [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now wavy.</p>");
				}
		}
		
		// Catch:
		return "";
	}
}
