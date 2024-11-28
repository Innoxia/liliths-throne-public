package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
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
 * @since 0.1.79
 * @version 0.2.8
 * @author Innoxia
 */
public class FingerBreasts {
	
	public static final SexAction FEEL_BREASTS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Grope breasts";
		}

		@Override
		public String getActionDescription() {
			return "Give [npc2.namePos] [npc2.breasts+] a squeeze.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()
					&& Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.HANDS)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.NIPPLES)){
				UtilText.nodeContentSB.setLength(0);
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc2.namePos] chest, [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(start)] fondling and groping [npc2.namePos] [npc2.breastRows] [npc2.breasts+],"
										+ " softly pressing [npc2.her] [npc2.lowClothing(NIPPLES)] down against [npc2.her] [npc2.nipples+] in the process.",

								"[npc.Name] [npc.verb(find)] [npc.herself] unable to resist the temptation of [npc2.namePos] [npc2.breasts+], and [npc.she] [npc.verb(reach)] up to gently press [npc.her] [npc.hands+]"
									+ " against [npc2.her] [npc2.topClothing(NIPPLES)], before starting to softly grope and squeeze [npc2.her] chest.",

								"Teasing [npc.her] [npc.fingers] over [npc2.namePos] [npc2.topClothing(NIPPLES)], [npc.name] [npc.verb(start)] to gently fondle and grope [npc2.her] [npc2.breasts+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc2.namePos] chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] eagerly fondling and groping [npc2.her] [npc2.breastRows] [npc2.breasts+],"
										+ " pressing [npc2.her] [npc2.lowClothing(NIPPLES)] down against [npc2.her] [npc2.nipples+] in the process.",

								"[npc.Name] [npc.verb(find)] [npc.herself] unable to resist the temptation of [npc2.namePos] [npc2.breasts+], and [npc.she] [npc.verb(reach)] up to eagerly press [npc.her] [npc.hands+]"
									+ " against [npc2.her] [npc2.topClothing(NIPPLES)], before starting to grope and squeeze [npc2.her] chest.",

								"Teasing [npc.her] [npc.fingers] over [npc2.namePos] [npc2.topClothing(NIPPLES)], [npc.name] [npc.verb(start)] to eagerly fondle and grope [npc2.her] [npc2.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc2.namePos] chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] roughly fondling and groping [npc2.her] [npc2.breastRows] [npc2.breasts+],"
										+ " forcefully grinding [npc2.her] [npc2.lowClothing(NIPPLES)] down against [npc2.her] [npc2.nipples+] in the process.",

								"[npc.Name] [npc.verb(find)] [npc.herself] unable to resist the temptation of [npc2.namePos] [npc2.breasts+], and [npc.she] [npc.verb(reach)] up to forcefully grind [npc.her] [npc.hands+]"
									+ " against [npc2.her] [npc2.topClothing(NIPPLES)], before starting to roughly grope and squeeze [npc2.her] chest.",

								"Sinking [npc.her] [npc.fingers] into [npc2.namePos] [npc2.topClothing(NIPPLES)], [npc.name] [npc.verb(start)] to roughly fondle and grope [npc2.her] [npc2.breasts+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc2.namePos] chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] eagerly fondling and groping [npc2.her] [npc2.breastRows] [npc2.breasts+],"
										+ " pressing [npc2.her] [npc2.lowClothing(NIPPLES)] down against [npc2.her] [npc2.nipples+] in the process.",

								"[npc.Name] [npc.verb(find)] [npc.herself] unable to resist the temptation of [npc2.namePos] [npc2.breasts+], and [npc.she] [npc.verb(reach)] up to eagerly press [npc.her] [npc.hands+]"
									+ " against [npc2.namePos] [npc2.topClothing(NIPPLES)], before starting to grope and squeeze [npc2.her] chest.",

								"Teasing [npc.her] [npc.fingers] over [npc2.namePos] [npc2.topClothing(NIPPLES)], [npc.name] [npc.verb(start)] to eagerly fondle and grope [npc2.her] [npc2.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc2.namePos] chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] fondling and groping [npc2.her] [npc2.breastRows] [npc2.breasts+],"
										+ " pressing [npc2.her] [npc2.lowClothing(NIPPLES)] down against [npc2.her] [npc2.nipples+] in the process.",

								"[npc.Name] [npc.verb(find)] [npc.herself] unable to resist the temptation of [npc2.namePos] [npc2.breasts+], and [npc.she] [npc.verb(reach)] up to press [npc.her] [npc.hands+]"
									+ " against [npc2.her] [npc2.topClothing(NIPPLES)], before starting to grope and squeeze [npc2.her] chest.",

								"Teasing [npc.her] [npc.fingers] over [npc2.namePos] [npc2.topClothing(NIPPLES)], [npc.name] [npc.verb(start)] to fondle and grope [npc2.her] [npc2.breasts+]."));
						break;
					default:
						break;
				}
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] at [npc.namePos] touch, before gently encouraging [npc.herHim] to continue giving [npc2.her] [npc2.breasts+] [npc.her] full attention.",
	
									" With a soft [npc2.moan], [npc2.name] slowly [npc2.verb(push)] [npc2.her] chest out, imploring [npc.name] to continue.",
	
									" Softly [npc2.moaning] at [npc.namePos] touch, [npc2.name] gently [npc2.verb(encourage)] [npc.herHim] to carry on playing with [npc2.her] [npc2.breasts+]."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] at [npc.namePos] touch, before eagerly encouraging [npc.herHim] to continue giving [npc2.her] [npc2.breasts+] [npc.her] full attention.",
	
									" With [npc2.a_moan+], [npc2.name] eagerly [npc2.verb(push)] [npc2.her] chest out, imploring [npc.name] to continue.",
	
									" [npc2.Moaning+] at [npc.namePos] touch, [npc2.name] eagerly [npc2.verb(encourage)] [npc.herHim] to carry on playing with [npc2.her] [npc2.breasts+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] at [npc.namePos] touch, before roughly growling for [npc.herHim] to continue giving [npc2.her] [npc2.breasts+] [npc.her] full attention.",
	
									" With [npc2.a_moan+], [npc2.name] [npc2.verb(push)] [npc2.her] chest out, and in a firm tone,"
											+ " [npc2.she] [npc2.verb(order)] [npc.name] to continue giving [npc2.her] [npc2.breasts+] [npc.her] full attention.",
	
									" Letting out [npc2.a_moan+] at [npc.namePos] touch, [npc2.name] [npc2.verb(demand)] that [npc.she] [npc.verb(carry)] on playing with [npc2.her] [npc2.breasts+]."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] at [npc.namePos] touch, before eagerly encouraging [npc.herHim] to continue giving [npc2.her] [npc2.breasts+] [npc.her] full attention.",
	
									" With [npc2.a_moan+], [npc2.name] eagerly [npc2.verb(push)] [npc2.her] chest out, imploring [npc.name] to continue.",
	
									" [npc2.Moaning+] at [npc.namePos] touch, [npc2.name] eagerly [npc2.verb(encourage)] [npc.herHim] to carry on playing with [npc2.her] [npc2.breasts+]."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] at [npc.namePos] touch, before encouraging [npc.herHim] to continue giving [npc2.her] [npc2.breasts+] [npc.her] full attention.",
	
									" With [npc2.a_moan+], [npc2.name] [npc2.verb(push)] [npc2.her] chest out, imploring [npc.name] to continue.",
	
									" [npc2.Moaning+] at [npc.namePos] touch, [npc2.name] [npc2.verb(encourage)] [npc.herHim] to carry on playing with [npc2.her] [npc2.breasts+]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] instinctively [npc2.verb(try)] to pull back,"
											+ " [npc2.sobbing] and struggling against [npc.namePos] touch as [npc2.she] [npc2.verb(try)] to knock [npc.her] [npc.fingers] away from [npc2.her] [npc2.breasts+].",
	
									" With [npc2.a_sob+], [npc2.name] [npc2.verb(start)] writhing around in discomfort,"
											+ " pleading for [npc.name] to leave [npc2.herHim] alone as [npc.she] [npc.verb(continue)] playing with [npc2.namePos] [npc2.breasts+].",
	
									" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] at [npc.namePos] touch, and as [npc.she] [npc.verb(carry)] on playing with [npc2.her] [npc2.breasts+],"
											+ " [npc2.name] [npc2.verb(continue)] to struggle against [npc.herHim]."));
							break;
						default:
							break;
					}
				}
				switch (Main.sex.getCharacterTargetedForSexAction(this).getBreastStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" A small trickle of [npc2.milk] leaks out into [npc2.namePos] [npc2.lowClothing(NIPPLES)] as [npc.name] [npc.verb(squeeze)] down on [npc2.her] [npc2.nipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" A small squirt of [npc2.milk] leaks out into [npc2.namePos] [npc2.lowClothing(NIPPLES)] as [npc.name] [npc.verb(squeeze)] down on [npc2.her] [npc2.nipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" A trickle of [npc2.milk] runs out into [npc2.namePos] [npc2.lowClothing(NIPPLES)] as [npc.name] [npc.verb(squeeze)] down on [npc2.her] [npc2.nipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)] to flow out into [npc2.her] [npc2.lowClothing(NIPPLES)],"
								+ " and [npc2.she] [npc2.moanVerb] as [npc2.she] [npc2.verb(feel)] it running down over [npc2.her] [npc2.breasts+].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)] drooling out in a little stream into [npc2.her] [npc2.lowClothing(NIPPLES)] as [npc.name] [npc.verb(squeeze)]"
								+ " down on [npc2.her] [npc2.nipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)] pouring out in a constant stream, quickly soaking [npc2.her] [npc2.lowClothing(NIPPLES)].");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)] pouring out in a heavy flow, quickly soaking [npc2.her] [npc2.lowClothing(NIPPLES)].");
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
								"Reaching up to [npc2.namePos] chest, [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(start)] gently fondling and groping [npc2.her] [npc2.breastRows] [npc2.breasts+].",

								"[npc.Name] [npc.verb(find)] [npc.herself] unable to resist the temptation of [npc2.namePos] [npc2.breasts+],"
										+ " and [npc.she] [npc.verb(reach)] up to gently start groping and squeezing [npc2.her] exposed chest.",

								"Teasing [npc.her] [npc.fingers] over [npc2.namePos] exposed chest, [npc.name] [npc.verb(start)] to gently fondle and grope [npc2.namePos] [npc2.breasts+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc2.namePos] chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] eagerly fondling and groping [npc2.her] [npc2.breastRows] [npc2.breasts+].",

								"[npc.Name] [npc.verb(find)] [npc.herself] unable to resist the temptation of [npc2.namePos] [npc2.breasts+],"
										+ " and [npc.she] [npc.verb(reach)] up to eagerly start groping and squeezing [npc2.her] exposed chest.",

								"Teasing [npc.her] [npc.fingers] over [npc2.namePos] exposed chest, [npc.name] [npc.verb(start)] to eagerly fondle and grope [npc2.namePos] [npc2.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc2.namePos] chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] roughly fondling and groping [npc2.her] [npc2.breastRows] [npc2.breasts+].",

								"[npc.Name] [npc.verb(find)] [npc.herself] unable to resist the temptation of [npc2.namePos] [npc2.breasts+],"
										+ " and [npc.she] [npc.verb(reach)] up to roughly start groping and squeezing [npc2.her] exposed chest.",

								"Sinking [npc.her] [npc.fingers] into [npc2.namePos] exposed chest, [npc.name] [npc.verb(start)] to roughly fondle and grope [npc2.namePos] [npc2.breasts+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc2.namePos] chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] eagerly fondling and groping [npc2.her] [npc2.breastRows] [npc2.breasts+].",

								"[npc.Name] [npc.verb(find)] [npc.herself] unable to resist the temptation of [npc2.namePos] [npc2.breasts+],"
										+ " and [npc.she] [npc.verb(reach)] up to eagerly start groping and squeezing [npc2.her] exposed chest.",

								"Teasing [npc.her] [npc.fingers] over [npc2.namePos] exposed chest, [npc.name] [npc.verb(start)] to eagerly fondle and grope [npc2.namePos] [npc2.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc2.namePos] chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] fondling and groping [npc2.her] [npc2.breastRows] [npc2.breasts+].",

								"[npc.Name] [npc.verb(find)] [npc.herself] unable to resist the temptation of [npc2.namePos] [npc2.breasts+],"
										+ " and [npc.she] [npc.verb(reach)] up to start groping and squeezing [npc2.her] exposed chest.",

								"Teasing [npc.her] [npc.fingers] over [npc2.namePos] exposed chest, [npc.name] [npc.verb(start)] to fondle and grope [npc2.namePos] [npc2.breasts+]."));
						break;
					default:
						break;
				}
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] in response to [npc.namePos] touch,"
											+ " before gently encouraging [npc.herHim] to continue giving [npc2.her] [npc2.breasts+] [npc.her] full attention.",
	
									" With a soft [npc2.moan], [npc2.name] slowly [npc2.verb(push)] [npc2.her] chest out, imploring [npc.name] to continue.",
	
									" Softly [npc2.moaning] at [npc.namePos] touch, [npc2.name] gently [npc2.verb(encourage)] [npc.herHim] to carry on playing with [npc2.her] [npc2.breasts+]."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response to [npc.namePos] touch,"
											+ " before eagerly encouraging [npc.herHim] to continue giving [npc2.her] [npc2.breasts+] [npc.her] full attention.",
	
									" With [npc2.a_moan+], [npc2.name] eagerly [npc2.verb(push)] [npc2.her] chest out, imploring [npc.herHim] to continue.",
	
									" [npc2.Moaning+] at [npc.namePos] touch, [npc2.name] eagerly [npc2.verb(encourage)] [npc.herHim] to carry on playing with [npc2.her] [npc2.breasts+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response to [npc.namePos] touch,"
											+ " before roughly growling for [npc.herHim] to continue giving [npc2.namePos] [npc2.breasts+] [npc.her] full attention.",
	
									" With [npc2.a_moan+], [npc2.name] [npc2.verb(push)] [npc2.her] chest out, and in a firm tone, [npc2.name] order [npc.herHim] to continue before carrying on making lewd noises.",
	
									" Letting out [npc2.a_moan+] at [npc.namePos] touch, [npc2.name] [npc2.verb(demand)] that [npc.she] [npc.verb(carry)] on playing with [npc2.her] [npc2.breasts+]."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response to [npc.namePos] touch,"
											+ " before eagerly encouraging [npc.herHim] to continue giving [npc2.her] [npc2.breasts+] [npc.her] full attention.",
	
									" With [npc2.a_moan+], [npc2.name] eagerly [npc2.verb(push)] [npc2.her] chest out, imploring [npc.herHim] to continue.",
	
									" [npc2.Moaning+] at [npc.namePos] touch, [npc2.name] eagerly [npc2.verb(encourage)] [npc.herHim] to carry on playing with [npc2.her] [npc2.breasts+]."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response to [npc.namePos] touch,"
											+ " before encouraging [npc.herHim] to continue giving [npc2.her] [npc2.breasts+] [npc.her] full attention.",
	
									" With [npc2.a_moan+], [npc2.name] [npc2.verb(push)] [npc2.her] chest out, imploring [npc.herHim] to continue.",
	
									" [npc2.Moaning+] at [npc.namePos] touch, [npc2.name] [npc2.verb(encourage)] [npc.herHim] to carry on playing with [npc2.her] [npc2.breasts+]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] instinctively [npc2.verb(try)] to pull back,"
											+ " [npc2.sobbing] and struggling against [npc.namePos] touch as [npc2.she] [npc2.verb(try)] to knock [npc.namePos] [npc.fingers] away from [npc2.her] [npc2.breasts+].",
	
									" With [npc2.a_sob+], [npc2.name] [npc2.verb(start)] writhing around in discomfort,"
											+ " pleading for [npc.namePos] to leave [npc2.herHim] alone as [npc.she] [npc.verb(continue)] playing with [npc2.her] [npc2.breasts+].",
	
									" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] in response to [npc.namePos] touch,"
											+ " and as [npc.she] [npc.verb(carry)] on playing with [npc2.her] [npc2.breasts+], [npc2.name] [npc2.verb(continue)] to struggle against [npc.herHim]."));
							break;
						default:
							break;
					}
				}
				switch (Main.sex.getCharacterTargetedForSexAction(this).getBreastStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" A small trickle of [npc2.milk] leaks out onto [npc.namePos] [npc.fingers] as [npc.she] [npc.verb(squeeze)] down on [npc2.namePos] [npc2.nipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" A small squirt of [npc2.milk] leaks out onto [npc.namePos] [npc.fingers] as [npc.she] [npc.verb(squeeze)] down on [npc2.namePos] [npc2.nipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" A trickle of [npc2.milk] runs out over [npc.namePos] [npc.fingers] as [npc.she] [npc.verb(squeeze)] down on [npc2.namePos] [npc2.nipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] starts to flow out over [npc.namePos] [npc.fingers],"
								+ " and [npc2.name] [npc2.moanVerb] as [npc2.she] [npc2.verb(feel)] it running down over [npc2.her] [npc2.breasts+].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)] drooling out in a little stream over [npc.namePos] [npc.fingers] as [npc.she] squeezes down on [npc2.her] [npc2.nipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)] pouring out in a constant stream, quickly soaking [npc2.her] [npc2.breasts+] and dripping down onto the floor beneath [npc2.herHim].");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)] pouring out in a heavy flow,"
								+ " quickly soaking [npc2.her] [npc2.breasts+] and dripping down to form a large pool on the floor beneath [npc2.herHim].");
						break;
					default:
						break;
				}
		
				return UtilText.nodeContentSB.toString();
				
			}
		}

		@Override
		public void applyEffects(){
			if(!Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.NIPPLES)
					&& Main.sex.getCharacterTargetedForSexAction(this).getBreastStoredMilk().getMinimumValue()>=Lactation.ONE_TRICKLE.getMinimumValue()
					&& Main.sex.getCharacterTargetedForSexAction(this).getLowestZLayerCoverableArea(CoverableArea.NIPPLES)!=null) {
				Main.sex.getCharacterTargetedForSexAction(this).getLowestZLayerCoverableArea(CoverableArea.NIPPLES).setDirty(Main.sex.getCharacterTargetedForSexAction(this), true);
			}
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterTargetedForSexAction(this).incrementBreastStoredMilk(-10);
		}
		
	};
	
	
	public static final SexAction FORCE_FEEL_BREASTS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Have breasts groped";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to give your [npc.breasts+] a good squeeze.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasBreasts()
					&& Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.HANDS)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS.getValue();
		}

		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.NIPPLES)){

				UtilText.nodeContentSB.setLength(0);
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc2.namePos] [npc2.hands] in [npc.hers], [npc.name] [npc.verb(guide)] them up to [npc.her] chest,"
										+ " letting out a soft [npc.moan] as [npc.she] [npc.verb(press)] them into [npc.her] [npc.breasts+],"
										+ " forcing [npc.her] [npc.lowClothing(NIPPLES)] down against [npc.her] [npc.nipples+] in the process.",

								"[npc.Name] [npc.verb(take)] hold of [npc2.namePos] [npc2.hands], before guiding them up to gently press into [npc.her] [npc.topClothing(NIPPLES)],"
										+ " and holding them there for a moment as [npc.name] [npc.verb(encourage)] [npc2.herHim] to grope and squeeze [npc.namePos] [npc.breasts].",

								"Taking hold of [npc2.namePos] [npc2.hands], [npc.name] gently [npc.verb(guide)] [npc2.her] [npc2.fingers] up to [npc.her] [npc.topClothing(NIPPLES)],"
										+ " before softly pressing them into [npc.her] [npc.breasts+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc2.namePos] [npc2.hands] in [npc.hers], [npc.name] [npc.verb(guide)] them up to [npc.her] chest,"
										+ " letting out [npc.a_moan+] as [npc.she] eagerly [npc.verb(press)] them into [npc.her] [npc.breasts+],"
										+ " forcing [npc.her] [npc.lowClothing(NIPPLES)] down against [npc.her] [npc.nipples+] in the process.",

								"[npc.Name] [npc.verb(take)] hold of [npc2.namePos] [npc2.hands], before eagerly guiding them up to press into [npc.her] [npc.topClothing(NIPPLES)],"
										+ " and holding them there for a moment as [npc.name] [npc.verb(encourage)] [npc2.herHim] to grope and squeeze [npc.namePos] [npc.breasts].",

								"Taking hold of [npc2.namePos] [npc2.hands], [npc.name] eagerly [npc.verb(guide)] [npc2.her] [npc2.fingers] up to [npc.her] [npc.topClothing(NIPPLES)],"
										+ " before enthusiastically pressing them into [npc.her] [npc.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc2.namePos] [npc2.hands] in [npc.hers], [npc.name] yank them up to [npc.her] chest,"
										+ " letting out [npc.a_moan+] as [npc.she] roughly [npc.verb(press)] them into [npc.her] [npc.breasts+],"
										+ " forcing [npc.her] [npc.lowClothing(NIPPLES)] down against [npc.her] [npc.nipples+] in the process.",

								"[npc.Name] [npc.verb(take)] hold of [npc2.namePos] [npc2.hands], before roughly yanking them up to press into [npc.her] [npc.topClothing(NIPPLES)],"
										+ " and holding them there for a moment as [npc.name] order [npc2.herHim] to grope and squeeze [npc.namePos] [npc.breasts].",

								"Taking hold of [npc2.namePos] [npc2.hands], [npc.name] violently [npc.verb(pull)] them up to [npc.her] chest,"
										+ " forcing [npc2.her] [npc2.fingers] to press into the [npc.topClothing(NIPPLES)] that's covering [npc.her] [npc.breasts+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc2.namePos] [npc2.hands] in [npc.hers], [npc.name] [npc.verb(guide)] them up to [npc.her] chest,"
										+ " letting out [npc.a_moan+] as [npc.she] eagerly [npc.verb(press)] them into [npc.her] [npc.breasts+],"
										+ " forcing [npc.her] [npc.lowClothing(NIPPLES)] down against [npc.her] [npc.nipples+] in the process.",

								"[npc.Name] [npc.verb(take)] hold of [npc2.namePos] [npc2.hands], before eagerly guiding them up to press into [npc.her] [npc.topClothing(NIPPLES)],"
										+ " and holding them there for a moment as [npc.name] [npc.verb(encourage)] [npc2.herHim] to grope and squeeze [npc.namePos] [npc.breasts].",

								"Taking hold of [npc2.namePos] [npc2.hands], [npc.name] eagerly [npc.verb(guide)] [npc2.her] [npc2.fingers] up to [npc.her] [npc.topClothing(NIPPLES)],"
										+ " before enthusiastically pressing them into [npc.her] [npc.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc2.namePos] [npc2.hands] in [npc.hers], [npc.name] [npc.verb(guide)] them up to [npc.her] chest,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(press)] them into [npc.her] [npc.breasts+],"
										+ " forcing [npc.her] [npc.lowClothing(NIPPLES)] down against [npc.her] [npc.nipples+] in the process.",

								"[npc.Name] [npc.verb(take)] hold of [npc2.namePos] [npc2.hands], before guiding them up to press into [npc.her] [npc.topClothing(NIPPLES)],"
										+ " and holding them there for a moment as [npc.name] [npc.verb(encourage)] [npc2.herHim] to grope and squeeze [npc.namePos] [npc.breasts].",

								"Taking hold of [npc2.namePos] [npc2.hands], [npc.name] [npc.verb(guide)] [npc2.her] [npc2.fingers] up to [npc.her] [npc.topClothing(NIPPLES)],"
									+ " before pressing them into [npc.her] [npc.breasts+]."));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] in response to [npc.namePos] eagerness, before gently pressing [npc2.her] [npc2.hands] into the flesh of [npc.her] [npc.breasts+].",
	
									" With a soft [npc2.moan], [npc2.name] eagerly responds to [npc.namePos] move by gently sinking [npc2.her] [npc2.fingers] into the soft flesh of [npc.her] [npc.breasts].",
	
									" Softly [npc2.moaning], [npc2.name] [npc2.verb(start)] playing with [npc.namePos] [npc.breasts+],"
											+ " drawing pleasurable [npc.moans] from between [npc.her] [npc.lips] as [npc2.she] gently [npc2.verb(press)] [npc2.her] [npc2.fingers] into [npc.her] [npc.breasts]."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response to [npc.namePos] eagerness,"
									+ " before eagerly pressing [npc2.her] [npc2.hands] into the flesh of [npc.namePos] [npc.breasts+].",
	
									" With [npc2.a_moan+], [npc2.name] eagerly responds to [npc.namePos] move by enthusiastically sinking [npc2.her] [npc2.fingers] into the soft flesh of [npc.her] [npc.breasts].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] playing with [npc.namePos] [npc.breasts+],"
											+ " drawing pleasurable [npc.moans] from between [npc.her] [npc.lips] as [npc2.she] eagerly [npc2.verb(press)] [npc2.her] [npc2.fingers] into [npc.her] [npc.breasts]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response to [npc.namePos] eagerness,"
											+ " roughly pressing [npc2.her] [npc2.hands] into the flesh of [npc.her] [npc.breasts+] as [npc2.she] growls out that [npc2.sheIs] still the one in charge.",
	
									" With [npc2.a_moan+], [npc2.name] eagerly responds to [npc.namePos] move by roughly sinking [npc2.her] [npc2.fingers] into the soft flesh of [npc.her] [npc.breasts].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] playing with [npc.namePos] [npc.breasts+],"
											+ " drawing pleasurable [npc.moans] from between [npc.her] [npc.lips] as [npc2.she] roughly [npc2.verb(press)] [npc2.her] [npc2.fingers] into [npc.her] [npc.breasts]."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response to [npc.namePos] eagerness, before eagerly pressing [npc2.her] [npc2.hands] into the flesh of [npc.her] [npc.breasts+].",
	
									" With [npc2.a_moan+], [npc2.name] eagerly responds to [npc.namePos] move by enthusiastically sinking [npc2.her] [npc2.fingers] into the soft flesh of [npc.her] [npc.breasts].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] playing with [npc.namePos] [npc.breasts+],"
											+ " drawing pleasurable [npc.moans] from between [npc.her] [npc.lips] as [npc2.she] eagerly [npc2.verb(press)] [npc2.her] [npc2.fingers] into [npc.her] [npc.breasts]."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response to [npc.namePos] eagerness,"
											+ " before pressing [npc2.her] [npc2.hands] into the flesh of [npc.her] [npc.breasts+].",
	
									" With [npc2.a_moan+], [npc2.name] responds to [npc.namePos] move by sinking [npc2.her] [npc2.fingers] into the soft flesh of [npc.her] [npc.breasts].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] playing with [npc.namePos] [npc.breasts+],"
											+ " drawing pleasurable [npc.moans] from between [npc.her] [npc.lips] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.fingers] into [npc.her] [npc.breasts]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(try)] to pull back,"
											+ " [npc2.sobbing] and struggling against [npc.namePos] touch as [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.hands] into [npc.her] [npc.breasts+].",
	
									" With [npc2.a_sob+], [npc2.name] [npc2.verb(start)] writhing around in discomfort,"
											+ " pleading for [npc.name] to leave [npc2.herHim] alone as [npc.she] [npc2.verb(continue)] forcing [npc2.namePos] [npc2.hands] into [npc.her] [npc.breasts+].",
	
									" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+],"
											+ " and as [npc.name] [npc.verb(carry)] on forcing [npc2.her] [npc2.hands] into [npc.her] [npc.breasts+], [npc2.she] [npc2.verb(continue)] to struggle against [npc.her] touch."));
							break;
						default:
							break;
					}
				}
				
				switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" A small trickle of [npc.milk] leaks out into [npc.namePos] [npc.lowClothing(NIPPLES)] as [npc2.name] squeezes down on [npc.her] [npc.nipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" A small squirt of [npc.milk] leaks out into [npc.namePos] [npc.lowClothing(NIPPLES)] as [npc2.name] squeezes down on [npc.her] [npc.nipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" A trickle of [npc.milk] runs out into [npc.namePos] [npc.lowClothing(NIPPLES)] as [npc2.name] squeezes down on [npc.her] [npc.nipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" [npc.NamePos] [npc.milk] starts to flow out into [npc.her] [npc.lowClothing(NIPPLES)],"
								+ " and [npc.she] [npc.moanVerb] as [npc.she] [npc.verb(feel)] it running down over [npc.her] [npc.breasts+].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" [npc.NamePos] [npc.milk] starts drooling out in a little stream into [npc.her] [npc.lowClothing(NIPPLES)] as [npc2.name] squeezes down on [npc.her] [npc.nipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" [npc.NamePos] [npc.milk] starts pouring out in a constant stream, quickly soaking [npc.her] [npc.lowClothing(NIPPLES)].");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" [npc.NamePos] [npc.milk] starts pouring out in a heavy flow, quickly soaking [npc.her] [npc.lowClothing(NIPPLES)].");
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
								+ " [npc.name] [npc.verb(guide)] them up to [npc.namePos] chest, letting out a soft [npc.moan] as [npc.name] [npc.verb(press)] them into [npc.her] [npc.breasts+].",

								"[npc.Name] [npc.verb(take)] hold of [npc2.namePos] [npc2.hands], guiding them up to gently [npc.verb(press)] into the soft flesh of [npc.her] [npc.breasts].",

								"Taking hold of [npc2.namePos] [npc2.hands], [npc.name] gently [npc.verb(guide)] [npc2.her] [npc2.fingers] up to softly [npc.verb(press)] into [npc.her] [npc.breasts+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc2.namePos] [npc2.hands] in [npc.hers],"
								+ " [npc.name] [npc.verb(guide)] them up to [npc.namePos] chest, letting out [npc.a_moan+] as [npc.name] eagerly [npc.verb(press)] them into [npc.her] [npc.breasts+].",

								"[npc.Name] [npc.verb(take)] hold of [npc2.namePos] [npc2.hands], eagerly guiding them up to press into the soft flesh of [npc.her] [npc.breasts].",

								"Taking hold of [npc2.namePos] [npc2.hands], [npc.name] eagerly [npc.verb(guide)] [npc2.her] [npc2.fingers] up to enthusiastically press them into [npc.her] [npc.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc2.namePos] [npc2.hands] in [npc.hers],"
								+ " [npc.name] yank them up to [npc.namePos] chest, letting out [npc.a_moan+] as [npc.name] roughly [npc.verb(press)] them into [npc.her] [npc.breasts+].",

								"[npc.Name] [npc.verb(take)] hold of [npc2.namePos] [npc2.hands], roughly yanking them up to press into the soft flesh of [npc.her] [npc.breasts].",

								"Taking hold of [npc2.namePos] [npc2.hands],"
								+ " [npc.name] violently [npc.verb(pull)] them up to [npc.namePos] chest, forcing [npc2.her] [npc2.fingers] to press into the soft flesh of [npc.her] [npc.breasts+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc2.namePos] [npc2.hands] in [npc.hers],"
								+ " [npc.name] [npc.verb(guide)] them up to [npc.namePos] chest, letting out [npc.a_moan+] as [npc.name] eagerly [npc.verb(press)] them into [npc.her] [npc.breasts+].",

								"[npc.Name] [npc.verb(take)] hold of [npc2.namePos] [npc2.hands], eagerly guiding them up to press into the soft flesh of [npc.her] [npc.breasts].",

								"Taking hold of [npc2.namePos] [npc2.hands], [npc.name] eagerly [npc.verb(guide)] [npc2.her] [npc2.fingers] up to enthusiastically press them into [npc.her] [npc.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc2.namePos] [npc2.hands] in [npc.hers],"
								+ " [npc.name] [npc.verb(guide)] them up to [npc.namePos] chest, letting out [npc.a_moan+] as [npc.name] [npc.verb(press)] them into [npc.her] [npc.breasts+].",

								"[npc.Name] [npc.verb(take)] hold of [npc2.namePos] [npc2.hands], guiding them up to press into the soft flesh of [npc.her] [npc.breasts].",

								"Taking hold of [npc2.namePos] [npc2.hands], [npc.name] [npc.verb(guide)] [npc2.her] [npc2.fingers] up to press them into [npc.her] [npc.breasts+]."));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] in response to [npc.namePos] eagerness, before gently pressing [npc2.her] [npc2.hands] into the flesh of [npc.her] [npc.breasts+].",
	
									" With a soft [npc2.moan], [npc2.name] eagerly responds to [npc.namePos] move by gently sinking [npc2.her] [npc2.fingers] into the soft flesh of [npc.her] [npc.breasts].",
	
									" Softly [npc2.moaning], [npc2.name] [npc2.verb(start)] playing with [npc.namePos] [npc.breasts+],"
											+ " drawing pleasurable [npc.moans] from between [npc.her] [npc.lips] as [npc2.she] gently [npc2.verb(press)] [npc2.her] [npc2.fingers] into [npc.her] [npc.breasts]."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response to [npc.namePos] eagerness, before eagerly pressing [npc2.her] [npc2.hands] into the flesh of [npc.her] [npc.breasts+].",
	
									" With [npc2.a_moan+], [npc2.name] eagerly responds to [npc.namePos] move by enthusiastically sinking [npc2.her] [npc2.fingers] into the soft flesh of [npc.her] [npc.breasts].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] playing with [npc.namePos] [npc.breasts+],"
											+ " drawing pleasurable [npc.moans] from between [npc.her] [npc.lips] as [npc2.she] eagerly [npc2.verb(press)] [npc2.her] [npc2.fingers] into [npc.her] [npc.breasts]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response to [npc.namePos] eagerness,"
											+ " roughly pressing [npc2.her] [npc2.hands] into the flesh of [npc.her] [npc.breasts+] as [npc2.she] growls out that [npc2.sheIs] still the one in charge.",
	
									" With [npc2.a_moan+], [npc2.name] eagerly responds to [npc.namePos] move by roughly sinking [npc2.her] [npc2.fingers] into the soft flesh of [npc.her] [npc.breasts].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] playing with [npc.namePos] [npc.breasts+],"
											+ " drawing pleasurable [npc.moans] from between [npc.her] [npc.lips] as [npc2.she] roughly [npc2.verb(press)] [npc2.her] [npc2.fingers] into [npc.her] [npc.breasts]."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response to [npc.namePos] eagerness, before eagerly pressing [npc2.her] [npc2.hands] into the flesh of [npc.her] [npc.breasts+].",
	
									" With [npc2.a_moan+], [npc2.name] eagerly responds to [npc.namePos] move by enthusiastically sinking [npc2.her] [npc2.fingers] into the soft flesh of [npc.her] [npc.breasts].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] playing with [npc.namePos] [npc.breasts+],"
											+ " drawing pleasurable [npc.moans] from between [npc.her] [npc.lips] as [npc2.she] eagerly [npc2.verb(press)] [npc2.her] [npc2.fingers] into [npc.her] [npc.breasts]."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response to [npc.namePos] eagerness, before pressing [npc2.her] [npc2.hands] into the flesh of [npc.her] [npc.breasts+].",
	
									" With [npc2.a_moan+], [npc2.name] responds to [npc.namePos] move by sinking [npc2.her] [npc2.fingers] into the soft flesh of [npc.her] [npc.breasts].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] playing with [npc.namePos] [npc.breasts+],"
											+ " drawing pleasurable [npc.moans] from between [npc.her] [npc.lips] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.fingers] into [npc.her] [npc.breasts]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] tries to pull back, [npc2.sobbing] and struggling against [npc.namePos] touch as [npc.she] [npc.verb(force)] [npc2.her] [npc2.hands] into [npc.her] [npc.breasts+].",
	
									" With [npc2.a_sob+], [npc2.name] [npc2.verb(start)] writhing around in discomfort,"
											+ " pleading for [npc.name] to leave [npc2.herHim] alone as [npc.she] [npc2.verb(continue)] forcing [npc2.her] [npc2.hands] into [npc.her] [npc.breasts+].",
	
									" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+],"
											+ " and as [npc.name] [npc.verb(carry)] on forcing [npc2.her] [npc2.hands] into [npc.her] [npc.breasts+], [npc2.namePos] [npc2.verb(continue)] to struggle against [npc.her] touch."));
							break;
						default:
							break;
					}
				}
				
				switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" A small trickle of [npc.milk] leaks out onto [npc2.namePos] fingertips as [npc2.she] squeezes down on [npc.namePos] [npc.nipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" A small squirt of [npc.milk] leaks out onto [npc2.namePos] fingertips as [npc2.she] squeezes down on [npc.namePos] [npc.nipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" A trickle of [npc.milk] runs out over [npc2.namePos] fingertips as [npc2.she] squeezes down on [npc.namePos] [npc.nipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" [npc.NamePos] [npc.milk] starts to flow out over [npc2.namePos] fingers,"
								+ " and [npc.she] [npc.moanVerb] as [npc.she] [npc.verb(feel)] it running down over [npc.her] [npc.breasts+].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" [npc.NamePos] [npc.milk] starts drooling out in a little stream over [npc2.namePos] fingers as [npc2.she] squeezes down on [npc.her] [npc.nipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" [npc.NamePos] [npc.milk] starts pouring out in a constant stream,"
								+ " quickly soaking [npc.her] breasts and dripping down onto the floor beneath [npc.herHim].");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" [npc.NamePos] [npc.milk] starts pouring out in a heavy flow,"
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
			if(!Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.NIPPLES)
					&& Main.sex.getCharacterPerformingAction().getBreastStoredMilk().getMinimumValue()>=Lactation.ONE_TRICKLE.getMinimumValue()
					&& Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.NIPPLES)!=null) {
				Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.NIPPLES).setDirty(Main.sex.getCharacterPerformingAction(), true);
			}
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterPerformingAction().incrementBreastStoredMilk(-10);
		}
		
	};
}
