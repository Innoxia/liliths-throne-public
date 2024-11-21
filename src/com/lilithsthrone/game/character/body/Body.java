package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractArmType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAssType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractBreastType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEarType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEyeType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFaceType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHairType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHornType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractLegType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractPenisType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTentacleType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTongueType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTorsoType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractVaginaType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractWingType;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringCategory;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringSkinToneColorHelper;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.TentacleType;
import com.lilithsthrone.game.character.body.types.TorsoType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AgeCategory;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeShape;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodyShape;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.FluidTypeBase;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.StartingSkinTone;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.pregnancy.Litter;
import com.lilithsthrone.game.character.pregnancy.PregnancyPossibility;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.AbstractRacialBody;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.FeralAttributes;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Builder;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.4.0
 * @author Innoxia
 */
public class Body implements XMLSaving {
	
	/** This determines the maximum amount of fluid (in mL) that can be stored in the SexAreaOrifice.VAGINA and SexAreaOrifice.URETHRA_VAGINA while pregnant. */
	public static final int MAXIMUM_CREAMPIE_WHILE_PREGNANT = 250;
	
	// Required:
	private Arm arm;
	private Ass ass;
	private Breast breast;
	private Face face;
	private Eye eye;
	private Ear ear;
	private Hair hair;
	private Leg leg;
	private Torso torso;
	private BodyMaterial bodyMaterial;
	protected AbstractSubspecies fleshSubspecies;

	// Optional:
	private Antenna antenna;
	private BreastCrotch breastCrotch;
	private Horn horn;
	private Penis penis;
	private Tail tail;
	private Tentacle tentacle;
	private Vagina vagina;
	private Wing wing;

	private OrificeSpinneret spinneret;
	
	private GenitalArrangement genitalArrangement;
	
	private boolean feral;
	
	private Map<AbstractRace, Integer> raceWeightMap = new ConcurrentHashMap<>();
	private AbstractSubspecies subspecies;
	/** This keeps track of what this body's subspecies was at the moment of last being saved. it's only used in BodyChanging.java as part of save/load transformation presets. */
	private AbstractSubspecies loadedSubspecies;
	private RaceStage raceStage;
	private boolean piercedStomach = false;
	private AbstractSubspecies subspeciesOverride = null;
	private AbstractSubspecies halfDemonSubspecies = null;
	private int height;
	private int femininity;
	private int bodySize;
	private int muscle;
	private BodyHair pubicHair;

	private Set<AbstractBodyCoveringType> heavyMakeup;
	private Map<AbstractBodyCoveringType, Covering> coverings;
	private Set<AbstractBodyCoveringType> coveringsDiscovered;

	private List<BodyPartInterface> allBodyParts;
	private List<BodyPartInterface> allBodyPartsExtended;
	
	private boolean takesAfterMother = true;
	
	
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
		private final Torso torso;
		private final BodyMaterial bodyMaterial;
		private GenitalArrangement genitalArrangement;
		private final int height;
		private final int femininity, bodySize, muscle;
		
		// Optional parameters - initialised to null values:
		private Antenna antenna = new Antenna(AntennaType.NONE, 0);
		private BreastCrotch breastCrotch = new BreastCrotch(BreastType.NONE, BreastShape.ROUND, 0, 0, 1, 1, NippleShape.NORMAL, 1, AreolaeShape.NORMAL, 1, 0, 2, 0, 0, true);
		private Horn horn = new Horn(HornType.NONE, 0);
		private Penis penis = new Penis(PenisType.NONE, 0, false, 0, 0, 0, 0);
		private Tail tail = new Tail(TailType.NONE);
		private Tentacle tentacle = new Tentacle(TentacleType.NONE);
		private Vagina vagina = new Vagina(VaginaType.NONE, 0, 0, 0, 0, 0, 2, 3, 3, true);
		private Wing wing = new Wing(WingType.NONE, 0);
		private OrificeSpinneret spinneret = new OrificeSpinneret();

		public BodyBuilder(Arm arm, Ass ass, Breast breast, Face face, Eye eye, Ear ear, Hair hair, Leg leg, Torso torso, BodyMaterial bodyMaterial, GenitalArrangement genitalArrangement, int height, int femininity, int bodySize, int muscle) {
			this.arm = arm;
			this.ass = ass;
			this.breast = breast;
			this.face = face;
			this.eye = eye;
			this.ear = ear;
			this.hair = hair;
			this.leg = leg;
			this.torso = torso;
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
		
		public BodyBuilder breastCrotch(BreastCrotch breastCrotch) {
			this.breastCrotch = breastCrotch;
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
		
		public BodyBuilder tail(Tail tail) {
			this.tail = tail;
			return this;
		}

		public BodyBuilder tentacle(Tentacle tentacle) {
			this.tentacle = tentacle;
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

		public BodyBuilder spinneret(OrificeSpinneret spinneret) {
			this.spinneret = spinneret;
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
		breastCrotch = builder.breastCrotch;
		face = builder.face;
		eye = builder.eye;
		ear = builder.ear;
		hair = builder.hair;
		leg = builder.leg;
		torso = builder.torso;
		horn = builder.horn;
		penis = builder.penis;
		tail = builder.tail;
		tentacle = builder.tentacle;
		vagina = builder.vagina;
		wing = builder.wing;
		
		spinneret = builder.spinneret;
		
		bodyMaterial = builder.bodyMaterial;
		genitalArrangement = builder.genitalArrangement;
		
		feral = false;
		
		height = builder.height;
		femininity = builder.femininity;
		bodySize = builder.bodySize;
		muscle = builder.muscle;
		
		this.pubicHair = BodyHair.ZERO_NONE;
		
		handleAllBodyPartsList();
		
		coverings = new HashMap<>();
		heavyMakeup = new HashSet<>();

		applyStartingCoveringValues();
		
		coveringsDiscovered = new HashSet<>();
		for(BodyPartInterface bp : allBodyPartsExtended) {
			if(bp.getBodyCoveringType(this)!=null) {
				coveringsDiscovered.add(bp.getBodyCoveringType(this));
			}
		}
		
		addDiscoveredBodyCoveringsFromMaterial(bodyMaterial);
		
		calculateRace(null);
		
		coveringsDiscovered.add(getBodyHairCoveringType(this.getRace()));
	}
	
	public Body(Body bodyToCopy) {
		// Core:
		this.genitalArrangement = bodyToCopy.getGenitalArrangement();
		this.feral = bodyToCopy.isFeral();
		this.subspecies = bodyToCopy.getSubspecies();
		this.piercedStomach = bodyToCopy.isPiercedStomach();
		if(bodyToCopy.getSubspeciesOverride()!=null) {
			this.subspeciesOverride = bodyToCopy.getSubspeciesOverride();
		}
		this.height = bodyToCopy.getHeightValue();
		this.femininity = bodyToCopy.getFemininity();
		this.bodySize = bodyToCopy.getBodySize();
		this.muscle = bodyToCopy.getMuscle();
		this.pubicHair = bodyToCopy.getPubicHair();
		this.bodyMaterial = bodyToCopy.getBodyMaterial();
		this.takesAfterMother = bodyToCopy.isTakesAfterMother();
		
		// Coverings:
		
		this.coverings = new HashMap<>();
		for(Entry<AbstractBodyCoveringType, Covering> entry : bodyToCopy.coverings.entrySet()) {
			coverings.put(entry.getKey(), new Covering(entry.getValue()));
		}
		this.coveringsDiscovered = new HashSet<>(bodyToCopy.coveringsDiscovered);
		
		this.heavyMakeup = new HashSet<>(bodyToCopy.heavyMakeup);
		
		// Body parts:
		
		this.antenna = new Antenna(bodyToCopy.antenna);
		this.arm = new Arm(bodyToCopy.arm);
		this.ass = new Ass(bodyToCopy.ass);
		this.breast = new Breast(bodyToCopy.breast);
		this.breastCrotch = new BreastCrotch(bodyToCopy.breastCrotch);
		this.ear = new Ear(bodyToCopy.ear);
		this.eye = new Eye(bodyToCopy.eye);
		this.face = new Face(bodyToCopy.face);
		this.hair = new Hair(bodyToCopy.hair);
		this.horn = new Horn(bodyToCopy.horn);
		this.leg = new Leg(bodyToCopy.leg);
		this.penis = new Penis(bodyToCopy.penis);
		this.spinneret = new OrificeSpinneret(bodyToCopy.spinneret);
		this.torso = new Torso(bodyToCopy.torso);
		this.tail = new Tail(bodyToCopy.tail);
		this.tentacle = new Tentacle(bodyToCopy.tentacle);
		this.vagina = new Vagina(bodyToCopy.vagina);
		this.wing = new Wing(bodyToCopy.wing);

		handleAllBodyPartsList();
		calculateRace(null);
	}
	
	public void handleAllBodyPartsList() {
		allBodyParts = new ArrayList<>();
		allBodyParts.add(antenna);
		allBodyParts.add(arm);
		allBodyParts.add(ass);
		allBodyParts.add(breast);
		allBodyParts.add(breastCrotch);
		allBodyParts.add(face);
		allBodyParts.add(eye);
		allBodyParts.add(ear);
		allBodyParts.add(hair);
		allBodyParts.add(leg);
		allBodyParts.add(torso);
		allBodyParts.add(horn);
		allBodyParts.add(penis);
		allBodyParts.add(tail);
		allBodyParts.add(tentacle);
		allBodyParts.add(vagina);
		allBodyParts.add(wing);

		allBodyPartsExtended = new ArrayList<>();
		allBodyPartsExtended.addAll(allBodyParts);
		allBodyPartsExtended.add(ass.getAnus());
		allBodyPartsExtended.add(breast.getNipples());
		allBodyPartsExtended.add(breastCrotch.getNipples());
		allBodyPartsExtended.add(face.getMouth());
		allBodyPartsExtended.add(face.getTongue());
	}
	
	public void addDiscoveredBodyCoveringsFromMaterial(BodyMaterial bodyMaterial) {
		if(bodyMaterial!=BodyMaterial.FLESH) {
			for(BodyCoveringCategory cat : BodyCoveringCategory.values()) {
				if(cat.isInfluencedByMaterialType()) {
					coveringsDiscovered.add(BodyCoveringType.getMaterialBodyCoveringType(bodyMaterial, cat));
				}
			}
			
		} else {
			coveringsDiscovered.add(BodyCoveringType.EYE_SCLERA);
			coveringsDiscovered.add(BodyCoveringType.NIPPLES);
			coveringsDiscovered.add(BodyCoveringType.NIPPLES_CROTCH);
			coveringsDiscovered.add(BodyCoveringType.TONGUE);
			coveringsDiscovered.add(BodyCoveringType.MOUTH);
			coveringsDiscovered.add(BodyCoveringType.ANUS);
			coveringsDiscovered.add(BodyCoveringType.VAGINA);
			coveringsDiscovered.add(BodyCoveringType.PENIS);
			coveringsDiscovered.add(BodyCoveringType.SPINNERET);
		}
	}
	
	public static AbstractBodyCoveringType getBodyHairCoveringType(AbstractRace race) {
		return race.getRacialBody().getBodyHairType();
	}
	
	private void applyStartingCoveringValues() {
		
		// Everything is based on human skin value:
		StartingSkinTone tone = StartingSkinTone.values()[Util.random.nextInt(StartingSkinTone.values().length)];
		
		for(AbstractBodyCoveringType s : BodyCoveringType.getAllBodyCoveringTypes()) {
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
						PresetColour.COVERING_NONE, false,
						PresetColour.COVERING_NONE, false));
				continue;
			}
			
			List<Colour> colourApplicationList = BodyCoveringSkinToneColorHelper.getAcceptableColoursForPrimary(tone, s);
			Colour primary = colourApplicationList.get(Util.random.nextInt(colourApplicationList.size()));
			
			Colour secondary = primary;
			
			if(!s.getNaturalColoursSecondary().isEmpty()) {
				colourApplicationList = new ArrayList<>(BodyCoveringSkinToneColorHelper.getAcceptableColoursForSecondary(tone, s));
				colourApplicationList.remove(primary);
				if(!colourApplicationList.isEmpty()) {
					secondary = colourApplicationList.get(Util.random.nextInt(colourApplicationList.size()));
				}
			}
			
//			List<CoveringPattern> availablePatterns = new ArrayList<>(s.getNaturalPatterns().keySet());
//			if(availablePatterns.size()>1) {
//				availablePatterns.remove(CoveringPattern.FRECKLED); // Do not start with freckles
//			}
			
			CoveringPattern pattern = Util.getRandomObjectFromWeightedMap(s.getNaturalPatterns());//availablePatterns.get(Util.random.nextInt(availablePatterns.size()));
			if(pattern==CoveringPattern.EYE_IRISES) {
				pattern = CoveringPattern.EYE_IRISES_HETEROCHROMATIC;
			}
			if(pattern == CoveringPattern.EYE_IRISES_HETEROCHROMATIC) {
				if(Math.random()>0.02f) { // As it's already selected heterochromatic eyes (0.5 chance), this 0.02 chance corresponds to an overall heterochromatic chance of 0.01, or 1%
					pattern = CoveringPattern.EYE_IRISES;
				} else {
					if(primary==secondary) {
						List<Colour> secondaryIrisColours = new ArrayList<>(colourApplicationList);
						secondaryIrisColours.remove(primary);
						if(secondaryIrisColours.isEmpty()) {
							pattern = CoveringPattern.EYE_IRISES;
							secondary = primary;
						} else {
							secondary = Util.randomItemFrom(secondaryIrisColours);
						}
					}
				}
			}
			
			coverings.put(s, new Covering(s,
					pattern,
					primary, false,
					secondary, false));
		}

		updateCoverings(true, true, true, true);
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		// Core:
		Element bodyCore = doc.createElement("bodyCore");
		parentElement.appendChild(bodyCore);
		XMLUtil.addAttribute(doc, bodyCore, "version", Main.VERSION_NUMBER);
		XMLUtil.addAttribute(doc, bodyCore, "piercedStomach", String.valueOf(this.isPiercedStomach()));
		XMLUtil.addAttribute(doc, bodyCore, "height", String.valueOf(this.getHeightValue()));
		XMLUtil.addAttribute(doc, bodyCore, "femininity", String.valueOf(this.getFemininity()));
		XMLUtil.addAttribute(doc, bodyCore, "bodySize", String.valueOf(this.getBodySize()));
		XMLUtil.addAttribute(doc, bodyCore, "muscle", String.valueOf(this.getMuscle()));
		XMLUtil.addAttribute(doc, bodyCore, "pubicHair", String.valueOf(this.getPubicHair()));
		XMLUtil.addAttribute(doc, bodyCore, "bodyMaterial", String.valueOf(this.getBodyMaterial()));
		XMLUtil.addAttribute(doc, bodyCore, "genitalArrangement", String.valueOf(this.getGenitalArrangement()));
		XMLUtil.addAttribute(doc, bodyCore, "feral", String.valueOf(this.isFeral()));
		if(this.getSubspeciesOverride()!=null) {
			XMLUtil.addAttribute(doc, bodyCore, "subspeciesOverride", Subspecies.getIdFromSubspecies(this.getSubspeciesOverride()));
		}
		XMLUtil.addAttribute(doc, bodyCore, "subspecies", Subspecies.getIdFromSubspecies(this.getSubspecies()));
		XMLUtil.addAttribute(doc, bodyCore, "takesAfterMother", String.valueOf(this.isTakesAfterMother()));
		
		for(AbstractBodyCoveringType bct : BodyCoveringType.getAllBodyCoveringTypes()) {
			Covering covering = this.coverings.get(bct);
			if(this.isBodyCoveringTypesDiscovered(bct)
					|| ((bct == BodyCoveringType.MAKEUP_BLUSHER
							|| bct == BodyCoveringType.MAKEUP_EYE_LINER
							|| bct == BodyCoveringType.MAKEUP_EYE_SHADOW
							|| bct == BodyCoveringType.MAKEUP_LIPSTICK
							|| bct == BodyCoveringType.MAKEUP_NAIL_POLISH_FEET
							|| bct == BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS)
							&& covering.getPrimaryColour()!=PresetColour.COVERING_NONE)
					|| bct == BodyCoveringType.EYE_PUPILS
					|| bct.getCategory()==BodyCoveringCategory.FLUID
					|| bct == getBodyHairCoveringType(this.getRace())) {
				Element element = doc.createElement("bodyCovering");
				bodyCore.appendChild(element);
				
				XMLUtil.addAttribute(doc, element, "type", BodyCoveringType.getIdFromBodyCoveringType(bct));
				XMLUtil.addAttribute(doc, element, "pattern", covering.getPattern().toString());
				XMLUtil.addAttribute(doc, element, "modifier", covering.getModifier().toString());
				XMLUtil.addAttribute(doc, element, "c1", covering.getPrimaryColour().getId());
				if(covering.isPrimaryGlowing()) {
					XMLUtil.addAttribute(doc, element, "g1", String.valueOf(covering.isPrimaryGlowing()));
				}
				if(covering.getPrimaryColour()!=covering.getSecondaryColour()) {
					XMLUtil.addAttribute(doc, element, "c2", covering.getSecondaryColour().getId());
				}
				if(covering.isSecondaryGlowing()) {
					XMLUtil.addAttribute(doc, element, "g2", String.valueOf(covering.isSecondaryGlowing()));
				}
			}
		}
		
		if(!heavyMakeup.isEmpty()) {
			Element element = doc.createElement("heavyMakeup");
			bodyCore.appendChild(element);
			for(AbstractBodyCoveringType bct : heavyMakeup) {
				Element bctElement = doc.createElement("type");
				element.appendChild(bctElement);
				bctElement.setTextContent(BodyCoveringType.getIdFromBodyCoveringType(bct));
			}
		}
		

		// Antennae:
		Element bodyAntennae = doc.createElement("antennae");
		parentElement.appendChild(bodyAntennae);
			XMLUtil.addAttribute(doc, bodyAntennae, "type", AntennaType.getIdFromAntennaType(this.antenna.getType()));
			XMLUtil.addAttribute(doc, bodyAntennae, "rows", String.valueOf(this.antenna.getAntennaRows()));
			XMLUtil.addAttribute(doc, bodyAntennae, "length", String.valueOf(this.antenna.length));
			XMLUtil.addAttribute(doc, bodyAntennae, "antennaePerRow", String.valueOf(this.antenna.antennaePerRow));
		
		// Arm:
		Element bodyArm = doc.createElement("arm");
		parentElement.appendChild(bodyArm);
			XMLUtil.addAttribute(doc, bodyArm, "type", ArmType.getIdFromArmType(this.arm.getType()));
			XMLUtil.addAttribute(doc, bodyArm, "rows", String.valueOf(this.arm.getArmRows()));
			XMLUtil.addAttribute(doc, bodyArm, "underarmHair", this.arm.getUnderarmHair().toString());
		
		// Ass:
		Element bodyAss = doc.createElement("ass");
		parentElement.appendChild(bodyAss);
			XMLUtil.addAttribute(doc, bodyAss, "type", AssType.getIdFromAssType(this.ass.getType()));
			XMLUtil.addAttribute(doc, bodyAss, "assSize", String.valueOf(this.ass.getAssSize().getValue()));
			XMLUtil.addAttribute(doc, bodyAss, "hipSize", String.valueOf(this.ass.getHipSize().getValue()));

		Element bodyAnus = doc.createElement("anus");
		parentElement.appendChild(bodyAnus);
			XMLUtil.addAttribute(doc, bodyAnus, "wetness", String.valueOf(this.ass.anus.orificeAnus.wetness));
			XMLUtil.addAttribute(doc, bodyAnus, "depth", String.valueOf(this.ass.anus.orificeAnus.depth));
			XMLUtil.addAttribute(doc, bodyAnus, "elasticity", String.valueOf(this.ass.anus.orificeAnus.elasticity));
			XMLUtil.addAttribute(doc, bodyAnus, "plasticity", String.valueOf(this.ass.anus.orificeAnus.plasticity));
			XMLUtil.addAttribute(doc, bodyAnus, "capacity", String.valueOf(this.ass.anus.orificeAnus.capacity));
			XMLUtil.addAttribute(doc, bodyAnus, "stretchedCapacity", String.valueOf(this.ass.anus.orificeAnus.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodyAnus, "virgin", String.valueOf(this.ass.anus.orificeAnus.virgin));
			XMLUtil.addAttribute(doc, bodyAnus, "bleached", String.valueOf(this.ass.anus.bleached));
			XMLUtil.addAttribute(doc, bodyAnus, "assHair", this.ass.anus.assHair.toString());
			for(OrificeModifier om : this.ass.anus.orificeAnus.getOrificeModifiers()) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(om.toString());
				bodyAnus.appendChild(mod);
			}
		
		// Breasts:
		Element bodyBreast = doc.createElement("breasts");
		parentElement.appendChild(bodyBreast);
			XMLUtil.addAttribute(doc, bodyBreast, "type", BreastType.getIdFromBreastType(this.breast.getType()));
			XMLUtil.addAttribute(doc, bodyBreast, "shape", this.breast.shape.toString());
			XMLUtil.addAttribute(doc, bodyBreast, "size", String.valueOf(this.breast.size));
			XMLUtil.addAttribute(doc, bodyBreast, "rows", String.valueOf(this.breast.rows));
			XMLUtil.addAttribute(doc, bodyBreast, "milkStorage", String.valueOf(this.breast.milkStorage));
			XMLUtil.addAttribute(doc, bodyBreast, "storedMilk", String.valueOf(this.breast.milkStored));
			XMLUtil.addAttribute(doc, bodyBreast, "milkRegeneration", String.valueOf(this.breast.milkRegeneration));
			XMLUtil.addAttribute(doc, bodyBreast, "nippleCountPerBreast", String.valueOf(this.breast.nippleCountPerBreast));

		Element bodyNipple = doc.createElement("nipples");
		parentElement.appendChild(bodyNipple);
			XMLUtil.addAttribute(doc, bodyNipple, "depth", String.valueOf(this.breast.nipples.orificeNipples.depth));
			XMLUtil.addAttribute(doc, bodyNipple, "elasticity", String.valueOf(this.breast.nipples.orificeNipples.elasticity));
			XMLUtil.addAttribute(doc, bodyNipple, "plasticity", String.valueOf(this.breast.nipples.orificeNipples.plasticity));
			XMLUtil.addAttribute(doc, bodyNipple, "capacity", String.valueOf(this.breast.nipples.orificeNipples.capacity));
			XMLUtil.addAttribute(doc, bodyNipple, "stretchedCapacity", String.valueOf(this.breast.nipples.orificeNipples.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodyNipple, "virgin", String.valueOf(this.breast.nipples.orificeNipples.virgin));
			XMLUtil.addAttribute(doc, bodyNipple, "pierced", String.valueOf(this.breast.nipples.pierced));
			XMLUtil.addAttribute(doc, bodyNipple, "nippleSize", String.valueOf(this.breast.nipples.nippleSize));
			XMLUtil.addAttribute(doc, bodyNipple, "nippleShape", this.breast.nipples.nippleShape.toString());
			XMLUtil.addAttribute(doc, bodyNipple, "areolaeSize", String.valueOf(this.breast.nipples.areolaeSize));
			XMLUtil.addAttribute(doc, bodyNipple, "areolaeShape", this.breast.nipples.areolaeShape.toString());
			for(OrificeModifier om : this.breast.nipples.orificeNipples.getOrificeModifiers()) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(om.toString());
				bodyNipple.appendChild(mod);
			}
			
		this.breast.milk.saveAsXML("milk", parentElement, doc);
		
		// Crotch Breasts:
		Element bodyCrotchBreast = doc.createElement("breastsCrotch");
		parentElement.appendChild(bodyCrotchBreast);
			XMLUtil.addAttribute(doc, bodyCrotchBreast, "type", BreastType.getIdFromBreastType(this.breastCrotch.getType()));
			XMLUtil.addAttribute(doc, bodyCrotchBreast, "shape", this.breastCrotch.shape.toString());
			XMLUtil.addAttribute(doc, bodyCrotchBreast, "size", String.valueOf(this.breastCrotch.size));
			XMLUtil.addAttribute(doc, bodyCrotchBreast, "rows", String.valueOf(this.breastCrotch.rows));
			XMLUtil.addAttribute(doc, bodyCrotchBreast, "milkStorage", String.valueOf(this.breastCrotch.milkStorage));
			XMLUtil.addAttribute(doc, bodyCrotchBreast, "storedMilk", String.valueOf(this.breastCrotch.milkStored));
			XMLUtil.addAttribute(doc, bodyCrotchBreast, "milkRegeneration", String.valueOf(this.breastCrotch.milkRegeneration));
			XMLUtil.addAttribute(doc, bodyCrotchBreast, "nippleCountPerBreast", String.valueOf(this.breastCrotch.nippleCountPerBreast));

		Element bodyCrotchNipple = doc.createElement("nipplesCrotch");
		parentElement.appendChild(bodyCrotchNipple);
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "depth", String.valueOf(this.breastCrotch.nipples.orificeNipples.depth));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "elasticity", String.valueOf(this.breastCrotch.nipples.orificeNipples.elasticity));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "plasticity", String.valueOf(this.breastCrotch.nipples.orificeNipples.plasticity));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "capacity", String.valueOf(this.breastCrotch.nipples.orificeNipples.capacity));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "stretchedCapacity", String.valueOf(this.breastCrotch.nipples.orificeNipples.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "virgin", String.valueOf(this.breastCrotch.nipples.orificeNipples.virgin));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "pierced", String.valueOf(this.breastCrotch.nipples.pierced));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "nippleSize", String.valueOf(this.breastCrotch.nipples.nippleSize));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "nippleShape", this.breastCrotch.nipples.nippleShape.toString());
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "areolaeSize", String.valueOf(this.breastCrotch.nipples.areolaeSize));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "areolaeShape", this.breastCrotch.nipples.areolaeShape.toString());
			for(OrificeModifier om : this.breastCrotch.nipples.orificeNipples.getOrificeModifiers()) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(om.toString());
				bodyCrotchNipple.appendChild(mod);
			}
			
		this.breastCrotch.milk.saveAsXML("milkCrotch", parentElement, doc);
		
		
		// Ear:
		Element bodyEar = doc.createElement("ear");
		parentElement.appendChild(bodyEar);
			XMLUtil.addAttribute(doc, bodyEar, "type", EarType.getIdFromEarType(this.ear.type));
			XMLUtil.addAttribute(doc, bodyEar, "pierced", String.valueOf(this.ear.pierced));

		// Eye:
		Element bodyEye = doc.createElement("eye");
		parentElement.appendChild(bodyEye);
			XMLUtil.addAttribute(doc, bodyEye, "type", EyeType.getIdFromEyeType(this.eye.type));
			XMLUtil.addAttribute(doc, bodyEye, "eyePairs", String.valueOf(this.eye.eyePairs));
			XMLUtil.addAttribute(doc, bodyEye, "irisShape", this.eye.irisShape.toString());
			XMLUtil.addAttribute(doc, bodyEye, "pupilShape", this.eye.pupilShape.toString());
		
		// Face:
		Element bodyFace = doc.createElement("face");
		parentElement.appendChild(bodyFace);
			XMLUtil.addAttribute(doc, bodyFace, "type", FaceType.getIdFromFaceType(this.face.type));
			XMLUtil.addAttribute(doc, bodyFace, "piercedNose", String.valueOf(this.face.piercedNose));
			XMLUtil.addAttribute(doc, bodyFace, "facialHair", this.face.facialHair.toString());

		Element bodyMouth = doc.createElement("mouth");
		parentElement.appendChild(bodyMouth);
			XMLUtil.addAttribute(doc, bodyMouth, "depth", String.valueOf(this.face.mouth.orificeMouth.depth));
			XMLUtil.addAttribute(doc, bodyMouth, "elasticity", String.valueOf(this.face.mouth.orificeMouth.elasticity));
			XMLUtil.addAttribute(doc, bodyMouth, "plasticity", String.valueOf(this.face.mouth.orificeMouth.plasticity));
			XMLUtil.addAttribute(doc, bodyMouth, "capacity", String.valueOf(this.face.mouth.orificeMouth.capacity));
			XMLUtil.addAttribute(doc, bodyMouth, "wetness", String.valueOf(this.face.mouth.orificeMouth.wetness));
			XMLUtil.addAttribute(doc, bodyMouth, "stretchedCapacity", String.valueOf(this.face.mouth.orificeMouth.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodyMouth, "virgin", String.valueOf(this.face.mouth.orificeMouth.virgin));
			XMLUtil.addAttribute(doc, bodyMouth, "piercedLip", String.valueOf(this.face.mouth.piercedLip));
			XMLUtil.addAttribute(doc, bodyMouth, "lipSize", String.valueOf(this.face.mouth.lipSize));
			for(OrificeModifier om : this.face.mouth.orificeMouth.getOrificeModifiers()) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(om.toString());
				bodyMouth.appendChild(mod);
			}
			
		Element bodyTongue = doc.createElement("tongue");
		parentElement.appendChild(bodyTongue);
			XMLUtil.addAttribute(doc, bodyTongue, "piercedTongue", String.valueOf(this.face.tongue.pierced));
			XMLUtil.addAttribute(doc, bodyTongue, "tongueLength", String.valueOf(this.face.tongue.tongueLength));
			for(TongueModifier tm : this.face.tongue.tongueModifiers) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(tm.toString());
				bodyTongue.appendChild(mod);
			}
			
		
		// Hair:
		Element bodyHair = doc.createElement("hair");
		parentElement.appendChild(bodyHair);
			XMLUtil.addAttribute(doc, bodyHair, "type", HairType.getIdFromHairType(this.hair.type));
			XMLUtil.addAttribute(doc, bodyHair, "length", String.valueOf(this.hair.length));
			XMLUtil.addAttribute(doc, bodyHair, "hairStyle", this.hair.style.toString());
			XMLUtil.addAttribute(doc, bodyHair, "neckFluff", String.valueOf(this.hair.neckFluff));
		
		// Horn:
		Element bodyHorn = doc.createElement("horn");
		parentElement.appendChild(bodyHorn);
			XMLUtil.addAttribute(doc, bodyHorn, "type", HornType.getIdFromHornType(this.horn.type));
			XMLUtil.addAttribute(doc, bodyHorn, "length", String.valueOf(this.horn.length));
			XMLUtil.addAttribute(doc, bodyHorn, "rows", String.valueOf(this.horn.rows));
			XMLUtil.addAttribute(doc, bodyHorn, "hornsPerRow", String.valueOf(this.horn.hornsPerRow));
		
		// Leg:
		Element bodyLeg = doc.createElement("leg");
		parentElement.appendChild(bodyLeg);
			XMLUtil.addAttribute(doc, bodyLeg, "type", LegType.getIdFromLegType(this.leg.type));
			XMLUtil.addAttribute(doc, bodyLeg, "footStructure", this.leg.footStructure.toString());
			XMLUtil.addAttribute(doc, bodyLeg, "configuration", this.leg.legConfiguration.toString());
			XMLUtil.addAttribute(doc, bodyLeg, "tailLength", String.valueOf(this.leg.lengthAsPercentageOfHeight));
		
		// Penis:
		Element bodyPenis = doc.createElement("penis");
		parentElement.appendChild(bodyPenis);
			XMLUtil.addAttribute(doc, bodyPenis, "type", PenisType.getIdFromPenisType(this.penis.type));
			XMLUtil.addAttribute(doc, bodyPenis, "size", String.valueOf(this.penis.length));
			XMLUtil.addAttribute(doc, bodyPenis, "girth", String.valueOf(this.penis.girth));
			XMLUtil.addAttribute(doc, bodyPenis, "pierced", String.valueOf(this.penis.pierced));
			XMLUtil.addAttribute(doc, bodyPenis, "virgin", String.valueOf(this.penis.virgin));
			for(PenetrationModifier pm : this.penis.getPenisModifiers()) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(pm.toString());
				bodyPenis.appendChild(mod);
			}
			XMLUtil.addAttribute(doc, bodyPenis, "depth", String.valueOf(this.penis.orificeUrethra.depth));
			XMLUtil.addAttribute(doc, bodyPenis, "elasticity", String.valueOf(this.penis.orificeUrethra.elasticity));
			XMLUtil.addAttribute(doc, bodyPenis, "plasticity", String.valueOf(this.penis.orificeUrethra.plasticity));
			XMLUtil.addAttribute(doc, bodyPenis, "capacity", String.valueOf(this.penis.orificeUrethra.capacity));
			XMLUtil.addAttribute(doc, bodyPenis, "stretchedCapacity", String.valueOf(this.penis.orificeUrethra.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodyPenis, "urethraVirgin", String.valueOf(this.penis.orificeUrethra.virgin));
			for(OrificeModifier om : this.penis.orificeUrethra.getOrificeModifiers()) {
				Element mod = doc.createElement("modUrethra");
				mod.setTextContent(om.toString());
				bodyPenis.appendChild(mod);
			}
			
		Element bodyTesticle = doc.createElement("testicles");
		parentElement.appendChild(bodyTesticle);
			XMLUtil.addAttribute(doc, bodyTesticle, "testicleSize", String.valueOf(this.penis.testicle.testicleSize));
			XMLUtil.addAttribute(doc, bodyTesticle, "cumStorage", String.valueOf(this.penis.testicle.cumStorage));
			XMLUtil.addAttribute(doc, bodyTesticle, "storedCum", String.valueOf(this.penis.testicle.cumStored));
			XMLUtil.addAttribute(doc, bodyTesticle, "cumRegeneration", String.valueOf(this.penis.testicle.cumRegeneration));
			XMLUtil.addAttribute(doc, bodyTesticle, "cumExpulsion", String.valueOf(this.penis.testicle.cumExpulsion));
			XMLUtil.addAttribute(doc, bodyTesticle, "numberOfTesticles", String.valueOf(this.penis.testicle.testicleCount));
			XMLUtil.addAttribute(doc, bodyTesticle, "internal", String.valueOf(this.penis.testicle.internal));
		
		this.penis.testicle.cum.saveAsXML("cum", parentElement, doc);

		// Spinneret:
		Element bodySpinneret = doc.createElement("spinneret");
		parentElement.appendChild(bodySpinneret);
			XMLUtil.addAttribute(doc, bodySpinneret, "wetness", String.valueOf(this.spinneret.wetness));
			XMLUtil.addAttribute(doc, bodySpinneret, "depth", String.valueOf(this.spinneret.depth));
			XMLUtil.addAttribute(doc, bodySpinneret, "elasticity", String.valueOf(this.spinneret.elasticity));
			XMLUtil.addAttribute(doc, bodySpinneret, "plasticity", String.valueOf(this.spinneret.plasticity));
			XMLUtil.addAttribute(doc, bodySpinneret, "capacity", String.valueOf(this.spinneret.capacity));
			XMLUtil.addAttribute(doc, bodySpinneret, "stretchedCapacity", String.valueOf(this.spinneret.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodySpinneret, "virgin", String.valueOf(this.spinneret.virgin));
			for(OrificeModifier om : this.spinneret.getOrificeModifiers()) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(om.toString());
				bodySpinneret.appendChild(mod);
			}
			
		// Torso:
		Element bodyTorso = doc.createElement("torso");
		parentElement.appendChild(bodyTorso);
			XMLUtil.addAttribute(doc, bodyTorso, "type", TorsoType.getIdFromTorsoType(this.torso.type));
		
		// Tail:
		Element bodyTail = doc.createElement("tail");
		parentElement.appendChild(bodyTail);
			XMLUtil.addAttribute(doc, bodyTail, "type", TailType.getIdFromTailType(this.tail.type));
			XMLUtil.addAttribute(doc, bodyTail, "count", String.valueOf(this.tail.tailCount));
			XMLUtil.addAttribute(doc, bodyTail, "girth", String.valueOf(this.tail.girth));
			XMLUtil.addAttribute(doc, bodyTail, "length", String.valueOf(this.tail.lengthAsPercentageOfHeight));
		
		// Tail:
		Element bodyTentacle = doc.createElement("tentacle");
		parentElement.appendChild(bodyTentacle);
			XMLUtil.addAttribute(doc, bodyTentacle, "type", TentacleType.getIdFromTentacleType(this.tentacle.type));
			XMLUtil.addAttribute(doc, bodyTentacle, "count", String.valueOf(this.tentacle.tentacleCount));
			XMLUtil.addAttribute(doc, bodyTentacle, "girth", String.valueOf(this.tentacle.girth));
			XMLUtil.addAttribute(doc, bodyTentacle, "length", String.valueOf(this.tentacle.lengthAsPercentageOfHeight));
			
		// Vagina
		Element bodyVagina = doc.createElement("vagina");
		parentElement.appendChild(bodyVagina);
			XMLUtil.addAttribute(doc, bodyVagina, "type", VaginaType.getIdFromVaginaType(this.vagina.type));
			XMLUtil.addAttribute(doc, bodyVagina, "labiaSize", String.valueOf(this.vagina.labiaSize));
			XMLUtil.addAttribute(doc, bodyVagina, "clitSize", String.valueOf(this.vagina.clitoris.clitSize));
			XMLUtil.addAttribute(doc, bodyVagina, "clitGirth", String.valueOf(this.vagina.clitoris.girth));
			for(PenetrationModifier pm : this.vagina.clitoris.getClitorisModifiers()) {
				Element mod = doc.createElement("modClit");
				mod.setTextContent(pm.toString());
				bodyVagina.appendChild(mod);
			}
			XMLUtil.addAttribute(doc, bodyVagina, "pierced", String.valueOf(this.vagina.pierced));
			XMLUtil.addAttribute(doc, bodyVagina, "eggLayer", String.valueOf(this.vagina.eggLayer));
			
			XMLUtil.addAttribute(doc, bodyVagina, "wetness", String.valueOf(this.vagina.orificeVagina.wetness));
			XMLUtil.addAttribute(doc, bodyVagina, "depth", String.valueOf(this.vagina.orificeVagina.depth));
			XMLUtil.addAttribute(doc, bodyVagina, "elasticity", String.valueOf(this.vagina.orificeVagina.elasticity));
			XMLUtil.addAttribute(doc, bodyVagina, "plasticity", String.valueOf(this.vagina.orificeVagina.plasticity));
			XMLUtil.addAttribute(doc, bodyVagina, "capacity", String.valueOf(this.vagina.orificeVagina.capacity));
			XMLUtil.addAttribute(doc, bodyVagina, "stretchedCapacity", String.valueOf(this.vagina.orificeVagina.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodyVagina, "virgin", String.valueOf(this.vagina.orificeVagina.virgin));
			XMLUtil.addAttribute(doc, bodyVagina, "hymen", String.valueOf(this.vagina.orificeVagina.hymen));
			XMLUtil.addAttribute(doc, bodyVagina, "squirter", String.valueOf(this.vagina.orificeVagina.squirter));
			for(OrificeModifier om : this.vagina.orificeVagina.getOrificeModifiers()) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(om.toString());
				bodyVagina.appendChild(mod);
			}

			XMLUtil.addAttribute(doc, bodyVagina, "urethraDepth", String.valueOf(this.vagina.orificeUrethra.depth));
			XMLUtil.addAttribute(doc, bodyVagina, "urethraElasticity", String.valueOf(this.vagina.orificeUrethra.elasticity));
			XMLUtil.addAttribute(doc, bodyVagina, "urethraPlasticity", String.valueOf(this.vagina.orificeUrethra.plasticity));
			XMLUtil.addAttribute(doc, bodyVagina, "urethraCapacity", String.valueOf(this.vagina.orificeUrethra.capacity));
			XMLUtil.addAttribute(doc, bodyVagina, "urethraStretchedCapacity", String.valueOf(this.vagina.orificeUrethra.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodyVagina, "urethraVirgin", String.valueOf(this.vagina.orificeUrethra.virgin));
			for(OrificeModifier om : this.vagina.orificeUrethra.getOrificeModifiers()) {
				Element mod = doc.createElement("modUrethra");
				mod.setTextContent(om.toString());
				bodyVagina.appendChild(mod);
			}
			
		this.vagina.girlcum.saveAsXML("girlcum", parentElement, doc);

		// Wing:
		Element bodyWing = doc.createElement("wing");
		parentElement.appendChild(bodyWing);
		XMLUtil.addAttribute(doc, bodyWing, "type", WingType.getIdFromWingType(this.wing.type));
		XMLUtil.addAttribute(doc, bodyWing, "size", String.valueOf(this.wing.size));

//		System.out.println("Difference1: "+(System.nanoTime()-timeStart)/1000000000f);
		
		return parentElement;
	}

	
	private void setBodyCoveringForXMLImport(AbstractBodyCoveringType bct, CoveringPattern pattern, CoveringModifier modifier, Colour primary, boolean primaryGlow, Colour secondary, boolean secondaryGlow) {
		this.getCoverings().put(bct, new Covering(bct, pattern, modifier, primary, primaryGlow, secondary, secondaryGlow));
	}
	private void setBodyCoveringForXMLImport(AbstractBodyCoveringType bct, CoveringPattern pattern, Colour primary, boolean primaryGlow, Colour secondary, boolean secondaryGlow) {
		this.getCoverings().put(bct, new Covering(bct, pattern, primary, primaryGlow, secondary, secondaryGlow));
	}
	
	public static Body loadFromXML(StringBuilder log, Element parentElement, Document doc) {
		
		// **************** Core **************** //
		
		Element element = (Element) parentElement.getElementsByTagName("bodyCore").item(0);
		
		String version = element.getAttribute("version");
		
		int importedFemininity = (Integer.valueOf(element.getAttribute("femininity")));
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set femininity: "+Integer.valueOf(element.getAttribute("femininity")));
		
		int importedHeight =(Integer.valueOf(element.getAttribute("height")));
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set height: "+Integer.valueOf(element.getAttribute("height")));
		
		int importedBodySize = (Integer.valueOf(element.getAttribute("bodySize")));
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set body size: "+Integer.valueOf(element.getAttribute("bodySize")));
	
		int importedMuscle = (Integer.valueOf(element.getAttribute("muscle")));
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set muscle: "+Integer.valueOf(element.getAttribute("muscle")));
		
		GenitalArrangement importedGenitalArrangement = GenitalArrangement.NORMAL;
		if(element.getAttribute("genitalArrangement") != null && !element.getAttribute("genitalArrangement").isEmpty()) {
			importedGenitalArrangement = GenitalArrangement.valueOf(element.getAttribute("genitalArrangement"));
		}
		
		BodyMaterial importedBodyMaterial = BodyMaterial.FLESH;
		if(element.getAttribute("bodyMaterial") != null && !element.getAttribute("bodyMaterial").isEmpty()) {
			importedBodyMaterial = BodyMaterial.valueOf(element.getAttribute("bodyMaterial"));
		}
		
		boolean feralBody = false;
		if(element.getAttribute("feral") != null && !element.getAttribute("feral").isEmpty()) {
			feralBody = Boolean.valueOf(element.getAttribute("feral"));
		}
		
		AbstractSubspecies importedSubspeciesOverride = null;
		try {
			if(element.getAttribute("subspeciesOverride") != null && !element.getAttribute("subspeciesOverride").isEmpty()) {
				importedSubspeciesOverride = Subspecies.getSubspeciesFromId(element.getAttribute("subspeciesOverride"));
			}
		} catch(Exception ex) {	
		}

		AbstractSubspecies importedLoadedSubspecies = null;
		try {
			if(element.getAttribute("subspecies") != null && !element.getAttribute("subspecies").isEmpty()) {
				importedLoadedSubspecies = Subspecies.getSubspeciesFromId(element.getAttribute("subspecies"));
			}
		} catch(Exception ex) {	
		}
		
		
		
		// **************** Antenna **************** //
		
		Element antennae = (Element)parentElement.getElementsByTagName("antennae").item(0);
		
		Antenna importedAntenna = new Antenna(AntennaType.getAntennaTypeFromId(antennae.getAttribute("type")), 0);
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Antennae:"+ "<br/>type: "+importedAntenna.getType());
		importedAntenna.setAntennaRows(null, Integer.valueOf(antennae.getAttribute("rows")));
		if(!antennae.getAttribute("length").isEmpty()) {
			importedAntenna.length = Integer.valueOf(antennae.getAttribute("length"));
		}
		if(!antennae.getAttribute("antennaePerRow").isEmpty()) {
			importedAntenna.antennaePerRow = Integer.valueOf(antennae.getAttribute("antennaePerRow"));
		}
		
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/>rows: "+importedAntenna.getAntennaRows());
		
		
		// **************** Arm **************** //
		
		Element arm = (Element)parentElement.getElementsByTagName("arm").item(0);
		
		Arm importedArm = new Arm(ArmType.getArmTypeFromId(arm.getAttribute("type")), Integer.valueOf(arm.getAttribute("rows")));
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Arm:"+ "<br/>type: "+importedArm.getType());

		Main.game.getCharacterUtils().appendToImportLog(log, "<br/>rows: "+importedArm.getArmRows());

		try {
			importedArm.underarmHair = BodyHair.valueOf(arm.getAttribute("underarmHair"));
			Main.game.getCharacterUtils().appendToImportLog(log, "<br/>underarm hair: "+importedArm.getUnderarmHair());
		} catch(IllegalArgumentException e) {
			importedArm.underarmHair = BodyHair.ZERO_NONE;
			Main.game.getCharacterUtils().appendToImportLog(log, "<br/>underarm hair: OLD_VALUE - Set to NONE");
		}
		
		// **************** Ass **************** //
		
		Element ass = (Element)parentElement.getElementsByTagName("ass").item(0);
		Element anus = (Element)parentElement.getElementsByTagName("anus").item(0);
		
		int depth = OrificeDepth.TWO_AVERAGE.getValue();
		String depthAttribute = anus.getAttribute("depth");
		if(!depthAttribute.isEmpty()) {
			depth = Integer.valueOf(depthAttribute);
		}
		
		Ass importedAss = new Ass(AssType.getAssTypeFromId(ass.getAttribute("type")),
				Integer.valueOf(ass.getAttribute("assSize")),
				Integer.valueOf(ass.getAttribute("hipSize")),
				Integer.valueOf(anus.getAttribute("wetness")),
				handleCapacityLoading(Float.valueOf(anus.getAttribute("capacity"))),
				depth,
				Integer.valueOf(anus.getAttribute("elasticity")),
				Integer.valueOf(anus.getAttribute("plasticity")),
				Boolean.valueOf(anus.getAttribute("virgin")));

		importedAss.anus.orificeAnus.stretchedCapacity = handleCapacityLoading(Float.valueOf(anus.getAttribute("stretchedCapacity")));
		importedAss.anus.bleached = (Boolean.valueOf(anus.getAttribute("bleached")));
		try {
			importedAss.anus.assHair = (BodyHair.valueOf(anus.getAttribute("assHair")));
		} catch(IllegalArgumentException e) {
			importedAss.anus.assHair = BodyHair.ZERO_NONE;
			Main.game.getCharacterUtils().appendToImportLog(log, "<br/>ass hair: OLD_VALUE - Set to NONE");
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Ass:"
				+ "<br/>type: "+importedAss.getType()
				+ "<br/>assSize: "+importedAss.getAssSize()
				+ "<br/>hipSize: "+importedAss.getHipSize());
		
		if (anus != null) {
			Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Anus:"
					+ "<br/>wetness: "+importedAss.anus.orificeAnus.wetness
					+ "<br/>depth: "+importedAss.anus.orificeAnus.depth
					+ "<br/>elasticity: "+importedAss.anus.orificeAnus.elasticity
					+ "<br/>elasticity: "+importedAss.anus.orificeAnus.plasticity
					+ "<br/>capacity: "+importedAss.anus.orificeAnus.capacity
					+ "<br/>stretchedCapacity: "+importedAss.anus.orificeAnus.stretchedCapacity
					+ "<br/>virgin: "+importedAss.anus.orificeAnus.virgin
					+ "<br/>bleached: "+importedAss.anus.bleached
					+ "<br/>assHair: "+importedAss.anus.assHair
					+"<br/>Modifiers:");
			if(Main.isVersionOlderThan(version, "0.4.9.7")) {
				Element anusModifiersElement = (Element)anus.getElementsByTagName("anusModifiers").item(0);
				Collection<OrificeModifier> anusModifiers = importedAss.anus.orificeAnus.orificeModifiers;
				anusModifiers.clear();
				if(anusModifiersElement!=null) {
					if(anusModifiersElement.hasAttribute("EXTRA_DEEP")) {
						importedAss.anus.orificeAnus.setDepth(null, depth+2);
					}
					handleLoadingOfModifiers(OrificeModifier.values(), log, anusModifiersElement, anusModifiers);
				}
				
			} else {
				NodeList mods = anus.getElementsByTagName("mod");
				for(int i = 0; i < mods.getLength(); i++) {
					Element e = ((Element)mods.item(i));
					importedAss.anus.orificeAnus.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
				}
			}
		}
		

		// **************** Breasts **************** //
		
		Element breasts = (Element)parentElement.getElementsByTagName("breasts").item(0);
		Element nipples = (Element)parentElement.getElementsByTagName("nipples").item(0);
		
		BreastShape breastShape = BreastShape.ROUND;
		try {
			breastShape = BreastShape.valueOf(breasts.getAttribute("shape"));
		} catch(Exception e) {
		}
		
		int milkStorage = 0;
		try {
			if(!breasts.getAttribute("lactation").isEmpty()) {
				milkStorage = Integer.valueOf(breasts.getAttribute("lactation"));
			} else {
				milkStorage = Integer.valueOf(breasts.getAttribute("milkStorage"));
			}
		} catch(Exception ex) {
		}

		depth = OrificeDepth.TWO_AVERAGE.getValue();
		depthAttribute = nipples.getAttribute("depth");
		if(!depthAttribute.isEmpty()) {
			depth = Integer.valueOf(depthAttribute);
		}
		
		Breast importedBreast = new Breast(BreastType.getBreastTypeFromId(breasts.getAttribute("type")),
				breastShape,
				Integer.valueOf(breasts.getAttribute("size")),
				milkStorage,
				Integer.valueOf(breasts.getAttribute("rows")),
				Integer.valueOf(nipples.getAttribute("nippleSize")),
				NippleShape.valueOf(nipples.getAttribute("nippleShape")),
				Integer.valueOf(nipples.getAttribute("areolaeSize")),
				AreolaeShape.valueOf(nipples.getAttribute("areolaeShape")),
				Integer.valueOf(breasts.getAttribute("nippleCountPerBreast")),
				handleCapacityLoading(Float.valueOf(nipples.getAttribute("capacity"))),
				depth,
				Integer.valueOf(nipples.getAttribute("elasticity")),
				Integer.valueOf(nipples.getAttribute("plasticity")),
				Boolean.valueOf(nipples.getAttribute("virgin")));

		try {
			importedBreast.milkStored = Float.valueOf(breasts.getAttribute("storedMilk"));
			importedBreast.milkRegeneration = Integer.valueOf(breasts.getAttribute("milkRegeneration"));
			if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.1.9")) { // Change from percentage-based to set value:
				importedBreast.milkRegeneration = FluidRegeneration.ONE_AVERAGE.getMedianRegenerationValuePerDay();
			}
		} catch(Exception ex) {
		}
		
		importedBreast.nipples.crotchNipples = false;
		importedBreast.nipples.orificeNipples.stretchedCapacity = handleCapacityLoading(Float.valueOf(nipples.getAttribute("stretchedCapacity")));
		importedBreast.nipples.pierced = (Boolean.valueOf(nipples.getAttribute("pierced")));
//		importedBreast.nipples.areolaeShape = (AreolaeShape.valueOf(nipples.getAttribute("areolaeShape")));
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Breasts:"
				+ "<br/>type: "+importedBreast.getType()
				+ "<br/>size: "+importedBreast.getSize()
				+ "<br/>rows: "+importedBreast.getRows()
				+ "<br/>lactation: "+importedBreast.getRawMilkStorageValue()
				+ "<br/>nippleCountPer: "+importedBreast.getNippleCountPerBreast()
				
				+ "<br/><br/>Nipples:"
				+ "<br/>depth: "+importedBreast.nipples.orificeNipples.getDepth(null)
				+ "<br/>elasticity: "+importedBreast.nipples.orificeNipples.getElasticity()
				+ "<br/>plasticity: "+importedBreast.nipples.orificeNipples.getPlasticity()
				+ "<br/>capacity: "+importedBreast.nipples.orificeNipples.getRawCapacityValue()
				+ "<br/>stretchedCapacity: "+importedBreast.nipples.orificeNipples.getStretchedCapacity()
				+ "<br/>virgin: "+importedBreast.nipples.orificeNipples.isVirgin()
				+ "<br/>pierced: "+importedBreast.nipples.isPierced()
				+ "<br/>nippleSize: "+importedBreast.nipples.getNippleSize()
				+ "<br/>nippleShape: "+importedBreast.nipples.getNippleShape()
				+ "<br/>areolaeSize: "+importedBreast.nipples.getAreolaeSize()
				+ "<br/>areolaeShape: "+importedBreast.nipples.getAreolaeShape()
				+"<br/>Modifiers:");

		if(Main.isVersionOlderThan(version, "0.4.9.7")) {
			Element nippleModifiersElement = (Element)nipples.getElementsByTagName("nippleModifiers").item(0);
			Collection<OrificeModifier> nippleOrificeModifiers = importedBreast.nipples.orificeNipples.orificeModifiers;
			nippleOrificeModifiers.clear();
			if(nippleModifiersElement!=null) {
				if(nippleModifiersElement.hasAttribute("EXTRA_DEEP")) {
					importedBreast.nipples.orificeNipples.setDepth(null, depth+2);
				}
				handleLoadingOfModifiers(OrificeModifier.values(), log, nippleModifiersElement, nippleOrificeModifiers);
			}
	
		} else {
			NodeList mods = nipples.getElementsByTagName("mod");
			for(int i = 0; i < mods.getLength(); i++) {
				Element e = ((Element)mods.item(i));
				importedBreast.nipples.orificeNipples.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
			}
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Milk:");
		
		importedBreast.milk = FluidMilk.loadFromXML("milk", parentElement, doc, importedBreast.getType().getFluidType(), false);
		if(Main.isVersionOlderThan(Main.VERSION_NUMBER, "0.2.5.1")) {
			importedBreast.milk.type = importedBreast.getType().getFluidType();
		}
		
		// **************** Ear **************** //
		
		Element ear = (Element)parentElement.getElementsByTagName("ear").item(0);

		Ear importedEar = new Ear(EarType.getEarTypeFromId(ear.getAttribute("type")));
		
		importedEar.pierced = (Boolean.valueOf(ear.getAttribute("pierced")));
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Ear:"
				+ "<br/>type: "+importedEar.getType()
				+ "<br/>pierced: "+importedEar.isPierced());

		
		
		// **************** Eye **************** //
		
		Element eye = (Element)parentElement.getElementsByTagName("eye").item(0);
		
		String eyeTypeFromSave = eye.getAttribute("type");
		
		Map<String, String> eyeTypeConverterMap = new HashMap<>();
		eyeTypeConverterMap.put("EYE_HUMAN", "HUMAN");
		eyeTypeConverterMap.put("EYE_ANGEL", "ANGEL");
		eyeTypeConverterMap.put("EYE_DEMON_COMMON", "DEMON_COMMON");
		eyeTypeConverterMap.put("EYE_DOG_MORPH", "DOG_MORPH");
		eyeTypeConverterMap.put("EYE_LYCAN", "LYCAN");
		eyeTypeConverterMap.put("EYE_FELINE", "CAT_MORPH");
		eyeTypeConverterMap.put("EYE_SQUIRREL", "SQUIRREL_MORPH");
		eyeTypeConverterMap.put("EYE_HORSE_MORPH", "HORSE_MORPH");
		eyeTypeConverterMap.put("EYE_HARPY", "HARPY");
		eyeTypeConverterMap.put("EYE_SLIME", "SLIME");
		if(eyeTypeConverterMap.containsKey(eyeTypeFromSave)) {
			eyeTypeFromSave = eyeTypeConverterMap.get(eyeTypeFromSave);
		}
		
		Eye importedEye = new Eye(EyeType.getEyeTypeFromId(eyeTypeFromSave));
		
		if(Main.isVersionOlderThan(version, "0.3.9") && importedSubspeciesOverride==Subspecies.HALF_DEMON) { // Fix to taurs spawning with no demon parts at all in v0.3.8.9
			importedEye = new Eye(EyeType.DEMON_COMMON);
		}
		
		importedEye.eyePairs = (Integer.valueOf(eye.getAttribute("eyePairs")));
		importedEye.irisShape = (EyeShape.valueOf(eye.getAttribute("irisShape")));
		importedEye.pupilShape = (EyeShape.valueOf(eye.getAttribute("pupilShape")));
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Eye:"
				+ "<br/>type: "+importedEye.getType()
				+ "<br/>pairs: "+importedEye.getEyePairs()
				+ "<br/>iris shape: "+importedEye.getIrisShape()
				+ "<br/>pupil shape: "+importedEye.getPupilShape());
		
		
		// **************** Face **************** //
	
		Element face = (Element)parentElement.getElementsByTagName("face").item(0);
		Element mouth = (Element)parentElement.getElementsByTagName("mouth").item(0);
		
		boolean oldPantherReplacement = face.getAttribute("type").equals("CAT_MORPH_PANTHER");
		
		Face importedFace = new Face(FaceType.getFaceTypeFromId(face.getAttribute("type")), Integer.valueOf(mouth.getAttribute("lipSize")));
		
		importedFace.piercedNose = (Boolean.valueOf(face.getAttribute("piercedNose")));
		try {
			importedFace.facialHair = (BodyHair.valueOf(face.getAttribute("facialHair")));
		} catch(IllegalArgumentException e) {
			importedFace.facialHair = BodyHair.ZERO_NONE;
			Main.game.getCharacterUtils().appendToImportLog(log, "<br/>facial hair: OLD_VALUE - Set to NONE");
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Face: "
				+ "<br/>type: "+importedFace.getType()
				+ "<br/>piercedNose: "+importedFace.isPiercedNose()
				+ "<br/>facial hair: "+importedFace.getFacialHair()
				
				+ "<br/><br/>Mouth: ");

		depth = OrificeDepth.TWO_AVERAGE.getValue();
		depthAttribute = mouth.getAttribute("depth");
		if(!depthAttribute.isEmpty()) {
			depth = Integer.valueOf(depthAttribute);
		}
		importedFace.mouth.orificeMouth.depth = depth;
		importedFace.mouth.orificeMouth.elasticity = (Integer.valueOf(mouth.getAttribute("elasticity")));
		importedFace.mouth.orificeMouth.plasticity = (Integer.valueOf(mouth.getAttribute("plasticity")));
		importedFace.mouth.orificeMouth.capacity = handleCapacityLoading(Float.valueOf(mouth.getAttribute("capacity")));
		try {
			importedFace.mouth.orificeMouth.wetness = (Integer.valueOf(mouth.getAttribute("wetness")));
		} catch(Exception ex) {
		}
		importedFace.mouth.orificeMouth.stretchedCapacity = handleCapacityLoading(Float.valueOf(mouth.getAttribute("stretchedCapacity")));
		importedFace.mouth.orificeMouth.virgin = (Boolean.valueOf(mouth.getAttribute("virgin")));
		importedFace.mouth.piercedLip = (Boolean.valueOf(mouth.getAttribute("piercedLip")));
		
		Main.game.getCharacterUtils().appendToImportLog(log, 
				"<br/>depth: "+importedFace.mouth.orificeMouth.getDepth(null)
				+ "<br/>elasticity: "+importedFace.mouth.orificeMouth.getElasticity()
				+ "<br/>plasticity: "+importedFace.mouth.orificeMouth.getPlasticity()
				+ "<br/>capacity: "+importedFace.mouth.orificeMouth.getCapacity()
				+ "<br/>stretchedCapacity: "+importedFace.mouth.orificeMouth.getStretchedCapacity()
				+ "<br/>virgin: "+importedFace.mouth.orificeMouth.isVirgin()
				+ "<br/>piercedLip: "+importedFace.mouth.isPiercedLip()
				+ "<br/>lipSize: "+importedFace.mouth.getLipSize()
				+ "<br/>Modifiers: ");

		if(Main.isVersionOlderThan(version, "0.4.9.7")) {
			Element mouthModifiersElement = (Element)mouth.getElementsByTagName("mouthModifiers").item(0);
			Collection<OrificeModifier> mouthOrificeModifiers = importedFace.mouth.orificeMouth.orificeModifiers;
			mouthOrificeModifiers.clear();
			if(mouthModifiersElement!=null) {
				if(mouthModifiersElement.hasAttribute("EXTRA_DEEP")) {
					importedFace.mouth.orificeMouth.setDepth(null, depth+2);
				}
				handleLoadingOfModifiers(OrificeModifier.values(), log, mouthModifiersElement, mouthOrificeModifiers);
			}
			
		} else {
			NodeList mods = mouth.getElementsByTagName("mod");
			for(int i = 0; i < mods.getLength(); i++) {
				Element e = ((Element)mods.item(i));
				importedFace.mouth.orificeMouth.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
			}
		}

		Element tongue = (Element)parentElement.getElementsByTagName("tongue").item(0);
			importedFace.tongue.pierced = (Boolean.valueOf(tongue.getAttribute("piercedTongue")));
			importedFace.tongue.tongueLength = (Integer.valueOf(tongue.getAttribute("tongueLength")));
			
			Main.game.getCharacterUtils().appendToImportLog(log, 
					"<br/><br/>Tongue: "
					+ "<br/>piercedTongue: "+importedFace.tongue.isPierced()
					+ "<br/>tongueLength: "+importedFace.tongue.getTongueLength()
					+ "<br/>Modifiers: ");

			if(Main.isVersionOlderThan(version, "0.4.9.7")) {
				Element tongueModifiersElement = (Element)tongue.getElementsByTagName("tongueModifiers").item(0);
				Collection<TongueModifier> tongueModifiers = importedFace.tongue.tongueModifiers;
				tongueModifiers.clear();
				if(tongueModifiersElement!=null) {
					handleLoadingOfModifiers(TongueModifier.values(), log, tongueModifiersElement, tongueModifiers);
				}
				if(version.isEmpty()) { // Version tracking was added in v0.3.7, so if there is no version, it is before then. Add new default modifiers added in v0.3.7:
					for(TongueModifier mod :importedFace.tongue.getType().getDefaultRacialTongueModifiers()) {
						if(mod==TongueModifier.FLAT || mod==TongueModifier.WIDE || mod==TongueModifier.STRONG) {
							importedFace.tongue.tongueModifiers.add(mod);
						}
					}
				}
				
			} else {
				NodeList mods = tongue.getElementsByTagName("mod");
				for(int i = 0; i < mods.getLength(); i++) {
					Element e = ((Element)mods.item(i));
					importedFace.tongue.tongueModifiers.add(TongueModifier.valueOf(e.getTextContent()));
				}
			}
			
			
		// **************** Hair **************** //
		
		Element hair = (Element)parentElement.getElementsByTagName("hair").item(0);
		String hairTypeFromSave = hair.getAttribute("type");
		
		Map<String, String> hairTypeConverterMap = new HashMap<>();
		hairTypeConverterMap.put("HAIR_HUMAN", "HUMAN");
		hairTypeConverterMap.put("HAIR_ANGEL", "ANGEL");
		hairTypeConverterMap.put("HAIR_DEMON", "DEMON_COMMON");
		hairTypeConverterMap.put("HAIR_CANINE_FUR", "DOG_MORPH");
		hairTypeConverterMap.put("HAIR_LYCAN_FUR", "LYCAN");
		hairTypeConverterMap.put("HAIR_FELINE_FUR", "CAT_MORPH");
		hairTypeConverterMap.put("HAIR_HORSE_HAIR", "HORSE_MORPH");
		hairTypeConverterMap.put("HAIR_SQUIRREL_FUR", "SQUIRREL_MORPH");
		hairTypeConverterMap.put("SLIME", "SLIME");
		hairTypeConverterMap.put("HAIR_HARPY", "HARPY");
		if(hairTypeConverterMap.containsKey(hairTypeFromSave)) {
			hairTypeFromSave = hairTypeConverterMap.get(hairTypeFromSave);
		}
		
		Hair importedHair = new Hair(HairType.getHairTypeFromId(hairTypeFromSave),
				Integer.valueOf(hair.getAttribute("length")),
				HairStyle.valueOf(hair.getAttribute("hairStyle")),
				null);
		
		importedHair.neckFluff = Boolean.valueOf(hair.getAttribute("neckFluff"));
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Hair: "
				+ "<br/>type: "+importedHair.getType()
				+ "<br/>length: "+importedHair.getLength()
				+ "<br/>hairStyle: "+importedHair.getStyle());

		
		// **************** Horn **************** //
		Element horn = (Element)parentElement.getElementsByTagName("horn").item(0);
		
		Horn importedHorn = new Horn(HornType.NONE, 0);
		int rows = (Integer.valueOf(horn.getAttribute("rows")));
		
		String hornType = horn.getAttribute("type");
		if(hornType.equals("DEMON")) {
			hornType = "";
		}
		if(hornType.equals("BOVINE")) {
			hornType = "";
		}
		int length = 0;
		if(!hornType.equals("NONE")) {
			length = HornLength.TWO_LONG.getMedianValue();
		}
		if(!horn.getAttribute("length").isEmpty()) {
			try {
				length = Integer.valueOf(horn.getAttribute("length"));
			} catch(IllegalArgumentException e) {
			}
		}
		int hornsPerRow = 2;
		if(!horn.getAttribute("hornsPerRow").isEmpty()) {
			try {
				hornsPerRow = Integer.valueOf(horn.getAttribute("hornsPerRow"));
			} catch(IllegalArgumentException e) {
			}
		}
		try {
			importedHorn = new Horn(HornType.getHornTypeFromId(hornType), length);
			importedHorn.rows = rows;
			importedHorn.hornsPerRow = hornsPerRow;
			Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Horn: "
					+ "<br/>type: "+importedHorn.getType()
					+ "<br/>length: "+length
					+ "<br/>rows: "+importedHorn.getHornRows()
					+ "<br/>horns per row: "+importedHorn.getHornsPerRow());
			
		} catch(IllegalArgumentException e) {
			if(horn.getAttribute("type").startsWith("DEMON")) {
				importedHorn = new Horn(HornType.SWEPT_BACK, length);
				importedHorn.rows = rows;
				
			} else if(horn.getAttribute("type").startsWith("BOVINE")) {
				importedHorn = new Horn(HornType.CURVED, length);
				importedHorn.rows = rows;
			}
			
			Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Horn: "
					+ "<br/>type NOT FOUND, defaulted to: "+importedHorn.getType()
					+ "<br/>rows: "+importedHorn.getHornRows());
		}
		
		
			
		// **************** Leg **************** //
		
		Element leg = (Element)parentElement.getElementsByTagName("leg").item(0);

		AbstractLegType legType = LegType.getLegTypeFromId(leg.getAttribute("type"));
		
		LegConfiguration configuration = LegConfiguration.BIPEDAL;
		try {
			configuration = LegConfiguration.getValueFromString(leg.getAttribute("configuration"));
		} catch(Exception ex) {}
		
		FootStructure footStructure = legType.getDefaultFootStructure(configuration);
		try {
			footStructure = FootStructure.valueOf(leg.getAttribute("footStructure"));
		} catch(Exception ex) {}
		
		
		Leg importedLeg = new Leg(legType, configuration);
		importedLeg.setFootStructure(null, footStructure);
		try {
			float tailLength = Float.valueOf(leg.getAttribute("tailLength"));
			importedLeg.setLengthAsPercentageOfHeight(null, tailLength);
		} catch(Exception ex) {}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Leg: "
				+ "<br/>type: "+importedLeg.getType());

		
		// **************** Penis **************** //
		
		Element penis = (Element)parentElement.getElementsByTagName("penis").item(0);
		Element testicles = (Element)parentElement.getElementsByTagName("testicles").item(0);
		
		int girth = 2;
		if(penis.getAttribute("girth") != null && !penis.getAttribute("girth").isEmpty()) {
			girth = Integer.valueOf(penis.getAttribute("girth"));
			if(Main.isVersionOlderThan(version, "0.3.7.6")) {// An extra level was added in 0.3.7.6, so +1 if the version is older than that
				girth+=1;
			}
		}
		
		int cumStorage = 0;
		try {
			if(testicles.hasAttribute("cumProduction")) {
				cumStorage = Integer.valueOf(testicles.getAttribute("cumProduction"));
			} else {
				cumStorage = Integer.valueOf(testicles.getAttribute("cumStorage"));
			}
		} catch(Exception ex) {
		}
		
		Penis importedPenis = new Penis(PenisType.getPenisTypeFromId(penis.getAttribute("type")),
				Integer.valueOf(penis.getAttribute("size")),
				false,
				girth,
				Integer.valueOf(testicles.getAttribute("testicleSize")),
				cumStorage,
				Integer.valueOf(testicles.getAttribute("numberOfTesticles")));
		
		importedPenis.pierced = (Boolean.valueOf(penis.getAttribute("pierced")));
		
		if(!penis.getAttribute("virgin").isEmpty()) {
			importedPenis.virgin = (Boolean.valueOf(penis.getAttribute("virgin")));
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Penis: "
				+ "<br/>type: "+importedPenis.getType()
				+ "<br/>size: "+importedPenis.getRawLengthValue()
				+ "<br/>pierced: "+importedPenis.isPierced()
				+ "<br/>Penis Modifiers: ");

		if(Main.isVersionOlderThan(version, "0.4.9.7")) {
			Collection<PenetrationModifier> penisModifiers = importedPenis.penisModifiers;
			penisModifiers.clear();
			Element penisModifiersElement = (Element)penis.getElementsByTagName("penisModifiers").item(0);
			if (penisModifiersElement != null) {
				handleLoadingOfModifiers(PenetrationModifier.values(), log, penisModifiersElement, penisModifiers);
			}
			
		} else {
			NodeList mods = penis.getElementsByTagName("mod");
			for(int i = 0; i < mods.getLength(); i++) {
				Element e = ((Element)mods.item(i));
				importedPenis.addPenisModifier(null, PenetrationModifier.valueOf(e.getTextContent()));
			}
		}
		
		depth = OrificeDepth.TWO_AVERAGE.getValue();
		depthAttribute = penis.getAttribute("depth");
		if(!depthAttribute.isEmpty()) {
			depth = Integer.valueOf(depthAttribute);
		}
		importedPenis.orificeUrethra.depth = depth;
		importedPenis.orificeUrethra.elasticity = (Integer.valueOf(penis.getAttribute("elasticity")));
		importedPenis.orificeUrethra.plasticity = (Integer.valueOf(penis.getAttribute("plasticity")));
		importedPenis.orificeUrethra.capacity = handleCapacityLoading(Float.valueOf(penis.getAttribute("capacity")));
		importedPenis.orificeUrethra.stretchedCapacity = handleCapacityLoading(Float.valueOf(penis.getAttribute("stretchedCapacity")));
		if(!penis.getAttribute("urethraVirgin").isEmpty()) {
			importedPenis.orificeUrethra.virgin = (Boolean.valueOf(penis.getAttribute("urethraVirgin")));
		} else {
			importedPenis.orificeUrethra.virgin = true;
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, 
				"<br/>depth: "+importedPenis.orificeUrethra.getDepth(null)
				+ "<br/>elasticity: "+importedPenis.orificeUrethra.getElasticity()
				+ "<br/>plasticity: "+importedPenis.orificeUrethra.getPlasticity()
				+ "<br/>capacity: "+importedPenis.orificeUrethra.getCapacity()
				+ "<br/>stretchedCapacity: "+importedPenis.orificeUrethra.getStretchedCapacity()
				+ "<br/>virgin: "+importedPenis.orificeUrethra.isVirgin()
				+ "<br/>Urethra Modifiers:");

		if(Main.isVersionOlderThan(version, "0.4.9.7")) {
			Element urethraModifiersElement = (Element)penis.getElementsByTagName("urethraModifiers").item(0);
			Collection<OrificeModifier> urethraOrificeModifiers = importedPenis.orificeUrethra.orificeModifiers;
			urethraOrificeModifiers.clear();
			if (urethraModifiersElement != null) {
				if(urethraModifiersElement.hasAttribute("EXTRA_DEEP")) {
					importedPenis.orificeUrethra.setDepth(null, depth+2);
				}
				handleLoadingOfModifiers(OrificeModifier.values(), log, urethraModifiersElement, urethraOrificeModifiers);
			}
			
		} else {
			NodeList mods = penis.getElementsByTagName("modUrethra");
			for(int i = 0; i < mods.getLength(); i++) {
				Element e = ((Element)mods.item(i));
				importedPenis.orificeUrethra.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
			}
		}
		
		importedPenis.testicle.internal = (Boolean.valueOf(testicles.getAttribute("internal")));
		
		try {
			importedPenis.testicle.cumStored = Float.valueOf(testicles.getAttribute("storedCum"));
			importedPenis.testicle.cumRegeneration = Integer.valueOf(testicles.getAttribute("cumRegeneration"));
			importedPenis.testicle.setCumExpulsion(null, Integer.valueOf(testicles.getAttribute("cumExpulsion")));
			if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.1.9")) { // Change from percentage-based to set value:
				importedPenis.testicle.cumRegeneration = FluidRegeneration.CUM_REGEN_DEFAULT;
			}
		} catch(Exception ex) {
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Testicles: "
				+ "<br/>cumProduction: "+importedPenis.testicle.getCumStorage()
				+ "<br/>numberOfTesticles: "+importedPenis.testicle.getTesticleCount()
				+ "<br/>testicleSize: "+importedPenis.testicle.getTesticleSize()
				+ "<br/>internal: "+importedPenis.testicle.internal);
		
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Cum:");
		
		importedPenis.testicle.cum = FluidCum.loadFromXML("cum", parentElement, doc, importedPenis.getType().getTesticleType().getFluidType());

		
		// **************** Skin **************** //
		
		Element torso;
		if(parentElement.getElementsByTagName("skin").getLength()>0) {
			torso = (Element)parentElement.getElementsByTagName("skin").item(0);
		} else {
			torso = (Element)parentElement.getElementsByTagName("torso").item(0);
		}
		String torsoTypeFromSave = torso.getAttribute("type");
		
		Torso importedTorso = new Torso(TorsoType.getTorsoTypeFromId(torsoTypeFromSave));
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Torso: "
				+ "<br/>type: "+importedTorso.getType());


		// **************** Spinneret **************** //
		
		Element spinneret = (Element)parentElement.getElementsByTagName("spinneret").item(0);

		OrificeSpinneret importedSpinneret = new OrificeSpinneret();
		
		if(spinneret!=null) {
			importedSpinneret.wetness = Integer.valueOf(spinneret.getAttribute("wetness"));
			importedSpinneret.capacity = handleCapacityLoading(Float.valueOf(spinneret.getAttribute("capacity")));
			depth = OrificeDepth.TWO_AVERAGE.getValue();
			depthAttribute = spinneret.getAttribute("depth");
			if(!depthAttribute.isEmpty()) {
				depth = Integer.valueOf(depthAttribute);
			}
			importedSpinneret.depth = depth;
			importedSpinneret.elasticity = Integer.valueOf(spinneret.getAttribute("elasticity"));
			importedSpinneret.plasticity = Integer.valueOf(spinneret.getAttribute("plasticity"));
			importedSpinneret.virgin = Boolean.valueOf(spinneret.getAttribute("virgin"));
			
			importedSpinneret.stretchedCapacity = handleCapacityLoading(Float.valueOf(spinneret.getAttribute("stretchedCapacity")));

			if(Main.isVersionOlderThan(version, "0.4.9.7")) {
				Element spinneretModifiers = (Element)spinneret.getElementsByTagName("spinneretModifiers").item(0);
				Collection<OrificeModifier> spinneretOrificeModifiers = importedSpinneret.orificeModifiers;
				spinneretOrificeModifiers.clear();
				if(spinneretModifiers!=null) {
					handleLoadingOfModifiers(OrificeModifier.values(), log, spinneretModifiers, spinneretOrificeModifiers);
				}
				
			} else {
				NodeList mods = spinneret.getElementsByTagName("mod");
				for(int i = 0; i < mods.getLength(); i++) {
					Element e = ((Element)mods.item(i));
					importedSpinneret.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
				}
			}
		}
		
		// **************** Tail **************** //
		
		Element tail = (Element)parentElement.getElementsByTagName("tail").item(0);
		AbstractTailType tailType = TailType.getTailTypeFromId(tail.getAttribute("type"));
		
		Tail importedTail = new Tail(tailType);
		
		importedTail.tailCount = (Integer.valueOf(tail.getAttribute("count")));
		
		if(tail.getAttribute("girth") != null && !tail.getAttribute("girth").isEmpty()) {
			int tailGirth = Integer.valueOf(tail.getAttribute("girth"));
			if(Main.isVersionOlderThan(version, "0.3.7.6")) {// An extra level was added in 0.3.7.6, so +1 if the version is older than that
				tailGirth+=1;
			}
			importedTail.girth = tailGirth;
		}
		
		if(Main.isVersionOlderThan(version, "0.3.10")) {
			importedTail.lengthAsPercentageOfHeight = tailType.getDefaultLengthAsPercentageOfHeight();
			
		} else {
			if(tail.getAttribute("length") != null && !tail.getAttribute("length").isEmpty()) {
				float tailLength = Float.valueOf(tail.getAttribute("length"));
				importedTail.lengthAsPercentageOfHeight = tailLength;
			}
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Tail: "
				+ "<br/>type: "+importedTail.getType()
				+ "<br/>count: "+importedTail.getTailCount());

		
		// **************** Tentacle **************** //

		Tentacle importedTentacle = new Tentacle(TentacleType.NONE);	
		
		Element tentacle = (Element)parentElement.getElementsByTagName("tentacle").item(0);
		if(tentacle!=null) {
			AbstractTentacleType tentacleType = TentacleType.getTentacleTypeFromId(tentacle.getAttribute("type"));
	
			importedTentacle = new Tentacle(tentacleType);
			
			importedTentacle.tentacleCount = (Integer.valueOf(tentacle.getAttribute("count")));
			
			if(tentacle.getAttribute("girth") != null && !tentacle.getAttribute("girth").isEmpty()) {
				int tentacleGirth = Integer.valueOf(tentacle.getAttribute("girth"));
				importedTentacle.girth = tentacleGirth;
			}
			
			if(tentacle.getAttribute("length")!=null && !tentacle.getAttribute("length").isEmpty()) {
				float tentacleLength = Float.valueOf(tentacle.getAttribute("length"));
				importedTentacle.lengthAsPercentageOfHeight = tentacleLength;
			} else {
				importedTentacle.lengthAsPercentageOfHeight = tentacleType.getDefaultLengthAsPercentageOfHeight();
			}
			
			Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Tentacle: "
					+ "<br/>type: "+importedTentacle.getType()
					+ "<br/>count: "+importedTentacle.getTentacleCount());
		}
		
		
		// **************** Vagina **************** //
		
		Element vagina = (Element)parentElement.getElementsByTagName("vagina").item(0);

		depth = OrificeDepth.TWO_AVERAGE.getValue();
		depthAttribute = vagina.getAttribute("depth");
		if(!depthAttribute.isEmpty()) {
			depth = Integer.valueOf(depthAttribute);
		}
		
		Vagina importedVagina = new Vagina(VaginaType.getVaginaTypeFromId(vagina.getAttribute("type")),
				(vagina.getAttribute("labiaSize").isEmpty()?1:Integer.valueOf(vagina.getAttribute("labiaSize"))),
				Integer.valueOf(vagina.getAttribute("clitSize")),
				vagina.hasAttribute("clitGirth")?Integer.valueOf(vagina.getAttribute("clitGirth")):PenetrationGirth.THREE_AVERAGE.getValue(),
				Integer.valueOf(vagina.getAttribute("wetness")),
				handleCapacityLoading(Float.valueOf(vagina.getAttribute("capacity"))),
				depth,
				Integer.valueOf(vagina.getAttribute("elasticity")),
				Integer.valueOf(vagina.getAttribute("plasticity")),
				Boolean.valueOf(vagina.getAttribute("virgin")));

		if(Main.isVersionOlderThan(version, "0.4.9.7")) {
			try {
				Collection<PenetrationModifier> clitModifiers = importedVagina.clitoris.clitModifiers;
				clitModifiers.clear();
				Element clitModifiersElement = (Element)vagina.getElementsByTagName("clitModifiers").item(0);
				if (clitModifiersElement != null) {
					handleLoadingOfModifiers(PenetrationModifier.values(), log, clitModifiersElement, clitModifiers);
				}
				
			} catch(Exception ex) {
			}
			
		} else {
			NodeList mods = vagina.getElementsByTagName("modClit");
			for(int i = 0; i < mods.getLength(); i++) {
				Element e = ((Element)mods.item(i));
				importedVagina.getClitoris().addClitorisModifier(null, PenetrationModifier.valueOf(e.getTextContent()));
			}
		}
		
		importedVagina.pierced = (Boolean.valueOf(vagina.getAttribute("pierced")));
		
		if(vagina.hasAttribute("eggLayer")) {
			importedVagina.eggLayer = (Boolean.valueOf(vagina.getAttribute("eggLayer")));
		} else if(vagina.getAttribute("type").equals("DEMON_EGGS") || vagina.getAttribute("type").equals("NoStepOnSnek_snake_vagina_e")){ // Removed egg-laying vagina variants in 0.4
			importedVagina.eggLayer = true;
		}
		
		importedVagina.orificeVagina.stretchedCapacity = handleCapacityLoading(Float.valueOf(vagina.getAttribute("stretchedCapacity")));
		try {
			importedVagina.orificeVagina.squirter = (Boolean.valueOf(vagina.getAttribute("squirter")));
		} catch(Exception ex) {
		}
		if(Main.isVersionOlderThan(version, "0.3.6.9")) {
			importedVagina.orificeVagina.hymen = importedVagina.getOrificeVagina().isVirgin();
			
		} else {
			importedVagina.orificeVagina.hymen = Boolean.valueOf(vagina.getAttribute("hymen"));
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Vagina: "
				+ "<br/>type: "+importedVagina.getType()
				+ "<br/>clitSize: "+importedVagina.clitoris.getClitorisSize()
				+ "<br/>pierced: "+importedVagina.isPierced()

				+ "<br/>wetness: "+importedVagina.orificeVagina.wetness
				+ "<br/>depth: "+importedVagina.orificeVagina.getDepth(null)
				+ "<br/>elasticity: "+importedVagina.orificeVagina.getElasticity()
				+ "<br/>plasticity: "+importedVagina.orificeVagina.getPlasticity()
				+ "<br/>capacity: "+importedVagina.orificeVagina.getCapacity()
				+ "<br/>stretchedCapacity: "+importedVagina.orificeVagina.getStretchedCapacity()
				+ "<br/>virgin: "+importedVagina.orificeVagina.isVirgin());

		if(Main.isVersionOlderThan(version, "0.4.9.7")) {
			Element vaginaModifiers = (Element)vagina.getElementsByTagName("vaginaModifiers").item(0);
			Collection<OrificeModifier> vaginaOrificeModifiers = importedVagina.orificeVagina.orificeModifiers;
			vaginaOrificeModifiers.clear();
			if(vaginaModifiers!=null) {
				if(vaginaModifiers.hasAttribute("EXTRA_DEEP")) {
					importedVagina.orificeVagina.setDepth(null, depth+2);
				}
				handleLoadingOfModifiers(OrificeModifier.values(), log, vaginaModifiers, vaginaOrificeModifiers);
			}
			
		} else {
			NodeList mods = vagina.getElementsByTagName("mod");
			for(int i = 0; i < mods.getLength(); i++) {
				Element e = ((Element)mods.item(i));
				importedVagina.orificeVagina.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
			}
		}
		
		try {
			depth = OrificeDepth.TWO_AVERAGE.getValue();
			depthAttribute = vagina.getAttribute("urethraDepth");
			if(!depthAttribute.isEmpty()) {
				depth = Integer.valueOf(depthAttribute);
			}
			importedVagina.orificeUrethra.depth = depth;
			importedVagina.orificeUrethra.elasticity = (Integer.valueOf(vagina.getAttribute("urethraElasticity")));
			importedVagina.orificeUrethra.plasticity = (Integer.valueOf(vagina.getAttribute("urethraPlasticity")));
			importedVagina.orificeUrethra.capacity = handleCapacityLoading(Float.valueOf(vagina.getAttribute("urethraCapacity")));
			importedVagina.orificeUrethra.stretchedCapacity = handleCapacityLoading(Float.valueOf(vagina.getAttribute("urethraStretchedCapacity")));
			if(!vagina.getAttribute("urethraVirgin").isEmpty()) {
				importedVagina.orificeUrethra.virgin = (Boolean.valueOf(vagina.getAttribute("urethraVirgin")));
			} else {
				importedVagina.orificeUrethra.virgin = true;
			}

			if(Main.isVersionOlderThan(version, "0.4.9.7")) {
				Element urethraModifiersElement = (Element)vagina.getElementsByTagName("urethraModifiers").item(0);
				Collection<OrificeModifier> vaginaUrethraOrificeModifiers = importedVagina.orificeUrethra.orificeModifiers;
				vaginaUrethraOrificeModifiers.clear();
				if (urethraModifiersElement != null) {
					if(urethraModifiersElement.hasAttribute("EXTRA_DEEP")) {
						importedVagina.orificeUrethra.setDepth(null, depth+2);
					}
					handleLoadingOfModifiers(OrificeModifier.values(), log, urethraModifiersElement, vaginaUrethraOrificeModifiers);
				}
				
			} else {
				NodeList mods = vagina.getElementsByTagName("modUrethra");
				for(int i = 0; i < mods.getLength(); i++) {
					Element e = ((Element)mods.item(i));
					importedVagina.orificeUrethra.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
				}
			}
			
		} catch(Exception ex) {
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Girlcum:");
		
		importedVagina.girlcum = FluidGirlCum.loadFromXML("girlcum", parentElement, doc, importedVagina.getType().getFluidType());
		
		// **************** Wing **************** //
		
		Element wing = (Element)parentElement.getElementsByTagName("wing").item(0);
		int wingSize = 0;
		if(!wing.getAttribute("size").isEmpty()) {
			wingSize = Integer.valueOf(wing.getAttribute("size"));
		}
		Wing importedWing = new Wing(WingType.getWingTypeFromId(wing.getAttribute("type")), wingSize);
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Wing: "
				+ "<br/>type: "+importedWing.getType()+"<br/>"
				+ "<br/>size: "+importedWing.getSizeValue()+"<br/>");


		// ************** Version Overrides **************//

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.5.1")) {
			importedVagina.girlcum.type = importedVagina.getType().getFluidType();
			importedPenis.testicle.cum.type = importedPenis.getType().getTesticleType().getFluidType();
			importedBreast.milk.type = importedBreast.getType().getFluidType();
		}


		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.8")) {
			// Convert all sizes from inch to cm
			importedHair.length *= 2.54;
			importedHorn.length *= 2.54;
			importedFace.tongue.tongueLength *= 2.54;
			importedPenis.length *= 2.54;
			importedVagina.clitoris.clitSize *= 2.54;

			// Convert all capacities from inch to cm
			importedFace.mouth.orificeMouth.capacity *= 2.54;
			importedFace.mouth.orificeMouth.stretchedCapacity *= 2.54;
			importedPenis.orificeUrethra.capacity *= 2.54;
			importedPenis.orificeUrethra.stretchedCapacity *= 2.54;
			importedVagina.orificeVagina.capacity *= 2.54;
			importedVagina.orificeVagina.stretchedCapacity *= 2.54;
			importedVagina.orificeUrethra.capacity *= 2.54;
			importedVagina.orificeUrethra.stretchedCapacity *= 2.54;
			importedAss.anus.orificeAnus.capacity *= 2.54;
			importedAss.anus.orificeAnus.stretchedCapacity *= 2.54;
			importedBreast.nipples.orificeNipples.capacity *= 2.54;
			importedBreast.nipples.orificeNipples.stretchedCapacity *= 2.54;
		}
		
		if(oldPantherReplacement) {
			if(importedArm.getType().getRace()==Race.CAT_MORPH) {
				importedArm.setType(null, ArmType.getArmTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedAss.getType().getRace()==Race.CAT_MORPH) {
				importedAss.setType(null, AssType.getAssTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedBreast.getType().getRace()==Race.CAT_MORPH) {
				importedBreast.setType(null, BreastType.getBreastTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedFace.getType().getRace()==Race.CAT_MORPH) {
				importedFace.setType(null, FaceType.getFaceTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedEye.getType().getRace()==Race.CAT_MORPH) {
				importedEye.setType(null, EyeType.getEyeTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedEar.getType().getRace()==Race.CAT_MORPH) {
				importedEar.setType(null, EarType.getEarTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedHair.getType().getRace()==Race.CAT_MORPH) {
				importedHair.setType(null, HairType.getHairTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedLeg.getType().getRace()==Race.CAT_MORPH) {
				importedLeg.setType(null, LegType.getLegTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedTorso.getType().getRace()==Race.CAT_MORPH) {
				importedTorso.setType(null, TorsoType.getTorsoTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			
			if(importedVagina.getType().getRace()==Race.CAT_MORPH) {
				importedVagina.setType(null, VaginaType.getVaginaTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedPenis.getType().getRace()==Race.CAT_MORPH) {
				importedPenis.setType(null, PenisType.getPenisTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedTail.getType().getRace()==Race.CAT_MORPH) {
				importedTail.setType(null, TailType.getTailTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
		}
		
		Body body = new Body.BodyBuilder(
				importedArm,
				importedAss,
				importedBreast,
				importedFace,
				importedEye,
				importedEar,
				importedHair,
				importedLeg,
				importedTorso,
				importedBodyMaterial,
				importedGenitalArrangement,
				importedHeight,
				importedFemininity,
				importedBodySize,
				importedMuscle)
						.vagina(importedVagina)
						.penis(importedPenis)
						.horn(importedHorn)
						.antenna(importedAntenna)
						.tail(importedTail)
						.wing(importedWing)
						.spinneret(importedSpinneret)
						.tentacle(importedTentacle)
						.build();


		// **************** Crotch Breasts **************** //

		breasts = (Element)parentElement.getElementsByTagName("breastsCrotch").item(0);
		nipples = (Element)parentElement.getElementsByTagName("nipplesCrotch").item(0);
		BreastCrotch importedCrotchBreast = null;

		if(breasts!=null) {
			breastShape = BreastShape.ROUND;
			try {
				breastShape = BreastShape.valueOf(breasts.getAttribute("shape"));
			} catch(Exception e) {
			}

			milkStorage = 0;
			try {
				if(!breasts.getAttribute("lactation").isEmpty()) {
					milkStorage = Integer.valueOf(breasts.getAttribute("lactation"));
				} else {
					milkStorage = Integer.valueOf(breasts.getAttribute("milkStorage"));
				}
			} catch(Exception ex) {
			}

			AbstractBreastType crotchBoobType = BreastType.getBreastTypeFromId(breasts.getAttribute("type"));
			if(oldPantherReplacement) {
				if(crotchBoobType.getRace()==Race.CAT_MORPH) {
					crotchBoobType = BreastType.getBreastTypes(Race.getRaceFromId("innoxia_panther")).get(0);
				}
			}
			if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.6") && importedLeg.getLegConfiguration().isBipedalPositionedCrotchBoobs()) { // Reset crotch-boob type as I accidentally applied crotch-boobs to demons
				if(body.isFeminine()) {
					crotchBoobType = RacialBody.valueOfRace(body.getRace()).getBreastCrotchType();
				} else {
					crotchBoobType = BreastType.NONE;
				}
			}
			
			depth = OrificeDepth.TWO_AVERAGE.getValue();
			depthAttribute = nipples.getAttribute("depth");
			if(!depthAttribute.isEmpty()) {
				depth = Integer.valueOf(depthAttribute);
			}
			
			importedCrotchBreast = new BreastCrotch(crotchBoobType,
					breastShape,
					Integer.valueOf(breasts.getAttribute("size")),
					milkStorage,
					Integer.valueOf(breasts.getAttribute("rows")),
					Integer.valueOf(nipples.getAttribute("nippleSize")),
					NippleShape.valueOf(nipples.getAttribute("nippleShape")),
					Integer.valueOf(nipples.getAttribute("areolaeSize")),
					AreolaeShape.valueOf(nipples.getAttribute("areolaeShape")),
					Integer.valueOf(breasts.getAttribute("nippleCountPerBreast")),
					Float.valueOf(nipples.getAttribute("capacity")),
					depth,
					Integer.valueOf(nipples.getAttribute("elasticity")),
					Integer.valueOf(nipples.getAttribute("plasticity")),
					Boolean.valueOf(nipples.getAttribute("virgin")));

			try {
				importedCrotchBreast.milkStored = Float.valueOf(breasts.getAttribute("storedMilk"));
				importedCrotchBreast.milkRegeneration = Integer.valueOf(breasts.getAttribute("milkRegeneration"));
				if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.2")) { // Change from percentage-based to set value:
					importedCrotchBreast.milkRegeneration = FluidRegeneration.ONE_AVERAGE.getMedianRegenerationValuePerDay();
				}
			} catch(Exception ex) {
			}

			importedCrotchBreast.nipples.crotchNipples = true;
			importedCrotchBreast.nipples.orificeNipples.stretchedCapacity = (Float.valueOf(nipples.getAttribute("stretchedCapacity")));
			importedCrotchBreast.nipples.pierced = (Boolean.valueOf(nipples.getAttribute("pierced")));
//			importedCrotchBreast.nipples.areolaeShape = (AreolaeShape.valueOf(nipples.getAttribute("areolaeShape")));

			Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Crotch Breasts:"
					+ "<br/>type: "+importedCrotchBreast.getType()
					+ "<br/>size: "+importedCrotchBreast.getSize()
					+ "<br/>rows: "+importedCrotchBreast.getRows()
					+ "<br/>lactation: "+importedCrotchBreast.getRawMilkStorageValue()
					+ "<br/>nippleCountPer: "+importedCrotchBreast.getNippleCountPerBreast()

					+ "<br/><br/>Nipples:"
					+ "<br/>depth: "+importedCrotchBreast.nipples.orificeNipples.getDepth(null)
					+ "<br/>elasticity: "+importedCrotchBreast.nipples.orificeNipples.getElasticity()
					+ "<br/>plasticity: "+importedCrotchBreast.nipples.orificeNipples.getPlasticity()
					+ "<br/>capacity: "+importedCrotchBreast.nipples.orificeNipples.getRawCapacityValue()
					+ "<br/>stretchedCapacity: "+importedCrotchBreast.nipples.orificeNipples.getStretchedCapacity()
					+ "<br/>virgin: "+importedCrotchBreast.nipples.orificeNipples.isVirgin()
					+ "<br/>pierced: "+importedCrotchBreast.nipples.isPierced()
					+ "<br/>nippleSize: "+importedCrotchBreast.nipples.getNippleSize()
					+ "<br/>nippleShape: "+importedCrotchBreast.nipples.getNippleShape()
					+ "<br/>areolaeSize: "+importedCrotchBreast.nipples.getAreolaeSize()
					+ "<br/>areolaeShape: "+importedCrotchBreast.nipples.getAreolaeShape()
					+"<br/>Modifiers:");


			if(Main.isVersionOlderThan(version, "0.4.9.7")) {
				Element nippleModifiersElement = (Element)nipples.getElementsByTagName("nippleModifiers").item(0);
				Collection<OrificeModifier> nippleOrificeModifiers = importedCrotchBreast.nipples.orificeNipples.orificeModifiers;
				nippleOrificeModifiers.clear();
				if (nippleModifiersElement != null) {
					if(nippleModifiersElement.hasAttribute("EXTRA_DEEP")) {
						importedCrotchBreast.nipples.orificeNipples.setDepth(null, depth+2);
					}
					handleLoadingOfModifiers(OrificeModifier.values(), log, nippleModifiersElement, nippleOrificeModifiers);
				}
				
			} else {
				NodeList mods = nipples.getElementsByTagName("mod");
				for(int i = 0; i < mods.getLength(); i++) {
					Element e = ((Element)mods.item(i));
					importedCrotchBreast.nipples.orificeNipples.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
				}
			}

			Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Milk:");

			if(parentElement.getElementsByTagName("milkCrotch").item(0)==null) {
				importedCrotchBreast.milk = FluidMilk.loadFromXML("milk", parentElement, doc, importedCrotchBreast.getType().getFluidType(), true);
			} else {
				importedCrotchBreast.milk = FluidMilk.loadFromXML("milkCrotch", parentElement, doc, importedCrotchBreast.getType().getFluidType(), true);
			}
			if(Main.isVersionOlderThan(Main.VERSION_NUMBER, "0.2.5.1")) {
				importedCrotchBreast.milk.type = importedCrotchBreast.getType().getFluidType();
			}
		}


		if(importedCrotchBreast!=null) {
			if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.8")) {
				importedCrotchBreast.nipples.orificeNipples.capacity *= 2.54;
				importedCrotchBreast.nipples.orificeNipples.stretchedCapacity *= 2.54;
			}

			body.setBreastCrotch(importedCrotchBreast);
		}
		
		body.setSubspeciesOverride(importedSubspeciesOverride);
		body.loadedSubspecies = importedLoadedSubspecies;
		
		body.setPiercedStomach(Boolean.valueOf(element.getAttribute("piercedStomach")));
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set piercedStomach: "+Boolean.valueOf(element.getAttribute("piercedStomach")));
		
		try {
			if(element.getAttribute("takesAfterMother") != null && !element.getAttribute("takesAfterMother").isEmpty()) {
				body.setTakesAfterMother(Boolean.valueOf(element.getAttribute("takesAfterMother")));
			}
		} catch(Exception ex) {	
		}
		
		if(element.getAttribute("pubicHair")!=null && !element.getAttribute("pubicHair").isEmpty()) {
			try {
				body.setPubicHair(BodyHair.valueOf(element.getAttribute("pubicHair")));
				Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set pubicHair: "+body.getPubicHair());
			} catch(IllegalArgumentException e) {
				body.pubicHair = BodyHair.ZERO_NONE;
				Main.game.getCharacterUtils().appendToImportLog(log, "<br/>pubic hair: OLD_VALUE - Set to NONE");
			}
		}
		
		
		NodeList bodyCoverings = element.getElementsByTagName("bodyCovering");
		for(int i = 0; i < bodyCoverings.getLength(); i++){
			Element e = ((Element)bodyCoverings.item(i));

			String type = e.getAttribute("type");
			if(type.equals("HORN_COW") || type.equals("HORN_DEMON")) {
				type = "HORN";
			}
			try {
				String colourPrimary = e.getAttribute("colourPrimary");
				if(colourPrimary.isEmpty()) {
					colourPrimary = e.getAttribute("c1");
				}
				String colourSecondary = e.getAttribute("colourSecondary");
				if(colourSecondary.isEmpty()) {
					colourSecondary = e.getAttribute("c2");
				}
				if(colourSecondary.isEmpty()) {
					colourSecondary = colourPrimary; // If secondary colour is missing, then it's the same as the primary
				}
				
				if(type.startsWith("HAIR_")) {
					if(colourPrimary.equals("COVERING_TAN")) {
						colourPrimary = "COVERING_DIRTY_BLONDE";
					}
					if(colourSecondary.equals("COVERING_TAN")) {
						colourSecondary = "COVERING_DIRTY_BLONDE";
					}
				}

				if(type.startsWith("EYE_")) {
					if(colourPrimary.equals("COVERING_BLUE")) {
						colourPrimary = "COVERING_BLUE_LIGHT";
					}
					if(colourSecondary.equals("COVERING_BLUE")) {
						colourSecondary = "COVERING_BLUE_LIGHT";
					}
				}

				AbstractBodyCoveringType coveringType = BodyCoveringType.getBodyCoveringTypeFromId(type);
				CoveringPattern loadedPattern = CoveringPattern.valueOf(e.getAttribute("pattern"));
				if(!coveringType.getAllPatterns().containsKey(loadedPattern)) {
					loadedPattern = Util.getRandomObjectFromWeightedMap(coveringType.getNaturalPatterns());
				}
				if(e.getAttribute("modifier").isEmpty()) {
					body.setBodyCoveringForXMLImport(coveringType,
							loadedPattern,
							PresetColour.getColourFromId(colourPrimary),
							!e.getAttribute("g1").isEmpty()?Boolean.valueOf(e.getAttribute("g1")):false,
							PresetColour.getColourFromId(colourSecondary),
							!e.getAttribute("g2").isEmpty()?Boolean.valueOf(e.getAttribute("g2")):false);
					
				} else { //TODO
					CoveringModifier modifier = CoveringModifier.valueOf(e.getAttribute("modifier"));
					
					body.setBodyCoveringForXMLImport(coveringType,
							loadedPattern,
							coveringType.getNaturalModifiers().contains(modifier) || coveringType.getExtraModifiers().contains(modifier) ? modifier : coveringType.getNaturalModifiers().get(0),
							PresetColour.getColourFromId(colourPrimary),
							!e.getAttribute("g1").isEmpty()?Boolean.valueOf(e.getAttribute("g1")):false,
							PresetColour.getColourFromId(colourSecondary),
							!e.getAttribute("g2").isEmpty()?Boolean.valueOf(e.getAttribute("g2")):false);
				}
				
				if(oldPantherReplacement && coveringType==BodyCoveringType.FELINE_FUR) {
					Covering felineCovering = body.getCovering(coveringType, false);
					body.setBodyCoveringForXMLImport(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_panther_fur"),
							felineCovering.getPattern(),
							felineCovering.getModifier(),
							felineCovering.getPrimaryColour(),
							felineCovering.isPrimaryGlowing(),
							felineCovering.getSecondaryColour(),
							felineCovering.isSecondaryGlowing());
					body.addBodyCoveringTypesDiscovered(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_panther_fur"));
				}
				
//				if(!e.getAttribute("discovered").isEmpty() && Boolean.valueOf(e.getAttribute("discovered"))) {
					body.addBodyCoveringTypesDiscovered(BodyCoveringType.getBodyCoveringTypeFromId(type));
//				}
				
				Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set bodyCovering: "+colourPrimary+", "+colourSecondary);
			} catch(Exception ex) {
			}
		}
		
		
		try {
			Element heavyMakeupElement = (Element)element.getElementsByTagName("heavyMakeup").item(0);
			
			NodeList bodyTypes = heavyMakeupElement.getElementsByTagName("type");
			for(int i = 0; i < bodyTypes.getLength(); i++){
				Element e = ((Element)bodyTypes.item(i));
				body.addHeavyMakeup(BodyCoveringType.getBodyCoveringTypeFromId(e.getTextContent()));
			}
		} catch(Exception ex) {	
		}
		
		body.feral = feralBody;
		
		body.addDiscoveredBodyCoveringsFromMaterial(importedBodyMaterial);
		
		body.calculateRace(null);
		
		// Converting harpy bald eagles to raptor bald eagles:
		if(Main.isVersionOlderThan(version, "0.4.2.5")) {
			if(body.getRace()==Race.HARPY) {
				AbstractBodyCoveringType feathers = body.getBodyMaterial()==BodyMaterial.SLIME?BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.MAIN_FEATHER):BodyCoveringType.FEATHERS;
				AbstractBodyCoveringType headFeathers = body.getBodyMaterial()==BodyMaterial.SLIME?BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.HAIR):BodyCoveringType.HAIR_HARPY;
				
				if(body.getCoverings().get(feathers).getPrimaryColour()==PresetColour.COVERING_BROWN_DARK && body.getCoverings().get(headFeathers).getPrimaryColour()==PresetColour.COVERING_WHITE) {

					if(body.getArmType().getRace()==Race.HARPY) {
						body.setArmType(null, ArmType.getArmTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getAssType().getRace()==Race.HARPY) {
						body.setAssType(null, AssType.getAssTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getBreastType().getRace()==Race.HARPY) {
						body.setBreastType(null, BreastType.getBreastTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getBreastCrotchType().getRace()==Race.HARPY) {
						body.setBreastCrotchType(null, BreastType.getBreastTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getFaceType().getRace()==Race.HARPY) {
						body.setFaceType(null, FaceType.getFaceTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getEyeType().getRace()==Race.HARPY) {
						body.setEyeType(null, EyeType.getEyeTypeFromId("innoxia_raptor_eye"));
					}
					if(body.getEarType().getRace()==Race.HARPY) {
						body.setEarType(null, EarType.getEarTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getHairType().getRace()==Race.HARPY) {
						body.setHairType(null, HairType.getHairTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getLegType().getRace()==Race.HARPY) {
						body.setLegType(null, LegType.getLegTypeFromId("innoxia_raptor_leg_large"));
					}
					if(body.getTorsoType().getRace()==Race.HARPY) {
						body.setTorsoType(null, TorsoType.getTorsoTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					
					if(body.getVaginaType().getRace()==Race.HARPY) {
						body.setVaginaType(null, VaginaType.getVaginaTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getPenisType().getRace()==Race.HARPY) {
						body.setPenisType(null, PenisType.getPenisTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getTailType().getRace()==Race.HARPY) {
						body.setTailType(null, TailType.getTailTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					
					// coverings:
					coveringCopyConversion(body, BodyCoveringType.FEATHERS, BodyCoveringType.getBodyCoveringTypeFromId("innoxia_raptor_feathers"));
					coveringCopyConversion(body, BodyCoveringType.HARPY_SKIN, BodyCoveringType.getBodyCoveringTypeFromId("innoxia_raptor_skin"));
					coveringCopyConversion(body, BodyCoveringType.BODY_HAIR_HARPY, BodyCoveringType.getBodyCoveringTypeFromId("innoxia_raptor_body_hair"));
					coveringCopyConversion(body, BodyCoveringType.EYE_HARPY, BodyCoveringType.getBodyCoveringTypeFromId("innoxia_raptor_eye"));
					coveringCopyConversion(body, BodyCoveringType.HAIR_HARPY, BodyCoveringType.getBodyCoveringTypeFromId("innoxia_raptor_hair"));
				}
			}
		}
		
		if(Main.isVersionOlderThan(version, "0.3.0.5")) {
			body.updateNippleCrotchColouring();
		}
		
		return body;
	}
	
	private static void coveringCopyConversion(Body body, AbstractBodyCoveringType coveringTypeToCopy, AbstractBodyCoveringType newCoveringType) {
		Covering covering = body.getCovering(coveringTypeToCopy, false);
		body.setBodyCoveringForXMLImport(newCoveringType,
				covering.getPattern(),
				covering.getModifier(),
				covering.getPrimaryColour(),
				covering.isPrimaryGlowing(),
				covering.getSecondaryColour(),
				covering.isSecondaryGlowing());
		body.addBodyCoveringTypesDiscovered(newCoveringType);
	}

	static <T extends Enum<T>> void handleLoadingOfModifiers(T[] enumValues, StringBuilder log, Element modifiersElement, Collection<T> modifiers) {
		for(T enumValue : enumValues) {
			String attributeValue = modifiersElement.getAttribute(enumValue.toString());
			if(!attributeValue.isEmpty()) {
				if(Boolean.valueOf(attributeValue)) {
					if(!modifiers.contains(enumValue)) {
						modifiers.add(enumValue);
					}
					if(log!=null) {
						Main.game.getCharacterUtils().appendToImportLog(log, "<br/>"+enumValue.toString()+":true");
					}
				} else if (!attributeValue.isEmpty()) {
					modifiers.remove(enumValue);
					if(log!=null) {
						Main.game.getCharacterUtils().appendToImportLog(log, "<br/>"+enumValue.toString()+":false");
					}
				} else {
					if(log!=null) {
						Main.game.getCharacterUtils().appendToImportLog(log, "<br/>"+enumValue.toString()+":not present, defaulted to "+modifiers.contains(enumValue));
					}
				}
			}
		}
	}
	
	private static float handleCapacityLoading(float input) {
		float loadedCapacity = input;
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6.7")) {
			loadedCapacity = Penis.getGenericDiameter((int)loadedCapacity, PenetrationGirth.THREE_AVERAGE);
		}
		return loadedCapacity;
	}
	
	public List<BodyPartInterface> getAllBodyParts() {
		return allBodyParts;
	}
	
	public List<BodyPartInterface> getAllBodyPartsWithAllOrifices() {
		return allBodyPartsExtended;
	}
	
	private String getHeader(String header) {
		return "<p style='padding-top:0; margin-top:0;'>[style.colourDisabled("+header+":)]<br/>";
	}
	
	/**
	 * @param owner
	 * @return
	 */
	public String getDescription(GameCharacter owner) {
		StringBuilder sb = new StringBuilder();
		boolean observant = Main.game.getPlayer().hasTrait(Perk.OBSERVANT, true);
		// Describe race:
		sb.append(getHeader("Overview"));
		String colouredHeightValue = "<span style='color:"+this.getHeight().getColour().toWebHexString()+";'>[npc.heightValue]</span>";
		
		String heightDescription = " Standing at full height, [npc.she] [npc.verb(measure)] "+colouredHeightValue;
		if(owner.isFeral() && !owner.getFeralAttributes().isSizeHeight()) {
			if(owner.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
				heightDescription = " [npc.Her] body measures "+colouredHeightValue+", which combined with [npc.her] [npc.tailLength]-long tail, gives [npc.herHim] a total length of "
						+ "<span style='color:"+Height.getHeightFromInt(owner.getHeightValue()+owner.getLegTailLength(false)).getColour().toWebHexString()+";'>"
							+ Units.size(owner.getHeightValue()+owner.getLegTailLength(false), Units.ValueType.NUMERIC, Units.UnitType.LONG)
						+"</span>";
				
//				heightDescription = " From head to tail,  [npc.she] [npc.verb(measure)] "
//						+Units.size(owner.getHeightValue()+owner.getLegTailLength(false), Units.ValueType.NUMERIC, Units.UnitType.LONG);
			} else {
				heightDescription = " From head to tail,  [npc.she] [npc.verb(measure)] "+colouredHeightValue;
			}
		}
		
		if (owner.isPlayer()) {
			sb.append("You are [pc.name], "
							+(owner.getRace()==Race.HUMAN
								?"<span style='color:"+owner.getFemininity().getColour().toWebHexString()+";'>[pc.a_femininity]</span> [pc.gender(true)] [style.colourHuman(human)]. "
								:"[pc.a_fullRace(true)] [pc.gender(true)]. ")
						+ owner.getAppearsAsGenderDescription(true)
						+heightDescription+".");
		} else {
			if(owner.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer()) && owner.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer())) {
				sb.append("[npc.Name] is "
							+(owner.getRace()==Race.HUMAN
								?"<span style='color:"+owner.getFemininity().getColour().toWebHexString()+";'>[npc.a_femininity]</span> [npc.gender(true)] [style.colourHuman(human)]. "
								:"[npc.a_fullRace(true)] [npc.gender(true)]. ")
						+ owner.getAppearsAsGenderDescription(true)
						+ heightDescription);
			} else {
				if(observant) {
					sb.append("Thanks to your observant perk, you can detect that [npc.name] is <span style='color:"+getGender().getColour().toWebHexString()+";'>[npc.a_gender]</span> [npc.raceStage] [npc.race]. "
							+ owner.getAppearsAsGenderDescription(true)
							+ heightDescription);
				} else {
					sb.append("[npc.Name] is a [npc.a_fullRace(true)]. "
								+ owner.getAppearsAsGenderDescription(true)
								+ heightDescription);
				}
			}
			if(owner.isSizeDifferenceTallerThan(Main.game.getPlayer())) {
				String descriptor = owner.isFeral() && !owner.getFeralAttributes().isSizeHeight()?"longer":"taller";
				sb.append(", making [npc.herHim] <span style='color:"+PresetColour.BODY_SIZE_FOUR.toWebHexString()+";'>significantly "+descriptor+"</span> than you.");
			} else if(owner.isSizeDifferenceShorterThan(Main.game.getPlayer())) {
				sb.append(", making [npc.herHim] <span style='color:"+PresetColour.BODY_SIZE_ZERO.toWebHexString()+";'>significantly shorter</span> than you.");
			} else {
				sb.append(".");
			}
		}
		
		switch(owner.getLegConfiguration()) {
			case ARACHNID:
				if(owner.isFeral()) {
					sb.append(" [style.colourFeral([npc.Her] entire body has transformed into that of a feral [npc.legRace]."
							+ " [npc.Her] genitals are located on the underside of [npc.her] feral [npc.assRace]'s body, while [npc.her] asshole is located near to the end of [npc.her] feral abdomen.)]");
				} else {
					sb.append(" [style.colourFeral([npc.Her] entire lower body, from the waist down, has transformed into that of a huge [npc.legRace]."
							+ " [npc.Her] legs and genitals are completely feral in nature, and are extremely similar to that of [npc.a_assRace]'s."
							+ " [npc.Her] genitals are located on the underside of [npc.her] feral [npc.assRace]'s body, while [npc.her] asshole is located near to the end of [npc.her] feral abdomen.)]");
				}
				break;
			case AVIAN:
				if(owner.isFeral()) {
					sb.append(" [style.colourFeral([npc.Her] entire body has transformed into that of a feral [npc.legRace]."
							+ " [npc.Her] genitals are located towards the rear of the underside of [npc.her] feral [npc.assRace]'s body, and are located within a cloaca.)]");
				} else {
					sb.append(" [style.colourFeral([npc.Her] entire lower body, from the waist down, has transformed into that of a huge [npc.legRace]."
							+ " [npc.Her] legs and genitals are completely feral in nature, and are extremely similar to that of [npc.a_assRace]'s."
							+ " [npc.Her] genitals are located towards the rear of the underside of [npc.her] feral [npc.assRace]'s body, and are located within a cloaca.)]");
				}
				break;
			case CEPHALOPOD:
				if(owner.isFeral()) {
					sb.append(" [style.colourFeral([npc.Her] entire body has transformed into that of a feral [npc.legRace]."
							+ " [npc.Her] genitals and asshole are located within a cloaca on the underside of [npc.her] feral body.)]");
				} else {
					sb.append(" [style.colourFeral([npc.Her] entire lower body, from the waist down, has transformed into that of a huge [npc.legRace]."
							+ " [npc.Her] legs and genitals are almost completely feral in nature, and are extremely similar to that of [npc.a_assRace]'s."
							+ " [npc.Her] genitals and asshole are located within a cloaca on the underside of [npc.her] feral body.)]");
				}
				break;
			case BIPEDAL:
			case WINGED_BIPED:
				break;
			case TAIL:
				if(owner.isFeral()) {
					sb.append(" [style.colourFeral([npc.Her] entire body has transformed into that of a feral [npc.legRace]."
							+ " [npc.Her] ass and genitals have shifted to be located within a cloaca on the underside of [npc.her] feral body.)]");
				} else {
					sb.append(" [style.colourFeral([npc.Her] entire lower body, from the waist down, has transformed into the tail of [npc.a_legRace]."
							+ " [npc.Her] ass and genitals, while retaining anthropomorphic qualities, have shifted to be located within a front-facing cloaca.)]");
	
					if(owner.hasStatusEffect(StatusEffect.AQUATIC_NEGATIVE)) {
						sb.append(" As there is no nearby body of water, [npc.her] tail has automatically [style.colourTan(transformed into a pair of legs)], thereby enabling [npc.herHim] to easily traverse over land.");
					}
				}
				break;
			case TAIL_LONG:
				if(owner.isFeral()) {
					sb.append(" [style.colourFeral([npc.Her] entire body has transformed into that of a feral [npc.legRace]."
							+ " [npc.Her] ass and genitals have shifted to be located within a cloaca on the underside of [npc.her] feral body.)]");
				} else {
					sb.append(" [style.colourFeral([npc.Her] entire lower body, from the waist down, has transformed into the long tail of [npc.a_legRace],"
								+ " which measures "+Units.size(owner.getLegTailLength(false))+" in length."
							+ " [npc.Her] ass and genitals are completely feral in nature, and, in a manner identical to that of [npc.a_assRace], have shifted to be located within a front-facing cloaca.)]");
				}
				break;
			case QUADRUPEDAL:
				if(owner.isFeral()) {
					sb.append(" [style.colourFeral([npc.Her] entire body has transformed into that of a feral [npc.legRace]."
							+ " [npc.Her] genitals and asshole are located at the rear-end of [npc.her] feral body.)]");
				} else {
					sb.append(" [style.colourFeral([npc.Her] entire lower body, from the waist down, has transformed into that of [npc.a_legRace]."
							+ " [npc.Her] legs, tail, ass, and genitals are completely feral in nature, and are identical to those of [npc.a_assRace]'s."
							+ " [npc.Her] genitals and asshole are located at the rear-end of [npc.her] feral body, in the same location as that of a regular [npc.a_assRace]'s.)]");
				}
				break;
		}
		
		if(Main.getProperties().hasValue(PropertyValue.ageContent)) {
			sb.append(" [npc.She] [npc.verb(appear)] to be "
					+(owner.getAppearsAsAge()==AgeCategory.SIXTIES_PLUS
						?""
						:"in [npc.her] ")+
					"<span style='color:"+owner.getAppearsAsAge().getColour().toWebHexString()+";'>"+owner.getAppearsAsAge().getName()+"</span>.");
		}
		sb.append("</p>");
		
		switch(this.getBodyMaterial()) {
			case FLESH:
				break;
			case SLIME:
				sb.append("<p>"
							+ "[npc.NamePos] entire body, save for a small, glowing sphere in the place where [npc.her] heart should be, is made out of [npc.skinFullDescription(true)]!"
							+ " [npc.She] [npc.do]n't need to have any parts of [npc.her] body pierced in order to equip jewellery, as [npc.she] can freely morph [npc.her] body at will!"
						+ "</p>");
				break;
			case SILICONE:
			case AIR:
			case ARCANE:
			case STONE:
			case RUBBER:
			case ICE:
			case WATER:
			case FIRE:
				sb.append("<p>"
							+ "[npc.NamePos] entire body, save for a small obsidian sphere in the place where [npc.her] heart should be, is made out of"
								+ " <b style='color:"+this.getBodyMaterial().getColour().toWebHexString()+";'>"+this.getBodyMaterial().getName()+"</b>!"
						+ "</p>");
				break;
		}
		
		// Describe face (ears, eyes & horns):
		// Femininity:
		sb.append(getHeader("Face"));
		sb.append(face.getType().getBodyDescription(owner));
		if(owner.getBlusher().getPrimaryColour()!=PresetColour.COVERING_NONE) {
			sb.append(" [npc.SheIsFull] wearing "+owner.getBlusher().getColourDescriptor(owner, true, false)+" blusher.");
		}
		
		// Hair:
		if (hair.getRawLengthValue() == 0) {
			if(face.isBaldnessNatural() || owner.isFeral()) {
				sb.append(" [npc.Her] head is [npc.materialDescriptor] [npc.faceFullDescription(true)].");
			} else {
				sb.append(" [npc.SheHasFull] no hair on [npc.her] head, revealing the [npc.faceSkin] that covers [npc.her] scalp.");
			}
			
		} else {
			sb.append(" "+hair.getType().getBodyDescription(owner).trim());
			
			if(hair.getType().getTags().contains(BodyPartTag.HAIR_NATURAL_MANE) && owner.getFaceType().getRace()==hair.getType().getRace()) {
				sb.append(", which forms a mane running down the back of [npc.her] neck and which "); // If hair and face races match, the mane is fully formed
			} else {
				sb.append(", which ");
			}
			
			switch (hair.getStyle()) {
				case NONE:
					sb.append((hair.getType().isDefaultPlural(owner)?"are":"is")+" unstyled.");
					break;
				case BRAIDED:
					sb.append((hair.getType().isDefaultPlural(owner)?"have":"has")+" been woven into a long braid.");
					break;
				case CURLY:
					sb.append((hair.getType().isDefaultPlural(owner)?"have":"has")+" been curled and left loose.");
					break;
				case LOOSE:
					sb.append((hair.getType().isDefaultPlural(owner)?"are":"is")+" left loose and unstyled.");
					break;
				case PONYTAIL:
					sb.append((hair.getType().isDefaultPlural(owner)?"have":"has")+" been styled into a ponytail.");
					break;
				case STRAIGHT:
					sb.append((hair.getType().isDefaultPlural(owner)?"have":"has")+" been straightened and left loose.");
					break;
				case TWIN_TAILS:
					sb.append((hair.getType().isDefaultPlural(owner)?"have":"has")+" been styled into twin tails.");
					break;
				case WAVY:
					sb.append((hair.getType().isDefaultPlural(owner)?"have":"has")+" been styled into waves and left loose.");
					break;
				case MOHAWK:
					sb.append((hair.getType().isDefaultPlural(owner)?"have":"has")+" been styled into a mohawk.");
					break;
				case AFRO:
					sb.append((hair.getType().isDefaultPlural(owner)?"have":"has")+" been styled into an afro.");
					break;
				case SIDECUT:
					sb.append((hair.getType().isDefaultPlural(owner)?"have":"has")+" been styled into a sidecut.");
					break;
				case BOB_CUT:
					sb.append((hair.getType().isDefaultPlural(owner)?"have":"has")+" been styled into a bob cut.");
					break;
				case PIXIE:
					sb.append((hair.getType().isDefaultPlural(owner)?"have":"has")+" been styled into a pixie-cut.");
					break;
				case SLICKED_BACK:
					sb.append((hair.getType().isDefaultPlural(owner)?"have":"has")+" been slicked back.");
					break;
				case MESSY:
					sb.append((hair.getType().isDefaultPlural(owner)?"are":"is")+" unstyled and very messy.");
					break;
				case HIME_CUT:
					sb.append((hair.getType().isDefaultPlural(owner)?"have":"has")+" been straightened and styled into a hime cut.");
					break;
				case CHONMAGE:
					sb.append((hair.getType().isDefaultPlural(owner)?"have":"has")+" been straightened, oiled and styled into a chonmage topknot.");
					break;
				case TOPKNOT:
					sb.append((hair.getType().isDefaultPlural(owner)?"have":"has")+" been styled into a topknot.");
					break;
				case DREADLOCKS:
					sb.append((hair.getType().isDefaultPlural(owner)?"have":"has")+" been styled into dreadlocks.");
					break;
				case BIRD_CAGE:
					sb.append((hair.getType().isDefaultPlural(owner)?"have":"has")+" been styled into an elaborate bird cage"+UtilText.returnStringAtRandom(".",", birds not included."));
					break;
				case TWIN_BRAIDS:
					sb.append((hair.getType().isDefaultPlural(owner)?"have":"has")+" been woven into long twin braids.");
					break;
				case DRILLS:
					sb.append((hair.getType().isDefaultPlural(owner)?"have":"has")+" been styled into ojou ringlets.");
					break;
				case LOW_PONYTAIL:
					sb.append((hair.getType().isDefaultPlural(owner)?"have":"has")+" been styled into a low ponytail.");
					break;
				case CROWN_BRAID:
					sb.append((hair.getType().isDefaultPlural(owner)?"have":"has")+" been woven into a "+UtilText.returnStringAtRandom("crown of braids.","braided crown."));
					break;
				case BUN:
					sb.append((hair.getType().isDefaultPlural(owner)?"have":"has")+" been styled into a bun.");
					break;
				case CHIGNON:
					sb.append((hair.getType().isDefaultPlural(owner)?"have":"has")+" been tied up into a chignon.");
					break;
				case SIDE_BRAIDS:
					sb.append((hair.getType().isDefaultPlural(owner)?"have":"has")+" been woven into braids that hang down on either side of [npc.her] face.");
					break;
			}
		}
		if (hair.isNeckFluff()) {
			sb.append(" A large amount of [npc.hair(true)] "+(hair.getType().isDefaultPlural(owner)?"have":"has")+" grown around [npc.her] neck and upper chest.");
		}
		
		// Horns:
		
		if(owner.hasHorns()) {
			sb.append(" "+owner.getHornType().getBodyDescription(owner));
		}
		
		// Antenna:

		if(owner.hasAntennae()) {
			sb.append(" "+owner.getAntennaType().getBodyDescription(owner));
		}
		
		// Nose:
		
		if(face.isPiercedNose()) {
			sb.append(" [npc.Her] [npc.nose] has been pierced.");
		}
		
		// Eyes:

		if(owner.isAreaKnownByCharacter(CoverableArea.EYES, Main.game.getPlayer())) {
			sb.append(getEyeDescription(owner));
		} else {
			sb.append(" [style.colourDisabled(You haven't seen [npc.her] eyes before, so you don't know what they look like.)]");
		}
		
		// Ear:
		if(owner.isFeral()) {
			sb.append(" [npc.She] [npc.has] a pair of #IF(npc.isPiercedEar()) pierced,#ENDIF [npc.earRace] ears, which are [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].");
			
		} else {
			sb.append(" "+ear.getType().getBodyDescription(owner));
			
			if(Main.game.isFacialHairEnabled()) {
				if(owner.getFacialHairType().getType()==BodyCoveringType.BODY_HAIR_SCALES_ALLIGATOR) {
					switch(owner.getFacialHair()) {
						case ZERO_NONE:
							if(!owner.isFeminine()) {
								sb.append(" [npc.She] [npc.do]n't have any trace of rough, stubbly "+owner.getFacialHairType().getName(owner)+".");
							}
							break;
						case ONE_STUBBLE:
							sb.append(" [npc.SheHasFull] a stubbly patch of "+owner.getFacialHairType().getFullDescription(owner, true)+" growing on [npc.her] [npc.face].");
							break;
						case TWO_MANICURED:
							sb.append(" [npc.SheHasFull] a small, rough patch of "+owner.getFacialHairType().getFullDescription(owner, true)+" growing on [npc.her] [npc.face], which resembles a beard.");
							break;
						case THREE_TRIMMED:
							sb.append(" [npc.SheHasFull] a rough patch of "+owner.getFacialHairType().getFullDescription(owner, true)+" growing on [npc.her] [npc.face], which resembles a beard.");
							break;
						case FOUR_NATURAL:
							sb.append(" [npc.SheHasFull] a natural-looking rough patch of "+owner.getFacialHairType().getFullDescription(owner, true)+" growing on [npc.her] [npc.face], which resembles a beard.");
							break;
						case FIVE_UNKEMPT:
							sb.append(" [npc.SheHasFull] an unkempt, rough patch of "+owner.getFacialHairType().getFullDescription(owner, true)+" growing on [npc.her] [npc.face], which resembles a beard.");
							break;
						case SIX_BUSHY:
							sb.append(" [npc.SheHasFull] a thick, rough patch of "+owner.getFacialHairType().getFullDescription(owner, true)+" growing on [npc.her] [npc.face], which resembles a beard.");
							break;
						case SEVEN_WILD:
							sb.append(" [npc.SheHasFull] a wild, rough patch of "+owner.getFacialHairType().getFullDescription(owner, true)+" growing on [npc.her] [npc.face], which resembles a beard.");
							break;
					}
				} else {
					switch(owner.getFacialHair()) {
						case ZERO_NONE:
							if(!owner.isFeminine()) {
								sb.append(" [npc.She] [npc.do]n't have any trace of facial "+owner.getFacialHairType().getName(owner)+".");
							}
							break;
						case ONE_STUBBLE:
							sb.append(" [npc.SheHasFull] a stubbly patch of "+owner.getFacialHairType().getFullDescription(owner, true)+" growing on [npc.her] [npc.face].");
							break;
						case TWO_MANICURED:
							sb.append(" [npc.SheHasFull] a small amount of "+owner.getFacialHairType().getFullDescription(owner, true)+" growing on [npc.her] [npc.face].");
							break;
						case THREE_TRIMMED:
							sb.append(" [npc.SheHasFull] a well-trimmed beard of "+owner.getFacialHairType().getFullDescription(owner, true)+" growing on [npc.her] [npc.face].");
							break;
						case FOUR_NATURAL:
							sb.append(" [npc.SheHasFull] a natural-looking beard of "+owner.getFacialHairType().getFullDescription(owner, true)+" growing on [npc.her] [npc.face].");
							break;
						case FIVE_UNKEMPT:
							sb.append(" [npc.SheHasFull] an unkempt, bushy beard of "+owner.getFacialHairType().getFullDescription(owner, true)+" growing on [npc.her] [npc.face].");
							break;
						case SIX_BUSHY:
							sb.append(" [npc.SheHasFull] a thick, bushy beard of "+owner.getFacialHairType().getFullDescription(owner, true)+" growing on [npc.her] [npc.face].");
							break;
						case SEVEN_WILD:
							sb.append(" [npc.SheHasFull] a wild, bushy beard of "+owner.getFacialHairType().getFullDescription(owner, true)+" growing on [npc.her] [npc.face].");
							break;
					}
				}
			}
		}
		sb.append("</p>");
		
		
		// Mouth & lips:
		sb.append(getHeader("Mouth"));
		sb.append(face.getMouth().getType().getBodyDescription(owner));
		
		// Throat modifiers:
		for(OrificeModifier om : OrificeModifier.values()) {
			if(owner.hasFaceOrificeModifier(om)) {
				switch(om) {
					case PUFFY:
						sb.append(" [npc.Her] [npc.lips] have swollen up to be far puffier than what would be considered normal.");
						break;
					case MUSCLE_CONTROL:
						sb.append(" [npc.SheHasFull] a series of internal muscles lining the inside of [npc.her] throat, allowing [npc.herHim] to expertly squeeze and grip down on any intruding object.");
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
		
		
		// Tongue & blowjob:
		if(owner.isFeral()) {
			sb.append(" [npc.Her] mouth holds [npc.a_tongueLength], [npc.tongueColour(true)] [npc.tongueRace] [npc.tongue]#IF(npc.isPiercedTongue()), which has been pierced#ENDIF.");
		} else {
			sb.append(" "+face.getTongue().getType().getBodyDescription(owner));
		}
		
		for(TongueModifier tm : TongueModifier.values()) {
			if(owner.hasTongueModifier(tm)) {
				switch(tm) {
					case RIBBED:
						sb.append(" Hard, fleshy ribs line its entire length, which are sure to grant extra pleasure to any orifice that they penetrate.");
						break;
					case TENTACLED:
						sb.append(" A series of little tentacles coat its surface, which wriggle and squirm with a mind of their own.");
						break;
					case BIFURCATED:
						sb.append(" Near the tip, it's split in two, leaving [npc.her] tongue bifurcated, like a snake.");
						break;
					case FLAT:
						if(owner.hasTongueModifier(TongueModifier.WIDE)) {
							sb.append(" It is both a lot flatter and wider than what would be considered normal.");
						} else {
							sb.append(" It is a lot flatter than what would be considered normal.");
						}
						break;
					case STRONG:
						sb.append(" It is far stronger than a normal tongue.");
						break;
					case WIDE:
						if(!owner.hasTongueModifier(TongueModifier.FLAT)) {
							sb.append(" It is a lot wider than what would be considered normal.");
						}
						break;
					case TAPERED:
						sb.append(" It tapers down from its base, getting narrower towards the tip.");
						break;
				}
			}
		}
		
		
		if (owner.isPlayer() || owner.isAreaKnownByCharacter(CoverableArea.MOUTH, Main.game.getPlayer())) {
			if(face.getMouth().getOrificeMouth().isVirgin()) { //TODO
				sb.append(" [npc.SheHas] [style.colourExcellent(never given head before)], so [npc.is] unsure of how much [npc.she] could fit down [npc.her] throat.</span>");
				
			} else {
				boolean virginityLossFound = false;
				for(SexAreaPenetration pt : SexAreaPenetration.values()) {
					if(pt.isTakesVirginity()) {
						if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, pt))!=null) {
							sb.append(" <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, pt)) + "</span>");
							virginityLossFound = true;
							break;
						}
					}
				}
				if(!virginityLossFound) {
					sb.append(" <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Name] [npc.has] lost [npc.her] oral virginity.</span>");
				}
				
				sb.append(" It is "+Capacity.getCapacityFromValue(face.getMouth().getOrificeMouth().getStretchedCapacity()).getDescriptor(true)+", and can comfortably accommodate objects of up to"
						+ " [style.colourSex("+ Units.size(Capacity.getMaximumComfortableDiameter(face.getMouth().getOrificeMouth().getElasticity(), face.getMouth().getOrificeMouth().getRawCapacityValue(), true)) + ")] in diameter.");
				
				if(Main.game.isPenetrationLimitationsEnabled()) {
					switch(owner.getFaceDepth()) {
						default:
							sb.append(" [npc.Her] throat is <span style='color:"+owner.getFaceDepth().getColour().toWebHexString()+";'>[npc.throatDepth]</span>,");
							break;
						case TWO_AVERAGE:
							sb.append(" [npc.Her] throat is of an <span style='color:"+owner.getFaceDepth().getColour().toWebHexString()+";'>average depth</span>,");
							break;
					}
					if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
						if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
							sb.append(" and as [npc.sheIsFull] a "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", [npc.she] can be pushed to [style.colourMinorGood(comfortably)] accommodate objects of a maximum length of "
									+ " [style.colourSex("+Units.size(owner.getFaceMaximumPenetrationDepthUncomfortable())+")].");
						} else {
							sb.append(" allowing [npc.herHim] to [style.colourMinorGood(comfortably)] accommodate [style.colourSex("+Units.size(owner.getFaceMaximumPenetrationDepthComfortable())+")] of a penetrative object,"
									+ " and [style.colourMinorBad(uncomfortably)] accommodate [style.colourSex("+Units.size(owner.getFaceMaximumPenetrationDepthUncomfortable())+")].");
						}
						
					} else {
						if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
							sb.append(" and as [npc.sheIsFull] a "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", combined with the fact that [npc.her] body is made out of [npc.bodyMaterial],"
									+ " [npc.she] can be pushed to [style.colourMinorGood(comfortably)] accommodate objects of [style.colourSex(any length)].");
						} else {
							sb.append(" allowing [npc.herHim] to [style.colourMinorGood(comfortably)] accommodate [style.colourSex("+Units.size(owner.getFaceMaximumPenetrationDepthComfortable())+")] of a penetrative object,"
									+ " and as [npc.her] body is made out of [npc.bodyMaterial], [npc.she] can be pushed to [style.colourMinorBad(uncomfortably)] accommodate objects of [style.colourSex(any length)].");
						}
					}
				}
				
				// Throat wetness:
				switch (face.getMouth().getOrificeMouth().getWetness(owner)) {
					case ZERO_DRY:
						sb.append(" [npc.Her] mouth and throat are [style.colourWetness(very dry)], and [npc.she] struggles to produce any saliva at all.");
						break;
					case ONE_SLIGHTLY_MOIST:
						sb.append(" [npc.Her] mouth and throat are [style.colourWetness(drier than most)], and [npc.she] only produces a small amount of saliva.");
						break;
					case TWO_MOIST:
						sb.append(" [npc.Her] mouth and throat are [style.colourWetness(a little drier than most)], and [npc.she] produces less saliva than what could be considered average.");
						break;
					case THREE_WET:
						sb.append(" [npc.Her] mouth and throat are [style.colourWetness(of a typical wetness)], and [npc.she] produces an average amount of saliva.");
						break;
					case FOUR_SLIMY:
						sb.append(" [npc.Her] mouth and throat are [style.colourWetness(wetter than most)], and [npc.she] produces more saliva than an average person.");
						break;
					case FIVE_SLOPPY:
						sb.append(" [npc.Her] mouth and throat are [style.colourWetness(considerably wetter than most)], and [npc.she] produces a lot more saliva than an average person.");
						break;
					case SIX_SOPPING_WET:
						sb.append(" [npc.Her] mouth and throat are [style.colourWetness(always wet and slimy)], and [npc.she] produces so much saliva that [npc.she] finds [npc.herself] having to swallow every few seconds.");
						break;
					case SEVEN_DROOLING:
						sb.append(" [npc.Her] mouth and throat are [style.colourWetness(overflowing with saliva)],"
								+ " and despite [npc.her] continuous efforts to swallow down the slimy mess, a constant stream of drool trickles from the corners of [npc.her] mouth.");
						break;
				}

				// Elasticity & plasticity:
				sb.append(" [npc.Her] throat");
				switch (face.getMouth().getOrificeMouth().getElasticity()) {
					case ZERO_UNYIELDING:
						sb.append(" is [style.colourElasticity(extremely unyielding)],");
						break;
					case ONE_RIGID:
						sb.append(" [style.colourElasticity(takes a huge amount of effort to be stretched out)],");
						break;
					case TWO_FIRM:
						sb.append(" [style.colourElasticity(does not stretch very easily)],");
						break;
					case THREE_FLEXIBLE:
						sb.append(" [style.colourElasticity(reluctantly stretches out)] when used as a sexual orifice,");
						break;
					case FOUR_LIMBER:
						sb.append(" is [style.colourElasticity(somewhat resistant to being stretched out)],");
						break;
					case FIVE_STRETCHY:
						sb.append(" [style.colourElasticity(stretches out fairly easily)],");
						break;
					case SIX_SUPPLE:
						sb.append(" [style.colourElasticity(stretches out very easily)],");
						break;
					case SEVEN_ELASTIC:
						sb.append(" is [style.colourElasticity(extremely elastic)],");
						break;
				}
				sb.append(" and after being used, it "+face.getMouth().getOrificeMouth().getPlasticity().getDescription()+".");
			}
			
		} else {
			sb.append(" [style.colourDisabled(You don't know [npc.herHim] well enough to know how competent [npc.she] is at performing oral sex.)]");
		}
		
		sb.append("</p>");

		
		// Describe body:
		sb.append(getHeader("Torso"));
		if(owner.isFeral()) {
			sb.append("[style.colourFeral([npc.Her] torso is entirely feral in appearance, and is no different to that of a regular [npc.race]'s.)] ");
		}
		sb.append(owner.getTorsoType().getBodyDescription(owner));
		sb.append(" [npc.SheHasFull] <span style='color:"+ BodySize.valueOf(getBodySize()).getColour().toWebHexString() + ";'>" + BodySize.valueOf(getBodySize()).getName(true) + "</span>, "
						+ "<span style='color:"+ Muscle.valueOf(getMuscle()).getColour().toWebHexString() + ";'>" +Muscle.valueOf(getMuscle()).getName(false) + "</span>"
							+ " body, giving [npc.herHim] <span style='color:"+ owner.getBodyShape().toWebHexStringColour() + ";'>[npc.a_bodyShape]</span> body shape.");
		if(torso.getType().getTags().contains(BodyPartTag.ALLOWS_FLIGHT)) {
			sb.append(" [style.colourBlue(The unique nature of [npc.namePos] body allows [npc.herHim] to fly!)]");
		}
		
		
		// Pregnancy:
		if(owner.isVisiblyPregnant()) {
			if(owner.getLegConfiguration()!=LegConfiguration.BIPEDAL) {
				sb.append(" The belly of [npc.her] [style.colourFeral(feral torso)]");
			} else {
				sb.append("[npc.Her] belly");
			}
			if(owner.hasStatusEffect(StatusEffect.PREGNANT_1)){
				sb.append(" is slightly swollen, and it's clear to anyone who takes a closer look that <span style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc.sheIs] pregnant</span>.");
				
			} else if(owner.hasStatusEffect(StatusEffect.PREGNANT_2)){
				sb.append(" is heavily swollen, and it's clear to anyone who glances [npc.her] way that <span style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc.sheIs] pregnant</span>.");
			
			} else if(owner.hasStatusEffect(StatusEffect.PREGNANT_3)){
				sb.append(" is massively swollen, and it's blatantly obvious to anyone who glances [npc.her] way that"
						+ " <span style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc.sheIs] expecting to give birth very soon</span>.");
			}
		}
		sb.append("</p>");
		
		
		
		// Breasts:
		sb.append(getHeader("Chest"));
		if(owner.isFeral() && !owner.getFeralAttributes().isBreastsPresent()) {
			sb.append("As [npc.nameHasFull] the body of a feral [npc.race], [npc.she] [npc.do] not have any breasts!");
			
		} else {
			Breast viewedBreast = breast;
			if(Main.game.getPlayer().hasIngestedPsychoactiveFluidType(FluidTypeBase.MILK)) {
				viewedBreast = new Breast(breast.getType(),
						breast.getShape(),
						(int)(breast.getRawSizeValue()*(1.75f)),
						(int)((breast.getRawMilkStorageValue()+100)*(2.25f)),
						breast.getRows(),
						breast.getNipples().getNippleSizeValue(),
						breast.getNipples().getNippleShape(),
						breast.getNipples().getAreolaeSizeValue(),
						breast.getNipples().getAreolaeShape(),
						breast.getNippleCountPerBreast(),
						breast.getNipples().getOrificeNipples().getRawCapacityValue(),
						breast.getNipples().getOrificeNipples().getDepth(null).getValue(),
						breast.getNipples().getOrificeNipples().getElasticity().getValue(),
						breast.getNipples().getOrificeNipples().getPlasticity().getValue(),
						breast.getNipples().getOrificeNipples().isVirgin());
				sb.append(" <i style='color:"+PresetColour.PSYCHOACTIVE.toWebHexString()+";'>The psychoactive milk you recently ingested is causing your view of "+(owner.isPlayer()?"your":"[npc.namePos]")+" breasts to be distorted!</i>");
			}
			if(viewedBreast.getRawSizeValue()>0){
				sb.append(" [npc.SheHasFull] " + Util.intToString(owner.getBreastRows()) + " pair" + (owner.getBreastRows() == 1 ? "" : "s") + " of ");
				if(Main.game.isVestigialMultiBreastsEnabled() && owner.getBreastRows()>1) {
					sb.append("[npc.breasts]");
				} else {
					sb.append(viewedBreast.getSize().getDescriptor()+" [npc.breasts]");
				}
				
				if(owner.getBreastRows()==1) {
					if (viewedBreast.getSize().isTrainingBraSize()) {
						sb.append(", which fit comfortably into a training bra.");
					} else {
						sb.append(", which fit comfortably into "+UtilText.generateSingularDeterminer(viewedBreast.getSize().getCupSizeName())+" "+viewedBreast.getSize().getCupSizeName()+"-cup bra.");
					}
					
				} else if(owner.getBreastRows()==2) {
					if (viewedBreast.getSize().isTrainingBraSize()) {
						sb.append(", with [npc.her] top pair fitting comfortably into a training bra,"
								+(Main.game.isVestigialMultiBreastsEnabled()
									?" and the pair below being vestigial in size."
									:" and the pair below being slightly smaller."));
					} else {
						sb.append(", with [npc.her] top pair fitting comfortably into "
								+UtilText.generateSingularDeterminer(viewedBreast.getSize().getCupSizeName())+" "+viewedBreast.getSize().getCupSizeName()+"-cup bra,"
										+(Main.game.isVestigialMultiBreastsEnabled()
												?" and the pair below being vestigial in size."
												:" and the pair below being slightly smaller."));
					}
					
				} else if(owner.getBreastRows()>2) {
					if (viewedBreast.getSize().isTrainingBraSize()) {
						sb.append(", with [npc.her] top pair fitting comfortably into a training bra,"
								+(Main.game.isVestigialMultiBreastsEnabled()
										?" and the pairs below being vestigial in size."
										:" and the pairs below each being slightly smaller than the ones above."));
					} else {
						sb.append(", with [npc.her] top pair fitting comfortably into "
									+UtilText.generateSingularDeterminer(viewedBreast.getSize().getCupSizeName())+" "+viewedBreast.getSize().getCupSizeName()+"-cup bra,"
											+(Main.game.isVestigialMultiBreastsEnabled()
													?" and the pairs below being vestigial in size."
													:" and the pairs below each being slightly smaller than the ones above."));
					}
				}
				
			} else {
				sb.append(" [npc.SheHasFull] a completely flat chest");
				if(owner.getBreastRows()==1) {
					sb.append(", with a single pair of pecs.");
				} else {
					sb.append(", with "+Util.intToString(owner.getBreastRows())+" pairs of pecs.");
				}
			}
			
			sb.append(" " + getBreastDescription(owner, viewedBreast));
		}
		sb.append("</p>");
		
		
		// BreastsCrotch:
		if(owner.isAreaKnownByCharacter(CoverableArea.BREASTS_CROTCH, Main.game.getPlayer())) {
			if(owner.hasBreastsCrotch()) {
				sb.append(getHeader(owner.getBreastCrotchShape()==BreastShape.UDDERS?"Udders":"Crotch-boobs"));
				sb.append(getBreastCrotchDescription(owner, breastCrotch));
				sb.append("</p>");
				
			} else if(this.leg.getLegConfiguration()!=LegConfiguration.BIPEDAL
					|| this.isFeral()
					|| (this.getRaceStage()==RaceStage.GREATER && RacialBody.valueOfRace(this.getRace()).getBreastCrotchType()!=BreastType.NONE && Main.getProperties().getUddersLevel()==2)) {
				sb.append(getHeader(owner.getBreastCrotchShape()==BreastShape.UDDERS?"Udders":"Crotch-boobs"));
				sb.append("[style.colourDisabled([npc.She] [npc.do] not have any crotch-boobs or udders.)]");
				sb.append("</p>");
			}
			
		} else if(leg.getLegConfiguration()!=LegConfiguration.BIPEDAL
				|| this.isFeral()
				|| (this.getRaceStage()==RaceStage.GREATER && Main.getProperties().getUddersLevel()==2)) {
			sb.append(getHeader(owner.getBreastCrotchShape()==BreastShape.UDDERS?"Udders":"Crotch-boobs"));
			if(owner.hasBreastsCrotch() && owner.isBreastsCrotchVisibleThroughClothing() && leg.getLegConfiguration().isBipedalPositionedCrotchBoobs()) {
				sb.append("Although you haven't seen [npc.her] exposed stomach before, [npc.her] [npc.crotchBoobsSize], [npc.crotchBoobsCups]-cup [npc.crotchBoobs] quite clearly bulge out from beneath [npc.her] [npc.topClothing(STOMACH)].");
			} else {
				sb.append("[style.colourDisabled(You haven't seen [npc.her] exposed stomach before, so you don't know if [npc.sheHasFull] any crotch-boobs or udders.)]");
			}
			sb.append("</p>");
		}
		
		
		// Arms and legs:

		// Arms:
		sb.append(getHeader("Arms"));
		if(owner.isFeral() && !owner.getFeralAttributes().isArmsOrWingsPresent()) {
			if(owner.getLegConfiguration()==LegConfiguration.AVIAN) {
				sb.append("As [npc.nameHasFull] the body of a feral [npc.race], [npc.she] [npc.has] [npc.armRows] wings, which are [npc.materialCompositionDescriptor] beautiful [npc.armFullDescription(true)]!");
			} else {
				sb.append("As [npc.nameHasFull] the body of a feral [npc.race], [npc.she] [npc.do] not have any arms!");
			}
			
		} else {
			sb.append(arm.getType().getBodyDescription(owner));
			
			if(owner.getHandNailPolish().getPrimaryColour() != PresetColour.COVERING_NONE) {
				if(owner.getArmType().equals(ArmType.HARPY)) {
					sb.append(" The little claw on [npc.her] thumb has been painted in "+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS).getFullDescription(owner, true)+".");
				} else {
					sb.append(" [npc.Her] fingernails have been painted in "+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS).getFullDescription(owner, true)+".");
				}
			}
			
			if(Main.game.isBodyHairEnabled()) {
				if(owner.getUnderarmHairType().getType()==BodyCoveringType.BODY_HAIR_SCALES_ALLIGATOR) {
					switch(owner.getUnderarmHair()) {
						case ZERO_NONE:
							sb.append(" There is no trace of any rough "+owner.getUnderarmHairType().getName(owner)+" in [npc.her] armpits.");
							break;
						case ONE_STUBBLE:
							sb.append(" [npc.SheHasFull] a rough patch of "+owner.getUnderarmHairType().getFullDescription(owner, true)+" in each of [npc.her] armpits.");
							break;
						case TWO_MANICURED:
							sb.append(" [npc.SheHasFull] a rough patch of "+owner.getUnderarmHairType().getFullDescription(owner, true)+" in each of [npc.her] armpits.");
							break;
						case THREE_TRIMMED:
							sb.append(" [npc.SheHasFull] a rough patch of "+owner.getUnderarmHairType().getFullDescription(owner, true)+" in each of [npc.her] armpits.");
							break;
						case FOUR_NATURAL:
							sb.append(" [npc.SheHasFull] a natural amount of rough "+owner.getUnderarmHairType().getFullDescription(owner, true)+" in each of [npc.her] armpits.");
							break;
						case FIVE_UNKEMPT:
							sb.append(" [npc.SheHasFull] an unkempt mass of rough "+owner.getUnderarmHairType().getFullDescription(owner, true)+" in each of [npc.her] armpits.");
							break;
						case SIX_BUSHY:
							sb.append(" [npc.SheHasFull] a thick, rough mass of "+owner.getUnderarmHairType().getFullDescription(owner, true)+" in each of [npc.her] armpits.");
							break;
						case SEVEN_WILD:
							sb.append(" [npc.SheHasFull] a wild, rough mass of "+owner.getUnderarmHairType().getFullDescription(owner, true)+" in each of [npc.her] armpits.");
							break;
					}
				} else {
					switch(owner.getUnderarmHair()) {
						case ZERO_NONE:
							sb.append(" There is no trace of any "+owner.getUnderarmHairType().getName(owner)+" in [npc.her] armpits.");
							break;
						case ONE_STUBBLE:
							sb.append(" [npc.SheHasFull] a stubbly patch of "+owner.getUnderarmHairType().getFullDescription(owner, true)+" in each of [npc.her] armpits.");
							break;
						case TWO_MANICURED:
							sb.append(" [npc.SheHasFull] a well-manicured patch of "+owner.getUnderarmHairType().getFullDescription(owner, true)+" in each of [npc.her] armpits.");
							break;
						case THREE_TRIMMED:
							sb.append(" [npc.SheHasFull] a trimmed patch of "+owner.getUnderarmHairType().getFullDescription(owner, true)+" in each of [npc.her] armpits.");
							break;
						case FOUR_NATURAL:
							sb.append(" [npc.SheHasFull] a natural amount of "+owner.getUnderarmHairType().getFullDescription(owner, true)+" in each of [npc.her] armpits.");
							break;
						case FIVE_UNKEMPT:
							sb.append(" [npc.SheHasFull] an unkempt mass of "+owner.getUnderarmHairType().getFullDescription(owner, true)+" in each of [npc.her] armpits.");
							break;
						case SIX_BUSHY:
							sb.append(" [npc.SheHasFull] a thick, bushy mass of "+owner.getUnderarmHairType().getFullDescription(owner, true)+" in each of [npc.her] armpits.");
							break;
						case SEVEN_WILD:
							sb.append(" [npc.SheHasFull] a wild, bushy mass of "+owner.getUnderarmHairType().getFullDescription(owner, true)+" in each of [npc.her] armpits.");
							break;
					}
				}
			}
			
			if (!(owner.isFeral() && owner.getArmType().allowsFlight())) {
				sb.append(" [npc.Her] [npc.arms] are "+(Util.randomItemFrom(owner.getBodyShape().getLimbDescriptors()))
						+", and are <span style='color:"+owner.getFemininity().getColour().toWebHexString()+";'>"+owner.getFemininity().getName(false)+" in appearance.");
			}
			
			if(arm.getType().allowsFlight()) {
				if(this.getBodyMaterial() == BodyMaterial.SLIME) {
					sb.append(" [style.colourSlime(As they're made out of slime, flight is rendered impossible...)]");
				} else if(this.getBodyMaterial() == BodyMaterial.SILICONE) {
					sb.append(" [style.colourDoll(As they're made out of silicone, flight is rendered impossible...)]");
				} else if(this.getLeg().getLegConfiguration().getMinimumWingSizeForFlight(this).getValue()>WingSize.THREE_LARGE.getValue()) {
					sb.append(" They aren't large enough to allow [npc.herHim] to fly.");
				} else {
					sb.append(" [style.colourBlue(They are large and powerful enough to allow [npc.herHim] to fly!)]");
				}
			}
		}
		sb.append("</p>");

		
		// Legs:
		if(owner.getLegType().isLegsReplacedByTentacles()) {
			sb.append(getHeader("Tentacle-legs"));
			
			sb.append("In place of legs, [npc.name] [npc.has] [npc.tentacleCount] tentacles.");
			sb.append(owner.getTentacleType().getGirthDescription(owner));
			sb.append(" They measure [npc.tentacleLength(true)] in length and, at their base, each one measures [npc.tentacleBaseDiameter(true)] in diameter ([npc.tentacleBaseCircumference(true)] in circumference).");
			for(BodyPartTag bpt : owner.getTentacleType().getTags()) {
				switch(bpt) {
					case TAIL_PREHENSILE:
						sb.append(" [npc.She] [npc.has] a very high level of control over each of these tentacles, allowing [npc.herHim] to use them to pick up and manipulate objects.");
						break;
					case TAIL_SLEEP_HUGGING:
						break;
					case TAIL_SUITABLE_FOR_PENETRATION:
						sb.append(" Each of them can be used as a penetrative object during sex, and when used to penetrate an orifice, a maximum of [npc.tentaclePenetrationLength(true)] can be inserted.");
						break;
					case TAIL_TAPERING_EXPONENTIAL:
						sb.append(" The girth of each one rapidly tapers off from the base, and so the [npc.tentacleTip] of each one is [npc.tentacleTipDiameter(true)] in diameter ([npc.tentacleTipCircumference(true)] in circumference).");
						break;
					case TAIL_TAPERING_LINEAR:
						sb.append(" The girth of each one tapers off at a steady rate from the base, and so the [npc.tentacleTip] of each one is [npc.tentacleTipDiameter(true)] in diameter ([npc.tentacleTipCircumference(true)] in circumference).");
						break;
					case TAIL_TAPERING_NONE:
						sb.append(" The girth of each one does not taper off from the base, and so each tentacle has a constant diameter all the way to the [npc.tentacleTip].");
						break;
					case TAIL_TAPERING_BULBOUS:
						sb.append(" The girth of each one expands, then tapers off, at a steady rate from the base so as to make a bulbous, oval shape.");
						break;
					default:
						break;
				}
			}
			sb.append("<br/>");
			
		} else {
			sb.append(getHeader("Legs"));
		}
		if(owner.isFeral()) {
			String feralLegsPrefix = "Just like the rest of [npc.her] body, [npc.her] ";
			switch(owner.getLegConfiguration()) {
				case ARACHNID:
				case CEPHALOPOD:
				case AVIAN:
					sb.append(feralLegsPrefix).append("[npc.legRace] [npc.legs] are entirely [style.colourFeral(feral in nature)]. ");
					break;
				case BIPEDAL:
					break;
				case TAIL:
					sb.append(feralLegsPrefix).append("[npc.legRace]'s tail, which [npc.sheHasFull] in place of legs, is entirely [style.colourFeral(feral in nature)]. ");
					break;
				case TAIL_LONG:
					sb.append(feralLegsPrefix).append("long [npc.legRace]'s tail, which [npc.sheHasFull] in place of legs, is entirely [style.colourFeral(feral in nature)]. ");
					if(!owner.isFeral()) {
						sb.append(" It forms the majority of [npc.her] body's [npc.heightValue]-length,");
						sb.append(" and from [npc.her] head, it tapers off at a steady rate towards the tip, where it ends with a diameter of [npc.tailTipDiameter(true)] ([npc.tailTipCircumference(true)] in circumference).");
						sb.append(" When used to penetrate an orifice, a maximum of [npc.tailPenetrationLength(true)] can be inserted. ");
					} else {
						sb.append(" Its [npc.tailLength]-length contributes to [npc.her] body's total length of "+Units.size(owner.getHeightValue()+owner.getLegTailLength(false), Units.ValueType.NUMERIC, Units.UnitType.LONG)+".");
						sb.append(" It tapers off at a steady rate towards the tip, where it ends with a diameter of [npc.tailTipDiameter(true)] ([npc.tailTipCircumference(true)] in circumference).");
						sb.append(" When used to penetrate an orifice, both [npc.her] tail and body can be inserted, up to a maximum of [npc.tailPenetrationLength(true)]. ");
					}
					break;
				case QUADRUPEDAL:
				case WINGED_BIPED:
					sb.append(feralLegsPrefix).append("[npc.legs], being part of [npc.her] [npc.legRace]'s body, are entirely [style.colourFeral(feral in nature)]. ");
					break;
			}
			if(owner.getLegConfiguration().getNumberOfLegs()>0) {
				sb.append("[npc.Her] legs are [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], and [npc.her] feet are formed into ").append(owner.getLegType().getFootType().getFootNamePlural()).append(".");
			} else {
				sb.append("It is [npc.materialCompositionDescriptor] [npc.legFullDescription(true)].");
			}
			
		} else {
			switch(owner.getLegConfiguration()) {
				case ARACHNID:
				case CEPHALOPOD:
				case AVIAN:
					sb.append("[npc.Her] [npc.legs], being part of [npc.her] [npc.legRace]'s body, are entirely [style.colourFeral(feral in nature)]. ");
					break;
				case BIPEDAL:
					break;
				case TAIL:
					sb.append("[npc.Her] [npc.legRace]'s tail, which [npc.sheHasFull] in place of legs, is entirely [style.colourFeral(feral in nature)]. ");
					break;
				case TAIL_LONG:
					sb.append("[npc.Her] long [npc.legRace]'s tail, which [npc.sheHasFull] in place of legs, is entirely [style.colourFeral(feral in nature)].");
					sb.append(" It measures ").append(Units.size(owner.getLegTailLength(false))).append(" in length,");
					sb.append(" and tapers off at a steady rate towards the tip, where it ends with a diameter of [npc.tailTipDiameter(true)] ([npc.tailTipCircumference(true)] in circumference).");
					sb.append(" When used to penetrate an orifice, a maximum of [npc.tailPenetrationLength(true)] can be inserted.");
					break;
				case QUADRUPEDAL:
				case WINGED_BIPED:
					sb.append("[npc.Her] [npc.legs], being part of [npc.her] [npc.legRace]'s body, are entirely [style.colourFeral(feral in nature)]. ");
					break;
			}
			if(owner.getLegConfiguration().getNumberOfLegs()>0) {
				sb.append(leg.getType().getBodyDescription(owner));
			} else {
				sb.append(" It is [npc.materialCompositionDescriptor] [npc.legFullDescription(true)].");
			}
		}

		if(owner.getLegConfiguration().getNumberOfLegs()>0) {
			switch(owner.getFootStructure()) {
				case NONE:
				case TENTACLED:
					break;
				case DIGITIGRADE:
					sb.append(" [npc.Her] [npc.legs] and [npc.feet] are [style.colourTFGeneric("+owner.getFootStructure().getName()+")], meaning that [npc.she] naturally [npc.verb(walk)] on [npc.her] toes.");
					break;
				case PLANTIGRADE:
					sb.append(" [npc.Her] [npc.legs] and [npc.feet] are [style.colourTFGeneric("+owner.getFootStructure().getName()+")], meaning that [npc.she] naturally [npc.verb(walk)] with [npc.her] feet flat on the ground.");
					break;
				case UNGULIGRADE:
					sb.append(" [npc.Her] [npc.legs] and [npc.feet] are [style.colourTFGeneric("+owner.getFootStructure().getName()+")], meaning that [npc.she] naturally [npc.verb(walk)] on [npc.her] hoofs.");
					break;
				case ARACHNOID:
					sb.append(" [npc.Her] [npc.legs] and [npc.feet] are [style.colourTFGeneric("+owner.getFootStructure().getName()+")], meaning that [npc.she] [npc.verb(walk)] on the ends of [npc.her] segmented arachnoid legs.");
					break;
			}
		}
		
		if(owner.getFootNailPolish().getPrimaryColour() != PresetColour.COVERING_NONE) {
			sb.append(owner.getLegType().getFootType().getFootNailPolishDescription(owner));
		}
		
		sb.append("<br/>");
		
		if(owner.getLegCount()>0) {
			sb.append(" [npc.Her] [npc.legs] are "+(Util.randomItemFrom(owner.getBodyShape().getLimbDescriptors()))+", and ");
			if (femininity <= Femininity.MASCULINE_STRONG.getMaximumFemininity()) {
				sb.append("[style.colourMasculineStrong(have a very masculine shape to them)].");
				
			} else if (femininity <= Femininity.MASCULINE.getMaximumFemininity()) {
				sb.append("[style.colourMasculine(have a masculine shape to them)].");
				
			} else if (femininity <= Femininity.ANDROGYNOUS.getMaximumFemininity()) {
				sb.append("[style.colourAndrogynous(look quite androgynous)].");
				
			} else if (femininity <= Femininity.FEMININE.getMaximumFemininity()) {
				sb.append("[style.colourFeminine(have a feminine shape to them)].");
				
			} else {
				sb.append("[style.colourFeminineStrong(have an extremely feminine shape to them)].");
			}
			
		} else if(!owner.isFeral()) {
			sb.append(" [npc.Her] [npc.leg(true)] is "+(Util.randomItemFrom(owner.getBodyShape().getLimbDescriptors()))+", and ");
			if (femininity <= Femininity.MASCULINE_STRONG.getMaximumFemininity()) {
				sb.append("[style.colourMasculineStrong(has a very masculine shape to it)].");
				
			} else if (femininity <= Femininity.MASCULINE.getMaximumFemininity()) {
				sb.append("[style.colourMasculine(has a masculine shape to it)].");
				
			} else if (femininity <= Femininity.ANDROGYNOUS.getMaximumFemininity()) {
				sb.append("[style.colourAndrogynous(looks quite androgynous)].");
				
			} else if (femininity <= Femininity.FEMININE.getMaximumFemininity()) {
				sb.append("[style.colourFeminine(has a feminine shape to it)].");
				
			} else {
				sb.append("[style.colourFeminineStrong(has an extremely feminine shape to it)].");
			}
		}
		
		sb.append("</p>");
		
		
		// Wing:
		if(wing.getType()!=WingType.NONE) {
			sb.append(getHeader("Wings"));
			if(owner.getLegConfiguration().isWingsOnLegConfiguration()) {
				sb.append("Growing from either side of [npc.her] [npc.legConfiguration] body, ");
			} else {
				sb.append("Growing from [npc.her] shoulder-blades, ");
			}
			sb.append(wing.getType().getBodyDescription(owner));
			if(wing.getType().allowsFlight()) {
				if(this.getBodyMaterial() == BodyMaterial.SLIME) {
					sb.append(" [style.colourSlime(As they're made out of slime, flight is rendered impossible...)]");
				} else if(this.getBodyMaterial() == BodyMaterial.SILICONE) {
					sb.append(" [style.colourDoll(As they're made out of silicone, flight is rendered impossible...)]");
				} else if(wing.getSizeValue()>=owner.getLegConfiguration().getMinimumWingSizeForFlight(owner.getBody()).getValue()) {
					sb.append(" [style.colourBlue(They are large and powerful enough to allow [npc.herHim] to fly!)]");
				} else {
					sb.append(" They aren't large enough to allow [npc.herHim] to fly.");
				}
				
			} else {
				sb.append(" They are entirely incapable of flight.");
			}
			sb.append("</p>");
		}
	
		
		// Tail:
		if(tail.getType()!=TailType.NONE) {
			sb.append(getHeader("Tail"));
			sb.append(owner.getTailType().getBodyDescription(owner));
			sb.append(owner.getTailType().getGirthDescription(owner));
			if(owner.getTailCount()==1) {
				sb.append(" It measures [npc.tailLength(true)] in length and, at the base, it measures [npc.tailBaseDiameter(true)] in diameter ([npc.tailBaseCircumference(true)] in circumference).");
				if(owner.getTailType().getTags().contains(BodyPartTag.TAIL_TAPERING_EXPONENTIAL)) {
					sb.append(" It rapidly tapers off from the base, and so at the [npc.tailTip], it is [npc.tailTipDiameter(true)] in diameter ([npc.tailTipCircumference(true)] in circumference).");
					
				} else if(owner.getTailType().getTags().contains(BodyPartTag.TAIL_TAPERING_LINEAR)) {
					sb.append(" It tapers off at a steady rate from the base, and so at the [npc.tailTip], it is [npc.tailTipDiameter(true)] in diameter ([npc.tailTipCircumference(true)] in circumference).");
					
				} else if(owner.getTailType().getTags().contains(BodyPartTag.TAIL_TAPERING_BULBOUS)) {
					sb.append(" Its girth expands, then tapers off, at a steady rate from the base so as to make a bulbous, oval shape.");
					
				} else {
					sb.append(" It does not taper off from the base, and is a constant diameter all the way to the [npc.tailTip].");
				}
				
			} else {
				sb.append(" They measure [npc.tailLength(true)] in length and, at their base, each one measures [npc.tailBaseDiameter(true)] in diameter ([npc.tailBaseCircumference(true)] in circumference).");
				if(owner.getTailType().getTags().contains(BodyPartTag.TAIL_TAPERING_EXPONENTIAL)) {
					sb.append(" They rapidly taper off from the base, and so at their [npc.tailTips], they are [npc.tailTipDiameter(true)] in diameter ([npc.tailTipCircumference(true)] in circumference).");
					
				} else if(owner.getTailType().getTags().contains(BodyPartTag.TAIL_TAPERING_LINEAR)) {
					sb.append(" They taper off at a steady rate from the base, and so at their [npc.tailTips], they are [npc.tailTipDiameter(true)] in diameter ([npc.tailTipCircumference(true)] in circumference).");
					
				} else if(owner.getTailType().getTags().contains(BodyPartTag.TAIL_TAPERING_BULBOUS)) {
					sb.append(" Their girth expands, then tapers off, at a steady rate from the base so as to make them into bulbous, oval shapes.");
					
				} else {
					sb.append(" They do not taper off from the base, and are a constant diameter all the way to the [npc.tailTips].");
				}
			}
			
			if(owner.isTailSuitableForPenetration()) {
				sb.append(" When used to penetrate an orifice, a maximum of [npc.tailPenetrationLength(true)] can be inserted.");
			} else {
				sb.append(" It is not suitable for penetrating orifices.");
			}
			sb.append("</p>");
		}
		
		
		// Spinneret:
		if(owner.hasSpinneret()) {
			sb.append(getHeader("Spinneret"));
			sb.append(getSpinneretDescription(owner));
			sb.append("</p>");
		}
		
		
		// Cloaca:
		if(owner.isPlayer()
				|| owner.isAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer())
				|| owner.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer())
				|| owner.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())) {
			switch(owner.getGenitalArrangement()) {
				case NORMAL:// Don't need to add a description for normal arrangement I think.
					break;
				case CLOACA:
				case CLOACA_BEHIND:
					sb.append(getHeader("Cloaca"));
					sb.append("[style.colourFeral("+owner.getGenitalArrangement().getDescription()+")]");
					sb.append("</p>");
					break;
			}
		}
		
		
		// Ass & hips:
		sb.append(getHeader("Ass"));
		if(!owner.isFeral() && ass.isFeral(owner)) {
			sb.append("[npc.Her] [npc.hips+] and [npc.assSize] [npc.ass] are [style.colourFeral(part of [npc.her] feral lower body)], and are [npc.materialCompositionDescriptor] [npc.assFullDescription(true)].");
		} else {
			sb.append("[npc.Her] [npc.hips+] and [npc.assSize] [npc.ass] are [npc.materialCompositionDescriptor] [npc.assFullDescription(true)].");
		}
		
		if(owner.isAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer())) {
			sb.append(" " + getAssDescription(owner, false));
		} else {
			sb.append(" [style.colourDisabled(You haven't seen [npc.her] naked ass before, so you don't know what [npc.her] asshole looks like.)]");
		}
		sb.append("</p>");
		
		if(owner.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer()) && owner.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())) {
			// Vagina, virgin/capacity, wetness:
			if (vagina.getType() == VaginaType.NONE && penis.getType() == PenisType.NONE) {
				sb.append(getHeader("Genitals"));
				sb.append(getMoundDescription(owner));
				sb.append("</p>");
			}
		}

		if(owner.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())) {
			// Penises, cum production, testicle size, capacity:
			if (owner.hasPenis()) {
				sb.append(getHeader("Penis"));
				sb.append(getPenisDescription(owner));
				sb.append("</p>");
			}
			
		} else {
			sb.append(getHeader("Penis"));
			if (observant) {
				if(owner.hasPenis()) {
					sb.append("[style.colourDisabled(Thanks to your '"+Perk.OBSERVANT.getName(Main.game.getPlayer())+"' trait, you can tell that [npc.sheHasFull] a cock,"
										+ " but as you haven't seen [npc.her] naked groin before, you don't know what it looks like.)]");
				} else {
					sb.append("[style.colourDisabled(You haven't seen [npc.her] naked groin before, but thanks to your '"+Perk.OBSERVANT.getName(Main.game.getPlayer())+"' trait, you can tell that [npc.she] doesn't have a cock.)]");
				}
				
			} else {
				sb.append("[style.colourDisabled(You haven't seen [npc.her] naked groin before, so you don't know what [npc.her] cock looks like, or even if [npc.sheHasFull] one.)]");
			}
			sb.append("</p>");
		}
		
		if(owner.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer())) {
			// Vagina, virgin/capacity, wetness:
			if (vagina.getType() != VaginaType.NONE) {
				sb.append(getHeader("Vagina"));
				sb.append(getVaginaDescription(owner));
				sb.append("</p>");
			}
		} else {
			sb.append(getHeader("Vagina"));
			if (observant){
				if(vagina.getType() != VaginaType.NONE){
					sb.append("[style.colourDisabled(Thanks to your '"+Perk.OBSERVANT.getName(Main.game.getPlayer())+"' trait, you can tell that [npc.sheHasFull] a pussy,"
									+ " but as you haven't seen [npc.her] naked groin before, you don't know what it looks like.)]");
					
				} else {
					sb.append("[style.colourDisabled(You haven't seen [npc.her] naked groin before, but thanks to your '"+Perk.OBSERVANT.getName(Main.game.getPlayer())+"' trait, you can tell that [npc.she] doesn't have a pussy.)]");
				}
				
			} else {
				sb.append("[style.colourDisabled(You haven't seen [npc.her] naked groin before, so you don't know what [npc.her] pussy looks like, or even if [npc.sheHasFull] one.)]");
			}
			sb.append("</p>");
		}
		
		// Tattoos:
		StringBuilder tattooSB = new StringBuilder();
		for(Entry<InventorySlot, Tattoo> tattoEntry : owner.getTattoos().entrySet()) {
			InventorySlot tattooSlot = tattoEntry.getKey();
			boolean knowsArea = owner.isPlayer();
			if(!knowsArea) {
				caLoop:
				for(CoverableArea ca : CoverableArea.values()) {
					for(InventorySlot slot : ca.getAssociatedInventorySlots(owner)) {
						if(tattooSlot==slot) {
							knowsArea = true;
							break caLoop;
						}
					}
				}
			}
			if(knowsArea) {
				if(tattooSB.length()>0) {
					tattooSB.append("<br/>");
				}
				Tattoo tattoo = tattoEntry.getValue();
				if(tattoo.getBodyOverviewDescription().isEmpty()) { // Old version support
					tattooSB.append("<span style='color:"+tattoo.getPrimaryColour().toWebHexString()+";'>"+Util.capitaliseSentence(tattooSlot.getTattooSlotName())+":</span> ");
					tattooSB.append(tattoo.getDescription());
					tattooSB.append(".");
					
				} else {
					tattooSB.append("On [npc.her] [style.boldBlueSteel("+tattooSlot.getTattooSlotName()+")], [npc.sheHasFull] ");
					tattooSB.append(tattoo.getBodyOverviewDescription());
					if(tattoo.getType()!=TattooType.getTattooTypeFromId("innoxia_misc_none")) {
						tattooSB.append(", which is primarily coloured ");
						tattooSB.append("<span style='color:"+tattoo.getPrimaryColour().toWebHexString()+";'>"+tattoo.getPrimaryColour().getName()+"</span>");
					}
					tattooSB.append(".");
				}
				if(tattoo.getWriting()!=null && tattoo.getWriting().getText()!=null && !tattoo.getWriting().getText().isEmpty()) {
					tattooSB.append(" It bears the words: '"+tattoo.getFormattedWritingOutput()+"'");
				}
				if(tattoo.getCounter()!=null && tattoo.getCounter().getType()!=TattooCounterType.NONE) {
					String counterName = tattoo.getCounter().getType().getName();
					if(tattoo.getWriting()!=null && tattoo.getWriting().getText()!=null && !tattoo.getWriting().getText().isEmpty()) {
						tattooSB.append(", and is enchanted to keep ");
					} else {
						tattooSB.append(" The tattoo is enchanted to keep ");
					}
					tattooSB.append(UtilText.generateSingularDeterminer(counterName)+" '"+counterName+"' count, which reads: '"+tattoo.getFormattedCounterOutput(owner)+"'.");
				} else if(tattoo.getWriting()!=null && tattoo.getWriting().getText()!=null && !tattoo.getWriting().getText().isEmpty()) {
					tattooSB.append(".");
				}
			}
		}
		if(tattooSB.length()>0) {
			sb.append(getHeader("Tattoos"));
			sb.append(tattooSB.toString());
		}
		
		if(!owner.isPlayer()) {
			sb.append(getSexDetails(owner));
			
			sb.append(getPregnancyDetails(owner));

			sb.append(getIncubationPregnancyDetails(owner));
		}

		return UtilText.parse(owner, sb.toString());
	}

	private void addRaceWeight(Map<AbstractRace, Integer> raceWeightMap, AbstractRace race, int weight) {
		if(race!=null && race!=Race.NONE) {
			raceWeightMap.putIfAbsent(race, 0);
			raceWeightMap.put(race, raceWeightMap.get(race)+weight);
		}
	}

	
	/** To be called after every transformation. Returns the body's race. */
	public void calculateRace(GameCharacter target) {

		// Every time race is calculated, it's because parts have changed, so reset the body parts list:
		handleAllBodyPartsList();
		
//		if(target!=null) {
//			target.removeStatusEffect(StatusEffect.SUBSPECIES_BONUS);
//		}
		
		AbstractRace race = Race.HUMAN;
		if(this.getBodyMaterial()==BodyMaterial.SLIME) {
			race = Race.SLIME;
			this.raceStage = RaceStage.GREATER;
			
		} else if(this.getBodyMaterial()==BodyMaterial.SILICONE) {
			race = Race.DOLL;
			this.raceStage = RaceStage.GREATER;
			
		} else if(target!=null && target.isElemental()) {
			race = Race.ELEMENTAL;
			this.raceStage = RaceStage.GREATER;
			
		} else {
			race = getRaceFromPartWeighting();
			this.raceStage = getRaceStageFromPartWeighting();
		}
		
		subspecies = AbstractSubspecies.getSubspeciesFromBody(this, race);
//		boolean overrideSubspecies = false;

		halfDemonSubspecies = null; // reset so it will be recalculated when accessed

		if(subspecies.getSubspeciesOverridePriority()>0 && (this.getSubspeciesOverride()==null || subspecies.getSubspeciesOverridePriority()>=this.getSubspeciesOverride().getSubspeciesOverridePriority())) {
			this.setSubspeciesOverride(subspecies);
		}
		
//		switch(subspecies) {
//			case IMP:
//				overrideSubspecies = this.getSubspeciesOverride()!=Subspecies.IMP_ALPHA
//						&& this.getSubspeciesOverride()!=Subspecies.HALF_DEMON
//						&& this.getSubspeciesOverride()!=Subspecies.DEMON
//						&& this.getSubspeciesOverride()!=Subspecies.LILIN
//						&& this.getSubspeciesOverride()!=Subspecies.ELDER_LILIN;
//				break;
//			case IMP_ALPHA:
//				overrideSubspecies = this.getSubspeciesOverride()!=Subspecies.HALF_DEMON
//						&& this.getSubspeciesOverride()!=Subspecies.DEMON
//						&& this.getSubspeciesOverride()!=Subspecies.LILIN
//						&& this.getSubspeciesOverride()!=Subspecies.ELDER_LILIN;
//				break;
//			case HALF_DEMON:
//				overrideSubspecies = this.getSubspeciesOverride()!=Subspecies.DEMON
//						&& this.getSubspeciesOverride()!=Subspecies.LILIN
//						&& this.getSubspeciesOverride()!=Subspecies.ELDER_LILIN;
//				break;
//			case DEMON:
//				overrideSubspecies = this.getSubspeciesOverride()!=Subspecies.LILIN
//						&& this.getSubspeciesOverride()!=Subspecies.ELDER_LILIN;
//				break;
//			case ELDER_LILIN:
//			case LILIN:
//				overrideSubspecies = true;
//				break;
//			default:
//				break;
//		}
//		
//		if(overrideSubspecies) {
//			this.setSubspeciesOverride(subspecies);
//		}
		
//		if(target!=null && target.getBody()!=null && Main.game.isStarted()) { // Apparently this is needed to stop Lyssieth from losing her status effect???
//			target.addStatusEffect(StatusEffect.SUBSPECIES_BONUS, -1);
//		}
	}

	public AbstractRace getRaceFromPartWeighting() {
		return getRaceFromPartWeighting(false);
	}
	
	public AbstractRace getRaceFromPartWeighting(boolean ignoreOverride) {
		AbstractRace race = Race.HUMAN;
		
		raceWeightMap.clear();
		
		addRaceWeight(raceWeightMap, torso.getType().getRace(), 4);
		addRaceWeight(raceWeightMap, face.getType().getRace(), 4);
		
		addRaceWeight(raceWeightMap, arm.getType().getRace(), 3);
		addRaceWeight(raceWeightMap, leg.getType().getRace(), 3);

		addRaceWeight(raceWeightMap, antenna.getType().getRace(), 1);
		addRaceWeight(raceWeightMap, eye.getType().getRace(), 1);
		addRaceWeight(raceWeightMap, ear.getType().getRace(), 1);
		addRaceWeight(raceWeightMap, hair.getType().getRace(), 1);
		addRaceWeight(raceWeightMap, tail.getType().getRace(), 1);
		addRaceWeight(raceWeightMap, wing.getType().getRace(), 1);
		addRaceWeight(raceWeightMap, horn.getType().getRace(), 1);
		
		// Best to leave this out...
		// Breast, ass, penis, and vagina have very low weighting so that the more visible parts of a character's body are counted more towards their subspecies
//		addRaceWeight(raceWeightMap, breast.getType().getRace(), 1);
//		addRaceWeight(raceWeightMap, breastCrotch.getType().getRace(), 1);
//		addRaceWeight(raceWeightMap, ass.getType().getRace(), 1);
//		addRaceWeight(raceWeightMap, penis.getType().getRace(), 1);
//		addRaceWeight(raceWeightMap, vagina.getType().getRace(), 1);
		
		raceWeightMap.remove(Race.NONE);
		
		int max = 0;
		boolean demonPartFound = false;
		
		for(Entry<AbstractRace, Integer> e : raceWeightMap.entrySet()) {
			if(e.getKey()!=null && e.getKey()==Race.DEMON) {
				demonPartFound = true;
				
			} else if(e.getKey()!=null && e.getKey()!=Race.HUMAN && e.getValue()>max) {
				race = e.getKey();
				max = e.getValue();
			}
		}
		if(!ignoreOverride && demonPartFound) { // Just one demon part is enough to make any character a demon:
			return Race.DEMON;
		}
		
		return race;
	}
	
	public RaceStage getRaceStageFromPartWeighting() {
		if(this.isFeral()) {
			return RaceStage.FERAL;
		}
		if(raceWeightMap.containsKey(Race.DEMON) && !raceWeightMap.containsKey(Race.HUMAN) && raceWeightMap.size()==2) {
			return RaceStage.GREATER;
		}
		if(raceWeightMap.size()==1) {
			if(raceWeightMap.containsKey(Race.HUMAN)) {
				return RaceStage.HUMAN;
			} else {
				return RaceStage.GREATER;
			}
			
		} else {
			return RaceStage.LESSER;
		}
	}

	public Map<AbstractRace, Integer> getRaceWeightMap() {
		return raceWeightMap;
	}
	
	public AbstractRace getRace() {
		if(subspecies == null) {
			calculateRace(null);
		}
		return subspecies.getRace();
	}

	/**
	 * @return This body's true race. If this body does not have a subspecies override, this will be the same as getRace(). If they do have an override, however, it will return the race of that override.
	 */
	public AbstractRace getTrueRace() {
		if(this.getSubspeciesOverride()!=null) {
			return this.getSubspeciesOverride().getRace();
		}
		return getRace();
	}
	
	public AbstractSubspecies getSubspecies() {
		return subspecies;
	}
	
	public AbstractSubspecies getLoadedSubspecies() {
		if(loadedSubspecies==null) {
			return getSubspecies();
		}
		return loadedSubspecies;
	}

	/**
	 * @return This body's true subspecies. If this body does not have a subspecies override, this will be the same as getSubspecies(). If they do have an override, however, it will return that override.
	 */
	public AbstractSubspecies getTrueSubspecies() {
		if(this.getSubspeciesOverride()!=null) {
			return this.getSubspeciesOverride();
		}
		return getSubspecies();
	}
	
	public RaceStage getRaceStage() {
		return raceStage;
	}

	public AbstractSubspecies getSubspeciesOverride() {
		return subspeciesOverride;
	}

	public void setSubspeciesOverride(AbstractSubspecies subspeciesOverride) {
		this.subspeciesOverride = subspeciesOverride;
	}

	public AbstractSubspecies getHalfDemonSubspecies() {
		if (halfDemonSubspecies == null) {
			halfDemonSubspecies = AbstractSubspecies.getSubspeciesFromBody(this, getRaceFromPartWeighting(true));
		}
		return halfDemonSubspecies;
	}
	
	public boolean isDoll() {
		return this.getRace()==Race.DOLL;
	}

	public Antenna getAntenna() {
		return antenna;
	}

	public Arm getArm() {
		return arm;
	}

	public AbstractArmType getArmType() {
		return arm.getType();
	}

	public Ass getAss() {
		return ass;
	}

	public AbstractAssType getAssType() {
		return ass.getType();
	}
	
	public Breast getBreast() {
		return breast;
	}

	public AbstractBreastType getBreastType() {
		return breast.getType();
	}
	
	public boolean hasBreasts() {
		return getBreastType()!=BreastType.NONE;
	}
	
	public BreastCrotch getBreastCrotch() {
		return breastCrotch;
	}

	public AbstractBreastType getBreastCrotchType() {
		return breastCrotch.getType();
	}
	
	public boolean hasBreastsCrotch() {
		return getBreastCrotchType()!=BreastType.NONE;
	}
	
	public Face getFace() {
		return face;
	}

	public AbstractFaceType getFaceType() {
		return face.getType();
	}
	
	public Eye getEye() {
		return eye;
	}

	public AbstractEyeType getEyeType() {
		return eye.getType();
	}

	public Ear getEar() {
		return ear;
	}

	public AbstractEarType getEarType() {
		return ear.getType();
	}

	public Hair getHair() {
		return hair;
	}

	public AbstractHairType getHairType() {
		return hair.getType();
	}

	public Horn getHorn() {
		return horn;
	}

	public AbstractHornType getHornType() {
		return horn.getType();
	}

	public boolean hasGenericHorns() {
		return getHorn().getType().isGeneric();
	}
	
	public Leg getLeg() {
		return leg;
	}

	public AbstractLegType getLegType() {
		return leg.getType();
	}

	public LegConfiguration getLegConfiguration() {
		return leg.getLegConfiguration();
	}

	public boolean isTaur() {
		return !getLegConfiguration().isBipedalPositionedGenitals();
	}
	
	public Penis getPenis() {
		return penis;
	}

	public AbstractPenisType getPenisType() {
		return penis.getType();
	}

	public boolean hasPenis() {
		return penis.getType() != PenisType.NONE;
	}

	public boolean hasPenisIgnoreDildo() {
		return hasPenis() && penis.getType() != PenisType.DILDO;
	}

	public boolean hasPenisIgnoresDildo() {
		return hasPenisIgnoreDildo();
	}
	
	public OrificeSpinneret getSpinneret() {
		return spinneret;
	}
	
	public boolean hasTailSpinneret() {
		return getTailType().hasSpinneret();
	}
	
	public boolean hasLegSpinneret() {
		return getLegConfiguration()==LegConfiguration.ARACHNID && getLegType().hasSpinneret();
	}
	
	public boolean hasSpinneret() {
		return hasTailSpinneret() || hasLegSpinneret();
	}

	public Torso getTorso() {
		return torso;
	}

	public AbstractTorsoType getTorsoType() {
		return torso.getType();
	}

	public Tail getTail() {
		return tail;
	}

	public AbstractTailType getTailType() {
		return tail.getType();
	}

	public Tentacle getTentacle() {
		return tentacle;
	}

	public AbstractTongueType getTongueType() {
		return face.getTongue().getType();
	}

	public Vagina getVagina() {
		return vagina;
	}

	public AbstractVaginaType getVaginaType() {
		return vagina.getType();
	}

	public boolean hasVagina() {
		return vagina.getType() != VaginaType.NONE;
	}

	public boolean hasVaginaIgnoreOnahole() {
		return hasVagina() && vagina.getType() != VaginaType.ONAHOLE;
	}
	
	public Wing getWing() {
		return wing;
	}

	public int getWingSizeValue() {
		return wing.getSizeValue();
	}

	public int getMinimumWingSizeValueForFlight() {
		return leg.getLegConfiguration().getMinimumWingSizeForFlight(this).getValue();
	}

	public AbstractWingType getWingType() {
		return wing.getType();
	}

	public void setAntenna(Antenna antenna) {
		this.antenna = antenna;
	}

	public void setArm(Arm arm) {
		this.arm = arm;
	}

	public String setArmType(AbstractArmType type) {
		return this.arm.setType(null, type);
	}

	public String setArmType(GameCharacter owner, AbstractArmType type) {
		return this.arm.setType(owner, type);
	}

	public String setArmRows(int armRows) {
		return this.arm.setArmRows(null, armRows);
	}

	public String setArmRows(GameCharacter owner, int armRows) {
		return this.arm.setArmRows(owner, armRows);
	}

	public void setAss(Ass ass) {
		this.ass = ass;
	}

	public String setAssType(GameCharacter owner, AbstractAssType type) {
		return this.ass.setType(owner, type);
	}
	
	public void setBreast(Breast breast) {
		this.breast = breast;
	}

	public String setBreastType(GameCharacter owner, AbstractBreastType type) {
		return this.breast.setType(owner, type);
	}

	public void setBreastCrotch(BreastCrotch breastCrotch) {
		this.breastCrotch = breastCrotch;
	}

	public String setBreastCrotchType(GameCharacter owner, AbstractBreastType type) {
		return this.breastCrotch.setType(owner, type);
	}

	public void setFace(Face face) {
		this.face = face;
	}

	public String setFaceType(AbstractFaceType type) {
		return face.setType(null, type);
	}

	public String setFaceType(GameCharacter owner, AbstractFaceType type) {
		return face.setType(owner, type);
	}

	public void setEye(Eye eye) {
		this.eye = eye;
	}

	public String setEyeType(GameCharacter owner, AbstractEyeType type) {
		return this.eye.setType(owner, type);
	}

	public void setEar(Ear ear) {
		this.ear = ear;
	}

	public String setEarType(AbstractEarType type) {
		return this.ear.setType(null, type);
	}

	public String setEarType(GameCharacter owner, AbstractEarType type) {
		return this.ear.setType(owner, type);
	}

	public void setHair(Hair hair) {
		this.hair = hair;
	}

	public String setHairType(AbstractHairType type) {
		return this.hair.setType(null, type);
	}

	public String setHairType(GameCharacter owner, AbstractHairType type) {
		return this.hair.setType(owner, type);
	}

	public void setLeg(Leg leg) {
		this.leg = leg;
	}

	public String setLegType(AbstractLegType type) {
		return this.leg.setType(null, type);
	}

	public String setLegType(GameCharacter owner, AbstractLegType type) {
		return this.leg.setType(owner, type);
	}

	public void setLegConfigurationForced(AbstractLegType type, LegConfiguration legConfiguration) {
		this.leg.setLegConfigurationForced(type, legConfiguration);
	}

	public void setTongueType(AbstractTongueType type) {
		this.face.getTongue().setType(type);
	}

	public void setTorso(Torso torso) {
		this.torso = torso;
	}
	
	public String setTorsoType(AbstractTorsoType type) {
		return this.torso.setType(null, type);
	}

	public String setTorsoType(GameCharacter owner, AbstractTorsoType type) {
		return this.torso.setType(owner, type);
	}

	public void setHorn(Horn horn) {
		this.horn = horn;
	}

	public String setHornType(AbstractHornType type) {
		return this.horn.setType(null, type);
	}

	public String setHornType(GameCharacter owner, AbstractHornType type) {
		return this.horn.setType(owner, type);
	}

	public void setPenis(Penis penis) {
		this.penis = penis;
	}

	public String setPenisType(AbstractPenisType type) {
		return this.penis.setType(null, type);
	}

	public String setPenisType(GameCharacter owner, AbstractPenisType type) {
		return this.penis.setType(owner, type);
	}

	public String addPenisModifier(PenetrationModifier modifier) {
		return this.penis.addPenisModifier(null, modifier);
	}

	public String addPenisModifier(GameCharacter owner, PenetrationModifier modifier) {
		return this.penis.addPenisModifier(owner, modifier);
	}

	public void setTail(Tail tail) {
		this.tail = tail;
	}

	public String setTailType(AbstractTailType type) {
		return this.tail.setType(null, type);
	}

	public String setTailType(GameCharacter owner, AbstractTailType type) {
		return this.tail.setType(owner, type);
	}

	public void setTentacle(Tentacle tentacle) {
		this.tentacle = tentacle;
	}

	public void setVagina(Vagina vagina) {
		this.vagina = vagina;
	}

	public String setVaginaType(AbstractVaginaType type) {
		return this.vagina.setType(null, type);
	}

	public String setVaginaType(GameCharacter owner, AbstractVaginaType type) {
		return this.vagina.setType(owner, type);
	}

	public void setWing(Wing wing) {
		this.wing = wing;
	}

	public String setWingSize(int size) {
		return this.wing.setSize(null, size);
	}

	public String setWingSize(GameCharacter owner, int size) {
		return this.wing.setSize(owner, size);
	}

	public String setWingSizeToMinimumWingSizeForFlight() {
		return wing.setSize(null, getMinimumWingSizeValueForFlight());
	}

	public String setWingSizeToMinimumWingSizeForFlight(GameCharacter owner) {
		return wing.setSize(owner, getMinimumWingSizeValueForFlight());
	}

	public String setWingType(AbstractWingType type) {
		return this.wing.setType(null, type);
	}

	public String setWingType(GameCharacter owner, AbstractWingType type) {
		return this.wing.setType(owner, type);
	}

	public void applyLegConfigurationTransformation(AbstractLegType legType, LegConfiguration legConfiguration, boolean applyFullEffects) {
		this.setLegType(legType);
		this.leg.getType().applyLegConfigurationTransformation(this, legConfiguration, applyFullEffects);
	}

	public Boolean hasTongueModifier(TongueModifier modifier) {
		return face.getTongue().hasTongueModifier(modifier);
	}

	public String addTongueModifier(TongueModifier modifier) {
		return addTongueModifier(null, modifier);
	}

	public String addTongueModifier(GameCharacter owner, TongueModifier modifier) {
		return face.getTongue().addTongueModifier(owner, modifier);
	}

	public void resetTongueModifiers() {
		face.getTongue().resetTongueModifiers();
	}

	public boolean hasWings() {
		return getWingType() != WingType.NONE;
	}
	
	public boolean hasGenericWings() {
		return getWingType().isGeneric();
	}

	public boolean isFaceHuman() {
		return face.getType().getRace() == Race.HUMAN;
	}

	// Descriptions:
	private StringBuilder descriptionSB;

	public String getEyeDescription(GameCharacter owner) {
		StringBuilder sb = new StringBuilder();
		
		if(owner.isFeral()) {
			sb.append(" [npc.SheHasFull] [npc.eyePairs] [npc.eyeRace] eyes, with [npc.irisShape], [npc.irisColour(true)] irises, [npc.pupilShape], [npc.pupilColour(true)] pupils, and [npc.scleraColour(true)] sclerae.");
		} else {
			sb.append(" "+eye.getType().getBodyDescription(owner));
		}
		
		// Eye makeup:
		if(owner.getEyeLiner().getPrimaryColour()!=PresetColour.COVERING_NONE) {
			sb.append(" Around [npc.her] [npc.eyes], [npc.sheHas] got a layer of "+owner.getEyeLiner().getColourDescriptor(owner, true, false)+" eye liner.");
		}
		if(owner.getEyeShadow().getPrimaryColour()!=PresetColour.COVERING_NONE) {
			sb.append(" [npc.SheIs] wearing a tasteful amount of "+owner.getEyeShadow().getFullDescription(owner, true)+".");
		}
		
		return sb.toString();
	}
	
	/**
	 * @param owner The person whose ass is to be described.
	 * @param locationSpecific Whether this description is specific to looking at the person's ass. If they have a cloaca, and you pass in true, it will say something along the lines of "there's no asshole here".
	 * @return A description of this character's asshole.
	 */
	public String getAssDescription(GameCharacter owner, boolean locationSpecific) {
		descriptionSB = new StringBuilder();
		
		switch(owner.getGenitalArrangement()) {
			case CLOACA:
				if(locationSpecific) {
					return UtilText.parse(owner, "[style.italicsFeral([npc.Her] asshole is not located between [npc.her] ass cheeks, and is instead positioned within [npc.her] front-facing cloaca!)]");
				} else {
					descriptionSB.append(UtilText.parse(owner, "[style.italicsFeral([npc.Her] asshole is located within [npc.her] slit-like, front-facing cloaca.)] "));
				}
				break;
			case CLOACA_BEHIND:
				descriptionSB.append(UtilText.parse(owner, "[style.italicsFeral([npc.Her] asshole is located within [npc.her] slit-like, rear-facing cloaca.)] "));
				break;
			case NORMAL:
			break;
		}
		
		descriptionSB.append(ass.getType().getBodyDescription(owner).trim());
		
		// Colour:
		if(ass.getAnus().isBleached()) {
			descriptionSB.append(", which has been bleached so that the rim is no darker than the [npc.assSkin] around it.");
		} else {
			descriptionSB.append(", the rim being slightly darker than the [npc.assSkin] around it.");
		}
		
		if(owner.isFeral()) {
			descriptionSB.append(" [style.colourFeral(As is to be expected, it is entirely feral in form, and is no different to that of a normal [npc.assRace]'s.)]");
		} else if(ass.isFeral(owner)) {
			descriptionSB.append(" [style.colourFeral(Being a part of [npc.her] feral lower body, it is entirely feral in form, and is no different to that of a feral [npc.assRace]'s.)]");
		}

		descriptionSB.append(" It is "+Capacity.getCapacityFromValue(ass.getAnus().getOrificeAnus().getStretchedCapacity()).getDescriptor(true)+", and when lubricated can comfortably accommodate objects of up to"
				+ " [style.colourSex("+ Units.size(Capacity.getMaximumComfortableDiameter(ass.getAnus().getOrificeAnus().getElasticity(), ass.getAnus().getOrificeAnus().getRawCapacityValue(), true)) + ")] in diameter.");

		if(Main.game.isPenetrationLimitationsEnabled()) {
			switch(owner.getAssDepth()) {
				default:
					descriptionSB.append(" [npc.Her] ass is <span style='color:"+owner.getAssDepth().getColour().toWebHexString()+";'>[npc.assDepth]</span>,");
					break;
				case TWO_AVERAGE:
					descriptionSB.append(" [npc.Her] ass is of an <span style='color:"+owner.getAssDepth().getColour().toWebHexString()+";'>average depth</span>,");
					break;
			}
			if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
				if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
					descriptionSB.append(" and as [npc.sheIsFull] a "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", [npc.she] can be pushed to [style.colourMinorGood(comfortably)] accommodate objects of a maximum length of "
							+ " [style.colourSex("+Units.size(owner.getAssMaximumPenetrationDepthUncomfortable())+")].");
				} else {
					descriptionSB.append(" allowing [npc.herHim] to [style.colourMinorGood(comfortably)] accommodate [style.colourSex("+Units.size(owner.getAssMaximumPenetrationDepthComfortable())+")] of a penetrative object,"
							+ " and [style.colourMinorBad(uncomfortably)] accommodate [style.colourSex("+Units.size(owner.getAssMaximumPenetrationDepthUncomfortable())+")].");
				}
				
			} else {
				if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
					descriptionSB.append(" and as [npc.sheIsFull] a "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", combined with the fact that [npc.her] body is made out of [npc.bodyMaterial],"
							+ " [npc.she] can be pushed to [style.colourMinorGood(comfortably)] accommodate objects of [style.colourSex(any length)].");
				} else {
					descriptionSB.append(" allowing [npc.herHim] to [style.colourMinorGood(comfortably)] accommodate [style.colourSex("+Units.size(owner.getAssMaximumPenetrationDepthComfortable())+")] of a penetrative object,"
							+ " and as [npc.her] body is made out of [npc.bodyMaterial], [npc.she] can be pushed to [style.colourMinorBad(uncomfortably)] accommodate objects of [style.colourSex(any length)].");
				}
			}
		}
		
		if (ass.getAnus().getOrificeAnus().isVirgin()) {
			descriptionSB.append(" [style.colourExcellent([npc.Name] [npc.has] retained [npc.her] anal virginity.)]");
			
		} else {
			boolean virginityLossFound = false;
			for(SexAreaPenetration pt : SexAreaPenetration.values()) {
				if(pt.isTakesVirginity()) {
					if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, pt))!=null) {
						descriptionSB.append(" <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, pt)) + "</span>");
						virginityLossFound = true;
						break;
					}
				}
			}
			if(!virginityLossFound) {
				descriptionSB.append(" <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Name] [npc.has] lost [npc.her] anal virginity.</span>");
			}
		}
		
		// Ass wetness:
		switch (ass.getAnus().getOrificeAnus().getWetness(owner)) {
			case ZERO_DRY:
				descriptionSB.append(" It is [style.colourWetness(completely dry)], and would need lubricating before sex.");
				break;
			case ONE_SLIGHTLY_MOIST:
				descriptionSB.append(" It is [style.colourWetness(slightly moist)], but would still need lubricating before sex.");
				break;
			case TWO_MOIST:
				descriptionSB.append(" It is [style.colourWetness(moist)], but would still need lubricating before sex.");
				break;
			case THREE_WET:
				descriptionSB.append(" Its [style.colourWetness(surface constantly beads with wet droplets)], which provides enough natural lubrication for sex.");
				break;
			case FOUR_SLIMY:
				descriptionSB.append(" Its surface is always [style.colourWetness(wet)] and ready for penetration.");
				break;
			case FIVE_SLOPPY:
				descriptionSB.append(" Its surface is always [style.colourWetness(wet and slimy)], presenting a well-lubricated orifice for penetration.");
				break;
			case SIX_SOPPING_WET:
				descriptionSB.append(" Its surface is never less than [style.colourWetness(sopping wet)] from natural lubrication, presenting a well-lubricated orifice for penetration.");
				break;
			case SEVEN_DROOLING:
				descriptionSB.append(" It constantly  [style.colourWetness(drools)] with natural lubrication, and its [style.colourWetness(sopping wet)] entrance is always ready for penetration.");
				break;
		}
		// Ass elasticity & plasticity:
		switch (ass.getAnus().getOrificeAnus().getElasticity()) {
			case ZERO_UNYIELDING:
				descriptionSB.append(" It is [style.colourElasticity(extremely unyielding)],");
				break;
			case ONE_RIGID:
				descriptionSB.append(" It [style.colourElasticity(takes a huge amount of effort to stretch it out)],");
				break;
			case TWO_FIRM:
				descriptionSB.append(" It [style.colourElasticity(does not stretch very easily)],");
				break;
			case THREE_FLEXIBLE:
				descriptionSB.append(" It [style.colourElasticity(reluctantly stretches out)] when used as a sexual orifice,");
				break;
			case FOUR_LIMBER:
				descriptionSB.append(" It is [style.colourElasticity(somewhat resistant to being stretched out)],");
				break;
			case FIVE_STRETCHY:
				descriptionSB.append(" It [style.colourElasticity(stretches out fairly easily)],");
				break;
			case SIX_SUPPLE:
				descriptionSB.append(" It [style.colourElasticity(stretches out very easily)],");
				break;
			case SEVEN_ELASTIC:
				descriptionSB.append(" It is [style.colourElasticity(extremely elastic)],");
				break;
		}
		descriptionSB.append(" and after being used, it "+ass.getAnus().getOrificeAnus().getPlasticity().getDescription()+".");
		
		if(Main.game.isAssHairEnabled()) {
			switch(ass.getAnus().getAssHair()) {
				case ZERO_NONE:
					descriptionSB.append(" There is no trace of any "+owner.getAssHairType().getName(owner)+" around [npc.her] asshole.");
					break;
				case ONE_STUBBLE:
					descriptionSB.append(" [npc.She] [npc.has] a few strands of "+owner.getAssHairType().getFullDescription(owner, true)+" around [npc.her] asshole.");
					break;
				case TWO_MANICURED:
					descriptionSB.append(" [npc.She] [npc.has] a very small amount of "+owner.getAssHairType().getFullDescription(owner, true)+" around [npc.her] asshole.");
					break;
				case THREE_TRIMMED:
					descriptionSB.append(" [npc.She] [npc.has] a small amount of "+owner.getAssHairType().getFullDescription(owner, true)+" around [npc.her] asshole.");
					break;
				case FOUR_NATURAL:
					descriptionSB.append(" [npc.She] [npc.has] a natural amount of "+owner.getAssHairType().getFullDescription(owner, true)+" around [npc.her] asshole.");
					break;
				case FIVE_UNKEMPT:
					descriptionSB.append(" [npc.She] [npc.has] an unkempt mass of "+owner.getAssHairType().getFullDescription(owner, true)+" around [npc.her] asshole.");
					break;
				case SIX_BUSHY:
					descriptionSB.append(" [npc.She] [npc.has] a thick, bushy mass of "+owner.getAssHairType().getFullDescription(owner, true)+" around [npc.her] asshole.");
					break;
				case SEVEN_WILD:
					descriptionSB.append(" [npc.She] [npc.has] a wild, bushy mass of "+owner.getAssHairType().getFullDescription(owner, true)+" around [npc.her] asshole.");
					break;
			}
		}
		
		for(OrificeModifier om : OrificeModifier.values()) {
			if(owner.hasAssOrificeModifier(om)) {
				switch(om) {
					case MUSCLE_CONTROL:
						descriptionSB.append(" [npc.She] [npc.has] a series of internal muscles lining the inside of [npc.her] [npc.asshole], allowing [npc.herHim] to expertly squeeze and grip down on any intruding object.");
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
		
		return UtilText.parse(owner, descriptionSB.toString());
	}

	public String getBreastDescription(GameCharacter owner) {
		return getBreastDescription(owner, breast);
	}
	
	public String getBreastDescription(GameCharacter owner, Breast viewedBreast) {
		descriptionSB = new StringBuilder();
		
		boolean playerKnowledgeOfBreasts = owner.isAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer());
		
		if(!owner.isPlayer() && !playerKnowledgeOfBreasts) {
			descriptionSB.append("[style.colourDisabled(You've never seen [npc.her] naked chest, so you don't know what [npc.her] nipples look like.)]");
			
		} else {
			descriptionSB.append("On each of [npc.her] "+(owner.hasBreasts()?owner.getBreastShape().getDescriptor()+" breasts":"pecs")+", [npc.she] [npc.has] "+Util.intToString(owner.getNippleCountPerBreast())+" [npc.nippleSize], ");
			
			switch(owner.getNippleShape()) {
				case NORMAL:
					descriptionSB.append(" [npc.nipplePrimaryColour(true)]");
					break;
				case INVERTED:
					descriptionSB.append(" [npc.nipplePrimaryColour(true)], inverted");
					break;
				case LIPS:
					descriptionSB.append(" [npc.nipplePrimaryColour(true)]-lipped");
					break;
				case VAGINA:
					descriptionSB.append(" [npc.nipplePrimaryColour(true)]-lipped");
					break;
			}
			if(owner.getNippleCountPerBreast()>1) {
				descriptionSB.append(" [npc.nipples],");
			} else {
				descriptionSB.append(" [npc.nipple(true)],");
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

			if(breast.isFeral(owner)) {
				descriptionSB.append(" [style.colourFeral(Due to [npc.her] entire body being feral in form, [npc.her] [npc.nipples] are identical to those of a feral [npc.breastRace]'s.)]");
			}
			
			if(owner.isPiercedNipple()) {
				descriptionSB.append(" They have been pierced.");
			}
			
			if(owner.getNippleCapacity() != Capacity.ZERO_IMPENETRABLE && Main.game.isNipplePenEnabled()) {
				if (viewedBreast.isFuckable()) {
					descriptionSB.append("<br/>[npc.Her] [npc.breasts] have internal, [npc.nippleSecondaryColour(true)] channels, allowing [npc.her] [npc.breastCapacity] [npc.nipples] to be comfortably penetrated by objects of"
							+ " [style.colourSex("+ Units.size(Capacity.getCapacityFromValue(viewedBreast.getNipples().getOrificeNipples().getStretchedCapacity()).getMaximumValue(true)) + ")] in diameter when lubricated.");

					if(Main.game.isPenetrationLimitationsEnabled()) {
						switch(owner.getNippleDepth()) {
							default:
								descriptionSB.append(" [npc.Her] fuckable [npc.nipples] are <span style='color:"+owner.getNippleDepth().getColour().toWebHexString()+";'>[npc.breastDepth]</span>,");
								break;
							case TWO_AVERAGE:
								descriptionSB.append(" [npc.Her] fuckable [npc.nipples] are of an <span style='color:"+owner.getNippleDepth().getColour().toWebHexString()+";'>average depth</span>,");
								break;
						}
						if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
							if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
								descriptionSB.append(" and as [npc.sheIsFull] a "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", [npc.she] can be pushed to [style.colourMinorGood(comfortably)] accommodate objects of a maximum length of "
										+ " [style.colourSex("+Units.size(owner.getNippleMaximumPenetrationDepthUncomfortable())+")].");
							} else {
								descriptionSB.append(" allowing [npc.herHim] to [style.colourMinorGood(comfortably)] accommodate [style.colourSex("+Units.size(owner.getNippleMaximumPenetrationDepthComfortable())+")] of a penetrative object,"
										+ " and [style.colourMinorBad(uncomfortably)] accommodate [style.colourSex("+Units.size(owner.getNippleMaximumPenetrationDepthUncomfortable())+")].");
							}
							
						} else {
							if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
								descriptionSB.append(" and as [npc.sheIsFull] a "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", combined with the fact that [npc.her] body is made out of [npc.bodyMaterial],"
										+ " [npc.she] can be pushed to [style.colourMinorGood(comfortably)] accommodate objects of [style.colourSex(any length)].");
							} else {
								descriptionSB.append(" allowing [npc.herHim] to [style.colourMinorGood(comfortably)] accommodate [style.colourSex("+Units.size(owner.getNippleMaximumPenetrationDepthComfortable())+")] of a penetrative object,"
										+ " and as [npc.her] body is made out of [npc.bodyMaterial], [npc.she] can be pushed to [style.colourMinorBad(uncomfortably)] accommodate objects of [style.colourSex(any length)].");
							}
						}
					}
					
				} else {
					descriptionSB.append("<br/>[npc.Her] [npc.breasts] have internal, [npc.nippleSecondaryColour(true)] channels,"
							+ " but [style.colourBad(they need to be at least C-cups)] before [npc.her] "
							+ Capacity.getCapacityFromValue(viewedBreast.getNipples().getOrificeNipples().getStretchedCapacity()).getDescriptor(true)+" [npc.nipples] could be penetrated.");
				}

				// Nipple elasticity & plasticity:
				switch (viewedBreast.getNipples().getOrificeNipples().getElasticity()) {
					case ZERO_UNYIELDING:
						descriptionSB.append(" They are [style.colourElasticity(extremely unyielding)],");
						break;
					case ONE_RIGID:
						descriptionSB.append(" It [style.colourElasticity(takes a huge amount of effort to stretch them out)],");
						break;
					case TWO_FIRM:
						descriptionSB.append(" They [style.colourElasticity(does not stretch very easily)],");
						break;
					case THREE_FLEXIBLE:
						descriptionSB.append(" They [style.colourElasticity(reluctantly stretch out)] when used as a sexual orifice,");
						break;
					case FOUR_LIMBER:
						descriptionSB.append(" They are [style.colourElasticity(somewhat resistant to being stretched out)],");
						break;
					case FIVE_STRETCHY:
						descriptionSB.append(" They [style.colourElasticity(stretch out fairly easily)],");
						break;
					case SIX_SUPPLE:
						descriptionSB.append(" They [style.colourElasticity(stretch out very easily)],");
						break;
					case SEVEN_ELASTIC:
						descriptionSB.append(" They are [style.colourElasticity(extremely elastic)],");
						break;
				}
				descriptionSB.append(" and after being used, they "+viewedBreast.getNipples().getOrificeNipples().getPlasticity().getDescriptionPlural()+".");
				
				for(OrificeModifier om : OrificeModifier.values()) {
					if(owner.hasNippleOrificeModifier(om)) {
						switch(om) {
							case MUSCLE_CONTROL:
								descriptionSB.append(" [npc.She] [npc.has] a series of muscles lining the insides of [npc.her] [npc.nipples], allowing [npc.herHim] to expertly squeeze and grip down on any intruding object.");
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
				
				if (!viewedBreast.getNipples().getOrificeNipples().isVirgin()) {
					boolean virginityLossFound = false;
					for(SexAreaPenetration pt : SexAreaPenetration.values()) {
						if(pt.isTakesVirginity()) {
							if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, pt))!=null) {
								descriptionSB.append(" <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, pt)) + "</span>");
								virginityLossFound = true;
								break;
							}
						}
					}
					if(!virginityLossFound) {
						descriptionSB.append(" <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Name] [npc.has] lost [npc.her] nipple virginity.</span>");
					}
					
				} else {
					descriptionSB.append(" [style.colourExcellent([npc.Name] [npc.has] retained [npc.her] nipple virginity.)]");
				}
				
			} else {
				if(owner.hasNippleOrificeModifier(OrificeModifier.PUFFY)) {
					descriptionSB.append(" [npc.Her] [npc.nipples] have swollen up to be exceptionally plump and puffy.");
				}	
			}
			
			if (viewedBreast.getRawMilkStorageValue() > 0) {
				descriptionSB.append("<br/>[npc.SheIsFull] currently producing "+ Units.fluid(viewedBreast.getRawMilkStorageValue(), Units.UnitType.LONG) + " of [npc.milkPrimaryColour(true)] [npc.milk]"
						+ " ("+ Units.fluid(viewedBreast.getRawStoredMilkValue()) + " currently stored) at [npc.a_milkRegen] rate.");
				
				switch(viewedBreast.getMilk().getFlavour()) {
					case MILK:
						descriptionSB.append(" [npc.Her] [npc.milkColour(true)] [npc.milk] tastes like regular milk.");
						break;
					case BUBBLEGUM:
						descriptionSB.append(" [npc.Her] [npc.milkColour(true)] [npc.milk] has the fruity taste of bubblegum.");
						break;
					case FLAVOURLESS:
						descriptionSB.append(" [npc.Her] [npc.milkColour(true)] [npc.milk] has absolutely no flavour whatsoever.");
						break;
					default:
						descriptionSB.append(" [npc.Her] [npc.milkColour(true)] [npc.milk] tastes exactly like "+viewedBreast.getMilk().getFlavour().getName()+".");
						break;
				}
				
				for(FluidModifier fm : FluidModifier.values()) {
					if(owner.hasMilkModifier(fm)) {
						descriptionSB.append(" " + fm.getBriefDescription());
					}
				}
				
			} else {
				descriptionSB.append("<br/>[npc.SheIsFull] not producing any milk.");
			}
		}

		return UtilText.parse(owner, descriptionSB.toString());
	}

	public String getBreastCrotchDescription(GameCharacter owner) {
		return getBreastCrotchDescription(owner, breastCrotch);
	}

	public String getBreastCrotchDescription(GameCharacter owner, BreastCrotch viewedBreastCrotch) {
		descriptionSB = new StringBuilder();
		
//		boolean playerKnowledgeOfUdders = owner.isAreaKnownByCharacter(CoverableArea.NIPPLES_CROTCH, Main.game.getPlayer());
//		
//		if(owner.isPlayer() || playerKnowledgeOfUdders) {
			if(owner.isFeral()) {
				if(owner.getLegConfiguration()==LegConfiguration.QUADRUPEDAL) {
					descriptionSB.append("Down beneath the groin of [npc.her] feral body,");
				} else {
					descriptionSB.append("Just above the groin of [npc.her] feral body,");
				}
			} else {
				descriptionSB.append(leg.getLegConfiguration().getCrotchBoobLocationDescription());
			}
			if(breastCrotch.getShape()==BreastShape.UDDERS) {
				if(breastCrotch.getRawSizeValue()>0){
					descriptionSB.append(" [npc.she] [npc.has] [npc.a_crotchBoobsSize]");
				} else {
					descriptionSB.append(" [npc.she] [npc.has] a completely flat");
				}
				if(breastCrotch.getRows()>0) {
					descriptionSB.append((breastCrotch.getRows()>1?" pairs":" pair")+" of udders.");
					
				} else {
					descriptionSB.append(" udder,");
				}
				
			} else {
				if(breastCrotch.getRawSizeValue()>0){
					descriptionSB.append(" [npc.she] [npc.has] [npc.crotchBoobsRows] [npc.crotchBoobsSize], [npc.crotchBoobsCups]-cup [npc.crotchBoobs]");
				} else {
					descriptionSB.append(" [npc.she] [npc.has] [npc.crotchBoobsRows] completely flat [npc.crotchBoobs]");
				}
				if(leg.getLegConfiguration().isBipedalPositionedCrotchBoobs() && breastCrotch.getRows()>1) {
					descriptionSB.append(", with the upper pair"+(breastCrotch.getRows()>2?"s":"")+" being positioned higher up on [npc.her] stomach.");
					
				} else if(breastCrotch.getRows()>1) {
					descriptionSB.append(", with the extra pair"+(breastCrotch.getRows()>2?"s":"")+" being positioned further towards [npc.her] underbelly.");
					
				} else {
					descriptionSB.append(".");
				}
			}
			
			if(viewedBreastCrotch.getShape()==BreastShape.UDDERS) {
				if(breastCrotch.getRows()>0) {
					descriptionSB.append(" They are formed into [npc.totalCrotchBoobs] protrusions, upon each of which [npc.she] [npc.has] [npc.crotchBoobsNipplesPerBreast] [npc.crotchNippleSize], ");
				} else {
					descriptionSB.append(" upon which [npc.sheHasFull] [npc.crotchBoobsNipplesPerBreast] [npc.crotchNippleSize], ");
				}
			} else {
				descriptionSB.append(" On each of [npc.her] [npc.totalCrotchBoobs] [npc.crotchBoobs], [npc.she] [npc.has] [npc.crotchBoobsNipplesPerBreast] [npc.crotchNippleSize], ");
			}
			
			switch(owner.getNippleCrotchShape()) {
				case NORMAL:
					descriptionSB.append(" [npc.crotchNipplePrimaryColour(true)]");
					break;
				case INVERTED:
					descriptionSB.append(" [npc.crotchNipplePrimaryColour(true)], inverted");
					break;
				case LIPS:
					descriptionSB.append(" [npc.crotchNipplePrimaryColour(true)]-lipped");
					break;
				case VAGINA:
					descriptionSB.append(" [npc.crotchNipplePrimaryColour(true)]-lipped");
					break;
			}
			if(owner.getNippleCrotchCountPerBreast()>1) {
				descriptionSB.append(" [npc.crotchNipples],");
			} else {
				descriptionSB.append(" [npc.crotchNipple(true)],");
			}
			
			switch(owner.getAreolaeCrotchShape()) {
				case NORMAL:
					descriptionSB.append(" with [npc.crotchBoobsAreolaSize], circular areolae.");
					break;
				case HEART:
					descriptionSB.append(" with [npc.crotchBoobsAreolaSize], heart-shaped areolae.");
					break;
				case STAR:
					descriptionSB.append(" with [npc.crotchBoobsAreolaSize], star-shaped areolae.");
					break;
			}
			if(owner.isFeral()) {
				descriptionSB.append(" [style.colourFeral(As [npc.sheHasFull] a fully feral body, [npc.her] [npc.crotchBoobs] are identical in form to those found on a normal [npc.crotchBoobRace].)]");
			} else if(breastCrotch.isFeral(owner)) {
				descriptionSB.append(" [style.colourFeral(As [npc.sheHasFull] the lower body of an animal, [npc.her] [npc.crotchBoobs] are identical in form to those found on a feral [npc.crotchBoobRace].)]");
			}
			
			if(owner.isPiercedNippleCrotch()) {
				descriptionSB.append(" They have been pierced.");
			}
			
			if(owner.getNippleCrotchCapacity() != Capacity.ZERO_IMPENETRABLE && Main.game.isNipplePenEnabled()) {
				if (viewedBreastCrotch.isFuckable()) {
					descriptionSB.append("<br/>[npc.Her] [npc.crotchBoobs] have internal, [npc.crotchNippleSecondaryColour(true)] channels, allowing [npc.her] [npc.crotchBoobsCapacity] [npc.crotchNipples] to be comfortably penetrated by objects of"
								+ " [style.colourSex("+ Units.size(Capacity.getCapacityFromValue(viewedBreastCrotch.getNipples().getOrificeNipples().getStretchedCapacity()).getMaximumValue(true)) + ")] in diameter when lubricated.");

					if(Main.game.isPenetrationLimitationsEnabled()) {
						switch(owner.getNippleCrotchDepth()) {
							default:
								descriptionSB.append(" [npc.Her] fuckable [npc.crotchNipples] are <span style='color:"+owner.getNippleCrotchDepth().getColour().toWebHexString()+";'>[npc.crotchBreastDepth]</span>,");
								break;
							case TWO_AVERAGE:
								descriptionSB.append(" [npc.Her] fuckable [npc.crotchNipples] are of an <span style='color:"+owner.getNippleCrotchDepth().getColour().toWebHexString()+";'>average depth</span>,");
								break;
						}
						if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
							if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
								descriptionSB.append(" and as [npc.sheIsFull] a "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", [npc.she] can be pushed to [style.colourMinorGood(comfortably)] accommodate objects of a maximum length of "
										+ " [style.colourSex("+Units.size(owner.getNippleCrotchMaximumPenetrationDepthUncomfortable())+")].");
							} else {
								descriptionSB.append(" allowing [npc.herHim] to [style.colourMinorGood(comfortably)] accommodate [style.colourSex("+Units.size(owner.getNippleCrotchMaximumPenetrationDepthComfortable())+")] of a penetrative object,"
										+ " and [style.colourMinorBad(uncomfortably)] accommodate [style.colourSex("+Units.size(owner.getNippleCrotchMaximumPenetrationDepthUncomfortable())+")].");
							}
							
						} else {
							if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
								descriptionSB.append(" and as [npc.sheIsFull] a "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", combined with the fact that [npc.her] body is made out of [npc.bodyMaterial],"
										+ " [npc.she] can be pushed to [style.colourMinorGood(comfortably)] accommodate objects of [style.colourSex(any length)].");
							} else {
								descriptionSB.append(" allowing [npc.herHim] to [style.colourMinorGood(comfortably)] accommodate [style.colourSex("+Units.size(owner.getNippleCrotchMaximumPenetrationDepthComfortable())+")] of a penetrative object,"
										+ " and as [npc.her] body is made out of [npc.bodyMaterial], [npc.she] can be pushed to [style.colourMinorBad(uncomfortably)] accommodate objects of [style.colourSex(any length)].");
							}
						}
					}
					
				} else {
					descriptionSB.append("<br/>[npc.Her] [npc.crotchBoobs] have internal, [npc.crotchNippleSecondaryColour(true)] channels,"
							+ " but [style.colourBad(they need to be at least C-cups)] before [npc.her] "
							+ Capacity.getCapacityFromValue(viewedBreastCrotch.getNipples().getOrificeNipples().getStretchedCapacity()).getDescriptor(true)+ " [npc.crotchNipples] could be penetrated.");
				}

				// Nipple elasticity & plasticity:
				switch (viewedBreastCrotch.getNipples().getOrificeNipples().getElasticity()) {
					case ZERO_UNYIELDING:
						descriptionSB.append(" They are [style.colourElasticity(extremely unyielding)],");
						break;
					case ONE_RIGID:
						descriptionSB.append(" It [style.colourElasticity(takes a huge amount of effort to stretch them out)],");
						break;
					case TWO_FIRM:
						descriptionSB.append(" They [style.colourElasticity(does not stretch very easily)],");
						break;
					case THREE_FLEXIBLE:
						descriptionSB.append(" They [style.colourElasticity(reluctantly stretch out)] when used as a sexual orifice,");
						break;
					case FOUR_LIMBER:
						descriptionSB.append(" They are [style.colourElasticity(somewhat resistant to being stretched out)],");
						break;
					case FIVE_STRETCHY:
						descriptionSB.append(" They [style.colourElasticity(stretch out fairly easily)],");
						break;
					case SIX_SUPPLE:
						descriptionSB.append(" They [style.colourElasticity(stretch out very easily)],");
						break;
					case SEVEN_ELASTIC:
						descriptionSB.append(" They are [style.colourElasticity(extremely elastic)],");
						break;
				}
				descriptionSB.append(" and after being used, they "+viewedBreastCrotch.getNipples().getOrificeNipples().getPlasticity().getDescriptionPlural()+".");
				
				for(OrificeModifier om : OrificeModifier.values()) {
					if(owner.hasNippleCrotchOrificeModifier(om)) {
						switch(om) {
							case MUSCLE_CONTROL:
								descriptionSB.append(" [npc.She] [npc.has] a series of muscles lining the insides of [npc.her] [npc.crotchNipples], allowing [npc.herHim] to expertly squeeze and grip down on any intruding object.");
								break;
							case PUFFY:
								descriptionSB.append(" [npc.Her] [npc.crotchNipples] have swollen up to be exceptionally plump and puffy.");
								break;
							case RIBBED:
								descriptionSB.append(" The insides of [npc.her] [npc.crotchNipples] are lined with sensitive, fleshy ribs, which grant [npc.herHim] extra pleasure when stimulated.");
								break;
							case TENTACLED:
								descriptionSB.append(" [npc.Her] [npc.crotchNipples] are filled with tiny little tentacles, which wriggle and squirm with a mind of their own.");
								break;
						}
					}
				}
				
				if (!viewedBreastCrotch.getNipples().getOrificeNipples().isVirgin()) {
					boolean virginityLossFound = false;
					for(SexAreaPenetration pt : SexAreaPenetration.values()) {
						if(pt.isTakesVirginity()) {
							if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE_CROTCH, pt))!=null) {
								descriptionSB.append(" <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE_CROTCH, pt)) + "</span>");
								virginityLossFound = true;
								break;
							}
						}
					}
					if(!virginityLossFound) {
						descriptionSB.append(" <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Name] [npc.has] lost [npc.her] [npc.crotchNipple] virginity.</span>");
					}
					
				} else {
					descriptionSB.append(" [style.colourExcellent([npc.Name] [npc.has] retained [npc.her] [npc.crotchNipple] virginity.)]");
				}
				
			} else {
				if(owner.hasNippleCrotchOrificeModifier(OrificeModifier.PUFFY)) {
					descriptionSB.append(" [npc.Her] [npc.crotchNipples] have swollen up to be exceptionally plump and puffy.");
				}	
			}
			
			if (viewedBreastCrotch.getRawMilkStorageValue() > 0) {
				descriptionSB.append("<br/>[npc.SheIsFull] currently producing "
						+ Units.fluid(viewedBreastCrotch.getRawMilkStorageValue(), Units.UnitType.LONG) + " of [npc.crotchMilkPrimaryColour(true)] [npc.crotchMilk] ("
						+ Units.fluid(viewedBreastCrotch.getRawStoredMilkValue(), Units.UnitType.LONG) + " currently stored) at [npc.a_crotchMilkRegen] rate.");
				
				switch(viewedBreastCrotch.getMilk().getFlavour()) {
					case MILK:
						descriptionSB.append(" [npc.Her] [npc.crotchMilkColour(true)] [npc.crotchMilk] tastes like regular milk.");
						break;
					case BUBBLEGUM:
						descriptionSB.append(" [npc.Her] [npc.crotchMilkColour(true)] [npc.crotchMilk] has the fruity taste of bubblegum.");
						break;
					case FLAVOURLESS:
						descriptionSB.append(" [npc.Her] [npc.crotchMilkColour(true)] [npc.crotchMilk] has absolutely no flavour whatsoever.");
						break;
					default:
						descriptionSB.append(" [npc.Her] [npc.crotchMilkColour(true)] [npc.crotchMilk] tastes exactly like "+viewedBreastCrotch.getMilk().getFlavour().getName()+".");
						break;
				}
				
				for(FluidModifier fm : FluidModifier.values()) {
					if(owner.hasMilkCrotchModifier(fm)) {
						descriptionSB.append(" " + fm.getBriefDescription());
					}
				}
				
			} else {
				descriptionSB.append("<br/>[npc.SheIsFull] not producing any milk.");
			}
//		}

		return UtilText.parse(owner, descriptionSB.toString());
	}

	public String getPenisDescription(GameCharacter owner) {
		boolean isPlayer = owner.isPlayer();
		
		descriptionSB = new StringBuilder();
		
		Penis viewedPenis = owner.getCurrentPenis();
		
		if(Main.game.getPlayer().hasIngestedPsychoactiveFluidType(FluidTypeBase.CUM)) {
			viewedPenis = new Penis(penis.getType(),
					(int) (penis.getRawLengthValue() * 2.25f),
					false,
					PenetrationGirth.FIVE_THICK.getValue(),
					penis.getTesticle().getTesticleSize().getValue()*2,
					(int) ((penis.getTesticle().getRawCumStorageValue()+100) * 3.25f),
					penis.getTesticle().getTesticleCount());
			descriptionSB.append("<i style='color:"+PresetColour.PSYCHOACTIVE.toWebHexString()+";'>The psychoactive cum you recently ingested is causing your view of "+(owner.isPlayer()?"your":"[npc.namePos]")+" cock to be distorted!</i> ");
		}

		switch(owner.getGenitalArrangement()) {
			case CLOACA:
				descriptionSB.append(UtilText.parse(owner, "[style.italicsFeral([npc.Her] cock is located within [npc.her] slit-like, front-facing cloaca.)] "));
				break;
			case CLOACA_BEHIND:
				descriptionSB.append(UtilText.parse(owner, "[style.italicsFeral([npc.Her] cock is located within [npc.her] slit-like, rear-facing cloaca.)] "));
				break;
			case NORMAL:
			break;
		}
		
		String penisAppearance = owner.getPenisType().getBodyDescription(owner);
		if(Main.game.getPlayer().hasIngestedPsychoactiveFluidType(FluidTypeBase.CUM)) {
			penisAppearance = penisAppearance.replaceAll("\\[npc\\.a_cockGirth\\]", UtilText.generateSingularDeterminer(viewedPenis.getGirth().getName())+" "+viewedPenis.getGirth().getName());
			penisAppearance = penisAppearance.replaceAll("\\[npc\\.cockLengthValue\\]", Units.size(viewedPenis.getRawLengthValue(), Units.UnitType.LONG_SINGULAR));
			descriptionSB.append(penisAppearance);
			descriptionSB.append(" It measures "+Units.size(viewedPenis.getDiameter(), Units.UnitType.LONG)+" in diameter ("+Units.size(viewedPenis.getDiameter()* Math.PI, Units.UnitType.LONG)+" in circumference).");
			
		} else {
			descriptionSB.append(penisAppearance);
			descriptionSB.append(" It measures [npc.penisDiameter(true)] in diameter ([npc.penisCircumference(true)] in circumference).");
		}

		if(viewedPenis.getType()!=PenisType.DILDO) {
			if(owner.isFeral()) {
				descriptionSB.append(" [style.colourFeral(As is to be expected, [npc.her] [npc.cock] is entirely feral in form, and is no different to that of a normal [npc.penisRace]'s.)]");
			} else if(penis.isFeral(owner)) {
				descriptionSB.append(" [style.colourFeral(As [npc.her] genitals are located on the animal portion of [npc.her] body, [npc.her] [npc.penis] is identical in functionality to that of a feral [npc.penisRace]'s.)]");
			}
		}
		
		for(PenetrationModifier pm : PenetrationModifier.getPenetrationModifiers()) {
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
					case BLUNT:
						descriptionSB.append(" The head curves around to a smooth surface.");
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
					case OVIPOSITOR:
						descriptionSB.append(" Its internal structure allows it to be used as an ovipositor, enabling [npc.name] to use it to lay [npc.her] pre-fertilised eggs in a target's orifices.");
						break;
				}
			}
		}

		if(owner.getCurrentPenis().getType()!=PenisType.DILDO) {
			if (!owner.getCurrentPenis().isVirgin()) {
				boolean virginityLossFound = false;
				for(SexAreaOrifice ot : SexAreaOrifice.values()) {
					if(ot.isInternalOrifice()) {
						if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, ot))!=null) {
							descriptionSB.append(" <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, ot)) + "</span>");
							virginityLossFound = true;
							break;
						}
					}
				}
				if(!virginityLossFound) {
					descriptionSB.append(" <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Name] [npc.has] lost [npc.her] penile virginity.</span>");
				}
				
			} else {
				descriptionSB.append(" [style.colourExcellent([npc.Name] [npc.has] retained [npc.her] penile virginity.)]");
			}
		}
		
		// Capacity:
		if (Capacity.getCapacityFromValue(viewedPenis.getOrificeUrethra().getStretchedCapacity()) != Capacity.ZERO_IMPENETRABLE) {
			descriptionSB.append("<br/>[npc.Her] cock's urethra has been loosened enough that it presents a ready orifice for penetration, and can be comfortably penetrated by objects of"
					+ " [style.colourSex("+ Units.size(Capacity.getCapacityFromValue(viewedPenis.getOrificeUrethra().getStretchedCapacity()).getMaximumValue(true)) + ")] in diameter when lubricated.");

			if(Main.game.isPenetrationLimitationsEnabled()) {
				switch(owner.getUrethraDepth()) {
					default:
						descriptionSB.append(" [npc.Her] fuckable urethra is <span style='color:"+owner.getUrethraDepth().getColour().toWebHexString()+";'>[npc.penisUrethraDepth]</span>,");
						break;
					case TWO_AVERAGE:
						descriptionSB.append(" [npc.Her] fuckable urethra is of an <span style='color:"+owner.getUrethraDepth().getColour().toWebHexString()+";'>average depth</span>,");
						break;
				}
				if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
					if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
						descriptionSB.append(" and as [npc.sheIsFull] a "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", [npc.she] can be pushed to [style.colourMinorGood(comfortably)] accommodate objects of a maximum length of "
								+ " [style.colourSex("+Units.size(owner.getUrethraMaximumPenetrationDepthUncomfortable())+")].");
					} else {
						descriptionSB.append(" allowing [npc.herHim] to [style.colourMinorGood(comfortably)] accommodate [style.colourSex("+Units.size(owner.getUrethraMaximumPenetrationDepthComfortable())+")] of a penetrative object,"
								+ " and [style.colourMinorBad(uncomfortably)] accommodate [style.colourSex("+Units.size(owner.getUrethraMaximumPenetrationDepthUncomfortable())+")].");
					}
					
				} else {
					if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
						descriptionSB.append(" and as [npc.sheIsFull] a "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", combined with the fact that [npc.her] body is made out of [npc.bodyMaterial],"
								+ " [npc.she] can be pushed to [style.colourMinorGood(comfortably)] accommodate objects of [style.colourSex(any length)].");
					} else {
						descriptionSB.append(" allowing [npc.herHim] to [style.colourMinorGood(comfortably)] accommodate [style.colourSex("+Units.size(owner.getUrethraMaximumPenetrationDepthComfortable())+")] of a penetrative object,"
								+ " and as [npc.her] body is made out of [npc.bodyMaterial], [npc.she] can be pushed to [style.colourMinorBad(uncomfortably)] accommodate objects of [style.colourSex(any length)].");
					}
				}
			}
			
			switch (viewedPenis.getOrificeUrethra().getElasticity()) {
				case ZERO_UNYIELDING:
					descriptionSB.append(" It is [style.colourElasticity(extremely unyielding)],");
					break;
				case ONE_RIGID:
					descriptionSB.append(" It [style.colourElasticity(takes a huge amount of effort to stretch it out)],");
					break;
				case TWO_FIRM:
					descriptionSB.append(" It [style.colourElasticity(does not stretch very easily)],");
					break;
				case THREE_FLEXIBLE:
					descriptionSB.append(" It [style.colourElasticity(reluctantly stretches out)] when used as a sexual orifice,");
					break;
				case FOUR_LIMBER:
					descriptionSB.append(" It is [style.colourElasticity(somewhat resistant to being stretched out)],");
					break;
				case FIVE_STRETCHY:
					descriptionSB.append(" It [style.colourElasticity(stretches out fairly easily)],");
					break;
				case SIX_SUPPLE:
					descriptionSB.append(" It [style.colourElasticity(stretches out very easily)],");
					break;
				case SEVEN_ELASTIC:
					descriptionSB.append(" It is [style.colourElasticity(extremely elastic)],");
					break;
			}
			descriptionSB.append(" and after being used, it "+viewedPenis.getOrificeUrethra().getPlasticity().getDescription()+".");
			
			for(OrificeModifier om : OrificeModifier.values()) {
				if(owner.hasUrethraOrificeModifier(om)) {
					switch(om) {
						case PUFFY:
							descriptionSB.append(" [npc.Her] urethra has transformed into having a swollen, puffy rim.");
							break;
						case MUSCLE_CONTROL:
							descriptionSB.append(" [npc.She] [npc.has] a series of muscles lining the inside of [npc.her] urethra, allowing [npc.herHim] to expertly squeeze and grip down on any intruding object.");
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

		if(viewedPenis.getType()!=PenisType.DILDO) {
			if (isPlayer && !owner.isUrethraVirgin()) {
				for(SexAreaPenetration pt : SexAreaPenetration.values()) {
					if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_PENIS, pt))!=null) {
						descriptionSB.append(" <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_PENIS, pt)) + "</span>");
						break;
					}
				}
			}
			descriptionSB.append("<br/>");
		}
		

		if(viewedPenis.getType()!=PenisType.DILDO) {
			// Pubic Hair:
			if(Main.game.isPubicHairEnabled()) {
				if(owner.getPubicHairType().getType()==BodyCoveringType.BODY_HAIR_SCALES_ALLIGATOR) {
					switch(owner.getPubicHair()) {
						case ZERO_NONE:
							descriptionSB.append(" There's no trace of any rough  "+owner.getPubicHairType().getName(owner)+" around the base of [npc.her] cock.");
							break;
						case ONE_STUBBLE:
							descriptionSB.append(" [npc.She] [npc.has] a small amount of rough "+owner.getPubicHairType().getFullDescription(owner, true)+" around the base of [npc.her] cock.");
							break;
						case TWO_MANICURED:
							descriptionSB.append(" [npc.She] [npc.has] a rough patch of "+owner.getPubicHairType().getFullDescription(owner, true)+" around the base of [npc.her] cock.");
							break;
						case THREE_TRIMMED:
							descriptionSB.append(" [npc.She] [npc.has] a rough patch of "+owner.getPubicHairType().getFullDescription(owner, true)+" around the base of [npc.her] cock.");
							break;
						case FOUR_NATURAL:
							descriptionSB.append(" [npc.She] [npc.has] a natural amount of rough "+owner.getPubicHairType().getFullDescription(owner, true)+" around the base of [npc.her] cock.");
							break;
						case FIVE_UNKEMPT:
							descriptionSB.append(" [npc.She] [npc.has] an unkempt mass of rough "+owner.getPubicHairType().getFullDescription(owner, true)+" around the base of [npc.her] cock.");
							break;
						case SIX_BUSHY:
							descriptionSB.append(" [npc.She] [npc.has] a thick, rough mass of "+owner.getPubicHairType().getFullDescription(owner, true)+" around the base of [npc.her] cock.");
							break;
						case SEVEN_WILD:
							descriptionSB.append(" [npc.She] [npc.has] a wild, rough mass of "+owner.getPubicHairType().getFullDescription(owner, true)+" around the base of [npc.her] cock.");
							break;
					}
				} else {
					switch(owner.getPubicHair()) {
						case ZERO_NONE:
							descriptionSB.append(" There is no trace of any "+owner.getPubicHairType().getName(owner)+" around the base of [npc.her] cock.");
							break;
						case ONE_STUBBLE:
							descriptionSB.append(" [npc.She] [npc.has] a stubbly patch of "+owner.getPubicHairType().getFullDescription(owner, true)+" around the base of [npc.her] cock.");
							break;
						case TWO_MANICURED:
							descriptionSB.append(" [npc.She] [npc.has] a neat, manicured patch of "+owner.getPubicHairType().getFullDescription(owner, true)+" around the base of [npc.her] cock.");
							break;
						case THREE_TRIMMED:
							descriptionSB.append(" [npc.She] [npc.has] a trimmed patch of "+owner.getPubicHairType().getFullDescription(owner, true)+" around the base of [npc.her] cock.");
							break;
						case FOUR_NATURAL:
							descriptionSB.append(" [npc.She] [npc.has] a natural bush of "+owner.getPubicHairType().getFullDescription(owner, true)+" around the base of [npc.her] cock.");
							break;
						case FIVE_UNKEMPT:
							descriptionSB.append(" [npc.She] [npc.has] an unkempt bush of "+owner.getPubicHairType().getFullDescription(owner, true)+" around the base of [npc.her] cock.");
							break;
						case SIX_BUSHY:
							descriptionSB.append(" [npc.She] [npc.has] a thick, bushy mass of "+owner.getPubicHairType().getFullDescription(owner, true)+" around the base of [npc.her] cock.");
							break;
						case SEVEN_WILD:
							descriptionSB.append(" [npc.She] [npc.has] a wild, bushy mass of "+owner.getPubicHairType().getFullDescription(owner, true)+" around the base of [npc.her] cock.");
							break;
					}
				}
			}
	
			descriptionSB.append("<br/>");
		}
		
		// Testicle size and cum production:

		if(viewedPenis.getType()!=PenisType.DILDO) {
			if(owner.isInternalTesticles()) {
				descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] have shifted to sit inside [npc.her] body, leaving [npc.her] [npc.cock] as the only visible part of [npc.her] male reproductive organs.");
				
			} else {
				switch (viewedPenis.getTesticle().getTesticleSize()) {
					case ZERO_VESTIGIAL:
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls] are [npc.materialCompositionDescriptor] [npc.ballFullDescription(true)],"
								+ " and are so small that they're only just visible as tiny little mounds nestling beneath [npc.her] [npc.cock].");
						break;
					case ONE_TINY:
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls] are [npc.materialCompositionDescriptor] [npc.ballFullDescription(true)], and are small enough to comfortably nestle underneath [npc.her] [npc.cock].");
						break;
					case TWO_AVERAGE:
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] are [npc.materialCompositionDescriptor] [npc.ballFullDescription(true)], and dangle down beneath [npc.her] [npc.cock].");
						break;
					case THREE_LARGE:
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] are [npc.materialCompositionDescriptor] [npc.ballFullDescription(true)], and hang down beneath [npc.her] [npc.cock].");
						break;
					case FOUR_HUGE:
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] are [npc.materialCompositionDescriptor] [npc.ballFullDescription(true)], and hang down beneath [npc.her] [npc.cock].");
						break;
					case FIVE_MASSIVE:
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] are [npc.materialCompositionDescriptor] [npc.ballFullDescription(true)], and hang down beneath [npc.her] [npc.cock].");
						break;
					case SIX_GIGANTIC:
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] are [npc.materialCompositionDescriptor] [npc.ballFullDescription(true)], and hang down beneath [npc.her] [npc.cock].");
						break;
					case SEVEN_ABSURD:
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] are [npc.materialCompositionDescriptor] [npc.ballFullDescription(true)], and hang down beneath [npc.her] [npc.cock].");
						break;
				}
			}
			
			switch (viewedPenis.getTesticle().getCumStorage()) {
				case ZERO_NONE:
					if (viewedPenis.getTesticle().getTesticleSize().getValue() > TesticleSize.TWO_AVERAGE.getValue()) {
						descriptionSB.append(" Despite their large size, they");
					} else {
						descriptionSB.append(" They");
					}
					descriptionSB.append(" don't produce any [npc.cum+] at all.");
					break;
				case ONE_TRICKLE:
					if (viewedPenis.getTesticle().getTesticleSize().getValue() > TesticleSize.TWO_AVERAGE.getValue()) {
						descriptionSB.append(" Despite their large size, they");
					} else {
						descriptionSB.append(" They");
					}
					descriptionSB.append(" only produce a tiny trickle of [npc.cum+].");
					break;
				case TWO_SMALL_AMOUNT:
					if (viewedPenis.getTesticle().getTesticleSize().getValue() > TesticleSize.THREE_LARGE.getValue()) {
						descriptionSB.append(" Despite their large size, they");
					} else {
						descriptionSB.append(" They");
					}
					descriptionSB.append(" only produce a small amount of [npc.cum+].");
					break;
				case THREE_AVERAGE:
					if (viewedPenis.getTesticle().getTesticleSize().getValue() > TesticleSize.FOUR_HUGE.getValue()) {
						descriptionSB.append(" Despite their huge size, they only");
					} else {
						descriptionSB.append(" They");
					}
					descriptionSB.append(" produce an average amount of [npc.cum+].");
					break;
				case FOUR_LARGE:
					if (viewedPenis.getTesticle().getTesticleSize().getValue() < TesticleSize.TWO_AVERAGE.getValue()) {
						descriptionSB.append(" Despite their small size, they");
					} else {
						descriptionSB.append(" They");
					}
					descriptionSB.append(" produce a large amount of [npc.cum+].");
					break;
				case FIVE_HUGE:
					if (viewedPenis.getTesticle().getTesticleSize().getValue() < TesticleSize.TWO_AVERAGE.getValue()) {
						descriptionSB.append(" Despite their small size, they");
					} else {
						descriptionSB.append(" They");
					}
					descriptionSB.append(" produce a huge amount of [npc.cum+].");
					break;
				case SIX_EXTREME:
					if (viewedPenis.getTesticle().getTesticleSize().getValue() < TesticleSize.TWO_AVERAGE.getValue()) {
						descriptionSB.append(" Despite their small size, they");
					} else {
						descriptionSB.append(" They");
					}
					descriptionSB.append(" produce an extreme amount of [npc.cum+].");
					break;
				case SEVEN_MONSTROUS:
					if (viewedPenis.getTesticle().getTesticleSize().getValue() < TesticleSize.TWO_AVERAGE.getValue()) {
						descriptionSB.append(" Despite their small size, they");
					} else {
						descriptionSB.append(" They");
					}
					descriptionSB.append(" produce a monstrous amount of [npc.cum+].");
					break;
			}
			
			descriptionSB.append(" [npc.Her] [npc.cumColour(true)] [npc.cum]");
			
			switch(viewedPenis.getTesticle().getCum().getFlavour()) {
				case CUM:
					descriptionSB.append(", much to nobody's surprise, tastes like cum.");
					break;
				case BUBBLEGUM:
					descriptionSB.append(" has the fruity taste of bubblegum.");
					break;
				case FLAVOURLESS:
					descriptionSB.append(" has absolutely no flavour whatsoever.");
					break;
				default:
					descriptionSB.append(" tastes exactly like "+viewedPenis.getTesticle().getCum().getFlavour().getName()+".");
					break;
			}
			
			for(FluidModifier fm : FluidModifier.values()) {
				if(owner.hasCumModifier(fm)) {
					descriptionSB.append(" " + fm.getBriefDescription());
				}
			}
		}
		
		return UtilText.parse(owner, descriptionSB.toString());
	}
	
	public String getVaginaDescription(GameCharacter owner) {
		boolean isPlayer = owner.isPlayer();
		
		descriptionSB = new StringBuilder();

		Vagina viewedVagina = vagina;
		
		boolean hallucinating = false;
		if(Main.game.getPlayer().hasIngestedPsychoactiveFluidType(FluidTypeBase.GIRLCUM)) {
			viewedVagina = new Vagina(vagina.getType(),
					vagina.getRawLabiaSizeValue(),
					vagina.getClitoris().getRawClitorisSizeValue(),
					vagina.getClitoris().getRawGirthValue(),
					Wetness.SEVEN_DROOLING.getValue(),
					vagina.getOrificeVagina().getRawCapacityValue() *3,
					vagina.getOrificeVagina().getDepth(null).getValue(),
					vagina.getOrificeVagina().getElasticity().getValue(),
					vagina.getOrificeVagina().getPlasticity().getValue(),
					vagina.getOrificeVagina().isVirgin());
			viewedVagina.setPierced(owner, vagina.isPierced());
			descriptionSB.append("<i style='color:"+PresetColour.PSYCHOACTIVE.toWebHexString()+";'>The psychoactive girlcum which you recently ingested is causing your view of "+(owner.isPlayer()?"your":"[npc.namePos]")+" pussy to be distorted!</i> ");
			hallucinating = true;
		}

		switch(owner.getGenitalArrangement()) {
			case CLOACA:
				descriptionSB.append(UtilText.parse(owner, "[style.italicsFeral([npc.Her] pussy is located within [npc.her] slit-like, front-facing cloaca.)] "));
				break;
			case CLOACA_BEHIND:
				descriptionSB.append(UtilText.parse(owner, "[style.italicsFeral([npc.Her] pussy is located within [npc.her] slit-like, rear-facing cloaca.)] "));
				break;
			case NORMAL:
				break;
		}
		
		if(owner.hasPenis()) {
			descriptionSB.append("Beneath [npc.her] [npc.penis], ");
		} else if(owner.hasLegs()) {
			descriptionSB.append("Between [npc.her] [npc.legs], ");
		} else {
			descriptionSB.append("Down at [npc.her] groin, ");
		}
		
		descriptionSB.append(viewedVagina.getType().getBodyDescription(owner));
		
		if(owner.isImpregnationPhysicallyPossible()) {
			if(owner.isVaginaEggLayer()) {
				descriptionSB.append(" Due to the configuration of [npc.her] reproductive organs, [npc.she] [style.colourEgg([npc.verb(lay)] eggs instead of giving birth to live young)].");
			} else {
				descriptionSB.append(" Due to the configuration of [npc.her] reproductive organs, [npc.she] [style.colourSex([npc.verb(give)] birth to live young)].");
			}
		}
		
		if(owner.isFeral()) {
			descriptionSB.append(" [style.colourFeral(As is to be expected, [npc.her] [npc.pussy] is entirely feral in form, and is no different to that of a normal [npc.vaginaRace]'s.)]");
		} else if(vagina.isFeral(owner)) {
			descriptionSB.append(" [style.colourFeral(As it is located on the lower, animalistic part of [npc.her] body, [npc.her] [npc.pussy] is no different to that of a feral [npc.vaginaRace]'s.)]");
		}
		
		// Clit:
		descriptionSB.append("<br/>[npc.She] [npc.has] [npc.a_clitSize]"+(owner.getClitorisGirth()==PenetrationGirth.THREE_AVERAGE?"":", [npc.clitGirth]")
				+" clit, which measures [npc.clitSizeValue] in length and [npc.clitDiameter(true)] in diameter ([npc.clitCircumference(true)] in circumference).");
		if(owner.isClitorisPseudoPenis()) {
			descriptionSB.append(" [style.colourSex(Its considerable size allows [npc.herHim] to use it as a pseudo-penis)].");
		}
		
		for(PenetrationModifier pm : PenetrationModifier.getPenetrationModifiers()) {
			if(owner.hasClitorisModifier(pm)) {
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
					case BLUNT:
						descriptionSB.append(" The tip curves around to a smooth surface.");
						break;
					case FLARED:
						descriptionSB.append(" The tip is wide and flared, like a horse's cock.");
						break;
					case KNOTTED:
						descriptionSB.append(" A fat knot sits at the base.");
						break;
					case PREHENSILE:
						descriptionSB.append(" It is prehensile, and can be manipulated and moved much like a primate's tail.");
						break;
					case SHEATHED:
						descriptionSB.append(" Its clitoral hood has transformed into a large sheath, into which [npc.her] [npc.clit] can retract, no matter how large it grows.");
						break;
					case TAPERED:
						descriptionSB.append(" The shaft is tapered, and gets thinner nearer to the head.");
						break;
					case VEINY:
						descriptionSB.append(" Large veins press out from its surface.");
						break;
					case OVIPOSITOR:
						descriptionSB.append(" Its internal structure allows it to be used as an ovipositor, enabling [npc.name] to use it to lay [npc.her] pre-fertilised eggs in a target's orifices.");
						break;
				}
			}
		}
		
		
		// Virgin/capacity:
		descriptionSB.append("<br/>");
		if(viewedVagina.getOrificeVagina().isVirgin()) {
			if(isPlayer || !hallucinating) {
				if(viewedVagina.getOrificeVagina().hasHymen()) {
					descriptionSB.append(" Within [npc.her] " + Capacity.getCapacityFromValue(viewedVagina.getOrificeVagina().getStretchedCapacity()).getDescriptor(true)
							+ " [npc.pussy], [style.colourMinorGood([npc.her] hymen is still intact)], and [style.colourExcellent([npc.she] [npc.has] retained [npc.her] vaginal virginity)].");
					
				} else {
					if(owner.isDoll()) {
						descriptionSB.append(" As a sex doll, [npc.name] [npc.do] not have a hymen, and [style.colourExcellent([npc.has] retained [npc.her] vaginal virginity)].");
						
					} else {
						descriptionSB.append(" Within [npc.her] " + Capacity.getCapacityFromValue(viewedVagina.getOrificeVagina().getStretchedCapacity()).getDescriptor(true)
								+ " [npc.pussy], [style.colourMinorBad([npc.her] hymen has been torn)], but despite this, [style.colourExcellent([npc.she] [npc.has] retained [npc.her] vaginal virginity)].");
					}
				}
			}
			
		} else {
			boolean virginityLossFound = false;
			for(SexAreaPenetration pt : SexAreaPenetration.values()) {
				if(pt.isTakesVirginity()) {
					if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, pt))!=null) {
						descriptionSB.append(" [style.colourArcane("+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, pt)) + ")]");
						virginityLossFound = true;
						break;
					}
				}
			}
			if(!virginityLossFound) {
				descriptionSB.append(" [style.colourArcane([npc.Name] [npc.has] lost [npc.her] virginity.)]");
			}
			if(viewedVagina.getOrificeVagina().hasHymen()) {
				descriptionSB.append(" Although [npc.sheIsFull] no longer a virgin, [style.colourMinorGood([npc.she] [npc.has] an intact hymen)] within [npc.her] pussy.");
			} else {
				if(owner.isDoll()) {
					descriptionSB.append(" As a sex doll, [npc.name] [npc.do] not have a hymen.");
					
				} else {
					descriptionSB.append(" As is to be expected of someone who is no longer a virgin, [style.colourMinorBad([npc.her] hymen has been torn)].");
				}
			}
		}
		
		if (isPlayer || !hallucinating) {
			descriptionSB.append(" [npc.Her] pussy is " + Capacity.getCapacityFromValue(viewedVagina.getOrificeVagina().getStretchedCapacity()).getDescriptor(true)+" and when lubricated can comfortably accommodate objects of up to"
					+ " [style.colourSex("+ Units.size(Capacity.getMaximumComfortableDiameter(viewedVagina.orificeVagina.getElasticity(), viewedVagina.getOrificeVagina().getRawCapacityValue(), true)) + ")] in diameter.");
		}

		if(Main.game.isPenetrationLimitationsEnabled()) {
			switch(owner.getVaginaDepth()) {
				default:
					descriptionSB.append(" [npc.Her] pussy is <span style='color:"+owner.getVaginaDepth().getColour().toWebHexString()+";'>[npc.pussyDepth]</span>,");
					break;
				case TWO_AVERAGE:
					descriptionSB.append(" [npc.Her] pussy is of an <span style='color:"+owner.getVaginaDepth().getColour().toWebHexString()+";'>average depth</span>,");
					break;
			}
			if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
				if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
					descriptionSB.append(" and as [npc.sheIsFull] a "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", [npc.she] can be pushed to [style.colourMinorGood(comfortably)] accommodate objects of a maximum length of "
							+ " [style.colourSex("+Units.size(owner.getVaginaMaximumPenetrationDepthUncomfortable())+")].");
				} else {
					descriptionSB.append(" allowing [npc.herHim] to [style.colourMinorGood(comfortably)] accommodate [style.colourSex("+Units.size(owner.getVaginaMaximumPenetrationDepthComfortable())+")] of a penetrative object,"
							+ " and [style.colourMinorBad(uncomfortably)] accommodate [style.colourSex("+Units.size(owner.getVaginaMaximumPenetrationDepthUncomfortable())+")].");
				}
				
			} else {
				if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
					descriptionSB.append(" and as [npc.sheIsFull] a "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", combined with the fact that [npc.her] body is made out of [npc.bodyMaterial],"
							+ " [npc.she] can be pushed to [style.colourMinorGood(comfortably)] accommodate objects of [style.colourSex(any length)].");
				} else {
					descriptionSB.append(" allowing [npc.herHim] to [style.colourMinorGood(comfortably)] accommodate [style.colourSex("+Units.size(owner.getVaginaMaximumPenetrationDepthComfortable())+")] of a penetrative object,"
							+ " and as [npc.her] body is made out of [npc.bodyMaterial], [npc.she] can be pushed to [style.colourMinorBad(uncomfortably)] accommodate objects of [style.colourSex(any length)].");
				}
			}
		}
		
		// Wetness:
		switch (viewedVagina.getOrificeVagina().getWetness(owner)) {
			case ZERO_DRY:
				descriptionSB.append(" It's [style.colourWetness(completely dry and never gets wet)], no matter how aroused [npc.she] [npc.is].");
				break;
			case ONE_SLIGHTLY_MOIST:
				descriptionSB.append(" It's [style.colourWetness(slightly moist)], and [npc.she] [npc.verb(need)] a huge amount of stimulation before [npc.she] [npc.verb(get)] wet.");
				break;
			case TWO_MOIST:
				descriptionSB.append(" It's [style.colourWetness(moist)], but [npc.she] still [npc.verb(need)] a lot of stimulation before [npc.she] [npc.verb(get)] wet.");
				break;
			case THREE_WET:
				descriptionSB.append(" It's of an [style.colourWetness(average wetness)], and [npc.she] only [npc.verb(need)] a small amount of foreplay before [npc.sheIs] wet enough for a pleasurable penetration.");
				break;
			case FOUR_SLIMY:
				descriptionSB.append(" It's always [style.colourWetness(slimy and wet)], and [npc.sheIs] ready for penetration at a moment's notice.");
				break;
			case FIVE_SLOPPY:
				descriptionSB.append(" Its surface is always coated in [style.colourWetness(slimy moisture)], and within, [npc.her] pussy is permanently [style.colourWetness(sloppy)] and practically begging to be fucked.");
				break;
			case SIX_SOPPING_WET:
				descriptionSB.append(" [npc.Her] pussy is never anything less than [style.colourWetness(sopping wet)], and a trickle of [npc.her] natural lubricant constantly dribbles from [npc.her] slit.");
				break;
			case SEVEN_DROOLING:
				descriptionSB.append(" [npc.Her] pussy is [style.colourWetness(so wet that it audibly squelches with every step [npc.she] [npc.verb(take)])], and a constant stream of juices flow from [npc.her] inviting cunt.");
				break;
		}
		
		if(viewedVagina.getOrificeVagina().isSquirter()) {
			descriptionSB.append(" [npc.She] [npc.is] a [style.colourArcane(squirter)], and [style.colourWetness([npc.verb(produce)] a considerable amount of female ejaculate)] each time [npc.she] [npc.verb(orgasm)].");
		}
		
		// Elasticity & plasticity:
		switch (viewedVagina.getOrificeVagina().getElasticity()) {
			case ZERO_UNYIELDING:
				descriptionSB.append(" It is [style.colourElasticity(extremely unyielding)],");
				break;
			case ONE_RIGID:
				descriptionSB.append(" It [style.colourElasticity(takes a huge amount of effort to stretch it out)],");
				break;
			case TWO_FIRM:
				descriptionSB.append(" It [style.colourElasticity(does not stretch very easily)],");
				break;
			case THREE_FLEXIBLE:
				descriptionSB.append(" It [style.colourElasticity(reluctantly stretches out)] when penetrated,");
				break;
			case FOUR_LIMBER:
				descriptionSB.append(" It is [style.colourElasticity(somewhat resistant to being stretched out)],");
				break;
			case FIVE_STRETCHY:
				descriptionSB.append(" It [style.colourElasticity(stretches out fairly easily)],");
				break;
			case SIX_SUPPLE:
				descriptionSB.append(" It [style.colourElasticity(stretches out very easily)],");
				break;
			case SEVEN_ELASTIC:
				descriptionSB.append(" It is [style.colourElasticity(extremely elastic)],");
				break;
		}
		descriptionSB.append(" and after being used, it "+viewedVagina.getOrificeVagina().getPlasticity().getDescription()+".");
		
		for(OrificeModifier om : OrificeModifier.values()) {
			if(owner.hasVaginaOrificeModifier(om)) {
				switch(om) {
					case MUSCLE_CONTROL:
						descriptionSB.append(" [npc.She] [npc.has] a series of internal muscles lining the inside of [npc.her] [npc.vagina], allowing [npc.herHim] to expertly squeeze and grip down on any intruding object.");
						break;
					case PUFFY:
						descriptionSB.append(" [npc.Her] labia have swollen up into big, extra-puffy pussy lips.");
						break;
					case RIBBED:
						descriptionSB.append(" The inside of [npc.her] [npc.vagina] is lined with sensitive, fleshy ribs, which grant [npc.herHim] extra pleasure when stimulated.");
						break;
					case TENTACLED:
						descriptionSB.append(" [npc.Her] [npc.vagina] is filled with tiny little tentacles, which wriggle and squirm with a mind of their own.");
						break;
				}
			}
		}
		
		
		// Girlcum:
		
		descriptionSB.append(" [npc.Her] [npc.girlcumColour(true)] [npc.girlcum]");
		
		switch(viewedVagina.getGirlcum().getFlavour()) {
			case GIRL_CUM:
				descriptionSB.append(", much to nobody's surprise, tastes like ordinary girlcum.");
				break;
			case BUBBLEGUM:
				descriptionSB.append(" has the fruity taste of bubblegum.");
				break;
			case FLAVOURLESS:
				descriptionSB.append(" has absolutely no flavour whatsoever.");
				break;
			default:
				descriptionSB.append(" tastes exactly like "+viewedVagina.getGirlcum().getFlavour().getName()+".");
				break;
		}
		
		for(FluidModifier fm : FluidModifier.values()) {
			if(viewedVagina.getGirlcum().getFluidModifiers().contains(fm)) {
				descriptionSB.append(" " + fm.getBriefDescription());
			}
		}
		
		descriptionSB.append("<br/>");
		
		// Urethra:
		if (Capacity.getCapacityFromValue(viewedVagina.getOrificeUrethra().getStretchedCapacity()) != Capacity.ZERO_IMPENETRABLE) {
			descriptionSB.append("[npc.Her] vagina's urethra has been loosened enough that it presents a ready orifice for penetration, and when lubricated can comfortably accommodate objects of up to"
					+ " [style.colourSex("+ Units.size(Capacity.getMaximumComfortableDiameter(viewedVagina.getOrificeUrethra().getElasticity(), viewedVagina.getOrificeUrethra().getRawCapacityValue(), true)) + ")] in diameter.");
			
			if(Main.game.isPenetrationLimitationsEnabled()) {
				switch(owner.getVaginaUrethraDepth()) {
					default:
						descriptionSB.append(" [npc.Her] fuckable urethra is <span style='color:"+owner.getVaginaUrethraDepth().getColour().toWebHexString()+";'>[npc.pussyUrethraDepth]</span>,");
						break;
					case TWO_AVERAGE:
						descriptionSB.append(" [npc.Her] fuckable urethra is of an <span style='color:"+owner.getVaginaUrethraDepth().getColour().toWebHexString()+";'>average depth</span>,");
						break;
				}
				if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
					if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
						descriptionSB.append(" and as [npc.sheIsFull] a "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", [npc.she] can be pushed to [style.colourMinorGood(comfortably)] accommodate objects of a maximum length of "
								+ " [style.colourSex("+Units.size(owner.getVaginaUrethraMaximumPenetrationDepthUncomfortable())+")].");
					} else {
						descriptionSB.append(" allowing [npc.herHim] to [style.colourMinorGood(comfortably)] accommodate [style.colourSex("+Units.size(owner.getVaginaUrethraMaximumPenetrationDepthComfortable())+")] of a penetrative object,"
								+ " and [style.colourMinorBad(uncomfortably)] accommodate [style.colourSex("+Units.size(owner.getVaginaUrethraMaximumPenetrationDepthUncomfortable())+")].");
					}
					
				} else {
					if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
						descriptionSB.append(" and as [npc.sheIsFull] a "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", combined with the fact that [npc.her] body is made out of [npc.bodyMaterial],"
								+ " [npc.she] can be pushed to [style.colourMinorGood(comfortably)] accommodate objects of [style.colourSex(any length)].");
					} else {
						descriptionSB.append(" allowing [npc.herHim] to [style.colourMinorGood(comfortably)] accommodate [style.colourSex("+Units.size(owner.getVaginaUrethraMaximumPenetrationDepthComfortable())+")] of a penetrative object,"
								+ " and as [npc.her] body is made out of [npc.bodyMaterial], [npc.she] can be pushed to [style.colourMinorBad(uncomfortably)] accommodate objects of [style.colourSex(any length)].");
					}
				}
			}
			
			// Elasticity & plasticity:
			switch (viewedVagina.getOrificeUrethra().getElasticity()) {
				case ZERO_UNYIELDING:
					descriptionSB.append(" It is [style.colourElasticity(extremely unyielding)],");
					break;
				case ONE_RIGID:
					descriptionSB.append(" It [style.colourElasticity(takes a huge amount of effort to stretch it out)],");
					break;
				case TWO_FIRM:
					descriptionSB.append(" It [style.colourElasticity(does not stretch very easily)],");
					break;
				case THREE_FLEXIBLE:
					descriptionSB.append(" It [style.colourElasticity(reluctantly stretches out)] when penetrated,");
					break;
				case FOUR_LIMBER:
					descriptionSB.append(" It is [style.colourElasticity(somewhat resistant to being stretched out)],");
					break;
				case FIVE_STRETCHY:
					descriptionSB.append(" It [style.colourElasticity(stretches out fairly easily)],");
					break;
				case SIX_SUPPLE:
					descriptionSB.append(" It [style.colourElasticity(stretches out very easily)],");
					break;
				case SEVEN_ELASTIC:
					descriptionSB.append(" It is [style.colourElasticity(extremely elastic)],");
					break;
			}
			descriptionSB.append(" and after being used, it "+viewedVagina.getOrificeUrethra().getPlasticity().getDescription()+".");
			
			for(OrificeModifier om : OrificeModifier.values()) {
				if(owner.hasVaginaUrethraOrificeModifier(om)) {
					switch(om) {
						case PUFFY:
							descriptionSB.append(" [npc.Her] urethra has transformed into having a swollen, puffy rim.");
							break;
						case MUSCLE_CONTROL:
							descriptionSB.append(" [npc.She] [npc.has] a series of muscles lining the inside of [npc.her] urethra, allowing [npc.herHim] to expertly squeeze and grip down on any intruding object.");
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
		
		// Pubic Hair:
		if(Main.game.isPubicHairEnabled()) {
			if(owner.getPubicHairType().getType()==BodyCoveringType.BODY_HAIR_SCALES_ALLIGATOR) {
				switch(owner.getPubicHair()) {
					case ZERO_NONE:
						descriptionSB.append(" There's no trace of any rough  "+owner.getPubicHairType().getName(owner)+" around [npc.her] [npc.pussy].");
						break;
					case ONE_STUBBLE:
						descriptionSB.append(" [npc.SheHasFull] a small amount of rough "+owner.getPubicHairType().getFullDescription(owner, true)+" around [npc.her] [npc.pussy].");
						break;
					case TWO_MANICURED:
						descriptionSB.append(" [npc.SheHasFull] a rough patch of "+owner.getPubicHairType().getFullDescription(owner, true)+" around [npc.her] [npc.pussy].");
						break;
					case THREE_TRIMMED:
						descriptionSB.append(" [npc.SheHasFull] a rough patch of "+owner.getPubicHairType().getFullDescription(owner, true)+" around [npc.her] [npc.pussy].");
						break;
					case FOUR_NATURAL:
						descriptionSB.append(" [npc.SheHasFull] a natural amount of rough "+owner.getPubicHairType().getFullDescription(owner, true)+" around [npc.her] [npc.pussy].");
						break;
					case FIVE_UNKEMPT:
						descriptionSB.append(" [npc.SheHasFull] an unkempt mass of rough "+owner.getPubicHairType().getFullDescription(owner, true)+" around [npc.her] [npc.pussy].");
						break;
					case SIX_BUSHY:
						descriptionSB.append(" [npc.SheHasFull] a thick, rough mass of "+owner.getPubicHairType().getFullDescription(owner, true)+" around [npc.her] [npc.pussy].");
						break;
					case SEVEN_WILD:
						descriptionSB.append(" [npc.SheHasFull] a wild, rough mass of "+owner.getPubicHairType().getFullDescription(owner, true)+" around [npc.her] [npc.pussy].");
						break;
				}
			} else {
				switch(owner.getPubicHair()) {
					case ZERO_NONE:
						descriptionSB.append(" There is no trace of any "+owner.getPubicHairType().getName(owner)+" around [npc.her] [npc.pussy].");
						break;
					case ONE_STUBBLE:
						descriptionSB.append(" [npc.SheHasFull] a stubbly patch of "+owner.getPubicHairType().getFullDescription(owner, true)+" around [npc.her] [npc.pussy].");
						break;
					case TWO_MANICURED:
						descriptionSB.append(" [npc.SheHasFull] a neat, manicured patch of "+owner.getPubicHairType().getFullDescription(owner, true)+" around [npc.her] [npc.pussy].");
						break;
					case THREE_TRIMMED:
						descriptionSB.append(" [npc.SheHasFull] a trimmed patch of "+owner.getPubicHairType().getFullDescription(owner, true)+" around [npc.her] [npc.pussy].");
						break;
					case FOUR_NATURAL:
						descriptionSB.append(" [npc.SheHasFull] a natural bush of "+owner.getPubicHairType().getFullDescription(owner, true)+" around [npc.her] [npc.pussy].");
						break;
					case FIVE_UNKEMPT:
						descriptionSB.append(" [npc.SheHasFull] an unkempt bush of "+owner.getPubicHairType().getFullDescription(owner, true)+" around [npc.her] [npc.pussy].");
						break;
					case SIX_BUSHY:
						descriptionSB.append(" [npc.SheHasFull] a thick, bushy mass of "+owner.getPubicHairType().getFullDescription(owner, true)+" around [npc.her] [npc.pussy].");
						break;
					case SEVEN_WILD:
						descriptionSB.append(" [npc.SheHasFull] a wild, bushy mass of "+owner.getPubicHairType().getFullDescription(owner, true)+" around [npc.her] [npc.pussy].");
						break;
				}
			}
		}
		
		
		if (isPlayer && !owner.isVaginaUrethraVirgin()) {
			for(SexAreaPenetration pt : SexAreaPenetration.values()) {
				if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_VAGINA, pt))!=null) {
					descriptionSB.append(" <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_VAGINA, pt))+ "</span>");
					break;
				}
			}
		}
		
		return UtilText.parse(owner, descriptionSB.toString());
	}

	public String getMoundDescription(GameCharacter owner) {
		return UtilText.parse(owner,
				"As [npc.she] [npc.verb(possess)] no genitalia, all [npc.she] [npc.has] is a genderless mound."
				+ " Despite [npc.her] lack of sexual organs, it's still an incredibly sensitive area, and [npc.she] can be brought to a quasi-orgasm by stimulating it.");
	}
	
	public String getSpinneretDescription(GameCharacter owner) {
		if(!owner.hasSpinneret()) {
			return "";
		}
		
		descriptionSB = new StringBuilder();
		
		if(owner.hasLegSpinneret()) {
			descriptionSB.append("At the end of [npc.her] [npc.legRace]'s abdomen, ");
		} else {
			descriptionSB.append("At the end of [npc.her] [npc.tail+], ");
		}
		descriptionSB.append("[npc.she] [npc.has] a [npc.spinneretFullDescription(true)]. It is not only capable of producing strong webbing, but can also be used as a sexual orifice.");
		
		// Virgin/capacity:
		if(spinneret.isVirgin()) {
			descriptionSB.append(" [npc.She] [npc.has] [style.colourExcellent(retained [npc.her] spinneret virginity)].");
			
		} else {
			boolean virginityLossFound = false;
			for(SexAreaPenetration pt : SexAreaPenetration.values()) {
				if(pt.isTakesVirginity()) {
					if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.SPINNERET, pt))!=null) {
						descriptionSB.append(" [style.colourArcane("+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.SPINNERET, pt)) + ")]");
						virginityLossFound = true;
						break;
					}
				}
			}
			if(!virginityLossFound) {
				descriptionSB.append(" [style.colourArcane([npc.Name] [npc.has] lost [npc.her] spinneret virginity.)]");
			}
		}
		
		descriptionSB.append(" [npc.Her] spinneret is " + Capacity.getCapacityFromValue(spinneret.getStretchedCapacity()).getDescriptor(true)+" and when lubricated can comfortably accommodate objects of up to"
				+ " [style.colourSex("+ Units.size(Capacity.getMaximumComfortableDiameter(spinneret.getElasticity(), spinneret.getRawCapacityValue(), true)) + ")] in diameter.");

		if(Main.game.isPenetrationLimitationsEnabled()) {
			switch(owner.getSpinneretDepth()) {
				default:
					descriptionSB.append(" It is <span style='color:"+owner.getSpinneretDepth().getColour().toWebHexString()+";'>[npc.spinneretDepth]</span>,");
					break;
				case TWO_AVERAGE:
					descriptionSB.append(" It is of an <span style='color:"+owner.getSpinneretDepth().getColour().toWebHexString()+";'>average depth</span>,");
					break;
			}
			if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
				if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
					descriptionSB.append(" and as [npc.sheIsFull] a "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", [npc.she] can be pushed to [style.colourMinorGood(comfortably)] accommodate objects of a maximum length of "
							+ " [style.colourSex("+Units.size(owner.getSpinneretMaximumPenetrationDepthUncomfortable())+")].");
				} else {
					descriptionSB.append(" allowing [npc.herHim] to [style.colourMinorGood(comfortably)] accommodate [style.colourSex("+Units.size(owner.getSpinneretMaximumPenetrationDepthComfortable())+")] of a penetrative object,"
							+ " and [style.colourMinorBad(uncomfortably)] accommodate [style.colourSex("+Units.size(owner.getSpinneretMaximumPenetrationDepthUncomfortable())+")].");
				}
				
			} else {
				if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
					descriptionSB.append(" and as [npc.sheIsFull] a "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", combined with the fact that [npc.her] body is made out of [npc.bodyMaterial],"
							+ " [npc.she] can be pushed to [style.colourMinorGood(comfortably)] accommodate objects of [style.colourSex(any length)].");
				} else {
					descriptionSB.append(" allowing [npc.herHim] to [style.colourMinorGood(comfortably)] accommodate [style.colourSex("+Units.size(owner.getSpinneretMaximumPenetrationDepthComfortable())+")] of a penetrative object,"
							+ " and as [npc.her] body is made out of [npc.bodyMaterial], [npc.she] can be pushed to [style.colourMinorBad(uncomfortably)] accommodate objects of [style.colourSex(any length)].");
				}
			}
		}
		
		// Wetness:
		switch (spinneret.getWetness(owner)) {
			case ZERO_DRY:
				descriptionSB.append(" It's [style.colourWetness(completely dry and never gets wet)], no matter how aroused [npc.she] [npc.is].");
				break;
			case ONE_SLIGHTLY_MOIST:
				descriptionSB.append(" It's [style.colourWetness(slightly moist)], and [npc.she] [npc.verb(need)] a huge amount of stimulation before [npc.she] [npc.verb(get)] wet.");
				break;
			case TWO_MOIST:
				descriptionSB.append(" It's [style.colourWetness(moist)], but [npc.she] still [npc.verb(need)] a lot of stimulation before [npc.she] [npc.verb(get)] wet.");
				break;
			case THREE_WET:
				descriptionSB.append(" It's of an [style.colourWetness(average wetness)], and [npc.she] only [npc.verb(need)] a small amount of foreplay before [npc.sheIs] wet enough for a pleasurable penetration.");
				break;
			case FOUR_SLIMY:
				descriptionSB.append(" It's always [style.colourWetness(slimy and wet)], and [npc.sheIs] ready for penetration at a moment's notice.");
				break;
			case FIVE_SLOPPY:
				descriptionSB.append(" Its surface is always coated in [style.colourWetness(slimy moisture)], and within, [npc.her] spinneret is permanently [style.colourWetness(sloppy)] and practically begging to be fucked.");
				break;
			case SIX_SOPPING_WET:
				descriptionSB.append(" [npc.Her] spinneret is never anything less than [style.colourWetness(sopping wet)], and a trickle of [npc.her] natural lubricant constantly dribbles from it.");
				break;
			case SEVEN_DROOLING:
				descriptionSB.append(" [npc.Her] spinneret is [style.colourWetness(so wet that it audibly squelches with every step [npc.she] [npc.verb(take)])], and a constant stream of juices flow from [npc.her] inviting orifice.");
				break;
		}
		
		// Elasticity & plasticity:
		switch (spinneret.getElasticity()) {
			case ZERO_UNYIELDING:
				descriptionSB.append(" It is [style.colourElasticity(extremely unyielding)],");
				break;
			case ONE_RIGID:
				descriptionSB.append(" It [style.colourElasticity(takes a huge amount of effort to stretch it out)],");
				break;
			case TWO_FIRM:
				descriptionSB.append(" It [style.colourElasticity(does not stretch very easily)],");
				break;
			case THREE_FLEXIBLE:
				descriptionSB.append(" It [style.colourElasticity(reluctantly stretches out)] when penetrated,");
				break;
			case FOUR_LIMBER:
				descriptionSB.append(" It is [style.colourElasticity(somewhat resistant to being stretched out)],");
				break;
			case FIVE_STRETCHY:
				descriptionSB.append(" It [style.colourElasticity(stretches out fairly easily)],");
				break;
			case SIX_SUPPLE:
				descriptionSB.append(" It [style.colourElasticity(stretches out very easily)],");
				break;
			case SEVEN_ELASTIC:
				descriptionSB.append(" It is [style.colourElasticity(extremely elastic)],");
				break;
		}
		descriptionSB.append(" and after being used, it "+spinneret.getPlasticity().getDescription()+".");
		
		for(OrificeModifier om : OrificeModifier.values()) {
			if(owner.hasSpinneretOrificeModifier(om)) {
				switch(om) {
					case MUSCLE_CONTROL:
						descriptionSB.append(" [npc.She] [npc.has] a series of internal muscles lining the inside of [npc.her] spinneret, allowing [npc.herHim] to expertly squeeze and grip down on any intruding object.");
						break;
					case PUFFY:
						descriptionSB.append(" The rim has swollen up into being particularly big and puffy.");
						break;
					case RIBBED:
						descriptionSB.append(" The inside of [npc.her] spinneret is lined with sensitive, fleshy ribs, which grant [npc.herHim] extra pleasure when stimulated.");
						break;
					case TENTACLED:
						descriptionSB.append(" [npc.Her] spinneret is filled with tiny little tentacles, which wriggle and squirm with a mind of their own.");
						break;
				}
			}
		}
		
		return UtilText.parse(owner, descriptionSB.toString());
	}
	
	private String getSexDetails(GameCharacter owner) {
		
		if(owner.getTotalTimesHadSex(Main.game.getPlayer()) >=1) {
			descriptionSB = new StringBuilder();

			descriptionSB.append(getHeader("Sex History"));
			
			// Amount of sex:
			
			descriptionSB.append(
					UtilText.parse(owner,
							"<span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>"
							+ "You have had sex with [npc.name] "+Util.intToString(owner.getTotalTimesHadSex(Main.game.getPlayer()))+" "+(owner.getTotalTimesHadSex(Main.game.getPlayer())==1?"time.":"times.")
						+"</span>"));
			
			if(owner.getSexConsensualCount(Main.game.getPlayer())>=1) {
				if(owner.getSexConsensualCount(Main.game.getPlayer()) == owner.getTotalTimesHadSex(Main.game.getPlayer())) {
					if(owner.getTotalTimesHadSex(Main.game.getPlayer())==1) {
						descriptionSB.append(UtilText.parse(owner," The one time you had sex with [npc.herHim], it was consensual."));
					} else {
						descriptionSB.append(UtilText.parse(owner," All "+Util.intToString(owner.getTotalTimesHadSex(Main.game.getPlayer()))+" times were consensual."));
					}
					
				} else {
					if(owner.getTotalTimesHadSex(Main.game.getPlayer())==1) {
						descriptionSB.append(UtilText.parse(owner," The one time you had sex with [npc.herHim], it was consensual."));
					} else {
						descriptionSB.append(UtilText.parse(owner," "+Util.capitaliseSentence(Util.intToString(owner.getSexConsensualCount(Main.game.getPlayer())))+" of these times were consensual."));
					}
				}
			}
			if(owner.getSexAsSubCount(Main.game.getPlayer())>=1) {
				if(owner.getSexAsSubCount(Main.game.getPlayer()) == owner.getTotalTimesHadSex(Main.game.getPlayer())) {
					if(owner.getTotalTimesHadSex(Main.game.getPlayer())==1) {
						descriptionSB.append(UtilText.parse(owner," The one time you had sex with [npc.herHim], you were the dominant partner."));
					} else {
						descriptionSB.append(UtilText.parse(owner," All "+Util.intToString(owner.getTotalTimesHadSex(Main.game.getPlayer()))+" times you were the dominant partner."));
					}
					
				} else {
					if(owner.getTotalTimesHadSex(Main.game.getPlayer())==1) {
						descriptionSB.append(UtilText.parse(owner," The one time you had sex with [npc.herHim], you were the dominant partner."));
					} else {
						descriptionSB.append(UtilText.parse(owner," "+Util.capitaliseSentence(Util.intToString(owner.getSexAsSubCount(Main.game.getPlayer())))+" of these times you were the dominant partner."));
					}
				}
			}
			if(owner.getSexAsDomCount(Main.game.getPlayer())>=1) {
				if(owner.getSexAsDomCount(Main.game.getPlayer()) == owner.getTotalTimesHadSex(Main.game.getPlayer())) {
					if(owner.getTotalTimesHadSex(Main.game.getPlayer())==1) {
						descriptionSB.append(UtilText.parse(owner," The one time you had sex with [npc.herHim], you were the submissive partner."));
					} else {
						descriptionSB.append(UtilText.parse(owner," All "+Util.intToString(owner.getTotalTimesHadSex(Main.game.getPlayer()))+" times you were the submissive partner."));
					}
					
				} else {
					if(owner.getTotalTimesHadSex(Main.game.getPlayer())==1) {
						descriptionSB.append(UtilText.parse(owner," The one time you had sex with [npc.herHim], you were the submissive partner."));
					} else {
						descriptionSB.append(UtilText.parse(owner," "+Util.capitaliseSentence(Util.intToString(owner.getSexAsDomCount(Main.game.getPlayer())))+" of these times you were the submissive partner."));
					}
				}
			}
			descriptionSB.append("</p>");

			return UtilText.parse(owner, descriptionSB.toString());
		
		} else {
			return "";
		}
	}
	
	private String getPregnancyDetails(GameCharacter owner) {
		descriptionSB = new StringBuilder();
		
		boolean sectionAdded = false;
		// NPC is mother:
		
		if(owner.isVisiblyPregnant()) {
			descriptionSB.append(getHeader("Pregnancy"));
			GameCharacter father = owner.getPregnantLitter().getFather();
			if(father == null) {
				descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>From one of [npc.her] sexual encounters, [npc.name] has ended up getting impregnated.</span>");
			} else if(father.isPlayer()) {
				descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>From one of your sexual encounters, you've ended up impregnating [npc.name].</span>");
			} else {
				descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>From one of [npc.her] sexual encounters, [npc.name] has ended up getting impregnated by "+father.getName(true)+".</span>");
			}
			
			if(owner.hasStatusEffect(StatusEffect.PREGNANT_1)) {
				descriptionSB.append(" [npc.Her] belly is only a little swollen, as [npc.sheIs] only in the first stage of pregnancy.");
			} else if(owner.hasStatusEffect(StatusEffect.PREGNANT_2)) {
				descriptionSB.append(" [npc.Her] belly is noticeably swollen, as [npc.sheIs] well into [npc.her] pregnancy.");
			} else {
				descriptionSB.append(" [npc.Her] belly is massively swollen, and although [npc.sheIs] clearly ready for it, [npc.sheHasFull]n't decided to give birth just yet.");
			}
			descriptionSB.append("</p>");
			sectionAdded = true;
		}
		
		if(!owner.getLittersBirthed().isEmpty()) {
			if(!sectionAdded) {
				descriptionSB.append(getHeader("Pregnancy"));
			} else {
				descriptionSB.append("<p>");
			}
			descriptionSB.append(
					"<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"
						+ "[npc.Name] has given birth "+Util.intToString(owner.getLittersBirthed().size())+" "+(owner.getLittersBirthed().size()==1?"time":"times")+".</span>");
			
			for(Litter litter : owner.getLittersBirthed()) {
				if(litter.getFather() == null) {
					descriptionSB.append("<br/>On "+Units.date(litter.getConceptionDate(), Units.DateType.LONG)
							+", [npc.she] was impregnated, and then on "+Units.date(litter.getBirthDate(), Units.DateType.LONG)+", [npc.she] gave birth to ");
					
				} else if(litter.getFather().isPlayer()) {
					descriptionSB.append("<br/>On "+Units.date(litter.getConceptionDate(), Units.DateType.LONG)
							+", you impregnated [npc.herHim], and then on "+Units.date(litter.getBirthDate(), Units.DateType.LONG)+", [npc.she] gave birth to ");
					
				} else {
					descriptionSB.append("<br/>On "+Units.date(litter.getConceptionDate(), Units.DateType.LONG)+", "+litter.getFather().getName(true)
							+" impregnated [npc.herHim], and then on "+Units.date(litter.getBirthDate(), Units.DateType.LONG)+", [npc.she] gave birth to ");
				}
				
				descriptionSB.append(litter.getBirthedDescription());
				
				descriptionSB.append(".");
			}
			
			descriptionSB.append("</p>");
			sectionAdded = true;
		}
		
		// NPC is father:
		
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			for(PregnancyPossibility pp : Main.game.getPlayer().getPotentialPartnersAsMother()) {
				if(pp.getFather()!=null && pp.getFather().equals(owner)) {
					if(!sectionAdded) {
						descriptionSB.append(getHeader("Pregnancy"));
					} else {
						descriptionSB.append("<p>");
					}
					descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>From one of your sexual encounters, you've been impregnated, and it's possible that [npc.name] is the father.</span>");
					descriptionSB.append("</p>");
					sectionAdded = true;
					break;
				}
			}
		}
		
		if(!Main.game.getPlayer().getLittersBirthed().isEmpty()) {
			int fatheredLitters = 0;
			
			for(Litter litter : Main.game.getPlayer().getLittersBirthed()) {
				if(litter.getFather()!=null && litter.getFather().equals(owner)){
					fatheredLitters++;
				}
			}
			
			if(fatheredLitters!=0) {
				if(!sectionAdded) {
					descriptionSB.append(getHeader("Pregnancy"));
				} else {
					descriptionSB.append("<p>");
				}
				descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"
							+ "[npc.Name] is the father of some of your children, and has, in total, impregnated you "+Util.intToString(fatheredLitters)+" "+(fatheredLitters==1?"time":"times")+".</span>");
				
				for(Litter litter : Main.game.getPlayer().getLittersBirthed()) {
					if(litter.getFather()!=null && litter.getFather().equals(owner)){
						descriptionSB.append("<br/>On "+Units.date(litter.getConceptionDate(), Units.DateType.LONG)
								+", [npc.she] impregnated you, and then on "+Units.date(litter.getBirthDate(), Units.DateType.LONG)+", you gave birth to "+litter.getBirthedDescription()+".");
					}
				}
				
				descriptionSB.append("</p>");
			}
		}

		return UtilText.parse(owner, descriptionSB.toString());
	}
	

	private String getIncubationPregnancyDetails(GameCharacter owner) {
		descriptionSB = new StringBuilder();
		boolean sectionAdded = false;
		
		if(!owner.getIncubatingLitters().isEmpty()) {
			sectionAdded = true;
			descriptionSB.append(getHeader("Egg Incubation"));
			int i=0;
			for(Entry<SexAreaOrifice, Litter> entry : owner.getIncubatingLitters().entrySet()) {
				if(i>0) {
					descriptionSB.append("<br/>");
				}
				String areaEgged = "";
				String stage = "";
				switch(entry.getKey()) {
					case ANUS:
					case MOUTH:
						areaEgged = "stomach";
						if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_STOMACH_1)) {
							stage = " [npc.Her] belly is only a little swollen, as the eggs in [npc.her] stomach have only recently been implanted.";
						} else if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_STOMACH_2)) {
							stage = " [npc.Her] belly is noticeably swollen, as the eggs in [npc.her] stomach have had time in which to mature and grow.";
						} else {
							stage = " [npc.Her] belly is massively swollen, and although [npc.sheIs] clearly ready for it, [npc.sheHasFull]n't decided to lay the eggs which [npc.sheHas] incubated in [npc.her] stomach just yet.";
						}
						break;
					case NIPPLE:
						areaEgged = "[npc.breasts]";
						if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_1)) {
							stage = " [npc.Her] [npc.breasts] are only a little swollen, as the eggs in [npc.her] [npc.nipples] have only recently been implanted.";
						} else if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_2)) {
							stage = " [npc.Her] [npc.breasts] are noticeably swollen, as the eggs in [npc.her] [npc.nipples] have had time in which to mature and grow.";
						} else {
							stage = " [npc.Her] [npc.breasts] are massively swollen, and although [npc.sheIs] clearly ready for it, [npc.sheHasFull]n't decided to lay the eggs which [npc.sheHas] incubated in [npc.her] [npc.nipples] just yet.";
						}
						break;
					case NIPPLE_CROTCH:
						areaEgged = "[npc.crotchBoobs]";
						if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_1)) {
							stage = " [npc.Her] [npc.crotchBoobs] are only a little swollen, as the eggs in [npc.her] [npc.crotchNipples] have only recently been implanted.";
						} else if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_2)) {
							stage = " [npc.Her] [npc.crotchBoobs] are noticeably swollen, as the eggs in [npc.her] [npc.crotchNipples] have had time in which to mature and grow.";
						} else {
							stage = " [npc.Her] [npc.crotchBoobs] are massively swollen, and although [npc.sheIs] clearly ready for it, [npc.sheHasFull]n't decided to lay the eggs which [npc.sheHas] incubated in [npc.her] [npc.crotchNipples] just yet.";
						}
						break;
					case SPINNERET:
						areaEgged = "[npc.spinneret]";
						String spinneretArea = owner.hasTailSpinneret()?"[npc.tail]":"abdomen";
						if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_SPINNERET_1)) {
							stage = " [npc.Her] "+spinneretArea+" is only a little swollen, as the eggs in [npc.her] spinneret have only recently been implanted.";
						} else if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_SPINNERET_2)) {
							stage = " [npc.Her] "+spinneretArea+" is noticeably swollen, as the eggs in [npc.her] spinneret have had time in which to mature and grow.";
						} else {
							stage = " [npc.Her] "+spinneretArea+" is massively swollen, and although [npc.sheIs] clearly ready for it, [npc.sheHasFull]n't decided to lay the eggs which [npc.sheHas] incubated in [npc.her] spinneret just yet.";
						}
						break;
					case VAGINA:
						areaEgged = "womb";
						if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_WOMB_1)) {
							stage = " [npc.Her] belly is only a little swollen, as the eggs in [npc.her] womb have only recently been implanted.";
						} else if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_WOMB_2)) {
							stage = " [npc.Her] belly is noticeably swollen, as the eggs in [npc.her] womb have had time in which to mature and grow.";
						} else {
							stage = " [npc.Her] belly is massively swollen, and although [npc.sheIs] clearly ready for it, [npc.sheHasFull]n't decided to lay the eggs which [npc.sheHas] incubated in [npc.her] womb just yet.";
						}
						break;
					case ARMPITS:
					case ASS:
					case BREAST:
					case BREAST_CROTCH:
					case THIGHS:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
						break;
				}
				GameCharacter mother = entry.getValue().getMother();
				descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>");
					if(mother == null) {
						descriptionSB.append("From one of [npc.her] sexual encounters, [npc.name] has [npc.her] "+areaEgged+" filled with eggs.");
					} else if(mother.isPlayer()) {
						descriptionSB.append("From one of your sexual encounters, you've ended up filling [npc.namePos] "+areaEgged+" with eggs.");
					} else {
						descriptionSB.append("From one of [npc.her] sexual encounters, [npc.name] has ended up having [npc.her] "+areaEgged+" filled with eggs by "+mother.getName(true)+".");
					}
				descriptionSB.append("</span>");
				descriptionSB.append(stage);
				i++;
			}
			descriptionSB.append("</p>");
		}
		
		if(!owner.getLittersIncubated().isEmpty()) {
			if(!sectionAdded) {
				descriptionSB.append(getHeader("Egg Incubation"));
			} else {
				descriptionSB.append("<p>");
			}
			sectionAdded = true;
				descriptionSB.append(
						"<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"
							+ "[npc.Name] has incubated and laid eggs "+Util.intToString(owner.getLittersIncubated().size())+" "+(owner.getLittersIncubated().size()==1?"time":"times")+"."
						+ "</span>");

				//Litter.getMother is the character who passed on the eggs to the incubator
				for(Litter litter : owner.getLittersIncubated()) {
					if(litter.getMother()==null) {
						descriptionSB.append("<br/>On "+Units.date(litter.getConceptionDate(), Units.DateType.LONG)
								+", [npc.she] was implanted with "+litter.getTotalLitterCount()+" eggs, and then on "+Units.date(litter.getBirthDate(), Units.DateType.LONG)+", [npc.she] laid and birthed ");
						
					} else if(litter.getMother().isPlayer()) {
						descriptionSB.append("<br/>On "+Units.date(litter.getConceptionDate(), Units.DateType.LONG)
								+", you implanted [npc.herHim] with "+litter.getTotalLitterCount()+" eggs, and then on "+Units.date(litter.getBirthDate(), Units.DateType.LONG)+", [npc.she] laid and birthed ");
						
					} else {
						descriptionSB.append("<br/>On "+Units.date(litter.getConceptionDate(), Units.DateType.LONG)+", "+litter.getMother().getName(true)
								+" implanted [npc.herHim] with "+litter.getTotalLitterCount()+" eggs, and then on "+Units.date(litter.getBirthDate(), Units.DateType.LONG)+", [npc.she] laid and birthed ");
					}
					
					descriptionSB.append(litter.getBirthedDescription());
					
					descriptionSB.append(".");
				}
			descriptionSB.append("</p>");
		}
		
		// NPC is egg implanter:
		
		if(!Main.game.getPlayer().getIncubatingLitters().isEmpty()) {
			List<String> areasEgged = new ArrayList<>();
			for(Entry<SexAreaOrifice, Litter> entry : Main.game.getPlayer().getIncubatingLitters().entrySet()) {
				if(entry.getValue().getMother()!=null && entry.getValue().getMother().equals(owner)) {
					String areaEgged = "";
					switch(entry.getKey()) {
						case ANUS:
						case MOUTH:
							areaEgged = "stomach";
							break;
						case NIPPLE:
							areaEgged = "[pc.nipples]";
							break;
						case NIPPLE_CROTCH:
							areaEgged = "[pc.crotchNipples]";
							break;
						case SPINNERET:
							areaEgged = "spinneret";
							break;
						case VAGINA:
							areaEgged = "womb";
							break;
						case ARMPITS:
						case ASS:
						case BREAST:
						case BREAST_CROTCH:
						case THIGHS:
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							break;
					}
					areasEgged.add(areaEgged);
				}
			}
			if(!areasEgged.isEmpty()) {
				if(!sectionAdded) {
					descriptionSB.append(getHeader("Egg Incubation"));
				} else {
					descriptionSB.append("<p>");
				}
				sectionAdded = true;
				descriptionSB.append(
						"<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"
							+ "[npc.NameIsFull] the one responsible for implanting the eggs that are currently in your "+Util.stringsToStringList(areasEgged, false)+"."
						+ "</span>");
				descriptionSB.append("</p>");
			}
		}
		
		if(!Main.game.getPlayer().getLittersIncubated().isEmpty()) {
			int incubatedLitters = 0;
			
			for(Litter litter : Main.game.getPlayer().getLittersIncubated()) {
				if(litter.getMother()!=null && litter.getMother().equals(owner)) {
					incubatedLitters++;
				}
			}
			
			if(incubatedLitters!=0) {
				if(!sectionAdded) {
					descriptionSB.append(getHeader("Egg Incubation"));
				} else {
					descriptionSB.append("<p>");
				}
				descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"
							+ "[npc.Name] is responsible for previously having laid [npc.her] eggs in you, and in total, [npc.sheHasFull] done so "+Util.intToString(incubatedLitters)+" "+(incubatedLitters==1?"time":"times")+".</span>");
				
				for(Litter litter : Main.game.getPlayer().getLittersIncubated()) {
					if(litter.getMother()!=null && litter.getMother().equals(owner)) {
						descriptionSB.append("<br/>On "+Units.date(litter.getIncubationStartDate(), Units.DateType.LONG)
								+", [npc.she] implanted a clutch of eggs in you, and then on "+Units.date(litter.getBirthDate(), Units.DateType.LONG)+", you laid and hatched "+litter.getBirthedDescription()+".");
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
		boolean hasPenis = penis.getType() != PenisType.NONE;
		boolean hasVagina = vagina.getType() != VaginaType.NONE;
		boolean hasBreasts = breast.hasBreasts();
		if(this.isFeral() && this.getSubspecies().getFeralAttributes(this)!=null) {
			hasBreasts = this.getSubspecies().getFeralAttributes(this).isBreastsPresent() || this.getBreastCrotch().hasBreasts();
		}
		
		// Looks male:
		if (femininity <= Femininity.MASCULINE.getMaximumFemininity()) {
			if(hasPenis) {
				if(hasVagina) {
					if(hasBreasts) {
						return Gender.M_P_V_B_HERMAPHRODITE;
					} else {
						return Gender.M_P_V_HERMAPHRODITE;
					}
				} else {
					if(hasBreasts) {
						return Gender.M_P_B_BUSTYBOY;
					} else {
						return Gender.M_P_MALE;
					}
				}
			} else {
				if(hasVagina) {
					if(hasBreasts) {
						return Gender.M_V_B_BUTCH;
					} else {
						return Gender.M_V_CUNTBOY;
					}
				} else {
					if(hasBreasts) {
						return Gender.M_B_MANNEQUIN;
					} else {
						return Gender.M_MANNEQUIN;
					}
				}
			}

		// Looks androgynous:
		} else if (femininity <= Femininity.ANDROGYNOUS.getMaximumFemininity()){
			if(hasPenis) {
				if(hasVagina) {
					if(hasBreasts) {
						return Gender.N_P_V_B_HERMAPHRODITE;
					} else {
						return Gender.N_P_V_HERMAPHRODITE;
					}
				} else {
					if(hasBreasts) {
						return Gender.N_P_B_SHEMALE;
					} else {
						return Gender.N_P_TRAP;
					}
				}
			} else {
				if(hasVagina) {
					if(hasBreasts) {
						return Gender.N_V_B_TOMBOY;
					} else {
						return Gender.N_V_TOMBOY;
					}
				} else {
					if(hasBreasts) {
						return Gender.N_B_DOLL;
					} else {
						return Gender.N_NEUTER;
					}
				}
			}
		
		// Looks feminine:
		} else {
			if(hasPenis) {
				if(hasVagina) {
					if(hasBreasts) {
						return Gender.F_P_V_B_FUTANARI;
					} else {
						return Gender.F_P_V_FUTANARI;
					}
				} else {
					if(hasBreasts) {
						return Gender.F_P_B_SHEMALE;
					} else {
						return Gender.F_P_TRAP;
					}
				}
			} else {
				if(hasVagina) {
					if(hasBreasts) {
						return Gender.F_V_B_FEMALE;
					} else {
						return Gender.F_V_FEMALE;
					}
				} else {
					if(hasBreasts) {
						return Gender.F_B_DOLL;
					} else {
						return Gender.F_DOLL;
					}
				}
			}
			
		}
	}

	/**
	 * @return weight in kilograms
	 */
	public int getCalculatedWeight() {
		//TODO?
		// Was this ever going to be used?
		// If so, it should take into account BodySize and Muscle

		// Weight = 0.4 * height
		int weight = (int) (height * 0.4f);

		// If harpy wings, your bones & muscles are really light, so *0.5
		if (arm.getType().equals(ArmType.HARPY)) {
			weight *= 0.5;
		}

		// If tauric lower body, weight is based on horse-sized animal, so weight*0.6 + 250 (horses are 400kg at lightest and centaur bodies are smaller than horse's)
		if (leg.getLegConfiguration()==LegConfiguration.QUADRUPEDAL
				|| leg.getLegConfiguration()==LegConfiguration.ARACHNID
				|| leg.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
			weight *= 0.6;
			weight += 250;
		}

		return weight;
	}
	
	/**
	 * @return true if this character's Height value is less than Height.ZERO_TINY. This means that a fairy-sized body will also return true for this method.
	 */
	public boolean isShortStature() {
		return this.getHeight().isShortStature();
	}
	
	public boolean isFairySized() {
		return this.getHeight().isFairySized();
	}
	
	/** Height is measured in cm. **/
	public int getHeightValue() {
		return height;
	}
	
	public Height getHeight() {
		return Height.getHeightFromInt(height);
	}

	/**
	 * Sets height attribute. Bound between 15cm (5.9 inches) and 365cm (12 feet).
	 * 
	 * @param height Value to set height to.
	 * @return True if height was changed.
	 */
	public boolean setHeight(int height) {
		if (this.height == height) {
			return false;
		}
		
		this.height = Math.max(Height.NEGATIVE_THREE_MINIMUM.getMinimumValue(), Math.min(height, Height.SEVEN_COLOSSAL.getMaximumValue()));

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
		
		addDiscoveredBodyCoveringsFromMaterial(bodyMaterial);
		
		this.bodyMaterial = bodyMaterial;
		
		return true;
	}

	public GenitalArrangement getGenitalArrangement() {
		return genitalArrangement;
	}

	public void setGenitalArrangement(GenitalArrangement genitalArrangement) {
		this.genitalArrangement = genitalArrangement;
	}

	public boolean isFeral() {
		return feral;
	}

	public boolean isFeralOrHasLegConfiguration(LegConfiguration... values) {
		return feral || leg.getLegConfiguration().isOneOf(values);
	}

	/**
	 * @param subspecies Pass in the AbstractSubspecies to which this character should be transformed into a feral version of. Pass in null to transform back from feral to a standard anthro.
	 */
	public void setFeral(GameCharacter target, AbstractSubspecies subspecies) {
		AbstractSubspecies targetSubspecies = subspecies == null ? getSubspecies() : subspecies;
		FeralAttributes attributes = targetSubspecies.getFeralAttributes(this);
		if(attributes==null) {
			System.err.println("Error in Body.setFeral(): subspecies '"+Subspecies.getIdFromSubspecies(targetSubspecies)+"' does not support FeralAttributes!");
			return;
		}
		
		this.feral = subspecies!=null;
		// Set body to full subspecies:
		Main.game.getCharacterUtils().reassignBody(
				target,
				this,
				this.getGender(),
				targetSubspecies,
				RaceStage.GREATER,
				false);
		
		if (subspecies == null) {
			return; 
		}
		// Set feral-specific attributes:
		this.getLeg().getType().applyLegConfigurationTransformation(this, attributes.getLegConfiguration(), true);
		
//		this.getLeg().setLegConfigurationForced(this.getLeg().getType(), attributes.getLegConfiguration());
		this.setHeight(attributes.getSize());
		this.getLeg().setLengthAsPercentageOfHeight(null, attributes.getSerpentTailLength());
		
		// Set breast and crotch-boob counts:
		this.getBreast().setRows(null, attributes.getBreastRowCount());
		this.getBreast().setNippleCountPerBreast(null, attributes.getNipplesPerBreastCount());
		this.getBreastCrotch().setRows(null, attributes.getCrotchBreastRowCount());
		this.getBreastCrotch().setNippleCountPerBreast(null, attributes.getNipplesPerCrotchBreastCount());
		if(attributes.getCrotchBreastRowCount() == 0) {
			this.getBreastCrotch().setType(null, BreastType.NONE);
		}
		
		// Set genital relative sizes:
		AbstractRacialBody rb = targetSubspecies.getRace().getRacialBody();
		float proportionSizeDifference = ((float)attributes.getSize())/(this.isFeminine()?rb.getFemaleHeight():rb.getMaleHeight());
		this.getPenis().setPenisLength(null, (int) (rb.getPenisSize()*proportionSizeDifference));
		this.getPenis().setPenisGirth(null, (int) (rb.getPenisGirth()*proportionSizeDifference));
		this.getPenis().getTesticle().setTesticleSize(null, (int) (rb.getTesticleSize()*proportionSizeDifference));
		
		// Set hair:
		this.getHair().setStyle(null, HairStyle.NONE);
		if(attributes.isHairPresent()) {
			this.getHair().setLength(null, HairLength.TWO_SHORT.getMedianValue());
		} else {
			this.getHair().setLength(null, 0);
		}
		
		removeAllMakeup();
		
		CharacterModificationUtils.resetCoveringsToBeApplied();
	}

	public void removeAllMakeup() {
		for(AbstractBodyCoveringType makeup : BodyCoveringType.allMakeupTypes) {
			if(coverings.containsKey(makeup)) {
				coverings.put(makeup, new Covering(makeup, CoveringPattern.NONE, CoveringModifier.SMOOTH, PresetColour.COVERING_NONE, false, PresetColour.COVERING_NONE, false));
			}
		}
		CharacterModificationUtils.resetCoveringsToBeApplied();
	}
	
	public boolean isPiercedStomach() {
		return piercedStomach;
	}

	public void setPiercedStomach(boolean piercedStomach) {
		this.piercedStomach = piercedStomach;
	}

	public Set<AbstractBodyCoveringType> getHeavyMakeup() {
		return heavyMakeup;
	}

	public boolean isHeavyMakeup(AbstractBodyCoveringType type) {
		return heavyMakeup.contains(type);
	}
	
	public void addHeavyMakeup(AbstractBodyCoveringType type) {
		heavyMakeup.add(type);
	}
	
	public boolean removeHeavyMakeup(AbstractBodyCoveringType type) {
		return heavyMakeup.remove(type);
	}

	public Map<AbstractBodyCoveringType, Covering> getCoverings() {
		return coverings;
	}

	/**
	 * This should only be used in special cases, where the covering map needs to be overwritten for some reason!
	 */
	public void setCoverings(Map<AbstractBodyCoveringType, Covering> coverings) {
		this.coverings = coverings;
	}
	
	public void setCovering(AbstractBodyCoveringType coveringType, CoveringPattern pattern, CoveringModifier modifier, Colour primaryColor, boolean primaryGlow, Colour secondaryColor, boolean secondaryGlow) {
		coverings.put(coveringType, new Covering(coveringType, pattern, modifier, primaryColor, primaryGlow, secondaryColor, secondaryGlow));
	}

	public void setCovering(AbstractBodyCoveringType coveringType, CoveringPattern pattern, Colour primaryColor, boolean primaryGlow, Colour secondaryColor, boolean secondaryGlow) {
		coverings.put(coveringType, new Covering(coveringType, pattern, primaryColor, primaryGlow, secondaryColor, secondaryGlow));
	}

	public AbstractBodyCoveringType getCoveringType(BodyPartInterface bodyPart) {
		return bodyPart.getBodyCoveringType(this);
	}

	public Covering getCoveringFromType(BodyPartInterface bodyPart) {
		return getCovering(bodyPart.getBodyCoveringType(this), false);
	}
	
	public Covering getCovering(AbstractBodyCoveringType bodyCoveringType, boolean accountForNonFleshMaterial) {
		if(!accountForNonFleshMaterial) {
			return this.getCoverings().get(bodyCoveringType);
		}
		AbstractBodyCoveringType handledType = this.getBodyMaterial()!=BodyMaterial.FLESH
												?BodyCoveringType.getMaterialBodyCoveringType(this.getBodyMaterial(), bodyCoveringType.getCategory())
												:bodyCoveringType;
		return this.getCoverings().get(handledType);
	}

	public CoveringModifier getCoveringModifier(AbstractBodyCoveringType bodyCoveringType, boolean accountForNonFleshMaterial) {
		return getCovering(bodyCoveringType, accountForNonFleshMaterial).getModifier();
	}

	public void setCoveringModifier(AbstractBodyCoveringType bodyCoveringType, boolean accountForNonFleshMaterial, CoveringModifier modifier) {
		getCovering(bodyCoveringType, accountForNonFleshMaterial).setModifier(modifier);
	}

	public CoveringPattern getCoveringPattern(AbstractBodyCoveringType bodyCoveringType, boolean accountForNonFleshMaterial) {
		return getCovering(bodyCoveringType, accountForNonFleshMaterial).getPattern();
	}

	public void setCoveringPattern(AbstractBodyCoveringType bodyCoveringType, boolean accountForNonFleshMaterial, CoveringPattern pattern) {
		getCovering(bodyCoveringType, accountForNonFleshMaterial).setPattern(pattern);
	}

	public boolean isBodyCoveringTypesDiscovered(AbstractBodyCoveringType bct) {
		return coveringsDiscovered.contains(bct);
	}

	public boolean addBodyCoveringTypesDiscovered(AbstractBodyCoveringType bct) {
		return coveringsDiscovered.add(bct);
	}
	
//	public Set<BodyCoveringType> getBodyCoveringTypesDiscovered() {
//		return coveringsDiscovered;
//	}
	
	/**
	 * Updates this body's covering to more realistic values.
	 * @param updateEyes If true, human eyes will be set to non-heterochromatic.
	 * @param updateHair If true, human hair and body hair will be set to unpatterned.
	 * @param updateBodyHairColours If true, the colour of all body hair values will be set to the matching colour of the body's head hair.
	 * @param updateSkin If true, all values for anus, lips, vagina, nipples, and penis will be set to the matching colour of the body's skin.
	 */
	public void updateCoverings(boolean updateEyes, boolean updateHair, boolean updateBodyHairColours, boolean updateSkin) {
		
//		if(this.getBodyMaterial()==BodyMaterial.SLIME) {
//			coverings.put(BodyCoveringType.SLIME, new Covering
//					(BodyCoveringType.SLIME, coverings.get(BodyCoveringType.SLIME).getPattern(), coverings.get(BodyCoveringType.SLIME).getPrimaryColour(), false, coverings.get(BodyCoveringType.SLIME).getPrimaryColour(), false));
//		}
		
		// Make eyes normal:
		if(updateEyes) {
			coverings.put(BodyCoveringType.EYE_HUMAN, new Covering
					(BodyCoveringType.EYE_HUMAN, CoveringPattern.EYE_IRISES, coverings.get(BodyCoveringType.EYE_HUMAN).getPrimaryColour(), false, coverings.get(BodyCoveringType.EYE_HUMAN).getPrimaryColour(), false));
		}
		
		// Make hair non-highlighted:
		if(updateHair) {
			coverings.put(BodyCoveringType.HAIR_HUMAN, new Covering
					(BodyCoveringType.HAIR_HUMAN, CoveringPattern.NONE, coverings.get(BodyCoveringType.HAIR_HUMAN).getPrimaryColour(), false, coverings.get(BodyCoveringType.HAIR_HUMAN).getPrimaryColour(), false));
	
			coverings.put(BodyCoveringType.BODY_HAIR_HUMAN, new Covering
					(BodyCoveringType.BODY_HAIR_HUMAN, CoveringPattern.NONE, coverings.get(BodyCoveringType.BODY_HAIR_HUMAN).getPrimaryColour(), false, coverings.get(BodyCoveringType.BODY_HAIR_HUMAN).getPrimaryColour(), false));
			
		}
		
		if(updateBodyHairColours) {
			for(AbstractRace race : Race.getAllRaces()) {
				if(!HairType.getHairTypes(race).isEmpty()) {
					coverings.put(race.getRacialBody().getBodyHairType(), new Covering(race.getRacialBody().getBodyHairType(), coverings.get(HairType.getHairTypes(race).get(0).getBodyCoveringType(this)).getPrimaryColour()));
				}
			}
		}
		
		// Make all orifice colours the same as their surroundings:
		if(updateSkin) {
			for(BodyMaterial mat : BodyMaterial.values()) { // Update all non-flesh parts to be the same colour as main skin:
				if(mat!=BodyMaterial.FLESH) {
					AbstractBodyCoveringType coreSlimeCovering = BodyCoveringType.getMaterialBodyCoveringType(mat, BodyCoveringCategory.MAIN_SKIN);
					Covering currentCovering = this.getCovering(coreSlimeCovering, true);
					
					for(BodyCoveringCategory cat : BodyCoveringCategory.values()) {
						if(cat.isInfluencedByMaterialType()) {
							AbstractBodyCoveringType nonFleshCovering = BodyCoveringType.getMaterialBodyCoveringType(mat, cat);
							CoveringPattern pattern = currentCovering.getPattern();
							if(!nonFleshCovering.getAllPatterns().keySet().contains(pattern)) {
								pattern = nonFleshCovering.getNaturalPatterns().entrySet().iterator().next().getKey();
							}
							CoveringModifier modifier = currentCovering.getModifier();
							if(!nonFleshCovering.getAllModifiers().contains(modifier)) {
								modifier = nonFleshCovering.getNaturalModifiers().get(0);
							}
							
							coverings.put(nonFleshCovering,
									new Covering(nonFleshCovering,
											pattern, //nonFleshCovering.getNaturalPatterns().entrySet().iterator().next().getKey(),
											modifier,
											coverings.get(coreSlimeCovering).getPrimaryColour(),
											false,
											coverings.get(coreSlimeCovering).getPrimaryColour(),
											false));
						}
					}
				}
			}
			
			updateAnusColouring();

			updateNippleColouring();
			
			updateNippleCrotchColouring();

			updateMouthColouring();

			updateVaginaColouring();
			
			updatePenisColouring();
			
			updateSpinneretColouring();
		}
	}
	
	public void updateAnusColouring() {
		if(ass.getType().getRace()==Race.ANGEL) {
			coverings.put(BodyCoveringType.ANUS, new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else if(ass.getType().getRace()==Race.DEMON) {
			coverings.put(BodyCoveringType.ANUS, new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else {
			coverings.put(BodyCoveringType.ANUS, new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
		}
	}
	
	public void updateNippleColouring() {
		if(breast.getType().getRace()==Race.ANGEL) {
			coverings.put(BodyCoveringType.NIPPLES, new Covering(BodyCoveringType.NIPPLES, CoveringPattern.ORIFICE_NIPPLE, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else if(breast.getType().getRace()==Race.DEMON) {
			coverings.put(BodyCoveringType.NIPPLES, new Covering(BodyCoveringType.NIPPLES, CoveringPattern.ORIFICE_NIPPLE, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else {
			coverings.put(BodyCoveringType.NIPPLES, new Covering(BodyCoveringType.NIPPLES, CoveringPattern.ORIFICE_NIPPLE, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
		}
	}
	
	public void updateNippleCrotchColouring() {
		if(breastCrotch.getType().getRace()==Race.ANGEL) {
			coverings.put(BodyCoveringType.NIPPLES_CROTCH,
					new Covering(BodyCoveringType.NIPPLES_CROTCH, CoveringPattern.ORIFICE_NIPPLE_CROTCH, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else if(breastCrotch.getType().getRace()==Race.DEMON) {
			coverings.put(BodyCoveringType.NIPPLES_CROTCH,
					new Covering(BodyCoveringType.NIPPLES_CROTCH, CoveringPattern.ORIFICE_NIPPLE_CROTCH, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else {
			coverings.put(BodyCoveringType.NIPPLES_CROTCH,
					new Covering(BodyCoveringType.NIPPLES_CROTCH, CoveringPattern.ORIFICE_NIPPLE_CROTCH, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
		}
	}
	
	public void updateMouthColouring() {
		if(face.getType().getRace()==Race.ANGEL) {
			coverings.put(BodyCoveringType.MOUTH, new Covering(BodyCoveringType.MOUTH, CoveringPattern.ORIFICE_MOUTH, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else if(face.getType().getRace()==Race.DEMON) {
			coverings.put(BodyCoveringType.MOUTH, new Covering(BodyCoveringType.MOUTH, CoveringPattern.ORIFICE_MOUTH, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else {
			coverings.put(BodyCoveringType.MOUTH, new Covering(BodyCoveringType.MOUTH, CoveringPattern.ORIFICE_MOUTH, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
		}
	}
	
	public void updateVaginaColouring() {
		AbstractRace race = vagina.getType()!=VaginaType.NONE?vagina.getType().getRace():getRace();
		if(race==Race.ANGEL) {
			coverings.put(BodyCoveringType.VAGINA, new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else if(race==Race.DEMON) {
			coverings.put(BodyCoveringType.VAGINA, new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else {
			coverings.put(BodyCoveringType.VAGINA, new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
		}
	}
	
	public void updatePenisColouring() {
		AbstractRace race = penis.getType()!=PenisType.NONE?penis.getType().getRace():getRace();
		if(race==Race.ANGEL) {
			coverings.put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, CoveringPattern.NONE, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else if(race==Race.DEMON) {
			coverings.put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, CoveringPattern.NONE, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else if(race==Race.DOG_MORPH || race==Race.WOLF_MORPH || race==Race.FOX_MORPH) {
			coverings.put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, CoveringPattern.NONE, PresetColour.SKIN_RED, false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else {
			coverings.put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, CoveringPattern.NONE, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
		}
	}

	public void updateSpinneretColouring() {
		coverings.put(BodyCoveringType.SPINNERET, new Covering(BodyCoveringType.SPINNERET, CoveringPattern.ORIFICE_SPINNERET, coverings.get(BodyCoveringType.VAGINA).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
	}
	
	public boolean isAbleToFlyFromArms() {
		if(this.getBodyMaterial()==BodyMaterial.SLIME
				|| this.getBodyMaterial()==BodyMaterial.SILICONE
				|| this.getLeg().getLegConfiguration().getMinimumWingSizeForFlight(this).getValue()>WingSize.THREE_LARGE.getValue()) {
			return false;
		}
		return arm.getType().allowsFlight();
	}
	
	public boolean isAbleToFlyFromWings() {
		if(this.getBodyMaterial()==BodyMaterial.SLIME || this.getBodyMaterial()==BodyMaterial.SILICONE) {
			return false;
		}
		return wing.getType().allowsFlight() && wing.getSize().getValue()>=this.getLeg().getLegConfiguration().getMinimumWingSizeForFlight(this).getValue();
	}

	public boolean isAbleToFlyFromExtraParts() {
		return getAllBodyParts().stream().anyMatch(bpi -> bpi.getType().getTags().contains(BodyPartTag.ALLOWS_FLIGHT));
	}

	/**
	 * Basically the opposite of isAbleToFly, but skips the minimumWingSizeForFlight-checks
	 * and the checks for slime bodies.
	 * @return true if the body is incapable of flight
	 */
	public Boolean isInCapableOfFlight() {
		return !(
			isAbleToFlyFromExtraParts() ||
			arm.getType().allowsFlight() ||
			wing.getType().allowsFlight());
	}

	public boolean isTakesAfterMother() {
		return takesAfterMother;
	}

	public void setTakesAfterMother(boolean takesAfterMother) {
		this.takesAfterMother = takesAfterMother;
	}

	/**
	 * Returns a randomly chosen BodyPart-type from the list of types (e. g. multiple WingTypes)
	 * provided as parameters. To give one or more types more weight these types can be repeated.
	 * @param values List of BodyPartTypes to choose from randomly
	 * @return The randomly chosen type of the corresponding BodyPart
	 */
	public BodyPartTypeInterface randomTypeFrom(BodyPartTypeInterface... values) {
		return Util.randomItemFrom(Util.newArrayListOfValues(values));
	}

	/**
	 * This is reset to null after every transformation, and is then recalculated in AbstractSubspecies.
	 * @return The subspecies which this character appears to be if they were made of flesh.
	 *  Use getTrueSubspecies() or do some checks with getSubspeciesOverride() to get their true Subspecies, but for 99.9% of the time, that won't be necessary and this method is fine to use.
	 */
	public AbstractSubspecies getFleshSubspecies() {
		if(fleshSubspecies==null) {
			fleshSubspecies = AbstractSubspecies.getSubspeciesFromBody(this, this.getRaceFromPartWeighting());
		}
		return fleshSubspecies;
	}

	public void setFleshSubspecies(AbstractSubspecies fleshSubspecies) {
		this.fleshSubspecies = fleshSubspecies;
	}

}
