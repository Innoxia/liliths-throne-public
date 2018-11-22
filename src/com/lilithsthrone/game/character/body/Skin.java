package com.lilithsthrone.game.character.body;

import java.io.Serializable;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.SkinType;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.2.10
 * @author Innoxia
 */
public class Skin implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	protected SkinType type;

	public Skin(SkinType type) {
		this.type = type;
	}

	@Override
	public SkinType getType() {
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
	public String getDescriptor(GameCharacter gc) {
		return type.getDescriptor(gc);
	}

	public String setType(GameCharacter owner, SkinType type) {
		if (type == getType()) {
			if (owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(You already have the [pc.skin] of [pc.a_skinRace], so nothing happens...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already has the [npc.skin] of [npc.a_skinRace], so nothing happens...)]</p>");
			}
			
		} else {
			UtilText.transformationContentSB.setLength(0);
			
			if (owner.isPlayer()) {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "Your entire torso starts to itch and grow hot, and you frantically start scratching all over as your [pc.skin] starts to transform.");
			} else {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "[npc.Name] squirms in discomfort and starts scratching [npc.herself] as [npc.her] [npc.skin] starts to transform.");
			}
		}

		// Parse existing content before transformation:
		String s = UtilText.parse(owner, UtilText.transformationContentSB.toString());
		UtilText.transformationContentSB.setLength(0);
		UtilText.transformationContentSB.append(s);
		this.type = type;
		
		switch (type) {
			case HUMAN:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and you let out a deep sigh as the itching finally stops, leaving your torso covered with human skin."
								+ "<br/>"
								+ "You now have [style.boldHuman(human)], [pc.skinFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and [npc.she] lets out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with human skin."
								+ "<br/>"
								+ "[npc.Name] now has [style.boldHuman(human)], [npc.skinFullDescription]."
							+ "</p>");
				}
				break;
			case DEMON_COMMON:
				if (!owner.isShortStature()) {
					UtilText.transformationContentSB.append(
							" After just a few moments, the transformation comes to an end, and [npc.she] [npc.verb(let)] out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with demonic skin."
							+ " It's far smoother than regular human skin, and the colour tones all over [npc.her] body have become perfectly balanced in order to help show off [npc.her] figure."
							+ "<br/>"
							+ "[npc.Name] now [npc.has] [style.boldDemon(demonic)], [npc.skinFullDescription]."
						+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
							" After just a few moments, the transformation comes to an end, and [npc.she] [npc.verb(let)] out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with impish skin."
							+ " It's far smoother than regular human skin, and the colour tones all over [npc.her] body have become perfectly balanced in order to help show off [npc.her] figure."
							+ "<br/>"
							+ "[npc.Name] now [npc.has] [style.boldImp(impish)], [npc.skinFullDescription]."
						+ "</p>");
				}
				break;
			case DOG_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and you let out a deep sigh as the itching finally stops, leaving your torso covered with dog-like fur."
								+ " Looking at your new fur, you notice that it follows the lines of your figure and is quite smooth and pleasant to touch."
								+ "<br/>"
								+ "You now have [style.boldDogMorph(canine)], [pc.skinFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and [npc.she] lets out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with dog-like fur."
								+ " [npc.Her] new fur follows the lines of [npc.her] figure and is quite smooth and pleasant to touch."
								+ "<br/>"
								+ "[npc.Name] now has [style.boldDogMorph(canine)], [npc.skinFullDescription]."
							+ "</p>");
				}
				break;
			case FOX_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and you let out a deep sigh as the itching finally stops, leaving your torso covered with fox-like fur."
								+ " Looking at your new fur, you see that it's a little shaggy around your joints and is quite densely packed."
								+ "</br>"
								+ "You now have [style.boldFoxMorph(vulpine)], [pc.skinFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and [npc.she] lets out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with fox-like fur."
								+ " [npc.Her] new fur is a little shaggy around [npc.her] joints and is quite densely packed."
								+ "</br>"
								+ "[npc.Name] now has [style.boldFoxMorph(vulpine)], [npc.skinFullDescription]."
							+ "</p>");
				}
				break;
			case LYCAN:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and you let out a deep sigh as the itching finally stops, leaving your torso covered with wolf-like fur."
								+ " Looking at your new fur, you see that it's a little shaggy around your joints and is quite densely packed."
								+ "<br/>"
								+ "You now have [style.boldWolfMorph(lupine)], [pc.skinFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and [npc.she] lets out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with wolf-like fur."
								+ " [npc.Her] new fur is a little shaggy around [npc.her] joints and is quite densely packed."
								+ "<br/>"
								+ "[npc.Name] now has [style.boldWolfMorph(lupine)], [npc.skinFullDescription]."
							+ "</p>");
				}
				break;
			case CAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and you let out a deep sigh as the itching finally stops, leaving your torso covered with cat-like fur."
								+ " Looking at your new fur, you see that it follows the lines of your figure and is extremely smooth and soft."
								+ "<br/>"
								+ "You now have [style.boldCatMorph(feline)], [pc.skinFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and [npc.she] lets out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with cat-like fur."
								+ " [npc.Her] new fur follows the lines of [npc.her] figure and is extremely smooth and soft."
								+ "<br/>"
								+ "[npc.Name] now has [style.boldCatMorph(feline)], [npc.skinFullDescription]."
							+ "</p>");
				}
				break;
			case HORSE_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and you let out a deep sigh as the itching finally stops, leaving your torso covered with short, horse-like hair."
								+ " Your new hair looks very sleek, and helps to show off your figure, although it's a little coarse to the touch."
								+ "<br/>"
								+ "You now have [style.boldHorseMorph(equine)], [pc.skinFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and [npc.she] lets out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with short, horse-like hair."
								+ " [npc.Her] new hair looks very sleek, and helps to show off [npc.her] figure, although it's a little coarse to the touch."
								+ "<br/>"
								+ "[npc.Name] now has [style.boldHorseMorph(equine)], [npc.skinFullDescription]."
							+ "</p>");
				}
				break;
			case REINDEER_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and you let out a deep sigh as the itching finally stops, leaving your torso covered with short, reindeer-like hair."
								+ " Your new fur looks very sleek, and helps to show off your figure, although it's a little coarse to the touch."
								+ "<br/>"
								+ "You now have [style.boldReindeerMorph(rangiferine)], [pc.skinFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and [npc.she] lets out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with short, reindeer-like hair."
								+ " [npc.Her] new fur looks very sleek, and helps to show off [npc.her] figure, although it's a little coarse to the touch."
								+ "<br/>"
								+ "[npc.Name] now has [style.boldReindeerMorph(rangiferine)], [npc.skinFullDescription]."
							+ "</p>");
				}
				break;
			case COW_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and you let out a deep sigh as the itching finally stops, leaving your torso covered with short, cow-like hair."
								+ " Your new hair looks very sleek, and helps to show off your figure, although it's a little coarse to the touch."
								+ "<br/>"
								+ "You now have [style.boldCowMorph(bovine)], [pc.skinFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and [npc.she] lets out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with short, cow-like hair."
								+ " [npc.Her] new hair looks very sleek, and helps to show off [npc.her] figure, although it's a little coarse to the touch."
								+ "<br/>"
								+ "[npc.Name] now has [style.boldCowMorph(bovine)], [npc.skinFullDescription]."
							+ "</p>");
				}
				break;
			case SQUIRREL_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and you let out a deep sigh as the itching finally stops, leaving your torso covered with squirrel-like fur."
								+ " Looking at your new fur, you see that it follows the lines of your figure and is extremely smooth and soft."
								+ "<br/>"
								+ "You now have [style.boldSquirrelMorph(squirrel-like)], [pc.skinFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and [npc.she] lets out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with squirrel-like fur."
								+ " [npc.Her] new fur follows the lines of [npc.her] figure and is extremely smooth and soft."
								+ "<br/>"
								+ "[npc.Name] now has [style.boldSquirrelMorph(squirrel-like)], [npc.skinFullDescription]."
							+ "</p>");
				}
				break;
			case RAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and you let out a deep sigh as the itching finally stops, leaving your torso covered with rat-like fur."
								+ " Looking at your new fur, you see that it follows the lines of your figure and is a little coarse to the touch."
								+ "<br/>"
								+ "You now have [style.boldRatMorph(rat-like)], [pc.skinFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and [npc.she] lets out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with rat-like fur."
								+ " [npc.Her] new fur follows the lines of [npc.her] figure and is a little coarse to the touch."
								+ "<br/>"
								+ "[npc.Name] now has [style.boldRatMorph(rat-like)], [npc.skinFullDescription]."
							+ "</p>");
				}
				break;
			case RABBIT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and you let out a deep sigh as the itching finally stops, leaving your torso covered with rabbit-like fur."
								+ " Looking at your new fur, you see that it follows the lines of your figure and is extremely smooth and soft."
								+ "<br/>"
								+ "You now have [style.boldRabbitMorph(rabbit-like)], [pc.skinFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and [npc.she] lets out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with rabbit-like fur."
								+ " [npc.Her] new fur follows the lines of [npc.her] figure and is extremely smooth and soft."
								+ "<br/>"
								+ "[npc.Name] now has [style.boldRabbitMorph(rabbit-like)], [npc.skinFullDescription]."
							+ "</p>");
				}
				break;
			case BAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and you let out a deep sigh as the itching finally stops, leaving your torso covered with bat-like fur."
								+ " Looking at your new fur, you see that it follows the lines of your figure and is quite smooth and pleasant to touch."
								+ "<br/>"
								+ "You now have [style.boldBatMorph(bat-like)], [pc.skinFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and [npc.she] lets out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with bat-like fur."
								+ " [npc.Her] new fur follows the lines of [npc.her] figure and is quite smooth and pleasant to touch."
								+ "<br/>"
								+ "[npc.Name] now has [style.boldBatMorph(bat-like)], [npc.skinFullDescription]."
							+ "</p>");
				}
				break;
			case ALLIGATOR_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and you let out a deep sigh as the itching finally stops, leaving your torso covered with a layer of tough, overlapping scales."
								+ " Your new scales follow the lines of your figure, and, while being quite hard to the touch, are also very smooth when rubbed in the right direction."
								+ "<br/>"
								+ "You now have [style.boldGatorMorph(reptile)], [pc.skinFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and [npc.she] lets out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with tough, overlapping scales."
								+ " [npc.Her] new scales follow the lines of [npc.her] figure, and, while being quite hard to the touch, are also very smooth when rubbed in the right direction."
								+ "<br/>"
								+ "[npc.Name] now has [style.boldGatorMorph(reptile)], [npc.skinFullDescription]."
							+ "</p>");
				}
				break;
			case HARPY:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and you let out a deep sigh as the itching finally stops, leaving your torso covered with a layer of beautiful, overlapping feathers."
								+ " Your new feathers follow the lines of your figure, and are extremely smooth and soft to the touch."
								+ "<br/>"
								+ "You now have [style.boldHarpy(avian)], [pc.skinFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" After just a few moments, the transformation comes to an end, and [npc.she] lets out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with beautiful, overlapping feathers."
								+ " [npc.Her] new feathers follow the lines of [npc.her] figure, and are extremely smooth and soft to the touch."
								+ "<br/>"
								+ "[npc.Name] now has [style.boldHarpy(avian)], [npc.skinFullDescription]."
							+ "</p>");
				}
				break;
			case ANGEL: //TODO
				break;
		}
		
		return UtilText.parse(owner, UtilText.transformationContentSB.toString())
				+ "<p>"
				+ owner.postTransformationCalculation()
				+ "</p>";
	}
}
