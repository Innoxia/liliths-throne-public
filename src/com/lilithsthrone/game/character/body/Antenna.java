package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAntennaType;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.83
 * @version 0.3.1
 * @author Innoxia
 */
public class Antenna implements BodyPartInterface {
	
	protected AbstractAntennaType type;
	protected int rows;
	
	public Antenna(AbstractAntennaType type) {
		this.type = type;
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
		rows = Math.max(1, Math.min(rows, 3));
		
		if(owner.getAntennaRows() == rows) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		this.rows = rows;
		
		if(owner.getAntennaType() == AntennaType.NONE) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		if(owner.getAntennaRows() > rows) {
			return UtilText.parse(owner,
					"<p>"
						+ "A tingling feeling spreads over [npc.namePos] [npc.antennae], before moving down and concentrating into [npc.her] forehead."
						+ " [npc.She] can't help but let out a little cry as [npc.she] [npc.verb(feel)] some of them [style.boldShrink(falling away)] and disappearing back down into [npc.her] [npc.faceSkin].<br/>"
						+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric("+getDeterminer(owner)+" [npc.antennae])]."
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "A tingling feeling spreads over [npc.namePos] [npc.antennae], before moving down and concentrating into [npc.her] forehead."
						+ " [npc.She] can't help but let out a little cry as [npc.she] [npc.verb(feel)] new [npc.antennae] [style.boldGrow(pushing up)] and growing out of [npc.her] [npc.faceSkin].<br/>"
						+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric("+getDeterminer(owner)+" [npc.antennae])]."
					+ "</p>");
			
		}
	}

	@Override
	public boolean isBestial(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.getLegConfiguration().getBestialParts().contains(Antenna.class) && getType().getRace().isBestialPartsAvailable();
	}
}
