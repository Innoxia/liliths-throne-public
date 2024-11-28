package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractArmType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Arm implements BodyPartInterface {

	
	public static final int MAXIMUM_ROWS = 3;
	
	protected AbstractArmType type;
	protected int armRows;
	protected BodyHair underarmHair;

	public Arm(AbstractArmType type, int armRows) {
		this.type = type;
		this.armRows = armRows;
		underarmHair = BodyHair.ZERO_NONE;
	}

	public Arm(Arm armToCopy) {
		this.type = armToCopy.type;
		this.armRows = armToCopy.armRows;
		this.underarmHair = armToCopy.underarmHair;
	}
	
	@Override
	public AbstractArmType getType() {
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
		List<String> descriptorList = new ArrayList<>();
		
		descriptorList.add(type.getDescriptor(gc));
		descriptorList.add(type.getDescriptor(gc));
		descriptorList.add(Util.randomItemFrom(gc.getBodyShape().getLimbDescriptors()));

		return Util.randomItemFrom(descriptorList);
	}

	public String setType(GameCharacter owner, AbstractArmType type) {
		if(!Main.game.isStarted() || owner==null) {
			this.type = type;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.has] the [npc.arms] of [npc.a_armRace], so nothing happens...)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();
		
		if (owner.isPlayer()) {
			sb.append(
					"<p>"
						+ "Your [pc.arms+] suddenly feel hot and itchy, and you gasp as you realise that they're starting to transform."
						+ " You let out a painful cry as your upper limbs twist and transform, leaving you powerless to do anything other than look on as they reshape themselves into a new form.");
		} else {
			sb.append(
					"<p>"
						+ "[npc.Name] fidgets about as [npc.she] feels [npc.her] [npc.arms+] growing hot and itchy, and [npc.she] gasps as [npc.she] realises that they're starting to transform."
						+ " [npc.She] lets out a painful cry as [npc.her] upper limbs twist and transform, leaving [npc.her] powerless to do anything other than look on as they reshape themselves into a new form.");
		}

		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		this.type = type;
		
		sb.append(type.getTransformationDescription(owner)+"</p>");
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public int getArmRows() {
		return armRows;
	}

	public String setArmRows(GameCharacter owner, int armRows) {
		if(!Main.game.isStarted() || owner==null) {
			armRows = Math.max(1, Math.min(armRows, MAXIMUM_ROWS));
			this.armRows = armRows;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		int currentArmRows = getArmRows();
		armRows = Math.max(1, Math.min(armRows, MAXIMUM_ROWS));
		if (armRows == currentArmRows) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		StringBuilder sb = new StringBuilder();
		
		if (armRows < currentArmRows) {
			boolean losesTwoPairs = (armRows + 2) == currentArmRows;
			if (owner.isPlayer()) {
				sb.append(
						"<p>"
							+ "You feel a strange pressure building up around the base of "
							+ (losesTwoPairs
								? "your two extra pairs"
								: (armRows == 2 
									? "the lowest of your extra pairs" 
									: "your extra pair"))
							+ " of [pc.arms], and before you can react, they rapidly shrink away into the [pc.skin] of your torso.<br/>" 
							+ "You now have [style.boldTfLesser(" + Util.intToString(armRows) + " pair"+ (armRows > 1 ? "s" : "") + " of [pc.arms])], [pc.materialDescriptor] [pc.armFullDescriptionColour]."
						+ "</p>");
			} else {
				sb.append(UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] glances worriedly down at "
							+ (losesTwoPairs
								? "[npc.her] two extra pairs"
								: (armRows == 2 
									? "the lowest of [npc.her] extra pairs" 
									: "[npc.her] extra pair"))
							+ " of [npc.arms], and before [npc.she] can react, they rapidly shrink away into the [npc.skin] of [npc.her] torso.<br/>" 
							+ "[npc.She] now has [style.boldTfLesser(" + Util.intToString(armRows) + " pair"+ (armRows > 1 ? "s" : "") + " of [npc.arms])], [npc.materialDescriptor] [npc.armFullDescriptionColour]."
						+ "</p>"));
			}
			
		} else {
			boolean gainsTwoPairs = (armRows - 2) == currentArmRows;
			if (owner.isPlayer()) {
				sb.append(
						"<p>"
							+ "You feel a strange pressure building up down the sides of your torso, and before you have time to react, "
								+ (gainsTwoPairs
									? "two extra pairs"
									: "an extra pair")
							+ " of [pc.arms] rapidly grow out of the [pc.skin] of your lower torso.<br/>"
							+ "You now have [style.boldTfLesser(" + Util.intToString(armRows) + " pair"+ (armRows > 1 ? "s" : "") + " of [pc.arms])], [pc.materialDescriptor] [pc.armFullDescriptionColour]."
						+ "</p>");
			} else {
				sb.append(UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] glances worriedly down at [npc.her] torso, and before [npc.she] can react, "
								+ (gainsTwoPairs
									? "two extra pairs"
									: "an extra pair")
							+ " of [npc.arms] rapidly grow out of the [npc.skin] of [npc.her] lower torso.<br/>"
							+ "[npc.She] now has [style.boldTfLesser(" + Util.intToString(armRows) + " pair"+ (armRows > 1 ? "s" : "") + " of [npc.arms])], [npc.materialDescriptor] [npc.armFullDescriptionColour]."
						+ "</p>"));
			}
		}
		
		this.armRows = armRows;
		
		sb.append(UtilText.parse(owner,
				"<p>"
					+ owner.postTransformationCalculation()
				+ "</p>"));
		
		return sb.toString();
	}

	public BodyHair getUnderarmHair() {
		return underarmHair;
	}

	public Covering getUnderarmHairType(GameCharacter owner) {
		return owner.getCovering(owner.getBodyHairCoveringType(owner.getArmType().getRace()));
	}

	public String setUnderarmHair(GameCharacter owner, BodyHair underarmHair) {
		if(owner==null) {
			this.underarmHair = underarmHair;
			return "";
		}

		if(!this.getType().isUnderarmHairAllowed()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(As [npc.namePos] arm type prevents [npc.herHim] from growing any underarm hair, nothing happens...)]</p>");
		}
		
		if(getUnderarmHair() == underarmHair) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		StringBuilder sb = new StringBuilder();
		
		switch(underarmHair) {
			case ZERO_NONE:
				sb.append(UtilText.parse(owner, "<p>There is no longer any trace of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in [npc.her] armpits.</p>"));
				break;
			case ONE_STUBBLE:
				sb.append(UtilText.parse(owner, "<p>[npc.Name] now [npc.has] stubbly patches of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in [npc.her] armpits.</p>"));
				break;
			case TWO_MANICURED:
				sb.append(UtilText.parse(owner, "<p>[npc.Name] now [npc.has] a well-manicured patch of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in [npc.her] armpits.</p>"));
				break;
			case THREE_TRIMMED:
				sb.append(UtilText.parse(owner, "<p>[npc.Name] now [npc.has] trimmed patches of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in [npc.her] armpits.</p>"));
				break;
			case FOUR_NATURAL:
				sb.append(UtilText.parse(owner, "<p>[npc.Name] now [npc.has] a natural amount of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in [npc.her] armpits.</p>"));
				break;
			case FIVE_UNKEMPT:
				sb.append(UtilText.parse(owner, "<p>[npc.Name] now [npc.has] an unkempt mass of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in [npc.her] armpits.</p>"));
				break;
			case SIX_BUSHY:
				sb.append(UtilText.parse(owner, "<p>[npc.Name] now [npc.has] thick, bushy masses of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in [npc.her] armpits.</p>"));
				break;
			case SEVEN_WILD:
				sb.append(UtilText.parse(owner, "<p>[npc.Name] now [npc.has] wild, bushy masses of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in [npc.her] armpits.</p>"));
				break;
		}
		
		this.underarmHair = underarmHair;

		return sb.toString();
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Arm.class) && getType().getRace().isFeralPartsAvailable());
	}
}
