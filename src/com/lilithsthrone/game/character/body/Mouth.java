package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractMouthType;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Mouth implements BodyPartInterface {
	
	protected AbstractMouthType type;
	protected OrificeMouth orificeMouth;
	protected int lipSize;
	protected boolean piercedLip;

	public Mouth(AbstractMouthType type, int lipSize, int wetness, float capacity, int depth, int elasticity, int plasticity, boolean virgin) {
		this.type = type;
		this.lipSize = lipSize;
		orificeMouth = new OrificeMouth(wetness, capacity, depth, elasticity, plasticity, virgin, type.getDefaultRacialOrificeModifiers());
	}

	public Mouth(Mouth mouthToCopy) {
		this.type = mouthToCopy.type;
		this.lipSize = mouthToCopy.lipSize;
		this.orificeMouth = new OrificeMouth(mouthToCopy.orificeMouth);
		this.piercedLip = mouthToCopy.piercedLip;
	}
	
	@Override
	public AbstractMouthType getType() {
		return type;
	}

	public OrificeMouth getOrificeMouth() {
		return orificeMouth;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		return type.getDeterminer(gc);
	}

	@Override
	public String getName(GameCharacter owner) {
		return getNameSingular(owner);
	}
	
	@Override
	public String getName(GameCharacter gc, boolean withDescriptor) {
		String name = getName(gc);
		return //UtilText.generateSingularDeterminer(name)+" "+
				name;
	}

	@Override
	public String getNameSingular(GameCharacter owner) {
		return type.getNameSingular(owner);
	}

	@Override
	public String getNamePlural(GameCharacter owner) {
		return type.getNamePlural(owner);
	}

	@Override
	public String getDescriptor(GameCharacter owner) {
		List<String> descriptorList = new ArrayList<>();
		
		for(OrificeModifier om : orificeMouth.getOrificeModifiers()) {
			descriptorList.add(om.getName());
		}
		descriptorList.add(owner.getCovering(owner.getMouthCovering()).getPrimaryColour().getName());
		
		descriptorList.add(type.getDescriptor(owner));
		
		return Util.randomItemFrom(descriptorList);
	}
	
	public String getLipsNameSingular(GameCharacter gc) {
		return type.getLipsNameSingular(gc);
	}
	
	public String getLipsNamePlural(GameCharacter gc) {
		return type.getLipsNamePlural(gc);
	}

	public String getLipsDescriptor(GameCharacter gc) {
		List<String> descriptorList = new ArrayList<>();
		
		if(type.isLipsDescriptorSizeAllowed(gc) && (!Main.game.isInSex() || getLipSize()!=LipSize.ONE_AVERAGE)) {
			descriptorList.add(getLipSize().getName());
		}
		
		descriptorList.addAll(type.getLipsDescriptors(gc));

		return Util.randomItemFrom(descriptorList);
	}

	public void setType(AbstractMouthType type) {
		this.type = type;
	}

	public LipSize getLipSize() {
		return LipSize.getLipSizeFromInt(lipSize);
	}
	
	public int getLipSizeValue() {
		return lipSize;
	}

	public String setLipSize(GameCharacter owner, int lipSize) {
		int effectiveLipSize = Math.max(0, Math.min(lipSize, LipSize.getLargest()));
		
		if(owner==null) {
			this.lipSize = effectiveLipSize;
			return "";
		}
		
		if(owner.getLipSizeValue() == effectiveLipSize) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The size of your [pc.lips] doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The size of [npc.namePos] [npc.lips] doesn't change...)]</p>");
			}
		}
		
		String transformation = "";
		
		if(this.lipSize > effectiveLipSize) {
			if(owner.isPlayer()) {
				transformation = "<p>A soothing coolness rises up into your [pc.lips], causing you to let out a surprised gasp as you feel them [style.boldShrink(shrinking)].<br/>";
			} else {
				transformation = UtilText.parse(owner, "<p>[npc.Name] lets out a little cry as [npc.she] feels a soothing coolness rise up into [npc.her] [npc.lips], before they suddenly [style.boldShrink(shrink)].<br/>");
			}
			
		} else {
			if(owner.isPlayer()) {
				transformation = "<p>A pulsating warmth rises up into your [pc.lips], causing you to let out a surprised gasp as you feel them [style.boldGrow(growing larger)].<br/>";
			} else {
				transformation = UtilText.parse(owner, "<p>[npc.Name] lets out a little cry as [npc.she] feels a pulsating warmth rise up into [npc.her] [npc.lips], before they suddenly [style.boldGrow(grow larger)].<br/>");
			}
		}
		
		this.lipSize = effectiveLipSize;

		if(owner.isPlayer()) {
			return transformation
				+ "You now have [style.boldSex([pc.lipSize] [pc.lips])]!</p>";
		} else {
			return transformation
					+ UtilText.parse(owner, "[npc.Name] now has [style.boldSex([npc.lipSize] [npc.lips])]!</p>");
		}
	}
	
	public boolean isPiercedLip() {
		return piercedLip;
	}
	
	public String setPiercedLip(GameCharacter owner, boolean piercedLip) {
		if(owner.isPiercedLip() == piercedLip) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		this.piercedLip = piercedLip;
		
		if(piercedLip) {
			if(owner.isPlayer()) {
				return "<p>Your [pc.lips] are now [style.boldGrow(pierced)]!</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.NamePos] [npc.lips] are now [style.boldGrow(pierced)]!</p>");
			}
			
		} else {
			AbstractClothing c = owner.getClothingInSlot(InventorySlot.PIERCING_LIP);
			String piercingUnequip = "";
			if(c!=null) {
				owner.forceUnequipClothingIntoVoid(owner, c);
				piercingUnequip = owner.addClothing(c, false);
			}
			
			if(owner.isPlayer()) {
				return "<p>"
							+ "Your [pc.lips] are [style.boldShrink(no longer pierced)]!"
						+ "</p>"
						+piercingUnequip;
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.NamePos] [npc.lips] are [style.boldShrink(no longer pierced)]!"
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
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Mouth.class) && getType().getRace().isFeralPartsAvailable());
	}
	
}