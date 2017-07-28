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
public class PartnerSelfFingerAnus {
	
	public static SexAction PARTNER_SELF_FINGER_ANUS_PENETRATION = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.ANUS_PARTNER) {
		
		@Override
		public String getActionTitle() {
			return "Anal fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Start fingering [npc.her] ass.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Reaching around to [npc.her] [npc.ass], [npc.name] teases [npc.her] fingers over the entrance to [npc.her] [npc.asshole+], before pushing them inside and letting out [npc.a_moan+].",
					
					"[npc.Name] probes [npc.her] fingers down over [npc.her] [npc.ass], [npc.moaning+] as [npc.she] pushes two of [npc.her] digits into [npc.her] inviting [npc.asshole].",
					
					"Sliding [npc.her] fingertips over [npc.her] neglected [npc.asshole], [npc.name] lets out a [npc.groan+] as [npc.she] pushes [npc.her] digits inside.",
					
					"[npc.Name] eagerly pushes [npc.her] fingers into [npc.her] needy [npc.asshole], [npc.moaning+] as [npc.she] starts pumping [npc.her] digits in and out of [npc.her] [npc.ass].");
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction DOM_PARTNER_SELF_FINGER_ANUS_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.ANUS_PARTNER,
			null,
			SexPace.DOM_GENTLE) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		};
		
		@Override
		public String getActionTitle() {
			return "Gentle anal fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Gently finger [npc.her] [npc.asshole].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] slowly pushes [npc.her] [npc.fingers] deep inside [npc.her] [npc.asshole+].",
					
					"Gently pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.asshole+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] rhythmically fingers [npc.her] [npc.ass].",
					
					"Curling [npc.her] [npc.fingers] up inside [npc.her] [npc.asshole], [npc.name] lets out a little whimper as [npc.she] starts "
							+(Sex.getPartner().hasPenis()?"gently stroking [npc.her] prostate.":"gently fingering [npc.her] [npc.ass+]."),
					
					"Focusing on pleasuring [npc.her] [npc.ass+], [npc.name] starts gently pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.asshole+].");
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction DOM_PARTNER_SELF_FINGER_ANUS_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.ANUS_PARTNER,
			null,
			SexPace.DOM_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		};
		
		@Override
		public String getActionTitle() {
			return "Anal fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fingering [npc.her] [npc.ass].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] greedily pushes [npc.her] [npc.fingers] deep inside [npc.her] [npc.asshole+].",
					
					"Pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.asshole+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] rhythmically fingers [npc.her] [npc.ass].",
					
					"Curling [npc.her] [npc.fingers] up inside [npc.her] [npc.asshole], [npc.name] lets out [npc.a_moan] as [npc.she] starts "
							+(Sex.getPartner().hasPenis()?"stroking [npc.her] prostate.":"fingering [npc.her] [npc.ass+]."),
					
					"Focusing on pleasuring [npc.her] [npc.ass+], [npc.name] starts pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.asshole+].");
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction DOM_PARTNER_SELF_FINGER_ANUS_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.ANUS_PARTNER,
			null,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		};
		
		@Override
		public String getActionTitle() {
			return "Rough anal fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Roughly finger [npc.her] [npc.ass].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] roughly slams [npc.her] [npc.fingers] deep inside [npc.her] [npc.asshole+], before starting to roughly finger [npc.her] [npc.ass].",
					
					"Roughly pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.asshole+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] ruthlessly fingers [npc.her] own [npc.ass].",
					
					"Forcefully curling [npc.her] [npc.fingers] up inside [npc.her] [npc.asshole], [npc.name] lets out [npc.a_moan] as [npc.she] starts "
							+(Sex.getPartner().hasPenis()
									?"roughly grinding [npc.her] fingertips up against [npc.her] prostate."
									:"roughly grinding [npc.her] digits in and out of [npc.her] [npc.ass+]."),
					
					"Focusing on pleasuring [npc.her] [npc.ass+], [npc.name] starts roughly slamming [npc.her] [npc.fingers] in and out of [npc.her] [npc.asshole+].");
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASOCHIST), new ListValue<>(Fetish.FETISH_ANAL), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction SUB_PARTNER_SELF_FINGER_ANUS_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.ANUS_PARTNER,
			null,
			SexPace.SUB_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		};
		
		@Override
		public String getActionTitle() {
			return "Anal fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fingering [npc.her] [npc.ass].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] greedily pushes [npc.her] [npc.fingers] deep inside [npc.her] [npc.asshole+].",
					
					"Pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.asshole+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] rhythmically fingers [npc.her] [npc.ass].",
					
					"Curling [npc.her] [npc.fingers] up inside [npc.her] [npc.asshole], [npc.name] lets out [npc.a_moan] as [npc.she] starts "
							+(Sex.getPartner().hasPenis()?"stroking [npc.her] prostate.":"fingering [npc.her] [npc.ass+]."),
					
					"Focusing on pleasuring [npc.her] [npc.ass+], [npc.name] starts pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.asshole+].");
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction SUB_PARTNER_SELF_FINGER_ANUS_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.ANUS_PARTNER,
			null,
			SexPace.SUB_EAGER) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		};
		
		@Override
		public String getActionTitle() {
			return "Eager anal fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly finger [npc.her] [npc.ass].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] eagerly slams [npc.her] [npc.fingers] deep inside [npc.her] [npc.asshole+], before starting to desperately finger [npc.her] [npc.ass].",
					
					"Enthusiastically pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.asshole+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] frantically fingers [npc.her] own [npc.ass].",
					
					"Desperately curling [npc.her] [npc.fingers] up inside [npc.her] [npc.asshole], [npc.name] lets out [npc.a_moan] as [npc.she] starts "
							+(Sex.getPartner().hasPenis()
									?"eagerly grinding [npc.her] fingertips up against [npc.her] prostate."
									:"eagerly grinding [npc.her] digits in and out of [npc.her] [npc.ass+]."),
					
					"Focusing on pleasuring [npc.her] [npc.ass+], [npc.name] eagerly starts slamming [npc.her] [npc.fingers] in and out of [npc.her] [npc.asshole+].");
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASOCHIST), new ListValue<>(Fetish.FETISH_ANAL), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction PARTNER_SELF_FINGER_ANUS_STOP_PENETRATION = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PARTNER,
			OrificeType.ANUS_PARTNER) {
		
		@Override
		public String getActionTitle() {
			return "Stop anal fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Stop fingering [npc.her] ass.";
		}

		@Override
		public String getDescription() {
			return "With [npc.a_groan+], [npc.name] slides [npc.her] fingers out of [npc.her] [npc.asshole+].";
		}
	};
}
