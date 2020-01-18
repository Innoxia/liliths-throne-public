package com.lilithsthrone.game.character.body;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.AbstractLegType;
import com.lilithsthrone.game.character.body.types.FootStructure;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.1
 * @author Innoxia
 */
public class Leg implements BodyPartInterface {

	protected AbstractLegType type;
	protected FootStructure footStructure;
	protected LegConfiguration legConfiguration;

	public Leg(AbstractLegType type, LegConfiguration legConfiguration) {
		this.type = type;
		this.legConfiguration = legConfiguration;
		this.footStructure = type.getDefaultFootStructure();
	}

	@Override
	public AbstractLegType getType() {
		return type;
	}

	public FootStructure getFootStructure() {
		return footStructure;
	}

	//TODO check
	public String setFootStructure(GameCharacter owner, FootStructure footStructure) {
		if(owner==null) {
			this.footStructure = footStructure;
			return "";
		}
		
		if(this.getFootStructure()==footStructure) {
			return UtilText.parse(owner,
					"<p>"
						+ "[style.colourDisabled(Nothing happens, as [npc.namePos] [npc.feet] are already [npc.footStructure]...)]"
					+ "</p>");
		}
		
		if(!type.getFootType().getPermittedFootStructures().contains(footStructure)) {
			return UtilText.parse(owner,
					"<p>"
						+ "[style.colourDisabled(Nothing happens, as [npc.namePos] [npc.feet] cannot transform to be "+footStructure.getName()+"...)]"
					+ "</p>");
		}

		
		this.footStructure = footStructure;
		
		StringBuilder sb = new StringBuilder();

		sb.append("<p>"
				+ "[npc.Name] [npc.verb(let)] out a shocked gasp as [npc.she] suddenly [npc.verb(feel)] an intense heat shoot down into [npc.her] [npc.legs] and [npc.feet]."
				+ " Staggering and swaying, [npc.she] almost [npc.verb(lose)] [npc.her] balance as the structure of [npc.her] feet transforms and changes.</br>");
		
		switch(footStructure) {
			case DIGITIGRADE:
				sb.append("After just a moment, [npc.sheIs] left with [style.boldTfGeneric(digitgrade [npc.feet])], meaning that [npc.she] now [npc.verb(walk)] on [npc.her] toes, with [npc.her] heel being permanently raised.");
				break;
			case PLANTIGRADE:
				sb.append("After just a moment, [npc.sheIs] left with [style.boldTfGeneric(plantigrade [npc.feet])], meaning that [npc.she] now [npc.verb(walk)] with [npc.her] feet flat on the ground.");
				break;
			case UNGULIGRADE:
				sb.append("After just a moment, [npc.sheIs] left with [style.boldTfGeneric(unguligrade [npc.feet])], meaning that [npc.she] now [npc.verb(walk)] on [npc.her] [npc.toes], with the rest of [npc.her] foot being permanently raised.");
				break;
		}
		
		return UtilText.parse(owner, sb.toString())
				+ "</p>"
				+ "<p>"
				+ owner.postTransformationCalculation()
				+ "</p>";
		
	}

	public LegConfiguration getLegConfiguration() {
		return legConfiguration;
	}

	public void setLegConfigurationForced(AbstractLegType type, LegConfiguration legConfiguration) {
		this.type = type;
		this.footStructure = type.getDefaultFootStructure();
		this.legConfiguration = legConfiguration;
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

	public String setType(GameCharacter owner, AbstractLegType type) {
		if(!Main.game.isStarted() || owner==null) {
			this.type = type;
			this.footStructure = type.getDefaultFootStructure();
//			if(!type.isLegConfigurationAvailable(this.getLegConfiguration())) {
//				this.legConfiguration = RacialBody.valueOfRace(type.getRace()).getLegConfiguration();
//				this.getType().applyLegConfigurationTransformation(owner, legConfiguration, true);
//			}
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if(!type.isLegConfigurationAvailable(this.getLegConfiguration())) {
			return "<p>"
					+ "[style.italicsDisabled(Nothing happens, as [npc.namePos] "+legConfiguration.getName()+" body cannot be transformed into the type '"+type.getTransformName()+"'...)]"
					+ "</p>";
		}
		
		if (type == getType()) {
			if (owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(You already have the [pc.legs] of [pc.a_legRace], so nothing happens...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already has the [npc.legs] of [npc.a_legRace], so nothing happens...)]</p>");
			}
			
		} else {
			UtilText.transformationContentSB.setLength(0);
			
			if (owner.isPlayer()) {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "Your [pc.legs] start to wobble and feel weak, and as you look down to see what's wrong, they start to transform. ");
			} else {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "[npc.Name] almost loses [npc.her] balance as [npc.her] [npc.legs] start to transform. ");
			}
		}

		// Parse existing content before transformation:
		String s = UtilText.parse(owner, UtilText.transformationContentSB.toString());
		UtilText.transformationContentSB.setLength(0);
		UtilText.transformationContentSB.append(s);
		this.type = type;
		this.footStructure = type.getDefaultFootStructure();
		
		UtilText.transformationContentSB.append(type.getTransformationDescription(owner)+"</p>");
		
		UtilText.transformationContentSB.append(
				"<p>"
					+ "The transformation has left the structure of [npc.her] [npc.feet] as [style.boldTFGeneric("+this.footStructure.getName()+")]! "+this.footStructure.getDescription()
				+ "</p>");
		
		return UtilText.parse(owner, UtilText.transformationContentSB.toString())
				+ "<p>"
				+ owner.postTransformationCalculation()
				+ "</p>";
	}

	@Override
	public boolean isBestial(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.getLegConfiguration().getBestialParts().contains(Leg.class) && getType().getRace().isBestialPartsAvailable();
	}

}