package com.lilithsthrone.game.character.body;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;

/**
 * @since 0.1.0
 * @version 0.3.5.5
 * @author Innoxia
 */
public class Hair implements BodyPartInterface {

	
	protected HairType type;
	protected int length;
	protected HairStyle style;

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
		if(!Main.game.isStarted() || owner==null) {
			this.type = type;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			if (owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(You already have the [pc.hair] of [pc.a_hairRace], so nothing happens...)]</p>";
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
							+ "[npc.NamePos] scalp tingles and itches, and [npc.she] rubs the top of [npc.her] head as [npc.she] feels [npc.her] [npc.hair] start to transform.");
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
								" The feeling goes away almost as quickly as it came, leaving you with human-like hair.<br/>"
								+ "You now have [pc.hairColour] [style.boldHuman(human hair)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" The transformation only lasts a matter of moments, leaving [npc.herHim] with human-like hair.<br/>"
								+ "[npc.Name] now has [npc.hairColour] [style.boldHuman(human hair)]."
							+ "</p>");
				}
				break;
			case DEMON_COMMON:
				if (!owner.isShortStature()) {
					UtilText.transformationContentSB.append(
							" The transformation only lasts a matter of moments, leaving [npc.herHim] with silky, demonic hair.<br/>"
							+ "[npc.Name] now [npc.has] [npc.hairColour] [style.boldDemon(demonic hair)]."
						+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
							" The transformation only lasts a matter of moments, leaving [npc.herHim] with silky, impish hair.<br/>"
							+ "[npc.Name] now [npc.has] [npc.hairColour] [style.boldImp(impish hair)]."
						+ "</p>");
				}
				break;
			case DOG_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" The feeling goes away almost as quickly as it came, leaving you with fur-like hair.<br/>"
								+ "You now have [pc.hairColour] [style.boldDogMorph(canine hair)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.<br/>"
								+ "[npc.Name] now has [npc.hairColour] [style.boldDogMorph(canine hair)]."
							+ "</p>");
				}
				break;
			case FOX_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" The feeling goes away almost as quickly as it came, leaving you with fur-like hair.</br>"
								+ "You now have [pc.hairColour] [style.boldFoxMorph(vulpine hair)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.</br>"
								+ "[npc.Name] now has [npc.hairColour] [style.boldFoxMorph(vulpine hair)]."
							+ "</p>");
				}
				break;
			case LYCAN:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" The feeling goes away almost as quickly as it came, leaving you with fur-like hair.<br/>"
								+ "You now have [pc.hairColour] [style.boldWolfMorph(lupine hair)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.<br/>"
								+ "[npc.Name] now has [npc.hairColour] [style.boldWolfMorph(lupine hair)]."
							+ "</p>");
				}
				break;
			case CAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" The feeling goes away almost as quickly as it came, leaving you with fur-like hair.<br/>"
								+ "You now have [pc.hairColour] [style.boldCatMorph(feline hair)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.<br/>"
								+ "[npc.Name] now has [npc.hairColour] [style.boldCatMorph(feline hair)]."
							+ "</p>");
				}
				break;
			case CAT_MORPH_SIDEFLUFF:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" The feeling goes away almost as quickly as it came, leaving you with fur-like hair and a bunch of fluff on the sides of your face. </br>"
								+ "You now have [pc.hairColour] [style.boldCatMorph(feline hair)] with side fluff."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair and a bunch of fluff on the sides of [npc.her] face.</br>"
								+ "[npc.Name] now has [npc.hairColour] [style.boldCatMorph(feline hair)] with side fluff."
							+ "</p>");
				}
				break;
			case COW_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" The feeling goes away almost as quickly as it came, leaving you with fur-like hair.<br/>"
								+ "You now have [pc.hairColour] [style.boldCowMorph(bovine hair)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.<br/>"
								+ "[npc.Name] now has [npc.hairColour] [style.boldCowMorph(bovine hair)]."
							+ "</p>");
				}
				break;
			case ALLIGATOR_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" The feeling goes away almost as quickly as it came, leaving you with a mass of scales in place of hair.<br/>"
								+ "You now have [pc.hairColour] [style.boldGatorMorph(scales in place of hair)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" The transformation only lasts a matter of moments, leaving [npc.herHim] with a mass of scales in place of hair.<br/>"
								+ "[npc.Name] now has [npc.hairColour] [style.boldGatorMorph(scales in place of hair)]."
							+ "</p>");
				}
				break;
			case HORSE_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" The feeling goes away almost as quickly as it came, leaving you with coarse, horse-like hair.<br/>"
								+ "You now have [pc.hairColour] [style.boldHorseMorph(equine hair)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" The transformation only lasts a matter of moments, leaving [npc.herHim] with coarse, horse-like hair.<br/>"
								+ "[npc.Name] now has [npc.hairColour] [style.boldHorseMorph(equine hair)]."
							+ "</p>");
				}
				break;
			case REINDEER_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" The feeling goes away almost as quickly as it came, leaving you with coarse, reindeer-like hair.<br/>"
								+ "You now have [pc.hairColour] [style.boldReindeerMorph(rangiferine hair)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" The transformation only lasts a matter of moments, leaving [npc.herHim] with coarse, reindeer-like hair.<br/>"
								+ "[npc.Name] now has [npc.hairColour] [style.boldReindeerMorph(rangiferine hair)]."
							+ "</p>");
				}
				break;
			case SQUIRREL_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" The feeling goes away almost as quickly as it came, leaving you with fur-like hair.<br/>"
								+ "You now have [pc.hairColour] [style.boldSquirrelMorph(squirrel-like hair)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.<br/>"
								+ "[npc.Name] now has [npc.hairColour] [style.boldSquirrelMorph(squirrel-like hair)]."
							+ "</p>");
				}
				break;
			case RAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" The feeling goes away almost as quickly as it came, leaving you with fur-like hair.<br/>"
								+ "You now have [pc.hairColour] [style.boldRatMorph(rat-like hair)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.<br/>"
								+ "[npc.Name] now has [npc.hairColour] [style.boldRatMorph(rat-like hair)]."
							+ "</p>");
				}
				break;
			case RABBIT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" The feeling goes away almost as quickly as it came, leaving you with fur-like hair.<br/>"
								+ "You now have [pc.hairColour] [style.boldRabbitMorph(rabbit-like hair)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.<br/>"
								+ "[npc.Name] now has [npc.hairColour] [style.boldRabbitMorph(rabbit-like hair)]."
							+ "</p>");
				}
				break;
			case BAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" The feeling goes away almost as quickly as it came, leaving you with fur-like hair.<br/>"
								+ "You now have [pc.hairColour] [style.boldBatMorph(bat-like hair)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.<br/>"
								+ "[npc.Name] now has [npc.hairColour] [style.boldBatMorph(bat-like hair)]."
							+ "</p>");
				}
				break;
			case HARPY:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" The feeling goes away almost as quickly as it came, leaving you with a plume of feathers in place of hair.<br/>"
								+ "You now have [pc.hairColour] [style.boldHarpy(harpy feathers in place of hair)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" The transformation only lasts a matter of moments, leaving [npc.herHim] with a plume of feathers in place of hair.<br/>"
								+ "[npc.Name] now has [npc.hairColour] [style.boldHarpy(harpy feathers in place of hair)]."
							+ "</p>");
				}
				break;
			case ANGEL://TODO
				break;
		}
		
		return UtilText.parse(owner, UtilText.transformationContentSB.toString())
				+ "<p>"
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
		int oldLength = this.length;
		this.length = Math.max(0, Math.min(length, HairLength.SEVEN_TO_FLOOR.getMaximumValue()));
		int sizeChange = this.length - oldLength;
		
		if(owner==null) {
			return "";
		}
		
		String styleChange = "";
		if(this.length < owner.getHairStyle().getMinimumLengthRequired()) {
			if(owner.isPlayer()) {
				styleChange = "<p>"
								+ "Your [pc.hair] "+(owner.getHairType().isDefaultPlural()?"are":"is")+" too short for your current hair style!"
							+ "</p>"
							+ owner.setHairStyle(HairStyle.NONE);
			} else {
				styleChange = "<p>"
						+ "[npc.Her] [npc.hair] "+(owner.getHairType().isDefaultPlural()?"are":"is")+" too short for [npc.her] current hair style!"
					+ "</p>"
					+ owner.setHairStyle(HairStyle.NONE);
			}
		}
		
		if (sizeChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The length of your [pc.hair] doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The length of [npc.namePos] [npc.hair] doesn't change...)]</p>");
			}
		} else if (sizeChange < 0) {
			if(owner.isPlayer()) {
				return "<p>Your scalp itches for a moment as you feel your [pc.hair] [style.boldShrink(getting shorter)].<br/>"
						+ "You now have [style.boldTfGeneric([pc.hairLength], "
						+ Units.size(this.length, Units.UnitType.LONG_SINGULAR) +" [pc.hair])]!</p>"
						+ styleChange;
			} else {
				return UtilText.parse(owner, "<p>[npc.Name] lets out a little cry and rubs at [npc.her] scalp as [npc.her] [npc.hair] [style.boldShrink(gets shorter)].<br/>"
						+ "[npc.She] now has [style.boldTfGeneric([npc.hairLength], "
						+ Units.size(this.length, Units.UnitType.LONG_SINGULAR) +" [npc.hair])]!</p>"
						+ styleChange);
			}
			
		} else {
			if(owner.isPlayer()) {
				return "<p>Your scalp itches for a moment as you feel your [pc.hair] [style.boldGrow(growing longer)].<br/>"
						+ "You now have [style.boldTfGeneric([pc.hairLength], "
						+ Units.size(this.length, Units.UnitType.LONG_SINGULAR) +" [pc.hair])]!</p>"
						+ styleChange;
			} else {
				return UtilText.parse(owner, "<p>[npc.Name] lets out a little cry and rubs at [npc.her] scalp as [npc.her] [npc.hair] [style.boldGrow(grows longer)].<br/>"
						+ "[npc.She] now has [style.boldTfGeneric([npc.hairLength], "
						+ Units.size(this.length, Units.UnitType.LONG_SINGULAR) +" [npc.hair])]!</p>"
						+ styleChange);
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
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now styled into braids.</p>");
			case CURLY:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now curled.</p>");
			case LOOSE:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now loose.</p>");
			case NONE:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now unstyled, and will take on "+(type.isDefaultPlural()?"their":"its")+" natural shape.</p>");
			case PONYTAIL:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now styled into a ponytail.</p>");
			case STRAIGHT:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now straightened.</p>");
			case TWIN_TAILS:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now styled into twin-tails.</p>");
			case WAVY:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now wavy.</p>");
			case MOHAWK:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now styled into a mohawk.</p>");
			case AFRO:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now styled into an afro.</p>");
			case SIDECUT:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now styled into a sidecut.</p>");
			case BOB_CUT:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now styled into a bob cut.</p>");
			case PIXIE:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now styled into a pixie cut.</p>");
			case SLICKED_BACK:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now slicked back.</p>");
			case MESSY:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now unstyled, and particularly messy.</p>");
			case HIME_CUT:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now straightened, and styled into a hime-cut.</p>");
			case CHONMAGE:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now straightened, oiled, and styled into a chonmage.</p>");
			case DREADLOCKS:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now styled into dreadlocks.</p>");
			case TOPKNOT:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now gathered up into a topknot.</p>");
			case BIRD_CAGE:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now styled into an elaborate bird cage.</p>");
			case TWIN_BRAIDS:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now styled into twin braids.</p>");
			case DRILLS:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now styled into drills.</p>");
			case LOW_PONYTAIL:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now styled into a low ponytail.</p>");
			case CROWN_BRAID:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now styled into a crown braid.</p>");
			case BUN:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now styled into a bun.</p>");
			case CHIGNON:
				return UtilText.parse(owner, "<p>[npc.NamePos] [npc.hair] "+(type.isDefaultPlural()?"are":"is")+" now tied up and styled into a chignon.</p>");
		}
		
		// Catch:
		return "";
	}

	@Override
	public boolean isBestial(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.getLegConfiguration().getBestialParts().contains(Hair.class) && getType().getRace().isBestialPartsAvailable();
	}
}
