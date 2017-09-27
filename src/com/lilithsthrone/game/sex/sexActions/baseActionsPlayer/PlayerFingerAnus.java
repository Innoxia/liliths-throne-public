package com.lilithsthrone.game.sex.sexActions.baseActionsPlayer;

import java.util.List;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
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
public class PlayerFingerAnus {

	public static final SexAction PLAYER_ANAL_FINGERING_START = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PLAYER,
			OrificeType.ANUS_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Finger [npc.her] ass";
		}

		@Override
		public String getActionDescription() {
			return "Sink your [pc.fingers] into [npc.name]'s [npc.asshole+] and start fingering [npc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly teasing your [pc.fingers] over [npc.name]'s [npc.ass+], you let out a little [pc.moan] before slowly sinking your digits into [npc.her] [npc.asshole+].",
							"You press your [pc.fingers] down between [npc.name]'s ass cheeks, and with a slow, steady pressure, you gently sink your digits into [npc.her] [npc.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing your [pc.fingers] over [npc.name]'s [npc.ass+], you let out [pc.a_moan+] before greedily sinking your digits into [npc.her] [npc.asshole+].",
							"You eagerly press your [pc.fingers] down between [npc.name]'s ass cheeks, and with a determined thrust, you greedily sink your digits into [npc.her] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding your [pc.fingers] down against [npc.name]'s [npc.ass+], you let out [pc.a_moan+] before violently slamming your digits deep into [npc.her] [npc.asshole+].",
							"You roughly grind your [pc.fingers] down between [npc.name]'s ass cheeks, and with a forceful thrust, you greedily slam your digits deep into [npc.her] [npc.asshole+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing your [pc.fingers] over [npc.name]'s [npc.ass+], you let out [pc.a_moan+] before greedily sinking your digits into [npc.her] [npc.asshole+].",
							"You eagerly press your [pc.fingers] down between [npc.name]'s ass cheeks, and with a determined thrust, you greedily sink your digits into [npc.her] [npc.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing your [pc.fingers] over [npc.name]'s [npc.ass+], you let out [pc.a_moan+] before sinking your digits into [npc.her] [npc.asshole+].",
							"You press your [pc.fingers] down between [npc.name]'s ass cheeks, and with a determined push of your [pc.hand], you sink your digits into [npc.her] [npc.asshole+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a soft [npc.moan] as your [pc.fingers] enter [npc.herHim], gently pushing [npc.her] [npc.ass] back as [npc.she] helps to sink your [pc.fingers] even deeper into [npc.her] [npc.asshole+].",
							" With a soft [npc.moan], [npc.she] starts gently pushing [npc.her] [npc.ass] back against your [pc.hand], encouraging you to sink your [pc.fingers] even deeper into [npc.her] [npc.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as your [pc.fingers] enter [npc.herHim],"
									+ " eagerly pushing [npc.her] [npc.ass] back as [npc.she] enthusiastically helps to sink your [pc.fingers] even deeper into [npc.her] [npc.asshole+].",
							" With [npc.a_moan+], [npc.she] starts eagerly bucking [npc.her] [npc.ass] back against your [pc.hand], desperately encouraging you to sink your [pc.fingers] even deeper into [npc.her] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as your [pc.fingers] enter [npc.herHim],"
									+ " violently thrusting [npc.her] [npc.ass] back as [npc.she] roughly forces you to sink your [pc.fingers] even deeper into [npc.her] [npc.asshole+].",
							" With [npc.a_moan+], [npc.she] starts violently bucking [npc.her] [npc.ass] back against your [pc.hand], roughly forcing you to sink your [pc.fingers] even deeper into [npc.her] [npc.asshole+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as your [pc.fingers] enter [npc.herHim],"
									+ " eagerly pushing [npc.her] [npc.ass] back as [npc.she] enthusiastically helps to sink your [pc.fingers] even deeper into [npc.her] [npc.asshole+].",
							" With [npc.a_moan+], [npc.she] starts eagerly bucking [npc.her] [npc.ass] back against your [pc.hand], desperately encouraging you to sink your [pc.fingers] even deeper into [npc.her] [npc.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as your [pc.fingers] enter [npc.herHim], bucking [npc.her] [npc.ass] back as [npc.she] helps to sink your [pc.fingers] even deeper into [npc.her] [npc.asshole+].",
							" With [npc.a_moan+], [npc.she] starts bucking [npc.her] [npc.ass] back against your [pc.hand], encouraging you to sink your [pc.fingers] even deeper into [npc.her] [npc.asshole+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_sob+] as your [pc.fingers] enter [npc.herHim], trying, in vain, to pull [npc.her] [npc.ass] away from your unwanted penetration, struggling and [npc.sobbing] all the while.",
							" With [npc.a_sob+], [npc.she] tries, in vain, to pull away from your unwanted penetration, [npc.sobbing] and struggling against you as your unwelcome [pc.fingers] push deep into [npc.her] [npc.asshole+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction PLAYER_ANAL_FINGERING_DOM_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PLAYER,
			OrificeType.ANUS_PARTNER,
			SexPace.DOM_GENTLE,
			null) {
		@Override
		public String getActionTitle() {
			return "Gentle anal fingering";
		}

		@Override
		public String getActionDescription() {
			return "Gently finger [npc.name]'s [npc.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently sinking your [pc.fingers+] into [npc.name]'s [npc.asshole+], you start slowly sliding your digits in and out,"
									+ " grinning as [npc.she] lets out a delighted [npc.moan] and eagerly thrusts [npc.her] [npc.ass] back against your [pc.hand].",
							"Slowly pushing your [pc.fingers+] into [npc.name]'s [npc.asshole+], you draw a delighted [npc.moan] from between [npc.her] [npc.lips+],"
									+ " and, eagerly thrusting [npc.her] [npc.ass] back against your touch, [npc.she] begs for you to continue fingering [npc.herHim].",
							"Gently sliding your [pc.fingers+] into [npc.name]'s [npc.asshole+], you cause [npc.herHim] to let out [npc.a_moan+] as [npc.she] eagerly grinds [npc.her] [npc.ass] back against your [pc.hand]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently sinking your [pc.fingers+] into [npc.name]'s [npc.asshole+], [npc.she] desperately tries to recoil [npc.her] away from your touch,"
									+ " letting out [npc.a_sob+] as [npc.she] weakly tries to struggle away from your intruding [pc.fingers].",
							"Slowly pushing your [pc.fingers+] into [npc.name]'s [npc.asshole+], [npc.a_sob+] bursts out from between [npc.her] [npc.lips],"
									+ " and [npc.she] starts squirming and protesting as you continue to gently finger [npc.her] [npc.ass+].",
							"Gently sliding your [pc.fingers+] into [npc.name]'s [npc.asshole+], [npc.she] starts [npc.sobbing] in distress,"
									+ " struggling weakly against you as your [pc.fingers+] continue gently sliding deep into [npc.her] [npc.ass+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently sinking your [pc.fingers+] into [npc.name]'s [npc.asshole+], you start slowly sliding your digits in and out,"
									+ " grinning as [npc.she] lets out [npc.a_moan] and thrusts [npc.her] [npc.ass] back against your [pc.hand].",
							"Slowly pushing your [pc.fingers+] into [npc.name]'s [npc.asshole+], you draw [npc.a_moan] from between [npc.her] [npc.lips+],"
									+ " and, thrusting [npc.her] [npc.ass] back against your touch, [npc.she] begs for you to continue fingering [npc.herHim].",
							"Gently sliding your [pc.fingers+] into [npc.name]'s [npc.asshole+], you cause [npc.herHim] to let out [npc.a_moan+] as [npc.she] grinds [npc.her] [npc.ass] back against your [pc.hand]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction PLAYER_ANAL_FINGERING_DOM_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PLAYER,
			OrificeType.ANUS_PARTNER,
			SexPace.DOM_NORMAL,
			null) {
		@Override
		public String getActionTitle() {
			return "Anal fingering";
		}

		@Override
		public String getActionDescription() {
			return "Continue fingering [npc.name]'s [npc.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly sinking your [pc.fingers+] into [npc.name]'s [npc.asshole+], you start rapidly sliding your digits in and out,"
									+ " grinning as [npc.she] lets out a delighted [npc.moan] and eagerly thrusts [npc.her] [npc.ass] back against your [pc.hand].",
							"Enthusiastically pushing your [pc.fingers+] deep into [npc.name]'s [npc.asshole+], you draw a delighted [npc.moan] from between [npc.her] [npc.lips+],"
									+ " and, eagerly thrusting [npc.her] [npc.ass] back against your touch, [npc.she] begs for you to continue fingering [npc.herHim].",
							"Eagerly pressing your [pc.fingers+] deep into [npc.name]'s [npc.asshole+], you cause [npc.herHim] to let out [npc.a_moan+] as [npc.she] eagerly grinds [npc.her] [npc.ass] back against your [pc.hand]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Greedily sinking your [pc.fingers+] into [npc.name]'s [npc.asshole+], [npc.she] desperately tries to recoil [npc.her] away from your eager touch,"
									+ " letting out [npc.a_sob+] as [npc.she] weakly tries to struggle away from your intruding [pc.fingers].",
							"Eagerly pushing your [pc.fingers+] into [npc.name]'s [npc.asshole+], [npc.a_sob+] bursts out from between [npc.her] [npc.lips],"
									+ " and [npc.she] starts squirming and protesting as you continue to enthusiastically finger [npc.her] [npc.ass+].",
							"Eagerly sliding your [pc.fingers+] into [npc.name]'s [npc.asshole+], [npc.she] starts [npc.sobbing] in distress,"
									+ " struggling weakly against you as your greedy [pc.fingers+] continue driving deep into [npc.her] [npc.ass+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Greedily sinking your [pc.fingers+] deep into [npc.name]'s [npc.asshole+], you start rapidly sliding your digits in and out,"
									+ " grinning as [npc.she] lets out [npc.a_moan] and thrusts [npc.her] [npc.ass] back against your [pc.hand].",
							"Eagerly pushing your [pc.fingers+] deep into [npc.name]'s [npc.asshole+], you draw [npc.a_moan] from between [npc.her] [npc.lips+],"
									+ " and, thrusting [npc.her] [npc.ass] back against your touch, [npc.she] begs for you to continue fingering [npc.herHim].",
							"Eagerly pressing your [pc.fingers+] deep into [npc.name]'s [npc.asshole+], you cause [npc.herHim] to let out [npc.a_moan+] as [npc.she] grinds [npc.her] [npc.ass] back against your [pc.hand]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction PLAYER_ANAL_FINGERING_DOM_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.FINGER_PLAYER,
			OrificeType.ANUS_PARTNER,
			SexPace.DOM_ROUGH,
			null) {
		@Override
		public String getActionTitle() {
			return "Rough anal fingering";
		}

		@Override
		public String getActionDescription() {
			return "Roughly finger [npc.name]'s [npc.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}


		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Greedily thrusting your [pc.fingers+] deep into [npc.name]'s [npc.asshole+], you start roughly slamming your digits in and out,"
									+ " grinning as [npc.she] lets out a delighted [npc.moan] and eagerly thrusts [npc.her] [npc.ass] back against your [pc.hand].",
							"Roughly slamming your [pc.fingers+] deep into [npc.name]'s [npc.asshole+], you draw a delighted [npc.moan] from between [npc.her] [npc.lips+],"
									+ " and, eagerly thrusting [npc.her] [npc.ass] back against your touch, [npc.she] begs for you to continue fingering [npc.herHim].",
							"Forcefully pressing your [pc.fingers+] deep into [npc.name]'s [npc.asshole+], you cause [npc.herHim] to let out [npc.a_moan+] as [npc.she] eagerly grinds [npc.her] [npc.ass] back against your [pc.hand]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Greedily thrusting your [pc.fingers+] deep into [npc.name]'s [npc.asshole+], [npc.she] desperately tries to recoil [npc.her] away from your rough touch,"
									+ " letting out [npc.a_sob+] as [npc.she] weakly tries to struggle away from your intruding [pc.fingers].",
							"Roughly slamming your [pc.fingers+] deep into [npc.name]'s [npc.asshole+], [npc.a_sob+] bursts out from between [npc.her] [npc.lips],"
									+ " and [npc.she] starts squirming and protesting as you continue to forcefully finger [npc.her] [npc.ass+].",
							"Forcefully pressing your [pc.fingers+] deep into [npc.name]'s [npc.asshole+], [npc.she] starts [npc.sobbing] in distress,"
									+ " struggling weakly against you as your [pc.fingers+] continue roughly slamming deep into [npc.her] [npc.ass+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Greedily thrusting your [pc.fingers+] deep into [npc.name]'s [npc.asshole+], you start roughly slamming your digits in and out,"
									+ " grinning as [npc.she] lets out [npc.a_moan] and thrusts [npc.her] [npc.ass] back against your [pc.hand].",
							"Roughly slamming your [pc.fingers+] deep into [npc.name]'s [npc.asshole+], you draw [npc.a_moan] from between [npc.her] [npc.lips+],"
									+ " and, thrusting [npc.her] [npc.ass] back against your touch, [npc.she] begs for you to continue fingering [npc.herHim].",
							"Forcefully pressing your [pc.fingers+] deep into [npc.name]'s [npc.asshole+], you cause [npc.herHim] to let out [npc.a_moan+] as [npc.she] grinds [npc.her] [npc.ass] back against your [pc.hand]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION), new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_SADIST));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION), new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_MASOCHIST));
		}
	};
	
	public static final SexAction PLAYER_ANAL_FINGERING_SUB_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PLAYER,
			OrificeType.ANUS_PARTNER,
			SexPace.SUB_NORMAL,
			null) {
		@Override
		public String getActionTitle() {
			return "Anal fingering";
		}

		@Override
		public String getActionDescription() {
			return "Continue fingering [npc.name]'s [npc.asshole+].";
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
							"Sinking your [pc.fingers+] into [npc.name]'s [npc.asshole+], you start sliding your digits in and out,"
									+ " drawing a soft [npc.moan] from [npc.her] mouth as [npc.she] slowly pushes [npc.her] [npc.ass] back against your [pc.hand].",
							"Pushing your [pc.fingers+] into [npc.name]'s [npc.asshole+], you draw a soft [npc.moan] from between [npc.her] [npc.lips+],"
									+ " and, gently pushing [npc.her] [npc.ass] back against your touch, [npc.she] begs for you to continue fingering [npc.herHim].",
							"Pressing your [pc.fingers+] into [npc.name]'s [npc.asshole+], you cause [npc.herHim] to let out a gentle [npc.moan] as [npc.she] slowly grinds [npc.her] [npc.ass] back against your [pc.hand]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sinking your [pc.fingers+] into [npc.name]'s [npc.asshole+], you start sliding your digits in and out,"
									+ " drawing [npc.a_moan+] from [npc.her] mouth as [npc.she] roughly thrusts [npc.her] [npc.ass] back against your [pc.hand].",
							"Pushing your [pc.fingers+] into [npc.name]'s [npc.asshole+], you draw [npc.a_moan+] from between [npc.her] [npc.lips+],"
									+ " and, roughly grinding [npc.her] [npc.ass] back against your touch, [npc.she] orders you to continue fingering [npc.herHim].",
							"Pressing your [pc.fingers+] into [npc.name]'s [npc.asshole+], you cause [npc.herHim] to let out [npc.a_moan+] as [npc.she] roughly grinds [npc.her] [npc.ass] back against your [pc.hand]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sinking your [pc.fingers+] into [npc.name]'s [npc.asshole+], you start sliding your digits in and out,"
									+ " drawing [npc.a_moan+] from [npc.her] mouth as [npc.she] eagerly thrusts [npc.her] [npc.ass] back against your [pc.hand].",
							"Pushing your [pc.fingers+] into [npc.name]'s [npc.asshole+], you draw a delighted [npc.moan] from between [npc.her] [npc.lips+],"
									+ " and, eagerly thrusting [npc.her] [npc.ass] back against your touch, [npc.she] begs for you to continue fingering [npc.herHim].",
							"Pressing your [pc.fingers+] into [npc.name]'s [npc.asshole+], you cause [npc.herHim] to let out [npc.a_moan+] as [npc.she] eagerly grinds [npc.her] [npc.ass] back against your [pc.hand]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction PLAYER_ANAL_FINGERING_SUB_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PLAYER,
			OrificeType.ANUS_PARTNER,
			SexPace.SUB_EAGER,
			null) {
		@Override
		public String getActionTitle() {
			return "Eager anal fingering";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly finger [npc.name]'s [npc.asshole+].";
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
							"Eagerly sinking your [pc.fingers+] deep into [npc.name]'s [npc.asshole+], you start rapidly sliding your digits in and out,"
									+ " drawing a soft [npc.moan] from [npc.her] mouth as [npc.she] slowly pushes [npc.her] [npc.ass] back against your [pc.hand].",
							"Firmly pushing your [pc.fingers+] deep into [npc.name]'s [npc.asshole+], you draw a soft [npc.moan] from between [npc.her] [npc.lips+],"
									+ " and, gently pushing [npc.her] [npc.ass] back against your touch, [npc.she] begs for you to continue fingering [npc.herHim].",
							"Eagerly pressing your [pc.fingers+] deep into [npc.name]'s [npc.asshole+], you cause [npc.herHim] to let out a gentle [npc.moan] as [npc.she] slowly grinds [npc.her] [npc.ass] back against your [pc.hand]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly sinking your [pc.fingers+] deep into [npc.name]'s [npc.asshole+], you start rapidly sliding your digits in and out,"
									+ " drawing [npc.a_moan+] from [npc.her] mouth as [npc.she] roughly thrusts [npc.her] [npc.ass] back against your [pc.hand].",
							"Firmly pushing your [pc.fingers+] deep into [npc.name]'s [npc.asshole+], you draw [npc.a_moan+] from between [npc.her] [npc.lips+],"
									+ " and, roughly grinding [npc.her] [npc.ass] back against your touch, [npc.she] orders you to continue fingering [npc.herHim].",
							"Eagerly pressing your [pc.fingers+] deep into [npc.name]'s [npc.asshole+], you cause [npc.herHim] to let out [npc.a_moan+] as [npc.she] roughly grinds [npc.her] [npc.ass] back against your [pc.hand]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly sinking your [pc.fingers+] deep into [npc.name]'s [npc.asshole+], you start rapidly sliding your digits in and out,"
									+ " drawing [npc.a_moan+] from [npc.her] mouth as [npc.she] eagerly thrusts [npc.her] [npc.ass] back against your [pc.hand].",
							"Firmly pushing your [pc.fingers+] deep into [npc.name]'s [npc.asshole+], you draw a delighted [npc.moan] from between [npc.her] [npc.lips+],"
									+ " and, eagerly thrusting [npc.her] [npc.ass] back against your touch, [npc.she] begs for you to continue fingering [npc.herHim].",
							"Eagerly pressing your [pc.fingers+] deep into [npc.name]'s [npc.asshole+], you cause [npc.herHim] to let out [npc.a_moan+] as [npc.she] eagerly grinds [npc.her] [npc.ass] back against your [pc.hand]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION), new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
	};
	
	public static final SexAction PLAYER_ANAL_FINGERING_STOP = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PLAYER,
			OrificeType.ANUS_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Stop anal fingering";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [pc.fingers] out of [npc.name]'s [npc.asshole+] and stop fingering [npc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking your [pc.fingers] out of [npc.name]'s [npc.asshole+], you quickly take your [pc.hand] away from [npc.her] [npc.ass+].",
							"Thrusting deep inside [npc.name] one last time, you then yank your [pc.fingers] back out of [npc.her] [npc.asshole+], putting an end to your rough fingering."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a distressed little [pc.sob], you quickly pull your [pc.fingers] out of [npc.name]'s [npc.asshole+].",
							"Pulling your [pc.fingers] out of [npc.name]'s [npc.asshole+], you let out a little [pc.sob] before pleading for [npc.herHim] to leave you alone."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.fingers] out of [npc.name]'s [npc.asshole+], you quickly take your [pc.hand] away from [npc.her] [npc.ass+].",
							"Pushing deep inside [npc.name] one last time, you then slide your [pc.fingers] back out of [npc.her] [npc.asshole+], putting an end to your fingering."));
					break;
			}
			
			switch(Sex.getSexPacePartner()) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] gasps as you withdraw from [npc.her] [npc.ass], before [npc.sobbing+] as [npc.she] continues to struggle against you.",
							" With [npc.a_sob+], [npc.name] continues to struggle against you as you withdraw from [npc.her] [npc.ass+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as you pull your [pc.fingers+] out of [npc.her] [npc.asshole+].",
							" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] desire for more of your attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Partner actions:
	
	public static final SexAction PARTNER_ANAL_FINGERING_START = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PLAYER,
			OrificeType.ANUS_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager().isConsensualSex() || !Sex.isPlayerDom(); // Partner can only start fingering in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Get anally fingered";
		}

		@Override
		public String getActionDescription() {
			return "Get [pc.name] to start fingering your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking a gentle, but firm, grip on your [pc.hand], [npc.name] slowly guides your [pc.fingers] over [npc.her] [npc.ass], letting out a little [npc.moan] before pushing your digits into [npc.her] [npc.asshole+].",
							"Taking hold of your [pc.hand], [npc.name] guides your [pc.fingers] over [npc.her] [npc.ass+], and with a slow, steady pressure, [npc.she] gently pushes your digits into [npc.her] [npc.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a firm grip on your [pc.hand], [npc.name] eagerly guides your [pc.fingers] over [npc.her] [npc.ass], letting out [npc.a_moan+] before greedily pushing your digits into [npc.her] [npc.asshole+].",
							"Taking hold of your [pc.hand], [npc.name] eagerly guides your [pc.fingers] over [npc.her] [npc.ass+], and with a determined pressure, [npc.she] greedily pushes your digits into [npc.her] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a vice-like grip on your [pc.hand], [npc.name] grinds your [pc.fingers] against [npc.her] [npc.ass], letting out [npc.a_moan+] before roughly pushing your digits into [npc.her] [npc.asshole+].",
							"Grabbing your [pc.hand], [npc.name] forcefully pushes your [pc.fingers] over [npc.her] [npc.ass+], and with a dominant, jerking motion, [npc.she] roughly pushes your digits into [npc.her] [npc.asshole+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a firm grip on your [pc.hand], [npc.name] eagerly guides your [pc.fingers] over [npc.her] [npc.ass], letting out [npc.a_moan+] before greedily pushing your digits into [npc.her] [npc.asshole+].",
							"Taking hold of your [pc.hand], [npc.name] eagerly guides your [pc.fingers] over [npc.her] [npc.ass+], and with a determined pressure, [npc.she] greedily pushes your digits into [npc.her] [npc.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a firm grip on your [pc.hand], [npc.name] guides your [pc.fingers] over [npc.her] [npc.ass], letting out [npc.a_moan+] before pushing your digits into [npc.her] [npc.asshole+].",
							"Taking hold of your [pc.hand], [npc.name] guides your [pc.fingers] over [npc.her] [npc.ass+], and with a determined pressure, [npc.she] pushes your digits into [npc.her] [npc.asshole+]."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soft [pc.moan] as you enter [npc.herHim], curling your [pc.fingers] up before gently starting to finger [npc.her] [npc.asshole+].",
							" With a soft [pc.moan], you curl your [pc.fingers+] up inside of [npc.herHim], gently pushing your [pc.hand] into [npc.her] [npc.ass] as you set about fingering [npc.her] [npc.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as you enter [npc.herHim], curling your [pc.fingers] up before eagerly starting to finger [npc.her] [npc.asshole+].",
							" With [pc.a_moan+], you curl your [pc.fingers+] up inside of [npc.herHim], eagerly pushing your [pc.hand] into [npc.her] [npc.ass] as you set about fingering [npc.her] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as you enter [npc.name], and, seeking to remind [npc.herHim] who's in charge, you roughly curl your [pc.fingers] up before starting to ruthlessly finger-fuck [npc.her] [npc.asshole+].",
							" With [pc.a_moan+], you curl your [pc.fingers+] up, seeking to remind [npc.name] who's in charge as you start roughly fingering [npc.her] [npc.asshole+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as you enter [npc.herHim], curling your [pc.fingers] up before eagerly starting to finger [npc.her] [npc.asshole+].",
							" With [pc.a_moan+], you curl your [pc.fingers+] up inside of [npc.herHim], eagerly pushing your [pc.hand] into [npc.her] [npc.ass] as you set about fingering [npc.her] [npc.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as you enter [npc.herHim], curling your [pc.fingers] up before starting to finger [npc.her] [npc.asshole+].",
							" With [pc.a_moan+], you curl your [pc.fingers+] up inside of [npc.herHim], pushing your [pc.hand] into [npc.her] [npc.ass] as you set about fingering [npc.her] [npc.asshole+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_sob+] as [npc.she] forces your [pc.fingers] inside of [npc.herHim], keeping a firm grip on your [pc.hand] as you struggle and try to pull yourself free.",
							" With [pc.a_sob+], you struggle against [npc.her] tight grip on your [pc.hand] as [npc.she] forces your [pc.fingers] deep into [npc.her] [npc.asshole+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION), new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION), new ListValue<>(Fetish.FETISH_DOMINANT));
		}
	};
	
	public static final SexAction PARTNER_ANALLY_FINGERED_DOM_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PLAYER,
			OrificeType.ANUS_PARTNER,
			null,
			SexPace.DOM_GENTLE) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Gently anally fingered";
		}

		@Override
		public String getActionDescription() {
			return "Gently enjoy [pc.name]'s [pc.fingers+] in your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently pushing [npc.her] [npc.ass] back against your [pc.hand], [npc.name] lets out a soft [npc.moan] as [npc.she] helps you to sink your [pc.fingers+] deep into [npc.her] [npc.asshole+].",
					"With a soft [npc.moan], [npc.name] gently starts pushing [npc.her] [npc.ass] back against your [pc.hand], forcing your [pc.fingers+] ever deeper into [npc.her] [npc.asshole+].",
					"Slowly thrusting [npc.her] [npc.ass] back against your [pc.hand], [npc.name] softly [npc.moansVerb] as [npc.her] movements force your [pc.fingers+] deep into [npc.her] [npc.asshole+]."));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction PARTNER_ANALLY_FINGERED_DOM_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PLAYER,
			OrificeType.ANUS_PARTNER,
			null,
			SexPace.DOM_NORMAL) {
		
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
					"Eagerly thrusting [npc.her] [npc.ass] back against your [pc.hand], [npc.name] lets out [npc.a_moan+] as [npc.she] energetically helps to sink your [pc.fingers+] deep into [npc.her] [npc.asshole+].",
					"With [npc.a_moan+], [npc.name] starts energetically pushing [npc.her] [npc.ass] back against your [pc.hand], forcing your [pc.fingers+] ever deeper into [npc.her] [npc.asshole+].",
					"Enthusiastically thrusting [npc.her] [npc.ass] back against your [pc.hand], [npc.name] [npc.moansVerb+] as [npc.her] eager movements force your [pc.fingers+] deep into [npc.her] [npc.asshole+]."));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction PARTNER_ANALLY_FINGERED_DOM_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.FINGER_PLAYER,
			OrificeType.ANUS_PARTNER,
			null,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Roughly anally fingered";
		}

		@Override
		public String getActionDescription() {
			return "Roughly force [pc.name]'s [pc.fingers+] deep inside your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Violently slamming [npc.her] [npc.ass] back against your [pc.hand], [npc.name] lets out [npc.a_moan+] as [npc.she] roughly forces your [pc.fingers+] deep into [npc.her] [npc.asshole+].",
					"With [npc.a_moan+], [npc.name] starts aggressively thrusting [npc.her] [npc.ass] back against your [pc.hand], roughly forcing your [pc.fingers+] ever deeper into [npc.her] [npc.asshole+].",
					"Roughly thrusting [npc.her] [npc.ass] back against your [pc.hand], [npc.name] [npc.moansVerb+] as [npc.her] forceful movements drive your [pc.fingers+] deep into [npc.her] [npc.asshole+]."));
					
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction PARTNER_FINGERED_SUB_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PLAYER,
			OrificeType.ANUS_PARTNER,
			null,
			SexPace.SUB_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
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
					"Thrusting [npc.her] [npc.ass] back against your [pc.hand], [npc.name] lets out [npc.a_moan+] as [npc.she] helps to sink your [pc.fingers+] deep into [npc.her] [npc.asshole+].",
					"With [npc.a_moan+], [npc.name] starts bucking [npc.her] [npc.ass] back against your [pc.hand], forcing your [pc.fingers+] ever deeper into [npc.her] [npc.asshole+].",
					"Thrusting [npc.her] [npc.ass] back against your [pc.hand], [npc.name] [npc.moansVerb+] as [npc.her] movements force your [pc.fingers+] deep into [npc.her] [npc.asshole+]."));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction PARTNER_ANALLY_FINGERED_SUB_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PLAYER,
			OrificeType.ANUS_PARTNER,
			null,
			SexPace.SUB_EAGER) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Eagerly anally fingered";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly buck your hips against [pc.name]'s [pc.hand] as [pc.she] fingers your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly thrusting [npc.her] [npc.ass] back against your [pc.hand], [npc.name] lets out [npc.a_moan+] as [npc.she] energetically helps to sink your [pc.fingers+] deep into [npc.her] [npc.asshole+].",
					"With [npc.a_moan+], [npc.name] starts energetically bucking [npc.her] [npc.ass] back against your [pc.hand], forcing your [pc.fingers+] ever deeper into [npc.her] [npc.asshole+].",
					"Enthusiastically thrusting [npc.her] [npc.ass] back against your [pc.hand], [npc.name] [npc.moansVerb+] as [npc.her] eager movements force your [pc.fingers+] deep into [npc.her] [npc.asshole+]."));
					
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction PARTNER_ANALLY_FINGERING_SUB_RESIST = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PLAYER,
			OrificeType.ANUS_PARTNER,
			null,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Resist anal fingering";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull [pc.name]'s [pc.fingers] out of your [npc.asshole+].";
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
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+], weakly trying to pull your [pc.fingers] out of [npc.her] [npc.asshole+] as you continue gently fingering [npc.herHim].",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.ass] back from your unwanted touch,"
									+ " struggling in desperation as your [pc.fingers] continue sliding in and out of [npc.her] [npc.asshole+].",
							"Trying desperately to pull [npc.her] [npc.ass] away from your [pc.hand], [npc.name] [npc.sobs] in distress as your [pc.fingers+] continue gently sliding deep into [npc.her] [npc.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+],"
									+ " weakly trying to pull your [pc.fingers] out of [npc.her] [npc.asshole+] as you continue eagerly fingering [npc.herHim].",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.ass] back from your unwanted touch,"
									+ " struggling in desperation as your [pc.fingers] continue eagerly sliding in and out of [npc.her] [npc.asshole+].",
							"Trying desperately to pull [npc.her] [npc.ass] away from your [pc.hand], [npc.name] [npc.sobs] in distress as your [pc.fingers+] continue eagerly sliding deep into [npc.her] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+],"
									+ " weakly trying to pull your [pc.fingers] out of [npc.her] [npc.asshole+] as you continue roughly fingering [npc.herHim].",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.ass] back from your rough touch,"
									+ " struggling in desperation as you continue slamming your [pc.fingers] in and out of [npc.her] [npc.asshole+].",
							"Trying desperately to pull [npc.her] [npc.ass] away from your [pc.hand], [npc.name] [npc.sobs] in distress as your [pc.fingers+] continue roughly slamming deep into [npc.her] [npc.asshole+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_MASTURBATION), new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_SADIST));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_MASTURBATION), new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_MASOCHIST));
		}
	};
	
	public static final SexAction PARTNER_ANAL_FINGERING_STOP = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PLAYER,
			OrificeType.ANUS_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager().isConsensualSex() || !Sex.isPlayerDom(); // Partner can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop getting anally fingered";
		}

		@Override
		public String getActionDescription() {
			return "Get [pc.name] to pull [pc.her] [pc.fingers] out of your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking your [pc.fingers] out of [npc.her] [npc.asshole+], [npc.name] growls at you as [npc.she] commands you to stop fingering [npc.herHim].",
							"[npc.Name] leans into you, causing you to inhale [npc.her] [npc.scent] before [npc.she] yanks your [pc.fingers] out of [npc.her] [npc.asshole+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.fingers] out of [npc.her] [npc.asshole+], [npc.name] lets out [npc.a_moan+] as [npc.she] tells you to stop fingering [npc.herHim].",
							"[npc.Name] leans into you, causing you to inhale [npc.her] [npc.scent] before [npc.she] slides your [pc.fingers] out of [npc.her] [npc.asshole+]."));
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
							" You let out [pc.a_moan+] as [npc.she] stops you from stimulating [npc.her] [npc.asshole+].",
							" [pc.A_moan+] escapes from between your [pc.lips+], betraying your desire to give [npc.her] [npc.asshole+] more of your attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
