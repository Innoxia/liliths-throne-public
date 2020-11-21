package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTentacleType;
import com.lilithsthrone.game.character.body.tags.TentacleTypeTag;
import com.lilithsthrone.game.character.body.types.TentacleType;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.8
 * @version 0.3.8.9
 * @author Innoxia
 */
public class Tentacle implements BodyPartInterface {

	public static final int MAXIMUM_COUNT = 9;
	
	protected AbstractTentacleType type;
	protected int tentacleCount;
	protected int girth;

	public Tentacle(AbstractTentacleType type) {
		this.type = type;
		this.tentacleCount = 1;
		this.girth = type.getDefaultGirth();
	}

	@Override
	public AbstractTentacleType getType() {
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
		
		if(gc.getTentacleGirth()!=PenetrationGirth.THREE_AVERAGE) {
			descriptors.add(gc.getTentacleGirth().getName());
		}
		
		return Util.randomItemFrom(descriptors);
	}

	public String setType(GameCharacter owner, AbstractTentacleType type) {
		if(!Main.game.isStarted() || owner==null) {
			this.type = type;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			if(type == TentacleType.NONE) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.verb(lack)] a tentacle, so nothing happens...)]</p>");
				
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.has] the [npc.tentacle] of [npc.a_tentacleRace], so nothing happens...)]</p>");
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(this.type == TentacleType.NONE) {
			sb.append(
					"<p>"
						+ "[npc.Name] rubs at [npc.her] lower back as [npc.she] [npc.verb(feel)] it growing hot and sensitive, and as [npc.she] [npc.do] so, something starts pushing out from under [npc.her] [npc.skin].");
		} else {
			sb.append(
					"<p>"
						+ (owner.getTentacleCount()==1
							?"[npc.Name] [npc.verb(feel)] [npc.her] [npc.tentacle] growing hot and itchy, and after just a moment it starts to transform."
							:"[npc.Name] [npc.verb(feel)] [npc.her] [npc.tentacles] growing hot and itchy, and after just a moment they start to transform."));
		}
		

		// If NONE, apply type change after. All else, before:
		if(type == TentacleType.NONE) {
			sb.append(type.getTransformationDescription(owner));
			this.type = type;
			
		} else {
			this.type = type;
			sb.append(type.getTransformationDescription(owner));
		}
		
		sb.append("</p>");
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public int getTentacleCount() {
		return tentacleCount;
	}

	public String setTentacleCount(GameCharacter owner, int tentacleCount) {
		tentacleCount = Math.max(1, Math.min(tentacleCount, MAXIMUM_COUNT));
		
		if(owner.getTentacleCount() == tentacleCount) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		owner.removeStatusEffect(StatusEffect.SUBSPECIES_BONUS);
		
		boolean removingTentacles = owner.getTentacleCount() > tentacleCount;
		this.tentacleCount = tentacleCount;

		owner.addStatusEffect(StatusEffect.SUBSPECIES_BONUS, -1);
		
		if (owner.getTentacleType() == TentacleType.NONE) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		if(removingTentacles) {
			return UtilText.parse(owner,
					"<p>"
						+ "A tingling feeling spreads over [npc.namePos] [npc.tentacles], before moving down and concentrating in [npc.her] lower back."
						+ " [npc.She] can't help but let out a cry as [npc.she] [npc.verb(feel)] some of them [style.boldShrink(shrinking away)] and disappearing back down into [npc.her] [npc.skin].<br/>"
						+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric("
								+(tentacleCount==1
									?"a single [npc.tentacle]"
									:Util.intToString(tentacleCount)+" [npc.tentacles]")
						+ ")]."
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "A tingling feeling spreads over [npc.namePos] [npc.tentacles], before moving down and concentrating in [npc.her] lower back."
						+ " [npc.She] can't help but let out a little cry as [npc.she] [npc.verb(feel)] new [npc.tentacles] [style.boldGrow(pushing up)] and growing out of [npc.her] [npc.skin].<br/>"
						+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric("
								+(tentacleCount==1
									?"a single [npc.tentacle]"
									:Util.intToString(tentacleCount)+" [npc.tentacles]")
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
	public String setTentacleGirth(GameCharacter owner, int girth) {
		if(owner==null) {
			this.girth = Math.max(0, Math.min(girth, PenetrationGirth.getMaximum()));
			return "";
		}
		
		if(!owner.hasTentacle()) {
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The girth of [npc.namePos] [npc.tentacle] doesn't change...)]</p>");
		}
		
		return owner.getTentacleType().getGirthTransformationDescription(owner, girthChange > 0);
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
	 * Uses basic equations to calculate a rough diameter dropoff, based on this tentacle type's TAPERING tag.
	 * 
	 * @param owner The character who this tentacle is attached to.
	 * @param atLength The length at which the diameter is to be found, measured from the base.
	 * @return The diameter.
	 */
	public float getDiameter(GameCharacter owner, float atLength) {
		float baseDiameter = (owner.getHeightValue() * 0.08f) * (1f + this.getGirth().getDiameterPercentageModifier());
		float lengthPercentage = Math.min(1, atLength / this.getLength(owner));
		
		if(this.getType().getTags().contains(TentacleTypeTag.TAPERING_EXPONENTIAL)) { // Exponential diameter tapering:
			// y = 1/(4x+1)
			// At maximum length, diameter is 20% base length
			return (1 / (4*lengthPercentage + 1)) * baseDiameter;
			
		} else if(this.getType().getTags().contains(TentacleTypeTag.TAPERING_LINEAR)) { // Linear diameter tapering:
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
		return owner.getLegConfiguration().getBestialParts().contains(Tentacle.class) && getType().getRace().isBestialPartsAvailable();
	}
}
