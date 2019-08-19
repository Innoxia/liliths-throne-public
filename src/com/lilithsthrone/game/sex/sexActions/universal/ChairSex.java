package com.lilithsthrone.game.sex.sexActions.universal;

import java.util.HashMap;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPositionOther;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexSlotOther;
import com.lilithsthrone.game.sex.sexActions.PositioningData;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericPositioningNew;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.8
 * @version 0.3.3.10
 * @author Innoxia
 */
public class ChairSex {

	private static boolean checkBaseRequirements(PositioningData data, boolean request) {
		return Sex.isPositionChangingAllowed(Sex.getCharacterPerformingAction())
				&& !(Sex.getPosition() == data.getPosition()
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==data.getPerformerSlots().get(0)
					&& Sex.getSexPositionSlot(Sex.getTargetedPartner(Sex.getCharacterPerformingAction()))==data.getPartnerSlots().get(0))
				&& data.getPosition().getMaximumSlots()>=Sex.getTotalParticipantCount(false)
				&& Sex.getTotalParticipantCount(false)<=(data.getPerformerSlots().size()+data.getPartnerSlots().size())
				&& (request
						?Sex.getCharacterPerformingAction().isPlayer() && Sex.getSexControl(Sex.getCharacterPerformingAction())!=SexControl.FULL
						:(Sex.getCharacterPerformingAction().isPlayer()
							?Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL
							:!Sex.isCharacterForbiddenByOthersFromPositioning(Sex.getCharacterPerformingAction())))
				&& (!request && !Sex.getCharacterPerformingAction().isPlayer()
						?((NPC) Sex.getCharacterPerformingAction()).isHappyToBeInSlot(data.getPosition(), data.getPerformerSlots().get(0), data.getPartnerSlots().get(0), Sex.getTargetedPartner(Sex.getCharacterPerformingAction()))
						:true);
	}
	
	private static boolean isSittingAvailable(GameCharacter gettingFucked) {
		return gettingFucked.getLegConfiguration().isBipedalPositionedGenitals();
	}
	
	public static final SexAction SWITCH_TO_STANDING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPositionOther.STANDING,
				Util.newArrayListOfValues(
						SexSlotOther.STANDING_DOMINANT,
						SexSlotOther.STANDING_DOMINANT_TWO,
						SexSlotOther.STANDING_DOMINANT_THREE,
						SexSlotOther.STANDING_DOMINANT_FOUR),
				Util.newArrayListOfValues(
						SexSlotOther.STANDING_SUBMISSIVE,
						SexSlotOther.STANDING_SUBMISSIVE_TWO,
						SexSlotOther.STANDING_SUBMISSIVE_THREE,
						SexSlotOther.STANDING_SUBMISSIVE_FOUR));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Switch to standing";
		}
		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to join you in standing up, ready to move to a different sex position.";
		}
		@Override
		public String getDescription() {
			return "Deciding that [npc.she] [npc.verb(want)] to switch into a different position, [npc.name] [npc.verb(get)] [npc2.name] to stand up with [npc.herHim]."
					+ " Now face-to-face with [npc.her] partner, [npc.name] [npc.moansVerb], "
					+ "[npc.speech(Let's try something else...)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	private static void applyChangeSlotEffects(GameCharacter mover, SexSlot moverSlot, GameCharacter partner, SexSlot partnerSlot) {
		if(Sex.getCharacterInPosition(moverSlot)!=null) {
			Sex.swapSexPositionSlots(mover, Sex.getCharacterInPosition(moverSlot));
		}
		if(Sex.getCharacterInPosition(partnerSlot)!=null && !Sex.getCharacterInPosition(partnerSlot).equals(partner)) {
			Sex.swapSexPositionSlots(partner, Sex.getCharacterInPosition(partnerSlot));
		}
		
		Map<GameCharacter, SexSlot> dominants = new HashMap<>(Sex.getDominantParticipants(true));
		Map<GameCharacter, SexSlot> submissives = new HashMap<>(Sex.getSubmissiveParticipants(true));
		
		if(Sex.isDom(mover)) {
			dominants.put(mover, moverSlot);
		} else {
			submissives.put(mover, moverSlot);
		}
		if(Sex.isDom(partner)) {
			dominants.put(partner, partnerSlot);
		} else {
			submissives.put(partner, partnerSlot);
		}

		Sex.setSexManager(new SexManagerDefault(
				SexPositionOther.SITTING,
				dominants,
				submissives){
		});
	}
	
	public static final SexAction SWITCH_TO_GIVING_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPositionOther.SITTING,
				Util.newArrayListOfValues(SexSlotOther.PERFORMING_ORAL),
				Util.newArrayListOfValues(SexSlotOther.SITTING));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Perform oral";
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
			applyChangeSlotEffects(
					Sex.getCharacterPerformingAction(),
					SexSlotOther.PERFORMING_ORAL,
					Sex.getCharacterTargetedForSexAction(this),
					SexSlotOther.SITTING);
//			GenericPositioningNew.setNewSexManager(data, false);
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
				SexPositionOther.SITTING,
				Util.newArrayListOfValues(SexSlotOther.PERFORMING_ORAL),
				Util.newArrayListOfValues(SexSlotOther.SITTING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Perform oral (R)";
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
				SexPositionOther.SITTING,
				Util.newArrayListOfValues(SexSlotOther.SITTING),
				Util.newArrayListOfValues(SexSlotOther.PERFORMING_ORAL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isSittingAvailable(Sex.getCharacterPerformingAction())
					&& checkBaseRequirements(data, false);
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
			applyChangeSlotEffects(
					Sex.getCharacterPerformingAction(),
					SexSlotOther.SITTING,
					Sex.getCharacterTargetedForSexAction(this),
					SexSlotOther.PERFORMING_ORAL);
//			GenericPositioningNew.setNewSexManager(data, false);
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
				SexPositionOther.SITTING,
				Util.newArrayListOfValues(SexSlotOther.SITTING),
				Util.newArrayListOfValues(SexSlotOther.PERFORMING_ORAL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isSittingAvailable(Sex.getCharacterPerformingAction())
					&& checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Receive oral (R)";
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
	
	public static final SexAction SWITCH_TO_RECEIVING_ORAL_AS_TAUR = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionOther.SITTING,
				Util.newArrayListOfValues(SexSlotOther.SITTING_TAUR_PRESENTING_ORAL),
				Util.newArrayListOfValues(SexSlotOther.SITTING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()
					&& checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Receive oral";
		}
		@Override
		public String getActionDescription() {
			return "Turn around and present your animalistic genitals to [npc2.name] so that [npc2.she] can perform oral on you.";
		}
		@Override
		public String getDescription() {
			return "Deciding that [npc.she] [npc.verb(want)] to get some oral sex, [npc.name] [npc.verb(stand)] up [npc.verb(turn)] around, before stepping back and presenting [npc.her] animalistic genitals to [npc2.name]."
					+ " Looking back down over [npc.her] shoulder, [npc.name] [npc.moansVerb], "
					+ "[npc.speech(Time to put your mouth to use!)]";
		}
		@Override
		public void applyEffects() {
			applyChangeSlotEffects(
					Sex.getCharacterPerformingAction(),
					SexSlotOther.SITTING_TAUR_PRESENTING_ORAL,
					Sex.getCharacterTargetedForSexAction(this),
					SexSlotOther.SITTING);
//			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction POSITION_RECEIVING_ORAL_AS_TAUR_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionOther.SITTING,
				Util.newArrayListOfValues(SexSlotOther.SITTING_TAUR_PRESENTING_ORAL),
				Util.newArrayListOfValues(SexSlotOther.SITTING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()
					&& checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Receive oral (R)";
		}
		@Override
		public String getActionDescription() {
			return "Try to turn around and present your animalistic genitals to [npc2.name] so that [npc2.she] can perform oral on you.";
		}
		@Override
		public String getDescription() {
			return "[npc.Name] [npc.verb(try)] to stand up and turn around in order to present [npc.her] animalistic genitals to [npc2.namePos] mouth."
					+ " As [npc.she] [npc.does] this, [npc.she] [npc.moans], [npc.speech(Please, I want you to use your mouth...)]";
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
				SexPositionOther.SITTING,
				Util.newArrayListOfValues(SexSlotOther.SITTING),
				Util.newArrayListOfValues(SexSlotOther.SITTING_IN_LAP));

		@Override
		public boolean isBaseRequirementsMet() {
			return isSittingAvailable(Sex.getCharacterPerformingAction())
					&& checkBaseRequirements(data, false);
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
			applyChangeSlotEffects(
					Sex.getCharacterPerformingAction(),
					SexSlotOther.SITTING,
					Sex.getCharacterTargetedForSexAction(this),
					SexSlotOther.SITTING_IN_LAP);
//			GenericPositioningNew.setNewSexManager(data, false);
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
				SexPositionOther.SITTING,
				Util.newArrayListOfValues(SexSlotOther.SITTING),
				Util.newArrayListOfValues(SexSlotOther.SITTING_IN_LAP));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isSittingAvailable(Sex.getCharacterPerformingAction())
					&& checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Sit (bottom) (R)";
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
				SexPositionOther.SITTING,
				Util.newArrayListOfValues(SexSlotOther.SITTING_IN_LAP),
				Util.newArrayListOfValues(SexSlotOther.SITTING));

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
			if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) {
				return "Switch positions so that [npc2.name] is the one sitting down, with you sitting in [npc2.her] lap.";
			} else {
				return "Switch positions so that [npc2.name] is the one sitting down, with you having turned around and lowered your animalistic genitals down into [npc2.her] lap";
			}
		}
		@Override
		public String getDescription() {
			if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) {
				return "[npc.Name] [npc.verb(decide)] to switch positions, and, getting [npc2.name] to sit down, [npc.she] [npc.verb(sit)] down on [npc2.her] lap."
						+ " Looking down into [npc2.her] [npc2.eyes], [npc.she] [npc.moansVerb], "
						+ "[npc.speech(Good [npc2.girl]!)]";
			} else {
				return "[npc.Name] [npc.verb(decide)] to switch positions, and, getting [npc2.name] to sit down, [npc.she] [npc.verb(stand)] up, [npc.verb(turn)] around, and [npc.verb(lower)] [npc.her] animalistic rear end down into [npc2.namePos] lap."
						+ " Looking back down over [npc.her] shoulder, [npc.name] then [npc.moansVerb], "
						+ "[npc.speech(Time to give you a ride!)]";
			}
		}
		@Override
		public void applyEffects() {
			applyChangeSlotEffects(
					Sex.getCharacterPerformingAction(),
					SexSlotOther.SITTING_IN_LAP,
					Sex.getCharacterTargetedForSexAction(this),
					SexSlotOther.SITTING);
//			GenericPositioningNew.setNewSexManager(data, false);
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
				SexPositionOther.SITTING,
				Util.newArrayListOfValues(SexSlotOther.SITTING_IN_LAP),
				Util.newArrayListOfValues(SexSlotOther.SITTING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Sit (top) (R)";
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
	
	public static final SexAction SWITCH_STANDING_BETWEEN_LEGS = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionOther.SITTING,
				Util.newArrayListOfValues(SexSlotOther.SITTING_BETWEEN_LEGS),
				Util.newArrayListOfValues(SexSlotOther.SITTING));

		@Override
		public boolean isBaseRequirementsMet() {
			return isSittingAvailable(Sex.getCharacterPerformingAction())
					&& checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Between legs";
		}
		@Override
		public String getActionDescription() {
			return "Switch position so that [npc2.name] is the one sitting down, with you "
					+(SexSlotOther.SITTING_BETWEEN_LEGS.isStanding(Sex.getCharacterPerformingAction())?"standing":"kneeling")+" between [npc2.her] [npc2.legs], ready to fuck [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			return "[npc.Name] [npc.verb(decide)] to switch positions, and, with [npc2.name] sitting down, [npc.she] [npc.verb(move)] to "
						+(SexSlotOther.SITTING_BETWEEN_LEGS.isStanding(Sex.getCharacterPerformingAction())?"stand":"kneel")+" between [npc2.her] [npc2.legs]."
					+ " Looking down into [npc2.her] [npc2.eyes], [npc.she] [npc.moansVerb], "
					+ "[npc.speech(Time to give you a good fuck!)]";
		}
		@Override
		public void applyEffects() {
			applyChangeSlotEffects(
					Sex.getCharacterPerformingAction(),
					SexSlotOther.SITTING_BETWEEN_LEGS,
					Sex.getCharacterTargetedForSexAction(this),
					SexSlotOther.SITTING);
//			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction SWITCH_STANDING_BETWEEN_LEGS_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionOther.SITTING,
				Util.newArrayListOfValues(SexSlotOther.SITTING_BETWEEN_LEGS),
				Util.newArrayListOfValues(SexSlotOther.SITTING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isSittingAvailable(Sex.getCharacterPerformingAction())
					&& checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Between legs (R)";
		}
		@Override
		public String getActionDescription() {
			return "Try to switch position so that [npc2.name] is the one sitting down, with you "
						+(SexSlotOther.SITTING_BETWEEN_LEGS.isStanding(Sex.getCharacterPerformingAction())?"standing":"kneeling")+" between [npc2.her] [npc2.legs], ready to fuck [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			return "You try to manoeuvre around in such a way that [npc2.nameIsFull] sitting down, with you "
						+(SexSlotOther.SITTING_BETWEEN_LEGS.isStanding(Sex.getCharacterPerformingAction())?"standing":"kneeling")+" between [npc2.her] [npc2.legs]."
						+ " As you do this, you [npc.moan], [npc.speech(Please, I want to fuck you like this...)]";
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
			boolean isHappy = ((NPC)Sex.getCharacterPerformingAction()).isHappyToBeInSlot(
					Sex.getPositionRequest().getPosition(),
					Sex.getPositionRequest().getPartnerSlots().get(0),
					Sex.getPositionRequest().getPerformerSlots().get(0),
					Main.game.getPlayer());
			
			if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotOther.SITTING_IN_LAP) {
				if(isHappy) {
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
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotOther.SITTING) {
				if(Sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotOther.SITTING_IN_LAP) {
					if(isHappy) {
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
					
				} else if(Sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotOther.SITTING_TAUR_PRESENTING_ORAL) {
					if(isHappy) {
						switch(Sex.getSexPace(Sex.getActivePartner())) {
							case DOM_ROUGH:
								return "Letting [npc2.name] finish [npc2.her] manoeuvre, [npc.name] reaches out to grab [npc2.her] hind-legs, before pulling [npc2.her] animalistic groin up to [npc.her] face."
										+ " Giving [npc2.her] rump a sharp slap, [npc.name] then [npc.moansVerb],"
										+ " [npc.speech(Alright, slut, you'd better make this good!)]";
							default:
								return "Letting [npc2.name] finish [npc2.her] manoeuvre, [npc.name] reaches out to grab [npc2.her] hind-legs, before pulling [npc2.her] animalistic groin up to [npc.her] face."
										+ " Giving [npc2.her] rump a playful slap, [npc.name] then [npc.moansVerb],"
										+ " [npc.speech(Good [npc2.girl]...)]";
						}
						
					} else {
						return "Reaching down to grab [npc2.name] by the [npc2.arm], [npc.name] [npc.verb(pull)] [npc2.herHim] back into [npc2.her] old position as [npc.she] angrily scolds [npc2.herHim], "
								+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
					}
					
				} else if(Sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotOther.SITTING_BETWEEN_LEGS) {
					if(isHappy) {
						switch(Sex.getSexPace(Sex.getActivePartner())) {
							case DOM_ROUGH:
								return "Letting [npc2.name] finish [npc2.her] manoeuvre, [npc.name] sits down, before lifting [npc.her] [np.legs], wrapping them around [npc2.namePos] lower back, and roughly forcing [npc2.herHim] forwards."
										+ " Glaring up at [npc2.herHim], [npc.name] then spreads [npc.her] [npc.legs] and [npc.moansVerb],"
										+ " [npc.speech(Alright, slut, you'd better make this good!)]";
							default:
								return "Letting [npc2.name] finish [npc2.her] manoeuvre, [npc.name] sits down, before lifting [npc.her] [np.legs], wrapping them around [npc2.namePos] lower back, and forcing [npc2.herHim] forwards."
										+ " Smiling up at [npc2.herHim], [npc.name] then spreads [npc.her] [npc.legs] and [npc.moansVerb],"
										+ " [npc.speech(Come on then, fuck me!)]";
						}
						
					} else {
						return "Reaching down to grab [npc2.name] by the [npc2.arm], [npc.name] [npc.verb(pull)] [npc2.herHim] back into [npc2.her] old position as [npc.she] angrily scolds [npc2.herHim], "
								+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
					}
					
				} else {
					if(isHappy) {
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
				}
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotOther.PERFORMING_ORAL) {
				if(isHappy) {
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
			if(((NPC)Sex.getCharacterPerformingAction()).isHappyToBeInSlot(
					Sex.getPositionRequest().getPosition(),
					Sex.getPositionRequest().getPartnerSlots().get(0),
					Sex.getPositionRequest().getPerformerSlots().get(0),
					Main.game.getPlayer())) {

				applyChangeSlotEffects(
						Sex.getCharacterPerformingAction(),
						Sex.getPositionRequest().getPartnerSlots().get(0),
						Main.game.getPlayer(),
						Sex.getPositionRequest().getPerformerSlots().get(0));
				
//				GenericPositioningNew.setNewSexManager(Sex.getPositionRequest(), true);
			}
			
			Sex.setPositionRequest(null);
		}
	};
	
}
