package com.lilithsthrone.game.character.body;

import java.io.Serializable;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.0
 * @version 0.2.2
 * @author Innoxia
 */
public class Face implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	protected FaceType type;
	protected boolean piercedNose;
	protected BodyHair facialHair;
	
	protected Mouth mouth;
	protected Tongue tongue;

	public Face(FaceType type, int lipSize) {
		this.type = type;
		piercedNose = false;
		facialHair = BodyHair.ZERO_NONE;
		
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
		if(owner==null) {
			this.type = type;
			return "";
		}
		
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
							+ "An intense tingling sensation suddenly sweeps across [npc.namePos] [npc.face], and [npc.she] scrunches up [npc.her] [npc.eyes] as [npc.she] feels it start to transform."
							+ " With an audible crunch, [npc.her] facial bones start to restructure themselves, and although the feeling isn't painful, it's enough of a shock to cause [npc.herHim] to let out an involuntary cry.");
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
								" Thankfully, the alarming feeling is over within a few moments, and you discover that you've been left with a normal human face, covered in [pc.faceSkin+].<br/>"
								+ "You now have a [style.boldHuman(human face)], covered in [pc.faceFullDescription], and within your mouth, you have a [style.boldHuman(human tongue)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" Thankfully for [npc.herHim], the transformation only lasts a matter of moments, leaving [npc.herHim] with a normal human face, covered in [npc.faceSkin+].<br/>"
								+ "[npc.Name] now has a [style.boldHuman(human face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] has a [style.boldHuman(human tongue)]."
							+ "</p>"));
				}
				break;
			case ANGEL:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" Thankfully, the alarming feeling is over within a few moments, and you discover that you've been left with an angelic, human-looking face, covered in [pc.faceSkin+].<br/>"
								+ "You now have an [style.boldAngel(angelic face)], covered in [pc.faceFullDescription], and within your mouth, you have an [style.boldAngel(angelic tongue)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" Thankfully for [npc.herHim], the transformation only lasts a matter of moments, leaving [npc.herHim] with an angelic, human-looking face, covered in [npc.faceSkin+].<br/>"
								+ "[npc.Name] now has an [style.boldAngel(angelic face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] has an [style.boldAngel(angelic tongue)]."
							+ "</p>"));
				}
				break;
			case DEMON_COMMON:
				if (!owner.isShortStature()) {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
							" Thankfully, the alarming transformation only lasts a matter of moments, leaving [npc.herHim] with a demonic, human-looking face, covered in [npc.faceSkin+].<br/>"
							+ "[npc.Name] now [npc.has] a [style.boldDemon(demonic face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] [npc.has] a [style.boldDemon(demonic tongue)]."
						+ "</p>"));
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
							" Thankfully, the alarming transformation only lasts a matter of moments, leaving [npc.herHim] with an impish, human-looking face, covered in [npc.faceSkin+].<br/>"
							+ "[npc.Name] now [npc.has] an [style.boldImp(impish face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] [npc.has] an [style.boldImp(impish tongue)]."
						+ "</p>"));
				}
				break;
			case DOG_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You feel your nose and mouth twitching and transforming as they push out into an anthropomorphic canine muzzle, and your tongue flattens and grows wider, turning into a dog-like tongue."
								+ " A layer of [pc.faceSkin+] quickly grows to cover your new face, and as the transformation finally comes to an end, you're left panting as you try to recover and catch your breath.<br/>"
								+ "You now have an anthropomorphic [style.boldDogMorph(dog-like face)], covered in [pc.faceFullDescription], and within your mouth, you have a [style.boldDogMorph(flat, dog-like tongue)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" [npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic canine muzzle, and [npc.her] tongue flattens and grows wider, turning into a dog-like tongue."
								+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face, and as the transformation finally comes to an end, [npc.sheIs] left panting as [npc.she] tries to recover and catch [npc.her] breath.<br/>"
								+ "[npc.Name] now has an anthropomorphic [style.boldDogMorph(dog-like face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] has a [style.boldDogMorph(flat, dog-like tongue)]."
							+ "</p>"));
				}
				break;
			case FOX_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You feel your nose and mouth twitching and transforming as they push out into an anthropomorphic vulpine muzzle, and your tongue flattens and shifts into a pink fox-like tongue."
								+ " A layer of [pc.faceSkin+] quickly grows to cover your new face, and as the transformation finally comes to an end, you're left panting as you try to recover and catch your breath.</br>"
								+ "You now have an anthropomorphic [style.boldFoxMorph(fox-like face)], covered in [pc.faceFullDescription], and within your mouth, you have a [style.boldFoxMorph(flat, fox-like tongue)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" [npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic vulpine muzzle, and [npc.her] tongue flattens and shiifts into a pink fox-like tongue."
								+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face, and as the transformation finally comes to an end, [npc.sheIs] left panting as [npc.she] tries to recover and catch [npc.her] breath.</br>"
								+ "[npc.Name] now has an anthropomorphic [style.boldFoxMorph(fox-like face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] has a [style.boldFoxMorph(flat, fox-like tongue)]."
							+ "</p>"));
				}
				break;
			case LYCAN:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You feel your nose and mouth twitching and transforming as they push out into an anthropomorphic lupine muzzle, and your tongue flattens and grows wider, turning into a wolf-like tongue."
								+ " A layer of [pc.faceSkin+] quickly grows to cover your new face, and as the transformation finally comes to an end, you're left panting as you try to recover and catch your breath.<br/>"
								+ "You now have an anthropomorphic [style.boldWolfMorph(wolf-like face)], covered in [pc.faceFullDescription], and within your mouth, you have a [style.boldWolfMorph(flat, wolf-like tongue)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" [npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic lupine muzzle, and [npc.her] tongue flattens and grows wider, turning into a wolf-like tongue."
								+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face, and as the transformation finally comes to an end, [npc.sheIs] left panting as [npc.she] tries to recover and catch [npc.her] breath.<br/>"
								+ "[npc.Name] now has an anthropomorphic [style.boldWolfMorph(wolf-like face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] has a [style.boldWolfMorph(flat, wolf-like tongue)]."
							+ "</p>"));
				}
				break;
			case CAT_MORPH: case CAT_MORPH_PANTHER:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You feel your nose and mouth twitching and transforming as they push out into an anthropomorphic feline muzzle, and your tongue flattens and shifts into a little pink cat-like tongue."
								+ " A layer of [pc.faceSkin+] quickly grows to cover your new face, and as the transformation finally comes to an end, you're left panting as you try to recover and catch your breath.<br/>"
								+ "You now have an anthropomorphic [style.boldCatMorph(cat-like face)], covered in [pc.faceFullDescription], and within your mouth, you have a [style.boldCatMorph(flat, cat-like tongue)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" [npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic feline muzzle, and [npc.her] tongue flattens and shifts into a little pink cat-like tongue."
								+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face, and as the transformation finally comes to an end, [npc.sheIs] left panting as [npc.she] tries to recover and catch [npc.her] breath.<br/>"
								+ "[npc.Name] now has an anthropomorphic [style.boldCatMorph(cat-like face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] has a [style.boldCatMorph(flat, cat-like tongue)]."
							+ "</p>"));
				}
				break;
			case COW_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You feel your nose and mouth twitching and transforming as they push out into an anthropomorphic bovine muzzle, and your tongue flattens and shifts into a cow-like tongue."
								+ " A layer of [pc.faceSkin+] quickly grows to cover your new face, and as the transformation finally comes to an end, you're left panting as you try to recover and catch your breath.<br/>"
								+ "You now have an anthropomorphic [style.boldCowMorph(cow-like face)], covered in [pc.faceFullDescription], and within your mouth, you have a [style.boldCowMorph(flat, cow-like tongue)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" [npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic bovine muzzle, and [npc.her] tongue flattens and shifts into a cow-like tongue."
								+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face, and as the transformation finally comes to an end, [npc.sheIs] left panting as [npc.she] tries to recover and catch [npc.her] breath.<br/>"
								+ "[npc.Name] now has an anthropomorphic [style.boldCowMorph(cow-like face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] has a [style.boldCowMorph(flat, cow-like tongue)]."
							+ "</p>"));
				}
				break;
			case ALLIGATOR_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You feel your nose and mouth twitching and transforming as they push out into an anthropomorphic reptile muzzle, and your tongue flattens out, turning into an alligator-like tongue."
								+ " A layer of [pc.faceSkin+] quickly grows to cover your new face, and as the transformation finally comes to an end, you're left panting as you try to recover and catch your breath.<br/>"
								+ "You now have an anthropomorphic [style.boldGatorMorph(alligator-like face)], covered in [pc.faceFullDescription], and within your mouth, you have a [style.boldGatorMorph(flat, alligator-like tongue)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" [npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic reptile muzzle, and [npc.her] tongue flattens out, turning into an alligator-like tongue."
								+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face, and as the transformation finally comes to an end, [npc.sheIs] left panting as [npc.she] tries to recover and catch [npc.her] breath.<br/>"
								+ "[npc.Name] now has an anthropomorphic [style.boldGatorMorph(alligator-like face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] has a [style.boldGatorMorph(flat, alligator-like tongue)]."
							+ "</p>"));
				}
				break;
			case HORSE_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You feel your nose and mouth twitching and transforming as they push out into an anthropomorphic equine muzzle, and your tongue grows thicker and stronger, turning into a horse-like tongue."
								+ " A layer of [pc.faceSkin+] quickly grows to cover your new face, and as the transformation finally comes to an end, you're left panting as you try to recover and catch your breath.<br/>"
								+ "You now have an anthropomorphic [style.boldHorseMorph(horse-like face)], covered in [pc.faceFullDescription], and within your mouth, you have a [style.boldHorseMorph(strong, horse-like tongue)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" [npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic equine muzzle, and [npc.her] tongue grows thicker and stronger, turning into a horse-like tongue."
								+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face, and as the transformation finally comes to an end, [npc.sheIs] left panting as [npc.she] tries to recover and catch [npc.her] breath.<br/>"
								+ "[npc.Name] now has an anthropomorphic [style.boldHorseMorph(horse-like face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] has a [style.boldHorseMorph(strong, horse-like tongue)]."
							+ "</p>"));
				}
				break;
			case REINDEER_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You feel your nose and mouth twitching and transforming as they push out into an anthropomorphic reindeer-like muzzle, and your tongue grows thicker and stronger, turning into a rangiferine tongue."
								+ " A layer of [pc.faceSkin+] quickly grows to cover your new face, and as the transformation finally comes to an end, you're left panting as you try to recover and catch your breath.<br/>"
								+ "You now have an anthropomorphic [style.boldReindeerMorph(reindeer-like face)], covered in [pc.faceFullDescription], and within your mouth, you have a [style.boldReindeerMorph(strong, reindeer-like tongue)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" [npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic reindeer-like muzzle, and [npc.her] tongue grows thicker and stronger, turning into a rangiferine tongue."
								+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face, and as the transformation finally comes to an end, [npc.sheIs] left panting as [npc.she] tries to recover and catch [npc.her] breath.<br/>"
								+ "[npc.Name] now has an anthropomorphic [style.boldReindeerMorph(reindeer-like face)], covered in [npc.faceFullDescription],"
									+ " and within [npc.her] mouth, [npc.she] has a [style.boldReindeerMorph(strong, reindeer-like tongue)]."
							+ "</p>"));
				}
				break;
			case SQUIRREL_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You feel your nose and mouth twitching and transforming as they push out into an anthropomorphic squirrel-like muzzle, and your tongue flattens and shifts into a little pink squirrel-like tongue."
								+ " A layer of [pc.faceSkin+] quickly grows to cover your new face, and as the transformation finally comes to an end, you're left panting as you try to recover and catch your breath.<br/>"
								+ "You now have an anthropomorphic [style.boldSquirrelMorph(squirrel-like face)], covered in [pc.faceFullDescription], and within your mouth, you have a [style.boldSquirrelMorph(little squirrel-like tongue)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" [npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic squirrel-like muzzle, and [npc.her] tongue flattens and shifts into a little pink squirrel-like tongue."
								+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face, and as the transformation finally comes to an end, [npc.sheIs] left panting as [npc.she] tries to recover and catch [npc.her] breath.<br/>"
								+ "[npc.Name] now has an anthropomorphic [style.boldSquirrelMorph(squirrel-like face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] has a [style.boldSquirrelMorph(little squirrel-like tongue)]."
							+ "</p>"));
				}
				break;
			case RAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You feel your nose and mouth twitching and transforming as they push out into an anthropomorphic rat-like muzzle, and your tongue flattens and shifts into a little pink rat-like tongue."
								+ " A layer of [pc.faceSkin+] quickly grows to cover your new face, and as the transformation finally comes to an end, you're left panting as you try to recover and catch your breath.<br/>"
								+ "You now have an anthropomorphic [style.boldRatMorph(rat-like face)], covered in [pc.faceFullDescription], and within your mouth, you have a [style.boldRatMorph(little rat-like tongue)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" [npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic rat-like muzzle, and [npc.her] tongue flattens and shifts into a little pink rat-like tongue."
								+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face, and as the transformation finally comes to an end, [npc.sheIs] left panting as [npc.she] tries to recover and catch [npc.her] breath.<br/>"
								+ "[npc.Name] now has an anthropomorphic [style.boldRatMorph(rat-like face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] has a [style.boldRatMorph(little rat-like tongue)]."
							+ "</p>"));
				}
				break;
			case RABBIT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You feel your nose and mouth twitching and transforming as they push out into an anthropomorphic rabbit-like muzzle, and your tongue flattens and shifts into a little pink rabbit-like tongue."
								+ " A layer of [pc.faceSkin+] quickly grows to cover your new face, and as the transformation finally comes to an end, you're left panting as you try to recover and catch your breath.<br/>"
								+ "You now have an anthropomorphic [style.boldRabbitMorph(rabbit-like face)], covered in [pc.faceFullDescription], and within your mouth, you have a [style.boldRabbitMorph(little rabbit-like tongue)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" [npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic rabbit-like muzzle, and [npc.her] tongue flattens and shifts into a little pink rabbit-like tongue."
								+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face, and as the transformation finally comes to an end, [npc.sheIs] left panting as [npc.she] tries to recover and catch [npc.her] breath.<br/>"
								+ "[npc.Name] now has an anthropomorphic [style.boldRabbitMorph(rabbit-like face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] has a [style.boldRabbitMorph(little rabbit-like tongue)]."
							+ "</p>"));
				}
				break;
			case BAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You feel your nose and mouth twitching and transforming as they push out into an anthropomorphic bat-like muzzle, and your tongue flattens and shifts into a little pink bat-like tongue."
								+ " A layer of [pc.faceSkin+] quickly grows to cover your new face, and as the transformation finally comes to an end, you're left panting as you try to recover and catch your breath.<br/>"
								+ "You now have an anthropomorphic [style.boldBatMorph(bat-like face)], covered in [pc.faceFullDescription], and within your mouth, you have a [style.boldBatMorph(little bat-like tongue)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" [npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic bat-like muzzle, and [npc.her] tongue flattens and shifts into a little pink bat-like tongue."
								+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face, and as the transformation finally comes to an end, [npc.sheIs] left panting as [npc.she] tries to recover and catch [npc.her] breath.<br/>"
								+ "[npc.Name] now has an anthropomorphic [style.boldBatMorph(bat-like face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] has a [style.boldBatMorph(little bat-like tongue)]."
							+ "</p>"));
				}
				break;
			case HARPY:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You feel your nose and mouth twitching and transforming as they fuse together and push out into a short beak, and your tongue grows longer and thinner, turning into a bird-like tongue."
								+ " A layer of [pc.faceSkin+] quickly grows to cover your new face, and as the transformation finally comes to an end, you're left panting as you try to recover and catch your breath."
								+ " You find, much to your relief, that you're able to harden or soften the edges of your beak, allowing you to portray facial emotions as well as wrap your beak's edges around anything you might want to put in your mouth<br/>"
								+ "You now have an anthropomorphic [style.boldHarpy(bird-like face)], covered in [pc.faceFullDescription], and within your mouth, you have a [style.boldHarpy(thin, bird-like tongue)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(UtilText.parse(owner,
								" [npc.Her] nose and mouth twitch and transform as they fuse together and push out into a short beak, and [npc.her] tongue grows longer and thinner, turning into a bird-like tongue."
								+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face, and as the transformation finally comes to an end, [npc.sheIs] left panting as [npc.she] tries to recover and catch [npc.her] breath."
								+ " [npc.She] finds, much to [npc.her] relief, that [npc.sheIs] able to harden or soften the edges of [npc.her] beak,"
									+ " allowing [npc.herHim] to portray facial emotions as well as wrap [npc.her] beak's edges around anything [npc.she] might want to put in [npc.her] mouth<br/>"
								+ "[npc.Name] now has an anthropomorphic [style.boldHarpy(bird-like face)], covered in [npc.faceFullDescription], and within [npc.her] mouth, [npc.she] has a [style.boldHarpy(thin, bird-like tongue)]."
							+ "</p>"));
				}
				break;
//			default:
//				if (owner.isPlayer()) {
//					UtilText.transformationContentSB.append(
//								" Within a matter of moments, a layer of [pc.faceFullDescription] has quickly grown over your new face.<br/>"
//								+ "As the transformation comes to an end, you're left with [style.boldTfLesser([pc.face+])], which is covered in [pc.faceFullDescription]."
//							+ "</p>");
//				} else {
//					UtilText.transformationContentSB.append(UtilText.parse(owner,
//								" Within a matter of moments, a layer of [npc.faceFullDescription] has quickly grown over [npc.her] new face.<br/>"
//								+ "As the transformation comes to an end, [npc.name] is left with [style.boldTfLesser([npc.face+])], which is covered in [npc.faceFullDescription]."
//							+ "</p>"));
//				}
		}
		return UtilText.parse(owner, UtilText.transformationContentSB.toString())
				+ "<br/><br/>"
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
				return "<p>Your [pc.nose] is now [style.boldGrow(pierced)]!</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.NamePos] [npc.nose] is now [style.boldGrow(pierced)]!</p>");
			}
			
		} else {
			AbstractClothing c = owner.getClothingInSlot(InventorySlot.PIERCING_NOSE);
			String piercingUnequip = "";
			if(c!=null) {
				owner.forceUnequipClothingIntoVoid(owner, c);
				piercingUnequip = owner.addClothing(c, false);
			}
			
			if(owner.isPlayer()) {
				return "<p>"
							+ "Your [pc.nose] is [style.boldShrink(no longer pierced)]!"
						+ "</p>"
						+piercingUnequip;
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.NamePos] [npc.nose] is [style.boldShrink(no longer pierced)]!"
						+ "</p>"
						+piercingUnequip);
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
		if(owner==null) {
			this.facialHair = facialHair;
			return "";
		}
		
		if(owner.getFemininityValue()>=Femininity.ANDROGYNOUS.getMinimumFemininity() && facialHair!=BodyHair.ZERO_NONE && !Main.getProperties().hasValue(PropertyValue.feminineBeardsContent)) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(You're too feminine to be able to grow a beard...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.sheIs] too feminine to be able to grow a beard...)]</p>");
			}
		}
		
		if(getFacialHair() == facialHair) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
			
		} else {
			UtilText.transformationContentSB.setLength(0);
			
			switch(facialHair) {
				case ZERO_NONE:
					if(owner.isPlayer()) {
						UtilText.transformationContentSB.append("<p>There is no longer any trace of "+getFacialHairType(owner).getFullDescription(owner, true)+" on your face.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner, "<p>There is no longer any trace of "+getFacialHairType(owner).getFullDescription(owner, true)+" on [npc.her] face.</p>"));
					}
					break;
				case ONE_STUBBLE:
					if(owner.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a stubbly layer of "+getFacialHairType(owner).getFullDescription(owner, true)+" on your face.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner, "<p>[npc.Name] now has a stubbly layer of "+getFacialHairType(owner).getFullDescription(owner, true)+" on [npc.her] face.</p>"));
					}
					break;
				case TWO_MANICURED:
					if(owner.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a small amount of "+getFacialHairType(owner).getFullDescription(owner, true)+" on your face.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner, "<p>[npc.Name] now has a small amount of "+getFacialHairType(owner).getFullDescription(owner, true)+" on [npc.her] face.</p>"));
					}
					break;
				case THREE_TRIMMED:
					if(owner.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a well-trimmed beard of "+getFacialHairType(owner).getFullDescription(owner, true)+" on your face.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner, "<p>[npc.Name] now has a well-trimmed beard of "+getFacialHairType(owner).getFullDescription(owner, true)+" on [npc.her] face.</p>"));
					}
					break;
				case FOUR_NATURAL:
					if(owner.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a beard, made of "+getFacialHairType(owner).getFullDescription(owner, true)+".</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner, "<p>[npc.Name] now has a beard, made of "+getFacialHairType(owner).getFullDescription(owner, true)+".</p>"));
					}
					break;
				case FIVE_UNKEMPT:
					if(owner.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have an unkempt, bushy beard, made of "+getFacialHairType(owner).getFullDescription(owner, true)+".</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner, "<p>[npc.Name] now has an unkempt, bushy beard, made of "+getFacialHairType(owner).getFullDescription(owner, true)+".</p>"));
					}
					break;
				case SIX_BUSHY:
					if(owner.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a large, bushy beard, made of "+getFacialHairType(owner).getFullDescription(owner, true)+".</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner, "<p>[npc.Name] now has a large, bushy beard, made of "+getFacialHairType(owner).getFullDescription(owner, true)+".</p>"));
					}
					break;
				case SEVEN_WILD:
					if(owner.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a wild, thick beard, made of "+getFacialHairType(owner).getFullDescription(owner, true)+".</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner, "<p>[npc.Name] now has a wild, thick beard, made of "+getFacialHairType(owner).getFullDescription(owner, true)+".</p>"));
					}
					break;
			}
		}
		
		this.facialHair = facialHair;

		return UtilText.transformationContentSB.toString();
	}
}
