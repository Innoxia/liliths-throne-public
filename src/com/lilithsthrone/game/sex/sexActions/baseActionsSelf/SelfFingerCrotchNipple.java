package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.1
 * @version 0.4.0.0
 * @author Innoxia
 */
public class SelfFingerCrotchNipple {
	
	public static final SexAction PINCH_NIPPLE_CROTCH = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.SELF) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedCrotchBoobs();
		}
		
		@Override
		public String getActionTitle() {
			return "Pinch crotch-nipples";
		}

		@Override
		public String getActionDescription() {
			return "Play with [npc.her] [npc.crotchNipples].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(reach)] down and [npc.verb(start)] playing with [npc.her] hard [npc.crotchNipples], pinching and rubbing them as [npc.she] [npc.moans] with arousal.",
					"[npc.NamePos] fingertips tease over [npc.her] [npc.crotchBoobs], stopping to pinch and tug at [npc.her] [npc.crotchNipples] as [npc.she] [npc.verb(moan)] and [npc.verb(sigh)] in delight.",
					"[npc.Name] [npc.verb(reach)] down to [npc.her] [npc.crotchBoobs], and, with eager fingers, [npc.verb(start)] to pinch and rub at [npc.her] exposed [npc.crotchNipples].",
					"[npc.NamePos] [npc.crotchNipples] are just begging for some attention, and [npc.she] [npc.verb(whine)] in delight as [npc.she] [npc.verb(reach)] down to start pinching them."));
			
			switch (Main.sex.getCharacterPerformingAction().getBreastCrotchStoredMilk()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(" A small trickle of [npc.crotchMilk] leaks out onto [npc.her] fingertips.");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(" A small squirt of [npc.crotchMilk] leaks out onto [npc.her] fingertips.");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(" A trickle of [npc.crotchMilk] runs out over [npc.her] fingertips.");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.crotchMilk] starts to flow out over [npc.her] fingers and run down [npc.her] [npc.crotchBoobs+].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.crotchMilk] starts drooling out in a little stream over [npc.her] fingers.");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.crotchMilk] starts pouring out in a constant stream to run down [npc.her] [npc.crotchBoobs+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.crotchMilk] starts pouring out in a heavy flow to quickly soak [npc.her] [npc.crotchBoobs+].");
					break;
				default:
					break;
			}

			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterPerformingAction().incrementBreastCrotchStoredMilk(-10);
		}
		
	};
	
	
	public static final SexAction SELF_FINGER_NIPPLE_CROTCH_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedCrotchBoobs();
		}

		@Override
		public String getActionTitle() {
			return "Finger crotch-nipples";
		}

		@Override
		public String getActionDescription() {
			return "Sink [npc.her] fingers into [npc.her] [npc.crotchNipples+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(reach)] down, letting out a lewd [npc.moan] as [npc.she] eagerly [npc.verb(sink)] [npc.her] fingers into [npc.her] fuckable [npc.crotchNipples].",
					"[npc.NamePos] fingertips [npc.verb(tease)] over [npc.her] [npc.crotchBoobs], circling around [npc.her] [npc.crotchNipples] before greedily sinking inside.",
					"[npc.Name] [npc.moan] and [npc.verb(squeal)] as [npc.she] [npc.verb(start)] eagerly fingering [npc.her] nipple-cunts.",
					"With a lewd cry, [npc.name] sink [npc.her] digits into [npc.her] inviting nipple-cunts, panting heavily as [npc.she] [npc.verb(start)] eagerly fingering [npc.herself]."));
		
			switch (Main.sex.getCharacterPerformingAction().getBreastCrotchStoredMilk()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(" A small trickle of [npc.crotchMilk] leaks out around [npc.her] fingertips.");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(" A small squirt of [npc.crotchMilk] leaks out around [npc.her] fingertips.");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(" A trickle of [npc.crotchMilk] runs out around [npc.her] fingertips.");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.crotchMilk] starts to flow out around [npc.her] fingers and run down [npc.her] [npc.crotchBoobs+].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.crotchMilk] starts drooling out in a little stream around [npc.her] fingers.");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.crotchMilk] starts pouring out in a constant stream to run down [npc.her] [npc.crotchBoobs+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.crotchMilk] starts pouring out in a heavy flow to quickly soak [npc.her] [npc.crotchBoobs+].");
					break;
				default:
					break;
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterPerformingAction().incrementBreastCrotchStoredMilk(-10);
		}
	};
	
	public static final SexAction DOM_SELF_FINGER_NIPPLE_CROTCH_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle nipple fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Gently finger [npc.her] [npc.crotchNipples+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.her] [npc.lips+] as [npc.she] slowly [npc.verb(push)] [npc.her] [npc.fingers] deep inside [npc.her] [npc.crotchNipple+].",
					"Gently pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.crotchNipple+], [npc.she] [npc.verb(find)] [npc.herself] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(finger)] [npc.her] [npc.crotchBoob+].",
					"Curling [npc.her] [npc.fingers] up inside [npc.her] [npc.crotchNipple], [npc.she] [npc.verb(let)] out a little whimper as [npc.she] [npc.verb(start)] pumping [npc.her] digits in and out of [npc.her] [npc.crotchBoob+].",
					"Focusing on pleasuring [npc.her] fuckable [npc.crotchBoobs], [npc.she] [npc.verb(start)] gently pumping [npc.her] [npc.fingers] in and out of one of [npc.her] [npc.crotchNipples+].");
		}
		
	};
	
	public static final SexAction DOM_SELF_FINGER_NIPPLE_CROTCH_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Nipple fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fingering [npc.her] [npc.crotchNipples+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.her] [npc.lips+] as [npc.she] greedily [npc.verb(push)] [npc.her] [npc.fingers] deep inside [npc.her] [npc.crotchNipple+].",
					"Pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.crotchNipple+], [npc.she] find [npc.herself] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(finger)] [npc.her] [npc.crotchBoob+].",
					"Curling [npc.her] [npc.fingers] up inside [npc.her] fuckable [npc.crotchNipple], [npc.she] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] pumping [npc.her] digits in and out of [npc.her] [npc.crotchBoob+].",
					"Focusing on pleasuring [npc.her] [npc.crotchBoobs+], [npc.she] [npc.verb(start)] pumping [npc.her] [npc.fingers] in and out of one of [npc.her] [npc.crotchNipples+].");
		}
	};
	
	public static final SexAction DOM_SELF_FINGER_NIPPLE_CROTCH_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Rough nipple-fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Roughly finger [npc.her] [npc.crotchNipples+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.her] [npc.lips+] as [npc.she] roughly [npc.verb(slam)] [npc.her] [npc.fingers] deep inside [npc.her] [npc.crotchNipple+], before starting to rapidly finger [npc.her] [npc.crotchBoob].",
					"Roughly pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.crotchNipple+], [npc.she] find [npc.herself] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(finger)] [npc.her] [npc.crotchBoob+].",
					"Forcefully curling [npc.her] [npc.fingers] up inside [npc.her] fuckable [npc.crotchNipple], [npc.she] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] roughly grinding [npc.her] digits in and out of [npc.her] [npc.crotchBoob+].",
					"Focusing on pleasuring [npc.her] fuckable [npc.crotchBoobs+], [npc.she] [npc.verb(start)] roughly slamming [npc.her] [npc.fingers] in and out of one of [npc.her] [npc.crotchNipples+].");
		}
	};
	
	public static final SexAction SUB_SELF_FINGER_NIPPLE_CROTCH_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Nipple-fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fingering [npc.her] [npc.crotchNipples+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.her] [npc.lips+] as [npc.she] greedily [npc.verb(push)] [npc.her] [npc.fingers] deep inside [npc.her] [npc.crotchNipple+].",
					"Pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.crotchNipple+], [npc.she] [npc.verb(find)] [npc.herself] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(finger)] [npc.her] [npc.crotchBoob+].",
					"Curling [npc.her] [npc.fingers] up inside [npc.her] fuckable [npc.crotchNipple], [npc.she] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] pumping [npc.her] digits in and out of [npc.her] [npc.crotchBoob+].",
					"Focusing on pleasuring [npc.her] [npc.crotchBoobs+], [npc.she] [npc.verb(start)] pumping [npc.her] [npc.fingers] in and out of one of [npc.her] [npc.crotchNipples+].");
		}
		
	};
	
	public static final SexAction SUB_SELF_FINGER_NIPPLE_CROTCH_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eager nipple-fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly finger [npc.her] [npc.crotchNipples+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.her] [npc.lips+] as [npc.she] eagerly [npc.verb(slam)] [npc.her] [npc.fingers] deep inside [npc.her] [npc.crotchNipple+], before starting to desperately finger [npc.her] [npc.crotchBoob].",
					"Enthusiastically pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.crotchNipple+], [npc.she] find [npc.herself] letting out a series of delighted [npc.moans] as [npc.she] frantically [npc.verb(finger)] [npc.her] [npc.crotchBoob+].",
					"Desperately curling [npc.her] [npc.fingers] up inside [npc.her] fuckable [npc.crotchNipple], [npc.she] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] eagerly grinding [npc.her] digits in and out of [npc.her] [npc.crotchBoob+].",
					"Focusing on pleasuring [npc.her] fuckable [npc.crotchBoobs+], [npc.she] eagerly [npc.verb(start)] slamming [npc.her] [npc.fingers] in and out of one of [npc.her] [npc.crotchNipples+].");
		}

	};
	
	public static final SexAction SELF_FINGER_NIPPLE_CROTCH_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "Stop fingering nipples (self)";
		}

		@Override
		public String getActionDescription() {
			return "Stop fingering [npc.her] [npc.crotchNipples+].";
		}

		@Override
		public String getDescription() {
			return "Letting out a satisfied [npc.moan], [npc.she] [npc.verb(slide)] [npc.her] fingers out of [npc.her] [npc.crotchNipples+].";
		}
	};
}
