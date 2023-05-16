package com.lilithsthrone.game.character.body.valueEnums;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.places.Darkness;

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
	
	SLIME("slime", "slimy", PresetColour.RACE_SLIME, DamageType.PHYSICAL, false, true, false) {
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
	
	RUBBER("rubber", PresetColour.BASE_BLACK, DamageType.PHYSICAL, true, false, true, true) {
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
	
	ARCANE("energy", PresetColour.GENERIC_ARCANE, DamageType.PHYSICAL, false, false, false, false) {
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
        
        PLANT("foliage", "leafy", PresetColour.BASE_GREEN, DamageType.PHYSICAL, false, false, false) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
                        if((Main.game.isDayTime() && target.getLocationPlaceType().getDarkness() == Darkness.DAYLIGHT)
                                || target.getLocationPlaceType().getDarkness() == Darkness.ALWAYS_LIGHT){
                            return Util.newHashMapOfValues(
                                            new Value<>(Attribute.RESISTANCE_FIRE, -5f),
                                            new Value<>(Attribute.RESISTANCE_ICE, -5f),
                                            new Value<>(Attribute.SPELL_COST_MODIFIER, 25f),
                                            new Value<>(Attribute.DAMAGE_SPELLS, 10f));
                        } else {
                            return Util.newHashMapOfValues(
                                            new Value<>(Attribute.MAJOR_PHYSIQUE, -5f),
                                            new Value<>(Attribute.MAJOR_ARCANE, -5f),
                                            new Value<>(Attribute.RESISTANCE_FIRE, -10f),
                                            new Value<>(Attribute.RESISTANCE_ICE, -10f));
                        }
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			return Util.newArrayListOfValues(
                                        "<b>[style.boldGood(Spells enhanced in well-lit environments!)]</b>",
					"<b>[style.boldBad(Lowered stats in darkness!)]</b>");
		}
	},
        
        FUNGUS("fungus", "spongy", PresetColour.BASE_PINK_SALMON, DamageType.PHYSICAL, false, false, false) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
                        return Util.newHashMapOfValues(
                                            new Value<>(Attribute.RESISTANCE_FIRE, -10f),
                                            new Value<>(Attribute.RESISTANCE_PHYSICAL, 25f),
                                            new Value<>(Attribute.RESISTANCE_POISON, 25f));
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
	private Colour colour;
	private DamageType unarmedDamageType;
	private boolean requiresPiercing;
	private boolean orificesAlwaysMaximumWetness;
        private boolean orificesAlwaysMaximumElasticity;
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
		this.colour = colour;
		this.unarmedDamageType = unarmedDamageType;
		this.requiresPiercing = requiresPiercing;
		this.orificesAlwaysMaximumWetness = orificesAlwaysMaximumWetness;
                this.orificesAlwaysMaximumElasticity = false;
		this.ableToWearMakeup = ableToWearMakeup;
	}

	private BodyMaterial(String noun, String adjective, Colour colour, DamageType unarmedDamageType, boolean requiresPiercing, boolean orificesAlwaysMaximumWetnessm, boolean ableToWearMakeup) {
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
		this.colour = colour;
		this.unarmedDamageType = unarmedDamageType;
		this.requiresPiercing = requiresPiercing;
		this.orificesAlwaysMaximumWetness = orificesAlwaysMaximumWetness;
                this.orificesAlwaysMaximumElasticity = false;
		this.ableToWearMakeup = ableToWearMakeup;
	}

	private BodyMaterial(String noun, Colour colour, DamageType unarmedDamageType, boolean requiresPiercing, boolean orificesAlwaysMaximumWetness, boolean orificesAlwaysMaximumElasticity, boolean ableToWearMakeup) {
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
		this.colour = colour;
		this.unarmedDamageType = unarmedDamageType;
		this.requiresPiercing = requiresPiercing;
		this.orificesAlwaysMaximumWetness = orificesAlwaysMaximumWetness;
                this.orificesAlwaysMaximumElasticity = orificesAlwaysMaximumElasticity;
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
        
        public boolean isOrificesAlwaysMaximumElasticity() {
                return orificesAlwaysMaximumElasticity;
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
}
