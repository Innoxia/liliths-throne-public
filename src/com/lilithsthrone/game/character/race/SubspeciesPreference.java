package com.lilithsthrone.game.character.race;
import com.lilithsthrone.rendering.SVGImages;

/**
 * @since 0.1.99
 * @version 0.1.99
 * @author tukaima
 */
public enum SubspeciesPreference {

	ZERO_NONE("off", 0) {
		@Override
		public String getSVGImage(boolean disabled) {
			return disabled?SVGImages.SVG_IMAGE_PROVIDER.getScaleZeroDisabled():SVGImages.SVG_IMAGE_PROVIDER.getScaleZero();
		}
	},
	ONE_LOW("low", 25) {
		@Override
		public String getSVGImage(boolean disabled) {
			return disabled?SVGImages.SVG_IMAGE_PROVIDER.getScaleOneDisabled():SVGImages.SVG_IMAGE_PROVIDER.getScaleOne();
		}
	},
	TWO_AVERAGE("average", 50) {
		@Override
		public String getSVGImage(boolean disabled) {
			return disabled?SVGImages.SVG_IMAGE_PROVIDER.getScaleTwoDisabled():SVGImages.SVG_IMAGE_PROVIDER.getScaleTwo();
		}
	},
	THREE_HIGH("high", 75) {
		@Override
		public String getSVGImage(boolean disabled) {
			return disabled?SVGImages.SVG_IMAGE_PROVIDER.getScaleThreeDisabled():SVGImages.SVG_IMAGE_PROVIDER.getScaleThree();
		}
	},
	FOUR_ABUNDANT("abundant", 100) {
		@Override
		public String getSVGImage(boolean disabled) {
			return disabled?SVGImages.SVG_IMAGE_PROVIDER.getScaleFourDisabled():SVGImages.SVG_IMAGE_PROVIDER.getScaleFour();
		}
	};

	private String name;
	private int value;
	
	private SubspeciesPreference(String name, int value) {
		this.name=name;
		this.value=value;
	}
	
	public abstract String getSVGImage(boolean disabled);
	
	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
}