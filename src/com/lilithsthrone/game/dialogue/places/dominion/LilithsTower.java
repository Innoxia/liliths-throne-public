package com.lilithsthrone.game.dialogue.places.dominion;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.0
 * @version 0.2.3
 * @author Innoxia
 */
public class LilithsTower {
	public static final DialogueNode OUTSIDE = new DialogueNode("Lilith's Tower", "Lilith's tower.", false) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
						+ "A giant spire, built out of dark black stone, dominates Dominion's skyline. Finding yourself standing near the base, you notice an increase in the amount of Enforcers patrolling this area."
					+ "</p>"
					+ "<p>"
						+ "An elaborate golden archway links the tower's grounds to the street that you're standing on. A single word inscribed into the metalwork reads 'Lilith', leaving you with little doubt as to the identity of the demon who lives here."
					+ "</p>");
			
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "<b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Arcane Storm:</b><br/>"
							+ "The arcane storm that's raging overhead has brought out a heavy presence of demon Enforcers in this area."
							+ " Unaffected by the arousing power of the storm's thunder, these elite Enforcers keep a close watch on you as you pass through the all-but-deserted area."
							+ " There's no way anyone would be able to assault you while under their watchful gaze, allowing you continue on your way in peace..."
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
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
	public static final DialogueNode LILITHS_DISTRICT_APPROACH = new DialogueNode("Lilith's Tower Entrance", "Archway", false, true) {
		/**
		 * 
		 */

		@Override
		public String getContent() {
			return "<p>"
						+ "You walk up to the archway, but as you approach, a pair of elite demon Enforcers seem to appear from out of nowhere, blocking your way."
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
