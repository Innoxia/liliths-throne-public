package com.lilithsthrone.game.character.body;

import java.io.Serializable;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public class Antenna implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	protected AntennaType type;
	protected int rows;
	
	public Antenna(AntennaType type) {
		this.type = type;
	}

	@Override
	public AntennaType getType() {
		return type;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc.getAntennaRows()==1) {
			return "a pair of";
		} else {
			return Util.intToString(gc.getAntennaRows())+" pairs of";
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

	public String setType(GameCharacter owner, AntennaType type) {
		if (type == getType()) {
			if(type == AntennaType.NONE) {
				if(owner.isPlayer()) {
					return "<p style='text-align:center;'>[style.colourDisabled(You already lack antennae, so nothing happens...)]</p>";
				} else {
					return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already lacks antennae, so nothing happens...)]</p>");
				}
			} else {
				if(owner.isPlayer()) {
					return "<p style='text-align:center;'>[style.colourDisabled(You already have the [pc.antennae] of [pc.a_antennaRace], so nothing happens...)]</p>";
				} else {
					return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already has the [npc.antennae] of [npc.a_antennaRace], so nothing happens...)]</p>");
				}
			}
		}
		
		UtilText.transformationContentSB.setLength(0);
		
		if (owner.isPlayer()) {
			if(this.type == AntennaType.NONE) {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "You rub at your forehead as it starts to feel hot and sensitive, and as you do, something starts pushing out from under your [pc.faceSkin].");
			} else {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "You feel an odd tingling sensation at the base of your [pc.antennae], and you gasp as you feel them start to transform.");
			}
			
		} else {
			if(this.type == AntennaType.NONE) {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "[npc.Name] rubs at [npc.her] forehead as [npc.she] feels it growing hot and sensitive, and as [npc.she] does so, something starts pushing out from under [npc.her] [npc.faceSkin].");
			} else {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "[npc.Name] feels an odd tingling sensation at the base of [npc.her] [npc.antennae], and [npc.she] gasps as [npc.she] feels them start to transform.");
			}
		}
		
		switch (type) {
			case NONE:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" Pieces of your [pc.antennae] fall to the floor as they start to crumble away, and within moments they've completely disappeared."
							+ "<br/>"
							+ "You now have [style.boldTfGeneric(no antennae)].");
				} else {
					UtilText.transformationContentSB.append(
								" Pieces of [npc.her] [npc.antennae] fall to the floor as they start to crumble away, and within moments they've completely disappeared."
								+ "<br/>"
								+ "[npc.Name] now has [style.boldTfGeneric(no antennae)].");
				}
				break;
		}
		
		// Parse existing content before transformation:
		String s = UtilText.parse(owner, UtilText.transformationContentSB.toString());
		UtilText.transformationContentSB.setLength(0);
		UtilText.transformationContentSB.append(s);
		this.type = type;
		
		return UtilText.parse(owner, UtilText.transformationContentSB.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}
	
	public int getAntennaRows() {
		return rows;
	}

	public String setAntennaRows(GameCharacter owner, int rows) {
		rows = Math.max(1, Math.min(rows, 3));
		
		if(owner.getAntennaRows() == rows) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		this.rows = rows;
		
		if (owner.getAntennaType() == AntennaType.NONE) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		if(owner.getAntennaRows() > rows) {
			if(owner.isPlayer()) {
				return
						"<p>"
							+ "A tingling feeling spreads over your [pc.antennae], before moving down and concentrating into your forehead."
							+ " You can't help but let out a little cry as you feel some of them [style.boldShrink(falling away)] and disappearing back down into your [pc.faceSkin].<br/>"
							+ "After a few moments, you're left with [style.boldTfGeneric("+getDeterminer(owner)+" [pc.antennae])]."
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "A tingling feeling spreads over [npc.namePos] [npc.antennae], before moving down and concentrating into [npc.her] forehead."
							+ " [npc.She] can't help but let out a little cry as [npc.she] feels some of them [style.boldShrink(falling away)] and disappearing back down into [npc.her] [npc.faceSkin].<br/>"
							+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric("+getDeterminer(owner)+" [npc.antennae])]."
						+ "</p>");
			}
			
		} else {
			if(owner.isPlayer()) {
				return
						"<p>"
							+ "A tingling feeling spreads over your [pc.antennae], before moving down and concentrating into your forehead."
							+ " You can't help but let out a little cry as you feel new [pc.horns] [style.boldGrow(pushing up)] and growing out of your [pc.faceSkin].<br/>"
							+ "After a few moments, you're left with [style.boldTfGeneric("+getDeterminer(owner)+" [pc.antennae])]."
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "A tingling feeling spreads over [npc.namePos] [npc.antennae], before moving down and concentrating into [npc.her] forehead."
							+ " [npc.She] can't help but let out a little cry as [npc.she] feels new [npc.horns] [style.boldGrow(pushing up)] and growing out of [npc.her] [npc.faceSkin].<br/>"
							+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric("+getDeterminer(owner)+" [npc.antennae])]."
						+ "</p>");
			}
		}
	}

}
