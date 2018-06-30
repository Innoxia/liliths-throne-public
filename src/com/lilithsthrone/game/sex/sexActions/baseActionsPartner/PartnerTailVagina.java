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
import com.lilithsthrone.main.Main;
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
			return "Slide your [npc.tail] over [pc.name]'s [pc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer()) ||Sex.isConsensual();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Snaking [npc.her] [npc.tail+] up to your [pc.pussy+], [npc.name] starts slowly teasing the [npc.tailTip+] up and down between your pussy lips, ready to penetrate you at any moment.",
							"With a soft [npc.moan], [npc.name] snakes [npc.her] [npc.tail+] up to your [pc.pussy+], before starting to gently slide the [npc.tailTip] up and down between your folds.",
							"Gently sliding the [npc.tailTip+] of [npc.her] [npc.tail] up and down over your [pc.pussy+], [npc.name] lets out a soft [npc.moan] at the thought of being able to penetrate you whenever [npc.she] feels like it."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Snaking [npc.her] [npc.tail+] up to your [pc.pussy+], [npc.name] starts eagerly sliding the [npc.tailTip+] up and down between your pussy lips, ready to penetrate you at any moment.",
							"With [npc.a_moan+], [npc.name] snakes [npc.her] [npc.tail+] up to your [pc.pussy+], before starting to eagerly slide the [npc.tailTip] up and down between your folds.",
							"Eagerly sliding the [npc.tailTip+] of [npc.her] [npc.tail] up and down over your [pc.pussy+], [npc.name] lets out [npc.a_moan+] at the thought of being able to penetrate you whenever [npc.she] feels like it."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding [npc.her] [npc.tail+] up against your [pc.pussy+], [npc.name] pulls back a little before starting to slide the [npc.tailTip+] up and down between your pussy lips,"
									+ " ready to start fucking you at any moment.",
							"With [npc.a_moan+], [npc.name] lines [npc.her] [npc.tail+] up to your [pc.pussy+], before starting to roughly grind the [npc.tailTip] up and down between your folds.",
							"Roughly grinding the [npc.tailTip+] of [npc.her] [npc.tail] up and down over your [pc.pussy+], [npc.name] lets out [npc.a_moan+] at the thought of being able to start fucking you whenever [npc.she] feels like it."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] bursts out from between your [pc.lips+], [pc.speech(Please! Fuck me! I need you inside of me!)]",
							" You let out a desperate [pc.moan], before pleading, [pc.speech(Go on! Please! Fuck me already!)]",
							" You [pc.moan] in delight as you beg, [pc.speech(Yes! Fuck my little pussy! I need you inside of me!)]"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] drifts out from between your [pc.lips+], [pc.speech(That's right, fuck me!)]",
							" You let out a [pc.moan], before pleading, [pc.speech(Please! Fuck me already!)]",
							" You [pc.moan] out loud as you beg, [pc.speech(Come on, fuck me already!)]"));
					break;
				case SUB_RESISTING:
					if(Main.game.getPlayer().isVaginaVirgin()) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [pc.A_sob+] bursts out from between your [pc.lips+] at the thought of what's about to happen, [pc.speech(No! Don't! Please! I-I'm a virgin! You can't do this!)]",
								" You let out a desperate [pc.sob], before pleading, [pc.speech(Please! Don't do this! I'm still a virgin!)]",
								" You [pc.sob] in distress at the thought of what's about to happen, before desperately begging, [pc.speech(No! Stop! I don't want to lose my virginity!)]"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [pc.A_sob+] bursts out from between your [pc.lips+], [pc.speech(No! Don't! Please! Get away from me!)]",
								" You let out a desperate [pc.sob], before pleading, [pc.speech(Please! Don't do this! Leave me alone!)]",
								" You [pc.sob] in distress as you beg, [pc.speech(No! Stop! Get away from there!)]"));
					}
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Sex.getActivePartner(), SexAreaPenetration.TAIL, Main.game.getPlayer(), SexAreaOrifice.VAGINA);
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
			return "Slide the [npc.tailTip] of [npc.name]'s [npc.tail] over your [pc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer()) ||Sex.isConsensual();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You grab [npc.name]'s [npc.tail+], before lining it up to your [pc.pussy+],"
									+ " slowly pushing the [npc.tailTip+] up and down between your pussy lips as you tease [npc.herHim] with the promise of penetration at any moment.",
							"With a soft [pc.moan], you grab [npc.name]'s [npc.tail+] and line it up to your [pc.pussy+], before starting to gently slide the [npc.tailTip] up and down between your folds.",
							"Grabbing [npc.name]'s [npc.tail+], you gently slide the [npc.tailTip+] over your folds, letting out a soft [pc.moan] as you tease [npc.herHim] with the promise of penetrating your [pc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You grab [npc.name]'s [npc.tail+], before lining it up to your [pc.pussy+],"
									+ " eagerly pushing the [npc.tailTip+] up and down between your pussy lips as you tease [npc.herHim] with the promise of penetration at any moment.",
							"With [pc.a_moan+], you grab [npc.name]'s [npc.tail+] and line it up to your [pc.pussy+], before starting to eagerly slide the [npc.tailTip] up and down between your folds.",
							"Grabbing [npc.name]'s [npc.tail+], you eagerly slide the [npc.tailTip+] over your folds, letting out [pc.a_moan+] as you tease [npc.herHim] with the promise of penetrating your [pc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You grab [npc.name]'s [npc.tail+], before grinding it up against your [pc.pussy+],"
									+ " rubbing the [npc.tailTip+] up and down between your pussy lips as you tease [npc.herHim] with the promise of penetration at any moment.",
							"With [pc.a_moan+], you grab [npc.name]'s [npc.tail+] and grind it up against your [pc.pussy+], before starting to roughly force the [npc.tailTip] up and down between your folds.",
							"Grabbing [npc.name]'s [npc.tail+], you roughly grind the [npc.tailTip+] over your folds, letting out [pc.a_moan+] as you tease [npc.herHim] with the promise of penetrating your [pc.pussy+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], [npc.speech(Please! Let me fuck you!)]",
							" [npc.Name] lets out a desperate [npc.moan], before pleading with you, [npc.speech(Yes! Please! I want to fuck you!)]",
							" [npc.Name] [npc.moansVerb] in delight as [npc.she] begs, [npc.speech(Yes! Let me fuck you! Please!)]"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] drifts out from between [npc.her] [npc.lips+], [npc.speech(Yes, let me fuck you!)]",
							" [npc.Name] lets out a [npc.moan], before calling out, [npc.speech(Please! I want to fuck you!)]",
							" [npc.Name] [npc.moansVerb] out loud as [npc.she] speaks to you, [npc.speech(Come on, let me fuck you already!)]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips+], [npc.speech(No! Don't! Please! Get away from me!)]",
							" [npc.Name] lets out a desperate [npc.sob], before pleading, [npc.speech(Please! Don't do this! Leave me alone!)]",
							" [npc.Name] [npc.sobsVerb] in distress as [npc.she] begs, [npc.speech(No! Stop! Get away from there!)]"));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Sex.getActivePartner(), SexAreaPenetration.TAIL, Main.game.getPlayer(), SexAreaOrifice.VAGINA);
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
			return "Sink your [npc.tail+] into [pc.name]'s [pc.pussy+] and start fucking [pc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly teasing the [npc.tailTip+] of [npc.her] [npc.tail] between your outer labia, [npc.name] lets out a little [npc.moan] before slowly pushing forwards, sinking [npc.her] [npc.tail+] into your [pc.pussy+].",
							"[npc.Name] positions the [npc.tailTip+] of [npc.her] [npc.tail] between your [pc.legs+], and with a slow, steady pressure, [npc.she] gently sinks it deep into your [pc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [npc.tailTip+] of [npc.her] [npc.tail] between your outer labia, [npc.name] lets out [npc.a_moan+] before thrusting forwards, greedily sinking [npc.her] [npc.tail+] into your [pc.pussy+].",
							"[npc.Name] positions the [npc.tailTip+] of [npc.her] [npc.tail] between your [pc.legs+], and with a determined thrust, [npc.she] eagerly sinks it deep into your [pc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding the [npc.tailTip+] of [npc.her] [npc.tail] against your outer labia, [npc.name] lets out [npc.a_moan+] before violently slamming forwards,"
									+ " forcing [npc.her] [npc.tail+] deep into your [pc.pussy+].",
							"[npc.Name] positions the [npc.tailTip+] of [npc.her] [npc.tail] between your [pc.legs+], and with a forceful thrust, [npc.she] roughly slams it deep into your [pc.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [npc.tailTip+] of [npc.her] [npc.tail] between your outer labia, [npc.name] lets out [npc.a_moan+] before thrusting forwards,"
									+ " greedily sinking [npc.her] [npc.tail+] into your [pc.pussy+].",
							"[npc.Name] positions the [npc.tailTip+] of [npc.her] [npc.tail] between your [pc.legs+], and with a determined thrust, [npc.she] eagerly sinks it deep into your [pc.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing the [npc.tailTip+] of [npc.her] [npc.tail] between your outer labia, [npc.name] lets out [npc.a_moan+] before thrusting forwards, sinking [npc.her] [npc.tail+] into your [pc.pussy+].",
							"[npc.Name] positions the [npc.tailTip+] of [npc.her] [npc.tail] between your [pc.legs+], and with a little thrust, [npc.she] sinks it deep into your [pc.pussy+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soft [pc.moan] as [npc.she] enters you, gently bucking your [pc.hips] as you help to sink [npc.her] [npc.tail+] even deeper into your [pc.pussy+].",
							" With a soft [pc.moan], you start gently bucking your [pc.hips], sinking [npc.her] [npc.tail+] even deeper into your [pc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.she] enters you, eagerly bucking your [pc.hips] as you help to sink [npc.her] [npc.tail+] even deeper into your [pc.pussy+].",
							" With [pc.a_moan+], you start eagerly bucking your [pc.hips], desperately helping to sink [npc.her] [npc.tail+] even deeper into your [pc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.she] enters you, violently thrusting your [pc.hips] as you roughly force [npc.her] [npc.tail+] even deeper into your [pc.pussy+].",
							" With [pc.a_moan+], you start violently bucking your [pc.hips], roughly forcing [npc.herHim] to sink [npc.her] [npc.tail+] even deeper into your [pc.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.she] enters you, eagerly bucking your [pc.hips] as you help to sink [npc.her] [npc.tail+] even deeper into your [pc.pussy+].",
							" With [pc.a_moan+], you start eagerly bucking your [pc.hips], desperately helping to sink [npc.her] [npc.tail+] even deeper into your [pc.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.she] enters you, bucking your [pc.hips] as you help to sink [npc.her] [npc.tail+] even deeper into your [pc.pussy+].",
							" With [pc.a_moan+], you start bucking your [pc.hips], helping to sink [npc.her] [npc.tail+] even deeper into your [pc.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_sob+] as [npc.she] enters you, and, with tears running down your [pc.face], you beg for [npc.herHim] to pull out as you weakly struggle against [npc.herHim].",
							" With [pc.a_sob+], you try, in vain, to pull away from [npc.name]'s unwanted penetration, tears running down your [pc.face] as [npc.her] unwelcome [npc.tail] pushes deep into your [pc.pussy+]."));
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
			return "Gently fuck [pc.name]'s [pc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sinking [npc.her] [npc.tail+] into your [pc.pussy+], [npc.name] starts sliding it in and out, letting out a little [npc.moan] with every thrust as [npc.she] slowly tail-fucks you.",
					"Slowly pushing [npc.her] [npc.tail+] into your [pc.pussy+], [npc.name] softly thrusts it in and out, letting out a little [npc.moan] as [npc.she] gently tail-fucks you.",
					"Sliding [npc.her] [npc.tail+] into your [pc.pussy+], [npc.name] lets out a little [npc.moan] as [npc.she] starts to gently pump it in and out, breathing in your [pc.scent] as [npc.she] slowly tail-fucks you."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You eagerly thrust your [pc.hips] back in response, letting out [pc.a_moan+] as you enthusiastically help to sink [npc.her] [npc.tail+] deep into your [pc.pussy+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, eagerly thrusting your [pc.hips] into [npc.her] [npc.tail], you beg for [npc.name] to carry on fucking you.",
							" [pc.Moaning] in delight, you eagerly grind your [pc.hips+] into [npc.her] [npc.tail], eagerly begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.tail+] deep into your [pc.pussy+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.name]'s [npc.tail], you let out [pc.a_sob+], tears streaming down your [pc.face] as you weakly beg for [npc.herHim] to pull out of your [pc.pussy+].",
							" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, tears streaming down your [pc.face] as you plead for [npc.herHim] to pull out of your [pc.pussy+].",
							" [pc.Sobbing] in distress, and with tears running down your [pc.face], you weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of your [pc.pussy+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You thrust your [pc.hips] back in response, letting out [pc.a_moan+] as you help to sink [npc.her] [npc.tail+] deep into your [pc.pussy+].",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, thrusting your [pc.hips] into [npc.her] [npc.tail], you beg for [npc.herHim] to carry on fucking you.",
							" [pc.Moaning] in delight, you grind your [pc.hips+] into [npc.her] [npc.tail], begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.tail+] deep into your [pc.pussy+]"));
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
			return "Continue fucking [pc.name]'s [pc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking [npc.her] [npc.tail+] into your [pc.pussy+], [npc.name] starts sliding it in and out, letting out [npc.a_moan+] with every thrust as [npc.she] enthusiastically tail-fucks you.",
					"Eagerly pushing [npc.her] [npc.tail+] into your [pc.pussy+], [npc.name] greedily thrusts it in and out, letting out [npc.a_moan+] as [npc.she] continues tail-fucking you.",
					"Enthusiastically thrusting [npc.her] [npc.tail+] into your [pc.pussy+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts frantically pumping it in and out,"
							+ " breathing in your [pc.scent] as [npc.she] eagerly tail-fucks you."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You eagerly thrust your [pc.hips] back in response, letting out [pc.a_moan+] as you enthusiastically help to sink [npc.her] [npc.tail+] deep into your [pc.pussy+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, eagerly thrusting your [pc.hips] into [npc.her] [npc.tail], you beg for [npc.name] to carry on fucking you.",
							" [pc.Moaning] in delight, you eagerly grind your [pc.hips+] into [npc.her] [npc.tail], eagerly begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.tail+] deep into your [pc.pussy+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.name]'s [npc.tail], you let out [pc.a_sob+], tears streaming down your [pc.face] as you weakly beg for [npc.herHim] to pull out of your [pc.pussy+].",
							" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, tears streaming down your [pc.face] as you plead for [npc.herHim] to pull out of your [pc.pussy+].",
							" [pc.Sobbing] in distress, and with tears running down your [pc.face], you weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of your [pc.pussy+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You thrust your [pc.hips] back in response, letting out [pc.a_moan+] as you help to sink [npc.her] [npc.tail+] deep into your [pc.pussy+].",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, thrusting your [pc.hips] into [npc.her] [npc.tail], you beg for [npc.herHim] to carry on fucking you.",
							" [pc.Moaning] in delight, you grind your [pc.hips+] into [npc.her] [npc.tail], begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.tail+] deep into your [pc.pussy+]"));
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
			return "Roughly fuck [pc.name]'s [pc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}


		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly slamming [npc.her] [npc.tail+] deep into your [pc.pussy+], [npc.name] starts violently it in and out, letting out [npc.a_moan+] with every thrust as [npc.she] brutally tail-fucks you.",
					"Violently thrusting [npc.her] [npc.tail+] deep into your [pc.pussy+], [npc.name] starts forcefully slamming it in and out, letting out [npc.a_moan+] as [npc.she] roughly tail-fucks you.",
					"Ruthlessly thrusting [npc.her] [npc.tail+] deep into your [pc.pussy+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts violently slamming it in and out, breathing in your [pc.scent] as [npc.she] roughly tail-fucks you."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You eagerly thrust your [pc.hips] back in response, letting out [pc.a_moan+] as you enthusiastically help to sink [npc.her] [npc.tail+] deep into your [pc.pussy+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, eagerly thrusting your [pc.hips] into [npc.her] [npc.tail], you beg for [npc.name] to carry on fucking you.",
							" [pc.Moaning] in delight, you eagerly grind your [pc.hips+] into [npc.her] [npc.tail], eagerly begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.tail+] deep into your [pc.pussy+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.name]'s [npc.tail], you let out [pc.a_sob+], tears streaming down your [pc.face] as you weakly beg for [npc.herHim] to pull out of your [pc.pussy+].",
							" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, tears streaming down your [pc.face] as you plead for [npc.herHim] to pull out of your [pc.pussy+].",
							" [pc.Sobbing] in distress, and with tears running down your [pc.face], you weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of your [pc.pussy+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You thrust your [pc.hips] back in response, letting out [pc.a_moan+] as you help to sink [npc.her] [npc.tail+] deep into your [pc.pussy+].",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, thrusting your [pc.hips] into [npc.her] [npc.tail], you beg for [npc.herHim] to carry on fucking you.",
							" [pc.Moaning] in delight, you grind your [pc.hips+] into [npc.her] [npc.tail], begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.tail+] deep into your [pc.pussy+]"));
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
			return "Continue fucking [pc.name]'s [pc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking [npc.her] [npc.tail+] into your [pc.pussy+], [npc.name] starts sliding it in and out, letting out [npc.a_moan+] with every thrust as [npc.she] tail-fucks you.",
					"Pushing [npc.her] [npc.tail+] into your [pc.pussy+], [npc.name] thrusts it in and out, letting out [npc.a_moan+] as [npc.she] continues tail-fucking you.",
					"Thrusting [npc.her] [npc.tail+] into your [pc.pussy+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts pumping it in and out, breathing in your [pc.scent] as [npc.she] tail-fucks you."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You slowly buck your [pc.hips] in response, letting out a soft [pc.moan] as you start gently imploring [npc.name] to continue fucking you.",
							" A soft [pc.moan] drifts out from between your [pc.lips+], and, gently pushing your [pc.hips] out, you beg for [npc.herHim] to continue fucking you.",
							" [pc.Moaning] in delight, you slowly push your [pc.hips] out, telling [npc.name] to continue as your movements cause [npc.herHim] to sink [npc.her] [npc.tail+] deep into your [pc.pussy+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You violently buck your [pc.hips] in response, letting out [pc.a_moan+] as you roughly demand that [npc.name] continues fucking you.",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, roughly thrusting your [pc.hips] out, you order [npc.herHim] to continue fucking you.",
							" [pc.Moaning] in delight, you roughly thrust your [pc.hips] out, ordering [npc.name] to continue as your aggressive movements cause [npc.herHim] to sink [npc.her] [npc.tail+] deep into your [pc.pussy+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You buck your [pc.hips] in response, letting out [pc.a_moan+] as you start imploring [npc.name] to continue fucking you.",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, pushing your [pc.hips] out, you beg for [npc.herHim] to continue fucking you.",
							" [pc.Moaning] in delight, you push your [pc.hips] out, telling [npc.name] to continue as your movements cause [npc.herHim] to sink [npc.her] [npc.tail+] deep into your [pc.pussy+]"));
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
			return "Eagerly fuck [pc.name]'s [pc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking [npc.her] [npc.tail+] into your [pc.pussy+], [npc.name] starts eagerly sliding it in and out, letting out [npc.a_moan+] with every thrust as [npc.she] enthusiastically tail-fucks you.",
					"Eagerly pushing [npc.her] [npc.tail+] into your [pc.pussy+], [npc.name] energetically thrusts it in and out, letting out [npc.a_moan+] as [npc.she] continues desperately tail-fucking you.",
					"Eagerly thrusting [npc.her] [npc.tail+] into your [pc.pussy+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts frantically pumping it in and out, breathing in your [pc.scent] as [npc.she] tail-fucks you."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You slowly buck your [pc.hips] in response, letting out a soft [pc.moan] as you start gently imploring [npc.name] to continue fucking you.",
							" A soft [pc.moan] drifts out from between your [pc.lips+], and, gently pushing your [pc.hips] out, you beg for [npc.herHim] to continue fucking you.",
							" [pc.Moaning] in delight, you slowly push your [pc.hips] out, telling [npc.name] to continue as your movements cause [npc.herHim] to sink [npc.her] [npc.tail+] deep into your [pc.pussy+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You violently buck your [pc.hips] in response, letting out [pc.a_moan+] as you roughly demand that [npc.name] continues fucking you.",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, roughly thrusting your [pc.hips] out, you order [npc.herHim] to continue fucking you.",
							" [pc.Moaning] in delight, you roughly thrust your [pc.hips] out, ordering [npc.name] to continue as your aggressive movements cause [npc.herHim] to sink [npc.her] [npc.tail+] deep into your [pc.pussy+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You buck your [pc.hips] in response, letting out [pc.a_moan+] as you start imploring [npc.name] to continue fucking you.",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, pushing your [pc.hips] out, you beg for [npc.herHim] to continue fucking you.",
							" [pc.Moaning] in delight, you push your [pc.hips] out, telling [npc.name] to continue as your movements cause [npc.herHim] to sink [npc.her] [npc.tail+] deep into your [pc.pussy+]"));
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
			return "Try to pull your [npc.tail] away from [pc.name]'s [pc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					" Desperately trying, and failing, to pull [npc.her] [npc.tail] free from your [pc.pussy+], [npc.name] lets out [npc.a_sob+], pushing against you as [npc.she] weakly begs to be released.",
					" [npc.A_sob+] bursts out from between [npc.name]'s [npc.lips] as [npc.she] weakly tries to push you away, pleading for you to get your [pc.pussy+] away from [npc.her] [npc.tail].",
					" [npc.Sobbing] in distress, [npc.name] weakly struggles against you, pleading for you to let go of [npc.her] [npc.tail]."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, you slowly thrust your [pc.hips] out, letting out a soft [pc.moan] as you continue gently fucking yourself on [npc.her] [npc.tail+].",
							" A soft [pc.moan] drifts out from between your [pc.lips+], and, totally ignoring [npc.name]'s protests, you gently push your [pc.hips] out, before continuing to fuck yourself on [npc.her] [npc.tail+].",
							" [pc.Moaning] in delight, you totally ignore [npc.name]'s protests, slowly grinding your [pc.hips] out and softly [pc.moaning] as you sink [npc.her] [npc.tail+] deep into your [pc.pussy+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, you roughly slam your [pc.hips] out, letting out [pc.a_moan+] as you continue violently fucking yourself on [npc.her] [npc.tail+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, totally ignoring [npc.name]'s protests, you forcefully thrust your [pc.hips] out, before continuing to roughly fuck yourself on [npc.her] [npc.tail+].",
							" [pc.Moaning] in delight, you totally ignore [npc.name]'s protests, roughly grinding your [pc.hips] out and [pc.moaning+] out loud as you violently force [npc.her] [npc.tail+] deep into your [pc.pussy+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, you eagerly thrust your [pc.hips] out, letting out [pc.a_moan+] as you continue happily fucking yourself on [npc.her] [npc.tail+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, totally ignoring [npc.name]'s protests, you eagerly push your [pc.hips] out, before continuing to energetically fuck yourself on [npc.her] [npc.tail+].",
							" [pc.Moaning] in delight, you totally ignore [npc.name]'s protests, eagerly grinding your [pc.hips] out and [pc.moaning+] out loud as you force [npc.her] [npc.tail+] deep into your [pc.pussy+]"));
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
			return !Sex.isDom(Main.game.getPlayer()) ||Sex.isConsensual(); // Partner can only stop if they're in charge (otherwise, this is the player fucking themselves on the partner's cock).
		}
		
		@Override
		public String getActionTitle() {
			return "Stop tail-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.tail+] out of [pc.name]'s [pc.pussy+] and stop fucking [pc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.tail+] out of your [pc.pussy+], [npc.name] dominantly slides the [npc.tailTip] up and down between your folds one last time before pulling back.",
							"Thrusting deep inside of you one last time, [npc.name] then yanks [npc.her] [npc.tail+] back out of your [pc.pussy+], putting an end to the rough tail-fucking."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.tail] out of your [pc.pussy+], [npc.name] rubs the [npc.tailTip] up and down between your folds one last time before pulling back.",
							"Pushing deep inside of you one last time, [npc.name] then slides [npc.her] [npc.tail+] back out of your [pc.pussy+], putting an end to your tail-fucking."));
					break;
			}
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You can't help but let out [pc.sob+] as [npc.name] pulls out of your [pc.pussy], and you continue crying and protesting as you carry on weakly struggling against [npc.herHim].",
							" With [pc.a_sob+], you continue to struggle against [npc.name], tears streaming down your [pc.face] as [npc.she] withdraws from your [pc.pussy+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.name] pulls [npc.her] [npc.tail+] out of your [pc.pussy+], eager for more of [npc.her] attention.",
							" [pc.A_moan+] escapes from between your [pc.lips+], betraying your lust for [npc.her] [npc.tail+]."));
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
			return (Sex.isConsensual() || Sex.isDom(Main.game.getPlayer()));
		}
		
		@Override
		public String getActionTitle() {
			return "Get tail-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Start fucking yourself on [npc.name]'s [npc.tail+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing [npc.name]'s [npc.tail], you slowly slide the [npc.tailTip] over your outer labia, letting out a little [pc.moan] before gently bucking your [pc.hips] forwards and forcing [npc.herHim] to penetrate your [pc.pussy+].",
							"Grabbing [npc.name]'s [npc.tail], you line it up to your [pc.pussy+] before slowly pushing your [pc.hips] forwards, letting out a soft [pc.moan] as you penetrate yourself on [npc.her] [npc.tail+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing [npc.name]'s [npc.tail], you eagerly slide the [npc.tailTip+] over your outer labia, letting out [pc.a_moan+] before desperately bucking your [pc.hips] forwards and forcing [npc.herHim] to penetrate your [pc.pussy+].",
							"Grabbing [npc.name]'s [npc.tail], you line it up to your [pc.pussy+] before eagerly pushing your [pc.hips] forwards, letting out [pc.a_moan+] as you penetrate yourself on [npc.her] [npc.tail+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing [npc.name]'s [npc.tail], you eagerly slide the [npc.tailTip+] over your outer labia, letting out [pc.a_moan+] before roughly slamming your [pc.hips] forwards and forcing [npc.herHim] to penetrate your [pc.pussy+].",
							"Grabbing [npc.name]'s [npc.tail], you line it up to your [pc.pussy+] before violently slamming your [pc.hips] forwards, letting out [pc.a_moan+] as you roughly start fucking yourself on [npc.her] [npc.tail+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing [npc.name]'s [npc.tail], you eagerly slide the [npc.tailTip+] over your outer labia, letting out [pc.a_moan+] before desperately bucking your [pc.hips] forwards and forcing [npc.herHim] to penetrate your [pc.pussy+].",
							"Grabbing [npc.name]'s [npc.tail], you line it up to your [pc.pussy+] before eagerly pushing your [pc.hips] forwards, letting out [pc.a_moan+] as you penetrate yourself on [npc.her] [npc.tail+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing [npc.name]'s [npc.tail], you slide the [npc.tailTip+] over your outer labia, letting out [pc.a_moan+] before bucking your [pc.hips] forwards and forcing [npc.herHim] to penetrate your [pc.pussy+].",
							"Grabbing [npc.name]'s [npc.tail], you line it up to your [pc.pussy+] before pushing your [pc.hips] forwards, letting out [pc.a_moan+] as you penetrate yourself on [npc.her] [npc.tail+]."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out a soft [npc.moan] as [npc.she] enters you, gently pushing [npc.her] [npc.tail] forwards as [npc.she] starts to tail-fuck your [pc.pussy+].",
							" With a soft [npc.moan], [npc.name] gently thrusts [npc.her] [npc.tail] forwards, sinking it deep into your [pc.pussy+] as [npc.she] starts tail-fucking you."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as [npc.she] enters you, eagerly pushing [npc.her] [npc.tail] forwards as [npc.she] starts enthusiastically tail-fucking your [pc.pussy+].",
							" With [npc.a_moan+], [npc.name] eagerly thrusts [npc.her] [npc.tail] forwards, sinking it deep into your [pc.pussy+] as [npc.she] starts energetically tail-fucking you."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as [npc.she] enters you, and, seeking to remind you who's in charge, [npc.she] roughly slams [npc.her] [npc.tail] forwards as [npc.she] starts to ruthlessly tail-fuck your [pc.pussy+].",
							" With [npc.a_moan+], [npc.name] roughly slams [npc.her] [npc.tail] forwards, seeking to remind you who's in charge as [npc.she] starts ruthlessly tail-fucking your [pc.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as [npc.she] enters you, eagerly pushing [npc.her] [npc.tail] forwards as [npc.she] starts enthusiastically tail-fucking your [pc.pussy+].",
							" With [npc.a_moan+], [npc.name] eagerly thrusts [npc.her] [npc.tail] forwards, sinking it deep into your [pc.pussy+] as [npc.she] starts energetically tail-fucking you."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as [npc.she] enters you, pushing [npc.her] [npc.tail] forwards as [npc.she] starts tail-fucking your [pc.pussy+].",
							" With [npc.a_moan+], [npc.name] thrusts [npc.her] [npc.tail] forwards, sinking it deep into your [pc.pussy+] as [npc.she] starts tail-fucking you."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_sob+] as you force [npc.her] [npc.tail] inside of you, and, struggling against you, [npc.she] desperately tries to pull [npc.her] [npc.tail+] free from your [pc.pussy+].",
							" With [npc.a_sob+], [npc.name] struggles against you as you force [npc.her] [npc.tail] deep into your [pc.pussy+]."));
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
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle tail-ride";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck yourself on [npc.name]'s [npc.tail+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently pushing your [pc.hips] out, you let out a soft [pc.moan] as you help to sink [npc.her] [npc.tail+] deep into your [pc.pussy+].",
					"With a soft [pc.moan], you gently start gyrating your [pc.hips], forcing [npc.name]'s [npc.tail+] ever deeper into your [pc.pussy+].",
					"Slowly thrusting your [pc.hips], a soft [pc.moan] drifts out from between your [pc.lips+] as your movements force [npc.name]'s [npc.tail+] deep into your [pc.pussy+]."));

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
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Normal tail-ride";
		}

		@Override
		public String getActionDescription() {
			return "Fuck yourself on [npc.name]'s [npc.tail+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing your [pc.hips] out, you let out [pc.a_moan+] as you energetically help to sink [npc.her] [npc.tail+] deep into your [pc.pussy+].",
					"With [pc.a_moan+], you energetically start gyrating your [pc.hips], forcing [npc.name]'s [npc.tail+] ever deeper into your [pc.pussy+].",
					"Enthusiastically thrusting your [pc.hips], [pc.a_moan+] bursts out from between your [pc.lips+] as your movements force [npc.name]'s [npc.tail+] deep into your [pc.pussy+]."));
			
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
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Rough tail-ride";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck yourself on [npc.name]'s [npc.tail+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Violently slamming your [pc.hips] out, you let out [pc.a_moan+] as you roughly force [npc.her] [npc.tail+] deep into your [pc.pussy+].",
					"With [pc.a_moan+], you aggressively start gyrating your [pc.hips] against [npc.name], forcing [npc.her] [npc.tail+] ever deeper into your [pc.pussy+].",
					"Roughly thrusting your [pc.hips], [pc.a_moan+] bursts out from between your [pc.lips+] as your forceful movements drive [npc.name]'s [npc.tail+] deep into your [pc.pussy+]."));
			
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
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Get tail-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Buck your hips to help thrust [npc.name]'s [npc.tail] deep into your [pc.pussy].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"With a thrust of your [pc.hips], you let out [pc.a_moan+] as you help to sink [npc.name]'s [npc.tail+] deep into your [pc.pussy+].",
					"With [pc.a_moan+], you start gyrating your [pc.hips], forcing [npc.name]'s [npc.tail+] ever deeper into your [pc.pussy+].",
					"Thrusting your [pc.hips], [pc.a_moan+] drifts out from between your [pc.lips+] as your movements force [npc.name]'s [npc.tail+] deep into your [pc.pussy+]."));
			
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
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Eagerly tail-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly buck your hips to help thrust [npc.name]'s [npc.tail] deep into your [pc.pussy].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing your [pc.hips] out, you let out [pc.a_moan+] as you energetically help to sink [npc.her] [npc.tail+] deep into your [pc.pussy+].",
					"With [pc.a_moan+], you energetically start gyrating your [pc.hips], forcing [npc.name]'s [npc.tail+] ever deeper into your [pc.pussy+].",
					"Enthusiastically thrusting your [pc.hips], [pc.a_moan+] bursts out from between your [pc.lips+] as your movements force [npc.name]'s [npc.tail+] deep into your [pc.pussy+]."));
			
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
			return "Try and pull your [pc.pussy+] away from [npc.name]'s [npc.tail+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You feel tears start to well up in your [pc.eyes], and, not being able to hold back any longer, you suddenly let out [pc.a_sob+],"
									+ " weakly trying to pull [npc.name]'s [npc.tail] out of your [pc.pussy+] as [npc.she] continues gently fucking you.",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.hips] back from [npc.name]'s unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.tail+] continues slowly sliding in and out of your [pc.pussy+].",
							"Trying desperately to pull your [pc.hips] away from [npc.name], you [pc.sob] in distress as [npc.her] [npc.tail+] continues gently sliding deep into your [pc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You feel tears start to well up in your [pc.eyes], and, not being able to hold back any longer, you suddenly let out [pc.a_sob+],"
									+ " weakly trying to pull [npc.name]'s [npc.tail] out of your [pc.pussy+] as [npc.she] continues eagerly fucking you.",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.hips] back from [npc.name]'s unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.tail+] continues eagerly sliding in and out of your [pc.pussy+].",
							"Trying desperately to pull your [pc.hips] away from [npc.name], you [pc.sob] in distress as [npc.her] [npc.tail+] continues eagerly sliding deep into your [pc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You feel tears start to well up in your [pc.eyes], and, not being able to hold back any longer, you suddenly let out [pc.a_sob+],"
									+ " weakly trying to pull [npc.name]'s [npc.tail] out of your [pc.pussy+] as [npc.she] continues roughly fucking you.",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.hips] back from [npc.name]'s unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.tail+] continues roughly slamming in and out of your [pc.pussy+].",
							"Trying desperately to pull your [pc.hips] away from [npc.name], you [pc.sob] in distress as [npc.her] [npc.tail+] continues roughly slamming deep into your [pc.pussy+]."));
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
			return Sex.isConsensual() || Sex.isDom(Main.game.getPlayer()); // Player can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop tail-fucking";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to pull [npc.her] [npc.tail] out of your [pc.pussy+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc.name]'s [npc.tail] out of your [pc.pussy+], you let out a menacing growl as you command [npc.herHim] to stop fucking you.",
							"You lean into [npc.name], inhaling [npc.her] [npc.scent] before yanking [npc.her] [npc.tail] out of your [pc.pussy+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.name]'s [npc.tail] out of your [pc.pussy+], you let out [pc.a_moan+] as you tell [npc.herHim] to stop fucking you.",
							"You lean into [npc.name], inhaling [npc.her] [npc.scent] before sliding [npc.her] [npc.tail] out of your [pc.pussy+]."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out a relieved sigh, which soon turns into [npc.a_sob+] as [npc.she] realises that you haven't finished with [npc.herHim] just yet.",
							" With [npc.a_sob+], [npc.name] continues to protest and struggle against you as you hold [npc.herHim] firmly in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as you stop [npc.herHim] from fucking your [pc.pussy+].",
							" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] desire to continue fucking your [pc.pussy+]."));
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
			return "Squeeze your demonic pussy down around [npc.name]'s [npc.tail].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().getVaginaType()==VaginaType.DEMON_COMMON;
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Letting out [pc.a_moan+], you concentrate on squeezing the tentacles and demonic muscles within your [pc.pussy] down around [npc.name]'s [npc.tail+].",
					"You let out a cheeky giggle as you focus on controlling the tentacles lining your [pc.pussy]."
							+ " Wriggling and squeezing them down around [npc.name]'s [npc.tail+], you cause [npc.herHim] to let out an involuntary cry of pleasure.",
					"You find your [pc.moans] falling into a steady rhythm as you concentrate on squeezing the tentacles and extra muscles within your [pc.pussy+] down around [npc.name]'s [npc.tail+].",
					"With [pc.a_moan+], you focus on controlling the demonic tentacles deep within your [pc.pussy], gripping them down and massaging [npc.name]'s [npc.tail+] as you squeal in pleasure.");
		}
	};
	
}
