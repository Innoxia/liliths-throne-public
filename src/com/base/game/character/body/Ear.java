package com.base.game.character.body;

import java.io.Serializable;

import com.base.game.character.GameCharacter;
import com.base.game.character.body.types.EarType;
import com.base.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public class Ear implements BodyPartInterface, Serializable {

	private static final long serialVersionUID = 1L;
	private EarType type;
	private boolean pierced;

	public Ear(EarType type) {
		this.type = type;
		pierced = false;
	}

	@Override
	public EarType getType() {
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
	
	public String setType(GameCharacter owner, EarType type) {
		if (type == getType()) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(You already have the [pc.ears] of [pc.a_earRace], so nothing happens...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already has the [npc.ears] of [npc.a_earRace], so nothing happens...)]</p>");
			}
		}
		
		UtilText.transformationContentSB.setLength(0);
		
		if (owner.isPlayer()) {
			UtilText.transformationContentSB.append(
					"<p>"
						+ "Your [pc.ears] start to involuntarily twitch and itch, and, feeling them start to transform, you let out a little gasp and reach up to rub at them.");
		} else {
			UtilText.transformationContentSB.append(
					"<p>"
							+ "[npc.Name]'s [npc.ears] start to involuntarily twitch and itch, and, feeling them start to transform, [npc.she] lets out a little gasp and reaches up to rub at them.");
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
								" The hot itching feeling passes after a few moments, leaving you with normal-looking human ears, covered in [pc.earFullDescriptionColour]."
								+ "</br>"
								+ "You now have [style.boldHuman(human ears)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" The hot itching feeling passes after a few moments, leaving [npc.herHim] with normal-looking human ears, covered in [npc.earFullDescriptionColour]."
								+ "</br>"
								+ "[npc.Name] now has [style.boldHuman(human ears)]."
							+ "</p>");
				}
				break;
			case DEMON_COMMON:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" They quickly shift into delicate, human-like ears, but with long, pointed tips, all covered in [pc.earFullDescriptionColour]."
								+ "</br>"
								+ "You now have [style.boldDemon(pointed, demonic ears)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" They quickly shift into delicate, human-like ears, but with long, pointed tips, all covered in [npc.earFullDescriptionColour]."
								+ "</br>"
								+ "[npc.Name] now has [style.boldDemon(pointed, demonic ears)]."
							+ "</p>");
				}
				break;
			case DOG_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" They quickly grow into upright points, and shift to sit higher up on your head than a normal pair of human ears would."
								+ " A layer of [pc.earFullDescriptionColour] grows to cover them, and as the transformation finishes, you discover that you can easily twitch your new dog-like ears back and forth."
								+ "</br>"
								+ "You now have [style.boldDogMorph(anthropomorphic, dog-like ears)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" They quickly grow into upright points, and shift to sit higher up on [npc.her] head than a normal pair of human ears would."
								+ " A layer of [npc.earFullDescriptionColour] grows to cover them, and as the transformation finishes, [npc.she] experimentally twitches [npc.her] new dog-like ears back and forth."
								+ "</br>"
								+ "[npc.Name] now has [style.boldDogMorph(anthropomorphic, dog-like ears)]."
							+ "</p>");
				}
				break;
			case LYCAN:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" They quickly grow into large, upright points, and shift to sit higher up on your head than a normal pair of human ears would."
								+ " A layer of [pc.earFullDescriptionColour] grows to cover them, and as the transformation finishes, you discover that you can easily twitch your new wolf-like ears back and forth."
								+ "</br>"
								+ "You now have [style.boldWolfMorph(anthropomorphic, wolf-like ears)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" They quickly grow into large, upright points, and shift to sit higher up on [npc.her] head than a normal pair of human ears would."
								+ " A layer of [npc.earFullDescriptionColour] grows to cover them, and as the transformation finishes, [npc.she] experimentally twitches [npc.her] new wolf-like ears back and forth."
								+ "</br>"
								+ "[npc.Name] now has [style.boldWolfMorph(anthropomorphic, wolf-like ears)]."
							+ "</p>");
				}
				break;
			case CAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" They quickly grow into small, upright points, and shift to sit higher up on your head than a normal pair of human ears would."
								+ " A layer of [pc.earFullDescriptionColour] grows to cover them, and as the transformation finishes, you discover that you can easily twitch your new cat-like ears back and forth."
								+ "</br>"
								+ "You now have [style.boldCatMorph(anthropomorphic, cat-like ears)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" They quickly grow into small, upright points, and shift to sit higher up on [npc.her] head than a normal pair of human ears would."
								+ " A layer of [npc.earFullDescriptionColour] grows to cover them, and as the transformation finishes, [npc.she] experimentally twitches [npc.her] new cat-like ears back and forth."
								+ "</br>"
								+ "[npc.Name] now has [style.boldCatMorph(anthropomorphic, cat-like ears)]."
							+ "</p>");
				}
				break;
			case COW_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" They quickly take on a distinctly bovine shape, by growing out and narrowing down into long, slightly-folded ovals."
								+ " A layer of [pc.earFullDescriptionColour] grows to cover them, and as the transformation finishes, you discover that you're able to twitch your new cow-like ears back and forth."
								+ "</br>"
								+ "You now have [style.boldCowMorph(anthropomorphic, cow-like ears)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" They quickly take on a distinctly bovine shape, by growing out and narrowing down into long, slightly-folded ovals."
								+ " A layer of [npc.earFullDescriptionColour] grows to cover them, and as the transformation finishes, [npc.she] experimentally twitches [npc.her] new cow-like ears back and forth."
								+ "</br>"
								+ "[npc.Name] now has [style.boldCowMorph(anthropomorphic, cow-like ears)]."
							+ "</p>");
				}
				break;
			case SQUIRREL_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" They quickly grow into small, upright ovals, and shift to sit higher up on your head than a normal pair of human ears would."
								+ " A layer of [pc.earFullDescriptionColour] grows to cover them, and as the transformation finishes, you discover that you can easily twitch your new squirrel-like ears back and forth."
								+ "</br>"
								+ "You now have [style.boldSquirrelMorph(anthropomorphic, squirrel-like ears)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" They quickly grow into small, upright ovals, and shift to sit higher up on [npc.her] head than a normal pair of human ears would."
								+ " A layer of [npc.earFullDescriptionColour] grows to cover them, and as the transformation finishes, [npc.she] experimentally twitches [npc.her] new squirrel-like ears back and forth."
								+ "</br>"
								+ "[npc.Name] now has [style.boldSquirrelMorph(anthropomorphic, squirrel-like ears)]."
							+ "</p>");
				}
				break;
			case HORSE_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" They quickly grow into sturdy little upright points, and shift to sit higher up on your head than a normal pair of human ears would."
								+ " A layer of [pc.earFullDescriptionColour] grows to cover them, and as the transformation finishes, you discover that you can easily twitch your new horse-like ears back and forth."
								+ "</br>"
								+ "You now have [style.boldHorseMorph(anthropomorphic, horse-like ears)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" They quickly grow into sturdy little upright points, and shift to sit higher up on [npc.her] head than a normal pair of human ears would."
								+ " A layer of [npc.earFullDescriptionColour] grows to cover them, and as the transformation finishes, [npc.she] experimentally twitches [npc.her] new horse-like ears back and forth."
								+ "</br>"
								+ "[npc.Name] now has [style.boldHorseMorph(anthropomorphic, horse-like ears)]."
							+ "</p>");
				}
				break;
			case HARPY:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" They quickly shrink down into little nubs as most of the external cartilage shifts down into the sides of your head."
								+ " A layer of [pc.earFullDescriptionColour] grow to cover your now-fully-internal ears, and as the transformation finishes, you discover that you've now got a pair of beautifully-feathered harpy ears."
								+ "</br>"
								+ "You now have [style.boldHarpy(internal, feather-covered harpy ears)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" They quickly shrink down into little nubs as most of the external cartilage shifts down into the sides of [npc.her] head."
								+ " A layer of [npc.earFullDescriptionColour] grow to cover [npc.her] now-fully-internal ears, and as the transformation finishes, [npc.she]'s left with a pair of beautifully-feathered harpy ears."
								+ "</br>"
								+ "[npc.Name] now has [style.boldHarpy(internal, feather-covered harpy ears)]."
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
					+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public boolean isPierced() {
		return pierced;
	}
	
	public String setPierced(GameCharacter owner, boolean pierced) {
		if(this.pierced == pierced) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		this.pierced = pierced;
		
		if(pierced) {
			if(owner.isPlayer()) {
				return "<p>Your [pc.ears] are now [style.boldGrow(pierced)]!</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.Name]'s [npc.ears] are now [style.boldGrow(pierced)]!</p>");
			}
		} else {
			if(owner.isPlayer()) {
				return "<p>Your [pc.ears] are [style.boldShrink(no longer pierced)]!</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.Name]'s [npc.ears] are [style.boldShrink(no longer pierced)]!</p>");
			}
		}
		
	}

}
