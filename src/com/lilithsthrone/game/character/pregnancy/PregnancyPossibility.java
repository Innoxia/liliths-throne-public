package com.lilithsthrone.game.character.pregnancy;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.misc.GenericFemaleNPC;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.66
 * @version 0.3
 * @author Innoxia
 */
public class PregnancyPossibility implements XMLSaving {
	
	private String motherId;
	private String fatherId;
	private float probability;
	
	public PregnancyPossibility(String motherId, String fatherId, float probability) {
		this.motherId = motherId;
		this.fatherId = fatherId;
		this.probability = probability;
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof PregnancyPossibility)
				&& ((PregnancyPossibility)o).getMotherId().equals(motherId)
				&& ((PregnancyPossibility)o).getFatherId().equals(fatherId)
				&& ((PregnancyPossibility)o).getProbability() == probability;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + motherId.hashCode();
		result = 31 * result + fatherId.hashCode();
		result = 31 * result + (int)probability;
		return result;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("pregnancyPossibility");
		parentElement.appendChild(element);
		
		XMLUtil.addAttribute(doc, element, "motherId", this.getMotherId());
		XMLUtil.addAttribute(doc, element, "fatherId", this.getFatherId());
		XMLUtil.addAttribute(doc, element, "probability", String.valueOf(this.getProbability()));
		
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
		try {
			return Main.game.getNPCById(motherId);
		} catch (Exception e) {
			if(!motherId.equals("NOT_SET")) {
				Util.logGetNpcByIdError("PregnancyPossibility.getMother()", motherId);
			}
			return Main.game.getNpc(GenericFemaleNPC.class);
		}
	}

	public GameCharacter getFather() {
		try {
			return Main.game.getNPCById(fatherId);
		} catch (Exception e) {
			return null;
		}
	}

	public float getProbability() {
		return probability;
	}

	public void setProbability(float probability) {
		this.probability = probability;
	}
	
}
