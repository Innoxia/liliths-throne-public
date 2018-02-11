package com.lilithsthrone.game.character.attributes;

/**
 * @since 0.1.99
 * @version 0.1.99
 * @author Innoxia
 */
public class AttributeRange {
	private float minimum, maximum;

	public AttributeRange(float minimum, float maximum) {
		super();
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public float getMinimum() {
		return minimum;
	}

	public float getMaximum() {
		return maximum;
	}
	
	public float getMedian() {
		return (maximum+minimum)/2;
	}
	
	public float getRandomVariance() {
		return (float) (Math.random()*(maximum-minimum));
	}
}
