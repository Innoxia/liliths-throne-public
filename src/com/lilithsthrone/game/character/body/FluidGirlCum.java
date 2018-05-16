package com.lilithsthrone.game.character.body;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.83
 * @version 0.2.5
 * @author Innoxia
 */
public class FluidGirlCum implements BodyPartInterface, Serializable, XMLSaving {
	private static final long serialVersionUID = 1L;
	
	protected FluidType type;
	protected FluidFlavour flavour;
	protected List<FluidModifier> fluidModifiers;
	protected List<ItemEffect> transformativeEffects;

	public FluidGirlCum(FluidType type) {
		this.type = type;
		this.flavour = type.getFlavour();
		transformativeEffects = new ArrayList<>();
		
		fluidModifiers = new ArrayList<>();
		fluidModifiers.addAll(type.getFluidModifiers());
	}

	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("girlcum");
		parentElement.appendChild(element);

		CharacterUtils.addAttribute(doc, element, "type", this.type.toString());
		CharacterUtils.addAttribute(doc, element, "flavour", this.flavour.toString());
		Element cumModifiers = doc.createElement("girlcumModifiers");
		element.appendChild(cumModifiers);
		for(FluidModifier fm : FluidModifier.values()) {
			CharacterUtils.addAttribute(doc, cumModifiers, fm.toString(), String.valueOf(this.hasFluidModifier(fm)));
		}
		
		return element;
	}
	
	public static FluidGirlCum loadFromXML(Element parentElement, Document doc) {
		
		Element girlcum = (Element)parentElement.getElementsByTagName("girlcum").item(0);

		FluidType fluidType = FluidType.GIRL_CUM_HUMAN;
		try {
			fluidType = FluidType.valueOf(girlcum.getAttribute("type"));
		} catch(Exception ex) {
		}
		
		FluidGirlCum fluidGirlcum = new FluidGirlCum(fluidType);
		
		fluidGirlcum.flavour = (FluidFlavour.valueOf(girlcum.getAttribute("flavour")));
		

		Element girlcumModifiersElement = (Element)girlcum.getElementsByTagName("girlcumModifiers").item(0);
		Collection<FluidModifier> girlcumFluidModifiers = fluidGirlcum.fluidModifiers;
		Body.handleLoadingOfModifiers(FluidModifier.values(), null, girlcumModifiersElement, girlcumFluidModifiers);
		
//		Element cumModifiers = (Element)cum.getElementsByTagName("girlcumModifiers").item(0);
//		fluidGirlcum.fluidModifiers.clear();
//		for(FluidModifier fm : FluidModifier.values()) {
//			if(Boolean.valueOf(cumModifiers.getAttribute(fm.toString()))) {
//				fluidGirlcum.fluidModifiers.add(fm);
//			}
//		}
		
		return fluidGirlcum;
	}

	@Override
	public boolean equals (Object o) {
		if(o instanceof FluidGirlCum){
			if(((FluidGirlCum)o).getType().equals(this.getType())
				&& ((FluidGirlCum)o).getFlavour() == this.getFlavour()
				&& ((FluidGirlCum)o).getFluidModifiers().equals(this.getFluidModifiers())
				&& ((FluidGirlCum)o).getTransformativeEffects().equals(this.getTransformativeEffects())){
					return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.getType().hashCode();
		result = 31 * result + this.getFlavour().hashCode();
		result = 31 * result + this.getFluidModifiers().hashCode();
		result = 31 * result + this.getTransformativeEffects().hashCode();
		return result;
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
		if(this.flavour == flavour || !owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}

		this.flavour = flavour;
		
		if(owner.isPlayer()) {
			return "<p>"
						+ "A soothing warmth spreads down into your [pc.pussy], causing you to let out a contented little sigh.</br>"
						+ "Your [pc.girlcum] now tastes of <b style='color:"+flavour.getColour().toWebHexString()+";'>"+flavour.getName()+"</b>."
					+ "</p>";
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "A soothing warmth spreads down into [npc.name]'s [npc.pussy], causing [npc.herHim] to let out a contented little sigh.</br>"
						+ "[npc.Name]'s [pc.girlcum] now tastes of <b style='color:"+flavour.getColour().toWebHexString()+";'>"+flavour.getName()+"</b>."
					+ "</p>");
		}
	}
	
	public boolean hasFluidModifier(FluidModifier fluidModifier) {
		return fluidModifiers.contains(fluidModifier);
	}
	
	public String addFluidModifier(GameCharacter owner, FluidModifier fluidModifier) {
		if(fluidModifiers.contains(fluidModifier) || !owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		fluidModifiers.add(fluidModifier);
		
		switch(fluidModifier) {
			case ADDICTIVE:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a strange, pulsating heat deep within your [pc.pussy], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.girlcum] is now [style.boldGrow(addictive)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A strange, pulsating heat takes root deep within [npc.name]'s [npc.pussy], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.girlcum] is now [style.boldGrow(addictive)]!"
							+ "</p>");
				}
			case ALCOHOLIC:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a strange, soothing warmth deep within your [pc.pussy], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.girlcum] is now [style.boldGrow(alcoholic)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A strange, soothing warmth takes root deep within [npc.name]'s [npc.pussy], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.girlcum] is now [style.boldGrow(alcoholic)]!"
							+ "</p>");
				}
			case BUBBLING:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a light, bubbly feeling rising up into your [pc.pussy], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.girlcum] is now [style.boldGrow(bubbly)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A light, bubbly feeling rises up into [npc.name]'s [npc.pussy], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.girlcum] is now [style.boldGrow(bubbly)]!"
							+ "</p>");
				}
			case HALLUCINOGENIC:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a series of strange pulses shoot up into your [pc.pussy], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.girlcum] is now [style.boldGrow(psychoactive)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A series of strange pulses shoot up into [npc.name]'s [npc.pussy], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.girlcum] is now [style.boldGrow(psychoactive)]!"
							+ "</p>");
				}
			case MUSKY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a slow, creeping warmth rise up into your [pc.pussy], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.girlcum] is now [style.boldGrow(musky)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A slow, creeping warmth rises up into [npc.name]'s [npc.pussy], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.girlcum] is now [style.boldGrow(musky)]!"
							+ "</p>");
				}
			case SLIMY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a strange, soothing warmth flow up into your [pc.pussy], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.girlcum] is now [style.boldGrow(slimy)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A strange, soothing warmth flows up into [npc.name]'s [npc.pussy], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.girlcum] is now [style.boldGrow(slimy)]!"
							+ "</p>");
				}
			case STICKY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a thick, sickly warmth flow up into your [pc.pussy], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.girlcum] is now [style.boldGrow(sticky)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A thick, sickly warmth flows up into [npc.name]'s [npc.pussy], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.girlcum] is now [style.boldGrow(sticky)]!"
							+ "</p>");
				}
			case VISCOUS:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a heavy heat slowly rising up into your [pc.pussy], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.girlcum] is now [style.boldGrow(viscous)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A heavy heat slowly rises up into [npc.name]'s [npc.pussy], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.girlcum] is now [style.boldGrow(viscous)]!"
							+ "</p>");
				}
			case SWEET:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a rich, pleasant warmth flowing up into your [pc.pussy], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.girlcum] is now [style.boldGrow(sweet)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A rich, pleasant warmth flows up into [npc.name]'s [npc.pussy], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.girlcum] is now [style.boldGrow(sweet)]!"
							+ "</p>");
				}
		}
		
		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}
	
	public String removeFluidModifier(GameCharacter owner, FluidModifier fluidModifier) {
		if(!fluidModifiers.contains(fluidModifier) || !owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		fluidModifiers.remove(fluidModifier);
		
		switch(fluidModifier) {
			case ADDICTIVE:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soft coolness spreading up into your [pc.pussy], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.girlcum] is [style.boldShrink(no longer addictive)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A soft coolness spreads up into [npc.name]'s [npc.pussy], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.girlcum] is [style.boldShrink(no longer addictive)]!"
							+ "</p>");
				}
			case ALCOHOLIC:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soft coolness spreading up into your [pc.pussy], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.girlcum] is [style.boldShrink(no longer alcoholic)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A soft coolness spreads up into [npc.name]'s [npc.pussy], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.girlcum] is [style.boldShrink(no longer alcoholic)]!"
							+ "</p>");
				}
			case BUBBLING:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a calm, settling feeling rising up into your [pc.pussy], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.girlcum] is [style.boldShrink(no longer bubbly)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A calm, settling feeling spreads up into [npc.name]'s [npc.pussy], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.girlcum] is [style.boldShrink(no longer bubbly)]!"
							+ "</p>");
				}
			case HALLUCINOGENIC:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a series of soothing waves wash up into your [pc.pussy], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.girlcum] is [style.boldShrink(no longer psychoactive)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A series of soothing waves wash up into [npc.name]'s [npc.pussy], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.girlcum] is [style.boldShrink(no longer psychoactive)]!"
							+ "</p>");
				}
			case MUSKY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a gentle coolness rise up into your [pc.pussy], causing you to let out soft sigh.</br>"
								+ "Your [pc.girlcum] is [style.boldShrink(no longer musky)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A gentle coolness rises up into [npc.name]'s [npc.pussy], causing [npc.herHim] to let out a soft sigh.</br>"
								+ "[npc.Name]'s [npc.girlcum] is [style.boldShrink(no longer musky)]!"
							+ "</p>");
				}
			case SLIMY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a calming coolness flow up into your [pc.pussy], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.girlcum] is [style.boldShrink(no longer slimy)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A calming coolness flows up into [npc.name]'s [npc.pussy], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.girlcum] is [style.boldShrink(no longer slimy)]!"
							+ "</p>");
				}
			case STICKY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soft warmth flow up into your [pc.pussy], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.girlcum] is [style.boldShrink(no longer sticky)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A soft warmth flows up into [npc.name]'s [npc.pussy], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.girlcum] is [style.boldShrink(no longer sticky)]!"
							+ "</p>");
				}
			case VISCOUS:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soft coolness slowly rising up into your [pc.pussy], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.girlcum] is [style.boldShrink(no longer viscous)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A soft coolness rises up into [npc.name]'s [npc.pussy], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.girlcum] is [style.boldShrink(no longer viscous)]!"
							+ "</p>");
				}
			case SWEET:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a calming coolness flow up into your [pc.pussy], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.girlcum] is [style.boldShrink(no longer sweet)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A calming coolness flows up into [npc.name]'s [npc.pussy], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.girlcum] is [style.boldShrink(no longer sweet)]!"
							+ "</p>");
				}
		}
		
		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}
	
	
	/*
	 * case TRANSFORMATIVE:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a powerful pulse of arcane energy shoot up into your [pc.pussy], causing you to let out [pc.a_moan+].</br>"
								+ "Your [pc.girlcum] is now [style.boldGrow(transformative)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A powerful pulse of arcane energy shoots up into [npc.name]'s [npc.pussy], causing [npc.herHim] to let out [npc.a_moan+].</br>"
								+ "[npc.Name]'s [npc.girlcum] is now [style.boldGrow(transformative)]!"
							+ "</p>");
				}
				
			case TRANSFORMATIVE:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soothing wave of arcane energy disperse from your [pc.pussy], causing you to let out a gentle sigh.</br>"
								+ "Your [pc.girlcum] is [style.boldShrink(no longer transformative)]!"
							+ "</p>";
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A soothing wave of arcane energy disperses from [npc.name]'s [npc.pussy], causing [npc.herHim] to let out a gentle sigh.</br>"
								+ "[npc.Name]'s [npc.girlcum] is [style.boldShrink(no longer transformative)]!"
							+ "</p>");
				}
	 */
	
	public List<ItemEffect> getTransformativeEffects() {
		return transformativeEffects;
	}

	/**
	 * DO NOT MODIFY!
	 */
	public List<FluidModifier> getFluidModifiers() {
		return fluidModifiers;
	}

	public float getValuePerMl() {
		return 1f + this.getFluidModifiers().size()*1f + (this.getFlavour()!=FluidFlavour.GIRL_CUM?0.5f:0);
	}
}
