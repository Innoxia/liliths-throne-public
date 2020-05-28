package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.tags.TailTypeTag;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.7
 * @author Innoxia
 */
public class Tail implements BodyPartInterface {

	public static final int MAXIMUM_COUNT = 9;
	
	protected AbstractTailType type;
	protected int tailCount;
	protected int girth;

	public Tail(AbstractTailType type) {
		this.type = type;
		this.tailCount = 1;
		this.girth = type.getDefaultGirth();
	}

	@Override
	public AbstractTailType getType() {
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
		List<String> descriptors = new ArrayList<>();
		
		descriptors.add(type.getDescriptor(gc));
		
		if(gc.getTailGirth()!=PenetrationGirth.THREE_AVERAGE) {
			descriptors.add(gc.getTailGirth().getName());
		}
		
		return Util.randomItemFrom(descriptors);
	}

	public String setType(GameCharacter owner, AbstractTailType type) {
		if(!Main.game.isStarted() || owner==null) {
			this.type = type;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			if(type == TailType.NONE) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.verb(lack)] a tail, so nothing happens...)]</p>");
				
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.has] the [npc.tail] of [npc.a_tailRace], so nothing happens...)]</p>");
			}
		}
		
		UtilText.transformationContentSB.setLength(0);
		
		if(this.type == TailType.NONE) {
			UtilText.transformationContentSB.append(
					"<p>"
						+ "[npc.Name] rubs at [npc.her] lower back as [npc.she] [npc.verb(feel)] it growing hot and sensitive, and as [npc.she] [npc.do] so, something starts pushing out from under [npc.her] [npc.skin].");
		} else {
			UtilText.transformationContentSB.append(
					"<p>"
						+ (owner.getTailCount()==1
							?"[npc.Name] [npc.verb(feel)] [npc.her] [npc.tail] growing hot and itchy, and after just a moment it starts to transform."
							:"[npc.Name] [npc.verb(feel)] [npc.her] [npc.tails] growing hot and itchy, and after just a moment they start to transform."));
		}
		

		// If NONE, apply type change after. All else, before:
		if(type == TailType.NONE) {
			UtilText.transformationContentSB.append(type.getTransformationDescription(owner));
			this.type = type;
			
		} else {
			this.type = type;
			UtilText.transformationContentSB.append(type.getTransformationDescription(owner));
		}
		
		UtilText.transformationContentSB.append("</p>");
		
		return UtilText.parse(owner, UtilText.transformationContentSB.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public int getTailCount() {
		return tailCount;
	}

	public String setTailCount(GameCharacter owner, int tailCount, boolean overrideYoukoLimitations) {
		tailCount = Math.max(1, Math.min(tailCount, MAXIMUM_COUNT));
		
		if(owner.getTailCount() == tailCount) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		if(owner.getTailType().equals(TailType.FOX_MORPH_MAGIC) && !overrideYoukoLimitations) {
			return "<p style='text-align:center;'>"
						+ "[style.colourMinorBad([npc.NamePos] arcane-infused "
							+(this.tailCount==1
								?"tail absorbs and nullifies"
								:"tails absorb and nullify")
							+" the transformative effect, preventing any alteration to the number of tails [npc.she] [npc.has]!)]"
					+ "</p>";
		}
		
		owner.removeStatusEffect(StatusEffect.SUBSPECIES_BONUS);
		
		boolean removingTails = owner.getTailCount() > tailCount;
		this.tailCount = tailCount;

		owner.addStatusEffect(StatusEffect.SUBSPECIES_BONUS, -1);
		
		if (owner.getTailType() == TailType.NONE) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		if(removingTails) {
			return UtilText.parse(owner,
					"<p>"
						+ "A tingling feeling spreads over [npc.namePos] [npc.tails], before moving down and concentrating in [npc.her] lower back."
						+ " [npc.She] can't help but let out a cry as [npc.she] [npc.verb(feel)] some of them [style.boldShrink(shrinking away)] and disappearing back down into [npc.her] [npc.skin].<br/>"
						+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric("
								+(tailCount==1
									?"a single [npc.tail]"
									:Util.intToString(tailCount)+" [npc.tails]")
						+ ")]."
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "A tingling feeling spreads over [npc.namePos] [npc.tails], before moving down and concentrating in [npc.her] lower back."
						+ " [npc.She] can't help but let out a little cry as [npc.she] [npc.verb(feel)] new [npc.tails] [style.boldGrow(pushing up)] and growing out of [npc.her] [npc.skin].<br/>"
						+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric("
								+(tailCount==1
									?"a single [npc.tail]"
									:Util.intToString(tailCount)+" [npc.tails]")
						+ ")]."
					+ "</p>");
		}
	}

	// Girth:

	public PenetrationGirth getGirth() {
		return PenetrationGirth.getGirthFromInt(girth);
	}

	public int getRawGirthValue() {
		return girth;
	}

	/**
	 * Sets the girth. Value is bound to >=0 && <=PenisGirth.FOUR_FAT.getValue()
	 */
	public String setTailGirth(GameCharacter owner, int girth) {
		if(owner==null) {
			this.girth = Math.max(0, Math.min(girth, PenetrationGirth.getMaximum()));
			return "";
		}
		
		if(!owner.hasTail()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		int girthChange = 0;
		
		if (girth <= 0) {
			if (this.girth != 0) {
				girthChange = 0 - this.girth;
				this.girth = 0;
			}
		} else if (girth >= PenetrationGirth.getMaximum()) {
			if (this.girth != PenetrationGirth.getMaximum()) {
				girthChange = PenetrationGirth.getMaximum() - this.girth;
				this.girth = PenetrationGirth.getMaximum();
			}
		} else {
			if (this.girth != girth) {
				girthChange = girth - this.girth;
				this.girth = girth;
			}
		}
		
		if(girthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The girth of [npc.namePos] [npc.tail] doesn't change...)]</p>");
		}
		
		return owner.getTailType().getGirthTransformationDescription(owner, girthChange > 0);
	}

	// Length:

	public int getLength(GameCharacter owner) {
		return (int) (owner.getHeightValue() * type.getLengthAsPercentageOfHeight());
	}
	
	
	// Diameter:

	public static float getGenericDiameter(int height, PenetrationGirth girth) {
		return Units.round((height * 0.08f) * (1f + girth.getDiameterPercentageModifier()), 2);
	}
	
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
		float baseDiameter = (owner.getHeightValue() * 0.08f) * (1f + this.getGirth().getDiameterPercentageModifier());
		float lengthPercentage = Math.min(1, atLength / this.getLength(owner));
		
		if(this.getType().getTags().contains(TailTypeTag.TAPERING_EXPONENTIAL)) { // Exponential diameter tapering:
			// y = 1/(4x+1)
			// At maximum length, diameter is 20% base length
			return (1 / (4*lengthPercentage + 1)) * baseDiameter;
			
		} else if(this.getType().getTags().contains(TailTypeTag.TAPERING_LINEAR)) { // Linear diameter tapering:
			// y = 1 - (0.8x)
			// At maximum length, diameter is 20% base length
			return (1 - (0.8f * lengthPercentage)) * baseDiameter;
			
		}
		// No diameter tapering:
		return Units.round(baseDiameter, 2);
	}
	
	@Override
	public boolean isBestial(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.getLegConfiguration().getBestialParts().contains(Tail.class) && getType().getRace().isBestialPartsAvailable();
	}
}
