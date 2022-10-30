package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
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
 * @since 0.1.79
 * @version 0.2.8
 * @author Innoxia
 */
public class FingerVagina {
	
	public static final SexAction STROKE_PUSSY = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stroke pussy";
		}

		@Override
		public String getActionDescription() {
			return "Reach down between [npc2.namePos] [npc2.legs] and start stroking [npc2.her] [npc2.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.VAGINA)){

				UtilText.nodeContentSB.setLength(0);
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(trace)] [npc.her] [npc.fingers+] over [npc2.namePos] [npc2.topClothing(VAGINA)],"
										+ " gently pushing down and letting out a soft [npc.moan] as [npc.she] [npc.verb(rub)] [npc2.her] [npc2.lowClothing(VAGINA)] against [npc2.her] [npc2.pussy+].",

								"With a soft [npc.moan], [npc.name] [npc.verb(reach)] down between [npc2.namePos] [npc2.legs],"
										+ " gently pushing [npc.her] [npc.fingers] down as [npc.she] [npc.verb(rub)] [npc2.namePos] [npc2.lowClothing(VAGINA)] against [npc2.her] [npc2.pussy+].",

								"Slowly teasing [npc.her] [npc.fingers+] between [npc2.namePos] [npc2.legs], [npc.name] gently [npc.verb(press)] down,"
										+ " rubbing [npc2.her] [npc2.lowClothing(VAGINA)] against [npc2.her] [npc2.pussy+] as [npc.she] [npc.verb(let)] out a soft [npc.moan]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(trace)] [npc.her] [npc.fingers+] over [npc2.namePos] [npc2.topClothing(VAGINA)],"
										+ " eagerly pushing down and letting out [npc.a_moan+] as [npc.she] [npc.verb(rub)] [npc2.her] [npc2.lowClothing(VAGINA)] against [npc2.her] [npc2.pussy+].",

								"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down between [npc2.namePos] [npc2.legs],"
										+ " eagerly pushing [npc.her] [npc.fingers] down as [npc.she] [npc.verb(rub)] [npc2.namePos] [npc2.lowClothing(VAGINA)] against [npc2.her] [npc2.pussy+].",

								"Eagerly teasing [npc.her] [npc.fingers+] between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(start)] to press down,"
										+ " rubbing [npc2.her] [npc2.lowClothing(VAGINA)] against [npc2.her] [npc2.pussy+] as [npc.she] [npc.verb(let)] out [npc.a_moan+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] roughly [npc.verb(press)] [npc.her] [npc.fingers+] against [npc2.namePos] [npc2.topClothing(VAGINA)],"
										+ " forcefully pushing down and letting out [npc.a_moan+] as [npc.she] rapidly [npc.verb(start)] rubbing [npc2.her] [npc2.lowClothing(VAGINA)] against [npc2.her] [npc2.pussy+].",

								"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down between [npc2.namePos] [npc2.legs],"
										+ " roughly pushing [npc.her] [npc.fingers] down as [npc.she] rapidly [npc.verb(start)] rubbing [npc2.namePos] [npc2.lowClothing(VAGINA)] against [npc2.her] [npc2.pussy+].",

								"Roughly teasing [npc.her] [npc.fingers+] between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(start)] to press down,"
										+ " rapidly rubbing [npc2.her] [npc2.lowClothing(VAGINA)] against [npc2.her] [npc2.pussy+] as [npc.she] [npc.verb(let)] out [npc.a_moan+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(trace)] [npc.her] [npc.fingers+] over [npc2.namePos] [npc2.topClothing(VAGINA)],"
										+ " eagerly pushing down and letting out [npc.a_moan+] as [npc.she] [npc.verb(rub)] [npc2.her] [npc2.lowClothing(VAGINA)] against [npc2.her] [npc2.pussy+].",

								"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down between [npc2.namePos] [npc2.legs],"
										+ " eagerly pushing [npc.her] [npc.fingers] down as [npc.she] [npc.verb(rub)] [npc2.namePos] [npc2.lowClothing(VAGINA)] against [npc2.her] [npc2.pussy+].",

								"Eagerly teasing [npc.her] [npc.fingers+] between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(start)] to press down,"
										+ " rubbing [npc2.her] [npc2.lowClothing(VAGINA)] against [npc2.her] [npc2.pussy+] as [npc.she] [npc.verb(let)] out [npc.a_moan+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(trace)] [npc.her] [npc.fingers+] over [npc2.namePos] [npc2.topClothing(VAGINA)],"
										+ " pushing down and letting out [npc.a_moan+] as [npc.she] [npc.verb(rub)] [npc2.her] [npc2.lowClothing(VAGINA)] against [npc2.her] [npc2.pussy+].",

								"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down between [npc2.namePos] [npc2.legs],"
										+ " pushing [npc.her] [npc.fingers] down as [npc.she] [npc.verb(rub)] [npc2.namePos] [npc2.lowClothing(VAGINA)] against [npc2.her] [npc2.pussy+].",

								"Teasing [npc.her] [npc.fingers+] between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(press)] down,"
										+ " rubbing [npc2.her] [npc2.lowClothing(VAGINA)] against [npc2.her] [npc2.pussy+] as [npc.she] [npc.verb(let)] out [npc.a_moan+]."));
						break;
					default:
						break;
				}
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+], and with a gentle bucking of [npc2.her] [npc2.hips],"
										+ " [npc2.she] softly [npc2.verb(encourage)] [npc.name] to continue stroking [npc2.her] [npc2.pussy+].",

								" [npc2.Name] [npc2.verb(start)] gently bucking [npc2.her] [npc2.hips] in response,"
										+ " softly pressing [npc2.herself] against [npc.namePos] [npc.fingers] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to continue.",

								" [npc2.Name] gently [npc2.verb(start)] thrusting [npc2.her] [npc2.hips] back against [npc.namePos] [npc.fingers],"
										+ " softly [npc2.moaning] as [npc2.she] [npc2.verb(enjoy)] the feeling of [npc.namePos] touch on [npc2.her] [npc2.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and with an eager bucking of [npc2.her] [npc2.hips],"
										+ " [npc2.she] desperately [npc2.verb(encourage)] [npc.name] to continue stroking [npc2.her] [npc2.pussy+].",

								" [npc2.Name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.hips] in response,"
										+ " desperately pressing [npc2.herself] against [npc.namePos] [npc.fingers] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to continue.",

								" [npc2.Name] eagerly [npc2.verb(start)] thrusting [npc2.her] [npc2.hips] back against [npc.namePos] [npc.fingers],"
										+ " [npc2.moaning+] as [npc2.she] [npc2.verb(enjoy)] the feeling of [npc.namePos] touch on [npc2.her] [npc2.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and with a violent buck of [npc2.her] [npc2.hips],"
										+ " [npc2.she] roughly [npc2.verb(order)] [npc.name] to continue stroking [npc2.her] [npc2.pussy+].",

								" [npc2.Name] [npc2.verb(start)] frantically bucking [npc2.her] [npc2.hips] in response,"
										+ " roughly pressing [npc2.herself] against [npc.namePos] [npc.fingers] as [npc2.she] [npc2.verb(demand)] that [npc.she] [npc.verb(continue)].",

								" [npc2.Name] forcefully [npc2.verb(start)] thrusting [npc2.her] [npc2.hips] back against [npc.namePos] [npc.fingers],"
										+ " [npc2.moaning+] as [npc2.she] [npc2.verb(enjoy)] the feeling of roughly grinding [npc2.her] [npc2.pussy+] against [npc.name]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and with an eager bucking of [npc2.her] [npc2.hips],"
										+ " [npc2.she] desperately [npc2.verb(encourage)] [npc.name] to continue stroking [npc2.her] [npc2.pussy+].",

								" [npc2.Name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.hips] in response,"
										+ " desperately pressing [npc2.herself] against [npc.namePos] [npc.fingers] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to continue.",

								" [npc2.Name] eagerly [npc2.verb(start)] thrusting [npc2.her] [npc2.hips] back against [npc.namePos] [npc.fingers],"
										+ " [npc2.moaning+] as [npc2.she] [npc2.verb(enjoy)] the feeling of [npc.namePos] touch on [npc2.her] [npc2.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, bucking [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand],"
										+ " [npc2.she] [npc2.verb(encourage)] [npc.herHim] to continue stroking [npc2.her] [npc2.pussy+].",

								" [npc2.Name] [npc2.verb(start)] bucking [npc2.her] [npc2.hips] in response,"
										+ " pressing [npc2.herself] against [npc.namePos] [npc.fingers] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to continue.",

								" [npc2.Name] [npc2.verb(start)] thrusting [npc2.her] [npc2.hips] back against [npc.namePos] [npc.fingers],"
										+ " [npc2.moaning+] as [npc2.she] [npc2.verb(enjoy)] the feeling of [npc.namePos] touch on [npc2.her] [npc2.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+], and, pulling [npc2.her] [npc2.hips] away from [npc.namePos] touch,"
										+ " [npc2.she] [npc2.verb(plead)] with [npc.name] to stop touching [npc2.herHim].",

								" [npc2.Name] [npc2.verb(pull)] [npc2.her] [npc2.hips] away in response,"
										+ " letting out [npc2.a_sob+] as [npc2.she] [npc2.verb(plead)] for [npc.name] to stop.",

								" [npc2.Name] [npc2.verb(try)] to pull [npc2.her] [npc2.hips] away from [npc.namePos] [npc.fingers],"
										+ " [npc2.sobbing+] as [npc2.she] [npc2.verb(beg)] for [npc.name] to leave [npc2.herHim] alone."));
						break;
					default:
						break;
				}
		
				return UtilText.nodeContentSB.toString();
				
			} else {
				
				UtilText.nodeContentSB.setLength(0);
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(trace)] [npc.her] [npc.fingers+] over [npc2.her] [npc2.pussy+],"
										+ " letting out a soft [npc.moan] as [npc.she] [npc.verb(start)] gently stroking [npc2.her] [npc2.labia+].",

								"With a soft [npc.moan], [npc.name] [npc.verb(reach)] down between [npc2.namePos] [npc2.legs],"
										+ " gently pushing [npc.her] [npc.fingers] against [npc2.her] [npc2.labia+] as [npc.she] [npc.verb(start)] stroking [npc2.her] [npc2.pussy+].",

								"Slowly teasing [npc.her] [npc.fingers+] between [npc2.namePos] [npc2.legs], [npc.name] gently [npc.verb(press)] down,"
										+ " letting out a soft [npc.moan] as [npc.she] [npc.verb(start)] stroking [npc2.her] [npc2.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] eagerly [npc.verb(trace)] [npc.her] [npc.fingers+] over [npc2.her] [npc2.pussy+],"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(start)] greedily stroking [npc2.her] [npc2.labia+].",

								"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down between [npc2.namePos] [npc2.legs],"
										+ " eagerly pressing [npc.her] [npc.fingers] against [npc2.her] [npc2.labia+] as [npc.she] [npc.verb(start)] stroking [npc2.her] [npc2.pussy+].",

								"Teasing [npc.her] [npc.fingers+] between [npc2.namePos] [npc2.legs], [npc.name] eagerly [npc.verb(press)] down,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(start)] happily stroking [npc2.her] [npc2.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] roughly grinds [npc.her] [npc.fingers+] over [npc2.her] [npc2.pussy+],"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(start)] rapidly stroking [npc2.her] [npc2.labia+].",

								"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down between [npc2.namePos] [npc2.legs],"
										+ " greedily pressing [npc.her] [npc.fingers] against [npc2.her] [npc2.labia+] as [npc.she] [npc.verb(start)] roughly rubbing [npc2.her] [npc2.pussy+].",

								"Roughly pushing [npc.her] [npc.fingers+] between [npc2.namePos] [npc2.legs], [npc.name] roughly [npc.verb(press)] down,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(start)] greedily fondling [npc2.her] [npc2.pussy+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] eagerly [npc.verb(trace)] [npc.her] [npc.fingers+] over [npc2.her] [npc2.pussy+],"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(start)] greedily stroking [npc2.her] [npc2.labia+].",

								"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down between [npc2.namePos] [npc2.legs],"
										+ " eagerly pressing [npc.her] [npc.fingers] against [npc2.her] [npc2.labia+] as [npc.she] [npc.verb(start)] stroking [npc2.her] [npc2.pussy+].",

								"Teasing [npc.her] [npc.fingers+] between [npc2.namePos] [npc2.legs], [npc.name] eagerly [npc.verb(press)] down,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(start)] happily stroking [npc2.her] [npc2.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(trace)] [npc.her] [npc.fingers+] over [npc2.her] [npc2.pussy+],"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(start)] stroking [npc2.her] [npc2.labia+].",

								"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down between [npc2.namePos] [npc2.legs],"
										+ " pressing [npc.her] [npc.fingers] against [npc2.her] [npc2.labia+] as [npc.she] [npc.verb(start)] stroking [npc2.her] [npc2.pussy+].",

								"Teasing [npc.her] [npc.fingers+] between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(press)] down,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(start)] stroking [npc2.her] [npc2.pussy+]."));
						break;
					default:
						break;
				}
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+], and with a gentle bucking of [npc2.her] [npc2.hips],"
										+ " [npc2.she] softly [npc2.verb(encourage)] [npc.name] to continue stroking [npc2.her] [npc2.pussy+].",

								" [npc2.Name] [npc2.verb(start)] gently bucking [npc2.her] [npc2.hips] in response,"
										+ " softly pressing [npc2.herself] against [npc.namePos] [npc.fingers] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to continue.",

								" [npc2.Name] gently [npc2.verb(start)] thrusting [npc2.her] [npc2.hips] back against [npc.namePos] [npc.fingers],"
										+ " softly [npc2.moaning] as [npc2.she] [npc2.verb(enjoy)] the feeling of [npc.her] touch on [npc2.her] [npc2.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and with an eager bucking of [npc2.her] [npc2.hips],"
										+ " [npc2.she] desperately [npc2.verb(encourage)] [npc.name] to continue stroking [npc2.her] [npc2.pussy+].",

								" [npc2.Name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.hips] in response,"
										+ " desperately pressing [npc2.herself] against [npc.namePos] [npc.fingers] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to continue.",

								" [npc2.Name] eagerly [npc2.verb(start)] thrusting [npc2.her] [npc2.hips] back against [npc.namePos] [npc.fingers],"
										+ " [npc2.moaning+] as [npc2.she] [npc2.verb(enjoy)] the feeling of [npc.her] touch on [npc2.her] [npc2.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and with a violent buck of [npc2.her] [npc2.hips],"
										+ " [npc2.she] roughly order [npc.name] to continue stroking [npc2.her] [npc2.pussy+].",

								" [npc2.Name] [npc2.verb(start)] frantically bucking [npc2.her] [npc2.hips] in response,"
										+ " roughly pressing [npc2.herself] against [npc.namePos] [npc.fingers] as [npc2.she] [npc2.verb(demand)] that [npc.she] continue.",

								" [npc2.Name] forcefully [npc2.verb(start)] thrusting [npc2.her] [npc2.hips] back against [npc.namePos] [npc.fingers],"
										+ " [npc2.moaning+] as [npc2.she] [npc2.verb(enjoy)] the feeling of roughly grinding [npc2.her] [npc2.pussy+] against [npc.herHim]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and with an eager bucking of [npc2.her] [npc2.hips],"
										+ " [npc2.she] desperately [npc2.verb(encourage)] [npc.name] to continue stroking [npc2.her] [npc2.pussy+].",

								" [npc2.Name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.hips] in response,"
										+ " desperately pressing [npc2.herself] against [npc.namePos] [npc.fingers] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to continue.",

								" [npc2.Name] eagerly [npc2.verb(start)] thrusting [npc2.her] [npc2.hips] back against [npc.namePos] [npc.fingers],"
										+ " [npc2.moaning+] as [npc2.she] [npc2.verb(enjoy)] the feeling of [npc.her] touch on [npc2.her] [npc2.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, bucking [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand],"
										+ " [npc2.she] [npc2.verb(encourage)] [npc.herHim] to continue stroking [npc2.her] [npc2.pussy+].",

								" [npc2.Name] [npc2.verb(start)] bucking [npc2.her] [npc2.hips] in response,"
										+ " pressing [npc2.herself] against [npc.namePos] [npc.fingers] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to continue.",

								" [npc2.Name] [npc2.verb(start)] thrusting [npc2.her] [npc2.hips] back against [npc.namePos] [npc.fingers],"
										+ " [npc2.moaning+] as [npc2.she] [npc2.verb(enjoy)] the feeling of [npc.her] touch on [npc2.her] [npc2.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+], and, pulling [npc2.her] [npc2.hips] away from [npc.namePos] touch,"
										+ " [npc2.she] [npc2.verb(plead)] with [npc.herHim] to stop touching [npc2.herHim].",

								" [npc2.Name] [npc2.verb(pull)] [npc2.her] [npc2.hips] away in response,"
										+ " letting out [npc2.a_sob+] as [npc2.she] [npc2.verb(plead)] for [npc.name] to stop.",

								" [npc2.Name] [npc2.verb(try)] to pull [npc2.her] [npc2.hips] away from [npc.namePos] [npc.fingers],"
										+ " [npc2.sobbing+] as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to leave [npc2.herHim] alone."));
						break;
					default:
						break;
				}
				
				return UtilText.nodeContentSB.toString();
				
			}
		}

		@Override
		public void applyEffects() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.VAGINA)) {
				Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA);
			}
		}
	};
	
	public static final SexAction FINGERING_PROSTATE_MASSAGE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo();
		}
		
		@Override
		public String getActionTitle() {
			return "Prostate massage";
		}

		@Override
		public String getActionDescription() {
			return "Curl your [npc.fingers] up inside [npc2.namePos] [npc2.pussy+] and start stroking [npc2.her] prostate.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently pushing the palm of [npc.her] [npc.hand] in against [npc2.namePos] [npc2.labia+],"
								+ " [npc.name] [npc.verb(slide)] [npc.her] [npc.fingers] deep into [npc2.her] [npc2.pussy], before curling them up and starting to softly stroke and massage [npc2.her] prostate.",
							"With a soft [npc.moan], [npc.name] gently [npc.verb(slide)] [npc.her] [npc.fingers+] as deep as possible into [npc2.namePos] [npc2.pussy+],"
								+ " before curling them up and starting to rhythmically stroke and massage [npc2.her] prostate.",
							"Slowly pushing [npc.her] [npc.fingers+] as deep as possible into [npc2.namePos] [npc2.pussy+],"
									+ " [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] then [npc.verb(curl)] them up and [npc.verb(start)] to gently massage [npc2.her] prostate."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly slamming the palm of [npc.her] [npc.hand] in against [npc2.namePos] [npc2.labia+],"
								+ " [npc.name] [npc.verb(force)] [npc.her] [npc.fingers] deep into [npc2.her] [npc2.pussy], before curling them up and starting to forcefully stroke and massage [npc2.her] prostate.",
							"With [npc.a_moan+], [npc.name] violently [npc.verb(slam)] [npc.her] [npc.fingers+] as deep as possible into [npc2.namePos] [npc2.pussy+],"
								+ " before curling them up and starting to aggressively stroke and massage [npc2.her] prostate.",
							"Forcefully pushing [npc.her] [npc.fingers+] as deep as possible into [npc2.namePos] [npc2.pussy+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] then [npc.verb(curl)] them up and [npc.verb(start)] to roughly massage [npc2.her] prostate."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing the palm of [npc.her] [npc.hand] in against [npc2.namePos] [npc2.labia+],"
								+ " [npc.name] [npc.verb(slide)] [npc.her] [npc.fingers] deep into [npc2.her] [npc2.pussy], before curling them up and starting to stroke and massage [npc2.her] prostate.",
							"With [npc.a_moan+], [npc.name] [npc.verb(push)] [npc.her] [npc.fingers+] as deep as possible into [npc2.namePos] [npc2.pussy+],"
								+ " before curling them up and starting to stroke and massage [npc2.her] prostate.",
							"Pushing [npc.her] [npc.fingers+] as deep as possible into [npc2.namePos] [npc2.pussy+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] then [npc.verb(curl)] them up and [npc.verb(start)] to massage [npc2.her] prostate."));
					break;
				default: // Normal dom and eager sub:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pushing the palm of [npc.her] [npc.hand] in against [npc2.namePos] [npc2.labia+],"
								+ " [npc.name] [npc.verb(slide)] [npc.her] [npc.fingers] deep into [npc2.her] [npc2.pussy], before curling them up and starting to rapidly stroke and massage [npc2.her] prostate.",
							"With [npc.a_moan+], [npc.name] desperately [npc.verb(push)] [npc.her] [npc.fingers+] as deep as possible into [npc2.namePos] [npc2.pussy+],"
								+ " before curling them up and starting to eagerly stroke and massage [npc2.her] prostate.",
							"Happily pushing [npc.her] [npc.fingers+] as deep as possible into [npc2.namePos] [npc2.pussy+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] then [npc.verb(curl)] them up and [npc.verb(start)] to energetically massage [npc2.her] prostate."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Upon feeling this sudden stimulating sensation, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+], before gently pushing [npc2.her] [npc2.labia+] against [npc.namePos] [npc.hand].",
							" Immediately, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] in response, before gently bucking [npc2.her] hips against [npc.namePos] touch.",
							" Responding to this move by letting out [npc2.a_moan+], [npc2.name] gently [npc2.verb(push)] [npc2.her] [npc2.labia+] out against [npc.namePos] [npc.hand]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Upon feeling this sudden stimulating sensation, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+], before roughly forcing [npc2.her] [npc2.labia+] against [npc.namePos] [npc.hand].",
							" Immediately, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] in response, before forcefully slamming [npc2.her] [npc2.labia+] against [npc.namePos] touch.",
							" Responding to this move by letting out [npc2.a_moan+], [npc2.name] aggressively [npc2.verb(slam)] [npc2.her] [npc2.labia+] against [npc.namePos] [npc.hand]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Upon feeling this sudden stimulating sensation, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+], before pushing [npc2.her] [npc2.labia+] against [npc.namePos] [npc.hand].",
							" Immediately, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] in response, before bucking [npc2.her] hips against [npc.namePos] touch.",
							" Responding to this move by letting out [npc2.a_moan+], [npc2.name] [npc2.verb(push)] [npc2.her] [npc2.labia+] against [npc.namePos] [npc.hand]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Upon feeling this unwanted stimulating sensation, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+], before desperately trying to pull [npc2.her] [npc2.labia+] away from [npc.namePos] [npc.hand].",
							" Immediately, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] in response, before desperately attempting to pull [npc2.her] hips away from [npc.namePos] touch.",
							" Responding to this move by letting out [npc2.a_moan+], [npc2.name] desperately [npc2.verb(try)] to pull [npc2.her] [npc2.labia+] away from [npc.namePos] [npc.name]."));
					break;
				default: // Normal dom and eager sub:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Upon feeling this sudden stimulating sensation, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+], before pushing [npc2.her] [npc2.labia+] against [npc.namePos] [npc.hand].",
							" Immediately, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] in response, before desperately bucking [npc2.her] hips against [npc.namePos] touch.",
							" Responding to this move by letting out [npc2.a_moan+], [npc2.name] desperately [npc2.verb(push)] [npc2.her] [npc2.labia+] against [npc.namePos] [npc.hand]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Finger [npc2.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Sink your [npc.fingers] into [npc2.namePos] [npc2.pussy+] and start fingering [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly teasing [npc.her] [npc.fingers+] over [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] out a little [npc.moan] before slowly sinking [npc.her] digits into [npc2.her] [npc2.pussy+].",

							"[npc.Name] [npc.verb(press)] [npc.her] [npc.fingers+] down between [npc2.namePos] [npc2.legs+],"
									+ " and with a slow, steady pressure, [npc.she] gently [npc.verb(sink)] [npc.her] digits into [npc2.her] [npc2.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing [npc.her] [npc.fingers+] over [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] out [npc.a_moan+] before greedily sinking [npc.her] digits into [npc2.her] [npc2.pussy+].",

							"[npc.Name] eagerly [npc.verb(press)] [npc.her] [npc.fingers+] down between [npc2.namePos] [npc2.legs+],"
									+ " and with a determined thrust, [npc.she] greedily [npc.verb(sink)] [npc.her] digits into [npc2.her] [npc2.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding [npc.her] [npc.fingers+] over [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] out [npc.a_moan+] before violently slamming [npc.her] digits deep into [npc2.her] [npc2.pussy+].",

							"[npc.Name] roughly grinds [npc.her] [npc.fingers+] down between [npc2.namePos] [npc2.legs+],"
									+ " and with a forceful thrust, [npc.she] greedily slams [npc.her] digits deep into [npc2.her] [npc2.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing [npc.her] [npc.fingers+] over [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] out [npc.a_moan+] before greedily sinking [npc.her] digits into [npc2.her] [npc2.pussy+].",

							"[npc.Name] eagerly [npc.verb(press)] [npc.her] [npc.fingers+] down between [npc2.namePos] [npc2.legs+],"
									+ " and with a determined thrust, [npc.she] greedily [npc.verb(sink)] [npc.her] digits into [npc2.her] [npc2.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing [npc.her] [npc.fingers+] over [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] out [npc.a_moan+] before sinking [npc.her] digits into [npc2.her] [npc2.pussy+].",

							"[npc.Name] [npc.verb(press)] [npc.her] [npc.fingers+] down between [npc2.namePos] [npc2.legs+], and with a little push, [npc.she] [npc.verb(sink)] [npc.her] digits into [npc2.her] [npc2.pussy+]."));
					break;
				default:
					break;
			}
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc.namePos] [npc.fingers] enter [npc2.herHim],"
									+ " gently bucking [npc2.her] [npc2.hips] against [npc.her] [npc.hand] as [npc2.she] [npc2.verb(help)] to sink [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.pussy+].",

							" With a soft [npc2.moan], [npc2.name] [npc2.verb(start)] gently bucking [npc2.her] [npc2.hips] into [npc.namePos] [npc.hand],"
									+ " encouraging [npc.herHim] to sink [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.namePos] [npc.fingers] enter [npc2.herHim],"
									+ " eagerly bucking [npc2.her] [npc2.hips] against [npc.her] [npc.hand] as [npc2.she] enthusiastically [npc2.verb(help)] to sink [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.pussy+].",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.hips] into [npc.namePos] [npc.hand],"
									+ " desperately encouraging [npc.herHim] to sink [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.namePos] [npc.fingers] enter [npc2.herHim],"
									+ " violently bucking [npc2.her] [npc2.hips] against [npc.her] [npc.hand] as [npc2.she] roughly [npc2.verb(force)] [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.pussy+].",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] violently bucking [npc2.her] [npc2.hips] into [npc.namePos] [npc.hand],"
									+ " roughly forcing [npc.herHim] to sink [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.namePos] [npc.fingers] enter [npc2.herHim],"
									+ " eagerly bucking [npc2.her] [npc2.hips] against [npc.her] [npc.hand] as [npc2.she] enthusiastically [npc2.verb(help)] to sink [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.pussy+].",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.hips] into [npc.namePos] [npc.hand],"
									+ " desperately encouraging [npc.herHim] to sink [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.namePos] [npc.fingers] enter [npc2.herHim],"
									+ " bucking [npc2.her] [npc2.hips] against [npc.her] [npc.hand] as [npc2.she] [npc2.verb(help)] to sink [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.pussy+].",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] bucking [npc2.her] [npc2.hips] into [npc.namePos] [npc.hand],"
									+ " encouraging [npc.herHim] to sink [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.fingers] enter [npc2.herHim],"
									+ " and [npc2.verb(try)], in vain, to pull [npc2.her] [npc2.hips] back from the unwanted penetration, struggling and [npc2.sobbing] all the while.",

							" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)], in vain, to pull away from the unwanted penetration,"
									+ " [npc2.sobbing] and struggling against [npc.name] as [npc.her] unwelcome [npc.fingers] [npc2.verb(push)] deep into [npc2.her] [npc2.pussy+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	private static String getTargetedCharacterResponse(SexAction action) {
		switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
			case SUB_EAGER:
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] eagerly [npc2.verb(thrust)] [npc2.her] [npc2.hips] out in response,"
								+ " letting out a delighted [npc2.moan] as [npc2.she] [npc2.verb(start)] enthusiastically imploring [npc.name] to continue fingering [npc2.herHim].",
	
						" A delighted [npc2.moan] bursts out from between [npc2.namePos] [npc2.lips+],"
								+ " and, eagerly thrusting [npc2.her] [npc2.hips] out against [npc.her] touch, [npc2.she] [npc2.verb(beg)] for [npc.name] to continue fingering [npc2.herHim].",
	
						" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] down against [npc.namePos] [npc.hand],"
								+ " eagerly begging for [npc.herHim] to continue as [npc2.her] movements drive [npc.her] [npc.fingers] ever deeper into [npc2.her] [npc2.pussy+]."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" Failing to recoil [npc2.her] [npc2.pussy] away from [npc.namePos] unwanted touch,"
								+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle away from [npc.namePos] intruding [npc.fingers].",
	
						" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
								+ " squirming and protesting as [npc.she] [npc.verb(continue)] to finger [npc2.namePos] [npc2.pussy+].",
	
						" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to recoil away from [npc.namePos] touch,"
								+ " struggling against [npc.herHim] as [npc.her] [npc.fingers+] [npc.verb(continue)] sliding deep into [npc2.namePos] [npc2.pussy+]."));
				break;
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					" [npc2.Name] slowly [npc2.verb(thrust)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.hand] in response,"
							+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(start)] gently imploring [npc.name] to continue fingering [npc2.herHim].",

					" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+],"
							+ " and, gently pushing [npc2.her] [npc2.hips] out against [npc.her] [npc.hand], [npc2.she] [npc2.verb(beg)] for [npc.name] to continue fingering [npc2.herHim].",

					" [npc2.Moaning] in delight, [npc2.name] slowly [npc2.verb(grind)] down against [npc.namePos] [npc.hand],"
							+ " softly [npc2.moaning] for [npc.herHim] to continue as [npc2.her] movements cause [npc.her] [npc.fingers] to slide ever deeper into [npc2.her] [npc2.pussy+]."));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.hand] in response,"
								+ " letting out [npc2.a_moan+] as [npc2.name] roughly [npc2.verb(order)] [npc.name] to continue fingering [npc2.herHim].",

						" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
								+ " and, roughly grinding [npc2.her] [npc2.hips] against [npc.her] [npc.hand], [npc2.name] dominantly [npc.verb(command)] [npc.name] to continue fingering [npc2.herHim].",

						" [npc2.Name] roughly [npc2.verb(grind)] down against [npc.namePos] [npc.hand],"
								+ " ordering [npc.herHim] to continue as [npc2.her] forceful movements cause [npc.her] [npc.fingers] to sink ever deeper into [npc2.her] [npc2.pussy+]."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.hand] in response,"
								+ " letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(beg)] for [npc.name] to continue fingering [npc2.herHim].",
	
						" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
								+ " and, thrusting [npc2.her] [npc2.hips] out against [npc.her] touch, [npc2.name] [npc2.verb(beg)] for [npc.name] to continue fingering [npc2.herHim].",
	
						" [npc2.Moaning+], [npc2.name] [npc2.verb(grind)] down against [npc.namePos] [npc.hand],"
								+ " begging for [npc.herHim] to continue as [npc2.her] movements drive [npc.her] [npc.fingers] ever deeper into [npc2.her] [npc2.pussy+]."));
				break;
		}
		return "";
	}
	
	public static final SexAction FINGERING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle fingering";
		}

		@Override
		public String getActionDescription() {
			return "Gently finger [npc2.namePos] [npc2.pussy+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sinking [npc.her] [npc.fingers+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(curl)] [npc.her] digits up, softly stroking [npc2.her] vaginal walls as [npc.she] [npc.verb(start)] slowly fingering [npc2.herHim].",

					"Slowly pushing [npc.her] [npc.hand] into [npc2.namePos] groin,"
							+ " [npc.name] gently slides [npc.her] [npc.fingers+] deep into [npc2.namePos] [npc2.pussy+], letting out a little [npc.moan] as [npc.she] [npc.verb(start)] softly fingering [npc2.herHim].",

					"Pressing [npc.her] [npc.hand] down between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] out a little [npc.moan] before gently sliding [npc.her] [npc.fingers+] deep into [npc2.her] [npc2.pussy+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Fingering";
		}

		@Override
		public String getActionDescription() {
			return "Continue fingering [npc2.namePos] [npc2.pussy+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly sinking [npc.her] [npc.fingers+] deep into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(curl)] [npc.her] digits up,"
							+ " stroking [npc2.her] vaginal walls as [npc.she] [npc.verb(start)] passionately fingering [npc2.herHim].",

					"Firmly pushing [npc.her] [npc.hand] into [npc2.namePos] groin, [npc.name] eagerly slides [npc.her] [npc.fingers+] deep into [npc2.namePos] [npc2.pussy+],"
							+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(start)] rapidly fingering [npc2.herHim].",

					"Eagerly pressing [npc.her] [npc.hand] down between [npc2.namePos] [npc2.legs],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] before enthusiastically sliding [npc.her] [npc.fingers+] deep into [npc2.her] [npc2.pussy+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Rough fingering";
		}

		@Override
		public String getActionDescription() {
			return "Roughly finger [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Greedily thrusting [npc.her] [npc.fingers+] deep into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(curl)] [npc.her] digits up,"
							+ " roughly stroking [npc2.namePos] vaginal walls as [npc.she] [npc.verb(start)] ruthlessly fingering [npc2.herHim].",

					"Roughly slamming [npc.her] [npc.hand] into [npc2.namePos] groin, [npc.name] greedily [npc.verb(pump)] [npc.her] [npc.fingers+] deep into [npc2.namePos] [npc2.pussy+],"
							+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(start)] violently fingering [npc2.herHim].",

					"Forcefully pressing [npc.her] [npc.hand] down between [npc2.namePos] [npc2.legs],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] before roughly slamming [npc.her] [npc.fingers+] deep into [npc2.her] [npc2.pussy+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FINGERING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Fingering";
		}

		@Override
		public String getActionDescription() {
			return "Continue fingering [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking [npc.her] [npc.fingers+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(curl)] [npc.her] digits up, stroking [npc2.her] vaginal walls as [npc.she] [npc.verb(focus)] [npc.her] efforts on fingering [npc2.herHim].",

					"Pushing [npc.her] [npc.hand] into [npc2.namePos] groin, [npc.name] slides [npc.her] [npc.fingers+] deep into [npc2.her] [npc2.pussy+],"
							+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(start)] focusing [npc.her] efforts on fingering [npc2.herHim].",

					"Pressing [npc.her] [npc.hand] down between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] out [npc.a_moan+] before sliding [npc.her] [npc.fingers+] deep into [npc2.her] [npc2.pussy+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eager fingering";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly finger [npc2.namePos] [npc2.pussy+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly sinking [npc.her] [npc.fingers+] deep into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(curl)] [npc.her] digits up,"
							+ " stroking [npc2.namePos] vaginal walls as [npc.she] [npc.verb(start)] passionately fingering [npc2.herHim].",

					"Firmly pushing [npc.her] [npc.hand] into [npc2.namePos] groin, [npc.name] eagerly slides [npc.her] [npc.fingers+] deep into [npc2.namePos] [npc2.pussy+],"
							+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(start)] rapidly fingering [npc2.herHim].",
					
					"Eagerly pressing [npc.her] [npc.hand] down between [npc2.namePos] [npc2.legs],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] before enthusiastically sliding [npc.her] [npc.fingers+] deep into [npc2.her] [npc2.pussy+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};

	public static final SexAction FINGERING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist fingering";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.fingers] out of [npc2.namePos] [npc2.vagina+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears starting to well up in [npc.namePos] [npc.eyes], and, unable to keep it in any longer,"
									+ " [npc.a_sob+] bursts out from [npc.her] mouth as [npc.she] weakly [npc.verb(try)] to pull [npc.her] [npc.fingers] out of [npc2.namePos] [npc2.vagina+].",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.fingers+] out of [npc2.namePos] [npc2.vagina+].",

							"Trying desperately to pull [npc.her] [npc.fingers+] out of [npc2.namePos] [npc2.vagina+],"
									+ " [npc.name] [npc.sob] in distress as [npc2.namePos] [npc2.verb(hold)] [npc.her] [npc.hand] in position,"
									+ " before gently bucking [npc2.her] [npc2.hips+] and forcing [npc.her] [npc.fingers] deep into [npc2.her] [npc2.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears starting to well up in [npc.namePos] [npc.eyes], and, unable to keep it in any longer,"
									+ " [npc.a_sob+] bursts out from [npc.her] mouth as [npc.she] weakly [npc.verb(try)] to pull [npc.her] [npc.fingers] out of [npc2.namePos] [npc2.vagina+].",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.fingers+] out of [npc2.namePos] [npc2.vagina+].",

							"Trying desperately to pull [npc.her] [npc.fingers+] out of [npc2.namePos] [npc2.vagina+],"
									+ " [npc.name] [npc.sob] in distress as [npc2.namePos] [npc2.verb(hold)] [npc.her] [npc.hand] in position,"
									+ " before eagerly bucking [npc2.her] [npc2.hips+] and forcing [npc.her] [npc.fingers] deep into [npc2.her] [npc2.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears starting to well up in [npc.namePos] [npc.eyes], and, unable to keep it in any longer,"
									+ " [npc.a_sob+] bursts out from [npc.her] mouth as [npc.she] weakly [npc.verb(try)] to pull [npc.her] [npc.fingers] out of [npc2.namePos] [npc2.vagina+].",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.fingers+] out of [npc2.namePos] [npc2.vagina+].",

							"Trying desperately to pull [npc.her] [npc.fingers+] out of [npc2.namePos] [npc2.vagina+],"
									+ " [npc.name] [npc.sob] in distress as [npc2.namePos] [npc2.verb(hold)] [npc.her] [npc.hand] in position,"
									+ " before roughly bucking [npc2.her] [npc2.hips+] and forcing [npc.her] [npc.fingers] deep into [npc2.her] [npc2.pussy+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FINGERING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop fingering";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.fingers] out of [npc2.namePos] [npc2.pussy+] and stop fingering [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.fingers] out of [npc2.namePos] [npc2.pussy+],"
									+ " [npc.name] [npc.verb(give)] [npc2.her] [npc2.clit+] a cruel squeeze before taking [npc.her] [npc.hand] away from [npc2.her] groin.",

							"Thrusting deep inside of [npc2.name] one last time, [npc.name] then yanks [npc.her] [npc.fingers] back out of [npc2.namePos] [npc2.pussy+], putting an end to [npc.her] rough fingering."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.fingers] out of [npc2.namePos] [npc2.pussy+],"
									+ " [npc.name] [npc.verb(give)] [npc2.her] [npc2.clit+] a little squeeze before taking [npc.her] [npc.hand] away from [npc2.her] groin.",

							"Pushing deep inside of [npc2.name] one last time, [npc.name] then [npc.verb(slide)] [npc.her] [npc.fingers] back out of [npc2.namePos] [npc2.pussy+], putting an end to [npc.her] fingering."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(gasp)] as [npc.name] withdraws from [npc2.her] [npc2.pussy], before letting out [npc2.a_sob+] as [npc2.she] [npc2.verb(continue)] to struggle against [npc.herHim].",

							" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to struggle against [npc.name] as [npc.she] withdraws from [npc2.her] [npc2.pussy+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] [npc.her] [npc.fingers+] out of [npc2.her] [npc2.pussy+].",

							" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desire for more of [npc.namePos] attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	public static final SexAction FINGERED_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Get fingered";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to start fingering your [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking a gentle, but firm, grip on [npc2.namePos] [npc2.hand], [npc.name] slowly [npc.verb(guide)] [npc2.her] [npc2.fingers] over [npc.her] [npc.labia+],"
									+ " letting out a little [npc.moan] before pushing [npc2.her] digits into [npc.her] [npc.pussy+].",

							"Taking hold of [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.her] [npc2.fingers] down between [npc.her] [npc.legs+], and with a slow, steady pressure,"
									+ " [npc.name] gently [npc.verb(push)] [npc2.her] digits into [npc.her] [npc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a firm grip on [npc2.her] [npc2.hand], [npc.name] eagerly [npc.verb(guide)] [npc2.namePos] [npc2.fingers] over [npc.her] [npc.labia+],"
									+ " letting out [npc.a_moan+] before greedily pushing [npc2.her] digits into [npc.her] [npc.pussy+].",

							"Taking hold of [npc2.namePos] [npc2.hand], [npc.name] eagerly [npc.verb(guide)] [npc2.her] [npc2.fingers] down between [npc.her] [npc.legs+],"
									+ " and with a determined pressure, [npc.name] greedily [npc.verb(push)] [npc2.her] digits into [npc.her] [npc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a vice-like grip on [npc2.her] [npc2.hand], [npc.name] [npc.verb(grind)] [npc2.namePos] [npc2.fingers] over [npc.her] [npc.labia+],"
									+ " letting out [npc.a_moan+] before roughly forcing [npc2.her] digits into [npc.her] [npc.pussy+].",

							"Grabbing [npc2.namePos] [npc2.hand], [npc.name] forcefully [npc.verb(push)] [npc2.her] [npc2.fingers] down between [npc.her] [npc.legs+],"
									+ " and with a dominant, jerking motion, [npc.name] roughly [npc.verb(stuff)] [npc2.her] digits into [npc.her] [npc.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a firm grip on [npc2.her] [npc2.hand], [npc.name] eagerly [npc.verb(guide)] [npc2.namePos] [npc2.fingers] over [npc.her] [npc.labia+],"
									+ " letting out [npc.a_moan+] before greedily pushing [npc2.her] digits into [npc.her] [npc.pussy+].",

							"Taking hold of [npc2.namePos] [npc2.hand], [npc.name] eagerly [npc.verb(guide)] [npc2.her] [npc2.fingers] down between [npc.her] [npc.legs+],"
									+ " and with a determined pressure, [npc.name] greedily [npc.verb(push)] [npc2.her] digits into [npc.her] [npc.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a firm grip on [npc2.her] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.namePos] [npc2.fingers] over [npc.her] [npc.labia+],"
									+ " letting out [npc.a_moan+] before pushing [npc2.her] digits into [npc.her] [npc.pussy+].",

							"Taking hold of [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.her] [npc2.fingers] down between [npc.her] [npc.legs+],"
									+ " and with a determined pressure, [npc.name] [npc.verb(push)] [npc2.her] digits into [npc.her] [npc.pussy+]."));
					break;
				default:
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.she] [npc2.verb(enter)] [npc.name], curling [npc2.her] [npc2.fingers] up before gently starting to finger [npc.her] [npc.pussy+].",

							" With a soft [npc2.moan], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] up inside of [npc.name],"
									+ " gently stroking [npc.her] vaginal walls as [npc2.she] [npc2.verb(set)] about fingering [npc.her] [npc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.name], curling [npc2.her] [npc2.fingers] up before eagerly starting to finger [npc.her] [npc.pussy+].",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] up inside of [npc.name],"
									+ " eagerly stroking [npc.her] vaginal walls as [npc2.she] [npc2.verb(set)] about fingering [npc.her] [npc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.name], and, seeking to remind [npc.herHim] who's in charge,"
									+ " [npc2.she] roughly [npc2.verb(curl)] [npc2.her] [npc2.fingers] up before starting to ruthlessly finger-fuck [npc.her] [npc.pussy+].",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] up inside of [npc.name],"
									+ " seeking to remind [npc.herHim] who's in charge as [npc2.she] [npc2.verb(start)] roughly fingering [npc.her] [npc.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.name], curling [npc2.her] [npc2.fingers] up before eagerly starting to finger [npc.her] [npc.pussy+].",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] up inside of [npc.name],"
									+ " eagerly stroking [npc.her] vaginal walls as [npc2.she] [npc2.verb(set)] about fingering [npc.her] [npc.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.name], curling [npc2.her] [npc2.fingers] up before starting to finger [npc.her] [npc.pussy+].",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] up inside of [npc.name],"
									+ " stroking [npc.her] vaginal walls as [npc2.she] [npc2.verb(set)] about fingering [npc.her] [npc.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(force)] [npc2.her] [npc2.fingers] inside of [npc.herHim],"
									+ " struggling against [npc.namePos] firm grip on [npc2.her] [npc2.hand] as [npc2.she] [npc2.verb(try)] to pull [npc2.herself] free.",

							" With [npc2.a_sob+], [npc2.name] [npc2.verb(start)] struggling against [npc.namePos] tight grip on [npc2.her] [npc2.hand],"
									+ " pleading for [npc.herHim] to stop as [npc.she] [npc.verb(force)] [npc2.her] [npc2.fingers] deep into [npc.her] [npc.pussy+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERED_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gently fingered";
		}

		@Override
		public String getActionDescription() {
			return "Gently enjoy [npc2.namePos] [npc2.fingers+] in your [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently pushing [npc.her] [npc.hips] out against [npc2.namePos] [npc2.hand],"
							+ " [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.pussy+].",

					"With a soft [npc.moan], [npc.name] gently [npc.verb(start)] gyrating [npc.her] [npc.hips] against [npc2.namePos] [npc2.hand],"
							+ " forcing [npc2.her] [npc2.fingers+] ever deeper into [npc.her] [npc.pussy+].",

					"Slowly thrusting [npc.her] [npc.hips] against [npc2.namePos] [npc2.hand],"
							+ " a soft [npc.moan] drifts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.pussy+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERED_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Fingered";
		}

		@Override
		public String getActionDescription() {
			return "Enjoy [npc2.namePos] [npc2.fingers+] in your [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc.her] [npc.hips] out against [npc2.namePos] [npc2.hand],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] energetically [npc.verb(help)] to sink [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.pussy+].",

					"With [npc.a_moan+], [npc.name] energetically [npc.verb(start)] gyrating [npc.her] [npc.hips] against [npc2.namePos] [npc2.hand],"
							+ " forcing [npc2.her] [npc2.fingers+] ever deeper into [npc.her] [npc.pussy+].",

					"Enthusiastically thrusting [npc.her] [npc.hips] against [npc2.namePos] [npc2.hand],"
							+ " [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERED_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Roughly fingered";
		}

		@Override
		public String getActionDescription() {
			return "Roughly [npc.verb(force)] [npc2.namePos] [npc2.fingers+] deep inside your [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Violently slamming [npc.her] [npc.hips] out against [npc2.namePos] [npc2.hand],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] roughly [npc.verb(force)] [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.pussy+].",

					"With [npc.a_moan+], [npc.name] aggressively [npc.verb(start)] gyrating [npc.her] [npc.hips] against [npc2.namePos] [npc2.hand],"
							+ " forcing [npc2.her] [npc2.fingers+] ever deeper into [npc.her] [npc.pussy+].",

					"Roughly thrusting [npc.her] [npc.hips] against [npc2.namePos] [npc2.hand],"
							+ " [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] forceful movements drive [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERED_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Fingered";
		}

		@Override
		public String getActionDescription() {
			return "Enjoy [npc2.namePos] [npc2.fingers+] in your [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc.her] [npc.hips] out against [npc2.namePos] [npc2.hand],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] energetically [npc.verb(help)] to sink [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.pussy+].",

					"With [npc.a_moan+], [npc.name] energetically [npc.verb(start)] gyrating [npc.her] [npc.hips] against [npc2.namePos] [npc2.hand],"
							+ " forcing [npc2.her] [npc2.fingers+] ever deeper into [npc.her] [npc.pussy+].",

					"Enthusiastically thrusting [npc.her] [npc.hips] against [npc2.namePos] [npc2.hand],"
							+ " [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERED_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eagerly fingered";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly buck your hips against [npc2.namePos] [npc2.hand] as [npc2.she] fingers your [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc.her] [npc.hips] out against [npc2.namePos] [npc2.hand],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] energetically [npc.verb(help)] to sink [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.pussy+].",

					"With [npc.a_moan+], [npc.name] energetically [npc.verb(start)] gyrating [npc.her] [npc.hips] against [npc2.namePos] [npc2.hand],"
							+ " forcing [npc2.her] [npc2.fingers+] ever deeper into [npc.her] [npc.pussy+].",

					"Enthusiastically thrusting [npc.her] [npc.hips] against [npc2.namePos] [npc2.hand],"
							+ " [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist fingering";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull [npc2.namePos] [npc2.fingers] out of your [npc.pussy+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears starting to well up in [npc.her] [npc.eyes],"
									+ " and, unable to keep it in any longer, [npc.a_sob+] bursts out from [npc.namePos] mouth as [npc.she] weakly [npc.verb(try)] to pull [npc2.namePos] [npc2.fingers] out of [npc.her] [npc.pussy+].",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.hips] back from [npc2.namePos] unwanted touch,"
									+ " struggling in desperation as [npc2.her] [npc2.fingers+] [npc2.verb(continue)] gently sliding in and out of [npc.her] [npc.pussy+].",

							"Trying desperately to pull [npc.her] [npc.hips] away from [npc2.namePos] [npc2.hand],"
									+ " [npc.name] [npc.sob] in distress as [npc2.namePos] [npc2.fingers+] [npc2.verb(continue)] gently sliding deep into [npc.her] [npc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears starting to well up in [npc.her] [npc.eyes],"
									+ " and, unable to keep it in any longer, [npc.a_sob+] bursts out from [npc.namePos] mouth as [npc.she] weakly [npc.verb(try)] to pull [npc2.namePos] greedy [npc2.fingers] out of [npc.her] [npc.pussy+].",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.hips] back from [npc2.namePos] unwanted touch,"
									+ " struggling in desperation as [npc2.her] [npc2.fingers+] [npc2.verb(continue)] eagerly sliding in and out of [npc.her] [npc.pussy+].",

							"Trying desperately to pull [npc.her] [npc.hips] away from [npc2.namePos] [npc2.hand],"
									+ " [npc.name] [npc.sob] in distress as [npc2.namePos] [npc2.fingers+] [npc2.verb(continue)] eagerly pumping deep into [npc.her] [npc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears starting to well up in [npc.her] [npc.eyes],"
									+ " and, unable to keep it in any longer, [npc.a_sob+] bursts out from [npc.namePos] mouth as [npc.she] weakly [npc.verb(try)] to pull [npc2.namePos] rough [npc2.fingers] out of [npc.her] [npc.pussy+].",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.hips] back from [npc2.namePos] unwanted touch,"
									+ " struggling in desperation as [npc2.her] [npc2.fingers+] [npc2.verb(continue)] roughly slamming in and out of [npc.her] [npc.pussy+].",

							"Trying desperately to pull [npc.her] [npc.hips] away from [npc2.namePos] [npc2.hand],"
									+ " [npc.name] [npc.sob] in distress as [npc2.namePos] [npc2.fingers+] [npc2.verb(continue)] roughly slamming deep into [npc.her] [npc.pussy+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop getting fingered";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to pull [npc2.her] [npc2.fingers] out of your [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc2.namePos] [npc2.fingers] out of [npc.her] [npc.pussy+], [npc.name] [npc.verb(growl)] as [npc.she] [npc.verb(command)] [npc2.herHim] to stop fingering [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] into [npc2.name], inhaling [npc2.her] [npc2.scent+] before yanking [npc2.her] [npc2.fingers] out of [npc.her] [npc.pussy+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc2.namePos] [npc2.fingers] out of [npc.her] [npc.pussy+], [npc.name] [npc.verb(let)] out [npc.a_moan+] before telling [npc2.herHim] to stop fingering [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] into [npc2.name], inhaling [npc2.her] [npc2.scent+] before sliding [npc2.her] [npc2.fingers] out of [npc.her] [npc.pussy+]."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a relieved sigh, which soon turns into [npc2.a_sob+] as [npc2.she] [npc2.verb(realise)] that [npc.nameIsFull]n't finished with [npc2.herHim] yet.",

							" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to protest and struggle against [npc.name] as [npc.she] [npc.verb(hold)] [npc2.herHim] firmly in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(stop)] [npc2.herHim] from stimulating [npc.her] [npc.pussy+].",

							" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desire to give [npc.namePos] [npc.pussy+] more of [npc2.her] attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
