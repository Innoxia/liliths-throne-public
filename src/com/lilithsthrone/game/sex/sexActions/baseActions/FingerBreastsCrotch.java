package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.1
 * @version 0.3.1
 * @author Innoxia
 */
public class FingerBreastsCrotch {

	public static final SexAction FEEL_BREASTS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.BREAST_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Grope udders";
			} else {
				return "Grope crotch-boobs";
			}
		}

		@Override
		public String getActionDescription() {
			return "Give [npc2.namePos] [npc2.crotchBoobs+] a squeeze.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).hasBreastsCrotch()
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.NIPPLES_CROTCH)){
				UtilText.nodeContentSB.setLength(0);
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(start)] fondling and groping [npc2.namePos] [npc2.crotchBoobsRows] [npc2.crotchBoobs+],"
										+ " softly pressing [npc2.her] [npc2.lowClothing(NIPPLES_CROTCH)] down against [npc2.her] [npc2.crotchNipples+] in the process.",

								"[npc.Name] [npc.verb(find)] [npc.herself] unable to resist the temptation of [npc2.namePos] [npc2.crotchBoobs+], and [npc.she] [npc.verb(reach)] down to gently press [npc.her] [npc.hands+]"
									+ " against [npc2.her] [npc2.topClothing(NIPPLES_CROTCH)], before starting to softly grope and squeeze [npc2.her] lower abdomen.",

								"Teasing [npc.her] [npc.fingers] over [npc2.namePos] [npc2.topClothing(NIPPLES_CROTCH)], [npc.name] [npc.verb(start)] to gently fondle and grope [npc2.her] [npc2.crotchBoobs+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] eagerly fondling and groping [npc2.her] [npc2.crotchBoobsRows] [npc2.crotchBoobs+],"
										+ " pressing [npc2.her] [npc2.lowClothing(NIPPLES_CROTCH)] down against [npc2.her] [npc2.crotchNipples+] in the process.",

								"[npc.Name] [npc.verb(find)] [npc.herself] unable to resist the temptation of [npc2.namePos] [npc2.crotchBoobs+], and [npc.she] [npc.verb(reach)] down to eagerly press [npc.her] [npc.hands+]"
									+ " against [npc2.her] [npc2.topClothing(NIPPLES_CROTCH)], before starting to grope and squeeze [npc2.her] lower abdomen.",

								"Teasing [npc.her] [npc.fingers] over [npc2.namePos] [npc2.topClothing(NIPPLES_CROTCH)], [npc.name] [npc.verb(start)] to eagerly fondle and grope [npc2.her] [npc2.crotchBoobs+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] roughly fondling and groping [npc2.her] [npc2.crotchBoobsRows] [npc2.crotchBoobs+],"
										+ " forcefully grinding [npc2.her] [npc2.lowClothing(NIPPLES_CROTCH)] down against [npc2.her] [npc2.crotchNipples+] in the process.",

								"[npc.Name] [npc.verb(find)] [npc.herself] unable to resist the temptation of [npc2.namePos] [npc2.crotchBoobs+], and [npc.she] [npc.verb(reach)] down to forcefully grind [npc.her] [npc.hands+]"
									+ " against [npc2.her] [npc2.topClothing(NIPPLES_CROTCH)], before starting to roughly grope and squeeze [npc2.her] lower abdomen.",

								"Sinking [npc.her] [npc.fingers] into [npc2.namePos] [npc2.topClothing(NIPPLES_CROTCH)], [npc.name] [npc.verb(start)] to roughly fondle and grope [npc2.her] [npc2.crotchBoobs+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] eagerly fondling and groping [npc2.her] [npc2.crotchBoobsRows] [npc2.crotchBoobs+],"
										+ " pressing [npc2.her] [npc2.lowClothing(NIPPLES_CROTCH)] down against [npc2.her] [npc2.crotchNipples+] in the process.",

								"[npc.Name] [npc.verb(find)] [npc.herself] unable to resist the temptation of [npc2.namePos] [npc2.crotchBoobs+], and [npc.she] [npc.verb(reach)] down to eagerly press [npc.her] [npc.hands+]"
									+ " against [npc2.namePos] [npc2.topClothing(NIPPLES_CROTCH)], before starting to grope and squeeze [npc2.her] lower abdomen.",

								"Teasing [npc.her] [npc.fingers] over [npc2.namePos] [npc2.topClothing(NIPPLES_CROTCH)], [npc.name] [npc.verb(start)] to eagerly fondle and grope [npc2.her] [npc2.crotchBoobs+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] fondling and groping [npc2.her] [npc2.crotchBoobsRows] [npc2.crotchBoobs+],"
										+ " pressing [npc2.her] [npc2.lowClothing(NIPPLES_CROTCH)] down against [npc2.her] [npc2.crotchNipples+] in the process.",

								"[npc.Name] [npc.verb(find)] [npc.herself] unable to resist the temptation of [npc2.namePos] [npc2.crotchBoobs+], and [npc.she] [npc.verb(reach)] down to press [npc.her] [npc.hands+]"
									+ " against [npc2.her] [npc2.topClothing(NIPPLES_CROTCH)], before starting to grope and squeeze [npc2.her] lower abdomen.",

								"Teasing [npc.her] [npc.fingers] over [npc2.namePos] [npc2.topClothing(NIPPLES_CROTCH)], [npc.name] [npc.verb(start)] to fondle and grope [npc2.her] [npc2.crotchBoobs+]."));
						break;
					default:
						break;
				}
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] at [npc.namePos] touch, before gently encouraging [npc.herHim] to continue giving [npc2.her] [npc2.crotchBoobs+] [npc.her] full attention.",
	
									" With a soft [npc2.moan], [npc2.name] slowly [npc2.verb(push)] [npc2.her] stomach out, imploring [npc.name] to continue.",
	
									" Softly [npc2.moaning] at [npc.namePos] touch, [npc2.name] gently [npc2.verb(encourage)] [npc.herHim] to carry on playing with [npc2.her] [npc2.crotchBoobs+]."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] at [npc.namePos] touch, before eagerly encouraging [npc.herHim] to continue giving [npc2.her] [npc2.crotchBoobs+] [npc.her] full attention.",
	
									" With [npc2.a_moan+], [npc2.name] eagerly [npc2.verb(push)] [npc2.her] stomach out, imploring [npc.name] to continue.",
	
									" [npc2.Moaning+] at [npc.namePos] touch, [npc2.name] eagerly [npc2.verb(encourage)] [npc.herHim] to carry on playing with [npc2.her] [npc2.crotchBoobs+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] at [npc.namePos] touch, before roughly growling for [npc.herHim] to continue giving [npc2.her] [npc2.crotchBoobs+] [npc.her] full attention.",
	
									" With [npc2.a_moan+], [npc2.name] [npc2.verb(push)] [npc2.her] stomach out, and in a firm tone,"
											+ " [npc2.she] [npc2.verb(order)] [npc.name] to continue giving [npc2.her] [npc2.crotchBoobs+] [npc.her] full attention.",
	
									" Letting out [npc2.a_moan+] at [npc.namePos] touch, [npc2.name] [npc2.verb(demand)] that [npc.she] [npc.verb(carry)] on playing with [npc2.her] [npc2.crotchBoobs+]."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] at [npc.namePos] touch, before eagerly encouraging [npc.herHim] to continue giving [npc2.her] [npc2.crotchBoobs+] [npc.her] full attention.",
	
									" With [npc2.a_moan+], [npc2.name] eagerly [npc2.verb(push)] [npc2.her] stomach out, imploring [npc.name] to continue.",
	
									" [npc2.Moaning+] at [npc.namePos] touch, [npc2.name] eagerly [npc2.verb(encourage)] [npc.herHim] to carry on playing with [npc2.her] [npc2.crotchBoobs+]."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] at [npc.namePos] touch, before encouraging [npc.herHim] to continue giving [npc2.her] [npc2.crotchBoobs+] [npc.her] full attention.",
	
									" With [npc2.a_moan+], [npc2.name] [npc2.verb(push)] [npc2.her] stomach out, imploring [npc.name] to continue.",
	
									" [npc2.Moaning+] at [npc.namePos] touch, [npc2.name] [npc2.verb(encourage)] [npc.herHim] to carry on playing with [npc2.her] [npc2.crotchBoobs+]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] instinctively [npc2.verb(try)] to pull back,"
											+ " [npc2.sobbing] and struggling against [npc.namePos] touch as [npc2.she] [npc2.verb(try)] to knock [npc.her] [npc.fingers] away from [npc2.her] [npc2.crotchBoobs+].",
	
									" With [npc2.a_sob+], [npc2.name] [npc2.verb(start)] writhing around in discomfort,"
											+ " pleading for [npc.name] to leave [npc2.herHim] alone as [npc.she] [npc.verb(continue)] playing with [npc2.namePos] [npc2.crotchBoobs+].",
	
									" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] at [npc.namePos] touch, and as [npc.she] [npc.verb(carry)] on playing with [npc2.her] [npc2.crotchBoobs+],"
											+ " [npc2.name] [npc2.verb(continue)] to struggle against [npc.herHim]."));
							break;
						default:
							break;
					}
				}
				switch (Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" A small trickle of [npc2.crotchMilk] leaks out into [npc2.namePos] [npc2.lowClothing(NIPPLES_CROTCH)] as [npc.name] squeezes down on [npc2.her] [npc2.crotchNipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" A small squirt of [npc2.crotchMilk] leaks out into [npc2.namePos] [npc2.lowClothing(NIPPLES_CROTCH)] as [npc.name] squeezes down on [npc2.her] [npc2.crotchNipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" A trickle of [npc2.crotchMilk] runs out into [npc2.namePos] [npc2.lowClothing(NIPPLES_CROTCH)] as [npc.name] squeezes down on [npc2.her] [npc2.crotchNipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.crotchMilk] [npc.verb(start)] to flow out into [npc2.her] [npc2.lowClothing(NIPPLES_CROTCH)],"
								+ " and [npc2.she] [npc2.moanVerb] as [npc2.she] [npc2.verb(feel)] it running down over [npc2.her] [npc2.crotchBoobs+].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.crotchMilk] [npc.verb(start)] drooling out in a little stream into [npc2.her] [npc2.lowClothing(NIPPLES_CROTCH)] as [npc.name] [npc.verb(squeeze)]"
								+ " down on [npc2.her] [npc2.crotchNipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.crotchMilk] [npc.verb(start)] pouring out in a constant stream, quickly soaking [npc2.her] [npc2.lowClothing(NIPPLES_CROTCH)].");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.crotchMilk] [npc.verb(start)] pouring out in a heavy flow, quickly soaking [npc2.her] [npc2.lowClothing(NIPPLES_CROTCH)].");
						break;
					default:
						break;
				}
		
				return UtilText.nodeContentSB.toString();
				
			} else {
				
				UtilText.nodeContentSB.setLength(0);
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(start)] gently fondling and groping [npc2.her] [npc2.crotchBoobsRows] [npc2.crotchBoobs+].",

								"[npc.Name] [npc.verb(find)] [npc.herself] unable to resist the temptation of [npc2.namePos] [npc2.crotchBoobs+],"
										+ " and [npc.she] [npc.verb(reach)] down to gently start groping and squeezing [npc2.her] lower abdomen.",

								"Teasing [npc.her] [npc.fingers] over [npc2.namePos] lower abdomen, [npc.name] [npc.verb(start)] to gently fondle and grope [npc2.namePos] [npc2.crotchBoobs+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] eagerly fondling and groping [npc2.her] [npc2.crotchBoobsRows] [npc2.crotchBoobs+].",

								"[npc.Name] [npc.verb(find)] [npc.herself] unable to resist the temptation of [npc2.namePos] [npc2.crotchBoobs+],"
										+ " and [npc.she] [npc.verb(reach)] down to eagerly start groping and squeezing [npc2.her] lower abdomen.",

								"Teasing [npc.her] [npc.fingers] over [npc2.namePos] lower abdomen, [npc.name] [npc.verb(start)] to eagerly fondle and grope [npc2.namePos] [npc2.crotchBoobs+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] roughly fondling and groping [npc2.her] [npc2.crotchBoobsRows] [npc2.crotchBoobs+].",

								"[npc.Name] [npc.verb(find)] [npc.herself] unable to resist the temptation of [npc2.namePos] [npc2.crotchBoobs+],"
										+ " and [npc.she] [npc.verb(reach)] down to roughly start groping and squeezing [npc2.her] lower abdomen.",

								"Sinking [npc.her] [npc.fingers] into [npc2.namePos] lower abdomen, [npc.name] [npc.verb(start)] to roughly fondle and grope [npc2.namePos] [npc2.crotchBoobs+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] eagerly fondling and groping [npc2.her] [npc2.crotchBoobsRows] [npc2.crotchBoobs+].",

								"[npc.Name] [npc.verb(find)] [npc.herself] unable to resist the temptation of [npc2.namePos] [npc2.crotchBoobs+],"
										+ " and [npc.she] [npc.verb(reach)] down to eagerly start groping and squeezing [npc2.her] lower abdomen.",

								"Teasing [npc.her] [npc.fingers] over [npc2.namePos] lower abdomen, [npc.name] [npc.verb(start)] to eagerly fondle and grope [npc2.namePos] [npc2.crotchBoobs+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] fondling and groping [npc2.her] [npc2.crotchBoobsRows] [npc2.crotchBoobs+].",

								"[npc.Name] [npc.verb(find)] [npc.herself] unable to resist the temptation of [npc2.namePos] [npc2.crotchBoobs+],"
										+ " and [npc.she] [npc.verb(reach)] down to start groping and squeezing [npc2.her] lower abdomen.",

								"Teasing [npc.her] [npc.fingers] over [npc2.namePos] lower abdomen, [npc.name] [npc.verb(start)] to fondle and grope [npc2.namePos] [npc2.crotchBoobs+]."));
						break;
					default:
						break;
				}
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] in response to [npc.namePos] touch,"
											+ " before gently encouraging [npc.herHim] to continue giving [npc2.her] [npc2.crotchBoobs+] [npc.her] full attention.",
	
									" With a soft [npc2.moan], [npc2.name] slowly [npc2.verb(push)] [npc2.her] stomach out, imploring [npc.name] to continue.",
	
									" Softly [npc2.moaning] at [npc.namePos] touch, [npc2.name] gently [npc2.verb(encourage)] [npc.herHim] to carry on playing with [npc2.her] [npc2.crotchBoobs+]."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response to [npc.namePos] touch,"
											+ " before eagerly encouraging [npc.herHim] to continue giving [npc2.her] [npc2.crotchBoobs+] [npc.her] full attention.",
	
									" With [npc2.a_moan+], [npc2.name] eagerly [npc2.verb(push)] [npc2.her] stomach out, imploring [npc.herHim] to continue.",
	
									" [npc2.Moaning+] at [npc.namePos] touch, [npc2.name] eagerly [npc2.verb(encourage)] [npc.herHim] to carry on playing with [npc2.her] [npc2.crotchBoobs+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response to [npc.namePos] touch,"
											+ " before roughly growling for [npc.herHim] to continue giving [npc2.namePos] [npc2.crotchBoobs+] [npc.her] full attention.",
	
									" With [npc2.a_moan+], [npc2.name] [npc2.verb(push)] [npc2.her] stomach out, and in a firm tone, [npc2.name] order [npc.herHim] to continue before carrying on making lewd noises.",
	
									" Letting out [npc2.a_moan+] at [npc.namePos] touch, [npc2.name] [npc2.verb(demand)] that [npc.she] [npc.verb(carry)] on playing with [npc2.her] [npc2.crotchBoobs+]."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response to [npc.namePos] touch,"
											+ " before eagerly encouraging [npc.herHim] to continue giving [npc2.her] [npc2.crotchBoobs+] [npc.her] full attention.",
	
									" With [npc2.a_moan+], [npc2.name] eagerly [npc2.verb(push)] [npc2.her] stomach out, imploring [npc.herHim] to continue.",
	
									" [npc2.Moaning+] at [npc.namePos] touch, [npc2.name] eagerly [npc2.verb(encourage)] [npc.herHim] to carry on playing with [npc2.her] [npc2.crotchBoobs+]."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response to [npc.namePos] touch,"
											+ " before encouraging [npc.herHim] to continue giving [npc2.her] [npc2.crotchBoobs+] [npc.her] full attention.",
	
									" With [npc2.a_moan+], [npc2.name] [npc2.verb(push)] [npc2.her] stomach out, imploring [npc.herHim] to continue.",
	
									" [npc2.Moaning+] at [npc.namePos] touch, [npc2.name] [npc2.verb(encourage)] [npc.herHim] to carry on playing with [npc2.her] [npc2.crotchBoobs+]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] instinctively [npc2.verb(try)] to pull back,"
											+ " [npc2.sobbing] and struggling against [npc.namePos] touch as [npc2.she] [npc2.verb(try)] to knock [npc.namePos] [npc.fingers] away from [npc2.her] [npc2.crotchBoobs+].",
	
									" With [npc2.a_sob+], [npc2.name] [npc2.verb(start)] writhing around in discomfort,"
											+ " pleading for [npc.namePos] to leave [npc2.herHim] alone as [npc.she] [npc.verb(continue)] playing with [npc2.her] [npc2.crotchBoobs+].",
	
									" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] in response to [npc.namePos] touch,"
											+ " and as [npc.she] [npc.verb(carry)] on playing with [npc2.her] [npc2.crotchBoobs+], [npc2.name] [npc2.verb(continue)] to struggle against [npc.herHim]."));
							break;
						default:
							break;
					}
				}
				switch (Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" A small trickle of [npc2.crotchMilk] leaks out onto [npc.namePos] fingertips as [npc.she] squeezes down on [npc2.namePos] [npc2.crotchNipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" A small squirt of [npc2.crotchMilk] leaks out onto [npc.namePos] fingertips as [npc.she] squeezes down on [npc2.namePos] [npc2.crotchNipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" A trickle of [npc2.crotchMilk] runs out over [npc.namePos] fingertips as [npc.she] squeezes down on [npc2.namePos] [npc2.crotchNipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.crotchMilk] starts to flow out over [npc.namePos] fingers,"
								+ " and [npc2.name] [npc2.moanVerb] as [npc2.she] [npc2.verb(feel)] it running down over [npc2.her] [npc2.crotchBoobs+].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.crotchMilk] [npc.verb(start)] drooling out in a little stream over [npc.namePos] fingers as [npc.she] squeezes down on [npc2.her] [npc2.crotchNipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.crotchMilk] [npc.verb(start)] pouring out in a constant stream, quickly soaking [npc2.her] [npc2.crotchBoobs+] and dripping down onto the floor beneath [npc2.herHim].");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.crotchMilk] [npc.verb(start)] pouring out in a heavy flow,"
								+ " quickly soaking [npc2.her] [npc2.crotchBoobs+] and dripping down to form a large pool on the floor beneath [npc2.herHim].");
						break;
					default:
						break;
				}
		
				return UtilText.nodeContentSB.toString();
				
			}
		}

		@Override
		public void applyEffects(){
			if(!Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.NIPPLES_CROTCH)
					&& Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchStoredMilk().getMinimumValue()>=Lactation.ONE_TRICKLE.getMinimumValue()
					&& Main.sex.getCharacterTargetedForSexAction(this).getLowestZLayerCoverableArea(CoverableArea.NIPPLES_CROTCH)!=null) {
				Main.sex.getCharacterTargetedForSexAction(this).getLowestZLayerCoverableArea(CoverableArea.NIPPLES_CROTCH).setDirty(Main.sex.getCharacterTargetedForSexAction(this), true);
			}
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterTargetedForSexAction(this).incrementBreastCrotchStoredMilk(-10);
		}
		
	};
	
	
	public static final SexAction FORCE_FEEL_BREASTS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST_CROTCH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Have udders groped";
			} else {
				return "Have crotch-boobs groped";
			}
		}

		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to give your [npc.crotchBoobs+] a good squeeze.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasBreastsCrotch()
					&& Main.sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedCrotchBoobs()
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS.getValue();
		}

		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.NIPPLES_CROTCH)){

				UtilText.nodeContentSB.setLength(0);
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc2.namePos] [npc2.hands] in [npc.hers], [npc.name] [npc.verb(guide)] them down to [npc.her] lower abdomen,"
										+ " letting out a soft [npc.moan] as [npc.she] [npc.verb(press)] them into [npc.her] [npc.crotchBoobs+],"
										+ " forcing [npc.her] [npc.lowClothing(NIPPLES_CROTCH)] down against [npc.her] [npc.crotchNipples+] in the process.",

								"[npc.Name] [npc.verb(take)] hold of [npc2.namePos] [npc2.hands], before guiding them down to gently press into [npc.her] [npc.topClothing(NIPPLES_CROTCH)],"
										+ " and holding them there for a moment as [npc.name] [npc.verb(encourage)] [npc2.herHim] to grope and squeeze [npc.namePos] [npc.crotchBoobs].",

								"Taking hold of [npc2.namePos] [npc2.hands], [npc.name] gently [npc.verb(guide)] [npc2.her] [npc2.fingers] down to [npc.her] [npc.topClothing(NIPPLES_CROTCH)],"
										+ " before softly pressing them into [npc.her] [npc.crotchBoobs+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc2.namePos] [npc2.hands] in [npc.hers], [npc.name] [npc.verb(guide)] them down to [npc.her] lower abdomen,"
										+ " letting out [npc.a_moan+] as [npc.she] eagerly [npc.verb(press)] them into [npc.her] [npc.crotchBoobs+],"
										+ " forcing [npc.her] [npc.lowClothing(NIPPLES_CROTCH)] down against [npc.her] [npc.crotchNipples+] in the process.",

								"[npc.Name] [npc.verb(take)] hold of [npc2.namePos] [npc2.hands], before eagerly guiding them down to press into [npc.her] [npc.topClothing(NIPPLES_CROTCH)],"
										+ " and holding them there for a moment as [npc.name] [npc.verb(encourage)] [npc2.herHim] to grope and squeeze [npc.namePos] [npc.crotchBoobs].",

								"Taking hold of [npc2.namePos] [npc2.hands], [npc.name] eagerly [npc.verb(guide)] [npc2.her] [npc2.fingers] down to [npc.her] [npc.topClothing(NIPPLES_CROTCH)],"
										+ " before enthusiastically pressing them into [npc.her] [npc.crotchBoobs+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc2.namePos] [npc2.hands] in [npc.hers], [npc.name] yank them down to [npc.her] lower abdomen,"
										+ " letting out [npc.a_moan+] as [npc.she] roughly [npc.verb(press)] them into [npc.her] [npc.crotchBoobs+],"
										+ " forcing [npc.her] [npc.lowClothing(NIPPLES_CROTCH)] down against [npc.her] [npc.crotchNipples+] in the process.",

								"[npc.Name] [npc.verb(take)] hold of [npc2.namePos] [npc2.hands], before roughly yanking them down to press into [npc.her] [npc.topClothing(NIPPLES_CROTCH)],"
										+ " and holding them there for a moment as [npc.name] order [npc2.herHim] to grope and squeeze [npc.namePos] [npc.crotchBoobs].",

								"Taking hold of [npc2.namePos] [npc2.hands], [npc.name] violently [npc.verb(pull)] them down to [npc.her] lower abdomen,"
										+ " forcing [npc2.her] [npc2.fingers] to press into the [npc.topClothing(NIPPLES_CROTCH)] that's covering [npc.her] [npc.crotchBoobs+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc2.namePos] [npc2.hands] in [npc.hers], [npc.name] [npc.verb(guide)] them down to [npc.her] lower abdomen,"
										+ " letting out [npc.a_moan+] as [npc.she] eagerly [npc.verb(press)] them into [npc.her] [npc.crotchBoobs+],"
										+ " forcing [npc.her] [npc.lowClothing(NIPPLES_CROTCH)] down against [npc.her] [npc.crotchNipples+] in the process.",

								"[npc.Name] [npc.verb(take)] hold of [npc2.namePos] [npc2.hands], before eagerly guiding them down to press into [npc.her] [npc.topClothing(NIPPLES_CROTCH)],"
										+ " and holding them there for a moment as [npc.name] [npc.verb(encourage)] [npc2.herHim] to grope and squeeze [npc.namePos] [npc.crotchBoobs].",

								"Taking hold of [npc2.namePos] [npc2.hands], [npc.name] eagerly [npc.verb(guide)] [npc2.her] [npc2.fingers] down to [npc.her] [npc.topClothing(NIPPLES_CROTCH)],"
										+ " before enthusiastically pressing them into [npc.her] [npc.crotchBoobs+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc2.namePos] [npc2.hands] in [npc.hers], [npc.name] [npc.verb(guide)] them down to [npc.her] lower abdomen,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(press)] them into [npc.her] [npc.crotchBoobs+],"
										+ " forcing [npc.her] [npc.lowClothing(NIPPLES_CROTCH)] down against [npc.her] [npc.crotchNipples+] in the process.",

								"[npc.Name] [npc.verb(take)] hold of [npc2.namePos] [npc2.hands], before guiding them down to press into [npc.her] [npc.topClothing(NIPPLES_CROTCH)],"
										+ " and holding them there for a moment as [npc.name] [npc.verb(encourage)] [npc2.herHim] to grope and squeeze [npc.namePos] [npc.crotchBoobs].",

								"Taking hold of [npc2.namePos] [npc2.hands], [npc.name] [npc.verb(guide)] [npc2.her] [npc2.fingers] down to [npc.her] [npc.topClothing(NIPPLES_CROTCH)],"
									+ " before pressing them into [npc.her] [npc.crotchBoobs+]."));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] in response to [npc.namePos] eagerness, before gently pressing [npc2.her] [npc2.hands] into the flesh of [npc.her] [npc.crotchBoobs+].",
	
									" With a soft [npc2.moan], [npc2.name] eagerly responds to [npc.namePos] move by gently sinking [npc2.her] [npc2.fingers] into the soft flesh of [npc.her] [npc.crotchBoobs].",
	
									" Softly [npc2.moaning], [npc2.name] [npc2.verb(start)] playing with [npc.namePos] [npc.crotchBoobs+],"
											+ " drawing pleasurable [npc.moans] from between [npc.her] [npc.lips] as [npc2.she] gently [npc2.verb(press)] [npc2.her] [npc2.fingers] into [npc.her] [npc.crotchBoobs]."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response to [npc.namePos] eagerness,"
									+ " before eagerly pressing [npc2.her] [npc2.hands] into the flesh of [npc.namePos] [npc.crotchBoobs+].",
	
									" With [npc2.a_moan+], [npc2.name] eagerly responds to [npc.namePos] move by enthusiastically sinking [npc2.her] [npc2.fingers] into the soft flesh of [npc.her] [npc.crotchBoobs].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] playing with [npc.namePos] [npc.crotchBoobs+],"
											+ " drawing pleasurable [npc.moans] from between [npc.her] [npc.lips] as [npc2.she] eagerly [npc2.verb(press)] [npc2.her] [npc2.fingers] into [npc.her] [npc.crotchBoobs]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response to [npc.namePos] eagerness,"
											+ " roughly pressing [npc2.her] [npc2.hands] into the flesh of [npc.her] [npc.crotchBoobs+] as [npc2.she] growls out that [npc2.sheIs] still the one in charge.",
	
									" With [npc2.a_moan+], [npc2.name] eagerly responds to [npc.namePos] move by roughly sinking [npc2.her] [npc2.fingers] into the soft flesh of [npc.her] [npc.crotchBoobs].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] playing with [npc.namePos] [npc.crotchBoobs+],"
											+ " drawing pleasurable [npc.moans] from between [npc.her] [npc.lips] as [npc2.she] roughly [npc2.verb(press)] [npc2.her] [npc2.fingers] into [npc.her] [npc.crotchBoobs]."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response to [npc.namePos] eagerness, before eagerly pressing [npc2.her] [npc2.hands] into the flesh of [npc.her] [npc.crotchBoobs+].",
	
									" With [npc2.a_moan+], [npc2.name] eagerly responds to [npc.namePos] move by enthusiastically sinking [npc2.her] [npc2.fingers] into the soft flesh of [npc.her] [npc.crotchBoobs].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] playing with [npc.namePos] [npc.crotchBoobs+],"
											+ " drawing pleasurable [npc.moans] from between [npc.her] [npc.lips] as [npc2.she] eagerly [npc2.verb(press)] [npc2.her] [npc2.fingers] into [npc.her] [npc.crotchBoobs]."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response to [npc.namePos] eagerness,"
											+ " before pressing [npc2.her] [npc2.hands] into the flesh of [npc.her] [npc.crotchBoobs+].",
	
									" With [npc2.a_moan+], [npc2.name] responds to [npc.namePos] move by sinking [npc2.her] [npc2.fingers] into the soft flesh of [npc.her] [npc.crotchBoobs].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] playing with [npc.namePos] [npc.crotchBoobs+],"
											+ " drawing pleasurable [npc.moans] from between [npc.her] [npc.lips] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.fingers] into [npc.her] [npc.crotchBoobs]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(try)] to pull back,"
											+ " [npc2.sobbing] and struggling against [npc.namePos] touch as [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.hands] into [npc.her] [npc.crotchBoobs+].",
	
									" With [npc2.a_sob+], [npc2.name] [npc2.verb(start)] writhing around in discomfort,"
											+ " pleading for [npc.name] to leave [npc2.herHim] alone as [npc.she] [npc2.verb(continue)] forcing [npc2.namePos] [npc2.hands] into [npc.her] [npc.crotchBoobs+].",
	
									" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+],"
											+ " and as [npc.name] [npc.verb(carry)] on forcing [npc2.her] [npc2.hands] into [npc.her] [npc.crotchBoobs+], [npc2.she] [npc2.verb(continue)] to struggle against [npc.her] touch."));
							break;
						default:
							break;
					}
				}
				
				switch (Main.sex.getCharacterPerformingAction().getBreastCrotchStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" A small trickle of [npc.crotchMilk] leaks out into [npc.namePos] [npc.lowClothing(NIPPLES_CROTCH)] as [npc2.name] squeezes down on [npc.her] [npc.crotchNipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" A small squirt of [npc.crotchMilk] leaks out into [npc.namePos] [npc.lowClothing(NIPPLES_CROTCH)] as [npc2.name] squeezes down on [npc.her] [npc.crotchNipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" A trickle of [npc.crotchMilk] runs out into [npc.namePos] [npc.lowClothing(NIPPLES_CROTCH)] as [npc2.name] squeezes down on [npc.her] [npc.crotchNipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" [npc.NamePos] [npc.crotchMilk] starts to flow out into [npc.her] [npc.lowClothing(NIPPLES_CROTCH)],"
								+ " and [npc.she] [npc.moanVerb] as [npc.she] [npc.verb(feel)] it running down over [npc.her] [npc.crotchBoobs+].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" [npc.NamePos] [npc.crotchMilk] starts drooling out in a little stream into [npc.her] [npc.lowClothing(NIPPLES_CROTCH)] as [npc2.name] squeezes down on [npc.her] [npc.crotchNipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" [npc.NamePos] [npc.crotchMilk] starts pouring out in a constant stream, quickly soaking [npc.her] [npc.lowClothing(NIPPLES_CROTCH)].");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" [npc.NamePos] [npc.crotchMilk] starts pouring out in a heavy flow, quickly soaking [npc.her] [npc.lowClothing(NIPPLES_CROTCH)].");
						break;
					default:
						break;
				}
		
				return UtilText.nodeContentSB.toString();
				
			} else {
				
				UtilText.nodeContentSB.setLength(0);
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc2.namePos] [npc2.hands] in [npc.hers],"
								+ " [npc.name] [npc.verb(guide)] them down to [npc.her] lower abdomen, letting out a soft [npc.moan] as [npc.name] [npc.verb(press)] them into [npc.her] [npc.crotchBoobs+].",

								"[npc.Name] [npc.verb(take)] hold of [npc2.namePos] [npc2.hands], guiding them down to gently [npc.verb(press)] into the soft flesh of [npc.her] [npc.crotchBoobs].",

								"Taking hold of [npc2.namePos] [npc2.hands], [npc.name] gently [npc.verb(guide)] [npc2.her] [npc2.fingers] down to softly press into [npc.her] [npc.crotchBoobs+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc2.namePos] [npc2.hands] in [npc.hers],"
								+ " [npc.name] [npc.verb(guide)] them down to [npc.her] lower abdomen, letting out [npc.a_moan+] as [npc.name] eagerly [npc.verb(press)] them into [npc.her] [npc.crotchBoobs+].",

								"[npc.Name] [npc.verb(take)] hold of [npc2.namePos] [npc2.hands], eagerly guiding them down to press into the soft flesh of [npc.her] [npc.crotchBoobs].",

								"Taking hold of [npc2.namePos] [npc2.hands], [npc.name] eagerly [npc.verb(guide)] [npc2.her] [npc2.fingers] down to enthusiastically press into [npc.her] [npc.crotchBoobs+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc2.namePos] [npc2.hands] in [npc.hers],"
								+ " [npc.name] yank them down to [npc.her] lower abdomen, letting out [npc.a_moan+] as [npc.name] roughly [npc.verb(press)] them into [npc.her] [npc.crotchBoobs+].",

								"[npc.Name] [npc.verb(take)] hold of [npc2.namePos] [npc2.hands], roughly yanking them down to press into the soft flesh of [npc.her] [npc.crotchBoobs].",

								"Taking hold of [npc2.namePos] [npc2.hands],"
								+ " [npc.name] violently [npc.verb(pull)] them down to [npc.her] lower abdomen, forcing [npc2.her] [npc2.fingers] to press into the soft flesh of [npc.her] [npc.crotchBoobs+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc2.namePos] [npc2.hands] in [npc.hers],"
								+ " [npc.name] [npc.verb(guide)] them down to [npc.her] lower abdomen, letting out [npc.a_moan+] as [npc.name] eagerly [npc.verb(press)] them into [npc.her] [npc.crotchBoobs+].",

								"[npc.Name] [npc.verb(take)] hold of [npc2.namePos] [npc2.hands], eagerly guiding them down to press into the soft flesh of [npc.her] [npc.crotchBoobs].",

								"Taking hold of [npc2.namePos] [npc2.hands], [npc.name] eagerly [npc.verb(guide)] [npc2.her] [npc2.fingers] down to enthusiastically press them into [npc.her] [npc.crotchBoobs+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc2.namePos] [npc2.hands] in [npc.hers],"
								+ " [npc.name] [npc.verb(guide)] them down to [npc.her] lower abdomen, letting out [npc.a_moan+] as [npc.name] [npc.verb(press)] them into [npc.her] [npc.crotchBoobs+].",

								"[npc.Name] [npc.verb(take)] hold of [npc2.namePos] [npc2.hands], guiding them down to [npc.verb(press)] into the soft flesh of [npc.her] [npc.crotchBoobs].",

								"Taking hold of [npc2.namePos] [npc2.hands], [npc.name] [npc.verb(guide)] [npc2.her] [npc2.fingers] down to press them into [npc.her] [npc.crotchBoobs+]."));
						break;
					default:
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] in response to [npc.namePos] eagerness, before gently pressing [npc2.her] [npc2.hands] into the flesh of [npc.her] [npc.crotchBoobs+].",
	
									" With a soft [npc2.moan], [npc2.name] eagerly responds to [npc.namePos] move by gently sinking [npc2.her] [npc2.fingers] into the soft flesh of [npc.her] [npc.crotchBoobs].",
	
									" Softly [npc2.moaning], [npc2.name] [npc2.verb(start)] playing with [npc.namePos] [npc.crotchBoobs+],"
											+ " drawing pleasurable [npc.moans] from between [npc.her] [npc.lips] as [npc2.she] gently [npc2.verb(press)] [npc2.her] [npc2.fingers] into [npc.her] [npc.crotchBoobs]."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response to [npc.namePos] eagerness, before eagerly pressing [npc2.her] [npc2.hands] into the flesh of [npc.her] [npc.crotchBoobs+].",
	
									" With [npc2.a_moan+], [npc2.name] eagerly responds to [npc.namePos] move by enthusiastically sinking [npc2.her] [npc2.fingers] into the soft flesh of [npc.her] [npc.crotchBoobs].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] playing with [npc.namePos] [npc.crotchBoobs+],"
											+ " drawing pleasurable [npc.moans] from between [npc.her] [npc.lips] as [npc2.she] eagerly [npc2.verb(press)] [npc2.her] [npc2.fingers] into [npc.her] [npc.crotchBoobs]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response to [npc.namePos] eagerness,"
											+ " roughly pressing [npc2.her] [npc2.hands] into the flesh of [npc.her] [npc.crotchBoobs+] as [npc2.she] growls out that [npc2.sheIs] still the one in charge.",
	
									" With [npc2.a_moan+], [npc2.name] eagerly responds to [npc.namePos] move by roughly sinking [npc2.her] [npc2.fingers] into the soft flesh of [npc.her] [npc.crotchBoobs].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] playing with [npc.namePos] [npc.crotchBoobs+],"
											+ " drawing pleasurable [npc.moans] from between [npc.her] [npc.lips] as [npc2.she] roughly [npc2.verb(press)] [npc2.her] [npc2.fingers] into [npc.her] [npc.crotchBoobs]."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response to [npc.namePos] eagerness, before eagerly pressing [npc2.her] [npc2.hands] into the flesh of [npc.her] [npc.crotchBoobs+].",
	
									" With [npc2.a_moan+], [npc2.name] eagerly responds to [npc.namePos] move by enthusiastically sinking [npc2.her] [npc2.fingers] into the soft flesh of [npc.her] [npc.crotchBoobs].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] playing with [npc.namePos] [npc.crotchBoobs+],"
											+ " drawing pleasurable [npc.moans] from between [npc.her] [npc.lips] as [npc2.she] eagerly [npc2.verb(press)] [npc2.her] [npc2.fingers] into [npc.her] [npc.crotchBoobs]."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response to [npc.namePos] eagerness, before pressing [npc2.her] [npc2.hands] into the flesh of [npc.her] [npc.crotchBoobs+].",
	
									" With [npc2.a_moan+], [npc2.name] responds to [npc.namePos] move by sinking [npc2.her] [npc2.fingers] into the soft flesh of [npc.her] [npc.crotchBoobs].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] playing with [npc.namePos] [npc.crotchBoobs+],"
											+ " drawing pleasurable [npc.moans] from between [npc.her] [npc.lips] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.fingers] into [npc.her] [npc.crotchBoobs]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] tries to pull back, [npc2.sobbing] and struggling against [npc.namePos] touch as [npc.she] [npc.verb(force)] [npc2.her] [npc2.hands] into [npc.her] [npc.crotchBoobs+].",
	
									" With [npc2.a_sob+], [npc2.name] [npc2.verb(start)] writhing around in discomfort,"
											+ " pleading for [npc.name] to leave [npc2.herHim] alone as [npc.she] [npc2.verb(continue)] forcing [npc2.her] [npc2.hands] into [npc.her] [npc.crotchBoobs+].",
	
									" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+],"
											+ " and as [npc.name] [npc.verb(carry)] on forcing [npc2.her] [npc2.hands] into [npc.her] [npc.crotchBoobs+], [npc2.namePos] [npc2.verb(continue)] to struggle against [npc.her] touch."));
							break;
						default:
							break;
					}
				}
				
				switch (Main.sex.getCharacterPerformingAction().getBreastCrotchStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" A small trickle of [npc.crotchMilk] leaks out onto [npc2.namePos] fingertips as [npc2.she] squeezes down on [npc.namePos] [npc.crotchNipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" A small squirt of [npc.crotchMilk] leaks out onto [npc2.namePos] fingertips as [npc2.she] squeezes down on [npc.namePos] [npc.crotchNipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" A trickle of [npc.crotchMilk] runs out over [npc2.namePos] fingertips as [npc2.she] squeezes down on [npc.namePos] [npc.crotchNipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" [npc.namePos] [npc.crotchMilk] starts to flow out over [npc2.namePos] fingers,"
								+ " and [npc.she] [npc.moanVerb] as [npc.she] [npc.verb(feel)] it running down over [npc.her] [npc.crotchBoobs+].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" [npc.namePos] [npc.crotchMilk] starts drooling out in a little stream over [npc2.namePos] fingers as [npc2.she] squeezes down on [npc.her] [npc.crotchNipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" [npc.namePos] [npc.crotchMilk] starts pouring out in a constant stream,"
								+ " quickly soaking [npc.her] breasts and dripping down onto the floor beneath [npc.herHim].");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" [npc.namePos] [npc.crotchMilk] starts pouring out in a heavy flow,"
								+ " quickly soaking [npc.her] breasts and dripping down to form a large pool on the floor beneath [npc.herHim].");
						break;
					default:
						break;
				}
		
				return UtilText.nodeContentSB.toString();
				
			}
		}

		@Override
		public void applyEffects(){
			if(!Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.NIPPLES_CROTCH)
					&& Main.sex.getCharacterPerformingAction().getBreastCrotchStoredMilk().getMinimumValue()>=Lactation.ONE_TRICKLE.getMinimumValue()
					&& Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.NIPPLES_CROTCH)!=null) {
				Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.NIPPLES_CROTCH).setDirty(Main.sex.getCharacterPerformingAction(), true);
			}
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterPerformingAction().incrementBreastCrotchStoredMilk(-10);
		}
		
	};
}
