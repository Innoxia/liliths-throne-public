package com.lilithsthrone.game.character.race;

import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.2.8
 * @author Innoxia
 */
public enum RaceStage {
	/**No animal-morph parts whatsoever.<br/>
	 * <i>"Not furry"</i> by any standard.*/
	HUMAN("", PresetColour.TRANSFORMATION_HUMAN) {
		@Override
		public boolean isAntennaFurry() {
			return false;
		}
		@Override
		public boolean isArmFurry() {
			return false;
		}
		@Override
		public boolean isAssFurry() {
			return false;
		}
		@Override
		public boolean isBreastFurry() {
			return false;
		}
		@Override
		public boolean isEarFurry() {
			return false;
		}
		@Override
		public boolean isEyeFurry() {
			return false;
		}
		@Override
		public boolean isFaceFurry() {
			return false;
		}
		@Override
		public boolean isHairFurry() {
			return false;
		}
		@Override
		public boolean isHornFurry() {
			return false;
		}
		@Override
		public boolean isLegFurry() {
			return false;
		}
		@Override
		public boolean isPenisFurry() {
			return false;
		}
		@Override
		public boolean isSkinFurry() {
			return false;
		}
		@Override
		public boolean isTailFurry() {
			return false;
		}
		@Override
		public boolean isVaginaFurry() {
			return false;
		}
		@Override
		public boolean isWingFurry() {
			return false;
		}
		@Override
		public boolean isTentacleFurry() {
			return false;
		}
	},
	
	/**Some minor animal-morph parts.<br/>
	 * When used in GameCharacter's setBody() method, will grant <b>only</b> hair, ears, eyes, tail, horns, antenna, and wings (no genitalia).<br/>
	 * <i>"Not furry"</i> by most standards.*/
	PARTIAL("partial", PresetColour.TRANSFORMATION_PARTIAL) {
		@Override
		public boolean isAntennaFurry() {
			return true;
		}
		@Override
		public boolean isArmFurry() {
			return false;
		}
		@Override
		public boolean isAssFurry() {
			return false;
		}
		@Override
		public boolean isBreastFurry() {
			return false;
		}
		@Override
		public boolean isEarFurry() {
			return true;
		}
		@Override
		public boolean isEyeFurry() {
			return true;
		}
		@Override
		public boolean isFaceFurry() {
			return false;
		}
		@Override
		public boolean isHairFurry() {
			return true;
		}
		@Override
		public boolean isHornFurry() {
			return true;
		}
		@Override
		public boolean isLegFurry() {
			return false;
		}
		@Override
		public boolean isPenisFurry() {
			return false;
		}
		@Override
		public boolean isSkinFurry() {
			return false;
		}
		@Override
		public boolean isTailFurry() {
			return true;
		}
		@Override
		public boolean isVaginaFurry() {
			return false;
		}
		@Override
		public boolean isWingFurry() {
			return true;
		}
		@Override
		public boolean isTentacleFurry() {
			return false;
		}
	},

	/**All minor animal-morph parts (including genitalia).<br/>
	 * <i>"Borderline furry"</i> by most standards.*/
	PARTIAL_FULL("minor", PresetColour.TRANSFORMATION_PARTIAL_FULL) {
		@Override
		public boolean isAntennaFurry() {
			return true;
		}
		@Override
		public boolean isArmFurry() {
			return false;
		}
		@Override
		public boolean isAssFurry() {
			return true;
		}
		@Override
		public boolean isBreastFurry() {
			return true;
		}
		@Override
		public boolean isEarFurry() {
			return true;
		}
		@Override
		public boolean isEyeFurry() {
			return true;
		}
		@Override
		public boolean isFaceFurry() {
			return false;
		}
		@Override
		public boolean isHairFurry() {
			return true;
		}
		@Override
		public boolean isHornFurry() {
			return true;
		}
		@Override
		public boolean isLegFurry() {
			return false;
		}
		@Override
		public boolean isPenisFurry() {
			return true;
		}
		@Override
		public boolean isSkinFurry() {
			return false;
		}
		@Override
		public boolean isTailFurry() {
			return true;
		}
		@Override
		public boolean isVaginaFurry() {
			return true;
		}
		@Override
		public boolean isWingFurry() {
			return true;
		}
		@Override
		public boolean isTentacleFurry() {
			return false;
		}
	},

	/**All minor animal-morph parts (including genitalia), plus animal-morph arms and legs.<br/>
	 * <i>"Low-level furry"</i> by most standards.*/
	LESSER("lesser", PresetColour.TRANSFORMATION_LESSER) {
		@Override
		public boolean isAntennaFurry() {
			return true;
		}
		@Override
		public boolean isArmFurry() {
			return true;
		}
		@Override
		public boolean isAssFurry() {
			return true;
		}
		@Override
		public boolean isBreastFurry() {
			return true;
		}
		@Override
		public boolean isEarFurry() {
			return true;
		}
		@Override
		public boolean isEyeFurry() {
			return true;
		}
		@Override
		public boolean isFaceFurry() {
			return false;
		}
		@Override
		public boolean isHairFurry() {
			return true;
		}
		@Override
		public boolean isHornFurry() {
			return true;
		}
		@Override
		public boolean isLegFurry() {
			return true;
		}
		@Override
		public boolean isPenisFurry() {
			return true;
		}
		@Override
		public boolean isSkinFurry() {
			return false;
		}
		@Override
		public boolean isTailFurry() {
			return true;
		}
		@Override
		public boolean isVaginaFurry() {
			return true;
		}
		@Override
		public boolean isWingFurry() {
			return true;
		}
		@Override
		public boolean isTentacleFurry() {
			return false;
		}
	},

	/**All minor animal-morph parts, animal-morph arms and legs, and animal-morph skin and face.<br/>
	 * <i>"Furry"</i> by all standards.*/
	GREATER("greater", PresetColour.TRANSFORMATION_GREATER) {
		@Override
		public boolean isAntennaFurry() {
			return true;
		}
		@Override
		public boolean isArmFurry() {
			return true;
		}
		@Override
		public boolean isAssFurry() {
			return true;
		}
		@Override
		public boolean isBreastFurry() {
			return true;
		}
		@Override
		public boolean isEarFurry() {
			return true;
		}
		@Override
		public boolean isEyeFurry() {
			return true;
		}
		@Override
		public boolean isFaceFurry() {
			return true;
		}
		@Override
		public boolean isHairFurry() {
			return true;
		}
		@Override
		public boolean isHornFurry() {
			return true;
		}
		@Override
		public boolean isLegFurry() {
			return true;
		}
		@Override
		public boolean isPenisFurry() {
			return true;
		}
		@Override
		public boolean isSkinFurry() {
			return true;
		}
		@Override
		public boolean isTailFurry() {
			return true;
		}
		@Override
		public boolean isVaginaFurry() {
			return true;
		}
		@Override
		public boolean isWingFurry() {
			return true;
		}
		@Override
		public boolean isTentacleFurry() {
			return true;
		}
	},
	
	FERAL("feral", PresetColour.RACE_BESTIAL) {
		@Override
		public boolean isAntennaFurry() {
			return true;
		}
		@Override
		public boolean isArmFurry() {
			return true;
		}
		@Override
		public boolean isAssFurry() {
			return true;
		}
		@Override
		public boolean isBreastFurry() {
			return true;
		}
		@Override
		public boolean isEarFurry() {
			return true;
		}
		@Override
		public boolean isEyeFurry() {
			return true;
		}
		@Override
		public boolean isFaceFurry() {
			return true;
		}
		@Override
		public boolean isHairFurry() {
			return true;
		}
		@Override
		public boolean isHornFurry() {
			return true;
		}
		@Override
		public boolean isLegFurry() {
			return true;
		}
		@Override
		public boolean isPenisFurry() {
			return true;
		}
		@Override
		public boolean isSkinFurry() {
			return true;
		}
		@Override
		public boolean isTailFurry() {
			return true;
		}
		@Override
		public boolean isVaginaFurry() {
			return true;
		}
		@Override
		public boolean isWingFurry() {
			return true;
		}
		@Override
		public boolean isTentacleFurry() {
			return true;
		}
	};

	private String name;
	private Colour colour;

	private RaceStage(String name,Colour colour) {
		this.name = name;
		this.colour = colour;
	}

	public String getName() {
		return name;
	}
	
	public Colour getColour() {
		return colour;
	}
	
	public static RaceStage getRaceStageFromUserPreferences(Gender gender, AbstractSubspecies subspecies) {
		FurryPreference preference;
		if(gender.isFeminine()) {
			preference = Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(subspecies);
		} else {
			preference = Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(subspecies);
		}
		if(preference==null) {
			preference = FurryPreference.HUMAN;
		}
		
		RaceStage raceStage = RaceStage.PARTIAL;
		
		switch(preference) {
			case HUMAN:
				return RaceStage.HUMAN;
			case MINIMUM:
				return RaceStage.PARTIAL;
			case REDUCED:
				return Util.randomItemFrom(Util.newArrayListOfValues(RaceStage.PARTIAL, RaceStage.LESSER));
			case NORMAL:
				return Util.randomItemFrom(Util.newArrayListOfValues(RaceStage.PARTIAL, RaceStage.LESSER, RaceStage.GREATER));
			case MAXIMUM:
				return RaceStage.GREATER;
		}
		
		return raceStage;
	}

	public abstract boolean isAntennaFurry();
	public abstract boolean isArmFurry();
	public abstract boolean isAssFurry();
	public abstract boolean isBreastFurry();
	public abstract boolean isEarFurry();
	public abstract boolean isEyeFurry();
	public abstract boolean isFaceFurry();
	public abstract boolean isHairFurry();
	public abstract boolean isHornFurry();
	public abstract boolean isLegFurry();
	public abstract boolean isPenisFurry();
	public abstract boolean isSkinFurry();
	public abstract boolean isTailFurry();
	public abstract boolean isTentacleFurry();
	public abstract boolean isVaginaFurry();
	public abstract boolean isWingFurry();
}
