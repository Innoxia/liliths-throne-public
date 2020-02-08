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

public class OrificePenisUrethra implements OrificeInterface {
	
	protected int wetness;
	protected int elasticity;
	protected int plasticity;
	protected float capacity;
	protected float stretchedCapacity;
	protected boolean virgin;
	protected Set<OrificeModifier> orificeModifiers;

	public OrificePenisUrethra(int wetness, int capacity, int elasticity, int plasticity, boolean virgin, Collection<OrificeModifier> orificeModifiers) {
		this.wetness = wetness;
		this.capacity = capacity;
		stretchedCapacity = capacity;
		this.elasticity = elasticity;
		this.plasticity = plasticity;
		this.virgin = virgin;
		
		this.orificeModifiers = new HashSet<>(orificeModifiers);
	}
	
	@Override
	public Wetness getWetness(GameCharacter owner) {
		return Wetness.valueOf(wetness);
	}

	@Override
	public String setWetness(GameCharacter owner, int wetness) {
		if (!owner.hasPenis()) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(You lack a penis, so nothing happens...)]</p>";
				
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] lacks a penis, so nothing happens...)]</p>");
			}
		}
		
		int oldWetness = this.wetness;
		this.wetness = Math.max(0, Math.min(wetness, Wetness.SEVEN_DROOLING.getValue()));
		int wetnessChange = this.wetness - oldWetness;
		
		if (wetnessChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(Your precum production doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.NamePos] precum production doesn't change...)]</p>");
			}
		}
		
		String wetnessDescriptor = getWetness(owner).getDescriptor();
		if (wetnessChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "Your [pc.eyes] widen as you feel your [pc.cock+] suddenly grow hard, and you let out [pc.a_moan+] as you feel a slick stream of precum oozing out of the tip as its production [style.boldGrow(increases)].<br/>"
							+ "You now have [style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " urethra)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.NamePos] [npc.eyes] widen as [npc.she] feels [npc.her] [npc.cock+] suddenly grow hard, and [npc.she] lets out [npc.a_moan+] as a slick stream of precum oozes out of the tip as its production [style.boldGrow(increases)].<br/>"
							+ "[npc.She] now has [style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " urethra)]!"
						+ "</p>");
			}
			
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You shift about uncomfortably and let out a frustrated groan as you feel your precum production [style.boldShrink(decrease)].<br/>"
							+ "You now have [style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " urethra)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] shifts about uncomfortably and lets out a frustrated groan as [npc.she] feels [npc.her] precum production [style.boldShrink(decrease)].<br/>"
							+ "[npc.She] now has [style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " urethra)]!"
						+ "</p>");
			}
		}
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
		if (!owner.hasPenis()) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(You lack a penis, so nothing happens...)]</p>";
				
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] lacks a penis, so nothing happens...)]</p>");
			}
		}
		
		float oldCapacity = this.capacity;
		this.capacity = Math.max(0, Math.min(capacity, Capacity.SEVEN_GAPING.getMaximumValue(false)));
		if(setStretchedValueToNewValue) {
			this.stretchedCapacity = this.capacity;
		}
		float capacityChange = this.capacity - oldCapacity;
		
		if (capacityChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(Your urethra's capacity doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The capacity of [npc.namePos] urethra doesn't change...)]</p>");
			}
		}
		
		String capacityDescriptor = getCapacity().getDescriptor();
		if (capacityChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out a shocked gasp as you feel your urethra dilate and stretch out as its internal [style.boldGrow(capacity increases)].<br/>"
							+ "The transformation quickly passes, leaving you with [style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " urethra)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a shocked gasp as [npc.she] feels [npc.her] urethra dilate and stretch out as its internal [style.boldGrow(capacity increases)].<br/>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " urethra)]!"
						+ "</p>");
			}
			
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out a cry as you feel your urethra uncontrollably tighten and clench as its internal [style.boldShrink(capacity decreases)].<br/>"
							+ "The transformation quickly passes, leaving you with [style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " urethra)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a cry as [npc.she] feels [npc.her] urethra uncontrollably tighten and clench as its internal [style.boldShrink(capacity decreases)].<br/>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " urethra)]!"
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
	public int getMaximumPenetrationDepthComfortable(GameCharacter owner) {
		return (int) (owner.getPenisRawSizeValue() * 1.5f * (this.hasOrificeModifier(OrificeModifier.EXTRA_DEEP)?2:1) * (owner.isPenisBestial()?1.5f:1) * (!owner.getBodyMaterial().isOrificesLimitedDepth()?4f:1));
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
		if(owner==null) {
			return "";
		}
		
		if (elasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The elasticity of [npc.namePos] urethra doesn't change...)]</p>");
		}
		
		String elasticityDescriptor = getElasticity().getDescriptor();
		if (elasticityChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out a little gasp as you feel a strange slackening sensation pulsating deep within your [pc.cock] as your urethra's [style.boldGrow(elasticity increases)].<br/>"
							+ "The transformation quickly passes, leaving you with [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " urethra)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a little gasp as [npc.she] feels a strange slackening sensation pulsating deep within [npc.her] [npc.cock] as [npc.her] urethra's [style.boldGrow(elasticity increases)].<br/>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " urethra)]!"
						+ "</p>");
			}
			
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out a little gasp as you feel a strange clenching sensation pulsating deep within your [pc.cock] as your urethra's [style.boldShrink(elasticity decreases)].<br/>"
							+ "The transformation quickly passes, leaving you with [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " urethra)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a little gasp as [npc.she] feels a strange clenching sensation pulsating deep within [npc.her] [npc.cock] as [npc.her] urethra's [style.boldShrink(elasticity decreases)].<br/>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " urethra)]!"
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
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(Your urethra's plasticity doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The plasticity of [npc.namePos] urethra doesn't change...)]</p>");
			}
		}
		
		String plasticityDescriptor = getPlasticity().getDescriptor();
		if (plasticityChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out a little gasp as you feel a strange moulding sensation pulsating deep within your [pc.cock] as your urethra's [style.boldGrow(plasticity increases)].<br/>"
							+ "The transformation quickly passes, leaving you with [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " urethra)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a little gasp as [npc.she] feels a strange moulding sensation pulsating deep within [npc.her] [npc.cock] as [npc.her] urethra's [style.boldGrow(plasticity increases)].<br/>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " urethra)]!"
						+ "</p>");
			}
			
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out a little gasp as you feel a strange softening sensation pulsating deep within your [pc.cock] as your urethra's [style.boldShrink(plasticity decreases)].<br/>"
							+ "The transformation quickly passes, leaving you with [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " urethra)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a little gasp as [npc.she] feels a strange softening sensation pulsating deep within [npc.her] [npc.cock] as [npc.her] urethra's [style.boldShrink(plasticity decreases)].<br/>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " urethra)]!"
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
		
		switch(modifier) {
		case MUSCLE_CONTROL:
			if(owner.isUrethraFuckable()) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] can't help but let out [npc.a_moan+] as an intense pressure swells up at the base of [npc.her] [npc.cock], but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
							+ " With an experimental clench, [npc.she] [npc.verb(discover)] that the interior of [npc.her] urethra is now lined with [style.boldGrow(muscles)],"
								+ " which [npc.she] can use to expertly grip and squeeze down on any penetrating object.<br/>"
							+ "[style.boldSex([npc.NamePos] penile urethra is now lined with an intricate series of muscles!)]"
						+ "</p>");
			}
			break;
		case RIBBED:
			if(owner.isUrethraFuckable()) {
				return UtilText.parse(owner,
						"<p>"
						+ "[npc.Name] can't help but let out [npc.a_moan+] as an intense pressure swells up at the base of [npc.her] [npc.cock], but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
						+ " Shifting around a little, [npc.she] [npc.verb(discover)] that the inside of [npc.her] urethra is now lined with [style.boldGrow(fleshy, highly-sensitive ribs)],"
							+ " which provide extreme pleasure when stimulated.<br/>"
						+ "[style.boldSex([npc.NamePos] penile urethra is now lined with fleshy, pleasure-inducing ribs!)]"
					+ "</p>");
			}
			break;
		case TENTACLED:
			if(owner.isUrethraFuckable()) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] can't help but let out [npc.a_moan+] as an intense pressure swells up at the base of [npc.her] [npc.cock], but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
							+ " With an experimental clench, [npc.she] [npc.verb(discover)] that the inside of [npc.her] urethra is now lined with [style.boldGrow(little wriggling tentacles)], over which [npc.she] has limited control.<br/>"
							+ "[style.boldSex(The inside of [npc.namePos] penile urethra is now filled with small tentacles, which wriggle and caress any intruding object with a mind of their own!)]"
						+ "</p>");
			}
			break;
		case PUFFY:
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] can't help but let out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a tingling sensation running up the length of [npc.her] [npc.cock],"
							+ " before the rim of [npc.her] urethra suddenly [style.boldGrow(puffs up)] into a doughnut-like ring.<br/>"
						+ "[style.boldSex(The rim of [npc.namePos] penile urethra is now swollen and puffy!)]"
					+ "</p>");
		case EXTRA_DEEP:
			if(owner.isUrethraFuckable()) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] can't help but let out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a throbbing sensation rising up from the base of [npc.her] [npc.cock] into [npc.her] lower abdomen."
							+ " Within moments, the feeling fades away, and [npc.name] [npc.verb(find)] [npc.herself] instinctively knowing that [npc.her] [style.boldGrow(urethra has significantly deepened)].<br/>"
							+ "[style.boldSex([npc.NamePos] penile urethra is now extra deep, allowing it to take double the normal length of penetrative objects!)]"
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
		
		switch(modifier) {
			case MUSCLE_CONTROL:
				if(owner.isUrethraFuckable()) {
					return UtilText.parse(owner,
							"<p>"
								+ "[npc.Name] can't help but let out a startled cry as an intense pressure swells up within [npc.her] [npc.cock], but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
								+ " With an experimental clench, [npc.she] [npc.verb(discover)] that the interior of [npc.her] urethra has [style.boldShrink(lost its extra muscles)].<br/>"
								+ "[style.boldSex([npc.NamePos] penile urethra is no longer lined with an intricate series of muscles!)]"
							+ "</p>");
				}
				break;
			case RIBBED:
				if(owner.isUrethraFuckable()) {
					return UtilText.parse(owner,
							"<p>"
							+ "[npc.Name] can't help but let out a startled cry as an intense pressure swells up within [npc.her] [npc.cock], but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
							+ " Shifting [npc.her] [npc.pussy] around a little, [npc.she] [npc.verb(discover)] that the [style.boldShrink(fleshy, highly-sensitive ribs)] that once lined [npc.her] urethra [style.boldShrink(have vanished)].<br/>"
							+ "[style.boldSex([npc.NamePos] penile urethra is no longer lined with fleshy, pleasure-inducing ribs!)]"
						+ "</p>");
				}
				break;
			case TENTACLED:
				if(owner.isUrethraFuckable()) {
					return UtilText.parse(owner,
							"<p>"
								+ "[npc.Name] can't help but let out a startled cry as an intense pressure swells up within [npc.her] [npc.cock], but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
								+ " With an experimental clench, [npc.she] [npc.verb(discover)] that the [style.boldShrink(wriggling tentacles)] within [npc.her] urethra [style.boldShrink(have all disappeared)].<br/>"
								+ "[style.boldSex(The inside of [npc.namePos] penile urethra is no longer filled with tentacles!)]"
							+ "</p>");
				}
				break;
			case PUFFY:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] can't help but let out a startled cry as [npc.she] feels a tingling sensation running up the length of [npc.her] [npc.cock],"
								+ " before the [style.boldShrink(puffy rim)] of [npc.her] urethra [style.boldShrink(deflates)] into a more normal-looking shape.<br/>"
							+ "[style.boldSex(The rim of [npc.namePos] penile urethra is no longer swollen and puffy!)]"
						+ "</p>");
			case EXTRA_DEEP:
				if(owner.isUrethraFuckable()) {
					return UtilText.parse(owner,
							"<p>"
								+ "[npc.Name] can't help but let out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a tightening sensation dropping down from [npc.her] lower abdomen into the base of [npc.her] [npc.cock]."
								+ " Within moments, the feeling fades away, and [npc.name] [npc.verb(find)] [npc.herself] instinctively knowing that [npc.her] [style.boldShrink(urethra has lost a significant amount of depth)].<br/>"
								+ "[style.boldSex([npc.NamePos] penile urethra is no longer extra deep, and can only take an average length of penetrative objects!)]"
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
