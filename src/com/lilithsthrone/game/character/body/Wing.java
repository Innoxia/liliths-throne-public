package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractWingType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Wing implements BodyPartInterface {

	protected AbstractWingType type;
	protected int size;
	
	public Wing(AbstractWingType type, int size) {
		this.type = type;
		this.size = size;
	}

	public Wing(Wing wingToCopy) {
		this.type = wingToCopy.type;
		this.size = wingToCopy.size;
	}

	@Override
	public AbstractWingType getType() {
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

	public void setType(AbstractWingType type) {
		this.type = type;
	}

	public String setType(GameCharacter owner, AbstractWingType type) {
		if(!Main.game.isStarted() || owner==null) {
			this.type = type;
			this.setSize(owner, this.getSizeValue());
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			if(type == WingType.NONE) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.verb(lack)] wings, so nothing happens...)]</p>");
				
			} else if(type.getRace()!=Race.NONE) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.has] the [npc.wings] of [npc.a_wingRace], so nothing happens...)]</p>");
				
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.has] "+type.getTransformName()+" [npc.wings], so nothing happens...)]</p>");
			}
		}
		
		StringBuilder sb = new StringBuilder();

		if(type != WingType.NONE) {
			sb.append(
					"<p>"
						+ "[npc.Name] [npc.verb(try)] to look behind [npc.herHim] as [npc.she] [npc.verb(feel)] a strange bubbling sensation rising up in [npc.her] back, before something starts pushing out from under [npc.her] [npc.skin].");
			
		} else {
			sb.append(
					"<p>"
						+ "[npc.NamePos] [npc.wings] suddenly start to twitch and flap with a mind of their own, and [npc.she] [npc.verb(let)] out a gasp as [npc.she] [npc.verb(feel)] them start to transform.");
		}
		
		sb.append(" "+type.getTransformationDescription(owner));
		
		if(type!=WingType.NONE) {
			if(this.getSize().getValue() >= owner.getLegConfiguration().getMinimumWingSizeForFlight(owner.getBody()).getValue()) {
				sb.append("</br>"
						+ "Giving them an experimental flap, [npc.sheIs] delighted to discover that they're [style.colourGood(powerful enough to enable [npc.herHim] to fly)]!"
						+ "<br/>"
						+ "[style.italics("+Util.capitaliseSentence(owner.getLegConfiguration().getName())+" bodies require wings to be at least '"+owner.getLegConfiguration().getMinimumWingSizeForFlight(owner.getBody()).getName()+"' to enable flight.)]");
			} else {
				sb.append("</br>"
						+ "Giving them an experimental flap, [npc.sheIs] disappointed to discover that they're [style.colourBad(not powerful enough to enable [npc.herHim] to fly)]..."
						+ "<br/>"
						+ "[style.italics("+Util.capitaliseSentence(owner.getLegConfiguration().getName())+" bodies require wings to be at least '"+owner.getLegConfiguration().getMinimumWingSizeForFlight(owner.getBody()).getName()+"' to enable flight.)]");
			}
		}
		sb.append("</p>");

		this.type = type;
		this.setSize(owner, this.getSizeValue());
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
				+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public WingSize getSize() {
		return WingSize.getWingSizeFromInt(size);
	}

	public int getSizeValue() {
		return size;
	}

	public void setSize(int wingSize) {
		this.size = Math.max(0, Math.min(wingSize, WingSize.getLargest()));
	}

	public void setTypeAndSize(AbstractWingType type, int wingSize) {
		setType(type);
		setSize(wingSize);
	}
	
	public String setSize(GameCharacter owner, int wingSize) {
		if(owner==null) {
			int effectiveSize = Math.max(this.getType().getMinimumSize().getValue(), Math.min(wingSize, this.getType().getMaximumSize().getValue()));
			this.size = effectiveSize;
			return "";
		}
		
		if(this.getType()==WingType.NONE) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.NamePos] [npc.do]n't have any wings, so nothing happens...)]</p>");
		}
		
		int effectiveSize = Math.max(this.getType().getMinimumSize().getValue(), Math.min(wingSize, this.getType().getMaximumSize().getValue()));
		if(owner.getWingSizeValue() == effectiveSize) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The size of [npc.namePos] [npc.wings] doesn't change...)]</p>");
		}

		StringBuilder sb = new StringBuilder();
		
		if(this.size > effectiveSize) {
			sb.append(
					UtilText.parse(owner, "<p>[npc.Name] [npc.verb(let)] out an alarmed cry as [npc.she] [npc.verb(feel)] a soothing coolness rise up into [npc.her] [npc.wings+], before they suddenly [style.boldShrink(shrink)].<br/>"));
			
		} else {
			sb.append(
					UtilText.parse(owner, "<p>[npc.Name] [npc.verb(let)] out an alarmed gasp as [npc.she] [npc.verb(feel)] a pulsating warmth rise up into [npc.her] [npc.wings+], before they suddenly [style.boldGrow(grow larger)].<br/>"));
		}
		
		this.size = effectiveSize;

		sb.append(UtilText.parse(owner, "[npc.Name] now [npc.has] [style.boldSex([npc.wingSize] [npc.wings])]!"));

		if(type!=WingType.NONE) {
			if(this.getSize().getValue() >= owner.getLegConfiguration().getMinimumWingSizeForFlight(owner.getBody()).getValue()) {
				sb.append("</br>"
						+ "Giving them an experimental flap, [npc.sheIs] delighted to discover that they're [style.colourGood(powerful enough to enable [npc.herHim] to fly)]!"
						+ "<br/>"
						+ "[style.italics("+Util.capitaliseSentence(owner.getLegConfiguration().getName())+" bodies require wings to be at least '"+owner.getLegConfiguration().getMinimumWingSizeForFlight(owner.getBody()).getName()+"' to enable flight.)]");
			} else {
				sb.append("</br>"
						+ "Giving them an experimental flap, [npc.sheIs] disappointed to discover that they're [style.colourBad(not powerful enough to enable [npc.herHim] to fly)]..."
						+ "<br/>"
						+ "[style.italics("+Util.capitaliseSentence(owner.getLegConfiguration().getName())+" bodies require wings to be at least '"+owner.getLegConfiguration().getMinimumWingSizeForFlight(owner.getBody()).getName()+"' to enable flight.)]");
			}
		}
		sb.append("</p>");
		
		return sb.toString();
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Wing.class) && getType().getRace().isFeralPartsAvailable());
	}
}
