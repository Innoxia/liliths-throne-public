package com.lilithsthrone.game.sex;

import java.util.List;

/**
 * @since 0.2.0
 * @version 0.2.0
 * @author Innoxia
 */
public class SexActionPresetPair {
	
	private List<Class<?>> playerSexActionContainingClasses;
	private List<Class<?>> partnerSexActionContainingClasses;
	
	public SexActionPresetPair(List<Class<?>> playerSexActionContainingClasses, List<Class<?>> partnerSexActionContainingClasses) {
		super();
		this.playerSexActionContainingClasses = playerSexActionContainingClasses;
		this.partnerSexActionContainingClasses = partnerSexActionContainingClasses;
	}

	public List<Class<?>> getPlayerSexActionContainingClasses() {
		return playerSexActionContainingClasses;
	}

	public List<Class<?>> getPartnerSexActionContainingClasses() {
		return partnerSexActionContainingClasses;
	}
}
