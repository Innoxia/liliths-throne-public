package com.lilithsthrone.game.dialogue.encounters;

import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;

/**
 * @since 0.2.1
 * @version 0.2.5
 * @author Innoxia
 */
public class SubmissionEncounterDialogue {

	public static final DialogueNode FIND_ITEM = new DialogueNode("Rubbish Pile", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "FIND_ITEM")
					+ "<p style='text-align:center;'>"
						+ "<b>"
						+ Encounter.getRandomItem().getDisplayName(true)
						+ "</b>"
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Take", "Add the " + Encounter.getRandomItem().getName() + " to your inventory.", Main.game.getDefaultDialogue(false)){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addItem(Encounter.getRandomItem(), true, true));
					}
				};
				
			} else if (index == 2) {
				return new Response("Leave", "Leave the " + Encounter.getRandomItem().getName() + " on the floor.", Main.game.getDefaultDialogue(false));
				
			} else {
				return null;
			}
		}
	};
}
