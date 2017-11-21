package com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.79
 * @version 0.1.79
 * @author Innoxia
 */
public class PlayerSelfTailAnus {
	
	public static final SexAction PLAYER_SELF_TAIL_ANUS_PENETRATION = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.TAIL_PLAYER,
			OrificeType.ANUS_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Tail-peg (self)";
		}

		@Override
		public String getActionDescription() {
			return "Start fucking your own [pc.ass] with your [pc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Snaking your [pc.tail] up to your [pc.ass], you tease the tip over your [pc.asshole], [pc.moaning] in delight before thrusting it deep inside your [pc.asshole+].",
					
					"You snake your [pc.tail] up to your [pc.ass], [pc.moaning] in delight as you force it deep into your inviting [pc.asshole].",
					
					"Sliding the tip of your [pc.tail+] up to your neglected [pc.asshole], you suddenly thrust it deep inside, letting out [pc.a_moan+] as you start tail-fucking your own [pc.ass].",
					
					"You eagerly thrust your [pc.tail+] deep into your needy [pc.asshole], letting out a series of [pc.moans+] as you start tail-fucking your own [pc.ass].");

		}
		
	};
	
	public static final SexAction DOM_PLAYER_SELF_TAIL_ANUS_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.TAIL_PLAYER,
			OrificeType.ANUS_PLAYER,
			SexPace.DOM_GENTLE,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle tail-pegging (self)";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck your own [pc.ass] with your [pc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you slowly push your [pc.tail] deep inside your [pc.asshole+].",
					
					"Gently pumping your [pc.tail] in and out of your [pc.asshole+], you find yourself letting out a series of delighted [pc.moans] as you slowly fuck your own [pc.ass].",
					
					"Slowly driving your [pc.tail] deep inside your [pc.asshole], you let out a little whimper as you "+(Main.game.getPlayer().hasPenis()?"use it to gently start massaging your prostate.":"gently fuck your own [pc.ass+]."),
					
					"Focusing on pleasuring your [pc.ass+], you start gently pumping your [pc.tail] in and out of your [pc.asshole+].");
		}
		
	};
	
	public static final SexAction DOM_PLAYER_SELF_TAIL_ANUS_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.TAIL_PLAYER,
			OrificeType.ANUS_PLAYER,
			SexPace.DOM_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Tail-pegging (self)";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fucking your own [pc.ass] with your [pc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you greedily push your [pc.tail] deep inside your [pc.asshole+].",
					
					"Pumping your [pc.tail] in and out of your [pc.asshole+], you find yourself letting out a series of delighted [pc.moans] as you rhythmically fuck your own [pc.ass].",
					
					"Driving your [pc.tail] deep inside your [pc.asshole], you let out [pc.a_moan] as you "+(Main.game.getPlayer().hasPenis()?"use it to start massaging your prostate.":"steadily fuck your own [pc.ass+]."),
					
					"Focusing on pleasuring your [pc.ass+], you start pumping your [pc.tail] in and out of your [pc.asshole+].");
		}
		
	};
	
	public static final SexAction DOM_PLAYER_SELF_TAIL_ANUS_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.TAIL_PLAYER,
			OrificeType.ANUS_PLAYER,
			SexPace.DOM_ROUGH,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Rough tail-pegging (self)";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck your own [pc.ass] with your [pc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you roughly slam your [pc.tail] deep inside your [pc.asshole+], before starting to roughly fuck your own [pc.ass].",
					
					"Roughly pumping your [pc.tail] in and out of your [pc.asshole+], you find yourself letting out a series of delighted [pc.moans] as you ruthlessly fuck your own [pc.ass].",
					
					"Forcefully driving your [pc.tail] deep inside your [pc.asshole], you let out [pc.a_moan] as you "+(Main.game.getPlayer().hasPenis()?"start roughly grinding it up against your prostate.":"roughly fuck your own [pc.ass+]."),
					
					"Focusing on pleasuring your [pc.ass+], you start roughly slamming your [pc.tail] in and out of your [pc.asshole+].");
		}

	};
	
	public static final SexAction SUB_PLAYER_SELF_TAIL_ANUS_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.TAIL_PLAYER,
			OrificeType.ANUS_PLAYER,
			SexPace.SUB_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Tail-pegging (self)";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fucking your own [pc.ass] with your [pc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you greedily push your [pc.tail] deep inside your [pc.asshole+].",
					
					"Pumping your [pc.tail] in and out of your [pc.asshole+], you find yourself letting out a series of delighted [pc.moans] as you rhythmically fuck your own [pc.ass].",
					
					"Driving your [pc.tail] deep inside your [pc.asshole], you let out [pc.a_moan] as you "+(Main.game.getPlayer().hasPenis()?"use it to start massaging your prostate.":"steadily fuck your own [pc.ass+]."),
					
					"Focusing on pleasuring your [pc.ass+], you start pumping your [pc.tail] in and out of your [pc.asshole+].");
		}
		
	};
	
	public static final SexAction SUB_PLAYER_SELF_TAIL_ANUS_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.TAIL_PLAYER,
			OrificeType.ANUS_PLAYER,
			SexPace.SUB_EAGER,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Eager tail-pegging (self)";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly start fucking your own [pc.ass] with your [pc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you eagerly slam your [pc.tail] deep inside your [pc.asshole+], before starting to desperately fuck your own [pc.ass].",
					
					"Enthusiastically pumping your [pc.tail] in and out of your [pc.asshole+], you find yourself letting out a series of delighted [pc.moans] as you frantically fuck your own [pc.ass].",
					
					"Desperately driving your [pc.tail] deep inside your [pc.asshole], you let out [pc.a_moan] as you "+(Main.game.getPlayer().hasPenis()?"start eagerly grinding it up against your prostate.":"eagerly fuck your own [pc.ass+]."),
					
					"Focusing on pleasuring your [pc.ass+], you eagerly start slamming your [pc.tail] in and out of your [pc.asshole+].");
		}

	};
	
	public static final SexAction PLAYER_SELF_TAIL_ANUS_STOP_PENETRATION = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL_PLAYER,
			OrificeType.ANUS_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Stop tail-peg (self)";
		}

		@Override
		public String getActionDescription() {
			return "Stop fucking yourself with your [pc.tail].";
		}

		@Override
		public String getDescription() {
			return "With [pc.a_groan+], you slide your [pc.tail+] out of your [pc.asshole+].";
		}
	};
}
