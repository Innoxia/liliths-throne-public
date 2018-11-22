package com.lilithsthrone.game.character.body;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.TongueType;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public class Tongue implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	protected TongueType type;
	protected Set<TongueModifier> tongueModifiers;
	protected int tongueLength;
	protected boolean pierced;

	public Tongue(TongueType type) {
		this.type = type;
		pierced = false;
		
		tongueLength = type.getDefaultTongueLength();
		
		this.tongueModifiers = new HashSet<>(type.getDefaultRacialTongueModifiers());
	}

	@Override
	public TongueType getType() {
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
		
        return UtilText.returnStringAtRandom(list.toArray(new String[]{}));
	}
	
	/**
	 * Tongue type is set when FaceType changes.
	 */
	public void setType(TongueType type) {
		this.type = type;
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
						+ "You now have "+UtilText.generateSingularDeterminer(Util.intToString(this.tongueLength))+" [style.boldTfGeneric("+this.tongueLength+"-inch [pc.tongue])]!</p>";
			} else {
				return UtilText.parse(owner, "<p>[npc.Name] lets out a little cry as [npc.she] feels a soothing coolness rise up into [npc.her] [npc.tongue], before it suddenly [style.boldShrink(gets shorter)].<br/>"
						+ "[npc.Name] now has "+UtilText.generateSingularDeterminer(Util.intToString(this.tongueLength))+" [style.boldTfGeneric("+this.tongueLength+"-inch [npc.tongue])]!</p>");
			}
			
		} else {
			if(owner.isPlayer()) {
				return "<p>A pulsating warmth rises up into your [pc.tongue], causing you to let out a surprised gasp as you feel it [style.boldGrow(growing longer)].<br/>"
						+ "You now have "+UtilText.generateSingularDeterminer(Util.intToString(this.tongueLength))+" [style.boldTfGeneric("+this.tongueLength+"-inch [pc.tongue])]!</p>";
			} else {
				return UtilText.parse(owner, "<p>[npc.Name] lets out a little cry as [npc.she] feels a pulsating warmth rise up into [npc.her] [npc.tongue], before it suddenly [style.boldGrow(grows longer)].<br/>"
						+ "[npc.Name] now has "+UtilText.generateSingularDeterminer(Util.intToString(this.tongueLength))+" [style.boldTfGeneric("+this.tongueLength+"-inch [npc.tongue])]!</p>");
			}
		}
	}
	
	public boolean hasTongueModifier(TongueModifier modifier) {
		return tongueModifiers.contains(modifier);
	}

	public String addTongueModifier(GameCharacter owner, TongueModifier modifier) {
		if(hasTongueModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		tongueModifiers.add(modifier);
		
		switch(modifier) {
			case RIBBED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense pressure building up in your [pc.tongue], but before you have a chance to panic, it suddenly fades away, leaving you with a series of [style.boldGrow(hard, fleshy ribs)] lining your [pc.tongue].<br/>"
								+ "[style.boldSex(Your [pc.tongue] is now ribbed!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense pressure builds up in [npc.namePos] [npc.tongue], but before [npc.she] has a chance to panic, it suddenly fades away,"
									+ " leaving [npc.herHim] with a series of [style.boldGrow(hard, fleshy ribs)] lining [npc.her] [npc.tongue].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.tongue] is now ribbed!)]"
							+ "</p>";
				}
			case TENTACLED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense pressure building up in your [pc.tongue], and you let out a gasp as you feel [style.boldGrow(a series of little wriggling tentacles)] grow up all over your [pc.tongue].<br/>"
								+ "[style.boldSex(Your [pc.tongue] is now covered with little tentacles, which wriggle with a mind of their own!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense pressure builds up in [npc.namePos] [npc.tongue], and [npc.she] lets out a gasp as [npc.she] feels [style.boldGrow(a series of little wriggling tentacles)] grow up all over [npc.her] [npc.tongue].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.tongue] is now covered with little tentacles, which wriggle with a mind of their own!)]"
							+ "</p>";
				}
			case BIFURCATED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense pressure building up in your [pc.tongue], and you let out a gasp as you feel [style.boldGrow(it split in two)].<br/>"
								+ "[style.boldSex(Your [pc.tongue] is now bifurcated, like a snake's!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense pressure builds up in [npc.namePos] [npc.tongue], and [npc.she] lets out a gasp as [npc.she] feels [style.boldGrow(it split in two)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.tongue] is now bifurcated, like a snake's!)]"
							+ "</p>";
				}
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}

	public String removeTongueModifier(GameCharacter owner, TongueModifier modifier) {
		if(!hasTongueModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		tongueModifiers.remove(modifier);
		
		switch(modifier) {
			case RIBBED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soft coolness rising up into your [pc.tongue], but before you have a chance to react, it suddenly fades away, [style.boldShrink(removing)] your hard, fleshy ribs in the process.<br/>"
								+ "[style.boldSex(Your [pc.tongue] is no longer ribbed!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An soft coolness rises up into [npc.namePos] [npc.tongue], but before [npc.she] has a chance to react, it suddenly fades away, [style.boldShrink(removing)] [npc.her] hard, fleshy ribs in the process.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.tongue] is no longer ribbed!)]"
							+ "</p>";
				}
			case TENTACLED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soft coolness rise up into your [pc.tongue], and you let out a gasp as you feel your [style.boldShrink(little wriggling tentacles disappear)].<br/>"
								+ "[style.boldSex(Your [pc.tongue] is no longer covered with little tentacles!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "A soft coolness rises up into [npc.namePos] [npc.tongue], and [npc.she] lets out a gasp as [npc.she] feels [npc.her] [style.boldShrink(little wriggling tentacles disappear)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.tongue] is no longer covered with little tentacles!)]"
							+ "</p>";
				}
			case BIFURCATED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soft coolness rise up into your [pc.tongue], and you let out a gasp as you feel [style.boldShrink(it fuse back into a single point)].<br/>"
								+ "[style.boldSex(Your [pc.tongue] is no longer bifurcated!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "A soft coolness rises up into [npc.namePos] [npc.tongue], and [npc.she] lets out a gasp as [npc.she] feels [style.boldShrink(it fuse back into a single point)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.tongue] is no longer bifurcated!)]"
							+ "</p>";
				}
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}
}
