package com.lilithsthrone.game.character.body;

import java.io.Serializable;
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
public class OrificeAnus implements OrificeInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	protected int wetness;
	protected int elasticity;
	protected int plasticity;
	protected float capacity;
	protected float stretchedCapacity;
	protected boolean virgin;
	protected Set<OrificeModifier> orificeModifiers;

	public OrificeAnus(int wetness, float capacity, int elasticity, int plasticity, boolean virgin, Collection<OrificeModifier> orificeModifiers) {
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
		int oldWetness = this.wetness;
		this.wetness = Math.max(0, Math.min(wetness, Wetness.SEVEN_DROOLING.getValue()));
		int wetnessChange = this.wetness - oldWetness;
		
		if (wetnessChange == 0) {
			if(owner.isPlayer()) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Your [pc.asshole]'s wetness doesn't change...)]</p>");
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The wetness of [npc.name]'s [npc.asshole] doesn't change...)]</p>");
			}
		}

		if(this.wetness < Wetness.SEVEN_DROOLING.getValue() && owner.getBodyMaterial().isOrificesAlwaysMaximumWetness()) {
			this.wetness = Wetness.SEVEN_DROOLING.getValue();
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourSex(Due to being made out of "+owner.getBodyMaterial().getName()+", your [pc.asshole] can't be anything but "+Wetness.SEVEN_DROOLING.getDescriptor()+"...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourSex(Due to being made out of "+owner.getBodyMaterial().getName()+", [npc.name]'s [npc.asshole] can't be anything but "+Wetness.SEVEN_DROOLING.getDescriptor()+"...)]</p>");
			}
		}
		
		String wetnessDescriptor = getWetness(owner).getDescriptor();
		String transformation = "";
		if (wetnessChange > 0) {
			if (owner.isPlayer()) {
				transformation = "<p>"
							+ "Your [pc.eyes] widen as you feel moisture beading around your asshole, and you let out [pc.a_moan+] as you realise that your rear entrance is lubricating itself and [style.boldGrow(getting wetter)].</br>"
							+ "The transformation quickly passes, leaving you with [style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " asshole)]!"
						+ "</p>";
			} else {
				transformation = "<p>"
							+ "[npc.Name]'s [npc.eyes] widen as [npc.she] feels moisture beading around [npc.her] asshole,"
								+ " and [npc.she] lets out [npc.a_moan+] as [npc.she] realises that [npc.her] rear entrance is lubricating itself and [style.boldGrow(getting wetter)].</br>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " asshole)]!"
						+ "</p>";
			}
			
		} else {
			if (owner.isPlayer()) {
				transformation = "<p>"
							+ "You shift about uncomfortably and let out a frustrated groan as you feel your rear entrance [style.boldShrink(getting drier)].</br>"
							+ "The transformation quickly passes, leaving you with [style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " asshole)]!"
						+ "</p>";
			} else {
				transformation = "<p>"
							+ "[npc.Name] shifts about uncomfortably and lets out a frustrated groan as [npc.she] feels [npc.her] rear entrance [style.boldShrink(getting drier)].</br>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " asshole)]!"
						+ "</p>";
			}
		}
		return UtilText.parse(owner, transformation);
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
		this.capacity = Math.max(0, Math.min(capacity, Capacity.SEVEN_GAPING.getMaximumValue()));
		if(setStretchedValueToNewValue) {
			this.stretchedCapacity = this.capacity;
		}
		float capacityChange = this.capacity - oldCapacity;
		
		if (capacityChange == 0) {
			if(owner.isPlayer()) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Your [pc.asshole]'s capacity doesn't change...)]</p>");
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The capacity of [npc.name]'s [npc.asshole] doesn't change...)]</p>");
			}
		}
		
		String capacityDescriptor = getCapacity().getDescriptor();
		if (capacityChange > 0) {
			if (owner.isPlayer()) {
				return UtilText.parse(owner, "<p>"
							+ "You let out a shocked gasp as you feel your asshole dilate and stretch out as its internal [style.boldGrow(capacity increases)].</br>"
							+ "The transformation quickly passes, leaving you with [style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " asshole)]!"
						+ "</p>");
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a shocked gasp as [npc.she] feels [npc.her] asshole dilate and stretch out as its internal [style.boldGrow(capacity increases)].</br>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " asshole)]!"
						+ "</p>");
			}
			
		} else {
			if (owner.isPlayer()) {
				return UtilText.parse(owner, "<p>"
							+ "You let out a cry as you feel your asshole uncontrollably tighten and clench as its internal [style.boldShrink(capacity decreases)].</br>"
							+ "The transformation quickly passes, leaving you with [style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " asshole)]!"
						+ "</p>");
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a cry as [npc.she] feels [npc.her] asshole uncontrollably tighten and clench as its internal [style.boldShrink(capacity decreases)].</br>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " asshole)]!"
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
		this.stretchedCapacity = Math.max(0, Math.min(stretchedCapacity, Capacity.SEVEN_GAPING.getMaximumValue()));
		return oldStretchedCapacity != this.stretchedCapacity;
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
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Your [pc.asshole]'s elasticity doesn't change...)]</p>");
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The elasticity of [npc.name]'s [npc.asshole] doesn't change...)]</p>");
			}
		}
		
		String elasticityDescriptor = getElasticity().getDescriptor();
		if (elasticityChange > 0) {
			if (owner.isPlayer()) {
				return UtilText.parse(owner, "<p>"
							+ "You let out a little gasp as you feel a strange slackening sensation pulsating deep within your ass as your asshole's [style.boldGrow(elasticity increases)].</br>"
							+ "The transformation quickly passes, leaving you with [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " asshole)]!"
						+ "</p>");
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a little gasp as [npc.she] feels a strange slackening sensation pulsating deep within [npc.her] ass as [npc.her] asshole's [style.boldGrow(elasticity increases)].</br>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " asshole)]!"
						+ "</p>");
			}
			
		} else {
			if (owner.isPlayer()) {
				return UtilText.parse(owner, "<p>"
							+ "You let out a little gasp as you feel a strange clenching sensation pulsating deep within your ass as your asshole's [style.boldShrink(elasticity decreases)].</br>"
							+ "The transformation quickly passes, leaving you with [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " asshole)]!"
						+ "</p>");
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a little gasp as [npc.she] feels a strange clenching sensation pulsating deep within [npc.her] ass as [npc.her] asshole's [style.boldShrink(elasticity decreases)].</br>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " asshole)]!"
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
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Your [pc.asshole]'s plasticity doesn't change...)]</p>");
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The plasticity of [npc.name]'s [npc.asshole] doesn't change...)]</p>");
			}
		}
		
		String plasticityDescriptor = getPlasticity().getDescriptor();
		if (plasticityChange > 0) {
			if (owner.isPlayer()) {
				return UtilText.parse(owner, "<p>"
							+ "You let out a little gasp as you feel a strange moulding sensation pulsating deep within your ass as your asshole's [style.boldGrow(plasticity increases)].</br>"
							+ "The transformation quickly passes, leaving you with [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " asshole)]!"
						+ "</p>");
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a little gasp as [npc.she] feels a strange moulding sensation pulsating deep within [npc.her] ass as [npc.her] asshole's [style.boldGrow(plasticity increases)].</br>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " asshole)]!"
						+ "</p>");
			}
			
		} else {
			if (owner.isPlayer()) {
				return UtilText.parse(owner, "<p>"
							+ "You let out a little gasp as you feel a strange softening sensation pulsating deep within your ass as your asshole's [style.boldShrink(plasticity decreases)].</br>"
							+ "The transformation quickly passes, leaving you with [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " asshole)]!"
						+ "</p>");
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a little gasp as [npc.she] feels a strange softening sensation pulsating deep within [npc.her] ass as [npc.her] asshole's [style.boldShrink(plasticity decreases)].</br>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " asshole)]!"
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
		
		switch(modifier) {
			case MUSCLE_CONTROL:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense pressure swelling up deep within your [pc.ass], but before you have any chance to react, the feeling suddenly fades away."
								+ " With an experimental clench, you discover that the interior of your [pc.asshole] is now lined with [style.boldGrow(extra muscles)], which you can use to expertly grip and squeeze down on any penetrating object.</br>"
								+ "[style.boldSex(Your asshole is now lined with an intricate series of muscles!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "[npc.Name] lets out a little cry as an intense pressure swells up deep within [npc.her] [npc.ass], but before [npc.she] has any chance to react, the feeling quickly dissipates."
								+ " With an experimental clench, [npc.she] discovers that the interior of [npc.her] [npc.asshole] is now lined with [style.boldGrow(extra muscles)],"
									+ " which [npc.she] can use to expertly grip and squeeze down on any penetrating object.</br>"
								+ "[style.boldSex([npc.Name]'s asshole is now lined with an intricate series of muscles!)]"
							+ "</p>";
				}
			case RIBBED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense pressure swelling up deep within your [pc.ass], but before you have any chance to react, the feeling suddenly fades away."
								+ " Shifting your [pc.ass] around a little, you feel that the inside of your [pc.asshole] is now lined with [style.boldGrow(fleshy, highly-sensitive ribs)], which provide extreme pleasure when stimulated.</br>"
								+ "[style.boldSex(Your asshole is now lined with fleshy, pleasure-inducing ribs!)]"
							+ "</p>";
				} else {
					return "<p>"
							+ "[npc.Name] lets out a little cry as an intense pressure swells up deep within [npc.her] [npc.ass], but before [npc.she] has any chance to react, the feeling quickly dissipates."
							+ " Shifting [npc.her] [npc.ass] around a little, [npc.she] discovers that the inside of [npc.her] [npc.asshole] is now lined with [style.boldGrow(fleshy, highly-sensitive ribs)],"
								+ " which provide extreme pleasure when stimulated.</br>"
							+ "[style.boldSex([npc.Name]'s asshole is now lined with fleshy, pleasure-inducing ribs!)]"
						+ "</p>";
				}
			case TENTACLED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense pressure swelling up deep within your [pc.ass], but before you have any chance to react, the feeling suddenly fades away."
								+ " A surprised cry bursts out from your mouth as you feel that the inside of your [pc.asshole] is now filled with [style.boldGrow(a series of little wriggling tentacles)], over which you have limited control.</br>"
								+ "[style.boldSex(The inside of your asshole is now filled with little tentacles, which wriggle with a mind of their own!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "[npc.Name] lets out a little cry as an intense pressure swells up deep within [npc.her] [npc.ass], but before [npc.she] has any chance to react, the feeling quickly dissipates."
								+ " With an experimental clench, [npc.she] discovers that the inside of [npc.her] [npc.asshole] is now filled with [style.boldGrow(a series of little wriggling tentacles)], over which [npc.she] has limited control.</br>"
								+ "[style.boldSex(The inside of [npc.name]'s asshole is now filled with little tentacles, which wriggle with a mind of their own!)]"
							+ "</p>";
				}
			case PUFFY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a tingling sensation running over your [pc.ass], and you let out a little cry as you feel the rim of your [pc.asshole] [style.boldGrow(puff up)] into a doughnut-like ring.</br>"
								+ "[style.boldSex(The rim of your asshole is now swollen and puffy!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "[npc.Name] lets out a little cry as [npc.she] feels a tingling sensation running over [npc.her] [npc.ass], before the rim of [npc.her] [npc.asshole] [style.boldGrow(puffs up)] into a doughnut-like ring.</br>"
								+ "[style.boldSex(The rim of [npc.name]'s asshole is now swollen and puffy!)]"
							+ "</p>";
				}
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
		
		switch(modifier) {
			case MUSCLE_CONTROL:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense pressure swelling up deep within your [pc.ass], but before you have any chance to react, the feeling suddenly fades away."
								+ " With an experimental clench, you discover that the interior of your [pc.asshole] has lost its [style.boldShrink(extra muscles)].</br>"
								+ "[style.boldSex(Your asshole is no longer lined with an intricate series of muscles!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "[npc.Name] lets out a little cry as an intense pressure swells up deep within [npc.her] [npc.ass], but before [npc.she] has any chance to react, the feeling quickly dissipates."
								+ " With an experimental clench, [npc.she] discovers that the interior of [npc.her] [npc.asshole] has lost its [style.boldShrink(extra muscles)].</br>"
								+ "[style.boldSex([npc.Name]'s asshole is no longer lined with an intricate series of muscles!)]"
							+ "</p>";
				}
			case RIBBED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense pressure swelling up deep within your [pc.ass], but before you have any chance to react, the feeling suddenly fades away."
								+ " Shifting your [pc.ass] around a little, you feel that the [style.boldShrink(fleshy, highly-sensitive ribs)] that once lined your [pc.asshole] have vanished.</br>"
								+ "[style.boldSex(Your asshole is no longer lined with fleshy, pleasure-inducing ribs!)]"
							+ "</p>";
				} else {
					return "<p>"
							+ "[npc.Name] lets out a little cry as an intense pressure swells up deep within [npc.her] [npc.ass], but before [npc.she] has any chance to react, the feeling quickly dissipates."
							+ " Shifting [npc.her] [npc.ass] around a little, [npc.she] discovers that the [style.boldShrink(fleshy, highly-sensitive ribs)] that once lined [npc.her] [npc.asshole] have vanished.</br>"
							+ "[style.boldSex([npc.Name]'s asshole is no longer lined with fleshy, pleasure-inducing ribs!)]"
						+ "</p>";
				}
			case TENTACLED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense pressure swelling up deep within your [pc.ass], but before you have any chance to react, the feeling suddenly fades away."
								+ " A surprised cry bursts out from your mouth as you feel that the [style.boldShrink(series of little wriggling tentacles)] within your [pc.asshole] have all disappeared.</br>"
								+ "[style.boldSex(The inside of your asshole is no longer filled with little tentacles!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "[npc.Name] lets out a little cry as an intense pressure swells up deep within [npc.her] [npc.ass], but before [npc.she] has any chance to react, the feeling quickly dissipates."
								+ " With an experimental clench, [npc.she] discovers that the [style.boldShrink(series of little wriggling tentacles)] within [npc.her] [npc.asshole] have all disappeared.</br>"
								+ "[style.boldSex(The inside of [npc.name]'s asshole is no longer filled with little tentacles!)]"
							+ "</p>";
				}
			case PUFFY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a tingling sensation running over your [pc.ass], and you let out a little cry as you feel the puffy rim of your [pc.asshole] [style.boldGrow(deflate)] into a more normal-looking shape.</br>"
								+ "[style.boldSex(The rim of your asshole is no longer swollen and puffy!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "[npc.Name] lets out a little cry as [npc.she] feels a tingling sensation running over [npc.her] [npc.ass],"
									+ " before the puffy rim of [npc.her] [npc.asshole] [style.boldShrink(deflates)] into a more normal-looking shape.</br>"
								+ "[style.boldSex(The rim of [npc.name]'s asshole is no longer swollen and puffy!)]"
							+ "</p>";
				}
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}

	public Set<OrificeModifier> getOrificeModifiers() {
		return orificeModifiers;
	}

}
