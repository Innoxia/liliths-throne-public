package com.lilithsthrone.game.sex.sexActions.baseActionsPlayer;

import java.util.List;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.CoverableArea;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.79
 * @version 0.1.79
 * @author Innoxia
 */
public class PlayerFingerVagina {

	/*
	 * Stroke (through clothing variant)
	 * Clit tease
	 * 
	 * Player Penetrate
	 * Partner force penetrate
	 * 
	 * Fingering:
	 * 		Dom gentle, normal, rough
	 * 		Sub normal, eager
	 * 
	 * Partner react:
	 * 		(Gentle fingered, Fingered, Grind pussy)
	 * 		Passive
	 * 		Sub resist
	 * 
	 * Player stop penetrate
	 * Partner stop penetrate
	 * 
	 */
	
	public static final SexAction PLAYER_STROKE_PUSSY = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Stroke pussy";
		}

		@Override
		public String getActionDescription() {
			return "Reach down between [npc.name]'s [npc.legs] and start stroking [npc.her] [npc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPacePlayer()!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			if(!Sex.getPartner().isCoverableAreaExposed(CoverableArea.VAGINA)){

				UtilText.nodeContentSB.setLength(0);
				
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc.name]'s [npc.legs], you trace your [pc.fingers+] over [npc.her] [npc.topClothing(pussy)],"
										+ " gently pushing down and letting out a soft [pc.moan] as you rub [npc.her] [npc.lowClothing(pussy)] against [npc.her] [npc.pussy+].",
								"With a soft [pc.moan], you reach down between [npc.name]'s [npc.legs], gently pushing your [pc.fingers] down as you rub [npc.her] [npc.lowClothing(pussy)] against [npc.her] [npc.pussy+].",
								"Slowly teasing your [pc.fingers+] between [npc.name]'s [npc.legs], you gently press down, rubbing [npc.her] [npc.lowClothing(pussy)] against [npc.her] [npc.pussy+] as you let out a soft [pc.moan]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc.name]'s [npc.legs], you trace your [pc.fingers+] over [npc.her] [npc.topClothing(pussy)],"
										+ " eagerly pushing down and letting out [pc.a_moan+] as you rub [npc.her] [npc.lowClothing(pussy)] down against [npc.her] [npc.pussy+].",
								"With [pc.a_moan+], you reach down between [npc.name]'s [npc.legs], eagerly pushing your [pc.fingers] down as you rub [npc.her] [npc.lowClothing(pussy)] against [npc.her] [npc.pussy+].",
								"Eagerly teasing your [pc.fingers+] between [npc.name]'s [npc.legs], you greedily press down, rubbing [npc.her] [npc.lowClothing(pussy)] against [npc.her] [npc.pussy+] as you let out [pc.a_moan+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc.name]'s [npc.legs], you roughly press your [pc.fingers+] against [npc.her] [npc.topClothing(pussy)],"
										+ " forcefully pushing down and letting out [pc.a_moan+] as you rapidly start rubbing [npc.her] [npc.lowClothing(pussy)] down against [npc.her] [npc.pussy+].",
								"With [pc.a_moan+], you greedily reach down between [npc.name]'s [npc.legs], roughly pushing your [pc.fingers] down as you rapidly start rubbing [npc.her] [npc.lowClothing(pussy)] against [npc.her] [npc.pussy+].",
								"Roughly pushing your [pc.fingers+] between [npc.name]'s [npc.legs], you press down, rapidly rubbing [npc.her] [npc.lowClothing(pussy)] against [npc.her] [npc.pussy+] as you let out [pc.a_moan+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc.name]'s [npc.legs], you trace your [pc.fingers+] over [npc.her] [npc.topClothing(pussy)],"
										+ " eagerly pushing down and letting out [pc.a_moan+] as you rub [npc.her] [npc.lowClothing(pussy)] down against [npc.her] [npc.pussy+].",
								"With [pc.a_moan+], you reach down between [npc.name]'s [npc.legs], eagerly pushing your [pc.fingers] down as you rub [npc.her] [npc.lowClothing(pussy)] against [npc.her] [npc.pussy+].",
								"Eagerly teasing your [pc.fingers+] between [npc.name]'s [npc.legs], you greedily press down, rubbing [npc.her] [npc.lowClothing(pussy)] against [npc.her] [npc.pussy+] as you let out [pc.a_moan+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc.name]'s [npc.legs], you trace your [pc.fingers+] over [npc.her] [npc.topClothing(pussy)],"
										+ " pushing down and letting out [pc.a_moan+] as you rub [npc.her] [npc.lowClothing(pussy)] down against [npc.her] [npc.pussy+].",
								"With [pc.a_moan+], you reach down between [npc.name]'s [npc.legs], pushing your [pc.fingers] down as you rub [npc.her] [npc.lowClothing(pussy)] against [npc.her] [npc.pussy+].",
								"Teasing your [pc.fingers+] between [npc.name]'s [npc.legs], you press down, rubbing [npc.her] [npc.lowClothing(pussy)] against [npc.her] [npc.pussy+] as you let out [pc.a_moan+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" A soft [npc.moan] drifts out from between [npc.name]'s [npc.lips+], and with a gentle bucking of [npc.her] [npc.hips], [npc.she] softly encourages you to continue stroking [npc.her] delicate folds.",
								" [npc.Name] starts gently bucking [npc.her] [npc.hips] in response, softly pressing [npc.her] [npc.topClothing(pussy)] against your [pc.fingers] as [npc.she] pleads for you to continue.",
								" [npc.Name] gently starts thrusting [npc.her] [npc.hips] back against your [pc.fingers], softly [npc.moaning] as [npc.she] presses [npc.her] [npc.topClothing(pussy)] back against your touch."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and with an eager bucking of [npc.her] [npc.hips], [npc.she] desperately encourages you to continue stimulating [npc.her] delicate folds.",
								" [npc.Name] starts eagerly bucking [npc.her] [npc.hips] in response, desperately pressing [npc.her] [npc.topClothing(pussy)] against your [pc.fingers] as [npc.she] pleads for you to continue.",
								" [npc.Name] eagerly starts thrusting [npc.her] [npc.hips] back against your [pc.fingers], [npc.moaning+] as [npc.she] presses [npc.her] [npc.topClothing(pussy)] back against your touch."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and with a violent buck of [npc.her] [npc.hips], [npc.she] roughly orders you to continue stimulating [npc.her] delicate folds.",
								" [npc.Name] starts frantically bucking [npc.her] [npc.hips] in response, roughly pressing [npc.her] [npc.topClothing(pussy)] against your [pc.fingers] as [npc.she] demands that you continue.",
								" [npc.Name] forcefully starts thrusting [npc.her] [npc.hips] back against your [pc.fingers], [npc.moaning+] as [npc.she] roughly grinds [npc.her] [npc.topClothing(pussy)] back against your touch."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and with an eager bucking of [npc.her] [npc.hips], [npc.she] desperately encourages you to continue stimulating [npc.her] delicate folds.",
								" [npc.Name] starts eagerly bucking [npc.her] [npc.hips] in response, desperately pressing [npc.her] [npc.topClothing(pussy)] against your [pc.fingers] as [npc.she] pleads for you to continue.",
								" [npc.Name] eagerly starts thrusting [npc.her] [npc.hips] back against your [pc.fingers], [npc.moaning+] as [npc.she] presses [npc.her] [npc.topClothing(pussy)] back against your touch."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, bucking [npc.her] [npc.hips] against your touch, [npc.she] encourages you to continue stimulating [npc.her] delicate folds.",
								" [npc.Name] starts bucking [npc.her] [npc.hips] in response, pressing [npc.her] [npc.topClothing(pussy)] against your [pc.fingers] as [npc.she] pleads for you to continue.",
								" [npc.Name] starts thrusting [npc.her] [npc.hips] back against your [pc.fingers], [npc.moaning+] as [npc.she] presses [npc.her] [npc.topClothing(pussy)] back against your touch."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.A_sob+] bursts out from between [npc.name]'s [npc.lips+], and, pulling [npc.her] [npc.hips] away from your touch, [npc.she] pleads with you to stop touching [npc.herHim] down there.",
								" [npc.Name] pulls [npc.her] [npc.hips] away in response, letting out [npc.a_sob+] as [npc.she] pleads for you to stop.",
								" [npc.Name] tries to pull [npc.her] [npc.hips] away from your [pc.fingers], [npc.sobbing+] as [npc.she] begs for you to leave [npc.herHim] alone."));
						break;
					default:
						break;
				}
		
				return UtilText.nodeContentSB.toString();
				
			}else{
				
				UtilText.nodeContentSB.setLength(0);
				
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc.name]'s [npc.legs], you trace your [pc.fingers+] over [npc.her] [npc.pussy+], letting out a soft [pc.moan] as you gently stroke [npc.her] outer labia.",
								"With a soft [pc.moan], you reach down between [npc.name]'s [npc.legs], gently pushing your [pc.fingers] against [npc.her] outer labia as you start stroking [npc.her] [npc.pussy+].",
								"Slowly teasing your [pc.fingers+] between [npc.name]'s [npc.legs], you gently press down, letting out a soft [pc.moan] as you start stroking [npc.her] [npc.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc.name]'s [npc.legs], you eagerly trace your [pc.fingers+] over [npc.her] [npc.pussy+], letting out [pc.a_moan+] as you greedily start stroking [npc.her] outer labia.",
								"With [pc.a_moan+], you reach down between [npc.name]'s [npc.legs], eagerly pressing your [pc.fingers] against [npc.her] outer labia as you start stroking [npc.her] [npc.pussy+].",
								"Teasing your [pc.fingers+] between [npc.name]'s [npc.legs], you eagerly press down against [npc.her] outer labia, letting out [pc.a_moan+] as you start stroking [npc.her] [npc.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc.name]'s [npc.legs], you roughly grind your [pc.fingers+] over [npc.her] [npc.pussy+], letting out [pc.a_moan+] as you greedily start rubbing at [npc.her] outer labia.",
								"With [pc.a_moan+], you reach down between [npc.name]'s [npc.legs], greedily pressing your [pc.fingers] against [npc.her] outer labia as you start roughly stroking [npc.her] [npc.pussy+].",
								"Roughly pushing your [pc.fingers+] between [npc.name]'s [npc.legs], you greedily press down against [npc.her] outer labia, letting out [pc.a_moan+] as you start forcefully rubbing [npc.her] [npc.pussy+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc.name]'s [npc.legs], you eagerly trace your [pc.fingers+] over [npc.her] [npc.pussy+], letting out [pc.a_moan+] as you greedily start stroking [npc.her] outer labia.",
								"With [pc.a_moan+], you reach down between [npc.name]'s [npc.legs], eagerly pressing your [pc.fingers] against [npc.her] outer labia as you start stroking [npc.her] [npc.pussy+].",
								"Teasing your [pc.fingers+] between [npc.name]'s [npc.legs], you eagerly press down against [npc.her] outer labia, letting out [pc.a_moan+] as you start stroking [npc.her] [npc.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc.name]'s [npc.legs], you trace your [pc.fingers+] over [npc.her] [npc.pussy+], letting out [pc.a_moan+] as you start stroking [npc.her] outer labia.",
								"With [pc.a_moan+], you reach down between [npc.name]'s [npc.legs], pressing your [pc.fingers] against [npc.her] outer labia as you start stroking [npc.her] [npc.pussy+].",
								"Teasing your [pc.fingers+] between [npc.name]'s [npc.legs], you press down against [npc.her] outer labia, letting out [pc.a_moan+] as you start stroking [npc.her] [npc.pussy+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" A soft [npc.moan] drifts out from between [npc.name]'s [npc.lips+], and with a gentle bucking of [npc.her] [npc.hips], [npc.she] softly encourages you to continue stroking [npc.her] delicate folds.",
								" [npc.Name] starts gently bucking [npc.her] [npc.hips] in response, softly pressing [npc.her] [npc.pussy+] against your [pc.fingers] as [npc.she] pleads for you to continue.",
								" [npc.Name] gently starts thrusting [npc.her] [npc.hips] back against your [pc.fingers], softly [npc.moaning] as [npc.she] presses [npc.her] [npc.pussy+] back against your touch."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and with an eager bucking of [npc.her] [npc.hips], [npc.she] desperately encourages you to continue stimulating [npc.her] delicate folds.",
								" [npc.Name] starts eagerly bucking [npc.her] [npc.hips] in response, desperately pressing [npc.her] [npc.pussy+] against your [pc.fingers] as [npc.she] pleads for you to continue.",
								" [npc.Name] eagerly starts thrusting [npc.her] [npc.hips] back against your [pc.fingers], [npc.moaning+] as [npc.she] presses [npc.her] [npc.pussy+] back against your touch."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and with a violent buck of [npc.her] [npc.hips], [npc.she] roughly orders you to continue stimulating [npc.her] delicate folds.",
								" [npc.Name] starts frantically bucking [npc.her] [npc.hips] in response, roughly pressing [npc.her] [npc.pussy+] against your [pc.fingers] as [npc.she] demands that you continue.",
								" [npc.Name] forcefully starts thrusting [npc.her] [npc.hips] back against your [pc.fingers], [npc.moaning+] as [npc.she] roughly grinds [npc.her] [npc.pussy+] back against your touch."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and with an eager bucking of [npc.her] [npc.hips], [npc.she] desperately encourages you to continue stimulating [npc.her] delicate folds.",
								" [npc.Name] starts eagerly bucking [npc.her] [npc.hips] in response, desperately pressing [npc.her] [npc.pussy+] against your [pc.fingers] as [npc.she] pleads for you to continue.",
								" [npc.Name] eagerly starts thrusting [npc.her] [npc.hips] back against your [pc.fingers], [npc.moaning+] as [npc.she] presses [npc.her] [npc.pussy+] back against your touch."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, bucking [npc.her] [npc.hips] against your touch, [npc.she] encourages you to continue stimulating [npc.her] delicate folds.",
								" [npc.Name] starts bucking [npc.her] [npc.hips] in response, pressing [npc.her] [npc.pussy+] against your [pc.fingers] as [npc.she] pleads for you to continue.",
								" [npc.Name] starts thrusting [npc.her] [npc.hips] back against your [pc.fingers], [npc.moaning+] as [npc.she] presses [npc.her] [npc.pussy+] back against your touch."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.A_sob+] bursts out from between [npc.name]'s [npc.lips+], and, pulling [npc.her] [npc.hips] away from your touch, [npc.she] pleads with you to stop touching [npc.herHim] down there.",
								" [npc.Name] pulls [npc.her] [npc.hips] away in response, letting out [npc.a_sob+] as [npc.she] pleads for you to stop.",
								" [npc.Name] tries to pull [npc.her] [npc.hips] away from your [pc.fingers], [npc.sobbing+] as [npc.she] begs for you to leave [npc.herHim] alone."));
						break;
					default:
						break;
				}
				
				return UtilText.nodeContentSB.toString();
				
			}
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction PLAYER_CLIT_PLAY = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Tease clit";
		}

		@Override
		public String getActionDescription() {
			return "Reach down to [npc.name]'s [npc.pussy+] and start playing with [npc.her] [npc.clit].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPacePlayer()!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly sliding your [pc.fingers+] up the length of [npc.name]'s [npc.pussy+], you focus your attention on [npc.her] [npc.clit+], gently squeezing and rubbing it as you let out a little [pc.moan].",
							"Gently tracing your [pc.fingers+] over [npc.name]'s [npc.pussy+], you let out a little [pc.moan] as you home in on [npc.her] [npc.clit+], before starting to softly rub and pinch it.",
							"Teasing your [pc.fingers] over [npc.name]'s [npc.pussy+], a little [pc.moan] escapes from between your [pc.lips] as you start to gently rub and pinch at [npc.her] [npc.clit+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.fingers+] up the length of [npc.name]'s [npc.pussy+], you focus your attention on [npc.her] [npc.clit+], eagerly squeezing and rubbing it as you let out [pc.a_moan+].",
							"Tracing your [pc.fingers+] over [npc.name]'s [npc.pussy+], you let out [pc.a_moan+] as you home in on [npc.her] [npc.clit+], before starting to eagerly rub and pinch it.",
							"Teasing your [pc.fingers] over [npc.name]'s [npc.pussy+], [pc.a_moan+] escapes from between your [pc.lips] as you eagerly start rubbing and pinching [npc.her] [npc.clit+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding your [pc.fingers+] up the length of [npc.name]'s [npc.pussy+], you focus your attention on [npc.her] [npc.clit+], cruelly squeezing and rubbing it as you let out [pc.a_moan+].",
							"Greedily tracing your [pc.fingers+] over [npc.name]'s [npc.pussy+], you let out [pc.a_moan+] as you home in on [npc.her] [npc.clit+], before starting to roughly rub and pinch it.",
							"Grinding your [pc.fingers] over [npc.name]'s [npc.pussy+], [pc.a_moan+] escapes from between your [pc.lips] as you roughly start rubbing and pinching [npc.her] [npc.clit+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.fingers+] up the length of [npc.name]'s [npc.pussy+], you focus your attention on [npc.her] [npc.clit+], eagerly squeezing and rubbing it as you let out [pc.a_moan+].",
							"Tracing your [pc.fingers+] over [npc.name]'s [npc.pussy+], you let out [pc.a_moan+] as you home in on [npc.her] [npc.clit+], before starting to eagerly rub and pinch it.",
							"Teasing your [pc.fingers] over [npc.name]'s [npc.pussy+], [pc.a_moan+] escapes from between your [pc.lips] as you eagerly start rubbing and pinching [npc.her] [npc.clit+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.fingers+] up the length of [npc.name]'s [npc.pussy+], you focus your attention on [npc.her] [npc.clit+], squeezing and rubbing it as you let out [pc.a_moan+].",
							"Tracing your [pc.fingers+] over [npc.name]'s [npc.pussy+], you let out [pc.a_moan+] as you home in on [npc.her] [npc.clit+], before starting to rub and pinch it.",
							"Teasing your [pc.fingers] over [npc.name]'s [npc.pussy+], [pc.a_moan+] escapes from between your [pc.lips] as you start rubbing and pinching [npc.her] [npc.clit+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" A soft [npc.moan] drifts out from [npc.name]'s mouth as [npc.she] gently pushes [npc.her] [npc.hips] back against your touch.",
							" [npc.Name] gently pushes [npc.her] [npc.hips] out against your [pc.hand], [npc.moaning] in delight as [npc.she] softly encourages you to continue playing with [npc.her] [npc.clit+].",
							" [npc.Name] slowly pushes [npc.her] [npc.hips] out against your [pc.fingers], [npc.moaning] and arching [npc.her] back as [npc.she] gently presses [npc.her] [npc.clit+] down against your touch."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from [npc.name]'s mouth as [npc.she] eagerly pushes [npc.her] [npc.hips] back against your touch.",
							" [npc.Name] eagerly pushes [npc.her] [npc.hips] out against your [pc.hand], [npc.moaning] in delight as [npc.she] enthusiastically encourages you to continue playing with [npc.her] [npc.clit+].",
							" [npc.Name] eagerly pushes [npc.her] [npc.hips] out against your [pc.fingers], [npc.moaning] and arching [npc.her] back as [npc.she] desperately presses [npc.her] [npc.clit+] down against your touch."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from [npc.name]'s mouth as [npc.she] roughly thrusts [npc.her] [npc.hips] back against your touch.",
							" [npc.Name] roughly grinds [npc.her] [npc.hips] against your [pc.hand], [npc.moaning] in delight as [npc.she] orders you to continue playing with [npc.her] [npc.clit+].",
							" [npc.Name] forcefully thrusts [npc.her] [npc.hips] against your [pc.fingers], [npc.moaning] and arching [npc.her] back as [npc.she] roughly grinds [npc.her] [npc.clit+] down against your touch."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from [npc.name]'s mouth as [npc.she] eagerly pushes [npc.her] [npc.hips] back against your touch.",
							" [npc.Name] eagerly pushes [npc.her] [npc.hips] out against your [pc.hand], [npc.moaning] in delight as [npc.she] enthusiastically encourages you to continue playing with [npc.her] [npc.clit+].",
							" [npc.Name] eagerly pushes [npc.her] [npc.hips] out against your [pc.fingers], [npc.moaning] and arching [npc.her] back as [npc.she] desperately presses [npc.her] [npc.clit+] down against your touch."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from [npc.name]'s mouth as [npc.she] pushes [npc.her] [npc.hips] back against your touch.",
							" [npc.Name] pushes [npc.her] [npc.hips] out against your [pc.hand], [npc.moaning] in delight as [npc.she] encourages you to continue playing with [npc.her] [npc.clit+].",
							" [npc.Name] pushes [npc.her] [npc.hips] out against your [pc.fingers], [npc.moaning] and arching [npc.her] back as [npc.she] presses [npc.her] [npc.clit+] down against your touch."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] tries to pull [npc.her] [npc.pussy] away from your touch, pleading with you to stop as [npc.she] struggles against you.",
							" [npc.Name] pulls [npc.her] [npc.hips] back in response, letting out [npc.a_sob+] as [npc.she] pleads for you to stop touching [npc.herHim].",
							" [npc.Name] tries to pull [npc.her] [npc.pussy] away from your [pc.fingers], [npc.sobbing+] as [npc.she] begs for you to leave [npc.herHim] alone."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction PLAYER_CLIT_FOCUS = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Clit focus";
		}

		@Override
		public String getActionDescription() {
			return "Focus on pleasuring [npc.name]'s [npc.clit+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPacePlayer()!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly sinking your [pc.fingers+] deep into [npc.name]'s [npc.pussy+], you lift your thumb up to [npc.her] [npc.clit],"
									+ " gently pressing down and rubbing against it as you continue curling your digits up inside of [npc.herHim].",
							"Gently sliding your [pc.fingers+] into [npc.name]'s [npc.pussy+], you press your thumb down against [npc.her] [npc.clit+], softly rubbing against it as you let out a little [pc.moan].",
							"Gently pushing your [pc.fingers] deep into [npc.name]'s [npc.pussy+], a little [pc.moan] escapes from between your [pc.lips] as you lift your thumb to start gently rubbing and pressing down on [npc.her] [npc.clit+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly sinking your [pc.fingers+] deep into [npc.name]'s [npc.pussy+], you lift your thumb up to [npc.her] [npc.clit],"
									+ " greedily pressing down and rubbing against it as you continue to curl your digits up inside of [npc.herHim].",
							"Greedily sliding your [pc.fingers+] into [npc.name]'s [npc.pussy+], you press your thumb down against [npc.her] [npc.clit+], eagerly rubbing against it as you let out a little [pc.moan].",
							"Pushing your [pc.fingers] deep into [npc.name]'s [npc.pussy+], [pc.a_moan+] escapes from between your [pc.lips] as you lift your thumb to start eagerly rubbing and pressing down on [npc.her] [npc.clit+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly digging your [pc.fingers+] deep into [npc.name]'s [npc.pussy+], you lift your thumb up to [npc.her] [npc.clit],"
									+ " forcefully pressing down and grinding against it as you continue to dominantly curl your digits up inside of [npc.herHim].",
							"Forcing your [pc.fingers+] deep into [npc.name]'s [npc.pussy+], you grind your thumb down against [npc.her] [npc.clit+], roughly rubbing against it as you let out a little [pc.moan].",
							"Slamming your [pc.fingers] deep into [npc.name]'s [npc.pussy+], [pc.a_moan+] escapes from between your [pc.lips] as you lift your thumb to start roughly rubbing and grinding down on [npc.her] [npc.clit+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly sinking your [pc.fingers+] deep into [npc.name]'s [npc.pussy+], you lift your thumb up to [npc.her] [npc.clit],"
									+ " greedily pressing down and rubbing against it as you continue to curl your digits up inside of [npc.herHim].",
							"Greedily sliding your [pc.fingers+] into [npc.name]'s [npc.pussy+], you press your thumb down against [npc.her] [npc.clit+], eagerly rubbing against it as you let out a little [pc.moan].",
							"Pushing your [pc.fingers] deep into [npc.name]'s [npc.pussy+], [pc.a_moan+] escapes from between your [pc.lips] as you lift your thumb to start eagerly rubbing and pressing down on [npc.her] [npc.clit+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sinking your [pc.fingers+] deep into [npc.name]'s [npc.pussy+], you lift your thumb up to [npc.her] [npc.clit], pressing down and rubbing against it as you continue to curl your digits up inside of [npc.herHim].",
							"Sliding your [pc.fingers+] into [npc.name]'s [npc.pussy+], you press your thumb down against [npc.her] [npc.clit+], rubbing against it as you let out a little [pc.moan].",
							"Pushing your [pc.fingers] deep into [npc.name]'s [npc.pussy+], [pc.a_moan+] escapes from between your [pc.lips] as you lift your thumb to start rubbing and pressing down on [npc.her] [npc.clit+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" A soft [npc.moan] drifts out from [npc.name]'s mouth as [npc.she] gently pushes [npc.her] [npc.hips] back against your touch.",
							" [npc.Name] gently pushes [npc.her] [npc.hips] out against your [pc.hand], [npc.moaning] in delight as [npc.she] softly encourages you to continue playing with [npc.her] [npc.clit+].",
							" [npc.Name] slowly pushes [npc.her] [npc.hips] out against your [pc.fingers], [npc.moaning] and arching [npc.her] back as [npc.she] gently presses [npc.her] [npc.clit+] down against your touch."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from [npc.name]'s mouth as [npc.she] eagerly pushes [npc.her] [npc.hips] back against your touch.",
							" [npc.Name] eagerly pushes [npc.her] [npc.hips] out against your [pc.hand], [npc.moaning] in delight as [npc.she] enthusiastically encourages you to continue playing with [npc.her] [npc.clit+].",
							" [npc.Name] eagerly pushes [npc.her] [npc.hips] out against your [pc.fingers], [npc.moaning] and arching [npc.her] back as [npc.she] desperately presses [npc.her] [npc.clit+] down against your touch."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from [npc.name]'s mouth as [npc.she] roughly thrusts [npc.her] [npc.hips] back against your touch.",
							" [npc.Name] roughly grinds [npc.her] [npc.hips] against your [pc.hand], [npc.moaning] in delight as [npc.she] orders you to continue playing with [npc.her] [npc.clit+].",
							" [npc.Name] forcefully thrusts [npc.her] [npc.hips] against your [pc.fingers], [npc.moaning] and arching [npc.her] back as [npc.she] roughly grinds [npc.her] [npc.clit+] down against your touch."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from [npc.name]'s mouth as [npc.she] eagerly pushes [npc.her] [npc.hips] back against your touch.",
							" [npc.Name] eagerly pushes [npc.her] [npc.hips] out against your [pc.hand], [npc.moaning] in delight as [npc.she] enthusiastically encourages you to continue playing with [npc.her] [npc.clit+].",
							" [npc.Name] eagerly pushes [npc.her] [npc.hips] out against your [pc.fingers], [npc.moaning] and arching [npc.her] back as [npc.she] desperately presses [npc.her] [npc.clit+] down against your touch."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from [npc.name]'s mouth as [npc.she] pushes [npc.her] [npc.hips] back against your touch.",
							" [npc.Name] pushes [npc.her] [npc.hips] out against your [pc.hand], [npc.moaning] in delight as [npc.she] encourages you to continue playing with [npc.her] [npc.clit+].",
							" [npc.Name] pushes [npc.her] [npc.hips] out against your [pc.fingers], [npc.moaning] and arching [npc.her] back as [npc.she] presses [npc.her] [npc.clit+] down against your touch."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] tries to pull [npc.her] [npc.pussy] away from your touch, pleading with you to stop as [npc.she] struggles against you.",
							" [npc.Name] pulls [npc.her] [npc.hips] back in response, letting out [npc.a_sob+] as [npc.she] pleads for you to stop touching [npc.herHim].",
							" [npc.Name] tries to pull [npc.her] [npc.pussy] away from your [pc.fingers], [npc.sobbing+] as [npc.she] begs for you to leave [npc.herHim] alone."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction PLAYER_FINGERING_START = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Finger [npc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Sink your [pc.fingers] into [npc.name]'s [npc.pussy+] and start fingering [npc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly teasing your [pc.fingers] over [npc.name]'s outer labia, you let out a little [pc.moan] before slowly sinking your digits into [npc.her] [npc.pussy+].",
							"You press your [pc.fingers] down between [npc.name]'s [npc.legs+], and with a slow, steady pressure, you gently sink your digits into [npc.her] [npc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing your [pc.fingers] over [npc.name]'s outer labia, you let out [pc.a_moan+] before greedily sinking your digits into [npc.her] [npc.pussy+].",
							"You eagerly press your [pc.fingers] down between [npc.name]'s [npc.legs+], and with a determined thrust, you greedily sink your digits into [npc.her] [npc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding your [pc.fingers] down against [npc.name]'s outer labia, you let out [pc.a_moan+] before violently slamming your digits deep into [npc.her] [npc.pussy+].",
							"You roughly grind your [pc.fingers] down between [npc.name]'s [npc.legs+], and with a forceful thrust, you greedily slam your digits deep into [npc.her] [npc.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing your [pc.fingers] over [npc.name]'s outer labia, you let out [pc.a_moan+] before greedily sinking your digits into [npc.her] [npc.pussy+].",
							"You eagerly press your [pc.fingers] down between [npc.name]'s [npc.legs+], and with a determined thrust, you greedily sink your digits into [npc.her] [npc.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing your [pc.fingers] over [npc.name]'s outer labia, you let out [pc.a_moan+] before sinking your digits into [npc.her] [npc.pussy+].",
							"You press your [pc.fingers] down between [npc.name]'s [npc.legs+], and with a determined push of your [pc.hand], you sink your digits into [npc.her] [npc.pussy+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a soft [npc.moan] as your [pc.fingers] enter [npc.herHim], gently bucking [npc.her] [npc.hips] against you as [npc.she] helps to sink your [pc.fingers] even deeper into [npc.her] [npc.pussy+].",
							" With a soft [npc.moan], [npc.she] starts gently bucking [npc.her] [npc.hips] into your [pc.hand], encouraging you to sink your [pc.fingers] even deeper into [npc.her] [npc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as your [pc.fingers] enter [npc.herHim],"
									+ " eagerly bucking [npc.her] [npc.hips] against you as [npc.she] enthusiastically helps to sink your [pc.fingers] even deeper into [npc.her] [npc.pussy+].",
							" With [npc.a_moan+], [npc.she] starts eagerly bucking [npc.her] [npc.hips] into your [pc.hand], desperately encouraging you to sink your [pc.fingers] even deeper into [npc.her] [npc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as your [pc.fingers] enter [npc.herHim],"
									+ " violently thrusting [npc.her] [npc.hips] against you as [npc.she] roughly forces you to sink your [pc.fingers] even deeper into [npc.her] [npc.pussy+].",
							" With [npc.a_moan+], [npc.she] starts violently bucking [npc.her] [npc.hips] into your [pc.hand], roughly forcing you to sink your [pc.fingers] even deeper into [npc.her] [npc.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as your [pc.fingers] enter [npc.herHim],"
									+ " eagerly bucking [npc.her] [npc.hips] against you as [npc.she] enthusiastically helps to sink your [pc.fingers] even deeper into [npc.her] [npc.pussy+].",
							" With [npc.a_moan+], [npc.she] starts eagerly bucking [npc.her] [npc.hips] into your [pc.hand], desperately encouraging you to sink your [pc.fingers] even deeper into [npc.her] [npc.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as your [pc.fingers] enter [npc.herHim], bucking [npc.her] [npc.hips] against you as [npc.she] helps to sink your [pc.fingers] even deeper into [npc.her] [npc.pussy+].",
							" With [npc.a_moan+], [npc.she] starts bucking [npc.her] [npc.hips] into your [pc.hand], encouraging you to sink your [pc.fingers] even deeper into [npc.her] [npc.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_sob+] as your [pc.fingers] enter [npc.herHim], trying, in vain, to pull [npc.her] [npc.hips] back from your unwanted penetration, struggling and [npc.sobbing] all the while.",
							" With [npc.a_sob+], [npc.she] tries, in vain, to pull away from your unwanted penetration, [npc.sobbing] and struggling against you as your unwelcome [pc.fingers] push deep into [npc.her] [npc.pussy+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction PLAYER_FINGERING_DOM_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PARTNER,
			SexPace.DOM_GENTLE,
			null) {
		@Override
		public String getActionTitle() {
			return "Gentle fingering";
		}

		@Override
		public String getActionDescription() {
			return "Gently finger [npc.name]'s [npc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sinking your [pc.fingers+] into [npc.name]'s [npc.pussy+], you curl your digits up, softly stroking [npc.her] vaginal walls as you start slowly fingering [npc.herHim].",
					"Slowly pushing your [pc.hand] into [npc.name]'s groin, you gently slide your [pc.fingers+] deep into [npc.her] [npc.pussy+], letting out a little [pc.moan] as you start softly fingering [npc.herHim].",
					"Pressing your [pc.hand] down between [npc.name]'s [npc.legs], you let out a little [pc.moan] before gently sliding your [pc.fingers+] deep into [npc.her] [npc.pussy+]."));
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] eagerly thrusts [npc.her] [npc.hips] out in response, letting out a delighted [npc.moan] as [npc.she] starts enthusiastically imploring you to continue fingering [npc.herHim].",
							" A delighted [npc.moan] bursts out from between [npc.her] [npc.lips+], and, eagerly thrusting [npc.her] [npc.hips] out against your touch, [npc.she] begs for you to continue fingering [npc.herHim].",
							" [npc.Moaning] in delight, [npc.she] eagerly grinds down against your [pc.hand],"
									+ " eagerly [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.fingers] deep into [npc.her] [npc.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to recoil [npc.her] [npc.pussy] away from your touch, [npc.she] lets out [npc.a_sob+] as [npc.she] weakly tries to struggle away from your intruding [pc.fingers].",
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips] as [npc.she] weakly tries to push you away, squirming and protesting as you continue to gently finger [npc.her] [npc.pussy+].",
							" [npc.Sobbing] in distress, [npc.she] tries, in vain, to recoil away from your touch, struggling against you as your [pc.fingers] continue gently sliding deep into [npc.her] [npc.pussy+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] thrusts [npc.her] [npc.hips] out in response, letting out [npc.a_moan+] as [npc.she] begs for you to continue fingering [npc.herHim].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, pushing [npc.her] [npc.hips] out against your touch, [npc.she] begs for you to continue fingering [npc.herHim].",
							" [npc.She] grinds down against your [pc.hand], [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.fingers] deep into [npc.her] [npc.pussy+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction PLAYER_FINGERING_DOM_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PARTNER,
			SexPace.DOM_NORMAL,
			null) {
		@Override
		public String getActionTitle() {
			return "Continue fingering";
		}

		@Override
		public String getActionDescription() {
			return "Continue fingering [npc.name]'s [npc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly sinking your [pc.fingers+] deep into [npc.name]'s [npc.pussy+], you curl your digits up, stroking [npc.her] vaginal walls as you start passionately fingering [npc.herHim].",
					"Firmly pushing your [pc.hand] into [npc.name]'s groin, you eagerly slide your [pc.fingers+] deep into [npc.her] [npc.pussy+], letting out [pc.a_moan+] as you start rapidly fingering [npc.herHim].",
					"Eagerly pressing your [pc.hand] down between [npc.name]'s [npc.legs], you let out [pc.a_moan+] before enthusiastically sliding your [pc.fingers+] deep into [npc.her] [npc.pussy+]."));
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] eagerly thrusts [npc.her] [npc.hips] out in response, letting out a delighted [npc.moan] as [npc.she] starts enthusiastically imploring you to continue fingering [npc.herHim].",
							" A delighted [npc.moan] bursts out from between [npc.her] [npc.lips+], and, eagerly thrusting [npc.her] [npc.hips] out against your touch, [npc.she] begs for you to continue fingering [npc.herHim].",
							" [npc.Moaning] in delight, [npc.she] eagerly grinds down against your [pc.hand],"
									+ " eagerly [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.fingers] deep into [npc.her] [npc.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to recoil [npc.her] [npc.pussy] away from your touch, [npc.she] lets out [npc.a_sob+] as [npc.she] weakly tries to struggle away from your intruding [pc.fingers].",
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips] as [npc.she] weakly tries to push you away, squirming and protesting as you continue to eagerly finger [npc.her] [npc.pussy+].",
							" [npc.Sobbing] in distress, [npc.she] tries, in vain, to recoil away from your touch, struggling against you as your [pc.fingers] continue eagerly sliding deep into [npc.her] [npc.pussy+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] thrusts [npc.her] [npc.hips] out in response, letting out [npc.a_moan+] as [npc.she] begs for you to continue fingering [npc.herHim].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, pushing [npc.her] [npc.hips] out against your touch, [npc.she] begs for you to continue fingering [npc.herHim].",
							" [npc.She] grinds down against your [pc.hand], [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.fingers] deep into [npc.her] [npc.pussy+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction PLAYER_FINGERING_DOM_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PARTNER,
			SexPace.DOM_ROUGH,
			null) {
		@Override
		public String getActionTitle() {
			return "Rough fingering";
		}

		@Override
		public String getActionDescription() {
			return "Roughly finger [npc.name]'s [npc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}


		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Greedily thrusting your [pc.fingers+] deep into [npc.name]'s [npc.pussy+], you curl your digits up, roughly stroking [npc.her] vaginal walls as you start rapidly fingering [npc.herHim].",
					"Roughly slamming your [pc.hand] into [npc.name]'s groin, you greedily pump your [pc.fingers+] deep into [npc.her] [npc.pussy+], letting out [pc.a_moan+] as you start violently fingering [npc.herHim].",
					"Forcefully pressing your [pc.hand] down between [npc.name]'s [npc.legs], you let out [pc.a_moan+] before roughly slamming your [pc.fingers+] deep into [npc.her] [npc.pussy+]."));

			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] eagerly thrusts [npc.her] [npc.hips] out in response, letting out a delighted [npc.moan] as [npc.she] starts enthusiastically imploring you to continue fingering [npc.herHim].",
							" A delighted [npc.moan] bursts out from between [npc.her] [npc.lips+], and, eagerly thrusting [npc.her] [npc.hips] out against your touch, [npc.she] begs for you to continue fingering [npc.herHim].",
							" [npc.Moaning] in delight, [npc.she] eagerly grinds down against your [pc.hand],"
									+ " eagerly [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.fingers] deep into [npc.her] [npc.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to recoil [npc.her] [npc.pussy] away from your touch, [npc.she] lets out [npc.a_sob+] as [npc.she] weakly tries to struggle away from your greedy [pc.fingers].",
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips] as [npc.she] weakly tries to push you away, squirming and protesting as you continue to roughly finger [npc.her] [npc.pussy+].",
							" [npc.Sobbing] in distress, [npc.she] tries, in vain, to recoil away from your touch, struggling against you as your [pc.fingers] continue roughly pumping deep into [npc.her] [npc.pussy+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] thrusts [npc.her] [npc.hips] out in response, letting out [npc.a_moan+] as [npc.she] begs for you to continue fingering [npc.herHim].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, pushing [npc.her] [npc.hips] out against your touch, [npc.she] begs for you to continue fingering [npc.herHim].",
							" [npc.She] grinds down against your [pc.hand], [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.fingers] deep into [npc.her] [npc.pussy+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION), new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_SADIST));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION), new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_MASOCHIST));
		}
	};
	
	public static final SexAction PLAYER_FINGERING_SUB_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PARTNER,
			SexPace.SUB_NORMAL,
			null) {
		@Override
		public String getActionTitle() {
			return "Continue fingering";
		}

		@Override
		public String getActionDescription() {
			return "Continue fingering [npc.name]'s [npc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking your [pc.fingers+] into [npc.name]'s [npc.pussy+], you curl your digits up, stroking [npc.her] vaginal walls as you focus your efforts on fingering [npc.herHim].",
					"Pushing your [pc.hand] into [npc.name]'s groin, you slide your [pc.fingers+] deep into [npc.her] [npc.pussy+], letting out [pc.a_moan+] as you start focusing on fingering [npc.herHim].",
					"Pressing your [pc.hand] down between [npc.name]'s [npc.legs], you let out [pc.a_moan+] before sliding your [pc.fingers+] deep into [npc.her] [npc.pussy+]."));
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc.She] slowly thrusts [npc.her] [npc.hips] out in response, letting out a soft [npc.moan] as [npc.she] starts gently imploring you to continue fingering [npc.herHim].",
						" A soft [npc.moan] drifts out from between [npc.her] [npc.lips+], and, gently pushing [npc.her] [npc.hips] out against your touch, [npc.she] begs for you to continue fingering [npc.herHim].",
						" [npc.Moaning] in delight, [npc.she] slowly grinds down against your [pc.hand], softly [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.fingers] deep into [npc.her] [npc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] violently thrusts [npc.her] [npc.hips] out in response, letting out [npc.a_moan+] as [npc.she] roughly demands that you continue fingering [npc.herHim].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, roughly grinding [npc.her] [npc.hips] out against your touch, [npc.she] orders you to continue fingering [npc.herHim].",
							" [npc.She] roughly grinds down against your [pc.hand], ordering you to continue as [npc.her] movements cause you to sink your [pc.fingers] deep into [npc.her] [npc.pussy+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc.She] thrusts [npc.her] [npc.hips] out in response, letting out [npc.a_moan+] as [npc.she] begs for you to continue fingering [npc.herHim].",
						" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, pushing [npc.her] [npc.hips] out against your touch, [npc.she] begs for you to continue fingering [npc.herHim].",
						" [npc.She] grinds down against your [pc.hand], [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.fingers] deep into [npc.her] [npc.pussy+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction PLAYER_FINGERING_SUB_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PARTNER,
			SexPace.SUB_EAGER,
			null) {
		@Override
		public String getActionTitle() {
			return "Eager fingering";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly finger [npc.name]'s [npc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly sinking your [pc.fingers+] deep into [npc.name]'s [npc.pussy+], you curl your digits up, rapidly stroking [npc.her] vaginal walls as you start passionately fingering [npc.herHim].",
					"Firmly pushing your [pc.hand] into [npc.name]'s groin, you eagerly slide your [pc.fingers+] deep into [npc.her] [npc.pussy+], letting out [pc.a_moan+] as you start rapidly fingering [npc.herHim].",
					"Eagerly pressing your [pc.hand] down between [npc.name]'s [npc.legs], you let out [pc.a_moan+] before enthusiastically sliding your [pc.fingers+] deep into [npc.her] [npc.pussy+]."));

			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc.She] slowly thrusts [npc.her] [npc.hips] out in response, letting out a soft [npc.moan] as [npc.she] starts gently imploring you to continue fingering [npc.herHim].",
						" A soft [npc.moan] drifts out from between [npc.her] [npc.lips+], and, gently pushing [npc.her] [npc.hips] out against your touch, [npc.she] begs for you to continue fingering [npc.herHim].",
						" [npc.Moaning] in delight, [npc.she] slowly grinds down against your [pc.hand], softly [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.fingers] deep into [npc.her] [npc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] violently thrusts [npc.her] [npc.hips] out in response, letting out [npc.a_moan+] as [npc.she] roughly demands that you continue fingering [npc.herHim].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, roughly grinding [npc.her] [npc.hips] out against your touch, [npc.she] orders you to continue fingering [npc.herHim].",
							" [npc.She] roughly grinds down against your [pc.hand], ordering you to continue as [npc.her] movements cause you to sink your [pc.fingers] deep into [npc.her] [npc.pussy+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc.She] thrusts [npc.her] [npc.hips] out in response, letting out [npc.a_moan+] as [npc.she] begs for you to continue fingering [npc.herHim].",
						" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, pushing [npc.her] [npc.hips] out against your touch, [npc.she] begs for you to continue fingering [npc.herHim].",
						" [npc.She] grinds down against your [pc.hand], [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.fingers] deep into [npc.her] [npc.pussy+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION), new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
	};
	
	public static final SexAction PLAYER_FINGERING_STOP = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Stop fingering";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [pc.fingers] out of [npc.name]'s [npc.pussy+] and stop fingering [npc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking your [pc.fingers] out of [npc.name]'s [npc.pussy+], you give [npc.her] [npc.clit+] a cruel squeeze before taking your [pc.hand] away from [npc.her] groin.",
							"Thrusting deep inside [npc.name] one last time, you then yank your [pc.fingers] back out of [npc.her] [npc.pussy+], putting an end to your rough fingering."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a distressed little [pc.sob], you quickly pull your [pc.fingers] out of [npc.name]'s [npc.pussy+].",
							"Pulling your [pc.fingers] out of [npc.name]'s [npc.pussy+], you let out a little [pc.sob] before pleading for [npc.herHim] to leave you alone."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.fingers] out of [npc.name]'s [npc.pussy+], you give [npc.her] [npc.clit+] a little squeeze before taking your [pc.hand] away from [npc.her] groin.",
							"Pushing deep inside [npc.name] one last time, you then slide your [pc.fingers] back out of [npc.her] [npc.pussy+], putting an end to your fingering."));
					break;
			}
			
			switch(Sex.getSexPacePartner()) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] gasps as you withdraw from [npc.her] [npc.pussy], before [npc.sobbing+] as [npc.she] continues to struggle against you.",
							" With [npc.a_sob+], [npc.name] continues to struggle against you as you withdraw from [npc.her] [npc.pussy+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as you pull your [pc.fingers+] out of [npc.her] [npc.pussy+].",
							" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] desire for more of your attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Partner actions:
	
	public static final SexAction PARTNER_FINGERING_START = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager().isConsensualSex() || !Sex.isPlayerDom(); // Partner can only start fingering in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Get fingered";
		}

		@Override
		public String getActionDescription() {
			return "Get [pc.name] to start fingering your [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking a gentle, but firm, grip on your [pc.hand], [npc.name] slowly guides your [pc.fingers] over [npc.her] outer labia, letting out a little [npc.moan] before pushing your digits into [npc.her] [npc.pussy+].",
							"Taking hold of your [pc.hand], [npc.name] guides your [pc.fingers] down between [npc.her] [npc.legs+], and with a slow, steady pressure, [npc.she] gently pushes your digits into [npc.her] [npc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a firm grip on your [pc.hand], [npc.name] eagerly guides your [pc.fingers] over [npc.her] outer labia, letting out [npc.a_moan+] before greedily pushing your digits into [npc.her] [npc.pussy+].",
							"Taking hold of your [pc.hand], [npc.name] eagerly guides your [pc.fingers] down between [npc.her] [npc.legs+], and with a determined pressure, [npc.she] greedily pushes your digits into [npc.her] [npc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a vice-like grip on your [pc.hand], [npc.name] grinds your [pc.fingers] against [npc.her] outer labia, letting out [npc.a_moan+] before roughly pushing your digits into [npc.her] [npc.pussy+].",
							"Grabbing your [pc.hand], [npc.name] forcefully pushes your [pc.fingers] down between [npc.her] [npc.legs+], and with a dominant, jerking motion, [npc.she] roughly pushes your digits into [npc.her] [npc.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a firm grip on your [pc.hand], [npc.name] eagerly guides your [pc.fingers] over [npc.her] outer labia, letting out [npc.a_moan+] before greedily pushing your digits into [npc.her] [npc.pussy+].",
							"Taking hold of your [pc.hand], [npc.name] eagerly guides your [pc.fingers] down between [npc.her] [npc.legs+], and with a determined pressure, [npc.she] greedily pushes your digits into [npc.her] [npc.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a firm grip on your [pc.hand], [npc.name] guides your [pc.fingers] over [npc.her] outer labia, letting out [npc.a_moan+] before pushing your digits into [npc.her] [npc.pussy+].",
							"Taking hold of your [pc.hand], [npc.name] guides your [pc.fingers] down between [npc.her] [npc.legs+], and with a determined pressure, [npc.she] pushes your digits into [npc.her] [npc.pussy+]."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soft [pc.moan] as you enter [npc.herHim], curling your [pc.fingers] up before gently starting to finger [npc.her] [npc.pussy+].",
							" With a soft [pc.moan], you curl your [pc.fingers+] up inside of [npc.herHim], gently stroking [npc.her] vaginal walls as you set about fingering [npc.her] [npc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as you enter [npc.herHim], curling your [pc.fingers] up before eagerly starting to finger [npc.her] [npc.pussy+].",
							" With [pc.a_moan+], you curl your [pc.fingers+] up inside of [npc.herHim], eagerly stroking [npc.her] vaginal walls as you set about fingering [npc.her] [npc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as you enter [npc.name], and, seeking to remind [npc.herHim] who's in charge, you roughly curl your [pc.fingers] up before starting to ruthlessly finger-fuck [npc.her] [npc.pussy+].",
							" With [pc.a_moan+], you curl your [pc.fingers+] up, seeking to remind [npc.name] who's in charge as you start roughly fingering [npc.her] [npc.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as you enter [npc.herHim], curling your [pc.fingers] up before eagerly starting to finger [npc.her] [npc.pussy+].",
							" With [pc.a_moan+], you curl your [pc.fingers+] up inside of [npc.herHim], eagerly stroking [npc.her] vaginal walls as you set about fingering [npc.her] [npc.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as you enter [npc.herHim], curling your [pc.fingers] up before starting to finger [npc.her] [npc.pussy+].",
							" With [pc.a_moan+], you curl your [pc.fingers+] up inside of [npc.herHim], stroking [npc.her] vaginal walls as you set about fingering [npc.her] [npc.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_sob+] as [npc.she] forces your [pc.fingers] inside of [npc.herHim], keeping a firm grip on your [pc.hand] as you struggle and try to pull yourself free.",
							" With [pc.a_sob+], you struggle against [npc.her] tight grip on your [pc.hand] as [npc.she] forces your [pc.fingers] deep into [npc.her] [npc.pussy+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION), new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION), new ListValue<>(Fetish.FETISH_DOMINANT));
		}
	};
	
	public static final SexAction PARTNER_FINGERED_DOM_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.DOM_GENTLE) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Gently fingered";
		}

		@Override
		public String getActionDescription() {
			return "Gently enjoy [pc.name]'s [pc.fingers+] in your [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently pushing [npc.her] [npc.hips] down against your [pc.hand], [npc.name] lets out a soft [npc.moan] as [npc.she] helps you to sink your [pc.fingers+] deep into [npc.her] [npc.pussy+].",
					"With a soft [npc.moan], [npc.name] gently starts bucking [npc.her] [npc.hips] against your [pc.hand], forcing your [pc.fingers+] ever deeper into [npc.her] [npc.pussy+].",
					"Slowly thrusting [npc.her] [npc.hips] against your [pc.hand], [npc.name] softly [npc.moansVerb] as [npc.her] movements force your [pc.fingers+] deep into [npc.her] [npc.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction PARTNER_FINGERED_DOM_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.DOM_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Enjoy fingering";
		}

		@Override
		public String getActionDescription() {
			return "Enjoy [pc.name]'s [pc.fingers+] in your [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly thrusting [npc.her] [npc.hips] out against your [pc.hand], [npc.name] lets out [npc.a_moan+] as [npc.she] energetically helps to sink your [pc.fingers+] deep into [npc.her] [npc.pussy+].",
					"With [npc.a_moan+], [npc.name] starts energetically bucking [npc.her] [npc.hips] against your [pc.hand], forcing your [pc.fingers+] ever deeper into [npc.her] [npc.pussy+].",
					"Enthusiastically thrusting [npc.her] [npc.hips] against your [pc.hand], [npc.name] [npc.moansVerb+] as [npc.her] eager movements force your [pc.fingers+] deep into [npc.her] [npc.pussy+]."));
					
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction PARTNER_FINGERED_DOM_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Roughly fingered";
		}

		@Override
		public String getActionDescription() {
			return "Roughly force [pc.name]'s [pc.fingers+] deep inside your [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Violently slamming [npc.her] [npc.hips] out against your [pc.hand], [npc.name] lets out [npc.a_moan+] as [npc.she] roughly forces your [pc.fingers+] deep into [npc.her] [npc.pussy+].",
					"With [npc.a_moan+], [npc.name] starts aggressively thrusting [npc.her] [npc.hips] against your [pc.hand], roughly forcing your [pc.fingers+] ever deeper into [npc.her] [npc.pussy+].",
					"Roughly thrusting [npc.her] [npc.hips] against your [pc.hand], [npc.name] [npc.moansVerb+] as [npc.her] forceful movements drive your [pc.fingers+] deep into [npc.her] [npc.pussy+]."));
					
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction PARTNER_FINGERED_SUB_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.SUB_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Enjoy fingering";
		}

		@Override
		public String getActionDescription() {
			return "Enjoy [pc.name]'s [pc.fingers+] in your [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Thrusting [npc.her] [npc.hips] out against your [pc.hand], [npc.name] lets out [npc.a_moan+] as [npc.she] helps to sink your [pc.fingers+] deep into [npc.her] [npc.pussy+].",
					"With [npc.a_moan+], [npc.name] starts bucking [npc.her] [npc.hips] against your [pc.hand], forcing your [pc.fingers+] ever deeper into [npc.her] [npc.pussy+].",
					"Thrusting [npc.her] [npc.hips] against your [pc.hand], [npc.name] [npc.moansVerb+] as [npc.her] movements force your [pc.fingers+] deep into [npc.her] [npc.pussy+]."));
					
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction PARTNER_FINGERED_SUB_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.SUB_EAGER) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Eagerly fingered";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly buck your hips against [pc.name]'s [pc.hand] as [pc.she] fingers your [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly thrusting [npc.her] [npc.hips] out against your [pc.hand], [npc.name] lets out [npc.a_moan+] as [npc.she] energetically helps to sink your [pc.fingers+] deep into [npc.her] [npc.pussy+].",
					"With [npc.a_moan+], [npc.name] starts energetically bucking [npc.her] [npc.hips] against your [pc.hand], forcing your [pc.fingers+] ever deeper into [npc.her] [npc.pussy+].",
					"Enthusiastically thrusting [npc.her] [npc.hips] against your [pc.hand], [npc.name] [npc.moansVerb+] as [npc.her] eager movements force your [pc.fingers+] deep into [npc.her] [npc.pussy+]."));
					
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction PARTNER_FINGERING_SUB_RESIST = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Resist fingering";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull [pc.name]'s [pc.fingers] out of your [npc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+], weakly trying to pull your [pc.fingers] out of [npc.her] [npc.pussy+] as you continue gently fingering [npc.herHim].",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.hips] back from your unwanted touch,"
									+ " struggling in desperation as your [pc.fingers] continue sliding in and out of [npc.her] [npc.pussy+].",
							"Trying desperately to pull [npc.her] [npc.hips] away from your [pc.hand], [npc.name] [npc.sobs] in distress as your [pc.fingers+] continue gently sliding deep into [npc.her] [npc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+],"
									+ " weakly trying to pull your [pc.fingers] out of [npc.her] [npc.pussy+] as you continue eagerly fingering [npc.herHim].",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.hips] back from your unwanted touch,"
									+ " struggling in desperation as your [pc.fingers] continue eagerly sliding in and out of [npc.her] [npc.pussy+].",
							"Trying desperately to pull [npc.her] [npc.hips] away from your [pc.hand], [npc.name] [npc.sobs] in distress as your [pc.fingers+] continue eagerly sliding deep into [npc.her] [npc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+],"
									+ " weakly trying to pull your [pc.fingers] out of [npc.her] [npc.pussy+] as you continue roughly fingering [npc.herHim].",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.hips] back from your rough touch,"
									+ " struggling in desperation as you continue slamming your [pc.fingers] in and out of [npc.her] [npc.pussy+].",
							"Trying desperately to pull [npc.her] [npc.hips] away from your [pc.hand], [npc.name] [npc.sobs] in distress as your [pc.fingers+] continue roughly slamming deep into [npc.her] [npc.pussy+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION), new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_SADIST));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION), new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_MASOCHIST));
		}
	};
	
	public static final SexAction PARTNER_FINGERING_STOP = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager().isConsensualSex() || !Sex.isPlayerDom(); // Partner can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop fingered";
		}

		@Override
		public String getActionDescription() {
			return "Get [pc.name] to pull [pc.her] [pc.fingers] out of your [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking your [pc.fingers] out of [npc.her] [npc.pussy+], [npc.name] growls at you as [npc.she] commands you to stop fingering [npc.herHim].",
							"[npc.Name] leans into you, causing you to inhale [npc.her] "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+" before [npc.she] yanks your [pc.fingers] out of [npc.her] [npc.pussy+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.fingers] out of [npc.her] [npc.pussy+], [npc.name] lets out [npc.a_moan+] as [npc.she] tells you to stop fingering [npc.herHim].",
							"[npc.Name] leans into you, causing you to inhale [npc.her] "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+" before [npc.she] slides your [pc.fingers] out of [npc.her] [npc.pussy+]."));
					break;
			}
			
			switch(Sex.getSexPacePlayer()) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a relieved sigh, which soon turns into [pc.a_sob+] as you realise that [npc.she] isn't finished with you yet.",
							" With [pc.a_sob+], you continue to protest and struggle against [npc.herHim] as [npc.she] holds you firmly in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.she] stops you from stimulating [npc.her] [npc.pussy+].",
							" [pc.A_moan+] escapes from between your [pc.lips+], betraying your desire to give [npc.her] [npc.pussy+] more of your attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
