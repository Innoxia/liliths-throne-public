package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.66
 * @version 0.4.7.8
 * @author Innoxia
 */
public class PixsPlayground {
	
	public static final DialogueNode GYM_EXTERIOR = new DialogueNode("Pix's Playground (Exterior)", "", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "GYM_EXTERIOR");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ShoppingArcadeDialogue.getCoreResponseTab(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					return new Response("Enter",
							"Enter the gym.",
							DialogueManager.getDialogueFromId("innoxia_places_dominion_shopping_arcade_gym_exit_initial_entry")) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.getWorldTypeFromId("innoxia_dominion_shopping_arcade_gym"), PlaceType.getPlaceTypeFromId("innoxia_dominion_shopping_arcade_gym_exit"));
						}
					};
				}
			}
			return ShoppingArcadeDialogue.getFastTravelResponses(responseTab, index);
		}
	};

}
