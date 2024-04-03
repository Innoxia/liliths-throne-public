package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractNippleType;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeShape;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.4
 * @author Innoxia
 */
public class Nipples implements BodyPartInterface {
	
	protected AbstractNippleType type;
	protected OrificeNipples orificeNipples;
	protected NippleShape nippleShape;
	protected AreolaeShape areolaeShape;
	protected int areolaeSize;
	protected int nippleSize;
	protected boolean pierced;
	protected boolean crotchNipples;

	public Nipples(AbstractNippleType type, int nippleSize, NippleShape nippleShape, int areolaeSize, AreolaeShape areolaeShape, int wetness, float capacity, int depth, int elasticity, int plasticity, boolean virgin, boolean crotchNipples) {
		this.type = type;
		this.nippleSize = nippleSize;
		this.nippleShape = nippleShape;
		this.areolaeShape = areolaeShape;
		this.areolaeSize = areolaeSize;
		orificeNipples = new OrificeNipples(wetness, capacity, depth, elasticity, plasticity, virgin, crotchNipples, type.getDefaultRacialOrificeModifiers());
		this.crotchNipples = crotchNipples;
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
		if(crotchNipples) {
			return type.getNameCrotchSingular(owner);
		} else {
			return type.getNameSingular(owner);
		}
	}

	@Override
	public String getNamePlural(GameCharacter owner) {
		if(crotchNipples) {
			return type.getNameCrotchPlural(owner);
		} else {
			return type.getNamePlural(owner);
		}
	}

	@Override
	public String getDescriptor(GameCharacter owner) {
		List<String> descriptorList = new ArrayList<>();
		
		for(OrificeModifier om : orificeNipples.getOrificeModifiers()) {
			descriptorList.add(om.getName());
		}
		
		if(isCrotchNipples()) {
			if(owner.getNippleCrotchCovering()!=null) {
				descriptorList.add(owner.getCovering(owner.getNippleCrotchCovering()).getColourDescriptor(owner, false, false));
			}
			
			if(owner.isBreastCrotchFuckableNipplePenetration()) {
				switch(owner.getBreastCrotchMilkStorage().getAssociatedWetness()) {
					case ONE_SLIGHTLY_MOIST:
					case TWO_MOIST:
					case THREE_WET:
					case FOUR_SLIMY:
					case FIVE_SLOPPY:
					case SIX_SOPPING_WET:
					case SEVEN_DROOLING:
//						descriptorList.add(owner.getBreastCrotchMilkStorage().getAssociatedWetness().getDescriptor());
						descriptorList.add("milky");
						break;
					default:
						break;
				}
			}
			
		} else {
			if(owner.getNippleCovering()!=null) {
				descriptorList.add(owner.getCovering(owner.getNippleCovering()).getColourDescriptor(owner, false, false));
			}
			
			if(owner.isBreastFuckableNipplePenetration()) {
				switch(owner.getBreastMilkStorage().getAssociatedWetness()) {
					case ONE_SLIGHTLY_MOIST:
					case TWO_MOIST:
					case THREE_WET:
					case FOUR_SLIMY:
					case FIVE_SLOPPY:
					case SIX_SOPPING_WET:
					case SEVEN_DROOLING:
//						descriptorList.add(owner.getBreastMilkStorage().getAssociatedWetness().getDescriptor());
						descriptorList.add("milky");
						break;
					default:
						break;
				}
			}
		}
		
		if(Main.game.isInSex() && Main.sex.getAllParticipants().contains(owner)) {
			if(Main.sex.hasLubricationTypeFromAnyone(owner, SexAreaOrifice.NIPPLE)) {
				descriptorList.add("wet");
			}
		}

		switch(this.getNippleShape()) {
			case INVERTED:
				descriptorList.add("inverted");
				break;
			case LIPS:
			case NORMAL:
			case VAGINA:
				break;
		}
		
		descriptorList.add(this.getNippleSize().getName());
		
		descriptorList.add(type.getDescriptor(owner));
		
		if(orificeNipples.getCapacity()!= Capacity.ZERO_IMPENETRABLE) {
			descriptorList.add(Capacity.getCapacityFromValue(orificeNipples.getStretchedCapacity()).getDescriptor().replaceAll(" ", "-"));
		}
		
		if(owner.getBodyMaterial().getPartDescriptors()!=null && !owner.getBodyMaterial().getPartDescriptors().isEmpty()) {
			descriptorList.add(Util.randomItemFrom(owner.getBodyMaterial().getPartDescriptors()));
		}
		
		descriptorList.removeIf(d->d==null || d.isEmpty());
		
		return Util.randomItemFrom(descriptorList);
	}

	@Override
	public AbstractNippleType getType() {
		return type;
	}

	public void setType(GameCharacter owner, AbstractNippleType type) {
		this.type = type;
	}

	public NippleSize getNippleSize() {
		return NippleSize.getNippleSizeFromInt(nippleSize);
	}
	
	public int getNippleSizeValue() {
		return nippleSize;
	}

	public String setNippleSize(GameCharacter owner, int nippleSize) {
		int boundNippleSize = Math.max(0, Math.min(nippleSize, NippleSize.FOUR_MASSIVE.getValue()));
		if(owner==null) {
			this.nippleSize = boundNippleSize;
			return "";
		}
		if(this.nippleSize == boundNippleSize) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The size of [npc.namePos] [npc.nipples] doesn't change...)]</p>");
		}
		
		String transformation = "";
		
		if(this.nippleSize > boundNippleSize) {
			transformation = UtilText.parse(owner, "<p>A soothing coolness rises up into [npc.namePos] [npc.nipples], causing [npc.herHim] to let out a surprised gasp as [npc.she] [npc.verb(feel)] them start to [style.boldShrink(shrink)].<br/>");
			
		} else {
			transformation = UtilText.parse(owner, "<p>A pulsating warmth rises up into [npc.namePos] [npc.nipples], causing [npc.herHim] to let out a surprised gasp as [npc.she] [npc.verb(feel)] them start to [style.boldGrow(grow larger)].<br/>");
		}
		
		this.nippleSize = boundNippleSize;

		return transformation + UtilText.parse(owner, "[npc.Name] now [npc.has] [style.boldSex([npc.nippleSize] [npc.nipples])]!</p>");
		
	}

	public NippleShape getNippleShape() {
		return nippleShape;
	}
	
	public String setNippleShape(GameCharacter owner, NippleShape nippleShape) {
		if(owner==null) {
			this.nippleShape = nippleShape;
			return "";
		}
		
		if(this.nippleShape == nippleShape) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The shape of [npc.namePos] [npc.nipples] doesn't change...)]</p>");
		}
		
		String transformation = "";
		
		switch(nippleShape) {
			case INVERTED:
				transformation = "<p>"
									+ "[npc.Name] [npc.verb(let)] out an uncomfortable whine as [npc.her] [npc.nipples] start to grow sore and sensitive."
									+ " Before [npc.she] [npc.has] a chance to do anything else, [npc.her] nipples suddenly transform into normal-looking ones, before pulling inwards and inverting!<br/>"
									+ "[npc.NamePos] [npc.nipplesFullDescriptionColour] [npc.nipples] have transformed into [style.boldSex(inverted nipples)]!"
								+ "</p>";
				break;
			case NORMAL:
				transformation = "<p>"
									+ "[npc.Name] [npc.verb(let)] out an uncomfortable whine as [npc.her] [npc.nipples] start to grow sore and sensitive, before suddenly transforming into normal-looking nipples.<br/>"
									+ "[npc.NamePos] [npc.nipplesFullDescriptionColour] [npc.nipples] have transformed into [style.boldSex(normal nipples)]!"
								+ "</p>";
				break;
			case LIPS:
				transformation = "<p>"
									+ "[npc.Name] [npc.verb(let)] out an uncomfortable whine as [npc.her] [npc.nipples] start to grow sore and sensitive, before suddenly swelling up and transforming into juicy pairs of lips!<br/>"
									+ "[npc.NamePos] [npc.nipplesFullDescriptionColour] [npc.nipples] have transformed into [style.boldSex(lip-like lipples)], which [npc.she] can control just like regular lips!"
								+ "</p>";
				break;
			case VAGINA:
				transformation = "<p>"
									+ "[npc.Name] [npc.verb(let)] out an uncomfortable whine as [npc.her] [npc.nipples] start to grow sore and sensitive, before suddenly shifting and transforming into pseudo-pussies!<br/>"
									+ "[npc.NamePos] [npc.nipplesFullDescriptionColour] [npc.nipples] have transformed into [style.boldSex(vagina-like nipple-cunts)]!"
								+ "</p>";
				break;
		}
		
		// Parse TF before changing nipple type:
		transformation = UtilText.parse(owner, transformation);
		
		this.nippleShape = nippleShape;
		
		return transformation;
	}
	
	public AreolaeShape getAreolaeShape() {
		return areolaeShape;
	}
	
	public String setAreolaeShape(GameCharacter owner, AreolaeShape areolaeShape) {
		if(owner==null) {
			this.areolaeShape = areolaeShape;
			return "";
		}
		
		if(this.areolaeShape == areolaeShape) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The shape of [npc.namePos] areolae doesn't change...)]</p>");
		}

		this.areolaeShape = areolaeShape;
		
		String transformation = "";
		switch(areolaeShape) {
			case NORMAL:
				transformation = "<p>[npc.NamePos] [npc.nipples] suddenly start to tingle, and [npc.she] [npc.verb(gasp)] as [npc.she] [npc.verb(feel)] [npc.her] areolae shift and transform into regular-looking circles.<br/>"
							+ "[npc.NamePos] areolae are now shaped like [style.boldSex(normal circles)]!";
				break;
			case HEART:
				transformation = "<p>[npc.NamePos] [npc.nipples] suddenly start to tingle, and [npc.she] [npc.verb(gasp)] as [npc.she] [npc.verb(feel)] [npc.her] areolae shift and transform into the shape of hearts.<br/>"
							+ "[npc.NamePos] areolae are now shaped like [style.boldSex(hearts)]!";
				break;
			case STAR:
				transformation = "<p>[npc.NamePos] [npc.nipples] suddenly start to tingle, and [npc.she] [npc.verb(gasp)] as [npc.she] [npc.verb(feel)] [npc.her] areolae shift and transform into the shape of stars.<br/>"
							+ "[npc.NamePos] areolae are now shaped like [style.boldSex(stars)]!";
				break;
		}
		
		return UtilText.parse(owner, transformation);
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
		int boundAreolaeSize = Math.max(0, Math.min(areolaeSize, AreolaeSize.FOUR_MASSIVE.getValue()));
		if(owner==null) {
			this.areolaeSize = boundAreolaeSize;
			return "";
		}
		if (this.areolaeSize == boundAreolaeSize) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The size of [npc.namePos] areolae doesn't change...)]</p>");
		}
		
		String transformation = "";
		
		if (this.areolaeSize > boundAreolaeSize) {
			transformation = UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(let)] out a shocked gasp as [npc.she] [npc.verb(feel)] a strange tingling sensation suddenly build up around [npc.her] [npc.nipples], before [npc.her] areolae suddenly [style.boldShrink(shrink)].<br/>");
			
		} else {
			transformation = UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(let)] out a shocked gasp as [npc.she] [npc.verb(feel)] a strange tingling sensation suddenly build up around [npc.her] [npc.nipples], before [npc.her] areolae suddenly [style.boldGrow(grow larger)].<br/>");
		}
		
		this.areolaeSize = boundAreolaeSize;

		return transformation + UtilText.parse(owner, "[npc.Name] now [npc.has] [style.boldSex([npc.areolaeSize] areolae)]!</p>");
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
			return UtilText.parse(owner, "<p>[npc.NamePos] [npc.nipples] are now [style.boldGrow(pierced)]!</p>");
			
		} else {
			AbstractClothing c = owner.getClothingInSlot(InventorySlot.PIERCING_NIPPLE);
			String piercingUnequip = "";
			if(c!=null) {
				owner.forceUnequipClothingIntoVoid(owner, c);
				piercingUnequip = owner.addClothing(c, false);
			}
			
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.NamePos] [npc.nipples] are [style.boldShrink(no longer pierced)]!"
					+ "</p>"
					+piercingUnequip);
		}
		
	}

	public boolean isCrotchNipples() {
		return crotchNipples;
	}

	@Override
	public AbstractBodyCoveringType getBodyCoveringType(GameCharacter gc) {
		if(this.isCrotchNipples()) {
			return BodyCoveringType.NIPPLES_CROTCH;
		}
		return BodyCoveringType.NIPPLES;
	}

	@Override
	public AbstractBodyCoveringType getBodyCoveringType(Body body) {
		if(this.isCrotchNipples()) {
			return BodyCoveringType.NIPPLES_CROTCH;
		}
		return BodyCoveringType.NIPPLES;
	}
	
	@Override
	public boolean isFeral(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		if(this.isCrotchNipples()) {
			return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(BreastCrotch.class) && getType().getRace().isFeralPartsAvailable());
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Breast.class) && getType().getRace().isFeralPartsAvailable());
	}
}