package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
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
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class SelfFingerPenis {
	
	public static final SexAction STROKE_PENIS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "Tease cock (self)";
		}
		@Override
		public String getActionDescription() {
			return "Get some pleasure from stroking and teasing your [npc.cock].";
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.PENIS)) {
				return UtilText.returnStringAtRandom(
						"Reaching down between [npc.her] [npc.legs], [npc.name] [npc.verb(slide)] [npc.her] [npc.fingers+] up and down the length of [npc.her] [npc.cock+] and [npc.verb(let)] out [npc.a_moan+].",
						"[npc.Name] [npc.groansVerb+] as [npc.she] [npc.verb(tease)] [npc.her] [npc.fingers] over the [npc.cockHead+] of [npc.her] [npc.cock+].",
						"Stroking and teasing [npc.her] [npc.fingers+] up and down the length of [npc.her] [npc.cock+], [npc.name] can't help but let out a series of [npc.moans+].",
						"Sliding [npc.her] [npc.fingers] up and down over [npc.her] [npc.cock+], [npc.name] [npc.verb(let)] out a series of [npc.groans+].");
				
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(run)] [npc.her] [npc.hand] over [npc.her] groin, before pressing [npc.her] "+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()
							+" down against [npc.her] [npc.cock+] and letting out [npc.a_moan+].",
						"[npc.Name] [npc.verb(slide)] [npc.her] [npc.fingers+] down between [npc.her] [npc.legs], before letting out [npc.a_moan+] as [npc.she] [npc.verb(press)] [npc.her] "
							+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+" down against [npc.her] [npc.cock+].",
						"[npc.Name] [npc.verb(slide)] [npc.her] [npc.fingers] over [npc.her] "+Main.sex.getCharacterPerformingAction().getHighestZLayerCoverableArea(CoverableArea.PENIS).getName()
							+", before pressing down and trying to stimulate [npc.her] [npc.cock+] through [npc.her] clothing.",
						"Pushing down between [npc.her] [npc.legs] with the palm of [npc.her] [npc.hand], [npc.name] [npc.verb(rub)] [npc.her] "+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()
							+" down against [npc.her] concealed [npc.cock+].");
			}
		}
		@Override
		public void applyEffects() {
			if(Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.PENIS)) {
				Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
			}
		}
	};
	
	public static final SexAction START_COCK_STROKING = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "Start stroking cock";
		}
		@Override
		public String getActionDescription() {
			return "Take hold of your [npc.cock+] and start jerking yourself off.";
		}
		@Override
		public String getDescription() {
			return (UtilText.returnStringAtRandom(
					"Reaching down between [npc.her] [npc.legs], [npc.name] [npc.verb(grab)] hold of [npc.her] [npc.cock+] and [npc.verb(start)] to masturbate.",
					"Taking hold of [npc.her] [npc.cock+], [npc.name] [npc.verb(start)] repeatedly stroking up and down.",
					"Reaching down and taking hold of [npc.her] [npc.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] jerking [npc.herself] off."));
		}
	};
	
	public static final SexAction DOM_GENTLE_COCK_STROKING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Gentle cock stroking";
		}
		@Override
		public String getActionDescription() {
			return "Focus on gently stroking your [npc.cock+].";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] slowly [npc.verb(slide)] [npc.her] [npc.fingers] up and down the length of [npc.her] [npc.cock+].",
					"Gently sliding [npc.her] [npc.fingers+] up and down around [npc.her] [npc.cock+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(jerk)] [npc.herself] off.",
					"Wrapping [npc.her] [npc.fingers] around [npc.her] [npc.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] gently [npc.verb(stroke)] up and down.",
					"Focusing on the pleasure [npc.sheIs] giving to [npc.herself], [npc.name] [npc.verb(focus)] on gently stroking [npc.her] [npc.fingers] up and down around [npc.her] [npc.cock+].");
		}
	};
	
	public static final SexAction DOM_NORMAL_COCK_STROKING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Cock stroking";
		}
		@Override
		public String getActionDescription() {
			return "Focus on stroking your [npc.cock+].";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] eagerly [npc.verb(slide)] [npc.her] [npc.fingers] up and down the length of [npc.her] [npc.cock+].",
					"Desperately sliding [npc.her] [npc.fingers+] up and down around [npc.her] [npc.cock+], [npc.name] [npc.verb(start)] letting out a series of [npc.moans+] as [npc.she] rhythmically [npc.verb(jerk)] [npc.herself] off.",
					"Wrapping [npc.her] [npc.fingers] around [npc.her] [npc.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] frantically [npc.verb(stroke)] up and down.",
					"Focusing on the pleasure [npc.sheIs] giving to [npc.herself], [npc.name] [npc.verb(focus)] on eagerly stroking [npc.her] [npc.fingers] up and down around [npc.her] [npc.cock+].");
		}
	};
	
	public static final SexAction DOM_ROUGH_COCK_STROKING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Rough cock stroking";
		}
		@Override
		public String getActionDescription() {
			return "Focus on roughly stroking your [npc.cock+].";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] violently [npc.verb(jerk)] [npc.her] [npc.fingers] up and down the length of [npc.her] [npc.cock+].",
					"Roughly pumping [npc.her] [npc.fingers+] up and down around [npc.her] [npc.cock+], [npc.name] [npc.verb(start)] letting out a series of [npc.moans+] as [npc.she] rhythmically [npc.verb(jerk)] [npc.herself] off.",
					"Wrapping [npc.her] [npc.fingers] around [npc.her] [npc.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] frantically [npc.verb(stroke)] up and down.",
					"Focusing on the pleasure [npc.sheIs] giving to [npc.herself], [npc.name] [npc.verb(focus)] on frantically stroking [npc.her] [npc.fingers] up and down around [npc.her] [npc.cock+].");
		}
	};
	
	public static final SexAction SUB_NORMAL_COCK_STROKING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Cock stroking";
		}
		@Override
		public String getActionDescription() {
			return "Focus on gently stroking your [npc.cock+].";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] [npc.verb(slide)] [npc.her] [npc.fingers] up and down the length of [npc.her] [npc.cock+].",
					"Sliding [npc.her] [npc.fingers+] up and down around [npc.her] [npc.cock+], [npc.name] [npc.verb(start)] letting out a series of [npc.moans+] as [npc.she] rhythmically [npc.verb(jerk)] [npc.herself] off.",
					"Wrapping [npc.her] [npc.fingers] around [npc.her] [npc.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(stroke)] up and down.",
					"Focusing on the pleasure [npc.sheIs] giving to [npc.herself], [npc.name] [npc.verb(focus)] on stroking [npc.her] [npc.fingers] up and down around [npc.her] [npc.cock+].");
		}
		
	};
	
	public static final SexAction SUB_EAGER_COCK_STROKING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Eager cock stroking";
		}
		@Override
		public String getActionDescription() {
			return "Focus on eagerly stroking your [npc.cock+].";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] eagerly [npc.verb(slide)] [npc.her] [npc.fingers] up and down the length of [npc.her] [npc.cock+].",
					"Desperately sliding [npc.her] [npc.fingers+] up and down around [npc.her] [npc.cock+], [npc.name] [npc.verb(start)] letting out a series of [npc.moans+] as [npc.she] rhythmically [npc.verb(jerk)] [npc.herself] off.",
					"Wrapping [npc.her] [npc.fingers] around [npc.her] [npc.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] frantically [npc.verb(stroke)] up and down.",
					"Focusing on the pleasure [npc.sheIs] giving to [npc.herself], [npc.name] [npc.verb(focus)] on eagerly stroking [npc.her] [npc.fingers] up and down around [npc.her] [npc.cock+].");
		}
	};
	
	public static final SexAction STOP_COCK_STROKING = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "Stop cock stroking";
		}
		@Override
		public String getActionDescription() {
			return "Stop stroking your [npc.cock+].";
		}
		@Override
		public String getDescription() {
			return "With [npc.a_moan+], [npc.name] [npc.verb(take)] [npc.her] [npc.hand] away from [npc.her] [npc.cock+] and [npc.verb(stop)] jerking off.";
		}
	};
}
