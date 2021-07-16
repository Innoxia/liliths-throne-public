package com.lilithsthrone.game.character.body;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEarType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.0
 * @version 0.3.2
 * @author Innoxia
 */
public class Ear implements BodyPartInterface, XMLSaving {
	
	protected AbstractEarType type;
	protected boolean pierced;

	public Ear(AbstractEarType type, boolean pierced) {
		this.type = type;
		this.pierced = pierced;
	}
	
	public Ear(AbstractEarType type) {
		this.type = type;
		pierced = false;
	}

	@Override
	public AbstractEarType getType() {
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
	
	public String setType(GameCharacter owner, AbstractEarType type) {
		if(!Main.game.isStarted() || owner==null) {
			this.type = type;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type.equals(getType())) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.has] the [npc.ears] of [npc.a_earRace], so nothing happens...)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();

		sb.append("<p>");
		if(owner.isArmMovementHindered()) {
			sb.append("[npc.NamePos] [npc.ears] start to involuntarily twitch and itch, and [npc.she] [npc.verb(let)] out a gasp as [npc.she] [npc.verb(feel)] them start to transform. ");
		} else {
			sb.append("[npc.NamePos] [npc.ears] start to involuntarily twitch and itch, and, letting out a gasp, [npc.she] [npc.verb(reach)] up to rub at them as [npc.she] [npc.verb(feel)] them start to transform. ");
		}
		
		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		this.type = type;

		sb.append(type.getTransformationDescription(owner));

		sb.append("</p>");
		
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public boolean isPierced() {
		return pierced;
	}
	
	public String setPierced(GameCharacter owner, boolean pierced) {
		if(this.pierced == pierced) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		this.pierced = pierced;
		
		if(pierced) {
			if(owner.isPlayer()) {
				return "<p>Your [pc.ears] are now [style.boldGrow(pierced)]!</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.NamePos] [npc.ears] are now [style.boldGrow(pierced)]!</p>");
			}
			
		} else {
			AbstractClothing c = owner.getClothingInSlot(InventorySlot.PIERCING_EAR);
			String piercingUnequip = "";
			if(c!=null) {
				owner.forceUnequipClothingIntoVoid(owner, c);
				piercingUnequip = owner.addClothing(c, false);
			}
			
			if(owner.isPlayer()) {
				return "<p>"
							+ "Your [pc.ears] are [style.boldShrink(no longer pierced)]!"
						+ "</p>"
						+piercingUnequip;
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.NamePos] [npc.ears] are [style.boldShrink(no longer pierced)]!"
						+ "</p>"
						+piercingUnequip);
			}
		}
		
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Ear.class) && getType().getRace().isFeralPartsAvailable());
	}
	
	@Override
	public boolean saveAsXML(Element parentElement) {
		Element earElement = parentElement.addElement("ear");
		earElement.addAttribute("type", String.valueOf(type));
		earElement.addAttribute("pierced", String.valueOf(pierced));
		return true;
	}
	
	public static Ear loadFromXML(Element parentElement) {
		try {
			Element earElement = parentElement.getMandatoryFirstOf("ear");
			return new Ear(EarType.getEarTypeFromId(earElement.getAttribute("type")),
					Boolean.parseBoolean(earElement.getAttribute("pierced")));
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
