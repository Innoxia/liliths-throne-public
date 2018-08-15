package com.lilithsthrone.game.character.body;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.FootStructure;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.2.10
 * @author Innoxia
 */
public class Leg implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	protected LegType type;
	protected FootStructure footStructure;

	public Leg(LegType type) {
		this.type = type;
		this.footStructure = type.getDefaultFootStructure();
	}

	@Override
	public LegType getType() {
		return type;
	}

	public FootStructure getFootStructure() {
		return footStructure;
	}

	public String setFootStructure(FootStructure footStructure) {
		this.footStructure = footStructure;
		//TODO
		return "";
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
		List<String> descriptorList = new ArrayList<>();
		
		descriptorList.add(type.getDescriptor(gc));
		descriptorList.add(type.getDescriptor(gc));
		descriptorList.add(Util.randomItemFrom(gc.getBodyShape().getLimbDescriptors()));
		
		return UtilText.returnStringAtRandom(descriptorList.toArray(new String[]{}));
	}

	public String setType(GameCharacter owner, LegType type) {
		if(owner==null) {
			this.type = type;
			return "";
		}
		
		if (type == getType()) {
			if (owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(You already have the [pc.legs] of [pc.a_legRace], so nothing happens...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already has the [npc.legs] of [npc.a_legRace], so nothing happens...)]</p>");
			}
			
		} else {
			UtilText.transformationContentSB.setLength(0);
			
			if (owner.isPlayer()) {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "Your [pc.legs] start to wobble and feel weak, and as you look down to see what's wrong, they start to transform.");
			} else {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "[npc.Name] almost loses [npc.her] balance as [npc.her] [npc.legs] start to transform.");
			}
		}

		// Parse existing content before transformation:
		String s = UtilText.parse(owner, UtilText.transformationContentSB.toString());
		UtilText.transformationContentSB.setLength(0);
		UtilText.transformationContentSB.append(s);
		this.type = type;
		this.footStructure = type.getDefaultFootStructure();
		
		switch (type) {
			case HUMAN:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" They rapidly shift into normal-looking human legs, complete with human feet.<br/>"
								+ "You now have [style.boldHuman(human legs and feet)], which are covered in [pc.legFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" They rapidly shift into normal-looking human legs, complete with human feet.<br/>"
								+ "[npc.She] now has [style.boldHuman(human legs and feet)], which are covered in [npc.legFullDescription]."
							+ "</p>");
				}
				break;
			case DEMON_COMMON:
				if (!owner.isShortStature()) {
					UtilText.transformationContentSB.append(
							" They quickly shift into a pair of smooth, slender legs, and [npc.she] [npc.verb(let)] out a gasp as a layer of flawless, demonic skin rapidly grows to cover them."
							+ " As they finish transforming, [npc.she] almost [npc.verb(lose)] [npc.her] balance as the bones in [npc.her] feet start to shift and rearrange themselves."
							+ " After a moment, they've transformed into slender human-like feet, ending in soft, delicate toes.<br/>"
							+ "[npc.Name] now [npc.has] [style.boldDemon(demonic legs and feet)], which are covered in [npc.legFullDescription]."
						+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
							" They quickly shift into a pair of smooth, slender legs, and [npc.she] [npc.verb(let)] out a gasp as a layer of flawless, impish skin rapidly grows to cover them."
							+ " As they finish transforming, [npc.she] almost [npc.verb(lose)] [npc.her] balance as the bones in [npc.her] feet start to shift and rearrange themselves."
							+ " After a moment, they've transformed into slender human-like feet, ending in soft, delicate toes.<br/>"
							+ "[npc.Name] now [npc.has] [style.boldImp(impish legs and feet)], which are covered in [npc.legFullDescription]."
						+ "</p>");
				}
				break;
			case DEMON_HOOFED:
				if (!owner.isShortStature()) {
					UtilText.transformationContentSB.append(
								" They quickly shift into a pair of smooth, slender legs, and [npc.name] [npc.verb(let)] out a gasp as a layer of flawless, demonic skin rapidly grows to cover them."
								+ " As [npc.her] new skin spreads down to the ends of [npc.her] toes, they suddenly push together, and [npc.she] [npc.verb(let)] out a cry as a thick, hoof-like nail grows in their place,"
									+ " quickly transforming to turn [npc.her] feet into hard, demonic hoofs."
								+ " As the transformation ends, [npc.she] [npc.verb(see)] that [npc.her] new skin smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
								+ "[npc.NameIsFull] left with [style.boldDemon(demonic legs and hoofed feet)], which are covered in [npc.legFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
							" They quickly shift into a pair of smooth, slender legs, and [npc.name] [npc.verb(let)] out a gasp as a layer of flawless, demonic skin rapidly grows to cover them."
							+ " As [npc.her] new skin spreads down to the ends of [npc.her] toes, they suddenly push together, and [npc.she] [npc.verb(let)] out a cry as a thick, hoof-like nail grows in their place,"
								+ " quickly transforming to turn [npc.her] feet into hard, demonic hoofs."
							+ " As the transformation ends, [npc.she] [npc.verb(see)] that [npc.her] new skin smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
							+ "[npc.NameIsFull] left with [style.boldImp(impish legs and hoofed feet)], which are covered in [npc.legFullDescription]."
						+ "</p>");
				}
				break;
			case DOG_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" A layer of dog-like fur quickly grows over your legs as they shift into a new form."
								+ " As your new fur spreads down to the ends of your toes, your toenails thicken into little blunt claws, and leathery pads grow to cover your soles, leaving you with paw-like feet."
								+ " As the transformation ends, you see that your new fur smoothly transitions into the [pc.skin] covering the rest of your body at your upper-thigh.<br/>"
								+ "You're left with anthropomorphic, [style.boldDogMorph(dog-like legs and feet)], which are covered in [pc.legFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" A layer of dog-like fur quickly grows over [npc.her] legs as they shift into a new form."
								+ " As [npc.her] new fur spreads down to the ends of [npc.her] toes, [npc.her] toenails thicken into little blunt claws, and leathery pads grow to cover [npc.her] soles, leaving [npc.herHim] with paw-like feet."
								+ " As the transformation ends, [npc.she] sees that [npc.her] new fur smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
								+ "[npc.Name] now has anthropomorphic, [style.boldDogMorph(dog-like legs and feet)], which are covered in [npc.legFullDescription]."
							+ "</p>");
				}
				break;
			case FOX_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" A layer of fox-like fur quickly grows over your legs as they shift into a new form."
								+ " As your new fur spreads down to the ends of your toes, your toenails thicken into sharp claws, and little pads grow to cover your soles, leaving you with paw-like feet."
								+ " As the transformation ends, you see that your new fur smoothly transitions into the [pc.skin] covering the rest of your body at your upper-thigh.</br>"
								+ "You're left with anthropomorphic, [style.boldFoxMorph(fox-like legs and feet)], which are covered in [pc.legFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" A layer of fox-like fur quickly grows over [npc.her] legs as they shift into a new form."
								+ " As [npc.her] new fur spreads down to the ends of [npc.her] toes, [npc.her] toenails thicken into sharp claws, and little pads grow to cover [npc.her] soles, leaving [npc.herHim] with paw-like feet."
								+ " As the transformation ends, [npc.she] sees that [npc.her] new fur smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.</br>"
								+ "[npc.Name] now has anthropomorphic, [style.boldFoxMorph(fox-like legs and feet)], which are covered in [npc.legFullDescription]."
							+ "</p>");
				}
				break;
			case LYCAN:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" A layer of wolf-like fur quickly grows over your legs as they shift into a new form."
								+ " As your new fur spreads down to the ends of your toes, your toenails thicken into sharp claws, and tough leathery pads grow to cover your soles, leaving you with paw-like feet."
								+ " As the transformation ends, you see that your new fur smoothly transitions into the [pc.skin] covering the rest of your body at your upper-thigh.<br/>"
								+ "You're left with anthropomorphic, [style.boldWolfMorph(wolf-like legs and feet)], which are covered in [pc.legFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" A layer of wolf-like fur quickly grows over [npc.her] legs as they shift into a new form."
								+ " As [npc.her] new fur spreads down to the ends of [npc.her] toes, [npc.her] toenails thicken into sharp claws, and tough leathery pads grow to cover [npc.her] soles, leaving [npc.herHim] with paw-like feet."
								+ " As the transformation ends, [npc.she] sees that [npc.her] new fur smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
								+ "[npc.Name] now has anthropomorphic, [style.boldWolfMorph(wolf-like legs and feet)], which are covered in [npc.legFullDescription]."
							+ "</p>");
				}
				break;
			case CAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" A layer of cat-like fur quickly grows over your legs as they shift into a new form."
								+ " As your new fur spreads down to the ends of your toes, your toenails thicken into sharp, retractable claws, and little pink pads grow to cover your soles, leaving you with paw-like feet."
								+ " As the transformation ends, you see that your new fur smoothly transitions into the [pc.skin] covering the rest of your body at your upper-thigh.<br/>"
								+ "You're left with anthropomorphic, [style.boldCatMorph(cat-like legs and feet)], which are covered in [pc.legFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" A layer of cat-like fur quickly grows over [npc.her] legs as they shift into a new form."
								+ " As [npc.her] new fur spreads down to the ends of [npc.her] toes, [npc.her] toenails thicken into sharp, retractable claws, and little pink pads grow to cover [npc.her] soles, leaving [npc.herHim] with paw-like feet."
								+ " As the transformation ends, [npc.she] sees that [npc.her] new fur smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
								+ "[npc.Name] now has anthropomorphic, [style.boldCatMorph(cat-like legs and feet)], which are covered in [npc.legFullDescription]."
							+ "</p>");
				}
				break;
			case ALLIGATOR_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" A layer of alligator-like scales quickly grows over your legs as they shift into a new form."
								+ " As your new scales spread down to the ends of your toes, your toenails thicken into sharp claws, and little scales grow to cover your soles, leaving you with alligator-like feet."
								+ " As the transformation ends, you see that your new scales smoothly transition into the [pc.skin] covering the rest of your body at your upper-thigh.<br/>"
								+ "You're left with anthropomorphic, [style.boldGatorMorph(alligator-like legs and feet)], which are covered in [pc.legFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" A layer of alligator-like scales quickly grows over [npc.her] legs as they shift into a new form."
								+ " As [npc.her] new scales spread down to the ends of [npc.her] toes, [npc.her] toenails thicken into sharp claws, and little scales grow to cover [npc.her] soles, leaving [npc.herHim] with alligator-like feet."
								+ " As the transformation ends, [npc.she] sees that [npc.her] new scales smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
								+ "[npc.Name] now has anthropomorphic, [style.boldGatorMorph(alligator-like legs and feet)], which are covered in [npc.legFullDescription]."
							+ "</p>");
				}
				break;
			case HORSE_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" A layer of short, horse-like hair quickly grows over your legs as they shift into a new form."
								+ " As your new fur spreads down to the ends of your toes, they suddenly push together, and you let out a cry as a thick, hoof-like nail grows in their place, quickly transforming to turn your feet into horse-like hoofs."
								+ " As the transformation ends, you see that your new fur smoothly transitions into the [pc.skin] covering the rest of your body at your upper-thigh.<br/>"
								+ "You're left with anthropomorphic, [style.boldHorseMorph(horse-like legs and hoofed feet)], which are covered in [pc.legFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" A layer of short, horse-like hair quickly grows over [npc.her] legs as they shift into a new form."
								+ " As [npc.her] new fur spreads down to the ends of [npc.her] toes, they suddenly push together, and [npc.she] lets out a cry as a thick, hoof-like nail grows in their place,"
									+ " quickly transforming to turn [npc.her] feet into horse-like hoofs."
								+ " As the transformation ends, [npc.she] sees that [npc.her] new fur smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
								+ "[npc.Name] now has anthropomorphic, [style.boldHorseMorph(horse-like legs and hoofed feet)], which are covered in [npc.legFullDescription]."
							+ "</p>");
				}
				break;
			case REINDEER_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" A layer of short, reindeer-like hair quickly grows over your legs as they shift into a new form."
								+ " As your new fur spreads down to the ends of your toes, they suddenly push together, and you let out a cry as a crescent-shaped, cloven hoof grows in their place,"
									+ " quickly transforming your feet into reindeer-like hoofs."
								+ " As the transformation ends, you see that your new fur smoothly transitions into the [pc.skin] covering the rest of your body at your upper-thigh.<br/>"
								+ "You're left with anthropomorphic, [style.boldReindeerMorph(reindeer-like legs and hoofed feet)], which are covered in [pc.legFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" A layer of short, reindeer-like hair quickly grows over [npc.her] legs as they shift into a new form."
								+ " As [npc.her] new fur spreads down to the ends of [npc.her] toes, they suddenly push together, and [npc.she] lets out a cry as a crescent-shaped, cloven hoof grows in their place,"
									+ " quickly transforming [npc.her] feet into reindeer-like hoofs."
								+ " As the transformation ends, [npc.she] sees that [npc.her] new fur smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
								+ "[npc.Name] now has anthropomorphic, [style.boldReindeerMorph(reindeer-like legs and hoofed feet)], which are covered in [npc.legFullDescription]."
							+ "</p>");
				}
				break;
			case COW_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" A layer of short, cow-like hair quickly grows over your legs as they shift into a new form."
								+ " As your new fur spreads down to the ends of your toes, they suddenly push together, and you let out a cry as a thick, hoof-like nail grows in their place, quickly transforming to turn your feet into cow-like hoofs."
								+ " As the transformation ends, you see that your new fur smoothly transitions into the [pc.skin] covering the rest of your body at your upper-thigh.<br/>"
								+ "You're left with anthropomorphic, [style.boldCowMorph(cow-like legs and hoofed feet)], which are covered in [pc.legFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" A layer of short, cow-like hair quickly grows over [npc.her] legs as they shift into a new form."
								+ " As [npc.her] new fur spreads down to the ends of [npc.her] toes, they suddenly push together, and [npc.she] lets out a cry as a thick, hoof-like nail grows in their place,"
									+ " quickly transforming to turn [npc.her] feet into cow-like hoofs."
								+ " As the transformation ends, [npc.she] sees that [npc.her] new fur smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
								+ "[npc.Name] now has anthropomorphic, [style.boldCowMorph(cow-like legs and hoofed feet)], which are covered in [npc.legFullDescription]."
							+ "</p>");
				}
				break;
			case SQUIRREL_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" A layer of squirrel-like fur quickly grows over your legs as they shift into a new form."
								+ " As your new fur spreads down to the ends of your toes, your toenails thicken into sharp claws, and little pink pads grow to cover your soles, leaving you with paw-like feet."
								+ " As the transformation ends, you see that your new fur smoothly transitions into the [pc.skin] covering the rest of your body at your upper-thigh.<br/>"
								+ "You're left with anthropomorphic, [style.boldSquirrelMorph(squirrel-like legs and feet)], which are covered in [pc.legFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" A layer of squirrel-like fur quickly grows over [npc.her] legs as they shift into a new form."
								+ " As [npc.her] new fur spreads down to the ends of [npc.her] toes, [npc.her] toenails thicken into sharp claws, and little pink pads grow to cover [npc.her] soles, leaving [npc.herHim] with paw-like feet."
								+ " As the transformation ends, [npc.she] sees that [npc.her] new fur smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
								+ "[npc.Name] now has anthropomorphic, [style.boldSquirrelMorph(squirrel-like legs and feet)], which are covered in [npc.legFullDescription]."
							+ "</p>");
				}
				break;
			case RAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" A layer of rat-like fur quickly grows over your legs as they shift into a new form."
								+ " As your new fur spreads down to the ends of your toes, your toenails thicken into sharp claws, and little pink pads grow to cover your soles, leaving you with paw-like feet."
								+ " As the transformation ends, you see that your new fur smoothly transitions into the [pc.skin] covering the rest of your body at your upper-thigh.<br/>"
								+ "You're left with anthropomorphic, [style.boldRatMorph(rat-like legs and feet)], which are covered in [pc.legFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" A layer of rat-like fur quickly grows over [npc.her] legs as they shift into a new form."
								+ " As [npc.her] new fur spreads down to the ends of [npc.her] toes, [npc.her] toenails thicken into sharp claws, and little pink pads grow to cover [npc.her] soles, leaving [npc.herHim] with paw-like feet."
								+ " As the transformation ends, [npc.she] sees that [npc.her] new fur smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
								+ "[npc.Name] now has anthropomorphic, [style.boldRatMorph(rat-like legs and feet)], which are covered in [npc.legFullDescription]."
							+ "</p>");
				}
				break;
			case RABBIT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" A layer of rabbit-like fur quickly grows over your legs as they shift into a new form."
								+ " As your new fur spreads down to the ends of your toes, your toenails thicken into blunt claws, and soft little pads grow to cover your soles, leaving you with long, paw-like feet."
								+ " As the transformation ends, you see that your new fur smoothly transitions into the [pc.skin] covering the rest of your body at your upper-thigh.<br/>"
								+ "You're left with anthropomorphic, [style.boldRabbitMorph(rabbit-like legs and feet)], which are covered in [pc.legFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" A layer of rat-like fur quickly grows over [npc.her] legs as they shift into a new form."
								+ " As [npc.her] new fur spreads down to the ends of [npc.her] toes, [npc.her] toenails thicken into blunt claws, and soft little pads grow to cover [npc.her] soles, leaving [npc.herHim] with long, paw-like feet."
								+ " As the transformation ends, [npc.she] sees that [npc.her] new fur smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
								+ "[npc.Name] now has anthropomorphic, [style.boldRabbitMorph(rabbit-like legs and feet)], which are covered in [npc.legFullDescription]."
							+ "</p>");
				}
				break;
			case BAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" A layer of bat-like fur quickly grows over your legs as they shift into a new form."
								+ " As your new fur spreads down to the ends of your toes, your toenails thicken into sharp claws, and little pink pads grow to cover your soles, leaving you with paw-like feet."
								+ " As the transformation ends, you see that your new fur smoothly transitions into the [pc.skin] covering the rest of your body at your upper-thigh.<br/>"
								+ "You're left with anthropomorphic, [style.boldBatMorph(bat-like legs and feet)], which are covered in [pc.legFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" A layer of bat-like fur quickly grows over [npc.her] legs as they shift into a new form."
								+ " As [npc.her] new fur spreads down to the ends of [npc.her] toes, [npc.her] toenails thicken into sharp claws, and little pink pads grow to cover [npc.her] soles, leaving [npc.herHim] with paw-like feet."
								+ " As the transformation ends, [npc.she] sees that [npc.her] new fur smoothly transitions into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh.<br/>"
								+ "[npc.Name] now has anthropomorphic, [style.boldBatMorph(bat-like legs and feet)], which are covered in [npc.legFullDescription]."
							+ "</p>");
				}
				break;
			case HARPY:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" A layer of scaly, bird-like leather quickly grows over your legs as they shift into a new form."
								+ " As your new leathery skin spreads down to the ends of your toes, your feet start to undergo an extreme transformation."
								+ " Your toes combine together and re-shape themselves into three forward-facing talons, as a fourth, thumb-like talon branches out behind them."
								+ " As the transformation ends, a layer of attractive feathers grow around your upper-thigh, smoothly transitioning into the leathery skin which now covers your lower-legs.<br/>"
								+ "You're left with anthropomorphic, [style.boldHarpy(bird-like legs and talons in place of feet)], which are covered in [pc.legFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" A layer of scaly, bird-like leather quickly grows over [npc.her] legs as they shift into a new form."
								+ " As [npc.her] new leathery skin spreads down to the ends of [npc.her] toes, [npc.her] feet start to undergo an extreme transformation."
								+ " [npc.Her] toes combine together and re-shape themselves into three forward-facing talons, as a fourth, thumb-like talon branches out behind them."
								+ " As the transformation ends, a layer of attractive feathers grow around [npc.her] upper-thigh, smoothly transitioning into the leathery skin which now covers [npc.her] lower-legs.<br/>"
								+ "[npc.Name] now has anthropomorphic, [style.boldHarpy(bird-like legs and talons in place of feet)], which are covered in [npc.legFullDescription]."
							+ "</p>");
				}
				break;
			case ANGEL: //TODO
				break;
		}
		UtilText.transformationContentSB.append(
				"<p>"
					+ "The transformation has left the structure of [npc.her] [npc.feet] as [style.boldTFGeneric("+this.footStructure.getName()+")]! "+this.footStructure.getDescription()
				+ "</p>");
		
		return UtilText.parse(owner, UtilText.transformationContentSB.toString())
				+ "<p>"
				+ owner.postTransformationCalculation()
				+ "</p>";
	}

}
