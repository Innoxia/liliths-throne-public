package com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
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
public class PartnerSelfFingerNipple {
	
	
	public static final SexAction PARTNER_PINCH_NIPPLES = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Pinch nipples";
		}

		@Override
		public String getActionDescription() {
			return "Play with your nipples.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] reaches up and starts playing with [npc.her] hard [npc.nipples], pinching and rubbing them as [npc.she] moans with arousal.",
					
					"[npc.NamePos] fingertips tease over [npc.her] [npc.breasts+], stopping to pinch and tug at [npc.her] [npc.nipples+] as [npc.she] moans and sighs in delight.",
					
					"[npc.Name] reaches up to [npc.her] [npc.breasts+], and, with eager fingers, starts to pinch and rub at [npc.her] exposed [npc.nipples].",
					
					"With [npc.a_moan+], [npc.name] reaches up to [npc.her] [npc.nipples+], pinching and flicking them as [npc.she] continues to cry out in delight."));
			
			switch (Sex.getActivePartner().getBreastStoredMilk()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(" A small trickle of [npc.milk] leaks out around [npc.her] fingertips.");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(" A small squirt of [npc.milk] leaks out around [npc.her] fingertips.");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(" A trickle of [npc.milk] runs out around [npc.her] fingertips.");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] starts to flow out around [npc.her] fingers and run down [npc.her] [npc.breasts+].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" [npc.Milk] starts drooling out in a little stream around [npc.her] fingers.");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" [npc.Milk] starts pouring out in a constant stream to run down [npc.her] [npc.breasts+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" [npc.Milk] starts pouring out in a heavy flow to quickly soak [npc.her] [npc.breasts+].");
					break;
				default:
					break;
			}

			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Sex.getActivePartner().incrementBreastStoredMilk(-10);
		}

	};
	
	
	public static final SexAction PARTNER_SELF_FINGER_NIPPLE_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Finger nipples (self)";
		}

		@Override
		public String getActionDescription() {
			return "Sink [npc.her] fingers into [npc.her] [npc.nipples+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] reaches up, letting out a lewd moan as [npc.she] eagerly sinks [npc.her] fingers into [npc.her] fuckable nipples.",
					
					"[npc.NamePos] fingertips tease over [npc.her] breasts, circling around [npc.her] nipples before greedily sinking inside.",
					
					"[npc.Name] moans and squeals as [npc.she] starts eagerly fingering [npc.her] nipple-cunts.",
					
					"With a lewd cry, [npc.name] sinks [npc.her] digits into [npc.her] inviting nipple-cunts, panting heavily as [npc.she] start eagerly fingering [npc.herself]."));
			
		
			switch (Sex.getActivePartner().getBreastStoredMilk()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(" A small trickle of [npc.milk] leaks out around [npc.her] fingertips.");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(" A small squirt of [npc.milk] leaks out around [npc.her] fingertips.");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(" A trickle of [npc.milk] runs out around [npc.her] fingertips.");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] starts to flow out around [npc.her] fingers and run down [npc.her] [npc.breasts+].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] starts drooling out in a little stream around [npc.her] fingers.");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] starts pouring out in a constant stream to run down [npc.her] [npc.breasts+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] starts pouring out in a heavy flow to quickly soak [npc.her] [npc.breasts+].");
					break;
				default:
					break;
			}

			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Sex.getActivePartner().incrementBreastStoredMilk(-10);
		}
		
	};
	
	public static final SexAction DOM_PARTNER_SELF_FINGER_NIPPLE_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle nipple fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Gently finger [npc.her] [npc.nipples+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] slowly pushes [npc.her] [npc.fingers] deep inside [npc.her] [npc.nipple+].",
					
					"Gently pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.nipple+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] rhythmically fingers [npc.her] [npc.breast+].",
					
					"Curling [npc.her] [npc.fingers] up inside [npc.her] [npc.nipple], [npc.name] lets out a little whimper as [npc.she] start pumping [npc.her] digits in and out of [npc.her] [npc.breast+].",
					
					"Focusing on pleasuring [npc.her] fuckable [npc.breasts], [npc.name] starts gently pumping [npc.her] [npc.fingers] in and out of one of [npc.her] [npc.nipples+].");
		}
		
	};
	
	public static final SexAction DOM_PARTNER_SELF_FINGER_NIPPLE_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Nipple fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fingering [npc.her] [npc.nipples+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] greedily pushes [npc.her] [npc.fingers] deep inside [npc.her] [npc.nipple+].",
					
					"Pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.nipple+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] rhythmically fingers [npc.her] [npc.breast+].",
					
					"Curling [npc.her] [npc.fingers] up inside [npc.her] fuckable [npc.nipple], [npc.name] lets out [npc.a_moan] as [npc.she] starts pumping [npc.her] digits in and out of [npc.her] [npc.breast+].",
					
					"Focusing on pleasuring [npc.her] [npc.breasts+], [npc.name] starts pumping [npc.her] [npc.fingers] in and out of one of [npc.her] [npc.nipples+].");
		}
		
	};
	
	public static final SexAction DOM_PARTNER_SELF_FINGER_NIPPLE_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Rough nipple-fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Roughly finger [npc.her] [npc.nipples+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] roughly slams [npc.her] [npc.fingers] deep inside [npc.her] [npc.nipple+], before starting to rapidly finger [npc.her] [npc.breast].",
					
					"Roughly pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.nipple+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] rhythmically fingers [npc.her] [npc.breast+].",
					
					"Forcefully curling [npc.her] [npc.fingers] up inside [npc.her] fuckable [npc.nipple], [npc.name] lets out [npc.a_moan] as [npc.she] starts roughly grinding [npc.her] digits in and out of [npc.her] [npc.breast+].",
					
					"Focusing on pleasuring [npc.her] fuckable [npc.breasts+], [npc.name] starts roughly slamming [npc.her] [npc.fingers] in and out of one of [npc.her] [npc.nipples+].");
		}

	};
	
	public static final SexAction SUB_PARTNER_SELF_FINGER_NIPPLE_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Nipple-fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fingering [npc.her] [npc.nipples+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] greedily pushes [npc.her] [npc.fingers] deep inside [npc.her] [npc.nipple+].",
					
					"Pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.nipple+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] rhythmically fingers [npc.her] [npc.breast+].",
					
					"Curling [npc.her] [npc.fingers] up inside [npc.her] fuckable [npc.nipple], [npc.name] lets out [npc.a_moan] as [npc.she] starts pumping [npc.her] digits in and out of [npc.her] [npc.breast+].",
					
					"Focusing on pleasuring [npc.her] [npc.breasts+], [npc.name] starts pumping [npc.her] [npc.fingers] in and out of one of [npc.her] [npc.nipples+].");
		}
		
	};
	
	public static final SexAction SUB_PARTNER_SELF_FINGER_NIPPLE_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Eager nipple-fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly finger [npc.her] [npc.nipples+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] eagerly slams [npc.her] [npc.fingers] deep inside [npc.her] [npc.nipple+], before starting to desperately finger [npc.her] [npc.breast].",
					
					"Enthusiastically pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.nipple+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] frantically fingers [npc.her] [npc.breast+].",
					
					"Desperately curling [npc.her] [npc.fingers] up inside [npc.her] fuckable [npc.nipple], [npc.name] lets out [npc.a_moan] as [npc.she] starts eagerly grinding [npc.her] digits in and out of [npc.her] [npc.breast+].",
					
					"Focusing on pleasuring [npc.her] fuckable [npc.breasts+], [npc.name] eagerly starts slamming [npc.her] [npc.fingers] in and out of one of [npc.her] [npc.nipples+].");
		}

	};
	
	public static final SexAction PARTNER_SELF_FINGER_NIPPLE_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Stop fingering nipples (self)";
		}

		@Override
		public String getActionDescription() {
			return "Stop fingering [npc.her] [npc.nipples+].";
		}

		@Override
		public String getDescription() {
			return "Letting out a satisfied [npc.moan], [npc.name] slides [npc.her] fingers out of [npc.her] [npc.nipples+].";
		}
	};
}
