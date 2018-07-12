package com.lilithsthrone.game.character.body;

import java.util.HashSet;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Innoxia
 */
public class Clitoris implements BodyPartInterface {
	
	protected int clitSize;
	protected int girth;
	protected Set<PenetrationModifier> clitModifiers;
	
	public Clitoris(int clitSize, int girth) {
		this.clitSize = clitSize;
		this.girth = girth;
		
		clitModifiers = new HashSet<>();
	}

	@Override
	public BodyPartTypeInterface getType() {
		return null;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return "";
	}

	@Override
	public String getName(GameCharacter gc) {
		return UtilText.returnStringAtRandom("clit", "clit", "clit", "nub", "button");
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		return UtilText.returnStringAtRandom("clit", "clit", "clit", "nub", "button");
	}

	@Override
	public String getNamePlural(GameCharacter gc) {
		return UtilText.returnStringAtRandom("clits", "clits", "clits", "nubs", "buttons");
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		return UtilText.returnStringAtRandom(
				"sensitive",
				"sensitive",
				this.getGirth()!=PenisGirth.TWO_AVERAGE?this.getGirth().getName():"",
				this.getClitorisSize()!=ClitorisSize.ZERO_AVERAGE?this.getClitorisSize().getDescriptor():"little");
	}
	
	public ClitorisSize getClitorisSize() {
		return ClitorisSize.getClitorisSizeFromInt(clitSize);
	}

	public int getRawClitorisSizeValue() {
		return clitSize;
	}
	
	public String setClitorisSize(GameCharacter owner, int clitSize) {
		if(!owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		int oldSize = this.clitSize;
		this.clitSize = Math.max(0, Math.min(clitSize, ClitorisSize.SEVEN_STALLION.getMaximumValue()));
		int sizeChange = this.clitSize - oldSize;
		
		if (sizeChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The size of your clit doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The size of [npc.namePos] clit doesn't change...)]</p>");
			}
		} else if (sizeChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out [pc.a_moan] as you feel a deep throbbing sensation building up within your [pc.pussy]."
							+ " Your cheeks flush red as the feeling works its way up into your clit, and with a little gasp, you feel it [style.boldGrow(grow larger)].<br/>"
							+ "You now have [style.boldSex([pc.a_clitSize] [pc.clit])]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] lets out [npc.a_moan] as [npc.she] feels a deep throbbing sensation building up within [npc.her] [npc.pussy]."
							+ " [npc.Her] cheeks flush red as the feeling works its way up [npc.her] clit, and with a little gasp, [npc.she] feels it [style.boldGrow(grow larger)].<br/>"
							+ "[npc.She] now has [style.boldSex([npc.a_clitSize] [npc.clit])]!"
						+ "</p>");
			}
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out [pc.a_moan] as you feel a deep throbbing sensation building up within your [pc.pussy]."
							+ " Your cheeks flush red as the feeling works its way up into your clit, and with a little gasp, you feel it [style.boldShrink(shrink)].<br/>"
							+ "You now have [style.boldSex([pc.a_clitSize] [pc.clit])]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name] lets out [npc.a_moan] as [npc.she] feels a deep throbbing sensation building up at the base of [npc.her] cock."
								+ " [npc.Her] cheeks flush red as the feeling works its way up [npc.her] clit, and with a little gasp, [npc.she] feels it [style.boldShrink(shrink)].<br/>"
								+ "[npc.She] now has [style.boldSex([npc.a_clitSize] [npc.clit])]!"
						+ "</p>");
			}
		}
	}
	

	// Girth:

	public PenisGirth getGirth() {
		return PenisGirth.getPenisGirthFromInt(girth);
	}

	public int getRawGirthValue() {
		return girth;
	}

	/**
	 * Sets the girth. Value is bound to >=0 && <=PenisGirth.FOUR_FAT.getValue()
	 */
	public String setGirth(GameCharacter owner, int girth) {
		if(owner==null) {
			this.girth = Math.max(0, Math.min(girth, PenisGirth.FOUR_FAT.getValue()));
			return "";
		}
		
		if(!owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		int girthChange = 0;
		
		if (girth <= 0) {
			if (this.girth != 0) {
				girthChange = 0 - this.girth;
				this.girth = 0;
			}
		} else if (girth >= PenisGirth.FOUR_FAT.getValue()) {
			if (this.girth != PenisGirth.FOUR_FAT.getValue()) {
				girthChange = PenisGirth.FOUR_FAT.getValue() - this.girth;
				this.girth = PenisGirth.FOUR_FAT.getValue();
			}
		} else {
			if (this.girth != girth) {
				girthChange = girth - this.girth;
				this.girth = girth;
			}
		}
		
		if(girthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The girth of [npc.namePos] [npc.clit] doesn't change...)]</p>");
		}
		
		if (girthChange > 0) {
			return UtilText.parse(owner,
					"</p>"
						+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up within [npc.her] clit."
						+ " [npc.Her] cheeks flush red as the feeling spreads throughout [npc.her] [npc.pussy+], and [npc.she] can't help but let out another [npc.moan+] as [npc.her] clit suddenly [style.boldGrow(grows thicker)].<br/>"
						+ "[npc.She] now [npc.has] [style.boldSex([npc.a_clitGirth] [npc.clit])]!"
					+ "</p>");
		} else {
			return UtilText.parse(owner,
					"</p>"
						+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up within [npc.her] clit."
						+ " [npc.Her] cheeks flush red as the feeling works its way up [npc.her] shaft, and [npc.she] can't help but let out another [npc.moan+] as [npc.her] clit suddenly [style.boldShrink(thins down)].<br/>"
						+ "[npc.She] now [npc.has] [style.boldSex([npc.a_clitGirth] [npc.cock])]!"
					+ "</p>");
		}
	}
	

	public Set<PenetrationModifier> getClitorisModifiers() {
		return clitModifiers;
	}
	
	public boolean hasClitorisModifier(PenetrationModifier modifier) {
		return clitModifiers.contains(modifier);
	}

	public String addClitorisModifier(GameCharacter owner, PenetrationModifier modifier) {
		if(hasClitorisModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		if(!owner.hasVagina()) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(You don't have a clitoris, so nothing happens...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] doesn't have a clitoris, so nothing happens...)]</p>");
			}
		}
		
		clitModifiers.add(modifier);
		
		switch(modifier) {
			case RIBBED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense pressure building up within your [pc.clit], but before you have a chance to react, a series of [style.boldGrow(hard, fleshy ribs)] grow up all along its length.<br/>"
								+ "[style.boldSex(Your [pc.clit] is now ribbed!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense pressure builds up within [npc.namePos] [npc.clit], but before [npc.she] has a chance to react, a series of [style.boldGrow(hard, fleshy ribs)] grow up all along its length.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.clit] is now ribbed!)]"
							+ "</p>";
				}
			case TENTACLED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a pulsating warmth building up within your [pc.clit], but before you have a chance to react, a series of [style.boldGrow(little wriggling tentacles)] grow up all along its length.<br/>"
								+ "[style.boldSex(Your [pc.clit] is now covered with little tentacles, which wriggle with a mind of their own!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "A pulsating warmth builds up within [npc.namePos] [npc.clit], but before [npc.she] has a chance to react, a series of [style.boldGrow(little wriggling tentacles)] grow up all along its length.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.clit] is now covered with little tentacles, which wriggle with a mind of their own!)]"
							+ "</p>";
				}
			case BARBED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense warmth building up within your [pc.clit], but before you have a chance to react, a series of [style.boldGrow(little fleshy barbs)] grow up all along its length.<br/>"
								+ "[style.boldSex(Your [pc.clit] is now lined with fleshy, backwards-facing barbs!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense warmth builds up within [npc.namePos] [npc.clit], but before [npc.she] has a chance to react, a series of [style.boldGrow(little fleshy barbs)] grow up all along its length.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.clit] is now lined with fleshy, backwards-facing barbs!)]"
							+ "</p>";
				}
			case FLARED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense warmth building up in the tip of your [pc.clit], and before you have a chance to react, the [style.boldGrow(head flares out)], much like that of a horse's cock.<br/>"
								+ "[style.boldSex(Your [pc.clit] now has a wide, flared head!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense warmth builds up in the tip of [npc.namePos] [npc.clit], and before [npc.she] has a chance to react, the [style.boldGrow(head flares out)], much like that of a horse's cock.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.clit] now has a wide, flared head!)]"
							+ "</p>";
				}
			case BLUNT:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense warmth building up in the tip of your [pc.clit], and before you have a chance to react, the [style.boldGrow(head smoothes over)], leaving it looking like the head of a reptile's cock.<br/>"
								+ "[style.boldSex(Your [pc.clit] now has a smooth, blunt head!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense warmth builds up in the tip of [npc.namePos] [npc.clit], and before [npc.she] has a chance to react, the [style.boldGrow(head smoothes over)], leaving it looking like the head of a reptile's cock.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.clit] now has a smooth, blunt head!)]"
							+ "</p>";
				}
			case KNOTTED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense warmth building up in the base of your [pc.clit], and before you have a chance to react, a [style.boldGrow(fat knot)] quickly grows up there.<br/>"
								+ "[style.boldSex(Your [pc.clit] now has a fat knot at the base!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense warmth builds up in the base of [npc.namePos] [npc.clit], and before [npc.she] has a chance to react, a [style.boldGrow(fat knot)] quickly grows up there.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.clit] now has a fat knot at the base!)]"
							+ "</p>";
				}
			case PREHENSILE:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a strange tingling sensation work its way up the length of your [pc.clit], and you suddenly become aware that it's transformed into being [style.boldGrow(prehensile)],"
									+ " allowing you to twist and move it around just like a primate's tail.<br/>"
								+ "[style.boldSex(Your [pc.clit] is now prehensile!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "A strange tingling sensation work its way up the length of [npc.namePos] [npc.clit], and [npc.she] suddenly becomes aware that it's transformed into being [style.boldGrow(prehensile)],"
									+ " allowing [npc.herHim] to twist and move it around just like a primate's tail.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.clit] is now prehensile!)]"
							+ "</p>";
				}
			case SHEATHED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense pressure building up in the base of your [pc.clit], and before you have a chance to react, it pulls back into a brand new [style.boldGrow(sheath)] that's just grown up there.<br/>"
								+ "[style.boldSex(Your [pc.clit] is now sheathed!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense pressure builds up in the base of [npc.namePos] [npc.clit], and before [npc.she] has a chance to react, it pulls back into a brand new [style.boldGrow(sheath)] that's just grown up there.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.clit] is now sheathed!)]"
							+ "</p>";
				}
			case TAPERED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense warmth building up within your [pc.clit], but before you have a chance to react, the shaft suddenly [style.boldGrow(tapers down)].<br/>"
								+ "[style.boldSex(Your [pc.clit] is now tapered!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense warmth builds up within [npc.namePos] [npc.clit], but before [npc.she] has a chance to react, the shaft suddenly [style.boldGrow(tapers down)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.clit] is now tapered!)]"
							+ "</p>";
				}
			case VEINY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense warmth building up within your [pc.clit], but before you have a chance to react, a series of [style.boldGrow(prominent veins)] grow up all along its length.<br/>"
								+ "[style.boldSex(Your [pc.clit] is now veiny!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense warmth builds up within [npc.namePos] [npc.clit], but before [npc.she] has a chance to react, a series of [style.boldGrow(prominent veins)] grow up all along its length.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.clit] is now veiny!)]"
							+ "</p>";
				}
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}

	public String removeClitorisModifier(GameCharacter owner, PenetrationModifier modifier) {
		if(!hasClitorisModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		clitModifiers.remove(modifier);
		
		switch(modifier) {
			case RIBBED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soothing coolness spread up within your [pc.clit], and before you have a chance to react, your hard, fleshy ribs suddenly [style.boldShrink(disappear)].<br/>"
								+ "[style.boldSex(Your [pc.clit] is no longer ribbed!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An soothing coolness builds up within [npc.namePos] [npc.clit], but before [npc.she] has a chance to react, [npc.her] hard, fleshy ribs suddenly [style.boldShrink(disappear)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.clit] is no longer ribbed!)]"
							+ "</p>";
				}
			case TENTACLED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soothing coolness building up within your [pc.clit], and before you have a chance to react, your little wriggling tentacles [style.boldShrink(disappear)].<br/>"
								+ "[style.boldSex(Your [pc.clit] is no longer covered with little tentacles!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "A soothing coolness builds up within [npc.namePos] [npc.clit], and before [npc.she] has a chance to react, [npc.her] little wriggling tentacles [style.boldShrink(disappear)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.clit] is no longer covered with little tentacles!)]"
							+ "</p>";
				}
			case BARBED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soothing coolness building up within your [pc.clit], and before you have a chance to react, your little fleshy barbs [style.boldShrink(disappear)].<br/>"
								+ "[style.boldSex(Your [pc.clit] is no longer barbed!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "A soothing coolness builds up within [npc.namePos] [npc.clit], but before [npc.she] has a chance to react, [npc.her] little fleshy barbs [style.boldShrink(disappear)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.clit] is no longer barbed!)]"
							+ "</p>";
				}
			case FLARED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soothing coolness building up in the tip of your [pc.clit], and before you have a chance to react, your flared head [style.boldShrink(shrinks)] down,"
									+ " making your clit look like a regular, human-like one.<br/>"
								+ "[style.boldSex(Your [pc.clit] no longer has a flared head!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "A soothing coolness builds up in the tip of [npc.namePos] [npc.clit], and before [npc.she] has a chance to react, [npc.her] flared head [style.boldShrink(shrinks)] down,"
									+ " making [npc.her] clit look like a regular, human-like one.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.clit] no longer has a flared head!)]"
							+ "</p>";
				}
			case BLUNT:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soothing coolness building up in the tip of your [pc.clit], and before you have a chance to react, your blunt head [style.boldShrink(shrinks)] down,"
									+ " making your clit look like a regular, human-like one.<br/>"
								+ "[style.boldSex(Your [pc.clit] no longer has a blunt head!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "A soothing coolness builds up in the tip of [npc.namePos] [npc.clit], and before [npc.she] has a chance to react, [npc.her] blunt head [style.boldShrink(shrinks)] down,"
									+ " making [npc.her] clit look like a regular, human-like one.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.clit] no longer has a blunt head!)]"
							+ "</p>";
				}
			case KNOTTED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soothing coolness building up in the base of your [pc.clit], and before you have a chance to react, your fat knot [style.boldShrink(shrinks)] and disappears.<br/>"
								+ "[style.boldSex(Your [pc.clit] is no longer knotted!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "A soothing coolness builds up in the base of [npc.namePos] [npc.clit], and before [npc.she] has a chance to react, [npc.her] fat knot [style.boldShrink(shrinks)] and disappears.<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.clit] is no longer knotted!)]"
							+ "</p>";
				}
			case PREHENSILE:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a strange tingling sensation work its way up the length of your [pc.clit], and you suddenly become aware that it's [style.boldShrink(no longer prehensile)].<br/>"
								+ "[style.boldSex(Your [pc.clit] is no longer prehensile!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "A strange tingling sensation works its way up the length of [npc.namePos] [npc.clit], and [npc.she] suddenly becomes aware that it's [style.boldShrink(no longer prehensile)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.clit] is no longer prehensile!)]"
							+ "</p>";
				}
			case SHEATHED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a soothing coolness building up in the base of your [pc.clit], and before you have a chance to react, your sheath [style.boldShrink(disappears)].<br/>"
								+ "[style.boldSex(Your [pc.clit] is no longer sheathed!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "A soothing coolness builds up in the base of [npc.namePos] [npc.clit], and before [npc.she] has a chance to react, [npc.her] sheath [style.boldShrink(disappears)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.clit] is no longer sheathed!)]"
							+ "</p>";
				}
			case TAPERED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense warmth building up within your [pc.clit], but before you have a chance to react, the shaft suddenly [style.boldShrink(widens)].<br/>"
								+ "[style.boldSex(Your [pc.clit] is no longer tapered!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense warmth builds up within [npc.namePos] [npc.clit], but before [npc.she] has a chance to react, the shaft suddenly [style.boldShrink(widens)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.clit] is no longer tapered!)]"
							+ "</p>";
				}
			case VEINY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense warmth building up within your [pc.clit], but before you have a chance to react, your prominent veins [style.boldShrink(disappear)].<br/>"
								+ "[style.boldSex(Your [pc.clit] is no longer veiny!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "An intense warmth builds up within [npc.namePos] [npc.clit], but before [npc.she] has a chance to react, [npc.her] prominent veins [style.boldShrink(disappear)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.clit] is no longer veiny!)]"
							+ "</p>";
				}
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}
	
}
