package com.lilithsthrone.game.sex.sexActions.universal;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.SexSlotBipeds;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionType;

/**
 * @since 0.2.8
 * @version 0.2.11
 * @author Innoxia
 */
public class Missionary {

	public static final SexAction SPREAD_LEGS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Spread legs";
		}

		@Override
		public String getActionDescription() {
			return "Spread your [npc.legs] wide open for [npc2.name].";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& (Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexSlotBipeds.MISSIONARY_ON_BACK
							|| Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexSlotBipeds.MISSIONARY_ON_BACK_SECOND
							|| Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexSlotBipeds.MISSIONARY_ON_BACK_THIRD
							|| Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexSlotBipeds.MISSIONARY_ON_BACK_FOURTH)
					&& (Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexSlotBipeds.MISSIONARY_KNEELING_BETWEEN_LEGS
						|| Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexSlotBipeds.MISSIONARY_KNEELING_BETWEEN_LEGS_SECOND)
					&& !Sex.isMasturbation();
		}

		@Override
		public String getDescription() {
			
			boolean vaginalSex = false;
			try {
				vaginalSex = Sex.getOngoingActionsMap(Sex.getCharacterTargetedForSexAction(this)).get(SexAreaPenetration.PENIS).get(Sex.getCharacterPerformingAction()).contains(SexAreaOrifice.VAGINA);
			} catch(Exception ex) {
			}
			boolean analSex = false;
			try {
				analSex = Sex.getOngoingActionsMap(Sex.getCharacterTargetedForSexAction(this)).get(SexAreaPenetration.PENIS).get(Sex.getCharacterPerformingAction()).contains(SexAreaOrifice.ANUS);
			} catch(Exception ex) {
			} 
			
			if(vaginalSex) {
				return UtilText.parse(Sex.getCharacterPerformingAction(), Sex.getTargetedPartner(Sex.getCharacterPerformingAction()),
						UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(bite)] [npc.her] [npc.lip] and [npc.verb(let)] out [npc.moan+],"
								+ " before spreading [npc.her] [npc.legs] in order to help sink [npc2.namePos] [npc.cock+] deeper into [npc.her] [npc.pussy+].",
						"[npc.Name] [npc.verb(spread)] [npc.her] [npc.legs] for [npc2.name], helping [npc2.herHim] to thrust [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+].",
						"Pulling [npc.her] [npc.feet] back and to each side,"
								+ " [npc.name] [npc.verb(help)] [npc2.name] to thrust [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+] by spreading [npc.her] [npc.legs] as wide as [npc.she] can.",
						"[npc.Name] [npc.verb(spread)] [npc.her] [npc.legs], looking up at [npc2.name] and biting [npc.her] [npc.lip] as [npc2.she] [npc2.verb(continue)] fucking [npc.her] [npc.pussy+]."));
			}
			if(analSex) {
				return UtilText.parse(Sex.getCharacterPerformingAction(), Sex.getTargetedPartner(Sex.getCharacterPerformingAction()),
						UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(bite)] [npc.her] [npc.lip] and [npc.verb(let)] out [npc.moan+],"
								+ " before raising [npc.her] [npc.ass+] and spreading [npc.her] [npc.legs] in order to help sink [npc2.namePos] [npc.cock+] deeper into [npc.her] [npc.asshole+].",
						"[npc.Name] [npc.verb(spread)] [npc.her] [npc.legs] and [npc.verb(raise)] [npc.her] [npc.ass+] for [npc2.name], helping [npc2.herHim] to thrust [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+].",
						"Pulling [npc.her] [npc.feet] back and to each side,"
								+ " [npc.name] [npc.verb(help)] [npc2.name] to thrust [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+] by raising [npc.her] [npc.ass] and spreading [npc.her] [npc.legs] as wide as [npc.she] can.",
						"[npc.Name] [npc.verb(spread)] [npc.her] [npc.legs] and [npc.verb(raise)] [npc.her] [npc.ass+],"
								+ " looking up at [npc2.name] and biting [npc.her] [npc.lip] as [npc2.she] [npc2.verb(continue)] fucking [npc.her] [npc.asshole+]."));
			}
			
			return UtilText.parse(Sex.getCharacterPerformingAction(), Sex.getTargetedPartner(Sex.getCharacterPerformingAction()),
					UtilText.returnStringAtRandom(
					"Looking up at [npc2.name], [npc.name] [npc.verb(bite)] [npc.her] [npc.lip] and [npc.verb(let)] out [npc.moan+], before spreading [npc.her] [npc.legs] and submissively presenting [npc.herself], ready to be penetrated.",
					"[npc.Name] [npc.verb(spread)] [npc.her] [npc.legs] for [npc2.name], presenting [npc.herself] in anticipation of being penetrated.",
					"Pulling [npc.her] [npc.feet] back and to each side, [npc.name] [npc.verb(spread)] [npc.her] [npc.legs] as wide as [npc.she] can, enticing [npc2.name] to penetrate [npc.herHim].",
					"[npc.Name] [npc.verb(spread)] [npc.her] [npc.legs], looking up at [npc2.name] and biting [npc.her] [npc.lip] as [npc.she] [npc.verb(entice)] [npc2.herHim] to penetrate [npc.herHim]."));
		}
	};
	
}
