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
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.4.0.0
 * @author Innoxia
 */
public class SelfTailVagina {
	
	public static final SexAction SELF_TAIL_VAGINA_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			return "Tail-fuck (self)";
		}

		@Override
		public String getActionDescription() {
			return "Start fucking [npc.her] [npc.pussy+] with [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return (UtilText.returnStringAtRandom(
					"Snaking [npc.her] [npc.tail] around between [npc.her] [npc.legs], [npc.name] [npc.verb(tease)] the tip over the entrance to [npc.her] [npc.pussy], [npc.moaning] in delight before thrusting it deep inside [npc.herself].",
					"[npc.Name] [npc.verb(snake)] [npc.her] [npc.tail] up between [npc.her] legs, [npc.moaning] in delight as [npc.she] [npc.verb(force)] it deep into [npc.her] inviting [npc.pussy].",
					"Sliding the tip of [npc.her] [npc.tail+] between the folds of [npc.her] neglected [npc.pussy], [npc.name] suddenly [npc.verb(thrust)] it deep inside, letting out [npc.a_moan+] as [npc.she] [npc.verb(start)] tail-fucking [npc.herself].",
					"[npc.Name] eagerly [npc.verb(thrust)] [npc.her] [npc.tail+] deep into [npc.her] needy [npc.pussy], letting out a series of [npc.moans+] as [npc.she] [npc.verb(start)] tail-fucking [npc.herself]."));
		}
	};
	
	public static final SexAction DOM_SELF_TAIL_VAGINA_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {

		@Override
		public String getActionTitle() {
			return "Gentle tail-fuck (self)";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc.her] [npc.pussy+] with [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] slowly [npc.verb(push)] [npc.her] [npc.tail] deep inside [npc.her] [npc.pussy+].",
					"Gently pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(fuck)] [npc.herself].",
					"Slowly driving [npc.her] [npc.tail] deep inside [npc.her] [npc.pussy+], [npc.name] [npc.verb(let)] out a little whimper as [npc.she] [npc.verb(start)] gently fucking [npc.herself].",
					"Focusing on pleasuring [npc.herself], [npc.name] [npc.verb(start)] gently pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.pussy+].");
		}
		
	};
	
	public static final SexAction DOM_SELF_TAIL_VAGINA_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Tail-fuck (self)";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fucking [npc.herself] with [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] greedily [npc.verb(push)] [npc.her] [npc.tail] deep inside [npc.her] [npc.pussy+].",
					"Pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(fuck)] [npc.herself].",
					"Driving [npc.her] [npc.tail] deep inside [npc.her] [npc.pussy], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] happily fucking [npc.herself].",
					"Focusing on the pleasure [npc.sheIs] giving [npc.herself] between [npc.her] [npc.legs], [npc.name] [npc.verb(start)] pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.pussy+].");
		}
	};
	
	public static final SexAction DOM_SELF_TAIL_VAGINA_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Rough tail-fuck (self)";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc.herself] with [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] roughly [npc.verb(slam)] [npc.her] [npc.tail] deep inside [npc.her] [npc.pussy+], before starting to roughly fuck [npc.herself].",
					"Roughly pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] ruthlessly [npc.verb(fuck)] [npc.herself].",
					"Driving [npc.her] [npc.tail] deep inside [npc.her] [npc.pussy], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] roughly grinding it up inside [npc.herself].",
					"Focusing on the pleasure [npc.sheIs] giving [npc.herself] between [npc.her] [npc.legs], [npc.name] [npc.verb(start)] roughly slamming [npc.her] [npc.tail] in and out of [npc.her] [npc.pussy+].");
		}
	};
	
	public static final SexAction SUB_SELF_TAIL_VAGINA_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Tail-fuck (self)";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fucking [npc.herself] with [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] greedily [npc.verb(push)] [npc.her] [npc.tail] deep inside [npc.her] [npc.pussy+].",
					"Pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(fuck)] [npc.herself].",
					"Driving [npc.her] [npc.tail] deep inside [npc.her] [npc.pussy], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] happily fucking [npc.herself].",
					"Focusing on the pleasure [npc.sheIs] giving [npc.herself] between [npc.her] [npc.legs], [npc.name] [npc.verb(start)] pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.pussy+].");
		}
		
	};
	
	public static final SexAction SUB_SELF_TAIL_VAGINA_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eager tail-fuck (self)";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [npc.herself] with [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] eagerly [npc.verb(slam)] [npc.her] [npc.tail] deep inside [npc.her] [npc.pussy+], before starting to desperately fuck [npc.herself].",
					"Enthusiastically pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] frantically [npc.verb(fuck)] [npc.herself].",
					"Desperately driving [npc.her] [npc.tail] deep inside [npc.her] [npc.pussy], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] eagerly grinding it up inside [npc.herself].",
					"Focusing on the pleasure [npc.sheIs] giving [npc.herself] between [npc.her] [npc.legs], [npc.name] eagerly [npc.verb(start)] slamming [npc.her] [npc.tail] in and out of [npc.her] [npc.pussy+].");
		}
	};
	
	public static final SexAction SELF_TAIL_VAGINA_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "Stop tail-fuck (self)";
		}

		@Override
		public String getActionDescription() {
			return "Stop fucking [npc.herself] with [npc.her] [npc.tail].";
		}

		@Override
		public String getDescription() {
			return "Letting out [npc.a_moan], [npc.name] [npc.verb(slide)] [npc.her] [npc.tail+] out of [npc.her] [npc.pussy+].";
		}
	};
}
