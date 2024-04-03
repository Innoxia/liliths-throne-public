package com.lilithsthrone.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.4
 * @author Innoxia
 */
public enum PenetrationModifier {

	SHEATHED("sheathed", "Helps to hide bulge in clothing when not erect."),
	
	RIBBED("ribbed", ""),
	
	TENTACLED("tentacled", ""),
	
	KNOTTED("knotted", "Grants ability to be pushed inside an orifice at the moment of orgasm, doubling effective diameter and locking partners together. (Requires orifice to be deep enough for knotted base to be inserted.)"),
	
	BLUNT("blunt", ""),

	TAPERED("tapered", "Reduces effective diameter by 5%. (Mutually exclusive with 'flared'.)") {
		@Override
		public List<PenetrationModifier> getMutuallyExclusivePenetrationModifiers() {
			return Util.newArrayListOfValues(FLARED);
		}
	},
	
	FLARED("flared", "Increases effective diameter by 5%. (Mutually exclusive with 'tapered'.)") {
		@Override
		public List<PenetrationModifier> getMutuallyExclusivePenetrationModifiers() {
			return Util.newArrayListOfValues(TAPERED);
		}
	},
	
	BARBED("barbed", ""),
	
	VEINY("veiny", ""),
	
	PREHENSILE("prehensile", ""),
	
	OVIPOSITOR("ovipositor",
			"Grants ability to lay eggs in the currently-penetrated orifice. (Requires the eggs to already be fertilised. Eggs cannot be laid in an already-pregnant target's vagina.)",
			Util.newArrayListOfValues(
					SexAreaPenetration.PENIS,
					SexAreaPenetration.CLIT));
	
	private String name;
	private String description;
	private List<SexAreaPenetration> restrictedPenetrationTypes;

	private PenetrationModifier(String name, String description) {
		this(name, description, null);
	}
	
	private PenetrationModifier(String name, String description, List<SexAreaPenetration> restrictedPenetrationTypes) {
		this.name = name;
		this.description = description;
		this.restrictedPenetrationTypes = restrictedPenetrationTypes;
	}
	
	public static List<PenetrationModifier> getPenetrationModifiers() {
		return getPenetrationModifiers(null);
	}
	
	public static List<PenetrationModifier> getPenetrationModifiers(SexAreaPenetration penetrationType) {
		List<PenetrationModifier> penetrationModifiers = new ArrayList<>(Arrays.asList(PenetrationModifier.values()));
		penetrationModifiers.removeIf(pm->pm.getRestrictedPenetrationTypes()!=null && penetrationType!=null && !pm.getRestrictedPenetrationTypes().contains(penetrationType));
		return penetrationModifiers;
	}

	public List<PenetrationModifier> getMutuallyExclusivePenetrationModifiers() {
		return new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isSpecialEffects() {
		return !description.isEmpty();
	}
	
	public String getDescription() {
		if(description.isEmpty()) {
			return "No gameplay effect.";
		}
		return description;
	}

	public List<SexAreaPenetration> getRestrictedPenetrationTypes() {
		return restrictedPenetrationTypes;
	}
}
