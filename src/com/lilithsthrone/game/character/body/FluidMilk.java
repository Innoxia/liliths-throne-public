package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFluidType;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.3.8.2
 * @author Innoxia
 */
public class FluidMilk implements FluidInterface {

	
	protected AbstractFluidType type;
	protected FluidFlavour flavour;
	protected List<FluidModifier> fluidModifiers;
	protected List<ItemEffect> transformativeEffects;
	
	protected boolean crotchMilk;

	public FluidMilk(AbstractFluidType type, boolean crotchMilk) {
		this.type = type;
		this.flavour = type.getFlavour();
		transformativeEffects = new ArrayList<>();
		
		fluidModifiers = new ArrayList<>();
		fluidModifiers.addAll(type.getDefaultFluidModifiers());
		
		this.crotchMilk = crotchMilk;
	}
	
	public Element saveAsXML(String rootElementName, Element parentElement, Document doc) {
		Element element = doc.createElement(rootElementName);
		parentElement.appendChild(element);

		XMLUtil.addAttribute(doc, element, "type", FluidType.getIdFromFluidType(this.type));
		XMLUtil.addAttribute(doc, element, "flavour", this.flavour.toString());
		
		Element milkModifiers = doc.createElement("milkModifiers");
		element.appendChild(milkModifiers);
		for(FluidModifier fm : this.getFluidModifiers()) {
			XMLUtil.addAttribute(doc, milkModifiers, fm.toString(), "true");
		}
		
		return element;
	}
	
	public static FluidMilk loadFromXML(String rootElementName, Element parentElement, Document doc) {
		return loadFromXML(rootElementName, parentElement, doc, null, false);
	}
	
	/**
	 * 
	 * @param parentElement
	 * @param doc
	 * @param baseType If you pass in a baseType, this method will ignore the saved type in parentElement.
	 */
	public static FluidMilk loadFromXML(String rootElementName, Element parentElement, Document doc, AbstractFluidType baseType, boolean crotchMilk) {
		
		Element milk = (Element)parentElement.getElementsByTagName(rootElementName).item(0);
		
		AbstractFluidType fluidType = FluidType.MILK_HUMAN;
		
		if(baseType!=null) {
			fluidType = baseType;
			
		} else {
			try {
				fluidType = FluidType.getFluidTypeFromId(milk.getAttribute("type"));
			} catch(Exception ex) {
			}
		}
		
		FluidMilk fluidMilk = new FluidMilk(fluidType, crotchMilk);
		
		String flavourId = milk.getAttribute("flavour");
		if(flavourId.equalsIgnoreCase("SLIME")) {
			fluidMilk.flavour = FluidFlavour.BUBBLEGUM;
		} else {
			fluidMilk.flavour = FluidFlavour.valueOf(flavourId);
		}
		
		Element milkModifiersElement = (Element)milk.getElementsByTagName("milkModifiers").item(0);
		fluidMilk.fluidModifiers.clear();
		if(milkModifiersElement!=null) {
			Collection<FluidModifier> milkFluidModifiers = fluidMilk.fluidModifiers;
			Body.handleLoadingOfModifiers(FluidModifier.values(), null, milkModifiersElement, milkFluidModifiers);
		}
		
		return fluidMilk;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof FluidMilk){
			if(((FluidMilk)o).getType().equals(this.getType())
				&& ((FluidMilk)o).getFlavour() == this.getFlavour()
				&& ((FluidMilk)o).getFluidModifiers().equals(this.getFluidModifiers())
				&& ((FluidMilk)o).getTransformativeEffects().equals(this.getTransformativeEffects())){
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
		if(owner==null) {
			this.flavour = flavour;
			return "";
		}
		
		if(this.flavour == flavour) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}

		this.flavour = flavour;
		
		if(this.isCrotchMilk()) {
			return UtilText.parse(owner,
					"<p>"
						+ "A soothing warmth spreads through [npc.namePos] [npc.crotchBoobs], causing [npc.herHim] to let out a contented little sigh.<br/>"
						+ "[npc.NamePos] [npc.crotchMilk] now tastes of <b style='color:"+flavour.getColour().toWebHexString()+";'>"+flavour.getName()+"</b>."
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "A soothing warmth spreads through [npc.namePos] [npc.breasts], causing [npc.herHim] to let out a contented little sigh.<br/>"
						+ "[npc.NamePos] [npc.milk] now tastes of <b style='color:"+flavour.getColour().toWebHexString()+";'>"+flavour.getName()+"</b>."
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
		
		if(owner == null) {
			return "";
		}
		
		switch(fluidModifier) {
			case ADDICTIVE:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "A strange, pulsating heat spreads through [npc.namePos] [npc.crotchBoobs], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
								+ "[npc.NamePos] [npc.crotchMilk] is now [style.boldGrow(addictive)]!"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A strange, pulsating heat spreads through [npc.namePos] [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
								+ "[npc.NamePos] [npc.milk] is now [style.boldGrow(addictive)]!"
							+ "</p>");
				}
			case ALCOHOLIC:
				fluidModifiers.remove(FluidModifier.ALCOHOLIC_WEAK);
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "A strange, soothing warmth spreads up through [npc.namePos] [npc.crotchBoobs], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
								+ "[npc.NamePos] [npc.crotchMilk] is now [style.boldGrow(strongly alcoholic)]!"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A strange, soothing warmth spreads up through [npc.namePos] [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
								+ "[npc.NamePos] [npc.milk] is now [style.boldGrow(strongly alcoholic)]!"
							+ "</p>");
				}
			case ALCOHOLIC_WEAK:
				fluidModifiers.remove(FluidModifier.ALCOHOLIC);
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "A strange, soothing warmth spreads up through [npc.namePos] [npc.crotchBoobs], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
								+ "[npc.NamePos] [npc.crotchMilk] is now [style.boldGrow(alcoholic)]!"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A strange, soothing warmth spreads up through [npc.namePos] [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
								+ "[npc.NamePos] [npc.milk] is now [style.boldGrow(alcoholic)]!"
							+ "</p>");
				}
			case BUBBLING:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "A light, bubbly feeling spreads up through [npc.namePos] [npc.crotchBoobs], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
								+ "[npc.NamePos] [npc.crotchMilk] is now [style.boldGrow(bubbly)]!"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A light, bubbly feeling spreads up through [npc.namePos] [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
								+ "[npc.NamePos] [npc.milk] is now [style.boldGrow(bubbly)]!"
							+ "</p>");
				}
			case HALLUCINOGENIC:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "A series of strange pulses shoot up through [npc.namePos] [npc.crotchBoobs], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
								+ "[npc.NamePos] [npc.crotchMilk] is now [style.boldGrow(psychoactive)]!"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A series of strange pulses shoot up through [npc.namePos] [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
								+ "[npc.NamePos] [npc.milk] is now [style.boldGrow(psychoactive)]!"
							+ "</p>");
				}
			case MINERAL_OIL:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "A soothing warmth flows into [npc.namePos] [npc.crotchBoobs], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
								+ "[npc.NamePos] [npc.crotchMilk] is now imbued with [style.boldGrow(mineral oil)], and can melt condoms!"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A soothing warmth flows into [npc.namePos] [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
								+ "[npc.NamePos] [npc.milk] is now imbued with [style.boldGrow(mineral oil)], and can melt condoms!"
							+ "</p>");
				}
			case MUSKY:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "A slow, creeping warmth rises up into [npc.namePos] [npc.crotchBoobs], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
								+ "[npc.NamePos] [npc.crotchMilk] is now [style.boldGrow(musky)]!"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A slow, creeping warmth rises up into [npc.namePos] [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
								+ "[npc.NamePos] [npc.milk] is now [style.boldGrow(musky)]!"
							+ "</p>");
				}
			case SLIMY:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "A strange, soothing warmth flows up into [npc.namePos] [npc.crotchBoobs], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
								+ "[npc.NamePos] [npc.crotchMilk] is now [style.boldGrow(slimy)]!"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A strange, soothing warmth flows up into [npc.namePos] [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
								+ "[npc.NamePos] [npc.milk] is now [style.boldGrow(slimy)]!"
							+ "</p>");
				}
			case STICKY:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "A thick, sickly warmth flows up into [npc.namePos] [npc.crotchBoobs], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
								+ "[npc.NamePos] [npc.crotchMilk] is now [style.boldGrow(sticky)]!"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A thick, sickly warmth flows up into [npc.namePos] [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
								+ "[npc.NamePos] [npc.milk] is now [style.boldGrow(sticky)]!"
							+ "</p>");
				}
			case VISCOUS:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "A heavy heat slowly rises up into [npc.namePos] [npc.crotchBoobs], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
								+ "[npc.NamePos] [npc.crotchMilk] is now [style.boldGrow(viscous)]!"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A heavy heat slowly rises up into [npc.namePos] [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+].<br/>"
								+ "[npc.NamePos] [npc.milk] is now [style.boldGrow(viscous)]!"
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
		
		if(owner == null) {
			return "";
		}
		
		switch(fluidModifier) {
			case ADDICTIVE:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "A soft coolness spreads up through [npc.namePos] [npc.crotchBoobs], causing [npc.herHim] to let out a gentle sigh.<br/>"
								+ "[npc.NamePos] [npc.crotchMilk] is [style.boldShrink(no longer addictive)]!"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A soft coolness spreads up through [npc.namePos] [npc.breasts], causing [npc.herHim] to let out a gentle sigh.<br/>"
								+ "[npc.NamePos] [npc.milk] is [style.boldShrink(no longer addictive)]!"
							+ "</p>");
				}
			case ALCOHOLIC:
			case ALCOHOLIC_WEAK:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "A soft coolness spreads up through [npc.namePos] [npc.crotchBoobs], causing [npc.herHim] to let out a gentle sigh.<br/>"
								+ "[npc.NamePos] [npc.crotchMilk] is [style.boldShrink(no longer alcoholic)]!"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A soft coolness spreads up through [npc.namePos] [npc.breasts], causing [npc.herHim] to let out a gentle sigh.<br/>"
								+ "[npc.NamePos] [npc.milk] is [style.boldShrink(no longer alcoholic)]!"
							+ "</p>");
				}
			case BUBBLING:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "A calm, settling feeling spreads up through [npc.namePos] [npc.crotchBoobs], causing [npc.herHim] to let out a gentle sigh.<br/>"
								+ "[npc.NamePos] [npc.crotchMilk] is [style.boldShrink(no longer bubbly)]!"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A calm, settling feeling spreads up through [npc.namePos] [npc.breasts], causing [npc.herHim] to let out a gentle sigh.<br/>"
								+ "[npc.NamePos] [npc.milk] is [style.boldShrink(no longer bubbly)]!"
							+ "</p>");
				}
			case HALLUCINOGENIC:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "A series of soothing waves wash up through [npc.namePos] [npc.crotchBoobs], causing [npc.herHim] to let out a gentle sigh.<br/>"
								+ "[npc.NamePos] [npc.crotchMilk] is [style.boldShrink(no longer psychoactive)]!"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A series of soothing waves wash up through [npc.namePos] [npc.breasts], causing [npc.herHim] to let out a gentle sigh.<br/>"
								+ "[npc.NamePos] [npc.milk] is [style.boldShrink(no longer psychoactive)]!"
							+ "</p>");
				}
			case MUSKY:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "A gentle coolness rises up into [npc.namePos] [npc.crotchBoobs], causing [npc.herHim] to let out a soft sigh.<br/>"
								+ "[npc.NamePos] [npc.crotchMilk] is [style.boldShrink(no longer musky)]!"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A gentle coolness rises up into [npc.namePos] [npc.breasts], causing [npc.herHim] to let out a soft sigh.<br/>"
								+ "[npc.NamePos] [npc.milk] is [style.boldShrink(no longer musky)]!"
							+ "</p>");
				}
			case SLIMY:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "A calming coolness flows up into [npc.namePos] [npc.crotchBoobs], causing [npc.herHim] to let out a gentle sigh.<br/>"
								+ "[npc.NamePos] [npc.crotchMilk] is [style.boldShrink(no longer slimy)]!"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A calming coolness flows up into [npc.namePos] [npc.breasts], causing [npc.herHim] to let out a gentle sigh.<br/>"
								+ "[npc.NamePos] [npc.milk] is [style.boldShrink(no longer slimy)]!"
							+ "</p>");
				}
			case STICKY:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "A soft warmth flows up into [npc.namePos] [npc.crotchBoobs], causing [npc.herHim] to let out a gentle sigh.<br/>"
								+ "[npc.NamePos] [npc.crotchMilk] is [style.boldShrink(no longer sticky)]!"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A soft warmth flows up into [npc.namePos] [npc.breasts], causing [npc.herHim] to let out a gentle sigh.<br/>"
								+ "[npc.NamePos] [npc.milk] is [style.boldShrink(no longer sticky)]!"
							+ "</p>");
				}
			case VISCOUS:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "A soft coolness rises up into [npc.namePos] [npc.crotchBoobs], causing [npc.herHim] to let out a gentle sigh.<br/>"
								+ "[npc.NamePos] [npc.crotchMilk] is [style.boldShrink(no longer viscous)]!"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A soft coolness rises up into [npc.namePos] [npc.breasts], causing [npc.herHim] to let out a gentle sigh.<br/>"
								+ "[npc.NamePos] [npc.milk] is [style.boldShrink(no longer viscous)]!"
							+ "</p>");
				}
			case MINERAL_OIL:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "A short relief flows up into [npc.namePos] [npc.crotchBoobs], causing [npc.herHim] to let out a gentle sigh.<br/>"
								+ "[npc.NamePos] [npc.crotchMilk] is [style.boldShrink(no longer mineral oil)]!"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "A short relief flows up into [npc.namePos] [npc.breasts], causing [npc.herHim] to let out a gentle sigh.<br/>"
								+ "[npc.NamePos] [npc.milk] is [style.boldShrink(no longer mineral oil)]!"
							+ "</p>");
				}
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
		return 0.01f;
	}

	public boolean isCrotchMilk() {
		return crotchMilk;
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(FluidMilk.class) && getType().getRace().isFeralPartsAvailable());
	}
}
