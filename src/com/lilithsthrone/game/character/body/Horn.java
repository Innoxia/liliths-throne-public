package com.lilithsthrone.game.character.body;

import java.io.Serializable;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.2.7
 * @author Innoxia
 */
public class Horn implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	protected HornType type;
	protected int rows;
	protected int hornsPerRow;
	protected int length;
	
	public Horn(HornType type, int length) {
		this.type = type;
		this.length = length;
		rows = 1;
		hornsPerRow = 2;
	}

	@Override
	public HornType getType() {
		return type;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc.getHornRows()==1) {
			if(hornsPerRow==1) {
				return "a solitary";
				
			} else if(hornsPerRow==2) {
				return "a pair of";
				
			} else if(hornsPerRow==2) {
				return "a trio of";
				
			} else {
				return "a quartet of";
			}
			
		} else {
			if(hornsPerRow==1) {
				return Util.intToString(gc.getHornRows())+" centrally-positioned";
				
			} else if(hornsPerRow==2) {
				return Util.intToString(gc.getHornRows())+" pairs of";
				
			} else if(hornsPerRow==2) {
				return Util.intToString(gc.getHornRows())+" trios of";
				
			} else {
				return Util.intToString(gc.getHornRows())+" quartets of";
			}
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
			case CURLED:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" Hard nubs push out from the sides of your head, and you gasp as you feel them quickly grow out into "+getDeterminer(owner)+" circular-curling horns."
							+ "<br/>"
							+ "You now have "+getDeterminer(owner)+" [style.boldTfGeneric(curled horns)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Hard nubs push out from the sides of [npc.her] head, and [npc.she] gasps as they quickly grow out into "+getDeterminer(owner)+" circular-curling horns."
								+ "<br/>"
								+ "[npc.Name] now has "+getDeterminer(owner)+" [style.boldTfGeneric(curled horns)]."
								+ "</p>");
				}
				break;
			case CURVED: case BOVINE_CURVED:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" Hard nubs push out from the sides of your head, and you gasp as you feel them quickly grow out into "+getDeterminer(owner)+" slightly-curved horns."
							+ "<br/>"
							+ "You now have "+getDeterminer(owner)+" [style.boldTfGeneric(curved horns)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Hard nubs push out from the sides of [npc.her] head, and [npc.she] gasps as they quickly grow out into "+getDeterminer(owner)+" slightly-curved horns."
								+ "<br/>"
								+ "[npc.Name] now has "+getDeterminer(owner)+" [style.boldTfGeneric(curved horns)]."
								+ "</p>");
				}
				break;
			case SPIRAL:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" Hard nubs push out from the sides of your head, and you gasp as you feel them quickly grow out into "+getDeterminer(owner)+" twisted, spiralling horns."
							+ "<br/>"
							+ "You now have "+getDeterminer(owner)+" [style.boldTfGeneric(spiral horns)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Hard nubs push out from the sides of [npc.her] head, and [npc.she] gasps as they quickly grow out into "+getDeterminer(owner)+" twisted, spiralling horns."
								+ "<br/>"
								+ "[npc.Name] now has "+getDeterminer(owner)+" [style.boldTfGeneric(spiral horns)]."
								+ "</p>");
				}
				break;
			case REINDEER_RACK:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" Hard nubs push out from the sides of your head, and you gasp as you feel them quickly grow out into "+getDeterminer(owner)+" branching, multi-pronged antlers."
							+ "<br/>"
							+ "You now have "+getDeterminer(owner)+" [style.boldTfGeneric(reindeer-like antlers)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Hard nubs push out from the sides of [npc.her] head, and [npc.she] gasps as they quickly grow out into "+getDeterminer(owner)+" branching, multi-pronged antlers."
								+ "<br/>"
								+ "[npc.Name] now has "+getDeterminer(owner)+" [style.boldTfGeneric(reindeer-like antlers)]."
								+ "</p>");
				}
				break;
			case STRAIGHT: case BOVINE_STRAIGHT:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" Hard nubs push out from the sides of your head, and you gasp as you feel them quickly grow out into "+getDeterminer(owner)+" sleek, straight horns."
							+ "<br/>"
							+ "You now have "+getDeterminer(owner)+" [style.boldTfGeneric(straight horns)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Hard nubs push out from the sides of [npc.her] head, and [npc.she] gasps as they quickly grow out into "+getDeterminer(owner)+" sleek, straight horns."
								+ "<br/>"
								+ "[npc.Name] now has "+getDeterminer(owner)+" [style.boldTfGeneric(straight horns)]."
								+ "</p>");
				}
				break;
			case SWEPT_BACK:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" Hard nubs push out from the sides of your head, and you gasp as you feel them quickly grow out and curve back over your head into "+getDeterminer(owner)+" swept-back horns."
							+ "<br/>"
							+ "You now have "+getDeterminer(owner)+" [style.boldTfGeneric(swept-back horns)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Hard nubs push out from the sides of [npc.her] head, and [npc.she] gasps as they quickly grow out and curve back over [npc.her] head into "+getDeterminer(owner)+" swept-back horns."
								+ "<br/>"
								+ "[npc.Name] now has "+getDeterminer(owner)+" [style.boldTfGeneric(swept-back horns)]."
								+ "</p>");
				}
				break;
			case NONE:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" Chunks of your [pc.horns] fall to the floor as they start to crumble away, and within moments they've completely disappeared."
							+ "<br/>"
							+ "You now have [style.boldTfGeneric(no horns)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Chunks of [npc.her] [npc.horns] fall to the floor as they start to crumble away, and within moments they've completely disappeared."
								+ "<br/>"
								+ "[npc.Name] now has [style.boldTfGeneric(no horns)]."
								+ "</p>");
				}
				break;
		}
		
		this.type = type;
		if(this.length==0) {
			length = HornLength.ONE_SMALL.getMinimumValue();
		}
		
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
							+ " You can't help but let out a little cry as you feel some of them [style.boldShrink(crumbling away)] and disappearing back down into your [pc.faceSkin].<br/>"
							+ "After a few moments, you're left with [style.boldTfGeneric("+getDeterminer(owner)+" [pc.horns])]."
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "A tingling feeling spreads over [npc.namePos] [npc.horns], before moving down and concentrating into [npc.her] forehead."
							+ " [npc.She] can't help but let out a little cry as [npc.she] feels some of them [style.boldShrink(crumbling away)] and disappearing back down into [npc.her] [npc.faceSkin].<br/>"
							+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric("+getDeterminer(owner)+" [npc.horns])]."
						+ "</p>");
			}
			
		} else {
			if(owner.isPlayer()) {
				return
						"<p>"
							+ "A tingling feeling spreads over your [pc.horns], before moving down and concentrating into your forehead."
							+ " You can't help but let out a little cry as you feel new [pc.horns] [style.boldGrow(pushing up)] and growing out of your [pc.faceSkin].<br/>"
							+ "After a few moments, you're left with [style.boldTfGeneric("+getDeterminer(owner)+" [pc.horns])]."
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "A tingling feeling spreads over [npc.namePos] [npc.horns], before moving down and concentrating into [npc.her] forehead."
							+ " [npc.She] can't help but let out a little cry as [npc.she] feels new [npc.horns] [style.boldGrow(pushing up)] and growing out of [npc.her] [npc.faceSkin].<br/>"
							+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric("+getDeterminer(owner)+" [npc.horns])]."
						+ "</p>");
			}
		}
	}
	
	public int getHornsPerRow() {
		return hornsPerRow;
	}

	public String setHornsPerRow(GameCharacter owner, int hornsPerRow) {
		hornsPerRow = Math.max(1, Math.min(hornsPerRow, 4));
		
		if(owner.getHornsPerRow() == hornsPerRow) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		boolean removingHorns = owner.getHornsPerRow() > hornsPerRow;
		this.hornsPerRow = hornsPerRow;
		
		if (owner.getHornType() == HornType.NONE) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		if(removingHorns) {
			return UtilText.parse(owner,
					"<p>"
						+ "A strange bubbling sensation starts to take root at the base of [npc.namePos] [npc.horns]."
						+ " Before [npc.she] can react, they start to [style.boldShrink(crumble away and reshape)] themselves.<br/>"
						+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric("+Util.intToString(hornsPerRow)+" "+(hornsPerRow==1?"[npc.horn]":"[npc.horns]")+(rows==1?"":" in each row")+")]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "A strange bubbling sensation starts to rise up in [npc.namePos] forehead."
						+ " Before [npc.she] can react, new [npc.horns] suddenly [style.boldGrow(push up)] and grow out of [npc.her] [npc.faceSkin].<br/>"
						+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric("+Util.intToString(hornsPerRow)+" "+(hornsPerRow==1?"[npc.horn]":"[npc.horns]")+(rows==1?"":" in each row")+")]!"
					+ "</p>");
		}
	}
	
	public HornLength getHornLength() {
		return HornLength.getHornLengthFromInt(length);
	}
	
	public int getHornLengthValue() {
		return length;
	}

	public String setHornLength(GameCharacter owner, int length) {
		int oldLength = this.length;
		this.length = Math.max(0, Math.min(length, HornLength.FOUR_MASSIVE.getMaximumValue()));
		int sizeChange = this.length - oldLength;
		
		if(owner.getHornType()==HornType.NONE) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(You don't have any horns, so nothing seems to happen...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] doesn't have any horns, so nothing seems to happen...)]</p>");
			}
		}
		
		if(sizeChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The length of your [pc.horns] doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The length of [npc.namePos] [npc.horns] doesn't change...)]</p>");
			}
		}
		
		if(sizeChange < 0) {
			if(owner.isPlayer()) {
				return "<p>A strange tingling sensation runs up through your [pc.face] and into your [pc.horns], causing you to let out a surprised gasp as you feel them [style.boldShrink(getting shorter)].<br/>"
						+ "You now have [style.boldTfGeneric([pc.hornSize] [pc.horns])]!</p>";
			} else {
				return UtilText.parse(owner, "<p>[npc.Name] lets out a little cry as [npc.she] feels a strange tingling sensation running up through [npc.her] [npc.face] and into [npc.her] [npc.horns],"
							+ " before they suddenly shrink down and [style.boldShrink(get noticeably shorter)].<br/>"
						+ "[npc.Name] now has [style.boldTfGeneric([npc.hornSize] [npc.horns])]!</p>");
			}
			
		} else {
			if(owner.isPlayer()) {
				return "<p>A warm pulsating sensation runs up through your [pc.face] and into your [pc.horns], causing you to let out a surprised gasp as you feel them [style.boldGrow(growing larger)].<br/>"
						+ "You now have [style.boldTfGeneric([pc.hornSize] [pc.horns])]!</p>";
			} else {
				return UtilText.parse(owner, "<p>[npc.Name] lets out a little cry as [npc.she] feels a warm pulsating sensation running up through [npc.her] [npc.face] and into [npc.her] [npc.horns],"
						+ " before they suddenly grow out and [style.boldGrow(get noticeably longer)].<br/>"
						+ "[npc.Name] now has [style.boldTfGeneric([npc.hornSize] [npc.horns])]!</p>");
			}
		}
	}

}
