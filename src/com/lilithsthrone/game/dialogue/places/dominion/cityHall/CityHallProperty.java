package com.lilithsthrone.game.dialogue.places.dominion.cityHall;

import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.3.2
 * @version 0.3.2
 * @author Innoxia
 */
public class CityHallProperty {
	
	public static final DialogueNode CITY_HALL_PROPERTY_ENTRANCE = new DialogueNode("Bureau of Property and Commerce", "-", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/property", "CITY_HALL_PROPERTY_ENTRANCE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
}
