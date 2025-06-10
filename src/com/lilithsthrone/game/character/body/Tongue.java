package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTongueType;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Tongue implements BodyPartInterface {

	
	protected AbstractTongueType type;
	protected Set<TongueModifier> tongueModifiers;
	protected int tongueLength;
	protected boolean pierced;

	public Tongue(AbstractTongueType type) {
		this.type = type;
		pierced = false;
		
		tongueLength = type.getDefaultLength();
		
		this.tongueModifiers = new HashSet<>(type.getDefaultRacialTongueModifiers());
	}

	public Tongue(Tongue tongueToCopy) {
		this.type = tongueToCopy.type;
		this.pierced = tongueToCopy.pierced;
		
		this.tongueLength = tongueToCopy.tongueLength;
		
		this.tongueModifiers = new HashSet<>(tongueToCopy.tongueModifiers);
	}
	
	@Override
	public AbstractTongueType getType() {
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
	
	public String getDescriptor(GameCharacter owner) {
		List<String> list = new ArrayList<>();
        
		for(TongueModifier tm : tongueModifiers) {
			list.add(tm.getName());
		}
		list.add(type.getDescriptor(owner));

		return Util.randomItemFrom(list);
	}
	
	/**
	 * Tongue type is set when FaceType changes.
	 */
	public void setType(AbstractTongueType type) {
		this.type = type;
		resetTongueModifiers();
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
				return "<p>Your [pc.tongue] is now [style.boldGrow(pierced)]!</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.NamePos] [npc.tongue] is now [style.boldGrow(pierced)]!</p>");
			}
			
		} else {
			AbstractClothing c = owner.getClothingInSlot(InventorySlot.PIERCING_TONGUE);
			String piercingUnequip = "";
			if(c!=null) {
				owner.forceUnequipClothingIntoVoid(owner, c);
				piercingUnequip = owner.addClothing(c, false);
			}
			
			if(owner.isPlayer()) {
				return "<p>"
							+ "Your [pc.tongue] is [style.boldShrink(no longer pierced)]!"
						+ "</p>"
						+piercingUnequip;
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.NamePos] [npc.tongue] is [style.boldShrink(no longer pierced)]!"
						+ "</p>"
						+piercingUnequip);
			}
		}
	}
	
	public TongueLength getTongueLength() {
		return TongueLength.getTongueLengthFromInt(tongueLength);
	}
	
	public int getTongueLengthValue() {
		return tongueLength;
	}

	public String setTongueLength(GameCharacter owner, int tongueLength) {
		int oldTongueLength = this.tongueLength;
		this.tongueLength = Math.max(0, Math.min(tongueLength, TongueLength.FOUR_ABSURDLY_LONG.getMaximumValue()));
		int sizeChange = this.tongueLength - oldTongueLength;
		
		if(sizeChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The length of your [pc.tongue] doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The length of [npc.namePos] [npc.tongue] doesn't change...)]</p>");
			}
		}
		
		if(sizeChange < 0) {
			if(owner.isPlayer()) {
				return "<p>A soothing coolness rises up into your [pc.tongue], causing you to let out a surprised gasp as you feel it [style.boldShrink(getting shorter)].<br/>"
						+ "You now have "+UtilText.generateSingularDeterminer(Util.intToString(this.tongueLength))+" [style.boldTfGeneric("
						+ Units.size(this.tongueLength, Units.UnitType.LONG_SINGULAR)+" [pc.tongue])]!</p>";
			} else {
				return UtilText.parse(owner, "<p>[npc.Name] lets out a little cry as [npc.she] feels a soothing coolness rise up into [npc.her] [npc.tongue], before it suddenly [style.boldShrink(gets shorter)].<br/>"
						+ "[npc.Name] now has "+UtilText.generateSingularDeterminer(Util.intToString(this.tongueLength))+" [style.boldTfGeneric("
						+ Units.size(this.tongueLength, Units.UnitType.LONG_SINGULAR)+" [npc.tongue])]!</p>");
			}
			
		} else {
			if(owner.isPlayer()) {
				return "<p>A pulsating warmth rises up into your [pc.tongue], causing you to let out a surprised gasp as you feel it [style.boldGrow(growing longer)].<br/>"
						+ "You now have "+UtilText.generateSingularDeterminer(Util.intToString(this.tongueLength))+" [style.boldTfGeneric("
						+ Units.size(this.tongueLength, Units.UnitType.LONG_SINGULAR)+" [pc.tongue])]!</p>";
			} else {
				return UtilText.parse(owner, "<p>[npc.Name] lets out a little cry as [npc.she] feels a pulsating warmth rise up into [npc.her] [npc.tongue], before it suddenly [style.boldGrow(grows longer)].<br/>"
						+ "[npc.Name] now has "+UtilText.generateSingularDeterminer(Util.intToString(this.tongueLength))+" [style.boldTfGeneric("
						+ Units.size(this.tongueLength, Units.UnitType.LONG_SINGULAR)+" [npc.tongue])]!</p>");
			}
		}
	}
	
	public boolean hasTongueModifier(TongueModifier modifier) {
		return tongueModifiers.contains(modifier);
	}

	public String addTongueModifier(GameCharacter owner, TongueModifier modifier) {
		if(hasTongueModifier(modifier)) {
			return owner==null ? "" : "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		tongueModifiers.add(modifier);
		
		if(owner==null) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<p>");
			switch(modifier) {
				case RIBBED:
					sb.append("[npc.Name] [npc.verb(feel)] an intense pressure building up at the back of [npc.her] throat, but before [npc.she] [npc.has] a chance to panic it suddenly fades away,"
									+ " leaving [npc.herHim] with a series of [style.boldGrow(hard, fleshy ribs)] lining [npc.her] [npc.tongue].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.tongue] is now ribbed!)]");
					break;
				case TENTACLED:
					sb.append("[npc.Name] [npc.verb(feel)] an intense pressure building up at the back of [npc.her] throat, and [npc.she] [npc.verb(let)] out an involuntary gasp as [npc.she] [npc.verb(feel)]"
									+ " [style.boldGrow(a series of little wriggling tentacles)] grow to cover [npc.her] [npc.tongue].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.tongue] is now covered with little tentacles, which wriggle with a mind of their own!)]");
					break;
				case BIFURCATED:
					sb.append("[npc.Name] [npc.verb(feel)] an intense pressure building up at the back of [npc.her] throat, and [npc.she] [npc.verb(let)] out an involuntary cry as [npc.she] [npc.verb(feel)]"
									+ " [style.boldGrow(the end of [npc.her] [npc.tongue] split in two)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.tongue] is now bifurcated, like a snake's!)]");
					break;
				case STRONG:
					sb.append("[npc.Name] [npc.verb(feel)] a steady pressure building up at the back of [npc.her] throat, but before [npc.she] can react, the sensation shoots into [npc.her] [npc.tongue], transforming it into"
									+ " [style.boldGrow(being particularly strong)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.tongue] is now very strong!)]");
					break;
				case FLAT:
					sb.append("[npc.Name] [npc.verb(feel)] a steady pressure building up at the back of [npc.her] throat, but before [npc.she] can react, the sensation shoots into [npc.her] [npc.tongue], transforming it into"
									+ " [style.boldGrow(being particularly flat)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.tongue] is now particularly flat!)]");
					break;
				case WIDE:
					sb.append("[npc.Name] [npc.verb(feel)] a steady pressure building up at the back of [npc.her] throat, but before [npc.she] can react, the sensation shoots into [npc.her] [npc.tongue], transforming it into"
									+ " [style.boldGrow(being particularly wide)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.tongue] is now particularly wide!)]");
					break;
				case TAPERED:
					return "<p>"
							+ "[npc.Name] [npc.verb(feel)] a constricting pressure on all sides of [npc.her] [npc.tongue], and [npc.she] [npc.verb(let)] out a gasp as [npc.she] [npc.verb(feel)]"
							+ " [style.boldGrow(it taper down towards the tip)].<br/>"
							+ "[style.boldSex([npc.NamePos] [npc.tongue] is now tapered!)]"
						+ "</p>";
			}
		sb.append("</p>");
		
		return UtilText.parse(owner, sb.toString());
	}

	public String removeTongueModifier(GameCharacter owner, TongueModifier modifier) {
		if(!hasTongueModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		tongueModifiers.remove(modifier);
		
		switch(modifier) {
			case RIBBED:
				return "<p>"
							+ "[npc.Name] [npc.verb(feel)] a soft coolness rising up into [npc.her] [npc.tongue], but it suddenly fades away before [npc.she] [npc.has] a chance to react,"
								+ " [style.boldShrink(removing)] [npc.her] hard, fleshy ribs in the process.<br/>"
							+ "[style.boldSex([npc.NamePos] [npc.tongue] is no longer ribbed!)]"
						+ "</p>";
			case TENTACLED:
				return "<p>"
							+ "[npc.Name] [npc.verb(feel)] a soft coolness rising up into [npc.her] [npc.tongue], and [npc.she] [npc.verb(let)] out a gasp as [npc.she] [npc.verb(feel)] [npc.her]"
								+ " [style.boldShrink(little wriggling tentacles disappear)].<br/>"
							+ "[style.boldSex([npc.NamePos] [npc.tongue] is no longer covered with little tentacles!)]"
						+ "</p>";
			case BIFURCATED:
				return "<p>"
							+ "[npc.Name] [npc.verb(feel)] a soft coolness rising up into [npc.her] [npc.tongue], and [npc.she] [npc.verb(let)] out a gasp as [npc.she] [npc.verb(feel)]"
							+ " [style.boldShrink(it fuse back into a single point)].<br/>"
							+ "[style.boldSex([npc.NamePos] [npc.tongue] is no longer bifurcated!)]"
						+ "</p>";
			case FLAT:
				return "<p>"
							+ "[npc.Name] [npc.verb(feel)] a pulsating pressure rising up into [npc.her] [npc.tongue], and [npc.she] [npc.verb(let)] out a gasp as [npc.she] [npc.verb(feel)]"
							+ " [style.boldShrink(it thicken up)].<br/>"
							+ "[style.boldSex([npc.NamePos] [npc.tongue] is no longer particularly flat!)]"
						+ "</p>";
			case STRONG:
				return "<p>"
						+ "[npc.Name] [npc.verb(feel)] a pulsating pressure rising up into [npc.her] [npc.tongue], and [npc.she] [npc.verb(let)] out a gasp as [npc.she] [npc.verb(feel)]"
						+ " [style.boldShrink(it soften and lose strength)].<br/>"
						+ "[style.boldSex([npc.NamePos] [npc.tongue] is no longer extra strong!)]"
					+ "</p>";
			case WIDE:
				return "<p>"
						+ "[npc.Name] [npc.verb(feel)] a pulsating pressure rising up into [npc.her] [npc.tongue], and [npc.she] [npc.verb(let)] out a gasp as [npc.she] [npc.verb(feel)]"
						+ " [style.boldShrink(it narrow down)].<br/>"
						+ "[style.boldSex([npc.NamePos] [npc.tongue] is no longer particularly wide!)]"
					+ "</p>";
			case TAPERED:
				return "<p>"
						+ "[npc.Name] [npc.verb(feel)] a pulsating pressure building up within [npc.her] [npc.tongue], and [npc.she] [npc.verb(let)] out a gasp as [npc.she] [npc.verb(feel)]"
						+ " [style.boldShrink(it fill out and lose its tapered point)].<br/>"
						+ "[style.boldSex([npc.NamePos] [npc.tongue] is no longer tapered!)]"
					+ "</p>";
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}

	public void resetTongueModifiers() {
		tongueModifiers = new HashSet<>(type.getDefaultRacialTongueModifiers());
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Tongue.class) && getType().getRace().isFeralPartsAvailable());
	}
}
