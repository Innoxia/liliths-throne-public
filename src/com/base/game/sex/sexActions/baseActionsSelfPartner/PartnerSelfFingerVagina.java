package com.base.game.sex.sexActions.baseActionsSelfPartner;

import java.util.List;

import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.effects.Fetish;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.sex.ArousalIncrease;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.Sex;
import com.base.game.sex.SexPace;
import com.base.game.sex.sexActions.SexAction;
import com.base.game.sex.sexActions.SexActionType;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;

/**
 * @since 0.1.79
 * @version 0.1.79
 * @author Innoxia
 */
public class PartnerSelfFingerVagina {
	
	public static final SexAction PARTNER_SELF_FINGER_VAGINA_PENETRATION = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PARTNER,
			OrificeType.VAGINA_PARTNER) {
		
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
					"Reaching down between [npc.her] [npc.legs], [npc.name] teases [npc.her] [npc.fingers] over the entrance to [npc.her] [npc.pussy+], before letting out [npc.a_moan+] as [npc.she] pushes [npc.her] digits deep inside.",
					
					"[npc.Name] probes [npc.her] fingers down between [npc.her] [npc.legs], moaning softly as [npc.she] pushes two of [npc.her] digits into [npc.her] inviting [npc.pussy+].",
					
					"Sliding [npc.her] fingertips over [npc.her] neglected [npc.pussy], [npc.name] lets out [npc.a_moan+] as [npc.she] pushes [npc.her] digits inside and start fingering [npc.herself].",
					
					"[npc.Name] eagerly pushes [npc.her] fingers into [npc.her] needy [npc.pussy], [npc.moaning+] as [npc.she] curls [npc.her] digits up inside [npc.herself] and starts stroking in a 'come-hither' motion."));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction DOM_PARTNER_SELF_FINGER_VAGINA_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PARTNER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.DOM_GENTLE) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
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
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] slowly pushes [npc.her] [npc.fingers] deep inside [npc.her] [npc.pussy+].",
					
					"Gently pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] rhythmically fingers [npc.herself].",
					
					"Curling [npc.her] [npc.fingers] up inside [npc.her] [npc.pussy], [npc.name] lets out a little whimper as [npc.she] starts stroking [npc.her] vaginal walls.",
					
					"Focusing on the pleasure [npc.she]'s giving [npc.herself] between [npc.her] [npc.legs], [npc.name] starts gently pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+].");
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction DOM_PARTNER_SELF_FINGER_VAGINA_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PARTNER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.DOM_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
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
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] greedily pushes [npc.her] [npc.fingers] deep inside [npc.her] [npc.pussy+].",
					
					"Pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] rhythmically fingers [npc.herself].",
					
					"Curling [npc.her] [npc.fingers] up inside [npc.her] [npc.pussy], [npc.name] lets out [npc.a_moan] as [npc.she] starts stroking [npc.her] vaginal walls.",
					
					"Focusing on the pleasure [npc.she]'s giving [npc.herself] between [npc.her] [npc.legs], [npc.name] starts pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+].");
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction DOM_PARTNER_SELF_FINGER_VAGINA_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PARTNER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
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
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] roughly slams [npc.her] [npc.fingers] deep inside [npc.her] [npc.pussy+], before starting to roughly finger [npc.herself].",
					
					"Roughly pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] ruthlessly fingers [npc.herself].",
					
					"Forcefully curling [npc.her] [npc.fingers] up inside [npc.her] [npc.pussy], [npc.name] lets out [npc.a_moan] as [npc.she] starts roughly grinding [npc.her] fingertips up against [npc.her] vaginal walls.",
					
					"Focusing on the pleasure [npc.she]'s giving [npc.herself] between [npc.her] [npc.legs], [npc.name] starts roughly slamming [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+].");
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASOCHIST), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction SUB_PARTNER_SELF_FINGER_VAGINA_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PARTNER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.SUB_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
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
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] greedily pushes [npc.her] [npc.fingers] deep inside [npc.her] [npc.pussy+].",
					
					"Pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] rhythmically fingers [npc.herself].",
					
					"Curling [npc.her] [npc.fingers] up inside [npc.her] [npc.pussy], [npc.name] lets out [npc.a_moan] as [npc.she] starts stroking [npc.her] vaginal walls.",
					
					"Focusing on the pleasure [npc.she]'s giving [npc.herself] between [npc.her] [npc.legs], [npc.name] starts pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+].");
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction SUB_PARTNER_SELF_FINGER_VAGINA_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PARTNER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.SUB_EAGER) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
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
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] eagerly slams [npc.her] [npc.fingers] deep inside [npc.her] [npc.pussy+], before starting to desperately finger [npc.herself].",
					
					"Enthusiastically pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] frantically fingers [npc.herself].",
					
					"Desperately curling [npc.her] [npc.fingers] up inside [npc.her] [npc.pussy], [npc.name] lets out [npc.a_moan] as [npc.she] starts eagerly grinding [npc.her] fingertips up against [npc.her] vaginal walls.",
					
					"Focusing on the pleasure [npc.she]'s giving [npc.herself] between [npc.her] [npc.legs], [npc.name] eagerly starts slamming [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+].");
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASOCHIST), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static final SexAction PARTNER_SELF_FINGER_VAGINA_STOP_PENETRATION = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PARTNER,
			OrificeType.VAGINA_PARTNER) {
		
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
			return "With [npc.a_groan+], [npc.name] slides [npc.her] fingers out of [npc.her] [npc.pussy+].";
		}
	};
}
