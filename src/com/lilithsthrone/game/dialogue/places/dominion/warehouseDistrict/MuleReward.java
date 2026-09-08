package com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict;

import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.7
 * @version 0.3.7.7
 * @author Innoxia
 */
public abstract class MuleReward {

	private String name;
	private String description;
	private int cost;
	
	public MuleReward(String name, String description, int cost) {
		this.name = name;
		this.description = description;
		this.cost = cost;
	}
	
	public Value<Boolean, String> isAvailable() {
		return new Value<>(true, "");
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getCost() {
		return cost;
	}
	
	public abstract String applyEffect();
}
