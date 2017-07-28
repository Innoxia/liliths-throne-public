package com.base.game.dialogue.places.dominion;

import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.responses.Response;

/**
 * @since 0.1.0
 * @version 0.1.69
 * @author Innoxia
 */
public class NightlifeDistrict {

	/*
	 * OLD IDEAS
	 * 
	 * All actions need a NO_PARTNER and WITH_PARTNER variant
	 * 
	 * Club NO_PARTNER Dance (random person hits on you) Have a Drink (random
	 * person hits on you) Hit on someone (get a choice) WITH_PARTNER Dance
	 * (partner feels you up, kisses you) Sex: your place, toilets, dancefloor
	 * gangbang Have a Drink (if partner girl, she expects you to buy her a
	 * drink, if partner is guy, he buys you a drink) Get rid of partner
	 */

	public static final DialogueNodeOld OUTSIDE = new DialogueNodeOld("Nightlife", "Nightlife", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "The Nightlife district will be an area where you can go to pick up random guys and girls to have sexy encounters with. This content is due to be added sometime in version 0.2.0."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Enter", "(This will be added soon!).", null);

			} else {
				return null;
			}
		}
	};
}
