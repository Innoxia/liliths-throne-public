package com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.2.9
 * @author Innoxia
 */
public class PlayerSelfFingerMouth {
	
	public static final SexAction PLAYER_SELF_FINGER_MOUTH_LUBRICATION = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.hasLubricationTypeFromAnyone(Main.game.getPlayer(), SexAreaPenetration.FINGER, LubricationType.SALIVA);
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
			Main.sex.transferLubrication(Main.game.getPlayer(), SexAreaPenetration.FINGER, Main.game.getPlayer(), SexAreaOrifice.MOUTH);
		}
	};
	
	public static final SexAction PLAYER_SELF_FINGER_MOUTH_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
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
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
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
