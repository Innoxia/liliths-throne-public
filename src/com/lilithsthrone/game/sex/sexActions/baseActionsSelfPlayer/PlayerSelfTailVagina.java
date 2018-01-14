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
public class PlayerSelfTailVagina {
	
	public static final SexAction PLAYER_SELF_TAIL_VAGINA_PENETRATION = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL_PLAYER,
			OrificeType.VAGINA_PLAYER) {
		
		@Override
		public String getActionTitle() {
			return "Tail-fuck (self)";
		}

		@Override
		public String getActionDescription() {
			return "Start fucking your [pc.pussy+] with your [pc.tail+].";
		}

		@Override
		public String getDescription() {
			return (UtilText.returnStringAtRandom(
					"Snaking your [pc.tail] around between your [pc.legs], you tease the tip over the entrance to your [pc.pussy], [pc.moaning] in delight before thrusting it deep inside your [pc.pussy+].",
					
					"You snake your [pc.tail] up between your legs, [pc.moaning] in delight as you force it deep into your inviting [pc.pussy].",
					
					"Sliding the tip of your [pc.tail+] between the folds of your neglected [pc.pussy], you suddenly thrust it deep inside, letting out [pc.a_moan+] as you start tail-fucking yourself.",
					
					"You eagerly thrust your [pc.tail+] deep into your needy [pc.pussy], letting out a series of [pc.moans+] as you start tail-fucking yourself."));
		}
		
	};
	
	public static final SexAction DOM_PLAYER_SELF_TAIL_VAGINA_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL_PLAYER,
			OrificeType.VAGINA_PLAYER,
			SexPace.DOM_GENTLE,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle tail-fuck (self)";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck your [pc.pussy+] with your [pc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you slowly push your [pc.tail] deep inside your [pc.pussy+].",
					
					"Gently pumping your [pc.tail] in and out of your [pc.pussy+], you find yourself letting out a series of delighted [pc.moans] as you rhythmically fuck yourself.",
					
					"Slowly driving your [pc.tail] deep inside your [pc.pussy], you let out a little whimper as you start gently fucking yourself.",

					"Focusing on pleasuring yourself, you start gently pumping your [pc.tail] in and out of your [pc.pussy+].");
		}
		
	};
	
	public static final SexAction DOM_PLAYER_SELF_TAIL_VAGINA_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL_PLAYER,
			OrificeType.VAGINA_PLAYER,
			SexPace.DOM_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Tail-fuck (self)";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fucking yourself with your [pc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you greedily push your [pc.tail] deep inside your [pc.pussy+].",
					
					"Pumping your [pc.tail] in and out of your [pc.pussy+], you find yourself letting out a series of delighted [pc.moans] as you rhythmically fuck yourself.",
					
					"Driving your [pc.tail] deep inside your [pc.pussy], you let out [pc.a_moan] as you start happily fucking yourself.",

					"Focusing on the pleasure you're giving yourself between your [pc.legs], you start pumping your [pc.tail] in and out of your [pc.pussy+].");
		}
		
	};
	
	public static final SexAction DOM_PLAYER_SELF_TAIL_VAGINA_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL_PLAYER,
			OrificeType.VAGINA_PLAYER,
			SexPace.DOM_ROUGH,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Rough tail-fuck (self)";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck yourself with your [pc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you roughly slam your [pc.tail] deep inside your [pc.pussy+], before starting to roughly fuck yourself.",
					
					"Roughly pumping your [pc.tail] in and out of your [pc.pussy+], you find yourself letting out a series of delighted [pc.moans] as you ruthlessly fuck yourself.",
					
					"Driving your [pc.tail] deep inside your [pc.pussy], you let out [pc.a_moan] as you start roughly grinding it up inside yourself.",

					"Focusing on the pleasure you're giving yourself between your [pc.legs], you start roughly slamming your [pc.tail] in and out of your [pc.pussy+].");
		}

	};
	
	public static final SexAction SUB_PLAYER_SELF_TAIL_VAGINA_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL_PLAYER,
			OrificeType.VAGINA_PLAYER,
			SexPace.SUB_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Tail-fuck (self)";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fucking yourself with your [pc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you greedily push your [pc.tail] deep inside your [pc.pussy+].",
					
					"Pumping your [pc.tail] in and out of your [pc.pussy+], you find yourself letting out a series of delighted [pc.moans] as you rhythmically fuck yourself.",
					
					"Driving your [pc.tail] deep inside your [pc.pussy], you let out [pc.a_moan] as you start happily fucking yourself.",

					"Focusing on the pleasure you're giving yourself between your [pc.legs], you start pumping your [pc.tail] in and out of your [pc.pussy+].");
		}
		
	};
	
	public static final SexAction SUB_PLAYER_SELF_TAIL_VAGINA_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL_PLAYER,
			OrificeType.VAGINA_PLAYER,
			SexPace.SUB_EAGER,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Eager tail-fuck (self)";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck yourself with your [pc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you eagerly slam your [pc.tail] deep inside your [pc.pussy+], before starting to desperately fuck yourself.",
					
					"Enthusiastically pumping your [pc.tail] in and out of your [pc.pussy+], you find yourself letting out a series of delighted [pc.moans] as you frantically fuck yourself.",
					
					"Desperately driving your [pc.tail] deep inside your [pc.pussy], you let out [pc.a_moan] as you start eagerly grinding it up inside yourself.",

					"Focusing on the pleasure you're giving yourself between your [pc.legs], you eagerly start slamming your [pc.tail] in and out of your [pc.pussy+].");
		}

	};
	
	public static final SexAction PLAYER_SELF_TAIL_VAGINA_STOP_PENETRATION = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL_PLAYER,
			OrificeType.VAGINA_PLAYER) {
		
		@Override
		public String getActionTitle() {
			return "Stop tail-fuck (self)";
		}

		@Override
		public String getActionDescription() {
			return "Stop fucking yourself with your [pc.tail].";
		}

		@Override
		public String getDescription() {
			return "Letting out [pc.a_moan], you slide your [pc.tail+] out of your [pc.pussy+].";
		}
	};
}
