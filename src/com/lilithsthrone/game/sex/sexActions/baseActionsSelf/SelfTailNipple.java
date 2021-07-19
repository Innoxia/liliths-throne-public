package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

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
 * @version 0.4.0.0
 * @author Innoxia
 */
public class SelfTailNipple {
	
	public static final SexAction SELF_TAIL_NIPPLE_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			return "Tail-fuck nipples (self)";
		}

		@Override
		public String getActionDescription() {
			return "Use [npc.her] [npc.tail+] to fuck [npc.her] own [npc.nipples+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Snaking [npc.her] [npc.tail] up to [npc.her] [npc.breasts+], [npc.name] [npc.verb(tease)] the tip over [npc.her] [npc.nipples], [npc.moaning] in delight before thrusting it deep inside.",
					"[npc.Name] [npc.verb(snake)] [npc.her] [npc.tail] up to [npc.her] [npc.breasts+], [npc.moaning] in delight as [npc.she] [npc.verb(force)] it deep into an inviting [npc.nipple(true)].",
					"Sliding the tip of [npc.her] [npc.tail+] up to [npc.her] [npc.breasts+], [npc.name] [npc.verb(thrust)] it deep inside an inviting [npc.nipple(true)], letting out [npc.a_moan+] as [npc.she] [npc.verb(start)] tail-fucking [npc.her] own [npc.breasts].",
					"[npc.Name] eagerly [npc.verb(thrust)] [npc.her] [npc.tail+] deep into a needy [npc.nipple(true)], letting out a series of [npc.moans+] as [npc.she] [npc.verb(start)] tail-fucking [npc.her] own [npc.breasts]."));
		
			switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(" A small trickle of [npc.milk] leaks out around [npc.her] [npc.tail].");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(" A small squirt of [npc.milk] leaks out around [npc.her] [npc.tail].");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(" A trickle of [npc.milk] runs out around [npc.her] [npc.tail].");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] starts to flow out around [npc.her] [npc.tail] and run down [npc.her] [npc.breasts].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" [npc.Milk] starts drooling out in a little stream around [npc.her] [npc.tail].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" [npc.Milk] starts pouring out in a constant stream to run down [npc.her] [npc.breasts].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" [npc.Milk] starts pouring out in a heavy flow to quickly soak [npc.her] [npc.breasts].");
					break;
				default:
					break;
			}

			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterPerformingAction().incrementBreastStoredMilk(-10);
		}
		
	};
	
	public static final SexAction DOM_SELF_TAIL_NIPPLE_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle nipple tail-fuck (self)";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc.her] [npc.nipples+] with [npc.her] [npc.tail].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] slowly [npc.verb(push)] [npc.her] [npc.tail] deep inside [npc.her] [npc.nipple+].",
					"Gently pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(fuck)] [npc.her] [npc.breast+].",
					"Slowly driving [npc.her] [npc.tail] deep inside [npc.her] [npc.nipple(true)], [npc.name] [npc.verb(let)] out a little whimper as [npc.she] [npc.verb(start)] gently sliding it in and out of [npc.her] [npc.breast+].",
					"Focusing on pleasuring [npc.her] fuckable [npc.breasts], [npc.name] [npc.verb(start)] gently pumping [npc.her] [npc.tail] in and out of one of [npc.her] [npc.nipples+].");
		}
		
	};
	
	public static final SexAction DOM_SELF_TAIL_NIPPLE_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Nipple tail-fucking (self)";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fucking [npc.her] [npc.nipples+] with [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] greedily [npc.verb(push)] [npc.her] [npc.tail] deep inside [npc.her] [npc.nipple+].",
					"Pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(fuck)] [npc.her] [npc.breast+].",
					"Driving [npc.her] [npc.tail] deep inside [npc.her] fuckable [npc.nipple(true)], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] pumping it in and out of [npc.her] [npc.breast+].",
					"Focusing on pleasuring [npc.her] [npc.breasts+], [npc.name] [npc.verb(start)] pumping [npc.her] [npc.tail] in and out of one of [npc.her] [npc.nipples+].");
		}
		
	};
	
	public static final SexAction DOM_SELF_TAIL_NIPPLE_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Rough nipple tail-fucking (self)";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc.her] [npc.nipples+] with [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] roughly [npc.verb(slam)] [npc.her] [npc.tail] deep inside [npc.her] [npc.nipple+], before starting to rapidly fuck [npc.her] [npc.breast(true)].",
					"Roughly pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(fuck)] [npc.her] [npc.breast+].",
					"Forcefully driving [npc.her] [npc.tail] deep inside [npc.her] fuckable [npc.nipple(true)], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] roughly grinding it in and out of [npc.her] [npc.breast+].",
					"Focusing on pleasuring [npc.her] [npc.breasts+], [npc.name] [npc.verb(start)] roughly slamming [npc.her] [npc.tail] in and out of one of [npc.her] [npc.nipples+].");
		}
	};
	
	public static final SexAction SUB_SELF_TAIL_NIPPLE_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Nipple tail-fucking (self)";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fucking [npc.her] [npc.nipples+] with [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] greedily [npc.verb(push)] [npc.her] [npc.tail] deep inside [npc.her] [npc.nipple+].",
					"Pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(fuck)] [npc.her] [npc.breast+].",
					"Driving [npc.her] [npc.tail] deep inside [npc.her] fuckable [npc.nipple(true)], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] pumping it in and out of [npc.her] [npc.breast+].",
					"Focusing on pleasuring [npc.her] [npc.breasts+], [npc.name] [npc.verb(start)] pumping [npc.her] [npc.tail] in and out of one of [npc.her] [npc.nipples+].");
		}
	};
	
	public static final SexAction SUB_SELF_TAIL_NIPPLE_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eager nipple tail-fucking (self)";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [npc.her] [npc.nipples+] with [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] eagerly [npc.verb(slam)] [npc.her] [npc.tail] deep inside [npc.her] [npc.nipple+], before starting to desperately fuck [npc.her] [npc.breast(true)].",
					"Enthusiastically pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] frantically [npc.verb(fuck)] [npc.her] [npc.breast+].",
					"Desperately driving [npc.her] [npc.tail] deep inside [npc.her] fuckable [npc.nipple(true)], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] eagerly grinding it in and out of [npc.her] [npc.breast+].",
					"Focusing on pleasuring [npc.her] [npc.breasts+], [npc.name] eagerly [npc.verb(start)] slamming [npc.her] [npc.tail] in and out of one of [npc.her] [npc.nipples+].");
		}
	};
	
	public static final SexAction SELF_TAIL_NIPPLE_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Stop nipple tail-fuck (self)";
		}

		@Override
		public String getActionDescription() {
			return "Stop fucking [npc.her] [npc.nipple+] with [npc.her] [npc.tail].";
		}

		@Override
		public String getDescription() {
			return "Letting out a satisfied [npc.moan], [npc.name] [npc.verb(slide)] [npc.her] [npc.tail+] out of [npc.her] [npc.nipple+].";
		}
	};
}
