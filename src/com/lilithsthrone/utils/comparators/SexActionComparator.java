package com.lilithsthrone.utils.comparators;

import java.util.Comparator;

import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericActions;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;

/**
 * This is fine
 * 
 * @since 0.4.10.12
 * @version 0.4.11.1
 * @author Innoxia
 */
public class SexActionComparator implements Comparator<SexActionInterface> {
	@Override
	public int compare(SexActionInterface s1, SexActionInterface s2) {
		if(s1==s2) {
			return 0;
		}
		
		if(s1.getActionRenderingPriority()!=s2.getActionRenderingPriority()) {
			return s1.getActionRenderingPriority()>s2.getActionRenderingPriority()?-1:1;
		}

		if(s1==GenericActions.PLAYER_STOP_SEX) {
			return 1;
		}
		if(s2==GenericActions.PLAYER_STOP_SEX) {
			return -1;
		}
		if(s1==GenericActions.PLAYER_SKIP_SEX) {
			return 1;
		}
		if(s2==GenericActions.PLAYER_SKIP_SEX) {
			return -1;
		}
		
		if(s1.getActionType()==s2.getActionType()) {
			if(s1==GenericOrgasms.GENERIC_PREPARATION_DENIAL) {
				return 1;
			}
			if(s2==GenericOrgasms.GENERIC_PREPARATION_DENIAL) {
				return -1;
			}
			if(s1.getSexPace()!=s2.getSexPace()) {
				return s1.getSexPace()==null
						?-1
						:s2.getSexPace()==null
							?1
							:s1.getSexPace().compareTo(s2.getSexPace());
			}
			
			// start/stop ongoing sort (?)
			int typeComparison = s1.getActionType().compareTo(s2.getActionType());
			if(typeComparison!=0) {
				return typeComparison;
			}
			
			/*
			 * For actions which have associated areas for performer and target, sort based on the following logic:
			 * 
			 * 1. Performer has a penetration type, target has an orifice type
			 *  i) Penetration type sorting takes priority
			 * ii) Orifice sorting secondary
			 * 
			 * 2. Performer has a penetration type, target has a penetration type
			 *  i) Performer's penetration type sorting takes priority
			 * ii) Target's penetration type sorting secondary
			 * 
			 * 3. Performer has a penetration type, target has null for targeting
			 *  i) Performer's penetration type sorting takes priority
			 * 
			 * 4. Performer has an orifice type, target has a penetration type
			 *  i) Performer's orifice type sorting takes priority
			 * ii) Target's penetration type sorting secondary
			 * 
			 * 5. Performer has an orifice type, target has an orifice type
			 *  i) Performer's orifice type sorting takes priority
			 * ii) Target's orifice type sorting secondary
			 * 
			 * 6. Performer has an orifice type, target has null for targeting
			 *  i) Performer's orifice type sorting takes priority
			 * 
			 */
			// FIXME I know this code is terrible! My brain isn't working! I haven't had enough coffee! I don't know how to do maths! Ok, ok, I admit it; I never finished Introduction to Algorithms 3rd edition! Leave me alone (or fix it)! ;_;
			SexAreaInterface s1PerformerArea = s1.getPerformingCharacterAreas().isEmpty()?null:s1.getPerformingCharacterAreas().get(0);
			SexAreaInterface s1TargetArea = s1.getTargetedCharacterAreas().isEmpty()?null:s1.getTargetedCharacterAreas().get(0);
			SexAreaInterface s2PerformerArea = s2.getPerformingCharacterAreas().isEmpty()?null:s2.getPerformingCharacterAreas().get(0);
			SexAreaInterface s2TargetArea = s2.getTargetedCharacterAreas().isEmpty()?null:s2.getTargetedCharacterAreas().get(0);
			
			// FIXME It came to me in a dream...
			int s1Value = 0;
			int s2Value = 0;
			if(s1PerformerArea!=null) {
				if(s1PerformerArea instanceof SexAreaPenetration) {
					s1Value += ((SexAreaPenetration)s1PerformerArea).ordinal();
				} else if(s1PerformerArea instanceof SexAreaOrifice) {
					s1Value +=  100 +((SexAreaOrifice)s1PerformerArea).ordinal();
				}
			}
			s1Value *= 1_000;
			if(s1TargetArea!=null) {
				if(s1TargetArea instanceof SexAreaPenetration) {
					s1Value += ((SexAreaPenetration)s1TargetArea).ordinal();
				} else if(s1TargetArea instanceof SexAreaOrifice) {
					s1Value +=  100 +((SexAreaOrifice)s1TargetArea).ordinal();
				}
			}
			
			if(s2PerformerArea!=null) {
				if(s2PerformerArea instanceof SexAreaPenetration) {
					s2Value += ((SexAreaPenetration)s2PerformerArea).ordinal();
				} else if(s2PerformerArea instanceof SexAreaOrifice) {
					s2Value +=  100 +((SexAreaOrifice)s2PerformerArea).ordinal();
				}
			}
			s2Value *= 1_000;
			if(s2TargetArea!=null) {
				if(s2TargetArea instanceof SexAreaPenetration) {
					s2Value += ((SexAreaPenetration)s2TargetArea).ordinal();
				} else if(s2TargetArea instanceof SexAreaOrifice) {
					s2Value +=  100 +((SexAreaOrifice)s2TargetArea).ordinal();
				}
			}
			
			if(s1Value-s2Value!=0) {
				return (int) Math.signum(s1Value-s2Value);
			}
			
//			// 1. Performer has a penetration type, target has an orifice type:
//			if(s1PerformerArea instanceof SexAreaPenetration && s1TargetArea instanceof SexAreaOrifice) {
//				if(!(s2PerformerArea instanceof SexAreaPenetration && s2TargetArea instanceof SexAreaOrifice)) {
//					return -1;
//				} else {
//					int penCompare = ((SexAreaPenetration)s1PerformerArea).compareTo((SexAreaPenetration)s2PerformerArea);
//					if(penCompare==0) {
//						return ((SexAreaOrifice)s1TargetArea).compareTo((SexAreaOrifice)s2TargetArea);
//					} else {
//						return penCompare;
//					}
//				}
//			}
//
//			// 2. Performer has a penetration type, target has a penetration type:
//			if(s1PerformerArea instanceof SexAreaPenetration && s1TargetArea instanceof SexAreaPenetration) {
//				if(!(s2PerformerArea instanceof SexAreaPenetration && s2TargetArea instanceof SexAreaPenetration)) {
//					if(s2PerformerArea instanceof SexAreaPenetration && s2TargetArea instanceof SexAreaOrifice) {
//						return 1;
//					} else {
//						return -1;
//					}
//				} else {
//					int penCompare = ((SexAreaPenetration)s1PerformerArea).compareTo((SexAreaPenetration)s2PerformerArea);
//					if(penCompare==0) {
//						return ((SexAreaPenetration)s1TargetArea).compareTo((SexAreaPenetration)s2TargetArea);
//					} else {
//						return penCompare;
//					}
//				}
//			}
//
//			// 3. Performer has a penetration type, target has null for targeting:
//			if(s1PerformerArea instanceof SexAreaPenetration && s1TargetArea==null) {
//				if(!(s2PerformerArea instanceof SexAreaPenetration && s2TargetArea == null)) {
//					if(s2PerformerArea instanceof SexAreaPenetration && s2TargetArea!=null) {
//						return 1;
//					} else {
//						return -1;
//					}
//				} else {
//					int penCompare = ((SexAreaPenetration)s1PerformerArea).compareTo((SexAreaPenetration)s2PerformerArea);
//					return penCompare;
//				}
//			}
//			
//			// 4. Performer has an orifice type, target has a penetration type:
//			if(s1PerformerArea instanceof SexAreaOrifice && s1TargetArea instanceof SexAreaPenetration) {
//				if(!(s2PerformerArea instanceof SexAreaOrifice && s2TargetArea instanceof SexAreaPenetration)) {
//					if(s2PerformerArea instanceof SexAreaPenetration) {
//						return 1;
//					} else {
//						return -1;
//					}
//				} else {
//					int orificeCompare = ((SexAreaOrifice)s1PerformerArea).compareTo((SexAreaOrifice)s2PerformerArea);
//					if(orificeCompare==0) {
//						return ((SexAreaPenetration)s1TargetArea).compareTo((SexAreaPenetration)s2TargetArea);
//					} else {
//						return orificeCompare;
//					}
//				}
//			}
//			
//			// 5. Performer has an orifice type, target has an orifice type:
//			if(s1PerformerArea instanceof SexAreaOrifice && s1TargetArea instanceof SexAreaOrifice) {
//				if(!(s2PerformerArea instanceof SexAreaOrifice && s2TargetArea instanceof SexAreaOrifice)) {
//					if(s2PerformerArea instanceof SexAreaPenetration
//							|| (s2PerformerArea instanceof SexAreaOrifice && s2TargetArea instanceof SexAreaPenetration)) {
//						return 1;
//					} else {
//						return -1;
//					}
//				} else {
//					int orificeCompare = ((SexAreaOrifice)s1PerformerArea).compareTo((SexAreaOrifice)s2PerformerArea);
//					if(orificeCompare==0) {
//						return ((SexAreaOrifice)s1TargetArea).compareTo((SexAreaOrifice)s2TargetArea);
//					} else {
//						return orificeCompare;
//					}
//				}
//			}
//			
//			// 6. Performer has an orifice type, target is null:
//			if(s1PerformerArea instanceof SexAreaOrifice && s1TargetArea==null) {
//				if(!(s2PerformerArea instanceof SexAreaOrifice && s2TargetArea==null)) {
//					if(s2PerformerArea instanceof SexAreaPenetration
//							|| (s2PerformerArea instanceof SexAreaOrifice && s2TargetArea!=null)) {
//						return 1;
//					} else {
//						return -1;
//					}
//				} else {
//					int orificeCompare = ((SexAreaOrifice)s1PerformerArea).compareTo((SexAreaOrifice)s2PerformerArea);
//					return orificeCompare;
//				}
//			}
		}
		
		return s1.getActionType().compareTo(s2.getActionType());
	}
}
