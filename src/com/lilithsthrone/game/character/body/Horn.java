package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHornType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Horn implements BodyPartInterface {

	public static final int MAXIMUM_ROWS = 3;
	public static final int MAXIMUM_HORNS_PER_ROW = 4;
	
	protected AbstractHornType type;
	protected int rows;
	protected int hornsPerRow;
	protected int length;
	
	public Horn(AbstractHornType type, int length) {
		if(length<=0) {
			this.length = 0;
			this.type = HornType.NONE;
			
		} else {
			this.type = type;
			this.length = length;
		}
		
		rows = 1;
		hornsPerRow = type.getDefaultHornsPerRow();
	}

	public Horn(Horn hornToCopy) {
		this.type = hornToCopy.type;
		this.length = hornToCopy.length;
		this.rows = hornToCopy.rows;
		this.hornsPerRow = hornToCopy.hornsPerRow;
	}
	
	@Override
	public AbstractHornType getType() {
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

	public void setType(AbstractHornType type) {
		this.type = type;
	}

	public String setType(GameCharacter owner, AbstractHornType type) {
		if(!Main.game.isStarted() || owner==null) {
			this.type = type;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			if(type.equals(HornType.NONE)) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.verb(lack)] horns, so nothing happens...)]</p>");
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.has] the [npc.horns] of [npc.a_hornRace], so nothing happens...)]</p>");
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		if (type == HornType.HORSE_STRAIGHT) {
			this.setHornsPerRow(owner, 1);
			this.setHornRows(owner, 1);
		}

		if(this.type.equals(HornType.NONE)) {
			sb.append(UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a surprised gasp and [npc.verb(rub)] at [npc.her] forehead as [npc.she] [npc.verb(feel)] it growing hot and sensitive."
						+ " After just a moment, [npc.her] [npc.eyes] widen in shock as something starts pushing out from under the [npc.faceSkin] of [npc.her] forehead."));
		} else {
			sb.append(UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a surprised gasp as [npc.she] [npc.verb(feel)] an odd tingling sensation at the base of [npc.her] "+(owner.getTotalHorns()==1?"[npc.horn]":"[npc.horns]")+"."
						+ " Before [npc.she] [npc.has] any time in which to react, "+(owner.getTotalHorns()==1?"it rapidly crumbles away, and within moments it's":"they rapidly crumble away, and within moments they've")+" completely disappeared. "));
		}

		if(type!=HornType.NONE) {
			sb.append(UtilText.parse(owner, (owner.getTotalHorns()==1
					?" A hard nub suddenly pushes out from the middle of [npc.her] forehead, and [npc.she] [npc.verb(gasp)] as [npc.she] [npc.verb(feel)] it quickly grow out into a "
					:" Hard nubs suddenly push out from the sides of [npc.her] head, and [npc.she] [npc.verb(gasp)] as [npc.she] [npc.verb(feel)] them quickly grow out into ")));
		}
		
		this.type = type;
		
		sb.append(type.getTransformationDescription(owner));
		
		if(this.length==0) {
			length = HornLength.ONE_SMALL.getMinimumValue();
		}
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}
	
	public int getHornRows() {
		return rows;
	}

	public String setHornRows(GameCharacter owner, int rows) {
		rows = Math.max(1, Math.min(rows, MAXIMUM_ROWS));
		if(owner==null) {
			this.rows = rows;
			return "";
		}
		
		if(owner.getHornRows() == rows) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		boolean removingHorns = owner.getHornRows() > rows;
		this.rows = rows;
		
		owner.postTransformationCalculation();
		
		if (owner.getHornType().equals(HornType.NONE)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		if(removingHorns) {
			return UtilText.parse(owner,
					"<p>"
						+ "A tingling feeling spreads over [npc.namePos] [npc.horns], before moving down and concentrating in [npc.her] forehead."
						+ " [npc.She] can't help but let out a cry as [npc.she] [npc.verb(feel)] some of them [style.boldShrink(crumbling away)] and disappearing back down into [npc.her] [npc.faceSkin].<br/>"
						+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric([npc.a_horns])]."
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "A tingling feeling spreads over [npc.namePos] [npc.horns], before moving down and concentrating in [npc.her] forehead."
						+ " [npc.She] can't help but let out a cry as [npc.she] [npc.verb(feel)] new [npc.horns] [style.boldGrow(pushing up)] and growing out of [npc.her] [npc.faceSkin].<br/>"
						+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric([npc.a_horns])]."
					+ "</p>");
		}
	}
	
	public int getHornsPerRow() {
		return hornsPerRow;
	}

	public String setHornsPerRow(GameCharacter owner, int hornsPerRow) {
		hornsPerRow = Math.max(1, Math.min(hornsPerRow, MAXIMUM_HORNS_PER_ROW));
		if(owner==null) {
			this.hornsPerRow = hornsPerRow;
			return "";
		}
		
		if(owner.getHornsPerRow() == hornsPerRow) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		boolean removingHorns = owner.getHornsPerRow() > hornsPerRow;
		this.hornsPerRow = hornsPerRow;
		
		owner.postTransformationCalculation();
		
		if (owner.getHornType().equals(HornType.NONE)) {
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

	public int getTotalHorns() {
		return getHornRows() * getHornsPerRow();
	}
	
	public HornLength getHornLength() {
		return HornLength.getLengthFromInt(length);
	}
	
	public int getHornLengthValue() {
		return length;
	}

	public void setHornLength(int length) {
		this.length = Math.max(0, Math.min(length, HornLength.FOUR_MASSIVE.getMaximumValue()));
	}

	public void setTypeAndLength(AbstractHornType type, int length) {
		setType(type);
		setHornLength(length);
	}

	public String setHornLength(GameCharacter owner, int length) {
		int oldLength = this.length;
		this.length = Math.max(0, Math.min(length, HornLength.FOUR_MASSIVE.getMaximumValue()));
		int sizeChange = this.length - oldLength;
		
		if (owner==null) {
			return "";
		}
		
		if(owner.getHornType().equals(HornType.NONE)) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.do]n't have any horns, so nothing seems to happen...)]</p>");
		}
		
		if(sizeChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The length of [npc.namePos] [npc.horns] doesn't change...)]</p>");
		}
		
		if(sizeChange < 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a cry as [npc.she] [npc.verb(feel)] a strange tingling sensation running up through [npc.her] [npc.face] and into [npc.her] [npc.horns],"
							+ " before "+(getTotalHorns()==1?"it suddenly shrinks":"they suddenly shrink")+" down and [style.boldShrink("+(getTotalHorns()==1?"gets":"get")+" noticeably shorter)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldTfGeneric("+(getTotalHorns()==1?"[npc.a_hornSize]":"[npc.hornSize]")+" [npc.horns])]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a little cry as [npc.she] [npc.verb(feel)] a warm pulsating sensation running up through [npc.her] [npc.face] and into [npc.her] [npc.horns],"
							+ " before "+(getTotalHorns()==1?"it suddenly grows":"they suddenly grow")+" out and [style.boldGrow("+(getTotalHorns()==1?"gets":"get")+" noticeably longer)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldTfGeneric("+(getTotalHorns()==1?"[npc.a_hornSize]":"[npc.hornSize]")+" [npc.horns])]!"
					+ "</p>");
		}
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Horn.class) && getType().getRace().isFeralPartsAvailable());
	}

}