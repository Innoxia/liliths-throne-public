package com.lilithsthrone.game.sex;

import java.io.Serializable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.53
 * @version 0.2.8
 * @author Innoxia
 */
public class SexType implements Serializable, XMLSaving {
	
	private static final long serialVersionUID = 1L;
	
	private SexParticipantType asParticipant;
	private SexAreaInterface performingSexArea;
	private SexAreaInterface targetedSexArea;

	public SexType(SexParticipantType asParticipant, SexAreaInterface performingSexArea, SexAreaInterface targetedSexArea) {
		this.asParticipant = asParticipant;
		this.performingSexArea = performingSexArea;
		this.targetedSexArea = targetedSexArea;
	}
	
	@Override
	public boolean equals (Object o) {
		if(o instanceof SexType){
			if(((SexType)o).getAsParticipant() == getAsParticipant()
				&& ((SexType)o).getPerformingSexArea() == getPerformingSexArea()
				&& ((SexType)o).getTargetedSexArea() == getTargetedSexArea()){
					return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + getPerformingSexArea().hashCode();
		result = 31 * result + getTargetedSexArea().hashCode();
		return result;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element effect = doc.createElement("sexType");
		parentElement.appendChild(effect);
		
		CharacterUtils.addAttribute(doc, effect, "SexParticipantType", asParticipant.toString());
		CharacterUtils.addAttribute(doc, effect, "penetrationType", performingSexArea.toString());
		CharacterUtils.addAttribute(doc, effect, "orificeType", targetedSexArea.toString());
		
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

	public SexAreaInterface getPerformingSexArea() {
		return performingSexArea;
	}

	public SexAreaInterface getTargetedSexArea() {
		return targetedSexArea;
	}
}
