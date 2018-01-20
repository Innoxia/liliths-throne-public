package com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;

/**
 * @since 0.1.79
 * @version 0.1.88
 * @author Innoxia
 */
public class PartnerSelfFingerMouth {
	
	public static final SexAction PARTNER_SELF_FINGER_MOUTH_LUBRICATION = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER,
			OrificeType.MOUTH,
			SexParticipantType.SELF) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getWetPenetrationTypes(Sex.getActivePartner()).get(PenetrationType.FINGER).isEmpty()
					&& Sex.isInForeplay()
					&& (Sex.getActivePartner().getForeplayPreference()!=null && Sex.getActivePartner().getForeplayPreference().getPenetrationType()==PenetrationType.FINGER);
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
			Sex.transferLubrication(Sex.getActivePartner(), Sex.getActivePartner(), PenetrationType.FINGER, OrificeType.MOUTH);
		}
	};
	
	public static final SexAction PARTNER_SELF_FINGER_MOUTH_PENETRATION = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER,
			OrificeType.MOUTH,
			SexParticipantType.SELF) {
		
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
			PenetrationType.FINGER,
			OrificeType.MOUTH,
			SexParticipantType.SELF) {
		
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
