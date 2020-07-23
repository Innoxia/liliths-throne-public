package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHairType;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;

/**
 * @since 0.1.0
 * @version 0.3.8.2
 * @author Innoxia
 */
public class Hair implements BodyPartInterface {

	protected AbstractHairType type;
	protected int length;
	protected HairStyle style;

	public Hair(AbstractHairType type, int length, HairStyle style) {
		this.type = type;
		this.length = length;
		this.style = style;
		
	}

	@Override
	public AbstractHairType getType() {
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
	
	public String setType(GameCharacter owner, AbstractHairType type) {
		if(!Main.game.isStarted() || owner==null) {
			this.type = type;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.has] the [npc.hair(true)] of [npc.a_hairRace], so nothing happens...)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(
				"<p>"
					+ "[npc.NamePos] scalp tingles and itches, and [npc.she] [npc.verb(rub)] the top of [npc.her] head as [npc.she] [npc.verb(feel)] [npc.her] [npc.hair(true)] start to transform. ");

		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		this.type = type;
		
		sb.append(type.getTransformationDescription(owner));
		sb.append("</p>");
		
		return UtilText.parse(owner, sb.toString())
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
			styleChange = "<p>"
							+ "[npc.Her] [npc.hair(true)] "+(owner.getHairType().isDefaultPlural()?"are":"is")+" too short for [npc.her] current hair style!"
						+ "</p>"
						+ owner.setHairStyle(HairStyle.NONE);
		}
		
		if (sizeChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The length of [npc.namePos] [npc.hair(true)] doesn't change...)]</p>");
			
		} else if (sizeChange < 0) {
			String hairChangedText;
			if (this.length == 0 && owner.isFaceBaldnessNatural()) {
				hairChangedText = "no [npc.hair(true)]";
			} else {
				hairChangedText = "[npc.hairLength], " + Units.size(this.length, Units.UnitType.LONG_SINGULAR) +" [npc.hair(true)]";
			}
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out an involuntary cry and [npc.verb(rub)] at [npc.her] scalp as [npc.she] [npc.verb(feel)] [npc.her] [npc.hair(true)] [style.boldShrink(getting shorter)].<br/>"
						+ "[npc.She] now [npc.has] [style.boldTfGeneric(" + hairChangedText + ")]!"
					+ "</p>"
					+ styleChange);
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out an involuntary cry and [npc.verb(rub)] at [npc.her] scalp as [npc.she] [npc.verb(feel)] [npc.her] [npc.hair(true)] [style.boldGrow(growing longer)].<br/>"
						+ "[npc.She] now [npc.has] [style.boldTfGeneric([npc.hairLength], "+ Units.size(this.length, Units.UnitType.LONG_SINGULAR) +" [npc.hair(true)])]!"
					+ "</p>"
					+ styleChange);
		}
	}

	public HairStyle getStyle() {
		return style;
	}
	
	public String setStyle(GameCharacter owner, HairStyle style) {
		this.style = style;
		
		if(owner==null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<p>");
			sb.append("[npc.NamePos] [npc.hair(true)] "+(type.isDefaultPlural()?"are":"is")+" now ");
			switch(style) {
				case BRAIDED:
					return "styled into braids.";
				case CURLY:
					return "curled.";
				case LOOSE:
					return "loose.";
				case NONE:
					return "unstyled, and will take on "+(type.isDefaultPlural()?"their":"its")+" natural shape.";
				case PONYTAIL:
					return "styled into a ponytail.";
				case STRAIGHT:
					return "straightened.";
				case TWIN_TAILS:
					return "styled into twin-tails.";
				case WAVY:
					return "wavy.";
				case MOHAWK:
					return "styled into a mohawk.";
				case AFRO:
					return "styled into an afro.";
				case SIDECUT:
					return "styled into a sidecut.";
				case BOB_CUT:
					return "styled into a bob cut.";
				case PIXIE:
					return "styled into a pixie cut.";
				case SLICKED_BACK:
					return "slicked back.";
				case MESSY:
					return "unstyled, and particularly messy.";
				case HIME_CUT:
					return "straightened, and styled into a hime-cut.";
				case CHONMAGE:
					return "straightened, oiled, and styled into a chonmage.";
				case DREADLOCKS:
					return "styled into dreadlocks.";
				case TOPKNOT:
					return "gathered up into a topknot.";
				case BIRD_CAGE:
					return "styled into an elaborate bird cage.";
				case TWIN_BRAIDS:
					return "styled into twin braids.";
				case DRILLS:
					return "styled into drills.";
				case LOW_PONYTAIL:
					return "styled into a low ponytail.";
				case CROWN_BRAID:
					return "styled into a crown braid.";
				case BUN:
					return "styled into a bun.";
				case CHIGNON:
					return "tied up and styled into a chignon.";
			}
		sb.append("</p>");
		return UtilText.parse(owner, sb.toString());
	}

	@Override
	public boolean isBestial(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.getLegConfiguration().getBestialParts().contains(Hair.class) && getType().getRace().isBestialPartsAvailable();
	}
}
