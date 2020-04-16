package com.lilithsthrone.game.dialogue.places.dominion.helenaHotel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.7
 * @version 0.3.7
 * @author Innoxia
 */
public enum HelenaConversationTopic {
	
	SLAVES,
	
	HARPY_NESTS,
	
	RACES,
	
	HARPIES,
	
	BUSINESS;

	/**
	 * @return A random conversation topic, not repeating a previously-seen topic unless all topics have been seen.
	 */
	public static HelenaConversationTopic getRandomTopic() {
		List<HelenaConversationTopic> topics = new ArrayList<>();
		
		Collections.addAll(topics, values());
		
		topics.removeIf((topic) -> Main.game.getDialogueFlags().hasHelenaConversationTopic(topic));
		
		if(topics.isEmpty()) {
			Collections.addAll(topics, values());
		}
		
		return Util.randomItemFrom(topics);
	}
	
}
