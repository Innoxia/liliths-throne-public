package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import com.lilithsthrone.game.character.npc.dominion.Akari;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.99
 * @version 0.1.99
 * @author Kumiko, Innoxia
 */
public class BargainStore {

	public static final DialogueNode EXTERIOR = new DialogueNode("Bargain Store (Exterior)", "-", false) {

		@Override
		public String getAuthor() {
			return "Nuke-Bait";
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
					return new Response("Enter", "Step inside the Bargain Store.", ENTRY){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.akariIntroduced);
						}
					};
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
				return new Response("Talk", "Talk to Akari about her shop and her wares...", TALK_AKARI);
			
			}else {
				
				if (index == 1) {
					return new ResponseTrade("Trade", "Wander around the shop and see what items there are for sale...", Main.game.getNpc(Akari.class));
					
					
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
