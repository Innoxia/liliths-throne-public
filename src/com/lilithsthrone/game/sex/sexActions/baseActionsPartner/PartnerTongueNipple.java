package com.lilithsthrone.game.sex.sexActions.baseActionsPartner;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.88
 * @version 0.2.1
 * @author Innoxia
 */
public class PartnerTongueNipple {

	public static final SexAction PARTNER_SUCKLE = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Suckle";
		}

		@Override
		public String getActionDescription() {
			return "Wrap [npc.namePos] [npc.lips] around [npc2.namePos] [npc2.nipple+] and [npc2.verb(start)] drinking [npc2.her] [npc2.milk].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] slowly [npc.verb(press)] [npc.her] [npc.lips+] against one of [npc2.namePos] [npc2.breasts+], before starting to gently suck and kiss [npc2.namePos] [npc2.nipple+].",

							"Gently pressing [npc.her] [npc.lips+] up against [npc2.namePos] [npc2.breast], [npc.name] [npc.verb(start)] slowly sucking and kissing [npc2.namePos] [npc2.nipple+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] eagerly [npc.verb(press)] [npc.her] [npc.lips+] against one of [npc2.namePos] [npc2.breasts+], before starting to greedily suck and kiss [npc2.namePos] [npc2.nipple+].",

							"Eagerly pressing [npc.her] [npc.lips+] up against [npc2.namePos] [npc2.breast], [npc.name] [npc.verb(start)] greedily sucking and kissing [npc2.namePos] [npc2.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] forcefully [npc.verb(press)] [npc.her] [npc.lips+] against one of [npc2.namePos] [npc2.breasts+], before starting to roughly suck and nipple [npc2.namePos] [npc2.nipple+].",

							"Roughly pressing [npc.her] [npc.lips+] up against [npc2.namePos] [npc2.breast], [npc.name] [npc.verb(start)] forcefully sucking and nibbling [npc2.namePos] [npc2.nipple+]."));
					break;
				default:
					break;
			}
			
			switch (Sex.getCharacterTargetedForSexAction(this).getBreastStoredMilk()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append("You [npc2.verb(feel)] no [npc2.milk+] coming from [npc2.namePos] [npc2.breasts+],"); //Shouldn't be able to be reached
					break;
				case ONE_TRICKLE:
				case TWO_SMALL_AMOUNT:
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before [npc2.namePos] [npc2.milk+] is trickling out into [npc.her] mouth,",

							" [npc2.Name] [npc2.verb(feel)] [npc2.namePos] [npc2.milk+] squirting out into [npc.her] mouth,"));
					break;
				case FOUR_LARGE_AMOUNT:
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before [npc2.namePos] [npc2.milk+] is flowing out into [npc.her] mouth,",

							" [npc2.Name] [npc2.verb(feel)] [npc2.namePos] [npc2.milk+] streaming out into [npc.her] mouth,"));
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before [npc2.namePos] [npc2.milk+] is pouring out into [npc.her] mouth,",

							" [npc2.Name] [npc2.verb(feel)] [npc2.namePos] [npc2.milk+] gushing out into [npc.her] mouth,"));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and [npc2.name] can't [npc2.verb(help)] but [npc2.verb(let)] out a soft sigh in response to the deeply satisfying feeling.",

							" and, gently pulling [npc.her] head into [npc2.namePos] [npc2.breast+], [npc2.name] softly [npc2.verb(encourage)] [npc.herHim] to keep on suckling on [npc2.namePos] [npc2.nipple+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and [npc2.name] can't [npc2.verb(help)] but [npc2.verb(let)] out [npc2.a_moan+] in response to the deeply satisfying feeling.",

							" and, happily pulling [npc.her] head into [npc2.namePos] [npc2.breast+], [npc2.name] readily [npc2.verb(encourage)] [npc.herHim] to keep on suckling on [npc2.namePos] [npc2.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and [npc2.name] can't [npc2.verb(help)] but [npc2.verb(let)] out [npc2.a_moan+] in response to the deeply satisfying feeling.",

							" and, roughly forcing [npc.her] head into [npc2.namePos] [npc2.breast+], [npc2.name] order [npc.herHim] to keep on suckling on [npc2.namePos] [npc2.nipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and [npc2.name] can't [npc2.verb(help)] but [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] frantically [npc2.verb(try)] to push [npc.herHim] away from [npc2.herHim].",

							" and, desperately trying to push [npc.her] head away from [npc2.namePos] [npc2.breast+], [npc2.name] [npc2.verb(plead)] with [npc.herHim] to leave [npc2.name] alone."));
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Sex.getCharacterPerformingAction().ingestFluid(Sex.getCharacterTargetedForSexAction(this), Sex.getCharacterTargetedForSexAction(this).getMilkType(), SexAreaOrifice.MOUTH, Sex.getCharacterTargetedForSexAction(this).getBreastRawMilkStorageValue()/5, Sex.getCharacterTargetedForSexAction(this).getMilk().getFluidModifiers())
					+ Sex.getCharacterTargetedForSexAction(this).incrementBreastStoredMilk(-Sex.getCharacterTargetedForSexAction(this).getBreastRawMilkStorageValue()/5);
		}
	};
	
	public static final SexAction PLAYER_BREASTFEED = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Breastfeed";
		}

		@Override
		public String getActionDescription() {
			return "Pull [npc.namePos] face into [npc2.namePos] [npc2.breasts] and get [npc.herHim] to start drinking [npc2.namePos] [npc2.milk].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING
					&& Sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc.namePos] head, [npc2.name] slowly guide [npc.her] [npc.lips+] up to one of [npc2.namePos] [npc2.breasts+], before gently encouraging [npc.herHim] to suck and kiss [npc2.namePos] [npc2.nipple+].",

							"Taking hold of [npc.namePos] head, [npc2.name] gently [npc2.verb(pull)] [npc.herHim] into [npc2.namePos] [npc2.breasts+], before softly encouraging [npc.herHim] to suck and kiss [npc2.namePos] [npc2.nipple+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc.namePos] head, [npc2.name] eagerly guide [npc.her] [npc.lips+] up to one of [npc2.namePos] [npc2.breasts+], before desperately encouraging [npc.herHim] to suck and kiss [npc2.namePos] [npc2.nipple+].",

							"Taking hold of [npc.namePos] head, [npc2.name] desperately [npc2.verb(pull)] [npc.herHim] into [npc2.namePos] [npc2.breasts+], before eagerly encouraging [npc.herHim] to suck and kiss [npc2.namePos] [npc2.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc.namePos] head, [npc2.name] roughly [npc2.verb(force)] [npc.her] [npc.lips+] up against one of [npc2.namePos] [npc2.breasts+], before dominantly ordering [npc.herHim] to suck and kiss [npc2.namePos] [npc2.nipple+].",

							"Taking hold of [npc.namePos] head, [npc2.name] forcefully [npc2.verb(pull)] [npc.herHim] into [npc2.namePos] [npc2.breasts+], before roughly ordering [npc.herHim] to suck and kiss [npc2.namePos] [npc2.nipple+]."));
					break;
				default:
					break;
			}
			
			switch (Sex.getCharacterTargetedForSexAction(this).getBreastStoredMilk()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append("You [npc2.verb(feel)] no [npc2.milk+] coming from [npc2.namePos] [npc2.breasts+],"); //Shouldn't be able to be reached
					break;
				case ONE_TRICKLE:
				case TWO_SMALL_AMOUNT:
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before [npc2.namePos] [npc2.milk+] is trickling out into [npc.her] mouth,",

							" [npc2.Name] [npc2.verb(feel)] [npc2.namePos] [npc2.milk+] squirting out into [npc.her] mouth,"));
					break;
				case FOUR_LARGE_AMOUNT:
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before [npc2.namePos] [npc2.milk+] is flowing out into [npc.her] mouth,",

							" [npc2.Name] [npc2.verb(feel)] [npc2.namePos] [npc2.milk+] streaming out into [npc.her] mouth,"));
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before [npc2.namePos] [npc2.milk+] is pouring out into [npc.her] mouth,",

							" [npc2.Name] [npc2.verb(feel)] [npc2.namePos] [npc2.milk+] gushing out into [npc.her] mouth,"));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and, with a soft, muffled sigh, [npc.she] buries [npc.her] face in [npc2.namePos] [npc2.breasts+] and [npc.verb(continue)] drinking [npc2.namePos] [npc2.milk].",

							" and, gently pressing [npc.her] [npc.face] into [npc2.namePos] [npc2.breast+], [npc.she] [npc.verb(continue)] softly suckling on [npc2.namePos] [npc2.nipple+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and, with a muffled, [npc.moan+], [npc.she] buries [npc.her] face in [npc2.namePos] [npc2.breasts+] and [npc.verb(continue)] drinking [npc2.namePos] [npc2.milk].",

							" and, happily pressing [npc.her] [npc.face] into [npc2.namePos] [npc2.breast+], [npc.she] [npc.verb(continue)] eagerly suckling on [npc2.namePos] [npc2.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and, with a muffled, [npc.moan+], [npc.she] roughly buries [npc.her] face in [npc2.namePos] [npc2.breasts+] and [npc.verb(continue)] greedily drinking [npc2.namePos] [npc2.milk].",

							" and, forcefully pressing [npc.her] [npc.face] into [npc2.namePos] [npc2.breast+], [npc.she] [npc.verb(continue)] roughly suckling on [npc2.namePos] [npc2.nipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and [npc.she] can't [npc2.verb(help)] but [npc2.verb(let)] out [npc.a_moan+] as [npc.she] frantically tries to push [npc2.name] away from [npc.herHim].",

							" and, desperately trying to pull away from [npc2.namePos] [npc2.breasts+], [npc.she] [npc2.verb(plead)]s with [npc2.name] to leave [npc.herHim] alone."));
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Sex.getCharacterPerformingAction().ingestFluid(Sex.getCharacterTargetedForSexAction(this), Sex.getCharacterTargetedForSexAction(this).getMilkType(), SexAreaOrifice.MOUTH, Sex.getCharacterTargetedForSexAction(this).getBreastRawMilkStorageValue()/5, Sex.getCharacterTargetedForSexAction(this).getMilk().getFluidModifiers())
					+ Sex.getCharacterTargetedForSexAction(this).incrementBreastStoredMilk(-Sex.getCharacterTargetedForSexAction(this).getBreastRawMilkStorageValue()/5);
		}
		
	};
}
