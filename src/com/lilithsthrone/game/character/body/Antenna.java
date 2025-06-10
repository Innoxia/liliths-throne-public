package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAntennaType;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Antenna implements BodyPartInterface {

	public static final int MAXIMUM_ROWS = 3;
	public static final int MAXIMUM_ANTENNAE_PER_ROW = 4;
	
	protected AbstractAntennaType type;
	protected int rows;
	protected int antennaePerRow;
	protected int length;
	
	public Antenna(AbstractAntennaType type, int length) {
		this.type = type;
		this.length = length;
		rows = 1;
		antennaePerRow = type.getDefaultAntennaePerRow();
	}

	public Antenna(Antenna antennaToCopy) {
		this.type = antennaToCopy.type;
		this.length = antennaToCopy.length;
		this.rows = antennaToCopy.rows;
		this.antennaePerRow = antennaToCopy.antennaePerRow;
	}
	
	@Override
	public AbstractAntennaType getType() {
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

	public String setType(GameCharacter owner, AbstractAntennaType type) {
		if(!Main.game.isStarted() || owner==null) {
			this.type = type;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			if(type.equals(AntennaType.NONE)) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.verb(lack)] antennae, so nothing happens...)]</p>");
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.has] the [npc.antenna] of [npc.a_antennaRace], so nothing happens...)]</p>");
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(this.type.equals(AntennaType.NONE)) {
			sb.append(UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a surprised gasp and [npc.verb(rub)] at [npc.her] forehead as [npc.she] [npc.verb(feel)] it growing hot and sensitive."
						+ " After just a moment, [npc.her] [npc.eyes] widen in shock as something starts pushing out from under the [npc.faceSkin] of [npc.her] forehead."));
		} else {
			sb.append(UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a surprised gasp as [npc.she] [npc.verb(feel)] an odd tingling sensation at the base of [npc.her] [npc.antennae]."
						+ " Before [npc.she] [npc.has] any time in which to react, they rapidly crumble away, and within moments they've completely disappeared. "));
		}
		
		if(type!=AntennaType.NONE) {
			sb.append(UtilText.parse(owner, 
					" Hard nubs suddenly push out from the sides of [npc.her] head, and [npc.she] [npc.verb(gasp)] as [npc.she] [npc.verb(feel)] them quickly grow out into "));
		}
		
		this.type = type;
		
		sb.append(type.getTransformationDescription(owner));
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}
	
	public int getAntennaRows() {
		return rows;
	}

	public String setAntennaRows(GameCharacter owner, int rows) {
		rows = Math.max(1, Math.min(rows, MAXIMUM_ROWS));
		if(owner==null) {
			this.rows = rows;
			return "";
		}
		
		if(owner.getAntennaRows() == rows) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		boolean removingAntennae = owner.getAntennaRows() > rows;
		this.rows = rows;
		
		owner.postTransformationCalculation();
		
		if (owner.getAntennaType().equals(AntennaType.NONE)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		if(removingAntennae) {
			return UtilText.parse(owner,
					"<p>"
						+ "A tingling feeling spreads over [npc.namePos] [npc.antennae], before moving down and concentrating in [npc.her] forehead."
						+ " [npc.She] can't help but let out a cry as [npc.she] [npc.verb(feel)] some of them [style.boldShrink(crumbling away)] and disappearing back down into [npc.her] [npc.faceSkin].<br/>"
						+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric([npc.a_antennae])]."
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "A tingling feeling spreads over [npc.namePos] [npc.antennae], before moving down and concentrating in [npc.her] forehead."
						+ " [npc.She] can't help but let out a cry as [npc.she] [npc.verb(feel)] new [npc.antennae] [style.boldGrow(pushing up)] and growing out of [npc.her] [npc.faceSkin].<br/>"
						+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric([npc.a_antennae])]."
					+ "</p>");
		}
	}
	
	public int getAntennaePerRow() {
		return antennaePerRow;
	}

	public String setAntennaePerRow(GameCharacter owner, int antennaePerRow) {
		antennaePerRow = Math.max(1, Math.min(antennaePerRow, MAXIMUM_ANTENNAE_PER_ROW));
		if(owner==null) {
			this.antennaePerRow = antennaePerRow;
			return "";
		}
		
		if(owner.getAntennaePerRow() == antennaePerRow) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		boolean removingAntennae = owner.getAntennaePerRow() > antennaePerRow;
		this.antennaePerRow = antennaePerRow;
		
		owner.postTransformationCalculation();
		
		if (owner.getAntennaType().equals(AntennaType.NONE)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		if(removingAntennae) {
			return UtilText.parse(owner,
					"<p>"
						+ "A strange bubbling sensation starts to take root at the base of [npc.namePos] [npc.antennae]."
						+ " Before [npc.she] can react, they start to [style.boldShrink(crumble away and reshape)] themselves.<br/>"
						+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric("+Util.intToString(antennaePerRow)+" "+(antennaePerRow==1?"[npc.antenna]":"[npc.antennae]")+(rows==1?"":" in each row")+")]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "A strange bubbling sensation starts to rise up in [npc.namePos] forehead."
						+ " Before [npc.she] can react, new [npc.antennae] suddenly [style.boldGrow(push up)] and grow out of [npc.her] [npc.faceSkin].<br/>"
						+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric("+Util.intToString(antennaePerRow)+" "+(antennaePerRow==1?"[npc.antenna]":"[npc.antennae]")+(rows==1?"":" in each row")+")]!"
					+ "</p>");
		}
	}

	public int getTotalAntennae() {
		return getAntennaRows() * getAntennaePerRow();
	}
	
	public HornLength getAntennaLength() {
		return HornLength.getLengthFromInt(length);
	}
	
	public int getAntennaLengthValue() {
		return length;
	}

	public String setAntennaLength(GameCharacter owner, int length) {
		int oldLength = this.length;
		this.length = Math.max(0, Math.min(length, HornLength.FOUR_MASSIVE.getMaximumValue()));
		int sizeChange = this.length - oldLength;
		
		if (owner==null) {
			return "";
		}
		
		if(owner.getAntennaType().equals(AntennaType.NONE)) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.do]n't have any antennae, so nothing seems to happen...)]</p>");
		}
		
		if(sizeChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The length of [npc.namePos] [npc.antennae] doesn't change...)]</p>");
		}
		
		if(sizeChange < 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a cry as [npc.she] [npc.verb(feel)] a strange tingling sensation running up through [npc.her] [npc.face] and into [npc.her] [npc.antennae],"
							+ " before they suddenly shrink down and [style.boldShrink(get noticeably shorter)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldTfGeneric([npc.antennaSize] [npc.antennae])]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a little cry as [npc.she] [npc.verb(feel)] a warm pulsating sensation running up through [npc.her] [npc.face] and into [npc.her] [npc.antennae],"
							+ " before they suddenly grow out and [style.boldGrow(get noticeably longer)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldTfGeneric([npc.antennaSize] [npc.antennae])]!"
					+ "</p>");
		}
	}
	
	@Override
	public boolean isFeral(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Antenna.class) && getType().getRace().isFeralPartsAvailable());
	}
}
