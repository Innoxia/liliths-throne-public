package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractVaginaType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public class Vagina implements BodyPartInterface {
	
	protected AbstractVaginaType type;
	protected Clitoris clitoris;
	protected int labiaSize;
	protected boolean pierced;
	protected boolean eggLayer;
	
	protected OrificeVagina orificeVagina;
	protected FluidGirlCum girlcum;
	protected OrificeVaginaUrethra orificeUrethra;

	public Vagina(AbstractVaginaType type, int labiaSize, int clitSize, int clitGirth, int wetness, float capacity, int depth, int elasticity, int plasticity, boolean virgin) {
		this.type = type;
		this.labiaSize = labiaSize;
		this.clitoris = new Clitoris(clitSize, clitGirth);
		this.pierced = false;
		this.eggLayer = type.isEggLayer();
		orificeVagina = new OrificeVagina(wetness, capacity, depth, elasticity, plasticity, virgin, type.getDefaultRacialOrificeModifiers());
		orificeUrethra = new OrificeVaginaUrethra(Wetness.TWO_MOIST.getValue(), 0, 2, OrificeElasticity.ZERO_UNYIELDING.getValue(), OrificePlasticity.THREE_RESILIENT.getValue(), true, new ArrayList<>());
		girlcum = new FluidGirlCum(type.getFluidType());
	}

	public OrificeVagina getOrificeVagina() {
		return orificeVagina;
	}

	public FluidGirlCum getGirlcum() {
		return girlcum;
	}
	
	public OrificeVaginaUrethra getOrificeUrethra() {
		return orificeUrethra;
	}

	@Override
	public AbstractVaginaType getType() {
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

	@Override
	public String getDescriptor(GameCharacter owner) {
		List<String> descriptorList = new ArrayList<>();
		
		for(OrificeModifier om : orificeVagina.getOrificeModifiers()) {
			descriptorList.add(om.getName());
		}
		
		String wetnessDescriptor = orificeVagina.getWetness(owner).getDescriptor();
		if(Main.game.isInSex() && Main.sex.getAllParticipants().contains(owner)) {
			if(Main.sex.hasLubricationTypeFromAnyone(owner, SexAreaOrifice.VAGINA)) {
				wetnessDescriptor = "wet";
			}
		}
		descriptorList.add(wetnessDescriptor);
		if(owner.getPubicHair().getValue()>=BodyHair.FOUR_NATURAL.getValue() && Main.game.isPubicHairEnabled()) {
			descriptorList.add("hairy");
		}
		
		// It doesn't make much sense to be referencing taste in a generic context
//		if(this.getGirlcum().getFlavour()!=FluidFlavour.GIRL_CUM && this.getGirlcum().getFlavour()!=FluidFlavour.FLAVOURLESS) {
//			descriptorList.add(this.getGirlcum().getFlavour().getName()+"-flavoured");
//		}
		
		if(owner.isVaginaFeral()) {
			descriptorList.add(Util.randomItemFrom(Util.newArrayListOfValues(
					"feral",
					"bestial",
					"animalistic")));
		} else {
			descriptorList.add(type.getDescriptor(owner));
		}
		
		if(owner.getBodyMaterial().getPartDescriptors()!=null && !owner.getBodyMaterial().getPartDescriptors().isEmpty()) {
			descriptorList.add(Util.randomItemFrom(owner.getBodyMaterial().getPartDescriptors()));
		}
		
		descriptorList.add(Capacity.getCapacityFromValue(orificeVagina.getStretchedCapacity()).getDescriptor().replaceAll(" ", "-"));

		descriptorList.removeIf(d->d==null || d.isEmpty());
		if(descriptorList.isEmpty()) {
			return "";
		}
		return Util.randomItemFrom(descriptorList);
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
	
	public String setType(GameCharacter owner, AbstractVaginaType type) {
		return setType(owner, type, false);
	}
	
	public String setType(GameCharacter owner, AbstractVaginaType type, boolean overridePregnancyPrevention) {
		if(this.type==VaginaType.NONE) {
			this.orificeVagina.setStretchedCapacity(this.orificeVagina.getRawCapacityValue());
			this.orificeUrethra.setStretchedCapacity(this.orificeUrethra.getRawCapacityValue());
			this.orificeVagina.hymen=true;
		}
		
		if(!Main.game.isStarted() || owner==null) {// This always overrides pregnancy prevention, as the only times where this is true are for utility methods:
			this.type = type;
			this.girlcum.setType(type.getFluidType());
			this.eggLayer = type.isEggLayer();
			if(owner!=null) {
				owner.resetAreaKnownByCharacters(CoverableArea.VAGINA);
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == owner.getVaginaType()) {
			if(type == VaginaType.NONE) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.verb(lack)] a vagina, so nothing happens...)]</p>");
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.has] [npc.a_vaginaRace] pussy, so nothing happens...)]</p>");
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		// Cannot transform if pregnant:
		if (!overridePregnancyPrevention
				&& type==VaginaType.NONE
				&& (owner.isPregnant() || owner.hasStatusEffect(StatusEffect.PREGNANT_0) || owner.getIncubationLitter(SexAreaOrifice.VAGINA)!=null)) {
			sb.append(UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a lewd moan as [npc.she] [npc.verb(feel)] [npc.her] [npc.pussy+] starting to grow hot and sensitive,"
							+ " and as a wave of tingling excitement washes through [npc.her] lower abdomen, [npc.her] moan turns into a desperate gasp."
						+ " Much to [npc.her] surprise, the feeling fades away almost as quickly as it came, and with a sigh, [npc.she] [npc.verb(realise)] that "
						+ (owner.getIncubationLitter(SexAreaOrifice.VAGINA)!=null
							?"<b>the eggs being incubated in [npc.her] womb have prevented [npc.her] vagina from being removed</b>!"
							:(owner.hasStatusEffect(StatusEffect.PREGNANT_0)
								?"<b>the possibility of being pregnant has prevented [npc.her] vagina from being removed</b>!"
								:"<b>[npc.her] ongoing pregnancy has prevented [npc.her] vagina from being removed</b>!"))
						+ "<br/>"
						+ "[npc.NamePos] pussy remains [style.boldTfSex(unchanged)]."
					+ "</p>"));
			
			return sb.toString()
					+ "<p>"
						+owner.postTransformationCalculation()
					+"</p>";
		}
		
		// If have no vagina:
		if(owner.getVaginaType() == VaginaType.NONE) {
			sb.append(UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(blush)] as [npc.she] [npc.verb(feel)] a strange heat spreading through [npc.her] groin, and can't help but let out a low [npc.moan] as the [npc.skin] "
						+ (!owner.hasPenisIgnoreDildo()
							? "in the middle of [npc.her] groin"
							: (!owner.isTaur()
									?"beneath [npc.her] cock"
									:"above and behind [npc.her] cock"))
						+ " starts to cave inwards and form a shallow furrow."
						+ " Showing no sign of stopping, this strange new indentation continues to deepen, sending another burst of heat shooting up into [npc.namePos] lower abdomen."
						+ " As this second wave of heat fades away, a sharp, penetrating sensation shoots up into [npc.her] groin, and while it isn't painful,"
							+ " [npc.she] can't help but cry out in shock as this groove suddenly splits and opens up to reveal a deep channel."
						+ " The opening to this new orifice then quickly transforms into the distinctive shape of a vagina, complete with a [npc.clitSize] clit and [npc.labiaSize] labia."
					+ "</p>"
					+ "<p>"
						+ "Just as [npc.she] [npc.verb(start)] to think that the transformation is over, one final intense wave of heat pulses up into [npc.namePos] lower abdomen,"
							+ " and [npc.she] can't help but let out a desperate [npc.moan] as [npc.she] instinctively [npc.verb(realise)] that a completely-functional female reproductive system has grown inside of [npc.herHim]."
						+ " As [npc.her] transformation finally comes to an end, [npc.namePos] suddenly [npc.verb(become)] aware of the fact that [npc.her] new cunt is already soaking wet from arousal, "
							+ "causing [npc.herHim] to let out one final, sensual [npc.moan]."
						+ "<br/>"));
			
			if(owner.isVaginaVirgin()) {
				sb.append(UtilText.parse(owner,
						"[npc.Name] now [npc.has] a [style.colourExcellent(virgin)] [style.boldTfSex(vagina)], complete with an [style.colourExcellent(unbroken hymen)]!"));
			} else {
				sb.append(UtilText.parse(owner,
						"[npc.Name] now [npc.has] a new [style.boldTfSex(vagina)], and although [npc.she] can't consider [npc.herself] a virgin, [npc.she] at least [npc.has] an [style.colourExcellent(unbroken hymen)]!"));
			}
					
			sb.append("</p>");
				
			if(owner.hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
				if(owner.isVaginaVirgin()) {
					sb.append(UtilText.parse(owner,
							"<p style='text-align:center;'>"
								+ "[style.boldExcellent(Pure Virgin)]"
								+ "<br/><i>"
								+ "Now that [npc.name] [npc.has] a vagina, [npc.she] can finally consider [npc.herself] to be a truly pure virgin!"
								+ " Letting a delighted smile settle on [npc.her] [npc.face], [npc.she] can't help but feel extremely elated, and that for as long as [npc.she] [npc.verb(retain)] [npc.her] virginity,"
									+ " [npc.she]'ll represent the perfect image of a proud, virtuous being!"
								+ "</i>"
								+ "<br/>"
								+ "[npc.NameIsFull] now a [style.boldExcellent(Pure Virgin)]!"
							+ "</p>"));
				} else {
					sb.append(UtilText.parse(owner,
							"<p style='text-align:center;'>"
								+ "[style.boldGood(Pure 'Virgin')]"
								+ "<br/><i>"
								+ "Finding [npc.herself] once again in possession of an unspoiled vagina, a huge wave of euphoria crashes over [npc.name]."
								+ " Convincing [npc.herself] that [npc.her] unbroken hymen means that [npc.sheIs] technically a virgin again, [npc.she] [npc.verb(feel)] tears of joy start to well up in [npc.her] [npc.eyes]."
								+ " Despite this feeling of elation, however, there's a small nagging voice in the back of [npc.her] mind which reminds [npc.herHim] that [npc.she]'ll never be a 'real' virgin ever again."
								+ "<br/>"
								+ "Shaking [npc.her] head clear of this unwelcome thought, [npc.name] [npc.verb(focus)] on the fact that for as long as [npc.her] hymen remains intact, [npc.she] can at least pretend that [npc.sheHas] never been fucked before."
								+ " Not even wanting to consider the notion that [npc.her] pussy might at some point be broken in again,"
									+ " [npc.she] [npc.verb(hold)] [npc.her] head up high and [npc.verb(tell)] [npc.herself] that [npc.sheIs] once again the perfect image of a proud, virtuous being!"
								+ "</i>"
								+ "<br/>"
								+ "[npc.NameIsFull] now a [style.boldGood(Pure 'Virgin')]!"
							+ "</p>"));
				}
			}
			
			this.type = VaginaType.HUMAN;
			owner.resetAreaKnownByCharacters(CoverableArea.VAGINA);
			
			if(type==VaginaType.HUMAN) {
				this.girlcum.setType(type.getFluidType());
				return sb.toString()
						+ "<p>"
							+owner.postTransformationCalculation()
						+"</p>";
			} else {
				return sb.toString()
						+ owner.setVaginaType(type);
			}
			
		} else {
			sb.append(UtilText.parse(owner,
					"<p>"
						+"[npc.Name] [npc.verb(feel)] a strange heat throbbing within [npc.her] pussy, and [npc.she] can't help but blush and pant for breath as [npc.her] pussy begins to transform.<br/>"));
		}

		sb.append(this.type.applyAdditionalTransformationEffects(owner, false));
		this.type = type;
		this.girlcum.setType(type.getFluidType());
		this.eggLayer = type.isEggLayer();
		owner.resetAreaKnownByCharacters(CoverableArea.VAGINA);
		sb.append(this.type.getTransformationDescription(owner));
		sb.append(this.type.applyAdditionalTransformationEffects(owner, true));
		
		sb.append("</p>");

		if(this.type != VaginaType.NONE) {
			sb.append("<p style='text-align:center;'>");
				if(this.eggLayer) {
					sb.append(UtilText.parse(owner,"<i>Instead of giving birth to live young, [npc.name] now [style.colourEgg([npc.verb(lay)] eggs)]!</i>"));
				} else {
					sb.append(UtilText.parse(owner,"<i>[npc.Name] now [style.colourSex([npc.verb(give)] birth to live young)]!</i>"));
				}
			sb.append("</p>");
		}
		
		orificeVagina.getOrificeModifiers().clear();
		for(OrificeModifier om : type.getDefaultRacialOrificeModifiers()) {
			orificeVagina.addOrificeModifier(owner, om);
		}
		
		sb.append(UtilText.parse(owner,"<p>"
				+ "Any old modifiers which [npc.her] pussy might have had have [style.boldShrink(transformed away)]!"));
		
		if(orificeVagina.getOrificeModifiers().isEmpty()) {
			sb.append("</p>");
		} else {
			sb.append(UtilText.parse(owner,"<br/>"
					+ "Instead, [npc.her] new pussy is:"));
			
			for(OrificeModifier om : orificeVagina.getOrificeModifiers()) {
				sb.append("<br/>[style.boldGrow("+Util.capitaliseSentence(om.getName())+")]");
			}
			sb.append("</p>");
		}
		
		return sb.toString()
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}
	
	
	public LabiaSize getLabiaSize() {
		return LabiaSize.getLabiaSizeFromInt(labiaSize);
	}
	
	public int getRawLabiaSizeValue() {
		return labiaSize;
	}
	
	public String setLabiaSize(GameCharacter owner, int labiaSize) {
		if(!owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		int oldSize = this.labiaSize;
		this.labiaSize = Math.max(0, Math.min(labiaSize, LabiaSize.FOUR_MASSIVE.getValue()));
		int sizeChange = this.labiaSize - oldSize;

		if(!Main.game.isStarted() || owner==null) {
			return "";
		}
		
		if (sizeChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The size of your labia doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The size of [npc.namePos] labia doesn't change...)]</p>");
			}
		} else if (sizeChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "[pc.A_moan+] bursts out from between your [pc.lips+] as you feel a warm tingling sensation run up into your [pc.pussy]."
							+ " Squirming about on the spot, your exclamation turns into a surprised gasp as you feel your labia swell up and [style.boldGrow(grow larger)].<br/>"
							+ "You now have [style.boldSex([pc.labiaSize] labia)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.A_moan+] bursts out from between [npc.name] [pc.lips+] as [npc.she] feels a warm tingling sensation run up into [npc.her] [npc.pussy]."
							+ " Squirming about on the spot, [npc.her] exclamation turns into a surprised gasp as [npc.she] feels [npc.her] labia swell up and [style.boldGrow(grow larger)].<br/>"
							+ "[npc.Name] now has [style.boldSex([npc.labiaSize] labia)]!"
						+ "</p>");
			}
		} else {
			if (owner.isPlayer()) {
				return "<p>"
						+ "[pc.A_moan+] bursts out from between your [pc.lips+] as you feel a cool tingling sensation spread up into your [pc.pussy]."
						+ " Squirming about on the spot, your exclamation turns into a surprised gasp as you feel your labia shrink down and [style.boldShrink(get smaller)].<br/>"
						+ "You now have [style.boldSex([pc.labiaSize] labia)]!"
					+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.A_moan+] bursts out from between [npc.name] [pc.lips+] as [npc.she] feels a cool tingling sensation spread up into [npc.her] [npc.pussy]."
							+ " Squirming about on the spot, [npc.her] exclamation turns into a surprised gasp as [npc.she] feels [npc.her] labia shrink down and [style.boldShrink(get smaller)].<br/>"
							+ "[npc.Name] now has [style.boldSex([npc.labiaSize] labia)]!"
						+ "</p>");
			}
		}
	}

	public boolean isPierced() {
		return pierced;
	}

	public String setPierced(GameCharacter owner, boolean pierced) {
		if(!Main.game.isStarted() || owner==null) {
			this.pierced = pierced;
			return "";
		}
		
		if(this.pierced == pierced || !owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		this.pierced = pierced;
		
		if(pierced) {
			return UtilText.parse(owner, "<p>[npc.NamePos] [npc.pussy] is now [style.boldGrow(pierced)]!</p>");
			
		} else {
			AbstractClothing c = owner.getClothingInSlot(InventorySlot.PIERCING_VAGINA);
			String piercingUnequip = "";
			if(c!=null) {
				owner.forceUnequipClothingIntoVoid(owner, c);
				piercingUnequip = owner.addClothing(c, false);
			}
			
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.NamePos] [npc.pussy] is [style.boldShrink(no longer pierced)]!"
					+ "</p>"
					+piercingUnequip);
		}
	}

	public boolean isEggLayer() {
		return eggLayer;
	}

	public String setEggLayer(GameCharacter owner, boolean eggLayer) {
		if(!Main.game.isStarted() || owner==null) {
			this.eggLayer = eggLayer;
			return "";
		}
		
		if(this.eggLayer == eggLayer || !owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		if(owner.isPregnant()) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a shocked gasp as an unpleasant tingling sensation suddenly spreads throughout [npc.her] lower abdomen."
						+ " Almost as soon as it arrived, however, this alarming feeling fades away, and [npc.name] [npc.verb(realise)] that [npc.her] ongoing pregnancy is preventing [npc.her] womb from being transformed!"
					+ "</p>");
		}
		
		this.eggLayer = eggLayer;
		
		if(eggLayer) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a shocked gasp as an unpleasant tingling sensation suddenly spreads throughout [npc.her] lower abdomen."
						+ " An intense cramp quickly replaces this feeling, causing [npc.namePos] gasp to turn into a distressed groan."
						+ "<br/>"
						+ "Thankfully, this uncomfortable transformation quickly runs its course, leaving [npc.name] panting for breath and instinctively knowing that [style.boldEgg([npc.she] will now lay eggs instead of birthing live young)]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a shocked gasp as an unpleasant tingling sensation suddenly spreads throughout [npc.her] lower abdomen."
						+ " An intense cramp quickly replaces this feeling, causing [npc.namePos] gasp to turn into a distressed groan."
						+ "<br/>"
						+ "Thankfully, this uncomfortable transformation quickly runs its course, leaving [npc.name] panting for breath and instinctively knowing that [style.boldSex([npc.she] will now birth live young instead of laying eggs)]!"
					+ "</p>");
		}
	
	}

	public Clitoris getClitoris() {
		return clitoris;
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Vagina.class) && getType().getRace().isFeralPartsAvailable());
	}
}
