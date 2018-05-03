package com.lilithsthrone.game.slavery.playerSlavery;

import java.io.Serializable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.utils.XMLSaving;

public abstract class PlayerSlaveryRule implements Serializable, XMLSaving {

	public abstract PlayerSlaveryRule loadFromXML(Element parentElement, Document doc);
	
	private static final long serialVersionUID = 1L;

	/**
	 * Checks the quality of player's performance and returns an obedience value. No need to adjust for several rules in place, the calling function should handle this.
	 * 
	 * Returns positive values for good performance and negative for poor performance.
	 * @return
	 */
	public abstract float getPerformanceQuality();
	
	/**
	 * Returns the name of the rule in a readable way. Don't compare with it.
	 * @return
	 */
	public abstract String getName();
	
	/**
	 * Returns the description of the rule in a readable way. Hopefully to be used in some sort of UI element.
	 * @return
	 */
	public abstract String getDescription();
	
	/**
	 * A technical unique name (hopefully) to be used for a rule. Used for lookups and comparisons (for example to see if player has "No clothing" rule set for them).
	 * @return
	 */
	public abstract String getUniqueName();
	
	
	/**
	 * Returns a comment from an NPC about the performance that can be inserted into a sentence. Example:
	 * 
	 * (Bad performance; player put on clothing when told not to)
	 * "you put on clothing, when I told you not to "
	 * 
	 * (Good performance; same, but player was being naked)
	 * "you kept yourself naked, good job "
	 * 
	 * This can be used by punishment (and reward) events that will grade player's good or bad performances and let the NPC remark on them while punishing or rewarding player, for example:
	 * "You got punishment because you put on clothing, when I told you not to." <- Injected the string here.
	 * 
	 * This function should use getPerformanceQuality to make sure it returns a proper comment. Sometimes it's not needed and can return null, although in case of low performance values that go into negatives, a comment is highly encouraged to let the player know about why are they getting punished.
	 * @return
	 */
	public abstract String getPerformanceComment();
	
	// Following are overrideable member functions that are needed for some specific instances.
	
	// Outside freedom rules and rules that have a timer on them.
	/**
	 * Call at the end of a day to reset player's free time.
	 */
	public abstract void resetFreeTime();
	
	/**
	 * Removes some time from free time.
	 */
	public abstract void lowerFreeTime(long time);
	
	/**
	 * Adjusts maximum free time the player can spend outside without getting penalties.
	 */
	public abstract void adjustMaxFreeTime(long time);
	
	/**
	 *  Returns true if player can still spend free time outside.
	 */
	public abstract boolean canPlayerRoam();
	/**
	 * Returns the amount of free time remaining.
	 */
	public abstract long getFreeTime();
	
	// Irbynx's note:
	// MAYBE: An icon for those as well? Since name and description could be used in the rule sets.
	// I'll probably put the list of active rules in a text form in player description, but feel free to change it around!
}
