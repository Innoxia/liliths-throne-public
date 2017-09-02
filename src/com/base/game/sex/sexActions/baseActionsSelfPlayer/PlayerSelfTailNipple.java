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
import com.base.main.Main;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;

/**
 * @since 0.1.79
 * @version 0.1.79
 * @author Innoxia
 */
public class PlayerSelfTailNipple {
	
	public static SexAction PLAYER_SELF_TAIL_NIPPLE_PENETRATION = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL_PLAYER,
			OrificeType.NIPPLE_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Tail-fuck nipples (self)";
		}

		@Override
		public String getActionDescription() {
			return "Use your [pc.tail+] to fuck your own [pc.nipples+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Snaking your [pc.tail] up to your [pc.breasts+], you tease the tip over your [pc.nipples], [pc.moaning] in delight before thrusting deep inside one of your [pc.nipples+].",
					
					"You snake your [pc.tail] up to your [pc.breasts+], [pc.moaning] in delight as you force it deep into an inviting [pc.nipple].",
					
					"Sliding the tip of your [pc.tail+] up to your [pc.breasts+], you thrust it deep inside an inviting [pc.nipple], letting out [pc.a_moan+] as you start tail-fucking your own [pc.breasts].",
					
					"You eagerly thrust your [pc.tail+] deep into a needy [pc.nipple], letting out a series of [pc.moans+] as you start tail-fucking your own [pc.breasts]."));
			
		
			switch (Main.game.getPlayer().getBreastLactation()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(" A small trickle of [pc.milk] leaks out around your [pc.tail].");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(" A small squirt of [pc.milk] leaks out around your [pc.tail].");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(" A trickle of [pc.milk] runs out around your [pc.tail].");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(" Your [pc.milk] starts to flow out around your [pc.tail] and run down your [pc.breasts].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" [pc.Milk] starts drooling out in a little stream around your [pc.tail].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" [pc.Milk] starts pouring out in a constant stream to run down your [pc.breasts].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" [pc.Milk] starts pouring out in a heavy flow to quickly soak your [pc.breasts].");
					break;
				default:
					break;
			}

			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction DOM_PLAYER_SELF_TAIL_NIPPLE_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL_PLAYER,
			OrificeType.NIPPLE_PLAYER,
			SexPace.DOM_GENTLE,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle nipple tail-fuck (self)";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck your [pc.nipples+] with your [pc.tail].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you slowly push your [pc.tail] deep inside your [pc.nipple+].",
					
					"Gently pumping your [pc.tail] in and out of your [pc.nipple+], you find yourself letting out a series of delighted [pc.moans] as you rhythmically fuck your [pc.breast+].",
					
					"Slowly driving your [pc.tail] deep inside your [pc.nipple], you let out a little whimper as you start gently sliding it in and out of your [pc.breast+].",
					
					"Focusing on pleasuring your fuckable [pc.breasts], you start gently pumping your [pc.tail] in and out of one of your [pc.nipples+].");
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction DOM_PLAYER_SELF_TAIL_NIPPLE_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL_PLAYER,
			OrificeType.NIPPLE_PLAYER,
			SexPace.DOM_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Nipple tail-fucking (self)";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fucking your [pc.nipples+] with your [pc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you greedily push your [pc.tail] deep inside your [pc.nipple+].",
					
					"Pumping your [pc.tail] in and out of your [pc.nipple+], you find yourself letting out a series of delighted [pc.moans] as you rhythmically fuck your [pc.breast+].",
					
					"Driving your [pc.tail] deep inside your fuckable [pc.nipple], you let out [pc.a_moan] as you start pumping it in and out of your [pc.breast+].",
					
					"Focusing on pleasuring your [pc.breasts+], you start pumping your [pc.tail] in and out of one of your [pc.nipples+].");
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction DOM_PLAYER_SELF_TAIL_NIPPLE_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL_PLAYER,
			OrificeType.NIPPLE_PLAYER,
			SexPace.DOM_ROUGH,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Rough nipple tail-fucking (self)";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck your [pc.nipples+] with your [pc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you roughly slam your [pc.tail] deep inside your [pc.nipple+], before starting to rapidly fuck your [pc.breast].",
					
					"Roughly pumping your [pc.tail] in and out of your [pc.nipple+], you find yourself letting out a series of delighted [pc.moans] as you rhythmically fuck your [pc.breast+].",
					
					"Forcefully driving your [pc.tail] deep inside your fuckable [pc.nipple], you let out [pc.a_moan] as you start roughly grinding it in and out of your [pc.breast+].",
					
					"Focusing on pleasuring your [pc.breasts+], you start roughly slamming your [pc.tail] in and out of one of your [pc.nipples+].");
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASOCHIST), new ListValue<>(Fetish.FETISH_BREASTS_SELF), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction SUB_PLAYER_SELF_TAIL_NIPPLE_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL_PLAYER,
			OrificeType.NIPPLE_PLAYER,
			SexPace.SUB_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Nipple tail-fucking (self)";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fucking your [pc.nipples+] with your [pc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you greedily push your [pc.tail] deep inside your [pc.nipple+].",
					
					"Pumping your [pc.tail] in and out of your [pc.nipple+], you find yourself letting out a series of delighted [pc.moans] as you rhythmically fuck your [pc.breast+].",
					
					"Driving your [pc.tail] deep inside your fuckable [pc.nipple], you let out [pc.a_moan] as you start pumping it in and out of your [pc.breast+].",
					
					"Focusing on pleasuring your [pc.breasts+], you start pumping your [pc.tail] in and out of one of your [pc.nipples+].");
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction SUB_PLAYER_SELF_TAIL_NIPPLE_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL_PLAYER,
			OrificeType.NIPPLE_PLAYER,
			SexPace.SUB_EAGER,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Eager nipple tail-fucking (self)";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck your [pc.nipples+] with your [pc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you eagerly slam your [pc.tail] deep inside your [pc.nipple+], before starting to desperately fuck your [pc.breast].",
					
					"Enthusiastically pumping your [pc.tail] in and out of your [pc.nipple+], you find yourself letting out a series of delighted [pc.moans] as you frantically fuck your [pc.breast+].",
					
					"Desperately driving your [pc.tail] deep inside your fuckable [pc.nipple], you let out [pc.a_moan] as you start eagerly grinding it in and out of your [pc.breast+].",
					
					"Focusing on pleasuring your [pc.breasts+], you eagerly start slamming your [pc.tail] in and out of one of your [pc.nipples+].");
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASOCHIST), new ListValue<>(Fetish.FETISH_BREASTS_SELF), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction PLAYER_SELF_TAIL_NIPPLE_STOP_PENETRATION = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL_PLAYER,
			OrificeType.NIPPLE_PLAYER) {
		
		@Override
		public String getActionTitle() {
			return "Stop nipple tail-fuck (self)";
		}

		@Override
		public String getActionDescription() {
			return "Stop fucking your [pc.nipple+] with your [pc.tail].";
		}

		@Override
		public String getDescription() {
			return "Letting out a satisfied [pc.moan], you slide your [pc.tail+] out of your [pc.nipple+].";
		}
	};
}
