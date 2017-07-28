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
import com.base.main.Main;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;

/**
 * @since 0.1.79
 * @version 0.1.79
 * @author Innoxia
 */
public class PartnerSelfTailNipple {
	
	public static SexAction PARTNER_SELF_TAIL_NIPPLE_PENETRATION = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL_PARTNER,
			OrificeType.NIPPLE_PARTNER) {
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
					"Snaking [npc.her] [npc.tail] up to [npc.her] [npc.breasts+], [npc.name] teases the tip over [npc.her] [npc.nipples], [npc.moaning] in delight before thrusting it deep inside.",
					
					"[npc.Name] snakes [npc.her] [npc.tail] up to [npc.her] [npc.breasts+], [npc.moaning] in delight as [npc.she] forces it deep into an inviting [npc.nipple].",
					
					"Sliding the tip of [npc.her] [npc.tail+] up to [npc.her] [npc.breasts+], [npc.name] thrusts it deep inside an inviting [npc.nipple], letting out [npc.a_moan+] as [npc.she] starts tail-fucking [npc.her] own [npc.breasts].",
					
					"[npc.Name] eagerly thrusts [npc.her] [npc.tail+] deep into a needy [npc.nipple], letting out a series of [npc.moans+] as [npc.she] starts tail-fucking [npc.her] own [npc.breasts]."));
			
		
			switch (Main.game.getPlayer().getBreastLactation()) {
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
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction DOM_PARTNER_SELF_TAIL_NIPPLE_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL_PARTNER,
			OrificeType.NIPPLE_PARTNER,
			null,
			SexPace.DOM_GENTLE) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		};
		
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
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] slowly pushes [npc.her] [npc.tail] deep inside [npc.her] [npc.nipple+].",
					
					"Gently pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.nipple+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] rhythmically fucks [npc.her] [npc.breast+].",
					
					"Slowly driving [npc.her] [npc.tail] deep inside [npc.her] [npc.nipple], [npc.name] lets out a little whimper as [npc.she] starts gently sliding it in and out of [npc.her] [npc.breast+].",
					
					"Focusing on pleasuring [npc.her] fuckable [npc.breasts], [npc.name] starts gently pumping [npc.her] [npc.tail] in and out of one of [npc.her] [npc.nipples+].");
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction DOM_PARTNER_SELF_TAIL_NIPPLE_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL_PARTNER,
			OrificeType.NIPPLE_PARTNER,
			null,
			SexPace.DOM_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		};
		
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
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] greedily pushes [npc.her] [npc.tail] deep inside [npc.her] [npc.nipple+].",
					
					"Pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.nipple+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] rhythmically fucks [npc.her] [npc.breast+].",
					
					"Driving [npc.her] [npc.tail] deep inside [npc.her] fuckable [npc.nipple], [npc.name] lets out [npc.a_moan] as [npc.she] starts pumping it in and out of [npc.her] [npc.breast+].",
					
					"Focusing on pleasuring [npc.her] [npc.breasts+], [npc.name] starts pumping [npc.her] [npc.tail] in and out of one of [npc.her] [npc.nipples+].");
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction DOM_PARTNER_SELF_TAIL_NIPPLE_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL_PARTNER,
			OrificeType.NIPPLE_PARTNER,
			null,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		};
		
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
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] roughly slams [npc.her] [npc.tail] deep inside [npc.her] [npc.nipple+], before starting to rapidly fuck [npc.her] [npc.breast].",
					
					"Roughly pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.nipple+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] rhythmically fucks [npc.her] [npc.breast+].",
					
					"Forcefully driving [npc.her] [npc.tail] deep inside [npc.her] fuckable [npc.nipple], [npc.name] lets out [npc.a_moan] as [npc.she] starts roughly grinding it in and out of [npc.her] [npc.breast+].",
					
					"Focusing on pleasuring [npc.her] [npc.breasts+], [npc.name] starts roughly slamming [npc.her] [npc.tail] in and out of one of [npc.her] [npc.nipples+].");
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASOCHIST), new ListValue<>(Fetish.FETISH_BREASTS), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction SUB_PARTNER_SELF_TAIL_NIPPLE_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL_PARTNER,
			OrificeType.NIPPLE_PARTNER,
			null,
			SexPace.SUB_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		};
		
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
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] greedily pushes [npc.her] [npc.tail] deep inside [npc.her] [npc.nipple+].",
					
					"Pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.nipple+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] rhythmically fucks [npc.her] [npc.breast+].",
					
					"Driving [npc.her] [npc.tail] deep inside [npc.her] fuckable [npc.nipple], [npc.name] lets out [npc.a_moan] as [npc.she] starts pumping it in and out of [npc.her] [npc.breast+].",
					
					"Focusing on pleasuring [npc.her] [npc.breasts+], [npc.name] starts pumping [npc.her] [npc.tail] in and out of one of [npc.her] [npc.nipples+].");
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction SUB_PARTNER_SELF_TAIL_NIPPLE_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL_PARTNER,
			OrificeType.NIPPLE_PARTNER,
			null,
			SexPace.SUB_EAGER) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		};
		
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
					"[npc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as [npc.she] eagerly slams [npc.her] [npc.tail] deep inside [npc.her] [npc.nipple+], before starting to desperately fuck [npc.her] [npc.breast].",
					
					"Enthusiastically pumping [npc.her] [npc.tail] in and out of [npc.her] [npc.nipple+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] frantically fucks [npc.her] [npc.breast+].",
					
					"Desperately driving [npc.her] [npc.tail] deep inside [npc.her] fuckable [npc.nipple], [npc.name] lets out [npc.a_moan] as [npc.she] starts eagerly grinding it in and out of [npc.her] [npc.breast+].",
					
					"Focusing on pleasuring [npc.her] [npc.breasts+], [npc.name] eagerly starts slamming [npc.her] [npc.tail] in and out of one of [npc.her] [npc.nipples+].");
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASOCHIST), new ListValue<>(Fetish.FETISH_BREASTS), new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	public static SexAction PARTNER_SELF_TAIL_NIPPLE_STOP_PENETRATION = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL_PARTNER,
			OrificeType.NIPPLE_PARTNER) {
		
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
			return "Letting out a satisfied [npc.moan], [npc.name] slides [npc.her] [npc.tail+] out of [npc.her] [npc.nipple+].";
		}
	};
}
