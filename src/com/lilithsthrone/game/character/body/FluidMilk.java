package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.controller.xmlParsing.Element;
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
	
	public boolean saveAsXML(Element parentElement) {
		Element milkElement = parentElement.addElement("milk");
		milkElement.addAttribute("type", FluidType.getIdFromFluidType(type));
		milkElement.addAttribute("flavour", flavour.toString());
		milkElement.addAttribute("crotchMilk", String.valueOf(crotchMilk));
		
		Element modifiers = milkElement.addElement("milkModifiers");
		for(FluidModifier fm : fluidModifiers) {
			modifiers.addAttribute(fm.toString(), "true");
		}
		return true;
	}
	
	public static FluidMilk loadFromXML(Element parentElement) {
		try {
			Element milkElement = parentElement.getMandatoryFirstOf("milk");
			FluidMilk fluidMilk = new FluidMilk(FluidType.getFluidTypeFromId(milkElement.getAttribute("type")), Boolean.parseBoolean(milkElement.getAttribute("crotchMilk")));
			fluidMilk.flavour = FluidFlavour.valueOf(milkElement.getAttribute("flavour"));
			if(milkElement.getOptionalFirstOf("milkModifiers").isPresent()) {
				Element milkModifiersElement = milkElement.getMandatoryFirstOf("milkModifiers");
				for(FluidModifier fm : FluidModifier.values()) {
					if(!milkModifiersElement.getAttribute(fm.toString()).isEmpty()) {
						fluidMilk.fluidModifiers.add(fm);
					}
				}
			}
			return fluidMilk;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof FluidMilk){
			return ((FluidMilk) o).getType().equals(this.getType())
					&& ((FluidMilk) o).getFlavour() == this.getFlavour()
					&& ((FluidMilk) o).getFluidModifiers().equals(this.getFluidModifiers())
					&& ((FluidMilk) o).getTransformativeEffects().equals(this.getTransformativeEffects());
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
		return (0.1f + (this.getFluidModifiers().size()*0.1f)) + (this.getFlavour()!=FluidFlavour.MILK?1.5f:1);
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
