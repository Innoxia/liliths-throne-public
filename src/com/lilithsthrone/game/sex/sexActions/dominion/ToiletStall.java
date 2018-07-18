package com.lilithsthrone.game.sex.sexActions.dominion;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexPositionType;
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
 * @since 0.2.8
 * @version 0.2.9
 * @author Innoxia
 */
public class ToiletStall {

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
					&& SexPositionType.FACING_WALL.getMaximumSlots()>=Sex.getTotalParticipantCount()
					&& Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Face-to-wall";
		}

		@Override
		public String getActionDescription() {
			return "Push [npc2.name] up against the wall of the toilet.";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, you push [npc2.herHim] up against one of the toilet stall's walls."
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
					&& SexPositionType.FACING_WALL.getMaximumSlots()>=Sex.getTotalParticipantCount()
					&& !Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Face-to-wall";
		}

		@Override
		public String getActionDescription() {
			return "Try and move into a position so that you're facing one of the toilet stall's wall.";
		}

		@Override
		public String getDescription() {
			return "Before [npc2.name] can react, you quickly move up against the nearest of the toilet stall's walls."
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
					&& SexPositionType.BACK_TO_WALL.getMaximumSlots()>=Sex.getTotalParticipantCount()
					&& Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Back-to-wall";
		}

		@Override
		public String getActionDescription() {
			return "Push [npc2.name] back against a one of the toilet stall's walls.";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, you push [npc2.herHim] back against the wall of the toilet."
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
					&& SexPositionType.BACK_TO_WALL.getMaximumSlots()>=Sex.getTotalParticipantCount()
					&& !Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
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
			return "Before [npc2.name] can react, you quickly move up against the nearest of the toilet stall's walls."
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
					&& SexPositionType.KNEELING_ORAL.getMaximumSlots()>=Sex.getTotalParticipantCount()
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
					&& SexPositionType.KNEELING_ORAL.getMaximumSlots()>=Sex.getTotalParticipantCount()
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
					&& SexPositionType.KNEELING_ORAL.getMaximumSlots()>=Sex.getTotalParticipantCount()
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
					&& SexPositionType.KNEELING_ORAL.getMaximumSlots()>=Sex.getTotalParticipantCount()
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
			
			if(SexFlags.requestedMissionary) {
				if(Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.MISSIONARY_ON_BACK) || Sex.getActivePartner().getSexPositionPreferences().isEmpty()) {
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
				if(Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS) || Sex.getActivePartner().getSexPositionPreferences().isEmpty()) {
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
				if(Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.FACE_TO_WALL_FACING_TARGET) || Sex.getActivePartner().getSexPositionPreferences().isEmpty()) {
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
				if(Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.BACK_TO_WALL_FACING_TARGET) || Sex.getActivePartner().getSexPositionPreferences().isEmpty()) {
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
				if(Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.DOGGY_BEHIND) || Sex.getActivePartner().getSexPositionPreferences().isEmpty()) {
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
				if(Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.DOGGY_ON_ALL_FOURS) || Sex.getActivePartner().getSexPositionPreferences().isEmpty()) {
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
				if(Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.DOGGY_BEHIND_ORAL) || Sex.getActivePartner().getSexPositionPreferences().isEmpty()) {
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
				if(Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.DOGGY_ON_ALL_FOURS) || Sex.getActivePartner().getSexPositionPreferences().isEmpty()) {
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
				if(Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.KNEELING_RECEIVING_ORAL) || Sex.getActivePartner().getSexPositionPreferences().isEmpty()) {
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
				if(Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.KNEELING_PERFORMING_ORAL) || Sex.getActivePartner().getSexPositionPreferences().isEmpty()) {
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
				if(Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.SIXTY_NINE_TOP) || Sex.getActivePartner().getSexPositionPreferences().isEmpty()) {
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
				if(Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.COWGIRL_RIDING) || Sex.getActivePartner().getSexPositionPreferences().isEmpty()) {
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
				if(Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.FACE_SITTING_ON_BACK) || Sex.getActivePartner().getSexPositionPreferences().isEmpty()) {
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
				if(Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.FACE_SITTING_ON_FACE) || Sex.getActivePartner().getSexPositionPreferences().isEmpty()) {
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
			if(SexFlags.requestedMissionary && (Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.MISSIONARY_ON_BACK) || Sex.getActivePartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMMissionary(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.MISSIONARY_ON_BACK)),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS))));
				
			} else if(SexFlags.requestedMissionaryOnBack && (Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS) || Sex.getActivePartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMMissionary(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS)),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MISSIONARY_ON_BACK))));
				
			} else if(SexFlags.requestedFaceToWall && (Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.FACE_TO_WALL_FACING_TARGET) || Sex.getActivePartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMFaceToWall(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.FACE_TO_WALL_FACING_TARGET)),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.FACE_TO_WALL_AGAINST_WALL))));
				
			} else if(SexFlags.requestedBackToWall && (Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.BACK_TO_WALL_FACING_TARGET) || Sex.getActivePartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMBackToWall(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.BACK_TO_WALL_FACING_TARGET)),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.BACK_TO_WALL_AGAINST_WALL))));
				
			} else if(SexFlags.requestedDoggy && (Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.DOGGY_BEHIND) || Sex.getActivePartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMDoggy(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.DOGGY_BEHIND)),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS))));
				
			} else if(SexFlags.requestedDoggyOral && (Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.DOGGY_ON_ALL_FOURS) || Sex.getActivePartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMDoggy(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.DOGGY_ON_ALL_FOURS)),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND_ORAL))));
				
			} else if(SexFlags.requestedDoggyReceiveOral && (Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.DOGGY_BEHIND_ORAL) || Sex.getActivePartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMDoggy(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.DOGGY_BEHIND_ORAL)),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS))));
				
			} else if(SexFlags.requestedDomFuckedDoggy && (Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.DOGGY_ON_ALL_FOURS) || Sex.getActivePartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMDoggy(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.DOGGY_ON_ALL_FOURS)),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND))));
				
			} else if(SexFlags.requestedKneeling && (Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.KNEELING_RECEIVING_ORAL) || Sex.getActivePartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMKneeling(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.KNEELING_RECEIVING_ORAL)),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.KNEELING_PERFORMING_ORAL))));
				
			} else if(SexFlags.requestedSelfKneeling && (Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.KNEELING_PERFORMING_ORAL) || Sex.getActivePartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMKneeling(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.KNEELING_PERFORMING_ORAL)),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.KNEELING_RECEIVING_ORAL))));
				
			} else if(SexFlags.requested69 && (Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.SIXTY_NINE_TOP) || Sex.getActivePartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMSixtyNine(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.SIXTY_NINE_TOP)),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.SIXTY_NINE_BOTTOM))));
				
			} else if(SexFlags.requestedCowgirl && (Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.COWGIRL_RIDING) || Sex.getActivePartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMCowgirl(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.COWGIRL_RIDING)),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.COWGIRL_ON_BACK))));
				
			} else if(SexFlags.requestedSitOnFace && (Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.FACE_SITTING_ON_BACK) || Sex.getActivePartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMFaceSitting(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.FACE_SITTING_ON_BACK)),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.FACE_SITTING_ON_FACE))));
				
			} else if(SexFlags.requestedFaceSitting && (Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.FACE_SITTING_ON_FACE) || Sex.getActivePartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMFaceSitting(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.FACE_SITTING_ON_FACE)),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.FACE_SITTING_ON_BACK))));
				
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
					&& 2>=Sex.getTotalParticipantCount()
					&& (Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.FACE_TO_WALL_FACING_TARGET) || Sex.getActivePartner().getSexPositionPreferences().isEmpty())
					&& Sex.getActivePartner().hasPenis()
					&& !Sex.isDom(Main.game.getPlayer())
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
			return "Taking hold of your shoulders, [npc.name] pushes you up against the nearest of the toilet stall's walls."
					+ " Grinding [npc.her] body up against your back, [npc.she] [npc.moans] into your [pc.ear], "
					+ "[npc.speech(Good [pc.girl]! Now hold still while I fuck you!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMFaceToWall(
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.FACE_TO_WALL_FACING_TARGET)),
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.FACE_TO_WALL_AGAINST_WALL))));
			
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
					&& 2>=Sex.getTotalParticipantCount()
					&& (Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.BACK_TO_WALL_FACING_TARGET) || Sex.getActivePartner().getSexPositionPreferences().isEmpty())
					&& !Sex.isDom(Main.game.getPlayer())
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
			return "Taking hold of your shoulders, [npc.name] pushes you back against the closest of the toilet stall's walls."
					+ " Grinding [npc.her] body up against yours, [npc.she] [npc.moans] into your [pc.ear], "
					+ "[npc.speech(Good [pc.girl]! Now hold still while I fuck you!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMBackToWall(
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.BACK_TO_WALL_FACING_TARGET)),
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.BACK_TO_WALL_AGAINST_WALL))));
			
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
					&& 2>=Sex.getTotalParticipantCount()
							&& (Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.KNEELING_RECEIVING_ORAL) || Sex.getActivePartner().getSexPositionPreferences().isEmpty())
					&& !Sex.isDom(Main.game.getPlayer())
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
			return "Taking hold of your shoulders, [npc.name] quickly forces you to your knees before [npc.herHim]."
					+ " Looking up, you see [npc.herHim] grinning down at your submissive form, and with a little laugh, [npc.she] [npc.moans], "
					+ "[npc.speech(Time to put your mouth to use!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMKneeling(
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.KNEELING_RECEIVING_ORAL)),
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.KNEELING_PERFORMING_ORAL))));
			
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
					&& 2>=Sex.getTotalParticipantCount()
							&& (Sex.getActivePartner().getSexPositionPreferences().contains(SexPositionSlot.KNEELING_PERFORMING_ORAL) || Sex.getActivePartner().getSexPositionPreferences().isEmpty())
					&& !Sex.isDom(Main.game.getPlayer())
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
			return "Running [npc.her] [npc.hands] down your body, [npc.name] drops on to [npc.her] knees before you."
					+ " Looking down, you see [npc.herHim] grinning up at you, and with a little laugh, [npc.she] [npc.moans],"
					+ " [npc.speech(Stay still and enjoy this!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMKneeling(
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.KNEELING_PERFORMING_ORAL)),
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.KNEELING_RECEIVING_ORAL))));
			
			SexFlags.resetRequests();
		}
	};
	
}
