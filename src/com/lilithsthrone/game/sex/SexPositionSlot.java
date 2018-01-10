package com.lilithsthrone.game.sex;

import java.util.List;

import com.lilithsthrone.game.sex.sexActions.SexActionPresets;

/**
 * @since 0.1.97
 * @version 0.1.97
 * @author Innoxia
 */
public enum SexPositionSlot {
	
	/**The partner who's on all fours, being fucked doggy-style.*/
	DOGGY_ON_ALL_FOURS("All fours",
			SexActionPresets.playerDoggyOnAllFours,
			SexActionPresets.partnerDoggyOnAllFours),
	
	/**The partner who's behind the doggy-style target. They are kneeling, and can perform vaginal or anal penetration.*/
	DOGGY_BEHIND("Behind",
			SexActionPresets.playerDoggyBehind,
			SexActionPresets.partnerDoggyBehind),

	/**The partner who's behind the doggy-style target. They are also on all fours, and can perform oral on the doggy-style target.*/
	DOGGY_BEHIND_ORAL("Behind (oral)",
			SexActionPresets.playerDoggyBehindOral,
			SexActionPresets.partnerDoggyBehindOral),

	/**The partner who's in front of the doggy-style target. They can receive oral from the doggy-style target.*/
	DOGGY_INFRONT("Infront",
			SexActionPresets.playerDoggyInfront,
			SexActionPresets.partnerDoggyInfront),

	/**The partner who's in front of the doggy-style target. They are turned around, and can receive anilingus from the doggy-style target.*/
	DOGGY_INFRONT_ANAL("Infront (anal)",
			SexActionPresets.playerDoggyInfrontAnal,
			SexActionPresets.partnerDoggyInfrontAnal);
	
	private String name;
	/**A list of classes that contain relevant SexActions for this slot.*/
	private List<Class<?>> playerSexActionContainingClasses;
	private List<Class<?>> partnerSexActionContainingClasses;
	
	private SexPositionSlot(String name,
			List<Class<?>> playerSexActionContainingClasses,
			List<Class<?>> partnerSexActionContainingClasses) {
		this.name = name;
		this.playerSexActionContainingClasses = playerSexActionContainingClasses;
		this.partnerSexActionContainingClasses = partnerSexActionContainingClasses;
	}

	public String getName() {
		return name;
	}

	public List<Class<?>> getPlayerSexActionContainingClasses() {
		return playerSexActionContainingClasses;
	}

	public List<Class<?>> getPartnerSexActionContainingClasses() {
		return partnerSexActionContainingClasses;
	}
}
