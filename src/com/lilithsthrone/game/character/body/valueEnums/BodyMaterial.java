package com.lilithsthrone.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermission;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.83
 * @version 0.2.4
 * @author Innoxia, tukaima
 */
public enum BodyMaterial {

	FLESH("flesh",
			"skin", "fleshy",
			"skin", "fleshy",
			"flesh", "fleshy",
			"flesh", "fleshy",
			"hair", "hairy",
			"hair", "hairy",
			"hair", "hairy",
			"fur", "furry",
			"fur", "furry",
			"feathers", "feathered",
			"feathers", "feathered",
			"scales", "scaled",
			"scales", "scaled",
			"shell", "shelled",
			"shell", "shelled",
			"nails", "hard",
			"keratin", "keratinous",
			PresetColour.BASE_PINK_LIGHT,
			DamageType.PHYSICAL,
			true,
			false,
			true) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			return null;
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			return null;
		}
	},
	
	SLIME("slime", PresetColour.RACE_SLIME, DamageType.PHYSICAL, false, true, false) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			return Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 100f));
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			return Util.newArrayListOfValues(
					"<b>[style.boldBad(-75%)] base [style.colourUnarmed(unarmed damage)]</b>",
					"<b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'>Can morph body at will</b>",
					"<b style='color: "+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>Impregnated through any orifice</b>");
		}
	},

	// Dolls:
	
	SILICONE("silicone", "smooth", PresetColour.BASE_BLACK, DamageType.PHYSICAL, true, false, true) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			return null;
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			return null;
		}
		@Override
		public List<String> getPartDescriptors() {
			return Util.newArrayListOfValues("silicone", "artificial", "doll");
		}
	},
	
	// Fire elementals:
	
	FIRE("fire", "burning", PresetColour.BASE_ORANGE, DamageType.FIRE, false, false, false) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			return Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_FIRE, 50f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 50f),
					new Value<>(Attribute.RESISTANCE_FIRE, 500f));
		}

		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			return null;
		}
	},

	// Water elementals:

	WATER("water", "cool", PresetColour.BASE_AQUA, DamageType.ICE, false, true, false) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			return Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 50f),
					new Value<>(Attribute.RESISTANCE_ICE, 50f));
		}

		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			return null;
		}
	},
	
	ICE("ice", "freezing", PresetColour.BASE_BLUE_LIGHT, DamageType.ICE, true, true, true) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			return Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_ICE, 50f),
					new Value<>(Attribute.RESISTANCE_ICE, 500f));
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			return null;
		}
	},

	// Air elementals:

	AIR("storm-clouds", "swirling", PresetColour.BASE_BLUE_STEEL, DamageType.POISON, false, false, false) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			return Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -100f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 100f));
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			return null;
		}
	},

	// Earth elementals:
	
	STONE("stone", "hard", PresetColour.BASE_GREY, DamageType.PHYSICAL, true, false, true) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			return Util.newHashMapOfValues(
						new Value<>(Attribute.DAMAGE_PHYSICAL, 25f),
						new Value<>(Attribute.RESISTANCE_PHYSICAL, 50f));
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			return null;
		}
	},
	
	RUBBER("rubber", PresetColour.BASE_BLACK, DamageType.PHYSICAL, true, false, true) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			return Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 50f));
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			return null;
		}
	},
	
	// Arcane elementals:
	
	ARCANE("energy", PresetColour.GENERIC_ARCANE, DamageType.PHYSICAL, false, false, false) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			return Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_SPELLS, 50f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 50f),
					new Value<>(Attribute.DAMAGE_LUST, 50f),
					new Value<>(Attribute.RESISTANCE_LUST, -50f));
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			return null;
		}
	},
	;

	private String name;
	private String skinNoun; private String skinAdj;
	private String skinAltNoun; private String skinAltAdj;
	private String orificeNoun; private String orificeAdj;
	private String orificeAltNoun; private String orificeAltAdj;
	private	String hairNoun; private String hairAdj;
	private	String hairBodyNoun; private String hairBodyAdj;
	private String hairAltNoun; private String hairAltAdj;
	private	String furNoun; private String furAdj;
	private String furAltNoun; private String furAltAdj;
	private	String featherNoun; private String featherAdj;
	private String featherAltNoun; private String featherAltAdj;
	private	String scaleNoun; private String scaleAdj;
	private String scaleAltNoun; private String scaleAltAdj;
	private	String shellNoun; private String shellAdj;
	private String shellAltNoun; private String shellAltAdj;
	private	String keratinNoun; private String keratinAdj;
	private String keratinAltNoun; private String keratinAltAdj;
	private List<String> partDescriptors;
	
	private Colour colour;
	private DamageType unarmedDamageType;
	
	private boolean requiresPiercing;
	private boolean orificesAlwaysMaximumWetness;
	private boolean ableToWearMakeup;

	private BodyMaterial(String name,
			String skinNoun, String skinAdj,
			String skinAltNoun, String skinAltAdj,
			String orificeNoun, String orificeAdj,
			String orificeAltNoun, String orificeAltAdj,
			String hairNoun, String hairAdj,
			String hairBodyNoun, String hairBodyAdj,
			String hairAltNoun, String hairAltAdj,
			String furNoun, String furAdj,
			String furAltNoun, String furAltAdj,
			String featherNoun, String featherAdj,
			String featherAltNoun, String featherAltAdj,
			String scaleNoun, String scaleAdj,
			String scaleAltNoun, String scaleAltAdj,
			String shellNoun, String shellAdj,
			String shellAltNoun, String shellAltAdj,
			String keratinNoun, String keratinAdj,
			String keratinAltNoun, String keratinAltAdj,
			Colour colour,
			DamageType unarmedDamageType,
			boolean requiresPiercing,
			boolean orificesAlwaysMaximumWetness,
			boolean ableToWearMakeup) {
		this.name = name;
		this.skinNoun = skinNoun;
		this.skinAdj = skinAdj;
		this.skinAltNoun = skinAltNoun;
		this.skinAltAdj = skinAltAdj;
		this.orificeNoun = orificeNoun;
		this.orificeAdj = orificeAdj;
		this.orificeAltNoun = orificeAltNoun;
		this.orificeAltAdj = orificeAltAdj;
		this.hairNoun = hairNoun;
		this.hairAdj = hairAdj;
		this.hairBodyNoun = hairBodyNoun;
		this.hairBodyAdj = hairBodyAdj;
		this.hairAltNoun = hairAltNoun;
		this.hairAltAdj = hairAltAdj;
		this.furNoun = furNoun;
		this.furAdj = furAdj;
		this.furAltNoun = furAltNoun;
		this.furAltAdj = furAltAdj;
		this.featherNoun = featherNoun;
		this.featherAdj = featherAdj;
		this.featherAltNoun = featherAltNoun;
		this.featherAltAdj = featherAltAdj;
		this.scaleNoun = scaleNoun;
		this.scaleAdj = scaleAdj;
		this.scaleAltNoun = scaleAltNoun;
		this.scaleAltAdj = scaleAltAdj;
		this.shellNoun = shellNoun;
		this.shellAdj = shellAdj;
		this.shellAltNoun = shellAltNoun;
		this.shellAltAdj = shellAltAdj;
		this.keratinNoun = keratinNoun;
		this.keratinAdj = keratinAdj;
		this.keratinAltNoun = keratinAltNoun;
		this.keratinAltAdj = keratinAltAdj;
		this.partDescriptors = new ArrayList<>();
		this.colour = colour;
		this.unarmedDamageType = unarmedDamageType;
		this.requiresPiercing = requiresPiercing;
		this.orificesAlwaysMaximumWetness = orificesAlwaysMaximumWetness;
		this.ableToWearMakeup = ableToWearMakeup;
	}

	private BodyMaterial(String noun, String adjective, Colour colour, DamageType unarmedDamageType, boolean requiresPiercing, boolean orificesAlwaysMaximumWetness, boolean ableToWearMakeup) {
		this.name = noun;
		this.skinNoun = noun;
		this.skinAdj = adjective;
		this.skinAltNoun = noun;
		this.skinAltAdj = adjective;
		this.orificeNoun = noun;
		this.orificeAdj = adjective;
		this.orificeAltNoun = noun;
		this.orificeAltAdj = adjective;
		this.hairNoun = noun;
		this.hairAdj = adjective;
		this.hairBodyNoun = noun;
		this.hairBodyAdj = adjective;
		this.hairAltNoun = noun;
		this.hairAltAdj = adjective;
		this.furNoun = noun;
		this.furAdj = adjective;
		this.furAltNoun = noun;
		this.furAltAdj = adjective;
		this.featherNoun = noun;
		this.featherAdj = adjective;
		this.featherAltNoun = noun;
		this.featherAltAdj = adjective;
		this.scaleNoun = noun;
		this.scaleAdj = adjective;
		this.scaleAltNoun = noun;
		this.scaleAltAdj = adjective;
		this.shellNoun = noun;
		this.shellAdj = adjective;
		this.shellAltNoun = noun;
		this.shellAltAdj = adjective;
		this.keratinNoun = noun;
		this.keratinAdj = adjective;
		this.keratinAltNoun = noun;
		this.keratinAltAdj = adjective;
		this.partDescriptors = new ArrayList<>();
		this.colour = colour;
		this.unarmedDamageType = unarmedDamageType;
		this.requiresPiercing = requiresPiercing;
		this.orificesAlwaysMaximumWetness = orificesAlwaysMaximumWetness;
		this.ableToWearMakeup = ableToWearMakeup;
	}

	private BodyMaterial(String noun, Colour colour, DamageType unarmedDamageType, boolean requiresPiercing, boolean orificesAlwaysMaximumWetness, boolean ableToWearMakeup) {
		this.name = noun;
		this.skinNoun = noun;
		this.skinAdj = noun;
		this.skinAltNoun = noun;
		this.skinAltAdj = noun;
		this.orificeNoun = noun;
		this.orificeAdj = noun;
		this.orificeAltNoun = noun;
		this.orificeAltAdj = noun;
		this.hairNoun = noun;
		this.hairAdj = noun;
		this.hairBodyNoun = noun;
		this.hairBodyAdj = noun;
		this.hairAltNoun = noun;
		this.hairAltAdj = noun;
		this.furNoun = noun;
		this.furAdj = noun;
		this.furAltNoun = noun;
		this.furAltAdj = noun;
		this.featherNoun = noun;
		this.featherAdj = noun;
		this.featherAltNoun = noun;
		this.featherAltAdj = noun;
		this.scaleNoun = noun;
		this.scaleAdj = noun;
		this.scaleAltNoun = noun;
		this.scaleAltAdj = noun;
		this.shellNoun = noun;
		this.shellAdj = noun;
		this.shellAltNoun = noun;
		this.shellAltAdj = noun;
		this.keratinNoun = noun;
		this.keratinAdj = noun;
		this.keratinAltNoun = noun;
		this.keratinAltAdj = noun;
		this.partDescriptors = new ArrayList<>();
		this.colour = colour;
		this.unarmedDamageType = unarmedDamageType;
		this.requiresPiercing = requiresPiercing;
		this.orificesAlwaysMaximumWetness = orificesAlwaysMaximumWetness;
		this.ableToWearMakeup = ableToWearMakeup;
	}
	

	public abstract Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target);
	
	public abstract List<String> getExtraEffects(GameCharacter target);
	
	public String getName() {
		return name;
	}

	public String getSkinNoun() {
		return skinNoun;
	}
	
	public String getSkinAdj() {
		return skinAdj;
	}
	
	public String getSkinAltNoun() {
		return skinAltNoun;
	}
	
	public String getSkinAltAdj() {
		return skinAltAdj;
	}
	
	public String getOrificeNoun() {
		return orificeNoun;
	}
	
	public String getOrificeAdj() {
		return orificeAdj;
	}
	
	public String getOrificeAltNoun() {
		return orificeAltNoun;
	}
	
	public String getOrificeAltAdj() {
		return orificeAltAdj;
	}

	public String getHairNoun() {
		return hairNoun;
	}
	
	public String getHairAdj() {
		return hairAdj;
	}
	
	public String getHairBodyNoun() {
		return hairBodyNoun;
	}
	
	public String getHairBodyAdj() {
		return hairBodyAdj;
	}
	
	public String getHairAltNoun() {
		return hairAltNoun;
	}
	
	public String getHairAltAdj() {
		return hairAltAdj;
	}
	
	public String getFurNoun() {
		return furNoun;
	}
	
	public String getFurAdj() {
		return furAdj;
	}
	
	public String getFurAltNoun() {
		return furAltNoun;
	}
	
	public String getFurAltAdj() {
		return furAltAdj;
	}
	
	public String getFeatherNoun() {
		return featherNoun;
	}
	
	public String getFeatherAdj() {
		return featherAdj;
	}
	
	public String getFeatherAltNoun() {
		return featherAltNoun;
	}
	
	public String getFeatherAltAdj() {
		return featherAltAdj;
	}
	
	public String getScaleNoun() {
		return scaleNoun;
	}
	
	public String getScaleAdj() {
		return scaleAdj;
	}
	
	public String getScaleAltNoun() {
		return scaleAltNoun;
	}
	
	public String getScaleAltAdj() {
		return scaleAltAdj;
	}
	
	public String getShellNoun() {
		return shellNoun;
	}
	
	public String getShellAdj() {
		return shellAdj;
	}
	
	public String getShellAltNoun() {
		return shellAltNoun;
	}
	
	public String getShellAltAdj() {
		return shellAltAdj;
	}
	
	public String getKeratinNoun() {
		return keratinNoun;
	}
	
	public String getKeratinAdj() {
		return keratinAdj;
	}
	
	public String getKeratinAltNoun() {
		return keratinAltNoun;
	}
	
	public String getKeratinAltAdj() {
		return keratinAltAdj;
	}

	public List<String> getPartDescriptors() {
		return partDescriptors;
	}

	public Colour getColour() {
		return colour;
	}

	public DamageType getUnarmedDamageType() {
		return unarmedDamageType;
	}

	public boolean isRequiresPiercing() {
		return requiresPiercing;
	}

	public boolean isOrificesAlwaysMaximumWetness() {
		return orificesAlwaysMaximumWetness;
	}

	public boolean isAbleToWearMakeup() {
		return ableToWearMakeup;
	}

	/**
	 * @return true if this body material is subject to limited depth of orifices.
	 */
	public boolean isOrificesLimitedDepth() {
		return this==FLESH;
	}
	
	/**
	 * Turns character into a doll.
	 */
	public static void applyGenericSiliconeBodyChange(GameCharacter doll) {
		doll.removeStatusEffect(StatusEffect.PREGNANT_0);
		
		// Standard attributes
		doll.setHeight(160);
		doll.setMuscle(Muscle.TWO_TONED.getMedianValue());
		doll.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		if(doll.isFeminine()) {
			doll.setFemininity(100);
		}
		doll.setArmRows(1);
		if(doll.getLegConfiguration()!=LegConfiguration.BIPEDAL) {
			doll.setLegConfiguration(doll.getLegType().getRace().getRacialBody().getLegType(), LegConfiguration.BIPEDAL, false);
		}
//		doll.setLegConfiguration(doll.getTrueRace().getRacialBody().getLegType(), LegConfiguration.BIPEDAL, false);
		// Why was this added? Commented out in 0.4.10.4
//		doll.setTailType(TailType.NONE);
//		doll.setHornType(HornType.NONE);
//		doll.setAntennaType(AntennaType.NONE);
//		doll.setWingType(WingType.NONE);
		
		// Sexual orifices:
		doll.completeVirginityReset();
		
		// Breasts:
		doll.setBreastSize(CupSize.DD);
		doll.setBreastShape(BreastShape.ROUND);
		doll.setBreastRows(1);
		doll.setBreastMilkStorage(0);
		doll.setBreastCrotchMilkStorage(0);
		
		// Nipples:
		doll.setNippleSize(NippleSize.TWO_BIG);
		doll.setNippleShape(NippleShape.NORMAL);
		doll.setAreolaeSize(AreolaeSize.TWO_BIG);
		doll.setAreolaeShape(AreolaeShape.NORMAL);
		doll.setNippleCountPerBreast(1);
		doll.setNippleCapacity(Capacity.ONE_EXTREMELY_TIGHT.getMedianValue(), true);
		doll.setNippleElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		doll.setNipplePlasticity(OrificePlasticity.ZERO_RUBBERY.getValue());
		doll.clearNippleOrificeModifiers();
		doll.addNippleOrificeModifier(OrificeModifier.RIBBED);
		doll.clearMilkModifiers();
		doll.addMilkModifier(FluidModifier.MINERAL_OIL);
		doll.addMilkModifier(FluidModifier.SLIMY);
		doll.setMilkFlavour(FluidFlavour.FLAVOURLESS);
		doll.getCovering(BodyCoveringType.MILK).setPrimaryColour(PresetColour.COVERING_CLEAR);
		boolean hadCrotchBoobs = doll.hasBreastsCrotch();
		if(!hadCrotchBoobs) {
			doll.setBreastCrotchType(BreastType.HORSE_MORPH);
		}
		doll.setNippleCrotchElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		doll.setNippleCrotchPlasticity(OrificePlasticity.ZERO_RUBBERY.getValue());
		doll.clearNippleCrotchOrificeModifiers();
		doll.addNippleCrotchOrificeModifier(OrificeModifier.RIBBED);
		doll.clearMilkCrotchModifiers();
		doll.addMilkCrotchModifier(FluidModifier.MINERAL_OIL);
		doll.addMilkCrotchModifier(FluidModifier.SLIMY);
		doll.setMilkCrotchFlavour(FluidFlavour.FLAVOURLESS);
		if(!hadCrotchBoobs) {
			doll.setBreastCrotchType(BreastType.NONE);
		}
		
		// Ass:
		doll.setAssCapacity(Capacity.TWO_TIGHT, true);
		doll.setAssElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		doll.setAssPlasticity(OrificePlasticity.ZERO_RUBBERY.getValue());
		doll.setAssWetness(Wetness.THREE_WET);
		doll.clearAssOrificeModifiers();
		doll.addAssOrificeModifier(OrificeModifier.RIBBED);
		
		// Tongue:
		doll.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		doll.resetTongueModifiers();
		// Face:
		doll.setFaceCapacity(Capacity.TWO_TIGHT, true);
		doll.setFaceElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		doll.setFacePlasticity(OrificePlasticity.ZERO_RUBBERY.getValue());
		doll.setFaceWetness(Wetness.THREE_WET.getValue());
		doll.clearFaceOrificeModifiers();
		doll.addFaceOrificeModifier(OrificeModifier.RIBBED);
		// Eyes:
		doll.setIrisShape(EyeShape.ROUND);
		
		// Vagina:
		boolean hadVagina = doll.hasVagina();
		if(!hadVagina) {
			doll.setVaginaType(VaginaType.HUMAN);
		}
		doll.setClitorisGirth(PenetrationGirth.ZERO_THIN.getValue());
		doll.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE.getMedianValue());
		doll.resetClitorisModifiers();
		doll.setVaginaLabiaSize(LabiaSize.ZERO_TINY);
		doll.setVaginaCapacity(Capacity.TWO_TIGHT, true);
		doll.setVaginaElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		doll.setVaginaPlasticity(OrificePlasticity.ZERO_RUBBERY.getValue());
		doll.setVaginaUrethraCapacity(Capacity.ONE_EXTREMELY_TIGHT.getMedianValue(), true);
		doll.setVaginaUrethraElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		doll.setVaginaUrethraPlasticity(OrificePlasticity.ZERO_RUBBERY.getValue());
		doll.clearVaginaUrethraOrificeModifiers();
		doll.clearGirlcumModifiers();
		doll.addGirlcumModifier(FluidModifier.MINERAL_OIL);
		doll.addGirlcumModifier(FluidModifier.SLIMY);
		doll.setGirlcumFlavour(FluidFlavour.FLAVOURLESS);
		doll.getCovering(BodyCoveringType.GIRL_CUM).setPrimaryColour(PresetColour.COVERING_CLEAR);
		doll.setHymen(false);
		doll.setVaginaWetness(Wetness.THREE_WET);
		doll.setVaginaSquirter(true);
		doll.clearVaginaOrificeModifiers();
		doll.addVaginaOrificeModifier(OrificeModifier.RIBBED);
		if(!hadVagina) {
			doll.setVaginaType(VaginaType.NONE);
		}
		
		// Penis:
		boolean hadPenis = doll.hasPenis();
		if(!hadPenis) {
			doll.setPenisType(PenisType.HUMAN);
		}
		doll.setPenisSize(PenisLength.THREE_LARGE.getMedianValue());
		doll.setTesticleSize(TesticleSize.THREE_LARGE);
		if(doll.hasVagina()) {
			doll.setInternalTesticles(true);
		} else {
			doll.setInternalTesticles(false);
		}
		doll.setPenisCapacity(Capacity.ONE_EXTREMELY_TIGHT.getMedianValue(), true);
		doll.setUrethraElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		doll.setUrethraPlasticity(OrificePlasticity.ZERO_RUBBERY.getValue());
		doll.clearUrethraOrificeModifiers();
		doll.clearCumModifiers();
		doll.addCumModifier(FluidModifier.MINERAL_OIL);
		doll.addCumModifier(FluidModifier.SLIMY);
		doll.setCumFlavour(FluidFlavour.FLAVOURLESS);
		doll.getCovering(BodyCoveringType.CUM).setPrimaryColour(PresetColour.COVERING_CLEAR);
		doll.setPenisCumStorage(250);
		if(!hadPenis) {
			doll.setPenisType(PenisType.NONE);
		}
		
		// Spinneret:
		boolean hadSpinneret = doll.hasSpinneret();
		AbstractTailType originalTail = doll.getTailType();
		if(!hadSpinneret) {
			doll.setTailType(TailType.getTailTypeFromId("charisma_spider_tail"));
		}
		doll.setSpinneretCapacity(Capacity.ONE_EXTREMELY_TIGHT.getMedianValue(), true);
		doll.setSpinneretElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		doll.setSpinneretPlasticity(OrificePlasticity.ZERO_RUBBERY.getValue());
		doll.setSpinneretWetness(Wetness.THREE_WET.getValue());
		if(!hadSpinneret) {
			doll.setTailType(originalTail);
		}
		
		// Remove all hair:
		doll.setHairLength(0);
		doll.setFacialHair(0);
		doll.setUnderarmHair(0);
		doll.setPubicHair(0);
		doll.setAssHair(0);
	}
	
	public static void applyGenericSiliconeMentalChange(GameCharacter doll) {
		// Birthday of a doll is when they're created
		doll.setBirthday(Main.game.getDateNow());
		// Personality:
		doll.clearPersonalityTraits();
		// Banish elemental:
		doll.setElementalSummoned(false);
		// Level 1, no experience, no essences:
		doll.setLevel(1);
		doll.setExperience(0);
		doll.setEssenceCount(0);
		// Occupation & perks:
		doll.setOccupation(Occupation.NPC_SEX_DOLL);
		doll.resetPerksMap(false, false);
		doll.resetSpecialPerksMap();
		doll.setupPerks(true);
		// Status effects:
		doll.removeStatusEffect(StatusEffect.RECOVERING_AURA);
		// Fetish removals:
		doll.clearFetishes();
		doll.clearFetishDesires();
		doll.applyFetishLossEffectsForAllClothingAndTattoos(); // Apply fetish loss effects from tattoos and clothing (dolls are not affected by them)
		for(AbstractFetish f : Fetish.allFetishes) {
			doll.setFetishExperience(f, 0);
		}
		// Fluid effects:
		doll.setAlcoholLevel(0);
		doll.removeStatusEffect(StatusEffect.PSYCHOACTIVE);
		doll.clearAddictions();
		// Slavery:
		doll.setObedience(100);
		doll.addSlavePermissionSetting(SlavePermission.PILLS, SlavePermissionSetting.PILLS_NO_PILLS);
		doll.addSlavePermissionSetting(SlavePermission.SLEEPING, SlavePermissionSetting.SLEEPING_DEFAULT);
		doll.addSlavePermissionSetting(SlavePermission.DIET, SlavePermissionSetting.FOOD_NORMAL);
		doll.addSlavePermissionSetting(SlavePermission.EXERCISE, SlavePermissionSetting.EXERCISE_NORMAL);
	}
}
