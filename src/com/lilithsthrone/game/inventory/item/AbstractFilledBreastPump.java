package com.lilithsthrone.game.inventory.item;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.FluidMilk;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.2.1
 * @version 0.2.1
 * @author Innoxia
 */
public class AbstractFilledBreastPump extends AbstractItem implements Serializable, XMLSaving {
	
	private static final long serialVersionUID = 1L;
	
	private String milkProvidor;
	private FluidMilk milk;
	private int millilitresStored;
	
	public AbstractFilledBreastPump(AbstractItemType itemType, Colour colour, GameCharacter milkProvidor, FluidMilk milk, int millilitresStored) {
		super(itemType);
		
		this.milkProvidor = milkProvidor.getId();
		this.milk = new FluidMilk(milk.getType());
		this.milk.setFlavour(milkProvidor, milk.getFlavour());
		for(FluidModifier fm : milk.getFluidModifiers()) {
			this.milk.addFluidModifier(milkProvidor, fm);
		}
		this.colourShade = colour;
		SVGString = getSVGString(itemType.getPathName(), colour);
		this.millilitresStored = millilitresStored;
	}
	
	public AbstractFilledBreastPump(AbstractItemType itemType, Colour colour, String milkProvidorId, FluidMilk milk, int millilitresStored) {
		super(itemType);
		
		this.milkProvidor = milkProvidorId;
		this.milk = new FluidMilk(milk.getType());
		this.milk.setFlavour(null, milk.getFlavour());
		for(FluidModifier fm : milk.getFluidModifiers()) {
			this.milk.addFluidModifier(null, fm);
		}
		this.colourShade = colour;
		SVGString = getSVGString(itemType.getPathName(), colour);
		this.millilitresStored = millilitresStored;
	}
	
	@Override
	public boolean equals (Object o) {
		if(super.equals(o)) {
			return (o instanceof AbstractFilledBreastPump)
					&& ((AbstractFilledBreastPump)o).getMilkProvidorId().equals(this.getMilkProvidorId())
					&& ((AbstractFilledBreastPump)o).getMilk().equals(milk);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + milkProvidor.hashCode();
		result = 31 * result + milk.hashCode();
		return result;
	}

	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("item");
		parentElement.appendChild(element);
		
		CharacterUtils.addAttribute(doc, element, "id", this.getItemType().getId());
		CharacterUtils.addAttribute(doc, element, "colour", String.valueOf(this.getColour()));
		CharacterUtils.addAttribute(doc, element, "milkProvidor", this.getMilkProvidorId());
		CharacterUtils.addAttribute(doc, element, "millilitresStored", String.valueOf(this.getMillilitresStored()));
		
		Element innerElement = doc.createElement("itemEffects");
		element.appendChild(innerElement);
		for(ItemEffect ie : this.getEffects()) {
			ie.saveAsXML(innerElement, doc);
		}

		innerElement = doc.createElement("milk");
		element.appendChild(innerElement);
		this.getMilk().saveAsXML(innerElement, doc);
		
		return element;
	}

	public static AbstractFilledBreastPump loadFromXML(Element parentElement, Document doc) {
		return new AbstractFilledBreastPump(
				ItemType.idToItemMap.get(parentElement.getAttribute("id")),
				Colour.valueOf(parentElement.getAttribute("colour")),
				parentElement.getAttribute("milkProvidor"),
				((Element) parentElement.getElementsByTagName("milk").item(0)==null
					?new FluidMilk(FluidType.MILK_HUMAN)
					:FluidMilk.loadFromXML((Element) parentElement.getElementsByTagName("milk").item(0), doc)),
				(parentElement.getAttribute("millilitresStored").isEmpty()
					?25
					:Integer.valueOf(parentElement.getAttribute("millilitresStored"))));
	}
	
	private String getSVGString(String pathName, Colour colour) {
		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/" + pathName + ".svg");
			String s = Util.inputStreamToString(is);

			for (int i = 0; i <= 14; i++)
				s = s.replaceAll("linearGradient" + i, this.hashCode() + colour.toString() + "linearGradient" + i);
			s = s.replaceAll("#ff2a2a", colour.getShades()[0]);
			s = s.replaceAll("#ff5555", colour.getShades()[1]);
			s = s.replaceAll("#ff8080", colour.getShades()[2]);
			s = s.replaceAll("#ffaaaa", colour.getShades()[3]);
			s = s.replaceAll("#ffd5d5", colour.getShades()[4]);
			
			is.close();
			
			return s;
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return "";
	}
	
	@Override
	public String applyEffect(GameCharacter user, GameCharacter target) {
		return target.ingestFluid(getMilkProvidor(), milk.getType(), SexAreaOrifice.MOUTH, millilitresStored, milk.getFluidModifiers())
				+ target.addItem(AbstractItemType.generateItem(ItemType.MOO_MILKER_EMPTY), false);
	}
	
	public String getMilkProvidorId() {
		return milkProvidor;
	}
	
	public GameCharacter getMilkProvidor() {
		return Main.game.getNPCById(milkProvidor);
	}

	public FluidMilk getMilk() {
		return milk;
	}

	public int getMillilitresStored() {
		return millilitresStored;
	}

	public void setMillilitresStored(int millilitresStored) {
		this.millilitresStored = millilitresStored;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
