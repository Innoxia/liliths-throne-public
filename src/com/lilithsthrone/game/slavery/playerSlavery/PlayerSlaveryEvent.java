package com.lilithsthrone.game.slavery.playerSlavery;

import com.lilithsthrone.game.dialogue.DialogueNodeOld;

// This class is running on two things being implied:
// 1: Player is being the slave here and
// 2: Player has the owner set
//
// From that this class should implicitly figure out the owner and the slave in these cases, usually through obvious means.


/**
 * Player Slavery Event class is the class that is called upon when a random event happens in relation to player character during their enslavement. Every slaver NPC should have a set of those.
 * 
 * @author irbynx
 * 
 */
public interface PlayerSlaveryEvent {
	
	/**
	 * Weight generating function. Once called, should return a weight value; normally hovering around 50 for "likely" actions.
	 * 
	 * When actions are generated, the action with biggest weight is picked. To add randomness, every time the getWeight function is called, it should slightly randomize it's own return value as necessary.
	 * @return
	 */
	public abstract int getWeight(boolean isForced);
	
	/**
	 * Returns response dialogue. Used once an event is picked.
	 * @return
	 */
	public abstract DialogueNodeOld getTriggerDialogue();
	
	/**
	 * Returns true if this is a punishment event.
	 */
	public abstract boolean getIsPunishment();
	
}
