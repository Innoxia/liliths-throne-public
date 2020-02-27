package com.lilithsthrone.game.character.body;


import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.1
 * @author Innoxia
 */
public class Wing implements BodyPartInterface {

	
	protected WingType type;
	protected int size;
	
	public Wing(WingType type, int size) {
		this.type = type;
		this.size = size;
	}

	@Override
	public WingType getType() {
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

	public String setType(GameCharacter owner, WingType type) {
		if(!Main.game.isStarted() || owner==null) {
			this.type = type;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			if(type == WingType.NONE) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.verb(lack)] wings, so nothing happens...)]</p>");
				
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.has] the [npc.wings] of [npc.a_wingRace], so nothing happens...)]</p>");
			}
			
		} else {
			UtilText.transformationContentSB.setLength(0);

			if(type != WingType.NONE) {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "[npc.Name] [npc.verb(try)] to look behind [npc.herHim] as [npc.she] [npc.verb(feel)] a strange bubbling sensation rising up in [npc.her] back, before something starts pushing out from under [npc.her] [npc.skin].");
				
			} else {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "[npc.NamePos] [npc.wings] suddenly start to twitch and flap with a mind of their own, and [npc.she] [npc.verb(let)] out a gasp as [npc.she] [npc.verb(feel)] them start to transform.");
			}
		}
		
		switch (type) {
			case ANGEL:
				if(owner.getLegConfiguration().isWingsOnLegConfiguration()) {
					UtilText.transformationContentSB.append(
							" [npc.She] [npc.verb(bite)] [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of [npc.wingSize], feathered wings push out from the sides of [npc.her] [npc.legConfiguration] body.<br/>"
							+ "[npc.Name] now [npc.has] [style.boldAngel(angelic, feathered wings)].");
				} else {
					UtilText.transformationContentSB.append(
							" [npc.She] [npc.verb(bite)] [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of [npc.wingSize], feathered wings push out from [npc.her] shoulder blades.<br/>"
							+ "[npc.Name] now [npc.has] [style.boldAngel(angelic, feathered wings)].");
				}
				break;
			case DEMON_COMMON:
				if(owner.getLegConfiguration().isWingsOnLegConfiguration()) {
					UtilText.transformationContentSB.append(
							" [npc.She] [npc.verb(bite)] [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of [npc.wingSize], bat-like wings push out from the sides of [npc.her] [npc.legConfiguration] body.<br/>"
							+ (!owner.isShortStature()
									?"[npc.Name] now [npc.has] [style.boldDemon(demonic bat-like wings)]."
									:"[npc.Name] now [npc.has] [style.boldImp(impish bat-like wings)]."));
				} else {
					UtilText.transformationContentSB.append(
							" [npc.She] [npc.verb(bite)] [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of [npc.wingSize], bat-like wings push out from [npc.her] shoulder blades.<br/>"
							+ (!owner.isShortStature()
									?"[npc.Name] now [npc.has] [style.boldDemon(demonic bat-like wings)]."
									:"[npc.Name] now [npc.has] [style.boldImp(impish bat-like wings)]."));
				}
				break;
			case DEMON_FEATHERED:
				if(owner.getLegConfiguration().isWingsOnLegConfiguration()) {
					UtilText.transformationContentSB.append(
							" [npc.She] [npc.verb(bite)] [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of [npc.wingSize], feathered wings push out from the sides of [npc.her] [npc.legConfiguration] body.<br/>"
							+ (!owner.isShortStature()
									?"[npc.Name] now [npc.has] [style.boldDemon(demonic feathered wings)]."
									:"[npc.Name] now [npc.has] [style.boldImp(impish feathered wings)]."));
				} else {
					UtilText.transformationContentSB.append(
								" [npc.She] [npc.verb(bite)] [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of [npc.wingSize], feathered wings push out from [npc.her] shoulder blades.<br/>"
								+ (!owner.isShortStature()
										?"[npc.Name] now [npc.has] [style.boldDemon(demonic feathered wings)]."
										:"[npc.Name] now [npc.has] [style.boldImp(impish feathered wings)]."));
				}
				break;
			case NONE:
				if(owner.getLegConfiguration().isWingsOnLegConfiguration()) {
					UtilText.transformationContentSB.append(
							" With a strong tugging sensation, [npc.her] [npc.wings] shrink away and disappear into the sides of [npc.her] [npc.legConfiguration] body."
							+ "<br/>"
							+ "[npc.Name] now [npc.has] [style.boldTfGeneric(no wings)].");
				} else {
					UtilText.transformationContentSB.append(
							" With a strong tugging sensation, [npc.her] [npc.wings] shrink away and disappear into [npc.her] back."
							+ "<br/>"
							+ "[npc.Name] now [npc.has] [style.boldTfGeneric(no wings)].");
				}
				break;
			case PEGASUS:
				if(owner.getLegConfiguration().isWingsOnLegConfiguration()) {
					UtilText.transformationContentSB.append(
							" [npc.She] [npc.verb(bite)] [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of [npc.wingSize], feathered wings push out from the sides of [npc.her] [npc.legConfiguration] body.<br/>"
							+ "[npc.Name] now [npc.has] the [style.boldPegataur(feathered wings of a pegataur)].");
				} else {
					UtilText.transformationContentSB.append(
							" [npc.She] [npc.verb(bite)] [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of [npc.wingSize], feathered wings push out from [npc.her] shoulder blades.<br/>"
							+ "[npc.Name] now [npc.has] the [style.boldPegataur(feathered wings of a pegataur)].");
				}
				break;
		}
		
		if(type!=WingType.NONE) {
			if(this.getSize().getValue() >= owner.getLegConfiguration().getMinimumWingSizeForFlight().getValue()) {
				UtilText.transformationContentSB.append("</br>"
						+ "Giving them an experimental flap, [npc.sheIs] delighted to discover that they're [style.colourGood(powerful enough to enable [npc.herHim] to fly)]!"
						+ "<br/>"
						+ "[style.italics("+Util.capitaliseSentence(owner.getLegConfiguration().getName())+" bodies require wings to be at least '"+owner.getLegConfiguration().getMinimumWingSizeForFlight().getName()+"' to enable flight.)]");
			} else {
				UtilText.transformationContentSB.append("</br>"
						+ "Giving them an experimental flap, [npc.sheIs] disappointed to discover that they're [style.colourBad(not powerful enough to enable [npc.herHim] to fly)]..."
						+ "<br/>"
						+ "[style.italics("+Util.capitaliseSentence(owner.getLegConfiguration().getName())+" bodies require wings to be at least '"+owner.getLegConfiguration().getMinimumWingSizeForFlight().getName()+"' to enable flight.)]");
			}
		}
		UtilText.transformationContentSB.append("</p>");

		this.type = type;
		
		return UtilText.parse(owner, UtilText.transformationContentSB.toString())
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
	
	public String setSize(GameCharacter owner, int wingSize) {
		if(owner==null) {
			int effectiveSize = Math.max(0, Math.min(wingSize, WingSize.getLargest()));
			this.size = effectiveSize;
			return "";
		}
		
		if(owner.getWingType()==WingType.NONE) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.NamePos] [npc.do]n't have any wings, so nothing happens...)]</p>");
		}
		
		int effectiveSize = Math.max(0, Math.min(wingSize, WingSize.getLargest()));
		if(owner.getWingSizeValue() == effectiveSize) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The size of [npc.namePos] [npc.wings] doesn't change...)]</p>");
		}

		UtilText.transformationContentSB.setLength(0);
		
		if(this.size > effectiveSize) {
			UtilText.transformationContentSB.append(
					UtilText.parse(owner, "<p>[npc.Name] [npc.verb(let)] out an alarmed cry as [npc.she] [npc.verb(feel)] a soothing coolness rise up into [npc.her] [npc.wings+], before they suddenly [style.boldShrink(shrink)].<br/>"));
			
		} else {
			UtilText.transformationContentSB.append(
					UtilText.parse(owner, "<p>[npc.Name] [npc.verb(let)] out an alarmed gasp as [npc.she] [npc.verb(feel)] a pulsating warmth rise up into [npc.her] [npc.wings+], before they suddenly [style.boldGrow(grow larger)].<br/>"));
		}
		
		this.size = effectiveSize;

		UtilText.transformationContentSB.append(UtilText.parse(owner, "[npc.Name] now [npc.has] [style.boldSex([npc.wingSize] [npc.wings])]!"));

		if(type!=WingType.NONE) {
			if(this.getSize().getValue() >= owner.getLegConfiguration().getMinimumWingSizeForFlight().getValue()) {
				UtilText.transformationContentSB.append("</br>"
						+ "Giving them an experimental flap, [npc.sheIs] delighted to discover that they're [style.colourGood(powerful enough to enable [npc.herHim] to fly)]!"
						+ "<br/>"
						+ "[style.italics("+Util.capitaliseSentence(owner.getLegConfiguration().getName())+" bodies require wings to be at least '"+owner.getLegConfiguration().getMinimumWingSizeForFlight().getName()+"' to enable flight.)]");
			} else {
				UtilText.transformationContentSB.append("</br>"
						+ "Giving them an experimental flap, [npc.sheIs] disappointed to discover that they're [style.colourBad(not powerful enough to enable [npc.herHim] to fly)]..."
						+ "<br/>"
						+ "[style.italics("+Util.capitaliseSentence(owner.getLegConfiguration().getName())+" bodies require wings to be at least '"+owner.getLegConfiguration().getMinimumWingSizeForFlight().getName()+"' to enable flight.)]");
			}
		}
		UtilText.transformationContentSB.append("</p>");
		
		return UtilText.transformationContentSB.toString();
	}

	@Override
	public boolean isBestial(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.getLegConfiguration().getBestialParts().contains(Wing.class) && getType().getRace().isBestialPartsAvailable();
	}
}
