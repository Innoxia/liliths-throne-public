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
import com.base.game.character.body.types.AntennaType;
import com.base.game.character.body.types.ArmType;
import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.body.types.HornType;
import com.base.game.character.body.types.LegType;
import com.base.game.character.body.types.PenisType;
import com.base.game.character.body.types.TailType;
import com.base.game.character.body.types.VaginaType;
import com.base.game.character.body.types.WingType;
import com.base.game.character.body.valueEnums.BodyHair;
import com.base.game.character.body.valueEnums.BodyMaterial;
import com.base.game.character.body.valueEnums.BodyShape;
import com.base.game.character.body.valueEnums.BodySize;
import com.base.game.character.body.valueEnums.Capacity;
import com.base.game.character.body.valueEnums.CoveringPattern;
import com.base.game.character.body.valueEnums.CupSize;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.character.body.valueEnums.FluidModifier;
import com.base.game.character.body.valueEnums.GenitalArrangement;
import com.base.game.character.body.valueEnums.Muscle;
import com.base.game.character.body.valueEnums.OrificeModifier;
import com.base.game.character.body.valueEnums.PenisModifier;
import com.base.game.character.body.valueEnums.StartingSkinTone;
import com.base.game.character.body.valueEnums.TesticleSize;
import com.base.game.character.body.valueEnums.TongueModifier;
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
 * @version 0.1.83
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
	private BodyMaterial bodyMaterial;

	// Optional:
	private Antenna antenna;
	private Horn horn;
	private Penis penis;
	private Penis secondPenis;
	private Tail tail;
	private Vagina vagina;
	private Wing wing;
	
	private GenitalArrangement genitalArrangement;

	private Race race;
	private RaceStage raceStage;
	private boolean piercedStomach = false;
	private int height, femininity, bodySize, muscle;
	private BodyHair pubicHair;
	
	private Map<BodyCoveringType, Covering> coverings;
	private Set<BodyCoveringType> coveringsDiscovered;

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
		private final BodyMaterial bodyMaterial;
		private GenitalArrangement genitalArrangement;
		private final Race race = null;
		private final int height;
		private final int femininity, bodySize, muscle;
		
		// Optional parameters - initialised to null values:
		private Antenna antenna = new Antenna(AntennaType.NONE);
		private Horn horn = new Horn(HornType.NONE);
		private Penis penis = new Penis(PenisType.NONE, 0, 0, 0, 0);
		private Penis secondPenis = new Penis(PenisType.NONE, 0, 0, 0, 0);
		private Tail tail = new Tail(TailType.NONE);
		private Vagina vagina = new Vagina(VaginaType.NONE, 0, 0, 0, 3, 3, true);
		private Wing wing = new Wing(WingType.NONE);

		public BodyBuilder(Arm arm, Ass ass, Breast breast, Face face, Eye eye, Ear ear, Hair hair, Leg leg, Skin skin, BodyMaterial bodyMaterial, GenitalArrangement genitalArrangement, int height, int femininity, int bodySize, int muscle) {
			this.arm = arm;
			this.ass = ass;
			this.breast = breast;
			this.face = face;
			this.eye = eye;
			this.ear = ear;
			this.hair = hair;
			this.leg = leg;
			this.skin = skin;
			this.bodyMaterial = bodyMaterial;
			this.genitalArrangement = genitalArrangement;
			this.height = height;
			this.femininity = femininity;
			this.bodySize = bodySize;
			this.muscle = muscle;
		}
		
		public BodyBuilder antenna(Antenna antenna) {
			this.antenna = antenna;
			return this;
		}

		public BodyBuilder horn(Horn horn) {
			this.horn = horn;
			return this;
		}

		public BodyBuilder penis(Penis penis) {
			this.penis = penis;
			return this;
		}
		
		public BodyBuilder secondPenis(Penis secondPenis) {
			this.secondPenis = secondPenis;
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
			return new Body(this);
		}
	}

	private Body(BodyBuilder builder) {
		antenna = builder.antenna;
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
		secondPenis = builder.secondPenis;
		tail = builder.tail;
		vagina = builder.vagina;
		wing = builder.wing;
		race = builder.race;

		calculateRace(); // For determining RaceStage.
		
		bodyMaterial = builder.bodyMaterial;
		genitalArrangement = builder.genitalArrangement;
		
		height = builder.height;
		femininity = builder.femininity;
		bodySize =builder. bodySize;
		muscle= builder.muscle;
		
		this.pubicHair = BodyHair.NONE;

		allBodyParts = new ArrayList<>();
		allBodyParts.add(antenna);
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
		allBodyParts.add(secondPenis);
		allBodyParts.add(tail);
		allBodyParts.add(vagina);
		allBodyParts.add(wing);
		
		coverings = new EnumMap<>(BodyCoveringType.class);
		
		applyStartingCoveringValues();
		
		coveringsDiscovered = EnumSet.noneOf(BodyCoveringType.class);
		for(BodyPartInterface bp : allBodyParts) {
			if(bp.getType().getBodyCoveringType()!=null) {
				coveringsDiscovered.add(bp.getType().getBodyCoveringType());
			}
		}
	}
	
	
	private void applyStartingCoveringValues() {
		
		// Everything is based on human skin value:
		StartingSkinTone tone = StartingSkinTone.values()[Util.random.nextInt(StartingSkinTone.values().length)];
		
		List<Colour> suitableColours = tone.getAssociatedColours();
		
		List<Colour> colourApplicationList = new ArrayList<>();

		for (BodyCoveringType s : BodyCoveringType.values()) {
			
			// Specials:
			// orifice exterior/interior
			// makeup
			if(s == BodyCoveringType.MAKEUP_BLUSHER
					|| s == BodyCoveringType.MAKEUP_EYE_LINER
					|| s == BodyCoveringType.MAKEUP_EYE_SHADOW
					|| s == BodyCoveringType.MAKEUP_LIPSTICK
					|| s == BodyCoveringType.MAKEUP_NAIL_POLISH_FEET
					|| s == BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS) {
				coverings.put(s, new Covering(s,
						CoveringPattern.NONE,
						Colour.COVERING_NONE, false,
						Colour.COVERING_NONE, false));
				continue;
			}
			
			colourApplicationList.clear();
			colourApplicationList.addAll(s.getNaturalColoursPrimary());
			colourApplicationList.retainAll(suitableColours);
			if(colourApplicationList.isEmpty()) {
				colourApplicationList.addAll(s.getNaturalColoursPrimary());
			}
			Colour primary = colourApplicationList.get(Util.random.nextInt(colourApplicationList.size()));
			
			Colour secondary = primary;
			
			if(!s.getNaturalColoursSecondary().isEmpty()) {
				colourApplicationList.clear();
				colourApplicationList.addAll(s.getNaturalColoursSecondary());
				colourApplicationList.retainAll(suitableColours);
				if(colourApplicationList.isEmpty()) {
					colourApplicationList.addAll(s.getNaturalColoursSecondary());
				}
				secondary = colourApplicationList.get(Util.random.nextInt(colourApplicationList.size()));
			}
			
			coverings.put(s, new Covering(s,
					s.getNaturalPatterns().get(Util.random.nextInt(s.getNaturalPatterns().size())),
					primary, false,
					secondary, false));
		}

		updateBodyHairColour();


		updateAllSkinCoverings();
	}
	
	
	public List<BodyPartInterface> getAllBodyParts() {
		return allBodyParts;
	}

	/**
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
						+" Standing at full height, you measure [pc.heightFeetInches] ([pc.heightCm]cm).");
		} else {
			if(owner.getPlayerKnowsAreasMap().get(CoverableArea.PENIS) && owner.getPlayerKnowsAreasMap().get(CoverableArea.VAGINA)) {
				sb.append("<p>"
						+ "[npc.Name] is <span style='color:"+getGender().getColour().toWebHexString()+";'>[npc.a_gender]</span> [npc.raceStage] [npc.race]. "
						+ owner.getAppearsAsGenderDescription()
						+ " Standing at full height, [npc.she] measures [npc.heightFeetInches] ([npc.heightCm]cm).");
			} else {
				if(Main.game.getPlayer().hasPerk(Perk.OBSERVANT)) {
					sb.append("<p>"
							+ "Thanks to your observant perk, you can detect that [npc.name] is <span style='color:"+getGender().getColour().toWebHexString()+";'>[npc.a_gender]</span> [npc.raceStage] [npc.race]. "
							+ owner.getAppearsAsGenderDescription()
							+ " Standing at full height, [npc.she] measures [npc.heightFeetInches] ([npc.heightCm]cm).");
				} else {
					sb.append("<p>"
								+ "[npc.Name] is a [npc.raceStage] [npc.race]. "
								+ owner.getAppearsAsGenderDescription()
								+ " Standing at full height, [npc.she] measures [npc.heightFeetInches] ([npc.heightCm]cm).");
				}
			}
		}
		sb.append("</p>");
		
		// Describe face (ears, eyes & horns):
		// Femininity:
		if (owner.isPlayer()) {
			sb.append("<p>"
						+ "You have ");
		} else {
			sb.append("<p>"
						+ "[npc.She] has ");
		}
		
		if (Femininity.valueOf(femininity) == Femininity.MASCULINE_STRONG) {
			sb.append(
					UtilText.returnStringAtRandom(
							"a <span style='color:" + Colour.MASCULINE_PLUS.toWebHexString() + ";'>very masculine</span>",
							"an <span style='color:" + Colour.MASCULINE_PLUS.toWebHexString() + ";'>extremely handsome</span>"));
			
		} else if (Femininity.valueOf(femininity) == Femininity.MASCULINE) {
			sb.append(
					UtilText.returnStringAtRandom(
							"a <span style='color:" + Colour.MASCULINE.toWebHexString() + ";'>masculine</span>",
							"a <span style='color:" + Colour.MASCULINE.toWebHexString() + ";'>handsome</span>"));
			
		} else if (Femininity.valueOf(femininity) == Femininity.ANDROGYNOUS) {
			sb.append("an <span style='color:" + Colour.ANDROGYNOUS.toWebHexString() + ";'>androgynous</span>");
			
		} else if (Femininity.valueOf(femininity) == Femininity.FEMININE) {
			sb.append(
					UtilText.returnStringAtRandom(
							"a <span style='color:" + Colour.FEMININE.toWebHexString() + ";'>pretty</span>",
							"a <span style='color:" + Colour.FEMININE.toWebHexString() + ";'>feminine</span>",
							"a <span style='color:" + Colour.FEMININE.toWebHexString() + ";'>cute</span>"));
			
		} else {
			sb.append(
					UtilText.returnStringAtRandom(
							"a <span style='color:" + Colour.FEMININE_PLUS.toWebHexString() + ";'>very feminine</span>",
							"a <span style='color:" + Colour.FEMININE_PLUS.toWebHexString() + ";'>beautiful</span>",
							"a <span style='color:" + Colour.FEMININE_PLUS.toWebHexString() + ";'>gorgeous</span>"));
		}
		
		// Face and eyes:
		switch (face.getType()) {
			case HUMAN:
				sb.append(" face.");
				break;
			case ANGEL:
				sb.append(", perfectly proportioned face.");
				break;
			case DEMON_COMMON:
				sb.append(", perfectly proportioned face.");
				break;
			case DOG_MORPH:
				sb.append(", anthropomorphic dog-like face, complete with muzzle.");
				break;
			case LYCAN:
				sb.append(", anthropomorphic wolf-like face, complete with muzzle.");
				break;
			case CAT_MORPH:
				sb.append(", anthropomorphic cat-like face, with a cute little muzzle.");
				break;
			case COW_MORPH:
				sb.append(", anthropomorphic cow-like face, with a cute little muzzle.");
				break;
			case SQUIRREL_MORPH:
				sb.append(", anthropomorphic squirrel-like face, with a cute little muzzle.");
				break;
			case HORSE_MORPH:
				sb.append(", anthropomorphic horse-like face, with a long, horse-like muzzle.");
				break;
			case SLIME:
				if (owner.isPlayer()) {
					sb.append(" face, which is completely made out of [pc.faceColour] slime.");
				} else {
					sb.append(" face, which is completely made out of [npc.faceColour] slime.");
				}
				break;
			case HARPY:
				sb.append(", anthropomorphic bird-like face, complete with beak.");
				break;
		}

		if(owner.getBlusher().getPrimaryColour()!=Colour.COVERING_NONE) {
			if (owner.isPlayer()) {
				sb.append(" You are wearing "+owner.getBlusher().getColourDescriptor(true)+" blusher.");
			} else {
				sb.append(" [npc.She] is wearing "+owner.getBlusher().getColourDescriptor(true)+" blusher.");
			}
		}
		
		// Hair:
		
		if (owner.isPlayer() && hair.getRawLengthValue() == 0) {
			sb.append(" You are completely bald.");
			
		} else if (!owner.isPlayer() && hair.getRawLengthValue() == 0) {
			sb.append(" [npc.She] is completely bald.");
			
		} else {

			if (owner.isPlayer()) {
				sb.append(" You have [pc.hairLength], [pc.hairColour(true)]");
			} else {
				sb.append(" [npc.She] has [npc.hairLength], [npc.hairColour(true)]");
			}
			
			switch (hair.getType()) {
				case HUMAN:
					sb.append(" hair");
					break;
				case DEMON_COMMON:
					sb.append(", silken hair");
					break;
				case ANGEL:
					sb.append(", silken hair");
					break;
				case DOG_MORPH:
					sb.append(", fur-like hair");
					break;
				case LYCAN:
					sb.append(", fur-like hair");
					break;
				case CAT_MORPH:
					sb.append(", fur-like hair");
					break;
				case COW_MORPH:
					sb.append(", fur-like hair");
					break;
				case SQUIRREL_MORPH:
					sb.append(", fur-like hair");
					break;
				case HORSE_MORPH:
					sb.append(", horse-like hair");
					break;
				case SLIME:
					sb.append(" slime-hair");
					break;
				case HARPY:
					sb.append(" feathers in place of hair");
					break;
			
			}
			switch (hair.getStyle()) {
				case BRAIDED:
					sb.append(", which "+(hair.getType().isDefaultPlural()?"have":"has")+" been woven into a long braid.");
					break;
				case CURLY:
					sb.append(", which "+(hair.getType().isDefaultPlural()?"have":"has")+" been curled and left loose.");
					break;
				case LOOSE:
					sb.append(", which "+(hair.getType().isDefaultPlural()?"are":"is")+" left loose and unstyled.");
					break;
				case NONE:
					sb.append(", which "+(hair.getType().isDefaultPlural()?"are":"is")+" unstyled.");
					break;
				case PONYTAIL:
					sb.append(", which "+(hair.getType().isDefaultPlural()?"have":"has")+" been styled into a ponytail.");
					break;
				case STRAIGHT:
					sb.append(", which "+(hair.getType().isDefaultPlural()?"have":"has")+" been straightened and left loose.");
					break;
				case TWIN_TAILS:
					sb.append(", which "+(hair.getType().isDefaultPlural()?"have":"has")+" been styled into twin tails.");
					break;
				case WAVY:
					sb.append(", which "+(hair.getType().isDefaultPlural()?"have":"has")+" been styled into waves and left loose.");
					break;
				case MOHAWK:
					sb.append(", which "+(hair.getType().isDefaultPlural()?"have":"has")+" been styled into a mohawk.");
					break;
				case SIDECUT:
					sb.append(", which "+(hair.getType().isDefaultPlural()?"have":"has")+" been styled into a sidecut.");
					break;
			}
		}
		
		// Horns:
		
		switch (horn.getType()) {
			case NONE:
				sb.append("");
				break;
			case DEMON_COMMON_MALE:
				if (owner.isPlayer())
					sb.append(" "+Util.capitaliseSentence(horn.getDeterminer(owner))+" short, curved horns protrude from your upper forehead.");
				else
					sb.append(" "+Util.capitaliseSentence(horn.getDeterminer(owner))+" short, curved horns protrude from [npc.her] upper forehead.");
				break;
			case DEMON_COMMON_FEMALE:
				if (owner.isPlayer())
					sb.append(" "+Util.capitaliseSentence(horn.getDeterminer(owner))+" long, swept-back horns protrude from your upper forehead.");
				else
					sb.append(" "+Util.capitaliseSentence(horn.getDeterminer(owner))+" long, swept-back horns protrude from [npc.her] upper forehead.");
				break;
			case BOVINE_MALE:
				if (owner.isPlayer())
					sb.append(" "+Util.capitaliseSentence(horn.getDeterminer(owner))+" long, swept-back horns protrude from your upper temples.");
				else
					sb.append(" "+Util.capitaliseSentence(horn.getDeterminer(owner))+" long, swept-back horns protrude from [npc.her] upper temples.");
				break;
			case BOVINE_FEMALE:
				if (owner.isPlayer())
					sb.append(" "+Util.capitaliseSentence(horn.getDeterminer(owner))+" short, curved horns protrude from your upper temples.");
				else
					sb.append(" "+Util.capitaliseSentence(horn.getDeterminer(owner))+" short, curved horns protrude from [npc.her] upper temples.");
				break;
			default:
				if (owner.isPlayer())
					sb.append(" [pc.A_horns+] protrude from your upper forehead.");
				else
					sb.append(" [npc.A_horns+] protrude from [npc.her] upper forehead.");
		}
		
		// Antenna:
		
		switch (antenna.getType()) {
			case NONE:
				sb.append("");
				break;
			default:
				if (owner.isPlayer())
					sb.append(" [pc.A_antennae+] protrude from your upper forehead.");
				else
					sb.append(" [npc.A_antennae+] protrude from [npc.her] upper forehead.");
		}
		
		// Nose:
		
		if(face.isPiercedNose()) {
			if (owner.isPlayer()) {
				sb.append(" Your [pc.nose] has been pierced.");
			} else {
				sb.append(" [npc.Her] [npc.nose] has been pierced.");
			}
		}
		
		// Eyes:
		
		if (owner.isPlayer()) {
			sb.append(" You have [pc.eyePairs] ");
		} else {
			sb.append(" [npc.She] has [npc.eyePairs] ");
		}
		
		switch(eye.getType()) {
			case ANGEL:
				sb.append(" angelic eyes");
				break;
			case CAT_MORPH:
				sb.append(" cat-like eyes");
				break;
			case COW_MORPH:
				sb.append(" cow-like eyes");
				break;
			case DEMON_COMMON:
				sb.append(" demonic eyes");
				break;
			case DOG_MORPH:
				sb.append(" dog-like eyes");
				break;
			case HARPY:
				sb.append(" bird-like eyes");
				break;
			case HORSE_MORPH:
				sb.append(" horse-like eyes");
				break;
			case HUMAN:
				sb.append(" normal, human eyes");
				break;
			case LYCAN:
				sb.append(" wolf-like eyes");
				break;
			case SLIME:
				sb.append(" slime eyes");
				break;
			case SQUIRREL_MORPH:
				sb.append(" squirrel-like eyes");
				break;
		}
		
		if (owner.isPlayer()) {
			if(owner.getEyeIrisCovering().getPattern() == CoveringPattern.EYE_IRISES_HETEROCHROMATIC) {
				sb.append(", with [pc.irisShape], heterochromatic [pc.irisPrimaryColour(true)]-and-[pc.irisSecondaryColour(true)] irises ");
			} else {
				sb.append(", with [pc.irisShape], [pc.irisPrimaryColour(true)] irises ");
			}
			
			if(owner.getEyePupilCovering().getPattern() == CoveringPattern.EYE_PUPILS_HETEROCHROMATIC) {
				sb.append("and [pc.pupilShape], heterochromatic [pc.pupilPrimaryColour(true)]-and-[pc.pupilSecondaryColour(true)] pupils.");
			} else {
				sb.append("and [pc.pupilShape], [pc.pupilPrimaryColour(true)] pupils.");
			}
		} else {
			if(owner.getEyeIrisCovering().getPattern() == CoveringPattern.EYE_IRISES_HETEROCHROMATIC) {
				sb.append(", with [npc.irisShape], heterochromatic [npc.irisPrimaryColour(true)]-and-[npc.irisSecondaryColour(true)] irises, ");
			} else {
				sb.append(", with [npc.irisShape], [npc.irisPrimaryColour(true)] irises ");
			}
			
			if(owner.getEyePupilCovering().getPattern() == CoveringPattern.EYE_PUPILS_HETEROCHROMATIC) {
				sb.append("and [npc.pupilShape], heterochromatic [npc.pupilPrimaryColour(true)]-and-[npc.pupilSecondaryColour(true)] pupils.");
			} else {
				sb.append("and [npc.pupilShape], [npc.pupilPrimaryColour(true)] pupils.");
			}
		}
		
		// Eye makeup:
		if(owner.getEyeLiner().getPrimaryColour()!=Colour.COVERING_NONE) {
			if(owner.isPlayer()) {
				sb.append(" Around your [pc.eyes], you've got a layer of "+owner.getEyeLiner().getColourDescriptor(true)+" eye liner.");
			} else {
				sb.append(" Around [npc.her] [npc.eyes], [npc.she]'s got a layer of "+owner.getEyeLiner().getColourDescriptor(true)+" eye liner.");
			}
		}
		if(owner.getEyeShadow().getPrimaryColour()!=Colour.COVERING_NONE) {
			if(owner.isPlayer()) {
				sb.append(" You're wearing a tasteful amount of "+owner.getEyeShadow().getColourDescriptor(true)+" eye shadow.");
			} else {
				sb.append(" [npc.She]'s wearing a tasteful amount of "+owner.getEyeShadow().getColourDescriptor(true)+" eye shadow.");
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
			case COW_MORPH:
				if (owner.isPlayer())
					sb.append(" You have a pair of "+(ear.isPierced() ? "pierced, " : "")+"cow-like ears, which are positioned high up on your head.");
				else
					sb.append(" [npc.She] has a pair of "+(ear.isPierced() ? "pierced, " : "")+"cow-like ears, which are positioned high up on [npc.her] head.");
				break;
			case SQUIRREL_MORPH:
				if (owner.isPlayer())
					sb.append(" You have a pair of "+(ear.isPierced() ? "pierced, " : "")+"rounded, squirrel-like ears, which are positioned high up on your head.");
				else
					sb.append(" [npc.She] has a pair of "+(ear.isPierced() ? "pierced, " : "")+"rounded, squirrel-like ears, which are positioned high up on [npc.her] head.");
				break;
			case HORSE_MORPH:
				if (owner.isPlayer())
					sb.append(" You have a pair of "+(ear.isPierced() ? "pierced, " : "")+"upright, horse-like ears, which are positioned high up on your head.");
				else
					sb.append(" [npc.She] has a pair of "+(ear.isPierced() ? "pierced, " : "")+"upright, horse-like ears, which are positioned high up on [npc.her] head.");
				break;
			case SLIME:
				if (owner.isPlayer())
					sb.append(" You have a pair of humanoid" + (ear.isPierced() ? ", pierced" : "") + " ears, made out of [pc.earColour] slime.");
				else
					sb.append(" [npc.She] has a pair of humanoid" + (ear.isPierced() ? ", pierced" : "") + " ears, made out of [npc.earColour] slime");
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
				if (owner.isPlayer()) {
					sb.append(" You have [pc.a_ears+]" + (ear.isPierced() ? ", which have been pierced." : "."));
				} else {
					sb.append(" [npc.She] has [npc.a_ears+]" + (ear.isPierced() ? ", which have been pierced." : "."));
				}
		}
		
		sb.append("</p>"
				+ "<p>");
		
		if(Main.game.isFacialHairEnabled()) {
			if(owner.isPlayer()) {
				switch(owner.getFacialHair()) {
					case NONE:
						if(!owner.isFeminine()) {
							sb.append(" You don't have any trace of facial "+owner.getFacialHairType().getName(owner, true)+".");
						}
						break;
					case MANICURED:
						sb.append(" You have a small amount of "+owner.getFacialHairType().getFullDescription(owner, true)+" growing on your [pc.face].");
						break;
					case TRIMMED:
						sb.append(" You have a well-trimmed beard of "+owner.getFacialHairType().getFullDescription(owner, true)+" growing on your [pc.face].");
						break;
					case BUSHY:
						sb.append(" You have a big bushy beard of "+owner.getFacialHairType().getFullDescription(owner, true)+" growing on your [pc.face].");
						break;
				}
				
			} else {
				switch(owner.getFacialHair()) {
					case NONE:
						if(!owner.isFeminine()) {
							sb.append(" [npc.She] doesn't have any trace of facial "+owner.getFacialHairType().getName(owner, true)+".");
						}
						break;
					case MANICURED:
						sb.append(" [npc.She] has a small amount of "+owner.getFacialHairType().getFullDescription(owner, true)+" growing on [npc.her] [npc.face].");
						break;
					case TRIMMED:
						sb.append(" [npc.She] has a well-trimmed beard of "+owner.getFacialHairType().getFullDescription(owner, true)+" growing on [npc.her] [npc.face].");
						break;
					case BUSHY:
						sb.append(" [npc.She] has a big bushy beard of "+owner.getFacialHairType().getFullDescription(owner, true)+" growing on [npc.her] [npc.face].");
						break;
				}
			}
		}
		
		// Mouth & lips:
		if (owner.isPlayer()) {
			sb.append(" You have [pc.lipSize], [pc.mouthColourPrimary(true)] [pc.lips]");
			if(owner.getLipstick().getPrimaryColour()!=Colour.COVERING_NONE) {
				sb.append((owner.isPiercedLip()?", which have been pierced, and":", which")+" are currently covered in "+owner.getLipstick().getColourDescriptor(true)+" lipstick.");
			} else {
				sb.append((owner.isPiercedLip()?", which have been pierced.":"."));
			}
			sb.append(" Your throat is [pc.mouthColourSecondary(true)] in colour.");
		} else {
			sb.append(" [npc.She] has [npc.lipSize], [npc.mouthColourPrimary(true)] [pc.lips]");
			if(owner.getLipstick().getPrimaryColour()!=Colour.COVERING_NONE) {
				sb.append((owner.isPiercedLip()?", which have been pierced, and":", which")+" are currently covered in "+owner.getLipstick().getColourDescriptor(true)+" lipstick.");
			} else {
				sb.append((owner.isPiercedLip()?", which have been pierced.":"."));
			}
			sb.append(" [npc.Her] throat is [npc.mouthColourSecondary(true)] in colour.");
		}
		
		// Throat modifiers:
		for(OrificeModifier om : OrificeModifier.values()) {
			if(owner.hasFaceOrificeModifier(om)) {
				if(owner.isPlayer()) {
					switch(om) {
						case PUFFY:
							sb.append(" Your [pc.lips] have swollen up to be far puffier than what would be considered normal.");
							break;
						case MUSCLE_CONTROL:
							sb.append(" You have a series of internal muscles lining the inside of your throat, allowing you to expertly squeeze and grip down on any intruding object.");
							break;
						case RIBBED:
							sb.append(" The inside of your throat is lined with sensitive, fleshy ribs, which grant you extra pleasure when stimulated.");
							break;
						case TENTACLED:
							sb.append(" Your throat is filled with tiny little tentacles, which wriggle and squirm with a mind of their own.");
							break;
					}
				} else {
					switch(om) {
						case PUFFY:
							sb.append(" [npc.Her] [npc.lips] have swollen up to be far puffier than what would be considered normal.");
							break;
						case MUSCLE_CONTROL:
							sb.append(" [npc.She] has a series of internal muscles lining the inside of [npc.her] throat, allowing [npc.herHim] to expertly squeeze and grip down on any intruding object.");
							break;
						case RIBBED:
							sb.append(" The inside of [npc.her] throat is lined with sensitive, fleshy ribs, which grant [npc.herHim] extra pleasure when stimulated.");
							break;
						case TENTACLED:
							sb.append(" [npc.Her] throat is filled with tiny little tentacles, which wriggle and squirm with a mind of their own.");
							break;
					}
				}
			}
		}
		
		
		// Tongue & blowjob:
		if (owner.isPlayer()) {
			sb.append(" Your mouth holds [pc.a_tongueLength], [pc.tongueColour(true)] [pc.tongue]"+ (face.getTongue().isPierced() ? ", which has been pierced." : "."));
		} else {
			sb.append(" [npc.Her] mouth holds [npc.a_tongueLength], [npc.tongueColour(true)] [npc.tongue]"+ (face.getTongue().isPierced() ? ", which has been pierced." : "."));
		}
		
		for(TongueModifier tm : TongueModifier.values()) {
			if(owner.hasTongueModifier(tm)) {
				if(owner.isPlayer()) {
					switch(tm) {
						case RIBBED:
							sb.append(" It's lined with hard, fleshy ribs, which are sure to grant extra pleasure to any orifice that they penetrate.");
							break;
						case TENTACLED:
							sb.append(" A series of little tentacles coat its surface, which wriggle and squirm with a mind of their own.");
							break;
						case BIFURCATED:
							sb.append(" Near the tip, it's split in two, leaving your tongue bifurcated, like a snake.");
							break;
					}
				} else {
					switch(tm) {
						case RIBBED:
							sb.append(" It's lined with hard, fleshy ribs, which are sure to grant extra pleasure to any orifice that they penetrate.");
							break;
						case TENTACLED:
							sb.append(" A series of little tentacles coat its surface, which wriggle and squirm with a mind of their own.");
							break;
						case BIFURCATED:
							sb.append(" Near the tip, it's split in two, leaving [npc.her] tongue bifurcated, like a snake.");
							break;
					}
				}
			}
		}
		
		
		
		if (owner.isPlayer()) {
			if (face.getMouth().getOrificeMouth().isVirgin()) {
				sb.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You've never given head before, so you don't know what you could fit down your throat.</span>");
			} else {
				switch(face.getMouth().getOrificeMouth().getCapacity().getMaximumSizeComfortableWithLube()) {
//					case NEGATIVE_UTILITY_VALUE:
					case ZERO_MICROSCOPIC:
						sb.append(" [style.colourSex(You're terrible at giving head)], and struggle to fit the tip of even a tiny cock into your mouth without gagging.");
						break;
					case ONE_TINY:
						sb.append(" [style.colourSex(You're really bad at giving head)], and struggle to fit even a tiny cocks into your mouth without gagging.");
//						sb.append(" You find anything larger than "+face.getRawCapacityValue()+" inches to be uncomfortable.");
						break;
					case TWO_AVERAGE:
						sb.append(" [style.colourSex(You're not great at giving head)], and anything larger than an average-sized human cock will cause you to gag.");
//						sb.append(" You find anything larger than "+face.getRawCapacityValue()+" inches to be uncomfortable.");
						break;
					case THREE_LARGE:
						sb.append(" [style.colourSex(You're somewhat competent at giving head)], and can suppress your gag reflex enough to comfortably suck large cocks.");
//						sb.append(" You find anything larger than "+face.getRawCapacityValue()+" inches to be uncomfortable.");
						break;
					case FOUR_HUGE:
						sb.append(" [style.colourSex(You're pretty good at giving head)], and can comfortably suck huge cocks without gagging.");
//						sb.append(" You find anything larger than "+face.getRawCapacityValue()+" inches to be uncomfortable.");
						break;
					case FIVE_ENORMOUS:
						sb.append(" [style.colourSex(You're somewhat of an expert at giving head)], and can suck enormous cocks without too much difficulty.");
//						sb.append(" You find anything larger than "+face.getRawCapacityValue()+" inches to be uncomfortable.");
						break;
					case SIX_GIGANTIC:
						sb.append(" [style.colourSex(You're amazing at giving head)], and can comfortably suck all but the most absurdly-sized of cocks with ease.");
//						sb.append(" You find anything larger than "+face.getRawCapacityValue()+" inches to be uncomfortable.");
						break;
					case SEVEN_STALLION:
						sb.append(" [style.colourSex(You are)]"
								+ " [style.colourLegendary(legendary)]"
								+ " [style.colourSex(at giving head)]; it's almost as though your throat was purposefully designed to fit phallic objects of any size or shape.");
						break;
					default:
						break;
				}
				for(PenetrationType pt : PenetrationType.values()) {
					if(Main.game.getPlayer().getVirginityLoss(new SexType(pt, OrificeType.MOUTH_PLAYER))!=null) {
						sb.append(" <span style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>The first time you performed oral sex was to "
								+ Main.game.getPlayer().getVirginityLoss(new SexType(pt, OrificeType.MOUTH_PLAYER)) + ".</span>");
					}
				}
			}
		} else {
			
			if (owner.getPlayerKnowsAreasMap().get(CoverableArea.MOUTH)) {
				if (face.getMouth().getOrificeMouth().isVirgin()) {
					sb.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.She]'s never given head before.</span>");
				} else {
					switch(face.getMouth().getOrificeMouth().getCapacity().getMaximumSizeComfortableWithLube()) {
//					case NEGATIVE_UTILITY_VALUE:
					case ZERO_MICROSCOPIC:
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
		sb.append("</p>");

		
		// Describe body:
		
		sb.append("<p>");
		if (owner.isPlayer()) {
			sb.append("Your torso has");
		} else {
			sb.append("[npc.Her] torso has");
		}
		if (femininity <= Femininity.MASCULINE_STRONG.getMaximumFemininity()) {
			sb.append(UtilText.returnStringAtRandom(
							" an <span style='color:" + Colour.MASCULINE_PLUS.toWebHexString() + ";'>extremely masculine</span> appearance",
							" a <span style='color:" + Colour.MASCULINE_PLUS.toWebHexString() + ";'>very masculine</span> appearance"));
			
		} else if (femininity <= Femininity.MASCULINE.getMaximumFemininity()) {
			sb.append(UtilText.returnStringAtRandom(
							" a <span style='color:" + Colour.MASCULINE.toWebHexString() + ";'>masculine</span> appearance",
							" a <span style='color:" + Colour.MASCULINE.toWebHexString() + ";'>boyish</span> appearance"));
			
		} else if (femininity <= Femininity.ANDROGYNOUS.getMaximumFemininity()) {
			sb.append(UtilText.returnStringAtRandom(
					" a very <span style='color:" + Colour.ANDROGYNOUS.toWebHexString() + ";'>androgynous</span> appearance"));
			
		} else if (femininity <= Femininity.FEMININE.getMaximumFemininity()) {
			sb.append(UtilText.returnStringAtRandom(
					" a <span style='color:" + Colour.FEMININE.toWebHexString() + ";'>feminine</span> appearance",
					" a <span style='color:" + Colour.FEMININE.toWebHexString() + ";'>pretty</span> appearance"));
			
		} else {
			sb.append(UtilText.returnStringAtRandom(
					" an <span style='color:" + Colour.FEMININE_PLUS.toWebHexString() + ";'>extremely feminine</span> appearance",
					" a <span style='color:" + Colour.FEMININE_PLUS.toWebHexString() + ";'>gorgeous</span> appearance",
					" a <span style='color:" + Colour.FEMININE_PLUS.toWebHexString() + ";'>jaw-droppingly beautiful</span> appearance"));
		}
		
		if (owner.isPlayer()) {
			sb.append(", and is covered in [pc.skinFullDescription(true)].");
		} else {
			sb.append(", and is covered in [npc.skinFullDescription(true)].");
		}
		
		if (owner.isPlayer()) {
			sb.append(" You have <span style='color:"+ owner.getBodySize().getColour().toWebHexString() + ";'>[pc.a_bodySize]</span>, "
							+ "<span style='color:"+ owner.getMuscle().getColour().toWebHexString() + ";'>[pc.muscle]</span>"
									+ " body, which gives you <span style='color:"+ owner.getBodyShape().toWebHexStringColour() + ";'>[pc.a_bodyShape]</span> body shape.");
		} else {
			sb.append(" [npc.She] has <span style='color:"+ BodySize.valueOf(getBodySize()).getColour().toWebHexString() + ";'>" + BodySize.valueOf(getBodySize()).getName(true) + "</span>, "
							+ "<span style='color:"+ Muscle.valueOf(getMuscle()).getColour().toWebHexString() + ";'>" +Muscle.valueOf(getMuscle()).getName(false) + "</span>"
								+ " body, giving [npc.herHim] <span style='color:"+ owner.getBodyShape().toWebHexStringColour() + ";'>[npc.a_bodyShape]</span> body shape.");
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
		sb.append("</p>");
		
		
		
		// Breasts:
		
		sb.append("<p>");
		if(owner.isPlayer()){
			if(breast.getRawSizeValue()>0){
				sb.append(" You have " + Util.intToString(breast.getRows()) + " pair" + (breast.getRows() == 1 ? "" : "s") + " of [pc.breastSize] [pc.breasts]");
				switch(breast.getRows()){
					case 1:
						sb.append(", which fit comfortably into ");
						if (breast.getSize() == CupSize.TRAINING) {
							sb.append("a training bra.");
						} else {
							sb.append("[pc.a_cupSize]-cup bra.");
						}
						break;
						
					case 2:
						sb.append(". Your top pair of breasts fit comfortably into ");
						if (breast.getSize() == CupSize.TRAINING) {
							sb.append("a training bra,");
						} else {
							sb.append("[pc.a_cupSize]-cup bra,");
						}
						if (breast.getSize().getMeasurement()-1 == 0) {
							sb.append(" and the pair below them are completely flat.");
							
						} else {
							sb.append(" and the pair below them fit into ");
							if (CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-1) == CupSize.TRAINING) {
								sb.append("a training bra.");
							} else {
								String cupSize = CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-1).getCupSizeName();
								sb.append(UtilText.generateSingularDeterminer(cupSize)+" "+cupSize+"-cup bra.");
							}
						}
						break;
						
					case 3:
						sb.append(". Your top pair of breasts fit comfortably into ");
						if (breast.getSize() == CupSize.TRAINING) {
							sb.append("a training bra,");
						} else {
							sb.append("[pc.a_cupSize]-cup bra,");
						}
						if (breast.getSize().getMeasurement()-1 == 0) {
							sb.append(" while your second and third pairs are completely flat.");
						} else {
							sb.append(" your second pair into ");
							if (CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-1) == CupSize.TRAINING) {
								sb.append("a training bra,");
							} else {
								String cupSize = CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-1).getCupSizeName();
								sb.append(UtilText.generateSingularDeterminer(cupSize)+" "+cupSize+"-cup bra,");
							}
							if (breast.getSize().getMeasurement()-2 == 0) {
								sb.append(" and your third, lowest pair are completely flat.");
							} else {
								sb.append(" and your third, lowest pair fit into ");
								if (CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-2) == CupSize.TRAINING) {
									sb.append("a training bra.");
								} else {
									String cupSize = CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-2).getCupSizeName();
									sb.append(UtilText.generateSingularDeterminer(cupSize)+" "+cupSize+"-cup bra.");
								}
							}
						}
						break;
						
					default:break;
				}
				
			} else {
				sb.append(" You have a completely flat chest");
				switch(breast.getRows()){
					case 1:
						sb.append(", with a single pair of pecs.");
						break;
					case 2:
						sb.append(", with two pairs of pecs.");
						break;
					case 3:
						sb.append(", with three pairs of pecs.");
						break;
					default:
						break;
				}
			}
			
		}else{
			if(breast.getRawSizeValue()>0){
				sb.append(" [npc.She] has " + Util.intToString(breast.getRows()) + " pair" + (breast.getRows() == 1 ? "" : "s") + " of [npc.breastSize] [npc.breasts]");
				switch(breast.getRows()){
					case 1:
						sb.append(", which fit comfortably into ");
						if (breast.getSize() == CupSize.TRAINING) {
							sb.append("a training bra.");
						} else {
							sb.append("[npc.a_cupSize]-cup bra.");
						}
						break;
						
					case 2:
						sb.append(". [npc.Her] top pair of breasts fit comfortably into ");
						if (breast.getSize() == CupSize.TRAINING) {
							sb.append("a training bra,");
						} else {
							sb.append("[npc.a_cupSize]-cup bra,");
						}
						if (breast.getSize().getMeasurement()-1 == 0) {
							sb.append(" and the pair below them are completely flat.");
							
						} else {
							sb.append(" and the pair below them fit into ");
							if (CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-1) == CupSize.TRAINING) {
								sb.append("a training bra.");
							} else {
								String cupSize = CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-1).getCupSizeName();
								sb.append(UtilText.generateSingularDeterminer(cupSize)+" "+cupSize+"-cup bra.");
							}
						}
						break;
						
					case 3:
						sb.append(". [npc.Her] top pair of breasts fit comfortably into ");
						if (breast.getSize() == CupSize.TRAINING) {
							sb.append("a training bra,");
						} else {
							sb.append("[npc.a_cupSize]-cup bra,");
						}
						if (breast.getSize().getMeasurement()-1 == 0) {
							sb.append(" while [npc.her] second and third pairs are completely flat.");
						} else {
							sb.append(" [npc.her] second pair into ");
							if (CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-1) == CupSize.TRAINING) {
								sb.append("a training bra,");
							} else {
								String cupSize = CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-1).getCupSizeName();
								sb.append(UtilText.generateSingularDeterminer(cupSize)+" "+cupSize+"-cup bra,");
							}
							if (breast.getSize().getMeasurement()-2 == 0) {
								sb.append(" and [npc.her] third, lowest pair are completely flat.");
							} else {
								sb.append(" and [npc.her] third, lowest pair fit into ");
								if (CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-2) == CupSize.TRAINING) {
									sb.append("a training bra.");
								} else {
									String cupSize = CupSize.getCupSizeFromInt(breast.getSize().getMeasurement()-2).getCupSizeName();
									sb.append(UtilText.generateSingularDeterminer(cupSize)+" "+cupSize+"-cup bra.");
								}
							}
						}
						break;
						
					default:break;
				}
				
			} else {
				sb.append(" [npc.She] has a completely flat chest");
				switch(breast.getRows()){
					case 1:
						sb.append(", with a single pair of pecs.");
						break;
					case 2:
						sb.append(", with two pairs of pecs.");
						break;
					case 3:
						sb.append(", with three pairs of pecs.");
						break;
					default:
						break;
				}
			}
		}
		
		// Nipples & piercings
		
		sb.append(" " + getBreastDescription(owner));
		sb.append("</p>");

		// Arms and legs:

		sb.append("<p>");
		// Arms:
		String armDeterminer = "a pair of";
		if(arm.getArmRows()==3) {
			armDeterminer = "three pairs of";
		} else if(arm.getArmRows()==2) {
			armDeterminer = "two pairs of";
		}
		switch (arm.getType()) {
			case HUMAN:
				if (owner.isPlayer())
					sb.append("You have "+armDeterminer+" normal human arms and hands, which are covered in [pc.armFullDescription(true)].");
				else
					sb.append("[npc.She] has "+armDeterminer+" normal human arms and hands, which are covered in [npc.armFullDescription(true)].");
				break;
			case ANGEL:
				if (owner.isPlayer())
					sb.append("You have "+armDeterminer+" human-like arms and hands, which are covered in [pc.armFullDescription(true)].");
				else
					sb.append("[npc.She] has "+armDeterminer+" human-like arms and hands, which are covered in [npc.armFullDescription(true)].");
				break;
			case DEMON_COMMON:
				if (owner.isPlayer())
					sb.append("You have "+armDeterminer+" slender, human-looking arms and hands, which are covered in [pc.armFullDescription(true)].");
				else
					sb.append("[npc.She] has "+armDeterminer+" slender human-looking arms and hands, which are covered in [npc.armFullDescription(true)].");
				break;
			case DOG_MORPH:
				if (owner.isPlayer())
					sb.append("You have "+armDeterminer+" arms, which are covered in [pc.armFullDescription(true)]."
								+ " Your hands are formed into anthropomorphic, dog-like hands, complete with little blunt claws and leathery pads.");
				else
					sb.append("[npc.She] has "+armDeterminer+" arms, which are covered in [npc.armFullDescription(true)]."
								+ " [npc.Her] hands are formed into anthropomorphic, dog-like hands, complete with little blunt claws and leathery pads.");
				break;
			case LYCAN:
				if (owner.isPlayer())
					sb.append("You have "+armDeterminer+" arms, which are covered in [pc.armFullDescription(true)]."
								+ " Your hands are formed into anthropomorphic, wolf-like hands, complete with sharp claws and tough leathery pads.");
				else
					sb.append("[npc.She] has "+armDeterminer+" arms, which are covered in [npc.armFullDescription(true)]."
							+ " [npc.Her] hands are formed into anthropomorphic, wolf-like hands, complete with sharp claws and tough leathery pads.");
				break;
			case CAT_MORPH:
				if (owner.isPlayer())
					sb.append("You have "+armDeterminer+" arms, which are covered in [pc.armFullDescription(true)]."
							+ " Your hands are formed into anthropomorphic, cat-like hands, complete with retractable claws and pink pads.");
				else
					sb.append("[npc.She] has "+armDeterminer+" arms, which are covered in [npc.armFullDescription(true)]."
							+ " [npc.Her] hands are formed into anthropomorphic, cat-like hands, complete with retractable claws and pink pads.");
				break;
			case COW_MORPH:
				if (owner.isPlayer())
					sb.append("You have "+armDeterminer+" arms, which are covered in [pc.armFullDescription(true)]."
								+ " Your hands, while human in shape, have tough little hoof-like nails.");
				else
					sb.append("[npc.She] has "+armDeterminer+" arms, which are covered in [npc.armFullDescription(true)]."
							+ " [npc.Her] hands, while human in shape, have tough little hoof-like nails.");
				break;
			case SQUIRREL_MORPH:
				if (owner.isPlayer())
					sb.append("You have "+armDeterminer+" arms, which are covered in [pc.armFullDescription(true)]."
							+ " Your hands are formed into anthropomorphic, squirrel-like hands, complete with claws.");
				else
					sb.append("[npc.She] has "+armDeterminer+" arms, which are covered in [npc.armFullDescription(true)]."
							+ " [npc.Her] hands are formed into anthropomorphic, squirrel-like hands, complete with claws.");
				break;
			case HORSE_MORPH:
				if (owner.isPlayer())
					sb.append("You have "+armDeterminer+" arms, which are covered in [pc.armFullDescription(true)]."
								+ " Your hands, while human in shape, have tough little hoof-like nails.");
				else
					sb.append("[npc.She] has "+armDeterminer+" arms, which are covered in [npc.armFullDescription(true)]."
							+ " [npc.Her] hands, while human in shape, have tough little hoof-like nails.");
				break;
			case SLIME:
				if (owner.isPlayer())
					sb.append("Your arms, although human-shaped, are formed out of [pc.armFullDescription(true)].");
				else
					sb.append("[npc.Her] arms, although human-shaped, are formed out of [npc.armFullDescription(true)].");
				break;
			case HARPY:
				if (owner.isPlayer())
					sb.append("Your arms have transformed into "+armDeterminer+" huge wings, and are covered in beautiful [pc.armFullDescription(true)]."
							+ " Where your hands should be, you have a single clawed thumb. Thankfully, you are still able to wrap your wings around objects to form a hand-like grip.");
				else
					sb.append("In place of arms and hands, [npc.she] has "+armDeterminer+" huge wings, which are covered in beautiful [npc.armFullDescription(true)]."
							+ " Where [npc.her] hands should be, [npc.she] has a single clawed thumb, which, when [npc.she] wraps [npc.her] wings around, can grasp objects in a hand-like grip.");
				break;
			default:
				break;
		}
		
		if(owner.isPlayer()) {
			if(owner.getHandNailPolish().getPrimaryColour() != Colour.COVERING_NONE) {
				if(owner.getArmType()==ArmType.HARPY) {
					sb.append(" The little claw on your thumb has been painted in "+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS).getFullDescription(owner, true)+".");
				} else {
					sb.append(" Your fingernails have been painted in "+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS).getFullDescription(owner, true)+".");
				}
			}
		} else {
			if(owner.getHandNailPolish().getPrimaryColour() != Colour.COVERING_NONE) {
				if(owner.getArmType()==ArmType.HARPY) {
					sb.append(" The little claw on [npc.her] thumb has been painted in "+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS).getFullDescription(owner, true)+".");
				} else {
					sb.append(" [npc.Her] fingernails have been painted in "+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS).getFullDescription(owner, true)+".");
				}
			}
		}
		
		if(Main.game.isBodyHairEnabled()) {
			if(owner.isPlayer()) {
				switch(owner.getUnderarmHair()) {
					case NONE:
						sb.append(" There is no trace of any "+owner.getUnderarmHairType().getName(owner)+" in your armpits.");
						break;
					case MANICURED:
						sb.append(" You have a well-manicured patch of "+owner.getUnderarmHairType().getFullDescription(owner, true)+" in each of your armpits.");
						break;
					case TRIMMED:
						sb.append(" You have a trimmed patch of "+owner.getUnderarmHairType().getFullDescription(owner, true)+" in each of your armpits.");
						break;
					case BUSHY:
						sb.append(" You have a thick mass of "+owner.getUnderarmHairType().getFullDescription(owner, true)+" in each of your armpits.");
						break;
				}
				
			} else {
				switch(owner.getUnderarmHair()) {
					case NONE:
						sb.append(" There is no trace of any "+owner.getUnderarmHairType().getName(owner, true)+" in [npc.her] armpits.");
						break;
					case MANICURED:
						sb.append(" [npc.She] has a well-manicured patch of "+owner.getUnderarmHairType().getFullDescription(owner, true)+" in each of [npc.her] armpits.");
						break;
					case TRIMMED:
						sb.append(" [npc.She] has a trimmed patch of "+owner.getUnderarmHairType().getFullDescription(owner, true)+" in each of [npc.her] armpits.");
						break;
					case BUSHY:
						sb.append(" [npc.She] has a thick mass of "+owner.getUnderarmHairType().getFullDescription(owner, true)+" in each of [npc.her] armpits.");
						break;
				}
			}
		}

		sb.append("</br>");
		
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
			case SQUIRREL_MORPH:
				if (owner.isPlayer())
					sb.append("Your legs are covered in <span style='color:[pc.legColourHex];'>[pc.legColour] [pc.legSkin]</span>,"
							+ " and your feet are formed into anthropomorphic squirrel-like paws, complete with claws and pink pads.");
				else
					sb.append("[npc.Her] legs are covered in <span style='color:[npc.legColourHex];'>[npc.legColour] [npc.legSkin]</span>,"
							+ " and [npc.her] feet are formed into anthropomorphic squirrel-like paws, complete with claws and pink pads.");
				break;
			case HORSE_MORPH:
				if (owner.isPlayer())
					sb.append("Your legs are covered in <span style='color:[pc.legColourHex];'>[pc.legColour] [pc.legSkin]</span>,"
							+ " and your feet are formed into anthropomorphic horse-like hooves.");
				else
					sb.append("[npc.Her] legs are covered in <span style='color:[npc.legColourHex];'>[npc.legColour] [npc.legSkin]</span>,"
							+ " and [npc.her] feet are formed into anthropomorphic horse-like hooves.");
				break;
			case COW_MORPH:
				if (owner.isPlayer())
					sb.append("Your legs are covered in <span style='color:[pc.legColourHex];'>[pc.legColour] [pc.legSkin]</span>,"
							+ " and your feet are formed into anthropomorphic cow-like hooves.");
				else
					sb.append("[npc.Her] legs are covered in <span style='color:[npc.legColourHex];'>[npc.legColour] [npc.legSkin]</span>,"
							+ " and [npc.her] feet are formed into anthropomorphic cow-like hooves.");
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
		
		if(owner.isPlayer()) {
			if(owner.getFootNailPolish().getPrimaryColour() != Colour.COVERING_NONE) {
				if(owner.getLegType()==LegType.HARPY) {
					sb.append(" The claws on your talons have been painted in "+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET).getFullDescription(owner, true)+".");
				} else if(owner.getLegType()==LegType.HORSE_MORPH) {
					sb.append(" Your hooves have been painted in "+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET).getFullDescription(owner, true)+".");

				} else if(owner.getLegType()==LegType.COW_MORPH) {
					sb.append(" Your hooves have been painted in "+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET).getFullDescription(owner, true)+".");


				} else {
					sb.append(" Your toenails have been painted in "+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET).getFullDescription(owner, true)+".");
				}
			}
		} else {
			if(owner.getFootNailPolish().getPrimaryColour() != Colour.COVERING_NONE) {
				if(owner.getLegType()==LegType.HARPY) {
					sb.append(" The claws on [npc.her] talons have been painted in "+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET).getFullDescription(owner, true)+".");
				} else if(owner.getLegType()==LegType.HORSE_MORPH) {
					sb.append(" [npc.Her] hooves have been painted in "+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET).getFullDescription(owner, true)+".");

				} else if(owner.getLegType()==LegType.COW_MORPH) {
					sb.append(" [npc.Her] hooves have been painted in "+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET).getFullDescription(owner, true)+".");


				} else {
					sb.append(" [npc.Her] toenails have been painted in "+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET).getFullDescription(owner, true)+".");
				}
			}
		}

		sb.append("</br>");
		
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
		if(wing.getType()!=WingType.NONE || tail.getType()!=TailType.NONE) {
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

			if(tail.getType()!=TailType.NONE) {
				if (owner.isPlayer()) {
					sb.append(" Growing out from just above your ass, you have ");
				} else {
					sb.append(" Growing out from just above [npc.her] ass, [npc.she] has ");
				}
			}
			
			if(owner.getTailCount()==1) {
				switch(owner.getTailType()){
					case CAT_MORPH:
						if (owner.isPlayer()) {
							sb.append("a furry, [pc.tailColour(true)] cat-like tail, which you can control well enough to grant you significantly improved balance.");
						} else {
							sb.append("a furry, [npc.tailColour(true)] cat-like tail, which [npc.she] can control well enough to grant [npc.herHim] significantly improved balance.");
						}
						break;
					case DEMON_COMMON:
						if (owner.isPlayer()) {
							sb.append("a spaded, [pc.tailColour(true)] demonic tail, over which you have complete control, and you can easily use it to grip and hold objects.");
						} else {
							sb.append("a spaded, [npc.tailColour(true)] demonic tail, over which [npc.she] has complete control, and [npc.she] can easily use it to grip and hold objects.");
						}
						break;
					case DOG_MORPH:
						if (owner.isPlayer()) {
							sb.append("a furry, [pc.tailColour(true)] dog-like tail, which wags uncontrollably when you get excited.");
						} else {
							sb.append("a furry, [npc.tailColour(true)] dog-like tail, which wags uncontrollably when [npc.she] gets excited.");
						}
						break;
					case HARPY:
						if (owner.isPlayer()) {
							sb.append("a plume of beautiful, [pc.tailColour(true)] tail-feathers, which you can rapidly move up and down to help you keep your balance and to control your path when in flight.");
						} else {
							sb.append("a plume of beautiful, [npc.tailColour(true)] tail-feathers, which [npc.she] can rapidly move up and down to help [npc.herHim] keep [npc.her] balance and to control [npc.her] path when in flight.");
						}
						break;
					case HORSE_MORPH:
						if (owner.isPlayer()) {
							sb.append("a long, [pc.tailColour(true)] horse-like tail, which you can swipe from side to side, but other than that, you don't have much control over it.");
						} else {
							sb.append("a long, [npc.tailColour(true)] horse-like tail, which [npc.she] can swipe from side to side, but other than that, [npc.she] doesn't have much control over it.");
						}
						break;
					case COW_MORPH:
						if (owner.isPlayer()) {
							sb.append("a long, [pc.tailColour(true)] cow-like tail, which you can swipe from side to side, but other than that, you don't have much control over it.");
						} else {
							sb.append("a long, [npc.tailColour(true)] cow-like tail, which [npc.she] can swipe from side to side, but other than that, [npc.she] doesn't have much control over it.");
						}
						break;
					case LYCAN:
						if (owner.isPlayer()) {
							sb.append("a furry, [pc.tailColour(true)] wolf-like tail.");
						} else {
							sb.append("a furry, [npc.tailColour(true)] wolf-like tail.");
						}
						break;
					case SQUIRREL_MORPH:
						if (owner.isPlayer()) {
							sb.append("a fluffy, [pc.tailColour(true)] squirrel-like tail, which you can control well enough to grant you significantly improved balance.");
						} else {
							sb.append("a fluffy, [npc.tailColour(true)] squirrel-like tail, which [npc.she] can control well enough to grant [npc.herHim] significantly improved balance.");
						}
						break;
					case NONE:
						break;
				}
			} else {
				sb.append(Util.intToString(owner.getTailCount())+" ");
				switch(owner.getTailType()){
					case CAT_MORPH:
						if (owner.isPlayer()) {
							sb.append("furry, [pc.tailColour(true)] cat-like tails, which you can control well enough to grant you significantly improved balance.");
						} else {
							sb.append("furry, [npc.tailColour(true)] cat-like tails, which [npc.she] can control well enough to grant [npc.herHim] significantly improved balance.");
						}
						break;
					case DEMON_COMMON:
						if (owner.isPlayer()) {
							sb.append("spaded, [pc.tailColour(true)] demonic tails, over which you have complete control, and you can easily use them to grip and hold objects.");
						} else {
							sb.append("spaded, [npc.tailColour(true)] demonic tails, over which [npc.she] has complete control, and [npc.she] can easily use them to grip and hold objects.");
						}
						break;
					case DOG_MORPH:
						if (owner.isPlayer()) {
							sb.append("furry, [pc.tailColour(true)] dog-like tails, which wag uncontrollably when you get excited.");
						} else {
							sb.append("furry, [npc.tailColour(true)] dog-like tails, which wag uncontrollably when [npc.she] gets excited.");
						}
						break;
					case HARPY:
						if (owner.isPlayer()) {
							sb.append("plumes of beautiful, [pc.tailColour(true)] tail-feathers, which you can rapidly move up and down to help you keep your balance and to control your path when in flight.");
						} else {
							sb.append("plumes of beautiful, [npc.tailColour(true)] tail-feathers, which [npc.she] can rapidly move up and down to help [npc.herHim] keep [npc.her] balance and to control [npc.her] path when in flight.");
						}
						break;
					case HORSE_MORPH:
						if (owner.isPlayer()) {
							sb.append("long, [pc.tailColour(true)] horse-like tails, which you can swipe from side to side, but other than that, you don't have much control over them.");
						} else {
							sb.append("long, [npc.tailColour(true)] horse-like tails, which [npc.she] can swipe from side to side, but other than that, [npc.she] doesn't have much control over them.");
						}
						break;
					case COW_MORPH:
						if (owner.isPlayer()) {
							sb.append("long, [pc.tailColour(true)] cow-like tails, which you can swipe from side to side, but other than that, you don't have much control over them.");
						} else {
							sb.append("long, [npc.tailColour(true)] cow-like tails, which [npc.she] can swipe from side to side, but other than that, [npc.she] doesn't have much control over them.");
						}
						break;
					case LYCAN:
						if (owner.isPlayer()) {
							sb.append("furry, [pc.tailColour(true)] wolf-like tails.");
						} else {
							sb.append("furry, [npc.tailColour(true)] wolf-like tails.");
						}
						break;
					case SQUIRREL_MORPH:
						if (owner.isPlayer()) {
							sb.append("fluffy, [pc.tailColour(true)] squirrel-like tails, which you can control well enough to grant you significantly improved balance.");
						} else {
							sb.append("fluffy, [npc.tailColour(true)] squirrel-like tails, which [npc.she] can control well enough to grant [npc.herHim] significantly improved balance.");
						}
						break;
					case NONE:
						break;
				}

			}
	
			sb.append("</p>");
		}

		sb.append("<p>");
		// Ass & hips:
		if (owner.isPlayer()) {
			sb.append("Your [pc.hips+] and [pc.assSize] [pc.ass] are covered in [pc.assFullDescription(true)].");
		} else {
			sb.append("[npc.Her] [npc.hips+] and [npc.assSize] [npc.ass] are covered in [npc.assFullDescription(true)].");
		}
		
		if(owner.getPlayerKnowsAreasMap().get(CoverableArea.ANUS)) {
			sb.append(" " + getAssDescription(owner));
			sb.append("</p>");
		} else {
			sb.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You haven't seen [npc.her] naked ass before, so you don't know what [npc.her] asshole looks like.</span>");
			sb.append("</p>");
		}
		//TODO pubic hair
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
				if (r == Race.HUMAN) {
					continue;
				}
				int currentParts = 0;
				int nonHumanParts = 0;

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

				if (nonHumanParts > leaderNonHumanParts) {
					this.race = r;
					if (currentParts == 8) {
						raceStage = RaceStage.PARTIAL_FULL;
					} else {
						raceStage = RaceStage.PARTIAL;
					}
					leaderNonHumanParts = nonHumanParts;
				}
			}

		}
	}

	public Race getRace() {
		return race;
	}

	public RaceStage getRaceStage() {
		return raceStage;
	}
	
	public Antenna getAntenna() {
		return antenna;
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

	public Penis getSecondPenis() {
		return secondPenis;
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
				if (isPlayer) {
					descriptionSB.append("You have a human, [pc.anusFullDescription(true)]");
				} else {
					descriptionSB.append("[npc.She] has a human, [npc.anusFullDescription(true)]");
				}
				break;
				
			case DEMON_COMMON:
				if (isPlayer) {
					descriptionSB.append("You have a demonic, [pc.anusFullDescription(true)]");
				} else {
					descriptionSB.append("[npc.She] has a demonic, [npc.anusFullDescription(true)]");
				}
				break;
				
			case DOG_MORPH:
				if (isPlayer) {
					descriptionSB.append("You have a canine, [pc.anusFullDescription(true)]");
				} else {
					descriptionSB.append("[npc.She] has a canine, [npc.anusFullDescription(true)]");
				}
				break;
				
			case WOLF_MORPH:
				if (isPlayer) {
					descriptionSB.append("You have a lupine, [pc.anusFullDescription(true)]");
				} else {
					descriptionSB.append("[npc.She] has a lupine, [npc.anusFullDescription(true)]");
				}
				break;
				
			case CAT_MORPH:
				if (isPlayer) {
					descriptionSB.append("You have a feline, [pc.anusFullDescription(true)]");
				} else {
					descriptionSB.append("[npc.She] has a feline, [npc.anusFullDescription(true)]");
				}
				break;
				
			case SQUIRREL_MORPH:
				if (isPlayer) {
					descriptionSB.append("You have a rodent, [pc.anusFullDescription(true)]");
				} else {
					descriptionSB.append("[npc.She] has a rodent, [npc.anusFullDescription(true)]");
				}
				break;
				
			case HORSE_MORPH:
				if (isPlayer) {
					descriptionSB.append("You have an equine, [pc.anusFullDescription(true)]");
				} else {
					descriptionSB.append("[npc.She] has an equine, [npc.anusFullDescription(true)]");
				}
				break;
				
			case COW_MORPH:
				if (isPlayer) {
					descriptionSB.append("You have a bovine, [pc.anusFullDescription(true)]");
				} else {
					descriptionSB.append("[npc.She] has a bovine, [npc.anusFullDescription(true)]");
				}
				break;
				
			case HARPY:
				if (isPlayer) {
					descriptionSB.append("You have an avian, [pc.anusFullDescription(true)]");
				} else {
					descriptionSB.append("[npc.She] has an avian, [npc.anusFullDescription(true)]");
				}
				break;
				
			case SLIME:
				if (isPlayer) {
					descriptionSB.append("You have a slime's, [pc.anusFullDescription(true)]");
				} else {
					descriptionSB.append("[npc.She] has a slime's, [npc.anusFullDescription(true)]");
				}
				break;
			default:
				break;
		}
		
		// Colour:
		if(ass.getAnus().isBleached()) {
			if (isPlayer) {
				descriptionSB.append(", which has been bleached so that the rim is no darker than the [pc.assSkin] around it.");
			} else {
				descriptionSB.append(", which has been bleached so that the rim is no darker than the [npc.assSkin] around it.");
			}
			
		} else {
			if (isPlayer) {
				descriptionSB.append(", the rim being slightly darker than the [pc.assSkin] around it.");
			} else {
				descriptionSB.append(", the rim being slightly darker than the [npc.assSkin] around it.");
			}
		}

		descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It is "+Capacity.getCapacityFromValue(ass.getAnus().getOrificeAnus().getStretchedCapacity()).getDescriptor()+", and can comfortably take "
				+ Capacity.getCapacityFromValue(ass.getAnus().getOrificeAnus().getStretchedCapacity()).getMaximumSizeComfortableWithLube().getDescriptor() + " cocks with enough lube.</span>");
		
		if (isPlayer) {
			if (ass.getAnus().getOrificeAnus().isVirgin()){
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>You have retained your anal virginity.</span>");
			}else{
				for(PenetrationType pt : PenetrationType.values()) {
					if(Main.game.getPlayer().getVirginityLoss(new SexType(pt, OrificeType.ANUS_PLAYER))!=null) {
						descriptionSB.append(" <span style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>You lost your anal virginity to "
								+ Main.game.getPlayer().getVirginityLoss(new SexType(pt, OrificeType.ANUS_PLAYER)) + ".</span>");
					}
				}
			}
		}
		// Ass wetness:
		switch (ass.getAnus().getOrificeAnus().getWetness(owner)) {
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
		// Ass elasticity & plasticity:
		switch (ass.getAnus().getOrificeAnus().getElasticity()) {
			case ZERO_UNYIELDING:
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It is extremely unyielding,");
				break;
			case ONE_RIGID:
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It takes a huge amount of effort to stretch it out,");
				break;
			case TWO_FIRM:
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It does not stretch very easily,");
				break;
			case THREE_FLEXIBLE:
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It reluctantly stretches out when used as a sexual orifice,");
				break;
			case FOUR_LIMBER:
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It is somewhat resistant to being stretched out,");
				break;
			case FIVE_STRETCHY:
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It stretches out fairly easily,");
				break;
			case SIX_SUPPLE:
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It stretches out very easily,");
				break;
			case SEVEN_ELASTIC:
				descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It is extremely elastic,");
				break;
			default:
				break;
		}
		switch (ass.getAnus().getOrificeAnus().getPlasticity()) {
			case ZERO_RUBBERY:
				descriptionSB.append(" and will instantly return to its original size.</span>");
				break;
			case ONE_SPRINGY:
				descriptionSB.append(" and returns to its original size within a matter of hours.</span>");
				break;
			case TWO_TENSILE:
				descriptionSB.append(" and returns to its original size within a day or so.</span>");
				break;
			case THREE_RESILIENT:
				descriptionSB.append(" and will return to its original size after a couple of days.</span>");
				break;
			case FOUR_ACCOMMODATING:
				descriptionSB.append(" and takes a while to return to its original size.</span>");
				break;
			case FIVE_YIELDING:
				descriptionSB.append(" and struggles to return to its original size.</span>");
				break;
			case SIX_MALLEABLE:
				descriptionSB.append(" and loses a good portion of its original tightness.</span>");
				break;
			case SEVEN_MOULDABLE:
				descriptionSB.append(" and once stretched out, it stays that way.</span>");
				break;
			default:
				break;
		}
		
		if(Main.game.isBodyHairEnabled()) {
			if(owner.isPlayer()) {
				switch(ass.getAnus().getAssHair()) {
					case NONE:
						descriptionSB.append(" There is no trace of any "+owner.getAssHairType().getName(owner, true)+" around your asshole.");
						break;
					case MANICURED:
						descriptionSB.append(" You have a well-manicured patch of "+owner.getAssHairType().getFullDescription(owner, true)+" around your asshole.");
						break;
					case TRIMMED:
						descriptionSB.append(" You have a trimmed patch of "+owner.getAssHairType().getFullDescription(owner, true)+" around your asshole.");
						break;
					case BUSHY:
						descriptionSB.append(" You have a thick mass of "+owner.getAssHairType().getFullDescription(owner, true)+" around your asshole.");
						break;
				}
				
			} else {
				switch(ass.getAnus().getAssHair()) {
					case NONE:
						descriptionSB.append(" There is no trace of any "+owner.getAssHairType().getName(owner, true)+" around [npc.her] asshole.");
						break;
					case MANICURED:
						descriptionSB.append(" [npc.She] has a well-manicured patch of "+owner.getAssHairType().getFullDescription(owner, true)+" around [npc.her] asshole.");
						break;
					case TRIMMED:
						descriptionSB.append(" [npc.She] has a trimmed patch of "+owner.getAssHairType().getFullDescription(owner, true)+" around [npc.her] asshole.");
						break;
					case BUSHY:
						descriptionSB.append(" [npc.She] has a thick mass of "+owner.getAssHairType().getFullDescription(owner, true)+" around [npc.her] asshole.");
						break;
				}
			}
		}
		
		for(OrificeModifier om : OrificeModifier.values()) {
			if(owner.hasAssOrificeModifier(om)) {
				if(owner.isPlayer()) {
					switch(om) {
						case MUSCLE_CONTROL:
							descriptionSB.append(" You have a series of internal muscles lining the inside of your [pc.asshole], allowing you to expertly squeeze and grip down on any intruding object.");
							break;
						case PUFFY:
							descriptionSB.append(" The rim of your [pc.asshole] has swollen up into a puffy, doughnut-like ring.");
							break;
						case RIBBED:
							descriptionSB.append(" The inside of your [pc.asshole] is lined with sensitive, fleshy ribs, which grant you extra pleasure when stimulated.");
							break;
						case TENTACLED:
							descriptionSB.append(" Your [pc.asshole] is filled with tiny little tentacles, which wriggle and squirm with a mind of their own.");
							break;
					}
				} else {
					switch(om) {
						case MUSCLE_CONTROL:
							descriptionSB.append(" [npc.She] has a series of internal muscles lining the inside of [npc.her] [npc.asshole], allowing [npc.herHim] to expertly squeeze and grip down on any intruding object.");
							break;
						case PUFFY:
							descriptionSB.append(" The rim of [npc.her] [npc.asshole] has swollen up into a puffy, doughnut-like ring.");
							break;
						case RIBBED:
							descriptionSB.append(" The inside of [npc.her] [npc.asshole] is lined with sensitive, fleshy ribs, which grant [npc.herHim] extra pleasure when stimulated.");
							break;
						case TENTACLED:
							descriptionSB.append(" [npc.Her] [npc.asshole] is filled with tiny little tentacles, which wriggle and squirm with a mind of their own.");
							break;
					}
				}
			}
		}
		
		return UtilText.parse(owner, descriptionSB.toString());
	}

	public String getBreastDescription(GameCharacter owner) {
		descriptionSB = new StringBuilder();
		
		boolean isPlayer = owner.isPlayer();
		boolean playerKnowledgeOfBreasts = owner.getPlayerKnowsAreasMap().get(CoverableArea.NIPPLES);
		
		if(!isPlayer && !playerKnowledgeOfBreasts) {
			descriptionSB.append("You've never seen [npc.her] naked chest, so you don't know what [npc.her] nipples look like.");
			
		} else if (isPlayer) {
			descriptionSB.append("On each [pc.breast] you have "+Util.intToString(owner.getNippleCountPerBreast())+" [pc.nippleSize], ");
			
			switch(owner.getNippleShape()) {
				case NORMAL:
					descriptionSB.append(" [pc.nipplePrimaryColour(true)] [pc.nipple],");
					break;
				case LIPS:
					descriptionSB.append(" [pc.nipplePrimaryColour(true)]-lipped [pc.nipple],");
					break;
				case VAGINA:
					descriptionSB.append(" [pc.nipplePrimaryColour(true)]-lipped [pc.nipple],");
					break;
			}
			
			switch(owner.getAreolaeShape()) {
				case NORMAL:
					descriptionSB.append(" with [pc.areolaeSize], circular areolae.");
					break;
				case HEART:
					descriptionSB.append(" with [pc.areolaeSize], heart-shaped areolae.");
					break;
				case STAR:
					descriptionSB.append(" with [pc.areolaeSize], star-shaped areolae.");
					break;
			}

			if(owner.isPiercedNipple()) {
				descriptionSB.append(" They have been pierced.");
			}
			
			if(owner.getNippleCapacity() != Capacity.ZERO_IMPENETRABLE) {
				if (breast.isFuckable()) {
					descriptionSB.append("</br>Your [pc.breasts] have internal, [pc.nippleSecondaryColour(true)] channels, allowing your [pc.breastCapacity] [pc.nipples] to be comfortably penetrated by "
							+ Capacity.getCapacityFromValue(breast.getNipples().getOrificeNipples().getStretchedCapacity()).getMaximumSizeComfortableWithLube().getDescriptor() + " objects with sufficient lubrication.");
					
				} else {
					descriptionSB.append("</br>Your [pc.breasts] have internal, [pc.nippleSecondaryColour(true)] channels, but you'd need at least D-cups before your [pc.breastCapacity] [pc.nipples] could be penetrated.");
				}
				
				// Nipple elasticity & plasticity:
				switch (breast.getNipples().getOrificeNipples().getElasticity()) {
					case ZERO_UNYIELDING:
						descriptionSB.append(" [style.colourSex(They are extremely unyielding,");
						break;
					case ONE_RIGID:
						descriptionSB.append(" [style.colourSex(They take a huge amount of effort to stretch out,");
						break;
					case TWO_FIRM:
						descriptionSB.append(" [style.colourSex(They do not stretch very easily,");
						break;
					case THREE_FLEXIBLE:
						descriptionSB.append(" [style.colourSex(They reluctantly stretch out when penetrated,");
						break;
					case FOUR_LIMBER:
						descriptionSB.append(" [style.colourSex(They are somewhat resistant to being stretched out,");
						break;
					case FIVE_STRETCHY:
						descriptionSB.append(" [style.colourSex(They stretch out fairly easily,");
						break;
					case SIX_SUPPLE:
						descriptionSB.append(" [style.colourSex(They stretch out very easily,");
						break;
					case SEVEN_ELASTIC:
						descriptionSB.append(" [style.colourSex(They are extremely elastic,");
						break;
					default:
						break;
				}
				switch (breast.getNipples().getOrificeNipples().getPlasticity()) {
					case ZERO_RUBBERY:
						descriptionSB.append(" and instantly return to their original size.)]");
						break;
					case ONE_SPRINGY:
						descriptionSB.append(" and return to their original size within a matter of hours.)]");
						break;
					case TWO_TENSILE:
						descriptionSB.append(" and return to their original size within a day or so.)]");
						break;
					case THREE_RESILIENT:
						descriptionSB.append(" and will return to their original size after a couple of days.)]");
						break;
					case FOUR_ACCOMMODATING:
						descriptionSB.append(" and take a while to return to their original size.)]");
						break;
					case FIVE_YIELDING:
						descriptionSB.append(" and struggle to return to their original size.)]");
						break;
					case SIX_MALLEABLE:
						descriptionSB.append(" and lose a good portion of their original tightness.)]");
						break;
					case SEVEN_MOULDABLE:
						descriptionSB.append(" and once stretched out, they stay that way.)]");
						break;
					default:
						break;
				}
				
				for(OrificeModifier om : OrificeModifier.values()) {
					if(owner.hasNippleOrificeModifier(om)) {
						switch(om) {
							case MUSCLE_CONTROL:
								descriptionSB.append(" You have a series of muscles lining the inside of your [pc.nipples], allowing you to expertly squeeze and grip down on any intruding object.");
								break;
							case PUFFY:
								descriptionSB.append(" Your [pc.nipples] have swollen up to be exceptionally plump and puffy.");
								break;
							case RIBBED:
								descriptionSB.append(" The insides of your [pc.nipples] are lined with sensitive, fleshy ribs, which grant you extra pleasure when stimulated.");
								break;
							case TENTACLED:
								descriptionSB.append(" Your [pc.nipples] are filled with tiny little tentacles, which wriggle and squirm with a mind of their own.");
								break;
						}
					}
				}
				
				if (!breast.getNipples().getOrificeNipples().isVirgin()) {
					for(PenetrationType pt : PenetrationType.values()) {
						if(owner.getVirginityLoss(new SexType(pt, OrificeType.NIPPLE_PLAYER))!=null) {
							descriptionSB.append(" [style.colourArcane(You lost your nipple virginity to "+ owner.getVirginityLoss(new SexType(pt, OrificeType.NIPPLE_PLAYER)) + ".)]");
						}
					}
				} else {
					descriptionSB.append(" [style.colourGood(You have retained your nipple virginity.)]");
				}
				
			} else {
				if(owner.hasNippleOrificeModifier(OrificeModifier.PUFFY)) {
					descriptionSB.append(" Your [pc.nipples] have swollen up to be exceptionally plump and puffy.");
				}
			}

			if (breast.getRawLactationValue() > 0) {
				descriptionSB.append("</br>You are currently producing "+ breast.getRawLactationValue() + "mL of [pc.milkPrimaryColour(true)] [pc.milk]");
				
				switch(breast.getMilk().getFlavour()) {
					case CHOCOLATE:
						descriptionSB.append(", which tastes of chocolate.");
						break;
					case CUM:
						descriptionSB.append(", which tastes exactly like cum.");
						break;
					case GIRL_CUM:
						descriptionSB.append(", which tastes of girl-cum.");
						break;
					case HONEY:
						descriptionSB.append(", which tastes of honey.");
						break;
					case MILK:
						descriptionSB.append(", which tastes like regular milk.");
						break;
					case MINT:
						descriptionSB.append(", which tastes of mint.");
						break;
					case PINEAPPLE:
						descriptionSB.append(", which tastes of pineapple.");
						break;
					case SLIME:
						descriptionSB.append(", which is mostly tasteless, but very sweet.");
						break;
					case STRAWBERRY:
						descriptionSB.append(", which tastes of strawberries.");
						break;
					case BEER:
						descriptionSB.append(", which tastes like beer.");
						break;
					case VANILLA:
						descriptionSB.append(", which tastes of vanilla.");
						break;
				}
				
				for(FluidModifier fm : FluidModifier.values()) {
					if(owner.hasMilkModifier(fm)) {
						switch(fm) {
							case ADDICTIVE:
								descriptionSB.append(" It is highly addictive, and anyone who drinks too much will quickly become dependent on it.");
								break;
							case BUBBLING:
								descriptionSB.append(" It fizzes and bubbles like a carbonated drink.");
								break;
							case HALLUCINOGENIC:
								descriptionSB.append(" Anyone who ingests it suffers hallucinations, often related to lactation and breast-feeding.");
								break;
							case MUSKY:
								descriptionSB.append(" It has a strong, musky smell.");
								break;
							case SLIMY:
								descriptionSB.append(" It has a slimy, oily texture.");
								break;
							case STICKY:
								descriptionSB.append(" It's quite sticky, and is difficult to fully wash off without soap.");
								break;
							case VISCOUS:
								descriptionSB.append(" It's quite viscous, and slowly drips in large globules, much like thick treacle.");
								break;
							case ALCOHOLIC:
								descriptionSB.append(" It has a high alcohol content, and will get those who consume it very drunk.");
								break;
						}
					}
				}
				
			} else {
				descriptionSB.append("</br>You are not producing any milk.");
			}
			
		} else {
			descriptionSB.append("On each [npc.breast] [npc.she] has "+Util.intToString(owner.getNippleCountPerBreast())+" [npc.nippleSize], ");
			
			switch(owner.getNippleShape()) {
				case NORMAL:
					descriptionSB.append(" [npc.nipplePrimaryColour(true)] nipples,");
					break;
				case LIPS:
					descriptionSB.append(" [npc.nipplePrimaryColour(true)]-lipped lipples,");
					break;
				case VAGINA:
					descriptionSB.append(" [npc.nipplePrimaryColour(true)]-lipped nipple-cunts,");
					break;
			}
			
			switch(owner.getAreolaeShape()) {
				case NORMAL:
					descriptionSB.append(" with [npc.areolaeSize], circular areolae.");
					break;
				case HEART:
					descriptionSB.append(" with [npc.areolaeSize], heart-shaped areolae.");
					break;
				case STAR:
					descriptionSB.append(" with [npc.areolaeSize], star-shaped areolae.");
					break;
			}
			
			if(owner.isPiercedNipple()) {
				descriptionSB.append(" They have been pierced.");
			}
			
			if(owner.getNippleCapacity() != Capacity.ZERO_IMPENETRABLE) {
				if (breast.isFuckable()) {
					descriptionSB.append("</br>[npc.Her] [npc.breasts] have internal, [npc.nippleSecondaryColour(true)] channels, allowing [npc.her] [npc.breastCapacity] [npc.nipples] to be comfortably penetrated by "
							+ Capacity.getCapacityFromValue(breast.getNipples().getOrificeNipples().getStretchedCapacity()).getMaximumSizeComfortableWithLube().getDescriptor() + " objects with sufficient lubrication.");
					
				} else {
					descriptionSB.append("</br>[npc.Her] [npc.breasts] have internal, [npc.nippleSecondaryColour(true)] channels, but [npc.she]'s need at least D-cups before [npc.her] [npc.breastCapacity] [npc.nipples] could be penetrated.");
				}
				
				// Nipple elasticity & plasticity:
				switch (breast.getNipples().getOrificeNipples().getElasticity()) {
					case ZERO_UNYIELDING:
						descriptionSB.append(" [style.colourSex(They are extremely unyielding,");
						break;
					case ONE_RIGID:
						descriptionSB.append(" [style.colourSex(They take a huge amount of effort to stretch out,");
						break;
					case TWO_FIRM:
						descriptionSB.append(" [style.colourSex(They do not stretch very easily,");
						break;
					case THREE_FLEXIBLE:
						descriptionSB.append(" [style.colourSex(They reluctantly stretch out when penetrated,");
						break;
					case FOUR_LIMBER:
						descriptionSB.append(" [style.colourSex(They are somewhat resistant to being stretched out,");
						break;
					case FIVE_STRETCHY:
						descriptionSB.append(" [style.colourSex(They stretch out fairly easily,");
						break;
					case SIX_SUPPLE:
						descriptionSB.append(" [style.colourSex(They stretch out very easily,");
						break;
					case SEVEN_ELASTIC:
						descriptionSB.append(" [style.colourSex(They are extremely elastic,");
						break;
					default:
						break;
				}
				switch (breast.getNipples().getOrificeNipples().getPlasticity()) {
					case ZERO_RUBBERY:
						descriptionSB.append(" and instantly return to their original size.)]");
						break;
					case ONE_SPRINGY:
						descriptionSB.append(" and return to their original size within a matter of hours.)]");
						break;
					case TWO_TENSILE:
						descriptionSB.append(" and return to their original size within a day or so.)]");
						break;
					case THREE_RESILIENT:
						descriptionSB.append(" and will return to their original size after a couple of days.)]");
						break;
					case FOUR_ACCOMMODATING:
						descriptionSB.append(" and take a while to return to their original size.)]");
						break;
					case FIVE_YIELDING:
						descriptionSB.append(" and struggle to return to their original size.)]");
						break;
					case SIX_MALLEABLE:
						descriptionSB.append(" and lose a good portion of their original tightness.)]");
						break;
					case SEVEN_MOULDABLE:
						descriptionSB.append(" and once stretched out, they stay that way.)]");
						break;
					default:
						break;
				}
				
				for(OrificeModifier om : OrificeModifier.values()) {
					if(owner.hasNippleOrificeModifier(om)) {
						switch(om) {
							case MUSCLE_CONTROL:
								descriptionSB.append(" [npc.She] has a series of muscles lining the insides of [npc.her] [npc.nipples], allowing [npc.herHim] to expertly squeeze and grip down on any intruding object.");
								break;
							case PUFFY:
								descriptionSB.append(" [npc.Her] [npc.nipples] have swollen up to be exceptionally plump and puffy.");
								break;
							case RIBBED:
								descriptionSB.append(" The insides of [npc.her] [npc.nipples] are lined with sensitive, fleshy ribs, which grant [npc.herHim] extra pleasure when stimulated.");
								break;
							case TENTACLED:
								descriptionSB.append(" [npc.Her] [npc.nipples] are filled with tiny little tentacles, which wriggle and squirm with a mind of their own.");
								break;
						}
					}
				}
				
				if (!breast.getNipples().getOrificeNipples().isVirgin()) {
					for(PenetrationType pt : PenetrationType.values()) {
						if(owner.getVirginityLoss(new SexType(pt, OrificeType.NIPPLE_PARTNER))!=null) {
							descriptionSB.append(" [style.colourArcane([npc.Name] lost [npc.her] nipple virginity to "+ owner.getVirginityLoss(new SexType(pt, OrificeType.NIPPLE_PARTNER)) + ".)]");
						}
					}
				} else {
					descriptionSB.append(" [style.colourGood([npc.Name] has retained [npc.her] nipple virginity.)]");
				}
				
				if (breast.getRawLactationValue() > 0) {
					descriptionSB.append("</br>[npc.She] is currently producing "+ breast.getRawLactationValue() + "mL of [npc.milkPrimaryColour(true)] [npc.milk]");
					
					switch(breast.getMilk().getFlavour()) {
						case CHOCOLATE:
							descriptionSB.append(", which tastes of chocolate.");
							break;
						case CUM:
							descriptionSB.append(", which tastes exactly like cum.");
							break;
						case GIRL_CUM:
							descriptionSB.append(", which tastes of girl-cum.");
							break;
						case HONEY:
							descriptionSB.append(", which tastes of honey.");
							break;
						case MILK:
							descriptionSB.append(", which tastes like regular milk.");
							break;
						case MINT:
							descriptionSB.append(", which tastes of mint.");
							break;
						case PINEAPPLE:
							descriptionSB.append(", which tastes of pineapple.");
							break;
						case SLIME:
							descriptionSB.append(", which is mostly tasteless, but very sweet.");
							break;
						case STRAWBERRY:
							descriptionSB.append(", which tastes of strawberries.");
							break;
						case BEER:
							descriptionSB.append(", which tastes like beer.");
							break;
						case VANILLA:
							descriptionSB.append(", which tastes of vanilla.");
							break;
					}
					
					for(FluidModifier fm : FluidModifier.values()) {
						if(owner.hasMilkModifier(fm)) {
							switch(fm) {
								case ADDICTIVE:
									descriptionSB.append(" It is highly addictive, and anyone who drinks too much will quickly become dependent on it.");
									break;
								case BUBBLING:
									descriptionSB.append(" It fizzes and bubbles like a carbonated drink.");
									break;
								case HALLUCINOGENIC:
									descriptionSB.append(" Anyone who ingests it suffers hallucinations, often related to lactation and breast-feeding.");
									break;
								case MUSKY:
									descriptionSB.append(" It has a strong, musky smell.");
									break;
								case SLIMY:
									descriptionSB.append(" It has a slimy, oily texture.");
									break;
								case STICKY:
									descriptionSB.append(" It's quite sticky, and is difficult to fully wash off without soap.");
									break;
								case VISCOUS:
									descriptionSB.append(" It's quite viscous, and slowly drips in large globules, much like thick treacle.");
									break;
								case ALCOHOLIC:
									descriptionSB.append(" It has a high alcohol content, and will get those who consume it very drunk.");
									break;
							}
						}
					}
					
				} else {
					descriptionSB.append("</br>[npc.She] is not producing any milk.");
				}
			} else {
				if(owner.hasNippleOrificeModifier(OrificeModifier.PUFFY)) {
					descriptionSB.append(" [npc.Her] [npc.nipples] have swollen up to be exceptionally plump and puffy.");
				}
			}
		}

		return UtilText.parse(owner, descriptionSB.toString());
	}

	public String getPenisDescription(GameCharacter owner) {
		boolean isPlayer = owner.isPlayer();
		
		descriptionSB = new StringBuilder();
		
		if (isPlayer) {
			descriptionSB.append("You have [pc.a_penisSize]");
		} else {
			descriptionSB.append("[npc.She] has [npc.a_penisSize]");
		}
		
		switch (penis.getType()) {
			case HUMAN:
				descriptionSB.append(" human cock");
				break;
			case DEMON_COMMON:
				descriptionSB.append(" demonic cock");
				break;
			case BOVINE:
				descriptionSB.append(" bovine");
				break;
			case CANINE:
				descriptionSB.append(" canine cock");
				break;
			case LUPINE:
				descriptionSB.append(" lupine cock");
				break;
			case FELINE:
				descriptionSB.append(" feline cock");
				break;
			case SQUIRREL:
				descriptionSB.append(" squirrel-like cock");
				break;
			case EQUINE:
				descriptionSB.append(" equine cock");
				break;
			case SLIME:
				descriptionSB.append(" slime cock");
				break;
			case AVIAN:
				descriptionSB.append(" avian cock");
				break;
			case ANGEL:
				descriptionSB.append(" angelic cock");
				break;
			case NONE:
				break;
		}
		
		if (isPlayer) {
			descriptionSB.append(", which is covered in [pc.cockFullDescription(true)].");
		} else {
			descriptionSB.append(", which is covered in [npc.cockFullDescription(true)].");
		}
		
		for(PenisModifier pm : PenisModifier.values()) {
			if(owner.hasPenisModifier(pm)) {
				switch(pm) {
					case RIBBED:
						descriptionSB.append(" It's lined with hard, fleshy ribs, which are sure to grant extra pleasure to any orifice that they penetrate.");
						break;
					case TENTACLED:
						descriptionSB.append(" A series of little tentacles coat its surface, which wriggle and squirm with a mind of their own.");
						break;
					case BARBED:
						descriptionSB.append(" Fleshy, backwards-facing barbs line its length.");
						break;
					case FLARED:
						descriptionSB.append(" The head is wide and flared.");
						break;
					case KNOTTED:
						descriptionSB.append(" A fat knot sits at the base.");
						break;
					case PREHENSILE:
						descriptionSB.append(" It is prehensile, and can be manipulated and moved much like a primate's tail.");
						break;
					case SHEATHED:
						descriptionSB.append(" When not in use, it retreats back into the sheath at its base.");
						break;
					case TAPERED:
						descriptionSB.append(" The shaft is tapered, and gets thinner nearer to the head.");
						break;
					case VEINY:
						descriptionSB.append(" Large veins press out from its surface.");
						break;
				}
			}
		}
		
		// Capacity:
		if (Capacity.getCapacityFromValue(penis.getOrificeUrethra().getStretchedCapacity()) != Capacity.ZERO_IMPENETRABLE) {
			if (isPlayer) {
				descriptionSB.append(" Your cock's urethra has been loosened enough that it presents a ready orifice for penetration,"
						+ " [style.colourSex(and can be comfortably penetrated by "+ Capacity.getCapacityFromValue(penis.getOrificeUrethra().getStretchedCapacity()).getMaximumSizeComfortableWithLube().getDescriptor() + " cocks with sufficient lubrication.)]");
			} else {
				descriptionSB.append(" [npc.Her] cock's urethra has been loosened enough that it presents a ready orifice for penetration,"
						+ " [style.colourSex(and can be comfortably penetrated by "+ Capacity.getCapacityFromValue(penis.getOrificeUrethra().getStretchedCapacity()).getMaximumSizeComfortableWithLube().getDescriptor() + " cocks with sufficient lubrication.)]");
			}
			switch (penis.getOrificeUrethra().getElasticity()) {
				case ZERO_UNYIELDING:
					descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It is extremely unyielding,");
					break;
				case ONE_RIGID:
					descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It takes a huge amount of effort to stretch it out,");
					break;
				case TWO_FIRM:
					descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It does not stretch very easily,");
					break;
				case THREE_FLEXIBLE:
					descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It reluctantly stretches out when used as a sexual orifice,");
					break;
				case FOUR_LIMBER:
					descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It is somewhat resistant to being stretched out,");
					break;
				case FIVE_STRETCHY:
					descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It stretches out fairly easily,");
					break;
				case SIX_SUPPLE:
					descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It stretches out very easily,");
					break;
				case SEVEN_ELASTIC:
					descriptionSB.append(" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>It is extremely elastic,");
					break;
				default:
					break;
			}
			switch (penis.getOrificeUrethra().getPlasticity()) {
				case ZERO_RUBBERY:
					descriptionSB.append(" and will instantly return to its original size.</span>");
					break;
				case ONE_SPRINGY:
					descriptionSB.append(" and returns to its original size within a matter of hours.</span>");
					break;
				case TWO_TENSILE:
					descriptionSB.append(" and returns to its original size within a day or so.</span>");
					break;
				case THREE_RESILIENT:
					descriptionSB.append(" and will return to its original size after a couple of days.</span>");
					break;
				case FOUR_ACCOMMODATING:
					descriptionSB.append(" and takes a while to return to its original size.</span>");
					break;
				case FIVE_YIELDING:
					descriptionSB.append(" and struggles to return to its original size.</span>");
					break;
				case SIX_MALLEABLE:
					descriptionSB.append(" and loses a good portion of its original tightness.</span>");
					break;
				case SEVEN_MOULDABLE:
					descriptionSB.append(" and once stretched out, it stays that way.</span>");
					break;
				default:
					break;
			}
			
			for(OrificeModifier om : OrificeModifier.values()) {
				if(owner.hasFaceOrificeModifier(om)) {
					if(owner.isPlayer()) {
						switch(om) {
							case PUFFY:
								descriptionSB.append(" Your urethra has transformed into having a swollen, puffy rim.");
								break;
							case MUSCLE_CONTROL:
								descriptionSB.append(" A series of muscles lining the inside of your urethra, allowing you to expertly squeeze and grip down on any intruding object.");
								break;
							case RIBBED:
								descriptionSB.append(" The inside of your urethra is lined with sensitive, fleshy ribs, which grant you extra pleasure when stimulated.");
								break;
							case TENTACLED:
								descriptionSB.append(" Your urethra is filled with tiny little tentacles, which wriggle and squirm with a mind of their own.");
								break;
						}
					} else {
						switch(om) {
							case PUFFY:
								descriptionSB.append(" [npc.Her] urethra has transformed into having a swollen, puffy rim.");
								break;
							case MUSCLE_CONTROL:
								descriptionSB.append(" [npc.She] has a series of muscles lining the inside of [npc.her] urethra, allowing [npc.herHim] to expertly squeeze and grip down on any intruding object.");
								break;
							case RIBBED:
								descriptionSB.append(" The inside of [npc.her] urethra is lined with sensitive, fleshy ribs, which grant [npc.herHim] extra pleasure when stimulated.");
								break;
							case TENTACLED:
								descriptionSB.append(" [npc.Her] urethra is filled with tiny little tentacles, which wriggle and squirm with a mind of their own.");
								break;
						}
					}
				}
			}
		}
		
		if (isPlayer && !owner.isUrethraVirgin()) {
			for(PenetrationType pt : PenetrationType.values()) {
				if(Main.game.getPlayer().getVirginityLoss(new SexType(pt, OrificeType.URETHRA_PLAYER))!=null) {
					descriptionSB.append(" <span style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>You lost your urethral virginity to "
							+ Main.game.getPlayer().getVirginityLoss(new SexType(pt, OrificeType.URETHRA_PLAYER)) + ".</span>");
				}
			}
		}
		
		descriptionSB.append("</br>");
		
		// Pubic Hair:
		if(Main.game.isPubicHairEnabled()) {
			switch(owner.getPubicHair()) {
				case NONE:
					if (isPlayer) {
						descriptionSB.append(" There is no trace of any "+owner.getPubicHairType().getName(owner, true)+" around the base of your cock.");
					} else {
						descriptionSB.append(" There is no trace of any "+owner.getPubicHairType().getName(owner, true)+" around the base of [npc.her] cock.");
					}
					break;
				case MANICURED:
					if (isPlayer) {
						descriptionSB.append(" You have a neat, manicured patch of "+owner.getPubicHairType().getFullDescription(owner, true)+" around the base of your cock.");
					} else {
						descriptionSB.append(" [npc.She] has a neat, manicured patch of "+owner.getPubicHairType().getFullDescription(owner, true)+" around the base of [npc.her] cock.");
					}
					break;
				case TRIMMED:
					if (isPlayer) {
						descriptionSB.append(" You have a trimmed patch of "+owner.getPubicHairType().getFullDescription(owner, true)+" around the base of your cock.");
					} else {
						descriptionSB.append(" [npc.She] has a trimmed patch of "+owner.getPubicHairType().getFullDescription(owner, true)+" around the base of [npc.her] cock.");
					}
					break;
				case BUSHY:
					if (isPlayer) {
						descriptionSB.append(" You have a wild, bushy mass of "+owner.getPubicHairType().getFullDescription(owner, true)+" around the base of your cock.");
					} else {
						descriptionSB.append(" [npc.She] has a wild, bushy mass of "+owner.getPubicHairType().getFullDescription(owner, true)+" around the base of [npc.her] cock.");
					}
					break;
			}
		}

		descriptionSB.append("</br>");
		
		// Testicle size and cum production:
		
		if(owner.isInternalTesticles()) {
			if (isPlayer) {
				descriptionSB.append(" Your [pc.ballsCount] [pc.balls+] have shifted to sit inside your body, leaving your [pc.cock] as the only visible part of your male reproductive organs.");
			} else {
				descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] have shifted to sit inside [npc.her] body, leaving [npc.her] [npc.cock] as the only visible part of [pc.her] male reproductive organs.");
			}
			
		} else {
			switch (penis.getTesticle().getTesticleSize()) {
				case ZERO_VESTIGIAL:
					if (isPlayer) {
						descriptionSB.append(" Your [pc.ballsCount] [pc.balls] are covered in [pc.ballFullDescription(true)], and are so small that they're only just visible as tiny little mounds nestling beneath your [pc.cock].");
					} else {
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls] are covered in [npc.ballFullDescription(true)], and are so small that they're only just visible as tiny little mounds nestling beneath [npc.her] [npc.cock].");
					}
					break;
				case ONE_TINY:
					if (isPlayer)
						descriptionSB.append(" Your [pc.ballsCount] [pc.balls] are covered in [pc.ballFullDescription(true)], and are small enough to comfortably nestle underneath your [pc.cock].");
					else
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls] are covered in [npc.ballFullDescription(true)], and are small enough to comfortably nestle underneath [npc.her] [npc.cock].");
					break;
				case TWO_AVERAGE:
					if (isPlayer)
						descriptionSB.append(" Your [pc.ballsCount] [pc.balls+] are covered in [pc.ballFullDescription(true)], and dangle down beneath your [pc.cock].");
					else
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] are covered in [npc.ballFullDescription(true)], and dangle down beneath [npc.her] [npc.cock].");
					break;
				case THREE_LARGE:
					if (isPlayer)
						descriptionSB.append(" Your [pc.ballsCount] [pc.balls+] are covered in [pc.ballFullDescription(true)], and hang down beneath your [pc.cock].");
					else
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] are covered in [npc.ballFullDescription(true)], and hang down beneath [npc.her] [npc.cock].");
					break;
				case FOUR_HUGE:
					if (isPlayer)
						descriptionSB.append(" Your [pc.ballsCount] [pc.balls+] are covered in [pc.ballFullDescription(true)], and hang down beneath your [pc.cock].");
					else
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] are covered in [npc.ballFullDescription(true)], and hang down beneath [npc.her] [npc.cock].");
					break;
				case FIVE_MASSIVE:
					if (isPlayer)
						descriptionSB.append(" Your [pc.ballsCount] [pc.balls+] are covered in [pc.ballFullDescription(true)], and hang down beneath your [pc.cock].");
					else
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] are covered in [npc.ballFullDescription(true)], and hang down beneath [npc.her] [npc.cock].");
					break;
				case SIX_GIGANTIC:
					if (isPlayer)
						descriptionSB.append(" Your [pc.ballsCount] [pc.balls+] are covered in [pc.ballFullDescription(true)], and hang down beneath your [pc.cock].");
					else
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] are covered in [npc.ballFullDescription(true)], and hang down beneath [npc.her] [npc.cock].");
					break;
				case SEVEN_ABSURD:
					if (isPlayer)
						descriptionSB.append(" Your [pc.ballsCount] [pc.balls+] are covered in [pc.ballFullDescription(true)], and hang down beneath your [pc.cock].");
					else
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] are covered in [npc.ballFullDescription(true)], and hang down beneath [npc.her] [npc.cock].");
					break;
			}
		}
		
		String cumName = "[npc.cum+]";
		if(owner.isPlayer()) {
			cumName = "[pc.cum+]";
		}
		switch (penis.getTesticle().getCumProduction()) {
			case ZERO_NONE:
				if (penis.getTesticle().getTesticleSize().getValue() > TesticleSize.TWO_AVERAGE.getValue()) {
					descriptionSB.append(" Despite their large size, they");
				} else {
					descriptionSB.append(" They");
				}
				descriptionSB.append(" don't produce any "+cumName+" at all.");
				break;
			case ONE_TRICKLE:
				if (penis.getTesticle().getTesticleSize().getValue() > TesticleSize.TWO_AVERAGE.getValue()) {
					descriptionSB.append(" Despite their large size, they");
				} else {
					descriptionSB.append(" They");
				}
				descriptionSB.append(" only produce a tiny trickle of "+cumName+" at each orgasm.");
				break;
			case TWO_SMALL_AMOUNT:
				if (penis.getTesticle().getTesticleSize().getValue() > TesticleSize.THREE_LARGE.getValue()) {
					descriptionSB.append(" Despite their large size, they");
				} else {
					descriptionSB.append(" They");
				}
				descriptionSB.append(" only produce a small amount of "+cumName+" at each orgasm.");
				break;
			case THREE_AVERAGE:
				if (penis.getTesticle().getTesticleSize().getValue() > TesticleSize.FOUR_HUGE.getValue()) {
					descriptionSB.append(" Despite their huge size, they only");
				} else {
					descriptionSB.append(" They");
				}
				descriptionSB.append(" produce an average amount of "+cumName+" at each orgasm.");
				break;
			case FOUR_LARGE:
				if (penis.getTesticle().getTesticleSize().getValue() < TesticleSize.TWO_AVERAGE.getValue()) {
					descriptionSB.append(" Despite their small size, they");
				} else {
					descriptionSB.append(" They");
				}
				descriptionSB.append(" produce a large amount of "+cumName+" at each orgasm.");
				break;
			case FIVE_HUGE:
				if (penis.getTesticle().getTesticleSize().getValue() < TesticleSize.TWO_AVERAGE.getValue()) {
					descriptionSB.append(" Despite their small size, they");
				} else {
					descriptionSB.append(" They");
				}
				descriptionSB.append(" produce a huge amount of "+cumName+" at each orgasm.");
				break;
			case SIX_EXTREME:
				if (penis.getTesticle().getTesticleSize().getValue() < TesticleSize.TWO_AVERAGE.getValue()) {
					descriptionSB.append(" Despite their small size, they");
				} else {
					descriptionSB.append(" They");
				}
				descriptionSB.append(" produce an extreme amount of "+cumName+" at each orgasm.");
				break;
			case SEVEN_MONSTROUS:
				if (penis.getTesticle().getTesticleSize().getValue() < TesticleSize.TWO_AVERAGE.getValue()) {
					descriptionSB.append(" Despite their small size, they");
				} else {
					descriptionSB.append(" They");
				}
				descriptionSB.append(" produce a monstrous amount of "+cumName+" at each orgasm.");
				break;
		}
		
		if(owner.isPlayer()) {
			descriptionSB.append(" Your [pc.cum]");
		} else {
			descriptionSB.append(" [npc.Her] [npc.cum]");
		}
		
		switch(penis.getTesticle().getCum().getFlavour()) {
			case CHOCOLATE:
				descriptionSB.append(" tastes of chocolate.");
				break;
			case CUM:
				descriptionSB.append(", much to nobody's surprise, tastes like cum.");
				break;
			case GIRL_CUM:
				descriptionSB.append(" tastes of girl-cum.");
				break;
			case HONEY:
				descriptionSB.append(" tastes of honey.");
				break;
			case MILK:
				descriptionSB.append(" tastes like regular milk.");
				break;
			case MINT:
				descriptionSB.append(" tastes of mint.");
				break;
			case PINEAPPLE:
				descriptionSB.append(" tastes of pineapple.");
				break;
			case SLIME:
				descriptionSB.append(" is mostly tasteless, but very sweet.");
				break;
			case STRAWBERRY:
				descriptionSB.append(" tastes of strawberries.");
				break;
			case BEER:
				descriptionSB.append(", which tastes like beer.");
				break;
			case VANILLA:
				descriptionSB.append(", which tastes of vanilla.");
				break;
		}
		
		for(FluidModifier fm : FluidModifier.values()) {
			if(owner.hasCumModifier(fm)) {
				switch(fm) {
					case ADDICTIVE:
						descriptionSB.append(" It is highly addictive, and anyone who drinks too much will quickly become dependent on it.");
						break;
					case BUBBLING:
						descriptionSB.append(" It fizzes and bubbles like a carbonated drink.");
						break;
					case HALLUCINOGENIC:
						descriptionSB.append(" Anyone who ingests it suffers hallucinations, often related to lactation and breast-feeding.");
						break;
					case MUSKY:
						descriptionSB.append(" It has a strong, musky smell.");
						break;
					case SLIMY:
						descriptionSB.append(" It has a slimy, oily texture.");
						break;
					case STICKY:
						descriptionSB.append(" It's quite sticky, and is difficult to fully wash off without soap.");
						break;
					case VISCOUS:
						descriptionSB.append(" It's quite viscous, and slowly drips in large globules, much like thick treacle.");
						break;
					case ALCOHOLIC:
						descriptionSB.append(" It has a high alcohol content, and will get those who consume it very drunk.");
						break;
				}
			}
		}
		
		return UtilText.parse(owner, descriptionSB.toString());
	}

	public String getVaginaDescription(GameCharacter owner) {
		boolean isPlayer = owner.isPlayer();
		
		descriptionSB = new StringBuilder();

		if (isPlayer) {
			if (penis.getType() != PenisType.NONE)
				descriptionSB.append("Beneath your [pc.penis], you have");
			else
				descriptionSB.append("Between your legs, you have");
		} else {
			if (penis.getType() != PenisType.NONE)
				descriptionSB.append("Beneath [npc.her] [npc.penis], [npc.she] has");
			else
				descriptionSB.append("Between [npc.her] legs, [npc.she] has");
		}
		
		switch (vagina.getType()) {
			case HUMAN:
				if (isPlayer) {
					descriptionSB.append((vagina.isPierced()?" a pierced,":" a")+" human pussy, with [pc.pussyPrimaryColour(true)] labia and [pc.pussySecondaryColour(true)] inner-walls.");
				} else {
					descriptionSB.append((vagina.isPierced()?" a pierced,":" a")+" human pussy, with [npc.pussyPrimaryColour(true)] labia and [npc.pussySecondaryColour(true)] inner-walls.");
				}
				break;
			case ANGEL:
				if (isPlayer) {
					descriptionSB.append((vagina.isPierced()?" a pierced,":" an")+" angelic pussy, with [pc.pussyPrimaryColour(true)] labia and [pc.pussySecondaryColour(true)] inner-walls.");
				} else {
					descriptionSB.append((vagina.isPierced()?" a pierced,":" an")+" angelic pussy, with [npc.pussyPrimaryColour(true)] labia and [npc.pussySecondaryColour(true)] inner-walls.");
				}
				break;
			case DEMON_COMMON:
				if (isPlayer) {
					descriptionSB.append((vagina.isPierced()?" a pierced,":" a")+" demonic pussy, with [pc.pussyPrimaryColour(true)] labia and [pc.pussySecondaryColour(true)] inner-walls.");
				} else {
					descriptionSB.append((vagina.isPierced()?" a pierced,":" a")+" demonic pussy, with [npc.pussyPrimaryColour(true)] labia and [npc.pussySecondaryColour(true)] inner-walls.");
				}
				break;
			case DOG_MORPH:
				if (isPlayer) {
					descriptionSB.append((vagina.isPierced()?" a pierced,":" a")+" canine pussy, with [pc.pussyPrimaryColour(true)] labia and [pc.pussySecondaryColour(true)] inner-walls.");
				} else {
					descriptionSB.append((vagina.isPierced()?" a pierced,":" a")+" canine pussy, with [npc.pussyPrimaryColour(true)] labia and [npc.pussySecondaryColour(true)] inner-walls.");
				}
				break;
			case WOLF_MORPH:
				if (isPlayer) {
					descriptionSB.append((vagina.isPierced()?" a pierced,":" a")+" lupine pussy, with [pc.pussyPrimaryColour(true)] labia and [pc.pussySecondaryColour(true)] inner-walls.");
				} else {
					descriptionSB.append((vagina.isPierced()?" a pierced,":" a")+" lupine pussy, with [npc.pussyPrimaryColour(true)] labia and [npc.pussySecondaryColour(true)] inner-walls.");
				}
				break;
			case CAT_MORPH:
				if (isPlayer) {
					descriptionSB.append((vagina.isPierced()?" a pierced,":" a")+" feline pussy, with [pc.pussyPrimaryColour(true)] labia and [pc.pussySecondaryColour(true)] inner-walls.");
				} else {
					descriptionSB.append((vagina.isPierced()?" a pierced,":" a")+" feline pussy, with [npc.pussyPrimaryColour(true)] labia and [npc.pussySecondaryColour(true)] inner-walls.");
				}
				break;
			case COW_MORPH:
				if (isPlayer) {
					descriptionSB.append((vagina.isPierced()?" a pierced,":" a")+" bovine pussy, with [pc.pussyPrimaryColour(true)] labia and [pc.pussySecondaryColour(true)] inner-walls.");
				} else {
					descriptionSB.append((vagina.isPierced()?" a pierced,":" a")+" bovine pussy, with [npc.pussyPrimaryColour(true)] labia and [npc.pussySecondaryColour(true)] inner-walls.");
				}
				break;
			case SQUIRREL_MORPH:
				if (isPlayer) {
					descriptionSB.append((vagina.isPierced()?" a pierced,":" a")+" squirrel-morph's pussy, with [pc.pussyPrimaryColour(true)] labia and [pc.pussySecondaryColour(true)] inner-walls.");
				} else {
					descriptionSB.append((vagina.isPierced()?" a pierced,":" a")+" squirrel-morph's pussy, with [npc.pussyPrimaryColour(true)] labia and [npc.pussySecondaryColour(true)] inner-walls.");
				}
				break;
			case HORSE_MORPH:
				if (isPlayer) {
					descriptionSB.append((vagina.isPierced()?" a pierced,":" an")+" equine pussy, with [pc.pussyPrimaryColour(true)] labia and [pc.pussySecondaryColour(true)] inner-walls.");
				} else {
					descriptionSB.append((vagina.isPierced()?" a pierced,":" an")+" equine pussy, with [npc.pussyPrimaryColour(true)] labia and [npc.pussySecondaryColour(true)] inner-walls.");
				}
				break;
			case HARPY:
				if (isPlayer) {
					descriptionSB.append((vagina.isPierced()?" a pierced,":" an")+" avian pussy, with [pc.pussyPrimaryColour(true)] labia and [pc.pussySecondaryColour(true)] inner-walls.");
				} else {
					descriptionSB.append((vagina.isPierced()?" a pierced,":" an")+" avian pussy, with [npc.pussyPrimaryColour(true)] labia and [npc.pussySecondaryColour(true)] inner-walls.");
				}
				break;
			case SLIME:
				if (isPlayer) {
					descriptionSB.append((vagina.isPierced()?" a pierced,":" a")+" slime pussy, with [pc.pussyPrimaryColour(true)] labia and [pc.pussySecondaryColour(true)] inner-walls.");
				} else {
					descriptionSB.append((vagina.isPierced()?" a pierced,":" a")+" slime pussy, with [npc.pussyPrimaryColour(true)] labia and [npc.pussySecondaryColour(true)] inner-walls.");
				}
				break;
			default:
				break;
		}
		
		// Pubic Hair:
		if(Main.game.isPubicHairEnabled()) {
			switch(owner.getPubicHair()) {
				case NONE:
					if (isPlayer) {
						descriptionSB.append(" There is no trace of any "+owner.getPubicHairType().getName(owner, true)+" around your pussy.");
					} else {
						descriptionSB.append(" There is no trace of any "+owner.getPubicHairType().getName(owner, true)+" around [npc.her] pussy.");
					}
					break;
				case MANICURED:
					if (isPlayer) {
						descriptionSB.append(" You have a neat, manicured patch of "+owner.getPubicHairType().getFullDescription(owner, true)+" around your pussy.");
					} else {
						descriptionSB.append(" [npc.She] has a neat, manicured patch of "+owner.getPubicHairType().getFullDescription(owner, true)+" around [pc.her] pussy.");
					}
					break;
				case TRIMMED:
					if (isPlayer) {
						descriptionSB.append(" You have a trimmed patch of "+owner.getPubicHairType().getFullDescription(owner, true)+" around your pussy.");
					} else {
						descriptionSB.append(" [npc.She] has a trimmed patch of "+owner.getPubicHairType().getFullDescription(owner, true)+" around [npc.her] pussy.");
					}
					break;
				case BUSHY:
					if (isPlayer) {
						descriptionSB.append(" You have a wild, bushy mass of "+owner.getPubicHairType().getFullDescription(owner, true)+" around your pussy.");
					} else {
						descriptionSB.append(" [npc.She] has a wild, bushy mass of "+owner.getPubicHairType().getFullDescription(owner, true)+" around [npc.her] pussy.");
					}
					break;
			}
		}
		
		if (isPlayer) {
			if(owner.getVaginaRawClitorisSizeValue()==0) {
				descriptionSB.append(" You have [pc.a_clitSize] clit, which measures less than one inch in length.");
			} else {
				descriptionSB.append(" You have [pc.a_clitSize] clit, which measures [pc.clitSizeInches] inch"+(owner.getVaginaRawClitorisSizeValue()==1?"":"es")+" long.");
			}
			
		} else {
			if(owner.getVaginaRawClitorisSizeValue()==0) {
				descriptionSB.append(" [npc.She] has [npc.a_clitSize] clit, which measures less than one inch in length.");
			} else {
				descriptionSB.append(" [npc.She] has [npc.a_clitSize] clit, which measures [npc.clitSizeInches] inch"+(owner.getVaginaRawClitorisSizeValue()==1?"":"es")+" long.");
			}
		}
		// Virgin/capacity:
		if (vagina.getOrificeVagina().isVirgin()) {
			if (isPlayer) {
				descriptionSB.append(" [style.colourSex(Within your " + Capacity.getCapacityFromValue(vagina.getOrificeVagina().getStretchedCapacity()).getDescriptor() + " [pc.pussy], your hymen is still intact, as it has never been penetrated before.)]"
						+ " [style.colourGood(You have retained your vaginal virginity.)]");
			} else {
				descriptionSB.append(" [style.colourSex(Within [npc.her] " + Capacity.getCapacityFromValue(vagina.getOrificeVagina().getStretchedCapacity()).getDescriptor() + " [npc.pussy], [npc.her] hymen is still intact, as it has never been penetrated before.)]"
						+ " [style.colourGood([npc.She] has retained [npc.her] vaginal virginity.)]");
			}
		} else {
			if (isPlayer) {
				descriptionSB.append(" [style.colourSex(Your pussy is " + Capacity.getCapacityFromValue(vagina.getOrificeVagina().getStretchedCapacity()).getDescriptor() + ", and can comfortably take "
						+ Capacity.getCapacityFromValue(vagina.getOrificeVagina().getStretchedCapacity()).getMaximumSizeComfortableWithLube().getDescriptor() + " cocks with sufficient lubrication.)]");
				
				for(PenetrationType pt : PenetrationType.values()) {
					if(Main.game.getPlayer().getVirginityLoss(new SexType(pt, OrificeType.VAGINA_PLAYER))!=null && pt.isTakesVirginity()) {
						descriptionSB.append(" <span style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>You lost your virginity to "
								+ Main.game.getPlayer().getVirginityLoss(new SexType(pt, OrificeType.VAGINA_PLAYER)) + ".</span>");
					}
				}
				
			} else{
				descriptionSB.append(" [style.colourSex([npc.Her] pussy is " + Capacity.getCapacityFromValue(vagina.getOrificeVagina().getStretchedCapacity()).getDescriptor() + ", and can comfortably take "
						+ Capacity.getCapacityFromValue(vagina.getOrificeVagina().getStretchedCapacity()).getMaximumSizeComfortableWithLube().getDescriptor() + " cocks with sufficient lubrication.)]");
			}
		}
		
		// Wetness:
		switch (vagina.getOrificeVagina().getWetness(owner)) {
			case ZERO_DRY:
				if (isPlayer) {
					descriptionSB.append(" [style.colourSex(It's completely dry and never gets wet, no matter how aroused you are.)]");
				} else {
					descriptionSB.append(" [style.colourSex(It's completely dry and never gets wet, no matter how aroused [npc.she] gets.)]");
				}
				break;
			case ONE_SLIGHTLY_MOIST:
				if (isPlayer) {
					descriptionSB.append(" [style.colourSex(It's slightly moist, and you need a huge amount of stimulation before you get wet.)]");
				} else {
					descriptionSB.append(" [style.colourSex(It's slightly moist, and [npc.she] needs a huge amount of stimulation before [npc.she] gets wet.)]");
				}
				break;
			case TWO_MOIST:
				if (isPlayer) {
					descriptionSB.append(" [style.colourSex(It's moist, but you still need a lot of stimulation before you get wet.)]");
				} else {
					descriptionSB.append(" [style.colourSex(It's moist, but [npc.she] still needs a lot of stimulation before [npc.she] gets wet.)]");
				}
				break;
			case THREE_WET:
				if (isPlayer) {
					descriptionSB.append(" [style.colourSex(You only need a small amount of foreplay before it gets wet enough for penetration.)]");
				} else {
					descriptionSB.append(" [style.colourSex([npc.She] only needs a small amount of foreplay before [npc.she] gets wet enough for penetration.)]");
				}
				break;
			case FOUR_SLIMY:
				if (isPlayer) {
					descriptionSB.append(" [style.colourSex(It's always slimy and wet, and you're ready for penetration at a moment's notice.)]");
				} else {
					descriptionSB.append(" [style.colourSex(It's always slimy and wet, and [npc.she]'s ready for penetration at a moment's notice.)]");
				}
				break;
			case FIVE_SLOPPY:
				if (isPlayer) {
					descriptionSB.append(" [style.colourSex(Its surface is always coated in slimy moisture, and within, your pussy is sloppy and practically begging to be fucked.)]");
				} else {
					descriptionSB.append(" [style.colourSex(Its surface is always coated in slimy moisture, and within, [npc.her] pussy is sloppy and practically begging to be fucked.)]");
				}
				break;
			case SIX_SOPPING_WET:
				if (isPlayer) {
					descriptionSB.append(" [style.colourSex(Your pussy is never anything less than sopping wet, and a trickle of your natural lubricant constantly dribbles from your slit.)]");
				} else {
					descriptionSB.append(" [style.colourSex([npc.Her] pussy is never anything less than sopping wet, and a trickle of [npc.her] natural lubricant constantly dribbles from [npc.her] slit.)]");
				}
				break;
			case SEVEN_DROOLING:
				if (isPlayer) {
					descriptionSB.append(" [style.colourSex(Your pussy is so wet that it audibly squelches with every step you take, and a constant stream of juices flow from your inviting cunt.)]");
				} else {
					descriptionSB.append(" [style.colourSex([npc.Her] pussy is so wet that it audibly squelches with every step [npc.she] takes, and a constant stream of juices flow from [npc.her] inviting cunt.)]");
				}
				break;
			default:
				break;
		}
		
		// Elasticity & plasticity:
		switch (vagina.getOrificeVagina().getElasticity()) {
			case ZERO_UNYIELDING:
				descriptionSB.append(" [style.colourSex(It is extremely unyielding,");
				break;
			case ONE_RIGID:
				descriptionSB.append(" [style.colourSex(It takes a huge amount of effort to stretch it out,");
				break;
			case TWO_FIRM:
				descriptionSB.append(" [style.colourSex(It does not stretch very easily,");
				break;
			case THREE_FLEXIBLE:
				descriptionSB.append(" [style.colourSex(It reluctantly stretches out when used as a sexual orifice,");
				break;
			case FOUR_LIMBER:
				descriptionSB.append(" [style.colourSex(It is somewhat resistant to being stretched out,");
				break;
			case FIVE_STRETCHY:
				descriptionSB.append(" [style.colourSex(It stretches out fairly easily,");
				break;
			case SIX_SUPPLE:
				descriptionSB.append(" [style.colourSex(It stretches out very easily,");
				break;
			case SEVEN_ELASTIC:
				descriptionSB.append(" [style.colourSex(It is extremely elastic,");
				break;
			default:
				break;
		}
		switch (vagina.getOrificeVagina().getPlasticity()) {
			case ZERO_RUBBERY:
				descriptionSB.append(" and will instantly return to its original size.)]");
				break;
			case ONE_SPRINGY:
				descriptionSB.append(" and returns to its original size within a matter of hours.)]");
				break;
			case TWO_TENSILE:
				descriptionSB.append(" and returns to its original size within a day or so.)]");
				break;
			case THREE_RESILIENT:
				descriptionSB.append(" and will return to its original size after a couple of days.)]");
				break;
			case FOUR_ACCOMMODATING:
				descriptionSB.append(" and takes a while to return to its original size.)]");
				break;
			case FIVE_YIELDING:
				descriptionSB.append(" and struggles to return to its original size.)]");
				break;
			case SIX_MALLEABLE:
				descriptionSB.append(" and loses a good portion of its original tightness.)]");
				break;
			case SEVEN_MOULDABLE:
				descriptionSB.append(" and once stretched out, it stays that way.)]");
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
		
		if(owner.getTotalTimesHadSex() >=1) {
			descriptionSB = new StringBuilder();
			
			// Amount of sex:
			
			descriptionSB.append(
					UtilText.parse(owner,
					"<p>"
						+ "<span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>"
							+ "You have had sex with [npc.name] "+Util.intToString(owner.getTotalTimesHadSex())+" "+(owner.getTotalTimesHadSex()==1?"time.":"times.")
						+"</span>"));
			
			if(owner.getSexConsensualCount()>=1) {
				if(owner.getSexConsensualCount() == owner.getTotalTimesHadSex()) {
					if(owner.getTotalTimesHadSex()==1) {
						descriptionSB.append(UtilText.parse(owner," The one time you had sex with [npc.herHim], it was consensual."));
					} else {
						descriptionSB.append(UtilText.parse(owner," All "+Util.intToString(owner.getTotalTimesHadSex())+" times were consensual."));
					}
					
				} else {
					if(owner.getTotalTimesHadSex()==1) {
						descriptionSB.append(UtilText.parse(owner," The one time you had sex with [npc.herHim], it was consensual."));
					} else {
						descriptionSB.append(UtilText.parse(owner," "+Util.capitaliseSentence(Util.intToString(owner.getSexConsensualCount()))+" of these times were consensual."));
					}
				}
			}
			if(owner.getSexAsSubCount()>=1) {
				if(owner.getSexAsSubCount() == owner.getTotalTimesHadSex()) {
					if(owner.getTotalTimesHadSex()==1) {
						descriptionSB.append(UtilText.parse(owner," The one time you had sex with [npc.herHim], it was non-consensual, with you as the dominant partner."));
					} else {
						descriptionSB.append(UtilText.parse(owner," All "+Util.intToString(owner.getTotalTimesHadSex())+" times were non-consensual, with you as the dominant partner."));
					}
					
				} else {
					if(owner.getTotalTimesHadSex()==1) {
						descriptionSB.append(UtilText.parse(owner," The one time you had sex with [npc.herHim], it was non-consensual, with you as the dominant partner."));
					} else {
						descriptionSB.append(UtilText.parse(owner," "+Util.capitaliseSentence(Util.intToString(owner.getSexAsSubCount()))+" of these times were non-consensual, with you as the dominant partner."));
					}
				}
			}
			if(owner.getSexAsDomCount()>=1) {
				if(owner.getSexAsDomCount() == owner.getTotalTimesHadSex()) {
					if(owner.getTotalTimesHadSex()==1) {
						descriptionSB.append(UtilText.parse(owner," The one time you had sex with [npc.herHim], it was non-consensual, with you as the submissive partner."));
					} else {
						descriptionSB.append(UtilText.parse(owner," All "+Util.intToString(owner.getTotalTimesHadSex())+" times were non-consensual, with you as the submissive partner."));
					}
					
				} else {
					if(owner.getTotalTimesHadSex()==1) {
						descriptionSB.append(UtilText.parse(owner," The one time you had sex with [npc.herHim], it was non-consensual, with you as the submissive partner."));
					} else {
						descriptionSB.append(UtilText.parse(owner," "+Util.capitaliseSentence(Util.intToString(owner.getSexAsDomCount()))+" of these times were non-consensual, with you as the submissive partner."));
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

	public boolean isFeminine() {
		return getFemininity() >= Femininity.ANDROGYNOUS.getMinimumFemininity();
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
	
	public BodyHair getPubicHair() {
		return pubicHair;
	}
	
	public void setPubicHair(BodyHair pubicHair) {
		this.pubicHair = pubicHair;
	}
	
	public int getBodySize() {
		return bodySize;
	}

	/**
	 * @param bodySize Value to set femininity to.
	 * @return True if bodySize was changed.
	 */
	public boolean setBodySize(int bodySize) {
		if (this.bodySize == bodySize) {
			return false;
		}
		
		if (bodySize <= 0) {
			if (this.bodySize == 0)
				return false;
			this.bodySize = 0;
			return true;
		}
		if (bodySize >= 100) {
			if (this.bodySize == 100)
				return false;
			this.bodySize = 100;
			return true;
		}
		
		this.bodySize = bodySize;
		return true;
	}
	
	public int getMuscle() {
		return muscle;
	}

	/**
	 * @param muscle Value to set muscle to.
	 * @return True if muscle was changed.
	 */
	public boolean setMuscle(int muscle) {
		if (this.muscle == muscle) {
			return false;
		}
		
		if (muscle <= 0) {
			if (this.muscle == 0)
				return false;
			this.muscle = 0;
			return true;
		}
		if (muscle >= 100) {
			if (this.muscle == 100)
				return false;
			this.muscle = 100;
			return true;
		}
		
		this.muscle = muscle;
		return true;
	}
	
	public BodyShape getBodyShape() {
		return BodyShape.valueOf(Muscle.valueOf(getMuscle()), BodySize.valueOf(getBodySize()));
	}
	
	public BodyMaterial getBodyMaterial() {
		return bodyMaterial;
	}
	
	public boolean setBodyMaterial(BodyMaterial bodyMaterial) {
		if(this.bodyMaterial == bodyMaterial) {
			return false;
		}
		
		this.bodyMaterial = bodyMaterial;
		
		return true;
	}

	public GenitalArrangement getGenitalArrangement() {
		return genitalArrangement;
	}

	public void setGenitalArrangement(GenitalArrangement genitalArrangement) {
		this.genitalArrangement = genitalArrangement;
	}


	public boolean isPiercedStomach() {
		return piercedStomach;
	}

	public void setPiercedStomach(boolean piercedStomach) {
		this.piercedStomach = piercedStomach;
	}

	public Map<BodyCoveringType, Covering> getCoverings() {
		return coverings;
	}

	public Set<BodyCoveringType> getBodyCoveringTypesDiscovered() {
		return coveringsDiscovered;
	}
	
	public void updateBodyHairColour() {
		for(Race r : Race.values()) {
			switch(r) {
				case ANGEL:
					coverings.put(BodyCoveringType.BODY_HAIR_ANGEL, new Covering(BodyCoveringType.BODY_HAIR_ANGEL, coverings.get(BodyCoveringType.HAIR_ANGEL).getPrimaryColour()));
					break;
				case CAT_MORPH:
					coverings.put(BodyCoveringType.BODY_HAIR_FELINE_FUR, new Covering(BodyCoveringType.BODY_HAIR_FELINE_FUR, coverings.get(BodyCoveringType.HAIR_FELINE_FUR).getPrimaryColour()));
					break;
				case DEMON:
					coverings.put(BodyCoveringType.BODY_HAIR_DEMON, new Covering(BodyCoveringType.BODY_HAIR_DEMON, coverings.get(BodyCoveringType.HAIR_DEMON).getPrimaryColour()));
					break;
				case DOG_MORPH:
					coverings.put(BodyCoveringType.BODY_HAIR_CANINE_FUR, new Covering(BodyCoveringType.BODY_HAIR_CANINE_FUR, coverings.get(BodyCoveringType.HAIR_CANINE_FUR).getPrimaryColour()));
					break;
				case HARPY:
					coverings.put(BodyCoveringType.BODY_HAIR_HARPY, new Covering(BodyCoveringType.BODY_HAIR_HARPY, coverings.get(BodyCoveringType.HAIR_HARPY).getPrimaryColour()));
					break;
				case HORSE_MORPH:
					coverings.put(BodyCoveringType.BODY_HAIR_HORSE_HAIR, new Covering(BodyCoveringType.BODY_HAIR_HORSE_HAIR, coverings.get(BodyCoveringType.HAIR_HORSE_HAIR).getPrimaryColour()));
					break;
				case COW_MORPH:
					coverings.put(BodyCoveringType.BODY_HAIR_BOVINE_FUR, new Covering(BodyCoveringType.BODY_HAIR_BOVINE_FUR, coverings.get(BodyCoveringType.HAIR_BOVINE_FUR).getPrimaryColour()));
					break;
				case HUMAN:
					coverings.put(BodyCoveringType.BODY_HAIR_HUMAN, new Covering(BodyCoveringType.BODY_HAIR_HUMAN, coverings.get(BodyCoveringType.HAIR_HUMAN).getPrimaryColour()));
					break;
				case SLIME:
					coverings.put(BodyCoveringType.BODY_HAIR_SLIME, new Covering(BodyCoveringType.BODY_HAIR_SLIME, coverings.get(BodyCoveringType.HAIR_SLIME).getPrimaryColour()));
					break;
				case SQUIRREL_MORPH:
					coverings.put(BodyCoveringType.BODY_HAIR_SQUIRREL_FUR, new Covering(BodyCoveringType.BODY_HAIR_SQUIRREL_FUR, coverings.get(BodyCoveringType.HAIR_SQUIRREL_FUR).getPrimaryColour()));
					break;
				case WOLF_MORPH:
					coverings.put(BodyCoveringType.BODY_HAIR_LYCAN_FUR, new Covering(BodyCoveringType.BODY_HAIR_LYCAN_FUR, coverings.get(BodyCoveringType.HAIR_LYCAN_FUR).getPrimaryColour()));
					break;
			}
		}
	}
	
	public void updateAllSkinCoverings() {
		
		// Make all orifice colours the same as their surroundings:
		switch(ass.getType().getRace()) {
			case ANGEL:
				coverings.put(BodyCoveringType.ANUS, new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, Colour.ORIFICE_INTERIOR, false));
				break;
			case DEMON:
				coverings.put(BodyCoveringType.ANUS, new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, Colour.ORIFICE_INTERIOR, false));
				break;
			case SLIME:
				coverings.put(BodyCoveringType.ANUS, new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, coverings.get(BodyCoveringType.SLIME).getPrimaryColour(),
						false, coverings.get(BodyCoveringType.SLIME).getPrimaryColour(), false));
				break;
			default:
				coverings.put(BodyCoveringType.ANUS, new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, Colour.ORIFICE_INTERIOR, false));
				break;
		}
		
		switch(breast.getType().getRace()) {
			case ANGEL:
				coverings.put(BodyCoveringType.NIPPLES, new Covering(BodyCoveringType.NIPPLES, CoveringPattern.ORIFICE_NIPPLE, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, Colour.ORIFICE_INTERIOR, false));
				break;
			case DEMON:
				coverings.put(BodyCoveringType.NIPPLES, new Covering(BodyCoveringType.NIPPLES, CoveringPattern.ORIFICE_NIPPLE, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, Colour.ORIFICE_INTERIOR, false));
				break;
			case SLIME:
				coverings.put(BodyCoveringType.NIPPLES_SLIME, new Covering(BodyCoveringType.NIPPLES_SLIME, CoveringPattern.ORIFICE_NIPPLE, coverings.get(BodyCoveringType.SLIME).getPrimaryColour(),
						false, coverings.get(BodyCoveringType.SLIME).getPrimaryColour(), false));
				break;
			default:
				coverings.put(BodyCoveringType.NIPPLES, new Covering(BodyCoveringType.NIPPLES, CoveringPattern.ORIFICE_NIPPLE, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, Colour.ORIFICE_INTERIOR, false));
				break;
		}
		
		switch(face.getType().getRace()) {
			case ANGEL:
				coverings.put(BodyCoveringType.MOUTH, new Covering(BodyCoveringType.MOUTH, CoveringPattern.ORIFICE_MOUTH, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, Colour.ORIFICE_INTERIOR, false));
				break;
			case DEMON:
				coverings.put(BodyCoveringType.MOUTH, new Covering(BodyCoveringType.MOUTH, CoveringPattern.ORIFICE_MOUTH, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, Colour.ORIFICE_INTERIOR, false));
				break;
			case SLIME:
				coverings.put(BodyCoveringType.MOUTH_SLIME, new Covering(BodyCoveringType.MOUTH_SLIME, CoveringPattern.ORIFICE_MOUTH, coverings.get(BodyCoveringType.SLIME).getPrimaryColour(),
						false, coverings.get(BodyCoveringType.SLIME).getPrimaryColour(), false));
				break;
			default:
				coverings.put(BodyCoveringType.MOUTH, new Covering(BodyCoveringType.MOUTH, CoveringPattern.ORIFICE_MOUTH, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, Colour.ORIFICE_INTERIOR, false));
				break;
		}
		
		if(vagina.getType().getRace()!=null) {
			switch(vagina.getType().getRace()) {
				case ANGEL:
					coverings.put(BodyCoveringType.VAGINA, new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, Colour.ORIFICE_INTERIOR, false));
					break;
				case DEMON:
					coverings.put(BodyCoveringType.VAGINA, new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, Colour.ORIFICE_INTERIOR, false));
					break;
				case SLIME:
					coverings.put(BodyCoveringType.VAGINA_SLIME, new Covering(BodyCoveringType.VAGINA_SLIME, CoveringPattern.ORIFICE_VAGINA, coverings.get(BodyCoveringType.SLIME).getPrimaryColour(),
							false, coverings.get(BodyCoveringType.SLIME).getPrimaryColour(), false));
					break;
				default:
					coverings.put(BodyCoveringType.VAGINA, new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, Colour.ORIFICE_INTERIOR, false));
					break;
			}
		} else {
			switch(getRace()) {
				case ANGEL:
					coverings.put(BodyCoveringType.VAGINA, new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, Colour.ORIFICE_INTERIOR, false));
					break;
				case DEMON:
					coverings.put(BodyCoveringType.VAGINA, new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, Colour.ORIFICE_INTERIOR, false));
					break;
				case SLIME:
					coverings.put(BodyCoveringType.VAGINA_SLIME, new Covering(BodyCoveringType.VAGINA_SLIME, CoveringPattern.ORIFICE_VAGINA, coverings.get(BodyCoveringType.SLIME).getPrimaryColour(),
							false, coverings.get(BodyCoveringType.SLIME).getPrimaryColour(), false));
					break;
				default:
					coverings.put(BodyCoveringType.VAGINA, new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, Colour.ORIFICE_INTERIOR, false));
					break;
			}
		}
		
		if(penis.getType().getRace()!=null) {
			switch(penis.getType().getRace()) {
				case ANGEL:
					coverings.put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, CoveringPattern.NONE, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, Colour.ORIFICE_INTERIOR, false));
					break;
				case DEMON:
					coverings.put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, CoveringPattern.NONE, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, Colour.ORIFICE_INTERIOR, false));
					break;
				case SLIME:
					coverings.put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, CoveringPattern.NONE, coverings.get(BodyCoveringType.SLIME).getPrimaryColour(), false, coverings.get(BodyCoveringType.SLIME).getPrimaryColour(), false));
					break;
				default:
					coverings.put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, CoveringPattern.NONE, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, Colour.ORIFICE_INTERIOR, false));
					break;
			}
		} else {
			switch(getRace()) {
				case ANGEL:
					coverings.put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, CoveringPattern.NONE, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, Colour.ORIFICE_INTERIOR, false));
					break;
				case DEMON:
					coverings.put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, CoveringPattern.NONE, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, Colour.ORIFICE_INTERIOR, false));
					break;
				case SLIME:
					coverings.put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, CoveringPattern.NONE, coverings.get(BodyCoveringType.SLIME).getPrimaryColour(), false, coverings.get(BodyCoveringType.SLIME).getPrimaryColour(), false));
					break;
				default:
					coverings.put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, CoveringPattern.NONE, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, Colour.ORIFICE_INTERIOR, false));
					break;
			}
		}
	}
	
	public boolean isAbleToFly() {
		return arm.getType().allowsFlight() || wing.getType().allowsFlight();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
