package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
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
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.3.7
 * @author Innoxia
 */
public class Penis implements BodyPartInterface {

	
	public static final float TWO_PENIS_SIZE_MULTIPLIER = 1.6f;

	protected PenisType type;
	protected int length;
	protected int girth;
	protected boolean pierced;
	protected boolean virgin;
	protected Set<PenetrationModifier> penisModifiers;
	
	protected Testicle testicle;
	protected OrificePenisUrethra orificeUrethra;

	public Penis(PenisType type, int length, boolean usePenisSizePreference, int girth, int testicleSize, int cumProduction, int testicleCount) {
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
		this.penisModifiers.addAll(type.getDefaultPenisModifiers());
	}

	@Override
	public PenisType getType() {
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
        
		for(PenetrationModifier pm : penisModifiers) {
			if(!Main.game.isInSex() && pm!=PenetrationModifier.SHEATHED) {
				list.add(pm.getName());
			}
		}

		if(owner.getPenisCovering()!=null) {
			list.add(owner.getCovering(owner.getPenisCovering()).getColourDescriptor(owner, false, false));
		}
		
		if(owner.isPenisBestial()) {
			list.add(Util.randomItemFrom(Util.newArrayListOfValues(
					"feral",
					owner.getPenisRace().getName(owner, true)+"-",
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
	
	public String setType(GameCharacter owner, PenisType type) {
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
			if(owner.isPlayer()) {
				if(type==PenisType.NONE) {
					return "<p style='text-align:center;'>[style.colourDisabled(You already lack a cock, so nothing happens...)]</p>";
				} else {
					return "<p style='text-align:center;'>[style.colourDisabled(You already have [pc.a_cockRace]'s cock, so nothing happens...)]</p>";
				}
			} else {
				if(type==PenisType.NONE) {
					return "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already lacks a cock, so nothing happens...)]</p>";
				} else {
					return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already has [npc.a_cockRace]'s cock, so nothing happens...)]</p>");
				}
			}
			
		} else {
			UtilText.transformationContentSB.setLength(0);
			
			if (owner.getPenisType() == PenisType.NONE) {
				if(length<1) {
					length = 1;
				}
				UtilText.transformationContentSB.append(
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
					UtilText.transformationContentSB.append(
							" As [npc.her] new cock flops down "
								+ (owner.hasVagina()
									? (!owner.isTaur()
											?"to bump against [npc.her] pussy, [npc.she] [npc.verb(feel)] [npc.a_balls] growing within [npc.her] groin,"
											:"beneath [npc.her] pussy, [npc.she] [npc.verb(feel)] [npc.a_balls] growing within [npc.her] groin,")
									: "between [npc.her] legs, [npc.she] [npc.verb(feel)] [npc.a_balls] growing within [npc.her] groin,")
							+ " and [npc.she] [npc.verb(let)] out an unwitting [npc.moan] as [npc.her] new sexual organ finishes growing.<br/>");
				} else {
					UtilText.transformationContentSB.append(
							" As [npc.her] new cock flops down "
								+ (owner.hasVagina()
									? (!owner.isTaur()
											?"to bump against [npc.her] pussy, [npc.she] [npc.verb(feel)] [npc.a_balls] pushing out between [npc.her] two sexes,"
											:"beneath [npc.her] pussy, [npc.she] [npc.verb(feel)] [npc.a_balls] pushing out between [npc.her] two sexes,")
									: "between [npc.her] legs, [npc.she] [npc.verb(feel)] [npc.a_balls] push out underneath the base of [npc.her] new shaft,")
							+ " and [npc.she] [npc.verb(let)] out an unwitting [npc.moan] as [npc.her] new sexual organ finishes growing.<br/>");
				}
				
			} else {
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append("You let out a gasp as you feel your [pc.cock] suddenly stand to attention, and, trying to get your unexpected erection under control, your gasp soon turns into [pc.a_moan+] as it transforms.");
				} else {
					UtilText.transformationContentSB.append("[npc.Name] suddenly blushes and lets out [npc.a_moan+], squeezing [npc.her] thighs together as [npc.her] [npc.cock] transforms.");
				}
			}
		}

		// Parse existing content before transformation:
		String s = UtilText.parse(owner, UtilText.transformationContentSB.toString());
		UtilText.transformationContentSB.setLength(0);
		UtilText.transformationContentSB.append("<p>");
		UtilText.transformationContentSB.append(s);
		this.type = type;
		testicle.setType(owner, type.getTesticleType());
		owner.resetAreaKnownByCharacters(CoverableArea.PENIS);
		owner.resetAreaKnownByCharacters(CoverableArea.TESTICLES);
		
		switch (type) {
			case DILDO:
				return "You have somehow transformed your penis into a dildo... This is a bug... (Please let Innoxia know!)";
			case NONE:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"<br/>"
							+ "You squirm and moan as your cock and balls rapidly shrink away, and within seconds, nothing's left to remind you of your manhood.<br/>"
							+ "You now have [style.boldSex(no penis)].");
				} else {
					UtilText.transformationContentSB.append(
							"<br/>"
							+ "[npc.She] squirms and moans as [npc.her] cock and balls rapidly shrink away, and within seconds, nothing's left to remind [npc.herHim] of [npc.her] manhood.<br/>"
							+ "[npc.Name] now has [style.boldSex(no penis)].");
				}
				owner.setPiercedPenis(false);
				break;
			case HUMAN:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You now have a [style.boldHuman(human penis)], covered in [pc.penisFullDescription(true)].<br/>"
							+ "You have [style.boldHuman([pc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" human balls)], covered in [pc.ballsFullDescription(true)],"
									+ " which produce [pc.cumColour(true)] [style.boldHuman(human cum)].");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] now has a [style.boldHuman(human penis)], covered in [npc.penisFullDescription(true)].<br/>"
							+ "[npc.She] has [style.boldHuman([npc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" human balls)], covered in [npc.ballsFullDescription(true)],"
									+ " which produce [npc.cumColour(true)] [style.boldHuman(human cum)].");
				}
				break;
			case DEMON_COMMON:
				if (!owner.isShortStature()) {
					UtilText.transformationContentSB.append(
							"[npc.She] [npc.verb(squirm)] and [npc.moansVerb] as the skin covering [npc.her] cock transforms into a smooth, highly sensitive demonic counterpart."
							+ " Slimy precum starts drooling from the tip, and [npc.she] [npc.verb(let)] out [npc.a_moan+] as thick ridges suddenly press out all along its length."
							+ " As if that wasn't enough, rows of little bumps start to press out and form into little tentacles, which then start wriggling with a mind of their own.<br/>"
							+ "[npc.She] now [npc.has] a [style.boldDemon(demonic penis)], covered in [npc.penisFullDescription(true)].<br/>"
							+ "[npc.She] [npc.has] [style.boldDemon([npc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" demonic balls)], covered in [npc.ballsFullDescription(true)],"
									+ " which produce [npc.cumColour(true)] [style.boldDemon(demon cum)].");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] [npc.verb(squirm)] and [npc.moansVerb] as the skin covering [npc.her] cock transforms into a smooth, highly sensitive impish counterpart."
							+ " Slimy precum starts drooling from the tip, and [npc.she] [npc.verb(let)] out [npc.a_moan+] as thick ridges suddenly press out all along its length."
							+ " As if that wasn't enough, rows of little bumps start to press out and form into little tentacles, which then start wriggling with a mind of their own.<br/>"
							+ "[npc.She] now [npc.has] a [style.boldImp(impish penis)], covered in [npc.penisFullDescription(true)].<br/>"
							+ "[npc.She] [npc.has] [style.boldImp([npc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" impish balls)], covered in [npc.ballsFullDescription(true)],"
									+ " which produce [npc.cumColour(true)] [style.boldImp(imp cum)].");
				}
				break;
			case CANINE:
				owner.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED), false);
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"Letting out an involuntary moan, you feel your penis shifting into a new form, and you're hit by a wave of overwhelming arousal as a thick knot suddenly presses out at the base of your shaft."
							+ " Panting and gasping for air, you feel the tip of your cock narrowing down as it tapers into its new form.<br/>"
							+ "You now have a [style.boldDogMorph(canine penis)], covered in [pc.penisFullDescription(true)].<br/>"
							+ "You have [style.boldDogMorph([pc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" canine balls)], covered in [pc.ballsFullDescription(true)],"
									+ " which produce [pc.cumColour(true)] [style.boldDogMorph(canine cum)].");
				} else {
					UtilText.transformationContentSB.append(
							"Letting out an involuntary moan, [npc.name] feels [npc.her] penis shifting into a new form,"
									+ " and [npc.sheIs] hit by a wave of overwhelming arousal as a thick knot suddenly presses out at the base of [npc.her] shaft."
							+ " As [npc.she] pants and gasps for air, the tip of [npc.her] cock narrows down as it tapers into its new form.<br/>"
							+ "[npc.She] now has a [style.boldDogMorph(canine penis)], covered in [npc.penisFullDescription(true)].<br/>"
							+ "[npc.She] has [style.boldDogMorph([npc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" canine balls)], covered in [npc.ballsFullDescription(true)],"
									+ " which produce [npc.cumColour(true)] [style.boldDogMorph(canine cum)].");
				}
				break;
			case VULPINE:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" Letting out an involuntary moan, you feel your penis shifting into a new form, and you're hit by a wave of overwhelming arousal as a thick knot suddenly presses out at the base of your shaft."
							+ " Panting and gasping for air, you feel the tip of your cock narrowing down as it tapers into its new form.</br>"
							+ "You now have a [style.boldFoxMorph(vulpine penis)], covered in [pc.penisFullDescription(true)].</br>"
							+ "You have [style.boldFoxMorph([pc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" vulpine balls)], covered in [pc.ballsFullDescription(true)], which produce [pc.cumColour(true)] [style.boldFoxMorph(vulpine cum)].");
				} else {
					UtilText.transformationContentSB.append(
							" Letting out an involuntary moan, [npc.name] feels [npc.her] penis shifting into a new form, and [npc.sheIs] hit by a wave of overwhelming arousal as a thick knot suddenly presses out at the base of [npc.her] shaft."
							+ " As [npc.she] pants and gasps for air, the tip of [npc.her] cock narrows down as it tapers into its new form.</br>"
							+ "[npc.She] now has a [style.boldFoxMorph(vulpine penis)], covered in [npc.penisFullDescription(true)].</br>"
							+ "[npc.She] has [style.boldFoxMorph([npc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" vulpine balls)], covered in [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldFoxMorph(vulpine cum)].");
				}
				break;
			case LUPINE:
				owner.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED), false);
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" Letting out an involuntary moan, you feel your penis shifting into a new form, and you're hit by a wave of overwhelming arousal as a thick knot suddenly presses out at the base of your shaft."
							+ " Panting and gasping for air, you feel the tip of your cock narrowing down as it tapers into its new form.<br/>"
							+ "You now have a [style.boldWolfMorph(lupine penis)], covered in [pc.penisFullDescription(true)].<br/>"
							+ "You have [style.boldWolfMorph([pc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" lupine balls)], covered in [pc.ballsFullDescription(true)], which produce [pc.cumColour(true)] [style.boldWolfMorph(lupine cum)].");
				} else {
					UtilText.transformationContentSB.append(
							" Letting out an involuntary moan, [npc.name] feels [npc.her] penis shifting into a new form, and [npc.sheIs] hit by a wave of overwhelming arousal as a thick knot suddenly presses out at the base of [npc.her] shaft."
							+ " As [npc.she] pants and gasps for air, the tip of [npc.her] cock narrows down as it tapers into its new form.<br/>"
							+ "[npc.She] now has a [style.boldWolfMorph(lupine penis)], covered in [npc.penisFullDescription(true)].<br/>"
							+ "[npc.She] has [style.boldWolfMorph([npc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" lupine balls)], covered in [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldWolfMorph(lupine cum)].");
				}
				break;
			case ANGEL:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You now have an [style.boldAngel(angelic penis)], covered in [pc.penisFullDescription(true)].<br/>"
							+ "You have [style.boldAngel([pc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" angelic balls)], covered in [pc.ballsFullDescription(true)], which produce [pc.cumColour(true)] [style.boldAngel(angelic cum)].");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] now has an [style.boldAngel(angelic penis)], covered in [npc.penisFullDescription(true)].<br/>"
							+ "[npc.She] has [style.boldAngel([npc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" angelic balls)], covered in [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldAngel(angelic cum)].");
				}
				break;
			case AVIAN:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							" Letting out an involuntary moan, you feel your penis shifting into a new form, and you're hit by a wave of overwhelming arousal as it retreats down into a new sheath that's formed at the base.<br/>"
							+ "You now have an [style.boldHarpy(avian penis)], covered in [pc.penisFullDescription(true)].<br/>"
							+ "You have [style.boldHarpy([pc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" avian balls)], covered in [pc.ballsFullDescription(true)], which produce [pc.cumColour(true)] [style.boldHarpy(avian cum)].");
				} else {
					UtilText.transformationContentSB.append(
							"Letting out an involuntary moan, [npc.name] feels [npc.her] penis shifting into a new form, and [npc.sheIs] hit by a wave of overwhelming arousal as it retreats down into a new sheath that's formed at the base.<br/>"
							+ "[npc.She] now has an [style.boldHarpy(avian penis)], covered in [npc.penisFullDescription(true)].<br/>"
							+ "[npc.She] has [style.boldHarpy([npc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" avian balls)], covered in [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldHarpy(avian cum)].");
				}
				break;
			case EQUINE:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"Letting out an involuntary moan, you feel your penis shifting into a new form, and you're hit by a wave of overwhelming arousal as your shaft grows wider and the head flattens down.<br/>"
							+ "You now have an [style.boldHorseMorph(equine penis)], covered in [pc.penisFullDescription(true)].<br/>"
							+ "You have [style.boldHorseMorph([pc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" equine balls)], covered in [pc.ballsFullDescription(true)], which produce [pc.cumColour(true)] [style.boldHorseMorph(equine cum)].");
				} else {
					UtilText.transformationContentSB.append(
							"Letting out an involuntary moan, [npc.name] feels [npc.her] penis shifting into a new form, and [npc.sheIs] hit by a wave of overwhelming arousal as [npc.her] shaft grows wider and the head flattens down.<br/>"
							+ "[npc.She] now has an [style.boldHorseMorph(equine penis)], covered in [npc.penisFullDescription(true)].<br/>"
							+ "[npc.She] has [style.boldHorseMorph([npc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" equine balls)], covered in [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldHorseMorph(equine cum)].");
				}
				break;
			case REINDEER_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"Letting out an involuntary moan, you feel your penis shifting into a new form, and you're hit by a wave of overwhelming arousal as your shaft grows wider and the head flattens down.<br/>"
							+ "You now have a [style.boldReindeerMorph(reindeer-like penis)], covered in [pc.penisFullDescription(true)].<br/>"
							+ "You have [style.boldReindeerMorph([pc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" reindeer balls)],"
									+ " covered in [pc.ballsFullDescription(true)], which produce [pc.cumColour(true)] [style.boldReindeerMorph(reindeer cum)].");
				} else {
					UtilText.transformationContentSB.append(
							"Letting out an involuntary moan, [npc.name] feels [npc.her] penis shifting into a new form, and [npc.sheIs] hit by a wave of overwhelming arousal as [npc.her] shaft grows wider and the head flattens down.<br/>"
							+ "[npc.She] now has an [style.boldReindeerMorph(reindeer-like penis)], covered in [npc.penisFullDescription(true)].<br/>"
							+ "[npc.She] has [style.boldReindeerMorph([npc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" reindeer balls)],"
									+ " covered in [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldReindeerMorph(reindeer cum)].");
				}
				break;
			case BOVINE:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"Letting out an involuntary moan, you feel your penis shifting into a new form, and you're hit by a wave of overwhelming arousal as your shaft grows wider and the head flattens down.<br/>"
							+ "You now have a [style.boldCowMorph(bovine penis)], covered in [pc.penisFullDescription].<br/>"
							+ "You have [style.boldCowMorph([pc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" bovine balls)], covered in [pc.ballsFullDescription(true)], which produce [pc.cumColour(true)] [style.boldCowMorph(bovine cum)].");
				} else {
					UtilText.transformationContentSB.append(
							"Letting out an involuntary moan, [npc.name] feels [npc.her] penis shifting into a new form, and [npc.sheIs] hit by a wave of overwhelming arousal as [npc.her] shaft grows wider and the head flattens down.<br/>"
							+ "[npc.She] now has a [style.boldCowMorph(bovine penis)], covered in [npc.penisFullDescription].<br/>"
							+ "[npc.She] has [style.boldCowMorph([npc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" bovine balls)], covered in [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldCowMorph(bovine cum)].");
				}
				break;
			case FELINE:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"Letting out an involuntary moan, you feel your penis shifting into a new form, and you're hit by a wave of overwhelming arousal as rows of fleshy little backwards-facing barbs press out all along your shaft.<br/>"
							+"You now have a [style.boldCatMorph(feline penis)], covered in [pc.penisFullDescription(true)].<br/>"
							+ "You have [style.boldCatMorph([pc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" feline balls)], covered in [pc.ballsFullDescription(true)], which produce [pc.cumColour(true)] [style.boldCatMorph(feline cum)].");
				} else {
					UtilText.transformationContentSB.append(
							"Letting out an involuntary moan, [npc.she] feels [npc.her] penis shifting into a new form, and [npc.sheIs] hit by a wave of overwhelming arousal as rows of fleshy little backwards-facing barbs press out all along [npc.her] shaft.<br/>"
							+ "[npc.She] now has a [style.boldCatMorph(feline penis)], covered in [npc.penisFullDescription(true)].<br/>"
							+ "[npc.She] has [style.boldCatMorph([npc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" feline balls)], covered in [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldCatMorph(feline cum)].");
				}
				break;
			case ALLIGATOR_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"Letting out an involuntary moan, you feel your penis shifting into a new form, and you're hit by a wave of overwhelming arousal as your shaft grows erect and the head smoothes over.<br/>"
							+ "You now have an [style.boldGatorMorph(alligator penis)], covered in [pc.penisFullDescription].<br/>"
							+ "You have [style.boldGatorMorph([pc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" reptilian balls)],"
									+ " covered in [pc.ballsFullDescription(true)], which produce [pc.cumColour(true)] [style.boldGatorMorph(alligator-morph cum)].");
				} else {
					UtilText.transformationContentSB.append(
							"Letting out an involuntary moan, [npc.name] feels [npc.her] penis shifting into a new form, and [npc.sheIs] hit by a wave of overwhelming arousal as [npc.her] shaft grows erect and the head smoothes over.<br/>"
							+ "[npc.She] now has an [style.boldGatorMorph(alligator penis)], covered in [npc.penisFullDescription].<br/>"
							+ "[npc.She] has [style.boldGatorMorph([npc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" reptilian balls)],"
									+ " covered in [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldGatorMorph(alligator-morph cum)].");
				}
				break;
			case SQUIRREL:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You now have a [style.boldSquirrelMorph(squirrel-morph's penis)], covered in [pc.penisFullDescription(true)].<br/>"
							+ "You have [style.boldSquirrelMorph([pc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" squirrel-morph's balls)],"
									+ " covered in [pc.ballsFullDescription(true)], which produce [pc.cumColour(true)] [style.boldSquirrelMorph(squirrel-morph cum)].");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] now has a [style.boldSquirrelMorph(squirrel-morph's penis)], covered in [npc.penisFullDescription(true)].<br/>"
							+ "[npc.She] has [style.boldSquirrelMorph([npc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" squirrel-morph's balls)], covered in [npc.ballsFullDescription(true)],"
									+ " which produce [npc.cumColour(true)] [style.boldSquirrelMorph(squirrel-morph cum)].");
				}
				break;
			case RAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You now have a [style.boldRatMorph(rat-morph's penis)], covered in [pc.penisFullDescription(true)].<br/>"
							+ "You have [style.boldRatMorph([pc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" rat-morph's balls)],"
									+ " covered in [pc.ballsFullDescription(true)], which produce [pc.cumColour(true)] [style.boldRatMorph(rat-morph cum)].");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] now has a [style.boldRatMorph(rat-morph's penis)], covered in [npc.penisFullDescription(true)].<br/>"
							+ "[npc.She] has [style.boldRatMorph([npc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" rat-morph's balls)], covered in [npc.ballsFullDescription(true)],"
									+ " which produce [npc.cumColour(true)] [style.boldRatMorph(rat-morph cum)].");
				}
				break;
			case RABBIT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You now have a [style.boldRabbitMorph(rabbit-morph's penis)], covered in [pc.penisFullDescription(true)].<br/>"
							+ "You have [style.boldRabbitMorph([pc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" rabbit-morph's balls)],"
									+ " covered in [pc.ballsFullDescription(true)], which produce [pc.cumColour(true)] [style.boldRabbitMorph(rabbit-morph cum)].");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] now has a [style.boldRabbitMorph(rabbit-morph's penis)], covered in [npc.penisFullDescription(true)].<br/>"
							+ "[npc.She] has [style.boldRabbitMorph([npc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" rabbit-morph's balls)], covered in [npc.ballsFullDescription(true)],"
									+ " which produce [npc.cumColour(true)] [style.boldRabbitMorph(rabbit-morph cum)].");
				}
				break;
			case BAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You now have a [style.boldBatMorph(bat-morph's penis)], covered in [pc.penisFullDescription(true)].<br/>"
							+ "You have [style.boldBatMorph([pc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" bat-morph's balls)],"
									+ " covered in [pc.ballsFullDescription(true)], which produce [pc.cumColour(true)] [style.boldBatMorph(bat-morph cum)].");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] now has a [style.boldBatMorph(bat-morph's penis)], covered in [npc.penisFullDescription(true)].<br/>"
							+ "[npc.She] has [style.boldBatMorph([npc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" bat-morph's balls)], covered in [npc.ballsFullDescription(true)],"
									+ " which produce [npc.cumColour(true)] [style.boldBatMorph(bat-morph cum)].");
				}
				break;
		}
		UtilText.transformationContentSB.append("</p>");
		
		penisModifiers.clear();
		penisModifiers.addAll(type.getDefaultPenisModifiers());

		UtilText.transformationContentSB.append(
				"<p>"
				+ "Any old modifiers which [npc.her] penis might have had have [style.boldShrink(transformed away)]!");
		
		if(!penisModifiers.isEmpty()) {
			if (owner.isPlayer()) {
				UtilText.transformationContentSB.append(
						"<br/>"
						+ "Instead, your new cock is:");
			} else {
				UtilText.transformationContentSB.append(
						"<br/>"
						+ "Instead, [npc.her] new cock is:");
			}
			
			for(PenetrationModifier pm : penisModifiers) {
				UtilText.transformationContentSB.append("<br/>[style.boldGrow("+Util.capitaliseSentence(pm.getName())+")]");
			}
		}
		UtilText.transformationContentSB.append("</p>");
		
		String postTF = owner.postTransformationCalculation(false); // Calculate this before parsing, as it updates covering colours
		
		return UtilText.parse(owner, UtilText.transformationContentSB.toString())
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
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The girth of your [pc.cock] doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The girth of [npc.namePos] [npc.cock] doesn't change...)]</p>");
			}
		}
		
		if (girthChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out [pc.a_moan+] as you feel a deep throbbing sensation building up at the base of your cock."
							+ " Your cheeks flush red as the feeling works its way up your shaft, and as a trickle of precum leaks out from the head of your now-hard member, you realise that your cock has [style.boldGrow(grown thicker)].<br/>"
							+ "You now have [style.boldSex([pc.a_penisGirth] [pc.cock])]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] lets out [npc.a_moan+] as [npc.she] feels a deep throbbing sensation building up at the base of [npc.her] cock."
							+ " [npc.Her] cheeks flush red as the feeling works its way up [npc.her] shaft, and as a trickle of precum leaks out from the head of [npc.her] now-hard member,"
								+ " [npc.she] realises that [npc.her] cock has [style.boldGrow(grown thicker)].<br/>"
							+ "[npc.She] now has [style.boldSex([npc.a_penisGirth] [npc.cock])]!"
						+ "</p>");
			}
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out [pc.a_moan+] as you feel a deep throbbing sensation building up at the base of your cock."
							+ " Your cheeks flush red as the feeling works its way up your shaft, and as a trickle of precum leaks out from the head of your now-hard member, you realise that your cock has [style.boldShrink(got thinner)].<br/>"
							+ "You now have [style.boldSex([pc.a_penisGirth] [pc.cock])]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name] lets out [npc.a_moan+] as [npc.she] feels a deep throbbing sensation building up at the base of [npc.her] cock."
								+ " [npc.Her] cheeks flush red as the feeling works its way up [npc.her] shaft, and as a trickle of precum leaks out from the head of [npc.her] now-hard member,"
									+ " [npc.she] realises that [npc.her] cock has [style.boldShrink(got thinner)].<br/>"
							+ "[npc.She] now has [style.boldSex([npc.a_penisGirth] [npc.cock])]!"
						+ "</p>");
			}
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
		return getGenericDiameter(length, girth, new ArrayList<>());
	}
	
	public static float getGenericDiameter(int length, PenetrationGirth girth, List<PenetrationModifier> mods) {
		return Units.round((length * 0.25f) * (1f + girth.getDiameterPercentageModifier() + (mods.contains(PenetrationModifier.FLARED)?0.05f:0) + (mods.contains(PenetrationModifier.TAPERED)?-0.05f:0)), 2);
	}
	
	public float getDiameter() {
		return Units.round((length * 0.25f) * (1f + this.getGirth().getDiameterPercentageModifier() + (this.hasPenisModifier(PenetrationModifier.FLARED)?0.05f:0) + (this.hasPenisModifier(PenetrationModifier.TAPERED)?-0.05f:0)), 2);
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
					"<p>"
							+ "[npc.NamePos] [npc.cock] is [style.boldShrink(no longer pierced)]!"
					+ "</p>"
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
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		if(owner==null || owner.getBody()==null) {
			penisModifiers.add(modifier);
			return "";
		}
		
		if(!owner.hasPenisIgnoreDildo()) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(You don't have a penis, so nothing happens...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] doesn't have a penis, so nothing happens...)]</p>");
			}
		}
		
		penisModifiers.add(modifier);
		
		switch(modifier) {
			case RIBBED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense pressure building up within your [pc.penis], but before you have a chance to react, a series of [style.boldGrow(hard, fleshy ribs)] grow up all along its length.<br/>"
								+ "[style.boldSex(Your [pc.penis] is now ribbed!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense pressure builds up within [npc.namePos] [npc.penis], but before [npc.she] has a chance to react, a series of [style.boldGrow(hard, fleshy ribs)] grow up all along its length.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.penis] is now ribbed!)]"
							+ "</p>";
				}
			case TENTACLED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a pulsating warmth building up within your [pc.penis], but before you have a chance to react, a series of [style.boldGrow(little wriggling tentacles)] grow up all along its length.<br/>"
								+ "[style.boldSex(Your [pc.penis] is now covered with little tentacles, which wriggle with a mind of their own!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "A pulsating warmth builds up within [npc.namePos] [npc.penis], but before [npc.she] has a chance to react, a series of [style.boldGrow(little wriggling tentacles)] grow up all along its length.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.penis] is now covered with little tentacles, which wriggle with a mind of their own!)]"
							+ "</p>";
				}
			case BARBED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense warmth building up within your [pc.penis], but before you have a chance to react, a series of [style.boldGrow(little fleshy barbs)] grow up all along its length.<br/>"
								+ "[style.boldSex(Your [pc.penis] is now lined with fleshy, backwards-facing barbs!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense warmth builds up within [npc.namePos] [npc.penis], but before [npc.she] has a chance to react, a series of [style.boldGrow(little fleshy barbs)] grow up all along its length.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.penis] is now lined with fleshy, backwards-facing barbs!)]"
							+ "</p>";
				}
			case FLARED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense warmth building up in the tip of your [pc.penis], and before you have a chance to react, the [style.boldGrow(head flares out)], much like that of a horse's.<br/>"
								+ "[style.boldSex(Your [pc.penis] now has a wide, flared head!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense warmth builds up in the tip of [npc.namePos] [npc.penis], and before [npc.she] has a chance to react, the [style.boldGrow(head flares out)], much like that of a horse's.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.penis] now has a wide, flared head!)]"
							+ "</p>";
				}
			case BLUNT:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense warmth building up in the tip of your [pc.penis], and before you have a chance to react, the [style.boldGrow(head smoothes over)], much like that of a reptile's.<br/>"
								+ "[style.boldSex(Your [pc.penis] now has a smooth, blunt head!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense warmth builds up in the tip of [npc.namePos] [npc.penis], and before [npc.she] has a chance to react, the [style.boldGrow(head smoothes over)], much like that of a reptile's.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.penis] now has a smooth, blunt head!)]"
							+ "</p>";
				}
			case KNOTTED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense warmth building up in the base of your [pc.penis], and before you have a chance to react, a [style.boldGrow(fat knot)] quickly grows up there.<br/>"
								+ "[style.boldSex(Your [pc.penis] now has a fat knot at the base!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense warmth builds up in the base of [npc.namePos] [npc.penis], and before [npc.she] has a chance to react, a [style.boldGrow(fat knot)] quickly grows up there.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.penis] now has a fat knot at the base!)]"
							+ "</p>";
				}
			case PREHENSILE:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a strange tingling sensation work its way up the length of your [pc.penis], and you suddenly become aware that it's transformed into being [style.boldGrow(prehensile)],"
									+ " allowing you to twist and move it around just like a primate's tail.<br/>"
								+ "[style.boldSex(Your [pc.penis] is now prehensile!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "A strange tingling sensation work its way up the length of [npc.namePos] [npc.penis], and [npc.she] suddenly becomes aware that it's transformed into being [style.boldGrow(prehensile)],"
									+ " allowing [npc.herHim] to twist and move it around just like a primate's tail.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.penis] is now prehensile!)]"
							+ "</p>";
				}
			case SHEATHED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense pressure building up in the base of your [pc.penis], and before you have a chance to react, it pulls back into a brand new [style.boldGrow(sheath)] that's just grown up there.<br/>"
								+ "[style.boldSex(Your [pc.penis] is now sheathed!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense pressure builds up in the base of [npc.namePos] [npc.penis], and before [npc.she] has a chance to react, it pulls back into a brand new [style.boldGrow(sheath)] that's just grown up there.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.penis] is now sheathed!)]"
							+ "</p>";
				}
			case TAPERED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense warmth building up within your [pc.penis], but before you have a chance to react, the shaft suddenly [style.boldGrow(tapers down)].<br/>"
								+ "[style.boldSex(Your [pc.penis] is now tapered!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense warmth builds up within [npc.namePos] [npc.penis], but before [npc.she] has a chance to react, the shaft suddenly [style.boldGrow(tapers down)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.penis] is now tapered!)]"
							+ "</p>";
				}
			case VEINY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense warmth building up within your [pc.penis], but before you have a chance to react, a series of [style.boldGrow(prominent veins)] grow up all along its length.<br/>"
								+ "[style.boldSex(Your [pc.penis] is now veiny!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense warmth builds up within [npc.namePos] [npc.penis], but before [npc.she] has a chance to react, a series of [style.boldGrow(prominent veins)] grow up all along its length.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.penis] is now veiny!)]"
							+ "</p>";
				}
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}

	public String removePenisModifier(GameCharacter owner, PenetrationModifier modifier) {
		if(!hasPenisModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		penisModifiers.remove(modifier);
		
		switch(modifier) {
			case RIBBED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soothing coolness spread up within your [pc.penis], and before you have a chance to react, your hard, fleshy ribs suddenly [style.boldShrink(disappear)].<br/>"
								+ "[style.boldSex(Your [pc.penis] is no longer ribbed!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An soothing coolness builds up within [npc.namePos] [npc.penis], but before [npc.she] has a chance to react, [npc.her] hard, fleshy ribs suddenly [style.boldShrink(disappear)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.penis] is no longer ribbed!)]"
							+ "</p>";
				}
			case TENTACLED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soothing coolness building up within your [pc.penis], and before you have a chance to react, your little wriggling tentacles [style.boldShrink(disappear)].<br/>"
								+ "[style.boldSex(Your [pc.penis] is no longer covered with little tentacles!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "A soothing coolness builds up within [npc.namePos] [npc.penis], and before [npc.she] has a chance to react, [npc.her] little wriggling tentacles [style.boldShrink(disappear)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.penis] is no longer covered with little tentacles!)]"
							+ "</p>";
				}
			case BARBED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soothing coolness building up within your [pc.penis], and before you have a chance to react, your little fleshy barbs [style.boldShrink(disappear)].<br/>"
								+ "[style.boldSex(Your [pc.penis] is no longer barbed!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "A soothing coolness builds up within [npc.namePos] [npc.penis], but before [npc.she] has a chance to react, [npc.her] little fleshy barbs [style.boldShrink(disappear)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.penis] is no longer barbed!)]"
							+ "</p>";
				}
			case FLARED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soothing coolness building up in the tip of your [pc.penis], and before you have a chance to react, your flared head [style.boldShrink(shrinks)] down into a regular, human-like one.<br/>"
								+ "[style.boldSex(Your [pc.penis] no longer has a flared head!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "A soothing coolness builds up in the tip of [npc.namePos] [npc.penis], and before [npc.she] has a chance to react, [npc.her] flared head [style.boldShrink(shrinks)] down into a regular, human-like one.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.penis] no longer has a flared head!)]"
							+ "</p>";
				}
			case BLUNT:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soothing coolness building up in the tip of your [pc.penis], and before you have a chance to react, your blunt head [style.boldShrink(shrinks)] down into a regular, human-like one.<br/>"
								+ "[style.boldSex(Your [pc.penis] no longer has a blunt head!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "A soothing coolness builds up in the tip of [npc.namePos] [npc.penis], and before [npc.she] has a chance to react, [npc.her] blunt head [style.boldShrink(shrinks)] down into a regular, human-like one.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.penis] no longer has a blunt head!)]"
							+ "</p>";
				}
			case KNOTTED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soothing coolness building up in the base of your [pc.penis], and before you have a chance to react, your fat knot [style.boldShrink(shrinks)] and disappears.<br/>"
								+ "[style.boldSex(Your [pc.penis] is no longer knotted!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "A soothing coolness builds up in the base of [npc.namePos] [npc.penis], and before [npc.she] has a chance to react, [npc.her] fat knot [style.boldShrink(shrinks)] and disappears.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.penis] is no longer knotted!)]"
							+ "</p>";
				}
			case PREHENSILE:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a strange tingling sensation work its way up the length of your [pc.penis], and you suddenly become aware that it's [style.boldShrink(no longer prehensile)].<br/>"
								+ "[style.boldSex(Your [pc.penis] is no longer prehensile!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "A strange tingling sensation works its way up the length of [npc.namePos] [npc.penis], and [npc.she] suddenly becomes aware that it's [style.boldShrink(no longer prehensile)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.penis] is no longer prehensile!)]"
							+ "</p>";
				}
			case SHEATHED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soothing coolness building up in the base of your [pc.penis], and before you have a chance to react, your sheath [style.boldShrink(disappears)].<br/>"
								+ "[style.boldSex(Your [pc.penis] is no longer sheathed!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "A soothing coolness builds up in the base of [npc.namePos] [npc.penis], and before [npc.she] has a chance to react, [npc.her] sheath [style.boldShrink(disappears)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.penis] is no longer sheathed!)]"
							+ "</p>";
				}
			case TAPERED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense warmth building up within your [pc.penis], but before you have a chance to react, the shaft suddenly [style.boldShrink(widens)].<br/>"
								+ "[style.boldSex(Your [pc.penis] is no longer tapered!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense warmth builds up within [npc.namePos] [npc.penis], but before [npc.she] has a chance to react, the shaft suddenly [style.boldShrink(widens)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.penis] is no longer tapered!)]"
							+ "</p>";
				}
			case VEINY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense warmth building up within your [pc.penis], but before you have a chance to react, your prominent veins [style.boldShrink(disappear)].<br/>"
								+ "[style.boldSex(Your [pc.penis] is no longer veiny!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense warmth builds up within [npc.namePos] [npc.penis], but before [npc.she] has a chance to react, [npc.her] prominent veins [style.boldShrink(disappear)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.penis] is no longer veiny!)]"
							+ "</p>";
				}
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}
	
	public void clearPenisModifiers() {
		penisModifiers.clear();
	}

	@Override
	public boolean isBestial(GameCharacter owner) {
		if(owner==null || getType()==PenisType.NONE) {
			return false;
		}
		return owner.getLegConfiguration().getBestialParts().contains(Penis.class) && getType().getRace().isBestialPartsAvailable();
	}
}
