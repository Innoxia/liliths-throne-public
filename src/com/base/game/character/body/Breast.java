package com.base.game.character.body;

import java.io.Serializable;

import com.base.game.character.GameCharacter;
import com.base.game.character.body.types.BreastType;
import com.base.game.character.body.valueEnums.CupSize;
import com.base.game.character.body.valueEnums.Lactation;
import com.base.game.character.body.valueEnums.NippleShape;
import com.base.game.dialogue.utils.UtilText;
import com.base.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public class Breast implements BodyPartInterface, Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final int MAXIMUM_NIPPLES_PER_BREAST = 4;
	
	private BreastType type;
	private int size, rows, lactation, nippleCountPerBreast;
	
	private Nipples nipples;
	private FluidMilk milk;
	
	/**
	 * @param size in inches from bust to underbust using the UK system.
	 * @param lactation in mL.
	 */
	public Breast(BreastType type, int size, int lactation, int rows, int nippleSize, NippleShape nippleShape, int areolaeSize, int nippleCountPerBreast, int capacity, int elasticity, int plasticity, boolean virgin) {
		this.type = type;
		this.size = size;
		this.lactation = lactation;
		this.rows = rows;
		this.nippleCountPerBreast = nippleCountPerBreast;
		
		nipples = new Nipples(type.getNippleType(), nippleSize, nippleShape, areolaeSize, Lactation.getLactationFromInt(lactation).getAssociatedWetness().getValue(), capacity, elasticity, plasticity, virgin);
		
		milk = new FluidMilk(type.getFluidType());
	}
	
	@Override
	public BreastType getType() {
		return type;
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
		String nippleCountDescriptor = "";
		if(nippleCountPerBreast == 4) {
			nippleCountDescriptor = "quad-nippled";
		} else if(nippleCountPerBreast == 3) {
			nippleCountDescriptor = "tri-nippled";
		} else if(nippleCountPerBreast == 2) {
			nippleCountDescriptor = "dual-nippled";
		} 
		
		return UtilText.returnStringAtRandom(
				nippleCountDescriptor,
				type.getDescriptor(owner));
		
	}

	public String getLactationDescription(GameCharacter gc) {
		if (lactation <= Lactation.ZERO_NONE.getMaximumValue()) {
			return " aren't producing any " + milk.getName(gc);
		} else {
			return " are producing " + getLactation().getDescriptor() + " " + milk.getName(gc) + ", averaging about " + lactation + "mL each time you are milked.";
		}
	}
	
	public boolean hasBreasts() {
		return size>=CupSize.AA.getMeasurement();
	}

	public String setType(GameCharacter owner, BreastType type) {
		if (type == getType()) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(You already have the breasts of [pc.a_breastRace], so nothing happens...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already has the breasts of [npc.a_breastRace], so nothing happens...)]</p>");
			}
		}
		
		UtilText.transformationContentSB.setLength(0);
		
		if (owner.isPlayer()) {
			UtilText.transformationContentSB.append(
					"<p>"
						+ "Your entire torso suddenly starts to feel extremely soft and sensitive, and you let out a little [pc.moan] as you feel your [pc.breasts] start to shift and transform.");
		} else {
			UtilText.transformationContentSB.append(
					"<p>"
					+ "[npc.Name] feels [npc.her] entire torso suddenly start to feel extremely soft and sensitive, and [npc.she] lets out a little [npc.moan] as [npc.her] [npc.breasts] start to shift and transform.");
		}

		// Parse existing content before transformation:
		String s = UtilText.parse(owner, UtilText.transformationContentSB.toString());
		UtilText.transformationContentSB.setLength(0);
		UtilText.transformationContentSB.append(s);
		this.type = type;
		nipples.setType(owner, type.getNippleType());
		milk.setType(type.getFluidType());
		
		switch (type) {
			case HUMAN:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" Your nipples and areolae start tingling, and you find yourself panting and sweating as the intense transformation runs its course."
								+ " After a few moments, the feeling starts to fade away, leaving you with human breasts, covered in [pc.breastFullDescriptionColour]."
								+ " The transformation has also left you with [pc.nipplesFullDescriptionColour]."
								+ "</br>"
								+ "You now have [style.boldHuman(human breasts and [pc.nipples])], and when lactating, you will produce [style.boldHuman(human milk)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" [npc.Her] nipples and areolae start tingling, causing [npc.herHim] to pant and sweat as the intense transformation runs its course."
								+ " After a few moments, the feeling starts to fade away, leaving [npc.herHim] with human breasts, covered in [npc.breastFullDescriptionColour]."
								+ " The transformation has also left [npc.herHim] with [npc.nipplesFullDescriptionColour]."
								+ "</br>"
								+ "[npc.She] now has [style.boldHuman(human breasts and [npc.nipples])], and when lactating, [npc.she] will produce [style.boldHuman(human milk)]."
								+ "</p>");
				}
				break;
			case DEMON_COMMON:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" Your nipples and areolae start tingling, and you find yourself panting and sweating as the intense transformation runs its course."
							+ " After a few moments, the feeling starts to fade away, leaving you with demonic breasts, covered in [pc.breastFullDescriptionColour]."
							+ " The transformation has also left you with [pc.nipplesFullDescriptionColour]."
							+ "</br>"
							+ "You now have [style.boldDemon(demonic breasts and [pc.nipples])], and when lactating, you will produce [style.boldDemon(demonic milk)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" [npc.Her] nipples and areolae start tingling, causing [npc.herHim] to pant and sweat as the intense transformation runs its course."
								+ " After a few moments, the feeling starts to fade away, leaving [npc.herHim] with demonic breasts, covered in [npc.breastFullDescriptionColour]."
								+ " The transformation has also left [npc.herHim] with [npc.nipplesFullDescriptionColour]."
								+ "</br>"
								+ "[npc.She] now has [style.boldDemon(demonic breasts and [npc.nipples])], and when lactating, [npc.she] will produce [style.boldDemon(demonic milk)]."
								+ "</p>");
				}
				break;
			case DOG_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" Your nipples and areolae start tingling, and you find yourself panting and sweating as the intense transformation runs its course."
							+ " After a few moments, the feeling starts to fade away, leaving you with canine breasts, covered in [pc.breastFullDescriptionColour]."
							+ " The transformation has also left you with [pc.nipplesFullDescriptionColour]."
							+ "</br>"
							+ "You now have [style.boldDogMorph(canine breasts and [pc.nipples])], and when lactating, you will produce [style.boldDemon(canine milk)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" [npc.Her] nipples and areolae start tingling, causing [npc.herHim] to pant and sweat as the intense transformation runs its course."
								+ " After a few moments, the feeling starts to fade away, leaving [npc.herHim] with canine breasts, covered in [npc.breastFullDescriptionColour]."
								+ " The transformation has also left [npc.herHim] with [npc.nipplesFullDescriptionColour]."
								+ "</br>"
								+ "[npc.She] now has [style.boldDogMorph(canine breasts and [npc.nipples])], and when lactating, [npc.she] will produce [style.boldDogMorph(canine milk)]."
								+ "</p>");
				}
				break;
			case WOLF_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" Your nipples and areolae start tingling, and you find yourself panting and sweating as the intense transformation runs its course."
							+ " After a few moments, the feeling starts to fade away, leaving you with lupine breasts, covered in [pc.breastFullDescriptionColour]."
							+ " The transformation has also left you with [pc.nipplesFullDescriptionColour]."
							+ "</br>"
							+ "You now have [style.boldWolfMorph(lupine breasts and [pc.nipples])], and when lactating, you will produce [style.boldWolfMorph(lupine milk)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" [npc.Her] nipples and areolae start tingling, causing [npc.herHim] to pant and sweat as the intense transformation runs its course."
								+ " After a few moments, the feeling starts to fade away, leaving [npc.herHim] with lupine breasts, covered in [npc.breastFullDescriptionColour]."
								+ " The transformation has also left [npc.herHim] with [npc.nipplesFullDescriptionColour]."
								+ "</br>"
								+ "[npc.She] now has [style.boldWolfMorph(lupine breasts and [npc.nipples])], and when lactating, [npc.she] will produce [style.boldWolfMorph(lupine milk)]."
								+ "</p>");
				}
				break;
			case CAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" Your nipples and areolae start tingling, and you find yourself panting and sweating as the intense transformation runs its course."
							+ " After a few moments, the feeling starts to fade away, leaving you with feline breasts, covered in [pc.breastFullDescriptionColour]."
							+ " The transformation has also left you with [pc.nipplesFullDescriptionColour]."
							+ "</br>"
							+ "You now have [style.boldCatMorph(feline breasts and [pc.nipples])], and when lactating, you will produce [style.boldCatMorph(feline milk)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" [npc.Her] nipples and areolae start tingling, causing [npc.herHim] to pant and sweat as the intense transformation runs its course."
								+ " After a few moments, the feeling starts to fade away, leaving [npc.herHim] with feline breasts, covered in [npc.breastFullDescriptionColour]."
								+ " The transformation has also left [npc.herHim] with [npc.nipplesFullDescriptionColour]."
								+ "</br>"
								+ "[npc.She] now has [style.boldCatMorph(feline breasts and [npc.nipples])], and when lactating, [npc.she] will produce [style.boldCatMorph(feline milk)]."
								+ "</p>");
				}
				break;
			case SQUIRREL_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" Your nipples and areolae start tingling, and you find yourself panting and sweating as the intense transformation runs its course."
							+ " After a few moments, the feeling starts to fade away, leaving you with a squirrel-morph's breasts, covered in [pc.breastFullDescriptionColour]."
							+ " The transformation has also left you with [pc.nipplesFullDescriptionColour]."
							+ "</br>"
							+ "You now have [style.boldSquirrelMorph(squirrel-morph breasts and [pc.nipples])], and when lactating, you will produce [style.boldSquirrelMorph(squirrel-morph milk)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" [npc.Her] nipples and areolae start tingling, causing [npc.herHim] to pant and sweat as the intense transformation runs its course."
								+ " After a few moments, the feeling starts to fade away, leaving [npc.herHim] with a squirrel-morph's breasts, covered in [npc.breastFullDescriptionColour]."
								+ " The transformation has also left [npc.herHim] with [npc.nipplesFullDescriptionColour]."
								+ "</br>"
								+ "[npc.She] now has [style.boldSquirrelMorph(squirrel-morph breasts and [npc.nipples])], and when lactating, [npc.she] will produce [style.boldSquirrelMorph(squirrel-morph milk)]."
								+ "</p>");
				}
				break;
			case HORSE_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" Your nipples and areolae start tingling, and you find yourself panting and sweating as the intense transformation runs its course."
							+ " After a few moments, the feeling starts to fade away, leaving you with equine breasts, covered in [pc.breastFullDescriptionColour]."
							+ " The transformation has also left you with [pc.nipplesFullDescriptionColour]."
							+ "</br>"
							+ "You now have [style.boldHorseMorph(equine breasts and [pc.nipples])], and when lactating, you will produce [style.boldHorseMorph(equine milk)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" [npc.Her] nipples and areolae start tingling, causing [npc.herHim] to pant and sweat as the intense transformation runs its course."
								+ " After a few moments, the feeling starts to fade away, leaving [npc.herHim] with equine breasts, covered in [npc.breastFullDescriptionColour]."
								+ " The transformation has also left [npc.herHim] with [npc.nipplesFullDescriptionColour]."
								+ "</br>"
								+ "[npc.She] now has [style.boldHorseMorph(equine breasts and [npc.nipples])], and when lactating, [npc.she] will produce [style.boldHorseMorph(equine milk)]."
								+ "</p>");
				}
				break;
			case HARPY:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" Your nipples and areolae start tingling, and you find yourself panting and sweating as the intense transformation runs its course."
							+ " After a few moments, the feeling starts to fade away, leaving you with avian breasts, covered in [pc.breastFullDescriptionColour]."
							+ " The transformation has also left you with [pc.nipplesFullDescriptionColour]."
							+ "</br>"
							+ "You now have [style.boldHarpy(avian breasts and [pc.nipples])], and when lactating, you will produce [style.boldHarpy(avian milk)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" [npc.Her] nipples and areolae start tingling, causing [npc.herHim] to pant and sweat as the intense transformation runs its course."
								+ " After a few moments, the feeling starts to fade away, leaving [npc.herHim] with avian breasts, covered in [npc.breastFullDescriptionColour]."
								+ " The transformation has also left [npc.herHim] with [npc.nipplesFullDescriptionColour]."
								+ "</br>"
								+ "[npc.She] now has [style.boldHarpy(avian breasts and [npc.nipples])], and when lactating, [npc.she] will produce [style.boldHarpy(avian milk)]."
								+ "</p>");
				}
				break;
			case ANGEL://TODO
				break;
			case SLIME://TODO
				break;
		}
		
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
	 * @return True if size was changed.
	 */
	public String setSize(GameCharacter owner, int size) {
		boolean hadBreasts = hasBreasts();
		
		int sizeChange = 0;
		
		if (size <= 0) {
			if (this.size != 0) {
				sizeChange = 0 - this.size;
				this.size = 0;
			}
		} else if (size >= CupSize.MAXIMUM.getMeasurement()) {
			if (this.size != CupSize.MAXIMUM.getMeasurement()) {
				sizeChange = CupSize.MAXIMUM.getMeasurement() - this.size;
				this.size = CupSize.MAXIMUM.getMeasurement();
			}
		} else {
			if (this.size != size) {
				sizeChange = size - this.size;
				this.size = size;
			}
		}
		
		if(sizeChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The size of your [pc.breasts] doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The size of [npc.name]'s [pc.breasts] doesn't change...)]</p>");
			}
		}
		
		String sizeDescriptor = getSize().getDescriptor();
		if (sizeChange > 0) {
			if (owner.isPlayer()) {
				return "</p>"
							+ "You feel a tingling heat quickly spreading throughout your torso, and you can't help but let out [pc.a_moan+] as your "
							+ (hadBreasts
									? "[pc.breasts] swell up and [style.boldGrow(grown larger)].</br>"
									: "chest swells up, and before you know what's happening, a pair of breasts have [style.boldGrow(grown)] out of your previously-flat torso.</br>")
							+ "You now have [style.boldSex(" + sizeDescriptor + (getSize().getMeasurement()>CupSize.AA.getMeasurement()?", "+getSize().getCupSizeName()+"-cup ":"") + "breasts)]!"
						+ "</p>";
			} else {
				return UtilText.genderParsing(owner,
						"</p>"
							+ "[npc.Name] feels a tingling heat quickly spreading throughout [npc.her] torso, and [npc.she] can't help but let out [npc.a_moan+] as [npc.her] "
							+ (hadBreasts
									? "[npc.breasts] swell up and [style.boldGrow(grown larger)].</br>"
									: "chest swells up, and before [npc.she] knows what's happening, a pair of breasts have [style.boldGrow(grown)] out of [npc.her] previously-flat torso.</br>")
							+ "[npc.Name] now has [style.boldSex(" + sizeDescriptor + (getSize().getMeasurement()>CupSize.AA.getMeasurement()?", "+getSize().getCupSizeName()+"-cup ":"") + "breasts)]!"
						+ "</p>");
			}
		} else {
			if (owner.isPlayer()) {
				return "</p>"
						+ "You feel a tingling heat quickly spreading throughout your torso, and you can't help but let out a frustrated [pc.moan] as your [pc.breasts] shrink down and [style.boldShrink(get smaller)].</br>"
						+ (this.size==0
							? "You now have [style.boldSex(a completely flat chest)]!"
							:"You now have [style.boldSex(" + sizeDescriptor + (getSize().getMeasurement()>CupSize.AA.getMeasurement()?", "+getSize().getCupSizeName()+"-cup ":"") + "breasts)]!")
					+ "</p>";
		} else {
			return UtilText.genderParsing(owner,
					"</p>"
						+ "[npc.Name] feels a tingling heat quickly spreading throughout [npc.her] torso, and [npc.she] can't help but let out a frustrated [npc.moan] as [npc.her] [npc.breasts] shrink down and [style.boldShrink(get smaller)].</br>"
						+ (this.size==0
							? "[npc.Name] now has [style.boldSex(a completely flat chest)]!"
							: "[npc.Name] now has [style.boldSex(" + sizeDescriptor + (getSize().getMeasurement()>CupSize.AA.getMeasurement()?", "+getSize().getCupSizeName()+"-cup ":"") + "breasts)]!")
					+ "</p>");
			}
		}
	}

	// Lactation:

	public Lactation getLactation() {
		return Lactation.getLactationFromInt(lactation);
	}

	public int getRawLactationValue() {
		return lactation;
	}

	/**
	 * Sets the lactation. Value is bound to >=0 && <=Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue()
	 */
	public String setLactation(GameCharacter owner, int lactation) {
		int lactationChange = 0;
		
		if (lactation <= 0) {
			if (this.lactation != 0) {
				lactationChange = 0 - this.lactation;
				this.lactation = 0;
			}
		} else if (lactation >= Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue()) {
			if (this.lactation != Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue()) {
				lactationChange = Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue() - this.lactation;
				this.lactation = Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue();
			}
		} else {
			if (this.lactation != lactation) {
				lactationChange = lactation - this.lactation;
				this.lactation = lactation;
			}
		}
		
		if(lactationChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The amount of [pc.milk] that you're producing doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The amount of [npc.milk] that [npc.name] is producing doesn't change...)]</p>");
			}
		}
		
		String lactationDescriptor = getLactation().getDescriptor();
		if (lactationChange > 0) {
			if (owner.isPlayer()) {
				return "</p>"
							+ "You feel a strange bubbling and churning taking place deep within your [pc.breasts], and you can't help but let out [pc.a_moan+] as a few drops of [pc.milk] suddenly leak from your [pc.nipples];"
								+ " clear evidence that that your [pc.milk] production has [style.boldGrow(increased)].</br>"
							+ "You are now producing [style.boldSex(" + lactationDescriptor + " [pc.milk])]!"
						+ "</p>";
			} else {
				return UtilText.genderParsing(owner,
						"</p>"
							+ "[npc.Name] feels a strange bubbling and churning taking place deep within [npc.her] [npc.breasts], and [npc.a_moan+] drifts out from between [npc.her] [npc.lips] as a few drops of [npc.milk] suddenly leak"
								+ " from [npc.her] [npc.nipples]; clear evidence that that [npc.her] [npc.milk] production has [style.boldGrow(increased)].</br>"
							+ "[npc.Name] is now producing [style.boldSex(" + lactationDescriptor + " [npc.milk])]!"
						+ "</p>");
			}
		} else {
			if (owner.isPlayer()) {
				return "</p>"
							+ "You feel a strange sucking sensation deep within your [pc.breasts], and you can't help but let out a shocked gasp as you realise that you're feeling your [pc.milk] production [style.boldShrink(drying up)].</br>"
							+ "You are now producing [style.boldSex(" + lactationDescriptor + " [pc.milk])]."
						+ "</p>";
			} else {
				return UtilText.genderParsing(owner,
						"</p>"
							+ "[npc.Name] feels a strange sucking sensation deep within [npc.her] [npc.breasts],"
								+ " and a frustrated sigh drifts out from between [npc.her] [npc.lips] as [npc.she] realises that [npc.she]'s feeling [npc.her] [npc.milk] production [style.boldShrink(drying up)].</br>"
							+ "[npc.Name] is now producing [style.boldSex(" + lactationDescriptor + " [npc.milk])]."
						+ "</p>");
			}
		}
	}

	public int getRows() {
		return rows;
	}

	public String setRows(GameCharacter owner, int rows) {
		
		if(rows<=0) {
			rows = 1;
		} else if (rows>3) {
			rows=3;
		}
		
		if(rows == getRows()) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The number of breast rows you have doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The number of breast rows [npc.name] has doesn't change...)]</p>");
			}
		}
		
		String transformation = "";
		
		if (rows < getRows()) {
			if (owner.isPlayer()) {
				transformation =
						"<p>"
							+ "You feel a strange bubbling sensation within your [pc.breasts], and before you have time to react, "
							+ (getRows() == 3
								? (rows == 2
									? "the lowest of your extra pairs"
									: "your two extra pairs")
								: "your extra pair")
							+ " of " + (hasBreasts() ? "breasts" : "pecs") + " rapidly shrink away and [style.boldShrink(disappear)] into the [pc.skin] of your torso.</br>"
							+ "You now have [style.boldSex("+ Util.intToString(rows) + " pair"+ (rows > 1 ? "s" : "") + " of " + (hasBreasts() ? "breasts" : "pecs") +")]!" 
						+ "</p>";
			} else {
				transformation = UtilText.parse(owner,
							"<p>"
								+ "[npc.Name] glances down worriedly at [npc.her] [npc.breasts], and before [npc.she] has time to react, "
								+ (getRows() == 3
									? (rows == 2
										? "the lowest of [npc.her] extra pairs"
										: "[npc.her] two extra pairs")
									: "[npc.her] extra pair")
								+ " of " + (hasBreasts() ? "breasts" : "pecs") + " rapidly shrink away and [style.boldShrink(disappear)] into the [npc.skin] of [npc.her] torso.</br>"
								+ "[npc.Name] now has [style.boldSex("+ Util.intToString(rows) + " pair"+ (rows > 1 ? "s" : "") + " of " + (hasBreasts() ? "breasts" : "pecs") +")]!" 
							+ "</p>");
			}
			
		} else if (rows > getRows()) {
			if (owner.isPlayer()) {
				transformation =
						"<p>"
							+ "You feel a strange bubbling sensation within your [pc.breasts], and before you have time to react, "
							+ (getRows() == 1
								? (rows == 3
									? "two extra pairs"
									: "an extra pair")
								: "an extra pair")
							+ " of " + (hasBreasts() ? "breasts" : "pecs") + " rapidly [style.boldGrow(grow)] out of the [pc.skin] of your torso.</br>"
							+ "You now have [style.boldSex("+ Util.intToString(rows) + " pair"+ (rows > 1 ? "s" : "") + " of " + (hasBreasts() ? "breasts" : "pecs") +")]!" 
						+ "</p>";
			} else {
				transformation = UtilText.parse(owner,
							"<p>"
								+ "[npc.Name] glances down worriedly at [npc.her] [npc.breasts], and before [npc.she] has time to react, "
								+ (getRows() == 1
									? (rows == 3
										? "two extra pairs"
										: "an extra pair")
									: "an extra pair")
								+ " of " + (hasBreasts() ? "breasts" : "pecs") + " rapidly [style.boldGrow(grow)] out of the [npc.skin] of [npc.her] torso.</br>"
								+ "[npc.Name] now has [style.boldSex("+ Util.intToString(rows) + " pair"+ (rows > 1 ? "s" : "") + " of " + (hasBreasts() ? "breasts" : "pecs") +")]!" 
							+ "</p>");
			}
		}

		if (rows <= 0) {
			this.rows = 1;
		} else if (rows > 3) {
			this.rows = 3;
		} else {
			this.rows = rows;
		}

		return transformation;
	}

	public boolean isFuckable() {
		return nipples.getOrificeNipples().getRawCapacityValue() > 0 && size >= CupSize.C.getMeasurement();
	}


	public int getNippleCountPerBreast() {
		return nippleCountPerBreast;
	}

	/**
	 * Minimum 1, maximum MAXIMUM_NIPPLES_PER_BREAST
	 */
	public String setNippleCountPerBreast(GameCharacter owner, int nippleCountPerBreast) {
		
		if(nippleCountPerBreast <= 0) {
			nippleCountPerBreast = 1;
			
		} else if (nippleCountPerBreast > MAXIMUM_NIPPLES_PER_BREAST) {
			nippleCountPerBreast = MAXIMUM_NIPPLES_PER_BREAST;
		}
		
		if (this.nippleCountPerBreast == nippleCountPerBreast) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The number of [pc.nipples] on each of your ...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The number of breast rows [npc.name] has doesn't change...)]</p>");
			}
		}
		
		String transformation = "";
		
		if (nippleCountPerBreast < getNippleCountPerBreast()) {
			if (owner.isPlayer()) {
				transformation =
						"<p>"
							+ "You feel a strange tingling sensation running just beneath the surface of the [pc.breastSkin] that covers your [pc.breasts]."
							+ " A shocked gasp bursts from your mouth as the force shoots up into your [pc.nipples], and you feel some of them [style.boldShrink(shrinking)] into the flesh of your [pc.breasts].</br>"
							+ "You now have [style.boldSex("+ Util.intToString(nippleCountPerBreast) + " "+ (nippleCountPerBreast > 1 ? "[pc.nipples]" : "[pc.nipple]") + " on each of your " + (hasBreasts() ? "breasts" : "pecs") +")]!" 
						+ "</p>";
			} else {
				transformation = UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] feels a strange tingling sensation running just beneath the surface of the [npc.breastSkin] that covers [npc.her] [npc.breasts]."
							+ " A shocked gasp bursts from [npc.her] mouth as the force shoots up into [npc.her] [npc.nipples], and [npc.she] continues [npc.moaning] as some of them [style.boldShrink(shrink)] into the flesh of [npc.her] [npc.breasts].</br>"
							+ "[npc.Name] now has [style.boldSex("+ Util.intToString(nippleCountPerBreast) + " "+ (nippleCountPerBreast > 1 ? "[npc.nipples]" : "[npc.nipple]") + " on each of [npc.her] " + (hasBreasts() ? "breasts" : "pecs") +")]!" 
						+ "</p>");
			}
			
		} else if (nippleCountPerBreast > getNippleCountPerBreast()) {
			if (owner.isPlayer()) {
				transformation =
						"<p>"
							+ "You feel a strange tingling sensation running just beneath the surface of the [pc.breastSkin] that covers your [pc.breasts]."
							+ " A shocked gasp bursts from your mouth as the force shoots up into your [pc.nipples], and you continue [pc.moaning] as you feel new ones [style.boldGrow(growing)] out of the flesh of your [pc.breasts].</br>"
							+ "You now have [style.boldSex("+ Util.intToString(nippleCountPerBreast) + " "+ (nippleCountPerBreast > 1 ? "[pc.nipples]" : "[pc.nipple]") + " on each of your " + (hasBreasts() ? "breasts" : "pecs") +")]!" 
						+ "</p>";
			} else {
				transformation = UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] feels a strange tingling sensation running just beneath the surface of the [npc.breastSkin] that covers [npc.her] [npc.breasts]."
							+ " A shocked gasp bursts from [npc.her] mouth as the force shoots up into [npc.her] [npc.nipples],"
								+ " and [npc.she] continues [npc.moaning] as [npc.she] feels new ones [style.boldGrow(growing)] out of the flesh of [npc.her] [npc.breasts].</br>"
							+ "[npc.Name] now has [style.boldSex("+ Util.intToString(nippleCountPerBreast) + " "+ (nippleCountPerBreast > 1 ? "[npc.nipples]" : "[npc.nipple]") + " on each of [npc.her] " + (hasBreasts() ? "breasts" : "pecs") +")]!" 
						+ "</p>");
			}
		}
		
		if (nippleCountPerBreast <= 0) {
			this.nippleCountPerBreast = 1;
		} else if (nippleCountPerBreast > MAXIMUM_NIPPLES_PER_BREAST) {
			this.nippleCountPerBreast = MAXIMUM_NIPPLES_PER_BREAST;
		} else {
			this.nippleCountPerBreast = nippleCountPerBreast;
		}

		return transformation;
	}

}
