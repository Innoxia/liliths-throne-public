package com.lilithsthrone.game.slavery.playerSlavery.rules;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.slavery.playerSlavery.PlayerSlaveryRule;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

public class RulesSlaveryAlleyway {
	
	static public PlayerSlaveryRule RULE_ALLEYWAY_BRIBE_COURIER = new PlayerSlaveryRule() {

		private static final long serialVersionUID = 1L;
		
		private long timer;

		@Override
		public Element saveAsXML(Element parentElement, Document doc) {
			Element slaveryRule = doc.createElement("rule");
			parentElement.appendChild(slaveryRule);
			CharacterUtils.createXMLElementWithValue(doc, slaveryRule, "uniqueName", this.getUniqueName());	
			CharacterUtils.createXMLElementWithValue(doc, slaveryRule, "timer", String.valueOf(this.timer));
			return slaveryRule;
		}

		@Override
		public void initFromXML(Element parentElement, Document doc) {
			timer = Long.valueOf(((Element)parentElement.getElementsByTagName("timer").item(0)).getAttribute("value"));
		}
		
		@Override
		public float getPerformanceQuality() {
			return 0f; 
		}

		@Override
		public String getName() {
			return "Deliver a bribe";
		}

		@Override
		public String getDescription() {
			int hoursLeft = (int)(this.timer / 60);
			return "You have "+hoursLeft+" hours left to deliver the bribe to Enforcer's HQ."
					+ " Look for Candi, the local bimbo secretary, who will definitely accept it without batting an eye.";
		}

		@Override
		public String getUniqueName() {
			return "alleyway-slavery-bribe-courier";
		}

		@Override
		public String getPerformanceComment() {
			if(this.isTimeUp())
			{
				return "had to deliver the gift to my friends at enforcer HQ in time";
			}
			return null;
		}
		
		@Override
		public void timerDecrease(long time)
		{
			this.timer -= time;
		}
		
		@Override
		public void timerSet(long time)
		{
			this.timer = time;
		}
		
		@Override
		public boolean isTimeUp()
		{
			return this.timer <= 0;
		}

	};
	
	static public PlayerSlaveryRule RULE_ALLEYWAY_SUPPLY_RUN = new PlayerSlaveryRule() {

		private static final long serialVersionUID = 1L;
		
		private boolean isClothing; // True if clothing; false if an item
		private String itemID; // Item/Clothing ID that is requested.
		private long timer;
		
		@Override
		public String getTargetName()
		{
			if(isClothing)
			{
				return ClothingType.getClothingTypeFromId(this.itemID).getName();
			}
			else
			{
				return ItemType.idToItemMap.get(this.itemID).getName(false);
			}
		}

		@Override
		public Element saveAsXML(Element parentElement, Document doc) {
			Element slaveryRule = doc.createElement("rule");
			parentElement.appendChild(slaveryRule);
			CharacterUtils.createXMLElementWithValue(doc, slaveryRule, "uniqueName", this.getUniqueName());	
			CharacterUtils.createXMLElementWithValue(doc, slaveryRule, "itemID", itemID);	
			CharacterUtils.createXMLElementWithValue(doc, slaveryRule, "timer", String.valueOf(this.timer));
			CharacterUtils.createXMLElementWithValue(doc, slaveryRule, "isClothing", String.valueOf(this.isClothing));
			return slaveryRule;
		}

		@Override
		public void initFromXML(Element parentElement, Document doc) {
			timer = Long.valueOf(((Element)parentElement.getElementsByTagName("timer").item(0)).getAttribute("value"));
			itemID = String.valueOf(((Element)parentElement.getElementsByTagName("itemID").item(0)).getAttribute("value"));
			isClothing = Boolean.valueOf(((Element)parentElement.getElementsByTagName("isClothing").item(0)).getAttribute("value"));
		}
		
		@Override
		public float getPerformanceQuality() {
			return 0f; 
		}

		@Override
		public String getName() {
			return "Get a "+this.getTargetName();
		}

		@Override
		public String getDescription() {
			int hoursLeft = (int)(this.timer / 60);
			return "You have "+hoursLeft+" hours left to get the "+this.getTargetName()+" to your master.";
		}

		@Override
		public String getUniqueName() {
			return "alleyway-slavery-supply-run";
		}

		@Override
		public String getPerformanceComment() {
			if(this.isTimeUp())
			{
				return "had to get me a "+this.getTargetName()+" in time";
			}
			return null;
		}
		
		@Override
		public void timerDecrease(long time)
		{
			this.timer -= time;
		}
		
		@Override
		public void timerSet(long time)
		{
			this.timer = time;
		}
		
		@Override
		public boolean isTimeUp()
		{
			return this.timer <= 0;
		}
		
		@Override
		public void resetRule()
		{
			double randVal = Math.random();
			this.timer = 1440;
			if(randVal < 0.75) // Requesting item
			{
				this.isClothing = false;
				itemID = ItemType.dominionAlleywayItems.get(Util.random.nextInt(ItemType.dominionAlleywayItems.size())).getId();
			}
			else // 25% chance to request clothing
			{
				this.isClothing = true;
				itemID = ClothingType.getCommonClothing().get(Util.random.nextInt(ClothingType.getCommonClothing().size())).getId();
			}
		}
		
		@Override
		public boolean canCompleteRule()
		{
			if(this.isClothing)
			{
				for(AbstractClothing clothing : Main.game.getPlayer().getAllClothingInInventory())
				{
					if(clothing.getClothingType().getId() == this.itemID)
					{
						return true;
					}
				}
			}
			else
			{
				if(Main.game.getPlayer().hasItemType(ItemType.idToItemMap.get(this.itemID)))
				{
					return true;
				}
				/*for(AbstractItem item : Main.game.getPlayer().getAllItemsInInventory())
				{
					System.out.println(item.getItemType().getId() + " == " + this.itemID);
					if(item.getItemType().getId() == this.itemID)
					{
						System.out.println(item.getItemType().getId() + " DEFO = " + this.itemID);
						return true;
					}
				}*/
			}
			return false;
		}
		
		@Override
		public String canCompleteRuleReason()
		{
			if(!this.canCompleteRule())
			{
				return "You don't have "+this.getTargetName()+" in your inventory!";
			}
			return "";
		}
		
		@Override
		public void completeRule()
		{
			// Preventing forced rule completion if it was unable to be completed to avoid null pointer exceptions.
			if(!this.canCompleteRule())
			{
				return;
			}
			
			Main.game.getPlayer().getOwner().removeRule(this);
			this.timer = 1;
			if(this.isClothing)
			{
				AbstractClothing clothingToRemove = null;
				for(AbstractClothing clothing : Main.game.getPlayer().getAllClothingInInventory())
				{
					if(clothing.getClothingType().getId() == this.itemID)
					{
						clothingToRemove = clothing;
					}
				}
				Main.game.getPlayer().removeClothing(clothingToRemove);
			}
			else
			{
				AbstractItem itemToRemove = null;
				for(AbstractItem item : Main.game.getPlayer().getAllItemsInInventory())
				{
					if(item.getItemType() == ItemType.idToItemMap.get(this.itemID))
					{
						itemToRemove = item;
					}
				}
				Main.game.getPlayer().removeItem(itemToRemove);
			}
			Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementObedience(10f, false));
			Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().getOwner().incrementAffection(Main.game.getPlayer(), 5f));
		}

	};
}
