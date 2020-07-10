package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.7
 * @author Innoxia
 */
public class Vagina implements BodyPartInterface {

	
	protected VaginaType type;
	protected Clitoris clitoris;
	protected int labiaSize;
	protected boolean pierced;
	
	protected OrificeVagina orificeVagina;
	protected FluidGirlCum girlcum;
	protected OrificeVaginaUrethra orificeUrethra;

	public Vagina(VaginaType type, int labiaSize, int clitSize, int wetness, float capacity, int depth, int elasticity, int plasticity, boolean virgin) {
		this.type = type;
		this.labiaSize = labiaSize;
		this.clitoris = new Clitoris(clitSize, PenetrationGirth.THREE_AVERAGE.getValue());
		pierced = false;
		
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
	public VaginaType getType() {
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
		
		if(this.getGirlcum().getFlavour()!=FluidFlavour.GIRL_CUM) {
			descriptorList.add(this.getGirlcum().getFlavour().getName()+"-flavoured");
		}
		
		if(owner.isVaginaBestial()) {
			descriptorList.add(Util.randomItemFrom(Util.newArrayListOfValues(
					(this.getType().getRace()==Race.HORSE_MORPH?"mare":null),
					"feral",
					owner.getVaginaRace().getName(owner, true)+"-",
					"bestial",
					"animalistic")));
		} else {
			descriptorList.add(type.getDescriptor(owner));
		}
		
		descriptorList.add(Capacity.getCapacityFromValue(orificeVagina.getStretchedCapacity()).getDescriptor().replaceAll(" ", "-"));

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
	
	public String setType(GameCharacter owner, VaginaType type) {
		return setType(owner, type, false);
	}
	
	public String setType(GameCharacter owner, VaginaType type, boolean overridePregnancyPrevention) {
		if(this.type==VaginaType.NONE) {
			this.orificeVagina.setStretchedCapacity(this.orificeVagina.getRawCapacityValue());
			this.orificeUrethra.setStretchedCapacity(this.orificeUrethra.getRawCapacityValue());
			this.orificeVagina.hymen=true;
		}
		
		if(!Main.game.isStarted() || owner==null) {// This always overrides pregnancy prevention, as the only times where this is true are for utility methods:
			this.type = type;
			this.girlcum.setType(type.getFluidType());
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
		
		UtilText.transformationContentSB.setLength(0);
		
		// Cannot transform if pregnant:
		if (!overridePregnancyPrevention && type==VaginaType.NONE && (owner.isPregnant() || owner.hasStatusEffect(StatusEffect.PREGNANT_0))) {
			UtilText.transformationContentSB.append(UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a lewd moan as [npc.she] [npc.verb(feel)] [npc.her] [npc.pussy+] starting to grow hot and sensitive,"
							+ " and as a wave of tingling excitement washes through [npc.her] lower abdomen, [npc.her] moan turns into a desperate gasp."
						+ " Much to [npc.her] surprise, the feeling fades away almost as quickly as it came, and with a sigh, [npc.she] [npc.verb(realise)] that "
						+ (owner.hasStatusEffect(StatusEffect.PREGNANT_0)
								?"<b>the possibility of being pregnant has prevented [npc.her] vagina from being removed</b>!"
								:"<b>[npc.her] ongoing pregnancy has prevented [npc.her] vagina from being removed</b>!")
						+ "<br/>"
						+ "[npc.NamePos] pussy remains [style.boldTfSex(unchanged)]."
					+ "</p>"));
			
			return UtilText.transformationContentSB.toString()
					+ "<p>"
						+owner.postTransformationCalculation()
					+"</p>";
		}
		
		// If have no vagina:
		if (owner.getVaginaType() == VaginaType.NONE) {
			UtilText.transformationContentSB.append(UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(blush)] as [npc.she] [npc.verb(feel)] a strange heat spreading through [npc.her] groin, and can't help but let out a low [npc.moan] as the [npc.skin] "
						+ (!owner.hasPenisIgnoreDildo()
							? "in the middle of [npc.her] groin,"
							: (!owner.isTaur()
									?"beneath [npc.her] cock,"
									:"above and behind [npc.her] cock,"))
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
				UtilText.transformationContentSB.append(UtilText.parse(owner,
						"[npc.Name] now [npc.has] a [style.colourExcellent(virgin)] [style.boldTfSex(vagina)], complete with an [style.colourExcellent(unbroken hymen)]!"));
			} else {
				UtilText.transformationContentSB.append(UtilText.parse(owner,
						"[npc.Name] now [npc.has] a new [style.boldTfSex(vagina)], and although [npc.she] can't consider [npc.herself] a virgin, [npc.she] at least [npc.has] an [style.colourExcellent(unbroken hymen)]!"));
			}
					
			UtilText.transformationContentSB.append("</p>");
				
			if(owner.hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
				if(owner.isVaginaVirgin()) {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
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
					UtilText.transformationContentSB.append(UtilText.parse(owner,
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
				return UtilText.transformationContentSB.toString()
						+ "<p>"
							+owner.postTransformationCalculation()
						+"</p>";
			} else {
				return UtilText.transformationContentSB.toString()
						+ owner.setVaginaType(type);
			}
			
		} else {
			if (owner.isPlayer()) {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "A strange heat suddenly washes through your pussy, and you cry out as it starts to transform.");
			} else {
				UtilText.transformationContentSB.append(UtilText.parse(owner,
						"<p>"
							+"[npc.Name] suddenly blushes and squeezes [npc.her] thighs together as [npc.she] [npc.verb(feel)] [npc.her] pussy starting to transform."));
			}
		}
		
		this.type = type;
		this.girlcum.setType(type.getFluidType());
		owner.resetAreaKnownByCharacters(CoverableArea.VAGINA);
		
		switch (type) {
			case NONE:
				if(owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You feel your pussy suddenly tighten and squeeze shut, and you let out a cry as a strange pressure fills your lower abdomen."
								+ " Thankfully, it doesn't last long, and when the discomfort subsides, you gasp as you suddenly realise that your cunt has vanished, along with all of your female reproductive organs."
								+ "<br/>"
								+ "You have [style.boldSex(lost your vagina)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
							" [npc.She] moans and squirms about as [npc.she] feels [npc.her] pussy suddenly tighten and squeeze shut, and with a desperate cry, a strange pressure washes through [npc.her] lower abdomen."
							+ " Thankfully for [npc.herHim], the feeling fades almost as quickly as it came, and when the discomfort subsides, [npc.she] gasps as [npc.she] suddenly realises that [npc.her] cunt has vanished,"
								+ " along with all of [npc.her] female reproductive organs."
							+ "<br/>"
							+ "[npc.Name] has [style.boldSex(lost [npc.her] vagina)]."
						+ "</p>"));
				}
				
				if(owner.isPlayer() && owner.hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					if(!owner.isVaginaVirgin()) {
						UtilText.transformationContentSB.append(UtilText.parse(owner,
								"<p style='text-align:center;'>"
									+ "[style.boldGood(Unbroken Virgin)]"
									+ "<br/><i>"
										+ "As the reality of losing [npc.her] worthless cunt sinks in, [npc.name] [npc.verb(start)] to think of [npc.herself] as something more than a dirty slut."
										+ " After all, if [npc.she] [npc.do]n't have a pussy, it's technically not possible for [npc.herHim] to have lost [npc.her] virginity!"
										+ " Letting out a deep sigh, [npc.she] [npc.verb(manage)] to convince [npc.herself] that [npc.she] no longer [npc.has] to worry about being a broken virgin."
									+ "<br/>"
										+ "Despite being able to finally see [npc.herself] as a dignified individual once again, the fact that [npc.sheIs] lacking the familiar feeling of having a pussy is making [npc.her] restless."
										+ " Perhaps if [npc.she] [npc.was] able to grow a new pussy, [npc.she]'d gain an unbroken hymen, thereby making [npc.herHim] a pure 'virgin' once again..."
									+ "</i><br/>"
									+ "[npc.NameIsFull] [style.boldGood(no longer a broken virgin)]!"
								+ "</p>"));
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner,
								"<p style='text-align:center;'>"
									+ "[style.boldBad(Pure Virgin?)]"
									+ "<br/><i>"
									+ "As the reality of losing [npc.her] vagina sinks in, [npc.name] [npc.verb(start)] to feel a rising panic in the back of [npc.her] mind."
									+ " After all, if [npc.she] [npc.do]n't have a pussy, [npc.she] can't be considered a pure virgin!"
									+ "</i><br/>"
									+ "Until [npc.name] can [npc.verb(regain)] [npc.her] lost vagina, [npc.she] will [style.boldBad(no longer be considered a pure virgin)]!"
								+ "</p>"
								));
					}
				}
				owner.setHymen(true);
				owner.setPiercedVagina(false);
				break;
			case HUMAN:
				if(owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" You let out a squeal of excitement as a wave of pleasure runs up from your groin, and you feel your slit shifting and contracting with a mind of its own."
							+ " Within moments, the feeling fades away, and you discover that your pussy has shaped itself into a regular human vagina."
							+ " As you let out a little sigh, a warm, tingling feeling spreads up through your lower abdomen, and you realise that your internal reproductive organs have also transformed into those of a human."
							+ "<br/>"
							+ "You now have a [style.boldHuman(human vagina)], with [pc.pussyColourPrimary(true)] labia and [pc.pussyColourSecondary(true)] internal walls."
						+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner, 
							" [npc.She] lets out a squeal of excitement as a wave of pleasure runs up through [npc.her] groin, and as [npc.she] feels [npc.her] slit shifting and contracting with a mind of its own,"
									+ " [npc.she] desperately clamps [npc.her] [npc.legs] shut."
							+ " Within moments, the feeling starts to fade away, and with a little cry of surprise, [npc.she] discovers that [npc.her] pussy has shaped itself into a regular human vagina."
							+ " A warm, tingling feeling spreads up through [npc.her] lower abdomen, and as the transformation comes to an end, [npc.she] realises that [npc.her] internal reproductive organs have also transformed into those of a human."
							+ "<br/>"
							+ "[npc.Name] now has a [style.boldHuman(human vagina)], with [npc.pussyColourPrimary(true)] labia and [npc.pussyColourSecondary(true)] internal walls."
						+ "</p>"));
				}
				break;
			case DEMON_COMMON:
				UtilText.transformationContentSB.append(UtilText.parse(owner,
						" [npc.She] [npc.verb(let)] out a squeal of excitement as a wave of pleasure runs up through [npc.her] groin, and as [npc.she] [npc.verb(feel)] [npc.her] slit shifting and contracting with a mind of its own,"
								+ " [npc.she] desperately [npc.verb(clamp)] [npc.her] [npc.legs] shut."
						+ " A strange, bubbling sensation starts running down deep into [npc.her] cunt,"
							+ " and [npc.she] [npc.verb(let)] out a lewd moan as [npc.she] [npc.verb(feel)] rows of little wriggling tentacles grow out to line [npc.her] inner vaginal walls."
						+ " Gasping for breath, [npc.she] [npc.verb(feel)] a new set of muscles forming within [npc.her] pussy, and with an experimental squeeze,"
							+ " [npc.she] [npc.verb(discover)] that [npc.she] has an incredible amount of control over [npc.her] pussy's new additions."
						+ " With one last shiver of pleasure, [npc.her] pussy reshapes its exterior into an absolutely perfect-looking vagina."
					+ "</p>"
					+ "<p>"
						+ "Just as [npc.she] [npc.verb(start)] think that the transformation has come to an end, [npc.her] pussy's new tentacles and muscles involuntarily clench down,"
							+ " and a desperate squeal escapes from between [npc.her] [npc.lips+] as a warm, tingling feeling spreads up through [npc.her] lower abdomen."
						+ " Images of fat demonic cocks slamming deep into [npc.her] new pussy flash before [npc.her] eyes,"
							+ " and [npc.her] squeal turns into a satisfied moan as [npc.she] [npc.verb(imagine)] them pumping their tainted seed deep into [npc.her] demonic womb."
						+ " Just as quickly as they came, the images fade from [npc.her] mind, and as one last wave of tingling pleasure washes through [npc.her] body,"
							+ " [npc.she] [npc.verb(feel)] [npc.her] female reproductive organs finishing their transformation."
						+ "<br/>"
						+ (!owner.isShortStature()
								?"[npc.Name] now [npc.has] a [style.boldDemon(demonic vagina)], with [npc.pussyColourPrimary(true)] labia and [npc.pussyColourSecondary(true)] internal walls."
								:"[npc.Name] now [npc.has] an [style.boldImp(impish vagina)], with [npc.pussyColourPrimary(true)] labia and [npc.pussyColourSecondary(true)] internal walls.")
					+ "</p>"));
				break;
			case DOG_MORPH:
				if(owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" You let out a squeal of excitement as a wave of pleasure runs up from your groin, and you feel your slit shifting and contracting with a mind of its own."
							+ " Within moments, the feeling fades away, and you discover that your pussy has shaped itself into a that of a dog-morph."
							+ " Although it looks like a normal human pussy, a short layer of soft "+owner.getCovering(type.getBodyCoveringType(owner)).getColourDescriptor(owner, false, false)
								+" fur has grown around it, giving your feminine sex a rather animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as you think that the transformation has come to an end, your pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between your [pc.lips+] as a warm, tingling feeling spreads up through your lower abdomen."
							+ " Images of knotted dog-like cocks slamming deep into your new pussy flash before your eyes, and your squeal turns into a satisfied moan as you imagine them pumping their potent seed deep into your canine womb."
							+ " Just as quickly as they came, the images fade from your mind, and as one last wave of tingling pleasure washes through your body, you feel your female reproductive organs finishing their transformation."
							+ "<br/>"
							+ "You now have a [style.boldDogMorph(canine vagina)], with [pc.pussyColourPrimary(true)] labia and [pc.pussyColourSecondary(true)] internal walls."
						+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner, 
							" [npc.She] lets out a squeal of excitement as a wave of pleasure runs up through [npc.her] groin, and as [npc.she] feels [npc.her] slit shifting and contracting with a mind of its own,"
									+ " [npc.she] desperately clamps [npc.her] [npc.legs] shut."
							+ " Within moments, the feeling fades away, and [npc.she] discovers that [npc.her] pussy has shaped itself into a that of a dog-morph."
							+ " Although it looks like a normal human pussy, a short layer of soft "+owner.getCovering(type.getBodyCoveringType(owner)).getColourDescriptor(owner, false, false)
								+" fur has grown around it, giving [npc.her] feminine sex a rather animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as [npc.she] starts think that the transformation has come to an end, [npc.her] pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between [npc.her] [npc.lips+] as a warm, tingling feeling spreads up through [npc.her] lower abdomen."
							+ " Images of knotted dog-like cocks slamming deep into [npc.her] new pussy flash before [npc.her] eyes,"
								+ " and [npc.her] squeal turns into a satisfied moan as [npc.she] imagines them pumping their potent seed deep into [npc.her] canine womb."
							+ " Just as quickly as they came, the images fade from [npc.her] mind, and as one last wave of tingling pleasure washes through [npc.her] body,"
								+ " [npc.she] feels [npc.her] female reproductive organs finishing their transformation."
							+ "<br/>"
							+ "[npc.Name] now has a [style.boldDogMorph(canine vagina)], with [npc.pussyColourPrimary(true)] labia and [npc.pussyColourSecondary(true)] internal walls."
						+ "</p>"));
				}
				break;
			case FOX_MORPH:
				if(owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" You let out a squeal of excitement as a wave of pleasure runs up from your groin, and you feel your slit shifting and contracting with a mind of its own."
							+ " Within moments, the feeling fades away, and you discover that your pussy has shaped itself into a that of a fox-morph."
							+ " Although it looks like a normal human pussy, a short layer of soft "+owner.getCovering(type.getBodyCoveringType(owner)).getColourDescriptor(owner, false, false)
								+" fur has grown around it, giving your feminine sex a rather animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as you think that the transformation has come to an end, your pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between your [pc.lips+] as a warm, tingling feeling spreads up through your lower abdomen."
							+ " Images of knotted fox-like cocks slamming deep into your new pussy flash before your eyes, and your squeal turns into a satisfied moan as you imagine them pumping their potent seed deep into your vulpine womb."
							+ " Just as quickly as they came, the images fade from your mind, and as one last wave of tingling pleasure washes through your body, you feel your female reproductive organs finishing their transformation."
							+ "</br>"
							+ "You now have a [style.boldFoxMorph(vulpine vagina)], with [pc.pussyColourPrimary(true)] labia and [pc.pussyColourSecondary(true)] internal walls."
						+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner, 
							" [npc.She] lets out a squeal of excitement as a wave of pleasure runs up through [npc.her] groin, and as [npc.she] feels [npc.her] slit shifting and contracting with a mind of its own,"
									+ " [npc.she] desperately clamps [npc.her] [npc.legs] shut."
							+ " Within moments, the feeling fades away, and [npc.she] discovers that [npc.her] pussy has shaped itself into a that of a fox-morph."
							+ " Although it looks like a normal human pussy, a short layer of soft "+owner.getCovering(type.getBodyCoveringType(owner)).getColourDescriptor(owner, false, false)
								+" fur has grown around it, giving [npc.her] feminine sex a rather animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as [npc.she] starts think that the transformation has come to an end, [npc.her] pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between [npc.her] [npc.lips+] as a warm, tingling feeling spreads up through [npc.her] lower abdomen."
							+ " Images of knotted fox-like cocks slamming deep into [npc.her] new pussy flash before [npc.her] eyes,"
								+ " and [npc.her] squeal turns into a satisfied moan as [npc.she] imagines them pumping their potent seed deep into [npc.her] vulpine womb."
							+ " Just as quickly as they came, the images fade from [npc.her] mind, and as one last wave of tingling pleasure washes through [npc.her] body,"
								+ " [npc.she] feels [npc.her] female reproductive organs finishing their transformation."
							+ "</br>"
							+ "[npc.Name] now has a [style.boldFoxMorph(vulpine vagina)], with [npc.pussyColourPrimary(true)] labia and [npc.pussyColourSecondary(true)] internal walls."
						+ "</p>"));
				}
				break;
			case WOLF_MORPH:
				if(owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" You let out a squeal of excitement as a wave of pleasure runs up from your groin, and you feel your slit shifting and contracting with a mind of its own."
							+ " Within moments, the feeling fades away, and you discover that your pussy has shaped itself into a that of a wolf-morph."
							+ " Although it looks like a normal human pussy, a short layer of soft "+owner.getCovering(type.getBodyCoveringType(owner)).getColourDescriptor(owner, false, false)
								+" fur has grown around it, giving your feminine sex a rather animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as you think that the transformation has come to an end, your pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between your [pc.lips+] as a warm, tingling feeling spreads up through your lower abdomen."
							+ " Images of knotted wolf-like cocks slamming deep into your new pussy flash before your eyes, and your squeal turns into a satisfied moan as you imagine them pumping their potent seed deep into your lupine womb."
							+ " Just as quickly as they came, the images fade from your mind, and as one last wave of tingling pleasure washes through your body, you feel your female reproductive organs finishing their transformation."
							+ "<br/>"
							+ "You now have a [style.boldWolfMorph(lupine vagina)], with [pc.pussyColourPrimary(true)] labia and [pc.pussyColourSecondary(true)] internal walls."
						+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner, 
							" [npc.She] lets out a squeal of excitement as a wave of pleasure runs up through [npc.her] groin, and as [npc.she] feels [npc.her] slit shifting and contracting with a mind of its own,"
									+ " [npc.she] desperately clamps [npc.her] [npc.legs] shut."
							+ " Within moments, the feeling fades away, and [npc.she] discovers that [npc.her] pussy has shaped itself into a that of a wolf-morph."
							+ " Although it looks like a normal human pussy, a short layer of soft "+owner.getCovering(type.getBodyCoveringType(owner)).getColourDescriptor(owner, false, false)
								+" fur has grown around it, giving [npc.her] feminine sex a rather animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as [npc.she] starts think that the transformation has come to an end, [npc.her] pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between [npc.her] [npc.lips+] as a warm, tingling feeling spreads up through [npc.her] lower abdomen."
							+ " Images of knotted wolf-like cocks slamming deep into [npc.her] new pussy flash before [npc.her] eyes,"
								+ " and [npc.her] squeal turns into a satisfied moan as [npc.she] imagines them pumping their potent seed deep into [npc.her] lupine womb."
							+ " Just as quickly as they came, the images fade from [npc.her] mind, and as one last wave of tingling pleasure washes through [npc.her] body,"
								+ " [npc.she] feels [npc.her] female reproductive organs finishing their transformation."
							+ "<br/>"
							+ "[npc.Name] now has a [style.boldWolfMorph(lupine vagina)], with [npc.pussyColourPrimary(true)] labia and [npc.pussyColourSecondary(true)] internal walls."
						+ "</p>"));
				}
				break;
			case CAT_MORPH:
				if(owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" You let out a squeal of excitement as a wave of pleasure runs up from your groin, and you feel your slit shifting and contracting with a mind of its own."
							+ " Within moments, the feeling fades away, and you discover that your pussy has shaped itself into a that of a cat-morph."
							+ " Although it looks like a normal human pussy, a short layer of soft "+owner.getCovering(type.getBodyCoveringType(owner)).getColourDescriptor(owner, false, false)
								+" fur has grown around it, giving your feminine sex a rather animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as you think that the transformation has come to an end, your pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between your [pc.lips+] as a warm, tingling feeling spreads up through your lower abdomen."
							+ " Images of barbed cat-like cocks slamming deep into your new pussy flash before your eyes, and your squeal turns into a satisfied moan as you imagine them pumping their potent seed deep into your feline womb."
							+ " Just as quickly as they came, the images fade from your mind, and as one last wave of tingling pleasure washes through your body, you feel your female reproductive organs finishing their transformation."
							+ "<br/>"
							+ "You now have a [style.boldCatMorph(feline vagina)], with [pc.pussyColourPrimary(true)] labia and [pc.pussyColourSecondary(true)] internal walls."
						+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner, 
							" [npc.She] lets out a squeal of excitement as a wave of pleasure runs up through [npc.her] groin, and as [npc.she] feels [npc.her] slit shifting and contracting with a mind of its own,"
									+ " [npc.she] desperately clamps [npc.her] [npc.legs] shut."
							+ " Within moments, the feeling fades away, and [npc.she] discovers that [npc.her] pussy has shaped itself into a that of a cat-morph."
							+ " Although it looks like a normal human pussy, a short layer of soft "+owner.getCovering(type.getBodyCoveringType(owner)).getColourDescriptor(owner, false, false)
								+" fur has grown around it, giving [npc.her] feminine sex a rather animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as [npc.she] starts think that the transformation has come to an end, [npc.her] pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between [npc.her] [npc.lips+] as a warm, tingling feeling spreads up through [npc.her] lower abdomen."
							+ " Images of barbed cat-like cocks slamming deep into [npc.her] new pussy flash before [npc.her] eyes,"
								+ " and [npc.her] squeal turns into a satisfied moan as [npc.she] imagines them pumping their potent seed deep into [npc.her] feline womb."
							+ " Just as quickly as they came, the images fade from [npc.her] mind, and as one last wave of tingling pleasure washes through [npc.her] body,"
								+ " [npc.she] feels [npc.her] female reproductive organs finishing their transformation."
							+ "<br/>"
							+ "[npc.Name] now has a [style.boldCatMorph(feline vagina)], with [npc.pussyColourPrimary(true)] labia and [npc.pussyColourSecondary(true)] internal walls."
						+ "</p>"));
				}
				break;
			case SQUIRREL_MORPH:
				if(owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" You let out a squeal of excitement as a wave of pleasure runs up from your groin, and you feel your slit shifting and contracting with a mind of its own."
							+ " Within moments, the feeling fades away, and you discover that your pussy has shaped itself into a that of a squirrel-morph."
							+ " Although it looks like a normal human pussy, a short layer of soft "+owner.getCovering(type.getBodyCoveringType(owner)).getColourDescriptor(owner, false, false)
								+" fur has grown around it, giving your feminine sex a rather animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as you think that the transformation has come to an end, your pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between your [pc.lips+] as a warm, tingling feeling spreads up through your lower abdomen."
							+ " Images of squirrel-like cocks slamming deep into your new pussy flash before your eyes, and your squeal turns into a satisfied moan as you imagine them pumping their potent seed deep into your rodent womb."
							+ " Just as quickly as they came, the images fade from your mind, and as one last wave of tingling pleasure washes through your body, you feel your female reproductive organs finishing their transformation."
							+ "<br/>"
							+ "You now have a [style.boldSquirrelMorph(squirrel-morph's vagina)], with [pc.pussyColourPrimary(true)] labia and [pc.pussyColourSecondary(true)] internal walls."
						+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner, 
							" [npc.She] lets out a squeal of excitement as a wave of pleasure runs up through [npc.her] groin, and as [npc.she] feels [npc.her] slit shifting and contracting with a mind of its own,"
									+ " [npc.she] desperately clamps [npc.her] [npc.legs] shut."
							+ " Within moments, the feeling fades away, and [npc.she] discovers that [npc.her] pussy has shaped itself into a that of a squirrel-morph."
							+ " Although it looks like a normal human pussy, a short layer of soft "+owner.getCovering(type.getBodyCoveringType(owner)).getColourDescriptor(owner, false, false)
								+" fur has grown around it, giving [npc.her] feminine sex a rather animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as [npc.she] starts think that the transformation has come to an end, [npc.her] pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between [npc.her] [npc.lips+] as a warm, tingling feeling spreads up through [npc.her] lower abdomen."
							+ " Images of squirrel-like cocks slamming deep into [npc.her] new pussy flash before [npc.her] eyes,"
								+ " and [npc.her] squeal turns into a satisfied moan as [npc.she] imagines them pumping their potent seed deep into [npc.her] rodent womb."
							+ " Just as quickly as they came, the images fade from [npc.her] mind, and as one last wave of tingling pleasure washes through [npc.her] body,"
								+ " [npc.she] feels [npc.her] female reproductive organs finishing their transformation."
							+ "<br/>"
							+ "[npc.Name] now has a [style.boldSquirrelMorph(squirrel-morph's vagina)], with [npc.pussyColourPrimary(true)] labia and [npc.pussyColourSecondary(true)] internal walls."
						+ "</p>"));
				}
				break;
			case RAT_MORPH:
				if(owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" You let out a squeal of excitement as a wave of pleasure runs up from your groin, and you feel your slit shifting and contracting with a mind of its own."
							+ " Within moments, the feeling fades away, and you discover that your pussy has shaped itself into a that of a rat-morph."
							+ " Although it looks like a normal human pussy, a short layer of soft "+owner.getCovering(type.getBodyCoveringType(owner)).getColourDescriptor(owner, false, false)
								+" fur has grown around it, giving your feminine sex a rather animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as you think that the transformation has come to an end, your pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between your [pc.lips+] as a warm, tingling feeling spreads up through your lower abdomen."
							+ " Images of rat-like cocks slamming deep into your new pussy flash before your eyes, and your squeal turns into a satisfied moan as you imagine them pumping their potent seed deep into your rodent womb."
							+ " Just as quickly as they came, the images fade from your mind, and as one last wave of tingling pleasure washes through your body, you feel your female reproductive organs finishing their transformation."
							+ "<br/>"
							+ "You now have a [style.boldRatMorph(rat-morph's vagina)], with [pc.pussyColourPrimary(true)] labia and [pc.pussyColourSecondary(true)] internal walls."
						+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner, 
							" [npc.She] lets out a squeal of excitement as a wave of pleasure runs up through [npc.her] groin, and as [npc.she] feels [npc.her] slit shifting and contracting with a mind of its own,"
									+ " [npc.she] desperately clamps [npc.her] [npc.legs] shut."
							+ " Within moments, the feeling fades away, and [npc.she] discovers that [npc.her] pussy has shaped itself into a that of a rat-morph."
							+ " Although it looks like a normal human pussy, a short layer of soft "+owner.getCovering(type.getBodyCoveringType(owner)).getColourDescriptor(owner, false, false)
								+" fur has grown around it, giving [npc.her] feminine sex a rather animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as [npc.she] starts think that the transformation has come to an end, [npc.her] pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between [npc.her] [npc.lips+] as a warm, tingling feeling spreads up through [npc.her] lower abdomen."
							+ " Images of rat-like cocks slamming deep into [npc.her] new pussy flash before [npc.her] eyes,"
								+ " and [npc.her] squeal turns into a satisfied moan as [npc.she] imagines them pumping their potent seed deep into [npc.her] rodent womb."
							+ " Just as quickly as they came, the images fade from [npc.her] mind, and as one last wave of tingling pleasure washes through [npc.her] body,"
								+ " [npc.she] feels [npc.her] female reproductive organs finishing their transformation."
							+ "<br/>"
							+ "[npc.Name] now has a [style.boldRatMorph(rat-morph's vagina)], with [npc.pussyColourPrimary(true)] labia and [npc.pussyColourSecondary(true)] internal walls."
						+ "</p>"));
				}
				break;
			case RABBIT_MORPH:
				if(owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" You let out a squeal of excitement as a wave of pleasure runs up from your groin, and you feel your slit shifting and contracting with a mind of its own."
							+ " Within moments, the feeling fades away, and you discover that your pussy has shaped itself into a that of a rabbit-morph."
							+ " Although it looks like a normal human pussy, a short layer of soft "+owner.getCovering(type.getBodyCoveringType(owner)).getColourDescriptor(owner, false, false)
								+" fur has grown around it, giving your feminine sex a rather animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as you think that the transformation has come to an end, your pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between your [pc.lips+] as a warm, tingling feeling spreads up through your lower abdomen."
							+ " Images of rabbit-like cocks slamming deep into your new pussy flash before your eyes, and your squeal turns into a satisfied moan as you imagine them pumping their potent seed deep into your fertile womb."
							+ " Just as quickly as they came, the images fade from your mind, and as one last wave of tingling pleasure washes through your body, you feel your female reproductive organs finishing their transformation."
							+ "<br/>"
							+ "You now have a [style.boldRabbitMorph(rabbit-morph's vagina)], with [pc.pussyColourPrimary(true)] labia and [pc.pussyColourSecondary(true)] internal walls."
						+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner, 
							" [npc.She] lets out a squeal of excitement as a wave of pleasure runs up through [npc.her] groin, and as [npc.she] feels [npc.her] slit shifting and contracting with a mind of its own,"
									+ " [npc.she] desperately clamps [npc.her] [npc.legs] shut."
							+ " Within moments, the feeling fades away, and [npc.she] discovers that [npc.her] pussy has shaped itself into a that of a rabbit-morph."
							+ " Although it looks like a normal human pussy, a short layer of soft "+owner.getCovering(type.getBodyCoveringType(owner)).getColourDescriptor(owner, false, false)
								+" fur has grown around it, giving [npc.her] feminine sex a rather animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as [npc.she] starts think that the transformation has come to an end, [npc.her] pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between [npc.her] [npc.lips+] as a warm, tingling feeling spreads up through [npc.her] lower abdomen."
							+ " Images of rabbit-like cocks slamming deep into [npc.her] new pussy flash before [npc.her] eyes,"
								+ " and [npc.her] squeal turns into a satisfied moan as [npc.she] imagines them pumping their potent seed deep into [npc.her] fertile womb."
							+ " Just as quickly as they came, the images fade from [npc.her] mind, and as one last wave of tingling pleasure washes through [npc.her] body,"
								+ " [npc.she] feels [npc.her] female reproductive organs finishing their transformation."
							+ "<br/>"
							+ "[npc.Name] now has a [style.boldRabbitMorph(rabbit-morph's vagina)], with [npc.pussyColourPrimary(true)] labia and [npc.pussyColourSecondary(true)] internal walls."
						+ "</p>"));
				}
				break;
			case BAT_MORPH:
				if(owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" You let out a squeal of excitement as a wave of pleasure runs up from your groin, and you feel your slit shifting and contracting with a mind of its own."
							+ " Within moments, the feeling fades away, and you discover that your pussy has shaped itself into a that of a bat-morph."
							+ " Although it looks like a normal human pussy, a short layer of soft "+owner.getCovering(type.getBodyCoveringType(owner)).getColourDescriptor(owner, false, false)
								+" fur has grown around it, giving your feminine sex a rather animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as you think that the transformation has come to an end, your pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between your [pc.lips+] as a warm, tingling feeling spreads up through your lower abdomen."
							+ " Images of bat-like cocks slamming deep into your new pussy flash before your eyes, and your squeal turns into a satisfied moan as you imagine them pumping their potent seed deep into your womb."
							+ " Just as quickly as they came, the images fade from your mind, and as one last wave of tingling pleasure washes through your body, you feel your female reproductive organs finishing their transformation."
							+ "<br/>"
							+ "You now have a [style.boldBatMorph(bat-morph's vagina)], with [pc.pussyColourPrimary(true)] labia and [pc.pussyColourSecondary(true)] internal walls."
						+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner, 
							" [npc.She] lets out a squeal of excitement as a wave of pleasure runs up through [npc.her] groin, and as [npc.she] feels [npc.her] slit shifting and contracting with a mind of its own,"
									+ " [npc.she] desperately clamps [npc.her] [npc.legs] shut."
							+ " Within moments, the feeling fades away, and [npc.she] discovers that [npc.her] pussy has shaped itself into a that of a bat-morph."
							+ " Although it looks like a normal human pussy, a short layer of soft "+owner.getCovering(type.getBodyCoveringType(owner)).getColourDescriptor(owner, false, false)
								+" fur has grown around it, giving [npc.her] feminine sex a rather animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as [npc.she] starts think that the transformation has come to an end, [npc.her] pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between [npc.her] [npc.lips+] as a warm, tingling feeling spreads up through [npc.her] lower abdomen."
							+ " Images of bat-like cocks slamming deep into [npc.her] new pussy flash before [npc.her] eyes,"
								+ " and [npc.her] squeal turns into a satisfied moan as [npc.she] imagines them pumping their potent seed deep into [npc.her] womb."
							+ " Just as quickly as they came, the images fade from [npc.her] mind, and as one last wave of tingling pleasure washes through [npc.her] body,"
								+ " [npc.she] feels [npc.her] female reproductive organs finishing their transformation."
							+ "<br/>"
							+ "[npc.Name] now has a [style.boldBatMorph(bat-morph's vagina)], with [npc.pussyColourPrimary(true)] labia and [npc.pussyColourSecondary(true)] internal walls."
						+ "</p>"));
				}
				break;
			case HORSE_MORPH:
				if(owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" You let out a squeal of excitement as a wave of pleasure runs up from your groin, and you feel your slit shifting and contracting with a mind of its own."
							+ " Your pussy lips puff up as your cunt reshapes itself into an animalistic, horse-like vagina."
						+ "</p>"
						+ "<p>"
							+ "Just as you think that the transformation has come to an end, your pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between your [pc.lips+] as a warm, tingling feeling spreads up through your lower abdomen."
							+ " Images of huge, flared horse-cocks slamming deep into your new pussy flash before your eyes, and your squeal turns into a satisfied moan as you imagine them pumping their hot seed deep into your equine womb."
							+ " Just as quickly as they came, the images fade from your mind, and as one last wave of tingling pleasure washes through your body, you feel your female reproductive organs finishing their transformation."
							+ "<br/>"
							+ "You now have an [style.boldHorseMorph(equine vagina)], with [pc.pussyColourPrimary(true)] labia and [pc.pussyColourSecondary(true)] internal walls."
						+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner, 
							" [npc.She] lets out a squeal of excitement as a wave of pleasure runs up through [npc.her] groin, and as [npc.she] feels [npc.her] slit shifting and contracting with a mind of its own,"
									+ " [npc.she] desperately clamps [npc.her] [npc.legs] shut."
							+ " [npc.Her] pussy lips puff up, and [npc.she] starts moaning and squirming as [npc.her] cunt reshapes itself into a horse-like vagina, giving [npc.her] feminine sex a very animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as [npc.she] starts think that the transformation has come to an end, [npc.her] pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between [npc.her] [npc.lips+] as a warm, tingling feeling spreads up through [npc.her] lower abdomen."
							+ " Images of huge, flared horse-cocks slamming deep into [npc.her] new pussy flash before [npc.her] eyes,"
								+ " and [npc.her] squeal turns into a satisfied moan as [npc.she] imagines them pumping their potent seed deep into [npc.her] equine womb."
							+ " Just as quickly as they came, the images fade from [npc.her] mind, and as one last wave of tingling pleasure washes through [npc.her] body,"
								+ " [npc.she] feels [npc.her] female reproductive organs finishing their transformation."
							+ "<br/>"
							+ "[npc.Name] now has an [style.boldHorseMorph(equine vagina)], with [npc.pussyColourPrimary(true)] labia and [npc.pussyColourSecondary(true)] internal walls."
						+ "</p>"));
				}
				break;
			case REINDEER_MORPH:
				if(owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" You let out a squeal of excitement as a wave of pleasure runs up from your groin, and you feel your pussy shifting and contracting with a mind of its own."
							+ " Your [pc.labia] puff up as your cunt reshapes itself into an animalistic, reindeer-like vagina."
						+ "</p>"
						+ "<p>"
							+ "Just as you think that the transformation has come to an end, your pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between your [pc.lips+] as a warm, tingling feeling spreads up through your lower abdomen."
							+ " Images of huge, flared reindeer-cocks slamming deep into your new pussy flash before your eyes, and your squeal turns into a satisfied moan as you imagine them pumping their hot seed deep into your reindeer womb."
							+ " Just as quickly as they came, the images fade from your mind, and as one last wave of tingling pleasure washes through your body, you feel your female reproductive organs finishing their transformation."
							+ "<br/>"
							+ "You now have a [style.boldReindeerMorph(reindeer vagina)], with [pc.pussyColourPrimary(true)] labia and [pc.pussyColourSecondary(true)] internal walls."
						+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner, 
							" [npc.She] lets out a squeal of excitement as a wave of pleasure runs up through [npc.her] groin, and as [npc.she] feels [npc.her] pussy shifting and contracting with a mind of its own,"
									+ " [npc.she] desperately clamps [npc.her] [npc.legs] shut."
							+ " [npc.Her] [npc.labia] puff up, and [npc.she] starts moaning and squirming as [npc.her] cunt reshapes itself into a reindeer-like vagina, giving [npc.her] feminine sex a very animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as [npc.she] starts think that the transformation has come to an end, [npc.her] pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between [npc.her] [npc.lips+] as a warm, tingling feeling spreads up through [npc.her] lower abdomen."
							+ " Images of huge, flared reindeer-cocks slamming deep into [npc.her] new pussy flash before [npc.her] eyes,"
								+ " and [npc.her] squeal turns into a satisfied moan as [npc.she] imagines them pumping their potent seed deep into [npc.her] reindeer womb."
							+ " Just as quickly as they came, the images fade from [npc.her] mind, and as one last wave of tingling pleasure washes through [npc.her] body,"
								+ " [npc.she] feels [npc.her] female reproductive organs finishing their transformation."
							+ "<br/>"
							+ "[npc.Name] now has a [style.boldReindeerMorph(reindeer vagina)], with [npc.pussyColourPrimary(true)] labia and [npc.pussyColourSecondary(true)] internal walls."
						+ "</p>"));
				}
				break;
			case COW_MORPH:
				if(owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" You let out a squeal of excitement as a wave of pleasure runs up from your groin, and you feel your slit shifting and contracting with a mind of its own."
									+ " Your pussy lips puff up as your cunt reshapes itself into an animalistic, cow-like vagina."
						+ "</p>"
						+ "<p>"
							+ "Just as you think that the transformation has come to an end, your pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between your [pc.lips+] as a warm, tingling feeling spreads up through your lower abdomen."
							+ " Images of huge, flared bull-cocks slamming deep into your new pussy flash before your eyes, and your squeal turns into a satisfied moan as you imagine them pumping their hot seed deep into your bovine womb."
							+ " Just as quickly as they came, the images fade from your mind, and as one last wave of tingling pleasure washes through your body, you feel your female reproductive organs finishing their transformation."
							+ "<br/>"
							+ "You now have an [style.boldCowMorph(bovine vagina)], with [pc.pussyColourPrimary(true)] labia and [pc.pussyColourSecondary(true)] internal walls."
						+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner, 
							" [npc.She] lets out a squeal of excitement as a wave of pleasure runs up through [npc.her] groin, and as [npc.she] feels [npc.her] slit shifting and contracting with a mind of its own,"
									+ " [npc.she] desperately clamps [npc.her] [npc.legs] shut."
							+ " [npc.Her] pussy lips puff up and darken to a deep black, and [npc.she] starts moaning and squirming as [npc.her] cunt reshapes itself into a cow-like vagina, giving [npc.her] feminine sex a very animalistic appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as [npc.she] starts think that the transformation has come to an end, [npc.her] pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between [npc.her] [npc.lips+] as a warm, tingling feeling spreads up through [npc.her] lower abdomen."
							+ " Images of huge, flared bull-cocks slamming deep into [npc.her] new pussy flash before [npc.her] eyes,"
								+ " and [npc.her] squeal turns into a satisfied moan as [npc.she] imagines them pumping their potent seed deep into [npc.her] bovine womb."
							+ " Just as quickly as they came, the images fade from [npc.her] mind, and as one last wave of tingling pleasure washes through [npc.her] body,"
								+ " [npc.she] feels [npc.her] female reproductive organs finishing their transformation."
							+ "<br/>"
							+ "[npc.Name] now has an [style.boldCowMorph(bovine vagina)], with [npc.pussyColourPrimary(true)] labia and [npc.pussyColourSecondary(true)] internal walls."
						+ "</p>"));
				}
				break;
			case ALLIGATOR_MORPH:
				if(owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" You let out a squeal of excitement as a wave of pleasure runs up from your groin, and you feel your slit shifting and contracting with a mind of its own."
							+ " Within moments, the feeling fades away, and you discover that your pussy has transformed into that of an alligator-morph's."
							+ " Although the shape and structure remains similar to a normal human's pussy, a short layer of soft "+owner.getCovering(type.getBodyCoveringType(owner)).getColourDescriptor(owner, false, false)
								+" scales have grown around it, giving your feminine sex a rather reptilian appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as you think that the transformation has come to an end, your pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between your [pc.lips+] as a warm, tingling feeling spreads up through your lower abdomen."
							+ " Images of spreading your legs before huge, throbbing alligator cocks flash before your eyes,"
								+ " and your squeal turns into a satisfied moan as you imagine them thrusting deep inside of you before squirting their hot seed deep into your hungry reptilian cunt."
							+ " Just as quickly as they came, the images fade from your mind, and as one last wave of tingling pleasure washes through your body, you feel your female reproductive organs finishing their transformation."
							+ "<br/>"
							+ "You now have an [style.boldGatorMorph(alligator-morph's vagina)], with [pc.pussyColourPrimary(true)] labia and [pc.pussyColourSecondary(true)] internal walls."
						+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner, 
							" [npc.She] lets out a squeal of excitement as a wave of pleasure runs up through [npc.her] groin, and as [npc.she] feels [npc.her] slit shifting and contracting with a mind of its own,"
									+ " [npc.she] desperately clamps [npc.her] [npc.legs] shut."
							+ " Within moments, the feeling fades away, and [npc.she] discovers that [npc.her] pussy has transformed into that of an alligator-morph's."
							+ " Although the shape and structure remains similar to a normal human's pussy, a short layer of soft "+owner.getCovering(type.getBodyCoveringType(owner)).getColourDescriptor(owner, false, false)
								+" scales have grown around it, giving [npc.her] feminine sex a rather reptilian appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as [npc.she] starts think that the transformation has come to an end, [npc.her] pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between [npc.her] [npc.lips+] as a warm, tingling feeling spreads up through [npc.her] lower abdomen."
							+ " Images of spreading [npc.her] legs before huge, throbbing alligator cocks flash before [npc.her] eyes,"
								+ " and [npc.her] squeal turns into a satisfied moan as [npc.she] imagines them thrusting deep inside of [npc.herHim] before squirting their hot seed deep into [npc.her] hungry reptilian cunt."
							+ " Just as quickly as they came, the images fade from [npc.her] mind, and as one last wave of tingling pleasure washes through [npc.her] body,"
								+ " [npc.she] feels [npc.her] female reproductive organs finishing their transformation."
							+ "<br/>"
							+ "[npc.Name] now has an [style.boldGatorMorph(alligator-morph's vagina)], with [npc.pussyColourPrimary(true)] labia and [npc.pussyColourSecondary(true)] internal walls."
						+ "</p>"));
				}
				break;
			case HARPY:
				if(owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" You let out a squeal of excitement as a wave of pleasure runs up from your groin, and you feel your slit shifting and contracting with a mind of its own."
							+ " Within moments, the feeling fades away, and you discover that your pussy has shaped itself into a that of a harpy."
							+ " Although it looks like a normal human pussy, a short layer of soft "+owner.getCovering(type.getBodyCoveringType(owner)).getColourDescriptor(owner, false, false)
								+" feathers have grown around it, giving your feminine sex a rather avian appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as you think that the transformation has come to an end, your pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between your [pc.lips+] as a warm, tingling feeling spreads up through your lower abdomen."
							+ " Images of slamming your pussy down on cute little harpy cocks flash before your eyes, and your squeal turns into a satisfied moan as you imagine them squirting their hot seed deep into your hungry avian cunt."
							+ " Just as quickly as they came, the images fade from your mind, and as one last wave of tingling pleasure washes through your body, you feel your female reproductive organs finishing their transformation."
							+ "<br/>"
							+ "You now have an [style.boldHarpy(avian vagina)], with [pc.pussyColourPrimary(true)] labia and [pc.pussyColourSecondary(true)] internal walls."
						+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner, 
							" [npc.She] lets out a squeal of excitement as a wave of pleasure runs up through [npc.her] groin, and as [npc.she] feels [npc.her] slit shifting and contracting with a mind of its own,"
									+ " [npc.she] desperately clamps [npc.her] [npc.legs] shut."
							+ " Within moments, the feeling fades away, and [npc.she] discovers that [npc.her] pussy has shaped itself into a that of a harpy."
							+ " Although it looks like a normal human pussy, a short layer of soft "+owner.getCovering(type.getBodyCoveringType(owner)).getColourDescriptor(owner, false, false)
								+" feathers have grown around it, giving [npc.her] feminine sex a rather avian appearance."
						+ "</p>"
						+ "<p>"
							+ "Just as [npc.she] starts think that the transformation has come to an end, [npc.her] pussy involuntarily clenches down,"
								+ " and a desperate squeal escapes from between [npc.her] [npc.lips+] as a warm, tingling feeling spreads up through [npc.her] lower abdomen."
							+ " Images of slamming [npc.her] pussy down on cute little harpy cocks flash before [npc.her] eyes,"
								+ " and [npc.her] squeal turns into a satisfied moan as [npc.she] imagines them squirting their hot seed deep into [npc.her] hungry avian cunt."
							+ " Just as quickly as they came, the images fade from [npc.her] mind, and as one last wave of tingling pleasure washes through [npc.her] body,"
								+ " [npc.she] feels [npc.her] female reproductive organs finishing their transformation."
							+ "<br/>"
							+ "[npc.Name] now has an [style.boldHarpy(avian vagina)], with [npc.pussyColourPrimary(true)] labia and [npc.pussyColourSecondary(true)] internal walls."
						+ "</p>"));
				}
				break;
			case ANGEL://TODO
				break;
		}
		
		orificeVagina.getOrificeModifiers().clear();
		for(OrificeModifier om : type.getDefaultRacialOrificeModifiers()) {
			orificeVagina.addOrificeModifier(owner, om);
		}

		UtilText.transformationContentSB.append(
				"<p>"
				+ "Any old modifiers which [npc.her] pussy might have had have [style.boldShrink(transformed away)]!");
		
		if(orificeVagina.getOrificeModifiers().isEmpty()) {
			UtilText.transformationContentSB.append("</p>");
		} else {
			if (owner.isPlayer()) {
				UtilText.transformationContentSB.append(
						"<br/>"
						+ "Instead, your new pussy is:");
			} else {
				UtilText.transformationContentSB.append(
						"<br/>"
						+ "Instead, [npc.her] new pussy is:");
			}
			
			for(OrificeModifier om : orificeVagina.getOrificeModifiers()) {
				UtilText.transformationContentSB.append("<br/>[style.boldGrow("+Util.capitaliseSentence(om.getName())+")]");
			}
			UtilText.transformationContentSB.append("</p>");
		}
		
		return UtilText.transformationContentSB.toString()
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
		if(this.pierced == pierced || !owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		this.pierced = pierced;
		
		if(pierced) {
			if(owner.isPlayer()) {
				return "<p>Your [pc.pussy] is now [style.boldGrow(pierced)]!</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.NamePos] [npc.pussy] is now [style.boldGrow(pierced)]!</p>");
			}
		} else {
			AbstractClothing c = owner.getClothingInSlot(InventorySlot.PIERCING_VAGINA);
			String piercingUnequip = "";
			if(c!=null) {
				owner.forceUnequipClothingIntoVoid(owner, c);
				piercingUnequip = owner.addClothing(c, false);
			}
			
			if(owner.isPlayer()) {
				return "<p>"
							+ "Your [pc.pussy] is [style.boldShrink(no longer pierced)]!"
						+ "</p>"
						+piercingUnequip;
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.NamePos] [npc.pussy] is [style.boldShrink(no longer pierced)]!"
						+ "</p>"
						+piercingUnequip);
			}
		}
	}

	public Clitoris getClitoris() {
		return clitoris;
	}

	@Override
	public boolean isBestial(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.getLegConfiguration().getBestialParts().contains(Vagina.class) && getType().getRace().isBestialPartsAvailable();
	}
}
