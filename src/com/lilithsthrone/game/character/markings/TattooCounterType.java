package com.lilithsthrone.game.character.markings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.pregnancy.Litter;
import com.lilithsthrone.game.character.pregnancy.PregnancyPossibility;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;

/**
 * @since 0.2.6
 * @version 0.3.7.2
 * @author Innoxia
 */
public enum TattooCounterType {
	
	NONE("none", "Do not add a tracker to this tattoo.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return 0;
		}
		@Override
		public boolean isRetroactiveApplicationAvailable() {
			return false;
		}
	},

	
	VALUE_AS_SLAVE("value as slave", "Displays how much the bearer is worth as a slave.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getValueAsSlave(false); 
		}
		@Override
		public boolean isRetroactiveApplicationAvailable() {
			return false;
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

	UNIQUE_SEX_PARTNERS("unique sex partners", "Keeps a count of how many different people the bearer has had sex with.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getUniqueSexPartnerCount();
		}
	},

	PUSSY_FUCKED("pussy penetrated", "Keeps a count of how many times the bearer's pussy has been penetrated.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalTimesOrificePenetrated(SexAreaOrifice.VAGINA);
		}
	},

	ANUS_FUCKED("anus penetrated", "Keeps a count of how many times the bearer's asshole has been penetrated.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalTimesOrificePenetrated(SexAreaOrifice.ANUS);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isAnalContentEnabled();
		}
	},
	
	ORAL_FUCKED("mouth penetrated", "Keeps a count of how many times the bearer's mouth has been penetrated.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalTimesOrificePenetrated(SexAreaOrifice.MOUTH);
		}
	},
	
	NIPPLES_FUCKED("nipples penetrated", "Keeps a count of how many times the bearer's nipples have been penetrated.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalTimesOrificePenetrated(SexAreaOrifice.NIPPLE);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isNipplePenEnabled();
		}
	},
	
	NIPPLES_CROTCH_FUCKED("crotch-nipples penetrated", "Keeps a count of how many times the bearer's crotch-nipples have been penetrated.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalTimesOrificePenetrated(SexAreaOrifice.NIPPLE_CROTCH);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isNipplePenEnabled() && Main.game.isUdderContentEnabled();
		}
	},

	PENIS_PENETRATIONS("penis penetrations", "Keeps a count of how many times the bearer's penis has penetrated orifices.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalTimesPenetratedOrifices(SexAreaPenetration.PENIS);
		}
	},

	TAIL_PENETRATIONS("tail penetrations", "Keeps a count of how many times the bearer's tail has penetrated orifices.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalTimesPenetratedOrifices(SexAreaPenetration.TAIL);
		}
	},

	TENTACLE_PENETRATIONS("tentacle penetrations", "Keeps a count of how many times the bearer's tentacles have penetrated orifices.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalTimesPenetratedOrifices(SexAreaPenetration.TENTACLE);
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
		@Override
		public boolean isAvailable() {
			return Main.game.isAnalContentEnabled();
		}
	},

	CUM_GIVEN_ORAL("oral loads given", "Keeps a count of how many times the bearer has cummed down someone's throat.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.MOUTH, true, false);
		}
	},

	CUM_GIVEN_NIPPLES("nipple creampies given", "Keeps a count of how many times the bearer has cummed into someone's nipples.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.NIPPLE, true, false);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isNipplePenEnabled();
		}
	},

	CUM_GIVEN_NIPPLES_CROTCH("crotch-nipple creampies given", "Keeps a count of how many times the bearer has cummed into someone's crotch-nipple.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.NIPPLE_CROTCH, true, false);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isNipplePenEnabled() && Main.game.isUdderContentEnabled();
		}
	},

	CUM_GIVEN_FEET("footjob climaxes received", "Keeps a count of how many times the bearer has cummed onto their partner's feet.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaPenetration.FOOT, true, false);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isFootContentEnabled();
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
		@Override
		public boolean isAvailable() {
			return Main.game.isAnalContentEnabled();
		}
	},

	CUM_TAKEN_ORAL("loads swallowed", "Keeps a count of how many times the bearer has swallowed a load of cum.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.MOUTH, false, true);
		}
	},

	CUM_TAKEN_NIPPLES("nipple creampies received", "Keeps a count of how many times the bearer has taken a load of cum in their nipples.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.NIPPLE, false, true);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isNipplePenEnabled();
		}
	},

	CUM_TAKEN_NIPPLES_CROTCH("crotch-nipple creampies received", "Keeps a count of how many times the bearer has taken a load of cum in their crotch-nipples.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.NIPPLE_CROTCH, false, true);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isNipplePenEnabled() && Main.game.isUdderContentEnabled();
		}
	},

	CUM_TAKEN_FEET("footjob climaxes given", "Keeps a count of how many times the bearer has had their partner cum over their feet.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaPenetration.FOOT, false, true); 
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isFootContentEnabled();
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
					if(pen.isTakesVirginity()
							&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, pen))!=null
							&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, pen)).getKey().equals(bearer.getId())) {
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
					if(pen.isTakesVirginity()
							&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, pen))!=null
							&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, pen)).getKey().equals(bearer.getId())) {
						count++;
						break;
					}
				}
			}
			
			return count; 
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isAnalContentEnabled();
		}
	},
	
	VIRGINITIES_TAKEN_ORAL("oral deflowerments", "Keeps a count of how many oral virginities the bearer has taken.") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			
			for(GameCharacter character : Main.game.getAllNPCs()) {
				if(!character.equals(bearer)) {
					for(SexAreaPenetration pen : SexAreaPenetration.values()) {
						if(pen.isTakesVirginity()
								&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, pen))!=null
								&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, pen)).getKey().equals(bearer.getId())) {
							count++;
							break;
						}
					}
				}
			}
			
			GameCharacter character = Main.game.getPlayer();
			if(!character.equals(bearer)) {
				for(SexAreaPenetration pen : SexAreaPenetration.values()) {
					if(pen.isTakesVirginity()
							&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, pen))!=null
							&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, pen)).getKey().equals(bearer.getId())) {
						count++;
						break;
					}
				}
			}
			
			return count; 
		}
	},
	
	VIRGINITIES_TAKEN_PENIS("penile deflowerments", "Keeps a count of how many penile virginities the bearer has taken.") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			
			for(GameCharacter character : Main.game.getAllNPCs()) {
				if(!character.equals(bearer)) {
					for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
						if(orifice.isInternalOrifice()
								&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, orifice))!=null
								&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, orifice)).getKey().equals(bearer.getId())) {
							count++;
							break;
						}
					}
				}
			}
			
			GameCharacter character = Main.game.getPlayer();
			if(!character.equals(bearer)) {
				for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
					if(orifice.isInternalOrifice()
							&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, orifice))!=null
							&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, orifice)).getKey().equals(bearer.getId())) {
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
		@Override
		public boolean isRetroactiveApplicationAvailable() {
			return false;
		}
	},

	OFFSPRING_BIRTHED("offspring birthed", "Counts how many children the bearer has given birth to.") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			for(Litter litter : bearer.getLittersBirthed()) {
				count+=litter.getTotalLitterCount();
			}
			return count;
		}
	},

	OFFSPRING_FATHERED("offspring fathered", "Counts how many children the bearer has fathered.") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			for(Litter litter : bearer.getLittersFathered()) {
				count+=litter.getTotalLitterCount();
			}
			return count;
		}
	},
	
	PREGNANCY("pregnancy", "Keeps a count of how many times the bearer has been impregnated.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getLittersBirthed().size() + (bearer.isPregnant()?1:0);
		}
	},

	PREGNANCY_PARTNERS("pregnancy partners", "Keeps a count of the number of unique partners with whom the bearer has completed a pregnancy with.") {
		@Override
		public int getCount(GameCharacter bearer) {
			Set<String> partners = new HashSet<>();
			for(Litter litter : bearer.getLittersBirthed()) {
				partners.add(litter.getFatherId());
			}
			return partners.size();
		}
	},
	
	IMPREGNATIONS("impregnation", "Keeps a count of the number of times the bearer has impregnated someone.") {
		@Override
		public int getCount(GameCharacter bearer) {
			int potentials = 0;
			for(PregnancyPossibility pp : bearer.getPotentialPartnersAsFather()) {
				if(pp.getMother()!=null && pp.getMother().isPregnant() && Objects.equals(pp.getFather(), bearer)) {
					potentials++;
				}
			}
			return potentials + bearer.getLittersFathered().size();
		}
	},

	IMPREGNATION_PARTNERS("impregnation partners", "Keeps a count of the number of unique partners whom the bearer has impregnated.") {
		@Override
		public int getCount(GameCharacter bearer) {
			Set<String> potentials = new HashSet<>();
			for(PregnancyPossibility pp : bearer.getPotentialPartnersAsFather()) {
				if(pp.getMother()!=null && pp.getMother().isPregnant() && Objects.equals(pp.getFather(), bearer)) {
					potentials.add(pp.getMotherId());
				}
			}
			Set<String> partners = new HashSet<>();
			for(Litter litter : bearer.getLittersFathered()) {
				partners.add(litter.getMotherId());
			}
			return potentials.size() + partners.size();
		}
	},

	EGGS_IMPLANTED("eggs implanted", "Keeps a count of how many eggs the bearer has implanted in others.") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			for(Litter litter : bearer.getLittersImplanted()) {
				count+=litter.getTotalLitterCount();
			}
			return count;
		}
	},

	EGGS_INCUBATED("eggs incubated", "Keeps a count of how many implanted eggs the bearer has fully incubated and laid.") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			for(Litter litter : bearer.getLittersIncubated()) {
				count+=litter.getTotalLitterCount();
			}
			return count;
		}
	},

	CUM_IN_VAGINA("cum in womb", "Displays how much cum is currently in the bearer's womb (in "+(Main.getProperties().hasValue(PropertyValue.metricFluids)?"mL":"oz")+").") {
		@Override
		public int getCount(GameCharacter bearer) {
			if(Main.getProperties().hasValue(PropertyValue.metricFluids)) {
				return Math.round(bearer.getTotalFluidInArea(SexAreaOrifice.VAGINA));
			} else {
				return Math.round(Units.mlToOz(bearer.getTotalFluidInArea(SexAreaOrifice.VAGINA)));
			}
		}
		@Override
		public boolean isRetroactiveApplicationAvailable() {
			return false;
		}
	},
	
	CUM_IN_ASS("cum in ass", "Displays how much cum is currently in the bearer's ass (in "+(Main.getProperties().hasValue(PropertyValue.metricFluids)?"mL":"oz")+").") {
		@Override
		public int getCount(GameCharacter bearer) {
			if(Main.getProperties().hasValue(PropertyValue.metricFluids)) {
				return Math.round(bearer.getTotalFluidInArea(SexAreaOrifice.ANUS));
			} else {
				return Math.round(Units.mlToOz(bearer.getTotalFluidInArea(SexAreaOrifice.ANUS)));
			}
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isAnalContentEnabled();
		}
		@Override
		public boolean isRetroactiveApplicationAvailable() {
			return false;
		}
	},
	
	CUM_IN_STOMACH("cum in stomach", "Displays how much cum is currently in the bearer's stomach (in "+(Main.getProperties().hasValue(PropertyValue.metricFluids)?"mL":"oz")+").") {
		@Override
		public int getCount(GameCharacter bearer) {
			if(Main.getProperties().hasValue(PropertyValue.metricFluids)) {
				return Math.round(bearer.getTotalFluidInArea(SexAreaOrifice.MOUTH));
			} else {
				return Math.round(Units.mlToOz(bearer.getTotalFluidInArea(SexAreaOrifice.MOUTH)));
			}
		}
		@Override
		public boolean isRetroactiveApplicationAvailable() {
			return false;
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
	
	public boolean isRetroactiveApplicationAvailable() {
		return true;
	}

	/**
	 * @return The negative int which should be applied in order to start this TattooCounter at 0 (i.e. a value for removing retroactive application)
	 */
	public int getNonRetroactiveOffset(GameCharacter bearer) {
		if(!isRetroactiveApplicationAvailable()) {
			return 0;
		}
		return -getCount(bearer);
	}
	
	public boolean isAvailable() {
		return true;
	}
	
	public static List<TattooCounterType> getTattooCounterTypesWithContentFiltersApplied() {
		List<TattooCounterType> returnList = new ArrayList<>();
		for(TattooCounterType tct : TattooCounterType.values()) {
			if(tct.isAvailable()) {
				returnList.add(tct);
			}
		}
		return returnList;
	}
}
