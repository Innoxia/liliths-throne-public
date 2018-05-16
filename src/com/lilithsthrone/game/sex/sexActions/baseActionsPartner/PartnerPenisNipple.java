package com.lilithsthrone.game.sex.sexActions.baseActionsPartner;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
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

/**
 * @since 0.1.82
 * @version 0.1.82
 * @author Innoxia
 */
public class PartnerPenisNipple {
	
	public static final SexAction PARTNER_TEASE_COCK_OVER_NIPPLE = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.NIPPLE,
			SexParticipantType.PITCHER) {
		
		@Override
		public String getActionTitle() {
			return "Tease [pc.her] nipple";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [npc.cockHead] of your [npc.cock] over [pc.name]'s [pc.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (!Sex.isDom(Main.game.getPlayer()) ||Sex.isConsensual()) && Main.game.getPlayer().isBreastFuckableNipplePenetration();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Positioning [npc.her] [npc.cock+] over one of your [pc.breast+], [npc.name] starts slowly teasing the [npc.cockHead+] up and down over your [pc.nipple+], ready to penetrate you at any moment.",
							"With a soft [npc.moan], [npc.name] positions [npc.her] [npc.cock+] over one of your [pc.breasts+], before starting to gently slide the [npc.cockHead] up and down over your [pc.nipple+].",
							"Gently sliding the [npc.cockHead+] of [npc.her] [npc.cock] up and down over your [pc.nipple+], [npc.name] lets out a soft [npc.moan] at the thought of being able to penetrate you whenever [npc.she] feels like it."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding [npc.her] [npc.cock+] up against one of your [pc.breasts+], [npc.name] pulls back a little before starting to slide the [npc.cockHead+] up and down over your [pc.nipple+],"
									+ " ready to start fucking you at any moment.",
							"With [npc.a_moan+], [npc.name] positions [npc.her] [npc.cock+] over one of your [pc.breasts+], before starting to roughly grind the [npc.cockHead] up and down over your [pc.nipple+].",
							"Roughly grinding the [npc.cockHead+] of [npc.her] [npc.cock] up and down over one of your [pc.breasts+], [npc.name] lets out [npc.a_moan+] at the thought of being able to start fucking you whenever [npc.she] feels like it."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Positioning [npc.her] [npc.cock+] over one of your [pc.breasts+], [npc.name] starts eagerly sliding the [npc.cockHead+] up and down over your [pc.nipple+], ready to penetrate you at any moment.",
							"With [npc.a_moan+], [npc.name] positions [npc.her] [npc.cock+] over one of your [pc.breasts+], before starting to eagerly slide the [npc.cockHead] up and down over your [pc.nipple+].",
							"Eagerly sliding the [npc.cockHead+] of [npc.her] [npc.cock] up and down over your [pc.nipple+], [npc.name] lets out [npc.a_moan+] at the thought of being able to penetrate you whenever [npc.she] feels like it."));
					break;
			}
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] bursts out from between your [pc.lips+], [pc.speech(Please! Fuck me! I need your cock inside of me!)]",
							" You let out a desperate [pc.moan], before pleading, [pc.speech(Go on! Please! Fuck me already!)]",
							" You [pc.moan] in delight as you beg, [pc.speech(Yes! Fuck my breasts! I need you inside of me!)]"));
					break;
				case SUB_RESISTING:
					if(Main.game.getPlayer().isNippleVirgin()) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [pc.A_sob+] bursts out from between your [pc.lips+] at the thought of what's about to happen, [pc.speech(No! Don't! Please! I-I've never done this before! You can't do this!)]",
								" You let out a desperate [pc.sob], before pleading, [pc.speech(Please! Don't do this! I've never done this before!)]",
								" You [pc.sob] in distress at the thought of what's about to happen, before desperately begging, [pc.speech(No! Stop! Don't do this!)]"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [pc.A_sob+] bursts out from between your [pc.lips+], [pc.speech(No! Don't! Please! Get away from me!)]",
								" You let out a desperate [pc.sob], before pleading, [pc.speech(Please! Don't do this! Leave me alone!)]",
								" You [pc.sob] in distress as you beg, [pc.speech(No! Stop! Get away from there!)]"));
					}
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] drifts out from between your [pc.lips+], [pc.speech(That's right, fuck me!)]",
							" You let out a [pc.moan], before pleading, [pc.speech(Please! Fuck me already!)]",
							" You [pc.moan] out loud as you beg, [pc.speech(Come on, fuck me already!)]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Sex.getActivePartner(), Main.game.getPlayer(), PenetrationType.PENIS, OrificeType.NIPPLE);
		}

		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL, Fetish.FETISH_BREASTS_SELF);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL, Fetish.FETISH_BREASTS_OTHERS);
			}
		}
	};
	
	public static final SexAction PLAYER_FORCE_COCK_OVER_PUSSY = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.NIPPLE,
			SexParticipantType.CATCHER) {
		@Override
		public String getActionTitle() {
			return "Nipple-tease";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [npc.cockHead] of [npc.name]'s [npc.cock] over your [pc.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.isDom(Main.game.getPlayer()) ||Sex.isConsensual()) && Main.game.getPlayer().isBreastFuckableNipplePenetration();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Repositioning yourself, you line [npc.name]'s [npc.cock+] up to one of your [pc.breasts+],"
									+ " slowly pushing the [npc.cockHead+] up and down over your [pc.nipple+] as you tease [npc.herHim] with the promise of penetration.",
							"With a soft [pc.moan], you position [npc.name]'s [npc.cock+] over one of your [pc.breasts+], before starting to gently slide the [npc.cockHead] up and down over your [pc.nipple+].",
							"Lining [npc.name]'s [npc.cock+] up to one of your [pc.breasts+], you gently slide the [npc.cockHead+] over your [pc.nipple+],"
									+ " letting out a soft [pc.moan] as you tease [npc.herHim] with the promise of penetration."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Repositioning yourself, you line [npc.name]'s [npc.cock+] up to one of your [pc.breasts+],"
									+ " forcefully grinding the [npc.cockHead+] up and down over your [pc.nipple+] as you tease [npc.herHim] with the promise of penetration.",
							"With [pc.a_moan+], you grind [npc.name]'s [npc.cock+] over one of your [pc.breasts+], before starting to roughly force the [npc.cockHead] up and down over your [pc.nipple+].",
							"Lining [npc.name]'s [npc.cock+] up to one of your [pc.breasts+], you roughly grind the [npc.cockHead+] over your [pc.nipple+],"
									+ " letting out [pc.a_moan+] as you tease [npc.herHim] with the promise of penetration."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Repositioning yourself, you line [npc.name]'s [npc.cock+] up to one of your [pc.breasts+],"
									+ " eagerly pushing the [npc.cockHead+] up and down over your [pc.nipple+] as you tease [npc.herHim] with the promise of penetration.",
							"With [pc.a_moan+], you position [npc.name]'s [npc.cock+] over one of your [pc.breasts+], before starting to eagerly slide the [npc.cockHead] up and down over your [pc.nipple+].",
							"Lining [npc.name]'s [npc.cock+] up to one of your [pc.breasts+], you eagerly slide the [npc.cockHead+] over your [pc.nipple+],"
									+ " letting out [pc.a_moan+] as you tease [npc.herHim] with the promise of penetration."));
					break;
			}
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], [npc.speech(Please! Let me fuck you!)]",
							" [npc.Name] lets out a desperate [npc.moan], before pleading with you, [npc.speech(Yes! Please! I want to fuck you!)]",
							" [npc.Name] [npc.moansVerb] in delight as [npc.she] begs, [npc.speech(Yes! Let me fuck you! Please!)]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips+], [npc.speech(No! Don't! Please! Get away from me!)]",
							" [npc.Name] lets out a desperate [npc.sob], before pleading, [npc.speech(Please! Don't do this! Leave me alone!)]",
							" [npc.Name] [npc.sobsVerb] in distress as [npc.she] begs, [npc.speech(No! Stop! Get away from there!)]"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] drifts out from between [npc.her] [npc.lips+], [npc.speech(Yes, let me fuck you!)]",
							" [npc.Name] lets out a [npc.moan], before calling out, [npc.speech(Please! I want to fuck you!)]",
							" [npc.Name] [npc.moansVerb] out loud as [npc.she] speaks to you, [npc.speech(Come on, let me fuck you already!)]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Sex.getActivePartner(), Main.game.getPlayer(), PenetrationType.PENIS, OrificeType.NIPPLE);
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL, Fetish.FETISH_BREASTS_SELF);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL, Fetish.FETISH_BREASTS_OTHERS);
			}
		}
	};
	
	
	public static final SexAction PARTNER_FUCKING_START = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.NIPPLE,
			SexParticipantType.PITCHER) {
		
		@Override
		public String getActionTitle() {
			return "Nipple-fuck [pc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Sink your [npc.cock+] into [pc.name]'s [pc.nipple+] and start fucking [pc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly teasing the [npc.cockHead+] of [npc.her] [npc.cock] over one of your [pc.breasts+], [npc.name] lets out a little [npc.moan] before slowly pushing [npc.her] [npc.hips] forwards,"
									+ " sinking [npc.her] [npc.cock+] into your [pc.nipple+].",
							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] over one of your [pc.breasts+], and with a slow, steady pressure, [npc.she] gently sinks [npc.her] [npc.cock+] into your [pc.nipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [npc.cockHead+] of [npc.her] [npc.cock] over one of your [pc.breasts+], [npc.name] lets out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards,"
									+ " greedily sinking [npc.her] [npc.cock+] into your [pc.nipple+].",
							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] over one of your [pc.breasts+], and with a determined thrust, [npc.she] eagerly sinks [npc.her] [npc.cock+] into your [pc.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding the [npc.cockHead+] of [npc.her] [npc.cock] over one of your [pc.breasts+], [npc.name] lets out [npc.a_moan+] before violently slamming [npc.her] [npc.hips] forwards,"
									+ " forcing [npc.her] [npc.cock+] deep into your [pc.nipple+].",
							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] over one of your [pc.breasts+], and with a forceful thrust, [npc.she] roughly slams [npc.her] [npc.cock+] deep into your [pc.nipple+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [npc.cockHead+] of [npc.her] [npc.cock] over one of your [pc.breasts+], [npc.name] lets out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards,"
									+ " greedily sinking [npc.her] [npc.cock+] into your [pc.nipple+].",
							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] over one of your [pc.breasts+], and with a determined thrust, [npc.she] eagerly sinks [npc.her] [npc.cock+] into your [pc.nipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing the [npc.cockHead+] of [npc.her] [npc.cock] over one of your [pc.breasts+], [npc.name] lets out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards, sinking [npc.her] [npc.cock+] into your [pc.nipple+].",
							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] over one of your [pc.breasts+], and with a thrust of [npc.her] [npc.hips], [npc.she] sinks [npc.her] [npc.cock+] into your [pc.nipple+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soft [pc.moan] as [npc.she] enters you, gently pushing your chest out as you help to sink [npc.her] [npc.cock+] even deeper into your [pc.nipple+].",
							" With a soft [pc.moan], you start gently pushing your [pc.breast+] into [npc.her] crotch, sinking [npc.her] [npc.cock+] even deeper into your [pc.nipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.she] enters you, eagerly pushing your chest out as you help to sink [npc.her] [npc.cock+] even deeper into your [pc.nipple+].",
							" With [pc.a_moan+], you start eagerly pushing your [pc.breast+] into [npc.her] crotch, desperately helping to sink [npc.her] [npc.cock+] even deeper into your [pc.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.she] enters you, violently thrusting your chest out as you roughly force [npc.her] [npc.cock+] even deeper into your [pc.nipple+].",
							" With [pc.a_moan+], you start violently thrusting your [pc.breast+] into [npc.her] crotch, roughly forcing [npc.herHim] to sink [npc.her] [npc.cock+] even deeper into your [pc.nipple+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.she] enters you, eagerly pushing your chest out as you help to sink [npc.her] [npc.cock+] even deeper into your [pc.nipple+].",
							" With [pc.a_moan+], you start eagerly pushing your [pc.breast+] into [npc.her] crotch, desperately helping to sink [npc.her] [npc.cock+] even deeper into your [pc.nipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.she] enters you, pushing your chest out as you help to sink [npc.her] [npc.cock+] even deeper into your [pc.nipple+].",
							" With [pc.a_moan+], you start pushing your [pc.breast+] into [npc.her] crotch, helping to sink [npc.her] [npc.cock+] even deeper into your [pc.nipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_sob+] as [npc.she] enters you, and, with tears running down your [pc.face], you beg for [npc.herHim] to pull out as you weakly struggle against [npc.herHim].",
							" With [pc.a_sob+], you try, in vain, to pull away from [npc.name]'s unwanted penetration, tears running down your [pc.face] as [npc.her] unwelcome [npc.cock] pushes deep into your [pc.nipple+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.NIPPLE,
			SexParticipantType.PITCHER,
			null,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle nipple-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [pc.name]'s [pc.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sinking [npc.her] [npc.cock+] into your [pc.nipple+], [npc.name] starts bucking [npc.her] [npc.hips] into you, softly pressing [npc.her] groin into your [pc.breast+] with every thrust as [npc.she] slowly nipple-fucks you.",
					"Slowly pushing [npc.her] [npc.cock+] into your [pc.nipple+], [npc.name] softly thrusts [npc.her] [npc.hips] against you, letting out a little [npc.moan] as [npc.she] gently nipple-fucks you.",
					"Sliding [npc.her] [npc.cock+] into your [pc.nipple+], [npc.name] lets out a little [npc.moan] as [npc.she] starts to gently buck [npc.her] [npc.hips], breathing in your [pc.scent] as [npc.she] slowly nipple-fucks you."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You eagerly thrust your chest out against [npc.herHim], letting out [pc.a_moan+] as you enthusiastically help to sink [npc.her] [npc.cock+] deep into your [pc.nipple+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, eagerly thrusting your chest out, you beg for [npc.name] to carry on fucking you like this.",
							" [pc.Moaning] in delight, you push your [pc.breasts+] out, eagerly begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.nipple+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.name]'s groin, you let out [pc.a_sob+], tears streaming down your [pc.face] as you weakly beg for [npc.herHim] to pull out of your [pc.nipple+].",
							" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, tears streaming down your [pc.face] as you plead for [npc.herHim] to pull out of your [pc.nipple+].",
							" [pc.Sobbing] in distress, and with tears running down your [pc.face], you weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of your [pc.nipple+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You thrust your chest out against [npc.name], letting out [pc.a_moan+] as you help to sink [npc.her] [npc.cock+] deep into your [pc.nipple+].",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, thrusting your chest out, you beg for [npc.herHim] to carry on fucking you like this.",
							" [pc.Moaning] in delight, you push your [pc.breasts+] out, begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.nipple+]"));
					break;
			}
				
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.NIPPLE,
			SexParticipantType.PITCHER,
			null,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Nipple-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [pc.name]'s [pc.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking [npc.her] [npc.cock+] into your [pc.nipple+], [npc.name] starts eagerly bucking [npc.her] [npc.hips] into you, slamming into your [pc.breasts+] with every thrust as [npc.she] enthusiastically nipple-fucks you.",
					"Eagerly pushing [npc.her] [npc.cock+] into your [pc.nipple+], [npc.name] greedily thrusts [npc.her] [npc.hips] into your [pc.breasts+], letting out [npc.a_moan+] as [npc.she] continues nipple-fucking you.",
					"Enthusiastically thrusting [npc.her] [npc.cock+] into your [pc.nipple+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts frantically bucking [npc.her] [npc.hips],"
							+ " breathing in your [pc.scent] as [npc.she] eagerly nipple-fucks you."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You eagerly thrust your chest out against [npc.herHim], letting out [pc.a_moan+] as you enthusiastically help to sink [npc.her] [npc.cock+] deep into your [pc.nipple+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, eagerly thrusting your chest out, you beg for [npc.name] to carry on fucking you like this.",
							" [pc.Moaning] in delight, you push your [pc.breasts+] out, eagerly begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.nipple+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.name]'s groin, you let out [pc.a_sob+], tears streaming down your [pc.face] as you weakly beg for [npc.herHim] to pull out of your [pc.nipple+].",
							" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, tears streaming down your [pc.face] as you plead for [npc.herHim] to pull out of your [pc.nipple+].",
							" [pc.Sobbing] in distress, and with tears running down your [pc.face], you weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of your [pc.nipple+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You thrust your chest out against [npc.name], letting out [pc.a_moan+] as you help to sink [npc.her] [npc.cock+] deep into your [pc.nipple+].",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, thrusting your chest out, you beg for [npc.herHim] to carry on fucking you like this.",
							" [pc.Moaning] in delight, you push your [pc.breasts+] out, begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.nipple+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.NIPPLE,
			SexParticipantType.PITCHER,
			null,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Rough nipple-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [pc.name]'s [pc.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}


		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly slamming [npc.her] [npc.cock+] deep into your [pc.nipple+], [npc.name] starts violently pumping [npc.her] [npc.hips], grinding into your [pc.breasts+] with every thrust as [npc.she] brutally nipple-fucks you.",
					"Violently thrusting [npc.her] [npc.cock+] deep into your [pc.nipple+], [npc.name] starts slamming [npc.her] [npc.hips] into your [pc.breasts+], letting out [npc.a_moan+] as [npc.she] roughly nipple-fucks you.",
					"Ruthlessly thrusting [npc.her] [npc.cock+] deep into your [pc.nipple+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts violently thrusting [npc.her] [pc.hips] back and forth,"
							+ " breathing in your [pc.scent] as [npc.she] roughly nipple-fucks you."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You eagerly thrust your chest out against [npc.herHim], letting out [pc.a_moan+] as you enthusiastically help to sink [npc.her] [npc.cock+] deep into your [pc.nipple+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, eagerly thrusting your chest out, you beg for [npc.name] to carry on fucking you like this.",
							" [pc.Moaning] in delight, you push your [pc.breasts+] out, eagerly begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.nipple+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.name]'s groin, you let out [pc.a_sob+], tears streaming down your [pc.face] as you weakly beg for [npc.herHim] to pull out of your [pc.nipple+].",
							" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, tears streaming down your [pc.face] as you plead for [npc.herHim] to pull out of your [pc.nipple+].",
							" [pc.Sobbing] in distress, and with tears running down your [pc.face], you weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of your [pc.nipple+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You thrust your chest out against [npc.name], letting out [pc.a_moan+] as you help to sink [npc.her] [npc.cock+] deep into your [pc.nipple+].",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, thrusting your chest out, you beg for [npc.herHim] to carry on fucking you like this.",
							" [pc.Moaning] in delight, you push your [pc.breasts+] out, begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.nipple+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.NIPPLE,
			SexParticipantType.PITCHER,
			null,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Nipple-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [pc.name]'s [pc.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking [npc.her] [npc.cock+] into your [pc.nipple+], [npc.name] starts bucking [npc.her] [npc.hips], slamming into your [pc.breasts+] with every thrust as [npc.she] nipple-fucks you.",
					"Pushing [npc.her] [npc.cock+] into your [pc.nipple+], [npc.name] thrusts [npc.her] [npc.hips] into your [pc.breasts+], letting out [npc.a_moan+] as [npc.she] continues nipple-fucking you.",
					"Thrusting [npc.her] [npc.cock+] into your [pc.nipple+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts bucking [npc.her] [npc.hips], breathing in your [pc.scent] as [npc.she] nipple-fucks you."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You slowly push out your chest in response, letting out a soft [pc.moan] as you start gently imploring [npc.name] to continue fucking you.",
							" A soft [pc.moan] drifts out from between your [pc.lips+], and, gently pushing your chest out against [npc.name]'s groin, you beg for [npc.herHim] to continue fucking you.",
							" [pc.Moaning] in delight, you slowly thrust your chest out, softly [pc.moaning] for [npc.herHim] to continue as your movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into your [pc.nipple+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You violently thrust your chest out in response, letting out [pc.a_moan+] as you roughly demand that [npc.name] continues fucking you.",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, roughly thrusting your chest out against [npc.name]'s groin, you order [npc.herHim] to continue fucking you.",
							" [pc.Moaning] in delight, you roughly thrust your chest out, ordering [npc.herHim] to continue as your aggressive movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into your [pc.nipple+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You push your chest out in response, letting out [pc.a_moan+] as you start imploring [npc.name] to continue fucking you.",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, pushing your chest out against [npc.name]'s groin, you beg for [npc.herHim] to continue fucking you.",
							" [pc.Moaning] in delight, you push your chest out, [pc.moaning] for [npc.herHim] to continue as your movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into your [pc.nipple+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.NIPPLE,
			SexParticipantType.PITCHER,
			null,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Eager nipple-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [pc.name]'s [pc.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking [npc.her] [npc.cock+] into your [pc.nipple+], [npc.name] starts eagerly bucking [npc.her] [npc.hips] into you, slamming into your [pc.breasts+] with every thrust as [npc.she] enthusiastically nipple-fucks you.",
					"Eagerly pushing [npc.her] [npc.cock+] into your [pc.nipple+], [npc.name] starts thrusting [npc.her] [npc.hips], letting out [npc.a_moan+] as [npc.she] continues desperately nipple-fucking you.",
					"Eagerly thrusting [npc.her] [npc.cock+] into your [pc.nipple+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts frantically bucking [npc.her] [npc.hips], breathing in your [pc.scent] as [npc.she] nipple-fucks you."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You slowly push out your chest in response, letting out a soft [pc.moan] as you start gently imploring [npc.name] to continue fucking you.",
							" A soft [pc.moan] drifts out from between your [pc.lips+], and, gently pushing your chest out against [npc.name]'s groin, you beg for [npc.herHim] to continue fucking you.",
							" [pc.Moaning] in delight, you slowly thrust your chest out, softly [pc.moaning] for [npc.herHim] to continue as your movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into your [pc.nipple+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You violently thrust your chest out in response, letting out [pc.a_moan+] as you roughly demand that [npc.name] continues fucking you.",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, roughly thrusting your chest out against [npc.name]'s groin, you order [npc.herHim] to continue fucking you.",
							" [pc.Moaning] in delight, you roughly thrust your chest out, ordering [npc.herHim] to continue as your aggressive movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into your [pc.nipple+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You push your chest out in response, letting out [pc.a_moan+] as you start imploring [npc.name] to continue fucking you.",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, pushing your chest out against [npc.name]'s groin, you beg for [npc.herHim] to continue fucking you.",
							" [pc.Moaning] in delight, you push your chest out, [pc.moaning] for [npc.herHim] to continue as your movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into your [pc.nipple+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS,
			OrificeType.NIPPLE,
			SexParticipantType.PITCHER,
			null,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Resist nipple-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [npc.cock] away from [pc.name]'s [pc.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					" Desperately trying, and failing, to pull [npc.her] [npc.cock] free from your [pc.nipple+], [npc.name] lets out [npc.a_sob+], pushing against you as [npc.she] weakly begs to be released.",
					" [npc.A_sob+] bursts out from between [npc.name]'s [npc.lips] as [npc.she] weakly tries to push you away, pleading for you to release [npc.her] [npc.cock].",
					" [npc.Sobbing] in distress, [npc.name] weakly struggles against you, pleading for you to release [npc.her] [npc.cock]."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, you slowly thrust your chest out, letting out a soft [pc.moan] as you continue gently fucking your [pc.nipple+] on [npc.her] [npc.cock+].",
							" A soft [pc.moan] drifts out from between your [pc.lips+], and, totally ignoring [npc.name]'s protests, you gently push your chest out, continuing to fuck your [pc.nipple+] on [npc.her] [npc.cock+].",
							" [pc.Moaning] in delight, you totally ignore [npc.name]'s protests, slowly pushing your chest out and softly [pc.moaning] as you sink [npc.her] [npc.cock+] deep into your [pc.nipple+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, you roughly slam your chest out, letting out [pc.a_moan+] as you continue violently fucking your [pc.nipple+] on [npc.her] [npc.cock+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, totally ignoring [npc.name]'s protests, you forcefully thrust your chest out, continuing to roughly fuck your [pc.nipple+] on [npc.her] [npc.cock+].",
							" [pc.Moaning] in delight, you totally ignore [npc.name]'s protests, roughly thrusting your chest out and [pc.moaning+] out loud as you violently force [npc.her] [npc.cock+] deep into your [pc.nipple+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, you eagerly thrust your chest out, letting out [pc.a_moan+] as you continue happily fucking your [pc.nipple+] on [npc.her] [npc.cock+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, totally ignoring [npc.name]'s protests, you eagerly push your chest out, continuing to energetically fuck your [pc.nipple+] on [npc.her] [npc.cock+].",
							" [pc.Moaning] in delight, you totally ignore [npc.name]'s protests, eagerly thrusting your chest out and [pc.moaning+] out loud as you force [npc.her] [npc.cock+] deep into your [pc.nipple+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_STOP = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS,
			OrificeType.NIPPLE,
			SexParticipantType.PITCHER) {
		
		@Override
		public String getActionTitle() {
			return "Stop nipple-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.cock+] out of [pc.name]'s [pc.nipple+] and stop fucking [pc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.cock+] out of your [pc.breast+], [npc.name] dominantly slides the [npc.cockHead] of [npc.her] [npc.cock] up and down over your [pc.nipple+] one last time before pulling [npc.her] [npc.hips] back.",
							"Thrusting deep inside of you one last time, [npc.name] then yanks [npc.her] [npc.cock+] back out of your [pc.nipple+], putting an end to the rough fucking."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.cock] out of your [pc.breast+], [npc.name] slides the [npc.cockHead] of [npc.her] [npc.cock] up and down over your [pc.nipple+] one last time before pulling [npc.her] [npc.hips] back.",
							"Pushing deep inside of you one last time, [npc.name] then slides [npc.her] [npc.cock+] back out of your [pc.nipple+], putting an end to your nipple-fucking."));
					break;
			}
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You can't help but let out [pc.sob+] as [npc.name] pulls out of your [pc.nipple], continuing to cry and protest as you weakly struggle against [npc.herHim].",
							" With [pc.a_sob+], you continue to struggle against [npc.name], tears streaming down your [pc.face] as [npc.she] withdraws from your [pc.nipple+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.name] pulls [npc.her] [npc.cock+] out of your [pc.nipple+], eager for more of [npc.her] attention.",
							" [pc.A_moan+] escapes from between your [pc.lips+], betraying your lust for [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Player actions:
	
	public static final SexAction PLAYER_USING_COCK_START = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.NIPPLE,
			SexParticipantType.CATCHER) {
		
		@Override
		public String getActionTitle() {
			return "Get nipple-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Use [npc.name]'s [npc.cock+] to fuck your [pc.breast+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing yourself against [npc.name], you slowly slide [npc.her] [npc.cock+] up against one of your [pc.breasts+],"
									+ " letting out a little [pc.moan] before gently pushing your chest out and forcing [npc.herHim] to penetrate your [pc.nipple+].",
							"Lining one of your [pc.nipples+] up to [npc.name]'s [npc.cock+], you slowly push your chest out, letting out a soft [pc.moan] as you penetrate yourself on [npc.her] [npc.cock+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing yourself against [npc.name], you eagerly guide [npc.her] [npc.cock+] up against one of your [pc.breasts+],"
									+ " letting out [pc.a_moan+] before desperately pushing your chest out and forcing [npc.herHim] to penetrate your [pc.nipple+].",
							"Lining one of your [pc.nipples+] up to [npc.name]'s [npc.cock+], you eagerly push your chest out, letting out [pc.a_moan+] as you penetrate yourself on [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Forcefully grinding yourself against [npc.name], you guide [npc.her] [npc.cock+] up against one of your [pc.breasts+],"
									+ " letting out [pc.a_moan+] before roughly pushing your chest out and forcing [npc.herHim] to penetrate your [pc.nipple+].",
							"Lining one of your [pc.nipples+] up to [npc.name]'s [npc.cock+], you violently thrust your chest out, letting out [pc.a_moan+] as you roughly start fucking your [pc.breast+] on [npc.her] [npc.cock+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing yourself against [npc.name], you eagerly guide [npc.her] [npc.cock+] up against one of your [pc.breasts+],"
									+ " letting out [pc.a_moan+] before desperately thrusting your chest out and forcing [npc.herHim] to penetrate your [pc.nipple+].",
							"Lining one of your [pc.nipples+] up to [npc.name]'s [npc.cock+], you eagerly push your chest out, letting out [pc.a_moan+] as you penetrate yourself on [npc.her] [npc.cock+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing yourself against [npc.name], you guide [npc.her] [npc.cock+] up against one of your [pc.breasts+],"
									+ " letting out [pc.a_moan+] before bucking your [pc.hips] forwards and forcing [npc.herHim] to penetrate your [pc.nipple+].",
							"Lining one of your [pc.nipples+] up to [npc.name]'s [npc.cock+], you push your chest out, letting out [pc.a_moan+] as you penetrate yourself on [npc.her] [npc.cock+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out a soft [npc.moan] as [npc.she] enters you, gently bucking [npc.her] [npc.hips] as [npc.she] starts to fuck your [pc.nipple+].",
							" With a soft [npc.moan], [npc.name] gently thrusts [npc.her] [npc.hips] into your [pc.breast+], sinking [npc.her] [npc.cock+] into your [pc.nipple+] as [npc.she] starts fucking you."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as [npc.she] enters you, eagerly bucking [npc.her] [npc.hips] as [npc.she] starts enthusiastically fucking your [pc.nipple+].",
							" With [npc.a_moan+], [npc.name] eagerly thrusts [npc.her] [npc.hips] into your [pc.breast+], sinking [npc.her] [npc.cock+] into your [pc.nipple+] as [npc.she] starts energetically fucking you."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as [npc.she] enters you, and, seeking to remind you who's in charge,"
									+ " [npc.she] roughly slams [npc.her] [npc.hips] forwards, before starting to ruthlessly fuck your [pc.nipple+].",
							" With [npc.a_moan+], [npc.name] roughly slams [npc.her] [npc.hips] into your [pc.breast+], seeking to remind you who's in charge as [npc.she] starts ruthlessly fucking your [pc.nipple+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as [npc.she] enters you, eagerly bucking [npc.her] [npc.hips] as [npc.she] starts enthusiastically fucking your [pc.nipple+].",
							" With [npc.a_moan+], [npc.name] eagerly thrusts [npc.her] [npc.hips] into your [pc.breast+], sinking [npc.her] [npc.cock+] into your [pc.nipple+] as [npc.she] starts energetically fucking you."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as [npc.she] enters you, bucking [npc.her] [npc.hips] into your groin as [npc.she] starts fucking your [pc.nipple+].",
							" With [npc.a_moan+], [npc.name] thrusts [npc.her] [npc.hips] into your [pc.breast+], sinking [npc.her] [npc.cock+] into your [pc.nipple+] as [npc.she] starts fucking you."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_sob+] as you force [npc.her] [npc.cock] inside of your [pc.breast+], and, struggling against you, [npc.she] desperately tries to pull [npc.her] [npc.cock+] free.",
							" With [npc.a_sob+], [npc.name] struggles against you as you force [npc.her] [npc.cock] deep into your [pc.nipple+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_DOM_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.NIPPLE,
			SexParticipantType.CATCHER,
			SexPace.DOM_GENTLE,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Gently nipple-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck your [pc.nipple+] on [npc.name]'s [npc.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently pushing your chest out into [npc.name]'s groin, you let out a soft [pc.moan] as you help to sink [npc.her] [npc.cock+] deep into your [pc.nipple+].",
					"With a soft [pc.moan], you gently start pushing your chest out, forcing [npc.name]'s [npc.cock+] ever deeper into your [pc.nipple+].",
					"Slowly pushing your chest out, a soft [pc.moan] drifts out from between your [pc.lips+] as your movements force [npc.her] [npc.cock+] deep into your [pc.nipple+]."));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_DOM_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.NIPPLE,
			SexParticipantType.CATCHER,
			SexPace.DOM_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Nipple-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Fuck yourself on [npc.name]'s [npc.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly thrusting your chest out into [npc.name]'s groin, you let out [pc.a_moan+] as you energetically help to sink [npc.her] [npc.cock+] deep into your [pc.nipple+].",
					"With [pc.a_moan+], you energetically start thrusting your [pc.hips] out, forcing [npc.name]'s [npc.cock+] ever deeper into your [pc.nipple+].",
					"Enthusiastically thrusting your chest out, [pc.a_moan+] bursts out from between your [pc.lips+] as your movements force [npc.her] [npc.cock+] deep into your [pc.nipple+]."));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_DOM_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.NIPPLE,
			SexParticipantType.CATCHER,
			SexPace.DOM_ROUGH,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Roughly nipple-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck your [pc.nipple+] on [npc.name]'s [npc.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Violently slamming your chest out into [npc.name]'s groin, you let out [pc.a_moan+] as you roughly force [npc.her] [npc.cock+] deep into your [pc.nipple+].",
					"With [pc.a_moan+], you aggressively start thrusting your chest out, forcing [npc.name]'s [npc.cock+] ever deeper into your [pc.nipple+].",
					"Roughly thrusting your chest out, [pc.a_moan+] bursts out from between your [pc.lips+] as your forceful movements drive [npc.name]'s [npc.cock+] deep into your [pc.nipple+]."));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_SUB_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.NIPPLE,
			SexParticipantType.CATCHER,
			SexPace.SUB_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Nipple-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Push your chest out into [npc.name]'s groin as [npc.her] [npc.cock] thrusts into your [pc.nipple].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Pushing your chest out into [npc.name]'s groin, you let out [pc.a_moan+] as you help to sink [npc.her] [npc.cock+] deep into your [pc.nipple+].",
					"With [pc.a_moan+], you start pushing your chest out, forcing [npc.name]'s [npc.cock+] ever deeper into your [pc.nipple+].",
					"Thrusting your chest out, [pc.a_moan+] drifts out from between your [pc.lips+] as your movements force [npc.name]'s [npc.cock+] deep into your [pc.nipple+]."));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_SUB_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.NIPPLE,
			SexParticipantType.CATCHER,
			SexPace.SUB_EAGER,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Eagerly nipple-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly buck your hips against [npc.name] as [npc.her] [npc.cock] thrusts into your [pc.nipple].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly thrusting your chest out into [npc.name]'s groin, you let out [pc.a_moan+] as you energetically help to sink [npc.her] [npc.cock+] deep into your [pc.nipple+].",
					"With [pc.a_moan+], you energetically start thrusting your [pc.hips] out, forcing [npc.name]'s [npc.cock+] ever deeper into your [pc.nipple+].",
					"Enthusiastically thrusting your chest out, [pc.a_moan+] bursts out from between your [pc.lips+] as your movements force [npc.her] [npc.cock+] deep into your [pc.nipple+]."));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKED_SUB_RESIST = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS,
			OrificeType.NIPPLE,
			SexParticipantType.CATCHER,
			SexPace.SUB_RESISTING,
			null) {
		@Override
		public String getActionTitle() {
			return "Resist nipple-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [pc.breasts+] away from [npc.name]'s [npc.cock+].";
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
									+ " weakly trying to pull [npc.name]'s [npc.cock] out of your [pc.nipple+] as [npc.she] continues gently fucking you.",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.breasts+] back from [npc.name]'s unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.cock+] continues slowly sliding in and out of your [pc.nipple+].",
							"Trying desperately to pull your [pc.breasts+] away from [npc.name], you [pc.sob] in distress as [npc.her] [npc.cock+] continues gently sliding deep into your [pc.nipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You feel tears start to well up in your [pc.eyes], and, not being able to hold back any longer, you suddenly let out [pc.a_sob+],"
									+ " weakly trying to pull [npc.name]'s [npc.cock] out of your [pc.nipple+] as [npc.she] continues eagerly fucking you.",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.breasts+] back from [npc.name]'s unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.cock+] continues eagerly sliding in and out of your [pc.nipple+].",
							"Trying desperately to pull your [pc.breasts+] away from [npc.name], you [pc.sob] in distress as [npc.her] [npc.cock+] continues eagerly sliding deep into your [pc.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You feel tears start to well up in your [pc.eyes], and, not being able to hold back any longer, you suddenly let out [pc.a_sob+],"
									+ " weakly trying to pull [npc.name]'s [npc.cock] out of your [pc.nipple+] as [npc.she] continues roughly fucking you.",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.breasts+] back from [npc.name]'s unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.cock+] continues roughly slamming in and out of your [pc.nipple+].",
							"Trying desperately to pull your [pc.breasts+] away from [npc.name], you [pc.sob] in distress as [npc.her] [npc.cock+] continues roughly slamming deep into your [pc.nipple+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKED_STOP = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS,
			OrificeType.NIPPLE,
			SexParticipantType.CATCHER) {
		
		@Override
		public String getActionTitle() {
			return "Stop nipple-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to pull [npc.her] [npc.cock] out of your [pc.nipple+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc.name]'s [npc.cock] out of your [pc.nipple+], you let out a menacing growl as you command [npc.herHim] to stop fucking you.",
							"You lean into [npc.name], inhaling [npc.her] [npc.scent] before pulling back, yanking [npc.her] [npc.cock] out of your [pc.nipple+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.name]'s [npc.cock] out of your [pc.nipple+], you let out [pc.a_moan+] as you tell [npc.herHim] to stop fucking you.",
							"You lean into [npc.name], inhaling [npc.her] [npc.scent] before sliding [npc.her] [npc.cock] out of your [pc.nipple+]."));
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
							" [npc.Name] lets out [npc.a_moan+] as you stop [npc.herHim] from fucking your [pc.nipple+].",
							" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] desire to continue fucking your [pc.nipple+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
