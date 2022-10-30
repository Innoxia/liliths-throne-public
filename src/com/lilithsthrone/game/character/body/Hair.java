package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHairType;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;

/**
 * @since 0.1.0
 * @version 0.4.2
 * @author Innoxia
 */
public class Hair implements BodyPartInterface {

	protected AbstractHairType type;
	protected int length;
	protected HairStyle style;
	protected boolean neckFluff;

	public Hair(AbstractHairType type, int length, HairStyle style, RaceStage ownerRaceStage) {
		this.type = type;
		this.length = length;
		this.style = style;
		
		neckFluff = false;
		if((!type.isNeckFluffRequiresGreater() || ownerRaceStage!=RaceStage.GREATER) && Math.random()<type.getNeckFluffChance()) {
			neckFluff = true;
		}
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
							+ "[npc.Her] [npc.hair(true)] "+(owner.getHairType().isDefaultPlural(owner)?"are":"is")+" too short for [npc.her] current hair style!"
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
			sb.append("[npc.NamePos] [npc.hair(true)] "+(type.isDefaultPlural(owner)?"are":"is")+" now ");
			switch(style) { //TODO This should be handled in the style itself
				case BRAIDED:
					sb.append("styled into braids.");
					break;
				case CURLY:
					sb.append("curled.");
					break;
				case LOOSE:
					sb.append("loose.");
					break;
				case NONE:
					sb.append("unstyled, and will take on "+(type.isDefaultPlural(owner)?"their":"its")+" natural shape.");
					break;
				case PONYTAIL:
					sb.append("styled into a ponytail.");
					break;
				case STRAIGHT:
					sb.append("straightened.");
					break;
				case TWIN_TAILS:
					sb.append("styled into twin-tails.");
					break;
				case WAVY:
					sb.append("wavy.");
					break;
				case MOHAWK:
					sb.append("styled into a mohawk.");
					break;
				case AFRO:
					sb.append("styled into an afro.");
					break;
				case SIDECUT:
					sb.append("styled into a sidecut.");
					break;
				case BOB_CUT:
					sb.append("styled into a bob cut.");
					break;
				case PIXIE:
					sb.append("styled into a pixie cut.");
					break;
				case SLICKED_BACK:
					sb.append("slicked back.");
					break;
				case MESSY:
					sb.append("unstyled, and particularly messy.");
					break;
				case HIME_CUT:
					sb.append("straightened, and styled into a hime-cut.");
					break;
				case CHONMAGE:
					sb.append("straightened, oiled, and styled into a chonmage.");
					break;
				case DREADLOCKS:
					sb.append("styled into dreadlocks.");
					break;
				case TOPKNOT:
					sb.append("gathered up into a topknot.");
					break;
				case BIRD_CAGE:
					sb.append("styled into an elaborate bird cage.");
					break;
				case TWIN_BRAIDS:
					sb.append("styled into twin braids.");
					break;
				case DRILLS:
					sb.append("styled into ojou ringlets.");
					break;
				case LOW_PONYTAIL:
					sb.append("styled into a low ponytail.");
					break;
				case CROWN_BRAID:
					sb.append("styled into a crown braid.");
					break;
				case BUN:
					sb.append("styled into a bun.");
					break;
				case CHIGNON:
					sb.append("tied up and styled into a chignon.");
					break;
				case SIDE_BRAIDS:
					sb.append("styled into two braids which hang down on either side of [npc.her] face.");
					break;
			}
		sb.append("</p>");
		return UtilText.parse(owner, sb.toString());
	}

	public boolean isNeckFluff() {
		return neckFluff;
	}

	public String setNeckFluff(GameCharacter owner, boolean neckFluff) {
		if(owner!=null && this.neckFluff == neckFluff) {
			if(this.neckFluff) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.has] neck [npc.hair(true)], so nothing happens...)]</p>");
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.verb(lack)] neck [npc.hair(true)], so nothing happens...)]</p>");
			}
		}
		this.neckFluff = neckFluff;
		if(owner==null) {
			return "";
		}
		
		if (neckFluff) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a gasp as [npc.she] [npc.verb(feel)] a significant amount of [npc.hair(true)] [style.boldGrow(growing out around [npc.her] neck and upper chest)]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a gasp as [npc.she] [npc.verb(feel)] [npc.her] neck [npc.hair(true)] [style.boldShrink(shrink away and disappear)]."
					+ "</p>");
		}
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Hair.class) && getType().getRace().isFeralPartsAvailable());
	}
}
