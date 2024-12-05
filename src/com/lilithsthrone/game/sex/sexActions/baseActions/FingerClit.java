package com.lilithsthrone.game.sex.sexActions.baseActions;

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
 * @since 0.1.79
 * @version 0.2.8
 * @author Innoxia
 */
public class FingerClit {

	public static final SexAction CLIT_PLAY = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Clit play";
		}

		@Override
		public String getActionDescription() {
			return "Reach down to [npc2.namePos] [npc2.pussy+] and start playing with [npc2.her] [npc2.clit].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getOrificesBeingPenetratedBy(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER, Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Slowly sinking [npc.her] [npc.fingers+] deep into [npc2.namePos] [npc2.pussy+], [npc.name] lifts [npc.her] thumb up to [npc2.her] [npc2.clit],"
										+ " before gently pressing down and rubbing against it as [npc.she] [npc.verb(continue)] curling [npc.her] digits up inside of [npc2.herHim].",
								"Gently sliding [npc.her] [npc.fingers+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] [npc.her] thumb down against [npc2.her] [npc2.clit+],"
										+ " softly rubbing against it as [npc.she] [npc.verb(let)] out a little [npc.moan].",
								"Gently pushing [npc.her] [npc.fingers] deep into [npc2.namePos] [npc2.pussy+],"
										+ " a little [npc.moan] escapes from between [npc.namePos] [npc.lips] as [npc.she] lifts [npc.her] thumb to start gently rubbing and pressing down on [npc2.her] [npc2.clit+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly digging [npc.her] [npc.fingers+] deep into [npc2.namePos] [npc2.pussy+], [npc.name] lifts [npc.her] thumb up to [npc2.her] [npc2.clit],"
										+ " before forcefully pressing down and grinding against it as [npc.she] [npc.verb(continue)] dominantly curling [npc.her] digits up inside of [npc2.herHim].",
								"Forcing [npc.her] [npc.fingers+] deep into [npc2.namePos] [npc2.pussy+], [npc.name] grinds [npc.her] thumb down against [npc2.her] [npc2.clit+],"
										+ " roughly rubbing against it as [npc.she] [npc.verb(let)] out [npc.a_moan+].",
								"Slamming [npc.her] [npc.fingers] deep into [npc2.namePos] [npc2.pussy+],"
										+ " [npc.a_moan+] escapes from between [npc.namePos] [npc.lips] as [npc.she] lifts [npc.her] thumb to start roughly rubbing and grinding down on [npc2.her] [npc2.clit+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Sinking [npc.her] [npc.fingers+] deep into [npc2.namePos] [npc2.pussy+], [npc.name] lifts [npc.her] thumb up to [npc2.her] [npc2.clit],"
										+ " before pressing down and rubbing against it as [npc.she] [npc.verb(continue)] curling [npc.her] digits up inside of [npc2.herHim].",
								"Sliding [npc.her] [npc.fingers+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] [npc.her] thumb down against [npc2.her] [npc2.clit+],"
										+ " rubbing against it as [npc.she] [npc.verb(let)] out [npc.a_moan+].",
								"Pushing [npc.her] [npc.fingers] deep into [npc2.namePos] [npc2.pussy+],"
										+ " [npc.a_moan+] escapes from between [npc.namePos] [npc.lips] as [npc.she] lifts [npc.her] thumb to start rubbing and pressing down on [npc2.her] [npc2.clit+]."));
						break;
					default: // Dom normal and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly sinking [npc.her] [npc.fingers+] deep into [npc2.namePos] [npc2.pussy+], [npc.name] lifts [npc.her] thumb up to [npc2.her] [npc2.clit],"
										+ " before greedily pressing down and rubbing against it as [npc.she] [npc.verb(continue)] curling [npc.her] digits up inside of [npc2.herHim].",
								"Greedily sliding [npc.her] [npc.fingers+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] [npc.her] thumb down against [npc2.her] [npc2.clit+],"
										+ " eagerly rubbing against it as [npc.she] [npc.verb(let)] out [npc.a_moan+].",
								"Pushing [npc.her] [npc.fingers] deep into [npc2.namePos] [npc2.pussy+],"
										+ " [npc.a_moan+] escapes from between [npc.namePos] [npc.lips] as [npc.she] lifts [npc.her] thumb to start eagerly rubbing and pressing down on [npc2.her] [npc2.clit+]."));
						break;
				}
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+],"
											+ " and with a gentle bucking of [npc2.her] [npc2.hips], [npc2.she] softly [npc2.verb(encourage)] [npc.name] to continue playing with [npc2.her] [npc2.clit+].",
									" [npc2.Name] [npc2.verb(start)] gently bucking [npc2.her] [npc2.hips] in response,"
											+ " softly pressing [npc2.her] [npc2.clit+] against [npc.namePos] [npc.fingers] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to continue.",
									" [npc2.Name] gently [npc2.verb(start)] thrusting [npc2.her] [npc2.hips] back against [npc.namePos] [npc.fingers],"
											+ " softly [npc2.moaning] as [npc2.she] [npc2.verb(focus)] on the pleasure in [npc2.her] [npc2.clit+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] bursts out from [npc2.namePos] mouth as [npc2.name] roughly [npc2.verb(push)] [npc2.her] [npc2.hips] back against [npc.her] touch.",
									" [npc2.Name] roughly [npc2.verb(grind)] [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand],"
											+ " [npc2.moaning] in delight as [npc2.she] [npc2.verb(order)] [npc.herHim] to continue playing with [npc2.her] [npc2.clit+].",
									" [npc2.Name] forcefully [npc2.verb(thrust)] [npc2.her] [npc2.hips] out against [npc.namePos] [npc.fingers],"
											+ " [npc2.moaning] and arching [npc2.her] back as [npc2.she] roughly [npc2.verb(grind)] [npc2.her] [npc2.clit+] down against [npc.her] touch."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] bursts out from [npc2.namePos] mouth as [npc2.name] [npc2.verb(push)] [npc2.her] [npc2.hips] back against [npc.her] touch.",
									" [npc2.Name] [npc2.verb(push)] [npc2.namePos] [npc2.hips] against [npc.namePos] [npc.hand],"
											+ " [npc2.moaning] in delight as [npc2.she] [npc2.verb(encourage)] [npc.herHim] to continue playing with [npc2.her] [npc2.clit+].",
									" [npc2.Name] [npc2.verb(push)] [npc2.her] [npc2.hips] out against [npc.namePos] [npc.fingers],"
											+ " [npc2.moaning] and arching [npc2.her] back as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.clit+] down against [npc.her] touch."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_sob+] bursts out from [npc2.namePos] mouth as [npc2.she] [npc2.verb(try)] to pull [npc2.her] [npc2.pussy] away from [npc.namePos] touch,"
											+ " pleading with [npc.herHim] to stop as [npc2.she] struggle against [npc.herHim].",
									" [npc2.Name] [npc2.verb(pull)] [npc2.her] [npc2.hips] back in response,"
											+ " letting out [npc2.a_sob+] as [npc2.she] [npc2.verb(plead)] for [npc.name] to stop touching [npc2.herHim].",
									" [npc2.Name] [npc2.verb(try)] to pull [npc2.her] [npc2.pussy] away from [npc.namePos] [npc.fingers],"
											+ " [npc2.sobbing+] as [npc2.she] [npc2.verb(beg)] for [npc.name] to leave [npc2.name] alone."));
							break;
						default: // Dom normal and sub eager:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] bursts out from [npc2.namePos] mouth as [npc2.name] eagerly [npc2.verb(push)] [npc2.her] [npc2.hips] back against [npc.her] touch.",
									" [npc2.Name] eagerly [npc2.verb(push)] [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand],"
											+ " [npc2.moaning] in delight as [npc2.she] enthusiastically [npc2.verb(encourage)] [npc.herHim] to continue playing with [npc2.her] [npc2.clit+].",
									" [npc2.Name] eagerly [npc2.verb(push)] [npc2.her] [npc2.hips] out against [npc.namePos] [npc.fingers],"
											+ " [npc2.moaning] and arching [npc2.her] back as [npc2.she] desperately [npc2.verb(press)] [npc2.her] [npc2.clit+] down against [npc.her] touch."));
							break;
					}
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Slowly sliding [npc.her] [npc.fingers+] up the length of [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(focus)] [npc.her] attention on [npc2.her] [npc2.clit+],"
										+ " gently squeezing and rubbing it as [npc.she] [npc.verb(lean)] in against [npc2.herHim] and [npc.verb(let)] out a little [npc.moan].",
								"Gently tracing [npc.her] [npc.fingers+] over [npc2.namePos] [npc2.pussy+],"
										+ " [npc.name] [npc.verb(let)] out a little [npc.moan] as [npc.she] [npc.verb(home)] in on [npc2.her] [npc2.clit+], before starting to softly rub and pinch it.",
								"Teasing [npc.her] [npc.fingers] over [npc2.namePos] [npc2.pussy+],"
										+ " a little [npc.moan] escapes from between [npc.namePos] [npc.lips] as [npc.she] [npc.verb(start)] to gently rub and pinch at [npc2.her] [npc2.clit+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly grinding [npc.her] [npc.fingers+] up the length of [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(focus)] [npc.her] attention on [npc2.her] [npc2.clit+],"
										+ " cruelly squeezing and pinching it as [npc.she] [npc.verb(lean)] in against [npc2.herHim] and [npc.verb(let)] out [npc.a_moan+].",
								"Greedily tracing [npc.her] [npc.fingers+] over [npc2.namePos] [npc2.pussy+],"
										+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(home)] in on [npc2.her] [npc2.clit+], before starting to roughly rub and pinch it.",
								"Grinding [npc.her] [npc.fingers] over [npc2.namePos] [npc2.pussy+],"
										+ " [npc.a_moan+] escapes from between [npc.namePos] [npc.lips] as [npc.she] [npc.verb(start)] to roughly rub and pinch at [npc2.her] [npc2.clit+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Sliding [npc.her] [npc.fingers+] up the length of [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(focus)] [npc.her] attention on [npc2.her] [npc2.clit+],"
										+ " squeezing and rubbing it as [npc.she] [npc.verb(lean)] in against [npc2.herHim] and [npc.verb(let)] out [npc.a_moan+].",
								"Tracing [npc.her] [npc.fingers+] over [npc2.namePos] [npc2.pussy+],"
										+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(home)] in on [npc2.her] [npc2.clit+], before starting to rub and pinch it.",
								"Teasing [npc.her] [npc.fingers] over [npc2.namePos] [npc2.pussy+],"
										+ " [npc.a_moan+] escapes from between [npc.namePos] [npc.lips] as [npc.she] [npc.verb(start)] to rub and pinch at [npc2.her] [npc2.clit+]."));
						break;
					default: // Dom normal and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Greedily sliding [npc.her] [npc.fingers+] up the length of [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(focus)] [npc.her] attention on [npc2.her] [npc2.clit+],"
										+ " eagerly squeezing and rubbing it as [npc.she] [npc.verb(lean)] in against [npc2.herHim] and [npc.verb(let)] out [npc.a_moan+].",
								"Firmly tracing [npc.her] [npc.fingers+] over [npc2.namePos] [npc2.pussy+],"
										+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(home)] in on [npc2.her] [npc2.clit+], before starting to eagerly rub and pinch it.",
								"Teasing [npc.her] [npc.fingers] over [npc2.namePos] [npc2.pussy+],"
										+ " [npc.a_moan+] escapes from between [npc.namePos] [npc.lips] as [npc.she] [npc.verb(start)] to eagerly rub and pinch at [npc2.her] [npc2.clit+]."));
						break;
				}
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+], and with a gentle bucking of [npc2.her] [npc2.hips],"
											+ " [npc2.she] softly [npc2.verb(encourage)] [npc.name] to continue playing with [npc2.her] [npc2.clit+].",
									" [npc2.Name] [npc2.verb(start)] gently bucking [npc2.her] [npc2.hips] in response,"
											+ " softly pressing [npc2.her] [npc2.clit+] against [npc.namePos] [npc.fingers] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to continue.",
									" [npc2.Name] gently [npc2.verb(start)] thrusting [npc2.her] [npc2.hips] back against [npc.namePos] [npc.fingers],"
											+ " softly [npc2.moaning] as [npc2.she] [npc2.verb(focus)] on the pleasure in [npc2.her] [npc2.clit+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] bursts out from [npc2.namePos] mouth as [npc2.she] roughly [npc2.verb(push)] [npc2.her] [npc2.hips] back against [npc.namePos] touch.",
									" [npc2.Name] roughly [npc2.verb(grind)] [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand],"
											+ " [npc2.moaning] in delight as [npc2.she] [npc2.verb(order)] [npc.herHim] to continue playing with [npc2.her] [npc2.clit+].",
									" [npc2.Name] forcefully [npc2.verb(thrust)] [npc2.her] [npc2.hips] out against [npc.namePos] [npc.fingers],"
											+ " [npc2.moaning] and arching [npc2.her] back as [npc2.she] roughly [npc2.verb(grind)] [npc2.her] [npc2.clit+] down against [npc.namePos] touch."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] bursts out from [npc2.namePos] mouth as [npc2.she] [npc2.verb(push)] [npc2.her] [npc2.hips] back against [npc.namePos] touch.",
									" [npc2.Name] [npc2.verb(push)] [npc2.namePos] [npc2.hips] against [npc.namePos] [npc.hand],"
											+ " [npc2.moaning] in delight as [npc2.she] [npc2.verb(encourage)] [npc.herHim] to continue playing with [npc2.her] [npc2.clit+].",
									" [npc2.Name] [npc2.verb(push)] [npc2.her] [npc2.hips] out against [npc.namePos] [npc.fingers],"
											+ " [npc2.moaning] and arching [npc2.her] back as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.clit+] down against [npc.namePos] touch."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_sob+] bursts out from [npc2.namePos] mouth as [npc2.she] [npc2.verb(try)] to pull [npc2.her] [npc2.pussy] away from [npc.namePos] touch,"
											+ " pleading with [npc.herHim] to stop as [npc2.she] [npc2.verb(struggle)] against [npc.herHim].",
									" [npc2.Name] [npc2.verb(pull)] [npc2.her] [npc2.hips] back in response,"
											+ " letting out [npc2.a_sob+] as [npc2.she] [npc2.verb(plead)] for [npc.name] to stop touching [npc2.herHim].",
									" [npc2.Name] [npc2.verb(try)] to pull [npc2.her] [npc2.pussy] away from [npc.namePos] [npc.fingers],"
											+ " [npc2.sobbing+] as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to leave [npc2.herHim] alone."));
							break;
						default: // Dom normal and sub eager:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] bursts out from [npc2.namePos] mouth as [npc2.she] eagerly [npc2.verb(push)] [npc2.her] [npc2.hips] back against [npc.namePos] touch.",
									" [npc2.Name] eagerly [npc2.verb(push)] [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand],"
											+ " [npc2.moaning] in delight as [npc2.she] enthusiastically [npc2.verb(encourage)] [npc.name] to continue playing with [npc2.her] [npc2.clit+].",
									" [npc2.Name] eagerly [npc2.verb(push)] [npc2.her] [npc2.hips] out against [npc.namePos] [npc.fingers],"
											+ " [npc2.moaning] and arching [npc2.her] back as [npc2.she] desperately [npc2.verb(press)] [npc2.her] [npc2.clit+] down against [npc.namePos] touch."));
							break;
					}
				}
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
}
