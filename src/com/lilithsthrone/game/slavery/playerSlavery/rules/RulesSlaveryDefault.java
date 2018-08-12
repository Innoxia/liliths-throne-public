package com.lilithsthrone.game.slavery.playerSlavery.rules;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.slavery.playerSlavery.PlayerSlaveryRule;
import com.lilithsthrone.main.Main;

public class RulesSlaveryDefault {
	
	static public PlayerSlaveryRule RULE_OUTSIDE_FREEDOM = new PlayerSlaveryRule() {

		private static final long serialVersionUID = 1L;
		private long freeTime; // In minutes
		private long maxFreeTime; // In minutes
		private boolean wasChangedToday;

		@Override
		public Element saveAsXML(Element parentElement, Document doc) {
			Element slaveryRule = doc.createElement("rule");
			parentElement.appendChild(slaveryRule);
			CharacterUtils.createXMLElementWithValue(doc, slaveryRule, "uniqueName", this.getUniqueName());	
			CharacterUtils.createXMLElementWithValue(doc, slaveryRule, "freeTime", String.valueOf(this.freeTime));	
			CharacterUtils.createXMLElementWithValue(doc, slaveryRule, "maxFreeTime", String.valueOf(this.maxFreeTime));
			CharacterUtils.createXMLElementWithValue(doc, slaveryRule, "wasChangedToday", String.valueOf(this.maxFreeTime));
			return slaveryRule;
		}

		@Override
		public void initFromXML(Element parentElement, Document doc) {
			wasChangedToday = Boolean.valueOf(((Element)parentElement.getElementsByTagName("wasChangedToday").item(0)).getAttribute("value"));
			freeTime = Long.valueOf(((Element)parentElement.getElementsByTagName("freeTime").item(0)).getAttribute("value"));
			maxFreeTime = Long.valueOf(((Element)parentElement.getElementsByTagName("maxFreeTime").item(0)).getAttribute("value"));
		}
		
		@Override
		public float getPerformanceQuality() {
			// No obedience gains from going along with this rule, but overstaying your outside world welcome is punishable.
			if(this.freeTime < 0)
			{
				return ((this.maxFreeTime+this.freeTime) / this.maxFreeTime)*-1;
			}
			return 0;
		}

		@Override
		public String getName() {
			return "Outside Freedom";
		}

		@Override
		public String getDescription() {
			int totalFreeHours = (int)(this.maxFreeTime / 60);
			int totalRemainingHours = (int)(this.freeTime / 60);
			if(this.freeTime > 60)
			{
				return "Your owner allowed you to spend some time outside, doing your own business for up to "+totalFreeHours+" hours. You still have "+totalRemainingHours+" hours of free time.";
			}
			if(this.freeTime > 0)
			{
				return "Your owner allowed you to spend some time outside, doing your own business for up to "+totalFreeHours+" hours. You still have " + this.freeTime + " minutes left, hurry up!";
			}
			return "Your owner allowed you to spend some time outside for up to "+totalFreeHours+" hours, but you've overstayed your welcome. Hurry up and hope they won't notice!";
		}

		@Override
		public String getUniqueName() {
			return "outside-freedom";
		}

		@Override
		public String getPerformanceComment() {
			if(this.getPerformanceQuality() < -25)
			{
				return "weren't allowed to walk around for so long";
			}
			return null;
		}
		
		/**
		 * Call at the end of a day to reset player's free time.
		 */
		@Override
		public void dailyReset()
		{
			this.freeTime = this.maxFreeTime;
			this.wasChangedToday = false;
		}
		
		/**
		 * Removes some time from free time.
		 */
		@Override
		public void lowerFreeTime(long time)
		{
			this.freeTime -= time;
		}
		
		/**
		 * Adjusts maximum free time the player can spend outside without getting penalties.
		 */
		@Override
		public void adjustMaxFreeTime(long time)
		{
			this.wasChangedToday = true;
			this.maxFreeTime += time;
			if(this.maxFreeTime < 0)
			{
				this.maxFreeTime = 0;
			}
			this.freeTime = this.maxFreeTime;
		}
		
		@Override
		public void setMaxFreeTime(long time)
		{
			this.wasChangedToday = true;
			this.maxFreeTime = time;
			if(this.maxFreeTime < 0)
			{
				this.maxFreeTime = 0;
			}
			this.freeTime = this.maxFreeTime;
		}
		
		/**
		 *  Returns true if player can still spend free time outside.
		 */
		@Override
		public boolean canPlayerRoam()
		{
			return this.freeTime > 0;
		}
		
		/**
		 * Returns the amount of free time remaining.
		 */
		@Override
		public long getFreeTime()
		{
			return this.freeTime;
		}
		
		@Override
		public boolean wasRuleChangedToday()
		{
			return this.wasChangedToday;
		}

	};
	
	static public PlayerSlaveryRule RULE_NO_FOOTWEAR = new PlayerSlaveryRule() {

		private static final long serialVersionUID = 1L;

		@Override
		public Element saveAsXML(Element parentElement, Document doc) {
			Element slaveryRule = doc.createElement("rule");
			parentElement.appendChild(slaveryRule);
			CharacterUtils.createXMLElementWithValue(doc, slaveryRule, "uniqueName", this.getUniqueName());	
			return slaveryRule;
		}

		@Override
		public void initFromXML(Element parentElement, Document doc) {
			//
		}
		
		@Override
		public float getPerformanceQuality() {
			// Obedience gains go up to 0, but not higher.
			boolean isPositive = true;
			if(Main.game.getPlayer().getClothingInSlot(InventorySlot.LEG) != null || Main.game.getPlayer().getClothingInSlot(InventorySlot.SOCK) != null)
			{
				isPositive = false;
			}
			if(isPositive)
			{
				if(Main.game.getPlayer().getObedienceValue() < -5f)
				{
					return 5f;
				}
				else if(Main.game.getPlayer().getObedienceValue() >= 0)
				{
					return 0;
				}
				else
				{
					return Main.game.getPlayer().getObedienceValue()*-1;
				}
			}
			return -5f; // Normal loss of obedience
		}

		@Override
		public String getName() {
			return "Footwear Forbidden";
		}

		@Override
		public String getDescription() {
			if(this.getPerformanceQuality() >= 0)
			{
				return "You are not allowed to wear any footwear at all, not even socks.";
			}
			return "You are not allowed to wear any footwear at all, not even socks, but you are clearly ignoring the order!";
		}

		@Override
		public String getUniqueName() {
			return "footwear-forbidden";
		}

		@Override
		public String getPerformanceComment() {
			if(this.getPerformanceQuality() < 0)
			{
				return "had to wear nothing on your feet";
			}
			return null;
		}

	};
	
	static public PlayerSlaveryRule RULE_NO_GROIN_BLOCKERS = new PlayerSlaveryRule() {

		private static final long serialVersionUID = 1L;

		@Override
		public Element saveAsXML(Element parentElement, Document doc) {
			Element slaveryRule = doc.createElement("rule");
			parentElement.appendChild(slaveryRule);
			CharacterUtils.createXMLElementWithValue(doc, slaveryRule, "uniqueName", this.getUniqueName());	
			return slaveryRule;
		}

		@Override
		public void initFromXML(Element parentElement, Document doc) {
			//
		}
		
		@Override
		public float getPerformanceQuality() {
			// Obedience gains go up to 0, but not higher.
			boolean isPositive = false;
			if((Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS) && Main.game.getPlayer().getPenisType() != PenisType.NONE)
					|| (Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.VAGINA) && Main.game.getPlayer().getVaginaType() != VaginaType.NONE))
			{
				isPositive = true;
			}
			if(isPositive)
			{
				if(Main.game.getPlayer().getObedienceValue() < -5f)
				{
					return 5f;
				}
				else if(Main.game.getPlayer().getObedienceValue() >= 0)
				{
					return 0;
				}
				else
				{
					return Main.game.getPlayer().getObedienceValue()*-1;
				}
			}
			return -5f; // Normal loss of obedience
		}

		@Override
		public String getName() {
			return "Keep Groin Naked";
		}

		@Override
		public String getDescription() {
			if(this.getPerformanceQuality() >= 0)
			{
				return "You are not allowed to cover your nether regions with any clothing, leaving your privates exposed.";
			}
			return "You are not allowed to cover your nether regions with any clothing, leaving your privates exposed, but you are clearly ignoring the order!";
		}

		@Override
		public String getUniqueName() {
			return "groin-covering-forbidden";
		}

		@Override
		public String getPerformanceComment() {
			if(this.getPerformanceQuality() < 0)
			{
				return "had to keep your groin shown";
			}
			return null;
		}

	};
	
	static public PlayerSlaveryRule RULE_NO_ASS_BLOCKERS = new PlayerSlaveryRule() {

		private static final long serialVersionUID = 1L;

		@Override
		public Element saveAsXML(Element parentElement, Document doc) {
			Element slaveryRule = doc.createElement("rule");
			parentElement.appendChild(slaveryRule);
			CharacterUtils.createXMLElementWithValue(doc, slaveryRule, "uniqueName", this.getUniqueName());	
			return slaveryRule;
		}

		@Override
		public void initFromXML(Element parentElement, Document doc) {
			//
		}
		
		@Override
		public float getPerformanceQuality() {
			// Obedience gains go up to 0, but not higher.
			boolean isPositive = true;
			if(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.ANUS))
			{
				isPositive = false;
			}
			if(isPositive)
			{
				if(Main.game.getPlayer().getObedienceValue() < -5f)
				{
					return 5f;
				}
				else if(Main.game.getPlayer().getObedienceValue() >= 0)
				{
					return 0;
				}
				else
				{
					return Main.game.getPlayer().getObedienceValue()*-1;
				}
			}
			return -5f; // Normal loss of obedience
		}

		@Override
		public String getName() {
			return "Keep Ass Naked";
		}

		@Override
		public String getDescription() {
			if(this.getPerformanceQuality() >= 0)
			{
				return "You are not allowed to cover your butt with any clothing, leaving it exposed.";
			}
			return "You are not allowed to cover your butt with any clothing, leaving it exposed, but you are clearly ignoring the order!";
		}

		@Override
		public String getUniqueName() {
			return "ass-covering-forbidden";
		}

		@Override
		public String getPerformanceComment() {
			if(this.getPerformanceQuality() < 0)
			{
				return "were told to keep your ass shown";
			}
			return null;
		}

	};
	
	static public PlayerSlaveryRule RULE_NO_BREAST_BLOCKERS = new PlayerSlaveryRule() {

		private static final long serialVersionUID = 1L;

		@Override
		public Element saveAsXML(Element parentElement, Document doc) {
			Element slaveryRule = doc.createElement("rule");
			parentElement.appendChild(slaveryRule);
			CharacterUtils.createXMLElementWithValue(doc, slaveryRule, "uniqueName", this.getUniqueName());	
			return slaveryRule;
		}

		@Override
		public void initFromXML(Element parentElement, Document doc) {
			//
		}
		
		@Override
		public float getPerformanceQuality() {
			// Obedience gains go up to 0, but not higher.
			boolean isPositive = true;
			if(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.BREASTS))
			{
				isPositive = false;
			}
			if(isPositive)
			{
				if(Main.game.getPlayer().getObedienceValue() < -5f)
				{
					return 5f;
				}
				else if(Main.game.getPlayer().getObedienceValue() >= 0)
				{
					return 0;
				}
				else
				{
					return Main.game.getPlayer().getObedienceValue()*-1;
				}
			}
			return -5f; // Normal loss of obedience
		}

		@Override
		public String getName() {
			return "Keep Breasts Naked";
		}

		@Override
		public String getDescription() {
			if(this.getPerformanceQuality() >= 0)
			{
				return "You are not allowed to cover your breasts with any clothing, leaving your chest exposed.";
			}
			return "You are not allowed to cover your breasts with any clothing, leaving your chest exposed, but you are clearly ignoring the order!";
		}

		@Override
		public String getUniqueName() {
			return "breast-covering-forbidden";
		}

		@Override
		public String getPerformanceComment() {
			if(this.getPerformanceQuality() < 0)
			{
				return "had to have your breasts uncovered";
			}
			return null;
		}

	};
	
	static public PlayerSlaveryRule RULE_DAILY_TRIBUTE_MONEY = new PlayerSlaveryRule() {

		private static final long serialVersionUID = 1L;
		
		private int dailyCash;
		private int remainingCash;

		@Override
		public Element saveAsXML(Element parentElement, Document doc) {
			Element slaveryRule = doc.createElement("rule");
			parentElement.appendChild(slaveryRule);
			CharacterUtils.createXMLElementWithValue(doc, slaveryRule, "uniqueName", this.getUniqueName());	
			CharacterUtils.createXMLElementWithValue(doc, slaveryRule, "dailyCash", String.valueOf(this.dailyCash));	
			CharacterUtils.createXMLElementWithValue(doc, slaveryRule, "remainingCash", String.valueOf(this.remainingCash));
			return slaveryRule;
		}

		@Override
		public void initFromXML(Element parentElement, Document doc)  {
			dailyCash = Integer.valueOf(((Element)parentElement.getElementsByTagName("dailyCash").item(0)).getAttribute("value"));
			remainingCash = Integer.valueOf(((Element)parentElement.getElementsByTagName("remainingCash").item(0)).getAttribute("value"));
		}
		
		@Override
		public float getPerformanceQuality() {
			if(dailyCash < remainingCash) // This implies the player has skipped a day and didn't pay up fully.
			{
				return -5f - 5f * (remainingCash / dailyCash); 
			}
			return 5f; // Normal gain of obedience
		}

		@Override
		public String getName() {
			return "Daily Tribute "+UtilText.getCurrencySymbol()+" (Flames)";
		}

		@Override
		public String getDescription() {
			if(this.getPerformanceQuality() >= 0)
			{
				if(remainingCash < 0)
				{
					return "Your master demands a daily tribute of "+UtilText.formatAsMoney(dailyCash)+" and you have paid it fully today and got a little extra too.";
				}
				else if(remainingCash == 0)
				{
					return "Your master demands a daily tribute of "+UtilText.formatAsMoney(dailyCash)+" and you have paid it fully today.";
				}
				return "Your master demands a daily tribute of "+UtilText.formatAsMoney(dailyCash)+" and you have "+UtilText.formatAsMoney(remainingCash)+" left to pay.";
			}
			return "Your master demands a daily tribute of "+UtilText.formatAsMoney(dailyCash)+" but you have "+UtilText.formatAsMoney(remainingCash)+" left to pay, since you missed your last payment!";
		}

		@Override
		public String getUniqueName() {
			return "daily-tribute-money";
		}

		@Override
		public String getPerformanceComment() {
			if(this.getPerformanceQuality() < 0)
			{
				return "didn't pay me my well earned money";
			}
			return null;
		}
		
		/**
		 * Returns the amount of cash the player needs to pay off somehow
		 * @return
		 */
		@Override
		public int getCashRequirement()
		{
			return this.remainingCash;
		}
		
		/**
		 * Modifies the amount of cash the player needs to pay off this day
		 * @param modifyBy
		 */
		@Override
		public void modifyCashRequirement(int cash)
		{
			this.remainingCash += cash;
		}
		
		/**
		 * Resets (daily) the daily rule
		 */
		@Override
		public void dailyReset()
		{
			// Doesn't exactly reset daily. It accumulates debt instead.
			this.remainingCash += this.dailyCash;
		}
		
		/**
		 * Sets the daily amount of cash that the player has to pay up.
		 * @param cash
		 */
		@Override
		public void setDailyCashRequirement(int cash)
		{
			this.dailyCash = cash;
		}
		
		/**
		 * Returns the daily amount of cash that the player has to pay up.
		 */
		@Override
		public int getDailyCashRequirement()
		{
			return this.dailyCash;
		}

	};
	
	static public PlayerSlaveryRule RULE_OWNERS_BITCH = new PlayerSlaveryRule() {

		private static final long serialVersionUID = 1L;

		@Override
		public Element saveAsXML(Element parentElement, Document doc) {
			Element slaveryRule = doc.createElement("rule");
			parentElement.appendChild(slaveryRule);
			CharacterUtils.createXMLElementWithValue(doc, slaveryRule, "uniqueName", this.getUniqueName());	
			return slaveryRule;
		}

		@Override
		public void initFromXML(Element parentElement, Document doc)  {
		}
		
		@Override
		public float getPerformanceQuality() {
			if(!Main.game.getPlayer().getName().equalsIgnoreCase(this.getTargetName()))
			{
				return -15f; 
			}
			return 5f; // Normal gain of obedience
		}

		@Override
		public String getName() {
			return "Master's Bitch";
		}

		@Override
		public String getDescription() {
			if(this.getPerformanceQuality() >= 0)
			{
				return "Your master demanded you to change your name to "+this.getTargetName()+" and you agreed to it... And now you are wearing it proudly!";
			}
			return "Your master demanded you to change your name to "+this.getTargetName()+" and you agreed to it. You don't have it changed right now, which is clearly a show of disobedience!";
		}

		@Override
		public String getUniqueName() {
			return "owners-bitch";
		}

		@Override
		public String getPerformanceComment() {
			if(this.getPerformanceQuality() < 0)
			{
				return "kept your worthless name";
			}
			return "kept your name as I like it";
		}
		
		@Override
		public String getTargetName()
		{
			return Main.game.getPlayer().getOwner().getNameIgnoresPlayerKnowledge()+"'s Bitch";
		}

	};
	
	//Special rule for when the players deny a request to change their name to something else.
	static public PlayerSlaveryRule RULE_NAME_FREEDOM = new PlayerSlaveryRule() {

		private static final long serialVersionUID = 1L;

		@Override
		public Element saveAsXML(Element parentElement, Document doc) {
			Element slaveryRule = doc.createElement("rule");
			parentElement.appendChild(slaveryRule);
			CharacterUtils.createXMLElementWithValue(doc, slaveryRule, "uniqueName", this.getUniqueName());	
			return slaveryRule;
		}

		@Override
		public void initFromXML(Element parentElement, Document doc)  {
		}
		
		@Override
		public float getPerformanceQuality() {
			return 0f; // Normal gain of obedience
		}

		@Override
		public String getName() {
			return "Freedom of Name";
		}

		@Override
		public String getDescription() {
			return "You have a freedom to keep your name.";
		}

		@Override
		public String getUniqueName() {
			return "name-freedom";
		}

		@Override
		public String getPerformanceComment() {
			return null;
		}

	};
}
