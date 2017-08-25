package com.base.game.character.body;

import java.io.Serializable;
import com.base.game.character.GameCharacter;
import com.base.game.character.body.types.BodyPartTypeInterface;
import com.base.game.character.body.types.NippleType;
import com.base.game.character.body.valueEnums.AreolaeShape;
import com.base.game.character.body.valueEnums.AreolaeSize;
import com.base.game.character.body.valueEnums.NippleShape;
import com.base.game.character.body.valueEnums.NippleSize;
import com.base.game.character.body.valueEnums.OrificeModifier;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.sex.OrificeType;
import com.base.game.sex.Sex;
import com.base.main.Main;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public class Nipples implements BodyPartInterface, Serializable {

	private static final long serialVersionUID = 1L;
	
	private NippleType type;
	private OrificeNipples orificeNipples;
	private NippleShape nippleShape;
	private AreolaeShape areolaeShape;
	private int areolaeSize, nippleSize;
	private boolean pierced;

	public Nipples(NippleType type, int nippleSize, NippleShape nippleShape, int areolaeSize, int wetness, int capacity, int elasticity, int plasticity, boolean virgin) {
		this.type = type;
		this.nippleSize = nippleSize;
		this.nippleShape = nippleShape;
		areolaeShape = AreolaeShape.NORMAL;
		this.areolaeSize = areolaeSize;
		orificeNipples = new OrificeNipples(wetness, capacity, elasticity, plasticity, virgin, type.getDefaultRacialOrificeModifiers());
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
		String name = "";
		
		switch(nippleShape) {
			case LIPS:
				name = UtilText.returnStringAtRandom("lipple", "nipple-lip");
				break;
			case NORMAL:
				name = type.getNameSingular(owner);
				break;
			case VAGINA:
				name = UtilText.returnStringAtRandom("nipple-cunt", "nipple-pussy");
				break;
		}
		
		return name;
	}

	@Override
	public String getNamePlural(GameCharacter owner) {
		String name = "";
		
		switch(nippleShape) {
			case LIPS:
				name = UtilText.returnStringAtRandom("lipples", "nipple-lips");
				break;
			case NORMAL:
				name = type.getNamePlural(owner);
				break;
			case VAGINA:
				name = UtilText.returnStringAtRandom("nipple-cunts", "nipple-pussies");
				break;
		}
		
		return name;
	}

	@Override
	public String getDescriptor(GameCharacter owner) {
		// Randomly give a capacity, wetness or type-specific descriptor:
		
		String wetnessDescriptor = owner.getBreastLactation().getAssociatedWetness().getDescriptor();
		if(Main.game.isInSex()) {
			if(owner.isPlayer() && !Sex.getWetOrificeTypes().get(OrificeType.NIPPLE_PLAYER).isEmpty()) {
				wetnessDescriptor = "wet";
			} else if(!owner.isPlayer() && !Sex.getWetOrificeTypes().get(OrificeType.NIPPLE_PARTNER).isEmpty()) {
				wetnessDescriptor = "wet";
			}
		}
		
		return UtilText.returnStringAtRandom(
				(orificeNipples.hasOrificeModifier(OrificeModifier.MUSCLE_CONTROL)?OrificeModifier.MUSCLE_CONTROL.getName():""),
				(orificeNipples.hasOrificeModifier(OrificeModifier.RIBBED)?OrificeModifier.RIBBED.getName():""),
				(orificeNipples.hasOrificeModifier(OrificeModifier.TENTACLED)?OrificeModifier.TENTACLED.getName():""),
				(orificeNipples.hasOrificeModifier(OrificeModifier.PUFFY)?OrificeModifier.PUFFY.getName():""),
				
				type.getDescriptor(owner),
				
				wetnessDescriptor,
				
				orificeNipples.getCapacity().getDescriptor());
		
	}

	@Override
	public NippleType getType() {
		return type;
	}

	public void setType(GameCharacter owner, BodyPartTypeInterface type) {
		this.type = (NippleType) type;
	}

	public NippleSize getNippleSize() {
		return NippleSize.getNippleSizeFromInt(nippleSize);
	}
	
	public int getNippleSizeValue() {
		return nippleSize;
	}

	public String setNippleSize(GameCharacter owner, int nippleSize) {
		if(this.nippleSize == nippleSize) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The size of your [pc.nipples] doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The size of [npc.name]'s [pc.nipples] doesn't change...)]</p>");
			}
		}
		
		String transformation = "";
		
		if(this.nippleSize > nippleSize) {
			if(owner.isPlayer()) {
				transformation = "<p>A soothing coolness rises up into your [pc.nipples], causing you to let out a surprised gasp as you feel them [style.boldShrink(shrinking)].</br>";
			} else {
				transformation = UtilText.parse(owner, "<p>[npc.Name] lets out a little cry as [npc.she] feels a soothing coolness rise up into [npc.her] [npc.nipples], before they suddenly [style.boldShrink(shrink)].</br>");
			}
			
		} else {
			if(owner.isPlayer()) {
				transformation = "<p>A pulsating warmth rises up into your [pc.nipples], causing you to let out a surprised gasp as you feel them [style.boldGrow(growing larger)].</br>";
			} else {
				transformation = UtilText.parse(owner, "<p>[npc.Name] lets out a little cry as [npc.she] feels a pulsating warmth rise up into [npc.her] [npc.nipples], before they suddenly [style.boldGrow(grow larger)].</br>");
			}
		}
		
		this.nippleSize = nippleSize;

		if(owner.isPlayer()) {
			return transformation
				+ "You now have [style.boldSex([pc.nippleSize] [pc.nipples])]!</p>";
		} else {
			return transformation
					+ UtilText.parse(owner, "[npc.Name] now has [style.boldSex([npc.nippleSize] [npc.nipples])]!</p>");
		}
	}

	public NippleShape getNippleShape() {
		return nippleShape;
	}
	
	public String setNippleShape(GameCharacter owner, NippleShape nippleShape) {
		
		if(this.nippleShape == nippleShape) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The shape of your [pc.nipples] doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The shape of [npc.name]'s [npc.nipples] doesn't change...)]</p>");
			}
		}
		
		String transformation = "";
		
		switch(nippleShape) {
			case NORMAL:
				if(owner.isPlayer()) {
					transformation = "<p>"
										+ "Your [pc.nipples] suddenly grow sore and sensitive, and before you have any time to react, they suddenly transform into normal-looking nipples.</br>"
										+ "Your [pc.nipplesFullDescriptionColour] [pc.nipples] have transformed into [style.boldSex(normal nipples)]!"
									+ "</p>";
				} else {
					transformation = "<p>"
										+ "[npc.Name] shifts about uncomfortably as [npc.her] [npc.nipples] start to grow sore and sensitive, before suddenly transforming into normal-looking nipples.</br>"
										+ "[npc.Name]'s [npc.nipplesFullDescriptionColour] [npc.nipples] have transformed into [style.boldSex(normal nipples)]!"
									+ "</p>";
				}
				break;
			case LIPS:
				if(owner.isPlayer()) {
					transformation = "<p>"
										+ "Your [pc.nipples] suddenly grow sore and sensitive, and before you have any time to react, they suddenly swell up and transform into juicy pairs of lips!</br>"
										+ "Your [pc.nipplesFullDescriptionColour] [pc.nipples] have transformed into [style.boldSex(lip-like lipples)], which you can control just like regular lips!"
									+ "</p>";
				} else {
					transformation = "<p>"
										+ "[npc.Name] shifts about uncomfortably as [npc.her] [npc.nipples] start to grow sore and sensitive, before suddenly swelling up and transforming into juicy pairs of lips!</br>"
										+ "[npc.Name]'s [npc.nipplesFullDescriptionColour] [npc.nipples] have transformed into [style.boldSex(lip-like lipples)], which [npc.she] can control just like regular lips!"
									+ "</p>";
				}
				break;
			case VAGINA:
				if(owner.isPlayer()) {
					transformation = "<p>"
										+ "Your [pc.nipples] suddenly grow sore and sensitive, and before you have any time to react, they suddenly shift and transform into vaginas!</br>"
										+ "Your [pc.nipplesFullDescriptionColour] [pc.nipples] have transformed into [style.boldSex(vagina-like nipple-cunts)]!"
									+ "</p>";
				} else {
					transformation = "<p>"
										+ "[npc.Name] shifts about uncomfortably as [npc.her] [npc.nipples] start to grow sore and sensitive, before suddenly shifting and transforming into vaginas!</br>"
										+ "[npc.Name]'s [npc.nipplesFullDescriptionColour] [npc.nipples] have transformed into [style.boldSex(vagina-like nipple-cunts)]!"
									+ "</p>";
				}
				break;
		}
		
		this.nippleShape = nippleShape;
		
		return transformation;
	}
	
	public AreolaeShape getAreolaeShape() {
		return areolaeShape;
	}
	
	public String setAreolaeShape(GameCharacter owner, AreolaeShape areolaeShape) {
		
		if(this.areolaeShape == areolaeShape) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The shape of your areolae doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The shape of [npc.name]'s areolae doesn't change...)]</p>");
			}
		}

		this.areolaeShape = areolaeShape;
		
		switch(areolaeShape) {
			case NORMAL:
				if(owner.isPlayer()) {
					return "<p>Your [pc.nipples] suddenly start to tingle, and you gasp as you feel your areolae shift and transform into regular-looking circles.</br>"
								+ "Your areolae are now shaped like [style.boldSex(circles)]!";
				} else {
					return "<p>[npc.Name]'s [npc.nipples] suddenly start to tingle, and [npc.she] gasps as [npc.she] feels [npc.her] areolae shift and transform into regular-looking circles.</br>"
							+ "[npc.Name]'s areolae are now shaped like [style.boldSex(circles)]!";
				}
			case HEART:
				if(owner.isPlayer()) {
					return "<p>Your [pc.nipples] suddenly start to tingle, and you gasp as you feel your areolae shift and transform into the shape of hearts.</br>"
							+ "Your areolae are now shaped like [style.boldSex(hearts)]!";
				} else {
					return "<p>[npc.Name]'s [npc.nipples] suddenly start to tingle, and [npc.she] gasps as [npc.she] feels [npc.her] areolae shift and transform into the shape of hearts.</br>"
							+ "[npc.Name]'s areolae are now shaped like [style.boldSex(hearts)]!";
				}
			case STAR:
				if(owner.isPlayer()) {
					return "<p>Your [pc.nipples] suddenly start to tingle, and you gasp as you feel your areolae shift and transform into the shape of stars.</br>"
							+ "Your areolae are now shaped like [style.boldSex(stars)]!";
				} else {
					return "<p>[npc.Name]'s [npc.nipples] suddenly start to tingle, and [npc.she] gasps as [npc.she] feels [npc.her] areolae shift and transform into the shape of stars.</br>"
							+ "[npc.Name]'s areolae are now shaped like [style.boldSex(stars)]!";
				}
		}
		
		return "";
	}

	public OrificeNipples getOrificeNipples() {
		return orificeNipples;
	}

	public AreolaeSize getAreolaeSize() {
		return AreolaeSize.getAreolaeSizeFromInt(areolaeSize);
	}
	
	public int getAreolaeSizeValue() {
		return areolaeSize;
	}

	public String setAreolaeSize(GameCharacter owner, int areolaeSize) {
		if(this.areolaeSize == areolaeSize) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The size of your areolae doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The size of [npc.name]'s areolae doesn't change...)]</p>");
			}
		}
		
		String transformation = "";
		
		if(this.areolaeSize > areolaeSize) {
			if(owner.isPlayer()) {
				transformation = "<p>You feel a strange tingling sensation suddenly build up around your [pc.nipples], and you let out a little cry as you feel your areolae [style.boldShrink(shrinking)].</br>";
			} else {
				transformation = UtilText.parse(owner, "<p>[npc.Name] lets out a little cry as [npc.she] feels a strange tingling sensation suddenly build up around [npc.her] [npc.nipples], before [npc.her] areolae suddenly [style.boldShrink(shrink)].</br>");
			}
			
		} else {
			if(owner.isPlayer()) {
				transformation = "<p>You feel a strange tingling sensation suddenly build up around your [pc.nipples], and you let out a little cry as you feel your areolae [style.boldGrow(getting larger)].</br>";
			} else {
				transformation = UtilText.parse(owner, "<p>[npc.Name] lets out a little cry as [npc.she] feels a strange tingling sensation suddenly build up around [npc.her] [npc.nipples], before [npc.her] areolae suddenly [style.boldGrow(grow larger)].</br>");
			}
		}
		
		this.areolaeSize = areolaeSize;

		if(owner.isPlayer()) {
			return transformation
				+ "You now have [style.boldSex([pc.areolaeSize] [pc.nipples])]!</p>";
		} else {
			return transformation
					+ UtilText.parse(owner, "[npc.Name] now has [style.boldSex([npc.areolaeSize] [npc.nipples])]!</p>");
		}
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
				return "<p>Your [pc.nipples] are now [style.boldGrow(pierced)]!</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.Name]'s [npc.nipples] are now [style.boldGrow(pierced)]!</p>");
			}
		} else {
			if(owner.isPlayer()) {
				return "<p>Your [pc.nipples] are [style.boldShrink(no longer pierced)]!</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.Name]'s [npc.nipples] are [style.boldShrink(no longer pierced)]!</p>");
			}
		}
		
	}
}