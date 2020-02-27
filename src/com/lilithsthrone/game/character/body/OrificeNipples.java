package com.lilithsthrone.game.character.body;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.OrificeInterface;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.?
 * @version 0.2.1
 * @author Innoxia
 */
public class OrificeNipples implements OrificeInterface {
	
	protected int elasticity;
	protected int plasticity;
	protected float capacity;
	protected float stretchedCapacity;
	protected boolean virgin;
	protected boolean crotchNipples;
	protected Set<OrificeModifier> orificeModifiers;

	public OrificeNipples(int wetness, float capacity, int elasticity, int plasticity, boolean virgin, boolean crotchNipples, Collection<OrificeModifier> orificeModifiers) {
		this.capacity = capacity;
		stretchedCapacity = capacity;
		this.elasticity = elasticity;
		this.plasticity = plasticity;
		this.virgin = virgin;
		this.crotchNipples = crotchNipples;
		
		this.orificeModifiers = new HashSet<>(orificeModifiers);
	}
	
	@Override
	public Wetness getWetness(GameCharacter owner) {
		return owner.getBreastMilkStorage().getAssociatedWetness();
	}
	
	@Override
	/**<b>DO NOT USE THIS. NIPPLE WETNESS IS DETERMINED BY BREAST LACTATION.</b>*/
	public String setWetness(GameCharacter owner, int wetness) {
		throw new IllegalAccessError(":BlobPeek: (Nipple wetness was attempted to be set manually!)");
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
		float capacityChange = this.capacity - oldCapacity;
		
		if (capacityChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The capacity of your [pc.nipples] doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The capacity of [npc.namePos] [npc.nipples] doesn't change...)]</p>");
			}
		}
		
		String capacityDescriptor = getCapacity().getDescriptor();
		if (capacityChange > 0) {
			if(oldCapacity == 0) { // Getting fuckable nipples:
				if (owner.isPlayer()) {
					return "<p>"
								+ "You squirm about uncomfortably as your [pc.nipples] grow unusually hard and sensitive."
								+ " A strange pressure starts to build up deep within your torso, and you let out a groan as you feel a drastic transformation taking place deep within your [pc.breasts]."
								+ " Your groan turns into [pc.a_moan+], which bursts out of your mouth as you feel your [pc.nipples] suddenly [style.boldGrow(spread open)], revealing a deep, fuckable passage that's formed behind them.<br/>"
								+ "You now have [style.boldSex(" + capacityDescriptor + ", fuckable [pc.nipples])]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>" 
								+ "[npc.Name] squirms about uncomfortably as [npc.her] [npc.nipples] grow unusually hard and sensitive."
								+ " A strange pressure starts to build up deep within [npc.her] torso, and [npc.she] lets out a groan as a drastic transformation takes place deep within [npc.her] [npc.breasts]."
								+ " [npc.Her] groan turns into [npc.a_moan+], which bursts out of [npc.her] mouth as [npc.her] [npc.nipples] suddenly [style.boldGrow(spread open)], revealing a deep, fuckable passage that's formed behind them.<br/>"
								+ "[npc.Name] now has [style.boldSex(" + capacityDescriptor + ", fuckable [npc.nipples])]!"
							+ "</p>");
				}
				
			} else { // Expanding fuckable nipples:
				if (owner.isPlayer()) {
					return "<p>"
								+ "You let out [pc.a_moan+] as you feel a familiar pressure building up behind your fuckable [pc.nipples], before they suddenly [style.boldGrow(grow)] both wider and deeper.<br/>"
								+ "You now have [style.boldSex(" + capacityDescriptor + " [pc.nipples])]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "[npc.Name] lets out [npc.a_moan+] as [npc.she] feels a familiar pressure building up behind [npc.her] fuckable [npc.nipples], before they suddenly [style.boldGrow(grow)] both wider and deeper.<br/>"
								+ "[npc.Name] now has [style.boldSex(" + capacityDescriptor + " [npc.nipples])]!"
							+ "</p>");
				}
			}
			
		} else {
			if(capacity == 0) { // Losing fuckable nipples:
				if (owner.isPlayer()) {
					return "<p>"
								+ "You squirm about uncomfortably as your [pc.nipples] grow unusually hard and sensitive."
								+ " A strange pressure starts to build up deep within your torso, and you let out a groan as you feel a drastic transformation taking place deep within your [pc.breasts]."
								+ " Your groan turns into a little sigh as you feel your [pc.nipples] suddenly [style.boldShrink(clench shut)], removing the ability for them to be fucked.<br/>"
								+ "You now have [style.boldSex(" + capacityDescriptor + ", non-fuckable [pc.nipples])]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>" 
								+ "[npc.Name] squirms about uncomfortably as [npc.her] [npc.nipples] grow unusually hard and sensitive."
								+ " A strange pressure starts to build up deep within [npc.her] torso, and [npc.she] lets out a groan as a drastic transformation takes place deep within [npc.her] [npc.breasts]."
								+ " [npc.Her] groan turns into a little sigh as [npc.her] [npc.nipples] suddenly [style.boldShrink(clench shut)], removing the ability for them to be fucked.<br/>"
								+ "[npc.Name] now has [style.boldSex(" + capacityDescriptor + ", non-fuckable [npc.nipples])]!"
							+ "</p>");
				}
				
			} else { // Shrinking fuckable nipples:
				if (owner.isPlayer()) {
					return "<p>"
								+ "You let out [pc.a_moan+] as you feel a familiar pressure building up behind your fuckable [pc.nipples], before they suddenly [style.boldShrink(shrink)] and become tighter.<br/>"
								+ "You now have [style.boldSex(" + capacityDescriptor + " [pc.nipples])]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "[npc.Name] lets out [npc.a_moan+] as [npc.she] feels a familiar pressure building up behind [npc.her] fuckable [npc.nipples], before they suddenly [style.boldShrink(shrink)] and become tighter.<br/>"
								+ "[npc.Name] now has [style.boldSex(" + capacityDescriptor + " [npc.nipples])]!"
							+ "</p>");
				}
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
	public int getMaximumPenetrationDepthComfortable(GameCharacter owner) {
		// Calculate breast depth as simply owner.getBreastSize().getMeasurement()
		if(this.isCrotchNipples()) {
			return (int) ((owner.getBreastCrotchSize().getMeasurement()) * 0.5f * (this.hasOrificeModifier(OrificeModifier.EXTRA_DEEP)?2:1) * (!owner.getBodyMaterial().isOrificesLimitedDepth()?4f:1));
		}
		return (int) ((owner.getBreastSize().getMeasurement()) * 0.5f * (this.hasOrificeModifier(OrificeModifier.EXTRA_DEEP)?2:1) * (!owner.getBodyMaterial().isOrificesLimitedDepth()?4f:1));
	}
	
	@Override
	public int getMaximumPenetrationDepthUncomfortable(GameCharacter owner) {
		return getMaximumPenetrationDepthComfortable(owner) * 2;
	}
	
	@Override
	public OrificeElasticity getElasticity() {
		return OrificeElasticity.getElasticityFromInt(elasticity);
	}

	@Override
	public String setElasticity(GameCharacter owner, int elasticity) {
		int oldElasticity = this.elasticity;
		this.elasticity = Math.max(0, Math.min(elasticity, OrificeElasticity.SEVEN_ELASTIC.getValue()));
		int elasticityChange = this.elasticity - oldElasticity;
		
		if (elasticityChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The elasticity of your [pc.nipples] doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The elasticity of [npc.namePos] [npc.nipples] doesn't change...)]</p>");
			}
		}
		
		String elasticityDescriptor = getElasticity().getDescriptor();
		if (elasticityChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out a little gasp as you feel a strange slackening sensation pulsating deep within your [pc.breasts] as your [pc.nipples]' [style.boldGrow(elasticity increases)].<br/>"
							+ "The transformation quickly passes, leaving you with [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " [pc.nipples])]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a little gasp as [npc.she] feels a strange slackening sensation pulsating deep within [npc.her] [npc.breasts] as [npc.her] [npc.nipple] [style.boldGrow(elasticity increases)].<br/>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " [npc.nipples])]!"
						+ "</p>");
			}
			
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out a little gasp as you feel a strange clenching sensation pulsating deep within your [pc.breasts] as your [pc.nipples]' [style.boldShrink(elasticity decreases)].<br/>"
							+ "The transformation quickly passes, leaving you with [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " [pc.nipples])]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a little gasp as [npc.she] feels a strange clenching sensation pulsating deep within [npc.her] [npc.breasts] as [npc.her] [npc.nipple] [style.boldShrink(elasticity decreases)].<br/>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " [npc.nipples])]!"
						+ "</p>");
			}
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
		int plasticityChange = this.plasticity - oldPlasticity;
		
		if (plasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The plasticity of [npc.namePos] [npc.nipples] doesn't change...)]</p>");
		}
		
		String plasticityDescriptor = getPlasticity().getDescriptor();
		if (plasticityChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out a little gasp as you feel a strange moulding sensation pulsating deep within your [pc.breasts] as your [pc.nipples]' [style.boldGrow(plasticity increases)].<br/>"
							+ "The transformation quickly passes, leaving you with [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " [pc.nipples])]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a little gasp as [npc.she] feels a strange moulding sensation pulsating deep within [npc.her] [npc.breasts] as [npc.her] [npc.nipples]' [style.boldGrow(plasticity increases)].<br/>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " [npc.nipples])]!"
						+ "</p>");
			}
			
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out a little gasp as you feel a strange softening sensation pulsating deep within your [pc.breasts] as your [pc.nipples]' [style.boldShrink(plasticity decreases)].<br/>"
							+ "The transformation quickly passes, leaving you with [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " [pc.nipples])]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a little gasp as [npc.she] feels a strange softening sensation pulsating deep within [npc.her] [npc.breasts] as [npc.her] [npc.nipples]' [style.boldShrink(plasticity decreases)].<br/>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " [npc.nipples])]!"
						+ "</p>");
			}
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
								+ " [npc.sheIs] shocked to discover that [npc.she] has an incredible amount of control over them, allowing [npc.her] to expertly grip and squeeze down on any penetrating object.<br/>"
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
										+ " [style.boldGrow(a series of little wriggling tentacles)], over which [npc.she] has limited control.<br/>"
								+ "[style.boldSex(The interior of [npc.namePos] "+nipplesString+" are now filled with little tentacles, which wriggle with a mind of their own!)]"
							+ "</p>");
				}
				break;
			case PUFFY:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] lets out a little cry as [npc.she] feels a tingling sensation running over [npc.her] "+nipplesString+", before they suddenly swell out and [style.boldGrow(puff up)].<br/>"
							+ "[style.boldSex([npc.NamePos] "+nipplesString+" are now extremely puffy!)]"
						+ "</p>");
			case EXTRA_DEEP:
				if(isCrotchNipples()?owner.isBreastCrotchFuckableNipplePenetration():owner.isBreastFuckableNipplePenetration()) {
					return UtilText.parse(owner,
							"<p>"
								+ "[npc.Name] can't help but let out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a throbbing sensation sinking deep into [npc.her] "+breastsString+"."
								+ " Within moments, the feeling fades away, and [npc.name] [npc.verb(find)] [npc.herself] instinctively knowing that [npc.her] [style.boldGrow(fuckable "+nipplesString+" have significantly deepened)].<br/>"
								+ "[style.boldSex([npc.NamePos] "+nipplesString+" are now extra deep, allowing them to take double the normal length of penetrative objects!)]"
							+ "</p>");
				}
				break;
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
			case EXTRA_DEEP:
				if(isCrotchNipples()?owner.isBreastCrotchFuckableNipplePenetration():owner.isBreastFuckableNipplePenetration()) {
					return UtilText.parse(owner,
							"<p>"
								+ "[npc.Name] can't help but let out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a sudden tightening sensation deep within [npc.her] "+breastsString+"."
								+ " Within moments, the feeling fades away, and [npc.name] [npc.verb(find)] [npc.herself] instinctively knowing that [npc.her] [style.boldShrink(fuckable "+nipplesString+" have lost a significant amount of depth)].<br/>"
								+ "[style.boldSex([npc.NamePos] "+nipplesString+" are no longer extra deep, and can only take an average length of penetrative objects!)]"
							+ "</p>");
				}
				break;
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}

	public Set<OrificeModifier> getOrificeModifiers() {
		return orificeModifiers;
	}

}
