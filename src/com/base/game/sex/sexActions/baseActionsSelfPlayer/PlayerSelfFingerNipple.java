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
public class PlayerSelfFingerNipple {
	
	
	public static SexAction PLAYER_PINCH_NIPPLES = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PLAYER,
			OrificeType.NIPPLE_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Pinch nipples (self)";
		}

		@Override
		public String getActionDescription() {
			return "Play with your nipples.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasBreasts();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"You reach up and start playing with your hard nipples, pinching and rubbing them as you moan with arousal.",
					
					"Your fingertips tease over your breasts, stopping to pinch and tug at your nipples as you moan and sigh in delight.",
					
					"You reach up to your breasts, and, with eager fingers, start to pinch and rub at your exposed nipples.",
					
					"Your nipples are just begging for some attention, and you reach up and start to pinch at them, whining in delight as [npc.name] smirks at you."));
			
			switch (Main.game.getPlayer().getBreastLactation()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(" A small trickle of [pc.milk] leaks out onto your fingertips.");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(" A small squirt of [pc.milk] leaks out onto your fingertips.");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(" A trickle of [pc.milk] runs out over your fingertips.");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(" Your [pc.milk] starts to flow out over your fingers and run down your [pc.breasts+].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" Your [pc.milk] starts drooling out in a little stream over your fingers.");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" Your [pc.milk] starts pouring out in a constant stream to run down your [pc.breasts+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" Your [pc.milk] starts pouring out in a heavy flow to quickly soak your [pc.breasts+].");
					break;
				default:
					break;
			}

			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	
	public static SexAction PLAYER_SELF_FINGER_NIPPLE_PENETRATION = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PLAYER,
			OrificeType.NIPPLE_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Finger nipples (self)";
		}

		@Override
		public String getActionDescription() {
			return "Sink your fingers into your [pc.nipples+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"You reach up, letting out a lewd moan as you eagerly sink your fingers into your fuckable nipples.",
					
					"Your fingertips tease over your breasts, circling around your nipples before greedily sinking inside.",
					
					"You moan and squeal as you start eagerly fingering your nipple-cunts.",
					
					"With a lewd cry, you sink your digits into your inviting nipple-cunts, panting heavily as you start eagerly fingering yourself."));
			
		
			switch (Main.game.getPlayer().getBreastLactation()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(" A small trickle of [pc.milk] leaks out around your fingertips.");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(" A small squirt of [pc.milk] leaks out around your fingertips.");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(" A trickle of [pc.milk] runs out around your fingertips.");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(" Your [pc.milk] starts to flow out around your fingers and run down your [pc.breasts+].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" Your [pc.milk] starts drooling out in a little stream around your fingers.");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" Your [pc.milk] starts pouring out in a constant stream to run down your [pc.breasts+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" Your [pc.milk] starts pouring out in a heavy flow to quickly soak your [pc.breasts+].");
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
	
	public static SexAction DOM_PLAYER_SELF_FINGER_NIPPLE_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PLAYER,
			OrificeType.NIPPLE_PLAYER,
			SexPace.DOM_GENTLE,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		};
		
		@Override
		public String getActionTitle() {
			return "Gentle nipple fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Gently finger your [pc.nipples+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you slowly push your [pc.fingers] deep inside your [pc.nipple+].",
					
					"Gently pumping your [pc.fingers] in and out of your [pc.nipple+], you find yourself letting out a series of delighted [pc.moans] as you rhythmically finger your [pc.breast+].",
					
					"Curling your [pc.fingers] up inside your [pc.nipple], you let out a little whimper as you start pumping your digits in and out of your [pc.breast+].",
					
					"Focusing on pleasuring your fuckable [pc.breasts], you start gently pumping your [pc.fingers] in and out of one of your [pc.nipples+].");
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction DOM_PLAYER_SELF_FINGER_NIPPLE_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PLAYER,
			OrificeType.NIPPLE_PLAYER,
			SexPace.DOM_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		};
		
		@Override
		public String getActionTitle() {
			return "Nipple fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fingering your [pc.nipples+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you greedily push your [pc.fingers] deep inside your [pc.nipple+].",
					
					"Pumping your [pc.fingers] in and out of your [pc.nipple+], you find yourself letting out a series of delighted [pc.moans] as you rhythmically finger your [pc.breast+].",
					
					"Curling your [pc.fingers] up inside your fuckable [pc.nipple], you let out [pc.a_moan] as you start pumping your digits in and out of your [pc.breast+].",
					
					"Focusing on pleasuring your [pc.breasts+], you start pumping your [pc.fingers] in and out of one of your [pc.nipples+].");
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction DOM_PLAYER_SELF_FINGER_NIPPLE_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PLAYER,
			OrificeType.NIPPLE_PLAYER,
			SexPace.DOM_ROUGH,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		};
		
		@Override
		public String getActionTitle() {
			return "Rough nipple-fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Roughly finger your [pc.nipples+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you roughly slam your [pc.fingers] deep inside your [pc.nipple+], before starting to rapidly finger your [pc.breast].",
					
					"Roughly pumping your [pc.fingers] in and out of your [pc.nipple+], you find yourself letting out a series of delighted [pc.moans] as you rhythmically finger your [pc.breast+].",
					
					"Forcefully curling your [pc.fingers] up inside your fuckable [pc.nipple], you let out [pc.a_moan] as you start roughly grinding your digits in and out of your [pc.breast+].",
					
					"Focusing on pleasuring your fuckable [pc.breasts+], you start roughly slamming your [pc.fingers] in and out of one of your [pc.nipples+].");
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASOCHIST), new ListValue<>(Fetish.FETISH_BREASTS_SELF), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction SUB_PLAYER_SELF_FINGER_NIPPLE_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PLAYER,
			OrificeType.NIPPLE_PLAYER,
			SexPace.SUB_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		};
		
		@Override
		public String getActionTitle() {
			return "Nipple-fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fingering your [pc.nipples+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you greedily push your [pc.fingers] deep inside your [pc.nipple+].",
					
					"Pumping your [pc.fingers] in and out of your [pc.nipple+], you find yourself letting out a series of delighted [pc.moans] as you rhythmically finger your [pc.breast+].",
					
					"Curling your [pc.fingers] up inside your fuckable [pc.nipple], you let out [pc.a_moan] as you start pumping your digits in and out of your [pc.breast+].",
					
					"Focusing on pleasuring your [pc.breasts+], you start pumping your [pc.fingers] in and out of one of your [pc.nipples+].");
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction SUB_PLAYER_SELF_FINGER_NIPPLE_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PLAYER,
			OrificeType.NIPPLE_PLAYER,
			SexPace.SUB_EAGER,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		};
		
		@Override
		public String getActionTitle() {
			return "Eager nipple-fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly finger your [pc.nipples+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you eagerly slam your [pc.fingers] deep inside your [pc.nipple+], before starting to desperately finger your [pc.breast].",
					
					"Enthusiastically pumping your [pc.fingers] in and out of your [pc.nipple+], you find yourself letting out a series of delighted [pc.moans] as you frantically finger your [pc.breast+].",
					
					"Desperately curling your [pc.fingers] up inside your fuckable [pc.nipple], you let out [pc.a_moan] as you start eagerly grinding your digits in and out of your [pc.breast+].",
					
					"Focusing on pleasuring your fuckable [pc.breasts+], you eagerly start slamming your [pc.fingers] in and out of one of your [pc.nipples+].");
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASOCHIST), new ListValue<>(Fetish.FETISH_BREASTS_SELF), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction PLAYER_SELF_FINGER_NIPPLE_STOP_PENETRATION = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PLAYER,
			OrificeType.NIPPLE_PLAYER) {
		
		@Override
		public String getActionTitle() {
			return "Stop fingering nipples (self)";
		}

		@Override
		public String getActionDescription() {
			return "Stop fingering your [pc.nipples+].";
		}

		@Override
		public String getDescription() {
			return "Letting out a satisfied [pc.moan], you slide your fingers out of your [pc.nipples+].";
		}
	};
}
