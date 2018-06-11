package com.lilithsthrone.game.sex.sexActions.universal.dom;

import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.83
 * @version 0.1.84
 * @author Innoxia
 */
public class DomCowgirl {
	
	public static String getPlayerTongueMouthDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), SexAreaOrifice.ANUS) == SexAreaPenetration.PENIS) {
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"You sink down onto [npc.name]'s [npc.penis+], letting out a happy squeal as you feel [npc.herHim] sink deep inside your [pc.asshole+]."
								+ " Bottoming out on [npc.her] [npc.cock], you lean down and grab [npc.her] head in both hands, before pulling [npc.herHim] up into a passionate kiss.",
					"With [pc.a_moan+], you slide down onto [npc.name]'s [npc.penis+], leaning down into [npc.her] [npc.breasts] and breathing in [npc.her] [npc.scent] before pressing your [pc.lips+] against [npc.hers].",
					"You let [npc.name]'s [npc.penis+] slide even deeper into your [pc.asshole+] as you lean down and pull [npc.herHim] into a desperate kiss.",
					"Leaning down, you let out [pc.a_moan+] as you bury yourself on [npc.name]'s [npc.penis+], before leaning forwards and pressing your [pc.lips+] against [npc.hers]."));
			
		} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), SexAreaOrifice.VAGINA) == SexAreaPenetration.PENIS) {
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"You sink down onto [npc.name]'s [npc.penis+], letting out a happy squeal as you feel [npc.herHim] sink deep inside your [pc.pussy+]."
								+ " Bottoming out on [npc.her] [npc.cock], you lean down and grab [npc.her] head in both hands, before pulling [npc.herHim] up into a passionate kiss.",
					"With [pc.a_moan+], you slide down onto [npc.name]'s [npc.penis+], leaning down into [npc.her] [npc.breasts] and breathing in [npc.her] [npc.scent] before pressing your [pc.lips+] against [npc.hers].",
					"You let [npc.name]'s [npc.penis+] slide even deeper into your [pc.pussy+] as you lean down and pull [npc.herHim] into a desperate kiss.",
					"Leaning down, you let out [pc.a_moan+] as you bury yourself on [npc.name]'s [npc.penis+], before leaning forwards and pressing your [pc.lips+] against [npc.hers]."));
			
		} else {
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"You lean down, grabbing [npc.name]'s head in both hands and pulling [npc.herHim] up into a passionate kiss.",
					"With a grin, you lean down into [npc.name]'s [npc.breasts], breathing in [npc.her] [npc.scent] before pressing your [pc.lips+] against [npc.hers].",
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
						" [npc.Name]'s [npc.sob+] is muffled into your mouth as [npc.she] tries to pull away, squirming in discomfort as you force yourself on [npc.herHim].",
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
						"Reaching down to take hold of [npc.name]'s [npc.cock+], you shuffle around to get into a good position."
								+ " After taking a moment to tease the [npc.cockHead+] between your outer labia, you let out a little [pc.moan] as you slowly drop down and penetrate your [pc.pussy+] on [npc.her] [npc.cock+].",
						"Shuffling around into a better position, you line the [npc.cockHead+] of [npc.name]'s [npc.cock+] up between your [pc.legs+], and with a slow, steady movement, you gently drop down, penetrating your [pc.pussy+]"
								+ " on [npc.her] [npc.cock+]."));
				break;
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching down to take a firm hold of [npc.name]'s [npc.cock+], you shuffle around to get into a good position."
								+ " After taking a moment to tease the [npc.cockHead+] between your outer labia, you let out [pc.a_moan+] as you eagerly drop down and penetrate your [pc.pussy+] on [npc.her] [npc.cock+].",
						"Shuffling around into a better position, you line the [npc.cockHead+] of [npc.name]'s [npc.cock+] up between your [pc.legs+], and with a single movement, you eagerly drop down, penetrating your [pc.pussy+]"
								+ " on [npc.her] [npc.cock+]."));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching down to roughly grab [npc.name]'s [npc.cock+], you shuffle around to get into a good position."
								+ " After taking a moment to tease the [npc.cockHead+] between your outer labia, you let out [pc.a_moan+] as you forcefully drop down and penetrate your [pc.pussy+] on [npc.her] [npc.cock+].",
						"Shuffling around into a better position, you line the [npc.cockHead+] of [npc.name]'s [npc.cock+] up between your [pc.legs+], and with a single movement, you roughly drop down, penetrating your [pc.pussy+]"
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
				"Using your legs, you slowly lift yourself up and down, sliding [npc.name]'s [npc.penis+] in and out of your [pc.pussy+] as you [pc.moanVerb+] out loud.",
				"Leaning forwards, you use your [pc.hands] to support some of your weight as you slowly slide up and down on [npc.name]'s [npc.penis+]."
						+ " As you lower your [pc.face+] towards [npc.herHim], you get a waft of [npc.her] [npc.scent], and you bite your [pc.lip] as you breathe in the intoxicating aroma.",
				"Lowering your [pc.hands] to your knees, you use your [pc.legs] to slowly slide yourself up and down, letting out soft [pc.moans] as you impale yourself on [npc.name]'s [npc.cock+].",
				"You slowly lift yourself up before sliding back down, letting out a soft [pc.moan] as you spear your [pc.pussy+] on [npc.name]'s [npc.penis+].");
	}
	
	public static String getPlayerRidingCockNormal() {
		return UtilText.returnStringAtRandom(
				"Using your legs, you lift yourself up and down, sliding [npc.name]'s [npc.penis+] in and out of your [pc.pussy+] as you [pc.moanVerb+] out loud.",
				"Leaning forwards, you use your [pc.hands] to support some of your weight as you eagerly slide up and down on [npc.name]'s [npc.penis+]."
						+ " As you lower your [pc.face+] towards [npc.herHim], you get a waft of [npc.her] [npc.scent], and you bite your [pc.lip] as you greedily breathe in the intoxicating aroma.",
				"Lowering your [pc.hands] to your knees, you use your [pc.legs] to eagerly slide yourself up and down, letting out [pc.moans+] as you impale yourself on [npc.name]'s [npc.cock+].",
				"You lift yourself up before greedily sliding back down, letting out [pc.a_moan+] as you spear your [pc.pussy+] on [npc.name]'s [npc.penis+].");
	}
	
	public static String getPlayerRidingCockRough() {
		return UtilText.returnStringAtRandom(
				"Using your legs, you start to rapidly bounce yourself up and down, slamming [npc.name]'s [npc.penis+] in and out of your [pc.pussy+] as you [pc.moanVerb+] in pleasure.",
				"Leaning forwards, you use [npc.name]'s [npc.breasts+] to support some of your weight before starting to rapidly buck your [pc.hips+] up and down on [npc.her] [npc.penis+]."
						+ " As your face comes closer to [npc.her] body, you get a waft of [npc.her] [npc.scent], and you bite your [pc.lip] as you greedily breathe in the intoxicating aroma.",
				"Placing your hands down on the floor behind you for support, you start rapidly bouncing yourself up and down, letting out shuddering, [pc.moans+] as you repeatedly impale yourself on [npc.name]'s [npc.cock+].",
				"Grinning down at [npc.name], you start enthusiastically bouncing up and down, letting out [pc.a_moan+] as you roughly spear your [pc.pussy+] on [npc.name]'s [npc.penis+].");
	}
	
	public static String getPlayerStoppingVaginalPenetrationDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		switch(Sex.getSexPace(Main.game.getPlayer())) {
			case DOM_ROUGH:
				UtilText.nodeContentSB.append("Reaching down, you roughly grab the base of [npc.name]'s [npc.cock], before lifting yourself up and allowing [npc.her] [npc.cock+] to slide out of your [pc.pussy+].");
				break;
			default:
				UtilText.nodeContentSB.append("Reaching down, you take hold of the base of [npc.name]'s [npc.cock], before lifting yourself up and allowing [npc.her] [npc.cock+] to slide out of your [pc.pussy+].");
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
						"Reaching down to take hold of [npc.name]'s [npc.cock+], you shuffle around to get into a good position."
								+ " After taking a moment to tease the [npc.cockHead+] over your [pc.asshole+], you let out a little [pc.moan] as you slowly drop down and penetrate your [pc.ass+] on [npc.her] [npc.cock+].",
						"Shuffling around into a better position, you line the [npc.cockHead+] of [npc.name]'s [npc.cock+] up between your ass cheeks, and with a slow, steady movement, you gently drop down, penetrating your [pc.asshole+]"
								+ " on [npc.her] [npc.cock+]."));
				break;
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching down to take a firm hold of [npc.name]'s [npc.cock+], you shuffle around to get into a good position."
								+ " After taking a moment to tease the [npc.cockHead+] over your [pc.asshole+], you let out [pc.a_moan+] as you eagerly drop down and penetrate your [pc.ass+] on [npc.her] [npc.cock+].",
						"Shuffling around into a better position, you line the [npc.cockHead+] of [npc.name]'s [npc.cock+] up between your ass cheeks, and with a single movement, you eagerly drop down, penetrating your [pc.asshole+]"
								+ " on [npc.her] [npc.cock+]."));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching down to roughly grab [npc.name]'s [npc.cock+], you shuffle around to get into a good position."
								+ " After taking a moment to tease the [npc.cockHead+] over your [pc.asshole+], you let out [pc.a_moan+] as you forcefully drop down and penetrate your [pc.ass+] on [npc.her] [npc.cock+].",
						"Shuffling around into a better position, you line the [npc.cockHead+] of [npc.name]'s [npc.cock+] up between your ass cheeks, and with a single movement, you roughly drop down, penetrating your [pc.asshole+]"
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
				"Using your legs, you slowly lift yourself up and down, sliding [npc.name]'s [npc.penis+] in and out of your [pc.asshole+] as you [pc.moanVerb+] out loud.",
				"Leaning forwards, you use your [pc.hands] to support some of your weight as you slowly slide up and down on [npc.name]'s [npc.penis+]."
						+ " As you lower your [pc.face+] towards [npc.herHim], you get a waft of [npc.her] [npc.scent], and you bite your [pc.lip] as you breathe in the intoxicating aroma.",
				"Lowering your [pc.hands] to your knees, you use your [pc.legs] to slowly slide yourself up and down, letting out soft [pc.moans] as you impale yourself on [npc.name]'s [npc.cock+].",
				"You slowly lift yourself up before sliding back down, letting out a soft [pc.moan] as you spear your [pc.asshole+] on [npc.name]'s [npc.penis+].");
	}
	
	public static String getPlayerRidingCockAnallyNormal() {
		return UtilText.returnStringAtRandom(
				"Using your legs, you lift yourself up and down, sliding [npc.name]'s [npc.penis+] in and out of your [pc.asshole+] as you [pc.moanVerb+] out loud.",
				"Leaning forwards, you use your [pc.hands] to support some of your weight as you eagerly slide up and down on [npc.name]'s [npc.penis+]."
						+ " As you lower your [pc.face+] towards [npc.herHim], you get a waft of [npc.her] [npc.scent], and you bite your [pc.lip] as you greedily breathe in the intoxicating aroma.",
				"Lowering your [pc.hands] to your knees, you use your [pc.legs] to eagerly slide yourself up and down, letting out [pc.moans+] as you impale yourself on [npc.name]'s [npc.cock+].",
				"You lift yourself up before greedily sliding back down, letting out [pc.a_moan+] as you spear your [pc.asshole+] on [npc.name]'s [npc.penis+].");
	}
	
	public static String getPlayerRidingCockAnallyRough() {
		return UtilText.returnStringAtRandom(
				"Using your legs, you start to rapidly bounce yourself up and down, slamming [npc.name]'s [npc.penis+] in and out of your [pc.asshole+] as you [pc.moanVerb+] in pleasure.",
				"Leaning forwards, you use [npc.name]'s [npc.breasts+] to support some of your weight before starting to rapidly buck your [pc.hips+] up and down on [npc.her] [npc.penis+]."
						+ " As your face comes closer to [npc.her] body, you get a waft of [npc.her] [npc.scent], and you bite your [pc.lip] as you greedily breathe in the intoxicating aroma.",
				"Placing your hands down on the floor behind you for support, you start rapidly bouncing yourself up and down, letting out shuddering, [pc.moans+] as you repeatedly impale yourself on [npc.name]'s [npc.cock+].",
				"Grinning down at [npc.name], you start enthusiastically bouncing up and down, letting out [pc.a_moan+] as you roughly spear your [pc.asshole+] on [npc.name]'s [npc.penis+].");
	}
	
	public static String getPlayerStoppingAnalPenetrationDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		switch(Sex.getSexPace(Main.game.getPlayer())) {
			case DOM_ROUGH:
				UtilText.nodeContentSB.append("Reaching back beneath you, you roughly grab the base of [npc.name]'s [npc.cock], before lifting yourself up and allowing [npc.her] [npc.cock+] to slide out of your [pc.asshole+].");
				break;
			default:
				UtilText.nodeContentSB.append("Reaching back beneath you, you take hold of the base of [npc.name]'s [npc.cock], before lifting yourself up and allowing [npc.her] [npc.cock+] to slide out of your [pc.asshole+].");
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
}
