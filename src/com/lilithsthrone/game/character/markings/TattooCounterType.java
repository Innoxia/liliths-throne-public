package com.lilithsthrone.game.character.markings;

import com.lilithsthrone.game.character.GameCharacter;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public enum TattooCounterType {
	
	NONE("none", "Do not add a tracker to this tattoo.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return 0;
		}
	},
	
	SEX_SUB("sub sex", "Keeps a count of how many times the bearer has had submissive sex.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getSexAsSubCount();
		}
	},

	SEX_DOM("dom sex", "Keeps a count of how many times the bearer has had dominant sex.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getSexAsDomCount();
		}
	},

	CUM_GIVEN("cum given", "Keeps a count of how many times the bearer has cummed inside someone.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCount(true, false);
		}
	},

	CUM_TAKEN("cum taken", "Keeps a count of how many times the bearer has taken a load of cum in their orifices.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCount(false, true);
		}
	},
	
	PREGNANCY("pregnancy", "Keeps a count of the bearer's completed pregnancies.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getLittersBirthed().size();
		}
	},
	
	IMPREGNATIONS("impregnation", "Keeps a count of the number of times the bearer has impregnated someone.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getLittersFathered().size();
		}
	};
	
	private String name;
	private String description;
	
	private TattooCounterType(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public abstract int getCount(GameCharacter bearer);

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}
