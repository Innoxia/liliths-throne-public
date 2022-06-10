package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractLegType;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public class Leg implements BodyPartInterface {

	public static final float LENGTH_PERCENTAGE_MIN_FERAL = 0.05f;
	public static final float LENGTH_PERCENTAGE_MIN = 2f;
	public static final float LENGTH_PERCENTAGE_MAX = 10f;
	
	protected AbstractLegType type;
	protected FootStructure footStructure;
	protected LegConfiguration legConfiguration;
	
	protected int girth;
	protected float lengthAsPercentageOfHeight;

	public Leg(AbstractLegType type, LegConfiguration legConfiguration) {
		this.type = type;
		this.legConfiguration = legConfiguration;
		this.footStructure = type.getDefaultFootStructure(legConfiguration);
		this.girth = PenetrationGirth.THREE_AVERAGE.getValue();
		this.lengthAsPercentageOfHeight = LegConfiguration.getDefaultSerpentTailLengthMultiplier();
	}

	@Override
	public AbstractLegType getType() {
		return type;
	}

	public FootStructure getFootStructure() {
		return footStructure;
	}

	//TODO check
	/**
	 * @return A description of the change. Returns an empty String if owner==null or if footStructure==FootStructure.TENTACLED
	 */
	public String setFootStructure(GameCharacter owner, FootStructure footStructure) {
		if(owner==null || footStructure==FootStructure.TENTACLED) {
			this.footStructure = footStructure;
			return "";
		}
		
		if(this.getFootStructure()==footStructure) {
			return UtilText.parse(owner,
					"<p>"
						+ "[style.colourDisabled(Nothing happens, as [npc.namePos] [npc.feet] are already [npc.footStructure]...)]"
					+ "</p>");
		}
		
		if(!type.getFootType().getPermittedFootStructures(owner.getLegConfiguration()).contains(footStructure)) {
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
			case NONE:
				sb.append("After just a moment, [npc.sheIs] left with [style.boldTfGeneric(no [npc.feet])]!");
				break;
			case DIGITIGRADE:
				sb.append("After just a moment, [npc.sheIs] left with [style.boldTfGeneric(digitigrade [npc.feet])], meaning that [npc.she] now [npc.verb(walk)] on [npc.her] toes, with [npc.her] heel being permanently raised.");
				break;
			case PLANTIGRADE:
				sb.append("After just a moment, [npc.sheIs] left with [style.boldTfGeneric(plantigrade [npc.feet])], meaning that [npc.she] now [npc.verb(walk)] with [npc.her] feet flat on the ground.");
				break;
			case UNGULIGRADE:
				sb.append("After just a moment, [npc.sheIs] left with [style.boldTfGeneric(unguligrade [npc.feet])], meaning that [npc.she] now [npc.verb(walk)] on [npc.her] [npc.toes], with the rest of [npc.her] foot being permanently raised.");
				break;
			case ARACHNOID:
				sb.append("After just a moment, [npc.sheIs] left with [style.boldTfGeneric(arachnoid [npc.feet])], meaning that [npc.she] now [npc.verb(walk)] on the ends of [npc.her] segmented arachnoid legs.");
				break;
			case TENTACLED:
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
		this.footStructure = type.getDefaultFootStructure(legConfiguration);
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
			this.footStructure = type.getDefaultFootStructure(this.getLegConfiguration());
			if(Main.game.isStarted() && !type.isLegConfigurationAvailable(this.getLegConfiguration())) {
				this.getType().applyLegConfigurationTransformation(owner, RacialBody.valueOfRace(type.getRace()).getLegConfiguration(), true, true);
			}
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if(type == getType()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.has] the [npc.legs] of [npc.a_legRace], so nothing happens...)]</p>");
		}
		
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(
				"<p>"
					+ "[npc.NamePos] [npc.legs] start to wobble and feel weak, and [npc.she] almost [npc.verb(lose)] [npc.her] balance as they start to transform. ");
		
		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		this.type = type;
		this.footStructure = type.getDefaultFootStructure(this.getLegConfiguration());
		
		sb.append(type.getTransformationDescription(owner)+"</p>");
		
		// Set tentacle variables:
		owner.setTentacleType(type.getTentacleType());
		owner.setTentacleCount(type.getTentacleCount());
		
		if(!type.isLegsReplacedByTentacles() && type.getDefaultFootStructure(this.getLegConfiguration())!=FootStructure.NONE) {
			sb.append(
					"<p>"
						+ "The transformation has left the structure of [npc.her] [npc.feet] as [style.boldTFGeneric("+this.footStructure.getName()+")]! "+this.footStructure.getDescription()
					+ "</p>");
		}
		
		if(!type.isLegConfigurationAvailable(this.getLegConfiguration())) {
			sb.append(this.getType().applyLegConfigurationTransformation(owner, type.getAllowedLegConfigurations().get(0), true, true));
		}
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
				+ owner.postTransformationCalculation()
				+ "</p>";
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Leg.class) && getType().getRace().isFeralPartsAvailable());
	}
	
	// These methods are just for if the Leg's LegConfiguration is of type TAIL_LONG:
	
	// Length:
	
	public float getLengthAsPercentageOfHeight() {
		return lengthAsPercentageOfHeight;
	}

	/**
	 * Sets the tails' length as a percentage of the owner's height. Value is bound to >=2f && <=10f
	 */
	public String setLengthAsPercentageOfHeight(GameCharacter owner, float lengthAsPercentageOfHeight) {
		if(owner==null) {
			// Allow for setting down to feral minimum, as this could be loading of a feral part:
			this.lengthAsPercentageOfHeight = Math.max(LENGTH_PERCENTAGE_MIN_FERAL, Math.min(lengthAsPercentageOfHeight, LENGTH_PERCENTAGE_MAX));
			return "";
		}
		
		float lengthChange = 0;
		
		float percentageMinimum = owner.isFeral()?LENGTH_PERCENTAGE_MIN_FERAL:LENGTH_PERCENTAGE_MIN;
		if (lengthAsPercentageOfHeight <= percentageMinimum) {
			if (this.lengthAsPercentageOfHeight != percentageMinimum) {
				lengthChange = percentageMinimum - this.lengthAsPercentageOfHeight;
				this.lengthAsPercentageOfHeight = percentageMinimum;
			}
		} else if (lengthAsPercentageOfHeight >= LENGTH_PERCENTAGE_MAX) {
			if (this.lengthAsPercentageOfHeight != LENGTH_PERCENTAGE_MAX) {
				lengthChange = LENGTH_PERCENTAGE_MAX - this.lengthAsPercentageOfHeight;
				this.lengthAsPercentageOfHeight = LENGTH_PERCENTAGE_MAX;
			}
			
		} else {
			if (this.lengthAsPercentageOfHeight != lengthAsPercentageOfHeight) {
				lengthChange = lengthAsPercentageOfHeight - this.lengthAsPercentageOfHeight;
				this.lengthAsPercentageOfHeight = lengthAsPercentageOfHeight;
			}
		}
		
		if(owner.getLegConfiguration()!=LegConfiguration.TAIL_LONG) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(As [npc.name] [npc.do] not have a "+LegConfiguration.TAIL_LONG+" lower-body, nothing seems to happen...)]</p>");
		}
		
		if(lengthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The length of [npc.namePos] [npc.tail] doesn't change...)]</p>");
		}
		
		String heightPercentageDescription = " (length is "+((int)(owner.getLegTailLengthAsPercentageOfHeight()*100))+"% of [npc.namePos] height)";
		if(lengthChange>0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up within [npc.her] [npc.tail+]."
						+ " Without any further warning of what's to come, [npc.her] [npc.tail] suddenly [style.boldGrow(grows longer)]."
						+ "<br/>"
						+ "[npc.She] now [npc.has] [style.boldTfGeneric([npc.a_tailLength] [npc.tail])]"+heightPercentageDescription+"!"
					+ "</p>");
				
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up within [npc.her] [npc.tail+]."
						+ " Without any further warning of what's to come, [npc.her] [npc.tail] suddenly [style.boldShrink(shortens)]."
						+ "<br/>"
						+ "[npc.She] now [npc.has] [style.boldTfGeneric([npc.a_tailLength] [npc.tail])]"+heightPercentageDescription+"!"
					+ "</p>");
		}
	}
	
	public int getLength(GameCharacter owner) {
		return (int) (owner.getHeightValue() * getLengthAsPercentageOfHeight());
	}

	public int getLength(Body body) {
		return (int) (body.getHeightValue() * getLengthAsPercentageOfHeight());
	}
	
	
	// Diameter:
	
	public float getBaseDiameter(GameCharacter owner) {
		return getDiameter(owner, 0);
	}

	/**
	 * Uses basic equations to calculate a rough diameter dropoff, based on this tail type's TAPERING tag.
	 * 
	 * @param owner The character who this tail is attached to.
	 * @param atLength The length at which the diameter is to be found, measured from the base.
	 * @return The diameter.
	 */
	public float getDiameter(GameCharacter owner, float atLength) {
//		System.out.println(owner.getName());
		// Waist-to-height ratio is 0.5 for non-ferals, 0.25 for ferals
		// It is a measurement of waist circumference divided by height
		float waistToHeightRatio = owner.isFeral()?0.25f:0.5f;
		float waistCircumference = owner.getHeightValue()*waistToHeightRatio;
		float waistDiameter = (float) (waistCircumference / Math.PI);
//		System.out.println("WD: "+waistDiameter);
		
		// Modify base diameter (at waist) by hip size:
		float baseDiameter = waistDiameter * (1f + owner.getLegTailGirth().getDiameterPercentageModifier());
//		System.out.println("BD: "+baseDiameter);
		float lengthPercentage = Math.min(1, atLength / this.getLength(owner));
		
		 // Linear diameter tapering:
		// y = 1 - (0.95x)
		// At maximum length, diameter is 5% base diameter
		float diameter = (1 - (0.95f * lengthPercentage)) * baseDiameter;

//		System.out.println("FD: "+diameter);
		
		return diameter;
	}

}