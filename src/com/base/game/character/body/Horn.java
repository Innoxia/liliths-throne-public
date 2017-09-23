package com.base.game.character.body;

import java.io.Serializable;

import com.base.game.character.GameCharacter;
import com.base.game.character.body.types.HornType;
import com.base.game.dialogue.utils.UtilText;
import com.base.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public class Horn implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	private HornType type;
	private int rows;
	
	public Horn(HornType type) {
		this.type = type;
		rows = 1;
	}

	@Override
	public HornType getType() {
		return type;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc.getHornRows()==1) {
			return "a pair of";
		} else {
			return Util.intToString(gc.getHornRows())+" pairs of";
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

	public String setType(GameCharacter owner, HornType type) {
		if (type == getType()) {
			if(type == HornType.NONE) {
				if(owner.isPlayer()) {
					return "<p style='text-align:center;'>[style.colourDisabled(You already lack horns, so nothing happens...)]</p>";
				} else {
					return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already lacks horns, so nothing happens...)]</p>");
				}
			} else {
				if(owner.isPlayer()) {
					return "<p style='text-align:center;'>[style.colourDisabled(You already have the [pc.horns] of [pc.a_hornRace], so nothing happens...)]</p>";
				} else {
					return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already has the [npc.horns] of [npc.a_hornRace], so nothing happens...)]</p>");
				}
			}
		}
		
		UtilText.transformationContentSB.setLength(0);
		
		if (owner.isPlayer()) {
			if(this.type == HornType.NONE) {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "You rub at your forehead as it starts to feel hot and sensitive, and as you do, something starts pushing out from under your [pc.faceSkin].");
			} else {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "You feel an odd tingling sensation at the base of your [pc.horns], and you gasp as you feel them start to transform.");
			}
			
		} else {
			if(this.type == HornType.NONE) {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "[npc.Name] rubs at [npc.her] forehead as [npc.she] feels it growing hot and sensitive, and as [npc.she] does so, something starts pushing out from under [npc.her] [npc.faceSkin].");
			} else {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "[npc.Name] feels an odd tingling sensation at the base of [npc.her] [npc.horns], and [npc.she] gasps as [npc.she] feels them start to transform.");
			}
		}
		
		switch (type) {
			case DEMON_COMMON_FEMALE:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" Hard nubs push out from your upper forehead, and you gasp as you feel "+getDeterminer(owner)+" long, sleek horns pushing out to sweep back across the sides of your head."
							+ "</br>"
							+ "You now have "+getDeterminer(owner)+" [style.boldDemon(feminine-looking demonic horns)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Hard nubs push out from [npc.her] upper forehead, and [npc.she] gasps as [npc.she] feels "+getDeterminer(owner)+" long, sleek horns pushing out to sweep back across the sides of [npc.her] head."
								+ "</br>"
								+ "[npc.Name] now has "+getDeterminer(owner)+" [style.boldDemon(feminine-looking demonic horns)]."
								+ "</p>");
				}
				break;
			case DEMON_COMMON_MALE:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" Hard nubs push out from your upper forehead, and you gasp as you feel "+getDeterminer(owner)+" short, ridged horns push out and slightly bend back over your head."
							+ "</br>"
							+ "You now have "+getDeterminer(owner)+" [style.boldDemon(masculine-looking demonic horns)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Hard nubs push out from [npc.her] upper forehead, and [npc.she] gasps as [npc.she] feels "+getDeterminer(owner)+" short, ridged horns push out and slightly bend back over [npc.her] head."
								+ "</br>"
								+ "[npc.Name] now has "+getDeterminer(owner)+" [style.boldDemon(masculine-looking demonic horns)]."
								+ "</p>");
				}
				break;
			case BOVINE_FEMALE:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" Hard nubs push out from the sides of your head, and you gasp as you feel them quickly grow out into "+getDeterminer(owner)+" short, sleek horns, much like a cow's."
							+ "</br>"
							+ "You now have "+getDeterminer(owner)+" [style.boldCowMorph(feminine-looking bovine horns)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Hard nubs push out from the sides of [npc.her] head, and [npc.she] gasps as they quickly grow out into "+getDeterminer(owner)+" short, sleek horns, much like a cow's."
								+ "</br>"
								+ "[npc.Name] now has "+getDeterminer(owner)+" [style.boldCowMorph(feminine-looking bovine horns)]."
								+ "</p>");
				}
				break;
			case BOVINE_MALE:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" Hard nubs push out from the sides of your head, and you gasp as you feel them quickly grow out into "+getDeterminer(owner)+" long, curved horns, much like a bull's."
							+ "</br>"
							+ "You now have "+getDeterminer(owner)+" [style.boldCowMorph(masculine-looking bovine horns)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Hard nubs push out from the sides of [npc.her] head, and [npc.she] gasps as they quickly grow out into "+getDeterminer(owner)+" long, curved horns, much like a bull's."
								+ "</br>"
								+ "[npc.Name] now has "+getDeterminer(owner)+" [style.boldCowMorph(masculine-looking bovine horns)]."
								+ "</p>");
				}
				break;
			case NONE:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" Chunks of your [pc.horns] fall to the floor as they start to crumble away, and within moments they've completely disappeared."
							+ "</br>"
							+ "You now have [style.boldTfGeneric(no horns)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Chunks of [npc.her] [npc.horns] fall to the floor as they start to crumble away, and within moments they've completely disappeared."
								+ "</br>"
								+ "[npc.Name] now has [style.boldTfGeneric(no horns)]."
								+ "</p>");
				}
				break;
		}
		
		this.type = type;
		
		return UtilText.parse(owner, UtilText.transformationContentSB.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}
	
	public int getHornRows() {
		return rows;
	}

	public String setHornRows(GameCharacter owner, int rows) {
		rows = Math.max(1, Math.min(rows, 3));
		
		if(owner.getHornRows() == rows) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		boolean removingHorns = owner.getHornRows() > rows;
		this.rows = rows;
		
		if (owner.getHornType() == HornType.NONE) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		if(removingHorns) {
			if(owner.isPlayer()) {
				return
						"<p>"
							+ "A tingling feeling spreads over your [pc.horns], before moving down and concentrating into your forehead."
							+ " You can't help but let out a little cry as you feel some of them [style.boldShrink(crumbling away)] and disappearing back down into your [pc.faceSkin].</br>"
							+ "After a few moments, you're left with [style.boldTfGeneric("+getDeterminer(owner)+" [pc.horns])]."
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "A tingling feeling spreads over [npc.name]'s [npc.horns], before moving down and concentrating into [npc.her] forehead."
							+ " [npc.She] can't help but let out a little cry as [npc.she] feels some of them [style.boldShrink(crumbling away)] and disappearing back down into [npc.her] [npc.faceSkin].</br>"
							+ "After a few moments, [npc.she]'s left with [style.boldTfGeneric("+getDeterminer(owner)+" [npc.horns])]."
						+ "</p>");
			}
			
		} else {
			if(owner.isPlayer()) {
				return
						"<p>"
							+ "A tingling feeling spreads over your [pc.horns], before moving down and concentrating into your forehead."
							+ " You can't help but let out a little cry as you feel new [pc.horns] [style.boldGrow(pushing up)] and growing out of your [pc.faceSkin].</br>"
							+ "After a few moments, you're left with [style.boldTfGeneric("+getDeterminer(owner)+" [pc.horns])]."
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "A tingling feeling spreads over [npc.name]'s [npc.horns], before moving down and concentrating into [npc.her] forehead."
							+ " [npc.She] can't help but let out a little cry as [npc.she] feels new [npc.horns] [style.boldGrow(pushing up)] and growing out of [npc.her] [npc.faceSkin].</br>"
							+ "After a few moments, [npc.she]'s left with [style.boldTfGeneric("+getDeterminer(owner)+" [npc.horns])]."
						+ "</p>");
			}
		}
	}

}
