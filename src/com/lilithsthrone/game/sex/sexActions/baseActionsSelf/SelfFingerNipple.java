package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
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

/**
 * @author Innoxia
 * @version 0.4.0.0
 * @since 0.1.79
 */
public class SelfFingerNipple {
    public static final SexAction PINCH_NIPPLES = new SexAction(
            SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
            ArousalIncrease.ONE_MINIMUM,
            ArousalIncrease.THREE_NORMAL,
            CorruptionLevel.ZERO_PURE,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
            SexParticipantType.SELF) {
        @Override
        public boolean isBaseRequirementsMet() {
            return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
        }

        @Override
        public String getActionTitle() {
            return "Pinch nipples (self)";
        }

        @Override
        public String getActionDescription() {
            return "Play with your nipples.";
        }

        @Override
        public String getDescription() {
            UtilText.nodeContentSB.setLength(0);

            UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
                    "[npc.Name] [npc.verb(reach)] up and [npc.verb(start)] playing with [npc.her] hard [npc.nipples], pinching and rubbing them as [npc.she] [npc.moans] with arousal.",
                    "[npc.NamePos] fingertips tease over [npc.her] [npc.breasts+], stopping to pinch and tug at [npc.her] [npc.nipples+] as [npc.she] [npc.moan] and sighs in delight.",
                    "[npc.Name] reaches up to [npc.her] [npc.breasts+], and, with eager fingers, [npc.verb(start)] to pinch and rub at [npc.her] exposed [npc.nipples].",
                    "With [npc.a_moan+], [npc.name] [npc.verb(reach)] up to [npc.her] [npc.nipples+], pinching and flicking them as [npc.she] continues to cry out in delight."));

            switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
                case ONE_TRICKLE:
                    UtilText.nodeContentSB.append(" A small trickle of [npc.milk] leaks out around [npc.her] fingertips.");
                    break;
                case TWO_SMALL_AMOUNT:
                    UtilText.nodeContentSB.append(" A small squirt of [npc.milk] leaks out around [npc.her] fingertips.");
                    break;
                case THREE_DECENT_AMOUNT:
                    UtilText.nodeContentSB.append(" A trickle of [npc.milk] runs out around [npc.her] fingertips.");
                    break;
                case FOUR_LARGE_AMOUNT:
                    UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] starts to flow out around [npc.her] fingers and run down [npc.her] [npc.breasts+].");
                    break;
                case FIVE_VERY_LARGE_DROOLING:
                    UtilText.nodeContentSB.append(" [npc.Milk] starts drooling out in a little stream around [npc.her] fingers.");
                    break;
                case SIX_EXTREME_AMOUNT_DRIPPING:
                    UtilText.nodeContentSB.append(" [npc.Milk] starts pouring out in a constant stream to run down [npc.her] [npc.breasts+].");
                    break;
                case SEVEN_MONSTROUS_AMOUNT_POURING:
                    UtilText.nodeContentSB.append(" [npc.Milk] starts pouring out in a heavy flow to quickly soak [npc.her] [npc.breasts+].");
                    break;
                default:
                    break;
            }

            return UtilText.nodeContentSB.toString();
        }

        @Override
        public String applyEffectsString() {
            return Main.sex.getCharacterPerformingAction().incrementBreastStoredMilk(-10);
        }

    };


    public static final SexAction SELF_FINGER_NIPPLE_PENETRATION = new SexAction(
            SexActionType.START_ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
            SexParticipantType.SELF) {
        @Override
        public boolean isBaseRequirementsMet() {
            return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
        }

        @Override
        public String getActionTitle() {
            return "Finger nipples (self)";
        }

        @Override
        public String getActionDescription() {
            return "Sink [npc.her] fingers into [npc.her] [npc.nipples+].";
        }

        @Override
        public String getDescription() {
            UtilText.nodeContentSB.setLength(0);

            UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
                    "[npc.Name] [npc.verb(reach)] up, letting out a lewd [npc.moan] as [npc.she] eagerly [npc.verb(sink)] [npc.her] fingers into [npc.her] fuckable [npc.nipples].",
                    "[npc.NamePos] fingertips tease over [npc.her] breasts, circling around [npc.her] [npc.nipples] before greedily sinking inside.",
                    "[npc.Name] [npc.moans] and [npc.verb(squeal)] as [npc.she] [npc.verb(start)] eagerly fingering [npc.her] nipple-cunts.",
                    "With a lewd cry, [npc.name] [npc.verb(sink)] [npc.her] digits into [npc.her] inviting nipple-cunts, panting heavily as [npc.she] [npc.verb(start)] eagerly fingering [npc.herself]."));
            switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
                case ONE_TRICKLE:
                    UtilText.nodeContentSB.append(" A small trickle of [npc.milk] leaks out around [npc.her] fingertips.");
                    break;
                case TWO_SMALL_AMOUNT:
                    UtilText.nodeContentSB.append(" A small squirt of [npc.milk] leaks out around [npc.her] fingertips.");
                    break;
                case THREE_DECENT_AMOUNT:
                    UtilText.nodeContentSB.append(" A trickle of [npc.milk] runs out around [npc.her] fingertips.");
                    break;
                case FOUR_LARGE_AMOUNT:
                    UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] starts to flow out around [npc.her] fingers and run down [npc.her] [npc.breasts+].");
                    break;
                case FIVE_VERY_LARGE_DROOLING:
                    UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] starts drooling out in a little stream around [npc.her] fingers.");
                    break;
                case SIX_EXTREME_AMOUNT_DRIPPING:
                    UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] starts pouring out in a constant stream to run down [npc.her] [npc.breasts+].");
                    break;
                case SEVEN_MONSTROUS_AMOUNT_POURING:
                    UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] starts pouring out in a heavy flow to quickly soak [npc.her] [npc.breasts+].");
                    break;
                default:
                    break;
            }

            return UtilText.nodeContentSB.toString();
        }

        @Override
        public String applyEffectsString() {
            return Main.sex.getCharacterPerformingAction().incrementBreastStoredMilk(-10);
        }
    };

    public static final SexAction DOM_SELF_FINGER_NIPPLE_GENTLE = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
            SexParticipantType.SELF,
            SexPace.DOM_GENTLE) {

        @Override
        public String getActionTitle() {
            return "Gentle nipple fingering (self)";
        }

        @Override
        public String getActionDescription() {
            return "Gently finger [npc.her] [npc.nipples+].";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] slowly [npc.verb(push)] [npc.her] [npc.fingers] deep inside [npc.her] [npc.nipple+].",
                    "Gently pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(finger)] [npc.her] [npc.breast+].",
                    "Curling [npc.her] [npc.fingers] up inside [npc.her] [npc.nipple(true)], [npc.name] [npc.verb(let)] out a little whimper as [npc.she] [npc.verb(start)] pumping [npc.her] digits in and out of [npc.her] [npc.breast+].",
                    "Focusing on pleasuring [npc.her] fuckable [npc.breasts], [npc.name] [npc.verb(start)] gently pumping [npc.her] [npc.fingers] in and out of one of [npc.her] [npc.nipples+].");
        }

    };

    public static final SexAction DOM_SELF_FINGER_NIPPLE_NORMAL = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
            SexParticipantType.SELF,
            SexPace.DOM_NORMAL) {

        @Override
        public String getActionTitle() {
            return "Nipple fingering (self)";
        }

        @Override
        public String getActionDescription() {
            return "Concentrate on fingering [npc.her] [npc.nipples+].";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] greedily [npc.verb(push)] [npc.her] [npc.fingers] deep inside [npc.her] [npc.nipple+].",
                    "Pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(finger)] [npc.her] [npc.breast+].",
                    "Curling [npc.her] [npc.fingers] up inside [npc.her] fuckable [npc.nipple(true)], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] pumping [npc.her] digits in and out of [npc.her] [npc.breast+].",
                    "Focusing on pleasuring [npc.her] [npc.breasts+], [npc.name] [npc.verb(start)] pumping [npc.her] [npc.fingers] in and out of one of [npc.her] [npc.nipples+].");
        }

    };

    public static final SexAction DOM_SELF_FINGER_NIPPLE_ROUGH = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
            SexParticipantType.SELF,
            SexPace.DOM_ROUGH) {

        @Override
        public String getActionTitle() {
            return "Rough nipple-fingering (self)";
        }

        @Override
        public String getActionDescription() {
            return "Roughly finger [npc.her] [npc.nipples+].";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] roughly [npc.verb(slam)] [npc.her] [npc.fingers] deep inside [npc.her] [npc.nipple+], before starting to rapidly finger [npc.her] [npc.breast(true)].",
                    "Roughly pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(finger)] [npc.her] [npc.breast+].",
                    "Forcefully curling [npc.her] [npc.fingers] up inside [npc.her] fuckable [npc.nipple(true)], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] roughly grinding [npc.her] digits in and out of [npc.her] [npc.breast+].",
                    "Focusing on pleasuring [npc.her] fuckable [npc.breasts+], [npc.name] [npc.verb(start)] roughly slamming [npc.her] [npc.fingers] in and out of one of [npc.her] [npc.nipples+].");
        }

    };

    public static final SexAction SUB_SELF_FINGER_NIPPLE_NORMAL = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
            SexParticipantType.SELF,
            SexPace.SUB_NORMAL) {

        @Override
        public String getActionTitle() {
            return "Nipple-fingering (self)";
        }

        @Override
        public String getActionDescription() {
            return "Concentrate on fingering [npc.her] [npc.nipples+].";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] greedily [npc.verb(push)] [npc.her] [npc.fingers] deep inside [npc.her] [npc.nipple+].",
                    "Pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(finger)] [npc.her] [npc.breast+].",
                    "Curling [npc.her] [npc.fingers] up inside [npc.her] fuckable [npc.nipple(true)], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] pumping [npc.her] digits in and out of [npc.her] [npc.breast+].",
                    "Focusing on pleasuring [npc.her] [npc.breasts+], [npc.name] [npc.verb(start)] pumping [npc.her] [npc.fingers] in and out of one of [npc.her] [npc.nipples+].");
        }

    };

    public static final SexAction SUB_SELF_FINGER_NIPPLE_EAGER = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
            SexParticipantType.SELF,
            SexPace.SUB_EAGER) {

        @Override
        public String getActionTitle() {
            return "Eager nipple-fingering (self)";
        }

        @Override
        public String getActionDescription() {
            return "Eagerly finger [npc.her] [npc.nipples+].";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] eagerly [npc.verb(slam)] [npc.her] [npc.fingers] deep inside [npc.her] [npc.nipple+], before starting to desperately finger [npc.her] [npc.breast(true)].",
                    "Enthusiastically pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] frantically [npc.verb(finger)] [npc.her] [npc.breast+].",
                    "Desperately curling [npc.her] [npc.fingers] up inside [npc.her] fuckable [npc.nipple(true)], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] eagerly grinding [npc.her] digits in and out of [npc.her] [npc.breast+].",
                    "Focusing on pleasuring [npc.her] fuckable [npc.breasts+], [npc.name] eagerly [npc.verb(start)] slamming [npc.her] [npc.fingers] in and out of one of [npc.her] [npc.nipples+].");
        }

    };

    public static final SexAction SELF_FINGER_NIPPLE_STOP_PENETRATION = new SexAction(
            SexActionType.STOP_ONGOING,
            ArousalIncrease.ONE_MINIMUM,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ZERO_PURE,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
            SexParticipantType.SELF) {

        @Override
        public String getActionTitle() {
            return "Stop fingering nipples (self)";
        }

        @Override
        public String getActionDescription() {
            return "Stop fingering [npc.her] [npc.nipples+].";
        }

        @Override
        public String getDescription() {
            return "Letting out a satisfied [npc.moan], [npc.name] [npc.verb(slide)] [npc.her] fingers out of [npc.her] [npc.nipples+].";
        }
    };
}
