package com.lilithsthrone.game.sex.sexActions.universal;

import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.main.Main;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Innoxia
 */
public class Cowgirl {
	
	//TODO These need proper formatting before inclusion into sex actions
	
	public static String getTongueMouthDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		if (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(sink)] down onto [npc2.namePos] [npc2.penis+], letting out [npc.a_moan+] [npc.she] [npc.verb(lean)] down and [npc.verb(grab)] [npc2.namePos] head in both hands, before pulling [npc2.name] up into a passionate kiss.",
					"With [npc.a_moan+], [npc.name] [npc.verb(slide)] down onto [npc2.namePos] [npc2.penis+], leaning down into [npc2.namePos] [npc2.breasts] and breathing in [npc2.namePos] [npc2.scent] before pressing [npc.her] [npc.lips+] against [npc2.namePos]s.",
					"[npc.Name] [npc.verb(let)] [npc2.namePos] [npc2.penis+] slide even deeper into [npc.her] [npc.asshole+] as [npc.she] [npc.verb(lean)] down and pulls [npc2.name] into a desperate kiss.",
					"Leaning down, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] buries [npc.herself] on [npc2.namePos] [npc2.penis+], before leaning forwards and pressing [npc.her] [npc.lips+] against [npc2.namePos]s."));
			
		} else if (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(sink)] down onto [npc2.namePos] [npc2.penis+], letting out a happy squeal as [npc.she] [npc.verb(feel)] [npc2.name] sink deep inside [npc.her] [npc.pussy+]."
								+ " Bottoming out on [npc2.namePos] [npc2.cock], [npc.she] [npc.verb(lean)] down and [npc.verb(grab)] [npc2.namePos] head in both hands, before pulling [npc2.name] up into a passionate kiss.",
					"With [npc.a_moan+], [npc.name] [npc.verb(slide)] down onto [npc2.namePos] [npc2.penis+], leaning down into [npc2.namePos] [npc2.breasts] and breathing in [npc2.namePos] [npc2.scent] before pressing [npc.her] [npc.lips+] against [npc2.namePos]s.",
					"[npc.Name] [npc.verb(let)] [npc2.namePos] [npc2.penis+] slide even deeper into [npc.her] [npc.pussy+] as [npc.she] [npc.verb(lean)] down and pulls [npc2.name] into a desperate kiss.",
					"Leaning down, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] buries [npc.herself] on [npc2.namePos] [npc2.penis+], before leaning forwards and pressing [npc.her] [npc.lips+] against [npc2.namePos]s."));
			
		} else {
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(lean)] down, grabbing [npc2.namePos] head in both hands and pulling [npc2.name] up into a passionate kiss.",
					"With a grin, [npc.name] [npc.verb(lean)] down into [npc2.namePos] [npc2.breasts], breathing in [npc2.namePos] [npc2.scent] before pressing [npc.her] [npc.lips+] against [npc2.namePos]s.",
					"Leaning down, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] presses [npc.her] [npc.lips+] against [npc2.namePos]s and starts to eagerly kiss [npc2.name]."));
		}

		switch(Sex.getSexPace(Main.game.getPlayer())) {
			case SUB_EAGER:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] happily push [npc2.namePos] [npc2.tongue] deep into [npc.her] mouth, eagerly pressing [npc2.namePos] [npc2.lips+] back against [npc.hers] and [npc2.moaning] in delight as [npc2.name] greedily return [npc.her] display of affection.",
						" With an eager [npc2.moan], [npc2.name] desperately grind back against [npc.herHim], muffling [npc2.namePos] [npc2.moans] into [npc.her] mouth as [npc2.name] greedily thrust [npc2.namePos] [npc2.tongue] past [npc.her] [npc.lips+].",
						" [npc2.Moaning] in delight, [npc2.name] desperately grind [npc2.namePos]self back against [npc.herHim], eagerly pressing [npc2.namePos] [npc2.lips+] firmly against [npc.hers] as [npc2.name] happily push [npc2.namePos] [npc2.tongue] into [npc.her] mouth."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] push [npc2.namePos] [npc2.tongue] into [npc.her] mouth, pressing [npc2.namePos] [npc2.lips+] back against [npc.hers] as [npc2.name] eagerly return [npc.her] display of affection.",
						" With [npc2.a_moan], [npc2.name] lean back against [npc.herHim], muffling [npc2.namePos] [npc2.moans] into [npc.her] mouth as [npc2.name] eagerly thrust [npc2.namePos] [npc2.tongue] past [npc.her] [npc.lips+].",
						" [npc2.Moaning] in delight, [npc2.name] lean back against [npc.herHim], pressing [npc2.namePos] [npc2.lips+] against [npc.hers] as [npc2.name] happily slide [npc2.namePos] [npc2.tongue] into [npc.her] mouth."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] try to pull away, [npc2.sobbing] and squirming in discomfort as [npc.she] forces [npc.herself] on [npc2.name].",
						" [npc2.NamePos] [npc2.sob+] is muffled into [npc.her] mouth as [npc2.name] try to pull away, squirming in discomfort as [npc.name] forces [npc.herself] on [npc2.name].",
						" With [npc2.a_sob+], [npc2.name] try, in vain, to pull away from [npc.herHim], protesting and squirming in discomfort as [npc.she] forces [npc.her] [npc.tongue] past [npc2.namePos] reluctant [npc2.lips]."));
				break;
			default:
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}
	
	public static String getStartingVaginalPenetrationDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] reaches down to take hold of [npc2.namePos] [npc2.cock+], before shuffling around to get into a good position."
								+ " After taking a moment to tease the [npc2.cockHead+] between [npc.her] outer labia, [npc.she] [npc.verb(let)] out a little [npc.moan] as [npc.she] slowly drops down and penetrates [npc.her] [npc.pussy+] on [npc2.namePos] [npc2.cock+].",
						"Shuffling around into a better position, [npc.name] lines the [npc2.cockHead+] of [npc2.namePos] [npc2.cock+] up between [npc.her] [npc.legs+], and with a slow, steady movement, [npc.she] gently drops down,"
								+ " penetrating [npc.her] [npc.pussy+] on [npc2.namePos] [npc2.cock+]."));
				break;
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] reaches down to take a firm hold of [npc2.namePos] [npc2.cock+], before shuffling around to get into a good position."
								+ " After taking a moment to tease the [npc2.cockHead+] between [npc.her] outer labia, [npc.she] [npc.verb(let)] out [npc.a_moan+] as [npc.she] eagerly drops down and penetrates [npc.her] [npc.pussy+] on [npc2.namePos] [npc2.cock+].",
						"Shuffling around into a better position, [npc.name] lines the [npc2.cockHead+] of [npc2.namePos] [npc2.cock+] up between [npc.her] [npc.legs+], and with a single movement, [npc.she] eagerly drops down,"
								+ " penetrating [npc.her] [npc.pussy+] on [npc2.namePos] [npc2.cock+]."));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] reaches down and roughly [npc.verb(grab)] [npc2.namePos] [npc2.cock+], before shuffling around to get into a good position."
								+ " After taking a moment to tease the [npc2.cockHead+] between [npc.her] outer labia, [npc.she] [npc.verb(let)] out [npc.a_moan+] as [npc.she] forcefully drops down and penetrates [npc.her] [npc.pussy+] on [npc2.namePos] [npc2.cock+].",
						"Shuffling around into a better position, [npc.name] lines the [npc2.cockHead+] of [npc2.namePos] [npc2.cock+] up between [npc.her] [npc.legs+], and with a single movement, [npc.she] roughly drops down,"
								+ " penetrating [npc.her] [npc.pussy+] on [npc2.namePos] [npc2.cock+]."));
				break;
			default:
				break;
		}
		switch(Sex.getSexPace(Main.game.getPlayer())) {
			case SUB_EAGER:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] let out [npc2.a_moan+] as [npc2.name] enter [npc.herHim], eagerly bucking [npc2.namePos] [npc2.hips] up as [npc2.name] start fucking [npc.her] [npc.pussy+].",
						" With [npc2.a_moan+], [npc2.name] eagerly thrust [npc2.namePos] [npc2.hips] up into [npc.her] groin, sinking [npc2.namePos] [npc2.cock+] into [npc.her] [npc.pussy+] as [npc2.name] start energetically fucking [npc.herHim]."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] let out [npc2.a_moan+] as [npc2.name] enter [npc.herHim], bucking [npc2.namePos] [npc2.hips] up as [npc2.name] start fucking [npc.her] [npc.pussy+].",
						" With [npc2.a_moan+], [npc2.name] thrust [npc2.namePos] [npc2.hips] up into [npc.her] groin, sinking [npc2.namePos] [npc2.cock+] into [npc.her] [npc.pussy+] as [npc2.name] start fucking [npc.herHim]."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] let out [npc2.a_sob+] as [npc.she] forces [npc2.namePos] [npc2.cock] inside of [npc.herHim], and, struggling against [npc.herHim] in vain, [npc2.name] desperately try to push [npc.herHim] off of [npc2.name].",
						" With [npc2.a_sob+], [npc2.name] struggle against [npc.name] as [npc.she] forces [npc2.namePos] [npc2.cock] deep into [npc.her] [npc.pussy+]."));
				break;
			default:
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}
	
	public static String getRidingCockGentle() {
		return UtilText.returnStringAtRandom(
				"Using [npc.her] legs, [npc.name] slowly [npc.verb(lift)] [npc.herself] up and down, sliding [npc2.namePos] [npc2.penis+] in and out of [npc.her] [npc.pussy+] as [npc.she] [npc.moansVerb+] out loud.",
				"Leaning forwards, [npc.name] uses [npc.her] [npc.hands] to support some of [npc.her] weight as [npc.she] slowly [npc.verb(slide)] up and down on [npc2.namePos] [npc2.penis+]."
						+ " As [npc.she] [npc.verb(lower)] [npc.her] [npc.face+] towards [npc2.name], [npc.she] gets a waft of [npc2.namePos] [npc2.scent], and [npc.she] bites [npc.her] [npc.lip] as [npc.she] breathes in the intoxicating aroma.",
				"Lowering [npc.her] [npc.hands] to [npc.her] knees, [npc.name] uses [npc.her] [npc.legs] to slowly slide [npc.herself] up and down, letting out soft [npc.moans] as [npc.she] impales [npc.herself] on [npc2.namePos] [npc2.cock+].",
				"[npc.Name] slowly [npc.verb(lift)] [npc.herself] up before sliding back down, letting out a soft [npc.moan] as [npc.she] spears [npc.her] [npc.pussy+] on [npc2.namePos] [npc2.penis+].");
	}
	
	public static String getRidingCockNormal() {
		return UtilText.returnStringAtRandom(
				"Using [npc.her] legs, [npc.name] [npc.verb(lift)] [npc.herself] up and down, sliding [npc2.namePos] [npc2.penis+] in and out of [npc.her] [npc.pussy+] as [npc.she] [npc.moansVerb+] out loud.",
				"Leaning forwards, [npc.name] uses [npc.her] [npc.hands] to support some of [npc.her] weight as [npc.she] eagerly [npc.verb(slide)] up and down on [npc2.namePos] [npc2.penis+]."
						+ " As [npc.she] [npc.verb(lower)] [npc.her] [npc.face+] towards [npc2.name], [npc.she] gets a waft of [npc2.namePos] [npc2.scent], and [npc.she] bites [npc.her] [npc.lip] as [npc.she] breathes in the intoxicating aroma.",
				"Lowering [npc.her] [npc.hands] to [npc.her] knees, [npc.name] uses [npc.her] [npc.legs] to enthusiastically slide [npc.herself] up and down, letting out [npc.moans+] as [npc.she] impales [npc.herself] on [npc2.namePos] [npc2.cock+].",
				"[npc.Name] [npc.verb(lift)] [npc.herself] up before greedily sliding back down, letting out [npc.a_moan+] as [npc.she] spears [npc.her] [npc.pussy+] on [npc2.namePos] [npc2.penis+].");
	}
	
	public static String getRidingCockRough() {
		return UtilText.returnStringAtRandom(
				"Using [npc.her] legs, [npc.name] rapidly bounces [npc.herself] up and down, slamming [npc2.namePos] [npc2.penis+] in and out of [npc.her] [npc.pussy+] as [npc.she] [npc.moansVerb+] out loud.",
				"Leaning forwards, [npc.name] uses [npc.her] [npc.hands] to support some of [npc.her] weight as [npc.she] starts rapidly bucking up and down on [npc2.namePos] [npc2.penis+]."
						+ " As [npc.she] [npc.verb(lower)] [npc.her] [npc.face+] towards [npc2.name], [npc.she] gets a waft of [npc2.namePos] [npc2.scent], and [npc.she] bites [npc.her] [npc.lip] as [npc.she] breathes in the intoxicating aroma.",
				"Placing [npc.her] [npc.hands] down on the floor behind [npc.herHim] for support, [npc.name] starts rapidly bouncing [npc.herself] up and down, letting out shuddering, [npc.moans+] as [npc.she] impales [npc.herself] on [npc2.namePos] [npc2.cock+].",
				"Grinning down at [npc2.name], [npc.name] starts enthusiastically bouncing up and down, letting out [npc.a_moan+] as [npc.she] spears [npc.her] [npc.pussy+] on [npc2.namePos] [npc2.penis+].");
	}
	
	public static String getStoppingVaginalPenetrationDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
			case DOM_ROUGH:
				UtilText.nodeContentSB.append("Reaching down, [npc.name] roughly [npc.verb(grab)] the base of [npc2.namePos] [npc2.cock], before lifting [npc.herself] up and allowing [npc2.namePos] [npc2.cock+] to slide out of [npc.her] [npc.pussy+].");
				break;
			default:
				UtilText.nodeContentSB.append("Reaching down, [npc.name] takes hold of the base of [npc2.namePos] [npc2.cock], before lifting [npc.herself] up and allowing [npc2.namePos] [npc2.cock+] to slide out of [npc.her] [npc.pussy+].");
				break;
		}
		
		switch(Sex.getSexPace(Main.game.getPlayer())) {
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] let out a relieved sigh, which soon turns into [npc2.a_sob+] as [npc2.name] realise that [npc.she] isn't finished with [npc2.name] yet.",
						" With [npc2.a_sob+], [npc2.name] continue to protest and struggle against [npc.herHim] as [npc.she] holds [npc2.name] firmly in place."));
				break;
			default:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] let out [npc2.a_moan+] as [npc.she] stops riding [npc2.name].",
						" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.namePos] desire to continue fucking [npc.her] [npc.pussy+]."));
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}
	
	public static String getStartingAnalPenetrationDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] reaches down to take hold of [npc2.namePos] [npc2.cock+], before shuffling around to get into a good position."
								+ " After taking a moment to tease the [npc2.cockHead+] over [npc.her] [npc.asshole+], [npc.she] [npc.verb(let)] out a little [npc.moan] as [npc.she] slowly drops down and penetrates [npc.her] [npc.ass+] on [npc2.namePos] [npc2.cock+].",
						"Shuffling around into a better position, [npc.name] lines the [npc2.cockHead+] of [npc2.namePos] [npc2.cock+] up between [npc.her] ass cheeks, and with a slow, steady movement, [npc.she] gently drops down,"
								+ " penetrating [npc.her] [npc.asshole+] on [npc2.namePos] [npc2.cock+]."));
				break;
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] reaches down to take a firm hold of [npc2.namePos] [npc2.cock+], before shuffling around to get into a good position."
								+ " After taking a moment to tease the [npc2.cockHead+] over [npc.her] [npc.asshole+], [npc.she] [npc.verb(let)] out [npc.a_moan+] as [npc.she] eagerly drops down and penetrates [npc.her] [npc.ass+] on [npc2.namePos] [npc2.cock+].",
						"Shuffling around into a better position, [npc.name] lines the [npc2.cockHead+] of [npc2.namePos] [npc2.cock+] up between [npc.her] ass cheeks, and with a single movement, [npc.she] eagerly drops down,"
								+ " penetrating [npc.her] [npc.asshole+] on [npc2.namePos] [npc2.cock+]."));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] reaches down and roughly [npc.verb(grab)] [npc2.namePos] [npc2.cock+], before shuffling around to get into a good position."
								+ " After taking a moment to tease the [npc2.cockHead+] over [npc.her] [npc.asshole+], [npc.she] [npc.verb(let)] out [npc.a_moan+] as [npc.she] forcefully drops down and penetrates [npc.her] [npc.ass+] on [npc2.namePos] [npc2.cock+].",
						"Shuffling around into a better position, [npc.name] lines the [npc2.cockHead+] of [npc2.namePos] [npc2.cock+] up between [npc.her] ass cheeks, and with a single movement, [npc.she] roughly drops down,"
								+ " penetrating [npc.her] [npc.asshole+] on [npc2.namePos] [npc2.cock+]."));
				break;
			default:
				break;
		}
		switch(Sex.getSexPace(Main.game.getPlayer())) {
			case SUB_EAGER:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] let out [npc2.a_moan+] as [npc2.name] enter [npc.herHim], eagerly bucking [npc2.namePos] [npc2.hips] up as [npc2.name] start fucking [npc.her] [npc.asshole+].",
						" With [npc2.a_moan+], [npc2.name] eagerly thrust [npc2.namePos] [npc2.hips] up into [npc.her] [npc.ass], sinking [npc2.namePos] [npc2.cock+] into [npc.her] [npc.asshole+] as [npc2.name] start energetically fucking [npc.herHim]."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] let out [npc2.a_moan+] as [npc2.name] enter [npc.herHim], bucking [npc2.namePos] [npc2.hips] up as [npc2.name] start fucking [npc.her] [npc.asshole+].",
						" With [npc2.a_moan+], [npc2.name] thrust [npc2.namePos] [npc2.hips] up into [npc.her] [npc.ass], sinking [npc2.namePos] [npc2.cock+] into [npc.her] [npc.asshole+] as [npc2.name] start fucking [npc.herHim]."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] let out [npc2.a_sob+] as [npc.she] forces [npc2.namePos] [npc2.cock] inside of [npc.herHim], and, struggling against [npc.herHim] in vain, [npc2.name] desperately try to push [npc.herHim] off of [npc2.name].",
						" With [npc2.a_sob+], [npc2.name] struggle against [npc.name] as [npc.she] forces [npc2.namePos] [npc2.cock] deep into [npc.her] [npc.asshole+]."));
				break;
			default:
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}
	
	public static String getRidingCockAnallyGentle() {
		return UtilText.returnStringAtRandom(
				"Using [npc.her] legs, [npc.name] slowly [npc.verb(lift)] [npc.herself] up and down, sliding [npc2.namePos] [npc2.penis+] in and out of [npc.her] [npc.asshole+] as [npc.she] [npc.moansVerb+] out loud.",
				"Leaning forwards, [npc.name] uses [npc.her] [npc.hands] to support some of [npc.her] weight as [npc.she] slowly [npc.verb(slide)] up and down on [npc2.namePos] [npc2.penis+]."
						+ " As [npc.she] [npc.verb(lower)] [npc.her] [npc.face+] towards [npc2.name], [npc.she] gets a waft of [npc2.namePos] [npc2.scent], and [npc.she] bites [npc.her] [npc.lip] as [npc.she] breathes in the intoxicating aroma.",
				"Lowering [npc.her] [npc.hands] to [npc.her] knees, [npc.name] uses [npc.her] [npc.legs] to slowly slide [npc.herself] up and down, letting out soft [npc.moans] as [npc.she] impales [npc.herself] on [npc2.namePos] [npc2.cock+].",
				"[npc.Name] slowly [npc.verb(lift)] [npc.herself] up before sliding back down, letting out a soft [npc.moan] as [npc.she] spears [npc.her] [npc.asshole+] on [npc2.namePos] [npc2.penis+].");
	}
	
	public static String getRidingCockAnallyNormal() {
		return UtilText.returnStringAtRandom(
				"Using [npc.her] legs, [npc.name] [npc.verb(lift)] [npc.herself] up and down, sliding [npc2.namePos] [npc2.penis+] in and out of [npc.her] [npc.asshole+] as [npc.she] [npc.moansVerb+] out loud.",
				"Leaning forwards, [npc.name] uses [npc.her] [npc.hands] to support some of [npc.her] weight as [npc.she] eagerly [npc.verb(slide)] up and down on [npc2.namePos] [npc2.penis+]."
						+ " As [npc.she] [npc.verb(lower)] [npc.her] [npc.face+] towards [npc2.name], [npc.she] gets a waft of [npc2.namePos] [npc2.scent], and [npc.she] bites [npc.her] [npc.lip] as [npc.she] breathes in the intoxicating aroma.",
				"Lowering [npc.her] [npc.hands] to [npc.her] knees, [npc.name] uses [npc.her] [npc.legs] to enthusiastically slide [npc.herself] up and down, letting out [npc.moans+] as [npc.she] impales [npc.herself] on [npc2.namePos] [npc2.cock+].",
				"[npc.Name] [npc.verb(lift)] [npc.herself] up before greedily sliding back down, letting out [npc.a_moan+] as [npc.she] spears [npc.her] [npc.asshole+] on [npc2.namePos] [npc2.penis+].");
	}
	
	public static String getRidingCockAnallyRough() {
		return UtilText.returnStringAtRandom(
				"Using [npc.her] legs, [npc.name] rapidly bounces [npc.herself] up and down, slamming [npc2.namePos] [npc2.penis+] in and out of [npc.her] [npc.asshole+] as [npc.she] [npc.moansVerb+] out loud.",
				"Leaning forwards, [npc.name] uses [npc.her] [npc.hands] to support some of [npc.her] weight as [npc.she] starts rapidly bucking up and down on [npc2.namePos] [npc2.penis+]."
						+ " As [npc.she] [npc.verb(lower)] [npc.her] [npc.face+] towards [npc2.name], [npc.she] gets a waft of [npc2.namePos] [npc2.scent], and [npc.she] bites [npc.her] [npc.lip] as [npc.she] breathes in the intoxicating aroma.",
				"Placing [npc.her] [npc.hands] down on the floor behind [npc.herHim] for support, [npc.name] starts rapidly bouncing [npc.herself] up and down, letting out shuddering, [npc.moans+] as [npc.she] impales [npc.herself] on [npc2.namePos] [npc2.cock+].",
				"Grinning down at [npc2.name], [npc.name] starts enthusiastically bouncing up and down, letting out [npc.a_moan+] as [npc.she] spears [npc.her] [npc.asshole+] on [npc2.namePos] [npc2.penis+].");
	}
	
	public static String getStoppingAnalPenetrationDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
			case DOM_ROUGH:
				UtilText.nodeContentSB.append("Reaching back beneath [npc.herHim], [npc.name] roughly [npc.verb(grab)] the base of [npc2.namePos] [npc2.cock], before lifting [npc.herself] up and allowing [npc2.namePos] [npc2.cock+] to slide out of [npc.her] [npc.asshole+].");
				break;
			default:
				UtilText.nodeContentSB.append("Reaching back beneath [npc.herHim], [npc.name] takes hold of the base of [npc2.namePos] [npc2.cock], before lifting [npc.herself] up and allowing [npc2.namePos] [npc2.cock+] to slide out of [npc.her] [npc.asshole+].");
				break;
		}
		
		switch(Sex.getSexPace(Main.game.getPlayer())) {
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] let out a relieved sigh, which soon turns into [npc2.a_sob+] as [npc2.name] realise that [npc.she] isn't finished with [npc2.name] yet.",
						" With [npc2.a_sob+], [npc2.name] continue to protest and struggle against [npc.herHim] as [npc.she] holds [npc2.name] firmly in place."));
				break;
			default:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] let out [npc2.a_moan+] as [npc.she] stops riding [npc2.name].",
						" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.namePos] desire to continue fucking [npc.her] [npc.ass+]."));
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}



}
