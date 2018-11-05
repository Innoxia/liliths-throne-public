package com.lilithsthrone.game.sex.sexActions.universal;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.2.11
 * @author Innoxia
 */
public class Missionary {

	public static final SexAction SPREAD_LEGS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(
					new Value<>(SexAreaOrifice.ANUS, null),
					new Value<>(SexAreaOrifice.VAGINA, null)),
			SexParticipantType.SELF) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& (Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.MISSIONARY_ON_BACK
							|| Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.MISSIONARY_ON_BACK_SECOND
							|| Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.MISSIONARY_ON_BACK_THIRD
							|| Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.MISSIONARY_ON_BACK_FOURTH)
					&& !Sex.isMasturbation();
		}
		
		@Override
		public String getActionTitle() {
			return "Spread legs";
		}

		@Override
		public String getActionDescription() {
			return "Spread your [npc.legs] wide open, in order to encourage [npc2.name] to penetrate you.";
		}

		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getCharacterPerformingAction(), Sex.getTargetedPartner(Sex.getCharacterPerformingAction()),
					UtilText.returnStringAtRandom(
					"Looking up at [npc2.name], [npc.name] [npc.verb(bite)] [npc.her] [npc.lip] and [npc.verb(let)] out [npc.moan+], before spreading [npc.her] [npc.legs] and presenting [npc.herself], ready to be penetrated.",
					"[npc.Name] [npc.verb(spread)] [npc.her] [npc.legs] for [npc2.name], presenting [npc.herself] in anticipation of being penetrated.",
					"Pulling [npc.her] [npc.feet] back and to each side, [npc.name] [npc.verb(spread)] [npc.her] [npc.legs] as wide as [npc.she] can, enticing [npc2.name] to penetrate [npc.herHim].",
					"[npc.Name] [npc.verb(spread)] [npc.her] [npc.legs], looking up at [npc2.name] and biting [npc.her] [npc.lip] as [npc.she] entices [npc2.herHim] to penetrate [npc.herHim]."));
		}
	};
	
}
