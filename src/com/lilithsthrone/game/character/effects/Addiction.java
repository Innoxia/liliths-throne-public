package com.lilithsthrone.game.character.effects;

import java.util.HashSet;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFluidType;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.2.0
 * @version 0.3.8.2
 * @author Innoxia
 */
public class Addiction implements XMLSaving {

	private AbstractFluidType fluid;
	private long lastTimeSatisfied;
	private Set<String> providerIDs;
	
	public Addiction(AbstractFluidType fluid, long lastTimeSatisfied) {
		this.fluid = fluid;
		this.lastTimeSatisfied = lastTimeSatisfied;
		this.providerIDs = new HashSet<>();
	}
	
	public Addiction(AbstractFluidType fluid, long lastTimeSatisfied, String providerID) {
		this.fluid = fluid;
		this.lastTimeSatisfied = lastTimeSatisfied;
		this.providerIDs = new HashSet<>();
		this.providerIDs.add(providerID);
	}
	
	public Addiction(AbstractFluidType fluid, long lastTimeSatisfied, Set<String> providerIDs) {
		this.fluid = fluid;
		this.lastTimeSatisfied = lastTimeSatisfied;
		this.providerIDs = providerIDs;
	}

	@Override
	public boolean equals(Object o) {
		if(super.equals(o)) {
			return (o instanceof Addiction)
					&& ((Addiction)o).getFluid().equals(this.getFluid())
					&& ((Addiction)o).getLastTimeSatisfied() == this.getLastTimeSatisfied()
					&& ((Addiction)o).getProviderIDs().equals(this.getProviderIDs());
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

		XMLUtil.addAttribute(doc, element, "fluid", FluidType.getIdFromFluidType(this.getFluid()));
		XMLUtil.addAttribute(doc, element, "lastTimeSatisfied", String.valueOf(this.getLastTimeSatisfied()));
		
		Element innerElement = doc.createElement("providerIDs");
		element.appendChild(innerElement);
		for(String id : this.getProviderIDs()) {
			Element idElement = doc.createElement("id");
			innerElement.appendChild(idElement);
			XMLUtil.addAttribute(doc, idElement, "value", id);
		}
		
		return element;
	}

	public static Addiction loadFromXML(StringBuilder log, Element parentElement, Document doc) {
		Set<String> IDs = new HashSet<>();
		NodeList idList = ((Element)parentElement.getElementsByTagName("providerIDs").item(0)).getElementsByTagName("id");
		for(int i = 0; i < idList.getLength(); i++){
			IDs.add(((Element)idList.item(i)).getAttribute("value"));
		}
		
		return new Addiction(FluidType.getFluidTypeFromId(parentElement.getAttribute("fluid")),
				Long.valueOf(parentElement.getAttribute("lastTimeSatisfied")),
				IDs);
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
}
