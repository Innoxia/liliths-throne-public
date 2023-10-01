package com.lilithsthrone.game.character.body;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.OrificeInterface;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.?
 * @version 0.3.7
 * @author Innoxia
 */
public class OrificeNipples implements OrificeInterface {

	protected int wetness;
	protected float capacity;
	protected float stretchedCapacity;
	protected int depth;
	protected int elasticity;
	protected int plasticity;
	protected boolean virgin;
	protected Set<OrificeModifier> orificeModifiers;

	protected boolean crotchNipples;

	public OrificeNipples(int wetness, float capacity, int depth, int elasticity, int plasticity, boolean virgin, boolean crotchNipples, Collection<OrificeModifier> orificeModifiers) {
		this.wetness = wetness;
		this.capacity = capacity;
		stretchedCapacity = capacity;
		this.depth = depth;
		this.elasticity = elasticity;
		this.plasticity = plasticity;
		this.virgin = virgin;
		
		this.orificeModifiers = new HashSet<>(orificeModifiers);
		
		this.crotchNipples = crotchNipples;
	}
	
	@Override
	public Wetness getWetness(GameCharacter owner) {
		return owner.getBreastMilkStorage().getAssociatedWetness();
	}
	
	@Override
	/**<b>DO NOT USE THIS. NIPPLE WETNESS IS DETERMINED BY BREAST LACTATION.</b>*/
	public String setWetness(GameCharacter owner, int wetness) {
		throw new IllegalAccessError("Nipple wetness was attempted to be set manually!");
	}
	
	@Override
	public Capacity getCapacity() {
		return Capacity.getCapacityFromValue((int)capacity);
	}
	
	@Override
	public float getRawCapacityValue() {
		return capacity;
	}
	
	@Override
	public String setCapacity(GameCharacter owner, float capacity, boolean setStretchedValueToNewValue) {
		float oldCapacity = this.capacity;
		this.capacity = Math.max(0, Math.min(capacity, Capacity.SEVEN_GAPING.getMaximumValue(false)));
		if(setStretchedValueToNewValue) {
			this.stretchedCapacity = this.capacity;
		}
		if(owner==null) {
			return "";
		}
		
		float capacityChange = this.capacity - oldCapacity;
		String nipplesString = isCrotchNipples()?"[npc.crotchNipples]":"[npc.nipples]";
		String breastsString = isCrotchNipples()?"[npc.crotchBoobs]":"[npc.breasts]";
		
		if (capacityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The capacity of [npc.namePos] "+nipplesString+" doesn't change...)]</p>");
		}
		
		String capacityDescriptor = getCapacity().getDescriptor();
		if (capacityChange > 0) {
			if(oldCapacity == 0) { // Getting fuckable nipples:
				return UtilText.parse(owner,
						"<p>" 
							+ "[npc.Name] [npc.verb(squirm)] about uncomfortably as [npc.her] "+nipplesString+" grow unusually hard and sensitive."
							+ " A strange pressure starts to build up within [npc.her] torso, and [npc.she] [npc.verb(let)] out a deep sigh as a drastic transformation takes place within [npc.her] "+breastsString+"."
							+ " Quickly overwhelmed by the growing intensity of the pressure building up within [npc.her] "+breastsString+", [npc.namePos] sigh turns into [npc.a_moan+],"
								+ " which bursts out of [npc.her] mouth as [npc.her] "+nipplesString+" suddenly [style.boldGrow(spread open)], revealing deep, fuckable passages that have formed behind them.<br/>"
							+ "[npc.Name] now [npc.has] [style.boldSex(" + capacityDescriptor + ", fuckable "+nipplesString+")]!"
						+ "</p>");
				
			} else { // Expanding fuckable nipples:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a familiar pressure building up behind [npc.her] fuckable "+nipplesString+", before they suddenly [style.boldGrow(grow)] both wider and deeper.<br/>"
							+ "[npc.Name] now [npc.has] [style.boldSex(" + capacityDescriptor + " "+nipplesString+")]!"
						+ "</p>");
			}
			
		} else {
			if(capacity == 0) { // Losing fuckable nipples:
				return UtilText.parse(owner,
						"<p>" 
							+ "[npc.Name] [npc.verb(squirm)] about uncomfortably as [npc.her] "+nipplesString+" grow unusually hard and sensitive."
							+ " An intense pressure starts to build up within [npc.her] torso, and [npc.she] [npc.verb(let)] out a deep sigh as [npc.her] "+nipplesString+" suddenly [style.boldShrink(clench shut)], removing the ability for them to be fucked.<br/>"
							+ "[npc.Name] now [npc.has] [style.boldSex(" + capacityDescriptor + ", non-fuckable "+nipplesString+")]!"
						+ "</p>");
				
			} else { // Shrinking fuckable nipples:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a familiar pressure building up behind [npc.her] fuckable "+nipplesString+", before they suddenly [style.boldShrink(shrink)] and become tighter.<br/>"
							+ "[npc.Name] now [npc.has] [style.boldSex(" + capacityDescriptor + " "+nipplesString+")]!"
						+ "</p>");
			}
		}
	}
	
	@Override
	public float getStretchedCapacity() {
		return stretchedCapacity;
	}

	@Override
	public boolean setStretchedCapacity(float stretchedCapacity) {
		float oldStretchedCapacity = this.stretchedCapacity;
		this.stretchedCapacity = Math.max(0, Math.min(stretchedCapacity, Capacity.SEVEN_GAPING.getMaximumValue(false)));
		return oldStretchedCapacity != this.stretchedCapacity;
	}

	@Override
	public int getMaximumPenetrationDepthComfortable(GameCharacter owner, OrificeDepth depth) {
		// Calculate breast depth as simply owner.getBreastSize().getMeasurement()
		if(this.isCrotchNipples()) {
			return (int) ((owner.getBreastCrotchSize().getMeasurement()) * 0.5f * depth.getDepthPercentage());
		}
		return (int) ((owner.getBreastSize().getMeasurement()) * 0.5f * depth.getDepthPercentage());
	}
	
	@Override
	public int getMaximumPenetrationDepthUncomfortable(GameCharacter owner, OrificeDepth depth) {
		if(Main.game.isElasticityAffectDepthEnabled() && OrificeElasticity.getElasticityFromInt(elasticity).isExtendingUncomfortableDepth()) {
			return (int) (getMaximumPenetrationDepthComfortable(owner, depth) * (float)elasticity/1.5f);
		} else {
			return getMaximumPenetrationDepthComfortable(owner, depth) * 2;
		}
	}
	
	@Override
	public OrificeDepth getDepth(GameCharacter owner) {
		if(owner!=null && !owner.getBodyMaterial().isOrificesLimitedDepth()) {
			return OrificeDepth.SEVEN_FATHOMLESS;
		}
		return OrificeDepth.getDepthFromInt(depth);
	}

	@Override
	public String setDepth(GameCharacter owner, int depth) {
		String nipplesString = isCrotchNipples()?"[npc.crotchNipples]":"[npc.nipples]";
		String breastsString = isCrotchNipples()?"[npc.crotchBoobs]":"[npc.breasts]";
		
		if(owner!=null && !owner.getBodyMaterial().isOrificesLimitedDepth()) {
			return UtilText.parse(owner,
					"<p style='text-align:center;'>[style.colourSex(Due to being made out of "+owner.getBodyMaterial().getName()+", the depth of [npc.namePos] "+OrificeDepth.SEVEN_FATHOMLESS.getDescriptor()+" "+nipplesString+" can't be changed...)]</p>");
		}
		
		int oldDepth = this.depth;
		this.depth = Math.max(0, Math.min(depth, OrificeDepth.SEVEN_FATHOMLESS.getValue()));
		if(owner==null) {
			return "";
		}
		
		int depthChange = this.depth - oldDepth;
		
		if(depthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The depth of [npc.namePos] "+nipplesString+" doesn't change...)]</p>");
		}
		
		String depthDescriptor = getDepth(owner).getDescriptor();
		if(depthChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] can't help but let out a surprised gasp as [npc.she] [npc.verb(feel)] an alarming pressure pulsating deep within [npc.her] "+breastsString+"."
						+ " Before [npc.her] gasp can turn into a distressed cry, the pressure suddenly fades away, leaving [npc.herHim] instinctively knowing that [npc.her] "+nipplesString+" [style.boldGrow(have deepened)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + " " + depthDescriptor + " "+nipplesString+")]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] can't help but let out a surprised gasp as [npc.she] [npc.verb(feel)] an alarming tightening sensation deep within [npc.her] "+breastsString+"."
						+ " Before [npc.her] gasp can turn into a distressed cry, the feeling suddenly fades away, leaving [npc.herHim] instinctively knowing that [npc.her] "+nipplesString+" [style.boldShrink(have become shallower)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + " " + depthDescriptor + " "+nipplesString+")]!"
					+ "</p>");
		}
	}
	
	@Override
	public OrificeElasticity getElasticity() {
		return OrificeElasticity.getElasticityFromInt(elasticity);
	}

	@Override
	public String setElasticity(GameCharacter owner, int elasticity) {
		int oldElasticity = this.elasticity;
                if (owner.getBodyMaterial().isOrificesAlwaysMaximumElasticity()) {
                        this.elasticity = OrificeElasticity.SEVEN_ELASTIC.getValue();
                        if (oldElasticity != this.elasticity) {
                                return UtilText.parse(owner,
					"<p style='text-align:center;'>[style.colourSex(Due to being made out of "+owner.getBodyMaterial().getName()+", the [npc.namePos] "+OrificeElasticity.SEVEN_ELASTIC.getDescriptor()+" nipples can't be changed...)]</p>");
                        } else {
                                return "";
                        }
                }
		this.elasticity = Math.max(0, Math.min(elasticity, OrificeElasticity.SEVEN_ELASTIC.getValue()));
		if(owner==null) {
			return "";
		}
		
		int elasticityChange = this.elasticity - oldElasticity;
		String nipplesString = isCrotchNipples()?"[npc.crotchNipples]":"[npc.nipples]";
		String breastsString = isCrotchNipples()?"[npc.crotchBoobs]":"[npc.breasts]";
		
		if (elasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The elasticity of [npc.namePos] "+nipplesString+" doesn't change...)]</p>");
		}
		
		
		String elasticityDescriptor = getElasticity().getDescriptor();
		if (elasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] can't help but let out a surprised gasp as [npc.she] [npc.verb(feel)] a strange slackening sensation pulsating deep within [npc.her] "+breastsString+"."
						+ " Just as quickly as it started, the feeling passes, and [npc.she] very quickly [npc.verb(realise)] that [npc.her] "+nipplesString+" [style.boldGrow(have gained some elasticity)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " "+nipplesString+")]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] can't help but let out a surprised gasp as [npc.she] [npc.verb(feel)] a strange clenching sensation pulsating deep within [npc.her] "+breastsString+"."
						+ " Just as quickly as it started, the feeling passes, and [npc.she] very quickly [npc.verb(realise)] that [npc.her] "+nipplesString+" [style.boldShrink(have lost some elasticity)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " "+nipplesString+")]!"
					+ "</p>");
		}
	}
	
	@Override
	public OrificePlasticity getPlasticity() {
		return OrificePlasticity.getElasticityFromInt(plasticity);
	}

	@Override
	public String setPlasticity(GameCharacter owner, int plasticity) {
		int oldPlasticity = this.plasticity;
		this.plasticity = Math.max(0, Math.min(plasticity, OrificePlasticity.SEVEN_MOULDABLE.getValue()));
		if(owner==null) {
			return "";
		}
		
		int plasticityChange = this.plasticity - oldPlasticity;
		String nipplesString = isCrotchNipples()?"[npc.crotchNipples]":"[npc.nipples]";
		String breastsString = isCrotchNipples()?"[npc.crotchBoobs]":"[npc.breasts]";
		
		if (plasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The plasticity of [npc.namePos] "+nipplesString+" doesn't change...)]</p>");
		}
		
		String plasticityDescriptor = getPlasticity().getDescriptor();
		if (plasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a shocked gasp as [npc.she] suddenly [npc.verb(feel)] a strange hardening sensation pulsating deep within [npc.her] "+breastsString+"."
						+ " Before [npc.she] [npc.has] any time to panic, the feeling quickly fades away, leaving [npc.herHim] instinctively knowing that [npc.her] "+nipplesString+" [style.boldGrow(have gained some plasticity)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " "+nipplesString+")]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a shocked gasp as [npc.she] suddenly [npc.verb(feel)] a strange softening sensation pulsating deep within [npc.her] "+breastsString+"."
						+ " Before [npc.she] [npc.has] any time to panic, the feeling quickly fades away, leaving [npc.herHim] instinctively knowing that [npc.her] "+nipplesString+" [style.boldShrink(have lost some plasticity)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " "+nipplesString+")]!"
					+ "</p>");
		}
	}

	@Override
	public boolean isVirgin() {
		return virgin;
	}

	@Override
	public void setVirgin(boolean virgin) {
		this.virgin = virgin;
	}

	public boolean isCrotchNipples() {
		return crotchNipples;
	}
	
	@Override
	public boolean hasOrificeModifier(OrificeModifier modifier) {
		return orificeModifiers.contains(modifier);
	}

	@Override
	public String addOrificeModifier(GameCharacter owner, OrificeModifier modifier) {
		if(hasOrificeModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		orificeModifiers.add(modifier);
		
		if(owner==null) {
			return "";
		}

		String nipplesString = isCrotchNipples()?"[npc.crotchNipples]":"[npc.nipples]";
		String breastsString = isCrotchNipples()?"[npc.crotchBoobs]":"[npc.breasts]";
		
		switch(modifier) {
			case MUSCLE_CONTROL:
				if(isCrotchNipples()?owner.isBreastCrotchFuckableNipplePenetration():owner.isBreastFuckableNipplePenetration()) {
					return UtilText.parse(owner,
							"<p>"
								+ "An intense pressure suddenly swells up deep within [npc.namePos] "+breastsString+", and [npc.she] can't help but let out [npc.a_moan+] as [npc.she] feels a series of [style.boldGrow(extra muscles)]"
									+ " growing down into the lining of [npc.her] "+nipplesString+"."
								+ " [npc.sheIs] shocked to discover that [npc.sheHasFull] an incredible amount of control over them, allowing [npc.herHim] to expertly grip and squeeze down on any penetrating object.<br/>"
								+ "[style.boldSex(The interior of [npc.namePos] "+nipplesString+" are now lined with an intricate series of muscles!)]"
							+ "</p>");
				}
				break;
			case RIBBED:
				if(isCrotchNipples()?owner.isBreastCrotchFuckableNipplePenetration():owner.isBreastFuckableNipplePenetration()) {
					return UtilText.parse(owner,
							"<p>"
								+ "An intense pressure suddenly swells up deep within [npc.namePos] "+breastsString+", and [npc.she] can't help but let out [npc.a_moan+] as [npc.she] feels a series of [style.boldGrow(fleshy, highly-sensitive ribs)]"
									+ " growing down into the lining of [npc.her] "+nipplesString+"."
								+ " Shifting [npc.her] "+breastsString+" around a little, a jolt of pleasure shoots through [npc.her] torso as [npc.she] feels [npc.her] new additions rub over one another, causing [npc.herHim] to let out another [npc.moan+].<br/>"
								+ "[style.boldSex(The interior of [npc.namePos] "+nipplesString+" are now lined with fleshy, pleasure-inducing ribs!)]"
							+ "</p>");
				}
				break;
			case TENTACLED:
				if(isCrotchNipples()?owner.isBreastCrotchFuckableNipplePenetration():owner.isBreastFuckableNipplePenetration()) {
					return UtilText.parse(owner,
							"<p>"
								+ "An intense pressure suddenly swells up deep within [npc.namePos] "+breastsString+", and [npc.she] can't help but let out [npc.a_moan+] as [npc.she] feels a strange tingling sensation deep down in [npc.her] "+nipplesString+"."
								+ " The tingling sensation grows stronger, and a surprised cry bursts out from [npc.her] mouth as [npc.she] suddenly discovers that the insides of [npc.her] "+nipplesString+" are now filled with"
										+ " [style.boldGrow(a series of little wriggling tentacles)], over which [npc.sheHasFull] limited control.<br/>"
								+ "[style.boldSex(The interior of [npc.namePos] "+nipplesString+" are now filled with little tentacles, which wriggle with a mind of their own!)]"
							+ "</p>");
				}
				break;
			case PUFFY:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] [npc.verb(let)] out a little cry as [npc.she] [npc.verb(feel)] a tingling sensation running over [npc.her] "+nipplesString+", before they suddenly swell out and [style.boldGrow(puff up)].<br/>"
							+ "[style.boldSex([npc.NamePos] "+nipplesString+" are now extremely puffy!)]"
						+ "</p>");
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}

	@Override
	public String removeOrificeModifier(GameCharacter owner, OrificeModifier modifier) {
		if(!hasOrificeModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		orificeModifiers.remove(modifier);
		
		if(owner==null) {
			return "";
		}

		String nipplesString = isCrotchNipples()?"[npc.crotchNipples]":"[npc.nipples]";
		String breastsString = isCrotchNipples()?"[npc.crotchBoobs]":"[npc.breasts]";
		
		switch(modifier) {
			case MUSCLE_CONTROL:
				if(isCrotchNipples()?owner.isBreastCrotchFuckableNipplePenetration():owner.isBreastFuckableNipplePenetration()) {
					return UtilText.parse(owner,
							"<p>"
								+ "A soothing warmth slowly washes up through [npc.namePos] torso, and an involuntary [pc.moan] drifts out from between [npc.her] [npc.lips] as [npc.she] [npc.verb(feel)] [npc.her] [style.boldShrink(extra muscles)]"
									+ " melt back into the flesh of [npc.her] "+breastsString+".<br/>"
								+ "[style.boldSex(The interior of [npc.namePos] "+nipplesString+" are no longer lined with an intricate series of muscles!)]"
							+ "</p>");
				}
				break;
			case RIBBED:
				if(isCrotchNipples()?owner.isBreastCrotchFuckableNipplePenetration():owner.isBreastFuckableNipplePenetration()) {
					return UtilText.parse(owner,
							"<p>"
								+ "A soothing warmth slowly washes up through [npc.namePos] torso, and an involuntary [pc.moan] drifts out from between [npc.her] [npc.lips] as"
									+ " [npc.she] [npc.verb(feel)] [npc.her] [style.boldShrink(fleshy, highly-sensitive ribs)] melt back into the flesh of [npc.her] "+breastsString+".<br/>"
								+ "[style.boldSex(The interior of [npc.namePos] "+nipplesString+" are no longer ribbed!)]"
							+ "</p>");
				}
				break;
					
			case TENTACLED:
				if(isCrotchNipples()?owner.isBreastCrotchFuckableNipplePenetration():owner.isBreastFuckableNipplePenetration()) {
					return UtilText.parse(owner,
							"<p>"
								+ "A soothing warmth slowly washes up through [npc.namePos] torso, and an involuntary [npc.moan] drifts out from between [npc.her] [npc.lips]"
									+ " as [npc.she] [npc.verb(feel)] [npc.her] [style.boldShrink(little wriggling tentacles)] melt back into the flesh of [npc.her] "+breastsString+".<br/>"
								+ "[style.boldSex(The interior of [npc.namePos] "+nipplesString+" are no longer filled with little tentacles!)]"
							+ "</p>");
				}
				break;
			case PUFFY:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] [npc.verb(let)] out a sigh as [npc.her] "+nipplesString+" [style.boldShrink(shrink down)] and lose their puffiness.<br/>"
							+ "[style.boldSex([npc.NamePos] "+nipplesString+" are no longer extremely puffy!)]"
						+ "</p>");
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}

	public Set<OrificeModifier> getOrificeModifiers() {
		return orificeModifiers;
	}

}
