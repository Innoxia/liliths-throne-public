package com.base.game.character.body;

import java.io.Serializable;

import com.base.game.character.GameCharacter;
import com.base.game.character.body.types.FaceType;
import com.base.game.character.body.valueEnums.BodyHair;
import com.base.game.character.body.valueEnums.Capacity;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.character.body.valueEnums.OrificeElasticity;
import com.base.game.character.body.valueEnums.OrificePlasticity;
import com.base.game.character.body.valueEnums.Wetness;
import com.base.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public class Face implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	private FaceType type;
	private boolean piercedNose;
	private BodyHair facialHair;
	
	private Mouth mouth;
	private Tongue tongue;

	public Face(FaceType type, int lipSize) {
		this.type = type;
		piercedNose = false;
		facialHair = BodyHair.NONE;
		
		mouth = new Mouth(type.getMouthType(), lipSize, Wetness.THREE_WET.getValue(), Capacity.THREE_SLIGHTLY_LOOSE.getMedianValue(), OrificeElasticity.FOUR_LIMBER.getValue(), OrificePlasticity.THREE_RESILIENT.getValue(), true);
		tongue = new Tongue(type.getTongueType());
	}

	public Mouth getMouth() {
		return mouth;
	}

	public Tongue getTongue() {
		return tongue;
	}

	@Override
	public FaceType getType() {
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
	
	public String getDescriptor(GameCharacter owner) {
		return type.getDescriptor(owner);
	}
	
	public String getNoseNameSingular(GameCharacter gc) {
		switch(type) {
			case HARPY:
				return "beak";
			default:
				return "nose";
		}
	}
	
	public String getNoseNamePlural(GameCharacter gc) {
		switch(type) {
			case HARPY:
				return "beaks";
			default:
				return "noses";
		}
	}

	public String getNoseDescriptor(GameCharacter gc) {
		return UtilText.returnStringAtRandom("");
	}
	
	public String setType(GameCharacter owner, FaceType type) {
		if (type == getType()) {
			if (owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(You already have the [pc.face] of [pc.a_faceRace], so nothing happens...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already has the [npc.face] of [npc.a_faceRace], so nothing happens...)]</p>");
			}
			
		} else {
			UtilText.transformationContentSB.setLength(0);
			
			if (owner.isPlayer()) {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "An intense tingling sensation suddenly sweeps across your [pc.face], and you scrunch up your [pc.eyes] as you feel it start to transform."
							+ " With an audible crunch, your facial bones start to restructure themselves, and although the feeling isn't painful, it's enough of a shock to cause you to let out an involuntary cry.");
			} else {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "An intense tingling sensation suddenly sweeps across [npc.name]'s [npc.face], and [npc.she] scrunches up [npc.her] [npc.eyes] as [npc.she] feels it start to transform."
							+ " With an audible crunch, [npc.her] facial bones start to restructure themselves, and although the feeling isn't painful, it's enough of a shock to cause [npc.herhim] to let out an involuntary cry.");
			}
		}

		// Parse existing content before transformation:
		String s = UtilText.parse(owner, UtilText.transformationContentSB.toString());
		UtilText.transformationContentSB.setLength(0);
		UtilText.transformationContentSB.append(s);
		this.type = type;

		mouth.setType(type.getMouthType());
		tongue.setType(type.getTongueType());
		
		switch (type) {
			case HUMAN:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" Thankfully, the alarming feeling is over within a few moments, and you discover that you've been left with a normal human face, covered in [pc.faceSkin+].</br>"
								+ "You now have a [style.boldHuman(human face)], covered in [pc.faceFullDescription], and within your mouth, you have a [style.boldHuman(human tongue)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" Thankfully for [npc.herHim], the transformation only lasts a matter of moments, leaving [npc.herHim] with a normal human face, covered in [npc.faceSkin+].</br>"
								+ "[npc.Name] now has a [style.boldHuman(human face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] has a [style.boldHuman(human tongue)]."
							+ "</p>"));
				}
				break;
			case DEMON_COMMON:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" Thankfully, the alarming feeling is over within a few moments, and you discover that you've been left with a demonic, human-looking face, covered in [pc.faceSkin+].</br>"
								+ "You now have a [style.boldDemon(demonic face)], covered in [pc.faceFullDescription], and within your mouth, you have a [style.boldDemon(demonic tongue)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" Thankfully for [npc.herHim], the transformation only lasts a matter of moments, leaving [npc.herHim] with a demonic, human-looking face, covered in [npc.faceSkin+].</br>"
								+ "[npc.Name] now has a [style.boldDemon(demonic face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] has a [style.boldDemon(demonic tongue)]."
							+ "</p>"));
				}
				break;
			case DOG_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You feel your nose and mouth twitching and transforming as they push out into an anthropomorphic canine muzzle, and your tongue flattens and grows wider, turning into a dog-like tongue."
								+ " A layer of [pc.faceSkin+] quickly grows to cover your new face, and as the transformation finally comes to an end, you're left panting as you try to recover and catch your breath.</br>"
								+ "You now have an anthropomorphic [style.boldDogMorph(dog-like face)], covered in [pc.faceFullDescription], and within your mouth, you have a [style.boldDogMorph(flat, dog-like tongue)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" [npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic canine muzzle, and [npc.her] tongue flattens and grows wider, turning into a dog-like tongue."
								+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face, and as the transformation finally comes to an end, [npc.she]'s left panting as [npc.she] tries to recover and catch [npc.her] breath.</br>"
								+ "[npc.Name] now has an anthropomorphic [style.boldDogMorph(dog-like face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] has a [style.boldDogMorph(flat, dog-like tongue)]."
							+ "</p>"));
				}
				break;
			case LYCAN:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You feel your nose and mouth twitching and transforming as they push out into an anthropomorphic lupine muzzle, and your tongue flattens and grows wider, turning into a wolf-like tongue."
								+ " A layer of [pc.faceSkin+] quickly grows to cover your new face, and as the transformation finally comes to an end, you're left panting as you try to recover and catch your breath.</br>"
								+ "You now have an anthropomorphic [style.boldWolfMorph(wolf-like face)], covered in [pc.faceFullDescription], and within your mouth, you have a [style.boldWolfMorph(flat, wolf-like tongue)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" [npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic lupine muzzle, and [npc.her] tongue flattens and grows wider, turning into a wolf-like tongue."
								+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face, and as the transformation finally comes to an end, [npc.she]'s left panting as [npc.she] tries to recover and catch [npc.her] breath.</br>"
								+ "[npc.Name] now has an anthropomorphic [style.boldWolfMorph(wolf-like face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] has a [style.boldWolfMorph(flat, wolf-like tongue)]."
							+ "</p>"));
				}
				break;
			case CAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You feel your nose and mouth twitching and transforming as they push out into an anthropomorphic feline muzzle, and your tongue flattens and shifts into a little pink cat-like tongue."
								+ " A layer of [pc.faceSkin+] quickly grows to cover your new face, and as the transformation finally comes to an end, you're left panting as you try to recover and catch your breath.</br>"
								+ "You now have an anthropomorphic [style.boldCatMorph(cat-like face)], covered in [pc.faceFullDescription], and within your mouth, you have a [style.boldCatMorph(flat, cat-like tongue)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" [npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic feline muzzle, and [npc.her] tongue flattens and shifts into a little pink cat-like tongue."
								+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face, and as the transformation finally comes to an end, [npc.she]'s left panting as [npc.she] tries to recover and catch [npc.her] breath.</br>"
								+ "[npc.Name] now has an anthropomorphic [style.boldCatMorph(cat-like face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] has a [style.boldCatMorph(flat, cat-like tongue)]."
							+ "</p>"));
				}
				break;
			case COW_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You feel your nose and mouth twitching and transforming as they push out into an anthropomorphic bovine muzzle, and your tongue flattens and shifts into a cow-like tongue."
								+ " A layer of [pc.faceSkin+] quickly grows to cover your new face, and as the transformation finally comes to an end, you're left panting as you try to recover and catch your breath.</br>"
								+ "You now have an anthropomorphic [style.boldCowMorph(cow-like face)], covered in [pc.faceFullDescription], and within your mouth, you have a [style.boldCowMorph(flat, cow-like tongue)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" [npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic bovine muzzle, and [npc.her] tongue flattens and shifts into a cow-like tongue."
								+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face, and as the transformation finally comes to an end, [npc.she]'s left panting as [npc.she] tries to recover and catch [npc.her] breath.</br>"
								+ "[npc.Name] now has an anthropomorphic [style.boldCowMorph(cow-like face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] has a [style.boldCowMorph(flat, cow-like tongue)]."
							+ "</p>"));
				}
				break;
			case HORSE_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You feel your nose and mouth twitching and transforming as they push out into an anthropomorphic equine muzzle, and your tongue grows thicker and stronger, turning into a horse-like tongue."
								+ " A layer of [pc.faceSkin+] quickly grows to cover your new face, and as the transformation finally comes to an end, you're left panting as you try to recover and catch your breath.</br>"
								+ "You now have an anthropomorphic [style.boldHorseMorph(horse-like face)], covered in [pc.faceFullDescription], and within your mouth, you have a [style.boldHorseMorph(strong, horse-like tongue)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" [npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic equine muzzle, and [npc.her] tongue grows thicker and stronger, turning into a horse-like tongue."
								+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face, and as the transformation finally comes to an end, [npc.she]'s left panting as [npc.she] tries to recover and catch [npc.her] breath.</br>"
								+ "[npc.Name] now has an anthropomorphic [style.boldHorseMorph(horse-like face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] has a [style.boldHorseMorph(strong, horse-like tongue)]."
							+ "</p>"));
				}
				break;
			case SQUIRREL_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You feel your nose and mouth twitching and transforming as they push out into an anthropomorphic squirrel-like muzzle, and your tongue flattens and shifts into a little pink squirrel-like tongue."
								+ " A layer of [pc.faceSkin+] quickly grows to cover your new face, and as the transformation finally comes to an end, you're left panting as you try to recover and catch your breath.</br>"
								+ "You now have an anthropomorphic [style.boldSquirrelMorph(squirrel-like face)], covered in [pc.faceFullDescription], and within your mouth, you have a [style.boldSquirrelMorph(little squirrel-like tongue)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" [npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic squirrel-like muzzle, and [npc.her] tongue flattens and shifts into a little pink squirrel-like tongue."
								+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face, and as the transformation finally comes to an end, [npc.she]'s left panting as [npc.she] tries to recover and catch [npc.her] breath.</br>"
								+ "[npc.Name] now has an anthropomorphic [style.boldSquirrelMorph(squirrel-like face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] has a [style.boldSquirrelMorph(little squirrel-like tongue)]."
							+ "</p>"));
				}
				break;
			case HARPY:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You feel your nose and mouth twitching and transforming as they fuse together and push out into a short beak, and your tongue grows longer and thinner, turning into a bird-like tongue."
								+ " A layer of [pc.faceSkin+] quickly grows to cover your new face, and as the transformation finally comes to an end, you're left panting as you try to recover and catch your breath."
								+ " You find, much to your relief, that you're able to harden or soften the edges of your beak, allowing you to portray facial emotions as well as wrap your beak's edges around anything you might want to put in your mouth</br>"
								+ "You now have an anthropomorphic [style.boldHarpy(bird-like face)], covered in [pc.faceFullDescription], and within your mouth, you have a [style.boldHarpy(thin, bird-like tongue)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" [npc.Her] nose and mouth twitch and transform as they fuse together and push out into a short beak, and [npc.her] tongue grows longer and thinner, turning into a bird-like tongue."
								+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face, and as the transformation finally comes to an end, [npc.she]'s left panting as [npc.she] tries to recover and catch [npc.her] breath."
								+ " [npc.She] finds, much to [npc.her] relief, that [npc.she]'s able to harden or soften the edges of [npc.her] beak,"
									+ " allowing [npc.herHim] to portray facial emotions as well as wrap [npc.her] beak's edges around anything [npc.she] might want to put in [npc.her] mouth</br>"
								+ "[npc.Name] now has an anthropomorphic [style.boldHarpy(bird-like face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] has a [style.boldHarpy(thin, bird-like tongue)]."
							+ "</p>"));
				}
				break;
			default:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [pc.faceFullDescription] has quickly grown over your new face.</br>"
								+ "As the transformation comes to an end, you're left with [style.boldTfLesser([pc.face+])], which is covered in [pc.faceFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" Within a matter of moments, a layer of [npc.faceFullDescription] has quickly grown over [npc.her] new face.</br>"
								+ "As the transformation comes to an end, [npc.name] is left with [style.boldTfLesser([npc.face+])], which is covered in [npc.faceFullDescription]."
							+ "</p>"));
				}
		}
		return UtilText.parse(owner, UtilText.transformationContentSB.toString())
				+ "</br></br>"
				+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public boolean isPiercedNose() {
		return piercedNose;
	}

	public String setPiercedNose(GameCharacter owner, boolean piercedNose) {
		if(owner.isPiercedNose() == piercedNose) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		this.piercedNose = piercedNose;
		
		if(piercedNose) {
			if(owner.isPlayer()) {
				return "<p>Your [pc.lips] are now [style.boldGrow(pierced)]!</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.Name]'s [npc.lips] are now [style.boldGrow(pierced)]!</p>");
			}
		} else {
			if(owner.isPlayer()) {
				return "<p>Your [pc.lips] are [style.boldShrink(no longer pierced)]!</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.Name]'s [npc.lips] are [style.boldShrink(no longer pierced)]!</p>");
			}
		}
	}
	
	public BodyHair getFacialHair() {
		return facialHair;
	}

	public Covering getFacialHairType(GameCharacter owner) {
		return owner.getCovering(owner.getBodyHairCoveringType(owner.getFaceType().getRace()));
	}

	public String setFacialHair(GameCharacter owner, BodyHair facialHair) {
		
		if(owner.getFemininity()>=Femininity.ANDROGYNOUS.getMinimumFemininity() && facialHair!=BodyHair.NONE) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(You're too feminine to be able to grow a beard...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.She]'s too feminine to be able to grow a beard...)]</p>");
			}
		}
		
		if(getFacialHair() == facialHair) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
			
		} else {
			UtilText.transformationContentSB.setLength(0);
			
			switch(facialHair) {
				case NONE:
					if(owner.isPlayer()) {
						UtilText.transformationContentSB.append("<p>There is no longer any trace of "+getFacialHairType(owner).getFullDescription(owner, true)+" on your face.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner, "<p>There is no longer any trace of "+getFacialHairType(owner).getFullDescription(owner, true)+" on [npc.her] face.</p>"));
					}
					break;
				case MANICURED:
					if(owner.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a small amount of "+getFacialHairType(owner).getFullDescription(owner, true)+" on your face.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner, "<p>[npc.Name] now has a small amount of "+getFacialHairType(owner).getFullDescription(owner, true)+" on [npc.her] face.</p>"));
					}
					break;
				case TRIMMED:
					if(owner.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a well-trimmed beard of "+getFacialHairType(owner).getFullDescription(owner, true)+" on your face.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner, "<p>[npc.Name] now has a well-trimmed beard of "+getFacialHairType(owner).getFullDescription(owner, true)+" on [npc.her] face.</p>"));
					}
					break;
				case BUSHY:
					if(owner.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a thick, bushy beard of "+getFacialHairType(owner).getFullDescription(owner, true)+" on your face.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner, "<p>[npc.Name] now has a thick, bushy beard of "+getFacialHairType(owner).getFullDescription(owner, true)+" on [npc.her] face.</p>"));
					}
					break;
			}
		}
		
		this.facialHair = facialHair;

		return UtilText.transformationContentSB.toString();
	}
}
