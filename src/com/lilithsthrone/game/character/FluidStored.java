package com.lilithsthrone.game.character;

import java.util.Objects;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.body.FluidGirlCum;
import com.lilithsthrone.game.character.body.FluidInterface;
import com.lilithsthrone.game.character.body.FluidMilk;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RaceStage;
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
//	private AbstractSubspecies cumSubspecies; // used for calculating pregnancy.
//	private AbstractSubspecies cumHalfDemonSubspecies; // used for calculating pregnancy.
	private Body body; // Body has to be stored as otherwise the character who provided the cum could be transformed, and their new body would no longer reflect the genetics of this FluidStored
	private boolean cumVirile;
	private float virility;
	private boolean feral;
	private FluidCum cum;
	private FluidMilk milk;
	private FluidGirlCum girlCum;
	private float millilitres;
	
	public FluidStored(GameCharacter character, FluidCum cum, float millilitres) {
		if(character!=null) {
			this.charactersFluidID = character.getId();
			this.body = new Body(character.getBody()); //TODO
			this.cumVirile = character.isVirile(Attribute.VIRILITY);
			this.virility = character.getAttributeValue(Attribute.VIRILITY);
			this.feral = cum.isFeral(character);
			
		} else {
			this.charactersFluidID = "";
			this.body = null;
			this.cumVirile = true;
			this.virility = 25;
			this.feral = false;
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
	
	public FluidStored(String charactersFluidID, Body body, FluidCum cum, float millilitres) {
		this.charactersFluidID = charactersFluidID;
		
		this.body = body;
		try {
			GameCharacter owner = charactersFluidID==null||charactersFluidID.isEmpty()?null:Main.game.getNPCById(charactersFluidID);
			this.feral = cum.isFeral(owner);
			this.cumVirile = owner==null?true:owner.isVirile(Attribute.VIRILITY);
			this.virility = owner==null?25:owner.getAttributeValue(Attribute.VIRILITY);
		} catch (Exception e) {
			this.feral = false;
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

		this.body = null;
		this.cumVirile = false;
		this.virility = 0;
		try {
			GameCharacter owner = charactersFluidID==null||charactersFluidID.isEmpty()?null:Main.game.getNPCById(charactersFluidID);
			this.feral = milk.isFeral(owner);
		} catch (Exception e) {
			this.feral = false;
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

		this.body = null;
		this.cumVirile = false;
		this.virility = 0;
		try {
			GameCharacter owner = charactersFluidID==null||charactersFluidID.isEmpty()?null:Main.game.getNPCById(charactersFluidID);
			this.feral = girlCum.isFeral(owner);
		} catch (Exception e) {
			this.feral = false;
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
					&& ((FluidStored)o).isFeral() == this.isFeral()
					// For the purposes of FluidStored, it's good enough to make a very basic equality check for Body:
					&& (this.getBody()!=null && this.body!=null
						?Objects.equals(((FluidStored)o).getBody().getSubspecies(), this.body.getSubspecies())
								&& Objects.equals(((FluidStored)o).getBody().getGender(), this.body.getGender())
						:this.getBody()==null && this.body==null)
					&& ((FluidStored)o).isCumVirile() == this.isCumVirile()
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
		result = 31 * result + (this.isFeral() ? 1 : 0);
		if(this.getBody()!=null) {
			result = 31 * result + this.getBody().getSubspecies().hashCode();
			result = 31 * result + this.getBody().getGender().hashCode();
		}
		result = 31 * result + (this.isCumVirile() ? 1 : 0);
		result = 31 * result + Float.floatToIntBits(this.getVirility());
		return result;
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		// Core:
		Element fluidStoredElement = doc.createElement("fluidStored");
		parentElement.appendChild(fluidStoredElement);
		XMLUtil.addAttribute(doc, fluidStoredElement, "charactersFluidID", charactersFluidID);
		XMLUtil.addAttribute(doc, fluidStoredElement, "bestial", String.valueOf(feral));
		XMLUtil.addAttribute(doc, fluidStoredElement, "cumVirile", String.valueOf(cumVirile));
		XMLUtil.addAttribute(doc, fluidStoredElement, "virility", String.valueOf(virility));
		XMLUtil.addAttribute(doc, fluidStoredElement, "millilitres", String.valueOf(millilitres));
		
		if(isCum()) {
//			XMLUtil.addAttribute(doc, fluidStoredElement, "cumSubspecies", Subspecies.getIdFromSubspecies(cumSubspecies));
//			if(cumHalfDemonSubspecies!=null) {
//				XMLUtil.addAttribute(doc, fluidStoredElement, "cumHalfDemonSubspecies", Subspecies.getIdFromSubspecies(cumHalfDemonSubspecies));
//			}
			//TODO need custom names for cum and girlcum
			Element bodyElement = doc.createElement("body");
			fluidStoredElement.appendChild(bodyElement);
			body.saveAsXML(bodyElement, doc);
			cum.saveAsXML("fluidCum", fluidStoredElement, doc);
		}
		if(isMilk()) {
			milk.saveAsXML("fluidMilk", fluidStoredElement, doc);
		}
		if(isGirlCum()) {
			girlCum.saveAsXML("fluidGirlCum", fluidStoredElement, doc);
		}
		
		return fluidStoredElement;
	}
	
	public static FluidStored loadFromXML(StringBuilder log, Element parentElement, Document doc) {
		String ID = parentElement.getAttribute("charactersFluidID");
		
		float millimetres = Float.parseFloat(parentElement.getAttribute("millilitres"));
		FluidStored fluid;
		boolean feral = false;
		boolean cumVirile = true;
		float virility = 25;
		try {
			feral = Boolean.parseBoolean(parentElement.getAttribute("bestial"));
			virility = Float.parseFloat(parentElement.getAttribute("virility"));
		} catch(Exception ex) {
		}
		if(!parentElement.getAttribute("cumVirile").isEmpty()) {
			cumVirile = Boolean.parseBoolean(parentElement.getAttribute("cumVirile"));
		}
		
		if(parentElement.getElementsByTagName("body").item(0)!=null) {
			// Cum:
			if(parentElement.getElementsByTagName("fluidCum").item(0)!=null) {
				fluid = new FluidStored(ID,
						Body.loadFromXML(log, (Element) parentElement.getElementsByTagName("body").item(0), doc),
						FluidCum.loadFromXML("fluidCum", parentElement, doc), millimetres);
				fluid.feral=feral;
				fluid.cumVirile = cumVirile;
				fluid.virility=virility;
				return fluid;
			}
			
		} else { // Old version support:
			// Milk:
			if(parentElement.getElementsByTagName("milk").item(0)!=null) {
				fluid = new FluidStored(ID, FluidMilk.loadFromXML("milk", parentElement, doc), millimetres);
				fluid.feral=feral;
				fluid.cumVirile = false;
				fluid.virility=0;
				return fluid;
				
			} else if(parentElement.getElementsByTagName("fluidMilk").item(0)!=null) {
				fluid = new FluidStored(ID, FluidMilk.loadFromXML("fluidMilk", parentElement, doc), millimetres);
				fluid.feral=feral;
				fluid.cumVirile = false;
				fluid.virility=0;
				return fluid;
			}

			// Girlcum:
			if(parentElement.getElementsByTagName("girlcum").item(0)!=null) {
				fluid = new FluidStored(ID, FluidGirlCum.loadFromXML("girlcum", parentElement, doc), millimetres);
				fluid.feral=feral;
				fluid.cumVirile = false;
				fluid.virility=0;
				return fluid;
				
			} else if(parentElement.getElementsByTagName("fluidGirlCum").item(0)!=null) {
				fluid = new FluidStored(ID, FluidGirlCum.loadFromXML("fluidGirlCum", parentElement, doc), millimetres);
				fluid.feral=feral;
				fluid.cumVirile = false;
				fluid.virility=0;
				return fluid;
			}
			
			// Cum:
			if(parentElement.getElementsByTagName("cum").item(0)!=null) {
				AbstractSubspecies subspecies = Subspecies.HUMAN;
				AbstractSubspecies halfDemonSubspecies = Subspecies.HUMAN;
				try {
					subspecies = Subspecies.getSubspeciesFromId(parentElement.getAttribute("cumSubspecies"));
					halfDemonSubspecies = Subspecies.getSubspeciesFromId(parentElement.getAttribute("cumHalfDemonSubspecies"));
				} catch(Exception ex) {
				}
				
				fluid = new FluidStored(ID,
						subspecies==Subspecies.HALF_DEMON
							?Main.game.getCharacterUtils().generateHalfDemonBody(null, Gender.M_P_MALE, halfDemonSubspecies, false)
							:Main.game.getCharacterUtils().generateBody(null, Gender.M_P_MALE, subspecies, RaceStage.GREATER),
						FluidCum.loadFromXML("cum", parentElement, doc), millimetres);
				
				fluid.feral=feral;
				fluid.cumVirile = cumVirile;
				fluid.virility=virility;
				return fluid;
			}
		}
		
		
		
		
		System.err.println("WARNING: FluidStored failed to load!");
		new Exception().printStackTrace();
		
		return null;
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

	/**
	 * @return Body associated with this fluid for the purposes of impregnation.
	 */
	public Body getBody() {
		return body;
	}
	
	public boolean isFeral() {
		return feral;
	}
	
	public boolean isCumVirile() {
		return cumVirile;
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
