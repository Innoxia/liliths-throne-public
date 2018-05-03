package com.lilithsthrone.game.slavery.playerSlavery.rules;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.slavery.playerSlavery.PlayerSlaveryRule;

public class RulesSlaveryDefault {
	
	static public PlayerSlaveryRule RULE_OUTSIDE_FREEDOM = new PlayerSlaveryRule() {

		private static final long serialVersionUID = 1L;
		private long freeTime; // In minutes
		private long maxFreeTime; // In minutes

		@Override
		public Element saveAsXML(Element parentElement, Document doc) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PlayerSlaveryRule loadFromXML(Element parentElement, Document doc) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public float getPerformanceQuality() {
			// No obedience gains from going along with this rule, but overstaying your outside world welcome is punishable.
			if(this.freeTime < 0)
			{
				return (this.maxFreeTime+this.freeTime) / this.maxFreeTime;
			}
			return 0;
		}

		@Override
		public String getName() {
			return "Outside Freedom";
		}

		@Override
		public String getDescription() {
			if(this.freeTime > 60)
			{
				return "Your owner allowed you to spend some time outside, doing your own business. You still have more than an hour of free time.";
			}
			if(this.freeTime > 0)
			{
				return "Your owner allowed you to spend some time outside, doing your own business. You still have " + this.freeTime + " minutes left, hurry up!";
			}
			return "Your owner allowed you to spend some time outside, but you've overstayed your welcome. Hurry up and hope they won't notice!";
		}

		@Override
		public String getUniqueName() {
			return "outside-freedom";
		}

		@Override
		public String getPerformanceComment() {
			if(this.getPerformanceQuality() < -25)
			{
				return "you weren't allowed to walk around for so long, ";
			}
			return null;
		}
		
		/**
		 * Call at the end of a day to reset player's free time.
		 */
		public void resetFreeTime()
		{
			this.freeTime = this.maxFreeTime;
		}
		
		/**
		 * Removes some time from free time.
		 */
		public void lowerFreeTime(long time)
		{
			this.freeTime -= time;
		}
		
		/**
		 * Adjusts maximum free time the player can spend outside without getting penalties.
		 */
		public void adjustMaxFreeTime(long time)
		{
			this.maxFreeTime += time;
			if(this.maxFreeTime < 0)
			{
				this.maxFreeTime = 0;
			}
		}
		
		/**
		 *  Returns true if player can still spend free time outside.
		 */
		public boolean canPlayerRoam()
		{
			return this.freeTime > 0;
		}
		
		/**
		 * Returns the amount of free time remaining.
		 */
		public long getFreeTime()
		{
			return this.freeTime;
		}

	};
}
