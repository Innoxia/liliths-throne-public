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
 * @version 0.3.4
 * @author Innoxia
 */
public class FingerAnus {
	
	public static final SexAction ANAL_FINGERING_PROSTATE_MASSAGE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()
					&& !Main.sex.getCharacterTargetedForSexAction(this).hasVagina();
		}
		
		@Override
		public String getActionTitle() {
			return "Prostate massage";
		}

		@Override
		public String getActionDescription() {
			return "Curl your [npc.fingers] up inside [npc2.namePos] [npc2.asshole+] and start stroking [npc2.her] prostate.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently pushing the palm of [npc.her] [npc.hand] in against [npc2.namePos] [npc2.assCloaca+],"
								+ " [npc.name] [npc.verb(slide)] [npc.her] [npc.fingers] deep into [npc2.her] [npc2.asshole], before curling them up and starting to softly stroke and massage [npc2.her] prostate.",
							"With a soft [npc.moan], [npc.name] gently [npc.verb(slide)] [npc.her] [npc.fingers+] as deep as possible into [npc2.namePos] [npc2.asshole+],"
								+ " before curling them up and starting to rhythmically stroke and massage [npc2.her] prostate.",
							"Slowly pushing [npc.her] [npc.fingers+] as deep as possible into [npc2.namePos] [npc2.asshole+],"
									+ " [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] then [npc.verb(curl)] them up and [npc.verb(start)] to gently massage [npc2.her] prostate."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly slamming the palm of [npc.her] [npc.hand] in against [npc2.namePos] [npc2.assCloaca+],"
								+ " [npc.name] [npc.verb(force)] [npc.her] [npc.fingers] deep into [npc2.her] [npc2.asshole], before curling them up and starting to forcefully stroke and massage [npc2.her] prostate.",
							"With [npc.a_moan+], [npc.name] violently [npc.verb(slam)] [npc.her] [npc.fingers+] as deep as possible into [npc2.namePos] [npc2.asshole+],"
								+ " before curling them up and starting to aggressively stroke and massage [npc2.her] prostate.",
							"Forcefully pushing [npc.her] [npc.fingers+] as deep as possible into [npc2.namePos] [npc2.asshole+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] then [npc.verb(curl)] them up and [npc.verb(start)] to roughly massage [npc2.her] prostate."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing the palm of [npc.her] [npc.hand] in against [npc2.namePos] [npc2.assCloaca+],"
								+ " [npc.name] [npc.verb(slide)] [npc.her] [npc.fingers] deep into [npc2.her] [npc2.asshole], before curling them up and starting to stroke and massage [npc2.her] prostate.",
							"With [npc.a_moan+], [npc.name] [npc.verb(push)] [npc.her] [npc.fingers+] as deep as possible into [npc2.namePos] [npc2.asshole+],"
								+ " before curling them up and starting to stroke and massage [npc2.her] prostate.",
							"Pushing [npc.her] [npc.fingers+] as deep as possible into [npc2.namePos] [npc2.asshole+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] then [npc.verb(curl)] them up and [npc.verb(start)] to massage [npc2.her] prostate."));
					break;
				default: // Normal dom and eager sub:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pushing the palm of [npc.her] [npc.hand] in against [npc2.namePos] [npc2.assCloaca+],"
								+ " [npc.name] [npc.verb(slide)] [npc.her] [npc.fingers] deep into [npc2.her] [npc2.asshole], before curling them up and starting to rapidly stroke and massage [npc2.her] prostate.",
							"With [npc.a_moan+], [npc.name] desperately [npc.verb(push)] [npc.her] [npc.fingers+] as deep as possible into [npc2.namePos] [npc2.asshole+],"
								+ " before curling them up and starting to eagerly stroke and massage [npc2.her] prostate.",
							"Happily pushing [npc.her] [npc.fingers+] as deep as possible into [npc2.namePos] [npc2.asshole+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] then [npc.verb(curl)] them up and [npc.verb(start)] to energetically massage [npc2.her] prostate."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Upon feeling this sudden stimulating sensation, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+], before gently pushing [npc2.her] [npc2.assCloaca+] against [npc.namePos] [npc.hand].",
								" Immediately, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] in response, before gently bucking [npc2.her] hips against [npc.namePos] touch.",
								" Responding to this move by letting out [npc2.a_moan+], [npc2.name] gently [npc2.verb(push)] [npc2.her] [npc2.assCloaca+] out against [npc.namePos] [npc.hand]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Upon feeling this sudden stimulating sensation, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+], before roughly forcing [npc2.her] [npc2.assCloaca+] against [npc.namePos] [npc.hand].",
								" Immediately, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] in response, before forcefully slamming [npc2.her] [npc2.assCloaca+] against [npc.namePos] touch.",
								" Responding to this move by letting out [npc2.a_moan+], [npc2.name] aggressively [npc2.verb(slam)] [npc2.her] [npc2.assCloaca+] against [npc.namePos] [npc.hand]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Upon feeling this sudden stimulating sensation, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+], before pushing [npc2.her] [npc2.assCloaca+] against [npc.namePos] [npc.hand].",
								" Immediately, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] in response, before bucking [npc2.her] hips against [npc.namePos] touch.",
								" Responding to this move by letting out [npc2.a_moan+], [npc2.name] [npc2.verb(push)] [npc2.her] [npc2.assCloaca+] against [npc.namePos] [npc.hand]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Upon feeling this unwanted stimulating sensation, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+], before desperately trying to pull [npc2.her] [npc2.assCloaca+] away from [npc.namePos] [npc.hand].",
								" Immediately, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] in response, before desperately attempting to pull [npc2.her] hips away from [npc.namePos] touch.",
								" Responding to this move by letting out [npc2.a_moan+], [npc2.name] desperately [npc2.verb(try)] to pull [npc2.her] [npc2.assCloaca+] away from [npc.namePos] [npc.name]."));
						break;
					default: // Normal dom and eager sub:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Upon feeling this sudden stimulating sensation, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+], before pushing [npc2.her] [npc2.assCloaca+] against [npc.namePos] [npc.hand].",
								" Immediately, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] in response, before desperately bucking [npc2.her] hips against [npc.namePos] touch.",
								" Responding to this move by letting out [npc2.a_moan+], [npc2.name] desperately [npc2.verb(push)] [npc2.her] [npc2.assCloaca+] against [npc.namePos] [npc.hand]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANAL_FINGERING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Finger [npc2.her] ass";
		}

		@Override
		public String getActionDescription() {
			return "Sink [npc.namePos] [npc.fingers] into [npc2.namePos] [npc2.asshole+] and start fingering [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly teasing [npc.her] [npc.fingers+] over [npc2.namePos] [npc2.assCloaca], [npc.name] [npc.verb(let)] out a little [npc.moan] before slowly sinking [npc.her] digits into [npc2.her] [npc2.asshole+].",

							
							"[npc.Name] [npc.verb(press)] [npc.her] [npc.fingers+] down between [npc2.namePos] ass cheeks,"
									+ " and with a slow, steady pressure, [npc.she] gently  [npc.verb(sink)] [npc.her] digits into [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing [npc.her] [npc.fingers+] over [npc2.namePos] [npc2.assCloaca], [npc.name] [npc.verb(let)] out [npc.a_moan+] before greedily sinking [npc.her] digits into [npc2.her] [npc2.asshole+].",

							
							"[npc.Name] eagerly [npc.verb(press)] [npc.her] [npc.fingers+] down between [npc2.namePos] ass cheeks,"
									+ " and with a determined thrust, [npc.she] greedily  [npc.verb(sink)] [npc.her] digits into [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding [npc.her] [npc.fingers+] over [npc2.namePos] [npc2.assCloaca], [npc.name] [npc.verb(let)] out [npc.a_moan+] before violently slamming [npc.her] digits deep into [npc2.her] [npc2.asshole+].",

							
							"[npc.Name] roughly grinds [npc.her] [npc.fingers+] down between [npc2.namePos] ass cheeks, and with a forceful thrust, [npc.she] greedily slams [npc.her] digits deep into [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing [npc.her] [npc.fingers+] over [npc2.namePos] [npc2.assCloaca], [npc.name] [npc.verb(let)] out [npc.a_moan+] before greedily sinking [npc.her] digits into [npc2.her] [npc2.asshole+].",

							
							"[npc.Name] eagerly [npc.verb(press)] [npc.her] [npc.fingers+] down between [npc2.namePos] ass cheeks,"
									+ " and with a determined thrust, [npc.she] greedily  [npc.verb(sink)] [npc.her] digits into [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing [npc.her] [npc.fingers+] over [npc2.namePos] [npc2.assCloaca], [npc.name] [npc.verb(let)] out [npc.a_moan+] before sinking [npc.her] digits into [npc2.her] [npc2.asshole+].",

							
							"[npc.Name] [npc.verb(press)] [npc.her] [npc.fingers+] down between [npc2.namePos] ass cheeks, and with a little push, [npc.she]  [npc.verb(sink)] [npc.her] digits into [npc2.her] [npc2.asshole+]."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc.namePos] [npc.fingers] enter [npc2.herHim],"
										+ " before gently bucking [npc2.her] [npc2.assCloaca] back against [npc.namePos] [npc.hand] as [npc2.name] [npc2.verb(help)]"
										+ " to [npc2.verb(sink)] [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.asshole+].",
	
	
								" With a soft [npc2.moan], [npc2.name] [npc2.verb(start)] gently bucking [npc2.her] [npc2.assCloaca] back against [npc.namePos] [npc.hand],"
										+ " encouraging [npc.herHim] to sink [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.asshole+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.namePos] [npc.fingers] enter [npc2.herHim],"
										+ " before eagerly bucking [npc2.her] [npc2.assCloaca] back against [npc.her] [npc.hand] as [npc2.she] enthusiastically [npc2.verb(help)]"
										+ " to [npc2.verb(sink)] [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.asshole+].",
	
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.assCloaca] back against [npc.namePos] [npc.hand],"
										+ " desperately encouraging [npc.name] to sink [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.namePos] [npc.fingers] enter [npc2.herHim],"
										+ " before violently bucking [npc2.her] [npc2.assCloaca] back against [npc.her] [npc.hand] and roughly forcing [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.asshole+].",
	
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] violently bucking [npc2.her] [npc2.assCloaca] back against [npc.namePos] [npc.hand],"
										+ " roughly forcing [npc.herHim] to sink [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.asshole+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.namePos] [npc.fingers] enter [npc2.herHim],"
										+ " eagerly bucking [npc2.her] [npc2.assCloaca] back against [npc.her] [npc.hand] as [npc2.she] enthusiastically [npc2.verb(help)]"
										+ " to [npc2.verb(sink)] [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.asshole+].",
	
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.assCloaca] back against [npc.namePos] [npc.hand],"
										+ " desperately encouraging [npc.herHim] to sink [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.namePos] [npc.fingers] enter [npc2.herHim],"
										+ " before bucking [npc2.her] [npc2.assCloaca] back against [npc.her] [npc.hand] and helping to sink [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.asshole+].",
	
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] bucking [npc2.her] [npc2.assCloaca] back against [npc.namePos] [npc.hand],"
										+ " encouraging [npc.herHim] to sink [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.asshole+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.fingers] enter [npc2.herHim],"
										+ " before trying, in vain, to pull [npc2.her] [npc2.assCloaca] away from the unwanted penetration, struggling and [npc2.sobbing] all the while.",
	
	
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)], in vain, to [npc2.verb(pull)] away from the unwanted penetration,"
										+ " [npc2.sobbing] and struggling against [npc.name] as [npc.her] unwelcome [npc.fingers] [npc2.verb(push)] deep into [npc2.her] [npc2.asshole+]."));
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
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] eagerly [npc2.verb(thrust)] [npc2.her] [npc2.assCloaca] back against [npc.namePos] [npc.hand],"
									+ " letting out a delighted [npc2.moan] as [npc2.she] [npc2.verb(start)] enthusiastically imploring [npc.name] to continue fingering [npc2.her] [npc2.asshole+].",
		
							" A delighted [npc2.moan] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, eagerly thrusting [npc2.her] [npc2.assCloaca] back against [npc.her] touch, [npc2.she] [npc2.verb(beg)] for [npc.name] to continue fingering [npc2.her] [npc2.asshole+].",
		
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.her] [npc2.assCloaca] against [npc.namePos] [npc.hand],"
									+ " eagerly begging for [npc.herHim] to continue as [npc2.her] movements drive [npc.her] [npc.fingers] ever deeper into [npc2.her] [npc2.ass+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to recoil [npc2.her] [npc2.ass+] away from [npc.namePos] unwanted touch,"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle away from [npc.namePos] intruding [npc.fingers].",
		
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
									+ " squirming and protesting as [npc.she] [npc.verb(continue)] to finger [npc2.namePos] [npc2.asshole+].",
		
							" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to recoil away from [npc.namePos] touch,"
									+ " struggling against [npc.herHim] as [npc.her] [npc.fingers+] [npc.verb(continue)] sliding deep into [npc2.namePos] [npc2.ass+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] slowly [npc2.verb(thrust)] [npc2.her] [npc2.assCloaca] back against [npc.namePos] [npc.hand],"
								+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(start)] gently imploring [npc.name] to continue fingering [npc2.her] [npc2.asshole+].",
	
						" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+],"
								+ " and, gently pushing [npc2.her] [npc2.hips] out against [npc.her] [npc.hand], [npc2.she] [npc2.verb(beg)] for [npc.name] to continue fingering [npc2.her] [npc2.asshole+].",
	
						" [npc2.Moaning] in delight, [npc2.name] slowly [npc2.verb(grind)] [npc2.her] [npc2.assCloaca] against [npc.namePos] [npc.hand],"
								+ " softly [npc2.moaning] for [npc.herHim] to continue as [npc2.her] movements cause [npc.her] [npc.fingers] to slide ever deeper into [npc2.her] [npc2.ass+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.assCloaca] back against [npc.namePos] [npc.hand],"
									+ " letting out [npc2.a_moan+] as [npc2.name] roughly [npc2.verb(order)] [npc.name] to continue fingering [npc2.her] [npc2.asshole+].",
	
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, roughly grinding [npc2.her] [npc2.hips] against [npc.her] [npc.hand], [npc2.name] dominantly [npc.verb(command)] [npc.name] to continue fingering [npc2.her] [npc2.asshole+].",
	
							" [npc2.Name] roughly [npc2.verb(grind)] [npc2.her] [npc2.assCloaca] against [npc.namePos] [npc.hand],"
									+ " ordering [npc.herHim] to continue as [npc2.her] forceful movements cause [npc.her] [npc.fingers] to sink ever deeper into [npc2.her] [npc2.ass+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.assCloaca] back against [npc.namePos] [npc.hand],"
									+ " letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(beg)] for [npc.name] to continue fingering [npc2.her] [npc2.asshole+].",
		
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, thrusting [npc2.her] [npc2.hips] out against [npc.her] touch, [npc2.name] [npc2.verb(beg)] for [npc.name] to continue fingering [npc2.her] [npc2.asshole+].",
		
							" [npc2.Moaning+], [npc2.name] [npc2.verb(grind)] [npc2.her] [npc2.assCloaca] against [npc.namePos] [npc.hand],"
									+ " begging for [npc.herHim] to continue as [npc2.her] movements drive [npc.her] [npc.fingers] ever deeper into [npc2.her] [npc2.ass+]."));
					break;
			}
		}
		return "";
	}
	
	
	public static final SexAction ANAL_FINGERING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle anal fingering";
		}

		@Override
		public String getActionDescription() {
			return "Gently finger [npc2.namePos] [npc2.asshole+].";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sinking [npc.her] [npc.fingers+] into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(curl)] [npc.her] digits up, softly stroking [npc2.her] inner walls as [npc.she] slowly [npc.verb(finger)] [npc2.her] [npc2.ass].",

					"Slowly pushing [npc.her] [npc.fingers+] into [npc2.namePos] [npc2.asshole+],"
							+ " [npc.name] gently [npc.verb(pump)] them in and out, letting out [npc.a_moan+] as [npc.she] softly [npc.verb(finger)] [npc2.her] [npc2.ass].",

					"Pressing [npc.her] [npc.hand] down against [npc2.namePos] [npc2.ass], [npc.name] [npc.verb(let)] out a little [npc.moan] as [npc.she] gently [npc.verb(slide)] [npc.her] [npc.fingers+] deep into [npc2.her] [npc2.asshole+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ANAL_FINGERING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Anal fingering";
		}
		@Override
		public String getActionDescription() {
			return "Continue fingering [npc2.namePos] [npc2.asshole+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly sinking [npc.her] [npc.fingers+] into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(curl)] [npc.her] digits up,"
							+ " enthusiastically stroking [npc2.her] inner walls as [npc.she] hungrily [npc.verb(finger)] [npc2.her] [npc2.ass].",

					"Firmly pushing [npc.her] [npc.fingers+] into [npc2.namePos] [npc2.asshole+],"
							+ " [npc.name] rapidly [npc.verb(pump)] them in and out, letting out [npc.a_moan+] as [npc.she] eagerly [npc.verb(finger)] [npc2.her] [npc2.ass].",

					"Eagerly pressing [npc.her] [npc.hand] down against [npc2.namePos] [npc2.ass], [npc.name] [npc.verb(let)] out [npc.a_moan+] before enthusiastically sliding [npc.her] [npc.fingers+] deep into [npc2.her] [npc2.asshole+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ANAL_FINGERING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Rough anal fingering";
		}

		@Override
		public String getActionDescription() {
			return "Roughly finger [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Greedily sinking [npc.her] [npc.fingers+] into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(curl)] [npc.her] digits up,"
							+ " roughly stroking [npc2.her] inner walls as [npc.she] forcefully [npc.verb(finger)] [npc2.her] [npc2.ass].",

					"Roughly pushing [npc.her] [npc.fingers+] into [npc2.namePos] [npc2.asshole+],"
							+ " [npc.name] violently [npc.verb(pump)] them in and out, letting out [npc.a_moan+] as [npc.she] forcefully [npc.verb(finger)] [npc2.her] [npc2.ass].",

					"Forcefully pressing [npc.her] [npc.hand] down against [npc2.namePos] [npc2.ass], [npc.name] [npc.verb(let)] out [npc.a_moan+] before roughly slamming [npc.her] [npc.fingers+] deep into [npc2.her] [npc2.asshole+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ANAL_FINGERING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Anal fingering";
		}

		@Override
		public String getActionDescription() {
			return "Continue fingering [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking [npc.her] [npc.fingers+] into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(curl)] [npc.her] digits up,"
							+ " stroking [npc2.her] inner walls as [npc.she] [npc.verb(finger)] [npc2.her] [npc2.ass].",

					"Pushing [npc.her] [npc.fingers+] into [npc2.namePos] [npc2.asshole+],"
							+ " [npc.name] [npc.verb(pump)] them in and out, letting out [npc.a_moan+] as [npc.she] [npc.verb(finger)] [npc2.her] [npc2.ass].",

					"Pressing [npc.her] [npc.hand] down against [npc2.namePos] [npc2.ass], [npc.name] [npc.verb(let)] out [npc.a_moan+] before sliding [npc.her] [npc.fingers+] deep into [npc2.her] [npc2.asshole+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ANAL_FINGERING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eager anal fingering";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly finger [npc2.namePos] [npc2.asshole+].";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly sinking [npc.her] [npc.fingers+] into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(curl)] [npc.her] digits up,"
							+ " enthusiastically stroking [npc2.her] inner walls as [npc.she] hungrily [npc.verb(finger)] [npc2.her] [npc2.ass].",

					"Firmly pushing [npc.her] [npc.fingers+] into [npc2.namePos] [npc2.asshole+],"
							+ " [npc.name] rapidly [npc.verb(pump)] them in and out, letting out [npc.a_moan+] as [npc.she] eagerly [npc.verb(finger)] [npc2.her] [npc2.ass].",

					"Eagerly pressing [npc.her] [npc.hand] down against [npc2.namePos] [npc2.ass], [npc.name] [npc.verb(let)] out [npc.a_moan+] before enthusiastically sliding [npc.her] [npc.fingers+] deep into [npc2.her] [npc2.asshole+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANAL_FINGERING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Resist anal fingering";
		}
		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.fingers] out of [npc2.namePos] [npc2.asshole+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(feel)] tears starting to well up in [npc.namePos] [npc.eyes], and, unable to keep it in any longer,"
							+ " [npc.a_sob+] bursts out from [npc.her] mouth as [npc.she] weakly [npc.verb(try)] to pull [npc.her] [npc.fingers] out of [npc2.namePos] [npc2.asshole+].",

					"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.fingers+] out of [npc2.namePos] [npc2.asshole+].",

					"Trying desperately to pull [npc.her] [npc.fingers+] out of [npc2.namePos] [npc2.asshole+],"
							+ " [npc.name] [npc.sob] in distress as [npc.she] [npc.verb(fail)] to pull them out of [npc2.her] [npc2.assCloaca+]."));
			
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ANAL_FINGERING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Stop anal fingering";
		}
		@Override
		public String getActionDescription() {
			return "Pull your [npc.fingers] out of [npc2.namePos] [npc2.asshole+] and stop fingering [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.fingers] out of [npc2.namePos] [npc2.asshole+], [npc.name] quickly takes [npc.her] [npc.hand] away from [npc2.namePos] [npc2.assCloaca].",

							"Thrusting deep inside of [npc2.name] one last time, [npc.name] then yanks [npc.her] [npc.fingers] back out of [npc2.namePos] [npc2.asshole+], putting an end to [npc.her] rough fingering."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.fingers] out of [npc2.namePos]  [npc2.asshole+], [npc.name] quickly takes [npc.her] [npc.hand] away from [npc2.namePos] [npc2.assCloaca].",

							"Pushing deep inside of [npc2.name] one last time, [npc.name] then [npc.verb(slide)] [npc.her] [npc.fingers] back out of [npc2.namePos] [npc2.asshole+], putting an end to [npc.her] fingering."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] gasp as [npc.name] withdraws from [npc2.namePos] [npc2.asshole], before letting out [npc2.a_sob+] as [npc2.name] [npc2.verb(continue)] to struggle against [npc.herHim].",


							" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to struggle against [npc.name] as [npc.she] withdraws from [npc2.namePos] [npc2.asshole+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] [npc.her] [npc.fingers+] out of [npc2.namePos] [npc2.asshole+].",


							" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.namePos] desire for more of [npc.namePos] attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ANAL_FINGERED_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Get anally fingered";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to start fingering your [npc.assCloaca+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking a gentle, but firm, grip on [npc2.namePos] [npc2.hand], [npc.name] slowly [npc.verb(guide)] [npc2.her] [npc2.fingers] over [npc.her] [npc.assCloaca],"
									+ " letting out a little [npc.moan] before pushing [npc2.her] digits into [npc.her] [npc.asshole+].",
							
							"Taking hold of [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.her] [npc2.fingers] between [npc.her] ass cheeks, and with a slow, steady pressure,"
									+ " [npc.she] gently [npc.verb(push)] [npc2.her] digits into [npc.her] [npc.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a firm grip on [npc2.her] [npc2.hand], [npc.name] eagerly [npc.verb(guide)] [npc2.namePos] [npc2.fingers] over [npc.her] [npc.assCloaca],"
									+ " letting out [npc.a_moan+] before greedily pushing [npc2.her] digits into [npc.her] [npc.asshole+].",

							"Taking hold of [npc2.namePos] [npc2.hand], [npc.name] eagerly [npc.verb(guide)] [npc2.her] [npc2.fingers] between [npc.her] ass cheeks, and with a determined pressure,"
									+ " [npc.she] greedily [npc.verb(push)] [npc2.her] digits into [npc.her] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a vice-like grip on [npc2.her] [npc2.hand], [npc.name] [npc.verb(grind)] [npc2.namePos] [npc2.fingers] over [npc.her] [npc.assCloaca],"
									+ " letting out [npc.a_moan+] before roughly forcing [npc2.her] digits into [npc.her] [npc.asshole+].",

							"Grabbing [npc2.namePos] [npc2.hand], [npc.name] forcefully [npc.verb(push)] [npc2.namePos] [npc2.fingers] between [npc.her] ass cheeks, and with a dominant, jerking motion,"
									+ " [npc.she] roughly [npc.verb(stuff)] [npc2.her] digits into [npc.her] [npc.asshole+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a firm grip on [npc2.her] [npc2.hand], [npc.name] eagerly [npc.verb(guide)] [npc2.namePos] [npc2.fingers] over [npc.her] [npc.assCloaca],"
									+ " letting out [npc.a_moan+] before greedily pushing [npc2.her] digits into [npc.her] [npc.asshole+].",

							"Taking hold of [npc2.namePos] [npc2.hand], [npc.name] eagerly [npc.verb(guide)] [npc2.namePos] [npc2.fingers] between [npc.her] ass cheeks, and with a determined pressure,"
									+ " [npc.she] greedily [npc.verb(push)] [npc2.her] digits into [npc.her] [npc.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a firm grip on [npc2.her] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.namePos] [npc2.fingers] over [npc.her] [npc.assCloaca],"
									+ " letting out [npc.a_moan+] before pushing [npc2.her] digits into [npc.her] [npc.asshole+].",

							"Taking hold of [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.namePos] [npc2.fingers] between [npc.her] ass cheeks, and with a determined pressure,"
									+ " [npc.she] [npc.verb(push)] [npc2.her] digits into [npc.her] [npc.asshole+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.she] [npc2.verb(enter)] [npc.herHim], curling [npc2.her] [npc2.fingers] up before gently starting to finger [npc.her] [npc.asshole+].",
	
								" With a soft [npc2.moan], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] up inside of [npc.name],"
										+ " gently pushing [npc2.her] [npc2.hand] into [npc.her] [npc.assCloaca] as [npc2.she] [npc2.verb(set)] about fingering [npc.her] [npc.asshole+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.herHim], curling [npc2.her] [npc2.fingers] up before eagerly starting to finger [npc.her] [npc.asshole+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] up inside of [npc.name],"
										+ " eagerly pushing [npc2.her] [npc2.hand] into [npc.her] [npc.assCloaca] as [npc2.she] [npc2.verb(set)] about fingering [npc.her] [npc.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.herHim], and, seeking to remind [npc.name] who's in charge,"
										+ " [npc2.she] roughly [npc2.verb(curl)] [npc2.her] [npc2.fingers] up before starting to ruthlessly finger-fuck [npc.her] [npc.asshole+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] up inside of [npc.name],"
										+ " seeking to remind [npc.name] who's in charge as [npc2.she] [npc2.verb(start)] roughly fingering [npc.her] [npc.asshole+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.herHim], curling [npc2.her] [npc2.fingers] up before eagerly starting to finger [npc.her] [npc.asshole+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] up inside of [npc.name],"
										+ " eagerly pushing [npc2.her] [npc2.hand] into [npc.her] [npc.assCloaca] as [npc2.she] [npc2.verb(set)] about fingering [npc.her] [npc.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.herHim], curling [npc2.her] [npc2.fingers] up before starting to finger [npc.her] [npc.asshole+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] up inside of [npc.name],"
										+ " pushing [npc2.her] [npc2.hand] into [npc.her] [npc.assCloaca] as [npc2.she] [npc2.verb(set)] about fingering [npc.her] [npc.asshole+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(force)] [npc2.her] [npc2.fingers] inside of [npc.herHim],"
										+ " struggling against [npc.namePos] firm grip on [npc2.her] [npc2.hand] as [npc2.she] [npc2.verb(try)] to pull [npc2.herself] free.",
	
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(start)] struggling against [npc.namePos] tight grip on [npc2.her] [npc2.hand],"
										+ " pleading for [npc.name] to stop as [npc.she] [npc2.verb(force)] [npc2.her] [npc2.fingers] deep into [npc.her] [npc.asshole+]."));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANALLY_FINGERED_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gently anally fingered";
		}

		@Override
		public String getActionDescription() {
			return "Gently enjoy [npc2.namePos] [npc2.fingers+] in your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently pushing [npc.her] [npc.assCloaca] back against [npc2.namePos] [npc2.hand],"
							+ " [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.asshole+].",

					"With a soft [npc.moan], [npc.name] gently [npc.verb(start)] gyrating [npc.her] [npc.assCloaca] back against [npc2.namePos] [npc2.hand], forcing [npc2.her] [npc2.fingers+] ever deeper into [npc.her] [npc.asshole+].",

					"Slowly thrusting [npc.her] [npc.assCloaca] back against [npc2.namePos] [npc2.hand],"
							+ " a soft [npc.moan] drifts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.asshole+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANALLY_FINGERED_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Enjoy fingering";
		}

		@Override
		public String getActionDescription() {
			return "Enjoy [npc2.namePos] [npc2.fingers+] in your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc.her] [npc.assCloaca] back against [npc2.namePos] [npc2.hand],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.asshole+].",

					"With [npc.a_moan+], [npc.name] energetically [npc.verb(start)] gyrating [npc.her] [npc.assCloaca] back against [npc2.namePos] [npc2.hand], forcing [npc2.her] [npc2.fingers+] ever deeper into [npc.her] [npc.asshole+].",

					"Enthusiastically thrusting [npc.her] [npc.assCloaca] back against [npc2.namePos] [npc2.hand],"
							+ " [npc.a_moan+] drifts out from between [npc.her] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.asshole+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANALLY_FINGERED_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Roughly anally fingered";
		}

		@Override
		public String getActionDescription() {
			return "Roughly force [npc2.namePos] [npc2.fingers+] deep inside your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Violently slamming [npc.her] [npc.assCloaca] back against [npc2.namePos] [npc2.hand],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] roughly [npc.verb(force)] [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.asshole+].",

					"With [npc.a_moan+], [npc.name] [npc.verb(start)] aggressively gyrating [npc.her] [npc.assCloaca] back against [npc2.namePos] [npc2.hand], forcing [npc2.her] [npc2.fingers+] ever deeper into [npc.her] [npc.asshole+].",

					"Roughly thrusting [npc.her] [npc.assCloaca] back against [npc2.namePos] [npc2.hand],"
							+ " [npc.a_moan+] bursts out from between [npc.her] [npc.lips+] as [npc.her] forceful movements drive [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.asshole+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANALLY_FINGERED_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Enjoy anal fingering";
		}

		@Override
		public String getActionDescription() {
			return "Enjoy [npc2.namePos] [npc2.fingers+] in [npc.her] [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Pushing [npc.her] [npc.assCloaca] back against [npc2.namePos] [npc2.hand],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.asshole+].",

					"With [npc.a_moan+], [npc.name] [npc.verb(start)] gyrating [npc.her] [npc.assCloaca] back against [npc2.namePos] [npc2.hand], forcing [npc2.her] [npc2.fingers+] ever deeper into [npc.her] [npc.asshole+].",

					"Thrusting [npc.her] [npc.assCloaca] back against [npc2.namePos] [npc2.hand],"
							+ " [npc.a_moan+] drifts out from between [npc.her] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.asshole+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANALLY_FINGERED_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eagerly anally fingered";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly push your [npc.assCloaca+] against [npc2.namePos] [npc2.hand] as [npc2.she] fingers your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc.her] [npc.assCloaca] back against [npc2.namePos] [npc2.hand],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.asshole+].",

					"With [npc.a_moan+], [npc.name] energetically [npc.verb(start)] gyrating [npc.her] [npc.assCloaca] back against [npc2.namePos] [npc2.hand], forcing [npc2.her] [npc2.fingers+] ever deeper into [npc.her] [npc.asshole+].",

					"Enthusiastically thrusting [npc.her] [npc.assCloaca] back against [npc2.namePos] [npc2.hand],"
							+ " [npc.a_moan+] drifts out from between [npc.her] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.fingers+] deep into [npc.her] [npc.asshole+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANAL_FINGERED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist anal fingering";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull [npc2.namePos] [npc2.fingers] out of your [npc.asshole+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears starting to well up in [npc.namePos] [npc.eyes], and, unable to keep it in any longer,"
									+ " [npc.a_sob+] bursts out from [npc.her] mouth as [npc.she] weakly [npc.verb(try)] to pull [npc2.namePos] gently-pumping [npc2.fingers] out of [npc.her] [npc.asshole+].",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.assCloaca] away from [npc2.namePos] unwanted touch,"
									+ " struggling in desperation as [npc2.namePos] [npc2.fingers+] continue gently sliding in and out of [npc.her] [npc.asshole+].",

							"Trying desperately to pull [npc.her] [npc.assCloaca] away from [npc2.namePos] [npc2.hand],"
									+ " [npc.name] [npc.sob] in distress as [npc2.namePos] [npc2.fingers+] continue gently sliding deep into [npc.her] [npc.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears starting to well up in [npc.namePos] [npc.eyes], and, unable to keep it in any longer,"
									+ " [npc.a_sob+] bursts out from [npc.her] mouth as [npc.she] weakly [npc.verb(try)] to pull [npc2.namePos] greedily-thrusting [npc2.fingers] out of [npc.her] [npc.asshole+].",


							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.assCloaca] away from [npc2.namePos] unwanted touch,"
									+ " struggling in desperation as [npc2.namePos] [npc2.fingers+] continue eagerly sliding in and out of [npc.her] [npc.asshole+].",


							"Trying desperately to pull [npc.her] [npc.assCloaca] away from [npc2.namePos] [npc2.hand],"
									+ " [npc.name] [npc.sob] in distress as [npc2.namePos] [npc2.fingers+] continue eagerly pumping deep into [npc.her] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears starting to well up in [npc.namePos] [npc.eyes], and, unable to keep it in any longer,"
									+ " [npc.a_sob+] bursts out from [npc.her] mouth as [npc.she] weakly [npc.verb(try)] to pull [npc2.namePos] roughly-thrusting [npc2.fingers] out of [npc.her] [npc.asshole+].",


							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.assCloaca] away from [npc2.namePos] unwanted touch,"
									+ " struggling in desperation as [npc2.namePos] [npc2.fingers+] continue roughly slamming in and out of [npc.her] [npc.asshole+].",


							"Trying desperately to pull [npc.her] [npc.assCloaca] away from [npc2.namePos] [npc2.hand],"
									+ " [npc.name] [npc.sob] in distress as [npc2.namePos] [npc2.fingers+] continue roughly slamming deep into [npc.her] [npc.asshole+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANAL_FINGERED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop anally fingered";
		}

		@Override
		public String getActionDescription() {
			return "Force [npc2.name] to pull [npc2.her] [npc2.fingers] out of your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc2.namePos] [npc2.fingers] out of [npc.her] [npc.asshole+], [npc.name] [npc.verb(growl)] at [npc2.name] as [npc.she] [npc.verb(command)] [npc2.herHim] to stop fingering [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] into [npc2.name], inhaling [npc2.her] [npc2.scent] as [npc.she] [npc.verb(yank)] [npc2.her] [npc2.fingers] out of [npc.her] [npc.asshole+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc2.namePos] [npc2.fingers] out of [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] out [npc.a_moan+] before telling [npc2.herHim] to stop fingering [npc.herHim].",

							"[npc.Name] lean into [npc2.name], inhaling [npc2.her] [npc2.scent] as [npc.name] [npc.verb(slide)] [npc2.her] [npc2.fingers] out of [npc.her] [npc.asshole+]."));
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
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(stop)] [npc2.herHim] from stimulating [npc.her] [npc.asshole+].",

							" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desire to give [npc.her] [npc.asshole+] more of [npc2.her] attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
