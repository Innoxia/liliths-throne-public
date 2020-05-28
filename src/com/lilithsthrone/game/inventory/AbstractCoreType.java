package com.lilithsthrone.game.inventory;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 0.1.0
 * @version 0.3.7.7
 * @author Innoxia
 */
public class AbstractCoreType {
	
	@Override
	public boolean equals(Object o) {
		return true;
	}
	
	@Override
	public int hashCode() {
		return 1;
	}

	public Rarity getRarity() {
		return Rarity.COMMON;
	}

	/**
	 * @return A List of other AbstractItemTypes which should be added to the player's Encyclopedia when this one is discovered. Used for unique items in situations where acquiring one would make it impossible to ever see other ones.
	 */
	public List<AbstractCoreType> getAdditionalDiscoveryTypes() {
		return new ArrayList<>();
	}
}
