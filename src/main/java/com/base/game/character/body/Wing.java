package com.base.game.character.body;

import java.io.Serializable;

import com.base.game.character.GameCharacter;
import com.base.game.character.body.types.WingType;
import com.base.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public class Wing implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	private WingType type;

	public Wing(WingType type) {
		this.type = type;
	}

	@Override
	public WingType getType() {
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

	public String setType(GameCharacter owner, WingType type) {
		if (type == getType()) {
			if(type == WingType.NONE) {
				if(owner.isPlayer()) {
					return "<p style='text-align:center;'>[style.colourDisabled(You already lack wings, so nothing happens...)]</p>";
				} else {
					return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already lacks wings, so nothing happens...)]</p>");
				}
			} else {
				if (owner.isPlayer()) {
					return "<p style='text-align:center;'>[style.colourDisabled(You already have the [pc.wings] of [pc.a_wingRace], so nothing happens...)]</p>";
				} else {
					return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already has the [npc.wings] of [npc.a_wingRace], so nothing happens...)]</p>");
				}
			}
			
		} else {
			UtilText.transformationContentSB.setLength(0);

			if(type == WingType.NONE) {
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"<p>"
								+ "You feel a strange bubbling feeling rising up in your back, and as you start to wonder what's going on, you feel something pushing out from under your [pc.skin].");
				} else {
					UtilText.transformationContentSB.append(
							"<p>"
								+ "[npc.Name] tries to look behind [npc.herHim] as [npc.she] feels a strange bubbling sensation rising up in [npc.her] back, before something starts pushing out from under [npc.her] [npc.skin].");
				}
				
			} else {
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
							"<p>"
								+ "Your [pc.wings] suddenly start to twitch and wriggle with a mind of their own, and you let out a gasp as you feel them start to transform.");
				} else {
					UtilText.transformationContentSB.append(
							"<p>"
								+ "[npc.Name]'s [npc.wings] suddenly start to twitch and wriggle with a mind of their own, and [npc.she] lets out a gasp as [npc.she] feels them start to transform.");
				}
				
			}
		}
		
		switch (type) {
			case ANGEL:
				if(owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You bite your [pc.lip] to try and suppress an unexpected moan of pleasure as a pair of huge, feathered wings push out from your shoulder blades."
								+ " You give them an experimental flap, and, much to your delight, you discover that they're powerful enough to enable you to fly."
								+ "</br>"
								+ "You now have [style.boldAngel(angelic, feathered wings)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" [npc.She] bites [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of huge, feathered wings push out from [npc.her] shoulder blades."
								+ " [npc.She] gives them an experimental flap, and, much to [npc.her] delight, [npc.she] discovers that they're powerful enough to enable [npc.herHim] to fly."
								+ "</br>"
								+ "[npc.Name] now has [style.boldAngel(angelic, feathered wings)]."
							+ "</p>");
				}
				break;
			case DEMON_COMMON:
				if(owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You bite your [pc.lip] to try and suppress an unexpected moan of pleasure as a pair of cute little bat-like wings push out from your shoulder blades."
								+ " You give them an experimental flutter, but they seem to be too small to be of any use."
								+ "</br>"
								+ "You now have [style.boldDemon(demonic bat-like wings)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" [npc.She] bites [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of cute little bat-like wings push out from [npc.her] shoulder blades."
								+ " [npc.She] gives them an experimental flutter, but they seem to be too small to be of any use."
								+ "</br>"
								+ "[npc.Name] now has [style.boldDemon(demonic bat-like wings)]."
							+ "</p>");
				}
				break;
			case NONE:
				if(owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" With a strong tugging sensation, your [pc.wings] shrink away into the flesh of your back."
								+ "</br>"
								+ "You now have [style.boldTfGeneric(no wings)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" With a strong tugging sensation, [npc.her] [npc.wings] shrink away into the flesh of [npc.her] back."
								+ "</br>"
								+ "[npc.Name] now has [style.boldTfGeneric(no wings)]."
							+ "</p>");
				}
				break;
		}

		this.type = type;
		
		return UtilText.parse(owner, UtilText.transformationContentSB.toString())
				+ "<p>"
				+ owner.postTransformationCalculation()
				+ "</p>";
	}

}
