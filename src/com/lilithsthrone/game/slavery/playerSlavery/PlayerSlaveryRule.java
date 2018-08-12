package com.lilithsthrone.game.slavery.playerSlavery;

import java.io.Serializable;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.slavery.playerSlavery.rules.RulesSlaveryAlleyway;
import com.lilithsthrone.game.slavery.playerSlavery.rules.RulesSlaveryDefault;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.world.WorldType;

public abstract class PlayerSlaveryRule implements Serializable, XMLSaving {

	static public final PlayerSlaveryRule loadFromXML(Element parentElement, Document doc)
	{
		if(parentElement.getElementsByTagName("uniqueName").item(0)!=null)
		{
			switch(((Element)parentElement.getElementsByTagName("uniqueName").item(0)).getAttribute("value"))
			{
				default:
					return null;
				case "outside-freedom" :
					RulesSlaveryDefault.RULE_OUTSIDE_FREEDOM.initFromXML(parentElement, doc);
					return RulesSlaveryDefault.RULE_OUTSIDE_FREEDOM;
				case "footwear-forbidden" :
					RulesSlaveryDefault.RULE_NO_FOOTWEAR.initFromXML(parentElement, doc);
					return RulesSlaveryDefault.RULE_NO_FOOTWEAR;
				case "groin-covering-forbidden" :
					RulesSlaveryDefault.RULE_NO_GROIN_BLOCKERS.initFromXML(parentElement, doc);
					return RulesSlaveryDefault.RULE_NO_GROIN_BLOCKERS;
				case "ass-covering-forbidden" :
					RulesSlaveryDefault.RULE_NO_ASS_BLOCKERS.initFromXML(parentElement, doc);
					return RulesSlaveryDefault.RULE_NO_ASS_BLOCKERS;
				case "breast-covering-forbidden" :
					RulesSlaveryDefault.RULE_NO_BREAST_BLOCKERS.initFromXML(parentElement, doc);
					return RulesSlaveryDefault.RULE_NO_BREAST_BLOCKERS;
				case "daily-tribute-money" :
					RulesSlaveryDefault.RULE_DAILY_TRIBUTE_MONEY.initFromXML(parentElement, doc);
					return RulesSlaveryDefault.RULE_DAILY_TRIBUTE_MONEY;
				case "owners-bitch" :
					RulesSlaveryDefault.RULE_OWNERS_BITCH.initFromXML(parentElement, doc);
					return RulesSlaveryDefault.RULE_OWNERS_BITCH;
				case "name-freedom" :
					RulesSlaveryDefault.RULE_NAME_FREEDOM.initFromXML(parentElement, doc);
					return RulesSlaveryDefault.RULE_NAME_FREEDOM;
				case "alleyway-slavery-bribe-courier" :
					RulesSlaveryAlleyway.RULE_ALLEYWAY_BRIBE_COURIER.initFromXML(parentElement, doc);
					return RulesSlaveryAlleyway.RULE_ALLEYWAY_BRIBE_COURIER;
				case "alleyway-slavery-supply-run" :
					RulesSlaveryAlleyway.RULE_ALLEYWAY_SUPPLY_RUN.initFromXML(parentElement, doc);
					return RulesSlaveryAlleyway.RULE_ALLEYWAY_SUPPLY_RUN;
			}
		}
		return null;
	}
	
	public abstract void initFromXML(Element parentElement, Document doc);
	
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
	 * Removes some time from free time.
	 */
	public void lowerFreeTime(long time)
	{
		// Stub
	}
	
	/**
	 * Adjusts maximum free time the player can spend outside without getting penalties.
	 */
	public void adjustMaxFreeTime(long time)
	{
		// Stub
	}
	
	/**
	 * Sets maximum free time the player can spend outside without getting penalties.
	 */
	public void setMaxFreeTime(long time)
	{
		// Stub
	}
	
	/**
	 *  Returns true if player can still spend free time outside.
	 */
	public boolean canPlayerRoam()
	{
		return true;
	}
	/**
	 * Returns the amount of free time remaining.
	 */
	public long getFreeTime()
	{
		return 0;
	}
	
	/**
	 * Returns the amount of cash the player needs to pay off somehow
	 * @return
	 */
	public int getCashRequirement()
	{
		return 0;
	}
	
	/**
	 * Modifies the amount of cash the player needs to pay off this day
	 * @param modifyBy
	 */
	public void modifyCashRequirement(int cash)
	{
		return;
	}
	
	/**
	 * Resets (daily) the daily rule
	 */
	public void dailyReset()
	{
		return;
	}
	
	/**
	 * Sets the daily amount of cash that the player has to pay up.
	 * @param cash
	 */
	public void setDailyCashRequirement(int cash)
	{
		return;
	}

	public int getDailyCashRequirement() 
	{
		return 0;
	}
	
	/**
	 * Decreases the remaining time the player has to perform this rule.
	 */
	public void timerDecrease(long time)
	{
		return;
	}
	
	/**
	 * Sets the timer to the amount of time the character has to perform the task.
	 * @param time
	 */
	public void timerSet(long time)
	{
		return;
	}
	
	/**
	 * Returns true if player ran out of time for their activity
	 */
	public boolean isTimeUp()
	{
		return false;
	}
	
	/**
	 * Returns location that the rule needs to be fulfilled at. Useful for rules that are like quests, requiring player to deliver something somewhere.
	 * @return
	 */
	public Value<WorldType, Vector2i> getRuleLocation()
	{
		return null;
	}
	
	/**
	 * Returns the item types the character needs to have to fulfill the rule. Useful for fetch quests for player.
	 * @return
	 */
	public Set<String> getRuleItemTypeIDs()
	{
		return null;
	}
	
	/**
	 * Returns the item types the character needs to have to fulfill the rule. Useful for fetch quests for player.
	 * @return
	 */
	public Set<String> getRuleClothingTypeIDs()
	{
		return null;
	}
	
	/**
	 * Returns true if rule was modified today.
	 */
	public boolean wasRuleChangedToday()
	{
		return false;
	}
	
	/**
	 * Resets rule to the default state if needed. Performs re-generation of it's properties as well, can be used for random quests.
	 */
	public void resetRule()
	{
		return;
	}
	
	/**
	 * Sets the rule as completed. Will remove itself and should add obedience gains.
	 */
	public void completeRule()
	{
		return;
	}
	
	/**
	 * Returns true if player can complete the rule. Usually should be used on the last point of the checking.
	 * @return
	 */
	public boolean canCompleteRule()
	{
		return true;
	}
	
	/**
	 * Returns a string that states what the player is missing in order to complete the rule. Use if canCompleteRule returns false so the player would know what is needed of them.
	 * @return
	 */
	public String canCompleteRuleReason()
	{
		return "";
	}
	
	/**
	 * Returns a string containing the target's name for dynamic quests.
	 * @return
	 */
	public String getTargetName()
	{
		return "";
	}
	
	// Irbynx's note:
	// MAYBE: An icon for those as well? Since name and description could be used in the rule sets.
	// I'll probably put the list of active rules in a text form in player description, but feel free to change it around!
}
