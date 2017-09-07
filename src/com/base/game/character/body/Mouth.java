package com.base.game.character.body;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.base.game.character.GameCharacter;
import com.base.game.character.body.types.MouthType;
import com.base.game.character.body.valueEnums.LipSize;
import com.base.game.character.body.valueEnums.OrificeModifier;
import com.base.game.dialogue.utils.UtilText;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public class Mouth implements BodyPartInterface, Serializable {

	private static final long serialVersionUID = 1L;
	
	private MouthType type;
	private OrificeMouth orificeMouth;
	private int lipSize;
	private boolean piercedLip;

	public Mouth(MouthType type, int lipSize, int wetness, int capacity, int elasticity, int plasticity, boolean virgin) {
		this.type = type;
		this.lipSize = lipSize;
		orificeMouth = new OrificeMouth(wetness, capacity, elasticity, plasticity, virgin, type.getDefaultRacialOrificeModifiers());
	}

	@Override
	public MouthType getType() {
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
		return getNamePlural(owner);
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
		List<String> descriptorList = new ArrayList<String>();
		
		for(OrificeModifier om : orificeMouth.getOrificeModifiers()) {
			descriptorList.add(om.getName());
		}
		descriptorList.add(type.getDescriptor(owner));
		
		return UtilText.returnStringAtRandom(descriptorList.toArray(new String[]{}));
	}
	
	public String getLipsNameSingular(GameCharacter gc) {
		return UtilText.returnStringAtRandom("lip");
	}
	
	public String getLipsNamePlural(GameCharacter gc) {
		return UtilText.returnStringAtRandom("lips");
	}

	public String getLipsDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			return UtilText.returnStringAtRandom("soft", "plump", "full");
		} else {
			return UtilText.returnStringAtRandom("");
		}
	}

	public void setType(MouthType type) {
		this.type = type;
	}

	public LipSize getLipSize() {
		return LipSize.getLipSizeFromInt(lipSize);
	}
	
	public int getLipSizeValue() {
		return lipSize;
	}

	public String setLipSize(GameCharacter owner, int lipSize) {
		if(owner.getLipSizeValue() == lipSize) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The size of your [pc.lips] doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The size of [npc.name]'s [pc.lips] doesn't change...)]</p>");
			}
		}
		
		String transformation = "";
		
		if(this.lipSize > lipSize) {
			if(owner.isPlayer()) {
				transformation = "<p>A soothing coolness rises up into your [pc.lips], causing you to let out a surprised gasp as you feel them [style.boldShrink(shrinking)].</br>";
			} else {
				transformation = UtilText.parse(owner, "<p>[npc.Name] lets out a little cry as [npc.she] feels a soothing coolness rise up into [npc.her] [npc.lips], before they suddenly [style.boldShrink(shrink)].</br>");
			}
			
		} else {
			if(owner.isPlayer()) {
				transformation = "<p>A pulsating warmth rises up into your [pc.lips], causing you to let out a surprised gasp as you feel them [style.boldGrow(growing larger)].</br>";
			} else {
				transformation = UtilText.parse(owner, "<p>[npc.Name] lets out a little cry as [npc.she] feels a pulsating warmth rise up into [npc.her] [npc.lips], before they suddenly [style.boldGrow(grow larger)].</br>");
			}
		}
		
		this.lipSize = lipSize;

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
						"<p>[npc.Name]'s [npc.lips] are now [style.boldGrow(pierced)]!</p>");
			}
		} else {
			if(owner.isPlayer()) {
				return "<p>Your [pc.lips] are [style.boldShrink(no longer pierced)]!</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.Name]'s [npc.lips] are [style.boldShrink(no longer pierced)]!</p>");
			}
		}
	}
	
}