package com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.79
 * @version 0.1.79
 * @author Innoxia
 */
public class PlayerSelfTailMouth {

	public static final SexAction PLAYER_SELF_TAIL_MOUTH_LUBRICATION = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL,
			OrificeType.MOUTH,
			SexParticipantType.SELF) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getWetPenetrationTypes(Main.game.getPlayer()).get(PenetrationType.TAIL).contains(LubricationType.PLAYER_SALIVA);
		}
		
		@Override
		public String getActionTitle() {
			return "Lubricate tail";
		}

		@Override
		public String getActionDescription() {
			return "Lubricate your tail with your saliva.";
		}

		@Override
		public String getDescription() {
			return "Lifting your [pc.tail+] up to your mouth, you slide your [pc.tail+] past your [pc.lips+], before lewdly sucking on it for a moment in order to get it well lubricated with your saliva.";
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Main.game.getPlayer(), Main.game.getPlayer(), PenetrationType.TAIL, OrificeType.MOUTH);
		}
	};
	
	public static final SexAction PLAYER_SELF_TAIL_MOUTH_PENETRATION = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL,
			OrificeType.MOUTH,
			SexParticipantType.SELF) {
		
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
	
	public static final SexAction PLAYER_SELF_FINGER_MOUTH_STOP_PENETRATION = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL,
			OrificeType.MOUTH,
			SexParticipantType.SELF) {
		
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
