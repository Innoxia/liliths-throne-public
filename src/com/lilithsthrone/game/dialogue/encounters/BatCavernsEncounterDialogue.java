package com.lilithsthrone.game.dialogue.encounters;

import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.main.Main;

/**
 * @since 0.2.4
 * @version 0.2.4
 * @author Innoxia
 */
public class BatCavernsEncounterDialogue {

	public static final DialogueNodeOld FIND_ITEM = new DialogueNodeOld("Discarded Item", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "TODO Find a discarded item, with a pool of slime next to it."//TODO
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "<b>"
						+ Encounter.getRandomItem().getDisplayName(true)
						+ "</b>"
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Take", "Add the " + Encounter.getRandomItem().getName() + " to your inventory.", Main.game.getDefaultDialogueNoEncounter()){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addItem(Encounter.getRandomItem(), true, true));
					}
				};
				
			} else if (index == 2) {
				return new Response("Leave", "Leave the " + Encounter.getRandomItem().getName() + " on the floor.", Main.game.getDefaultDialogueNoEncounter());
				
			} else {
				return null;
			}
		}
	};
}
