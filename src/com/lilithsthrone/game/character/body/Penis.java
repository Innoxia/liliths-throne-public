package com.lilithsthrone.game.character.body;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.2.2
 * @author Innoxia
 */
public class Penis implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;

	public static final float TWO_PENIS_SIZE_MULTIPLIER = 1.6f;

	protected PenisType type;
	protected int size;
	protected int girth;
	protected boolean pierced;
	protected boolean virgin;
	protected Set<PenetrationModifier> penisModifiers;
	
	protected Testicle testicle;
	protected OrificePenisUrethra orificeUrethra;

	public Penis(PenisType type, int size, int girth, int testicleSize, int cumProduction, int testicleCount) {
		this.type = type;
		this.size = size;
		this.girth = girth;
		pierced = false;
		virgin = true;
		
		testicle = new Testicle(type.getTesticleType(), testicleSize, cumProduction, testicleCount);
		
		orificeUrethra = new OrificePenisUrethra(testicle.getCumStorage().getAssociatedWetness().getValue(), 0, OrificeElasticity.ZERO_UNYIELDING.getValue(), OrificePlasticity.THREE_RESILIENT.getValue(), true, new ArrayList<>());
		
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
		list.add(type.getDescriptor(owner));
		if(Main.game.isInSex() && Sex.getAllParticipants().contains(owner)) {
			list.add("hard");
			if(this.getType()!=PenisType.DILDO) {
				list.add("throbbing");
			}
		}
		
        return UtilText.returnStringAtRandom(list.toArray(new String[]{}));
	}
	
	public String getUrethraDescriptor(GameCharacter owner) {
		List<String> descriptorList = new ArrayList<>();
		
		for(OrificeModifier om : orificeUrethra.getOrificeModifiers()) {
			descriptorList.add(om.getName());
		}
		
		descriptorList.add(type.getDescriptor(owner));
		
		descriptorList.add(orificeUrethra.getCapacity().getDescriptor());
		
		return UtilText.returnStringAtRandom(descriptorList.toArray(new String[]{}));
	}
	
	public String getPenisHeadName(GameCharacter gc) {
		List<String> list = new ArrayList<>();
		list.add("head");
        
		if(penisModifiers.contains(PenetrationModifier.TAPERED)) {
			list.add("tip");
		}
		
        return UtilText.returnStringAtRandom(list.toArray(new String[]{}));
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
		
        return UtilText.returnStringAtRandom(list.toArray(new String[]{}));
	}
	
	public String setType(GameCharacter owner, PenisType type) {
		
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
				if(size<1) {
					size = 1;
				}
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"<p>"
								+ "You feel an intense heat building up in your groin, and you let out a lewd moan as you feel the [pc.skin] "+ (owner.hasVagina() ? "above your pussy" : "between your legs")+ " tighten up and start to press outwards."
								+ " Within moments, a large bump has formed " + (owner.hasVagina() ? "above your feminine slit," : "in the middle of your groin,")+ " and with a sudden splitting sensation, the bump pushes out and forms into a penis.");
					
					if(owner.isInternalTesticles()) {
						UtilText.transformationContentSB.append(
								" As your new cock flops down "
									+ (owner.hasVagina()
										? "to bump against your pussy, you feel [pc.a_balls] growing within your groin,"
										: "between your legs, you feel [pc.a_balls] growing within your groin,")
								+ " and you let out an unwitting [pc.moan] as your new sexual organ finishes growing.<br/>");
					} else {
						UtilText.transformationContentSB.append(
								" As your new cock flops down "
									+ (owner.hasVagina()
										? "to bump against your pussy, you feel [pc.a_balls] pushing out between your two sexes,"
										: "between your legs, you feel [pc.a_balls] push out underneath the base of your new shaft,")
								+ " and you let out an unwitting [pc.moan] as your new sexual organ finishes growing.<br/>");
					}
					
				} else {
					UtilText.transformationContentSB.append(
							"<p>"
								+ "[npc.Name] feels an intense heat building up in [npc.her] groin, and [npc.she] lets out [npc.a_moan+] as [npc.she] feels the [npc.skin] "+ (owner.hasVagina() ? "above [npc.her] pussy" : "between [npc.her] legs")
									+ " tighten up and start to press outwards."
								+ " Within moments, a large bump has formed " + (owner.hasVagina() ? "above [npc.her] feminine slit," : "in the middle of [npc.her] groin,")+ " and with a sudden splitting sensation, the bump pushes out and forms into a penis.");
					
					if(owner.isInternalTesticles()) {
						UtilText.transformationContentSB.append(
								" As [npc.her] new cock flops down "
									+ (owner.hasVagina()
										? "to bump against [npc.her] pussy, [npc.she] feels [npc.a_balls] growing within [npc.her] groin,"
										: "between [npc.her] legs, [npc.she] feels [npc.a_balls] growing within [npc.her] groin,")
								+ " and [npc.she] lets out an unwitting [npc.moan] as [npc.her] new sexual organ finishes growing.<br/>");
					} else {
						UtilText.transformationContentSB.append(
								" As [npc.her] new cock flops down "
									+ (owner.hasVagina()
										? "to bump against [npc.her] pussy, [npc.she] feels [npc.a_balls] pushing out between [npc.her] two sexes,"
										: "between [npc.her] legs, [npc.she] feels [npc.a_balls] push out underneath the base of [npc.her] new shaft,")
								+ " and [npc.she] lets out an unwitting [npc.moan] as [npc.her] new sexual organ finishes growing.<br/>");
					}
				}
				
			} else {
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"<p>"
								+ "You let out a gasp as you feel your [pc.cock] suddenly stand to attention, and, trying to get your unexpected erection under control, your gasp soon turns into [pc.a_moan+] as it transforms.<br/>");
				} else {
					UtilText.transformationContentSB.append(
							"<p>"
								+ "[npc.Name] suddenly blushes and lets out [npc.a_moan+], squeezing [npc.her] thighs together as [npc.her] [npc.cock] transforms.<br/>");
				}
			}
		}

		// Parse existing content before transformation:
		String s = UtilText.parse(owner, UtilText.transformationContentSB.toString());
		UtilText.transformationContentSB.setLength(0);
		UtilText.transformationContentSB.append(s);
		this.type = type;
		testicle.setType(owner, type.getTesticleType());
		owner.resetAreaKnownByCharacters(CoverableArea.PENIS);
		owner.resetAreaKnownByCharacters(CoverableArea.TESTICLES);
		
		switch (type) {
			case DILDO:
				return "You have somehow transformed your penis into a dildo... This is a bug... (please let Innoxia know!)";
			case NONE:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You squirm and moan as your cock and balls rapidly shrink away, and within seconds, nothing's left to remind you of your manhood.<br/>"
							+ "You now have [style.boldSex(no penis)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] squirms and moans as [npc.her] cock and balls rapidly shrink away, and within seconds, nothing's left to remind [npc.herHim] of [npc.her] manhood.<br/>"
							+ "[npc.Name] now has [style.boldSex(no penis)]."
							+ "</p>");
				}
				orificeUrethra.setVirgin(true);
				owner.setPiercedPenis(false);
				break;
			case HUMAN:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You now have a [style.boldHuman(human penis)], covered in [pc.penisFullDescription(true)].<br/>"
							+ "You have [style.boldHuman([pc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" human balls)], covered in [pc.ballsFullDescription(true)], which produce [pc.cumColour(true)] [style.boldHuman(human cum)].");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] now has a [style.boldHuman(human penis)], covered in [npc.penisFullDescription(true)].<br/>"
							+ "[npc.She] has [style.boldHuman([npc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" human balls)], covered in [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldHuman(human cum)].");
				}
				break;
			case DEMON_COMMON:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You squirm and [pc.moan] as the skin covering your cock transforms into a smooth, highly sensitive demonic counterpart."
							+ " Slimy pre-cum starts drooling from the tip, and you let out [pc.a_moan+] as thick ridges suddenly press out all along its length."
							+ " As if that wasn't enough, rows of little bumps start to press out and form into little tentacles, which then start wriggling with a mind of their own.<br/>"
							+ "You now have a [style.boldDemon(demonic penis)], covered in [pc.penisFullDescription(true)].<br/>"
							+ "You have [style.boldDemon([pc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" demonic balls)], covered in [pc.ballsFullDescription(true)], which produce [pc.cumColour(true)] [style.boldDemon(demon cum)].");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] squirms and [npc.moansVerb] as the skin covering [npc.her] cock transforms into a smooth, highly sensitive demonic counterpart."
							+ " Slimy pre-cum starts drooling from the tip, and [npc.she] lets out [npc.a_moan+] as thick ridges suddenly press out all along its length."
							+ " As if that wasn't enough, rows of little bumps start to press out and form into little tentacles, which then start wriggling with a mind of their own.<br/>"
							+ "[npc.She] now has a [style.boldDemon(demonic penis)], covered in [npc.penisFullDescription(true)].<br/>"
							+ "[npc.She] has [style.boldDemon([npc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" demonic balls)], covered in [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldDemon(demon cum)].");
				}
				break;
			case IMP:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You squirm and [pc.moan] as the skin covering your cock transforms into a smooth, highly sensitive impish counterpart."
							+ " Slimy pre-cum starts drooling from the tip, and you let out [pc.a_moan+] as thick ridges suddenly press out all along its length."
							+ " As if that wasn't enough, rows of little bumps start to press out and form into little tentacles, which then start wriggling with a mind of their own.<br/>"
							+ "You now have an [style.boldImp(impish penis)], covered in [pc.penisFullDescription(true)].<br/>"
							+ "You have [style.boldImp([pc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" impish balls)], covered in [pc.ballsFullDescription(true)], which produce [pc.cumColour(true)] [style.boldImp(imp cum)].");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] squirms and [npc.moansVerb] as the skin covering [npc.her] cock transforms into a smooth, highly sensitive impish counterpart."
							+ " Slimy pre-cum starts drooling from the tip, and [npc.she] lets out [npc.a_moan+] as thick ridges suddenly press out all along its length."
							+ " As if that wasn't enough, rows of little bumps start to press out and form into little tentacles, which then start wriggling with a mind of their own.<br/>"
							+ "[npc.She] now has a [style.boldImp(impish penis)], covered in [npc.penisFullDescription(true)].<br/>"
							+ "[npc.She] has [style.boldImp([npc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" impish balls)], covered in [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldImp(imp cum)].");
				}
				break;
			case CANINE:
				owner.setSkinCovering(new Covering(BodyCoveringType.PENIS, Colour.SKIN_RED), false);
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"Letting out an involuntary moan, you feel your penis shifting into a new form, and you're hit by a wave of overwhelming arousal as a thick knot suddenly presses out at the base of your shaft."
							+ " Panting and gasping for air, you feel the tip of your cock narrowing down as it tapers into its new form.<br/>"
							+ "You now have a [style.boldDogMorph(canine penis)], covered in [pc.penisFullDescription(true)].<br/>"
							+ "You have [style.boldDogMorph([pc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" canine balls)], covered in [pc.ballsFullDescription(true)], which produce [pc.cumColour(true)] [style.boldDogMorph(canine cum)].");
				} else {
					UtilText.transformationContentSB.append(
							"Letting out an involuntary moan, [npc.name] feels [npc.her] penis shifting into a new form, and [npc.sheIs] hit by a wave of overwhelming arousal as a thick knot suddenly presses out at the base of [npc.her] shaft."
							+ " As [npc.she] pants and gasps for air, the tip of [npc.her] cock narrows down as it tapers into its new form.<br/>"
							+ "[npc.She] now has a [style.boldDogMorph(canine penis)], covered in [npc.penisFullDescription(true)].<br/>"
							+ "[npc.She] has [style.boldDogMorph([npc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" canine balls)], covered in [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldDogMorph(canine cum)].");
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
				owner.setSkinCovering(new Covering(BodyCoveringType.PENIS, Colour.SKIN_RED), false);
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
							+ "You have [style.boldReindeerMorph([pc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" rangiferine balls)],"
									+ " covered in [pc.ballsFullDescription(true)], which produce [pc.cumColour(true)] [style.boldReindeerMorph(rangiferine cum)].");
				} else {
					UtilText.transformationContentSB.append(
							"Letting out an involuntary moan, [npc.name] feels [npc.her] penis shifting into a new form, and [npc.sheIs] hit by a wave of overwhelming arousal as [npc.her] shaft grows wider and the head flattens down.<br/>"
							+ "[npc.She] now has an [style.boldReindeerMorph(reindeer-like penis)], covered in [npc.penisFullDescription(true)].<br/>"
							+ "[npc.She] has [style.boldReindeerMorph([npc.ballsCount]"+(owner.isInternalTesticles()?" internal,":"")+" rangiferine balls)],"
									+ " covered in [npc.ballsFullDescription(true)], which produce [npc.cumColour(true)] [style.boldReindeerMorph(rangiferine cum)].");
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

		penisModifiers.clear();
		penisModifiers.addAll(type.getDefaultPenisModifiers());

		if (owner.isPlayer()) {
			UtilText.transformationContentSB.append(
					"<br/>"
					+ "Any old modifiers that your penis might have had have [style.boldShrink(transformed away)]!");
		} else {
			UtilText.transformationContentSB.append(
					"<br/>"
					+ "Any old modifiers that [npc.her] penis might have had have [style.boldShrink(transformed away)]!");
		}
		
		if(penisModifiers.isEmpty()) {
			UtilText.transformationContentSB.append("</p>");
		} else {
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
			UtilText.transformationContentSB.append("</p>");
		}
		
		return UtilText.parse(owner, UtilText.transformationContentSB.toString())
				+ "<p>"
				+ owner.postTransformationCalculation(false)
				+ "</p>";
	}
	
	// Girth:

	public PenisGirth getGirth() {
		return PenisGirth.getPenisGirthFromInt(girth);
	}

	public int getRawGirthValue() {
		return girth;
	}

	/**
	 * Sets the girth. Value is bound to >=0 && <=PenisGirth.FOUR_FAT.getValue()
	 */
	public String setPenisGirth(GameCharacter owner, int girth) {
		if(owner==null) {
			this.girth = Math.max(0, Math.min(girth, PenisGirth.FOUR_FAT.getValue()));
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
		} else if (girth >= PenisGirth.FOUR_FAT.getValue()) {
			if (this.girth != PenisGirth.FOUR_FAT.getValue()) {
				girthChange = PenisGirth.FOUR_FAT.getValue() - this.girth;
				this.girth = PenisGirth.FOUR_FAT.getValue();
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
							+ " Your cheeks flush red as the feeling works its way up your shaft, and as a trickle of pre-cum leaks out from the head of your now-hard member, you realise that your cock has [style.boldGrow(grown thicker)].<br/>"
							+ "You now have [style.boldSex([pc.a_penisGirth] [pc.cock])]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] lets out [npc.a_moan+] as [npc.she] feels a deep throbbing sensation building up at the base of [npc.her] cock."
							+ " [npc.Her] cheeks flush red as the feeling works its way up [npc.her] shaft, and as a trickle of pre-cum leaks out from the head of [npc.her] now-hard member,"
								+ " [npc.she] realises that [npc.her] cock has [style.boldGrow(grown thicker)].<br/>"
							+ "[npc.She] now has [style.boldSex([npc.a_penisGirth] [npc.cock])]!"
						+ "</p>");
			}
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out [pc.a_moan+] as you feel a deep throbbing sensation building up at the base of your cock."
							+ " Your cheeks flush red as the feeling works its way up your shaft, and as a trickle of pre-cum leaks out from the head of your now-hard member, you realise that your cock has [style.boldShrink(got thinner)].<br/>"
							+ "You now have [style.boldSex([pc.a_penisGirth] [pc.cock])]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name] lets out [npc.a_moan+] as [npc.she] feels a deep throbbing sensation building up at the base of [npc.her] cock."
								+ " [npc.Her] cheeks flush red as the feeling works its way up [npc.her] shaft, and as a trickle of pre-cum leaks out from the head of [npc.her] now-hard member,"
									+ " [npc.she] realises that [npc.her] cock has [style.boldShrink(got thinner)].<br/>"
							+ "[npc.She] now has [style.boldSex([npc.a_penisGirth] [npc.cock])]!"
						+ "</p>");
			}
		}
	}
	
	// Size:

	public PenisSize getSize() {
		return PenisSize.getPenisSizeFromInt(size);
	}

	public int getRawSizeValue() {
		return size;
	}

	/**
	 * Sets the size. Value is bound to >=0 && <=PenisSize.SEVEN_STALLION.getMaximumValue()
	 */
	public String setPenisSize(GameCharacter owner, int size) {
		if(!owner.hasPenisIgnoreDildo()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		int sizeChange = 0;
		
		if (size <= 0) {
			if (this.size != 0) {
				sizeChange = 0 - this.size;
				this.size = 0;
			}
		} else if (size >= PenisSize.SEVEN_STALLION.getMaximumValue()) {
			if (this.size != PenisSize.SEVEN_STALLION.getMaximumValue()) {
				sizeChange = PenisSize.SEVEN_STALLION.getMaximumValue() - this.size;
				this.size = PenisSize.SEVEN_STALLION.getMaximumValue();
			}
		} else {
			if (this.size != size) {
				sizeChange = size - this.size;
				this.size = size;
			}
		}
		
		if(sizeChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The size of your [pc.cock] doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The size of [npc.namePos] [npc.cock] doesn't change...)]</p>");
			}
		}
		
		if (sizeChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out [pc.a_moan] as you feel a deep throbbing sensation building up at the base of your cock."
							+ " Your cheeks flush red as the feeling works its way up your shaft, and as a trickle of pre-cum leaks out from the head of your now-hard member, you realise that your cock has [style.boldGrow(grown larger)].<br/>"
							+ "You now have [style.boldSex([pc.a_penisSize] [pc.cock])]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] lets out [npc.a_moan] as [npc.she] feels a deep throbbing sensation building up at the base of [npc.her] cock."
							+ " [npc.Her] cheeks flush red as the feeling works its way up [npc.her] shaft, and as a trickle of pre-cum leaks out from the head of [npc.her] now-hard member,"
								+ " [npc.she] realises that [npc.her] cock has [style.boldGrow(grown larger)].<br/>"
							+ "[npc.She] now has [style.boldSex([npc.a_penisSize] [npc.cock])]!"
						+ "</p>");
			}
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out a groan as you feel a deep throbbing sensation building up at the base of your cock."
							+ " Your cheeks flush red as the feeling works its way up your shaft, and as a trickle of pre-cum leaks out from the head of your now-hard member, you realise that your cock has [style.boldShrink(shrunk)].<br/>"
							+ "You now have [style.boldSex([pc.a_penisSize] [pc.cock])]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name] lets out [npc.a_moan] as [npc.she] feels a deep throbbing sensation building up at the base of [npc.her] cock."
								+ " [npc.Her] cheeks flush red as the feeling works its way up [npc.her] shaft, and as a trickle of pre-cum leaks out from the head of [npc.her] now-hard member,"
									+ " [npc.she] realises that [npc.her] cock has [style.boldShrink(shrunk)].<br/>"
							+ "[npc.She] now has [style.boldSex([npc.a_penisSize] [npc.cock])]!"
						+ "</p>");
			}
		}
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
			if(owner.isPlayer()) {
				return "<p>Your [pc.cock] is now [style.boldGrow(pierced)]!</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.NamePos] [npc.cock] is now [style.boldGrow(pierced)]!</p>");
			}
			
		} else {
			AbstractClothing c = owner.getClothingInSlot(InventorySlot.PIERCING_PENIS);
			String piercingUnequip = "";
			if(c!=null) {
				owner.forceUnequipClothingIntoVoid(owner, c);
				piercingUnequip = owner.addClothing(c, false);
			}
			
			if(owner.isPlayer()) {
				return "<p>"
							+ "Your [pc.cock] is [style.boldShrink(no longer pierced)]!"
						+ "</p>"
						+piercingUnequip;
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.NamePos] [npc.cock] is [style.boldShrink(no longer pierced)]!"
						+ "</p>"
						+piercingUnequip);
			}
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
}
