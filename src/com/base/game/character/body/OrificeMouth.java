package com.base.game.character.body;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.base.game.character.GameCharacter;
import com.base.game.character.body.types.OrificeInterface;
import com.base.game.character.body.valueEnums.Capacity;
import com.base.game.character.body.valueEnums.OrificeElasticity;
import com.base.game.character.body.valueEnums.OrificeModifier;
import com.base.game.character.body.valueEnums.OrificePlasticity;
import com.base.game.character.body.valueEnums.Wetness;
import com.base.game.dialogue.utils.UtilText;

public class OrificeMouth implements OrificeInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	private int wetness, elasticity, plasticity;
	private float capacity, stretchedCapacity;
	private boolean virgin;
	private Set<OrificeModifier> orificeModifiers;

	public OrificeMouth(int wetness, int capacity, int elasticity, int plasticity, boolean virgin, Collection<OrificeModifier> orificeModifiers) {
		this.wetness = wetness;
		this.capacity = capacity;
		stretchedCapacity = capacity;
		this.elasticity = elasticity;
		this.plasticity = plasticity;
		this.virgin = virgin;
		
		this.orificeModifiers = new HashSet<>();
		for(OrificeModifier om : orificeModifiers) {
			this.orificeModifiers.add(om);
		}
	}
	
	@Override
	public Wetness getWetness(GameCharacter owner) {
		return Wetness.valueOf(wetness);
	}

	@Override
	public String setWetness(GameCharacter owner, int wetness) {
		int wetnessChange = 0;
		
		if (wetness <= 0) {
			if (this.wetness != 0) {
				wetnessChange = 0 - this.wetness;
				this.wetness = 0;
			}
		} else if (wetness >= Wetness.SEVEN_DROOLING.getValue()) {
			if (this.wetness != Wetness.SEVEN_DROOLING.getValue()) {
				wetnessChange = Wetness.SEVEN_DROOLING.getValue() - this.wetness;
				this.wetness = Wetness.SEVEN_DROOLING.getValue();
			}
		} else {
			if (this.wetness != wetness) {
				wetnessChange = wetness - this.wetness;
				this.wetness = wetness;
			}
		}
		
		if(wetnessChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(Your throat's wetness doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The wetness of [npc.name]'s throat doesn't change...)]</p>");
			}
		}
		
		String wetnessDescriptor = getWetness(owner).getDescriptor();
		if (wetnessChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "Your throat suddenly fills with saliva, and you gulp as you realise that it's permanently [style.boldGrow(got wetter)].</br>"
							+ "You now have [style.boldSex(" + UtilText.generateSingluarDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " throat)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name]'s throat suddenly fills with saliva, and [npc.she] gulps as [npc.she] realises that it's permanently [style.boldGrow(got wetter)].</br>"
							+ "[npc.Name] now has [style.boldSex(" + UtilText.generateSingluarDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " throat)]!"
						+ "</p>");
			}
			
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You shift about uncomfortably as you feel your throat [style.boldShrink(getting drier)].</br>"
							+ "You now have [style.boldSex(" + UtilText.generateSingluarDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " throat)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] shifts about uncomfortably as [npc.she] feels [npc.her] throat [style.boldShrink(getting drier)].</br>"
							+ "[npc.Name] now has [style.boldSex(" + UtilText.generateSingluarDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " throat)]!"
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
	public String setCapacity(GameCharacter owner, float capacity) {
		float capacityChange = 0;
		
		if (capacity <= 0) {
			if (this.capacity != 0) {
				capacityChange = 0 - this.capacity;
				this.capacity = 0;
			}
		} else if (capacity >= Capacity.SEVEN_GAPING.getMaximumValue()) {
			if (this.capacity != Capacity.SEVEN_GAPING.getMaximumValue()) {
				capacityChange = Capacity.SEVEN_GAPING.getMaximumValue() - this.capacity;
				this.capacity = Capacity.SEVEN_GAPING.getMaximumValue();
			}
		} else {
			if (this.capacity != capacity) {
				capacityChange = capacity - this.capacity;
				this.capacity = capacity;
			}
		}
		
		if(capacityChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(Your throat's capacity doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The capacity of [npc.name]'s throat doesn't change...)]</p>");
			}
		}
		
		String capacityDescriptor = getCapacity().getDescriptor();
		if (capacityChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You feel your throat's [style.boldGrow(capacity increasing)] as it relaxes and stretches out with a mind of its own.</br>"
							+ "The transformation quickly passes, leaving you with [style.boldSex(" + UtilText.generateSingluarDeterminer(capacityDescriptor) + " " + capacityDescriptor + " throat)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] feels [npc.her] throat's [style.boldGrow(capacity increasing)] as it relaxes and stretches out with a mind of its own.</br>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingluarDeterminer(capacityDescriptor) + " " + capacityDescriptor + " throat)]!"
						+ "</p>");
			}
			
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out a cry as you feel your throat close up and tighten as its internal [style.boldShrink(capacity decreases)].</br>"
							+ "The transformation quickly passes, leaving you with [style.boldSex(" + UtilText.generateSingluarDeterminer(capacityDescriptor) + " " + capacityDescriptor + " throat)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a cry as [npc.she] feels [npc.her] throat close up and tighten as its internal [style.boldShrink(capacity decreases)].</br>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingluarDeterminer(capacityDescriptor) + " " + capacityDescriptor + " throat)]!"
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
		if (stretchedCapacity <= 0) {
			if (this.stretchedCapacity != 0) {
				this.stretchedCapacity = 0;
				return true;
			}
		} else if (stretchedCapacity >= Capacity.SEVEN_GAPING.getMaximumValue()) {
			if (this.stretchedCapacity != Capacity.SEVEN_GAPING.getMaximumValue()) {
				this.stretchedCapacity = Capacity.SEVEN_GAPING.getMaximumValue();
				return true;
			}
		} else {
			if (this.stretchedCapacity != stretchedCapacity) {
				this.stretchedCapacity = stretchedCapacity;
				return true;
			}
		}
		return false;
	}

	@Override
	public OrificeElasticity getElasticity() {
		return OrificeElasticity.getElasticityFromInt(elasticity);
	}

	@Override
	public String setElasticity(GameCharacter owner, int elasticity) {
		float elasticityChange = 0;
		
		if (elasticity <= 0) {
			if (this.elasticity != 0) {
				elasticityChange = 0 - this.elasticity;
				this.elasticity = 0;
			}
		} else if (elasticity >= OrificeElasticity.SEVEN_ELASTIC.getValue()) {
			if (this.elasticity != OrificeElasticity.SEVEN_ELASTIC.getValue()) {
				elasticityChange = OrificeElasticity.SEVEN_ELASTIC.getValue() - this.elasticity;
				this.elasticity = OrificeElasticity.SEVEN_ELASTIC.getValue();
			}
		} else {
			if (this.elasticity != elasticity) {
				elasticityChange = elasticity - this.elasticity;
				this.elasticity = elasticity;
			}
		}
		
		if(elasticityChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(Your throat's elasticity doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The elasticity of [npc.name]'s throat doesn't change...)]</p>");
			}
		}
		
		String elasticityDescriptor = getElasticity().getDescriptor();
		if (elasticityChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out a little gasp as you feel a strange slackening sensation pulsating deep within your throat as its [style.boldGrow(elasticity increases)].</br>"
							+ "The transformation quickly passes, leaving you with [style.boldSex(" + UtilText.generateSingluarDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " throat)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a little gasp as [npc.she] feels a strange slackening sensation pulsating deep within [npc.her] throat as its [style.boldGrow(elasticity increases)].</br>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingluarDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " throat)]!"
						+ "</p>");
			}
			
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out a little gasp as you feel a strange clenching sensation pulsating deep within your throat as its [style.boldShrink(elasticity decreases)].</br>"
							+ "The transformation quickly passes, leaving you with [style.boldSex(" + UtilText.generateSingluarDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " throat)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a little gasp as [npc.she] feels a strange clenching sensation pulsating deep within [npc.her] throat as its [style.boldShrink(elasticity decreases)].</br>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingluarDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " throat)]!"
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
		float plasticityChange = 0;
		
		if (plasticity <= 0) {
			if (this.plasticity != 0) {
				plasticityChange = 0 - this.plasticity;
				this.plasticity = 0;
			}
		} else if (plasticity >= OrificePlasticity.SEVEN_MOULDABLE.getValue()) {
			if (this.plasticity != OrificePlasticity.SEVEN_MOULDABLE.getValue()) {
				plasticityChange = OrificePlasticity.SEVEN_MOULDABLE.getValue() - this.plasticity;
				this.plasticity = OrificePlasticity.SEVEN_MOULDABLE.getValue();
			}
		} else {
			if (this.plasticity != plasticity) {
				plasticityChange = plasticity - this.plasticity;
				this.plasticity = plasticity;
			}
		}
		
		if(plasticityChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(Your throat's plasticity doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The plasticity of [npc.name]'s throat doesn't change...)]</p>");
			}
		}
		
		String plasticityDescriptor = getPlasticity().getDescriptor();
		if (plasticityChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out a little gasp as you feel a strange moulding sensation pulsating deep within your throat as its [style.boldGrow(plasticity increases)].</br>"
							+ "The transformation quickly passes, leaving you with [style.boldSex(" + UtilText.generateSingluarDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " throat)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a little gasp as [npc.she] feels a strange moulding sensation pulsating deep within [npc.her] throat as its [style.boldGrow(plasticity increases)].</br>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingluarDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " throat)]!"
						+ "</p>");
			}
			
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out a little gasp as you feel a strange softening sensation pulsating deep within your throat as its [style.boldShrink(plasticity decreases)].</br>"
							+ "The transformation quickly passes, leaving you with [style.boldSex(" + UtilText.generateSingluarDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " throat)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a little gasp as [npc.she] feels a strange softening sensation pulsating deep within [npc.her] throat as its [style.boldShrink(plasticity decreases)].</br>"
							+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingluarDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " throat)]!"
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
								+ "You feel an intense pressure swelling up deep within your throat, but before you have any chance to react, the feeling suddenly fades away."
								+ " With an experimental clench, you discover that the interior of your throat is now lined with [style.boldGrow(extra muscles)], which you can use to expertly grip and squeeze down on any penetrating object.</br>"
								+ "[style.boldSex(Your throat is now lined with an intricate series of muscles!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "[npc.Name] lets out a little cry as an intense pressure swells up deep within [npc.her] throat, but before [npc.she] has any chance to react, the feeling quickly dissipates."
								+ " With an experimental clench, [npc.she] discovers that the interior of [npc.her] throat is now lined with [style.boldGrow(extra muscles)],"
									+ " which [npc.she] can use to expertly grip and squeeze down on any penetrating object.</br>"
								+ "[style.boldSex([npc.Name]'s throat is now lined with an intricate series of muscles!)]"
							+ "</p>";
				}
			case RIBBED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense pressure swelling up deep within your throat, but before you have any chance to react, the feeling suddenly fades away."
								+ " Shifting your throat around a little, you feel that the inside of your throat is now lined with [style.boldGrow(fleshy, highly-sensitive ribs)], which provide extreme pleasure when stimulated.</br>"
								+ "[style.boldSex(Your throat is now lined with fleshy, pleasure-inducing ribs!)]"
							+ "</p>";
				} else {
					return "<p>"
							+ "[npc.Name] lets out a little cry as an intense pressure swells up deep within [npc.her] throat, but before [npc.she] has any chance to react, the feeling quickly dissipates."
							+ " Shifting [npc.her] throat around a little, [npc.she] discovers that the inside of [npc.her] throat is now lined with [style.boldGrow(fleshy, highly-sensitive ribs)],"
								+ " which provide extreme pleasure when stimulated.</br>"
							+ "[style.boldSex([npc.Name]'s throat is now lined with fleshy, pleasure-inducing ribs!)]"
						+ "</p>";
				}
			case TENTACLED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense pressure swelling up deep within your throat, but before you have any chance to react, the feeling suddenly fades away."
								+ " A surprised cry bursts out from your mouth as you feel that the inside of your throat is now filled with [style.boldGrow(a series of little wriggling tentacles)], over which you have limited control.</br>"
								+ "[style.boldSex(The inside of your throat is now filled with little tentacles, which wriggle with a mind of their own!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "[npc.Name] lets out a little cry as an intense pressure swells up deep within [npc.her] throat, but before [npc.she] has any chance to react, the feeling quickly dissipates."
								+ " With an experimental clench, [npc.she] discovers that the inside of [npc.her] throat is now filled with [style.boldGrow(a series of little wriggling tentacles)], over which [npc.she] has limited control.</br>"
								+ "[style.boldSex(The inside of [npc.name]'s throat is now filled with little tentacles, which wriggle with a mind of their own!)]"
							+ "</p>";
				}
			case PUFFY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a tingling sensation running over your mouth, and you let out a little cry as you feel your lips swell out and [style.boldGrow(puff up)].</br>"
								+ "[style.boldSex(Your lips are now extremely puffy!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "[npc.Name] lets out a little cry as [npc.she] feels a tingling sensation running over your mouth, before [npc.her] lips swell out and [style.boldGrow(puff up)].</br>"
								+ "[style.boldSex([npc.Name]'s lips are now extremely puffy!)]"
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
								+ "You feel an intense pressure swelling up deep within your throat, but before you have any chance to react, the feeling suddenly fades away."
								+ " With an experimental clench, you discover that the interior of your throat has lost its [style.boldShrink(extra muscles)].</br>"
								+ "[style.boldSex(Your throat is no longer lined with an intricate series of muscles!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "[npc.Name] lets out a little cry as an intense pressure swells up deep within [npc.her] throat, but before [npc.she] has any chance to react, the feeling quickly dissipates."
								+ " With an experimental clench, [npc.she] discovers that the interior of [npc.her] throat has lost its [style.boldShrink(extra muscles)].</br>"
								+ "[style.boldSex([npc.Name]'s throat is no longer lined with an intricate series of muscles!)]"
							+ "</p>";
				}
			case RIBBED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense pressure swelling up deep within your throat, but before you have any chance to react, the feeling suddenly fades away."
								+ " Shifting your throat around a little, you feel that the [style.boldShrink(fleshy, highly-sensitive ribs)] that once lined your throat have vanished.</br>"
								+ "[style.boldSex(Your throat is no longer lined with fleshy, pleasure-inducing ribs!)]"
							+ "</p>";
				} else {
					return "<p>"
							+ "[npc.Name] lets out a little cry as an intense pressure swells up deep within [npc.her] throat, but before [npc.she] has any chance to react, the feeling quickly dissipates."
							+ " Shifting [npc.her] throat around a little, [npc.she] discovers that the [style.boldShrink(fleshy, highly-sensitive ribs)] that once lined [npc.her] throat have vanished.</br>"
							+ "[style.boldSex([npc.Name]'s throat is no longer lined with fleshy, pleasure-inducing ribs!)]"
						+ "</p>";
				}
			case TENTACLED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense pressure swelling up deep within your throat, but before you have any chance to react, the feeling suddenly fades away."
								+ " A surprised cry bursts out from your mouth as you feel that the [style.boldShrink(series of little wriggling tentacles)] within your throat have all disappeared.</br>"
								+ "[style.boldSex(The inside of your throat is no longer filled with little tentacles!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "[npc.Name] lets out a little cry as an intense pressure swells up deep within [npc.her] throat, but before [npc.she] has any chance to react, the feeling quickly dissipates."
								+ " With an experimental clench, [npc.she] discovers that the [style.boldShrink(series of little wriggling tentacles)] within [npc.her] throat have all disappeared.</br>"
								+ "[style.boldSex(The inside of [npc.name]'s throat is no longer filled with little tentacles!)]"
							+ "</p>";
				}
			case PUFFY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a tingling sensation running over your mouth, and you let out a little cry as you feel your puffy lips [style.boldGrow(deflate)] into a more normal-looking size.</br>"
								+ "[style.boldSex(Your lips are no longer extremely puffy!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "[npc.Name] lets out a little cry as [npc.she] feels a tingling sensation running over [npc.her] lips, before they suddenly [style.boldShrink(deflate)] into a more normal-looking size.</br>"
								+ "[style.boldSex([npc.Name]'s lips are no longer extremely puffy!)]"
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
