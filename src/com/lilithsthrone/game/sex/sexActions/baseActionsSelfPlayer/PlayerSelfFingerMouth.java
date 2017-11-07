package com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.79
 * @version 0.1.89
 * @author Innoxia
 */
public class PlayerSelfFingerMouth {
	
	public static final SexAction PLAYER_SELF_FINGER_MOUTH_LUBRICATION = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PLAYER,
			OrificeType.MOUTH_PLAYER) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getWetPenetrationTypes().get(PenetrationType.FINGER_PLAYER).contains(LubricationType.PLAYER_SALIVA);
		}
		
		@Override
		public String getActionTitle() {
			return "Lubricate fingers";
		}

		@Override
		public String getActionDescription() {
			return "Lubricate your fingers with your saliva.";
		}

		@Override
		public String getDescription() {
			return "Lifting your [pc.hand] up to your mouth, you slide your [pc.fingers] past your [pc.lips+], before lewdly sucking on them for a moment in order to get them well lubricated with your saliva.";
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Main.game.getPlayer(), Main.game.getPlayer(), PenetrationType.FINGER_PARTNER, OrificeType.MOUTH_PARTNER);
		}
	};
	
	public static final SexAction PLAYER_SELF_FINGER_MOUTH_PENETRATION = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PLAYER,
			OrificeType.MOUTH_PLAYER) {
		
		@Override
		public String getActionTitle() {
			return "Suck fingers (self)";
		}

		@Override
		public String getActionDescription() {
			return "Put your [pc.fingers] in your mouth and start sucking them.";
		}

		@Override
		public String getDescription() {
			return "Lifting your [pc.hand] up to your mouth, you slide your [pc.fingers] past your [pc.lips+] before starting to lewdly suck on your intruding digits.";
		}
	};
	
	public static final SexAction PLAYER_SELF_FINGER_MOUTH_STOP_PENETRATION = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PLAYER,
			OrificeType.MOUTH_PLAYER) {
		
		@Override
		public String getActionTitle() {
			return "Stop sucking fingers (self)";
		}

		@Override
		public String getActionDescription() {
			return "Stop sucking your [pc.fingers].";
		}

		@Override
		public String getDescription() {
			return "With a little sigh, you slide your saliva-coated [pc.fingers] out of your mouth.";
		}
	};
}
