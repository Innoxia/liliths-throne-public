package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractPenisType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Penis implements BodyPartInterface {
	
	public static final float TWO_PENIS_SIZE_MULTIPLIER = 1.6f;

	protected AbstractPenisType type;
	protected int length;
	protected int girth;
	protected boolean pierced;
	protected boolean virgin;
	protected Set<PenetrationModifier> penisModifiers;
	
	protected Testicle testicle;
	protected OrificePenisUrethra orificeUrethra;

	public Penis(AbstractPenisType type, int length, boolean usePenisSizePreference, int girth, int testicleSize, int cumProduction, int testicleCount) {
		this.type = type;
		if(usePenisSizePreference) {
			this.length = Math.max(1, Math.min(PenisLength.SEVEN_STALLION.getMaximumValue(), length)+Main.getProperties().penisSizePreference);
		} else {
			this.length = Math.min(PenisLength.SEVEN_STALLION.getMaximumValue(), length);
		}
		this.girth = Math.min(PenetrationGirth.getMaximum(), girth);
		pierced = false;
		virgin = true;
		
		testicle = new Testicle(type.getTesticleType(), testicleSize, cumProduction, testicleCount);
		
		orificeUrethra = new OrificePenisUrethra(testicle.getCumStorage().getAssociatedWetness().getValue(), 0, 2, OrificeElasticity.ZERO_UNYIELDING.getValue(), OrificePlasticity.THREE_RESILIENT.getValue(), true, new ArrayList<>());
		
		this.penisModifiers = new HashSet<>();
		this.penisModifiers.addAll(type.getDefaultRacialPenetrationModifiers());
	}

	public Penis(Penis penisToCopy) {
		this.type = penisToCopy.type;
		this.length = penisToCopy.length;
		this.girth = penisToCopy.girth;
		this.pierced = penisToCopy.pierced;
		this.virgin = penisToCopy.virgin;
		
		this.testicle = new Testicle(penisToCopy.testicle);
		
		this.orificeUrethra = new OrificePenisUrethra(penisToCopy.orificeUrethra);
		
		this.penisModifiers = new HashSet<>(penisToCopy.penisModifiers);
	}
	
	@Override
	public AbstractPenisType getType() {
		return type;
	}
	
	public Testicle getTesticle() {
		return testicle;
	}
	
	public OrificePenisUrethra getOrificeUrethra() {
		return orificeUrethra;
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
	public String getDescriptor(GameCharacter owner) {
		List<String> list = new ArrayList<>();
        
		if(owner.getPenisSize()!=PenisLength.TWO_AVERAGE) {
			list.add(owner.getPenisSize().getDescriptor());
		}
		
		if(owner.getPenisGirth()!=PenetrationGirth.THREE_AVERAGE) {
			list.add(owner.getPenisGirth().getName());
		}
		
		for(PenetrationModifier pm : penisModifiers) {
			if(!Main.game.isInSex() && pm!=PenetrationModifier.SHEATHED) {
				list.add(pm.getName());
			}
		}
		
		if(owner.getPenisCovering()!=null) {
			list.add(owner.getCovering(owner.getPenisCovering()).getColourDescriptor(owner, false, false));
		}
		
		if(owner.isPenisFeral()) {
			list.add(Util.randomItemFrom(Util.newArrayListOfValues(
					"feral",
					"bestial",
					"animalistic")));
		} else {
			list.add(type.getDescriptor(owner));
		}
		
		if(Main.game.isInSex() && Main.sex.getAllParticipants().contains(owner)) {
			if(owner.hasErection()) {
				list.add("hard");
				if(this.getType()!=PenisType.DILDO) {
					list.add("throbbing");
				}
			} else {
				list.add("soft");
				if(owner.isErectionPreventedPhysically()) {
					list.add("caged");
					list.add("imprisoned");
				}
			}
		}
		
		if(owner.getBodyMaterial().getPartDescriptors()!=null && !owner.getBodyMaterial().getPartDescriptors().isEmpty()) {
			list.add(Util.randomItemFrom(owner.getBodyMaterial().getPartDescriptors()));
		}
		
		list.removeIf(d->d.isEmpty());
		if(list.isEmpty()) {
			return "";
		}
		return Util.randomItemFrom(list);
	}
	
	public String getUrethraDescriptor(GameCharacter owner) {
		List<String> descriptorList = new ArrayList<>();
		
		for(OrificeModifier om : orificeUrethra.getOrificeModifiers()) {
			descriptorList.add(om.getName());
		}
		
		descriptorList.add(type.getDescriptor(owner));
		
		descriptorList.add(Capacity.getCapacityFromValue(orificeUrethra.getStretchedCapacity()).getDescriptor().replaceAll(" ", "-"));

		return Util.randomItemFrom(descriptorList);
	}
	
	public String getPenisHeadName(GameCharacter gc) {
		List<String> list = new ArrayList<>();
		list.add("head");
        
		if(penisModifiers.contains(PenetrationModifier.TAPERED)) {
			list.add("tip");
		}

		return Util.randomItemFrom(list);
	}
	
	public String getPenisHeadDescriptor(GameCharacter gc) {
		List<String> list = new ArrayList<>();
        
		if(penisModifiers.contains(PenetrationModifier.TAPERED)) {
			list.add("tapered");
			list.add("pointed");
		}
		if(penisModifiers.contains(PenetrationModifier.FLARED)) {
			list.add("wide");
			list.add("flared");
			list.add("flat");
		}

		return Util.randomItemFrom(list);
	}
	
	public String setType(GameCharacter owner, AbstractPenisType type) {
		if(this.type==PenisType.NONE) {
			this.orificeUrethra.setStretchedCapacity(this.orificeUrethra.getRawCapacityValue());
		}
		
		if(!Main.game.isStarted() || owner==null) {
			this.type = type;
			testicle.setType(owner, type.getTesticleType());
			if(owner!=null) {
				owner.resetAreaKnownByCharacters(CoverableArea.PENIS);
				owner.resetAreaKnownByCharacters(CoverableArea.TESTICLES);
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			if(type==PenisType.NONE) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.verb(lack)] a cock, so nothing happens...)]</p>");
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.has] [npc.a_cockRace]'s cock, so nothing happens...)]</p>");
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(!owner.hasPenisIgnoreDildo()) {
			if(length<1) {
				length = 1;
			}
			sb.append(
					"[npc.Name] [npc.verb(feel)] an intense heat building up in [npc.her] groin, and [npc.she] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] the [npc.skin] "
								+ (owner.hasVagina()
										? (!owner.isTaur()
											?"above [npc.her] pussy"
											:"beneath [npc.her] pussy")
										: "between [npc.her] legs")
							+ " tighten up and start to press outwards."
						+ " Within moments, a large bump has formed "
							+ (owner.hasVagina()
									? (!owner.isTaur()
										?"above [npc.her] feminine slit"
										:"beneath [npc.her] feminine slit")
									: "in the middle of [npc.her] groin,")
							+ " and with a sudden splitting sensation, the bump pushes out and forms into a penis.");
			
			if(owner.isInternalTesticles()) {
				sb.append(
						" As [npc.her] new cock flops down "
							+ (owner.hasVagina()
								? (!owner.isTaur()
										?"to bump against [npc.her] pussy, [npc.she] [npc.verb(feel)] [npc.a_balls] growing within [npc.her] groin,"
										:"beneath [npc.her] pussy, [npc.she] [npc.verb(feel)] [npc.a_balls] growing within [npc.her] groin,")
								: "between [npc.her] legs, [npc.she] [npc.verb(feel)] [npc.a_balls] growing within [npc.her] groin,")
						+ " and [npc.she] [npc.verb(let)] out an unwitting [npc.moan] as [npc.her] new sexual organ finishes growing.<br/>");
			} else {
				sb.append(
						" As [npc.her] new cock flops down "
							+ (owner.hasVagina()
								? (!owner.isTaur()
										?"to bump against [npc.her] pussy, [npc.she] [npc.verb(feel)] [npc.a_balls] pushing out between [npc.her] two sexes,"
										:"beneath [npc.her] pussy, [npc.she] [npc.verb(feel)] [npc.a_balls] pushing out between [npc.her] two sexes,")
								: "between [npc.her] legs, [npc.she] [npc.verb(feel)] [npc.a_balls] push out underneath the base of [npc.her] new shaft,")
						+ " and [npc.she] [npc.verb(let)] out an unwitting [npc.moan] as [npc.her] new sexual organ finishes growing.<br/>");
			}
			
		} else {
			sb.append(
					"[npc.Name] [npc.verb(let)] out a gasp as [npc.she] [npc.verb(feel)] [npc.her] [npc.cock] suddenly stand to attention,"
							+ " and before [npc.sheIs] able to try and get [npc.her] unexpected erection under control, [npc.her] gasp turns into [npc.a_moan+] as [npc.her] cock starts to transform.<br/>");
		}
		

		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append("<p>");
			sb.append(s);
			sb.append(this.type.applyAdditionalTransformationEffects(owner, false));
			this.type = type;
			testicle.setType(owner, type.getTesticleType());
			owner.resetAreaKnownByCharacters(CoverableArea.PENIS);
			owner.resetAreaKnownByCharacters(CoverableArea.TESTICLES);
			sb.append(this.type.getTransformationDescription(owner));
			sb.append(this.type.applyAdditionalTransformationEffects(owner, true));
		sb.append("</p>");
		
		penisModifiers.clear();
		penisModifiers.addAll(type.getDefaultRacialPenetrationModifiers());

		sb.append("<p>");
			sb.append("Any old modifiers which [npc.her] penis might have had have [style.boldShrink(transformed away)]!");
			if(!penisModifiers.isEmpty()) {
				sb.append("<br/>Instead, [npc.her] new cock is:");
				for(PenetrationModifier pm : penisModifiers) {
					sb.append("<br/>[style.boldGrow("+Util.capitaliseSentence(pm.getName())+")]");
				}
			}
		sb.append("</p>");
		
		String postTF = owner.postTransformationCalculation(false); // Calculate this before parsing, as it updates covering colours
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
					+ postTF
				+ "</p>";
	}
	
	// Girth:

	public PenetrationGirth getGirth() {
		return PenetrationGirth.getGirthFromInt(girth);
	}

	public int getRawGirthValue() {
		return girth;
	}

	/**
	 * Sets the girth. Value is bound to >=0 && <=PenisGirth.FOUR_FAT.getValue()
	 */
	public String setPenisGirth(GameCharacter owner, int girth) {
		if(owner==null) {
			this.girth = Math.max(0, Math.min(girth, PenetrationGirth.getMaximum()));
			return "";
		}
		
		if(!owner.hasPenisIgnoreDildo()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		int girthChange = 0;
		
		if (girth <= 0) {
			if (this.girth != 0) {
				girthChange = 0 - this.girth;
				this.girth = 0;
			}
		} else if (girth >= PenetrationGirth.getMaximum()) {
			if (this.girth != PenetrationGirth.getMaximum()) {
				girthChange = PenetrationGirth.getMaximum() - this.girth;
				this.girth = PenetrationGirth.getMaximum();
			}
		} else {
			if (this.girth != girth) {
				girthChange = girth - this.girth;
				this.girth = girth;
			}
		}
		
		if(girthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The girth of [npc.namePos] [npc.cock] doesn't change...)]</p>");
		}
		
		if (girthChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] cock."
						+ " [npc.Her] cheeks flush red as the feeling works its way up [npc.her] shaft, and as a trickle of precum leaks out from the head of [npc.her] now-hard member,"
							+ " [npc.she] [npc.verb(realise)] that [npc.her] cock has [style.boldGrow(grown thicker)].<br/>"
						+ "[npc.She] now [npc.has] [style.boldSex([npc.a_penisGirth] [npc.cock])]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] cock."
						+ " [npc.Her] cheeks flush red as the feeling works its way up [npc.her] shaft, and as a trickle of precum leaks out from the head of [npc.her] now-hard member,"
							+ " [npc.she] [npc.verb(realise)] that [npc.her] cock has [style.boldShrink(got thinner)].<br/>"
						+ "[npc.She] now [npc.has] [style.boldSex([npc.a_penisGirth] [npc.cock])]!"
					+ "</p>");
		}
	}
	
	// Length:

	public PenisLength getLength() {
		return PenisLength.getPenisLengthFromInt(length);
	}

	public int getRawLengthValue() {
		return length;
	}

	/**
	 * Sets the length. Value is bound to >=0 && <=PenisLength.SEVEN_STALLION.getMaximumValue()
	 */
	public String setPenisLength(GameCharacter owner, int length) {
		if(owner!=null && !owner.hasPenisIgnoreDildo()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		int lengthChange = 0;
		
		if (length <= 0) {
			if (this.length != 0) {
				lengthChange = 0 - this.length;
				this.length = 0;
			}
		} else if (length >= PenisLength.SEVEN_STALLION.getMaximumValue()) {
			if (this.length != PenisLength.SEVEN_STALLION.getMaximumValue()) {
				lengthChange = PenisLength.SEVEN_STALLION.getMaximumValue() - this.length;
				this.length = PenisLength.SEVEN_STALLION.getMaximumValue();
			}
		} else {
			if (this.length != length) {
				lengthChange = length - this.length;
				this.length = length;
			}
		}
		
		if(owner==null) {
			return "";
		}
		
		if(lengthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The length of [npc.namePos] [npc.cock] doesn't change...)]</p>");
		}
		
		if (lengthChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] cock."
						+ " [npc.Her] cheeks flush red as the feeling works its way up [npc.her] shaft, and as a trickle of precum leaks out from the head of [npc.her] now-hard member,"
							+ " [npc.she] [npc.verb(realise)] that [npc.her] cock has [style.boldGrow(grown larger)].<br/>"
						+ "[npc.She] now [npc.has] [style.boldSex([npc.a_penisSize] [npc.cock])]!"
					+ "</p>");
		} else {
			return UtilText.parse(owner,
					"<p>"
							+ "[npc.Name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(feel)] an intense tightening sensation building up at the base of [npc.her] cock."
							+ " [npc.Her] cheeks flush red as the feeling works its way up [npc.her] shaft, and as a trickle of precum leaks out from the head of [npc.her] now-hard member,"
								+ " [npc.she] [npc.verb(realise)] that [npc.her] cock has [style.boldShrink(shrunk)].<br/>"
						+ "[npc.She] now [npc.has] [style.boldSex([npc.a_penisSize] [npc.cock])]!"
					+ "</p>");
		}
	}
	
	// Diameter:

	public static float getGenericDiameter(int length, PenetrationGirth girth) {
		return getGenericDiameter(length, girth, new HashSet<>());
	}
	
	public static float getGenericDiameter(int length, PenetrationGirth girth, Set<PenetrationModifier> mods) {
		float baseDiameterModifier = 0.2f;
		baseDiameterModifier = Math.max(0.15f, baseDiameterModifier - (Math.max(length-15, 0) * 0.0025f)); // Every cm over 15 (6 inches) reduces the base diameter modifier by 0.25%
		
		return Units.round((length * baseDiameterModifier) * (1f + girth.getDiameterPercentageModifier() + (mods.contains(PenetrationModifier.FLARED)?0.05f:0) + (mods.contains(PenetrationModifier.TAPERED)?-0.05f:0)), 2);
	}
	
	public float getDiameter() {
		return getGenericDiameter(length, getGirth(), penisModifiers);
	}
	
	public boolean isPierced() {
		return pierced;
	}

	public String setPierced(GameCharacter owner, boolean pierced) {
		if(this.pierced == pierced || !owner.hasPenisIgnoreDildo()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		this.pierced = pierced;
		
		if(pierced) {
			return UtilText.parse(owner, "<p>[npc.NamePos] [npc.cock] is now [style.boldGrow(pierced)]!</p>");
			
		} else {
			AbstractClothing c = owner.getClothingInSlot(InventorySlot.PIERCING_PENIS);
			String piercingUnequip = "";
			if(c!=null) {
				owner.forceUnequipClothingIntoVoid(owner, c);
				piercingUnequip = owner.addClothing(c, false);
			}
			
			return UtilText.parse(owner,
					"<p>[npc.NamePos] [npc.cock] is [style.boldShrink(no longer pierced)]!</p>"
					+piercingUnequip);
		}
	}
	
	public boolean isVirgin() {
		return virgin;
	}

	public void setVirgin(boolean virgin) {
		this.virgin = virgin;
	}
	
	public Set<PenetrationModifier> getPenisModifiers() {
		return penisModifiers;
	}
	
	public boolean hasPenisModifier(PenetrationModifier modifier) {
		return penisModifiers.contains(modifier);
	}

	public String addPenisModifier(GameCharacter owner, PenetrationModifier modifier) {
		if(hasPenisModifier(modifier)) {
			return owner == null ? "" : "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		if(owner==null || owner.getBody()==null) {
			penisModifiers.add(modifier);
			return "";
		}
		
		if(!owner.hasPenisIgnoreDildo()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.do]n't have a penis, so nothing happens...)]</p>");
		}
		
		penisModifiers.add(modifier);

		List<String> pmsRemoved = new ArrayList<>();
		
		for(PenetrationModifier pm : modifier.getMutuallyExclusivePenetrationModifiers()) {
			if(hasPenisModifier(pm)) {
				pmsRemoved.add(pm.getName());
				penisModifiers.remove(pm);
			}
		}
		String removedText = "";
		if(!pmsRemoved.isEmpty()) {
			removedText = "<br/>[style.italicsMinorBad(Due to being mutually exclusive with the '"+modifier.getName()+"' modifier, [npc.namePos] cock is no longer "+Util.stringsToStringList(pmsRemoved, false)+".)]";
		}
		
		String returnText = "";
		
		switch(modifier) {
			case RIBBED:
				returnText = "An intense pressure builds up within [npc.namePos] [npc.cock], but before [npc.she] [npc.has] a chance to react, a series of [style.boldGrow(hard, fleshy ribs)] grow up all along its length."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.cock] is now ribbed!)]";
				break;
			case TENTACLED:
				returnText = "A pulsating warmth builds up within [npc.namePos] [npc.cock], but before [npc.she] [npc.has] a chance to react, a series of [style.boldGrow(little wriggling tentacles)] grow up all along its length."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.cock] is now covered with little tentacles, which wriggle with a mind of their own!)]";
				break;
			case BARBED:
				returnText = "An intense warmth builds up within [npc.namePos] [npc.cock], but before [npc.she] [npc.has] a chance to react, a series of [style.boldGrow(little fleshy barbs)] grow up all along its length."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.cock] is now lined with fleshy, backwards-facing barbs!)]";
				break;
			case FLARED:
				returnText = "An intense warmth builds up in the tip of [npc.namePos] [npc.cock], and before [npc.she] [npc.has] a chance to react, the [style.boldGrow(head flares out)], much like that of a horse's."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.cock] now has a wide, flared head!)]";
				break;
			case BLUNT:
				returnText = "An intense warmth builds up in the tip of [npc.namePos] [npc.cock], and before [npc.she] [npc.has] a chance to react, the [style.boldGrow(head smoothes over)], much like that of a reptile's."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.cock] now has a smooth, blunt head!)]";
				break;
			case KNOTTED:
				returnText = "An intense warmth builds up in the base of [npc.namePos] [npc.cock], and before [npc.she] [npc.has] a chance to react, a [style.boldGrow(fat knot)] quickly grows up there."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.cock] now has a fat knot at the base!)]";
				break;
			case PREHENSILE:
				returnText = "A strange tingling sensation works its way up the length of [npc.namePos] [npc.cock], and [npc.she] suddenly becomes aware that it's transformed into being [style.boldGrow(prehensile)],"
								+ " allowing [npc.herHim] to twist and move it around just like a primate's tail."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.cock] is now prehensile!)]";
				break;
			case SHEATHED:
				returnText = "An intense pressure builds up in the base of [npc.namePos] [npc.cock], and before [npc.she] [npc.has] a chance to react, it pulls back into a brand new [style.boldGrow(sheath)] that's just grown up there."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.cock] is now sheathed!)]";
				break;
			case TAPERED:
				returnText = "An intense warmth builds up within [npc.namePos] [npc.cock], but before [npc.she] [npc.has] a chance to react, the shaft suddenly [style.boldGrow(tapers down)]."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.cock] is now tapered!)]";
				break;
			case VEINY:
				returnText = "An intense warmth builds up within [npc.namePos] [npc.cock], but before [npc.she] [npc.has] a chance to react, a series of [style.boldGrow(prominent veins)] grow up all along its length."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.cock] is now veiny!)]";
				break;
			case OVIPOSITOR:
				returnText = "An intense tingling sensation works its up [npc.namePos] [npc.cock], and [npc.she] can't help but let out [npc.a_moan+] as [npc.she] feels it transforming into [style.boldGrow(an ovipositor)]."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.cock] is now able to lay eggs!)]"
							+ "<br/><i>(To be fully functional, [npc.name] [npc.verb(require)] [npc.her] eggs to be fertilised before laying can occur. Eggs cannot be laid in an already-pregnant target's vagina.)</i>";
				break;
		}
		
		if(returnText.isEmpty()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		return UtilText.parse(owner,
				"<p>"
					+returnText
					+removedText
				+"</p>");
	}

	public String removePenisModifier(GameCharacter owner, PenetrationModifier modifier) {
		if(!hasPenisModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		penisModifiers.remove(modifier);
		
		switch(modifier) {
			case RIBBED:
				return "<p>"
							+ "An soothing coolness builds up within [npc.namePos] [npc.penis], but before [npc.she] [npc.has] a chance to react, [npc.her] hard, fleshy ribs suddenly [style.boldShrink(disappear)]."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.penis] is no longer ribbed!)]"
						+ "</p>";
			case TENTACLED:
				return "<p>"
							+ "A soothing coolness builds up within [npc.namePos] [npc.penis], and before [npc.she] [npc.has] a chance to react, [npc.her] little wriggling tentacles [style.boldShrink(disappear)]."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.penis] is no longer covered with little tentacles!)]"
						+ "</p>";
			case BARBED:
				return "<p>"
							+ "A soothing coolness builds up within [npc.namePos] [npc.penis], but before [npc.she] [npc.has] a chance to react, [npc.her] little fleshy barbs [style.boldShrink(disappear)]."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.penis] is no longer barbed!)]"
						+ "</p>";
			case FLARED:
				return "<p>"
							+ "A soothing coolness builds up in the tip of [npc.namePos] [npc.penis], and before [npc.she] [npc.has] a chance to react, [npc.her] flared head [style.boldShrink(shrinks)] down into a regular, human-like one."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.penis] no longer has a flared head!)]"
						+ "</p>";
			case BLUNT:
				return "<p>"
							+ "A soothing coolness builds up in the tip of [npc.namePos] [npc.penis], and before [npc.she] [npc.has] a chance to react, [npc.her] blunt head [style.boldShrink(shrinks)] down into a regular, human-like one."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.penis] no longer has a blunt head!)]"
						+ "</p>";
			case KNOTTED:
				return "<p>"
							+ "A soothing coolness builds up in the base of [npc.namePos] [npc.penis], and before [npc.she] [npc.has] a chance to react, [npc.her] fat knot [style.boldShrink(shrinks)] and disappears."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.penis] is no longer knotted!)]"
						+ "</p>";
			case PREHENSILE:
				return "<p>"
							+ "A strange tingling sensation works its way up the length of [npc.namePos] [npc.penis], and [npc.she] suddenly becomes aware that it's [style.boldShrink(no longer prehensile)]."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.penis] is no longer prehensile!)]"
						+ "</p>";
			case SHEATHED:
				return "<p>"
							+ "A soothing coolness builds up in the base of [npc.namePos] [npc.penis], and before [npc.she] [npc.has] a chance to react, [npc.her] sheath [style.boldShrink(disappears)]."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.penis] is no longer sheathed!)]"
						+ "</p>";
			case TAPERED:
				return "<p>"
							+ "An intense warmth builds up within [npc.namePos] [npc.penis], but before [npc.she] [npc.has] a chance to react, the shaft suddenly [style.boldShrink(widens)]."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.penis] is no longer tapered!)]"
						+ "</p>";
			case VEINY:
				return "<p>"
							+ "An intense warmth builds up within [npc.namePos] [npc.penis], but before [npc.she] [npc.has] a chance to react, [npc.her] prominent veins [style.boldShrink(disappear)]."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.penis] is no longer veiny!)]"
						+ "</p>";
			case OVIPOSITOR:
				return "<p>"
							+ "A strange tingling sensation works its way up the length of [npc.namePos] [npc.penis], and [npc.she] can't help but let out [npc.a_moan+] as [npc.she] feels it transforming to"
								+ " [style.boldShrink(no longer function as an ovipositor)]."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.penis] is no longer an ovipositor!)]"
						+ "</p>";
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}
	
	public void clearPenisModifiers() {
		penisModifiers.clear();
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(owner==null || getType()==PenisType.NONE) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Penis.class) && getType().getRace().isFeralPartsAvailable());
	}
}
