package com.lilithsthrone.game.character.body;

import java.io.Serializable;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.2.2
 * @author Innoxia
 */
public class Ass implements BodyPartInterface, Serializable {

	private static final long serialVersionUID = 1L;

	protected AssType type;
	protected int assSize;
	protected int hipSize;
	
	protected Anus anus;

	public Ass(AssType type, int size, int wetness, float capacity, int elasticity, int plasticity, boolean virgin) {
		this.type = type;
		assSize = size;
		hipSize = size;
		
		anus = new Anus(type.getAnusType(), wetness, capacity, elasticity, plasticity, virgin);
	}
	
	public Anus getAnus() {
		return anus;
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

	@Override
	public AssType getType() {
		return type;
	}

	public String setType(GameCharacter owner, AssType type) {
		
		if (type == getType()) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(You already have the ass of [pc.a_assRace], so nothing happens...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already has the ass of [npc.a_assRace], so nothing happens...)]</p>");
			}
			
		} else {
			UtilText.transformationContentSB.setLength(0);
			
			if (owner.isPlayer()) {
				UtilText.transformationContentSB.append("<p>"
						+ "Your [pc.ass] starts to noticeably soften and become very sensitive, and you let out a lewd moan as the transformation moves down into your [pc.asshole] as well."
						+ " Panting and sighing, you continue letting out the occasional [pc.moan] as your [pc.ass] finishes shifting into a new form.<br/>");
			} else {
				UtilText.transformationContentSB.append(
						"<p>"
						+ "[npc.NamePos] [npc.ass] starts to soften and become very sensitive, and [npc.she] lets out [npc.a_moan+] as the transformation moves down into [npc.her] [npc.asshole] as well."
						+ " Panting and sighing, [npc.she] continues letting out the occasional [npc.moan] as [npc.her] [npc.ass] finishes shifting into a new form.<br/>");
			}
		}

		// Parse existing content before transformation:
		String s = UtilText.parse(owner, UtilText.transformationContentSB.toString());
		UtilText.transformationContentSB.setLength(0);
		UtilText.transformationContentSB.append(s);
		this.type = type;
		anus.setType(type.getAnusType());
		
		// Asshole properties are defined independently from type.
		switch (type) {
			case HUMAN:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You now have a [style.boldHuman(normal human ass)], covered in [pc.assFullDescription].<br/>"
							+ "You have also been left with [style.boldHuman(a human)] [pc.assholeFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] now has a [style.boldHuman(normal human ass)], covered in [npc.assFullDescription].<br/>"
							+ "[npc.She] has also been left with [style.boldHuman(a human)] [npc.assholeFullDescription]."
							+ "</p>");
				}
				break;
			case ANGEL:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You now have an [style.boldAngel(angelic ass)], covered in [pc.assFullDescription].<br/>"
							+ "You have also been left with [style.boldAngel(an angelic)] [pc.assholeFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] now has a [style.boldAngel(angelic ass)], covered in [npc.assFullDescription].<br/>"
							+ "[npc.She] has also been left with [style.boldAngel(an angelic)] [npc.assholeFullDescription]."
							+ "</p>");
				}
				break;
			case DEMON_COMMON:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You now have a [style.boldDemon(demonic ass)], covered in [pc.assFullDescription].<br/>"
							+ "You have also been left with [style.boldDemon(a demonic)] [pc.assholeFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] now has a [style.boldDemon(demonic ass)], covered in [npc.assFullDescription].<br/>"
							+ "[npc.She] has also been left with [style.boldDemon(a demonic)] [npc.assholeFullDescription]."
							+ "</p>");
				}
				break;
			case IMP:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You now have an [style.boldImp(impish ass)], covered in [pc.assFullDescription].<br/>"
							+ "You have also been left with [style.boldImp(an impish)] [pc.assholeFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] now has an [style.boldImp(impish ass)], covered in [npc.assFullDescription].<br/>"
							+ "[npc.She] has also been left with [style.boldImp(an impish)] [npc.assholeFullDescription]."
							+ "</p>");
				}
				break;
			case DOG_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You now have a [style.boldDogMorph(canine ass)], covered in [pc.assFullDescription].<br/>"
							+ "You have also been left with [style.boldDogMorph(a canine)] [pc.assholeFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] now has a [style.boldDogMorph(canine ass)], covered in [npc.assFullDescription].<br/>"
							+ "[npc.She] has also been left with [style.boldDogMorph(a canine)] [npc.assholeFullDescription]."
							+ "</p>");
				}
				break;
			case FOX_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You now have a [style.boldFoxMorph(vulpine ass)], covered in [pc.assFullDescription].</br>"
							+ "You have also been left with [style.boldFoxMorph(a vulpine)] [pc.assholeFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] now has a [style.boldFoxMorph(vulpine ass)], covered in [npc.assFullDescription].</br>"
							+ "[npc.She] has also been left with [style.boldFoxMorph(a vulpine)] [npc.assholeFullDescription]."
							+ "</p>");
				}
				break;
			case WOLF_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You now have a [style.boldWolfMorph(lupine ass)], covered in [pc.assFullDescription].<br/>"
							+ "You have also been left with [style.boldWolfMorph(a lupine)] [pc.assholeFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] now has a [style.boldWolfMorph(lupine ass)], covered in [npc.assFullDescription].<br/>"
							+ "[npc.She] has also been left with [style.boldWolfMorph(a lupine)] [npc.assholeFullDescription]."
							+ "</p>");
				}
				break;
			case CAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You now have a [style.boldCatMorph(feline ass)], covered in [pc.assFullDescription].<br/>"
							+ "You have also been left with [style.boldCatMorph(a feline)] [pc.assholeFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] now has a [style.boldCatMorph(feline ass)], covered in [npc.assFullDescription].<br/>"
							+ "[npc.She] has also been left with [style.boldCatMorph(a feline)] [npc.assholeFullDescription]."
							+ "</p>");
				}
				break;
			case COW_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You now have a [style.boldCowMorph(bovine ass)], covered in [pc.assFullDescription].<br/>"
							+ "You have also been left with [style.boldCowMorph(a bovine)] [pc.assholeFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] now has a [style.boldCowMorph(bovine ass)], covered in [npc.assFullDescription].<br/>"
							+ "[npc.She] has also been left with [style.boldCowMorph(a bovine)] [npc.assholeFullDescription]."
							+ "</p>");
				}
				break;
			case HORSE_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You now have an [style.boldHorseMorph(equine ass)], covered in [pc.assFullDescription].<br/>"
							+ "You have also been left with [style.boldHorseMorph(an equine)] [pc.assholeFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] now has an [style.boldHorseMorph(equine ass)], covered in [npc.assFullDescription].<br/>"
							+ "[npc.She] has also been left with [style.boldHorseMorph(an equine)] [npc.assholeFullDescription]."
							+ "</p>");
				}
				break;
			case REINDEER_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You now have a [style.boldReindeerMorph(reindeer-morph's ass)], covered in [pc.assFullDescription].<br/>"
							+ "You have also been left with [style.boldReindeerMorph(a rangiferine)] [pc.assholeFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] now has an [style.boldReindeerMorph(reindeer-morph's ass)], covered in [npc.assFullDescription].<br/>"
							+ "[npc.She] has also been left with [style.boldReindeerMorph(a rangiferine)] [npc.assholeFullDescription]."
							+ "</p>");
				}
				break;
			case SQUIRREL_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You now have a [style.boldSquirrelMorph(squirrel-morph ass)], covered in [pc.assFullDescription].<br/>"
							+ "You have also been left with [style.boldSquirrelMorph(a squirrel-morph)] [pc.assholeFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] now has a [style.boldSquirrelMorph(squirrel-morph ass)], covered in [npc.assFullDescription].<br/>"
							+ "[npc.She] has also been left with [style.boldSquirrelMorph(a squirrel-morph)] [npc.assholeFullDescription]."
							+ "</p>");
				}
				break;
			case RAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You now have a [style.boldRatMorph(rat-morph ass)], covered in [pc.assFullDescription].<br/>"
							+ "You have also been left with [style.boldRatMorph(a rat-morph)] [pc.assholeFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] now has a [style.boldRatMorph(rat-morph ass)], covered in [npc.assFullDescription].<br/>"
							+ "[npc.She] has also been left with [style.boldRatMorph(a rat-morph)] [npc.assholeFullDescription]."
							+ "</p>");
				}
				break;
			case RABBIT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You now have a [style.boldRabbitMorph(rabbit-morph ass)], covered in [pc.assFullDescription].<br/>"
							+ "You have also been left with [style.boldRabbitMorph(a rabbit-morph)] [pc.assholeFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] now has a [style.boldRabbitMorph(rabbit-morph ass)], covered in [npc.assFullDescription].<br/>"
							+ "[npc.She] has also been left with [style.boldRabbitMorph(a rabbit-morph)] [npc.assholeFullDescription]."
							+ "</p>");
				}
				break;
			case BAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You now have a [style.boldBatMorph(bat-morph ass)], covered in [pc.assFullDescription].<br/>"
							+ "You have also been left with [style.boldBatMorph(a bat-morph)] [pc.assholeFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] now has a [style.boldBatMorph(bat-morph ass)], covered in [npc.assFullDescription].<br/>"
							+ "[npc.She] has also been left with [style.boldBatMorph(a bat-morph)] [npc.assholeFullDescription]."
							+ "</p>");
				}
				break;
			case ALLIGATOR_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You now have an [style.boldGatorMorph(alligator-morph ass)], covered in [pc.assFullDescription].<br/>"
							+ "You have also been left with [style.boldGatorMorph(an alligator-morph)] [pc.assholeFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] now has an [style.boldGatorMorph(alligator-morph ass)], covered in [npc.assFullDescription].<br/>"
							+ "[npc.She] has also been left with [style.boldGatorMorph(an alligator-morph)] [npc.assholeFullDescription]."
							+ "</p>");
				}
				break;
			case HARPY:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"You now have an [style.boldHarpy(avian ass)], covered in [pc.assFullDescription].<br/>"
							+ "You have also been left with [style.boldHarpy(an avian)] [pc.assholeFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
							"[npc.She] now has an [style.boldHarpy(avian ass)], covered in [npc.assFullDescription].<br/>"
							+ "[npc.She] has also been left with [style.boldHarpy(an avian)] [npc.assholeFullDescription]."
							+ "</p>");
				}
				break;
//			default:
//				if (owner.isPlayer()) {
//					UtilText.transformationContentSB.append("You discover that your ass has shifted shape and transformed.<br/>"
//							+ "You now have an [style.boldSex("+type.getRace().getName()+" asshole)]."
//							+ "</p>");
//				} else {
//					UtilText.transformationContentSB.append(
//							"[npc.She] soon discovers that [npc.her] ass has shifted shape and transformed.<br/>"
//							+ "[npc.She] now has an [style.boldHarpy("+type.getRace().getName()+" asshole)]."
//							+ "</p>");
//				}
//				break;
		}
		
		return UtilText.parse(owner, UtilText.transformationContentSB.toString())
				+ "<p>"
				+ owner.postTransformationCalculation(false)
				+ "</p>";
	}
	
	public AssSize getAssSize() {
		return AssSize.getAssSizeFromInt(assSize);
	}

	/**
	 * Sets assSize attribute.<br/>
	 * Value is bound to >=0 && <=AssSize.SEVEN_GIGANTIC.getValue()
	 * 
	 * @param owner The character to change.
	 * @param assSize Value to set assSize to.
	 * @return Description of the change.
	 */
	public String setAssSize(GameCharacter owner, int assSize) {
		int oldSize = this.assSize;
		this.assSize = Math.max(0, Math.min(assSize, AssSize.SEVEN_GIGANTIC.getValue()));
		int sizeChange = this.assSize - oldSize;
		
		if (sizeChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The size of your ass doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The size of [npc.namePos] ass doesn't change...)]</p>");
			}
		}
		
		String sizeDescriptor = getAssSize().getDescriptor();
		if (sizeChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You feel a little off-balance as your [pc.ass] suddenly seems to get heavier, and as you give it an experimental shake, you find that it's definitely [style.boldGrow(grown larger)].<br/>"
							+ "You now have [style.boldSex(" + UtilText.generateSingularDeterminer(sizeDescriptor) + " " + sizeDescriptor + " ass)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] looks to be a little off-balance as [npc.her] [npc.ass] suddenly seems to get bigger, and as [npc.she] gives it an experimental shake, [npc.she] discovers that it's definitely [style.boldGrow(grown larger)].<br/>"
							+ "[npc.She] now has [style.boldSex(" + UtilText.generateSingularDeterminer(sizeDescriptor) + " " + sizeDescriptor + " ass)]!"
						+ "</p>");
			}
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You suddenly feel like a weight has been lifted from your rear end, and, giving your [pc.ass] an experimental shake, you discover that it's [style.boldShrink(shrunk)].<br/>"
							+ "You now have [style.boldSex(" + UtilText.generateSingularDeterminer(sizeDescriptor) + " " + sizeDescriptor + " ass)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] looks to be a little off-balance as [npc.her] [npc.ass] suddenly seems to get smaller, and as [npc.she] gives it an experimental shake, [npc.she] discovers that it's definitely [style.boldShrink(shrunk)].<br/>"
							+ "[npc.She] now has [style.boldSex(" + UtilText.generateSingularDeterminer(sizeDescriptor) + " " + sizeDescriptor + " ass)]!"
						+ "</p>");
			}
		}
	}

	public HipSize getHipSize() {
		return HipSize.getHipSizeFromInt(hipSize);
	}
	
	/**
	 * Sets hipSize attribute.<br/>
	 * Value is bound to >=0 && <=HipSize.SEVEN_ABSURDLY_WIDE.getValue()
	 * 
	 * @param owner The character to change.
	 * @param hipSize Value to set hipSize to.
	 * @return Description of the change.
	 */
	public String setHipSize(GameCharacter owner, int hipSize) {
		int oldSize = this.hipSize;
		this.hipSize = Math.max(0, Math.min(hipSize, HipSize.SEVEN_ABSURDLY_WIDE.getValue()));
		int sizeChange = this.hipSize - oldSize;
		
		if (sizeChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The size of your hips doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The size of [npc.namePos] hips doesn't change...)]</p>");
			}
		}
		
		String styledSizeDescriptor = "[style.boldSex("+ getHipSize().getDescriptor() + " hips)]";
		if (sizeChange > 0) {
			if (owner.isPlayer()) {
				return "</p>"
							+ "You inhale sharply in surprise as you feel your hips reshape themselves, pushing out and [style.boldGrow(growing wider)].<br/>"
							+ "You now have " + styledSizeDescriptor + "!"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"</p>"
							+ "[npc.Name] inhales sharply in surprise as [npc.she] feels [npc.her] hips reshape themselves, pushing out and [style.boldGrow(growing wider)].<br/>"
							+ "[npc.She] now has " + styledSizeDescriptor + "!"
						+ "</p>");
			}
		} else {
			if (owner.isPlayer()) {
				return "</p>"
							+ "You inhale sharply in surprise as you feel your hips collapse inwards and reshape themselves as they get [style.boldShrink(narrower)].<br/>"
							+ "You now have " + styledSizeDescriptor + "!"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"</p>"
							+ "[pc.Name] inhales sharply in surprise as [npc.she] feels [npc.her] hips collapse inwards and reshape themselves as they get [style.boldShrink(narrower)].<br/>"
							+ "[npc.She] now has " + styledSizeDescriptor + "!"
						+ "</p>");
			}
		}
	}
	
}
