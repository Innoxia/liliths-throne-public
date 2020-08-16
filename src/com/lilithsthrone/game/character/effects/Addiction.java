package com.lilithsthrone.game.character.effects;

import java.util.HashSet;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFluidType;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.utils.XMLSaving;

import static java.lang.Boolean.valueOf;

/**
 * @since 0.2.0
 * @version 0.3.8.2
 * @author Innoxia
 */
public class Addiction implements XMLSaving {

	private AbstractFluidType fluid;
	private long lastTimeSatisfied;
	private Set<String> providerIDs;
	private boolean isBestial;

	public Addiction(AbstractFluidType fluid, long lastTimeSatisfied) {
		this.fluid = fluid;
		this.lastTimeSatisfied = lastTimeSatisfied;
		this.providerIDs = new HashSet<>();
	}
	
	public Addiction(AbstractFluidType fluid, long lastTimeSatisfied, String providerID, boolean isBestial) {
		this.fluid = fluid;
		this.lastTimeSatisfied = lastTimeSatisfied;
		this.providerIDs = new HashSet<>();
		this.providerIDs.add(providerID);
		this.isBestial = isBestial;
	}
	
	public Addiction(AbstractFluidType fluid, long lastTimeSatisfied, Set<String> providerIDs, boolean isBestial) {
		this.fluid = fluid;
		this.lastTimeSatisfied = lastTimeSatisfied;
		this.providerIDs = providerIDs;
		this.isBestial = isBestial;
	}

	@Override
	public boolean equals(Object o) {
		if(super.equals(o)) {
			return (o instanceof Addiction)
					&& ((Addiction)o).getFluid().equals(this.getFluid())
					&& ((Addiction)o).getLastTimeSatisfied() == this.getLastTimeSatisfied()
					&& ((Addiction)o).getProviderIDs().equals(this.getProviderIDs())
					&& ((Addiction)o).isBestial() == (this.isBestial());
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.getFluid().hashCode();
		result = 31 * result + (int)this.getLastTimeSatisfied();
		result = 31 * result + this.getProviderIDs().hashCode();
		return result;
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("addiction");
		parentElement.appendChild(element);

		CharacterUtils.addAttribute(doc, element, "fluid", FluidType.getIdFromFluidType(this.getFluid()));
		CharacterUtils.addAttribute(doc, element, "lastTimeSatisfied", String.valueOf(this.getLastTimeSatisfied()));

		Element innerElement = doc.createElement("providerIDs");
		element.appendChild(innerElement);
		for(String id : this.getProviderIDs()) {
			Element idElement = doc.createElement("id");
			innerElement.appendChild(idElement);
			CharacterUtils.addAttribute(doc, idElement, "value", id);
		}
		CharacterUtils.addAttribute(doc, element, "bestial", String.valueOf(this.isBestial()));

		return element;
	}

	public static Addiction loadFromXML(StringBuilder log, Element parentElement, Document doc) {
		Set<String> IDs = new HashSet<>();
		NodeList idList = ((Element)parentElement.getElementsByTagName("providerIDs").item(0)).getElementsByTagName("id");
		for(int i = 0; i < idList.getLength(); i++){
			IDs.add(((Element)idList.item(i)).getAttribute("value"));
		}
		
		return new Addiction(FluidType.getFluidTypeFromId(parentElement.getAttribute("fluid")),
				Long.parseLong(parentElement.getAttribute("lastTimeSatisfied")),
				IDs, Boolean.parseBoolean(parentElement.getAttribute("bestial")));
	}
	
	public AbstractFluidType getFluid() {
		return fluid;
	}
	
	public void setFluid(AbstractFluidType fluid) {
		this.fluid = fluid;
	}
	
	public long getLastTimeSatisfied() {
		return lastTimeSatisfied;
	}
	
	public void setLastTimeSatisfied(long lastTimeSatisfied) {
		this.lastTimeSatisfied = lastTimeSatisfied;
	}
	
	public Set<String> getProviderIDs() {
		return providerIDs;
	}
	
	public void addProviderID(String providerID) {
		getProviderIDs().add(providerID);
	}

	public boolean isBestial() {
		return isBestial;
	}

	public void setBestial(boolean bestial) {
		isBestial = bestial;
	}
}
