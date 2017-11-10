package com.lilithsthrone.game.character;

import java.io.Serializable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.66
 * @version 0.1.89
 * @author Innoxia
 */
public class PregnancyPossibility implements Serializable, XMLSaving {

	private static final long serialVersionUID = 1L;
	
	private String motherId, fatherId;
	private float probability;
	
	public PregnancyPossibility(String motherId, String fatherId, float probability) {
		this.motherId = motherId;
		this.fatherId = fatherId;
		this.probability = probability;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("pregnancyPossibility");
		parentElement.appendChild(element);
		
		CharacterUtils.addAttribute(doc, element, "motherId", this.getMotherId());
		CharacterUtils.addAttribute(doc, element, "fatherId", this.getFatherId());
		CharacterUtils.addAttribute(doc, element, "probability", String.valueOf(this.getProbability()));
		
		return element;
	}
	
	public static PregnancyPossibility loadFromXML(Element parentElement, Document doc) {
		return new PregnancyPossibility(
				parentElement.getAttribute("motherId"),
				parentElement.getAttribute("fatherId"),
				Float.valueOf(parentElement.getAttribute("probability")));
	}
	
	public String getMotherId() {
		return motherId;
	}

	public String getFatherId() {
		return fatherId;
	}

	public GameCharacter getMother() {
		return Main.game.getNPCById(motherId);
	}

	public GameCharacter getFather() {
		return Main.game.getNPCById(fatherId);
	}

	public float getProbability() {
		return probability;
	}
	
}
