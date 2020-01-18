package com.lilithsthrone.game.inventory.enchanting;
import com.lilithsthrone.main.Main;

/**
 * @since 0.2.0
 * @version 0.3.1
 * @author Innoxia
 */
public class ItemEffectTimer {
	private int secondsPassed;
	
	public ItemEffectTimer() {
		if(Main.game!=null) {
			secondsPassed = (int) (Main.game.getSecondsPassed()%(60*60)); // To synchronise all TFs on the hour.
		} else {
			secondsPassed = 0;
		}
	}
	
	public int getSecondsPassed() {
		return secondsPassed;
	}

	public void setSecondsPassed(int secondsPassed) {
		this.secondsPassed = secondsPassed;
	}
	
	public void incrementSecondsPassed(int increment) {
		setSecondsPassed(getSecondsPassed() + increment);
	}

}
