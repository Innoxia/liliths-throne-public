package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.88
 * @version 0.2.8
 * @author Innoxia
 */
public class TongueNipple {

	public static final SexAction SUCKLE = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Suckle";
		}

		@Override
		public String getActionDescription() {
			return "Wrap your [npc.lips] around [npc2.namePos] [npc2.nipple+] and drink [npc2.her] [npc2.milk].";
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
							"[npc.Name] slowly [npc.verb(press)] [npc.her] [npc.lips+] against one of [npc2.namePos] [npc2.breasts+], before starting to gently suck and kiss [npc2.her] [npc2.nipple+].",

							"Gently pressing [npc.her] [npc.lips+] up against [npc2.namePos] [npc2.breast], [npc.name] [npc.verb(start)] slowly sucking and kissing [npc2.her] [npc2.nipple+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] eagerly [npc.verb(press)] [npc.her] [npc.lips+] against one of [npc2.namePos] [npc2.breasts+], before starting to greedily suck and kiss [npc2.her] [npc2.nipple+].",

							"Eagerly pressing [npc.her] [npc.lips+] up against [npc2.namePos] [npc2.breast], [npc.name] [npc.verb(start)] greedily sucking and kissing [npc2.her] [npc2.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] forcefully [npc.verb(press)] [npc.her] [npc.lips+] against one of [npc2.namePos] [npc2.breasts+], before starting to roughly suck and nipple [npc2.her] [npc2.nipple+].",

							"Roughly pressing [npc.her] [npc.lips+] up against [npc2.namePos] [npc2.breast], [npc.name] [npc.verb(start)] forcefully sucking and nibbling [npc2.her] [npc2.nipple+]."));
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
							" It only takes a moment before [npc2.her] [npc2.milk+] is trickling out into [npc.her] mouth,",

							" [npc.Name] quickly [npc.verb(get)] a taste of [npc2.her] [npc2.milk+] as it squirts out into [npc.her] mouth,"));
					break;
				case FOUR_LARGE_AMOUNT:
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before [npc2.her] [npc2.milk+] is flowing out into [npc.her] mouth,",

							" [npc.Name] quickly [npc.verb(get)] a taste of [npc2.her] [npc2.milk+] as it streams out into [npc.her] mouth,"));
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before [npc2.her] [npc2.milk+] is pouring out into [npc.her] mouth,",

							" [npc.Name] quickly [npc.verb(get)] a taste of [npc2.her] [npc2.milk+] as it gushes out into [npc.her] mouth,"));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and [npc2.name] can't [npc2.verb(help)] but [npc2.verb(let)] out a soft sigh in response to the deeply satisfying feeling.",

							" and, gently pulling [npc.her] head into [npc2.her] [npc2.breast+], [npc2.name] softly [npc2.verb(encourage)] [npc.herHim] to keep on suckling on [npc2.her] [npc2.nipple+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and [npc2.name] can't [npc2.verb(help)] but [npc2.verb(let)] out [npc2.a_moan+] in response to the deeply satisfying feeling.",

							" and, happily pulling [npc.her] head into [npc2.her] [npc2.breast+], [npc2.name] readily [npc2.verb(encourage)] [npc.herHim] to keep on suckling on [npc2.her] [npc2.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and [npc2.name] can't [npc2.verb(help)] but [npc2.verb(let)] out [npc2.a_moan+] in response to the deeply satisfying feeling.",

							" and, roughly forcing [npc.her] head into [npc2.her] [npc2.breast+], [npc2.name] [npc2.verb(order)] [npc.herHim] to keep on suckling on [npc2.her] [npc2.nipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and [npc2.name] can't [npc2.verb(help)] but [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] frantically [npc2.verb(try)] to push [npc.herHim] away from [npc2.herHim].",

							" and, desperately trying to push [npc.namePos] head away from [npc2.her] [npc2.breast+], [npc2.name] [npc2.verb(plead)] with [npc.herHim] to leave [npc2.herHim] alone."));
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Sex.getCharacterPerformingAction().ingestFluid(
						Sex.getCharacterTargetedForSexAction(this),
						Sex.getCharacterTargetedForSexAction(this).getMilkType(),
						SexAreaOrifice.MOUTH,
						Sex.getCharacterTargetedForSexAction(this).getBreastRawMilkStorageValue()/5,
						Sex.getCharacterTargetedForSexAction(this).getMilk().getFluidModifiers())
					+ Sex.getCharacterTargetedForSexAction(this).incrementBreastStoredMilk(-Sex.getCharacterTargetedForSexAction(this).getBreastRawMilkStorageValue()/5);
		}
	};
	
	public static final SexAction BREASTFEED = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Breastfeed";
		}

		@Override
		public String getActionDescription() {
			return "Pull [npc2.namePos] face into your [npc.breasts] and get [npc2.herHim] to start drinking your [npc.milk].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Sex.getCharacterPerformingAction().getBreastRawStoredMilkValue()>0;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc2.namePos] head, [npc.name] slowly guide [npc2.her] [npc2.lips+] up to one of [npc.her] [npc.breasts+],"
									+ " before gently encouraging [npc2.herHim] to suck and kiss [npc.her] [npc.nipple+].",

							"Taking hold of [npc2.namePos] head, [npc.name] gently [npc.verb(pull)] [npc2.herHim] into [npc.her] [npc.breasts+],"
									+ " before softly encouraging [npc2.herHim] to suck and kiss [npc.her] [npc.nipple+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc2.namePos] head, [npc.name] eagerly guide [npc2.her] [npc2.lips+] up to one of [npc.her] [npc.breasts+],"
									+ " before desperately encouraging [npc2.herHim] to suck and kiss [npc.her] [npc.nipple+].",

							"Taking hold of [npc2.namePos] head, [npc.name] desperately [npc.verb(pull)] [npc2.herHim] into [npc.her] [npc.breasts+],"
									+ " before eagerly encouraging [npc2.herHim] to suck and kiss [npc.her] [npc.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc2.namePos] head, [npc.name] roughly [npc.verb(force)] [npc2.her] [npc2.lips+] up against one of [npc.her] [npc.breasts+],"
									+ " before dominantly ordering [npc2.herHim] to suck and kiss [npc.her] [npc.nipple+].",

							"Taking hold of [npc2.namePos] head, [npc.name] forcefully [npc.verb(pull)] [npc2.herHim] into [npc.her] [npc.breasts+],"
									+ " before roughly ordering [npc2.herHim] to suck and kiss [npc.her] [npc.nipple+]."));
					break;
				default:
					break;
			}
			
			switch (Sex.getCharacterPerformingAction().getBreastStoredMilk()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append("[npc.Name] [npc.verb(feel)] no [npc.milk+] coming from [npc.namePos] [npc.breasts+],"); //Shouldn't be able to be reached
					break;
				case ONE_TRICKLE:
				case TWO_SMALL_AMOUNT:
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before [npc.namePos] [npc.milk+] is trickling out into [npc2.her] mouth,",

							" [npc2.Name] quickly [npc2.verb(get)] a taste of [npc.her] [npc.milk+] as it squirts out into [npc2.her] mouth,"));
					break;
				case FOUR_LARGE_AMOUNT:
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before [npc.namePos] [npc.milk+] is flowing out into [npc2.her] mouth,",

							" [npc2.Name] quickly [npc2.verb(get)] a taste of [npc.her] [npc.milk+] as it streams out into [npc2.her] mouth,"));
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before [npc.namePos] [npc.milk+] is pouring out into [npc2.her] mouth,",

							" [npc2.Name] quickly [npc2.verb(get)] a taste of [npc.her] [npc.milk+] as it gushes out into [npc2.her] mouth,"));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and, with a soft, muffled sigh, [npc2.she] buries [npc2.her] face in [npc.namePos] [npc.breasts+] and [npc2.verb(continue)] drinking [npc.her] [npc.milk].",

							" and, gently pressing [npc2.her] [npc2.face] into [npc.namePos] [npc.breast+], [npc2.she] [npc2.verb(continue)] softly suckling on [npc.her] [npc.nipple+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and, with a muffled, [npc2.moan+], [npc2.she] buries [npc2.her] face in [npc.namePos] [npc.breasts+] and [npc2.verb(continue)] drinking [npc.her] [npc.milk].",

							" and, happily pressing [npc2.her] [npc2.face] into [npc.namePos] [npc.breast+], [npc2.she] [npc2.verb(continue)] eagerly suckling on [npc.her] [npc.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and, with a muffled, [npc2.moan+], [npc2.she] roughly buries [npc2.her] face in [npc.namePos] [npc.breasts+] and [npc2.verb(continue)] greedily drinking [npc.her] [npc.milk].",

							" and, forcefully pressing [npc2.her] [npc2.face] into [npc.namePos] [npc.breast+], [npc2.she] [npc2.verb(continue)] roughly suckling on [npc.her] [npc.nipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and [npc2.she] can't [npc.verb(help)] but [npc.verb(let)] out [npc2.a_moan+] as [npc2.she] frantically [npc2.verb(try)] to push [npc.name] away from [npc2.herHim].",

							" and, desperately trying to pull away from [npc.namePos] [npc.breasts+], [npc2.she] [npc.verb(plead)] with [npc.herHim] to leave [npc2.herHim] alone."));
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Sex.getCharacterTargetedForSexAction(this).ingestFluid(
					Sex.getCharacterPerformingAction(),
					Sex.getCharacterPerformingAction().getMilkType(),
					SexAreaOrifice.MOUTH,
					Sex.getCharacterPerformingAction().getBreastRawMilkStorageValue()/5,
					Sex.getCharacterPerformingAction().getMilk().getFluidModifiers())
				+ Sex.getCharacterPerformingAction().incrementBreastStoredMilk(-Sex.getCharacterPerformingAction().getBreastRawMilkStorageValue()/5);
		}
		
	};
}
