package com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
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
public class PlayerSelfTailNipple {
	
	public static final SexAction PLAYER_SELF_TAIL_NIPPLE_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
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
					
					"You snake your [pc.tail] up to your [pc.breasts+], [pc.moaning] in delight as you force it deep into an inviting [pc.nipple(true)].",
					
					"Sliding the tip of your [pc.tail+] up to your [pc.breasts+], you thrust it deep inside an inviting [pc.nipple(true)], letting out [pc.a_moan+] as you start tail-fucking your own [pc.breasts].",
					
					"You eagerly thrust your [pc.tail+] deep into a needy [pc.nipple(true)], letting out a series of [pc.moans+] as you start tail-fucking your own [pc.breasts]."));
			
		
			switch (Main.game.getPlayer().getBreastStoredMilk()) {
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
		public String applyEffectsString() {
			return Main.game.getPlayer().incrementBreastStoredMilk(-10);
		}
		
	};
	
	public static final SexAction DOM_PLAYER_SELF_TAIL_NIPPLE_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDom(Main.game.getPlayer());
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
					
					"Slowly driving your [pc.tail] deep inside your [pc.nipple(true)], you let out a little whimper as you start gently sliding it in and out of your [pc.breast+].",
					
					"Focusing on pleasuring your fuckable [pc.breasts], you start gently pumping your [pc.tail] in and out of one of your [pc.nipples+].");
		}
		
	};
	
	public static final SexAction DOM_PLAYER_SELF_TAIL_NIPPLE_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDom(Main.game.getPlayer());
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
					
					"Driving your [pc.tail] deep inside your fuckable [pc.nipple(true)], you let out [pc.a_moan] as you start pumping it in and out of your [pc.breast+].",
					
					"Focusing on pleasuring your [pc.breasts+], you start pumping your [pc.tail] in and out of one of your [pc.nipples+].");
		}
		
	};
	
	public static final SexAction DOM_PLAYER_SELF_TAIL_NIPPLE_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDom(Main.game.getPlayer());
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
					"[pc.A_moan+] escapes from between your [pc.lips+] as you roughly slam your [pc.tail] deep inside your [pc.nipple+], before starting to rapidly fuck your [pc.breast(true)].",
					
					"Roughly pumping your [pc.tail] in and out of your [pc.nipple+], you find yourself letting out a series of delighted [pc.moans] as you rhythmically fuck your [pc.breast+].",
					
					"Forcefully driving your [pc.tail] deep inside your fuckable [pc.nipple(true)], you let out [pc.a_moan] as you start roughly grinding it in and out of your [pc.breast+].",
					
					"Focusing on pleasuring your [pc.breasts+], you start roughly slamming your [pc.tail] in and out of one of your [pc.nipples+].");
		}

	};
	
	public static final SexAction SUB_PLAYER_SELF_TAIL_NIPPLE_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDom(Main.game.getPlayer());
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
					
					"Driving your [pc.tail] deep inside your fuckable [pc.nipple(true)], you let out [pc.a_moan] as you start pumping it in and out of your [pc.breast+].",
					
					"Focusing on pleasuring your [pc.breasts+], you start pumping your [pc.tail] in and out of one of your [pc.nipples+].");
		}
		
	};
	
	public static final SexAction SUB_PLAYER_SELF_TAIL_NIPPLE_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDom(Main.game.getPlayer());
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
					"[pc.A_moan+] escapes from between your [pc.lips+] as you eagerly slam your [pc.tail] deep inside your [pc.nipple+], before starting to desperately fuck your [pc.breast(true)].",
					
					"Enthusiastically pumping your [pc.tail] in and out of your [pc.nipple+], you find yourself letting out a series of delighted [pc.moans] as you frantically fuck your [pc.breast+].",
					
					"Desperately driving your [pc.tail] deep inside your fuckable [pc.nipple(true)], you let out [pc.a_moan] as you start eagerly grinding it in and out of your [pc.breast+].",
					
					"Focusing on pleasuring your [pc.breasts+], you eagerly start slamming your [pc.tail] in and out of one of your [pc.nipples+].");
		}

	};
	
	public static final SexAction PLAYER_SELF_TAIL_NIPPLE_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
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
