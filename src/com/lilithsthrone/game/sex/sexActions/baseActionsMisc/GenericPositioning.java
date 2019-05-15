package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;
import com.lilithsthrone.game.sex.positions.SexSlotBipeds;
import com.lilithsthrone.game.sex.sexActions.PositioningData;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * Contains all positional changes for both sub and dom.
 * 
 * If sub, positional change is just a suggestion, which the NPC may refuse if they have other preferences.
 * 
 * @since 0.1.79
 * @version 0.3.1
 * @author Innoxia
 */
public class GenericPositioning {

	private static String getRequestTooltipText() {
		return "[style.italicsSex(This is a request, which [npc2.name] might refuse!)]";
	}

	public static final SexAction POSITION_SWAP = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isPositionSwap() {
			return true;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return 
//					Sex.isPositionChangingAllowed(Sex.getCharacterPerformingAction())
					Sex.getInitialSexManager().isPositionChangingAllowed(Sex.getCharacterPerformingAction())
					&& Sex.getCharacterPerformingAction().getLegConfiguration()==Sex.getCharacterTargetedForSexAction(this).getLegConfiguration() // Can only swap if have same body type
					&& Sex.getSexManager().isPlayerAbleToSwapPositions()
					&& Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Swap with [npc2.name]";
		}

		@Override
		public String getActionDescription() {
			return "Swap position with [npc2.name].";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc2.name], you move [npc2.herHim] around and swap position with [npc2.herHim], before [npc.moaning],"
					+ " [npc.speech(It'll be more fun like this!)]";
		}

		@Override
		public void applyEffects() {
			Sex.swapSexPositionSlots(Main.game.getPlayer(), Sex.getActivePartner());
		}
	};
	
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

	public static final SexAction POSITION_MISSIONARY = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPositionBipeds.MISSIONARY,
				Util.newArrayListOfValues(SexSlotBipeds.MISSIONARY_KNEELING_BETWEEN_LEGS),
				Util.newArrayListOfValues(SexSlotBipeds.MISSIONARY_ON_BACK));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Missionary";
		}
		@Override
		public String getActionDescription() {
			return "Push [npc2.name] down onto [npc2.her] back and kneel between [npc2.her] [npc2.legs], ready to have sex in the missionary position.";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] down onto [npc2.her] back."
					+ " Kneeling down between [npc2.her] [npc2.legs], [npc.she] [npc.moansVerb] as [npc.she] looks down into [npc2.her] [npc2.eyes+],"
					+ " [npc.speech(That's right, spread your legs for me...)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction REQUEST_POSITION_MISSIONARY = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPositionBipeds.MISSIONARY,
				Util.newArrayListOfValues(SexSlotBipeds.MISSIONARY_KNEELING_BETWEEN_LEGS),
				Util.newArrayListOfValues(SexSlotBipeds.MISSIONARY_ON_BACK));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Missionary (R)";
		}
		@Override
		public String getActionDescription() {
			return "Try to get [npc2.name] to lie down on [npc2.her] back and spread [npc2.her] [npc2.legs] so that you can have sex with [npc2.herHim] in the missionary position.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "You reach up to take hold of [npc2.namePos] shoulders, and, pushing down, you try to get [npc2.herHim] to lie down on [npc2.her] back.";
		}
		@Override
		public void applyEffects() {
			Sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_MISSIONARY_ON_BACK = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPositionBipeds.MISSIONARY,
				Util.newArrayListOfValues(SexSlotBipeds.MISSIONARY_ON_BACK),
				Util.newArrayListOfValues(SexSlotBipeds.MISSIONARY_KNEELING_BETWEEN_LEGS));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Missionary (on back)";
		}
		@Override
		public String getActionDescription() {
			return "Lie down on your back and spread your [npc.legs], ready to have sex with [npc2.name] in the missionary position.";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] down onto [npc2.her] knees."
					+ " Kneeling down before [npc2.herHim], [npc.she] then [npc.verb(lie)] down on [npc.her] back, spreading [npc.her] [npc.legs] and looking up into [npc2.namePos] [npc2.eyes+] as [npc.she] [npc.moanVerb],"
					+ " [npc.speech(Come and take me!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction REQUEST_POSITION_MISSIONARY_ON_BACK = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.MISSIONARY,
				Util.newArrayListOfValues(SexSlotBipeds.MISSIONARY_ON_BACK),
				Util.newArrayListOfValues(SexSlotBipeds.MISSIONARY_KNEELING_BETWEEN_LEGS));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Missionary (on back) (R)";
		}
		@Override
		public String getActionDescription() {
			return "Lie down on your back and spread your [npc.legs] to try and encourage [npc2.name] to have sex with you in the missionary position.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "You lie down on your back before [npc2.name], letting out a little [npc.moan] as you spread your [npc.legs] to try and encourage [npc2.name] to have sex with you in the missionary position.";
		}
		@Override
		public void applyEffects() {
			Sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_FACE_TO_WALL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.FACING_WALL,
				Util.newArrayListOfValues(SexSlotBipeds.FACE_TO_WALL_FACING_TARGET),
				Util.newArrayListOfValues(SexSlotBipeds.FACE_TO_WALL_AGAINST_WALL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Face-to-wall";
		}
		@Override
		public String getActionDescription() {
			return "Push [npc2.name] up against a nearby wall.";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] up against a nearby wall."
					+ " Grinding [npc.her] body up against [npc2.her] back, [npc.she] [npc.moansVerb] into [npc2.her] [npc2.ear],"
					+ " [npc.speech(Good [npc2.girl]! Now hold still while I fuck you!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction REQUEST_POSITION_FACE_TO_WALL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.FACING_WALL,
				Util.newArrayListOfValues(SexSlotBipeds.FACE_TO_WALL_AGAINST_WALL),
				Util.newArrayListOfValues(SexSlotBipeds.FACE_TO_WALL_FACING_TARGET));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Face-to-wall (R)";
		}
		@Override
		public String getActionDescription() {
			return "Try and move into a position so that you're facing a nearby wall.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Before [npc2.name] can react, you quickly move up against a nearby wall."
					+ " Placing your hands up against the solid surface that's now in front of you, you push your [npc.ass+] out, shaking it at [npc2.name] as you try to encourage [npc2.herHim] to fuck you like this.";
		}
		@Override
		public void applyEffects() {
			Sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_BACK_TO_WALL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.BACK_TO_WALL,
				Util.newArrayListOfValues(SexSlotBipeds.BACK_TO_WALL_FACING_TARGET),
				Util.newArrayListOfValues(SexSlotBipeds.BACK_TO_WALL_AGAINST_WALL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Back-to-wall";
		}
		@Override
		public String getActionDescription() {
			return "Push [npc2.name] back against a nearby wall.";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] back against a nearby wall."
					+ " Grinding [npc.her] body up against [npc2.hers], [npc.she] [npc.moansVerb] into [npc2.her] [npc2.ear], "
					+ "[npc.speech(Good [npc2.girl]! Now hold still while I fuck you!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction REQUEST_POSITION_BACK_TO_WALL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.BACK_TO_WALL,
				Util.newArrayListOfValues(SexSlotBipeds.BACK_TO_WALL_AGAINST_WALL),
				Util.newArrayListOfValues(SexSlotBipeds.BACK_TO_WALL_FACING_TARGET));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Back-to-wall (R)";
		}
		@Override
		public String getActionDescription() {
			return "Try and move into a position so that your back is up against a nearby wall.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Before [npc2.name] can react, you quickly move up against a nearby wall."
					+ " Leaning back against the solid surface that's now behind you, you give [npc2.name] your most seductive look, trying to encourage [npc2.herHim] to fuck you like this.";
		}
		@Override
		public void applyEffects() {
			Sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_KNEELING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.KNEELING_ORAL,
				Util.newArrayListOfValues(SexSlotBipeds.KNEELING_RECEIVING_ORAL),
				Util.newArrayListOfValues(SexSlotBipeds.KNEELING_PERFORMING_ORAL));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Kneel (receive oral)";
		}
		@Override
		public String getActionDescription() {
			return "Force [npc2.name] to [npc2.her] knees.";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] quickly [npc.verb(force)] [npc2.herHim] to [npc2.her] knees before [npc.herHim]."
					+ " Looking up, [npc2.name] [npc2.verb(see)] [npc.herHim] grinning down at [npc2.her] submissive form, and with a little laugh, [npc.name] [npc.moansVerb],"
					+ " [npc.speech(Time to put your mouth to use!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction REQUEST_POSITION_KNEELING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.KNEELING_ORAL,
				Util.newArrayListOfValues(SexSlotBipeds.KNEELING_PERFORMING_ORAL),
				Util.newArrayListOfValues(SexSlotBipeds.KNEELING_RECEIVING_ORAL));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Kneel (R)";
		}
		@Override
		public String getActionDescription() {
			return "Drop down onto your knees in the hope that [npc2.name] wants you to perform oral on [npc2.herHim].<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "You quickly drop down to your knees in front of [npc2.name], shuffling forwards a little to bring your face closer to [npc2.her] groin.";
		}
		@Override
		public void applyEffects() {
			Sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_SELF_KNEELING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.KNEELING_ORAL,
				Util.newArrayListOfValues(SexSlotBipeds.KNEELING_PERFORMING_ORAL),
				Util.newArrayListOfValues(SexSlotBipeds.KNEELING_RECEIVING_ORAL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Kneel (give oral)";
		}
		@Override
		public String getActionDescription() {
			return "Get on your knees before [npc2.name], ready to service [npc2.herHim] with your mouth or [npc.hands].";
		}
		@Override
		public String getDescription() {
			return "Running [npc.her] [npc.hands] down [npc2.namePos] body, [npc.name] [npc.verb(drop)] to [npc.her] knees before [npc2.herHim], before looking up and flashing [npc2.herHim] a seductive smile."
					+ " With a little laugh, [npc.she] then [npc.moansVerb],"
					+ " [npc.speech(Stay still and enjoy this!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioningNew.setNewSexManager(data, false);
		}
	};

	public static final SexAction REQUEST_POSITION_SELF_KNEELING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.KNEELING_ORAL,
				Util.newArrayListOfValues(SexSlotBipeds.KNEELING_RECEIVING_ORAL),
				Util.newArrayListOfValues(SexSlotBipeds.KNEELING_PERFORMING_ORAL));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Receive kneeling oral (R)";
		}
		@Override
		public String getActionDescription() {
			return "Try and push [npc2.name] down onto [npc2.her] knees so that [npc2.she]'ll perform oral on you.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Lifting your [npc.arms], you take hold of [npc2.namePos] shoulders, and, with a little pressure, try to get [npc2.herHim] to kneel before you.";
		}
		@Override
		public void applyEffects() {
			Sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_SIXTY_NINE = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.SIXTY_NINE,
				Util.newArrayListOfValues(SexSlotBipeds.SIXTY_NINE_TOP),
				Util.newArrayListOfValues(SexSlotBipeds.SIXTY_NINE_BOTTOM));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Sixty-nine (top)";
		}
		@Override
		public String getActionDescription() {
			return "Push [npc2.name] down onto [npc2.her] back and straddle [npc2.her] face, in the sixty-nine position.";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] down onto [npc2.her] back."
					+ " Quickly lowering [npc.herself] down onto all fours over the top of [npc2.herHim],"
						+ " [npc.she] [npc.verb(drop)] [npc.her] crotch down over [npc2.her] face as [npc.she] similarly [npc.verb(position)] [npc.her] own head over [npc2.her] groin."
					+ " Looking back beneath [npc.herHim], [npc.name] [npc.moansVerb], "
					+ "[npc.speech(Good [npc2.girl]! Now let's have some fun!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction REQUEST_POSITION_SIXTY_NINE = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.SIXTY_NINE,
				Util.newArrayListOfValues(SexSlotBipeds.SIXTY_NINE_TOP),
				Util.newArrayListOfValues(SexSlotBipeds.SIXTY_NINE_BOTTOM));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Sixty-nine (top) (R)";
		}
		@Override
		public String getActionDescription() {
			return "Try and push [npc2.name] down onto [npc2.her] back so that you can sixty-nine with [npc2.herHim].<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Reaching up to take hold of [npc2.namePos] shoulders, you put on the most enticing look you can muster as you try to push [npc2.herHim] down, pleading,"
					+ " [npc.speech(I want to sixty-nine with you... Please!)]";
		}
		@Override
		public void applyEffects() {
			Sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_SIXTY_NINE_BOTTOM = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.SIXTY_NINE,
				Util.newArrayListOfValues(SexSlotBipeds.SIXTY_NINE_BOTTOM),
				Util.newArrayListOfValues(SexSlotBipeds.SIXTY_NINE_TOP));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false)
					&& (Sex.getCharacterPerformingAction().isPlayer()
						|| !Sex.isDom(Sex.getCharacterPerformingAction())
						|| Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_SUBMISSIVE).isPositive());
		}
		@Override
		public String getActionTitle() {
			return "Sixty-nine (bottom)";
		}
		@Override
		public String getActionDescription() {
			return "Lie down on your back and let [npc2.name] straddle your face, in the sixty-nine position.";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(tug)] [npc2.herHim] down on top of [npc.herHim] as [npc.she] [npc.verb(lie)] down on [npc.her] back."
					+ " Pulling [npc2.her] [npc2.hips] back so that [npc2.sheIs] in a reversed all-fours position over the top of [npc.herHim], [npc.name] [npc.verb(look)] up at [npc2.her] crotch hovering over [npc.her] face, and [npc.moansVerb],"
					+ " [npc.speech(Good [npc2.girl]! It's time for some oral fun!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction REQUEST_POSITION_SIXTY_NINE_BOTTOM = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.SIXTY_NINE,
				Util.newArrayListOfValues(SexSlotBipeds.SIXTY_NINE_BOTTOM),
				Util.newArrayListOfValues(SexSlotBipeds.SIXTY_NINE_TOP));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Sixty-nine (bottom) (R)";
		}
		@Override
		public String getActionDescription() {
			return "Lie down on your back and ask [npc2.name] to sixty-nine with you.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Sinking down to lie on your back, you put on the most enticing look you can muster as you plead, "
					+ "[npc.speech(I want to sixty-nine with you... Please!)]";
		}
		@Override
		public void applyEffects() {
			Sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_COW_GIRL_RIDING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.COWGIRL,
				Util.newArrayListOfValues(SexSlotBipeds.COWGIRL_RIDING),
				Util.newArrayListOfValues(SexSlotBipeds.COWGIRL_ON_BACK));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Cowgirl (riding)";
		}
		@Override
		public String getActionDescription() {
			return "Push [npc2.name] down onto [npc2.her] back and straddle [npc2.her] groin, in the cow-girl position.";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] down onto [npc2.her] back."
					+ " [npc.She] then lowers [npc.herself] down on top of [npc2.herHim], bringing [npc.her] crotch down to bump against [npc2.hers] as [npc.she] [npc.verb(straddle)] [npc2.herHim] in the cowgirl position."
					+ " Once [npc.sheHas] made [npc.herself] comfortable, [npc.she] [npc.verb(grin)] down at [npc2.name] and [npc.moansVerb],"
					+ " [npc.speech(Good [npc2.girl]! It's time to give you a ride!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioningNew.setNewSexManager(data, false);
		}
	};

	public static final SexAction REQUEST_POSITION_COWGIRL_RIDING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.COWGIRL,
				Util.newArrayListOfValues(SexSlotBipeds.COWGIRL_RIDING),
				Util.newArrayListOfValues(SexSlotBipeds.COWGIRL_ON_BACK));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Cowgirl (riding) (R)";
		}
		@Override
		public String getActionDescription() {
			return "Try to push [npc2.name] down onto [npc2.her] back so that you can straddle [npc2.herHim] in the cowgirl position.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Lifting your [npc.arms], you take hold of [npc2.namePos] shoulders, and with a little pressure, try to get [npc2.herHim] to drop down onto [npc2.her] back as you plead, "
					+ "[npc.speech(Let me ride you... Please!)]";
		}
		@Override
		public void applyEffects() {
			Sex.setPositionRequest(data);
		}
	};

	public static final SexAction POSITION_COW_GIRL_BOTTOM = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.COWGIRL,
				Util.newArrayListOfValues(SexSlotBipeds.COWGIRL_ON_BACK),
				Util.newArrayListOfValues(SexSlotBipeds.COWGIRL_RIDING));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Cowgirl (bottom)";
		}
		@Override
		public String getActionDescription() {
			return "Lie down onto your back and get [npc2.name] to straddle your groin, in the cow-girl position.";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(pull)] [npc2.herHim] down with [npc.herHim] as [npc.she] [npc.verb(lie)] down on [npc.her] back."
					+ " With a firm grip on [npc2.namePos] [npc2.hips], [npc.she] [npc.verb(push)] [npc2.herHim] back so that [npc2.sheIs] straddling [npc.herHim] in the cowgirl position."
					+ " Once [npc.sheHas] made [npc.herself] comfortable, [npc.she] [npc.verb(grin)] up at [npc2.name] and [npc.moansVerb],"
					+ " [npc.speech(Good [npc2.girl]! It's time for you to have a ride!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioningNew.setNewSexManager(data, false);
		}
	};

	public static final SexAction REQUEST_POSITION_COWGIRL_BOTTOM = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.COWGIRL,
				Util.newArrayListOfValues(SexSlotBipeds.COWGIRL_ON_BACK),
				Util.newArrayListOfValues(SexSlotBipeds.COWGIRL_RIDING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Cowgirl (bottom) (R)";
		}
		@Override
		public String getActionDescription() {
			return "Lie down on your back and ask [npc2.name] to straddle you in the cowgirl position.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Dropping down and quickly lying on your back, you put on the most enticing look you can muster as you plead, "
					+ "[npc.speech(Come and ride me... Please!)]";
		}
		@Override
		public void applyEffects() {
			Sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_SITTING_ON_FACE = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.FACE_SITTING,
				Util.newArrayListOfValues(SexSlotBipeds.FACE_SITTING_ON_FACE),
				Util.newArrayListOfValues(SexSlotBipeds.FACE_SITTING_ON_BACK));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Sit on face";
		}
		@Override
		public String getActionDescription() {
			return "Push [npc2.name] down onto [npc2.her] back and sit on [npc2.her] face.";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] down onto [npc2.her] back."
					+ " [npc.She] then [npc.verb(lower)] [npc.herself] down over the top of [npc2.herHim], such that [npc.her] crotch is hovering just above [npc2.her] [npc2.face]."
					+ " Once [npc.sheHas] made [npc.herself] comfortable, [npc.she] [npc.verb(allow)] [npc.her] [npc.legs] to give way, firmly planting [npc.her] groin down against [npc2.namePos] mouth.";
		}
		@Override
		public void applyEffects() {
			GenericPositioningNew.setNewSexManager(data, false);
		}
	};

	public static final SexAction REQUEST_POSITION_SITTING_ON_FACE = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.FACE_SITTING,
				Util.newArrayListOfValues(SexSlotBipeds.FACE_SITTING_ON_FACE),
				Util.newArrayListOfValues(SexSlotBipeds.FACE_SITTING_ON_BACK));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Sit on face (R)";
		}
		@Override
		public String getActionDescription() {
			return "Try to push [npc2.name] down onto [npc2.her] back so that you can sit on [npc2.her] face.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Reaching up to take hold of [npc2.namePos] shoulders, you try to push [npc2.herHim] down onto [npc2.her] back, pleading, "
					+ "[npc.speech(Please, let me sit on your face!)]";
		}
		@Override
		public void applyEffects() {
			Sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_FACESITTING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.FACE_SITTING,
				Util.newArrayListOfValues(SexSlotBipeds.FACE_SITTING_ON_BACK),
				Util.newArrayListOfValues(SexSlotBipeds.FACE_SITTING_ON_FACE));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Face sitting";
		}
		@Override
		public String getActionDescription() {
			return "Lie down on your back and get [npc2.name] to sit on your face.";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(pull)] [npc2.herHim] down with [npc.herHim] as [npc.she] [npc.verb(lie)] down on [npc.her] back."
					+ " Reaching around to grab [npc2.her] thighs, [npc.she] then [npc.verb(pull)] [npc2.herHim] down on top of [npc.herHim], so that [npc2.her] crotch is hovering just over [npc.her] [npc.face]."
					+ " At that moment, [npc2.namePos] [npc2.legs] suddenly give way, causing [npc2.herHim] to firmly plant [npc2.her] groin down against [npc.namePos] mouth.";
		}
		@Override
		public void applyEffects() {
			GenericPositioningNew.setNewSexManager(data, false);
		}
	};

	public static final SexAction REQUEST_POSITION_FACESITTING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.FACE_SITTING,
				Util.newArrayListOfValues(SexSlotBipeds.FACE_SITTING_ON_BACK),
				Util.newArrayListOfValues(SexSlotBipeds.FACE_SITTING_ON_FACE));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Face sitting (R)";
		}
		@Override
		public String getActionDescription() {
			return "Try to lie down on your back so that [npc2.name] can sit on your face.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] [npc2.hands], you move to lie down on your back, pleading, "
					+ "[npc.speech(Please, sit on my face!)]";
		}
		@Override
		public void applyEffects() {
			Sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_DOGGY = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.DOGGY_STYLE,
				Util.newArrayListOfValues(
						SexSlotBipeds.DOGGY_BEHIND,
						SexSlotBipeds.DOGGY_INFRONT),
				Util.newArrayListOfValues(
						SexSlotBipeds.DOGGY_ON_ALL_FOURS,
						SexSlotBipeds.DOGGY_ON_ALL_FOURS_SECOND));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Doggy-style";
		}
		@Override
		public String getActionDescription() {
			return "Push [npc2.name] down onto all fours and kneel behind [npc2.herHim]. (From this position, you can switch with [npc2.name], or drop down to perform oral on [npc2.herHim].)";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] down on all fours."
					+ " Stepping around behind [npc2.herHim], [npc.she] [npc.verb(drop)] down onto [npc.her] knees, shuffling forwards to grind [npc.her] crotch against [npc2.her] [npc2.ass+]."
					+ " Grabbing hold of [npc2.her] [npc2.hips+], [npc.she] [npc.moansVerb],"
					+ " [npc.speech(Good [npc2.girl]! Now hold still while I fuck you like the bitch you are!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction REQUEST_POSITION_DOM_FUCKED_DOGGY = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.DOGGY_STYLE,
				Util.newArrayListOfValues(
						SexSlotBipeds.DOGGY_BEHIND,
						SexSlotBipeds.DOGGY_INFRONT),
				Util.newArrayListOfValues(
						SexSlotBipeds.DOGGY_ON_ALL_FOURS,
						SexSlotBipeds.DOGGY_ON_ALL_FOURS_SECOND));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Doggy-style (R)";
		}
		@Override
		public String getActionDescription() {
			return "Try and get [npc2.name] to present [npc2.herself] in the hopes that [npc2.she] wants you to fuck [npc2.herHim], doggy-style.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Lifting your [npc.arms], you take hold of [npc2.namePos] shoulders, and with a little pressure, try to get [npc2.herHim] to drop down onto all fours so that you can fuck [npc2.herHim] doggy-style.";
		}
		@Override
		public void applyEffects() {
			Sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction DOM_POSITION_DOGGY_STYLED = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.DOGGY_STYLE,
				Util.newArrayListOfValues(
						SexSlotBipeds.DOGGY_ON_ALL_FOURS,
						SexSlotBipeds.DOGGY_ON_ALL_FOURS_SECOND),
				Util.newArrayListOfValues(
						SexSlotBipeds.DOGGY_BEHIND,
						SexSlotBipeds.DOGGY_INFRONT));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Doggy-styled";
		}
		@Override
		public String getActionDescription() {
			return "Push [npc2.name] down onto [npc2.her] knees and position yourself on all fours in front of [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] down onto [npc2.her] knees."
					+ " Turing around, [npc.she] then [npc.verb(drop)] down onto all fours, before shuffling back to grind [npc.her] [npc.ass+] against [npc2.her] crotch."
					+ " Reaching back and forcing [npc2.her] [npc2.hands] to take hold of [npc.her] [npc.hips+], [npc.she] [npc.moansVerb],"
					+ " [npc.speech(Come on! Fuck me like an animal!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioningNew.setNewSexManager(data, false);
		}
	};

	public static final SexAction REQUEST_POSITION_DOGGY_STYLED = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.DOGGY_STYLE,
				Util.newArrayListOfValues(
						SexSlotBipeds.DOGGY_ON_ALL_FOURS,
						SexSlotBipeds.DOGGY_ON_ALL_FOURS_SECOND),
				Util.newArrayListOfValues(
						SexSlotBipeds.DOGGY_BEHIND,
						SexSlotBipeds.DOGGY_INFRONT));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Doggy-styled (R)";
		}
		@Override
		public String getActionDescription() {
			return "Get down on all fours and present yourself in the hopes that [npc2.name] wants to fuck you, doggy-style.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Before [npc2.name] can react, you quickly drop down onto all fours, before shuffling around to present yourself to [npc2.herHim].";
		}
		@Override
		public void applyEffects() {
			Sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_DOGGY_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.DOGGY_STYLE,
				Util.newArrayListOfValues(
						SexSlotBipeds.DOGGY_BEHIND_ORAL,
						SexSlotBipeds.DOGGY_INFRONT),
				Util.newArrayListOfValues(
						SexSlotBipeds.DOGGY_ON_ALL_FOURS,
						SexSlotBipeds.DOGGY_ON_ALL_FOURS_SECOND));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Doggy-style (perform oral)";
		}
		@Override
		public String getActionDescription() {
			return "Push [npc2.name] down onto all fours and drop down behind [npc2.herHim], ready to perform oral on [npc2.herHim] in the doggy-style position.";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] down onto all fours."
					+ " Stepping around behind [npc2.herHim], [npc.she] similarly drops down onto [npc.her] [npc.hands] and knees, before shuffling forwards to bring [npc.her] [npc.face] up against [npc2.her] [npc2.ass+]."
					+ " Once [npc.sheIs] in position, [npc.she] [npc.moansVerb],"
					+ " [npc.speech(Good [npc2.girl]! Now hold still and enjoy this!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction REQUEST_POSITION_DOGGY_GIVE_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.DOGGY_STYLE,
				Util.newArrayListOfValues(
						SexSlotBipeds.DOGGY_BEHIND_ORAL,
						SexSlotBipeds.DOGGY_INFRONT),
				Util.newArrayListOfValues(
						SexSlotBipeds.DOGGY_ON_ALL_FOURS,
						SexSlotBipeds.DOGGY_ON_ALL_FOURS_SECOND));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Doggy-style give oral (R)";
		}
		@Override
		public String getActionDescription() {
			return "Get down on all fours and ask [npc2.name] to copy you, so that you can perform oral on [npc2.herHim] in the doggy-style position.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Before [npc2.name] can react, you quickly drop down onto all fours, before looking up and moaning,"
					+ " [npc.speech(Come on, get down on your [npc2.hands] and knees, and let me use my mouth on you!)]";
		}
		@Override
		public void applyEffects() {
			Sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_SELF_DOGGY_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.DOGGY_STYLE,
				Util.newArrayListOfValues(
						SexSlotBipeds.DOGGY_ON_ALL_FOURS,
						SexSlotBipeds.DOGGY_ON_ALL_FOURS_SECOND),
				Util.newArrayListOfValues(
						SexSlotBipeds.DOGGY_BEHIND_ORAL,
						SexSlotBipeds.DOGGY_INFRONT));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Doggy-style (receive oral)";
		}
		@Override
		public String getActionDescription() {
			return "Push [npc2.name] down onto all fours and drop down in front of [npc2.herHim], ready to receive oral from [npc2.herHim] in the doggy-style position.";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] down onto all fours."
					+ " Stepping around in front of [npc2.herHim], [npc.she] similarly drops down onto [npc.her] [npc.hands] and knees, before shuffling backwards to bring [npc.her] [npc.ass+] up against [npc2.her] [npc2.face+]."
					+ " Once [npc.sheIs] in position, [npc.she] [npc.moansVerb],"
					+ " [npc.speech(Good [npc2.girl]! Now put that tongue of yours to use!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction REQUEST_POSITION_DOGGY_RECEIVE_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.DOGGY_STYLE,
				Util.newArrayListOfValues(
						SexSlotBipeds.DOGGY_ON_ALL_FOURS,
						SexSlotBipeds.DOGGY_ON_ALL_FOURS_SECOND),
				Util.newArrayListOfValues(
						SexSlotBipeds.DOGGY_BEHIND_ORAL,
						SexSlotBipeds.DOGGY_INFRONT));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Doggy-style receive oral (R)";
		}
		@Override
		public String getActionDescription() {
			return "Get down on all fours and present yourself in the hopes that [npc2.name] wants to perform oral on you in the doggy-style position.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Before [npc2.name] can react, you quickly drop down onto all fours, before shuffling around to present yourself to [npc2.herHim].";
		}
		@Override
		public void applyEffects() {
			Sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_MATING_PRESS = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPositionBipeds.MATING_PRESS,
				Util.newArrayListOfValues(
						SexSlotBipeds.MATING_PRESS_TOP),
				Util.newArrayListOfValues(
						SexSlotBipeds.MATING_PRESS_BOTTOM));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getCharacterPerformingAction().hasPenis()
					&& checkBaseRequirements(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if(Sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& !Sex.getCharacterTargetedForSexAction(this).isVisiblyPregnant()
					&& Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isPositive()) {
				return SexActionPriority.HIGH;
			} else {
				return SexActionPriority.NORMAL;
			}
		}
		@Override
		public String getActionTitle() {
			return "Mating press";
		}
		@Override
		public String getActionDescription() {
			return "Force [npc2.name] down onto [npc2.her] back, push [npc2.her] [npc2.legs] apart and up towards [npc2.her] head, and then lie down on top of [npc2.herHim], in the 'mating press' position.";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] down onto the ground, forcing [npc2.herHim] to lie on [npc2.her] back."
					+ " Grabbing [npc2.her] [npc2.legs], [npc.name] [npc.verb(push)] them apart and back up towards [npc2.her] head, before lying down on top of [npc2.herHim] and bumping [npc.her] groin against [npc2.hers]."
					+ " Pinning [npc2.namePos] wrists to the floor on either side of [npc2.her] head, [npc.name] [npc.moansVerb],"
					+ " [npc.speech(It's time to"
					+ UtilText.parse(Sex.getCharacterTargetedForSexAction(this),
							"#IFnpc.isVisiblyPregnant() || !npc.hasVagina()#THEN give you a good hard fuck!#ELSE breed you!#ENDIF")
					+ ")]";
		}
		@Override
		public void applyEffects() {
			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction REQUEST_POSITION_MATING_PRESS = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPositionBipeds.MATING_PRESS,
				Util.newArrayListOfValues(
						SexSlotBipeds.MATING_PRESS_BOTTOM),
				Util.newArrayListOfValues(
						SexSlotBipeds.MATING_PRESS_TOP));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getCharacterTargetedForSexAction(this).hasPenis()
					&& checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Mating pressed (R)";
		}
		@Override
		public String getActionDescription() {
			return "Get down on your back and pull your [npc.legs] apart and back up towards your head, presenting yourself to be mounted and bred in the 'mating press' position.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Before [npc2.name] can react, you quickly drop down onto your back, before spreading your [npc.legs] and pulling them back up towards your head."
					+ " Presenting your groin to [npc2.name], you [npc.moansVerb],"
					+ " [npc.speech(Pin me down and"
					+ UtilText.parse(Sex.getCharacterPerformingAction(), "#IFnpc.isVisiblyPregnant() || !npc.hasVagina()#THEN fuck me!#ELSE breed me!#ENDIF")
					+ ")]";
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
							
			if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.MISSIONARY_ON_BACK) {
				if(isHappy) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							return "Much to your delight, [npc.name] allows [npc.herself] to be pushed down onto [npc.her] back, but as [npc.she] spreads [npc.her] [npc.legs] for you, [npc.she] growls in a menacing tone, "
									+ "[npc.speech(Don't get carried away, bitch! I'm still the one in charge here!)]";
						default:
							return "Much to your delight, [npc.name] allows [npc.herself] to be pushed down onto [npc.her] back, and as [npc.she] spreads [npc.her] [npc.legs] for you, [npc.she] [npc.moansVerb], "
									+ "[npc.speech(I like it when my partner shows a bit of initiative! Come take me!)]";
					}
				} else {
					return "Slapping your [pc.hands] away, [npc.name] pushes you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.MISSIONARY_KNEELING_BETWEEN_LEGS) {
				if(isHappy) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							return "Much to your delight, [npc.name] kneels down between your [pc.legs], and as [npc.she] grabs your [npc.legs] to push them apart, [npc.she] growls, "
									+ "[npc.speech(That's right, bitch! Spread your legs like the slut you are!)]";
						default:
							return "Much to your delight, [npc.name] kneels down between your [pc.legs], and as [npc.she] takes hold of your [npc.legs] to help push them apart, [npc.she] [npc.moansVerb], "
									+ "[npc.speech(Good idea! Spread your legs nice and wide, now!)]";
					}
				} else {
					return "Grabbing one of your [pc.arms], [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.FACE_TO_WALL_FACING_TARGET) {
				if(isHappy) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							return "Much to your delight, you feel [npc.name] reach down and roughly grab your hips, and, grinding [npc.herself] into your back, [npc.she] growls into your ear, "
									+ "[npc.speech(I love fucking bitches like you from behind! Now <i>stay still</i> like a good slut!)]";
						default:
							return "Much to your delight, you feel [npc.name] reach down to take hold of your hips, and as [npc.she] leans in over your shoulder, [npc.she] [npc.moans] into your ear, "
									+ "[npc.speech(Good [pc.girl]! This is gonna be fun!)]";
					}
				} else {
					return "Grabbing you by the shoulders, [npc.name] pulls you away from the wall, pushing you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.BACK_TO_WALL_FACING_TARGET) {
				if(isHappy) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							return "[npc.Name] grins as you try to entice [npc.herHim] to come over and fuck you against the wall."
									+ " Moving up to roughly grind [npc.her] body against yours, [npc.she] leans in over your shoulder and growls into your ear, "
									+ "[npc.speech(Good slut! Now <i>stay still</i> so I can give you a proper fucking!)]";
						default:
							return "[npc.Name] grins as you try to entice [npc.herHim] to come over and fuck you against the wall."
									+ " Moving up to press [npc.her] body against yours, [npc.she] leans in over your shoulder and [npc.moans] into your ear, "
									+ "[npc.speech(Good [pc.girl]! This is gonna be fun!)]";
					}
					
				} else {
					return "Grabbing you by the shoulders, [npc.name] pulls you away from the wall, pushing you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.DOGGY_BEHIND) {
				if(isHappy) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							return "Turning your head, you see [npc.name] drop down onto [npc.her] knees behind you."
									+ " Moving up to roughly grind [npc.her] groin against your [pc.ass], [npc.she] grabs hold of your [pc.hips+] before growling down at you, "
									+ "[npc.speech(That's right, present yourself like an obedient little bitch! Now <i>stay still</i> so I can give you a proper fucking!)]";
						default:
							return "Turning your head, you see [npc.name] drop down onto [npc.her] knees behind you."
									+ " Moving up to press [npc.her] groin against your [pc.ass], [npc.she] grabs hold of your [pc.hips+] before [npc.moaning] down at you, "
									+ "[npc.speech(Good [pc.girl]! This is gonna be fun!)]";
					}
					
				} else {
					return "Reaching down to grab you by the [pc.arm], [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.DOGGY_BEHIND_ORAL) {
				if(isHappy) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							return "Turning your head, you see [npc.name] drop down onto all fours behind you."
									+ " Moving [npc.her] [npc.face] up to your [pc.ass+], [npc.she] growls, "
									+ "[npc.speech(That's right, present yourself like an obedient little bitch! Now <i>stay still</i> and enjoy this!)]";
						default:
							return "Turning your head, you see [npc.name] drop down onto all fours behind you."
									+ " Moving [npc.her] [npc.face] up to your [pc.ass+], [npc.she] [npc.moans], "
									+ "[npc.speech(Good [pc.girl]! This is gonna be fun!)]";
					}
					
				} else {
					return "Reaching down to grab you by the [pc.arm], [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.DOGGY_ON_ALL_FOURS
					&& Sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotBipeds.DOGGY_BEHIND) {
				if(isHappy) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							return "A devious grin spreads across [npc.namePos] face as [npc.she] realises what it is you want."
									+ " Much to your delight, [npc.she] does exactly what you want, and drops down onto all fours in front of you, before shuffling back and rubbing [npc.her] [npc.ass+] against your crotch,"
									+ " [npc.speech(You want to fuck me, you little bitch?! Come on then, let's see if you've got what to takes to satisfy me!)]";
						default:
							return "A devious grin spreads across [npc.namePos] face as [npc.she] realises what it is you want."
									+ " Much to your delight, [npc.she] does exactly what you want, and drops down onto all fours in front of you, before shuffling back and rubbing [npc.her] [npc.ass+] against your crotch,"
									+ " [npc.speech(Come on then! This is what you wanted, isn't it?!)]";
					}
					
				} else {
					return "Pulling you back into your previous position, [npc.name] lets out an angry growl as [npc.she] shouts at you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.DOGGY_ON_ALL_FOURS
					&& Sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotBipeds.DOGGY_BEHIND_ORAL) {
				if(isHappy) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							return "A devious grin spreads across [npc.namePos] face as [npc.she] realises what it is you want."
									+ " Much to your delight, [npc.she] does exactly what you want, and drops down onto all fours in front of you, before shuffling back and rubbing [npc.her] [npc.ass+] against your [pc.face],"
									+ " [npc.speech(You want to use your mouth, you little bitch?! Come on then, let's see if you've got what to takes to satisfy me!)]";
						default:
							return "A devious grin spreads across [npc.namePos] face as [npc.she] realises what it is you want."
									+ " Much to your delight, [npc.she] does exactly what you want, and drops down onto all fours in front of you, before shuffling back and rubbing [npc.her] [npc.ass+] against your [pc.face],"
									+ " [npc.speech(Come on then! This is what you wanted, isn't it?!)]";
					}
					
				} else {
					return "Pulling you back into your previous position, [npc.name] lets out an angry growl as [npc.she] shouts at you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.KNEELING_RECEIVING_ORAL) {
				if(isHappy) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							return "[npc.Name] grins down at your submissive, kneeling form."
									+ " With a little laugh, [npc.she] grabs hold of your head with one [npc.hand], yanking you forwards into [npc.her] crotch as [npc.she] growls down at you, "
									+ "[npc.speech(You'd better be good at this, bitch! Now <i>stay still</i> while I use your mouth!)]";
						default:
							return "[npc.Name] grins down at your submissive, kneeling form."
									+ " With [npc.a_moan+], [npc.she] takes hold of your head with one [npc.hand], pulling you forwards into [npc.her] crotch as [npc.she] [npc.moansVerb] down at you, "
									+ "[npc.speech(Good [pc.girl]! This is gonna be fun!)]";
					}
					
				} else {
					return "Reaching down to grab you by the [pc.arm], [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.KNEELING_PERFORMING_ORAL) {
				if(isHappy) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							return "Reaching up and throwing your [pc.arms] off of [npc.her], [npc.name] lets out an angry snarl."
									+ " Surprisingly, [npc.she] then suddenly drops to [npc.her] knees, and you look down to see [npc.herHim] grinning up at you,"
									+ " [npc.speech(Luckily for you, this is what I planning all along! Now stay still bitch, you'd better appreciate this!)]";
						default:
							return "Reaching up to take hold of your [pc.arms], [npc.name] lets out a little laugh as [npc.she] allows you to push [npc.herHim] down onto [npc.her] knees."
									+ " Looking down, you see [npc.herHim] grinning up at you,"
									+ " [npc.speech(This is your lucky day! I love giving oral! You'd better appreciate this!)]";
					}
					
				} else {
					return "Reaching up and throwing your [pc.arms] off of [npc.her], [npc.name] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Do you really expect me to go down on you?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.SIXTY_NINE_TOP) {
				if(isHappy) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							return "Jumping down onto all fours, [npc.name] lowers [npc.herself] down over the top of you, bringing [npc.her] crotch down to your face as [npc.she] drops [npc.her] head down between your [pc.legs]."
									+ " Turning [npc.her] head back to look at you, [npc.she] growls, "
									+ "[npc.speech(Good idea slut! Now <i>stay still</i> so I can use you properly!)]";
						default:
							return "Jumping down onto all fours, [npc.name] lowers [npc.herself] down over the top of you, bringing [npc.her] crotch down to your face as [npc.she] drops [npc.her] head down between your [pc.legs]."
									+ " Turning [npc.her] head back to look at you, [npc.she] grins, "
									+ "[npc.speech(Good [pc.girl]! This is gonna be fun!)]";
					}
					
				} else {
					return "Reaching down to grab you by the [pc.arm], [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.SIXTY_NINE_BOTTOM) {
				if(isHappy) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							return "Allowing you to push [npc.herHim] down, [npc.name] reaches up and takes a firm grip on your [pc.hips], before roughly tugging you down so that you're over the top of [npc.herHim] in a reversed all-fours position."
									+ " Pulling your groin down to [npc.her] face, [npc.she] growls, "
									+ "[npc.speech(Good idea, slut! Now put your mouth to use!)]";
						default:
							return "Allowing you to push [npc.herHim] down, [npc.name] reaches up and takes a firm grip on your [pc.hips], before pulling you down so that you're over the top of [npc.herHim] in a reversed all-fours position."
									+ " Pulling your groin down to [npc.her] face, [npc.she] [npc.moansVerb], "
									+ "[npc.speech(Good [pc.girl]! This is gonna be fun!)]";
					}
					
				} else {
					return "Knocking your [pc.arms] away from [npc.her] shoulders, [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.COWGIRL_RIDING) {
				if(isHappy) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							return "Jumping down over the top of you, [npc.name] lowers [npc.herself] down over your groin, bringing [npc.her] crotch down to yours as [npc.she] straddles you in the cowgirl position."
									+ " Leaning forwards a little, [npc.she] growls down at you, "
									+ "[npc.speech(Good idea, slut! Now <i>stay still</i> so I can use you properly!)]";
						default:
							return "Jumping down over the top of you, [npc.name] lowers [npc.herself] down over your groin, bringing [npc.her] crotch down to yours as [npc.she] straddles you in the cowgirl position."
									+ " Leaning forwards a little, [npc.she] grins down at you, "
									+ "[npc.speech(Good [pc.girl]! This is gonna be fun!)]";
					}
					
				} else {
					return "Reaching down to grab you by the [pc.arm], [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.COWGIRL_ON_BACK) {
				if(isHappy) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							return "Allowing you to push [npc.herHim] down, [npc.name] reaches up and takes a firm grip on your [pc.hips], before roughly pulling you down so that you're straddling [npc.herHim] in the cowgirl position."
									+ " Looking up into your [pc.eyes], [npc.she] growls, "
									+ "[npc.speech(Good idea, slut! Now put some effort into it!)]";
						default:
							return "Allowing you to push [npc.herHim] down, [npc.name] reaches up and takes a firm grip on your [pc.hips], before pulling you down so that you're straddling [npc.herHim] in the cowgirl position."
									+ " Looking up into your [pc.eyes], [npc.she] [npc.moansVerb], "
									+ "[npc.speech(Good [pc.girl]! This is gonna be fun!)]";
					}
					
				} else {
					return "Knocking your [pc.arms] away from [npc.her] shoulders, [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.FACE_SITTING_ON_BACK) {
				if(isHappy) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							return "Slapping your [pc.hands] away from [npc.herHim], [npc.name] grabs you by the wrists and yanks you forwards, growling, "
									+ "[npc.speech(Don't you <i>dare</i> try to take control here! I'll let you sit on my face, but only because <i>I</i> want it, understood?!)]"
									+ "<br/>"
									+ "With that, [npc.she] quickly lies down on [npc.her] back, dragging you down with [npc.herHim]."
									+ " Despite [npc.namePos] angry words, you've ended up getting exactly what you wanted, and happily plant your groin down on [npc.her] face.";
						default:
							return "Smiling at you, [npc.name] responds, "
									+ "[npc.speech(That's a great idea! Oh, this is gonna be good!)]"
									+ "<br/>"
									+ "With that, [npc.she] quickly lies down on [npc.her] back, pulling you down with [npc.herHim]."
									+ " You let out a happy [pc.moan] as you find yourself getting exactly what you wanted, and eagerly plant your groin down on [npc.her] face.";
					}
					
				} else {
					return "Slapping your [pc.hands] away from [npc.herHim], [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.FACE_SITTING_ON_FACE) {
				if(isHappy) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							return "Jumping down over the top of you, [npc.name] lowers [npc.herself] down over you, bringing [npc.her] crotch down over your face before planting [npc.her] groin down onto your mouth."
									+ " Grinding roughly down against your face, [npc.she] growls, "
									+ "[npc.speech(Good idea slut! Now <i>stay still</i> so I can use you properly!)]";
						default:
							return "Jumping down over the top of you, [npc.name] lowers [npc.herself] down over you, bringing [npc.her] crotch down over your face before planting [npc.her] groin down onto your mouth."
									+ " Grinding down against your face, [npc.she] [npc.moans], "
									+ "[npc.speech(Good [pc.girl]! This is gonna be fun!)]";
					}
					
				} else {
					return "Reaching down to grab you by the [pc.arm], [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotBipeds.MATING_PRESS_TOP) {
				if(isHappy) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							return "With an eager, lustful grin on [npc.her] face, [npc.name] does just as you say, and drops [npc.her] full weight down on top of you."
									+ " Roughly grinding [npc.her] crotch against yours, [npc.she] pins your wrists to the floor on either side of your head and growls, "
									+ "[npc.speech(You filthy slut!"
									+ UtilText.parse(Sex.getCharacterTargetedForSexAction(this), "#IFnpc.isVisiblyPregnant() || !npc.hasVagina()#THEN I'm going to fuck you real good!#ELSE I'm going to breed you real good!#ENDIF")
									+ ")]";
						default:
							return "With an eager, lustful smile on [npc.her] face, [npc.name] does just as you say, and lies down over the top of you."
									+ " Desperately [npc.her] crotch against yours, [npc.she] pins your wrists to the floor on either side of your head and [npc.moansVerb], "
									+ "[npc.speech(Good [pc.girl]!"
									+ UtilText.parse(Sex.getCharacterTargetedForSexAction(this), "#IFnpc.isVisiblyPregnant() || !npc.hasVagina()#THEN I'm going to fuck you real good!#ELSE I'm going to breed you real good!#ENDIF")
									+ ")]";
					}
					
				} else {
					return "Reaching down to grab you by the [pc.arm], [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
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
				GenericPositioningNew.setNewSexManager(Sex.getPositionRequest(), true);
			}
			
			Sex.setPositionRequest(null);
		}
	};
	
}
