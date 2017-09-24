package com.base.game.dialogue.places.dominion.shoppingArcade;

import com.base.game.character.npc.dominion.Nyan;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseEffectsOnly;
import com.base.game.dialogue.responses.ResponseTrade;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.main.Main;
import com.base.world.WorldType;
import com.base.world.places.ShoppingArcade;

/**
 * @since 0.1.82
 * @version 0.1.82
 * @author Innoxia
 */
public class ClothingEmporium {

	public static final DialogueNodeOld EXTERIOR = new DialogueNodeOld("Nyan's Clothing Emporium (Exterior)", "-", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Most of the clothing stores in the shopping arcade are small, one-story affairs, with a limited selection of goods on offer."
						+ " The establishment before you appears to be the exception to this rule, and takes the form of a huge, two-story clothing department bearing the sign 'Nyan's Clothing Emporium'."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				if(Main.game.getDialogueFlags().nyanIntroduced) {
					return new Response("Enter", "Step inside Nyan's Clothing Emporium.", SHOP_CLOTHING_REPEAT);
					
				} else {
					return new Response("Enter", "Step inside Nyan's Clothing Emporium.", SHOP_CLOTHING);
				}
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Arcade Entrance", "Fast travel to the entrance to the arcade."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), ShoppingArcade.ARCADE_ENTRANCE, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SHOP_CLOTHING = new DialogueNodeOld("Nyan's Clothing Emporium", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Stepping through the open glass doors, you find yourself in a large, modern-looking clothing store."
						+ " As you walk through the building, you discover that not only is this the largest shop in the arcade, but that it's also the busiest."
						+ " Crowds of shoppers block many of the aisles that you'd like to walk down, and every member of staff that you see is busily occupied with other customers."
					+ "</p>"
					+ "<p>"
						+ "Deciding that it might be best to check out one of the smaller, quieter clothing stores, you turn around and start to head for the exit."
						+ " As you do so, you hear a nervous voice calling out from behind you, "
						+ "[nyan.speech(E-erm, excuse me? C-can I help you?)]"
					+ "</p>"
					+ "<p>"
						+ "Turning once more, this time in the direction that the timid voice came from, you find yourself looking at a lesser cat-girl."
						+ " As you look into her bright blue eyes, her cheeks instantly turn a deep shade of red, and she glances down at the floor and starts shuffling her feet, "
						+ "[nyan.speech(H-hello... T-there's nobody else available, you see, s-so I thought maybe I could help. Oh, erm, I-I'm Nyan... I probably should have said that first... W-welcome to my store...)]"
					+ "</p>"
					+ "<p>"
						+ "If it wasn't for the fact that she's wearing a staff uniform, and has a little name tag pinned to her blouse reading 'Nyan, Store Manager', you're not sure if you would have believed that she's the store's owner."
						+ " In an attempt to put the anxious cat-girl at ease, you ignore the fact that she's visibly trembling and explain your situation to her, "
						+ "[pc.speech(Pleased to meet you Nyan, it's a nice place you've got here! I was just having a little difficulty finding anything in amongst all these crowds...)]"
					+ "</p>"
					+ "<p>"
						+ "[nyan.speech(O-oh, sorry! E-everything we have on display can also b-be found back in our stockroom. F-follow me and I'll show you!)]"
					+ "</p>"
					+ "<p>"
						+ "In what appears to be an almost desperate attempt to escape further conversation, Nyan hurries past you and heads off towards a door bearing the sign 'Staff Only'."
						+ " Doing as she instructed, you follow her through the door into the store's stockroom."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Stockroom", "Nyan has shown you into the stockroom.", SHOP_CLOTHING_STOCK_ROOM) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().nyanIntroduced=true;
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SHOP_CLOTHING_STOCK_ROOM = new DialogueNodeOld("Nyan's Clothing Emporium", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "The stockroom of Nyan's Clothing Emporium looks pretty much exactly as you'd expected it be."
						+ " Ceiling-height shelving units are stocked full of clothes, shoes, and accessories of all different sorts, and there's a distinctive 'new clothing' smell lingering in the air."
					+ "</p>"
					+ "<p>"
						+ "Nyan's heels clatter across the laminate flooring as she hurries to retrieve a notepad that she uses to track what's currently in stock."
						+ " Rushing back over to you, she fumbles with the little pad and almost drops it on the floor, "
						+ "[nyan.speech(A-ah! S-sorry, I don't usually interact with customers, s-so sorry if I'm a little unprofessional... A-Anyway, let me know what you'd like t-to see, and I'll show y-you what I've got in stock!)]"
					+ "</p>"
					+ "<p>"
						+ "You start to thank the nervous cat-girl, but she suddenly squeaks out and cuts you off, "
						+ "[nyan.speech(O-oh! A-and I forgot! I-I was going to do a t-trial of offering to buy old clothes from customers, s-so if you wanted to, I-I could buy some old clothing from you..."
							+ " O-oh, I interrupted you... S-sorry...)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(index);
		}
	};
	
	public static final DialogueNodeOld SHOP_CLOTHING_REPEAT = new DialogueNodeOld("Nyan's Clothing Emporium", "-", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Stepping through the open glass doors, you find 'Nyan's Clothing Emporium' to be just as busy as it always is."
						+ " Crowds of shoppers block almost every aisle, and if it wasn't for Nyan's offer to personally show you around her stockroom, you don't think you'd ever manage to get any shopping done in here."
					+ "</p>"
					+ "<p>"
						+ "Scanning around for any sign of the store's nervous owner, you suddenly hear a familiar squeak coming from behind you, "
						+ "[nyan.speech(A-ah! H-hello again!)]"
					+ "</p>"
					+ "<p>"
						+ "You turn around to see Nyan looking down at her feet as she shuffles them about on the shop's laminate flooring, "
						+ "[pc.speech(Hi Nyan, how are y-)]"
					+ "</p>"
					+ "<p>"
						+ "[nyan.speech(I-I'm fine! T-thanks for asking,)] Nyan bursts out, interrupting you in her haste to answer, "
						+ "[nyan.speech(I-I can show you the stockroom again if y-you'd like! F-follow me!)]"
					+ "</p>"
					+ "<p>"
						+ "Following Nyan into her store's stockroom once again, you wonder what to take a look at this time..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseTrade("Female Clothing", "Ask her what female clothing is available.", Main.game.getNyan()){
					@Override
					public void effects() {
						Main.game.getNyan().clearNonEquippedInventory();

						for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonFemaleClothing())
							Main.game.getNyan().addClothing(c, false);
					}
				};
				
			} else if (index == 2) {
				return new ResponseTrade("Female Underwear", "Ask her what female underwear is available.", Main.game.getNyan()){
					@Override
					public void effects() {
						Main.game.getNyan().clearNonEquippedInventory();

						for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonFemaleUnderwear())
							Main.game.getNyan().addClothing(c, false);
					}
				};
				
			} else if (index == 3) {
				return new ResponseTrade("Female Lingerie", "Ask her what female lingerie is available.", Main.game.getNyan()){
					@Override
					public void effects() {
						Main.game.getNyan().clearNonEquippedInventory();

						for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonFemaleOtherLingerie())
							Main.game.getNyan().addClothing(c, false);
					}
				};
				
			} else if (index == 4) {
				return new ResponseTrade("Female Accessories", "Ask her what female accessories are available.", Main.game.getNyan()){
					@Override
					public void effects() {
						Main.game.getNyan().clearNonEquippedInventory();

						for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonFemaleAccessories())
							Main.game.getNyan().addClothing(c, false);
					}
				};
				
			} else if (index == 5) {
				return new ResponseTrade("Male Clothing", "Ask her what male clothing is available.", Main.game.getNyan()){
					@Override
					public void effects() {
						Main.game.getNyan().clearNonEquippedInventory();

						for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonMaleClothing())
							Main.game.getNyan().addClothing(c, false);
					}
				};
				
			} else if (index == 6) {
				return new ResponseTrade("Male Underwear", "Ask her what male underwear is available.", Main.game.getNyan()){
					@Override
					public void effects() {
						Main.game.getNyan().clearNonEquippedInventory();

						for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonMaleLingerie())
							Main.game.getNyan().addClothing(c, false);
					}
				};
				
			} else if (index == 7) {
				return new ResponseTrade("Male Accessories", "Ask her what male accessories are is available.", Main.game.getNyan()){
					@Override
					public void effects() {
						Main.game.getNyan().clearNonEquippedInventory();

						for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonMaleAccessories())
							Main.game.getNyan().addClothing(c, false);
					}
				};
				
			} else if (index == 8) {
				return new ResponseTrade("Unisex Clothing", "Ask her what unisex clothing is available.", Main.game.getNyan()){
					@Override
					public void effects() {
						Main.game.getNyan().clearNonEquippedInventory();

						for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonAndrogynousClothing())
							Main.game.getNyan().addClothing(c, false);
					}
				};
				
			} else if (index == 9) {
				return new ResponseTrade("Unisex Underwear", "Ask her what unisex underwear is available.", Main.game.getNyan()){
					@Override
					public void effects() {
						Main.game.getNyan().clearNonEquippedInventory();

						for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonAndrogynousLingerie())
							Main.game.getNyan().addClothing(c, false);
					}
				};
				
			} else if (index == 10) {
				return new ResponseTrade("Unisex Accessories", "Ask her what unisex accessories are is available.", Main.game.getNyan()){
					@Override
					public void effects() {
						Main.game.getNyan().clearNonEquippedInventory();

						for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonAndrogynousAccessories())
							Main.game.getNyan().addClothing(c, false);
					}
				};
				
			} else if (index == 11) {
				return new ResponseTrade("Specials", "Ask her if she has anything special.", Main.game.getNyan()){
					@Override
					public void effects() {
						Main.game.getNyan().clearNonEquippedInventory();

						for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getSpecials())
							Main.game.getNyan().addClothing(c, false);
					}
				};
				
			} else if (index == 0) {
				return new Response("Leave", "Tell Nyan that you've got to get going.", EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Thanking Nyan for her time, you explain to her that you need to get going now."
									+ " Hurrying to put her little notepad away, she stutters, "
									+ "[nyan.speech(W-well, thanks for choosing to s-shop here! If you ever want t-to come back, just find me and I-I'll show you what's in stock back here!)]"
								+ "</p>"
								+ "<p>"
									+ "Before you can respond, Nyan turns around and runs off deeper into the stockroom."
									+ " You're pretty sure that there aren't any other exits in that direction, and that she was just unable to continue the conversation any longer."
								+ "</p>"
								+ "<p>"
									+ "You step out of the stockroom, and, after pushing your way through a crowd of customers that's loitering around the store's exit, you head back out into the shopping arcade."
								+ "</p>");
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
