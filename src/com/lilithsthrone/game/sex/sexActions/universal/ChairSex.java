package com.lilithsthrone.game.sex.sexActions.universal;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.managers.universal.SMChair;
import com.lilithsthrone.game.sex.managers.universal.SMChairOral;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Innoxia
 */
public class ChairSex {

	public static final SexAction SWITCH_TO_GIVING_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPositionChangingAllowed(Sex.getCharacterPerformingAction())
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()) != SexPositionSlot.CHAIR_KNEELING
					&& Sex.isDom(Sex.getCharacterPerformingAction())
					&& (Sex.getCharacterPerformingAction().isPlayer()
							|| (((NPC) Sex.getCharacterPerformingAction()).getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexPositionSlot.CHAIR_KNEELING)
									|| Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).isEmpty()));
		}
		
		@Override
		public String getActionTitle() {
			return "Kneel";
		}

		@Override
		public String getActionDescription() {
			return "Kneel down in front of [npc2.name] and perform oral on [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			return "Deciding that [npc.she] [npc.verb(want)] to perform oral on [npc2.name], [npc.name] [npc.verb(kneel)] down in front of [npc2.herHim]."
					+ " Bringing [npc.her] mouth up to [npc2.namePos] groin, [npc.she] [npc.moansVerb], "
					+ "[npc.speech(You just sit back and enjoy this!)]";
		}

		@Override
		public void applyEffects() {
			if(Sex.isDom(Sex.getCharacterPerformingAction())) {
				Sex.setSexManager(new SMChairOral(
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexPositionSlot.CHAIR_KNEELING)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.CHAIR_ORAL_SITTING))));
			} else {
				Sex.setSexManager(new SMChairOral(
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.CHAIR_ORAL_SITTING)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexPositionSlot.CHAIR_KNEELING))));
			}
			
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction POSITION_GIVING_ORAL_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedChairOralGiving
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())!=SexPositionSlot.CHAIR_KNEELING
					&& SexPositionType.CHAIR_SEX_ORAL.getMaximumSlots()>=Sex.getTotalParticipantCount(false)
					&& !Sex.isDom(Sex.getCharacterPerformingAction())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Kneel";
		}

		@Override
		public String getActionDescription() {
			return "Try to kneel down in front of [npc2.name] in order to perform oral on [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			return "You try to manoeuvre around in such a way that you'll be able to kneel in front of [npc2.name]. As you do this, you [npc.moan], [npc.speech(Please, I want to use my mouth...)]";
		}

		@Override
		public void applyEffects() {
			SexFlags.requestedChairOralGiving = true;
		}
	};
	
	public static final SexAction SWITCH_TO_RECEIVING_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPositionChangingAllowed(Sex.getCharacterPerformingAction())
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()) != SexPositionSlot.CHAIR_ORAL_SITTING
					&& Sex.isDom(Sex.getCharacterPerformingAction())
					&& (Sex.getCharacterPerformingAction().isPlayer()
							|| (((NPC) Sex.getCharacterPerformingAction()).getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexPositionSlot.CHAIR_ORAL_SITTING)
									|| Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).isEmpty()));
		}
		
		@Override
		public String getActionTitle() {
			return "Receive oral";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to kneel down in front of you and perform oral.";
		}

		@Override
		public String getDescription() {
			return "Deciding that [npc.she] [npc.verb(want)] to get some oral sex, [npc.name] [npc.verb(get)] [npc2.name] to kneel down in front of [npc.herHim]."
					+ " Looking down into [npc2.her] [npc2.eyes], [npc.name] [npc.moansVerb], "
					+ "[npc.speech(Time to put your mouth to use!)]";
		}

		@Override
		public void applyEffects() {
			if(Sex.isDom(Sex.getCharacterPerformingAction())) {
				Sex.setSexManager(new SMChairOral(
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexPositionSlot.CHAIR_ORAL_SITTING)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.CHAIR_KNEELING))));
				
			} else {
				Sex.setSexManager(new SMChairOral(
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.CHAIR_KNEELING)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexPositionSlot.CHAIR_ORAL_SITTING))));
			}
			
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction POSITION_RECEIVING_ORAL_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedChairOralReceiving
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())!=SexPositionSlot.CHAIR_ORAL_SITTING
					&& SexPositionType.CHAIR_SEX_ORAL.getMaximumSlots()>=Sex.getTotalParticipantCount(false)
					&& !Sex.isDom(Sex.getCharacterPerformingAction())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Receive oral";
		}

		@Override
		public String getActionDescription() {
			return "Try to get [npc2.name] to kneel down in front of you so that [npc2.she] can perform oral on you.";
		}

		@Override
		public String getDescription() {
			return "You try to manoeuvre around in such a way that you'll be able to get [npc2.name] to kneel down in front of you. As you do this, you [npc.moan], [npc.speech(Please, I want you to use your mouth...)]";
		}

		@Override
		public void applyEffects() {
			SexFlags.requestedChairOralReceiving = true;
		}
	};
	
	public static final SexAction SWITCH_SITTING_BOTTOM = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPositionChangingAllowed(Sex.getCharacterPerformingAction())
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()) != SexPositionSlot.CHAIR_BOTTOM
					&& Sex.isDom(Sex.getCharacterPerformingAction())
					&& (Sex.getCharacterPerformingAction().isPlayer()
							|| (((NPC) Sex.getCharacterPerformingAction()).getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexPositionSlot.CHAIR_BOTTOM)
									|| Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).isEmpty()));
		}
		
		@Override
		public String getActionTitle() {
			return "Sit (bottom)";
		}

		@Override
		public String getActionDescription() {
			return "Switch position so that you're the one sitting down, with [npc2.name] sitting in your lap.";
		}

		@Override
		public String getDescription() {
			return "[npc.Name] [npc.verb(decide)] to switch positions, and, getting [npc2.name] to stand up, [npc.she] [npc.verb(sit)] down, before pulling [npc2.herHim] down onto [npc.her] lap."
					+ " Looking up into [npc2.her] [npc2.eyes], [npc.she] [npc.moansVerb], "
					+ "[npc.speech(Good [npc2.girl]!)]";
		}

		@Override
		public void applyEffects() {
			if(Sex.isDom(Sex.getCharacterPerformingAction())) {
				Sex.setSexManager(new SMChair(
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexPositionSlot.CHAIR_BOTTOM)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.CHAIR_TOP))));
				
			} else {
				Sex.setSexManager(new SMChair(
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.CHAIR_TOP)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexPositionSlot.CHAIR_BOTTOM))));
			}
			
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction POSITION_SITTING_BOTTOM_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedChairOralReceiving
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())!=SexPositionSlot.CHAIR_BOTTOM
					&& SexPositionType.CHAIR_SEX.getMaximumSlots()>=Sex.getTotalParticipantCount(false)
					&& !Sex.isDom(Sex.getCharacterPerformingAction())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Sit (bottom)";
		}

		@Override
		public String getActionDescription() {
			return "Try to get [npc2.name] to sit down in your lap.";
		}

		@Override
		public String getDescription() {
			return "You try to manoeuvre around in such a way that you'll be able to get [npc2.name] to sit in your lap. As you do this, you [npc.moan], [npc.speech(Please, I want to be on the bottom...)]";
		}

		@Override
		public void applyEffects() {
			SexFlags.requestedChairBottom = true;
		}
	};
	
	public static final SexAction SWITCH_SITTING_TOP = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPositionChangingAllowed(Sex.getCharacterPerformingAction())
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()) != SexPositionSlot.CHAIR_TOP
					&& Sex.isDom(Sex.getCharacterPerformingAction())
					&& (Sex.getCharacterPerformingAction().isPlayer()
							|| (((NPC) Sex.getCharacterPerformingAction()).getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexPositionSlot.CHAIR_TOP)
									|| Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).isEmpty()));
		}
		
		@Override
		public String getActionTitle() {
			return "Sit (top)";
		}

		@Override
		public String getActionDescription() {
			return "Switch position so that [npc2.name] is the one sitting down, with you sitting in [npc2.her] lap.";
		}

		@Override
		public String getDescription() {
			return "[npc.Name] [npc.verb(decide)] to switch positions, and, getting [npc2.name] to sit down, [npc.she] [npc.verb(sit)] down on [npc2.her] lap."
					+ " Looking down into [npc2.her] [npc2.eyes], [npc.she] [npc.moansVerb], "
					+ "[npc.speech(Good [npc2.girl]!)]";
		}

		@Override
		public void applyEffects() {
			if(Sex.isDom(Sex.getCharacterPerformingAction())) {
				Sex.setSexManager(new SMChair(
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexPositionSlot.CHAIR_TOP)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.CHAIR_BOTTOM))));
				
			} else {
				Sex.setSexManager(new SMChair(
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.CHAIR_BOTTOM)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexPositionSlot.CHAIR_TOP))));
			}
			
			
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction POSITION_SITTING_TOP_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedChairOralReceiving
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())!=SexPositionSlot.CHAIR_TOP
					&& SexPositionType.CHAIR_SEX.getMaximumSlots()>=Sex.getTotalParticipantCount(false)
					&& !Sex.isDom(Sex.getCharacterPerformingAction())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Sit (top)";
		}

		@Override
		public String getActionDescription() {
			return "Try to sit down in [npc2.namePos] lap.";
		}

		@Override
		public String getDescription() {
			return "You try to manoeuvre around in such a way that you'll be able to sit down in [npc2.namePos] lap. As you do this, you [npc.moan], [npc.speech(Please, I want to be on the top...)]";
		}

		@Override
		public void applyEffects() {
			SexFlags.requestedChairTop= true;
		}
	};
	
	public static final SexAction PARTNER_POSITION_RESPONSE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return (SexFlags.requestedChairBottom
					|| SexFlags.requestedChairTop
					|| SexFlags.requestedChairOralGiving
					|| SexFlags.requestedChairOralReceiving)
					&& !Sex.getCharacterPerformingAction().isPlayer()
					&& !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			if(SexFlags.requestedChairBottom) {
				if(Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexPositionSlot.CHAIR_TOP)
						|| Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).isEmpty()) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							return "Roughly pushing [npc2.name] down, [npc.name] [npc.verb(straddle)] [npc2.her] lap, leaning forwards to glare into [npc2.her] eyes as [npc.she] [npc.moansVerb],"
									+ " [npc.speech(Alright, slut, I'll take you for a ride!)]";
						default:
							return "Pushing [npc2.name] down, [npc.name] steps forwards and [npc.verb(straddle)] [npc2.her] lap, leaning forwards to gaze into [npc2.her] eyes as [npc.she] [npc.moansVerb],"
									+ " [npc.speech(Sure, I'll take you for a ride...)]";
					}
					
				} else {
					return "Reaching down to grab [npc2.name] by the [npc2.arm], [npc.name] [npc.verb(pull)] [npc2.herHim] back into [npc2.her] old position as [npc.she] angrily scolds [npc2.herHim], "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(SexFlags.requestedChairTop) {
				if(Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexPositionSlot.CHAIR_BOTTOM)
						|| Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).isEmpty()) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							return "Sitting down, [npc.name] [npc.verb(grab)] hold of [npc2.namePos] [npc2.arm], and with a sharp tug,"
										+ " [npc.she] [npc.verb(pull)] [npc2.herHim] down so that [npc2.sheIs] straddling [npc.her] lap. Leaning forwards to glare into [npc2.her] eyes, [npc.name] [npc.moansVerb],"
									+ " [npc.speech(Alright, slut, you'd better make this good!)]";
						default:
							return "Sitting down, [npc.name] [npc.verb(take)] hold of [npc2.namePos] [npc2.arm], and with a firm tug,"
										+ " [npc.she] [npc.verb(pull)] [npc2.herHim] down so that [npc2.sheIs] straddling [npc.her] lap. Leaning forwards to gaze into [npc2.her] eyes, [npc.name] [npc.moansVerb],"
									+ " [npc.speech(Good [npc2.girl]...)]";
					}
					
				} else {
					return "Reaching down to grab [npc2.name] by the [npc2.arm], [npc.name] [npc.verb(pull)] [npc2.herHim] back into [npc2.her] old position as [npc.she] angrily scolds [npc2.herHim], "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(SexFlags.requestedChairOralGiving) {
				if(Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexPositionSlot.CHAIR_ORAL_SITTING)
						|| Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).isEmpty()) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							return "Sitting down, [npc.name] [npc.verb(grab)] hold of [npc2.namePos] [npc2.arm], and with a sharp tug, [npc.she] [npc.verb(pull)] [npc2.herHim] down onto [npc2.her] knees before [npc.herHim]."
									+ " Leaning forwards to glare down into [npc2.her] eyes, [npc.name] [npc.moansVerb],"
									+ " [npc.speech(Alright, slut, put that mouth of yours to use!)]";
						default:
							return "Sitting down, [npc.name] [npc.verb(take)] hold of [npc2.namePos] [npc2.arm], and with a firm tug, [npc.she] [npc.verb(pull)] [npc2.herHim] down onto [npc2.her] knees before [npc.herHim]."
									+ " Leaning forwards to gaze into [npc2.her] eyes, [npc.name] [npc.moansVerb],"
									+ " [npc.speech(Good [npc2.girl]. Let's put your mouth to use...)]";
					}
					
				} else {
					return "Reaching down to grab [npc2.name] by the [npc2.arm], [npc.name] [npc.verb(pull)] [npc2.herHim] back into [npc2.her] old position as [npc.she] angrily scolds [npc2.herHim], "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(SexFlags.requestedChairOralReceiving) {
				if(Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexPositionSlot.CHAIR_KNEELING)
						|| Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).isEmpty()) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							return "Roughly pushing [npc2.name] down, [npc.name] kneels before [npc2.herHim], leaning forwards to glare up into [npc2.her] eyes as [npc.she] [npc.moansVerb],"
									+ " [npc.speech(Alright, slut, you'd better be glad that I like performing oral!)]";
						default:
							return "Pushing [npc2.name] down, [npc.name] kneels before [npc2.herHim], leaning forwards to gaze up into [npc2.her] eyes as [npc.she] [npc.moansVerb],"
									+ " [npc.speech(I love performing oral...)]";
					}
					
				} else {
					return "Reaching down to grab [npc2.name] by the [npc2.arm], [npc.name] [npc.verb(pull)] [npc2.herHim] back into [npc2.her] old position as [npc.she] angrily scolds [npc2.herHim], "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
			}
			
			return "";
		}

		@Override
		public void applyEffects() {
			if(SexFlags.requestedChairBottom && (Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexPositionSlot.CHAIR_TOP)
					|| Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).isEmpty())) {
				Sex.setSexManager(new SMChair(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.CHAIR_TOP)),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_BOTTOM))));
				
			} else if(SexFlags.requestedChairTop && (Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexPositionSlot.CHAIR_BOTTOM)
					|| Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).isEmpty())) {
				Sex.setSexManager(new SMChair(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.CHAIR_BOTTOM)),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_TOP))));
				
			} else if(SexFlags.requestedChairOralGiving && (Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexPositionSlot.CHAIR_ORAL_SITTING)
					|| Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).isEmpty())) {
				Sex.setSexManager(new SMChairOral(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.CHAIR_ORAL_SITTING)),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_KNEELING))));
				
			} else if(SexFlags.requestedChairOralReceiving && (Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexPositionSlot.CHAIR_KNEELING)
					|| Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).isEmpty())) {
				Sex.setSexManager(new SMChairOral(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.CHAIR_KNEELING)),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_ORAL_SITTING))));
				
			}
			
			SexFlags.resetRequests();
		}
	};
	
}
