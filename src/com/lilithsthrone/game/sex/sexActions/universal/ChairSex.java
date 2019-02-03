package com.lilithsthrone.game.sex.sexActions.universal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexSlotBipeds;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;
import com.lilithsthrone.game.sex.sexActions.PositioningData;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.8
 * @version 0.3.1
 * @author Innoxia
 */
public class ChairSex {

	private static boolean checkBaseRequirements(PositioningData data, boolean request) {
		return !Sex.isCharacterBannedFromPositioning(Sex.getCharacterPerformingAction())
				&& !(Sex.getPosition() == data.getPosition() && Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==data.getPerformerSlots().get(0))
				&& data.getPosition().getMaximumSlots()>=Sex.getTotalParticipantCount(false)
				&& Sex.getTotalParticipantCount(false)==(data.getPerformerSlots().size()+data.getPartnerSlots().size())
				&& (request
						?Sex.getSexControl(Sex.getCharacterPerformingAction())!=SexControl.FULL
						:Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL)
				&& (request
						?Sex.getCharacterPerformingAction().isPlayer()
						:true)
				&& (!request && !Sex.getCharacterPerformingAction().isPlayer()
						?(((NPC) Sex.getCharacterPerformingAction()).getSexPositionPreferences(Sex.getTargetedPartner(Sex.getCharacterPerformingAction())).contains(data.getPerformerSlots().get(0))
								|| ((NPC) Sex.getCharacterPerformingAction()).getSexPositionPreferences(Sex.getCharacterPerformingAction()).isEmpty())
						:true);
	}

	private static void setNewSexManager(PositioningData data, boolean requestAccepted) {
		Map<GameCharacter, SexSlot> dominants = new HashMap<>();
		Map<GameCharacter, SexSlot> submissives = new HashMap<>();
		List<GameCharacter> doms = new ArrayList<>(Sex.getDominantParticipants().keySet());
		List<GameCharacter> subs = new ArrayList<>(Sex.getSubmissiveParticipants().keySet());
		
		GameCharacter performer = Sex.getCharacterPerformingAction();
		GameCharacter target = Sex.getTargetedPartner(performer);
		if(requestAccepted) {
			target = Sex.getCharacterPerformingAction();
			performer = Sex.getTargetedPartner(target);
		}
		
		if(Sex.isDom(performer)) {
			doms.remove(performer);
			dominants.put(performer, data.getPerformerSlots().get(0));
			for(int i=0; i<doms.size(); i++) {
				dominants.put(doms.get(i), data.getPerformerSlots().get(i+1));
			}
			subs.remove(target);
			submissives.put(target, data.getPartnerSlots().get(0));
			for(int i=0; i<subs.size(); i++) {
				submissives.put(subs.get(i), data.getPartnerSlots().get(i+1));
			}
		} else {
			doms.remove(target);
			dominants.put(target, data.getPartnerSlots().get(0));
			for(int i=0; i<doms.size(); i++) {
				dominants.put(doms.get(i), data.getPartnerSlots().get(i+1));
			}
			subs.remove(performer);
			submissives.put(performer, data.getPerformerSlots().get(0));
			for(int i=0; i<subs.size(); i++) {
				submissives.put(subs.get(i), data.getPerformerSlots().get(i+1));
			}
		}
		Sex.setSexManager(new SexManagerDefault(
				data.getPosition(),
				dominants,
				submissives){
		});
		Sex.setPositionRequest(null);
	}
	
	public static final SexAction SWITCH_TO_GIVING_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPositionBipeds.CHAIR_SEX_ORAL,
				Util.newArrayListOfValues(SexSlotBipeds.CHAIR_KNEELING),
				Util.newArrayListOfValues(SexSlotBipeds.CHAIR_ORAL_SITTING));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
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
			setNewSexManager(data, false);
		}
	};
	
	public static final SexAction POSITION_GIVING_ORAL_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.CHAIR_SEX_ORAL,
				Util.newArrayListOfValues(SexSlotBipeds.CHAIR_KNEELING),
				Util.newArrayListOfValues(SexSlotBipeds.CHAIR_ORAL_SITTING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
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
			Sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction SWITCH_TO_RECEIVING_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.CHAIR_SEX_ORAL,
				Util.newArrayListOfValues(SexSlotBipeds.CHAIR_ORAL_SITTING),
				Util.newArrayListOfValues(SexSlotBipeds.CHAIR_KNEELING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
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
			setNewSexManager(data, false);
		}
	};
	
	public static final SexAction POSITION_RECEIVING_ORAL_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.CHAIR_SEX_ORAL,
				Util.newArrayListOfValues(SexSlotBipeds.CHAIR_ORAL_SITTING),
				Util.newArrayListOfValues(SexSlotBipeds.CHAIR_KNEELING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
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
			Sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction SWITCH_SITTING_BOTTOM = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.CHAIR_SEX,
				Util.newArrayListOfValues(SexSlotBipeds.CHAIR_BOTTOM),
				Util.newArrayListOfValues(SexSlotBipeds.CHAIR_TOP));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
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
			setNewSexManager(data, false);
		}
	};
	
	public static final SexAction POSITION_SITTING_BOTTOM_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.CHAIR_SEX,
				Util.newArrayListOfValues(SexSlotBipeds.CHAIR_BOTTOM),
				Util.newArrayListOfValues(SexSlotBipeds.CHAIR_TOP));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
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
			Sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction SWITCH_SITTING_TOP = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.CHAIR_SEX,
				Util.newArrayListOfValues(SexSlotBipeds.CHAIR_TOP),
				Util.newArrayListOfValues(SexSlotBipeds.CHAIR_BOTTOM));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
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
			setNewSexManager(data, false);
		}
	};
	
	public static final SexAction POSITION_SITTING_TOP_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.CHAIR_SEX,
				Util.newArrayListOfValues(SexSlotBipeds.CHAIR_TOP),
				Util.newArrayListOfValues(SexSlotBipeds.CHAIR_BOTTOM));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
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
			Sex.setPositionRequest(data);
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
			return Sex.getPositionRequest()!=null
					&& !Sex.getCharacterPerformingAction().isPlayer();
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
			if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.CHAIR_TOP) {
				if(Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexSlotBipeds.CHAIR_TOP)
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
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.CHAIR_BOTTOM) {
				if(Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexSlotBipeds.CHAIR_BOTTOM)
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
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.CHAIR_ORAL_SITTING) {
				if(Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexSlotBipeds.CHAIR_ORAL_SITTING)
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
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.CHAIR_KNEELING) {
				if(Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexSlotBipeds.CHAIR_KNEELING)
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
			Set<SexSlot> positionPreferences = Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this));

			if((Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.CHAIR_TOP && (positionPreferences.contains(SexSlotBipeds.CHAIR_TOP) || positionPreferences.isEmpty()))
					|| (Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.CHAIR_BOTTOM && (positionPreferences.contains(SexSlotBipeds.CHAIR_BOTTOM) || positionPreferences.isEmpty()))
					|| (Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.CHAIR_ORAL_SITTING && (positionPreferences.contains(SexSlotBipeds.CHAIR_ORAL_SITTING) || positionPreferences.isEmpty()))
					|| (Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.CHAIR_KNEELING && (positionPreferences.contains(SexSlotBipeds.CHAIR_KNEELING) || positionPreferences.isEmpty()))) {
				setNewSexManager(Sex.getPositionRequest(), true);
			}
			
			Sex.setPositionRequest(null);
		}
	};
	
}
