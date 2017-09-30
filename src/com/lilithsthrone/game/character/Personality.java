package com.lilithsthrone.game.character;

import java.util.List;

import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.85
 * @version 0.1.86
 * @author Innoxia
 */
public enum Personality {
	
	/**
	 * Enthusiastic, active, persuasive, social:
	 * +inspirational</br>
	 * +interactive</br>
	 * +interesting</br>
	 * -impulsive</br>
	 * -loud</br>
	 */
	AIR_SOCIABLE(Colour.BASE_YELLOW,
			"sociable",
			"Sociable characters are outgoing, active, and enthusiastic. They are the easiest to gain affection with, and will often react to a situation with a positive outlook.",
			"You are outgoing, active, and enthusiastic. You enjoy the company of others, and will always try to find the good in any situation.",
			Util.newArrayListOfValues(
					new ListValue<>("")),
			Util.newArrayListOfValues(
					new ListValue<>(""))),
	
	/**
	 * Takes charge, leader, in control: +direct</br>
	 * +decisive</br>
	 * +confident</br>
	 * -domineering</br>
	 * -demanding</br>
	 */
	FIRE_COMMANDING(Colour.BASE_RED,
			"commanding",
			"Commanding characters are direct, prefer to act rather than think, and are willing to take risks."
					+ " They are the most resilient to obedience training, and will often react to a situation by trying to take control and tackling the issue head-on.",
			"You are a very direct and commanding person, and prefer to take charge of a situation in order to take action, even if it involves taking some risks.",
			Util.newArrayListOfValues(
					new ListValue<>("")),
			Util.newArrayListOfValues(
					new ListValue<>(""))),
	
	/**
	 * Analytical, wise, and quiet: +cautious</br>
	 * +careful</br>
	 * +conscientious</br>
	 * -scheming</br>
	 * -condescending</br>
	 */
	WATER_ANALYTICAL(Colour.BASE_BLUE,
			"analytical",
			"Analytical characters are quiet, careful, and prefer to think rather than act."
					+ " Often being quite introverted and reluctant to socialise, they are the hardest to gain affection with, and will typically respond to a situation by stopping and thinking carefully about it.",
			"You are a deep thinker, and prefer to stay quiet and analyse problems rather than diving in head-first.",
			Util.newArrayListOfValues(
					new ListValue<>("")),
			Util.newArrayListOfValues(
					new ListValue<>(""))),
	
	/**
	 * Relaxed and peaceful: +stable</br>
	 * +supportive</br>
	 * +sincere</br>
	 * -slow</br>
	 * -sensitive</br>
	 */
	EARTH_CALM(Colour.BASE_GREEN,
			"calm",
			"Calm characters are relaxed and peaceful, and prefer to maintain the status quo rather than take any risks."
					+ " Typically willing to do as they're told, calm characters are the more receptive to obedience training, and will respond to a situation by trying to do whatever is in everyone's best interests.",
			"You are calm and peaceful, and know that you can take on all of life's problems by being a steady, rational individual.",
			Util.newArrayListOfValues(
					new ListValue<>("")),
			Util.newArrayListOfValues(
					new ListValue<>("")));

	private Colour colour;
	private String name, description, descriptionForPlayer;
	private List<String> positives;
	private List<String> negatives;
	
	private Personality(Colour colour, String name, String description, String descriptionForPlayer, List<String> positives, List<String> negatives) {
		this.colour = colour;
		this.name = name;
		this.description = description;
		this.descriptionForPlayer = descriptionForPlayer;
		this.positives = positives;
		this.negatives = negatives;
	}

	public Colour getColour() {
		return colour;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	public String getDescriptionForPlayer() {
		return descriptionForPlayer;
	}

	public List<String> getPositives() {
		return positives;
	}

	public List<String> getNegatives() {
		return negatives;
	}
	
}
