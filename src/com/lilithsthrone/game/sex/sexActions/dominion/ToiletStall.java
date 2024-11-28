package com.lilithsthrone.game.sex.sexActions.dominion;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.PositioningData;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericPositioning;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * Contains all positional changes for both sub and dom.
 * 
 * If sub, positional change is just a suggestion, which the NPC may refuse if they have other preferences.
 * 
 * @since 0.1.79
 * @version 0.4.9.5
 * @author Innoxia
 */
public class ToiletStall {
	
	private static boolean checkBaseRequirements(PositioningData data, boolean request) {
		return GenericPositioning.checkBaseRequirements(data, request);
//		return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
//				&& Main.sex.getInitialSexManager().getAllowedSexPositions().contains(data.getPosition())
//				&& !(Main.sex.getPosition() == data.getPosition()
//					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==data.getPerformerSlots().get(0)
//					&& Main.sex.getSexPositionSlot(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))==data.getPartnerSlots().get(0))
//				&& data.getPosition().getMaximumSlots()>=Main.sex.getTotalParticipantCount(false)
//				&& Main.sex.getTotalParticipantCount(false)==(data.getPerformerSlots().size()+data.getPartnerSlots().size())
//				&& (request
//						?Main.sex.getCharacterPerformingAction().isPlayer() && Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())!=SexControl.FULL
//						:(Main.sex.getCharacterPerformingAction().isPlayer()
//							?Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
//							:!Main.sex.isCharacterForbiddenByOthersFromPositioning(Main.sex.getCharacterPerformingAction())))
//				&& (!request && !Main.sex.getCharacterPerformingAction().isPlayer()
//						?((NPC) Main.sex.getCharacterPerformingAction()).isHappyToBeInSlot(data.getPosition(), data.getPerformerSlots().get(0), data.getPartnerSlots().get(0), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))
//						:true);
	}

	public static final SexAction PLAYER_POSITION_SWAP = new SexAction(
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
			return !Main.sex.getCharacterPerformingAction().equals(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getInitialSexManager().isSwapPositionAllowed(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))
//					&& Main.sex.getInitialSexManager().isPositionChangingAllowed(Main.sex.getCharacterPerformingAction()) // Should be covered in the method above
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getCharacterPerformingAction().isPlayer();
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
			Main.sex.swapSexPositionSlots(Main.game.getPlayer(), Main.sex.getCharacterTargetedForSexAction(this));
		}
	};

	public static final SexAction PLAYER_POSITION_FACE_TO_WALL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.AGAINST_WALL,
				Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL),
				Util.newArrayListOfValues(SexSlotAgainstWall.FACE_TO_WALL));
		
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
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] up against the wall of the toilet stall."
					+ " Grinding [npc.her] body up against [npc2.her] back, [npc.she] [npc.moansVerb] into [npc2.her] [npc2.ear],"
					+ " [npc.speech(Good [npc2.girl]! Now hold still while I fuck you!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction PLAYER_POSITION_FACE_TO_WALL_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.AGAINST_WALL,
				Util.newArrayListOfValues(SexSlotAgainstWall.FACE_TO_WALL),
				Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Face-to-wall";
		}
		@Override
		public String getActionDescription() {
			return "Try and move into a position so that you're facing a wall of the toilet stall.";
		}
		@Override
		public String getDescription() {
			return "Before [npc2.name] can react, you quickly move up against one wall of the toilet stall."
					+ " Placing your hands up against the graffiti-covered surface that's now in front of you, you push your [npc.ass+] out, shaking it at [npc2.name] as you try to encourage [npc2.herHim] to fuck you like this.";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction PLAYER_POSITION_BACK_TO_WALL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.AGAINST_WALL,
				Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL),
				Util.newArrayListOfValues(SexSlotAgainstWall.BACK_TO_WALL));
		
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
			return "Push [npc2.name] back against one of the toilet stall's walls.";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] back against one of the toilet stall's walls."
					+ " Grinding [npc.her] body up against [npc2.hers], [npc.she] [npc.moansVerb] into [npc2.her] [npc2.ear], "
					+ "[npc.speech(Good [npc2.girl]! Now hold still while I fuck you!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction PLAYER_POSITION_BACK_TO_WALL_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.AGAINST_WALL,
				Util.newArrayListOfValues(SexSlotAgainstWall.BACK_TO_WALL),
				Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Back-to-wall";
		}
		@Override
		public String getActionDescription() {
			return "Try and move into a position so that your back is up against one of the toilet stall's walls.";
		}
		@Override
		public String getDescription() {
			return "Before [npc2.name] can react, you quickly move up against the wall of the toilet stall."
					+ " Leaning back against the graffiti-covered surface that's now behind you, you give [npc2.name] your most seductive look, trying to encourage [npc2.herHim] to fuck you like this.";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction PLAYER_POSITION_KNEELING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.STANDING,
				Util.newArrayListOfValues(SexSlotStanding.STANDING_DOMINANT),
				Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL));

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
			GenericPositioning.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction PLAYER_POSITION_KNEELING_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.STANDING,
				Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL),
				Util.newArrayListOfValues(SexSlotStanding.STANDING_DOMINANT));

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
			return "Drop down onto your knees in the hope that [npc2.name] wants you to perform oral on [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			return "You quickly drop down to your knees in front of [npc2.name], shuffling forwards a little to bring your face closer to [npc2.her] groin.";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction PLAYER_FORCE_POSITION_SELF_KNEELING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.STANDING,
				Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL),
				Util.newArrayListOfValues(SexSlotStanding.STANDING_DOMINANT));
		
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
			GenericPositioning.setNewSexManager(data, false);
		}
	};

	public static final SexAction PLAYER_POSITION_REQUEST_SELF_KNEELING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.STANDING,
				Util.newArrayListOfValues(SexSlotStanding.STANDING_DOMINANT),
				Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Receive kneeling oral";
		}
		@Override
		public String getActionDescription() {
			return "Try and push [npc2.name] down onto [npc2.her] knees so that [npc2.she]'ll perform oral on you.";
		}
		@Override
		public String getDescription() {
			return "Lifting your [npc.arms], you take hold of [npc2.namePos] shoulders, and, with a little pressure, try to get [npc2.herHim] to kneel before you.";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
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
			return Main.sex.getPositionRequest()!=null
					&& !Main.sex.getCharacterPerformingAction().isPlayer();
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
			boolean isHappy = ((NPC)Main.sex.getCharacterPerformingAction()).isHappyToBeInSlot(
					Main.sex.getPositionRequest().getPosition(),
					Main.sex.getPositionRequest().getPartnerSlots().get(0),
					Main.sex.getPositionRequest().getPerformerSlots().get(0),
					Main.game.getPlayer());
			
			if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotAgainstWall.STANDING_WALL) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
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
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotAgainstWall.STANDING_WALL) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
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
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotStanding.STANDING_DOMINANT) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
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
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotStanding.PERFORMING_ORAL) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
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
				
			} 
			
			return "";
		}

		@Override
		public void applyEffects() {
			if(((NPC)Main.sex.getCharacterPerformingAction()).isHappyToBeInSlot(
					Main.sex.getPositionRequest().getPosition(),
					Main.sex.getPositionRequest().getPartnerSlots().get(0),
					Main.sex.getPositionRequest().getPerformerSlots().get(0),
					Main.game.getPlayer())) {
				GenericPositioning.setNewSexManager(Main.sex.getPositionRequest(), true);
			}
			
			Main.sex.setPositionRequest(null);
		}
	};
	
}
