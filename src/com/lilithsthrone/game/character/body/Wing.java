package com.lilithsthrone.game.character.body;

import java.io.Serializable;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.1.95
 * @author Innoxia
 */
public class Wing implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	protected WingType type;
	protected int size;
	
	public Wing(WingType type, int size) {
		this.type = type;
		this.size = size;
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

			if(type != WingType.NONE) {
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
								+ "Your [pc.wings] suddenly start to twitch and flap with a mind of their own, and you let out a gasp as you feel them start to transform.");
				} else {
					UtilText.transformationContentSB.append(
							"<p>"
								+ "[npc.NamePos] [npc.wings] suddenly start to twitch and flap with a mind of their own, and [npc.she] lets out a gasp as [npc.she] feels them start to transform.");
				}
				
			}
		}
		
		switch (type) {
			case ANGEL:
				if(owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You bite your [pc.lip] to try and suppress an unexpected moan of pleasure as a pair of huge, feathered wings push out from your shoulder blades."
								+ (getSize().isSizeAllowsFlight()
										?" You give them an experimental flap, and, much to your delight, you discover that they're powerful enough to enable you to fly."
										:" You give them an experimental flap, and although they aren't quite big enough just yet, you think that if you were to increase their size, they'd enable you to fly.")
								+ "<br/>"
								+ "You now have [style.boldAngel(angelic, feathered wings)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" [npc.She] bites [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of huge, feathered wings push out from [npc.her] shoulder blades."
								+ (getSize().isSizeAllowsFlight()
										?" [npc.She] gives them an experimental flap, and, much to [npc.her] delight, [npc.she] discovers that they're powerful enough to enable [npc.herHim] to fly."
										:" [npc.She] gives them an experimental flap, and although they aren't quite big enough just yet, it looks as though if they were to be a little bigger, they'd enable [npc.herHim] to fly.")
								+ "<br/>"
								+ "[npc.Name] now has [style.boldAngel(angelic, feathered wings)]."
							+ "</p>");
				}
				break;
			case DEMON_COMMON:
				if(owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You bite your [pc.lip] to try and suppress an unexpected moan of pleasure as a pair of cute little bat-like wings push out from your shoulder blades."
								+ (getSize().isSizeAllowsFlight()
										?" You give them an experimental flutter, and, much to your delight, you discover that they're powerful enough to enable you to fly."
										:" You give them an experimental flutter, and although they aren't quite big enough just yet, you think that if you were to increase their size, they'd enable you to fly.")
								+ "<br/>"
								+ "You now have [style.boldDemon(demonic bat-like wings)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" [npc.She] bites [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of cute little bat-like wings push out from [npc.her] shoulder blades."
								+ (getSize().isSizeAllowsFlight()
										?" [npc.She] gives them an experimental flutter, and, much to [npc.her] delight, [npc.she] discovers that they're powerful enough to enable [npc.herHim] to fly."
										:" [npc.She] gives them an experimental flutter, and although they aren't quite big enough just yet, it looks as though if they were to be a little bigger, they'd enable [npc.herHim] to fly.")
								+ "<br/>"
								+ "[npc.Name] now has [style.boldDemon(demonic bat-like wings)]."
							+ "</p>");
				}
				break;
			case IMP:
				if(owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" You bite your [pc.lip] to try and suppress an unexpected moan of pleasure as a pair of cute little bat-like wings push out from your shoulder blades."
								+ (getSize().isSizeAllowsFlight()
										?" You give them an experimental flutter, and, much to your delight, you discover that they're powerful enough to enable you to fly."
										:" You give them an experimental flutter, and although they aren't quite big enough just yet, you think that if you were to increase their size, they'd enable you to fly.")
								+ "<br/>"
								+ "You now have [style.boldImp(impish bat-like wings)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" [npc.She] bites [npc.her] [npc.lip] to try and suppress an unexpected moan of pleasure as a pair of cute little bat-like wings push out from [npc.her] shoulder blades."
								+ (getSize().isSizeAllowsFlight()
										?" [npc.She] gives them an experimental flutter, and, much to [npc.her] delight, [npc.she] discovers that they're powerful enough to enable [npc.herHim] to fly."
										:" [npc.She] gives them an experimental flutter, and although they aren't quite big enough just yet, it looks as though if they were to be a little bigger, they'd enable [npc.herHim] to fly.")
								+ "<br/>"
								+ "[npc.Name] now has [style.boldImp(impish bat-like wings)]."
							+ "</p>");
				}
				break;
			case NONE:
				if(owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" With a strong tugging sensation, your [pc.wings] shrink away into the flesh of your back."
								+ "<br/>"
								+ "You now have [style.boldTfGeneric(no wings)]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" With a strong tugging sensation, [npc.her] [npc.wings] shrink away into the flesh of [npc.her] back."
								+ "<br/>"
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

	public WingSize getSize() {
		return WingSize.getWingSizeFromInt(size);
	}

	public int getSizeValue() {
		return size;
	}
	
	public String setSize(GameCharacter owner, int wingSize) {
		if(owner.getWingType()==WingType.NONE) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(You don't have any wings, so nothing happens...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.NamePos] doesn't have any wings, so nothing happens...)]</p>");
			}
		}
		
		int effectiveSize = Math.max(0, Math.min(wingSize, WingSize.getLargest()));
		if(owner.getWingSizeValue() == effectiveSize) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The size of your [pc.wings] doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The size of [npc.namePos] [npc.wings] doesn't change...)]</p>");
			}
		}
		
		String transformation = "";
		
		if(this.size > effectiveSize) {
			if(owner.isPlayer()) {
				transformation = "<p>A soothing coolness rises up into your [pc.wings+], causing you to let out a surprised gasp as you feel them [style.boldShrink(shrinking)].<br/>";
			} else {
				transformation = UtilText.parse(owner, "<p>[npc.Name] lets out a little cry as [npc.she] feels a soothing coolness rise up into [npc.her] [npc.wings+], before they suddenly [style.boldShrink(shrink)].<br/>");
			}
			
		} else {
			if(owner.isPlayer()) {
				transformation = "<p>A pulsating warmth rises up into your [pc.wings+], causing you to let out a surprised gasp as you feel them [style.boldGrow(growing larger)].<br/>";
			} else {
				transformation = UtilText.parse(owner, "<p>[npc.Name] lets out a little cry as [npc.she] feels a pulsating warmth rise up into [npc.her] [npc.wings+], before they suddenly [style.boldGrow(grow larger)].<br/>");
			}
		}
		
		this.size = effectiveSize;

		if(owner.isPlayer()) {
			return transformation
				+ "You now have [style.boldSex([pc.wingSize] [pc.wings])]!</p>";
		} else {
			return transformation
					+ UtilText.parse(owner, "[npc.Name] now has [style.boldSex([npc.wingSize] [npc.wings])]!</p>");
		}
	}

}
