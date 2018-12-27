package com.lilithsthrone.game.character;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.body.FluidGirlCum;
import com.lilithsthrone.game.character.body.FluidInterface;
import com.lilithsthrone.game.character.body.FluidMilk;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.2.7
 * @version 0.2.11
 * @author Innoxia
 */
public class FluidStored implements XMLSaving {
	
	private String charactersFluidID;
	private Subspecies cumSubspecies; // used for calculating pregnancy.
	private FluidCum cum;
	private FluidMilk milk;
	private FluidGirlCum girlCum;
	private int millilitres;
	
	public FluidStored(GameCharacter character, FluidCum cum, int millilitres) {
		this.charactersFluidID = character.getId();
		
		this.cumSubspecies = character.getSubspecies();
		this.cum = new FluidCum(cum.getType());
		this.cum.clearFluidModifiers();
		
		this.cum.setFlavour(null, cum.getFlavour());
		for(FluidModifier fm : cum.getFluidModifiers()) {
			this.cum.addFluidModifier(null, fm);
		}
		for(ItemEffect ie : cum.getTransformativeEffects()) {
			this.cum.addTransformativeEffect(ie);
		}
		
		this.millilitres = millilitres;
	}
	
	public FluidStored(String charactersFluidID, Subspecies cumSubspecies, FluidCum cum, int millilitres) {
		this.charactersFluidID = charactersFluidID;
		
		this.cumSubspecies = cumSubspecies;
		this.cum = new FluidCum(cum.getType());
		this.cum.clearFluidModifiers();
		
		this.cum.setFlavour(null, cum.getFlavour());
		for(FluidModifier fm : cum.getFluidModifiers()) {
			this.cum.addFluidModifier(null, fm);
		}
		for(ItemEffect ie : cum.getTransformativeEffects()) {
			this.cum.addTransformativeEffect(ie);
		}
		
		this.millilitres = millilitres;
	}

	public FluidStored(String charactersFluidID, FluidMilk milk, int millilitres) {
		this.charactersFluidID = charactersFluidID;
		
		this.milk = new FluidMilk(milk.getType());
		this.milk.clearFluidModifiers();
		
		this.milk.setFlavour(null, milk.getFlavour());
		for(FluidModifier fm : milk.getFluidModifiers()) {
			this.milk.addFluidModifier(null, fm);
		}
		for(ItemEffect ie : milk.getTransformativeEffects()) {
			this.milk.addTransformativeEffect(ie);
		}
		
		this.millilitres = millilitres;
	}
	
	public FluidStored(String charactersFluidID, FluidGirlCum girlCum, int millilitres) {
		this.charactersFluidID = charactersFluidID;
		
		this.girlCum = new FluidGirlCum(girlCum.getType());
		this.girlCum.clearFluidModifiers();
		
		this.girlCum.setFlavour(null, girlCum.getFlavour());
		for(FluidModifier fm : girlCum.getFluidModifiers()) {
			this.girlCum.addFluidModifier(null, fm);
		}
		for(ItemEffect ie : girlCum.getTransformativeEffects()) {
			this.girlCum.addTransformativeEffect(ie);
		}
		
		this.millilitres = millilitres;
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		// Core:
		Element fluidStoredElement = doc.createElement("fluidStored");
		parentElement.appendChild(fluidStoredElement);
		CharacterUtils.addAttribute(doc, fluidStoredElement, "charactersFluidID", charactersFluidID);
		CharacterUtils.addAttribute(doc, fluidStoredElement, "millilitres", String.valueOf(millilitres));
		
		if(isCum()) {
			CharacterUtils.addAttribute(doc, fluidStoredElement, "cumSubspecies", cumSubspecies.toString());
			cum.saveAsXML(fluidStoredElement, doc);
		}
		if(isMilk()) {
			milk.saveAsXML(fluidStoredElement, doc);
		}
		if(isGirlCum()) {
			girlCum.saveAsXML(fluidStoredElement, doc);
		}
		
		return parentElement;
	}
	

	public static FluidStored loadFromXML(StringBuilder log, Element parentElement, Document doc) {
		String ID = parentElement.getAttribute("charactersFluidID");
		int millimetres = Integer.parseInt(parentElement.getAttribute("millilitres"));
		
		if(parentElement.getElementsByTagName("milk").item(0)!=null) {
			return new FluidStored(ID, FluidMilk.loadFromXML(parentElement, doc), millimetres);
		}
		
		if(parentElement.getElementsByTagName("cum").item(0)!=null) {
			Subspecies subspecies = Subspecies.HUMAN;
			try {
				subspecies = Subspecies.valueOf(parentElement.getAttribute("cumSubspecies"));
			} catch(Exception ex) {
			}
			return new FluidStored(ID, subspecies, FluidCum.loadFromXML(parentElement, doc), millimetres);
		}

		return new FluidStored(ID, FluidGirlCum.loadFromXML(parentElement, doc), millimetres);
		
	}
	
	
	public String getCharactersFluidID() {
		return charactersFluidID;
	}
	
	public GameCharacter getFluidCharacter() {
		if(charactersFluidID.equals(Main.game.getPlayer().getId())) {
			return Main.game.getPlayer();
		}
		try {
			return Main.game.getNPCById(charactersFluidID);
		} catch (Exception e) {
			Util.logGetNpcByIdError("getFluidCharacter()", charactersFluidID);
			return null;
		}
	}
	
	public boolean isCum() {
		return cum!=null;
	}

	public boolean isMilk() {
		return milk!=null;
	}

	public boolean isGirlCum() {
		return girlCum!=null;
	}
	
	public FluidInterface getFluid() {
		if(isCum()) {
			return cum;
		}
		if(isMilk()) {
			return milk;
		}
		return girlCum;
	}

	public Subspecies getCumSubspecies() {
		return cumSubspecies;
	}

	public int getMillilitres() {
		return millilitres;
	}
	
	public void setMillilitres(int millilitres) {
		this.millilitres = millilitres;
		if(this.millilitres<0) {
			this.millilitres = 0;
		}
	}
	
	public void incrementMillilitres(int increment) {
		setMillilitres(this.millilitres + increment);
	}
	
}
