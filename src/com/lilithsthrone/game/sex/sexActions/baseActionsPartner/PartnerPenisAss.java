package com.lilithsthrone.game.sex.sexActions.baseActionsPartner;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.90
 * @version 0.1.90
 * @author Innoxia
 */
public class PartnerPenisAss {
	
	public static final SexAction PARTNER_TEASE_COCK_OVER_ASS = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.ASS,
			SexParticipantType.PITCHER) {
		@Override
		public String getActionTitle() {
			return "Hotdogging tease";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [npc.cockHead] of your [npc.cock] between [pc.name]'s ass cheeks.";
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
							"Gently pushing your [pc.assSize] ass cheeks together, [npc.name] starts slowly sliding the [npc.cockHead] of [npc.her] [npc.cock+] up and down over the cleft that's formed.",
							"Taking a gentle hold of your [pc.ass+], [npc.name] slowly pushes your cheeks together, before starting to tease [npc.her] [npc.cock+] over the cleft.",
							"With a gentle grasp on each of your ass cheeks, [npc.name] slowly pushes them together, before teasing the [npc.cockHead] of [npc.her] [npc.cock+] up against the crevice that's formed."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly pressing your [pc.assSize] ass cheeks together, [npc.name] starts forcefully sliding the [npc.cockHead] of [npc.her] [npc.cock+] up and down over the cleft that's formed.",
							"Taking a rough grip of your [pc.ass+], [npc.name] forcefully pushes your cheeks together, before starting to tease [npc.her] [npc.cock+] over the cleft.",
							"With a forceful grasp on each of your ass cheeks, [npc.name] roughly pushes them together, before grinding the [npc.cockHead] of [npc.her] [npc.cock+] up against the crevice that's formed."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Greedily pushing your [pc.assSize] ass cheeks together, [npc.name] starts eagerly sliding the [npc.cockHead] of [npc.her] [npc.cock+] up and down over the cleft that's formed.",
							"Taking a firm hold of your [pc.ass+], [npc.name] enthusiastically pushes your cheeks together, before starting to tease [npc.her] [npc.cock+] over the cleft.",
							"With a greedy grasp on each of your ass cheeks, [npc.name] eagerly pushes them together, before enthusiastically teasing the [npc.cockHead] of [npc.her] [npc.cock+] up against the crevice that's formed."));
					break;
			}
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] bursts out from between your [pc.lips+], [pc.speech(Please! Use my ass! I need to please your cock!)]",
							" You let out a desperate [pc.moan], before pleading, [pc.speech(Go on! Please! Use my ass already!)]",
							" You [pc.moan] in delight as you beg, [pc.speech(Yes! Fuck my ass! I need your cock!)]"));
					break;
				case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [pc.A_sob+] bursts out from between your [pc.lips+], [pc.speech(No! Don't! Please! Get away from me!)]",
								" You let out a desperate [pc.sob], before pleading, [pc.speech(Please! Don't do this! Leave me alone!)]",
								" You [pc.sob] in distress as you beg, [pc.speech(No! Stop! Get away from there!)]"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] drifts out from between your [pc.lips+], [pc.speech(That's right, use my ass!)]",
							" You let out a [pc.moan], before pleading, [pc.speech(Please! Use my ass!)]",
							" You [pc.moan] out loud as you beg, [pc.speech(Come on, use my ass already!)]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Sex.getActivePartner(), Main.game.getPlayer(), PenetrationType.PENIS, OrificeType.ASS);
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
			} else {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_DENIAL));
			}
		}
	};
	
	public static final SexAction PLAYER_FORCE_COCK_OVER_ASS = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.ASS,
			SexParticipantType.CATCHER) {
		@Override
		public String getActionTitle() {
			return "Hotdogging tease";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [npc.cockHead] of [npc.name]'s [npc.cock] between your ass cheeks.";
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
							"Shifting your [pc.hips] a little, you reach back and press your ass cheeks together, before slowly sliding the [npc.cockHead] of [npc.name]'s [npc.cock+] up and down over the cleft that's formed.",
							"Taking a gentle hold of your [pc.ass], you slowly push your cheeks together, before starting to tease [npc.name]'s [npc.cock+] over the cleft.",
							"With a gentle grasp on each of your ass cheeks, you slowly push them together, before teasing the [npc.cockHead] of [npc.name]'s [npc.cock+] up against the crevice that's formed."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting your [pc.hips] a little, you reach back and roughly press your ass cheeks together, before forcefully sliding the [npc.cockHead] of [npc.name]'s [npc.cock+] up and down over the cleft that's formed.",
							"Taking a firm hold of your [pc.ass], you roughly push your cheeks together, before starting to forcefully tease [npc.name]'s [npc.cock+] over the cleft.",
							"With a firm grasp on each of your ass cheeks, you forcefully push them together, before roughly teasing the [npc.cockHead] of [npc.name]'s [npc.cock+] up against the crevice that's formed."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting your [pc.hips] a little, you reach back and eagerly presses your ass cheeks together, before happily sliding the [npc.cockHead] of [npc.name]'s [npc.cock+] up and down over the cleft that's formed.",
							"Taking a firm hold of your [pc.ass], you slowly push your cheeks together, before starting to eagerly tease [npc.name]'s [npc.cock+] over the cleft.",
							"With a firm grasp on each of your ass cheeks, you eagerly push them together, before teasing the [npc.cockHead] of [npc.name]'s [npc.cock+] up against the crevice that's formed."));
					break;
			}
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], [npc.speech(Please! Let me fuck your ass!)]",
							" [npc.Name] lets out a desperate [npc.moan], before pleading with you, [npc.speech(Yes! Please! I want to fuck your ass!)]",
							" [npc.Name] [npc.moansVerb] in delight as [npc.she] begs, [npc.speech(Yes! Let me fuck you! Please!)]"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] drifts out from between [npc.her] [npc.lips+], [npc.speech(Yes, let me fuck your ass!)]",
							" [npc.Name] lets out a [npc.moan], before calling out, [npc.speech(Please! I want to fuck your ass!)]",
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
			Sex.transferLubrication(Main.game.getPlayer(), Sex.getActivePartner(), PenetrationType.PENIS, OrificeType.ASS);
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_DENIAL));
			} else {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING));
			}
		}
	};
	
	
	public static final SexAction PARTNER_ANAL_FUCKING_START = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.ASS,
			SexParticipantType.PITCHER) {

		@Override
		public boolean isBaseRequirementsMet() {
			// Partner can't penetrate if you're already fucking them, due to physical limitations. (I mean, if you're facing opposite ways and lying on top of each other, it might be possible, but that position will be special.)
			return Sex.isPenetrationTypeFree(Main.game.getPlayer(), PenetrationType.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Start hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Sink your [npc.cock+] between [pc.name]'s ass cheeks.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly pushing your [pc.assSize] ass cheeks together, [npc.name] presses the [npc.cockHead+] of [npc.her] [npc.cock+] up against your [pc.ass],"
									+ " before slowly pushing [npc.her] [npc.hips] forwards and starting to fuck the crevice that's formed.",
							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] up against your [pc.ass+], and with a slow, steady pressure,"
									+ " [npc.she] gently presses your cheeks together, before starting to fuck the crevice that's formed."));
					break;
				case DOM_NORMAL: case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pushing your [pc.assSize] ass cheeks together, [npc.name] presses the [npc.cockHead+] of [npc.her] [npc.cock+] up against your [pc.ass],"
									+ " before greedily pushing [npc.her] [npc.hips] forwards and starting to fuck the crevice that's formed.",
							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] up against your [pc.ass+], before eagerly pressing your cheeks together and starting to fuck the crevice that's formed."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly pushing your [pc.assSize] ass cheeks together, [npc.name] forcefully presses the [npc.cockHead+] of [npc.her] [npc.cock+] up against your [pc.ass],"
									+ " before violently pushing [npc.her] [npc.hips] forwards and starting to fuck the crevice that's formed.",
							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] up against your [pc.ass+], before roughly pressing your cheeks together and starting to aggressively fuck the crevice that's formed."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing your [pc.assSize] ass cheeks together, [npc.name] presses the [npc.cockHead+] of [npc.her] [npc.cock+] up against your [pc.ass],"
									+ " before pushing [npc.her] [npc.hips] forwards and starting to fuck the crevice that's formed.",
							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] up against your [pc.ass+], before pressing your cheeks together and starting to fuck the crevice that's formed."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soft [pc.moan] as [npc.she] starts using your ass, gently bucking your [pc.ass] back against [npc.herHim] as you help to sink [npc.her] [npc.cock+] even deeper between your ass cheeks.",
							" With a soft [pc.moan], you start gently bucking your [pc.ass] into [npc.her] crotch, sinking [npc.her] [npc.cock+] even deeper between your ass cheeks."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.she] starts using your ass, eagerly bucking your [pc.ass] back against [npc.herHim] as you help to sink [npc.her] [npc.cock+] even deeper between your ass cheeks.",
							" With [pc.a_moan+], you start eagerly bucking your [pc.ass] into [npc.her] crotch, desperately helping to sink [npc.her] [npc.cock+] even deeper between your ass cheeks."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.she] starts using your ass, violently thrusting your [pc.ass] back against [npc.herHim] as you roughly force [npc.her] [npc.cock+] even deeper between your ass cheeks.",
							" With [pc.a_moan+], you start violently bucking your [pc.ass] into [npc.her] crotch, roughly forcing [npc.herHim] to sink [npc.her] [npc.cock+] even deeper between your ass cheeks."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.she] starts using your ass, eagerly bucking your [pc.ass] back against [npc.herHim] as you help to sink [npc.her] [npc.cock+] even deeper between your ass cheeks.",
							" With [pc.a_moan+], you start eagerly bucking your [pc.ass] into [npc.her] crotch, desperately helping to sink [npc.her] [npc.cock+] even deeper between your ass cheeks."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.she] starts using your ass, bucking your [pc.ass] back against [npc.herHim] as you help to sink [npc.her] [npc.cock+] even deeper between your ass cheeks.",
							" With [pc.a_moan+], you start bucking your [pc.ass] into [npc.her] crotch, helping to sink [npc.her] [npc.cock+] even deeper between your ass cheeks."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_sob+] as [npc.she] starts using your ass, and, with tears running down your [pc.face], you beg for [npc.herHim] to stop as you weakly struggle against [npc.herHim].",
							" With [pc.a_sob+], you try, in vain, to pull away from [npc.name]; tears running down your [pc.face] as [npc.her] unwelcome [npc.cock] pushes deep between your ass cheeks."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.ASS,
			SexParticipantType.PITCHER,
			null,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [pc.name]'s ass cheeks.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently sinking [npc.her] [npc.cock+] between your ass cheeks, a soft [npc.moan] drifts out from between [npc.name]'s [npc.lips+] as [npc.she] starts slowly bucking [npc.her] [npc.hips],"
									+ " grinning as [npc.she] feels you greedily pushing your [pc.ass] back against [npc.herHim].",
							"[pc.A_moan+] bursts out from between your [pc.lips+] as [npc.name] slowly pushes [npc.her] [npc.cock+] between your ass cheeks,"
									+ " and you find yourself pushing your [pc.hips] back against [npc.herHim] as [npc.she] gently thrusts into your ass cleavage.",
							"Sliding [npc.her] [npc.cock+] between your ass cheeks, [npc.name] starts to gently buck [npc.her] [npc.hips] forwards, breathing in your [pc.scent] as you [pc.moanVerb+] in pleasure."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] gently sinks [npc.her] [npc.cock+] between your ass cheeks, causing you to let out [pc.a_sob+]. With tears streaming down your [pc.face], you weakly beg for [npc.herHim] to stop.",
							"[pc.A_sob+] bursts out from between your [pc.lips] as [npc.name] slowly pushes [npc.her] [npc.cock+] between your ass cheeks,"
									+ " and with tears streaming down your [pc.face], you desperately plead for [npc.herHim] to go away.",
							"Slowly sliding [npc.her] [npc.cock+] between your ass cheeks, [npc.name] gently bucks [npc.her] [npc.hips] forwards as you weakly struggle against [npc.herHim],"
									+ " pleading and crying for [npc.herHim] to stop."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently sinking [npc.her] [npc.cock+] between your ass cheeks, [npc.a_moan+] drifts out from between [npc.name]'s [npc.lips+] as [npc.she] starts slowly bucking [npc.her] [npc.hips],"
									+ " grinning as [npc.she] feels you greedily pushing your [pc.ass] back against [npc.herHim].",
							"[pc.A_moan+] bursts out from between your [pc.lips+] as [npc.name] slowly pushes [npc.her] [npc.cock+] between your ass cheeks,"
									+ " and you find yourself pushing your [pc.hips] back against [npc.herHim] as [npc.she] gently thrusts into your ass cleavage.",
							"Sliding [npc.her] [npc.cock+] between your ass cheeks, [npc.name] starts to gently buck [npc.her] [npc.hips] forwards, breathing in your [pc.scent] as you [pc.moanVerb+] in pleasure."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.ASS,
			SexParticipantType.PITCHER,
			null,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Normal hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [pc.name]'s ass cheeks.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking [npc.her] [npc.cock+] between your ass cheeks, [npc.a_moan+] drifts out from between [npc.name]'s [npc.lips+] as [npc.she] starts eagerly bucking [npc.her] [npc.hips],"
									+ " grinning as [npc.she] feels you greedily pushing your [pc.ass] back against [npc.herHim].",
							"[pc.A_moan+] bursts out from between your [pc.lips+] as [npc.name] eagerly pushes [npc.her] [npc.cock+] between your ass cheeks,"
									+ " and you find yourself pushing your [pc.hips] back against [npc.herHim] as [npc.she] rapidly thrusts into your ass cleavage.",
							"Enthusiastically thrusting [npc.her] [npc.cock+] between your ass cheeks, [npc.name] starts to eagerly buck [npc.her] [npc.hips] forwards,"
									+ " breathing in your [pc.scent] as you [pc.moanVerb+] in pleasure."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] eagerly sinks [npc.her] [npc.cock+] between your ass cheeks, causing you to let out [pc.a_sob+]. With tears streaming down your [pc.face], you weakly beg for [npc.herHim] to stop.",
							"[pc.A_sob+] bursts out from between your [pc.lips] as [npc.name] eagerly thrusts [npc.her] [npc.cock+] between your ass cheeks,"
									+ " and with tears streaming down your [pc.face], you desperately plead for [npc.herHim] to stop.",
							"Enthusiastically thrusting [npc.her] [npc.cock+] between your ass cheeks, [npc.name] eagerly bucks [npc.her] [npc.hips] forwards as you weakly struggle against [npc.herHim],"
									+ " pleading and crying for [npc.herHim] to go away."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking [npc.her] [npc.cock+] between your ass cheeks, [npc.a_moan+] drifts out from between [npc.name]'s [npc.lips+] as [npc.she] starts eagerly bucking [npc.her] [npc.hips],"
									+ " grinning as [npc.she] feels you greedily pushing your [pc.ass] back against [npc.herHim].",
							"[pc.A_moan+] bursts out from between your [pc.lips+] as [npc.name] eagerly pushes [npc.her] [npc.cock+] between your ass cheeks,"
									+ " and you find yourself pushing your [pc.hips] back against [npc.herHim] as [npc.she] rapidly thrusts into your ass cleavage.",
							"Enthusiastically thrusting [npc.her] [npc.cock+] between your ass cheeks, [npc.name] starts to rapidly buck [npc.her] [npc.hips] forwards,"
									+ " breathing in your [pc.scent] as you [pc.moanVerb+] in pleasure."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.PENIS,
			OrificeType.ASS,
			SexParticipantType.PITCHER,
			null,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Rough hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [pc.name]'s ass cheeks.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}


		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly slamming [npc.her] [npc.cock+] between your ass cheeks, [npc.a_moan+] drifts out from between [npc.name]'s [npc.lips+] as [npc.she] starts violently bucking [npc.her] [npc.hips],"
									+ " grinning as [npc.she] feels you greedily pushing your [pc.ass] back against [npc.herHim].",
							"[pc.A_moan+] bursts out from between your [pc.lips+] as [npc.name] roughly slams [npc.her] [npc.cock+] between your ass cheeks,"
									+ " and you find yourself pushing your [pc.hips] back against [npc.herHim] as [npc.she] brutally thrusts into your ass cleavage.",
							"Ruthlessly thrusting [npc.her] [npc.cock+] between your ass cheeks, [npc.name] starts to roughly slam [npc.her] [npc.hips] forwards,"
									+ " breathing in your [pc.scent] as you [pc.moanVerb+] in pleasure."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] roughly slams [npc.her] [npc.cock+] between your ass cheeks, causing you to let out [pc.a_sob+]. With tears streaming down your [pc.face], you weakly beg for [npc.herHim] to stop abusing you.",
							"[pc.A_sob+] bursts out from between your [pc.lips] as [npc.name] roughly slams [npc.her] [npc.cock+] between your ass cheeks,"
									+ " and with tears streaming down your [pc.face], you desperately plead for [npc.herHim] to stop abusing you.",
							"Ruthlessly thrusting [npc.her] [npc.cock+] between your ass cheeks, [npc.name] roughly slams [npc.her] [npc.hips] forwards as you weakly struggle against [npc.herHim],"
									+ " pleading and crying for [npc.herHim] to stop."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly slamming [npc.her] [npc.cock+] between your ass cheeks, [npc.a_moan+] drifts out from between [npc.name]'s [npc.lips+] as [npc.she] starts violently bucking [npc.her] [npc.hips],"
									+ " grinning as [npc.she] feels you pushing your [pc.ass] back against [npc.herHim].",
							"[pc.A_moan+] bursts out from between your [pc.lips+] as [npc.name] roughly slams [npc.her] [npc.cock+] between your ass cheeks,"
									+ " and you find yourself pushing your [pc.hips] back against [npc.herHim] as [npc.she] brutally thrusts into your ass cleavage.",
							"Ruthlessly thrusting [npc.her] [npc.cock+] between your ass cheeks, [npc.name] starts to roughly slam [npc.her] [npc.hips] forwards,"
									+ " breathing in your [pc.scent] as you [pc.moanVerb+] in pleasure."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.ASS,
			SexParticipantType.PITCHER,
			null,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Normal hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [pc.name]'s ass cheeks.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sinking [npc.her] [npc.cock+] between your ass cheeks, [npc.name] draws a soft [pc.moan] from your [pc.lips+] as [npc.she] starts bucking [npc.her] [npc.hips],"
									+ " letting out [npc.a_moan] of [npc.her] own as you start gently pushing your [pc.ass] back against [npc.her] groin.",
							"A soft [pc.moan] drifts out from between your [pc.lips+] as [npc.name] thrusts [npc.her] [npc.cock+] between your ass cheeks,"
									+ " and you can't help but gently push your [pc.ass] back against [npc.herHim] as [npc.she] carries on fucking your ass cleavage.",
							"Sliding [npc.her] [npc.cock+] between your ass cheeks, [npc.name] starts to thrust [npc.her] [npc.hips] forwards, breathing in your [pc.scent] as you softly [pc.moan] in pleasure."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sinking [npc.her] [npc.cock+] between your ass cheeks, [npc.name] draws [pc.a_moan+] from your [pc.lips+] as [npc.she] starts bucking [npc.her] [npc.hips],"
									+ " letting out [npc.a_moan] of [npc.her] own as you roughly command [pc.herHim] to continue fucking your ass cleavage.",
							"[pc.A_moan+] drifts out from between your [pc.lips+] as [npc.name] thrusts [npc.her] [npc.cock+] between your ass cheeks,"
									+ " and, roughly thrusting your [pc.ass] back into [npc.her] groin, you order [npc.herHim] to continue fucking your ass cleavage.",
							"Sliding [npc.her] [npc.cock+] between your ass cheeks, [npc.name] starts to thrust [npc.her] [npc.hips] forwards, breathing in your [pc.scent] as you [pc.moanVerb+] in pleasure."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sinking [npc.her] [npc.cock+] between your ass cheeks, [npc.name] draws [pc.a_moan+] from your [pc.lips+] as [npc.she] starts bucking [npc.her] [npc.hips],"
									+ " letting out [npc.a_moan] of [npc.her] own as you start greedily pushing your [pc.ass] back against [npc.her] groin.",
							"[pc.A_moan+] drifts out from between your [pc.lips+] as [npc.name] thrusts [npc.her] [npc.cock+] between your ass cheeks,"
									+ " and you can't help but greedily push your [pc.ass] back against [npc.herHim] as [npc.she] carries on fucking your ass cleavage.",
							"Sliding [npc.her] [npc.cock+] between your ass cheeks, [npc.name] starts to thrust [npc.her] [npc.hips] forwards, breathing in your [pc.scent] as you softly [pc.moan] in pleasure."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.ASS,
			SexParticipantType.PITCHER,
			null,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Eager hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [pc.name]'s ass cheeks.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking [npc.her] [npc.cock+] between your ass cheeks, [npc.name] draws a soft [pc.moan] from your [pc.lips+] as [npc.she] starts eagerly bucking [npc.her] [npc.hips],"
									+ " letting out [npc.a_moan] of [npc.her] own as you start gently pushing your [pc.ass] back against [npc.her] groin.",
							"A soft [pc.moan] drifts out from between your [pc.lips+] as [npc.name] eagerly thrusts [npc.her] [npc.cock+] between your ass cheeks,"
									+ " and you can't help but gently push your [pc.ass] back against [npc.herHim] as [npc.she] carries on enthusiastically fucking your ass cleavage.",
							"Eagerly pushing [npc.her] [npc.cock+] between your ass cheeks, [npc.name] starts to rapidly thrust [npc.her] [npc.hips] forwards, breathing in your [pc.scent] as you softly [pc.moan] in pleasure."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking [npc.her] [npc.cock+] between your ass cheeks, [npc.name] draws [pc.a_moan+] from your [pc.lips+] as [npc.she] starts eagerly bucking [npc.her] [npc.hips],"
									+ " letting out [npc.a_moan] of [npc.her] own as you roughly command [pc.herHim] to continue fucking your ass cleavage.",
							"[pc.A_moan+] drifts out from between your [pc.lips+] as [npc.name] eagerly thrusts [npc.her] [npc.cock+] between your ass cheeks,"
									+ " and, roughly thrusting your [pc.ass] back into [npc.her] groin, you order [npc.herHim] to continue fucking your ass cleavage.",
							"Eagerly sliding [npc.her] [npc.cock+] between your ass cheeks, [npc.name] starts to rapidly thrust [npc.her] [npc.hips] forwards, breathing in your [pc.scent] as you [pc.moanVerb+] in pleasure."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking [npc.her] [npc.cock+] between your ass cheeks, [npc.name] draws [pc.a_moan+] from your [pc.lips+] as [npc.she] starts eagerly bucking [npc.her] [npc.hips],"
									+ " letting out [npc.a_moan] of [npc.her] own as you start greedily pushing your [pc.ass] back against [npc.her] groin.",
							"[pc.A_moan+] drifts out from between your [pc.lips+] as [npc.name] eagerly thrusts [npc.her] [npc.cock+] between your ass cheeks,"
									+ " and you can't help but greedily push your [pc.ass] back against [npc.herHim] as [npc.she] carries on enthusiastically fucking your ass cleavage.",
							"Eagerly sliding [npc.her] [npc.cock+] between your ass cheeks, [npc.name] starts to rapidly thrust [npc.her] [npc.hips] forwards, breathing in your [pc.scent] as you softly [pc.moan] in pleasure."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS,
			OrificeType.ASS,
			SexParticipantType.PITCHER,
			null,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Resist hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [npc.cock] away from [pc.name]'s [pc.ass+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull [npc.her] [npc.cock+] away from you, [npc.name] lets out [npc.a_sob+] as, ignoring [npc.her] protests,"
									+ " you slowly thrust your [pc.ass] back against [npc.herHim] and continue gently forcing [npc.her] [npc.cock+] between your ass cheeks.",
							"[npc.A_sob+] bursts out from between [npc.name]'s [npc.lips] as [npc.she] weakly tries to push you away, but, totally ignoring [npc.her] protests,"
									+ " you firmly hold [npc.herHim] in place, gently pushing your [pc.ass] out against [npc.her] groin as you force [npc.her] [npc.cock+] between your ass cheeks.",
							"[npc.Sobbing] in distress, [npc.name] weakly struggles against you as [npc.she] pleads for you to let go of [npc.her] [npc.cock]."
									+ " [pc.Moaning] in delight, you totally ignore [npc.her] protests, slowly grinding yourself against [npc.herHim] as you sink [npc.her] [npc.cock+] deep between your ass cheeks."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull [npc.her] [npc.cock+] away from you, [npc.name] lets out [npc.a_sob+] as, ignoring [npc.her] protests,"
									+ " you violently thrust your [pc.ass] back against [npc.herHim] and continue roughly forcing [npc.her] [npc.cock+] between your ass cheeks.",
							"[npc.A_sob+] bursts out from between [npc.name]'s [npc.lips] as [npc.she] weakly tries to push you away, but, totally ignoring [npc.her] protests,"
									+ " you dominantly hold [npc.herHim] in place, violently pushing your [pc.ass] out against [npc.her] groin as you roughly force [npc.her] [npc.cock+] between your ass cheeks.",
							"[npc.Sobbing] in distress, [npc.name] weakly struggles against you as [npc.she] pleads for you to let go of [npc.her] [npc.cock]."
									+ " [pc.Moaning] in delight, you totally ignore [npc.her] protests, roughly grinding yourself against [npc.herHim] as you force [npc.her] [npc.cock+] deep between your ass cheeks."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull [npc.her] [npc.cock+] away from you, [npc.name] lets out [npc.a_sob+] as, ignoring [npc.her] protests,"
									+ " you eagerly thrust your [pc.ass] back against [npc.herHim] and continue rapidly forcing [npc.her] [npc.cock+] between your ass cheeks.",
							"[npc.A_sob+] bursts out from between [npc.name]'s [npc.lips] as [npc.she] weakly tries to push you away, but, totally ignoring [npc.her] protests,"
									+ " you firmly hold [npc.herHim] in place, eagerly pushing your [pc.ass] out against [npc.her] groin as you desperately force [npc.her] [npc.cock+] between your ass cheeks.",
							"[npc.Sobbing] in distress, [npc.name] weakly struggles against you as [npc.she] pleads for you to let go of [npc.her] [npc.cock]."
									+ " [pc.Moaning] in delight, you totally ignore [npc.her] protests, eagerly grinding yourself against [npc.herHim] as you happily force [npc.her] [npc.cock+] deep between your ass cheeks."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_STOP = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS,
			OrificeType.ASS,
			SexParticipantType.PITCHER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer()) ||Sex.isConsensual(); // Partner can only stop if they're in charge (otherwise, this is the player fucking themselves on the partner's cock).
		}
		
		@Override
		public String getActionTitle() {
			return "Stop hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.cock+] out from between [pc.name]'s ass cheeks.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.cock+] out of your ass cleavage,"
									+ " [npc.name] dominantly slides the [npc.cockHead] of [npc.her] [npc.cock] up and down over your [pc.ass+] one last time before pulling [npc.her] [npc.hips] back.",
							"Thrusting deep inside of your ass cleavage one last time, [npc.name] then pulls back, putting an end to your hotdogging."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.cock] out of your ass cleavage, [npc.name] slides the [npc.cockHead] of [npc.her] [npc.cock] up and down over your [pc.ass+] one last time before pulling [npc.her] [npc.hips] back.",
							"Pushing deep inside of your ass cleavage one last time, [npc.name] then pulls back, putting an end to your hotdogging."));
					break;
			}
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You can't help but let out [pc.sob+] as [npc.name] moves away, and you continue crying and protesting as you continue to weakly struggle against [npc.herHim].",
							" With [pc.a_sob+], you continue to struggle against [npc.name], tears streaming down your [pc.face] as [npc.she] prepares [npc.her] next move."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.name] pulls [npc.her] [npc.cock+] back, eager for more of [npc.her] attention.",
							" [pc.A_moan+] escapes from between your [pc.lips+], betraying your lust for [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Player actions:
	
	public static final SexAction PLAYER_USING_COCK_ANALLY_START = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.ASS,
			SexParticipantType.CATCHER) {

		@Override
		public boolean isBaseRequirementsMet() {
			// Player can only start fucking themselves on the partner's cock in consensual sex or if they're the dom.
			// You can't penetrate if you're already fucking your partner, due to physical limitations. (I mean, if you're facing opposite ways and lying on top of each other, it might be possible, but that position will be special.)
			
			if(Sex.isPenetrationTypeFree(Main.game.getPlayer(), PenetrationType.PENIS)) {
				return (Sex.isConsensual() || Sex.isDom(Main.game.getPlayer()));
			} else {
				return false;//(Sex.isConsensual() || Sex.isDom(Main.game.getPlayer())) && !Sex.getOngoingPenetrationMap().get(PenetrationType.PENIS).contains(OrificeType.VAGINA);
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Get hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "Force [npc.name]'s [npc.cock+] between your ass cheeks.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing yourself against [npc.name], you slowly slide [npc.her] [npc.cock+] over your [pc.ass+],"
									+ " letting out a little [pc.moan] before gently pressing your cheeks together and forcing [npc.her] [npc.cock] into the resulting cleavage.",
							"Lining your [pc.ass+] up to [npc.name]'s [npc.cock+], you slowly push your cheeks together,"
									+ " softly [pc.moaning] as you force [npc.her] [npc.cock] into the resulting cleavage."));
					break;
				case DOM_NORMAL: case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing yourself against [npc.name], you eagerly slide [npc.her] [npc.cock+] over your [pc.ass+],"
									+ " letting out [pc.a_moan+] before desperately pressing your cheeks together and forcing [npc.her] [npc.cock] into the resulting cleavage.",
							"Lining your [pc.ass+] up to [npc.name]'s [npc.cock+], you eagerly push your cheeks together,"
									+ " [pc.moaning+] as you force [npc.her] [npc.cock] into the resulting cleavage."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Forcefully pressing yourself against [npc.name], you roughly slide [npc.her] [npc.cock+] over your [pc.ass+],"
									+ " letting out [pc.a_moan+] before pressing your cheeks together and forcing [npc.her] [npc.cock] into the resulting cleavage.",
							"Lining your [pc.ass+] up to [npc.name]'s [npc.cock+], you roughly push your cheeks together,"
									+ " [pc.moaning+] as you force [npc.her] [npc.cock] into the resulting cleavage."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing yourself against [npc.name], you slide [npc.her] [npc.cock+] over your [pc.ass+],"
									+ " letting out [pc.a_moan+] before pressing your cheeks together and forcing [npc.her] [npc.cock] into the resulting cleavage.",
							"Lining your [pc.ass+] up to [npc.name]'s [npc.cock+], you push your cheeks together,"
									+ " [pc.moaning+] as you force [npc.her] [npc.cock] into the resulting cleavage."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out a soft [npc.moan], gently bucking [npc.her] [npc.hips] forwards as [npc.she] starts to fuck your ass cheeks.",
							" With a soft [npc.moan], [npc.name] gently thrusts [npc.her] [npc.hips] forwards and sinks [npc.her] [npc.cock+] between your ass cheeks."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+], eagerly bucking [npc.her] [npc.hips] as [npc.she] starts enthusiastically fucking your ass cheeks.",
							" With [npc.a_moan+], [npc.name] eagerly thrusts [npc.her] [npc.hips] forwards and sinks [npc.her] [npc.cock+] between your ass cheeks."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+], and, seeking to remind you who's in charge,"
									+ " [npc.she] roughly slams [npc.her] [npc.hips] forwards, before starting to ruthlessly fuck your ass cheeks.",
							" With [npc.a_moan+], [npc.name] roughly slams [npc.her] [npc.hips] forwards, seeking to remind you who's in charge as [npc.she] starts ruthlessly fucking your ass cheeks."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+], eagerly bucking [npc.her] [npc.hips] forwards as [npc.she] starts enthusiastically fucking your ass cheeks.",
							" With [npc.a_moan+], [npc.name] eagerly thrusts [npc.her] [npc.hips] forwards and sinks [npc.her] [npc.cock+] between your ass cheeks."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+], bucking [npc.her] [npc.hips] forwards as [npc.she] starts fucking your ass cheeks.",
							" With [npc.a_moan+], [npc.name] thrusts [npc.her] [npc.hips] forwards and sinks [npc.her] [npc.cock+] between your ass cheeks."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_sob+] as you force [npc.her] [npc.cock] between your ass cheeks, and, struggling against you, [npc.she] desperately tries to pull away.",
							" With [npc.a_sob+], [npc.name] struggles against you as you force [npc.her] [npc.cock] deep between your ass cheeks."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_ANALLY_DOM_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.ASS,
			SexParticipantType.CATCHER,
			SexPace.DOM_GENTLE,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Gently hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "Gently use [npc.name]'s [npc.cock+] to fuck your ass cheeks.";
		}

		@Override
		public String getDescription() {
				return UtilText.returnStringAtRandom(
						"Gently pushing your [pc.ass] into [npc.name]'s groin, you let out a soft [pc.moan] as you help to sink [npc.her] [npc.cock+] deep between your ass cheeks.",
						"With a soft [pc.moan], you gently start gyrating your [pc.hips] into [npc.name]'s [npc.ass], forcing [npc.her] [npc.cock+] ever deeper between your ass cheeks.",
						"Slowly thrusting your [pc.ass] into [npc.name]'s groin, a soft [pc.moan] drifts out from between your [pc.lips+] as your movements force [npc.her] [npc.cock+] deep between your ass cheeks.");
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_ANALLY_DOM_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.ASS,
			SexParticipantType.CATCHER,
			SexPace.DOM_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "Use [npc.name]'s [npc.cock+] to fuck your ass cheeks.";
		}

		@Override
		public String getDescription() {
				return UtilText.returnStringAtRandom(
						"Eagerly pushing your [pc.ass] into [npc.name]'s groin, you let out [pc.a_moan+] as you energetically help to sink [npc.her] [npc.cock+] deep between your ass cheeks.",
						"With [pc.a_moan+], you energetically start gyrating your [pc.hips] into [npc.name]'s [npc.ass], forcing [npc.her] [npc.cock+] ever deeper between your ass cheeks.",
						"Enthusiastically thrusting your [pc.ass] into [npc.name]'s groin, [pc.a_moan+] bursts out from between your [pc.lips+] as your movements force [npc.her] [npc.cock+] deep between your ass cheeks.");
			
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_ANALLY_DOM_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.PENIS,
			OrificeType.ASS,
			SexParticipantType.CATCHER,
			SexPace.DOM_ROUGH,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Roughly hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "Roughly use [npc.name]'s [npc.cock+] to fuck your ass cheeks.";
		}

		@Override
		public String getDescription() {
			
				return UtilText.returnStringAtRandom(
						"Violently slamming your [pc.ass] into [npc.name]'s groin, you let out [pc.a_moan+] as you roughly force [npc.her] [npc.cock+] deep between your ass cheeks.",
						"With [pc.a_moan+], you aggressively start gyrating your [pc.hips] into [npc.name]'s [npc.ass], roughly forcing [npc.her] [npc.cock+] ever deeper between your ass cheeks.",
						"Roughly thrusting your [pc.ass] into [npc.name]'s groin, [pc.a_moan+] bursts out from between your [pc.lips+] as your forceful movements drive [npc.her] [npc.cock+] deep between your ass cheeks.");
			
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_ANALLY_SUB_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.ASS,
			SexParticipantType.CATCHER,
			SexPace.SUB_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "Push your [pc.ass+] out against [npc.name] as [npc.her] [npc.cock] thrusts between your ass cheeks.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Pushing your [pc.ass] out into [npc.name]'s groin, you let out [pc.a_moan+] as you help to sink [npc.her] [npc.cock+] deep between your ass cheeks.",
					"With [pc.a_moan+], you start gyrating your [pc.ass] into [npc.name]'s groin, forcing [npc.her] [npc.cock+] ever deeper between your ass cheeks.",
					"Thrusting your [pc.ass] into [npc.name]'s groin, [pc.a_moan+] drifts out from between your [pc.lips+] as your movements force [npc.her] [npc.cock+] deep between your ass cheeks."));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_ANALLY_SUB_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.ASS,
			SexParticipantType.CATCHER,
			SexPace.SUB_EAGER,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Eagerly hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly push your [pc.ass+] out against [npc.name] as [npc.her] [npc.cock] thrusts between your ass cheeks.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing your [pc.ass] out into [npc.name]'s groin, you let out [pc.a_moan+] as you energetically help to sink [npc.her] [npc.cock+] deep between your ass cheeks.",
					"With [pc.a_moan+], you energetically start gyrating your [pc.ass] into [npc.name]'s groin, forcing [npc.her] [npc.cock+] ever deeper between your ass cheeks.",
					"Enthusiastically thrusting your [pc.ass] into [npc.name]'s groin, [pc.a_moan+] bursts out from between your [pc.lips+] as your movements force [npc.her] [npc.cock+] deep between your ass cheeks."));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKED_ANALLY_SUB_RESIST = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS,
			OrificeType.ASS,
			SexParticipantType.CATCHER,
			SexPace.SUB_RESISTING,
			null) {
		@Override
		public String getActionTitle() {
			return "Resist hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [pc.ass+] away from [npc.name]'s [npc.cock+].";
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
									+ " weakly trying to pull away from [npc.name]'s [npc.cock] as [npc.she] continues gently fucking your ass cheeks.",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.ass] away from [npc.name]'s unwanted [npc.cock],"
									+ " struggling in desperation as [npc.she] continues slowly sliding in and out between your ass cheeks.",
							"Trying desperately to pull your [pc.ass] away from [npc.name], you [pc.sob] in distress as [npc.her] [npc.cock+] continues gently sliding deep between your ass cheeks."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You feel tears start to well up in your [pc.eyes], and, not being able to hold back any longer, you suddenly let out [pc.a_sob+],"
									+ " weakly trying to pull away from [npc.name]'s [npc.cock] as [npc.she] continues eagerly fucking your ass cheeks.",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.ass] back from [npc.name]'s unwanted [npc.cock],"
									+ " struggling in desperation as [npc.she] continues eagerly sliding in and out between your ass cheeks.",
							"Trying desperately to pull your [pc.ass] away from [npc.name], you [pc.sob] in distress as [npc.her] [npc.cock+] continues eagerly sliding deep between your ass cheeks."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You feel tears start to well up in your [pc.eyes], and, not being able to hold back any longer, you suddenly let out [pc.a_sob+],"
									+ " weakly trying to pull [npc.name]'s [npc.cock] out of your [pc.asshole+] as [npc.she] continues roughly fucking your ass cheeks.",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.ass] back from [npc.name]'s unwanted [npc.cock],"
									+ " struggling in desperation as [npc.she] continues roughly slamming in and out between your ass cheeks.",
							"Trying desperately to pull your [pc.ass] away from [npc.name], you [pc.sob] in distress as [npc.her] [npc.cock+] continues roughly slamming deep between your ass cheeks."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKED_STOP = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS,
			OrificeType.ASS,
			SexParticipantType.CATCHER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || Sex.isDom(Main.game.getPlayer()); // Player can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to pull [npc.her] [npc.cock] out from between your ass cheeks.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc.name]'s [npc.cock] out from between your ass cheeks, you let out a menacing growl as you command [npc.herHim] to stop.",
							"You lean into [npc.name], inhaling [npc.her] [npc.scent] before yanking [npc.her] [npc.cock] out from between your ass cheeks."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.name]'s [npc.cock] out from between your ass cheeks, you let out [pc.a_moan+] as you tell [npc.herHim] to stop.",
							"You lean into [npc.name], inhaling [npc.her] [npc.scent] before sliding [npc.her] [npc.cock] out from between your ass cheeks."));
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
							" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] desire to continue fucking your ass cleavage."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
			
		}
	};
	
}
