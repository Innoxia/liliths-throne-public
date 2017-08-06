package com.base.game.sex.sexActions.baseActionsPlayer;

import java.util.List;

import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.effects.Fetish;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.sex.ArousalIncrease;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.Sex;
import com.base.game.sex.SexPace;
import com.base.game.sex.SexPosition;
import com.base.game.sex.sexActions.SexAction;
import com.base.game.sex.sexActions.SexActionType;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;

/**
 * @since 0.1.79
 * @version 0.1.79
 * @author Innoxia
 */
public class PlayerFingerUrethra {
	
	//TODO grope cock
	
	public static SexAction PLAYER_MASTURBATE_PARTNERS_COCK = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PLAYER,
			OrificeType.URETHRA_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPacePlayer()!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Stroke [npc.her] cock";
		}

		@Override
		public String getActionDescription() {
			return "Reach down and start masturbating [npc.name]'s [npc.cock+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching down between [npc.name]'s [npc.legs], you wrap your [pc.fingers] around [npc.her] [npc.cock+], letting out a soft [pc.moan] as you start slowly stroking up and down its length.",
							"You drop one of your [pc.hands] down between [npc.name]'s [npc.legs], and, taking hold of [npc.her] [npc.cock+], you start slowly jerking [npc.herHim] off.",
							"Teasing your [pc.fingers] over [npc.name]'s [npc.cock+], you let out a soft [pc.moan] as you start gently stroking up and down its throbbing length."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching down between [npc.name]'s [npc.legs], you eagerly wrap your [pc.fingers] around [npc.her] [npc.cock+], letting out [pc.a_moan+] as you start rapidly stroking up and down its length.",
							"You drop one of your [pc.hands] down between [npc.name]'s [npc.legs], and, taking hold of [npc.her] [npc.cock+], you start eagerly jerking [npc.herHim] off.",
							"Teasing your [pc.fingers] over [npc.name]'s [npc.cock+], you let out [pc.a_moan+] as you start rapidly stroking up and down its throbbing length."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching down between [npc.name]'s [npc.legs], you forcefully wrap your [pc.fingers] around [npc.her] [npc.cock+], letting out [pc.a_moan+] as you start frantically stroking up and down its length.",
							"You drop one of your [pc.hands] down between [npc.name]'s [npc.legs], and, roughly taking hold of [npc.her] [npc.cock+], you start forcefully jerking [npc.herHim] off.",
							"Forcefully wrapping your [pc.fingers] around [npc.name]'s [npc.cock+], you let out [pc.a_moan+] as you start frantically stroking up and down its throbbing length."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching down between [npc.name]'s [npc.legs], you eagerly wrap your [pc.fingers] around [npc.her] [npc.cock+], letting out [pc.a_moan+] as you start rapidly stroking up and down its length.",
							"You drop one of your [pc.hands] down between [npc.name]'s [npc.legs], and, taking hold of [npc.her] [npc.cock+], you start eagerly jerking [npc.herHim] off.",
							"Teasing your [pc.fingers] over [npc.name]'s [npc.cock+], you let out [pc.a_moan+] as you start rapidly stroking up and down its throbbing length."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching down between [npc.name]'s [npc.legs], you wrap your [pc.fingers] around [npc.her] [npc.cock+], letting out [pc.a_moan+] as you start rapidly stroking up and down its length.",
							"You drop one of your [pc.hands] down between [npc.name]'s [npc.legs], and, taking hold of [npc.her] [npc.cock+], you start jerking [npc.herHim] off.",
							"Teasing your [pc.fingers] over [npc.name]'s [npc.cock+], you let out [pc.a_moan+] as you start stroking up and down its throbbing length."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Letting out a soft [npc.moan], [npc.she] starts gently bucking [npc.her] [npc.hips] against your [pc.hand], and you feel [npc.her] [npc.cock+] throbbing in response to your touch.",
							" With a soft [npc.moan], [npc.name] starts slowly thrusting [npc.her] [npc.hips] against your touch, clearly enjoying the stimulation that you're providing to [npc.herHim].",
							" [npc.She] starts slowly bucking [npc.her] [npc.hips] against your [pc.hand], [npc.moaning] softly as [npc.she] focuses on the pleasure that you're giving to [npc.her] [npc.cock+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Letting out [npc.a_moan+], [npc.she] starts eagerly bucking [npc.her] [npc.hips] against your [pc.hand], and you feel [npc.her] [npc.cock+] throbbing in response to your touch.",
							" With [npc.a_moan+], [npc.name] starts eagerly thrusting [npc.her] [npc.hips] against your touch, clearly enjoying the stimulation that you're providing to [npc.herHim].",
							" [npc.She] starts eagerly bucking [npc.her] [npc.hips] against your [pc.hand], [npc.moaning+] as [npc.she] focuses on the pleasure that you're giving to [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Letting out [npc.a_moan+], [npc.she] starts violently bucking [npc.her] [npc.hips] against your [pc.hand], growling out as [npc.she] orders you to continue pleasuring [npc.her] [npc.cock+].",
							" With [npc.a_moan+], [npc.name] starts roughly thrusting [npc.her] [npc.hips] against your touch, filling your [pc.hand] with [npc.her] [npc.cock+] as [npc.she] orders you not to stop.",
							" [npc.She] starts forcefully bucking [npc.her] [npc.hips] against your [pc.hand], [npc.moaning+] as [npc.she] orders you to continue servicing [npc.her] [npc.cock+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Letting out [npc.a_moan+], [npc.she] starts eagerly bucking [npc.her] [npc.hips] against your [pc.hand], and you feel [npc.her] [npc.cock+] throbbing in response to your touch.",
							" With [npc.a_moan+], [npc.name] starts eagerly thrusting [npc.her] [npc.hips] against your touch, clearly enjoying the stimulation that you're providing to [npc.herHim].",
							" [npc.She] starts eagerly bucking [npc.her] [npc.hips] against your [pc.hand], [npc.moaning+] as [npc.she] focuses on the pleasure that you're giving to [npc.her] [npc.cock+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Letting out [npc.a_moan+], [npc.she] starts bucking [npc.her] [npc.hips] against your [pc.hand], and you feel [npc.her] [npc.cock+] throbbing in response to your touch.",
							" With [npc.a_moan+], [npc.name] starts thrusting [npc.her] [npc.hips] against your touch, clearly enjoying the stimulation that you're providing to [npc.herHim].",
							" [npc.She] starts bucking [npc.her] [npc.hips] against your [pc.hand], [npc.moaning+] as [npc.she] focuses on the pleasure that you're giving to [npc.her] [npc.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] tries to pull [npc.her] [npc.hips] back from your unwanted touch, [npc.sobbing] and struggling as [npc.her] throbbing [npc.cock] betrays [npc.her] arousal.",
							" With [npc.a_sob+], [npc.name] tries to pull away from you, struggling and protesting as [npc.her] hard [npc.cock] betrays [npc.her] arousal.",
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips+] as [npc.name] struggles back against your unwanted touch."));
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
	
	public static SexAction PLAYER_FONDLE_BALLS = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PLAYER,
			OrificeType.URETHRA_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPacePlayer()!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Fondle balls";
		}

		@Override
		public String getActionDescription() {
			return "Start playing with [npc.name]'s [npc.balls+].";
		}

		@Override
		public String getDescription() {
			if(Sex.getPosition()==SexPosition.SIXTY_NINE_PARTNER_TOP) {
				return UtilText.returnStringAtRandom(
						"You prop yourself up on one [pc.arm], before using your free [pc.hand] to stroke and squeeze [npc.name]'s [npc.balls+].",
						"You let out [pc.a_moan+] as you prop yourself up on one [pc.arm], before reaching down and starting to stroke and play with [npc.name]'s [npc.balls+].",
						"Propping yourself up on one [pc.arm], you run your [pc.fingers+] over [npc.name]'s ballsack, grinning as you start to stroke and cup [npc.her] [npc.balls+].",
						"Propping yourself up on one [pc.arm], you use your free [pc.hand] to cup and stroke [npc.name]'s [npc.balls+], moaning happily to yourself as [npc.her] [npc.cock+] twitches in response.");
				
			} else {
				return UtilText.returnStringAtRandom(
							"You reach between [npc.name]'s [npc.legs] with one [pc.hand], before starting to stroke and squeeze [npc.her] [npc.balls+].",
							"[npc.Name] lets out [npc.a_moan+] as you reach up with one [pc.hand] and start to stroke and play with [npc.her] [npc.balls+].",
							"Running your [pc.fingers+] over [npc.name]'s [npc.balls+], you start to stroke and cup them, letting out [pc.a_moan+] as you see [npc.her] [npc.cock+] twitching in response.");
			}
		}
	};
	
}
