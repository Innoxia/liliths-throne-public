package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractBreastType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeShape;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.1
 * @version 0.3.7
 * @author Innoxia
 */
public class BreastCrotch implements BodyPartInterface {
	
	public static final int MAXIMUM_BREAST_ROWS = 6;
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
	
	public BreastCrotch(AbstractBreastType type, BreastShape shape, int size, int milkStorage, int rows,
			int nippleSize, NippleShape nippleShape, int areolaeSize, AreolaeShape areolaeShape, int nippleCountPerBreast, float capacity, int depth, int elasticity, int plasticity, boolean virgin) {
		this.type = type;
		this.shape = shape;
		this.size = size;
		this.milkStorage = milkStorage;
		milkStored = milkStorage;
		milkRegeneration = FluidRegeneration.ONE_AVERAGE.getMedianRegenerationValuePerDay();
		this.rows = rows;
		this.nippleCountPerBreast = nippleCountPerBreast;
		
		nipples = new Nipples(type.getNippleType(), nippleSize, nippleShape, areolaeSize, areolaeShape, Lactation.getLactationFromInt(milkStorage).getAssociatedWetness().getValue(), capacity, depth, elasticity, plasticity, virgin, true);
		
		milk = new FluidMilk(type.getFluidType(), true);
	}
	
	@Override
	public AbstractBreastType getType() {
		return type;
	}

	public boolean isVisibleThroughClothing(GameCharacter owner) {
		if(owner.getLegConfiguration()==LegConfiguration.BIPEDAL) { // Non-bipedal clothing is assumed to conceal the body so that crotch-boobs are not visible.
			return size>=CupSize.A.getMeasurement();
		} else {
			return false;
		}
	}
	
	public BreastShape getShape() {
		return shape;
	}
	
	public String setShape(GameCharacter owner, BreastShape shape) {
		if (shape == getShape()) {
			if(owner==null) {
				return "";
			}
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already has "+shape.getDescriptor()+" [npc.crotchBoobs], so nothing happens...)]</p>");
		}
		
		this.shape = shape;
		if(shape!=BreastShape.UDDERS && this.getRows()==0) {
			this.rows = 1;
		}
		if(shape==BreastShape.UDDERS && this.getRows()==0 && this.getNippleCountPerBreast()==1) {
			this.nippleCountPerBreast = 2;
		}
		
		if(!owner.hasBreastsCrotch()) {
			return UtilText.parse(owner,
					"<p>"
						+ "A strange tingling feeling rises up into [npc.namePos] crotch, but as [npc.she] [npc.do]n't have any udders, nothing seems to happen...<br/>"
						+ "If [npc.she] ever [npc.verb(grow)] any, [npc.name] will now have [style.boldSex("+shape.getDescriptor()+" udders)]!"
					+ "</p>");
		}
		return UtilText.parse(owner,
				"<p>"
					+ "A strange tingling feeling rises up into [npc.namePos] crotch, and before [npc.she] [npc.verb(know)] what's happening, [npc.her] [npc.crotchBoobs] transform into a new shape...<br/>"
					+ "[npc.Name] now [npc.has] [style.boldSex("+shape.getDescriptor()+" [npc.crotchBoobs])]!"
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
		if(getShape()==BreastShape.UDDERS && this.getRows()==0) {
			return getNameSingular(gc);
		}
		return getNamePlural(gc);
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		if(getShape()==BreastShape.UDDERS) {
			return "udder";
		}
		return type.getCrotchNameSingular(gc);
	}

	@Override
	public String getNamePlural(GameCharacter gc) {
		if(getShape()==BreastShape.UDDERS) {
			return "udders";
		}
		return type.getCrotchNamePlural(gc);
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
		
		if(owner.getBodyMaterial().getPartDescriptors()!=null && !owner.getBodyMaterial().getPartDescriptors().isEmpty()) {
			list.add(Util.randomItemFrom(owner.getBodyMaterial().getPartDescriptors()));
		}
		
		return Util.randomItemFrom(list);
	}

	public boolean hasBreasts() {
		return type!=BreastType.NONE;
	}

	public String setType(GameCharacter owner, AbstractBreastType type) {
		this.shape = Util.randomItemFrom(RacialBody.valueOfRace(type.getRace()).getBreastCrotchShapes());
		
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
		
		if(type==BreastType.NONE && owner.getIncubationLitter(SexAreaOrifice.NIPPLE_CROTCH)!=null) {
			return UtilText.parse(owner, "<p style='text-align:center;'>Due to the fact that [npc.nameIsFull] incubating eggs in [npc.her] [npc.crotchBoobs],"
					+ " [style.colourMinorBad("+(getShape()==BreastShape.UDDERS && this.getRows()==0?"it":"they")+" cannot be removed)]!</p>");
		}
		
		if (type == getType()) {
			if(type.equals(BreastType.NONE)) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.verb(lack)] [npc.crotchBoobs], so nothing happens...)]</p>");
			}
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.has] the [npc.crotchBoobs] of [npc.a_breastRace], so nothing happens...)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(type.equals(BreastType.NONE)) { // Removal:
			sb.append(
					"<p>"
						+ "The area above [npc.namePos] crotch suddenly feels extremely soft and sensitive, and [npc.she] can't help but let out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a transformation start to take place."
						+" [npc.Her] [npc.crotchBoobs] tingle and soften, and, with [npc.her] nipples and areolae leading the way, they quickly shrink down and disappear back into the [npc.skin] covering [npc.her] lower stomach."
						+ "<br/>");
			
		} else if(this.getType().equals(BreastType.NONE)) { // New addition:
			sb.append(
					"<p>"
						+ "The area above [npc.namePos] crotch suddenly feels extremely soft and sensitive, and [npc.she] can't help but let out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a transformation start to take place."
						+ " [npc.CrotchBoobsRows] hard little nubs start to grow out of the [npc.skin] covering [npc.her] lower stomach,"
							+ " and [npc.she] can't help but let out a surprised gasp as they quickly swell out into [npc.crotchBoobsSize] [npc.crotchBoobs]."
						+ "<br/>");
			
			
		} else {
			sb.append(
					"<p>"
						+ "The area above [npc.namePos] crotch suddenly feels extremely soft and sensitive, and [npc.she] can't help but let out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a transformation start to take place."
						+" The nipples and areolae on [npc.her] [npc.crotchBoobs] tingle and harden, causing [npc.herHim] to pant and let out a lewd [npc.moan] as the intense transformation runs its course."
						+ " While there's no change to their outwards appearance, [npc.she] [npc.verb(feel)] the interior structure of [npc.her] [npc.crotchBoobs] shifting and changing into a new form."
						+ " After just a moment, the transformation ends, leaving [npc.herHim] with [npc.totalNipples] new nipples."
						+ "<br/>");
		}
		
		
		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		this.type = type;
		nipples.setType(owner, type.getNippleType());
		milk.setType(type.getFluidType());
		owner.resetAreaKnownByCharacters(CoverableArea.BREASTS);
		owner.resetAreaKnownByCharacters(CoverableArea.NIPPLES);

		sb.append(type.getTransformationCrotchDescription(owner)+"</p>");
		
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
		int oldSize = this.size;
		this.size = Math.max(0, Math.min(size, CupSize.getMaximumCupSize().getMeasurement()));
		int sizeChange = this.size - oldSize;
		if(owner==null) {
			this.size = size;
			return "";
		}

		if(!isAbleToIncubateEggs() && owner.getIncubationLitter(SexAreaOrifice.NIPPLE_CROTCH)!=null) {
			this.size = CupSize.getMinimumCupSizeForEggIncubation().getMeasurement();
			return UtilText.parse(owner, "<p style='text-align:center;'>Due to the fact that [npc.namePos] [npc.crotchBoobs] are incubating eggs,"
					+ " [style.colourMinorBad("+(getShape()==BreastShape.UDDERS && this.getRows()==0?"its":"their")+" size cannot be reduced past "+CupSize.getMinimumCupSizeForEggIncubation().getCupSizeName()+"-cups)]!</p>");
		}

		if (sizeChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The size of [npc.namePos] [npc.crotchBoobs] doesn't change...)]</p>");
		}
		
		if (sizeChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(feel)] a tingling heat quickly spreading throughout [npc.her] lower torso, and [npc.she] can't help but let out [npc.a_moan+] as [npc.her] [npc.crotchBoobs] swell up and [style.boldGrow(grow larger)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex([npc.crotchBoobsSize] [npc.crotchBoobs])]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(feel)] a tingling heat quickly spreading throughout [npc.her] lower torso,"
							+ " and [npc.she] can't help but let out a frustrated [npc.moan] as [npc.her] [npc.crotchBoobs] shrink down and [style.boldShrink(get smaller)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex([npc.crotchBoobsSize] [npc.crotchBoobs])]!"
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The amount of [npc.crotchMilk] that [npc.nameIsFull] able to produce doesn't change...)]</p>");
		}
		
		String lactationDescriptor = getMilkStorage().getDescriptor();
		if (lactationChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(feel)] a strange bubbling and churning taking place deep within [npc.her] [npc.crotchBoobs], and [npc.a_moan+] drifts out from between [npc.her] [npc.lips] as a few drops of [npc.crotchMilk] suddenly leak"
							+ " from [npc.her] [npc.crotchNipples]; clear evidence that [npc.her] [npc.crotchMilk] production has [style.boldGrow(increased)].<br/>"
						+ "[npc.NameIsFull] now able to produce [style.boldSex(" + lactationDescriptor + " [npc.crotchMilk])]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(feel)] a strange sucking sensation deep within [npc.her] [npc.crotchBoobs],"
							+ " and a frustrated sigh drifts out from between [npc.her] [npc.lips] as [npc.she] [npc.verb(realise)] that [npc.sheIs] feeling [npc.her] [npc.crotchMilk] production [style.boldShrink(drying up)].<br/>"
						+ "[npc.NameIsFull] now able to produce [style.boldSex(" + lactationDescriptor + " [npc.crotchMilk])]."
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
									Units.fluid(lactationChange, Units.UnitType.LONG)+" of [npc.namePos] [npc.crotchMilk] squirts out of [npc.her] [npc.crotchNipples+].",
									Units.fluid(lactationChange, Units.UnitType.LONG)+" of [npc.crotchMilk+] squirts out of [npc.namePos] [npc.crotchNipples+].",
									Units.fluid(lactationChange, Units.UnitType.LONG)+" of [npc.namePos] [npc.crotchMilk] leaks out of [npc.her] [npc.crotchNipples+].",
									Units.fluid(lactationChange, Units.UnitType.LONG)+" of [npc.crotchMilk+] leaks out of [npc.namePos] [npc.crotchNipples+].")
					+ "</i>"
					+ (this.milkStored==0
						?"<br/><i>[npc.Name] now [npc.has] no more [npc.crotchMilk] stored in [npc.her] [npc.crotchBoobs]!</i>"
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos] rate of [npc.crotchMilk] regeneration doesn't change...)]</p>");
		}
		
		String regenerationDescriptor = getLactationRegeneration().getName();
		if (regenerationChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(feel)] an alarming bubbling and churning taking place deep within [npc.her] [npc.crotchBoobs], and [npc.a_moan+] drifts out from between [npc.her] [npc.lips] as a few drops of [npc.crotchMilk] suddenly leak"
							+ " from [npc.her] [npc.crotchNipples]; clear evidence that [npc.her] [npc.crotchMilk] regeneration has [style.boldGrow(increased)].<br/>"
						+ "[npc.NamePos] rate of [npc.crotchMilk] regeneration is now [style.boldSex(" + regenerationDescriptor + ")] ("+Units.fluid(milkRegeneration)+"/day)!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(feel)] a strange sucking sensation deep within [npc.her] [npc.crotchBoobs],"
							+ " and a frustrated sigh drifts out from between [npc.her] [npc.lips] as [npc.she] realises that [npc.sheIs] feeling [npc.her] [npc.crotchMilk] regeneration [style.boldShrink(decreasing)].<br/>"
						+ "[npc.NamePos] rate of [npc.crotchMilk] regeneration is now [style.boldSex(" + regenerationDescriptor + ")] ("+Units.fluid(milkRegeneration)+"/day)!"
					+ "</p>");
		}
	}
	
	
	
	// Rows:
	
	public int getRows() {
		return rows;
	}

	public String setRows(GameCharacter owner, int rows) {
		if(this.getShape()==BreastShape.UDDERS) { // Udders can be configured into one single udder (as '0' rows):
			rows = Math.max(0, Math.min(rows, MAXIMUM_BREAST_ROWS));
		} else {
			rows = Math.max(1, Math.min(rows, MAXIMUM_BREAST_ROWS));
		}
		
		if(rows==0 && this.nippleCountPerBreast==1) {
			this.nippleCountPerBreast = 2;
		}
		
		if(owner==null) {
			this.rows = rows;
			return "";
		}
		
		if(rows == getRows()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The number of breast rows [npc.name] has doesn't change...)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();
		
		int rowsDifference = Math.abs(rows - getRows());
		
		if (rows < getRows()) {
			sb.append("<p>"
							+ "[npc.Name] [npc.verb(let)] out a worried gasp as [npc.she] [npc.verb(feel)] a strange tightening sensation just above [npc.her] groin, and before [npc.sheHasFull] time to react, [npc.her] ");
			
			if(this.getShape()==BreastShape.UDDERS) {
				sb.append((rowsDifference==1
						?"highest pair of [npc.crotchNipples]"
						:"highest "+Util.intToString(rowsDifference)+" pairs of [npc.crotchNipples]"));
				sb.append(" rapidly shrink away and [style.boldShrink(disappear)] into [npc.her] udders.<br/>");
			} else {
				sb.append((rowsDifference==1
						?"highest pair of [npc.crotchBoobs]"
						:"highest "+Util.intToString(rowsDifference)+" pairs of [npc.crotchBoobs]"));
				sb.append(" rapidly shrink away and [style.boldShrink(disappear)] into the [npc.skin] of [npc.her] stomach.<br/>");
			}
			
			
			
		} else if (rows > getRows()) {
			sb.append("<p>"
						+ "[npc.Name] [npc.verb(let)] out a surprised gasp as [npc.she] [npc.verb(feel)] a strange swelling sensation just above [npc.her] groin, and before [npc.sheHasFull] time to react, ");
			
			if(this.getShape()==BreastShape.UDDERS) {
				sb.append((rowsDifference==1
						?"an extra pair of [npc.crotchNipples]"
						:Util.intToString(rowsDifference)+" extra pairs of [npc.crotchNipples]"));
				sb.append(" rapidly [style.boldGrow(grow)] out at the top [npc.her] udders.<br/>");
			} else {
				sb.append((rowsDifference==1
						?"an extra pair of [npc.crotchBoobs]"
						:"extra pairs of [npc.crotchBoobs]"));
				sb.append(" rapidly [style.boldGrow(grow)] out of the [npc.skin] of [npc.her] stomach.<br/>");
			}
		}

		if(this.getShape()==BreastShape.UDDERS) {
			if(rows==0) {
				sb.append("[npc.Name] now [npc.has] [style.boldSex(a single udder)]!" 
						+ "</p>");
			} else {
				sb.append("[npc.Name] now [npc.has] [style.boldSex("+ Util.intToString(rows) + " pair"+(rows > 1 ? "s" : "")+" of udders)]!" 
						+ "</p>");
			}
		} else {
			sb.append("[npc.Name] now [npc.has] [style.boldSex("+ Util.intToString(rows) + " pair"+ (rows > 1 ? "s" : "") + " of [npc.crotchBoobs])]!" 
					+ "</p>");
		}

		this.rows = rows;

		return UtilText.parse(owner,sb.toString());
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
		if(this.getShape()==BreastShape.UDDERS && this.getRows()==0) {
			nippleCountPerBreast = Math.max(2, Math.min(nippleCountPerBreast, MAXIMUM_NIPPLES_PER_BREAST));
		} else {
			nippleCountPerBreast = Math.max(1, Math.min(nippleCountPerBreast, MAXIMUM_NIPPLES_PER_BREAST));
		}
		
		if(owner==null) {
			this.nippleCountPerBreast = nippleCountPerBreast;
			return "";
		}
		
		if (this.nippleCountPerBreast == nippleCountPerBreast) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The number of [npc.crotchNipples] [npc.nameHasFull] doesn't change...)]</p>");
		}
		
		String transformation = "";
		
		if (nippleCountPerBreast < getNippleCountPerBreast()) {
				transformation = UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] [npc.verb(feel)] a strange tingling sensation running just beneath the surface of the [npc.breastCrotchSkin] that covers [npc.her] [npc.crotchBoobs]."
							+ " A shocked gasp bursts from [npc.her] mouth as the force shoots up into [npc.her] [npc.crotchNipples],"
								+ " and [npc.she] [npc.verb(continue)] [npc.moaning] as some of them [style.boldShrink(shrink)] into the flesh of [npc.her] [npc.crotchBoobs].<br/>"
							+ "[npc.Name] now [npc.has] [style.boldSex("+ Util.intToString(nippleCountPerBreast) + " "+ (nippleCountPerBreast > 1 ? "[npc.crotchNipples]" : "[npc.crotchNipple]") + " on each of [npc.her] [npc.crotchBoobs])]!" 
						+ "</p>");
			
			
		} else if (nippleCountPerBreast > getNippleCountPerBreast()) {
				transformation = UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] [npc.verb(feel)] a strange tingling sensation running just beneath the surface of the [npc.breastCrotchSkin] that covers [npc.her] [npc.crotchBoobs]."
							+ " A shocked gasp bursts from [npc.her] mouth as the force shoots up into [npc.her] [npc.crotchNipples],"
								+ " and [npc.she] [npc.verb(continue)] [npc.moaning] as [npc.she] feels new ones [style.boldGrow(growing)] out of the flesh of [npc.her] [npc.crotchBoobs].<br/>"
							+ "[npc.Name] now [npc.has] [style.boldSex("+ Util.intToString(nippleCountPerBreast) + " "+ (nippleCountPerBreast > 1 ? "[npc.crotchNipples]" : "[npc.crotchNipple]") + " on each of [npc.her] [npc.crotchBoobs])]!" 
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
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(BreastCrotch.class) && getType().getRace().isFeralPartsAvailable());
	}

}
