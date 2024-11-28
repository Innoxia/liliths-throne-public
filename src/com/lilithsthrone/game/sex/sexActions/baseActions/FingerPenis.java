package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
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
public class FingerPenis {
	
	private static boolean isCharacterPrecumming(GameCharacter character) {
		return character.getArousal() >= character.getPenisCumStorage().getArousalNeededToStartPreCumming();
	}
	
	//TODO grope cock
	
	public static final SexAction FONDLE_BALLS = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isInternalTesticles()
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		@Override
		public String getActionTitle() {
			return "Fondle balls";
		}
		@Override
		public String getActionDescription() {
			return "Fondle and play with [npc2.namePos] [npc2.balls+].";
		}
		@Override
		public String getDescription() {
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SIXTY_NINE)) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(prop)] [npc.herself] up on one [npc.arm], before using [npc.her] free [npc.hand] to stroke and squeeze [npc2.namePos] [npc2.balls+].",
						"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(prop)] [npc.herself] up on one [npc.arm], before reaching down and starting to stroke and play with [npc2.namePos] [npc2.balls+].",
						"Propping [npc.herself] up on one [npc.arm], [npc.name] [npc.verb(run)] [npc.her] [npc.fingers+] over [npc2.namePos] groin, grinning as [npc.she] [npc.verb(start)] to stroke and cup [npc2.namePos] [npc2.balls+].",
						"Propping [npc.herself] up on one [npc.arm], [npc.name] uses [npc.her] free [npc.hand] to cup and stroke [npc2.namePos] [npc2.balls+],"
								+ " [npc.moaning] happily to [npc.herself] as [npc2.namePos] [npc2.cock+] twitches in response.");
				
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(reach)] between [npc2.namePos] [npc2.legs] with one [npc.hand] and [npc.verb(start)] to stroke and squeeze [npc2.her] [npc2.balls+].",
						"[npc2.Name] [npc2.verb(find)] [npc2.herself] letting out [npc2.a_moan+] as [npc.name] [npc.verb(reach)] across with one [npc.hand] to start stroking and playing with [npc2.her] [npc2.balls+].",
						"Running [npc.her] [npc.fingers+] over [npc2.namePos] [npc2.balls+], [npc.name] [npc.verb(start)] to stroke and cup them, letting out [npc.a_moan+] as [npc2.namePos] [npc2.cock+] twitches in response.");
			}
		}
	};
	

	public static final SexAction COCK_MASTURBATING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		@Override
		public String getActionTitle() {
			return "Start handjob";
		}
		@Override
		public String getActionDescription() {
			return "Reach down and start stroking [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(wrap)] [npc.her] [npc.fingers] around [npc2.her] [npc2.cock+],"
									+ " letting out a soft [npc.moan] as [npc.she] [npc.verb(start)] slowly stroking up and down its length.",

							"[npc.Name] [npc.verb(drop)] one of [npc.her] [npc.hands] down between [npc2.namePos] [npc2.legs],"
									+ " and, taking hold of [npc2.her] [npc2.cock+], [npc.she] [npc.verb(start)] slowly jerking [npc2.herHim] off.",

							"Teasing [npc.her] [npc.fingers] over [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(start)] gently stroking up and down its throbbing length."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] eagerly [npc.verb(wrap)] [npc.her] [npc.fingers] around [npc2.her] [npc2.cock+],"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(start)] rapidly stroking up and down its length.",

							"[npc.Name] [npc.verb(drop)] one of [npc.her] [npc.hands] down between [npc2.namePos] [npc2.legs],"
									+ " and, taking hold of [npc2.her] [npc2.cock+], [npc.she] [npc.verb(start)] eagerly jerking [npc2.herHim] off.",

							"Teasing [npc.her] [npc.fingers] over [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] rapidly stroking up and down its throbbing length."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] forcefully [npc.verb(wrap)] [npc.her] [npc.fingers] around [npc2.her] [npc2.cock+],"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(start)] frantically stroking up and down its length.",

							"[npc.Name] [npc.verb(drop)] one of [npc.her] [npc.hands] down between [npc2.namePos] [npc2.legs],"
									+ " and, roughly taking hold of [npc2.her] [npc2.cock+], [npc.she] [npc.verb(start)] forcefully jerking [npc2.herHim] off.",

							"Forcefully wrapping [npc.her] [npc.fingers] around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] frantically stroking up and down its throbbing length."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] eagerly [npc.verb(wrap)] [npc.her] [npc.fingers] around [npc2.her] [npc2.cock+],"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(start)] rapidly stroking up and down its length.",

							"[npc.Name] [npc.verb(drop)] one of [npc.her] [npc.hands] down between [npc2.namePos] [npc2.legs],"
									+ " and, taking hold of [npc2.her] [npc2.cock+], [npc.she] [npc.verb(start)] eagerly jerking [npc2.herHim] off.",

							"Teasing [npc.her] [npc.fingers] over [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] rapidly stroking up and down its throbbing length."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching down between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(wrap)] [npc.her] [npc.fingers] around [npc2.her] [npc2.cock+],"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(start)] stroking up and down its length.",

							"[npc.Name] [npc.verb(drop)] one of [npc.her] [npc.hands] down between [npc2.namePos] [npc2.legs],"
									+ " and, taking hold of [npc2.her] [npc2.cock+], [npc.she] [npc.verb(start)] jerking [npc2.herHim] off.",

							"Teasing [npc.her] [npc.fingers] over [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] stroking up and down its throbbing length."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Letting out a soft [npc2.moan], [npc2.name] [npc2.verb(start)] gently bucking [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand],"
										+ " focusing on the feeling of [npc2.her] [npc2.cock+] as it throbs in response to [npc.her] touch.",
	
								" With a soft [npc2.moan], [npc2.name] [npc2.verb(start)] slowly thrusting [npc2.her] [npc2.hips] against [npc.namePos] touch,"
										+ " enjoying the feeling of [npc.her] [npc.fingers+] sliding up and down [npc2.her] [npc2.cock+].",
	
								" [npc2.Name] [npc2.verb(start)] slowly bucking [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand],"
										+ " [npc2.moaning] softly as [npc2.she] [npc2.verb(focus)] on the feeling of [npc.her] [npc.fingers+] sliding up and down [npc2.her] [npc2.cock+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Letting out [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand],"
										+ " focusing on the feeling of [npc2.her] [npc2.cock+] as it throbs in response to [npc.her] touch.",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly thrusting [npc2.her] [npc2.hips] against [npc.namePos] touch,"
										+ " enjoying the feeling of [npc.her] [npc.fingers+] sliding up and down [npc2.her] [npc2.cock+].",
								
								" [npc2.Name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand],"
										+ " [npc2.moaning+] as [npc2.she] [npc2.verb(focus)] on the feeling of [npc.her] [npc.fingers+] sliding up and down [npc2.her] [npc2.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Letting out [npc2.a_moan+], [npc2.name] [npc2.verb(start)] violently bucking [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand],"
										+ " growling out as [npc2.she] [npc2.verb(order)] [npc.herHim] to continue pleasuring [npc2.her] [npc2.cock+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] roughly thrusting [npc2.her] [npc2.hips] against [npc.namePos] touch,"
										+ " filling [npc.her] [npc.hand] with [npc2.her] [npc2.cock+] as [npc2.she] [npc2.verb(order)] [npc.herHim] not to stop.",
	
								" [npc2.Name] [npc2.verb(start)] forcefully bucking [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand],"
										+ " [npc2.moaning+] as [npc2.she] [npc2.verb(order)] [npc.herHim] to continue servicing [npc2.her] [npc2.cock+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Letting out [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand],"
										+ " focusing on the feeling of [npc2.her] [npc2.cock+] as it throbs in response to [npc.her] touch.",
								
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly thrusting [npc2.her] [npc2.hips] against [npc.namePos] touch,"
										+ " enjoying the feeling of [npc.her] [npc.fingers+] sliding up and down [npc2.her] [npc2.cock+].",
	
								" [npc2.Name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand],"
										+ " [npc2.moaning+] as [npc2.she] [npc2.verb(focus)] on the feeling of [npc.her] [npc.fingers+] sliding up and down [npc2.her] [npc2.cock+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Letting out [npc2.a_moan+], [npc2.name] [npc2.verb(start)] bucking [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand],"
										+ " focusing on the feeling of [npc2.her] [npc2.cock+] as it throbs in response to [npc.her] touch.",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] thrusting [npc2.her] [npc2.hips] against [npc.namePos] touch,"
										+ " enjoying the feeling of [npc.her] [npc.fingers+] sliding up and down [npc2.her] [npc2.cock+].",
	
								" [npc2.Name] [npc2.verb(start)] bucking [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand],"
										+ " [npc2.moaning+] as [npc2.she] [npc2.verb(focus)] on the feeling of [npc.her] [npc.fingers+] sliding up and down [npc2.her] [npc2.cock+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(try)] to pull [npc2.her] [npc2.hips] back from [npc.namePos] unwanted touch, [npc2.sobbing] and struggling as [npc2.her] throbbing [npc2.cock] betrays [npc2.her] arousal.",
	
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)] to pull away from [npc.name], struggling and protesting as [npc2.her] hard [npc2.cock] betrays [npc2.her] arousal.",
	
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] struggle back against [npc.namePos] unwanted touch."));
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
							" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] out in response,"
									+ " letting out a delighted [npc2.moan] as [npc2.she] [npc2.verb(start)] enthusiastically imploring [npc.name] to continue giving [npc2.her] a handjob.",
		
							" A delighted [npc2.moan] bursts out from between [npc2.namePos] [npc2.lips],"
									+ " and [npc2.she] [npc2.verb(start)] eagerly thrusting [npc2.her] [npc2.hips] out against [npc.namePos] touch as [npc2.she] [npc2.verb(beg)] [npc.herHim] to continue giving [npc2.herHim] a handjob.",
		
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(buck)] [npc2.her] [npc2.hips] forwards, eagerly imploring [npc.name] to continue giving [npc2.herHim] a handjob."));
				case SUB_RESISTING:
					return (UtilText.returnStringAtRandom(
							" Failing to recoil [npc2.her] [npc2.hips] away from [npc.namePos] touch, [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away from [npc2.herHim].",
		
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips],"
									+ " before [npc2.she] [npc2.verb(start)] weakly trying to push [npc.name] away, squirming and protesting as [npc.name] [npc.verb(continue)] to give [npc2.herHim] a handjob.",
		
							" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain,"
									+ " to pull [npc2.her] [npc2.hips] away from [npc.namePos] touch, struggling against [npc.herHim] as [npc.she] [npc.verb(continue)] giving [npc2.herHim] an unwanted handjob."));
				case DOM_GENTLE:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] out in response, letting out a delighted [npc2.moan] before starting to enthusiastically implore [npc.name] to continue giving [npc2.herHim] a handjob.",
	
							" A delighted [npc2.moan] bursts out from between [npc2.namePos] [npc2.lips],"
									+ " and [npc2.she] [npc2.verb(start)] gently pushing [npc2.her] [npc2.hips] out against [npc.namePos] touch as [npc2.she] [npc2.verb(beg)] [npc.herHim] to carry on giving [npc2.herHim] a handjob.",
	
							" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(push)] out [npc2.her] [npc2.hips] as [npc2.she] [npc2.verb(implore)] [npc.name] to continue giving [npc2.herHim] a handjob."));
				case DOM_ROUGH:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] out in response, letting out a delighted [npc2.moan] as [npc2.she] [npc2.verb(start)] commanding [npc.name] to continue giving [npc2.herHim] a handjob.",
	
							" A delighted [npc2.moan] bursts out from between [npc2.namePos] [npc2.lips],"
									+ " and [npc2.she] [npc2.verb(start)] roughly thrusting [npc2.her] [npc2.hips] out against [npc.namePos] touch as [npc2.she] [npc2.verb(order)] [npc.name] to carry on giving [npc2.herHim] a handjob.",
	
							" [npc2.Moaning] in delight, [npc2.name] aggressively [npc2.verb(thrust)] out [npc2.her] [npc2.hips] as [npc2.she] [npc2.verb(command)] [npc.name] to continue giving [npc2.herHim] a handjob."));
				case SUB_NORMAL:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] out in response, letting out [npc2.a_moan] as [npc2.she] [npc2.verb(implore)] [npc.name] to continue giving [npc2.herHim] a handjob.",
		
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips],"
									+ " and [npc2.she] [npc2.verb(push)] [npc2.her] [npc2.hips] out against [npc.namePos] touch as [npc2.she] [npc2.verb(implore)] [npc.herHim] to carry on giving [npc2.herHim] a handjob.",
		
							" [npc2.Moaning+], [npc2.name] [npc2.verb(push)] out [npc2.her] [npc2.hips], imploring [npc.name] to continue giving [npc2.herHim] a handjob."));
			}
		}
		return "";
	}
	
	public static final SexAction COCK_MASTURBATING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Gentle handjob";
		}
		@Override
		public String getActionDescription() {
			return "Gently continue giving [npc2.name] a handjob.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(isCharacterPrecumming(Main.sex.getCharacterTargetedForSexAction(this))) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] gently [npc.verb(slide)] [npc.her] [npc.hand] up and down around [npc2.namePos] [npc2.penisGirth] cock, spreading [npc2.her] [npc2.precum+] all over [npc2.her] shaft in the process.",
						
						"Using [npc2.namePos] [npc2.precum+] as extra lubricant, [npc.name] gently [npc.verb(wrap)] [npc.her] [npc.fingers+] around [npc2.her] [npc2.cock+] and [npc.verb(jerk)] [npc2.herHim] off.",
						
						"[npc.Name] [npc.verb(let)] out a reassuring [npc.moan] as [npc.she] gently [npc.verb(slide)] [npc.her] [npc.hand+] up and down the length of [npc2.namePos] [npc2.precum]-dripping [npc2.cock]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Gently wrapping [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] slowly [npc.verb(start)] to slide [npc.her] [npc.hand] up and down [npc2.her] [npc2.penisGirth] shaft.",

						"[npc.Name] gently [npc.verb(wrap)] [npc.her] [npc.hand] around [npc2.namePos] [npc2.cock+],"
								+ " leaning forwards and inhaling [npc2.her] [npc2.scent+] as [npc.she] gently [npc.verb(slide)] [npc.her] [npc.fingers+] up and down around [npc2.her] [npc2.penisGirth] length.",

						"Gently pressing [npc.herself] against [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] softly [npc.verb(slide)] [npc.her] [npc.hand+] up and down the length of [npc2.namePos] [npc2.cock+]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Handjob";
		}
		@Override
		public String getActionDescription() {
			return "Continue giving [npc2.name] a handjob.";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			if(isCharacterPrecumming(Main.sex.getCharacterTargetedForSexAction(this))) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] eagerly [npc.verb(slide)] [npc.her] [npc.hand] up and down around [npc2.namePos] [npc2.penisGirth] cock, spreading [npc2.her] [npc2.precum+] all over [npc2.her] shaft in the process.",
						
						"Using [npc2.namePos] [npc2.precum+] as extra lubricant, [npc.name] enthusiastically [npc.verb(wrap)] [npc.her] [npc.fingers+] around [npc2.her] [npc2.cock+] and eagerly [npc.verb(jerk)] [npc2.herHim] off.",
						
						"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] rapidly [npc.verb(slide)] [npc.her] [npc.hand+] up and down the length of [npc2.namePos] [npc2.precum]-dripping [npc2.cock]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Eagerly wrapping [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] rapidly [npc.verb(start)] to slide [npc.her] [npc.hand] up and down [npc2.her] [npc2.penisGirth] shaft.",
	
						"[npc.Name] enthusiastically [npc.verb(wrap)] [npc.her] [npc.hand] around [npc2.namePos] [npc2.cock+],"
								+ " leaning forwards and inhaling [npc2.her] [npc2.scent+] as [npc.she] eagerly [npc.verb(slide)] [npc.her] [npc.fingers+] up and down around [npc2.her] [npc2.penisGirth] length.",
	
						"Happily pressing [npc.herself] against [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] rapidly [npc.verb(slide)] [npc.her] [npc.hand+] up and down the length of [npc2.namePos] [npc2.cock+]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Rough handjob";
		}
		@Override
		public String getActionDescription() {
			return "Give [npc2.name] a rough handjob.";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			if(isCharacterPrecumming(Main.sex.getCharacterTargetedForSexAction(this))) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] roughly [npc.verb(pump)] [npc.her] [npc.hand] up and down around [npc2.namePos] [npc2.penisGirth] cock, spreading [npc2.her] [npc2.precum+] all over [npc2.her] shaft in the process.",
						
						"Using [npc2.namePos] [npc2.precum+] as extra lubricant, [npc.name] forcefully [npc.verb(wrap)] [npc.her] [npc.fingers+] around [npc2.her] [npc2.cock+] and aggressively [npc.verb(jerk)] [npc2.herHim] off.",
						
						"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] roughly [npc.verb(pump)] [npc.her] [npc.hand+] up and down the length of [npc2.namePos] [npc2.precum]-dripping [npc2.cock]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Forcefully gripping [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(start)] to roughly pump [npc.her] [npc.hand] up and down the length of [npc2.her] [npc2.penisGirth] shaft.",
	
						"[npc.Name] aggressively [npc.verb(wrap)] [npc.her] [npc.hand] around [npc2.namePos] [npc2.cock+],"
								+ " leaning forwards and inhaling [npc2.her] [npc2.scent+] as [npc.she] roughly [npc.verb(pump)] [npc.her] [npc.fingers+] up and down around [npc2.her] [npc2.penisGirth] length.",
	
						"Roughly grinding [npc.herself] against [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] aggressively [npc.verb(pump)] [npc.her] [npc.hand+] up and down the length of [npc2.namePos] [npc2.cock+]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Handjob";
		}
		@Override
		public String getActionDescription() {
			return "Continue giving [npc2.namePos] a handjob.";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			if(isCharacterPrecumming(Main.sex.getCharacterTargetedForSexAction(this))) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(slide)] [npc.her] [npc.hand] up and down around [npc2.namePos] [npc2.penisGirth] cock, spreading [npc2.her] [npc2.precum+] all over [npc2.her] shaft in the process.",
						
						"Using [npc2.namePos] [npc2.precum+] as extra lubricant, [npc.name] [npc.verb(wrap)] [npc.her] [npc.fingers+] around [npc2.her] [npc2.cock+] and [npc.verb(jerk)] [npc2.herHim] off.",
						
						"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(slide)] [npc.her] [npc.hand+] up and down the length of [npc2.namePos] [npc2.precum]-dripping [npc2.cock]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Wrapping [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(start)] to slide [npc.her] [npc.hand] up and down [npc2.her] [npc2.penisGirth] shaft.",
	
						"[npc.Name] [npc.verb(wrap)] [npc.her] [npc.hand] around [npc2.namePos] [npc2.cock+],"
								+ " leaning forwards and inhaling [npc2.her] [npc2.scent+] as [npc.she] [npc.verb(slide)] [npc.her] [npc.fingers+] up and down around [npc2.her] [npc2.penisGirth] length.",
	
						"Pressing [npc.herself] against [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(slide)] [npc.her] [npc.hand+] up and down the length of [npc2.namePos] [npc2.cock+]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_COCK_MASTURBATING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Eager handjob";
		}
		@Override
		public String getActionDescription() {
			return "Enthusiastically give [npc2.name] a handjob.";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			if(isCharacterPrecumming(Main.sex.getCharacterTargetedForSexAction(this))) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] eagerly [npc.verb(slide)] [npc.her] [npc.hand] up and down around [npc2.namePos] [npc2.penisGirth] cock, spreading [npc2.her] [npc2.precum+] all over [npc2.her] shaft in the process.",
						
						"Using [npc2.namePos] [npc2.precum+] as extra lubricant, [npc.name] enthusiastically [npc.verb(wrap)] [npc.her] [npc.fingers+] around [npc2.her] [npc2.cock+] and eagerly [npc.verb(jerk)] [npc2.herHim] off.",
						
						"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] rapidly [npc.verb(slide)] [npc.her] [npc.hand+] up and down the length of [npc2.namePos] [npc2.precum]-dripping [npc2.cock]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Eagerly wrapping [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] rapidly [npc.verb(start)] to slide [npc.her] [npc.hand] up and down [npc2.her] [npc2.penisGirth] shaft.",
	
						"[npc.Name] enthusiastically [npc.verb(wrap)] [npc.her] [npc.hand] around [npc2.namePos] [npc2.cock+],"
								+ " leaning forwards and inhaling [npc2.her] [npc2.scent+] as [npc.she] eagerly [npc.verb(slide)] [npc.her] [npc.fingers+] up and down around [npc2.her] [npc2.penisGirth] length.",
	
						"Happily pressing [npc.herself] against [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] rapidly [npc.verb(slide)] [npc.her] [npc.hand+] up and down the length of [npc2.namePos] [npc2.cock+]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Resist handjob";
		}
		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.hand] away from [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(feel)] tears starting to well up in [npc.namePos] [npc.eyes], and, unable to keep it in any longer,"
							+ " [npc.a_sob+] bursts out from [npc.her] mouth as [npc.she] weakly [npc.verb(try)] to pull [npc.her] [npc.hand] away from [npc2.namePos] [npc2.cock+].",

					"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.hand] away from [npc2.namePos] [npc2.cock+].",

					"[npc.Name] [npc.sobsVerb] in distress as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.hand] away from [npc2.namePos] [npc2.cock+]."));
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Anticipating [npc.her] move, [npc2.name] [npc2.verb(reach)] down and gently, but firmly, [npc2.verb(force)] [npc.name] to continue giving [npc2.herHim] a handjob.",

							" [npc2.Name] quickly [npc2.verb(reach)] down and firmly [npc2.verb(grab)] [npc.namePos] [npc.hand], before gently guiding it back to [npc2.her] [npc2.cock+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Anticipating [npc.her] move, [npc2.name] [npc2.verb(reach)] down and [npc2.verb(grab)] [npc.namePos] [npc.hand], before firmly forcing [npc.herHim] to continue giving [npc2.herHim] a handjob.",

							" [npc2.Name] quickly [npc2.verb(reach)] down and [npc2.verb(grab)] [npc.namePos] [npc.hand], before pulling it back to [npc2.her] [npc2.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Anticipating [npc.her] move, [npc2.name] [npc2.verb(reach)] down and roughly [npc2.verb(grab)] [npc.namePos] [npc.hand], before forcing [npc.herHim] to continue giving [npc2.herHim] a handjob.",

							" [npc2.Name] quickly [npc2.verb(reach)] down and roughly [npc2.verb(grab)] [npc.namePos] [npc.hand], before yanking it back to [npc2.her] [npc2.cock+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction COCK_MASTURBATING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Stop handjob";
		}
		@Override
		public String getActionDescription() {
			return "Let go of [npc2.namePos] [npc2.cock+] and stop giving [npc2.herHim] a handjob.";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking [npc.her] [npc.hand] away from [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(give)] [npc2.her] [npc2.cockHead] one last rough squeeze as [npc.she] stops giving [npc2.herHim] a handjob.",

							"[npc.Name] sharply [npc.verb(inhale)], breathing in [npc2.namePos] [npc2.scent+] before taking [npc.her] [npc.fingers] away from [npc2.her] [npc2.cock+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking [npc.her] [npc.hand] away from [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(give)] [npc2.her] [npc2.cockHead] one last stroke as [npc.she] stops giving [npc2.herHim] a handjob.",

							"[npc.Name] sharply [npc.verb(inhale)], breathing in [npc2.namePos] [npc2.scent+] before taking [npc.her] [npc.fingers] away from [npc2.her] [npc2.cock+]."));
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
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] stops stimulating [npc2.her] [npc2.cock+].",

							" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desire for more of [npc.namePos] attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction COCK_MASTURBATED_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Get handjob";
		}
		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to start giving you a handjob.";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking a gentle, but firm, grip on [npc2.namePos] [npc2.hand], [npc.name] slowly [npc.verb(guide)] [npc2.her] [npc2.fingers] around [npc.her] [npc.cock+],"
									+ " letting out a little [npc.moan] before making [npc2.herHim] start giving [npc.herHim] a handjob.",
							
							"Taking hold of [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.her] [npc2.fingers] around [npc.her] [npc.cock+], and with a slow, steady pressure,"
									+ " [npc.she] gently [npc.verb(make)] [npc2.herHim] start giving [npc.herHim] a handjob."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a firm grip on [npc2.her] [npc2.hand], [npc.name] eagerly [npc.verb(guide)] [npc2.namePos] [npc2.fingers] around [npc.her] [npc.cock+],"
									+ " letting out [npc.a_moan+] before greedily making [npc2.herHim] start giving [npc.herHim] a handjob.",

							"Taking hold of [npc2.namePos] [npc2.hand], [npc.name] eagerly [npc.verb(guide)] [npc2.her] [npc2.fingers] around [npc.her] [npc.cock+], and with a determined pressure,"
									+ " [npc.she] greedily [npc.verb(make)] [npc2.herHim] start giving [npc.herHim] a handjob."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a vice-like grip on [npc2.her] [npc2.hand], [npc.name] [npc.verb(grind)] [npc2.namePos] [npc2.fingers] around [npc.her] [npc.cock+],"
									+ " letting out [npc.a_moan+] before roughly forcing [npc2.herHim] to start giving [npc.herHim] a handjob.",

							"Grabbing [npc2.namePos] [npc2.hand], [npc.name] forcefully [npc.verb(push)] [npc2.namePos] [npc2.fingers] around [npc.her] [npc.cock+], and with a dominant, jerking motion,"
									+ " [npc.she] roughly [npc.verb(force)] [npc2.herHim] start giving [npc.herHim] a handjob."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a firm grip on [npc2.her] [npc2.hand], [npc.name] eagerly [npc.verb(guide)] [npc2.namePos] [npc2.fingers] around [npc.her] [npc.cock+],"
									+ " letting out [npc.a_moan+] before greedily making [npc2.herHim] start giving [npc.herHim] a handjob.",

							"Taking hold of [npc2.namePos] [npc2.hand], [npc.name] eagerly [npc.verb(guide)] [npc2.namePos] [npc2.fingers] around [npc.her] [npc.cock+], and with a determined pressure,"
									+ " [npc.she] greedily [npc.verb(make)] [npc2.herHim] start giving [npc.herHim] a handjob."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a firm grip on [npc2.her] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.namePos] [npc2.fingers] around [npc.her] [npc.cock+],"
									+ " letting out [npc.a_moan+] before making [npc2.herHim] start giving [npc.herHim] a handjob.",

							"Taking hold of [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.namePos] [npc2.fingers] around [npc.her] [npc.cock+], and with a determined pressure,"
									+ " [npc.she] [npc.verb(make)] [npc2.herHim] start giving [npc.herHim] a handjob."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.she] [npc2.verb(start)] gently stroking [npc.her] [npc.cock+].",
	
								" With a soft [npc2.moan], [npc2.name] [npc2.verb(start)] slowly sliding [npc2.her] [npc2.hand] up and down the length of [npc.namePos] [npc.cock+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(start)] eagerly stroking [npc.her] [npc.cock+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] happily sliding [npc2.her] [npc2.hand] up and down the length of [npc.namePos] [npc.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(start)] forcefully stroking [npc.her] [npc.cock], and, seeking to remind [npc.name] who's in charge,"
										+ " [npc2.she] roughly [npc2.verb(squeeze)] [npc2.her] throbbing shaft.",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] roughly pumping [npc2.her] [npc2.hand] up and down the length of [npc.namePos] [npc.cock+],"
										+ " seeking to remind [npc.herHim] who's in charge as [npc2.she] forcefully [npc2.verb(squeeze)] [npc2.her] throbbing shaft."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(start)] eagerly stroking [npc.her] [npc.cock+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] happily sliding [npc2.her] [npc2.hand] up and down the length of [npc.namePos] [npc.cock+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(start)] stroking [npc.her] [npc.cock+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] sliding [npc2.her] [npc2.hand] up and down the length of [npc.namePos] [npc.cock+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(force)] [npc2.herHim] to perform a handjob,"
										+ " struggling against [npc.namePos] firm grip on [npc2.her] [npc2.hand] as [npc2.she] [npc2.verb(try)] to pull [npc2.herself] free.",
	
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(start)] struggling against [npc.namePos] tight grip on [npc2.her] [npc2.hand],"
										+ " pleading for [npc.name] to stop as [npc.she] [npc2.verb(force)] [npc2.herHim] to perform a handjob."));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATED_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Gently receive handjob";
		}
		@Override
		public String getActionDescription() {
			return "Gently enjoy [npc2.namePos] handjob.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently pushing [npc.her] [npc.hips] out against [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc2.name] [npc2.verb(continue)] giving [npc.herHim] a handjob.",

					"With a soft [npc.moan], [npc.name] gently [npc.verb(start)] thrusting [npc.her] [npc.hips] out against [npc2.namePos] [npc2.hand], enjoying the handjob that [npc.sheIs] receiving.",

					"Slowly thrusting [npc.her] [npc.hips] out against [npc2.namePos] [npc2.hand],"
							+ " a soft [npc.moan] drifts out from between [npc.namePos] [npc.lips+] as [npc.her] movements amplify the pleasure [npc.sheIs] receiving from [npc2.namePos] handjob."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATED_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Receive handjob";
		}
		@Override
		public String getActionDescription() {
			return "Enjoy [npc2.namePos] handjob.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc.her] [npc.hips] out against [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] [npc2.verb(continue)] giving [npc.herHim] a handjob.",

					"With [npc.a_moan+], [npc.name] enthusiastically [npc.verb(start)] thrusting [npc.her] [npc.hips] out against [npc2.namePos] [npc2.hand], enjoying the handjob that [npc.sheIs] receiving.",

					"Eagerly thrusting [npc.her] [npc.hips] out against [npc2.namePos] [npc2.hand],"
							+ " [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] movements amplify the pleasure [npc.sheIs] receiving from [npc2.namePos] handjob."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATED_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Roughly receive handjob";
		}
		@Override
		public String getActionDescription() {
			return "Roughly enjoy [npc2.namePos] handjob.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Violently slamming [npc.her] [npc.hips] out against [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] [npc2.verb(continue)] giving [npc.herHim] a handjob.",

					"With [npc.a_moan+], [npc.name] aggressively [npc.verb(start)] thrusting [npc.her] [npc.hips] out against [npc2.namePos] [npc2.hand], enjoying the handjob that [npc.sheIs] receiving.",

					"Roughly thrusting [npc.her] [npc.hips] out against [npc2.namePos] [npc2.hand],"
							+ " [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] movements amplify the pleasure [npc.sheIs] receiving from [npc2.namePos] handjob."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATED_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Receive handjob";
		}
		@Override
		public String getActionDescription() {
			return "Enjoy [npc2.namePos] handjob.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Pushing [npc.her] [npc.hips] out against [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] [npc2.verb(continue)] giving [npc.herHim] a handjob.",

					"With [npc.a_moan+], [npc.name] [npc.verb(start)] thrusting [npc.her] [npc.hips] out against [npc2.namePos] [npc2.hand], enjoying the handjob that [npc.sheIs] receiving.",

					"Thrusting [npc.her] [npc.hips] out against [npc2.namePos] [npc2.hand],"
							+ " [npc.a_moan+] drifts out from between [npc.namePos] [npc.lips+] as [npc.her] movements amplify the pleasure [npc.sheIs] receiving from [npc2.namePos] handjob."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATED_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Eagerly receive handjob";
		}
		@Override
		public String getActionDescription() {
			return "Eagerly enjoy [npc2.namePos] handjob.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc.her] [npc.hips] out against [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] [npc2.verb(continue)] giving [npc.herHim] a handjob.",

					"With [npc.a_moan+], [npc.name] enthusiastically [npc.verb(start)] thrusting [npc.her] [npc.hips] out against [npc2.namePos] [npc2.hand], enjoying the handjob that [npc.sheIs] receiving.",

					"Eagerly thrusting [npc.her] [npc.hips] out against [npc2.namePos] [npc2.hand],"
							+ " [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] movements amplify the pleasure [npc.sheIs] receiving from [npc2.namePos] handjob."));

			return UtilText.nodeContentSB.toString();
		}

	};

	public static final SexAction COCK_MASTURBATED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Resist receiving handjob";
		}
		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.cock+] away from [npc2.namePos] [npc2.hand].";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(feel)] tears starting to well up in [npc.namePos] [npc.eyes], and, unable to keep it in any longer,"
							+ " [npc.a_sob+] bursts out from [npc.her] mouth as [npc.she] weakly [npc.verb(try)] to pull [npc.her] [npc.hips] and [npc.cock] away from [npc2.namePos] [npc2.hand].",

					"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.cock+] away from [npc2.namePos] [npc2.hand].",

					"[npc.Name] [npc.sobsVerb] in distress as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.cock+] away from [npc2.namePos] [npc2.hand]."));
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Anticipating [npc.her] move, [npc2.name] gently, but firmly, [npc2.verb(grip)] [npc.namePos] [npc.cock+],"
									+ " giving [npc.herHim] no option but to stay still and try to enjoy the handjob [npc2.sheIs] giving [npc2.herHim].",

							" [npc2.Name] softly, but firmly, [npc2.verb(grip)] [npc.namePos] [npc.cock+] in [npc2.her] [npc2.hand], preventing [npc2.her] escape and continuing with the ongoing handjob."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Anticipating [npc.her] move, [npc2.name] firmly [npc2.verb(grip)] [npc.namePos] [npc.cock+],"
									+ " giving [npc.herHim] no option but to stay still and try to enjoy the handjob [npc2.sheIs] giving [npc2.herHim].",

							" [npc2.Name] firmly [npc2.verb(grip)] [npc.namePos] [npc.cock+] in [npc2.her] [npc2.hand], preventing [npc2.her] escape and continuing with the ongoing handjob."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Anticipating [npc.her] move, [npc2.name] roughly [npc2.verb(grip)] [npc.namePos] [npc.cock+],"
									+ " giving [npc.herHim] no option but to stay still and try to enjoy the handjob [npc2.sheIs] giving [npc2.herHim].",

							" [npc2.Name] forcefully [npc2.verb(grip)] [npc.namePos] [npc.cock+] in [npc2.her] [npc2.hand], preventing [npc2.her] escape and continuing with the ongoing handjob."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Stop receiving handjob";
		}
		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to take [npc2.her] [npc2.hand] off your [npc.cock].";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc2.namePos] [npc2.fingers] away from [npc.her] [npc.cock+], [npc.name] [npc.verb(growl)] at [npc2.name] as [npc.she] [npc.verb(command)] [npc2.herHim] to stop with [npc2.her] handjob.",
	
							"[npc.Name] [npc.verb(lean)] into [npc2.name], inhaling [npc2.her] [npc2.scent] before yanking [npc2.her] [npc2.fingers] away from [npc.her] [npc.cock+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc2.namePos] [npc2.fingers] away from [npc.her] [npc.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] before telling [npc2.herHim] to stop with [npc2.her] handjob.",
	
							"[npc.Name] [npc.verb(lean)] into [npc2.name], inhaling [npc2.her] [npc2.scent] before pushing [npc2.her] [npc2.hand] away from [npc.her] [npc.cock+]."));
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
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(stop)] [npc2.herHim] from touching [npc.her] [npc.cock+].",
	
							" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desire to give [npc.her] [npc.cock+] more of [npc2.her] attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
