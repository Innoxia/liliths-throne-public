package com.lilithsthrone.game.dialogue.places.dominion;

import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;

/**
 * @since 0.1.0
 * @version 0.1.69
 * @author Innoxia
 */
public class LilithsTower {
	public static final DialogueNodeOld OUTSIDE = new DialogueNodeOld("Lilith's Tower", "Lilith's tower.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "A giant spire, built out of dark black stone, dominates Dominion's skyline. Finding yourself standing near the base, you notice an increase in the amount of Enforcers patrolling this area."
					+ "</p>"
					+ "<p>"
					+ "An elaborate golden archway links the tower's grounds to the street that you're standing on. A single word inscribed into the metalwork reads 'Lilith', leaving you with little doubt as to the identity of the demon who lives here."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Approach", "Approach the archway and see if you can enter the tower's grounds.", LILITHS_DISTRICT_APPROACH);

			} else {
				return null;
			}
		}
	};
	public static final DialogueNodeOld LILITHS_DISTRICT_APPROACH = new DialogueNodeOld("Lilith's Tower Entrance", "Archway", false, true) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ "You walk up to the archway, but as you approach, a pair of muscular horse-boy Enforcers seem to appear from out of nowhere, blocking your way."
					+ " Even if you felt confident enough to beat them, you know that you'd stand no chance in combat against the sheer number of Enforcers that would quickly arrive as backup."
					+ "</p>"
					+ "<p>"
					+ "<b>Content for Lilith's Tower will be added much later, sometime around version 0.8.0.</b>"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
}
