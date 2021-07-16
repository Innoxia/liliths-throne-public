package com.lilithsthrone.game.character.body;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTorsoType;
import com.lilithsthrone.game.character.body.types.TorsoType;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.0
 * @version 0.3.8.9
 * @author Innoxia
 */
public class Torso implements BodyPartInterface, XMLSaving {
	
	protected AbstractTorsoType type;

	public Torso(AbstractTorsoType type) {
		this.type = type;
	}

	@Override
	public AbstractTorsoType getType() {
		return type;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return type.getDeterminer(gc);
	}

	@Override
	public String getName(GameCharacter gc) {
		return type.getName(gc);
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		return type.getNameSingular(gc);
	}

	@Override
	public String getNamePlural(GameCharacter gc) {
		return type.getNamePlural(gc);
	}
	
	@Override
	public String getDescriptor(GameCharacter gc) {
		return type.getDescriptor(gc);
	}

	public String setType(GameCharacter owner, AbstractTorsoType type) {
		if(!Main.game.isStarted() || owner==null) {
			this.type = type;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if(type == getType()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.has] the [npc.skin] of [npc.a_skinRace], so nothing happens...)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(
				"<p>"
					+ "[npc.NamePos] entire torso starts to itch and grow hot, and [npc.she] [npc.verb(start)] frantically scratching all over as [npc.her] [npc.skin] starts to transform. ");
		

		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		this.type = type;
		
		sb.append(this.type.getTransformationDescription(owner));
		
		sb.append("</p>");
				
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
				+ owner.postTransformationCalculation()
				+ "</p>";
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Torso.class) && getType().getRace().isFeralPartsAvailable());
	}
	
	@Override
	public boolean saveAsXML(Element parentElement) {
		Element torsoElement = parentElement.addElement("torso");
		torsoElement.addAttribute("type", TorsoType.getIdFromTorsoType(type));
		return true;
	}
	
	public static Torso loadFromXML(Element parentElement) {
		try {
			Element torsoElement = parentElement.getMandatoryFirstOf("tongue");
			return new Torso(TorsoType.getTorsoTypeFromId(torsoElement.getAttribute("type")));
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
