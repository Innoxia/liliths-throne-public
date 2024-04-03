package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

public class FingerNippleCrotch {
	
	public static final SexAction PINCH_NIPPLES = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Pinch udder-nipples";
			} else {
				return "Pinch crotch-nipples";
			}
		}

		@Override
		public String getActionDescription() {
			return "Pinch [npc2.namePos] [npc2.crotchNipples+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!= SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(start)] to gently pinch and rub at [npc2.her] [npc2.crotchNipples+].",

							"[npc2.NamePos] [npc2.crotchBoobs+], fully on display, prove to be too tempting a target for [npc.name] to ignore,"
									+ " and with a soft little [npc.moan], [npc.she] [npc.verb(start)] gently tugging and pinching [npc2.her] [npc2.crotchNipples+].",

							"Teasing [npc.her] [npc.fingers] over [npc2.namePos] [npc2.crotchBoobRows] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] to gently tug and pinch at [npc2.her] [npc2.crotchNipples+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to eagerly pinch and rub at [npc2.her] [npc2.crotchNipples+].",

							"[npc2.NamePos] [npc2.crotchBoobs+], fully on display, prove to be too tempting a target for [npc.name] to ignore,"
									+ " and with [npc.a_moan+], [npc.she] [npc.verb(start)] eagerly tugging and pinching [npc2.her] [npc2.crotchNipples+].",

							"Teasing [npc.her] [npc.fingers] over [npc2.namePos] [npc2.crotchBoobRows] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] eagerly tugging and pinching at [npc2.her] [npc2.crotchNipples+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to [npc2.namePos] [npc2.crotchBoobs+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] roughly groping [npc2.namePos] chest, before moving up to forcefully pinch and squeeze [npc2.her] [npc2.crotchNipples+].",

							"[npc2.NamePos] [npc2.crotchBoobs+], fully on display, prove to be too tempting a target for [npc.name] to ignore,"
									+ " and with [npc.a_moan+], [npc.she] [npc.verb(start)] roughly pinching and squeezing [npc2.her] [npc2.crotchNipples+].",

							"Sinking [npc.her] [npc.fingers] into [npc2.namePos] [npc2.crotchBoobRows] [npc2.crotchBoobs+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] before starting to roughly pinch and squeeze [npc2.her] [npc2.crotchNipples+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to eagerly pinch and rub at [npc2.her] [npc2.crotchNipples+].",

							"[npc2.NamePos] [npc2.crotchBoobs+], fully on display, prove to be too tempting a target for [npc.name] to ignore,"
									+ " and with [npc.a_moan+], [npc.she] [npc.verb(start)] eagerly tugging and pinching [npc2.her] [npc2.crotchNipples+].",

							"Teasing [npc.her] [npc.fingers] over [npc2.namePos] [npc2.crotchBoobRows] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] eagerly tugging and pinching at [npc2.her] [npc2.crotchNipples+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to pinch and rub at [npc2.her] [npc2.crotchNipples+].",

							"[npc2.NamePos] [npc2.crotchBoobs+], fully on display, prove to be too tempting a target for [npc.name] to ignore,"
									+ " and with [npc.a_moan+], [npc.she] [npc.verb(start)] tugging and pinching [npc2.her] [npc2.crotchNipples+].",

							"Teasing [npc.her] [npc.fingers] over [npc2.namePos] [npc2.crotchBoobRows] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] tugging and pinching at [npc2.her] [npc2.crotchNipples+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] at [npc.namePos] touch, before gently encouraging [npc.herHim] to continue giving [npc2.her] [npc2.crotchNipples+] [npc.her] full attention.",
	
								" With a soft [npc2.moan], [npc2.name] slowly [npc2.verb(push)] [npc2.her] chest out, imploring [npc.name] to continue as [npc2.she] [npc2.verb(carry)] on making lewd little noises.",
	
								" Softly [npc2.moaning] at [npc.her] touch, [npc2.name] gently [npc2.verb(encourage)] [npc.name] to carry on stimulating [npc2.her] [npc2.crotchNipples+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] at [npc.namePos] touch, before eagerly encouraging [npc.herHim] to continue giving [npc2.her] [npc2.crotchNipples+] [npc.her] full attention.",
	
								" With [npc2.a_moan+], [npc2.name] eagerly [npc2.verb(push)] [npc2.her] chest out, imploring [npc.name] to continue as [npc2.she] [npc2.verb(carry)] on making lewd noises.",
	
								" [npc2.Moaning+] at [npc.her] touch, [npc2.name] eagerly [npc2.verb(encourage)] [npc.name] to carry on stimulating [npc2.her] [npc2.crotchNipples+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] at [npc.namePos] touch, before roughly growling for [npc.herHim] to continue giving [npc2.her] [npc2.crotchNipples+] [npc.her] full attention.",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(push)] [npc2.her] chest out, and in a firm tone,"
										+ " [npc2.she] [npc2.verb(order)] [npc.name] to continue giving [npc2.her] [npc2.crotchNipples+] [npc.her] full attention.",
	
								" Letting out [npc2.a_moan+] at [npc.her] touch, [npc2.name] [npc2.verb(demand)] that [npc.she] carries on stimulating [npc2.her] [npc2.crotchNipples+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] at [npc.namePos] touch, before eagerly encouraging [npc.herHim] to continue giving [npc2.her] [npc2.crotchNipples+] [npc.her] full attention.",
	
								" With [npc2.a_moan+], [npc2.name] eagerly [npc2.verb(push)] [npc2.her] chest out, imploring [npc.herHim] to continue as [npc2.she] [npc2.verb(carry)] on making lewd noises.",
	
								" [npc2.Moaning+] at [npc.her] touch, [npc2.name] eagerly [npc2.verb(encourage)] [npc.name] to carry on stimulating [npc2.her] [npc2.crotchNipples+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] at [npc.namePos] touch, before encouraging [npc.herHim] to continue giving [npc2.her] [npc2.crotchNipples+] [npc.her] full attention.",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(push)] [npc2.her] chest out, imploring [npc.herHim] to continue as [npc2.she] [npc2.verb(carry)] on making lewd noises.",
	
								" [npc2.Moaning+] at [npc.her] touch, [npc2.name] [npc2.verb(encourage)] [npc.name] to carry on stimulating [npc2.her] [npc2.crotchNipples+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] instinctively [npc2.verb(try)] to pull back,"
										+ " [npc2.sobbing] and struggling against [npc.namePos] touch as [npc2.she] [npc2.verb(try)] to knock [npc.her] [npc.fingers] away from [npc2.her] [npc2.crotchNipples+].",
	
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(start)] writhing around in discomfort,"
										+ " pleading for [npc.name] to leave [npc2.herHim] alone as [npc.she] [npc.verb(continue)] stimulating [npc2.her] [npc2.crotchNipples+].",
	
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] in response to [npc.namePos] touch,"
										+ " and as [npc.name] carries on stimulating [npc2.her] [npc2.crotchNipples+], [npc2.name] [npc2.verb(continue)] to struggle against [npc.herHim]."));
						break;
					default:
						break;
				}
			}
			
			switch (Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchStoredMilk()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(" As [npc.name] [npc.verb(start)] pinching [npc2.namePos] [npc2.crotchNipples], a small trickle of [npc2.milk] leaks out to run down [npc2.her] [npc2.crotchBoobs+].");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(" As [npc.name] [npc.verb(start)] pinching [npc2.namePos] [npc2.crotchNipples], a small squirt of [npc2.milk] leaks out to run down [npc2.her] [npc2.crotchBoobs+].");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(" As [npc.name] [npc.verb(start)] pinching [npc2.namePos] [npc2.crotchNipples], a trickle of [npc2.milk] runs down over [npc2.her] [npc2.crotchBoobs+].");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)] to flow out over [npc.namePos] fingertips as [npc.she] greedily [npc.verb(milk)] [npc2.her] [npc2.crotchBoobs+].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)] drooling out over [npc.namePos] fingertips as [npc.she] greedily [npc.verb(milk)] [npc2.her] [npc2.crotchBoobs+].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)] pouring out over [npc.namePos] fingertips as [npc.she] greedily [npc.verb(milk)] [npc2.her] [npc2.crotchBoobs+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)] pouring out over [npc.namePos] fingertips as [npc.she] greedily [npc.verb(milk)] [npc2.her] [npc2.crotchBoobs+].");
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterTargetedForSexAction(this).incrementBreastCrotchStoredMilk(-10);
		}
		
	};
	
	
	public static final SexAction MILK_TARGET = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Milk udders";
			} else {
				return "Milk crotch-boobs";
			}
		}

		@Override
		public String getActionDescription() {
			return "Pinch and tug at [npc2.namePos] [npc2.crotchNipples+] in order to coax [npc2.her] [npc2.milk+] out.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!= SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(trace)] [npc.her] [npc.fingers+] up and over [npc2.namePos] [npc2.crotchBoobs+], before homing in on [npc2.her] [npc2.crotchNipples+]."
								+ " Gently tugging and squeezing at [npc2.namePos] [npc2.crotchNipples+], [npc.name] [npc.verb(let)] out a soft cry of delight as [npc.she] [npc.verb(manage)] to draw out",

							"With a soft [npc.moan], [npc.name] gently [npc.verb(press)] [npc.her] [npc.fingers] into [npc2.namePos] [npc2.crotchBoobs+], before moving down to tease [npc2.her] [npc2.crotchNipples+]."
								+ " Delicately pinching and squeezing at [npc2.namePos] [npc2.crotchNipples+], [npc.name] [npc.verb(let)] out a delighted cry as [npc.she] [npc.verb(manage)] to draw out"));
					break;
				case SUB_NORMAL: case DOM_NORMAL: case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] eagerly [npc.verb(trace)] [npc.her] [npc.fingers+] up and over [npc2.namePos] [npc2.crotchBoobs+], before quickly homing in on [npc2.her] [npc2.crotchNipples+]."
								+ " Greedily tugging and squeezing at [npc2.namePos] [npc2.crotchNipples+], [npc.name] [npc.verb(let)] out a delighted cry as [npc.she] [npc.verb(manage)] to draw out",

							"With [npc.a_moan+], [npc.name] eagerly [npc.verb(press)] [npc.her] [npc.fingers] into [npc2.namePos] [npc2.crotchBoobs+], before quickly moving down to tease [npc2.her] [npc2.crotchNipples+]."
									+ " Greedily pinching and squeezing at [npc2.namePos] [npc2.crotchNipples+], [npc.name] [npc.verb(let)] out a delighted cry as [npc.she] [npc.verb(manage)] to draw out"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] digs [npc.her] [npc.fingers+] into [npc2.namePos] [npc2.crotchBoobs+], before moving [npc.her] rough touch up to [npc2.her] [npc2.crotchNipples+]."
								+ " Forcefully pinching and squeezing [npc2.namePos] [npc2.crotchNipples+], [npc.name] [npc.verb(let)] out a triumphant growl as [npc.she] [npc.verb(manage)] to draw out",

							"With [npc.a_moan+], [npc.name] roughly digs [npc.her] [npc.fingers] into [npc2.namePos] [npc2.crotchBoobs+], before quickly moving down to roughly flick [npc2.her] [npc2.crotchNipples+]."
									+ " Dominantly pinching and squeezing at [npc2.namePos] [npc2.crotchNipples+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(manage)] to draw out"));
					break;
				case SUB_RESISTING:
					break;
			}
			
			switch (Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchStoredMilk()) {
				case ZERO_NONE://Shouldn't be able to be reached
					UtilText.nodeContentSB.append(" no [npc2.milk] from [npc2.namePos] [npc2.crotchBoobs+].");
					break;
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" a small trickle of [npc2.milk] from [npc2.her] [npc2.crotchBoobs+].",

							" a little trickle of [npc2.milk] from [npc2.her] [npc2.crotchBoobs+]."));
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" a small squirt of [npc2.milk] from [npc2.her] [npc2.crotchBoobs+].",

							" a little stream of [npc2.milk] from [npc2.her] [npc2.crotchBoobs+]."));
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" a squirt of [npc2.milk] from [npc2.her] [npc2.crotchBoobs+].",

							" a trickle of [npc2.milk] from [npc2.her] [npc2.crotchBoobs+]."));
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" a flow of [npc2.milk] from [npc2.her] [npc2.crotchBoobs+].",

							" a stream of [npc2.milk] from [npc2.her] [npc2.crotchBoobs+]."));
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" a heavy flow of [npc2.milk] from [npc2.her] [npc2.crotchBoobs+].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" a considerable amount of [npc2.milk] from [npc2.her] [npc2.crotchBoobs+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" a huge amount of [npc2.milk] from [npc2.her] [npc2.crotchBoobs+].");
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc.name] [npc.verb(start)] to milk [npc2.herHim],"
										+ " and, pushing [npc2.her] [npc2.crotchBoobs] out into [npc.her] [npc.hands], [npc2.name] gently [npc2.verb(encourage)] [npc.name] to continue.",
	
								" [npc2.Name] can't help but [npc2.moanVerb] at the delightful feeling of having [npc2.her] [npc2.crotchBoobs] milked,"
										+ " and, biting [npc2.her] [npc2.lip], [npc2.she] gently [npc2.verb(plead)] with [npc.name] to continue."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a delighted [npc2.moan] as [npc.name] [npc.verb(start)] to milk [npc2.herHim],"
										+ " and, frantically pushing [npc2.her] [npc2.crotchBoobs] out into [npc.her] [npc.hands], [npc2.name] happily [npc2.verb(encourage)] [npc.name] to continue.",
	
								" [npc2.Name] can't help but [npc2.moanVerb] at the delightful feeling of having [npc2.her] [npc2.crotchBoobs] milked,"
										+ " and, biting [npc2.her] [npc2.lip], [npc2.she] eagerly [npc2.verb(plead)] with [npc.name] to continue."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a deeply satisfied growl as [npc.name] [npc.verb(start)] to milk [npc2.herHim],"
										+ " and, pushing [npc2.her] [npc2.crotchBoobs] out into [npc.her] [npc.hands], [npc2.name] roughly [npc2.verb(order)] [npc.name] to continue.",
	
								" [npc2.Name] can't help but [npc2.moanVerb] at the delightful feeling of having [npc2.her] [npc2.crotchBoobs] milked,"
										+ " and, biting [npc2.her] [npc2.lip], [npc2.she] roughly [npc2.verb(order)] [npc.name] to continue."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] to milk [npc2.herHim],"
										+ " and, trying to pull [npc2.her] [npc2.crotchBoobs] away from [npc.her] [npc.hands], [npc2.name] desperately [npc2.verb(plead)] with [npc.name] to stop.",
	
								" Desperately trying to pull [npc2.her] [npc2.crotchBoobs+] away from the unwanted milking, [npc2.name] [npc2.verb(plead)] with [npc.name] to leave [npc2.herHim] alone."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterTargetedForSexAction(this).incrementBreastCrotchStoredMilk(-Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawMilkStorageValue()/5);
		}
		
	};
	
	public static final SexAction GET_MILKED = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Get udders milked";
			} else {
				return "Get crotch-boobs milked";
			}
		}

		@Override
		public String getActionDescription() {
			return "Guide [npc2.namePos] [npc2.hands] up to your [npc.crotchBoobs+] and get [npc2.herHim] to milk you.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getBreastRawStoredMilkValue()>0
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!= SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(encourage)] [npc2.herHim] to trace [npc2.her] [npc2.fingers+] up and over [npc.her] [npc.crotchBoobs+],"
									+ " before gently guiding [npc2.herHim] down to [npc.her] [npc.crotchNipples+]."
									+ " Instructing [npc2.herHim] to tug and squeeze at [npc.her] [npc.crotchNipples+], [npc.name] [npc.verb(let)] out a soft cry as [npc2.namePos] [npc2.verb(manage)] to draw out",

							"With a soft [npc.moan], [npc.name] gently [npc.verb(guide)] [npc2.namePos] [npc2.fingers] into [npc.her] [npc.crotchBoobs+], before moving [npc2.her] touch down to tease [npc.her] [npc.crotchNipples+]."
									+ " Instructing [npc2.herHim] to pinch and squeeze at [npc.her] [npc.crotchNipples+], [npc.name] [npc.verb(let)] out a delighted cry as [npc2.namePos] [npc2.verb(manage)] to draw out"));
					break;
				case SUB_NORMAL: case DOM_NORMAL: case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc2.namePos] [npc2.hand], [npc.name] guide [npc2.her] [npc2.fingers+] up and over [npc.her] [npc.crotchBoobs+], before greedily guiding [npc2.herHim] down to [npc.her] [npc.crotchNipples+]."
									+ " Telling [npc2.herHim] to tug and squeeze at [npc.her] [npc.crotchNipples+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.namePos] [npc2.verb(manage)] to draw out",

							"With [npc.a_moan+], [npc.name] eagerly [npc.verb(guide)] [npc2.namePos] [npc2.fingers] into [npc.her] [npc.crotchBoobs+], before moving [npc2.her] touch down to tease [npc.her] [npc.crotchNipples+]."
									+ " Instructing [npc2.herHim] to pinch and squeeze at [npc.her] [npc.crotchNipples+], [npc.name] [npc.verb(let)] out a delighted cry as [npc2.namePos] [npc2.verb(manage)] to draw out"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing hold of [npc2.namePos] [npc2.hand], [npc.name] roughly [npc.verb(force)] [npc2.her] [npc2.fingers+] up and over [npc.her] [npc.crotchBoobs+],"
									+ " before dominantly guiding [npc2.her] touch down to [npc.her] [npc.crotchNipples+]."
									+ " Ordering [npc2.herHim] to tug and squeeze at [npc.her] [npc.crotchNipples+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.namePos] [npc2.verb(manage)] to draw out",

							"With [npc.a_moan+], [npc.name] forcefully [npc.verb(guide)] [npc2.namePos] [npc2.fingers] into [npc.her] [npc.crotchBoobs+], before moving [npc2.her] touch down to tease [npc.her] [npc.crotchNipples+]."
									+ " Ordering [npc2.herHim] to pinch and squeeze at [npc.her] [npc.crotchNipples+], [npc.name] [npc.verb(let)] out a delighted cry as [npc2.namePos] [npc2.verb(manage)] to draw out"));
					break;
				case SUB_RESISTING:
					break;
			}
			
			switch (Main.sex.getCharacterPerformingAction().getBreastCrotchStoredMilk()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append(" no [npc.milk] from [npc.namePos] [npc.crotchBoobs+]."); //Shouldn't be able to be reached
					break;
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" a small trickle of [npc.milk] from [npc.her] [npc.crotchBoobs+].",

							" a little trickle of [npc.milk] from [npc.her] [npc.crotchBoobs+]."));
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" a small squirt of [npc.milk] from [npc.her] [npc.crotchBoobs+].",

							" a little stream of [npc.milk] from [npc.her] [npc.crotchBoobs+]."));
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" a squirt of [npc.milk] from [npc.her] [npc.crotchBoobs+].",

							" a trickle of [npc.milk] from [npc.her] [npc.crotchBoobs+]."));
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" a flow of [npc.milk] from [npc.her] [npc.crotchBoobs+].",

							" a stream of [npc.milk] from [npc.her] [npc.crotchBoobs+]."));
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" a heavy flow of [npc.milk] from [npc.her] [npc.crotchBoobs+].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" a considerable amount of [npc.milk] from [npc.her] [npc.crotchBoobs+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" a huge amount of [npc.milk] from [npc.her] [npc.crotchBoobs+].");
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.she] [npc2.verb(start)] to milk [npc.name],"
										+ " and as [npc.name] [npc.verb(push)] [npc.her] [npc.crotchBoobs] out into [npc2.her] [npc2.hands], [npc2.name] [npc2.verb(continue)] gently drawing the [npc.milk+] from [npc.her] [npc.crotchNipples].",
	
								" [npc2.Name] can't help but let out [npc2.a_moan+] at the delightful feeling of milking [npc.namePos] [npc.crotchBoobs],"
										+ " and, encouraged by the similar sounds that [npc.sheIs] making, [npc2.she] [npc2.verb(continue)] gently teasing the [npc.milk+] from [npc.namePos] [npc.crotchNipples]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(start)] to milk [npc.name],"
										+ " and as [npc.name] [npc.verb(push)] [npc.her] [npc.crotchBoobs] out into [npc2.her] [npc2.hands], [npc2.name] [npc2.verb(continue)] eagerly drawing the [npc.milk+] from [npc.her] [npc.crotchNipples].",
	
								" [npc2.Name] can't help but let out [npc2.a_moan+] at the delightful feeling of milking [npc.namePos] [npc.crotchBoobs],"
										+ " and, encouraged by the similar sounds that [npc.sheIs] making, [npc2.she] [npc2.verb(continue)] greedily squeezing the [npc.milk+] from [npc.namePos] [npc.crotchNipples]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a growl as [npc2.she] [npc2.verb(start)] to milk [npc.name],"
										+ " and as [npc.name] [npc.verb(push)] [npc.her] [npc.crotchBoobs] out into [npc2.her] [npc2.hands], [npc2.name] [npc2.verb(continue)] roughly drawing the [npc.milk+] from [npc.her] [npc.crotchNipples].",
	
								" [npc2.Name] can't help but let out [npc2.a_moan+] at the feeling of milking [npc.namePos] [npc.crotchBoobs],"
										+ " and, encouraged by the similar sounds that [npc.sheIs] making, [npc2.she] [npc2.verb(continue)] roughly squeezing the [npc.milk+] from [npc.namePos] [npc.crotchNipples]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] and [npc2.verb(try)] to pull away, but, with a forceful yank, [npc.name] [npc.verb(pull)] [npc2.her] [npc2.hands] back into [npc.her] [npc.crotchBoobs],"
										+ " making it quite clear that [npc2.sheHasFull] no choice but to squeeze the [npc.milk+] from [npc.her] [npc.crotchNipples].",
	
								" [npc2.Name] desperately tries to pull away from [npc.namePos] [npc.crotchBoobs],"
										+ " but, grabbing hold of [npc2.her] [npc2.hand], [npc.name] [npc.verb(force)] [npc2.herHim] to continue squeezing the [npc.milk+] from [npc.her] [npc.crotchNipples]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterPerformingAction().incrementBreastCrotchStoredMilk(-Main.sex.getCharacterPerformingAction().getBreastCrotchRawMilkStorageValue()/5);
		}
	};
	
	
	
	public static final SexAction NIPPLE_FINGERING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Finger udder-nipples";
			} else {
				return "Finger crotch-nipples";
			}
		}

		@Override
		public String getActionDescription() {
			return "Sink your [npc.fingers] into one of [npc2.namePos] fuckable [npc2.crotchNipples] and [npc2.verb(start)] fingering [npc2.her] breasts.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing [npc.her] [npc.fingers] over [npc2.namePos] [npc2.crotchBoobs+],"
									+ " [npc2.name] [npc2.verb(let)] out a gasp as [npc.name] circles around one of [npc2.her] [npc2.crotchNipples+], before slowly pushing [npc.her] digits into [npc2.her] inviting orifice.",

							"[npc.Name] [npc.verb(press)] [npc.her] [npc.fingers] against one of [npc2.namePos] [npc2.crotchNipples+],"
									+ " and with a slow, steady pressure, [npc.she] gently [npc.verb(sink)] [npc.her] digits into the flesh of [npc2.namePos] breast."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing [npc.her] [npc.fingers] over [npc2.namePos] [npc2.crotchBoobs+],"
									+ " [npc2.name] [npc2.verb(let)] out a gasp as [npc.name] circles around one of [npc2.her] [npc2.crotchNipples+], before eagerly pushing [npc.her] digits into [npc2.her] inviting orifice.",

							"[npc.Name] [npc.verb(press)] [npc.her] [npc.fingers] against one of [npc2.namePos] [npc2.crotchNipples+],"
									+ " and with a steady pressure, [npc.she] greedily [npc.verb(sink)] [npc.her] digits into the flesh of [npc2.namePos] breast."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Groping and squeezing [npc2.namePos] [npc2.crotchBoobs+],"
									+ " [npc.name] [npc.verb(start)] to circle [npc.her] [npc.fingers] around one of [npc2.her] [npc2.crotchNipples+], before roughly forcing [npc.her] digits into [npc2.namePos] inviting orifice.",

							"Greedily pressing [npc.her] [npc.fingers] against one of [npc2.namePos] [npc2.crotchNipples+],"
									+ " [npc.name] [npc.verb(let)] out a little growl as [npc.she] roughly [npc.verb(sink)] [npc.her] digits into the flesh of [npc2.namePos] breast."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing [npc.her] [npc.fingers] over [npc2.namePos] [npc2.crotchBoobs+],"
									+ " [npc2.name] [npc2.verb(let)] out a gasp as [npc.name] eagerly circles around one of [npc2.her] [npc2.crotchNipples+], before desperately pushing [npc.her] digits into [npc2.her] inviting orifice.",

							"[npc.Name] [npc.verb(press)] [npc.her] [npc.fingers] against one of [npc2.namePos] [npc2.crotchNipples+],"
									+ " and with a steady pressure, [npc.she] eagerly [npc.verb(sink)] [npc.her] digits into the flesh of [npc2.namePos] breast."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing [npc.her] [npc.fingers] over [npc2.namePos] [npc2.crotchBoobs+],"
									+ " [npc2.name] [npc2.verb(let)] out a gasp as [npc.name] circles around one of [npc2.her] [npc2.crotchNipples+], before eagerly pushing [npc.her] digits into [npc2.her] inviting orifice.",

							"[npc.Name] [npc.verb(press)] [npc.her] [npc.fingers] against one of [npc2.namePos] [npc2.crotchNipples+],"
									+ " and with a steady pressure, [npc.she] greedily [npc.verb(sink)] [npc.her] digits into the flesh of [npc2.namePos] breast."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc.name] [npc.verb(start)] fingering [npc2.namePos] [npc2.crotchBoobs],"
										+ " gently pushing [npc2.her] chest out as [npc2.she] [npc2.verb(help)] [npc.name] sink [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.crotchNipple+].",
	
								" With a soft [npc2.moan], [npc2.name] slowly [npc2.verb(push)] [npc2.her] chest out,"
										+ " imploring [npc.name] to sink [npc.her] [npc.fingers+] even deeper into [npc2.her] breast."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] fingering [npc2.namePos] [npc2.crotchBoob],"
										+ " eagerly pushing [npc2.her] chest out as [npc2.she] [npc2.verb(help)] [npc.name] sink [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.crotchNipple+].",
	
								" With [npc.a_moan+], [npc2.name] eagerly [npc2.verb(push)] [npc2.her] chest out,"
										+ " imploring [npc.name] to sink [npc.her] [npc.fingers] even deeper into [npc2.her] breast."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] fingering [npc2.namePos] [npc2.crotchBoobs],"
										+ " violently thrusting [npc2.her] chest out against [npc.her] touch as [npc2.she] [npc2.verb(command)] [npc.name] to sink [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.crotchNipple+].",
	
								" With [npc2.a_moan+], [npc2.name] respond by violently thrusting [npc2.her] chest out,"
										+ " commanding [npc.name] to sink [npc.her] [npc.fingers] even deeper into [npc2.her] breast."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] fingering [npc2.namePos] [npc2.crotchBoobs],"
										+ " eagerly pushing [npc2.her] chest out as [npc2.name] [npc2.verb(help)] [npc.name] sink [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.crotchNipple+].",
	
								" With [npc.a_moan+], [npc2.name] eagerly [npc2.verb(push)] [npc2.her] chest out,"
										+ " imploring [npc.name] to sink [npc.her] [npc.fingers] even deeper into [npc2.her] breast."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] fingering [npc2.namePos] [npc2.crotchBoob],"
										+ " eagerly pushing [npc2.her] chest out as [npc2.name] [npc2.verb(help)] [npc.name] sink [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.crotchNipple+].",
	
								" With [npc.a_moan+], [npc2.name] eagerly [npc2.verb(push)] [npc2.her] chest out,"
										+ " imploring [npc.name] to sink [npc.her] [npc.fingers] even deeper into [npc2.her] breast."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(try)] to pull back,"
										+ " [npc2.sobbing] and writhing in discomfort as [npc.name] [npc.verb(start)] to pump [npc.her] [npc.fingers] in and out of [npc2.her] [npc2.crotchNipple+].",
	
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)], in vain, to pull away from the unwanted penetration,"
										+ " protesting and struggling against [npc.namePos] touch as [npc.she] [npc.verb(pump)] [npc.her] [npc.fingers+] in and out of [npc2.her] [npc2.crotchNipple+]."));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(push)] [npc2.her] chest out in response,"
									+ " letting out a delighted [npc2.moan] as [npc2.she] [npc2.verb(start)] enthusiastically imploring [npc.name] to continue fingering [npc2.her] [npc2.crotchBoobs].",
		
							" A delighted [npc2.moan] bursts out from between [npc2.namePos] [npc2.lips],"
									+ " and [npc2.she] [npc2.verb(start)] eagerly thrusting [npc2.her] chest out against [npc.namePos] touch as [npc2.she] [npc2.verb(beg)] [npc.herHim] to continue fingering [npc2.her] [npc2.crotchNipples+].",
		
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(push)] out [npc2.her] chest,"
									+ " eagerly imploring [npc.name] to continue fingering [npc2.her] [npc2.crotchBoobs] as [npc2.her] movements cause [npc.name] to sink [npc.her] [npc.fingers] deep into [npc2.her] [npc2.crotchNipple+]."));
				case SUB_RESISTING:
					return (UtilText.returnStringAtRandom(
							" Failing to recoil [npc2.her] chest away from [npc.namePos] touch,"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away from [npc2.herHim].",
		
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips],"
									+ " before [npc2.she] [npc2.verb(start)] weakly trying to push [npc.name] away, squirming and protesting as [npc.name] [npc.verb(continue)] to gently finger [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain,"
									+ " to recoil [npc2.her] chest away from [npc.namePos] touch, struggling against [npc.herHim] as [npc.her] [npc.fingers] [npc.verb(continue)] gently sliding deep into [npc2.her] [npc2.crotchNipple+]."));
				case DOM_GENTLE:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(push)] [npc2.her] chest out in response,"
									+ " letting out a delighted [npc2.moan] before starting to enthusiastically implore [npc.name] to continue fingering [npc2.her] [npc2.crotchBoobs].",
	
							" A delighted [npc2.moan] bursts out from between [npc2.namePos] [npc2.lips],"
									+ " and [npc2.she] [npc2.verb(start)] gently pushing [npc2.her] chest out against [npc.namePos] touch as [npc2.she] [npc2.verb(beg)] [npc.herHim] to continue fingering [npc2.her] [npc2.crotchNipples+].",
	
							" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(push)] out [npc2.her] chest,"
									+ " imploring [npc.name] to continue fingering [npc2.her] [npc2.crotchBoobs] as [npc2.her] movements cause [npc.name] to sink [npc.her] [npc.fingers] deep into [npc2.her] [npc2.crotchNipple+]."));
				case DOM_ROUGH:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(push)] [npc2.her] chest out in response, letting out a delighted [npc2.moan] as [npc2.she] [npc2.verb(start)] commanding [npc.name] to continue fingering [npc2.her] [npc2.crotchBoobs].",
	
							" A delighted [npc2.moan] bursts out from between [npc2.namePos] [npc2.lips],"
									+ " and [npc2.she] [npc2.verb(start)] roughly thrusting [npc2.her] chest out against [npc.namePos] touch as [npc2.she] [npc2.verb(order)] [npc.name] to continue fingering [npc2.her] [npc2.crotchNipples+].",
	
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(thrust)] out [npc2.her] chest,"
									+ " commanding [npc.name] to continue fingering [npc2.her] [npc2.crotchBoobs] as [npc2.her] sudden movement causes [npc.name] to sink [npc.her] [npc.fingers] deep into [npc2.her] [npc2.crotchNipple+]."));
				case SUB_NORMAL:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(push)] [npc2.her] chest out in response,"
									+ " letting out [npc2.a_moan] as [npc2.she] [npc2.verb(implore)] [npc.name] to continue fingering [npc2.her] [npc2.crotchBoobs].",
		
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips],"
									+ " and [npc2.she] [npc2.verb(push)] [npc2.her] chest out against [npc.namePos] touch as [npc2.she] [npc2.verb(implore)] [npc.herHim] to continue fingering [npc2.her] [npc2.crotchNipples+].",
		
							" [npc2.Moaning+], [npc2.name] [npc2.verb(push)] out [npc2.her] chest,"
									+ " imploring [npc.name] to continue fingering [npc2.her] [npc2.crotchBoobs] as [npc2.her] movements cause [npc.name] to sink [npc.her] [npc.fingers+] deep into [npc2.her] [npc2.crotchNipple+]."));
			}
		}
		return "";
	}
	
	public static final SexAction NIPPLE_FINGERING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Gentle udder-nipple fingering";
			} else {
				return "Gentle crotch-nipple fingering";
			}
		}

		@Override
		public String getActionDescription() {
			return "Gently finger [npc2.namePos] [npc2.crotchNipple].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sinking [npc.her] [npc.fingers+] deep into [npc2.namePos] [npc2.crotchNipple+], [npc.name] slowly [npc.verb(start)] to slide [npc.her] digits in and out of [npc2.her] [npc2.crotchBoob].",

					"[npc.Name] gently [npc.verb(lean)] in against [npc2.name], causing [npc2.herHim] to inhale [npc.her] [npc.scent+] as [npc.she] gently [npc.verb(pump)] [npc.her] [npc.fingers+] in and out of [npc2.her] [npc2.crotchNipple+].",

					"Gently pressing [npc.herself] against [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] softly [npc.verb(pump)] [npc.her] [npc.fingers+] in and out of [npc2.namePos] [npc2.crotchNipple+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Finger udder-nipples";
			} else {
				return "Finger crotch-nipples";
			}
		}

		@Override
		public String getActionDescription() {
			return "Continue fingering [npc2.namePos] [npc2.crotchNipple+].";
		}
		
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking [npc.her] [npc.fingers+] deep into [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(start)] eagerly fingering [npc2.her] [npc2.crotchBoobs],"
							+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(press)] [npc.herself] up against [npc2.herHim].",

					"[npc.Name] [npc.verb(lean)] in against [npc2.herHim],"
							+ " [npc.her] [npc.scent+] overwhelming [npc2.namePos] senses as [npc.she] [npc.verb(start)] eagerly pumping [npc.her] [npc.fingers+] in and out of [npc2.namePos] [npc2.crotchNipple+].",

					"Pressing [npc.herself] against [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] eagerly pumping [npc.her] [npc.fingers+] in and out of [npc2.namePos] [npc2.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Rough udder-nipple fingering";
			} else {
				return "Rough crotch-nipple fingering";
			}
		}

		@Override
		public String getActionDescription() {
			return "Roughly finger [npc2.namePos] [npc2.crotchNipple].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Greedily plunging [npc.her] [npc.fingers+] deep into [npc2.namePos] [npc2.crotchNipple+],"
							+ " [npc.name] [npc.verb(start)] roughly slamming [npc.her] digits in and out, rapidly fingering [npc2.namePos] [npc2.crotchBoob] as [npc.she] grinds [npc.herself] up against [npc2.herHim].",

					"[npc.Name] grinds [npc.herself] against [npc2.name],"
							+ " forcing [npc2.herHim] to inhale [npc.her] [npc.scent+] as [npc.she] [npc.verb(start)] roughly slamming [npc.her] [npc.fingers+] in and out of [npc2.namePos] [npc2.crotchNipple+].",

					"Grinding [npc.herself] up against [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] roughly slamming [npc.her] [npc.fingers+] in and out of [npc2.namePos] [npc2.crotchNipple+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Finger udder-nipples";
			} else {
				return "Finger crotch-nipples";
			}
		}

		@Override
		public String getActionDescription() {
			return "Continue fingering [npc2.namePos] [npc2.crotchNipple].";
		}
		
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking [npc.her] [npc.fingers+] deep into [npc2.namePos] [npc2.crotchNipple+],"
							+ " [npc.name] [npc.verb(start)] sliding [npc.her] digits in and out, fingering [npc2.namePos] [npc2.crotchBoob] as [npc.she] [npc.verb(press)] [npc.herself] up against [npc2.herHim].",

					"[npc.Name] [npc.verb(lean)] in against [npc2.name], causing [npc2.herHim] to inhale [npc.her] [npc.scent+] as [npc.she] [npc.verb(start)] pumping [npc.her] [npc.fingers+] in and out of [npc2.her] [npc2.crotchNipple+].",

					"Pressing [npc.herself] against [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] pumping [npc.her] [npc.fingers+] in and out of [npc2.namePos] [npc2.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_NIPPLE_FINGERING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Eager udder-nipple fingering";
			} else {
				return "Eager crotch-nipple fingering";
			}
		}

		@Override
		public String getActionDescription() {
			return "Eagerly finger [npc2.namePos] [npc2.crotchNipple].";
		}
		
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking [npc.her] [npc.fingers+] deep into [npc2.namePos] [npc2.crotchNipple+],"
							+ " [npc.name] [npc.verb(start)] sliding [npc.her] digits in and out, eagerly fingering [npc2.her] [npc2.crotchBoob] as [npc.she] [npc.verb(press)] [npc.herself] up against [npc2.herHim].",

					"[npc.Name] [npc.verb(lean)] in against [npc2.name],"
							+ " causing [npc2.herHim] to breathe in [npc.her] [npc.scent+] as [npc.she] [npc.verb(start)] eagerly pumping [npc.her] [npc.fingers+] in and out of [npc2.her] [npc2.crotchNipple+].",

					"Pressing [npc.herself] against [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] eagerly pumping [npc.her] [npc.fingers+] in and out of [npc2.namePos] [npc2.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Resist udder-nipple fingering";
			} else {
				return "Resist crotch-nipple fingering";
			}
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.fingers] out of [npc2.namePos] [npc2.crotchNipple+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears starting to well up in [npc.namePos] [npc.eyes], and, unable to keep it in any longer,"
									+ " [npc.a_sob+] bursts out from [npc.her] mouth as [npc.she] weakly [npc.verb(try)] to pull [npc.her] [npc.fingers] out of [npc2.namePos] [npc2.crotchNipple+].",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to [npc.verb(pull)] [npc.her] [npc.fingers+] out of [npc2.namePos] [npc2.crotchNipple+].",

							"Trying desperately to pull [npc.her] [npc.fingers+] out of [npc2.namePos] [npc2.crotchNipple+],"
									+ " [npc.name] [npc.sob] in distress as [npc2.namePos] [npc2.verb(hold)] [npc.her] [npc.hand] in position,"
									+ " before gently thrusting [npc2.her] chest out and forcing [npc.namePos] [npc.fingers] deep into [npc2.her] [npc2.crotchBoob]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears starting to well up in [npc.namePos] [npc.eyes], and, unable to keep it in any longer,"
									+ " [npc.a_sob+] bursts out from [npc.her] mouth as [npc.she] weakly [npc.verb(try)] to pull [npc.her] [npc.fingers] out of [npc2.namePos] [npc2.crotchNipple+].",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to [npc.verb(pull)] [npc.her] [npc.fingers+] out of [npc2.namePos] [npc2.crotchNipple+].",

							"Trying desperately to pull [npc.her] [npc.fingers+] out of [npc2.namePos] [npc2.crotchNipple+],"
									+ " [npc.name] [npc.sob] in distress as [npc2.namePos] [npc2.verb(hold)] [npc.her] [npc.hand] in position,"
									+ " before eagerly thrusting [npc2.her] chest out and forcing [npc.namePos] [npc.fingers] deep into [npc2.her] [npc2.crotchBoob]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears starting to well up in [npc.namePos] [npc.eyes], and, unable to keep it in any longer,"
									+ " [npc.a_sob+] bursts out from [npc.her] mouth as [npc.she] weakly [npc.verb(try)] to pull [npc.her] [npc.fingers] out of [npc2.namePos] [npc2.crotchNipple+].",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to [npc.verb(pull)] [npc.her] [npc.fingers+] out of [npc2.namePos] [npc2.crotchNipple+].",

							"Trying desperately to pull [npc.her] [npc.fingers+] out of [npc2.namePos] [npc2.crotchNipple+],"
									+ " [npc.name] [npc.sob] in distress as [npc2.namePos] [npc2.verb(hold)] [npc.her] [npc.hand] in position,"
									+ " before roughly thrusting [npc2.her] chest out and forcing [npc.namePos] [npc.fingers] deep into [npc2.her] [npc2.crotchBoob]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction NIPPLE_FINGERING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Stop udder-nipple fingering";
			} else {
				return "Stop crotch-nipple fingering";
			}
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.fingers] out of [npc2.namePos] [npc2.crotchNipple] and stop fingering [npc2.her] [npc2.crotchBoob].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc.her] [npc.fingers] out of [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(give)] [npc2.namePos] [npc2.crotchBoob] one last rough squeeze as [npc.she] stops fingering [npc2.her] chest.",

							"[npc.Name] sharply [npc.verb(inhale)], breathing in [npc2.namePos] [npc2.scent+] before roughly yanking [npc.her] [npc.fingers] out of [npc2.her] [npc2.crotchNipple]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.fingers] out of [npc2.namePos] [npc2.crotchNipple], [npc.name] [npc.verb(give)] [npc2.namePos] [npc2.crotchBoob] one last squeeze as [npc.she] stops fingering [npc2.her] chest.",

							"[npc.Name] sharply [npc.verb(inhale)], breathing in [npc2.namePos] [npc2.scent+] before sliding [npc.her] [npc.fingers] out of [npc2.her] [npc2.crotchNipple]."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a relieved sigh, which soon turns into [npc2.a_sob+] as [npc2.she] [npc2.verb(continue)] to struggle against [npc.name].",

							" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to protest and struggle in discomfort as [npc.name] holds [npc2.herHim] in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] stops stimulating [npc2.her] [npc2.crotchBoobs+].",

							" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desire for more of [npc.namePos] attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction NIPPLE_FINGERED_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Get udder-nipples fingered";
			} else {
				return "Get crotch-nipples fingered";
			}
		}

		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to start fingering your [npc.crotchNipple+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking a gentle, but firm, grip on [npc2.namePos] [npc2.hand], [npc.name] slowly [npc.verb(guide)] [npc2.her] [npc2.fingers] up and over [npc.her] [npc.crotchBoobs],"
									+ " letting out a little [npc.moan] before pushing [npc2.her] digits into [npc.her] [npc.crotchNipple+].",
							
							"Taking hold of [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.her] [npc2.fingers] up to [npc.her] [npc.crotchBoobs], and with a slow, steady pressure,"
									+ " [npc.she] gently [npc.verb(push)] [npc2.her] digits into [npc.her] [npc.crotchNipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a firm grip on [npc2.her] [npc2.hand], [npc.name] eagerly [npc.verb(guide)] [npc2.namePos] [npc2.fingers] up and over [npc.her] [npc.crotchBoobs],"
									+ " letting out [npc.a_moan+] before greedily pushing [npc2.her] digits into [npc.her] [npc.crotchNipple+].",

							"Taking hold of [npc2.namePos] [npc2.hand], [npc.name] eagerly [npc.verb(guide)] [npc2.her] [npc2.fingers] up to [npc.her] [npc.crotchBoobs], and with a determined pressure,"
									+ " [npc.she] greedily [npc.verb(push)] [npc2.her] digits into [npc.her] [npc.crotchNipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a vice-like grip on [npc2.her] [npc2.hand], [npc.name] [npc.verb(grind)] [npc2.namePos] [npc2.fingers] up and over [npc.her] [npc.crotchBoobs],"
									+ " letting out [npc.a_moan+] before roughly forcing [npc2.her] digits into [npc.her] [npc.crotchNipple+].",

							"Grabbing [npc2.namePos] [npc2.hand], [npc.name] forcefully [npc.verb(push)] [npc2.namePos] [npc2.fingers] up to [npc.her] [npc.crotchBoobs], and with a dominant, jerking motion,"
									+ " [npc.she] roughly [npc.verb(stuff)] [npc2.her] digits into [npc.her] [npc.crotchNipple+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a firm grip on [npc2.her] [npc2.hand], [npc.name] eagerly [npc.verb(guide)] [npc2.namePos] [npc2.fingers] up and over [npc.her] [npc.crotchBoobs],"
									+ " letting out [npc.a_moan+] before greedily pushing [npc2.her] digits into [npc.her] [npc.crotchNipple+].",

							"Taking hold of [npc2.namePos] [npc2.hand], [npc.name] eagerly [npc.verb(guide)] [npc2.namePos] [npc2.fingers] up to [npc.her] [npc.crotchBoobs], and with a determined pressure,"
									+ " [npc.she] greedily [npc.verb(push)] [npc2.her] digits into [npc.her] [npc.crotchNipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a firm grip on [npc2.her] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.namePos] [npc2.fingers] up and over [npc.her] [npc.crotchBoobs],"
									+ " letting out [npc.a_moan+] before pushing [npc2.her] digits into [npc.her] [npc.crotchNipple+].",

							"Taking hold of [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.namePos] [npc2.fingers] up to [npc.her] [npc.crotchBoobs], and with a determined pressure,"
									+ " [npc.she] [npc.verb(push)] [npc2.her] digits into [npc.her] [npc.crotchNipple+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.she] [npc2.verb(enter)] [npc.herHim], curling [npc2.her] [npc2.fingers] up before gently starting to finger [npc.her] [npc.crotchNipple+].",
	
								" With a soft [npc2.moan], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] up inside of [npc.name],"
										+ " gently pushing [npc2.her] [npc2.hand] into the soft flesh of [npc.her] [npc.crotchBoob] as [npc2.she] [npc2.verb(set)] about fingering [npc.her] [npc.crotchNipple+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.herHim], curling [npc2.her] [npc2.fingers] up before eagerly starting to finger [npc.her] [npc.crotchNipple+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] up inside of [npc.name],"
										+ " eagerly pushing [npc2.her] [npc2.hand] into the soft flesh of [npc.her] [npc.crotchBoob] as [npc2.she] [npc2.verb(set)] about fingering [npc.her] [npc.crotchNipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.herHim], and, seeking to remind [npc.name] who's in charge,"
										+ " [npc2.she] roughly [npc2.verb(curl)] [npc2.her] [npc2.fingers] up before starting to ruthlessly finger-fuck [npc.her] [npc.crotchNipple+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] up inside of [npc.name],"
										+ " seeking to remind [npc.herHim] who's in charge as [npc2.she] [npc2.verb(start)] roughly fingering [npc.her] [npc.crotchNipple+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.herHim], curling [npc2.her] [npc2.fingers] up before eagerly starting to finger [npc.her] [npc.crotchNipple+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] up inside of [npc.name],"
										+ " eagerly pushing [npc2.her] [npc2.hand] into the soft flesh of [npc.her] [npc.crotchBoob] as [npc2.she] [npc2.verb(set)] about fingering [npc.her] [npc.crotchNipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.herHim], curling [npc2.her] [npc2.fingers] up before starting to finger [npc.her] [npc.crotchNipple+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] up inside of [npc.name],"
										+ " pushing [npc2.her] [npc2.hand] into the soft flesh of [npc.her] [npc.crotchBoob] as [npc2.she] [npc2.verb(set)] about fingering [npc.her] [npc.crotchNipple+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(force)] [npc2.her] [npc2.fingers] inside of [npc.herHim],"
										+ " struggling against [npc.namePos] firm grip on [npc2.her] [npc2.hand] as [npc2.she] tries to pull [npc2.herself] free.",
	
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(start)] struggling against [npc.namePos] tight grip on [npc2.her] [npc2.hand],"
										+ " pleading for [npc.name] to stop as [npc.she] [npc2.verb(force)] [npc2.her] [npc2.fingers] deep into [npc.her] [npc.crotchNipple+]."));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERED_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Gently udder-nipple-fingered";
			} else {
				return "Gently crotch-nipple-fingered";
			}
		}

		@Override
		public String getActionDescription() {
			return "Gently enjoy [npc2.namePos] [npc2.fingers+] in your [npc.crotchNipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently pushing [npc.her] [npc.crotchBoob] out against [npc2.namePos] [npc2.hand],"
							+ " [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.crotchNipple+].",

					"With a soft [npc.moan], [npc.name] gently [npc.verb(start)] thrusting [npc.her] [npc.crotchBoob] out against [npc2.namePos] [npc2.hand], forcing [npc2.her] [npc2.fingers+] ever deeper into [npc.her] [npc.crotchNipple+].",

					"Slowly thrusting [npc.her] [npc.crotchBoob] out against [npc2.namePos] [npc2.hand],"
							+ " a soft [npc.moan] drifts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.crotchNipple+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERED_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Enjoy udder-nipple-fingering";
			} else {
				return "Enjoy crotch-nipple-fingering";
			}
		}

		@Override
		public String getActionDescription() {
			return "Enjoy [npc2.namePos] [npc2.fingers+] in your [npc.crotchNipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc.her] [npc.crotchBoob] out against [npc2.namePos] [npc2.hand],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.crotchNipple+].",

					"With [npc.a_moan+], [npc.name] energetically [npc.verb(start)] thrusting [npc.her] [npc.crotchBoob] out against [npc2.namePos] [npc2.hand], forcing [npc2.her] [npc2.fingers+] ever deeper into [npc.her] [npc.crotchNipple+].",

					"Enthusiastically thrusting [npc.her] [npc.crotchBoob] out against [npc2.namePos] [npc2.hand],"
							+ " [npc.a_moan+] drifts out from between [npc.her] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.crotchNipple+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERED_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Roughly udder-nipple-fingered";
			} else {
				return "Roughly crotch-nipple-fingered";
			}
		}

		@Override
		public String getActionDescription() {
			return "Roughly force [npc2.namePos] [npc2.fingers+] deep inside your [npc.crotchNipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Violently slamming [npc.her] [npc.crotchBoob] out against [npc2.namePos] [npc2.hand],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] roughly [npc.verb(force)] [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.crotchNipple+].",

					"With [npc.a_moan+], [npc.name] [npc.verb(start)] aggressively thrusting [npc.her] [npc.crotchBoob] out against [npc2.namePos] [npc2.hand], forcing [npc2.her] [npc2.fingers+] ever deeper into [npc.her] [npc.crotchNipple+].",

					"Roughly thrusting [npc.her] [npc.crotchBoob] out against [npc2.namePos] [npc2.hand],"
							+ " [npc.a_moan+] bursts out from between [npc.her] [npc.lips+] as [npc.her] forceful movements drive [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.crotchNipple+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERED_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Enjoy udder-nipple-fingering";
			} else {
				return "Enjoy crotch-nipple-fingering";
			}
		}

		@Override
		public String getActionDescription() {
			return "Enjoy [npc2.namePos] [npc2.fingers+] in [npc.her] [npc.crotchNipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Pushing [npc.her] [npc.crotchBoob] out against [npc2.namePos] [npc2.hand],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.crotchNipple+].",

					"With [npc.a_moan+], [npc.name] [npc.verb(start)] thrusting [npc.her] [npc.crotchBoob] out against [npc2.namePos] [npc2.hand], forcing [npc2.her] [npc2.fingers+] ever deeper into [npc.her] [npc.crotchNipple+].",

					"Thrusting [npc.her] [npc.crotchBoob] out against [npc2.namePos] [npc2.hand],"
							+ " [npc.a_moan+] drifts out from between [npc.her] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.crotchNipple+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERED_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Eagerly udder-nipple-fingered";
			} else {
				return "Eagerly crotch-nipple-fingered";
			}
		}

		@Override
		public String getActionDescription() {
			return "Eagerly push your [npc.breast+(true)] against [npc2.namePos] [npc2.hand] as [npc2.she] fingers your [npc.crotchNipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc.her] [npc.crotchBoob] out against [npc2.namePos] [npc2.hand],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.crotchNipple+].",

					"With [npc.a_moan+], [npc.name] energetically [npc.verb(start)] thrusting [npc.her] [npc.crotchBoob] out against [npc2.namePos] [npc2.hand], forcing [npc2.her] [npc2.fingers+] ever deeper into [npc.her] [npc.crotchNipple+].",

					"Enthusiastically thrusting [npc.her] [npc.crotchBoob] out against [npc2.namePos] [npc2.hand],"
							+ " [npc.a_moan+] drifts out from between [npc.her] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.crotchNipple+]."));

			return UtilText.nodeContentSB.toString();
		}

	};

	public static final SexAction NIPPLE_FINGERED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Resist udder-nipple-fingering";
			} else {
				return "Resist crotch-nipple-fingering";
			}
		}

		@Override
		public String getActionDescription() {
			return "Try and pull [npc2.namePos] [npc2.fingers] out of your [npc.crotchNipple+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears starting to well up in [npc.namePos] [npc.eyes], and, unable to keep it in any longer,"
									+ " [npc.a_sob+] bursts out from [npc.her] mouth as [npc.she] weakly [npc.verb(try)] to pull [npc2.namePos] gently-pumping [npc2.fingers] out of [npc.her] [npc.crotchNipple+].",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to [npc.verb(pull)] [npc.her] [npc.crotchBoob] away from [npc2.namePos] unwanted touch,"
									+ " struggling in desperation as [npc2.namePos] [npc2.fingers+] continue gently sliding in and out of [npc.her] [npc.crotchNipple+].",

							"Trying desperately to pull [npc.her] [npc.crotchBoob] away from [npc2.namePos] [npc2.hand],"
									+ " [npc.name] [npc.sob] in distress as [npc2.namePos] [npc2.fingers+] continue gently sliding deep into [npc.her] [npc.crotchNipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears starting to well up in [npc.namePos] [npc.eyes], and, unable to keep it in any longer,"
									+ " [npc.a_sob+] bursts out from [npc.her] mouth as [npc.she] weakly [npc.verb(try)] to pull [npc2.namePos] greedily-thrusting [npc2.fingers] out of [npc.her] [npc.crotchNipple+].",


							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to [npc.verb(pull)] [npc.her] [npc.crotchBoob] away from [npc2.namePos] unwanted touch,"
									+ " struggling in desperation as [npc2.namePos] [npc2.fingers+] continue eagerly sliding in and out of [npc.her] [npc.crotchNipple+].",


							"Trying desperately to pull [npc.her] [npc.crotchBoob] away from [npc2.namePos] [npc2.hand],"
									+ " [npc.name] [npc.sob] in distress as [npc2.namePos] [npc2.fingers+] continue eagerly pumping deep into [npc.her] [npc.crotchNipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears starting to well up in [npc.namePos] [npc.eyes], and, unable to keep it in any longer,"
									+ " [npc.a_sob+] bursts out from [npc.her] mouth as [npc.she] weakly [npc.verb(try)] to pull [npc2.namePos] roughly-thrusting [npc2.fingers] out of [npc.her] [npc.crotchNipple+].",


							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to [npc.verb(pull)] [npc.her] [npc.crotchBoob] away from [npc2.namePos] unwanted touch,"
									+ " struggling in desperation as [npc2.namePos] [npc2.fingers+] continue roughly slamming in and out of [npc.her] [npc.crotchNipple+].",


							"Trying desperately to pull [npc.her] [npc.crotchBoob] away from [npc2.namePos] [npc2.hand],"
									+ " [npc.name] [npc.sob] in distress as [npc2.namePos] [npc2.fingers+] continue roughly slamming deep into [npc.her] [npc.crotchNipple+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Stop udder-nipple-fingering";
			} else {
				return "Stop crotch-nipple-fingering";
			}
		}

		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to pull [npc2.her] [npc2.fingers] out of your [npc.crotchNipple] and stop fingering your [npc.crotchBoobs].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc2.namePos] [npc2.fingers] out of [npc.her] [npc.crotchNipple+], [npc.name] [npc.verb(growl)] at [npc2.name] as [npc.she] [npc.verb(command)] [npc2.herHim] to stop fingering [npc.herHim].",
	
							"[npc.Name] [npc.verb(lean)] into [npc2.name], inhaling [npc2.her] [npc2.scent] before yanking [npc2.her] [npc2.fingers] out of [npc.her] [npc.crotchNipple+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc2.namePos] [npc2.fingers] out of [npc.her] [npc.crotchNipple+], [npc.name] [npc.verb(let)] out [npc.a_moan+] before telling [npc2.herHim] to stop fingering [npc.herHim].",
	
							"[npc.Name] lean into [npc2.name], inhaling [npc2.her] [npc2.scent] as [npc.name] [npc.verb(slide)] [npc2.her] [npc2.fingers] out of [npc.her] [npc.crotchNipple+]."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a relieved sigh, which soon turns into [npc2.a_sob+] as [npc2.she] [npc2.verb(realise)] that [npc.nameIsFull]n't finished with [npc2.herHim] yet.",
	
							" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to protest and struggle against [npc.name] as [npc.she] [npc.verb(hold)] [npc2.herHim] firmly in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(stop)] [npc2.herHim] from stimulating [npc.her] [npc.crotchNipple+].",
	
							" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desire to give [npc.her] [npc.crotchNipple+] more of [npc2.her] attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
