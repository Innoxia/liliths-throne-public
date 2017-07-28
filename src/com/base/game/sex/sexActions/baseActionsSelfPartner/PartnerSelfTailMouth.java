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
public class PartnerSelfTailMouth {
	public static SexAction PARTNER_SELF_TAIL_MOUTH_PENETRATION = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL_PARTNER,
			OrificeType.MOUTH_PARTNER) {
		
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
			return "Snaking [npc.her] [npc.tail] up to [npc.her] mouth, [npc.name] slides the tip past [npc.her] [npc.lips+] before starting to lewdly suck on it.";
		}
	};
	
	public static SexAction PARTNER_SELF_FINGER_MOUTH_STOP_PENETRATION = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL_PARTNER,
			OrificeType.MOUTH_PARTNER) {
		
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
			return "With a little sigh, [npc.name] slides [npc.her] saliva-coated [npc.tail] out of [npc.her] mouth.";
		}
	};
}
