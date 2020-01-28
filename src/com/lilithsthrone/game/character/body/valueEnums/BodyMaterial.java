package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.utils.Colour;

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
			Colour.BASE_PINK_LIGHT,
			DamageType.PHYSICAL,
			true,
			false,
			true),
	
	SLIME("slime", Colour.RACE_SLIME, DamageType.PHYSICAL, false, true, false),

	// Fire elementals:
	
	FIRE("fire", "burning", Colour.BASE_ORANGE, DamageType.FIRE, false, false, false),

	// Water elementals:

	WATER("water", "cool", Colour.BASE_AQUA, DamageType.ICE, false, true, false),
	
	ICE("ice", "freezing", Colour.BASE_BLUE_LIGHT, DamageType.ICE, true, true, true),

	// Air elementals:

	AIR("storm-clouds", "swirling", Colour.BASE_BLUE_STEEL, DamageType.POISON, false, false, false),

	// Earth elementals:
	
	STONE("stone", "hard", Colour.BASE_GREY, DamageType.PHYSICAL, true, false, true),
	
	RUBBER("rubber", Colour.BASE_BLACK, DamageType.PHYSICAL, true, false, true),
	
	// Arcane elementals:
	
	ARCANE("energy", Colour.GENERIC_ARCANE, DamageType.PHYSICAL, false, false, false),
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
		this.colour = colour;
		this.unarmedDamageType = unarmedDamageType;
		this.requiresPiercing = requiresPiercing;
		this.orificesAlwaysMaximumWetness = orificesAlwaysMaximumWetness;
		this.ableToWearMakeup = ableToWearMakeup;
	}

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

	public boolean isAbleToWearMakeup() {
		return ableToWearMakeup;
	}
}
