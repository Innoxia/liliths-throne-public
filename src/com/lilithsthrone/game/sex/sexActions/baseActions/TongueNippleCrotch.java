package com.lilithsthrone.game.sex.sexActions.baseActions;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.1
 * @version 0.3.1
 * @author Innoxia
 */
public class TongueNippleCrotch {

	public static final SexAction SUCKLE = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Suckle udders";
			} else {
				return "Suckle crotch-boobs";
			}
		}

		@Override
		public String getActionDescription() {
			return "Wrap your [npc.lips] around [npc2.namePos] [npc2.crotchNipple+] and drink [npc2.her] [npc2.crotchMilk].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).hasBreastsCrotch()
					&& Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()>0;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] slowly [npc.verb(press)] [npc.her] [npc.lips+] against one of [npc2.namePos] [npc2.crotchBoobs+], before starting to gently suck and kiss [npc2.her] [npc2.crotchNipple+].",

							"Gently pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob], [npc.name] [npc.verb(start)] slowly sucking and kissing [npc2.her] [npc2.crotchNipple+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] eagerly [npc.verb(press)] [npc.her] [npc.lips+] against one of [npc2.namePos] [npc2.crotchBoobs+], before starting to greedily suck and kiss [npc2.her] [npc2.crotchNipple+].",

							"Eagerly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob], [npc.name] [npc.verb(start)] greedily sucking and kissing [npc2.her] [npc2.crotchNipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] forcefully [npc.verb(press)] [npc.her] [npc.lips+] against one of [npc2.namePos] [npc2.crotchBoobs+], before starting to roughly suck and nipple [npc2.her] [npc2.crotchNipple+].",

							"Roughly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob], [npc.name] [npc.verb(start)] forcefully sucking and nibbling [npc2.her] [npc2.crotchNipple+]."));
					break;
				default:
					break;
			}
			
			switch (Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchStoredMilk()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append("You [npc2.verb(feel)] no [npc2.crotchMilk+] coming from [npc2.namePos] [npc2.crotchBoobs+],"); //Shouldn't be able to be reached
					break;
				case ONE_TRICKLE:
				case TWO_SMALL_AMOUNT:
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before [npc2.her] [npc2.crotchMilk+] is trickling out into [npc.her] mouth,",

							" [npc.Name] quickly [npc.verb(get)] a taste of [npc2.her] [npc2.crotchMilk+] as it squirts out into [npc.her] mouth,"));
					break;
				case FOUR_LARGE_AMOUNT:
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before [npc2.her] [npc2.crotchMilk+] is flowing out into [npc.her] mouth,",

							" [npc.Name] quickly [npc.verb(get)] a taste of [npc2.her] [npc2.crotchMilk+] as it streams out into [npc.her] mouth,"));
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before [npc2.her] [npc2.crotchMilk+] is pouring out into [npc.her] mouth,",

							" [npc.Name] quickly [npc.verb(get)] a taste of [npc2.her] [npc2.crotchMilk+] as it gushes out into [npc.her] mouth,"));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and [npc2.name] can't help but let out a soft sigh in response to the deeply satisfying feeling.",

							" and, gently pulling [npc.her] head into [npc2.her] [npc2.crotchBoob+], [npc2.name] softly [npc2.verb(encourage)] [npc.herHim] to keep on suckling on [npc2.her] [npc2.crotchNipple+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and [npc2.name] can't help but let out [npc2.a_moan+] in response to the deeply satisfying feeling.",

							" and, happily pulling [npc.her] head into [npc2.her] [npc2.crotchBoob+], [npc2.name] readily [npc2.verb(encourage)] [npc.herHim] to keep on suckling on [npc2.her] [npc2.crotchNipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and [npc2.name] can't help but let out [npc2.a_moan+] in response to the deeply satisfying feeling.",

							" and, roughly forcing [npc.her] head into [npc2.her] [npc2.crotchBoob+], [npc2.name] [npc2.verb(order)] [npc.herHim] to keep on suckling on [npc2.her] [npc2.crotchNipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and [npc2.name] can't help but let out [npc2.a_sob+] as [npc2.she] frantically [npc2.verb(try)] to push [npc.herHim] away from [npc2.herHim].",

							" and, desperately trying to push [npc.namePos] head away from [npc2.her] [npc2.crotchBoob+], [npc2.name] [npc2.verb(plead)] with [npc.herHim] to leave [npc2.herHim] alone."));
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			float suckleAmount = Math.max(5, Math.min(100, Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawMilkStorageValue()/5));
			
			if(suckleAmount>Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()) {
				suckleAmount = Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue();
			}
			
			return Main.sex.getCharacterPerformingAction().ingestFluid(
						Main.sex.getCharacterTargetedForSexAction(this),
						Main.sex.getCharacterTargetedForSexAction(this).getMilk(),
						SexAreaOrifice.MOUTH,
						suckleAmount)
					+ Main.sex.getCharacterTargetedForSexAction(this).incrementBreastCrotchStoredMilk(-suckleAmount);
		}
	};
	
	public static final SexAction BREASTFEED = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Breastfeed (udders)";
			} else {
				return "Breastfeed (crotch-boobs)";
			}
		}

		@Override
		public String getActionDescription() {
			return "Pull [npc2.namePos] face into your [npc.crotchBoobs] and get [npc2.herHim] to start drinking your [npc.crotchMilk].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterPerformingAction().hasBreastsCrotch()
					&& Main.sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedCrotchBoobs()
					&& Main.sex.getCharacterPerformingAction().getBreastCrotchRawStoredMilkValue()>0;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc2.namePos] head, [npc.name] slowly guide [npc2.her] [npc2.lips+] to one of [npc.her] [npc.crotchBoobs+],"
									+ " before gently encouraging [npc2.herHim] to suck and kiss [npc.her] [npc.crotchNipple+].",

							"Taking hold of [npc2.namePos] head, [npc.name] gently [npc.verb(pull)] [npc2.herHim] into [npc.her] [npc.crotchBoobs+],"
									+ " before softly encouraging [npc2.herHim] to suck and kiss [npc.her] [npc.crotchNipple+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc2.namePos] head, [npc.name] eagerly guide [npc2.her] [npc2.lips+] to one of [npc.her] [npc.crotchBoobs+],"
									+ " before desperately encouraging [npc2.herHim] to suck and kiss [npc.her] [npc.crotchNipple+].",

							"Taking hold of [npc2.namePos] head, [npc.name] desperately [npc.verb(pull)] [npc2.herHim] into [npc.her] [npc.crotchBoobs+],"
									+ " before eagerly encouraging [npc2.herHim] to suck and kiss [npc.her] [npc.crotchNipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc2.namePos] head, [npc.name] roughly [npc.verb(force)] [npc2.her] [npc2.lips+] against one of [npc.her] [npc.crotchBoobs+],"
									+ " before dominantly ordering [npc2.herHim] to suck and kiss [npc.her] [npc.crotchNipple+].",

							"Taking hold of [npc2.namePos] head, [npc.name] forcefully [npc.verb(pull)] [npc2.herHim] into [npc.her] [npc.crotchBoobs+],"
									+ " before roughly ordering [npc2.herHim] to suck and kiss [npc.her] [npc.crotchNipple+]."));
					break;
				default:
					break;
			}
			
			switch (Main.sex.getCharacterPerformingAction().getBreastCrotchStoredMilk()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append("[npc.Name] [npc.verb(feel)] no [npc.crotchMilk+] coming from [npc.namePos] [npc.crotchBoobs+],"); //Shouldn't be able to be reached
					break;
				case ONE_TRICKLE:
				case TWO_SMALL_AMOUNT:
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before [npc.namePos] [npc.crotchMilk+] is trickling out into [npc2.her] mouth,",

							" [npc2.Name] quickly [npc2.verb(get)] a taste of [npc.her] [npc.crotchMilk+] as it squirts out into [npc2.her] mouth,"));
					break;
				case FOUR_LARGE_AMOUNT:
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before [npc.namePos] [npc.crotchMilk+] is flowing out into [npc2.her] mouth,",

							" [npc2.Name] quickly [npc2.verb(get)] a taste of [npc.her] [npc.crotchMilk+] as it streams out into [npc2.her] mouth,"));
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before [npc.namePos] [npc.crotchMilk+] is pouring out into [npc2.her] mouth,",

							" [npc2.Name] quickly [npc2.verb(get)] a taste of [npc.her] [npc.crotchMilk+] as it gushes out into [npc2.her] mouth,"));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and, with a soft, muffled sigh, [npc2.she] buries [npc2.her] face in [npc.namePos] [npc.crotchBoobs+] and [npc2.verb(continue)] drinking [npc.her] [npc.crotchMilk].",

							" and, gently pressing [npc2.her] [npc2.face] into [npc.namePos] [npc.crotchBoob+], [npc2.she] [npc2.verb(continue)] softly suckling on [npc.her] [npc.crotchNipple+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and, with a muffled, [npc2.moan+], [npc2.she] buries [npc2.her] face in [npc.namePos] [npc.crotchBoobs+] and [npc2.verb(continue)] drinking [npc.her] [npc.crotchMilk].",

							" and, happily pressing [npc2.her] [npc2.face] into [npc.namePos] [npc.crotchBoob+], [npc2.she] [npc2.verb(continue)] eagerly suckling on [npc.her] [npc.crotchNipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and, with a muffled, [npc2.moan+], [npc2.she] roughly buries [npc2.her] face in [npc.namePos] [npc.crotchBoobs+] and [npc2.verb(continue)] greedily drinking [npc.her] [npc.crotchMilk].",

							" and, forcefully pressing [npc2.her] [npc2.face] into [npc.namePos] [npc.crotchBoob+], [npc2.she] [npc2.verb(continue)] roughly suckling on [npc.her] [npc.crotchNipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and [npc2.she] can't [npc.verb(help)] but [npc.verb(let)] out [npc2.a_moan+] as [npc2.she] frantically [npc2.verb(try)] to push [npc.name] away from [npc2.herHim].",

							" and, desperately trying to pull away from [npc.namePos] [npc.crotchBoobs+], [npc2.she] [npc2.verb(plead)] with [npc.herHim] to leave [npc2.herHim] alone."));
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterTargetedForSexAction(this).ingestFluid(
					Main.sex.getCharacterPerformingAction(),
					Main.sex.getCharacterPerformingAction().getMilk(),
					SexAreaOrifice.MOUTH,
					Main.sex.getCharacterPerformingAction().getBreastCrotchRawMilkStorageValue()/5)
				+ Main.sex.getCharacterPerformingAction().incrementBreastCrotchStoredMilk(-Main.sex.getCharacterPerformingAction().getBreastCrotchRawMilkStorageValue()/5);
		}
		
	};
}
