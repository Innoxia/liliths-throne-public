package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractBreastType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeShape;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.3.7
 * @author Innoxia
 */
public class Breast implements BodyPartInterface {
	
	public static final int MAXIMUM_BREAST_ROWS = 6;
	public static final int MAXIMUM_NIPPLES_PER_BREAST = 4;
	
	protected AbstractBreastType type;
	protected BreastShape shape;
	protected int size;
	protected int rows;
	protected int milkStorage;
	protected float milkStored;
	/** Measured in mL/day */
	protected int milkRegeneration;
	protected int nippleCountPerBreast;
	
	protected Nipples nipples;
	protected FluidMilk milk;
	
	/**
	 * @param size in inches from bust to underbust using the UK system.
	 * @param lactation in mL.
	 */
	public Breast(AbstractBreastType type, BreastShape shape, int size, int milkStorage, int rows,
			int nippleSize, NippleShape nippleShape, int areolaeSize, AreolaeShape areolaeShape, int nippleCountPerBreast, float capacity, int depth, int elasticity, int plasticity, boolean virgin) {
		this.type = type;
		this.shape = shape;
		this.size = size;
		this.milkStorage = milkStorage;
		milkStored = milkStorage;
		milkRegeneration = FluidRegeneration.ONE_AVERAGE.getMedianRegenerationValuePerDay();
		this.rows = rows;
		this.nippleCountPerBreast = nippleCountPerBreast;
		
		nipples = new Nipples(type.getNippleType(), nippleSize, nippleShape, areolaeSize, areolaeShape, Lactation.getLactationFromInt(milkStorage).getAssociatedWetness().getValue(), capacity, depth, elasticity, plasticity, virgin, false);
		
		milk = new FluidMilk(type.getFluidType(), false);
	}
	
	@Override
	public AbstractBreastType getType() {
		return type;
	}

	public BreastShape getShape() {
		return shape;
	}

	public String setShape(GameCharacter owner, BreastShape shape) {
		if (shape == getShape()) {
			if(owner==null) {
				return "";
			}
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.has] "+shape.getDescriptor()+" breasts, so nothing happens...)]</p>");
		}
		
		this.shape = shape;
		
		if(!owner.hasBreasts()) {
			return UtilText.parse(owner,
					"<p>"
						+ "A strange tingling feeling rises up into [npc.namePos] [npc.breasts+], but as [npc.she] [npc.do]n't have any breasts, nothing seems to happen...<br/>"
						+ "If [npc.she] ever [npc.verb(grow)] any, [npc.name] will now have [style.boldSex("+shape.getDescriptor()+" breasts)]!"
					+ "</p>");
		}
		
		return UtilText.parse(owner,
				"<p>"
					+ "A strange tingling feeling rises up into [npc.namePos] [npc.breasts+], and before [npc.she] [npc.verb(know)] what's happening, they've transformed into a new shape...<br/>"
					+ "[npc.Name] now [npc.has] [style.boldSex("+shape.getDescriptor()+" breasts)]!"
				+ "</p>");
		
	}

	public Nipples getNipples() {
		return nipples;
	}

	public FluidMilk getMilk() {
		return milk;
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
		
		if(nippleCountPerBreast == 4) {
			list.add("quad-nippled");
		} else if(nippleCountPerBreast == 3) {
			list.add("tri-nippled");
		} else if(nippleCountPerBreast == 2) {
			list.add("dual-nippled");
		} 
		
		list.add(type.getDescriptor(owner));
		list.add(this.getSize().getDescriptor());
		list.add(this.getShape().getDescriptor());
		
		return Util.randomItemFrom(list);
	}

	public boolean hasBreasts() {
		return size>=CupSize.getMinimumCupSizeForBreasts().getMeasurement();
	}

	public String setType(GameCharacter owner, AbstractBreastType type) {
		if(!Main.game.isStarted() || owner==null) {
			this.type = type;
			nipples.setType(owner, type.getNippleType());
			milk.setType(type.getFluidType());
			if(owner!=null) {
				owner.resetAreaKnownByCharacters(CoverableArea.BREASTS);
				owner.resetAreaKnownByCharacters(CoverableArea.NIPPLES);
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.has] the breasts of [npc.a_breastRace], so nothing happens...)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(
				"<p>"
					+ "The front of [npc.namePos] torso suddenly feels extremely soft and sensitive, and [npc.she] can't help but let out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a transformation start to take place."
					+" [npc.Her] nipples and areolae tingle and harden, causing [npc.herHim] to pant and let out a lewd [npc.moan] as the intense transformation runs its course."
					+ " While there's no change to the [npc.breastFullDescription] which covers [npc.her] [npc.breasts],"
						+ " [npc.she] [npc.verb(feel)] #IFnpc.hasBreasts()#THENtheir#ELSEits#ENDIF interior structure shifting and changing into a new form."
					+ " After just a moment, the transformation ends, leaving [npc.herHim] with [npc.totalNipples] new nipples."
					+ "<br/>");
		
		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		this.type = type;
		nipples.setType(owner, type.getNippleType());
		milk.setType(type.getFluidType());
		owner.resetAreaKnownByCharacters(CoverableArea.BREASTS);
		owner.resetAreaKnownByCharacters(CoverableArea.NIPPLES);

		sb.append(type.getTransformationDescription(owner)+"</p>");
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
				+ owner.postTransformationCalculation(false)
				+ "</p>";
	}


	// Size:

	public CupSize getSize() {
		return CupSize.getCupSizeFromInt(size);
	}

	public int getRawSizeValue() {
		return size;
	}

	/**
	 * Sets the raw size value. Value is bound to >=0 && <=CupSize.MAXIMUM.getMeasurement()
	 * 
	 * @param size Value to set size to.
	 * @return description of size change
	 */
	public String setSize(GameCharacter owner, int size) {
		boolean hadBreasts = hasBreasts();
		
		int oldSize = this.size;
		this.size = Math.max(0, Math.min(size, CupSize.getMaximumCupSize().getMeasurement()));
		int sizeChange = this.size - oldSize;
		if(owner==null) {
			this.size = size;
			return "";
		}
		
		if(!isAbleToIncubateEggs() && owner.getIncubationLitter(SexAreaOrifice.NIPPLE)!=null) {
			this.size = CupSize.getMinimumCupSizeForEggIncubation().getMeasurement();
			return UtilText.parse(owner, "<p style='text-align:center;'>Due to the fact that [npc.namePos] breasts are incubating eggs,"
					+ " [style.colourMinorBad(their size cannot be reduced past "+CupSize.getMinimumCupSizeForEggIncubation().getCupSizeName()+"-cups)]!</p>");
		}

		if (sizeChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The size of [npc.namePos] [npc.breasts] doesn't change...)]</p>");
		}
		
		String sizeDescriptor = getSize().getDescriptor();
		if (sizeChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(feel)] a tingling heat quickly spreading throughout [npc.her] torso, and [npc.she] can't help but let out [npc.a_moan+] as [npc.her] "
						+ (hadBreasts
								? "[npc.breasts] swell up and [style.boldGrow(grow larger)].<br/>"
								: "chest swells up, and before [npc.she] [npc.verb(know)] what's happening, a pair of breasts have [style.boldGrow(grown)] out of [npc.her] previously-flat torso.<br/>")
						+ "[npc.Name] now [npc.has] [style.boldSex(" + sizeDescriptor + (getSize().getMeasurement()>CupSize.AA.getMeasurement()?", "+getSize().getCupSizeName()+"-cup":"") + " breasts)]!"
					+ "</p>");
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(feel)] a tingling heat quickly spreading throughout [npc.her] torso,"
							+ " and [npc.she] can't help but let out a frustrated [npc.moan] as [npc.her] [npc.breasts] shrink down and [style.boldShrink(get smaller)].<br/>"
						+ (this.size==0
							? "[npc.Name] now [npc.has] [style.boldSex(a completely flat chest)]!"
							: "[npc.Name] now [npc.has] [style.boldSex(" + sizeDescriptor + (getSize().getMeasurement()>CupSize.AA.getMeasurement()?", "+getSize().getCupSizeName()+"-cup":"") + " breasts)]!")
					+ "</p>");
		}
	}

	// Lactation:

	public Lactation getMilkStorage() {
		return Lactation.getLactationFromInt(milkStorage);
	}

	public int getRawMilkStorageValue() {
		return milkStorage;
	}

	/**
	 * Sets the milkStorage. Value is bound to >=0 && <=Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue()
	 */
	public String setMilkStorage(GameCharacter owner, int milkStorage) {
		int oldLactation = this.milkStorage;
		this.milkStorage = Math.max(0, Math.min(milkStorage, Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue()));
		int lactationChange = this.milkStorage - oldLactation;
		if(owner==null) {
			return "";
		}
		
		if (lactationChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The amount of [npc.milk] that [npc.nameIsFull] able to produce doesn't change...)]</p>");
		}
		
		String lactationDescriptor = getMilkStorage().getDescriptor();
		if (lactationChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(feel)] a strange bubbling and churning taking place deep within [npc.her] [npc.breasts], and [npc.a_moan+] drifts out from between [npc.her] [npc.lips] as a few drops of [npc.milk] suddenly leak"
							+ " from [npc.her] [npc.nipples]; clear evidence that [npc.her] [npc.milk] production has [style.boldGrow(increased)].<br/>"
						+ "[npc.NameIsFull] now able to produce [style.boldSex(" + lactationDescriptor + " [npc.milk])]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(feel)] a strange sucking sensation deep within [npc.her] [npc.breasts],"
							+ " and a frustrated sigh drifts out from between [npc.her] [npc.lips] as [npc.she] [npc.verb(realise)] that [npc.sheIs] feeling [npc.her] [npc.milk] production [style.boldShrink(drying up)].<br/>"
						+ "[npc.NameIsFull] now able to produce [style.boldSex(" + lactationDescriptor + " [npc.milk])]."
					+ "</p>");
		}
	}
	
	// Stored milk:

	public Lactation getStoredMilk() {
		return Lactation.getLactationFromInt((int) milkStored);
	}
	
	public float getRawStoredMilkValue() {
		return milkStored;
	}

	/**
	 * Sets the milkStorage. Value is bound to >=0 && <=getRawMilkStorageValue()
	 */
	public String setStoredMilk(GameCharacter owner, float milkStored) {
		float oldStoredMilk = this.milkStored;
		this.milkStored = Math.max(0, (Math.min(milkStored, getRawMilkStorageValue())));
		float lactationChange = oldStoredMilk - this.milkStored;
		
		if(owner==null) {
			return "";
		}
		
		if (lactationChange <= 0) {
			return "";
		} else {
			return UtilText.parse(owner,
					"<p style='text-align:center;'><i style='color:"+PresetColour.BASE_YELLOW_LIGHT.toWebHexString()+";'>"
							+ UtilText.returnStringAtRandom(
									Units.fluid(lactationChange, Units.UnitType.LONG)+" of [npc.namePos] [npc.milk] squirts out of [npc.her] [npc.nipples+].",
									Units.fluid(lactationChange, Units.UnitType.LONG)+" of [npc.milk+] squirts out of [npc.namePos] [npc.nipples+].",
									Units.fluid(lactationChange, Units.UnitType.LONG)+" of [npc.namePos] [npc.milk] leaks out of [npc.her] [npc.nipples+].",
									Units.fluid(lactationChange, Units.UnitType.LONG)+" of [npc.milk+] leaks out of [npc.namePos] [npc.nipples+].")
					+ "</i>"
					+ (this.milkStored==0
						?"<br/><i>[npc.Name] now [npc.has] no more [npc.milk] stored in [npc.her] breasts!</i>"
						:"")
					+ "</p>");
		}
	}

	// Regeneration:

	public FluidRegeneration getLactationRegeneration() {
		return FluidRegeneration.getFluidRegenerationFromInt(milkRegeneration);
	}

	public int getRawLactationRegenerationValue() {
		return milkRegeneration;
	}

	/**
	 * Sets the milkRegeneration. Value is bound to >=0 && <=FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay()
	 */
	public String setLactationRegeneration(GameCharacter owner, int milkRegeneration) {
		int oldRegeneration = this.milkRegeneration;
		this.milkRegeneration = Math.max(0, Math.min(milkRegeneration, FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay()));
		int regenerationChange = this.milkRegeneration - oldRegeneration;

		if(owner==null) {
			return "";
		}
		
		if (regenerationChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos] rate of [npc.milk] regeneration doesn't change...)]</p>");
		}
		
		String regenerationDescriptor = getLactationRegeneration().getName();
		if (regenerationChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(feel)] an alarming bubbling and churning taking place deep within [npc.her] [npc.breasts], and [npc.a_moan+] drifts out from between [npc.her] [npc.lips] as a few drops of [npc.milk] suddenly leak"
							+ " from [npc.her] [npc.nipples]; clear evidence that [npc.her] [npc.milk] regeneration has [style.boldGrow(increased)].<br/>"
						+ "[npc.NamePos] rate of [npc.milk] regeneration is now [style.boldSex(" + regenerationDescriptor + ")] ("+Units.fluid(milkRegeneration)+"/day)!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(feel)] a strange sucking sensation deep within [npc.her] [npc.breasts],"
							+ " and a frustrated sigh drifts out from between [npc.her] [npc.lips] as [npc.she] realises that [npc.sheIs] feeling [npc.her] [npc.milk] regeneration [style.boldShrink(decreasing)].<br/>"
						+ "[npc.NamePos] rate of [npc.milk] regeneration is now [style.boldSex(" + regenerationDescriptor + ")] ("+Units.fluid(milkRegeneration)+"/day)!"
					+ "</p>");
		}
	}
	
	
	
	// Rows:
	
	public int getRows() {
		return rows;
	}

	public String setRows(GameCharacter owner, int rows) {
		rows = Math.max(1, Math.min(rows, MAXIMUM_BREAST_ROWS));
		
		if(owner==null) {
			this.rows = rows;
			return "";
		}
		
		if(rows == getRows()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The number of breast rows [npc.nameHasFull] doesn't change...)]</p>");
		}
		
		String transformation = "";
		
		int rowsDifference = Math.abs(rows - getRows());
		
		if (rows < getRows()) {
			transformation = UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] [npc.verb(glance)] down worriedly as [npc.she] [npc.verb(feel)] a strange tightening sensation in [npc.her] torso, and before [npc.sheHasFull] time to react, [npc.her] "
							+ (rowsDifference==1
								?"lowest pair of [npc.breasts]"
								:"lowest "+Util.intToString(rowsDifference)+" pairs of [npc.breasts]")
							+ " rapidly shrink away and [style.boldShrink(disappear)] into the [npc.skin] of [npc.her] torso.<br/>"
							+ "[npc.Name] now [npc.has] [style.boldSex("+ Util.intToString(rows) + " pair"+ (rows > 1 ? "s" : "") + " of " + (hasBreasts() ? "breasts" : "pecs") +")]!" 
						+ "</p>");
			
		} else if (rows > getRows()) {
			transformation = UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] [npc.verb(glance)] down in surprise as [npc.she] [npc.verb(feel)] a strange swelling sensation in [npc.her] torso, and before [npc.sheHasFull] time to react, "
							+ (rowsDifference==1
								?"an extra pair of [npc.breasts]"
								:Util.intToString(rowsDifference)+" extra pairs of [npc.breasts]")
							+ " rapidly [style.boldGrow(grow)] out of the [npc.skin] of [npc.her] torso.<br/>"
							+ "[npc.Name] now [npc.has] [style.boldSex("+ Util.intToString(rows) + " pair"+ (rows > 1 ? "s" : "") + " of " + (hasBreasts() ? "breasts" : "pecs") +")]!" 
						+ "</p>");
		}

		this.rows = rows;

		return transformation;
	}

	public boolean isFuckable() {
		return nipples.getOrificeNipples().getCapacity() != Capacity.ZERO_IMPENETRABLE
				&& size >= CupSize.getMinimumCupSizeForPenetration().getMeasurement()
				&& Main.game.isNipplePenEnabled();
	}

	public boolean isAbleToIncubateEggs() {
		return this.size>=CupSize.getMinimumCupSizeForEggIncubation().getMeasurement();
	}

	public int getNippleCountPerBreast() {
		return nippleCountPerBreast;
	}

	/**
	 * Minimum 1, maximum MAXIMUM_NIPPLES_PER_BREAST
	 */
	public String setNippleCountPerBreast(GameCharacter owner, int nippleCountPerBreast) {
		nippleCountPerBreast = Math.max(1, Math.min(nippleCountPerBreast, MAXIMUM_NIPPLES_PER_BREAST));

		if(owner==null) {
			this.nippleCountPerBreast = nippleCountPerBreast;
			return "";
		}
		
		if (this.nippleCountPerBreast == nippleCountPerBreast) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The number of [npc.nipples] [npc.nameHasFull] doesn't change...)]</p>");
		}
		
		String transformation = "";
		
		if (nippleCountPerBreast < getNippleCountPerBreast()) {
			transformation = UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(feel)] a strange tingling sensation running just beneath the surface of the [npc.breastSkin] that covers [npc.her] [npc.breasts]."
						+ " A shocked gasp bursts from [npc.her] mouth as the force shoots up into [npc.her] [npc.nipples],"
							+ " and [npc.she] [npc.verb(continue)] [npc.moaning] as some of them [style.boldShrink(shrink)] into the flesh of [npc.her] [npc.breasts].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex("+ Util.intToString(nippleCountPerBreast) + " "+ (nippleCountPerBreast > 1 ? "[npc.nipples]" : "[npc.nipple(true)]") + " on each of [npc.her] " + (hasBreasts() ? "breasts" : "pecs") +")]!" 
					+ "</p>");
			
		} else if (nippleCountPerBreast > getNippleCountPerBreast()) {
			transformation = UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(feel)] a strange tingling sensation running just beneath the surface of the [npc.breastSkin] that covers [npc.her] [npc.breasts]."
						+ " A shocked gasp bursts from [npc.her] mouth as the force shoots up into [npc.her] [npc.nipples],"
							+ " and [npc.she] [npc.verb(continue)] [npc.moaning] as [npc.she] feels new ones [style.boldGrow(growing)] out of the flesh of [npc.her] [npc.breasts].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex("+ Util.intToString(nippleCountPerBreast) + " "+ (nippleCountPerBreast > 1 ? "[npc.nipples]" : "[npc.nipple(true)]") + " on each of [npc.her] " + (hasBreasts() ? "breasts" : "pecs") +")]!" 
					+ "</p>");
			
		}
		
		this.nippleCountPerBreast = nippleCountPerBreast;

		return transformation;
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Breast.class) && getType().getRace().isFeralPartsAvailable());
	}

}
