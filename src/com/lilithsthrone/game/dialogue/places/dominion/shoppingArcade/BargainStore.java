package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import com.lilithsthrone.game.character.npc.dominion.Akari;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 3.3.0
 * @version 0.3.3
 * @author NukeBait, Innoxia
 */
public class BargainStore {

	public static final DialogueNode EXTERIOR = new DialogueNode("Bargain Store (Exterior)", "-", false) {

		@Override
		public String getAuthor() {
			return "NukeBait";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/bargainStore", "EXTERIOR");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.akariIntroduced)) {
					return new Response("Enter", "Step inside Akari's Bargain Store.", ENTRY);
					
				} else {
					return new Response("Enter", "Step inside the Bargain Store.", ENTRY);
				}
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Arcade Entrance", "Fast travel to the entrance to the arcade."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), PlaceType.SHOPPING_ARCADE_ENTRANCE, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENTRY = new DialogueNode("Bargain Store", "-", true) {

		@Override
		public String getAuthor() {
			return "NukeBait";
		}
		
		@Override
		public String getContent() {

			if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.akariIntroduced)) {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/bargainStore", "ENTRY");

			} else {

				UtilText.nodeContentSB.setLength(0);
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/bargainStore", "ENTRY_REPEAT");

			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {				
			if(index == 2) {
				return new Response("Talk", "Talk to Akari about her shop and her wares...", TALK_AKARI){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.akariIntroduced);
					}
				};

			}else {
				
				if (index == 1) {
					return new Response("Browse Shelves", "Wander around the shop and see what items there are for sale...", TRADE_AKARI);
					
					
				} else if (index == 0) {
					return new Response("Leave", "Head back out to the Shopping Arcade.", EXTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/bargainStore", "EXIT"));
						}
					};
	
				} else {
					return null;
				}
			}
		}
	};

	public static final DialogueNode TRADE_AKARI = new DialogueNode("Bargain Store", "-", true) {

		@Override
		public String getAuthor() {
			return "NukeBait";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/bargainStore", "TRADE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (((Akari) Main.game.getNpc(Akari.class)).getWeaponsForSale().isEmpty()) {
					return new Response("Weapons", "Akari doesn't have any weapons in stock at the moment.", null);
				}

				return new ResponseTrade("Weapons", "Walk over to the counter and see what weapons Vicky has in stock.", Main.game.getNpc(Akari.class)) {
					@Override
					public void effects() {

						Main.game.getNpc(Akari.class).clearNonEquippedInventory();

						for (AbstractWeapon weapon : ((Akari) Main.game.getNpc(Akari.class)).getWeaponsForSale()) {
							if (Main.game.getNpc(Akari.class).isInventoryFull()) {
								break;
							}
							Main.game.getNpc(Akari.class).addWeapon(weapon, false);
						}
					}
				};

			} else if (index == 2) {
				if (((Akari) Main.game.getNpc(Akari.class)).getItemsForSale().isEmpty()) {
					return new Response("Items", "Akari doesn't have any items in stock at the moment.", null);
				}

				return new ResponseTrade("Items", "Walk over to the counter and see what items Akari has collected", Main.game.getNpc(Akari.class)) {
					@Override
					public void effects() {

						Main.game.getNpc(Akari.class).clearNonEquippedInventory();

						for (AbstractItem item : ((Akari) Main.game.getNpc(Akari.class)).getItemsForSale()) {
							if (Main.game.getNpc(Akari.class).isInventoryFull()) {
								break;
							}
							Main.game.getNpc(Akari.class).addItem(item, false);
						}
					}
				};

			} else if (index == 3) {
				if (((Akari) Main.game.getNpc(Akari.class)).getClothingForSale().isEmpty()) {
					return new Response("Clothing", "Akari doesn't have any clothing in stock at the moment.", null);
				}
				return new ResponseTrade("Clothing", "Walk over to the counter and see what clothing Vicky has in stock.", Main.game.getNpc(Akari.class)) {
					@Override
					public void effects() {

						Main.game.getNpc(Akari.class).clearNonEquippedInventory();

						for (AbstractClothing clothing : ((Akari) Main.game.getNpc(Akari.class)).getClothingForSale()) {
							if (Main.game.getNpc(Akari.class).isInventoryFull()) {
								break;
							}
							Main.game.getNpc(Akari.class).addClothing(clothing, false);
						}
					}
				};

			} else if (index == 0) {
				return new Response("Nevermind", "Leave Arcane Arts and head back out into the arcade.", ENTRY) {

				};

			} else {
				return null;
			}


		}
	};

	
	public static final DialogueNode LOOK_AROUND = new DialogueNode("Bargain Store", "-", true, true) {

		@Override
		public String getAuthor() {
			return "NukeBait";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/bargainStore", "EXPLORE_SHELVES");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseTrade("Trade", "Wander around the shop and see what items there are for sale...", Main.game.getNpc(Akari.class));
			}
			
			else if(index == 2) {
				return new Response("Talk", "Actually, you wanted to talk a little about her...", TALK_AKARI) {
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Akari.class).incrementAffection(Main.game.getPlayer(), 4f));
					}
				};
			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNode TALK_AKARI = new DialogueNode("Bargain Store", "-", true, true) {

		@Override
		public String getAuthor() {
			return "NukeBait";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/bargainStore", "TALK_AKARI");
		}
		
		@Override
		public Response getResponse(int responseTab, int index ) {
			return ENTRY.getResponse(responseTab, index);
		}
	};
	
}
