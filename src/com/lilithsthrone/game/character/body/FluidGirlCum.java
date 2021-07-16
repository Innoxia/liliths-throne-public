package com.lilithsthrone.game.character.body;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFluidType;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 0.1.83
 * @version 0.3.8.2
 * @author Innoxia
 */
public class FluidGirlCum implements FluidInterface, XMLSaving {
	
	protected AbstractFluidType type;
	protected FluidFlavour flavour;
	protected List<FluidModifier> fluidModifiers;
	protected List<ItemEffect> transformativeEffects;
	
	public FluidGirlCum(AbstractFluidType type, FluidFlavour flavour, List<FluidModifier> fluidModifiers, List<ItemEffect> transformativeEffects) {
		this.type = type;
		this.flavour = flavour;
		this.fluidModifiers = fluidModifiers;
		this.transformativeEffects = transformativeEffects;
	}

	public FluidGirlCum(AbstractFluidType type) {
		this.type = type;
		this.flavour = type.getFlavour();
		transformativeEffects = new ArrayList<>();
		
		fluidModifiers = new ArrayList<>();
		fluidModifiers.addAll(type.getDefaultFluidModifiers());
	}
	
	public boolean saveAsXML(Element parentElement) {
		Element girlCumElement = parentElement.addElement("girlCum");
		girlCumElement.addAttribute("type", FluidType.getIdFromFluidType(type));
		girlCumElement.addAttribute("flavour", flavour.toString());
		Element modifiers = girlCumElement.addElement("cumModifiers");
		for(FluidModifier fm : fluidModifiers) {
			modifiers.addAttribute(fm.toString(), "true");
		}
		Element transformativeEffectsElement = girlCumElement.addElement("transformativeEffects");
		for(ItemEffect ie : transformativeEffects) {
			Element itemElement = transformativeEffectsElement.addElement("item");
			ie.saveAsXML(itemElement);
		}
		return true;
	}
	
	public static FluidGirlCum loadFromXML(Element parentElement) {
		try {
			Element girlCumElement = parentElement.getMandatoryFirstOf("girlCum");
			Element girlCumModifiersElement = girlCumElement.getMandatoryFirstOf("cumModifiers");
			List<FluidModifier> modifiers = new ArrayList<>();
			for(String key : girlCumModifiersElement.getAttributes().keySet()) {
				modifiers.add(FluidModifier.valueOf(key));
			}
			Element transformativeEffectsElement = girlCumElement.getMandatoryFirstOf("transformativeEffects");
			List<ItemEffect> effects = new ArrayList<>();
			for(Element item : transformativeEffectsElement.getAllOf("item")) {
				effects.add(ItemEffect.loadFromXML(item));
			}
			return new FluidGirlCum(FluidType.getFluidTypeFromId(girlCumElement.getAttribute("type")),
					FluidFlavour.valueOf(girlCumElement.getAttribute("flavour")),
					modifiers,
					effects);
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof FluidGirlCum){
			return ((FluidGirlCum) o).getType().equals(this.getType())
					&& ((FluidGirlCum) o).getFlavour() == this.getFlavour()
					&& ((FluidGirlCum) o).getFluidModifiers().equals(this.getFluidModifiers())
					&& ((FluidGirlCum) o).getTransformativeEffects().equals(this.getTransformativeEffects());
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
	public AbstractFluidType getType() {
		return type;
	}

	public void setType(AbstractFluidType type) {
		this.type = type;
	}

	public FluidFlavour getFlavour() {
		return flavour;
	}

	public String setFlavour(GameCharacter owner, FluidFlavour flavour) {
		if (owner == null) {
			this.flavour = flavour;
			return "";
		}
		if(this.flavour == flavour || !owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}

		this.flavour = flavour;
		
		return UtilText.parse(owner,
				"<p>"
					+ "A soothing warmth spreads down into [npc.namePos] [npc.pussy], causing [npc.herHim] to let out an involuntary [npc.moan].<br/>"
					+ "[npc.NamePos] [pc.girlcum] now tastes of <b style='color:"+flavour.getColour().toWebHexString()+";'>"+flavour.getName()+"</b>."
				+ "</p>");
	}
	
	public boolean hasFluidModifier(FluidModifier fluidModifier) {
		return fluidModifiers.contains(fluidModifier);
	}
	
	public String addFluidModifier(GameCharacter owner, FluidModifier fluidModifier) {
		if(owner==null && !fluidModifiers.contains(fluidModifier)) {
			fluidModifiers.add(fluidModifier);
			return "";
		}
		if(fluidModifiers.contains(fluidModifier) || !owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		fluidModifiers.add(fluidModifier);

		switch(fluidModifier) {
			case ADDICTIVE:
				return UtilText.parse(owner,
						"<p>"
							+ "A strange, pulsating heat takes root deep within [npc.namePos] [npc.pussy], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
							+ "[npc.NamePos] [npc.girlcum] is now [style.boldGrow(addictive)]!"
						+ "</p>");
			case ALCOHOLIC:
				return UtilText.parse(owner,
						"<p>"
							+ "A strange, soothing warmth takes root deep within [npc.namePos] [npc.pussy], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
							+ "[npc.NamePos] [npc.girlcum] is now [style.boldGrow(alcoholic)]!"
						+ "</p>");
			case BUBBLING:
				return UtilText.parse(owner,
						"<p>"
							+ "A light, bubbly feeling rises up into [npc.namePos] [npc.pussy], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
							+ "[npc.NamePos] [npc.girlcum] is now [style.boldGrow(bubbly)]!"
						+ "</p>");
			case HALLUCINOGENIC:
				return UtilText.parse(owner,
						"<p>"
							+ "A series of strange pulses shoot down into [npc.namePos] [npc.pussy], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
							+ "[npc.NamePos] [npc.girlcum] is now [style.boldGrow(psychoactive)]!"
						+ "</p>");
			case MINERAL_OIL:
				return UtilText.parse(owner,
						"<p>"
							+ "A soothing warmth flows into [npc.namePos] [npc.pussy], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
							+ "[npc.NamePos] [npc.girlcum] is now imbued with [style.boldGrow(mineral oil)], and can melt condoms!"
						+ "</p>");
			case MUSKY:
				return UtilText.parse(owner,
						"<p>"
							+ "A slow, creeping warmth rises up into [npc.namePos] [npc.pussy], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
							+ "[npc.NamePos] [npc.girlcum] is now [style.boldGrow(musky)]!"
						+ "</p>");
			case SLIMY:
				return UtilText.parse(owner,
						"<p>"
							+ "A strange, soothing warmth flows up into [npc.namePos] [npc.pussy], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
							+ "[npc.NamePos] [npc.girlcum] is now [style.boldGrow(slimy)]!"
						+ "</p>");
			case STICKY:
				return UtilText.parse(owner,
						"<p>"
							+ "A thick, sickly warmth flows up into [npc.namePos] [npc.pussy], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
							+ "[npc.NamePos] [npc.girlcum] is now [style.boldGrow(sticky)]!"
						+ "</p>");
			case VISCOUS:
				return UtilText.parse(owner,
						"<p>"
							+ "A heavy heat slowly rises up into [npc.namePos] [npc.pussy], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
							+ "[npc.NamePos] [npc.girlcum] is now [style.boldGrow(viscous)]!"
						+ "</p>");
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
				return UtilText.parse(owner,
						"<p>"
							+ "A soft coolness spreads up into [npc.namePos] [npc.pussy], causing [npc.herHim] to let out a gentle sigh.<br/>"
							+ "[npc.NamePos] [npc.girlcum] is [style.boldShrink(no longer addictive)]!"
						+ "</p>");
			case ALCOHOLIC:
				return UtilText.parse(owner,
						"<p>"
							+ "A soft coolness spreads up into [npc.namePos] [npc.pussy], causing [npc.herHim] to let out a gentle sigh.<br/>"
							+ "[npc.NamePos] [npc.girlcum] is [style.boldShrink(no longer alcoholic)]!"
						+ "</p>");
			case BUBBLING:
				return UtilText.parse(owner,
						"<p>"
							+ "A calm, settling feeling spreads up into [npc.namePos] [npc.pussy], causing [npc.herHim] to let out a gentle sigh.<br/>"
							+ "[npc.NamePos] [npc.girlcum] is [style.boldShrink(no longer bubbly)]!"
						+ "</p>");
			case HALLUCINOGENIC:
				return UtilText.parse(owner,
						"<p>"
							+ "A series of soothing waves wash up into [npc.namePos] [npc.pussy], causing [npc.herHim] to let out a gentle sigh.<br/>"
							+ "[npc.NamePos] [npc.girlcum] is [style.boldShrink(no longer psychoactive)]!"
						+ "</p>");
			case MUSKY:
				return UtilText.parse(owner,
						"<p>"
							+ "A gentle coolness rises up into [npc.namePos] [npc.pussy], causing [npc.herHim] to let out a soft sigh.<br/>"
							+ "[npc.NamePos] [npc.girlcum] is [style.boldShrink(no longer musky)]!"
						+ "</p>");
			case SLIMY:
				return UtilText.parse(owner,
						"<p>"
							+ "A calming coolness flows up into [npc.namePos] [npc.pussy], causing [npc.herHim] to let out a gentle sigh.<br/>"
							+ "[npc.NamePos] [npc.girlcum] is [style.boldShrink(no longer slimy)]!"
						+ "</p>");
			case STICKY:
				return UtilText.parse(owner,
						"<p>"
							+ "A soft warmth flows up into [npc.namePos] [npc.pussy], causing [npc.herHim] to let out a gentle sigh.<br/>"
							+ "[npc.NamePos] [npc.girlcum] is [style.boldShrink(no longer sticky)]!"
						+ "</p>");
			case VISCOUS:
				return UtilText.parse(owner,
						"<p>"
							+ "A soft coolness rises up into [npc.namePos] [npc.pussy], causing [npc.herHim] to let out a gentle sigh.<br/>"
							+ "[npc.NamePos] [npc.girlcum] is [style.boldShrink(no longer viscous)]!"
						+ "</p>");
			case MINERAL_OIL:
				return UtilText.parse(owner,
						"<p>"
							+ "A short relief flows up into [npc.namePos] [npc.pussy], causing [npc.herHim] to let out a gentle sigh.<br/>"
							+ "[npc.NamePos] [npc.girlcum] is [style.boldShrink(no longer mineral oil)]!"
						+ "</p>");
		}
		
		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}
	
	public List<ItemEffect> getTransformativeEffects() {
		return transformativeEffects;
	}
	
	public void addTransformativeEffect(ItemEffect ie) {
		transformativeEffects.add(ie);
	}

	/**
	 * DO NOT MODIFY!
	 */
	public List<FluidModifier> getFluidModifiers() {
		return fluidModifiers;
	}
	
	public void clearFluidModifiers() {
		fluidModifiers.clear();
	}

	public float getValuePerMl() {
		return (4f + (this.getFluidModifiers().size()*0.5f)) * (this.getFlavour()!=FluidFlavour.GIRL_CUM?1.5f:1);
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(FluidGirlCum.class) && getType().getRace().isFeralPartsAvailable());
	}
}
