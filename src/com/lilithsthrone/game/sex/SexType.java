package com.lilithsthrone.game.sex;

import java.io.Serializable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.53
 * @version 0.2.5
 * @author Innoxia
 */
public class SexType implements Serializable, XMLSaving {
	
	private static final long serialVersionUID = 1L;
	
	private SexParticipantType asParticipant;
	private SexAreaPenetration penetrationType;
	private SexAreaOrifice orificeType;

	public SexType(SexParticipantType asParticipant, SexAreaPenetration penetrationType, SexAreaOrifice orificeType) {
		this.asParticipant = asParticipant;
		this.penetrationType=penetrationType;
		this.orificeType = orificeType;
	}
	
	@Override
	public boolean equals (Object o) {
		if(o instanceof SexType){
			if(((SexType)o).getAsParticipant().equals(getAsParticipant())
				&& ((SexType)o).getPenetrationType().equals(getPenetrationType())
				&& ((SexType)o).getOrificeType().equals(getOrificeType())){
					return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + getPenetrationType().hashCode();
		result = 31 * result + getOrificeType().hashCode();
		return result;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element effect = doc.createElement("sexType");
		parentElement.appendChild(effect);

		CharacterUtils.addAttribute(doc, effect, "SexParticipantType", asParticipant.toString());
		CharacterUtils.addAttribute(doc, effect, "penetrationType", penetrationType.toString());
		CharacterUtils.addAttribute(doc, effect, "orificeType", orificeType.toString());
		
		return effect;
	}
	
	public static SexType loadFromXML(Element parentElement, Document doc) {
		return new SexType(
				SexParticipantType.valueOf(parentElement.getAttribute("SexParticipantType")),
				SexAreaPenetration.valueOf(parentElement.getAttribute("penetrationType")),
				SexAreaOrifice.valueOf(parentElement.getAttribute("orificeType")));
	}
	
	public String getName() {
		return "";
	}

	public SexParticipantType getAsParticipant() {
		return asParticipant;
	}

	public SexAreaPenetration getPenetrationType() {
		return penetrationType;
	}

	public SexAreaOrifice getOrificeType() {
		return orificeType;
	}
}
