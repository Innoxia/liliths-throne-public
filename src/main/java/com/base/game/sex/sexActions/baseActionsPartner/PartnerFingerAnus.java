package com.base.game.sex.sexActions.baseActionsPartner;

import java.util.List;

import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.effects.Fetish;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.sex.ArousalIncrease;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.Sex;
import com.base.game.sex.SexPace;
import com.base.game.sex.sexActions.SexAction;
import com.base.game.sex.sexActions.SexActionType;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;

/**
 * @since 0.1.79
 * @version 0.1.79
 * @author Innoxia
 */
public class PartnerFingerAnus {
	
	public static SexAction PARTNER_ANAL_FINGERING_START = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.ANUS_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Finger [pc.her] ass";
		}

		@Override
		public String getActionDescription() {
			return "Sink your [npc.fingers] into [pc.name]'s [pc.asshole+] and start fingering [pc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly teasing [npc.her] [npc.fingers+] over your [pc.ass], [npc.name] lets out a little [npc.moan] before slowly sinking [npc.her] digits into your [pc.asshole+].",
							"[npc.Name] presses [npc.her] [npc.fingers+] down between your ass cheeks, and with a slow, steady pressure, [npc.she] gently sinks [npc.her] digits into your [pc.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing [npc.her] [npc.fingers+] over your [pc.ass], [npc.name] lets out [npc.a_moan+] before greedily sinking [npc.her] digits into your [pc.asshole+].",
							"[npc.Name] eagerly presses [npc.her] [npc.fingers+] down between your ass cheeks, and with a determined thrust, [npc.she] greedily sinks [npc.her] digits into your [pc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding [npc.her] [npc.fingers+] over your [pc.ass], [npc.name] lets out [npc.a_moan+] before violently slamming [npc.her] digits deep into your [pc.asshole+].",
							"[npc.Name] roughly grinds [npc.her] [npc.fingers+] down between your ass cheeks, and with a forceful thrust, [npc.she] greedily slams [npc.her] digits deep into your [pc.asshole+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing [npc.her] [npc.fingers+] over your [pc.ass], [npc.name] lets out [npc.a_moan+] before greedily sinking [npc.her] digits into your [pc.asshole+].",
							"[npc.Name] eagerly presses [npc.her] [npc.fingers+] down between your ass cheeks, and with a determined thrust, [npc.she] greedily sinks [npc.her] digits into your [pc.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing [npc.her] [npc.fingers+] over your [pc.ass], [npc.name] lets out [npc.a_moan+] before sinking [npc.her] digits into your [pc.asshole+].",
							"[npc.Name] presses [npc.her] [npc.fingers+] down between your ass cheeks, and with a little push, [npc.she] sinks [npc.her] digits into your [pc.asshole+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soft [pc.moan] as [npc.her] [npc.fingers] enter you, gently bucking your [pc.ass] back against [npc.her] [npc.hand] as you help to sink [npc.her] [npc.fingers] even deeper into your [pc.asshole+].",
							" With a soft [pc.moan], you start gently bucking your [pc.ass] back against [npc.her] [npc.hand], encouraging [npc.herHim] to sink [npc.her] [npc.fingers] even deeper into your [npc.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.her] [npc.fingers] enter you,"
									+ " eagerly bucking your [pc.ass] back against [npc.her] [npc.hand] as you enthusiastically help to sink [npc.her] [npc.fingers] even deeper into your [pc.asshole+].",
							" With [pc.a_moan+], you start eagerly bucking your [pc.ass] back against [npc.her] [npc.hand], desperately encouraging [npc.herHim] to sink [npc.her] [npc.fingers] even deeper into your [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.her] [npc.fingers] enter you, violently bucking your [pc.ass] back against [npc.her] [npc.hand] as you roughly force [npc.her] [npc.fingers] even deeper into your [pc.asshole+].",
							" With [pc.a_moan+], you start violently bucking your [pc.ass] back against [npc.her] [npc.hand], roughly forcing [npc.herHim] to sink [npc.her] [npc.fingers] even deeper into your [npc.asshole+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.her] [npc.fingers] enter you,"
									+ " eagerly bucking your [pc.ass] back against [npc.her] [npc.hand] as you enthusiastically help to sink [npc.her] [npc.fingers] even deeper into your [pc.asshole+].",
							" With [pc.a_moan+], you start eagerly bucking your [pc.ass] back against [npc.her] [npc.hand], desperately encouraging [npc.herHim] to sink [npc.her] [npc.fingers] even deeper into your [npc.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.her] [npc.fingers] enter you, bucking your [pc.ass] back against [npc.her] [npc.hand] as you help to sink [npc.her] [npc.fingers] even deeper into your [pc.asshole+].",
							" With [pc.a_moan+], you start bucking your [pc.ass] back against [npc.her] [npc.hand], encouraging [npc.herHim] to sink [npc.her] [npc.fingers] even deeper into your [npc.asshole+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_sob+] as you feel [npc.her] [npc.fingers] enter you, and you try, in vain, to pull your [npc.ass] away from [npc.her] unwanted penetration, struggling and [pc.sobbing] all the while.",
							" With [pc.a_sob+], you try, in vain, to pull away from the unwanted penetration, [pc.sobbing] and struggling against [npc.name] as [npc.her] unwelcome [npc.fingers] push deep into your [pc.asshole+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction PARTNER_ANAL_FINGERING_DOM_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.ANUS_PLAYER,
			null,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Gentle anal fingering";
		}

		@Override
		public String getActionDescription() {
			return "Gently finger [pc.name]'s [pc.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently sinking [npc.her] [npc.fingers+] into your [pc.asshole+], [npc.name] starts slowly sliding [npc.her] digits in and out,"
									+ " grinning as you let out a delighted [pc.moan] and eagerly thrust your [pc.ass] back against [npc.her] [npc.hand].",
							"Slowly pushing [npc.her] [npc.fingers+] into your [pc.asshole+], [npc.name] draws a delighted [pc.moan] from between your [pc.lips+],"
									+ " and, eagerly thrusting your [pc.ass] back against [npc.her] touch, you beg for [npc.herHim] to continue fingering you.",
							"Gently sliding [npc.her] [npc.fingers+] into your [pc.asshole+], [npc.name]'s touch causes you to let out [pc.a_moan+], and, desperate for more, you eagerly grind your [pc.ass] back against [npc.her] [npc.hand]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] gently sinks [npc.her] [pc.fingers+] into your [pc.asshole+], causing you to desperately try to recoil away from [npc.her] touch,"
									+ " letting out [pc.a_sob+] as you weakly attempt to struggle away from [npc.her] intruding [npc.fingers].",
							"[npc.Name] slowly pushes [npc.her] [npc.fingers+] into your [pc.asshole+], causing [pc.a_sob+] to burst out from between your [pc.lips],"
									+ " and you start squirming and protesting as [npc.she] continues to gently finger your [pc.ass+].",
							"Gently sliding [npc.her] [npc.fingers+] into your [pc.asshole+], [npc.name]'s unwanted attention causes you to start [pc.sobbing] in distress,"
									+ " struggling weakly against [npc.herHim] as [npc.her] [npc.fingers+] continue gently sliding deep into your [pc.ass+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently sinking [npc.her] [npc.fingers+] into your [pc.asshole+], [npc.name] starts slowly sliding [npc.her] digits in and out,"
									+ " grinning as you let out [pc.a_moan] and thrust your [pc.ass] back against [npc.her] [npc.hand].",
							"Slowly pushing [npc.her] [npc.fingers+] into your [pc.asshole+], [npc.name] draws [pc.a_moan] from between your [pc.lips+],"
									+ " and, thrusting your [pc.ass] back against [npc.her] touch, you beg for [npc.herHim] to continue fingering you.",
							"Gently sliding [npc.her] [npc.fingers+] into your [pc.asshole+], [npc.name]'s touch causes you to let out [pc.a_moan], and, wanting more, you grind your [pc.ass] back against [npc.her] [npc.hand]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction PARTNER_ANAL_FINGERING_DOM_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.ANUS_PLAYER,
			null,
			SexPace.DOM_NORMAL) {
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
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly sinking [npc.her] [npc.fingers+] deep into your [pc.asshole+], [npc.name] starts enthusiastically sliding [npc.her] digits in and out,"
									+ " grinning as you let out a delighted [pc.moan] and eagerly thrust your [pc.ass] back against [npc.her] [npc.hand].",
							"Firmly pushing [npc.her] [npc.fingers+] deep into your [pc.asshole+], [npc.name] draws a delighted [pc.moan] from between your [pc.lips+],"
									+ " and, eagerly thrusting your [pc.ass] back against [npc.her] touch, you beg for [npc.herHim] to continue fingering you.",
							"Eagerly sliding [npc.her] [npc.fingers+] deep into your [pc.asshole+], [npc.name]'s touch causes you to let out [pc.a_moan+],"
									+ " and, desperate for more, you happily grind your [pc.ass] back against [npc.her] [npc.hand]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] eagerly sinks [npc.her] [pc.fingers+] deep into your [pc.asshole+], causing you to desperately try to recoil away from [npc.her] touch,"
									+ " letting out [pc.a_sob+] as you weakly attempt to struggle away from [npc.her] intruding [npc.fingers].",
							"[npc.Name] greedily pushes [npc.her] [npc.fingers+] deep into your [pc.asshole+], causing [pc.a_sob+] to burst out from between your [pc.lips],"
									+ " and you start squirming and protesting as [npc.she] continues to eagerly finger your [pc.ass+].",
							"Eagerly sliding [npc.her] [npc.fingers+] into your [pc.asshole+], [npc.name]'s unwanted attention causes you to start [pc.sobbing] in distress,"
									+ " struggling weakly against [npc.herHim] as [npc.her] [npc.fingers+] continue frantically pumping deep into your [pc.ass+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly sinking [npc.her] [npc.fingers+] into your [pc.asshole+], [npc.name] starts enthusiastically sliding [npc.her] digits in and out,"
									+ " grinning as you let out [pc.a_moan] and thrust your [pc.ass] back against [npc.her] [npc.hand].",
							"Firmly pushing [npc.her] [npc.fingers+] into your [pc.asshole+], [npc.name] draws [pc.a_moan] from between your [pc.lips+],"
									+ " and, thrusting your [pc.ass] back against [npc.her] touch, you beg for [npc.herHim] to continue fingering you.",
							"Eagerly sliding [npc.her] [npc.fingers+] into your [pc.asshole+], [npc.name]'s touch causes you to let out [pc.a_moan], and, wanting more, you grind your [pc.ass] back against [npc.her] [npc.hand]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction PARTNER_ANAL_FINGERING_DOM_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.ANUS_PLAYER,
			null,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Rough anal fingering";
		}

		@Override
		public String getActionDescription() {
			return "Roughly finger [pc.name]'s [pc.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}


		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Greedily thrusting [npc.her] [npc.fingers+] deep into your [pc.asshole+], [npc.name] starts roughly slamming [npc.her] digits in and out,"
									+ " grinning as you let out a delighted [pc.moan] and eagerly thrust your [pc.ass] back against [npc.her] [npc.hand].",
							"Roughly slamming [npc.her] [npc.fingers+] deep into your [pc.asshole+], [npc.name] draws a delighted [pc.moan] from between your [pc.lips+],"
									+ " and, eagerly thrusting your [pc.ass] back against [npc.her] touch, you beg for [npc.herHim] to continue fingering you.",
							"Forcefully slamming [npc.her] [npc.fingers+] deep into your [pc.asshole+], [npc.name]'s rough touch causes you to let out [pc.a_moan+],"
									+ " and, desperate for more, you happily grind your [pc.ass] back against [npc.her] [npc.hand]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] roughly slams [npc.her] [pc.fingers+] deep into your [pc.asshole+], causing you to desperately try to recoil away from [npc.her] touch,"
									+ " and, letting out [pc.a_sob+], you weakly attempt to struggle away from [npc.her] intruding [npc.fingers].",
							"[npc.Name] forcefully rams [npc.her] [npc.fingers+] deep into your [pc.asshole+], causing [pc.a_sob+] to burst out from between your [pc.lips],"
									+ " and you start squirming and protesting as [npc.she] continues to roughly finger-fuck your [pc.ass+].",
							"Forcefully slamming [npc.her] [npc.fingers+] deep into your [pc.asshole+], [npc.name]'s unwanted attention causes you to start [pc.sobbing] in distress,"
									+ " struggling weakly against [npc.herHim] as [npc.her] [npc.fingers+] continue roughly pumping deep into your [pc.ass+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Greedily thrusting [npc.her] [npc.fingers+] deep into your [pc.asshole+], [npc.name] starts roughly slamming [npc.her] digits in and out,"
									+ " grinning as you let out [pc.a_moan] and thrust your [pc.ass] back against [npc.her] [npc.hand].",
							"Roughly slamming [npc.her] [npc.fingers+] deep into your [pc.asshole+], [npc.name] draws [pc.a_moan] from between your [pc.lips+],"
									+ " and, thrusting your [pc.ass] back against [npc.her] touch, you beg for [npc.herHim] to continue fingering you.",
							"Forcefully slamming [npc.her] [npc.fingers+] deep into your [pc.asshole+], [npc.name]'s rough touch causes you to let out [pc.a_moan], and, wanting more, you grind your [pc.ass] back against [npc.her] [npc.hand]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION), new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_MASOCHIST));
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION), new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_SADIST));
		}
	};
	
	public static SexAction PARTNER_ANAL_FINGERING_SUB_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.ANUS_PLAYER,
			null,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Anal fingering";
		}

		@Override
		public String getActionDescription() {
			return "Continue fingering [pc.name]'s [pc.asshole+].";
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
							"Sinking [npc.her] [npc.fingers+] into your [pc.asshole+], [npc.name] starts sliding [npc.her] digits in and out,"
									+ " drawing a soft [pc.moan] from between your [pc.lips+] as you gently thrust your [pc.ass] back against [npc.her] [npc.hand].",
							"Pushing [npc.her] [npc.fingers+] into your [pc.asshole+], [npc.name] draws a soft [pc.moan] from between your [pc.lips+],"
									+ " and, gently thrusting your [pc.ass] back against [npc.her] touch, you beg for [npc.herHim] to continue fingering you.",
							"Sliding [npc.her] [npc.fingers+] into your [pc.asshole+], [npc.name]'s touch causes you to let out a gentle [pc.moan],"
									+ " and, hungry for more, you slowly push your [pc.ass] back against [npc.her] [npc.hand]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sinking [npc.her] [npc.fingers+] into your [pc.asshole+], [npc.name] starts sliding [npc.her] digits in and out,"
									+ " drawing [pc.a_moan+] from between your [pc.lips+] as you roughly slam your [pc.ass] back against [npc.her] [npc.hand].",
							"Pushing [npc.her] [npc.fingers+] into your [pc.asshole+], [npc.name] draws [pc.a_moan+] from between your [pc.lips+],"
									+ " and, roughly slamming your [pc.ass] back against [npc.her] touch, you command [npc.herHim] to continue fingering you.",
							"Sliding [npc.her] [npc.fingers+] into your [pc.asshole+], [npc.name]'s touch causes you to let out [pc.a_moan+],"
									+ " and, hungry for more, you roughly grind your [pc.ass] back against [npc.her] [npc.hand]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sinking [npc.her] [npc.fingers+] into your [pc.asshole+], [npc.name] starts sliding [npc.her] digits in and out,"
									+ " drawing [pc.a_moan+] from between your [pc.lips+] as you eagerly grind your [pc.ass] back against [npc.her] [npc.hand].",
							"Pushing [npc.her] [npc.fingers+] into your [pc.asshole+], [npc.name] draws [pc.a_moan+] from between your [pc.lips+],"
									+ " and, eagerly grinding your [pc.ass] back against [npc.her] touch, you beg for [npc.herHim] to continue fingering you.",
							"Sliding [npc.her] [npc.fingers+] into your [pc.asshole+], [npc.name]'s touch causes you to let out [pc.a_moan+],"
									+ " and, hungry for more, you eagerly push your [pc.ass] back against [npc.her] [npc.hand]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction PARTNER_ANAL_FINGERING_SUB_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.ANUS_PLAYER,
			null,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Eager anal fingering";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly finger [pc.name]'s [pc.asshole+].";
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
							"Eagerly sinking [npc.her] [npc.fingers+] deep into your [pc.asshole+], [npc.name] starts rapidly sliding [npc.her] digits in and out,"
									+ " drawing a soft [pc.moan] from between your [pc.lips+] as you gently thrust your [pc.ass] back against [npc.her] [npc.hand].",
							"Firmly pushing [npc.her] [npc.fingers+] deep into your [pc.asshole+], [npc.name] draws a soft [pc.moan] from between your [pc.lips+],"
									+ " and, gently thrusting your [pc.ass] back against [npc.her] touch, you beg for [npc.herHim] to continue fingering you.",
							"Eagerly sliding [npc.her] [npc.fingers+] deep into your [pc.asshole+], [npc.name]'s enthusiastic touch causes you to let out a gentle [pc.moan],"
									+ " and, hungry for more, you slowly push your [pc.ass] back against [npc.her] [npc.hand]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly sinking [npc.her] [npc.fingers+] deep into your [pc.asshole+], [npc.name] starts sliding [npc.her] digits in and out,"
									+ " drawing [pc.a_moan+] from between your [pc.lips+] as you roughly slam your [pc.ass] back against [npc.her] [npc.hand].",
							"Firmly pushing [npc.her] [npc.fingers+] deep into your [pc.asshole+], [npc.name] draws [pc.a_moan+] from between your [pc.lips+],"
									+ " and, roughly slamming your [pc.ass] back against [npc.her] touch, you command [npc.herHim] to continue fingering you.",
							"Eagerly sliding [npc.her] [npc.fingers+] deep into your [pc.asshole+], [npc.name]'s enthusiastic touch causes you to let out [pc.a_moan+],"
									+ " and, hungry for more, you roughly grind your [pc.ass] back against [npc.her] [npc.hand]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sinking [npc.her] [npc.fingers+] deep into your [pc.asshole+], [npc.name] starts sliding [npc.her] digits in and out,"
									+ " drawing [pc.a_moan+] from between your [pc.lips+] as you eagerly grind your [pc.ass] back against [npc.her] [npc.hand].",
							"Firmly pushing [npc.her] [npc.fingers+] deep into your [pc.asshole+], [npc.name] draws [pc.a_moan+] from between your [pc.lips+],"
									+ " and, eagerly grinding your [pc.ass] back against [npc.her] touch, you beg for [npc.herHim] to continue fingering you.",
							"Eagerly sliding [npc.her] [npc.fingers+] deep into your [pc.asshole+], [npc.name]'s enthusiastic touch causes you to let out [pc.a_moan+],"
									+ " and, hungry for more, you eagerly push your [pc.ass] back against [npc.her] [npc.hand]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION), new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction PARTNER_ANAL_FINGERING_STOP = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PARTNER,
			OrificeType.ANUS_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Stop anal fingering";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.fingers] out of [pc.name]'s [pc.asshole+] and stop fingering [pc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.fingers] out of your [pc.asshole+], [npc.name] quickly takes [npc.her] [npc.hand] away from your [pc.ass].",
							"Thrusting deep inside of you one last time, [npc.name] then yanks [npc.her] [npc.fingers] back out of your [pc.asshole+], putting an end to [npc.her] rough fingering."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.fingers] out of your  [pc.asshole+], [npc.name] quickly takes [npc.her] [npc.hand] away from your [pc.ass].",
							"Pushing deep inside of you one last time, [npc.name] then slides [npc.her] [npc.fingers] back out of your [pc.asshole+], putting an end to [npc.her] fingering."));
					break;
			}
			
			switch(Sex.getSexPacePlayer()) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You gasp as [npc.name] withdraws from your [pc.asshole], before letting out [pc.a_sob+] as you continue to struggle against [npc.herHim].",
							" With [pc.a_sob+], you continue to struggle against [npc.name] as [npc.she] withdraws from your [pc.asshole+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.name] pulls [npc.her] [npc.fingers+] out of your [pc.asshole+].",
							" [pc.A_moan+] escapes from between your [pc.lips+], betraying your desire for more of [npc.name]'s attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Partner actions:
	
	public static SexAction PLAYER_ANAL_FINGERING_START = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.ANUS_PLAYER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager().isConsensualSex() || Sex.isPlayerDom(); // Player can only start fingering in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Get anally fingered";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to start fingering your [pc.ass+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking a gentle, but firm, grip on [npc.her] [npc.hand], you slowly guide [npc.name]'s [npc.fingers] over your [pc.ass], letting out a little [pc.moan] before pushing [npc.her] digits into your [pc.asshole+].",
							"Taking hold of [npc.name]'s [npc.hand], you guide [npc.her] [npc.fingers] between your ass cheeks, and with a slow, steady pressure, you gently push [npc.her] digits into your [pc.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a firm grip on [npc.her] [npc.hand], you eagerly guide [npc.name]'s [npc.fingers] over your [pc.ass], letting out [pc.a_moan+] before greedily pushing [npc.her] digits into your [pc.asshole+].",
							"Taking hold of [npc.name]'s [npc.hand], you eagerly guide [npc.her] [npc.fingers] between your ass cheeks, and with a determined pressure, you greedily push [npc.her] digits into your [pc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a vice-like grip on [npc.her] [npc.hand], you grind [npc.name]'s [npc.fingers] over your [pc.ass], letting out [pc.a_moan+] before roughly forcing [npc.her] digits into your [pc.asshole+].",
							"Grabbing [npc.name]'s [npc.hand], you forcefully push [npc.her] [npc.fingers] between your ass cheeks, and with a dominant, jerking motion, you roughly stuff [npc.her] digits into your [pc.asshole+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a firm grip on [npc.her] [npc.hand], you eagerly guide [npc.name]'s [npc.fingers] over your [pc.ass], letting out [pc.a_moan+] before greedily pushing [npc.her] digits into your [pc.asshole+].",
							"Taking hold of [npc.name]'s [npc.hand], you eagerly guide [npc.her] [npc.fingers] between your ass cheeks, and with a determined pressure, you greedily push [npc.her] digits into your [pc.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a firm grip on [npc.her] [npc.hand], you guide [npc.name]'s [npc.fingers] over your [pc.ass], letting out [pc.a_moan+] before pushing [npc.her] digits into your [pc.asshole+].",
							"Taking hold of [npc.name]'s [npc.hand], you guide [npc.her] [npc.fingers] between your ass cheeks, and with a determined pressure, you push [npc.her] digits into your [pc.asshole+]."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a soft [npc.moan] as [npc.she] enters you, curling [npc.her] [npc.fingers] up before gently starting to finger your [pc.asshole+].",
							" With a soft [npc.moan], [npc.she] curls [npc.her] [npc.fingers+] up inside of you, gently pushing [npc.her] [npc.hand] into your [pc.ass] as [npc.she] sets about fingering your [pc.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as [npc.she] enters you, curling [npc.her] [npc.fingers] up before eagerly starting to finger your [pc.asshole+].",
							" With [npc.a_moan+], [npc.she] curls [npc.her] [npc.fingers+] up inside of you, eagerly pushing [npc.her] [npc.hand] into your [pc.ass] as [npc.she] sets about fingering your [pc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as [npc.she] enters you, and, seeking to remind you who's in charge, [npc.she] roughly curls [npc.her] [npc.fingers] up before starting to ruthlessly finger-fuck your [pc.asshole+].",
							" With [npc.a_moan+], [npc.she] curls [npc.her] [npc.fingers+] up inside of you, seeking to remind you who's in charge as [npc.she] starts roughly fingering your [pc.asshole+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as [npc.she] enters you, curling [npc.her] [npc.fingers] up before eagerly starting to finger your [pc.asshole+].",
							" With [npc.a_moan+], [npc.she] curls [npc.her] [npc.fingers+] up inside of you, eagerly pushing [npc.her] [npc.hand] into your [pc.ass] as [npc.she] sets about fingering your [pc.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as [npc.she] enters you, curling [npc.her] [npc.fingers] up before starting to finger your [pc.asshole+].",
							" With [npc.a_moan+], [npc.she] curls [npc.her] [npc.fingers+] up inside of you, pushing [npc.her] [npc.hand] into your [pc.ass] as [npc.she] sets about fingering your [pc.asshole+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_sob+] as you force [npc.her] [npc.fingers] inside of you, struggling against your firm grip on [npc.her] [npc.hand] as [npc.she] tries to pull [npc.herself] free.",
							" With [npc.a_sob+], [npc.name] starts struggling against your tight grip on [npc.her] [npc.hand], pleading for you to stop as you force [npc.her] [npc.fingers] deep into your [pc.asshole+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION), new ListValue<>(Fetish.FETISH_DOMINANT));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION), new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
	};
	
	public static SexAction PLAYER_ANALLY_FINGERED_DOM_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.ANUS_PLAYER,
			SexPace.DOM_GENTLE,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Gently anally fingered";
		}

		@Override
		public String getActionDescription() {
			return "Gently enjoy [npc.name]'s [npc.fingers+] in your [pc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently pushing your [pc.ass] back against [npc.name]'s [npc.hand], you let out a soft [pc.moan] as you help to sink [npc.her] [npc.fingers+] deep into your [pc.asshole+].",
					"With a soft [pc.moan], you gently start gyrating your [pc.ass] back against [npc.name]'s [npc.hand], forcing [npc.her] [npc.fingers+] ever deeper into your [pc.asshole+].",
					"Slowly thrusting your [pc.ass] back against [npc.name]'s [npc.hand], a soft [pc.moan] drifts out from between your [pc.lips+] as your movements force [npc.her] [npc.fingers+] deep into your [npc.asshole+]."));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction PLAYER_ANALLY_FINGERED_DOM_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.ANUS_PLAYER,
			SexPace.DOM_NORMAL,
			null) {
		
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
			return "Enjoy [npc.name]'s [npc.fingers+] in your [pc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing your [pc.ass] back against [npc.name]'s [npc.hand], you let out [pc.a_moan+] as you energetically help to sink [npc.her] [npc.fingers+] deep into your [pc.asshole+].",
					"With [pc.a_moan+], you energetically start gyrating your [pc.ass] back against [npc.name]'s [npc.hand], forcing [npc.her] [npc.fingers+] ever deeper into your [pc.asshole+].",
					"Enthusiastically thrusting your [pc.ass] back against [npc.name]'s [npc.hand], [pc.a_moan+] bursts out from between your [pc.lips+] as your movements force [npc.her] [npc.fingers+] deep into your [npc.asshole+]."));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction PLAYER_ANALLY_FINGERED_DOM_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.ANUS_PLAYER,
			SexPace.DOM_ROUGH,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Roughly anally fingered";
		}

		@Override
		public String getActionDescription() {
			return "Roughly force [npc.name]'s [npc.fingers+] deep inside your [pc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Violently slamming your [pc.ass] back against [npc.name]'s [npc.hand], you let out [pc.a_moan+] as you roughly force [npc.her] [npc.fingers+] deep into your [pc.asshole+].",
					"With [pc.a_moan+], you aggressively start gyrating your [pc.ass] back against [npc.name]'s [npc.hand], forcing [npc.her] [npc.fingers+] ever deeper into your [pc.asshole+].",
					"Roughly thrusting your [pc.ass] back against [npc.name]'s [npc.hand], [pc.a_moan+] bursts out from between your [pc.lips+] as your forceful movements drive [npc.her] [npc.fingers+] deep into your [npc.asshole+]."));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction PLAYER_ANALLY_FINGERED_SUB_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.ANUS_PLAYER,
			SexPace.SUB_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Enjoy anal fingering";
		}

		@Override
		public String getActionDescription() {
			return "Enjoy [pc.name]'s [pc.fingers+] in your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing your [pc.ass] back against [npc.name]'s [npc.hand], you let out [pc.a_moan+] as you energetically help to sink [npc.her] [npc.fingers+] deep into your [pc.asshole+].",
					"With [pc.a_moan+], you energetically start gyrating your [pc.ass] back against [npc.name]'s [npc.hand], forcing [npc.her] [npc.fingers+] ever deeper into your [pc.asshole+].",
					"Enthusiastically thrusting your [pc.ass] back against [npc.name]'s [npc.hand], [pc.a_moan+] bursts out from between your [pc.lips+] as your movements force [npc.her] [npc.fingers+] deep into your [npc.asshole+]."));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction PLAYER_ANALLY_FINGERED_SUB_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.ANUS_PLAYER,
			SexPace.SUB_EAGER,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Eagerly anally fingered";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly buck your hips against [npc.name]'s [npc.hand] as [npc.she] fingers your [pc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing your [pc.ass] back against [npc.name]'s [npc.hand], you let out [pc.a_moan+] as you energetically help to sink [npc.her] [npc.fingers+] deep into your [pc.asshole+].",
					"With [pc.a_moan+], you energetically start gyrating your [pc.ass] back against [npc.name]'s [npc.hand], forcing [npc.her] [npc.fingers+] ever deeper into your [pc.asshole+].",
					"Enthusiastically thrusting your [pc.ass] back against [npc.name]'s [npc.hand], [pc.a_moan+] bursts out from between your [pc.lips+] as your movements force [npc.her] [npc.fingers+] deep into your [npc.asshole+]."));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction PLAYER_ANAL_FINGERING_SUB_RESIST = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PARTNER,
			OrificeType.ANUS_PLAYER,
			SexPace.SUB_RESISTING,
			null) {
		@Override
		public String getActionTitle() {
			return "Resist anal fingering";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull [npc.name]'s [npc.fingers] out of your [pc.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You feel tears starting to well up in your [pc.eyes], and, unable to keep it in any longer, [pc.a_sob+] bursts out from your mouth as you weakly try to pull [npc.name]'s [npc.fingers] out of your [pc.asshole+].",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.ass] away from [npc.name]'s unwanted touch,"
									+ " struggling in desperation as [npc.her] [npc.fingers+] continue gently sliding in and out of your [pc.asshole+].",
							"Trying desperately to pull your [pc.ass] away from [npc.name]'s [npc.hand], you [pc.sob] in distress as [npc.her] [npc.fingers+] continue gently sliding deep into your [pc.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You feel tears starting to well up in your [pc.eyes], and, unable to keep it in any longer,"
									+ " [pc.a_sob+] bursts out from your mouth as you weakly try to pull [npc.name]'s greedy [npc.fingers] out of your [pc.asshole+].",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.ass] away from [npc.name]'s unwanted touch,"
									+ " struggling in desperation as [npc.her] [npc.fingers+] continue eagerly sliding in and out of your [pc.asshole+].",
							"Trying desperately to pull your [pc.ass] away from [npc.name]'s [npc.hand], you [pc.sob] in distress as [npc.her] [npc.fingers+] continue eagerly pumping deep into your [pc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You feel tears starting to well up in your [pc.eyes], and, unable to keep it in any longer, [pc.a_sob+] bursts out from your mouth as you weakly try to pull [npc.name]'s rough [npc.fingers] out of your [pc.asshole+].",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.ass] away from [npc.name]'s unwanted touch,"
									+ " struggling in desperation as [npc.her] [npc.fingers+] continue roughly slamming in and out of your [pc.asshole+].",
							"Trying desperately to pull your [pc.ass] away from [npc.name]'s [npc.hand], you [pc.sob] in distress as [npc.her] [npc.fingers+] continue roughly slamming deep into your [pc.asshole+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION), new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_MASOCHIST));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION), new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_SADIST));
		}
	};
	
	public static SexAction PLAYER_ANAL_FINGERING_STOP = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PARTNER,
			OrificeType.ANUS_PLAYER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager().isConsensualSex() || Sex.isPlayerDom(); // Player can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop getting anally fingered";
		}

		@Override
		public String getActionDescription() {
			return "Force [npc.name] to pull [npc.her] [npc.fingers] out of your [pc.asshole+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc.her] [npc.fingers] out of your [pc.asshole+], you growl at [npc.name] as you command [npc.herHim] to stop fingering you.",
							"You lean into [npc.name], inhaling [npc.her] [npc.scent] as you yank [npc.her] [npc.fingers] out of your [pc.asshole+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.name]'s [npc.fingers] out of your [pc.asshole+], you let out [pc.a_moan+] before telling [npc.herHim] to stop fingering you.",
							"You lean into [npc.name], inhaling [npc.her] [npc.scent] as you slide [npc.her] [npc.fingers] out of your [pc.asshole+]."));
					break;
			}
			
			switch(Sex.getSexPacePartner()) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out a relieved sigh, which soon turns into [npc.a_sob+] as [npc.she] realises that you aren't finished with [npc.herHim] yet.",
							" With [npc.a_sob+], [npc.name] continues to protest and struggle against you as you hold [npc.herHim] firmly in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as you stop [npc.herHim] from stimulating your [pc.asshole+].",
							" [npc.A_moan+] escapes from between [npc.name]'s [npc.lips+], betraying [npc.her] desire to give your [pc.asshole+] more of [npc.her] attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
