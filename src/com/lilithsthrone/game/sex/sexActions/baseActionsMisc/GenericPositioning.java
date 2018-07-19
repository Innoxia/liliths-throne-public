package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.HashMap;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.universal.SMBackToWall;
import com.lilithsthrone.game.sex.managers.universal.SMCowgirl;
import com.lilithsthrone.game.sex.managers.universal.SMDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMFaceSitting;
import com.lilithsthrone.game.sex.managers.universal.SMFaceToWall;
import com.lilithsthrone.game.sex.managers.universal.SMKneeling;
import com.lilithsthrone.game.sex.managers.universal.SMMissionary;
import com.lilithsthrone.game.sex.managers.universal.SMSixtyNine;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * Contains all positional changes for both sub and dom.
 * 
 * If sub, positional change is just a suggestion, which the NPC may refuse if they have other preferences.
 * 
 * 
 * @since 0.1.79
 * @version 0.2.9
 * @author Innoxia
 */
public class GenericPositioning {

	public static final SexAction PLAYER_POSITION_SWAP = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& Sex.getSexManager().isPlayerAbleToSwapPositions()
					&& (Sex.isDom(Main.game.getPlayer()) || Sex.isSubHasEqualControl())
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
	
	public static final SexAction PLAYER_POSITION_MISSIONARY = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !(Sex.getPosition() == SexPositionType.MISSIONARY && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS)
					&& SexPositionType.MISSIONARY.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
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
			return "Taking hold of [npc2.namePos] shoulders, you push [npc2.herHim] down onto [npc2.her] back."
					+ " Kneeling down between [npc2.her] [npc2.legs], you [npc.moan] as you look down into [npc2.her] [npc2.eyes+], "
					+ "[npc.speech(That's right, spread your legs for me...)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMMissionary(
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS)),
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.MISSIONARY_ON_BACK))));
			
//			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};
	
	
	public static final SexAction PLAYER_POSITION_MISSIONARY_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedMissionary
					&& !(Sex.getPosition() == SexPositionType.MISSIONARY && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS)
					&& SexPositionType.MISSIONARY.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& !Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Missionary";
		}

		@Override
		public String getActionDescription() {
			return "Try to get [npc2.name] to lie down on [npc2.her] back and spread [npc2.her] [npc2.legs] so that you can have sex with [npc2.herHim] in the missionary position.";
		}

		@Override
		public String getDescription() {
			return "You reach up to take hold of [npc2.namePos] shoulders, and, pushing down, you try to get [npc2.herHim] to lie down on [npc2.her] back.";
		}

		@Override
		public void applyEffects() {
			SexFlags.requestedMissionary = true;
		}
	};
	
	public static final SexAction PLAYER_POSITION_MISSIONARY_ON_BACK = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !(Sex.getPosition() == SexPositionType.MISSIONARY && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.MISSIONARY_ON_BACK)
					&& SexPositionType.MISSIONARY.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
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
			return "Taking hold of [npc2.namePos] shoulders, you push [npc2.herHim] down onto [npc2.her] knees."
					+ " Kneeling down before [npc2.herHim], you then lie down onto your back, spreading your [npc.legs] and looking up into [npc2.her] [npc2.eyes+] as you [npc.moanVerb], "
					+ "[npc.speech(Come and take me!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMMissionary(
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MISSIONARY_ON_BACK)),
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS))));
			
//			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};
	
	
	public static final SexAction PLAYER_POSITION_MISSIONARY_ON_BACK_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedMissionaryOnBack
					&& !(Sex.getPosition() == SexPositionType.MISSIONARY && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.MISSIONARY_ON_BACK)
					&& SexPositionType.MISSIONARY.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& !Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Missionary (on back)";
		}

		@Override
		public String getActionDescription() {
			return "Lie down on your back and spread your [npc.legs] to try and encourage [npc2.name] to have sex with you in the missionary position.";
		}

		@Override
		public String getDescription() {
			return "You lie down on your back before [npc2.name], letting out a little [npc.moan] as you spread your [npc.legs] to try and encourage [npc2.name] to have sex with you in the missionary position.";
		}

		@Override
		public void applyEffects() {
			SexFlags.requestedMissionaryOnBack = true;
		}
	};
	
	
	
	public static final SexAction PLAYER_POSITION_FACE_TO_WALL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !(Sex.getPosition() == SexPositionType.FACING_WALL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.FACE_TO_WALL_FACING_TARGET)
					&& SexPositionType.FACING_WALL.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
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
			return "Taking hold of [npc2.namePos] shoulders, you push [npc2.herHim] up against a nearby wall."
					+ " Grinding your body up against [npc2.her] back, you [npc.moan] into [npc2.her] [npc2.ear], "
					+ "[npc.speech(Be a good [npc2.girl] and hold still while I fuck you!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMFaceToWall(
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.FACE_TO_WALL_FACING_TARGET)),
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.FACE_TO_WALL_AGAINST_WALL))));
			
//			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};
	
	
	public static final SexAction PLAYER_POSITION_FACE_TO_WALL_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedFaceToWall
					&& !(Sex.getPosition() == SexPositionType.FACING_WALL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.FACE_TO_WALL_FACING_TARGET)
					&& SexPositionType.FACING_WALL.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& !Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Face-to-wall";
		}

		@Override
		public String getActionDescription() {
			return "Try and move into a position so that you're facing a nearby wall.";
		}

		@Override
		public String getDescription() {
			return "Before [npc2.name] can react, you quickly move up against a nearby wall."
					+ " Placing your hands up against the solid surface that's now in front of you, you push your [npc.ass+] out, shaking it at [npc2.name] as you try to encourage [npc2.herHim] to fuck you like this.";
		}

		@Override
		public void applyEffects() {
			SexFlags.requestedFaceToWall = true;
		}
	};
	
	
	public static final SexAction PLAYER_POSITION_BACK_TO_WALL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !(Sex.getPosition() == SexPositionType.BACK_TO_WALL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.BACK_TO_WALL_FACING_TARGET)
					&& SexPositionType.BACK_TO_WALL.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
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
			return "Taking hold of [npc2.namePos] shoulders, you push [npc2.herHim] back against a nearby wall."
					+ " Grinding your body up against [npc2.hers], you [npc.moan] into [npc2.her] [npc2.ear], "
					+ "[npc.speech(Be a good [npc2.girl] and hold still while I fuck you!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMBackToWall(
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.BACK_TO_WALL_FACING_TARGET)),
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.BACK_TO_WALL_AGAINST_WALL))));
			
//			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PLAYER_POSITION_BACK_TO_WALL_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedBackToWall
					&& !(Sex.getPosition() == SexPositionType.BACK_TO_WALL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.BACK_TO_WALL_FACING_TARGET)
					&& SexPositionType.BACK_TO_WALL.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& !Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Back-to-wall";
		}

		@Override
		public String getActionDescription() {
			return "Try and move into a position so that your back is up against a nearby wall.";
		}

		@Override
		public String getDescription() {
			return "Before [npc2.name] can react, you quickly move up against a nearby wall."
					+ " Leaning back against the solid surface that's now behind you, you give [npc2.name] your most seductive look, trying to encourage [npc2.herHim] to fuck you like this.";
		}

		@Override
		public void applyEffects() {
			SexFlags.requestedBackToWall = true;
		}
	};
	
	public static final SexAction PLAYER_POSITION_KNEELING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !(Sex.getPosition() == SexPositionType.KNEELING_ORAL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.KNEELING_PERFORMING_ORAL)
					&& SexPositionType.KNEELING_ORAL.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
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
			return "Taking hold of [npc2.namePos] shoulders, you quite quickly force [npc2.herHim] to [npc2.her] knees before you."
					+ " Grinning down at [npc2.her] submissive form, you [npc.moan], "
					+ "[npc.speech(Time to put your mouth to use!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMKneeling(
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.KNEELING_RECEIVING_ORAL)),
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.KNEELING_PERFORMING_ORAL))));
			
//			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PLAYER_POSITION_KNEELING_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedKneeling
					&& !(Sex.getPosition() == SexPositionType.KNEELING_ORAL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.KNEELING_RECEIVING_ORAL)
					&& SexPositionType.KNEELING_ORAL.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& !Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
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
			SexFlags.requestedKneeling = true;
		}
	};
	
	public static final SexAction PLAYER_FORCE_POSITION_SELF_KNEELING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !(Sex.getPosition() == SexPositionType.KNEELING_ORAL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.KNEELING_RECEIVING_ORAL)
					&& SexPositionType.KNEELING_ORAL.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getActionTitle() {
			return "Kneel (give oral)";
		}

		@Override
		public String getActionDescription() {
			return "Get on your knees.";
		}

		@Override
		public String getDescription() {
			return "Smiling, you slowly slide down to your knees in front of [npc2.name].";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMKneeling(
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.KNEELING_PERFORMING_ORAL)),
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.KNEELING_RECEIVING_ORAL))));

//			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};

	public static final SexAction PLAYER_POSITION_REQUEST_SELF_KNEELING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedSelfKneeling
					&& !(Sex.getPosition() == SexPositionType.KNEELING_ORAL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.KNEELING_PERFORMING_ORAL)
					&& SexPositionType.KNEELING_ORAL.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& !Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
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
			SexFlags.requestedSelfKneeling = true;
		}
	};
	
	public static final SexAction PLAYER_POSITION_SIXTY_NINE = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !(Sex.getPosition() == SexPositionType.SIXTY_NINE && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.SIXTY_NINE_BOTTOM)
					&& SexPositionType.SIXTY_NINE.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Sixty-nine";
		}

		@Override
		public String getActionDescription() {
			return "Push [npc2.name] down onto [npc2.her] back and straddle [npc2.her] face, in the sixty-nine position.";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, you push [npc2.herHim] down onto [npc2.her] back."
					+ " You then lower yourself down onto all fours over the top of [npc2.herHim], lowering your crotch down to [npc2.her] face as you similarly position your own head over [npc2.her] groin."
					+ " Looking back beneath you, you [npc.moan], "
					+ "[npc.speech(Good [npc2.girl]! Now let's have some fun!)]";
			
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMSixtyNine(
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.SIXTY_NINE_TOP)),
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.SIXTY_NINE_BOTTOM))));
			
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PLAYER_POSITION_SIXTY_NINE_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requested69
					&& !(Sex.getPosition() == SexPositionType.SIXTY_NINE && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.SIXTY_NINE_TOP)
					&& SexPositionType.SIXTY_NINE.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& !Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Sixty-nine";
		}

		@Override
		public String getActionDescription() {
			return "Lie down on your back and ask [npc2.name] to sixty-nine with you.";
		}

		@Override
		public String getDescription() {
			return "Sinking down to lie on your back, you put on the most enticing look you can muster as you plead, "
					+ "[npc.speech(I want to sixty-nine with you... Please!)]";
		}

		@Override
		public void applyEffects() {
			SexFlags.requested69 = true;
		}
	};
	
	public static final SexAction PLAYER_FORCE_POSITION_COW_GIRL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !(Sex.getPosition() == SexPositionType.COWGIRL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.COWGIRL_ON_BACK)
					&& SexPositionType.COWGIRL.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Cowgirl";
		}

		@Override
		public String getActionDescription() {
			return "Push [npc2.name] down onto [npc2.her] back and straddle [npc2.her] groin, in the cow-girl position.";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, you push [npc2.herHim] down onto [npc2.her] back."
					+ " You then lower yourself down on top of [npc2.herHim], bringing your crotch down to [npc2.hers] as you straddle [npc2.herHim] in the cowgirl position."
					+ " Once you've made yourself comfortable, you grin down at [npc2.name], "
					+ "[npc.speech(Good [npc2.girl]! Now let's have some fun!)]";
			
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMCowgirl(
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.COWGIRL_RIDING)),
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.COWGIRL_ON_BACK))));
			
			SexFlags.resetRequests();
		}
	};

	public static final SexAction PLAYER_POSITION_REQUEST_COWGIRL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedCowgirl
					&& !(Sex.getPosition() == SexPositionType.COWGIRL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.COWGIRL_RIDING)
					&& SexPositionType.COWGIRL.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& !Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Cowgirl";
		}

		@Override
		public String getActionDescription() {
			return "Lie down on your back and ask [npc2.name] to straddle you in the cowgirl position.";
		}

		@Override
		public String getDescription() {
			return "Dropping down and quickly lying on your back, you put on the most enticing look you can muster as you plead, "
					+ "[npc.speech(Come and ride me... Please!)]";
		}

		@Override
		public void applyEffects() {
			SexFlags.requestedCowgirl = true;
		}
	};

	public static final SexAction PLAYER_FORCE_POSITION_SITTING_ON_FACE = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !(Sex.getPosition() == SexPositionType.FACE_SITTING && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.FACE_SITTING_ON_BACK)
					&& SexPositionType.FACE_SITTING.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Sit on Face";
		}

		@Override
		public String getActionDescription() {
			return "Push [npc2.name] down onto [npc2.her] back and sit on [npc2.her] face.";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, you push [npc2.herHim] down onto [npc2.her] back."
					+ " You then lower yourself down on top of [npc2.herHim], bringing your crotch down over [npc2.her] face."
					+ " Once you've made yourself comfortable, you allow your legs to give way, firmly planting your groin down against [npc2.namePos] mouth.";
			
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMFaceSitting(
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.FACE_SITTING_ON_FACE)),
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.FACE_SITTING_ON_BACK))));
			
			SexFlags.resetRequests();
		}
	};

	public static final SexAction PLAYER_POSITION_REQUEST_SITTING_ON_FACE = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedSitOnFace
					&& !(Sex.getPosition() == SexPositionType.FACE_SITTING && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.FACE_SITTING_ON_BACK)
					&& SexPositionType.FACE_SITTING.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& !Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Sit on Face";
		}

		@Override
		public String getActionDescription() {
			return "Try to push [npc2.name] down onto [npc2.her] back so that you can sit on [npc2.her] face.";
		}

		@Override
		public String getDescription() {
			return "Reaching up to take hold of [npc2.namePos] shoulders, you try to push [npc2.herHim] down onto [npc2.her] back, pleading, "
					+ "[npc.speech(Please, let me sit on your face!)]";
		}

		@Override
		public void applyEffects() {
			SexFlags.requestedSitOnFace = true;
		}
	};
	

	public static final SexAction PLAYER_FORCE_POSITION_FACESITTING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !(Sex.getPosition() == SexPositionType.FACE_SITTING && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.FACE_SITTING_ON_FACE)
					&& SexPositionType.FACE_SITTING.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Face Sitting";
		}

		@Override
		public String getActionDescription() {
			return "Lie down on your back and get [npc2.name] to sit on your face.";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] [npc2.arms], you pull [npc2.herHim] down with you as you lie down on your back."
					+ " Reaching around to grab [npc2.her] thighs, you pull [npc2.herHim] down on top of you, bringing [npc2.her] crotch down over your face."
					+ " [npc2.NamePos] [npc2.legs] suddenly give way, causing [npc2.herHim] to firmly plant [npc2.her] groin down against your mouth.";
			
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMFaceSitting(
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.FACE_SITTING_ON_BACK)),
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.FACE_SITTING_ON_FACE))));
			
			SexFlags.resetRequests();
		}
	};

	public static final SexAction PLAYER_POSITION_REQUEST_FACESITTING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedFaceSitting
					&& !(Sex.getPosition() == SexPositionType.FACE_SITTING && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.FACE_SITTING_ON_FACE)
					&& SexPositionType.FACE_SITTING.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& !Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Face Sitting";
		}

		@Override
		public String getActionDescription() {
			return "Try to lie down on your back so that [npc2.name] can sit on your face.";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] [npc2.hands], you move to lie down on your back, pleading, "
					+ "[npc.speech(Please, sit on my face!)]";
		}

		@Override
		public void applyEffects() {
			SexFlags.requestedFaceSitting = true;
		}
	};
	
	public static final SexAction PLAYER_POSITION_DOGGY = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !(Sex.getPosition() == SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.DOGGY_BEHIND)
					&& SexPositionType.DOGGY_STYLE.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
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
			return "Taking hold of [npc2.namePos] shoulders, you push [npc2.herHim] down on all fours."
					+ " Stepping around behind [npc2.herHim], you drop down onto your knees, shuffling forwards to grind your crotch against [npc2.her] [npc2.ass+]."
					+ " Grabbing hold of [npc2.her] [npc2.hips+], you [npc.moan], "
					+ "[npc.speech(Be a good [npc2.girl] and hold still while I fuck you like the bitch you are!)]";
		}
	
		@Override
		public void applyEffects() {
			HashMap<GameCharacter, SexPositionSlot> submissives = new HashMap<>();
			for(GameCharacter participant : Sex.getSubmissiveParticipants().keySet()) {
				if(Sex.getActivePartner().equals(participant)) {
					submissives.put(participant, SexPositionSlot.DOGGY_ON_ALL_FOURS);
				} else {
					submissives.put(participant, SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND);
				}
			}
			
			Sex.setSexManager(new SMDoggy(
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND)),
					submissives));
			
//				SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();

		}
	};

	public static final SexAction PLAYER_POSITION_REQUEST_DOGGY = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedDoggy
					&& !(Sex.getPosition() == SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.DOGGY_BEHIND)
					&& SexPositionType.DOGGY_STYLE.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& !Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Doggy-style";
		}

		@Override
		public String getActionDescription() {
			return "Get down on all fours and present yourself in the hopes that [npc2.name] wants to fuck you, doggy-style.";
		}

		@Override
		public String getDescription() {
			return "Before [npc2.name] can react, you quickly drop down onto all fours, before shuffling around to present yourself to [npc2.herHim].";
		}

		@Override
		public void applyEffects() {
			SexFlags.requestedDoggy = true;
		}
	};
	
	
	
	public static final SexAction PLAYER_DOM_POSITION_SELF_DOGGY = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !(Sex.getPosition() == SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.DOGGY_ON_ALL_FOURS)
					&& SexPositionType.DOGGY_STYLE.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Doggy-style (self)";
		}

		@Override
		public String getActionDescription() {
			return "Push [npc2.name] down onto [npc2.her] knees and position yourself on all fours in front of [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, you push [npc2.herHim] towards the ground, making [npc2.herHim] kneel."
					+ " You move in front of [npc2.herHim] and position yourself on all fours, suggestively shaking your [npc.hips] and softly tracing your finger over your [npc.ass] to entice [npc2.herHim]. "
					+ "[npc.speech(Get to work, [npc2.girl]!)]";
		}

		@Override
		public void applyEffects() {
			HashMap<GameCharacter, SexPositionSlot> submissives = new HashMap<>();
			for(GameCharacter participant : Sex.getSubmissiveParticipants().keySet()) {
				if(Sex.getActivePartner().equals(participant)) {
					submissives.put(participant , SexPositionSlot.DOGGY_BEHIND);
				} else {
					submissives.put(participant , SexPositionSlot.DOGGY_INFRONT);
				}
			}
			Sex.setSexManager(new SMDoggy(
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS)),
					submissives));
			
//			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};
	
	
	
	
	
	public static final SexAction PLAYER_FORCE_POSITION_DOGGY_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !(Sex.getPosition() == SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.DOGGY_BEHIND_ORAL)
					&& SexPositionType.DOGGY_STYLE.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
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
			return "Taking hold of [npc2.namePos] shoulders, you push [npc2.herHim] down on all fours."
					+ " Stepping around behind [npc2.herHim], you drop down onto all fours yourself, crawling forwards to your face up against [npc2.her] [npc2.ass+]."
					+ " Grinning at the sight in front of you, you [npc.moan], "
					+ "[npc.speech(Be a good [npc2.girl] and enjoy this!)]";
		}

		@Override
		public void applyEffects() {
			HashMap<GameCharacter, SexPositionSlot> submissives = new HashMap<>();
			for(GameCharacter participant : Sex.getSubmissiveParticipants().keySet()) {
				if(Sex.getActivePartner().equals(participant)) {
					submissives.put(participant , SexPositionSlot.DOGGY_ON_ALL_FOURS);
				} else {
					submissives.put(participant , SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND);
				}
			}
			Sex.setSexManager(new SMDoggy(
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND_ORAL)),
					submissives));
			
//			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PLAYER_FORCE_POSITION_SELF_DOGGY_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !(Sex.getPosition() == SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.DOGGY_BEHIND_ORAL)
					&& SexPositionType.DOGGY_STYLE.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
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
			return "Taking hold of [npc2.namePos] shoulders, you push [npc2.herHim] down on all fours."
					+ " Stepping around in front of [npc2.herHim], you drop down onto all fours yourself, shuffling backwards to press your [npc.ass+] up against [npc2.her] [npc2.face+]."
					+ " Looking back over your shoulder, you [npc.moan], "
					+ "[npc.speech(Be a good [npc2.girl] and put your tongue to use!)]";
		}

		@Override
		public void applyEffects() {
			HashMap<GameCharacter, SexPositionSlot> submissives = new HashMap<>();
			for(GameCharacter participant : Sex.getSubmissiveParticipants().keySet()) {
				if(Sex.getActivePartner().equals(participant)) {
					submissives.put(participant , SexPositionSlot.DOGGY_BEHIND_ORAL);
				} else {
					submissives.put(participant , SexPositionSlot.DOGGY_INFRONT);
				}
			}
			Sex.setSexManager(new SMDoggy(
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS)),
					submissives));
			
//			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PLAYER_POSITION_REQUEST_DOGGY_RECEIVE_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedDoggyReceiveOral
					&& !(Sex.getPosition() == SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.DOGGY_BEHIND_ORAL)
					&& SexPositionType.DOGGY_STYLE.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& !Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Doggy-style receive oral";
		}

		@Override
		public String getActionDescription() {
			return "Get down on all fours and present yourself in the hopes that [npc2.name] wants to perform oral on you in the doggy-style position.";
		}

		@Override
		public String getDescription() {
			return "Before [npc2.name] can react, you quickly drop down onto all fours, before shuffling around to present yourself to [npc2.herHim].";
		}

		@Override
		public void applyEffects() {
			SexFlags.requestedDoggyReceiveOral = true;
		}
	};
	
	public static final SexAction PLAYER_POSITION_REQUEST_DOM_FUCKED_DOGGY = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedDomFuckedDoggy
					&& !(Sex.getPosition() == SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.DOGGY_ON_ALL_FOURS)
					&& SexPositionType.DOGGY_STYLE.getMaximumSlots()>=Sex.getTotalParticipantCount(false) && Sex.getTotalParticipantCount(false)==2
					&& !Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Doggy-style [npc2.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Try and get [npc2.name] to present [npc2.herself] in the hopes that [npc2.she] wants you to fuck [npc2.herHim], doggy-style.";
		}

		@Override
		public String getDescription() {
			return "Lifting your [npc.arms], you take hold of [npc2.namePos] shoulders, and with a little pressure, try to get [npc2.herHim] to drop down onto all fours so that you can fuck [npc2.herHim] doggy-style.";
		}

		@Override
		public void applyEffects() {
			SexFlags.requestedDomFuckedDoggy = true;
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
			return (SexFlags.requestedCowgirl
					|| SexFlags.requested69
					|| SexFlags.requestedDoggy
					|| SexFlags.requestedDoggyOral
					|| SexFlags.requestedDoggyReceiveOral
					|| SexFlags.requestedDomFuckedDoggy
					|| SexFlags.requestedBackToWall
					|| SexFlags.requestedFaceToWall
					|| SexFlags.requestedKneeling
					|| SexFlags.requestedSelfKneeling
					|| SexFlags.requestedMissionary
					|| SexFlags.requestedMissionaryOnBack
					|| SexFlags.requestedSitOnFace
					|| SexFlags.requestedFaceSitting)
					&& !Sex.isDom(Main.game.getPlayer())
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

			Set<SexPositionSlot> positionPreferences = Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this));
			
			if(SexFlags.requestedMissionary) {
				if(positionPreferences.contains(SexPositionSlot.MISSIONARY_ON_BACK) || positionPreferences.isEmpty()) {
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
				
			} else if(SexFlags.requestedMissionaryOnBack) {
				if(positionPreferences.contains(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS) || positionPreferences.isEmpty()) {
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
				
			} else if(SexFlags.requestedFaceToWall) {
				if(positionPreferences.contains(SexPositionSlot.FACE_TO_WALL_FACING_TARGET) || positionPreferences.isEmpty()) {
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
				
			} else if(SexFlags.requestedBackToWall) {
				if(positionPreferences.contains(SexPositionSlot.BACK_TO_WALL_FACING_TARGET) || positionPreferences.isEmpty()) {
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
				
			} else if(SexFlags.requestedDoggy) {
				if(positionPreferences.contains(SexPositionSlot.DOGGY_BEHIND) || positionPreferences.isEmpty()) {
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
				
			} else if(SexFlags.requestedDoggyOral) {
				if(positionPreferences.contains(SexPositionSlot.DOGGY_ON_ALL_FOURS) || positionPreferences.isEmpty()) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							return "[npc.Name] grabs hold of your shoulders and throws you down onto all fours."
									+ " Stepping around in front of you, [npc.she] drops down onto all fours [npc.herself], before shuffling back and pressing [npc.her] [npc.ass+] up against your face, growling, "
									+ "[npc.speech(That's right, put that tongue of yours to use like an obedient little bitch!)]";
						default:
							return "[npc.Name] takes hold of your shoulders and pushes you down onto all fours."
									+ " Stepping around in front of you, [npc.she] drops down onto all fours [npc.herself], before shuffling back and pressing [npc.her] [npc.ass+] up against your face, [npc.moaning], "
									+ "[npc.speech(Good [pc.girl]! This is gonna be fun!)]";
					}
					
				} else {
					return "Reaching down to grab you by the [pc.arm], [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(SexFlags.requestedDoggyReceiveOral) {
				if(positionPreferences.contains(SexPositionSlot.DOGGY_BEHIND_ORAL) || positionPreferences.isEmpty()) {
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
				
			} else if(SexFlags.requestedDomFuckedDoggy) {
				if(positionPreferences.contains(SexPositionSlot.DOGGY_ON_ALL_FOURS) || positionPreferences.isEmpty()) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							return "Slapping your [pc.arms] away from [npc.herHim], [npc.name] lets out an intimidating growl before roughly forcing you down onto your knees."
									+ " Much to your surprise, and delight, [npc.she] then drops down onto all fours in front of you, before shuffling back and rubbing [npc.her] [npc.ass+] against your crotch,"
									+ " [npc.speech(You want to fuck me, you little bitch?! Come on then, let's see if you've got what to takes to satisfy me!)]";
						default:
							return "A devious grin spreads across [npc.namePos] face as [npc.she] realises what it is you want."
									+ " Much to your delight, [npc.she] does exactly what you want, and drops down onto all fours in front of you, before shuffling back and rubbing [npc.her] [npc.ass+] against your crotch,"
									+ " [npc.speech(Come on then! This is what you wanted, isn't it?!)]";
					}
					
				} else {
					return "Slapping your [pc.arms] away from [npc.herHim], [npc.name] lets out an angry growl as [npc.she] shouts at you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(SexFlags.requestedKneeling) {
				if(positionPreferences.contains(SexPositionSlot.KNEELING_RECEIVING_ORAL) || positionPreferences.isEmpty()) {
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
				
			} else if(SexFlags.requestedSelfKneeling) {
				if(positionPreferences.contains(SexPositionSlot.KNEELING_PERFORMING_ORAL) || positionPreferences.isEmpty()) {
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
				
			} else if(SexFlags.requested69) {
				if(positionPreferences.contains(SexPositionSlot.SIXTY_NINE_TOP) || positionPreferences.isEmpty()) {
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
				
			} else if(SexFlags.requestedCowgirl) {
				if(positionPreferences.contains(SexPositionSlot.COWGIRL_RIDING) || positionPreferences.isEmpty()) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							return "Jumping down over the top of you, [npc.name] lowers [npc.herself] down over your groin, bringing [npc.her] crotch down to yours as [npc.she] straddles you in the cowgirl position."
									+ " Leaning forwards a little, [npc.she] growls down at you, "
									+ "[npc.speech(Good idea slut! Now <i>stay still</i> so I can use you properly!)]";
						default:
							return "Jumping down over the top of you, [npc.name] lowers [npc.herself] down over your groin, bringing [npc.her] crotch down to yours as [npc.she] straddles you in the cowgirl position."
									+ " Leaning forwards a little, [npc.she] grins down at you, "
									+ "[npc.speech(Good [pc.girl]! This is gonna be fun!)]";
					}
					
				} else {
					return "Reaching down to grab you by the [pc.arm], [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(SexFlags.requestedSitOnFace) {
				if(positionPreferences.contains(SexPositionSlot.FACE_SITTING_ON_BACK) || positionPreferences.isEmpty()) {
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
				
			} else if(SexFlags.requestedFaceSitting) {
				if(positionPreferences.contains(SexPositionSlot.FACE_SITTING_ON_FACE) || positionPreferences.isEmpty()) {
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
			}
			
			return "";
		}

		@Override
		public void applyEffects() {
			Set<SexPositionSlot> positionPreferences = Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this));
			
			if(SexFlags.requestedMissionary && (positionPreferences.contains(SexPositionSlot.MISSIONARY_ON_BACK) || positionPreferences.isEmpty())) {
				Sex.setSexManager(new SMMissionary(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.MISSIONARY_ON_BACK)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS))));
				
			} else if(SexFlags.requestedMissionaryOnBack && (positionPreferences.contains(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS) || positionPreferences.isEmpty())) {
				Sex.setSexManager(new SMMissionary(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.MISSIONARY_ON_BACK))));
				
			} else if(SexFlags.requestedFaceToWall && (positionPreferences.contains(SexPositionSlot.FACE_TO_WALL_FACING_TARGET) || positionPreferences.isEmpty())) {
				Sex.setSexManager(new SMFaceToWall(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.FACE_TO_WALL_FACING_TARGET)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.FACE_TO_WALL_AGAINST_WALL))));
				
			} else if(SexFlags.requestedBackToWall && (positionPreferences.contains(SexPositionSlot.BACK_TO_WALL_FACING_TARGET) || positionPreferences.isEmpty())) {
				Sex.setSexManager(new SMBackToWall(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.BACK_TO_WALL_FACING_TARGET)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.BACK_TO_WALL_AGAINST_WALL))));
				
			} else if(SexFlags.requestedDoggy && (positionPreferences.contains(SexPositionSlot.DOGGY_BEHIND) || positionPreferences.isEmpty())) {
				Sex.setSexManager(new SMDoggy(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.DOGGY_BEHIND)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.DOGGY_ON_ALL_FOURS))));
				
			} else if(SexFlags.requestedDoggyOral && (positionPreferences.contains(SexPositionSlot.DOGGY_ON_ALL_FOURS) || positionPreferences.isEmpty())) {
				Sex.setSexManager(new SMDoggy(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.DOGGY_ON_ALL_FOURS)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.DOGGY_BEHIND_ORAL))));
				
			} else if(SexFlags.requestedDoggyReceiveOral && (positionPreferences.contains(SexPositionSlot.DOGGY_BEHIND_ORAL) || positionPreferences.isEmpty())) {
				Sex.setSexManager(new SMDoggy(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.DOGGY_BEHIND_ORAL)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.DOGGY_ON_ALL_FOURS))));
				
			} else if(SexFlags.requestedDomFuckedDoggy && (positionPreferences.contains(SexPositionSlot.DOGGY_ON_ALL_FOURS) || positionPreferences.isEmpty())) {
				Sex.setSexManager(new SMDoggy(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.DOGGY_ON_ALL_FOURS)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.DOGGY_BEHIND))));
				
			} else if(SexFlags.requestedKneeling && (positionPreferences.contains(SexPositionSlot.KNEELING_RECEIVING_ORAL) || positionPreferences.isEmpty())) {
				Sex.setSexManager(new SMKneeling(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.KNEELING_RECEIVING_ORAL)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.KNEELING_PERFORMING_ORAL))));
				
			} else if(SexFlags.requestedSelfKneeling && (positionPreferences.contains(SexPositionSlot.KNEELING_PERFORMING_ORAL) || positionPreferences.isEmpty())) {
				Sex.setSexManager(new SMKneeling(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.KNEELING_PERFORMING_ORAL)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.KNEELING_RECEIVING_ORAL))));
				
			} else if(SexFlags.requested69 && (positionPreferences.contains(SexPositionSlot.SIXTY_NINE_TOP) || positionPreferences.isEmpty())) {
				Sex.setSexManager(new SMSixtyNine(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.SIXTY_NINE_TOP)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.SIXTY_NINE_BOTTOM))));
				
			} else if(SexFlags.requestedCowgirl && (positionPreferences.contains(SexPositionSlot.COWGIRL_RIDING) || positionPreferences.isEmpty())) {
				Sex.setSexManager(new SMCowgirl(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.COWGIRL_RIDING)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.COWGIRL_ON_BACK))));
				
			} else if(SexFlags.requestedSitOnFace && (positionPreferences.contains(SexPositionSlot.FACE_SITTING_ON_BACK) || positionPreferences.isEmpty())) {
				Sex.setSexManager(new SMFaceSitting(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.FACE_SITTING_ON_BACK)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.FACE_SITTING_ON_FACE))));
				
			} else if(SexFlags.requestedFaceSitting && (positionPreferences.contains(SexPositionSlot.FACE_SITTING_ON_FACE) || positionPreferences.isEmpty())) {
				Sex.setSexManager(new SMFaceSitting(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.FACE_SITTING_ON_FACE)),
						Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.FACE_SITTING_ON_BACK))));
				
			}
			
			SexFlags.resetRequests();
		}
	};
	
	// Partner positioning:
	
	
	public static final SexAction PARTNER_FORCE_POSITION_STANDING_FACE_TO_WALL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPartner
					&& !(Sex.getPosition() == SexPositionType.FACING_WALL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.FACE_TO_WALL_FACING_TARGET)
					&& 2>=Sex.getTotalParticipantCount(false)
					&& (Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexPositionSlot.FACE_TO_WALL_FACING_TARGET)
							|| Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).isEmpty())
					&& Sex.getActivePartner().hasPenis()
					&& Sex.isDom(Sex.getActivePartner())
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Face to wall";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] pushes [npc2.herHim] up against a nearby wall."
					+ " Grinding [npc.her] body up against [npc2.her] back, [npc.she] [npc.moansVerb] into [npc2.her] [npc2.ear],"
					+ " [npc.speech(Good [npc2.girl]! Now hold still while I fuck you!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMFaceToWall(
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.FACE_TO_WALL_FACING_TARGET)),
					Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.FACE_TO_WALL_AGAINST_WALL))));
			
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PARTNER_FORCE_POSITION_STANDING_BACK_TO_WALL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPartner
					&& !(Sex.getPosition() == SexPositionType.BACK_TO_WALL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.BACK_TO_WALL_FACING_TARGET)
					&& 2>=Sex.getTotalParticipantCount(false)
					&& (Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexPositionSlot.BACK_TO_WALL_FACING_TARGET)
							|| Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).isEmpty())
					&& Sex.isDom(Sex.getActivePartner())
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Back to wall";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] pushes [npc2.herHim] back against a nearby wall."
					+ " Grinding [npc.her] body up against [npc2.her]s, [npc.she] [npc.moansVerb] into [npc2.her] [npc2.ear], "
					+ "[npc.speech(Good [npc2.girl]! Now hold still while I fuck you!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMBackToWall(
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.BACK_TO_WALL_FACING_TARGET)),
					Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.BACK_TO_WALL_AGAINST_WALL))));
			
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PARTNER_FORCE_POSITION_DOGGY = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPartner
					&& !(Sex.getPosition() == SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.DOGGY_BEHIND)
					&& 2>=Sex.getTotalParticipantCount(false)
					&& (Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexPositionSlot.DOGGY_BEHIND)
							|| Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).isEmpty())
					&& Sex.getActivePartner().hasPenis()
					&& Sex.isDom(Sex.getActivePartner())
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Doggy-style";
		}

		@Override
		public String getActionDescription() {
			return "Push [npc.name] down onto all fours and kneel behind [npc.herHim], ready to fuck [npc.herHim] in the doggy-style position.";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] pushes [npc2.herHim] down on all fours."
					+ " Stepping around behind [npc2.herHim], [npc.she] drops down onto [npc.her] knees, shuffling forwards to grind [npc.her] crotch against [npc2.her] [npc2.ass+]."
					+ " Grabbing hold of [npc2.her] [npc2.hips+], [npc.she] [npc.moansVerb],"
					+ " [npc.speech(Good [npc2.girl]! Now hold still while I fuck you like the bitch you are!)]";
		}

		@Override
		public void applyEffects() {
			HashMap<GameCharacter, SexPositionSlot> dominants = new HashMap<>();
			int i=0;
			for(GameCharacter participant : Sex.getDominantParticipants().keySet()) {
				if(i==0) {
					dominants.put(participant , SexPositionSlot.DOGGY_BEHIND);
				} else {
					dominants.put(participant , SexPositionSlot.DOGGY_INFRONT);
				}
				i++;
			}
			Sex.setSexManager(new SMDoggy(
					dominants,
					Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.DOGGY_ON_ALL_FOURS))));
			
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PARTNER_FORCE_POSITION_DOGGY_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPartner
					&& !(Sex.getPosition() == SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.DOGGY_BEHIND_ORAL)
					&& 2>=Sex.getTotalParticipantCount(false)
					&& (Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexPositionSlot.DOGGY_BEHIND_ORAL)
							|| Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).isEmpty())
					&& Sex.isDom(Sex.getActivePartner())
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Doggy-style (oral)";
		}

		@Override
		public String getActionDescription() {
			return "Push [npc2.name] down onto all fours and drop down on all fours behind [npc2.herHim], ready to perform oral on [npc2.herHim] in the doggy-style position.";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] pushes [npc2.herHim] down on all fours."
					+ " Stepping around behind [npc2.herHim], [npc.she] similarly drops down onto all fours, shuffling forwards to bring [npc.her] [npc.face] up against [npc2.her] [npc2.ass+]."
					+ " Once [npc.sheIs] in position, [npc.she] [npc.moansVerb],"
					+ " [npc.speech(Good [npc2.girl]! Now hold still and enjoy this!)]";
		}

		@Override
		public void applyEffects() {
			HashMap<GameCharacter, SexPositionSlot> dominants = new HashMap<>();
			int i=0;
			for(GameCharacter participant : Sex.getDominantParticipants().keySet()) {
				if(i==0) {
					dominants.put(participant , SexPositionSlot.DOGGY_BEHIND_ORAL);
				} else {
					dominants.put(participant , SexPositionSlot.DOGGY_INFRONT);
				}
				i++;
			}
			Sex.setSexManager(new SMDoggy(
					dominants,
					Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.DOGGY_ON_ALL_FOURS))));
			
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PARTNER_FORCE_POSITION_DOGGY_SELF_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			SexType foreplayPreference = Sex.getActivePartner().getForeplayPreference(Sex.getCharacterTargetedForSexAction(this));
			SexType mainSexPreference = Sex.getActivePartner().getForeplayPreference(Sex.getCharacterTargetedForSexAction(this));
			
			return !SexFlags.positioningBlockedPartner
					&& !(Sex.getPosition() == SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.DOGGY_ON_ALL_FOURS)
					&& 2>=Sex.getTotalParticipantCount(false)
					&& (Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexPositionSlot.DOGGY_ON_ALL_FOURS)
							|| Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).isEmpty())
					&& (Sex.isInForeplay()
							?foreplayPreference==null
								|| foreplayPreference.getTargetedSexArea()==SexAreaPenetration.TONGUE
								|| foreplayPreference.getTargetedSexArea()==SexAreaOrifice.MOUTH
							:mainSexPreference==null
								|| mainSexPreference.getTargetedSexArea()==SexAreaPenetration.TONGUE
								|| mainSexPreference.getTargetedSexArea()==SexAreaOrifice.MOUTH)
					&& Sex.isDom(Sex.getActivePartner())
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Doggy-style (receive oral)";
		}

		@Override
		public String getActionDescription() {
			return "Push [npc2.name] down onto all fours and drop down on all fours in front of [npc2.herHim], ready to receive oral from [npc2.herHim] in the doggy-style position.";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] pushes [npc2.herHim] down on all fours."
					+ " Stepping around in front of [npc2.herHim], [npc.she] similarly drops down onto all fours, shuffling backwards to bring [npc.her] [npc.ass+] up against [npc2.her] [npc2.face+]."
					+ " Once [npc.sheIs] in position, [npc.she] [npc.moans],"
					+ " [npc.speech(Good [npc2.girl]! Now put that tongue of yours to use!)]";
		}

		@Override
		public void applyEffects() {
			HashMap<GameCharacter, SexPositionSlot> dominants = new HashMap<>();
			int i=0;
			for(GameCharacter participant : Sex.getDominantParticipants().keySet()) {
				if(i==0) {
					dominants.put(participant , SexPositionSlot.DOGGY_ON_ALL_FOURS);
				} else {
					dominants.put(participant , SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND);
				}
				i++;
			}
			Sex.setSexManager(new SMDoggy(
					dominants,
					Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.DOGGY_BEHIND_ORAL))));
			
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PARTNER_FORCE_POSITION_DOM_AS_DOGGY = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPartner
					&& !(Sex.getPosition() == SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.DOGGY_ON_ALL_FOURS)
					&& 2>=Sex.getTotalParticipantCount(false)
					&& (Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexPositionSlot.DOGGY_ON_ALL_FOURS)
							|| Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).isEmpty())
					&& Sex.isDom(Sex.getActivePartner())
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Doggy-style";
		}

		@Override
		public String getActionDescription() {
			return "Push [npc2.name] down onto [npc2.her] knees before dropping down on all fours, ready to get fucked in the doggy-style position.";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] pushes [npc2.herHim] down onto [npc2.her] knees."
					+ " Turing around, [npc.she] then drops down onto all fours, before shuffling back to grind [npc.her] [npc.ass+] against [npc2.her] crotch."
					+ " Reaching back and forcing [npc2.her] [npc2.hands] to take hold of [npc.her] [npc.hips+], [npc.she] [npc.moans],"
					+ " [npc.speech(Come on! Fuck me like an animal!)]";
		}

		@Override
		public void applyEffects() {
			HashMap<GameCharacter, SexPositionSlot> dominants = new HashMap<>();
			int i=0;
			for(GameCharacter participant : Sex.getDominantParticipants().keySet()) {
				if(i==0) {
					dominants.put(participant , SexPositionSlot.DOGGY_ON_ALL_FOURS);
				} else {
					dominants.put(participant , SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND);
				}
				i++;
			}
			Sex.setSexManager(new SMDoggy(
					dominants,
					Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.DOGGY_BEHIND))));
			
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PARTNER_FORCE_POSITION_KNEELING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPartner
					&& !(Sex.getPosition() == SexPositionType.KNEELING_ORAL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.KNEELING_RECEIVING_ORAL)
					&& 2>=Sex.getTotalParticipantCount(false)
							&& (Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexPositionSlot.KNEELING_RECEIVING_ORAL)
									|| Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).isEmpty())
					&& Sex.isDom(Sex.getActivePartner())
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Kneel (receive oral)";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] quickly forces [npc2.herHim] to [npc2.her] knees before [npc.herHim]."
					+ " Looking up, [npc2.name] [npc2.verb(see)] [npc.herHim] grinning down at [npc2.her] submissive form, and with a little laugh, [npc.name] [npc.moans], "
					+ "[npc.speech(Time to put your mouth to use!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMKneeling(
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.KNEELING_RECEIVING_ORAL)),
					Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.KNEELING_PERFORMING_ORAL))));
			
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PARTNER_FORCE_POSITION_SELF_KNEELING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPartner
					&& !(Sex.getPosition() == SexPositionType.KNEELING_ORAL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.KNEELING_PERFORMING_ORAL)
					&& 2>=Sex.getTotalParticipantCount(false)
							&& (Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexPositionSlot.KNEELING_PERFORMING_ORAL)
									|| Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).isEmpty())
					&& Sex.isDom(Sex.getActivePartner())
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getActionTitle() {
			return "Kneel (give oral)";
		}

		@Override
		public String getActionDescription() {
			return "Get on your knees.";
		}

		@Override
		public String getDescription() {
			return "Running [npc.her] [npc.hands] down [npc2.namePos] body, [npc.name] drops on to [npc.her] knees before [npc2.herHim]."
					+ " Looking up, [npc.she] grins at [npc2.name], and with a little laugh, [npc.she] [npc.moans],"
					+ " [npc.speech(Stay still and enjoy this!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMKneeling(
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.KNEELING_PERFORMING_ORAL)),
					Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.KNEELING_RECEIVING_ORAL))));
			
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PARTNER_FORCE_POSITION_SIXTY_NINE = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPartner
					&& !(Sex.getPosition() == SexPositionType.SIXTY_NINE && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.SIXTY_NINE_TOP)
					&& 2>=Sex.getTotalParticipantCount(false)
					&& (Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexPositionSlot.SIXTY_NINE_TOP)
							|| Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).isEmpty())
					&& Sex.isDom(Sex.getActivePartner())
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Sixty-nine";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] pushes [npc2.herHim] down onto [npc2.her] back."
					+ " Quickly lowering [npc.herself] down onto all fours over the top of [npc2.herHim],"
						+ " [npc.she] drops [npc.her] crotch down over [npc2.her] face as [npc.she] similarly [npc.verb(position)] [npc.her] own head over [npc2.her] groin."
					+ " Looking back beneath [npc.herHim], [npc.name] [npc.moans], "
					+ "[npc.speech(Good [npc2.girl]! Now let's have some fun!)]";
			
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMSixtyNine(
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.SIXTY_NINE_TOP)),
					Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.SIXTY_NINE_BOTTOM))));
			
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PARTNER_FORCE_POSITION_COWGIRL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPartner
					&& !(Sex.getPosition() == SexPositionType.COWGIRL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.COWGIRL_RIDING)
					&& 2>=Sex.getTotalParticipantCount(false)
					&& (Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexPositionSlot.COWGIRL_RIDING)
							|| Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).isEmpty())
					&& Sex.isDom(Sex.getActivePartner())
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Cowgirl";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] pushes [npc2.herHim] down onto [npc2.her] back."
					+ " [npc.She] then lowers [npc.herself] down on top of [npc2.herHim], bringing [npc.her] crotch down to [npc2.hers] as [npc.she] straddles [npc2.herHim] in the cowgirl position."
					+ " Once [npc.sheHas] made [npc.herself] comfortable, [npc.she] grins down at [npc2.name], "
					+ "[npc.speech(Good [npc2.girl]! Now let's have some fun!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMCowgirl(
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.COWGIRL_RIDING)),
					Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.COWGIRL_ON_BACK))));
			
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PARTNER_FORCE_POSITION_MISSIONARY = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPartner
					&& !(Sex.getPosition() == SexPositionType.MISSIONARY && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS)
					&& 2>=Sex.getTotalParticipantCount(false)
					&& (Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS)
							|| Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).isEmpty())
					&& Sex.isDom(Sex.getActivePartner())
					&& !Sex.getCharacterPerformingAction().isPlayer();
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
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] pushes [npc2.herHim] down onto [npc2.her] back."
					+ " Kneeling down between [npc2.her] [npc2.legs], [npc.she] [npc.moansVeb] as [npc.she] looks down into [npc2.her] [npc2.eyes+], "
					+ "[npc.speech(That's right, spread your legs for me...)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMMissionary(
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS)),
					Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.MISSIONARY_ON_BACK))));
			
//			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PARTNER_FORCE_POSITION_MISSIONARY_ON_BACK = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPartner
					&& !(Sex.getPosition() == SexPositionType.MISSIONARY && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.MISSIONARY_ON_BACK)
					&& 2>=Sex.getTotalParticipantCount(false)
					&& (Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).contains(SexPositionSlot.MISSIONARY_ON_BACK)
							|| Sex.getActivePartner().getSexPositionPreferences(Sex.getCharacterTargetedForSexAction(this)).isEmpty())
					&& Sex.isDom(Sex.getActivePartner())
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Missionary (on back)";
		}

		@Override
		public String getActionDescription() {
			return "Lie down on [npc2.her] back and spread [npc2.her] [npc.legs], ready to have sex with [npc2.name] in the missionary position.";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] pushes [npc2.herHim] down onto [npc2.her] knees."
					+ " Kneeling down before [npc2.herHim], [npc.she] then lies down onto [npc.her] back, spreading [npc.her] [npc.legs] and looking up into [npc2.her] [npc2.eyes+] as [npc.she] [npc.moansVerb], "
					+ "[npc.speech(Come and take me!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMMissionary(
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.MISSIONARY_ON_BACK)),
					Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS))));
			
//			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};
}
