package com.lilithsthrone.game.character.race;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.utils.colours.Colour;

/**
 * @since 0.3.9.1
 * @version 0.3.9.1
 * @author Innoxia
 */
public abstract class AbstractRace {

	private String name;
	private String namePlural;
	private String nameBestial;
	private String nameBestialPlural;
	private String defaultTransformName;
	private Colour colour;
	private Disposition disposition;
	private List<Attack> preferredAttacks;
	private int numberOfOffspringLow;
	private int numberOfOffspringHigh;
	private float chanceForMaleOffspring;
	private Attribute damageMultiplier;
	private FurryPreference defaultFemininePreference;
	private FurryPreference defaultMasculinePreference;
	private boolean affectedByFurryPreference;
	
	public AbstractRace(String name,
			String namePlural,
			String nameBestial,
			String nameBestialPlural,
			String defaultTransformName,
			Colour colour,
			Disposition disposition,
			List<Attack> preferredAttacks,
			float chanceForMaleOffspring,
			int numberOfOffspringLow,
			int numberOfOffspringHigh,
			Attribute damageMultiplier,
			FurryPreference defaultFemininePreference,
			FurryPreference defaultMasculinePreference,
			boolean affectedByFurryPreference) {
		
		this.name = name;
		this.namePlural = namePlural;
		this.nameBestial = nameBestial;
		this.nameBestialPlural = nameBestialPlural;
		this.defaultTransformName = defaultTransformName;
		
		this.colour = colour;
		this.disposition = disposition;

		this.preferredAttacks = preferredAttacks;

		this.chanceForMaleOffspring=chanceForMaleOffspring;
		
		this.numberOfOffspringLow = numberOfOffspringLow;
		this.numberOfOffspringHigh = numberOfOffspringHigh;
		
		this.damageMultiplier = damageMultiplier;
		
		this.defaultFemininePreference = defaultFemininePreference;
		this.defaultMasculinePreference = defaultMasculinePreference;
		
		this.affectedByFurryPreference = affectedByFurryPreference;
	}
	
	public abstract AbstractRacialBody getRacialBody();
	
	public abstract BodyCoveringType getBodyHairType();

	public abstract AbstractItemType getConsumableAttributeItem();

	public abstract AbstractItemType getConsumableTransformativeItem();
	
	public boolean isBestialPartsAvailable() {
		return true;
	}
	
	public boolean isFlyingRace() {
		return false;
	}
	
	/**
	 * Applies any special racial changes to the body which is passed in. This is called <b>before</b> Subspecies.applySpeciesChanges()
	 */
	public void applyRaceChanges(Body body) {
	}
	
	public String getName(GameCharacter character, boolean bestial) {
		if(bestial) {
			return nameBestial;
		}
		return name;
	}
	
	public String getName(boolean bestial) {
		return getName(null, bestial);
	}
	
	public String getNamePlural(GameCharacter character, boolean bestial) {
		if(bestial) {
			return nameBestialPlural;
		}
		return namePlural;
	}
	
	public String getNamePlural(boolean bestial) {
		return getNamePlural(null, bestial);
	}

	public String getDefaultTransformName() {
		return defaultTransformName;
	}

	public Disposition getDisposition() {
		return disposition;
	}

	public List<Attack> getPreferredAttacks() {
		return preferredAttacks;
	}

	public int getNumberOfOffspringLow() {
		return numberOfOffspringLow;
	}

	public int getNumberOfOffspringHigh() {
		return numberOfOffspringHigh;
	}
	
	public Colour getColour() {
		return colour;
	}
	
	public boolean isAffectedByFurryPreference() {
		return affectedByFurryPreference;
	}
	
	public float getChanceForMaleOffspring() {
		return chanceForMaleOffspring;
	}

	/**
	 * <b>Should only be used in Subspecies' getDamageMultiplier() method!</b>
	 */
	public Attribute getDefaultDamageMultiplier() {
		return damageMultiplier;
	}

	public FurryPreference getDefaultFemininePreference() {
		return defaultFemininePreference;
	}

	public FurryPreference getDefaultMasculinePreference() {
		return defaultMasculinePreference;
	}

}
