package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.8
 * @version 0.4.9.7
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

	public Clitoris(Clitoris clitorisToCopy) {
		this.clitSize = clitorisToCopy.clitSize;
		this.girth = clitorisToCopy.girth;
		
		this.clitModifiers = new HashSet<>(clitorisToCopy.clitModifiers);
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
		return getNameSingular(gc);
	}
	
	@Override
	public String getName(GameCharacter gc, boolean withDescriptor) {
		String name = getName(gc);
		String descriptor = getDescriptor(gc);
		return (withDescriptor && descriptor!=null && !descriptor.isEmpty()?descriptor+" ":"")+name;
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		if(this.getClitorisSize()==ClitorisSize.ZERO_AVERAGE) {
			return UtilText.returnStringAtRandom("clit", "clit", "clit", "nub", "button");
		} else {
			return UtilText.returnStringAtRandom("clit", "clit", "clit", "clit-dick");
		}
	}

	@Override
	public String getNamePlural(GameCharacter gc) {
		if(this.getClitorisSize()==ClitorisSize.ZERO_AVERAGE) {
			return UtilText.returnStringAtRandom("clits", "clits", "clits", "nubs", "buttons");
		} else {
			return UtilText.returnStringAtRandom("clits", "clits", "clits", "clit-dick");
		}
	}

	@Override
	public AbstractBodyCoveringType getBodyCoveringType(GameCharacter gc) {
		return gc.getVaginaType().getBodyCoveringType(gc);
	}

	@Override
	public AbstractBodyCoveringType getBodyCoveringType(Body body) {
		return body.getVagina().getBodyCoveringType(body);
	}
	
	@Override
	public String getDescriptor(GameCharacter gc) {
		List<String> descriptors = new ArrayList<>();
		
		descriptors.add("sensitive");
		
		if(this.getGirth()!=PenetrationGirth.THREE_AVERAGE) {
			descriptors.add(this.getGirth().getName());
		}
		
		if(this.getClitorisSize()!=ClitorisSize.ZERO_AVERAGE) {
			descriptors.add(this.getClitorisSize().getDescriptor());
		} else {
			descriptors.add("little");
		}
		
		if(!this.getClitorisModifiers().isEmpty()) {
			PenetrationModifier mod = Util.randomItemFrom(this.getClitorisModifiers());
			if(mod!=PenetrationModifier.OVIPOSITOR) {
				descriptors.add(mod.getName());
			}
		}

		if(gc.getBodyMaterial().getPartDescriptors()!=null && !gc.getBodyMaterial().getPartDescriptors().isEmpty()) {
			descriptors.add(Util.randomItemFrom(gc.getBodyMaterial().getPartDescriptors()));
		}
		
		return Util.randomItemFrom(descriptors);
	}
	
	public String getClitTipNameSingular(GameCharacter gc) {
		return UtilText.returnStringAtRandom("tip", "tip", "end");
	}
	
	public String getClitTipNamePlural(GameCharacter gc) {
		return UtilText.returnStringAtRandom("tips", "tips", "ends");
	}
	
	public String getClitTipDescriptor(GameCharacter gc) {
		List<String> descriptors = new ArrayList<>();
		for(PenetrationModifier mod : this.getClitorisModifiers()) {
			switch(mod) {
				case BARBED:
				case KNOTTED:
				case OVIPOSITOR:
				case PREHENSILE:
				case RIBBED:
				case SHEATHED:
				case TENTACLED:
				case VEINY:
					break;
				case BLUNT:
					descriptors.add("blunt");
					break;
				case FLARED:
					descriptors.add("flared");
					break;
				case TAPERED:
					descriptors.add("tapered");
					break;
			}
		}
		if(descriptors.isEmpty()) {
			return "";
		}
		return Util.randomItemFrom(descriptors);
	}
	
	public ClitorisSize getClitorisSize() {
		return ClitorisSize.getClitorisSizeFromInt(clitSize);
	}

	public int getRawClitorisSizeValue() {
		return clitSize;
	}
	
	public String setClitorisSize(GameCharacter owner, int clitSize) {
		if(owner==null) {
			this.clitSize = Math.max(0, Math.min(clitSize, ClitorisSize.SEVEN_STALLION.getMaximumValue()));
			return "";
		}
		
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

	public PenetrationGirth getGirth() {
		return PenetrationGirth.getGirthFromInt(girth);
	}

	public int getRawGirthValue() {
		return girth;
	}

	/**
	 * Sets the girth. Value is bound to >=0 && <=PenetrationGirth.FOUR_FAT.getValue()
	 */
	public String setGirth(GameCharacter owner, int girth) {
		if(owner==null) {
			this.girth = Math.max(0, Math.min(girth, PenetrationGirth.getMaximum()));
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
		} else if (girth >= PenetrationGirth.getMaximum()) {
			if (this.girth != PenetrationGirth.getMaximum()) {
				girthChange = PenetrationGirth.getMaximum() - this.girth;
				this.girth = PenetrationGirth.getMaximum();
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

	// Diameter:

	public static float getGenericDiameter(int length, PenetrationGirth girth) {
		return getGenericDiameter(length, girth, new HashSet<>());
	}
	
	public static float getGenericDiameter(int length, PenetrationGirth girth, Set<PenetrationModifier> mods) {
		float baseDiameterModifier = 0.2f;
		baseDiameterModifier = Math.max(0.15f, baseDiameterModifier - (Math.max(length-15, 0) * 0.0025f)); // Every cm over 15 (6 inches) reduces the base diameter modifier by 0.25%
		
		return Units.round((length * baseDiameterModifier) * (1f + girth.getDiameterPercentageModifier() + (mods.contains(PenetrationModifier.FLARED)?0.05f:0) + (mods.contains(PenetrationModifier.TAPERED)?-0.05f:0)), 2);
	}
	
	public float getDiameter() {
		return getGenericDiameter(clitSize, getGirth(), clitModifiers);
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

		clitModifiers.add(modifier);
		
		List<String> pmsRemoved = new ArrayList<>();
		
		for(PenetrationModifier pm : modifier.getMutuallyExclusivePenetrationModifiers()) {
			if(hasClitorisModifier(pm)) {
				pmsRemoved.add(pm.getName());
				clitModifiers.remove(pm);
			}
		}

		if(owner==null) {
			return "";
		}
		
		if(!owner.hasVagina()) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(You don't have a clitoris, so nothing happens...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] doesn't have a clitoris, so nothing happens...)]</p>");
			}
		}
		
		
		String removedText = "";
		if(!pmsRemoved.isEmpty()) {
			removedText = "<br/>[style.italicsMinorBad(Due to being mutually exclusive with the '"+modifier.getName()+"' modifier, [npc.namePos] clit is no longer "+Util.stringsToStringList(pmsRemoved, false)+".)]";
		}
		
		String returnText = "";
		
		switch(modifier) {
			case RIBBED:
				returnText = "An intense pressure builds up within [npc.namePos] [npc.clit], but before [npc.she] [npc.has] a chance to react, a series of [style.boldGrow(hard, fleshy ribs)] grow up all along its length."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.clit] is now ribbed!)]";
				break;
			case TENTACLED:
				returnText = "A pulsating warmth builds up within [npc.namePos] [npc.clit], but before [npc.she] [npc.has] a chance to react, a series of [style.boldGrow(little wriggling tentacles)] grow up all along its length."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.clit] is now covered with little tentacles, which wriggle with a mind of their own!)]";
				break;
			case BARBED:
				returnText = "An intense warmth builds up within [npc.namePos] [npc.clit], but before [npc.she] [npc.has] a chance to react, a series of [style.boldGrow(little fleshy barbs)] grow up all along its length."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.clit] is now lined with fleshy, backwards-facing barbs!)]";
				break;
			case FLARED:
				returnText = "An intense warmth builds up in the tip of [npc.namePos] [npc.clit], and before [npc.she] [npc.has] a chance to react, the [style.boldGrow(head flares out)], much like that of a horse's."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.clit] now has a wide, flared head!)]";
				break;
			case BLUNT:
				returnText = "An intense warmth builds up in the tip of [npc.namePos] [npc.clit], and before [npc.she] [npc.has] a chance to react, the [style.boldGrow(head smoothes over)], much like that of a reptile's."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.clit] now has a smooth, blunt head!)]";
				break;
			case KNOTTED:
				returnText = "An intense warmth builds up in the base of [npc.namePos] [npc.clit], and before [npc.she] [npc.has] a chance to react, a [style.boldGrow(fat knot)] quickly grows up there."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.clit] now has a fat knot at the base!)]";
				break;
			case PREHENSILE:
				returnText = "A strange tingling sensation works its way up the length of [npc.namePos] [npc.clit], and [npc.she] suddenly becomes aware that it's transformed into being [style.boldGrow(prehensile)],"
								+ " allowing [npc.herHim] to twist and move it around just like a primate's tail."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.clit] is now prehensile!)]";
				break;
			case SHEATHED:
				returnText = "An intense pressure builds up in the base of [npc.namePos] [npc.clit], and before [npc.she] [npc.has] a chance to react, it pulls back into a brand new [style.boldGrow(sheath)] that's just grown up there."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.clit] is now sheathed!)]";
				break;
			case TAPERED:
				returnText = "An intense warmth builds up within [npc.namePos] [npc.clit], but before [npc.she] [npc.has] a chance to react, the shaft suddenly [style.boldGrow(tapers down)]."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.clit] is now tapered!)]";
				break;
			case VEINY:
				returnText = "An intense warmth builds up within [npc.namePos] [npc.clit], but before [npc.she] [npc.has] a chance to react, a series of [style.boldGrow(prominent veins)] grow up all along its length."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.clit] is now veiny!)]";
				break;
			case OVIPOSITOR:
				returnText = "An intense tingling sensation works its up [npc.namePos] [npc.clit], and [npc.she] can't help but let out [npc.a_moan+] as [npc.she] feels it transforming into [style.boldGrow(an ovipositor)]."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.clit] is now able to lay eggs!)]"
							+ "<br/><i>(To be fully functional, [npc.name] [npc.verb(require)] [npc.her] eggs to be fertilised before laying can occur. Eggs cannot be laid in an already-pregnant target's vagina.)</i>";
				break;
		}
		
		if(returnText.isEmpty()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		return UtilText.parse(owner,
				"<p>"
					+returnText
					+removedText
				+"</p>");
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
								+ "An soothing coolness builds up within [npc.namePos] [npc.clit], but before [npc.sheHasFull] a chance to react, [npc.her] hard, fleshy ribs suddenly [style.boldShrink(disappear)].<br/>"
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
								+ "A soothing coolness builds up within [npc.namePos] [npc.clit], and before [npc.sheHasFull] a chance to react, [npc.her] little wriggling tentacles [style.boldShrink(disappear)].<br/>"
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
								+ "A soothing coolness builds up within [npc.namePos] [npc.clit], but before [npc.sheHasFull] a chance to react, [npc.her] little fleshy barbs [style.boldShrink(disappear)].<br/>"
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
								+ "A soothing coolness builds up in the tip of [npc.namePos] [npc.clit], and before [npc.sheHasFull] a chance to react, [npc.her] flared head [style.boldShrink(shrinks)] down,"
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
								+ "A soothing coolness builds up in the tip of [npc.namePos] [npc.clit], and before [npc.sheHasFull] a chance to react, [npc.her] blunt head [style.boldShrink(shrinks)] down,"
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
								+ "A soothing coolness builds up in the base of [npc.namePos] [npc.clit], and before [npc.sheHasFull] a chance to react, [npc.her] fat knot [style.boldShrink(shrinks)] and disappears.<br/>"
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
								+ "A soothing coolness builds up in the base of [npc.namePos] [npc.clit], and before [npc.sheHasFull] a chance to react, [npc.her] sheath [style.boldShrink(disappears)].<br/>"
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
								+ "An intense warmth builds up within [npc.namePos] [npc.clit], but before [npc.sheHasFull] a chance to react, the shaft suddenly [style.boldShrink(widens)].<br/>"
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
								+ "An intense warmth builds up within [npc.namePos] [npc.clit], but before [npc.sheHasFull] a chance to react, [npc.her] prominent veins [style.boldShrink(disappear)].<br/>"
								+ "[style.boldSex([npc.NamePos] [npc.clit] is no longer veiny!)]"
							+ "</p>";
				}
			case OVIPOSITOR:
				return "<p>"
							+ "A strange tingling sensation works its way up the length of [npc.namePos] [npc.clit], and [npc.she] can't help but let out [npc.a_moan+] as [npc.she] feels it transforming to"
								+ " [style.boldShrink(no longer function as an ovipositor)]."
							+ "<br/>[style.boldSex([npc.NamePos] [npc.clit] is no longer an ovipositor!)]"
						+ "</p>";
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Clitoris.class) && getType().getRace().isFeralPartsAvailable());
	}
	
}
