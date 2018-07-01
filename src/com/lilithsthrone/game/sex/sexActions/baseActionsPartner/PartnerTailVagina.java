package com.lilithsthrone.game.sex.sexActions.baseActionsPartner;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.82
 * @version 0.1.82
 * @author Innoxia
 */
public class PartnerTailVagina {
	
	public static final SexAction PARTNER_TEASE_TAIL_OVER_PUSSY = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Tail tease";
		}

		@Override
		public String getActionDescription() {
			return "Slide [npc.namePos] [npc.tail] over [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this)) ||Sex.isConsensual();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Snaking [npc.her] [npc.tail+] up to [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] slowly teasing the [npc.tailTip+] up and down between [npc2.namePos] pussy lips, ready to penetrate [npc2.name] at any moment.",

							"With a soft [npc.moan], [npc.name] snakes [npc.her] [npc.tail+] up to [npc2.namePos] [npc2.pussy+], before starting to gently slide the [npc.tailTip] up and down between [npc2.namePos] folds.",

							"Gently sliding the [npc.tailTip+] of [npc.her] [npc.tail] up and down over [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] out a soft [npc.moan] at the thought of being able to penetrate [npc2.name] whenever [npc.she] feels like it."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Snaking [npc.her] [npc.tail+] up to [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] eagerly sliding the [npc.tailTip+] up and down between [npc2.namePos] pussy lips, ready to penetrate [npc2.name] at any moment.",

							"With [npc.a_moan+], [npc.name] snakes [npc.her] [npc.tail+] up to [npc2.namePos] [npc2.pussy+], before starting to eagerly slide the [npc.tailTip] up and down between [npc2.namePos] folds.",

							"Eagerly sliding the [npc.tailTip+] of [npc.her] [npc.tail] up and down over [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] out [npc.a_moan+] at the thought of being able to penetrate [npc2.name] whenever [npc.she] feels like it."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding [npc.her] [npc.tail+] up against [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(pull)] back a little before starting to slide the [npc.tailTip+] up and down between [npc2.namePos] pussy lips,"
									+ " ready to start fucking [npc2.name] at any moment.",

							"With [npc.a_moan+], [npc.name] lines [npc.her] [npc.tail+] up to [npc2.namePos] [npc2.pussy+], before starting to roughly [npc2.verb(grind)] the [npc.tailTip] up and down between [npc2.namePos] folds.",

							"Roughly grinding the [npc.tailTip+] of [npc.her] [npc.tail] up and down over [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] out [npc.a_moan+] at the thought of being able to start fucking [npc2.name] whenever [npc.she] feels like it."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], [npc2.speech(Please! Fuck me! I need [npc2.name] inside of me!)]",

							" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.moan], before pleading, [npc2.speech(Go on! Please! Fuck me already!)]",

							" [npc2.Name] [npc2.moan] in delight as [npc2.name] [npc2.verb(beg)], [npc2.speech(Yes! Fuck my little pussy! I need [npc2.name] inside of me!)]"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], [npc2.speech(That's right, fuck me!)]",

							" [npc2.Name] [npc2.verb(let)] out a [npc2.moan], before pleading, [npc2.speech(Please! Fuck me already!)]",

							" [npc2.Name] [npc2.moan] out loud as [npc2.name] [npc2.verb(beg)], [npc2.speech(Come on, fuck me already!)]"));
					break;
				case SUB_RESISTING:
					if(Sex.getCharacterTargetedForSexAction(this).isVaginaVirgin()) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] at the thought of what's about to happen, [npc2.speech(No! Don't! Please! I-I'm a virgin! [npc2.name] can't do this!)]",

								" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob], before pleading, [npc2.speech(Please! Don't do this! I'm still a virgin!)]",

								" [npc2.Name] [npc2.sob] in distress at the thought of what's about to happen, before desperately begging, [npc2.speech(No! Stop! I don't want to lose my virginity!)]"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+], [npc2.speech(No! Don't! Please! Get away from me!)]",

								" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob], before pleading, [npc2.speech(Please! Don't do this! Leave me alone!)]",

								" [npc2.Name] [npc2.sob] in distress as [npc2.name] [npc2.verb(beg)], [npc2.speech(No! Stop! Get away from there!)]"));
					}
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Sex.getCharacterPerformingAction(), SexAreaPenetration.TAIL, Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA);
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
		}
	};
	
	public static final SexAction PLAYER_FORCE_TAIL_OVER_PUSSY = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Tease [npc.her] tail";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [npc.tailTip] of [npc.namePos] [npc.tail] over [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this)) ||Sex.isConsensual();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You grab [npc.namePos] [npc.tail+], before lining it up to [npc2.namePos] [npc2.pussy+],"
									+ " slowly pushing the [npc.tailTip+] up and down between [npc2.namePos] pussy lips as [npc2.name] tease [npc.herHim] with the promise of penetration at any moment.",

							"With a soft [npc2.moan], [npc2.name] grab [npc.namePos] [npc.tail+] and line it up to [npc2.namePos] [npc2.pussy+], before starting to gently slide the [npc.tailTip] up and down between [npc2.namePos] folds.",

							"Grabbing [npc.namePos] [npc.tail+], [npc2.name] gently slide the [npc.tailTip+] over [npc2.namePos] folds, letting out a soft [npc2.moan] as [npc2.name] tease [npc.herHim] with the promise of penetrating [npc2.namePos] [npc2.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You grab [npc.namePos] [npc.tail+], before lining it up to [npc2.namePos] [npc2.pussy+],"
									+ " eagerly pushing the [npc.tailTip+] up and down between [npc2.namePos] pussy lips as [npc2.name] tease [npc.herHim] with the promise of penetration at any moment.",

							"With [npc2.a_moan+], [npc2.name] grab [npc.namePos] [npc.tail+] and line it up to [npc2.namePos] [npc2.pussy+], before starting to eagerly slide the [npc.tailTip] up and down between [npc2.namePos] folds.",

							"Grabbing [npc.namePos] [npc.tail+], [npc2.name] eagerly slide the [npc.tailTip+] over [npc2.namePos] folds, letting out [npc2.a_moan+] as [npc2.name] tease [npc.herHim] with the promise of penetrating [npc2.namePos] [npc2.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You grab [npc.namePos] [npc.tail+], before grinding it up against [npc2.namePos] [npc2.pussy+],"
									+ " rubbing the [npc.tailTip+] up and down between [npc2.namePos] pussy lips as [npc2.name] tease [npc.herHim] with the promise of penetration at any moment.",

							"With [npc2.a_moan+], [npc2.name] grab [npc.namePos] [npc.tail+] and [npc2.verb(grind)] it up against [npc2.namePos] [npc2.pussy+], before starting to roughly [npc2.verb(force)] the [npc.tailTip] up and down between [npc2.namePos] folds.",

							"Grabbing [npc.namePos] [npc.tail+], [npc2.name] roughly [npc2.verb(grind)] the [npc.tailTip+] over [npc2.namePos] folds, letting out [npc2.a_moan+] as [npc2.name] tease [npc.herHim] with the promise of penetrating [npc2.namePos] [npc2.pussy+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], [npc.speech(Please! [npc2.verb(let)] me fuck you!)]",

							" [npc.Name] [npc.verb(let)] out a desperate [npc.moan], before pleading with [npc2.herHim], [npc.speech(Yes! Please! I want to fuck you!)]",

							" [npc.Name] [npc.moansVerb] in delight as [npc.she] [npc2.verb(beg)]s, [npc.speech(Yes! [npc2.verb(let)] me fuck you! Please!)]"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] drifts out from between [npc.her] [npc.lips+], [npc.speech(Yes, [npc2.verb(let)] me fuck you!)]",

							" [npc.Name] [npc.verb(let)] out a [npc.moan], before calling out, [npc.speech(Please! I want to fuck you!)]",

							" [npc.Name] [npc.moansVerb] out loud as [npc.she] speaks to [npc2.herHim], [npc.speech(Come on, [npc2.verb(let)] me fuck [npc2.name] already!)]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips+], [npc.speech(No! Don't! Please! Get away from me!)]",

							" [npc.Name] [npc.verb(let)] out a desperate [npc.sob], before pleading, [npc.speech(Please! Don't do this! Leave me alone!)]",

							" [npc.Name] [npc.sobsVerb] in distress as [npc.she] [npc2.verb(beg)]s, [npc.speech(No! Stop! Get away from there!)]"));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Sex.getCharacterPerformingAction(), SexAreaPenetration.TAIL, Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA);
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
		}
	};
	
	
	public static final SexAction PARTNER_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Tail-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Sink [npc.namePos] [npc.tail+] into [npc2.namePos] [npc2.pussy+] and [npc2.verb(start)] fucking [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly teasing the [npc.tailTip+] of [npc.her] [npc.tail] between [npc2.namePos] outer labia, [npc.name] [npc.verb(let)] out a little [npc.moan] before slowly pushing forwards, sinking [npc.her] [npc.tail+] into [npc2.namePos] [npc2.pussy+].",

							"[npc.Name] positions the [npc.tailTip+] of [npc.her] [npc.tail] between [npc2.namePos] [npc2.legs+], and with a slow, steady pressure, [npc.she] gently  [npc.verb(sink)] it deep into [npc2.namePos] [npc2.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [npc.tailTip+] of [npc.her] [npc.tail] between [npc2.namePos] outer labia, [npc.name] [npc.verb(let)] out [npc.a_moan+] before thrusting forwards, greedily sinking [npc.her] [npc.tail+] into [npc2.namePos] [npc2.pussy+].",

							"[npc.Name] positions the [npc.tailTip+] of [npc.her] [npc.tail] between [npc2.namePos] [npc2.legs+], and with a determined [npc2.verb(thrust)], [npc.she] eagerly  [npc.verb(sink)] it deep into [npc2.namePos] [npc2.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding the [npc.tailTip+] of [npc.her] [npc.tail] against [npc2.namePos] outer labia, [npc.name] [npc.verb(let)] out [npc.a_moan+] before violently slamming forwards,"
									+ " forcing [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+].",

							"[npc.Name] positions the [npc.tailTip+] of [npc.her] [npc.tail] between [npc2.namePos] [npc2.legs+], and with a forceful [npc2.verb(thrust)], [npc.she] roughly slams it deep into [npc2.namePos] [npc2.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [npc.tailTip+] of [npc.her] [npc.tail] between [npc2.namePos] outer labia, [npc.name] [npc.verb(let)] out [npc.a_moan+] before thrusting forwards,"
									+ " greedily sinking [npc.her] [npc.tail+] into [npc2.namePos] [npc2.pussy+].",

							"[npc.Name] positions the [npc.tailTip+] of [npc.her] [npc.tail] between [npc2.namePos] [npc2.legs+], and with a determined [npc2.verb(thrust)], [npc.she] eagerly  [npc.verb(sink)] it deep into [npc2.namePos] [npc2.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing the [npc.tailTip+] of [npc.her] [npc.tail] between [npc2.namePos] outer labia, [npc.name] [npc.verb(let)] out [npc.a_moan+] before thrusting forwards, sinking [npc.her] [npc.tail+] into [npc2.namePos] [npc2.pussy+].",

							"[npc.Name] positions the [npc.tailTip+] of [npc.her] [npc.tail] between [npc2.namePos] [npc2.legs+], and with a little [npc2.verb(thrust)], [npc.she]  [npc.verb(sink)] it deep into [npc2.namePos] [npc2.pussy+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc.she] enters [npc2.herHim], gently bucking [npc2.namePos] [npc2.hips] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.tail+] even deeper into [npc2.namePos] [npc2.pussy+].",

							" With a soft [npc2.moan], [npc2.name] [npc2.verb(start)] gently bucking [npc2.namePos] [npc2.hips], sinking [npc.her] [npc.tail+] even deeper into [npc2.namePos] [npc2.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], eagerly bucking [npc2.namePos] [npc2.hips] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.tail+] even deeper into [npc2.namePos] [npc2.pussy+].",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.namePos] [npc2.hips], desperately [npc2.verb(help)]ing to sink [npc.her] [npc.tail+] even deeper into [npc2.namePos] [npc2.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], violently thrusting [npc2.namePos] [npc2.hips] as [npc2.name] roughly [npc2.verb(force)] [npc.her] [npc.tail+] even deeper into [npc2.namePos] [npc2.pussy+].",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] violently bucking [npc2.namePos] [npc2.hips], roughly forcing [npc.herHim] to sink [npc.her] [npc.tail+] even deeper into [npc2.namePos] [npc2.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], eagerly bucking [npc2.namePos] [npc2.hips] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.tail+] even deeper into [npc2.namePos] [npc2.pussy+].",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.namePos] [npc2.hips], desperately [npc2.verb(help)]ing to sink [npc.her] [npc.tail+] even deeper into [npc2.namePos] [npc2.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], bucking [npc2.namePos] [npc2.hips] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.tail+] even deeper into [npc2.namePos] [npc2.pussy+].",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] bucking [npc2.namePos] [npc2.hips], [npc2.verb(help)]ing to sink [npc.her] [npc.tail+] even deeper into [npc2.namePos] [npc2.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.she] enters [npc2.herHim], and, with tears running down [npc2.namePos] [npc2.face], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to pull out as [npc2.name] weakly struggle against [npc.herHim].",

							" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)], in vain, to pull away from [npc.namePos] unwanted penetration, tears running down [npc2.namePos] [npc2.face] as [npc.her] unwelcome [npc.tail] [npc.verb(push)] deep into [npc2.namePos] [npc2.pussy+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle tail-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sinking [npc.her] [npc.tail+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] sliding it in and out, letting out a little [npc.moan] with every [npc2.verb(thrust)] as [npc.she] slowly tail-fucks [npc2.herHim].",

					"Slowly pushing [npc.her] [npc.tail+] into [npc2.namePos] [npc2.pussy+], [npc.name] softly [npc2.verb(thrust)]s it in and out, letting out a little [npc.moan] as [npc.she] gently tail-fucks [npc2.herHim].",

					"Sliding [npc.her] [npc.tail+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] out a little [npc.moan] as [npc.she] [npc.verb(start)] to gently pump it in and out, breathing in [npc2.namePos] [npc2.scent] as [npc.she] slowly tail-fucks [npc2.herHim]."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] eagerly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] back in response, letting out [npc2.a_moan+] as [npc2.name] enthusiastically [npc2.verb(help)] to sink [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, eagerly thrusting [npc2.namePos] [npc2.hips] into [npc.her] [npc.tail], [npc2.name] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] into [npc.her] [npc.tail], eagerly begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.namePos] [npc.tail], [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] weakly [npc2.verb(beg)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.pussy+].",

							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.name] weakly [npc2.verb(try)] to push [npc.name] away, tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] [npc2.verb(plead)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.pussy+].",

							" [npc2.Sobbing] in distress, and with tears running down [npc2.namePos] [npc2.face], [npc2.name] weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of [npc2.namePos] [npc2.pussy+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] back in response, letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, thrusting [npc2.namePos] [npc2.hips] into [npc.her] [npc.tail], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to carry on fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] into [npc.her] [npc.tail], begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Normal tail-fuck";
		}

		@Override
		public String getActionDescription() {
			return "[npc.verb(continue)] fucking [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking [npc.her] [npc.tail+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] sliding it in and out, letting out [npc.a_moan+] with every [npc2.verb(thrust)] as [npc.she] enthusiastically tail-fucks [npc2.herHim].",

					"Eagerly pushing [npc.her] [npc.tail+] into [npc2.namePos] [npc2.pussy+], [npc.name] greedily [npc2.verb(thrust)]s it in and out, letting out [npc.a_moan+] as [npc.she] [npc.verb(continue)] tail-fucking [npc2.herHim].",

					"Enthusiastically thrusting [npc.her] [npc.tail+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] frantically pumping it in and out,"
							+ " breathing in [npc2.namePos] [npc2.scent] as [npc.she] eagerly tail-fucks [npc2.herHim]."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] eagerly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] back in response, letting out [npc2.a_moan+] as [npc2.name] enthusiastically [npc2.verb(help)] to sink [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, eagerly thrusting [npc2.namePos] [npc2.hips] into [npc.her] [npc.tail], [npc2.name] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] into [npc.her] [npc.tail], eagerly begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.namePos] [npc.tail], [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] weakly [npc2.verb(beg)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.pussy+].",

							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.name] weakly [npc2.verb(try)] to push [npc.name] away, tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] [npc2.verb(plead)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.pussy+].",

							" [npc2.Sobbing] in distress, and with tears running down [npc2.namePos] [npc2.face], [npc2.name] weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of [npc2.namePos] [npc2.pussy+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] back in response, letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, thrusting [npc2.namePos] [npc2.hips] into [npc.her] [npc.tail], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to carry on fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] into [npc.her] [npc.tail], begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Rough tail-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}


		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly slamming [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] violently it in and out, letting out [npc.a_moan+] with every [npc2.verb(thrust)] as [npc.she] brutally tail-fucks [npc2.herHim].",

					"Violently thrusting [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] forcefully slamming it in and out, letting out [npc.a_moan+] as [npc.she] roughly tail-fucks [npc2.herHim].",

					"Ruthlessly thrusting [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] violently slamming it in and out, breathing in [npc2.namePos] [npc2.scent] as [npc.she] roughly tail-fucks [npc2.herHim]."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] eagerly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] back in response, letting out [npc2.a_moan+] as [npc2.name] enthusiastically [npc2.verb(help)] to sink [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, eagerly thrusting [npc2.namePos] [npc2.hips] into [npc.her] [npc.tail], [npc2.name] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] into [npc.her] [npc.tail], eagerly begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.namePos] [npc.tail], [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] weakly [npc2.verb(beg)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.pussy+].",

							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.name] weakly [npc2.verb(try)] to push [npc.name] away, tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] [npc2.verb(plead)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.pussy+].",

							" [npc2.Sobbing] in distress, and with tears running down [npc2.namePos] [npc2.face], [npc2.name] weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of [npc2.namePos] [npc2.pussy+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] back in response, letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, thrusting [npc2.namePos] [npc2.hips] into [npc.her] [npc.tail], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to carry on fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] into [npc.her] [npc.tail], begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Normal tail-fuck";
		}

		@Override
		public String getActionDescription() {
			return "[npc.verb(continue)] fucking [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking [npc.her] [npc.tail+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] sliding it in and out, letting out [npc.a_moan+] with every [npc2.verb(thrust)] as [npc.she] tail-fucks [npc2.herHim].",

					"Pushing [npc.her] [npc.tail+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(thrust)]s it in and out, letting out [npc.a_moan+] as [npc.she] [npc.verb(continue)] tail-fucking [npc2.herHim].",

					"thrusting [npc.her] [npc.tail+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] pumping it in and out, breathing in [npc2.namePos] [npc2.scent] as [npc.she] tail-fucks [npc2.herHim]."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] slowly buck [npc2.namePos] [npc2.hips] in response, letting out a soft [npc2.moan] as [npc2.name] [npc2.verb(start)] gently imploring [npc.name] to continue fucking [npc2.herHim].",

							" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+], and, gently pushing [npc2.namePos] [npc2.hips] out, [npc2.name] [npc2.verb(beg)] for [npc.herHim] to continue fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] slowly [npc2.verb(push)] [npc2.namePos] [npc2.hips] out, telling [npc.name] to continue as [npc2.namePos] movements cause [npc.herHim] to sink [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] violently buck [npc2.namePos] [npc2.hips] in response, letting out [npc2.a_moan+] as [npc2.name] roughly demand that [npc.name] [npc.verb(continue)] fucking [npc2.herHim].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, roughly thrusting [npc2.namePos] [npc2.hips] out, [npc2.name] order [npc.herHim] to continue fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out, ordering [npc.name] to continue as [npc2.namePos] aggressive movements cause [npc.herHim] to sink [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] buck [npc2.namePos] [npc2.hips] in response, letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] imploring [npc.name] to continue fucking [npc2.herHim].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, pushing [npc2.namePos] [npc2.hips] out, [npc2.name] [npc2.verb(beg)] for [npc.herHim] to continue fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(push)] [npc2.namePos] [npc2.hips] out, telling [npc.name] to continue as [npc2.namePos] movements cause [npc.herHim] to sink [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Eager tail-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking [npc.her] [npc.tail+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] eagerly sliding it in and out, letting out [npc.a_moan+] with every [npc2.verb(thrust)] as [npc.she] enthusiastically tail-fucks [npc2.herHim].",

					"Eagerly pushing [npc.her] [npc.tail+] into [npc2.namePos] [npc2.pussy+], [npc.name] energetically [npc2.verb(thrust)]s it in and out, letting out [npc.a_moan+] as [npc.she] [npc.verb(continue)] desperately tail-fucking [npc2.herHim].",

					"Eagerly thrusting [npc.her] [npc.tail+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] frantically pumping it in and out, breathing in [npc2.namePos] [npc2.scent] as [npc.she] tail-fucks [npc2.herHim]."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] slowly buck [npc2.namePos] [npc2.hips] in response, letting out a soft [npc2.moan] as [npc2.name] [npc2.verb(start)] gently imploring [npc.name] to continue fucking [npc2.herHim].",

							" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+], and, gently pushing [npc2.namePos] [npc2.hips] out, [npc2.name] [npc2.verb(beg)] for [npc.herHim] to continue fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] slowly [npc2.verb(push)] [npc2.namePos] [npc2.hips] out, telling [npc.name] to continue as [npc2.namePos] movements cause [npc.herHim] to sink [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] violently buck [npc2.namePos] [npc2.hips] in response, letting out [npc2.a_moan+] as [npc2.name] roughly demand that [npc.name] [npc.verb(continue)] fucking [npc2.herHim].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, roughly thrusting [npc2.namePos] [npc2.hips] out, [npc2.name] order [npc.herHim] to continue fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out, ordering [npc.name] to continue as [npc2.namePos] aggressive movements cause [npc.herHim] to sink [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] buck [npc2.namePos] [npc2.hips] in response, letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] imploring [npc.name] to continue fucking [npc2.herHim].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, pushing [npc2.namePos] [npc2.hips] out, [npc2.name] [npc2.verb(beg)] for [npc.herHim] to continue fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(push)] [npc2.namePos] [npc2.hips] out, telling [npc.name] to continue as [npc2.namePos] movements cause [npc.herHim] to sink [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Resist tail-sex";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull [npc.namePos] [npc.tail] away from [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					" Desperately trying, and failing, to pull [npc.her] [npc.tail] free from [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] out [npc.a_sob+], pushing against [npc2.name] as [npc.she] weakly [npc2.verb(beg)]s to be released.",

					" [npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.name] away, pleading for [npc2.name] to get [npc2.namePos] [npc2.pussy+] away from [npc.her] [npc.tail].",

					" [npc.Sobbing] in distress, [npc.name] weakly struggles against [npc2.herHim], pleading for [npc2.name] to let go of [npc.her] [npc.tail]."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, [npc2.name] slowly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out, letting out a soft [npc2.moan] as [npc2.name] [npc.verb(continue)] gently fucking [npc2.herself] on [npc.her] [npc.tail+].",

							" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+], and, totally ignoring [npc.namePos] protests, [npc2.name] gently [npc2.verb(push)] [npc2.namePos] [npc2.hips] out, before continuing to fuck [npc2.herself] on [npc.her] [npc.tail+].",

							" [npc2.Moaning] in delight, [npc2.name] totally ignore [npc.namePos] protests, slowly grinding [npc2.namePos] [npc2.hips] out and softly [npc2.moaning] as [npc2.name] [npc.verb(sink)] [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, [npc2.name] roughly slam [npc2.namePos] [npc2.hips] out, letting out [npc2.a_moan+] as [npc2.name] [npc.verb(continue)] violently fucking [npc2.herself] on [npc.her] [npc.tail+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, totally ignoring [npc.namePos] protests, [npc2.name] forcefully [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out, before continuing to roughly fuck [npc2.herself] on [npc.her] [npc.tail+].",

							" [npc2.Moaning] in delight, [npc2.name] totally ignore [npc.namePos] protests, roughly grinding [npc2.namePos] [npc2.hips] out and [npc2.moaning+] out loud as [npc2.name] violently [npc2.verb(force)] [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, [npc2.name] eagerly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out, letting out [npc2.a_moan+] as [npc2.name] [npc.verb(continue)] happily fucking [npc2.herself] on [npc.her] [npc.tail+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, totally ignoring [npc.namePos] protests, [npc2.name] eagerly [npc2.verb(push)] [npc2.namePos] [npc2.hips] out, before continuing to energetically fuck [npc2.herself] on [npc.her] [npc.tail+].",

							" [npc2.Moaning] in delight, [npc2.name] totally ignore [npc.namePos] protests, eagerly grinding [npc2.namePos] [npc2.hips] out and [npc2.moaning+] out loud as [npc2.name] [npc2.verb(force)] [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this)) ||Sex.isConsensual(); // Partner can only stop if they're in charge (otherwise, this is the player fucking themselves on the partner's cock).
		}
		
		@Override
		public String getActionTitle() {
			return "Stop tail-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Pull [npc.namePos] [npc.tail+] out of [npc2.namePos] [npc2.pussy+] and stop fucking [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.tail+] out of [npc2.namePos] [npc2.pussy+], [npc.name] dominantly slides the [npc.tailTip] up and down between [npc2.namePos] folds one last time before pulling back.",

							"thrusting deep inside of [npc2.name] one last time, [npc.name] then yanks [npc.her] [npc.tail+] back out of [npc2.namePos] [npc2.pussy+], putting an end to the rough tail-fucking."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.tail] out of [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(rub)] the [npc.tailTip] up and down between [npc2.namePos] folds one last time before pulling back.",

							"Pushing deep inside of [npc2.name] one last time, [npc.name] then slides [npc.her] [npc.tail+] back out of [npc2.namePos] [npc2.pussy+], putting an end to [npc2.namePos] tail-fucking."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] can't [npc2.verb(help)] but [npc2.verb(let)] out [npc2.sob+] as [npc.name] [npc.verb(pull)] out of [npc2.namePos] [npc2.pussy], and [npc2.name] [npc.verb(continue)] crying and protesting as [npc2.name] carry on weakly struggling against [npc.herHim].",

							" With [npc2.a_sob+], [npc2.name] [npc.verb(continue)] to struggle against [npc.name], tears streaming down [npc2.namePos] [npc2.face] as [npc.she] withdraws from [npc2.namePos] [npc2.pussy+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] [npc.her] [npc.tail+] out of [npc2.namePos] [npc2.pussy+], eager for more of [npc.her] attention.",

							" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.namePos] lust for [npc.her] [npc.tail+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Player actions:
	
	public static final SexAction PLAYER_USING_TAIL_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.isConsensual() || Sex.isDom(Sex.getCharacterTargetedForSexAction(this)));
		}
		
		@Override
		public String getActionTitle() {
			return "Get tail-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Start fucking [npc2.herself] on [npc.namePos] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing [npc.namePos] [npc.tail], [npc2.name] slowly slide the [npc.tailTip] over [npc2.namePos] outer labia, letting out a little [npc2.moan] before gently bucking [npc2.namePos] [npc2.hips] forwards and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.pussy+].",

							"Grabbing [npc.namePos] [npc.tail], [npc2.name] line it up to [npc2.namePos] [npc2.pussy+] before slowly pushing [npc2.namePos] [npc2.hips] forwards, letting out a soft [npc2.moan] as [npc2.name] penetrate [npc2.herself] on [npc.her] [npc.tail+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing [npc.namePos] [npc.tail], [npc2.name] eagerly slide the [npc.tailTip+] over [npc2.namePos] outer labia, letting out [npc2.a_moan+] before desperately bucking [npc2.namePos] [npc2.hips] forwards and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.pussy+].",

							"Grabbing [npc.namePos] [npc.tail], [npc2.name] line it up to [npc2.namePos] [npc2.pussy+] before eagerly pushing [npc2.namePos] [npc2.hips] forwards, letting out [npc2.a_moan+] as [npc2.name] penetrate [npc2.herself] on [npc.her] [npc.tail+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing [npc.namePos] [npc.tail], [npc2.name] eagerly slide the [npc.tailTip+] over [npc2.namePos] outer labia, letting out [npc2.a_moan+] before roughly slamming [npc2.namePos] [npc2.hips] forwards and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.pussy+].",

							"Grabbing [npc.namePos] [npc.tail], [npc2.name] line it up to [npc2.namePos] [npc2.pussy+] before violently slamming [npc2.namePos] [npc2.hips] forwards, letting out [npc2.a_moan+] as [npc2.name] roughly [npc2.verb(start)] fucking [npc2.herself] on [npc.her] [npc.tail+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing [npc.namePos] [npc.tail], [npc2.name] eagerly slide the [npc.tailTip+] over [npc2.namePos] outer labia, letting out [npc2.a_moan+] before desperately bucking [npc2.namePos] [npc2.hips] forwards and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.pussy+].",

							"Grabbing [npc.namePos] [npc.tail], [npc2.name] line it up to [npc2.namePos] [npc2.pussy+] before eagerly pushing [npc2.namePos] [npc2.hips] forwards, letting out [npc2.a_moan+] as [npc2.name] penetrate [npc2.herself] on [npc.her] [npc.tail+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing [npc.namePos] [npc.tail], [npc2.name] slide the [npc.tailTip+] over [npc2.namePos] outer labia, letting out [npc2.a_moan+] before bucking [npc2.namePos] [npc2.hips] forwards and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.pussy+].",

							"Grabbing [npc.namePos] [npc.tail], [npc2.name] line it up to [npc2.namePos] [npc2.pussy+] before pushing [npc2.namePos] [npc2.hips] forwards, letting out [npc2.a_moan+] as [npc2.name] penetrate [npc2.herself] on [npc.her] [npc.tail+]."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] enters [npc2.herHim], gently pushing [npc.her] [npc.tail] forwards as [npc.she] [npc.verb(start)] to tail-fuck [npc2.namePos] [npc2.pussy+].",

							" With a soft [npc.moan], [npc.name] gently [npc2.verb(thrust)]s [npc.her] [npc.tail] forwards, sinking it deep into [npc2.namePos] [npc2.pussy+] as [npc.she] [npc.verb(start)] tail-fucking [npc2.herHim]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] enters [npc2.herHim], eagerly pushing [npc.her] [npc.tail] forwards as [npc.she] [npc.verb(start)] enthusiastically tail-fucking [npc2.namePos] [npc2.pussy+].",

							" With [npc.a_moan+], [npc.name] eagerly [npc2.verb(thrust)]s [npc.her] [npc.tail] forwards, sinking it deep into [npc2.namePos] [npc2.pussy+] as [npc.she] [npc.verb(start)] energetically tail-fucking [npc2.herHim]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] enters [npc2.herHim], and, seeking to remind [npc2.name] who's in charge, [npc.she] roughly slams [npc.her] [npc.tail] forwards as [npc.she] [npc.verb(start)] to ruthlessly tail-fuck [npc2.namePos] [npc2.pussy+].",

							" With [npc.a_moan+], [npc.name] roughly slams [npc.her] [npc.tail] forwards, seeking to remind [npc2.name] who's in charge as [npc.she] [npc.verb(start)] ruthlessly tail-fucking [npc2.namePos] [npc2.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] enters [npc2.herHim], eagerly pushing [npc.her] [npc.tail] forwards as [npc.she] [npc.verb(start)] enthusiastically tail-fucking [npc2.namePos] [npc2.pussy+].",

							" With [npc.a_moan+], [npc.name] eagerly [npc2.verb(thrust)]s [npc.her] [npc.tail] forwards, sinking it deep into [npc2.namePos] [npc2.pussy+] as [npc.she] [npc.verb(start)] energetically tail-fucking [npc2.herHim]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] enters [npc2.herHim], pushing [npc.her] [npc.tail] forwards as [npc.she] [npc.verb(start)] tail-fucking [npc2.namePos] [npc2.pussy+].",

							" With [npc.a_moan+], [npc.name] [npc.verb(thrust)]s [npc.her] [npc.tail] forwards, sinking it deep into [npc2.namePos] [npc2.pussy+] as [npc.she] [npc.verb(start)] tail-fucking [npc2.herHim]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_sob+] as [npc2.name] [npc2.verb(force)] [npc.her] [npc.tail] inside of [npc2.herHim], and, struggling against [npc2.herHim], [npc.she] desperately tries to pull [npc.her] [npc.tail+] free from [npc2.namePos] [npc2.pussy+].",

							" With [npc.a_sob+], [npc.name] struggles against [npc2.name] as [npc2.name] [npc2.verb(force)] [npc.her] [npc.tail] deep into [npc2.namePos] [npc2.pussy+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_TAIL_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle tail-ride";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc2.herself] on [npc.namePos] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently pushing [npc2.namePos] [npc2.hips] out, [npc2.name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+].",

					"With a soft [npc2.moan], [npc2.name] gently [npc2.verb(start)] gyrating [npc2.namePos] [npc2.hips], forcing [npc.namePos] [npc.tail+] ever deeper into [npc2.namePos] [npc2.pussy+].",

					"Slowly thrusting [npc2.namePos] [npc2.hips], a soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] movements [npc2.verb(force)] [npc.namePos] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+]."));

			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_RIDING_TAIL_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Normal tail-ride";
		}

		@Override
		public String getActionDescription() {
			return "Fuck [npc2.herself] on [npc.namePos] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc2.namePos] [npc2.hips] out, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] energetically [npc2.verb(help)] to sink [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+].",

					"With [npc2.a_moan+], [npc2.name] energetically [npc2.verb(start)] gyrating [npc2.namePos] [npc2.hips], forcing [npc.namePos] [npc.tail+] ever deeper into [npc2.namePos] [npc2.pussy+].",

					"Enthusiastically thrusting [npc2.namePos] [npc2.hips], [npc2.a_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] movements [npc2.verb(force)] [npc.namePos] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_RIDING_TAIL_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Rough tail-ride";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc2.herself] on [npc.namePos] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Violently slamming [npc2.namePos] [npc2.hips] out, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] roughly [npc2.verb(force)] [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+].",

					"With [npc2.a_moan+], [npc2.name] aggressively [npc2.verb(start)] gyrating [npc2.namePos] [npc2.hips] against [npc.name], forcing [npc.her] [npc.tail+] ever deeper into [npc2.namePos] [npc2.pussy+].",

					"Roughly thrusting [npc2.namePos] [npc2.hips], [npc2.a_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] forceful movements drive [npc.namePos] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_RIDING_TAIL_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Get tail-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Buck [npc2.namePos] hips to help [npc2.verb(thrust)] [npc.namePos] [npc.tail] deep into [npc2.namePos] [npc2.pussy].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"With a [npc2.verb(thrust)] of [npc2.namePos] [npc2.hips], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(help)] to sink [npc.namePos] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+].",

					"With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] gyrating [npc2.namePos] [npc2.hips], forcing [npc.namePos] [npc.tail+] ever deeper into [npc2.namePos] [npc2.pussy+].",

					"thrusting [npc2.namePos] [npc2.hips], [npc2.a_moan+] drifts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] movements [npc2.verb(force)] [npc.namePos] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_RIDING_TAIL_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Eagerly tail-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly buck [npc2.namePos] hips to help [npc2.verb(thrust)] [npc.namePos] [npc.tail] deep into [npc2.namePos] [npc2.pussy].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc2.namePos] [npc2.hips] out, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] energetically [npc2.verb(help)] to sink [npc.her] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+].",

					"With [npc2.a_moan+], [npc2.name] energetically [npc2.verb(start)] gyrating [npc2.namePos] [npc2.hips], forcing [npc.namePos] [npc.tail+] ever deeper into [npc2.namePos] [npc2.pussy+].",

					"Enthusiastically thrusting [npc2.namePos] [npc2.hips], [npc2.a_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] movements [npc2.verb(force)] [npc.namePos] [npc.tail+] deep into [npc2.namePos] [npc2.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Resist tail-fucking";
		}

		@Override
		public String getActionDescription() {
			return "Try and [npc2.verb(pull)] [npc2.namePos] [npc2.pussy+] away from [npc.namePos] [npc.tail+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You [npc2.verb(feel)] tears [npc2.verb(start)] to well up in [npc2.namePos] [npc2.eyes], and, not being able to hold back any longer, [npc2.name] suddenly [npc2.verb(let)] out [npc2.a_sob+],"
									+ " weakly trying to pull [npc.namePos] [npc.tail] out of [npc2.namePos] [npc2.pussy+] as [npc.she] [npc.verb(continue)] gently fucking [npc2.herHim].",

							"[npc2.A_sob+] bursts out from [npc2.namePos] mouth as [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.hips] back from [npc.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.tail+] [npc.verb(continue)] slowly sliding in and out of [npc2.namePos] [npc2.pussy+].",

							"Trying desperately to pull [npc2.namePos] [npc2.hips] away from [npc.name], [npc2.name] [npc2.sob] in distress as [npc.her] [npc.tail+] [npc.verb(continue)] gently sliding deep into [npc2.namePos] [npc2.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You [npc2.verb(feel)] tears [npc2.verb(start)] to well up in [npc2.namePos] [npc2.eyes], and, not being able to hold back any longer, [npc2.name] suddenly [npc2.verb(let)] out [npc2.a_sob+],"
									+ " weakly trying to pull [npc.namePos] [npc.tail] out of [npc2.namePos] [npc2.pussy+] as [npc.she] [npc.verb(continue)] eagerly fucking [npc2.herHim].",

							"[npc2.A_sob+] bursts out from [npc2.namePos] mouth as [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.hips] back from [npc.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.tail+] [npc.verb(continue)] eagerly sliding in and out of [npc2.namePos] [npc2.pussy+].",

							"Trying desperately to pull [npc2.namePos] [npc2.hips] away from [npc.name], [npc2.name] [npc2.sob] in distress as [npc.her] [npc.tail+] [npc.verb(continue)] eagerly sliding deep into [npc2.namePos] [npc2.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You [npc2.verb(feel)] tears [npc2.verb(start)] to well up in [npc2.namePos] [npc2.eyes], and, not being able to hold back any longer, [npc2.name] suddenly [npc2.verb(let)] out [npc2.a_sob+],"
									+ " weakly trying to pull [npc.namePos] [npc.tail] out of [npc2.namePos] [npc2.pussy+] as [npc.she] [npc.verb(continue)] roughly fucking [npc2.herHim].",

							"[npc2.A_sob+] bursts out from [npc2.namePos] mouth as [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.hips] back from [npc.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.tail+] [npc.verb(continue)] roughly slamming in and out of [npc2.namePos] [npc2.pussy+].",

							"Trying desperately to pull [npc2.namePos] [npc2.hips] away from [npc.name], [npc2.name] [npc2.sob] in distress as [npc.her] [npc.tail+] [npc.verb(continue)] roughly slamming deep into [npc2.namePos] [npc2.pussy+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || Sex.isDom(Sex.getCharacterTargetedForSexAction(this)); // Player can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop tail-fucking";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to pull [npc.her] [npc.tail] out of [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc.namePos] [npc.tail] out of [npc2.namePos] [npc2.pussy+], [npc2.name] [npc2.verb(let)] out a menacing growl as [npc2.name] [npc.verb(command)] [npc.herHim] to stop fucking [npc2.herHim].",

							"You lean into [npc.name], inhaling [npc.her] [npc.scent] before yanking [npc.her] [npc.tail] out of [npc2.namePos] [npc2.pussy+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.namePos] [npc.tail] out of [npc2.namePos] [npc2.pussy+], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] tell [npc.herHim] to stop fucking [npc2.herHim].",

							"You lean into [npc.name], inhaling [npc.her] [npc.scent] before sliding [npc.her] [npc.tail] out of [npc2.namePos] [npc2.pussy+]."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out a relieved sigh, which soon turns into [npc.a_sob+] as [npc.she] realises that [npc2.name] haven't finished with [npc.herHim] just yet.",

							" With [npc.a_sob+], [npc.name] [npc.verb(continue)] to protest and struggle against [npc2.name] as [npc2.name] hold [npc.herHim] firmly in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] stop [npc.herHim] from fucking [npc2.namePos] [npc2.pussy+].",

							" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] desire to continue fucking [npc2.namePos] [npc2.pussy+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_PUSSY_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Pussy control";
		}

		@Override
		public String getActionDescription() {
			return "Squeeze [npc2.namePos] demonic pussy down around [npc.namePos] [npc.tail].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getCharacterTargetedForSexAction(this).getVaginaType()==VaginaType.DEMON_COMMON;
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Letting out [npc2.a_moan+], [npc2.name] concentrate on squeezing the tentacles and demonic muscles within [npc2.namePos] [npc2.pussy] down around [npc.namePos] [npc.tail+].",

					"You [npc2.verb(let)] out a cheeky giggle as [npc2.name] [npc.verb(focus)] on controlling the tentacles lining [npc2.namePos] [npc2.pussy]."
							+ " Wriggling and squeezing them down around [npc.namePos] [npc.tail+], [npc2.name] cause [npc.herHim] to let out an involuntary cry of pleasure.",

					"You find [npc2.namePos] [npc2.moans] falling into a steady rhythm as [npc2.name] concentrate on squeezing the tentacles and extra muscles within [npc2.namePos] [npc2.pussy+] down around [npc.namePos] [npc.tail+].",

					"With [npc2.a_moan+], [npc2.name] [npc.verb(focus)] on controlling the demonic tentacles deep within [npc2.namePos] [npc2.pussy], gripping them down and massaging [npc.namePos] [npc.tail+] as [npc2.name] squeal in pleasure.");
		}
	};
	
}
