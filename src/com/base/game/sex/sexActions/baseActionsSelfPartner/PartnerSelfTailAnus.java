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
public class PartnerSelfTailAnus {
	
	public static SexAction PARTNER_SELF_TAIL_ANUS_PENETRATION = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.TAIL_PARTNER,
			OrificeType.ANUS_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Tail-peg (self)";
		}

		@Override
		public String getActionDescription() {
			return "Start fucking [npc.her] own [npc.ass] with [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Snaking [npc.her] [npc.tail] up to [npc.her] [npc.ass], [npc.name] teases the tip over [npc.her] [npc.asshole+], [npc.moaning] in delight before thrusting it deep inside.",
					
					"[npc.Name] snakes [npc.her] [npc.tail] up to [npc.her] [npc.ass], [npc.moaning] in delight as [npc.she] forces it deep into [npc.her] inviting [npc.asshole].",
					
					"Sliding the tip of [npc.her] [npc.tail+] up to [npc.her] neglected [npc.asshole], [npc.name] suddenly thrusts it deep inside, letting out [npc.a_moan+] as [npc.she] starts tail-fucking [npc.her] own [npc.ass].",
					
					"[npc.Name] eagerly thrusts [npc.her] [npc.tail+] deep into [npc.her] needy [npc.asshole], letting out a series of [npc.moans+] as [npc.she] starts tail-fucking [npc.her] own [npc.ass].");

		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL));
		}
	};
	
	public static SexAction DOM_PARTNER_SELF_TAIL_ANUS_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.TAIL_PARTNER,
			OrificeType.ANUS_PARTNER,
			null,
			SexPace.DOM_GENTLE) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		};
		
		@Override
		public String getActionTitle() {
			return "Gentle tail-pegging (self)";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc.her] own [npc.ass] with [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] slowly pushes [npc.her] [npc.tail] deep inside [npc.her] [npc.asshole+].",
					
					"Gently pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.asshole+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] slowly fucks [npc.her] own [npc.ass].",
					
					"Slowly driving [npc.her] [npc.tail] deep inside [npc.her] [npc.asshole], [npc.name] lets out a little whimper as [npc.she] "
							+(Sex.getPartner().hasPenis()?"uses it to gently start massaging [npc.her] prostate.":"gently fucks [npc.her] own [npc.ass+]."),
					
					"Focusing on pleasuring [npc.her] [npc.ass+], [npc.name] starts gently pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.asshole+].");
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction DOM_PARTNER_SELF_TAIL_ANUS_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.TAIL_PARTNER,
			OrificeType.ANUS_PARTNER,
			null,
			SexPace.DOM_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		};
		
		@Override
		public String getActionTitle() {
			return "Tail-pegging (self)";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fucking [npc.her] own [npc.ass] with [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] greedily pushes [npc.her] [npc.tail] deep inside [npc.her] [npc.asshole+].",
					
					"Pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.asshole+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] rhythmically fucks [npc.her] own [npc.ass].",
					
					"Driving [npc.her] [npc.tail] deep inside [npc.her] [npc.asshole], [npc.name] lets out [npc.a_moan] as [npc.she] "
							+(Sex.getPartner().hasPenis()?"uses it to start massaging [npc.her] prostate.":"steadily fucks [npc.her] own [npc.ass+]."),
					
					"Focusing on pleasuring [npc.her] [npc.ass+], [npc.name] starts pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.asshole+].");
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction DOM_PARTNER_SELF_TAIL_ANUS_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.TAIL_PARTNER,
			OrificeType.ANUS_PARTNER,
			null,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		};
		
		@Override
		public String getActionTitle() {
			return "Rough tail-pegging (self)";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc.her] own [npc.ass] with [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] roughly slams [npc.her] [npc.tail] deep inside [npc.her] [npc.asshole+], before starting to roughly fuck [npc.her] own [npc.ass].",
					
					"Roughly pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.asshole+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] ruthlessly fucks [npc.her] own [npc.ass].",
					
					"Forcefully driving [npc.her] [npc.tail] deep inside [npc.her] [npc.asshole], [npc.name] lets out [npc.a_moan] as [npc.she] "
							+(Sex.getPartner().hasPenis()?"starts roughly grinding it up against [npc.her] prostate.":"roughly fucks [npc.her] own [npc.ass+]."),
					
					"Focusing on pleasuring [npc.her] [npc.ass+], [npc.name] starts roughly slamming [npc.her] [npc.tail] in and out of [npc.her] [npc.asshole+].");
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASOCHIST), new ListValue<>(Fetish.FETISH_ANAL), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction SUB_PARTNER_SELF_TAIL_ANUS_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.TAIL_PARTNER,
			OrificeType.ANUS_PARTNER,
			null,
			SexPace.SUB_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		};
		
		@Override
		public String getActionTitle() {
			return "Tail-pegging (self)";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fucking [npc.her] own [npc.ass] with [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] greedily pushes [npc.her] [npc.tail] deep inside [npc.her] [npc.asshole+].",
					
					"Pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.asshole+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] rhythmically fucks [npc.her] own [npc.ass].",
					
					"Driving [npc.her] [npc.tail] deep inside [npc.her] [npc.asshole], [npc.name] lets out [npc.a_moan] as [npc.she] "
							+(Sex.getPartner().hasPenis()?"uses it to start massaging [npc.her] prostate.":"steadily fucks [npc.her] own [npc.ass+]."),
					
					"Focusing on pleasuring [npc.her] [npc.ass+], [npc.name] starts pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.asshole+].");
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction SUB_PARTNER_SELF_TAIL_ANUS_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.TAIL_PARTNER,
			OrificeType.ANUS_PARTNER,
			null,
			SexPace.SUB_EAGER) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		};
		
		@Override
		public String getActionTitle() {
			return "Eager tail-pegging (self)";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly start fucking [npc.her] own [npc.ass] with [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] eagerly slams [npc.her] [npc.tail] deep inside [npc.her] [npc.asshole+], before starting to desperately fuck [npc.her] own [npc.ass].",
					
					"Enthusiastically pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.asshole+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] frantically fucks [npc.her] own [npc.ass].",
					
					"Desperately driving [npc.her] [npc.tail] deep inside [npc.her] [npc.asshole], [npc.name] lets out [npc.a_moan] as [npc.she] "
							+(Sex.getPartner().hasPenis()?"starts eagerly grinding it up against [npc.her] prostate.":"eagerly fucks [npc.her] own [npc.ass+]."),
					
					"Focusing on pleasuring [npc.her] [npc.ass+], [npc.name] eagerly starts slamming [npc.her] [npc.tail] in and out of [npc.her] [npc.asshole+].");
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASOCHIST), new ListValue<>(Fetish.FETISH_ANAL), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction PARTNER_SELF_TAIL_ANUS_STOP_PENETRATION = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL_PARTNER,
			OrificeType.ANUS_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Stop tail-peg (self)";
		}

		@Override
		public String getActionDescription() {
			return "Stop fucking [npc.her]self with [npc.her] [npc.tail].";
		}

		@Override
		public String getDescription() {
			return "With [npc.a_groan+], [npc.name] slides [npc.her] [npc.tail+] out of [npc.her] [npc.asshole+].";
		}
	};
}
