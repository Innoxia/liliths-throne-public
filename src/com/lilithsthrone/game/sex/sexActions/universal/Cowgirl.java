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
	
	public static String getPlayerTongueMouthDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		if (Sex.getAllContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"You sink down onto [npc.namePos] [npc.penis+], letting out a happy squeal as you feel [npc.herHim] sink deep inside your [pc.asshole+]."
								+ " Bottoming out on [npc.her] [npc.cock], you lean down and grab [npc.her] head in both hands, before pulling [npc.herHim] up into a passionate kiss.",
					"With [pc.a_moan+], you slide down onto [npc.namePos] [npc.penis+], leaning down into [npc.her] [npc.breasts] and breathing in [npc.her] [npc.scent] before pressing your [pc.lips+] against [npc.hers].",
					"You let [npc.namePos] [npc.penis+] slide even deeper into your [pc.asshole+] as you lean down and pull [npc.herHim] into a desperate kiss.",
					"Leaning down, you let out [pc.a_moan+] as you bury yourself on [npc.namePos] [npc.penis+], before leaning forwards and pressing your [pc.lips+] against [npc.hers]."));
			
		} else if (Sex.getAllContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"You sink down onto [npc.namePos] [npc.penis+], letting out a happy squeal as you feel [npc.herHim] sink deep inside your [pc.pussy+]."
								+ " Bottoming out on [npc.her] [npc.cock], you lean down and grab [npc.her] head in both hands, before pulling [npc.herHim] up into a passionate kiss.",
					"With [pc.a_moan+], you slide down onto [npc.namePos] [npc.penis+], leaning down into [npc.her] [npc.breasts] and breathing in [npc.her] [npc.scent] before pressing your [pc.lips+] against [npc.hers].",
					"You let [npc.namePos] [npc.penis+] slide even deeper into your [pc.pussy+] as you lean down and pull [npc.herHim] into a desperate kiss.",
					"Leaning down, you let out [pc.a_moan+] as you bury yourself on [npc.namePos] [npc.penis+], before leaning forwards and pressing your [pc.lips+] against [npc.hers]."));
			
		} else {
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"You lean down, grabbing [npc.namePos] head in both hands and pulling [npc.herHim] up into a passionate kiss.",
					"With a grin, you lean down into [npc.namePos] [npc.breasts], breathing in [npc.her] [npc.scent] before pressing your [pc.lips+] against [npc.hers].",
					"Leaning down, you let out [pc.a_moan+] as you press your [pc.lips+] against [npc.hers] and start to eagerly kiss [npc.herHim]."));
		}
		
		switch(Sex.getSexPace(Sex.getActivePartner())) {
			case SUB_EAGER:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc.Name] happily pushes [npc.her] [npc.tongue] deep into your mouth,"
								+ " eagerly pressing [npc.her] [npc.lips+] back against yours and [npc.moaning] in delight as [npc.she] greedily returns your display of affection.",
						" With an eager [npc.moan], [npc.she] desperately grinds back against you, muffling [npc.her] [npc.moans] into your mouth as [npc.she] desperately thrusts [npc.her] [npc.tongue] past your [pc.lips+].",
						" [npc.Moaning] in delight, [npc.she] desperately grinds [npc.herself] back against you,"
								+ " eagerly pressing [npc.her] [npc.lips+] firmly against yours as [npc.she] happily pushes [npc.her] [npc.tongue] into your mouth."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc.Name] pushes [npc.her] [npc.tongue] into your mouth, pressing [npc.her] [npc.lips+] back against yours as [npc.she] eagerly returns your display of affection.",
						" With [npc.a_moan], [npc.she] leans back against you, muffling [npc.her] [npc.moans] into your mouth as [npc.she] eagerly thrusts [npc.her] [npc.tongue] past your [pc.lips+].",
						" [npc.Moaning] in delight, [npc.she] leans back against you, pressing [npc.her] [npc.lips+] against yours as [npc.she] happily slides [npc.her] [npc.tongue] into your mouth."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc.Name] tries to pull away, [npc.sobbing] and squirming in discomfort as you force yourself on [npc.herHim].",
						" [npc.NamePos] [npc.sob+] is muffled into your mouth as [npc.she] tries to pull away, squirming in discomfort as you force yourself on [npc.herHim].",
						" With [npc.a_sob+], [npc.name] tries, in vain, to pull away from you, protesting and squirming in discomfort as you force your [pc.tongue] past [npc.her] reluctant [npc.lips]."));
				break;
			default:
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}
	
	public static String getPlayerStartingVaginalPenetrationDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		switch(Sex.getSexPace(Main.game.getPlayer())) {
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching down to take hold of [npc.namePos] [npc.cock+], you shuffle around to get into a good position."
								+ " After taking a moment to tease the [npc.cockHead+] between your outer labia, you let out a little [pc.moan] as you slowly drop down and penetrate your [pc.pussy+] on [npc.her] [npc.cock+].",
						"Shuffling around into a better position, you line the [npc.cockHead+] of [npc.namePos] [npc.cock+] up between your [pc.legs+], and with a slow, steady movement, you gently drop down, penetrating your [pc.pussy+]"
								+ " on [npc.her] [npc.cock+]."));
				break;
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching down to take a firm hold of [npc.namePos] [npc.cock+], you shuffle around to get into a good position."
								+ " After taking a moment to tease the [npc.cockHead+] between your outer labia, you let out [pc.a_moan+] as you eagerly drop down and penetrate your [pc.pussy+] on [npc.her] [npc.cock+].",
						"Shuffling around into a better position, you line the [npc.cockHead+] of [npc.namePos] [npc.cock+] up between your [pc.legs+], and with a single movement, you eagerly drop down, penetrating your [pc.pussy+]"
								+ " on [npc.her] [npc.cock+]."));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching down to roughly grab [npc.namePos] [npc.cock+], you shuffle around to get into a good position."
								+ " After taking a moment to tease the [npc.cockHead+] between your outer labia, you let out [pc.a_moan+] as you forcefully drop down and penetrate your [pc.pussy+] on [npc.her] [npc.cock+].",
						"Shuffling around into a better position, you line the [npc.cockHead+] of [npc.namePos] [npc.cock+] up between your [pc.legs+], and with a single movement, you roughly drop down, penetrating your [pc.pussy+]"
								+ " on [npc.her] [npc.cock+]."));
				break;
			default:
				break;
		}
		switch(Sex.getSexPace(Sex.getActivePartner())) {
			case SUB_EAGER:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc.Name] lets out [npc.a_moan+] as [npc.she] enters you, eagerly bucking [npc.her] [npc.hips] up as [npc.she] starts enthusiastically fucking your [pc.pussy+].",
						" With [npc.a_moan+], [npc.name] eagerly thrusts [npc.her] [npc.hips] up into your groin, sinking [npc.her] [npc.cock+] into your [pc.pussy+] as [npc.she] starts energetically fucking you."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc.Name] lets out [npc.a_moan+] as [npc.she] enters you, bucking [npc.her] [npc.hips] up into your groin as [npc.she] starts fucking your [pc.pussy+].",
						" With [npc.a_moan+], [npc.name] thrusts [npc.her] [npc.hips] up into your groin, sinking [npc.her] [npc.cock+] into your [pc.pussy+] as [npc.she] starts fucking you."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc.Name] lets out [npc.a_sob+] as you force [npc.her] [npc.cock] inside of you, and, struggling against you, [npc.she] desperately tries to push you off of [npc.herHim].",
						" With [npc.a_sob+], [npc.name] struggles against you as you force [npc.her] [npc.cock] deep into your [pc.pussy+]."));
				break;
			default:
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}
	
	public static String getPlayerRidingCockGentle() {
		return UtilText.returnStringAtRandom(
				"Using your legs, you slowly lift yourself up and down, sliding [npc.namePos] [npc.penis+] in and out of your [pc.pussy+] as you [pc.moanVerb+] out loud.",
				"Leaning forwards, you use your [pc.hands] to support some of your weight as you slowly slide up and down on [npc.namePos] [npc.penis+]."
						+ " As you lower your [pc.face+] towards [npc.herHim], you get a waft of [npc.her] [npc.scent], and you bite your [pc.lip] as you breathe in the intoxicating aroma.",
				"Lowering your [pc.hands] to your knees, you use your [pc.legs] to slowly slide yourself up and down, letting out soft [pc.moans] as you impale yourself on [npc.namePos] [npc.cock+].",
				"You slowly lift yourself up before sliding back down, letting out a soft [pc.moan] as you spear your [pc.pussy+] on [npc.namePos] [npc.penis+].");
	}
	
	public static String getPlayerRidingCockNormal() {
		return UtilText.returnStringAtRandom(
				"Using your legs, you lift yourself up and down, sliding [npc.namePos] [npc.penis+] in and out of your [pc.pussy+] as you [pc.moanVerb+] out loud.",
				"Leaning forwards, you use your [pc.hands] to support some of your weight as you eagerly slide up and down on [npc.namePos] [npc.penis+]."
						+ " As you lower your [pc.face+] towards [npc.herHim], you get a waft of [npc.her] [npc.scent], and you bite your [pc.lip] as you greedily breathe in the intoxicating aroma.",
				"Lowering your [pc.hands] to your knees, you use your [pc.legs] to eagerly slide yourself up and down, letting out [pc.moans+] as you impale yourself on [npc.namePos] [npc.cock+].",
				"You lift yourself up before greedily sliding back down, letting out [pc.a_moan+] as you spear your [pc.pussy+] on [npc.namePos] [npc.penis+].");
	}
	
	public static String getPlayerRidingCockRough() {
		return UtilText.returnStringAtRandom(
				"Using your legs, you start to rapidly bounce yourself up and down, slamming [npc.namePos] [npc.penis+] in and out of your [pc.pussy+] as you [pc.moanVerb+] in pleasure.",
				"Leaning forwards, you use [npc.namePos] [npc.breasts+] to support some of your weight before starting to rapidly buck your [pc.hips+] up and down on [npc.her] [npc.penis+]."
						+ " As your face comes closer to [npc.her] body, you get a waft of [npc.her] [npc.scent], and you bite your [pc.lip] as you greedily breathe in the intoxicating aroma.",
				"Placing your hands down on the floor behind you for support, you start rapidly bouncing yourself up and down, letting out shuddering, [pc.moans+] as you repeatedly impale yourself on [npc.namePos] [npc.cock+].",
				"Grinning down at [npc.name], you start enthusiastically bouncing up and down, letting out [pc.a_moan+] as you roughly spear your [pc.pussy+] on [npc.namePos] [npc.penis+].");
	}
	
	public static String getPlayerStoppingVaginalPenetrationDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		switch(Sex.getSexPace(Main.game.getPlayer())) {
			case DOM_ROUGH:
				UtilText.nodeContentSB.append("Reaching down, you roughly grab the base of [npc.namePos] [npc.cock], before lifting yourself up and allowing [npc.her] [npc.cock+] to slide out of your [pc.pussy+].");
				break;
			default:
				UtilText.nodeContentSB.append("Reaching down, you take hold of the base of [npc.namePos] [npc.cock], before lifting yourself up and allowing [npc.her] [npc.cock+] to slide out of your [pc.pussy+].");
				break;
		}
		
		switch(Sex.getSexPace(Sex.getActivePartner())) {
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc.Name] lets out a relieved sigh, which soon turns into [npc.a_sob+] as [npc.she] realises that you haven't finished with [npc.herHim] just yet.",
						" With [npc.a_sob+], [npc.name] continues to protest and struggle against you as you hold [npc.herHim] firmly in place."));
				break;
			default:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc.Name] lets out [npc.a_moan+] as you stop riding [npc.herHim].",
						" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] desire to continue fucking your [pc.pussy+]."));
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}
	
	public static String getPlayerStartingAnalPenetrationDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		switch(Sex.getSexPace(Main.game.getPlayer())) {
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching down to take hold of [npc.namePos] [npc.cock+], you shuffle around to get into a good position."
								+ " After taking a moment to tease the [npc.cockHead+] over your [pc.asshole+], you let out a little [pc.moan] as you slowly drop down and penetrate your [pc.ass+] on [npc.her] [npc.cock+].",
						"Shuffling around into a better position, you line the [npc.cockHead+] of [npc.namePos] [npc.cock+] up between your ass cheeks, and with a slow, steady movement, you gently drop down, penetrating your [pc.asshole+]"
								+ " on [npc.her] [npc.cock+]."));
				break;
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching down to take a firm hold of [npc.namePos] [npc.cock+], you shuffle around to get into a good position."
								+ " After taking a moment to tease the [npc.cockHead+] over your [pc.asshole+], you let out [pc.a_moan+] as you eagerly drop down and penetrate your [pc.ass+] on [npc.her] [npc.cock+].",
						"Shuffling around into a better position, you line the [npc.cockHead+] of [npc.namePos] [npc.cock+] up between your ass cheeks, and with a single movement, you eagerly drop down, penetrating your [pc.asshole+]"
								+ " on [npc.her] [npc.cock+]."));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching down to roughly grab [npc.namePos] [npc.cock+], you shuffle around to get into a good position."
								+ " After taking a moment to tease the [npc.cockHead+] over your [pc.asshole+], you let out [pc.a_moan+] as you forcefully drop down and penetrate your [pc.ass+] on [npc.her] [npc.cock+].",
						"Shuffling around into a better position, you line the [npc.cockHead+] of [npc.namePos] [npc.cock+] up between your ass cheeks, and with a single movement, you roughly drop down, penetrating your [pc.asshole+]"
								+ " on [npc.her] [npc.cock+]."));
				break;
			default:
				break;
		}
		switch(Sex.getSexPace(Sex.getActivePartner())) {
			case SUB_EAGER:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc.Name] lets out [npc.a_moan+] as [npc.she] enters you, eagerly bucking [npc.her] [npc.hips] up as [npc.she] starts enthusiastically fucking your [pc.asshole+].",
						" With [npc.a_moan+], [npc.name] eagerly thrusts [npc.her] [npc.hips] up, sinking [npc.her] [npc.cock+] into your [pc.asshole+] as [npc.she] starts energetically fucking you."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc.Name] lets out [npc.a_moan+] as [npc.she] enters you, bucking [npc.her] [npc.hips] up as [npc.she] starts fucking your [pc.asshole+].",
						" With [npc.a_moan+], [npc.name] thrusts [npc.her] [npc.hips] up, sinking [npc.her] [npc.cock+] into your [pc.asshole+] as you start riding [npc.herHim]."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc.Name] lets out [npc.a_sob+] as you force [npc.her] [npc.cock] inside of you, and, struggling against you, [npc.she] desperately tries to pull [npc.her] [npc.cock+] free from your [pc.asshole+].",
						" With [npc.a_sob+], [npc.name] struggles against you as you force [npc.her] [npc.cock] deep into your [pc.asshole+]."));
				break;
			default:
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}
	
	public static String getPlayerRidingCockAnallyGentle() {
		return UtilText.returnStringAtRandom(
				"Using your legs, you slowly lift yourself up and down, sliding [npc.namePos] [npc.penis+] in and out of your [pc.asshole+] as you [pc.moanVerb+] out loud.",
				"Leaning forwards, you use your [pc.hands] to support some of your weight as you slowly slide up and down on [npc.namePos] [npc.penis+]."
						+ " As you lower your [pc.face+] towards [npc.herHim], you get a waft of [npc.her] [npc.scent], and you bite your [pc.lip] as you breathe in the intoxicating aroma.",
				"Lowering your [pc.hands] to your knees, you use your [pc.legs] to slowly slide yourself up and down, letting out soft [pc.moans] as you impale yourself on [npc.namePos] [npc.cock+].",
				"You slowly lift yourself up before sliding back down, letting out a soft [pc.moan] as you spear your [pc.asshole+] on [npc.namePos] [npc.penis+].");
	}
	
	public static String getPlayerRidingCockAnallyNormal() {
		return UtilText.returnStringAtRandom(
				"Using your legs, you lift yourself up and down, sliding [npc.namePos] [npc.penis+] in and out of your [pc.asshole+] as you [pc.moanVerb+] out loud.",
				"Leaning forwards, you use your [pc.hands] to support some of your weight as you eagerly slide up and down on [npc.namePos] [npc.penis+]."
						+ " As you lower your [pc.face+] towards [npc.herHim], you get a waft of [npc.her] [npc.scent], and you bite your [pc.lip] as you greedily breathe in the intoxicating aroma.",
				"Lowering your [pc.hands] to your knees, you use your [pc.legs] to eagerly slide yourself up and down, letting out [pc.moans+] as you impale yourself on [npc.namePos] [npc.cock+].",
				"You lift yourself up before greedily sliding back down, letting out [pc.a_moan+] as you spear your [pc.asshole+] on [npc.namePos] [npc.penis+].");
	}
	
	public static String getPlayerRidingCockAnallyRough() {
		return UtilText.returnStringAtRandom(
				"Using your legs, you start to rapidly bounce yourself up and down, slamming [npc.namePos] [npc.penis+] in and out of your [pc.asshole+] as you [pc.moanVerb+] in pleasure.",
				"Leaning forwards, you use [npc.namePos] [npc.breasts+] to support some of your weight before starting to rapidly buck your [pc.hips+] up and down on [npc.her] [npc.penis+]."
						+ " As your face comes closer to [npc.her] body, you get a waft of [npc.her] [npc.scent], and you bite your [pc.lip] as you greedily breathe in the intoxicating aroma.",
				"Placing your hands down on the floor behind you for support, you start rapidly bouncing yourself up and down, letting out shuddering, [pc.moans+] as you repeatedly impale yourself on [npc.namePos] [npc.cock+].",
				"Grinning down at [npc.name], you start enthusiastically bouncing up and down, letting out [pc.a_moan+] as you roughly spear your [pc.asshole+] on [npc.namePos] [npc.penis+].");
	}
	
	public static String getPlayerStoppingAnalPenetrationDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		switch(Sex.getSexPace(Main.game.getPlayer())) {
			case DOM_ROUGH:
				UtilText.nodeContentSB.append("Reaching back beneath you, you roughly grab the base of [npc.namePos] [npc.cock], before lifting yourself up and allowing [npc.her] [npc.cock+] to slide out of your [pc.asshole+].");
				break;
			default:
				UtilText.nodeContentSB.append("Reaching back beneath you, you take hold of the base of [npc.namePos] [npc.cock], before lifting yourself up and allowing [npc.her] [npc.cock+] to slide out of your [pc.asshole+].");
				break;
		}
		
		switch(Sex.getSexPace(Sex.getActivePartner())) {
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc.Name] lets out a relieved sigh, which soon turns into [npc.a_sob+] as [npc.she] realises that you haven't finished with [npc.herHim] just yet.",
						" With [npc.a_sob+], [npc.name] continues to protest and struggle against you as you hold [npc.herHim] firmly in place."));
				break;
			default:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc.Name] lets out [npc.a_moan+] as you stop riding [npc.herHim].",
						" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] desire to continue fucking your [pc.asshole+]."));
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}
	

	
	public static String getPartnerTongueMouthDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		if (Sex.getAllContactingSexAreas(Sex.getActivePartner(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] sinks down onto your [pc.penis+], letting out a happy squeal as [npc.she] feels you sink deep inside [npc.her] [npc.asshole+]."
								+ " Bottoming out on your [pc.cock], [npc.she] leans down and grabs your head in both hands, before pulling you up into a passionate kiss.",
					"With [npc.a_moan+], [npc.name] slides down onto your [pc.penis+], leaning down into your [pc.breasts] and breathing in your [pc.scent] before pressing [npc.her] [npc.lips+] against yours.",
					"[npc.Name] lets your [pc.penis+] slide even deeper into [npc.her] [npc.asshole+] as [npc.she] leans down and pulls you into a desperate kiss.",
					"Leaning down, [npc.name] lets out [npc.a_moan+] as [npc.she] buries [npc.herself] on your [pc.penis+], before leaning forwards and pressing [npc.her] [npc.lips+] against yours."));
			
		} else if (Sex.getAllContactingSexAreas(Sex.getActivePartner(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
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

		switch(Sex.getSexPace(Main.game.getPlayer())) {
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
		
		switch(Sex.getSexPace(Sex.getActivePartner())) {
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
		switch(Sex.getSexPace(Main.game.getPlayer())) {
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
		
		switch(Sex.getSexPace(Sex.getActivePartner())) {
			case DOM_ROUGH:
				UtilText.nodeContentSB.append("Reaching down, [npc.name] roughly grabs the base of your [pc.cock], before lifting [npc.herself] up and allowing your [pc.cock+] to slide out of [npc.her] [npc.pussy+].");
				break;
			default:
				UtilText.nodeContentSB.append("Reaching down, [npc.name] takes hold of the base of your [pc.cock], before lifting [npc.herself] up and allowing your [pc.cock+] to slide out of [npc.her] [npc.pussy+].");
				break;
		}
		
		switch(Sex.getSexPace(Main.game.getPlayer())) {
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
		
		switch(Sex.getSexPace(Sex.getActivePartner())) {
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
		switch(Sex.getSexPace(Main.game.getPlayer())) {
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
		
		switch(Sex.getSexPace(Sex.getActivePartner())) {
			case DOM_ROUGH:
				UtilText.nodeContentSB.append("Reaching back beneath [npc.herHim], [npc.name] roughly grabs the base of your [pc.cock], before lifting [npc.herself] up and allowing your [pc.cock+] to slide out of [npc.her] [npc.asshole+].");
				break;
			default:
				UtilText.nodeContentSB.append("Reaching back beneath [npc.herHim], [npc.name] takes hold of the base of your [pc.cock], before lifting [npc.herself] up and allowing your [pc.cock+] to slide out of [npc.her] [npc.asshole+].");
				break;
		}
		
		switch(Sex.getSexPace(Main.game.getPlayer())) {
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
