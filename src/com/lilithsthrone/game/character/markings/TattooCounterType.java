package com.lilithsthrone.game.character.markings;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.2.6
 * @version 0.2.10
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
			return bearer.getTotalCumCountInArea(SexAreaOrifice.VAGINA, true, false);
		}
	},

	CUM_GIVEN_ANUS("anal creampies given", "Keeps a count of how many times the bearer has cummed into someone's ass.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.ANUS, true, false);
		}
	},

	CUM_GIVEN_ORAL("oral loads given", "Keeps a count of how many times the bearer has cummed down someone's throat.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.MOUTH, true, false);
		}
	},

	CUM_GIVEN_FEET("footjob climaxes received", "Keeps a count of how many times the bearer has cummed onto their partner's feet.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaPenetration.FOOT, true, false);
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
			return bearer.getTotalCumCountInArea(SexAreaOrifice.VAGINA, false, true);
		}
	},

	CUM_TAKEN_ANUS("anal creampies received", "Keeps a count of how many times the bearer has taken a load of cum in their ass.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.ANUS, false, true);
		}
	},

	CUM_TAKEN_ORAL("loads swallowed", "Keeps a count of how many times the bearer has swallowed a load of cum.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.MOUTH, false, true);
		}
	},

	CUM_TAKEN_FEET("footjob climaxes given", "Keeps a count of how many times the bearer has had their partner cum over their feet.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaPenetration.FOOT, false, true); 
		}
	},
	

	VIRGINITIES_TAKEN_TOTAL("total deflowerments", "Keeps a count of how many virginities of all types (vaginal, anal, oral, nipple, urethral) the bearer has taken.") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			
			for(GameCharacter character : Main.game.getAllNPCs()) {
				if(!character.equals(bearer)) {
					for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
						if(orifice.isInternalOrifice()) {
							for(SexAreaPenetration pen : SexAreaPenetration.values()) {
								if(pen.isTakesVirginity()
										&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, orifice, pen))!=null
										&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, orifice, pen)).getKey().equals(bearer.getId())) {
									count++;
									break;
								}
							}
						}
					}
				}
			}
			
			GameCharacter character = Main.game.getPlayer();
			if(!character.equals(bearer)) {
				for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
					if(orifice.isInternalOrifice()) {
						for(SexAreaPenetration pen : SexAreaPenetration.values()) {
							if(pen.isTakesVirginity()
									&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, orifice, pen))!=null
									&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, orifice, pen)).getKey().equals(bearer.getId())) {
								count++;
								break;
							}
						}
					}
				}
			}
			
			return count; 
		}
	},
	
	VIRGINITIES_TAKEN_VAGINAL("vaginal deflowerments", "Keeps a count of how many vaginal virginities the bearer has taken.") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			
			for(GameCharacter character : Main.game.getAllNPCs()) {
				if(!character.equals(bearer)) {
					for(SexAreaPenetration pen : SexAreaPenetration.values()) {
						if(pen.isTakesVirginity()
								&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, pen))!=null
								&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, pen)).getKey().equals(bearer.getId())) {
							count++;
							break;
						}
					}
				}
			}
			
			GameCharacter character = Main.game.getPlayer();
			if(!character.equals(bearer)) {
				for(SexAreaPenetration pen : SexAreaPenetration.values()) {
					if(pen.isTakesVirginity() && character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, pen)).getKey().equals(bearer.getId())) {
						count++;
						break;
					}
				}
			}
			
			return count; 
		}
	},
	
	VIRGINITIES_TAKEN_ANAL("anal deflowerments", "Keeps a count of how many anal virginities the bearer has taken.") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			
			for(GameCharacter character : Main.game.getAllNPCs()) {
				if(!character.equals(bearer)) {
					for(SexAreaPenetration pen : SexAreaPenetration.values()) {
						if(pen.isTakesVirginity()
								&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, pen))!=null
								&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, pen)).getKey().equals(bearer.getId())) {
							count++;
							break;
						}
					}
				}
			}
			
			GameCharacter character = Main.game.getPlayer();
			if(!character.equals(bearer)) {
				for(SexAreaPenetration pen : SexAreaPenetration.values()) {
					if(pen.isTakesVirginity() && character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, pen)).getKey().equals(bearer.getId())) {
						count++;
						break;
					}
				}
			}
			
			return count; 
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
