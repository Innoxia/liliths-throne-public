package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexPosition;
import com.lilithsthrone.game.sex.managers.universal.SMDomBackToWall;
import com.lilithsthrone.game.sex.managers.universal.SMDomCowgirl;
import com.lilithsthrone.game.sex.managers.universal.SMDomDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMDomDoggyOral;
import com.lilithsthrone.game.sex.managers.universal.SMDomFaceToWall;
import com.lilithsthrone.game.sex.managers.universal.SMDomKneeling;
import com.lilithsthrone.game.sex.managers.universal.SMDomSelfDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMDomSelfDoggyOral;
import com.lilithsthrone.game.sex.managers.universal.SMDomSelfKneeling;
import com.lilithsthrone.game.sex.managers.universal.SMDomSixtyNine;
import com.lilithsthrone.game.sex.managers.universal.SMSubBackToWall;
import com.lilithsthrone.game.sex.managers.universal.SMSubCowgirl;
import com.lilithsthrone.game.sex.managers.universal.SMSubDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMSubDoggyOral;
import com.lilithsthrone.game.sex.managers.universal.SMSubFaceToWall;
import com.lilithsthrone.game.sex.managers.universal.SMSubKneeling;
import com.lilithsthrone.game.sex.managers.universal.SMSubSelfDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMSubSelfDoggyOral;
import com.lilithsthrone.game.sex.managers.universal.SMSubSelfKneeling;
import com.lilithsthrone.game.sex.managers.universal.SMSubSixtyNine;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;

/**
 * Contains all positional changes for both sub and dom.
 * 
 * If sub, positional change is just a suggestion, which the NPC may refuse if they have other preferences.
 * 
 * 
 * @since 0.1.79
 * @version 0.1.86
 * @author Innoxia
 */
public class GenericPositioning {

	// Dom position changes:
	
	public static final SexAction PLAYER_FORCE_POSITION_STANDING_FACE_TO_WALL = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& Sex.getPosition() != SexPosition.FACING_WALL_PARTNER
					&& Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Face to wall";
		}

		@Override
		public String getActionDescription() {
			return "Push [npc.name] up against a nearby wall.";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc.name]'s shoulders, you push [npc.herHim] up against a nearby wall."
					+ " Grinding your body up against [npc.her] back, you [pc.moan] into [npc.her] [npc.ear], "
					+ "[pc.speech(Be a good [npc.girl] and hold still while I fuck you!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMDomFaceToWall());
			
//			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PLAYER_DOM_POSITION_SELF_DOGGY = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& Sex.getPosition() != SexPosition.SELF_DOGGY_PLAYER_ON_ALL_FOURS
					&& Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Doggy-style (self)";
		}

		@Override
		public String getActionDescription() {
			return "Push [npc.name] down onto [npc.her] knees and position yourself on all fours in front of [npc.herHim].";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc.name]'s shoulders, you push [npc.herHim] towards the ground, making [npc.herHim] kneel."
					+ " You move in front of [npc.herHim] and position yourself on all fours, suggestively shaking your [pc.hips] and softly tracing your finger over your [pc.ass] to entice [npc.herHim]. "
					+ "[pc.speech(Get to work, [npc.girl]!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMDomSelfDoggy());
			
//			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PLAYER_FORCE_POSITION_STANDING_BACK_TO_WALL = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& Sex.getPosition() != SexPosition.BACK_TO_WALL_PARTNER
					&& Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Back to wall";
		}

		@Override
		public String getActionDescription() {
			return "Push [npc.name] back against a nearby wall.";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc.name]'s shoulders, you push [npc.herHim] back against a nearby wall."
					+ " Grinding your body up against [npc.hers], you [pc.moan] into [npc.her] [npc.ear], "
					+ "[pc.speech(Be a good [npc.girl] and hold still while I fuck you!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMDomBackToWall());
			
//			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PLAYER_FORCE_POSITION_DOGGY = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& Sex.getPosition() != SexPosition.DOGGY_PARTNER_ON_ALL_FOURS
					&& Sex.isPlayerDom();
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
			return "Taking hold of [npc.name]'s shoulders, you push [npc.herHim] down on all fours."
					+ " Stepping around behind [npc.herHim], you drop down onto your knees, shuffling forwards to grind your crotch against [npc.her] [npc.ass+]."
					+ " Grabbing hold of [npc.her] [npc.hips+], you [pc.moan], "
					+ "[pc.speech(Be a good [npc.girl] and hold still while I fuck you like the bitch you are!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMDomDoggy());
			
//			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PLAYER_FORCE_POSITION_DOGGY_ORAL = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& Sex.getPosition() != SexPosition.DOGGY_ORAL_PLAYER_DOM_PARTNER_ON_ALL_FOURS
					&& Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Doggy-style (perform oral)";
		}

		@Override
		public String getActionDescription() {
			return "Push [npc.name] down onto all fours and drop down behind [npc.herHim], ready to perform oral on [npc.herHim] in the doggy-style position.";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc.name]'s shoulders, you push [npc.herHim] down on all fours."
					+ " Stepping around behind [npc.herHim], you drop down onto all fours yourself, crawling forwards to your face up against [npc.her] [npc.ass+]."
					+ " Grinning at the sight in front of you, you [pc.moan], "
					+ "[pc.speech(Be a good [npc.girl] and enjoy this!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMDomDoggyOral());
			
//			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PLAYER_FORCE_POSITION_SELF_DOGGY_ORAL = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& Sex.getPosition() != SexPosition.DOGGY_ORAL_PLAYER_DOM_PLAYER_ON_ALL_FOURS
					&& Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Doggy-style (receive oral)";
		}

		@Override
		public String getActionDescription() {
			return "Push [npc.name] down onto all fours and drop down in front of [npc.herHim], ready to receive oral from [npc.herHim] in the doggy-style position.";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc.name]'s shoulders, you push [npc.herHim] down on all fours."
					+ " Stepping around in front of [npc.herHim], you drop down onto all fours yourself, shuffling backwards to press your [pc.ass+] up against [npc.her] [npc.face+]."
					+ " Looking back over your shoulder, you [pc.moan], "
					+ "[pc.speech(Be a good [npc.girl] and put your tongue to use!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMDomSelfDoggyOral());
			
//			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PLAYER_FORCE_POSITION_KNEELING = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& Sex.getPosition() != SexPosition.KNEELING_PARTNER_PERFORMING_ORAL
					&& Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Kneel (receive oral)";
		}

		@Override
		public String getActionDescription() {
			return "Force [npc.name] to [npc.her] knees.";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc.name]'s shoulders, you quite quickly force [npc.herHim] to [npc.her] knees before you."
					+ " Grinning down at [npc.her] submissive form, you [pc.moan], "
					+ "[pc.speech(Time to put your mouth to use!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMDomKneeling());
			
//			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};

	public static final SexAction PLAYER_FORCE_POSITION_SELF_KNEELING = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& Sex.getPosition() != SexPosition.KNEELING_PLAYER_PERFORMING_ORAL
					&& Sex.isPlayerDom();
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
			return "Smiling, you slowly slide down to your knees in front of [npc.name].";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMDomSelfKneeling());

//			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PLAYER_FORCE_POSITION_SIXTY_NINE = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& Sex.getPosition() != SexPosition.SIXTY_NINE_PLAYER_TOP
					&& Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Sixty-nine";
		}

		@Override
		public String getActionDescription() {
			return "Push [npc.name] down onto [npc.her] back and straddle [npc.her] face, in the sixty-nine position.";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc.name]'s shoulders, you push [npc.herHim] down onto [npc.her] back."
					+ " You then lower yourself down onto all fours over the top of [npc.herHim], lowering your crotch down to [npc.her] face as you similarly position your own head over [npc.her] groin."
					+ " Looking back beneath you, you [pc.moan], "
					+ "[pc.speech(Good [npc.girl]! Now let's have some fun!)]";
			
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMDomSixtyNine());
		}
	};
	
	public static final SexAction PLAYER_FORCE_POSITION_COW_GIRL = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& Sex.getPosition() != SexPosition.COWGIRL_PLAYER_TOP
					&& Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Cowgirl";
		}

		@Override
		public String getActionDescription() {
			return "Push [npc.name] down onto [npc.her] back and straddle [npc.her] groin, in the cow-girl position.";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc.name]'s shoulders, you push [npc.herHim] down onto [npc.her] back."
					+ " You then lower yourself down on top of [npc.herHim], bringing your crotch down to [npc.hers] as you straddle [npc.herHim] in the cowgirl position."
					+ " Once you've made yourself comfortable, you grin down at [npc.name], "
					+ "[pc.speech(Good [npc.girl]! Now let's have some fun!)]";
			
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMDomCowgirl());
		}
	};
	
	
	// Sub position changes with associated partner response:
	
	public static final SexAction PLAYER_POSITION_REQUEST_STANDING_FACE_TO_WALL = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedFaceToWall
					&& Sex.getPosition() != SexPosition.FACING_WALL_PLAYER
					&& !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Face-to-wall (request)";
		}

		@Override
		public String getActionDescription() {
			return "Try and move into a position so that you're facing a nearby wall.";
		}

		@Override
		public String getDescription() {
			return "Before [npc.name] can react, you quickly move up against a nearby wall."
					+ " Placing your hands up against the solid surface that's now in front of you, you push your [pc.ass+] out, shaking it at [npc.name] as you try to encourage [npc.herHim] to fuck you like this.";
		}

		@Override
		public void applyEffects() {
			SexFlags.requestedFaceToWall = true;
		}
	};
	
	public static final SexAction PLAYER_POSITION_REQUEST_STANDING_BACK_TO_WALL = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedBackToWall
					&& Sex.getPosition() != SexPosition.BACK_TO_WALL_PLAYER
					&& !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Back-to-wall (request)";
		}

		@Override
		public String getActionDescription() {
			return "Try and move into a position so that your back is up against a nearby wall.";
		}

		@Override
		public String getDescription() {
			return "Before [npc.name] can react, you quickly move up against a nearby wall."
					+ " Leaning back against the solid surface that's now behind you, you give [npc.name] your most seductive look, trying to encourage [npc.herHim] to fuck you like this.";
		}

		@Override
		public void applyEffects() {
			SexFlags.requestedBackToWall = true;
		}
	};
	
	public static final SexAction PLAYER_POSITION_REQUEST_DOGGY = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedDoggy
					&& Sex.getPosition() != SexPosition.DOGGY_PLAYER_ON_ALL_FOURS
					&& !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Doggy-style (request)";
		}

		@Override
		public String getActionDescription() {
			return "Get down on all fours and present yourself in the hopes that [npc.name] wants to fuck you, doggy-style.";
		}

		@Override
		public String getDescription() {
			return "Before [npc.name] can react, you quickly drop down onto all fours, before shuffling around to present yourself to [npc.herHim].";
		}

		@Override
		public void applyEffects() {
			SexFlags.requestedDoggy = true;
		}
	};
	
	public static final SexAction PLAYER_POSITION_REQUEST_DOGGY_ORAL = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedDoggyOral
					&& Sex.getPosition() != SexPosition.DOGGY_ORAL_PLAYER_SUB_PLAYER_ON_ALL_FOURS
					&& !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Doggy-style perform oral (request)";
		}

		@Override
		public String getActionDescription() {
			return "Try and push [npc.name] down onto all fours in the hope that [npc.she] wants you to perform oral on [npc.herHim] in the doggy-style position.";
		}

		@Override
		public String getDescription() {
			return "You try to push [npc.name] down onto all fours, [pc.moaning],"
					+ " [pc.speech(Let me perform oral on you... Please...)]";
		}

		@Override
		public void applyEffects() {
			SexFlags.requestedDoggyOral = true;
		}
	};
	
	public static final SexAction PLAYER_POSITION_REQUEST_DOGGY_RECEIVE_ORAL = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedDoggyReceiveOral
					&& Sex.getPosition() != SexPosition.DOGGY_ORAL_PLAYER_SUB_PLAYER_ON_ALL_FOURS
					&& !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Doggy-style receive oral (request)";
		}

		@Override
		public String getActionDescription() {
			return "Get down on all fours and present yourself in the hopes that [npc.name] wants to perform oral on you in the doggy-style position.";
		}

		@Override
		public String getDescription() {
			return "Before [npc.name] can react, you quickly drop down onto all fours, before shuffling around to present yourself to [npc.herHim].";
		}

		@Override
		public void applyEffects() {
			SexFlags.requestedDoggyReceiveOral = true;
		}
	};
	
	public static final SexAction PLAYER_POSITION_REQUEST_DOM_FUCKED_DOGGY = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedDomFuckedDoggy
					&& Sex.getPosition() != SexPosition.DOGGY_PARTNER_AS_DOM_ON_ALL_FOURS
					&& !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Doggy-style [npc.herHim] (request)";
		}

		@Override
		public String getActionDescription() {
			return "Try and get [npc.name] to present [npc.herself] in the hopes that [npc.she] wants you to fuck [npc.herHim], doggy-style.";
		}

		@Override
		public String getDescription() {
			return "Lifting your [pc.arms], you take hold of [npc.name]'s shoulders, and with a little pressure, try to get [npc.herHim] to drop down onto all fours so that you can fuck [npc.herHim] doggy-style.";
		}

		@Override
		public void applyEffects() {
			SexFlags.requestedDomFuckedDoggy = true;
		}
	};
	
	public static final SexAction PLAYER_POSITION_REQUEST_KNEELING = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedKneeling
					&& Sex.getPosition() != SexPosition.KNEELING_PLAYER_PERFORMING_ORAL
					&& !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Kneel (request)";
		}

		@Override
		public String getActionDescription() {
			return "Drop down onto your knees in the hope that [npc.name] wants you to perform oral on [npc.herHim].";
		}

		@Override
		public String getDescription() {
			return "You quickly drop down to your knees in front of [npc.name], shuffling forwards a little to bring your face closer to [npc.her] groin.";
		}

		@Override
		public void applyEffects() {
			SexFlags.requestedKneeling = true;
		}
	};
	
	public static final SexAction PLAYER_POSITION_REQUEST_SELF_KNEELING = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedSelfKneeling
					&& Sex.getPosition() != SexPosition.KNEELING_PARTNER_PERFORMING_ORAL
					&& !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Receive kneeling oral (request)";
		}

		@Override
		public String getActionDescription() {
			return "Try and push [npc.name] down onto [npc.her] knees so that [npc.she]'ll perform oral on you.";
		}

		@Override
		public String getDescription() {
			return "Lifting your [pc.arms], you take hold of [npc.name]'s shoulders, and, with a little pressure, try to get [npc.herHim] to kneel before you.";
		}

		@Override
		public void applyEffects() {
			SexFlags.requestedSelfKneeling = true;
		}
	};
	
	public static final SexAction PLAYER_POSITION_REQUEST_SIXTY_NINE = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requested69
					&& Sex.getPosition() != SexPosition.SIXTY_NINE_PARTNER_TOP
					&& !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Sixty-nine (request)";
		}

		@Override
		public String getActionDescription() {
			return "Lie down on your back and ask [npc.name] to sixty-nine with you.";
		}

		@Override
		public String getDescription() {
			return "Sinking down to lie on your back, you put on the most enticing look you can muster as you plead, "
					+ "[pc.speech(I want to sixty-nine with you... Please!)]";
		}

		@Override
		public void applyEffects() {
			SexFlags.requested69 = true;
		}
	};
	
	public static final SexAction PLAYER_POSITION_REQUEST_COWGIRL = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& !SexFlags.requestedCowgirl
					&& Sex.getPosition() != SexPosition.COWGIRL_PARTNER_TOP
					&& !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Cowgirl (request)";
		}

		@Override
		public String getActionDescription() {
			return "Lie down on your back and ask [npc.name] to straddle you in the cowgirl position.";
		}

		@Override
		public String getDescription() {
			return "Dropping down and quickly lying on your back, you put on the most enticing look you can muster as you plead, "
					+ "[pc.speech(Come and ride me... Please!)]";
		}

		@Override
		public void applyEffects() {
			SexFlags.requestedCowgirl = true;
		}
	};
	
	public static final SexAction PARTNER_POSITION_RESPONSE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

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
					|| SexFlags.requestedSelfKneeling)
					&& !Sex.isPlayerDom();
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
			if(SexFlags.requestedFaceToWall) {
				if(Sex.getPartner().getSexPositionPreferences().contains(SexPosition.FACING_WALL_PLAYER) || Sex.getPartner().getSexPositionPreferences().isEmpty()) {
					switch(Sex.getSexPacePartner()) {
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
				if(Sex.getPartner().getSexPositionPreferences().contains(SexPosition.BACK_TO_WALL_PLAYER) || Sex.getPartner().getSexPositionPreferences().isEmpty()) {
					switch(Sex.getSexPacePartner()) {
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
				if(Sex.getPartner().getSexPositionPreferences().contains(SexPosition.DOGGY_PLAYER_ON_ALL_FOURS) || Sex.getPartner().getSexPositionPreferences().isEmpty()) {
					switch(Sex.getSexPacePartner()) {
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
				if(Sex.getPartner().getSexPositionPreferences().contains(SexPosition.DOGGY_ORAL_PLAYER_SUB_PLAYER_ON_ALL_FOURS) || Sex.getPartner().getSexPositionPreferences().isEmpty()) {
					switch(Sex.getSexPacePartner()) {
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
				if(Sex.getPartner().getSexPositionPreferences().contains(SexPosition.DOGGY_ORAL_PLAYER_SUB_PLAYER_ON_ALL_FOURS) || Sex.getPartner().getSexPositionPreferences().isEmpty()) {
					switch(Sex.getSexPacePartner()) {
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
				if(Sex.getPartner().getSexPositionPreferences().contains(SexPosition.DOGGY_PARTNER_AS_DOM_ON_ALL_FOURS) || Sex.getPartner().getSexPositionPreferences().isEmpty()) {
					switch(Sex.getSexPacePartner()) {
						case DOM_ROUGH:
							return "Slapping your [pc.arms] away from [npc.herHim], [npc.name] lets out an intimidating growl before roughly forcing you down onto your knees."
									+ " Much to your surprise, and delight, [npc.she] then drops down onto all fours in front of you, before shuffling back and rubbing [npc.her] [npc.ass+] against your crotch,"
									+ " [npc.speech(You want to fuck me, you little bitch?! Come on then, let's see if you've got what to takes to satisfy me!)]";
						default:
							return "A devious grin spreads across [npc.name]'s face as [npc.she] realises what it is you want."
									+ " Much to your delight, [npc.she] does exactly what you want, and drops down onto all fours in front of you, before shuffling back and rubbing [npc.her] [npc.ass+] against your crotch,"
									+ " [npc.speech(Come on then! This is what you wanted, isn't it?!)]";
					}
					
				} else {
					return "Slapping your [pc.arms] away from [npc.herHim], [npc.name] lets out an angry growl as [npc.she] shouts at you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(SexFlags.requestedKneeling) {
				if(Sex.getPartner().getSexPositionPreferences().contains(SexPosition.KNEELING_PLAYER_PERFORMING_ORAL) || Sex.getPartner().getSexPositionPreferences().isEmpty()) {
					switch(Sex.getSexPacePartner()) {
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
				if(Sex.getPartner().getSexPositionPreferences().contains(SexPosition.KNEELING_PARTNER_PERFORMING_ORAL) || Sex.getPartner().getSexPositionPreferences().isEmpty()) {
					switch(Sex.getSexPacePartner()) {
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
				if(Sex.getPartner().getSexPositionPreferences().contains(SexPosition.SIXTY_NINE_PARTNER_TOP) || Sex.getPartner().getSexPositionPreferences().isEmpty()) {
					switch(Sex.getSexPacePartner()) {
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
				if(Sex.getPartner().getSexPositionPreferences().contains(SexPosition.COWGIRL_PARTNER_TOP) || Sex.getPartner().getSexPositionPreferences().isEmpty()) {
					switch(Sex.getSexPacePartner()) {
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
			}
			
			return "";
		}

		@Override
		public void applyEffects() {
			if(SexFlags.requestedFaceToWall && (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.FACING_WALL_PLAYER) || Sex.getPartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMSubFaceToWall());
//				SexFlags.positioningBlockedPartner = true;
				
			} else if(SexFlags.requestedBackToWall && (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.BACK_TO_WALL_PLAYER) || Sex.getPartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMSubBackToWall());
//				SexFlags.positioningBlockedPartner = true;
				
			} else if(SexFlags.requestedDoggy && (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.DOGGY_PLAYER_ON_ALL_FOURS) || Sex.getPartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMSubDoggy());
//				SexFlags.positioningBlockedPartner = true;
				
			} else if(SexFlags.requestedDoggyOral && (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.DOGGY_ORAL_PLAYER_SUB_PARTNER_ON_ALL_FOURS) || Sex.getPartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMSubSelfDoggyOral());
//				SexFlags.positioningBlockedPartner = true;
				
			} else if(SexFlags.requestedDoggyReceiveOral && (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.DOGGY_ORAL_PLAYER_SUB_PLAYER_ON_ALL_FOURS) || Sex.getPartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMSubDoggyOral());
//				SexFlags.positioningBlockedPartner = true;
				
			} else if(SexFlags.requestedDomFuckedDoggy && (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.DOGGY_PARTNER_AS_DOM_ON_ALL_FOURS) || Sex.getPartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMSubSelfDoggy());
//				SexFlags.positioningBlockedPartner = true;
				
			} else if(SexFlags.requestedKneeling && (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.KNEELING_PLAYER_PERFORMING_ORAL) || Sex.getPartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMSubKneeling());
//				SexFlags.positioningBlockedPartner = true;
				
			} else if(SexFlags.requestedSelfKneeling && (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.KNEELING_PARTNER_PERFORMING_ORAL) || Sex.getPartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMSubSelfKneeling());
//				SexFlags.positioningBlockedPartner = true;
				
			} else if(SexFlags.requested69 && (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.SIXTY_NINE_PARTNER_TOP) || Sex.getPartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMSubSixtyNine());
//				SexFlags.positioningBlockedPartner = true;
				
			} else if(SexFlags.requestedCowgirl && (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.COWGIRL_PARTNER_TOP) || Sex.getPartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMSubCowgirl());
//				SexFlags.positioningBlockedPartner = true;
				
			}
			
//			SexFlags.positioningBlockedPlayer = true;
			SexFlags.resetRequests();
		}
	};
	
	// Partner positioning:
	
	
	public static final SexAction PARTNER_FORCE_POSITION_STANDING_FACE_TO_WALL = new SexAction(
			SexActionType.PARTNER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPartner
					&& Sex.getPosition() != SexPosition.FACING_WALL_PLAYER
					&& (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.FACING_WALL_PLAYER) || Sex.getPartner().getSexPositionPreferences().isEmpty())
					&& Sex.getPartner().hasPenis()
					&& !Sex.isPlayerDom();
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
			return "Taking hold of your shoulders, [npc.name] pushes you up against a nearby wall."
					+ " Grinding [npc.her] body up against your back, [npc.she] [npc.moans] into your [pc.ear], "
					+ "[npc.speech(Good [pc.girl]! Now hold still while I fuck you!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMSubFaceToWall());
			
//			SexFlags.positioningBlockedPartner = true;
//			SexFlags.positioningBlockedPlayer = true;
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PARTNER_FORCE_POSITION_STANDING_BACK_TO_WALL = new SexAction(
			SexActionType.PARTNER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPartner
					&& Sex.getPosition() != SexPosition.BACK_TO_WALL_PLAYER
					&& (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.BACK_TO_WALL_PLAYER) || Sex.getPartner().getSexPositionPreferences().isEmpty())
					&& !Sex.isPlayerDom();
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
			return "Taking hold of your shoulders, [npc.name] pushes you back against a nearby wall."
					+ " Grinding [npc.her] body up against yours, [npc.she] [npc.moans] into your [pc.ear], "
					+ "[npc.speech(Good [pc.girl]! Now hold still while I fuck you!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMSubBackToWall());

//			SexFlags.positioningBlockedPartner = true;
//			SexFlags.positioningBlockedPlayer = true;
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PARTNER_FORCE_POSITION_DOGGY = new SexAction(
			SexActionType.PARTNER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPartner
					&& Sex.getPosition() != SexPosition.DOGGY_PLAYER_ON_ALL_FOURS
					&& (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.DOGGY_PLAYER_ON_ALL_FOURS) || Sex.getPartner().getSexPositionPreferences().isEmpty())
					&& Sex.getPartner().hasPenis()
					&& !Sex.isPlayerDom();
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
			return "Taking hold of your shoulders, [npc.name] pushes you down on all fours."
					+ " Stepping around behind you, [npc.she] drops down onto [npc.her] knees, shuffling forwards to grind [npc.her] crotch against your [pc.ass+]."
					+ " Grabbing hold of your [pc.hips+], [npc.she] [npc.moans], "
					+ "[npc.speech(Good [pc.girl]! Now hold still while I fuck you like the bitch you are!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMSubDoggy());
			
//			SexFlags.positioningBlockedPartner = true;
//			SexFlags.positioningBlockedPlayer = true;
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PARTNER_FORCE_POSITION_DOGGY_ORAL = new SexAction(
			SexActionType.PARTNER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPartner
					&& Sex.getPosition() != SexPosition.DOGGY_ORAL_PLAYER_SUB_PLAYER_ON_ALL_FOURS
					&& (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.DOGGY_ORAL_PLAYER_SUB_PLAYER_ON_ALL_FOURS) || Sex.getPartner().getSexPositionPreferences().isEmpty())
					&& !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Doggy-style (oral)";
		}

		@Override
		public String getActionDescription() {
			return "Push [npc.name] down onto all fours and drop down on all fours behind [npc.herHim], ready to perform oral on [npc.herHim] in the doggy-style position.";
		}

		@Override
		public String getDescription() {
			return "Taking hold of your shoulders, [npc.name] pushes you down on all fours."
					+ " Stepping around behind you, [npc.she] similarly drops down onto all fours, shuffling forwards to bring [npc.her] [npc.face] up against your [pc.ass+]."
					+ " Once [npc.she]'s in position, [npc.she] [npc.moans], "
					+ "[npc.speech(Good [pc.girl]! Now hold still and enjoy this!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMSubDoggyOral());
			
//			SexFlags.positioningBlockedPartner = true;
//			SexFlags.positioningBlockedPlayer = true;
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PARTNER_FORCE_POSITION_DOGGY_SELF_ORAL = new SexAction(
			SexActionType.PARTNER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPartner
					&& Sex.getPosition() != SexPosition.DOGGY_ORAL_PLAYER_SUB_PARTNER_ON_ALL_FOURS
					&& (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.DOGGY_ORAL_PLAYER_SUB_PARTNER_ON_ALL_FOURS) || Sex.getPartner().getSexPositionPreferences().isEmpty())
					&& !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Doggy-style (receive oral)";
		}

		@Override
		public String getActionDescription() {
			return "Push [npc.name] down onto all fours and drop down on all fours in front of [npc.herHim], ready to receive oral on [npc.herHim] in the doggy-style position.";
		}

		@Override
		public String getDescription() {
			return "Taking hold of your shoulders, [npc.name] pushes you down on all fours."
					+ " Stepping around in front of you, [npc.she] similarly drops down onto all fours, shuffling backwards to bring [npc.her] [npc.ass+] up against your [pc.face+]."
					+ " Once [npc.she]'s in position, [npc.she] [npc.moans], "
					+ "[npc.speech(Good [pc.girl]! Now put that tongue of yours to use!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMSubSelfDoggyOral());
			
//			SexFlags.positioningBlockedPartner = true;
//			SexFlags.positioningBlockedPlayer = true;
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PARTNER_FORCE_POSITION_DOM_AS_DOGGY = new SexAction(
			SexActionType.PARTNER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPartner
					&& Sex.getPosition() != SexPosition.DOGGY_PARTNER_AS_DOM_ON_ALL_FOURS
					&& (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.DOGGY_PARTNER_AS_DOM_ON_ALL_FOURS) || Sex.getPartner().getSexPositionPreferences().isEmpty())
					&& !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Doggy-style";
		}

		@Override
		public String getActionDescription() {
			return "Push [pc.name] down onto [pc.her] knees before dropping down on all fours, ready for [pc.herHim] to fuck you in the doggy-style position.";
		}

		@Override
		public String getDescription() {
			return "Taking hold of your shoulders, [npc.name] pushes you down onto your knees."
					+ " Turing around, [npc.she] then drops down onto all fours, before shuffling back to grind [npc.her] [npc.ass+] against your crotch."
					+ " Reaching back and forcing your [pc.hands] to take hold of [npc.her] [npc.hips+], [npc.she] [npc.moans], "
					+ "[npc.speech(Come on! Fuck me like an animal!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMSubSelfDoggy());
			
//			SexFlags.positioningBlockedPartner = true;
//			SexFlags.positioningBlockedPlayer = true;
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PARTNER_FORCE_POSITION_KNEELING = new SexAction(
			SexActionType.PARTNER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPartner
					&& Sex.getPosition() != SexPosition.KNEELING_PLAYER_PERFORMING_ORAL
							&& (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.KNEELING_PLAYER_PERFORMING_ORAL) || Sex.getPartner().getSexPositionPreferences().isEmpty())
					&& !Sex.isPlayerDom();
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
			Sex.setSexManager(new SMSubKneeling());
			
//			SexFlags.positioningBlockedPartner = true;
//			SexFlags.positioningBlockedPlayer = true;
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PARTNER_FORCE_POSITION_SELF_KNEELING = new SexAction(
			SexActionType.PARTNER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPartner
					&& Sex.getPosition() != SexPosition.KNEELING_PARTNER_PERFORMING_ORAL
							&& (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.KNEELING_PARTNER_PERFORMING_ORAL) || Sex.getPartner().getSexPositionPreferences().isEmpty())
					&& !Sex.isPlayerDom();
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
			Sex.setSexManager(new SMSubSelfKneeling());
			
//			SexFlags.positioningBlockedPartner = true;
//			SexFlags.positioningBlockedPlayer = true;
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PARTNER_FORCE_POSITION_SIXTY_NINE = new SexAction(
			SexActionType.PARTNER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPartner
					&& Sex.getPosition() != SexPosition.SIXTY_NINE_PARTNER_TOP
					&& (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.SIXTY_NINE_PARTNER_TOP) || Sex.getPartner().getSexPositionPreferences().isEmpty())
					&& !Sex.isPlayerDom();
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
			return "Taking hold of your shoulders, [npc.name] pushes you down onto your back."
					+ " Quickly lowering [npc.herself] down onto all fours over the top of you, [npc.she] drops [npc.her] crotch down over your face as [npc.she] similarly positions [npc.her] own head over your groin."
					+ " Looking back beneath [npc.herHim], [npc.she] [npc.moans], "
					+ "[npc.speech(Good [pc.girl]! Now let's have some fun!)]";
			
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMSubSixtyNine());

//			SexFlags.positioningBlockedPartner = true;
//			SexFlags.positioningBlockedPlayer = true;
			SexFlags.resetRequests();
		}
	};
	
	public static final SexAction PARTNER_FORCE_POSITION_COWGIRL = new SexAction(
			SexActionType.PARTNER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPartner
					&& Sex.getPosition() != SexPosition.COWGIRL_PARTNER_TOP
					&& (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.COWGIRL_PARTNER_TOP) || Sex.getPartner().getSexPositionPreferences().isEmpty())
					&& !Sex.isPlayerDom();
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
			return "Taking hold of your shoulders, [npc.name] pushes you down onto your back."
					+ " [npc.She] then lowers [npc.herself] down on top of you, bringing [npc.her] crotch down to yours as [npc.she] straddles you in the cowgirl position."
					+ " Once [npc.she]'s made [npc.herself] comfortable, [npc.she] grins down at you, "
					+ "[npc.speech(Good [pc.girl]! Now let's have some fun!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMSubCowgirl());

//			SexFlags.positioningBlockedPartner = true;
//			SexFlags.positioningBlockedPlayer = true;
			SexFlags.resetRequests();
		}
	};
}
