package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.OrificeInterface;
import com.lilithsthrone.game.character.body.valueEnums.*;
import com.lilithsthrone.game.dialogue.utils.UtilText;

import java.io.Serializable;
import java.util.Collection;

/**
 * @since 0.1.?
 * @version 0.2.1
 * @author Innoxia
 */
public class OrificeNipples extends AbstractOrifice implements OrificeInterface, Serializable {
	private static final long serialVersionUID = 1L;

	public OrificeNipples(int wetness, float capacity, int elasticity, int plasticity, boolean virgin, Collection<OrificeModifier> orificeModifiers) {
		super(wetness, capacity, elasticity, plasticity, virgin, orificeModifiers);
		ingestionRate = IngestionRate.TWO_AVERAGE.getValue();
	}

	
	@Override
	/**<b>DO NOT USE THIS. NIPPLE WETNESS IS DETERMINED BY BREAST LACTATION.</b>*/
	public String setWetness(GameCharacter owner, int wetness) {
		throw new IllegalAccessError(":BlobPeek: (Nipple wetness was attempted to be set manually!)");
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
	public boolean setStretchedCapacity(float stretchedCapacity) {
		float oldStretchedCapacity = this.stretchedCapacity;
		this.stretchedCapacity = Math.max(0, Math.min(stretchedCapacity, Capacity.SEVEN_GAPING.getMaximumValue()));
		return oldStretchedCapacity != this.stretchedCapacity;
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
	public String setPlasticity(GameCharacter owner, int plasticity) {
		int oldPlasticity = this.plasticity;
		this.plasticity = Math.max(0, Math.min(plasticity, OrificePlasticity.SEVEN_MOULDABLE.getValue()));
		int plasticityChange = this.plasticity - oldPlasticity;
		
		if (plasticityChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(Your [pc.nipples]'s plasticity doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The plasticity of [npc.namePos] [npc.nipples] doesn't change...)]</p>");
			}
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
	public String setIngestionRate(GameCharacter owner, int ingestionSpeed) {
		int oldIngestionSpeed = this.ingestionRate;
		this.ingestionRate = Math.max(0, Math.min(ingestionSpeed, IngestionRate.FIVE_MAXIMUM.getValue()));
		int ingestionRateChange = this.ingestionRate - oldIngestionSpeed;

		if(owner==null) {
			return "";
		}

		if (ingestionRateChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(Your ingestion rate of fluids taken through nipples doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos] ingestion rate of fluids taken through nipples doesn't change...)]</p>");
			}
		}

		String ingestionDescriptor = getIngestionRate().getDescriptor();
		if (ingestionRateChange > 0) {
			return UtilText.parse(owner,
					"<p>"
							+ "[npc.Name] [npc.verb(feel)] strange warmth in [npc.her] nipples. "
							+ "[npc.She] [npc.verb(believe)] they should be more absorbent now.<br/>"
							+ "Now [npc.her] igestion rate for fluids taken through nipples is [style.boldSex(" + ingestionDescriptor + ")]!"
							+ "</p>");

		} else {
			return UtilText.parse(owner,
					"<p>"
							+ "[npc.Name] [npc.verb(feel)] strange cold in [npc.her] nipples. "
							+ "[npc.She] [npc.verb(believe)] they should be less absorbent now.<br/>"
							+ "Now [npc.her] igestion rate for fluids taken through nipples is [style.boldSex(" + ingestionDescriptor + ")]!"
							+ "</p>");
		}
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
								+ "An intense pressure suddenly swells up deep within your [pc.breasts], and you can't help but let out [pc.a_moan+] as you feel a series of [style.boldGrow(extra muscles)] growing down into the lining of your [pc.nipples]."
								+ " You're shocked to discover that you have an incredible amount of control over them, allowing you to expertly grip and squeeze down on any penetrating object.<br/>"
								+ "[style.boldSex(The interior of your [pc.nipples] are now lined with an intricate series of muscles!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense pressure suddenly swells up deep within [npc.namePos] [npc.breasts], and [npc.she] can't help but let out [npc.a_moan+] as [npc.she] feels a series of [style.boldGrow(extra muscles)]"
									+ " growing down into the lining of [npc.her] [npc.nipples]."
								+ " [npc.sheIs] shocked to discover that [npc.she] has an incredible amount of control over them, allowing [npc.her] to expertly grip and squeeze down on any penetrating object.<br/>"
								+ "[style.boldSex(The interior of [npc.namePos] [npc.nipples] are now lined with an intricate series of muscles!)]"
							+ "</p>";
				}
			case RIBBED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "An intense pressure suddenly swells up deep within your [pc.breasts], and you can't help but let out [pc.a_moan+] as you feel a series of [style.boldGrow(fleshy, highly-sensitive ribs)]"
									+ " growing down into the lining of your [pc.nipples]."
								+ " Shifting your [pc.breasts] around a little, a jolt of pleasure shoots through your torso as you feel your new additions rub over one another, making you wonder how good it would feel to have your [pc.nipples] fucked.<br/>"
								+ "[style.boldSex(The interior of your [pc.nipples] are now lined with fleshy, pleasure-inducing ribs!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense pressure suddenly swells up deep within [npc.namePos] [npc.breasts], and [npc.she] can't help but let out [npc.a_moan+] as [npc.she] feels a series of [style.boldGrow(fleshy, highly-sensitive ribs)]"
									+ " growing down into the lining of [npc.her] [npc.nipples]."
								+ " Shifting [npc.her] [npc.breasts] around a little, a jolt of pleasure shoots through [npc.her] torso as [npc.she] feels [npc.her] new additions rub over one another, causing [npc.herHim] to let out another [npc.moan+].<br/>"
								+ "[style.boldSex(The interior of [npc.namePos] [npc.nipples] are now lined with fleshy, pleasure-inducing ribs!)]"
							+ "</p>";
				}
			case TENTACLED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "An intense pressure suddenly swells up deep within your [pc.breasts], and you can't help but let out [pc.a_moan+] as you feel a strange tingling sensation deep down in your [pc.nipples]."
								+ " The tingling sensation grows stronger, and a surprised cry bursts out from your mouth as you suddenly discover that the insides of your [pc.nipples] are now filled with"
									+ " [style.boldGrow(a series of little wriggling tentacles)], over which you have limited control.<br/>"
								+ "[style.boldSex(The insides of your [pc.nipples] are now filled with little tentacles, which wriggle with a mind of their own!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense pressure suddenly swells up deep within [npc.namePos] [npc.breasts], and [npc.she] can't help but let out [npc.a_moan+] as [npc.she] feels a strange tingling sensation deep down in [npc.her] [npc.nipples]."
								+ " The tingling sensation grows stronger, and a surprised cry bursts out from [npc.her] mouth as [npc.she] suddenly discovers that the insides of [npc.her] [npc.nipples] are now filled with"
										+ " [style.boldGrow(a series of little wriggling tentacles)], over which [npc.she] has limited control.<br/>"
								+ "[style.boldSex(The interior of [npc.namePos] [npc.nipples] are now filled with little tentacles, which wriggle with a mind of their own!)]"
							+ "</p>";
				}
			case PUFFY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a tingling sensation running over your [pc.nipples], and you let out a little cry as you feel them swell out and [style.boldGrow(puff up)].<br/>"
								+ "[style.boldTfSex(Your [pc.nipples] are now extremely puffy!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "[npc.Name] lets out a little cry as [npc.she] feels a tingling sensation running over [npc.her] [npc.nipples], before they suddenly swell out and [style.boldGrow(puff up)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.nipples] are now extremely puffy!)]"
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
								+ "A soothing warmth slowly washes up through your torso, and you can't help but let out a gentle [pc.moan] as you feel your [style.boldShrink(extra muscles)] melt back into the flesh of your [pc.breasts].<br/>"
								+ "[style.boldSex(The interior of your [pc.nipples] are no longer lined with an intricate series of muscles!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "A soothing warmth slowly washes up through [npc.namePos] torso, and a gentle [pc.moan] drifts out from between [npc.her] [npc.lips] as [npc.she] feels [npc.her] [style.boldShrink(extra muscles)]"
									+ " melt back into the flesh of [npc.her] [npc.breasts].<br/>"
								+ "[style.boldSex(The interior of [npc.namePos] [npc.nipples] are no longer lined with an intricate series of muscles!)]"
							+ "</p>";
				}
			case RIBBED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "A soothing warmth slowly washes up through your torso, and you can't help but let out a gentle [pc.moan] as you feel your [style.boldShrink(fleshy, highly-sensitive ribs)] melt back into the flesh of your [pc.breasts].<br/>"
								+ "[style.boldSex(The interior of your [pc.nipples] are no longer ribbed!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "A soothing warmth slowly washes up through [npc.namePos] torso, and a gentle [pc.moan] drifts out from between [npc.her] [npc.lips] as [npc.she] feels [npc.her] [style.boldShrink(fleshy, highly-sensitive ribs)]"
									+ " melt back into the flesh of [npc.her] [npc.breasts].<br/>"
								+ "[style.boldSex(The interior of [npc.namePos] [npc.nipples] are no longer ribbed!)]"
							+ "</p>";
				}
			case TENTACLED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "A soothing warmth slowly washes up through your torso, and you can't help but let out a gentle [pc.moan] as you feel your [style.boldShrink(little wriggling tentacles)] melt back into the flesh of your [pc.breasts].<br/>"
								+ "[style.boldSex(The interior of your [pc.nipples] are no longer filled with little tentacles!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "A soothing warmth slowly washes up through [npc.namePos] torso, and a gentle [pc.moan] drifts out from between [npc.her] [npc.lips] as [npc.she] feels [npc.her] [style.boldShrink(little wriggling tentacles)]"
									+ " melt back into the flesh of [npc.her] [npc.breasts].<br/>"
								+ "[style.boldSex(The interior of [npc.namePos] [npc.nipples] are no longer filled with little tentacles!)]"
							+ "</p>";
				}
			case PUFFY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a tingling sensation running over your [pc.nipples], and you let out a little sigh as you feel them [style.boldShrink(shrink down)], losing their puffiness.<br/>"
								+ "[style.boldTfSex(Your [pc.nipples] are no longer extremely puffy!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "[npc.Name] lets out a little sigh as [npc.her] [npc.nipples] [style.boldShrink(shrink down)] and lose their puffiness.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.nipples] are no longer extremely puffy!)]"
							+ "</p>";
				}
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}

	@Override
	public float getFluidLeakPerMinute() {
		return 4;
	}

}
