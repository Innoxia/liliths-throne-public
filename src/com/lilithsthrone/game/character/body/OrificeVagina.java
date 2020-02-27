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
 * @version 0.2.4
 * @author Innoxia
 */
public class OrificeVagina implements OrificeInterface {
	
	protected int wetness;
	protected int elasticity;
	protected int plasticity;
	protected float capacity;
	protected float stretchedCapacity;
	protected boolean virgin;
	protected Set<OrificeModifier> orificeModifiers;
	protected boolean squirter;

	public OrificeVagina(int wetness, float capacity, int elasticity, int plasticity, boolean virgin, Collection<OrificeModifier> orificeModifiers) {
		this.wetness = wetness;
		this.capacity = capacity;
		stretchedCapacity = capacity;
		this.elasticity = elasticity;
		this.plasticity = plasticity;
		this.virgin = virgin;
		squirter = wetness > Wetness.THREE_WET.getValue();
		
		this.orificeModifiers = new HashSet<>(orificeModifiers);
	}
	
	@Override
	public Wetness getWetness(GameCharacter owner) {
		return Wetness.valueOf(wetness);
	}

	@Override
	public String setWetness(GameCharacter owner, int wetness) {
		int oldWetness = this.wetness;
		this.wetness = Math.max(0, Math.min(wetness, Wetness.SEVEN_DROOLING.getValue()));
		int wetnessChange = this.wetness - oldWetness;
		
		if(owner==null) {
			return null;
		}
		
		if (!owner.hasVagina()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] a vagina, so nothing happens...)]</p>");
		}
		
		if(this.wetness < Wetness.SEVEN_DROOLING.getValue() && owner.getBodyMaterial().isOrificesAlwaysMaximumWetness()) {
			this.wetness = Wetness.SEVEN_DROOLING.getValue();
			return UtilText.parse(owner,
					"<p style='text-align:center;'>[style.colourDisabled(Due to being made out of "+owner.getBodyMaterial().getName()+", [npc.namePos] [npc.pussy] can't be anything but "+Wetness.SEVEN_DROOLING.getDescriptor()+"...)]</p>");
		}
		
		if(wetnessChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The wetness of [npc.namePos] [npc.pussy] doesn't change...)]</p>");
		}
		
		String wetnessDescriptor = getWetness(owner).getDescriptor();
		if (wetnessChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.NamePos] [npc.eyes] widen as [npc.she] [npc.verb(feel)] moisture beading around [npc.her] [npc.pussy],"
							+ " and [npc.she] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(realise)] that it's lubricating itself and [style.boldGrow(getting wetter)].<br/>"
						+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " pussy)]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] [npc.verb(shift)] about uncomfortably and [npc.verb(let)] out a frustrated groan as [npc.she] [npc.verb(feel)] [npc.her] [npc.pussy] [style.boldShrink(getting drier)].<br/>"
						+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " pussy)]!"
					+ "</p>");
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
		
		if (owner!=null && !owner.hasVagina()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] a vagina, so nothing happens...)]</p>");
		}
		
		float oldCapacity = this.capacity;
		this.capacity = Math.max(0, Math.min(capacity, Capacity.SEVEN_GAPING.getMaximumValue(false)));
		if(setStretchedValueToNewValue) {
			this.stretchedCapacity = this.capacity;
		}
		float capacityChange = this.capacity - oldCapacity;
		
		if(owner==null) {
			return "";
		}
		
		if (capacityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The capacity of [npc.namePos] [npc.pussy] doesn't change...)]</p>");
		}
		
		String capacityDescriptor = getCapacity().getDescriptor();
		if (capacityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a shocked gasp as [npc.she] [npc.verb(feel)] [npc.her] [npc.pussy] dilate and stretch out as its internal [style.boldGrow(capacity increases)].<br/>"
						+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " pussy)]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a cry as [npc.she] [npc.verb(feel)] [npc.her] [npc.pussy] uncontrollably tighten and clench as its internal [style.boldShrink(capacity decreases)].<br/>"
						+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " pussy)]!"
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
	public int getMaximumPenetrationDepthComfortable(GameCharacter owner) {
		return (int) (owner.getHeightValue() * 0.08f * (this.hasOrificeModifier(OrificeModifier.EXTRA_DEEP)?2:1) * (owner.isVaginaBestial()?1.5f:1) * (!owner.getBodyMaterial().isOrificesLimitedDepth()?4f:1));
	}
	
	@Override
	public int getMaximumPenetrationDepthUncomfortable(GameCharacter owner) {
		return (int) (getMaximumPenetrationDepthComfortable(owner) * 1.5f);
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The elasticity of [npc.namePos] [npc.pussy] doesn't change...)]</p>");
		}
		
		String elasticityDescriptor = getElasticity().getDescriptor();
		if (elasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a little gasp as [npc.she] [npc.verb(feel)] a strange slackening sensation pulsating deep within [npc.her] [npc.pussy] as its [style.boldGrow(elasticity increases)].<br/>"
						+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " pussy)]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a little gasp as [npc.she] [npc.verb(feel)] a strange clenching sensation pulsating deep within [npc.her] [npc.pussy] as its [style.boldShrink(elasticity decreases)].<br/>"
						+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " pussy)]!"
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
		int plasticityChange = this.plasticity - oldPlasticity;
		if(owner==null) {
			return "";
		}
		
		if (plasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The plasticity of [npc.namePos] [npc.pussy] doesn't change...)]</p>");
		}
		
		String plasticityDescriptor = getPlasticity().getDescriptor();
		if (plasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a little gasp as [npc.she] [npc.verb(feel)] a strange moulding sensation pulsating deep within [npc.her] [npc.pussy] as its [style.boldGrow(plasticity increases)].<br/>"
						+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " pussy)]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a little gasp as [npc.she] [npc.verb(feel)] a strange softening sensation pulsating deep within [npc.her] [pc.pussy] as its [style.boldShrink(plasticity decreases)].<br/>"
						+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " pussy)]!"
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
		
		if(owner==null || owner.getBody()==null) {
			return "";
		}
		if(!owner.hasVagina()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Nothing happens, as [npc.name] [npc.verb(lack)] a vagina...)]</p>");
		}
		
		switch(modifier) {
			case MUSCLE_CONTROL:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] can't help but let out [npc.a_moan+] as an intense pressure swells up deep within [npc.her] [npc.pussy], but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
							+ " With an experimental clench, [npc.she] [npc.verb(discover)] that the inner walls of [npc.her] pussy are now lined with [style.boldGrow(extra muscles)],"
								+ " which [npc.she] can use to expertly grip and squeeze down on any penetrating object.<br/>"
							+ "[style.boldSex([npc.NamePos] pussy is now lined with an intricate series of muscles!)]"
						+ "</p>");
				
			case RIBBED:
				return UtilText.parse(owner,
						"<p>"
						+ "[npc.Name] can't help but let out [npc.a_moan+] as an intense pressure swells up deep within [npc.her] [npc.pussy], but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
						+ " Shifting around a little, [npc.she] [npc.verb(discover)] that the inside of [npc.her] pussy is now lined with [style.boldGrow(fleshy, highly-sensitive ribs)],"
							+ " which provide extreme pleasure when stimulated.<br/>"
						+ "[style.boldSex([npc.NamePos] pussy is now lined with fleshy, pleasure-inducing ribs!)]"
					+ "</p>");
				
			case TENTACLED:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] can't help but let out [npc.a_moan+] as an intense pressure swells up deep within [npc.her] [npc.pussy], but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
							+ " With an experimental clench, [npc.she] [npc.verb(discover)] that the inside of [npc.her] pussy is now lined with [style.boldGrow(little wriggling tentacles)], over which [npc.she] has limited control.<br/>"
							+ "[style.boldSex(The inside of [npc.namePos] pussy is now filled with small tentacles, which wriggle and caress any intruding object with a mind of their own!)]"
						+ "</p>");
					
			case PUFFY:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] can't help but let out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a tingling sensation running over [npc.her] [npc.pussy], before [npc.her] labia [style.boldGrow(puff up)] into big, swollen pussy lips.<br/>"
							+ "[style.boldSex([npc.NamePos] labia are now extremely swollen and puffy!)]"
						+ "</p>");
				
			case EXTRA_DEEP:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] can't help but let out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a throbbing sensation rising up from [npc.her] [npc.pussy] into [npc.her] lower abdomen."
							+ " Within moments, the feeling fades away, and [npc.name] [npc.verb(find)] [npc.herself] instinctively knowing that [npc.her] [style.boldGrow(pussy has significantly deepened)].<br/>"
							+ "[style.boldSex([npc.NamePos] pussy is now extra deep, allowing it to take double the normal length of penetrative objects!)]"
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
							+ "[npc.Name] can't help but let out a startled cry as an intense pressure swells up deep within [npc.her] [npc.pussy], but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
							+ " With an experimental clench, [npc.she] [npc.verb(discover)] that the interior of [npc.her] [npc.pussy] has [style.boldShrink(lost its extra muscles)].<br/>"
							+ "[style.boldSex([npc.NamePos] pussy is no longer lined with an intricate series of muscles!)]"
						+ "</p>");
					
			case RIBBED:
				return UtilText.parse(owner,
						"<p>"
						+ "[npc.Name] can't help but let out a startled cry as an intense pressure swells up deep within [npc.her] [npc.pussy], but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
						+ " Shifting around a little, [npc.she] [npc.verb(discover)] that the [style.boldShrink(fleshy, highly-sensitive ribs)] that once lined the walls of [npc.her] pussy [style.boldShrink(have vanished)].<br/>"
						+ "[style.boldSex([npc.NamePos] pussy is no longer lined with fleshy, pleasure-inducing ribs!)]"
					+ "</p>");
					
			case TENTACLED:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] can't help but let out a startled cry as an intense pressure swells up deep within [npc.her] [npc.pussy], but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
							+ " With an experimental clench, [npc.she] [npc.verb(discover)] that the [style.boldShrink(wriggling tentacles)] within [npc.her] pussy [style.boldShrink(have all disappeared)].<br/>"
							+ "[style.boldSex(The inside of [npc.namePos] pussy is no longer filled with tentacles!)]"
						+ "</p>");
					
			case PUFFY:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] can't help but let out a startled cry as [npc.she] feels a tingling sensation running over [npc.her] [npc.pussy],"
								+ " before [npc.her] [style.boldShrink(extra-puffy labia shrink down)] to take on a more average shape.<br/>"
							+ "[style.boldSex([npc.NamePos] labia are no longer extra puffy!)]"
						+ "</p>");
				
			case EXTRA_DEEP:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] can't help but let out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a tightening sensation dropping down from [npc.her] lower abdomen into [npc.her] [npc.pussy]."
							+ " Within moments, the feeling fades away, and [npc.name] [npc.verb(find)] [npc.herself] instinctively knowing that [npc.her] [style.boldShrink(pussy has lost a significant amount of depth)].<br/>"
							+ "[style.boldSex([npc.NamePos] pussy is no longer extra deep, and can only take an average length of penetrative objects!)]"
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

	public boolean isSquirter() {
		return squirter;
	}

	public String setSquirter(GameCharacter owner, boolean squirter) {
		if(owner == null) {
			this.squirter = squirter;
			return "";
		}
		if(this.squirter == squirter || !owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		this.squirter = squirter;
		
		if(squirter) {
			if(owner.isPlayer()) {
				return "<p>You are now a [style.boldGrow(squirter)]!</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.Name] is now a [style.boldGrow(squirter)]!</p>");
			}
		} else {
			if(owner.isPlayer()) {
				return "<p>You are no longer a [style.boldShrink(squirter)]!</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.Name] is no longer a [style.boldShrink(squirter)]!</p>");
			}
		}
	}

}
