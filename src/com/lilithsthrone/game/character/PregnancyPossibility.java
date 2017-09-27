package com.lilithsthrone.game.character;

import java.io.Serializable;

/**
 * @since 0.1.66
 * @version 0.1.75
 * @author Innoxia
 */
public class PregnancyPossibility implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private GameCharacter mother, father;
	private float probability;
	
	public PregnancyPossibility(GameCharacter mother, GameCharacter father, float probability) {
		this.mother = mother;
		this.father = father;
		this.probability = probability;
	}
	
	public GameCharacter getMother() {
		return mother;
	}

	public GameCharacter getFather() {
		return father;
	}

	public float getProbability() {
		return probability;
	}
	
}
