package com.base.game.character.body;

import java.io.Serializable;

import com.base.game.character.GameCharacter;
import com.base.game.character.body.types.TailType;
import com.base.game.dialogue.utils.UtilText;
import com.base.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public class Tail implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	private TailType type;
	private int tailCount;

	public Tail(TailType type) {
		this.type = type;
		tailCount = 1;
	}

	@Override
	public TailType getType() {
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

	public String setType(GameCharacter owner, TailType type) {
		if (type == getType()) {
			if(type == TailType.NONE) {
				if(owner.isPlayer()) {
					return "<p style='text-align:center;'>[style.colourDisabled(You already lack a tail, so nothing happens...)]</p>";
				} else {
					return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already lacks a tail, so nothing happens...)]</p>");
				}
			} else {
				if(owner.isPlayer()) {
					return "<p style='text-align:center;'>[style.colourDisabled(You already have the [pc.tail] of [pc.a_tailRace], so nothing happens...)]</p>";
				} else {
					return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already has the [npc.tail] of [npc.a_tailRace], so nothing happens...)]</p>");
				}
			}
		}
		
		UtilText.transformationContentSB.setLength(0);
		
		if (owner.isPlayer()) {
			if(this.type == TailType.NONE) {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "You feel a strange bubbling sensation building up in your lower back, and after just a moment you feel something pushing out from under your [pc.skin].");
			} else {
				UtilText.transformationContentSB.append(
						"<p>"
							+ (owner.getTailCount()==1
								?"You feel your [pc.tail] growing hot and itchy, and after just a moment it starts to transform."
								:"You feel your [pc.tails] growing hot and itchy, and after just a moment they start to transform"));
			}
			
		} else {
			if(this.type == TailType.NONE) {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "[npc.Name] rubs at [npc.her] lower back as [npc.she] feels it growing hot and sensitive, and as [npc.she] does so, something starts pushing out from under [npc.her] [npc.skin].");
			} else {
				UtilText.transformationContentSB.append(
						"<p>"
							+ (owner.getTailCount()==1
								?"[npc.Name] feels [npc.her] [npc.tail] growing hot and itchy, and after just a moment it starts to transform."
								:"[npc.Name] feels [npc.her] [npc.tails] growing hot and itchy, and after just a moment they starts to transform"));
			}
		}
		
		switch (type) {
			case NONE:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							(owner.getTailCount()==1
								?" You gasp as you feel your [pc.tail] shrinking down and disappearing into your lower back."
								:" You gasp as you feel your [pc.tails] shrinking down and disappearing into your lower back")
							+ "</br>"
							+ "You now have [style.boldTfGeneric(no tail)]");
				} else {
					UtilText.transformationContentSB.append(
							(owner.getTailCount()==1
								?" [npc.She] gasps as [npc.she] feels [npc.her] [npc.tail] shrinking down and disappearing into [npc.her] lower back."
								:" [npc.She] gasps as [npc.she] feels [npc.her] [npc.tails] shrinking down and disappearing into [npc.her] lower back.")
							+ "</br>"
							+ "[npc.Name] now has [style.boldTfGeneric(no tail)]");
				}
				break;
			case CAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							(owner.getTailCount()==1
								?" A furry, cat-like tail sprouts from just above your ass, rapidly growing in size until it's almost as long as one of your legs."
									+ " You quickly realise that you have a decent amount of control over it, and you can twist it almost anywhere you please."
									+ "</br>"
									+ "You now have a [style.boldCatMorph(cat-like tail)]"
								:" Furry, cat-like tails sprout from just above your ass, rapidly growing in size until they're each almost as long as one of your legs."
									+ " You quickly realise that you have a decent amount of control over them, and you can twist them almost anywhere you please."
									+ "</br>"
									+ "You now have [pc.tailCount] [style.boldCatMorph(cat-like tails)]")
							);
				} else {
					UtilText.transformationContentSB.append(
							(owner.getTailCount()==1
								?" A furry, cat-like tail sprouts from just above [npc.her] ass, rapidly growing in size until it's almost as long as one of [npc.her] legs."
									+ " [npc.She] quickly realises that [npc.she] has a decent amount of control over it, and can twist it almost anywhere [npc.she] pleases."
									+ "</br>"
									+ "[npc.Name] now has a [style.boldCatMorph(cat-like tail)]"
								:" Furry, cat-like tails sprout from just above [npc.her] ass, rapidly growing in size until they're each almost as long as one of [npc.her] legs."
									+ " [npc.She] quickly realises that [npc.she] has a decent amount of control over them, and can twist them almost anywhere [npc.she] pleases."
									+ "</br>"
									+ "[npc.Name] now has [npc.tailCount] [style.boldCatMorph(cat-like tails)]")
							);
				}
				break;
			case DEMON_COMMON:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							(owner.getTailCount()==1
								?" A demonic, spaded tail sprouts from just above your ass, rapidly growing in size until it's slightly longer than one of your legs."
									+ " You quickly realise that you have complete control over where it goes, allowing you to use it like a third limb."
									+ "</br>"
									+ "You now have a [style.boldDemon(demonic tail)]"
								:" Demonic, spaded tail sprout from just above your ass, rapidly growing in size until they're each slightly longer than one of your legs."
									+ " You quickly realise that you have complete control over where they go, allowing you to use them like extra limbs."
									+ "</br>"
									+ "You now have [pc.tailCount] [style.boldDemon(demonic tails)]")
							);
				} else {
					UtilText.transformationContentSB.append(
							(owner.getTailCount()==1
								?" A demonic, spaded tail sprouts from just above [npc.her] ass, rapidly growing in size until it's slightly longer than one of [npc.her] legs."
									+ " [npc.She] quickly realises that [npc.she] has complete control over where it goes, allowing [npc.herHim] to use it like a third limb."
									+ "</br>"
									+ "[npc.Name] now has a [style.boldDemon(demonic tail)]"
								:" Demonic, spaded tail sprout from just above [npc.her] ass, rapidly growing in size until they're slightly longer than one of [npc.her] legs."
									+ " [npc.She] quickly realises that [npc.she] has complete control over where they go, allowing [npc.herHim] to use them like extra limbs."
									+ "</br>"
									+ "[npc.Name] now has [npc.tailCount] [style.boldDemon(demonic tails)]")
							);
				}
				break;
			case DOG_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							(owner.getTailCount()==1
								?" A furry, dog-like tail sprouts from just above your ass, rapidly growing in size until it's about half the length of one of your legs."
									+ " You quickly realise that you have little control over it, and it wags with a mind of its own whenever you get excited."
									+ "</br>"
									+ "You now have a [style.boldDogMorph(dog-like tail)]"
								:" Furry, dog-like tails sprout from just above your ass, rapidly growing in size until they're each about half the length of one of your legs."
									+ " You quickly realise that you have little control over them, and they wag with a mind of their own whenever you get excited."
									+ "</br>"
									+ "You now have [pc.tailCount] [style.boldDogMorph(dog-like tails)]")
							);
				} else {
					UtilText.transformationContentSB.append(
							(owner.getTailCount()==1
								?" A furry, dog-like tail sprouts from just above [npc.her] ass, rapidly growing in size until it's about half the length of one of [npc.her] legs."
									+ " [npc.She] quickly realises that [npc.she] has little control over it, and it wags with a mind of its own whenever [npc.she] gets excited."
									+ "</br>"
									+ "[npc.Name] now has a [style.boldDogMorph(dog-like tail)]"
								:" Furry, dog-like tails sprout from just above [npc.her] ass, rapidly growing in size until they're each about half the length of one of [npc.her] legs."
									+ " [npc.She] quickly realises that [npc.she] has little control over them, and they wag with a mind of their own whenever [npc.she] gets excited."
									+ "</br>"
									+ "[npc.Name] now has [npc.tailCount] [style.boldDogMorph(dog-like tails)]")
							);
				}
				break;
			case HARPY:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							(owner.getTailCount()==1
								?" A pretty plume of tail feathers sprouts from just above your ass, with each feather quickly growing to be about one-third the length of one of your legs."
									+ " You discover that you can quickly raise and lower your new bird-like tail, which helps you to keep your balance."
									+ "</br>"
									+ "You now have a [style.boldHarpy(harpy's bird-like tail)]"
								:" Pretty plumes of tail feathers sprout from just above your ass, with each feather quickly growing to be about one-third the length of one of your legs."
									+ " You discover that you can quickly raise and lower your new bird-like tails, which helps you to keep your balance."
									+ "</br>"
									+ "You now have [pc.tailCount] [style.boldHarpy(harpy's bird-like tails)]")
							);
				} else {
					UtilText.transformationContentSB.append(
							(owner.getTailCount()==1
								?" A pretty plume of tail feathers sprouts from just above [npc.her] ass, with each feather quickly growing to be about one-third the length of one of [npc.her] legs."
									+ " [npc.She] discovers that [npc.she] can quickly raise and lower [npc.her] new bird-like tail, which helps [npc.herHim] to keep [npc.her] balance."
									+ "</br>"
									+ "[npc.Name] now has a [style.boldHarpy(harpy's bird-like tail)]"
								:" Pretty plumes of tail feathers sprout from just above [npc.her] ass, with each feather quickly growing to be about one-third the length of one of [npc.her] legs."
									+ " [npc.She] discovers that [npc.she] can quickly raise and lower [npc.her] new bird-like tails, which helps [npc.herHim] to keep [npc.her] balance."
									+ "</br>"
									+ "[npc.Name] now has [npc.tailCount] [style.boldHarpy(harpy's bird-like tails)]")
							);
				}
				break;
			case HORSE_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							(owner.getTailCount()==1
								?" A horse-like tail sprouts from just above your ass, rapidly growing in length until it hangs to just over half-way down your legs."
									+ " You quickly discover that your control over it is limited to swishing it from side to side."
									+ "</br>"
									+ "You now have a [style.boldHorseMorph(horse-like tail)]"
								:" Horse-like tails sprout from just above your ass, rapidly growing in length until they hang to just over half-way down your legs."
									+ " You quickly discover that your control over them is limited to swishing them from side to side."
									+ "</br>"
									+ "You now have [pc.tailCount] [style.boldHorseMorph(horse-like tails)]")
							);
				} else {
					UtilText.transformationContentSB.append(
							(owner.getTailCount()==1
								?" A horse-like tail sprouts from just above [npc.her] ass, rapidly growing in length until it hangs to just over half-way down [npc.her] legs."
									+ " [npc.She] quickly discovers that [npc.her] control over it is limited to swishing it from side to side."
									+ "</br>"
									+ "[npc.Name] now has a [style.boldHorseMorph(horse-like tail)]"
								:" Horse-like tails sprout from just above [npc.her] ass, rapidly growing in length until they hang to just over half-way down [npc.her] legs."
									+ " [npc.She] quickly discovers that [npc.her] control over them is limited to swishing them from side to side."
									+ "</br>"
									+ "[npc.Name] now has [npc.tailCount] [style.boldHorseMorph(horse-like tails)]")
							);
				}
				break;
			case LYCAN:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							(owner.getTailCount()==1
								?" A furry, wolf-like tail sprouts from just above your ass, rapidly growing in size until it's about half the length of one of your legs."
									+ " You quickly realise that you have limited control over it, and it takes a lot of effort to stop it from betraying your emotions."
									+ "</br>"
									+ "You now have a [style.boldWolfMorph(wolf-like tail)]"
								:" Furry, wolf-like tails sprout from just above your ass, rapidly growing in size until they're each about half the length of one of your legs."
									+ " You quickly realise that you have limited control over them, and it takes a lot of effort to stop them from betraying your emotions."
									+ "</br>"
									+ "You now have [pc.tailCount] [style.boldWolfMorph(wolf-like tails)]")
							);
				} else {
					UtilText.transformationContentSB.append(
							(owner.getTailCount()==1
								?" A furry, wolf-like tail sprouts from just above [npc.her] ass, rapidly growing in size until it's about half the length of one of [npc.her] legs."
									+ " [npc.She] quickly realises that [npc.she] has limited control over it, and it takes a lot of effort to stop it from betraying [npc.her] emotions."
									+ "</br>"
									+ "[npc.Name] now has a [style.boldWolfMorph(wolf-like tail)]"
								:" Furry, wolf-like tails sprout from just above [npc.her] ass, rapidly growing in size until they're each about half the length of one of [npc.her] legs."
									+ " [npc.She] quickly realises that [npc.she] has limited control over them, and it takes a lot of effort to stop them from betraying [npc.her] emotions."
									+ "</br>"
									+ "[npc.Name] now has [npc.tailCount] [style.boldWolfMorph(wolf-like tails)]")
							);
				}
				break;
			case SQUIRREL_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							(owner.getTailCount()==1
								?" A furry, squirrel-like tail sprouts from just above your ass, rapidly growing in size until it's almost as long as your body."
									+ " You quickly realise that you have a reasonable amount of control over it, and can use it to help balance you out while moving quickly."
									+ "</br>"
									+ "You now have a [style.boldSquirrelMorph(squirrel-like tail)]"
								:" Furry, squirrel-like tails sprout from just above your ass, rapidly growing in size until they're each almost as long as your body."
									+ " You quickly realise that you have a reasonable amount of control over them, and can use them to help balance you out while moving quickly."
									+ "</br>"
									+ "You now have [pc.tailCount] [style.boldSquirrelMorph(squirrel-like tails)]")
							);
				} else {
					UtilText.transformationContentSB.append(
							(owner.getTailCount()==1
								?" A furry, squirrel-like tail sprouts from just above [npc.her] ass, rapidly growing in size until it's almost as long as [npc.her] body."
									+ " [npc.She] quickly realises that [npc.she] has a reasonable amount of control over it, and can use it to help balance [npc.herHim] out while moving quickly."
									+ "</br>"
									+ "[npc.Name] now has a [style.boldSquirrelMorph(squirrel-like tail)]"
								:" Furry, squirrel-like tails sprout from just above [npc.her] ass, rapidly growing in size until they're each almost as long as [npc.her] body."
									+ " [npc.She] quickly realises that [npc.she] has a reasonable amount of control over them, and can use them to help balance [npc.herHim] out while moving quickly."
									+ "</br>"
									+ "[npc.Name] now has [npc.tailCount] [style.boldSquirrelMorph(squirrel-like tails)]")
							);
				}
				break;
		}
		
		this.type = type;
		
		if(type == TailType.NONE) {
			UtilText.transformationContentSB.append(".</p>");
		} else {
			if (owner.isPlayer()) {
				UtilText.transformationContentSB.append(", covered in [pc.tailFullDescription(true)].</p>");
			} else {
				UtilText.transformationContentSB.append(", covered in [npc.tailFullDescription(true)].</p>");
			}
		}
		
		return UtilText.parse(owner, UtilText.transformationContentSB.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public int getTailCount() {
		return tailCount;
	}

	public String setTailCount(GameCharacter owner, int tailCount) {
		if(tailCount<=0) {
			tailCount = 1;
		} else if (tailCount>9) {
			tailCount=9;
		}
		
		if(owner.getTailCount() == tailCount) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		boolean removingTails = owner.getTailCount() > tailCount;
		this.tailCount = tailCount;
		
		if (owner.getTailType() == TailType.NONE) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		if(removingTails) {
			if(owner.isPlayer()) {
				return
						"<p>"
							+ "A tingling feeling spreads over your [pc.tails], before moving down and concentrating into your lower back."
							+ " You can't help but let out a little cry as you feel some of them [style.boldShrink(shrinking away)] and disappearing back down into your [pc.skin].</br>"
							+ "After a few moments, you're left with [style.boldTfGeneric("
									+(tailCount==1
										?"a single [pc.tail]"
										:Util.intToString(tailCount)+" [pc.tails]")
							+ ")]."
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "A tingling feeling spreads over [npc.name]'s [npc.tails], before moving down and concentrating into [npc.her] lower back."
							+ " [npc.She] can't help but let out a little cry as [npc.she] feels some of them [style.boldShrink(shrinking away)] and disappearing back down into [npc.her] [npc.skin].</br>"
							+ "After a few moments, [npc.she]'s left with [style.boldTfGeneric("
									+(tailCount==1
										?"a single [npc.tail]"
										:Util.intToString(tailCount)+" [npc.tails]")
							+ ")]."
						+ "</p>");
			}
			
		} else {
			if(owner.isPlayer()) {
				return
						"<p>"
							+ "A tingling feeling spreads over your [pc.tails], before moving down and concentrating into your lower back."
							+ " You can't help but let out a little cry as you feel new [pc.tails] [style.boldGrow(pushing up)] and growing out of your [pc.skin].</br>"
							+ "After a few moments, you're left with [style.boldTfGeneric("
									+(tailCount==1
										?"a single [pc.tail]"
										:Util.intToString(tailCount)+" [pc.tails]")
							+ ")]."
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "A tingling feeling spreads over [npc.name]'s [npc.tails], before moving down and concentrating into [npc.her] lower back."
							+ " [npc.She] can't help but let out a little cry as [npc.she] feels new [npc.tails] [style.boldGrow(pushing up)] and growing out of [npc.her] [npc.skin].</br>"
							+ "After a few moments, [npc.she]'s left with [style.boldTfGeneric("
									+(tailCount==1
										?"a single [npc.tail]"
										:Util.intToString(tailCount)+" [npc.tails]")
							+ ")]."
						+ "</p>");
			}
		}
	}
}
