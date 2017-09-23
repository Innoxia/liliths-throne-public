package com.base.game.character;

/**
 * @since 0.1.85
 * @version 0.1.85
 * @author Innoxia
 */
public enum Personality {
	
	/**
	 * Enthusiastic, active, persuasive, social:
	 * +inspirational</br>
	 * +interactive</br>
	 * +interesting</br>
	 * -slow</br>
	 * -sensitive</br>
	 */
	AIR_SOCIABLE("sociable"),
	
	/**
	 * Takes charge, leader, in control:
	 * +direct</br>
	 * +decisive</br>
	 * +confident</br>
	 * -domineering</br>
	 * -demanding</br>
	 */
	FIRE_COMMANDING("commanding"),
	
	/**
	 * Analytical, wise, and quiet:
	 * +cautious</br>
	 * +careful</br>
	 * +conscientious</br>
	 * -scheming</br>
	 * -condescending</br>
	 */
	WATER_ANALYTICAL("analytical"),
	
	/**
	 * Relaxed and peaceful:
	 * +stable</br>
	 * +supportive</br>
	 * +sincere</br>
	 * -slow</br>
	 * -sensitive</br>
	 */
	EARTH_CALM("calm");
	
	private String name;

	private Personality(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
