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
public class OrificeAnus implements OrificeInterface {
	
	protected int wetness;
	protected float capacity;
	protected float stretchedCapacity;
	protected int depth;
	protected int elasticity;
	protected int plasticity;
	protected boolean virgin;
	protected Set<OrificeModifier> orificeModifiers;

	public OrificeAnus(int wetness, float capacity, int depth, int elasticity, int plasticity, boolean virgin, Collection<OrificeModifier> orificeModifiers) {
		this.wetness = wetness;
		this.capacity = capacity;
		stretchedCapacity = capacity;
		this.depth = depth;
		this.elasticity = elasticity;
		this.plasticity = plasticity;
		this.virgin = virgin;
		
		this.orificeModifiers = new HashSet<>(orificeModifiers);
	}
	
	@Override
	public Wetness getWetness(GameCharacter owner) {
		if(owner!=null && owner.getBodyMaterial().isOrificesAlwaysMaximumWetness()) {
			return Wetness.SEVEN_DROOLING;
		}
		return Wetness.valueOf(wetness);
	}

	@Override
	public String setWetness(GameCharacter owner, int wetness) {
		if(owner!=null && owner.getBodyMaterial().isOrificesAlwaysMaximumWetness()) {
			return UtilText.parse(owner,
					"<p style='text-align:center;'>[style.colourSex(Due to being made out of "+owner.getBodyMaterial().getName()+", the wetness of [npc.namePos] "+Wetness.SEVEN_DROOLING.getDescriptor()+" [npc.asshole] can't be changed...)]</p>");
		}
		
		int oldWetness = this.wetness;
		this.wetness = Math.max(0, Math.min(wetness, Wetness.SEVEN_DROOLING.getValue()));
		if(owner==null) {
			return "";
		}
		
		int wetnessChange = this.wetness - oldWetness;
		
		if (wetnessChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The wetness of [npc.namePos] [npc.asshole] doesn't change...)]</p>");
		}

		
		String wetnessDescriptor = getWetness(owner).getDescriptor();
		if (wetnessChange > 0) {
			return UtilText.parse(owner,
						"<p>"
							+ "[npc.NamePos] [npc.eyes] widen as [npc.she] [npc.verb(feel)] moisture beading around [npc.her] asshole,"
								+ " and [npc.she] can't help but let out a deep [npc.moan] as [npc.she] [npc.verb(realise)] that [npc.her] rear entrance is lubricating itself and [style.boldGrow(getting wetter)].<br/>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " asshole)]!"
						+ "</p>");
			
		} else {
			return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] [npc.verb(shift)] about uncomfortably and [npc.verb(let)] out a frustrated groan as [npc.she] [npc.verb(feel)] [npc.her] rear entrance [style.boldShrink(drying up)].<br/>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " asshole)]!"
						+ "</p>");
		}
	}

	@Override
	public Capacity getCapacity() {
		return Capacity.getCapacityFromValue(capacity);
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
		
		if (capacityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The capacity of [npc.namePos] [npc.asshole] doesn't change...)]</p>");
		}
		
		String capacityDescriptor = getCapacity().getDescriptor();
		if (capacityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "An involuntary, shocked gasp escapes from [npc.namePos] mouth as [npc.she] [npc.verb(feel)] [npc.her] asshole dilate and stretch."
						+ " Within moments, the feeling has passed, and [npc.she] very quickly [npc.verb(realise)] that [npc.her] asshole's internal [style.boldGrow(capacity has increased)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " asshole)]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a cry as [npc.she] [npc.verb(feel)] [npc.her] asshole uncontrollably tighten and clench."
						+ " Within moments, the feeling has passed, and [npc.she] very quickly [npc.verb(realise)] that [npc.her] asshole's internal [style.boldShrink(capacity has decreased)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " asshole)]!"
					+ "</p>");
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
		return (int) (owner.getHeightValue() * 0.12f * depth.getDepthPercentage());
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
		if(owner!=null && !owner.getBodyMaterial().isOrificesLimitedDepth()) {
			return UtilText.parse(owner,
					"<p style='text-align:center;'>[style.colourSex(Due to being made out of "+owner.getBodyMaterial().getName()+", the depth of [npc.namePos] "+OrificeDepth.SEVEN_FATHOMLESS.getDescriptor()+" [npc.asshole] can't be changed...)]</p>");
		}
		
		int oldDepth = this.depth;
		this.depth = Math.max(0, Math.min(depth, OrificeDepth.SEVEN_FATHOMLESS.getValue()));
		if(owner==null) {
			return "";
		}
		
		int depthChange = this.depth - oldDepth;
		
		if(depthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The depth of [npc.namePos] [npc.asshole] doesn't change...)]</p>");
		}
		
		String depthDescriptor = getDepth(owner).getDescriptor();
		if(depthChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] can't help but let out a surprised gasp as [npc.she] [npc.verb(feel)] an alarming pressure pulsating up deep into [npc.her] ass."
						+ " Before [npc.her] gasp can turn into a distressed cry, the pressure suddenly fades away, leaving [npc.herHim] instinctively knowing that [npc.her] asshole [style.boldGrow(has deepened)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + " " + depthDescriptor + " asshole)]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] can't help but let out a surprised gasp as [npc.she] [npc.verb(feel)] an alarming tightening sensation moving its way down from the depths of [npc.her] ass."
						+ " Before [npc.her] gasp can turn into a distressed cry, the feeling suddenly fades away, leaving [npc.herHim] instinctively knowing that [npc.her] asshole [style.boldShrink(has become shallower)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + " " + depthDescriptor + " asshole)]!"
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
					"<p style='text-align:center;'>[style.colourSex(Due to being made out of "+owner.getBodyMaterial().getName()+", the [npc.namePos] "+OrificeElasticity.SEVEN_ELASTIC.getDescriptor()+" asshole can't be changed...)]</p>");
                        } else {
                                return "";
                        }
                }
		this.elasticity = Math.max(0, Math.min(elasticity, OrificeElasticity.SEVEN_ELASTIC.getValue()));
		if(owner==null) {
			return "";
		}
		
		int elasticityChange = this.elasticity - oldElasticity;
		
		if (elasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The elasticity of [npc.namePos] [npc.asshole] doesn't change...)]</p>");
		}
		
		String elasticityDescriptor = getElasticity().getDescriptor();
		if (elasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] can't help but let out a surprised gasp as [npc.she] [npc.verb(feel)] a strange slackening sensation pulsating deep within [npc.her] ass."
						+ " Just as quickly as it started, the feeling passes, and [npc.she] very quickly [npc.verb(realise)] that [npc.her] asshole's [style.boldGrow(elasticity has increased)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " asshole)]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] can't help but let out a surprised gasp as [npc.she] [npc.verb(feel)] a strange clenching sensation pulsating deep within [npc.her] ass."
						+ " Just as quickly as it started, the feeling passes, and [npc.she] very quickly [npc.verb(realise)] that [npc.her] asshole's [style.boldShrink(elasticity has decreased)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " asshole)]!"
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
		
		if (plasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The plasticity of [npc.namePos] [npc.asshole] doesn't change...)]</p>");
		}
		
		String plasticityDescriptor = getPlasticity().getDescriptor();
		if (plasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a shocked gasp as [npc.she] suddenly [npc.verb(feel)] a strange hardening sensation pulsating deep within [npc.her] ass."
						+ " Before [npc.she] [npc.has] any time to panic, the feeling quickly fades away, leaving [npc.herHim] instinctively knowing that [npc.her] asshole's [style.boldGrow(plasticity has increased)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " asshole)]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a shocked gasp as [npc.she] suddenly [npc.verb(feel)] a strange softening sensation pulsating deep within [npc.her] ass."
						+ " Before [npc.she] [npc.has] any time to panic, the feeling quickly fades away, leaving [npc.herHim] instinctively knowing that [npc.her] asshole's [style.boldShrink(plasticity has decreased)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " asshole)]!"
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
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] can't help but let out [npc.a_moan+] as an intense pressure swells up deep within [npc.her] ass, but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
							+ " With an experimental clench, [npc.she] [npc.verb(discover)] that the inner walls of [npc.her] asshole are now lined with [style.boldGrow(extra muscles)],"
								+ " which [npc.she] can use to expertly grip and squeeze down on any penetrating object.<br/>"
							+ "[style.boldSex([npc.NamePos] asshole is now lined with an intricate series of muscles!)]"
						+ "</p>");
				
			case RIBBED:
				return UtilText.parse(owner,
						"<p>"
						+ "[npc.Name] can't help but let out [npc.a_moan+] as an intense pressure swells up deep within [npc.her] ass, but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
						+ " Shifting around a little, [npc.she] [npc.verb(discover)] that the inside of [npc.her] asshole is now lined with [style.boldGrow(fleshy, highly-sensitive ribs)],"
							+ " which provide extreme pleasure when stimulated.<br/>"
						+ "[style.boldSex([npc.NamePos] asshole is now lined with fleshy, pleasure-inducing ribs!)]"
					+ "</p>");
				
			case TENTACLED:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] can't help but let out [npc.a_moan+] as an intense pressure swells up deep within [npc.her] ass, but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
							+ " With an experimental clench, [npc.she] [npc.verb(discover)] that the inside of [npc.her] asshole is now lined with [style.boldGrow(little wriggling tentacles)], over which [npc.sheHasFull] limited control.<br/>"
							+ "[style.boldSex(The inside of [npc.namePos] asshole is now filled with small tentacles, which wriggle and caress any intruding object with a mind of their own!)]"
						+ "</p>");
					
			case PUFFY:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] can't help but let out a cry as [npc.she] [npc.verb(feel)] a tingling sensation running over [npc.her] [npc.ass],"
								+ " before the rim of [npc.her] [npc.asshole] [style.boldGrow(puffs up)] into a doughnut-like ring.<br/>"
							+ "[style.boldSex(The rim of [npc.namePos] asshole is now swollen and puffy!)]"
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
		
		switch(modifier) {
			case MUSCLE_CONTROL:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] can't help but let out a startled cry as an intense pressure swells up deep within [npc.her] ass, but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
							+ " With an experimental clench, [npc.she] [npc.verb(discover)] that the interior of [npc.her] asshole has [style.boldShrink(lost its extra muscles)].<br/>"
							+ "[style.boldSex([npc.NamePos] asshole is no longer lined with an intricate series of muscles!)]"
						+ "</p>");
					
			case RIBBED:
				return UtilText.parse(owner,
						"<p>"
						+ "[npc.Name] can't help but let out a startled cry as an intense pressure swells up deep within [npc.her] ass, but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
						+ " Shifting around a little, [npc.she] [npc.verb(discover)] that the [style.boldShrink(fleshy, highly-sensitive ribs)] that once lined the walls of [npc.her] asshole [style.boldShrink(have vanished)].<br/>"
						+ "[style.boldSex([npc.NamePos] asshole is no longer lined with fleshy, pleasure-inducing ribs!)]"
					+ "</p>");
					
			case TENTACLED:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] can't help but let out a startled cry as an intense pressure swells up deep within [npc.her] ass, but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
							+ " With an experimental clench, [npc.she] [npc.verb(discover)] that the [style.boldShrink(wriggling tentacles)] within [npc.her] asshole [style.boldShrink(have all disappeared)].<br/>"
							+ "[style.boldSex(The inside of [npc.namePos] asshole is no longer filled with tentacles!)]"
						+ "</p>");
					
			case PUFFY:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] can't help but let out a cry as [npc.she] [npc.verb(feel)] a tingling sensation running over [npc.her] [npc.ass],"
								+ " before the puffy rim of [npc.her] [npc.asshole] [style.boldShrink(deflates)] into a more normal-looking shape.<br/>"
							+ "[style.boldSex(The rim of [npc.namePos] asshole is no longer swollen and puffy!)]"
						+ "</p>");
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}

	public Set<OrificeModifier> getOrificeModifiers() {
		return orificeModifiers;
	}

	public void clearOrificeModifiers() {
		orificeModifiers.clear();
	}

}
