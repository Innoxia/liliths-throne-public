package com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;

/**
 * @since 0.1.79
 * @version 0.1.79
 * @author Innoxia
 */
public class PartnerSelfTailVagina {
	
	public static final SexAction PARTNER_SELF_TAIL_VAGINA_PENETRATION = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL_PARTNER,
			OrificeType.VAGINA_PARTNER) {
		
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
					"Snaking [npc.her] [npc.tail] around between [npc.her] [npc.legs], [npc.name] teases the tip over the entrance to [npc.her] [npc.pussy], [npc.moaning] in delight before thrusting it deep inside [npc.herself].",
					
					"[npc.Name] snakes [npc.her] [npc.tail] up between [npc.her] legs, [npc.moaning] in delight as [npc.she] forces it deep into [npc.her] inviting [npc.pussy].",
					
					"Sliding the tip of [npc.her] [npc.tail+] between the folds of [npc.her] neglected [npc.pussy], [npc.name] suddenly thrusts it deep inside, letting out [npc.a_moan+] as [npc.she] starts tail-fucking [npc.herself].",
					
					"[npc.Name] eagerly thrusts [npc.her] [npc.tail+] deep into [npc.her] needy [npc.pussy], letting out a series of [npc.moans+] as [npc.she] starts tail-fucking [npc.herself]."));
		}
		
	};
	
	public static final SexAction DOM_PARTNER_SELF_TAIL_VAGINA_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL_PARTNER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.DOM_GENTLE) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
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
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] slowly pushes [npc.her] [npc.tail] deep inside [npc.her] [npc.pussy+].",
					
					"Gently pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.pussy+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] rhythmically fucks [npc.herself].",
					
					"Slowly driving [npc.her] [npc.tail] deep inside [npc.her] [npc.pussy+], [npc.name] lets out a little whimper as [npc.she] starts gently fucking [npc.herself].",

					"Focusing on pleasuring [npc.herself], [npc.name] starts gently pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.pussy+].");
		}
		
	};
	
	public static final SexAction DOM_PARTNER_SELF_TAIL_VAGINA_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL_PARTNER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.DOM_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
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
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] greedily pushes [npc.her] [npc.tail] deep inside [npc.her] [npc.pussy+].",
					
					"Pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.pussy+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] rhythmically fucks [npc.herself].",
					
					"Driving [npc.her] [npc.tail] deep inside [npc.her] [npc.pussy], [npc.name] lets out [npc.a_moan] as [npc.she] starts happily fucking [npc.herself].",

					"Focusing on the pleasure [npc.she]'s giving [npc.herself] between [npc.her] [npc.legs], [npc.name] starts pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.pussy+].");
		}
		
	};
	
	public static final SexAction DOM_PARTNER_SELF_TAIL_VAGINA_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL_PARTNER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
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
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] roughly slams [npc.her] [npc.tail] deep inside [npc.her] [npc.pussy+], before starting to roughly fuck [npc.herself].",
					
					"Roughly pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.pussy+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] ruthlessly fucks [npc.herself].",
					
					"Driving [npc.her] [npc.tail] deep inside [npc.her] [npc.pussy], [npc.name] lets out [npc.a_moan] as [npc.she] starts roughly grinding it up inside [npc.herself].",

					"Focusing on the pleasure [npc.she]'s giving [npc.herself] between [npc.her] [npc.legs], [npc.name] starts roughly slamming [npc.her] [npc.tail] in and out of [npc.her] [npc.pussy+].");
		}

	};
	
	public static final SexAction SUB_PARTNER_SELF_TAIL_VAGINA_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL_PARTNER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.SUB_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
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
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] greedily pushes [npc.her] [npc.tail] deep inside [npc.her] [npc.pussy+].",
					
					"Pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.pussy+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] rhythmically fucks [npc.herself].",
					
					"Driving [npc.her] [npc.tail] deep inside [npc.her] [npc.pussy], [npc.name] lets out [npc.a_moan] as [npc.she] starts happily fucking [npc.herself].",

					"Focusing on the pleasure [npc.she]'s giving [npc.herself] between [npc.her] [npc.legs], [npc.name] starts pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.pussy+].");
		}
		
	};
	
	public static final SexAction SUB_PARTNER_SELF_TAIL_VAGINA_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL_PARTNER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.SUB_EAGER) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
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
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] eagerly slams [npc.her] [npc.tail] deep inside [npc.her] [npc.pussy+], before starting to desperately fuck [npc.herself].",
					
					"Enthusiastically pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.pussy+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] frantically fucks [npc.herself].",
					
					"Desperately driving [npc.her] [npc.tail] deep inside [npc.her] [npc.pussy], [npc.name] lets out [npc.a_moan] as [npc.she] starts eagerly grinding it up inside [npc.herself].",

					"Focusing on the pleasure [npc.she]'s giving [npc.herself] between [npc.her] [npc.legs], [npc.name] eagerly starts slamming [npc.her] [npc.tail] in and out of [npc.her] [npc.pussy+].");
		}

	};
	
	public static final SexAction PARTNER_SELF_TAIL_VAGINA_STOP_PENETRATION = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL_PARTNER,
			OrificeType.VAGINA_PARTNER) {
		
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
			return "Letting out [npc.a_moan], [npc.name] slides [npc.her] [npc.tail+] out of [npc.her] [npc.pussy+].";
		}
	};
}
