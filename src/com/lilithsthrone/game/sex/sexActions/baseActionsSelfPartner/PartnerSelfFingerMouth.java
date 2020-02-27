package com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.1.88
 * @author Innoxia
 */
public class PartnerSelfFingerMouth {
	
	public static final SexAction PARTNER_SELF_FINGER_MOUTH_LUBRICATION = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.hasLubricationTypeFromAnyone(Sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)
					&& Sex.isInForeplay(Sex.getCharacterPerformingAction())
					&& (Sex.getForeplayPreference((NPC) Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))!=null
						&& Sex.getForeplayPreference((NPC) Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this)).getPerformingSexArea()==SexAreaPenetration.FINGER);
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
			return "Lifting [npc.her] [npc.hand] up to [npc.her] mouth, [npc.name] slides [npc.her] [npc.fingers] past [npc.her] [npc.lips+],"
					+ " before lewdly sucking on them for a moment in order to get them well lubricated with [npc.her] saliva.";
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER, Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH);
		}
	};
	
	public static final SexAction PARTNER_SELF_FINGER_MOUTH_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
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
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
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
