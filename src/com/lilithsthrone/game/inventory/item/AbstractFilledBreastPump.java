package com.lilithsthrone.game.inventory.item;

import java.io.IOException;
import java.io.InputStream;

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
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.2.1
 * @version 0.2.1
 * @author Innoxia
 */
public class AbstractFilledBreastPump extends AbstractItem implements XMLSaving {
	
	
	private String milkProvider;
	private FluidMilk milk;
	private int millilitresStored;
	
	public AbstractFilledBreastPump(AbstractItemType itemType, Colour colour, GameCharacter milkProvider, FluidMilk milk, int millilitresStored) {
		super(itemType);
		
		this.milkProvider = milkProvider.getId();
		this.milk = new FluidMilk(milk.getType(), milk.isCrotchMilk());
		this.milk.setFlavour(milkProvider, milk.getFlavour());
		for(FluidModifier fm : milk.getFluidModifiers()) {
			this.milk.addFluidModifier(milkProvider, fm);
		}
		this.colourShade = colour;
		SVGString = getSVGString(itemType.getPathName(), colour);
		this.millilitresStored = millilitresStored;
	}
	
	public AbstractFilledBreastPump(AbstractItemType itemType, Colour colour, String milkProviderId, FluidMilk milk, int millilitresStored) {
		super(itemType);
		
		this.milkProvider = milkProviderId;
		this.milk = new FluidMilk(milk.getType(), milk.isCrotchMilk());
		this.milk.setFlavour(null, milk.getFlavour());
		for(FluidModifier fm : milk.getFluidModifiers()) {
			this.milk.addFluidModifier(null, fm);
		}
		this.colourShade = colour;
		SVGString = getSVGString(itemType.getPathName(), colour);
		this.millilitresStored = millilitresStored;
	}
	
	@Override
	public boolean equals(Object o) {
		if(super.equals(o)) {
			return (o instanceof AbstractFilledBreastPump)
					&& ((AbstractFilledBreastPump)o).getMilkProviderId().equals(this.getMilkProviderId())
					&& ((AbstractFilledBreastPump)o).getMilk().equals(milk);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + milkProvider.hashCode();
		result = 31 * result + milk.hashCode();
		return result;
	}

	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("item");
		parentElement.appendChild(element);
		
		CharacterUtils.addAttribute(doc, element, "id", this.getItemType().getId());
		CharacterUtils.addAttribute(doc, element, "colour", String.valueOf(this.getColour()));
		CharacterUtils.addAttribute(doc, element, "milkProvider", Main.game.checkId(this.getMilkProviderId()));
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
		String provider = parentElement.getAttribute("milkProvider");
		if(provider.isEmpty()) {
			provider = parentElement.getAttribute("milkProvidor"); // Support for old versions in which I could not spell
		}
		return new AbstractFilledBreastPump(
				ItemType.getIdToItemMap().get(parentElement.getAttribute("id")),
				Colour.valueOf(parentElement.getAttribute("colour")),
				provider,
				((Element) parentElement.getElementsByTagName("milk").item(0)==null
					?new FluidMilk(FluidType.MILK_HUMAN, false)
					:FluidMilk.loadFromXML((Element) parentElement.getElementsByTagName("milk").item(0), doc)),
				(parentElement.getAttribute("millilitresStored").isEmpty()
					?25
					:Integer.valueOf(parentElement.getAttribute("millilitresStored"))));
	}
	
	private String getSVGString(String pathName, Colour colour) {
		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/" + pathName + ".svg");
			String s = Util.inputStreamToString(is);
			
			s = SvgUtil.colourReplacement(String.valueOf(this.hashCode()), colour, s);
			
			is.close();
			
			return s;
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return "";
	}
	
	@Override
	public String applyEffect(GameCharacter user, GameCharacter target) {
		return target.ingestFluid(getMilkProvider(), milk, SexAreaOrifice.MOUTH, millilitresStored)
				+ target.addItem(AbstractItemType.generateItem(ItemType.MOO_MILKER_EMPTY), false);
	}
	
	public String getMilkProviderId() {
		return milkProvider;
	}
	
	public GameCharacter getMilkProvider() {
		try {
			return Main.game.getNPCById(milkProvider);
		} catch (Exception e) {
			Util.logGetNpcByIdError("getMilkProvider()", milkProvider);
			return null;
		}
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
	
}
