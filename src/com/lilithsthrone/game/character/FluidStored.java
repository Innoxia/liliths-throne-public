package com.lilithsthrone.game.character;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.body.FluidGirlCum;
import com.lilithsthrone.game.character.body.FluidInterface;
import com.lilithsthrone.game.character.body.FluidMilk;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.2.7
 * @version 0.3.1
 * @author Innoxia
 */
public class FluidStored implements XMLSaving {
	
	private String charactersFluidID;
	private AbstractSubspecies cumSubspecies; // used for calculating pregnancy.
	private AbstractSubspecies cumHalfDemonSubspecies; // used for calculating pregnancy.
	private float virility;
	private boolean bestial;
	private FluidCum cum;
	private FluidMilk milk;
	private FluidGirlCum girlCum;
	private float millilitres;
	
	public FluidStored(GameCharacter character, FluidCum cum, float millilitres) {
		if(character!=null) {
			this.charactersFluidID = character.getId();
			this.cumSubspecies = character.getSubspecies();
			this.cumHalfDemonSubspecies = character.getHalfDemonSubspecies();
			this.virility = character.getAttributeValue(Attribute.VIRILITY);
			this.bestial = cum.isBestial(character);
			
		} else {
			this.charactersFluidID = "";
			this.cumSubspecies = null;
			this.cumHalfDemonSubspecies = null;
			this.virility = 25;
			this.bestial = false;
		}
		
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
	
	public FluidStored(String charactersFluidID, AbstractSubspecies cumSubspecies, AbstractSubspecies cumHalfDemonSubspecies, FluidCum cum, float millilitres) {
		this.charactersFluidID = charactersFluidID;
		
		this.cumSubspecies = cumSubspecies;
		this.cumHalfDemonSubspecies = cumHalfDemonSubspecies;
		try {
			GameCharacter owner = charactersFluidID==null||charactersFluidID.isEmpty()?null:Main.game.getNPCById(charactersFluidID);
			this.bestial = cum.isBestial(owner);
			this.virility = owner==null?25:owner.getAttributeValue(Attribute.VIRILITY);
		} catch (Exception e) {
			this.bestial = false;
		}
		
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

	public FluidStored(String charactersFluidID, FluidMilk milk, float millilitres) {
		this.charactersFluidID = charactersFluidID;

		this.cumSubspecies = null;
		this.cumHalfDemonSubspecies = null;
		this.virility = 0;
		try {
			GameCharacter owner = charactersFluidID==null||charactersFluidID.isEmpty()?null:Main.game.getNPCById(charactersFluidID);
			this.bestial = milk.isBestial(owner);
		} catch (Exception e) {
			this.bestial = false;
		}
		
		this.milk = new FluidMilk(milk.getType(), milk.isCrotchMilk());
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
	
	public FluidStored(String charactersFluidID, FluidGirlCum girlCum, float millilitres) {
		this.charactersFluidID = charactersFluidID;

		this.cumSubspecies = null;
		this.cumHalfDemonSubspecies = null;
		this.virility = 0;
		try {
			GameCharacter owner = charactersFluidID==null||charactersFluidID.isEmpty()?null:Main.game.getNPCById(charactersFluidID);
			this.bestial = girlCum.isBestial(owner);
		} catch (Exception e) {
			this.bestial = false;
		}
		
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
	public boolean equals(Object o) {
		// Does not take into account quantity on purpose.
		if(o instanceof FluidStored){
			if(((FluidStored)o).getFluid().equals(this.getFluid())
					&& ((FluidStored)o).getCharactersFluidID().equals(this.getCharactersFluidID())
					&& ((FluidStored)o).isBestial() == this.isBestial()
					&& ((FluidStored)o).getCumSubspecies()==this.getCumSubspecies()
					&& ((FluidStored)o).getCumHalfDemonSubspecies()==this.getCumHalfDemonSubspecies()
					&& ((FluidStored)o).getVirility() == this.getVirility()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		// Does not take into account quantity on purpose.
		int result = 17;
		result = 31 * result + this.getFluid().hashCode();
		result = 31 * result + this.getCharactersFluidID().hashCode();
		result = 31 * result + (this.isBestial() ? 1 : 0);
		if(this.getCumSubspecies()!=null) {
			result = 31 * result + this.getCumSubspecies().hashCode();
		}
		if(this.getCumHalfDemonSubspecies()!=null) {
			result = 31 * result + this.getCumHalfDemonSubspecies().hashCode();
		}
		result = 31 * result + Float.floatToIntBits(this.getVirility());
		return result;
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		// Core:
		Element fluidStoredElement = doc.createElement("fluidStored");
		parentElement.appendChild(fluidStoredElement);
		XMLUtil.addAttribute(doc, fluidStoredElement, "charactersFluidID", charactersFluidID);
		XMLUtil.addAttribute(doc, fluidStoredElement, "bestial", String.valueOf(bestial));
		XMLUtil.addAttribute(doc, fluidStoredElement, "virility", String.valueOf(virility));
		XMLUtil.addAttribute(doc, fluidStoredElement, "millilitres", String.valueOf(millilitres));
		
		if(isCum()) {
			XMLUtil.addAttribute(doc, fluidStoredElement, "cumSubspecies", Subspecies.getIdFromSubspecies(cumSubspecies));
			if(cumHalfDemonSubspecies!=null) {
				XMLUtil.addAttribute(doc, fluidStoredElement, "cumHalfDemonSubspecies", Subspecies.getIdFromSubspecies(cumHalfDemonSubspecies));
			}
			cum.saveAsXML(fluidStoredElement, doc);
		}
		if(isMilk()) {
			milk.saveAsXML("milk", fluidStoredElement, doc);
		}
		if(isGirlCum()) {
			girlCum.saveAsXML(fluidStoredElement, doc);
		}
		
		return fluidStoredElement;
	}
	
	public static FluidStored loadFromXML(StringBuilder log, Element parentElement, Document doc) {
		String ID = parentElement.getAttribute("charactersFluidID");
		
		float millimetres = Float.parseFloat(parentElement.getAttribute("millilitres"));
		
		boolean bestial = false;
		float virility = 25;
		try {
			bestial = Boolean.parseBoolean(parentElement.getAttribute("bestial"));
			virility = Float.parseFloat(parentElement.getAttribute("virility"));
		} catch(Exception ex) {
		}
		
		if(parentElement.getElementsByTagName("milk").item(0)!=null) {
			FluidStored fluid = new FluidStored(ID, FluidMilk.loadFromXML("milk", parentElement, doc), millimetres);
			fluid.bestial=bestial;
			fluid.virility=0;
			return fluid;
		}
		
		if(parentElement.getElementsByTagName("cum").item(0)!=null) {
			AbstractSubspecies subspecies = Subspecies.HUMAN;
			AbstractSubspecies halfDemonSubspecies = Subspecies.HUMAN;
			try {
				subspecies = Subspecies.getSubspeciesFromId(parentElement.getAttribute("cumSubspecies"));
				halfDemonSubspecies = Subspecies.getSubspeciesFromId(parentElement.getAttribute("cumHalfDemonSubspecies"));
			} catch(Exception ex) {
			}
			FluidStored fluid = new FluidStored(ID, subspecies, halfDemonSubspecies, FluidCum.loadFromXML(parentElement, doc), millimetres);
			fluid.bestial=bestial;
			fluid.virility=virility;
			return fluid;
		}

		FluidStored fluid = new FluidStored(ID, FluidGirlCum.loadFromXML(parentElement, doc), millimetres);
		fluid.bestial=bestial;
		fluid.virility=0;
		return fluid;
	}
	
	
	public String getCharactersFluidID() {
		return charactersFluidID;
	}
	
	/**
	 * @return The character whose fluid this is.
	 * @throws Exception A NullPointerException if the character does not exist.
	 */
	public GameCharacter getFluidCharacter() throws Exception {
		if(charactersFluidID.equals(Main.game.getPlayer().getId())) {
			return Main.game.getPlayer();
		}
		return Main.game.getNPCById(charactersFluidID);
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

	public AbstractSubspecies getCumSubspecies() {
		return cumSubspecies;
	}

	public AbstractSubspecies getCumHalfDemonSubspecies() {
		return cumHalfDemonSubspecies;
	}
	
	public boolean isBestial() {
		return bestial;
	}

	public float getVirility() {
		return virility;
	}

	public float getMillilitres() {
		return millilitres;
	}
	
	public void setMillilitres(float millilitres) {
		this.millilitres = Math.max(0, millilitres);
	}
	
	public void incrementMillilitres(float increment) {
		setMillilitres(this.millilitres + increment);
	}
	
}
