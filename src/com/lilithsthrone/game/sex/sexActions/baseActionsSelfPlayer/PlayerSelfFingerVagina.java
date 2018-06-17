package com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.1.97
 * @author Innoxia
 */
public class PlayerSelfFingerVagina {
	
	public static final SexAction PLAYER_SELF_FINGER_VAGINA_SPREAD_PUSSY = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Main.game.getPlayer())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Spread pussy";
		}

		@Override
		public String getActionDescription() {
			return "Use your [pc.fingers] to spread your pussy.";
		}

		@Override
		public String getDescription() {
			if((Sex.getPosition()==SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.DOGGY_ON_ALL_FOURS)) {
				return (UtilText.returnStringAtRandom(
						"Reaching back between your [pc.legs], you tease your fingers over the entrance to your [pc.pussy+], before letting out [pc.a_moan+] as you use your digits to spread out your labia for [npc.name].",
						"You probe your fingers back between your [pc.legs], moaning softly as you use two of your digits to invitingly spread out your [pc.pussy+] and present yourself to [npc.name].",
						"Sliding your fingertips over your [pc.pussy+], you let out [pc.a_moan+] as you shake your ass a little, before using your digits to spread out your pussy lips.",
						"You eagerly slide your fingers over your needy [pc.pussy], [pc.moaning+] as you use your digits to part your soft folds and present yourself for penetration."));
			} else {
				return (UtilText.returnStringAtRandom(
						"Reaching down between your [pc.legs], you tease your fingers over the entrance to your [pc.pussy+], before letting out [pc.a_moan+] as you use your digits to spread out your labia.",
						"You probe your fingers down between your [pc.legs], moaning softly as you use two of your digits to invitingly spread out your [pc.pussy+].",
						"Sliding your fingertips over your [pc.pussy+], you let out [pc.a_moan+] as you use your digits to spread out your pussy lips.",
						"You eagerly slide your fingers over your needy [pc.pussy], [pc.moaning+] as you use your digits to part your soft folds and present yourself for penetration."));
			}
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Main.game.getPlayer(), SexAreaPenetration.FINGER, Main.game.getPlayer(), SexAreaOrifice.VAGINA);
		}
		
	};
	
	public static final SexAction PLAYER_SELF_FINGER_VAGINA_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
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
		
	};
	
	public static final SexAction DOM_PLAYER_SELF_FINGER_VAGINA_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
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
		
	};
	
	public static final SexAction DOM_PLAYER_SELF_FINGER_VAGINA_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
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
		
	};
	
	public static final SexAction DOM_PLAYER_SELF_FINGER_VAGINA_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
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

	};
	
	public static final SexAction SUB_PLAYER_SELF_FINGER_VAGINA_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
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
		
	};
	
	public static final SexAction SUB_PLAYER_SELF_FINGER_VAGINA_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
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

	};
	
	public static final SexAction PLAYER_SELF_FINGER_VAGINA_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
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
