package com.lilithsthrone.game.character.body;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemEffect;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public class FluidMilk implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	protected FluidType type;
	protected FluidFlavour flavour;
	protected List<FluidModifier> fluidModifiers;
	protected List<ItemEffect> transformativeEffects;

	public FluidMilk(FluidType type) {
		this.type = type;
		this.flavour = type.getFlavour();
		transformativeEffects = new ArrayList<>();
		
		fluidModifiers = new ArrayList<>();
		fluidModifiers.addAll(type.getFluidModifiers());
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
		String modifierDescriptor = "";
		if(!fluidModifiers.isEmpty()) {
			modifierDescriptor = fluidModifiers.get(Util.random.nextInt(fluidModifiers.size())).getName();
		}
		
		return UtilText.returnStringAtRandom(
				modifierDescriptor,
				flavour.getRandomFlavourDescriptor(),
				type.getDescriptor(gc));
	}

	@Override
	public FluidType getType() {
		return type;
	}

	public void setType(BodyPartTypeInterface type) {
		this.type = (FluidType) type;
	}

	public FluidFlavour getFlavour() {
		return flavour;
	}

	public String setFlavour(GameCharacter owner, FluidFlavour flavour) {
		if(this.flavour == flavour) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}

		this.flavour = flavour;
		
		if(owner.isPlayer()) {
			return "<p>"
						+ "A soothing warmth spreads up through your [pc.breasts], causing you to let out a contented little sigh.</br>"
						+ "Your [pc.milk] now tastes of <b style='color:"+flavour.getColour().toWebHexString()+";'>"+flavour.getName()+"</b>."
					+ "</p>";
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "A soothing warmth spreads up through [npc.name]'s [npc.breasts], causing [npc.herHim] to let out a contented little sigh.</br>"
						+ "[npc.Name]'s [npc.milk] now tastes of <b style='color:"+flavour.getColour().toWebHexString()+";'>"+flavour.getName()+"</b>."
					+ "</p>");
		}
	}
	
	public boolean hasFluidModifier(FluidModifier fluidModifier) {
		return fluidModifiers.contains(fluidModifier);
	}
	
	public String addFluidModifier(GameCharacter owner, FluidModifier fluidModifier) {
		if(fluidModifiers.contains(fluidModifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		fluidModifiers.add(fluidModifier);
		
		switch(fluidModifier) {
			case ADDICTIVE:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a strange, pulsating heat spreading up through your [pc.breasts], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.milk] is now [style.boldGrow(addictive)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A strange, pulsating heat spreads up through [npc.name]'s [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.milk] is now [style.boldGrow(addictive)]!"
							+ "</p>");
				}
			case ALCOHOLIC:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a strange, soothing warmth spreading up through your [pc.breasts], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.milk] is now [style.boldGrow(alcoholic)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A strange, soothing warmth spreads up through [npc.name]'s [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.milk] is now [style.boldGrow(alcoholic)]!"
							+ "</p>");
				}
			case BUBBLING:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a light, bubbly feeling rising up into your [pc.breasts], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.milk] is now [style.boldGrow(bubbly)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A light, bubbly feeling spreads up through [npc.name]'s [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.milk] is now [style.boldGrow(bubbly)]!"
							+ "</p>");
				}
			case HALLUCINOGENIC:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a series of strange pulses shoot up into your [pc.breasts], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.milk] is now [style.boldGrow(psychoactive)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A series of strange pulses shoot up through [npc.name]'s [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.milk] is now [style.boldGrow(psychoactive)]!"
							+ "</p>");
				}
			case MUSKY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a slow, creeping warmth rise up into your [pc.breasts], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.milk] is now [style.boldGrow(musky)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A slow, creeping warmth rises up into [npc.name]'s [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.milk] is now [style.boldGrow(musky)]!"
							+ "</p>");
				}
			case SLIMY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a strange, soothing warmth flow up into your [pc.breasts], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.milk] is now [style.boldGrow(slimy)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A strange, soothing warmth flows up into [npc.name]'s [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.milk] is now [style.boldGrow(slimy)]!"
							+ "</p>");
				}
			case STICKY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a thick, sickly warmth flow up into your [pc.breasts], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.milk] is now [style.boldGrow(sticky)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A thick, sickly warmth flows up into [npc.name]'s [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.milk] is now [style.boldGrow(sticky)]!"
							+ "</p>");
				}
			case VISCOUS:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a heavy heat slowly rising up into your [pc.breasts], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.milk] is now [style.boldGrow(viscous)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A heavy heat slowly rises up into [npc.name]'s [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.milk] is now [style.boldGrow(viscous)]!"
							+ "</p>");
				}
		}
		
		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}
	
	public String removeFluidModifier(GameCharacter owner, FluidModifier fluidModifier) {
		if(!fluidModifiers.contains(fluidModifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		fluidModifiers.remove(fluidModifier);
		
		switch(fluidModifier) {
			case ADDICTIVE:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soft coolness spreading up through your [pc.breasts], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.milk] is [style.boldShrink(no longer addictive)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A soft coolness spreads up through [npc.name]'s [npc.breasts], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.milk] is [style.boldShrink(no longer addictive)]!"
							+ "</p>");
				}
			case ALCOHOLIC:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soft coolness spreading up through your [pc.breasts], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.milk] is [style.boldShrink(no longer alcoholic)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A soft coolness spreads up through [npc.name]'s [npc.breasts], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.milk] is [style.boldShrink(no longer alcoholic)]!"
							+ "</p>");
				}
			case BUBBLING:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a calm, settling feeling rising up into your [pc.breasts], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.milk] is [style.boldShrink(no longer bubbly)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A calm, settling feeling spreads up through [npc.name]'s [npc.breasts], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.milk] is [style.boldShrink(no longer bubbly)]!"
							+ "</p>");
				}
			case HALLUCINOGENIC:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a series of soothing waves wash up into your [pc.breasts], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.milk] is [style.boldShrink(no longer psychoactive)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A series of soothing waves wash up through [npc.name]'s [npc.breasts], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.milk] is [style.boldShrink(no longer psychoactive)]!"
							+ "</p>");
				}
			case MUSKY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a gentle coolness rise up into your [pc.breasts], causing you to let out soft sigh.</br>"
								+ "Your [pc.milk] is [style.boldShrink(no longer musky)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A gentle coolness rises up into [npc.name]'s [npc.breasts], causing [npc.herHim] to let out a soft sigh.</br>"
								+ "[npc.Name]'s [npc.milk] is [style.boldShrink(no longer musky)]!"
							+ "</p>");
				}
			case SLIMY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a calming coolness flow up into your [pc.breasts], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.milk] is [style.boldShrink(no longer slimy)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A calming coolness flows up into [npc.name]'s [npc.breasts], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.milk] is [style.boldShrink(no longer slimy)]!"
							+ "</p>");
				}
			case STICKY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soft warmth flow up into your [pc.breasts], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.milk] is [style.boldShrink(no longer sticky)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A soft warmth flows up into [npc.name]'s [npc.breasts], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.milk] is [style.boldShrink(no longer sticky)]!"
							+ "</p>");
				}
			case VISCOUS:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soft coolness slowly rising up into your [pc.breasts], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.milk] is [style.boldShrink(no longer viscous)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A soft coolness rises up into [npc.name]'s [npc.breasts], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.milk] is [style.boldShrink(no longer viscous)]!"
							+ "</p>");
				}
		}
		
		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}
	
	
	/*
	 * case TRANSFORMATIVE:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a powerful pulse of arcane energy shoot up into your [pc.breasts], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.milk] is now [style.boldGrow(transformative)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A powerful pulse of arcane energy shoots up into [npc.name]'s [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.milk] is now [style.boldGrow(transformative)]!"
							+ "</p>");
				}
				
			case TRANSFORMATIVE:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soothing wave of arcane energy disperse from your [pc.breasts], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.milk] is [style.boldShrink(no longer transformative)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A soothing wave of arcane energy disperses from [npc.name]'s [npc.breasts], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.milk] is [style.boldShrink(no longer transformative)]!"
							+ "</p>");
				}
	 */
	
	public List<ItemEffect> getTransformativeEffects() {
		return transformativeEffects;
	}
}
