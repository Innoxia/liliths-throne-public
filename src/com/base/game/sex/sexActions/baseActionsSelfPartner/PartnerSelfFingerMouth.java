package com.base.game.sex.sexActions.baseActionsSelfPartner;

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
public class PartnerSelfFingerMouth {
	public static final SexAction PARTNER_SELF_FINGER_MOUTH_PENETRATION = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PARTNER,
			OrificeType.MOUTH_PARTNER) {
		
		@Override
		public String getActionTitle() {
			return "Suck fingers (self)";
		}

		@Override
		public String getActionDescription() {
			return "Put your [npc.fingers] in your mouth and start sucking them.";
		}

		@Override
		public String getDescription() {
			return "Lifting [npc.her] [npc.hand] up to [npc.her] mouth, [npc.name] slides [npc.her] [npc.fingers] past [npc.her] [npc.lips+] before starting to lewdly suck on [npc.her] intruding digits.";
		}
	};
	
	public static final SexAction PARTNER_SELF_FINGER_MOUTH_STOP_PENETRATION = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PARTNER,
			OrificeType.MOUTH_PARTNER) {
		
		@Override
		public String getActionTitle() {
			return "Stop sucking fingers (self)";
		}

		@Override
		public String getActionDescription() {
			return "Stop sucking your [npc.fingers].";
		}

		@Override
		public String getDescription() {
			return "With a little sigh, [npc.name] slides [npc.her] saliva-coated [npc.fingers] out of [npc.her] mouth.";
		}
	};
}
