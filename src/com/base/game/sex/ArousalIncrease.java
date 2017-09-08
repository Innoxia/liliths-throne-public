package com.base.game.sex;

/**
 * @since 0.1.0
 * @version 0.1.69.9
 * @author Innoxia
 */
public enum ArousalIncrease {

	/**This is a special action for if the player has the denial fetish.*/
	NEGATIVE(-10),
	
	/**Calm down a little.*/
	NEGATIVE_MINOR(-5),

	/**This action is not arousing at all for this partner. For example, if the other partner is masturbating out-of-sight.*/
	ZERO_NONE(0),
	
	/**This action is minimally arousing for this partner. For example, the player is masturbating in front of them.*/
	ONE_MINIMUM(1),
	
	/**This action is not very arousing for this partner. For example, sucking the other partner's cock while receiving no stimulation for themselves.*/
	TWO_LOW(2),
	
	/**This action is of an average amount of arousal. Equating to mild stimulation of your genitals (same level as masturbation).*/
	THREE_NORMAL(3),
	
	/**This action is highly arousing or causing a large amount of stimulation. Most penetrative sex actions should fall into this category.*/
	FOUR_HIGH(4),
	
	/**This action is causing an unusually large amount of stimulation, of the equivalent level as an orgasm. Only very special actions, such as having a demon's vagina squeezing down on your cock, should cause this much arousal.*/
	FIVE_EXTREME(5);

	private float arousalIncreaseValue;

	private ArousalIncrease(float arousalIncreaseValue) {
		this.arousalIncreaseValue = arousalIncreaseValue;
	}

	public float getArousalIncreaseValue() {
		return arousalIncreaseValue;
	}
}
