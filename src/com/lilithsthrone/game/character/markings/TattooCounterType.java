package com.lilithsthrone.game.character.markings;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.SexAreaOrifice;

/**
 * @since 0.2.6
 * @version 0.2.9
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
			return bearer.getTotalSexAsSubCount();
		}
	},

	SEX_DOM("dom sex", "Keeps a count of how many times the bearer has had dominant sex.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalSexAsDomCount();
		}
	},

	CUM_GIVEN("total creampies given", "Keeps a count of how many times the bearer has cummed inside someone.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCount(true, false);
		}
	},

	CUM_GIVEN_PUSSY("pussy creampies given", "Keeps a count of how many times the bearer has cummed into someone's pussy.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInOrifice(SexAreaOrifice.VAGINA, true, false);
		}
	},

	CUM_GIVEN_ANUS("anal creampies given", "Keeps a count of how many times the bearer has cummed into someone's ass.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInOrifice(SexAreaOrifice.ANUS, true, false);
		}
	},

	CUM_GIVEN_ORAL("oral loads given", "Keeps a count of how many times the bearer has cummed down someone's throat.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInOrifice(SexAreaOrifice.MOUTH, true, false);
		}
	},
	
	CUM_TAKEN("total creampies received", "Keeps a count of how many times the bearer has taken a load of cum in their orifices.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCount(false, true);
		}
	},

	CUM_TAKEN_PUSSY("pussy creampies received", "Keeps a count of how many times the bearer has taken a load of cum in their pussy.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInOrifice(SexAreaOrifice.VAGINA, false, true);
		}
	},

	CUM_TAKEN_ANUS("anal creampies received", "Keeps a count of how many times the bearer has taken a load of cum in their ass.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInOrifice(SexAreaOrifice.ANUS, false, true);
		}
	},

	CUM_TAKEN_ORAL("loads swallowed", "Keeps a count of how many times the bearer has swallowed a load of cum.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInOrifice(SexAreaOrifice.MOUTH, false, true);
		}
	},

	CURRENT_PREGNANCY("litter size", "Counts how many children the bearer is currently pregnant with.") {
		@Override
		public int getCount(GameCharacter bearer) {
			if(bearer.getPregnantLitter()==null) {
				return 0;
			}
			return bearer.getPregnantLitter().getTotalLitterCount();
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
