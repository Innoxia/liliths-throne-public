package com.lilithsthrone.game.character.body;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.AbstractBreastType;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3
 * @author Innoxia
 */
public class Breast implements BodyPartInterface, Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MAXIMUM_BREAST_ROWS = 5;
	public static final int MAXIMUM_NIPPLES_PER_BREAST = 4;
	
	protected AbstractBreastType type;
	protected BreastShape shape;
	protected int size;
	protected int rows;
	protected int milkStorage;
	protected float milkStored;
	protected int milkRegeneration;
	protected int nippleCountPerBreast;
	
	protected Nipples nipples;
	protected FluidMilk milk;
	
	/**
	 * @param size in inches from bust to underbust using the UK system.
	 * @param lactation in mL.
	 */
	public Breast(AbstractBreastType type, BreastShape shape, int size, int milkStorage, int rows, int nippleSize, NippleShape nippleShape, int areolaeSize, int nippleCountPerBreast, float capacity, int elasticity, int plasticity, boolean virgin) {
		this.type = type;
		this.shape = shape;
		this.size = size;
		this.milkStorage = milkStorage;
		milkStored = milkStorage;
		milkRegeneration = FluidRegeneration.ONE_AVERAGE.getValue();
		this.rows = rows;
		this.nippleCountPerBreast = nippleCountPerBreast;
		
		nipples = new Nipples(type.getNippleType(), nippleSize, nippleShape, areolaeSize, Lactation.getLactationFromInt(milkStorage).getAssociatedWetness().getValue(), capacity, elasticity, plasticity, virgin);
		
		milk = new FluidMilk(type.getFluidType());
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
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(You already have "+shape.getName()+" breasts, so nothing happens...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already has "+shape.getName()+" breasts, so nothing happens...)]</p>");
			}
		}
		
		this.shape = shape;
		
		if(!owner.hasBreasts()) {
			if(owner.isPlayer()) {
				return "<p>"
							+ "A strange tingling feeling rises up into your chest, but as you don't have any breasts, nothing seems to happen...<br/>"
							+ "If you ever grow any, you will now have [style.boldSex("+shape.getName()+" breasts)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "A strange tingling feeling rises up into [npc.namePos] [npc.breasts+], but as [npc.she] doesn't have any breasts, nothing seems to happen...<br/>"
							+ "If [npc.she] ever grows any, [npc.name] will now have [style.boldSex("+shape.getName()+" breasts)]!"
						+ "</p>");
			}
		}
		
		if(owner.isPlayer()) {
			return "<p>"
						+ "A strange tingling feeling rises up into your [pc.breasts+], and before you know what's happening, they're shifting and rearranging themselves into a new shape...<br/>"
						+ "You now have [style.boldSex("+shape.getName()+" breasts)]!"
					+ "</p>";
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "A strange tingling feeling rises up into [npc.namePos] [npc.breasts+], and before [npc.she] knows what's happening, they're shifting and rearranging themselves into a new shape...<br/>"
						+ "[npc.Name] now has [style.boldSex("+shape.getName()+" breasts)]!"
					+ "</p>");
		}
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
		list.add(this.getShape().getName());
		
		return Util.randomItemFrom(list);
	}

	public boolean hasBreasts() {
		return size>=CupSize.AA.getMeasurement();
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
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(You already have the breasts of [pc.a_breastRace], so nothing happens...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already has the breasts of [npc.a_breastRace], so nothing happens...)]</p>");
			}
		}
		
		UtilText.transformationContentSB.setLength(0);
		
		UtilText.transformationContentSB.append(
				"<p>"
					+ "The front of [npc.namePos] torso suddenly feels extremely soft and sensitive, and [npc.she] can't help but let out [npc.a_moan+] as [npc.she] feels a transformation start to take place."
					+" [npc.Her] nipples and areolae tingle and harden, causing [npc.herHim] to pant and sweat as the intense transformation runs its course."
					+ " While there's no change to the [npc.breastFullDescription] which covers [npc.her] [npc.breasts],"
						+ " [npc.she] [npc.verb(feel)] #IFnpc.hasBreasts()#THENtheir#ELSEits#ENDIF interior structure shifting and changing into a new form."
					+ " After just a moment, the transformation ends, leaving [npc.herHim] with [npc.totalNipples] new nipples."
					+ "<br/>");
		
		// Parse existing content before transformation:
		String s = UtilText.parse(owner, UtilText.transformationContentSB.toString());
		UtilText.transformationContentSB.setLength(0);
		UtilText.transformationContentSB.append(s);
		this.type = type;
		nipples.setType(owner, type.getNippleType());
		milk.setType(type.getFluidType());
		owner.resetAreaKnownByCharacters(CoverableArea.BREASTS);
		owner.resetAreaKnownByCharacters(CoverableArea.NIPPLES);

		UtilText.transformationContentSB.append(type.getTransformationDescription(owner)+"</p>");
		
		return UtilText.parse(owner, UtilText.transformationContentSB.toString())
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
		
		if (sizeChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The size of your [pc.breasts] doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The size of [npc.namePos] [npc.breasts] doesn't change...)]</p>");
			}
		}
		
		String sizeDescriptor = getSize().getDescriptor();
		if (sizeChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You feel a tingling heat quickly spreading throughout your torso, and you can't help but let out [pc.a_moan+] as your "
							+ (hadBreasts
									? "[pc.breasts] swell up and [style.boldGrow(grow larger)].<br/>"
									: "chest swells up, and before you know what's happening, a pair of breasts have [style.boldGrow(grown)] out of your previously-flat torso.<br/>")
							+ "You now have [style.boldSex(" + sizeDescriptor + (getSize().getMeasurement()>CupSize.AA.getMeasurement()?", "+getSize().getCupSizeName()+"-cup":"") + " breasts)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] feels a tingling heat quickly spreading throughout [npc.her] torso, and [npc.she] can't help but let out [npc.a_moan+] as [npc.her] "
							+ (hadBreasts
									? "[npc.breasts] swell up and [style.boldGrow(grow larger)].<br/>"
									: "chest swells up, and before [npc.she] knows what's happening, a pair of breasts have [style.boldGrow(grown)] out of [npc.her] previously-flat torso.<br/>")
							+ "[npc.Name] now has [style.boldSex(" + sizeDescriptor + (getSize().getMeasurement()>CupSize.AA.getMeasurement()?", "+getSize().getCupSizeName()+"-cup":"") + " breasts)]!"
						+ "</p>");
			}
		} else {
			if (owner.isPlayer()) {
				return "<p>"
						+ "You feel a tingling heat quickly spreading throughout your torso, and you can't help but let out a frustrated [pc.moan] as your [pc.breasts] shrink down and [style.boldShrink(get smaller)].<br/>"
						+ (this.size==0
							? "You now have [style.boldSex(a completely flat chest)]!"
							:"You now have [style.boldSex(" + sizeDescriptor + (getSize().getMeasurement()>CupSize.AA.getMeasurement()?", "+getSize().getCupSizeName()+"-cup":"") + " breasts)]!")
					+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] feels a tingling heat quickly spreading throughout [npc.her] torso, and [npc.she] can't help but let out a frustrated [npc.moan] as [npc.her] [npc.breasts] shrink down and [style.boldShrink(get smaller)].<br/>"
							+ (this.size==0
								? "[npc.Name] now has [style.boldSex(a completely flat chest)]!"
								: "[npc.Name] now has [style.boldSex(" + sizeDescriptor + (getSize().getMeasurement()>CupSize.AA.getMeasurement()?", "+getSize().getCupSizeName()+"-cup":"") + " breasts)]!")
						+ "</p>");
			}
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
		
		if (lactationChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The amount of [pc.milk] that you're able to produce doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The amount of [npc.milk] that [npc.name] is able to produce doesn't change...)]</p>");
			}
		}
		
		String lactationDescriptor = getMilkStorage().getDescriptor();
		if (lactationChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You feel a strange bubbling and churning taking place deep within your [pc.breasts], and you can't help but let out [pc.a_moan+] as a few drops of [pc.milk] suddenly leak from your [pc.nipples];"
								+ " clear evidence that that your [pc.milk] production has [style.boldGrow(increased)].<br/>"
							+ "You are now able to produce [style.boldSex(" + lactationDescriptor + " [pc.milk])]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] feels a strange bubbling and churning taking place deep within [npc.her] [npc.breasts], and [npc.a_moan+] drifts out from between [npc.her] [npc.lips] as a few drops of [npc.milk] suddenly leak"
								+ " from [npc.her] [npc.nipples]; clear evidence that that [npc.her] [npc.milk] production has [style.boldGrow(increased)].<br/>"
							+ "[npc.Name] is now able to produce [style.boldSex(" + lactationDescriptor + " [npc.milk])]!"
						+ "</p>");
			}
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You feel a strange sucking sensation deep within your [pc.breasts], and you can't help but let out a shocked gasp as you realise that you're feeling your [pc.milk] production [style.boldShrink(drying up)].<br/>"
							+ "You are now able to produce [style.boldSex(" + lactationDescriptor + " [pc.milk])]."
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] feels a strange sucking sensation deep within [npc.her] [npc.breasts],"
								+ " and a frustrated sigh drifts out from between [npc.her] [npc.lips] as [npc.she] realises that [npc.sheIs] feeling [npc.her] [npc.milk] production [style.boldShrink(drying up)].<br/>"
							+ "[npc.Name] is now able to produce [style.boldSex(" + lactationDescriptor + " [npc.milk])]."
						+ "</p>");
			}
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
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'><i style='color:"+Colour.BASE_YELLOW_LIGHT.toWebHexString()+";'>"
							+ UtilText.returnStringAtRandom(
									lactationChange+"ml of your [pc.milk] squirts out of your [pc.nipples+].",
									lactationChange+"ml of [pc.milk+] leaks out of your [pc.nipples+].",
									lactationChange+"ml of [pc.milk+] drips out of your [pc.nipples+].")
						+ "</i>"
						+ (this.milkStored==0
							?"<br/><i>You now have no more [pc.milk] stored in your breasts!</i>"
							:"")
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p style='text-align:center;'><i style='color:"+Colour.BASE_YELLOW_LIGHT.toWebHexString()+";'>"
								+ UtilText.returnStringAtRandom(
										lactationChange+"ml of [npc.namePos] [npc.milk] squirts out of [npc.her] [npc.nipples+].",
										lactationChange+"ml of [npc.milk+] leaks out of [npc.namePos] [npc.nipples+].",
										lactationChange+"ml of [npc.milk+] drips out of [npc.namePos] [npc.nipples+].")
						+ "</i>"
						+ (this.milkStored==0
							?"<br/><i>[npc.Name] now has no more [npc.milk] stored in [npc.her] breasts!</i>"
							:"")
						+ "</p>");
			}
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
	 * Sets the milkRegeneration. Value is bound to >=0 && <=FluidRegeneration.FOUR_MAXIMUM.getMaximumValue()
	 */
	public String setLactationRegeneration(GameCharacter owner, int milkRegeneration) {
		int oldRegeneration = this.milkRegeneration;
		this.milkRegeneration = Math.max(0, Math.min(milkRegeneration, FluidRegeneration.FOUR_MAXIMUM.getValue()));
		int regenerationChange = this.milkRegeneration - oldRegeneration;
		
		if (regenerationChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(Your rate of [pc.milk] regeneration doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos] rate of [npc.milk] regeneration doesn't change...)]</p>");
			}
		}
		
		String regenerationDescriptor = getLactationRegeneration().getName();
		if (regenerationChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You feel an alarming bubbling and churning taking place deep within your [pc.breasts], and you can't help but let out [pc.a_moan+] as a few drops of [pc.milk] suddenly leak from your [pc.nipples];"
								+ " clear evidence that that your [pc.milk] regeneration has [style.boldGrow(increased)].<br/>"
							+ "Your rate of [pc.milk] regeneration is now [style.boldSex(" + regenerationDescriptor + ")]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] feels an alarming bubbling and churning taking place deep within [npc.her] [npc.breasts], and [npc.a_moan+] drifts out from between [npc.her] [npc.lips] as a few drops of [npc.milk] suddenly leak"
								+ " from [npc.her] [npc.nipples]; clear evidence that that [npc.her] [npc.milk] regeneration has [style.boldGrow(increased)].<br/>"
							+ "[npc.NamePos] rate of [npc.milk] regeneration is now [style.boldSex(" + regenerationDescriptor + ")]!"
						+ "</p>");
			}
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You feel a strange sucking sensation deep within your [pc.breasts], and you can't help but let out a shocked gasp as you realise that you're feeling your [pc.milk] regeneration [style.boldShrink(decreasing)].<br/>"
							+ "Your rate of [pc.milk] regeneration is now [style.boldSex(" + regenerationDescriptor + ")]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] feels a strange sucking sensation deep within [npc.her] [npc.breasts],"
								+ " and a frustrated sigh drifts out from between [npc.her] [npc.lips] as [npc.she] realises that [npc.sheIs] feeling [npc.her] [npc.milk] regeneration [style.boldShrink(decreasing)].<br/>"
							+ "[npc.NamePos] rate of [npc.milk] regeneration is now [style.boldSex(" + regenerationDescriptor + ")]!"
						+ "</p>");
			}
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
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The number of breast rows you have doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The number of breast rows [npc.name] has doesn't change...)]</p>");
			}
		}
		
		String transformation = "";
		
		int rowsDifference = Math.abs(rows - getRows());
		
		if (rows < getRows()) {
			if (owner.isPlayer()) {
				transformation =
						"<p>"
							+ "You feel a strange bubbling sensation within your [pc.breasts], and before you have time to react, your"
								+ (rowsDifference==1
									?"lowest pair of [pc.breasts]"
									:"lowest "+Util.intToString(rowsDifference)+" pairs of [pc.breasts]")
							+ " rapidly shrink away and [style.boldShrink(disappear)] into the [pc.skin] of your torso.<br/>"
							+ "You now have [style.boldSex("+ Util.intToString(rows) + " pair"+ (rows > 1 ? "s" : "") + " of " + (hasBreasts() ? "breasts" : "pecs") +")]!" 
						+ "</p>";
			} else {
				transformation = UtilText.parse(owner,
							"<p>"
								+ "[npc.Name] glances down worriedly at [npc.her] [npc.breasts], and before [npc.she] has time to react, [npc.her] "
								+ (rowsDifference==1
									?"lowest pair of [npc.breasts]"
									:"lowest "+Util.intToString(rowsDifference)+" pairs of [npc.breasts]")
								+ " rapidly shrink away and [style.boldShrink(disappear)] into the [npc.skin] of [npc.her] torso.<br/>"
								+ "[npc.Name] now has [style.boldSex("+ Util.intToString(rows) + " pair"+ (rows > 1 ? "s" : "") + " of " + (hasBreasts() ? "breasts" : "pecs") +")]!" 
							+ "</p>");
			}
			
		} else if (rows > getRows()) {
			if (owner.isPlayer()) {
				transformation =
						"<p>"
							+ "You feel a strange bubbling sensation within your [pc.breasts], and before you have time to react, "
							+ (rowsDifference==1
								?"an extra pair of [pc.breasts]"
								:Util.intToString(rowsDifference)+" extra pairs of [pc.breasts]")
							+ " rapidly [style.boldGrow(grow)] out of the [pc.skin] of your torso.<br/>"
							+ "You now have [style.boldSex("+ Util.intToString(rows) + " pair"+ (rows > 1 ? "s" : "") + " of " + (hasBreasts() ? "breasts" : "pecs") +")]!" 
						+ "</p>";
			} else {
				transformation = UtilText.parse(owner,
							"<p>"
								+ "[npc.Name] glances down worriedly at [npc.her] [npc.breasts], and before [npc.she] has time to react, "
								+ (rowsDifference==1
									?"an extra pair of [npc.breasts]"
									:Util.intToString(rowsDifference)+" extra pairs of [npc.breasts]")
								+ " rapidly [style.boldGrow(grow)] out of the [npc.skin] of [npc.her] torso.<br/>"
								+ "[npc.Name] now has [style.boldSex("+ Util.intToString(rows) + " pair"+ (rows > 1 ? "s" : "") + " of " + (hasBreasts() ? "breasts" : "pecs") +")]!" 
							+ "</p>");
			}
		}

		this.rows = rows;

		return transformation;
	}

	public boolean isFuckable() {
		return nipples.getOrificeNipples().getCapacity() != Capacity.ZERO_IMPENETRABLE && size >= CupSize.C.getMeasurement();
	}


	public int getNippleCountPerBreast() {
		return nippleCountPerBreast;
	}

	/**
	 * Minimum 1, maximum MAXIMUM_NIPPLES_PER_BREAST
	 */
	public String setNippleCountPerBreast(GameCharacter owner, int nippleCountPerBreast) {
		nippleCountPerBreast = Math.max(1, Math.min(nippleCountPerBreast, MAXIMUM_NIPPLES_PER_BREAST));
		
		if (this.nippleCountPerBreast == nippleCountPerBreast) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The number of [pc.nipples] on each of your " + (hasBreasts() ? "breasts" : "pecs") + " doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The number of [npc.nipples] [npc.name] has doesn't change...)]</p>");
			}
		}
		
		String transformation = "";
		
		if (nippleCountPerBreast < getNippleCountPerBreast()) {
			if (owner.isPlayer()) {
				transformation =
						"<p>"
							+ "You feel a strange tingling sensation running just beneath the surface of the [pc.breastSkin] that covers your [pc.breasts]."
							+ " A shocked gasp bursts from your mouth as the force shoots up into your [pc.nipples], and you feel some of them [style.boldShrink(shrinking)] into the flesh of your [pc.breasts].<br/>"
							+ "You now have [style.boldSex("+ Util.intToString(nippleCountPerBreast) + " "+ (nippleCountPerBreast > 1 ? "[pc.nipples]" : "[pc.nipple]") + " on each of your " + (hasBreasts() ? "breasts" : "pecs") +")]!" 
						+ "</p>";
			} else {
				transformation = UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] feels a strange tingling sensation running just beneath the surface of the [npc.breastSkin] that covers [npc.her] [npc.breasts]."
							+ " A shocked gasp bursts from [npc.her] mouth as the force shoots up into [npc.her] [npc.nipples], and [npc.she] continues [npc.moaning] as some of them [style.boldShrink(shrink)] into the flesh of [npc.her] [npc.breasts].<br/>"
							+ "[npc.Name] now has [style.boldSex("+ Util.intToString(nippleCountPerBreast) + " "+ (nippleCountPerBreast > 1 ? "[npc.nipples]" : "[npc.nipple]") + " on each of [npc.her] " + (hasBreasts() ? "breasts" : "pecs") +")]!" 
						+ "</p>");
			}
			
		} else if (nippleCountPerBreast > getNippleCountPerBreast()) {
			if (owner.isPlayer()) {
				transformation =
						"<p>"
							+ "You feel a strange tingling sensation running just beneath the surface of the [pc.breastSkin] that covers your [pc.breasts]."
							+ " A shocked gasp bursts from your mouth as the force shoots up into your [pc.nipples], and you continue [pc.moaning] as you feel new ones [style.boldGrow(growing)] out of the flesh of your [pc.breasts].<br/>"
							+ "You now have [style.boldSex("+ Util.intToString(nippleCountPerBreast) + " "+ (nippleCountPerBreast > 1 ? "[pc.nipples]" : "[pc.nipple]") + " on each of your " + (hasBreasts() ? "breasts" : "pecs") +")]!" 
						+ "</p>";
			} else {
				transformation = UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] feels a strange tingling sensation running just beneath the surface of the [npc.breastSkin] that covers [npc.her] [npc.breasts]."
							+ " A shocked gasp bursts from [npc.her] mouth as the force shoots up into [npc.her] [npc.nipples],"
								+ " and [npc.she] continues [npc.moaning] as [npc.she] feels new ones [style.boldGrow(growing)] out of the flesh of [npc.her] [npc.breasts].<br/>"
							+ "[npc.Name] now has [style.boldSex("+ Util.intToString(nippleCountPerBreast) + " "+ (nippleCountPerBreast > 1 ? "[npc.nipples]" : "[npc.nipple]") + " on each of [npc.her] " + (hasBreasts() ? "breasts" : "pecs") +")]!" 
						+ "</p>");
			}
		}
		
		this.nippleCountPerBreast = nippleCountPerBreast;

		return transformation;
	}

}
