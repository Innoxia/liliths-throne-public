package com.lilithsthrone.game.sex.sexActions.universal.sub;

import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;

/**
 * @since 0.1.83
 * @version 0.1.84
 * @author Innoxia
 */
public class SubCowgirl {
	
	public static String getPartnerTongueMouthDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		if (Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER) == PenetrationType.PENIS_PLAYER) {
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] sinks down onto your [pc.penis+], letting out a happy squeal as [npc.she] feels you sink deep inside [npc.her] [npc.asshole+]."
								+ " Bottoming out on your [pc.cock], [npc.she] leans down and grabs your head in both hands, before pulling you up into a passionate kiss.",
					"With [npc.a_moan+], [npc.name] slides down onto your [pc.penis+], leaning down into your [pc.breasts] and breathing in your [pc.scent] before pressing [npc.her] [npc.lips+] against yours.",
					"[npc.Name] lets your [pc.penis+] slide even deeper into [npc.her] [npc.asshole+] as [npc.she] leans down and pulls you into a desperate kiss.",
					"Leaning down, [npc.name] lets out [npc.a_moan+] as [npc.she] buries [npc.herself] on your [pc.penis+], before leaning forwards and pressing [npc.her] [npc.lips+] against yours."));
			
		} else if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER) == PenetrationType.PENIS_PLAYER) {
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] sinks down onto your [pc.penis+], letting out a happy squeal as [npc.she] feels you sink deep inside [npc.her] [npc.pussy+]."
								+ " Bottoming out on your [pc.cock], [npc.she] leans down and grabs your head in both hands, before pulling you up into a passionate kiss.",
					"With [npc.a_moan+], [npc.name] slides down onto your [pc.penis+], leaning down into your [pc.breasts] and breathing in your [pc.scent] before pressing [npc.her] [npc.lips+] against yours.",
					"[npc.Name] lets your [pc.penis+] slide even deeper into [npc.her] [npc.pussy+] as [npc.she] leans down and pulls you into a desperate kiss.",
					"Leaning down, [npc.name] lets out [npc.a_moan+] as [npc.she] buries [npc.herself] on your [pc.penis+], before leaning forwards and pressing [npc.her] [npc.lips+] against yours."));
			
		} else {
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] leans down, grabbing your head in both hands and pulling you up into a passionate kiss.",
					"With a grin, [npc.name] leans down into your [pc.breasts], breathing in your [pc.scent] before pressing [npc.her] [npc.lips+] against yours.",
					"Leaning down, [npc.name] lets out [npc.a_moan+] as [npc.she] presses [npc.her] [npc.lips+] against yours and starts to eagerly kiss you."));
		}

		switch(Sex.getSexPacePlayer()) {
			case SUB_EAGER:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" You happily push your [pc.tongue] deep into [npc.her] mouth, eagerly pressing your [pc.lips+] back against [npc.hers] and [pc.moaning] in delight as you greedily return [npc.her] display of affection.",
						" With an eager [pc.moan], you desperately grind back against [npc.herHim], muffling your [pc.moans] into [npc.her] mouth as you greedily thrust your [pc.tongue] past [npc.her] [npc.lips+].",
						" [pc.Moaning] in delight, you desperately grind yourself back against [npc.herHim], eagerly pressing your [pc.lips+] firmly against [npc.hers] as you happily push your [pc.tongue] into [npc.her] mouth."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" You push your [pc.tongue] into [npc.her] mouth, pressing your [pc.lips+] back against [npc.hers] as you eagerly return [npc.her] display of affection.",
						" With [pc.a_moan], you lean back against [npc.herHim], muffling your [pc.moans] into [npc.her] mouth as you eagerly thrust your [pc.tongue] past [npc.her] [npc.lips+].",
						" [pc.Moaning] in delight, you lean back against [npc.herHim], pressing your [pc.lips+] against [npc.hers] as you happily slide your [pc.tongue] into [npc.her] mouth."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" You try to pull away, [pc.sobbing] and squirming in discomfort as [npc.she] forces [npc.herself] on you.",
						" Your [pc.sob+] is muffled into [npc.her] mouth as you try to pull away, squirming in discomfort as [npc.name] forces [npc.herself] on you.",
						" With [pc.a_sob+], you try, in vain, to pull away from [npc.herHim], protesting and squirming in discomfort as [npc.she] forces [npc.her] [npc.tongue] past your reluctant [pc.lips]."));
				break;
			default:
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}
	
	public static String getPartnerStartingVaginalPenetrationDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		switch(Sex.getSexPacePartner()) {
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] reaches down to take hold of your [pc.cock+], before shuffling around to get into a good position."
								+ " After taking a moment to tease the [pc.cockHead+] between [npc.her] outer labia, [npc.she] lets out a little [npc.moan] as [npc.she] slowly drops down and penetrates [npc.her] [npc.pussy+] on your [pc.cock+].",
						"Shuffling around into a better position, [npc.name] lines the [pc.cockHead+] of your [pc.cock+] up between [npc.her] [npc.legs+], and with a slow, steady movement, [npc.she] gently drops down,"
								+ " penetrating [npc.her] [npc.pussy+] on your [pc.cock+]."));
				break;
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] reaches down to take a firm hold of your [pc.cock+], before shuffling around to get into a good position."
								+ " After taking a moment to tease the [pc.cockHead+] between [npc.her] outer labia, [npc.she] lets out [npc.a_moan+] as [npc.she] eagerly drops down and penetrates [npc.her] [npc.pussy+] on your [pc.cock+].",
						"Shuffling around into a better position, [npc.name] lines the [pc.cockHead+] of your [pc.cock+] up between [npc.her] [npc.legs+], and with a single movement, [npc.she] eagerly drops down,"
								+ " penetrating [npc.her] [npc.pussy+] on your [pc.cock+]."));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] reaches down and roughly grabs your [pc.cock+], before shuffling around to get into a good position."
								+ " After taking a moment to tease the [pc.cockHead+] between [npc.her] outer labia, [npc.she] lets out [npc.a_moan+] as [npc.she] forcefully drops down and penetrates [npc.her] [npc.pussy+] on your [pc.cock+].",
						"Shuffling around into a better position, [npc.name] lines the [pc.cockHead+] of your [pc.cock+] up between [npc.her] [npc.legs+], and with a single movement, [npc.she] roughly drops down,"
								+ " penetrating [npc.her] [npc.pussy+] on your [pc.cock+]."));
				break;
			default:
				break;
		}
		switch(Sex.getSexPacePlayer()) {
			case SUB_EAGER:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" You let out [pc.a_moan+] as you enter [npc.herHim], eagerly bucking your [pc.hips] up as you start fucking [npc.her] [npc.pussy+].",
						" With [pc.a_moan+], you eagerly thrust your [pc.hips] up into [npc.her] groin, sinking your [pc.cock+] into [npc.her] [npc.pussy+] as you start energetically fucking [npc.herHim]."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" You let out [pc.a_moan+] as you enter [npc.herHim], bucking your [pc.hips] up as you start fucking [npc.her] [npc.pussy+].",
						" With [pc.a_moan+], you thrust your [pc.hips] up into [npc.her] groin, sinking your [pc.cock+] into [npc.her] [npc.pussy+] as you start fucking [npc.herHim]."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" You let out [pc.a_sob+] as [npc.she] forces your [pc.cock] inside of [npc.herHim], and, struggling against [npc.herHim] in vain, you desperately try to push [npc.herHim] off of you.",
						" With [pc.a_sob+], you struggle against [npc.name] as [npc.she] forces your [pc.cock] deep into [npc.her] [npc.pussy+]."));
				break;
			default:
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}
	
	public static String getPartnerRidingCockGentle() {
		return UtilText.returnStringAtRandom(
				"Using [npc.her] legs, [npc.name] slowly lifts [npc.herself] up and down, sliding your [pc.penis+] in and out of [npc.her] [npc.pussy+] as [npc.she] [npc.moansVerb+] out loud.",
				"Leaning forwards, [npc.name] uses [npc.her] [npc.hands] to support some of [npc.her] weight as [npc.she] slowly slides up and down on your [pc.penis+]."
						+ " As [npc.she] lowers [npc.her] [npc.face+] towards you, [npc.she] gets a waft of your [pc.scent], and [npc.she] bites [npc.her] [npc.lip] as [npc.she] breathes in the intoxicating aroma.",
				"Lowering [npc.her] [npc.hands] to [npc.her] knees, [npc.name] uses [npc.her] [npc.legs] to slowly slide [npc.herself] up and down, letting out soft [npc.moans] as [npc.she] impales [npc.herself] on your [pc.cock+].",
				"[npc.Name] slowly lifts [npc.herself] up before sliding back down, letting out a soft [npc.moan] as [npc.she] spears [npc.her] [npc.pussy+] on your [pc.penis+].");
	}
	
	public static String getPartnerRidingCockNormal() {
		return UtilText.returnStringAtRandom(
				"Using [npc.her] legs, [npc.name] lifts [npc.herself] up and down, sliding your [pc.penis+] in and out of [npc.her] [npc.pussy+] as [npc.she] [npc.moansVerb+] out loud.",
				"Leaning forwards, [npc.name] uses [npc.her] [npc.hands] to support some of [npc.her] weight as [npc.she] eagerly slides up and down on your [pc.penis+]."
						+ " As [npc.she] lowers [npc.her] [npc.face+] towards you, [npc.she] gets a waft of your [pc.scent], and [npc.she] bites [npc.her] [npc.lip] as [npc.she] breathes in the intoxicating aroma.",
				"Lowering [npc.her] [npc.hands] to [npc.her] knees, [npc.name] uses [npc.her] [npc.legs] to enthusiastically slide [npc.herself] up and down, letting out [npc.moans+] as [npc.she] impales [npc.herself] on your [pc.cock+].",
				"[npc.Name] lifts [npc.herself] up before greedily sliding back down, letting out [npc.a_moan+] as [npc.she] spears [npc.her] [npc.pussy+] on your [pc.penis+].");
	}
	
	public static String getPartnerRidingCockRough() {
		return UtilText.returnStringAtRandom(
				"Using [npc.her] legs, [npc.name] rapidly bounces [npc.herself] up and down, slamming your [pc.penis+] in and out of [npc.her] [npc.pussy+] as [npc.she] [npc.moansVerb+] out loud.",
				"Leaning forwards, [npc.name] uses [npc.her] [npc.hands] to support some of [npc.her] weight as [npc.she] starts rapidly bucking up and down on your [pc.penis+]."
						+ " As [npc.she] lowers [npc.her] [npc.face+] towards you, [npc.she] gets a waft of your [pc.scent], and [npc.she] bites [npc.her] [npc.lip] as [npc.she] breathes in the intoxicating aroma.",
				"Placing [npc.her] [npc.hands] down on the floor behind [npc.herHim] for support, [npc.name] starts rapidly bouncing [npc.herself] up and down, letting out shuddering, [npc.moans+] as [npc.she] impales [npc.herself] on your [pc.cock+].",
				"Grinning down at you, [npc.name] starts enthusiastically bouncing up and down, letting out [npc.a_moan+] as [npc.she] spears [npc.her] [npc.pussy+] on your [pc.penis+].");
	}
	
	public static String getPartnerStoppingVaginalPenetrationDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		switch(Sex.getSexPacePartner()) {
			case DOM_ROUGH:
				UtilText.nodeContentSB.append("Reaching down, [npc.name] roughly grabs the base of your [pc.cock], before lifting [npc.herself] up and allowing your [pc.cock+] to slide out of [npc.her] [npc.pussy+].");
				break;
			default:
				UtilText.nodeContentSB.append("Reaching down, [npc.name] takes hold of the base of your [pc.cock], before lifting [npc.herself] up and allowing your [pc.cock+] to slide out of [npc.her] [npc.pussy+].");
				break;
		}
		
		switch(Sex.getSexPacePlayer()) {
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" You let out a relieved sigh, which soon turns into [pc.a_sob+] as you realise that [npc.she] isn't finished with you yet.",
						" With [pc.a_sob+], you continue to protest and struggle against [npc.herHim] as [npc.she] holds you firmly in place."));
				break;
			default:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" You let out [pc.a_moan+] as [npc.she] stops riding you.",
						" [pc.A_moan+] escapes from between your [pc.lips+], betraying your desire to continue fucking [npc.her] [npc.pussy+]."));
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}
	
	public static String getPartnerStartingAnalPenetrationDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		switch(Sex.getSexPacePartner()) {
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] reaches down to take hold of your [pc.cock+], before shuffling around to get into a good position."
								+ " After taking a moment to tease the [pc.cockHead+] over [npc.her] [npc.asshole+], [npc.she] lets out a little [npc.moan] as [npc.she] slowly drops down and penetrates [npc.her] [npc.ass+] on your [pc.cock+].",
						"Shuffling around into a better position, [npc.name] lines the [pc.cockHead+] of your [pc.cock+] up between [npc.her] ass cheeks, and with a slow, steady movement, [npc.she] gently drops down,"
								+ " penetrating [npc.her] [npc.asshole+] on your [pc.cock+]."));
				break;
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] reaches down to take a firm hold of your [pc.cock+], before shuffling around to get into a good position."
								+ " After taking a moment to tease the [pc.cockHead+] over [npc.her] [npc.asshole+], [npc.she] lets out [npc.a_moan+] as [npc.she] eagerly drops down and penetrates [npc.her] [npc.ass+] on your [pc.cock+].",
						"Shuffling around into a better position, [npc.name] lines the [pc.cockHead+] of your [pc.cock+] up between [npc.her] ass cheeks, and with a single movement, [npc.she] eagerly drops down,"
								+ " penetrating [npc.her] [npc.asshole+] on your [pc.cock+]."));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] reaches down and roughly grabs your [pc.cock+], before shuffling around to get into a good position."
								+ " After taking a moment to tease the [pc.cockHead+] over [npc.her] [npc.asshole+], [npc.she] lets out [npc.a_moan+] as [npc.she] forcefully drops down and penetrates [npc.her] [npc.ass+] on your [pc.cock+].",
						"Shuffling around into a better position, [npc.name] lines the [pc.cockHead+] of your [pc.cock+] up between [npc.her] ass cheeks, and with a single movement, [npc.she] roughly drops down,"
								+ " penetrating [npc.her] [npc.asshole+] on your [pc.cock+]."));
				break;
			default:
				break;
		}
		switch(Sex.getSexPacePlayer()) {
			case SUB_EAGER:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" You let out [pc.a_moan+] as you enter [npc.herHim], eagerly bucking your [pc.hips] up as you start fucking [npc.her] [npc.asshole+].",
						" With [pc.a_moan+], you eagerly thrust your [pc.hips] up into [npc.her] [npc.ass], sinking your [pc.cock+] into [npc.her] [npc.asshole+] as you start energetically fucking [npc.herHim]."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" You let out [pc.a_moan+] as you enter [npc.herHim], bucking your [pc.hips] up as you start fucking [npc.her] [npc.asshole+].",
						" With [pc.a_moan+], you thrust your [pc.hips] up into [npc.her] [npc.ass], sinking your [pc.cock+] into [npc.her] [npc.asshole+] as you start fucking [npc.herHim]."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" You let out [pc.a_sob+] as [npc.she] forces your [pc.cock] inside of [npc.herHim], and, struggling against [npc.herHim] in vain, you desperately try to push [npc.herHim] off of you.",
						" With [pc.a_sob+], you struggle against [npc.name] as [npc.she] forces your [pc.cock] deep into [npc.her] [npc.asshole+]."));
				break;
			default:
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}
	
	public static String getPartnerRidingCockAnallyGentle() {
		return UtilText.returnStringAtRandom(
				"Using [npc.her] legs, [npc.name] slowly lifts [npc.herself] up and down, sliding your [pc.penis+] in and out of [npc.her] [npc.asshole+] as [npc.she] [npc.moansVerb+] out loud.",
				"Leaning forwards, [npc.name] uses [npc.her] [npc.hands] to support some of [npc.her] weight as [npc.she] slowly slides up and down on your [pc.penis+]."
						+ " As [npc.she] lowers [npc.her] [npc.face+] towards you, [npc.she] gets a waft of your [pc.scent], and [npc.she] bites [npc.her] [npc.lip] as [npc.she] breathes in the intoxicating aroma.",
				"Lowering [npc.her] [npc.hands] to [npc.her] knees, [npc.name] uses [npc.her] [npc.legs] to slowly slide [npc.herself] up and down, letting out soft [npc.moans] as [npc.she] impales [npc.herself] on your [pc.cock+].",
				"[npc.Name] slowly lifts [npc.herself] up before sliding back down, letting out a soft [npc.moan] as [npc.she] spears [npc.her] [npc.asshole+] on your [pc.penis+].");
	}
	
	public static String getPartnerRidingCockAnallyNormal() {
		return UtilText.returnStringAtRandom(
				"Using [npc.her] legs, [npc.name] lifts [npc.herself] up and down, sliding your [pc.penis+] in and out of [npc.her] [npc.asshole+] as [npc.she] [npc.moansVerb+] out loud.",
				"Leaning forwards, [npc.name] uses [npc.her] [npc.hands] to support some of [npc.her] weight as [npc.she] eagerly slides up and down on your [pc.penis+]."
						+ " As [npc.she] lowers [npc.her] [npc.face+] towards you, [npc.she] gets a waft of your [pc.scent], and [npc.she] bites [npc.her] [npc.lip] as [npc.she] breathes in the intoxicating aroma.",
				"Lowering [npc.her] [npc.hands] to [npc.her] knees, [npc.name] uses [npc.her] [npc.legs] to enthusiastically slide [npc.herself] up and down, letting out [npc.moans+] as [npc.she] impales [npc.herself] on your [pc.cock+].",
				"[npc.Name] lifts [npc.herself] up before greedily sliding back down, letting out [npc.a_moan+] as [npc.she] spears [npc.her] [npc.asshole+] on your [pc.penis+].");
	}
	
	public static String getPartnerRidingCockAnallyRough() {
		return UtilText.returnStringAtRandom(
				"Using [npc.her] legs, [npc.name] rapidly bounces [npc.herself] up and down, slamming your [pc.penis+] in and out of [npc.her] [npc.asshole+] as [npc.she] [npc.moansVerb+] out loud.",
				"Leaning forwards, [npc.name] uses [npc.her] [npc.hands] to support some of [npc.her] weight as [npc.she] starts rapidly bucking up and down on your [pc.penis+]."
						+ " As [npc.she] lowers [npc.her] [npc.face+] towards you, [npc.she] gets a waft of your [pc.scent], and [npc.she] bites [npc.her] [npc.lip] as [npc.she] breathes in the intoxicating aroma.",
				"Placing [npc.her] [npc.hands] down on the floor behind [npc.herHim] for support, [npc.name] starts rapidly bouncing [npc.herself] up and down, letting out shuddering, [npc.moans+] as [npc.she] impales [npc.herself] on your [pc.cock+].",
				"Grinning down at you, [npc.name] starts enthusiastically bouncing up and down, letting out [npc.a_moan+] as [npc.she] spears [npc.her] [npc.asshole+] on your [pc.penis+].");
	}
	
	public static String getPartnerStoppingAnalPenetrationDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		switch(Sex.getSexPacePartner()) {
			case DOM_ROUGH:
				UtilText.nodeContentSB.append("Reaching back beneath [npc.herHim], [npc.name] roughly grabs the base of your [pc.cock], before lifting [npc.herself] up and allowing your [pc.cock+] to slide out of [npc.her] [npc.asshole+].");
				break;
			default:
				UtilText.nodeContentSB.append("Reaching back beneath [npc.herHim], [npc.name] takes hold of the base of your [pc.cock], before lifting [npc.herself] up and allowing your [pc.cock+] to slide out of [npc.her] [npc.asshole+].");
				break;
		}
		
		switch(Sex.getSexPacePlayer()) {
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" You let out a relieved sigh, which soon turns into [pc.a_sob+] as you realise that [npc.she] isn't finished with you yet.",
						" With [pc.a_sob+], you continue to protest and struggle against [npc.herHim] as [npc.she] holds you firmly in place."));
				break;
			default:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" You let out [pc.a_moan+] as [npc.she] stops riding you.",
						" [pc.A_moan+] escapes from between your [pc.lips+], betraying your desire to continue fucking [npc.her] [npc.ass+]."));
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}
}
