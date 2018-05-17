package com.lilithsthrone.game.inventory.enchanting;

/**
 * I am tired and don't know what I'm doing. I hope this isn't too stupid.
 * 
 * @since 0.2.0
 * @version 0.2.0
 * @author Innoxia
 */
public class ItemEffectTimer {
	private int timePassed = 0;

	public int getTimePassed() {
		return timePassed;
	}

	public void setTimePassed(int timePassed) {
		this.timePassed = timePassed;
	}
	
	public void incrementTimePassed(int increment) {
		setTimePassed(getTimePassed() + increment);
	}

}
