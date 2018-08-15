package com.lilithsthrone.game.character.body;

import java.io.Serializable;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.TentacleType;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Innoxia
 */
public class Tentacle implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	protected TentacleType type;
	protected int tentacleCount;

	public Tentacle(TentacleType type) {
		this.type = type;
		tentacleCount = 1;
	}

	@Override
	public TentacleType getType() {
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

	public String setType(GameCharacter owner, TentacleType type) {
		if(owner==null) {
			this.type = type;
			return "";
		}
		
		if (type == getType()) {
			if(type == TentacleType.NONE) {
				if(owner.isPlayer()) {
					return "<p style='text-align:center;'>[style.colourDisabled(You already lack tentacles, so nothing happens...)]</p>";
				} else {
					return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already lacks tentacles, so nothing happens...)]</p>");
				}
			} else {
				if(owner.isPlayer()) {
					return "<p style='text-align:center;'>[style.colourDisabled(You already have the [pc.tentacles] of [pc.a_tentacleRace], so nothing happens...)]</p>";
				} else {
					return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already has the [npc.tentacles] of [npc.a_tentacleRace], so nothing happens...)]</p>");
				}
			}
		}
		
		UtilText.transformationContentSB.setLength(0);
		
		if(this.type == TentacleType.NONE) {
			UtilText.transformationContentSB.append(
					"<p>"
						+ "[npc.Name] [npc.verb(rub)] at the middle of [npc.her] back as [npc.she] [npc.verb(feel)] [npc.her] [npc.skin] growing hot and sensitive, and as [npc.she] [npc.verb(do)] so,"
							+ " [npc.she] [npc.verb(feel)] something pushing out from under [npc.her] [npc.skin].");
		} else {
			UtilText.transformationContentSB.append(
					"<p>"
						+ (owner.getTentacleCount()==1
							?"[npc.Name] [npc.verb(feel)] [npc.her] [npc.tentacle] growing hot and itchy, and after just a moment it starts to transform."
							:"[npc.Name] [npc.verb(feel)] [npc.her] [npc.tentacles] growing hot and itchy, and after just a moment they start to transform."));
		}
		
		switch (type) {
			case NONE:
				UtilText.transformationContentSB.append(
						(owner.getTentacleCount()==1
							?" [npc.She] [npc.verb(gasp)] as [npc.she] [npc.verb(feel)] [npc.her] [npc.tentacle] shrinking down and disappearing into [npc.her] back."
							:" [npc.She] [npc.verb(gasp)] as [npc.she] [npc.verb(feel)] [npc.her] [npc.tentacles] shrinking down and disappearing into [npc.her] back.")
						+ "<br/>"
						+ "[npc.Name] now [npc.has] [style.boldTfGeneric(no tentacles)]");
				break;
			case DEMON_COMMON:
				UtilText.transformationContentSB.append(
						(owner.getTentacleCount()==1
							?" A demonic, spaded tentacle sprouts from the middle of [npc.her] spine, rapidly growing in size until it's roughly as long as [npc.sheIsFull] tall."
								+ " [npc.She] quickly [npc.verb(discover)] that [npc.she] [npc.has] complete control over where it goes, allowing [npc.herHim] to use it like a third limb."
								+ "<br/>"
								+ "[npc.Name] now [npc.has] a [style.boldDemon(demonic tentacle)]"
							:" [npc.TentacleCount] demonic, spaded tentacles sprout from the middle of [npc.her] spine, rapidly growing in size until they're roughly as long as [npc.sheIsFull] tall."
								+ " [npc.She] quickly [npc.verb(discover)] that [npc.she] [npc.has] complete control over where they go, allowing [npc.herHim] to use them like extra limbs."
								+ "<br/>"
								+ "[npc.Name] now [npc.has] [npc.tentacleCount] [style.boldDemon(demonic tentacles)]")
						);
				break;
		}
		
		this.type = type;
		
		if(type == TentacleType.NONE) {
			UtilText.transformationContentSB.append(".</p>");
		} else {
			UtilText.transformationContentSB.append(", covered in [npc.tentacleFullDescription(true)].</p>");
		}
		
		return UtilText.parse(owner, UtilText.transformationContentSB.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public int getTentacleCount() {
		return tentacleCount;
	}

	public String setTentacleCount(GameCharacter owner, int tentacleCount) {
		tentacleCount = Math.max(1, Math.min(tentacleCount, 9));
		
		if(owner.getTentacleCount() == tentacleCount) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		boolean removingTentacles = owner.getTentacleCount() > tentacleCount;
		this.tentacleCount = tentacleCount;
		
		if (owner.getTentacleType() == TentacleType.NONE) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		if(removingTentacles) {
			return UtilText.parse(owner,
					"<p>"
						+ "A tingling feeling spreads over [npc.namePos] [npc.tentacles], before moving down into [npc.her] spine."
						+ " [npc.She] can't help but let out a little cry as [npc.she] [npc.verb(feel)] some of them [style.boldShrink(shrinking away)] and disappearing back down into [npc.her] [npc.skin].<br/>"
						+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric("
								+(tentacleCount==1
									?"a single [npc.tentacle]"
									:Util.intToString(tentacleCount)+" [npc.tentacles]")
						+ ")]."
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "A tingling feeling spreads over [npc.namePos] [npc.tentacles], before moving down into [npc.her] spine."
						+ " [npc.She] can't help but let out a little cry as [npc.she] [npc.verb(feel)] new [npc.tentacles] [style.boldGrow(pushing up)] and growing out of [npc.her] [npc.skin].<br/>"
						+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric("
								+(tentacleCount==1
									?"a single [npc.tentacle]"
									:Util.intToString(tentacleCount)+" [npc.tentacles]")
						+ ")]."
					+ "</p>");
		}
	}
}
