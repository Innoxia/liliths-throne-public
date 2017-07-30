package com.base.game.character.body;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.base.game.character.GameCharacter;
import com.base.game.character.Litter;
import com.base.game.character.PregnancyPossibility;
import com.base.game.character.body.types.ArmType;
import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.body.types.HornType;
import com.base.game.character.body.types.PenisType;
import com.base.game.character.body.types.TailType;
import com.base.game.character.body.types.TongueType;
import com.base.game.character.body.types.VaginaType;
import com.base.game.character.body.types.WingType;
import com.base.game.character.body.valueEnums.Capacity;
import com.base.game.character.body.valueEnums.CupSize;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.character.body.valueEnums.TesticleSize;
import com.base.game.character.effects.Perk;
import com.base.game.character.effects.StatusEffect;
import com.base.game.character.gender.Gender;
import com.base.game.character.race.Race;
import com.base.game.character.race.RaceStage;
import com.base.game.character.race.RacialBody;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.clothing.CoverableArea;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.SexType;
import com.base.main.Main;
import com.base.utils.Builder;
import com.base.utils.Colour;
import com.base.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.78
 * @author Innoxia
 */
public class Body implements Serializable {

	private static final long serialVersionUID = 1L;

	// Required:
	private Arm arm;
	private Ass ass;
	private Breast breast;
	private Face face;
	private Eye eye;
	private Ear ear;
	private Hair hair;
	private Leg leg;
	private Skin skin;

	// Optional:
	private Horn horn;
	private Penis penis;
	private Tail tail;
	private Vagina vagina;
	private Wing wing;

	private Race race;
	private RaceStage raceStage;
	private boolean piercedStomach = false;
	private int height, femininity;
	private Map<BodyCoveringType, Colour> skinTypeColours;
	private Set<BodyCoveringType> skinTypeColoursDiscovered;

	private List<BodyPartInterface> allBodyParts;

	public static class BodyBuilder implements Builder<Body> {

		// Required parameters:
		private final Arm arm;
		private final Ass ass;
		private final Breast breast;
		private final Face face;
		private final Eye eye;
		private final Ear ear;
		private final Hair hair;
		private final Leg leg;
		private final Skin skin;
		private final Race race = null;
		private final int height;
		private final int femininity;
		
		// Optional parameters - initialised to null values:
		private Horn horn = new Horn(HornType.NONE);
		private Penis penis = new Penis(PenisType.NONE, PenisType.NONE, 0, 0, 0, 0);
		private Tail tail = new Tail(TailType.NONE);
		private Vagina vagina = new Vagina(VaginaType.NONE, 0, 0, 0, 0, true);
		private Wing wing = new Wing(WingType.NONE);

		public BodyBuilder(Arm arm, Ass ass, Breast breast, Face face, Eye eye, Ear ear, Hair hair, Leg leg, Skin skin, int height, int femininity) {
			this.arm = arm;
			this.ass = ass;
			this.breast = breast;
			this.face = face;
			this.eye = eye;
			this.ear = ear;
			this.hair = hair;
			this.leg = leg;
			this.skin = skin;
			this.height = height;
			this.femininity = femininity;
			
		}

		public BodyBuilder horn(Horn horn) {
			this.horn = horn;
			return this;
		}

		public BodyBuilder penis(Penis penis) {
			this.penis = penis;
			return this;
		}

		public BodyBuilder tail(Tail tail) {
			this.tail = tail;
			return this;
		}

		public BodyBuilder vagina(Vagina vagina) {
			this.vagina = vagina;
			return this;
		}

		public BodyBuilder wing(Wing wing) {
			this.wing = wing;
			return this;
		}

		@Override
		public Body build() {
			return new Body(this, height, femininity);
		}
	}

	private Body(BodyBuilder builder, int height, int femininity) {
		arm = builder.arm;
		ass = builder.ass;
		breast = builder.breast;
		face = builder.face;
		eye = builder.eye;
		ear = builder.ear;
		hair = builder.hair;
		leg = builder.leg;
		skin = builder.skin;
		horn = builder.horn;
		penis = builder.penis;
		tail = builder.tail;
		vagina = builder.vagina;
		wing = builder.wing;
		race = builder.race;

		calculateRace(); // For determining RaceStage.

		this.height = height;
		this.femininity = femininity;

		allBodyParts = new ArrayList<>();
		allBodyParts.add(arm);
		allBodyParts.add(ass);
		allBodyParts.add(breast);
		allBodyParts.add(face);
		allBodyParts.add(eye);
		allBodyParts.add(ear);
		allBodyParts.add(hair);
		allBodyParts.add(leg);
		allBodyParts.add(skin);
		allBodyParts.add(horn);
		allBodyParts.add(penis);
		allBodyParts.add(tail);
		allBodyParts.add(vagina);
		allBodyParts.add(wing);
		
		skinTypeColours = new EnumMap<>(BodyCoveringType.class);
		for (BodyCoveringType s : BodyCoveringType.values()) 
			skinTypeColours.put(s, s.getNaturalColours().get(Util.random.nextInt(s.getNaturalColours().size())));
		
		skinTypeColoursDiscovered = EnumSet.noneOf(BodyCoveringType.class);
		for(BodyPartInterface bp : allBodyParts) {
			if(bp.getType().getSkinType()!=null)
				skinTypeColoursDiscovered.add(bp.getType().getSkinType());
		}
	}

	public List<BodyPartInterface> getAllBodyParts() {
		return allBodyParts;
	}

	/**
	 * 
	 * @param owner
	 * @param playerKnowledgeOfThroat
	 * @param playerKnowledgeOfBreasts
	 * @param playerKnowledgeOfGroin
	 * @return
	 */
	public String getDescription(GameCharacter owner) {
		StringBuilder sb = new StringBuilder();
		// Describe race:
		if (owner.isPlayer()) {
			sb.append("<p>"
						+ "You are [pc.name], <span style='color:"+getGender().getColour().toWebHexString()+";'>[pc.a_gender]</span> [pc.raceStage] [pc.race]. "
						+ owner.getAppearsAsGenderDescription()
						+" Standing at full height, you measure [pc.heightFeetInches] ([pc.heightCm]cm)."
					+"</p>");
		} else {
			if(owner.getPlayerKnowsAreasMap().get(CoverableArea.PENIS) && owner.getPlayerKnowsAreasMap().get(CoverableArea.VAGINA)) {
				sb.append("<p>"
						+ "[npc.Name] is <span style='color:"+getGender().getColour().toWebHexString()+";'>[npc.a_gender]</span> [npc.raceStage] [npc.race]. "
						+ owner.getAppearsAsGenderDescription()
						+ " Standing at full height, [npc.she] measures [npc.heightFeetInches] ([npc.heightCm]cm)."
					+ "</p>");
			} else {
				if(Main.game.getPlayer().hasPerk(Perk.OBSERVANT)) {
					sb.append("<p>"
							+ "Thanks to your observant perk, you can detect that [npc.name] is <span style='color:"+getGender().getColour().toWebHexString()+";'>[npc.a_gender]</span> [npc.raceStage] [npc.race]. "
							+ owner.getAppearsAsGenderDescription()
							+ " Standing at full height, [npc.she] measures [npc.heightFeetInches] ([npc.heightCm]cm)."
						+ "</p>");
				} else {
					sb.append("<p>"
								+ "[npc.Name] is a [npc.raceStage] [npc.race]. "
								+ owner.getAppearsAsGenderDescription()
								+ " Standing at full height, [npc.she] measures [npc.heightFeetInches] ([npc.heightCm]cm)."
							+ "</p>");
				}
			}
		}

		// Describe face (ears, eyes & horns):
		// Femininity:
		if (owner.isPlayer()) {
			sb.append("<p>"
						+ "You have ");
		} else {
			sb.append("<p>"
						+ "[npc.She] has ");
		}
		
		if (femininity <= Femininity.MASCULINE_STRONG.getMaximumFemininity()) {
			sb.append(
					UtilText.returnStringAtRandom(
							"a <span style='color:" + Colour.MASCULINE_PLUS.toWebHexString() + ";'>very masculine</span>",
							"an <span style='color:" + Colour.MASCULINE_PLUS.toWebHexString() + ";'>extremely handsome</span>"));
			
		} else if (femininity <= Femininity.MASCULINE.getMaximumFemininity()) {
			sb.append(
					UtilText.returnStringAtRandom(
							"a <span style='color:" + Colour.MASCULINE.toWebHexString() + ";'>masculine</span>",
							"a <span style='color:" + Colour.MASCULINE.toWebHexString() + ";'>handsome</span>"));
			
		} else if (femininity <= Femininity.ANDROGYNOUS.getMaximumFemininity()) {
			sb.append("an <span style='color:" + Colour.ANDROGYNOUS.toWebHexString() + ";'>androgynous</span>");
			
		} else if (femininity <= Femininity.FEMININE.getMaximumFemininity()) {
			sb.append(
					UtilText.returnStringAtRandom(
							"a <span style='color:" + Colour.FEMININE.toWebHexString() + ";'>pretty</span>",
							"a <span style='color:" + Colour.FEMININE.toWebHexString() + ";'>feminine</span>",
							"a <span style='color:" + Colour.FEMININE.toWebHexString() + ";'>cute</span>"));
			
		} else {
			sb.append(
					UtilText.returnStringAtRandom(
							"an <span style='color:" + Colour.FEMININE_PLUS.toWebHexString() + ";'>extremely beautiful</span>",
							"a <span style='color:" + Colour.FEMININE_PLUS.toWebHexString() + ";'>very feminine</span>",
							"a <span style='color:" + Colour.FEMININE_PLUS.toWebHexString() + ";'>beautiful</span>",
							"a <span style='color:" + Colour.FEMININE_PLUS.toWebHexString() + ";'>gorgeous</span>"));
		}
		
		// Face and eyes:
		switch (face.getType()) {
			case HUMAN:
				if (owner.isPlayer()) {
					sb.append(" face, with a pair of <span style='color:"+owner.getEyeColour().toWebHexString()+";'>[pc.eyeColour] [pc.eyes+]</span>"
							+ (face.isPiercedNose() ? " and a pierced nose." : "."));
				} else {
					sb.append(" face, with a pair of <span style='color:"+owner.getEyeColour().toWebHexString()+";'>[npc.eyeColour] [npc.eyes+]</span>"
							+ (face.isPiercedNose() ? " and a pierced nose." : "."));
				}
				break;
			case ANGEL:
				if (owner.isPlayer()) {
					sb.append(" face, which seems to radiate a soft golden glow."
							+ " You have a pair of <span style='color:"+owner.getEyeColour().toWebHexString()+";'>[pc.eyeColour] [pc.eyes+]</span>"
							+ (face.isPiercedNose() ? ", and your nose has been pierced." : "."));
				} else {
					sb.append(" face which seems to radiate a soft golden glow."
							+ " [npc.She] has a pair of <span style='color:"+owner.getEyeColour().toWebHexString()+";'>[npc.eyeColour] [npc.eyes+]</span>"
							+ (face.isPiercedNose() ? ", and [npc.her] nose has been pierced." : "."));
				}
				break;
			case DEMON_COMMON:
				if (owner.isPlayer()) {
					sb.append(", perfectly proportioned face, with a pair of <span style='color:"+owner.getEyeColour().toWebHexString()+";'>[pc.eyeColour] [pc.eyes+]</span>"
							+ (face.isPiercedNose() ? " and a pierced nose." : "."));
				} else {
					sb.append(", perfectly proportioned face, with a pair of <span style='color:"+owner.getEyeColour().toWebHexString()+";'>[npc.eyeColour] [npc.eyes+]</span>"
							+ (face.isPiercedNose() ? ", and a pierced nose." : "."));
				}
				break;
			case DOG_MORPH:
				if (owner.isPlayer()) {
					sb.append(", anthropomorphic dog-like face, complete with muzzle."
							+ " You have a pair of <span style='color:"+owner.getEyeColour().toWebHexString()+";'>[pc.eyeColour] [pc.eyes+]</span>"
							+ (face.isPiercedNose() ? "a pierced nose." : "."));
				} else {
					sb.append(", anthropomorphic dog-like face, complete with muzzle."
							+ " [npc.She] has a pair of <span style='color:"+owner.getEyeColour().toWebHexString()+";'>[npc.eyeColour] [npc.eyes+]</span>"
							+ (face.isPiercedNose() ? ", and a pierced nose." : "."));
				}
				break;
			case LYCAN:
				if (owner.isPlayer()) {
					sb.append(", anthropomorphic wolf-like face, complete with muzzle."
							+ " You have a pair of <span style='color:"+owner.getEyeColour().toWebHexString()+";'>[pc.eyeColour] [pc.eyes+]</span>" + (face.isPiercedNose() ? " and a pierced nose." : "."));
				} else {
					sb.append(", anthropomorphic wolf-like face, complete with muzzle."
							+ " [npc.She] has a pair of <span style='color:"+owner.getEyeColour().toWebHexString()+";'>[npc.eyeColour] [npc.eyes+]</span>" + (face.isPiercedNose() ? " and a pierced nose." : "."));
				}
				break;
			case CAT_MORPH:
				if (owner.isPlayer()) {
					sb.append(", anthropomorphic cat-like face, with a cute little muzzle."
							+ " You have a pair of <span style='color:"+owner.getEyeColour().toWebHexString()+";'>[pc.eyeColour] [pc.eyes+]</span>" + (face.isPiercedNose() ? " and a pierced nose." : "."));
				} else {
					sb.append(", anthropomorphic cat-like face, with a cute little muzzle."
							+ " [npc.She] has a pair of <span style='color:"+owner.getEyeColour().toWebHexString()+";'>[npc.eyeColour] [npc.eyes+]</span>" + (face.isPiercedNose() ? " and a pierced nose." : "."));
				}
				break;
			case HORSE_MORPH:
				if (owner.isPlayer()) {
					sb.append(", anthropomorphic horse-like face, with a long, horse-like muzzle."
							+ " You have a pair of <span style='color:"+owner.getEyeColour().toWebHexString()+";'>[pc.eyeColour] [pc.eyes+]</span>"
							+ (face.isPiercedNose() ? " and a pierced nose." : "."));
				} else {
					sb.append(", anthropomorphic horse-like face, with a long, horse-like muzzle."
							+ " [npc.She] has a pair of <span style='color:"+owner.getEyeColour().toWebHexString()+";'>[npc.eyeColour] [npc.eyes+]</span>"
							+ (face.isPiercedNose() ? " and a pierced nose." : "."));
				}
				break;
			case SLIME:
				if (owner.isPlayer())
					sb.append(" face, which is completely made out of [pc.faceColour] slime."
							+ " You have a pair of <span style='color:"+owner.getEyeColour().toWebHexString()+";'>[pc.eyeColour] [pc.eyes+]</span>"
							+ (face.isPiercedNose() ? " and a pierced nose." : "."));
				else
					sb.append(" face, which is completely made out of " + skinTypeColours.get(face.getType().getSkinType()).getName() + " slime."
							+ " [npc.She] has a pair of <span style='color:"+owner.getEyeColour().toWebHexString()+";'>[npc.eyeColour] [npc.eyes+]</span>"
							+ (face.isPiercedNose() ? " and a pierced nose." : "."));
				break;
			case HARPY:
				if (owner.isPlayer())
					sb.append(", anthropomorphic bird-like face, complete with beak."
							+ " You have a pair of <span style='color:"+owner.getEyeColour().toWebHexString()+";'>[pc.eyeColour] [pc.eyes+]</span>"
							+ (face.isPiercedNose() ? ", and the end of your beak has been pierced." : "."));
				else
					sb.append(", anthropomorphic bird-like face, complete with beak."
							+ " [npc.She] has a pair of <span style='color:"+owner.getEyeColour().toWebHexString()+";'>[npc.eyeColour] [npc.eyes+]</span>"
							+ (face.isPiercedNose() ? ", and the end of [npc.her] beak has been pierced." : "."));
				break;
			default:
				sb.append(face.getName(owner, true) + ".");
		}
		// Tongue & blowjob:
		if (owner.isPlayer()) {
			sb.append(" Your mouth holds a "
					+ (face.getTongue().getType()==TongueType.HUMAN
						?"normal tongue"
						:face.getTongue().getName(owner, true))
					+(face.getTongue().isPierced() ? ", which has been pierced." : "."));
			
			if(face.isPiercedLip()) {
				sb.append(" Your [pc.lips+] have been pierced.");
			}
			
			if (face.isVirgin()) {
				sb.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You've never given head before, so you don't know what you could fit down your throat</span>.");
			} else {
				switch(face.getCapacity().getMaximumSizeComfortableWithLube()) {
					case NEGATIVE_UTILITY_VALUE: case ZERO_MICROSCOPIC:
						sb.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You're terrible at giving head</span>, and struggle to fit the tip of even a tiny cock into your mouth without gagging.");
						break;
					case ONE_TINY:
						sb.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You're really bad at giving head</span>, and struggle to fit even a tiny cocks into your mouth without gagging.");
//						sb.append(" You find anything larger than "+face.getRawCapacityValue()+" inches to be uncomfortable.");
						break;
					case TWO_AVERAGE:
						sb.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You're not great at giving head</span>, and anything larger than an average-sized human cock will cause you to gag.");
//						sb.append(" You find anything larger than "+face.getRawCapacityValue()+" inches to be uncomfortable.");
						break;
					case THREE_LARGE:
						sb.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You're somewhat competent at giving head</span>, and can suppress your gag reflex enough to comfortably suck large cocks.");
//						sb.append(" You find anything larger than "+face.getRawCapacityValue()+" inches to be uncomfortable.");
						break;
					case FOUR_HUGE:
						sb.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You're pretty good at giving head</span>, and can comfortably suck huge cocks without gagging.");
//						sb.append(" You find anything larger than "+face.getRawCapacityValue()+" inches to be uncomfortable.");
						break;
					case FIVE_ENORMOUS:
						sb.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You're somewhat of an expert at giving head</span>, and can suck enormous cocks without too much difficulty.");
//						sb.append(" You find anything larger than "+face.getRawCapacityValue()+" inches to be uncomfortable.");
						break;
					case SIX_GIGANTIC:
						sb.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You're amazing at giving head</span>, and can comfortably suck all but the most absurdly-sized of cocks with ease.");
//						sb.append(" You find anything larger than "+face.getRawCapacityValue()+" inches to be uncomfortable.");
						break;
					case SEVEN_STALLION:
						sb.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You are</span>"
								+ " <span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>legendary</span>"
								+ " <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>at giving head</span>; it's almost as though your throat was purposefully designed to fit phallic objects of any size or shape.");
						break;
					default:
						break;
				}
				for(PenetrationType pt : PenetrationType.values()) {
					if(Main.game.getPlayer().getStats().getVirginityLoss(new SexType(pt, OrificeType.MOUTH_PLAYER))!=null) {
						sb.append(" <span style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>The first time you performed oral sex was to "
								+ Main.game.getPlayer().getStats().getVirginityLoss(new SexType(pt, OrificeType.MOUTH_PLAYER)) + ".</span>");
					}
				}
			}
		} else {
			sb.append(" [npc.Her] mouth holds a "
					+ (face.getTongue().getType()==TongueType.HUMAN
						?"normal tongue"
						:face.getTongue().getName(owner, true))
					+(face.getTongue().isPierced() ? ", which has been pierced." : "."));
			
			if(face.isPiercedLip()) {
				sb.append(" [npc.Her] [npc.lips+] have been pierced.");
			}
			
			if (owner.getPlayerKnowsAreasMap().get(CoverableArea.MOUTH)) {
				if (face.isVirgin()) {
					sb.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.She]'s never given head before.</span>.");
				} else {
					switch(face.getCapacity().getMaximumSizeComfortableWithLube()) {
					case NEGATIVE_UTILITY_VALUE: case ZERO_MICROSCOPIC:
						sb.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.She]'s terrible at giving head</span>, and struggles to fit the tip of even the smallest of cocks into [npc.her] mouth without gagging.");
						break;
					case ONE_TINY:
						sb.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.She]'s really bad at giving head</span>, and struggles to fit even tiny cocks into [npc.her] mouth without gagging.");
//						sb.append(" [npc.She] finds anything larger than "+face.getRawCapacityValue()+" inches to be uncomfortable.");
						break;
					case TWO_AVERAGE:
						sb.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.She]'s not great at giving head</span>, and anything larger than an average-sized human cock will cause [npc.her] to gag.");
//						sb.append(" [npc.She] finds anything larger than "+face.getRawCapacityValue()+" inches to be uncomfortable.");
						break;
					case THREE_LARGE:
						sb.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.She]'s somewhat competent at giving head</span>, and can suppress [npc.her] gag reflex enough to comfortably suck large cocks.");
//						sb.append(" [npc.She] finds anything larger than "+face.getRawCapacityValue()+" inches to be uncomfortable.");
						break;
					case FOUR_HUGE:
						sb.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.She]'s pretty good at giving head</span>, and can comfortably suck huge cocks without gagging.");
//						sb.append(" [npc.She] finds anything larger than "+face.getRawCapacityValue()+" inches to be uncomfortable.");
						break;
					case FIVE_ENORMOUS:
						sb.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.She]'s somewhat of an expert at giving head</span>, and can suck enormous cocks without too much difficulty.");
//						sb.append(" [npc.She] finds anything larger than "+face.getRawCapacityValue()+" inches to be uncomfortable.");
						break;
					case SIX_GIGANTIC:
						sb.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.She]'s amazing at giving head</span>, and can comfortably suck all but the most absurdly-sized of cocks with ease.");
//						sb.append(" [npc.She] finds anything larger than "+face.getRawCapacityValue()+" inches to be uncomfortable.");
						break;
					case SEVEN_STALLION:
						sb.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.She] is</span>"
								+ " <span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>legendary</span>"
								+ " <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>at giving head</span>;"
										+ " it's almost as though [npc.her] throat was purposefully designed to fit phallic objects of any size or shape.");
						break;
					default:
						break;
				}
				}
			} else {
				sb.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You don't know [npc.herHim] well enough to know how competent [npc.she] is at performing oral sex.</span>");
			}
		}
		// Ear:
		switch (ear.getType()) {
			case HUMAN:
				if (owner.isPlayer())
					sb.append(" You have a pair of normal, human ears" + (ear.isPierced() ? ", which have been pierced" : "") + ".");
				else
					sb.append(" [npc.She] has a pair of normal, human ears" + (ear.isPierced() ? ", which have been pierced" : "") + ".");
				break;
			case DEMON_COMMON:
				if (owner.isPlayer())
					sb.append(" You have a pair of pointed, demonic ears" + (ear.isPierced() ? ", which have been pierced" : "") + ".");
				else
					sb.append(" [npc.She] has a pair of pointed, demonic ears" + (ear.isPierced() ? ", which have been pierced" : "") + ".");
				break;
			case DOG_MORPH:
				if (owner.isPlayer())
					sb.append(" You have a pair of "+(ear.isPierced() ? "pierced, " : "")+"dog-like ears, which are positioned high up on your head.");
				else
					sb.append(" [npc.She] has a pair of "+(ear.isPierced() ? "pierced, " : "")+"dog-like ears, which are positioned high up on [npc.her] head.");
				break;
			case LYCAN:
				if (owner.isPlayer())
					sb.append(" You have a pair of "+(ear.isPierced() ? "pierced, " : "")+"upright, wolf-like ears, which are positioned high up on your head.");
				else
					sb.append(" [npc.She] has a pair of "+(ear.isPierced() ? "pierced, " : "")+"upright, wolf-like ears, which are positioned high up on [npc.her] head.");
				break;
			case CAT_MORPH:
				if (owner.isPlayer())
					sb.append(" You have a pair of "+(ear.isPierced() ? "pierced, " : "")+"upright, cat-like ears, which are positioned high up on your head.");
				else
					sb.append(" [npc.She] has a pair of "+(ear.isPierced() ? "pierced, " : "")+"upright, cat-like ears, which are positioned high up on [npc.her] head.");
				break;
			case HORSE_MORPH:
				if (owner.isPlayer())
					sb.append(" You have a pair of "+(ear.isPierced() ? "pierced, " : "")+"upright, horse-like ears, which are positioned high up on your head.");
				else
					sb.append(" [npc.She] has a pair of "+(ear.isPierced() ? "pierced, " : "")+"upright, horse-like ears, which are positioned high up on [npc.her] head.");
				break;
			case SLIME:
				if (owner.isPlayer())
					sb.append(" You have a pair of humanoid" + (ear.isPierced() ? ", pierced" : "") + " ears, made out of " + skinTypeColours.get(BodyCoveringType.SLIME).getName()+ " slime.");
				else
					sb.append(" [npc.She] has a pair of humanoid" + (ear.isPierced() ? ", pierced" : "") + " ears, made out of " + skinTypeColours.get(BodyCoveringType.SLIME).getName()+ " slime");
				break;
			case HARPY:
				if (owner.isPlayer())
					sb.append(" Your ears are an internal part of your head, and are covered by a fan of <span style='color:[pc.earColourHex];'>[pc.earColour] feathers</span>."
							+ (ear.isPierced()?" They have been cleverly pierced so as to allow you to wear ear-specific jewellery.":""));
				else
					sb.append(" [npc.Her] ears are an internal part of [npc.her] head, and are covered by a fan of <span style='color:[npc.earColourHex];'>[npc.earColour] feathers</span>."
							+ (ear.isPierced()?" They have been cleverly pierced so as to allow [npc.herHim] to wear ear-specific jewellery.":""));
				break;
			default:
				if (owner.isPlayer())
					sb.append(" You have " + ear.getType().getDeterminer(owner) + " " + (ear.isPierced() ? ", pierced" : "") + " " + ear.getName(owner, true) + ".");
				else
					sb.append(" [npc.She] has " + ear.getType().getDeterminer(owner) + " " + (ear.isPierced() ? ", pierced" : "") + " " + ear.getName(owner, true) + ".");
		}
		
		// Hair & horns:
		if (owner.isPlayer() && hair.getRawLengthValue() == 0) {
			sb.append(" You are completely bald.");
			
		} else if (!owner.isPlayer() && hair.getRawLengthValue() == 0) {
			sb.append(" [npc.She] is completely bald.");
			
		} else {
			switch (hair.getType()) {
				case HAIR_HUMAN:
					if (owner.isPlayer())
						sb.append(" You have [pc.hairLength], <span style='color:[pc.hairColourHex];'>[pc.hairColour] hair</span>");
					else
						sb.append(" [npc.She] has [npc.hairLength], <span style='color:[npc.hairColourHex];'>[npc.hairColour] hair</span>");
					break;
				case HAIR_ANGEL:
					if (owner.isPlayer())
						sb.append(" You have [pc.hairLength], <span style='color:[pc.hairColourHex];'>[pc.hairColour] hair</span>, which gives off a soft golden glow");
					else
						sb.append(" [npc.She] has [npc.hairLength], <span style='color:[npc.hairColourHex];'>[npc.hairColour] hair</span>, which gives off a soft golden glow");
					break;
				case HAIR_CANINE_FUR:
					if (owner.isPlayer())
						sb.append(" You have [pc.hairLength], <span style='color:[pc.hairColourHex];'>[pc.hairColour], fur-like hair</span>");
					else
						sb.append(" [npc.She] has [npc.hairLength], <span style='color:[npc.hairColourHex];'>[npc.hairColour], fur-like hair</span>");
					break;
				case HAIR_LYCAN_FUR:
					if (owner.isPlayer())
						sb.append(" You have [pc.hairLength], <span style='color:[pc.hairColourHex];'>[pc.hairColour], fur-like hair</span>");
					else
						sb.append(" [npc.She] has [npc.hairLength], <span style='color:[npc.hairColourHex];'>[npc.hairColour], fur-like hair</span>");
					break;
				case HAIR_FELINE_FUR:
					if (owner.isPlayer())
						sb.append(" You have [pc.hairLength], <span style='color:[pc.hairColourHex];'>[pc.hairColour], fur-like hair</span>");
					else
						sb.append(" [npc.She] has [npc.hairLength], <span style='color:[npc.hairColourHex];'>[npc.hairColour], fur-like hair</span>");
					break;
				case HAIR_HORSE_HAIR:
					if (owner.isPlayer())
						sb.append(" You have [pc.hairLength], <span style='color:[pc.hairColourHex];'>[pc.hairColour], slightly coarse hair</span>");
					else
						sb.append(" [npc.She] has [npc.hairLength], <span style='color:[npc.hairColourHex];'>[npc.hairColour], slightly coarse hair</span>");
					break;
				case HAIR_SLIME:
					if (owner.isPlayer())
						sb.append(" You have [pc.hairLength], <span style='color:[pc.hairColourHex];'>[pc.hairColour], gooey slime</span> instead of hair");
					else
						sb.append(" [npc.She] has [npc.hairLength], <span style='color:[npc.hairColourHex];'>[npc.hairColour], gooey slime</span> instead of hair");
					break;
				case HAIR_HARPY:
					if (owner.isPlayer())
						sb.append(" You have [pc.a_hairLength], <span style='color:[pc.hairColourHex];'>[pc.hairColour] plume of feathers</span> in place of hair");
					else
						sb.append(" [npc.She] has [npc.a_hairLength], <span style='color:[npc.hairColourHex];'>[npc.hairColour] plume of feathers</span> in place of hair");
					break;
				default:
					if (owner.isPlayer())
						sb.append(" You have " + hair.getLength().getDescriptor() + ", " + skinTypeColours.get(hair.getType()).getName() + " " + hair.getName(owner, true));
					else
						sb.append(" [npc.She] has " + hair.getLength().getDescriptor() + ", " + skinTypeColours.get(hair.getType()).getName() + " " + hair.getName(owner, true));
			}
		}
		switch (horn.getType()) {
			case NONE:
				sb.append(".");
				break;
			case DEMON_COMMON_MALE:
				if (owner.isPlayer())
					sb.append(", and a pair of short, curved horns protrude from your upper forehead.");
				else
					sb.append(", and a pair of short, curved horns protrude from [npc.her] upper forehead.");
				break;
			case DEMON_COMMON_FEMALE:
				if (owner.isPlayer())
					sb.append(", and a pair of long, swept-back horns protrude from your upper forehead.");
				else
					sb.append(", and a pair of long, swept-back horns protrude from [npc.her] upper forehead.");
				break;
			default:
				if (owner.isPlayer())
					sb.append(", and " + horn.getType().getDeterminer(owner) + " " + horn.getName(owner, true) + " protrude from your upper forehead.");
				else
					sb.append(", and " + horn.getType().getDeterminer(owner) + " " + horn.getName(owner, true) + " protrude from [npc.her] upper forehead.");
		}
		switch (face.getMakeupLevel()) {
			case NONE:
				break;
			case MINIMAL:
				if (owner.isPlayer())
					sb.append(" You have an almost-unnoticeable touch of makeup on, covering any slight blemishes on your face.");
				else
					sb.append(" [npc.She] has an almost-unnoticeable touch of makeup on, covering any slight blemishes on [npc.her] face.");
				break;
			case LOW:
				if (owner.isPlayer())
					sb.append(" You have a slight touch of makeup on, covering any blemishes on your face.");
				else
					sb.append(" [npc.She] has a slight touch of makeup on, covering any blemishes on [npc.her] face.");
				break;
			case AVERAGE:
				if (owner.isPlayer())
					sb.append(" You are wearing a tasteful amount of makeup, which elegantly enhances the natural shape of your face.");
				else
					sb.append(" [npc.She] is wearing a tasteful amount of makeup, which elegantly enhances the natural shape of [npc.her] face.");
				break;
			case HIGH:
				if (owner.isPlayer())
					sb.append(" You are wearing a lot of makeup, which borders the upper limits of what would be considered tasteful.");
				else
					sb.append(" [npc.She] is wearing a lot of makeup, which borders the upper limits of what would be considered tasteful.");
				break;
			case VERY_HIGH:
				if (owner.isPlayer())
					sb.append(" Your face is covered in a heavy layer of makeup, which makes you look like a desperate slut on a night out.");
				else
					sb.append(" [npc.Her] face is covered in a heavy layer of makeup, which makes [npc.herHim] look like a desperate slut on a night out.");
				break;
			case EXTREME:
				if (owner.isPlayer())
					sb.append(" Your face is absolutely plastered in makeup, making you look like a cheap whore.");
				else
					sb.append(" [npc.Her] face is absolutely plastered in makeup, making [npc.herHim] look like a cheap whore.");
				break;
			case MAXIMUM:
				if (owner.isPlayer())
					sb.append(" Your face is painted in an obscene amount of makeup, making you look like a comical caricature of a cheap whore.");
				else
					sb.append(" [npc.Her] face is painted in an obscene amount of makeup, making [npc.herHim] look like a comical caricature of a cheap whore.");
				break;
		}
		sb.append("</p>");

		// Describe body & chest:

		if (owner.isPlayer()) {
			sb.append("<p> Your torso, " + (breast.getRawSizeValue() == 0 ? "chest" : breast.getName(owner, false))
					+ " and ass, just like your face, are covered in <span style=color:[pc.skinColourHex];>[pc.skinColour] [pc.skin]</span>");
		} else {
			sb.append("<p> [npc.Her] torso, " + (breast.getRawSizeValue() == 0 ? "chest" : breast.getName(owner, false))
					+ " and ass, just like [npc.her] face, are covered in <span style=color:[npc.skinColourHex];>[npc.skinColour] [npc.skin]</span>");
		}
		
		if (femininity <= Femininity.MASCULINE_STRONG.getMaximumFemininity()) {
			sb.append(
					UtilText.returnStringAtRandom(
							", and together, form an <span style='color:" + Colour.MASCULINE_PLUS.toWebHexString() + ";'>extremely masculine</span> figure.",
							", and together, form a <span style='color:" + Colour.MASCULINE_PLUS.toWebHexString() + ";'>very handsome</span> figure."));
			
		} else if (femininity <= Femininity.MASCULINE.getMaximumFemininity()) {
			sb.append(
					UtilText.returnStringAtRandom(
							", and together, form a <span style='color:" + Colour.MASCULINE.toWebHexString() + ";'>masculine</span> figure.",
							", and together, form a <span style='color:" + Colour.MASCULINE.toWebHexString() + ";'>handsome</span> figure."));
			
		} else if (femininity <= Femininity.ANDROGYNOUS.getMaximumFemininity()) {
			sb.append(", and together, form an <span style='color:" + Colour.ANDROGYNOUS.toWebHexString() + ";'>androgynous</span> figure.");
			
		} else if (femininity <= Femininity.FEMININE.getMaximumFemininity()) {
			sb.append(
					UtilText.returnStringAtRandom(
							", and together, form a <span style='color:" + Colour.FEMININE.toWebHexString() + ";'>feminine</span> figure.",
							", and together, form a <span style='color:" + Colour.FEMININE.toWebHexString() + ";'>pretty</span> figure."));
			
		} else {
			sb.append(
					UtilText.returnStringAtRandom(
							", and together, form an <span style='color:" + Colour.FEMININE_PLUS.toWebHexString() + ";'>extremely beautiful</span> figure.",
							", and together, form a <span style='color:" + Colour.FEMININE_PLUS.toWebHexString() + ";'>gorgeous</span> figure.",
							", and together, form a <span style='color:" + Colour.FEMININE_PLUS.toWebHexString() + ";'>jaw-droppingly beautiful</span> figure."));
		}
		
		// Pregnancy:
		if(owner.hasStatusEffect(StatusEffect.PREGNANT_1)){
			if (owner.isPlayer())
				sb.append(" Your belly is slightly swollen, and it's clear to anyone who takes a closer look that <span style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>you're pregnant</span>.");
			else
				sb.append(" [npc.Her] belly is slightly swollen, and it's clear to anyone who takes a closer look that <span style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc.she]'s pregnant</span>.");
			
		}else if(owner.hasStatusEffect(StatusEffect.PREGNANT_2)){
			if (owner.isPlayer())
				sb.append(" Your belly is heavily swollen, and it's clear to anyone who glances your way that <span style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>you're pregnant</span>.");
			else
				sb.append(" [npc.Her] belly is heavily swollen, and it's clear to anyone who glances [npc.her] way that <span style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc.she]'s pregnant</span>.");
		
		}else if(owner.hasStatusEffect(StatusEffect.PREGNANT_3)){
			if (owner.isPlayer())
				sb.append(" Your belly is massively swollen, and it's completely obvious to anyone who glances your way that"
						+ " <span style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>you're expecting to give birth very soon</span>.");
			else
				sb.append(" [npc.Her] belly is massively swollen, and it's completely obvious to anyone who glances [npc.her] way that"
						+ " <span style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc.she]'s expecting to give birth very soon</span>.");
		}
		
		// Breasts:
		if(owner.isPlayer()){
			if(breast.getRawSizeValue()>0){
				sb.append(" You have " + Util.intToString(breast.getRows()) + " pair" + (breast.getRows() == 1 ? "" : "s") + " of "+breast.getSize().getDescriptor()+" breasts");
				switch(breast.getRows()){
					case 1:
						sb.append(", which fit comfortably into ");
						if (breast.getSize() == CupSize.TRAINING)
							sb.append("a training bra.");
						else
							sb.append((Util.isVowel(breast.getSize().getCupSizeName().charAt(0)) ? "an" : "a") + " " + breast.getSize().getCupSizeName() + "-cup bra.");
						break;
						
					case 2:
						sb.append(". Your top pair of breasts fit comfortably into ");
						if (breast.getSize() == CupSize.TRAINING)
							sb.append("a training bra,");
						else
							sb.append((Util.isVowel(breast.getSize().getCupSizeName().charAt(0)) ? "an" : "a") + " " + breast.getSize().getCupSizeName() + "-cup bra,");
						sb.append(" and the pair below them fit into ");
						if (CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-1) == CupSize.TRAINING)
							sb.append("a training bra.");
						else
							sb.append((Util.isVowel(CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-1).getCupSizeName().charAt(0)) ? "an" : "a")
									+ " " + CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-1).getCupSizeName() + "-cup bra.");
						break;
						
					case 3:
						sb.append(". Your top pair of breasts fit comfortably into ");
						if (breast.getSize() == CupSize.TRAINING)
							sb.append("a training bra,");
						else
							sb.append((Util.isVowel(breast.getSize().getCupSizeName().charAt(0)) ? "an" : "a") + " " + breast.getSize().getCupSizeName() + "-cup bra,");
						sb.append(" your second pair into ");
						if (CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-1) == CupSize.TRAINING)
							sb.append("a training bra,");
						else
							sb.append((Util.isVowel(CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-1).getCupSizeName().charAt(0)) ? "an" : "a")
									+ " " + CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-1).getCupSizeName() + "-cup bra,");
						sb.append(" and your third, lowest pair fit into ");
						if (CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-2) == CupSize.TRAINING)
							sb.append("a training bra.");
						else
							sb.append((Util.isVowel(CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-2).getCupSizeName().charAt(0)) ? "an" : "a")
									+ " " + CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-2).getCupSizeName() + "-cup bra.");
						break;
						
					default:break;
				}
				if(breast.isPierced()) {
					sb.append(" Your nipples have been pierced.");
				}
				
			}else{
				sb.append(" You have a flat chest");
				switch(breast.getRows()){
					case 1:
						sb.append(", with a single pair of "+(breast.isPierced()?"pierced ":"")+"nipples.");
						break;
					case 2:
						sb.append(", with two pairs of "+(breast.isPierced()?"pierced ":"")+"nipples.");
						break;
					case 3:
						sb.append(", with three pairs of "+(breast.isPierced()?"pierced ":"")+"nipples.");
						break;
					default:
						break;
				}
			}
		}else{
			if(breast.getRawSizeValue()>0){
				sb.append(" [npc.She] has " + Util.intToString(breast.getRows()) + " pair" + (breast.getRows() == 1 ? "" : "s") + " of "+breast.getSize().getDescriptor()+" breasts");
				switch(breast.getRows()){
					case 1:
						sb.append(", which fit comfortably into ");
						if (breast.getSize() == CupSize.TRAINING)
							sb.append("a training bra.");
						else
							sb.append((Util.isVowel(breast.getSize().getCupSizeName().charAt(0)) ? "an" : "a") + " " + breast.getSize().getCupSizeName() + "-cup bra.");
						break;
						
					case 2:
						sb.append(". [npc.Her] top pair of breasts fit comfortably into ");
						if (breast.getSize() == CupSize.TRAINING)
							sb.append("a training bra,");
						else
							sb.append((Util.isVowel(breast.getSize().getCupSizeName().charAt(0)) ? "an" : "a") + " " + breast.getSize().getCupSizeName() + "-cup bra,");
						sb.append(" and the pair below them fit into ");
						if (CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-1) == CupSize.TRAINING)
							sb.append("a training bra.");
						else
							sb.append((Util.isVowel(CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-1).getCupSizeName().charAt(0)) ? "an" : "a")
									+ " " + CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-1).getCupSizeName() + "-cup bra.");
						break;
						
					case 3:
						sb.append(". [npc.Her] top pair of breasts fit comfortably into ");
						if (breast.getSize() == CupSize.TRAINING)
							sb.append("a training bra,");
						else
							sb.append((Util.isVowel(breast.getSize().getCupSizeName().charAt(0)) ? "an" : "a") + " " + breast.getSize().getCupSizeName() + "-cup bra,");
						sb.append(" [npc.her] second pair into ");
						if (CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-1) == CupSize.TRAINING)
							sb.append("a training bra,");
						else
							sb.append((Util.isVowel(CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-1).getCupSizeName().charAt(0)) ? "an" : "a")
									+ " " + CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-1).getCupSizeName() + "-cup bra,");
						sb.append(" and [npc.her] third, lowest pair fit into ");
						if (CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-2) == CupSize.TRAINING)
							sb.append("a training bra,");
						else
							sb.append((Util.isVowel(CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-2).getCupSizeName().charAt(0)) ? "an" : "a")
									+ " " + CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-2).getCupSizeName() + "-cup bra,");
						break;
						
					default:break;
				}
				if(breast.isPierced()) {
					sb.append(" [npc.Her] nipples have been pierced.");
				}
			}else{
				sb.append(" [npc.She] has a flat chest");
				switch(breast.getRows()){
					case 1:
						sb.append(", with a single pair of "+(breast.isPierced()?"pierced ":"")+"nipples.");
						break;
						
					case 2:
						sb.append(", with two pairs of "+(breast.isPierced()?"pierced ":"")+"nipples.");
						break;
						
					case 3:
						sb.append(", with three pairs of "+(breast.isPierced()?"pierced ":"")+"nipples.");
						break;
						
					default:break;
				}
			}
		}
		
		sb.append(" " + getBreastDescription(owner));
		sb.append("</p>");

		// Arms and legs:

		sb.append("<p>");
		// Arms:
		switch (arm.getType()) {
			case HUMAN:
				if (owner.isPlayer())
					sb.append("You have a pair of normal human arms and hands, which are covered in <span style='color:[pc.armColourHex];'>[pc.armColour] [pc.armSkin]</span>.");
				else
					sb.append("[npc.She] has a pair of normal human arms and hands, which are covered in <span style='color:[npc.armColourHex];'>[npc.armColour] [npc.armSkin]</span>.");
				break;
			case ANGEL:
				if (owner.isPlayer())
					sb.append("You have a pair of human-like arms and hands, which are covered in <span style='color:[pc.armColourHex];'>[pc.armColour] [pc.armSkin]</span>, and seem to radiate a soft golden glow.");
				else
					sb.append("[npc.She] has a pair of human-like arms and hands, which are covered in <span style='color:[npc.armColourHex];'>[npc.armColour] [npc.armSkin]</span>, and seem to radiate a soft golden glow.");
				break;
			case DEMON_COMMON:
				if (owner.isPlayer())
					sb.append("You have a pair of slender, human-looking arms and hands, which are covered in <span style='color:[pc.armColourHex];'>[pc.armColour] [pc.armSkin]</span>.");
				else
					sb.append("[npc.She] has a pair of slender human-looking arms and hands, which are covered in <span style='color:[npc.armColourHex];'>[npc.armColour] [npc.armSkin]</span>.");
				break;
			case DOG_MORPH:
				if (owner.isPlayer())
					sb.append("Your arms are covered in <span style='color:[pc.armColourHex];'>[pc.armColour] [pc.armSkin]</span>,"
							+ " and your hands are formed into anthropomorphic, dog-like hands, complete with little blunt claws and leathery pads.");
				else
					sb.append("[npc.Her] arms are covered in <span style='color:[npc.armColourHex];'>[npc.armColour] [npc.armSkin]</span>,"
							+ " and [npc.her] hands are formed into anthropomorphic, dog-like hands, complete with little blunt claws and leathery pads.");
				break;
			case LYCAN:
				if (owner.isPlayer())
					sb.append("Your arms are covered in <span style='color:[pc.armColourHex];'>[pc.armColour] [pc.armSkin]</span>,"
							+ " and your hands are formed into anthropomorphic, wolf-like hands, complete with sharp claws and tough leathery pads.");
				else
					sb.append("[npc.Her] arms are covered in <span style='color:[npc.armColourHex];'>[npc.armColour] [npc.armSkin]</span>,"
							+ " and [npc.her] hands are formed into anthropomorphic, wolf-like hands, complete with sharp claws and tough leathery pads.");
				break;
			case CAT_MORPH:
				if (owner.isPlayer())
					sb.append("Your arms are covered in <span style='color:[pc.armColourHex];'>[pc.armColour] [pc.armSkin]</span>,"
							+ " and your hands are formed into anthropomorphic, cat-like hands, complete with retractable claws and pink pads.");
				else
					sb.append("[npc.Her] arms are covered in <span style='color:[npc.armColourHex];'>[npc.armColour] [npc.armSkin]</span>,"
							+ " and [npc.her] hands are formed into anthropomorphic, cat-like hands, complete with retractable claws and pink pads.");
				break;
			case HORSE_MORPH:
				if (owner.isPlayer())
					sb.append("Your arms are covered in <span style='color:[pc.armColourHex];'>[pc.armColour] [pc.armSkin]</span>,"
							+ " and your hands, while human in shape, have tough little hoof-like nails.");
				else
					sb.append("[npc.Her] arms are covered in <span style='color:[npc.armColourHex];'>[npc.armColour] [npc.armSkin]</span>,"
							+ " and [npc.her] hands, while human in shape, have tough little hoof-like nails.");
				break;
			case SLIME:
				if (owner.isPlayer())
					sb.append("Your arms, although human-shaped, are formed out of <span style='color:[pc.armColourHex];'>[pc.armColour] [pc.armSkin]</span>.");
				else
					sb.append("[npc.Her] arms, although human-shaped, are formed out of <span style='color:[npc.armColourHex];'>[npc.armColour] [npc.armSkin]</span>.");
				break;
			case HARPY:
				if (owner.isPlayer())
					sb.append("Your arms and hands have transformed into huge wings, and are covered in beautiful <span style='color:[pc.armColourHex];'>[pc.armColour] [pc.armSkin]</span>."
							+ " Where your hands should be, you have a single clawed thumb. Thankfully, you are still able to wrap your wings around objects to form a hand-like grip.");
				else
					sb.append("In place of arms and hands, [npc.she] has a pair of huge wings, which are covered in beautiful <span style='color:[npc.armColourHex];'>[npc.armColour] [npc.armSkin]</span>."
							+ " Where [npc.her] hands should be, [npc.she] has a single clawed thumb, which, when [npc.she] wraps [npc.her] wings around, can grasp objects in a hand-like grip.");
				break;
			default:
				break;
		}
		sb.append(" ");
		// Legs:
		switch (leg.getType()) {
			case HUMAN:
				if (owner.isPlayer())
					sb.append("You have a pair of human legs and feet, which are covered in <span style='color:[pc.legColourHex];'>[pc.legColour] [pc.legSkin]</span>.");
				else
					sb.append("[npc.Her] legs and feet are human, and are covered in <span style='color:[npc.legColourHex];'>[npc.legColour] [npc.legSkin]</span>.");
				break;
			case ANGEL:
				if (owner.isPlayer())
					sb.append("Your legs and feet, while appearing to be human, give off a soft golden glow, and are covered in <span style='color:[pc.legColourHex];'>[pc.legColour] [pc.legSkin]</span>.");
				else
					sb.append("[npc.Her] legs and feet, while appearing to be human, give off a soft golden glow, and are covered in <span style='color:[npc.legColourHex];'>[npc.legColour] [npc.legSkin]</span>.");
				break;
			case DEMON_COMMON:
				if (owner.isPlayer())
					sb.append("Your legs and feet are human in shape, but are covered in <span style='color:[pc.legColourHex];'>[pc.legColour] [pc.legSkin]</span>.");
				else
					sb.append("[npc.Her] legs and feet are human in shape, but are covered in <span style='color:[npc.legColourHex];'>[npc.legColour] [npc.legSkin]</span>.");
				break;
			case DOG_MORPH:
				if (owner.isPlayer())
					sb.append("Your legs are covered in <span style='color:[pc.legColourHex];'>[pc.legColour] [pc.legSkin]</span>,"
							+ " and your feet are formed into anthropomorphic dog-like paws, complete with little blunt claws and leathery pads.");
				else
					sb.append("[npc.Her] legs are covered in <span style='color:[npc.legColourHex];'>[npc.legColour] [npc.legSkin]</span>,"
							+ " and [npc.her] feet are formed into anthropomorphic dog-like paws, complete with little blunt claws and leathery pads.");
				break;
			case LYCAN:
				if (owner.isPlayer())
					sb.append("Your legs are covered in <span style='color:[pc.legColourHex];'>[pc.legColour] [pc.legSkin]</span>,"
							+ " and your feet are formed into anthropomorphic wolf-like paws, complete with sharp claws and tough leathery pads.");
				else
					sb.append("[npc.Her] legs are covered in <span style='color:[npc.legColourHex];'>[npc.legColour] [npc.legSkin]</span>,"
							+ " and [npc.her] feet are formed into anthropomorphic wolf-like paws, complete with sharp claws and tough leathery pads.");
				break;
			case CAT_MORPH:
				if (owner.isPlayer())
					sb.append("Your legs are covered in <span style='color:[pc.legColourHex];'>[pc.legColour] [pc.legSkin]</span>,"
							+ " and your feet are formed into anthropomorphic cat-like paws, complete with retractable claws and pink pads.");
				else
					sb.append("[npc.Her] legs are covered in <span style='color:[npc.legColourHex];'>[npc.legColour] [npc.legSkin]</span>,"
							+ " and [npc.her] feet are formed into anthropomorphic cat-like paws, complete with retractable claws and pink pads.");
				break;
			case HORSE_MORPH:
				if (owner.isPlayer())
					sb.append("Your legs are covered in <span style='color:[pc.legColourHex];'>[pc.legColour] [pc.legSkin]</span>,"
							+ " and your feet are formed into anthropomorphic horse-like hooves.");
				else
					sb.append("[npc.Her] legs are covered in <span style='color:[npc.legColourHex];'>[npc.legColour] [npc.legSkin]</span>,"
							+ " and [npc.her] feet are formed into anthropomorphic horse-like hooves.");
				break;
			case SLIME:
				if (owner.isPlayer())
					sb.append("Your legs, although human-shaped, are made out of <span style='color:[pc.legColourHex];'>[pc.legColour] [pc.legSkin]</span>.");
				else
					sb.append("[npc.Her] legs, although human-shaped, are made out of <span style='color:[npc.legColourHex];'>[npc.legColour] [npc.legSkin]</span>.");
				break;
			case HARPY:
				if (owner.isPlayer())
					sb.append("Your upper thighs are covered in <span style='color:[pc.legColourHex];'>[pc.legColour] [pc.legSkin]</span>, which transition into leathery bird-like skin just above your knee."
							+ " While your legs still retain a human-like shape, your feet have transformed into bird-like talons.");
				else
					sb.append("[npc.Her] upper thighs are covered in <span style='color:[npc.legColourHex];'>[npc.legColour] [npc.legSkin]</span>, which transition into leathery bird-like skin just above [npc.her] knee."
							+ " While [npc.her] legs still retain a human-like shape, [npc.her] feet have transformed into bird-like talons.");
				break;
			default:
				break;
		}
		
		if (owner.isPlayer()) {
			sb.append(" All of your limbs ");
		} else {
			sb.append(" All of [npc.her] limbs ");
		}
		
		if (femininity <= Femininity.MASCULINE_STRONG.getMaximumFemininity()) {
			sb.append(
					UtilText.returnStringAtRandom(
							"<span style='color:" + Colour.MASCULINE_PLUS.toWebHexString() + ";'>have a very masculine shape to them</span>."));
			
		} else if (femininity <= Femininity.MASCULINE.getMaximumFemininity()) {
			sb.append(
					UtilText.returnStringAtRandom(
							"<span style='color:" + Colour.MASCULINE.toWebHexString() + ";'>have a masculine shape to them</span>."));
			
		} else if (femininity <= Femininity.ANDROGYNOUS.getMaximumFemininity()) {
			sb.append("<span style='color:" + Colour.ANDROGYNOUS.toWebHexString() + ";'>look quite androgynous, and could easily belong to either a male or female</span>.");
			
		} else if (femininity <= Femininity.FEMININE.getMaximumFemininity()) {
			sb.append(
					UtilText.returnStringAtRandom(
							"<span style='color:" + Colour.FEMININE.toWebHexString() + ";'>are slender and feminine-looking</span>."));
			
		} else {
			sb.append(
					UtilText.returnStringAtRandom(
							"<span style='color:" + Colour.FEMININE_PLUS.toWebHexString() + ";'>have an extremely feminine shape to them</span>."));
		}
		
		
		sb.append("</p>");

		// Tail, wings & ass:
		sb.append("<p>");
		// Wing:
		switch (wing.getType()) {
			case DEMON_COMMON:
				if (owner.isPlayer())
					sb.append("Growing from your shoulder-blades, you have a pair of tiny bat-like leathery wings. They aren't large enough to allow you to fly, but they do look quite cute.");
				else
					sb.append("Growing from [npc.her] shoulder-blades, [npc.she] has a pair of tiny bat-like leathery wings. They aren't large enough to allow [npc.her] to fly, but they do look quite cute.");
				break;
			case ANGEL:
				if (owner.isPlayer())
					sb.append("Growing from your shoulder-blades, you have a huge pair of white feathered wings. They are so large that they can be used to allow you to fly.");
				else
					sb.append("Growing from [npc.her] shoulder-blades, [npc.she] has a huge pair of white feathered wings. They are so large that they can be used to allow [npc.her] to fly.");
				break;
			default:
				break;
		}
		// Tail:
		switch (tail.getType()) {
			case DEMON_COMMON:
				if (owner.isPlayer())
					sb.append(" A long, thin, spaded tail grows out from just above your ass. You have complete control over it, and you can easily use it to grip and hold objects.");
				else
					sb.append(" A long, thin, spaded tail grows out from just above [npc.her] ass. [npc.She] appears to have complete control over it, and could easily use it to grip and hold objects.");
				break;
			case DOG_MORPH:
				if (owner.isPlayer())
					sb.append(" A furry, <span style='color:[pc.tailColourHex];'>[pc.tailColour], dog-like tail</span> grows out from just above your ass. It wags uncontrollably when you get excited.");
				else
					sb.append(" A furry, <span style='color:[npc.tailColourHex];'>[npc.tailColour], dog-like tail</span> grows out from just above [npc.her] ass. It wags uncontrollably when [npc.she] gets excited.");
				break;
			case LYCAN:
				if (owner.isPlayer())
					sb.append(" A furry, <span style='color:[pc.tailColourHex];'>[pc.tailColour], wolf-like tail</span> grows out from just above your ass.");
				else
					sb.append(" A furry, <span style='color:[npc.tailColourHex];'>[npc.tailColour], wolf-like tail</span> tail grows out from just above [npc.her] ass.");
				break;
			case CAT_MORPH:
				if (owner.isPlayer())
					sb.append(" A furry, <span style='color:[pc.tailColourHex];'>[pc.tailColour], cat-like tail</span> grows out from just above your ass."
							+ " You can control it well enough to grant you significantly improved balance.");
				else
					sb.append(" A furry, <span style='color:[npc.tailColourHex];'>[npc.tailColour], cat-like tail</span> grows out from just above [npc.her] ass."
							+ " [npc.She] seems to be able to control it enough to grant [npc.her] significantly improved balance.");
				break;
			case HORSE_MORPH:
				if (owner.isPlayer())
					sb.append(" A long, <span style='color:[pc.tailColourHex];'>[pc.tailColour], horse-like tail</span> grows out from just above your ass."
							+ " You can swipe it from side to side, but other than that, you don't have much control over it.");
				else
					sb.append(" A long, <span style='color:[npc.tailColourHex];'>[npc.tailColour], horse-like tail</span> grows out from just above [npc.her] ass."
							+ " [npc.She] can swipe it from side to side, but other than that, [npc.she] doesn't seem to have much  control over it.");
				break;
			case HARPY:
				if (owner.isPlayer())
					sb.append(" A beautiful <span style='color:[pc.tailColourHex];'> plume of [pc.tailColour] tail-feathers</span> grows out from just above your ass."
							+ " You can rapidly move it up and down to help you keep your balance and to control your path when in flight.");
				else
					sb.append(" A beautiful <span style='color:[npc.tailColourHex];'> plume of [npc.tailColour] tail-feathers</span> grows out from just above [npc.her] ass."
							+ " [npc.She] can rapidly move it up and down to help [npc.herHim] keep [npc.her] balance and to control [npc.her] path when in flight.");
				break;
			default:
				break;
		}
		// Ass & hips:
		if (owner.isPlayer()) {
			sb.append(" Like the rest of your torso, your [pc.hips+] and [pc.assSize] [pc.ass] are covered in <span style='color:[pc.assColourHex];'> [pc.assColour] [pc.assSkin]</span>.");
		} else {
			sb.append(" Like the rest of [npc.her] torso, [npc.her] [npc.hips+] and [npc.assSize] [npc.ass] are covered in <span style='color:[npc.assColourHex];'> [npc.assColour] [npc.assSkin]</span>.");
		}
		
		if(owner.getPlayerKnowsAreasMap().get(CoverableArea.ANUS)) {
			sb.append(" " + getAssDescription(owner));
			sb.append("</p>");
		} else {
			sb.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You haven't seen [npc.her] naked ass before, so you don't know what [npc.her] asshole looks like.</span>");
			sb.append("</p>");
		}
		
		if(owner.getPlayerKnowsAreasMap().get(CoverableArea.VAGINA) && owner.getPlayerKnowsAreasMap().get(CoverableArea.PENIS)) {
			// Vagina, virgin/capacity, wetness:
			if (vagina.getType() == VaginaType.NONE && penis.getType() == PenisType.NONE) {
				sb.append("<p>" + getMoundDescription(owner) + "</p>");
			}
		} 
		
		if(owner.getPlayerKnowsAreasMap().get(CoverableArea.PENIS)) {
			// Penises, cum production, testicle size, capacity:
			if (penis.getType() != PenisType.NONE) {
				sb.append("<p>" + getPenisDescription(owner) + "</p>");
			}
		} else {
			sb.append(" <p>"
						+ "<span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You haven't seen [npc.her] naked groin before, so you don't know what [npc.her] cock looks like, or even if [npc.she] has one.</span>"
					+ "</p>");
		}
		
		if(owner.getPlayerKnowsAreasMap().get(CoverableArea.VAGINA)) {
			// Vagina, virgin/capacity, wetness:
			if (vagina.getType() != VaginaType.NONE) {
				sb.append("<p>" + getVaginaDescription(owner) + "</p>");
			}
		} else {
			sb.append(" <p>"
						+ "<span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You haven't seen [npc.her] naked groin before, so you don't know what [npc.her] pussy looks like, or even if [npc.she] has one.</span>"
					+ "</p>");
		}
		
		
		
		if(!owner.isPlayer()) {
			sb.append(getSexDetails(owner));
			sb.append(getPregnancyDetails(owner));
		}

		return UtilText.parse(owner, sb.toString());
	}

	/** To be called after every transformation. Returns the body's race. */
	public void calculateRace() {

		if (skin.getType().getRace() != Race.HUMAN) {
			this.race = skin.getType().getRace();
			raceStage = RaceStage.GREATER;

		} else if (face.getType().getRace() != Race.HUMAN) {
			this.race = face.getType().getRace();
			raceStage = RaceStage.GREATER;

		} else if (arm.getType().getRace() != Race.HUMAN) {
			this.race = arm.getType().getRace();
			raceStage = RaceStage.LESSER;

		}  else if (leg.getType().getRace() != Race.HUMAN) {
			this.race = leg.getType().getRace();
			raceStage = RaceStage.LESSER;

		} else {
			
			int leaderNonHumanParts = 0;
			this.race = Race.HUMAN;
			raceStage = RaceStage.HUMAN;
			// Check to see if the body is a partial morph:
			for (Race r : Race.values()) {
				int currentParts = 0, nonHumanParts = 0;

				// if(r!=Race.HUMAN){
				if (ass.getType() == RacialBody.valueOfRace(r).getAssType()) {
					currentParts++;
					if (ass.getType() != RacialBody.valueOfRace(Race.HUMAN).getAssType())
						nonHumanParts++;
				}

				if (breast.getType() == RacialBody.valueOfRace(r).getBreastType()) {
					currentParts++;
					if (breast.getType() != RacialBody.valueOfRace(Race.HUMAN).getBreastType())
						nonHumanParts++;
				}

				if (eye.getType() == RacialBody.valueOfRace(r).getEyeType()) {
					currentParts++;
					if (eye.getType() != RacialBody.valueOfRace(Race.HUMAN).getEyeType())
						nonHumanParts++;
				}

				if (ear.getType() == RacialBody.valueOfRace(r).getEarType()) {
					currentParts++;
					if (ear.getType() != RacialBody.valueOfRace(Race.HUMAN).getEarType())
						nonHumanParts++;
				}

				if (hair.getType() == RacialBody.valueOfRace(r).getHairType()) {
					currentParts++;
					if (hair.getType() != RacialBody.valueOfRace(Race.HUMAN).getHairType())
						nonHumanParts++;
				}

				if (horn.getType() == RacialBody.valueOfRace(r).getHornTypeFemale() || horn.getType() == RacialBody.valueOfRace(r).getHornTypeMale()) {
					currentParts++;
					if (horn.getType() != RacialBody.valueOfRace(Race.HUMAN).getHornTypeMale())
						nonHumanParts++;
				}

				if (tail.getType() == RacialBody.valueOfRace(r).getTailType()) {
					currentParts++;
					if (tail.getType() != RacialBody.valueOfRace(Race.HUMAN).getTailType())
						nonHumanParts++;
				}

				if (wing.getType() == RacialBody.valueOfRace(r).getWingType()) {
					currentParts++;
					if (wing.getType() != RacialBody.valueOfRace(Race.HUMAN).getWingType())
						nonHumanParts++;
				}

				if (penis.getType() == RacialBody.valueOfRace(r).getPenisType()
						&& penis.getType() != RacialBody.valueOfRace(Race.HUMAN).getPenisType()
						&& penis.getType() != PenisType.NONE)
					nonHumanParts++;

				if (vagina.getType() == RacialBody.valueOfRace(r).getVaginaType()
						&& vagina.getType() != RacialBody.valueOfRace(Race.HUMAN).getVaginaType()
						&& vagina.getType() != VaginaType.NONE)
					nonHumanParts++;

				// }

				if (r != Race.HUMAN) {
					if (nonHumanParts > leaderNonHumanParts) {
						this.race = r;
						if (currentParts == 8)
							raceStage = RaceStage.PARTIAL_FULL;
						else
							raceStage = RaceStage.PARTIAL;
						leaderNonHumanParts = nonHumanParts;
					}
				}
//				else if (currentParts == 8) {
//					this.race = r;
//				}
			}

		}
	}

	public Race getRace() {
		return race;
	}

	public RaceStage getRaceStage() {
		return raceStage;
	}

	public Arm getArm() {
		return arm;
	}

	public Ass getAss() {
		return ass;
	}

	public Breast getBreast() {
		return breast;
	}

	public Face getFace() {
		return face;
	}

	public Eye getEye() {
		return eye;
	}

	public Ear getEar() {
		return ear;
	}

	public Hair getHair() {
		return hair;
	}

	public Horn getHorn() {
		return horn;
	}

	public Leg getLeg() {
		return leg;
	}

	public Penis getPenis() {
		return penis;
	}

	public Skin getSkin() {
		return skin;
	}

	public Tail getTail() {
		return tail;
	}

	public Vagina getVagina() {
		return vagina;
	}

	public Wing getWing() {
		return wing;
	}

	// Descriptions:
	private StringBuilder descriptionSB;

	public String getAssDescription(GameCharacter owner) {
		descriptionSB = new StringBuilder();
		
		boolean isPlayer = owner.isPlayer();
		
		switch (ass.getType()) {
			case HUMAN:
				if (isPlayer) 
					descriptionSB.append("You have a normal, human-looking asshole.");
				else
					descriptionSB.append("[npc.She] has a normal, human-looking asshole.");
				break;
				
			case DEMON_COMMON:
				if (isPlayer) 
					descriptionSB.append("Your asshole has transformed into a pussy-like orifice, lined with gripping muscles that can squeeze and milk any cock that finds its way inside.");
				else
					descriptionSB.append("[npc.Her] asshole is formed into a pussy-like orifice, lined with gripping muscles that can squeeze and milk any cock that finds its way inside.");
				break;
				
			case DOG_MORPH:
				if (isPlayer) 
					descriptionSB.append("You have a canine asshole, which looks very similar to that of a human's.");
				 else
					descriptionSB.append("[npc.She] has a canine asshole, which looks very similar to that of a human's.");
				break;
				
			case WOLF_MORPH:
				if (isPlayer) 
					descriptionSB.append("You have a lupine asshole, which looks very similar to that of a human's.");
				 else
					descriptionSB.append("[npc.She] has a lupine asshole, which looks very similar to that of a human's.");
				break;
				
			case CAT_MORPH:
				if (isPlayer) 
					descriptionSB.append("You have a feline asshole, which looks very similar to that of a human's.");
				 else
					descriptionSB.append("[npc.She] has a feline asshole, which looks very similar to that of a human's.");
				break;
				
			case HORSE_MORPH:
				if (isPlayer) 
					descriptionSB.append("Your rear entrance has transformed into a black, puffy, horse-like asshole.");
				 else
					descriptionSB.append("[npc.Her] rear entrance is formed into a black, puffy, horse-like asshole.");
				break;
				
			case HARPY:
				if (isPlayer) 
					descriptionSB.append("You have an avian asshole, which looks very similar to that of a human's.");
				 else
					descriptionSB.append("[npc.She] has an avian asshole, which looks very similar to that of a human's.");
				break;
				
			case SLIME:
				if (isPlayer)
					descriptionSB.append("Your asshole has transformed to be made completely out of " + skinTypeColours.get(BodyCoveringType.SLIME).getName() + " slime.");
				else
					descriptionSB.append("[npc.Her] asshole is made completely out of " + skinTypeColours.get(BodyCoveringType.SLIME).getName() + " slime.");
				break;
			default:
				break;
		}

		descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It is "+Capacity.getCapacityFromValue(ass.getStretchedCapacity()).getDescriptor()+", and can comfortably take "
				+ Capacity.getCapacityFromValue(ass.getStretchedCapacity()).getMaximumSizeComfortableWithLube().getDescriptor() + " cocks with enough lube.</span>");
		
		if (isPlayer) {
			if (ass.isVirgin()){
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>You have retained your anal virginity.</span>");
			}else{
				for(PenetrationType pt : PenetrationType.values()) {
					if(Main.game.getPlayer().getStats().getVirginityLoss(new SexType(pt, OrificeType.ANUS_PLAYER))!=null) {
						descriptionSB.append(" <span style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>You lost your anal virginity to "
								+ Main.game.getPlayer().getStats().getVirginityLoss(new SexType(pt, OrificeType.ANUS_PLAYER)) + ".</span>");
					}
				}
			}
		}
		// Ass wetness:
		switch (ass.getWetness()) {
		case ZERO_DRY:
			descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It is completely dry, and would need lubricating before sex.</span>");
			break;
		case ONE_SLIGHTLY_MOIST:
			descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It is slightly moist, but would still need lubricating before sex.</span>");
			break;
		case TWO_MOIST:
			descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It is moist, but would still need lubricating before sex.</span>");
			break;
		case THREE_WET:
			descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Its surface constantly beads with wet droplets, which provides enough natural lubrication for sex.</span>");
			break;
		case FOUR_SLIMY:
			descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Its surface is always slimy and ready for penetration.</span>");
			break;
		case FIVE_SLOPPY:
			descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Its surface is always slimy, and the interior is constantly sloppy and ready for sex.</span>");
			break;
		case SIX_SOPPING_WET:
			descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Its constantly sopping wet from natural lubrication and ready for penetration.</span>");
			break;
		case SEVEN_DROOLING:
			descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It constantly drools with natural lubrication, and its sopping wet entrance is always ready for penetration.</span>");
			break;
		default:
			break;
		}
		// Ass plasticity:
		switch (ass.getElasticity()) {
			case ZERO_UNYIELDING:
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It is extremely unyielding, but when it does get stretched out, it stays that way.</span>");
				break;
			case ONE_RIGID:
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It takes a huge amount of effort to stretch it out, but when it does, it loses a good portion of its original tightness.</span>");
				break;
			case TWO_FIRM:
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It does not stretch very easily, but when it does, it struggles to return to its original size.</span>");
				break;
			case THREE_FLEXIBLE:
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It reluctantly stretches out when used as a sexual orifice, and takes a while to return to its original size.</span>");
				break;
			case FOUR_LIMBER:
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It is somewhat resistant to being stretched out, but will return to its original size after a couple of days.</span>");
				break;
			case FIVE_STRETCHY:
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It stretches out fairly easily, and returns to its original size within a day or so.</span>");
				break;
			case SIX_SUPPLE:
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It stretches out very easily, and returns to its original size within a matter of hours.</span>");
				break;
			case SEVEN_ELASTIC:
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It is extremely elastic, and can be comfortably stretched out before instantly returning to its original size.</span>");
				break;
			default:
				break;
		}
		
		return UtilText.parse(owner, descriptionSB.toString());
	}

	public String getBreastDescription(GameCharacter owner) {
		descriptionSB = new StringBuilder();
		
		boolean isPlayer = owner.isPlayer();
		boolean playerKnowledgeOfBreasts = owner.getPlayerKnowsAreasMap().get(CoverableArea.NIPPLES);
		
		// Capacity and nipple appearance:
		switch (breast.getType()) {
			case HUMAN:
				if (isPlayer) {
					descriptionSB.append("Your nipples are normal, human-looking nubs that are surrounded by " + (breast.getRawSizeValue() == 0 ? "tiny" : "delicate") + " areolae.");
				} else if (playerKnowledgeOfBreasts) {
						descriptionSB.append("[npc.Her] nipples are normal, human-looking nubs that are surrounded by [npc.her] " + (breast.getRawSizeValue() == 0 ? "tiny" : "delicate") + " areolae.");
				}
				break;
				
			case DEMON_COMMON:
				if (isPlayer) {
					descriptionSB.append("Your nipples have formed into demonic little pseudo-pussies, which invitingly drool out a slimy milk-like liquid whenever you get aroused.");
					if (breast.getRawCapacityValue() == 0)
						descriptionSB.append(" You feel disappointed that despite their appearance, <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>your nipples are far too tight for anything to fit into them</span>.");
					
				} else if (playerKnowledgeOfBreasts) {
						descriptionSB.append("[npc.Her] nipples are formed into demonic little pseudo-pussies, which invitingly drool out a slimy milk-like liquid whenever [npc.she] gets aroused.");
						if (breast.getRawCapacityValue() == 0)
							descriptionSB.append(" Despite their appearance, <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.her] nipples are far too tight for anything to fit into them</span>.");
				}
				break;
				
			case DOG_MORPH:
				if (isPlayer) {
					descriptionSB.append("Your canine nipples look very similar to those found on a human, with the main difference being that they produce canine milk when lactating.");
				} else if (playerKnowledgeOfBreasts) {
						descriptionSB.append("[npc.Her] canine nipples look very similar to those found on a human, with the main difference being that they produce canine milk when lactating.");
				}
				break;
				
			case WOLF_MORPH:
				if (isPlayer) {
					descriptionSB.append("Your lupine nipples look very similar to those found on a human, with the main difference being that they produce lupine milk when lactating.");
				} else if (playerKnowledgeOfBreasts) {
						descriptionSB.append("[npc.Her] lupine nipples look very similar to those found on a human, with the main difference being that they produce lupine milk when lactating.");
				}
				break;
				
			case CAT_MORPH:
				if (isPlayer) {
					descriptionSB.append("Your feline nipples look very similar to those found on a human, with the main difference being that they produce feline milk when lactating.");
				} else if (playerKnowledgeOfBreasts) {
						descriptionSB.append("[npc.Her] feline nipples look very similar to those found on a human, with the main difference being that they produce feline milk when lactating.");
				}
				break;
				
			case HORSE_MORPH:
				if (isPlayer) {
					descriptionSB.append("Your equine nipples look very similar to those found on a human, with the main difference being that they produce equine milk when lactating.");
				} else if (playerKnowledgeOfBreasts) {
						descriptionSB.append("[npc.Her] equine nipples look very similar to those found on a human, with the main difference being that they produce equine milk when lactating.");
				}
				break;
				
			case HARPY:
				if (isPlayer) {
					descriptionSB.append("Your avian nipples look very similar to those found on a human, with the main difference being that they produce avian milk when lactating.");
				} else if (playerKnowledgeOfBreasts) {
						descriptionSB.append("[npc.Her] avian nipples look very similar to those found on a human, with the main difference being that they produce avian milk when lactating.");
				}
				break;
				
			case SLIME:
				if (isPlayer) {
					descriptionSB.append("Your nipples have shifted into little nubs of " + skinTypeColours.get(BodyCoveringType.SLIME) + " slime.");
				} else {
					if (playerKnowledgeOfBreasts) {
						descriptionSB.append("[npc.Her] nipples are little nubs of " + skinTypeColours.get(BodyCoveringType.SLIME) + " slime.");
					} else
						descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You've never seen [npc.her] naked chest, so you don't know what [npc.her] nipples look like.</span>");
				}
				break;
			default:
				break;
		}
		if(!isPlayer && !playerKnowledgeOfBreasts) {
			descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You've never seen [npc.her] naked chest, so you don't know what [npc.her] nipples look like.</span>");
		}
		
		if(isPlayer){
			if (breast.isFuckable())
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Your nipples have widened enough to be able to comfortably take "
						+ Capacity.getCapacityFromValue(breast.getStretchedCapacity()).getMaximumSizeComfortableWithLube().getDescriptor() + " cocks with sufficient lubrication.</span>");
			else if (breast.getRawCapacityValue() > 0)
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Despite the fact that your nipples have widened enough to be able to comfortably take "
						+ Capacity.getCapacityFromValue(breast.getStretchedCapacity()).getMaximumSizeComfortableWithLube().getDescriptor()
						+ " cocks with sufficient lubrication, you don't have enough breast flesh for your tits to be fuckable!</span>");
		
		}else if(playerKnowledgeOfBreasts){
			if (breast.isFuckable())
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.Her] nipples have widened enough to be able to comfortably take "
						+ Capacity.getCapacityFromValue(breast.getStretchedCapacity()).getMaximumSizeComfortableWithLube().getDescriptor() + " cocks with sufficient lubrication.</span>");
			else if (breast.getRawCapacityValue() > 0)
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Despite the fact that [npc.her] nipples have widened enough to be able to comfortably take "
						+ Capacity.getCapacityFromValue(breast.getStretchedCapacity()).getMaximumSizeComfortableWithLube().getDescriptor()
						+ " cocks with sufficient lubrication, [npc.she] doesn't have enough breast flesh for [npc.her] tits to be fuckable!</span>");
		}
		
		
		if (isPlayer && !breast.isVirgin()) {
			for(PenetrationType pt : PenetrationType.values()) {
				if(Main.game.getPlayer().getStats().getVirginityLoss(new SexType(pt, OrificeType.NIPPLE_PLAYER))!=null) {
					descriptionSB.append(" <span style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>You lost your nipple virginity to "
							+ Main.game.getPlayer().getStats().getVirginityLoss(new SexType(pt, OrificeType.NIPPLE_PLAYER)) + ".</span>");
				}
			}
		}
		// Milk production:
		if (breast.getRawLactationValue() > 0) {
			if (isPlayer)
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You are currently lactating, and are producing about " + breast.getRawLactationValue() + "mL of " + breast.getMilkName()
						+ " every time you are milked.</span>");
			else {
				if (playerKnowledgeOfBreasts)
					descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.She] is currently lactating, and can produce about " + breast.getRawLactationValue() + "mL of " + breast.getMilkName()
							+ " every time [npc.she] is milked.</span>");
				else
					descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You don't know if [npc.she]'s producing any milk.</span>");
			}
		}
		
		if (breast.getCapacity() != Capacity.ZERO_IMPENETRABLE && (isPlayer || playerKnowledgeOfBreasts)) {
			// Elasticity:
			switch (breast.getElasticity()) {
				case ZERO_UNYIELDING:
					if (isPlayer)
						descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Your nipples are extremely unyielding, but when they do get stretched out, they stay that way.</span>");
					else
						descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.Her] nipples are extremely unyielding, but when they do get stretched out, they stay that way.</span>");
					break;
				case ONE_RIGID:
					if (isPlayer)
						descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It takes a huge amount of effort to stretch your nipples out, but when it happens,"
								+ " they lose a good portion of their original tightness.</span>");
					else
						descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It takes a huge amount of effort to stretch [npc.her] nipples out, but when it happens,"
								+ " they lose a good portion of their original tightness.</span>");
					break;
				case TWO_FIRM:
					if (isPlayer)
						descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Your nipples do not stretch very easily, but when they do, they struggle to return to their original size.</span>");
					else
						descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.Her] nipples do not stretch very easily, but when they do, they struggle to return to their original size.</span>");
					break;
				case THREE_FLEXIBLE:
					if (isPlayer)
						descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Your nipples reluctantly stretch out when used as a sexual orifice, and they take a while to return to their original size.</span>");
					else
						descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.Her] nipples reluctantly stretch out when used as a sexual orifice, and they take a while to return to their original size.</span>");
					break;
				case FOUR_LIMBER:
					if (isPlayer)
						descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Your nipples are somewhat resistant to being stretched out, but will return to their original size after a couple of days.</span>");
					else
						descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.Her] nipples are somewhat resistant to being stretched out, but will return to their original size after a couple of days.</span>");
					break;
				case FIVE_STRETCHY:
					if (isPlayer)
						descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Your nipples stretch out fairly easily, and return to their original size within a day or so.</span>");
					else
						descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.Her] nipples stretch out fairly easily, and return to their original size within a day or so.</span>");
					break;
				case SIX_SUPPLE:
					if (isPlayer)
						descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Your nipples stretch out very easily, and return to their original size within a matter of hours.</span>");
					else
						descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.Her] nipples stretch out very easily, and return to their original size within a matter of hours.</span>");
					break;
				case SEVEN_ELASTIC:
					if (isPlayer)
						descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Your nipples are extremely elastic, and can be comfortably stretched out before instantly returning to their original size.</span>");
					else
						descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.Her] nipples are extremely elastic, and can be comfortably stretched out before instantly returning to their original size.</span>");
					break;
				default:
					break;
			}
		}

		return UtilText.parse(owner, descriptionSB.toString());
	}

	public String getPenisDescription(GameCharacter owner) {
		boolean isPlayer = owner.isPlayer();
		
		descriptionSB = new StringBuilder();

		// First penis:
		switch (penis.getType()) {
			case HUMAN:
				if (isPlayer)
					descriptionSB.append("You have "+(Util.isVowel(penis.getSize().getDescriptor().charAt(0)) ? "an" : "a")+" " + penis.getSize().getDescriptor() + (penis.isPierced()?", pierced":"") + " cock, and when erect, it grows to " + (penis.getRawSizeValue() > 0 ? penis.getRawSizeValue() + " inches long." : "less than an inch in length."));
				else
					descriptionSB.append("[npc.She] has "+(Util.isVowel(penis.getSize().getDescriptor().charAt(0)) ? "an" : "a")+" " + penis.getSize().getDescriptor() + (penis.isPierced()?", pierced":"")  + " cock, and when erect, it grows to " + (penis.getRawSizeValue() > 0 ? penis.getRawSizeValue() + " inches long." : "less than an inch in length."));
				break;
			case DEMON_COMMON:
				if (isPlayer)
					descriptionSB.append("You have "+(Util.isVowel(penis.getSize().getDescriptor().charAt(0)) ? "an" : "a")+" " + penis.getSize().getDescriptor() + (penis.isPierced()?", pierced":"")  + " demonic cock. Thick ridges press out all along its length,"
							+ " and between them, tiny little sensitive bumps wriggle with a mind of their own. When erect, it grows to " + (penis.getRawSizeValue() > 0 ? penis.getRawSizeValue() + " inches long." : "less than an inch in length."));
				else
					descriptionSB.append("[npc.She] has "+(Util.isVowel(penis.getSize().getDescriptor().charAt(0)) ? "an" : "a")+" " + penis.getSize().getDescriptor() + (penis.isPierced()?", pierced":"")  + " demonic cock. Thick ridges press out all along its length,"
							+ " and between them, tiny little sensitive bumps wriggle with a mind of their own. When erect, it grows to " + (penis.getRawSizeValue() > 0 ? penis.getRawSizeValue() + " inches long." : "less than an inch in length."));
				break;
			case CANINE:
				if (isPlayer)
					descriptionSB.append("You have "+(Util.isVowel(penis.getSize().getDescriptor().charAt(0)) ? "an" : "a")+" " + penis.getSize().getDescriptor() + (penis.isPierced()?", pierced":"")  + " dog-like cock, with a fat knot at the base. When erect, it grows to "
							+ (penis.getRawSizeValue() > 0 ? penis.getRawSizeValue() + " inches long." : "less than an inch in length."));
				else
					descriptionSB.append("[npc.She] has "+(Util.isVowel(penis.getSize().getDescriptor().charAt(0)) ? "an" : "a")+" " + penis.getSize().getDescriptor() + (penis.isPierced()?", pierced":"")  + " dog-like cock, with a fat knot at the base. When erect, it grows to "
							+ (penis.getRawSizeValue() > 0 ? penis.getRawSizeValue() + " inches long." : "less than an inch in length."));
				break;
			case LUPINE:
				if (isPlayer)
					descriptionSB.append("You have "+(Util.isVowel(penis.getSize().getDescriptor().charAt(0)) ? "an" : "a")+" " + penis.getSize().getDescriptor() + (penis.isPierced()?", pierced":"")  + " wolf-like cock, with a fat knot at the base. When erect, it grows to "
							+ (penis.getRawSizeValue() > 0 ? penis.getRawSizeValue() + " inches long." : "less than an inch in length."));
				else
					descriptionSB.append("[npc.She] has "+(Util.isVowel(penis.getSize().getDescriptor().charAt(0)) ? "an" : "a")+" " + penis.getSize().getDescriptor() + (penis.isPierced()?", pierced":"")  + " wolf-like cock, with a fat knot at the base. When erect, it grows to "
							+ (penis.getRawSizeValue() > 0 ? penis.getRawSizeValue() + " inches long." : "less than an inch in length."));
				break;
			case FELINE:
				if (isPlayer)
					descriptionSB.append("You have "+(Util.isVowel(penis.getSize().getDescriptor().charAt(0)) ? "an" : "a")+" " + penis.getSize().getDescriptor() + (penis.isPierced()?", pierced":"")  + " cat-like cock, with little backwards-facing barbs running up its length. When erect, it grows to "
							+ (penis.getRawSizeValue() > 0 ? penis.getRawSizeValue() + " inches long." : "less than an inch in length."));
				else
					descriptionSB.append("[npc.She] has "+(Util.isVowel(penis.getSize().getDescriptor().charAt(0)) ? "an" : "a")+" " + penis.getSize().getDescriptor() + (penis.isPierced()?", pierced":"")  + " cat-like cock, with little backwards-facing barbs running up its length. When erect, it grows to "
							+ (penis.getRawSizeValue() > 0 ? penis.getRawSizeValue() + " inches long." : "less than an inch in length."));
				break;
			case EQUINE:
				if (isPlayer)
					descriptionSB.append("You have "+(Util.isVowel(penis.getSize().getDescriptor().charAt(0)) ? "an" : "a")+" " + penis.getSize().getDescriptor() + (penis.isPierced()?", pierced":"")  + " horse-like cock, with a flat, flared head. When erect, it grows to "
							+ (penis.getRawSizeValue() > 0 ? penis.getRawSizeValue() + " inches long." : "less than an inch in length."));
				else
					descriptionSB.append("[npc.She] has "+(Util.isVowel(penis.getSize().getDescriptor().charAt(0)) ? "an" : "a")+" " + penis.getSize().getDescriptor() + (penis.isPierced()?", pierced":"")  + " horse-like cock, with a flat, flared head. When erect, it grows to "
							+ (penis.getRawSizeValue() > 0 ? penis.getRawSizeValue() + " inches long." : "less than an inch in length."));
				break;
			case SLIME:
				if (isPlayer)
					descriptionSB.append("You have "+(Util.isVowel(penis.getSize().getDescriptor().charAt(0)) ? "an" : "a")+" " + penis.getSize().getDescriptor() + (penis.isPierced()?", pierced":"")
							+ " cock, and although it looks human, it's made completely out of " + skinTypeColours.get(penis.getType().getSkinType()).getName() + " slime. When erect,"
							+ " it grows to " + (penis.getRawSizeValue() > 0 ? penis.getRawSizeValue() + " inches long." : "less than an inch in length."));
				else
					descriptionSB.append("[npc.She] has "+(Util.isVowel(penis.getSize().getDescriptor().charAt(0)) ? "an" : "a")+" " + penis.getSize().getDescriptor() + (penis.isPierced()?", pierced":"")
							+ " cock, and although it looks human, it's made completely out of " + skinTypeColours.get(penis.getType().getSkinType()).getName() + " slime. When erect,"
							+ " it grows to " + (penis.getRawSizeValue() > 0 ? penis.getRawSizeValue() + " inches long." : "less than an inch in length."));
				break;
			case AVIAN:
				if (isPlayer)
					descriptionSB.append("The skin around your " + penis.getSize().getDescriptor() + (penis.isPierced()?", pierced":"")
							+ " human-like cock has formed into a cloaca-like slit, allowing you to retract your cock into your body. When erect, it pushes out and grows to "
							+ (penis.getRawSizeValue() > 0 ? penis.getRawSizeValue() + " inches long." : "less than an inch in length."));
				else
					descriptionSB.append("The skin around [npc.her] " + penis.getSize().getDescriptor() + (penis.isPierced()?", pierced":"")
							+ " human-like cock has formed into a cloaca-like slit, allowing [npc.herHim] to retract [npc.her] cock into [npc.her] body. When erect,"
							+ " it pushes out and grows to " + (penis.getRawSizeValue() > 0 ? penis.getRawSizeValue() + " inches long." : "less than an inch in length."));
				break;
			case ANGEL:
				break;
			case NONE:
				break;
		}

		// Second penis:
		if (penis.getSecondPenisType() != PenisType.NONE) {
			switch (penis.getType()) {
			case HUMAN:
				if (isPlayer)
					descriptionSB.append(" Beneath your cock, you have a human-like second penis, which is slightly smaller than your main shaft.");
				else
					descriptionSB.append(" Beneath [npc.her] cock, [npc.she] has a human-like second penis, which is slightly smaller than [npc.her] main shaft.");
				break;
			case DEMON_COMMON:
				if (isPlayer)
					descriptionSB.append(" Beneath your cock, you have a demonic second penis, which is slightly smaller than your main shaft.");
				else
					descriptionSB.append(" Beneath [npc.her] cock, [npc.she] has a demonic second penis, which is slightly smaller than [npc.her] main shaft.");
				break;
			case CANINE:
				if (isPlayer)
					descriptionSB.append(" Beneath your cock, you have a dog-like second penis, which is slightly smaller than your main shaft.");
				else
					descriptionSB.append(" Beneath [npc.her] cock, [npc.she] has a dog-like second penis, which is slightly smaller than [npc.her] main shaft.");
				break;
			case FELINE:
				if (isPlayer)
					descriptionSB.append(" Beneath your cock, you have a cat-like second penis, which is slightly smaller than your main shaft.");
				else
					descriptionSB.append(" Beneath [npc.her] cock, [npc.she] has a cat-like second penis, which is slightly smaller than [npc.her] main shaft.");
				break;
			case EQUINE:
				if (isPlayer)
					descriptionSB.append(" Beneath your cock, you have a horse-like second penis, which is slightly smaller than your main shaft.");
				else
					descriptionSB.append(" Beneath [npc.her] cock, [npc.she] has a horse-like second penis, which is slightly smaller than [npc.her] main shaft.");
				break;
			case SLIME:
				if (isPlayer)
					descriptionSB.append(" Beneath your cock, you have a second penis, made out of " + skinTypeColours.get(penis.getType().getSkinType()).getName() + " slime, which is slightly smaller than your main shaft.");
				else
					descriptionSB.append(" Beneath [npc.her] cock, [npc.she] has a second penis, made out of " + skinTypeColours.get(penis.getType().getSkinType()).getName() + " slime, which is slightly smaller than [npc.her] main shaft.");
				break;
			case AVIAN:
				if (isPlayer)
					descriptionSB.append(" Beneath your cock, you have a second penis, which is slightly smaller than your main shaft.");
				else
					descriptionSB.append(" Beneath [npc.her] cock, [npc.she] has a second penis, which is slightly smaller than [npc.her] main shaft.");
				break;
			default:
				break;
			}
		}
		// Capacity:
		if (Capacity.getCapacityFromValue(penis.getStretchedCapacity()) != Capacity.ZERO_IMPENETRABLE) {
			if (isPlayer) {
				if (penis.getSecondPenisType() != PenisType.NONE)
					descriptionSB.append(" Both of your cock's urethras have been loosened enough that they present ready orifices for penetration. <span style='color:" + Colour.GENERIC_SEX.toWebHexString()
							+ ";'>They can comfortably be penetrated by " + Capacity.getCapacityFromValue(penis.getStretchedCapacity()).getMaximumSizeComfortableWithLube().getDescriptor() + " cocks with sufficient lubrication.</span>");
				else
					descriptionSB.append(" Your cock's urethra has been loosened enough that it presents a ready orifice for penetration. <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It can comfortably be penetrated by "
							+ Capacity.getCapacityFromValue(penis.getStretchedCapacity()).getMaximumSizeComfortableWithLube().getDescriptor() + " cocks with sufficient lubrication.</span>");
			} else {
				if (penis.getSecondPenisType() != PenisType.NONE)
					descriptionSB.append(" Both of [npc.her] cock's urethras have been loosened enough that they present ready orifices for penetration. <span style='color:" + Colour.GENERIC_SEX.toWebHexString()
							+ ";'>They can comfortably be penetrated by " + Capacity.getCapacityFromValue(penis.getStretchedCapacity()).getMaximumSizeComfortableWithLube().getDescriptor() + " cocks with sufficient lubrication.</span>");
				else
					descriptionSB.append(" [npc.Her] cock's urethra has been loosened enough that it presents a ready orifice for penetration. <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It can comfortably be penetrated by "
							+ Capacity.getCapacityFromValue(penis.getStretchedCapacity()).getMaximumSizeComfortableWithLube().getDescriptor() + " cocks with sufficient lubrication.</span>");
			}
			// Elasticity:
			switch (penis.getElasticity()) {
				case ZERO_UNYIELDING:
					descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>The urethra is extremely unyielding, but when it does get stretched out, it stays that way.</span>");
					break;
				case ONE_RIGID:
					descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It takes a huge amount of effort to stretch the urethra out, but when it does, it loses a good portion of its original tightness.</span>");
					break;
				case TWO_FIRM:
					descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>The urethra does not stretch very easily, but when it does, it struggles to return to its original size.</span>");
					break;
				case THREE_FLEXIBLE:
					descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>The urethra reluctantly stretches out when used as a sexual orifice, and takes a while to return to its original size.</span>");
					break;
				case FOUR_LIMBER:
					descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>The urethra is somewhat resistant to being stretched out, but will return to its original size after a couple of days.</span>");
					break;
				case FIVE_STRETCHY:
					descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>The urethra stretches out fairly easily, and returns to its original size within a day or so.</span>");
					break;
				case SIX_SUPPLE:
					descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>The urethra stretches out very easily, and returns to its original size within a matter of hours.</span>");
					break;
				case SEVEN_ELASTIC:
					descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>The urethra is extremely elastic, and can be comfortably stretched out before instantly returning to its original size.</span>");
					break;
				default:
					break;
			}
		}
		if (isPlayer) {
			if (!penis.isUrethraVirgin()) {
				for(PenetrationType pt : PenetrationType.values()) {
					if(Main.game.getPlayer().getStats().getVirginityLoss(new SexType(pt, OrificeType.URETHRA_PLAYER))!=null) {
						descriptionSB.append(" <span style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>You lost your urethral virginity to "
								+ Main.game.getPlayer().getStats().getVirginityLoss(new SexType(pt, OrificeType.URETHRA_PLAYER)) + ".</span>");
					}
				}
			}
		}
		// Testicle size and cum production:
		if(owner.isInternalTesticles()) {
				if (isPlayer) {
					descriptionSB.append(" Your [pc.ballsCount] [pc.balls+] have shifted to sit inside your body, leaving your [pc.cock] as the only visible part of your male reproductive organs.");
				} else {
					descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] have shifted to sit inside [npc.her] body, leaving [npc.her] [npc.cock] as the only visible part of [pc.her] male reproductive organs.");
				}
			
		} else {
			switch (penis.getTesticleSize()) {
				case ZERO_VESTIGIAL:
					if (isPlayer) {
						descriptionSB.append(" Your [pc.ballsCount] [pc.balls] are so small that they're only just visible as tiny little mounds nestling beneath your [pc.cock].");
					} else {
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls] are so small that they're only just visible as tiny little mounds nestling beneath [npc.her] [npc.cock].");
					}
					break;
				case ONE_TINY:
					if (isPlayer)
						descriptionSB.append(" Your [pc.ballsCount] [pc.balls] are extremely small, and nestle comfortably underneath your [pc.cock].");
					else
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls] are extremely small, and nestle comfortably underneath [npc.her] [npc.cock].");
					break;
				case TWO_AVERAGE:
					if (isPlayer)
						descriptionSB.append(" Your [pc.ballsCount] [pc.balls] are an average size, and dangle down beneath your [pc.cock].");
					else
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls] are an average size, and dangle down beneath [npc.her] [npc.cock].");
					break;
				case THREE_LARGE:
					if (isPlayer)
						descriptionSB.append(" Your [pc.ballsCount] [pc.balls+] hang down beneath your [pc.cock].");
					else
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] hang down beneath [npc.her] [npc.cock].");
					break;
				case FOUR_HUGE:
					if (isPlayer)
						descriptionSB.append(" Your [pc.ballsCount] [pc.balls+] hang down beneath your [pc.cock].");
					else
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] hang down beneath [npc.her] [npc.cock].");
					break;
				case FIVE_MASSIVE:
					if (isPlayer)
						descriptionSB.append(" Your [pc.ballsCount] [pc.balls+] hang down beneath your [pc.cock].");
					else
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] hang down beneath [npc.her] [npc.cock].");
					break;
				case SIX_GIGANTIC:
					if (isPlayer)
						descriptionSB.append(" Your [pc.ballsCount] [pc.balls+] hang down beneath your [pc.cock].");
					else
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] hang down beneath [npc.her] [npc.cock].");
					break;
				case SEVEN_ABSURD:
					if (isPlayer)
						descriptionSB.append(" Your [pc.ballsCount] [pc.balls] hang down beneath your [pc.cock], and are absurdly enormous.");
					else
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls] hang down beneath [npc.her] [npc.cock], and are absurdly enormous.");
					break;
			}
		}
		switch (penis.getCumProduction()) {
		case ZERO_NONE:
			if (penis.getTesticleSize().getValue() > TesticleSize.TWO_AVERAGE.getValue())
				descriptionSB.append(" Despite their large size, t");
			else
				descriptionSB.append(" T");
			descriptionSB.append("hey don't produce any cum at all.");
			break;
		case ONE_TRICKLE:
			if (penis.getTesticleSize().getValue() > TesticleSize.TWO_AVERAGE.getValue())
				descriptionSB.append(" Despite their large size, t");
			else
				descriptionSB.append(" T");
			descriptionSB.append("hey only produce a tiny trickle of cum at each orgasm.");
			break;
		case TWO_SMALL_AMOUNT:
			if (penis.getTesticleSize().getValue() > TesticleSize.THREE_LARGE.getValue())
				descriptionSB.append(" Despite their large size, t");
			else
				descriptionSB.append(" T");
			descriptionSB.append("hey only produce a small amount of cum at each orgasm.");
			break;
		case THREE_AVERAGE:
			if (penis.getTesticleSize().getValue() > TesticleSize.FOUR_HUGE.getValue())
				descriptionSB.append(" Despite their huge size, they only");
			else
				descriptionSB.append(" They");
			descriptionSB.append(" produce an average amount of cum at each orgasm.");
			break;
		case FOUR_LARGE:
			if (penis.getTesticleSize().getValue() < TesticleSize.TWO_AVERAGE.getValue())
				descriptionSB.append(" Despite their small size, t");
			else
				descriptionSB.append(" T");
			descriptionSB.append("hey produce a large amount of cum at each orgasm.");
			break;
		case FIVE_HUGE:
			if (penis.getTesticleSize().getValue() < TesticleSize.TWO_AVERAGE.getValue())
				descriptionSB.append(" Despite their small size, t");
			else
				descriptionSB.append(" T");
			descriptionSB.append("hey produce a huge amount of cum at each orgasm.");
			break;
		case SIX_EXTREME:
			if (penis.getTesticleSize().getValue() < TesticleSize.TWO_AVERAGE.getValue())
				descriptionSB.append(" Despite their small size, t");
			else
				descriptionSB.append(" T");
			descriptionSB.append("hey produce an extreme amount of cum at each orgasm.");
			break;
		case SEVEN_MONSTROUS:
			if (penis.getTesticleSize().getValue() < TesticleSize.TWO_AVERAGE.getValue())
				descriptionSB.append(" Despite their small size, t");
			else
				descriptionSB.append(" T");
			descriptionSB.append("hey produce a monstrous amount of cum at each orgasm.");
			break;
		}

		return UtilText.parse(owner, descriptionSB.toString());
	}

	public String getVaginaDescription(GameCharacter owner) {
		boolean isPlayer = owner.isPlayer();
		
		descriptionSB = new StringBuilder();

		if (isPlayer) {
			if (penis.getType() != PenisType.NONE)
				descriptionSB.append("Beneath your " + penis.getName(owner, false) + ", ");
			else
				descriptionSB.append("Between your legs, ");
		} else {
			if (penis.getType() != PenisType.NONE)
				descriptionSB.append("Beneath [npc.her] " + penis.getName(owner, false) + ", ");
			else
				descriptionSB.append("Between [npc.her] legs, ");
		}

		switch (vagina.getType()) {
			case HUMAN:
				if (isPlayer)
					descriptionSB.append("you have a normal human vagina"+(vagina.isPierced()?", which has been pierced":"")+".");
				else
					descriptionSB.append("[npc.she] has a normal human vagina"+(vagina.isPierced()?", which has been pierced":"")+".");
				break;
			case ANGEL:
				if (isPlayer)
					descriptionSB.append("you have an angelic vagina"+(vagina.isPierced()?", which has been pierced":"")+".");
				else
					descriptionSB.append("[npc.she] has an angelic vagina"+(vagina.isPierced()?", which has been pierced":"")+".");
				break;
			case DEMON_COMMON:
				if (isPlayer)
					descriptionSB.append("you have a demonic vagina"+(vagina.isPierced()?", which has been pierced":"")+"."
							+ " Rows of little grasping tentacles line your inner walls, and you find that you have extremely fine control over how they grip and squeeze down on any intruding object.");
				else
					descriptionSB.append("[npc.she] has a demonic vagina"+(vagina.isPierced()?", which has been pierced":"")+"."
							+ " Rows of little grasping tentacles line [npc.her] inner walls, and [npc.she] has extremely fine control over how they grip and squeeze down on any intruding object.");
				break;
			case DOG_MORPH:
				if (isPlayer)
					descriptionSB.append("you have a canine pussy"+(vagina.isPierced()?", which has been pierced":"")+"."
							+ " Although it looks very much like one found on a human, your vagina is surrounded by a short layer of soft [pc.vaginaColour] fur, which gives it a rather animalistic appearance.");
				else
					descriptionSB.append("[npc.she] has a canine pussy"+(vagina.isPierced()?", which has been pierced":"")+"."
							+ " Although it looks very much like one found on a human, [npc.her] vagina is surrounded by a short layer of soft [pc.vaginaColour] fur, which gives it a rather animalistic appearance.");
				break;
			case WOLF_MORPH:
				if (isPlayer)
					descriptionSB.append("you have a lupine pussy"+(vagina.isPierced()?", which has been pierced":"")+"."
							+ " Although it looks very much like one found on a human, your vagina is surrounded by a short layer of soft [pc.vaginaColour] fur, which gives it a rather animalistic appearance.");
				else
					descriptionSB.append("[npc.she] has a lupine pussy"+(vagina.isPierced()?", which has been pierced":"")+"."
							+ " Although it looks very much like one found on a human, [npc.her] vagina is surrounded by a short layer of soft [pc.vaginaColour] fur, which gives it a rather animalistic appearance.");
				break;
			case CAT_MORPH:
				if (isPlayer)
					descriptionSB.append("you have a feline pussy"+(vagina.isPierced()?", which has been pierced":"")+"."
							+ " Although it looks very much like one found on a human, your vagina is surrounded by a short layer of soft [pc.vaginaColour] fur, which gives it a rather animalistic appearance.");
				else
					descriptionSB.append("[npc.she] has a feline pussy"+(vagina.isPierced()?", which has been pierced":"")+"."
							+ " Although it looks very much like one found on a human, [npc.her] vagina is surrounded by a short layer of soft [pc.vaginaColour] fur, which gives it a rather animalistic appearance.");
				break;
			case HORSE_MORPH:
				if (isPlayer)
					descriptionSB.append("you have an equine pussy"+(vagina.isPierced()?", which has been pierced":"")+"."
							+ " Your outer labia are formed into puffy black lips, leaving your vagina looking just like one that would normally be found on a horse.");
				else
					descriptionSB.append("[npc.she] has an equine pussy"+(vagina.isPierced()?", which has been pierced":"")+"."
							+ " [npc.Her] outer labia are formed into puffy black lips, leaving [npc.her] vagina looking just like one that would normally be found on a horse.");
				break;
			case HARPY:
				if (isPlayer)
					descriptionSB.append("you have an avian pussy"+(vagina.isPierced()?", which has been pierced":"")+"."
							+ " Although it looks very much like one found on a human, your vagina is surrounded by a short layer of soft [pc.vaginaColour] feathers, which gives it a somewhat bird-like appearance.");
				else
					descriptionSB.append("[npc.she] has an avian pussy"+(vagina.isPierced()?", which has been pierced":"")+"."
							+ " Although it looks very much like one found on a human, [npc.her] vagina is surrounded by a short layer of soft [pc.vaginaColour] feathers, which gives it a somewhat bird-like appearance.");
				break;
			case SLIME:
				if (isPlayer)
					descriptionSB.append("you have a "+(vagina.isPierced()?"pierced, ":"")+"human-like vagina that's made completely out of [pc.vaginaColour] slime.");
				else
					descriptionSB.append("[npc.she] has a "+(vagina.isPierced()?"pierced, ":"")+"human-like vagina that's made completely out of [npc.vaginaColour] slime.");
				break;
			default:
				break;
		}
		if (isPlayer) {
			if(owner.getVaginaRawClitorisSizeValue()==0)
				descriptionSB.append(" You have [pc.a_clitSize] clit, which measures less than one inch in length.");
			else
				descriptionSB.append(" You have [pc.a_clitSize] clit, which measures [pc.clitSizeInches] inches long.");
			
		} else {
			if(owner.getVaginaRawClitorisSizeValue()==0)
				descriptionSB.append(" [npc.She] has [npc.a_clitSize] clit, which measures less than one inch in length.");
			else
				descriptionSB.append(" [npc.She] has [npc.a_clitSize] clit, which measures [npc.clitSizeInches] inches long.");
		}
		// Virgin/capacity:
		if (vagina.isVirgin()) {
			if (isPlayer)
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Within your " + Capacity.getCapacityFromValue(vagina.getStretchedCapacity()).getDescriptor() + " "
						+ vagina.getName(owner, false) + ", your hymen is still intact, as your " + vagina.getName(owner, false) + " has never been penetrated before.</span>"
						+ " <span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>You have retained your vaginal virginity.</span>");
			else
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Within [npc.her] " + Capacity.getCapacityFromValue(vagina.getStretchedCapacity()).getDescriptor() + " "
						+ vagina.getName(owner, false) + ", [npc.her] hymen is still intact, as [npc.her] " + vagina.getName(owner, false) + " has never been penetrated before.</span>");
		} else {
			if (isPlayer) {
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Your pussy is " + Capacity.getCapacityFromValue(vagina.getStretchedCapacity()).getDescriptor() + ", and can comfortably take "
						+ Capacity.getCapacityFromValue(vagina.getStretchedCapacity()).getMaximumSizeComfortableWithLube().getDescriptor() + " cocks with sufficient lubrication.</span>");
				

				for(PenetrationType pt : PenetrationType.values()) {
					if(Main.game.getPlayer().getStats().getVirginityLoss(new SexType(pt, OrificeType.VAGINA_PLAYER))!=null) {
						descriptionSB.append(" <span style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>You lost your virginity to "
								+ Main.game.getPlayer().getStats().getVirginityLoss(new SexType(pt, OrificeType.VAGINA_PLAYER)) + ".</span>");
					}
				}
				
			} else{
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.Her] pussy is " + Capacity.getCapacityFromValue(vagina.getStretchedCapacity()).getDescriptor() + ", and can comfortably take "
						+ Capacity.getCapacityFromValue(vagina.getStretchedCapacity()).getMaximumSizeComfortableWithLube().getDescriptor() + " cocks with sufficient lubrication.</span>");
			}
		}
		// Wetness:
		switch (vagina.getWetness()) {
		case ZERO_DRY:
			if (isPlayer)
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It's completely dry and never gets wet, no matter how aroused you are.</span>");
			else
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It's completely dry and never gets wet, no matter how aroused [npc.she] gets.</span>");
			break;
		case ONE_SLIGHTLY_MOIST:
			if (isPlayer)
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It's slightly moist, and you need a huge amount of stimulation before you get wet.</span>");
			else
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It's slightly moist, and [npc.she] needs a huge amount of stimulation before [npc.she] gets wet.</span>");
			break;
		case TWO_MOIST:
			if (isPlayer)
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It's moist, but you still need a lot of stimulation before you get wet.</span>");
			else
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It's moist, but [npc.she] still needs a lot of stimulation before [npc.she] gets wet.</span>");
			break;
		case THREE_WET:
			if (isPlayer)
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You only need a small amount of foreplay before it gets wet enough for penetration.</span>");
			else
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.She] only needs a small amount of foreplay before [npc.she] gets wet enough for penetration.</span>");
			break;
		case FOUR_SLIMY:
			if (isPlayer)
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It's always slimy and wet, and you're ready for penetration at a moment's notice.</span>");
			else
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It's always slimy and wet, and [npc.she]'s ready for penetration at a moment's notice.</span>");
			break;
		case FIVE_SLOPPY:
			if (isPlayer)
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Its surface is always coated in slimy moisture, and within, your pussy is sloppy and practically begging to be fucked.</span>");
			else
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Its surface is always coated in slimy moisture, and within, [npc.her] pussy is sloppy and practically begging to be fucked.</span>");
			break;
		case SIX_SOPPING_WET:
			if (isPlayer)
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Your pussy is never anything less than sopping wet, and a trickle of your natural lubricant constantly dribbles from your slit.</span>");
			else
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.Her] pussy is never anything less than sopping wet, and a trickle of [npc.her] natural lubricant constantly dribbles from [npc.her] slit.</span>");
			break;
		case SEVEN_DROOLING:
			if (isPlayer)
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Your pussy is so wet that it audibly squelches with every step you take, and a constant stream of juices flow from your inviting cunt.</span>");
			else
				descriptionSB
						.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.Her] pussy is so wet that it audibly squelches with every step [npc.she] takes, and a constant stream of juices flow from [npc.her] inviting cunt.</span>");
			break;
		default:
			break;
		}
		// Elasticity:
		switch (vagina.getElasticity()) {
			case ZERO_UNYIELDING:
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It is extremely unyielding, but when it does get stretched out, it stays that way.</span>");
				break;
			case ONE_RIGID:
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It takes a huge amount of effort to stretch it out, but when it does, it loses a good portion of its original tightness.</span>");
				break;
			case TWO_FIRM:
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It does not stretch very easily, but when it does, it struggles to return to its original size.</span>");
				break;
			case THREE_FLEXIBLE:
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It reluctantly stretches out when used as a sexual orifice, and takes a while to return to its original size.</span>");
				break;
			case FOUR_LIMBER:
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It is somewhat resistant to being stretched out, but will return to its original size after a couple of days.</span>");
				break;
			case FIVE_STRETCHY:
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It stretches out fairly easily, and returns to its original size within a day or so.</span>");
				break;
			case SIX_SUPPLE:
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It stretches out very easily, and returns to its original size within a matter of hours.</span>");
				break;
			case SEVEN_ELASTIC:
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It is extremely elastic, and can be comfortably stretched out before instantly returning to its original size.</span>");
				break;
			default:
				break;
		}

		return UtilText.parse(owner, descriptionSB.toString());
	}

	public String getMoundDescription(GameCharacter owner) {
		descriptionSB = new StringBuilder();

		if (owner.isPlayer()) {
			descriptionSB.append("Between your legs, you have a genderless mound. Despite your lack of genitalia, it's still an incredibly sensitive area, and you can bring yourself to a quasi-orgasm by stroking it.");
		} else {
			descriptionSB.append("Between [npc.her] legs, [npc.she] has a genderless mound. Despite [npc.her] lack of genitalia, it's still an incredibly sensitive area, and [npc.she] can be brought to a quasi-orgasm by stimulating it.");
		}

		return UtilText.parse(owner, descriptionSB.toString());
	}
	
	public String getSexDetails(GameCharacter owner) {
		
		if(owner.getStats().getTotalTimesHadSex() >=1) {
			descriptionSB = new StringBuilder();
			
			// Amount of sex:
			
			descriptionSB.append(
					UtilText.parse(owner,
					"<p>"
						+ "<span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>"
							+ "You have had sex with [npc.name] "+Util.intToString(owner.getStats().getTotalTimesHadSex())+" "+(owner.getStats().getTotalTimesHadSex()==1?"time.":"times.")
						+"</span>"));
			
			if(owner.getStats().getSexConsensualCount()>=1) {
				if(owner.getStats().getSexConsensualCount() == owner.getStats().getTotalTimesHadSex()) {
					if(owner.getStats().getTotalTimesHadSex()==1) {
						descriptionSB.append(UtilText.parse(owner," The one time you had sex with [npc.herHim], it was consensual."));
					} else {
						descriptionSB.append(UtilText.parse(owner," All "+Util.intToString(owner.getStats().getTotalTimesHadSex())+" times were consensual."));
					}
					
				} else {
					if(owner.getStats().getTotalTimesHadSex()==1) {
						descriptionSB.append(UtilText.parse(owner," The one time you had sex with [npc.herHim], it was consensual."));
					} else {
						descriptionSB.append(UtilText.parse(owner," "+Util.capitaliseSentence(Util.intToString(owner.getStats().getSexConsensualCount()))+" of these times were consensual."));
					}
				}
			}
			if(owner.getStats().getSexAsSubCount()>=1) {
				if(owner.getStats().getSexAsSubCount() == owner.getStats().getTotalTimesHadSex()) {
					if(owner.getStats().getTotalTimesHadSex()==1) {
						descriptionSB.append(UtilText.parse(owner," The one time you had sex with [npc.herHim], it was non-consensual, with you as the dominant partner."));
					} else {
						descriptionSB.append(UtilText.parse(owner," All "+Util.intToString(owner.getStats().getTotalTimesHadSex())+" times were non-consensual, with you as the dominant partner."));
					}
					
				} else {
					if(owner.getStats().getTotalTimesHadSex()==1) {
						descriptionSB.append(UtilText.parse(owner," The one time you had sex with [npc.herHim], it was non-consensual, with you as the dominant partner."));
					} else {
						descriptionSB.append(UtilText.parse(owner," "+Util.capitaliseSentence(Util.intToString(owner.getStats().getSexAsSubCount()))+" of these times were non-consensual, with you as the dominant partner."));
					}
				}
			}
			if(owner.getStats().getSexAsDomCount()>=1) {
				if(owner.getStats().getSexAsDomCount() == owner.getStats().getTotalTimesHadSex()) {
					if(owner.getStats().getTotalTimesHadSex()==1) {
						descriptionSB.append(UtilText.parse(owner," The one time you had sex with [npc.herHim], it was non-consensual, with you as the submissive partner."));
					} else {
						descriptionSB.append(UtilText.parse(owner," All "+Util.intToString(owner.getStats().getTotalTimesHadSex())+" times were non-consensual, with you as the submissive partner."));
					}
					
				} else {
					if(owner.getStats().getTotalTimesHadSex()==1) {
						descriptionSB.append(UtilText.parse(owner," The one time you had sex with [npc.herHim], it was non-consensual, with you as the submissive partner."));
					} else {
						descriptionSB.append(UtilText.parse(owner," "+Util.capitaliseSentence(Util.intToString(owner.getStats().getSexAsDomCount()))+" of these times were non-consensual, with you as the submissive partner."));
					}
				}
			}
			descriptionSB.append("</p>");

			return UtilText.parse(owner, descriptionSB.toString());
		
		} else {
			return "";
		}
	}
	
	public String getPregnancyDetails(GameCharacter owner) {
		descriptionSB = new StringBuilder();
		
		// NPC is mother:
		
		if(owner.isVisiblyPregnant()) {
			descriptionSB.append(UtilText.parse(owner,
				"<p>"
				+ "<span style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>From one of your sexual encounters, you've ended up impregnating [npc.name].</span>"));
			
			if(owner.hasStatusEffect(StatusEffect.PREGNANT_1)) {
				descriptionSB.append(UtilText.parse(owner,
						" [npc.Her] belly is only a little swollen, as [npc.she]'s only in the first stage of pregnancy."));
			} else if(owner.hasStatusEffect(StatusEffect.PREGNANT_2)) {
				descriptionSB.append(UtilText.parse(owner,
						" [npc.Her] belly is noticeably swollen, as [npc.she]'s well into [npc.her] pregnancy."));
			} else {
				descriptionSB.append(UtilText.parse(owner,
						" [npc.Her] belly is massively swollen, and although [npc.she]'s clearly ready for it, [npc.she] hasn't decided to give birth to your children just yet."));
			}
			descriptionSB.append("</p>");
		}
		
		if(!owner.getLittersBirthed().isEmpty()) {
			
			descriptionSB.append(UtilText.parse(owner,
				"<p>"
				+ "<span style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>"
						+ "[npc.Name] is the mother of some of your children, and in total, has given birth "+Util.intToString(owner.getLittersBirthed().size())+" "+(owner.getLittersBirthed().size()==1?"time":"times")+".</span>"));
			
			for(Litter litter : owner.getLittersBirthed()) {
				descriptionSB.append(UtilText.parse(owner,
						"</br>On day "+litter.getDayOfConception()+", you impregnated [npc.her], and "+Util.intToString(litter.getDayOfBirth()-litter.getDayOfConception())+" days later, [npc.she] gave birth to "));
				

				descriptionSB.append(litter.getBirthedDescriptionList());
				
				descriptionSB.append(".");
			}
			
			descriptionSB.append("</p>");
		}
		
		// NPC is father:
		
		if(Main.game.getPlayer().isVisiblyPregnant()){
			for(PregnancyPossibility pp : Main.game.getPlayer().getPotentialPartnersAsMother()) {
				if(pp.getFather()==owner) {
					descriptionSB.append(UtilText.parse(owner,
							"<p>"
								+ "<span style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>From one of your sexual encounters, you've been impregnated, and it's possible that [npc.name] is the father.</span>"
							+ "</p>"));
					break;
				}
			}
		}
		
		if(!Main.game.getPlayer().getLittersBirthed().isEmpty()) {
			int fatheredLitters = 0;
			
			for(Litter litter : Main.game.getPlayer().getLittersBirthed()) {
				if(litter.getFather()==owner){
					fatheredLitters++;
				}
			}
			
			if(fatheredLitters!=0) {
				descriptionSB.append(UtilText.parse(owner,
					"<p>"
					+ "<span style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>"
							+ "[npc.Name] is the father of some of your children, and has, in total, impregnated you "+Util.intToString(fatheredLitters)+" "+(fatheredLitters==1?"time":"times")+".</span>"));
				
				for(Litter litter : Main.game.getPlayer().getLittersBirthed()) {
					if(litter.getFather()==owner){
						descriptionSB.append(UtilText.parse(owner,
								"</br>On day "+litter.getDayOfConception()+", [npc.she] impregnated you, and "+Util.intToString(litter.getDayOfBirth()-litter.getDayOfConception())+" days later, you gave birth to "));
						
						descriptionSB.append(litter.getBirthedDescriptionList());
						
						descriptionSB.append(".");
					}
				}
				
				descriptionSB.append("</p>");
			}
		}

		return UtilText.parse(owner, descriptionSB.toString());
	}

	/**
	 * Gender is calculated based on body femininity and sexual organs.
	 * 
	 * @return gender of this body
	 */
	public Gender getGender() {
		// Looks male:
		if (femininity <= Femininity.MASCULINE.getMaximumFemininity()) {
			// Has penis:
			if (penis.getType() != PenisType.NONE) {
				// Has vagina:
				if (vagina.getType() != VaginaType.NONE)
					return Gender.HERMAPHRODITE;
				else
					return Gender.MALE;
			}
			// Has vagina:
			if (vagina.getType() != VaginaType.NONE)
				return Gender.CUNT_BOY;
			
			// Return genderless if no sexual organs found:
			return Gender.GENDERLESS_MASCULINE;

			// Looks female or androgynous:
		} else {
			// Has penis:
			if (penis.getType() != PenisType.NONE) {
				// Has vagina:
				if (vagina.getType() != VaginaType.NONE)
					return Gender.FUTANARI;
				else
					return Gender.SHEMALE;
			}
			// Has vagina:
			if (vagina.getType() != VaginaType.NONE)
				return Gender.FEMALE;
			
			// Return genderless if no sexual organs found:
			return Gender.GENDERLESS_FEMININE;
		}
	}

	/**
	 * @return weight in kilograms
	 */
	public int getCalculatedWeight() {

		// Weight = 0.4 * height
		int weight = (int) (height * 0.4f);

		// If harpy wings, your bones & muscles are really light, so *0.75
		if (arm.getType() == ArmType.HARPY)
			weight *= 0.75;

//		// If centaur, you have a horse body, so weight*0.6 + 250 (horses are
//		// 400kg at lightest and centaur bodies are smaller than horse's)
//		if (leg.getType() == LegType.CENTAUR) {
//			weight *= 0.6;
//			weight += 250;
//		}

		return weight;
	}

	/** Height is measured in cm. **/
	public int getHeight() {
		return height;
	}
	
	public String getHeightName() {
		String name[];
		
		if (height<152) { // Under 5':
			name = new String[]{ "extremely short" };
			
		} else if (height<166) { // Under 5'6"
			name = new String[]{ "short" };
			
		} else if (height<183) { // Under 6'
			name = new String[]{ "average height" };
			
		} else if (height<198) { // Under 6' 6"
			name = new String[]{ "tall" };
			
		} else if (height<214) { // Under 7'
			name = new String[]{ "very tall" };
			
		} else if (height<244) { // Under 8'
			name = new String[]{ "extremely tall" };
			
		} else if (height<275) { // Under 9'
			name = new String[]{ "giant" };
			
		} else if (height<305) { // Under 10'
			name = new String[]{ "towering" };
			
		} else if (height<335) { // Under 11'
			name = new String[]{ "colossal" };
			
		} else { // Under 12'
			name = new String[]{ "monstrous" };
		} 
		
		return name[Util.random.nextInt(name.length)];
	}

	/**
	 * Sets height attribute. Bound between 122 (4 feet) and 365 (12 feet).
	 * 
	 * @param height
	 *            Value to set height to.
	 * @return True if height was changed.
	 */
	public boolean setHeight(int height) {
		if (this.height == height) {
			return false;
		}
		
		if (height < 122) {
			if (this.height > 122) {
				this.height = 122;
				return true;
			} else
				return false;
		}
		if (height > 366) {
			if (this.height < 366) {
				this.height = 366;
				return true;
			} else
				return false;
		}

		this.height = height;
		return true;
	}

	public int getFemininity() {
		return femininity;
	}

	/**
	 * @param femininity
	 *            Value to set femininity to.
	 * @return True if femininity was changed.
	 */
	public boolean setFemininity(int femininity) {
		if (this.femininity == femininity) {
			return false;
		}
		
		if (femininity <= 0) {
			if (this.femininity == 0)
				return false;
			this.femininity = 0;
			return true;
		}
		if (femininity >= 100) {
			if (this.femininity == 100)
				return false;
			this.femininity = 100;
			return true;
		}
		
		this.femininity = femininity;
		return true;
	}

	public boolean isPiercedStomach() {
		return piercedStomach;
	}

	public void setPiercedStomach(boolean piercedStomach) {
		this.piercedStomach = piercedStomach;
	}

	public Map<BodyCoveringType, Colour> getBodyCoveringTypeColours() {
		return skinTypeColours;
	}

	public Set<BodyCoveringType> getBodyCoveringTypesDiscovered() {
		return skinTypeColoursDiscovered;
	}
	
	public boolean isAbleToFly() {
		return arm.getType()==ArmType.HARPY;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
