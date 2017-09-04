package com.base.game.character.body;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.base.game.character.GameCharacter;
import com.base.game.character.attributes.Attribute;
import com.base.game.character.body.types.BodyPartTypeInterface;
import com.base.game.character.body.types.FluidType;
import com.base.game.character.body.valueEnums.FluidFlavour;
import com.base.game.character.body.valueEnums.FluidModifier;
import com.base.game.character.race.Race;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.item.ItemEffect;
import com.base.utils.Util;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public class FluidCum implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	private FluidType type;
	private FluidFlavour flavour;
	private List<FluidModifier> fluidModifiers;
	private List<ItemEffect> transformativeEffects;

	public FluidCum(FluidType type) {
		this.type = type;
		this.flavour = type.getFlavour();
		transformativeEffects = new ArrayList<>();
		
		fluidModifiers = new ArrayList<>();
		for(FluidModifier fm : type.getFluidModifiers()) {
			fluidModifiers.add(fm);
		}
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return type.getDeterminer(gc);
	}

	@Override
	public String getName(GameCharacter gc) {
		switch(type){
			case CUM_DOG_MORPH:
				if(gc.getRace()==Race.WOLF_MORPH) {
					return UtilText.returnStringAtRandom("wolf-cum", "wolf-cream", "wolf-jism", "wolf-jizz", "wolf-seed");
				} else {
					return UtilText.returnStringAtRandom("dog-cum", "dog-cream", "dog-jism", "dog-jizz", "dog-seed");
				}
			case CUM_HORSE_MORPH:
				return UtilText.returnStringAtRandom("horse-cum", "horse-cream", "horse-jism", "horse-jizz", "horse-seed");
			case CUM_CAT_MORPH:
				return UtilText.returnStringAtRandom("cat-cum", "cat-cream", "cat-jism", "cat-jizz", "cat-seed");
			default:
				return UtilText.returnStringAtRandom("cum", "cream", "jism", "jizz", "load", "seed", "spooge");
		}
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
				(gc.getAttributeValue(Attribute.VIRILITY)>=20?"potent":""),
				"hot",
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
		if(this.flavour == flavour || !owner.hasPenis()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}

		this.flavour = flavour;
		
		if(owner.isPlayer()) {
			return "<p>"
						+ "A soothing warmth spreads down into your [pc.balls], causing you to let out a contented little sigh.</br>"
						+ "Your [pc.cum] now tastes of <b style='color:"+flavour.getColour().toWebHexString()+";'>"+flavour.getName()+"</b>."
					+ "</p>";
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "A soothing warmth spreads down into [npc.name]'s [npc.balls], causing [npc.herHim] to let out a contented little sigh.</br>"
						+ "[npc.Name]'s [pc.cum] now tastes of <b style='color:"+flavour.getColour().toWebHexString()+";'>"+flavour.getName()+"</b>."
					+ "</p>");
		}
	}
	
	public boolean hasFluidModifier(FluidModifier fluidModifier) {
		return fluidModifiers.contains(fluidModifier);
	}
	
	public String addFluidModifier(GameCharacter owner, FluidModifier fluidModifier) {
		if(fluidModifiers.contains(fluidModifier) || !owner.hasPenis()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		fluidModifiers.add(fluidModifier);
		
		switch(fluidModifier) {
			case ADDICTIVE:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a strange, pulsating heat deep within your [pc.balls], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.cum] is now [style.boldGrow(addictive)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A strange, pulsating heat takes root deep within [npc.name]'s [npc.balls], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.cum] is now [style.boldGrow(addictive)]!"
							+ "</p>");
				}
			case ALCOHOLIC:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a strange, soothing warmth deep within your [pc.balls], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.cum] is now [style.boldGrow(alcoholic)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A strange, soothing warmth takes root deep within [npc.name]'s [npc.balls], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.cum] is now [style.boldGrow(alcoholic)]!"
							+ "</p>");
				}
			case BUBBLING:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a light, bubbly feeling rising up into your [pc.balls], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.cum] is now [style.boldGrow(bubbly)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A light, bubbly feeling rises up into [npc.name]'s [npc.balls], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.cum] is now [style.boldGrow(bubbly)]!"
							+ "</p>");
				}
			case HALLUCINOGENIC:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a series of strange pulses shoot up into your [pc.balls], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.cum] is now [style.boldGrow(hallucinogenic)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A series of strange pulses shoot up into [npc.name]'s [npc.balls], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.cum] is now [style.boldGrow(hallucinogenic)]!"
							+ "</p>");
				}
			case MUSKY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a slow, creeping warmth rise up into your [pc.balls], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.cum] is now [style.boldGrow(musky)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A slow, creeping warmth rises up into [npc.name]'s [npc.balls], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.cum] is now [style.boldGrow(musky)]!"
							+ "</p>");
				}
			case SLIMY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a strange, soothing warmth flow up into your [pc.balls], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.cum] is now [style.boldGrow(slimy)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A strange, soothing warmth flows up into [npc.name]'s [npc.balls], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.cum] is now [style.boldGrow(slimy)]!"
							+ "</p>");
				}
			case STICKY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a thick, sickly warmth flow up into your [pc.balls], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.cum] is now [style.boldGrow(sticky)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A thick, sickly warmth flows up into [npc.name]'s [npc.balls], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.cum] is now [style.boldGrow(sticky)]!"
							+ "</p>");
				}
			case VISCOUS:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a heavy heat slowly rising up into your [pc.balls], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.cum] is now [style.boldGrow(viscous)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A heavy heat slowly rises up into [npc.name]'s [npc.balls], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.cum] is now [style.boldGrow(viscous)]!"
							+ "</p>");
				}
		}
		
		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}
	
	public String removeFluidModifier(GameCharacter owner, FluidModifier fluidModifier) {
		if(!fluidModifiers.contains(fluidModifier) || !owner.hasPenis()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		fluidModifiers.remove(fluidModifier);
		
		switch(fluidModifier) {
			case ADDICTIVE:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soft coolness spreading up into your [pc.balls], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.cum] is [style.boldShrink(no longer addictive)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A soft coolness spreads up into [npc.name]'s [npc.balls], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.cum] is [style.boldShrink(no longer addictive)]!"
							+ "</p>");
				}
			case ALCOHOLIC:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soft coolness spreading up into your [pc.balls], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.cum] is [style.boldShrink(no longer alcoholic)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A soft coolness spreads up into [npc.name]'s [npc.balls], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.cum] is [style.boldShrink(no longer alcoholic)]!"
							+ "</p>");
				}
			case BUBBLING:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a calm, settling feeling rising up into your [pc.balls], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.cum] is [style.boldShrink(no longer bubbly)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A calm, settling feeling spreads up into [npc.name]'s [npc.balls], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.cum] is [style.boldShrink(no longer bubbly)]!"
							+ "</p>");
				}
			case HALLUCINOGENIC:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a series of soothing waves wash up into your [pc.balls], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.cum] is [style.boldShrink(no longer hallucinogenic)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A series of soothing waves wash up into [npc.name]'s [npc.balls], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.cum] is [style.boldShrink(no longer hallucinogenic)]!"
							+ "</p>");
				}
			case MUSKY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a gentle coolness rise up into your [pc.balls], causing you to let out soft sigh.</br>"
								+ "Your [pc.cum] is [style.boldShrink(no longer musky)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A gentle coolness rises up into [npc.name]'s [npc.balls], causing [npc.herHim] to let out a soft sigh.</br>"
								+ "[npc.Name]'s [npc.cum] is [style.boldShrink(no longer musky)]!"
							+ "</p>");
				}
			case SLIMY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a calming coolness flow up into your [pc.balls], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.cum] is [style.boldShrink(no longer slimy)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A calming coolness flows up into [npc.name]'s [npc.balls], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.cum] is [style.boldShrink(no longer slimy)]!"
							+ "</p>");
				}
			case STICKY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soft warmth flow up into your [pc.balls], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.cum] is [style.boldShrink(no longer sticky)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A soft warmth flows up into [npc.name]'s [npc.balls], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.cum] is [style.boldShrink(no longer sticky)]!"
							+ "</p>");
				}
			case VISCOUS:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soft coolness slowly rising up into your [pc.balls], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.cum] is [style.boldShrink(no longer viscous)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A soft coolness rises up into [npc.name]'s [npc.balls], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.cum] is [style.boldShrink(no longer viscous)]!"
							+ "</p>");
				}
		}
		
		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}
	
	
	/*
	 * case TRANSFORMATIVE:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a powerful pulse of arcane energy shoot up into your [pc.balls], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.cum] is now [style.boldGrow(transformative)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A powerful pulse of arcane energy shoots up into [npc.name]'s [npc.balls], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.cum] is now [style.boldGrow(transformative)]!"
							+ "</p>");
				}
				
			case TRANSFORMATIVE:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soothing wave of arcane energy disperse from your [pc.balls], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.cum] is [style.boldShrink(no longer transformative)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A soothing wave of arcane energy disperses from [npc.name]'s [npc.balls], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.cum] is [style.boldShrink(no longer transformative)]!"
							+ "</p>");
				}
	 */
	
	public List<ItemEffect> getTransformativeEffects() {
		return transformativeEffects;
	}
}
