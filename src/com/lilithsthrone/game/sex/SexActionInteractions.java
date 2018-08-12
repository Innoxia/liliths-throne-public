package com.lilithsthrone.game.sex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @since 0.2.0
 * @version 0.2.8
 * @author Innoxia
 */
public class SexActionInteractions {

	private Map<SexAreaInterface, List<SexAreaInterface>> interactions;
	
	public SexActionInteractions(Map<SexAreaInterface, List<SexAreaInterface>> interactions) {
		
		if(interactions==null) {
			this.interactions = new HashMap<>();
		} else {
			this.interactions = interactions;
		}
	}
	
	/**
	 * @return interactions map. The keys correspond to the performing characters area, while the values are lists of areas (owned by the targeted character) that can be targeted by the key.
	 */
	public Map<SexAreaInterface, List<SexAreaInterface>> getInteractions() {
		return interactions;
	}

}
