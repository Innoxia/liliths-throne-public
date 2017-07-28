package com.base.game.sex.sexActions.baseActionsSelfPlayer;

import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.sex.ArousalIncrease;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.sexActions.SexAction;
import com.base.game.sex.sexActions.SexActionType;

/**
 * @since 0.1.79
 * @version 0.1.79
 * @author Innoxia
 */
public class PlayerSelfTailMouth {
	public static SexAction PLAYER_SELF_TAIL_MOUTH_PENETRATION = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL_PLAYER,
			OrificeType.MOUTH_PLAYER) {
		
		@Override
		public String getActionTitle() {
			return "Suck tail (self)";
		}

		@Override
		public String getActionDescription() {
			return "Put your [pc.tail] in your mouth and start sucking it.";
		}

		@Override
		public String getDescription() {
			return "Snaking your [pc.tail] up to your mouth, you slide the tip past your [pc.lips+] before starting to lewdly suck on it.";
		}
	};
	
	public static SexAction PLAYER_SELF_FINGER_MOUTH_STOP_PENETRATION = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL_PLAYER,
			OrificeType.MOUTH_PLAYER) {
		
		@Override
		public String getActionTitle() {
			return "Stop sucking tail (self)";
		}

		@Override
		public String getActionDescription() {
			return "Stop sucking your [pc.tail].";
		}

		@Override
		public String getDescription() {
			return "With a little sigh, you slide your saliva-coated [pc.tail] out of your mouth.";
		}
	};
}
