package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.4.0.0
 * @author Innoxia
 */
public class SelfFingerVagina {
	
	public static final SexAction SELF_FINGER_VAGINA_SPREAD_PUSSY = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Spread pussy";
		}

		@Override
		public String getActionDescription() {
			return "Use your [npc.fingers] to spread your pussy.";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.ALL_FOURS)) {
				return (UtilText.returnStringAtRandom(
						"Reaching back between [npc.her] [npc.legs], [npc.name] [npc.verb(tease)] [npc.her] [npc.fingers] over the entrance to [npc.her] [npc.pussy+],"
								+ " before letting out [npc.a_moan+] as [npc.she] [npc.verb(use)] [npc.her] digits to spread out [npc.her] labia.",
						"[npc.Name] [npc.verb(probe)] [npc.her] [npc.fingers] back between [npc.her] [npc.legs], [npc.moaning] softly as [npc.she] [npc.verb(use)] two of [npc.her] digits to spread out [npc.her] [npc.pussy+].",
						"Sliding [npc.her] fingertips over [npc.her] [npc.pussy+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(shake)] [npc.her] ass a little, before using [npc.her] digits to spread out [npc.her] pussy lips.",
						"[npc.Name] eagerly [npc.verb(slide)] [npc.her] [npc.fingers] over [npc.her] needy [npc.pussy], [npc.moaning+] as [npc.she] [npc.verb(use)] [npc.her] digits to part [npc.her] soft folds."));
			} else {
				return (UtilText.returnStringAtRandom(
						"Reaching down between [npc.her] [npc.legs], [npc.name] [npc.verb(tease)] [npc.her] [npc.fingers] over the entrance to [npc.her] [npc.pussy+],"
								+ " before letting out [npc.a_moan+] as [npc.she] [npc.verb(use)] [npc.her] digits to spread out [npc.her] labia.",
						"[npc.Name] [npc.verb(probe)] [npc.her] [npc.fingers] back between [npc.her] [npc.legs], [npc.moaning] softly as [npc.she] [npc.verb(use)] two of [npc.her] digits to spread out [npc.her] [npc.pussy+].",
						"Sliding [npc.her] fingertips over [npc.her] [npc.pussy+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(use)] [npc.her] digits to spread out [npc.her] pussy lips.",
						"[npc.Name] eagerly [npc.verb(slide)] [npc.her] [npc.fingers] over [npc.her] needy [npc.pussy], [npc.moaning+] as [npc.she] [npc.verb(use)] [npc.her] digits to part [npc.her] soft folds."));
			}
		}

		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA);
		}
	};
	
	public static final SexAction SELF_FINGER_VAGINA_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "Finger [npc.herself]";
		}

		@Override
		public String getActionDescription() {
			return "Start fingering [npc.herself].";
		}

		@Override
		public String getDescription() {
			return (UtilText.returnStringAtRandom(
					"Reaching down between [npc.her] [npc.legs], [npc.name] [npc.verb(tease)] [npc.her] [npc.fingers] over the entrance to [npc.her] [npc.pussy+], before letting out [npc.a_moan+] as [npc.she] [npc.verb(push)] [npc.her] digits deep inside.",
					"[npc.Name] [npc.verb(probe)] [npc.her] fingers down between [npc.her] [npc.legs], [npc.moaning] softly as [npc.she] [npc.verb(push)] two of [npc.her] digits into [npc.her] inviting [npc.pussy+].",
					"Sliding [npc.her] fingertips over [npc.her] neglected [npc.pussy], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(push)] [npc.her] digits inside and [npc.verb(start)] fingering [npc.herself].",
					"[npc.Name] eagerly [npc.verb(push)] [npc.her] fingers into [npc.her] needy [npc.pussy], [npc.moaning+] as [npc.she] [npc.verb(curl)] [npc.her] digits up inside [npc.herself] and [npc.verb(start)] stroking in a 'come-hither' motion."));
		}
	};
	
	public static final SexAction DOM_SELF_FINGER_VAGINA_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Gently finger [npc.her] [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] slowly [npc.verb(push)] [npc.her] [npc.fingers] deep inside [npc.her] [npc.pussy+].",
					"Gently pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(finger)] [npc.herself].",
					"Curling [npc.her] [npc.fingers] up inside [npc.her] [npc.pussy], [npc.name] [npc.verb(let)] out a little whimper as [npc.she] [npc.verb(start)] stroking [npc.her] vaginal walls.",
					"Focusing on the pleasure [npc.sheIs] giving [npc.herself] between [npc.her] [npc.legs], [npc.name] [npc.verb(start)] gently pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+].");
		}
	};
	
	public static final SexAction DOM_SELF_FINGER_VAGINA_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Finger self";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fingering [npc.herself].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] greedily [npc.verb(push)] [npc.her] [npc.fingers] deep inside [npc.her] [npc.pussy+].",
					"Pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(finger)] [npc.herself].",
					"Curling [npc.her] [npc.fingers] up inside [npc.her] [npc.pussy], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] stroking [npc.her] vaginal walls.",
					"Focusing on the pleasure [npc.sheIs] giving [npc.herself] between [npc.her] [npc.legs], [npc.name] [npc.verb(start)] pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+].");
		}
	};
	
	public static final SexAction DOM_SELF_FINGER_VAGINA_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Rough fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Roughly finger [npc.herself].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] roughly [npc.verb(slam)] [npc.her] [npc.fingers] deep inside [npc.her] [npc.pussy+], before starting to roughly finger [npc.herself].",
					"Roughly pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] ruthlessly [npc.verb(finger)] [npc.herself].",
					"Forcefully curling [npc.her] [npc.fingers] up inside [npc.her] [npc.pussy], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] roughly grinding [npc.her] fingertips up against [npc.her] vaginal walls.",
					"Focusing on the pleasure [npc.sheIs] giving [npc.herself] between [npc.her] [npc.legs], [npc.name] [npc.verb(start)] roughly slamming [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+].");
		}
	};
	
	public static final SexAction SUB_SELF_FINGER_VAGINA_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {

		@Override
		public String getActionTitle() {
			return "Finger self";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fingering [npc.herself].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] greedily [npc.verb(push)] [npc.her] [npc.fingers] deep inside [npc.her] [npc.pussy+].",
					"Pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(finger)] [npc.herself].",
					"Curling [npc.her] [npc.fingers] up inside [npc.her] [npc.pussy], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] stroking [npc.her] vaginal walls.",
					"Focusing on the pleasure [npc.sheIs] giving [npc.herself] between [npc.her] [npc.legs], [npc.name] [npc.verb(start)] pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+].");
		}
	};
	
	public static final SexAction SUB_SELF_FINGER_VAGINA_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {

		@Override
		public String getActionTitle() {
			return "Eager fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly finger [npc.herself].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] eagerly [npc.verb(slam)] [npc.her] [npc.fingers] deep inside [npc.her] [npc.pussy+], before starting to desperately finger [npc.herself].",
					"Enthusiastically pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] frantically [npc.verb(finger)] [npc.herself].",
					"Desperately curling [npc.her] [npc.fingers] up inside [npc.her] [npc.pussy], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] eagerly grinding [npc.her] fingertips up against [npc.her] vaginal walls.",
					"Focusing on the pleasure [npc.sheIs] giving [npc.herself] between [npc.her] [npc.legs], [npc.name] eagerly [npc.verb(start)] slamming [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+].");
		}

	};
	
	public static final SexAction SELF_FINGER_VAGINA_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "Stop fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Stop fingering [npc.herself].";
		}

		@Override
		public String getDescription() {
			return "With [npc.a_groan+], [npc.name] [npc.verb(slide)] [npc.her] fingers out of [npc.her] [npc.pussy+].";
		}
	};
}
