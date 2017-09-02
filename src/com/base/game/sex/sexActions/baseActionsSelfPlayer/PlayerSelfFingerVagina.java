package com.base.game.sex.sexActions.baseActionsSelfPlayer;

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
public class PlayerSelfFingerVagina {
	
	public static SexAction PLAYER_SELF_FINGER_VAGINA_PENETRATION = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PLAYER) {
		
		@Override
		public String getActionTitle() {
			return "Finger yourself";
		}

		@Override
		public String getActionDescription() {
			return "Start fingering yourself.";
		}

		@Override
		public String getDescription() {
			return (UtilText.returnStringAtRandom(
					"Reaching down between your [pc.legs], you tease your fingers over the entrance to your [pc.pussy+], before letting out [pc.a_moan+] as you push your digits deep inside.",
					
					"You probe your fingers down between your [pc.legs], moaning softly as you push two of your digits into your inviting [pc.pussy+].",
					
					"Sliding your fingertips over your neglected [pc.pussy], you let out [pc.a_moan+] as you push your digits inside and start fingering yourself.",
					
					"You eagerly push your fingers into your needy [pc.pussy], [pc.moaning+] as you curl your digits up inside yourself and start stroking in a 'come-hither' motion."));
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction DOM_PLAYER_SELF_FINGER_VAGINA_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PLAYER,
			SexPace.DOM_GENTLE,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Gently finger your [pc.pussy+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you slowly push your [pc.fingers] deep inside your [pc.pussy+].",
					
					"Gently pumping your [pc.fingers] in and out of your [pc.pussy+], you find yourself letting out a series of delighted [pc.moans] as you rhythmically finger yourself.",
					
					"Curling your [pc.fingers] up inside your [pc.pussy], you let out a little whimper as you start stroking your vaginal walls.",
					
					"Focusing on the pleasure you're giving yourself between your [pc.legs], you start gently pumping your [pc.fingers] in and out of your [pc.pussy+].");
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction DOM_PLAYER_SELF_FINGER_VAGINA_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PLAYER,
			SexPace.DOM_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Finger self";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fingering yourself.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you greedily push your [pc.fingers] deep inside your [pc.pussy+].",
					
					"Pumping your [pc.fingers] in and out of your [pc.pussy+], you find yourself letting out a series of delighted [pc.moans] as you rhythmically finger yourself.",
					
					"Curling your [pc.fingers] up inside your [pc.pussy], you let out [pc.a_moan] as you start stroking your vaginal walls.",
					
					"Focusing on the pleasure you're giving yourself between your [pc.legs], you start pumping your [pc.fingers] in and out of your [pc.pussy+].");
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction DOM_PLAYER_SELF_FINGER_VAGINA_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PLAYER,
			SexPace.DOM_ROUGH,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Rough fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Roughly finger yourself.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you roughly slam your [pc.fingers] deep inside your [pc.pussy+], before starting to roughly finger yourself.",
					
					"Roughly pumping your [pc.fingers] in and out of your [pc.pussy+], you find yourself letting out a series of delighted [pc.moans] as you ruthlessly finger yourself.",
					
					"Forcefully curling your [pc.fingers] up inside your [pc.pussy], you let out [pc.a_moan] as you start roughly grinding your fingertips up against your vaginal walls.",
					
					"Focusing on the pleasure you're giving yourself between your [pc.legs], you start roughly slamming your [pc.fingers] in and out of your [pc.pussy+].");
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASOCHIST), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction SUB_PLAYER_SELF_FINGER_VAGINA_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PLAYER,
			SexPace.SUB_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Finger self";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fingering yourself.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you greedily push your [pc.fingers] deep inside your [pc.pussy+].",
					
					"Pumping your [pc.fingers] in and out of your [pc.pussy+], you find yourself letting out a series of delighted [pc.moans] as you rhythmically finger yourself.",
					
					"Curling your [pc.fingers] up inside your [pc.pussy], you let out [pc.a_moan] as you start stroking your vaginal walls.",
					
					"Focusing on the pleasure you're giving yourself between your [pc.legs], you start pumping your [pc.fingers] in and out of your [pc.pussy+].");
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction SUB_PLAYER_SELF_FINGER_VAGINA_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PLAYER,
			SexPace.SUB_EAGER,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Eager fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly finger yourself.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you eagerly slam your [pc.fingers] deep inside your [pc.pussy+], before starting to desperately finger yourself.",
					
					"Enthusiastically pumping your [pc.fingers] in and out of your [pc.pussy+], you find yourself letting out a series of delighted [pc.moans] as you frantically finger yourself.",
					
					"Desperately curling your [pc.fingers] up inside your [pc.pussy], you let out [pc.a_moan] as you start eagerly grinding your fingertips up against your vaginal walls.",
					
					"Focusing on the pleasure you're giving yourself between your [pc.legs], you eagerly start slamming your [pc.fingers] in and out of your [pc.pussy+].");
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASOCHIST), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction PLAYER_SELF_FINGER_VAGINA_STOP_PENETRATION = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PLAYER) {
		
		@Override
		public String getActionTitle() {
			return "Stop fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Stop fingering yourself.";
		}

		@Override
		public String getDescription() {
			return "With [pc.a_groan+], you slide your fingers out of your [pc.pussy+].";
		}
	};
}
