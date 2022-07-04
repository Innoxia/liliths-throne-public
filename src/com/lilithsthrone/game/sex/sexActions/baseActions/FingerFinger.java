package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.4.3.2
 * @version 0.4.4.1
 * @author Innoxia
 */
public class FingerFinger {
	
	public static final SexAction HAND_HOLDING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isLovingAction() {
			return true;
		}
		@Override
		public String getActionTitle() {
			return "Start hand holding";
		}
		@Override
		public String getActionDescription() {
			return "Take hold of [npc2.namePos] [npc2.hands].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Wanting some more physical contact with [npc2.name], [npc.name] [npc.verb(reach)] out and gently [npc.verb(take)] hold of [npc2.her] [npc2.hands].",
							"Letting out [npc.a_moan+], [npc.name] [npc.verb(reach)] out and gently [npc.verb(take)] hold of [npc2.namePos] [npc2.hands]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Wanting a greater degree of control over [npc2.namePos] movements, [npc.name] [npc.verb(reach)] out and roughly [npc.verb(grab)] hold of [npc2.her] [npc2.hands].",
							"Letting out [npc.a_moan+], [npc.name] [npc.verb(reach)] out and roughly [npc.verb(grab)] hold of [npc2.namePos] [npc2.hands]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Wanting some more physical contact with [npc2.name], [npc.name] [npc.verb(reach)] out and eagerly [npc.verb(take)] hold of [npc2.her] [npc2.hands].",
							"Letting out [npc.a_moan+], [npc.name] [npc.verb(reach)] out and eagerly [npc.verb(take)] hold of [npc2.namePos] [npc2.hands]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Wanting some more physical contact with [npc2.name], [npc.name] [npc.verb(reach)] out and [npc.verb(take)] hold of [npc2.her] [npc2.hands].",
							"Letting out [npc.a_moan+], [npc.name] [npc.verb(reach)] out and [npc.verb(take)] hold of [npc2.namePos] [npc2.hands]."));
					break;
				case SUB_RESISTING:
					break;
			}
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Happily wrapping [npc2.her] [npc2.fingers+] around [npc.namePos] [npc.hand] in return, [npc2.name] [npc2.verb(let)] out a gentle sigh.",
							" Responding by gently wrapping [npc2.her] [npc2.fingers+] around [npc.namePos] [npc.hand], [npc2.name] happily [npc2.verb(engage)] in [npc.namePos] hand holding."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Roughly squeezing [npc2.her] [npc2.fingers+] around [npc.namePos] [npc.hand] in return, [npc2.name] [npc2.verb(let)] out a dominant growl.",
							" Responding by tightly squeezing [npc2.her] [npc2.fingers+] around [npc.namePos] [npc.hand], [npc2.name] roughly [npc2.verb(engage)] in [npc.namePos] hand holding."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Happily wrapping [npc2.her] [npc2.fingers+] around [npc.namePos] [npc.hand] in return, [npc2.name] [npc2.verb(let)] out a happy [npc2.moan].",
							" Responding by eagerly wrapping [npc2.her] [npc2.fingers+] around [npc.namePos] [npc.hand], [npc2.name] happily [npc2.verb(engage)] in [npc.namePos] hand holding."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Wrapping [npc2.her] [npc2.fingers+] around [npc.namePos] [npc.hand] in return, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+].",
							" Responding by wrapping [npc2.her] [npc2.fingers+] around [npc.namePos] [npc.hand], [npc2.name] [npc2.verb(engage)] in [npc.namePos] hand holding."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying to pull [npc2.her] [npc2.hand] away from [npc.nameHers], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(beg)] to be released.",
							" Responding by frantically recoiling from [npc.namePos] unwanted advance, [npc2.name] [npc2.verb(start)] pleading to be left alone, all the while trying to pull [npc2.her] [npc2.hand] away from [npc.nameHers]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	private static String getTargetedCharacterResponse(SexAction action) {
		switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
			case SUB_EAGER:
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] enthusiastically [npc2.verb(respond)] in kind, and [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(grip)] [npc.namePos] [npc.hand+] in [npc2.her] own.",
						" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] eagerly [npc2.verb(grip)] [npc.namePos] [npc.hand+].",
						" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grip)] [npc.namePos] [npc.hand+]."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" Desperately trying, and failing, to pull [npc2.her] [npc2.hand] away from [npc.nameHers], [npc2.name] desperately [npc2.verb(beg)] for [npc.name] to get away from [npc2.herHim].",
						" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to pull [npc2.her] [npc2.hand] away from [npc.nameHers].",
						" [npc2.Sobbing] in distress, and with tears running down [npc2.her] [npc2.face],"
								+ " [npc2.name] weakly [npc2.verb(struggle)] against [npc.name], pleading and crying for [npc.herHim] to let go of [npc2.her] [npc2.hand]."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(respond)] in kind, and [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(grip)] [npc.namePos] [npc.hand+] in [npc2.her] own.",
						" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] [npc2.verb(grip)] [npc.namePos] [npc.hand+].",
						" Letting out [npc2.a_moan], [npc2.name] [npc2.verb(respond)] by gripping [npc.namePos] [npc.hand+]."));
				break;
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] happily [npc2.verb(respond)] in kind, and [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] softly [npc2.verb(hold)] [npc.namePos] [npc.hand+] in [npc2.her] own.",
						" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] gently [npc2.verb(hold)] [npc.namePos] [npc.hand+].",
						" Letting out [npc2.a_moan], [npc2.name] [npc2.verb(respond)] by gently squeezing [npc.namePos] [npc.hand+]."));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] forcefully [npc2.verb(respond)] in kind, and [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] roughly [npc2.verb(squeeze)] [npc.namePos] [npc.hand+] in [npc2.her] own.",
						" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] roughly [npc2.verb(squeeze)] [npc.namePos] [npc.hand+].",
						" Letting out [npc2.a_moan], [npc2.name] [npc2.verb(respond)] by forcefully squeezing [npc.namePos] [npc.hand+]."));
				break;
		}
		return "";
	}
	
	public static final SexAction HAND_HOLDING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle hand holding";
		}

		@Override
		public String getActionDescription() {
			return "Gently hold [npc2.namePos] [npc2.hand+] in your own.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Letting out a little [npc.moan], [npc.name] gently [npc.verb(squeeze)] [npc2.namePos] [npc2.hand+].",
					"[npc.Name] [npc.verb(let)] out a series of soft sighs as [npc.she] gently [npc.verb(wrap)] [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.hand+].",
					"Gently wrapping [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.hand+], [npc.name] [npc.verb(let)] out a little [npc.moan]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction HAND_HOLDING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Hand holding";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly hold [npc2.namePos] [npc2.hand+] in your own.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Letting out [npc.a_moan+], [npc.name] happily [npc.verb(squeeze)] [npc2.namePos] [npc2.hand+].",
					"[npc.Name] [npc.verb(let)] out a series of [npc.moans+] as [npc.she] eagerly [npc.verb(wrap)] [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.hand+].",
					"Eagerly wrapping [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.hand+], [npc.name] [npc.verb(let)] out a [npc.moan+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction HAND_HOLDING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Rough hand holding";
		}

		@Override
		public String getActionDescription() {
			return "Roughly grasp [npc2.namePos] [npc2.hand+] in your own.";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Letting out [npc.a_moan+], [npc.name] forcefully [npc.verb(squeeze)] [npc2.namePos] [npc2.hand+].",
					"[npc.Name] [npc.verb(let)] out a series of [npc.moans+] as [npc.she] roughly [npc.verb(clench)] [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.hand+].",
					"Roughly wrapping [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.hand+], [npc.name] [npc.verb(let)] out a [npc.moan+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction HAND_HOLDING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Hand holding";
		}

		@Override
		public String getActionDescription() {
			return "Hold [npc2.namePos] [npc2.hand+] in your own.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Letting out [npc.a_moan+], [npc.name] [npc.verb(squeeze)] [npc2.namePos] [npc2.hand+].",
					"[npc.Name] [npc.verb(let)] out a series of [npc.moans+] as [npc.she] [npc.verb(wrap)] [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.hand+].",
					"Wrapping [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.hand+], [npc.name] [npc.verb(let)] out a [npc.moan+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction HAND_HOLDING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eager hand holding";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly hold [npc2.namePos] [npc2.hand+] in your own.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Letting out [npc.a_moan+], [npc.name] happily [npc.verb(squeeze)] [npc2.namePos] [npc2.hand+].",
					"[npc.Name] [npc.verb(let)] out a series of [npc.moans+] as [npc.she] eagerly [npc.verb(wrap)] [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.hand+].",
					"Eagerly wrapping [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.hand+], [npc.name] [npc.verb(let)] out a [npc.moan+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction HAND_HOLDING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist hand holding";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [npc.hand] away from [npc2.namePos].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.hand] away from [npc2.nameHers], but [npc.she] [npc.verb(fail)] to escape from [npc2.her] rough grip.",
							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.hand] away from [npc2.nameHers], but [npc.she] [npc.do]n't manage to escape from [npc2.her] rough grip.",
							"Tears start to well up in [npc.namePos] [npc.eyes] as [npc.she] [npc.verb(fail)] to pull [npc.her] [npc.hand] away from [npc2.nameHers]."));
					break;
				default: 
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.hand] away from [npc2.nameHers], but [npc.she] [npc.verb(fail)] to escape from [npc2.her] grip.",
							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.hand] away from [npc2.nameHers], but [npc.she] [npc.do]n't manage to escape from [npc2.her] grip.",
							"Tears start to well up in [npc.namePos] [npc.eyes] as [npc.she] [npc.verb(fail)] to pull [npc.her] [npc.hand] away from [npc2.nameHers]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction HAND_HOLDING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop hand holding";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.hand+] away from [npc2.nameHers].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With one last rough squeeze, [npc.name] [npc.verb(release)] [npc.her] grip on [npc2.namePos] [npc2.hand+], putting an end to the hand holding.",
							"Roughly squeezing [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.hand] one last time, [npc.name] then [npc.verb(release)] [npc.her] grip, putting an end to the rough hand holding."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With one last squeeze, [npc.name] [npc.verb(release)] [npc.her] grip on [npc2.namePos] [npc2.hand+], putting an end to the hand holding.",
							"Squeezing [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.hand] one last time, [npc.name] then [npc.verb(release)] [npc.her] grip, putting an end to the hand holding."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Although happy to have [npc2.her] [npc2.hand] released, [npc2.name] [npc2.verb(continue)] crying and weakly struggling against [npc.name] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to leave [npc2.herHim] alone.",
							" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to struggle and protest, tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(pull)] away from [npc.name]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] [npc.her] [npc.hand] back, signalling [npc2.her] desire for more attention.",
							" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desperate desire for more of [npc.namePos] attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
