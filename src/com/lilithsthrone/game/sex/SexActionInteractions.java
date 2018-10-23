package com.lilithsthrone.game.sex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @since 0.2.0
 * @version 0.2.11
 * @author Innoxia
 */
public class SexActionInteractions {

	private Map<SexAreaInterface, List<SexAreaInterface>> interactions;
	
	private List<OrgasmCumTarget> availableCumTargets;
	
	public SexActionInteractions(
			Map<SexAreaInterface, List<SexAreaInterface>> interactions,
			List<OrgasmCumTarget> availableCumTargets) {
		
		if(interactions==null) {
			this.interactions = new HashMap<>();
		} else {
			this.interactions = interactions;
		}

		if(availableCumTargets==null) {
			this.availableCumTargets = new ArrayList<>();
		} else {
			this.availableCumTargets = availableCumTargets;
		}
	}
	
	/**
	 * @return interactions map. The keys correspond to the performing characters area, while the values are lists of areas (owned by the targeted character) that can be targeted by the key.
	 */
	public Map<SexAreaInterface, List<SexAreaInterface>> getInteractions() {
		return interactions;
	}

	public void setInteractions(Map<SexAreaInterface, List<SexAreaInterface>> interactions) {
		this.interactions = interactions;
	}

	public List<OrgasmCumTarget> getAvailableCumTargets() {
		return availableCumTargets;
	}

	public void setAvailableCumTargets(List<OrgasmCumTarget> availableCumTargets) {
		this.availableCumTargets = availableCumTargets;
	}

}
