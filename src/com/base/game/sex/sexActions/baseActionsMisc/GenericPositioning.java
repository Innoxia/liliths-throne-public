package com.base.game.sex.sexActions.baseActionsMisc;

import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.sex.ArousalIncrease;
import com.base.game.sex.Sex;
import com.base.game.sex.SexFlags;
import com.base.game.sex.SexPosition;
import com.base.game.sex.managers.universal.SMDomBackToWall;
import com.base.game.sex.managers.universal.SMDomDoggy;
import com.base.game.sex.managers.universal.SMDomFaceToWall;
import com.base.game.sex.managers.universal.SMDomSelfKneeling;
import com.base.game.sex.managers.universal.SMDomKneeling;
import com.base.game.sex.managers.universal.SMDomSixtyNine;
import com.base.game.sex.managers.universal.SMSubBackToWall;
import com.base.game.sex.managers.universal.SMSubDoggy;
import com.base.game.sex.managers.universal.SMSubFaceToWall;
import com.base.game.sex.managers.universal.SMSubKneeling;
import com.base.game.sex.managers.universal.SMSubSelfKneeling;
import com.base.game.sex.managers.universal.SMSubSixtyNine;
import com.base.game.sex.sexActions.SexAction;
import com.base.game.sex.sexActions.SexActionPriority;
import com.base.game.sex.sexActions.SexActionType;

/**
 * Contains all positional changes for both sub and dom.
 * 
 * If sub, positional change is just a suggestion, which the NPC may refuse if they have other preferences.
 * 
 * 
 * @since 0.1.79
 * @version 0.1.82
 * @author Innoxia
 */
public class GenericPositioning {

	// Dom position changes:
	
	public static SexAction PLAYER_FORCE_POSITION_STANDING_FACE_TO_WALL = new SexAction(
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
			return "Taking hold of [npc.name]'s shoulders, you push [npc.her] up against a nearby wall."
					+ " Grinding your body up against [npc.her] back, you [pc.moan] into [npc.her] [npc.ear], "
					+ "[pc.speech(Be a good [npc.girl] and hold still while I fuck you!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMDomFaceToWall());
			
			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};
	
	public static SexAction PLAYER_FORCE_POSITION_STANDING_BACK_TO_WALL = new SexAction(
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
			
			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};
	
	public static SexAction PLAYER_FORCE_POSITION_DOGGY = new SexAction(
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
			
			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};
	
	public static SexAction PLAYER_FORCE_POSITION_KNEELING = new SexAction(
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
			
			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};

	public static SexAction PLAYER_FORCE_POSITION_SELF_KNEELING = new SexAction(
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

			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};
	
	public static SexAction PLAYER_FORCE_POSITION_SIXTY_NINE = new SexAction(
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
			return "Taking hold of [npc.name]'s shoulders, you push [npc.her] down onto [npc.her] back."
					+ " You then lower yourself down onto all fours over the top of [npc.herHim], lowering your crotch down to [npc.her] face as you similarly position your own head over [npc.her] groin."
					+ " Looking back beneath you, you [pc.moan], "
					+ "[pc.speech(Good [npc.girl]! Now let's have some fun!)]";
			
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMDomSixtyNine());
		}
	};
	
	
	// Sub position changes with associated partner response:
	
	public static SexAction PLAYER_POSITION_REQUEST_STANDING_FACE_TO_WALL = new SexAction(
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
	
	public static SexAction PLAYER_POSITION_REQUEST_STANDING_BACK_TO_WALL = new SexAction(
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
	
	public static SexAction PLAYER_POSITION_REQUEST_DOGGY = new SexAction(
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
	
	public static SexAction PLAYER_POSITION_REQUEST_KNEELING = new SexAction(
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
	
	public static SexAction PLAYER_POSITION_REQUEST_SELF_KNEELING = new SexAction(
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
	
	public static SexAction PLAYER_POSITION_REQUEST_SIXTY_NINE = new SexAction(
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
	
	public static SexAction PARTNER_POSITION_RESPONSE = new SexAction(
			SexActionType.PARTNER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return (SexFlags.requested69
					|| SexFlags.requestedDoggy
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
									+ " [npc.speech(Luckily for you, this is what I panning all along! Now stay still bitch, you'd better appreciate this!)]";
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
									+ " Turning [npc.her] head back to look at you, she growls, "
									+ "[npc.speech(Good idea slut! Now <i>stay still</i> so I can use you properly!)]";
						default:
							return "Jumping down onto all fours, [npc.name] lowers [npc.herself] down over the top of you, bringing [npc.her] crotch down to your face as [npc.she] drops [npc.her] head down between your [pc.legs]."
									+ " Turning [npc.her] head back to look at you, she grins, "
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
				SexFlags.positioningBlockedPartner = true;
				
			} else if(SexFlags.requestedBackToWall && (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.BACK_TO_WALL_PLAYER) || Sex.getPartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMSubBackToWall());
				SexFlags.positioningBlockedPartner = true;
				
			} else if(SexFlags.requestedDoggy && (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.DOGGY_PLAYER_ON_ALL_FOURS) || Sex.getPartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMSubDoggy());
				SexFlags.positioningBlockedPartner = true;
				
			} else if(SexFlags.requestedKneeling && (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.KNEELING_PLAYER_PERFORMING_ORAL) || Sex.getPartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMSubKneeling());
				SexFlags.positioningBlockedPartner = true;
				
			} else if(SexFlags.requestedSelfKneeling && (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.KNEELING_PARTNER_PERFORMING_ORAL) || Sex.getPartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMSubSelfKneeling());
				SexFlags.positioningBlockedPartner = true;
				
			} else if(SexFlags.requested69 && (Sex.getPartner().getSexPositionPreferences().contains(SexPosition.SIXTY_NINE_PARTNER_TOP) || Sex.getPartner().getSexPositionPreferences().isEmpty())) {
				Sex.setSexManager(new SMSubSixtyNine());
				SexFlags.positioningBlockedPartner = true;
				
			}
			
			SexFlags.positioningBlockedPlayer = true;
			SexFlags.resetRequests();
		}
	};
	
	// Partner positioning:
	
	
	public static SexAction PARTNER_FORCE_POSITION_STANDING_FACE_TO_WALL = new SexAction(
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
			
			SexFlags.positioningBlockedPartner = true;
			SexFlags.positioningBlockedPlayer = true;
			SexFlags.resetRequests();
		}
	};
	
	public static SexAction PARTNER_FORCE_POSITION_STANDING_BACK_TO_WALL = new SexAction(
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

			SexFlags.positioningBlockedPartner = true;
			SexFlags.positioningBlockedPlayer = true;
			SexFlags.resetRequests();
		}
	};
	
	public static SexAction PARTNER_FORCE_POSITION_DOGGY = new SexAction(
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
			
			SexFlags.positioningBlockedPartner = true;
			SexFlags.positioningBlockedPlayer = true;
			SexFlags.resetRequests();
		}
	};
	
	public static SexAction PARTNER_FORCE_POSITION_KNEELING = new SexAction(
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
			
			SexFlags.positioningBlockedPartner = true;
			SexFlags.positioningBlockedPlayer = true;
			SexFlags.resetRequests();
		}
	};
	
	public static SexAction PARTNER_FORCE_POSITION_SELF_KNEELING = new SexAction(
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
			
			SexFlags.positioningBlockedPartner = true;
			SexFlags.positioningBlockedPlayer = true;
			SexFlags.resetRequests();
		}
	};
	
	public static SexAction PARTNER_FORCE_POSITION_SIXTY_NINE = new SexAction(
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

			SexFlags.positioningBlockedPartner = true;
			SexFlags.positioningBlockedPlayer = true;
			SexFlags.resetRequests();
		}
	};
}
