package com.base.game.sex.sexActions.dominion.harpies;

import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.sex.ArousalIncrease;
import com.base.game.sex.Sex;
import com.base.game.sex.SexFlags;
import com.base.game.sex.SexPosition;
import com.base.game.sex.managers.universal.SMDomDoggy;
import com.base.game.sex.managers.universal.SMDomKneeling;
import com.base.game.sex.managers.universal.SMDomSixtyNine;
import com.base.game.sex.sexActions.SexAction;
import com.base.game.sex.sexActions.SexActionType;

/**
 * @since 0.1.8
 * @version 0.1.8
 * @author Innoxia
 */
public class HarpyPositioning {

	// Dom position changes:
	
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
	
}
