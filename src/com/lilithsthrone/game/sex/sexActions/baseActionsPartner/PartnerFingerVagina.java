package com.lilithsthrone.game.sex.sexActions.baseActionsPartner;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
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
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.1.79
 * @author Innoxia
 */
public class PartnerFingerVagina {
	
	public static final SexAction PARTNER_STROKE_PUSSY = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.PITCHER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Stroke pussy";
		}

		@Override
		public String getActionDescription() {
			return "Reach down between [pc.name]'s [pc.legs] and start stroking [pc.her] [pc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getActivePartner())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			if(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.VAGINA)){

				UtilText.nodeContentSB.setLength(0);
				
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between your [pc.legs], [npc.name] traces [npc.her] [npc.fingers+] over your [pc.topClothing(pussy)],"
										+ " gently pushing down and letting out a soft [npc.moan] as [npc.she] rubs your [pc.lowClothing(pussy)] against your [pc.pussy+].",
								"With a soft [npc.moan], [npc.name] reaches down between your [pc.legs], gently pushing [npc.her] [npc.fingers] down as [npc.she] rubs your [pc.lowClothing(pussy)] against your [pc.pussy+].",
								"Slowly teasing [npc.her] [npc.fingers+] between your [pc.legs], [npc.name] gently presses down, rubbing your [pc.lowClothing(pussy)] against your [pc.pussy+] as [npc.she] lets out a soft [npc.moan]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between your [pc.legs], [npc.name] traces [npc.her] [npc.fingers+] over your [pc.topClothing(pussy)],"
										+ " eagerly pushing down and letting out [npc.a_moan+] as [npc.she] rubs your [pc.lowClothing(pussy)] against your [pc.pussy+].",
								"With [npc.a_moan+], [npc.name] reaches down between your [pc.legs], eagerly pushing [npc.her] [npc.fingers] down as [npc.she] rubs your [pc.lowClothing(pussy)] against your [pc.pussy+].",
								"Eagerly teasing [npc.her] [npc.fingers+] between your [pc.legs], [npc.name] starts to press down, rubbing your [pc.lowClothing(pussy)] against your [pc.pussy+] as [npc.she] lets out [npc.a_moan+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between your [pc.legs], [npc.name] roughly presses [npc.her] [npc.fingers+] against your [pc.topClothing(pussy)],"
										+ " forcefully pushing down and letting out [npc.a_moan+] as [npc.she] rapidly starts rubbing your [pc.lowClothing(pussy)] against your [pc.pussy+].",
								"With [npc.a_moan+], [npc.name] reaches down between your [pc.legs], roughly pushing [npc.her] [npc.fingers] down as [npc.she] rapidly starts rubbing your [pc.lowClothing(pussy)] against your [pc.pussy+].",
								"Roughly teasing [npc.her] [npc.fingers+] between your [pc.legs], [npc.name] starts to press down, rapidly rubbing your [pc.lowClothing(pussy)] against your [pc.pussy+] as [npc.she] lets out [npc.a_moan+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between your [pc.legs], [npc.name] traces [npc.her] [npc.fingers+] over your [pc.topClothing(pussy)],"
										+ " eagerly pushing down and letting out [npc.a_moan+] as [npc.she] rubs your [pc.lowClothing(pussy)] against your [pc.pussy+].",
								"With [npc.a_moan+], [npc.name] reaches down between your [pc.legs], eagerly pushing [npc.her] [npc.fingers] down as [npc.she] rubs your [pc.lowClothing(pussy)] against your [pc.pussy+].",
								"Eagerly teasing [npc.her] [npc.fingers+] between your [pc.legs], [npc.name] starts to press down, rubbing your [pc.lowClothing(pussy)] against your [pc.pussy+] as [npc.she] lets out [npc.a_moan+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between your [pc.legs], [npc.name] traces [npc.her] [npc.fingers+] over your [pc.topClothing(pussy)],"
										+ " pushing down and letting out [npc.a_moan+] as [npc.she] rubs your [pc.lowClothing(pussy)] against your [pc.pussy+].",
								"With [npc.a_moan+], [npc.name] reaches down between your [pc.legs], pushing [npc.her] [npc.fingers] down as [npc.she] rubs your [pc.lowClothing(pussy)] against your [pc.pussy+].",
								"Teasing [npc.her] [npc.fingers+] between your [pc.legs], [npc.name] presses down, rubbing your [pc.lowClothing(pussy)] against your [pc.pussy+] as [npc.she] lets out [npc.a_moan+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" A soft [pc.moan] drifts out from between your [pc.lips+], and with a gentle bucking of your [pc.hips], you softly encourage [npc.herHim] to continue stroking your [pc.pussy+].",
								" You start gently bucking your [pc.hips] in response, softly pressing yourself against [npc.her] [npc.fingers] as you plead for [npc.herHim] to continue.",
								" You gently start thrusting your [pc.hips] back against [npc.her] [npc.fingers], softly [pc.moaning] as you enjoy the feeling of [npc.her] touch on your [pc.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [pc.A_moan+] drifts out from between your [pc.lips+], and with an eager bucking of your [pc.hips], you desperately encourage [npc.herHim] to continue stroking your [pc.pussy+].",
								" You start eagerly bucking your [pc.hips] in response, desperately pressing yourself against [npc.her] [npc.fingers] as you plead for [npc.herHim] to continue.",
								" You eagerly start thrusting your [pc.hips] back against [npc.her] [npc.fingers], [pc.moaning+] as you enjoy the feeling of [npc.her] touch on your [pc.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [pc.A_moan+] drifts out from between your [pc.lips+], and with a violent buck of your [pc.hips], you roughly order [npc.herHim] to continue stroking your [pc.pussy+].",
								" You start frantically bucking your [pc.hips] in response, roughly pressing yourself against [npc.her] [npc.fingers] as you demand that [npc.she] continues.",
								" You forcefully start thrusting your [pc.hips] back against [npc.her] [npc.fingers], [pc.moaning+] as you enjoy the feeling of roughly grinding your [pc.pussy+] against [npc.herHim]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [pc.A_moan+] drifts out from between your [pc.lips+], and with an eager bucking of your [pc.hips], you desperately encourage [npc.herHim] to continue stroking your [pc.pussy+].",
								" You start eagerly bucking your [pc.hips] in response, desperately pressing yourself against [npc.her] [npc.fingers] as you plead for [npc.herHim] to continue.",
								" You eagerly start thrusting your [pc.hips] back against [npc.her] [npc.fingers], [pc.moaning+] as you enjoy the feeling of [npc.her] touch on your [pc.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [pc.A_moan+] drifts out from between your [pc.lips+], and, bucking your [pc.hips] against [npc.her] [npc.hand], you encourage [npc.herHim] to continue stroking your [pc.pussy+].",
								" You start bucking your [pc.hips] in response, pressing yourself against [npc.her] [npc.fingers] as you plead for [npc.herHim] to continue.",
								" You start thrusting your [pc.hips] back against [npc.her] [npc.fingers], [pc.moaning+] as you enjoy the feeling of [npc.her] touch on your [pc.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [pc.A_sob+] bursts out from between your [pc.lips+], and, pulling your [pc.hips] away from [npc.her] touch, you plead with [npc.name] to stop touching you.",
								" You pull your [pc.hips] away in response, letting out [pc.a_sob+] as you plead for [npc.herHim] to stop.",
								" You try to pull your [pc.hips] away from [npc.her] [npc.fingers], [pc.sobbing+] as you beg for [npc.herHim] to leave you alone."));
						break;
					default:
						break;
				}
		
				return UtilText.nodeContentSB.toString();
				
			}else{
				
				UtilText.nodeContentSB.setLength(0);
				
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between your [pc.legs], [npc.name] traces [npc.her] [npc.fingers+] over your [pc.pussy+], letting out a soft [npc.moan] as [npc.she] starts gently stroking your outer labia.",
								"With a soft [npc.moan], [npc.name] reaches down between your [pc.legs], gently pushing [npc.her] [npc.fingers] against your outer labia as [npc.she] starts stroking your [pc.pussy+].",
								"Slowly teasing [npc.her] [npc.fingers+] between your [pc.legs], [npc.name] gently presses down, letting out a soft [npc.moan] as [npc.she] starts stroking your [pc.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between your [pc.legs], [npc.name] eagerly traces [npc.her] [npc.fingers+] over your [pc.pussy+], letting out [npc.a_moan+] as [npc.she] starts greedily stroking your outer labia.",
								"With [npc.a_moan+], [npc.name] reaches down between your [pc.legs], eagerly pressing [npc.her] [npc.fingers] against your outer labia as [npc.she] starts stroking your [pc.pussy+].",
								"Teasing [npc.her] [npc.fingers+] between your [pc.legs], [npc.name] eagerly presses down, letting out [npc.a_moan+] as [npc.she] starts happily stroking your [pc.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between your [pc.legs], [npc.name] roughly grinds [npc.her] [npc.fingers+] over your [pc.pussy+], letting out [npc.a_moan+] as [npc.she] starts rapidly stroking your outer labia.",
								"With [npc.a_moan+], [npc.name] reaches down between your [pc.legs], greedily pressing [npc.her] [npc.fingers] against your outer labia as [npc.she] starts roughly stroking your [pc.pussy+].",
								"Roughly pushing [npc.her] [npc.fingers+] between your [pc.legs], [npc.name] roughly presses down, letting out [npc.a_moan+] as [npc.she] starts greedily fondling your [pc.pussy+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between your [pc.legs], [npc.name] eagerly traces [npc.her] [npc.fingers+] over your [pc.pussy+], letting out [npc.a_moan+] as [npc.she] starts greedily stroking your outer labia.",
								"With [npc.a_moan+], [npc.name] reaches down between your [pc.legs], eagerly pressing [npc.her] [npc.fingers] against your outer labia as [npc.she] starts stroking your [pc.pussy+].",
								"Teasing [npc.her] [npc.fingers+] between your [pc.legs], [npc.name] eagerly presses down, letting out [npc.a_moan+] as [npc.she] starts happily stroking your [pc.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between your [pc.legs], [npc.name] traces [npc.her] [npc.fingers+] over your [pc.pussy+], letting out [npc.a_moan+] as [npc.she] starts stroking your outer labia.",
								"With [npc.a_moan+], [npc.name] reaches down between your [pc.legs], pressing [npc.her] [npc.fingers] against your outer labia as [npc.she] starts stroking your [pc.pussy+].",
								"Teasing [npc.her] [npc.fingers+] between your [pc.legs], [npc.name] presses down, letting out [npc.a_moan+] as [npc.she] starts stroking your [pc.pussy+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" A soft [pc.moan] drifts out from between your [pc.lips+], and with a gentle bucking of your [pc.hips], you softly encourage [npc.herHim] to continue stroking your [pc.pussy+].",
								" You start gently bucking your [pc.hips] in response, softly pressing yourself against [npc.her] [npc.fingers] as you plead for [npc.herHim] to continue.",
								" You gently start thrusting your [pc.hips] back against [npc.her] [npc.fingers], softly [pc.moaning] as you enjoy the feeling of [npc.her] touch on your [pc.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [pc.A_moan+] drifts out from between your [pc.lips+], and with an eager bucking of your [pc.hips], you desperately encourage [npc.herHim] to continue stroking your [pc.pussy+].",
								" You start eagerly bucking your [pc.hips] in response, desperately pressing yourself against [npc.her] [npc.fingers] as you plead for [npc.herHim] to continue.",
								" You eagerly start thrusting your [pc.hips] back against [npc.her] [npc.fingers], [pc.moaning+] as you enjoy the feeling of [npc.her] touch on your [pc.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [pc.A_moan+] drifts out from between your [pc.lips+], and with a violent buck of your [pc.hips], you roughly order [npc.herHim] to continue stroking your [pc.pussy+].",
								" You start frantically bucking your [pc.hips] in response, roughly pressing yourself against [npc.her] [npc.fingers] as you demand that [npc.she] continues.",
								" You forcefully start thrusting your [pc.hips] back against [npc.her] [npc.fingers], [pc.moaning+] as you enjoy the feeling of roughly grinding your [pc.pussy+] against [npc.herHim]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [pc.A_moan+] drifts out from between your [pc.lips+], and with an eager bucking of your [pc.hips], you desperately encourage [npc.herHim] to continue stroking your [pc.pussy+].",
								" You start eagerly bucking your [pc.hips] in response, desperately pressing yourself against [npc.her] [npc.fingers] as you plead for [npc.herHim] to continue.",
								" You eagerly start thrusting your [pc.hips] back against [npc.her] [npc.fingers], [pc.moaning+] as you enjoy the feeling of [npc.her] touch on your [pc.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [pc.A_moan+] drifts out from between your [pc.lips+], and, bucking your [pc.hips] against [npc.her] [npc.hand], you encourage [npc.herHim] to continue stroking your [pc.pussy+].",
								" You start bucking your [pc.hips] in response, pressing yourself against [npc.her] [npc.fingers] as you plead for [npc.herHim] to continue.",
								" You start thrusting your [pc.hips] back against [npc.her] [npc.fingers], [pc.moaning+] as you enjoy the feeling of [npc.her] touch on your [pc.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [pc.A_sob+] bursts out from between your [pc.lips+], and, pulling your [pc.hips] away from [npc.her] touch, you plead with [npc.name] to stop touching you.",
								" You pull your [pc.hips] away in response, letting out [pc.a_sob+] as you plead for [npc.herHim] to stop.",
								" You try to pull your [pc.hips] away from [npc.her] [npc.fingers], [pc.sobbing+] as you beg for [npc.herHim] to leave you alone."));
						break;
					default:
						break;
				}
				
				return UtilText.nodeContentSB.toString();
				
			}
		}

		@Override
		public void applyEffects() {
			if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.VAGINA)) {
				Sex.transferLubrication(Sex.getActivePartner(), SexAreaPenetration.FINGER, Main.game.getPlayer(), SexAreaOrifice.VAGINA);
			}
		}
	};
	
	public static final SexAction PARTNER_CLIT_PLAY = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.PITCHER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Tease clit";
		}

		@Override
		public String getActionDescription() {
			return "Reach down to [pc.name]'s [pc.pussy+] and start playing with [pc.her] [pc.clit].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getActivePartner())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly sliding [npc.her] [npc.fingers+] up the length of your [pc.pussy+], [npc.name] focuses [npc.her] attention on your [pc.clit+], gently squeezing and rubbing it as [npc.she] lets out a little [npc.moan].",
							"Gently tracing [npc.her] [npc.fingers+] over your [pc.pussy+], [npc.name] lets out a little [npc.moan] as [npc.she] homes in on your [pc.clit+], before starting to softly rub and pinch it.",
							"Teasing [npc.her] [npc.fingers] over your [pc.pussy+], a little [npc.moan] escapes from between [npc.her] [npc.lips] as [npc.she] starts to gently rub and pinch at your [pc.clit+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Greedily sliding [npc.her] [npc.fingers+] up the length of your [pc.pussy+], [npc.name] focuses [npc.her] attention on your [pc.clit+], eagerly squeezing and rubbing it as [npc.she] lets out [npc.a_moan+].",
							"Firmly tracing [npc.her] [npc.fingers+] over your [pc.pussy+], [npc.name] lets out [npc.a_moan+] as [npc.she] homes in on your [pc.clit+], before starting to eagerly rub and pinch it.",
							"Teasing [npc.her] [npc.fingers] over your [pc.pussy+], [npc.a_moan+] escapes from between [npc.her] [npc.lips] as [npc.she] starts to eagerly rub and pinch at your [pc.clit+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding [npc.her] [npc.fingers+] up the length of your [pc.pussy+], [npc.name] focuses [npc.her] attention on your [pc.clit+], cruelly squeezing and rubbing it as [npc.she] lets out [npc.a_moan+].",
							"Greedily tracing [npc.her] [npc.fingers+] over your [pc.pussy+], [npc.name] lets out [npc.a_moan+] as [npc.she] homes in on your [pc.clit+], before starting to roughly rub and pinch it.",
							"Grinding [npc.her] [npc.fingers] over your [pc.pussy+], [npc.a_moan+] escapes from between [npc.her] [npc.lips] as [npc.she] starts to roughly rub and pinch at your [pc.clit+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Greedily sliding [npc.her] [npc.fingers+] up the length of your [pc.pussy+], [npc.name] focuses [npc.her] attention on your [pc.clit+], eagerly squeezing and rubbing it as [npc.she] lets out [npc.a_moan+].",
							"Firmly tracing [npc.her] [npc.fingers+] over your [pc.pussy+], [npc.name] lets out [npc.a_moan+] as [npc.she] homes in on your [pc.clit+], before starting to eagerly rub and pinch it.",
							"Teasing [npc.her] [npc.fingers] over your [pc.pussy+], [npc.a_moan+] escapes from between [npc.her] [npc.lips] as [npc.she] starts to eagerly rub and pinch at your [pc.clit+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.fingers+] up the length of your [pc.pussy+], [npc.name] focuses [npc.her] attention on your [pc.clit+], squeezing and rubbing it as [npc.she] lets out [npc.a_moan+].",
							"Tracing [npc.her] [npc.fingers+] over your [pc.pussy+], [npc.name] lets out [npc.a_moan+] as [npc.she] homes in on your [pc.clit+], before starting to rub and pinch it.",
							"Teasing [npc.her] [npc.fingers] over your [pc.pussy+], [npc.a_moan+] escapes from between [npc.her] [npc.lips] as [npc.she] starts to rub and pinch at your [pc.clit+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" A soft [pc.moan] drifts out from between your [pc.lips+], and with a gentle bucking of your [pc.hips], you softly encourage [npc.name] to continue playing with your [pc.clit+].",
							" You start gently bucking your [pc.hips] in response, softly pressing your [pc.clit+] against [npc.name]'s [npc.fingers] as you plead for [npc.herHim] to continue.",
							" You gently start thrusting your [pc.hips] back against [npc.name]'s [npc.fingers], softly [pc.moaning] as you focus on the pleasure in your [pc.clit+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] bursts out from your mouth as you eagerly push your [pc.hips] back against [npc.name]'s touch.",
							" You eagerly push your [pc.hips] against [npc.name]'s [npc.hand], [pc.moaning] in delight as you enthusiastically encourage [npc.herHim] to continue playing with your [pc.clit+].",
							" You eagerly push your [pc.hips] out against [npc.name]'s [npc.fingers], [pc.moaning] and arching your back as you desperately press your [pc.clit+] down against [npc.her] touch."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] bursts out from your mouth as you roughly push your [pc.hips] back against [npc.name]'s touch.",
							" You roughly grind your [pc.hips] against [npc.name]'s [npc.hand], [pc.moaning] in delight as you order [npc.herHim] to continue playing with your [pc.clit+].",
							" You forcefully thrust your [pc.hips] out against [npc.name]'s [npc.fingers], [pc.moaning] and arching your back as you roughly grind your [pc.clit+] down against [npc.her] touch."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] bursts out from your mouth as you eagerly push your [pc.hips] back against [npc.name]'s touch.",
							" You eagerly push your [pc.hips] against [npc.name]'s [npc.hand], [pc.moaning] in delight as you enthusiastically encourage [npc.herHim] to continue playing with your [pc.clit+].",
							" You eagerly push your [pc.hips] out against [npc.name]'s [npc.fingers], [pc.moaning] and arching your back as you desperately press your [pc.clit+] down against [npc.her] touch."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] bursts out from your mouth as you push your [pc.hips] back against [npc.name]'s touch.",
							" You push your [pc.hips] against [npc.name]'s [npc.hand], [pc.moaning] in delight as you encourage [npc.herHim] to continue playing with your [pc.clit+].",
							" You push your [pc.hips] out against [npc.name]'s [npc.fingers], [pc.moaning] and arching your back as you press your [pc.clit+] down against [npc.her] touch."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_sob+] bursts out from your mouth as you try to pull your [pc.pussy] away from [npc.name]'s touch, pleading with [npc.herHim] to stop as you struggle against [npc.herHim].",
							" You pull your [pc.hips] back in response, letting out [pc.a_sob+] as you plead for [npc.herHim] to stop touching you.",
							" You try to pull your [pc.pussy] away from [npc.name]'s [npc.fingers], [pc.sobbing+] as you beg for [npc.herHim] to leave you alone."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_CLIT_FOCUS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.PITCHER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Clit focus";
		}

		@Override
		public String getActionDescription() {
			return "Focus on pleasuring [pc.name]'s [pc.clit+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getActivePartner())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly sinking [npc.her] [npc.fingers+] deep into your [pc.pussy+], [npc.name] lifts [npc.her] thumb up to your [pc.clit],"
									+ " gently pressing down and rubbing against it as [npc.she] continues curling [npc.her] digits up inside of you.",
							"Gently sliding [npc.her] [npc.fingers+] into your [pc.pussy+], [npc.name] presses [npc.her] thumb down against your [pc.clit+], softly rubbing against it as [npc.she] lets out a little [npc.moan].",
							"Gently pushing [npc.her] [npc.fingers] deep into your [pc.pussy+],"
									+ " a little [npc.moan] escapes from between [npc.name]'s [npc.lips] as [npc.she] lifts [npc.her] thumb to start gently rubbing and pressing down on your [pc.clit+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly sinking [npc.her] [npc.fingers+] deep into your [pc.pussy+], [npc.name] lifts [npc.her] thumb up to your [pc.clit],"
									+ " greedily pressing down and rubbing against it as [npc.she] continues curling [npc.her] digits up inside of you.",
							"Greedily sliding [npc.her] [npc.fingers+] into your [pc.pussy+], [npc.name] presses [npc.her] thumb down against your [pc.clit+], eagerly rubbing against it as [npc.she] lets out [npc.a_moan+].",
							"Pushing [npc.her] [npc.fingers] deep into your [pc.pussy+],"
									+ " [npc.a_moan+] escapes from between [npc.name]'s [npc.lips] as [npc.she] lifts [npc.her] thumb to start eagerly rubbing and pressing down on your [pc.clit+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly digging [npc.her] [npc.fingers+] deep into your [pc.pussy+], [npc.name] lifts [npc.her] thumb up to your [pc.clit],"
									+ " forcefully pressing down and grinding against it as [npc.she] continues dominantly curling [npc.her] digits up inside of you.",
							"Forcing [npc.her] [npc.fingers+] deep into your [pc.pussy+], [npc.name] grinds [npc.her] thumb down against your [pc.clit+], roughly rubbing against it as [npc.she] lets out [npc.a_moan+].",
							"Slamming [npc.her] [npc.fingers] deep into your [pc.pussy+],"
									+ " [npc.a_moan+] escapes from between [npc.name]'s [npc.lips] as [npc.she] lifts [npc.her] thumb to start roughly rubbing and grinding down on your [pc.clit+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly sinking [npc.her] [npc.fingers+] deep into your [pc.pussy+], [npc.name] lifts [npc.her] thumb up to your [pc.clit],"
									+ " greedily pressing down and rubbing against it as [npc.she] continues curling [npc.her] digits up inside of you.",
							"Greedily sliding [npc.her] [npc.fingers+] into your [pc.pussy+], [npc.name] presses [npc.her] thumb down against your [pc.clit+], eagerly rubbing against it as [npc.she] lets out [npc.a_moan+].",
							"Pushing [npc.her] [npc.fingers] deep into your [pc.pussy+],"
									+ " [npc.a_moan+] escapes from between [npc.name]'s [npc.lips] as [npc.she] lifts [npc.her] thumb to start eagerly rubbing and pressing down on your [pc.clit+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sinking [npc.her] [npc.fingers+] deep into your [pc.pussy+], [npc.name] lifts [npc.her] thumb up to your [pc.clit],"
									+ " pressing down and rubbing against it as [npc.she] continues curling [npc.her] digits up inside of you.",
							"Sliding [npc.her] [npc.fingers+] into your [pc.pussy+], [npc.name] presses [npc.her] thumb down against your [pc.clit+], rubbing against it as [npc.she] lets out [npc.a_moan+].",
							"Pushing [npc.her] [npc.fingers] deep into your [pc.pussy+], [npc.a_moan+] escapes from between [npc.name]'s [npc.lips] as [npc.she] lifts [npc.her] thumb to start rubbing and pressing down on your [pc.clit+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" A soft [pc.moan] drifts out from between your [pc.lips+], and with a gentle bucking of your [pc.hips], you softly encourage [npc.name] to continue playing with your [pc.clit+].",
							" You start gently bucking your [pc.hips] in response, softly pressing your [pc.clit+] against [npc.name]'s [npc.fingers] as you plead for [npc.herHim] to continue.",
							" You gently start thrusting your [pc.hips] back against [npc.name]'s [npc.fingers], softly [pc.moaning] as you focus on the pleasure in your [pc.clit+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] bursts out from your mouth as you eagerly push your [pc.hips] back against [npc.name]'s touch.",
							" You eagerly push your [pc.hips] against [npc.name]'s [npc.hand], [pc.moaning] in delight as you enthusiastically encourage [npc.herHim] to continue playing with your [pc.clit+].",
							" You eagerly push your [pc.hips] out against [npc.name]'s [npc.fingers], [pc.moaning] and arching your back as you desperately press your [pc.clit+] down against [npc.her] touch."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] bursts out from your mouth as you roughly push your [pc.hips] back against [npc.name]'s touch.",
							" You roughly grind your [pc.hips] against [npc.name]'s [npc.hand], [pc.moaning] in delight as you order [npc.herHim] to continue playing with your [pc.clit+].",
							" You forcefully thrust your [pc.hips] out against [npc.name]'s [npc.fingers], [pc.moaning] and arching your back as you roughly grind your [pc.clit+] down against [npc.her] touch."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] bursts out from your mouth as you eagerly push your [pc.hips] back against [npc.name]'s touch.",
							" You eagerly push your [pc.hips] against [npc.name]'s [npc.hand], [pc.moaning] in delight as you enthusiastically encourage [npc.herHim] to continue playing with your [pc.clit+].",
							" You eagerly push your [pc.hips] out against [npc.name]'s [npc.fingers], [pc.moaning] and arching your back as you desperately press your [pc.clit+] down against [npc.her] touch."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] bursts out from your mouth as you push your [pc.hips] back against [npc.name]'s touch.",
							" You push your [pc.hips] against [npc.name]'s [npc.hand], [pc.moaning] in delight as you encourage [npc.herHim] to continue playing with your [pc.clit+].",
							" You push your [pc.hips] out against [npc.name]'s [npc.fingers], [pc.moaning] and arching your back as you press your [pc.clit+] down against [npc.her] touch."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_sob+] bursts out from your mouth as you try to pull your [pc.pussy] away from [npc.name]'s touch, pleading with [npc.herHim] to stop as you struggle against [npc.herHim].",
							" You pull your [pc.hips] back in response, letting out [pc.a_sob+] as you plead for [npc.herHim] to stop touching you.",
							" You try to pull your [pc.pussy] away from [npc.name]'s [npc.fingers], [pc.sobbing+] as you beg for [npc.herHim] to leave you alone."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FINGERING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.PITCHER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Finger [pc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Sink your [npc.fingers] into [pc.name]'s [pc.pussy+] and start fingering [pc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly teasing [npc.her] [npc.fingers+] over your outer labia, [npc.name] lets out a little [npc.moan] before slowly sinking [npc.her] digits into your [pc.pussy+].",
							"[npc.Name] presses [npc.her] [npc.fingers+] down between your [pc.legs+], and with a slow, steady pressure, [npc.she] gently sinks [npc.her] digits into your [pc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing [npc.her] [npc.fingers+] over your outer labia, [npc.name] lets out [npc.a_moan+] before greedily sinking [npc.her] digits into your [pc.pussy+].",
							"[npc.Name] eagerly presses [npc.her] [npc.fingers+] down between your [pc.legs+], and with a determined thrust, [npc.she] greedily sinks [npc.her] digits into your [pc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding [npc.her] [npc.fingers+] over your outer labia, [npc.name] lets out [npc.a_moan+] before violently slamming [npc.her] digits deep into your [pc.pussy+].",
							"[npc.Name] roughly grinds [npc.her] [npc.fingers+] down between your [pc.legs+], and with a forceful thrust, [npc.she] greedily slams [npc.her] digits deep into your [pc.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing [npc.her] [npc.fingers+] over your outer labia, [npc.name] lets out [npc.a_moan+] before greedily sinking [npc.her] digits into your [pc.pussy+].",
							"[npc.Name] eagerly presses [npc.her] [npc.fingers+] down between your [pc.legs+], and with a determined thrust, [npc.she] greedily sinks [npc.her] digits into your [pc.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing [npc.her] [npc.fingers+] over your outer labia, [npc.name] lets out [npc.a_moan+] before sinking [npc.her] digits into your [pc.pussy+].",
							"[npc.Name] presses [npc.her] [npc.fingers+] down between your [pc.legs+], and with a little push, [npc.she] sinks [npc.her] digits into your [pc.pussy+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soft [pc.moan] as [npc.her] [npc.fingers] enter you, gently bucking your [pc.hips] against [npc.her] [npc.hand] as you help to sink [npc.her] [npc.fingers] even deeper into your [pc.pussy+].",
							" With a soft [pc.moan], you start gently bucking your [pc.hips] into [npc.her] [npc.hand], encouraging [npc.herHim] to sink [npc.her] [npc.fingers] even deeper into your [pc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.her] [npc.fingers] enter you,"
									+ " eagerly bucking your [pc.hips] against [npc.her] [npc.hand] as you enthusiastically help to sink [npc.her] [npc.fingers] even deeper into your [pc.pussy+].",
							" With [pc.a_moan+], you start eagerly bucking your [pc.hips] into [npc.her] [npc.hand], desperately encouraging [npc.herHim] to sink [npc.her] [npc.fingers] even deeper into your [pc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.her] [npc.fingers] enter you, violently bucking your [pc.hips] against [npc.her] [npc.hand] as you roughly force [npc.her] [npc.fingers] even deeper into your [pc.pussy+].",
							" With [pc.a_moan+], you start violently bucking your [pc.hips] into [npc.her] [npc.hand], roughly forcing [npc.herHim] to sink [npc.her] [npc.fingers] even deeper into your [pc.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.her] [npc.fingers] enter you,"
									+ " eagerly bucking your [pc.hips] against [npc.her] [npc.hand] as you enthusiastically help to sink [npc.her] [npc.fingers] even deeper into your [pc.pussy+].",
							" With [pc.a_moan+], you start eagerly bucking your [pc.hips] into [npc.her] [npc.hand], desperately encouraging [npc.herHim] to sink [npc.her] [npc.fingers] even deeper into your [pc.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.her] [npc.fingers] enter you, bucking your [pc.hips] against [npc.her] [npc.hand] as you help to sink [npc.her] [npc.fingers] even deeper into your [pc.pussy+].",
							" With [pc.a_moan+], you start bucking your [pc.hips] into [npc.her] [npc.hand], encouraging [npc.herHim] to sink [npc.her] [npc.fingers] even deeper into your [pc.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_sob+] as you feel [npc.her] [npc.fingers] enter you, and try, in vain, to pull your [pc.hips] back from [npc.her] unwanted penetration, struggling and [pc.sobbing] all the while.",
							" With [pc.a_sob+], you try, in vain, to pull away from the unwanted penetration, [pc.sobbing] and struggling against [npc.name] as [npc.her] unwelcome [npc.fingers] push deep into your [pc.pussy+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_FINGERING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.PITCHER,
			null,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Gentle fingering";
		}

		@Override
		public String getActionDescription() {
			return "Gently finger [pc.name]'s [pc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sinking [npc.her] [npc.fingers+] into your [pc.pussy+], [npc.name] curls [npc.her] digits up, softly stroking your vaginal walls as [npc.she] starts slowly fingering you.",
					"Slowly pushing [npc.her] [npc.hand] into your groin, [npc.name] gently slides [npc.her] [npc.fingers+] deep into your [pc.pussy+], letting out a little [npc.moan] as [npc.she] starts softly fingering you.",
					"Pressing [npc.her] [npc.hand] down between your [pc.legs], [npc.name] lets out a little [npc.moan] before gently sliding [npc.her] [npc.fingers+] deep into your [pc.pussy+]."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You eagerly thrust your [pc.hips] out in response, letting out a delighted [pc.moan] as you start enthusiastically imploring [npc.herHim] to continue fingering you.",
							" A delighted [pc.moan] bursts out from between your [pc.lips+], and, eagerly thrusting your [pc.hips] out against [npc.her] touch, you beg for [npc.name] to continue fingering you.",
							" [pc.Moaning] in delight, you eagerly grind down against [npc.name]'s [npc.hand], eagerly begging for [npc.herHim] to continue as your movements drive [npc.her] [npc.fingers] ever deeper into your [pc.pussy+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to recoil your [pc.pussy] away from [npc.her] unwanted touch, you let out [pc.a_sob+] before weakly trying to struggle away from [npc.name]'s intruding [npc.fingers].",
							" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, squirming and protesting as [npc.she] continues to gently finger your [pc.pussy+].",
							" [pc.Sobbing] in distress, you try, in vain, to recoil away from [npc.name]'s touch, struggling against [npc.herHim] as [npc.her] [npc.fingers+] continue gently sliding deep into your [pc.pussy+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You thrust your [pc.hips] out in response, letting out [pc.a_moan+] as you beg for [npc.name] to continue fingering you.",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, thrusting your [pc.hips] out against [npc.her] touch, you beg for [npc.name] to continue fingering you.",
							" [pc.Moaning+], you grind down against [npc.name]'s [npc.hand], begging for [npc.herHim] to continue as your movements drive [npc.her] [npc.fingers] ever deeper into your [pc.pussy+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_FINGERING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.PITCHER,
			null,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Continue fingering";
		}

		@Override
		public String getActionDescription() {
			return "Continue fingering [pc.name]'s [pc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly sinking [npc.her] [npc.fingers+] deep into your [pc.pussy+], [npc.name] curls [npc.her] digits up, stroking your vaginal walls as [npc.she] starts passionately fingering you.",
					"Firmly pushing [npc.her] [npc.hand] into your groin, [npc.name] eagerly slides [npc.her] [npc.fingers+] deep into your [pc.pussy+], letting out [npc.a_moan+] as [npc.she] starts rapidly fingering you.",
					"Eagerly pressing [npc.her] [npc.hand] down between your [pc.legs], [npc.name] lets out [npc.a_moan+] before enthusiastically sliding [npc.her] [npc.fingers+] deep into your [pc.pussy+]."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You eagerly thrust your [pc.hips] out in response, letting out a delighted [pc.moan] as you start enthusiastically imploring [npc.herHim] to continue fingering you.",
							" A delighted [pc.moan] bursts out from between your [pc.lips+], and, eagerly thrusting your [pc.hips] out against [npc.her] touch, you beg for [npc.name] to continue fingering you.",
							" [pc.Moaning] in delight, you eagerly grind down against [npc.name]'s [npc.hand], eagerly begging for [npc.herHim] to continue as your movements drive [npc.her] [npc.fingers] ever deeper into your [pc.pussy+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to recoil your [pc.pussy] away from [npc.her] unwanted touch, you let out [pc.a_sob+] before weakly trying to struggle away from [npc.name]'s intruding [npc.fingers].",
							" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, squirming and protesting as [npc.she] continues to gently finger your [pc.pussy+].",
							" [pc.Sobbing] in distress, you try, in vain, to recoil away from [npc.name]'s touch, struggling against [npc.herHim] as [npc.her] [npc.fingers+] continue gently sliding deep into your [pc.pussy+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You thrust your [pc.hips] out in response, letting out [pc.a_moan+] as you beg for [npc.name] to continue fingering you.",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, thrusting your [pc.hips] out against [npc.her] touch, you beg for [npc.name] to continue fingering you.",
							" [pc.Moaning+], you grind down against [npc.name]'s [npc.hand], begging for [npc.herHim] to continue as your movements drive [npc.her] [npc.fingers] ever deeper into your [pc.pussy+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_FINGERING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.PITCHER,
			null,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Rough fingering";
		}

		@Override
		public String getActionDescription() {
			return "Roughly finger [pc.name]'s [pc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}


		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Greedily thrusting [npc.her] [npc.fingers+] deep into your [pc.pussy+], [npc.name] curls [npc.her] digits up, roughly stroking your vaginal walls as [npc.she] starts ruthlessly fingering you.",
					"Roughly slamming [npc.her] [npc.hand] into your groin, [npc.name] greedily pumps [npc.her] [npc.fingers+] deep into your [pc.pussy+], letting out [npc.a_moan+] as [npc.she] starts violently fingering you.",
					"Forcefully pressing [npc.her] [npc.hand] down between your [pc.legs], [npc.name] lets out [npc.a_moan+] before roughly slamming [npc.her] [npc.fingers+] deep into your [pc.pussy+]."));

			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You eagerly thrust your [pc.hips] out in response, letting out a delighted [pc.moan] as you start enthusiastically imploring [npc.herHim] to continue fingering you.",
							" A delighted [pc.moan] bursts out from between your [pc.lips+], and, eagerly thrusting your [pc.hips] out against [npc.her] touch, you beg for [npc.name] to continue fingering you.",
							" [pc.Moaning] in delight, you eagerly grind down against [npc.name]'s [npc.hand], eagerly begging for [npc.herHim] to continue as your movements drive [npc.her] [npc.fingers] ever deeper into your [pc.pussy+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to recoil your [pc.pussy] away from [npc.her] unwanted touch, you let out [pc.a_sob+] before weakly trying to struggle away from [npc.name]'s intruding [npc.fingers].",
							" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, squirming and protesting as [npc.she] continues to gently finger your [pc.pussy+].",
							" [pc.Sobbing] in distress, you try, in vain, to recoil away from [npc.name]'s touch, struggling against [npc.herHim] as [npc.her] [npc.fingers+] continue gently sliding deep into your [pc.pussy+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You thrust your [pc.hips] out in response, letting out [pc.a_moan+] as you beg for [npc.name] to continue fingering you.",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, thrusting your [pc.hips] out against [npc.her] touch, you beg for [npc.name] to continue fingering you.",
							" [pc.Moaning+], you grind down against [npc.name]'s [npc.hand], begging for [npc.herHim] to continue as your movements drive [npc.her] [npc.fingers] ever deeper into your [pc.pussy+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FINGERING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.PITCHER,
			null,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Continue fingering";
		}

		@Override
		public String getActionDescription() {
			return "Continue fingering [pc.name]'s [pc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking [npc.her] [npc.fingers+] into your [pc.pussy+], [npc.name] curls [npc.her] digits up, stroking your vaginal walls as [npc.she] focuses [npc.her] efforts on fingering you.",
					"Pushing [npc.her] [npc.hand] into your groin, [npc.name] slides [npc.her] [npc.fingers+] deep into your [pc.pussy+], letting out [npc.a_moan+] as [npc.she] starts focusing [npc.her] efforts on fingering you.",
					"Pressing [npc.her] [npc.hand] down between your [pc.legs], [npc.name] lets out [npc.a_moan+] before sliding [npc.her] [npc.fingers+] deep into your [pc.pussy+]."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" You slowly thrust your [pc.hips] out in response, letting out a soft [pc.moan] as you start gently imploring [npc.name] to continue fingering you.",
						" A soft [pc.moan] drifts out from between your [pc.lips+], and, gently pushing your [pc.hips] out against [npc.her] [npc.hand], you beg for [npc.name] to continue fingering you.",
						" [pc.Moaning] in delight, you slowly grind down against [npc.name]'s [npc.hand],"
								+ " softly [pc.moaning] for [npc.herHim] to continue as your movements cause [npc.her] [npc.fingers] to slide ever deeper into your [pc.pussy+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You violently thrust your [pc.hips] up in response, letting out [pc.a_moan+] as you roughly order [npc.name] to continue fingering you.",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, roughly grinding your [pc.hips] against [npc.name]'s [npc.hand], you dominantly command [npc.herHim] to continue fingering you.",
							" You roughly grind down against [npc.name]'s [npc.hand], ordering [npc.herHim] to continue as your forceful movements cause [npc.her] [npc.fingers] to sink ever deeper into your [pc.pussy+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You thrust your [pc.hips] out in response, letting out [pc.a_moan+] as you beg for [npc.name] to continue fingering you.",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, pushing your [pc.hips] out against [npc.name]'s [npc.hand], you beg for [npc.herHim] to continue fingering you.",
							" You grind down against [npc.name]'s [npc.hand], [pc.moaning] for [npc.herHim] to continue as your movements cause [npc.her] [npc.fingers] to slide ever deeper into your [pc.pussy+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_FINGERING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.PITCHER,
			null,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Eager fingering";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly finger [pc.name]'s [pc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly sinking [npc.her] [npc.fingers+] deep into your [pc.pussy+], [npc.name] curls [npc.her] digits up, stroking your vaginal walls as [npc.she] starts passionately fingering you.",
					"Firmly pushing [npc.her] [npc.hand] into your groin, [npc.name] eagerly slides [npc.her] [npc.fingers+] deep into your [pc.pussy+], letting out [npc.a_moan+] as [npc.she] starts rapidly fingering you.",
					"Eagerly pressing [npc.her] [npc.hand] down between your [pc.legs], [npc.name] lets out [npc.a_moan+] before enthusiastically sliding [npc.her] [npc.fingers+] deep into your [pc.pussy+]."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" You slowly thrust your [pc.hips] out in response, letting out a soft [pc.moan] as you start gently imploring [npc.name] to continue fingering you.",
						" A soft [pc.moan] drifts out from between your [pc.lips+], and, gently pushing your [pc.hips] out against [npc.her] [npc.hand], you beg for [npc.name] to continue fingering you.",
						" [pc.Moaning] in delight, you slowly grind down against [npc.name]'s [npc.hand],"
								+ " softly [pc.moaning] for [npc.herHim] to continue as your movements cause [npc.her] [npc.fingers] to slide ever deeper into your [pc.pussy+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You violently thrust your [pc.hips] up in response, letting out [pc.a_moan+] as you roughly order [npc.name] to continue fingering you.",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, roughly grinding your [pc.hips] against [npc.name]'s [npc.hand], you dominantly command [npc.herHim] to continue fingering you.",
							" You roughly grind down against [npc.name]'s [npc.hand], ordering [npc.herHim] to continue as your forceful movements cause [npc.her] [npc.fingers] to sink ever deeper into your [pc.pussy+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You thrust your [pc.hips] out in response, letting out [pc.a_moan+] as you beg for [npc.name] to continue fingering you.",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, pushing your [pc.hips] out against [npc.name]'s [npc.hand], you beg for [npc.herHim] to continue fingering you.",
							" You grind down against [npc.name]'s [npc.hand], [pc.moaning] for [npc.herHim] to continue as your movements cause [npc.her] [npc.fingers] to slide ever deeper into your [pc.pussy+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_FINGERING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.PITCHER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Stop fingering";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.fingers] out of [pc.name]'s [pc.pussy+] and stop fingering [pc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.fingers] out of your [pc.pussy+], [npc.name] gives your [pc.clit+] a cruel squeeze before taking [npc.her] [npc.hand] away from your groin.",
							"Thrusting deep inside of you one last time, [npc.name] then yanks [npc.her] [npc.fingers] back out of your [pc.pussy+], putting an end to [npc.her] rough fingering."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.fingers] out of your  [pc.pussy+], [npc.name] gives your [pc.clit+] a little squeeze before taking [npc.her] [npc.hand] away from your groin.",
							"Pushing deep inside of you one last time, [npc.name] then slides [npc.her] [npc.fingers] back out of your [pc.pussy+], putting an end to [npc.her] fingering."));
					break;
			}
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You gasp as [npc.name] withdraws from your [pc.pussy], before letting out [pc.a_sob+] as you continue to struggle against [npc.herHim].",
							" With [pc.a_sob+], you continue to struggle against [npc.name] as [npc.she] withdraws from your [pc.pussy+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.name] pulls [npc.her] [npc.fingers+] out of your [pc.pussy+].",
							" [pc.A_moan+] escapes from between your [pc.lips+], betraying your desire for more of [npc.name]'s attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Partner actions:
	
	public static final SexAction PLAYER_FINGERING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.CATCHER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || Sex.isDom(Main.game.getPlayer()); // Player can only start fingering in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Get fingered";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to start fingering your [pc.pussy+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking a gentle, but firm, grip on [npc.her] [npc.hand], you slowly guide [npc.name]'s [npc.fingers] over your outer labia, letting out a little [pc.moan] before pushing [npc.her] digits into your [pc.pussy+].",
							"Taking hold of [npc.name]'s [npc.hand], you guide [npc.her] [npc.fingers] down between your [pc.legs+], and with a slow, steady pressure, you gently push [npc.her] digits into your [pc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a firm grip on [npc.her] [npc.hand], you eagerly guide [npc.name]'s [npc.fingers] over your outer labia, letting out [pc.a_moan+] before greedily pushing [npc.her] digits into your [pc.pussy+].",
							"Taking hold of [npc.name]'s [npc.hand], you eagerly guide [npc.her] [npc.fingers] down between your [pc.legs+], and with a determined pressure, you greedily push [npc.her] digits into your [pc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a vice-like grip on [npc.her] [npc.hand], you grind [npc.name]'s [npc.fingers] over your outer labia, letting out [pc.a_moan+] before roughly forcing [npc.her] digits into your [pc.pussy+].",
							"Grabbing [npc.name]'s [npc.hand], you forcefully push [npc.her] [npc.fingers] down between your [pc.legs+], and with a dominant, jerking motion, you roughly stuff [npc.her] digits into your [pc.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a firm grip on [npc.her] [npc.hand], you eagerly guide [npc.name]'s [npc.fingers] over your outer labia, letting out [pc.a_moan+] before greedily pushing [npc.her] digits into your [pc.pussy+].",
							"Taking hold of [npc.name]'s [npc.hand], you eagerly guide [npc.her] [npc.fingers] down between your [pc.legs+], and with a determined pressure, you greedily push [npc.her] digits into your [pc.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a firm grip on [npc.her] [npc.hand], you guide [npc.name]'s [npc.fingers] over your outer labia, letting out [pc.a_moan+] before pushing [npc.her] digits into your [pc.pussy+].",
							"Taking hold of [npc.name]'s [npc.hand], you guide [npc.her] [npc.fingers] down between your [pc.legs+], and with a determined pressure, you push [npc.her] digits into your [pc.pussy+]."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a soft [npc.moan] as [npc.she] enters you, curling [npc.her] [npc.fingers] up before gently starting to finger your [pc.pussy+].",
							" With a soft [npc.moan], [npc.she] curls [npc.her] [npc.fingers+] up inside of you, gently stroking your vaginal walls as [npc.she] sets about fingering your [pc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as [npc.she] enters you, curling [npc.her] [npc.fingers] up before eagerly starting to finger your [pc.pussy+].",
							" With [npc.a_moan+], [npc.she] curls [npc.her] [npc.fingers+] up inside of you, eagerly stroking your vaginal walls as [npc.she] sets about fingering your [pc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as [npc.she] enters you, and, seeking to remind you who's in charge, [npc.she] roughly curls [npc.her] [npc.fingers] up before starting to ruthlessly finger-fuck your [pc.pussy+].",
							" With [npc.a_moan+], [npc.she] curls [npc.her] [npc.fingers+] up inside of you, seeking to remind you who's in charge as [npc.she] starts roughly fingering your [pc.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as [npc.she] enters you, curling [npc.her] [npc.fingers] up before eagerly starting to finger your [pc.pussy+].",
							" With [npc.a_moan+], [npc.she] curls [npc.her] [npc.fingers+] up inside of you, eagerly stroking your vaginal walls as [npc.she] sets about fingering your [pc.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as [npc.she] enters you, curling [npc.her] [npc.fingers] up before starting to finger your [pc.pussy+].",
							" With [npc.a_moan+], [npc.she] curls [npc.her] [npc.fingers+] up inside of you, stroking your vaginal walls as [npc.she] sets about fingering your [pc.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_sob+] as you force [npc.her] [npc.fingers] inside of you, struggling against your firm grip on [npc.her] [npc.hand] as [npc.she] tries to pull [npc.herself] free.",
							" With [npc.a_sob+], [npc.name] starts struggling against your tight grip on [npc.her] [npc.hand], pleading for you to stop as you force [npc.her] [npc.fingers] deep into your [pc.pussy+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_FINGERED_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.CATCHER,
			SexPace.DOM_GENTLE,
			null) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Gently fingered";
		}

		@Override
		public String getActionDescription() {
			return "Gently enjoy [npc.name]'s [npc.fingers+] in your [pc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently pushing your [pc.hips] out against [npc.name]'s [npc.hand], you let out a soft [pc.moan] as you help to sink [npc.her] [npc.fingers+] deep into your [pc.pussy+].",
					"With a soft [pc.moan], you gently start gyrating your [pc.hips] against [npc.name]'s [npc.hand], forcing [npc.her] [npc.fingers+] ever deeper into your [pc.pussy+].",
					"Slowly thrusting your [pc.hips] against [npc.name]'s [npc.hand], a soft [pc.moan] drifts out from between your [pc.lips+] as your movements force [npc.her] [npc.fingers+] deep into your [pc.pussy+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_FINGERED_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.CATCHER,
			SexPace.DOM_NORMAL,
			null) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Enjoy fingering";
		}

		@Override
		public String getActionDescription() {
			return "Enjoy [npc.name]'s [npc.fingers+] in your [pc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing your [pc.hips] out against [npc.name]'s [npc.hand], you let out [pc.a_moan+] as you energetically help to sink [npc.her] [npc.fingers+] deep into your [pc.pussy+].",
					"With [pc.a_moan+], you energetically start gyrating your [pc.hips] against [npc.name]'s [npc.hand], forcing [npc.her] [npc.fingers+] ever deeper into your [pc.pussy+].",
					"Enthusiastically thrusting your [pc.hips] against [npc.name]'s [npc.hand], [pc.a_moan+] bursts out from between your [pc.lips+] as your movements force [npc.her] [npc.fingers+] deep into your [pc.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_FINGERED_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.CATCHER,
			SexPace.DOM_ROUGH,
			null) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Roughly fingered";
		}

		@Override
		public String getActionDescription() {
			return "Roughly force [npc.name]'s [npc.fingers+] deep inside your [pc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Violently slamming your [pc.hips] out against [npc.name]'s [npc.hand], you let out [pc.a_moan+] as you roughly force [npc.her] [npc.fingers+] deep into your [pc.pussy+].",
					"With [pc.a_moan+], you aggressively start gyrating your [pc.hips] against [npc.name]'s [npc.hand], forcing [npc.her] [npc.fingers+] ever deeper into your [pc.pussy+].",
					"Roughly thrusting your [pc.hips] against [npc.name]'s [npc.hand], [pc.a_moan+] bursts out from between your [pc.lips+] as your forceful movements drive [npc.her] [npc.fingers+] deep into your [pc.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_FINGERED_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.CATCHER,
			SexPace.SUB_NORMAL,
			null) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Enjoy fingering";
		}

		@Override
		public String getActionDescription() {
			return "Enjoy [npc.name]'s [npc.fingers+] in your [pc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing your [pc.hips] out against [npc.name]'s [npc.hand], you let out [pc.a_moan+] as you energetically help to sink [npc.her] [npc.fingers+] deep into your [pc.pussy+].",
					"With [pc.a_moan+], you energetically start gyrating your [pc.hips] against [npc.name]'s [npc.hand], forcing [npc.her] [npc.fingers+] ever deeper into your [pc.pussy+].",
					"Enthusiastically thrusting your [pc.hips] against [npc.name]'s [npc.hand], [pc.a_moan+] bursts out from between your [pc.lips+] as your movements force [npc.her] [npc.fingers+] deep into your [pc.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_FINGERED_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.CATCHER,
			SexPace.SUB_EAGER,
			null) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Eagerly fingered";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly buck your hips against [npc.name]'s [npc.hand] as [npc.she] fingers your [pc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing your [pc.hips] out against [npc.name]'s [npc.hand], you let out [pc.a_moan+] as you energetically help to sink [npc.her] [npc.fingers+] deep into your [pc.pussy+].",
					"With [pc.a_moan+], you energetically start gyrating your [pc.hips] against [npc.name]'s [npc.hand], forcing [npc.her] [npc.fingers+] ever deeper into your [pc.pussy+].",
					"Enthusiastically thrusting your [pc.hips] against [npc.name]'s [npc.hand], [pc.a_moan+] bursts out from between your [pc.lips+] as your movements force [npc.her] [npc.fingers+] deep into your [pc.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_FINGERING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.CATCHER,
			SexPace.SUB_RESISTING,
			null) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Resist fingering";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull [npc.name]'s [npc.fingers] out of your [pc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You feel tears starting to well up in your [pc.eyes], and, unable to keep it in any longer, [pc.a_sob+] bursts out from your mouth as you weakly try to pull [npc.name]'s [npc.fingers] out of your [pc.pussy+].",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.hips] back from [npc.name]'s unwanted touch,"
									+ " struggling in desperation as [npc.her] [npc.fingers+] continue gently sliding in and out of your [pc.pussy+].",
							"Trying desperately to pull your [pc.hips] away from [npc.name]'s [npc.hand], you [pc.sob] in distress as [npc.her] [npc.fingers+] continue gently sliding deep into your [pc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You feel tears starting to well up in your [pc.eyes], and, unable to keep it in any longer, [pc.a_sob+] bursts out from your mouth as you weakly try to pull [npc.name]'s greedy [npc.fingers] out of your [pc.pussy+].",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.hips] back from [npc.name]'s unwanted touch,"
									+ " struggling in desperation as [npc.her] [npc.fingers+] continue eagerly sliding in and out of your [pc.pussy+].",
							"Trying desperately to pull your [pc.hips] away from [npc.name]'s [npc.hand], you [pc.sob] in distress as [npc.her] [npc.fingers+] continue eagerly pumping deep into your [pc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You feel tears starting to well up in your [pc.eyes], and, unable to keep it in any longer, [pc.a_sob+] bursts out from your mouth as you weakly try to pull [npc.name]'s rough [npc.fingers] out of your [pc.pussy+].",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.hips] back from [npc.name]'s unwanted touch,"
									+ " struggling in desperation as [npc.her] [npc.fingers+] continue roughly slamming in and out of your [pc.pussy+].",
							"Trying desperately to pull your [pc.hips] away from [npc.name]'s [npc.hand], you [pc.sob] in distress as [npc.her] [npc.fingers+] continue roughly slamming deep into your [pc.pussy+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_FINGERING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.CATCHER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || Sex.isDom(Main.game.getPlayer()); // Player can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop getting fingered";
		}

		@Override
		public String getActionDescription() {
			return "Force [npc.name] to pull [npc.her] [npc.fingers] out of your [pc.pussy+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc.her] [npc.fingers] out of your [pc.pussy+], you growl at [npc.name] as you command [npc.herHim] to stop fingering you.",
							"You lean into [npc.name], inhaling [npc.her] "+(Sex.getActivePartner().isFeminine()?"feminine scent":"masculine musk")+" as you yank [npc.her] [npc.fingers] out of your [pc.pussy+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.name]'s [npc.fingers] out of your [pc.pussy+], you let out [pc.a_moan+] before telling [npc.herHim] to stop fingering you.",
							"You lean into [npc.name], inhaling [npc.her] "+(Sex.getActivePartner().isFeminine()?"feminine scent":"masculine musk")+" as you slide [npc.her] [npc.fingers] out of your [pc.pussy+]."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out a relieved sigh, which soon turns into [npc.a_sob+] as [npc.she] realises that you aren't finished with [npc.herHim] yet.",
							" With [npc.a_sob+], [npc.name] continues to protest and struggle against you as you hold [npc.herHim] firmly in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as you stop [npc.herHim] from stimulating your [pc.pussy+].",
							" [npc.A_moan+] escapes from between [npc.name]'s [npc.lips+], betraying [npc.her] desire to give your [pc.pussy+] more of [npc.her] attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
