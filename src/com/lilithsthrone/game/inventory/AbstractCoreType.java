package com.lilithsthrone.game.inventory;

/**
 * @since 0.1.0
 * @version 0.2.0
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

}
